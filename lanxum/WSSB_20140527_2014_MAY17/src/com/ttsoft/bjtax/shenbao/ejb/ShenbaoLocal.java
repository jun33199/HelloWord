package com.ttsoft.bjtax.shenbao.ejb;

import javax.ejb.EJBLocalObject;

import com.ttsoft.framework.util.VOPackage;

/**
 *
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: �걨��ϵͳ���̨EJBͨ�ŵĴ����࣬�������̨��ͨ�ű���ͨ������</p>
 * <p>Copyright: (C) 2003-2004 �廪ͬ������ɷ����޹�˾����Ȩ����.</p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 * @author ����һ�飭�����
 * @version 1.1
 */

public interface ShenbaoLocal extends EJBLocalObject
{
    public Object process(VOPackage vo) throws Exception;
}