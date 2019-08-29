package com.fehead.coredata.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fehead.coredata.dao.dataobject.IfLike;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


/**
 * @author lmwis
 * @description:
 * @date 2019-08-17 09:31
 * @Version 1.0
 */
public interface IfLikeMapper extends BaseMapper<IfLike> {


    @Select("select * from ifLike " +
            "where user_id=#{userId} " +
            "and comment_id=#{commentId}")
    public IfLike selectLikeByUserIdAndCommentId(@Param("userId") int userId, @Param("commentId") int commentId);

    @Update("update ifLike " +
            "set is_like=1 " +
            "where user_id=#{userId} " +
            "and comment_id=#{commentId}")
    public void good(@Param("userId") int userId, @Param("commentId") int commentId);

    @Update("update ifLike " +
            "set is_like=0 " +
            "where user_id=#{userId} " +
            "and comment_id=#{commentId}")
    public void cancel(@Param("userId") int userId, @Param("commentId") int commentId);

}
