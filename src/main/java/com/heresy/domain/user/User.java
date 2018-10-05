package com.heresy.domain.user;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class User {

    private int userIdx;

    @Email
    private String userId;

    @Size(min=1, max=6)
    private String userNickName;

    @Size(min=6)
    private String password;

    private int experience;

    private int tendency;

    private String introduction;

    private String authSnsId;

    private Date createDate;

    private Date updateDate;

}
