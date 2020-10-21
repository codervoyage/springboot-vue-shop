package com.buba.pojo;


public class UserHistory {

  private Integer id;
  private Integer uid;
  private String keyword;
  private String selectTime;

  private User user;


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


  public String getSelectTime() {
    return selectTime;
  }

  public void setSelectTime(String selectTime) {
    this.selectTime = selectTime;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
