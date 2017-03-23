package com.sqchen.bmobmvptest.model;

import android.content.Context;

/**
 * Created by Administrator on 2017/3/23.
 */

public interface IUserModel {

    /**
     * 用户登录
     * @param name
     * @param pwd
     * @param listener
     */
    void checkUser(String name,String pwd,OnUserOperationListener listener);

    /**
     * 用户注册
     * @param name
     * @param pwd
     * @param listener
     */
    void registerUser(String name,String pwd,OnUserOperationListener listener);

    interface OnUserOperationListener {

        /**
         * 操作开始
         */
        void onOperationBegin();

        /**
         * 操作成功
         */
        void onSuccess();

        /**
         * 因用户原因，导致操作失败
         */
        void onUserFailed();

        /**
         * 因系统原因，导致操作失败
         */
        void onSysFailed();
    }
    
}
