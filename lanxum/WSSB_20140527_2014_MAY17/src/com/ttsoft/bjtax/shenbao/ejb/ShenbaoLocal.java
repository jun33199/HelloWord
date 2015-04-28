package com.ttsoft.bjtax.shenbao.ejb;

import javax.ejb.EJBLocalObject;

import com.ttsoft.framework.util.VOPackage;

/**
 *
 * <p>Title: 北京地税核心征管系统－－网上申报</p>
 * <p>Description: 申报子系统与后台EJB通信的代理类，所有与后台的通信必须通过该类</p>
 * <p>Copyright: (C) 2003-2004 清华同方软件股份有限公司，版权所有.</p>
 * <p>Company: 清华同方软件股份有限公司</p>
 * @author 开发一组－－张昕
 * @version 1.1
 */

public interface ShenbaoLocal extends EJBLocalObject
{
    public Object process(VOPackage vo) throws Exception;
}