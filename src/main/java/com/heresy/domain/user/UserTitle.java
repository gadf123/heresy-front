package com.heresy.domain.user;

import lombok.Data;

import java.util.Date;

@Data
public class UserTitle {

    private int idx;

    private int userIdx;

    private int titleIdx;

    private Date createDate;

    private Date updateDate;
}
