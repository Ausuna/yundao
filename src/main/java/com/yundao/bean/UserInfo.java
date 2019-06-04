package com.yundao.bean;

import java.util.List;

public class UserInfo extends BaseInfo {

    private String userId;
    private String userName;
    private String roleId;
    private String tel;
    private String email;

    private RoleInfo roleInfo;
    private List<UserLogin> userLogins;
    private List<UserClass> userClasses;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RoleInfo getRoleInfo() {
        return roleInfo;
    }

    public void setRoleInfo(RoleInfo roleInfo) {
        this.roleInfo = roleInfo;
    }

    public List<UserLogin> getUserLogins() {
        return userLogins;
    }

    public void setUserLogins(List<UserLogin> userLogins) {
        this.userLogins = userLogins;
    }

    public List<UserClass> getUserClasses() {
        return userClasses;
    }

    public void setUserClasses(List<UserClass> userClasses) {
        this.userClasses = userClasses;
    }
}
