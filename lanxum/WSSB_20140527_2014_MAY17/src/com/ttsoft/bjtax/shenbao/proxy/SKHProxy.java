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
 * Title: 北京地税核心征管网上申报-税库行服务接口
 * </p>
 * <p>
 * Description: 为税库行平台提供核心征管业务接口
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
                // 得到ShenbaoEjb home接口
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
     * 无申报银行缴款根据申报明细生成一票多税缴款书
     * 
     * @param yhsbInfo
     *            申报数据
     * @param hjzse
     *            申报合计总应纳税额
     * @return YhsbResult
     * @throws java.lang.Exception
     *             应用异常
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
     * 有申报银行缴款根据申报编号生成一票多税缴款书
     * 
     * @param yhsbInfo
     *            申报数据
     * @param hjzse
     *            申报合计总应纳税额
     * @return YhsbResult
     * @throws java.lang.Exception
     *             应用异常
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
     * 无申报银行缴款扣款结果
     * 
     * @param kkFlag
     *            扣款成功标记，true-扣款成功，false-扣款失败
     * @param ypdsJkss
     *            一票多税缴款书列表，内部成员为com.ttsoft.bjtax.shenbao.service.vo.YPDSJKS
     *            YPDSJKS对象需要计算机代码、税务机关组织机构代码、税票号码三个参数
     * @return YhsbResult
     * @throws java.lang.Exception
     *             应用异常
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
     * 无申报银行缴款扣款结果
     * 
     * @param kkFlag
     *            扣款成功标记，true-扣款成功，false-扣款失败
     * @param ypdsJkss
     *            一票多税缴款书列表，内部成员为com.ttsoft.bjtax.shenbao.service.vo.YPDSJKS
     *            YPDSJKS对象需要计算机代码、税务机关组织机构代码、税票号码三个参数
     * @return YhsbResult
     * @throws java.lang.Exception
     *             应用异常
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
     * 用申报编号进行银行缴款扣款结果
     * 
     * @param kkFlag
     *            扣款成功标记，true-扣款成功，false-扣款失败
     * @param ypdsJkss
     *            一票多税缴款书列表，内部成员为com.ttsoft.bjtax.shenbao.service.vo.YPDSJKS
     *            YPDSJKS对象需要计算机代码、税务机关组织机构代码、税票号码三个参数
     * @return YhsbResult
     * @throws java.lang.Exception
     *             应用异常
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
     * 有申报银行缴款根据申报明细生成一票多税缴款书,可选税种 @param yhsbInfo 申报数据,应当包括选择的所有税种税目信息 @param
     * hjzse 申报合计总应纳税额 @return YhsbResult @throws java.lang.Exception 应用异常
     */
    public static YhsbResult generateYpdsJksWithSbInfoAndSzAlterable(YhsbInfo yhsbInfo, double hjzse) throws Exception
    {
        try
        {
            long c1 = System.currentTimeMillis();
            Service sv = getEJB();
            long c2 = System.currentTimeMillis();

            System.out.println("银行端有申报: == 取得EJB时间所耗时间:  " + (c2 - c1) / 1000 + "秒");
            YhsbResult yr = sv.generateYpdsJksWithSbInfoAndSzAlterable(yhsbInfo, hjzse);
            long c3 = System.currentTimeMillis();
            System.out.println("银行端有申报: == 调用银行端申报接口方法所耗时间 :  " + (c3 - c2) / 1000 + "秒");
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