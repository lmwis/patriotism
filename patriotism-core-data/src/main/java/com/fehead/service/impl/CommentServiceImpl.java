package com.fehead.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fehead.controller.vo.CommentDisplayInfo;
import com.fehead.dao.CommentMapper;
import com.fehead.dao.dataobject.Comment;
import com.fehead.dao.dataobject.Video;
import com.fehead.error.BusinessException;
import com.fehead.error.EmBusinessError;
import com.fehead.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lmwis
 * @description:
 * @date 2019-08-14 20:07
 * @Version 1.0
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentMapper commentMapper;

    @Override
    public List<CommentDisplayInfo> selectCommentByActualId(Integer id, Pageable pageable) throws BusinessException {

        if(id==0){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        // 默认按照创建时间进行排序，无修改必要
        queryWrapper.orderByDesc("datetime");

        queryWrapper.eq("data_id","").inSql("data_id","select" +
                " id from data_collect where actual_id="+id);

        // 分页设置
        Page<Comment> page = new Page<>(pageable.getPageNumber(),pageable.getPageSize());
        // 分页结果
        IPage<Comment> commentIPage = commentMapper.selectPage(page, queryWrapper);

//        List<Comment> comments = commentMapper.selectDataCommentsByActualid(id);
        return null;
    }
}
