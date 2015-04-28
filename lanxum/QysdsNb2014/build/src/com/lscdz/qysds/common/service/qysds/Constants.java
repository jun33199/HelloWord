package com.lscdz.qysds.common.service.qysds;

public class Constants {


    //modified by Guoxh, 2007.01.29
//	public static boolean DEBUG_PROGRAN_FLAG = true;
	public static boolean DEBUG_PROGRAN_FLAG = false;

    /**
     * 系统方式-网上
     */
    public static String CREPORTS_SYSTEM_FS_WANGSHANG = "5";
    /**
     * 系统方式-上门
     */
    public static String CREPORTS_SYSTEM_FS_SHANGMENG = "1";

    /**
     * 应用ID， 全部属性列表
     */
    public static String[] CREPORTS_APPID_QYSDS_GROUP = {"001"};

    /**
     * 应用ID，企业所得税
     */
    public static String CREPORTS_APPID_QYSDS = "001";
    /**
     * 应用ID，企业所得税清算
     */
    public static String CREPORTS_APPID_QYQSSDS = "002";

    /**
     * 项目活动属性（参照所有应用、表、项目的属性）， 全部属性列表
     */
    public static String[] CREPORTS_OBJ_ACTIVITY_GROUP = {"0", "1", "2", "3",
            "4"};

    /**
     * 项目活动属性（参照所有应用、表、项目的属性），创建未启用
     */
    public static String CREPORTS_OBJ_ACTIVITY_CREATE_NOINUSE = "0";

    /**
     * 项目活动属性（参照所有应用、表、项目的属性），启用
     */
    public static String CREPORTS_OBJ_ACTIVITY_INUSE = "1";

    /**
     * 项目活动属性（参照所有应用、表、项目的属性），暂停
     */
    public static String CREPORTS_OBJ_ACTIVITY_PAUSE = "2";

    /**
     * 项目活动属性（参照所有应用、表、项目的属性），停用
     */
    public static String CREPORTS_OBJ_ACTIVITY_STOP = "3";

    /**
     * 项目活动属性（参照所有应用、表、项目的属性），作废
     */
    public static String CREPORTS_OBJ_ACTIVITY_DROP = "4";

    /**
     * 报表类型（仅参照表属性）， 全部属性列表
     */
    public static String[] CREPORTS_TABLE_REPORTTYPE_GROUP = {"0", "1", "2"};

    /**
     * 报表类型（仅参照表属性）,0-一般一维表
     */
    public static String CREPORTS_TABLE_REPORTTYPE_NORMAL_ONE_DIMENSIONALITY =
            "0";

    /**
     * 报表类型（仅参照表属性）,1-一维变长表
     */
    public static String CREPORTS_TABLE_REPORTTYPE_ONE_DIMENSIONALITY_ALTER =
            "1";

    /**
     * 报表类型（仅参照表属性）,2-二维变长表
     */
    public static String CREPORTS_TABLE_REPORTTYPE_TWO_DIMENSIONALITY_ALTER =
            "2";

    /**
     * 报表项类型（仅参照录入项属性）， 全部属性列表
     */
    public static String[] CREPORTS_ITEM_INPUT_TYPE_GROUP = {"0", "1", "2", "3",
            "4", "5", "6", "7"};

    /**
     * 报表项类型（仅参照录入项属性）， 数字无子表不可变长
     */
    public static String CREPORTS_ITEM_INPUT_TYPE_NOCHILD_NUMBER_NOALTER = "0";

    /**
     * 报表项类型（仅参照录入项属性）， 数字无子表可变长
     */
    public static String CREPORTS_ITEM_INPUT_TYPE_NOCHILD_NUMBER_ALTER = "1";

    /**
     * 报表项类型（仅参照录入项属性）， 字符串无子表可变长
     */
    public static String CREPORTS_ITEM_INPUT_TYPE_NOCHILD_STRING_NOALTER = "2";

    /**
     * 报表项类型（仅参照录入项属性）， 字符串无子表可变长
     */
    public static String CREPORTS_ITEM_INPUT_TYPE_NOCHILD_STRING_ALTER = "3";

    /**
     * 报表项类型（仅参照录入项属性）， 数字子表不可变长
     */
    public static String CREPORTS_ITEM_INPUT_TYPE_CHILD_NUMBER_NOALTER = "4";

    /**
     * 报表项类型（仅参照录入项属性）， 数字子表可变长
     */
    public static String CREPORTS_ITEM_INPUT_TYPE_CHILD_NUMBER_ALTER = "5";

    /**
     * 报表项类型（仅参照录入项属性）， 字符串子表可变长
     */
    public static String CREPORTS_ITEM_INPUT_TYPE_CHILD_STRING_NOALTER = "6";

    /**
     * 报表项类型（仅参照录入项属性）， 字符串子表可变长
     */
    public static String CREPORTS_ITEM_INPUT_TYPE_CHILD_STRING_ALTER = "7";

    /**
     * 报表项类型（仅参照样式项属性）， 全部属性列表
     */
    public static String[] CREPORTS_ITEM_STYLE_TYPE_GROUP = {"0", "1"};

    /**
     * 报表项类型（仅参照样式项属性）， 样式项
     */
    public static String CREPORTS_ITEM_STYLE_TYPE_STYLE_ITEM = "0";

    /**
     * 报表项类型（仅参照样式项属性）， 录入项
     */
    public static String CREPORTS_ITEM_STYLE_TYPE_INPUT_ITEM = "1";

    /**
     * 公式类型（仅参照样式项属性）， 全部属性列表
     */
    public static String[] CREPORTS_FORMULA_TYPE_GROUP = {"0", "1", "2", "3"};

    /**
     * 公式类型（仅参照样式项属性）， 表内验证公式
     */
    public static String CREPORTS_FORMULA_TYPE_IN_CHECK = "0";

    /**
     * 公式类型（仅参照样式项属性）， 表间验证公式
     */
    public static String CREPORTS_FORMULA_TYPE_BETWEEN_CHECK = "1";

    /**
     * 公式类型（仅参照样式项属性）， 表内审核公式
     */
    public static String CREPORTS_FORMULA_TYPE_IN_FORBIDDEN = "2";

    /**
     * 公式类型（仅参照样式项属性）， 表间审核公式
     */
    public static String CREPORTS_FORMULA_TYPE_BETWEEN_FORBIDDEN = "3";

    /**
     * 域值标识，单行
     */
    public static String CREPORTS_ITEM_FIELD_FLAG_SINGLELINE = "0";

    /**
     * 域值标识，多行
     */
    public static String CREPORTS_ITEM_FIELD_FLAG_MULITLINES = "1";


    /**
     * 报表期类型（）， 全部属性列表
     */
    public static String[] CREPORTS_BBQLX_TYPE_GROUP = {"0", "1", "2"};

    /**
     * 报表期类型（）， 月报
     */
    public static String CREPORTS_IBBQLX_TYPE_MONTH = "0";

    /**
     * 报表期类型（）， 季报
     */
    public static String CREPORTS_IBBQLX_TYPE_QUARTOR = "1";

    /**
     * 报表期类型（）， 年报
     */
    public static String CREPORTS_IBBQLX_TYPE_YEAR = "2";

    /**
     * 企业所得税年度申报表审核状态-保存成功
     */
    public static final String QYSDS_SHZT_SAVE ="0";
    /**
     * 企业所得税年度申报表审核状态-单表审核通过
     */
    public static final String QYSDS_SHZT_SINGLE_PASS ="1";
    /**
     * 企业所得税年度申报表审核状态-全表审核通过
     */
    public static final String QYSDS_SHZT_ALL_PASS ="2";

    /**
     * 申报表录入需要控制的特殊字符（Java中使用）
     */
    public static final String QYSDS_CONTROL_CHAR_FOR_JAVA = "\"=%=_=*='=&=\\=] ]>=&lt=&gt=&amp=&apos=&quot=<=>=,=?=/=|=]=[=}={=;=:=)=(";
    /**
     * 申报表录入需要控制的特殊字符（Js中使用）
     */
    public static final String QYSDS_CONTROL_CHAR_FOR_JS = "\\\"=%=_=*=\\'=&=\\\\=] ]>=&lt=&gt=&amp=&apos=&quot=<=>=,=?=/=|=]=[=}={=;=:=)=(";

    
    
    /**
     * 企业所得税年度申报表审核状态-保存成功
     */
    public static final String QYQSSDS_SHZT_SAVE ="0";
    /**
     * 企业所得税年度申报表审核状态-单表审核通过
     */
    public static final String QYQSSDS_SHZT_SINGLE_PASS ="1";
    /**
     * 企业所得税年度申报表审核状态-全表审核通过
     */
    public static final String QYQSSDS_SHZT_ALL_PASS ="2";
    /**
     * 申报表录入需要控制的特殊字符（Java中使用）
     */
    public static final String QYQSSDS_CONTROL_CHAR_FOR_JAVA = "\"=%=_=*='=&=\\=] ]>=&lt=&gt=&amp=&apos=&quot=<=>=,=?=/=|=]=[=}={=;=:=)=(";
    /**
     * 申报表录入需要控制的特殊字符（Js中使用）
     */
    public static final String QYQSSDS_CONTROL_CHAR_FOR_JS = "\\\"=%=_=*=\\'=&=\\\\=] ]>=&lt=&gt=&amp=&apos=&quot=<=>=,=?=/=|=]=[=}={=;=:=)=(";
    /**
     * 报表期类型（）企业清算定位3 与企业所得税区别开
     */
    public static String CREPORTS_IBBQLX_TYPE_QYQSSDS = "3";
    /**
     * 报表方式--年报（01）
     */
    public static String QYSDS_NB_BBFS="01";
    
}
