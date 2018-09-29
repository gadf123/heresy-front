package com.heresy.service;

import com.heresy.domain.board.BasicBoardArticle;
import com.heresy.mapper.BasicBoardArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.websocket.RemoteEndpoint;
import java.util.HashMap;
import java.util.List;

/**
 * @user updown
 * @date 2018. 9. 11.
 **/

@Service
public class BasicBoardArticleService {

    @Autowired
    BasicBoardArticleMapper basicBoardArticleMapper;

    public List<HashMap<String, ?>> selectAll() {
        return basicBoardArticleMapper.selectAll();
    }

    public List<HashMap<String, ?>> selectWithOffset(HashMap parameters) {
        return basicBoardArticleMapper.selectWithOffset(parameters);
    }

    public BasicBoardArticle selectOne(int articleIdx) {
        return basicBoardArticleMapper.selectOne(articleIdx);
    }

    public int insert(BasicBoardArticle basicBoardArticle) {
        return basicBoardArticleMapper.insert(basicBoardArticle);
    }

    public int update(BasicBoardArticle basicBoardArticle) {
        return basicBoardArticleMapper.update(basicBoardArticle);
    }

    public int delete(int idx) {
        return basicBoardArticleMapper.delete(idx);
    }

}
