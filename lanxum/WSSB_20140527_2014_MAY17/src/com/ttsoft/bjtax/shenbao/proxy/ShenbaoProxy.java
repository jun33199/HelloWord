package com.ttsoft.bjtax.shenbao.proxy;

import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.bjtax.shenbao.ejb.ShenbaoHome;
import com.ttsoft.framework.util.ResourceLocator;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.bjtax.shenbao.ejb.Shenbao;
import java.rmi.RemoteException;
import com.ttsoft.framework.exception.BaseException;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����ģ��</p>
 * <p>Description: �걨��ϵͳ���̨EJBͨ�ŵĴ����࣬�������̨��ͨ�ű���ͨ������</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: TTSOFT</p>
 * @author not attributable
 * @version 1.0
 */

public class ShenbaoProxy
{
    /**
     * ����ShenbaoHome����
     */
    private static ShenbaoHome ejbHome;

    private static final String SHENBAO_HOME_NAME = "shenbao";
    private static final String SHENBAO_CLASS_NAME =
        "com.ttsoft.bjtax.shenbao.ejb.ShenbaoHome";

    /**
     *����ģʽ�µ�proxyʵ��
     */
    protected static ShenbaoProxy instance = new ShenbaoProxy();

    protected ShenbaoProxy()
    {
        try
        {
            //�õ�ShenbaoEjb home�ӿ�
            ejbHome = (ShenbaoHome)ResourceLocator.getEjbHome(
                SHENBAO_HOME_NAME,
                SHENBAO_CLASS_NAME);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public static ShenbaoProxy getInstance()
    {
        if (instance == null && ejbHome == null)
        {
            instance = new ShenbaoProxy();
        }
        return instance;
    }

    public Object process(VOPackage vo) throws BaseException
    {
        try
        {
            //�õ�Ejb
            Shenbao ejb = ejbHome.create();
            //���ش���Ľ��
            return ejb.process(vo);
        }
        catch(RemoteException ex)
        {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(new Exception("����ʧ�����뼼��֧����ϵ!"));
        }
        catch(Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
    }

}
