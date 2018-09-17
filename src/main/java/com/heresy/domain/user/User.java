package com.heresy.domain.user;

import lombok.Data;

import java.util.Date;

@Data
public class User {

    private int useridx;

    private String userId;

    private String userNickName;

    private String password;

    private int experience;

    private int tendency;

    private String introduction;

    private String authSnsId;

    private Date createDate;

    private Date updateDate;

}
