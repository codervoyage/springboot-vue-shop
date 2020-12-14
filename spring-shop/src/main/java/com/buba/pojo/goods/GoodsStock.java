package com.buba.pojo.goods;

public class GoodsStock {

    private long stockId;
    private String stockValue;
    private double stockMoney;
    private long stockNum;
    private String img;
    private long goodsId;

    public GoodsStock() {
    }

    public GoodsStock(long stockId, String stockValue, double stockMoney, long stockNum, String img, long goodsId) {
        this.stockId = stockId;
        this.stockValue = stockValue;
        this.stockMoney = stockMoney;
        this.stockNum = stockNum;
        this.img = img;
        this.goodsId = goodsId;
    }

    public long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(long goodsId) {
        this.goodsId = goodsId;
    }

    public long getStockId() {
        return stockId;
    }

    public void setStockId(long stockId) {
        this.stockId = stockId;
    }


    public String getStockValue() {
        return stockValue;
    }

    public void setStockValue(String stockValue) {
        this.stockValue = stockValue;
    }


    public double getStockMoney() {
        return stockMoney;
    }

    public void setStockMoney(double stockMoney) {
        this.stockMoney = stockMoney;
    }


    public long getStockNum() {
        return stockNum;
    }

    public void setStockNum(long stockNum) {
        this.stockNum = stockNum;
    }


    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

}
