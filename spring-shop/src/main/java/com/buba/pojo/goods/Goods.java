package com.buba.pojo.goods;

import java.util.List;

public class Goods {
    /*商品*/
    private long goodsId;
    private long num;
    private String name;
    private double money;
    private long isNew;
    private long isHot;
    private long isSell;
    private String images;
    private String img;
    private String company;
    private String keywords;
    private long categoryId;
    private long brandId;
    private String text;
    private String detail;

    private List<GoodsSpec> goodsSpec;
    private List<GoodsStock> goodsStocks;
    private List<GoodsPara> goodsParas;

    public List<GoodsSpec> getGoodsSpec() {
        return goodsSpec;
    }

    public void setGoodsSpec(List<GoodsSpec> goodsSpec) {
        this.goodsSpec = goodsSpec;
    }

    public List<GoodsStock> getGoodsStocks() {
        return goodsStocks;
    }

    public void setGoodsStocks(List<GoodsStock> goodsStocks) {
        this.goodsStocks = goodsStocks;
    }

    public List<GoodsPara> getGoodsParas() {
        return goodsParas;
    }

    public void setGoodsParas(List<GoodsPara> goodsParas) {
        this.goodsParas = goodsParas;
    }

    public long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(long goodsId) {
        this.goodsId = goodsId;
    }

    public long getNum() {
        return num;
    }

    public void setNum(long num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public long getIsNew() {
        return isNew;
    }

    public void setIsNew(long isNew) {
        this.isNew = isNew;
    }

    public long getIsHot() {
        return isHot;
    }

    public void setIsHot(long isHot) {
        this.isHot = isHot;
    }

    public long getIsSell() {
        return isSell;
    }

    public void setIsSell(long isSell) {
        this.isSell = isSell;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public long getBrandId() {
        return brandId;
    }

    public void setBrandId(long brandId) {
        this.brandId = brandId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
