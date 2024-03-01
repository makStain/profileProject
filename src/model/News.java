package model;

import java.sql.Timestamp;

public class News {
    private Long id;
    private Timestamp postDate;
    private Long categoryID;
    private String title;
    private String content;

    public News() {
    }

    public News(Long id, Timestamp postDate, Long categoryID, String title, String content) {
        this.id = id;
        this.postDate = postDate;
        this.categoryID = categoryID;
        this.title = title;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getPostDate() {
        return postDate;
    }

    public void setPostDate(Timestamp postDate) {
        this.postDate = postDate;
    }

    public Long getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Long categoryID) {
        this.categoryID = categoryID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
