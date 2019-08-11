package com.fehead;

import static org.junit.Assert.assertTrue;

import com.fehead.dao.VideoMapper;
import com.fehead.dao.dataobject.Video;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Unit test for simple CoreDataApp.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class CoreDataAppTest
{

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    VideoMapper videoMapper;
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
}
