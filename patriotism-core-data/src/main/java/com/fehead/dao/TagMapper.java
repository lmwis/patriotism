package com.fehead.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fehead.dao.dataobject.Tag;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author lmwis on 2019-08-12 16:32
 */
public interface TagMapper extends BaseMapper<Tag> {


    @Select("select * from tags where tags.id in " +
            "(select tag_id from data_tags where data_id=" +
            "(select id from data_collect where actual_id=#{id}))")
    public List<Tag> selectDataTagsByActualId(int id);
}
