package com.heresy.mapper;

import com.heresy.domain.user.User;
import org.springframework.transaction.annotation.Transactional;


/**
 * @user Park
 * @date 2018. 9. 9.
 **/

@Transactional
public interface UserMapper {

    User selectOne(String userId);

    int insert(User user);

    int update(User user);

    int delete(int idx);

}
