package com.ttsoft.bjtax.smsb.zjyjmsb.web;

import com.ttsoft.framework.exception.SystemException;

/**
 * <p>Title: 北京地税综合管理系统  申报征收模块</p>
 * <p>Description:再就业减免申报业务常量</p>
 * <p>Company: 四一安信</p>
 * @author qinwei
 * @version 1.0 2006-5-18
 */

public class ZjyjmsbConstant {
 
	//再就业报表(一)
	public final static String ZJYJMSBB1 = "0";
	//再就业报表(二)
	public final static String ZJYJMSBB2= "1";
	//报表类型代码
	public final static String BBLXDM_JB = "0";//季报
	public final static String BBLXDM_NB = "1";//年报，暂时无用，实际填充的都是0
	//记帐标识
	public final static String JZBS = "000000";
	//税种类型代码
	public final static String SZDM_YYS = "02";//营业税
	public final static String SZDM_JSS = "10";//城市维护建设税
	public final static String SZDM_JYFFJ = "51";//教育费附加
	public final static String SZDM_GRSDS = "05";//个人所得税
	public final static String SZDM_QYSDS = "30";//企业所得税
    //登记注册类型
	public final static String DJCELX_GTGSH = "410";//个体工商户
	public final static String DJCELX_GRHH  = "420";//个人合伙
	public final static int DJCELX_GTJY  = 2; //个体经营 包括410和420
	public final static int DJCELX_QY  = 1;  //企业，个体经营的补集
	public static String getNsrlxmc(String nsrlxdm){
		int nsrlx=Integer.parseInt(nsrlxdm);
		switch(nsrlx)
		{
		  case 1:  
			  return "新办企业服务型30%以上";
			  
		  case 2:
			  return "新办企业商业纯零售30%以上";
			  
		  case 3:
			  return "现有企业服务型30%以上";
		  case 4:
			  return "现有企业商业纯零售30%以上";
		  case 5:
			  return "主辅分离经济实体";

		  default:
              return "";
		}
		
	}
	public static String getNsrlxmc1(String nsrlxdm){
		int nsrlx=Integer.parseInt(nsrlxdm);
		switch(nsrlx)
		{
		  case 1:  
			  return  "企业吸纳下岗失业人员";
			  
		  case 2:
			  return  "主辅分离经济实体";
			  
		  case 3:
			  return  "个体经营";

		  default:
              return "";
		}
		
	}
}
