package com.heresy.domain.user;

import lombok.Data;

import java.util.Date;

@Data
public class UserRoles {

    private int idx;

    private int userIdx;

    private int roleIdx;

    private Date createDate;

    private Date updateDate;

}
