package com.heresy.controller;

import com.heresy.annotations.AuthCheck;
import com.heresy.domain.board.AgendaAndDebateComment;
import com.heresy.domain.board.BasicBoardComment;
import com.heresy.domain.user.User;
import com.heresy.service.AgendaBoardCommentService;
import com.heresy.service.BasicBoardCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

/**
 * @user park
 * @date 2018. 9. 27.
 **/

@RestController
@RequestMapping("agendaComment")
public class AgendaBoardCommentController {

    @Autowired
    AgendaBoardCommentService agendaBoardCommentService;

    @RequestMapping(value = "/selectAll", method = RequestMethod.GET)
    @CrossOrigin(value = "*")
    public HashMap<String, Object> selectAll(HttpServletResponse response,
                             @RequestParam(value="articleIdx") int articleIdx){
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Origin", "*");

        HashMap<String, Object> comments = agendaBoardCommentService.selectAll(articleIdx);
        return comments;
    }

    @RequestMapping(value = "/Write", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(value = "*")
    @AuthCheck
    public int commentWrite(HttpServletResponse response,
                            @RequestBody AgendaAndDebateComment agendaAndDebateComment, User user){
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Origin", "*");
        agendaAndDebateComment.setUserIdx(user.getUserIdx());
        int result = agendaBoardCommentService.insert(agendaAndDebateComment);
        return result;
    }
}
