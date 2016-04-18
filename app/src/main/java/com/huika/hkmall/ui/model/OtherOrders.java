package com.huika.hkmall.ui.model;

import java.io.Serializable;
import java.util.List;

/**
 * 我的页面其它订单接口（2015年10月13日添加）
 *
 * @author ldm
 * @description：
 * @date 2015-10-13 下午3:55:27
 */
public class OtherOrders implements Serializable {
  private static final long serialVersionUID = 1L;
  private String orderSumName;// 所有已经有的订单名称
  private List<OrderBean> otherOrders; // 订单内容

  public String getOrderSumName() {
    return orderSumName;
  }

  public void setOrderSumName(String orderSumName) {
    this.orderSumName = orderSumName;
  }

  public List<OrderBean> getOtherOrders() {
    return otherOrders;
  }

  public void setOtherOrders(List<OrderBean> otherOrders) {
    this.otherOrders = otherOrders;
  }

  @Override public String toString() {
    return "OtherOrders [orderSumName=" + orderSumName + ", otherOrders=" + otherOrders + "]";
  }
}
