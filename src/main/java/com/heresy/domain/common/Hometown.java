package com.heresy.domain.common;

import lombok.Data;

import java.util.Date;

@Data
public class Hometown {

    private int idx;

    private String hometownName;

    private Date createDate;

    private Date updateDate;
}
