package com.heresy.service;

import com.heresy.domain.board.AgendaAndDebateComment;
import com.heresy.mapper.AgendaBoardCommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @user park
 * @date 2018. 9. 27.
 **/

@Service
public class AgendaBoardCommentService {

    @Autowired
    AgendaBoardCommentMapper agendaBoardCommentMapper;

    public HashMap<String, Object> selectAll(int articleID) {
        List<AgendaAndDebateComment> agendaAndDebateComments = agendaBoardCommentMapper.selectAll(articleID);

        HashMap<String, Object> filteredResult = new HashMap<>();
        List<AgendaAndDebateComment> posiComments = new ArrayList();
        List<AgendaAndDebateComment> negaComments = new ArrayList();
        try{
            posiComments = agendaAndDebateComments.stream()
                    .filter(comment -> comment.getType().equals("P"))
                    .collect(Collectors.toList());

            negaComments = agendaAndDebateComments.stream()
                    .filter(comment -> comment.getType().equals("N"))
                    .collect(Collectors.toList());

        }catch (Exception e){
            e.printStackTrace();
        }

        filteredResult.put("posiComments", posiComments);
        filteredResult.put("posiCommentsSize", posiComments.size());
        filteredResult.put("negaComments", negaComments);
        filteredResult.put("negaCommentsSize", negaComments.size());

        return filteredResult;
    }

    public int insert(AgendaAndDebateComment agendaAndDebateComment) {
        return agendaBoardCommentMapper.insert(agendaAndDebateComment);
    }

    /*public int delete(int idx) {
        return basicBoardCommentMapper.delete(idx);
    }*/

}
