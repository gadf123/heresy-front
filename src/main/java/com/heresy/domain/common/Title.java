package com.heresy.domain.common;

import lombok.Data;

import java.util.Date;

@Data
public class Title {

    private int idx;

    private String title;

    private Date createDate;

    private Date updateDate;
}
