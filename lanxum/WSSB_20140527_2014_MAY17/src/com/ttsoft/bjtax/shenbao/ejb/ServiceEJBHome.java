package com.ttsoft.bjtax.shenbao.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;

/**
 *
 * <p>Title: 北京地税核心征管系统－－网上申报</p>
 * <p>Description: ServiceEjb Home接口</p>
 * <p>Copyright: (C) 2003-2004 清华同方软件股份有限公司，版权所有.</p>
 * <p>Company: 清华同方软件股份有限公司</p>
 * @author 开发一组－－张昕
 * @version 1.1
 */
public interface ServiceEJBHome extends javax.ejb.EJBHome
{
    public Service create() throws CreateException, RemoteException;
}