/*
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.gzwh;

import java.util.*;
import com.ttsoft.bjtax.sfgl.common.code.CodeManager;
import com.ttsoft.bjtax.sfgl.common.constant.CodeConstants;
import com.ttsoft.bjtax.sfgl.common.util.LabelValueBean;
import com.ttsoft.bjtax.sfgl.common.util.Debug;
import com.ttsoft.bjtax.sfgl.common.util.SfHashList;
import com.ttsoft.framework.exception.*;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: ��֪����Ĺ�����</p>
 * @author �������飭��ʯ�ҷ�
 * @version 1.1
 */
public class GzwhHelper
{

    /**
     * ����������λ���� ȡ�õ���������Ϣ
     * @param userSsdwdm �û�������λ����
     * @return ���������б�
     * @throws BaseException
     */
    public static List getDqjsSelect (String userSsdwdm)
        throws BaseException
    {
        try
        {
            List dqjsList = new ArrayList();
            List codeList = CodeManager.getCodeList("GZWH_SWJGZZJG",
                CodeConstants.CODE_MAP_BEANLIST).
                            getRecords();
            if (userSsdwdm.startsWith("90"))
            {
                //ȡ���о��ܴ���ĵ�������
                dqjsList = getCityBureau(userSsdwdm, codeList, dqjsList);
            }
            else if (userSsdwdm.endsWith("00"))
            {
                //ȡ�÷־��ܴ���ĵ�������
                dqjsList = getBureau(userSsdwdm, codeList, dqjsList);
            }
            else
            {
                //ȡ��ָ�����ܴ���ĵ�������
                getDepartment(userSsdwdm, codeList, dqjsList);
            }
            return dqjsList;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e);
        }
    }

    /**
     * ȡ���о��ܴ���ĵ�������
     * @param userSsdwdm �û�������λ����
     * @param codeList ����list
     * @param dqjsList ����������Ϣlist
     * @return dqjsList
     */
    private static List getCityBureau (String userSsdwdm, List codeList,
                                       List dqjsList)
    {
        LabelValueBean firstBean = new LabelValueBean("* ���е�������", "0");
        dqjsList.add(firstBean);
        for (int i = 0; i < codeList.size(); i++)
        {
            LabelValueBean dqjs = (LabelValueBean) codeList.get(i);
            LabelValueBean tempDqjs = new LabelValueBean("", "");
            tempDqjs.setLabel(dqjs.getLabel());
            tempDqjs.setValue(dqjs.getValue());
            if (dqjs.getValue().endsWith("00"))
            {
                dqjsList.add(tempDqjs);
            }
            else
            {
                tempDqjs.setLabel("����" + dqjs.getLabel());
                dqjsList.add(tempDqjs);
            }
        }
        return dqjsList;
    }

    /**
     * ȡ�÷־��ܴ���ĵ�������
     * @param userSsdwdm �û�������λ����
     * @param codeList ����list
     * @param dqjsList ����������Ϣlist
     * @return dqjsList
     */
    private static List getBureau (String userSsdwdm, List codeList,
                                   List dqjsList)
    {
        String subSsdwdm = userSsdwdm.substring(0, 2);
        for (int i = 0; i < codeList.size(); i++)
        {
            LabelValueBean dqjs = (LabelValueBean) codeList.get(i);
            if (dqjs.getValue().startsWith(subSsdwdm))
            {
                LabelValueBean tempDqjs = new LabelValueBean("", "");
                tempDqjs.setLabel(dqjs.getLabel());
                tempDqjs.setValue(dqjs.getValue());
                if (dqjs.getValue().endsWith("00"))
                {
                    dqjsList.add(tempDqjs);
                }
                else
                {
                    tempDqjs.setLabel("����" + dqjs.getLabel());
                    dqjsList.add(tempDqjs);
                }
            }
        }
        return dqjsList;
    }

    /**
     * ȡ��ָ�����ܴ���ĵ�������
     * @param userSsdwdm ������λ����
     * @param codeList ����list
     * @param dqjsList ����������Ϣlist
     * @return dqjsList ����������Ϣlist
     */
    private static List getDepartment (String userSsdwdm, List codeList,
                                       List dqjsList)
    {
        for (int i = 0; i < codeList.size(); i++)
        {
            LabelValueBean dqjs = (LabelValueBean) codeList.get(i);
            if (dqjs.getValue().equals(userSsdwdm))
            {
                LabelValueBean tempDqjs = new LabelValueBean("", "");
                tempDqjs.setLabel(dqjs.getLabel());
                tempDqjs.setValue(dqjs.getValue());
                dqjsList.add(tempDqjs);
            }
        }
        return dqjsList;
    }

}