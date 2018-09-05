package com.heresy.domain.user;

import lombok.Data;

import java.util.Date;

@Data
public class User {

    private int idx;

    private String userId;

    private String userNickName;

    private int experience;

    private int tendency;

    private String introduction;

    private String authSnsId;

    private Date createDate;

    private Date updateDate;

}
