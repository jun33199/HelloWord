package com.ttsoft.bjtax.smsb.gdzys.util;

import java.math.BigDecimal;

import com.syax.creports.util.MoneyUtils;
import com.ttsoft.bjtax.smsb.util.SBStringUtils;
import com.ttsoft.framework.exception.ApplicationException;

public class GdzysMoneyUtils {

	  /**
	   * ��һ������ҵ�������ʽת��Ϊ����ҵĴ�д��ʽ,���ת��ֵΪ�����999999.99
	   * @param money ����ҵ�������ʽ
	   * @return ����ҵĴ�д��ʽ
	   */
	  public static String convert0(double money) throws ApplicationException {
	    //�������
	    StringBuffer str=new StringBuffer();
	    int tmp=0;
	    //�Ϸ��Լ��
	    if(money>999999.99){
	      throw new ApplicationException("����ҽ������޷�����ת��");
	    }
	    //��ʼת��
	    int tar=(new Double(money*100+0.1)).intValue();
	    ///1.����ʮ��λ
	    tmp=tar/10000000;
	    if(tmp>0){
	      str.append(GdzysMoneyUtils.convert2(tmp));
	      str.append("ʰ");
	    }
	    //2.������λ
	    tar=tar-tmp*10000000;
	    tmp=tar/1000000;
	    if(tmp>0){
	      str.append(GdzysMoneyUtils.convert2(tmp));
	      str.append("��");
	    }
	    //3.����ǧλ
	    tar=tar-tmp*1000000;
	    tmp=tar/100000;
	    if(tmp>0){
	      str.append(GdzysMoneyUtils.convert2(tmp));
	      str.append("Ǫ");
	    }
	    //3.�����λ
	    tar=tar-tmp*100000;
	    tmp=tar/10000;
	    if(tmp>0){
	      str.append(GdzysMoneyUtils.convert2(tmp));
	      str.append("��");
	    }
	    //��.����ʮλ
	    tar=tar-tmp*10000;
	    tmp=tar/1000;
	    if(tmp>0){
	      str.append(GdzysMoneyUtils.convert2(tmp));
	      str.append("ʰ");
	    }
	    //��.�����λ
	    tar=tar-tmp*1000;
	    tmp=tar/100;
	    if(tmp>0){
	      str.append(GdzysMoneyUtils.convert2(tmp));
	    }
	    str.append("Ԫ");
	    //��.�����λ
	    tar=tar-tmp*100;
	    tmp=tar/10;
	    if(tmp>=0){
	      str.append(GdzysMoneyUtils.convert2(tmp));
	      str.append("��");
	    }
	    //��.�����λ
	    tar=tar-tmp*10;
	    tmp=tar;
	    if(tmp>=0){
	      str.append(GdzysMoneyUtils.convert2(tmp));
	      str.append("��");
	    }
	    //����
	    return str.toString();
	  }

	  public static String convert2(int money) throws ApplicationException{
	    //�Ϸ��Լ��
	    if(money>9){
	      throw new ApplicationException("����ҽ������޷�����ת��");
	    }
	    //��ʼת��
	    String [] names={"��","Ҽ","��","��","��","��","½","��","��","��"};
	    //����
	    return names[money];
	  }

	  /**
	   * ����Ǯ�ĸ�ʽ
	   * @param money ���
	   * @return �����Ľ��
	   */
	  public static String format(double money){
	    BigDecimal tmpBig = new BigDecimal(money);
	    tmpBig = tmpBig.setScale(2, BigDecimal.ROUND_HALF_UP);
	    return tmpBig.toString();
	  }

	  /**
	   * ����Ǯ�ĸ�ʽ
	   * @param money ���
	   * @return �����Ľ��
	   */
	  public static String format(String money){
	    if(SBStringUtils.killNull(money).equals("")){
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
	  public static String format(int money){
	    BigDecimal tmpBig = new BigDecimal(money);
	    tmpBig = tmpBig.setScale(2, BigDecimal.ROUND_HALF_UP);
	    return tmpBig.toString();
	  }

	  /**
	   * ����Ǯ�ĸ�ʽ
	   * @param money ���
	   * @return �����Ľ��
	   */
	  public static String format(long money){
	    BigDecimal tmpBig = new BigDecimal(money);
	    tmpBig = tmpBig.setScale(2, BigDecimal.ROUND_HALF_UP);
	    return tmpBig.toString();
	  }

	  /**
	   * ����Ǯ�ĸ�ʽ
	   * @param money ���
	   * @return �����Ľ��
	   */
	  public static double format2(double money){
	    BigDecimal tmpBig = new BigDecimal(money);
	    tmpBig = tmpBig.setScale(2, BigDecimal.ROUND_HALF_UP);
	    return tmpBig.doubleValue();
	  }

	  /**
	   * ����Ǯ�ĸ�ʽ
	   * @param money ���
	   * @return �����Ľ��
	   */
	  public static double format2(String money){
	    if(SBStringUtils.killNull(money).equals("")){
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
	  public static double format2(int money){
	    BigDecimal tmpBig = new BigDecimal(money);
	    tmpBig = tmpBig.setScale(2, BigDecimal.ROUND_HALF_UP);
	    return tmpBig.doubleValue();
	  }

	  /**
	   * ����Ǯ�ĸ�ʽ
	   * @param money ���
	   * @return �����Ľ��
	   */
	  public static double format2(long money){
	    BigDecimal tmpBig = new BigDecimal(money);
	    tmpBig = tmpBig.setScale(2, BigDecimal.ROUND_HALF_UP);
	    return tmpBig.doubleValue();
	  }



	  public static void main(String[] args) {
	    try{
	      String tmp="1";
	      System.out.println(MoneyUtils.format2(tmp));
	    }catch(Exception e){
	      e.printStackTrace();
	    }
	  }
	  
	  /**
	   * ����Ǯ�ĸ�ʽ
	   * @param money ���
	   * @return �����Ľ��
	   */
	  public static String format3(double money){
	    BigDecimal tmpBig = new BigDecimal(money);
	    tmpBig = tmpBig.setScale(0, BigDecimal.ROUND_HALF_UP);
	    return tmpBig.toString();
	  }
}
