package com.heresy.mapper;

import com.heresy.domain.board.BasicBoardArticle;
import com.heresy.domain.board.BasicBoardComment;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

/**
 * @user park
 * @date 2018. 9. 27.
 **/

@Transactional
public interface BasicBoardCommentMapper {

    List<BasicBoardComment> selectAll(int articleId);

    int insert(BasicBoardComment basicBoardComment);

    int updateSequence(BasicBoardComment basicBoardComment);

    int deleteOne(int commentIdx);

    int deleteAllByArticle(int articleIdx);
}
