/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.ejb;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.transaction.UserTransaction;

import com.ttsoft.bjtax.sfgl.common.util.Debug;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description:�ۺ��걨ejb</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class ZqrlEJBBean
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
        if (vo.getAction() == CodeConstant.SMSB_QUERYACTION)
        {
            try
            {
                Debug.out("run in ejb!");
                return ( (Processor) Class.forName(vo.getProcessor()).
                        newInstance()).
                    process(vo);
            }
            catch (BaseException e)
            {
                Debug.out("------EJB   BaseException--------" + e.getMessage());
                throw ExceptionUtil.getBaseException(e);
            }
            catch (Exception ex)
            {
                Debug.out("-------EJB   Exception------------");
                ex.printStackTrace();
                throw ExceptionUtil.getBaseException(ex);
            }
        }
        else
        {
            UserTransaction transaction = null;
            try
            {
                Debug.out("run in ejb!");
                transaction = sessionContext.getUserTransaction();
                transaction.setTransactionTimeout(800);
                transaction.begin();

                Object obj = ( (Processor) Class.forName(vo.getProcessor()).
                              newInstance()).
                             process(vo);
                transaction.commit();
                return obj;
            }
            catch (BaseException e)
            {
                //sessionContext.setRollbackOnly();
                try
                {
                    transaction.rollback();
                }
                catch (Exception ex1)
                {
                    ex1.printStackTrace();
                }
                Debug.out("------EJB   BaseException--------" + e.getMessage());
                throw ExceptionUtil.getBaseException(e);
            }
            catch (Exception ex)
            {
                Debug.out("-------EJB   Exception------------");
                ex.printStackTrace();
                try
                {
                    transaction.rollback();
                }
                catch (Exception ex2)
                {
                    ex2.printStackTrace();
                }
                throw ExceptionUtil.getBaseException(ex);
            }
            finally
            {
                transaction = null;
            }
        }

    }

}
