package com.ttsoft.bjtax.smsb.wrkcx.processor;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

/**
 * <p>Title: 北京地税核心征管系统－－计划统计</p>
 * <p>Description: 税务登记通用查询--使用的数据库字段名称常量定义</p>
 * @author 开发四组－－陈静
 * @version 1.1
 */
public class ConstantKey
{
    /**
     * 默认构造函数
     */
    public ConstantKey()
    {
    }

    //Header.jsp需要的3个参数写入到request中
    /**
     * Header.jsp需要的参数--当前位置
     */
    public static final String HEAD_POSITION = "postion";

    /**
     * Header.jsp需要的参数--帮助url
     */
    public static final String HEAD_HELPURL = "helpurl";

    /**
     * Header.jsp需要的参数--title值
     */
    public static final String HEAD_TITLE = "headtitle";

    //查询类型 1：入库，2：退税
    public static final String CXLX_RK = "1";
    public static final String CXLX_TS = "2";
    //查询级别 1：分局级别查询,2：税务所级别查询
    public static final String CXJB_FJ = "1";
    public static final String CXJB_SWS = "2";
    //操作类型 1：转入，2：转出，3：减少，4：新增，5：临时登记
    public static final String CZLB_ZR = "1";
    public static final String CZLB_ZC = "2";
    public static final String CZLB_JS = "3";
    public static final String CZLB_XZ = "4";
    public static final String CZLB_LSDJ = "5";
    //变更项目代码（组串代码）
    //银行信息
    public final static String BGXM_YHXX = "119";
    //总机构信息
    public final static String BGXM_ZJGXX = "118";
    //分支机构信息
    public final static String BGXM_FZJGXX = "120";
    //法人代表信息
    public final static String BGXM_FRDBXX = "116";
    //投资方信息
    public final static String BGXM_TZFXX = "117";
    //自然人登记-银行帐户表
    public final static String BGXM_ZRR_YHZH = "212";
    //自然人登记-服务单位信息
    public final static String BGXM_ZRR_FWDW = "210";
}