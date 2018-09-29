package com.heresy.controller;

import com.heresy.annotations.AuthCheck;
import com.heresy.domain.board.BasicBoardComment;
import com.heresy.domain.user.User;
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
@RequestMapping("basicComment")
public class CommentController {

    @Autowired
    BasicBoardCommentService basicBoardCommentService;

    @RequestMapping(value = "/selectAll", method = RequestMethod.GET)
    @CrossOrigin(value = "*")
    public List<BasicBoardComment> selectAll(HttpServletResponse response,
                                             @RequestParam(value="articleIdx") int articleIdx){
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Origin", "*");

        List<BasicBoardComment> comments = basicBoardCommentService.selectAll(articleIdx);
        return comments;
    }

    @RequestMapping(value = "/Write", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(value = "*")
    @AuthCheck
    public int commentWrite(HttpServletResponse response,
                            @RequestBody BasicBoardComment basicBoardComment, User user){
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Origin", "*");
        basicBoardComment.setSequence(1);
        basicBoardComment.setUserIdx(user.getUseridx());
        int result = basicBoardCommentService.insert(basicBoardComment);
        return result;
    }

    @RequestMapping(value = "/Rewrite", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(value = "*")
    @AuthCheck
    public int commentReWrite(HttpServletResponse response,
                            @RequestBody BasicBoardComment basicBoardComment, User user){
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Origin", "*");

        basicBoardComment.setUserIdx(user.getUseridx());
        int updateResult = basicBoardCommentService.updateSequence(basicBoardComment);
        System.out.println(updateResult);
        basicBoardComment.setSequence(basicBoardComment.getSequence()+1);
        int result = basicBoardCommentService.insert(basicBoardComment);
        return result;
    }
}
