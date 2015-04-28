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

import com.ttsoft.bjtax.sfgl.common.code.CodeUtils;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.util.SfTimeUtil;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.bjtax.smsb.yhsgl.web.YhsgmhzcxForm;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title:  北京地税核心征管系统－－上门申报</p>
 * <p>Description: 撤销印花税购买情况汇总 后台处理</p>
 * @author 开发六组－－诸光林
 * @version 1.1
 */
public class YhsgmhzcxProcessor
    implements Processor
{

    /**
     * 默认构造函数
     */
    public YhsgmhzcxProcessor ()
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
        YhsgmhzcxForm form = (YhsgmhzcxForm) vo.getData();
        String cxhzdx = form.getCxhzdx();
        String jsjdm = "";
        String yhsxsry = form.getYhsxsry();
        Connection conn = null;

        try
        {
            ArrayList jksList = new ArrayList();

            conn = SfDBResource.getConnection();
            SfDBAccess da = new SfDBAccess(conn);
            UserData userData = vo.getUserData();
            String strSsdwdm = userData.getSsdwdm();
            String strDsJsjdm = getDsjsjdmByUserData(strSsdwdm);
            if (strDsJsjdm == null || strDsJsjdm == "")
            {
                throw new ApplicationException("未得到相应的代售计算机代码！");
            }
            else
            {
                form.setDsjsjdm(strDsJsjdm);
                jsjdm = strDsJsjdm;
            }

            StringBuffer sqlBuffer = new StringBuffer();
            sqlBuffer.append(
                "select distinct t1.JKPZH, t1.SJJE, t1.SBBH")
                .append(" from sbdb.sb_jl_sbjkzb t1, sbdb.sb_jl_yhsgmz t2")
                .append(
                " where t1.JKPZH=t2.JKPZH and t1.FSDM='1' and t1.SJLY='15'")
                .append(" and t1.qxdm='" + InterfaceDj.getQxdm(vo.getUserData()) +
                        "' ")
                .append(" and t1.zwbs like  '" + CodeConstant.SMSB_ZWBS + "%" +
                        CodeConstant.SMSB_ZWBS + "' ")
                .append(" and t1.JSJDM='").append(jsjdm).append("' ")
                .append(" and t2.qxdm='" + InterfaceDj.getQxdm(vo.getUserData()) +
                        "' ")
                .append(" and t2.SJLY='0' and t2.jzbs='0'");

            if (cxhzdx.equals("0"))
            { //按单位汇总
                sqlBuffer.append(" and t2.HZFS='0'");
            }
            else
            { //按销售人汇总
                sqlBuffer.append(" and t2.HZFS='1' and t2.ZHDM='")
                    .append(yhsxsry).append("'");
            } //end if

            String sqlString = sqlBuffer.toString();
            ResultSet rs = da.querySQL(sqlString);
            while (rs.next())
            {
                HashMap map = new HashMap();
                map.put("jkpzh", rs.getString("jkpzh"));
                map.put("sjje", rs.getString("sjje"));
                map.put("sbbh",rs.getString("sbbh"));
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
        UserData us = vo.getUserData();
        YhsgmhzcxForm form = (YhsgmhzcxForm) vo.getData();
        String cxsbbh = form.getCxsbbh();
        String strSsdwdm = us.getSsdwdm(); //userData所属的税务机关组织机构代码
        String jsjdm = getDsjsjdmByUserData(strSsdwdm);
        Connection conn = null;

         //创建删除日志 20040429 Shi Yanfeng
        TinyTools.makeLog4YhsZfjks(vo.getUserData(), cxsbbh);
        try
        {
            conn = SfDBResource.getConnection();
            SfDBAccess da = new SfDBAccess(conn);

            //验证此缴款书还未入库
            StringBuffer checkSql = new StringBuffer();
            checkSql.append(
                "select sum(substr(t1.zwbs,1,1) || substr(t1.zwbs,20,1)) Count from sbdb.sb_jl_sbjkzb t1, sbdb.sb_jl_yhsgmz t2 where t1.FSDM='1' and t1.SJLY='15'")
                .append(" and t1.JSJDM='" + jsjdm
                        + "' ")
                .append(" and t1.SWJGZZJGDM='"
                        + us.getSsdwdm() +
                        "' ")
                .append(" and t1.SBBH='").append(cxsbbh).append(
                "' and t2.JZBS='0' and t1.JKPZH=t2.JKPZH");
            ResultSet rs = da.querySQL(checkSql.toString());

            if (rs.next())
            {
                if (rs.getInt("Count") <= 0) { //若此缴款书可以撤销
                    String sbjkzSql =
                            "SELECT JKPZH FROM SBDB.SB_JL_SBJKZB t WHERE t.SBBH='" +
                            cxsbbh + "' and t.JSJDM='"+jsjdm+"'";
                    System.out.println("sbjkzSql = " + sbjkzSql);
                    ResultSet sbzbrs = da.querySQL(sbjkzSql);
                    while (sbzbrs.next()) {
                        //恢复印花税购买记录到未汇总状态
                        StringBuffer yhsSql = new StringBuffer();
                        String timeNow = SfTimeUtil.getNowTimestamp().toString();
                        timeNow = timeNow.substring(0, timeNow.length() - 4);
                        yhsSql.append("UPDATE SBDB.SB_JL_YHSGMZ")
                                .append(
                                        " SET JKPZH='', HZRQ='', HZR='', YHZBS='0', HZFS=''")
                                .append(", LRRQ=TO_DATE('")
                                .append(timeNow)
                                .append("','yyyy-mm-dd hh24:mi:ss')")
                                .append(" WHERE JKPZH='").append(sbzbrs.
                                getString("jkpzh")).append(
                                        "'");
                        try {
                            PreparedStatement sqlStatement = conn.
                                    prepareStatement(
                                            yhsSql.
                                            toString());
                            sqlStatement.execute();
                        } catch (Exception ex10) {
                            throw new ApplicationException("恢复印花税购买记录到未汇总状态出错！");
                        } //end try
                    }
                    sbzbrs.close();
                    //删除申报缴款明细表数据
                    String jkmxSql =
                        "DELETE FROM SBDB.SB_JL_SBJKMX WHERE SBBH='"+
                        cxsbbh+"' AND JSJDM='"+jsjdm+"'";
                    try
                    {
                        PreparedStatement sqlStatement = conn.prepareStatement(
                            jkmxSql);
                        sqlStatement.execute();
                    }
                    catch (Exception ex8)
                    {
                        throw new ApplicationException("删除申报缴款明细表数据出错！");
                    } //end try

                    //删除申报缴款主表数据
                    String jkzbSql =
                            "DELETE FROM SBDB.SB_JL_SBJKZB WHERE SBBH='" +
                            cxsbbh + "' AND JSJDM='"+jsjdm+"'";
                    try
                    {
                        PreparedStatement sqlStatement = conn.prepareStatement(
                            jkzbSql.
                            toString());
                        sqlStatement.execute();
                    }
                    catch (Exception ex8)
                    {
                        ex8.printStackTrace();
                        throw new ApplicationException("删除申报缴款主表数据出错！");
                    } //end try
                }
                else
                {
                    throw new ApplicationException("申报表号 " + cxsbbh + " 不可撤销！");
                }

            } //end if
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

    /**
     * 根据UserData得到代售单位计算机代码
     * @param strSsdwdm 所属税务单位代码
     * @return 代售单位计算机代码
     */
    private String getDsjsjdmByUserData (String strSsdwdm)
    {
        String dsJsjdm = "";
        dsJsjdm = CodeUtils.getCodeMapValue("ZHSB_SWJGZZJG",
                                            "swjgzzjgdm", strSsdwdm, "jsjdm");
        return dsJsjdm;
    }

}
