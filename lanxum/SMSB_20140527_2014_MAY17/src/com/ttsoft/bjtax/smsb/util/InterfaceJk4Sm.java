/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.util;

import com.ttsoft.bjtax.jikuai.zwcl.exception.ZwclException;
import com.ttsoft.bjtax.jikuai.zwcl.inf.JKAdapter;
import com.ttsoft.bjtax.jikuai.zwcl.vo.page.Yskm;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;

/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description: 通过计会接口取得相关信息</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class InterfaceJk4Sm
{
    public InterfaceJk4Sm ()
    {
    }

    /**
     * 通过计会接口得到预算科目数据
     * @param szsmdm 税种税目代码
     * @param djzclxdm 登记注册类型代码
     * @param gjbzhydm 国家标准行业代码
     * @param ysjcdm 预算级次代码
     * @return Yskm
     * @throws BaseException
     */
    public static Yskm getYskm (String szsmdm, String djzclxdm, String gjbzhydm,
                                String ysjcdm)
        throws
        BaseException
    {
        Yskm yskm = null;
        try
        {
            yskm = JKAdapter.getInstance().getYskm(szsmdm, djzclxdm, gjbzhydm,
                ysjcdm);
        }
        catch (ZwclException ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
        return yskm;
    }


}