package com.lscdz.qysds.application.jmsba2014;

public class Jmsba2014Contant {
	//新增
    public final static int QYSDS_JMSBAJL_CZLX_ADD=1;
    //修改
    public final static int QYSDS_JMSBAJL_CZLX_MODIFY=2;
    //审核
    public final static int QYSDS_JMSBAJL_CZLX_CHECK=3;
    //查看
    public final static int QYSDS_JMSBAJL_CZLX_VIEW=4;
    //作废变更
    public final static int QYSDS_JMSBAJL_CZLX_ZFBG=5;
    //变更
    public final static int QYSDS_JMSBAJL_CZLX_BGZX=6; 
    
    
    //申请状态代码，1：保存未提交，2：保存未审核，3：提交未审核，4：审核已通过，5：审核未通过
    public final static String QYSDS_JMSBA_SQZT_BCWTJ_CODE="1";
    public final static String QYSDS_JMSBA_SQZT_BCWTJ_NAME="保存未提交";
    
    public final static String QYSDS_JMSBA_SQZT_BCWSH_CODE="2";
    public final static String QYSDS_JMSBA_SQZT_DBG_NAME="待变更";
    public final static String QYSDS_JMSBA_SQZT_BCWSH_NAME="保存未审核";
    
    public final static String QYSDS_JMSBA_SQZT_TJWSH_CODE="3";
    public final static String QYSDS_JMSBA_SQZT_TJWSH_NAME="提交未审核";
    
    public final static String QYSDS_JMSBA_SQZT_SHYTG_CODE="4";
    public final static String QYSDS_JMSBA_SQZT_SHYTG_NAME="审核已通过";
    
    public final static String QYSDS_JMSBA_SQZT_SHWTG_CODE="5";
    public final static String QYSDS_JMSBA_SQZT_SHWTG_NAME="审核未通过";
    
    public final static String QYSDS_JMSBA_SQZT_YZF_CODE="6";
    public final static String QYSDS_JMSBA_SQZT_YZF_NAME="已作废";
    
    //农、林、牧、渔业 设置特殊状态
    public final static String QYSDS_JMSBA_SQZT_TSZD_CODE="7";
    public final static String QYSDS_JMSBA_SQZT_TSZD_NAME="已接受申请";
    
    
    //（十二）从事农、林、牧、渔业项目的所得减免征收企业所得税
    public final static String QYSDS_JMSBA_BASX_0120="0120";
    public final static String QYSDS_JMSBA_BASX_0120_NAME="（十二）从事农、林、牧、渔业项目的所得减免征收企业所得税";
}
