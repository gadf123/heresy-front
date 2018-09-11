package com.heresy.mapper;

import com.heresy.domain.board.BasicBoardArticle;

import java.util.List;

/**
 * @user updown
 * @date 2018. 9. 5.
 **/


public interface BasicBoardArticleMapper {

    List<BasicBoardArticle> select();

    int insert(BasicBoardArticle basicBoardArticle);

    int update(BasicBoardArticle basicBoardArticle);

    int delete(int idx);

}
