package com.fehead.coredata.service;

import com.fehead.coredata.controller.vo.data.article.ArticleDetailInfo;
import com.fehead.coredata.controller.vo.data.article.ArticleListDisplayInfo;
import com.fehead.coredata.error.BusinessException;
import org.springframework.data.domain.Pageable;

/**
 * @author lmwis
 * @description:
 * @date 2019-08-16 15:43
 * @Version 1.0
 */
public interface ArticleDataService {
    public ArticleListDisplayInfo selectArticleListsPageable(Pageable pageable) throws BusinessException;

    ArticleDetailInfo selectArticleModelById(Integer id) throws BusinessException;
}
