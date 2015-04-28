package com.lscdz.qysds.application.nsrjbxx2014.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import yangjian.frame.util.FrameException;
import com.lscdz.qysds.application.nsrjbxx2014.vo.response.QyjbxxVo;
import com.lscdz.qysds.application.qysdsnb2014.QysdsNb2014Contant;
import com.lscdz.qysds.common.QysdsNbConstant;
import com.lscdz.qysds.common.codetable.CodeTableKey;
import com.lscdz.qysds.common.codetable.vo.dj_dm_djzclx;
import com.lscdz.qysds.common.service.QysdsInfo.bo.Zsfs;
import com.lscdz.qysds.common.service.djjbsj.bo.Djjbsj;
import com.lscdz.qysds.common.service.qysdsCheck.bo.CheckBean;
import com.lscdz.qysds.common.util.QysdsHelperUtil;
import com.lscdz.util.codetable.CodeTableManager;

public class CheckUtil {
	 public static final String FLAG = "_need_flush";
	/**
     * 获取当前年
     * @return nowYear
     */
   public static CheckBean getCheckBean(CheckBean checkBean, String jsjdm,
			String sknd,String startTime,String endTime){
	 //校验清算期材料
	 		checkBean.setJsjdm(jsjdm);
	 		checkBean.setCheckQsq(true);
	 		//校验当前版本
	 		checkBean.setCurrentYear(sknd);
	 		//校验征管范围鉴定类型材料
	 		checkBean.setSkssrqq(startTime);
	 		checkBean.setSkssrqz(endTime);
	 		checkBean.setJdlxCheckStyle("2");
	 		checkBean.setCheckJdlx(true);
	 		//检查总分备案
	 		checkBean.setCheckZfba(true);
	 		return checkBean;
   }
   /**
    * 征收方式校验
    * @param qyjbxxVo
    * @param zsfsdm
    * @throws FrameException 
    */
   public static void checkZsfs(QyjbxxVo qyjbxxVo,Zsfs zsfs,String sknd) throws FrameException{
	   String zsfsdm="";
	   String zsfsmc="";
	   if(zsfs!=null){
			zsfsdm=zsfs.getZsfsdm();
			zsfsmc=zsfs.getZsfsmc();
		}else{
			zsfsdm=QysdsNbConstant.ZSFSDM_CZZS;
		}
	   //判断征收方式
      /* if (zsfsdm!= null) {
    	   if (zsfsdm.equals(QysdsNbConstant.ZSFSDM_CYLZS)) {
				throw new FrameException("该纳税人为纯益率方式！不需要填报纳税人基本信息表!");
			} else if (zsfsdm.equals(QysdsNbConstant.ZSFSDM_DEZS)) { // 定额征收
				throw new FrameException("该纳税人为定额征收方式！不需要填报纳税人基本信息表!");
			} else if (zsfsdm.equals(QysdsNbConstant.ZSFSDM_CZZS)) { // 查账征收
					}
    	   qyjbxxVo.setZsfsdm(zsfsdm);
    	   qyjbxxVo.setZsfsmc(zsfsmc);
       } */
	   if(!"03".equals(zsfsdm)){
		   throw new FrameException("该企业"+sknd+"年认定的企业所得税征收方式为核定征收,不应填报本表!");
	   }
	   else {
			// 20070208征收方式如果取出为空则认为是查账征收企业的。
			// throw new FrameException(
			// "该纳税人没有核定企业所得税征收方式，请核定后继续填写基本信息表!");
			qyjbxxVo.setZsfsdm(QysdsNbConstant.ZSFSDM_CZZS);
			qyjbxxVo.setZsfsmc(QysdsNbConstant.ZSFSNAME_CZZS);
		}
   }
   /*// 是否自然人
   static private boolean isZrr(HttpServletRequest request)
   {
       UserData userData =
           (UserData)request.getSession().getAttribute(SessionKey.USER_DATA);

       return userData.yhlb.equals(com.ttsoft.common.util.CodeConstants.YHLB_ZRR);
   }*/
   /**
    * 判断是否是外企
    * @param djjbsjVo
    * @return
    * @throws FrameException
    */
   public static boolean isForeignCorporation(Djjbsj djjbsjVo) throws FrameException
   {
	   boolean isWq=false;//是否是外企的标志（默认为内陆企业）
	   String djzclxdm="";
	   if(djjbsjVo!=null){
		   djzclxdm =djjbsjVo.getDjzclxdm();
	   }
	   dj_dm_djzclx djzclx=new dj_dm_djzclx();
	   djzclx = (dj_dm_djzclx) CodeTableManager.getObjectByCode(CodeTableKey.DJ_DM_DJZCLX, djzclxdm);
	  
	   if(djzclx!=null){
		  /* if(djzclx.getNwzfldm().equals(QysdsNbConstant.DJZCLXDM_NWZFLDM_GAT)){
			   isWq=true;
			   throw new FrameException("是港澳台企业");
			} else*/ if(djzclx.getNwzfldm().equals(QysdsNbConstant.DJZCLXDM_NWZFLDM_WZ)){
				isWq=true;
			   throw new FrameException("该企业是外资企业，不能进行企业所得税申报！！！");
			}else {
				isWq=false;
			}
	   }
	   		return isWq;
    }
   /**
    * 校验税款所属日期
    * @param skksrq
    * @param skjzrq
    * @param sqlx
    * @param sknd
    */
   public static void checkSkssrq(String skksrq,String skjzrq,String sqlx,String sknd){
	   String nowYear=String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
	   String  qsrq="";//起始日期
	   String  jzrq="";
	   //判断税款所属日期
	   if(QysdsNb2014Contant.QYSDS_NB_SQLX_SM_CODE.equals(sqlx)){
		  if(nowYear.equals(sknd)){
				qsrq=nowYear+"0101";//起始日期
				jzrq=QysdsHelperUtil.getDate();//截止日期
				if(qsrq.equals(skksrq)&&jzrq.equals(skjzrq)){
					System.out.println("+++++");
				//生成的起始截止日期正常
				}else{
					skksrq=qsrq;
					skjzrq=jzrq;
				}
		  }else{
			  qsrq=sknd+"0101";//起始日期
			  jzrq=sknd+"1231";//截止日期
			  if(qsrq.equals(skksrq)&&jzrq.equals(skjzrq)){
			  //生成的起始截止日期正常
			  }else{
				  skksrq=qsrq;
				  skjzrq=jzrq;
			}
		  }
		}else if(QysdsNb2014Contant.QYSDS_NB_SQLX_WS_CODE.equals(sqlx)){
			qsrq=sknd+"0101";//起始日期
			jzrq=sknd+"1231";//截止日期
			if(qsrq.equals(skksrq)&&jzrq.equals(skjzrq)){
				//生成的起始截止日期正常
				}else{
					skksrq=qsrq;
					skjzrq=jzrq;
				}
	    }
   }
   /**
    * 获取日期类型的年度
    */
   public static String getYear(Date curDate)
   {
       GregorianCalendar calendar = new GregorianCalendar();
       calendar.setTime(curDate);
       int year = calendar.get(calendar.YEAR);
       return new Integer(year).toString();
   }
}
