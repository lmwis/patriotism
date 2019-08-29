package com.fehead.coredata.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fehead.coredata.dao.dataobject.Static;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 写代码 敲快乐
 * だからよ...止まるんじゃねぇぞ
 * ▏n
 * █▏　､⺍
 * █▏ ⺰ʷʷｨ
 * █◣▄██◣
 * ◥██████▋
 * 　◥████ █▎
 * 　　███▉ █▎
 * 　◢████◣⌠ₘ℩
 * 　　██◥█◣\≫
 * 　　██　◥█◣
 * 　　█▉　　█▊
 * 　　█▊　　█▊
 * 　　█▊　　█▋
 * 　　 █▏　　█▙
 * 　　 █
 *
 * @author Nightnessss 2019/8/26 18:34
 */
public interface StaticMapper extends BaseMapper<Static> {

    @Select("SELECT * FROM static " +
            "WHERE slide_show != 0")
    public List<Static> selectSlide_show();
}
