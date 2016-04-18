package com.huika.hkmall.ui.model;

import java.io.Serializable;

/**
 *
 * 服务后台Config 配置信息
 * Created by jixionghui on 2015/12/22.
 */
public class ServiceAppConfig implements Serializable {

  public String iOSShema;    /* h5对应的AppSchema (IOS的字段)*/
  public int isJumpToAppIfExsit;/* App存在的情况下是否跳转到App */
  public int isShowOnAndroid; /* 是否在安卓平台显示,需要app端过滤数据源 */
  public int isShowOnIOS;     /* 是否在iOS平台显示,需要app端过滤数据源 */
  public int isHidenNavbar;   /* 是否隐藏导航栏 */
  public int isShowLoading;  /* 是否需要显示加载标志 */
  public String statusBarColor; /* 状态栏颜色 0xffffff*/
  public String urlCloseIdentifier;  /* 关闭链接标志 /Close/Close*/
  public String urlShareIdentifier;  /* 分享链接标识 Share/xxx */
  public int urlShareType;  /* 在识别到分享的链接之后，还需要判断这是哪个h5页面的分享，如 1=脱贫宝分享 2=恵粉联盟分享...  */
  public String androidPackage;   /*android 包名*/
  public String androidShowVersion ; /*小于这个版本不显示*/
  public int openType;

  @Override public String toString() {
    return "ServiceAppConfig{" +
        "iOSShema='" + iOSShema + '\'' +
        ", isJumpToAppIfExsit=" + isJumpToAppIfExsit +
        ", isShowOnAndroid=" + isShowOnAndroid +
        ", isShowOnIOS=" + isShowOnIOS +
        ", isHidenNavbar=" + isHidenNavbar +
        ", isShowLoading=" + isShowLoading +
        ", statusBarColor='" + statusBarColor + '\'' +
        ", urlCloseIdentifier='" + urlCloseIdentifier + '\'' +
        ", urlShareIdentifier='" + urlShareIdentifier + '\'' +
        ", urlShareType=" + urlShareType +
        ", androidPackage='" + androidPackage + '\'' +
        ", androidShowVersion='" + androidShowVersion + '\'' +
        ", openType=" + openType +
        '}';
  }
}
