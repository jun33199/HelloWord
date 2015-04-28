/**
 * @Title:       CheckBean.java
 * @Description: TODO
 * @date:        2014-4-16����03:30:55
 * @version:     V1.0
 */
package com.ttsoft.bjtax.smsb.util.qysdsCheck;

import java.io.Serializable;
import java.util.Calendar;

/**
 * @Description: TODO ��ҵ����˰��������У����Ϣ��У��������
 * 					  ʹ�ã���У��ǰ��У����Ϣ����form����У��������У������У�����ٴ���form������ֱ��ʹ��QysdsNewForm����Ϊ���ⱸ���ͻ�����Ϣ��ʹ��QysdsNewForm���أ�
 * @author: 	 Lijn
 * @time:        2014-4-16
 */
public final class CheckBean implements Serializable
{
	/**
	 * Description��
	 */
	private static final long serialVersionUID = -1928965683666352321L;

	//���������
	private String jsjdm="";
	
	/**
	 * �汾У��ʱ
	 * currentYear����ǰ���--����ǰ̨ҳ��õ�
	 * versionStartYear���ð汾���ÿ�ʼ���
	 * versionStartYear���ð汾���ý�ֹ���
	 * У��ʧ���׳��쳣����У����
	 */
	private String currentTime = "";
	private String versionStartTime = "";
	private String versionEndTime = "";
	
	
	/**
	 * ���ܷ�Χ����
	 * jsjdm:���������
	 * skssrqq:˰������������
	 * skssrqz��˰����������ֹ
	 * sksj:˰��ʱ��
	 * jdlx:��������--У�������ɸ���1.skssrqq��skssrqz��ü��������2.sksj��ü������
	 */
	private String skssrqq = "";
	private String skssrqz = "";
	private String sksj="";
	private String jdlx ="";
	
	
	
	/**
	 * ������
	 * jsjdm:���������
	 * isInQsq:�Ƿ���������
	 * У��ʧ���׳��쳣����У����
	 */
	private boolean isInQsq=false;

/*-----------------------------------*/	
	/**
	 * �Ƿ��������
	 */
	private boolean finishQs=false;	
	/**
	 * @Description: ����˰�������������伾��
	 */
	public void createZgrqByCurrenttimeM()
	{
		int year = Calendar.getInstance().get(Calendar.YEAR);
		int month = Calendar.getInstance().get(Calendar.MONTH)+1;
		
		String starttime="";
		String endtime="";
		if(month>3 && month<=6)
		{
			starttime = year+"0101";
			endtime = year +"0331";
		}
		if(month>6 && month<=9)
		{
			starttime = year+"0101";
			endtime = year +"0630";
		}
		if(month>9 && month<=12)
		{
			starttime = year+"0101";
			endtime = year+"0930";
		}
		if(month>0 && month<=3)
		{
			starttime = (year-1)+"0101";
			endtime = (year-1)+"1231";
		}
		
		skssrqq = starttime;
		skssrqz = endtime;
	}
	
	/**
	 * @description: getter-- jsjdm
	 * @return the jsjdm
	 */
	public String getJsjdm() {
		return jsjdm;
	}
	/**
	 * @description: getter-- isInQsq
	 * @return the isInQsq
	 */
	public boolean isInQsq() {
		return isInQsq;
	}
	/**
	 * @description: setter-- isInQsq
	 * @param isInQsq the isInQsq to set
	 */
	public void setInQsq(boolean isInQsq) {
		this.isInQsq = isInQsq;
	}
	/**
	 * @description: setter-- jsjdm
	 * @param jsjdm the jsjdm to set
	 */
	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}
	/**
	 * @description: getter-- currentYear
	 * @return the currentYear
	 */
	public String getCurrentTime() {
		return currentTime;
	}
	/**
	 * @description: setter-- currentYear
	 * @param currentYear the currentYear to set
	 */
	public void setCurrentTime(String currentYear) {
		this.currentTime = currentYear;
	}
	/**
	 * @description: getter-- versionStartYear
	 * @return the versionStartYear
	 */
	public String getVersionStartTime() {
		return versionStartTime;
	}
	/**
	 * @description: setter-- versionStartYear
	 * @param versionStartYear the versionStartYear to set
	 */
	public void setVersionStartTime(String versionStartYear) {
		this.versionStartTime = versionStartYear;
	}
	/**
	 * @description: getter-- versionEndYear
	 * @return the versionEndYear
	 */
	public String getVersionEndTime() {
		return versionEndTime;
	}
	/**
	 * @description: setter-- versionEndYear
	 * @param versionEndYear the versionEndYear to set
	 */
	public void setVersionEndTime(String versionEndYear) {
		this.versionEndTime = versionEndYear;
	}
	/**
	 * @description: getter-- skssrqq
	 * @return the skssrqq
	 */
	public String getSkssrqq() {
		return skssrqq;
	}
	/**
	 * @description: setter-- skssrqq
	 * @param skssrqq the skssrqq to set
	 */
	public void setSkssrqq(String skssrqq) {
		this.skssrqq = skssrqq;
	}
	/**
	 * @description: getter-- skssrqz
	 * @return the skssrqz
	 */
	public String getSkssrqz() {
		return skssrqz;
	}
	/**
	 * @description: setter-- skssrqz
	 * @param skssrqz the skssrqz to set
	 */
	public void setSkssrqz(String skssrqz) {
		this.skssrqz = skssrqz;
	}
	/**
	 * @description: getter-- sksj
	 * @return the sksj
	 */
	public String getSksj() {
		return sksj;
	}
	/**
	 * @description: setter-- sksj
	 * @param sksj the sksj to set
	 */
	public void setSksj(String sksj) {
		this.sksj = sksj;
	}
	/**
	 * @description: getter-- jdlx
	 * @return the jdlx
	 */
	public String getJdlx() {
		return jdlx;
	}
	/**
	 * @description: setter-- jdlx
	 * @param jdlx the jdlx to set
	 */
	public void setJdlx(String jdlx) {
		this.jdlx = jdlx;
	}

	public boolean isFinishQs() {
		return finishQs;
	}

	public void setFinishQs(boolean finishQs) {
		this.finishQs = finishQs;
	}

	
}
