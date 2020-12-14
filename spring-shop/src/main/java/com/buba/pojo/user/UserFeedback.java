package com.buba.pojo.user;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class UserFeedback {

    private Integer id;
    private Integer uid;
    private String feedbackContent;
    private String feedbackPicture;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date feedbackTime;

    private User user;

    public long getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFeedbackTime() {
        return feedbackTime;
    }

    public void setFeedbackTime(Date feedbackTime) {
        this.feedbackTime = feedbackTime;
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


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
