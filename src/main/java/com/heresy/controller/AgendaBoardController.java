package com.heresy.controller;

import com.heresy.annotations.AuthCheck;
import com.heresy.domain.board.AgendaAndDebateBoardArticle;
import com.heresy.domain.board.BasicBoardArticle;
import com.heresy.domain.user.User;
import com.heresy.service.AgendaBoardArticleService;
import com.heresy.utills.Pagination;
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
@RequestMapping("agendaBoard")
public class AgendaBoardController {

    @Autowired
    AgendaBoardArticleService agendaBoardArticleService;

    @RequestMapping(value = "/write", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    @ResponseBody
    @AuthCheck
    public int write(HttpServletResponse response,
                     @RequestBody AgendaAndDebateBoardArticle agendaAndDebateBoardArticle, User user) {
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Origin", "*");
        agendaAndDebateBoardArticle.setUserIdx(user.getUseridx());
        agendaAndDebateBoardArticle.setUserNickName(user.getUserNickName());
        agendaAndDebateBoardArticle.setSubBoardIdx(0);
        agendaAndDebateBoardArticle.setGood(0);

        return agendaBoardArticleService.insert(agendaAndDebateBoardArticle);
    }

    @RequestMapping("/getAllArticle")
    @CrossOrigin(origins = "*")
    @ResponseBody
    public HashMap<String, Object> getAllArticle(HttpServletResponse response,
                                                  @RequestParam(value = "pageSize") int pageSize,
                                                  @RequestParam(value = "currentPage") int currentPage){
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Origin", "*");

        int totalSize = agendaBoardArticleService.selectAll().size();
        Pagination pagination = new Pagination(pageSize, totalSize, currentPage);

        HashMap<String, Integer> parameters = new HashMap<>();
        parameters.put("offSet", pagination.getOffset());
        parameters.put("pageSize", pageSize);
        List<HashMap<String, ?>> contentList = agendaBoardArticleService.selectWithOffset(parameters);

        HashMap<String, Object> result = new HashMap<>();
        result.put("contentList", contentList);
        result.put("pagination", pagination);

        return result;
    }

    @RequestMapping("/getOneArticle")
    @CrossOrigin(origins = "*")
    @ResponseBody
    public AgendaAndDebateBoardArticle getOneArticle(HttpServletResponse response,
                                           @RequestParam("articleIdx") int articleIdx){
        return agendaBoardArticleService.selectOne(articleIdx);
    }
}
