package com.heresy.mapper;

import com.heresy.domain.board.AgendaAndDebateComment;
import com.heresy.domain.board.BasicBoardComment;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @user park
 * @date 2018. 9. 27.
 **/

@Transactional
public interface AgendaBoardCommentMapper {

    List<AgendaAndDebateComment> selectAll(int articleId);

    int insert(AgendaAndDebateComment agendaAndDebateComment);

    //int delete(int idx);
}
