package com.huika.hkmall.config;

import com.huika.hkmall.util.PreferHelper;

/**
 * @author wangxuhao
 * @Description:环境配置
 * @date 2015-7-17 下午4:38:56
 */
public class Configuration {
  public static boolean IS_DEBUG_ENABLE = false;// 正式环境和测试环境的切换，false:正式环境，true:测试环境
  public static boolean IS_LOG = true;// 发布时改为false,不打LOG
  public static String DEBUG_TAG = "HKMALL";

  /** webip配置*/
  public static final String WEB_RELEASE_SERVER_DOMAIN = "http://api.huikamall.com";
  /** 准生成环境*/
  public static final String WEB_PRE_RELEASE_SERVER_DOMAIN = "http://121.199.27.45:9011";
  /** 预生产环境*/
  public static final String WEB_RELEASE_TEST_SERVER_DOMAIN = "http://192.168.16.160:9001";
  /** 预生产环境(分支)*/
  public static final String ONLINE_TEST_SERVER_DOMAIN = "http://192.168.16.240:9005";
  /** 预生产环境(主干)*/
  public static final String WEB_TEST_SERVER_DOMAIN = "http://192.168.16.240:9009";


  public static final String BASE_WEB_SERVER = getWebServerDomain();

  public static final boolean isReleaseEnvironment() {
    return PreferHelper.getInstance().getBoolean(Constant.IS_RELEASE_ENVIRONMENT, true);
  }

  private static final boolean isPreReleaseTestEnvironment() {
    return PreferHelper.getInstance().getBoolean(Constant.IS_PRE_RELEASE_ENVIRONMENT, false);
  }

  private static final boolean isReleaseTestEnvironment() {
    return PreferHelper.getInstance().getBoolean(Constant.IS_RELEASE_TEST_ENVIRONMENT, false);
  }

  private static final boolean isWebTestEnvironment() {
    return PreferHelper.getInstance().getBoolean(Constant.IS_WEB_TEST_ENVIRONMENT, false);
  }

  private static final boolean isTestEnvironment() {
    return PreferHelper.getInstance().getBoolean(Constant.IS_TEST_ENVIRONMENT, false);
  }



  /**
   * @Description:获取web服务端配置路径
   */
  private static final String getWebServerDomain() {
    if (isReleaseEnvironment()) {
      return WEB_RELEASE_SERVER_DOMAIN;
    } else if (isPreReleaseTestEnvironment()) {
      return WEB_PRE_RELEASE_SERVER_DOMAIN;
    }else if (isReleaseTestEnvironment()) {
      return WEB_RELEASE_TEST_SERVER_DOMAIN;
    } else if (isWebTestEnvironment()) {
      return ONLINE_TEST_SERVER_DOMAIN;
    } else {
      return WEB_TEST_SERVER_DOMAIN;
    }
  }

  /**
   * @Description:不同环境下的配置标题显示
   */
  public static final String getEnvironmentTitle(String title) {
    if (isReleaseEnvironment()) {
      return title;
    } else {
      return title + "(测试环境)";
    }
  }


}
