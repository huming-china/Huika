package com.huika.hkmall.model.bean;

import io.realm.RealmObject;
import java.io.Serializable;

/**
 * @author wangxuhao
 * @Description:用户资料实体类
 * @date 2015年4月29日 下午3:27:42
 */
public class UserInfo extends RealmObject implements Serializable{
  static final long serialVersionUID = 1L;

  /******************* IM这边用到的字段 ********************/
  private String myID;// 通行证id(64位有符号整数)
  private String accountId;// 电友通行证ID【唯一标识】
  private String account;// 惠信号
  private String userName;// 通行证账号名
  private String nickName;// 用户昵称
  private String headerImg;// 头像图片url
  private String remarkName;// 备注
  private String sign;// 个性签名
  private int gender;// 性别
  private String header;// 快速检索的header
  private String updateTime;//
  private String regtime;// 注册时间

  public UserInfo() {
  }

  public static long getSerialVersionUID() {
    return serialVersionUID;
  }

  public String getMyID() {
    return myID;
  }

  public void setMyID(String myID) {
    this.myID = myID;
  }

  public String getAccountId() {
    return accountId;
  }

  public void setAccountId(String accountId) {
    this.accountId = accountId;
  }

  public String getAccount() {
    return account;
  }

  public void setAccount(String account) {
    this.account = account;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }

  public String getHeaderImg() {
    return headerImg;
  }

  public void setHeaderImg(String headerImg) {
    this.headerImg = headerImg;
  }

  public String getRemarkName() {
    return remarkName;
  }

  public void setRemarkName(String remarkName) {
    this.remarkName = remarkName;
  }

  public String getSign() {
    return sign;
  }

  public void setSign(String sign) {
    this.sign = sign;
  }

  public int getGender() {
    return gender;
  }

  public void setGender(int gender) {
    this.gender = gender;
  }

  public String getHeader() {
    return header;
  }

  public void setHeader(String header) {
    this.header = header;
  }

  public String getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(String updateTime) {
    this.updateTime = updateTime;
  }

  public String getRegtime() {
    return regtime;
  }

  public void setRegtime(String regtime) {
    this.regtime = regtime;
  }

  //  public String getUpdateTime() {
//    return updateTime;
//  }
//
//  public String getAccountId() {
//    return accountId.trim();
//  }
//
//  public void setAccountId(String accountId) {
//    this.accountId = accountId;
//  }
//
//  public String getRegtime() {
//    return regtime;
//  }
//
//  public void setRegtime(String regtime) {
//    this.regtime = regtime;
//  }
//
//  public void setUpdateTime(String updateTime) {
//    this.updateTime = updateTime;
//  }

  /** 获取更友好的名称显示 */
//  public String getFriendlyName() {
//    return (!TextUtils.isEmpty(getRemarkName())) ? (getRemarkName())
//        : ((!TextUtils.isEmpty(nickName)) ? (nickName) : (userName));
//  }

//  public String getGroupName(GroupMembersDB groupMembersDB) {
//    return (!TextUtils.isEmpty(getRemarkName())) ? (getRemarkName())
//        : ((!TextUtils.isEmpty(groupMembersDB.getgCard())) ? groupMembersDB.getgCard()
//            : (!TextUtils.isEmpty(nickName)) ? (nickName) : (userName));
//  }

//  public String getFirstLetter() {
//    remarkName=getRemarkName();
//    if(!TextUtils.isEmpty(remarkName)){
//      this.header= CharacterParser.getInstance().getStoreString(remarkName);
//    }
//    if (!TextUtils.isEmpty(header)) {
//      return header.substring(0, 1).toUpperCase();
//    } else {
//      return "#";
//    }
//  }

//  public String getMyID() {
//    return myID;
//  }
//
//  public void setMyID(String myID) {
//    this.myID = myID;
//  }
//
//  public long getUserId() {
//    return MiscUtil.parseLong(accountId, 0);
//  }
//
//  public void setUserId(String accountId) {
//    this.accountId = accountId;
//  }
//
//  public String getAccount() {
//    return account;
//  }
//
//  public void setAccount(String account) {
//    this.account = account;
//  }
//
//  public String getUserName() {
//    return userName;
//  }
//
//  public void setUserName(String userName) {
//    this.userName = userName;
//  }
//
//  public String getNickName() {
    /**
     * @author lzt
     *
     */
    /** 发送消息的时候需要传nickName字段，如果没有的话就得传账号 */
//    if (TextUtils.isEmpty(nickName)) {
//      return userName;
//    }
//    return nickName;
//  }

//  public void setNickName(String nickName) {
//    this.nickName = nickName;
//  }
//
//  public String getHeaderImg() {
//    return headerImg;
//  }
//
//  public void setHeaderImg(String headerImg) {
//    this.headerImg = headerImg;
//  }

//  public String getRemarkName() {
//    return HKContactDBHelper.getInstance().getSingleHCRemarkName(MiscUtil.parseLong(accountId, 0));
//  }
//  public String getRemarkName() {
//    return remarkName;
//  }
//
//  public void setRemarkName(String remarkName) {
//    this.remarkName = remarkName;
//  }
//
//  public String getSign() {
//    return sign;
//  }
//
//  public void setSign(String sign) {
//    this.sign = sign;
//  }
//
//  public int getGender() {
//    return gender;
//  }
//
//  public void setGender(int gender) {
//    this.gender = gender;
//  }
//
//  public String getHeader() {
//    return header;
//  }
//
//  public void setHeader(String header) {
//    this.header = header;
//  }

  /** 获取用户头像缩略图，缩略大小：134x134 */
//  public String getHeaderThumbImageUrl() {
//    String url = headerImg;
//    if (null != url) {
//      if (url.startsWith("http://")) {
//        url = headerImg;
//      } else {
//        url = GlobalApp.formatMemberThumbImageUrl(headerImg);
//      }
//    } else {
//      url = "";
//    }
//    return url;
//  }

//  public String getHeaderOrignalImageUrl() {
//    String url = headerImg;
//    if (null != url) {
//      if (url.startsWith("http://")) {
//        url = headerImg;
//      } else {
//        url = GlobalApp.formatMemberOriginalImageUrl(headerImg);
//      }
//    } else {
//      url = "";
//    }
//    return url;
//  }

  /**
   * @Description:获取好友头像的原始路径
   */
//  public String getHKFriendOriginalImageUrl(String relativePath) {
//    String userImagePrefile = "";
//
//    if (TextUtils.isEmpty(userImagePrefile)) {
//      userImagePrefile = ServerInIDBHelper.getInstance().getServerImagePrefix();
//    }
//    return String.format("%s%s", userImagePrefile, relativePath);
//  }

//  @Override public String toString() {
//    return "UserInfo [myID=" + myID + ", accountId=" + accountId + ", account=" + account
//        + ", userName=" + userName + ", nickName=" + nickName + ", headerImg=" + headerImg
//        + ", remarkName=" + remarkName + ", sign=" + sign + ", gender=" + gender + ", header="
//        + header + "]";
//  }

//  public String getValidaStr() {
//    return "UserInfo [myID=" + myID + ", accountId=" + accountId + ", account=" + account
//        + ", userName=" + userName + ", nickName=" + nickName + ", headerImg=" + headerImg
//        + ", remarkName=" + remarkName + ", sign=" + sign + ", gender=" + gender + ", updateTime="
//        + updateTime + ",header=" + header + "]";
//  }

  /**
   * @Description:获取134*134的好友头像缩略图
   */
//  public String getHKFriendThumbImageUrl(String relativePath) {
//    String userImagePrefile = "";
//    if (TextUtils.isEmpty(userImagePrefile)) {
//      userImagePrefile = ServerInIDBHelper.getInstance().getServerImagePrefix();
//    }
//    if (TextUtils.isEmpty(relativePath)) {
//      return "";
//    }
//    int endPoint = relativePath.lastIndexOf(".");
//    if (endPoint <= 0) {
//      return "";
//    }
//    return String.format("%s%s%s%s", userImagePrefile, relativePath.substring(0, endPoint),
//        IMsgConstant.USER_HEAD_IMAGE_THUMBNAIL_PROPORTION, relativePath.substring(endPoint));
//  }
}
