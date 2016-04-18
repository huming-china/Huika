package com.huika.hkmall.util;

import android.content.SharedPreferences;
import com.huika.hkmall.GlobalApplication;


/**
 * @author jixionghui
 */
public class PreferHelper {

  public static final String NAME = "pmall_preference";
  private static SharedPreferences sp;
  private static SharedPreferences.Editor editor;
  private static PreferHelper mInstance;

  /** 登录 */
  public static final String KEY_LOGIN_PHONE = "key_login_phone";
  public static final String KEY_LOGIN_PWD = "key_login_pwd";
  public static final String KEY_LOGIN_UID = "key_login_uid";

  public static final String KEY_REAL_NAME = "key_real_name";// 真实姓名
  public static final String KEY_PHONE = "key_phone";// 手机号
  public static final String KEY_IS_DISTRIBUTION = "key_is_distribution";// 分销商字段标示
  private PreferHelper() {
    sp = GlobalApplication.getInstance().getSharedPreferences(NAME, 0);
    editor = sp.edit();
  }

  // 增加了双重判断
  public static PreferHelper getInstance() {
    if (null == mInstance) {
      synchronized (PreferHelper.class) {
        if (null == mInstance) mInstance = new PreferHelper();
      }
    }
    return mInstance;
  }

  /**
   * 储存值
   */
  public void setString(String key, String value) {
    editor.putString(key, value);
    editor.commit();
  }

  public void setInt(String key, int value) {
    editor.putInt(key, value);
    editor.commit();
  }

  public void setLong(String key, long value) {
    editor.putLong(key, value);
    editor.commit();
  }

  public void setBoolean(String key, boolean value) {
    editor.putBoolean(key, value);
    editor.commit();
  }

  /**
   * 获取值
   */
  public String getString(String key) {
    return sp.getString(key, "");
  }

  public int getInt(String key) {
    return sp.getInt(key, -1);
  }

  public long getLong(String key) {
    return sp.getLong(key, 1);
  }

  public boolean getBoolean(String key, boolean defaultValue) {
    return sp.getBoolean(key, defaultValue);
  }

  /**
   * @description：移除特定的
   * @date 2014年11月5日 下午4:30:08
   */
  public void remove(String name) {
    editor.remove(name);
    editor.commit();
  }


  /**
   * 保存登录相关信息
   *
   * @author FAN 创建于Dec 1, 2014
   */
  public void saveLoginInfo(String phone, String pwd) {
    editor.putString(KEY_LOGIN_PHONE, phone);
    editor.putString(KEY_LOGIN_PWD, pwd);
    editor.commit();
  }

  /**
   * @Description:清除相关登陆标示
   */
  public static void clearLoginState() {
    editor.putString(KEY_LOGIN_PWD, "");
    // 注销登陆不清空账号
    // editor.putString(KEY_LOGIN_PHONE, "");
    editor.commit();
  }
}
