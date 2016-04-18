package com.huika.hkmall.ui.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 服务列表 item
 * Created by jixionghui on 2015/12/23.
 */

public class HKServiceBean implements Serializable {
  public int typeId;  /**类型Id*/
  public String typeName; /**类型名字*/
  public int num; /**每行显示的数目*/
  public ArrayList<ServiceArrayBean> serviceArray;   /**类型中的服务列表*/

  @Override public String toString() {
    return "HKServiceBean{" +
        "typeId=" + typeId +
        ", typeName='" + typeName + '\'' +
        ", num=" + num +
        ", serviceArray=" + serviceArray +
        '}';
  }
}
