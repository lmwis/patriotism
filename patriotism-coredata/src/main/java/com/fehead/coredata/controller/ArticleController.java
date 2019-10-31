package com.fehead.coredata.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.fehead.coredata.controller.vo.data.article.ArticleDetailInfo;
import com.fehead.coredata.controller.vo.data.article.ArticleListDisplayInfo;
import com.fehead.coredata.error.BusinessException;
import com.fehead.coredata.error.EmBusinessError;
import com.fehead.coredata.response.CommonReturnType;
import com.fehead.coredata.response.FeheadResponse;
import com.fehead.coredata.service.ArticleDataService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

/**
 * @author lmwis
 * @description:
 * @date 2019-08-16 15:40
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/v1/data/article")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class ArticleController extends BaseController {

    @Autowired
    ArticleDataService articleDataService;

    /**
     * 首页article类型的信息list展示
     *  物理分页
     * @param pageable
     * @return
     * @throws BusinessException
     */
    @GetMapping("/lists")
    @ApiOperation("首页article类型的信息list展示")
    public FeheadResponse articleList(@PageableDefault(size = 10) Pageable pageable) throws BusinessException {

        logger.info("PARAM pageable:" + pageable);

        if(pageable==null){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        ArticleListDisplayInfo articleListDisplayInfo = articleDataService.selectArticleListsPageable(pageable);

        return CommonReturnType.create(articleListDisplayInfo);
    }

    /**
     * 获取article详细信息
     * @param id
     * @return
     * @throws BusinessException
     */
    @GetMapping("/info/{id}")
    @JsonView(ArticleDetailInfo.ArticleTypeView.class)
    @ApiOperation("获取article详细信息")
    public FeheadResponse videoInfoById(@PathVariable("id")String id) throws BusinessException {

        logger.info("PARAM id:" + id);

        if(id.equals(0)){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR) ;
        }

        ArticleDetailInfo articleDetailInfo = articleDataService.selectArticleModelById(new Integer(id));

//        return CommonReturnType.create(videoDataService.findVideoModelById(id));
        return articleDetailInfo;
    }
}
