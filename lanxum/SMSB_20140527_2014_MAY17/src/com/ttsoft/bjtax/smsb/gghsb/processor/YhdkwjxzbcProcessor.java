/*
 * <p>Title:北京地税核心征管系统--上门申报</p>
 * <p>Copyright:  (C) 2003-2004 北京市地方税务局，清华紫光股份有限公司版权所有.</p>
 * <p>Company: 清华紫光股份有限公司</p>
 */
package com.ttsoft.bjtax.smsb.gghsb.processor;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.bjtax.shenbao.model.domain.Yhkkxx;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkmx;
import com.ttsoft.bjtax.shenbao.model.domain.Yhkkhzxx;
import com.ttsoft.bjtax.shenbao.model.domain.GtgshZt;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.JksUtil;
import com.ttsoft.bjtax.smsb.model.client.Ysjc;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.gghsb.web.YhdkwjxzbcForm;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBUtils;
import com.ttsoft.bjtax.sfgl.common.db.util.BeanUtil;
import com.ttsoft.bjtax.sfgl.common.code.CodeUtils;
import com.ttsoft.bjtax.sfgl.common.util.SfTimeUtil;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.jikuai.zwcl.vo.page.Yskm;
import com.ttsoft.bjtax.jikuai.zwcl.inf.JKAdapter;
import com.ttsoft.bjtax.jikuai.zwcl.exception.ZwclException;
import com.ttsoft.bjtax.sfgl.common.util.LabelValueBean;
import com.ekernel.db.or.ORPrimaryKey;
import com.ttsoft.bjtax.smsb.gghsb.GghConstant;
//import java.sql.*;
import com.ttsoft.bjtax.sfgl.common.util.SfStringUtils;

/**
 * <p>Title: 北京地税核心征管系统--上门申报--国地共管户</p>
 * <p>Description: 银行扣款文件生成上传Processor</p>
 * @author 开发六组－－诸光林
 * @version 1.0
 */
public class YhdkwjxzbcProcessor
    implements Processor {
  public YhdkwjxzbcProcessor() {
  }

  /**
   * 通用处理调度模块
   * @param vo 数据集对象（包括Form和UserData对象）
   * @return object
   * @throws com.ttsoft.framework.exception.BaseException
   */
  public Object process(VOPackage vo) throws com.ttsoft.framework.exception.
      BaseException {
    Object result = null;

    if (vo == null) {
      throw new NullPointerException();
    }

    switch (vo.getAction()) {
      case CodeConstant.SMSB_SAVEACTION:
        result = doSave(vo);
        break;
      case CodeConstant.SMSB_SHOWACTION:
        result = doShow(vo);
        break;
      case CodeConstant.SMSB_QUERYACTION:
        result = doQuery(vo);
        break;
      case CodeConstant.SMSB_UPDATEACTION:
        result = doUpdate(vo);
        break;
      default:
        throw new UnsupportedOperationException(
            "Method process() not yet implemented.");
    }
    return result;
  }

  /**
   * 页面显示准备
   * @param vo 数据集对象（包括Form和UserData对象）
   * @return object
   * @throws BaseException
   */
  private Object doShow(VOPackage vo) throws BaseException {
    ResourceBundle rb = ResourceBundle.getBundle(
        "com.ttsoft.bjtax.smsb.ApplicationResources");
    String qxdmOfBccb = rb.getString("gghsb.qxdm.bccb");
    String qxdmOfNxs = rb.getString("gghsb.qxdm.nxs");
    String qxdms = qxdmOfBccb + "," + qxdmOfNxs;
    Connection conn = null;
    YhdkwjxzbcForm form = (YhdkwjxzbcForm) vo.getData();
    form.setQxdmOfBccb(qxdmOfBccb);
    form.setQxdmOfNxs(qxdmOfNxs);
    UserData userData = vo.getUserData();
    //区县代码
    String iQxdm = InterfaceDj.getQxdm(userData);
    List qxList = new ArrayList();
    List nyList = new ArrayList();
    try {
      conn = SfDBResource.getConnection();
      SfDBAccess da = new SfDBAccess(conn);
      StringBuffer sqlBuffer = new StringBuffer();
      if (iQxdm.equals(GghConstant.QXDM_SJ)) { //市局可以生成全部区县局的数据
        sqlBuffer.append("SELECT SWJGZZJGMC DESCR, SWJGZZJGDM CODE FROM")
            .append(" DMDB.GY_DM_SWJGZZJG WHERE SWJGZZJGDM IN (" + qxdms + ")")
            .append(" ORDER BY SWJGZZJGDM");
      }
      else {
        sqlBuffer.append("SELECT SWJGZZJGMC DESCR, SWJGZZJGDM CODE FROM")
            .append(" DMDB.GY_DM_SWJGZZJG WHERE SWJGZZJGDM = '" + iQxdm + "00'")
            .append(" ORDER BY SWJGZZJGDM");
      }
      try {
        ResultSet rs = da.querySQL(sqlBuffer.toString());
        while (rs.next()) {
          LabelValueBean bean = new LabelValueBean(rs.getString("DESCR"),
              rs.getString("CODE"));
          qxList.add(bean);
        }
        if (rs != null) {
          rs.close();
        }
      }
      catch (Exception ex) {
        throw new ApplicationException("获取区县局列表失败");
      }

      //生成年月列表      
      String year = SfDateUtil.getDate().substring(0, 4);
      String month = SfDateUtil.getDate().substring(4, 6);
      if (iQxdm.equals(GghConstant.QXDM_SJ)) { //市局可以生成上月月数据 
		//    ********* 上月月数据 ********
		  if (month.equals("01")) {
		    String iYear = String.valueOf(Integer.parseInt(year) + 10000-1);
		    String tyear = iYear.substring(iYear.length() - 4, iYear.length());
		    String tmonth = "12";
		    
		    nyList.add(new LabelValueBean(tyear + "年" + tmonth + "月", tyear + tmonth));
		  }
		  else {
		    String iMonth = String.valueOf(Integer.parseInt(month) + 100-1);
		    String tmonth = iMonth.substring(iMonth.length() - 2, iMonth.length());
		    
		    nyList.add(new LabelValueBean(year + "年" + tmonth + "月", year + tmonth));
		  }
      }
      
      
      
      //********  本月数据 ********
//      if (!iQxdm.equals("90")) {
//        //非市局用户 判断是否银行已取本月数据 有则 本月不可再生成文件
//        StringBuffer queryYhBuf1 = new StringBuffer(
//            "SELECT * FROM SFDB.SF_JL_GTGSH_ZT WHERE QXDM='")
//            .append(iQxdm).append("' AND ND='").append(year)
//            .append("' AND YD='").append(month).append("'");
//        ResultSet rs1 = da.querySQL(queryYhBuf1.toString());
//        if (rs1.next()) {
//          if (rs1.getString("yhqzt").equals("N")) {
//            nyList.add(new LabelValueBean(year + "年" + month + "月",
//                                          year + month));
//            form.setYscNY(year + month);
//          }
//
//        }
//        else {
//          nyList.add(new LabelValueBean(year + "年" + month + "月", year + month));
//        }
//
//        rs1.close();
//      }
//      else {
      if (! (year + month).equals("200501")) {
        nyList.add(new LabelValueBean(year + "年" + month + "月", year + month));
      }
//      }

      //********* 下月数据 ********
       if (month.equals("12")) {
         String iYear = String.valueOf(Integer.parseInt(year) + 10001);
         year = iYear.substring(iYear.length() - 4, iYear.length());
         month = "01";
       }
       else {
         String tMonth = String.valueOf(Integer.parseInt(month) + 101);
         month = tMonth.substring(tMonth.length() - 2, tMonth.length());
       }

//      if (!iQxdm.equals("90")) {
//        //非市局用户 判断是否银行已取前月数据 有则 前月不可再生成文件
//        StringBuffer queryYhBuf2 = new StringBuffer(
//            "SELECT * FROM SFDB.SF_JL_GTGSH_ZT WHERE QXDM='")
//            .append(iQxdm).append("' AND ND='").append(year)
//            .append("' AND YD='").append(month).append("'");
//        ResultSet rs2 = da.querySQL(queryYhBuf2.toString());
//        if (rs2.next()) {
//          if (rs2.getString("yhqzt").equals("N")) {
//            nyList.add(new LabelValueBean(year + "年" + month + "月",
//                                          year + month));
//            if (!form.getYscNY().equals("")) {
//              form.setYscNY(form.getYscNY() + "|" + year + month);
//            }
//            else {
//              form.setYscNY(year + month);
//            }
//          }
//        }
//        else {
//          nyList.add(new LabelValueBean(year + "年" + month + "月", year + month));
//        }
//
//        rs2.close();
//      }
//      else {
      nyList.add(new LabelValueBean(year + "年" + month + "月", year + month));
//      }

      form.setNyList(nyList);
      form.setQxList(qxList);
      return form;
    }
    catch (Exception ex) {
      ex.printStackTrace();
      throw ExceptionUtil.getBaseException(ex);
    }
    finally {
      SfDBResource.freeConnection(conn);
    }
  }

  /**
   * 获取已下载文件名列表
   * @param vo 数据集对象（包括Form和UserData对象）
   * @return object
   * @throws BaseException
   */
  private Object doQuery(VOPackage vo) throws BaseException {
    Connection conn = null;
    YhdkwjxzbcForm form = (YhdkwjxzbcForm) vo.getData();
    try {
      conn = SfDBResource.getConnection();
      SfDBAccess da = new SfDBAccess(conn);
      StringBuffer sqlBuf = new StringBuffer(
          "SELECT YDKKWJMC FROM SFDB.SF_JL_GTGSH_ZT ")
          .append(" WHERE QXDM='").append(form.getQxdm().substring(0, 2))
          .append("' AND ND='").append(form.getNd())
          .append("' AND YD ='").append(form.getYd())
          .append("'");
      ResultSet rs = null;
      try {
        rs = da.querySQL(sqlBuf.toString());
      }
      catch (BaseException ex1) {
        throw new ApplicationException("获取已下载文件名列表失败");
      }
      String[] fileNames = null;
      String fileName = null;

      if (rs.next()) {
        fileName = rs.getString("YDKKWJMC");
        System.out.println(" [银行代扣文件下载] 已下载过的文件名列表:" + fileName);
        if (fileName != null) {
          fileNames = SfStringUtils.split(fileName, ",");
        }
        else {
        }
      }
      rs.close();
      form.setFileNames(fileNames);
      return form;
    }
    catch (Exception ex) {
      ex.printStackTrace();
      throw ExceptionUtil.getBaseException(ex);
    }
    finally {
      SfDBResource.freeConnection(conn);
    } //end try
  }

  /**
   * 变更 状态表里获取文件状态
   * @param vo 数据集对象（包括Form和UserData对象）
   * @return object
   * @throws BaseException
   */
  private Object doUpdate(VOPackage vo) throws BaseException {
    Connection conn = null;
    YhdkwjxzbcForm form = (YhdkwjxzbcForm) vo.getData();
    try {
      String updateStr = null;
      // 1. 上次已取文件数大于0,表示本次不是第一次取
      if (form.getFileNames() != null && form.getFileNames().length > 0) {
        //全部取状态都为成功
        updateStr = " YCKKZT='Y', ECKKZT='Y'";
      }
      // 2. 上次已取文件数不大于0,表示本次是第一次取
      else {
        // 2.1 本次获得文件数不大于2,表示本次只取到第一次扣款信息
        if (form.getFileCount() <= 2) {
          updateStr = " YCKKZT='Y'";
        }
        // 2.2 本次获得文件数大于2,表示本次取到多次次扣款信息
        else {
          updateStr = " YCKKZT='Y', ECKKZT='Y'";
        }
      }
      conn = SfDBResource.getConnection();
      SfDBAccess da = new SfDBAccess(conn);
      StringBuffer sqlBuf = new StringBuffer(
          "UPDATE SFDB.SF_JL_GTGSH_ZT")
          .append(" SET").append(updateStr)
          .append(" WHERE QXDM='").append(form.getQxdm().substring(0, 2))
          .append("' AND ND='").append(form.getNd())
          .append("' AND YD ='").append(form.getYd())
          .append("'");
      try {
        da.updateSQL(sqlBuf.toString());
      }
      catch (BaseException ex1) {
        throw new ApplicationException("更新状态表(获取文件状态)失败");
      }
      return form;
    }
    catch (Exception ex) {
      ex.printStackTrace();
      throw ExceptionUtil.getBaseException(ex);
    }
    finally {
      SfDBResource.freeConnection(conn);
    } //end try
  }

  /**
   * 保存银行扣款反馈信息
   * @param vo 数据集对象（包括Form和UserData对象）
   * @return object
   * @throws BaseException
   */
  private Object doSave(VOPackage vo) throws BaseException {
    Connection conn = null;
    ResultSet rs = null;
    YhdkwjxzbcForm form = (YhdkwjxzbcForm) vo.getData();
    UserData userData = vo.getUserData();
    List xxList = new ArrayList();
    String count = "0"; //总笔数
    String totalAmount = "0"; //总金额
    String errorCount = "0"; //失败笔数
    String errortotalAmount = "0"; //失败金额
    Timestamp systime = SfTimeUtil.getNowTimestamp();
    String ssny = ""; // 所属年月
    String ssnd = ""; // 所属年度
    String ssyd = ""; // 所属月度
    if (form.getYd().equals("01")) {
      ssnd = String.valueOf(Integer.parseInt(form.getNd()) + 9999).substring(1,
          5);
      ssyd = "12";
    }
    else {
      ssnd = form.getNd();
      ssyd = String.valueOf(Integer.parseInt(form.getYd()) + 99).substring(1, 3);
    }
    ssny = ssnd + ssyd;

    //当前时间
    try {
      conn = SfDBResource.getConnection();
      SfDBAccess da = new SfDBAccess(conn);
      HashMap zqrlMap = new HashMap();

      // 1. 取征期日历的hashmap
      StringBuffer sqlZqrl = new StringBuffer(
          "SELECT A.SZSMDM,A.DJZCLXDM,A.ZQZZRQ FROM SBDB.SB_JL_ZQRL A ")
          .append(" WHERE  A.ND = '").append(ssnd)
          .append("' AND A.ZQQSRQ <= to_date('").append(ssny)
          .append("','yyyyMM')")
          .append(" AND A.ZQZZRQ >= to_date('").append(ssny)
          .append("','yyyyMM')");
      try {
        rs = da.querySQL(sqlZqrl.toString());
        while (rs.next()) {
          zqrlMap.put(rs.getString("SZSMDM") + rs.getString("DJZCLXDM"),
                      rs.getTimestamp("ZQZZRQ"));
        }
        rs.close();
      }
      catch (Exception ex1) {
        throw new ApplicationException("获取征期日历失败");
      }

      // 2.

      // 3. 解析从ftp得到的封装数据
      String key = ""; //保存需要查询的KEY值
      HashMap ftpMap = form.getFtpMap(); //从ftp得到的封装数据
      ArrayList tempList = new ArrayList(); //存放按时间先后排好次序的文件名
      Iterator keyIterator = ftpMap.keySet().iterator(); //本次取得文件名的集合

      // 3.1 得到按时间先后排好次序的文件名列表
      while (keyIterator.hasNext()) {
        key = keyIterator.next().toString(); //文件名
        int j = 0; //插入的位置
        boolean xiaoyu = false; //判断文件早晚的标识
        for (int i = 0; i < tempList.size(); i++) {
          //判断文件的早晚  取文件名中间部分的 日期比较 "TM2004110616.20041206.txt"
          if (Long.parseLong(key.substring(13, 21)) <
              Long.parseLong(tempList.get(i).toString().substring(13, 21))) {
            xiaoyu = true;
            j = i;
            break;
          }
        }
        if (xiaoyu) {
          tempList.add(j, key); //插到位置 j
        }
        else {
          tempList.add(key); //插到最后
        }
      }

      // 3.2 按文件名先后次序 取数据
      StringBuffer cgxx = new StringBuffer("该征期的银行扣款文件下载幷保存成功：");
      for (int i = 0; i < tempList.size(); i++) {
        String fileName = tempList.get(i).toString();

        // 3.2.1 汇总文件
        if (fileName.substring(0, 2).equals(GghConstant.FILE_PREFIX_HZ)) { //
          System.out.println(" [银行代扣文件下载] 汇总文件:" + fileName);
          HashMap hzMap = (HashMap) (ftpMap.get(fileName));
          String countStr = hzMap.get("COUNT").toString();
          int kk = countStr.indexOf(".");
          if (kk >= 0) {
            countStr = countStr.substring(0, kk);
          }
          count = Integer.valueOf(countStr).toString();
          totalAmount = hzMap.get("TOTALAMOUNT").toString();
          String errorCountStr = hzMap.get("ERRORCOUNT").toString();
          kk = errorCountStr.indexOf(".");
          if (kk >= 0) {
            errorCountStr = errorCountStr.substring(0, kk);
          }
          errorCount = Integer.valueOf(errorCountStr).toString();
          errortotalAmount = hzMap.get("ERRORTOTALAMOUNT").toString();
          cgxx.append("<br>&nbsp;&nbsp;&nbsp;扣款日期" +
                      fileName.substring(13, 21))
              .append("，总笔数").append(count)
              .append("，总金额").append(totalAmount)
              .append("元，失败总笔数").append(errorCount)
              .append("，失败总金额").append(errortotalAmount).append("元");
          // 更新状态表
          StringBuffer updateZt1 = new StringBuffer(
              "UPDATE SFDB.SF_JL_GTGSH_ZT SET")
              .append(" CWZJLS =").append(errorCount)
              .append(", CWZJE=").append(errortotalAmount)
              .append(", LRR='").append(userData.getYhid())
              .append("', LRRQ=to_date('")
              .append(systime.toString().substring(0, 19))
              .append("', 'yyyy-mm-dd hh24:mi:ss')")
              .append(", YDKKWJMC=YDKKWJMC||'").append(fileName).append(",'")
              .append(" WHERE QXDM='").append(form.getQxdm().substring(0, 2))
              .append("' AND ND='").append(form.getNd())
              .append("' AND YD='").append(form.getYd())
              .append("'");
          try {
            da.updateSQL(updateZt1.toString());
          }
          catch (BaseException ex2) {
            throw new ApplicationException("更新状态表(汇总)失败");
          }
        }

        // 3.2.2 明细文件
        else if (fileName.substring(0, 2).equals(GghConstant.FILE_PREFIX_MX)) {
          System.out.println(" [银行代扣文件下载] 明细文件:" + fileName);
          // 3.2.2.1 更新状态表 (已获取文件名称)
          StringBuffer updateZt2 = new StringBuffer(
              "UPDATE SFDB.SF_JL_GTGSH_ZT SET")
              .append(" YDKKWJMC=YDKKWJMC||'").append(fileName).append(",'")
              .append(" WHERE QXDM='").append(form.getQxdm().substring(0, 2))
              .append("' AND ND='").append(form.getNd())
              .append("' AND YD='").append(form.getYd())
              .append("'");
          try {
            da.updateSQL(updateZt2.toString());
          }
          catch (BaseException ex3) {
            throw new ApplicationException("更新状态表(明细)失败");
          }

          List yhkkxxlist = (List) ftpMap.get(fileName);
          //如果明细没有内容,跳出本次大循环
          if (yhkkxxlist.size() <= 0) {
            continue;
          }

          // 申报编号：扣款信息所属时间 + 区县 + 银行 + '.' + 扣款信息回执日期
          String sbbh = fileName.substring(2, 21);
          long time1 = System.currentTimeMillis();
          // 3.2.2.2 逐条保存申报信息
          PreparedStatement updatePS = null; //更新银行下载回执表ps
          PreparedStatement insertZbPS = null; //保存申报缴款主表ps
          PreparedStatement insertMxPS = null; //保存申报缴款明细表ps

          // 3.2.2.2.1 更新银行下载回执表
          StringBuffer updateZtSql = new StringBuffer(
              // 1.KKBZ, 2.KKRQ, 3.KKDWDM, 4.RKJE, 5.BCGYYDM, 6.JKPZH
              "UPDATE SFDB.SF_JL_GTGSH_YHKKHZXX SET KKBZ=?, KKRQ=?, KKDWDM=?, RKJE=?,")
              .append(" BCGYYDM=?, LRR='")
              .append(userData.getYhid()).append("', LRRQ=to_date('")
              .append(systime.toString().substring(0, 19))
              .append("', 'yyyy-mm-dd hh24:mi:ss') WHERE JKPZH=?");
          updatePS = conn.prepareStatement(updateZtSql.toString());

          Yhkkhzxx hzxx4PS = new Yhkkhzxx();
          hzxx4PS = (Yhkkhzxx) yhkkxxlist.get(0);
          // 3.2.2.2.2 保存申报缴款主表
          StringBuffer insertZbSql = new StringBuffer("insert into SBDB.SB_JL_SBJKZB (JKPZH, SKLXDM, JSJDM, FSDM, LSGXDM, YHDM, YHMC, ZH, DJZCLXDM, SWJGZZJGDM, ZSSWJGZZJGDM, GJBZHYDM, GKZZJGDM, YSKMDM, YSJCDM, SZDM, LRRQ, SBRQ, JKSJ, XJRQ, CLBJDM, SJJE, ZYRQ, RKJE, ZWBS, HXRDM, HXRMC, LRR, BZ, HXRQ, SBBH, JYDZLXDM, SKSSKSRQ, SKSSJSRQ, SJLY, ND, CJRQ, QXDM ,SPHM) values (")
              // 1.JKPZH, SKLXDM, 2.JSJDM, FSDM, 3.LSGXDM,
              .append(" ?, '").append(CodeConstant.SKLXDM_4DJJ)
              .append("', ?, '").append(CodeConstant.FSDM_QTFS)
              .append("', ?,")
              //YHDM, YHMC, 4.ZH, 5.DJZCLXDM, 6.SWJGZZJGDM,
              .append(" '").append(hzxx4PS.getYhdm()).append("', '").append(
              CodeUtils.getCodeBeanLabel("DM_YH", hzxx4PS.getYhdm())).append(
              "', ?, ?, ?,")
              // 7.ZSSWJGZZJGDM, 8.GJBZHYDM, 9.GKZZJGDM, 10.YSKMDM, 11.YSJCDM,
              .append(" ?, ?, ?, ?, ?,")
              // 12.SZDM, LRRQ, 13.SBRQ, 14.JKSJ, 15.XJRQ,
              .append(" ?, to_date('").append(systime.toString().substring(0,
              19)).append("','yyyy-mm-dd hh24:mi:ss'), ?, ?, ?,")
              //CLBJDM, 16.SJJE, ZYRQ, 17.RKJE, ZWBS,
              .append(" '").append(CodeConstant.CLBJDM_YSB)
              .append("', ?, to_date('")
              .append(systime.toString().substring(0, 19)).append(
              "','yyyy-mm-dd hh24:mi:ss'), ?, '")
              .append("00000000000000000091").append("',")
              //HXRDM, HXRMC, LRR, BZ, HXRQ,
              .append(" null, null, '").append(userData.getYhid())
              .append("', null, null,")
              //SBBH, 18.JYDZLXDM, SKSSKSRQ, SKSSJSRQ, SJLY,
              .append(" '").append(sbbh)
              .append("', ?, to_date('")
              .append(hzxx4PS.getSkssksrq().toString().substring(0, 10))
              .append("','yyyy-mm-dd'), to_date('")
              .append(hzxx4PS.getSkssjsrq().toString().substring(0, 10))
              .append("','yyyy-mm-dd'), '")
              .append(CodeConstant.SJLY_DQDEDR).append("',")
              //ND, CJRQ, QXDM
              .append("'").append(form.getNd()).append("', to_date('")
              .append(systime.toString().substring(0, 19)).append(
              "','yyyy-mm-dd hh24:mi:ss'), '")
              .append(form.getQxdm().substring(0, 2)).append("', ?").append(")");
          insertZbPS = conn.prepareStatement(insertZbSql.toString());

          // 3.2.2.2.3 保存申报缴款明细表
          StringBuffer insertMxSql = new StringBuffer("insert into SBDB.SB_JL_SBJKMX (SZSMDM, JKPZH, JSJDM, YSKMDM, YSJCDM, KSSL, JSJE, SJSE, SKSSKSRQ, SKSSJSRQ, RKJE, SBBH, SJFC, QJFC, SWJGZZJGDM, ND, SL, CJRQ, LRRQ, QXDM) values (")
              // 1.SZSMDM, 2.JKPZH, 3.JSJDM, 4.YSKMDM, 5.YSJCDM,
              .append(" ?, ?, ?, ?, ?,")
              //KSSL, 6.JSJE, 7.SJSE, SKSSKSRQ, SKSSJSRQ,
              .append(" 0, ?, ?,  to_date('")
              .append(hzxx4PS.getSkssksrq().toString().substring(0, 10))
              .append("','yyyy-mm-dd'), to_date('")
              .append(hzxx4PS.getSkssjsrq().toString().substring(0, 10))
              .append("','yyyy-mm-dd'),")
              // 8.RKJE, SBBH, 9.SJFC, 10.QJFC, 11.SWJGZZJGDM,
              .append(" ?, '").append(sbbh).append("', ?, ?, ?, '")
              //ND, SL, CJRQ, LRRQ, QXDM
              .append(form.getNd()).append("', null, to_date('")
              .append(systime.toString().substring(0, 19))
              .append("','yyyy-mm-dd hh24:mi:ss'), to_date('")
              .append(systime.toString().substring(0, 19)).append(
              "','yyyy-mm-dd hh24:mi:ss'), '")
              .append(form.getQxdm().substring(0, 2)).append("')");
          insertMxPS = conn.prepareStatement(insertMxSql.toString());

          //小循环(针对一个明细文件)
          long iCount = 0;
          for (int j = 0; j < yhkkxxlist.size(); j++, iCount++) {

            Yhkkhzxx yhkkhzxx = new Yhkkhzxx();
            yhkkhzxx = (Yhkkhzxx) yhkkxxlist.get(j);

            boolean successFlag = true; //扣款成功标识
            if (!yhkkhzxx.getKkbz().equals(GghConstant.KKBZ_1_SUCCESS) && //一次成功
                !yhkkhzxx.getKkbz().equals(GghConstant.KKBZ_2_SUCCESS)) { //二次成功
              successFlag = false;
            }

            // [A]. 更新扣款信息
            updatePS.setString(1, yhkkhzxx.getKkbz());
            updatePS.setTimestamp(2, yhkkhzxx.getKkrq());
            updatePS.setString(3, yhkkhzxx.getKkdwdm());
            if (successFlag) {
              updatePS.setDouble(4, yhkkhzxx.getRkje());
            }
            else {
              updatePS.setDouble(4, 0);
            }
            updatePS.setString(5, yhkkhzxx.getBcgyydm());
            updatePS.setString(6, yhkkhzxx.getJkpzh());
            updatePS.addBatch();

            // [B]. 如果扣款成功，则保存该记录的缴款信息
            if (successFlag) {
              // 1. 保存申报缴款主表信息
              ORPrimaryKey pKey = new ORPrimaryKey(yhkkhzxx, yhkkhzxx.getJkpzh());
              Yhkkhzxx savedKkhzxx = (Yhkkhzxx) da.query(pKey);
              if (savedKkhzxx == null) {
                throw new ApplicationException("银行的扣款回执信息与原始信息不一致");
              }

              insertZbPS.setString(1, savedKkhzxx.getJkpzh());
              insertZbPS.setString(2, savedKkhzxx.getJsjdm());
              insertZbPS.setString(3, savedKkhzxx.getLsgxdm());
              insertZbPS.setString(4, savedKkhzxx.getZh());
              insertZbPS.setString(5, savedKkhzxx.getDjzclxdm());
              insertZbPS.setString(6, savedKkhzxx.getSwjgzzjgdm());
              insertZbPS.setString(7, yhkkhzxx.getKkdwdm());
              insertZbPS.setString(8, savedKkhzxx.getGjbzhydm());
              insertZbPS.setString(9, savedKkhzxx.getGkzzjgdm());
              insertZbPS.setString(10, savedKkhzxx.getYskmdm());
              insertZbPS.setString(11, savedKkhzxx.getYsjcdm());
              insertZbPS.setString(12, savedKkhzxx.getSzsmdm().substring(0, 2));
              insertZbPS.setTimestamp(13, yhkkhzxx.getKkrq());
              insertZbPS.setTimestamp(14, yhkkhzxx.getKkrq());
              insertZbPS.setTimestamp(15, getXjrq(zqrlMap,
                                                  savedKkhzxx.getSzsmdm() +
                                                  savedKkhzxx.getDjzclxdm(),
                                                  ssny));
              insertZbPS.setDouble(16, savedKkhzxx.getSjje());
              insertZbPS.setDouble(17, yhkkhzxx.getRkje());
              insertZbPS.setString(18, savedKkhzxx.getJydzlxdm());
              insertZbPS.setString(19, savedKkhzxx.getJkpzh());
              insertZbPS.addBatch();

              // 2. 保存申报缴款明细信息
              insertMxPS.setString(1, savedKkhzxx.getSzsmdm());
              insertMxPS.setString(2, savedKkhzxx.getJkpzh());
              insertMxPS.setString(3, savedKkhzxx.getJsjdm());
              insertMxPS.setString(4, savedKkhzxx.getYskmdm());
              insertMxPS.setString(5, savedKkhzxx.getYsjcdm());
              insertMxPS.setDouble(6, savedKkhzxx.getSjrd());
              insertMxPS.setDouble(7, savedKkhzxx.getSjje());
              insertMxPS.setDouble(8, yhkkhzxx.getRkje());
              insertMxPS.setBigDecimal(9, getFc(new BigDecimal(yhkkhzxx.getRkje()),
                                                new BigDecimal(savedKkhzxx.
                  getSjfc())));
              insertMxPS.setBigDecimal(10,
                                       getFc(new BigDecimal(yhkkhzxx.getRkje()),
                                             new BigDecimal(savedKkhzxx.
                  getQjfc())));
              insertMxPS.setString(11, savedKkhzxx.getSwjgzzjgdm());
              insertMxPS.addBatch();
            }
            if (iCount >= 30) {
              try {
                updatePS.executeBatch();
                updatePS.clearBatch();
              }
              catch (SQLException ex8) {
                ex8.printStackTrace();
                throw new ApplicationException("更新入库金额失败:" + ex8.getMessage());
              }
              try {
                insertZbPS.executeBatch();
                insertZbPS.clearBatch();
              }
              catch (SQLException ex9) {
                ex9.printStackTrace();
                throw new ApplicationException("保存扣款信息出错:" + ex9.getMessage());
              }
              try {
                insertMxPS.executeBatch();
                insertMxPS.clearBatch();
              }
              catch (SQLException ex10) {
                ex10.printStackTrace();
                throw new ApplicationException("保存扣款明细信息出错:" + ex10.getMessage());
              }
//              System.gc();
              iCount = 0;
            }
          } //end for 详细信息小循环
          yhkkxxlist = null;
          System.gc();
          try {
            updatePS.executeBatch();
            updatePS.clearBatch();
            updatePS.close();
          }
          catch (SQLException ex4) {
            ex4.printStackTrace();
            throw new ApplicationException("更新入库金额失败:" + ex4.getMessage());
          }

          try {
            insertZbPS.executeBatch();
            insertZbPS.clearBatch();
            insertZbPS.close();
          }
          catch (SQLException ex5) {
            ex5.printStackTrace();
            throw new ApplicationException("保存扣款信息出错:" + ex5.getMessage());
          }

          try {
            insertMxPS.executeBatch();
            insertMxPS.clearBatch();
            insertMxPS.close();
          }
          catch (SQLException ex6) {
            ex6.printStackTrace();
            throw new ApplicationException("保存扣款明细信息出错:" + ex6.getMessage());
          }

          System.gc();
          long time2 = System.currentTimeMillis();
          System.out.println(" [银行代扣文件下载] 保存明细信息耗时:  " + (time2 - time1) +
                             " 毫秒");
        }
      } //end for 大循环
      System.gc();
      // 3.3 如果上次已取文件数小于4, 则需要变更 状态表里获取文件状态
      if (form.getFileNames() == null || form.getFileNames().length < 4) {
        String updateStr = null;
        // 3.3.1. 上次已取文件数大于0,表示本次不是第一次成功取
        if (form.getFileNames() != null && form.getFileNames().length > 0) {
          //全部取内容状态都为成功
          updateStr = " YCKKNRZT='Y', ECKKNRZT='Y'";
        }
        // 3.3.2. 上次已取文件数不大于0,表示本次是第一次成功取
        else {
          // 3.3.2.1 本次获得文件数不大于2,表示本次只成功取到第一次扣款信息
          if (form.getFileCount() <= 2) {
            updateStr = " YCKKNRZT='Y'";
          }
          // 3.3.2.2 本次获得文件数大于2,表示本次成功取到多次次扣款信息
          else {
            updateStr = " YCKKNRZT='Y', ECKKNRZT='Y'";
          }
        }
        StringBuffer sqlUpdateZt = new StringBuffer(
            "UPDATE SFDB.SF_JL_GTGSH_ZT")
            .append(" SET").append(updateStr)
            .append(" WHERE QXDM='").append(form.getQxdm().substring(0, 2))
            .append("' AND ND='").append(form.getNd())
            .append("' AND YD ='").append(form.getYd())
            .append("'");
        try {
          da.updateSQL(sqlUpdateZt.toString());
        }
        catch (BaseException ex7) {
          ex7.printStackTrace();
          throw new ApplicationException("更新状态表(文件内容获取状态)失败:" + ex7.getMessage());
        }
      }
      form.setCgxx(cgxx.toString());
      System.gc();
      return form;
    }
    catch (Exception ex) {
      ex.printStackTrace();
      throw ExceptionUtil.getBaseException(ex);
    }
    finally {
      SfDBResource.freeConnection(conn);
    } //end try
  }

  /**
   * 计算限缴日期
   * @param map
   * @param key
   * @param ny
   * @return
   */
  private Timestamp getXjrq(HashMap map, String key, String ny) {
    if (map.get(key) == null) {
      return SfTimeUtil.getTimestamp(ny + "15"); //同上门申报
    }
    return (Timestamp) map.get(key);
  }

  /**
   * 计算分成金额
   * @param je 实缴金额
   * @param bl 分成比例（默认是0.00）
   * @return 分成金额(保留4位小数)，如果实缴金额或分成比例为null，则放回0
   */
  private BigDecimal getFc(BigDecimal je, BigDecimal bl) {
    //
    //计会接口没有返回分成比率
    if (je == null || bl == null) {
      return new BigDecimal(0);
    }
    BigDecimal fc = je.multiply(bl).setScale(4, BigDecimal.ROUND_HALF_UP);
    return fc;
  }

  public static void main(String[] args) {
  }
}