package com.fehead.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.fehead.controller.vo.VideoDetailInfo;
import com.fehead.controller.vo.VideoListDisplayInfo;
import com.fehead.error.BusinessException;
import com.fehead.error.EmBusinessError;
import com.fehead.response.CommonReturnType;
import com.fehead.response.FeheadResponse;
import com.fehead.service.CommentService;
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

    @Autowired
    CommentService commentService;

//    @GetMapping("/lists/all")
//    public FeheadResponse videoAll() {
//
//        return CommonReturnType.create(videoDataService.selectAllVideo());
//    }

    /**
     * 首页video类型的信息list展示
     *  物理分页
     * @param pageable
     * @return
     */
    @GetMapping("/lists")
    public FeheadResponse videoLists(@PageableDefault(size = 10) Pageable pageable){

        System.out.println(ReflectionToStringBuilder.toString(pageable));
        VideoListDisplayInfo videoListDisplayInfo = videoDataService.selectVideoListsPageable(pageable);
        System.out.println(videoListDisplayInfo);
        return CommonReturnType.create(videoListDisplayInfo);
    }


    /**
     * 根据id请求video详细信息
     * @param id actual id
     * @return
     * @throws BusinessException
     */
    @GetMapping("/info/{id}")
    @JsonView(VideoDetailInfo.VideoTypeView.class)
    public FeheadResponse videoInfoById(@PathVariable("id")Integer id) throws BusinessException {

        if(id==0){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR) ;
        }

        VideoDetailInfo videoDetailInfo = videoDataService.selectVideoModelById(id);

//        return CommonReturnType.create(videoDataService.findVideoModelById(id));
        return videoDetailInfo;
    }

    /**
     * 根据id请求video的评论信息
     * @param id actual id
     * @param pageable
     * @return
     * @throws BusinessException
     */
    @GetMapping("/info/{id}/comment")
    public FeheadResponse videoComment(@PathVariable("id")Integer id,@PageableDefault(size = 6,page = 1) Pageable pageable) throws BusinessException {

        if(id==0){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR) ;
        }

        return CommonReturnType.create(commentService.selectCommentByActualId(id,pageable));
    }

}
