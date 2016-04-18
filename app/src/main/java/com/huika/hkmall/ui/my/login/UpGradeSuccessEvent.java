package com.huika.hkmall.ui.my.login;

import java.io.Serializable;

public class UpGradeSuccessEvent implements Serializable {

  /**
   * @description： 准惠粉升级完成发送通知
   * @author jxh
   * @date 2015-8-7 上午9:05:37
   */
  private boolean isSuccess;
  private static final long serialVersionUID = -918353726002456050L;

  public UpGradeSuccessEvent(boolean isSuccess) {
    this.isSuccess = isSuccess;
  }

  public boolean isSuccess() {
    return isSuccess;
  }
}
