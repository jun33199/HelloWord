package com.ttsoft.bjtax.shenbao.ejb;

import javax.ejb.*;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.exception.BaseException;

/**
 *
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: �걨ģ���ejbʵ����,����ݲ�ͬ�Ĳ������ò�ͬ��processor</p>
 * <p>Copyright: (C) 2003-2004 �廪ͬ������ɷ����޹�˾����Ȩ����.</p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 * @author ����һ�飭�����
 * @version 1.1
 */

public class ShenbaoBean implements SessionBean
{
    SessionContext sessionContext;
    public void ejbCreate() throws CreateException
    {
    }

    public void ejbRemove()
    {
    }

    public void ejbActivate()
    {
    }

    public void ejbPassivate()
    {
    }

    public void setSessionContext(SessionContext sessionContext)
    {
        this.sessionContext = sessionContext;
    }

    /**
     * ���ö�Ӧ��processor�������Ӧ��ҵ��
     * @param vo ����Ĳ���
     * @return  ������
     * @throws Exception �쳣��Ϣ
     */
    public Object process(VOPackage vo) throws BaseException, Exception
    {
        try
        {
            //���ش�����
            return((Processor)Class.forName(vo.getProcessor()).newInstance()).process(vo);
        }
        catch(BaseException e)
        {
            sessionContext.setRollbackOnly();
            throw e;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            sessionContext.setRollbackOnly();

            throw ExceptionUtil.getBaseException(ex);
        }
    }
}