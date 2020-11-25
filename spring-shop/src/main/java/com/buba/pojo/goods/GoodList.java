package com.buba.pojo.goods;

import com.buba.pojo.mall.MallBrand;
import com.buba.pojo.mall.MallCategory;

import java.util.List;
import java.util.Objects;

public class GoodList {
    private Integer goodsId;
    private String goodsName;
    private String goodsPic;
    private String sharePic;
    private String goodsDetail;
    private String goodsUnit;
    private Double marketPrice;
    private Double goodsPrice;
    private Integer isNew;
    private Integer isHot;
    private Integer isSells;

    private MallBrand mallBrand;
    private String keyword;
    private MallCategory mallCategory;
    private List<GoodsPropagate> goodsPropagate;

    public MallBrand getMallBrand() {
        return mallBrand;
    }

    public void setMallBrand(MallBrand mallBrand) {
        this.mallBrand = mallBrand;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public MallCategory getMallCategory() {
        return mallCategory;
    }

    public void setMallCategory(MallCategory mallCategory) {
        this.mallCategory = mallCategory;
    }

    public List<GoodsPropagate> getGoodsPropagate() {
        return goodsPropagate;
    }

    public void setGoodsPropagate(List<GoodsPropagate> goodsPropagate) {
        this.goodsPropagate = goodsPropagate;
    }

    public GoodList() {
    }

    public GoodList(Integer goodsId, String goodsName, String goodsPic, String sharePic, String goodsDetail, String goodsUnit, Double marketPrice, Double goodsPrice, Integer isNew, Integer isHot, Integer isSells, MallBrand mallBrand, String keyword, MallCategory mallCategory, List<GoodsPropagate> goodsPropagate) {
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.goodsPic = goodsPic;
        this.sharePic = sharePic;
        this.goodsDetail = goodsDetail;
        this.goodsUnit = goodsUnit;
        this.marketPrice = marketPrice;
        this.goodsPrice = goodsPrice;
        this.isNew = isNew;
        this.isHot = isHot;
        this.isSells = isSells;
        this.mallBrand = mallBrand;
        this.keyword = keyword;
        this.mallCategory = mallCategory;

        this.goodsPropagate = goodsPropagate;
    }

    @Override
    public String toString() {
        return "GoodList{" +
                "goodsId=" + goodsId +
                ", goodsName='" + goodsName + '\'' +
                ", goodsPic='" + goodsPic + '\'' +
                ", sharePic='" + sharePic + '\'' +
                ", goodsDetail='" + goodsDetail + '\'' +
                ", goodsUnit='" + goodsUnit + '\'' +
                ", marketPrice=" + marketPrice +
                ", goodsPrice=" + goodsPrice +
                ", isNew=" + isNew +
                ", isHot=" + isHot +
                ", isSells=" + isSells +
                ", mallBrand=" + mallBrand +
                ", keyword='" + keyword + '\'' +
                ", mallCategory=" + mallCategory +

                ", goodsPropagate='" + goodsPropagate + '\'' +
                '}';
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsPic() {
        return goodsPic;
    }

    public void setGoodsPic(String goodsPic) {
        this.goodsPic = goodsPic;
    }

    public String getSharePic() {
        return sharePic;
    }

    public void setSharePic(String sharePic) {
        this.sharePic = sharePic;
    }

    public String getGoodsDetail() {
        return goodsDetail;
    }

    public void setGoodsDetail(String goodsDetail) {
        this.goodsDetail = goodsDetail;
    }

    public String getGoodsUnit() {
        return goodsUnit;
    }

    public void setGoodsUnit(String goodsUnit) {
        this.goodsUnit = goodsUnit;
    }

    public Double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Double marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(Double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Integer getIsNew() {
        return isNew;
    }

    public void setIsNew(Integer isNew) {
        this.isNew = isNew;
    }

    public Integer getIsHot() {
        return isHot;
    }

    public void setIsHot(Integer isHot) {
        this.isHot = isHot;
    }

    public Integer getIsSells() {
        return isSells;
    }

    public void setIsSells(Integer isSells) {
        this.isSells = isSells;
    }
}
