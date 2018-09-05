package com.heresy.domain.board;

import lombok.Data;

import java.util.Date;


@Data
public class Tendency {

    private int idx;

    private int superRight;

    private int superLeft;

    private int rright;

    private int lleft;

    private Date createDate;

    private Date updateDate;

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public int getSuperRight() {
        return superRight;
    }

    public void setSuperRight(int superRight) {
        this.superRight = superRight;
    }

    public int getSuperLeft() {
        return superLeft;
    }

    public void setSuperLeft(int superLeft) {
        this.superLeft = superLeft;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }


}
