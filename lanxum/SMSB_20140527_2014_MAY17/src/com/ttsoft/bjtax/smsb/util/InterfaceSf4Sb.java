/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.util;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ttsoft.bjtax.sfgl.common.model.Grzsfs;
import com.ttsoft.bjtax.sfgl.model.orobj.Dqdedlmx1;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description: ˰���ṩ��һЩ�����ӿ�(processor��)</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class InterfaceSf4Sb
{

    /**
     * ���Dqdedlmx1���󣬰�����Ӧ��˰��
     * @param jsjdm ���������
     * @param curDate ��ǰ����
     * @return Map ����
     * @throws BaseException
     */
    public static Map getYnsje (String jsjdm, Date curDate)
        throws BaseException
    {
        Map map = new HashMap();
        Dqdedlmx1 obj = new Dqdedlmx1();
        try
        {
            com.ttsoft.bjtax.sfgl.proxy.ServiceProxy sfServiceProxy = new
                com.ttsoft.bjtax.sfgl.proxy.ServiceProxy();
            List tempList = (List) sfServiceProxy.getYnsje(jsjdm, curDate,
                curDate);

            for (int i = 0; i < tempList.size(); i++)
            {
                Dqdedlmx1 tempObj = (Dqdedlmx1) tempList.get(i);
                map.put(tempObj.getSzsmdm(), tempObj);

            }
            return map;
        }
        catch (Exception e)
        {
            //e.printStackTrace();
            throw ExceptionUtil.getBaseException(e, "û�л����Ӧ��˰�");
        }
    }

    /**
     * ͨ��˰�ѽӿڵõ����˶��ʺϻ���ҵ�����շ�ʽ
     * @param  jsjdm ���������
     * @param  rq ����
     * @return Grzsfs ����
     * @throws BaseException
     */
    public static Grzsfs getGrzsfsInfo (String jsjdm,
                                        Date rq)
        throws BaseException
    {
        com.ttsoft.bjtax.sfgl.proxy.ServiceProxy sfServiceProxy = new
            com.ttsoft.bjtax.sfgl.proxy.ServiceProxy();
        try
        {
            Grzsfs ret = sfServiceProxy.getGrzsfsInfo(jsjdm, rq);
            return ret;
        }
        catch (BaseException ex)
        {
            throw ExceptionUtil.getBaseException(ex, "û�л�ø��˶��ʺϻ���ҵ�����շ�ʽ��");
        }
    }


}