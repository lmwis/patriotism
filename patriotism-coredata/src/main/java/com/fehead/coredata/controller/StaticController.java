package com.fehead.coredata.controller;

import com.fehead.coredata.dao.StaticMapper;
import com.fehead.coredata.dao.dataobject.Static;
import com.fehead.coredata.response.CommonReturnType;
import com.fehead.coredata.response.FeheadResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
 * @author Nightnessss 2019/8/26 18:21
 */
@RestController
@RequestMapping("/api/v1/static")
public class StaticController {

    @Autowired
    private StaticMapper staticMapper;

    @GetMapping("/slideShow")
    public FeheadResponse getSlideShowPicture() {

        List<Static> list = staticMapper.selectSlide_show();

        return CommonReturnType.create(list);
    }
}
