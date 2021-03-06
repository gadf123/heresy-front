package com.heresy.domain.board;

import lombok.Data;

import java.util.Date;

@Data
public class BasicBoardComment {

    private int commentIdx;

    private int articleIdx;

    private int userIdx;

    private String comment;

    private int good;

    private int bad;

    private Date createDate;

    private Date updateDate;

    private int groupNum;

    private int sequence;

    private int depth;
}
