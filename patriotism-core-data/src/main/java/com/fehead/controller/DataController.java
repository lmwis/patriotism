package com.fehead.controller;

import com.fehead.controller.vo.CommentDisplayInfo;
import com.fehead.controller.vo.data.DataDisplayInfo;
import com.fehead.controller.vo.data.DataListDisplayInfo;
import com.fehead.controller.vo.data.DataTypeInfo;
import com.fehead.dao.DataMapper;
import com.fehead.error.BusinessException;
import com.fehead.response.CommonReturnType;
import com.fehead.response.FeheadResponse;
import com.fehead.service.CommentService;
import com.fehead.service.DataService;
import com.fehead.service.impl.DataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 统一处理不分数据类型的请求
 * @author lmwis
 * @description:
 * @date 2019-08-16 15:00
 * @Version 1.0
 */

@RestController
@RequestMapping("/api/v1/data/")
public class DataController {

    @Autowired
    CommentService commentService;

    @Autowired
    DataService dataService;

    /**
     * 用户对资源作出评论
     * @param id
     * @param userId
     * @param content
     * @param likeNum
     * @return
     * @throws BusinessException
     */
    @PostMapping("/info/{id}/comment")
    public FeheadResponse doComment(@PathVariable int id
            ,@RequestParam("user_id")int userId
            ,@RequestParam("comment_content") String content
            ,@RequestParam(value = "like_num",required = false,defaultValue = "0")int likeNum) throws BusinessException {

        CommentDisplayInfo commentDisplayInfo = new CommentDisplayInfo();
        commentDisplayInfo.setUser_id(userId);
        commentDisplayInfo.setLike_num(likeNum);
        commentDisplayInfo.setComment_content(content);

//        return null;
        return CommonReturnType.create(commentService.addCommentByDataId(id,commentDisplayInfo));
    }

    /**
     * 根据dataId获取该数据的类型信息
     * @param id
     * @return
     */
    @GetMapping("/info/{id}")
    public FeheadResponse findDataType(@PathVariable int id){

        DataTypeInfo dataTypeInfo = dataService.selectDataTypeInfoById(id);

        return CommonReturnType.create(dataTypeInfo);

    }

    @GetMapping("/lists")
    public FeheadResponse dataList(@PageableDefault(size = 10) Pageable pageable) throws BusinessException {

        DataListDisplayInfo dataListDisplayInfo = dataService.selectDataListsPageable(pageable);

        return CommonReturnType.create(dataListDisplayInfo);
    }
}
