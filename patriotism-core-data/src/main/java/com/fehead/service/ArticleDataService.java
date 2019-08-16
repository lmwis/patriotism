package com.fehead.service;

import com.fehead.controller.vo.data.article.ArticleDetailInfo;
import com.fehead.controller.vo.data.article.ArticleListDisplayInfo;
import com.fehead.error.BusinessException;
import org.springframework.data.domain.Pageable;

/**
 * @author lmwis
 * @description:
 * @date 2019-08-16 15:43
 * @Version 1.0
 */
public interface ArticleDataService {
    public ArticleListDisplayInfo selectVideoListsPageable(Pageable pageable);

    ArticleDetailInfo selectArticleModelById(Integer id) throws BusinessException;
}
