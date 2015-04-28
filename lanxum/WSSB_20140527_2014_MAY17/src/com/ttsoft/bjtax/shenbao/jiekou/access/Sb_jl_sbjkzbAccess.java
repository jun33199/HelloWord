package com.ttsoft.bjtax.shenbao.jiekou.access;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb;

/**
 * 为纳税评估单独提供的接口代码，添加申报缴款主表数据
 * <p>Title: 北京地税综合管理系统  申报征收模块</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: TTSOFT</p>
 * @author wanghw
 * @version 1.0
 */
public class Sb_jl_sbjkzbAccess {
  public static void insertRecord(Connection con, Sbjkzb vo) throws Exception {
    PreparedStatement st = null;
    vo.setSphm(new String(vo.getJkpzh()));
    String buf = "INSERT INTO SBDB.SB_JL_SBJKZB(CJRQ, CLBJDM, DJZCLXDM, FSDM, GJBZHYDM, JKPZH, JSJDM, LRR, LRRQ, RKJE, SBRQ, SJJE, SKLXDM, SWJGZZJGDM, SZDM, YSJCDM, YSKMDM, ZSSWJGZZJGDM, ZWBS, BZ, GKZZJGDM, HXRDM, HXRMC, HXRQ, JKSJ, JYDZLXDM, LSGXDM, SBBH, SJLY, SKSSJSRQ, SKSSKSRQ, XJRQ, YHDM, YHMC, ZH, ZYRQ, ND, QXDM,SPHM) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    try {
      st = con.prepareStatement(buf);
      st.setTimestamp(1, vo.getCjrq());
      st.setString(2, vo.getClbjdm());
      st.setString(3, vo.getDjzclxdm());
      st.setString(4, vo.getFsdm());
      st.setString(5, vo.getGjbzhydm());
      st.setString(6, vo.getJkpzh());
      st.setString(7, vo.getJsjdm());
      st.setString(8, vo.getLrr());
      st.setTimestamp(9, vo.getLrrq());
      st.setBigDecimal(10, vo.getRkje());
      st.setTimestamp(11, vo.getSbrq());
      st.setBigDecimal(12, vo.getSjje());
      st.setString(13, vo.getSklxdm());
      st.setString(14, vo.getSwjgzzjgdm());
      st.setString(15, vo.getSzdm());
      st.setString(16, vo.getYsjcdm());
      st.setString(17, vo.getYskmdm());
      st.setString(18, vo.getZsswjgzzjgdm());
      st.setString(19, vo.getZwbs());
      st.setString(20, vo.getBz());
      st.setString(21, vo.getGkzzjgdm());
      st.setString(22, vo.getHxrdm());
      st.setString(23, vo.getHxrmc());
      st.setTimestamp(24, vo.getHxrq());
      st.setTimestamp(25, vo.getJksj());
      st.setString(26, vo.getJydzlxdm());
      st.setString(27, vo.getLsgxdm());
      st.setString(28, vo.getSbbh());
      st.setString(29, vo.getSjly());
      st.setTimestamp(30, vo.getSkssjsrq());
      st.setTimestamp(31, vo.getSkssksrq());
      st.setTimestamp(32, vo.getXjrq());
      st.setString(33, vo.getYhdm());
      st.setString(34, vo.getYhmc());
      st.setString(35, vo.getZh());
      st.setTimestamp(36, vo.getZyrq());
      st.setString(37, vo.getNd());
      st.setString(38, vo.getQxdm());
      st.setString(39, vo.getSphm());

      st.execute();
    }
    catch (Exception e) {
      e.printStackTrace();
      throw new Exception(
          "Database record access errors, Table: SBDB.SB_JL_SBJKZB");
    }
    finally {
      try {
        if (st != null)
          st.close();
      }
      catch (Exception ex) {
      }
    }
  }
}