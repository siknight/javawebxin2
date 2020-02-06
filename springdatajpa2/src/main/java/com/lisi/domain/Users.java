package com.lisi.domain;

import com.lisi.domain.Roles;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 用户的数据模型
 */
@Entity
@Table(name="users")
public class Users implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long userId;
    @Column(name="user_code")
    private String userCode;
    @Column(name="user_name")
    private String userName;
    @Column(name="user_password")
    private String userPassword;
    @Column(name="user_state")
    private String userState;

    @ManyToMany(mappedBy = "users")
    private Set<Roles> roles =new HashSet<Roles>();

//    @ManyToMany(mappedBy = "users")
//    private Set<Roles> roles ;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState;
    }

    public Set<Roles> getRoles() {
        return roles;
    }

    public void setRoles(Set<Roles> roles) {
        this.roles = roles;
    }
}
