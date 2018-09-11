package com.heresy.basicBoard;

import com.heresy.domain.board.BasicBoardArticle;
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
}
