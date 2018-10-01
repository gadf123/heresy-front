package com.heresy.service;

import com.heresy.domain.board.AgendaAndDebateBoardArticle;
import com.heresy.domain.board.BasicBoardArticle;
import com.heresy.mapper.AgendaBoardArticleMapper;
import com.heresy.mapper.BasicBoardArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @user park
 * @date 2018. 9. 30.
 **/

@Service
public class AgendaBoardArticleService {

    @Autowired
    AgendaBoardArticleMapper agendaBoardArticleMapper;

    public List<HashMap<String, ?>> selectAll() {
        return agendaBoardArticleMapper.selectAll();
    }

    public List<HashMap<String, ?>> selectWithOffset(HashMap parameters) {
        return agendaBoardArticleMapper.selectWithOffset(parameters);
    }

    public AgendaAndDebateBoardArticle selectOne(int articleIdx) {
        return agendaBoardArticleMapper.selectOne(articleIdx);
    }

    public int insert(AgendaAndDebateBoardArticle agendaAndDebateBoardArticle) {
        return agendaBoardArticleMapper.insert(agendaAndDebateBoardArticle);
    }

    public int update(AgendaAndDebateBoardArticle agendaAndDebateBoardArticle) {
        return agendaBoardArticleMapper.update(agendaAndDebateBoardArticle);
    }

    public int delete(int idx) {
        return agendaBoardArticleMapper.delete(idx);
    }

}
