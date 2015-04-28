/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.util;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ttsoft.bjtax.sfgl.common.model.Grzsfs;
import com.ttsoft.bjtax.sfgl.model.orobj.Dqdedlmx1;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;

/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description: 税费提供的一些公共接口(processor层)</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class InterfaceSf4Sb
{

    /**
     * 获得Dqdedlmx1对象，包含年应纳税额
     * @param jsjdm 计算机代码
     * @param curDate 当前日期
     * @return Map 对象
     * @throws BaseException
     */
    public static Map getYnsje (String jsjdm, Date curDate)
        throws BaseException
    {
        Map map = new HashMap();
        Dqdedlmx1 obj = new Dqdedlmx1();
        try
        {
            com.ttsoft.bjtax.sfgl.proxy.ServiceProxy sfServiceProxy = new
                com.ttsoft.bjtax.sfgl.proxy.ServiceProxy();
            List tempList = (List) sfServiceProxy.getYnsje(jsjdm, curDate,
                curDate);

            for (int i = 0; i < tempList.size(); i++)
            {
                Dqdedlmx1 tempObj = (Dqdedlmx1) tempList.get(i);
                map.put(tempObj.getSzsmdm(), tempObj);

            }
            return map;
        }
        catch (Exception e)
        {
            //e.printStackTrace();
            throw ExceptionUtil.getBaseException(e, "没有获得年应纳税额！");
        }
    }

    /**
     * 通过税费接口得到个人独资合伙企业的征收方式
     * @param  jsjdm 计算机代码
     * @param  rq 日期
     * @return Grzsfs 对象
     * @throws BaseException
     */
    public static Grzsfs getGrzsfsInfo (String jsjdm,
                                        Date rq)
        throws BaseException
    {
        com.ttsoft.bjtax.sfgl.proxy.ServiceProxy sfServiceProxy = new
            com.ttsoft.bjtax.sfgl.proxy.ServiceProxy();
        try
        {
            Grzsfs ret = sfServiceProxy.getGrzsfsInfo(jsjdm, rq);
            return ret;
        }
        catch (BaseException ex)
        {
            throw ExceptionUtil.getBaseException(ex, "没有获得个人独资合伙企业的征收方式！");
        }
    }


}