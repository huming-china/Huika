package com.huika.hkmall.hkinterface;

import com.huika.hkmall.ui.model.ServiceAdvBean;
import com.huika.hkmall.ui.model.ServiceArrayBean;

/**
 * 服务模块广告的点击事件
 * Created by jixionghui on 2015/12/31.
 */
public interface IServiceClickCallback {
  void onServiceBannerClick(ServiceAdvBean  serviceAdvBean);
  void onServiceItemClick(ServiceArrayBean serviceBean);
}
