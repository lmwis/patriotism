package com.fehead.controller;

import com.fehead.controller.vo.data.DataListDisplayInfo;
import com.fehead.controller.vo.data.video.VideoListDisplayInfo;
import com.fehead.error.BusinessException;
import com.fehead.error.EmBusinessError;
import com.fehead.response.CommonReturnType;
import com.fehead.response.FeheadResponse;
import com.fehead.service.DataService;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * 处理首页页面的主要请求
 * @author lmwis on 2019-08-11 19:40
 */

@RestController
@RequestMapping("/api/v1/index/")
@CrossOrigin("*")
public class IndexController {

    @Autowired
    private DataService dataService;

    /**
     * 首页所有信息的展示
     * @param pageable
     * @return
     * @throws BusinessException
     */
    @GetMapping("/lists")
    public FeheadResponse lists(@PageableDefault(size = 10) Pageable pageable) throws BusinessException {

        if(pageable==null){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        DataListDisplayInfo listDisplayInfo = dataService.selectDataListsPageable(pageable);
        System.out.println(listDisplayInfo);
        return CommonReturnType.create(listDisplayInfo);
    }

}
