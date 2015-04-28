/*
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */
package com.ttsoft.bjtax.smsb.yhsgl.processor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBUtils;
import com.ttsoft.bjtax.sfgl.common.util.SfHashList;
import com.ttsoft.bjtax.sfgl.common.util.SfStringUtils;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.bjtax.smsb.yhsgl.web.YhsgmlrcxForm;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Description: 录入印花税购买情况撤销 后台处理</p>
 * @author 开发六组－－诸光林
 * @version 1.1
 */

public class YhsgmlrcxProcessor
    implements Processor
{
    /**
     * 默认构造函数
     */
    public YhsgmlrcxProcessor ()
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
            case CodeConstant.SMSB_DELETEACTION:
                result = doDelete(vo);
                break;
            case CodeConstant.SMSB_DELETEALLACTION:
                result = doDeleteAll(vo);
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

        yForm.setLength(CodeConstant.GZ_PG_LENGTH); //设置页长

        StringBuffer sqlBuffer = new StringBuffer();
        sqlBuffer.append(
            "select distinct y1.xspzh,y1.dsjsjdm,y1.nd,y1.lrr,y1.hjje,")
            .append(" to_char(y1.cjrq,'yyyy-mm-dd hh24:mi:ss') cjrq1,y2.jsjdm||y2.zjhm dwdm,y2.dwmc ")
            .append(" from sbdb.sb_jl_yhsgmz y1,sbdb.sb_jl_yhsgmmx y2 where ")
            .append(
            " y1.xspzh=y2.xspzh and y1.dsjsjdm=y2.dsjsjdm and y1.jzbs='0'");
        sqlBuffer.append(getQueryCondition(yForm, vo.getUserData()));
        sqlBuffer.append(" order by cjrq1 ");

        String sql = new String();
        sql = sqlBuffer.toString();
        SfHashList sfHashList = null;
        try
        {
            conn = SfDBResource.getConnection();
            SfDBUtils sfDB = new SfDBUtils(conn);
            sfHashList = sfDB.getData(sql, yForm.getLength(), yForm.getPgNum());
            List recordList = sfHashList.getRecords();

            //若删除最后一页所有记录，则退到上一页，即当前最后一页
            if (yForm.getPgNum() > 1 && recordList.size() < 1)
            {
                yForm.setPgNum(yForm.getPgNum() - 1);
                sfHashList = sfDB.getData(sql, yForm.getLength(),
                                          yForm.getPgNum());
                recordList = sfHashList.getRecords();
            }
            //进行一些必要的格式转换
            if (recordList.size() > 0)
            {
                for (int i = 0; i < recordList.size(); i++)
                {
                    Map map = (Map) recordList.get(i);
                    String checkboxId = "xspzh:" + map.get("xspzh") + "|"
                                        + "dsjsjdm:" + map.get("dsjsjdm") + "|"
                                        + "nd:" + map.get("nd") + "|";
                    String viewId = "xspzh:" + map.get("xspzh") + "|"
                                    + "dsjsjdm:" + map.get("dsjsjdm") + "|"
                                    + "nd:" + map.get("nd") + "|"
                                    + "cjrq:" + map.get("cjrq1") + "|"
                                    + "hjje:" + map.get("hjje") + "|"
                                    + "dwdm:" + map.get("dwdm") + "|"
                                    + "dwmc:" + map.get("dwmc") + "|";

                    map.put("checkboxId", checkboxId);
                    map.put("viewId", viewId);
                }
                yForm.setDataList(recordList);
                //确定最大页数
                yForm.setPgSum(sfDB.getMaxResultNum() % yForm.getLength() == 0 ?
                               sfDB.getMaxResultNum() / yForm.getLength() :
                               sfDB.getMaxResultNum() / yForm.getLength() + 1);

            }
            else
            {
                yForm.setDataList(new ArrayList());
                yForm.setPgNum(0);
                yForm.setPgSum(0);
                if (!yForm.isFromDelete())
                {
                    throw new ApplicationException("没有找到指定的记录！");
                }
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

    /**
     * 删除处理
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return Form
     * @throws BaseException
     */
    private Object doDelete (VOPackage vo)
        throws BaseException
    {
        Connection conn = null;
        YhsgmlrcxForm yForm = (YhsgmlrcxForm) vo.getData();
        String[] deleteCheckbox = yForm.getDeleteCheckbox();
        try
        {
            conn = SfDBResource.getConnection();
            for (int i = 0; i < deleteCheckbox.length; i++)
            {
                Map map = SfStringUtils.splitString(deleteCheckbox[i]);
                String xspzh = (String) map.get("xspzh");
                String dsjsjdm = (String) map.get("dsjsjdm");
                String nd = (String) map.get("nd");
                deleteItem(conn, vo.getUserData(), xspzh, dsjsjdm, nd);
            }

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
        finally
        {
            SfDBResource.freeConnection(conn);
        }

        return yForm;

    }

    /**
     * 删除全部后台处理
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return Form
     * @throws BaseException
     */
    private Object doDeleteAll (VOPackage vo)
        throws BaseException
    {
        Connection conn = null;
        YhsgmlrcxForm yForm = (YhsgmlrcxForm) vo.getData();
        StringBuffer sqlBuffer = new StringBuffer();
        sqlBuffer.append(
            "select distinct y1.xspzh,y1.dsjsjdm,y1.nd,y1.lrr,y1.cjrq ,y1.hjje,")
            .append(" y2.jsjdm||y2.zjhm dwdm,y2.dwmc ")
            .append(" from sbdb.sb_jl_yhsgmz y1,sbdb.sb_jl_yhsgmmx y2 where ")
            .append(
            " y1.xspzh=y2.xspzh and y1.dsjsjdm=y2.dsjsjdm and y1.jzbs='0'");
        sqlBuffer.append(getQueryCondition(yForm, vo.getUserData()));

        String sql = new String();
        sql = sqlBuffer.toString();
        try
        {
            conn = SfDBResource.getConnection();
            SfDBUtils sfDB = new SfDBUtils(conn);
            SfHashList sfHashList = sfDB.getData(sql);
            List recordList = sfHashList.getRecords();
            for (int i = 0; i < recordList.size(); i++)
            {
                Map map = (Map) recordList.get(i);
                String xspzh = (String) map.get("xspzh");
                String dsjsjdm = (String) map.get("dsjsjdm");
                String nd = (String) map.get("nd");
                deleteItem(conn, vo.getUserData(), xspzh, dsjsjdm, nd);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
        finally
        {
            SfDBResource.freeConnection(conn);
        }

        return yForm;
    }

    /**
     * 撤销指定的销售纪录
     * @param conn Connection
     * @param userData UserData
     * @param xspzh 指定撤销的销售凭证号
     * @param dsjsjdm 代售单位计算机代码
     * @param nd 所属年度
     * @throws BaseException
     */
    private void deleteItem (Connection conn, UserData userData, String xspzh,
                             String dsjsjdm, String nd)
        throws
        BaseException
    {
        SfDBUtils sfDB = new SfDBUtils(conn);
         //创建删除日志 20040429 Shi Yanfeng
        TinyTools.makeLog4YhsCxgm(userData, xspzh);

        //首先验证此销售纪录未记账
        StringBuffer checkSql = new StringBuffer();
        checkSql.append("select * from sbdb.sb_jl_yhsgmz where xspzh='")
            .append(xspzh).append("'").append(" and dsjsjdm='")
            .append(dsjsjdm).append("'").append(" and nd='")
            .append(nd).append("' and jzbs='0'");
        List checkList = sfDB.getDataList(checkSql.toString());

        //若此销售纪录不可撤销 modified by zhu guanglin
        if (checkList.size() == 0)
        {
            throw new ApplicationException("此销售纪录不可撤销，请重新查询！");
        }

        //更新印花税票证库存
        StringBuffer pzSelectSql = new StringBuffer();
        pzSelectSql.append(
            "select distinct a.XSPZH,a.SPMZDM,a.GPSL,a.JE,b.zhdm ")
            .append(" from sbdb.sb_jl_yhsgmmx a, sbdb.sb_jl_yhsgmz b")
            .append(
            " where a.xspzh=b.xspzh and a.dsjsjdm=b.dsjsjdm and a.xspzh='")
            .append(xspzh).append("'").append(" and a.dsjsjdm='")
            .append(dsjsjdm).append("'").append(" and a.nd='")
            .append(nd).append("' ");

        List pzSelectlist = sfDB.getDataList(pzSelectSql.toString());
        try
        {
            if (pzSelectlist.size() > 0)
            {
                com.ttsoft.bjtax.pzgl.proxy.ServiceProxy.yhsUpdatePzkc(
                    userData,
                    (String) ( (Map) pzSelectlist.get(0)).get("zhdm"),
                    pzSelectlist);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new ApplicationException("更新印花税票库存失败！");
        }

        StringBuffer deleteSb1 = new StringBuffer();
        StringBuffer deleteSb2 = new StringBuffer();
        deleteSb1.append("delete from sbdb.sb_jl_yhsgmmx where xspzh='")
            .append(xspzh).append("'").append(" and dsjsjdm='")
            .append(dsjsjdm).append("'").append(" and nd='")
            .append(nd).append("' ");
        deleteSb2.append("delete from sbdb.sb_jl_yhsgmz where xspzh='")
            .append(xspzh).append("'").append(" and dsjsjdm='")
            .append(dsjsjdm).append("'").append(" and nd='")
            .append(nd).append("' ");
        try
        {
            PreparedStatement sqlStatement = conn.prepareStatement(deleteSb1.
                toString());
            sqlStatement.execute();
        }
        catch (Exception ex1)
        {
            throw new ApplicationException("删除印花税购买明细表记录失败！");
        } //end try
        try
        {
            PreparedStatement sqlStatement = conn.prepareStatement(deleteSb2.
                toString());
            sqlStatement.execute();
        }
        catch (Exception ex1)
        {
            throw new ApplicationException("删除印花税购买主表记录失败！");
        } //end try

    }

    /**
     * 或的查询条件
     * @param yForm YhsgmlrcxForm
     * @param userData UserData
     * @return 查询条件串
     */
    private String getQueryCondition (YhsgmlrcxForm yForm, UserData userData)
    {
        StringBuffer sb = new StringBuffer();
        sb.append(" and y1.qxdm= '" + InterfaceDj.getQxdm(userData) + "' ");
        sb.append(" and (y1.yhzbs<>'1' or y1.yhzbs is null) ");

        //录入起始日期
        if (yForm.getLrqsrq() != null && yForm.getLrqsrq().trim().length() > 0)
        {
            sb.append(" and y1.cjrq>=to_date('" + yForm.getLrqsrq().trim() +
                      " 00:00:00','yyyy-mm-dd hh24:mi:ss')");
        }
        //录入截至日期
        if (yForm.getLrjzrq() != null && yForm.getLrjzrq().trim().length() > 0)
        {
            sb.append(" and y1.cjrq<=to_date('" + yForm.getLrjzrq().trim() +
                      " 23:59:59','yyyy-mm-dd hh24:mi:ss')");
        }
        //录入人
        if (yForm.getLrr() != null && yForm.getLrr().trim().length() > 0)
        {
            sb.append(" and y1.lrr = '" + yForm.getLrr().trim() + "' ");
        }
        //销售凭证号
        if (yForm.getXspzh() != null && yForm.getXspzh().trim().length() > 0)
        {
            sb.append(" and y1.xspzh like '%" + yForm.getXspzh().trim() + "%' ");
        }
        //合计金额
        if (yForm.getDbjehj() != null && yForm.getDbjehj().trim().length() > 0)
        {
            sb.append(" and y1.hjje = '" + yForm.getDbjehj().trim() + "' ");
        }
        if (yForm.getGhxz().equals("1"))
        {
            //购花单位时
            if (yForm.getGhdwjsjdm() != null &&
                yForm.getGhdwjsjdm().trim().length() > 0)
            {
                sb.append(" and y2.jsjdm like '%" + yForm.getGhdwjsjdm().trim()
                          + "%' ");
            }
            if (yForm.getGhdwmc() != null
                && yForm.getGhdwmc().trim().length() > 0)
            {
                sb.append(" and y2.dwmc like '%" + yForm.getGhdwmc().trim()
                          + "%' ");
            }
        }
        else
        {
            //购花个人时
            if (yForm.getZjhm() != null && yForm.getZjhm().trim().length() > 0)
            {
                sb.append(" and y2.zjhm like '%" + yForm.getZjhm().trim()
                          + "%' ");
            }
            if (yForm.getGhrmc() != null
                && yForm.getGhrmc().trim().length() > 0)
            {
                sb.append(" and y2.dwmc like '%" + yForm.getGhrmc().trim()
                          + "%' ");
            }
        }
        return sb.toString();
    }
}