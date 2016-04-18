package com.huika.hkmall.ui.my.login;

import android.graphics.Color;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.huika.hkmall.GlobalApplication;
import com.huika.hkmall.R;
import com.huika.hkmall.model.bean.UserInfo;
import com.huika.hkmall.ui.api.HuiKaRetrofitApi;
import com.huika.hkmall.ui.model.UserModel;
import com.huika.hkmall.util.PreferHelper;
import com.huika.lib.core.db.RealmDBUtil;
import com.huika.lib.core.ext.ui.act.BaseAct;
import com.huika.lib.core.ext.utils.ToastMsg;
import com.huika.lib.core.model.bean.RequestResult;
import com.huika.lib.core.model.bean.ResultCallback;
import com.huika.lib.core.security.MD5Security;
import com.huika.lib.core.security.RsaHelper;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by zhanghao on 2015/12/17.
 */
public class LoginActivity extends BaseAct implements TextWatcher {

    private Toolbar mToolbar;
    private EditText login_edit_name;
    private EditText login_edit_pwd;
    private Button btn_login;
    private String phone;
    private String pwd;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_login);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initTitleBar();
        login_edit_name = (EditText) findViewById(R.id.login_edit_name);
        login_edit_pwd = (EditText) findViewById(R.id.login_edit_pwd);
        btn_login = (Button) findViewById(R.id.btn_login);

        btn_login.setOnClickListener(this);
    }

    private void initTitleBar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("登录");
        mToolbar.setTitleTextColor(Color.WHITE);
        //可以添加布局
//        View view = View.inflate(this, R.layout.index_title, null);
//        mToolbar.addView(view);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ssdk_back_arr);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.btn_login:
                phone = getInputStr(login_edit_name);
                pwd = getInputStr(login_edit_pwd);
                // 检查输入
                if (checkAccountInput(phone, true) && checkPWDLogin(pwd, true)) {
//                    showLoadingDialog(false);
                    closeInput();
                    String md5Pwd = MD5Security.getMd5_32_UP(pwd);
                    String rsaPwd = RsaHelper.encryptDataFromStr(md5Pwd, RsaHelper.publicKey);
                    String name = RsaHelper.encryptDataFromStr(phone, RsaHelper.publicKey);
                    login(name, rsaPwd);
                }
                break;
        }
    }

    private void login(String name, String pwd) {
        HuiKaRetrofitApi.getInstance().get().login(name, pwd, "2", new ResultCallback<RequestResult<UserModel>>() {
            @Override
            public void success(RequestResult<UserModel> result, Response response) {
                super.success(result, response);
                if (result.flag == 1) {
                    ToastMsg.showToastMsg(LoginActivity.this, result.msg);
                    // 惠粉登陆成功操作
                    UserModel userMoel = result.getRs();
                    GlobalApplication.getInstance().setUserModel(userMoel);
                    // 保存登陆用户资料到数据库
                    UserInfo info = new UserInfo();
                    info.setMyID(userMoel.userId);
                    info.setAccountId(userMoel.userId);
                    info.setAccount(userMoel.account);
                    info.setUserName(userMoel.account);
                    info.setNickName(userMoel.nickName);
                    info.setHeaderImg(userMoel.imageUrl);
//                    info.setHeader(CharacterParser.getInstance().getStoreString(info.getFriendlyName()));
                    // info.setSign(userMoel.signsture);
                    // info.setGender(userMoel.sex);
//                    UserDao.getInstance(context).saveUser(info);
                    RealmDBUtil.deleteDataByClass(UserInfo.class);
                    RealmDBUtil.insertData(info);
                    // 缓存userModeL里面的特殊字段
                    PreferHelper.getInstance().setString(PreferHelper.KEY_LOGIN_UID, userMoel.userId);
                    PreferHelper.getInstance().setString(PreferHelper.KEY_REAL_NAME, userMoel.realName);
                    PreferHelper.getInstance().setString(PreferHelper.KEY_PHONE, userMoel.phone);
                    PreferHelper.getInstance().setInt(PreferHelper.KEY_IS_DISTRIBUTION, userMoel.isDistribution);

                    finish();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                super.failure(error);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_right:
                ToastMsg.showToastMsg(LoginActivity.this, "跳转加盟惠卡");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String account = getInputStr(login_edit_name);
        String pwd = getInputStr(login_edit_pwd);
        if (!TextUtils.isEmpty(account) && !TextUtils.isEmpty(pwd)) {
            btn_login.setEnabled(true);
        } else {
            btn_login.setEnabled(false);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
