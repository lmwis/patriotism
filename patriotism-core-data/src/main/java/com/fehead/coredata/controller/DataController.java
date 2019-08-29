package com.fehead.coredata.controller;

import com.fehead.coredata.controller.vo.CommentDisplayInfo;
import com.fehead.coredata.controller.vo.data.DataListDisplayInfo;
import com.fehead.coredata.controller.vo.data.DataTypeInfo;
import com.fehead.coredata.dao.dataobject.IfLike;
import com.fehead.coredata.error.BusinessException;
import com.fehead.coredata.error.EmBusinessError;
import com.fehead.coredata.response.CommonReturnType;
import com.fehead.coredata.response.FeheadResponse;
import com.fehead.coredata.service.CommentService;
import com.fehead.coredata.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

/**
 * 统一处理不分数据类型的请求
 * @author lmwis
 * @description:
 * @date 2019-08-16 15:00
 * @Version 1.0
 */

@RestController
@RequestMapping("/api/v1/data/")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class DataController extends BaseController {

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

        logger.info("PARAM id:" + id);
        logger.info("PARAM userId:" + userId);
        logger.info("PARAM content:" + content);
        logger.info("PARAM likeNum:" + likeNum);
        if (content.isEmpty()) {
            logger.info("content为空");
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

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

        logger.info("PARAM id:" + id);

        DataTypeInfo dataTypeInfo = dataService.selectDataTypeInfoById(id);

        return CommonReturnType.create(dataTypeInfo);

    }

    @GetMapping("/lists")
    public FeheadResponse dataList(@PageableDefault(size = 10) Pageable pageable) throws BusinessException {

        logger.info("PARAM pageable:" + pageable);

        if (pageable == null) {
            logger.info("pageable为null");
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        DataListDisplayInfo dataListDisplayInfo = dataService.selectDataListsPageable(pageable);

        return CommonReturnType.create(dataListDisplayInfo);
    }

    /**
     * 用户对评论点赞或取消点赞
     * @param id
     * @param userId
     * @return
     * @throws BusinessException
     */
    @PutMapping("/info/{id}/comment/{comment_id}")
    public FeheadResponse likeComment(@PathVariable int id
            , @PathVariable("comment_id") int commentId
            , @RequestParam("user_id")int userId) throws BusinessException {

        logger.info("PARAM id:" + id);
        logger.info("PARAM comment_id:" + commentId);
        logger.info("PARAM user_id:" + userId);

        IfLike like = commentService.clickLike(userId, commentId);
        return CommonReturnType.create(like);
    }

    /**
     * 获取评论分页列表
     * @param id
     * @param pageable
     * @return
     * @throws BusinessException
     */
    @GetMapping("/info/{id}/comment")
    public FeheadResponse commentList(@PathVariable int id
            ,@PageableDefault(size = 6,page = 1) Pageable pageable,
                                      @RequestParam("user_id") int userId) throws BusinessException {

        logger.info("PARAM id:" + id);
        logger.info("PARAM pageable:" + pageable);
        logger.info("PARAM user_id:" + userId);

        if (pageable == null) {
            logger.info("pageable为null");
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        return CommonReturnType.create(commentService.selectCommentByDataId(id, userId, pageable));
    }
}
