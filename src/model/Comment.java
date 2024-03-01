package model;

import java.sql.Timestamp;

public class Comment {
    private Long id;
    private String comment;
    private Timestamp postDate;
    private Users userID;
    private News newsID;

    public Comment() {
    }

    public Comment(Long id, String comment, Timestamp postDate, Users userID, News newsID) {
        this.id = id;
        this.comment = comment;
        this.postDate = postDate;
        this.userID = userID;
        this.newsID = newsID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Timestamp getPostDate() {
        return postDate;
    }

    public void setPostDate(Timestamp postDate) {
        this.postDate = postDate;
    }

    public Users getUserID() {
        return userID;
    }

    public void setUserID(Users userID) {
        this.userID = userID;
    }

    public News getNewsID() {
        return newsID;
    }

    public void setNewsID(News newsID) {
        this.newsID = newsID;
    }
}
