package com.heresy.mapper;

import com.heresy.domain.user.User;
import org.springframework.transaction.annotation.Transactional;


/**
 * @user Park
 * @date 2018. 9. 9.
 **/

@Transactional
public interface UserMapper {

    User selectOneByUserId(String userId);

    User selectOneByNickName(String userNickname);

    int insert(User user);

    int update(User user);

    int delete(int userIdx);
}
