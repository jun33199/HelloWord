package com.ttsoft.bjtax.shenbao.util;

import java.math.BigDecimal;

import com.ttsoft.framework.exception.ApplicationException;

/**
 * <p>Title: 车船税管理</p>
 * <p>Description: 用来进行所有与钱相关的计算和转换</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: THUNIS</p>
 * @author Ha Zhengze
 * @version 1.0
 */

public class MoneyUtils {

  /**
   * 将一个人民币的数字形式转换为人民币的大写形式,最大转换值为人民币999999.99
   * @param money 人民币的数字形式
   * @return 人民币的大写形式
   */
  public static String convert0(double money) throws ApplicationException {
    //句柄申明
    StringBuffer str = new StringBuffer();
    int tmp = 0;
    //合法性检查
    if (money > 999999.99) {
      throw new ApplicationException("人民币金额过大，无法进行转换");
    }
    //开始转换
    int tar = (new Double(money * 100)).intValue();
    ///-2.计算亿位
    //tmp = tar / 10000000;
    ///-1.计算千万位

    ///0.计算百万位

    ///1.计算十万位
    tmp = tar / 10000000;
    if (tmp > 0) {
      str.append(MoneyUtils.convert2(tmp));
      str.append("拾");
    }
    //2.计算万位
    tar = tar - tmp * 10000000;
    tmp = tar / 1000000;
    if (tmp > 0) {
      str.append(MoneyUtils.convert2(tmp));
      str.append("万");
    }
    //3.计算千位
    tar = tar - tmp * 1000000;
    tmp = tar / 100000;
    if (tmp > 0) {
      str.append(MoneyUtils.convert2(tmp));
      str.append("仟");
    }
    //3.计算百位
    tar = tar - tmp * 100000;
    tmp = tar / 10000;
    if (tmp > 0) {
      str.append(MoneyUtils.convert2(tmp));
      str.append("佰");
    }
    //４.计算十位
    tar = tar - tmp * 10000;
    tmp = tar / 1000;
    if (tmp > 0) {
      str.append(MoneyUtils.convert2(tmp));
      str.append("拾");
    }
    //５.计算个位
    tar = tar - tmp * 1000;
    tmp = tar / 100;
    if (tmp > 0) {
      str.append(MoneyUtils.convert2(tmp));
    }
    str.append("元");
    //６.计算角位
    tar = tar - tmp * 100;
    tmp = tar / 10;
    if (tmp >= 0) {
      str.append(MoneyUtils.convert2(tmp));
      str.append("角");
    }
    //７.计算分位
    tar = tar - tmp * 10;
    tmp = tar;
    if (tmp >= 0) {
      str.append(MoneyUtils.convert2(tmp));
      str.append("分");
    }
    //返回
    return str.toString();
  }

  public static String convert2(int money) throws ApplicationException {
    //合法性检查
    if (money > 9) {
      throw new ApplicationException("人民币金额过大，无法进行转换");
    }
    //开始转换
    String[] names = {
        "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
    //返回
    return names[money];
  }

  /**
   * 整理钱的格式
   * @param money 金额
   * @return 整理后的金额
   */
  public static String format(double money) {
    BigDecimal tmpBig = new BigDecimal(money);
    tmpBig = tmpBig.setScale(2, BigDecimal.ROUND_HALF_UP);
    return tmpBig.toString();
  }

  /**
   * 整理钱的格式
   * @param money 金额
   * @return 整理后的金额
   */
  public static String format(String money) {
    if (StringUtils.killNull(money).equals("")) {
      return "0.00";
    }
    BigDecimal tmpBig = new BigDecimal(Double.parseDouble(money));
    tmpBig = tmpBig.setScale(2, BigDecimal.ROUND_HALF_UP);
    return tmpBig.toString();
  }

  /**
   * 整理钱的格式
   * @param money 金额
   * @return 整理后的金额
   */
  public static String format(int money) {
    BigDecimal tmpBig = new BigDecimal(money);
    tmpBig = tmpBig.setScale(2, BigDecimal.ROUND_HALF_UP);
    return tmpBig.toString();
  }

  /**
   * 整理钱的格式
   * @param money 金额
   * @return 整理后的金额
   */
  public static String format(long money) {
    BigDecimal tmpBig = new BigDecimal(money);
    tmpBig = tmpBig.setScale(2, BigDecimal.ROUND_HALF_UP);
    return tmpBig.toString();
  }

  /**
   * 整理钱的格式
   * @param money 金额
   * @return 整理后的金额
   */
  public static double format2(double money) {
    BigDecimal tmpBig = new BigDecimal(money);
    tmpBig = tmpBig.setScale(2, BigDecimal.ROUND_HALF_UP);
    return tmpBig.doubleValue();
  }

  /**
   * 整理钱的格式
   * @param money 金额
   * @return 整理后的金额
   */
  public static double format2(String money) {
    if (StringUtils.killNull(money).equals("")) {
      return 0.00;
    }
    BigDecimal tmpBig = new BigDecimal(Double.parseDouble(money));
    tmpBig = tmpBig.setScale(2, BigDecimal.ROUND_HALF_UP);
    return tmpBig.doubleValue();
  }

  /**
   * 整理钱的格式
   * @param money 金额
   * @return 整理后的金额
   */
  public static double format2(int money) {
    BigDecimal tmpBig = new BigDecimal(money);
    tmpBig = tmpBig.setScale(2, BigDecimal.ROUND_HALF_UP);
    return tmpBig.doubleValue();
  }

  /**
   * 整理钱的格式
   * @param money 金额
   * @return 整理后的金额
   */
  public static double format2(long money) {
    BigDecimal tmpBig = new BigDecimal(money);
    tmpBig = tmpBig.setScale(2, BigDecimal.ROUND_HALF_UP);
    return tmpBig.doubleValue();
  }

  public static void main(String[] args) {
    try {
      double tmp = 1;
      tmp = MoneyUtils.format2(tmp);
      System.out.println(tmp);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

}