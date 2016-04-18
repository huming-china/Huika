package com.huika.hkmall.ui.model;

import java.io.Serializable;

public class ClassifyBean implements Serializable {
  private static final long serialVersionUID = -3104748472726611490L;
  public int classifyId;// 二级分类ID
  public String classifyName;// 分类名称
  private String classifyImg;// 分类图片

  public int getClassifyId() {
    return classifyId;
  }

  public void setClassifyId(int classifyId) {
    this.classifyId = classifyId;
  }

  public String getClassifyName() {
    return classifyName;
  }

  public void setClassifyName(String classifyName) {
    this.classifyName = classifyName;
  }

  public String getClassifyImg() {
    return classifyImg;
  }

  public void setClassifyImg(String classifyImg) {
    this.classifyImg = classifyImg;
  }

  @Override public String toString() {
    return "TwoClassifyBean [classifyId=" + classifyId + ", classifyName=" + classifyName
        + ", classifyImg=" + classifyImg + "]";
  }
}
