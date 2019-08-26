package com.fehead.initialize.controller;

import com.fehead.initialize.response.CommonReturnType;
import com.fehead.initialize.response.FeheadResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lmwis on 2019-08-11 19:12
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping
    public FeheadResponse hello(){
        return CommonReturnType.create("adsas");
    }
}
