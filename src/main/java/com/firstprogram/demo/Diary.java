package com.firstprogram.demo;
import java.sql.Date;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="diary")
public class Diary {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;    

    @Column(name = "author")
    private String author;

    @Column(name = "subject")
    private String subject;

    @Column(name = "text")
    private String text;

    @Column(name="create_date")
    private Date createDate;

    @Column(name="update_date")
    private Date updateDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "Diary [id= " + id + ", author= " + author + ", subject= " + subject + ", text= " + text + ", create_date= " + createDate + ", update_date= " + updateDate + " ]";
    }

    public Diary(Long id, String author, String subject, String text, Date createDate, Date updateDate) {
        this.id = id;
		this.author = author;
		this.subject = subject;
		this.text = text;
        this.createDate = createDate;
        this.updateDate = updateDate;
	}
}