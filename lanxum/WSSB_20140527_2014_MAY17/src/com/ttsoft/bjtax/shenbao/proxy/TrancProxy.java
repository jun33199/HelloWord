package com.ttsoft.bjtax.shenbao.proxy;

import com.ttsoft.bjtax.shenbao.model.client.DeclareInfor;
import com.ttsoft.bjtax.shenbao.model.client.Sbsj;

import java.util.List;
import com.ttsoft.bjtax.shenbao.service.processor.InterFaceProcessor;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.bjtax.shenbao.ejb.ServiceEJBHome;
import com.ttsoft.framework.util.ResourceLocator;
import com.ttsoft.bjtax.shenbao.ejb.ServiceEJB;
import com.ttsoft.bjtax.shenbao.ejb.Service;
import com.ttsoft.framework.exception.BaseException;
import java.util.Date;
import com.ttsoft.bjtax.shenbao.model.domain.Szsm;
import com.ttsoft.bjtax.shenbao.model.domain.*;
import java.util.Map;
/**
 *
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����ģ��</p>
 * <p>�걨���õ����ڿ�������Ľӿ� </p>
 * <p>Copyright:  (c) 2003</p>
 * <p>Company: TTSOFT</p>
 * @author undefine
 * @version 1.0
 */
public class TrancProxy
{
    private static ServiceEJBHome ejbHome = null;
    private static final String SHENBAO_HOME_NAME = "ServiceEJB";
    private static final String SHENBAO_CLASS_NAME =
        "com.ttsoft.bjtax.shenbao.ejb.ServiceEJBHome";
    private TrancProxy()
    {
    }

    private static Service getEJB()
    {
        try
        {
            if( ejbHome == null)
            {
                //�õ�ShenbaoEjb home�ӿ�
                ejbHome = (ServiceEJBHome)ResourceLocator.getEjbHome(
                    SHENBAO_HOME_NAME,
                    SHENBAO_CLASS_NAME);
            }
            return ejbHome.create();
        }
        catch(Exception ex)
        {
            ExceptionUtil.getBaseException(ex,"����ʧ���������Ա��ϵ!");
        }
        return  null;
    }

    /**
     * �û���ȡ�ü���������Ӧ�ı���ʹ�õ�������
     * @param jsjdm ���������8λ
     * @param ny ����yyyyMM 6λ
     * @return String
     * @throws BaseException
     */
    public static String getSequence(String jsjdm, String ny) throws BaseException
    {
        try
        {
            return getEJB().getXh(jsjdm, ny);
        }
        catch(Exception e)
        {
            throw ExceptionUtil.getBaseException(e);
        }
    }

    /**
     * ��ȡ˰��˰Ŀ����
     * @param szsmdm ˰��˰Ŀ����
     * @return Szsmֵ����
     * @throws BaseException
     */
    public static Szsm getSzsm(String szsmdm) throws BaseException
    {
        try
        {
            return getEJB().getSzsm(szsmdm);
        }
        catch(Exception e)
        {
            throw ExceptionUtil.getBaseException(e);
        }
    }

    /**
     * ��ȡ�Ǽ�ע������
     * @param djzclxdm  �Ǽ�ע�����ʹ���
     * @return Djzclx
     * @throws BaseException
     */
    public static Djzclx getDjzclx(String djzclxdm) throws BaseException
    {
        try
        {
            return getEJB().getDjzclx(djzclxdm);
        }
        catch(Exception e)
        {
            throw ExceptionUtil.getBaseException(e);
        }
    }

    /**
     * ��ȡ˰����������
     * @param smdm ˰Ŀ����
     * @param djzclx �Ǽ�ע������
     * @param rq  ��ǰ����
     * @return Map
     * @throws BaseException
     */
    public static Map getZqzzrq(String smdm, String djzclx, String rq) throws BaseException
    {
        try
        {
            return getEJB().getZqzzrq(smdm, djzclx, rq);
        }
        catch(Exception e)
        {
            throw ExceptionUtil.getBaseException(e);
        }
    }
}
