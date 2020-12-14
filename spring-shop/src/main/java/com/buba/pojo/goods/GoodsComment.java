package com.buba.pojo.goods;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class GoodsComment {

    private Integer cId;
    private Integer userId;
    private Integer goodsId;
    private Integer scoring;
    private String text;
    private String cImg;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getScoring() {
        return scoring;
    }

    public void setScoring(Integer scoring) {
        this.scoring = scoring;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getcImg() {
        return cImg;
    }

    public void setcImg(String cImg) {
        this.cImg = cImg;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
