package com.huika.hkmall.ui.model;

import java.io.Serializable;

/**
 * 服务
 * Created by jixionghui on 2015/12/22.
 */
public class ServiceArrayBean implements Serializable {

  private static final long serialVersionUID = 7072821904496429317L;
  /** 服务ID */
  public String serviceId;
  /** 服务名称 */
  public String serviceName;
  /** ICON图片 */
  public String iconImg;
  /** H5链接地址 */
  public String html5Url;
  /** 活动类型 0:没有活动 1：HOT 2：NEW */
  public String activeType;
  /** android包名 */
  public String androidPackage;
  /** appType：应用类型 1:sdk(内部跳转) 2:html5 */
  public String appType;
  /** android包下载地址 */
  public String androidDownLoadUrl;
  public String iosPackage;// ios包名
  public String iosUrl;// ios下载地址
  public String isOnline;// 是否上线0否1是
  public String html5UrlEncrypt;  //H5链接地址（加密）
  public ServiceAppConfig appConfigs;  //APP配置信息

  @Override public String toString() {
    return "ServiceArrayBean{" +
        "serviceId='" + serviceId + '\'' +
        ", serviceName='" + serviceName + '\'' +
        ", iconImg='" + iconImg + '\'' +
        ", html5Url='" + html5Url + '\'' +
        ", activeType='" + activeType + '\'' +
        ", androidPackage='" + androidPackage + '\'' +
        ", appType='" + appType + '\'' +
        ", androidDownLoadUrl='" + androidDownLoadUrl + '\'' +
        ", iosPackage='" + iosPackage + '\'' +
        ", iosUrl='" + iosUrl + '\'' +
        ", isOnline='" + isOnline + '\'' +
        ", html5UrlEncrypt='" + html5UrlEncrypt + '\'' +
        ", appConfigs=" + appConfigs +
        '}';
  }
}
