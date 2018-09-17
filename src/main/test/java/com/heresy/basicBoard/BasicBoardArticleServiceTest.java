package com.heresy.basicBoard;

import com.heresy.domain.board.BasicBoardArticle;
import com.heresy.domain.user.User;
import com.heresy.mapper.BasicBoardArticleMapper;
import com.heresy.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    @Autowired
    UserMapper userMapper;

    @Test
    public void select() throws Exception {
        System.out.println(basicBoardArticleMapper.select());
    }

    @Test
    public void insert() throws Exception {
        BasicBoardArticle basicBoardArticle = new BasicBoardArticle();
        basicBoardArticle.setSubBoardIdx(1);
        basicBoardArticle.setTitle("TESTTITLE");
        basicBoardArticle.setUserIdx(1);
        basicBoardArticle.setUserNickName("TESTUSERNICKNAME");
        basicBoardArticle.setContent("TESTCONTENT");
        basicBoardArticle.setGood(1);
        System.out.println(basicBoardArticleMapper.insert(basicBoardArticle));
    }

    @Test
    public void update() throws Exception {
        BasicBoardArticle basicBoardArticle = new BasicBoardArticle();
        basicBoardArticle.setIdx(2);
        basicBoardArticle.setBad(2);
        basicBoardArticle.setSubBoardIdx(2);
        basicBoardArticle.setTitle("TESTTITLEUPDATE");
        basicBoardArticle.setUserIdx(2);
        basicBoardArticle.setUserNickName("TESTUSERNICKNAMEUPDATE");
        basicBoardArticle.setContent("TESTCONTENTUPDATE");
        System.out.println(basicBoardArticleMapper.update(basicBoardArticle));
    }

    @Test
    public void delete() throws Exception {

        int idx = 2;
        System.out.println(basicBoardArticleMapper.delete(idx));

    }

    @Test
    public void userinsert() throws Exception {
        User user = new User();
        user.setAuthSnsId("1");
        user.setPassword("1");
        user.setExperience(1);
        user.setIntroduction("1");
        user.setTendency(1);
        user.setUserId("1");
        user.setUserNickName("1");
        System.out.println(userMapper.insert(user));
    }
}
