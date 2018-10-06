package com.heresy.controller;

import com.heresy.annotations.AuthCheck;
import com.heresy.domain.board.BasicBoardArticle;
import com.heresy.domain.user.User;
import com.heresy.service.BasicBoardArticleService;
import com.heresy.utills.FileUploader;
import com.heresy.utills.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @Autowired
    FileUploader fileUploader;
    
    @RequestMapping(value = "/write", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    @ResponseBody
    @AuthCheck
    public int write(HttpServletResponse response,
                              @RequestBody BasicBoardArticle basicBoardArticle, User user) {

        basicBoardArticle.setUserIdx(user.getUserIdx());
        basicBoardArticle.setUserNickName(user.getUserNickName());
        basicBoardArticle.setSubBoardIdx(0);
        basicBoardArticle.setGood(0);

        return basicBoardArticleService.insert(basicBoardArticle);
    }

    @RequestMapping(value = "/addImage", method = RequestMethod.POST)
    @CrossOrigin(origins = "*")
    @ResponseBody
    @AuthCheck
    public String addImage(HttpServletResponse response,
                           @RequestParam("image") MultipartFile image, User user){
        System.out.println("------------------");
        String fileName = fileUploader.uploadSingleFile(image);
        System.out.println("------------------");
        return fileName;
    }

    @RequestMapping(value = "/reWrite", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    @ResponseBody
    @AuthCheck
    public int reWrite(HttpServletResponse response,
                     @RequestBody BasicBoardArticle basicBoardArticle, User user) {

        basicBoardArticle.setUserNickName(user.getUserNickName());
        BasicBoardArticle articleFromServer = basicBoardArticleService.selectOne(basicBoardArticle.getArticleIdx());

        int result = 0;

        if(user.getUserIdx() == articleFromServer.getUserIdx()){
            result = basicBoardArticleService.updateArticle(basicBoardArticle);
        }

        return result;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    @ResponseBody
    @AuthCheck
    public int delete(HttpServletResponse response,
                      @RequestBody HashMap body, User user) {

        int articleIdx = Integer.parseInt(body.get("articleIdx").toString());
        BasicBoardArticle basicBoardArticle = basicBoardArticleService.selectOne(articleIdx);

        int result = 0;

        if(user.getUserIdx() == basicBoardArticle.getUserIdx()){
            result = basicBoardArticleService.delete(articleIdx);
        }

        return result;
    }

    @RequestMapping("/getAllArticle")
    @CrossOrigin(origins = "*")
    @ResponseBody
    public HashMap<String, Object> getAllArticle(HttpServletResponse response,
                                                  @RequestParam(value = "pageSize") int pageSize,
                                                  @RequestParam(value = "currentPage") int currentPage){

        int totalSize = basicBoardArticleService.selectAll().size();
        Pagination pagination = new Pagination(pageSize, totalSize, currentPage);

        HashMap<String, Integer> parameters = new HashMap<>();
        parameters.put("offSet", pagination.getOffset());
        parameters.put("pageSize", pageSize);
        List<HashMap<String, ?>> contentList = basicBoardArticleService.selectWithOffset(parameters);

        HashMap<String, Object> result = new HashMap<>();
        result.put("contentList", contentList);
        result.put("pagination", pagination);

        return result;
    }

    @RequestMapping("/getOneArticle")
    @CrossOrigin(origins = "*")
    @ResponseBody
    public BasicBoardArticle getOneArticle(HttpServletResponse response,
                                           @RequestParam("articleIdx") int articleIdx){
        return basicBoardArticleService.selectOne(articleIdx);
    }


}
