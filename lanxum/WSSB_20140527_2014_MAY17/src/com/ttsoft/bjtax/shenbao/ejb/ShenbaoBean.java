package com.ttsoft.bjtax.shenbao.ejb;

import javax.ejb.*;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.exception.BaseException;

/**
 *
 * <p>Title: 北京地税核心征管系统－－网上申报</p>
 * <p>Description: 申报模块的ejb实现类,会根据不同的参数调用不同的processor</p>
 * <p>Copyright: (C) 2003-2004 清华同方软件股份有限公司，版权所有.</p>
 * <p>Company: 清华同方软件股份有限公司</p>
 * @author 开发一组－－张昕
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
     * 调用对应的processor来处理对应的业务
     * @param vo 处理的参数
     * @return  处理结果
     * @throws Exception 异常信息
     */
    public Object process(VOPackage vo) throws BaseException, Exception
    {
        try
        {
            //返回处理结果
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