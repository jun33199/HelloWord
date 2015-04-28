package com.ttsoft.bjtax.smsb.gghsb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import com.ttsoft.bjtax.smsb.gghsb.vo.YhkkhzxxLw;
import com.ttsoft.bjtax.smsb.gghsb.vo.YhkkxxLw;
import com.ttsoft.bjtax.smsb.gghsb.vo.ZtLw;
import java.math.BigDecimal;

/**
 * <p>Title: 北京地税核心征管系统-上门申报</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: SYAX</p>
 * @author Ha Zhengze
 * @version 1.0
 */

public class DataAccess {

  public DataAccess() {
  }

  /**
   * 插入银行扣款信息表
   * @param vos 数据集
   * @param conn 数据库连接
   */
  public void insertYhkkxxLwData(List vos, Connection conn) throws Exception {
    YhkkxxLw vo = null;
    PreparedStatement st = null;
    StringBuffer buf = new StringBuffer();
    buf.append("INSERT INTO SFDB.SF_JL_GTGSH_YHKKXX_LW(");
    buf.append("JKPZH, SPHM, BIZPH,");
    buf.append("JSJDM, NSRMC, YHDM,");
    buf.append("ZH, SZSMDM, SZSMMC,");
    buf.append("SJRD, SJJE, SKSSKSRQ,");
    buf.append("SKSSJSRQ, YSJCDM, YSJCMC,");
    buf.append("YSKMDM, GKZZJGDM, GKZZJGMC,");
    buf.append("SWJGZZJGDM, SWJGZZJGMC, SCJXDM,");
    buf.append("QXDM, ND, YD,");
    buf.append("GJBZHYDM, DJZCLXDM, JYDZLXDM,");
    buf.append("LSGXDM, CJR, CJRQ,");
    buf.append("LRR, LRRQ)");
    buf.append(" VALUES(?, ?||LPAD(SBDB.SEQ_SB_SPHM.NEXTVAL,8,'0'), ?,");
    buf.append("?, ?, ?,");
    buf.append("?, ?, ?,");
    buf.append("?, ?, ?,");
    buf.append("?, ?, ?,");
    buf.append("?, ?, ?,");
    buf.append("?, ?, ?,");
    buf.append("?, ?, ?,");
    buf.append("?, ?, ?,");
    buf.append("?, ?, ?,");
    buf.append("?, ?)");
    try {
      st = conn.prepareStatement(buf.toString());
      int count = 0;
      for (int i = 0; i < vos.size(); i++) {
        vo = (YhkkxxLw) vos.get(i);
        st.setString(1, vo.getJkpzh());
        st.setString(2, vo.getSphm());
        st.setString(3, vo.getBizPh());
        st.setString(4, vo.getJsjdm());
        st.setString(5, vo.getNsrmc());
        st.setString(6, vo.getYhdm());
        st.setString(7, vo.getZh());
        st.setString(8, vo.getSzsmdm());
        st.setString(9, vo.getSzsmmc());
        st.setBigDecimal(10, vo.getSjrd());
        st.setBigDecimal(11, vo.getSjje());
        st.setTimestamp(12, vo.getSkssksrq());
        st.setTimestamp(13, vo.getSkssjsrq());
        st.setString(14, vo.getYsjcdm());
        st.setString(15, vo.getYsjcmc());
        st.setString(16, vo.getYskmdm());
        st.setString(17, vo.getGkzzjgdm());
        st.setString(18, vo.getGkzzjgmc());
        st.setString(19, vo.getSwjgzzjgdm());
        st.setString(20, vo.getSwjgzzjgmc());
        st.setString(21, vo.getScjxdm());
        st.setString(22, vo.getQxdm());
        st.setString(23, vo.getNd());
        st.setString(24, vo.getYd());
        st.setString(25, vo.getGjbzhydm());
        st.setString(26, vo.getDjzclxdm());
        st.setString(27, vo.getJydzlxdm());
        st.setString(28, vo.getLsgxdm());
        st.setString(29, vo.getCjr());
        st.setTimestamp(30, vo.getCjrq());
        st.setString(31, vo.getLrr());
        st.setTimestamp(32, vo.getLrrq());
        st.addBatch();
        count++;
        if (count == 1000) {
          st.executeBatch();
          st.clearBatch();
          count = 0;
        }
      }
      st.executeBatch();
      st.clearBatch();
      count = 0;
      System.gc();
    }
    catch (Exception e) {
      e.printStackTrace();
      throw new Exception(
          "Database record access errors, Table: SFDB.SF_JL_GTGSH_YHKKXX_LW");
    }
    finally {
      try {
        if (st != null)
          st.close();
      }
      catch (Exception ex) {
        ex.printStackTrace();
      }
    }
  }



  /**
   * 插入银行扣款信息表
   * @param vos 数据集
   * @param conn 数据库连接
   */
  public void insertYhkkhzxxToYhkkxxLwData(List vos, Connection conn) throws Exception {
    YhkkhzxxLw vo = null;
    PreparedStatement st = null;
    StringBuffer buf = new StringBuffer();
    buf.append("INSERT INTO SFDB.SF_JL_GTGSH_YHKKXX_LW(");
    buf.append("JKPZH, SPHM, BIZPH,");
    buf.append("JSJDM, NSRMC, YHDM,");
    buf.append("ZH, SZSMDM, SZSMMC,");
    buf.append("SJRD, SJJE, SKSSKSRQ,");
    buf.append("SKSSJSRQ, YSJCDM, YSJCMC,");
    buf.append("YSKMDM, GKZZJGDM, GKZZJGMC,");
    buf.append("SWJGZZJGDM, SWJGZZJGMC, SCJXDM,");
    buf.append("QXDM, ND, YD,");
    buf.append("GJBZHYDM, DJZCLXDM, JYDZLXDM,");
    buf.append("LSGXDM, CJR, CJRQ,");
    buf.append("LRR, LRRQ)");
    buf.append(" VALUES(?, ?||LPAD(SBDB.SEQ_SB_SPHM.NEXTVAL,8,'0'), ?,");
    buf.append("?, ?, ?,");
    buf.append("?, ?, ?,");
    buf.append("?, ?, ?,");
    buf.append("?, ?, ?,");
    buf.append("?, ?, ?,");
    buf.append("?, ?, ?,");
    buf.append("?, ?, ?,");
    buf.append("?, ?, ?,");
    buf.append("?, ?, ?,");
    buf.append("?, ?)");
    try {
      st = conn.prepareStatement(buf.toString());
      int count = 0;
      for (int i = 0; i < vos.size(); i++) {
        vo = (YhkkhzxxLw) vos.get(i);
        st.setString(1, vo.getJkpzh());
        st.setString(2, vo.getSphm());
        st.setString(3, vo.getBizph());
        st.setString(4, vo.getJsjdm());
        st.setString(5, vo.getNsrmc());
        st.setString(6, vo.getYhdm());
        st.setString(7, vo.getZh());
        st.setString(8, vo.getSzsmdm());
        st.setString(9, vo.getSzsmmc());
        st.setBigDecimal(10, vo.getSjrd());
        st.setBigDecimal(11, vo.getSjje());
        st.setTimestamp(12, vo.getSkssksrq());
        st.setTimestamp(13, vo.getSkssjsrq());
        st.setString(14, vo.getYsjcdm());
        st.setString(15, vo.getYsjcmc());
        st.setString(16, vo.getYskmdm());
        st.setString(17, vo.getGkzzjgdm());
        st.setString(18, vo.getGkzzjgmc());
        st.setString(19, vo.getSwjgzzjgdm());
        st.setString(20, vo.getSwjgzzjgmc());
        st.setString(21, vo.getScjxdm());
        st.setString(22, vo.getQxdm());
        st.setString(23, vo.getNd());
        st.setString(24, vo.getYd());
        st.setString(25, vo.getGjbzhydm());
        st.setString(26, vo.getDjzclxdm());
        st.setString(27, vo.getJydzlxdm());
        st.setString(28, vo.getLsgxdm());
        st.setString(29, vo.getCjr());
        st.setTimestamp(30, vo.getCjrq());
        st.setString(31, vo.getLrr());
        st.setTimestamp(32, vo.getLrrq());
        st.addBatch();
        count++;
        if (count == 1000) {
          st.executeBatch();
          st.clearBatch();
          count = 0;
        }
      }
      st.executeBatch();
      st.clearBatch();
      count = 0;
      System.gc();
    }
    catch (Exception e) {
      e.printStackTrace();
      throw new Exception(
          "Database record access errors, Table: SFDB.SF_JL_GTGSH_YHKKXX_LW");
    }
    finally {
      try {
        if (st != null)
          st.close();
      }
      catch (Exception ex) {
        ex.printStackTrace();
      }
    }
  }


  /**
   * 插入银行扣款回执信息表
   * @param vos 数据集
   * @param conn 数据库连接
   */
  public void insertYhkkhzxxLwData(List vos, Connection conn) throws Exception {
    YhkkhzxxLw vo = null;

    PreparedStatement st = null;
    StringBuffer buf = new StringBuffer();
    buf.append("INSERT INTO SFDB.SF_JL_GTGSH_YHKKHZXX_LW (");
    buf.append("JKPZH, SPHM, BIZPH, ");
    buf.append("JSJDM, NSRMC, YHDM, ");
    buf.append("ZH, SZSMDM, SZSMMC, ");
    buf.append("SJRD, SJJE, SKSSKSRQ, ");
    buf.append("SKSSJSRQ, YSJCDM, YSJCMC, ");
    buf.append("YSKMDM, GKZZJGDM, GKZZJGMC, ");
    buf.append("SWJGZZJGDM, SWJGZZJGMC, SCJXDM, ");
    buf.append("QXDM, ND, YD, ");
    buf.append("JYDZLXDM, GJBZHYDM, DJZCLXDM, ");
    buf.append("LSGXDM, RKJE, SJFC, ");
    buf.append("QJFC, KKBZ, KKRQ, ");
    buf.append("KKDWDM, BCGYYDM, CJR, ");
    buf.append("CJRQ, LRR, LRRQ, ");
    buf.append("FSZT,XYSHM) ");
    buf.append(" VALUES ( ?, ?||LPAD(SBDB.SEQ_SB_SPHM.NEXTVAL,8,'0'), ?,");
    buf.append("?, ?, ?,");
    buf.append("?, ?, ?,");
    buf.append("?, ?, ?,");
    buf.append("?, ?, ?,");
    buf.append("?, ?, ?,");
    buf.append("?, ?, ?,");
    buf.append("?, ?, ?,");
    buf.append("?, ?, ?,");
    buf.append("?, ?, ?,");
    buf.append("?, ?, ?,");
    buf.append("?, ?, ?,");
    buf.append("?, ?, ?,");
    buf.append("?, ?)");
    try {
      st = conn.prepareStatement(buf.toString());
      int count = 0;
      for (int i = 0; i < vos.size(); i++) {
        vo = (YhkkhzxxLw) vos.get(i);
        st.setString(1, vo.getJkpzh());
        st.setString(2, vo.getSphm());
        st.setString(3, vo.getBizph());
        st.setString(4, vo.getJsjdm());
        st.setString(5, vo.getNsrmc());
        st.setString(6, vo.getYhdm());
        st.setString(7, vo.getZh());
        st.setString(8, vo.getSzsmdm());
        st.setString(9, vo.getSzsmmc());
        st.setBigDecimal(10, vo.getSjrd());
        st.setBigDecimal(11, vo.getSjje());
        st.setTimestamp(12, vo.getSkssksrq());
        st.setTimestamp(13, vo.getSkssjsrq());
        st.setString(14, vo.getYsjcdm());
        st.setString(15, vo.getYsjcmc());
        st.setString(16, vo.getYskmdm());
        st.setString(17, vo.getGkzzjgdm());
        st.setString(18, vo.getGkzzjgmc());
        st.setString(19, vo.getSwjgzzjgdm());
        st.setString(20, vo.getSwjgzzjgmc());
        st.setString(21, vo.getScjxdm());
        st.setString(22, vo.getQxdm());
        st.setString(23, vo.getNd());
        st.setString(24, vo.getYd());
        st.setString(25, vo.getJydzlxdm());
        st.setString(26, vo.getGjbzhydm());
        st.setString(27, vo.getDjzclxdm());
        st.setString(28, vo.getLsgxdm());
        st.setBigDecimal(29, new BigDecimal(0));//0
        st.setBigDecimal(30, vo.getSjfc());
        st.setBigDecimal(31, vo.getQjfc());
        st.setString(32, vo.getKkbz());
        st.setTimestamp(33, vo.getKkrq());
        st.setString(34, vo.getKkdwdm());
        st.setString(35, vo.getBcgyydm());
        st.setString(36, vo.getCjr());
        st.setTimestamp(37, vo.getCjrq());
        st.setString(38, vo.getLrr());
        st.setTimestamp(39, vo.getLrrq());
        st.setString(40, vo.getFszt());
        st.setString(41, vo.getXyshm());
        st.addBatch();
        count++;
        if (count == 1000) {
          st.executeBatch();
          st.clearBatch();
          count = 0;
        }
      }
      st.executeBatch();
      st.clearBatch();
      count = 0;
      System.gc();
    }
    catch (Exception e) {
      e.printStackTrace();
      throw new Exception(
          "Database record access errors, Table: SFDB.SF_JL_GTGSH_YHKKHZXX_LW");
    }
    finally {
      try {
        if (st != null)
          st.close();
      }
      catch (Exception ex) {
        ex.printStackTrace();
      }
    }

  }

  /**
   * 插入扣款状态表
   * @param vos 数据集
   * @param conn 数据库连接
   */
  public void insertZtLwData(List vos, Connection conn) throws Exception {
    ZtLw vo = null;

    PreparedStatement st = null;
    StringBuffer buf = new StringBuffer();
    buf.append("INSERT INTO SFDB.SF_JL_GTGSH_ZT_LW(");
    buf.append("QXDM, ND, YD,");
    buf.append("SCZT, YHFSZT, YHKKRESULT,");
    buf.append("ZJLS, ZJE, CWZJLS,");
    buf.append("CWZJE, BZ, CJR,");
    buf.append("CJRQ, LRR, LRRQ)");
    buf.append(" VALUES(?, ?, ?,");
    buf.append("?, ?, ?,");
    buf.append("?, ?, ?,");
    buf.append("?, ?, ?,");
    buf.append("?, ?, ?)");
    try {
      st = conn.prepareStatement(buf.toString());
      int count = 0;
      for (int i = 0; i < vos.size(); i++) {
        vo = (ZtLw) vos.get(i);
        st.setString(1, vo.getQxdm());
        st.setString(2, vo.getNd());
        st.setString(3, vo.getYd());
        st.setString(4, vo.getSczt());
        st.setString(5, vo.getYhfszt());
        st.setString(6, vo.getYhkkresult());
        st.setBigDecimal(7, vo.getZjls());
        st.setBigDecimal(8, vo.getZje());
        st.setBigDecimal(9, vo.getCwzjls());
        st.setBigDecimal(10, vo.getCwzje());
        st.setString(11, vo.getBz());
        st.setString(12, vo.getCjr());
        st.setTimestamp(13, vo.getCjrq());
        st.setString(14, vo.getLrr());
        st.setTimestamp(15, vo.getLrrq());
        st.addBatch();
        count++;
        if (count == 1000) {
          st.executeBatch();
          st.clearBatch();
          count = 0;
        }
      }
      st.executeBatch();
      st.clearBatch();
      count = 0;
      System.gc();
    }
    catch (Exception e) {
      e.printStackTrace();
      throw new Exception(
          "Database record access errors, Table: SFDB.SF_JL_GTGSH_ZT_LW");
    }
    finally {
      try {
        if (st != null)
          st.close();
      }
      catch (Exception ex) {
        ex.printStackTrace();
      }
    }

  }

}
