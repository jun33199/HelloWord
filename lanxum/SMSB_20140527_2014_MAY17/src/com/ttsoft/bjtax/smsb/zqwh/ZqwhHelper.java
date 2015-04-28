/*
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */
package com.ttsoft.bjtax.smsb.zqwh;

import java.util.ArrayList;
import java.util.List;

import com.ttsoft.bjtax.sfgl.common.code.CodeManager;
import com.ttsoft.bjtax.sfgl.common.constant.CodeConstants;
import com.ttsoft.bjtax.sfgl.common.util.Debug;
import com.ttsoft.bjtax.sfgl.common.util.LabelValueBean;

/**
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Description: 征期日历公用类</p>
 * @author 开发六组－－陆峰
 * @version 1.1
 */
public class ZqwhHelper
{
    /**
     * 税种list
     */
    private List szList = new ArrayList();

    /**
     * 税目list
     */
    private List smList = new ArrayList();

    /**
     * 征期类型list
     */
    private List zqlxList = new ArrayList();

    /**
     * 登记注册类型list
     */
    private List djzclxList = new ArrayList();

    /**
     * 月份list
     */
    private List monthList = new ArrayList();

    /**
     * 初始给定对象
     */
    private Object initLock = new Object();

    /**
     * 页面输出的js
     */
    private String jsOutput = new String();

    /**
     * 征期天数
     */
    private String zqts = new String();

    /**
     * 得到页面Js语句
     * @return 页面使用的js信息和代码
     */
    public String getJsOutput ()
    {
        szList = new ArrayList();
        smList = new ArrayList();
        getSzsmSelect();
        writeJsOutput();
        return jsOutput;
    }

    /**
     * 得到税目列表
     * @return 税目list
     */
    public List getSmList ()
    {
        szList = new ArrayList();
        smList = new ArrayList();
        getSzsmSelect();
        return smList;
    }

    /**
     * 得到税种列表
     * @return 税种list
     */
    public List getSzList ()
    {
        szList = new ArrayList();
        smList = new ArrayList();
        getSzsmSelect();
        return szList;
    }

    /**
     * 得到登记注册类型列表
     * @return 登记注册类型list
     */
    public List getDjzclxList ()
    {
        djzclxList = new ArrayList();
        getSzsmSelect();
        return djzclxList;
    }

    /**
     * 得到月份列表
     * @return 月份List
     */
    public List getMonthList ()
    {
        monthList = new ArrayList();
        getMonthSelect();
        return monthList;
    }

    /**
     * 生成Js语句
     */
    private void writeJsOutput ()
    {
        StringBuffer sb = new StringBuffer();
        StringBuffer sbSzOption = new StringBuffer();
        // 打印税种Select控件
        sb.append("var strSzSelect = '<select name=\"szdm\">" + "'\n");
        for (int i = 0; i < szList.size(); i++)
        {
            LabelValueBean sz = (LabelValueBean) szList.get(i);
            sb.append("+'<option value=\"" + sz.getValue() + "\">"
                      + sz.getLabel() + "</option>" + "'\n");
        }
        sb.append("+'</select>" + "';\n");
        // 打印征期类型Select控件
        sb.append("var strZqlxSelect = '<select name=\"zqlxdm\" >" + "'\n");
        for (int i = 0; i < zqlxList.size(); i++)
        {
            LabelValueBean bean = (LabelValueBean) zqlxList.get(i);
            sb.append("+'<option value=\"" + bean.getValue() + "\">"
                      + bean.getLabel() + "</option>" + "'\n");
            sbSzOption.append("+'<option value=\"" + bean.getValue() + "\">"
                              + bean.getLabel() + "</option>" + "'\n");
        }
        sb.append("+'</select>" + "';\n");
        // 打印登记注册类型Select控件
        sb.append("var strDjzclxSelect = '<select name=\"djzclxdm\" >" + "'\n");
        for (int i = 0; i < djzclxList.size(); i++)
        {
            LabelValueBean bean = (LabelValueBean) djzclxList.get(i);
            sb.append("+'<option value=\"" + bean.getValue() + "\">"
                      + bean.getLabel() + "</option>" + "'\n");
            sbSzOption.append("+'<option value=\"" + bean.getValue() + "\">"
                              + bean.getLabel() + "</option>" + "'\n");
        }
        sb.append("+'</select>" + "';\n");

        sbSzOption.append("+'</select>" + "';\n");
        jsOutput = sb.toString();
    }

    /**
     * 1.从代码表中得到税种税目的List
     * 2.从代码表中得到征期类型
     */
    private void getSzsmSelect ()
    {
        List codeList = CodeManager.getCodeList("ZQWH_SZSM",
                                                CodeConstants.CODE_MAP_BEANLIST).
                        getRecords();
        setSzSmList(codeList);

        zqlxList = CodeManager.getCodeList("ZQWH_ZQLX",
                                           CodeConstants.
                                           CODE_MAP_BEANLIST).getRecords();
        djzclxList = CodeManager.getCodeList("ZQWH_DJZCLX",
                                             CodeConstants.
                                             CODE_MAP_BEANLIST).getRecords();
        djzclxList = setDjzclxList(djzclxList);
    }

    /**
     * 1.从代码表中得到税种税目的List
     * 2.从代码表中得到征期类型
     */
    private void getMonthSelect ()
    {
        LabelValueBean monthBean = new LabelValueBean("", "");
        monthBean.setLabel("全年");
        monthBean.setValue("*");
        String[] strLable =
            {"一", "二", "三", "四", "五", "六", "七", "八", "九", "十",
            "十一", "十二"};
        String[] strValue =
            {"01", "02", "03", "04", "05", "06", "07", "08",
            "09", "10", "11", "12"};
        monthList.add(monthBean);
        for (int i = 0; i < 12; i++)
        {
            LabelValueBean tempBean = new LabelValueBean("", "");
            tempBean.setLabel(strLable[i] + "月");
            tempBean.setValue(strValue[i]);
            monthList.add(tempBean);
        }
    }

    /**
     * 分别为税种税目List设置相应的值
     * @param codeList 税种税目List
     */
    private void setSzSmList (List codeList)
    {
        for (int i = 0; i < codeList.size(); i++)
        {
            // 取得税种税目Map对象
            LabelValueBean szsm = (LabelValueBean) codeList.get(i);
            // 税种税目代码
            String szsmdm = szsm.getValue();
            // 税种税目名称
            String szsmmc = szsm.getLabel();
            // 税种税目代码长度小于6就是税种，且过滤罚金
            if (szsmdm.length() < 6 && !szsmdm.startsWith("20"))
            {
                LabelValueBean newSzsm = new LabelValueBean("", "");
                int len = szsmdm.length();
                //营业税
                if (szsmdm.startsWith("02"))
                {
                    if (len == 2)
                    {
                        newSzsm.setValue(szsmdm);
                        newSzsm.setLabel(szsmmc);
                    }
                    else if (len == 3)
                    {
                        newSzsm.setValue(szsmdm);
                        newSzsm.setLabel("　" + szsmmc);
                    }
                    else if (len == 4)
                    {
                        newSzsm.setValue(szsmdm);
                        newSzsm.setLabel("　　" + szsmmc);
                    }
                    szList.add(newSzsm);
                }
                // 个人所得税
                else if (szsmdm.startsWith("05"))
                {
                    if (len == 2)
                    {
                        newSzsm.setValue(szsmdm);
                        newSzsm.setLabel(szsmmc);
                    }
                    else if (len == 4)
                    {
                        newSzsm.setValue(szsmdm);
                        newSzsm.setLabel("　" + szsmmc);
                    }
                    szList.add(newSzsm);
                }
                // 资源税
                else if (szsmdm.startsWith("14"))
                {
                    if (len == 2)
                    {
                        newSzsm.setValue(szsmdm);
                        newSzsm.setLabel(szsmmc);
                    }
                    else if (len == 3)
                    {
                        newSzsm.setValue(szsmdm);
                        newSzsm.setLabel("　" + szsmmc);
                    }
                    szList.add(newSzsm);
                }
                // 其他税种
                else
                {
                    newSzsm.setValue(szsmdm);
                    newSzsm.setLabel(szsmmc);
                    szList.add(newSzsm);
                }
            }
            // 长度为6都是税目
            else
            {
                //过滤罚金
                if (!szsmdm.endsWith("91") && !szsmdm.endsWith("92"))
                {
                    LabelValueBean newSzsm = new LabelValueBean("", "");
                    newSzsm.setValue(szsmdm);
                    newSzsm.setLabel(szsmmc);
                    smList.add(newSzsm);
                }
            }
        }
    }

    /**
     * 分别为登记注册类型List设置相应的值
     * @param djzclxList 登记注册类型List
     * @return 带值的登记注册类型List
     */
    private List setDjzclxList (List djzclxList)
    {
        List tempList = new ArrayList();
        // 取得税种税目Map对象
        LabelValueBean djzclx = new LabelValueBean("", "");
        djzclx.setLabel("全部");
        djzclx.setValue("*");
        tempList.add(djzclx);
        for (int i = 0; i < djzclxList.size(); i++)
        {
            LabelValueBean dj = (LabelValueBean) djzclxList.get(i);
            LabelValueBean dj1 = new LabelValueBean("", "");
            dj1.setLabel(dj.getLabel());
            dj1.setValue(dj.getValue());
            tempList.add(dj);
        }
        djzclxList = tempList;
        return tempList;
    }

    /**
     * 内部测试方法
     * @param args 测试
     */
    public static void main (String[] args)
    {
        ZqwhHelper z = new ZqwhHelper();
        String a = z.getJsOutput();
        for (int i = 0; i < z.smList.size(); i++)
        {
            LabelValueBean b = (LabelValueBean) z.smList.get(i);
        }
    }

}
