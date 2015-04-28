/*
 * <p>Title: 北京地税核心征管系统－－契税管理</p>
 * <p>Copyright: (C) 2004-2005 北京市地方税务局，北京创讯益达软件技术有限公司，版权所有. </p>
 * <p>Company: 北京创讯益达软件技术有限公司</p>
 */

package com.creationstar.bjtax.qsgl.BizLogic.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;

/**
 * <p>Title: 北京地税核心征管系统－－契税管理</p>
 * <p>Description: InitialStartupEJB(契税模块初始化的SessionBean) 的home接口 </p>
 * @author 契税开发组－－赵博
 * @version 1.0
 */
public interface InitialStartupHome extends javax.ejb.EJBHome {
    public InitialStartup create() throws CreateException, RemoteException;
}
