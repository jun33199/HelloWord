/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.ejb;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import com.ttsoft.bjtax.sfgl.common.util.Debug;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description: ejb</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class SmDefaultEJB
    implements SessionBean
{
    SessionContext sessionContext;

    public void ejbCreate ()
        throws CreateException
    {
    }

    public void ejbRemove ()
    {
    }

    public void ejbActivate ()
    {
    }

    public void ejbPassivate ()
    {
    }

    public void setSessionContext (SessionContext sessionContext)
    {
        this.sessionContext = sessionContext;
    }

    /**
     * @J2EE_METHOD  --  process
     * ҵ���߼�����
     *
     * @param target Ҫ���õ�processor������
     * @param data Ҫ��������ݡ�
     */
    public Object process (VOPackage vo)
        throws BaseException
    {
        try
        {
            Debug.out("run in ejb!");
            return ( (Processor) Class.forName(vo.getProcessor()).newInstance()).
                process(vo);
        }
        catch (BaseException e)
        {
            sessionContext.setRollbackOnly();
            Debug.out("------EJB   BaseException--------" + e.getMessage());
            throw ExceptionUtil.getBaseException(e);
        }
        catch (Exception ex)
        {
            Debug.out("-------EJB   Exception------------");
            ex.printStackTrace();
            sessionContext.setRollbackOnly();
            throw ExceptionUtil.getBaseException(ex);
        }

    }

    /**
     * �������
     */
    public void refreshCache ()
    {
//    CacheManager.refreshCache();
//    Debug.out("-----CLEAR CACHE IN EJB-------");
//    SfMessageSender sender = new SfMessageSender();
//    try {
//      sender.init();
//      sender.send(CodeConstants.JMS_COMMAND_REFRESHCACHE);
//    }
//    catch (BaseException ex) {
//      ex.printStackTrace();
//    }
//    finally {
//      try {
//        sender.close();
//      }
//      catch (BaseException ex1) {
//      }
//    }

    }

}
