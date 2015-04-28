/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.ejb;

import java.rmi.RemoteException;
import javax.ejb.CreateException;

/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description:综合申报ejb</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public interface ZqrlEJBHome
    extends javax.ejb.EJBHome
{
    public ZqrlEJB create ()
        throws CreateException, RemoteException;
}