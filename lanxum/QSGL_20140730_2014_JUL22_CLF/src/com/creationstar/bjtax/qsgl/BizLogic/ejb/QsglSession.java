/*
 * <p>Title: 北京地税核心征管系统－－票证管理</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.creationstar.bjtax.qsgl.BizLogic.ejb;

import java.rmi.RemoteException;

import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.util.VOPackage;


/**
 * <p>Title: 北京地税核心征管系统－－票证管理</p>
 * <p>Description: PzglEJB(票证业务SessionBean) 的remote接口 </p>
 * @author 开发五组－－李燕军
 * @version 1.1
 */
public interface QsglSession extends javax.ejb.EJBObject {
    public Object process(VOPackage vo) throws RemoteException, BaseException;
}
