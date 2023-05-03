package com.daniel.server.entities;

import com.daniel.server.enums.UserType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "user_name" , nullable = false , unique = true)
    private String userName;

    @Column(name = "password" , nullable = false)
    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "user_type" , nullable = false)
    private UserType userType;

    @ManyToOne
    @JoinColumn(name = "company_id", insertable = false , updatable = false)
    private CompanyEntity company;

    public CompanyEntity getCompany() {
        return company;
    }

    public void setCompany(CompanyEntity company) {
        this.company = company;
    }

    public UserEntity() {
    }

    public UserEntity(long id, String userName, String password, UserType userType) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.userType = userType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }


    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", userType=" + userType +
                '}';
    }
}
