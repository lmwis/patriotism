package com.fehead.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fehead.dao.dataobject.Comment;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author lmwis
 * @description:
 * @date 2019-08-14 20:12
 * @Version 1.0
 */
public interface CommentMapper extends BaseMapper<Comment> {


    @Select("select * from comment_info where " +
            "data_id=(select id from data_collect where actual_id=#{id})")
    public List<Comment> selectDataCommentsByActualid(int id);
}
