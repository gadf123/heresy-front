package com.heresy.mapper;

import com.heresy.domain.board.BasicBoardArticle;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

/**
 * @user updown
 * @date 2018. 9. 5.
 **/

@Transactional
public interface BasicBoardArticleMapper {

    List<HashMap<String,?>> selectAll();

    List<HashMap<String,?>> selectWithOffset(HashMap parameters);

    BasicBoardArticle selectOne(int articleIdx);

    int insert(BasicBoardArticle basicBoardArticle);

    int updateArticle(BasicBoardArticle basicBoardArticle);

    int delete(int articleIdx);
}
