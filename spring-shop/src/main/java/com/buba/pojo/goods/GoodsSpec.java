package com.buba.pojo.goods;

import java.util.Objects;

public class GoodsSpec {
    private Integer goodsId;
    private String goodsColor;
    private String goods_size;

    public GoodsSpec(Integer goodsId, String goodsColor, String goods_size) {
        this.goodsId = goodsId;
        this.goodsColor = goodsColor;
        this.goods_size = goods_size;
    }

    public GoodsSpec() {
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsColor() {
        return goodsColor;
    }

    public void setGoodsColor(String goodsColor) {
        this.goodsColor = goodsColor;
    }

    public String getGoods_size() {
        return goods_size;
    }

    public void setGoods_size(String goods_size) {
        this.goods_size = goods_size;
    }

    @Override
    public String toString() {
        return "GoodsSpec{" +
                "goodsId=" + goodsId +
                ", goodsColor='" + goodsColor + '\'' +
                ", goods_size='" + goods_size + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GoodsSpec goodsSpec = (GoodsSpec) o;
        return Objects.equals(goodsId, goodsSpec.goodsId) &&
                Objects.equals(goodsColor, goodsSpec.goodsColor) &&
                Objects.equals(goods_size, goodsSpec.goods_size);
    }

    @Override
    public int hashCode() {
        return Objects.hash(goodsId, goodsColor, goods_size);
    }
}
