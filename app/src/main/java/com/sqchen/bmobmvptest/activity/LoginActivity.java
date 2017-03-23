package com.sqchen.bmobmvptest.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sqchen.bmobmvptest.R;
import com.sqchen.bmobmvptest.presenter.UserPresenter;
import com.sqchen.bmobmvptest.view.IUserView;

import cn.bmob.v3.Bmob;

public class LoginActivity extends BaseActivity<IUserView,UserPresenter> implements View.OnClickListener,IUserView{

    private EditText editName;

    private EditText editPwd;

    private Button btnLogin;

    private Button btnToRegister;

    //进度对话框
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化BmobSDK,
        Bmob.initialize(this, "4fd01c1c4eaca3d97c85e36494554549");
        setContentView(R.layout.activity_login);
        initView();
    }

    /**
     * 控件初始化
     */
    private void initView() {
        editName = (EditText) findViewById(R.id.edit_login_name);
        editPwd = (EditText) findViewById(R.id.edit_login_pwd);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnToRegister = (Button) findViewById(R.id.btn_to_register);
        btnLogin.setOnClickListener(this);
        btnToRegister.setOnClickListener(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("登陆");
        progressDialog.setMessage("正在登陆...");
        progressDialog.setCancelable(false);
    }

    /**
     * 重写按钮的点击事件
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                userLogin();
                break;
            case R.id.btn_to_register:
                toRegister();
                break;
            default:
                break;
        }
    }

    /**
     * 去注册
     */
    private void toRegister() {
        Intent intentReg = new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(intentReg);
    }

    /**
     * 登陆
     */
    private void userLogin() {
        //初始化中间者
        mPresenter = new UserPresenter(this);
        //通过中间者进行用户名和密码的检查
        mPresenter.check(this,getUserName(),getUserPwd());
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
