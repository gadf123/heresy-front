package com.heresy.domain.board;

import lombok.Data;

import java.util.Date;

@Data
public class BasicBoardArticle {

    private int idx;

    private int subBoardIdx;

    private String title;

    private int userIdx;

    private String userNickName;

    private String content;

    private int good;

    private int bad;

    private Date createDate;

    private Date updateDate;

}
