package com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.util;

public class DateUtilToChinese {
	
	/**
	 * ������ת��Ϊһ�����ڣ����磺2014-05-09,2014-05-11ת��Ϊ2014��5��9����2014��5��11��
	 * @param beginDate��ʼ����
	 * @param endDate��������
	 * @return
	 */
	public static String convertDate(String beginDate,String endDate){
		String beginStr ="";
		String endStr ="";
		//�ж��Ƿ��������ڶ���Ϊ��
		if((beginDate!=null&&!"".equals(beginDate))&&(endDate!=null&&!"".equals(endDate))){
			String[] begins = beginDate.split("-");
			beginStr =begins[0]+" �� "+begins[1]+" �� "+begins[2]+" �� ";
			String[] ends = endDate.split("-");
			endStr =ends[0]+" �� "+ends[1]+" �� "+ends[2]+" �� ";
		}
		return beginStr+" �� "+endStr;
	}

}
