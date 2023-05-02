package com.firstprogram.demo;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name="author")
public class Author {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;  

    @OneToMany(mappedBy = "author")
    private List<Diary> diaries;
    
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "second_name")
    private String secondName;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "birth_date")
    private Date birthDate;

    public Author()
    {        
    }
    public Author(String firstName, String secondName, Date birthDate) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.birthDate = birthDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
    
}
