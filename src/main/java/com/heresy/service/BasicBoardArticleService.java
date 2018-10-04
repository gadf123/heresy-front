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

    @Autowired
    BasicBoardCommentService basicBoardCommentService;

    /*In use*/
    public List<HashMap<String, ?>> selectAll() {
        return basicBoardArticleMapper.selectAll();
    }

    /*In use*/
    public List<HashMap<String, ?>> selectWithOffset(HashMap parameters) {
        return basicBoardArticleMapper.selectWithOffset(parameters);
    }

    /*In use*/
    public BasicBoardArticle selectOne(int articleIdx) {
        return basicBoardArticleMapper.selectOne(articleIdx);
    }

    /*In use*/
    public int insert(BasicBoardArticle basicBoardArticle) {
        return basicBoardArticleMapper.insert(basicBoardArticle);
    }

    /*In use*/
    public int updateArticle(BasicBoardArticle basicBoardArticle) {
        return basicBoardArticleMapper.updateArticle(basicBoardArticle);
    }

    /*In use*/
    public int delete(int articleIdx) {
        basicBoardCommentService.deleteAllByArticle(articleIdx);
        return basicBoardArticleMapper.delete(articleIdx);
    }

}
