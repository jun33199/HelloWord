package com.ttsoft.bjtax.shenbao.proxy;

import java.util.Date;
import java.util.List;

import com.ttsoft.bjtax.shenbao.ejb.Service;
import com.ttsoft.bjtax.shenbao.ejb.ServiceEJBHome;
import com.ttsoft.bjtax.shenbao.service.vo.YhsbInfo;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.ResourceLocator;
import com.ttsoft.bjtax.shenbao.service.vo.YhsbResult;
import com.ttsoft.bjtax.shenbao.service.constant.SkhConstant;

/**
 * <p>
 * Title: ������˰�������������걨-˰���з���ӿ�
 * </p>
 * <p>
 * Description: Ϊ˰����ƽ̨�ṩ��������ҵ��ӿ�
 * </p>
 * <p>
 * Copyright: Copyright (c) 2005
 * </p>
 * <p>
 * Company: SYAX
 * </p>
 * 
 * @author Ha Zhengze
 * @version 1.0
 */

public class SKHProxy
{

    private static ServiceEJBHome ejbHome = null;

    private static final String SHENBAO_HOME_NAME = "ServiceEJB";

    private static final String SHENBAO_CLASS_NAME = "com.ttsoft.bjtax.shenbao.ejb.ServiceEJBHome";

    private SKHProxy()
    {
    }

    private static Service getEJB()
    {
        try
        {
            if (ejbHome == null)
            {
                // �õ�ShenbaoEjb home�ӿ�
                ejbHome = (ServiceEJBHome) ResourceLocator.getEjbHome(SHENBAO_HOME_NAME, SHENBAO_CLASS_NAME);
            }
            return ejbHome.create();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * ���걨���нɿ�����걨��ϸ����һƱ��˰�ɿ���
     * 
     * @param yhsbInfo
     *            �걨����
     * @param hjzse
     *            �걨�ϼ���Ӧ��˰��
     * @return YhsbResult
     * @throws java.lang.Exception
     *             Ӧ���쳣
     */
    public static YhsbResult generateYpdsJksWithNoSbInfo(YhsbInfo yhsbInfo, double hjzse) throws Exception
    {
        try
        {
            yhsbInfo.printContent();
            return getEJB().generateYpdsJksWithNoSbInfo(yhsbInfo, hjzse);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return getYhsbResultEjbErrorObj();
            // throw ExceptionUtil.getBaseException(e);
        }
    }

    /**
     * ���걨���нɿ�����걨�������һƱ��˰�ɿ���
     * 
     * @param yhsbInfo
     *            �걨����
     * @param hjzse
     *            �걨�ϼ���Ӧ��˰��
     * @return YhsbResult
     * @throws java.lang.Exception
     *             Ӧ���쳣
     */
    public static YhsbResult generateYpdsJksWithSbInfo(YhsbInfo yhsbInfo, double hjzse) throws Exception
    {
        try
        {
            yhsbInfo.printContent();
            return getEJB().generateYpdsJksWithSbInfo(yhsbInfo, hjzse);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return getYhsbResultEjbErrorObj();
            // throw ExceptionUtil.getBaseException(e);
        }
    }

    /**
     * ���걨���нɿ�ۿ���
     * 
     * @param kkFlag
     *            �ۿ�ɹ���ǣ�true-�ۿ�ɹ���false-�ۿ�ʧ��
     * @param ypdsJkss
     *            һƱ��˰�ɿ����б��ڲ���ԱΪcom.ttsoft.bjtax.shenbao.service.vo.YPDSJKS
     *            YPDSJKS������Ҫ��������롢˰�������֯�������롢˰Ʊ������������
     * @return YhsbResult
     * @throws java.lang.Exception
     *             Ӧ���쳣
     */
    public static YhsbResult WsbYhJkKkResult(boolean kkFlag, List ypdsJkss) throws Exception
    {
        try
        {
            return getEJB().WsbYhJkKkResult(kkFlag, ypdsJkss);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return getYhsbResultEjbErrorObj();
            // throw ExceptionUtil.getBaseException(e);
        }
    }

    /**
     * ���걨���нɿ�ۿ���
     * 
     * @param kkFlag
     *            �ۿ�ɹ���ǣ�true-�ۿ�ɹ���false-�ۿ�ʧ��
     * @param ypdsJkss
     *            һƱ��˰�ɿ����б��ڲ���ԱΪcom.ttsoft.bjtax.shenbao.service.vo.YPDSJKS
     *            YPDSJKS������Ҫ��������롢˰�������֯�������롢˰Ʊ������������
     * @return YhsbResult
     * @throws java.lang.Exception
     *             Ӧ���쳣
     */
    public static YhsbResult yhJkKkResult(boolean kkFlag, List ypdsJkss) throws Exception
    {
        try
        {
            return getEJB().yhJkKkResult(kkFlag, ypdsJkss);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return getYhsbResultEjbErrorObj();
            // throw ExceptionUtil.getBaseException(e);
        }
    }

    /**
     * ���걨��Ž������нɿ�ۿ���
     * 
     * @param kkFlag
     *            �ۿ�ɹ���ǣ�true-�ۿ�ɹ���false-�ۿ�ʧ��
     * @param ypdsJkss
     *            һƱ��˰�ɿ����б��ڲ���ԱΪcom.ttsoft.bjtax.shenbao.service.vo.YPDSJKS
     *            YPDSJKS������Ҫ��������롢˰�������֯�������롢˰Ʊ������������
     * @return YhsbResult
     * @throws java.lang.Exception
     *             Ӧ���쳣
     */
    public static YhsbResult yhkkBySbbh(String sbbh, String sphm, String jsjdm, String jksj) throws Exception
    {
        try
        {
            return getEJB().yhkkBySbbh(sbbh, sphm, jsjdm, jksj);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return getYhsbResultEjbErrorObj();
            // throw ExceptionUtil.getBaseException(e);
        }
    }

    /*
     * ���걨���нɿ�����걨��ϸ����һƱ��˰�ɿ���,��ѡ˰�� @param yhsbInfo �걨����,Ӧ������ѡ�������˰��˰Ŀ��Ϣ @param
     * hjzse �걨�ϼ���Ӧ��˰�� @return YhsbResult @throws java.lang.Exception Ӧ���쳣
     */
    public static YhsbResult generateYpdsJksWithSbInfoAndSzAlterable(YhsbInfo yhsbInfo, double hjzse) throws Exception
    {
        try
        {
            long c1 = System.currentTimeMillis();
            Service sv = getEJB();
            long c2 = System.currentTimeMillis();

            System.out.println("���ж����걨: == ȡ��EJBʱ������ʱ��:  " + (c2 - c1) / 1000 + "��");
            YhsbResult yr = sv.generateYpdsJksWithSbInfoAndSzAlterable(yhsbInfo, hjzse);
            long c3 = System.currentTimeMillis();
            System.out.println("���ж����걨: == �������ж��걨�ӿڷ�������ʱ�� :  " + (c3 - c2) / 1000 + "��");
            return yr;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return getYhsbResultEjbErrorObj();
            // throw ExceptionUtil.getBaseException(e);
        }

    }

    public boolean deleteSbjkDataBySphm(String sphm) throws Exception
    {
        try
        {
            return getEJB().deleteSbjkDataBySphm(sphm);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            // return getYhsbResultEjbErrorObj();
            throw ExceptionUtil.getBaseException(e);
        }
    }

    private static YhsbResult getYhsbResultEjbErrorObj()
    {
        YhsbResult yhsbResult = new YhsbResult();
        yhsbResult.setResultCode(SkhConstant.RESULT_CODE_PREFIX_ERROR + SkhConstant.RESULT_CODE_MID_DEFAULT
                + SkhConstant.RESULT_CODE_POSTFIX_ERROR_EJB);
        yhsbResult.setResultDescription(SkhConstant.RESULT_CODE_DESCRIPTION_ERROR_EJB);
        return yhsbResult;
    }

}