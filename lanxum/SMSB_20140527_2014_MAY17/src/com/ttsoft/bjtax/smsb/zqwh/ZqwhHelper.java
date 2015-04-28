/*
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.zqwh;

import java.util.ArrayList;
import java.util.List;

import com.ttsoft.bjtax.sfgl.common.code.CodeManager;
import com.ttsoft.bjtax.sfgl.common.constant.CodeConstants;
import com.ttsoft.bjtax.sfgl.common.util.Debug;
import com.ttsoft.bjtax.sfgl.common.util.LabelValueBean;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: ��������������</p>
 * @author �������飭��½��
 * @version 1.1
 */
public class ZqwhHelper
{
    /**
     * ˰��list
     */
    private List szList = new ArrayList();

    /**
     * ˰Ŀlist
     */
    private List smList = new ArrayList();

    /**
     * ��������list
     */
    private List zqlxList = new ArrayList();

    /**
     * �Ǽ�ע������list
     */
    private List djzclxList = new ArrayList();

    /**
     * �·�list
     */
    private List monthList = new ArrayList();

    /**
     * ��ʼ��������
     */
    private Object initLock = new Object();

    /**
     * ҳ�������js
     */
    private String jsOutput = new String();

    /**
     * ��������
     */
    private String zqts = new String();

    /**
     * �õ�ҳ��Js���
     * @return ҳ��ʹ�õ�js��Ϣ�ʹ���
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
     * �õ�˰Ŀ�б�
     * @return ˰Ŀlist
     */
    public List getSmList ()
    {
        szList = new ArrayList();
        smList = new ArrayList();
        getSzsmSelect();
        return smList;
    }

    /**
     * �õ�˰���б�
     * @return ˰��list
     */
    public List getSzList ()
    {
        szList = new ArrayList();
        smList = new ArrayList();
        getSzsmSelect();
        return szList;
    }

    /**
     * �õ��Ǽ�ע�������б�
     * @return �Ǽ�ע������list
     */
    public List getDjzclxList ()
    {
        djzclxList = new ArrayList();
        getSzsmSelect();
        return djzclxList;
    }

    /**
     * �õ��·��б�
     * @return �·�List
     */
    public List getMonthList ()
    {
        monthList = new ArrayList();
        getMonthSelect();
        return monthList;
    }

    /**
     * ����Js���
     */
    private void writeJsOutput ()
    {
        StringBuffer sb = new StringBuffer();
        StringBuffer sbSzOption = new StringBuffer();
        // ��ӡ˰��Select�ؼ�
        sb.append("var strSzSelect = '<select name=\"szdm\">" + "'\n");
        for (int i = 0; i < szList.size(); i++)
        {
            LabelValueBean sz = (LabelValueBean) szList.get(i);
            sb.append("+'<option value=\"" + sz.getValue() + "\">"
                      + sz.getLabel() + "</option>" + "'\n");
        }
        sb.append("+'</select>" + "';\n");
        // ��ӡ��������Select�ؼ�
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
        // ��ӡ�Ǽ�ע������Select�ؼ�
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
     * 1.�Ӵ�����еõ�˰��˰Ŀ��List
     * 2.�Ӵ�����еõ���������
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
     * 1.�Ӵ�����еõ�˰��˰Ŀ��List
     * 2.�Ӵ�����еõ���������
     */
    private void getMonthSelect ()
    {
        LabelValueBean monthBean = new LabelValueBean("", "");
        monthBean.setLabel("ȫ��");
        monthBean.setValue("*");
        String[] strLable =
            {"һ", "��", "��", "��", "��", "��", "��", "��", "��", "ʮ",
            "ʮһ", "ʮ��"};
        String[] strValue =
            {"01", "02", "03", "04", "05", "06", "07", "08",
            "09", "10", "11", "12"};
        monthList.add(monthBean);
        for (int i = 0; i < 12; i++)
        {
            LabelValueBean tempBean = new LabelValueBean("", "");
            tempBean.setLabel(strLable[i] + "��");
            tempBean.setValue(strValue[i]);
            monthList.add(tempBean);
        }
    }

    /**
     * �ֱ�Ϊ˰��˰ĿList������Ӧ��ֵ
     * @param codeList ˰��˰ĿList
     */
    private void setSzSmList (List codeList)
    {
        for (int i = 0; i < codeList.size(); i++)
        {
            // ȡ��˰��˰ĿMap����
            LabelValueBean szsm = (LabelValueBean) codeList.get(i);
            // ˰��˰Ŀ����
            String szsmdm = szsm.getValue();
            // ˰��˰Ŀ����
            String szsmmc = szsm.getLabel();
            // ˰��˰Ŀ���볤��С��6����˰�֣��ҹ��˷���
            if (szsmdm.length() < 6 && !szsmdm.startsWith("20"))
            {
                LabelValueBean newSzsm = new LabelValueBean("", "");
                int len = szsmdm.length();
                //Ӫҵ˰
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
                        newSzsm.setLabel("��" + szsmmc);
                    }
                    else if (len == 4)
                    {
                        newSzsm.setValue(szsmdm);
                        newSzsm.setLabel("����" + szsmmc);
                    }
                    szList.add(newSzsm);
                }
                // ��������˰
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
                        newSzsm.setLabel("��" + szsmmc);
                    }
                    szList.add(newSzsm);
                }
                // ��Դ˰
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
                        newSzsm.setLabel("��" + szsmmc);
                    }
                    szList.add(newSzsm);
                }
                // ����˰��
                else
                {
                    newSzsm.setValue(szsmdm);
                    newSzsm.setLabel(szsmmc);
                    szList.add(newSzsm);
                }
            }
            // ����Ϊ6����˰Ŀ
            else
            {
                //���˷���
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
     * �ֱ�Ϊ�Ǽ�ע������List������Ӧ��ֵ
     * @param djzclxList �Ǽ�ע������List
     * @return ��ֵ�ĵǼ�ע������List
     */
    private List setDjzclxList (List djzclxList)
    {
        List tempList = new ArrayList();
        // ȡ��˰��˰ĿMap����
        LabelValueBean djzclx = new LabelValueBean("", "");
        djzclx.setLabel("ȫ��");
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
     * �ڲ����Է���
     * @param args ����
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
