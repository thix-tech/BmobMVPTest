package com.sqchen.bmobmvptest.view;

/**
 * Created by Administrator on 2017/3/23.
 */

public interface IUserView {

    /**
     * 获取用户输入的用户名
     */
    String getUserName();

    /**
     * 获取用户输入的密码
     */
    String getUserPwd();

    /**
     * 加载进度对话框
     */
    void showLoading();

    /**
     * 隐藏进度对话框
     */
    void hideLoading();
}
