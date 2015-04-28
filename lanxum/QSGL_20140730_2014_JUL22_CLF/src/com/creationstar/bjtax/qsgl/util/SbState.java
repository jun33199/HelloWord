package com.creationstar.bjtax.qsgl.util;

/**
 * <p>Title: </p>
 *
 * <p>Description: 契税申报数据的状态机。提供获取每个状态的名称，以及每个装态的前一状态等接口
 * </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class SbState {
    /**=========================申报主表中各项标识的说明====================================*/
    /**
     * 申报主表中
     * 状态标示：
     * 0--保存
     * 1--打印申报表
     * 2--已打印减免申报表
     * 3--撤销打印  现在不用
     * 4--打印核定通知书
     * 5--即时办理审核同意
     * 6--即时办理审核不同意
     * 7--已复核（打印完税证/缴款书）
     * 91--作废
     * 99--办理申报结束
     * 来源于Constants
             public static final String ZB_ZTBS_BC = "0";
             public static final String ZB_ZTBS_DY = "1";
             public static final String ZB_ZTBS_DY_JMSBB = "2";
             public static final String ZB_ZTBS_CX_DY = "3";
             public static final String ZB_ZTBS_DY_HD = "4";
             public static final String ZB_ZTBS_JS_TY = "5";
             public static final String ZB_ZTBS_JS_BTY = "6";
             public static final String ZB_ZTBS_YFH = "7";
             public static final String ZB_ZTBS_ZF = "91";
             public static final String ZB_ZTBS_YRK = "99";
     */

    /**
     * 获取状态名称（说明）
     * @param code String 状态代码
     * @return String 状态说明
     */
    public static String getStateName(String code) {
        if (code == null) {
            return "";
        }
        if (code.equals(Constants.ZB_ZTBS_BC)) {
            return "未打印";
        } else if (code.equals(Constants.ZB_ZTBS_DY)) {
            return "已打印申报表";
        } else if (code.equals(Constants.ZB_ZTBS_DY_JMSBB)) {
            return "已打印减免申报表";
        } else if (code.equals(Constants.ZB_ZTBS_CX_DY)) {
            return "撤销打印";
        } else if (code.equals(Constants.ZB_ZTBS_DY_HD)) {
            return "打印核定通知书";
        } else if (code.equals(Constants.ZB_ZTBS_JS_TY)) {
            return "即时办理审核同意";
        } else if (code.equals(Constants.ZB_ZTBS_JS_BTY)) {
            return "即时办理审核不同意";
        } else if (code.equals(Constants.ZB_ZTBS_YFH)) {
            return "已复核（打印完税证或缴款书）";
        } else if (code.equals(Constants.ZB_ZTBS_ZF)) {
            return "作废";
        } else if (code.equals(Constants.ZB_ZTBS_YRK)) {
            return "办理申报结束";
        } else {
            return "";
        }
    }

    /**
     * 获取撤销当前状态，回复后的状态代码
     * @param code 主表状态标志
     * @param jmsbs 减免税标志
     * @return null 如果当前状态非法或者当前状态不能回复
     */
    public static String getCancelStateCode(String code, String jmsbs) {
        if ((code == null) || (jmsbs == null)) {
            return "";
        }

        if (code.equals(Constants.ZB_ZTBS_BC)) {
            //"未打印";
            return "";
        } else if (code.equals(Constants.ZB_ZTBS_DY)) {
            //"已打印申报表";
            return Constants.ZB_ZTBS_BC;
        } else if (code.equals(Constants.ZB_ZTBS_DY_JMSBB)) {
            //"已打印减免申报表";
            return "";
        } else if (code.equals(Constants.ZB_ZTBS_CX_DY)) {
            // "撤销打印";
            return "";
        } else if (code.equals(Constants.ZB_ZTBS_DY_HD)) {
            // "打印核定通知书";
            return Constants.ZB_ZTBS_JS_TY;
        } else if (code.equals(Constants.ZB_ZTBS_JS_TY)) {
            // "即时办理审核同意";
            return Constants.ZB_ZTBS_DY;
        } else if (code.equals(Constants.ZB_ZTBS_JS_BTY)) {
            // "即时办理审核不同意";
            return Constants.ZB_ZTBS_DY;
        } else if (code.equals(Constants.ZB_ZTBS_YFH)) {
            // "已复核（打印完税证或缴款书）";
            if (jmsbs.equals(Constants.ZB_BLJMSBS_BFHBLTJ)) { //不符合，没有打印核定通知书
                return Constants.ZB_ZTBS_DY;
            } else {
                return Constants.ZB_ZTBS_DY_HD;
            }
        } else if (code.equals(Constants.ZB_ZTBS_ZF)) {
            // "作废";
            return "";
        } else if (code.equals(Constants.ZB_ZTBS_YRK)) {
            // "已入库";
            return "";
        } else {
            return "";
        }
    }

    /**
     * 获取撤销当前状态，回复后的状态代码 为减免申报专门的方法
     * @param code 主表状态标志
     * @param jmsbs 减免税标志
     * @return null 如果当前状态非法或者当前状态不能回复
     */
    public static String getCancelStateCode4Jmsb(String code, String jmsbs) {
        if ((code == null) || (jmsbs == null)) {
            return "";
        }

        if (code.equals(Constants.ZB_ZTBS_BC)) {
            //"未打印";
            return "";
        } else if (code.equals(Constants.ZB_ZTBS_DY_JMSBB)) {
            //"已打印减免申报表";
            return Constants.ZB_ZTBS_BC;
        } else if (code.equals(Constants.ZB_ZTBS_DY_HD)) {
            // "打印核定通知书";
            return Constants.ZB_ZTBS_DY_JMSBB;
        } else if (code.equals(Constants.ZB_ZTBS_JS_TY)) {
            // "即时办理审核同意";
            return "";
        } else if (code.equals(Constants.ZB_ZTBS_JS_BTY)) {
            // "即时办理审核不同意";
            return Constants.ZB_ZTBS_DY_HD;
        } else {
            return "";
        }
    }

    /**
     * 获取撤销当前状态，回复后的状态名称
     * @param code String
     * @return String
     */
    public static String getCancelStateName(String code, String jmsbs) {
        return getStateName(getCancelStateCode(code, jmsbs));
    }

    /**
     * 获取撤销当前状态，回复后的状态名称 为减免申报专门的方法
     * @param code String
     * @return String
     */
    public static String getCancelStateName4Jmsb(String code, String jmsbs) {
        return getStateName(getCancelStateCode4Jmsb(code, jmsbs));
    }

}
