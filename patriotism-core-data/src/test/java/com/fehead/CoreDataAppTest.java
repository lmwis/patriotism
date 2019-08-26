package com.fehead;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fehead.dao.ArticleMapper;
import com.fehead.dao.TagMapper;
import com.fehead.dao.VideoMapper;
import com.fehead.dao.dataobject.Article;
import com.fehead.dao.dataobject.Tag;
import com.fehead.dao.dataobject.Video;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple CoreDataApp.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class CoreDataAppTest
{

    @Autowired
    WebApplicationContext applicationContext;

    @Autowired
    VideoMapper videoMapper;

    MockMvc mockMvc;

    String urlPre = "/api/v1";

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();
    }
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void whenSelectVideoInfoSuccess(){

        List<Video> videos = videoMapper.selectList(null);
        videos.forEach(System.out::println);

    }

    @Autowired
    ArticleMapper articleMapper;
    @Test
    public void whenSelectArticleInfoSuccess(){
        List<Article> articles = articleMapper.selectList(null);
        articles.forEach(k->{
            System.out.println(new ReflectionToStringBuilder(k));
            System.out.println(k.getDatetime().getTime());
        });
    }

    /**
     * 首页分页测试
     * @throws Exception
     */
    @Test
    public void whenFindVideoListsPageableSuccess() throws Exception {
        String result = mockMvc.perform(
                MockMvcRequestBuilders.get(urlPre+"/data/video/lists")
                .param("page","0")
                .param("size","10"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void whenFindVideoInfoSuccess() throws Exception {
        String result = mockMvc.perform(
                MockMvcRequestBuilders.get(urlPre+"/data/video/info/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

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
    @Autowired
    TagMapper tagMapper;
    @Test
    public void whenSelectByMuliTables(){
        List<Tag> tags = tagMapper.selectDataTagsByActualId(1);
        Assert.assertEquals(tags.size(),2);
        tags.forEach(k->{
            System.out.println(new ReflectionToStringBuilder(k));
//            System.out.println(k.getDatetime().getTime());
        });
    }

//    @Test
//    public void whenFindIndexListsPageableSuccess(){
//        String result = mockMvc.perform(MockMvcRequestBuilders.get("/"))
//    }
}
