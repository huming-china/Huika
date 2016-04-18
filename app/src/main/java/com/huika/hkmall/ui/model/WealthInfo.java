package com.huika.hkmall.ui.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

/**
 * @author tsp
 * @Description:
 * @date 2015年6月5日 上午9:19:44
 * 财富主页面
 */
public class WealthInfo implements Serializable {
  private static final long serialVersionUID = 9125294979046408507L;
  private String balance; // 账户余额
  private String totleIncome; // 惠粉总收益
  private String joinIncome; // 加盟收益
  private String sellIncome; // 销售收益
  private String revenueIncome; // 营收收益
  private ArrayList<Map<String, String>> remark = new ArrayList<Map<String, String>>();
  private String rechargeLimit; // 可充值金额（保留，暂时不用）
  private String withdrawLimit; // 可提现金额（保留，暂时不用）
  private String withdrawMaxAmount; // 最大提现金额
  private String withdrawMinAmount; // 最小提现金额
  private String repaymentCredit;// 剩余还款额度为
  private String couponCount;//现金券的数量
  private long failDate;// 申请失败时间
  private int repaymentType;// 还款方式分类 1:用惠信的后续收益进行还款, 2:按月分期还款,等额本息
  private int byStagesType;
  // 分期还款类型 1:分三个月还完本息 2:分六个月还完本息 3:分九个月还完本息 4:分十二个月还完本息 5:分十五个月还完本息 6:分十八个月还完本息 7:分二十四个月还完本息
  private long serviced;// 服务器时间
  private long applyDate;// 申请
  private String repaymentAmount;// 当前借款金额
  private String repaymentDate;// 还款时间
  private int isSmall;// 是否支持小额免密支付 0:不支持1:支持
  private String smallCredit;// 小额免密支付额度
  public String consumeSubsidy;//员工福利

  private ArrayList<Map<String, String>> rechargeTip = new ArrayList<Map<String, String>>();
  // 充值界面提示语 map通过content
  private ArrayList<Map<String, String>> withdrawTip = new ArrayList<Map<String, String>>();
  // 提现界面提示语

  // 接口文档未定义 自测
  private String creditLimit; // 惠粉授信额度（新增）
  private String surplusCredit; // 剩余授信额度（新增）
  private int creditStatus; // 授信额度状态（0 失败 1 审核中 2 还款中 3还款完成）
  private String failReason; // 失败原因
  private String deductMoney;// 已扣除收益金额

  public String getConsumeSubsidy() {
    return consumeSubsidy;
  }

  public void setConsumeSubsidy(String consumeSubsidy) {
    this.consumeSubsidy = consumeSubsidy;
  }

  public String getDeductMoney() {
    return deductMoney;
  }

  public void setDeductMoney(String deductMoney) {
    this.deductMoney = deductMoney;
  }

  public long getApplyDate() {
    return applyDate;
  }

  public void setApplyDate(long applyDate) {
    this.applyDate = applyDate;
  }

  public String getCouponCount() {
    return couponCount;
  }

  public void setCouponCount(String couponCount) {
    this.couponCount = couponCount;
  }

  public String getRepaymentAmount() {
    return repaymentAmount;
  }

  public void setRepaymentAmount(String repaymentAmount) {
    repaymentAmount = repaymentAmount;
  }

  public String getRepaymentDate() {
    return repaymentDate;
  }

  public void setRepaymentDate(String repaymentDate) {
    repaymentDate = repaymentDate;
  }

  public String getRepaymentCredit() {
    return repaymentCredit;
  }

  public int getRepaymentType() {
    return repaymentType;
  }

  public void setRepaymentType(int repaymentType) {
    this.repaymentType = repaymentType;
  }

  public int getByStagesType() {
    return byStagesType;
  }

  public void setByStagesType(int byStagesType) {
    this.byStagesType = byStagesType;
  }

  public long getServiced() {
    return serviced;
  }

  public void setServiced(long serviced) {
    this.serviced = serviced;
  }

  public void setRepaymentCredit(String repaymentCredit) {
    this.repaymentCredit = repaymentCredit;
  }

  public long getFailDate() {
    return failDate;
  }

  public void setFailDate(long failDate) {
    this.failDate = failDate;
  }

  public ArrayList<Map<String, String>> getRemark() {
    return remark;
  }

  public void setRemark(ArrayList<Map<String, String>> remark) {
    this.remark = remark;
  }

  public ArrayList<Map<String, String>> getRechargeTip() {
    return rechargeTip;
  }

  public void setRechargeTip(ArrayList<Map<String, String>> rechargeTip) {
    this.rechargeTip = rechargeTip;
  }

  public ArrayList<Map<String, String>> getWithdrawTip() {
    return withdrawTip;
  }

  public void setWithdrawTip(ArrayList<Map<String, String>> withdrawTip) {
    this.withdrawTip = withdrawTip;
  }

  public String getBalance() {
    return balance;
  }

  public void setBalance(String balance) {
    this.balance = balance;
  }

  public String getTotleIncome() {
    return totleIncome;
  }

  public void setTotleIncome(String totleIncome) {
    this.totleIncome = totleIncome;
  }

  public String getJoinIncome() {
    return joinIncome;
  }

  public void setJoinIncome(String joinIncome) {
    this.joinIncome = joinIncome;
  }

  public String getSellIncome() {
    return sellIncome;
  }

  public void setSellIncome(String sellIncome) {
    this.sellIncome = sellIncome;
  }

  public String getRechargeLimit() {
    return rechargeLimit;
  }

  public void setRechargeLimit(String rechargeLimit) {
    this.rechargeLimit = rechargeLimit;
  }

  public String getWithdrawLimit() {
    return withdrawLimit;
  }

  public void setWithdrawLimit(String withdrawLimit) {
    this.withdrawLimit = withdrawLimit;
  }

  public String getRevenueIncome() {
    return revenueIncome;
  }

  public void setRevenueIncome(String revenueIncome) {
    this.revenueIncome = revenueIncome;
  }

  public String getCreditLimit() {
    return creditLimit;
  }

  public void setCreditLimit(String creditLimit) {
    this.creditLimit = creditLimit;
  }

  public String getSurplusCredit() {
    return surplusCredit;
  }

  public void setSurplusCredit(String surplusCredit) {
    this.surplusCredit = surplusCredit;
  }

  public int getCreditStatus() {
    return creditStatus;
  }

  public void setCreditStatus(int creditStatus) {
    this.creditStatus = creditStatus;
  }

  public String getFailReason() {
    return failReason;
  }

  public void setFailReason(String failReason) {
    this.failReason = failReason;
  }

  public String getWithdrawMaxAmount() {
    return withdrawMaxAmount;
  }

  public void setWithdrawMaxAmount(String withdrawMaxAmount) {
    this.withdrawMaxAmount = withdrawMaxAmount;
  }

  public String getWithdrawMinAmount() {
    return withdrawMinAmount;
  }

  public void setWithdrawMinAmount(String withdrawMinAmount) {
    this.withdrawMinAmount = withdrawMinAmount;
  }

  public int getIsSmall() {
    return isSmall;
  }

  public void setIsSmall(int isSmall) {
    this.isSmall = isSmall;
  }

  public String getSmallCredit() {
    return smallCredit;
  }

  public void setSmallCredit(String smallCredit) {
    this.smallCredit = smallCredit;
  }

  @Override public String toString() {
    return "WealthInfo [balance=" + balance + ", totleIncome=" + totleIncome + ", joinIncome="
        + joinIncome + ", sellIncome=" + sellIncome + ", revenueIncome=" + revenueIncome
        + ", remark=" + remark + ", rechargeLimit=" + rechargeLimit + ", withdrawLimit="
        + withdrawLimit + ", withdrawMaxAmount=" + withdrawMaxAmount + ", withdrawMinAmount="
        + withdrawMinAmount + ", repaymentCredit=" + repaymentCredit + ", failDate=" + failDate
        + ", repaymentType=" + repaymentType + ", byStagesType=" + byStagesType + ", serviced="
        + serviced + ", applyDate=" + applyDate + ", repaymentAmount=" + repaymentAmount
        + ", repaymentDate=" + repaymentDate + ", isSmall=" + isSmall + ", smallCredit="
        + smallCredit + ", rechargeTip=" + rechargeTip + ", withdrawTip=" + withdrawTip
        + ", creditLimit=" + creditLimit + ", surplusCredit=" + surplusCredit + ", creditStatus="
        + creditStatus + ", failReason=" + failReason + ", deductMoney=" + deductMoney + "]";
  }
}
