package com.fehead.service;

import com.fehead.controller.vo.CommentDisplayInfo;
import com.fehead.error.BusinessException;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author lmwis
 * @description:
 * @date 2019-08-14 20:06
 * @Version 1.0
 */
public interface CommentService {

    List<CommentDisplayInfo> selectCommentByActualId(Integer id, Pageable pageable) throws BusinessException;
}
