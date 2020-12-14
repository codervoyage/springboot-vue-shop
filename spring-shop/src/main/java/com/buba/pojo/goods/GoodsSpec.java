package com.buba.pojo.goods;

public class GoodsSpec {

  private long specId;
  private String name;
  private String value;
  private String img;
  private long goodsId;

  public GoodsSpec() {
  }

  public GoodsSpec(long specId, String name, String value, String img, long goodsId) {
    this.specId = specId;
    this.name = name;
    this.value = value;
    this.img = img;
    this.goodsId = goodsId;
  }

  public long getSpecId() {
    return specId;
  }

  public void setSpecId(long specId) {
    this.specId = specId;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }


  public String getImg() {
    return img;
  }

  public void setImg(String img) {
    this.img = img;
  }


  public long getGoodsId() {
    return goodsId;
  }

  public void setGoodsId(long goodsId) {
    this.goodsId = goodsId;
  }

}
