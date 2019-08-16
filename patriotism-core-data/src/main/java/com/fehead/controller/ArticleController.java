package com.fehead.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.fehead.controller.vo.data.article.ArticleDetailInfo;
import com.fehead.controller.vo.data.video.VideoDetailInfo;
import com.fehead.error.BusinessException;
import com.fehead.error.EmBusinessError;
import com.fehead.response.CommonReturnType;
import com.fehead.response.FeheadResponse;
import com.fehead.service.ArticleDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lmwis
 * @description:
 * @date 2019-08-16 15:40
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/v1/data/article")
public class ArticleController extends BaseController {

    @Autowired
    ArticleDataService articleDataService;

    /**
     * 首页video类型的信息list展示
     *  物理分页
     * @param pageable
     * @return
     * @throws BusinessException
     */
    @GetMapping("/lists")
    public FeheadResponse articleList(@PageableDefault(size = 10) Pageable pageable) throws BusinessException {

        if(pageable==null){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        return CommonReturnType.create(articleDataService.selectVideoListsPageable(pageable));
    }

    /**
     * 获取article详细信息
     * @param id
     * @return
     * @throws BusinessException
     */
    @GetMapping("/info/{id}")
    @JsonView(ArticleDetailInfo.ArticleTypeView.class)
    public FeheadResponse videoInfoById(@PathVariable("id")Integer id) throws BusinessException {

        if(id==0){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR) ;
        }

        ArticleDetailInfo articleDetailInfo = articleDataService.selectArticleModelById(id);

//        return CommonReturnType.create(videoDataService.findVideoModelById(id));
        return articleDetailInfo;
    }
}
