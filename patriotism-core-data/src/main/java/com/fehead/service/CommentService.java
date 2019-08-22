package com.fehead.service;

import com.fehead.controller.vo.CommentDisplayInfo;
import com.fehead.controller.vo.UserDisplayInfo;
import com.fehead.dao.dataobject.IfLike;
import com.fehead.error.BusinessException;
import com.fehead.inherent.DataType;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author lmwis
 * @description:
 * @date 2019-08-14 20:06
 * @Version 1.0
 */
public interface CommentService {

    List<CommentDisplayInfo> selectCommentByActualIdAndDataType(Integer userId, Integer id, Pageable pageable, DataType dataType) throws BusinessException;

//    List<CommentDisplayInfo> selectVideoCommentByActualId(Integer id, Pageable pageable) throws BusinessException;

//    List<CommentDisplayInfo> selectArticleCommentByActualId(Integer id, Pageable pageable) throws BusinessException;

    List<CommentDisplayInfo> selectCommentByDataId(Integer id, Integer userId, Pageable pageable) throws BusinessException;

    UserDisplayInfo addCommentByDataId(int dataId, CommentDisplayInfo commentDisplayInfo) throws BusinessException;

    IfLike clickLike(int userId, int commentId) throws BusinessException;
}
