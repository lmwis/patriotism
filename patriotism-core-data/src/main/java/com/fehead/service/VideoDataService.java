package com.fehead.service;

import com.fehead.controller.vo.VideoDisplayInfo;
import com.fehead.controller.vo.VideoListDisplayInfo;
import com.fehead.error.BusinessException;
import com.fehead.service.model.VideoModel;
import org.springframework.data.domain.Pageable;

/**
 * @author lmwis on 2019-08-12 09:42
 */
public interface VideoDataService {

    public VideoModel findVideoMoudleById(Integer id) throws BusinessException;

    public VideoDisplayInfo findVideoDisplayInfoById(Integer id) throws BusinessException;

    VideoListDisplayInfo selectVideoListsPageable(Pageable pageable);
}
