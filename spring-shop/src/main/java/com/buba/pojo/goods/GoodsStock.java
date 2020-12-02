package com.buba.pojo.goods;

import java.util.Objects;

public class GoodsStock {
    private Integer goodsId;
    private Double goodsSale;
    private Double goodsAmout;
    private String goodsPic;

    public GoodsStock() {
    }

    public GoodsStock(Integer goodsId, Double goodsSale, Double goodsAmout, String goodsPic) {
        this.goodsId = goodsId;
        this.goodsSale = goodsSale;
        this.goodsAmout = goodsAmout;
        this.goodsPic = goodsPic;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Double getGoodsSale() {
        return goodsSale;
    }

    public void setGoodsSale(Double goodsSale) {
        this.goodsSale = goodsSale;
    }

    public Double getGoodsAmout() {
        return goodsAmout;
    }

    public void setGoodsAmout(Double goodsAmout) {
        this.goodsAmout = goodsAmout;
    }

    public String getGoodsPic() {
        return goodsPic;
    }

    public void setGoodsPic(String goodsPic) {
        this.goodsPic = goodsPic;
    }

    @Override
    public String toString() {
        return "GoodsStock{" +
                "goodsId=" + goodsId +
                ", goodsSale=" + goodsSale +
                ", goodsAmout=" + goodsAmout +
                ", goodsPic='" + goodsPic + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GoodsStock that = (GoodsStock) o;
        return Objects.equals(goodsId, that.goodsId) &&
                Objects.equals(goodsSale, that.goodsSale) &&
                Objects.equals(goodsAmout, that.goodsAmout) &&
                Objects.equals(goodsPic, that.goodsPic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(goodsId, goodsSale, goodsAmout, goodsPic);
    }
}
