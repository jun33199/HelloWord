package com.ttsoft.bjtax.shenbao.util;

import java.math.BigDecimal;

import com.ttsoft.framework.exception.ApplicationException;

/**
 * <p>Title: ����˰����</p>
 * <p>Description: ��������������Ǯ��صļ����ת��</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: THUNIS</p>
 * @author Ha Zhengze
 * @version 1.0
 */

public class MoneyUtils {

  /**
   * ��һ������ҵ�������ʽת��Ϊ����ҵĴ�д��ʽ,���ת��ֵΪ�����999999.99
   * @param money ����ҵ�������ʽ
   * @return ����ҵĴ�д��ʽ
   */
  public static String convert0(double money) throws ApplicationException {
    //�������
    StringBuffer str = new StringBuffer();
    int tmp = 0;
    //�Ϸ��Լ��
    if (money > 999999.99) {
      throw new ApplicationException("����ҽ������޷�����ת��");
    }
    //��ʼת��
    int tar = (new Double(money * 100)).intValue();
    ///-2.������λ
    //tmp = tar / 10000000;
    ///-1.����ǧ��λ

    ///0.�������λ

    ///1.����ʮ��λ
    tmp = tar / 10000000;
    if (tmp > 0) {
      str.append(MoneyUtils.convert2(tmp));
      str.append("ʰ");
    }
    //2.������λ
    tar = tar - tmp * 10000000;
    tmp = tar / 1000000;
    if (tmp > 0) {
      str.append(MoneyUtils.convert2(tmp));
      str.append("��");
    }
    //3.����ǧλ
    tar = tar - tmp * 1000000;
    tmp = tar / 100000;
    if (tmp > 0) {
      str.append(MoneyUtils.convert2(tmp));
      str.append("Ǫ");
    }
    //3.�����λ
    tar = tar - tmp * 100000;
    tmp = tar / 10000;
    if (tmp > 0) {
      str.append(MoneyUtils.convert2(tmp));
      str.append("��");
    }
    //��.����ʮλ
    tar = tar - tmp * 10000;
    tmp = tar / 1000;
    if (tmp > 0) {
      str.append(MoneyUtils.convert2(tmp));
      str.append("ʰ");
    }
    //��.�����λ
    tar = tar - tmp * 1000;
    tmp = tar / 100;
    if (tmp > 0) {
      str.append(MoneyUtils.convert2(tmp));
    }
    str.append("Ԫ");
    //��.�����λ
    tar = tar - tmp * 100;
    tmp = tar / 10;
    if (tmp >= 0) {
      str.append(MoneyUtils.convert2(tmp));
      str.append("��");
    }
    //��.�����λ
    tar = tar - tmp * 10;
    tmp = tar;
    if (tmp >= 0) {
      str.append(MoneyUtils.convert2(tmp));
      str.append("��");
    }
    //����
    return str.toString();
  }

  public static String convert2(int money) throws ApplicationException {
    //�Ϸ��Լ��
    if (money > 9) {
      throw new ApplicationException("����ҽ������޷�����ת��");
    }
    //��ʼת��
    String[] names = {
        "��", "Ҽ", "��", "��", "��", "��", "½", "��", "��", "��"};
    //����
    return names[money];
  }

  /**
   * ����Ǯ�ĸ�ʽ
   * @param money ���
   * @return �����Ľ��
   */
  public static String format(double money) {
    BigDecimal tmpBig = new BigDecimal(money);
    tmpBig = tmpBig.setScale(2, BigDecimal.ROUND_HALF_UP);
    return tmpBig.toString();
  }

  /**
   * ����Ǯ�ĸ�ʽ
   * @param money ���
   * @return �����Ľ��
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
   * ����Ǯ�ĸ�ʽ
   * @param money ���
   * @return �����Ľ��
   */
  public static String format(int money) {
    BigDecimal tmpBig = new BigDecimal(money);
    tmpBig = tmpBig.setScale(2, BigDecimal.ROUND_HALF_UP);
    return tmpBig.toString();
  }

  /**
   * ����Ǯ�ĸ�ʽ
   * @param money ���
   * @return �����Ľ��
   */
  public static String format(long money) {
    BigDecimal tmpBig = new BigDecimal(money);
    tmpBig = tmpBig.setScale(2, BigDecimal.ROUND_HALF_UP);
    return tmpBig.toString();
  }

  /**
   * ����Ǯ�ĸ�ʽ
   * @param money ���
   * @return �����Ľ��
   */
  public static double format2(double money) {
    BigDecimal tmpBig = new BigDecimal(money);
    tmpBig = tmpBig.setScale(2, BigDecimal.ROUND_HALF_UP);
    return tmpBig.doubleValue();
  }

  /**
   * ����Ǯ�ĸ�ʽ
   * @param money ���
   * @return �����Ľ��
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
   * ����Ǯ�ĸ�ʽ
   * @param money ���
   * @return �����Ľ��
   */
  public static double format2(int money) {
    BigDecimal tmpBig = new BigDecimal(money);
    tmpBig = tmpBig.setScale(2, BigDecimal.ROUND_HALF_UP);
    return tmpBig.doubleValue();
  }

  /**
   * ����Ǯ�ĸ�ʽ
   * @param money ���
   * @return �����Ľ��
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