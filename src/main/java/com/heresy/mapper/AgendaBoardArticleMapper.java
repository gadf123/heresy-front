package com.heresy.mapper;

import com.heresy.domain.board.AgendaAndDebateBoardArticle;
import com.heresy.domain.board.BasicBoardArticle;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

/**
 * @user park
 * @date 2018. 9. 30.
 **/

@Transactional
public interface AgendaBoardArticleMapper {

    List<HashMap<String,?>> selectAll();

    AgendaAndDebateBoardArticle selectOne(int articleIdx);

    int insert(AgendaAndDebateBoardArticle agendaAndDebateBoardArticle );

    int update(AgendaAndDebateBoardArticle agendaAndDebateBoardArticle);

    int delete(int idx);

    List<HashMap<String,?>> selectWithOffset(HashMap parameters);
}
