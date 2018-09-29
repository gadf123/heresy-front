package com.heresy.controller;

import com.heresy.annotations.AuthCheck;
import com.heresy.domain.board.BasicBoardArticle;
import com.heresy.domain.user.User;
import com.heresy.service.BasicBoardArticleService;
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
@RequestMapping("board")
public class BoardController {

    @Autowired
    BasicBoardArticleService basicBoardArticleService;

    @RequestMapping(value = "/write", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    @ResponseBody
    @AuthCheck
    public int write(HttpServletResponse response,
                              @RequestBody BasicBoardArticle basicBoardArticle, User user) {
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Origin", "*");
        basicBoardArticle.setUserIdx(user.getUseridx());
        basicBoardArticle.setUserNickName(user.getUserNickName());
        basicBoardArticle.setSubBoardIdx(0);
        basicBoardArticle.setGood(0);

        return basicBoardArticleService.insert(basicBoardArticle);
    }

    @RequestMapping("/getAllArticle")
    @CrossOrigin(origins = "*")
    @ResponseBody
    public List<HashMap<String, ?>> getAllArticle(HttpServletResponse response, User user){
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Origin", "*");

        return basicBoardArticleService.selectAll();
    }

    @RequestMapping("/getOneArticle")
    @CrossOrigin(origins = "*")
    @ResponseBody
    public BasicBoardArticle getOneArticle(HttpServletResponse response,
                                           @RequestParam("articleIdx") int articleIdx){
        return basicBoardArticleService.selectOne(articleIdx);
    }
}
