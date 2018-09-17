package com.heresy.mapper;

import com.heresy.domain.board.BasicBoardArticle;
import com.heresy.domain.user.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @user updown
 * @date 2018. 9. 5.
 **/

@Transactional
public interface UserMapper {

    List<BasicBoardArticle> select();

    int insert(User user);

    int update(User user);

    int delete(int idx);

}
