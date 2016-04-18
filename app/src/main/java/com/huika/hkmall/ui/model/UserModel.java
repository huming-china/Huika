package com.huika.hkmall.ui.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 用户数据模型
 */
public class UserModel implements Serializable, Parcelable{
  /** 1未实名认证 */
  public static final int SAFE_STATE_NAME = 1;
  /** 2未绑定手机 */
  public static final int SAFE_STATE_MOBILE = 2;
  /** 3未设置交易密码 */
  public static final int SAFE_STATE_PWD = 3;
  /** 4未绑定邮箱 */
  public static final int SAFE_STATE_EMAIL = 4;

  /** 账号(规则:大写字母U+手机号码) */
  public String account;
  /** 用户ID */
  public String userId;
  /** 性别 1男  2女 */
  public int sex;
  public String age;
  /** 昵称 */
  public String nickName;
  /** 手机号 */
  public String phone;
  /** 头像图片地址 */
  public String imageUrl;
  // /**个人账户余额*/
  // public double balance;
  /** 待付款的订单数 */
  public int waitPayCount;
  /** 待收货的订单数 */
  public int waitReceiveCount;
  /** 待评价的订单数 */
  public int waitComment;
  /** 真实姓名 */
  public String realName;
  /** 账户安全状态 1未实名认证 2未绑定手机 3未设置交易密码 4未绑定邮箱 */
  public int safeStatus;
  public String safeMessage;
  /** 是否实名认证0:未完善， 1:审核中，2:已认证，3:审核失败 */
  public int realNameAuthentication;
  /***/
  public int transPassword;// 是否设置交易密码(返回0或1)0：未设置交易密码 1：已设置交易密码(返回0或1)
  /***/
  public String idCard;// 身份证号
  /***/
  public String email;// 邮箱
  public String address;
  /** shicm 分销商标示 0不是1是 **/
  public int isDistribution;
  /** 个人账户余额 */
  public double balance;
  /** 是否是新用户注册(是1不是0) */
  public int isNewUser;
  /** 惠粉类型 1惠粉 2准惠粉 */
  public int hfType;
  /** 是否支持小额支付 0:不支持(默认) 1:支持 */
  public int isSmall;
  public float smallCredit;
  /** 推荐人 */
  public String referrer;
  /** 推荐人手机号码 */
  public String referrerPhone;
  /** 推荐人Id */
  public String referrerId;
  public String identityCardFrontImg;// 身份证件照（正面）
  public String identityCardContraryImg;// 身份证件照（反面）
  public ArrayList<ContentBean> failureRemark;// 实名验证失败提示语(当是否实名认证状态为3时才会返回)

  public String signsture;
  public float consumebalance;
  public long registerDt;
  public int shoppingCount;
  public int isJoinMerchant;// 是否加盟本地商家 0：没有加 1：已加入
  public int trainSwitch;// 火车票功能开关（0表示关闭维护，1表示开启）

  /** 惠粉升级信息 */
  public HfUpdateBean hfUpdate;
  // 动态图片数组（最多9条）
//  public ArrayList<ImageDynamicBean> dynamicImages;
  public int isSnsFriend;// 是否是 三级分销商或者好友 1 ：表示 是三级分销或是好友
  /**是否可以购买优惠产品(0:不可购买 1:可以购买)*/
  public int isFavorable;

  @Override public String toString() {
    return "UserModel{" +
        "account='" + account + '\'' +
        ", userId='" + userId + '\'' +
        ", sex=" + sex +
        ", age='" + age + '\'' +
        ", nickName='" + nickName + '\'' +
        ", phone='" + phone + '\'' +
        ", imageUrl='" + imageUrl + '\'' +
        ", waitPayCount=" + waitPayCount +
        ", waitReceiveCount=" + waitReceiveCount +
        ", waitComment=" + waitComment +
        ", realName='" + realName + '\'' +
        ", safeStatus=" + safeStatus +
        ", safeMessage='" + safeMessage + '\'' +
        ", realNameAuthentication=" + realNameAuthentication +
        ", transPassword=" + transPassword +
        ", idCard='" + idCard + '\'' +
        ", email='" + email + '\'' +
        ", address='" + address + '\'' +
        ", isDistribution=" + isDistribution +
        ", balance=" + balance +
        ", isNewUser=" + isNewUser +
        ", hfType=" + hfType +
        ", isSmall=" + isSmall +
        ", smallCredit=" + smallCredit +
        ", referrer='" + referrer + '\'' +
        ", referrerPhone='" + referrerPhone + '\'' +
        ", referrerId='" + referrerId + '\'' +
        ", identityCardFrontImg='" + identityCardFrontImg + '\'' +
        ", identityCardContraryImg='" + identityCardContraryImg + '\'' +
        ", failureRemark=" + failureRemark +
        ", signsture='" + signsture + '\'' +
        ", consumebalance=" + consumebalance +
        ", registerDt=" + registerDt +
        ", shoppingCount=" + shoppingCount +
        ", isJoinMerchant=" + isJoinMerchant +
        ", trainSwitch=" + trainSwitch +
        ", hfUpdate=" + hfUpdate +
//        ", dynamicImages=" + dynamicImages +
        ", isSnsFriend=" + isSnsFriend +
        ", isFavorable=" + isFavorable +
        '}';
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(imageUrl);
  }

  public static final Creator<UserModel> CREATOR = new Creator<UserModel>() {
    @Override public UserModel createFromParcel(Parcel source) {
      UserModel usermodel = new UserModel();
      usermodel.imageUrl = source.readString();
      return usermodel;
    }

    @Override public UserModel[] newArray(int size) {
      return new UserModel[size];
    }
  };
//    @Override public String getCommonBigImg() {
//        return imageUrl;
////    }
//
//    @Override public void setCommonBigImg(String imgUrl) {
//        imageUrl = imgUrl;
//    }

//  public ArrayList<ImageDynamicBean> getDynamicImages() {
//    return dynamicImages;
//  }
//
//  public void setDynamicImages(ArrayList<ImageDynamicBean> dynamicImages) {
//    this.dynamicImages = dynamicImages;
//  }
}
