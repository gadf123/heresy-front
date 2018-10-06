package com.heresy.mapper;

import com.heresy.domain.user.User;
import com.heresy.domain.user.UserWithCount;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;


/**
 * @user Park
 * @date 2018. 9. 9.
 **/

@Transactional
public interface UserMapper {

    User selectOneByUserId(String userId);

    User selectOneByNickName(String userNickname);

    String selectPasswordByUserIdx(int userIdx);

    int insert(User user);

    //int update(User user);

    int updateUserPassword(HashMap upComingUser);

    int updateUserNickName(HashMap upComingUser);

    int delete(int userIdx);

    UserWithCount selectOneWithCount(int userIdx);
}
