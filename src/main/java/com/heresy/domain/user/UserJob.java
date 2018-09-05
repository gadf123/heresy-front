package com.heresy.domain.user;

import lombok.Data;

import java.util.Date;

@Data
public class UserJob {

    private int idx;

    private int userIdx;

    private int jobIdx;

    private Date createDate;

    private Date updateDate;
}
