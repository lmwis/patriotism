package com.fehead.coredata.controller;

import com.fehead.coredata.error.BusinessException;
import com.fehead.coredata.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
//    @GetMapping("/lists")
//    public FeheadResponse lists(@PageableDefault(size = 10) Pageable pageable) throws BusinessException {
//
//        if(pageable==null){
//            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
//        }
//
//        DataListDisplayInfo listDisplayInfo = dataService.selectDataListsPageable(pageable);
//        System.out.println(listDisplayInfo);
//        return CommonReturnType.create(listDisplayInfo);
//    }

}
