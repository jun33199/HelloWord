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
 * Title: 北京地税综合管理系统 申报征收模块
 * </p>
 * <p>
 * Description: 上门上网的综合申报的后台processor
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

    // 帐务标识代码(默认值为20个0)
    public final static String ZWBS = "00000000000000000000";

    // 是否返回缴款数据默认为false
    private boolean isReturnPaymentInfo = false;

    // 打印标识
    private int printTag;
    
    // 征期日历申报方式
    private int zqlxsbfs;
    
    private BigDecimal sumhjje = new BigDecimal("0.00");

    /**
     * 申报缴款主表信息
     */
    private Sbjkzb sbjkzb;

    /**
     * 申报缴款明细数据
     */
    private List sbjkmxInfo;

    /**
     * 计算机代码
     */
    private String jsjdm;

    /**
     * orManage的常量
     */
    private long SESSIONID = 0;

    /**
     * 申报编号
     */
    private String sbbh;

    // 构造方法
    public ZhsbProcessor() {
    }

    // 实现Processor接口
    public Object process(VOPackage vOPackage) throws BaseException {
        switch (vOPackage.getAction()) {
            case ActionConstant.INT_ACTION_SAVE:
                try {
                    return doSave(vOPackage);
                }
                catch (Exception ex) {
                    throw ExceptionUtil.getBaseException(ex, "保存缴款数据失败!");
                }
            case ActionConstant.INT_ACTION_QUERY:
                try {
                    return doQuery(vOPackage, CodeConstant.SJLY_SB_SBLR);
                }
                catch (Exception ex) {
                    throw ExceptionUtil.getBaseException(ex, "查询缴款数据失败！");
                }
            case ActionConstant.INT_ACTION_DELETE:
                try {
                    return doDel(vOPackage);
                }
                catch (Exception ex) {
                    throw ExceptionUtil.getBaseException(ex,
                        "作废缴款数据失败！");
                }
            case ActionConstant.INT_ACTION_PRINT: // 一票多税打印请求
                try {
                    return doPrint(vOPackage);
                }
                catch (Exception ex) {
                    throw ExceptionUtil.getBaseException(ex,
                        "获取一票多税打印数据失败！");
                }
            case ActionConstant.INT_ACTION_GETPROPERTY: // 取得属性值
                try {
                    return getProperty(vOPackage);
                }
                catch (Exception ex) {
                    throw ExceptionUtil.getBaseException(ex,
                        "取得属性值失败！");
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
                    throw ExceptionUtil.getBaseException(ex, "保存小微企业减免信息失败");
                }
               
                // case ActionConstant.INT_ACTION_YPDS_GENETATE: //银行扣款成功
                // try {
                // return doGenerateYpdsJks(vOPackage);
                // }
                // catch (Exception ex) {
                // ex.printStackTrace();
                // throw ExceptionUtil.getBaseException(ex,
                // "修改账务标志失败，扣款已完成，请与管理员立即联系");
                // }
            case ActionConstant.INT_ACTION_ZWBS_YHKK: // 银行扣款成功
                try {
                    // return doModifyZwbsYhkk(vOPackage);
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                    throw ExceptionUtil.getBaseException(ex,
                        "修改账务标志失败，扣款已完成，请与管理员立即联系");
                }
            case ActionConstant.INT_ACTION_DELETEALL: // 删除所有当笔申报数据
                try {
                    doDelBySBBH(vOPackage);
                    return null;
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                    throw ExceptionUtil.
                        getBaseException(ex,
                        "申报失败，申报数据未删除，请与管理员立即联系");
                }
            case ActionConstant.INT_ACTION_QUERYYSB: // 查询已申报数据
                try {
                    return doQueryysb(vOPackage);
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                    throw ExceptionUtil.
                        getBaseException(ex,
                        "查询已申报失败，请与管理员立即联系");
                }
            default:
                throw ExceptionUtil.
                    getBaseException(new
                    Exception("系统错误,请与技术支持联系!"));
        }
    }

    // /**
     // * 保存缴款数据
     // * @param voPackage 前台传过来的申报信息(其中data部分为DeclareInfo)
     // * @return (读取DeclareInfo中的一个标识确定是否需要返回缴款数据)<br>
     // * 当需要返回缴款数据时 返回返回一个封装的map<br>
     // * 不需要时只是返回Boolean.True(成功时)当其他情况都会抛出异常信息<br>
     // * @throws Exception 抛出异常
     // */
    // private Object doSave(VOPackage voPackage) throws Exception {
    // //参数检查
    // if(voPackage == null || voPackage.getData() == null
    // || voPackage.getUserData() == null )
    // {
    // throw ExceptionUtil.getBaseException(new Exception(), "提交的数据不合法!");
    // }
    // //得到前台传过来的declareInfor的Map
    // Map mapDeclareInfor = (Map) voPackage.getData();
    // DeclareInfor declareInfor = null;
    // Set set = mapDeclareInfor.keySet();
    // String key = null;
    // Map jkdata = new HashMap();
    // HashMap tempData = new HashMap();
    // UserData ud = voPackage.getUserData();
    // //为该次申报的该税款类型取一个申报编号
    // sbbh = getSbbh(ud.getYhid());
    // //根据不同的税款类型区分生成缴款凭证
    // for (Iterator keyset = set.iterator(); keyset.hasNext(); ) {
    // key = (String) keyset.next();
    // declareInfor = (DeclareInfor) mapDeclareInfor.get(key);
    // isReturnPaymentInfo = declareInfor.isIsReturnPaymentInfo();
    // printTag = declareInfor.getPrintTag();
    // if (declareInfor.getSbjkmxInfo() == null
    // || declareInfor.getSbjkmxInfo().size() < 1
    // || declareInfor.getSbjkzb() == null) {
    // throw ExceptionUtil.getBaseException(new Exception(), "申报的数据为空!");
    // }
    // else {
    // sbjkzb = declareInfor.getSbjkzb();
    // sbjkmxInfo = declareInfor.getSbjkmxInfo();
    // }
    //
    // //创建缴款数据并进行数据库插入操作
    // Object object = createSbJkData(declareInfor.getSbjkzb(),
    // declareInfor.getSbjkmxInfo());
    // jkdata.put(key, object);
    // tempData = listToMap(object); //将数据从ArrayList 转换为map格式
    // jkdata.put(sbbh, tempData); //按照申报编号对数据进行区分，并存入map中
    // }
    // if (isReturnPaymentInfo) {
    // return jkdata; //返回按照税款类型来分的由缴款数据list构成的Map
    // }
    // else {
    // return null;
    // }
    // }

    /**
     * 保存缴款数据
     *
     * @param voPackage
     *            前台传过来的申报信息(其中data部分为DeclareInfo)
     * @return (读取DeclareInfo中的一个标识确定是否需要返回缴款数据)<br>
     *         当需要返回缴款数据时 返回返回一个封装的map<br>
     *         不需要时只是返回Boolean.True(成功时)当其他情况都会抛出异常信息<br>
     * @throws Exception
     *             抛出异常
     */
    private Object doSave(VOPackage vo) throws Exception {
        // 0.句柄申明
        Map rnMap = null;
        String showMsg = "";
        Map mapDeclareInfor = null;
        Sfxy sfxy = null;
        UserData ud = null;
        Map inMap = null;
        DzyjsjVO dzyj = null;
        Map jkdata = null;
        // 1.初始化
        // /1.0.句柄初始化
        rnMap = new HashMap();
        jkdata = new HashMap();
        // /1.1.值对象初始化
        inMap = (Map) vo.getData();
        mapDeclareInfor = (Map) inMap.get("declareMap");
        sfxy = (Sfxy) inMap.get("sfxy");
        dzyj = (DzyjsjVO) inMap.get(ZhsbMapConstant.CA_QMSJ_VO);
        Yh yh = (Yh) inMap.get("yh");
        SWDJJBSJ jbsj = (SWDJJBSJ) inMap.get("jbsj");
        // /1.2.获取用户基本账户对象
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
            System.out.println("============保存签名数据结束==============");
            // System.out.println(dzyj.getJssj().toString());
        }

        // 2.基本业务流程
        // /2.0.子句柄申明及初始化
        DeclareInfor declareInfor = null;
        Set set = mapDeclareInfor.keySet();
        String key = null;
        HashMap tempData = new HashMap();
        // /2.1.为该次申报的该税款类型取一个申报编号
        sbbh = getSbbh(ud.getYhid());
        // /2.2.根据不同的税款类型区分生成缴款凭证
        for (Iterator keyset = set.iterator(); keyset.hasNext(); ) {
            key = (String) keyset.next();
            // //2.2.0.获取值对象
            declareInfor = (DeclareInfor) mapDeclareInfor.get(key);
            isReturnPaymentInfo = declareInfor.isIsReturnPaymentInfo();
            printTag = declareInfor.getPrintTag();
            if (declareInfor.getSbjkmxInfo() == null ||
                declareInfor.getSbjkmxInfo().size() < 1
                || declareInfor.getSbjkzb() == null) {
                throw ExceptionUtil.getBaseException(new Exception(),
                    "申报的数据为空!");
            }
            else {
                sbjkzb = declareInfor.getSbjkzb();
                sbjkmxInfo = declareInfor.getSbjkmxInfo();
            }
            /*获取委托单位代征的税种税目，判断查询结果中是否存在税款类型为正常的税目要显示 modified by Junbing Tu 2014.07*/
            Sbjkmx sbjkmxTmp = null;
    		BigDecimal sumhjje = new BigDecimal("0.00");
            for (int i = 0; i < sbjkmxInfo.size(); i++) {
                sbjkmxTmp = (Sbjkmx) sbjkmxInfo.get(i);
                sumhjje = sumhjje.add(sbjkmxTmp.getSjse());
            }
            
            
            // //2.2.1.创建缴款数据并进行数据库插入操作
            Object object = createSbJkData(declareInfor.getSbjkzb(),
                                           declareInfor.getSbjkmxInfo());
           
            // //2.2.2.将数据从ArrayList 转换为map格式
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
            
            // //2.2.3.按照申报编号对数据进行区分，并存入map中
            jkdata.put(sbbh, tempData);
        }
        
   /*begin================================================合计小于一元处理==============================================*/
       Map data = (Map) jkdata.get(sbbh);
       boolean msgFlag = false;
       if(inMap.get("lwFlag") != null) {
    	   boolean lwFlag = ((Boolean) inMap.get("lwFlag")).booleanValue();
    	   List jkshList = (List) data.get("jkshList");
    	   
    	   if(lwFlag) {//联网银行，合并缴款书
    		   double hj = 0;
        	   for(int ii=0; ii<jkshList.size(); ii++) {
        		   String jksh = (String) jkshList.get(ii);
        		   Map jks = (Map) data.get(jksh);
        		   hj += ((BigDecimal) jks.get("jehj")).doubleValue();
        	   }
        	   if(hj < 1) {
        		   showMsg = "缴款书金额不足1元，根据国家税务总局2012年25号公告的规定，不予征收!";
        		   msgFlag = true;
        		   for(int ii=0; ii<jkshList.size(); ii++) {
            		   String jksh = (String) jkshList.get(ii);
            		   data.remove(jksh);
            	   }
        		   
        		   //数据转入his表
        		   this.insertToHisBySbbh(sbbh, vo.getUserData());
        		   //根据申报编号删除数据库临时数据
        		   this.deleteTempJksBySbbh(sbbh);
        		   
        		   data.remove("jkshList");
            	   jkdata.remove(sbbh);
            	   // 8.整理返回数据
        	        rnMap.put("reObject", jkdata);
        	        rnMap.put("showMsg", showMsg);
        	        rnMap.put("sskk", Boolean.valueOf(false));
        	        if (callYyaqSignFlag) {
        	            rnMap.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
        	        }
        	        // 99.返回值
        	        if (isReturnPaymentInfo) {
        	            // /99.0.返回按照税款类型来分的由缴款数据list构成的Map
        	            return rnMap;
        	        }
        	        else {
        	            // /99.1.返回null
        	            return null;
        	        }
        	   }
    	   } else {//非联网银行
    		   int removeNum = 0;
    		   for(int ii=0; ii<jkshList.size(); ii++) {
        		   String jksh = (String) jkshList.get(ii);
        		   Map jks = (Map) data.get(jksh);
        		   if(((BigDecimal) jks.get("jehj")).doubleValue() < 1) {
        			   data.remove(jksh);
        			   //数据转入his表
            		   this.insertToHisByJksh(jksh, vo.getUserData());
        			   //根据缴款书号删除数据库临时数据
            		   this.deleteTempJksByJksh(jksh);
        			   removeNum ++;
        		   }
        	   }
    		   //全部移除，直接返回
    		   if(jkshList.size() == removeNum) {
    			   showMsg += "缴款书金额不足1元，根据国家税务总局2012年25号公告的规定，不予征收!";
    			   msgFlag = true;
    			   jkdata.remove(sbbh);
    			   // 8.整理返回数据
    		        rnMap.put("reObject", jkdata);
    		        rnMap.put("showMsg", showMsg);
    		        rnMap.put("sskk", Boolean.valueOf(false));
    		        if (callYyaqSignFlag) {
    		            rnMap.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
    		        }
    		        // 99.返回值
    		        if (isReturnPaymentInfo) {
    		            // /99.0.返回按照税款类型来分的由缴款数据list构成的Map
    		            return rnMap;
    		        }
    		        else {
    		            // /99.1.返回null
    		            return null;
    		        }
    		   } 
    		   data.remove("jkshList");
    		   if(0 != removeNum) {
    			   jkdata.put(sbbh, data);
    			   showMsg += "部分缴款书金额不足1元，根据国家税务总局2012年25号公告的规定，不予征收!";
    			   msgFlag = true;
    		   }
    	   }
       }
       
       data.remove("jkshList");
	   if(msgFlag == false) {
		   showMsg += "申报成功";
	   }
   /*end================================================合计小于一元处理==============================================*/
        
        // 3.判定扣款条件
        boolean kkflag = false;
        if (callYyaqSignFlag) {
            if (sfxy != null) {
                if (CodeConstant.PRINT_YPDS_KM == printTag) {
                    kkflag = true;
                }
            }
        }
        log("kkflag=" + kkflag);
        // 4.判定是否三方协议户生成一票多税缴款书，并且根据三方协议修改银行信息
        List ypdsJkss = null;
        if (kkflag) {
            System.out.println("============生成税票号码并更新至数据库开始==============");
            vo.setData(jkdata);
            vo.setAction(ActionConstant.INT_ACTION_YPDS_GENETATE);
            ypdsJkss = this.doGenerateYpdsJks(vo, sfxy);
            System.out.println("============生成税票号码并更新至数据库结束==============");
        }
        // 5.判定是否三方协议户以进行银行扣款之前的账务标志调整
        // if(kkflag){
        // log("===========调用后台更新账务标志成功开始===============");
        // vo.setAction(ActionConstant.INT_ACTION_ZWBS_YHKK);
        // this.doModifyZwbsYhkk(vo, 0);
        // log("===========调用后台更新账务标志成功完毕===============");
        // }

        // 6.如果是CA户则进行签名回写
        // start modified code by qianchao 2006-2-7
        if (callYyaqSignFlag) {
            log("============回填申报编号到签名开始==============");
            // 获取所有的申报交款数据并生成税票号码
            Iterator keyset = jkdata.keySet().iterator();
            while (keyset.hasNext()) {
                String sbbhCA = (String) keyset.next();
                dzyj.setYwuid(sbbhCA);
                try {
                    log("=签名YWUID回填= yh:" + dzyj.getJsjdm() + " lsh:" +
                        dzyj.getLsh() + "  ywuid:" + sbbhCA);
                    CAProxy.getInstance().updateSignedData(dzyj);
                }
                catch (Exception e) {
                    e.printStackTrace();
                    throw e;
                }
            }
            log("============回填申报编号到签名结束==============");
        }
        // end modified code by qianchao 2006-2-7

        // 7.判定是否三方协议户以执行银行扣款,调用税库银扣款接口

        if (kkflag) {
            System.out.println("========网上申报调用税库银扣款接口开始[" + ud.getYhid() + "|" +
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
                    showMsg += ",从" + sfxy.getYhmc() + "的" + sfxy.getZh() +
                        "账户共扣款人民币" + hjStr + "元";
                    // 5.判定是否三方协议户以进行银行扣款之前的账务标志调整
                    log("===========调用后台更新账务标志成功开始===============");
                    vo.setAction(ActionConstant.INT_ACTION_ZWBS_YHKK);
                    this.doModifyZwbsYhkk(vo, 0);
                    log("===========调用后台更新账务标志成功完毕===============");
                }
                else if (sa.YHKK_RESULT_NOREASON.equals(result)) {
                    showMsg = "银行返回信息不明，请核实银行账户余额。";
                    log("===========调用后台更新账务标志状态锁定开始===============");
                    vo.setAction(ActionConstant.INT_ACTION_ZWBS_YHKK);
                    this.doModifyZwbsYhkk(vo, 1);
                    log("===========调用后台更新账务标志状态锁定完毕===============");
                }
                else if (sa.YHKK_RESULT_LOST.equals(result)) {
                    throw new ApplicationException(addWord);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                throw ExceptionUtil.getBaseException(e,
                    "请5分钟后【查看本期缴款书】核对是否已经形成【电子缴库专用缴款书】，并及时核对银行账户金额；如仍有问题，请与技术支持联系，谢谢！");
            }
        }

        // 8.整理返回数据
        rnMap.put("SBBH", sbbh);
        rnMap.put("reObject", jkdata);
        rnMap.put("showMsg", showMsg);
        rnMap.put("sskk", Boolean.valueOf(kkflag));
        if (callYyaqSignFlag) {
            rnMap.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
        }
        // 99.返回值
        if (isReturnPaymentInfo) {
            // /99.0.返回按照税款类型来分的由缴款数据list构成的Map
            return rnMap;
        }
        else {
            // /99.1.返回null
            return null;
        }
    }
    
    private Object doSave_jm(VOPackage vo) throws Exception {
        // 0.句柄申明
        Map mapDeclareInfor = null;
        Sfxy sfxy = null;
        UserData ud = null;
        Map inMap = null;
        DzyjsjVO dzyj = null;
        Map jkdata = null;
        // 1.初始化
        // /1.0.句柄初始化
        
        jkdata = new HashMap();
        // /1.1.值对象初始化
        inMap = (Map) vo.getData();
        
        mapDeclareInfor = (Map) inMap.get("declareMap");
        sfxy = (Sfxy) inMap.get("sfxy");
        dzyj = (DzyjsjVO) inMap.get(ZhsbMapConstant.CA_QMSJ_VO);
        
        // /1.2.获取用户基本账户对象
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
            System.out.println("============保存签名数据结束==============");
            // System.out.println(dzyj.getJssj().toString());
        }

        // 2.基本业务流程
        // /2.0.子句柄申明及初始化
        DeclareInfor declareInfor = null;
        Set set = mapDeclareInfor.keySet();
        String key = null;
        
        // /2.1.为该次申报的该税款类型取一个申报编号
        if(inMap.get("SBBH")==null || "".equals(inMap.get("SBBH")))
        {
        	sbbh = getSbbh(ud.getYhid());
        }else {
			sbbh = (String) inMap.get("SBBH");
		}
        // /2.2.根据不同的税款类型区分生成缴款凭证
        for (Iterator keyset = set.iterator(); keyset.hasNext(); ) {
            key = (String) keyset.next();
            // //2.2.0.获取值对象
            declareInfor = (DeclareInfor) mapDeclareInfor.get(key);
            isReturnPaymentInfo = declareInfor.isIsReturnPaymentInfo();
            printTag = declareInfor.getPrintTag();
            
            /*获取委托单位代征的税种税目，判断查询结果中是否存在税款类型为正常的税目要显示 modified by Junbing Tu 2014.07*/
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
     * 根据缴款书号，把数据插入主表his
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
			//查询
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
     * 根据审批编号，把数据插入主表his
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
			//查询
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
     * 根据缴款书号删除数据库临时数据
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
     * 根据申报编号删除数据库临时数据
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
     * 生成缴款数据并进行数据库插入操作
     *
     * @param insbjkzb
     *            申报缴款主表(已经基本填写了数据,jkpzh没有)
     * @param insbjkmxInfo
     *            申报缴款明细List
     * @param _sbbh
     *            申报编号
     * @return 操作是否成功,且如果要返回缴款数据的话:还会返回缴款数据
     * @throws BaseException
     *             异常信息
     */
    public Object createJkInfor(Sbjkzb insbjkzb, List insbjkmxInfo,
                                String _sbbh) throws BaseException {
        try {
            this.sbbh = _sbbh;
            // 1.填充每个缴款明细数据的级次,预算科目等在后台才要填写的信息
            fillSbjkmxInfo(insbjkzb, insbjkmxInfo);
            // 2.按照预算科目拆分明细数据
            List sbjkmxInfoByyskmdm = slipSbjkmxInfo(insbjkmxInfo);

            // 3.按照不同科目信息创建多个缴款主表并填写对应的缴款凭证号信息
            // 并插入数据库按照不同的打印标识返回缴款数据
            List jkdataList = creatjkData(insbjkzb, sbjkmxInfoByyskmdm);

            this.sbbh = "";
            // 返回处理
            if (isReturnPaymentInfo) {
                return jkdataList;
            }
            else {
                // 不需要返回缴款数据
                return Boolean.TRUE;
            }
        }
        catch (Exception ex) {
            throw ExceptionUtil.getBaseException(ex, "保存数据错误,请与技术支持联系!");
        }
    }

    /**
     * 生成缴款数据并进行数据库插入操作
     *
     * @param insbjkzb
     *            申报缴款主表(已经基本填写了数据,jkpzh没有)
     * @param insbjkmxInfo
     *            申报缴款明细List
     * @return 操作是否成功,且如果要返回缴款数据的话:还会返回缴款数据
     * @throws BaseException
     *             异常信息
     */
    private List createSbJkData(Sbjkzb insbjkzb, List insbjkmxInfo) throws
        BaseException {
        try {
            // 1.填充每个缴款明细数据的级次,预算科目等在后台才要填写的信息
            fillSbjkmxInfo(insbjkzb, insbjkmxInfo);
            // 2.按照预算科目拆分明细数据
            List sbjkmxInfoByyskmdm = slipSbjkmxInfo(insbjkmxInfo);

            // 3.按照不同科目信息创建多个缴款主表并填写对应的缴款凭证号信息
            // 并插入数据库按照不同的打印标识返回缴款数据
            List jkdataList = creatjkData(insbjkzb, sbjkmxInfoByyskmdm);

            // 返回处理
            return jkdataList;
        }
        catch (Exception ex) {
            throw ExceptionUtil.getBaseException(ex, "保存数据错误,请与技术支持联系!");
        }
    }

    /**
     * 生成缴款数据并进行数据库插入操作
     *
     * @param insbjkzb
     *            申报缴款主表(已经基本填写了数据,jkpzh没有)
     * @param insbjkmxInfo
     *            申报缴款明细List
     * @return 操作是否成功,且如果要返回缴款数据的话:还会返回缴款数据
     * @throws BaseException
     *             异常信息
     */
    private List createSbJkData_his(Sbjkzb insbjkzb, List insbjkmxInfo ,String zrlxdm ,String zrr) throws
        BaseException {
        try {
            // 1.填充每个缴款明细数据的级次,预算科目等在后台才要填写的信息
            fillSbjkmxInfo(insbjkzb, insbjkmxInfo);
            // 2.按照预算科目拆分明细数据
            List sbjkmxInfoByyskmdm = slipSbjkmxInfo(insbjkmxInfo);

            // 3.按照不同科目信息创建多个缴款主表并填写对应的缴款凭证号信息
            // 并插入数据库按照不同的打印标识返回缴款数据
            List jkdataList = creatjkData_his(insbjkzb, sbjkmxInfoByyskmdm ,zrlxdm ,zrr);

            // 返回处理
            return jkdataList;
        }
        catch (Exception ex) {
            throw ExceptionUtil.getBaseException(ex, "保存数据错误,请与技术支持联系!");
        }
    }

    
    /**
     * 生成缴款数据并进行数据库插入操作
     *
     * @param declareInfor
     *            申报数据
     * @param _sbbh
     *            申报编号
     * @return 操作是否成功,且如果要返回缴款数据的话:还会返回缴款数据
     * @throws BaseException
     *             操作异常
     */
    public Object createJkInfor(DeclareInfor declareInfor, String _sbbh) throws
        BaseException {
        this.sbbh = _sbbh;
        isReturnPaymentInfo = declareInfor.isIsReturnPaymentInfo();
        printTag = declareInfor.getPrintTag();
        Sbjkzb insbjkzb = declareInfor.getSbjkzb();
        List insbjkmxInfo = declareInfor.getSbjkmxInfo();
        try {
            // 1.填充每个缴款明细数据的级次,预算科目等在后台才要填写的信息
            fillSbjkmxInfo(insbjkzb, insbjkmxInfo);
            // 2.按照预算科目拆分明细数据
            List sbjkmxInfoByyskmdm = slipSbjkmxInfo(insbjkmxInfo);
            // 3.按照不同科目信息创建多个缴款主表并填写对应的缴款凭证号信息
            // 并插入数据库按照不同的打印标识返回缴款数据
            List jkdataList = creatjkData(insbjkzb, sbjkmxInfoByyskmdm);

            this.sbbh = "";
            // 返回处理
            if (isReturnPaymentInfo) {
                // 需要返回缴款数据
                return jkdataList;
            }
            else {
                // 不需要返回缴款数据
                return Boolean.TRUE;
            }
        }
        catch (Exception ex) {
            throw ExceptionUtil.getBaseException(ex, "保存数据错误!");
        }
    }

    /**
     * 按照拆分后的申报缴款明细数据(多个List构成的List,List中的描述同上)创建多个申报主表数据
     *
     * @param insbjkzb
     *            申报主表的一些公共信息
     * @param sbjkmxInfo
     *            拆分后的申报明细数据
     * @return 申报缴款主表数据的List
     * @throws java.lang.Exception
     *             操作中的异常信息
     */
    private List creatjkData(Sbjkzb insbjkzb, List sbjkmxInfo) throws Exception {
        try {

            switch (printTag) {
                case CodeConstant.PRINT_YPYS: // 一票一税
                    return createSkjkzbInfor_onetax(insbjkzb, sbjkmxInfo);
                case CodeConstant.PRINT_YPDS_KM: // 一票多税(科目)
                    return createSkjkzbInfor_mortax(insbjkzb, sbjkmxInfo);
                default: // 默认为一票一税
                    return createSkjkzbInfor_onetax(insbjkzb, sbjkmxInfo);
            }
        }
        catch (Exception ex) {
            throw ExceptionUtil.getBaseException(ex, "保存数据错误,请与技术支持联系!");
        }

    }
    
    /**
     * 按照拆分后的申报缴款明细数据(多个List构成的List,List中的描述同上)创建多个申报主表数据
     *
     * @param insbjkzb
     *            申报主表的一些公共信息
     * @param sbjkmxInfo
     *            拆分后的申报明细数据
     * @return 申报缴款主表数据的List
     * @throws java.lang.Exception
     *             操作中的异常信息
     */
    private List creatjkData_his(Sbjkzb insbjkzb, List sbjkmxInfo,String zrlxdm ,String zrr) throws Exception {
        try {

            switch (printTag) {
                case CodeConstant.PRINT_YPYS: // 一票一税
                    return createSkjkzbInfor_onetax_his(insbjkzb, sbjkmxInfo ,zrlxdm ,zrr);
                case CodeConstant.PRINT_YPDS_KM: // 一票多税(科目)
                    return createSkjkzbInfor_mortax_his(insbjkzb, sbjkmxInfo ,zrlxdm ,zrr);
                default: // 默认为一票一税
                    return createSkjkzbInfor_onetax_his(insbjkzb, sbjkmxInfo ,zrlxdm ,zrr);
            }
        }
        catch (Exception ex) {
            throw ExceptionUtil.getBaseException(ex, "保存数据错误,请与技术支持联系!");
        }

    }

    /**
     * 填充申报明细List中的预算级次和科目信息
     *
     * @param insbjkzb
     *            缴款主表信息
     * @param insbjkmxInfo
     *            缴款明细信息
     * @throws Exception
     *             操作异常
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
                throw ExceptionUtil.getBaseException(ex, "获取预算科目失败!");
            }

        }
    }

    /**
     * 在缴款明细数据中添加预算级次和预算科目信息
     *
     * @param sbjkmx
     *            操作的缴款明细
     * @param insbjkzb
     *            申报缴款主表
     * @throws BaseException
     *             操作异常
     */
    private void fillSbjkmx(Sbjkmx sbjkmx, Sbjkzb insbjkzb) throws
        BaseException {
        // 3.填写税款所属时期
        if (sbjkmx.getSkssksrq() == null && sbjkmx.getSkssjsrq() == null) {
            Map map = Skssrq.getSksssq(sbjkmx.getJsjdm(), sbjkmx.getSzsmdm(),
                                       insbjkzb.getDjzclxdm(), insbjkzb
                                       .getSklxdm(), sbjkmx.getZqlxdm(),
                                       insbjkzb.getLrrq(), sbjkmx.getSjse(),
                                       sbjkmx.getKssl(), sbjkmx
                                       .getJsje());
            // 印花税网上申报项目 modified by Junbing Tu 2014.0
            //1.税款类型为“委托代征”，印花税和其他税种实缴金额 选中”是否按次“时，税款所属日期为当月; 不选”是否按次“时，税款所属日期为征期日历起止日期；
            //2.税款类型为“正常税款”，  税款所属日期为征期日历起止日期。
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
        // 如果税款所属的截止日期还是为空，则默认为上月的最后一天
        if (sbjkmx.getSkssjsrq() == null) {
            // 税款所属截止日期，默认为上个月的最后一天
            sbjkmx.setSkssjsrq( (Timestamp) Skssrq.monthSkssrq(new Date()).get(
                Skssrq.SKSSJSRQ));
        }
        if (sbjkmx.getYsjcdm() == null) { // 如果预算级次尚未赋值，则执行查找预算级次
            try {
                // 设置默认预算级次为‘地方级’
                Ysjc ysjc = Ysjc.getYsjc(Ysjc.YSJCDM_DF);
                // 调用税费管理接口得到预算级次
                com.ttsoft.bjtax.sfgl.proxy.ServiceProxy sfServiceProxy = new
                    com.ttsoft.bjtax.sfgl.proxy.ServiceProxy();
                com.ttsoft.bjtax.sfgl.common.model.Ysjc sfysjc = sfServiceProxy.
                    getYsjcInfo(insbjkzb.getJsjdm(), sbjkmx
                                .getSzsmdm(), sbjkmx.getSkssjsrq());
                if (sfysjc != null) {
                    ysjc = new Ysjc(sfysjc.getYsjcdm(), sfysjc.getYsjcmc());
                }

                sbjkmx.setYsjcdm(ysjc.getYsjcdm()); // 赋值
            }
            catch (Exception ex) {
                throw new ApplicationException(ex.getMessage());
            }
        }
        // 查找预算科目代码并填充
        // 调用计会接口，获取预算科目
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
            throw new ApplicationException("获取预算科目失败!");
        }

        if (sbjkmx.getYskmdm() == null) { // 如果预算科目代码尚未赋值，则将查询结果赋值给明细对象的对应属性
            sbjkmx.setYskmdm(yskm.getYskmdm()); // 赋值
        }

        // 入库金额
        sbjkmx.setRkje(sbjkmx.getSjse());
        sbjkmx.setSbbh(sbbh); // 设置申报编号
        if (sbjkmx.getYsjcdm().equals("21")) { // 如果是地方级
            sbjkmx.setSjfc(getFc(sbjkmx.getSjse(), yskm.getSjfcbl())); // 设置市局级分成金额
            sbjkmx.setQjfc(getFc(sbjkmx.getSjse(), yskm.getQxfcbl())); // 设置区县级分成金额
        }
    }

    /**
     * 按照预算科目信息拆分sbjkmxInfo为多个List(每个List中的sbjkmx实例有相同预算科目信) 组成的嵌套List
     *
     * @param sbjkmxInfo
     *            申报缴款明细信息(多条sbjkmx实例的List)
     * @return List 成员还是为List
     */
    private List slipSbjkmxInfo(List sbjkmxInfo) {
        List splitsbjkmx = new ArrayList();
        List paramList = new ArrayList();

        // 按照预算科目代码加税款所属开始和结束来拆分缴款明细数据
        paramList.add("getYskmdm");
        paramList.add("getSkssksrq");
        paramList.add("getSkssjsrq");
        try {
            splitsbjkmx = FindObjInList.splitListByParam(sbjkmxInfo, Sbjkmx.class,
                paramList);
        }
        catch (Exception ex) {
            Debug.out("拆分申报数据失败!");
            splitsbjkmx = null;
        }
        return splitsbjkmx;
    }

    /**
     * 把申报主表,申报明细数据插入数据库
     *
     * @param sbjkzbList
     *            申报缴款主表信息List中的值对象为:Sbjkzb
     * @param sbjkmxInfoList
     *            申报明细信息:List中的对象还是List的实例(有相同科目信息的
     *            明细的List):每个subList中的对象才是Sbjkmx
     * @param conn
     *            数据库连接
     * @throws BaseException
     *             异常信息
     */
    public void insertSbjkData(List sbjkzbList, List sbjkmxInfoList,
                               Connection conn) throws BaseException {
        // OR实例
        ORManager orManager = null;
        // 循环插入主表和明细数据
        try {
            // 获得 ORManager
            orManager = DBResource.getORManager(DBResource.OR_SHENBAO);

            // 插入主表数据
            Sbjkzb sbjkzb;
            for (Iterator sbjkzbs = sbjkzbList.iterator(); sbjkzbs.hasNext(); ) {
                sbjkzb = (Sbjkzb) sbjkzbs.next();
                sbjkzb.setSphm(sbjkzb.getJkpzh());
                orManager.makePersistent(SESSION_ID, conn, sbjkzb);
            }

            // 插入明细数据
            List sbjkmxList = null;
            for (Iterator sbjkmxLists = sbjkmxInfoList.iterator();
                 sbjkmxLists.hasNext(); ) {
                // 同一科目的明细list
                for (Iterator sbjkmxs = ( (List) sbjkmxLists.next()).iterator();
                     sbjkmxs.hasNext(); ) {
                    // 同一科目中多条明细
                    orManager.makePersistent(SESSION_ID, conn, sbjkmxs.next());
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex, "生成数据错误!");
        }
    }

    /**
     * 把申报主表,申报明细数据插入数据库
     *
     * @param sbjkzbList
     *            申报缴款主表信息List中的值对象为:Sbjkzb
     * @param sbjkmxInfoList
     *            申报明细信息:List中的对象还是List的实例(有相同科目信息的
     *            明细的List):每个subList中的对象才是Sbjkmx
     * @param conn
     *            数据库连接
     * @throws BaseException
     *             异常信息
     */
    public void insertSbjkData_his(List sbjkzbList, List sbjkmxInfoList,
                               Connection conn ,String zrlxdm ,String zrr) throws BaseException {
        // OR实例
        ORManager orManager = null;
        // 循环插入主表和明细数据
        try {
            // 获得 ORManager
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
            
            // 插入主表数据
            Sbjkzb sbjkzb;
            for (Iterator sbjkzbs = sbjkzbList.iterator(); sbjkzbs.hasNext(); ) {
            	sbjkzb = (Sbjkzb) sbjkzbs.next();
            	sbjkzb.setSphm(sbjkzb.getJkpzh());
            	
            	//由sbjkzb转his
    	        Sbjkzb_His sbjkzbHis_temp = new Sbjkzb_His();
    	        sbjkzbHis_temp.copyFrom(sbjkzb);

    			sbjkzbHis_temp.setZrlxdm(zrlxdm);
    			sbjkzbHis_temp.setZrrq(now);
    			sbjkzbHis_temp.setZrr(zrr);

                orManager.makePersistent(SESSION_ID, conn, sbjkzbHis_temp);
            }

            // 插入明细数据
            List sbjkmxList = null;
            for (Iterator sbjkmxLists = sbjkmxInfoList.iterator();
                 sbjkmxLists.hasNext(); ) {
                // 同一科目的明细list
                for (Iterator sbjkmxs = ( (List) sbjkmxLists.next()).iterator();
                     sbjkmxs.hasNext(); ) {
                    // 同一科目中多条明细
                	
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
            throw ExceptionUtil.getBaseException(ex, "生成数据错误!");
        }
    }
    
    
    // 对按照预算科目拆分后的每组明细生成缴款主表数据并填写缴款凭证号一票一税情况
    // 返回生成的缴款书信息
    private List createSkjkzbInfor_onetax(Sbjkzb insbjkzb, List sbjkmxInfo) throws
        BaseException {
        String sequence = null; // 缴款凭证号中的序列号
        // 定义数据库连接
        Connection conn = null;

        // 得到当前计算机在申报缴款主表中的最大流水号.
        try {
            // 得到录入日期的年月（6位）
            SimpleDateFormat simpleDataFormat = new SimpleDateFormat("yyyyMM");
            String yyyyMM = simpleDataFormat.format(insbjkzb.getLrrq());
            SimpleDateFormat simpleDataFormats = new SimpleDateFormat("yyMM");
            String yyMM = simpleDataFormats.format(insbjkzb.getLrrq());
            // NumberFormat nmbFormat = new DecimalFormat("000");

            // 循环中的临时变量
            List sbjkzbList = new ArrayList(); // 创建的缴款主表List
            List jkInforList = new ArrayList(); // 返回的缴款书数据List
            DeclareInfor jkInfor = null; // 一条缴款数据
            List splitsbjkmxList = null; // 按4个科目税款时期拆分后的多组明细数据list

            Sbjkmx sbjkmxtmp = null;
            Sbjkzb sbjkzbtmp = null;
            String jkpzh = null;
            List tmpSbjkmxList; // 按4个科目税款时期拆分后的一组明细数据list
            BigDecimal sjjehe; // 实缴金额
            // 获得数据库连接
            conn = DBResource.getConnection(DBResource.DB_SHENBAO);
            for (int i = 0; i < sbjkmxInfo.size(); i++) {
                // 超过4条明细拆分
                splitsbjkmxList = splitList( (List) sbjkmxInfo.get(i),
                                            SPLITNUM_SM);
                // 对拆分后的每组明细生成一条缴款主表数据并回填明细的jkpzh
                for (int k = 0; k < splitsbjkmxList.size(); k++) {
                    // 取得当前计算机代码使用的最大序号
                    // sequence = nmbFormat.format(
                    // Integer.parseInt(TrancProxy.getSequence(insbjkzb.getJsjdm(),
                    // yyyyMM)) + 1);

                    String xh = TrancProxy.getSequence(insbjkzb.getJsjdm(),
                        yyyyMM);
                    // 自增后的ASCII码
                    String xhAscii = TinyTools.stringToASCII(TinyTools.
                        asciiToString(Integer.parseInt(xh) + 1));
                    // 格式化为"000"格式
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
            // 把生成的数据插入数据库中
            insertSbjkData(sbjkzbList, sbjkmxInfo, conn);
            return jkInforList;
        }
        catch (Exception ex) {
            throw ExceptionUtil.getBaseException(ex, "保存缴款数据失败!");
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
    String sequence = null; // 缴款凭证号中的序列号
    // 定义数据库连接
    Connection conn = null;

    // 得到当前计算机在申报缴款主表中的最大流水号.
    try {
        // 得到录入日期的年月（6位）
        SimpleDateFormat simpleDataFormat = new SimpleDateFormat("yyyyMM");
        String yyyyMM = simpleDataFormat.format(insbjkzb.getLrrq());
        SimpleDateFormat simpleDataFormats = new SimpleDateFormat("yyMM");
        String yyMM = simpleDataFormats.format(insbjkzb.getLrrq());
        // NumberFormat nmbFormat = new DecimalFormat("000");

        // 循环中的临时变量
        List sbjkzbList = new ArrayList(); // 创建的缴款主表List
        List jkInforList = new ArrayList(); // 返回的缴款书数据List
        DeclareInfor jkInfor = null; // 一条缴款数据
        List splitsbjkmxList = null; // 按4个科目税款时期拆分后的多组明细数据list

        Sbjkmx sbjkmxtmp = null;
        Sbjkzb sbjkzbtmp = null;
        String jkpzh = null;
        List tmpSbjkmxList; // 按4个科目税款时期拆分后的一组明细数据list
        BigDecimal sjjehe; // 实缴金额
        // 获得数据库连接
        conn = DBResource.getConnection(DBResource.DB_SHENBAO);
        for (int i = 0; i < sbjkmxInfo.size(); i++) {
            // 超过4条明细拆分
            splitsbjkmxList = splitList( (List) sbjkmxInfo.get(i),
                                        SPLITNUM_SM);
            // 对拆分后的每组明细生成一条缴款主表数据并回填明细的jkpzh
            for (int k = 0; k < splitsbjkmxList.size(); k++) {
                // 取得当前计算机代码使用的最大序号
                // sequence = nmbFormat.format(
                // Integer.parseInt(TrancProxy.getSequence(insbjkzb.getJsjdm(),
                // yyyyMM)) + 1);

                String xh = TrancProxy.getSequence(insbjkzb.getJsjdm(),
                    yyyyMM);
                // 自增后的ASCII码
                String xhAscii = TinyTools.stringToASCII(TinyTools.
                    asciiToString(Integer.parseInt(xh) + 1));
                // 格式化为"000"格式
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
        // 把生成的数据插入数据库中
        insertSbjkData_his(sbjkzbList, sbjkmxInfo, conn ,zrlxdm ,zrr);
        return jkInforList;
    }
    catch (Exception ex) {
        throw ExceptionUtil.getBaseException(ex, "保存缴款数据失败!");
    }
    finally {
        DBResource.destroyConnection(conn);
    }
}
    
    // 对按照预算科目拆分后的每组明细生成缴款主表数据并填写缴款凭证号一票多税情况
    // 返回生成的缴款书信息
    private List createSkjkzbInfor_mortax(Sbjkzb insbjkzb, List sbjkmxInfo) throws
        BaseException {
        String sequence = null; // 缴款凭证号中的序列号
        // 定义数据库连接
        Connection conn = null;

        // 得到当前计算机在申报缴款主表中的最大流水号.
        try {
            // 得到录入日期的年月（6位）
            SimpleDateFormat simpleDataFormat = new SimpleDateFormat("yyyyMM");
            String yyyyMM = simpleDataFormat.format(insbjkzb.getLrrq());
            SimpleDateFormat simpleDataFormats = new SimpleDateFormat("yyMM");
            String yyMM = simpleDataFormats.format(insbjkzb.getLrrq());
            // NumberFormat nmbFormat = new DecimalFormat("000");

            // 循环中的临时变量
            List sbjkzbList = new ArrayList(); // 创建的缴款主表List
            List jkInforList = new ArrayList(); // 返回的缴款书数据List(多张票据数据)
            List onePageJkInfor = null; // 一张票据数据
            DeclareInfor jkInfor = null; // 一条缴款数据
            List splitsbjkmxList = null; // 按4个科目税款时期拆分后的多组明细数据list
            Sbjkzb sbjkzbtmp = null;
            String jkpzh = null;
            List tmpSbjkmxList; // 按4个科目税款时期拆分后的一组明细数据list
            BigDecimal sjjehe; // 实缴金额

            // 按每组4条(或少于4条)不同的科目税款来进行分组
            List eachfourList = splitList(sbjkmxInfo, SPLITNUM_SM);
            // 获得数据库连接
            conn = DBResource.getConnection(DBResource.DB_SHENBAO);

            for (int i = 0; i < eachfourList.size(); i++) {
                // 取得当前计算机代码使用的最大序号
                // sequence = nmbFormat.format(
                // Integer.parseInt(TrancProxy.getSequence(insbjkzb.getJsjdm(),
                // yyyyMM)) + 1);

                String xh = TrancProxy.getSequence(insbjkzb.getJsjdm(), yyyyMM);
                // 自增后的ASCII码
                String xhAscii = TinyTools.stringToASCII(TinyTools.
                    asciiToString(Integer.parseInt(xh) + 1));
                // 格式化为"000"格式
                sequence = TinyTools.xhFormat(TinyTools.asciiToString(Integer.
                    parseInt(xhAscii)));

                splitsbjkmxList = (List) eachfourList.get(i);
                onePageJkInfor = new ArrayList();
                // 对拆分后的每组明细生成一条缴款主表数据并回填明细的jkpzh
                for (int k = 0; k < splitsbjkmxList.size(); k++) {
                    int mark = k + 1;
                    jkpzh = insbjkzb.getJsjdm() + yyMM + sequence + mark; // 一票多税的最后一位从1开始
                    tmpSbjkmxList = (List) splitsbjkmxList.get(k);
                    // 创建一个新的sbjkzb并填写缴款凭证号
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
            throw ExceptionUtil.getBaseException(ex, "保存缴款数据失败!");
        }
        finally {
            DBResource.destroyConnection(conn);
        }
    }

    /**
     * @Description: TODO 历史表
     * @param insbjkzb
     * @param sbjkmxInfo
     * @return
     * @throws BaseException
     */
    private List createSkjkzbInfor_mortax_his(Sbjkzb insbjkzb, List sbjkmxInfo,String zrlxdm ,String zrr) throws
    BaseException {
    String sequence = null; // 缴款凭证号中的序列号
    // 定义数据库连接
    Connection conn = null;

    // 得到当前计算机在申报缴款主表中的最大流水号.
    try {
        // 得到录入日期的年月（6位）
        SimpleDateFormat simpleDataFormat = new SimpleDateFormat("yyyyMM");
        String yyyyMM = simpleDataFormat.format(insbjkzb.getLrrq());
        SimpleDateFormat simpleDataFormats = new SimpleDateFormat("yyMM");
        String yyMM = simpleDataFormats.format(insbjkzb.getLrrq());
        // NumberFormat nmbFormat = new DecimalFormat("000");

        // 循环中的临时变量
        List sbjkzbList = new ArrayList(); // 创建的缴款主表List
        List jkInforList = new ArrayList(); // 返回的缴款书数据List(多张票据数据)
        List onePageJkInfor = null; // 一张票据数据
        DeclareInfor jkInfor = null; // 一条缴款数据
        List splitsbjkmxList = null; // 按4个科目税款时期拆分后的多组明细数据list
        Sbjkzb sbjkzbtmp = null;
        String jkpzh = null;
        List tmpSbjkmxList; // 按4个科目税款时期拆分后的一组明细数据list
        BigDecimal sjjehe; // 实缴金额

        // 按每组4条(或少于4条)不同的科目税款来进行分组
        List eachfourList = splitList(sbjkmxInfo, SPLITNUM_SM);
        // 获得数据库连接
        conn = DBResource.getConnection(DBResource.DB_SHENBAO);

        for (int i = 0; i < eachfourList.size(); i++) {
            // 取得当前计算机代码使用的最大序号
            // sequence = nmbFormat.format(
            // Integer.parseInt(TrancProxy.getSequence(insbjkzb.getJsjdm(),
            // yyyyMM)) + 1);

            String xh = TrancProxy.getSequence(insbjkzb.getJsjdm(), yyyyMM);
            // 自增后的ASCII码
            String xhAscii = TinyTools.stringToASCII(TinyTools.
                asciiToString(Integer.parseInt(xh) + 1));
            // 格式化为"000"格式
            sequence = TinyTools.xhFormat(TinyTools.asciiToString(Integer.
                parseInt(xhAscii)));

            splitsbjkmxList = (List) eachfourList.get(i);
            onePageJkInfor = new ArrayList();
            // 对拆分后的每组明细生成一条缴款主表数据并回填明细的jkpzh
            for (int k = 0; k < splitsbjkmxList.size(); k++) {
                int mark = k + 1;
                jkpzh = insbjkzb.getJsjdm() + yyMM + sequence + mark; // 一票多税的最后一位从1开始
                tmpSbjkmxList = (List) splitsbjkmxList.get(k);
                // 创建一个新的sbjkzb并填写缴款凭证号
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
        throw ExceptionUtil.getBaseException(ex, "保存缴款数据失败!");
    }
    finally {
        DBResource.destroyConnection(conn);
    }
}
    
    // 得到明细数据创建一条sbjkzb数据
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
                sjjehe = sjjehe.add(sbjkmxtmp.getSjse()); // 统计实缴金额的合计
            }
        }
        // 设置实缴金额等其他字段数据
        Debug.out("实缴金额合计:" + sjjehe);
        cloneSbjkzb.setSjje(sjjehe);
        cloneSbjkzb.setYsjcdm(sbjkmxtmp.getYsjcdm());
        // cloneSbjkzb.setYsjcmc(sbjkmxtmp.getYsjcmc());
        cloneSbjkzb.setYskmdm(sbjkmxtmp.getYskmdm());
        // cloneSbjkzb.setYskmmc(sbjkmxtmp.getYskmmc());
        cloneSbjkzb.setSzdm(sbjkmxtmp.getSzsmdm().substring(0, 2)); // 明细不存在税种字段，取税目前两位
        // cloneSbjkzb.setSzmc(sbjkmxtmp.getSzmc());
        cloneSbjkzb.setRkje(sjjehe);
        cloneSbjkzb.setSbbh(sbbh); // 设置申报编号
        cloneSbjkzb.setZwbs(ZWBS);
        // 添加申报缴款主表税款所属时期 add by wanghw
        cloneSbjkzb.setSkssksrq(sbjkmxtmp.getSkssksrq());
        cloneSbjkzb.setSkssjsrq(sbjkmxtmp.getSkssjsrq());
        // 限缴日期
        String smdm = sbjkmxtmp.getSzsmdm(); // 税种税目代码
        String djzclx = sbjkzb.getDjzclxdm(); // 登记注册类型代码
        SimpleDateFormat simpleDataFormat = new SimpleDateFormat("yyyyMMdd");
        String rq = simpleDataFormat.format(new Date()); // 当前年月:YYYYMMdd
        BigDecimal sjse = sbjkmxtmp.getSjse();
        String sklxdm = sbjkzb.getSklxdm(); // 税款类型代码
        
        String zqlxsbfs = sbjkmxtmp.getSbfs();
        if (cloneSbjkzb.getXjrq() == null) { // 如果限缴日期为空
            cloneSbjkzb.setXjrq(getZqzzrq(smdm, djzclx, rq, sjse, sklxdm, zqlxsbfs, conn)); // 限缴日期
        }
        // 得到计算机代码的登记信息并填写主表
        String jsjdm = sbjkmxtmp.getJsjdm(); // 计算机代码
        if (cloneSbjkzb.getGkzzjgdm() == null ||
            cloneSbjkzb.getGkzzjgdm().equals("")) {
            Swjgzzjg swjg = getSkgk(jsjdm, cloneSbjkzb.getZsswjgzzjgdm(), conn);
            cloneSbjkzb.setGkzzjgdm(swjg.getGkjhh()); // 国库组织机构代码
            // cloneSbjkzb.setGkzzjgmc(swjg.getSkgk()); //国库名称
        }

        return cloneSbjkzb;
    }

    // add by 2003-09-28 wanghw
    /**
     * 根据税种税目代码和当前年月，获取征期日历表中对呀的征期终止日期 如果存在多条满足条件的记录，取第一条，
     * 如果没有满足条件的记录，则返回当前月的15号为限缴日期
     *
     * @param smdm
     *            税种税目代码
     * @param djzclx
     *            登记注册类型代码
     * @param rq
     *            日期
     * @param conn
     *            数据库连接
     * @return Timestamp 征期终止日期
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
        // 默认限缴日期当月15日
        Zqrl zqrl = new Zqrl();
        zqrl.setZqzzrq(time);
        try {
            // 获得 ORManager
            ORManager orManager = DBResource.getORManager(DBResource.OR_SHENBAO);
            // 查询征期日历表
            ArrayList zqrlRs = new ArrayList();
            /*******************************************************************
             * modified by Daniel ,条件跟上门一致 String sqlWhere = "(ZQLXDM >= '0' AND
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
            zqrlRs = (ArrayList) orManager.query(SESSION_ID, conn, orCtx); // 查询征期日历表
            if (zqrlRs.size() != 0) {
            	// 印花税网上申报项目 modified by Junbing Tu 2014.0
            	String yhssz = ( (Zqrl) zqrlRs.get(0)).getSzsmdm().substring(0, 2); //印花税税种代码
            	//1.1 征期类型为“按次申报”（08）
        		if ( ( (Zqrl) zqrlRs.get(0)).getZqlxdm().equals("08")) 
        		{
        			//1.1.1 税款类型为“正常税款”,征期类型为“按次申报”（08），且是印花税的限缴日期为：征期内征期日历截止日期、征期外申报日期(无法申报)；
        			//1.1.2 税款类型为“委托代征”,征期类型为“按次申报”（08），且是印花税，不选中”是否按次“时，征期内限缴日期为征期日历截止日期，征期外限缴日期为申报日期(无法申报)；
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
        			
        			////1.1.3 征期类型为“按次申报”（08），且是印花税，选中”是否按次“时；
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
                // 如果当前申报日期过了征期的结束日期，则限缴日期为当天
                Timestamp xjrq = ( (Zqrl) zqrlRs.get(0)).getZqzzrq();
                Timestamp today = new Timestamp(System.currentTimeMillis());
                if (xjrq.getTime() < today.getTime()) {
                    xjrq = today;
                }

                return xjrq;
            }
            else {
                // return zqrl.getZqzzrq(); //返回默认值，当前月15日
                /**
                 * ***********************modified by Daniel
                 * ,条件跟上门一致********************
                 */
                // 如果当前申报日期过了征期的结束日期，则限缴日期为当天
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
            throw ExceptionUtil.getBaseException(e, "获取限缴日期出错!");
        }
    }

    /**
     * 查询计算机代码对呀的收款国库
     *
     * @param jsjdm
     *            计算机代码
     * @param swjgdm
     *            税务机关组织机构代码
     * @return Swjgzzjg 国库代码
     * @param conn
     *            数据库连接
     * @throws BaseException
     */
    private Swjgzzjg getSkgk(String jsjdm, String swjgdm, Connection conn) throws
        BaseException {
        Swjgzzjg swjg = new Swjgzzjg();
        try {
            // 获得 ORManager
            ORManager orManager = DBResource.getORManager(DBResource.OR_SHENBAO);
            // 查询税务机关表
            ArrayList swjgRs = new ArrayList();

            String sqlWhere = "(SWJGZZJGDM = '" + swjgdm + "')";
            Vector criteria = new Vector();
            criteria.add(sqlWhere);
            ORContext orCtx = new ORContext(Swjgzzjg.class.getName(), criteria);
            swjgRs = (ArrayList) orManager.query(SESSION_ID, conn, orCtx); // 查询税务机关表
            if (swjgRs.size() != 0) {
                return (Swjgzzjg) swjgRs.get(0);
            }
            else {
                return swjg; // 返回空
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e, "获取收款国库出错!");
        }
    }

    /**
     * 申报编号的生成,申报编号的生成规则为：计算机代码加上服务器的当前时间的十八位字符串
     *
     * @param jsjdm
     *            计算机代码
     * @return sbbh
     * @throws DeclareException
     */
    public String getSbbh(String jsjdm) throws Exception {
        String sbbh = InterFaceProcessor.getSbbh(jsjdm); // 标准时间，1970年1月1日0点以来的毫秒数
        return sbbh;
    }

    /**
     * 计算分成金额
     *
     * @param je
     *            实缴金额
     * @param bl
     *            分成比例（默认是0。00）
     * @return 分成金额(保留4位小数)
     */
    private BigDecimal getFc(BigDecimal je, BigDecimal bl) {
        BigDecimal fc = je.multiply(bl).setScale(4, BigDecimal.ROUND_HALF_UP);
        return fc;
    }

    /**
     * 申报数据维护入口进入，查询纳税人本期所有未缴款申报数据
     *
     * @param voPackage
     *            object
     * @param sjly
     *            数据来源
     * @return map
     * @throws java.lang.Exception
     */
    public HashMap doQuery(VOPackage voPackage, String sjly) throws Exception {
        HashMap dataMap = new HashMap(); // 返回的map对象
        // 定义数据库连接
        Connection conn = null;
        try {
            Map tempData = (HashMap) voPackage.getData(); // 从前端获得map对象
            String swjgzzjgdm = (String) tempData.get("WSSB_SWJGZZJGDM"); // 税务机关组织机构代码
            String jsjdm = (String) tempData.get(ZhsbMapConstant.JSJDM); // 获取用户的计算机代码
            // 注意日期格式，必须为:(YYYY-MM-DD)
            String whrq = (String) tempData.get(ZhsbMapConstant.WHRQ); // 获取用户维护申报数据日期

            String qxdm = swjgzzjgdm.substring(0, 2); // 区县代码

            // 查询申报缴款主表的查询结果
            ArrayList zbResult = new ArrayList();
            // 查询申报缴款明细表的查询结果
            ArrayList mxResult = new ArrayList();

            // 过滤出已经有缴款记录的申报编号，帐页日期或者(帐务标识的第1位<>0or第20位<>0)
            // 不管同一批数据是否有缴款记录，只要没有缴款，就允许修改
            String sqlWhere =
                "(ZYRQ >= to_date('20060101','yyyymmdd') AND QXDM = '" + qxdm +
                "' AND JSJDM = '" + jsjdm
                + "'" +
                " AND substr(to_char(SBRQ,'yyyy-mm-dd'),0,7) = substr('" + whrq +
                "', 0, 7) AND SJLY = '"
                + sjly + "' AND FSDM = '" + CodeConstant.FSDM_WSSB + "')"
                // 需要显示所有缴款书 Modifyed by wuyouzhi 2006.2.9
                // + " AND substr(zwbs, 1, 1) = '0' AND substr(zwbs, 20, 1)
                // = '0' )"
                + " ORDER BY SBRQ DESC, SBBH, JKPZH";
            Vector criteria = new Vector();
            criteria.add(sqlWhere);

            ORContext orCtx = new ORContext(Sbjkzb.class.getName(), criteria);

            // 获得 ORManager
            ORManager orManager = DBResource.getORManager(DBResource.OR_SHENBAO);
            // 获得数据库连接
            conn = DBResource.getConnection(DBResource.DB_SHENBAO);

            zbResult = (ArrayList) orManager.query(SESSION_ID, conn, orCtx); // 查询申报缴款主表
            if (zbResult.size() == 0) {
                return null; // 没有可以维护的申报数据！
            }
            // 存在申报未缴款的当期数据
            Sbjkzb tempObj = (Sbjkzb) zbResult.get(0);
            String _jkpzh = tempObj.getJkpzh();
            // 拼申报明细表的查询where条件
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
            mxResult = (ArrayList) orManager.query(SESSION_ID, conn, orCtxMx); // 查询申报缴款明细表
            DBResource.destroyConnection(conn); // 关闭数据库连接
            conn = null;
            // 接着对取回的数据进行格式封装处理
            dataMap = (HashMap) convertResult(zbResult, mxResult);

            return dataMap; // 返回结果数据
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
     * 申报数据维护入口进入，查询纳税人本期所有未缴款申报数据
     *
     * @param voPackage
     *            object
     * @param sjly
     *            数据来源
     * @return map
     * @throws java.lang.Exception
     */
    public ArrayList doQueryysb(VOPackage voPackage) throws Exception {
        ArrayList dataList = new ArrayList(); // 返回的map对象
        // 定义数据库连接
        Connection conn = null;
        ResultSet rs = null;

        try {
            UserData ud = voPackage.getUserData();
            String jsjdm = ud.yhid;
            // 取得当前月时间段
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
            // 获得数据库连接
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

            DBResource.destroyConnection(conn); // 关闭数据库连接
            conn = null;
            // 接着对取回的数据进行格式封装处理

            return dataList; // 返回结果数据
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
     * 将申报主表和明细表数据，按照数据格式封装到map中，并返回
     *
     * @param zbResult
     *            主表数据
     * @param mxResult
     *            明细数据
     * @return HashMap
     */
    public HashMap convertResult(ArrayList zbResult, ArrayList mxResult) {
        HashMap dataMap = new HashMap(); // 返回的数据包

        ArrayList sbbhAr = new ArrayList(); // 按申报编号保存数据的list
        ArrayList objAr = new ArrayList(); // 存放DeclareInfor对象数据
        ArrayList mxAr = new ArrayList(); // 存放申报明细数据
        int zbSize = zbResult.size();
        /**
         * qianchao. 2006.5.20 读代码后，理解如下。 按申报表号对主表数据，明细数据归类。 最后结果在sbbhAr中
         * sbbhAr是一个list，其中的每一个元素也是一个list， 对应于一个sbbh，
         * 暂称其jkslist，jkslist的每一个元素，是一个DeclareInfor结构。
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
            if (_jkpzh.substring(_jkpzh.length() - 1).equals("0")) { // 判断缴款凭证号的最后一位是否为‘0’
                dataInfor.setPrintTag(CodeConstant.PRINT_YPYS); // 一票一税
            }
            else {
                dataInfor.setPrintTag(CodeConstant.PRINT_YPDS_KM); // 一票多税
            }
            String tempSbbh = "";
            if (i < (zbSize - 1)) {
                tempSbbh = ( (Sbjkzb) zbResult.get(i + 1)).getSbbh(); // 临时参数，当前主表记录的下一条的申报编号
                // 说明：正常数据中，申报编号都不为空，因而最后一条记录对比的结果必然为false，所以不需要增加新的判断
            }
            objAr.add(dataInfor);
            if (!zbData.getSbbh().equals(tempSbbh)) {
                ArrayList temp = (ArrayList) objAr.clone();
                sbbhAr.add(temp);
                objAr.clear();
            }
        }

        // 按照申报编号对数据进行格式处理
        HashMap bhMap = new HashMap(); // 一个申报编号为key对应的map
        HashMap pzMap = new HashMap(); // 一个缴款书号为key对应的map
        ArrayList bhList = new ArrayList();
        /**
         * qianchao. 2006.5.20 读代码后，理解如下。 对sbbhAr进行进一步处理，结果在dataMap中。
         *
         * dataMap结构示例如下
         *
         *
         * 第一层：hashmap； 第二层: hashmap： key（String）：sbbh
         * value（hashmap）：包含各种申报信息的map， 第三层：hashmap： key：printTag value：是否一票一税
         * key：sklx value：税款类型 key：sbrq value：申报日期 key：zwbs value：帐务标识 对一票一税，
         * key：jkpzh value：包含该缴款凭证的信息一个hashmap 对一票多税， key：15位jkpzh
         * value：包含该缴款凭证的信息一个hashmap 第四层: hashmap： 对一票一税， key（String）：jehj
         * value：该缴款书合计金额 key（String）：sbsj value：该缴款书的DeclareInfor 对一票多税，
         * key（String）：jehj value：该15位缴款书号对应的多张缴款书的list key（String）：sbsj
         * value：该缴款书的DeclareInfor
         */
        for (int k = 0; k < sbbhAr.size(); k++) {
            bhList.clear();
            bhList = (ArrayList) sbbhAr.get(k); // 获得同一个申报表号的数据
            DeclareInfor tempObj = (DeclareInfor) bhList.get(0);
            String _sbbh = tempObj.getSbjkzb().getSbbh(); // 申报编号
            bhMap.put(ZhsbMapConstant.PRINTTAG, new Integer(tempObj.getPrintTag())); // 票据种类：一票多税/一票一税
            bhMap.put(ZhsbMapConstant.SKLX, tempObj.getSbjkzb().getSklxdm()); // 税款类型代码
            bhMap.put(ZhsbMapConstant.SBRQ, tempObj.getSbjkzb().getSbrq()); // 申报日期
            bhMap.put(ZhsbMapConstant.ZWBS, tempObj.getSbjkzb().getZwbs()); // 帐务标识
            if (tempObj.getPrintTag() == CodeConstant.PRINT_YPYS) { // 一票一税
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
            else if (tempObj.getPrintTag() == CodeConstant.PRINT_YPDS_KM) { // 一票多税
                String _oldJksh = ""; // 保存缴款书号
                ArrayList tempJksAr = new ArrayList(); // 临时缴款书数据列表
                BigDecimal jehj = new BigDecimal(0); // 定义金额合计参数
                for (int m = 0; m < bhList.size(); m++) {
                    tempObj = (DeclareInfor) bhList.get(m);
                    String _jkpzh = tempObj.getSbjkzb().getJkpzh();
                    String _jksh = _jkpzh.substring(0, _jkpzh.length() - 1); // 获得缴款书号
                    if (m == 0 || _jksh.equals(_oldJksh)) {
                        _oldJksh = _jksh;
                        jehj = jehj.add(tempObj.getSbjkzb().getRkje()); // 对一张凭证上的记录进行金额累加
                        tempJksAr.add(tempObj);
                    }
                    else { // if(m != 0 && !_jksh.equals(oldJksh))
                        ArrayList temps = (ArrayList) tempJksAr.clone();
                        pzMap.put(ZhsbMapConstant.SBSJ, temps);
                        pzMap.put(ZhsbMapConstant.JEHJ, jehj);
                        HashMap tempPz = (HashMap) pzMap.clone(); // copy
                        bhMap.put(_oldJksh, tempPz); // 将缴款书map封装到申报编号对应的map中
                        tempJksAr.clear(); // 清空
                        jehj = jehj.subtract(jehj); // 清零
                        // 开始新的累加
                        _oldJksh = _jksh;
                        jehj = jehj.add(tempObj.getSbjkzb().getRkje()); // 对一张凭证上的记录进行金额累加
                        tempJksAr.add(tempObj);
                    }
                }
                // 对最后一张税票进行封装
                ArrayList temp = (ArrayList) tempJksAr.clone();
                pzMap.put(ZhsbMapConstant.SBSJ, temp);
                pzMap.put(ZhsbMapConstant.JEHJ, jehj);
                HashMap tempPz = (HashMap) pzMap.clone();
                bhMap.put(_oldJksh, tempPz); // 将缴款书map封装到申报编号对应的map中
                pzMap.clear();
                tempJksAr.clear(); // 清空
                jehj = jehj.subtract(jehj); // 清零
            }
            HashMap tempMap = (HashMap) bhMap.clone();
            dataMap.put(_sbbh, tempMap); // 将同一个申报编号的数据封装到map中的一个key的value
            bhMap.clear();
        }
        return dataMap;
    }

    /**
     * 按照申报编号作废该申报编号对应的所有数据
     *
     * @param voPackage
     *            主要获得申报编号
     * @throws java.lang.Exception
     */
    private Object doDel(VOPackage voPackage) throws Exception {
        HashMap tempData = (HashMap) voPackage.getData();
        Map reMap = new HashMap();
        // 作废的申报编号
        String zfSbbh = (String) tempData.get(ZhsbMapConstant.SBBH);
        String jsjdm = zfSbbh.substring(0, 8);
        // 作废的缴款书号
        String zfJksh = "";
        zfJksh = (String) tempData.get(ZhsbMapConstant.JKSH);
        String swjgzzjgdm = (String) tempData.get("WSSB_SWJGZZJGDM");
        String qxdm = swjgzzjgdm.substring(0, 2);
        DzyjsjVO dzyj = (DzyjsjVO) tempData.get(ZhsbMapConstant.CA_QMSJ_VO);
        UserData ud = voPackage.getUserData();

        // 定义数据库连接
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
             * System.out.println("============保存签名数据结束=============="); }
             */
            try {
                dzyj = CAUtils.saveDzyjsj(ud, dzyj, "0", "0", "0", zfSbbh);
            }
            catch (Exception ex) {
                ex.printStackTrace();
                throw ExceptionUtil.getBaseException(ex);
            }
            reMap.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);

            // 如果是按照缴款凭证号单张作废缴款书，则增加删除条件限制为传入缴款书号
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
            // 判断该批（或者单张）缴款凭证是否未缴款及核销
            String sqlW = "SELECT JKPZH FROM SBDB.SB_JL_SBJKZB"
                + " WHERE ZYRQ >= to_date('20060101','yyyymmdd') AND QXDM='" +
                qxdm + "' AND JSJDM = '" + jsjdm
                + "' AND SBBH = '" + zfSbbh + "' " +
                "AND substr(zwbs, 1, 1) = '0' AND substr(zwbs, 20, 1) = '0' "
                + sqlAdd;
            // 定义sql语句，批处理

            // 删除明细数据的sql语句
            StringBuffer sqlStringBuffer = new StringBuffer();
            /*
             * sqlStringBuffer.append("DELETE FROM SBDB.SB_JL_SBJKMX WHERE QXDM =
             * '") .append(qxdm).append("' AND JKPZH IN ("+sqlW+")");
             */
            sqlStringBuffer.append("DELETE FROM SBDB.SB_JL_SBJKMX WHERE ").
                append(" JKPZH IN (" + sqlW + ")");
            // 赋值明细语句
            String mxSql = sqlStringBuffer.toString();
            sqlStringBuffer.setLength(0); // clear
            System.out.println(mxSql);

            // 删除主表记录数据的sql语句
            StringBuffer sqlBuffer = new StringBuffer();
            sqlBuffer.append("DELETE FROM SBDB.SB_JL_SBJKZB").append(
                " WHERE ZYRQ >= to_date('20060101','yyyymmdd') AND QXDM = '").
                append(qxdm).append("' AND JSJDM='")
                .append(jsjdm).append("' AND SBBH = '").append(zfSbbh).append(
                    "' AND substr(zwbs, 1, 1) = '0' AND substr(zwbs, 20, 1) = '0'").
                append(tempStr);
            // 赋值主表语句
            String zbSql = sqlBuffer.toString();
            System.out.println(zbSql);
            sqlBuffer.setLength(0); // clear

            // 获得数据库连接
            conn = DBResource.getConnection(DBResource.DB_SHENBAO);
            Statement st = conn.createStatement();
            st.addBatch(mxSql); // 添加明细sql语句
            st.addBatch(zbSql); // 添加主表sql语句
            st.executeBatch(); // 执行删除
        }
        catch (Exception e) {
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e, "作废缴款数据失败！");
        }
        finally {
            DBResource.destroyConnection(conn);
        }
        return reMap;
    }

    /**
     * 按照申报编号作废该申报编号对应的所有数据
     *
     * @param voPackage
     *            主要获得申报编号
     * @throws java.lang.Exception
     */
    private void doDelBySBBH(VOPackage voPackage) throws Exception {
        HashMap tempData = (HashMap) voPackage.getData();
        // 作废的申报编号
        String zfSbbh = (String) tempData.get(ZhsbMapConstant.SBBH);
        String jsjdm = zfSbbh.substring(0, 8);
        String sql = null;
        StringBuffer sb = null;
        // 定义数据库连接
        Connection conn = null;
        Statement st = null;
        try {
            // 获得数据库连接
            conn = DBResource.getConnection(DBResource.DB_SHENBAO);
            st = conn.createStatement();
            // 添加明细sql语句
            sb = new StringBuffer();
            sb.append("DELETE FROM SBDB.SB_JL_SBJKMX WHERE jsjdm=");
            sb.append(StringUtils.getSQLStr(jsjdm));
            sb.append(" AND sbbh=");
            sb.append(StringUtils.getSQLStr(zfSbbh));
            sql = sb.toString();
            log(sql);
            st.addBatch(sql);
            // 添加主表sql语句
            sb = new StringBuffer();
            sb.append("DELETE FROM SBDB.SB_JL_SBJKZB WHERE jsjdm=");
            sb.append(StringUtils.getSQLStr(jsjdm));
            sb.append(" AND sbbh=");
            sb.append(StringUtils.getSQLStr(zfSbbh));
            sql = sb.toString();
            log(sql);
            st.addBatch(sql);
            // 执行删除
            st.executeBatch();
            //
            st.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e, "作废缴款数据失败！");
        }
        finally {
            DBResource.destroyConnection(conn);
        }
    }

    /**
     * 一票多税情况下的打印请求输出，对数据只根据申报编号进行区分，将所有明细数据分组，每组最多9条记录
     *
     * @param voPackage
     *            取申报编号
     * @return map
     * @throws java.lang.Exception
     */
    private HashMap doPrint(VOPackage voPackage) throws Exception {
        HashMap tempData = (HashMap) voPackage.getData();
        String printSbbh = (String) tempData.get(ZhsbMapConstant.SBBH);
        String jsjdm = printSbbh.substring(0, 8);
        String swjgzzjgdm = (String) tempData.get("WSSB_SWJGZZJGDM");
        String qxdm = swjgzzjgdm.substring(0, 2); // 区县代码
        ArrayList dataAr = new ArrayList();
        HashMap dataMap = new HashMap(); // 打印数据包
        // 定义一个数据库连接
        Connection con = null;
        try {
            // 查询申报缴款明细表的查询结果
            ArrayList mxResult = new ArrayList();

            // 获得数据库连接
            con = DBResource.getConnection(DBResource.DB_SHENBAO);
            // 获得 ORManager
            ORManager orManager = DBResource.getORManager(DBResource.OR_SHENBAO);
            String sqlWhere = "(QXDM = '" + qxdm + "' AND SBBH = '" + printSbbh +
                "') ORDER BY SZSMDM";
            Vector criteria = new Vector();
            criteria.add(sqlWhere);
            ORContext orCtx = new ORContext(Sbjkmx.class.getName(), criteria);
            mxResult = (ArrayList) orManager.query(SESSION_ID, con, orCtx); // 查询申报缴款明细表
            // 一个申报编号只对应一个税款类型
            dataAr = (ArrayList) splitList(mxResult, 9); // 按每票最多9条记录进行分组
            ArrayList tempAr = (ArrayList) dataAr.clone(); // copy
            dataMap.put(ZhsbMapConstant.SBSJ, tempAr); // 将分组后的打印数据，按照key='sbsj'放入map中

            // 查询该申报编号对应的税款类型及银行代码、名称
            String sqlW =
                "(ZYRQ >= to_date('20040101','yyyymmdd') AND QXDM = '" + qxdm +
                "' AND JSJDM = '" + jsjdm
                + "' AND SBBH = '" + printSbbh +
                "' AND ROWNUM=1) ORDER BY SZDM";
            Vector cri = new Vector();
            cri.add(sqlW);
            ORContext orCtx2 = new ORContext(Sbjkzb.class.getName(), cri);
            ArrayList zbResult = (ArrayList) orManager.query(SESSION_ID, con,
                orCtx2); // 查询申报缴款明细表
            if (zbResult.size() == 1) {
                Sbjkzb zbTmp = (Sbjkzb) zbResult.get(0);
                String sklxdm = zbTmp.getSklxdm(); // 获得税款类型代码
                // 将数据封装到map中
                dataMap.put(ZhsbMapConstant.SKLX, sklxdm); // 赋值税款类型代码,key='sklx'
                dataMap.put(ZhsbMapConstant.YHZH, zbTmp.getZh()); // 银行帐户
                dataMap.put(ZhsbMapConstant.YHMC, zbTmp.getYhmc()); // 银行名称
                dataMap.put(ZhsbMapConstant.SBRQ, zbTmp.getSbrq()); // 申报日期
                dataMap.put(ZhsbMapConstant.ZWBS, zbTmp.getZwbs()); // 帐务标识
                dataMap.put(ZhsbMapConstant.SBBH, printSbbh); // 申报日期
                
                //限缴日期  gsdz
                String xjrq = "";
                
                String queryXjrq = "select max(xjrq) maxxjrq from sbdb.sb_jl_sbjkzb where " +
                		"(ZYRQ >= to_date('20040101','yyyymmdd') AND QXDM = '" + qxdm +
                		 "' AND JSJDM = '" + jsjdm
                         + "' AND SBBH = '" + printSbbh +
                         "') ORDER BY SZDM";
                PreparedStatement ps = con.prepareStatement(queryXjrq);
                ResultSet rs = ps.executeQuery();
                DateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日"); 
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
                throw new Exception("数据错误，没有对应的申报数据！");
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
     * 将后端保存返回的List，转换为格式化的Map
     *
     * @param obj
     *            ArrayList
     * @return HashMap
     */
    private HashMap listToMap(Object obj) {
        ArrayList tempAr = (ArrayList) obj;
        HashMap midMap = new HashMap(); // 封装缴款凭书层面的map
        HashMap bottomMap = new HashMap(); // 封装具体缴款数据层面的map

        if (printTag == CodeConstant.PRINT_YPYS) { // 一票一税
            midMap.put(ZhsbMapConstant.PRINTTAG, new Integer(printTag));
            DeclareInfor tempObj = (DeclareInfor) tempAr.get(0);
            midMap.put(ZhsbMapConstant.SKLX, tempObj.getSbjkzb().getSklxdm()); // 将税款类型代码封装入map
            midMap.put(ZhsbMapConstant.SBRQ, tempObj.getSbjkzb().getSbrq()); // 将申报日期封装入map
            midMap.put(ZhsbMapConstant.ZWBS, tempObj.getSbjkzb().getZwbs()); // 帐务标识
            for (int i = 0; i < tempAr.size(); i++) {
                tempObj = (DeclareInfor) tempAr.get(i);
                bottomMap.put(ZhsbMapConstant.JEHJ, tempObj.getSbjkzb().getRkje()); // 入库金额合计（单票）
                bottomMap.put(ZhsbMapConstant.SBSJ, tempObj); // 申报数据对象封装入map
                String jkpzh = tempObj.getSbjkzb().getJkpzh(); // 获得缴款凭证号
                HashMap tmpMap = (HashMap) bottomMap.clone(); // 复制
                midMap.put(jkpzh, tmpMap); // 将一张缴款凭证数据及金额封装入map
            }
        }
        else if (printTag == CodeConstant.PRINT_YPDS_KM) { // 一票多税（科目）
            String jkpzh, jksh;
            BigDecimal jehj = new BigDecimal(0); // 定义金额合计参数
            midMap.put(ZhsbMapConstant.PRINTTAG, new Integer(printTag)); //
            ArrayList tempList = new ArrayList();
            tempList = (ArrayList) tempAr.get(0); // 获得具体一张缴款书数据列表
            DeclareInfor tempObj = (DeclareInfor) tempList.get(0); // 获得第一条科目数据
            midMap.put(ZhsbMapConstant.SKLX, tempObj.getSbjkzb().getSklxdm()); // 税款类型代码
            midMap.put(ZhsbMapConstant.SBRQ, tempObj.getSbjkzb().getSbrq());
            midMap.put(ZhsbMapConstant.ZWBS, tempObj.getSbjkzb().getZwbs());
            for (int i = 0; i < tempAr.size(); i++) {
                jehj = jehj.subtract(jehj); // 合计金额进行清零(jehj-jehj=0)
                jkpzh = "";
                jksh = ""; // 清空旧数据
                tempList = (ArrayList) tempAr.get(i); // 获得具体一张缴款书数据列表
                tempObj = (DeclareInfor) tempList.get(0); // 获得第一条科目数据
                jkpzh = tempObj.getSbjkzb().getJkpzh(); // 每一条预算科目数据对应的缴款凭证号（16位）
                jksh = jkpzh.substring(0, 15); // 一张缴款书对应的缴款书号（15位）
                for (int j = 0; j < tempList.size(); j++) {
                    tempObj = (DeclareInfor) tempList.get(j); // 获得一条科目对应的申报数据
                    jehj = jehj.add(tempObj.getSbjkzb().getRkje()); // 对每条科目的金额按税票汇总
                }
                bottomMap.put(ZhsbMapConstant.JEHJ, jehj);
                ArrayList tmpList = (ArrayList) tempList.clone(); // copy
                bottomMap.put(ZhsbMapConstant.SBSJ, tmpList); // 数据map
                HashMap tempMap = (HashMap) bottomMap.clone(); // copy
                midMap.put(jksh, tempMap); // 数据map封装到缴款书map中
            }
        }
        return midMap; // 返回结果数据map
    }

    /**
     * 将后端保存返回的List，转换为格式化的Map
     *
     * @param obj
     *            ArrayList
     * @param boolean
     *            是否允许撤销
     * @return HashMap
     */
    private HashMap listToMapOfSkh(Object obj, boolean zfFlag) {
        ArrayList tempAr = (ArrayList) obj;
        HashMap midMap = new HashMap(); // 封装缴款凭书层面的map
        HashMap bottomMap = new HashMap(); // 封装具体缴款数据层面的map
        List jkshList = new ArrayList();
        
        if (printTag == CodeConstant.PRINT_YPYS) { // 一票一税
            midMap.put(ZhsbMapConstant.PRINTTAG, new Integer(printTag));
            DeclareInfor tempObj = (DeclareInfor) tempAr.get(0);
            midMap.put(ZhsbMapConstant.SKLX, tempObj.getSbjkzb().getSklxdm()); // 将税款类型代码封装入map
            midMap.put(ZhsbMapConstant.SBRQ, tempObj.getSbjkzb().getSbrq()); // 将申报日期封装入map
            midMap.put(ZhsbMapConstant.ZWBS, tempObj.getSbjkzb().getZwbs());
            for (int i = 0; i < tempAr.size(); i++) {
                tempObj = (DeclareInfor) tempAr.get(i);
                bottomMap.put(ZhsbMapConstant.JEHJ, tempObj.getSbjkzb().getRkje()); // 入库金额合计（单票）
                bottomMap.put(ZhsbMapConstant.SBSJ, tempObj); // 申报数据对象封装入map
                String jkpzh = tempObj.getSbjkzb().getJkpzh(); // 获得缴款凭证号
                HashMap tmpMap = (HashMap) bottomMap.clone(); // 复制
                midMap.put(jkpzh, tmpMap); // 将一张缴款凭证数据及金额封装入map
                jkshList.add(jkpzh);
            }
        }
        else if (printTag == CodeConstant.PRINT_YPDS_KM) { // 一票多税（科目）
            String jkpzh, jksh;
            BigDecimal jehj = new BigDecimal(0); // 定义金额合计参数
            midMap.put(ZhsbMapConstant.PRINTTAG, new Integer(printTag)); //
            ArrayList tempList = new ArrayList();
            tempList = (ArrayList) tempAr.get(0); // 获得具体一张缴款书数据列表
            DeclareInfor tempObj = (DeclareInfor) tempList.get(0); // 获得第一条科目数据
            midMap.put(ZhsbMapConstant.SKLX, tempObj.getSbjkzb().getSklxdm()); // 税款类型代码
            midMap.put(ZhsbMapConstant.SBRQ, tempObj.getSbjkzb().getSbrq());
            midMap.put(ZhsbMapConstant.ZWBS, tempObj.getSbjkzb().getZwbs());
            log("添加单笔申报的作废许可标记" + zfFlag);
            midMap.put(ZhsbMapConstant.ZFBZ, new Boolean(zfFlag));
            midMap.put(ZhsbMapConstant.ZWBS, tempObj.getSbjkzb().getZwbs()); // 帐务标识
            for (int i = 0; i < tempAr.size(); i++) {
                jehj = jehj.subtract(jehj); // 合计金额进行清零(jehj-jehj=0)
                jkpzh = "";
                jksh = ""; // 清空旧数据
                tempList = (ArrayList) tempAr.get(i); // 获得具体一张缴款书数据列表
                tempObj = (DeclareInfor) tempList.get(0); // 获得第一条科目数据
                jkpzh = tempObj.getSbjkzb().getJkpzh(); // 每一条预算科目数据对应的缴款凭证号（16位）
                jksh = jkpzh.substring(0, 15); // 一张缴款书对应的缴款书号（15位）
                for (int j = 0; j < tempList.size(); j++) {
                    tempObj = (DeclareInfor) tempList.get(j); // 获得一条科目对应的申报数据
                    jehj = jehj.add(tempObj.getSbjkzb().getRkje()); // 对每条科目的金额按税票汇总
                }
                bottomMap.put(ZhsbMapConstant.JEHJ, jehj);
                ArrayList tmpList = (ArrayList) tempList.clone(); // copy
                bottomMap.put(ZhsbMapConstant.SBSJ, tmpList); // 数据map
                HashMap tempMap = (HashMap) bottomMap.clone(); // copy
                midMap.put(jksh, tempMap); // 数据map封装到缴款书map中
                jkshList.add(jksh);
            }
        }
        midMap.put("jkshList", jkshList);
        return midMap; // 返回结果数据map
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
     * 根据properties的名称，从属性配置表中读取该properties对应的值。
     *
     * @param propertyName
     *            properties的名称
     * @return java.lang.String
     */
    public String getProperty(VOPackage voPackage) throws BaseException {
        String propertyName = (String) voPackage.getData();
        String value = "";

        Connection con = null;
        try {
            // 查询属性配置表的查询结果
            ArrayList result = new ArrayList();

            // 获得数据库连接
            con = DBResource.getConnection(DBResource.DB_SHENBAO);
            // 获得 ORManager
            ORManager orManager = DBResource.getORManager(DBResource.OR_SHENBAO);
            String sqlWhere = "propertyname = '" + propertyName +
                "' AND zxbs = '0'";
            // System.out.println("zhsbProcessor.getProperty:"+sqlWhere);
            Vector criteria = new Vector();
            criteria.add(sqlWhere);
            ORContext orCtx = new ORContext(com.ttsoft.bjtax.shenbao.model.
                                            domain.Properties.class.getName(),
                                            criteria);
            result = (ArrayList) orManager.query(SESSION_ID, con, orCtx); // 查询属性配置表
            if (result.size() == 0) {
                return ""; // 没有可以维护的申报数据！
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
     * 对三方协议户银行扣款成功的申报置标志位
     *
     * @param vo
     *            vo对象
     * @param flag
     *            0--扣款成功,1--扣款状态锁定
     * @return java.lang.String 返回指
     */
    private Map doModifyZwbsYhkk(VOPackage vo, int flag) throws BaseException {
        log("===========后台更新账务标志开始============");
        String value = "";
        Connection con = null;
        Statement st = null;
        Map jkdata = null;
        UserData ud = null;
        StringBuffer sb = null;
        String sql = null;
        String sbbh = null;
        try {
            // 获得数据库连接
            con = DBResource.getConnection(DBResource.DB_SHENBAO);
            st = con.createStatement();
            // 从vo对象获取数据
            jkdata = (Map) vo.getData();
            ud = vo.getUserData();
            // 获取所有的申报交款数据并更新账务标志
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
     * 生成一票多税缴款书的税票号码及对象数据
     *
     * @param vo
     *            vo对象
     * @return java.util.List 一票多税缴款书集合
     */
    private List doGenerateYpdsJks(VOPackage vo, Sfxy sfxy) throws
        BaseException {
        log("===========生成一票多税缴款书开始============");
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
            // 获得数据库连接
            con = DBResource.getConnection(DBResource.DB_SHENBAO);
            st = con.createStatement();
            // 从vo对象获取数据
            jkdata = (Map) vo.getData();
            ud = vo.getUserData();
            // 获取所有的申报交款数据并生成税票号码
            Iterator keyset = jkdata.keySet().iterator();
            while (keyset.hasNext()) {
                sbbh = (String) keyset.next();
                ypdsJksList = InterFaceProcessor.getYpdsJks(sbbh);
                for (int i = 0; i < ypdsJksList.size(); i++) {
                    jks = (YPDSJKS) ypdsJksList.get(i);
                    log("一票一税缴款书的明细数量为" + jks.getYpysJksMx().size());
                    jkpzhs = jks.getPropertyJkpzhFfYpdsJks();
                    sphm = InterFaceProcessor.getSphm(jks.getJsjdm());
                    log("后台根据申报编号为" + sbbh + "的一票一税数据分票后的一票多税缴款书获取税票号码为" + sphm);
                    jks.setSphm(sphm); // 设置一票多税缴款书对象属性
                    // 更新税票号码
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
     * 税款所属开始日期常量
     */
    public static final String SKSSKSRQ = "SKSSKSRQ";

    /**
     * 税款所属结束日期常量
     */
    public static final String SKSSJSRQ = "SKSSJSRQ";

    /**
     * 税款所属日期年度常量
     */
    public static final String SKSSRQ_ND = "ND";
    
    /**
     * @Description: TODO 分离小微企业减免营业税和
     * @param mxInf
     * @return
     */
    private List separator(DeclareInfor declareInfor)
    {
    	List jmXwqyList = new ArrayList();	//被分离出的list
    	
    	//未分离明细数据
    	List mxList = declareInfor.getSbjkmxInfo();


    	if(mxList==null)
    	{
    		return null;
    	}
    	
	
    	/*------小微企业营业税减免:移除源数据，生成专门的list数据---------*/
    	boolean hasYysXwqy = false;					//用于移除附加税
    		
    	/*-------------------------------------月报-----------*/
    	List jmXwqyYysYdList = new ArrayList();		//月度营业税小微企业
    	double sumYdJsJeXwqyYys = 0.0;
    	Iterator iteratorYd = mxList.iterator();	//如有其它减免需移除该 明细中的数据需注意ConcurrentModifiedException	
    	while(iteratorYd.hasNext())
    	{
    		Sbjkmx sbjkmx = (Sbjkmx)iteratorYd.next();
    		
    		String szsmdm_temp = sbjkmx.getSzsmdm();
    		//税种税目是 非罚款或滞纳金的营业税
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
    	
    	//是否够3万计税金额
    	if(sumYdJsJeXwqyYys>30000 || sumYdJsJeXwqyYys == 0){
    		mxList.addAll(jmXwqyYysYdList);
    	}else{
    		jmXwqyList.addAll(jmXwqyYysYdList);
    		hasYysXwqy = true;
    	}
    	
    	/*----------------------------季报-------------------*/
    	List jmXwqyYysJdList = new ArrayList();		//季度营业税小微企业
    	double sumJdJsJeXwqyYys = 0.0;
    	Iterator iteratorJd = mxList.iterator();	//如有其它减免需移除该 明细中的数据需注意ConcurrentModifiedException	
    	while(iteratorJd.hasNext())
    	{
    		Sbjkmx sbjkmx = (Sbjkmx)iteratorJd.next();
    		String szsmdm_temp = sbjkmx.getSzsmdm();
    		//税种税目是 非罚款或滞纳金的营业税
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
    	
    	//是否够9万计税金额
    	if(sumJdJsJeXwqyYys>90000 ||  sumJdJsJeXwqyYys == 0){
    		mxList.addAll(jmXwqyYysJdList);
    	}else{
    		jmXwqyList.addAll(jmXwqyYysJdList);
    		hasYysXwqy = true;
    	}
    	
    	/*---------------------------------------------存在营业税减免则同时减免附加费----------*/
    	if(hasYysXwqy == true)
    	{
    		Iterator iteratorfjf = mxList.iterator();	//如有其它减免需移除该 明细中的数据需注意ConcurrentModifiedException	
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
    	
    	
    	/*---------------------------------------------------------存在文化事业建设费--------*/
    	List jmXwqyWhsyjsfYdList = new ArrayList();		//文化事业建设费
    	double sumYdJsJeXwqyWhsyjsf = 0.0;
    	Iterator iteratorWhsyjsf = mxList.iterator();	//如有其它减免需移除该 明细中的数据需注意ConcurrentModifiedException	
    	while(iteratorWhsyjsf.hasNext())
    	{
    		Sbjkmx sbjkmx = (Sbjkmx)iteratorWhsyjsf.next();
    		
    		String szsmdm_temp = sbjkmx.getSzsmdm();
    		//税种税目是 非罚款或滞纳金的营业税
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
    	
    	//是否够3万计税金额
    	if(sumYdJsJeXwqyWhsyjsf>30000){
    		mxList.addAll(jmXwqyWhsyjsfYdList);
    	}else{
    		jmXwqyList.addAll(jmXwqyWhsyjsfYdList);
    	}
    	
    	
    	
    	
    	/*--------小微企业减免——文化事业建设费------------*/
    	
    	
    	
    	//一元减免在后面已实现
    	/*--*/
    	
    	return jmXwqyList;
    	
    }
    
    /**
     * 计算月报类型的税款所属日期
     * @param curDate 当前日期
     * @return Map 使用Key ＝ Skssrq.SKSSKSRQ 得到 税款所属开始日期Timestamp
     *             使用Key ＝ Skssrq.SKSSJSRQ 得到 税款所属结束日期Timestamp
     *             使用Key ＝ Skssrq.SKSSRQ_ND 得到 税款所属日期所在的年度 String
     */
    public Map monthSkssrq(Date curDate)
    {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(curDate);

        int year = calendar.get(calendar.YEAR);
        String nd = new Integer(year).toString();
        int month = calendar.get(calendar.MONTH);

        int maxDay = calendar.getActualMaximum(calendar.DAY_OF_MONTH);

        // 本月第一天
        Timestamp skssksrqDate =
            new Timestamp(new GregorianCalendar(year, month, 1).getTime().getTime());

        // 本月最后一天
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
     * @Description: TODO 检查征期类型
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
     * @Description: TODO 检查是否是当期税款
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
     * 代征划款数据
     *
     * @param voPackage
     *            前台传过来的申报信息(其中data部分为DeclareInfo)
     * @return (读取DeclareInfo中的一个标识确定是否需要返回缴款数据)<br>
     *         当需要返回缴款数据时 返回返回一个封装的map<br>
     *         不需要时只是返回Boolean.True(成功时)当其他情况都会抛出异常信息<br>
     * @throws Exception
     *             抛出异常
     */
    private Object doGsdzHk(VOPackage vo) throws Exception {
        // 0.句柄申明
        Map rnMap = null;
        String showMsg = "";
        Map mapDeclareInfor = null;
        Sfxy sfxy = null;
        UserData ud = null;
        Map inMap = null;
        DzyjsjVO dzyj = null;
        Map jkdata = null;
        // 1.初始化
        // /1.0.句柄初始化
        rnMap = new HashMap();
        jkdata = new HashMap();
        // /1.1.值对象初始化
        inMap = (Map) vo.getData();
        mapDeclareInfor = (Map) inMap.get("declareMap");
        sfxy = (Sfxy) inMap.get("sfxy");
        //dzyj = (DzyjsjVO) inMap.get(ZhsbMapConstant.CA_QMSJ_VO);
        //Yh yh = (Yh) inMap.get("yh");
        SWDJJBSJ jbsj = (SWDJJBSJ) inMap.get("jbsj");
        // /1.2.获取用户基本账户对象
        ud = vo.getUserData();
        boolean callYyaqSignFlag = ud.caflag;
        //DzyjHelper dh = new DzyjHelper();

   
        
        // 3.判定扣款条件
        boolean kkflag = false;
        if (callYyaqSignFlag) {
            if (sfxy != null) {
               // if (CodeConstant.PRINT_YPDS_KM == printTag) {
                    kkflag = true;
                //}
            }
        }
        log("kkflag=" + kkflag);
        // 4.判定是否三方协议户生成一票多税缴款书，并且根据三方协议修改银行信息
        List ypdsJkss = null;
        if (kkflag) {
        	jkdata.put((String)inMap.get("sbbh"), (String)inMap.get("sbbh"));
            System.out.println("============生成税票号码并更新至数据库开始==============");
            vo.setData(jkdata);
            vo.setAction(ActionConstant.INT_ACTION_YPDS_GENETATE);
            ypdsJkss = this.doGenerateYpdsJks(vo, sfxy);
            System.out.println("============生成税票号码并更新至数据库结束==============");
        }


        // 7.判定是否三方协议户以执行银行扣款,调用税库银扣款接口

        if (kkflag) {
            System.out.println("========网上申报调用税库银扣款接口开始[" + ud.getYhid() + "|" +
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
                    showMsg += "划款成功，从" + sfxy.getYhmc() + "的" + sfxy.getZh() +
                        "账户共扣款人民币" + hjStr + "元。";
                    // 5.判定是否三方协议户以进行银行扣款之前的账务标志调整
                    log("===========调用后台更新账务标志成功开始===============");
                    vo.setAction(ActionConstant.INT_ACTION_ZWBS_YHKK);
                    this.doModifyZwbsYhkk(vo, 0);
                    log("===========调用后台更新账务标志成功完毕===============");
                }
                else if (sa.YHKK_RESULT_NOREASON.equals(result)) {
                    showMsg = "银行返回信息不明，请核实银行账户余额。";
                    log("===========调用后台更新账务标志状态锁定开始===============");
                    vo.setAction(ActionConstant.INT_ACTION_ZWBS_YHKK);
                    this.doModifyZwbsYhkk(vo, 1);
                    log("===========调用后台更新账务标志状态锁定完毕===============");
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
                   throw new Exception("请5分钟后【查看本期缴款书】核对是否已经形成【电子缴库专用缴款书】，并及时核对银行账户金额；如仍有问题，请与技术支持联系，谢谢！");
            	}
            }
        }

        // 8.整理返回数据
        rnMap.put("SBBH", sbbh);
        rnMap.put("reObject", jkdata);
        rnMap.put("showMsg", showMsg);
        rnMap.put("sskk", Boolean.valueOf(kkflag));
//        if (callYyaqSignFlag) {
//            rnMap.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
//        }
        // 99.返回值
        isReturnPaymentInfo=true;
        if (isReturnPaymentInfo) {
            // /99.0.返回按照税款类型来分的由缴款数据list构成的Map
            return rnMap;
        }
        else {
            // /99.1.返回null
            return null;
        }
    }
}
