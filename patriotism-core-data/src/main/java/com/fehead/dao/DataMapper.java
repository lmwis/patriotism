package com.fehead.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fehead.controller.vo.data.DataDisplayInfo;
import com.fehead.controller.vo.data.DataTypeInfo;
import com.fehead.dao.dataobject.Data;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

/**
 * @author lmwis
 * @description:
 * @date 2019-08-16 15:14
 * @Version 1.0
 */
public interface DataMapper extends BaseMapper<Data> {

    @Results(value = {
            @Result(id = true,column="id",property = "id"),
            @Result(column="actual_id",property = "actual_id"),
            @Result(column = "code",property = "type_code"),
            @Result(column = "str",property = "type_str")}
    )

    @Select("select * from data_collect,types where " +
            "data_collect.id=#{id} and data_collect.type_id=types.id;")
    public DataTypeInfo selectDataDisplayInfoById(int id);

}
