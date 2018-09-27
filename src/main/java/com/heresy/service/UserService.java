package com.heresy.service;

import com.heresy.domain.board.BasicBoardArticle;
import com.heresy.domain.user.User;
import com.heresy.mapper.BasicBoardArticleMapper;
import com.heresy.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @user park
 * @date 2018. 9. 11.
 **/

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public User selectOne(String userId) {
        return userMapper.selectOne(userId);
    }

    public int insert(User user) {
        return userMapper.insert(user);
    }

    public int update(User user) {
        return userMapper.update(user);
    }

    public int delete(int idx) {
        return userMapper.delete(idx);
    }


}
