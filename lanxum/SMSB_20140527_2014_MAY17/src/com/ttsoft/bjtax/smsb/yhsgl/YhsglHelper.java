/*
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.yhsgl;

import java.util.ArrayList;
import java.util.List;

import com.ttsoft.bjtax.sfgl.common.util.Debug;
import com.ttsoft.bjtax.sfgl.common.util.LabelValueBean;
import com.ttsoft.bjtax.sfgl.common.util.SfHashList;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: ¼��ӡ��˰�������������</p>
 * @author �������飭�������
 * @version 1.1
 */
public class YhsglHelper
{

    /**
     * ����������λ���� ȡ��������Ա�б�
     * @param userSsdwdm ������λ����
     * @return ������Ա�б�
     * @throws BaseException
     */
    public static List getPeopleSelect (String userSsdwdm)
        throws BaseException
    {
        try
        {
            //��Ʊ֤�ӿ�
            SfHashList yhspList = new SfHashList(com.ttsoft.bjtax.pzgl.proxy.
                                                 ServiceProxy.
                                                 getYhsZhBySwjg(userSsdwdm));
            ArrayList dataList = new ArrayList();
            for (int i = 0; i < yhspList.size(); i++)
            {
                LabelValueBean bean = new LabelValueBean(yhspList.get(i, "ZHMC"),
                    yhspList.get(i, "ZHDM"));
                dataList.add(bean);
            }
            Debug.out(yhspList);
            return dataList;
        }
        catch (Exception e1)
        {
            Debug.out("--------Action-Show----------" + e1.getMessage());
            throw ExceptionUtil.getBaseException(e1);
        }
    }
}
