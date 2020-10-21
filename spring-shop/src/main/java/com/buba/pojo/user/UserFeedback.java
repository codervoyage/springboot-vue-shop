package com.buba.pojo.user;


public class UserFeedback {

  private Integer id;
  private Integer uid;
  private String feedbackContent;
  private String feedbackPicture;
  private String feedbackTime;

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


  public String getFeedbackContent() {
    return feedbackContent;
  }

  public void setFeedbackContent(String feedbackContent) {
    this.feedbackContent = feedbackContent;
  }


  public String getFeedbackPicture() {
    return feedbackPicture;
  }

  public void setFeedbackPicture(String feedbackPicture) {
    this.feedbackPicture = feedbackPicture;
  }


  public String getFeedbackTime() {
    return feedbackTime;
  }

  public void setFeedbackTime(String feedbackTime) {
    this.feedbackTime = feedbackTime;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
