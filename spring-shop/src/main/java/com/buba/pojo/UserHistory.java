package com.buba.pojo;


public class UserHistory {

  private Integer id;
  private Integer uid;
  private String keyword;
  private java.sql.Timestamp selectTime;


  public long getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public long getUid() {
    return uid;
  }

  public void setUid(Integer uid) {
    this.uid = uid;
  }


  public String getKeyword() {
    return keyword;
  }

  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }


  public java.sql.Timestamp getSelectTime() {
    return selectTime;
  }

  public void setSelectTime(java.sql.Timestamp selectTime) {
    this.selectTime = selectTime;
  }

}
