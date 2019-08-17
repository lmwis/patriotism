package com.fehead.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fehead.controller.vo.CommentDisplayInfo;
import com.fehead.controller.vo.UserDisplayInfo;
import com.fehead.dao.CommentMapper;
import com.fehead.dao.IfLikeMapper;
import com.fehead.dao.dataobject.Data;
import com.fehead.dao.DataMapper;
import com.fehead.dao.UserMapper;
import com.fehead.dao.dataobject.Comment;
import com.fehead.dao.dataobject.IfLike;
import com.fehead.dao.dataobject.User;
import com.fehead.error.BusinessException;
import com.fehead.error.EmBusinessError;
import com.fehead.inherent.DataType;
import com.fehead.service.CommentService;
import com.fehead.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author lmwis
 * @description:
 * @date 2019-08-14 20:07
 * @Version 1.0
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    DataMapper dataMapper;

    @Autowired
    IfLikeMapper LIfLikeMapper;

    @Autowired
    DataServiceImpl dataService;

    /**
     * 通过dataId和typeId获取评论信息
     *  内存分页
     * @param id
     * @param pageable
     * @param dataType
     * @return
     * @throws BusinessException
     */
    @Override
    public List<CommentDisplayInfo> selectCommentByActualIdAndDataType(Integer id, Pageable pageable,DataType dataType) throws BusinessException {

        if(id==0){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        // 默认按照创建时间进行排序，无修改必要
        queryWrapper.orderByDesc("datetime");

        List<Comment> comments = commentMapper.selectDataCommentsByActualIdAndTypeId(id,dataType.getId());

        // 手动内存分页
        List<Comment> targetComments = convertToPage(comments, pageable);

        if(targetComments==null||targetComments.size()==0){
            return null;
        }

        List<CommentDisplayInfo> commentDisplayInfoList = new ArrayList<>();

        // 封装
        targetComments.forEach(k->{
            CommentDisplayInfo commentDisplayInfo = new CommentDisplayInfo();

            User user = userMapper.selectById(k.getUserId());

            commentDisplayInfo.setComment_content(k.getContent());
            commentDisplayInfo.setLike_num(k.getLikeNum());
            commentDisplayInfo.setUser_avatar(user.getAvatar());
            commentDisplayInfo.setUser_id(user.getId());
            commentDisplayInfo.setUser_name(user.getDisplayName());
            commentDisplayInfo.setDatetime(k.getDatetime());
            commentDisplayInfoList.add(commentDisplayInfo);
        });

//        List<Comment> comments = commentMapper.selectDataCommentsByActualid(id);
        return commentDisplayInfoList;
    }

    @Override
    public List<CommentDisplayInfo> selectVideoCommentByActualId(Integer id, Pageable pageable) throws BusinessException {

        return this.selectCommentByActualIdAndDataType(id,pageable,DataType.DATA_VIDEO);
    }

    @Override
    public List<CommentDisplayInfo> selectArticleCommentByActualId(Integer id, Pageable pageable) throws BusinessException {

        return this.selectCommentByActualIdAndDataType(id,pageable,DataType.DATA_ARTICLE);
    }

    @Override
    public List<CommentDisplayInfo> selectCommentByDataId(Integer id, Pageable pageable) throws BusinessException {

        Data data = dataMapper.selectById(id);

        return this.selectCommentByActualIdAndDataType(data.getActualId(),pageable,dataService.getType(data.getTypeId()));
    }

    /**
     * 添加一条数据
     * @param dataId
     * @param commentDisplayInfo
     * @return
     * @throws BusinessException
     */
    @Override
    public UserDisplayInfo addCommentByDataId(int dataId, CommentDisplayInfo commentDisplayInfo) throws BusinessException {
        if(commentDisplayInfo==null || dataId==0){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        // 验证数据存在性
        Data data = dataMapper.selectById(dataId);
        if(data==null){
            throw new BusinessException(EmBusinessError.DATA_RESOURCES_NOT_EXIST);
        }

        // 数据封装
        Comment comment = new Comment();
        comment.setContent(commentDisplayInfo.getComment_content());
        comment.setDataId(dataId);
        comment.setDatetime(getLocalDatetime());
        comment.setLikeNum(commentDisplayInfo.getLike_num());
        comment.setUserId(commentDisplayInfo.getUser_id());

        int insert = commentMapper.insert(comment);

        User user = userMapper.selectById(commentDisplayInfo.getUser_id());

        return convertFromDO(user);

    }

    @Override
    public IfLike clickLike(int userId, int commentId) throws BusinessException {

//
//        try {
//            comment = commentMapper.selectById(commentId);
//        } catch (Exception e) {
//            throw new BusinessException(EmBusinessError.DATA_RESOURCES_NOT_EXIST, "Comment selectById error");
//        }

        IfLike ifLike = new IfLike();


        ifLike = LIfLikeMapper.selectLikeByUserIdAndCommentId(userId, commentId);

        if (ifLike == null) {
//            ifLike.setUserId(userId);
//            ifLike.setCommentId(commentId);
//            ifLike.setIsLike(1);
//            ifLike.setUpdateTime(new Date());
            ifLike = new IfLike(userId, commentId, 0, new Date());
            LIfLikeMapper.insert(ifLike);
        }
        if (ifLike.getIsLike() == 0) {
            try {
                commentMapper.like(commentId);
                LIfLikeMapper.good(userId, commentId);
            } catch (Exception e) {
                throw new BusinessException(EmBusinessError.DATARESOURCE_CONNECT_FAILURE);
            }

        } else if (ifLike.getIsLike() == 1) {
            try {
                commentMapper.dislike(commentId);
                LIfLikeMapper.cancel(userId, commentId);
            } catch (Exception e) {
                throw new BusinessException(EmBusinessError.DATARESOURCE_CONNECT_FAILURE);
            }

        } else {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        return ifLike;

    }

    private UserDisplayInfo convertFromDO(User user){

        if(user==null){
            return null;
        }

        UserDisplayInfo userDisplayInfo = new UserDisplayInfo();
        userDisplayInfo.setUser_avatar(user.getAvatar());
        userDisplayInfo.setUser_id(user.getId());
        userDisplayInfo.setUser_name(user.getDisplayName());

        return userDisplayInfo;

    }

    /**
     * 手动在内存中分页
     * @param comments
     * @param pageable
     * @return
     */
    private List<Comment> convertToPage(List<Comment> comments,Pageable pageable){

        if(comments==null || comments.size()==0){
            return null;
        }

        List<Comment> result = new ArrayList<>();

        // 总页数
        int totalPage = comments.size()/pageable.getPageSize()+1;

        int filterCounter=(pageable.getPageNumber()-1)*pageable.getPageSize();
        int addCounter = pageable.getPageSize();


        for (Comment c : comments) {
            if(filterCounter--!=0 && filterCounter>=-1){// 数据过滤
                continue;
            }

            if(addCounter--==0 && addCounter>=-1){// 数据数目保障
                break;
            }
            result.add(c);
        }

        return result;

    }

    private Date getLocalDatetime(){
        return new Date();
    }
}
