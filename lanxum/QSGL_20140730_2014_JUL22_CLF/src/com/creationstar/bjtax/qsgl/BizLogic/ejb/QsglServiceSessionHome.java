package com.creationstar.bjtax.qsgl.BizLogic.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;


/**
 *
 * <p>Title: 北京地税核心征管系统－－票证管理</p>
 *
 * <p>Description: 契税退税业务的remote接口 </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: </p>
 *
 * @author 韩雷
 * @version 1.0
 */
public interface QsglServiceSessionHome extends javax.ejb.EJBHome {
    public QsglServiceSession create() throws CreateException, RemoteException;
}
