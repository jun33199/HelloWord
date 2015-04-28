package com.ttsoft.bjtax.shenbao.gzsx.processor;

import java.sql.*;
import java.text.*;
import java.util.*;

import com.ekernel.db.or.*;
import com.ttsoft.bjtax.shenbao.constant.*;
import com.ttsoft.bjtax.shenbao.model.domain.*;
import com.ttsoft.bjtax.shenbao.util.*;
import com.ttsoft.framework.exception.*;
import com.ttsoft.framework.processor.*;
import com.ttsoft.framework.util.*;
import com.ttsoft.bjtax.dj.proxy.ServiceProxy;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;


/**
 * <p>Title: 北京地税综合管理系统  申报征收模块</p>
 * <p>Description: 告知事项的processor</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: TTSOFT</p>
 * @author Stone
 * @version 1.0 2003-9-1
 */

public class GzsxProcessor implements Processor
{
    private static final int SESSION_ID = 0;

    private static final String DJ_JBSJ = "JBSJ";
    public GzsxProcessor()
    {
    }

    /**
     * 实现接口Processor的process方法
     * @param vo VOPackage参数包
     * @return 返回处理结果为告知事项的List
     * @throws BaseException 操作异常
     */
    public Object process(VOPackage vo) throws BaseException
    {
        try
        {
            if(vo.getAction() == ActionConstant.INT_ACTION_QUERY)
            {
                //查询告知信息
                return doQueryGzsxData(vo);
            }
            else
            {
                throw ExceptionUtil.getBaseException(new Exception(),"参数不合法!");
            }
        }
        catch(Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex,"查询失败!");
        }
    }

    /**
     * 查询告知数据
     * @param vo VOPackage包
     * @return List 查询到的告知信息
     * @throws java.lang.Exception 操作异常
     */
    public List doQueryGzsxData(VOPackage vo) throws Exception
    {
        Connection conn = null;
        try
        {
            String jsjdm = (String)vo.getData();
            //得到当前时间
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String strDate = format.format(new java.util.Date());
            //查询的返回结果
            List queryresult = new ArrayList();

            ServiceProxy sp = new ServiceProxy();
            Map djMap = sp.getDjInfo(jsjdm);
            SWDJJBSJ jbsj = (SWDJJBSJ)djMap.get(DJ_JBSJ);

            String swjgzzjgdm2 = jbsj.getSwjgzzjgdm();
            String qxdm = swjgzzjgdm2.substring(0, 2);
            String superSwjgzzjgdm = swjgzzjgdm2.substring(0, 2) + "00";
            String gjbzhydm = jbsj.getGjbzhydm();
            String djzclxdm = jbsj.getDjzclxdm();

            // 查询后返回数据的条件：
            //	1	时间段内的数据库纪录的计算机代码等于传入的计算机代码
            //	2	时间段内的数据库纪录的计算机代码等于*
            //		并且数据库纪录的GJBZHYDM等于0或者传入的GJBZHYDM
            //		并且数据库纪录的DJZCLXDM等于0或者传入的DJZCLXDM
            //		并且数据库纪录的SWJGZZJGDM2等于0或者传入的SWJGZZJGDM2或者等于区局局码
            //add by wanghw
            StringBuffer sqlW = new StringBuffer();
            sqlW.append("GZSXKSRQ <= to_date('").append(strDate)
                .append("','yyyy-mm-dd') AND GZSXJSRQ >= to_date('")
                .append(strDate).append("','yyyy-mm-dd')")
                .append("  AND ((JSJDM = '").append(jsjdm)
                .append("' AND QXDM = '").append(qxdm)
                .append("') OR (QXDM = '").append(qxdm)
                .append("' AND JSJDM = '*' AND ")
                .append("DJZCLXDM IN ('0','").append(djzclxdm).append("') AND ")
                .append("GJBZHYDM IN ('0','").append(gjbzhydm).append("') AND ")
                .append("SWJGZZJGDM2 IN ('0','").append(swjgzzjgdm2).append("','")
                .append(superSwjgzzjgdm)
                .append("')) OR (JSJDM = '*' AND ")
                .append("DJZCLXDM = '0' AND ")
                .append("GJBZHYDM = '0' AND ")
                .append("SWJGZZJGDM2 = '0'")
                .append(")) order by cjrq desc");

            Vector criteria = new Vector();
            criteria.add(sqlW.toString());
            // 获得 ORManager
            ORManager orManager = DBResource.getORManager(DBResource.OR_SHENBAO);
            ORContext orCtx = new ORContext(Gzsx.class.getName(), criteria);
            //获得数据库连接
            conn = DBResource.getConnection(DBResource.DB_SHENBAO);
            queryresult = (ArrayList)orManager.query(SESSION_ID, conn, orCtx);

            return queryresult;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
        finally
        {
            DBResource.destroyConnection(conn);
        }
    }
}