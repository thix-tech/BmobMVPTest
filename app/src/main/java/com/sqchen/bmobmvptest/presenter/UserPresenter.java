package com.sqchen.bmobmvptest.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.sqchen.bmobmvptest.activity.MainActivity;
import com.sqchen.bmobmvptest.model.IUserModel;
import com.sqchen.bmobmvptest.model.IUserModelImpl;
import com.sqchen.bmobmvptest.view.IUserView;

import static cn.bmob.v3.Bmob.getApplicationContext;

/**
 * Created by Administrator on 2017/3/23.
 */

public class UserPresenter extends BasePresenter<IUserView>{

    //model
    private IUserModel mUserModel;

    //view
    private IUserView mUserView;

    /**
     * 实例化view
     * @param mUserview
     */
    public UserPresenter(IUserView mUserview) {
        super();
        this.mUserModel = new IUserModelImpl();
        this.mUserView = mUserview;
    }

    /**
     * bind view and model for user login
     *
     * @param context   上下文环境
     * @param name  用户名
     * @param pwd   密码
     */
    public void check(final Context context, String name, String pwd) {
        //显示进度对话框
        mUserView.showLoading();
        if(mUserModel != null) {
            mUserModel.checkUser(name, pwd, new IUserModel.OnUserOperationListener() {
                @Override
                public void onOperationBegin() {
                }

                @Override
                public void onSuccess() {
                    mUserView.hideLoading();
                    Toast.makeText(getApplicationContext(),
                            "登录成功！",
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    context.startActivity(intent);
                }

                @Override
                public void onUserFailed() {
                    mUserView.hideLoading();
                    Toast.makeText(getApplicationContext(),
                            "用户名或密码错误，请重新输入！",
                            Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onSysFailed() {
                    mUserView.hideLoading();
                    Toast.makeText(getApplicationContext(),
                            "登录失败，请检查网络设置！",
                            Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    /**
     * bind view and model for user register
     *
     * @param context
     * @param name
     * @param pwd
     */
    public void register(final Context context,String name,String pwd) {
        //显示进度对话框
        mUserView.showLoading();
        if(mUserModel != null) {
            mUserModel.registerUser(name, pwd, new IUserModel.OnUserOperationListener() {
                @Override
                public void onOperationBegin() {
                }

                @Override
                public void onSuccess() {
                    mUserView.hideLoading();
                    Toast.makeText(getApplicationContext(),
                            "注册成功！",
                            Toast.LENGTH_SHORT).show();
                    ((Activity) context).finish();
                }

                @Override
                public void onUserFailed() {
                    mUserView.hideLoading();
                    Toast.makeText(getApplicationContext(),
                            "用户名或密码不合法，请重新输入！",
                            Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onSysFailed() {
                    mUserView.hideLoading();
                    Toast.makeText(getApplicationContext(),
                            "你可能长得太丑，网络都看不下去了 ^_^ ",
                            Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}
