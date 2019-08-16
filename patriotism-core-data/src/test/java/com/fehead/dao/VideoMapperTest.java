package com.fehead.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fehead.dao.dataobject.Comment;
import com.fehead.dao.dataobject.Tag;
import com.fehead.dao.dataobject.Video;
import com.fehead.inherent.DataType;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author lmwis on 2019-08-14 19:36
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class VideoMapperTest {

    @Autowired
    VideoMapper videoMapper;


    @Autowired
    TagMapper tagMapper;

    @Autowired
    CommentMapper commentMapper;

    /**
     * 查询所有video
     */
    @Test
    public void whenSelectVideoInfoSuccess(){

        List<Video> videos = videoMapper.selectList(null);
        videos.forEach(System.out::println);

    }

    /**
     * video分页查询
     */
    @Test
    public void selectPage(){
        QueryWrapper<Video> wrapper = new QueryWrapper<>();

        wrapper.orderByDesc("datetime");

        Page<Video> page = new Page<>(1,2);

        IPage<Video> videoIPage = videoMapper.selectPage(page, wrapper);

        System.out.println("总页数："+videoIPage.getPages());

        System.out.println("总记录数："+videoIPage.getTotal());

        List<Video> records = videoIPage.getRecords();

        records.forEach(k->{
            System.out.println(new ReflectionToStringBuilder(k));
//            System.out.println(k.getDatetime().getTime());
        });

    }


    /**
     * 通过实际id数据和dataType id项查询所包含标签
     */
    @Test
    public void whenSelectByMuliTables(){
        List<Tag> tags = tagMapper.selectDataTagsByActualIdAndTypeId(1,2);
        Assert.assertEquals(tags.size(),2);
        tags.forEach(k->{
            System.out.println(new ReflectionToStringBuilder(k));
//            System.out.println(k.getDatetime().getTime());
        });
    }


    /**
     * 查询系统中所有评论
     */
    @Test
    public void whenSelectCommentsList(){
        List<Comment> comments = commentMapper.selectList(null);
//        Assert.assertEquals(comments.size(),1);

        comments.forEach(k->{
            System.out.println(new ReflectionToStringBuilder(k));
        });
    }

    /**
     * video评论查询分页
     */
    @Test
    public void whenSelectCommentsPageable(){

        int id =1;
        Pageable pageable = new PageRequest(1, 2);

        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        // 默认按照创建时间进行排序，无修改必要
//        queryWrapper.orderByDesc("datetime");

       commentMapper.selectDataCommentsByActualIdAndTypeId(id, DataType.DATA_VIDEO.getId()).forEach(k->{
           System.out.println(new ReflectionToStringBuilder(k));
       });
    }
}
