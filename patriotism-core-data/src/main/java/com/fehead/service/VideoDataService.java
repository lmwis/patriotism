package com.fehead.service;

import com.fehead.controller.vo.data.video.VideoDetailInfo;
import com.fehead.controller.vo.data.video.VideoDisplayInfo;
import com.fehead.controller.vo.data.video.VideoListDisplayInfo;
import com.fehead.error.BusinessException;
import org.springframework.data.domain.Pageable;

/**
 * @author lmwis on 2019-08-12 09:42
 */
public interface VideoDataService {

    public VideoDetailInfo selectVideoModelById(Integer id) throws BusinessException;

    public VideoDisplayInfo findVideoDisplayInfoById(Integer id) throws BusinessException;

    VideoListDisplayInfo selectVideoListsPageable(Pageable pageable);

//    VideoListDisplayInfo selectAllVideo();
}
