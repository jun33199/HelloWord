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
     * ��ȡ��ǰ��
     * @return nowYear
     */
   public static CheckBean getCheckBean(CheckBean checkBean, String jsjdm,
			String sknd,String startTime,String endTime){
	 //У�������ڲ���
	 		checkBean.setJsjdm(jsjdm);
	 		checkBean.setCheckQsq(true);
	 		//У�鵱ǰ�汾
	 		checkBean.setCurrentYear(sknd);
	 		//У�����ܷ�Χ�������Ͳ���
	 		checkBean.setSkssrqq(startTime);
	 		checkBean.setSkssrqz(endTime);
	 		checkBean.setJdlxCheckStyle("2");
	 		checkBean.setCheckJdlx(true);
	 		//����ֱܷ���
	 		checkBean.setCheckZfba(true);
	 		return checkBean;
   }
   /**
    * ���շ�ʽУ��
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
	   //�ж����շ�ʽ
      /* if (zsfsdm!= null) {
    	   if (zsfsdm.equals(QysdsNbConstant.ZSFSDM_CYLZS)) {
				throw new FrameException("����˰��Ϊ�����ʷ�ʽ������Ҫ���˰�˻�����Ϣ��!");
			} else if (zsfsdm.equals(QysdsNbConstant.ZSFSDM_DEZS)) { // ��������
				throw new FrameException("����˰��Ϊ�������շ�ʽ������Ҫ���˰�˻�����Ϣ��!");
			} else if (zsfsdm.equals(QysdsNbConstant.ZSFSDM_CZZS)) { // ��������
					}
    	   qyjbxxVo.setZsfsdm(zsfsdm);
    	   qyjbxxVo.setZsfsmc(zsfsmc);
       } */
	   if(!"03".equals(zsfsdm)){
		   throw new FrameException("����ҵ"+sknd+"���϶�����ҵ����˰���շ�ʽΪ�˶�����,��Ӧ�����!");
	   }
	   else {
			// 20070208���շ�ʽ���ȡ��Ϊ������Ϊ�ǲ���������ҵ�ġ�
			// throw new FrameException(
			// "����˰��û�к˶���ҵ����˰���շ�ʽ����˶��������д������Ϣ��!");
			qyjbxxVo.setZsfsdm(QysdsNbConstant.ZSFSDM_CZZS);
			qyjbxxVo.setZsfsmc(QysdsNbConstant.ZSFSNAME_CZZS);
		}
   }
   /*// �Ƿ���Ȼ��
   static private boolean isZrr(HttpServletRequest request)
   {
       UserData userData =
           (UserData)request.getSession().getAttribute(SessionKey.USER_DATA);

       return userData.yhlb.equals(com.ttsoft.common.util.CodeConstants.YHLB_ZRR);
   }*/
   /**
    * �ж��Ƿ�������
    * @param djjbsjVo
    * @return
    * @throws FrameException
    */
   public static boolean isForeignCorporation(Djjbsj djjbsjVo) throws FrameException
   {
	   boolean isWq=false;//�Ƿ�������ı�־��Ĭ��Ϊ��½��ҵ��
	   String djzclxdm="";
	   if(djjbsjVo!=null){
		   djzclxdm =djjbsjVo.getDjzclxdm();
	   }
	   dj_dm_djzclx djzclx=new dj_dm_djzclx();
	   djzclx = (dj_dm_djzclx) CodeTableManager.getObjectByCode(CodeTableKey.DJ_DM_DJZCLX, djzclxdm);
	  
	   if(djzclx!=null){
		  /* if(djzclx.getNwzfldm().equals(QysdsNbConstant.DJZCLXDM_NWZFLDM_GAT)){
			   isWq=true;
			   throw new FrameException("�Ǹ۰�̨��ҵ");
			} else*/ if(djzclx.getNwzfldm().equals(QysdsNbConstant.DJZCLXDM_NWZFLDM_WZ)){
				isWq=true;
			   throw new FrameException("����ҵ��������ҵ�����ܽ�����ҵ����˰�걨������");
			}else {
				isWq=false;
			}
	   }
	   		return isWq;
    }
   /**
    * У��˰����������
    * @param skksrq
    * @param skjzrq
    * @param sqlx
    * @param sknd
    */
   public static void checkSkssrq(String skksrq,String skjzrq,String sqlx,String sknd){
	   String nowYear=String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
	   String  qsrq="";//��ʼ����
	   String  jzrq="";
	   //�ж�˰����������
	   if(QysdsNb2014Contant.QYSDS_NB_SQLX_SM_CODE.equals(sqlx)){
		  if(nowYear.equals(sknd)){
				qsrq=nowYear+"0101";//��ʼ����
				jzrq=QysdsHelperUtil.getDate();//��ֹ����
				if(qsrq.equals(skksrq)&&jzrq.equals(skjzrq)){
					System.out.println("+++++");
				//���ɵ���ʼ��ֹ��������
				}else{
					skksrq=qsrq;
					skjzrq=jzrq;
				}
		  }else{
			  qsrq=sknd+"0101";//��ʼ����
			  jzrq=sknd+"1231";//��ֹ����
			  if(qsrq.equals(skksrq)&&jzrq.equals(skjzrq)){
			  //���ɵ���ʼ��ֹ��������
			  }else{
				  skksrq=qsrq;
				  skjzrq=jzrq;
			}
		  }
		}else if(QysdsNb2014Contant.QYSDS_NB_SQLX_WS_CODE.equals(sqlx)){
			qsrq=sknd+"0101";//��ʼ����
			jzrq=sknd+"1231";//��ֹ����
			if(qsrq.equals(skksrq)&&jzrq.equals(skjzrq)){
				//���ɵ���ʼ��ֹ��������
				}else{
					skksrq=qsrq;
					skjzrq=jzrq;
				}
	    }
   }
   /**
    * ��ȡ�������͵����
    */
   public static String getYear(Date curDate)
   {
       GregorianCalendar calendar = new GregorianCalendar();
       calendar.setTime(curDate);
       int year = calendar.get(calendar.YEAR);
       return new Integer(year).toString();
   }
}
