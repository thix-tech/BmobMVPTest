package com.sqchen.bmobmvptest.model;

import android.widget.Toast;

import com.sqchen.bmobmvptest.bean.MyUser;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

import static cn.bmob.v3.Bmob.getApplicationContext;

/**
 * Created by Administrator on 2017/3/23.
 */

public class IUserModelImpl implements IUserModel {

    /**
     * 由model进行具体的业务逻辑操作,检查用户名和密码
     * @param name
     * @param pwd
     */
    @Override
    public void checkUser(String name, String pwd, final OnUserOperationListener listener) {
        //开始检查过程
        listener.onOperationBegin();
        BmobQuery<MyUser> userQuery = new BmobQuery<MyUser>();
        userQuery.addWhereEqualTo("userName",name);
        userQuery.addWhereEqualTo("userPwd",pwd);
        userQuery.findObjects(new FindListener<MyUser>() {
            @Override
            public void done(List<MyUser> list, BmobException e) {
                if(e == null) {
                    if(list.size() == 1) {
                        listener.onSuccess();
                    } else {
                        listener.onUserFailed();
                    }
                } else {
                    listener.onSysFailed();
                }
            }
        });
    }

    @Override
    public void registerUser( String name, String pwd, final OnUserOperationListener listener) {
        listener.onOperationBegin();
        MyUser mUser = new MyUser();
        mUser.setUserName(name);
        mUser.setUserPwd(pwd);
        mUser.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if(e == null) {
                    Toast.makeText(getApplicationContext(),
                            "注册成功！请登录",
                            Toast.LENGTH_SHORT).show();
                    listener.onSuccess();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "注册失败！" + "错误码：" + e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                    listener.onSysFailed();
                }
            }
        });
    }
}
