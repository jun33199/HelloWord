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
 * <p>Title: 北京地税综合管理系统  申报征收模块</p>
 * <p>申报自用的用于控制事务的接口 </p>
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
                //得到ShenbaoEjb home接口
                ejbHome = (ServiceEJBHome)ResourceLocator.getEjbHome(
                    SHENBAO_HOME_NAME,
                    SHENBAO_CLASS_NAME);
            }
            return ejbHome.create();
        }
        catch(Exception ex)
        {
            ExceptionUtil.getBaseException(ex,"操作失败请与管理员联系!");
        }
        return  null;
    }

    /**
     * 用户获取该计算机代码对应的本月使用的最大序号
     * @param jsjdm 计算机代码8位
     * @param ny 年月yyyyMM 6位
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
     * 获取税种税目对象
     * @param szsmdm 税种税目代码
     * @return Szsm值对象
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
     * 获取登记注册类型
     * @param djzclxdm  登记注册类型代码
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
     * 获取税款所属日期
     * @param smdm 税目代码
     * @param djzclx 登记注册类型
     * @param rq  当前日期
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
