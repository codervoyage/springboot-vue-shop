package com.buba.pojo.mall;

import java.util.ArrayList;
import java.util.Objects;

public class MallArea {

  private Integer areaId;
  private String areaCode;
  private String areaName;
  private Integer level;
  private String cityCode;
  private String center;
  private Integer parentId;
  private ArrayList<MallArea> children;

  public ArrayList<MallArea> getChildren() {
    return children;
  }

  public void setChildren(ArrayList<MallArea> children) {
    this.children = children;
  }

  public Integer getAreaId() {
    return areaId;
  }

  public void setAreaId(Integer areaId) {
    this.areaId = areaId;
  }

  public String getAreaCode() {
    return areaCode;
  }

  public void setAreaCode(String areaCode) {
    this.areaCode = areaCode;
  }

  public String getAreaName() {
    return areaName;
  }

  public void setAreaName(String areaName) {
    this.areaName = areaName;
  }

  public Integer getLevel() {
    return level;
  }

  public void setLevel(Integer level) {
    this.level = level;
  }

  public String getCityCode() {
    return cityCode;
  }

  public void setCityCode(String cityCode) {
    this.cityCode = cityCode;
  }

  public String getCenter() {
    return center;
  }

  public void setCenter(String center) {
    this.center = center;
  }

  public Integer getParentId() {
    return parentId;
  }

  public void setParentId(Integer parentId) {
    this.parentId = parentId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    MallArea mallArea = (MallArea) o;
    return Objects.equals(areaId, mallArea.areaId) &&
            Objects.equals(areaCode, mallArea.areaCode) &&
            Objects.equals(areaName, mallArea.areaName) &&
            Objects.equals(level, mallArea.level) &&
            Objects.equals(cityCode, mallArea.cityCode) &&
            Objects.equals(center, mallArea.center) &&
            Objects.equals(parentId, mallArea.parentId) &&
            Objects.equals(children, mallArea.children);
  }

  @Override
  public int hashCode() {
    return Objects.hash(areaId, areaCode, areaName, level, cityCode, center, parentId, children);
  }
}
