package com.buba.pojo.mall;


import java.util.Map;

public class MallCategory {

  private long id;
  private long fid;
  private String typeName;
  private String typeLcon;
  private String typePicture;
  private String typeKeyword;
  private String typeContent;
  private long typeGrade;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getFid() {
    return fid;
  }

  public void setFid(long fid) {
    this.fid = fid;
  }


  public String getTypeName() {
    return typeName;
  }

  public void setTypeName(String typeName) {
    this.typeName = typeName;
  }


  public String getTypeLcon() {
    return typeLcon;
  }

  public void setTypeLcon(String typeLcon) {
    this.typeLcon = typeLcon;
  }


  public String getTypePicture() {
    return typePicture;
  }

  public void setTypePicture(String typePicture) {
    this.typePicture = typePicture;
  }


  public String getTypeKeyword() {
    return typeKeyword;
  }

  public void setTypeKeyword(String typeKeyword) {
    this.typeKeyword = typeKeyword;
  }


  public String getTypeContent() {
    return typeContent;
  }

  public void setTypeContent(String typeContent) {
    this.typeContent = typeContent;
  }


  public long getTypeGrade() {
    return typeGrade;
  }

  public void setTypeGrade(long typeGrade) {
    this.typeGrade = typeGrade;
  }

}
