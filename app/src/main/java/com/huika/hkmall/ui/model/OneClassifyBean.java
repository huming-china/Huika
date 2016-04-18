package com.huika.hkmall.ui.model;

import java.io.Serializable;
import java.util.ArrayList;

public class OneClassifyBean implements Serializable {
  private static final long serialVersionUID = -1360071323635464573L;
  public String rootName;// 根分类名称
  public String rootImg;
  public int rootId;// 一级分类ID
  public ArrayList<ClassifyBean> reclassify;

  public OneClassifyBean() {

  }

  public OneClassifyBean(String rootName) {
    this.rootName = rootName;
  }

  public String getRootName() {
    return rootName;
  }

  public void setRootName(String rootName) {
    this.rootName = rootName;
  }

  public String getRootImg() {
    return rootImg;
  }

  public void setRootImg(String rootImg) {
    this.rootImg = rootImg;
  }

  public int getRootId() {
    return rootId;
  }

  public void setRootId(int rootId) {
    this.rootId = rootId;
  }

  public ArrayList<ClassifyBean> getReclassify() {
    return reclassify;
  }

  public void setReclassify(ArrayList<ClassifyBean> reclassify) {
    this.reclassify = reclassify;
  }

  @Override public String toString() {
    return "OneClassifyBean [rootName=" + rootName + ", rootImg=" + rootImg + ", rootId=" + rootId
        + ", reclassify=" + reclassify + "]";
  }
}
