package com.ttsoft.bjtax.smsb.wrkcx.web;

/*
 * <p>Title: 北京地税核心征管系统－－计划统计</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */


import java.util.Map;
import java.util.HashMap;

/**
 * <p>Title: 北京地税核心征管系统－－计划统计</p>
 * <p>Description: 通用查询客户端常量定义</p>
 * @author 开发四组－－陈静
 * @version 1.1
 */
public class WebConstantKey
{
    /**
     * 默认构造函数
     */
    public WebConstantKey()
    {
    }

    /**
     * 查询区县分局税源变化数据Processor类路径
     */
    public final static String SYBDCX_PROCESSOR =
        "com.ttsoft.bjtax.jhtj.sybdcx.processor.SybdcxProcessor";

    /**
     * 清单查询
     */
    public final static int ACTION_QUERY_FHQD = 1;

    /**
     * 汇总查询
     */
    public final static int ACTION_QUERY_HZ = 2;

    /**
     * 详细信息查询
     */
    public final static int ACTION_QUERY_MXQD = 3;

    /**
     * 查询页面中SYBDCXBO 存放到 session 中的Key
     */
    public final static String SESSION_KEY_SYBDCXBO = "SESSION_KEY_SYBDCXBO";
    /**
     * 查询页面中DJXXXXBO 存放到 session 中的Key
     */
    public final static String SESSION_KEY_DJXXXXBO = "SESSION_KEY_DJXXXXBO";

    /**
     * 清单查询FY_LIST
     */
    public final static String FY_LIST = "FY_LIST";
    /**
     * 汇总查询HZ_LIST
     */
    public final static String HZ_LIST = "HZ_LIST";
    /**
     * 详细信息查询HZ_LIST
     */
    public final static String MX_LIST = "MX_LIST";

    /**
     * 分局入库情况查询
     */
    public final static int FJRKQKCX = 21;

    /**
     * 分局退税情况查询
     */
    public final static int FJTSQKCX = 22;
    /**
     * 税务所入库情况查询
     */
    public final static int SWSRKQKCX = 23;

    /**
     * 税务所退税情况查询
     */
    public final static int SWSTSQKCX = 24;

    /**
     * 分局入库转出户清单
     */
    public final static int FJRKZCHCX = 1;

    /**
     * 分局退税转出户清单
     */
    public final static int FJTSZCHCX = 2;
    /**
     * 税务所入库转出户清单
     */
    public final static int SWSRKZCHCX = 3;

    /**
     * 税务所退税转出户清单
     */
    public final static int SWSTSZCHCX = 4;

    /**
     * 分局入库转入户清单
     */
    public final static int FJRKZRHCX = 5;

    /**
     * 分局退税转入户清单
     */
    public final static int FJTSZRHCX = 6;
    /**
     * 税务所入库转入户清单
     */
    public final static int SWSRKZRHCX = 7;

    /**
     * 税务所退税转入户清单
     */
    public final static int SWSTSZRHCX = 8;

    /**
     * 分局入库减少户清单
     */
    public final static int FJRKJSHCX = 9;

    /**
     * 分局退税减少户清单
     */
    public final static int FJTSJSHCX = 10;
    /**
     * 税务所入库减少户清单
     */
    public final static int SWSRKJSHCX = 11;

    /**
     * 税务所退税减少户清单
     */
    public final static int SWSTSJSHCX = 12;

    /**
     * 分局入库新增户清单
     */
    public final static int FJRKXZHCX = 13;

    /**
     * 分局退税新增户清单
     */
    public final static int FJTSXZHCX = 14;
    /**
     * 税务所入库新增户清单
     */
    public final static int SWSRKXZHCX = 15;

    /**
     * 税务所退税新增户清单
     */
    public final static int SWSTSXZHCX = 16;

    /**
     * 分局入库临时登记户清单
     */
    public final static int FJRKLSDJHCX = 17;

    /**
     * 分局退税临时登记户清单
     */
    public final static int FJTSLSDJHCX = 18;
    /**
     * 税务所入库临时登记户清单
     */
    public final static int SWSRKLSDJHCX = 19;

    /**
     * 税务所退税临时登记户清单
     */
    public final static int SWSTSLSDJHCX = 20;
    /**
     * 查询URI
     */
    public static final String RAK_REQUEST_URI = "RAK_REQUEST_URI";
    /**
     * 查询ACTION
     */
    public static final String RAK_ACTION = "RAK_ACTION";

    /**
     * 汇总查询
     */
    public static final String ACTION_HZCX = "SYBDCX_HZCX";

    /**
     * 清单查询
     */
    public static final String ACTION_QDCX = "SYBDCX_QDCX";
    /**
     * 每页显示内容数
     */
    public static final int PAGENUMB = 20;

    /**
     * 税源变动查询分局汇总模板
     */
    public static final String SYBDHZ_TEMPLET = "/jh/webapp/sybdcx/templates/SybdhzTempleExcel.xls";
    /**
     * 税源变动查询分户汇总模板
     */
    public static final String SYBDFH_TEMPLET = "/jh/webapp/sybdcx/templates/SybdfhTempleExcel.xls";
    /**
     * 税源变动查询明细模板
     */
    public static final String SYBDMX_TEMPLET = "/jh/webapp/sybdcx/templates/SybdmxTempleExcel.xls";

    //操作类型
    public static final Map czlxdm = new HashMap();
    //名称类型
    public static final Map mclx = new HashMap();
    //税务机关类型
    public static final Map swjglx = new HashMap();
    //查询类型
    public static final Map cxlx = new HashMap();

    static
    {
        //设置操作类型1：转入，2：转出，3：减少，4：新增，5：临时登记
        czlxdm.put("1", "转入");
        czlxdm.put("2", "转出");
        czlxdm.put("3", "减少");
        czlxdm.put("4", "新增");
        czlxdm.put("5", "临时登记");
        //设置名称类型
        mclx.put("1", "转出机关");
        mclx.put("2", "转入机关");
        mclx.put("3", "税务分局");
        mclx.put("4", "税务分局");
        mclx.put("5", "税务分局");
        //设置税务机关
        swjglx.put("1", "分区县（分局）");
        swjglx.put("2", "分区县（分局）");
        swjglx.put("3", "税务所");
        swjglx.put("4", "税务所");
        swjglx.put("5", "分区县（分局）");
        swjglx.put("6", "分区县（分局）");
        swjglx.put("7", "税务所");
        swjglx.put("8", "税务所");
        swjglx.put("9", "分区县（分局）");
        swjglx.put("10", "分区县（分局）");
        swjglx.put("11", "税务所");
        swjglx.put("12", "税务所");
        swjglx.put("13", "分区县（分局）");
        swjglx.put("14", "分区县（分局）");
        swjglx.put("15", "税务所");
        swjglx.put("16", "税务所");
        swjglx.put("17", "分区县（分局）");
        swjglx.put("18", "分区县（分局）");
        swjglx.put("19", "税务所");
        swjglx.put("20", "税务所");
        swjglx.put("21", "分区县（分局）");
        swjglx.put("22", "分区县（分局）");
        swjglx.put("23", "税务所");
        swjglx.put("24", "税务所");

        //设置查询类型
        cxlx.put("1", "入库");
        cxlx.put("2", "退税");
    }

}
