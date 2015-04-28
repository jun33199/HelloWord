package com.ttsoft.bjtax.shenbao.service.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import com.ekernel.db.or.ORContext;
import com.ekernel.db.or.ORManager;
import com.ttsoft.bjtax.shenbao.model.client.JksInfor;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb;
import com.ttsoft.bjtax.shenbao.model.domain.Yhsgmmx;
import com.ttsoft.bjtax.shenbao.util.DBResource;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.bjtax.shenbao.util.StringUtils;

/**
 * <p>Title: 票证管理接口业务处理</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author Ha Zhengze
 * @version 1.0
 */

public class PzglInterFaceProcessor
    extends InterFaceProcessor {

  /**
   * 回填零散税、个体工商户申报数据的结报单号
   * 联网情况和非联网情况都使用此接口
   * @param wszhList 完税证号列表
   * @param xspzhList 销售凭证号列表
   * @param jbdh 结报单号
   * @param userData 用户信息
   * @throws BaseException 异常
   */
  public static void updatejbdhForPZ(List wszhList,
                                     List xspzhList,
                                     String jbdh,
                                     UserData userData) throws BaseException {
    if (userData == null || userData.getXtsbm1() == null ||
        userData.getXtsbm1().equals("")) {
      throw ExceptionUtil.getBaseException(
          new ApplicationException("参数错误，用户帐户代码信息不能为空！"));
    }
    if (jbdh == null || jbdh.equals("")) {
      throw ExceptionUtil.getBaseException(
          new ApplicationException("参数错误，结报单号不能为空！"));
    }
    if ( ( (wszhList == null || wszhList.size() == 0) &&
          (xspzhList == null || xspzhList.size() == 0))) {
      throw ExceptionUtil.getBaseException(
          new ApplicationException("参数错误，需要更新的数据不能都为空！"));
    }
    //定义数据库连接
    Connection conn = null;
    PreparedStatement pstmt1 = null;
    PreparedStatement pstmt2 = null;
    PreparedStatement pstmt3 = null;
    try {
      String sqlWszLs = ""; //零散税语句
      String sqlWszGt = ""; //个体工商户语句
      if (wszhList != null && wszhList.size() != 0) {
        //开始更新零散税完税证数据
        StringBuffer sqlBuffer = new StringBuffer();
        sqlBuffer.append("UPDATE SBDB.SB_JL_LSSWSZZ SET JBDH = '")
            .append(jbdh).append("' WHERE WSZH IN ('")
            .append(wszhList.get(0)).append("'");
        for (int i = 1; i < wszhList.size(); i++) {
          sqlBuffer.append(",'").append(wszhList.get(i)).append("'");
        }
        sqlBuffer.append(")");
        sqlWszLs = sqlBuffer.toString();
        sqlBuffer.setLength(0); //清空StringBuffer

        //更新个体工商户完税征主表数据
        StringBuffer strBuffer = new StringBuffer();
        strBuffer.append("UPDATE SBDB.SB_JL_GTGSHWSZZ SET JBDH = '")
            .append(jbdh).append("' WHERE WSZH IN ('")
            .append(wszhList.get(0)).append("'");
        for (int i = 1; i < wszhList.size(); i++) {
          strBuffer.append(",'").append(wszhList.get(i)).append("'");
        }
        strBuffer.append(")");
        sqlWszGt = strBuffer.toString();
        strBuffer.setLength(0); //清空StringBuffer
      }

      String strYhs = "";
      if (xspzhList != null && xspzhList.size() != 0) {
        //更新印花税主表数据
        StringBuffer sql = new StringBuffer();
        sql.append("UPDATE SBDB.SB_JL_YHSGMZ SET PKJBDH = '")
            .append(jbdh).append("' WHERE ZHDM = '")
            .append(userData.getXtsbm1()).append("' AND XSPZH IN ('")
            .append(xspzhList.get(0)).append("'");
        for (int j = 1; j < xspzhList.size(); j++) {
          sql.append(",'").append(xspzhList.get(j)).append("'");
        }
        sql.append(")");
        strYhs = sql.toString();
        sql.setLength(0); //clear
      }

      // 获得数据库连接
      conn = DBResource.getConnection(DBResource.DB_SHENBAO);
      if (wszhList != null && wszhList.size() != 0) {
        pstmt1 = conn.prepareStatement(sqlWszLs);
        pstmt2 = conn.prepareStatement(sqlWszGt);
        pstmt1.executeUpdate(); //更新完税征数据库操作－－零散税
        pstmt2.executeUpdate(); //更新完税征数据库操作－－个体工商户
      }
      if (xspzhList != null && xspzhList.size() != 0) {
        pstmt3 = conn.prepareStatement(strYhs);
        pstmt3.executeUpdate(); //更新印花税数据库操作
      }
    }
    catch (Exception ex) {
      ex.printStackTrace();
      throw ExceptionUtil.getBaseException(ex, "回填结报单号失败！");
    }
    finally {
      //释放连接
      DBResource.destroyConnection(conn);
    }
  }

  /**
   * 查询结报单号对应的所有缴款数据是否已经入库
   * 联网情况和非联网情况都使用此接口
   * @param jbdh 结报单号
   * @return 是否入库(只要有一条缴款数据没有入库就返回false),都入库返回true
   * @throws BaseException 操作异常
   */
  public static boolean cxsfrk(String jbdh) throws BaseException {
    if (jbdh == null || jbdh.trim().equals("")) {
      throw ExceptionUtil.getBaseException(new ApplicationException("结报单号不能为空！"));
    }
    //定义数据库连接
    Connection conn = null;
    try {
      //查询语句，查询缴款凭证号
      StringBuffer sqlBuffer = new StringBuffer();
      sqlBuffer.append("SELECT DISTINCT A.JKPZH FROM SBDB.SB_JL_LSWSZSBHZ A,")
          .append("SBDB.SB_JL_LSSWSZZ B WHERE A.JSJDM>='0' AND B.JSJDM>='0'")
          .append(" AND B.JBDH = '").append(jbdh)
          .append("' AND A.SBHZDH = B.SBHZDH ")
          .append("UNION ")
          .append("SELECT DISTINCT C.JKPZH FROM SBDB.SB_JL_GTGSHWSZSBHZ C,")
          .append("SBDB.SB_JL_GTGSHWSZZ D WHERE C.JSJDM>='0' AND D.JSJDM>='0'")
          .append(" AND D.JBDH = '").append(jbdh)
          .append("' AND C.SBHZDH = D.SBHZDH ")
          .append("UNION ")
          .append(
          "SELECT DISTINCT JKPZH FROM SBDB.SB_JL_YHSGMZ WHERE DSJSJDM>='0'")
          .append(" AND PKJBDH = '").append(jbdh).append("'");
      String sql = sqlBuffer.toString();
      log(sql);
      sqlBuffer.setLength(0); //clear

      ArrayList jkpzhList = new ArrayList();
      // 获得数据库连接
      conn = DBResource.getConnection(DBResource.DB_SHENBAO);
      PreparedStatement sqlStatement = conn.prepareStatement(sql);
      ResultSet rs = sqlStatement.executeQuery();
      while (rs.next()) {
        jkpzhList.add(rs.getString("jkpzh"));
      }
      if (jkpzhList == null || jkpzhList.size() == 0) {
        throw ExceptionUtil.getBaseException(new ApplicationException
                                             ("找不到此结报单号(" + jbdh + ")对应的缴款凭证号！"));
      }

      /*-------------------------------------------------------------
                查看是否所有的缴款凭证号都已经入库
       -------------------------------------------------------------*/
      return isJkpzhInStore(jkpzhList, conn, 0);
    }
    catch (Exception ex) {
      ex.printStackTrace();
      throw ExceptionUtil.getBaseException(ex, "查询入库数据失败！");
    }
    finally {
      //释放连接
      DBResource.destroyConnection(conn);
    }
  }

  /**
   * 通过完税证数组得到缴款书信息(JksInfor)
   * 非联网情况使用
   * @param wszhArray 完税证号数组
   * @return 缴款书信息(JksInfor)List(查找不到返回一个size==0的List)
   * @throws BaseException 操作异常
   * 第一步:通过完税证号查零散税主表得到对应的申报汇总单号<b>
   * 第二步:把得到的汇总单号合并(按照汇总单号)
   * 第三步:通过汇总单号得到缴款凭证号和金额数据
   */
  public static List getJkpzhJeByWszh(String[] wszhArray) throws
      BaseException {
    if (wszhArray == null || wszhArray.length < 1) {
      throw ExceptionUtil.getBaseException(new ApplicationException("参数不合法"));
    }

    Connection conn = null;
    try {
      StringBuffer sqlBuffer = new StringBuffer();
      sqlBuffer.append(
          "SELECT DISTINCT A.JKPZH,SJSE FROM SBDB.SB_JL_LSWSZSBHZ A,")
          .append("SBDB.SB_JL_LSSWSZZ B WHERE A.JSJDM>='0' AND B.JSJDM>='0'")
          .append(" AND B.WSZH IN ('").append(wszhArray[0]).append("'");
      for (int i = 1; i < wszhArray.length; i++) {
        sqlBuffer.append(",'" + wszhArray[i] + "'");
      }
      sqlBuffer.append(") AND A.SBHZDH = B.SBHZDH ")
          .append("UNION ")
          .append(
          "SELECT DISTINCT C.JKPZH,SJSE FROM SBDB.SB_JL_GTGSHWSZSBHZ C,")
          .append("SBDB.SB_JL_GTGSHWSZZ D WHERE C.JSJDM>='0' AND D.JSJDM>='0'")
          .append(" AND D.WSZH IN ('").append(wszhArray[0]).append("'");
      for (int i = 1; i < wszhArray.length; i++) {
        sqlBuffer.append(",'" + wszhArray[i] + "'");
      }
      sqlBuffer.append(") AND C.SBHZDH = D.SBHZDH");

      String sqlString = sqlBuffer.toString();
      sqlBuffer.setLength(0); //clear
      //选择不同的缴款凭证
      String sql = "SELECT DISTINCT JKPZH, SJSE FROM (" + sqlString + ")";

      String jkpzh = null; //缴款凭证号
      BigDecimal je = null; //实缴金额
      JksInfor jksInfor = null; //缴款书信息(包含实缴金额)

      ArrayList jkpzList = new ArrayList();
      // 获得数据库连接
      conn = DBResource.getConnection(DBResource.DB_SHENBAO);
      PreparedStatement sqlStatement = conn.prepareStatement(sql);
      ResultSet rs = sqlStatement.executeQuery();
      while (rs.next()) {
        jkpzh = rs.getString("jkpzh");
        je = rs.getBigDecimal("sjse");
        jksInfor = new JksInfor(jkpzh, je);
        jkpzList.add(jksInfor);
      }
      rs.close();
      sqlStatement.close();
      return jkpzList;
    }
    catch (Exception ex) {
      ex.printStackTrace();
      throw ExceptionUtil.getBaseException(ex, "查询缴款凭证数据失败！");
    }
    finally {
      //释放连接
      DBResource.destroyConnection(conn);
    }
  }



  /**
   * 通过完税证数组得到缴款书信息(JksInfor)
   * 联网情况使用,在获取一票一税缴款凭证号后再次整理为一票多税缴款书数据
   * @param wszhArray 完税证号数组
   * @return 缴款书信息(JksInfor)List(查找不到返回一个size==0的List)
   * @throws BaseException 操作异常
   * 第一步:通过完税证号查零散税主表得到对应的申报汇总单号<b>
   * 第二步:把得到的汇总单号合并(按照汇总单号)
   * 第三步:通过汇总单号得到缴款凭证号和金额数据
   */
  public static List getJkpzhJeByWszhForLw(String[] wszhArray) throws
      BaseException {
    if (wszhArray == null || wszhArray.length < 1) {
      throw ExceptionUtil.getBaseException(new ApplicationException("参数不合法"));
    }

    Connection conn = null;
    try {
      StringBuffer sqlBuffer = new StringBuffer();
      sqlBuffer.append(
          "SELECT DISTINCT A.JKPZH,SJSE FROM SBDB.SB_JL_LSWSZSBHZ A,")
          .append("SBDB.SB_JL_LSSWSZZ B WHERE A.JSJDM>='0' AND B.JSJDM>='0'")
          .append(" AND B.WSZH IN ('").append(wszhArray[0]).append("'");
      for (int i = 1; i < wszhArray.length; i++) {
        sqlBuffer.append(",'" + wszhArray[i] + "'");
      }
      sqlBuffer.append(") AND A.SBHZDH = B.SBHZDH ")
          .append("UNION ")
          .append(
          "SELECT DISTINCT C.JKPZH,SJSE FROM SBDB.SB_JL_GTGSHWSZSBHZ C,")
          .append("SBDB.SB_JL_GTGSHWSZZ D WHERE C.JSJDM>='0' AND D.JSJDM>='0'")
          .append(" AND D.WSZH IN ('").append(wszhArray[0]).append("'");
      for (int i = 1; i < wszhArray.length; i++) {
        sqlBuffer.append(",'" + wszhArray[i] + "'");
      }
      sqlBuffer.append(") AND C.SBHZDH = D.SBHZDH");

      String sqlString = sqlBuffer.toString();
      sqlBuffer.setLength(0); //clear
      //选择不同的缴款凭证
      String sql = "SELECT DISTINCT JKPZH, SJSE FROM (" + sqlString + ")";
      String jkpzh = null; //缴款凭证号
      BigDecimal je = null; //实缴金额
      JksInfor jksInfor = null; //缴款书信息(包含实缴金额)
      ArrayList jkpzList = new ArrayList();
      // 获得数据库连接
      conn = DBResource.getConnection(DBResource.DB_SHENBAO);
      log(sql);
      PreparedStatement sqlStatement = conn.prepareStatement(sql);
      ResultSet rs = sqlStatement.executeQuery();
      while (rs.next()) {
        jkpzh = rs.getString("jkpzh");
        je = rs.getBigDecimal("sjse");
        jksInfor = new JksInfor(jkpzh, je);
        jkpzList.add(jksInfor);
      }
      rs.close();
      sqlStatement.close();
      //
      List rnList=new ArrayList();
      sqlBuffer=new StringBuffer();
      sqlBuffer.append("SELECT sphm,SUM(sjje) AS sjse FROM SBDB.SB_JL_SBJKZB");
      sqlBuffer.append(" WHERE jkpzh IN (");
      for(int i=0;i<jkpzList.size();i++){
        jksInfor=(JksInfor)jkpzList.get(i);
        if(i!=0){
          sqlBuffer.append(",");
        }
        sqlBuffer.append(StringUtils.getSQLStr(jksInfor.getJkpzh()));
      }
      sqlBuffer.append(")");
      sql=sqlBuffer.toString();
      log(sql);
      sqlStatement = conn.prepareStatement(sql);
      rs = sqlStatement.executeQuery();
      while (rs.next()) {
        jkpzh = rs.getString("sphm");
        je = rs.getBigDecimal("sjse");
        jksInfor = new JksInfor(jkpzh, je);
        rnList.add(jksInfor);
      }
      rs.close();
      sqlStatement.close();
      if(rnList.size()==0){
        throw new Exception("无法找到完税证对应的税票信息!wszs="+wszhArray);
      }
      //
      return rnList;
    }
    catch (Exception ex) {
      ex.printStackTrace();
      throw ExceptionUtil.getBaseException(ex, "查询缴款凭证数据失败！");
    }
    finally {
      //释放连接
      DBResource.destroyConnection(conn);
    }
  }

  //ADD for 票证
  /**
   * 票证模块处理取印花税销售数据
   * 非联网状态
   * @param zhdm 帐户代码
   * @param jsjdm 计算机代码
   * @return 印花税税票购买明细ArrayList(returnList.get(0)=cjshMap)
   *                                  (returnList.get(1)=yhssjList)
   *                                  (returnList.get(2)=jksjList)
   * @throws BaseException
   */
  public static ArrayList getYhssj(String zhdm, String jsjdm) throws
      BaseException {
    if (zhdm == null || zhdm.equals(""))
      throw ExceptionUtil.getBaseException(
          new ApplicationException("印花税查询的帐户代码不能为空！"));
    Connection conn = null;
    ArrayList returnList = new ArrayList();
    // 创建时间Map(key=xspzh,value=cjsj)
    HashMap cjsjMap = new HashMap();
    ArrayList yhssjList = new ArrayList();
    try {
      // 得到连接
      conn = DBResource.getConnection(DBResource.DB_SHENBAO);

      StringBuffer sql = new StringBuffer();
      // 查询条件为帐户代码等于参数zhdm
      // 记帐标识为0的印花税销售数据
      sql.append(" select xspzh,gpsl,je,spmzdm from SBDB.SB_JL_YHSGMMX ")
          .append(" where xspzh in ( ")
          .append(" select DISTINCT xspzh from SBDB.SB_JL_YHSGMZ ")
          .append(" where zhdm = '").append(zhdm).append("'")
          .append(" and dsjsjdm = '").append(jsjdm).append("'")
          .append(" and jzbs = '0' ")
          .append(")")
          .append(" and dsjsjdm = '").append(jsjdm).append("'");
      Statement st = conn.createStatement();
      ResultSet rs = st.executeQuery(sql.toString());
      while (rs.next()) {
        Yhsgmmx yhsgmmx = new Yhsgmmx();
        // 销售凭证号
        yhsgmmx.setXspzh(rs.getString("xspzh"));
        // 购票数量
        yhsgmmx.setGpsl(rs.getBigDecimal("gpsl"));
        // 金额
        yhsgmmx.setJe(rs.getBigDecimal("je"));
        // 票证种类代码（在申报的表中叫税票面值代码）
        yhsgmmx.setSpmzdm(rs.getString("spmzdm"));
        yhssjList.add(yhsgmmx);
      }
      // 清空sql语句
      sql.delete(0, sql.length());
      // 记帐标识为0的销售凭证号和创建时间
      sql.append(" select xspzh, cjrq from SBDB.SB_JL_YHSGMZ ")
          .append(" where zhdm = '").append(zhdm).append("'")
          .append(" and jzbs = '0' ")
          .append(" and dsjsjdm = '").append(jsjdm).append("'");
      rs = st.executeQuery(sql.toString());
      while (rs.next()) {
        cjsjMap.put(rs.getString("xspzh"), rs.getTimestamp("cjrq"));
      }

      //查询对应缴款凭证的数据
      // 清空sql语句
      sql.delete(0, sql.length());
      //定义list
      ArrayList jkData = new ArrayList();
      // 记帐标识为0的销售凭证号和创建时间
      sql.append(" select jkpzh,rkje from sbdb.sb_jl_sbjkzb ")
          .append(
          " where jkpzh in (select distinct jkpzh from SBDB.SB_JL_YHSGMZ ")
          .append(" where zhdm = '").append(zhdm).append("'")
          .append(" and jzbs = '0' ")
          .append(" and dsjsjdm = '").append(jsjdm).append("')");
      rs = st.executeQuery(sql.toString());
      while (rs.next()) {
        Sbjkzb zb = new Sbjkzb();
        zb.setJkpzh(rs.getString("jkpzh"));
        zb.setRkje(rs.getBigDecimal("rkje"));
        zb.setSjje(rs.getBigDecimal("rkje"));
        jkData.add(zb);
      }
      // 清空sql语句
      sql.delete(0, sql.length());

      returnList.add(cjsjMap); //销售明细数据
      returnList.add(yhssjList); //创建日期及销售凭证号
      returnList.add(jkData); //缴款凭证数据

      return returnList; //返回结果数据
    }
    catch (Exception e) {
      e.printStackTrace();
      throw ExceptionUtil.getBaseException(e, "取印花税销售数据失败！");
    }
    finally {
      //释放连接
      DBResource.destroyConnection(conn);
    }
  }


  /**
   * 票证模块处理取印花税销售数据
   * 联网状态
   * @param zhdm 帐户代码
   * @param jsjdm 计算机代码
   * @return 印花税税票购买明细ArrayList(returnList.get(0)=cjshMap)
   *                                  (returnList.get(1)=yhssjList)
   *                                  (returnList.get(2)=jksjList)
   * @throws BaseException
   */
  public static ArrayList getYhssjForLw(String zhdm, String jsjdm) throws
      BaseException {
    if (zhdm == null || zhdm.equals(""))
      throw ExceptionUtil.getBaseException(
          new ApplicationException("印花税查询的帐户代码不能为空！"));
    Connection conn = null;
    ArrayList returnList = new ArrayList();
    // 创建时间Map(key=xspzh,value=cjsj)
    HashMap cjsjMap = new HashMap();
    ArrayList yhssjList = new ArrayList();
    try {
      // 得到连接
      conn = DBResource.getConnection(DBResource.DB_SHENBAO);

      StringBuffer sql = new StringBuffer();
      // 查询条件为帐户代码等于参数zhdm
      // 记帐标识为0的印花税销售数据
      sql.append(" select xspzh,gpsl,je,spmzdm from SBDB.SB_JL_YHSGMMX ")
          .append(" where xspzh in ( ")
          .append(" select DISTINCT xspzh from SBDB.SB_JL_YHSGMZ ")
          .append(" where zhdm = '").append(zhdm).append("'")
          .append(" and dsjsjdm = '").append(jsjdm).append("'")
          .append(" and jzbs = '0' ")
          .append(")")
          .append(" and dsjsjdm = '").append(jsjdm).append("'");
      Statement st = conn.createStatement();
      ResultSet rs = st.executeQuery(sql.toString());
      while (rs.next()) {
        Yhsgmmx yhsgmmx = new Yhsgmmx();
        // 销售凭证号
        yhsgmmx.setXspzh(rs.getString("xspzh"));
        // 购票数量
        yhsgmmx.setGpsl(rs.getBigDecimal("gpsl"));
        // 金额
        yhsgmmx.setJe(rs.getBigDecimal("je"));
        // 票证种类代码（在申报的表中叫税票面值代码）
        yhsgmmx.setSpmzdm(rs.getString("spmzdm"));
        yhssjList.add(yhsgmmx);
      }
      // 清空sql语句
      sql.delete(0, sql.length());
      // 记帐标识为0的销售凭证号和创建时间
      sql.append(" select xspzh, cjrq from SBDB.SB_JL_YHSGMZ ")
          .append(" where zhdm = '").append(zhdm).append("'")
          .append(" and jzbs = '0' ")
          .append(" and dsjsjdm = '").append(jsjdm).append("'");
      rs = st.executeQuery(sql.toString());
      while (rs.next()) {
        cjsjMap.put(rs.getString("xspzh"), rs.getTimestamp("cjrq"));
      }

      //查询对应缴款凭证的数据
      // 清空sql语句
      sql.delete(0, sql.length());
      //定义list
      ArrayList jkData = new ArrayList();
      // 记帐标识为0的销售凭证号和创建时间
      sql.append(" SELECT sphm,SUM(rkje) AS rkje FROM SBDB.sb_jl_sbjkzb ")
          .append(
          " where jkpzh in (SELECT distinct jkpzh FROM SBDB.SB_JL_YHSGMZ ")
          .append(" where zhdm = '").append(zhdm).append("'")
          .append(" and jzbs = '0' ")
          .append(" and dsjsjdm = '").append(jsjdm).append("')")
          .append(" GROUP BY SPHM");
      rs = st.executeQuery(sql.toString());
      while (rs.next()) {
        Sbjkzb zb = new Sbjkzb();
        zb.setJkpzh(rs.getString("sphm"));
        zb.setRkje(rs.getBigDecimal("rkje"));
        zb.setSjje(rs.getBigDecimal("rkje"));
        jkData.add(zb);
      }
      // 清空sql语句
      sql.delete(0, sql.length());

      returnList.add(cjsjMap); //销售明细数据
      returnList.add(yhssjList); //创建日期及销售凭证号
      returnList.add(jkData); //缴款凭证数据

      return returnList; //返回结果数据
    }
    catch (Exception e) {
      e.printStackTrace();
      throw ExceptionUtil.getBaseException(e, "取印花税销售数据失败！");
    }
    finally {
      //释放连接
      DBResource.destroyConnection(conn);
    }
  }

  /**
   * 票证模块处理印花税销售数据修改记帐标识
   * 将已经记录在票证帐中的印花税销售记录的记帐标识由0改为1
   * @param xspzhList 要修改的销售凭证号List
   * @param jsjdm 计算机代码
   * @param lx 更新类型 整型 0：更新为未记帐；1：更新为已记帐
   * @throws BaseException
   */
  public static void updateJzbs(ArrayList xspzhList, String jsjdm, int lx) throws
      BaseException {
    if (xspzhList == null || xspzhList.size() == 0)
      throw ExceptionUtil.getBaseException(
          new ApplicationException("印花税修改的销售凭证号不能为空！"));
    Connection conn = null;
    try {
      StringBuffer sql = new StringBuffer();
      if (lx == 1) {
        sql.append(" update SBDB.SB_JL_YHSGMZ set jzbs = '1' ")
            .append(" where xspzh = ? ")
            .append(" and dsjsjdm = '").append(jsjdm).append("'")
            .append(" and jzbs = '0' ");
      }
      else if (lx == 0) {
        sql.append(" update SBDB.SB_JL_YHSGMZ set jzbs = '0' ")
            .append(" where xspzh = ? ")
            .append(" and dsjsjdm = '").append(jsjdm).append("'")
            .append(" and jzbs = '1' ");
      }
      else
        throw ExceptionUtil.getBaseException(
            new ApplicationException("未知更新标识！"));

      // 得到连接
      conn = DBResource.getConnection(DBResource.DB_SHENBAO);
      PreparedStatement st = conn.prepareStatement(sql.toString());
      for (int i = 0; i < xspzhList.size(); i++) {
        st.setString(1, (String) xspzhList.get(i));
        st.executeUpdate();
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      throw ExceptionUtil.getBaseException(e, "修改记帐标识失败！");
    }
    finally {
      //释放连接
      DBResource.destroyConnection(conn);
    }
  }

  /**
   * 为票证模块提供的根据缴款凭证号获得入库金额的接口方法
   * 非联网情况
   * @param jkpzh  缴款凭证号
   * @return BigDecimal rkje
   * @throws BaseException
   */
  public static BigDecimal getRkjeByJkpzhPZ(String jkpzh) throws BaseException {
    //参数检查
    if (jkpzh == null || jkpzh.equals("")) {
      throw ExceptionUtil.getBaseException(new ApplicationException(
          "输入的缴款凭证号为空!"));
    }
    ArrayList zbResult = new ArrayList(); //定义主表查询结果集
    //定义数据库连接
    Connection conn = null;
    //OR实例
    ORManager orManager = null;
    try {
      // 获得 ORManager
      orManager = DBResource.getORManager(DBResource.OR_SHENBAO);

      //查找申报缴款主表
      StringBuffer sqlStrBuf = new StringBuffer();
      sqlStrBuf.append("(JKPZH = '").append(jkpzh).append("')");
      String sqlString = sqlStrBuf.toString(); //将缴款凭证号列表中的所有记录都查询出来
      Vector criteria = new Vector();
      criteria.add(sqlString);
      ORContext orCtx = new ORContext(Sbjkzb.class.getName(), criteria);
      // 获得数据库连接
      conn = DBResource.getConnection(DBResource.DB_SHENBAO);
      zbResult = (ArrayList) orManager.query(SESSION_ID, conn, orCtx); //主表查询结果
      if (zbResult.size() == 0) {
        return null; //没有满足条件的记录(即，该缴款凭证号不存在)，返回一个null
      }
      Sbjkzb tempZb = (Sbjkzb) zbResult.get(0);
      //如果帐务标识第二位为‘0’，则标识未入库，则返回入库金额为0 ！！！
      if (tempZb.getZwbs().substring(1, 2).equals("0")) {
        return new BigDecimal("0");
      }
      return tempZb.getRkje(); //返回该缴款凭证的入库金额
    }
    catch (Exception ex) {
      ex.printStackTrace();
      throw ExceptionUtil.getBaseException(ex, "查询入库数据失败，未得到缴款数据！");
    }
    finally {
      //释放连接
      DBResource.destroyConnection(conn);
    }
  }



  /**
   * 为票证模块提供的根据缴款凭证号获得入库金额的接口方法
   * 联网情况
   * @param jkpzh  缴款凭证号
   * @return BigDecimal rkje
   * @throws BaseException
   */
  public static BigDecimal getRkjeByJkpzhPZForLw(String jkpzh) throws BaseException {
    //参数检查
    if (jkpzh == null || jkpzh.equals("")) {
      throw ExceptionUtil.getBaseException(new ApplicationException(
          "输入的缴款凭证号为空!"));
    }
    ArrayList zbResult = new ArrayList(); //定义主表查询结果集
    //定义数据库连接
    Connection conn = null;
    //OR实例
    ORManager orManager = null;
    try {
      // 获得 ORManager
      orManager = DBResource.getORManager(DBResource.OR_SHENBAO);

      //查找申报缴款主表
      StringBuffer sqlStrBuf = new StringBuffer();
      sqlStrBuf.append("(sphm = '").append(jkpzh).append("')");
      String sqlString = sqlStrBuf.toString(); //将缴款凭证号列表中的所有记录都查询出来
      Vector criteria = new Vector();
      criteria.add(sqlString);
      ORContext orCtx = new ORContext(Sbjkzb.class.getName(), criteria);
      // 获得数据库连接
      conn = DBResource.getConnection(DBResource.DB_SHENBAO);
      zbResult = (ArrayList) orManager.query(SESSION_ID, conn, orCtx); //主表查询结果
      if (zbResult.size() == 0) {
        return null; //没有满足条件的记录(即，该缴款凭证号不存在)，返回一个null
      }
      Sbjkzb tempZb = (Sbjkzb) zbResult.get(0);
      //如果帐务标识第二位为‘0’，则标识未入库，则返回入库金额为0 ！！！
      if (tempZb.getZwbs().substring(1, 2).equals("0")) {
        return new BigDecimal("0");
      }
      return tempZb.getRkje(); //返回该缴款凭证的入库金额
    }
    catch (Exception ex) {
      ex.printStackTrace();
      throw ExceptionUtil.getBaseException(ex, "查询入库数据失败，未得到缴款数据！");
    }
    finally {
      //释放连接
      DBResource.destroyConnection(conn);
    }
  }


}