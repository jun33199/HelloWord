package com.ttsoft.bjtax.smsb.zjyjmsb.web;

import com.ttsoft.framework.exception.SystemException;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����ģ��</p>
 * <p>Description:�پ�ҵ�����걨ҵ����</p>
 * <p>Company: ��һ����</p>
 * @author qinwei
 * @version 1.0 2006-5-18
 */

public class ZjyjmsbConstant {
 
	//�پ�ҵ����(һ)
	public final static String ZJYJMSBB1 = "0";
	//�پ�ҵ����(��)
	public final static String ZJYJMSBB2= "1";
	//�������ʹ���
	public final static String BBLXDM_JB = "0";//����
	public final static String BBLXDM_NB = "1";//�걨����ʱ���ã�ʵ�����Ķ���0
	//���ʱ�ʶ
	public final static String JZBS = "000000";
	//˰�����ʹ���
	public final static String SZDM_YYS = "02";//Ӫҵ˰
	public final static String SZDM_JSS = "10";//����ά������˰
	public final static String SZDM_JYFFJ = "51";//�����Ѹ���
	public final static String SZDM_GRSDS = "05";//��������˰
	public final static String SZDM_QYSDS = "30";//��ҵ����˰
    //�Ǽ�ע������
	public final static String DJCELX_GTGSH = "410";//���幤�̻�
	public final static String DJCELX_GRHH  = "420";//���˺ϻ�
	public final static int DJCELX_GTJY  = 2; //���徭Ӫ ����410��420
	public final static int DJCELX_QY  = 1;  //��ҵ�����徭Ӫ�Ĳ���
	public static String getNsrlxmc(String nsrlxdm){
		int nsrlx=Integer.parseInt(nsrlxdm);
		switch(nsrlx)
		{
		  case 1:  
			  return "�°���ҵ������30%����";
			  
		  case 2:
			  return "�°���ҵ��ҵ������30%����";
			  
		  case 3:
			  return "������ҵ������30%����";
		  case 4:
			  return "������ҵ��ҵ������30%����";
		  case 5:
			  return "�������뾭��ʵ��";

		  default:
              return "";
		}
		
	}
	public static String getNsrlxmc1(String nsrlxdm){
		int nsrlx=Integer.parseInt(nsrlxdm);
		switch(nsrlx)
		{
		  case 1:  
			  return  "��ҵ�����¸�ʧҵ��Ա";
			  
		  case 2:
			  return  "�������뾭��ʵ��";
			  
		  case 3:
			  return  "���徭Ӫ";

		  default:
              return "";
		}
		
	}
}
