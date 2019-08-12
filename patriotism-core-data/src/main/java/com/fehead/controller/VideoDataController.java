package com.fehead.controller;

import com.fehead.response.CommonReturnType;
import com.fehead.response.FeheadResponse;
import com.fehead.service.VideoDataService;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lmwis on 2019-08-11 19:48
 */
@RequestMapping("/api/v1/data/video")
@RestController
public class VideoDataController {

    @Autowired
    VideoDataService videoDataService;

    @GetMapping("/lists")
    public FeheadResponse videoLists(@PageableDefault(size = 10) Pageable pageable){

        System.out.println(ReflectionToStringBuilder.toString(pageable));


        return CommonReturnType.create(videoDataService.selectVideoListsPageable(pageable));
    }


    @GetMapping("/info/{id}")
    public FeheadResponse videoInfoById(@PathVariable("id")Integer id){




        return CommonReturnType.create("nu");
    }

}
