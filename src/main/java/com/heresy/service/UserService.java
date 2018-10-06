package com.heresy.service;

import com.heresy.domain.board.BasicBoardArticle;
import com.heresy.domain.user.User;
import com.heresy.domain.user.UserWithCount;
import com.heresy.mapper.BasicBoardArticleMapper;
import com.heresy.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @user park
 * @date 2018. 9. 11.
 **/

@Service
public class UserService {

    private PasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    UserMapper userMapper;

    public User selectOneByUserId(String userId) {
        return userMapper.selectOneByUserId(userId);
    }

    public User selectOneByNickName(String userNickname) {
        return userMapper.selectOneByNickName(userNickname);
    }

    public int insert(User user) {
        return userMapper.insert(user);
    }

    /*public int update(User user) {
        return userMapper.update(user);
    }*/

    public int updateUserPassword(HashMap upComingUser) {
        return userMapper.updateUserPassword(upComingUser);
    }

    public int updateUserNickName(HashMap upComingUser) {
        return userMapper.updateUserNickName(upComingUser);
    }

    public int delete(int userIdx) {
        return userMapper.delete(userIdx);
    }

    public boolean isPasswordMatch(String password, int userIdx) {
        String encodedPassword = selectPasswordByUserIdx(userIdx);
        return bCryptPasswordEncoder.matches(password, encodedPassword);
    }

    private String selectPasswordByUserIdx(int userIdx){
        return userMapper.selectPasswordByUserIdx(userIdx);
    }

    public UserWithCount selectOneWithCount(int userIdx) {
        return userMapper.selectOneWithCount(userIdx);
    }
}
