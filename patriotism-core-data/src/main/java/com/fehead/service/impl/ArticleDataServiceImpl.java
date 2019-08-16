package com.fehead.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fehead.controller.vo.*;
import com.fehead.controller.vo.data.article.ArticleDetailInfo;
import com.fehead.controller.vo.data.article.ArticleDisplayInfo;
import com.fehead.controller.vo.data.article.ArticleListDisplayInfo;
import com.fehead.controller.vo.data.video.VideoDetailInfo;
import com.fehead.controller.vo.data.video.VideoDisplayInfo;
import com.fehead.dao.ArticleMapper;
import com.fehead.dao.TagMapper;
import com.fehead.dao.dataobject.Article;
import com.fehead.dao.dataobject.Tag;
import com.fehead.dao.dataobject.Video;
import com.fehead.error.BusinessException;
import com.fehead.error.EmBusinessError;
import com.fehead.inherent.DataType;
import com.fehead.service.ArticleDataService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lmwis
 * @description:
 * @date 2019-08-16 15:44
 * @Version 1.0
 */
@Service
public class ArticleDataServiceImpl extends BaseDataService implements ArticleDataService {

    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    TagMapper tagMapper;

    /**
     * 查询article列表展示
     *  物理分页
     * @param pageable
     * @return
     */
    @Override
    public ArticleListDisplayInfo selectVideoListsPageable(Pageable pageable) {
        if (pageable==null) return null;

        QueryWrapper<Article> wrapper = new QueryWrapper<>();

        // 默认按照创建时间进行排序，无修改必要
        wrapper.orderByDesc("datetime");
        // 分页设置
        Page<Article> page = new Page<>(pageable.getPageNumber(),pageable.getPageSize());
        // 分页结果
        IPage<Article> articleIPage = articleMapper.selectPage(page, wrapper);

        ArticleListDisplayInfo lists = new ArticleListDisplayInfo();

        List<ArticleDisplayInfo> articleDisplayInfos = new ArrayList<>();
        articleIPage.getRecords().forEach(re->{
            // 获取标签信息
            List<Tag> tags = tagMapper.selectDataTagsByActualIdAndTypeId(re.getArticleId(),DataType.DATA_ARTICLE.getId());
            List<TagDisplay> tagDisplays = convertFromTag(tags);

            ArticleDisplayInfo articleDisplayInfo = convertFromArticle(re);
            articleDisplayInfo.setArticle_tags(tagDisplays);

            articleDisplayInfos.add(articleDisplayInfo);

        });


        // 最终封装
        lists.setArticle_lists(articleDisplayInfos);
        lists.setPage(pageable.getPageNumber());
        lists.setType_code(DataType.DATA_VIDEO.getCode());
        lists.setType_str(DataType.DATA_VIDEO.getStr());

        return lists;
    }

    /**
     * 获取article详细信息
     * @param id
     * @return
     * @throws BusinessException
     */
    @Override
    public ArticleDetailInfo selectArticleModelById(Integer id) throws BusinessException {

        if(id==0){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        //从数据库获取所需资源

        Article article = articleMapper.selectById(id);


        if(article==null){//资源不存在
            throw new BusinessException(EmBusinessError.DATA_RESOURCES_NOT_EXIST);
        }

        ArticleDisplayInfo articleDisplayInfo = convertFromArticle(article);

        ArticleDetailInfo articleDetailInfo = new ArticleDetailInfo();

        BeanUtils.copyProperties(articleDisplayInfo, articleDetailInfo);

        articleDetailInfo.setArticle_content(article.getContext());

        return articleDetailInfo;
    }

    private ArticleDisplayInfo convertFromArticle(Article article) {
        if(article == null) return null;

        ArticleDisplayInfo articleDisplayInfo = new ArticleDisplayInfo();

        // 封装
        articleDisplayInfo.setId(article.getArticleId());
        articleDisplayInfo.setArticle_author(article.getAuthor());
        articleDisplayInfo.setArticle_des(article.getDes());
        articleDisplayInfo.setArticle_image(article.getImageUrl());
        articleDisplayInfo.setArticle_title(article.getTitle());
        articleDisplayInfo.setArticle_upload_time(article.getDatetime());


        return articleDisplayInfo;
    }
}
