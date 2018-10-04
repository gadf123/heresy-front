package com.heresy.service;

import com.heresy.domain.board.BasicBoardArticle;
import com.heresy.domain.board.BasicBoardComment;
import com.heresy.mapper.BasicBoardArticleMapper;
import com.heresy.mapper.BasicBoardCommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @user park
 * @date 2018. 9. 27.
 **/

@Service
public class BasicBoardCommentService {

    @Autowired
    BasicBoardCommentMapper basicBoardCommentMapper;

    public List<BasicBoardComment> selectAll(int articleID) {
        return basicBoardCommentMapper.selectAll(articleID);
    }

    public int insert(BasicBoardComment basicBoardComment) {
        return basicBoardCommentMapper.insert(basicBoardComment);
    }

    public int updateSequence(BasicBoardComment basicBoardComment) {
        return basicBoardCommentMapper.updateSequence(basicBoardComment);
    }

    public int deleteOne(int commentIdx) {
        return basicBoardCommentMapper.deleteOne(commentIdx);
    }

    public int deleteAllByArticle(int articleIdx) {
        return basicBoardCommentMapper.deleteAllByArticle(articleIdx);
    }

}
