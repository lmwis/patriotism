package com.fehead.controller;

import com.fehead.response.CommonReturnType;
import com.fehead.response.FeheadResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lmwis on 2019-08-11 19:48
 */
@RequestMapping("/data/video")
@RestController
public class VideoDataController {


    @GetMapping("/lists")
    public FeheadResponse videoLists(){



        return CommonReturnType.create("nu");
    }

}
