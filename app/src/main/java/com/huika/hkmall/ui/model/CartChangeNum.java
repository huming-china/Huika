package com.huika.hkmall.ui.model;

import java.io.Serializable;

public class CartChangeNum implements Serializable {
  private static final long serialVersionUID = -3430223512668809125L;
  public String number;

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  @Override public String toString() {
    return "CartChangeNum [number=" + number + "]";
  }
}
