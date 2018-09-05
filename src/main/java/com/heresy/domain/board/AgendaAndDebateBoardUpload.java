package com.heresy.domain.board;

import lombok.Data;

import java.util.Date;

@Data
public class AgendaAndDebateBoardUpload {

    private int idx;

    private String fileName;

    private int articleIdx;

    private int userIdx;

    private Date createDate;

    private Date updateDate;

}
