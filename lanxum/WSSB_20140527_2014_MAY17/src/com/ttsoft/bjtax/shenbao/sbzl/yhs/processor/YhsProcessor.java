package com.ttsoft.bjtax.shenbao.sbzl.yhs.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.ekernel.db.or.ORContext;
import com.ekernel.db.or.ORManager;
import com.syax.bjtax.ca.util.DzyjHelper;
import com.syax.bjtax.ca.vo.DzyjsjVO;
import com.syax.common.util.CAcodeConstants;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.shenbao.constant.CodeConstant;
import com.ttsoft.bjtax.shenbao.model.domain.Szsm;
import com.ttsoft.bjtax.shenbao.model.domain.Yhsbgmx;
import com.ttsoft.bjtax.shenbao.model.domain.Yhsbgz;
import com.ttsoft.bjtax.shenbao.sbzl.yhs.YhsActionConstant;
import com.ttsoft.bjtax.shenbao.sbzl.yhs.YhsMapConstant;
import com.ttsoft.bjtax.shenbao.sbzl.yhs.xmlvo.YhsbgmxVO;
import com.ttsoft.bjtax.shenbao.util.CAUtils;
import com.ttsoft.bjtax.shenbao.util.DBResource;
import com.ttsoft.bjtax.shenbao.util.Skssrq;
import com.ttsoft.bjtax.shenbao.util.TinyTools;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.exception.SystemException;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * @author zhiyong He
 * @version 1.0 印花税Processor
 */
public class YhsProcessor implements Processor
{
    /**
     * SessionID常量
     */
    private final long SESSIONID = 0;

    /**
     * 前台传来的用户信息的数据对象
     */
    private UserData userData = null;

    public YhsProcessor()
    {
    }

    /**
     * 根据业务操作类型值来做业务操作
     * 
     * @param voPackage
     *            VOPackage
     * @return Object
     * @throws BaseException
     */
    public Object process(VOPackage voPackage) throws BaseException
    {
        this.userData = voPackage.getUserData();
        // 根据业务操作类型值来做业务操作
        try
        {
            switch (voPackage.getAction())
            {
                // 数据查询
                case YhsActionConstant.INT_ACTION_QUERY:
                    voPackage.setData(doQuery((Map) voPackage.getData()));
                    break;

                // 保存
                case YhsActionConstant.INT_ACTION_SAVE:
                    voPackage.setData(doSave(voPackage));
                    break;

                // 删除
                case YhsActionConstant.INT_ACTION_DELETE:
                    voPackage.setData(doDelete(voPackage, "delete"));
                    break;

                // 如果没有对应Action，抛出SystemException
                default:
                    throw new SystemException("no such mothod");
            }
        }
        catch (Exception e)
        {
            throw ExceptionUtil.getBaseException(e);
        }
        // 数据返回
        return voPackage;
    }

    /**
     * 保存印花税报告数据
     * 
     * @param data
     *            Map
     * @return Object
     * @throws BaseException
     */
    private Map doSave(VOPackage vo) throws BaseException
    {
        Map data = (Map) vo.getData();
        DzyjsjVO dzyj = saveSignData(vo);
        data.remove(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);
        data.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);

        // 数据库连接
        Connection conn = null;
        // OR实列
        ORManager orManager = null;

        try
        {
            // 取得印花税报告主表值对象
            Yhsbgz yhsbgz = (Yhsbgz) data.get(YhsMapConstant.YHSBGZ);
            // 设置申报日期
            yhsbgz.setSbrq(TinyTools.second2Day(new Timestamp(System.currentTimeMillis())));

            // 取得印花税报告明细表值对象List
            List YhsbgmxList = (List) data.get(YhsMapConstant.LIST_YHSBGMX);

            // 删除印花税报告数据
            this.doDelete(vo, "save");

            conn = DBResource.getConnection();
            orManager = DBResource.getORManager();

            // 保存印花税报告主表数据
            orManager.makePersistent(this.SESSIONID, conn, yhsbgz);

            // 循环保存印花税报告明细数据
            for (int i = 0; i < YhsbgmxList.size(); i++)
            {
                orManager.makePersistent(this.SESSIONID, conn, YhsbgmxList.get(i));
            }
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "印花税年度报告表保存失败");
        }
        finally
        {
            DBResource.destroyConnection(conn);
        }
        return data;
    }

    /**
     * 取得登记数据、税种税目代码数据、印花税年度已申报数据 1.根据计算机代码从登记接口查出登记信息 2.从税种税目代码表取出税目、税率（单位税额）
     * 3.根据计算机代码和当前年月检索是否已存在此纳税人的印花税年度纳税申报资料 4.返回查询结果
     * 
     * @param data
     *            Map
     * @return Map
     * @throws com.ttsoft.framework.exception.BaseException
     */
    private Map doQuery(Map data) throws BaseException
    {
        Connection conn = null;
        ORManager orManager = null;

        // 返回数据Map
        Map retMap = null;
        try
        {
            // 取得计算机代码
            String jsjdm = (String) data.get(YhsMapConstant.JSJDM);
            // 取得税款所属日期年度
            String nd = (String) ((Map) Skssrq.yearSkssrq(new Date())).get(Skssrq.SKSSRQ_ND);
            // Timestamp curTime = new Timestamp(System.currentTimeMillis());
            // String nd = new SimpleDateFormat("yyyy").format(curTime);
            // System.out.println(nd + "nnnnnnnnnnnnn");
            // 登记数据Map
            Map djsjMap = (Map) data.get(YhsMapConstant.MAP_DJSJ);

            // 取得登记基本数据
            SWDJJBSJ djInfo = (SWDJJBSJ) djsjMap.get(YhsMapConstant.JBSJ);
            conn = DBResource.getConnection();
            orManager = DBResource.getORManager();

            // 取得印花税税种税目数据
            List yhsSzsmList = this.getYhsSzsm(conn, orManager);
            // 查询印花税报告主表数据
            Yhsbgz yhsbgz = this.getYhsbzsj(conn, orManager, jsjdm, nd, djInfo);

            // 印花税报告明细数据
            List yhsbgmxList = null;
            // 判断是否申报过印花税
            String ifSB = "wsb";
            // 如果没有申报过的印花税报告主表数据
            if (yhsbgz == null)
            {
                // 生成新的印花税报告主表数据
                yhsbgz = this.createNewYhsbgz(djInfo, nd);
                // 生成新的印花税报告明细数据
                yhsbgmxList = this.createNewYhsgm(jsjdm, nd, yhsSzsmList);
            }
            else
            {
                ifSB = "ysb";
                // 从数据库中查找印花税报告明细数据
                yhsbgmxList = this.getYhsbgmx(conn, orManager, jsjdm, nd, djInfo);
            }
            // 构造返回数据Map
            retMap = new HashMap();
            retMap.put(YhsMapConstant.YHSBGZ, yhsbgz);
            retMap.put(YhsMapConstant.LIST_YHSBGMX, yhsbgmxList);
            retMap.put(YhsMapConstant.JBSJ, djInfo);
            retMap.put(YhsMapConstant.LIST_SZSM, yhsSzsmList);
            retMap.put(YhsMapConstant.IFSB, ifSB);
            return retMap;
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "系统错误，请与管理员联系");
        }
        finally
        {
            // 关闭数据库连接
            DBResource.destroyConnection(conn);
        }
    }

    /**
     * 删除印花税报告数据
     * 
     * @param data
     *            Map
     * @return Object
     * @throws BaseException
     */
    private Map doDelete(VOPackage vo, String aType) throws BaseException
    {
        Map data = (Map) vo.getData();
        Connection conn = null;
        ORManager orManager = null;
        DzyjsjVO dzyj = null;
        if (aType.equals("delete"))
        {
            dzyj = saveSignData(vo);
            data.remove(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);
            data.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
        }
        try
        {
            conn = DBResource.getConnection();
            orManager = DBResource.getORManager();

            // 取得印花税报告主表值对象
            SWDJJBSJ jbsj = (SWDJJBSJ) data.get(YhsMapConstant.JBSJ);

            String nd = String.valueOf(Calendar.getInstance().get(Calendar.YEAR) - 1);
            // /System.out.println(nd + "ddddddddddddddddddd");
            // Timestamp curTime = new Timestamp(System.currentTimeMillis());
            // String nd = new SimpleDateFormat("yyyy").format(curTime);
            // 查询印花税报告主表数据
            Yhsbgz yhsbgz = this.getYhsbzsj(conn, orManager, jbsj.getJsjdm(), nd, jbsj);

            if (yhsbgz == null)
            {
                // 生成新的印花税报告主表数据
                return null;
            }

            // 删除印花税报告明细数据
            // 构造删除SQL和删除条件
            StringBuffer sqlBuffer = new StringBuffer();
            sqlBuffer.append("delete ");
            sqlBuffer.append("from sbdb.sb_jl_yhsbgmx");
            sqlBuffer.append(" where jsjdm = ? ");
            sqlBuffer.append("and nd = ? ");
            sqlBuffer.append("and qxdm = ? ");
            String sql = sqlBuffer.toString();

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, jbsj.getJsjdm());
            pstmt.setString(2, nd);
            pstmt.setString(3, jbsj.getSwjgzzjgdm().substring(0, 2));

            // 删除印花税年度报告数据
            pstmt.execute();

            // 删除印花税报告主表数据
            sqlBuffer = new StringBuffer();
            sqlBuffer.append("delete ");
            sqlBuffer.append("from sbdb.sb_jl_yhsbgz");
            sqlBuffer.append(" where jsjdm = ? ");
            sqlBuffer.append("and nd = ? ");
            sqlBuffer.append("and fsdm = ? ");
            sqlBuffer.append("and qxdm = ? ");
            sql = sqlBuffer.toString();

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, jbsj.getJsjdm());
            pstmt.setString(2, nd);
            pstmt.setString(3, CodeConstant.FSDM_WSSB);
            pstmt.setString(4, jbsj.getSwjgzzjgdm().substring(0, 2));

            // 删除印花税年度报告数据
            pstmt.execute();
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
        finally
        {
            DBResource.destroyConnection(conn);
        }
        return data;
    }

    /**
     * 取得印花税的税种税目
     * 
     * @param conn
     *            Connection
     * @param orManager
     *            ORManager
     * @return List
     * @throws BaseException
     */
    private List getYhsSzsm(Connection conn, ORManager orManager) throws BaseException
    {
        // 印花税税种税目List
        List yhsSzsmList = null;
        try
        {
            // 构造查询条件
            Vector v = new Vector();

            v.add("SZSMDM LIKE '16__%' ");
            v.add("ZXBS = '0'");
            v.add("SZSMDM <> '160091' ");
            v.add("SZSMDM <> '160092'" + " ORDER BY SZSMDM");

            ORContext szsmContext = new ORContext(Szsm.class.getName(), v);
            yhsSzsmList = orManager.query(SESSIONID, conn, szsmContext);

            return yhsSzsmList;
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    /**
     * 取印花税报告主表数据
     * 
     * @param conn
     *            Connection
     * @param orManager
     *            ORManager
     * @param jsjdm
     *            String
     * @param nd
     *            String
     * @return Yhsbgz
     * @throws BaseException
     */
    private Yhsbgz getYhsbzsj(Connection conn, ORManager orManager, String jsjdm, String nd, SWDJJBSJ jbsj)
            throws BaseException
    {
        try
        {
            // 印花税报告主表值对象List
            List yhsbgzList = null;

            // 构造查询条件
            Vector v = new Vector();
            v.add("JSJDM = '" + jsjdm + "' ");
            v.add("ND = '" + nd + "' ");
            v.add("FSDM = '" + CodeConstant.FSDM_WSSB + "'");
            v.add("QXDM = '" + jbsj.getSwjgzzjgdm().substring(0, 2) + "'");

            ORContext yhsbgzContext = new ORContext(Yhsbgz.class.getName(), v);
            yhsbgzList = orManager.query(this.SESSIONID, conn, yhsbgzContext);
            if (yhsbgzList == null || yhsbgzList.size() == 0)
            {
                // 如果取不到数据，则返回null
                return null;
            }
            // 返回数据
            return (Yhsbgz) yhsbgzList.get(0);
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    /**
     * 取印花税报告明细数据
     * 
     * @param conn
     *            Connection
     * @param orManager
     *            ORManager
     * @param jsjdm
     *            String
     * @param nd
     *            String
     * @return List
     * @throws BaseException
     */
    private List getYhsbgmx(Connection conn, ORManager orManager, String jsjdm, String nd, SWDJJBSJ jbsj)
            throws BaseException
    {
        try
        {
            // 印花税报告明细表值对象List
            List yhsbgmxList = null;

            // 构造查询条件
            Vector v = new Vector();
            v.add("JSJDM = '" + jsjdm + "'");
            v.add("QXDM = '" + jbsj.getSwjgzzjgdm().substring(0, 2) + "'");
            v.add("ND = '" + nd + "'  ORDER BY SZSMDM");

            ORContext yhsbgmxContext = new ORContext(Yhsbgmx.class.getName(), v);
            // 利用ORMapping查询印花税报告明细表
            yhsbgmxList = orManager.query(this.SESSIONID, conn, yhsbgmxContext);
            if (yhsbgmxList == null || yhsbgmxList.size() == 0)
            {
                // 如果取不到数据，抛出异常
                throw new SystemException("有主表数据，没有明细数据！");
            }
            // 返回印花税报告明细表值对象List
            return yhsbgmxList;
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    /**
     * 生成新的印花税年报主表
     * 
     * @param djInfo
     *            SWDJJBSJ
     * @param nd
     *            String
     * @return Yhsbgz
     */
    private Yhsbgz createNewYhsbgz(SWDJJBSJ djInfo, String nd)
    {
        Yhsbgz yhsbgz = new Yhsbgz();
        // 计算税款所属日期
        Map skssrqMap = Skssrq.yearSkssrq(new Date());
        // 设置印花税报告主表值对象得熟悉
        yhsbgz.setJsjdm(djInfo.getJsjdm());
        yhsbgz.setNd(nd);
        yhsbgz.setFsdm(CodeConstant.FSDM_WSSB);
        yhsbgz.setLrr(this.userData.yhid);
        yhsbgz.setSkssjsrq((Timestamp) skssrqMap.get(Skssrq.SKSSJSRQ));
        yhsbgz.setSkssksrq((Timestamp) skssrqMap.get(Skssrq.SKSSKSRQ));
        yhsbgz.setSwjgzzjgdm(djInfo.getSwjgzzjgdm());
        yhsbgz.setHjfs(new BigDecimal(0));
        yhsbgz.setHjjsje(new BigDecimal(0.00));
        yhsbgz.setHjynse(new BigDecimal(0.00));
        // 返回印花税报告主表值对象
        return yhsbgz;
    }

    /**
     * 生成印花税报告明细数据List
     * 
     * @param jsjdm
     *            String
     * @param nd
     *            String
     * @param yhsSzsmInfo
     *            List
     * @return List
     */
    private List createNewYhsgm(String jsjdm, String nd, List yhsSzsmInfo)
    {
        List yhsbgmxList = new ArrayList();
        // 生成初始值
        BigDecimal defaultValue = new BigDecimal("0");
        // 根据印花税税种税目得大小，生成印花税报告明细数据，并设置属性
        for (int i = 0; i < yhsSzsmInfo.size(); i++)
        {
            Yhsbgmx yhsbgmx = new Yhsbgmx();
            yhsbgmx.setJsjdm(jsjdm);
            yhsbgmx.setNd(nd);
            yhsbgmx.setSzsmdm(((Szsm) yhsSzsmInfo.get(i)).getSzsmdm());
            yhsbgmx.setFs(defaultValue);
            yhsbgmx.setJsje(defaultValue);
            yhsbgmx.setSl(((Szsm) yhsSzsmInfo.get(i)).getSl());
            yhsbgmx.setSjse(defaultValue);
            // 添加到印花税报告明细值对象List
            yhsbgmxList.add(yhsbgmx);
        }
        // 返回印花税报告明细值对象List
        return yhsbgmxList;
    }

    private DzyjsjVO saveSignData(VOPackage vo) throws BaseException
    {
        Map pData = (Map) vo.getData();
        YhsbgmxVO yhs = (YhsbgmxVO) pData.get(CAcodeConstants.CA_MAPKEY_VO_XMLVO);
        UserData ud = vo.getUserData();
        DzyjsjVO dzyj = null;
        dzyj = (DzyjsjVO) pData.get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);

        try
        {
            dzyj = CAUtils.saveDzyjsj(ud, dzyj,yhs.getSbxx().getNd(), "", yhs.getSbxx().getSkssksrq(), "");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
        return dzyj;
    }
    /**
     * 删除印花税报告主表数据
     * 
     * @param conn
     *            Connection
     * @param orManager
     *            ORManager
     * @param yhsbgz
     *            Yhsbgz
     * @throws BaseException
     */
    // private void deleteYhsbgz(Connection conn, ORManager orManager, Yhsbgz
    // yhsbgz)
    // throws BaseException
    // {
    // try
    // {
    // // 删除印花税报告主表数据
    // orManager.deleteObject(this.SESSIONID, conn, yhsbgz);
    // }
    // catch(Exception ex)
    // {
    // throw ExceptionUtil.getBaseException(ex, "删除印花税报告主表数据错误");
    // }
    // }
    /**
     * 删除印花税报告明细数据
     * 
     * @param conn
     *            Connection
     * @param jsjdm
     *            String
     * @param nd
     *            String
     * @throws BaseException
     */
    // private void deleteYhsbgmx(Connection conn, String jsjdm, String nd)
    // throws BaseException
    // {
    // try
    // {
    // // 构造删除SQL和删除条件
    // StringBuffer sqlBuffer = new StringBuffer();
    // sqlBuffer.append("delete ");
    // sqlBuffer.append("from sbdb.sb_jl_yhsbgmx");
    // sqlBuffer.append(" where jsjdm = ? ");
    // sqlBuffer.append("and nd = ? ");
    // String sql = sqlBuffer.toString();
    //
    // PreparedStatement pstmt = conn.prepareStatement(sql);
    // pstmt.setString(1, jsjdm);
    // pstmt.setString(2, nd);
    // // 删除印花税年度报告数据
    // pstmt.execute();
    //
    // }
    // catch(Exception ex)
    // {
    // throw ExceptionUtil.getBaseException(ex, "删除印花税报告明细数据错误");
    // }
    // }
}