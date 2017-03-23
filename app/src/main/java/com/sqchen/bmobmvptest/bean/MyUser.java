package com.sqchen.bmobmvptest.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2017/3/22.
 */

public class MyUser extends BmobObject {
    //用户名
    private String userName;

    //密码
    private String userPwd;

    public MyUser() {

    }

    public MyUser(String name, String pwd) {
        this.userName = name;
        this.userPwd = pwd;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return this.userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }
}
