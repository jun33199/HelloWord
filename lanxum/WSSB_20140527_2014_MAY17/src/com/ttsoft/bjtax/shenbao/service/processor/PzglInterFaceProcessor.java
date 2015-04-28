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
 * <p>Title: Ʊ֤����ӿ�ҵ����</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author Ha Zhengze
 * @version 1.0
 */

public class PzglInterFaceProcessor
    extends InterFaceProcessor {

  /**
   * ������ɢ˰�����幤�̻��걨���ݵĽᱨ����
   * ��������ͷ����������ʹ�ô˽ӿ�
   * @param wszhList ��˰֤���б�
   * @param xspzhList ����ƾ֤���б�
   * @param jbdh �ᱨ����
   * @param userData �û���Ϣ
   * @throws BaseException �쳣
   */
  public static void updatejbdhForPZ(List wszhList,
                                     List xspzhList,
                                     String jbdh,
                                     UserData userData) throws BaseException {
    if (userData == null || userData.getXtsbm1() == null ||
        userData.getXtsbm1().equals("")) {
      throw ExceptionUtil.getBaseException(
          new ApplicationException("���������û��ʻ�������Ϣ����Ϊ�գ�"));
    }
    if (jbdh == null || jbdh.equals("")) {
      throw ExceptionUtil.getBaseException(
          new ApplicationException("�������󣬽ᱨ���Ų���Ϊ�գ�"));
    }
    if ( ( (wszhList == null || wszhList.size() == 0) &&
          (xspzhList == null || xspzhList.size() == 0))) {
      throw ExceptionUtil.getBaseException(
          new ApplicationException("����������Ҫ���µ����ݲ��ܶ�Ϊ�գ�"));
    }
    //�������ݿ�����
    Connection conn = null;
    PreparedStatement pstmt1 = null;
    PreparedStatement pstmt2 = null;
    PreparedStatement pstmt3 = null;
    try {
      String sqlWszLs = ""; //��ɢ˰���
      String sqlWszGt = ""; //���幤�̻����
      if (wszhList != null && wszhList.size() != 0) {
        //��ʼ������ɢ˰��˰֤����
        StringBuffer sqlBuffer = new StringBuffer();
        sqlBuffer.append("UPDATE SBDB.SB_JL_LSSWSZZ SET JBDH = '")
            .append(jbdh).append("' WHERE WSZH IN ('")
            .append(wszhList.get(0)).append("'");
        for (int i = 1; i < wszhList.size(); i++) {
          sqlBuffer.append(",'").append(wszhList.get(i)).append("'");
        }
        sqlBuffer.append(")");
        sqlWszLs = sqlBuffer.toString();
        sqlBuffer.setLength(0); //���StringBuffer

        //���¸��幤�̻���˰����������
        StringBuffer strBuffer = new StringBuffer();
        strBuffer.append("UPDATE SBDB.SB_JL_GTGSHWSZZ SET JBDH = '")
            .append(jbdh).append("' WHERE WSZH IN ('")
            .append(wszhList.get(0)).append("'");
        for (int i = 1; i < wszhList.size(); i++) {
          strBuffer.append(",'").append(wszhList.get(i)).append("'");
        }
        strBuffer.append(")");
        sqlWszGt = strBuffer.toString();
        strBuffer.setLength(0); //���StringBuffer
      }

      String strYhs = "";
      if (xspzhList != null && xspzhList.size() != 0) {
        //����ӡ��˰��������
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

      // ������ݿ�����
      conn = DBResource.getConnection(DBResource.DB_SHENBAO);
      if (wszhList != null && wszhList.size() != 0) {
        pstmt1 = conn.prepareStatement(sqlWszLs);
        pstmt2 = conn.prepareStatement(sqlWszGt);
        pstmt1.executeUpdate(); //������˰�����ݿ����������ɢ˰
        pstmt2.executeUpdate(); //������˰�����ݿ�����������幤�̻�
      }
      if (xspzhList != null && xspzhList.size() != 0) {
        pstmt3 = conn.prepareStatement(strYhs);
        pstmt3.executeUpdate(); //����ӡ��˰���ݿ����
      }
    }
    catch (Exception ex) {
      ex.printStackTrace();
      throw ExceptionUtil.getBaseException(ex, "����ᱨ����ʧ�ܣ�");
    }
    finally {
      //�ͷ�����
      DBResource.destroyConnection(conn);
    }
  }

  /**
   * ��ѯ�ᱨ���Ŷ�Ӧ�����нɿ������Ƿ��Ѿ����
   * ��������ͷ����������ʹ�ô˽ӿ�
   * @param jbdh �ᱨ����
   * @return �Ƿ����(ֻҪ��һ���ɿ�����û�����ͷ���false),����ⷵ��true
   * @throws BaseException �����쳣
   */
  public static boolean cxsfrk(String jbdh) throws BaseException {
    if (jbdh == null || jbdh.trim().equals("")) {
      throw ExceptionUtil.getBaseException(new ApplicationException("�ᱨ���Ų���Ϊ�գ�"));
    }
    //�������ݿ�����
    Connection conn = null;
    try {
      //��ѯ��䣬��ѯ�ɿ�ƾ֤��
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
      // ������ݿ�����
      conn = DBResource.getConnection(DBResource.DB_SHENBAO);
      PreparedStatement sqlStatement = conn.prepareStatement(sql);
      ResultSet rs = sqlStatement.executeQuery();
      while (rs.next()) {
        jkpzhList.add(rs.getString("jkpzh"));
      }
      if (jkpzhList == null || jkpzhList.size() == 0) {
        throw ExceptionUtil.getBaseException(new ApplicationException
                                             ("�Ҳ����˽ᱨ����(" + jbdh + ")��Ӧ�Ľɿ�ƾ֤�ţ�"));
      }

      /*-------------------------------------------------------------
                �鿴�Ƿ����еĽɿ�ƾ֤�Ŷ��Ѿ����
       -------------------------------------------------------------*/
      return isJkpzhInStore(jkpzhList, conn, 0);
    }
    catch (Exception ex) {
      ex.printStackTrace();
      throw ExceptionUtil.getBaseException(ex, "��ѯ�������ʧ�ܣ�");
    }
    finally {
      //�ͷ�����
      DBResource.destroyConnection(conn);
    }
  }

  /**
   * ͨ����˰֤����õ��ɿ�����Ϣ(JksInfor)
   * ���������ʹ��
   * @param wszhArray ��˰֤������
   * @return �ɿ�����Ϣ(JksInfor)List(���Ҳ�������һ��size==0��List)
   * @throws BaseException �����쳣
   * ��һ��:ͨ����˰֤�Ų���ɢ˰����õ���Ӧ���걨���ܵ���<b>
   * �ڶ���:�ѵõ��Ļ��ܵ��źϲ�(���ջ��ܵ���)
   * ������:ͨ�����ܵ��ŵõ��ɿ�ƾ֤�źͽ������
   */
  public static List getJkpzhJeByWszh(String[] wszhArray) throws
      BaseException {
    if (wszhArray == null || wszhArray.length < 1) {
      throw ExceptionUtil.getBaseException(new ApplicationException("�������Ϸ�"));
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
      //ѡ��ͬ�Ľɿ�ƾ֤
      String sql = "SELECT DISTINCT JKPZH, SJSE FROM (" + sqlString + ")";

      String jkpzh = null; //�ɿ�ƾ֤��
      BigDecimal je = null; //ʵ�ɽ��
      JksInfor jksInfor = null; //�ɿ�����Ϣ(����ʵ�ɽ��)

      ArrayList jkpzList = new ArrayList();
      // ������ݿ�����
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
      throw ExceptionUtil.getBaseException(ex, "��ѯ�ɿ�ƾ֤����ʧ�ܣ�");
    }
    finally {
      //�ͷ�����
      DBResource.destroyConnection(conn);
    }
  }



  /**
   * ͨ����˰֤����õ��ɿ�����Ϣ(JksInfor)
   * �������ʹ��,�ڻ�ȡһƱһ˰�ɿ�ƾ֤�ź��ٴ�����ΪһƱ��˰�ɿ�������
   * @param wszhArray ��˰֤������
   * @return �ɿ�����Ϣ(JksInfor)List(���Ҳ�������һ��size==0��List)
   * @throws BaseException �����쳣
   * ��һ��:ͨ����˰֤�Ų���ɢ˰����õ���Ӧ���걨���ܵ���<b>
   * �ڶ���:�ѵõ��Ļ��ܵ��źϲ�(���ջ��ܵ���)
   * ������:ͨ�����ܵ��ŵõ��ɿ�ƾ֤�źͽ������
   */
  public static List getJkpzhJeByWszhForLw(String[] wszhArray) throws
      BaseException {
    if (wszhArray == null || wszhArray.length < 1) {
      throw ExceptionUtil.getBaseException(new ApplicationException("�������Ϸ�"));
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
      //ѡ��ͬ�Ľɿ�ƾ֤
      String sql = "SELECT DISTINCT JKPZH, SJSE FROM (" + sqlString + ")";
      String jkpzh = null; //�ɿ�ƾ֤��
      BigDecimal je = null; //ʵ�ɽ��
      JksInfor jksInfor = null; //�ɿ�����Ϣ(����ʵ�ɽ��)
      ArrayList jkpzList = new ArrayList();
      // ������ݿ�����
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
        throw new Exception("�޷��ҵ���˰֤��Ӧ��˰Ʊ��Ϣ!wszs="+wszhArray);
      }
      //
      return rnList;
    }
    catch (Exception ex) {
      ex.printStackTrace();
      throw ExceptionUtil.getBaseException(ex, "��ѯ�ɿ�ƾ֤����ʧ�ܣ�");
    }
    finally {
      //�ͷ�����
      DBResource.destroyConnection(conn);
    }
  }

  //ADD for Ʊ֤
  /**
   * Ʊ֤ģ�鴦��ȡӡ��˰��������
   * ������״̬
   * @param zhdm �ʻ�����
   * @param jsjdm ���������
   * @return ӡ��˰˰Ʊ������ϸArrayList(returnList.get(0)=cjshMap)
   *                                  (returnList.get(1)=yhssjList)
   *                                  (returnList.get(2)=jksjList)
   * @throws BaseException
   */
  public static ArrayList getYhssj(String zhdm, String jsjdm) throws
      BaseException {
    if (zhdm == null || zhdm.equals(""))
      throw ExceptionUtil.getBaseException(
          new ApplicationException("ӡ��˰��ѯ���ʻ����벻��Ϊ�գ�"));
    Connection conn = null;
    ArrayList returnList = new ArrayList();
    // ����ʱ��Map(key=xspzh,value=cjsj)
    HashMap cjsjMap = new HashMap();
    ArrayList yhssjList = new ArrayList();
    try {
      // �õ�����
      conn = DBResource.getConnection(DBResource.DB_SHENBAO);

      StringBuffer sql = new StringBuffer();
      // ��ѯ����Ϊ�ʻ�������ڲ���zhdm
      // ���ʱ�ʶΪ0��ӡ��˰��������
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
        // ����ƾ֤��
        yhsgmmx.setXspzh(rs.getString("xspzh"));
        // ��Ʊ����
        yhsgmmx.setGpsl(rs.getBigDecimal("gpsl"));
        // ���
        yhsgmmx.setJe(rs.getBigDecimal("je"));
        // Ʊ֤������루���걨�ı��н�˰Ʊ��ֵ���룩
        yhsgmmx.setSpmzdm(rs.getString("spmzdm"));
        yhssjList.add(yhsgmmx);
      }
      // ���sql���
      sql.delete(0, sql.length());
      // ���ʱ�ʶΪ0������ƾ֤�źʹ���ʱ��
      sql.append(" select xspzh, cjrq from SBDB.SB_JL_YHSGMZ ")
          .append(" where zhdm = '").append(zhdm).append("'")
          .append(" and jzbs = '0' ")
          .append(" and dsjsjdm = '").append(jsjdm).append("'");
      rs = st.executeQuery(sql.toString());
      while (rs.next()) {
        cjsjMap.put(rs.getString("xspzh"), rs.getTimestamp("cjrq"));
      }

      //��ѯ��Ӧ�ɿ�ƾ֤������
      // ���sql���
      sql.delete(0, sql.length());
      //����list
      ArrayList jkData = new ArrayList();
      // ���ʱ�ʶΪ0������ƾ֤�źʹ���ʱ��
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
      // ���sql���
      sql.delete(0, sql.length());

      returnList.add(cjsjMap); //������ϸ����
      returnList.add(yhssjList); //�������ڼ�����ƾ֤��
      returnList.add(jkData); //�ɿ�ƾ֤����

      return returnList; //���ؽ������
    }
    catch (Exception e) {
      e.printStackTrace();
      throw ExceptionUtil.getBaseException(e, "ȡӡ��˰��������ʧ�ܣ�");
    }
    finally {
      //�ͷ�����
      DBResource.destroyConnection(conn);
    }
  }


  /**
   * Ʊ֤ģ�鴦��ȡӡ��˰��������
   * ����״̬
   * @param zhdm �ʻ�����
   * @param jsjdm ���������
   * @return ӡ��˰˰Ʊ������ϸArrayList(returnList.get(0)=cjshMap)
   *                                  (returnList.get(1)=yhssjList)
   *                                  (returnList.get(2)=jksjList)
   * @throws BaseException
   */
  public static ArrayList getYhssjForLw(String zhdm, String jsjdm) throws
      BaseException {
    if (zhdm == null || zhdm.equals(""))
      throw ExceptionUtil.getBaseException(
          new ApplicationException("ӡ��˰��ѯ���ʻ����벻��Ϊ�գ�"));
    Connection conn = null;
    ArrayList returnList = new ArrayList();
    // ����ʱ��Map(key=xspzh,value=cjsj)
    HashMap cjsjMap = new HashMap();
    ArrayList yhssjList = new ArrayList();
    try {
      // �õ�����
      conn = DBResource.getConnection(DBResource.DB_SHENBAO);

      StringBuffer sql = new StringBuffer();
      // ��ѯ����Ϊ�ʻ�������ڲ���zhdm
      // ���ʱ�ʶΪ0��ӡ��˰��������
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
        // ����ƾ֤��
        yhsgmmx.setXspzh(rs.getString("xspzh"));
        // ��Ʊ����
        yhsgmmx.setGpsl(rs.getBigDecimal("gpsl"));
        // ���
        yhsgmmx.setJe(rs.getBigDecimal("je"));
        // Ʊ֤������루���걨�ı��н�˰Ʊ��ֵ���룩
        yhsgmmx.setSpmzdm(rs.getString("spmzdm"));
        yhssjList.add(yhsgmmx);
      }
      // ���sql���
      sql.delete(0, sql.length());
      // ���ʱ�ʶΪ0������ƾ֤�źʹ���ʱ��
      sql.append(" select xspzh, cjrq from SBDB.SB_JL_YHSGMZ ")
          .append(" where zhdm = '").append(zhdm).append("'")
          .append(" and jzbs = '0' ")
          .append(" and dsjsjdm = '").append(jsjdm).append("'");
      rs = st.executeQuery(sql.toString());
      while (rs.next()) {
        cjsjMap.put(rs.getString("xspzh"), rs.getTimestamp("cjrq"));
      }

      //��ѯ��Ӧ�ɿ�ƾ֤������
      // ���sql���
      sql.delete(0, sql.length());
      //����list
      ArrayList jkData = new ArrayList();
      // ���ʱ�ʶΪ0������ƾ֤�źʹ���ʱ��
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
      // ���sql���
      sql.delete(0, sql.length());

      returnList.add(cjsjMap); //������ϸ����
      returnList.add(yhssjList); //�������ڼ�����ƾ֤��
      returnList.add(jkData); //�ɿ�ƾ֤����

      return returnList; //���ؽ������
    }
    catch (Exception e) {
      e.printStackTrace();
      throw ExceptionUtil.getBaseException(e, "ȡӡ��˰��������ʧ�ܣ�");
    }
    finally {
      //�ͷ�����
      DBResource.destroyConnection(conn);
    }
  }

  /**
   * Ʊ֤ģ�鴦��ӡ��˰���������޸ļ��ʱ�ʶ
   * ���Ѿ���¼��Ʊ֤���е�ӡ��˰���ۼ�¼�ļ��ʱ�ʶ��0��Ϊ1
   * @param xspzhList Ҫ�޸ĵ�����ƾ֤��List
   * @param jsjdm ���������
   * @param lx �������� ���� 0������Ϊδ���ʣ�1������Ϊ�Ѽ���
   * @throws BaseException
   */
  public static void updateJzbs(ArrayList xspzhList, String jsjdm, int lx) throws
      BaseException {
    if (xspzhList == null || xspzhList.size() == 0)
      throw ExceptionUtil.getBaseException(
          new ApplicationException("ӡ��˰�޸ĵ�����ƾ֤�Ų���Ϊ�գ�"));
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
            new ApplicationException("δ֪���±�ʶ��"));

      // �õ�����
      conn = DBResource.getConnection(DBResource.DB_SHENBAO);
      PreparedStatement st = conn.prepareStatement(sql.toString());
      for (int i = 0; i < xspzhList.size(); i++) {
        st.setString(1, (String) xspzhList.get(i));
        st.executeUpdate();
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      throw ExceptionUtil.getBaseException(e, "�޸ļ��ʱ�ʶʧ�ܣ�");
    }
    finally {
      //�ͷ�����
      DBResource.destroyConnection(conn);
    }
  }

  /**
   * ΪƱ֤ģ���ṩ�ĸ��ݽɿ�ƾ֤�Ż�������Ľӿڷ���
   * ���������
   * @param jkpzh  �ɿ�ƾ֤��
   * @return BigDecimal rkje
   * @throws BaseException
   */
  public static BigDecimal getRkjeByJkpzhPZ(String jkpzh) throws BaseException {
    //�������
    if (jkpzh == null || jkpzh.equals("")) {
      throw ExceptionUtil.getBaseException(new ApplicationException(
          "����Ľɿ�ƾ֤��Ϊ��!"));
    }
    ArrayList zbResult = new ArrayList(); //���������ѯ�����
    //�������ݿ�����
    Connection conn = null;
    //ORʵ��
    ORManager orManager = null;
    try {
      // ��� ORManager
      orManager = DBResource.getORManager(DBResource.OR_SHENBAO);

      //�����걨�ɿ�����
      StringBuffer sqlStrBuf = new StringBuffer();
      sqlStrBuf.append("(JKPZH = '").append(jkpzh).append("')");
      String sqlString = sqlStrBuf.toString(); //���ɿ�ƾ֤���б��е����м�¼����ѯ����
      Vector criteria = new Vector();
      criteria.add(sqlString);
      ORContext orCtx = new ORContext(Sbjkzb.class.getName(), criteria);
      // ������ݿ�����
      conn = DBResource.getConnection(DBResource.DB_SHENBAO);
      zbResult = (ArrayList) orManager.query(SESSION_ID, conn, orCtx); //�����ѯ���
      if (zbResult.size() == 0) {
        return null; //û�����������ļ�¼(�����ýɿ�ƾ֤�Ų�����)������һ��null
      }
      Sbjkzb tempZb = (Sbjkzb) zbResult.get(0);
      //��������ʶ�ڶ�λΪ��0�������ʶδ��⣬�򷵻������Ϊ0 ������
      if (tempZb.getZwbs().substring(1, 2).equals("0")) {
        return new BigDecimal("0");
      }
      return tempZb.getRkje(); //���ظýɿ�ƾ֤�������
    }
    catch (Exception ex) {
      ex.printStackTrace();
      throw ExceptionUtil.getBaseException(ex, "��ѯ�������ʧ�ܣ�δ�õ��ɿ����ݣ�");
    }
    finally {
      //�ͷ�����
      DBResource.destroyConnection(conn);
    }
  }



  /**
   * ΪƱ֤ģ���ṩ�ĸ��ݽɿ�ƾ֤�Ż�������Ľӿڷ���
   * �������
   * @param jkpzh  �ɿ�ƾ֤��
   * @return BigDecimal rkje
   * @throws BaseException
   */
  public static BigDecimal getRkjeByJkpzhPZForLw(String jkpzh) throws BaseException {
    //�������
    if (jkpzh == null || jkpzh.equals("")) {
      throw ExceptionUtil.getBaseException(new ApplicationException(
          "����Ľɿ�ƾ֤��Ϊ��!"));
    }
    ArrayList zbResult = new ArrayList(); //���������ѯ�����
    //�������ݿ�����
    Connection conn = null;
    //ORʵ��
    ORManager orManager = null;
    try {
      // ��� ORManager
      orManager = DBResource.getORManager(DBResource.OR_SHENBAO);

      //�����걨�ɿ�����
      StringBuffer sqlStrBuf = new StringBuffer();
      sqlStrBuf.append("(sphm = '").append(jkpzh).append("')");
      String sqlString = sqlStrBuf.toString(); //���ɿ�ƾ֤���б��е����м�¼����ѯ����
      Vector criteria = new Vector();
      criteria.add(sqlString);
      ORContext orCtx = new ORContext(Sbjkzb.class.getName(), criteria);
      // ������ݿ�����
      conn = DBResource.getConnection(DBResource.DB_SHENBAO);
      zbResult = (ArrayList) orManager.query(SESSION_ID, conn, orCtx); //�����ѯ���
      if (zbResult.size() == 0) {
        return null; //û�����������ļ�¼(�����ýɿ�ƾ֤�Ų�����)������һ��null
      }
      Sbjkzb tempZb = (Sbjkzb) zbResult.get(0);
      //��������ʶ�ڶ�λΪ��0�������ʶδ��⣬�򷵻������Ϊ0 ������
      if (tempZb.getZwbs().substring(1, 2).equals("0")) {
        return new BigDecimal("0");
      }
      return tempZb.getRkje(); //���ظýɿ�ƾ֤�������
    }
    catch (Exception ex) {
      ex.printStackTrace();
      throw ExceptionUtil.getBaseException(ex, "��ѯ�������ʧ�ܣ�δ�õ��ɿ����ݣ�");
    }
    finally {
      //�ͷ�����
      DBResource.destroyConnection(conn);
    }
  }


}