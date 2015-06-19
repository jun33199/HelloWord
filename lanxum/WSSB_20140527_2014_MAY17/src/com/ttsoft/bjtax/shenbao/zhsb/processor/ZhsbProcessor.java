package com.ttsoft.bjtax.shenbao.zhsb.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import com.ekernel.db.or.ORContext;
import com.ekernel.db.or.ORManager;
import com.syax.bjtax.ca.proxy.CAProxy;
import com.syax.bjtax.ca.util.DzyjHelper;
import com.syax.bjtax.ca.vo.DzyjsjVO;
import com.syax.common.util.CAcodeConstants;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.jikuai.zwcl.exception.ZwclException;
import com.ttsoft.bjtax.jikuai.zwcl.inf.JKAdapter;
import com.ttsoft.bjtax.jikuai.zwcl.vo.page.Yskm;
import com.ttsoft.bjtax.sfgl.common.model.Sfxy;
import com.ttsoft.bjtax.shenbao.constant.ActionConstant;
import com.ttsoft.bjtax.shenbao.constant.ApplicationConstant;
import com.ttsoft.bjtax.shenbao.constant.CodeConstant;
import com.ttsoft.bjtax.shenbao.model.client.DeclareInfor;
import com.ttsoft.bjtax.shenbao.model.client.Ysjc;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkmx;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkmx_His;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb_His;
import com.ttsoft.bjtax.shenbao.model.domain.Swjgzzjg;
import com.ttsoft.bjtax.shenbao.model.domain.Yh;
import com.ttsoft.bjtax.shenbao.model.domain.Zqrl;
import com.ttsoft.bjtax.shenbao.proxy.TrancProxy;
import com.ttsoft.bjtax.shenbao.service.adaptor.SKHAdaptor;
import com.ttsoft.bjtax.shenbao.service.processor.InterFaceProcessor;
import com.ttsoft.bjtax.shenbao.service.vo.YPDSJKS;
import com.ttsoft.bjtax.shenbao.util.CAUtils;
import com.ttsoft.bjtax.shenbao.util.DBResource;
import com.ttsoft.bjtax.shenbao.util.FindObjInList;
import com.ttsoft.bjtax.shenbao.util.FriendHelper;
import com.ttsoft.bjtax.shenbao.util.Skssrq;
import com.ttsoft.bjtax.shenbao.util.StringUtils;
import com.ttsoft.bjtax.shenbao.util.TinyTools;
import com.ttsoft.bjtax.shenbao.zhsb.ZhsbMapConstant;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.Debug;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.DateUtil;
import com.ttsoft.framework.util.StringUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>
 * Title: ������˰�ۺϹ���ϵͳ �걨����ģ��
 * </p>
 * <p>
 * Description: �����������ۺ��걨�ĺ�̨processor
 * </p>
 * <p>
 * Copyright: Copyright (c) 2003
 * </p>
 * <p>
 * Company: TTSOFT
 * </p>
 *
 * @author wanghw
 * @version 1.0 2003-8-24
 */
public class ZhsbProcessor
    implements Processor {
    private static final int SPLITNUM_SM = 4;

    private static final int SESSION_ID = 0;

    // �����ʶ����(Ĭ��ֵΪ20��0)
    public final static String ZWBS = "00000000000000000000";

    // �Ƿ񷵻ؽɿ�����Ĭ��Ϊfalse
    private boolean isReturnPaymentInfo = false;

    // ��ӡ��ʶ
    private int printTag;
    
    // ���������걨��ʽ
    private int zqlxsbfs;
    
    private BigDecimal sumhjje = new BigDecimal("0.00");

    /**
     * �걨�ɿ�������Ϣ
     */
    private Sbjkzb sbjkzb;

    /**
     * �걨�ɿ���ϸ����
     */
    private List sbjkmxInfo;

    /**
     * ���������
     */
    private String jsjdm;

    /**
     * orManage�ĳ���
     */
    private long SESSIONID = 0;

    /**
     * �걨���
     */
    private String sbbh;

    // ���췽��
    public ZhsbProcessor() {
    }

    // ʵ��Processor�ӿ�
    public Object process(VOPackage vOPackage) throws BaseException {
        switch (vOPackage.getAction()) {
            case ActionConstant.INT_ACTION_SAVE:
                try {
                    return doSave(vOPackage);
                }
                catch (Exception ex) {
                    throw ExceptionUtil.getBaseException(ex, "����ɿ�����ʧ��!");
                }
            case ActionConstant.INT_ACTION_QUERY:
                try {
                    return doQuery(vOPackage, CodeConstant.SJLY_SB_SBLR);
                }
                catch (Exception ex) {
                    throw ExceptionUtil.getBaseException(ex, "��ѯ�ɿ�����ʧ�ܣ�");
                }
            case ActionConstant.INT_ACTION_DELETE:
                try {
                    return doDel(vOPackage);
                }
                catch (Exception ex) {
                    throw ExceptionUtil.getBaseException(ex,
                        "���Ͻɿ�����ʧ�ܣ�");
                }
            case ActionConstant.INT_ACTION_PRINT: // һƱ��˰��ӡ����
                try {
                    return doPrint(vOPackage);
                }
                catch (Exception ex) {
                    throw ExceptionUtil.getBaseException(ex,
                        "��ȡһƱ��˰��ӡ����ʧ�ܣ�");
                }
            case ActionConstant.INT_ACTION_GETPROPERTY: // ȡ������ֵ
                try {
                    return getProperty(vOPackage);
                }
                catch (Exception ex) {
                    throw ExceptionUtil.getBaseException(ex,
                        "ȡ������ֵʧ�ܣ�");
                }
            case ActionConstant.INT_ACTION_JMZQ:
                try {
                    return doCheckZqlx(vOPackage);
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                    return new Boolean(false);
                }
            case ActionConstant.INT_ACTION_JM_DEAL:
                try {
                    return doSave_jm(vOPackage);
                }
                catch (Exception ex) {
                	ex.printStackTrace();
                    throw ExceptionUtil.getBaseException(ex, "����С΢��ҵ������Ϣʧ��");
                }
               
                // case ActionConstant.INT_ACTION_YPDS_GENETATE: //���пۿ�ɹ�
                // try {
                // return doGenerateYpdsJks(vOPackage);
                // }
                // catch (Exception ex) {
                // ex.printStackTrace();
                // throw ExceptionUtil.getBaseException(ex,
                // "�޸������־ʧ�ܣ��ۿ�����ɣ��������Ա������ϵ");
                // }
            case ActionConstant.INT_ACTION_ZWBS_YHKK: // ���пۿ�ɹ�
                try {
                    // return doModifyZwbsYhkk(vOPackage);
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                    throw ExceptionUtil.getBaseException(ex,
                        "�޸������־ʧ�ܣ��ۿ�����ɣ��������Ա������ϵ");
                }
            case ActionConstant.INT_ACTION_DELETEALL: // ɾ�����е����걨����
                try {
                    doDelBySBBH(vOPackage);
                    return null;
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                    throw ExceptionUtil.
                        getBaseException(ex,
                        "�걨ʧ�ܣ��걨����δɾ�����������Ա������ϵ");
                }
            case ActionConstant.INT_ACTION_QUERYYSB: // ��ѯ���걨����
                try {
                    return doQueryysb(vOPackage);
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                    throw ExceptionUtil.
                        getBaseException(ex,
                        "��ѯ���걨ʧ�ܣ��������Ա������ϵ");
                }
            default:
                throw ExceptionUtil.
                    getBaseException(new
                    Exception("ϵͳ����,���뼼��֧����ϵ!"));
        }
    }

    // /**
     // * ����ɿ�����
     // * @param voPackage ǰ̨���������걨��Ϣ(����data����ΪDeclareInfo)
     // * @return (��ȡDeclareInfo�е�һ����ʶȷ���Ƿ���Ҫ���ؽɿ�����)<br>
     // * ����Ҫ���ؽɿ�����ʱ ���ط���һ����װ��map<br>
     // * ����Ҫʱֻ�Ƿ���Boolean.True(�ɹ�ʱ)��������������׳��쳣��Ϣ<br>
     // * @throws Exception �׳��쳣
     // */
    // private Object doSave(VOPackage voPackage) throws Exception {
    // //�������
    // if(voPackage == null || voPackage.getData() == null
    // || voPackage.getUserData() == null )
    // {
    // throw ExceptionUtil.getBaseException(new Exception(), "�ύ�����ݲ��Ϸ�!");
    // }
    // //�õ�ǰ̨��������declareInfor��Map
    // Map mapDeclareInfor = (Map) voPackage.getData();
    // DeclareInfor declareInfor = null;
    // Set set = mapDeclareInfor.keySet();
    // String key = null;
    // Map jkdata = new HashMap();
    // HashMap tempData = new HashMap();
    // UserData ud = voPackage.getUserData();
    // //Ϊ�ô��걨�ĸ�˰������ȡһ���걨���
    // sbbh = getSbbh(ud.getYhid());
    // //���ݲ�ͬ��˰�������������ɽɿ�ƾ֤
    // for (Iterator keyset = set.iterator(); keyset.hasNext(); ) {
    // key = (String) keyset.next();
    // declareInfor = (DeclareInfor) mapDeclareInfor.get(key);
    // isReturnPaymentInfo = declareInfor.isIsReturnPaymentInfo();
    // printTag = declareInfor.getPrintTag();
    // if (declareInfor.getSbjkmxInfo() == null
    // || declareInfor.getSbjkmxInfo().size() < 1
    // || declareInfor.getSbjkzb() == null) {
    // throw ExceptionUtil.getBaseException(new Exception(), "�걨������Ϊ��!");
    // }
    // else {
    // sbjkzb = declareInfor.getSbjkzb();
    // sbjkmxInfo = declareInfor.getSbjkmxInfo();
    // }
    //
    // //�����ɿ����ݲ��������ݿ�������
    // Object object = createSbJkData(declareInfor.getSbjkzb(),
    // declareInfor.getSbjkmxInfo());
    // jkdata.put(key, object);
    // tempData = listToMap(object); //�����ݴ�ArrayList ת��Ϊmap��ʽ
    // jkdata.put(sbbh, tempData); //�����걨��Ŷ����ݽ������֣�������map��
    // }
    // if (isReturnPaymentInfo) {
    // return jkdata; //���ذ���˰���������ֵ��ɽɿ�����list���ɵ�Map
    // }
    // else {
    // return null;
    // }
    // }

    /**
     * ����ɿ�����
     *
     * @param voPackage
     *            ǰ̨���������걨��Ϣ(����data����ΪDeclareInfo)
     * @return (��ȡDeclareInfo�е�һ����ʶȷ���Ƿ���Ҫ���ؽɿ�����)<br>
     *         ����Ҫ���ؽɿ�����ʱ ���ط���һ����װ��map<br>
     *         ����Ҫʱֻ�Ƿ���Boolean.True(�ɹ�ʱ)��������������׳��쳣��Ϣ<br>
     * @throws Exception
     *             �׳��쳣
     */
    private Object doSave(VOPackage vo) throws Exception {
        // 0.�������
        Map rnMap = null;
        String showMsg = "";
        Map mapDeclareInfor = null;
        Sfxy sfxy = null;
        UserData ud = null;
        Map inMap = null;
        DzyjsjVO dzyj = null;
        Map jkdata = null;
        // 1.��ʼ��
        // /1.0.�����ʼ��
        rnMap = new HashMap();
        jkdata = new HashMap();
        // /1.1.ֵ�����ʼ��
        inMap = (Map) vo.getData();
        mapDeclareInfor = (Map) inMap.get("declareMap");
        sfxy = (Sfxy) inMap.get("sfxy");
        dzyj = (DzyjsjVO) inMap.get(ZhsbMapConstant.CA_QMSJ_VO);
        Yh yh = (Yh) inMap.get("yh");
        SWDJJBSJ jbsj = (SWDJJBSJ) inMap.get("jbsj");
        // /1.2.��ȡ�û������˻�����
        ud = vo.getUserData();
        // String jksh="123";
        // Map aDeclaration=new HashMap();
        // Sbjkzb zb =
        // ((DeclareInfor)((Map)aDeclaration.get(jksh)).get(ZhsbMapConstant.SBSJ)).getSbjkzb();
        // started modified by qianchao 2005-12-9
        boolean callYyaqSignFlag = ud.caflag;
        // ended modified by qianchao 2005-12-9
        DzyjHelper dh = new DzyjHelper();

        if (ud.getCaflag()) {
            try {
                dzyj = dh.saveDzyjsj(dzyj, "0", "0", "0", "0");
            }
            catch (com.syax.frame.exception.ApplicationException e) {
                // e.printStackTrace();
                throw ExceptionUtil.getBaseException(e);
            }
            catch (Exception ex) {
                ex.printStackTrace();
                throw ExceptionUtil.getBaseException(ex);
            }
            System.out.println("============����ǩ�����ݽ���==============");
            // System.out.println(dzyj.getJssj().toString());
        }

        // 2.����ҵ������
        // /2.0.�Ӿ����������ʼ��
        DeclareInfor declareInfor = null;
        Set set = mapDeclareInfor.keySet();
        String key = null;
        HashMap tempData = new HashMap();
        // /2.1.Ϊ�ô��걨�ĸ�˰������ȡһ���걨���
        sbbh = getSbbh(ud.getYhid());
        // /2.2.���ݲ�ͬ��˰�������������ɽɿ�ƾ֤
        for (Iterator keyset = set.iterator(); keyset.hasNext(); ) {
            key = (String) keyset.next();
            // //2.2.0.��ȡֵ����
            declareInfor = (DeclareInfor) mapDeclareInfor.get(key);
            isReturnPaymentInfo = declareInfor.isIsReturnPaymentInfo();
            printTag = declareInfor.getPrintTag();
            if (declareInfor.getSbjkmxInfo() == null ||
                declareInfor.getSbjkmxInfo().size() < 1
                || declareInfor.getSbjkzb() == null) {
                throw ExceptionUtil.getBaseException(new Exception(),
                    "�걨������Ϊ��!");
            }
            else {
                sbjkzb = declareInfor.getSbjkzb();
                sbjkmxInfo = declareInfor.getSbjkmxInfo();
            }
            /*��ȡί�е�λ������˰��˰Ŀ���жϲ�ѯ������Ƿ����˰������Ϊ������˰ĿҪ��ʾ modified by Junbing Tu 2014.07*/
            Sbjkmx sbjkmxTmp = null;
    		BigDecimal sumhjje = new BigDecimal("0.00");
            for (int i = 0; i < sbjkmxInfo.size(); i++) {
                sbjkmxTmp = (Sbjkmx) sbjkmxInfo.get(i);
                sumhjje = sumhjje.add(sbjkmxTmp.getSjse());
            }
            
            
            // //2.2.1.�����ɿ����ݲ��������ݿ�������
            Object object = createSbJkData(declareInfor.getSbjkzb(),
                                           declareInfor.getSbjkmxInfo());
           
            // //2.2.2.�����ݴ�ArrayList ת��Ϊmap��ʽ
            boolean kkflag1 = false;
            if (callYyaqSignFlag) {
                if (sfxy != null) {
                    if (CodeConstant.PRINT_YPDS_KM == printTag) {
                        kkflag1 = true;
                    }
                }
            }
            log("kkflag1=" + kkflag1);
            tempData = listToMapOfSkh(object, !kkflag1); //
            
            // //2.2.3.�����걨��Ŷ����ݽ������֣�������map��
            jkdata.put(sbbh, tempData);
        }
        
   /*begin================================================�ϼ�С��һԪ����==============================================*/
       Map data = (Map) jkdata.get(sbbh);
       boolean msgFlag = false;
       if(inMap.get("lwFlag") != null) {
    	   boolean lwFlag = ((Boolean) inMap.get("lwFlag")).booleanValue();
    	   List jkshList = (List) data.get("jkshList");
    	   
    	   if(lwFlag) {//�������У��ϲ��ɿ���
    		   double hj = 0;
        	   for(int ii=0; ii<jkshList.size(); ii++) {
        		   String jksh = (String) jkshList.get(ii);
        		   Map jks = (Map) data.get(jksh);
        		   hj += ((BigDecimal) jks.get("jehj")).doubleValue();
        	   }
        	   if(hj < 1) {
        		   showMsg = "�ɿ������1Ԫ�����ݹ���˰���ܾ�2012��25�Ź���Ĺ涨����������!";
        		   msgFlag = true;
        		   for(int ii=0; ii<jkshList.size(); ii++) {
            		   String jksh = (String) jkshList.get(ii);
            		   data.remove(jksh);
            	   }
        		   
        		   //����ת��his��
        		   this.insertToHisBySbbh(sbbh, vo.getUserData());
        		   //�����걨���ɾ�����ݿ���ʱ����
        		   this.deleteTempJksBySbbh(sbbh);
        		   
        		   data.remove("jkshList");
            	   jkdata.remove(sbbh);
            	   // 8.����������
        	        rnMap.put("reObject", jkdata);
        	        rnMap.put("showMsg", showMsg);
        	        rnMap.put("sskk", Boolean.valueOf(false));
        	        if (callYyaqSignFlag) {
        	            rnMap.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
        	        }
        	        // 99.����ֵ
        	        if (isReturnPaymentInfo) {
        	            // /99.0.���ذ���˰���������ֵ��ɽɿ�����list���ɵ�Map
        	            return rnMap;
        	        }
        	        else {
        	            // /99.1.����null
        	            return null;
        	        }
        	   }
    	   } else {//����������
    		   int removeNum = 0;
    		   for(int ii=0; ii<jkshList.size(); ii++) {
        		   String jksh = (String) jkshList.get(ii);
        		   Map jks = (Map) data.get(jksh);
        		   if(((BigDecimal) jks.get("jehj")).doubleValue() < 1) {
        			   data.remove(jksh);
        			   //����ת��his��
            		   this.insertToHisByJksh(jksh, vo.getUserData());
        			   //���ݽɿ����ɾ�����ݿ���ʱ����
            		   this.deleteTempJksByJksh(jksh);
        			   removeNum ++;
        		   }
        	   }
    		   //ȫ���Ƴ���ֱ�ӷ���
    		   if(jkshList.size() == removeNum) {
    			   showMsg += "�ɿ������1Ԫ�����ݹ���˰���ܾ�2012��25�Ź���Ĺ涨����������!";
    			   msgFlag = true;
    			   jkdata.remove(sbbh);
    			   // 8.����������
    		        rnMap.put("reObject", jkdata);
    		        rnMap.put("showMsg", showMsg);
    		        rnMap.put("sskk", Boolean.valueOf(false));
    		        if (callYyaqSignFlag) {
    		            rnMap.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
    		        }
    		        // 99.����ֵ
    		        if (isReturnPaymentInfo) {
    		            // /99.0.���ذ���˰���������ֵ��ɽɿ�����list���ɵ�Map
    		            return rnMap;
    		        }
    		        else {
    		            // /99.1.����null
    		            return null;
    		        }
    		   } 
    		   data.remove("jkshList");
    		   if(0 != removeNum) {
    			   jkdata.put(sbbh, data);
    			   showMsg += "���ֽɿ������1Ԫ�����ݹ���˰���ܾ�2012��25�Ź���Ĺ涨����������!";
    			   msgFlag = true;
    		   }
    	   }
       }
       
       data.remove("jkshList");
	   if(msgFlag == false) {
		   showMsg += "�걨�ɹ�";
	   }
   /*end================================================�ϼ�С��һԪ����==============================================*/
        
        // 3.�ж��ۿ�����
        boolean kkflag = false;
        if (callYyaqSignFlag) {
            if (sfxy != null) {
                if (CodeConstant.PRINT_YPDS_KM == printTag) {
                    kkflag = true;
                }
            }
        }
        log("kkflag=" + kkflag);
        // 4.�ж��Ƿ�����Э�黧����һƱ��˰�ɿ��飬���Ҹ�������Э���޸�������Ϣ
        List ypdsJkss = null;
        if (kkflag) {
            System.out.println("============����˰Ʊ���벢���������ݿ⿪ʼ==============");
            vo.setData(jkdata);
            vo.setAction(ActionConstant.INT_ACTION_YPDS_GENETATE);
            ypdsJkss = this.doGenerateYpdsJks(vo, sfxy);
            System.out.println("============����˰Ʊ���벢���������ݿ����==============");
        }
        // 5.�ж��Ƿ�����Э�黧�Խ������пۿ�֮ǰ�������־����
        // if(kkflag){
        // log("===========���ú�̨���������־�ɹ���ʼ===============");
        // vo.setAction(ActionConstant.INT_ACTION_ZWBS_YHKK);
        // this.doModifyZwbsYhkk(vo, 0);
        // log("===========���ú�̨���������־�ɹ����===============");
        // }

        // 6.�����CA�������ǩ����д
        // start modified code by qianchao 2006-2-7
        if (callYyaqSignFlag) {
            log("============�����걨��ŵ�ǩ����ʼ==============");
            // ��ȡ���е��걨�������ݲ�����˰Ʊ����
            Iterator keyset = jkdata.keySet().iterator();
            while (keyset.hasNext()) {
                String sbbhCA = (String) keyset.next();
                dzyj.setYwuid(sbbhCA);
                try {
                    log("=ǩ��YWUID����= yh:" + dzyj.getJsjdm() + " lsh:" +
                        dzyj.getLsh() + "  ywuid:" + sbbhCA);
                    CAProxy.getInstance().updateSignedData(dzyj);
                }
                catch (Exception e) {
                    e.printStackTrace();
                    throw e;
                }
            }
            log("============�����걨��ŵ�ǩ������==============");
        }
        // end modified code by qianchao 2006-2-7

        // 7.�ж��Ƿ�����Э�黧��ִ�����пۿ�,����˰�����ۿ�ӿ�

        if (kkflag) {
            System.out.println("========�����걨����˰�����ۿ�ӿڿ�ʼ[" + ud.getYhid() + "|" +
                               sfxy.getYhdm() + "|" + sfxy.getZh()
                               + "]===========");
            try {
                SKHAdaptor sa = new SKHAdaptor();
                double hjzse = 0.00;
                YPDSJKS ypdsJks = null;
                for (int i = 0; i < ypdsJkss.size(); i++) {
                    ypdsJks = (YPDSJKS) ypdsJkss.get(i);
                    hjzse += Double.parseDouble(ypdsJks.getSjjexx());
                }
                String hjStr = StringUtil.getFormatData(new BigDecimal(hjzse).
                    toString());
                Map retMap = (Map) sa.transferMoneyFromNsrZhToGk(jbsj.
                    getSwjgzzjgdm().substring(0, 2), hjzse, ypdsJkss,
                    jbsj.getSwjgzzjgdm(), ud, sfxy);
                String result = (String) retMap.get(sa.YHKK_KEY_RESULT);
                String addWord = (String) retMap.get(sa.YHKK_KEY_ADDWORD);
                if (sa.YHKK_RESULT_SUCCESS.equals(result)) {
                    showMsg += ",��" + sfxy.getYhmc() + "��" + sfxy.getZh() +
                        "�˻����ۿ������" + hjStr + "Ԫ";
                    // 5.�ж��Ƿ�����Э�黧�Խ������пۿ�֮ǰ�������־����
                    log("===========���ú�̨���������־�ɹ���ʼ===============");
                    vo.setAction(ActionConstant.INT_ACTION_ZWBS_YHKK);
                    this.doModifyZwbsYhkk(vo, 0);
                    log("===========���ú�̨���������־�ɹ����===============");
                }
                else if (sa.YHKK_RESULT_NOREASON.equals(result)) {
                    showMsg = "���з�����Ϣ���������ʵ�����˻���";
                    log("===========���ú�̨���������־״̬������ʼ===============");
                    vo.setAction(ActionConstant.INT_ACTION_ZWBS_YHKK);
                    this.doModifyZwbsYhkk(vo, 1);
                    log("===========���ú�̨���������־״̬�������===============");
                }
                else if (sa.YHKK_RESULT_LOST.equals(result)) {
                    throw new ApplicationException(addWord);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                throw ExceptionUtil.getBaseException(e,
                    "��5���Ӻ󡾲鿴���ڽɿ��顿�˶��Ƿ��Ѿ��γɡ����ӽɿ�ר�ýɿ��顿������ʱ�˶������˻������������⣬���뼼��֧����ϵ��лл��");
            }
        }

        // 8.����������
        rnMap.put("SBBH", sbbh);
        rnMap.put("reObject", jkdata);
        rnMap.put("showMsg", showMsg);
        rnMap.put("sskk", Boolean.valueOf(kkflag));
        if (callYyaqSignFlag) {
            rnMap.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
        }
        // 99.����ֵ
        if (isReturnPaymentInfo) {
            // /99.0.���ذ���˰���������ֵ��ɽɿ�����list���ɵ�Map
            return rnMap;
        }
        else {
            // /99.1.����null
            return null;
        }
    }
    
    private Object doSave_jm(VOPackage vo) throws Exception {
        // 0.�������
        Map mapDeclareInfor = null;
        Sfxy sfxy = null;
        UserData ud = null;
        Map inMap = null;
        DzyjsjVO dzyj = null;
        Map jkdata = null;
        // 1.��ʼ��
        // /1.0.�����ʼ��
        
        jkdata = new HashMap();
        // /1.1.ֵ�����ʼ��
        inMap = (Map) vo.getData();
        
        mapDeclareInfor = (Map) inMap.get("declareMap");
        sfxy = (Sfxy) inMap.get("sfxy");
        dzyj = (DzyjsjVO) inMap.get(ZhsbMapConstant.CA_QMSJ_VO);
        
        // /1.2.��ȡ�û������˻�����
        ud = vo.getUserData();
        
        boolean callYyaqSignFlag = ud.caflag;
        // ended modified by qianchao 2005-12-9
        DzyjHelper dh = new DzyjHelper();

        if (ud.getCaflag()) {
            try {
                dzyj = dh.saveDzyjsj(dzyj, "0", "0", "0", "0");
            }
            catch (com.syax.frame.exception.ApplicationException e) {
                // e.printStackTrace();
                throw ExceptionUtil.getBaseException(e);
            }
            catch (Exception ex) {
                ex.printStackTrace();
                throw ExceptionUtil.getBaseException(ex);
            }
            System.out.println("============����ǩ�����ݽ���==============");
            // System.out.println(dzyj.getJssj().toString());
        }

        // 2.����ҵ������
        // /2.0.�Ӿ����������ʼ��
        DeclareInfor declareInfor = null;
        Set set = mapDeclareInfor.keySet();
        String key = null;
        
        // /2.1.Ϊ�ô��걨�ĸ�˰������ȡһ���걨���
        if(inMap.get("SBBH")==null || "".equals(inMap.get("SBBH")))
        {
        	sbbh = getSbbh(ud.getYhid());
        }else {
			sbbh = (String) inMap.get("SBBH");
		}
        // /2.2.���ݲ�ͬ��˰�������������ɽɿ�ƾ֤
        for (Iterator keyset = set.iterator(); keyset.hasNext(); ) {
            key = (String) keyset.next();
            // //2.2.0.��ȡֵ����
            declareInfor = (DeclareInfor) mapDeclareInfor.get(key);
            isReturnPaymentInfo = declareInfor.isIsReturnPaymentInfo();
            printTag = declareInfor.getPrintTag();
            
            /*��ȡί�е�λ������˰��˰Ŀ���жϲ�ѯ������Ƿ����˰������Ϊ������˰ĿҪ��ʾ modified by Junbing Tu 2014.07*/
            sbjkmxInfo=declareInfor.getSbjkmxInfo();
            Sbjkmx sbjkmxTmp = null;
    		BigDecimal sumhjje = new BigDecimal("0.00");
            for (int i = 0; i < sbjkmxInfo.size(); i++) {
                sbjkmxTmp = (Sbjkmx) sbjkmxInfo.get(i);
                sumhjje = sumhjje.add(sbjkmxTmp.getSjse());
            }

           	createSbJkData_his(declareInfor.getSbjkzb(), (List)inMap.get("JMLIST"),"10",ud.getYhid());

        }
        

        
        return new Boolean(true);
    }
    
    /**
     * ���ݽɿ���ţ������ݲ�������his
     * @param jksh
     * @param userData
     */
    public void insertToHisByJksh(String jksh, UserData userData) {
    	Connection conn = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	String sqlSelZb = "select * from sbdb.sb_jl_sbjkzb where jkpzh = '" + jksh + "'";
    	String sqlSelMx = "select * from sbdb.sb_jl_sbjkmx where jkpzh = '" + jksh + "'";
    	String sqlInsertZb = "insert into sbdb.sb_jl_sbjkzb_his ( JKPZH, JSJDM, ZRLXDM, ZRRQ, ZRR, SKLXDM, FSDM, LSGXDM, YHDM, YHMC, " +
    			  " ZH, DJZCLXDM, SWJGZZJGDM, ZSSWJGZZJGDM, GJBZHYDM, GKZZJGDM, YSKMDM, YSJCDM, SZDM, LRRQ,"
    			  + " SBRQ, JKSJ, XJRQ, CLBJDM, SJJE, ZYRQ, RKJE, ZWBS, HXRDM, HXRMC, "
    			  + "LRR, BZ, HXRQ, SBBH, JYDZLXDM, SKSSKSRQ, SKSSJSRQ, SJLY, ND, CJRQ, "
    			  + "QXDM, SPHM ) " + 
    			  " values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
      	String sqlInsertMx = "insert into sbdb.sb_jl_sbjkmx_his ( JKPZH, JSJDM, SZSMDM, ZRLXDM, ZRRQ, ZRR, YSKMDM, YSJCDM, KSSL, JSJE, "
      			+ " SJSE, SKSSKSRQ, SKSSJSRQ, RKJE, SBBH, SJFC, QJFC, SWJGZZJGDM, ND, SL, "
      			+ " CJRQ, LRRQ, QXDM )"
      			+ " values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,  ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
      	
    	try {
			conn = DBResource.getConnection(DBResource.DB_SHENBAO);
			//��ѯ
			pstmt = conn.prepareStatement(sqlSelZb);
			rs = pstmt.executeQuery();

			Map zb = new HashMap();
			while(rs.next()) {
				zb.put("JKPZH", rs.getString("JKPZH"));
				zb.put("SKLXDM", rs.getString("SKLXDM"));
				zb.put("JSJDM", rs.getString("JSJDM"));
				zb.put("FSDM", rs.getString("FSDM"));
				zb.put("LSGXDM", rs.getString("LSGXDM"));
				zb.put("YHDM", rs.getString("YHDM"));
				zb.put("YHMC", rs.getString("YHMC"));
				zb.put("ZH", rs.getString("ZH"));
				zb.put("DJZCLXDM", rs.getString("DJZCLXDM"));
				zb.put("SWJGZZJGDM", rs.getString("SWJGZZJGDM"));
				zb.put("ZSSWJGZZJGDM", rs.getString("ZSSWJGZZJGDM"));
				zb.put("GJBZHYDM", rs.getString("GJBZHYDM"));
				zb.put("GKZZJGDM", rs.getString("GKZZJGDM"));
				zb.put("YSKMDM", rs.getString("YSKMDM"));
				zb.put("YSJCDM", rs.getString("YSJCDM"));
				zb.put("SZDM", rs.getString("SZDM"));
				zb.put("LRRQ", rs.getDate("LRRQ"));
				zb.put("SBRQ", rs.getDate("SBRQ"));
				zb.put("JKSJ", rs.getDate("JKSJ"));
				zb.put("XJRQ", rs.getDate("XJRQ"));
				zb.put("CLBJDM", rs.getString("CLBJDM"));
				zb.put("SJJE", new Double(rs.getDouble("SJJE")));
				zb.put("ZYRQ", rs.getDate("ZYRQ"));
				zb.put("RKJE", new Double(rs.getDouble("RKJE")));
				zb.put("ZWBS", rs.getString("ZWBS"));
				zb.put("HXRDM", rs.getString("HXRDM"));
				zb.put("HXRMC", rs.getString("HXRMC"));
				zb.put("LRR", rs.getString("LRR"));
				zb.put("BZ", rs.getString("BZ"));
				zb.put("HXRQ", rs.getString("HXRQ"));
				zb.put("SBBH", rs.getString("SBBH"));
				zb.put("JYDZLXDM", rs.getString("JYDZLXDM"));
				zb.put("SKSSKSRQ", rs.getDate("SKSSKSRQ"));
				zb.put("SKSSJSRQ", rs.getDate("SKSSJSRQ"));
				zb.put("SJLY", rs.getString("SJLY"));
				zb.put("ND", rs.getString("ND"));
				zb.put("CJRQ", rs.getDate("CJRQ"));
				zb.put("QXDM", rs.getString("QXDM"));
				zb.put("SPHM", rs.getString("SPHM"));
			}
			
		
			if(rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		
			pstmt = conn.prepareStatement(sqlInsertZb);
			pstmt.setString(1, (String) zb.get("JKPZH"));
			pstmt.setString(2, (String) zb.get("JSJDM"));
			pstmt.setString(3, "09");
			pstmt.setDate(4, new java.sql.Date(System.currentTimeMillis()));
			pstmt.setString(5, userData.getYhid());
			pstmt.setString(6, (String) zb.get("SKLXDM"));
			pstmt.setString(7, (String) zb.get("FSDM"));
			pstmt.setString(8, (String) zb.get("LSGXDM"));
			pstmt.setString(9, (String) zb.get("YHDM"));
			pstmt.setString(10, (String) zb.get("YHMC"));
			pstmt.setString(11, (String) zb.get("ZH"));
			pstmt.setString(12, (String) zb.get("DJZCLXDM"));
			pstmt.setString(13, (String) zb.get("SWJGZZJGDM"));
			pstmt.setString(14, (String) zb.get("ZSSWJGZZJGDM"));
			pstmt.setString(15, (String) zb.get("GJBZHYDM"));
			pstmt.setString(16, (String) zb.get("GKZZJGDM"));
			pstmt.setString(17, (String) zb.get("YSKMDM"));
			pstmt.setString(18, (String) zb.get("YSJCDM"));
			pstmt.setString(19, (String) zb.get("SZDM"));
			pstmt.setDate(20, (java.sql.Date) zb.get("LRRQ"));
			pstmt.setDate(21, (java.sql.Date) zb.get("SBRQ"));
			pstmt.setDate(22, (java.sql.Date) zb.get("JKSJ"));
			pstmt.setDate(23, (java.sql.Date) zb.get("XJRQ"));
			pstmt.setString(24, (String) zb.get("CLBJDM"));
			pstmt.setDouble(25, ((Double)zb.get("SJJE")).doubleValue());
			pstmt.setDate(26, (java.sql.Date) zb.get("ZYRQ"));
			pstmt.setDouble(27, ((Double)zb.get("RKJE")).doubleValue());
			pstmt.setString(28, (String) zb.get("ZWBS"));
			pstmt.setString(29, (String) zb.get("HXRDM"));
			pstmt.setString(30, (String) zb.get("HXRMC"));
			pstmt.setString(31, (String) zb.get("LRR"));
			pstmt.setString(32, (String) zb.get("BZ"));
			pstmt.setDate(33, (java.sql.Date) zb.get("HXRQ"));
			pstmt.setString(34, (String) zb.get("SBBH"));
			pstmt.setString(35, (String) zb.get("JYDZLXDM"));
			pstmt.setDate(36, (java.sql.Date) zb.get("SKSSKSRQ"));
			pstmt.setDate(37, (java.sql.Date) zb.get("SKSSJSRQ"));
			pstmt.setString(38, (String) zb.get("SJLY"));
			pstmt.setString(39, (String) zb.get("ND"));
			pstmt.setDate(40, (java.sql.Date) zb.get("CJRQ"));
			pstmt.setString(41, (String) zb.get("QXDM"));
			pstmt.setString(42, (String) zb.get("SPHM"));
			
			pstmt.execute();
			
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		
			
			pstmt = conn.prepareStatement(sqlSelMx);
			rs = pstmt.executeQuery();
			List mxList = new ArrayList();
			while(rs.next()) {
				Map mx = new HashMap();
				mx.put("JKPZH", rs.getString("JKPZH"));
				mx.put("JSJDM", rs.getString("JSJDM"));
				mx.put("SZSMDM", rs.getString("SZSMDM"));
				mx.put("YSKMDM", rs.getString("YSKMDM"));
				mx.put("YSJCDM", rs.getString("YSJCDM"));
				mx.put("KSSL", new Double(rs.getDouble(("KSSL"))));
				mx.put("JSJE", new Double(rs.getDouble(("JSJE"))));
				mx.put("SJSE", new Double(rs.getDouble(("SJSE"))));
				mx.put("SKSSKSRQ", rs.getDate("SKSSKSRQ"));
				mx.put("SKSSJSRQ", rs.getDate("SKSSJSRQ")); 	
				mx.put("RKJE", new Double(rs.getDouble(("RKJE"))));
				mx.put("SBBH", rs.getString("SBBH"));
				mx.put("SJFC", new Double(rs.getDouble(("SJFC"))));
				mx.put("QJFC", new Double(rs.getDouble(("QJFC"))));
				mx.put("SWJGZZJGDM", rs.getString("SWJGZZJGDM"));
				mx.put("ND", rs.getString("ND"));
				mx.put("SL", new Double(rs.getDouble(("SL"))));
				mx.put("CJRQ", rs.getDate("CJRQ"));
				mx.put("LRRQ", rs.getDate("LRRQ"));
				mx.put("QXDM", rs.getString("QXDM"));
				
				mxList.add(mx);
			}
		
			if(rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			for(int i=0; i<mxList.size(); i++) {
				Map mx = (Map) mxList.get(i);
				pstmt = conn.prepareStatement(sqlInsertMx);

				pstmt.setString(1, (String) mx.get("JKPZH"));
				pstmt.setString(2, (String) mx.get("JSJDM"));
				pstmt.setString(3, (String) mx.get("SZSMDM"));
				pstmt.setString(4, "09");
				pstmt.setDate(5, new java.sql.Date(System.currentTimeMillis()));
				pstmt.setString(6, userData.getYhid());
				pstmt.setString(7, (String) mx.get("YSKMDM"));
				pstmt.setString(8, (String) mx.get("YSJCDM"));
				pstmt.setDouble(9, ((Double)(mx.get("KSSL"))).doubleValue());
				pstmt.setDouble(10, ((Double)(mx.get("JSJE"))).doubleValue());
				pstmt.setDouble(11, ((Double)(mx.get("SJSE"))).doubleValue());
				pstmt.setDate(12, (java.sql.Date) mx.get("SKSSKSRQ"));
				pstmt.setDate(13, (java.sql.Date) mx.get("SKSSJSRQ"));
				pstmt.setDouble(14, ((Double)(mx.get("RKJE"))).doubleValue());
				pstmt.setString(15, (String) mx.get("SBBH"));
				pstmt.setDouble(16, ((Double)(mx.get("SJFC"))).doubleValue());
				pstmt.setDouble(17, ((Double)(mx.get("QJFC"))).doubleValue());
				pstmt.setString(18, (String) mx.get("SWJGZZJGDM"));
				pstmt.setString(19, (String) mx.get("ND"));
				pstmt.setDouble(20, ((Double)(mx.get("SL"))).doubleValue());
				pstmt.setDate(21, (java.sql.Date) mx.get("CJRQ"));
				pstmt.setDate(22, (java.sql.Date) mx.get("LRRQ"));
				pstmt.setString(23, (String) mx.get("QXDM"));
			
				pstmt.execute();
				if(pstmt != null) {
					try {
						pstmt.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch (BaseException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			DBResource.destroyConnection(conn);
		}
	}


	/**
     * ����������ţ������ݲ�������his
     * @param sbbh
     * @param userData
     */
    public void insertToHisBySbbh(String sbbh, UserData userData) {
    	Connection conn = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	String sqlSelZb = "select * from sbdb.sb_jl_sbjkzb where sbbh = '" + sbbh + "'";
    	String sqlSelMx = "select * from sbdb.sb_jl_sbjkmx where sbbh = '" + sbbh + "'";
    	String sqlInsertZb = "insert into sbdb.sb_jl_sbjkzb_his ( JKPZH, JSJDM, ZRLXDM, ZRRQ, ZRR, SKLXDM, FSDM, LSGXDM, YHDM, YHMC, " +
  			  " ZH, DJZCLXDM, SWJGZZJGDM, ZSSWJGZZJGDM, GJBZHYDM, GKZZJGDM, YSKMDM, YSJCDM, SZDM, LRRQ,"
  			  + " SBRQ, JKSJ, XJRQ, CLBJDM, SJJE, ZYRQ, RKJE, ZWBS, HXRDM, HXRMC, "
  			  + "LRR, BZ, HXRQ, SBBH, JYDZLXDM, SKSSKSRQ, SKSSJSRQ, SJLY, ND, CJRQ, "
  			  + "QXDM, SPHM ) " + 
  			  " values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
    	String sqlInsertMx = "insert into sbdb.sb_jl_sbjkmx_his ( JKPZH, JSJDM, SZSMDM, ZRLXDM, ZRRQ, ZRR, YSKMDM, YSJCDM, KSSL, JSJE, "
    			+ " SJSE, SKSSKSRQ, SKSSJSRQ, RKJE, SBBH, SJFC, QJFC, SWJGZZJGDM, ND, SL, "
    			+ " CJRQ, LRRQ, QXDM ) "
    			+ " values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,  ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
    	
    	try {
			conn = DBResource.getConnection(DBResource.DB_SHENBAO);
			//��ѯ
			pstmt = conn.prepareStatement(sqlSelZb);
			rs = pstmt.executeQuery();
			List zbDatas = new ArrayList();
			while(rs.next()) {

				Map zb = new HashMap();
				zb.put("JKPZH", rs.getString("JKPZH"));
				zb.put("SKLXDM", rs.getString("SKLXDM"));
				zb.put("JSJDM", rs.getString("JSJDM"));
				zb.put("FSDM", rs.getString("FSDM"));
				zb.put("LSGXDM", rs.getString("LSGXDM"));
				zb.put("YHDM", rs.getString("YHDM"));
				zb.put("YHMC", rs.getString("YHMC"));
				zb.put("ZH", rs.getString("ZH"));
				zb.put("DJZCLXDM", rs.getString("DJZCLXDM"));
				zb.put("SWJGZZJGDM", rs.getString("SWJGZZJGDM"));
				zb.put("ZSSWJGZZJGDM", rs.getString("ZSSWJGZZJGDM"));
				zb.put("GJBZHYDM", rs.getString("GJBZHYDM"));
				zb.put("GKZZJGDM", rs.getString("GKZZJGDM"));
				zb.put("YSKMDM", rs.getString("YSKMDM"));
				zb.put("YSJCDM", rs.getString("YSJCDM"));
				zb.put("SZDM", rs.getString("SZDM"));
				zb.put("LRRQ", rs.getDate("LRRQ"));
				zb.put("SBRQ", rs.getDate("SBRQ"));
				zb.put("JKSJ", rs.getDate("JKSJ"));
				zb.put("XJRQ", rs.getDate("XJRQ"));
				zb.put("CLBJDM", rs.getString("CLBJDM"));
				zb.put("SJJE", new Double(rs.getDouble("SJJE")));
				zb.put("ZYRQ", rs.getDate("ZYRQ"));
				zb.put("RKJE", new Double(rs.getDouble("RKJE")));
				zb.put("ZWBS", rs.getString("ZWBS"));
				zb.put("HXRDM", rs.getString("HXRDM"));
				zb.put("HXRMC", rs.getString("HXRMC"));
				zb.put("LRR", rs.getString("LRR"));
				zb.put("BZ", rs.getString("BZ"));
				zb.put("HXRQ", rs.getString("HXRQ"));
				zb.put("SBBH", rs.getString("SBBH"));
				zb.put("JYDZLXDM", rs.getString("JYDZLXDM"));
				zb.put("SKSSKSRQ", rs.getDate("SKSSKSRQ"));
				zb.put("SKSSJSRQ", rs.getDate("SKSSJSRQ"));
				zb.put("SJLY", rs.getString("SJLY"));
				zb.put("ND", rs.getString("ND"));
				zb.put("CJRQ", rs.getDate("CJRQ"));
				zb.put("QXDM", rs.getString("QXDM"));
				zb.put("SPHM", rs.getString("SPHM"));
				
				zbDatas.add(zb);
			}
			if(rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			for(int i=0; i<zbDatas.size(); i++) {
				pstmt = conn.prepareStatement(sqlInsertZb);
				Map zb = (Map) zbDatas.get(i);
				pstmt.setString(1, (String) zb.get("JKPZH"));
				pstmt.setString(2, (String) zb.get("JSJDM"));
				pstmt.setString(3, "09");
				pstmt.setDate(4, new java.sql.Date(System.currentTimeMillis()));
				pstmt.setString(5, userData.getYhid());
				pstmt.setString(6, (String) zb.get("SKLXDM"));
				pstmt.setString(7, (String) zb.get("FSDM"));
				pstmt.setString(8, (String) zb.get("LSGXDM"));
				pstmt.setString(9, (String) zb.get("YHDM"));
				pstmt.setString(10, (String) zb.get("YHMC"));
				pstmt.setString(11, (String) zb.get("ZH"));
				pstmt.setString(12, (String) zb.get("DJZCLXDM"));
				pstmt.setString(13, (String) zb.get("SWJGZZJGDM"));
				pstmt.setString(14, (String) zb.get("ZSSWJGZZJGDM"));
				pstmt.setString(15, (String) zb.get("GJBZHYDM"));
				pstmt.setString(16, (String) zb.get("GKZZJGDM"));
				pstmt.setString(17, (String) zb.get("YSKMDM"));
				pstmt.setString(18, (String) zb.get("YSJCDM"));
				pstmt.setString(19, (String) zb.get("SZDM"));
				pstmt.setDate(20, (java.sql.Date) zb.get("LRRQ"));
				pstmt.setDate(21, (java.sql.Date) zb.get("SBRQ"));
				pstmt.setDate(22, (java.sql.Date) zb.get("JKSJ"));
				pstmt.setDate(23, (java.sql.Date) zb.get("XJRQ"));
				pstmt.setString(24, (String) zb.get("CLBJDM"));
				pstmt.setDouble(25, ((Double)zb.get("SJJE")).doubleValue());
				pstmt.setDate(26, (java.sql.Date) zb.get("ZYRQ"));
				pstmt.setDouble(27, ((Double)zb.get("RKJE")).doubleValue());
				pstmt.setString(28, (String) zb.get("ZWBS"));
				pstmt.setString(29, (String) zb.get("HXRDM"));
				pstmt.setString(30, (String) zb.get("HXRMC"));
				pstmt.setString(31, (String) zb.get("LRR"));
				pstmt.setString(32, (String) zb.get("BZ"));
				pstmt.setDate(33, (java.sql.Date) zb.get("HXRQ"));
				pstmt.setString(34, (String) zb.get("SBBH"));
				pstmt.setString(35, (String) zb.get("JYDZLXDM"));
				pstmt.setDate(36, (java.sql.Date) zb.get("SKSSKSRQ"));
				pstmt.setDate(37, (java.sql.Date) zb.get("SKSSJSRQ"));
				pstmt.setString(38, (String) zb.get("SJLY"));
				pstmt.setString(39, (String) zb.get("ND"));
				pstmt.setDate(40, (java.sql.Date) zb.get("CJRQ"));
				pstmt.setString(41, (String) zb.get("QXDM"));
				pstmt.setString(42, (String) zb.get("SPHM"));
				
				pstmt.execute();
				
				if(pstmt != null) {
					try {
						pstmt.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			
			
			pstmt = conn.prepareStatement(sqlSelMx);
			rs = pstmt.executeQuery();
			List mxList = new ArrayList();
			while(rs.next()) {
				Map mx = new HashMap();

				mx.put("JKPZH", rs.getString("JKPZH"));
				mx.put("JSJDM", rs.getString("JSJDM"));
				mx.put("SZSMDM", rs.getString("SZSMDM"));
				mx.put("YSKMDM", rs.getString("YSKMDM"));
				mx.put("YSJCDM", rs.getString("YSJCDM"));
				mx.put("KSSL", new Double(rs.getDouble(("KSSL"))));
				mx.put("JSJE", new Double(rs.getDouble(("JSJE"))));
				mx.put("SJSE", new Double(rs.getDouble(("SJSE"))));
				mx.put("SKSSKSRQ", rs.getDate("SKSSKSRQ"));
				mx.put("SKSSJSRQ", rs.getDate("SKSSJSRQ")); 	
				mx.put("RKJE", new Double(rs.getDouble(("RKJE"))));
				mx.put("SBBH", rs.getString("SBBH"));
				mx.put("SJFC", new Double(rs.getDouble(("SJFC"))));
				mx.put("QJFC", new Double(rs.getDouble(("QJFC"))));
				mx.put("SWJGZZJGDM", rs.getString("SWJGZZJGDM"));
				mx.put("ND", rs.getString("ND"));
				mx.put("SL", new Double(rs.getDouble(("SL"))));
				mx.put("CJRQ", rs.getDate("CJRQ"));
				mx.put("LRRQ", rs.getDate("LRRQ"));
				mx.put("QXDM", rs.getString("QXDM"));
				
				mxList.add(mx);
			}
			if(rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			for(int i=0; i<mxList.size(); i++) {
				Map mx = (Map) mxList.get(i);
				pstmt = conn.prepareStatement(sqlInsertMx);

				pstmt.setString(1, (String) mx.get("JKPZH"));
				pstmt.setString(2, (String) mx.get("JSJDM"));
				pstmt.setString(3, (String) mx.get("SZSMDM"));
				pstmt.setString(4, "09");
				pstmt.setDate(5, new java.sql.Date(System.currentTimeMillis()));
				pstmt.setString(6, userData.getYhid());
				pstmt.setString(7, (String) mx.get("YSKMDM"));
				pstmt.setString(8, (String) mx.get("YSJCDM"));
				pstmt.setDouble(9, ((Double)(mx.get("KSSL"))).doubleValue());
				pstmt.setDouble(10, ((Double)(mx.get("JSJE"))).doubleValue());
				pstmt.setDouble(11, ((Double)(mx.get("SJSE"))).doubleValue());
				pstmt.setDate(12, (java.sql.Date) mx.get("SKSSKSRQ"));
				pstmt.setDate(13, (java.sql.Date) mx.get("SKSSJSRQ"));
				pstmt.setDouble(14, ((Double)(mx.get("RKJE"))).doubleValue());
				pstmt.setString(15, (String) mx.get("SBBH"));
				pstmt.setDouble(16, ((Double)(mx.get("SJFC"))).doubleValue());
				pstmt.setDouble(17, ((Double)(mx.get("QJFC"))).doubleValue());
				pstmt.setString(18, (String) mx.get("SWJGZZJGDM"));
				pstmt.setString(19, (String) mx.get("ND"));
				pstmt.setDouble(20, ((Double)(mx.get("SL"))).doubleValue());
				pstmt.setDate(21, (java.sql.Date) mx.get("CJRQ"));
				pstmt.setDate(22, (java.sql.Date) mx.get("LRRQ"));
				pstmt.setString(23, (String) mx.get("QXDM"));
				
				pstmt.execute();
				if(pstmt != null) {
					try {
						pstmt.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			
		} catch (BaseException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			DBResource.destroyConnection(conn);
		}
    	
	}

	/**
     * ���ݽɿ����ɾ�����ݿ���ʱ����
     * @param jksh
     */
    public void deleteTempJksByJksh(String jksh) {
    	Connection conn = null;
    	PreparedStatement pstmt = null;
    	String sqlZb = "delete from sbdb.sb_jl_sbjkzb where jkpzh = '" + jksh + "'";
    	String sqlMx = "delete from sbdb.sb_jl_sbjkmx where jkpzh = '" + jksh + "'";
    	try {
			conn = DBResource.getConnection(DBResource.DB_SHENBAO);
			pstmt = conn.prepareStatement(sqlZb);
			pstmt.execute();
			
			pstmt = conn.prepareStatement(sqlMx);
			pstmt.execute();
		} catch (BaseException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			DBResource.destroyConnection(conn);
		}
    }

	/**
     * �����걨���ɾ�����ݿ���ʱ����
     * @param jksh
     */
    public void deleteTempJksBySbbh(String spbh) {
    	Connection conn = null;
    	PreparedStatement pstmt = null;
    	
    	String sqlDelZb = "delete from sbdb.sb_jl_sbjkzb where sbbh = '" + spbh + "'";
    	String sqlDelMx = "delete from sbdb.sb_jl_sbjkmx where sbbh = '" + spbh + "'";
    	try {
			conn = DBResource.getConnection(DBResource.DB_SHENBAO);
			pstmt = conn.prepareStatement(sqlDelZb);
			pstmt.execute();
			
			pstmt = conn.prepareStatement(sqlDelMx);
			pstmt.execute();
		} catch (BaseException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			DBResource.destroyConnection(conn);
		}
    }

    /**
     * ���ɽɿ����ݲ��������ݿ�������
     *
     * @param insbjkzb
     *            �걨�ɿ�����(�Ѿ�������д������,jkpzhû��)
     * @param insbjkmxInfo
     *            �걨�ɿ���ϸList
     * @param _sbbh
     *            �걨���
     * @return �����Ƿ�ɹ�,�����Ҫ���ؽɿ����ݵĻ�:���᷵�ؽɿ�����
     * @throws BaseException
     *             �쳣��Ϣ
     */
    public Object createJkInfor(Sbjkzb insbjkzb, List insbjkmxInfo,
                                String _sbbh) throws BaseException {
        try {
            this.sbbh = _sbbh;
            // 1.���ÿ���ɿ���ϸ���ݵļ���,Ԥ���Ŀ���ں�̨��Ҫ��д����Ϣ
            fillSbjkmxInfo(insbjkzb, insbjkmxInfo);
            // 2.����Ԥ���Ŀ�����ϸ����
            List sbjkmxInfoByyskmdm = slipSbjkmxInfo(insbjkmxInfo);

            // 3.���ղ�ͬ��Ŀ��Ϣ��������ɿ�������д��Ӧ�Ľɿ�ƾ֤����Ϣ
            // ���������ݿⰴ�ղ�ͬ�Ĵ�ӡ��ʶ���ؽɿ�����
            List jkdataList = creatjkData(insbjkzb, sbjkmxInfoByyskmdm);

            this.sbbh = "";
            // ���ش���
            if (isReturnPaymentInfo) {
                return jkdataList;
            }
            else {
                // ����Ҫ���ؽɿ�����
                return Boolean.TRUE;
            }
        }
        catch (Exception ex) {
            throw ExceptionUtil.getBaseException(ex, "�������ݴ���,���뼼��֧����ϵ!");
        }
    }

    /**
     * ���ɽɿ����ݲ��������ݿ�������
     *
     * @param insbjkzb
     *            �걨�ɿ�����(�Ѿ�������д������,jkpzhû��)
     * @param insbjkmxInfo
     *            �걨�ɿ���ϸList
     * @return �����Ƿ�ɹ�,�����Ҫ���ؽɿ����ݵĻ�:���᷵�ؽɿ�����
     * @throws BaseException
     *             �쳣��Ϣ
     */
    private List createSbJkData(Sbjkzb insbjkzb, List insbjkmxInfo) throws
        BaseException {
        try {
            // 1.���ÿ���ɿ���ϸ���ݵļ���,Ԥ���Ŀ���ں�̨��Ҫ��д����Ϣ
            fillSbjkmxInfo(insbjkzb, insbjkmxInfo);
            // 2.����Ԥ���Ŀ�����ϸ����
            List sbjkmxInfoByyskmdm = slipSbjkmxInfo(insbjkmxInfo);

            // 3.���ղ�ͬ��Ŀ��Ϣ��������ɿ�������д��Ӧ�Ľɿ�ƾ֤����Ϣ
            // ���������ݿⰴ�ղ�ͬ�Ĵ�ӡ��ʶ���ؽɿ�����
            List jkdataList = creatjkData(insbjkzb, sbjkmxInfoByyskmdm);

            // ���ش���
            return jkdataList;
        }
        catch (Exception ex) {
            throw ExceptionUtil.getBaseException(ex, "�������ݴ���,���뼼��֧����ϵ!");
        }
    }

    /**
     * ���ɽɿ����ݲ��������ݿ�������
     *
     * @param insbjkzb
     *            �걨�ɿ�����(�Ѿ�������д������,jkpzhû��)
     * @param insbjkmxInfo
     *            �걨�ɿ���ϸList
     * @return �����Ƿ�ɹ�,�����Ҫ���ؽɿ����ݵĻ�:���᷵�ؽɿ�����
     * @throws BaseException
     *             �쳣��Ϣ
     */
    private List createSbJkData_his(Sbjkzb insbjkzb, List insbjkmxInfo ,String zrlxdm ,String zrr) throws
        BaseException {
        try {
            // 1.���ÿ���ɿ���ϸ���ݵļ���,Ԥ���Ŀ���ں�̨��Ҫ��д����Ϣ
            fillSbjkmxInfo(insbjkzb, insbjkmxInfo);
            // 2.����Ԥ���Ŀ�����ϸ����
            List sbjkmxInfoByyskmdm = slipSbjkmxInfo(insbjkmxInfo);

            // 3.���ղ�ͬ��Ŀ��Ϣ��������ɿ�������д��Ӧ�Ľɿ�ƾ֤����Ϣ
            // ���������ݿⰴ�ղ�ͬ�Ĵ�ӡ��ʶ���ؽɿ�����
            List jkdataList = creatjkData_his(insbjkzb, sbjkmxInfoByyskmdm ,zrlxdm ,zrr);

            // ���ش���
            return jkdataList;
        }
        catch (Exception ex) {
            throw ExceptionUtil.getBaseException(ex, "�������ݴ���,���뼼��֧����ϵ!");
        }
    }

    
    /**
     * ���ɽɿ����ݲ��������ݿ�������
     *
     * @param declareInfor
     *            �걨����
     * @param _sbbh
     *            �걨���
     * @return �����Ƿ�ɹ�,�����Ҫ���ؽɿ����ݵĻ�:���᷵�ؽɿ�����
     * @throws BaseException
     *             �����쳣
     */
    public Object createJkInfor(DeclareInfor declareInfor, String _sbbh) throws
        BaseException {
        this.sbbh = _sbbh;
        isReturnPaymentInfo = declareInfor.isIsReturnPaymentInfo();
        printTag = declareInfor.getPrintTag();
        Sbjkzb insbjkzb = declareInfor.getSbjkzb();
        List insbjkmxInfo = declareInfor.getSbjkmxInfo();
        try {
            // 1.���ÿ���ɿ���ϸ���ݵļ���,Ԥ���Ŀ���ں�̨��Ҫ��д����Ϣ
            fillSbjkmxInfo(insbjkzb, insbjkmxInfo);
            // 2.����Ԥ���Ŀ�����ϸ����
            List sbjkmxInfoByyskmdm = slipSbjkmxInfo(insbjkmxInfo);
            // 3.���ղ�ͬ��Ŀ��Ϣ��������ɿ�������д��Ӧ�Ľɿ�ƾ֤����Ϣ
            // ���������ݿⰴ�ղ�ͬ�Ĵ�ӡ��ʶ���ؽɿ�����
            List jkdataList = creatjkData(insbjkzb, sbjkmxInfoByyskmdm);

            this.sbbh = "";
            // ���ش���
            if (isReturnPaymentInfo) {
                // ��Ҫ���ؽɿ�����
                return jkdataList;
            }
            else {
                // ����Ҫ���ؽɿ�����
                return Boolean.TRUE;
            }
        }
        catch (Exception ex) {
            throw ExceptionUtil.getBaseException(ex, "�������ݴ���!");
        }
    }

    /**
     * ���ղ�ֺ���걨�ɿ���ϸ����(���List���ɵ�List,List�е�����ͬ��)��������걨��������
     *
     * @param insbjkzb
     *            �걨�����һЩ������Ϣ
     * @param sbjkmxInfo
     *            ��ֺ���걨��ϸ����
     * @return �걨�ɿ��������ݵ�List
     * @throws java.lang.Exception
     *             �����е��쳣��Ϣ
     */
    private List creatjkData(Sbjkzb insbjkzb, List sbjkmxInfo) throws Exception {
        try {

            switch (printTag) {
                case CodeConstant.PRINT_YPYS: // һƱһ˰
                    return createSkjkzbInfor_onetax(insbjkzb, sbjkmxInfo);
                case CodeConstant.PRINT_YPDS_KM: // һƱ��˰(��Ŀ)
                    return createSkjkzbInfor_mortax(insbjkzb, sbjkmxInfo);
                default: // Ĭ��ΪһƱһ˰
                    return createSkjkzbInfor_onetax(insbjkzb, sbjkmxInfo);
            }
        }
        catch (Exception ex) {
            throw ExceptionUtil.getBaseException(ex, "�������ݴ���,���뼼��֧����ϵ!");
        }

    }
    
    /**
     * ���ղ�ֺ���걨�ɿ���ϸ����(���List���ɵ�List,List�е�����ͬ��)��������걨��������
     *
     * @param insbjkzb
     *            �걨�����һЩ������Ϣ
     * @param sbjkmxInfo
     *            ��ֺ���걨��ϸ����
     * @return �걨�ɿ��������ݵ�List
     * @throws java.lang.Exception
     *             �����е��쳣��Ϣ
     */
    private List creatjkData_his(Sbjkzb insbjkzb, List sbjkmxInfo,String zrlxdm ,String zrr) throws Exception {
        try {

            switch (printTag) {
                case CodeConstant.PRINT_YPYS: // һƱһ˰
                    return createSkjkzbInfor_onetax_his(insbjkzb, sbjkmxInfo ,zrlxdm ,zrr);
                case CodeConstant.PRINT_YPDS_KM: // һƱ��˰(��Ŀ)
                    return createSkjkzbInfor_mortax_his(insbjkzb, sbjkmxInfo ,zrlxdm ,zrr);
                default: // Ĭ��ΪһƱһ˰
                    return createSkjkzbInfor_onetax_his(insbjkzb, sbjkmxInfo ,zrlxdm ,zrr);
            }
        }
        catch (Exception ex) {
            throw ExceptionUtil.getBaseException(ex, "�������ݴ���,���뼼��֧����ϵ!");
        }

    }

    /**
     * ����걨��ϸList�е�Ԥ�㼶�κͿ�Ŀ��Ϣ
     *
     * @param insbjkzb
     *            �ɿ�������Ϣ
     * @param insbjkmxInfo
     *            �ɿ���ϸ��Ϣ
     * @throws Exception
     *             �����쳣
     */
    private void fillSbjkmxInfo(Sbjkzb insbjkzb, List insbjkmxInfo) throws
        Exception {
        Sbjkmx sbjkmxTmp = null;
        String qylxdm = insbjkzb.getDjzclxdm();
        String gjbzhydm = insbjkzb.getGjbzhydm();
        String sklxdm = insbjkzb.getSklxdm();
        for (int i = 0; i < insbjkmxInfo.size(); i++) {
            sbjkmxTmp = (Sbjkmx) insbjkmxInfo.get(i);
            try {
                fillSbjkmx(sbjkmxTmp, insbjkzb);
            }
            catch (Exception ex) {
                throw ExceptionUtil.getBaseException(ex, "��ȡԤ���Ŀʧ��!");
            }

        }
    }

    /**
     * �ڽɿ���ϸ���������Ԥ�㼶�κ�Ԥ���Ŀ��Ϣ
     *
     * @param sbjkmx
     *            �����Ľɿ���ϸ
     * @param insbjkzb
     *            �걨�ɿ�����
     * @throws BaseException
     *             �����쳣
     */
    private void fillSbjkmx(Sbjkmx sbjkmx, Sbjkzb insbjkzb) throws
        BaseException {
        // 3.��д˰������ʱ��
        if (sbjkmx.getSkssksrq() == null && sbjkmx.getSkssjsrq() == null) {
            Map map = Skssrq.getSksssq(sbjkmx.getJsjdm(), sbjkmx.getSzsmdm(),
                                       insbjkzb.getDjzclxdm(), insbjkzb
                                       .getSklxdm(), sbjkmx.getZqlxdm(),
                                       insbjkzb.getLrrq(), sbjkmx.getSjse(),
                                       sbjkmx.getKssl(), sbjkmx
                                       .getJsje());
            // ӡ��˰�����걨��Ŀ modified by Junbing Tu 2014.0
            //1.˰������Ϊ��ί�д�������ӡ��˰������˰��ʵ�ɽ�� ѡ�С��Ƿ񰴴Ρ�ʱ��˰����������Ϊ����; ��ѡ���Ƿ񰴴Ρ�ʱ��˰����������Ϊ����������ֹ���ڣ�
            //2.˰������Ϊ������˰���  ˰����������Ϊ����������ֹ���ڡ�
            //if((insbjkzb.getSklxdm() != null && insbjkzb.getSklxdm().equals(CodeConstant.SKLXDM_SDJJ)) 
            //		&& (sumhjje!= null && (sumhjje.compareTo(CodeConstant.WSSB_YSSB_SJSE_200000) >-1) ))
            //{
            if((insbjkzb.getSklxdm() != null && insbjkzb.getSklxdm().equals(CodeConstant.SKLXDM_SDJJ)) 
                		&& (sbjkmx.getSbfs() != null && sbjkmx.getSbfs().equals(CodeConstant.WSSB_YSSB_ZQLX_08) ))
               {
            	sbjkmx.setSkssksrq( (Timestamp) this.monthSkssrq(new Date()).get(Skssrq.SKSSKSRQ));
                sbjkmx.setSkssjsrq( (Timestamp) this.monthSkssrq(new Date()).get(Skssrq.SKSSJSRQ));
            }
            else
            {
            	sbjkmx.setSkssksrq( (Timestamp) map.get(Skssrq.SKSSKSRQ));
                sbjkmx.setSkssjsrq( (Timestamp) map.get(Skssrq.SKSSJSRQ));
            }
        }
        // ���˰�������Ľ�ֹ���ڻ���Ϊ�գ���Ĭ��Ϊ���µ����һ��
        if (sbjkmx.getSkssjsrq() == null) {
            // ˰��������ֹ���ڣ�Ĭ��Ϊ�ϸ��µ����һ��
            sbjkmx.setSkssjsrq( (Timestamp) Skssrq.monthSkssrq(new Date()).get(
                Skssrq.SKSSJSRQ));
        }
        if (sbjkmx.getYsjcdm() == null) { // ���Ԥ�㼶����δ��ֵ����ִ�в���Ԥ�㼶��
            try {
                // ����Ĭ��Ԥ�㼶��Ϊ���ط�����
                Ysjc ysjc = Ysjc.getYsjc(Ysjc.YSJCDM_DF);
                // ����˰�ѹ���ӿڵõ�Ԥ�㼶��
                com.ttsoft.bjtax.sfgl.proxy.ServiceProxy sfServiceProxy = new
                    com.ttsoft.bjtax.sfgl.proxy.ServiceProxy();
                com.ttsoft.bjtax.sfgl.common.model.Ysjc sfysjc = sfServiceProxy.
                    getYsjcInfo(insbjkzb.getJsjdm(), sbjkmx
                                .getSzsmdm(), sbjkmx.getSkssjsrq());
                if (sfysjc != null) {
                    ysjc = new Ysjc(sfysjc.getYsjcdm(), sfysjc.getYsjcmc());
                }

                sbjkmx.setYsjcdm(ysjc.getYsjcdm()); // ��ֵ
            }
            catch (Exception ex) {
                throw new ApplicationException(ex.getMessage());
            }
        }
        // ����Ԥ���Ŀ���벢���
        // ���üƻ�ӿڣ���ȡԤ���Ŀ
        Yskm yskm = null;
        try {
            yskm = JKAdapter.getInstance().getYskm(sbjkmx.getSzsmdm(),
                insbjkzb.getDjzclxdm(), insbjkzb.getGjbzhydm(),
                sbjkmx.getYsjcdm());
        }
        catch (ZwclException ex) {
            throw new ApplicationException(ex.getMessage());
        }
        if (yskm == null) {
            throw new ApplicationException("��ȡԤ���Ŀʧ��!");
        }

        if (sbjkmx.getYskmdm() == null) { // ���Ԥ���Ŀ������δ��ֵ���򽫲�ѯ�����ֵ����ϸ����Ķ�Ӧ����
            sbjkmx.setYskmdm(yskm.getYskmdm()); // ��ֵ
        }

        // �����
        sbjkmx.setRkje(sbjkmx.getSjse());
        sbjkmx.setSbbh(sbbh); // �����걨���
        if (sbjkmx.getYsjcdm().equals("21")) { // ����ǵط���
            sbjkmx.setSjfc(getFc(sbjkmx.getSjse(), yskm.getSjfcbl())); // �����оּ��ֳɽ��
            sbjkmx.setQjfc(getFc(sbjkmx.getSjse(), yskm.getQxfcbl())); // �������ؼ��ֳɽ��
        }
    }

    /**
     * ����Ԥ���Ŀ��Ϣ���sbjkmxInfoΪ���List(ÿ��List�е�sbjkmxʵ������ͬԤ���Ŀ��) ��ɵ�Ƕ��List
     *
     * @param sbjkmxInfo
     *            �걨�ɿ���ϸ��Ϣ(����sbjkmxʵ����List)
     * @return List ��Ա����ΪList
     */
    private List slipSbjkmxInfo(List sbjkmxInfo) {
        List splitsbjkmx = new ArrayList();
        List paramList = new ArrayList();

        // ����Ԥ���Ŀ�����˰��������ʼ�ͽ�������ֽɿ���ϸ����
        paramList.add("getYskmdm");
        paramList.add("getSkssksrq");
        paramList.add("getSkssjsrq");
        try {
            splitsbjkmx = FindObjInList.splitListByParam(sbjkmxInfo, Sbjkmx.class,
                paramList);
        }
        catch (Exception ex) {
            Debug.out("����걨����ʧ��!");
            splitsbjkmx = null;
        }
        return splitsbjkmx;
    }

    /**
     * ���걨����,�걨��ϸ���ݲ������ݿ�
     *
     * @param sbjkzbList
     *            �걨�ɿ�������ϢList�е�ֵ����Ϊ:Sbjkzb
     * @param sbjkmxInfoList
     *            �걨��ϸ��Ϣ:List�еĶ�����List��ʵ��(����ͬ��Ŀ��Ϣ��
     *            ��ϸ��List):ÿ��subList�еĶ������Sbjkmx
     * @param conn
     *            ���ݿ�����
     * @throws BaseException
     *             �쳣��Ϣ
     */
    public void insertSbjkData(List sbjkzbList, List sbjkmxInfoList,
                               Connection conn) throws BaseException {
        // ORʵ��
        ORManager orManager = null;
        // ѭ�������������ϸ����
        try {
            // ��� ORManager
            orManager = DBResource.getORManager(DBResource.OR_SHENBAO);

            // ������������
            Sbjkzb sbjkzb;
            for (Iterator sbjkzbs = sbjkzbList.iterator(); sbjkzbs.hasNext(); ) {
                sbjkzb = (Sbjkzb) sbjkzbs.next();
                sbjkzb.setSphm(sbjkzb.getJkpzh());
                orManager.makePersistent(SESSION_ID, conn, sbjkzb);
            }

            // ������ϸ����
            List sbjkmxList = null;
            for (Iterator sbjkmxLists = sbjkmxInfoList.iterator();
                 sbjkmxLists.hasNext(); ) {
                // ͬһ��Ŀ����ϸlist
                for (Iterator sbjkmxs = ( (List) sbjkmxLists.next()).iterator();
                     sbjkmxs.hasNext(); ) {
                    // ͬһ��Ŀ�ж�����ϸ
                    orManager.makePersistent(SESSION_ID, conn, sbjkmxs.next());
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex, "�������ݴ���!");
        }
    }

    /**
     * ���걨����,�걨��ϸ���ݲ������ݿ�
     *
     * @param sbjkzbList
     *            �걨�ɿ�������ϢList�е�ֵ����Ϊ:Sbjkzb
     * @param sbjkmxInfoList
     *            �걨��ϸ��Ϣ:List�еĶ�����List��ʵ��(����ͬ��Ŀ��Ϣ��
     *            ��ϸ��List):ÿ��subList�еĶ������Sbjkmx
     * @param conn
     *            ���ݿ�����
     * @throws BaseException
     *             �쳣��Ϣ
     */
    public void insertSbjkData_his(List sbjkzbList, List sbjkmxInfoList,
                               Connection conn ,String zrlxdm ,String zrr) throws BaseException {
        // ORʵ��
        ORManager orManager = null;
        // ѭ�������������ϸ����
        try {
            // ��� ORManager
            orManager = DBResource.getORManager(DBResource.OR_SHENBAO);

            Timestamp now = new Timestamp((new java.util.Date()).getTime());
    	    String[] sbjkZbNames = { "jkpzh", "sklxdm", "jsjdm", "fsdm", "lsgxdm",
    				"yhdm", "yhmc", "zh", "djzclxdm", "swjgzzjgdm", "zsswjgzzjgdm",
    				"gjbzhydm", "gkzzjgdm", "yskmdm", "ysjcdm", "szdm", "lrrq",
    				"sbrq", "jksj", "xjrq", "clbjdm", "sjje", "zyrq", "rkje",
    				"zwbs", "hxrdm", "hxrmc", "lrr", "bz", "hxrq", "sbbh",
    				"jydzlxdm", "skssksrq", "skssjsrq", "sjly", "nd", "cjrq",
    				"qxdm", "sphm" };

    		String[] sbjkmxNames = { "szsmdm", "jkpzh", "jsjdm", "yskmdm",
    				"ysjcdm", "kssl", "jsje", "sjse", "skssksrq", "skssjsrq",
    				"rkje", "sbbh", "sjfc", "qjfc", "swjgzzjgdm", "nd", "sl",
    				"cjrq", "lrrq", "qxdm" };
    	    
            if(zrr==null||"".equals(zrr))
            {
            	zrr="system";
            }
            
            // ������������
            Sbjkzb sbjkzb;
            for (Iterator sbjkzbs = sbjkzbList.iterator(); sbjkzbs.hasNext(); ) {
            	sbjkzb = (Sbjkzb) sbjkzbs.next();
            	sbjkzb.setSphm(sbjkzb.getJkpzh());
            	
            	//��sbjkzbתhis
    	        Sbjkzb_His sbjkzbHis_temp = new Sbjkzb_His();
    	        sbjkzbHis_temp.copyFrom(sbjkzb);

    			sbjkzbHis_temp.setZrlxdm(zrlxdm);
    			sbjkzbHis_temp.setZrrq(now);
    			sbjkzbHis_temp.setZrr(zrr);

                orManager.makePersistent(SESSION_ID, conn, sbjkzbHis_temp);
            }

            // ������ϸ����
            List sbjkmxList = null;
            for (Iterator sbjkmxLists = sbjkmxInfoList.iterator();
                 sbjkmxLists.hasNext(); ) {
                // ͬһ��Ŀ����ϸlist
                for (Iterator sbjkmxs = ( (List) sbjkmxLists.next()).iterator();
                     sbjkmxs.hasNext(); ) {
                    // ͬһ��Ŀ�ж�����ϸ
                	
                	Sbjkmx sbjkmx = (Sbjkmx) sbjkmxs.next();
                	Sbjkmx_His sbjkmx_His = new Sbjkmx_His();
                	sbjkmx_His.copyFrom(sbjkmx);
                	
                	sbjkmx_His.setZrlxdm(zrlxdm);
                	sbjkmx_His.setZrrq(now);
                	sbjkmx_His.setZrr(zrr);
                    orManager.makePersistent(SESSION_ID, conn, sbjkmx_His);
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex, "�������ݴ���!");
        }
    }
    
    
    // �԰���Ԥ���Ŀ��ֺ��ÿ����ϸ���ɽɿ��������ݲ���д�ɿ�ƾ֤��һƱһ˰���
    // �������ɵĽɿ�����Ϣ
    private List createSkjkzbInfor_onetax(Sbjkzb insbjkzb, List sbjkmxInfo) throws
        BaseException {
        String sequence = null; // �ɿ�ƾ֤���е����к�
        // �������ݿ�����
        Connection conn = null;

        // �õ���ǰ��������걨�ɿ������е������ˮ��.
        try {
            // �õ�¼�����ڵ����£�6λ��
            SimpleDateFormat simpleDataFormat = new SimpleDateFormat("yyyyMM");
            String yyyyMM = simpleDataFormat.format(insbjkzb.getLrrq());
            SimpleDateFormat simpleDataFormats = new SimpleDateFormat("yyMM");
            String yyMM = simpleDataFormats.format(insbjkzb.getLrrq());
            // NumberFormat nmbFormat = new DecimalFormat("000");

            // ѭ���е���ʱ����
            List sbjkzbList = new ArrayList(); // �����Ľɿ�����List
            List jkInforList = new ArrayList(); // ���صĽɿ�������List
            DeclareInfor jkInfor = null; // һ���ɿ�����
            List splitsbjkmxList = null; // ��4����Ŀ˰��ʱ�ڲ�ֺ�Ķ�����ϸ����list

            Sbjkmx sbjkmxtmp = null;
            Sbjkzb sbjkzbtmp = null;
            String jkpzh = null;
            List tmpSbjkmxList; // ��4����Ŀ˰��ʱ�ڲ�ֺ��һ����ϸ����list
            BigDecimal sjjehe; // ʵ�ɽ��
            // ������ݿ�����
            conn = DBResource.getConnection(DBResource.DB_SHENBAO);
            for (int i = 0; i < sbjkmxInfo.size(); i++) {
                // ����4����ϸ���
                splitsbjkmxList = splitList( (List) sbjkmxInfo.get(i),
                                            SPLITNUM_SM);
                // �Բ�ֺ��ÿ����ϸ����һ���ɿ��������ݲ�������ϸ��jkpzh
                for (int k = 0; k < splitsbjkmxList.size(); k++) {
                    // ȡ�õ�ǰ���������ʹ�õ�������
                    // sequence = nmbFormat.format(
                    // Integer.parseInt(TrancProxy.getSequence(insbjkzb.getJsjdm(),
                    // yyyyMM)) + 1);

                    String xh = TrancProxy.getSequence(insbjkzb.getJsjdm(),
                        yyyyMM);
                    // �������ASCII��
                    String xhAscii = TinyTools.stringToASCII(TinyTools.
                        asciiToString(Integer.parseInt(xh) + 1));
                    // ��ʽ��Ϊ"000"��ʽ
                    sequence = TinyTools.xhFormat(TinyTools.asciiToString(
                        Integer.parseInt(xhAscii)));

                    jkpzh = insbjkzb.getJsjdm() + yyMM + sequence + "0";
                    tmpSbjkmxList = (List) splitsbjkmxList.get(k);
                    sbjkzbtmp = createSbjkzb(tmpSbjkmxList, insbjkzb, jkpzh,
                                             conn);
                    sbjkzbList.add(sbjkzbtmp);
                    jkInfor = new DeclareInfor(sbjkzbtmp, tmpSbjkmxList);
                    jkInforList.add(jkInfor);
                }
            }
            // �����ɵ����ݲ������ݿ���
            insertSbjkData(sbjkzbList, sbjkmxInfo, conn);
            return jkInforList;
        }
        catch (Exception ex) {
            throw ExceptionUtil.getBaseException(ex, "����ɿ�����ʧ��!");
        }
        finally {
            DBResource.destroyConnection(conn);
        }
    }

    /**
     * @Description: TODO 
     * @param insbjkzb
     * @param sbjkmxInfo
     * @return
     * @throws BaseException
     */
    private List createSkjkzbInfor_onetax_his(Sbjkzb insbjkzb, List sbjkmxInfo ,String zrlxdm ,String zrr) throws
    BaseException {
    String sequence = null; // �ɿ�ƾ֤���е����к�
    // �������ݿ�����
    Connection conn = null;

    // �õ���ǰ��������걨�ɿ������е������ˮ��.
    try {
        // �õ�¼�����ڵ����£�6λ��
        SimpleDateFormat simpleDataFormat = new SimpleDateFormat("yyyyMM");
        String yyyyMM = simpleDataFormat.format(insbjkzb.getLrrq());
        SimpleDateFormat simpleDataFormats = new SimpleDateFormat("yyMM");
        String yyMM = simpleDataFormats.format(insbjkzb.getLrrq());
        // NumberFormat nmbFormat = new DecimalFormat("000");

        // ѭ���е���ʱ����
        List sbjkzbList = new ArrayList(); // �����Ľɿ�����List
        List jkInforList = new ArrayList(); // ���صĽɿ�������List
        DeclareInfor jkInfor = null; // һ���ɿ�����
        List splitsbjkmxList = null; // ��4����Ŀ˰��ʱ�ڲ�ֺ�Ķ�����ϸ����list

        Sbjkmx sbjkmxtmp = null;
        Sbjkzb sbjkzbtmp = null;
        String jkpzh = null;
        List tmpSbjkmxList; // ��4����Ŀ˰��ʱ�ڲ�ֺ��һ����ϸ����list
        BigDecimal sjjehe; // ʵ�ɽ��
        // ������ݿ�����
        conn = DBResource.getConnection(DBResource.DB_SHENBAO);
        for (int i = 0; i < sbjkmxInfo.size(); i++) {
            // ����4����ϸ���
            splitsbjkmxList = splitList( (List) sbjkmxInfo.get(i),
                                        SPLITNUM_SM);
            // �Բ�ֺ��ÿ����ϸ����һ���ɿ��������ݲ�������ϸ��jkpzh
            for (int k = 0; k < splitsbjkmxList.size(); k++) {
                // ȡ�õ�ǰ���������ʹ�õ�������
                // sequence = nmbFormat.format(
                // Integer.parseInt(TrancProxy.getSequence(insbjkzb.getJsjdm(),
                // yyyyMM)) + 1);

                String xh = TrancProxy.getSequence(insbjkzb.getJsjdm(),
                    yyyyMM);
                // �������ASCII��
                String xhAscii = TinyTools.stringToASCII(TinyTools.
                    asciiToString(Integer.parseInt(xh) + 1));
                // ��ʽ��Ϊ"000"��ʽ
                sequence = TinyTools.xhFormat(TinyTools.asciiToString(
                    Integer.parseInt(xhAscii)));

                jkpzh = insbjkzb.getJsjdm() + yyMM + sequence + "0";
                tmpSbjkmxList = (List) splitsbjkmxList.get(k);
                sbjkzbtmp = createSbjkzb(tmpSbjkmxList, insbjkzb, jkpzh,
                                         conn);
                sbjkzbList.add(sbjkzbtmp);
                jkInfor = new DeclareInfor(sbjkzbtmp, tmpSbjkmxList);
                jkInforList.add(jkInfor);
            }
        }
        // �����ɵ����ݲ������ݿ���
        insertSbjkData_his(sbjkzbList, sbjkmxInfo, conn ,zrlxdm ,zrr);
        return jkInforList;
    }
    catch (Exception ex) {
        throw ExceptionUtil.getBaseException(ex, "����ɿ�����ʧ��!");
    }
    finally {
        DBResource.destroyConnection(conn);
    }
}
    
    // �԰���Ԥ���Ŀ��ֺ��ÿ����ϸ���ɽɿ��������ݲ���д�ɿ�ƾ֤��һƱ��˰���
    // �������ɵĽɿ�����Ϣ
    private List createSkjkzbInfor_mortax(Sbjkzb insbjkzb, List sbjkmxInfo) throws
        BaseException {
        String sequence = null; // �ɿ�ƾ֤���е����к�
        // �������ݿ�����
        Connection conn = null;

        // �õ���ǰ��������걨�ɿ������е������ˮ��.
        try {
            // �õ�¼�����ڵ����£�6λ��
            SimpleDateFormat simpleDataFormat = new SimpleDateFormat("yyyyMM");
            String yyyyMM = simpleDataFormat.format(insbjkzb.getLrrq());
            SimpleDateFormat simpleDataFormats = new SimpleDateFormat("yyMM");
            String yyMM = simpleDataFormats.format(insbjkzb.getLrrq());
            // NumberFormat nmbFormat = new DecimalFormat("000");

            // ѭ���е���ʱ����
            List sbjkzbList = new ArrayList(); // �����Ľɿ�����List
            List jkInforList = new ArrayList(); // ���صĽɿ�������List(����Ʊ������)
            List onePageJkInfor = null; // һ��Ʊ������
            DeclareInfor jkInfor = null; // һ���ɿ�����
            List splitsbjkmxList = null; // ��4����Ŀ˰��ʱ�ڲ�ֺ�Ķ�����ϸ����list
            Sbjkzb sbjkzbtmp = null;
            String jkpzh = null;
            List tmpSbjkmxList; // ��4����Ŀ˰��ʱ�ڲ�ֺ��һ����ϸ����list
            BigDecimal sjjehe; // ʵ�ɽ��

            // ��ÿ��4��(������4��)��ͬ�Ŀ�Ŀ˰�������з���
            List eachfourList = splitList(sbjkmxInfo, SPLITNUM_SM);
            // ������ݿ�����
            conn = DBResource.getConnection(DBResource.DB_SHENBAO);

            for (int i = 0; i < eachfourList.size(); i++) {
                // ȡ�õ�ǰ���������ʹ�õ�������
                // sequence = nmbFormat.format(
                // Integer.parseInt(TrancProxy.getSequence(insbjkzb.getJsjdm(),
                // yyyyMM)) + 1);

                String xh = TrancProxy.getSequence(insbjkzb.getJsjdm(), yyyyMM);
                // �������ASCII��
                String xhAscii = TinyTools.stringToASCII(TinyTools.
                    asciiToString(Integer.parseInt(xh) + 1));
                // ��ʽ��Ϊ"000"��ʽ
                sequence = TinyTools.xhFormat(TinyTools.asciiToString(Integer.
                    parseInt(xhAscii)));

                splitsbjkmxList = (List) eachfourList.get(i);
                onePageJkInfor = new ArrayList();
                // �Բ�ֺ��ÿ����ϸ����һ���ɿ��������ݲ�������ϸ��jkpzh
                for (int k = 0; k < splitsbjkmxList.size(); k++) {
                    int mark = k + 1;
                    jkpzh = insbjkzb.getJsjdm() + yyMM + sequence + mark; // һƱ��˰�����һλ��1��ʼ
                    tmpSbjkmxList = (List) splitsbjkmxList.get(k);
                    // ����һ���µ�sbjkzb����д�ɿ�ƾ֤��
                    sbjkzbtmp = createSbjkzb(tmpSbjkmxList, insbjkzb, jkpzh,
                                             conn);
                    sbjkzbList.add(sbjkzbtmp);
                    jkInfor = new DeclareInfor(sbjkzbtmp, tmpSbjkmxList);
                    onePageJkInfor.add(jkInfor);
                }
                jkInforList.add(onePageJkInfor);
            }
            insertSbjkData(sbjkzbList, sbjkmxInfo, conn);
            return jkInforList;
        }
        catch (Exception ex) {
            throw ExceptionUtil.getBaseException(ex, "����ɿ�����ʧ��!");
        }
        finally {
            DBResource.destroyConnection(conn);
        }
    }

    /**
     * @Description: TODO ��ʷ��
     * @param insbjkzb
     * @param sbjkmxInfo
     * @return
     * @throws BaseException
     */
    private List createSkjkzbInfor_mortax_his(Sbjkzb insbjkzb, List sbjkmxInfo,String zrlxdm ,String zrr) throws
    BaseException {
    String sequence = null; // �ɿ�ƾ֤���е����к�
    // �������ݿ�����
    Connection conn = null;

    // �õ���ǰ��������걨�ɿ������е������ˮ��.
    try {
        // �õ�¼�����ڵ����£�6λ��
        SimpleDateFormat simpleDataFormat = new SimpleDateFormat("yyyyMM");
        String yyyyMM = simpleDataFormat.format(insbjkzb.getLrrq());
        SimpleDateFormat simpleDataFormats = new SimpleDateFormat("yyMM");
        String yyMM = simpleDataFormats.format(insbjkzb.getLrrq());
        // NumberFormat nmbFormat = new DecimalFormat("000");

        // ѭ���е���ʱ����
        List sbjkzbList = new ArrayList(); // �����Ľɿ�����List
        List jkInforList = new ArrayList(); // ���صĽɿ�������List(����Ʊ������)
        List onePageJkInfor = null; // һ��Ʊ������
        DeclareInfor jkInfor = null; // һ���ɿ�����
        List splitsbjkmxList = null; // ��4����Ŀ˰��ʱ�ڲ�ֺ�Ķ�����ϸ����list
        Sbjkzb sbjkzbtmp = null;
        String jkpzh = null;
        List tmpSbjkmxList; // ��4����Ŀ˰��ʱ�ڲ�ֺ��һ����ϸ����list
        BigDecimal sjjehe; // ʵ�ɽ��

        // ��ÿ��4��(������4��)��ͬ�Ŀ�Ŀ˰�������з���
        List eachfourList = splitList(sbjkmxInfo, SPLITNUM_SM);
        // ������ݿ�����
        conn = DBResource.getConnection(DBResource.DB_SHENBAO);

        for (int i = 0; i < eachfourList.size(); i++) {
            // ȡ�õ�ǰ���������ʹ�õ�������
            // sequence = nmbFormat.format(
            // Integer.parseInt(TrancProxy.getSequence(insbjkzb.getJsjdm(),
            // yyyyMM)) + 1);

            String xh = TrancProxy.getSequence(insbjkzb.getJsjdm(), yyyyMM);
            // �������ASCII��
            String xhAscii = TinyTools.stringToASCII(TinyTools.
                asciiToString(Integer.parseInt(xh) + 1));
            // ��ʽ��Ϊ"000"��ʽ
            sequence = TinyTools.xhFormat(TinyTools.asciiToString(Integer.
                parseInt(xhAscii)));

            splitsbjkmxList = (List) eachfourList.get(i);
            onePageJkInfor = new ArrayList();
            // �Բ�ֺ��ÿ����ϸ����һ���ɿ��������ݲ�������ϸ��jkpzh
            for (int k = 0; k < splitsbjkmxList.size(); k++) {
                int mark = k + 1;
                jkpzh = insbjkzb.getJsjdm() + yyMM + sequence + mark; // һƱ��˰�����һλ��1��ʼ
                tmpSbjkmxList = (List) splitsbjkmxList.get(k);
                // ����һ���µ�sbjkzb����д�ɿ�ƾ֤��
                sbjkzbtmp = createSbjkzb(tmpSbjkmxList, insbjkzb, jkpzh,
                                         conn);
                sbjkzbList.add(sbjkzbtmp);
                jkInfor = new DeclareInfor(sbjkzbtmp, tmpSbjkmxList);
                onePageJkInfor.add(jkInfor);
            }
            jkInforList.add(onePageJkInfor);
        }
        insertSbjkData_his(sbjkzbList, sbjkmxInfo, conn ,zrlxdm ,zrr);
        return jkInforList;
    }
    catch (Exception ex) {
        throw ExceptionUtil.getBaseException(ex, "����ɿ�����ʧ��!");
    }
    finally {
        DBResource.destroyConnection(conn);
    }
}
    
    // �õ���ϸ���ݴ���һ��sbjkzb����
    private Sbjkzb createSbjkzb(List sbjkmxList, Sbjkzb sbjkzb, String jkpzh,
                                Connection conn) throws BaseException {
        Sbjkzb cloneSbjkzb = sbjkzb.getCopy();
        cloneSbjkzb.setJkpzh(jkpzh);
        cloneSbjkzb.setZyrq(cloneSbjkzb.getSbrq());
        Sbjkmx sbjkmxtmp = null;
        BigDecimal sjjehe = new BigDecimal(0.00);
        for (int j = 0; j < sbjkmxList.size(); j++) {
            sbjkmxtmp = (Sbjkmx) sbjkmxList.get(j);
            sbjkmxtmp.setJkpzh(jkpzh);
            if (sbjkmxtmp.getSjse() != null) {
                sjjehe = sjjehe.add(sbjkmxtmp.getSjse()); // ͳ��ʵ�ɽ��ĺϼ�
            }
        }
        // ����ʵ�ɽ��������ֶ�����
        Debug.out("ʵ�ɽ��ϼ�:" + sjjehe);
        cloneSbjkzb.setSjje(sjjehe);
        cloneSbjkzb.setYsjcdm(sbjkmxtmp.getYsjcdm());
        // cloneSbjkzb.setYsjcmc(sbjkmxtmp.getYsjcmc());
        cloneSbjkzb.setYskmdm(sbjkmxtmp.getYskmdm());
        // cloneSbjkzb.setYskmmc(sbjkmxtmp.getYskmmc());
        cloneSbjkzb.setSzdm(sbjkmxtmp.getSzsmdm().substring(0, 2)); // ��ϸ������˰���ֶΣ�ȡ˰Ŀǰ��λ
        // cloneSbjkzb.setSzmc(sbjkmxtmp.getSzmc());
        cloneSbjkzb.setRkje(sjjehe);
        cloneSbjkzb.setSbbh(sbbh); // �����걨���
        cloneSbjkzb.setZwbs(ZWBS);
        // ����걨�ɿ�����˰������ʱ�� add by wanghw
        cloneSbjkzb.setSkssksrq(sbjkmxtmp.getSkssksrq());
        cloneSbjkzb.setSkssjsrq(sbjkmxtmp.getSkssjsrq());
        // �޽�����
        String smdm = sbjkmxtmp.getSzsmdm(); // ˰��˰Ŀ����
        String djzclx = sbjkzb.getDjzclxdm(); // �Ǽ�ע�����ʹ���
        SimpleDateFormat simpleDataFormat = new SimpleDateFormat("yyyyMMdd");
        String rq = simpleDataFormat.format(new Date()); // ��ǰ����:YYYYMMdd
        BigDecimal sjse = sbjkmxtmp.getSjse();
        String sklxdm = sbjkzb.getSklxdm(); // ˰�����ʹ���
        
        String zqlxsbfs = sbjkmxtmp.getSbfs();
        if (cloneSbjkzb.getXjrq() == null) { // ����޽�����Ϊ��
            cloneSbjkzb.setXjrq(getZqzzrq(smdm, djzclx, rq, sjse, sklxdm, zqlxsbfs, conn)); // �޽�����
        }
        // �õ����������ĵǼ���Ϣ����д����
        String jsjdm = sbjkmxtmp.getJsjdm(); // ���������
        if (cloneSbjkzb.getGkzzjgdm() == null ||
            cloneSbjkzb.getGkzzjgdm().equals("")) {
            Swjgzzjg swjg = getSkgk(jsjdm, cloneSbjkzb.getZsswjgzzjgdm(), conn);
            cloneSbjkzb.setGkzzjgdm(swjg.getGkjhh()); // ������֯��������
            // cloneSbjkzb.setGkzzjgmc(swjg.getSkgk()); //��������
        }

        return cloneSbjkzb;
    }

    // add by 2003-09-28 wanghw
    /**
     * ����˰��˰Ŀ����͵�ǰ���£���ȡ�����������ж�ѽ��������ֹ���� ������ڶ������������ļ�¼��ȡ��һ����
     * ���û�����������ļ�¼���򷵻ص�ǰ�µ�15��Ϊ�޽�����
     *
     * @param smdm
     *            ˰��˰Ŀ����
     * @param djzclx
     *            �Ǽ�ע�����ʹ���
     * @param rq
     *            ����
     * @param conn
     *            ���ݿ�����
     * @return Timestamp ������ֹ����
     * @throws BaseException
     */
    private Timestamp getZqzzrq(String smdm, String djzclx, String rq,BigDecimal sjse, String sklxdm,String zqlxsbfs,
                                Connection conn) throws BaseException {
        GregorianCalendar calendars = new GregorianCalendar();
        int months = calendars.get(calendars.MONTH);
        int years = calendars.get(calendars.YEAR);
        int days = 15;
        Timestamp time = new Timestamp(new GregorianCalendar(years, months,
            days).getTime().getTime());
        // Ĭ���޽����ڵ���15��
        Zqrl zqrl = new Zqrl();
        zqrl.setZqzzrq(time);
        try {
            // ��� ORManager
            ORManager orManager = DBResource.getORManager(DBResource.OR_SHENBAO);
            // ��ѯ����������
            ArrayList zqrlRs = new ArrayList();
            /*******************************************************************
             * modified by Daniel ,����������һ�� String sqlWhere = "(ZQLXDM >= '0' AND
             * ZQQSRQ <= to_date('" + rq + "','yyyymmdd') AND SZSMDM = '" + smdm + "'
             * AND DJZCLXDM = '" + djzclx + "' AND ZQZZRQ >= to_date('" + rq +
             * "','yyyymmdd')) ORDER BY ZQQSRQ";
             ******************************************************************/
            String newrq = rq.substring(0, 6);
            String sqlWhere = "(ZQLXDM >= '0' AND ZQQSRQ <= to_date('" + newrq +
                "','yyyymm') AND SZSMDM = '" + smdm
                + "' AND DJZCLXDM = '" + djzclx + "' AND ZQZZRQ >= to_date('" +
                newrq
                + "','yyyymm')) ORDER BY ZQQSRQ";

            Vector criteria = new Vector();
            criteria.add(sqlWhere);
            ORContext orCtx = new ORContext(Zqrl.class.getName(), criteria);
            zqrlRs = (ArrayList) orManager.query(SESSION_ID, conn, orCtx); // ��ѯ����������
            if (zqrlRs.size() != 0) {
            	// ӡ��˰�����걨��Ŀ modified by Junbing Tu 2014.0
            	String yhssz = ( (Zqrl) zqrlRs.get(0)).getSzsmdm().substring(0, 2); //ӡ��˰˰�ִ���
            	//1.1 ��������Ϊ�������걨����08��
        		if ( ( (Zqrl) zqrlRs.get(0)).getZqlxdm().equals("08")) 
        		{
        			//1.1.1 ˰������Ϊ������˰�,��������Ϊ�������걨����08��������ӡ��˰���޽�����Ϊ������������������ֹ���ڡ��������걨����(�޷��걨)��
        			//1.1.2 ˰������Ϊ��ί�д�����,��������Ϊ�������걨����08��������ӡ��˰����ѡ�С��Ƿ񰴴Ρ�ʱ���������޽�����Ϊ����������ֹ���ڣ��������޽�����Ϊ�걨����(�޷��걨)��
        			if((yhssz != null && yhssz.equals(CodeConstant.WSSB_YSSB_YHS) 
        					&& (sklxdm != null && sklxdm.equals(CodeConstant.SKLXDM_ZCJK))) 
        				|| ((yhssz != null && yhssz.equals(CodeConstant.WSSB_YSSB_YHS)) 
        					&& (zqlxsbfs != null && zqlxsbfs.equals(CodeConstant.WSSB_YSSB_ZQLX_08)) 
        					&& (sklxdm != null && sklxdm.equals(CodeConstant.SKLXDM_SDJJ))))
            		{
            			Timestamp zqksrq = ( (Zqrl) zqrlRs.get(0)).getZqqsrq();
                		int zqts = ( (Zqrl) zqrlRs.get(0)).getZqts().intValue()-1;
                		
                		Timestamp xjrq = Timestamp.valueOf(DateUtil.addDatetimeByDay(zqksrq.toString(), zqts));
                		Timestamp today = new Timestamp(System.currentTimeMillis());
                        if (xjrq.getTime() < today.getTime()) {
                            xjrq = today;
                        }
                        return xjrq;
            		}
        			
        			////1.1.3 ��������Ϊ�������걨����08��������ӡ��˰��ѡ�С��Ƿ񰴴Ρ�ʱ��
                    GregorianCalendar calendar = new GregorianCalendar();
                    calendar.add(calendar.DAY_OF_MONTH, 1);
                    int month = calendar.get(calendar.MONTH);
                    int year = calendar.get(calendar.YEAR);
                    int day = calendar.get(calendar.DAY_OF_MONTH);
                    Timestamp xjrq = new Timestamp(new GregorianCalendar(year,
                        month, day).getTime().getTime());
                    return xjrq;
                }
                // -------------modified by Daniel------------------------
                // return( (Zqrl)zqrlRs.get(0)).getZqzzrq();
                // �����ǰ�걨���ڹ������ڵĽ������ڣ����޽�����Ϊ����
                Timestamp xjrq = ( (Zqrl) zqrlRs.get(0)).getZqzzrq();
                Timestamp today = new Timestamp(System.currentTimeMillis());
                if (xjrq.getTime() < today.getTime()) {
                    xjrq = today;
                }

                return xjrq;
            }
            else {
                // return zqrl.getZqzzrq(); //����Ĭ��ֵ����ǰ��15��
                /**
                 * ***********************modified by Daniel
                 * ,����������һ��********************
                 */
                // �����ǰ�걨���ڹ������ڵĽ������ڣ����޽�����Ϊ����
                Timestamp xjrq = zqrl.getZqzzrq();
                Timestamp today = new Timestamp(System.currentTimeMillis());
                if (xjrq.getTime() < today.getTime()) {
                    xjrq = today;
                }

                return xjrq;
            }

        }
        catch (Exception e) {
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e, "��ȡ�޽����ڳ���!");
        }
    }

    /**
     * ��ѯ����������ѽ���տ����
     *
     * @param jsjdm
     *            ���������
     * @param swjgdm
     *            ˰�������֯��������
     * @return Swjgzzjg �������
     * @param conn
     *            ���ݿ�����
     * @throws BaseException
     */
    private Swjgzzjg getSkgk(String jsjdm, String swjgdm, Connection conn) throws
        BaseException {
        Swjgzzjg swjg = new Swjgzzjg();
        try {
            // ��� ORManager
            ORManager orManager = DBResource.getORManager(DBResource.OR_SHENBAO);
            // ��ѯ˰����ر�
            ArrayList swjgRs = new ArrayList();

            String sqlWhere = "(SWJGZZJGDM = '" + swjgdm + "')";
            Vector criteria = new Vector();
            criteria.add(sqlWhere);
            ORContext orCtx = new ORContext(Swjgzzjg.class.getName(), criteria);
            swjgRs = (ArrayList) orManager.query(SESSION_ID, conn, orCtx); // ��ѯ˰����ر�
            if (swjgRs.size() != 0) {
                return (Swjgzzjg) swjgRs.get(0);
            }
            else {
                return swjg; // ���ؿ�
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e, "��ȡ�տ�������!");
        }
    }

    /**
     * �걨��ŵ�����,�걨��ŵ����ɹ���Ϊ�������������Ϸ������ĵ�ǰʱ���ʮ��λ�ַ���
     *
     * @param jsjdm
     *            ���������
     * @return sbbh
     * @throws DeclareException
     */
    public String getSbbh(String jsjdm) throws Exception {
        String sbbh = InterFaceProcessor.getSbbh(jsjdm); // ��׼ʱ�䣬1970��1��1��0�������ĺ�����
        return sbbh;
    }

    /**
     * ����ֳɽ��
     *
     * @param je
     *            ʵ�ɽ��
     * @param bl
     *            �ֳɱ�����Ĭ����0��00��
     * @return �ֳɽ��(����4λС��)
     */
    private BigDecimal getFc(BigDecimal je, BigDecimal bl) {
        BigDecimal fc = je.multiply(bl).setScale(4, BigDecimal.ROUND_HALF_UP);
        return fc;
    }

    /**
     * �걨����ά����ڽ��룬��ѯ��˰�˱�������δ�ɿ��걨����
     *
     * @param voPackage
     *            object
     * @param sjly
     *            ������Դ
     * @return map
     * @throws java.lang.Exception
     */
    public HashMap doQuery(VOPackage voPackage, String sjly) throws Exception {
        HashMap dataMap = new HashMap(); // ���ص�map����
        // �������ݿ�����
        Connection conn = null;
        try {
            Map tempData = (HashMap) voPackage.getData(); // ��ǰ�˻��map����
            String swjgzzjgdm = (String) tempData.get("WSSB_SWJGZZJGDM"); // ˰�������֯��������
            String jsjdm = (String) tempData.get(ZhsbMapConstant.JSJDM); // ��ȡ�û��ļ��������
            // ע�����ڸ�ʽ������Ϊ:(YYYY-MM-DD)
            String whrq = (String) tempData.get(ZhsbMapConstant.WHRQ); // ��ȡ�û�ά���걨��������

            String qxdm = swjgzzjgdm.substring(0, 2); // ���ش���

            // ��ѯ�걨�ɿ�����Ĳ�ѯ���
            ArrayList zbResult = new ArrayList();
            // ��ѯ�걨�ɿ���ϸ��Ĳ�ѯ���
            ArrayList mxResult = new ArrayList();

            // ���˳��Ѿ��нɿ��¼���걨��ţ���ҳ���ڻ���(�����ʶ�ĵ�1λ<>0or��20λ<>0)
            // ����ͬһ�������Ƿ��нɿ��¼��ֻҪû�нɿ�������޸�
            String sqlWhere =
                "(ZYRQ >= to_date('20060101','yyyymmdd') AND QXDM = '" + qxdm +
                "' AND JSJDM = '" + jsjdm
                + "'" +
                " AND substr(to_char(SBRQ,'yyyy-mm-dd'),0,7) = substr('" + whrq +
                "', 0, 7) AND SJLY = '"
                + sjly + "' AND FSDM = '" + CodeConstant.FSDM_WSSB + "')"
                // ��Ҫ��ʾ���нɿ��� Modifyed by wuyouzhi 2006.2.9
                // + " AND substr(zwbs, 1, 1) = '0' AND substr(zwbs, 20, 1)
                // = '0' )"
                + " ORDER BY SBRQ DESC, SBBH, JKPZH";
            Vector criteria = new Vector();
            criteria.add(sqlWhere);

            ORContext orCtx = new ORContext(Sbjkzb.class.getName(), criteria);

            // ��� ORManager
            ORManager orManager = DBResource.getORManager(DBResource.OR_SHENBAO);
            // ������ݿ�����
            conn = DBResource.getConnection(DBResource.DB_SHENBAO);

            zbResult = (ArrayList) orManager.query(SESSION_ID, conn, orCtx); // ��ѯ�걨�ɿ�����
            if (zbResult.size() == 0) {
                return null; // û�п���ά�����걨���ݣ�
            }
            // �����걨δ�ɿ�ĵ�������
            Sbjkzb tempObj = (Sbjkzb) zbResult.get(0);
            String _jkpzh = tempObj.getJkpzh();
            // ƴ�걨��ϸ��Ĳ�ѯwhere����
            StringBuffer sqlStrBuf = new StringBuffer();
            sqlStrBuf.append("(QXDM = '").append(qxdm).append(
                "' AND JKPZH IN ('");
            sqlStrBuf.append(_jkpzh);
            for (int i = 1; i < zbResult.size(); i++) {
                tempObj = (Sbjkzb) zbResult.get(i);
                _jkpzh = tempObj.getJkpzh();
                sqlStrBuf.append("','").append(_jkpzh);
            }
            sqlStrBuf.append("')").append(") ORDER BY SBBH DESC, JKPZH");
            String sqlString = sqlStrBuf.toString();
            Vector criteriaMx = new Vector();
            criteriaMx.add(sqlString);

            ORContext orCtxMx = new ORContext(Sbjkmx.class.getName(),
                                              criteriaMx);
            mxResult = (ArrayList) orManager.query(SESSION_ID, conn, orCtxMx); // ��ѯ�걨�ɿ���ϸ��
            DBResource.destroyConnection(conn); // �ر����ݿ�����
            conn = null;
            // ���Ŷ�ȡ�ص����ݽ��и�ʽ��װ����
            dataMap = (HashMap) convertResult(zbResult, mxResult);

            return dataMap; // ���ؽ������
        }
        catch (Exception e) {
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e);
        }
        finally {
            DBResource.destroyConnection(conn);
        }
    }

    /**
     * �걨����ά����ڽ��룬��ѯ��˰�˱�������δ�ɿ��걨����
     *
     * @param voPackage
     *            object
     * @param sjly
     *            ������Դ
     * @return map
     * @throws java.lang.Exception
     */
    public ArrayList doQueryysb(VOPackage voPackage) throws Exception {
        ArrayList dataList = new ArrayList(); // ���ص�map����
        // �������ݿ�����
        Connection conn = null;
        ResultSet rs = null;

        try {
            UserData ud = voPackage.getUserData();
            String jsjdm = ud.yhid;
            // ȡ�õ�ǰ��ʱ���
            GregorianCalendar gc = new GregorianCalendar();
            int monthNow = gc.get(Calendar.MONTH) + 1;
            String dateNow = "";
            if (monthNow > 9) {
                dateNow = String.valueOf(gc.get(Calendar.YEAR)) +
                    String.valueOf(monthNow) + "01";
            }
            else {
                dateNow = String.valueOf(gc.get(Calendar.YEAR)) + "0" +
                    String.valueOf(monthNow) + "01";
            }
            gc.add(Calendar.MONTH, 1);
            int monthNext = gc.get(Calendar.MONTH) + 1;
            String dateNextMonth = "";
            if (monthNext > 9) {
                dateNextMonth = String.valueOf(gc.get(Calendar.YEAR)) +
                    monthNext + "01";
            }
            else {
                dateNextMonth = String.valueOf(gc.get(Calendar.YEAR)) + "0" +
                    monthNext + "01";
            }
            String sql =
                "select sum(sjje) je, sbbh, zwbs, cjrq, SYSDATE from sbdb.sb_jl_sbjkzb where jsjdm = '" +
                jsjdm +
                "' and fsdm = '5' and sjly = '11' and sbrq >= to_date('" +
                dateNow +
                "', 'yyyymmdd') and sbrq <  to_date('" + dateNextMonth +
                "', 'yyyymmdd') and zyrq >= to_date('" + dateNow +
                "', 'yyyymmdd') and zyrq < to_date('" + dateNextMonth +
                "', 'yyyymmdd') group by sbbh, zwbs, cjrq order by sbbh desc";
            System.out.println("sql========" + sql);
            // ������ݿ�����
            conn = DBResource.getConnection(DBResource.DB_SHENBAO);
            Statement st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Sbjkzb zb = new Sbjkzb();
                zb.setSjje(rs.getBigDecimal("je"));
                zb.setSbbh(rs.getString("sbbh"));
                zb.setZwbs(rs.getString("zwbs"));
                Timestamp cjrq = rs.getTimestamp("cjrq");
                zb.setCjrq(cjrq);
                Timestamp now = rs.getTimestamp("SYSDATE");
                if ( (now.getTime() - cjrq.getTime()) < 1800000) {
                    zb.setBz("1");
                }
                dataList.add(zb);
            }
            st.close();

            DBResource.destroyConnection(conn); // �ر����ݿ�����
            conn = null;
            // ���Ŷ�ȡ�ص����ݽ��и�ʽ��װ����

            return dataList; // ���ؽ������
        }
        catch (Exception e) {
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e);
        }
        finally {
            DBResource.destroyConnection(conn);
        }
    }

    /**
     * ���걨�������ϸ�����ݣ��������ݸ�ʽ��װ��map�У�������
     *
     * @param zbResult
     *            ��������
     * @param mxResult
     *            ��ϸ����
     * @return HashMap
     */
    public HashMap convertResult(ArrayList zbResult, ArrayList mxResult) {
        HashMap dataMap = new HashMap(); // ���ص����ݰ�

        ArrayList sbbhAr = new ArrayList(); // ���걨��ű������ݵ�list
        ArrayList objAr = new ArrayList(); // ���DeclareInfor��������
        ArrayList mxAr = new ArrayList(); // ����걨��ϸ����
        int zbSize = zbResult.size();
        /**
         * qianchao. 2006.5.20 �������������¡� ���걨��Ŷ��������ݣ���ϸ���ݹ��ࡣ �������sbbhAr��
         * sbbhAr��һ��list�����е�ÿһ��Ԫ��Ҳ��һ��list�� ��Ӧ��һ��sbbh��
         * �ݳ���jkslist��jkslist��ÿһ��Ԫ�أ���һ��DeclareInfor�ṹ��
         */
        for (int i = 0; i < zbSize; i++) {
            Sbjkzb zbData = (Sbjkzb) zbResult.get(i);
            for (int j = 0; j < mxResult.size(); j++) {
                Sbjkmx mxData = (Sbjkmx) mxResult.get(j);
                if (zbData.getJkpzh().equals(mxData.getJkpzh())) {
                    mxAr.add(mxData);
                }
            }
            ArrayList tempAr = (ArrayList) mxAr.clone();
            DeclareInfor dataInfor = new DeclareInfor(zbData, tempAr);
            dataInfor.setSbjkzb(zbData); // test??
            mxAr.clear();
            String _jkpzh = zbData.getJkpzh();
            if (_jkpzh.substring(_jkpzh.length() - 1).equals("0")) { // �жϽɿ�ƾ֤�ŵ����һλ�Ƿ�Ϊ��0��
                dataInfor.setPrintTag(CodeConstant.PRINT_YPYS); // һƱһ˰
            }
            else {
                dataInfor.setPrintTag(CodeConstant.PRINT_YPDS_KM); // һƱ��˰
            }
            String tempSbbh = "";
            if (i < (zbSize - 1)) {
                tempSbbh = ( (Sbjkzb) zbResult.get(i + 1)).getSbbh(); // ��ʱ��������ǰ�����¼����һ�����걨���
                // ˵�������������У��걨��Ŷ���Ϊ�գ�������һ����¼�ԱȵĽ����ȻΪfalse�����Բ���Ҫ�����µ��ж�
            }
            objAr.add(dataInfor);
            if (!zbData.getSbbh().equals(tempSbbh)) {
                ArrayList temp = (ArrayList) objAr.clone();
                sbbhAr.add(temp);
                objAr.clear();
            }
        }

        // �����걨��Ŷ����ݽ��и�ʽ����
        HashMap bhMap = new HashMap(); // һ���걨���Ϊkey��Ӧ��map
        HashMap pzMap = new HashMap(); // һ���ɿ����Ϊkey��Ӧ��map
        ArrayList bhList = new ArrayList();
        /**
         * qianchao. 2006.5.20 �������������¡� ��sbbhAr���н�һ�����������dataMap�С�
         *
         * dataMap�ṹʾ������
         *
         *
         * ��һ�㣺hashmap�� �ڶ���: hashmap�� key��String����sbbh
         * value��hashmap�������������걨��Ϣ��map�� �����㣺hashmap�� key��printTag value���Ƿ�һƱһ˰
         * key��sklx value��˰������ key��sbrq value���걨���� key��zwbs value�������ʶ ��һƱһ˰��
         * key��jkpzh value�������ýɿ�ƾ֤����Ϣһ��hashmap ��һƱ��˰�� key��15λjkpzh
         * value�������ýɿ�ƾ֤����Ϣһ��hashmap ���Ĳ�: hashmap�� ��һƱһ˰�� key��String����jehj
         * value���ýɿ���ϼƽ�� key��String����sbsj value���ýɿ����DeclareInfor ��һƱ��˰��
         * key��String����jehj value����15λ�ɿ���Ŷ�Ӧ�Ķ��Žɿ����list key��String����sbsj
         * value���ýɿ����DeclareInfor
         */
        for (int k = 0; k < sbbhAr.size(); k++) {
            bhList.clear();
            bhList = (ArrayList) sbbhAr.get(k); // ���ͬһ���걨��ŵ�����
            DeclareInfor tempObj = (DeclareInfor) bhList.get(0);
            String _sbbh = tempObj.getSbjkzb().getSbbh(); // �걨���
            bhMap.put(ZhsbMapConstant.PRINTTAG, new Integer(tempObj.getPrintTag())); // Ʊ�����ࣺһƱ��˰/һƱһ˰
            bhMap.put(ZhsbMapConstant.SKLX, tempObj.getSbjkzb().getSklxdm()); // ˰�����ʹ���
            bhMap.put(ZhsbMapConstant.SBRQ, tempObj.getSbjkzb().getSbrq()); // �걨����
            bhMap.put(ZhsbMapConstant.ZWBS, tempObj.getSbjkzb().getZwbs()); // �����ʶ
            if (tempObj.getPrintTag() == CodeConstant.PRINT_YPYS) { // һƱһ˰
                for (int m = 0; m < bhList.size(); m++) {
                    tempObj = (DeclareInfor) bhList.get(m);
                    pzMap.put(ZhsbMapConstant.JEHJ, tempObj.getSbjkzb().getRkje());
                    pzMap.put(ZhsbMapConstant.SBSJ, tempObj);
                    String _jkpzh = tempObj.getSbjkzb().getJkpzh();
                    HashMap tempPz = (HashMap) pzMap.clone();
                    bhMap.put(_jkpzh, tempPz);
                    pzMap.clear();
                }
            }
            else if (tempObj.getPrintTag() == CodeConstant.PRINT_YPDS_KM) { // һƱ��˰
                String _oldJksh = ""; // ����ɿ����
                ArrayList tempJksAr = new ArrayList(); // ��ʱ�ɿ��������б�
                BigDecimal jehj = new BigDecimal(0); // ������ϼƲ���
                for (int m = 0; m < bhList.size(); m++) {
                    tempObj = (DeclareInfor) bhList.get(m);
                    String _jkpzh = tempObj.getSbjkzb().getJkpzh();
                    String _jksh = _jkpzh.substring(0, _jkpzh.length() - 1); // ��ýɿ����
                    if (m == 0 || _jksh.equals(_oldJksh)) {
                        _oldJksh = _jksh;
                        jehj = jehj.add(tempObj.getSbjkzb().getRkje()); // ��һ��ƾ֤�ϵļ�¼���н���ۼ�
                        tempJksAr.add(tempObj);
                    }
                    else { // if(m != 0 && !_jksh.equals(oldJksh))
                        ArrayList temps = (ArrayList) tempJksAr.clone();
                        pzMap.put(ZhsbMapConstant.SBSJ, temps);
                        pzMap.put(ZhsbMapConstant.JEHJ, jehj);
                        HashMap tempPz = (HashMap) pzMap.clone(); // copy
                        bhMap.put(_oldJksh, tempPz); // ���ɿ���map��װ���걨��Ŷ�Ӧ��map��
                        tempJksAr.clear(); // ���
                        jehj = jehj.subtract(jehj); // ����
                        // ��ʼ�µ��ۼ�
                        _oldJksh = _jksh;
                        jehj = jehj.add(tempObj.getSbjkzb().getRkje()); // ��һ��ƾ֤�ϵļ�¼���н���ۼ�
                        tempJksAr.add(tempObj);
                    }
                }
                // �����һ��˰Ʊ���з�װ
                ArrayList temp = (ArrayList) tempJksAr.clone();
                pzMap.put(ZhsbMapConstant.SBSJ, temp);
                pzMap.put(ZhsbMapConstant.JEHJ, jehj);
                HashMap tempPz = (HashMap) pzMap.clone();
                bhMap.put(_oldJksh, tempPz); // ���ɿ���map��װ���걨��Ŷ�Ӧ��map��
                pzMap.clear();
                tempJksAr.clear(); // ���
                jehj = jehj.subtract(jehj); // ����
            }
            HashMap tempMap = (HashMap) bhMap.clone();
            dataMap.put(_sbbh, tempMap); // ��ͬһ���걨��ŵ����ݷ�װ��map�е�һ��key��value
            bhMap.clear();
        }
        return dataMap;
    }

    /**
     * �����걨������ϸ��걨��Ŷ�Ӧ����������
     *
     * @param voPackage
     *            ��Ҫ����걨���
     * @throws java.lang.Exception
     */
    private Object doDel(VOPackage voPackage) throws Exception {
        HashMap tempData = (HashMap) voPackage.getData();
        Map reMap = new HashMap();
        // ���ϵ��걨���
        String zfSbbh = (String) tempData.get(ZhsbMapConstant.SBBH);
        String jsjdm = zfSbbh.substring(0, 8);
        // ���ϵĽɿ����
        String zfJksh = "";
        zfJksh = (String) tempData.get(ZhsbMapConstant.JKSH);
        String swjgzzjgdm = (String) tempData.get("WSSB_SWJGZZJGDM");
        String qxdm = swjgzzjgdm.substring(0, 2);
        DzyjsjVO dzyj = (DzyjsjVO) tempData.get(ZhsbMapConstant.CA_QMSJ_VO);
        UserData ud = voPackage.getUserData();

        // �������ݿ�����
        Connection conn = null;
        try {
            /*
             * if (ud.getCaflag()) { try { dzyj = (new
             * DzyjHelper()).saveDzyjsj(dzyj, "0", "0", "0", zfSbbh); } catch
             * (com.syax.frame.exception.ApplicationException e) {
             * e.printStackTrace(); throw ExceptionUtil.getBaseException(e); }
             * catch (Exception ex) { ex.printStackTrace(); throw
             * ExceptionUtil.getBaseException(ex); }
             * reMap.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ,dzyj);
             * System.out.println("============����ǩ�����ݽ���=============="); }
             */
            try {
                dzyj = CAUtils.saveDzyjsj(ud, dzyj, "0", "0", "0", zfSbbh);
            }
            catch (Exception ex) {
                ex.printStackTrace();
                throw ExceptionUtil.getBaseException(ex);
            }
            reMap.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);

            // ����ǰ��սɿ�ƾ֤�ŵ������Ͻɿ��飬������ɾ����������Ϊ����ɿ����
            StringBuffer tempStr = new StringBuffer();
            String sqlAdd = "";
            if (zfJksh != null && zfJksh.length() != 0) {
                tempStr.append(" AND SUBSTR(JKPZH,0,'").append(zfJksh.length()).
                    append("') = '").append(zfJksh).append(
                        "'");
                // System.out.println(" AND
                // SUBSTR(JKPZH,0,'"+zfJksh.length()+"') = '"+zfJksh+"'");
                /*
                 * tempStr.append(" AND JKPZH='") .append(zfJksh).append("' ");
                 */
                sqlAdd = tempStr.toString();
            }
            // System.out.println(tempStr.toString());
            // �жϸ��������ߵ��ţ��ɿ�ƾ֤�Ƿ�δ�ɿ����
            String sqlW = "SELECT JKPZH FROM SBDB.SB_JL_SBJKZB"
                + " WHERE ZYRQ >= to_date('20060101','yyyymmdd') AND QXDM='" +
                qxdm + "' AND JSJDM = '" + jsjdm
                + "' AND SBBH = '" + zfSbbh + "' " +
                "AND substr(zwbs, 1, 1) = '0' AND substr(zwbs, 20, 1) = '0' "
                + sqlAdd;
            // ����sql��䣬������

            // ɾ����ϸ���ݵ�sql���
            StringBuffer sqlStringBuffer = new StringBuffer();
            /*
             * sqlStringBuffer.append("DELETE FROM SBDB.SB_JL_SBJKMX WHERE QXDM =
             * '") .append(qxdm).append("' AND JKPZH IN ("+sqlW+")");
             */
            sqlStringBuffer.append("DELETE FROM SBDB.SB_JL_SBJKMX WHERE ").
                append(" JKPZH IN (" + sqlW + ")");
            // ��ֵ��ϸ���
            String mxSql = sqlStringBuffer.toString();
            sqlStringBuffer.setLength(0); // clear
            System.out.println(mxSql);

            // ɾ�������¼���ݵ�sql���
            StringBuffer sqlBuffer = new StringBuffer();
            sqlBuffer.append("DELETE FROM SBDB.SB_JL_SBJKZB").append(
                " WHERE ZYRQ >= to_date('20060101','yyyymmdd') AND QXDM = '").
                append(qxdm).append("' AND JSJDM='")
                .append(jsjdm).append("' AND SBBH = '").append(zfSbbh).append(
                    "' AND substr(zwbs, 1, 1) = '0' AND substr(zwbs, 20, 1) = '0'").
                append(tempStr);
            // ��ֵ�������
            String zbSql = sqlBuffer.toString();
            System.out.println(zbSql);
            sqlBuffer.setLength(0); // clear

            // ������ݿ�����
            conn = DBResource.getConnection(DBResource.DB_SHENBAO);
            Statement st = conn.createStatement();
            st.addBatch(mxSql); // �����ϸsql���
            st.addBatch(zbSql); // �������sql���
            st.executeBatch(); // ִ��ɾ��
        }
        catch (Exception e) {
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e, "���Ͻɿ�����ʧ�ܣ�");
        }
        finally {
            DBResource.destroyConnection(conn);
        }
        return reMap;
    }

    /**
     * �����걨������ϸ��걨��Ŷ�Ӧ����������
     *
     * @param voPackage
     *            ��Ҫ����걨���
     * @throws java.lang.Exception
     */
    private void doDelBySBBH(VOPackage voPackage) throws Exception {
        HashMap tempData = (HashMap) voPackage.getData();
        // ���ϵ��걨���
        String zfSbbh = (String) tempData.get(ZhsbMapConstant.SBBH);
        String jsjdm = zfSbbh.substring(0, 8);
        String sql = null;
        StringBuffer sb = null;
        // �������ݿ�����
        Connection conn = null;
        Statement st = null;
        try {
            // ������ݿ�����
            conn = DBResource.getConnection(DBResource.DB_SHENBAO);
            st = conn.createStatement();
            // �����ϸsql���
            sb = new StringBuffer();
            sb.append("DELETE FROM SBDB.SB_JL_SBJKMX WHERE jsjdm=");
            sb.append(StringUtils.getSQLStr(jsjdm));
            sb.append(" AND sbbh=");
            sb.append(StringUtils.getSQLStr(zfSbbh));
            sql = sb.toString();
            log(sql);
            st.addBatch(sql);
            // �������sql���
            sb = new StringBuffer();
            sb.append("DELETE FROM SBDB.SB_JL_SBJKZB WHERE jsjdm=");
            sb.append(StringUtils.getSQLStr(jsjdm));
            sb.append(" AND sbbh=");
            sb.append(StringUtils.getSQLStr(zfSbbh));
            sql = sb.toString();
            log(sql);
            st.addBatch(sql);
            // ִ��ɾ��
            st.executeBatch();
            //
            st.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e, "���Ͻɿ�����ʧ�ܣ�");
        }
        finally {
            DBResource.destroyConnection(conn);
        }
    }

    /**
     * һƱ��˰����µĴ�ӡ���������������ֻ�����걨��Ž������֣���������ϸ���ݷ��飬ÿ�����9����¼
     *
     * @param voPackage
     *            ȡ�걨���
     * @return map
     * @throws java.lang.Exception
     */
    private HashMap doPrint(VOPackage voPackage) throws Exception {
        HashMap tempData = (HashMap) voPackage.getData();
        String printSbbh = (String) tempData.get(ZhsbMapConstant.SBBH);
        String jsjdm = printSbbh.substring(0, 8);
        String swjgzzjgdm = (String) tempData.get("WSSB_SWJGZZJGDM");
        String qxdm = swjgzzjgdm.substring(0, 2); // ���ش���
        ArrayList dataAr = new ArrayList();
        HashMap dataMap = new HashMap(); // ��ӡ���ݰ�
        // ����һ�����ݿ�����
        Connection con = null;
        try {
            // ��ѯ�걨�ɿ���ϸ��Ĳ�ѯ���
            ArrayList mxResult = new ArrayList();

            // ������ݿ�����
            con = DBResource.getConnection(DBResource.DB_SHENBAO);
            // ��� ORManager
            ORManager orManager = DBResource.getORManager(DBResource.OR_SHENBAO);
            String sqlWhere = "(QXDM = '" + qxdm + "' AND SBBH = '" + printSbbh +
                "') ORDER BY SZSMDM";
            Vector criteria = new Vector();
            criteria.add(sqlWhere);
            ORContext orCtx = new ORContext(Sbjkmx.class.getName(), criteria);
            mxResult = (ArrayList) orManager.query(SESSION_ID, con, orCtx); // ��ѯ�걨�ɿ���ϸ��
            // һ���걨���ֻ��Ӧһ��˰������
            dataAr = (ArrayList) splitList(mxResult, 9); // ��ÿƱ���9����¼���з���
            ArrayList tempAr = (ArrayList) dataAr.clone(); // copy
            dataMap.put(ZhsbMapConstant.SBSJ, tempAr); // �������Ĵ�ӡ���ݣ�����key='sbsj'����map��

            // ��ѯ���걨��Ŷ�Ӧ��˰�����ͼ����д��롢����
            String sqlW =
                "(ZYRQ >= to_date('20040101','yyyymmdd') AND QXDM = '" + qxdm +
                "' AND JSJDM = '" + jsjdm
                + "' AND SBBH = '" + printSbbh +
                "' AND ROWNUM=1) ORDER BY SZDM";
            Vector cri = new Vector();
            cri.add(sqlW);
            ORContext orCtx2 = new ORContext(Sbjkzb.class.getName(), cri);
            ArrayList zbResult = (ArrayList) orManager.query(SESSION_ID, con,
                orCtx2); // ��ѯ�걨�ɿ���ϸ��
            if (zbResult.size() == 1) {
                Sbjkzb zbTmp = (Sbjkzb) zbResult.get(0);
                String sklxdm = zbTmp.getSklxdm(); // ���˰�����ʹ���
                // �����ݷ�װ��map��
                dataMap.put(ZhsbMapConstant.SKLX, sklxdm); // ��ֵ˰�����ʹ���,key='sklx'
                dataMap.put(ZhsbMapConstant.YHZH, zbTmp.getZh()); // �����ʻ�
                dataMap.put(ZhsbMapConstant.YHMC, zbTmp.getYhmc()); // ��������
                dataMap.put(ZhsbMapConstant.SBRQ, zbTmp.getSbrq()); // �걨����
                dataMap.put(ZhsbMapConstant.ZWBS, zbTmp.getZwbs()); // �����ʶ
                dataMap.put(ZhsbMapConstant.SBBH, printSbbh); // �걨����
                
                //�޽�����  gsdz
                String xjrq = "";
                
                String queryXjrq = "select max(xjrq) maxxjrq from sbdb.sb_jl_sbjkzb where " +
                		"(ZYRQ >= to_date('20040101','yyyymmdd') AND QXDM = '" + qxdm +
                		 "' AND JSJDM = '" + jsjdm
                         + "' AND SBBH = '" + printSbbh +
                         "') ORDER BY SZDM";
                PreparedStatement ps = con.prepareStatement(queryXjrq);
                ResultSet rs = ps.executeQuery();
                DateFormat sdf = new SimpleDateFormat("yyyy��MM��dd��"); 
                if(rs.next()){
                	Timestamp ts = rs.getTimestamp("maxxjrq");
                    if(ts!=null){
                    	try {
                    		xjrq = sdf.format(ts);
    					} catch (Exception e) {
    						e.printStackTrace();
    					}
                    }
                }
                
                dataMap.put("xjrq",xjrq); 
            }
            else {
                throw new Exception("���ݴ���û�ж�Ӧ���걨���ݣ�");
            }
            return dataMap;
        }
        catch (Exception e) {
            throw ExceptionUtil.getBaseException(e);
        }
        finally {
            DBResource.destroyConnection(con);
        }
    }

    /**
     * ����˱��淵�ص�List��ת��Ϊ��ʽ����Map
     *
     * @param obj
     *            ArrayList
     * @return HashMap
     */
    private HashMap listToMap(Object obj) {
        ArrayList tempAr = (ArrayList) obj;
        HashMap midMap = new HashMap(); // ��װ�ɿ�ƾ������map
        HashMap bottomMap = new HashMap(); // ��װ����ɿ����ݲ����map

        if (printTag == CodeConstant.PRINT_YPYS) { // һƱһ˰
            midMap.put(ZhsbMapConstant.PRINTTAG, new Integer(printTag));
            DeclareInfor tempObj = (DeclareInfor) tempAr.get(0);
            midMap.put(ZhsbMapConstant.SKLX, tempObj.getSbjkzb().getSklxdm()); // ��˰�����ʹ����װ��map
            midMap.put(ZhsbMapConstant.SBRQ, tempObj.getSbjkzb().getSbrq()); // ���걨���ڷ�װ��map
            midMap.put(ZhsbMapConstant.ZWBS, tempObj.getSbjkzb().getZwbs()); // �����ʶ
            for (int i = 0; i < tempAr.size(); i++) {
                tempObj = (DeclareInfor) tempAr.get(i);
                bottomMap.put(ZhsbMapConstant.JEHJ, tempObj.getSbjkzb().getRkje()); // �����ϼƣ���Ʊ��
                bottomMap.put(ZhsbMapConstant.SBSJ, tempObj); // �걨���ݶ����װ��map
                String jkpzh = tempObj.getSbjkzb().getJkpzh(); // ��ýɿ�ƾ֤��
                HashMap tmpMap = (HashMap) bottomMap.clone(); // ����
                midMap.put(jkpzh, tmpMap); // ��һ�Žɿ�ƾ֤���ݼ�����װ��map
            }
        }
        else if (printTag == CodeConstant.PRINT_YPDS_KM) { // һƱ��˰����Ŀ��
            String jkpzh, jksh;
            BigDecimal jehj = new BigDecimal(0); // ������ϼƲ���
            midMap.put(ZhsbMapConstant.PRINTTAG, new Integer(printTag)); //
            ArrayList tempList = new ArrayList();
            tempList = (ArrayList) tempAr.get(0); // ��þ���һ�Žɿ��������б�
            DeclareInfor tempObj = (DeclareInfor) tempList.get(0); // ��õ�һ����Ŀ����
            midMap.put(ZhsbMapConstant.SKLX, tempObj.getSbjkzb().getSklxdm()); // ˰�����ʹ���
            midMap.put(ZhsbMapConstant.SBRQ, tempObj.getSbjkzb().getSbrq());
            midMap.put(ZhsbMapConstant.ZWBS, tempObj.getSbjkzb().getZwbs());
            for (int i = 0; i < tempAr.size(); i++) {
                jehj = jehj.subtract(jehj); // �ϼƽ���������(jehj-jehj=0)
                jkpzh = "";
                jksh = ""; // ��վ�����
                tempList = (ArrayList) tempAr.get(i); // ��þ���һ�Žɿ��������б�
                tempObj = (DeclareInfor) tempList.get(0); // ��õ�һ����Ŀ����
                jkpzh = tempObj.getSbjkzb().getJkpzh(); // ÿһ��Ԥ���Ŀ���ݶ�Ӧ�Ľɿ�ƾ֤�ţ�16λ��
                jksh = jkpzh.substring(0, 15); // һ�Žɿ����Ӧ�Ľɿ���ţ�15λ��
                for (int j = 0; j < tempList.size(); j++) {
                    tempObj = (DeclareInfor) tempList.get(j); // ���һ����Ŀ��Ӧ���걨����
                    jehj = jehj.add(tempObj.getSbjkzb().getRkje()); // ��ÿ����Ŀ�Ľ�˰Ʊ����
                }
                bottomMap.put(ZhsbMapConstant.JEHJ, jehj);
                ArrayList tmpList = (ArrayList) tempList.clone(); // copy
                bottomMap.put(ZhsbMapConstant.SBSJ, tmpList); // ����map
                HashMap tempMap = (HashMap) bottomMap.clone(); // copy
                midMap.put(jksh, tempMap); // ����map��װ���ɿ���map��
            }
        }
        return midMap; // ���ؽ������map
    }

    /**
     * ����˱��淵�ص�List��ת��Ϊ��ʽ����Map
     *
     * @param obj
     *            ArrayList
     * @param boolean
     *            �Ƿ�������
     * @return HashMap
     */
    private HashMap listToMapOfSkh(Object obj, boolean zfFlag) {
        ArrayList tempAr = (ArrayList) obj;
        HashMap midMap = new HashMap(); // ��װ�ɿ�ƾ������map
        HashMap bottomMap = new HashMap(); // ��װ����ɿ����ݲ����map
        List jkshList = new ArrayList();
        
        if (printTag == CodeConstant.PRINT_YPYS) { // һƱһ˰
            midMap.put(ZhsbMapConstant.PRINTTAG, new Integer(printTag));
            DeclareInfor tempObj = (DeclareInfor) tempAr.get(0);
            midMap.put(ZhsbMapConstant.SKLX, tempObj.getSbjkzb().getSklxdm()); // ��˰�����ʹ����װ��map
            midMap.put(ZhsbMapConstant.SBRQ, tempObj.getSbjkzb().getSbrq()); // ���걨���ڷ�װ��map
            midMap.put(ZhsbMapConstant.ZWBS, tempObj.getSbjkzb().getZwbs());
            for (int i = 0; i < tempAr.size(); i++) {
                tempObj = (DeclareInfor) tempAr.get(i);
                bottomMap.put(ZhsbMapConstant.JEHJ, tempObj.getSbjkzb().getRkje()); // �����ϼƣ���Ʊ��
                bottomMap.put(ZhsbMapConstant.SBSJ, tempObj); // �걨���ݶ����װ��map
                String jkpzh = tempObj.getSbjkzb().getJkpzh(); // ��ýɿ�ƾ֤��
                HashMap tmpMap = (HashMap) bottomMap.clone(); // ����
                midMap.put(jkpzh, tmpMap); // ��һ�Žɿ�ƾ֤���ݼ�����װ��map
                jkshList.add(jkpzh);
            }
        }
        else if (printTag == CodeConstant.PRINT_YPDS_KM) { // һƱ��˰����Ŀ��
            String jkpzh, jksh;
            BigDecimal jehj = new BigDecimal(0); // ������ϼƲ���
            midMap.put(ZhsbMapConstant.PRINTTAG, new Integer(printTag)); //
            ArrayList tempList = new ArrayList();
            tempList = (ArrayList) tempAr.get(0); // ��þ���һ�Žɿ��������б�
            DeclareInfor tempObj = (DeclareInfor) tempList.get(0); // ��õ�һ����Ŀ����
            midMap.put(ZhsbMapConstant.SKLX, tempObj.getSbjkzb().getSklxdm()); // ˰�����ʹ���
            midMap.put(ZhsbMapConstant.SBRQ, tempObj.getSbjkzb().getSbrq());
            midMap.put(ZhsbMapConstant.ZWBS, tempObj.getSbjkzb().getZwbs());
            log("��ӵ����걨��������ɱ��" + zfFlag);
            midMap.put(ZhsbMapConstant.ZFBZ, new Boolean(zfFlag));
            midMap.put(ZhsbMapConstant.ZWBS, tempObj.getSbjkzb().getZwbs()); // �����ʶ
            for (int i = 0; i < tempAr.size(); i++) {
                jehj = jehj.subtract(jehj); // �ϼƽ���������(jehj-jehj=0)
                jkpzh = "";
                jksh = ""; // ��վ�����
                tempList = (ArrayList) tempAr.get(i); // ��þ���һ�Žɿ��������б�
                tempObj = (DeclareInfor) tempList.get(0); // ��õ�һ����Ŀ����
                jkpzh = tempObj.getSbjkzb().getJkpzh(); // ÿһ��Ԥ���Ŀ���ݶ�Ӧ�Ľɿ�ƾ֤�ţ�16λ��
                jksh = jkpzh.substring(0, 15); // һ�Žɿ����Ӧ�Ľɿ���ţ�15λ��
                for (int j = 0; j < tempList.size(); j++) {
                    tempObj = (DeclareInfor) tempList.get(j); // ���һ����Ŀ��Ӧ���걨����
                    jehj = jehj.add(tempObj.getSbjkzb().getRkje()); // ��ÿ����Ŀ�Ľ�˰Ʊ����
                }
                bottomMap.put(ZhsbMapConstant.JEHJ, jehj);
                ArrayList tmpList = (ArrayList) tempList.clone(); // copy
                bottomMap.put(ZhsbMapConstant.SBSJ, tmpList); // ����map
                HashMap tempMap = (HashMap) bottomMap.clone(); // copy
                midMap.put(jksh, tempMap); // ����map��װ���ɿ���map��
                jkshList.add(jksh);
            }
        }
        midMap.put("jkshList", jkshList);
        return midMap; // ���ؽ������map
    }

    private List splitList(List sourcelist, int max) {
        List splitList = new ArrayList();
        int eachpageIndex = 1;
        List eachPageList = new ArrayList();
        for (int i = 0; i < sourcelist.size(); i++) {
            if (eachpageIndex == max) {
                eachPageList.add(sourcelist.get(i));
                splitList.add(eachPageList);
                eachPageList = new ArrayList();
                eachpageIndex = 1;
            }
            else {
                eachPageList.add(sourcelist.get(i));
                eachpageIndex++;
            }
        }
        if (eachpageIndex != 1) {
            splitList.add(eachPageList);
        }
        return splitList;
    }

    /**
     * ����properties�����ƣ����������ñ��ж�ȡ��properties��Ӧ��ֵ��
     *
     * @param propertyName
     *            properties������
     * @return java.lang.String
     */
    public String getProperty(VOPackage voPackage) throws BaseException {
        String propertyName = (String) voPackage.getData();
        String value = "";

        Connection con = null;
        try {
            // ��ѯ�������ñ�Ĳ�ѯ���
            ArrayList result = new ArrayList();

            // ������ݿ�����
            con = DBResource.getConnection(DBResource.DB_SHENBAO);
            // ��� ORManager
            ORManager orManager = DBResource.getORManager(DBResource.OR_SHENBAO);
            String sqlWhere = "propertyname = '" + propertyName +
                "' AND zxbs = '0'";
            // System.out.println("zhsbProcessor.getProperty:"+sqlWhere);
            Vector criteria = new Vector();
            criteria.add(sqlWhere);
            ORContext orCtx = new ORContext(com.ttsoft.bjtax.shenbao.model.
                                            domain.Properties.class.getName(),
                                            criteria);
            result = (ArrayList) orManager.query(SESSION_ID, con, orCtx); // ��ѯ�������ñ�
            if (result.size() == 0) {
                return ""; // û�п���ά�����걨���ݣ�
            }
            value = ( (com.ttsoft.bjtax.shenbao.model.domain.Properties) result.
                     get(0)).getPropertyvalue();
            if (value == null) {
                value = "";
            }
        }
        catch (Exception e) {
            throw ExceptionUtil.getBaseException(e);
        }
        finally {
            DBResource.destroyConnection(con);
        }

        return value.trim();
    }

    /**
     * ������Э�黧���пۿ�ɹ����걨�ñ�־λ
     *
     * @param vo
     *            vo����
     * @param flag
     *            0--�ۿ�ɹ�,1--�ۿ�״̬����
     * @return java.lang.String ����ָ
     */
    private Map doModifyZwbsYhkk(VOPackage vo, int flag) throws BaseException {
        log("===========��̨���������־��ʼ============");
        String value = "";
        Connection con = null;
        Statement st = null;
        Map jkdata = null;
        UserData ud = null;
        StringBuffer sb = null;
        String sql = null;
        String sbbh = null;
        try {
            // ������ݿ�����
            con = DBResource.getConnection(DBResource.DB_SHENBAO);
            st = con.createStatement();
            // ��vo�����ȡ����
            jkdata = (Map) vo.getData();
            ud = vo.getUserData();
            // ��ȡ���е��걨�������ݲ����������־
            Iterator keyset = jkdata.keySet().iterator();
            while (keyset.hasNext()) {
                sbbh = (String) keyset.next();
                sb = new StringBuffer();
                sb.append(
                    "UPDATE sbdb.sb_jl_sbjkzb SET zwbs=SUBSTR(zwbs, 1, 17)||");
                if (flag == 0) {
                    sb.append(StringUtils.getSQLStr(ApplicationConstant.
                        ZWBS_YHKK_SFXY_SUCCESS));
                    sb.append(",jksj=sysdate");
                }
                else {
                    sb.append(StringUtils.getSQLStr(ApplicationConstant.
                        ZWBS_YHKK_SFXY_STATUS_LOCK));
                }
                sb.append(" WHERE sbbh=");
                sb.append(StringUtils.getSQLStr(sbbh));
                sb.append(" AND jsjdm=");
                sb.append(StringUtils.getSQLStr(ud.getYhid()));
                sql = sb.toString();
                log(sql);
                st.addBatch(sql);
            }
            st.executeBatch();
            st.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e);
        }
        finally {
            DBResource.destroyConnection(con);
        }

        return jkdata;
    }

    /**
     * ����һƱ��˰�ɿ����˰Ʊ���뼰��������
     *
     * @param vo
     *            vo����
     * @return java.util.List һƱ��˰�ɿ��鼯��
     */
    private List doGenerateYpdsJks(VOPackage vo, Sfxy sfxy) throws
        BaseException {
        log("===========����һƱ��˰�ɿ��鿪ʼ============");
        List ypdsJksList = null;
        YPDSJKS jks = null;
        String value = "";
        Connection con = null;
        Statement st = null;
        Map jkdata = null;
        UserData ud = null;
        StringBuffer sb = null;
        String sql = null;
        String sbbh = null;
        String sphm = null;
        String[] jkpzhs = {};
        try {
            // ������ݿ�����
            con = DBResource.getConnection(DBResource.DB_SHENBAO);
            st = con.createStatement();
            // ��vo�����ȡ����
            jkdata = (Map) vo.getData();
            ud = vo.getUserData();
            // ��ȡ���е��걨�������ݲ�����˰Ʊ����
            Iterator keyset = jkdata.keySet().iterator();
            while (keyset.hasNext()) {
                sbbh = (String) keyset.next();
                ypdsJksList = InterFaceProcessor.getYpdsJks(sbbh);
                for (int i = 0; i < ypdsJksList.size(); i++) {
                    jks = (YPDSJKS) ypdsJksList.get(i);
                    log("һƱһ˰�ɿ������ϸ����Ϊ" + jks.getYpysJksMx().size());
                    jkpzhs = jks.getPropertyJkpzhFfYpdsJks();
                    sphm = InterFaceProcessor.getSphm(jks.getJsjdm());
                    log("��̨�����걨���Ϊ" + sbbh + "��һƱһ˰���ݷ�Ʊ���һƱ��˰�ɿ����ȡ˰Ʊ����Ϊ" + sphm);
                    jks.setSphm(sphm); // ����һƱ��˰�ɿ����������
                    // ����˰Ʊ����
                    sb = new StringBuffer();
                    sb.append("UPDATE SBDB.SB_JL_SBJKZB SET sphm=");
                    sb.append(StringUtils.getSQLStr(sphm));
                    sb.append(",yhdm=");
                    sb.append(StringUtils.getSQLStr(sfxy.getYhdm()));
                    sb.append(",yhmc=");
                    sb.append(StringUtils.getSQLStr(sfxy.getYhmc()));
                    sb.append(",zh=");
                    sb.append(StringUtils.getSQLStr(sfxy.getZh()));
                    sb.append(" WHERE jkpzh IN (");
                    for (int j = 0; j < jkpzhs.length; j++) {
                        if (j != 0) {
                            sb.append(",");
                        }
                        sb.append(StringUtils.getSQLStr(jkpzhs[j]));
                    }
                    sb.append(")");
                    sql = sb.toString();
                    log(sql);
                    st.addBatch(sql);
                }
            }
            st.executeBatch();
            st.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e);
        }
        finally {
            DBResource.destroyConnection(con);
        }
        return ypdsJksList;
    }

    private void log(String str) {
        if (ApplicationConstant.DEBUG_FLAG) {
            System.out.println("[WSSB DEBUG " + (new Date()) + "]" + str);
        }
    }
    
    /**
     * ˰��������ʼ���ڳ���
     */
    public static final String SKSSKSRQ = "SKSSKSRQ";

    /**
     * ˰�������������ڳ���
     */
    public static final String SKSSJSRQ = "SKSSJSRQ";

    /**
     * ˰������������ȳ���
     */
    public static final String SKSSRQ_ND = "ND";
    
    /**
     * @Description: TODO ����С΢��ҵ����Ӫҵ˰��
     * @param mxInf
     * @return
     */
    private List separator(DeclareInfor declareInfor)
    {
    	List jmXwqyList = new ArrayList();	//���������list
    	
    	//δ������ϸ����
    	List mxList = declareInfor.getSbjkmxInfo();


    	if(mxList==null)
    	{
    		return null;
    	}
    	
	
    	/*------С΢��ҵӪҵ˰����:�Ƴ�Դ���ݣ�����ר�ŵ�list����---------*/
    	boolean hasYysXwqy = false;					//�����Ƴ�����˰
    		
    	/*-------------------------------------�±�-----------*/
    	List jmXwqyYysYdList = new ArrayList();		//�¶�Ӫҵ˰С΢��ҵ
    	double sumYdJsJeXwqyYys = 0.0;
    	Iterator iteratorYd = mxList.iterator();	//���������������Ƴ��� ��ϸ�е�������ע��ConcurrentModifiedException	
    	while(iteratorYd.hasNext())
    	{
    		Sbjkmx sbjkmx = (Sbjkmx)iteratorYd.next();
    		
    		String szsmdm_temp = sbjkmx.getSzsmdm();
    		//˰��˰Ŀ�� �Ƿ�������ɽ��Ӫҵ˰
    		if("02".equals(szsmdm_temp.substring(0, 2)) && !"020091".equals(szsmdm_temp) && !"020092".equals(szsmdm_temp))
    		{	
        		if("07".equals(sbjkmx.getZqlxdm()))
        		{
        			jmXwqyYysYdList.add(sbjkmx);
        			iteratorYd.remove();
        			sumYdJsJeXwqyYys+=sbjkmx.getJsje().doubleValue();
        		}
    		}
    	}
    	
    	//�Ƿ�3���˰���
    	if(sumYdJsJeXwqyYys>30000 || sumYdJsJeXwqyYys == 0){
    		mxList.addAll(jmXwqyYysYdList);
    	}else{
    		jmXwqyList.addAll(jmXwqyYysYdList);
    		hasYysXwqy = true;
    	}
    	
    	/*----------------------------����-------------------*/
    	List jmXwqyYysJdList = new ArrayList();		//����Ӫҵ˰С΢��ҵ
    	double sumJdJsJeXwqyYys = 0.0;
    	Iterator iteratorJd = mxList.iterator();	//���������������Ƴ��� ��ϸ�е�������ע��ConcurrentModifiedException	
    	while(iteratorJd.hasNext())
    	{
    		Sbjkmx sbjkmx = (Sbjkmx)iteratorJd.next();
    		String szsmdm_temp = sbjkmx.getSzsmdm();
    		//˰��˰Ŀ�� �Ƿ�������ɽ��Ӫҵ˰
    		if("02".equals(szsmdm_temp.substring(0, 2)) && !"020091".equals(szsmdm_temp) && !"020092".equals(szsmdm_temp))
    		{		
        		if("06".equals(sbjkmx.getZqlxdm()))
        		{
        			jmXwqyYysJdList.add(sbjkmx);
        			iteratorJd.remove();
        			sumJdJsJeXwqyYys+=sbjkmx.getJsje().doubleValue();
        		}
    		}
    	}
    	
    	//�Ƿ�9���˰���
    	if(sumJdJsJeXwqyYys>90000 ||  sumJdJsJeXwqyYys == 0){
    		mxList.addAll(jmXwqyYysJdList);
    	}else{
    		jmXwqyList.addAll(jmXwqyYysJdList);
    		hasYysXwqy = true;
    	}
    	
    	/*---------------------------------------------����Ӫҵ˰������ͬʱ���⸽�ӷ�----------*/
    	if(hasYysXwqy == true)
    	{
    		Iterator iteratorfjf = mxList.iterator();	//���������������Ƴ��� ��ϸ�е�������ע��ConcurrentModifiedException	
        	while(iteratorfjf.hasNext())
        	{
        		Sbjkmx sbjkmx = (Sbjkmx)iteratorfjf.next();
        		String szsmdm_temp = sbjkmx.getSzsmdm();
        		if("540010".equals(szsmdm_temp) || "510010".equals(szsmdm_temp) || "100010".equals(szsmdm_temp))
        		{
        			iteratorfjf.remove();
        			jmXwqyList.add(sbjkmx);
        		}	
        	}
    	}
    	
    	
    	/*---------------------------------------------------------�����Ļ���ҵ�����--------*/
    	List jmXwqyWhsyjsfYdList = new ArrayList();		//�Ļ���ҵ�����
    	double sumYdJsJeXwqyWhsyjsf = 0.0;
    	Iterator iteratorWhsyjsf = mxList.iterator();	//���������������Ƴ��� ��ϸ�е�������ע��ConcurrentModifiedException	
    	while(iteratorWhsyjsf.hasNext())
    	{
    		Sbjkmx sbjkmx = (Sbjkmx)iteratorWhsyjsf.next();
    		
    		String szsmdm_temp = sbjkmx.getSzsmdm();
    		//˰��˰Ŀ�� �Ƿ�������ɽ��Ӫҵ˰
    		if("53".equals(szsmdm_temp.substring(0, 2)) && !"530091".equals(szsmdm_temp) && !"530092".equals(szsmdm_temp))
    		{	
    				
        		if("07".equals(sbjkmx.getZqlxdm()))
        		{
        			jmXwqyWhsyjsfYdList.add(sbjkmx);
        			iteratorWhsyjsf.remove();
        			sumYdJsJeXwqyWhsyjsf+=sbjkmx.getJsje().doubleValue();
        		}
    		}
    	}
    	
    	//�Ƿ�3���˰���
    	if(sumYdJsJeXwqyWhsyjsf>30000){
    		mxList.addAll(jmXwqyWhsyjsfYdList);
    	}else{
    		jmXwqyList.addAll(jmXwqyWhsyjsfYdList);
    	}
    	
    	
    	
    	
    	/*--------С΢��ҵ���⡪���Ļ���ҵ�����------------*/
    	
    	
    	
    	//һԪ�����ں�����ʵ��
    	/*--*/
    	
    	return jmXwqyList;
    	
    }
    
    /**
     * �����±����͵�˰����������
     * @param curDate ��ǰ����
     * @return Map ʹ��Key �� Skssrq.SKSSKSRQ �õ� ˰��������ʼ����Timestamp
     *             ʹ��Key �� Skssrq.SKSSJSRQ �õ� ˰��������������Timestamp
     *             ʹ��Key �� Skssrq.SKSSRQ_ND �õ� ˰�������������ڵ���� String
     */
    public Map monthSkssrq(Date curDate)
    {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(curDate);

        int year = calendar.get(calendar.YEAR);
        String nd = new Integer(year).toString();
        int month = calendar.get(calendar.MONTH);

        int maxDay = calendar.getActualMaximum(calendar.DAY_OF_MONTH);

        // ���µ�һ��
        Timestamp skssksrqDate =
            new Timestamp(new GregorianCalendar(year, month, 1).getTime().getTime());

        // �������һ��
        Timestamp skssjsrqDate =
            new Timestamp(new GregorianCalendar(year, month, maxDay).getTime().getTime());
        calendar.add(calendar.MONTH, 1);

        Map retMap = new HashMap();
        retMap.put(SKSSKSRQ, skssksrqDate);
        retMap.put(SKSSJSRQ, skssjsrqDate);
        retMap.put(SKSSRQ_ND, nd);

        return retMap;
    }

    /**
     * @Description: TODO �����������
     * @param vo
     * @return
     * @throws Exception
     */
    public Boolean doCheckZqlx (VOPackage vo) throws Exception
    {
    	boolean result = false;
    	Map checkMap = (Map)vo.getData();
    	
    	Sbjkmx sbjkmx = (Sbjkmx) checkMap.get("DATE");
    	String zqlxdm = (String) checkMap.get("ZQLX");
    	
    	String szsmdm = sbjkmx.getSzsmdm();
    	
    	String jsjdm = sbjkmx.getJsjdm();
   	 
   	 	SWDJJBSJ jbsj = FriendHelper.getSWDJJBSJ(jsjdm);
		
   	 	String djzclxdm = jbsj.getDjzclxdm();
   	 	
   	 	result = checkIsCurrentMoney(szsmdm, djzclxdm, zqlxdm);
    	return new Boolean(result);
    }
    
    /**
     * @Description: TODO ����Ƿ��ǵ���˰��
     * @return
     * @throws BaseException 
     */
    private boolean checkIsCurrentMoney(String szsmdm ,String djzclxdm, String zqlxdm ) throws BaseException
    {
    	boolean result = false;
    	
    	try {
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
    		String nowString =sdf.format(new Date());
    		
    		String querySQL = "select  *   from sbdb.sb_jl_zqrl where szsmdm = ?   and djzclxdm = ?   and zqqsrq <= to_date(?, 'yyyyMM')   and zqzzrq >= to_date(?, 'yyyyMM')   and zqlxdm = ?   ";
    		
    		
			Connection conn = DBResource.getConnection(DBResource.DB_SHENBAO);
			PreparedStatement ps = conn.prepareStatement(querySQL);
			
			ps.setString(1, szsmdm);
			ps.setString(2, djzclxdm);
			ps.setString(3, nowString);
			ps.setString(4, nowString);
			ps.setString(5, zqlxdm);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				result = true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw ExceptionUtil.getBaseException(e);
		}
    	
    	return result;
    	
    }
	    
    /**
     * ������������
     *
     * @param voPackage
     *            ǰ̨���������걨��Ϣ(����data����ΪDeclareInfo)
     * @return (��ȡDeclareInfo�е�һ����ʶȷ���Ƿ���Ҫ���ؽɿ�����)<br>
     *         ����Ҫ���ؽɿ�����ʱ ���ط���һ����װ��map<br>
     *         ����Ҫʱֻ�Ƿ���Boolean.True(�ɹ�ʱ)��������������׳��쳣��Ϣ<br>
     * @throws Exception
     *             �׳��쳣
     */
    private Object doGsdzHk(VOPackage vo) throws Exception {
        // 0.�������
        Map rnMap = null;
        String showMsg = "";
        Map mapDeclareInfor = null;
        Sfxy sfxy = null;
        UserData ud = null;
        Map inMap = null;
        DzyjsjVO dzyj = null;
        Map jkdata = null;
        // 1.��ʼ��
        // /1.0.�����ʼ��
        rnMap = new HashMap();
        jkdata = new HashMap();
        // /1.1.ֵ�����ʼ��
        inMap = (Map) vo.getData();
        mapDeclareInfor = (Map) inMap.get("declareMap");
        sfxy = (Sfxy) inMap.get("sfxy");
        //dzyj = (DzyjsjVO) inMap.get(ZhsbMapConstant.CA_QMSJ_VO);
        //Yh yh = (Yh) inMap.get("yh");
        SWDJJBSJ jbsj = (SWDJJBSJ) inMap.get("jbsj");
        // /1.2.��ȡ�û������˻�����
        ud = vo.getUserData();
        boolean callYyaqSignFlag = ud.caflag;
        //DzyjHelper dh = new DzyjHelper();

   
        
        // 3.�ж��ۿ�����
        boolean kkflag = false;
        if (callYyaqSignFlag) {
            if (sfxy != null) {
               // if (CodeConstant.PRINT_YPDS_KM == printTag) {
                    kkflag = true;
                //}
            }
        }
        log("kkflag=" + kkflag);
        // 4.�ж��Ƿ�����Э�黧����һƱ��˰�ɿ��飬���Ҹ�������Э���޸�������Ϣ
        List ypdsJkss = null;
        if (kkflag) {
        	jkdata.put((String)inMap.get("sbbh"), (String)inMap.get("sbbh"));
            System.out.println("============����˰Ʊ���벢���������ݿ⿪ʼ==============");
            vo.setData(jkdata);
            vo.setAction(ActionConstant.INT_ACTION_YPDS_GENETATE);
            ypdsJkss = this.doGenerateYpdsJks(vo, sfxy);
            System.out.println("============����˰Ʊ���벢���������ݿ����==============");
        }


        // 7.�ж��Ƿ�����Э�黧��ִ�����пۿ�,����˰�����ۿ�ӿ�

        if (kkflag) {
            System.out.println("========�����걨����˰�����ۿ�ӿڿ�ʼ[" + ud.getYhid() + "|" +
                               sfxy.getYhdm() + "|" + sfxy.getZh()
                               + "]===========");
            try {
                SKHAdaptor sa = new SKHAdaptor();
                double hjzse = 0.00;
                YPDSJKS ypdsJks = null;
                for (int i = 0; i < ypdsJkss.size(); i++) {
                    ypdsJks = (YPDSJKS) ypdsJkss.get(i);
                    hjzse += Double.parseDouble(ypdsJks.getSjjexx());
                }
                String hjStr = StringUtil.getFormatData(new BigDecimal(hjzse).
                    toString());
                Map retMap = (Map) sa.transferMoneyFromNsrZhToGk(jbsj.
                    getSwjgzzjgdm().substring(0, 2), hjzse, ypdsJkss,
                    jbsj.getSwjgzzjgdm(), ud, sfxy);
                String result = (String) retMap.get(sa.YHKK_KEY_RESULT);
                String addWord = (String) retMap.get(sa.YHKK_KEY_ADDWORD);
                if (sa.YHKK_RESULT_SUCCESS.equals(result)) {
                    showMsg += "����ɹ�����" + sfxy.getYhmc() + "��" + sfxy.getZh() +
                        "�˻����ۿ������" + hjStr + "Ԫ��";
                    // 5.�ж��Ƿ�����Э�黧�Խ������пۿ�֮ǰ�������־����
                    log("===========���ú�̨���������־�ɹ���ʼ===============");
                    vo.setAction(ActionConstant.INT_ACTION_ZWBS_YHKK);
                    this.doModifyZwbsYhkk(vo, 0);
                    log("===========���ú�̨���������־�ɹ����===============");
                }
                else if (sa.YHKK_RESULT_NOREASON.equals(result)) {
                    showMsg = "���з�����Ϣ���������ʵ�����˻���";
                    log("===========���ú�̨���������־״̬������ʼ===============");
                    vo.setAction(ActionConstant.INT_ACTION_ZWBS_YHKK);
                    this.doModifyZwbsYhkk(vo, 1);
                    log("===========���ú�̨���������־״̬�������===============");
                }
                else if (sa.YHKK_RESULT_LOST.equals(result)) {
                    throw new ApplicationException(addWord);
                }
            }
            catch (Exception e) {
            	e.printStackTrace();
            	if(e instanceof ApplicationException){
            		throw new Exception(e.getMessage());
            	}else{
                   throw new Exception("��5���Ӻ󡾲鿴���ڽɿ��顿�˶��Ƿ��Ѿ��γɡ����ӽɿ�ר�ýɿ��顿������ʱ�˶������˻������������⣬���뼼��֧����ϵ��лл��");
            	}
            }
        }

        // 8.����������
        rnMap.put("SBBH", sbbh);
        rnMap.put("reObject", jkdata);
        rnMap.put("showMsg", showMsg);
        rnMap.put("sskk", Boolean.valueOf(kkflag));
//        if (callYyaqSignFlag) {
//            rnMap.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
//        }
        // 99.����ֵ
        isReturnPaymentInfo=true;
        if (isReturnPaymentInfo) {
            // /99.0.���ذ���˰���������ֵ��ɽɿ�����list���ɵ�Map
            return rnMap;
        }
        else {
            // /99.1.����null
            return null;
        }
    }
}
