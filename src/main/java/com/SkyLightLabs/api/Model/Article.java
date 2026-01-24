package com.SkyLightLabs.api.Model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="articles")
public class Article {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    private String title;
    private String author;
    private String content;
    private String tags;
    // @Column(name="pdate")
    private Date publishDate;
    // @Column(name="udate")
    private Date updateDate;
    public Article(){}
    public Article(String title,String content,String author,String tags){
        this.content=content;
        this.title=title;
        this.author=author;
        this.tags=tags;
    }
    public int getId() {
        return id;
    }
    public String getContent() {
        return content;
    }
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setTags(String tags) {
        this.tags = tags;
    }
    public String getTags() {
        return tags;
    }
    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
    public Date getPublishDate() {
        return publishDate;
    }
    public Date getUpdateDate() {
        return updateDate;
    }
}
