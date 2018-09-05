package com.heresy.basicBoard;

import com.heresy.mapper.BasicBoardArticleMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @user updown
 * @date 2018. 9. 5.
 **/


@RunWith(SpringRunner.class)
@SpringBootTest
public class BasicBoardArticleServiceTest {

    @Autowired
    BasicBoardArticleMapper basicBoardArticleMapper;

    @Test
    public void select() throws Exception {
        System.out.println(basicBoardArticleMapper.select());
    }
}
