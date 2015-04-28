/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.util;

import com.ttsoft.bjtax.jikuai.zwcl.exception.ZwclException;
import com.ttsoft.bjtax.jikuai.zwcl.inf.JKAdapter;
import com.ttsoft.bjtax.jikuai.zwcl.vo.page.Yskm;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description: ͨ���ƻ�ӿ�ȡ�������Ϣ</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class InterfaceJk4Sm
{
    public InterfaceJk4Sm ()
    {
    }

    /**
     * ͨ���ƻ�ӿڵõ�Ԥ���Ŀ����
     * @param szsmdm ˰��˰Ŀ����
     * @param djzclxdm �Ǽ�ע�����ʹ���
     * @param gjbzhydm ���ұ�׼��ҵ����
     * @param ysjcdm Ԥ�㼶�δ���
     * @return Yskm
     * @throws BaseException
     */
    public static Yskm getYskm (String szsmdm, String djzclxdm, String gjbzhydm,
                                String ysjcdm)
        throws
        BaseException
    {
        Yskm yskm = null;
        try
        {
            yskm = JKAdapter.getInstance().getYskm(szsmdm, djzclxdm, gjbzhydm,
                ysjcdm);
        }
        catch (ZwclException ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
        return yskm;
    }


}