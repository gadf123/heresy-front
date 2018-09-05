package com.heresy.domain.user;

import lombok.Data;

import java.util.Date;

@Data
public class UserHomeTown {

    private int idx;

    private int userIdx;

    private int hometownIdx;

    private Date createDate;

    private Date updateDate;

}
