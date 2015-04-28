package com.ttsoft.bjtax.shenbao.constant;

/**
 *
 * <p>Title: 北京地税核心征管系统－－网上申报</p>
 * <p>Description: 税种税目代码常量</p>
 * <p>Copyright: (C) 2003-2004 清华同方软件股份有限公司，版权所有.</p>
 * <p>Company: 清华同方软件股份有限公司</p>
 * @author 开发一组－－张昕
 * @version 1.1
 */
public class SzsmdmConstant
{
    public SzsmdmConstant()
    {
    }

    /*-----------------------印花税税种代码和税种名称----------------*/
    public static final String YHS = "16";

    /**
     * 印花税其他帐簿的税种税目代码（按份数×单位税额计算已纳税额）
     */
    public static final String YHS_QTZB = "161220";

    /**
     * 印花税权力、许可证照的税种税目代码（按份数×单位税额计算已纳税额）
     */
    public static final String YHS_QLXKZZ = "161300";

    /**
     * 工薪定额代码
     */
    public static final String GRSDS_GXDE= "050130";

    /**
     * 企业所得税税种
     */
    public static final String QYSDS = "30";

    /**
     * 企业所得税税目
     */
    public static final String QYSDS_SM = "300000";

    /**
     * 房产税
     */
    public static final String FCS = ",12,89,";

    /**
     * 车船使用费
     */
    public static final String CCSYF = ",11,88,";

    /**
     * 土地使用费
     */
    public static final String TDSYF = ",15,76,";

    /**
     * 营业税
     */
    public static final String YYS = "02";

    /**
     * 金融业
     */
    public static final String JRY = "023";

    /**
     * 营业税----铁路运输
     */
    public static final String YYS_TLYS = "021101";

    /**
     * 个人所得税
     */
    public static final String GRSDS = "05";

    /**
     * 个人所得税滞纳金
     */
    public static final String GRSDS_ZNJ = "050091";

    /**
     * 个人所得税检查、评估补税
     */
    public static final String GRSDS_JCPGBS = "050092";

    /**
     * 个人所得税(个体工商户经营所得)
     */
    public static final String GTGSH = "0512";

    /**
     * 个人所得税(个人承包承租客运所得)
     */
    public static final String GRCB = "0502";

    /**
     * 工薪－奖金-分红(调整前适用)
     */
    public static final String GRGXJJFHTZQ = "05011";

    /**
     * 工薪定额(调整前适用)
     */
    public static final String GRGXDETZQ = "050130";

    /**
     * 数月奖金(调整前适用)
     */
    public static final String GRSYJJTZQ = "050150";
    
    /**
     * 调资(调整前适用)
     */
    public static final String GRTZQ = "050151";
    
    /**
     * 退职费(调整前适用)
     */
    public static final String GRTZFTZQ = "050152";
    
    /**
     * 资源税
     */
    public static final String ZYS = "14";

    /**
     * 文化事业建设费
     */
    public final static String WHSYJSF = "53";

    /**
     * 城市集体服务事业费
     */
    public final static String CSJTFWSYF = "52";

    /**
     * 城市维护建设税_营业税
     */
    public final static String CSWHJSS_YYS = "100010";

    /**
     * 教育费附加_营业税
     */
    public final static String JYFFJ_YYS = "510010";
    
    //无税减免原因List  减免税项目  tujb 200404
    /**
     * 契税
     */
    public static final String QS = "74";
    
    /**
     * 耕地占用税
     */
    public static final String GDZYS = "73";
    
    /**
     * 固定投资方向调节税
     */
    public static final String GDTZFXTJS = "18";
    //无税减免原因List  减免税项目  tujb 200404
}