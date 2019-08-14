package com.fehead;

import com.fehead.dao.ArticleMapper;
import com.fehead.dao.dataobject.Article;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple CoreDataApp.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class CoreDataAppTest
{

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
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






//    @Test
//    public void whenFindIndexListsPageableSuccess(){
//        String result = mockMvc.perform(MockMvcRequestBuilders.get("/"))
//    }
}
