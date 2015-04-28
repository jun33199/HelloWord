/*
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */
package com.ttsoft.bjtax.smsb.yhsgl.processor;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ttsoft.bjtax.sfgl.common.code.CodeManager;
import com.ttsoft.bjtax.sfgl.common.constant.CodeConstants;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBUtils;
import com.ttsoft.bjtax.sfgl.common.util.LabelValueBean;
import com.ttsoft.bjtax.sfgl.common.util.SfHashList;
import com.ttsoft.bjtax.sfgl.common.util.SfStringUtils;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.yhsgl.web.YhsgmlrcxForm;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Description: 录入印花税购买情况查看后台处理</p>
 * @author 开发六组－－诸光林
 * @version 1.1
 */

public class YhsgmlrckProcessor
    implements Processor
{
    /**
     * 默认构造函数
     */
    public YhsgmlrckProcessor ()
    {
    }

    /**
     * 通用处理调度模块
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return object
     * @throws com.ttsoft.framework.exception.BaseException
     */
    public Object process (VOPackage vo)
        throws com.ttsoft.framework.exception.
        BaseException
    {
        Object result = null;

        if (vo == null)
        {
            throw new NullPointerException();
        }

        switch (vo.getAction())
        {
            case CodeConstant.SMSB_QUERYACTION:
                result = doQuery(vo);
                break;
            default:
                throw new UnsupportedOperationException(
                    "Method process() not yet implemented.");
        }
        return result;
    }

    /**
     * 检索
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return Form
     * @throws BaseException
     */
    private Object doQuery (VOPackage vo)
        throws BaseException
    {
        YhsgmlrcxForm yForm = (YhsgmlrcxForm) vo.getData();
        Connection conn = null;
        UserData userdata = vo.getUserData();

        Map map = SfStringUtils.splitString(yForm.getViewId());
        String xspzh = (String) map.get("xspzh");
        String dsjsjdm = (String) map.get("dsjsjdm");
        String nd = (String) map.get("nd");
        String cjrq = (String) map.get("cjrq");
        String hjje = (String) map.get("hjje");
        String dwdm = (String) map.get("dwdm");
        String dwmc = (String) map.get("dwmc");
        yForm.setMxCjrq(cjrq);
        yForm.setMxHjje(hjje);
        yForm.setMxDwdm(dwdm);
        yForm.setMxDwmc(dwmc);
        //查询的sql
        StringBuffer sqlBuffer = new StringBuffer();
        sqlBuffer.append(
            "select t.spmzdm,t.gpsl,t.je from sbdb.sb_jl_yhsgmmx t where ")
            .append(" t.qxdm = '" + InterfaceDj.getQxdm(vo.getUserData())
                    + "' ")
            .append(" and t.xspzh = '" + xspzh + "' ")
            .append(" and t.dsjsjdm = '" + dsjsjdm + "' ")
            .append(" and t.nd = '" + nd + "' ");
        sqlBuffer.append(" order by t.spmzdm ");

        String sql = new String();
        sql = sqlBuffer.toString();
        SfHashList sfHashList = null;
        try
        {
            conn = SfDBResource.getConnection();
            SfDBUtils sfDB = new SfDBUtils(conn);
            sfHashList = sfDB.getData(sql);
            List recordList = sfHashList.getRecords();
            //处理查询结果
            if (recordList.size() > 0)
            {
                List codeList = CodeManager.getCodeList("YHS_PZZL",
                    CodeConstants.CODE_MAP_BEANLIST).
                                getRecords();
                List mxDataList = new ArrayList();
                for (int i = 0; i < recordList.size(); i++)
                {
                    Map temMap = (Map) recordList.get(i);
                    String spmzdm = (String) temMap.get("spmzdm");
                    for (int j = 0; j < codeList.size(); j++)
                    {
                        LabelValueBean labelValueBean = (LabelValueBean)
                            codeList.get(j);
                        if (spmzdm.equals(labelValueBean.getValue()))
                        {
                            temMap.put("spmzmc", labelValueBean.getLabel());
                            mxDataList.add(temMap);
                            break;
                        }
                    }
                }

                yForm.setMxDataList(mxDataList);
            }
            else
            {
                yForm.setMxDataList(new ArrayList());
                throw new ApplicationException("没有找到指定的记录！");
            }

        }
        catch (Exception sqlex)
        {
            sqlex.printStackTrace();
            throw ExceptionUtil.getBaseException(sqlex);
        }
        finally
        {
            SfDBResource.freeConnection(conn);
        }

        return yForm;

    }

}
