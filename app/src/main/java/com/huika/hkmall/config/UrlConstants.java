package com.huika.hkmall.config;

import com.huika.hkmall.util.PreferHelper;

/**
 * 接口地址常量
 */
public class UrlConstants {
  /**
   * ==========================================环境切换部分=================================
   */

  public static String ServerName = getServerDomain();
  /** 正式版本的环境 */
  public static final String RELEASE_SERVER_DOMAIN = Configuration.WEB_RELEASE_SERVER_DOMAIN;
  /** 准生成的环境 */
  public static final String PRE_RELEASE_SERVER_DOMAIN =Configuration.WEB_PRE_RELEASE_SERVER_DOMAIN;
  /** 预测试环境 */
  public static final String RELEASE_TEST_SERVER_DOMAIN =Configuration.WEB_RELEASE_TEST_SERVER_DOMAIN;
  /** 线上测试版本的环境（测试环境(分支)） */
  public static final String ONLINE_SERVER_DOMAIN = Configuration.ONLINE_TEST_SERVER_DOMAIN;
  /** 测试版本的环境（测试环境(主干)） */
  public static final String TEST_SERVER_DOMAIN = Configuration.WEB_TEST_SERVER_DOMAIN;

  private static final boolean isReleaseEnvironment() {
    return PreferHelper.getInstance().getBoolean(Constant.IS_RELEASE_ENVIRONMENT, true);
  }

  private static final boolean isPreReleaseEnvironment() {
    return PreferHelper.getInstance().getBoolean(Constant.IS_PRE_RELEASE_ENVIRONMENT, false);
  }

  private static final boolean isReleaseTestEnvironment() {
    return PreferHelper.getInstance().getBoolean(Constant.IS_RELEASE_TEST_ENVIRONMENT, false);
  }

  private static final boolean isWebTestEnvironment() {
    return PreferHelper.getInstance().getBoolean(Constant.IS_WEB_TEST_ENVIRONMENT, false);
  }

  private static final String getServerDomain() {
    if (isReleaseEnvironment()) {
      return RELEASE_SERVER_DOMAIN;
    } else {
      if (isPreReleaseEnvironment()) {
        return PRE_RELEASE_SERVER_DOMAIN;
      }else if (isReleaseTestEnvironment()) {
        return ONLINE_SERVER_DOMAIN;
      }  else if (isWebTestEnvironment()) {
        return ONLINE_SERVER_DOMAIN;
      } else {
        return TEST_SERVER_DOMAIN;
      }
    }
  }



  /**
   * ==========================================接口URRL部分=================================
   */
  // 惠卡商城试地址
  public static final String HOST = ServerName + "/";
  /** 7.6.	惠信服务列表 */
  public static final String GET_SERVICE_LIST_V1 = HOST + "Support/GetServiceListV1";

  /** 7.7.惠信服务广告位(9-24) */
  public static final String GET_SERVICE_BANNER = HOST + "Support/GetServiceBanner";
}
