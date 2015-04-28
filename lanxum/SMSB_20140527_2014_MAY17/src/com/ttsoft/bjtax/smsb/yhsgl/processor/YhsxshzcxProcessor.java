/*
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */
package com.ttsoft.bjtax.smsb.yhsgl.processor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBUtils;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.bjtax.smsb.yhsgl.web.YhsxshzcxForm;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Description: 撤销印花税代售单位销售汇总 后台处理</p>
 * @author 开发六组－－诸光林
 * @version 1.1
 */

public class YhsxshzcxProcessor
    implements Processor
{

    /**
     * 默认构造函数
     */
    public YhsxshzcxProcessor ()
    {
    }

    /**
     * 通用处理调度模块
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return object
     * @throws com.ttsoft.framework.exception.BaseException
     */
    public Object process (VOPackage vo)
        throws
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
            case CodeConstant.SMSB_CXJKSACTION:
                result = doCxjks(vo);
                break;
            default:
                throw new UnsupportedOperationException(
                    "Method process() not yet implemented.");
        }
        return result;
    }

    /**
     * 查询帐户可以撤销的缴款书
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return Form
     * @throws BaseException
     */
    private Object doQuery (VOPackage vo)
        throws BaseException
    {
        YhsxshzcxForm form = (YhsxshzcxForm) vo.getData();
        String jsjdm = form.getDsjsjdm();
        Connection conn = null;
        try
        {
            ArrayList jksList = new ArrayList();
            conn = SfDBResource.getConnection();
            SfDBAccess da = new SfDBAccess(conn);
            //查询sql
            StringBuffer sqlBuffer = new StringBuffer();
            sqlBuffer.append(
                "select distinct t1.JKPZH, t1.SJJE")
                .append(" from sbdb.sb_jl_sbjkzb t1, sbdb.sb_jl_yhsgmz t2")
                .append(
                " where t1.JKPZH=t2.JKPZH and t1.FSDM='1' and t1.SJLY='15'")
                .append(" and t1.qxdm='" + InterfaceDj.getQxdm(vo.getUserData())
                        + "' ")
                .append(" and t1.zwbs like  '" + CodeConstant.SMSB_ZWBS + "%"
                        + CodeConstant.SMSB_ZWBS + "' ")
                .append(" and t1.JSJDM='").append(jsjdm).append("' ")
                .append(" and t2.qxdm='" + InterfaceDj.getQxdm(vo.getUserData())
                        + "' ")
                .append(" and t2.SJLY='1' and t2.HZFS='0'");

            String sqlString = sqlBuffer.toString();
            ResultSet rs = da.querySQL(sqlString);
            while (rs.next())
            {
                HashMap map = new HashMap();
                map.put("jkpzh", rs.getString("jkpzh"));
                map.put("sjje", rs.getString("sjje"));
                jksList.add(map);
            }
            rs.close();
            if (jksList.size() == 0 && !form.isIsFromCx())
            {
                throw new ApplicationException("没有可以撤销的缴款书！");
            }
            else
            {
                form.setDataList(jksList); //缴款书数据
            }
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
        finally
        {
            SfDBResource.freeConnection(conn);
        }
        return form;
    }

    /**
     * 撤销缴款书
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return Form
     * @throws BaseException
     */
    private Object doCxjks (VOPackage vo)
        throws BaseException
    {
        YhsxshzcxForm form = (YhsxshzcxForm) vo.getData();
        String cxjkpzh = form.getCxjkpzh();
        String dsjsjdm = form.getDsjsjdm();
        Connection conn = null;
        //创建删除日志 20040429 Shi Yanfeng
        TinyTools.makeLog4DsYhsZfjks(vo.getUserData(), cxjkpzh);

        try
        {
            conn = SfDBResource.getConnection();
            SfDBAccess da = new SfDBAccess(conn);

            //验证此缴款书还未入库
            StringBuffer checkSql = new StringBuffer();
            checkSql.append(
                "select count(*) Count from sbdb.sb_jl_sbjkzb where FSDM='1' and SJLY='15'")
                .append(" and substr(zwbs,1,1)='" + CodeConstant.SMSB_ZWBS
                        + "' ")
                .append(" and substr(zwbs,20,1)='" + CodeConstant.SMSB_ZWBS20
                        + "' ")
                .append(" and jkpzh='").append(cxjkpzh).append("'");
            ResultSet rs = da.querySQL(checkSql.toString());
            //若此缴款书可以撤销
            if (rs.next())
            {
                if (rs.getInt("Count") > 0)
                {
                    //删除申报缴款明细表数据
                    StringBuffer jkmxSql = new StringBuffer();
                    jkmxSql.append(
                        "delete from sbdb.sb_jl_sbjkmx where jkpzh='")
                        .append(cxjkpzh).append("'");
                    try
                    {
                        PreparedStatement sqlStatement = conn.prepareStatement(
                            jkmxSql.
                            toString());
                        sqlStatement.execute();
                    }
                    catch (Exception ex8)
                    {
                        throw new ApplicationException("删除申报缴款明细表数据出错！");
                    } //end try

                    //删除申报缴款主表数据
                    StringBuffer jkzbSql = new StringBuffer();
                    jkzbSql.append(
                        "delete from sbdb.sb_jl_sbjkzb where jkpzh='")
                        .append(cxjkpzh).append("'");
                    try
                    {
                        PreparedStatement sqlStatement = conn.prepareStatement(
                            jkzbSql.
                            toString());
                        sqlStatement.execute();
                    }
                    catch (Exception ex8)
                    {
                        throw new ApplicationException("删除申报缴款主表数据出错！");
                    } //end try

                    //得到对应的销售凭证号
                    ArrayList jksList = new ArrayList();
                    StringBuffer yhszbSql = new StringBuffer();
                    yhszbSql.append("select xspzh from sbdb.sb_jl_yhsgmz")
                        .append(" where jkpzh='").append(cxjkpzh).append("'");
                    try
                    {
                        ResultSet rs2 = da.querySQL(yhszbSql.toString());
                        while (rs2.next())
                        {
                            HashMap map = new HashMap();
                            map.put("xspzh", rs2.getString("xspzh"));
                            jksList.add(map);
                        }
                        if (jksList.size() == 0)
                        {
                            throw new ApplicationException("没有相应的印花税购买记录！");
                        }
                        rs2.close();
                    }
                    catch (Exception ex1)
                    {
                        throw new ApplicationException("取相应销售凭证号失败："
                            + ex1.getMessage());
                    }

                    //删除对应的销售数据
                    for (int ii = 0; ii < jksList.size(); ii++)
                    {
                        String xspzh = ( (HashMap) jksList.get(ii)).get("xspzh").
                                       toString();
                        if (xspzh.equals(""))
                        {
                            continue;
                        }
                        //更新印花税票证库存
                        StringBuffer pzSelectSql = new StringBuffer();
                        pzSelectSql.append(
                            "select distinct a.XSPZH,a.SPMZDM,a.GPSL,a.JE,b.zhdm ")
                            .append(
                            " from sbdb.sb_jl_yhsgmmx a, sbdb.sb_jl_yhsgmz b")
                            .append(
                            " where a.xspzh=b.xspzh and a.dsjsjdm=b.dsjsjdm and a.xspzh='")
                            .append(xspzh).append("'").append(
                            " and a.dsjsjdm='")
                            .append(dsjsjdm).append("'");
                        SfDBUtils sfDB = new SfDBUtils(conn);
                        List pzSelectlist = sfDB.getDataList(pzSelectSql.
                            toString());
                        try
                        {
                            if (pzSelectlist.size() > 0)
                            {
                                com.ttsoft.bjtax.pzgl.proxy.ServiceProxy.
                                    yhsUpdatePzkc(
                                    vo.getUserData(),
                                    (String) ( (Map) pzSelectlist.get(0)).get(
                                    "zhdm"), pzSelectlist);
                            }
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                            throw new ApplicationException("更新印花税票库存失败！");
                        }

                        //删除销售明细数据
                        StringBuffer xsmxSql = new StringBuffer();
                        xsmxSql.append(
                            "delete from sbdb.sb_jl_yhsgmmx where xspzh='")
                            .append(xspzh).append("'").append(" and dsjsjdm='")
                            .append(dsjsjdm).append("'");
                        try
                        {
                            PreparedStatement sqlStatement = conn.
                                prepareStatement(xsmxSql.
                                                 toString());
                            sqlStatement.execute();
                        }
                        catch (Exception ex1)
                        {
                            throw new ApplicationException("删除印花税购买明细记录失败！");
                        }

                        //删除销售主表数据
                        StringBuffer xszbSql = new StringBuffer();
                        xszbSql.append(
                            "delete from sbdb.sb_jl_yhsgmz where xspzh='")
                            .append(xspzh).append("'").append(" and dsjsjdm='")
                            .append(dsjsjdm).append("'");
                        try
                        {
                            PreparedStatement sqlStatement = conn.
                                prepareStatement(xszbSql.
                                                 toString());
                            sqlStatement.execute();
                        }
                        catch (Exception ex1)
                        {
                            throw new ApplicationException("删除印花税购买主表记录失败！");
                        } //end try
                    } //end for
                }
            } //end if
            else
            {
                throw new ApplicationException("缴款书 " + cxjkpzh + " 不可撤销！！");
            }
            rs.close();
            return form;
        }
        catch (Exception ex)
        {
            throw new ApplicationException("撤销缴款书失败：" + ex.getMessage());
        } //end try
        finally
        {
            SfDBResource.freeConnection(conn);
        }
    }

}