package com.kharevich.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@Entity
@Table(name = "USER")
public class User implements BaseEntity<Long> {

    @Id
    @Column(name = "USER_ID")
    @GeneratedValue
    private long userId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SURNAME")
    private String surname;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "LAST_ACTIVITY")
    private Date lastActivity;

    public Long getId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public Date getLastActivity() {
        return lastActivity;
    }

    public void setId(Long id) {
        this.userId = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLastActivity(Date lastActivity) {
        this.lastActivity = lastActivity;
    }


    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", lastActivity=" + lastActivity +
                '}';
    }
}
