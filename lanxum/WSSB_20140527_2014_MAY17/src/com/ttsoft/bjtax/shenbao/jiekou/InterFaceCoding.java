package com.ttsoft.bjtax.shenbao.jiekou;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.dj.model.YHZH;
import com.ttsoft.bjtax.dj.proxy.ServiceProxy;
import com.ttsoft.bjtax.shenbao.jiekou.processor.ShenbaoProcessor;
import com.ttsoft.bjtax.shenbao.jiekou.vo.Nspg;
import com.ttsoft.bjtax.shenbao.model.client.DeclareInfor;
import com.ttsoft.bjtax.shenbao.model.client.Sbsj;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkmx;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb;

//import com.ttsoft.bjtax.shenbao.util.FriendHelper;
//import com.ttsoft.bjtax.shenbao.util.*;

/**
 * 提供给纳税评估系统的接口代码
 * <p>Title: 北京地税综合管理系统  申报征收模块</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: TTSOFT</p>
 * @author wanghw
 * @version 1.0
 */
public class InterFaceCoding {
  //定义自身实例
  private static InterFaceCoding _instance = null;
  //构造函数
  private InterFaceCoding() {

  }

  /**
   * 获取该类当前实例
   * @return instance
   */
  public static InterFaceCoding getInstance() {
    if (_instance == null)
      _instance = new InterFaceCoding();
    return _instance;
  }

  /**
   * 删除缴款凭证号对应的缴款数据,
   * @param jkpzhList 缴款凭证号ArrayList
   * @param conn  数据库连接
   * @throws Exception 操作异常 注意当要删除的缴款凭证号不存在时也不抛异常
   */
  public void deleteJKS(ArrayList jkpzhList, Connection conn) throws Exception {
    log("=======================deleteJKS start========================");
    if (jkpzhList == null || jkpzhList.size() == 0) {
      throw new Exception("参数不合法，请提交正确的缴款凭证号！");
    }
    try {
      //删除明细和主表数据
      StringBuffer sqlBuffer = new StringBuffer();
      sqlBuffer.append("JKPZH IN ('" + jkpzhList.get(0) + "'");
      for (int i = 1; i < jkpzhList.size(); i++) {
        sqlBuffer.append(",'" + jkpzhList.get(i) + "'");
      }
      sqlBuffer.append(") AND SJLY = '").append(SBJK_SJLY_NSPGLR)
          .append(
          "' AND (substr(zwbs, 1, 1) = '0' AND substr(zwbs, 20, 1) = '0')");
      String wheresql = sqlBuffer.toString(); //删除条件

      String sqlZb = wheresql; //主表条件
      String sqlMx = "JKPZH IN (SELECT JKPZH FROM SBDB.SB_JL_SBJKZB WHERE "
          + wheresql + ")";

      StringBuffer delBuffer = new StringBuffer();
      Statement st = conn.createStatement();
      delBuffer.append("DELETE FROM SBDB.SB_JL_SBJKMX WHERE ").append(sqlMx);
      st.addBatch(delBuffer.toString());
      delBuffer.setLength(0);
      delBuffer.append("DELETE FROM SBDB.SB_JL_SBJKZB WHERE ").append(sqlZb);
      st.addBatch(delBuffer.toString());
      st.executeBatch(); //delete
      delBuffer.setLength(0); //clear
      sqlBuffer.setLength(0); //clear
    }
    catch (Exception ex) {
      throw new Exception(ex.getMessage() + "--delete data failed!");
    }
    log("=======================deleteJKS end========================");
  }

  /**
   * 根据缴款凭证号查询该记录是否已入库及入库数据
   * @param jkpzhList 缴款凭证号
   * @param conn  数据库连接
   * @return ArrayList 返回数据列表
   * @throws java.lang.Exception
   */
  public ArrayList queryDataByJkpzh(ArrayList jkpzhList, Connection conn) throws
      Exception {
    log("=======================queryDataByJkpzh start========================");
    if (jkpzhList == null || jkpzhList.size() == 0 || conn == null)
      throw new Exception("error:参数不能为空！");

    try {
      ArrayList resultList = new ArrayList(); //define result
      ArrayList sqlList = new ArrayList(); //分组in，max_count一组

      int c = jkpzhList.size();
      int n = 0;

      while (n < c) {
        StringBuffer s = new StringBuffer();
        s.append(" JKPZH IN ('").append(jkpzhList.get(n)).append("'");
        for (int i = 1; i < MAX_COUNT; i++) {
          n = n + 1;
          if (n >= c)
            break;
          s.append(",'").append(jkpzhList.get(n)).append("'");
        }
        n = n + 1;
        s.append(") ");
        sqlList.add(s);
      }

      String sqlHead =
          "SELECT JKPZH, RKJE, ZYRQ, ZWBS FROM SBDB.SB_JL_SBJKZB WHERE ";

      String sqlEnd = " ORDER BY JKPZH";
      Statement st = conn.createStatement();
      ResultSet rs = null;
      for (int i = 0; i < sqlList.size(); i++) {
        StringBuffer sql = new StringBuffer();
        StringBuffer sqlWhere = (StringBuffer) sqlList.get(i);
        sql.append(sqlHead).append(sqlWhere.toString()).append(sqlEnd);
        rs = st.executeQuery(sql.toString());
        while (rs.next()) {
          Nspg nspg = new Nspg();
          nspg.setJkpzh(rs.getString("jkpzh"));
          nspg.setRkje(rs.getBigDecimal("rkje"));
          nspg.setZyrq(rs.getTimestamp("zyrq"));
          //帐务标识第一位为0，表示未入库
          if (rs.getString("zwbs").substring(0, 1).equals("0"))
            nspg.setIsYrk(Boolean.FALSE); //未入库
          else
            nspg.setIsYrk(Boolean.TRUE);

          resultList.add(nspg); //add to result list
        }
      }
      log("=======================queryDataByJkpzh end========================");

      return resultList;
    }
    catch (Exception ex) {
      throw new Exception(ex.getMessage() + "--query data failed!");
    }
  }

  /**
   * 根据纳税评估提供的申报数据和数据库连接，生成缴款数据
   * @param sbsj 申报数据
   * @param conn 数据库连接
   * @return 缴款值对象
   * @throws java.lang.Exception
   */
  public DeclareInfor shenbaoJiaokuan(Sbsj sbsj, Connection conn) throws
      Exception {
    log("=======================shenbaoJiaokuan start========================");
    Timestamp now = new Timestamp(System.currentTimeMillis());
    String nd = new SimpleDateFormat("yyyy").format(now);
    //填写申报主表信息
    Map map = null;
    //调用登记接口
    ServiceProxy serviceProxy = new ServiceProxy();
    map = serviceProxy.getDjInfo(sbsj.getJsjdm());
    SWDJJBSJ swdjjbsj = (SWDJJBSJ) map.get("JBSJ");
    //把生成的数据插入数据表中并返回
    ShenbaoProcessor sbPro = new ShenbaoProcessor();
    //String p = sbPro.getProperty("WSSB_JIEKOU_NSPG_EXCLUDES_NSRZT",conn);
    String n = swdjjbsj.getNsrzt();
    String swjgzzjgdm = swdjjbsj.getSwjgzzjgdm(); //税务机关

//判断纳税人是否可申报,税务机关组织机构代码后两位为00的不能开缴款书
    //   if (n.equals("20") || n.equals("40") || n.equals("50"))

    if (swjgzzjgdm.substring(swjgzzjgdm.length() - 2)
        .equalsIgnoreCase("00")) {
      // 非正常户
      throw new Exception("该纳税人状态不为正常，不能生成缴款书！");
    }

    List sbjkmxList = new ArrayList();
    //填写申报明细信息
    Sbjkmx sbjkmx = new Sbjkmx();
    sbjkmx.setJsjdm(sbsj.getJsjdm());
    sbjkmx.setSjse(sbsj.getJe());
    sbjkmx.setRkje(sbsj.getJe());
    sbjkmx.setSzsmdm(sbsj.getSzsmdm());
    sbjkmx.setSwjgzzjgdm(swjgzzjgdm);
    sbjkmx.setNd(nd);
    sbjkmx.setSkssksrq(sbsj.getSkssksrq());
    sbjkmx.setSkssjsrq(sbsj.getSkssjsrq());
    sbjkmx.setCjrq(now); //创建日期
    sbjkmx.setLrrq(now); //录入日期
    sbjkmx.setQxdm(swjgzzjgdm.substring(0, 2)); //区县代码
    sbjkmxList.add(sbjkmx);

    //申报主表数据
    Sbjkzb sbjkzb = new Sbjkzb();
    sbjkzb.setJsjdm(sbsj.getJsjdm());
    //从税务登记得到的数据
    sbjkzb.setDjzclxdm(swdjjbsj.getDjzclxdm());
    sbjkzb.setGjbzhydm(swdjjbsj.getGjbzhydm());
    sbjkzb.setLsgxdm(swdjjbsj.getLsgxdm());
    sbjkzb.setNsrmc(swdjjbsj.getNsrmc());
    sbjkzb.setSwjgzzjgdm(swdjjbsj.getSwjgzzjgdm());
    sbjkzb.setLrrq(now);
    sbjkzb.setCjrq(now);
    sbjkzb.setFsdm(FSDM_SM); //征收方式为上门
    sbjkzb.setLrr(sbsj.getLrr());
    sbjkzb.setRkje(sbsj.getJe());
    sbjkzb.setSbrq(second2Day(now));
    sbjkzb.setZyrq(second2Day(now)); //帐页日期
    sbjkzb.setSjje(sbsj.getJe());
    sbjkzb.setSklxdm(sbsj.getSklxdm());
    sbjkzb.setSzdm(sbsj.getSzdm());
    sbjkzb.setXjrq(sbsj.getXjrq());
    sbjkzb.setSkssksrq(sbsj.getSkssksrq());
    sbjkzb.setSkssjsrq(sbsj.getSkssjsrq());
    //获取银行帐户信息
    List tempList = (List) map.get("YHZH");
    if (tempList != null && tempList.size() != 0) {
      for (int i = 0; i < tempList.size(); i++) {
        YHZH yhzh = (YHZH) tempList.get(i);
        if (yhzh.getJbzhbs().equals("1")) { //基本银行信息
          sbjkzb.setYhdm(yhzh.getYhdm()); //银行代码
          sbjkzb.setYhmc(yhzh.getKhyhmc()); //银行名称
          sbjkzb.setZh(yhzh.getZh()); //银行账号
        }
      }
    }
    sbjkzb.setZsswjgzzjgdm(sbsj.getZsjgdm());
    sbjkzb.setNd(nd);
    sbjkzb.setBz(sbsj.getBz());
    sbjkzb.setSjly(sbsj.getSjly()); //数据来源
    sbjkzb.setQxdm(swjgzzjgdm.substring(0, 2)); //区县代码
    sbjkzb.setClbjdm(CLBJDM_YSB); //默认为‘已申报’

    //创建数据对象
    DeclareInfor declareInfor = new DeclareInfor(sbjkzb, sbjkmxList);
    declareInfor.setIsReturnPaymentInfo(true); //返回数据

    String sbbh = sbPro.getSbbh(sbsj.getJsjdm());
    List dataList = (List) sbPro.createJkInfor(declareInfor, sbbh, conn);

    //只有一张票，因为，只有一条缴款明细记录
    DeclareInfor data = (DeclareInfor) dataList.get(0);
    log("=======================shenbaoJiaokuan end========================");
    return data; //封装后的数据返回
  }

  //将秒格式的Timestamp转换为到天格式的Timestamp
  public java.sql.Timestamp second2Day(java.sql.Timestamp date) {
    if (date == null)
      return null;
    java.sql.Timestamp tempStamp = null;

    try {
      String tempStr = new SimpleDateFormat("yyyy-MM-dd").format(date);
      tempStamp = java.sql.Timestamp.valueOf(tempStr + " 00:00:00.000");
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }

    return tempStamp;
  }

  /**
   * 判定区县银行是否联网
   * @param swjgzzjgdm 税务机关组织机构代码
   * @param yhdm 银行代码
   * @param conn 数据库连接
   * @return true-已联网，false-未联网
   * @throws java.lang.Exception 异常
   */
  public boolean isLw(String swjgzzjgdm, String yhdm, Connection conn) throws
      Exception {
    log("=======================isLw end========================");
    //0.句柄申明
    boolean rnFlag = false;
    StringBuffer sb = null;
    String sql = null;
    Statement stat = null;
    ResultSet rs = null;
    boolean exFlag = false;
    //1.入口参数检查
    if (swjgzzjgdm == null || NULL_ZERO.equals(swjgzzjgdm) || yhdm == null ||
        NULL_ZERO.equals(yhdm)) {
      throw new Exception("入口参数非法(swjgzzjgdm=" + swjgzzjgdm + ",yhdm=" + yhdm +
                          ")");
    }
    else if (conn == null) {
      throw new Exception("入口数据库连接为空！");
    }
    //2.初始化
    sb = new StringBuffer();
    //3.业务过程
    ///3.0.创建查询SQL及相关对象
    String swjgzzjgLwzt = null;
    String yhLwzt = null;
    sb.append("SELECT a.SWJGZZJGDM as dm1,a.LWZT zt1,b.YHDM as dm2,b.LWZT as zt2 FROM DMDB.GY_DM_SWJGZZJG a,DMDB.GY_DM_YH b WHERE a.SWJGZZJGDM='");
    sb.append(swjgzzjgdm);
    sb.append("' AND b.YHDM='");
    sb.append(yhdm);
    sb.append("'");
    sql = sb.toString();
    ///3.1.执行查询
    try {
      stat = conn.createStatement();
      rs = stat.executeQuery(sql);
      if (rs.next()) {
        swjgzzjgLwzt = rs.getString("zt1");
        yhLwzt = rs.getString("zt2");
        exFlag = false;
      }
      else {
        exFlag = true;
      }
      rs.close();
      stat.close();
      if (exFlag) {
        throw new Exception("(swjgzzjgdm=" + swjgzzjgdm + ",yhdm=" + yhdm +
                            ")在数据库中没有获得对应的代码记录！");
      }
    }
    catch (Exception ex) {
      log(ex.getMessage());
      throw ex;
    }
    ///3.2.判定是否联网
    if (LWZT_LW.equals(swjgzzjgLwzt) && LWZT_LW.equals(yhLwzt)) {
      rnFlag = true;
      log("(swjgzzjgdm=" + swjgzzjgdm + ",yhdm=" + yhdm + ")对应的区县银行已联网!");
    }
    else {
      rnFlag = false;
      log("(swjgzzjgdm=" + swjgzzjgdm + ",yhdm=" + yhdm + ")对应的区县银行未联网!");
    }
    //99.返回值
    log("=======================isLw end========================");
    return rnFlag;
  }

  /**
   * 判定区县银行是否联网
   * @param swjgzzjgdm 税务机关组织机构代码
   * @param conn 数据库连接
   * @return true-已联网，false-未联网
   * @throws java.lang.Exception 异常
   */
  public boolean isLwByQxdm(String swjgzzjgdm,Connection conn) throws
      Exception {
    log("=======================isLw end========================");
    //0.句柄申明
    boolean rnFlag = false;
    StringBuffer sb = null;
    String sql = null;
    Statement stat = null;
    ResultSet rs = null;
    boolean exFlag = false;
    //1.入口参数检查
    if (swjgzzjgdm == null || NULL_ZERO.equals(swjgzzjgdm)) {
      throw new Exception("入口参数非法(swjgzzjgdm=" + swjgzzjgdm + ")");
    }
    else if (conn == null) {
      throw new Exception("入口数据库连接为空！");
    }
    //2.初始化
    sb = new StringBuffer();
    //3.业务过程
    ///3.0.创建查询SQL及相关对象
    String swjgzzjgLwzt = null;
    sb.append("SELECT a.SWJGZZJGDM as dm1,a.LWZT zt1 FROM DMDB.GY_DM_SWJGZZJG a WHERE a.SWJGZZJGDM='");
    sb.append(swjgzzjgdm);
    sb.append("'");
    sql = sb.toString();
    ///3.1.执行查询
    try {
      stat = conn.createStatement();
      rs = stat.executeQuery(sql);
      if (rs.next()) {
        swjgzzjgLwzt = rs.getString("zt1");
        exFlag = false;
      }
      else {
        exFlag = true;
      }
      rs.close();
      stat.close();
      if (exFlag) {
        throw new Exception("(swjgzzjgdm=" + swjgzzjgdm +
                            ")在数据库中没有获得对应的代码记录！");
      }
    }
    catch (Exception ex) {
      log(ex.getMessage());
      throw ex;
    }
    ///3.2.判定是否联网
    if (LWZT_LW.equals(swjgzzjgLwzt)) {
      rnFlag = true;
      log("(swjgzzjgdm=" + swjgzzjgdm  + ")对应的区县已联网!");
    }
    else {
      rnFlag = false;
      log("(swjgzzjgdm=" + swjgzzjgdm  + ")对应的区县未联网!");
    }
    //99.返回值
    log("=======================isLw end========================");
    return rnFlag;
  }


  /**
   * 根据缴款凭证号的数据判定涉及区县银行是否联网
   * @param jkpzh 缴款凭证号
   * @param conn 数据库连接
   * @return true-已联网，false-未联网
   * @throws java.lang.Exception 异常
   */
  public boolean isLwQueryByJkpzh(String jkpzh, Connection conn) throws
      Exception {
    log("=======================isLwQueryByJkpzh start========================");
    //0.句柄申明
    boolean rnFlag = false;
    StringBuffer sb = null;
    String sql = null;
    Statement stat = null;
    ResultSet rs = null;
    boolean exFlag = false;
    String swjgzzjgdm = null;
    String yhdm = null;
    //1.入口参数检查
    if (jkpzh == null || NULL_ZERO.equals(jkpzh)) {
      throw new Exception("入口参数非法(jkpzh=" + jkpzh + ")");
    }
    else if (conn == null) {
      throw new Exception("入口数据库连接为空！");
    }
    try {
      stat = conn.createStatement();
      //2.获取缴款数据的银行帐户和税务机关组织机构代码
      ///2.0.创建查询SQL及相关对象
      sb = new StringBuffer();
      sb.append("SELECT swjgzzjgdm,yhdm FROM SBDB.SB_JL_SBJKZB WHERE JKPZH='");
      sb.append(jkpzh);
      sb.append("'");
      sql = sb.toString();
      ///2.1.执行查询
      rs = stat.executeQuery(sql);
      if (rs.next()) {
        swjgzzjgdm = rs.getString("swjgzzjgdm");
        yhdm = rs.getString("yhdm");
      }
      else {
        throw new Exception("(jkpzh=" + jkpzh + ")在数据库中没有获得对应的代码记录！");
      }
      //3.判定是否联网业务过程
      rnFlag = this.isLw(swjgzzjgdm, yhdm, conn);
    }
    catch (Exception ex) {
      log(ex.getMessage());
      throw ex;
    }
    //99.返回值
    log("=======================isLwQueryByJkpzh end========================");
    return rnFlag;
  }

  /**
   * 根据缴款凭证号获取对应的申报编号
   * @param jkpzh 缴款凭证号
   * @param conn 数据库连接
   * @return 申报编号
   * @throws java.lang.Exception 异常
   */
  public String getSbbhByJkpzh(String jkpzh, Connection conn) throws Exception {
    log("=======================getSbbhByJkpzh start========================");
    //0.句柄申明
    String rnStr = null;
    StringBuffer sb = null;
    String sql = null;
    Statement stat = null;
    ResultSet rs = null;
    boolean exFlag = false;
    //1.入口参数检查
    if (jkpzh == null || NULL_ZERO.equals(jkpzh)) {
      throw new Exception("入口参数非法(jkpzh=" + jkpzh + ")");
    }
    else if (conn == null) {
      throw new Exception("入口数据库连接为空！");
    }
    //2.初始化
    sb = new StringBuffer();
    //3.业务过程
    ///3.0.创建查询SQL及相关对象
    sb.append("SELECT sbbh FROM SBDB.SB_JL_SBJKZB WHERE JKPZH='");
    sb.append(jkpzh);
    sb.append("'");
    sql = sb.toString();
    ///3.1.执行查询
    try {
      stat = conn.createStatement();
      rs = stat.executeQuery(sql);
      if (rs.next()) {
        rnStr = rs.getString("sbbh");
        exFlag = false;
      }
      else {
        exFlag = true;
      }
      rs.close();
      stat.close();
      if (exFlag) {
        throw new Exception("(jkpzh=" + jkpzh + ")在数据库中没有获得对应的代码记录！");
      }
    }
    catch (Exception ex) {
      log(ex.getMessage());
      throw ex;
    }
    ///3.2.检查申报编号合法性
    if (rnStr == null || NULL_ZERO.equals(rnStr) || NULL_STR.equals(rnStr)) {
      throw new Exception("(jkpzh=" + jkpzh + ")在数据库中对应的申报编号为空！");
    }
    //99.返回值
    log("=======================getSbbhByJkpzh end========================");
    return rnStr;
  }

  /**
   * 根据申报编号查询未入库数据
   * @param sbbh 缴款凭证号
   * @param conn  数据库连接
   * @return ArrayList 返回数据列表 成员变量为DeclareInfor
   * @throws java.lang.Exception
   */
  public List queryDataBySBBH(String sbbh, Connection conn) throws Exception {
    log("=======================queryDataBySBBH start========================");
    //0.句柄申明
    List rnList = null;
    StringBuffer sb = null;
    String sql = null;
    Statement stat = null;
    ResultSet rs = null;
    boolean exFlag = false;
    DeclareInfor declareInfor = null;
    Sbjkzb sbjkzb = null;
    Sbjkmx sbjkmx = null;
    ArrayList jksmxList = null;
    //1.入口参数检查
    if (sbbh == null || NULL_ZERO.equals(sbbh)) {
      throw new Exception("入口参数非法(sbbh=" + sbbh + ")");
    }
    else if (conn == null) {
      throw new Exception("入口数据库连接为空！");
    }
    //2.初始化
    rnList = new ArrayList();
    String jsjdm=sbbh.substring(0,8);
    //3.业务过程
    ///3.0.创建相关对象
    ///3.1.获取数据
    try {
      ////3.1.0.初始化数据库
      stat = conn.createStatement();
      ////3.1.1.创建查询申报缴款主表的SQL
      sb = new StringBuffer();
      sb.append("SELECT * FROM SBDB.SB_JL_SBJKZB WHERE sbbh='");
      sb.append(sbbh);
      sb.append("' AND jsjdm='");
      sb.append(jsjdm);
      sb.append("' AND zwbs like '0%0'");
      sql = sb.toString();
      ////3.1.1.执行查询
      rs = stat.executeQuery(sql);
      ////3.1.2.整理申报缴款数据并加入数据集
      while (rs.next()) {
        declareInfor = new DeclareInfor();
        sbjkzb = new Sbjkzb();
        jksmxList = new ArrayList();
        declareInfor.setSbjkzb(sbjkzb);
        declareInfor.setSbjkmxInfo(jksmxList);
        //值对象转换
        sbjkzb.setJkpzh(rs.getString("jkpzh"));
        sbjkzb.setSklxdm(rs.getString("sklxdm"));
        sbjkzb.setJsjdm(rs.getString("jsjdm"));
        sbjkzb.setFsdm(rs.getString("fsdm"));
        sbjkzb.setLsgxdm(rs.getString("lsgxdm"));
        sbjkzb.setYhdm(rs.getString("yhdm"));
        sbjkzb.setYhmc(rs.getString("yhmc"));
        sbjkzb.setZh(rs.getString("zh"));
        sbjkzb.setDjzclxdm(rs.getString("djzclxdm"));
        sbjkzb.setSwjgzzjgdm(rs.getString("swjgzzjgdm"));
        sbjkzb.setZsswjgzzjgdm(rs.getString("zsswjgzzjgdm"));
        sbjkzb.setGjbzhydm(rs.getString("gjbzhydm"));
        sbjkzb.setGkzzjgdm(rs.getString("gkzzjgdm"));
        sbjkzb.setYskmdm(rs.getString("yskmdm"));
        sbjkzb.setYsjcdm(rs.getString("ysjcdm"));
        sbjkzb.setSzdm(rs.getString("szdm"));
        sbjkzb.setLrrq(rs.getTimestamp("lrrq"));
        sbjkzb.setSbrq(rs.getTimestamp("sbrq"));
        sbjkzb.setJksj(rs.getTimestamp("jksj"));
        sbjkzb.setXjrq(rs.getTimestamp("xjrq"));
        sbjkzb.setClbjdm(rs.getString("clbjdm"));
        sbjkzb.setSjje(rs.getBigDecimal("sjje"));
        sbjkzb.setZyrq(rs.getTimestamp("zyrq"));
        sbjkzb.setRkje(rs.getBigDecimal("rkje"));
        sbjkzb.setZwbs(rs.getString("zwbs"));
        sbjkzb.setHxrdm(rs.getString("hxrdm"));
        sbjkzb.setHxrmc(rs.getString("hxrmc"));
        sbjkzb.setLrr(rs.getString("lrr"));
        sbjkzb.setBz(rs.getString("bz"));
        sbjkzb.setHxrq(rs.getTimestamp("hxrq"));
        sbjkzb.setSbbh(rs.getString("sbbh"));
        sbjkzb.setJydzlxdm(rs.getString("jydzlxdm"));
        sbjkzb.setSkssksrq(rs.getTimestamp("skssksrq"));
        sbjkzb.setSkssjsrq(rs.getTimestamp("skssjsrq"));
        sbjkzb.setSjly(rs.getString("sjly"));
        sbjkzb.setNd(rs.getString("nd"));
        sbjkzb.setCjrq(rs.getTimestamp("cjrq"));
        sbjkzb.setQxdm(rs.getString("qxdm"));
        sbjkzb.setSphm(rs.getString("sphm"));
        rnList.add(declareInfor);
      }
      rs.close();
      ////3.1.2.检查主数据集合的合法性
      if (rnList.size() == 0) {
        throw new Exception("(sbbh=" + sbbh + ")在数据库中没有获得对应的申报缴款主表未入库代码记录！");
      }
      ////3.1.2.创建查询申报缴款主表的SQL
      sb = new StringBuffer();
      sb.append("SELECT * FROM SBDB.SB_JL_SBJKMX WHERE sbbh='");
      sb.append(sbbh);
      sb.append("' AND jsjdm='");
      sb.append(jsjdm);
      sb.append("'");
      sql = sb.toString();
      ////3.1.1.执行查询
      rs = stat.executeQuery(sql);
      boolean mxFlag = false;
      while (rs.next()) {
        /////3.1.1.0.生成新的值对象
        sbjkmx = new Sbjkmx();
        mxFlag = false;
        /////3.1.1.1.数据转换
        sbjkmx.setSzsmdm(rs.getString("szsmdm"));
        sbjkmx.setJkpzh(rs.getString("jkpzh"));
        sbjkmx.setJsjdm(rs.getString("jsjdm"));
        sbjkmx.setYskmdm(rs.getString("yskmdm"));
        sbjkmx.setYsjcdm(rs.getString("ysjcdm"));
        sbjkmx.setKssl(rs.getBigDecimal("kssl"));
        sbjkmx.setJsje(rs.getBigDecimal("jsje"));
        sbjkmx.setSjse(rs.getBigDecimal("sjse"));
        sbjkmx.setSkssksrq(rs.getTimestamp("skssksrq"));
        sbjkmx.setSkssjsrq(rs.getTimestamp("skssjsrq"));
        sbjkmx.setRkje(rs.getBigDecimal("rkje"));
        sbjkmx.setSbbh(rs.getString("sbbh"));
        sbjkmx.setSjfc(rs.getBigDecimal("sjfc"));
        sbjkmx.setQjfc(rs.getBigDecimal("qjfc"));
        sbjkmx.setSwjgzzjgdm(rs.getString("swjgzzjgdm"));
        sbjkmx.setNd(rs.getString("nd"));
        sbjkmx.setSl(rs.getBigDecimal("sl"));
        sbjkmx.setCjrq(rs.getTimestamp("cjrq"));
        sbjkmx.setLrrq(rs.getTimestamp("lrrq"));
        sbjkmx.setQxdm(rs.getString("qxdm"));
        /////3.1.1.2.判定属于哪一个数据集并加入该数据集,如果找不到则抛出异常
        for (int i = 0; i < rnList.size(); i++) {
          declareInfor = (DeclareInfor) rnList.get(i);
          if (sbjkmx.getJkpzh().equals(declareInfor.getSbjkzb().getJkpzh()) &&
              sbjkmx.getJsjdm().equals(declareInfor.getSbjkzb().getJsjdm())) {
            mxFlag = true;
            declareInfor.getSbjkmxInfo().add(sbjkmx);
            break;
          }
        }
        if (!mxFlag) {
          throw new Exception("无法找到申报缴款明细[" + sbjkmx.getJkpzh() + "]");
        }
      }
      rs.close();
      ////3.1.2.对数据集合进行合法性检查
      for (int i = 0; i < rnList.size(); i++) {
        declareInfor = (DeclareInfor) rnList.get(i);
        if (declareInfor.getSbjkmxInfo().size() == 0) {
          throw new Exception("无法找到对应的申报缴款明细[" +
                              declareInfor.getSbjkzb().getJkpzh() + "]");
        }
      }
      ////3.1.99.关闭数据库连接
      stat.close();
    }
    catch (Exception ex) {
      log(ex.getMessage());
      throw ex;
    }
    //99.返回值
    log("=======================queryDataBySBBH end========================");
    return rnList;
  }

  public String getSbbh(String jsjdm, Connection conn) throws Exception {
    log("=======================getSbbh start========================");
    //0.句柄申明
    String sequence = "00000000"; //默认
    String sql = null;
    Statement st = null;
    ResultSet rs = null;
    String sbbh = null;
    //1.入口参数校验
    if (jsjdm == null || jsjdm.equals(""))
      throw new Exception("计算机代码不能为空！");
    //2.初始化
    //3.业务过程
    try {
      //1.创建资源
      st = conn.createStatement();
      //2.定义sql语句，批处理，取当前序号
      sql = "SELECT LPAD(SBDB.SEQ_SB_SBBH.NEXTVAL,8,'0') FROM DUAL";
      rs = st.executeQuery(sql);
      if (rs.next()) {
        sequence = rs.getString(1);
      }
      else {
        throw new Exception("无法获取申报编号从序列中");
      }
      //3.组织序号
      sbbh = jsjdm + getCurYearStr() + sequence;
    }
    catch (Exception e) {
      e.printStackTrace();
      throw new Exception(e.getMessage() + "获取申报编号失败!");
    }
    finally {
      try {
        rs.close();
        st.close();
      }
      catch (Exception sqle) {
        sqle.printStackTrace();
      }
    }
    //99.返回值
    log("=======================getSbbh end========================");
    return sbbh;
  }

  /**
   * 根据纳税评估提供的申报数据和数据库连接，生成缴款数据
   * @param sbsj 申报数据
   * @param sbbh 申报编号
   * @param conn 数据库连接
   * @return 缴款值对象
   * @throws java.lang.Exception
   */
  public DeclareInfor shenbaoJiaokuanWithSbbh(Sbsj sbsj, String sbbh,
                                              Connection conn) throws
      Exception {
    log(
        "=======================shenbaoJiaokuanWithSbbh start========================");
    Timestamp now = new Timestamp(System.currentTimeMillis());
    String nd = new SimpleDateFormat("yyyy").format(now);
    //填写申报主表信息
    Map map = null;
    //调用登记接口
    ServiceProxy serviceProxy = new ServiceProxy();
    map = serviceProxy.getDjInfo(sbsj.getJsjdm());
    SWDJJBSJ swdjjbsj = (SWDJJBSJ) map.get("JBSJ");
    //把生成的数据插入数据表中并返回
    ShenbaoProcessor sbPro = new ShenbaoProcessor();
    //String p = sbPro.getProperty("WSSB_JIEKOU_NSPG_EXCLUDES_NSRZT",conn);
    String n = swdjjbsj.getNsrzt();
    String swjgzzjgdm = swdjjbsj.getSwjgzzjgdm(); //税务机关

//判断纳税人是否可申报,税务机关组织机构代码后两位为00的不能开缴款书
    //   if (n.equals("20") || n.equals("40") || n.equals("50"))

    if (swjgzzjgdm.substring(swjgzzjgdm.length() - 2)
        .equalsIgnoreCase("00")) {
      // 非正常户
      throw new Exception("该纳税人状态不为正常，不能生成缴款书！");
    }

    List sbjkmxList = new ArrayList();
    //填写申报明细信息
    Sbjkmx sbjkmx = new Sbjkmx();
    sbjkmx.setJsjdm(sbsj.getJsjdm());
    sbjkmx.setSjse(sbsj.getJe());
    sbjkmx.setRkje(sbsj.getJe());
    sbjkmx.setSzsmdm(sbsj.getSzsmdm());
    sbjkmx.setSwjgzzjgdm(swjgzzjgdm);
    sbjkmx.setNd(nd);
    sbjkmx.setSkssksrq(sbsj.getSkssksrq());
    sbjkmx.setSkssjsrq(sbsj.getSkssjsrq());
    sbjkmx.setCjrq(now); //创建日期
    sbjkmx.setLrrq(now); //录入日期
    sbjkmx.setQxdm(swjgzzjgdm.substring(0, 2)); //区县代码
    sbjkmxList.add(sbjkmx);

    //申报主表数据
    Sbjkzb sbjkzb = new Sbjkzb();
    sbjkzb.setJsjdm(sbsj.getJsjdm());
    //从税务登记得到的数据
    sbjkzb.setDjzclxdm(swdjjbsj.getDjzclxdm());
    sbjkzb.setGjbzhydm(swdjjbsj.getGjbzhydm());
    sbjkzb.setLsgxdm(swdjjbsj.getLsgxdm());
    sbjkzb.setNsrmc(swdjjbsj.getNsrmc());
    sbjkzb.setSwjgzzjgdm(swdjjbsj.getSwjgzzjgdm());
    sbjkzb.setLrrq(now);
    sbjkzb.setCjrq(now);
    sbjkzb.setFsdm(FSDM_SM); //征收方式为上门
    sbjkzb.setLrr(sbsj.getLrr());
    sbjkzb.setRkje(sbsj.getJe());
    sbjkzb.setSbrq(second2Day(now));
    sbjkzb.setZyrq(second2Day(now)); //帐页日期
    sbjkzb.setSjje(sbsj.getJe());
    sbjkzb.setSklxdm(sbsj.getSklxdm());
    sbjkzb.setSzdm(sbsj.getSzdm());
    sbjkzb.setXjrq(sbsj.getXjrq());
    sbjkzb.setSkssksrq(sbsj.getSkssksrq());
    sbjkzb.setSkssjsrq(sbsj.getSkssjsrq());
    //获取银行帐户信息
    List tempList = (List) map.get("YHZH");
    if (tempList != null && tempList.size() != 0) {
      for (int i = 0; i < tempList.size(); i++) {
        YHZH yhzh = (YHZH) tempList.get(i);
        if (yhzh.getJbzhbs().equals("1")) { //基本银行信息
          sbjkzb.setYhdm(yhzh.getYhdm()); //银行代码
          sbjkzb.setYhmc(yhzh.getKhyhmc()); //银行名称
          sbjkzb.setZh(yhzh.getZh()); //银行账号
        }
      }
    }
    sbjkzb.setZsswjgzzjgdm(sbsj.getZsjgdm());
    sbjkzb.setNd(nd);
    sbjkzb.setBz(sbsj.getBz());
    sbjkzb.setSjly(sbsj.getSjly()); //数据来源
    sbjkzb.setQxdm(swjgzzjgdm.substring(0, 2)); //区县代码
    sbjkzb.setClbjdm(CLBJDM_YSB); //默认为‘已申报’

    //创建数据对象
    DeclareInfor declareInfor = new DeclareInfor(sbjkzb, sbjkmxList);
    declareInfor.setIsReturnPaymentInfo(true); //返回数据
    List dataList = (List) sbPro.createJkInfor(declareInfor, sbbh, conn);

    //只有一张票，因为，只有一条缴款明细记录
    DeclareInfor data = (DeclareInfor) dataList.get(0);
    log(
        "=======================shenbaoJiaokuanWithSbbh start========================");
    return data; //封装后的数据返回
  }

  /**
   * 删除申报编号对应的未缴款申报数据,如果该申报编号对应的数据集中如果存在已缴款数据则抛出异常
   * @param sbbh 申报编号
   * @param conn  数据库连接
   * @throws Exception 操作异常 注意当要删除的缴款凭证号不存在时也不抛异常
   */
  public void deleteSbInfoWithSbbh(String sbbh, Connection conn) throws
      Exception {
    log(
        "=======================deleteSbInfoWithSbbh start========================");
    if (sbbh == null || NULL_ZERO.equals(sbbh)) {
      throw new Exception("入口参数非法(sbbh=" + sbbh + ")");
    }
    String jsjdm=sbbh.substring(0,8);
    try {
      Statement st = conn.createStatement();
      //检查该笔申已入库的数据是否存在
      StringBuffer sqlBuffer = new StringBuffer();
      sqlBuffer.append("SELECT * FROM SBDB.SB_JL_SBJKZB WHERE sbbh='");
      sqlBuffer.append(sbbh);
      sqlBuffer.append("' AND jsjdm='");
      sqlBuffer.append(jsjdm);
      sqlBuffer.append("' AND ((zwbs NOT LIKE '0%') OR (zwbs NOT LIKE '%0'))");
      ResultSet rs = st.executeQuery(sqlBuffer.toString());
      if (rs.next()) {
        log(sbbh + "所涉及的申报缴款数据中有不合法的未缴款数据!");
        rs.close();
        st.close();
        throw new Exception(sbbh + "所涉及的申报缴款数据中有不合法的未缴款数据!");
      }
      rs.close();
      //删除明细和主表数据
      sqlBuffer = new StringBuffer();
      String sql = null;
      sqlBuffer.append("sbbh='" + sbbh + "'");
      sqlBuffer.append(" AND SJLY = '").append(SBJK_SJLY_NSPGLR)
          .append(
          "' AND (substr(zwbs, 1, 1) = '0' AND substr(zwbs, 20, 1) = '0')");
      String wheresql = sqlBuffer.toString(); //删除条件
      String sqlZb = wheresql; //主表条件
      String sqlMx = "JKPZH IN (SELECT JKPZH FROM SBDB.SB_JL_SBJKZB WHERE "
          + wheresql + ")";
      StringBuffer delBuffer = new StringBuffer();
      delBuffer.append("DELETE FROM SBDB.SB_JL_SBJKMX WHERE ").append(sqlMx);
      sql = delBuffer.toString();
      log(sql);
      st.addBatch(sql);
      delBuffer.setLength(0);
      delBuffer.append("DELETE FROM SBDB.SB_JL_SBJKZB WHERE ").append(sqlZb);
      sql = delBuffer.toString();
      log(sql);
      st.addBatch(sql);
      st.executeBatch(); //delete
      delBuffer.setLength(0); //clear
      sqlBuffer.setLength(0); //clear
    }
    catch (Exception ex) {
      ex.printStackTrace();
      throw new Exception(ex.getMessage() + "--delete data failed!");
    }
    log(
        "=======================deleteSbInfoWithSbbh end========================");
  }

  private void log(String str) {
    System.out.println("[WSSB FOR NSPG INTERFACE (" + (new Date()) + ")]" + str);
  }

  private String getCurYearStr() {
    Date date = new Date();
    String year = String.valueOf(date.getYear() + 1900);
    year = year.substring(2, 4);
    return year;
  }

  //到税务机关征收方式代码
  private final static String FSDM_SM = "1";
  //数据来源，纳税评估录入  81
  public final static String SBJK_SJLY_NSPGLR = "81";
  //处理标记代码‘已申报’： ‘11’
  private final static String CLBJDM_YSB = "11";
  //缴款凭证查询分组每组最大数，不能超过1000
  private final static int MAX_COUNT = 900;
  //联网状态常量
  private final static String LWZT_LW = "1";
  //""值常量
  private final static String NULL_ZERO = "";
  //"null"值常量
  private final static String NULL_STR = "null";

}
