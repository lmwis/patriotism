package com.fehead.coredata.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fehead.coredata.controller.vo.TagDisplay;
import com.fehead.coredata.controller.vo.data.DataDisplayInfo;
import com.fehead.coredata.controller.vo.data.DataListDisplayInfo;
import com.fehead.coredata.controller.vo.data.DataTypeInfo;
import com.fehead.coredata.dao.ArticleMapper;
import com.fehead.coredata.dao.DataMapper;
import com.fehead.coredata.dao.TagMapper;
import com.fehead.coredata.dao.VideoMapper;
import com.fehead.coredata.dao.dataobject.Data;
import com.fehead.coredata.dao.dataobject.Tag;
import com.fehead.coredata.error.BusinessException;
import com.fehead.coredata.error.EmBusinessError;
import com.fehead.coredata.inherent.DataType;
import com.fehead.coredata.service.DataService;
import com.fehead.coredata.service.VideoDataService;
import com.fehead.coredata.utils.PageUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * @author lmwis
 * @description:
 * @date 2019-08-16 15:55
 * @Version 1.0
 */
@Service
public class DataServiceImpl extends BaseDataService implements DataService {

    Logger logger = LoggerFactory.getLogger(DataServiceImpl.class);

    @Autowired
    DataMapper dataMapper;

    @Autowired
    TagMapper tagMapper;

    @Autowired
    VideoDataService videoDataService;

    @Autowired
    VideoMapper videoMapper;

    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    PageUtil pageUtil;

    /**
     * 根据id获取信息
     * @param dataId
     * @return
     */
    @Override
    public DataTypeInfo selectDataTypeInfoById(int dataId) {

        DataTypeInfo dataTypeInfo = dataMapper.selectDataDisplayInfoById(dataId);

        return dataTypeInfo;
    }

    /**
     * 查询dataList信息
     *  物理分页
     * @param pageable
     * @return
     * @throws BusinessException
     */
    @Override
    public DataListDisplayInfo selectDataListsPageable(Pageable pageable) throws BusinessException {

        if (pageable==null) return null;

        QueryWrapper<Data> wrapper = new QueryWrapper<>();

        // 默认按照创建时间进行排序，无修改必要
        wrapper.orderByDesc("datetime");
        // 分页设置
        Page<Data> page = new Page<>(pageable.getPageNumber(),pageable.getPageSize());
        // 分页结果
        IPage<Data> dataIPage = dataMapper.selectPage(page, wrapper);

        DataListDisplayInfo lists = new DataListDisplayInfo();

        List<DataDisplayInfo> dataDisplayInfos = datasToDataDisplayInfos(dataIPage.getRecords());

//        List<DataDisplayInfo> dataDisplayInfos = new ArrayList<>();
//        for (Data re : dataIPage.getRecords()) {
//            // 获取标签信息
//            List<Tag> tags = tagMapper.selectDataTagsByActualIdAndTypeId(re.getActualId(),re.getTypeId());
//            List<TagDisplay> tagDisplays = convertFromTag(tags);
//
//            // 分类封装
//            com.fehead.coredata.dao.Data dataSources =null;
//            DataType dataType = getType(re.getTypeId());
//            if(dataType == DataType.DATA_VIDEO){// video类型
//                dataSources = videoMapper.selectById(re.getActualId());
//            }else if(dataType == DataType.DATA_ARTICLE){// article类型
//                dataSources = articleMapper.selectById(re.getActualId());
//            }else{// 类型错误
//                throw new BusinessException(EmBusinessError.DATA_RESOURCES_TYPE_WRONG);
//            }
//
//            DataDisplayInfo dataDisplayInfo = convertFromData(re,dataSources);
//
//            dataDisplayInfo.setType_code(dataType.getCode());
//            dataDisplayInfo.setType_str(dataType.getStr());
//            dataDisplayInfo.setTags(tagDisplays);
//
//            dataDisplayInfos.add(dataDisplayInfo);
//        }

        // 最终封装
        lists.setData_lists(dataDisplayInfos);
        lists.setPage(pageable.getPageNumber());

        return lists;
    }

    @Override
    public DataListDisplayInfo selectDateListByTags(String tags, Pageable pageable) throws BusinessException {

        if (StringUtils.isEmpty(tags)) {
            logger.info("EXCEPTION: " + EmBusinessError.PARAMETER_VALIDATION_ERROR.getErrorCode() + " "
                        + EmBusinessError.PARAMETER_VALIDATION_ERROR.getErrorMsg() + " tags");
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        QueryWrapper<Data> queryWrapper = new QueryWrapper<>();
        // 默认按照创建时间进行排序，无修改必要
        queryWrapper.orderByDesc("datetime");

        List<String> tagList = Arrays.asList(tags.split("-"));
        List<Data> dataList = new ArrayList<>();

        for (String tag:tagList){
            int t = 0;
            try {
                t = Integer.parseInt(tag);
            } catch (Exception e) {
                logger.info("EXCEPTION: " + EmBusinessError.PARAMETER_VALIDATION_ERROR.getErrorCode() + " "
                        + EmBusinessError.PARAMETER_VALIDATION_ERROR.getErrorMsg() + " tags");
                throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "tags不合法");
            }
            List<Data> data = dataMapper.selectDataByTag(t);
            dataList.addAll(data);
        }
        // 去重
        List<Data> finalDataList = pageUtil.convertToPage(removeDuplicate(dataList), pageable);

        DataListDisplayInfo lists = new DataListDisplayInfo();
        List<DataDisplayInfo> dataDisplayInfos = datasToDataDisplayInfos(finalDataList);
        lists.setData_lists(dataDisplayInfos);
        lists.setPage(pageable.getPageNumber());

        return lists;
    }

    /**
     * 根据typeId获取系统中Type的枚举类
     * @param typeId
     * @return
     * @throws BusinessException
     */
    protected DataType getType(int typeId) throws BusinessException {

        if( typeId ==0) return null;

        for (DataType value : DataType.values()) {
            if(value.getId()==typeId){
                return value;
            }
        }
        throw new BusinessException(EmBusinessError.DATA_RESOURCES_TYPE_WRONG);
    }

    private DataDisplayInfo convertFromData(Data data, com.fehead.coredata.dao.Data dataSources) {

        if(data == null) return null;

        DataDisplayInfo dataDisplayInfo = new DataDisplayInfo();

        // 封装
        dataDisplayInfo.setId(data.getId());
        dataDisplayInfo.setAuthor(dataSources.getAuthor());
        dataDisplayInfo.setDes(dataSources.getDes());
        dataDisplayInfo.setImage(dataSources.getImageUrl());
        dataDisplayInfo.setTitle(dataSources.getTitle());
        dataDisplayInfo.setUpload_time(dataSources.getDatetime());

        return dataDisplayInfo;
    }

    private List<DataDisplayInfo> datasToDataDisplayInfos(List<Data> dataList) throws BusinessException {
        List<DataDisplayInfo> dataDisplayInfos = new ArrayList<>();
        for (Data re : dataList) {
            // 获取标签信息
            List<Tag> tags = tagMapper.selectDataTagsByActualIdAndTypeId(re.getActualId(),re.getTypeId());
            List<TagDisplay> tagDisplays = convertFromTag(tags);

            // 分类封装
            com.fehead.coredata.dao.Data dataSources =null;
            DataType dataType = getType(re.getTypeId());
            if(dataType == DataType.DATA_VIDEO){// video类型
                dataSources = videoMapper.selectById(re.getActualId());
            }else if(dataType == DataType.DATA_ARTICLE){// article类型
                dataSources = articleMapper.selectById(re.getActualId());
            }else{// 类型错误
                throw new BusinessException(EmBusinessError.DATA_RESOURCES_TYPE_WRONG);
            }

            DataDisplayInfo dataDisplayInfo = convertFromData(re,dataSources);

            dataDisplayInfo.setType_code(dataType.getCode());
            dataDisplayInfo.setType_str(dataType.getStr());
            dataDisplayInfo.setTags(tagDisplays);

            dataDisplayInfos.add(dataDisplayInfo);
        }
        return dataDisplayInfos;
    }

    private <K> List<K> removeDuplicate(List<K> list) {
        HashSet h = new HashSet(list);
        list.clear();
        list.addAll(h);
        return list;
    }
}
