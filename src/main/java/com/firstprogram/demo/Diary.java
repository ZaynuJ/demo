package com.firstprogram.demo;
import java.util.Date;
import java.util.Set;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name="diary")
public class Diary {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;    

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;

    @Column(name = "subject")
    private String subject;

    @Column(name = "text")
    private String text;
    
    @Temporal(TemporalType.DATE)
    @Column(name="create_date")
    private Date createDate; 

    @Temporal(TemporalType.DATE)
    @Column(name="update_date", nullable = false)
    private Date updateDate;

    @PrePersist
    protected void onCreate() {
      this.createDate = new Date();
      this.updateDate = new Date();
    }
  
    @PreUpdate
    protected void onUpdate() {
      this.updateDate = new Date();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
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

    private Date getCreateDate() {
        return createDate;
    }

     void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    private Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "Diary [id= " + id + ", author= " + author + ", subject= " + subject + ", text= " + text + ", create_date= " + createDate + ", update_date= " + updateDate + " ]";
    }

    Diary() {
        
    }

    public Diary(Author author, String subject, String text) {
		this.author = author;
		this.subject = subject;
		this.text = text;
	}
}