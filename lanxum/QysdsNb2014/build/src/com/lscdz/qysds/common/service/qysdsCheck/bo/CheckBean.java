/**
 * @Title:       CheckBean.java
 * @Description: TODO
 * @date:        2014-4-16下午03:30:55
 * @version:     V1.0
 */
package com.lscdz.qysds.common.service.qysdsCheck.bo;

import java.io.Serializable;
import java.util.Calendar;

/**
 * @Description: TODO 企业所得税用来承载校验信息和校验结果的类
 * 					  使用：在校验前将校验信息存入form，在校验中设置校验结果，校验完再存入form（不能直接使用QysdsNewForm，因为减免备案和基本信息表不使用QysdsNewForm承载）
 * @author: 	 Lijn
 * @time:        2014-4-16
 */
public final class CheckBean implements Serializable
{
	/**
	 * Description：
	 */
	private static final long serialVersionUID = -4732956676489787468L;

	//计算机代码
	private String jsjdm="";
	
	/**
	 * 版本校验时
	 * currentYear：当前年度--根据前台页面得到
	 * versionStartYear：该版本适用开始年度
	 * versionStartYear：该版本适用截止年度
	 * 校验失败抛出异常，无校验结果
	 */
	private String currentYear = "";
	private String versionStartYear = "";
	private String versionEndYear = "";
	
	
	/**
	 * 征管范围鉴定
	 * jsjdm:计算机代码
	 * skssrqq:税款所属日期起
	 * skssrqz：税款所属日期止
	 * sksj:税款时间
	 * jdlx:鉴定类型--校验结果；可根据1.skssrqq和skssrqz获得鉴定结果或2.sksj获得鉴定结果
	 */
	private String skssrqq = "";
	private String skssrqz = "";
	private String sksj="";
	private String jdlx;
	
	
	
	/**
	 * 清算期
	 * jsjdm:计算机代码
	 * 校验失败抛出异常，无校验结果
	 */
	private boolean isInQsq=false;
/*-----------------------------------*/	
	/**
	 * 是否完成清算
	 */
	private boolean finishQs=false;
	/**
	 * 是否检验清算期
	 */
	private boolean checkQsq=false;
	/**
	 * 是否检验鉴定类型	
	 */
	private boolean checkJdlx=false;
	/**
	 * 是否检验总分备案
	 */
	private boolean checkZfba=false;
	/**
	 * 检验鉴定类型所根据的方式，1代表根据计算机代码、税款时间检验征管范围鉴定类型
	 * 2代表根据计算机代码、税款所属日期起和税款所属日期止检验征管范围鉴定类型
	 */
	private String jdlxCheckStyle="";
	/**
	 * @Description: 生成税款所属日期区间季报
	 */
	/**
	 * 企业所得税征管范围鉴定表对象，其中包含jdlx等信息
	 */
	private Qysdszgfwjd qysdszgfwjd=null;
	/**
	 * 总分机构信息
	 */
	private ZfjgInf zfjgInf=null;
	/**
	 * @Description: 生成税款所属日期区间季报
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
	 * @Description: TODO 生成税款所属日期区间--年
	 */
	public void createZgrqByCurrenttimeY()
	{
		int year = Calendar.getInstance().get(Calendar.YEAR)-1;		
		
		skssrqq = year+"0101";
		skssrqz = year+"1231";
	}
	
	/**
	 * @description: getter-- jsjdm
	 * @return the jsjdm
	 */
	public String getJsjdm() {
		return jsjdm;
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
	public String getCurrentYear() {
		return currentYear;
	}
	/**
	 * @description: setter-- currentYear
	 * @param currentYear the currentYear to set
	 */
	public void setCurrentYear(String currentYear) {
		this.currentYear = currentYear;
	}
	/**
	 * @description: getter-- versionStartYear
	 * @return the versionStartYear
	 */
	public String getVersionStartYear() {
		return versionStartYear;
	}
	/**
	 * @description: setter-- versionStartYear
	 * @param versionStartYear the versionStartYear to set
	 */
	public void setVersionStartYear(String versionStartYear) {
		this.versionStartYear = versionStartYear;
	}
	/**
	 * @description: getter-- versionEndYear
	 * @return the versionEndYear
	 */
	public String getVersionEndYear() {
		return versionEndYear;
	}
	/**
	 * @description: setter-- versionEndYear
	 * @param versionEndYear the versionEndYear to set
	 */
	public void setVersionEndYear(String versionEndYear) {
		this.versionEndYear = versionEndYear;
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

	public boolean isCheckQsq() {
		return checkQsq;
	}

	public void setCheckQsq(boolean checkQsq) {
		this.checkQsq = checkQsq;
	}

	public boolean isCheckJdlx() {
		return checkJdlx;
	}

	public void setCheckJdlx(boolean checkJdlx) {
		this.checkJdlx = checkJdlx;
	}

	public boolean isCheckZfba() {
		return checkZfba;
	}

	public void setCheckZfba(boolean checkZfba) {
		this.checkZfba = checkZfba;
	}

	public String getJdlxCheckStyle() {
		return jdlxCheckStyle;
	}

	public void setJdlxCheckStyle(String jdlxCheckStyle) {
		this.jdlxCheckStyle = jdlxCheckStyle;
	}

	public boolean isFinishQs() {
		return finishQs;
	}

	public void setFinishQs(boolean finishQs) {
		this.finishQs = finishQs;
	}

	public Qysdszgfwjd getQysdszgfwjd() {
		return qysdszgfwjd;
	}

	public void setQysdszgfwjd(Qysdszgfwjd qysdszgfwjd) {
		this.qysdszgfwjd = qysdszgfwjd;
	}

	public ZfjgInf getZfjgInf() {
		return zfjgInf;
	}

	public void setZfjgInf(ZfjgInf zfjgInf) {
		this.zfjgInf = zfjgInf;
	}

	
	
}
