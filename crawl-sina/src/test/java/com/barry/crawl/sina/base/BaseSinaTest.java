package com.barry.crawl.sina.base;

import com.barry.crawl.sina.SinaStarter;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SinaStarter.class)
@WebAppConfiguration
public class BaseSinaTest {

    @Ignore
    @Test
    public void baseTest(){
    }

}