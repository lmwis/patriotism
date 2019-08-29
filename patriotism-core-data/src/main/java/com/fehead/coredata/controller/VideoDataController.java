package com.fehead.coredata.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.fehead.coredata.controller.vo.data.video.VideoDetailInfo;
import com.fehead.coredata.controller.vo.data.video.VideoListDisplayInfo;
import com.fehead.coredata.error.BusinessException;
import com.fehead.coredata.error.EmBusinessError;
import com.fehead.coredata.response.CommonReturnType;
import com.fehead.coredata.response.FeheadResponse;
import com.fehead.coredata.service.CommentService;
import com.fehead.coredata.service.VideoDataService;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

/**
 * @author lmwis on 2019-08-11 19:48
 */
@RequestMapping("/api/v1/data/video")
@RestController
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class VideoDataController extends BaseController {

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
    public FeheadResponse videoList(@PageableDefault(size = 10) Pageable pageable) throws BusinessException {

        logger.info("PARAM pageable:" + pageable);

        if (pageable == null) {
            logger.info("pageable为null");
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
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

        logger.info("PARAM id:" + id);

        if(id==0){
            logger.info("id为0");
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR) ;
        }

        VideoDetailInfo videoDetailInfo = videoDataService.selectVideoModelById(id);

//        return CommonReturnType.create(videoDataService.findVideoModelById(id));
        return videoDetailInfo;
    }

    /**
     * 根据id请求video的评论信息
     *  手动内存分页
     * @param id actual id
     * @param pageable
     * @return
     * @throws BusinessException
     */
//    @GetMapping("/info/{id}/comment")
//    public FeheadResponse videoComment(@PathVariable("id")Integer id,@PageableDefault(size = 6,page = 1) Pageable pageable) throws BusinessException {
//
//        if(id==0){
//            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR) ;
//        }
//
//        return CommonReturnType.create(commentService.selectVideoCommentByActualId(id,pageable));
//    }

}
