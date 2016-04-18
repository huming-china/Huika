package com.huika.hkmall.ui.model;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;

public class MoneyTools {
  private static int maxFractionReserve = 2;
  private static int maxFractionThree = 3;
  private Pattern pattern =
      Pattern.compile("([\\d]*[.]?[\\d]{0," + Math.max(0, maxFractionReserve) + "}).*");

  public static String movePointRight(double value, int n, int scale) {
    BigDecimal bd = new BigDecimal(value + "");
    bd = bd.setScale(n + scale, BigDecimal.ROUND_DOWN);
    bd = bd.movePointRight(n);
    return bd.toString();
  }

  public static String roundDecimal(String value, int scale) {
    BigDecimal bd = new BigDecimal(value + "");
    bd = bd.setScale(scale, BigDecimal.ROUND_DOWN);
    if (bd.doubleValue() > 0) {
      return "+" + bd.toString();
    } else {
      return bd.toString();
    }
  }

  /**
   * 方法概述：
   *
   * @param @param temp
   * @return String
   * @throws
   * @description 方法详细描述：保留两位有效数字的方法
   * @author lzt
   * @Title: MoneyTools.java
   * @Package com.huika.huixin.utils
   * @date 2014-4-29 下午5:27:56
   */
  public static String coreMatchCutMoney(String temp) {

    try {
      String result = coreMatchCut(temp);
      result = amendFraction(result); // 可选
      return result;
    } catch (NumberFormatException ignored) {
      return "0.00";
    } catch (NullPointerException ignored) {
      return "0.00";
    } catch (NoSuchElementException ignored) {
      return "0.00";
    }
  }

  /** 不要小数 */
  public static String getCreditFormat(String creditLimit) {
    String format = "";
    if (creditLimit.indexOf(".") > 0) {
      format = creditLimit.substring(0, creditLimit.indexOf("."));
    } else {
      format = creditLimit;
    }
    return format;
  }

  /**
   * 核心函数，取数字
   *
   * @param src - 需要截取的原始数字转换成的字符串
   * @throws NumberFormatException 输入格式无法识别
   * @throws NullPointerException 空字符串
   * @throws NoSuchElementException 无法识别的数字
   */
  private static String coreMatchCut(String src)
      throws NumberFormatException, NullPointerException, NoSuchElementException {
    Double.valueOf(src);
    Pattern pattern =
        Pattern.compile("([\\d]*[.]?[\\d]{0," + Math.max(0, maxFractionReserve) + "}).*");
    Matcher matcher = pattern.matcher(src);
    if (matcher.find()) return matcher.group(1);

    throw new NoSuchElementException("unrecognized string '" + src + "'");
  }

  /**
   * 可选：如果小数不足，则补齐
   *
   * @param src - 原始数字转化的字符串
   */
  public static String amendFraction(String src) {
    String ret = src;
    ret = amendDot(src);

    if (maxFractionReserve > 0) {
      int lenFraction = ret.substring(ret.indexOf(".")).length();
      if (lenFraction < maxFractionReserve + 1) {
        for (int i = lenFraction; i < maxFractionReserve + 1; i++)
          ret += "0";
      }
    }
    return ret;
  }

  /**
   * 方法概述：
   *
   * @param @param temp
   * @return String
   * @throws
   * @description 方法详细描述：保留三位有效数字的方法
   * @author lzt
   * @Title: MoneyTools.java
   * @Package com.huika.huixin.utils
   * @date 2014-4-29 下午5:27:56
   */
  public static String coreMatchThreeCutMoney(String temp) {

    try {
      String result = coreThreeMatchCut(temp);
      result = amendThreeFraction(result); // 可选
      return result;
    } catch (NumberFormatException ignored) {
      return "0.000";
    } catch (NullPointerException ignored) {
      return "0.000";
    } catch (NoSuchElementException ignored) {
      return "0.000";
    }
  }

  /**
   * @description：
   * @author tsp
   * @date 2014年10月15日 下午5:31:49
   * 3位小数
   */
  private static String coreThreeMatchCut(String src)
      throws NumberFormatException, NullPointerException, NoSuchElementException {
    Double.valueOf(src);
    Pattern pattern =
        Pattern.compile("([\\d]*[.]?[\\d]{0," + Math.max(0, maxFractionThree) + "}).*");
    Matcher matcher = pattern.matcher(src);
    if (matcher.find()) return matcher.group(1);

    throw new NoSuchElementException("unrecognized string '" + src + "'");
  }

  /**
   * 可选：如果小数不足，则补齐
   *
   * @param src - 原始数字转化的字符串
   */
  public static String amendThreeFraction(String src) {
    String ret = src;
    ret = amendDot(src);

    if (maxFractionThree > 0) {
      int lenFraction = ret.substring(ret.indexOf(".")).length();
      if (lenFraction < maxFractionThree + 1) {
        for (int i = lenFraction; i < maxFractionThree + 1; i++)
          ret += "0";
      }
    }
    return ret;
  }

  /**
   * 可选：整数位填0(如果没有)
   *
   * @param src - 原始数字转化的字符串
   */
  public static String amendInteger(String src) {
    String ret = src;
    ret = amendDot(ret);
    int posDot = ret.indexOf(".");
    if (posDot == 0 || ret.length() == 0) ret = "0" + ret;
    return ret;
  }

  /** 这个不应该由外部调用 */
  private static String amendDot(String src) {
    String ret = src;
    int posDot = ret.indexOf(".");
    if (maxFractionReserve < 1 && posDot >= 0) {
      ret = ret.substring(0, ret.indexOf("."));
    } else if (maxFractionReserve > 0 && posDot < 0) {
      ret += ".";
    }
    return ret;
  }

  /**
   * @description：将小数转化为百分数
   * @author shicm
   * @date 2014年12月4日 上午11:05:57
   */
  public static String getPercentFormat(double d) {
    NumberFormat nf = NumberFormat.getPercentInstance();
    String str = nf.format(d);
    return str;
  }

  /**
   * 判断提现当中出现的五种情况
   */
  //	public static Spanned getWithdrawal(TotalBillDate consumeInfoData,int type) {
  //		if (!TextUtils.isEmpty(consumeInfoData.getAddOrCutResult())
  //				&& Integer.valueOf(consumeInfoData.getAddOrCutResult()) == 1) {
  //			if(type==2){
  //				return Html.fromHtml("<font color=#999999>" + "已审核，待打款"+ "</font>");
  //			}else{
  //				return Html.fromHtml("<font color=#999999>" + "已充值"+ "</font>");
  //			}
  //		} else if (!TextUtils.isEmpty(consumeInfoData.getAddOrCutResult())
  //				&& Integer.valueOf(consumeInfoData.getAddOrCutResult()) == 2) {
  //			if(type==2){
  //				return Html.fromHtml("<font color=#999999>" + "已打款"+ "</font>");
  //			}else{
  //				return Html.fromHtml("<font color=#999999>" + "充值中"+ "</font>");
  //			}
  //		} else if (!TextUtils.isEmpty(consumeInfoData.getAddOrCutResult())
  //				&& Integer.valueOf(consumeInfoData.getAddOrCutResult()) == 3) {
  //			if(type==2){
  //				return Html.fromHtml("<font color=#999999>" + "待审核"+ "</font>");
  //			}else{
  //				return Html.fromHtml("<font color=#ff6600>" + "充值失败(已退款)"+ "</font>");
  //			}
  //
  //		} else if (!TextUtils.isEmpty(consumeInfoData.getAddOrCutResult())
  //				&& Integer.valueOf(consumeInfoData.getAddOrCutResult()) == 4) {
  //			if(type==2){
  //				return Html.fromHtml("<font color=#ff6600>" + "审核不通过"+ "</font>");
  //			}else{
  //				return Html.fromHtml("<font color=#ff6600>" + "充值失败(未退款)"+ "</font>");
  //			}
  //		} else {
  //			if(type==2){
  //				return Html.fromHtml("<font color=#ff6600>" + "打款失败"+ "</font>");
  //			}else{
  //				return Html.fromHtml("<font color=#ff6600>" + "未付款"+ "</font>");
  //			}
  //		}
  //	}
}
