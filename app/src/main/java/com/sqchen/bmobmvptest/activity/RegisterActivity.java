package com.sqchen.bmobmvptest.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sqchen.bmobmvptest.R;
import com.sqchen.bmobmvptest.bean.MyUser;
import com.sqchen.bmobmvptest.presenter.UserPresenter;
import com.sqchen.bmobmvptest.view.IUserView;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class RegisterActivity extends BaseActivity<IUserView,UserPresenter> implements IUserView{

    private EditText editName;

    private EditText editPwd;

    private Button btnRegister;

    private UserPresenter mUserPresenter;

    //进度对话框
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    /**
     * 控件初始化
     */
    private void initView() {
        editName = (EditText) findViewById(R.id.edit_register_name);
        editPwd = (EditText) findViewById(R.id.edit_register_pwd);
        btnRegister = (Button) findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userRegister();
            }
        });

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("注册");
        progressDialog.setMessage("正在注册...");
        progressDialog.setCancelable(false);
    }

    /**
     * 用户注册，即添加一行数据
     */
    private void userRegister() {
        mUserPresenter = new UserPresenter(this);
        mUserPresenter.register(this,getUserName(),getUserPwd());
    }

    @Override
    public String getUserName() {
        return editName.getText().toString();
    }

    @Override
    public String getUserPwd() {
        return editPwd.getText().toString();
    }

    @Override
    public void showLoading() {
        progressDialog.show();
    }

    @Override
    public void hideLoading() {
        progressDialog.hide();
    }

    @Override
    protected UserPresenter createPresenter() {
        return new UserPresenter(this);
    }
}
