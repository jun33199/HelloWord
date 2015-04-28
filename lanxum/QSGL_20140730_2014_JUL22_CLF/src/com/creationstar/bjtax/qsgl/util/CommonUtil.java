/*
 * <p>Title: ������˰��������ϵͳ������˰����</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣�������Ѷ�������������޹�˾����Ȩ����. </p>
 * <p>Company: ������Ѷ�������������޹�˾</p>
 */

package com.creationstar.bjtax.qsgl.util;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.DAOFactory;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Grxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Jm;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Jsblcq;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Jsblgyzf;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbzb;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Spjgxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Swjgzzjg;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Szsm;
import com.creationstar.bjtax.qsgl.BizLogic.vo.SzsmYskm;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Tufwxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Yskm;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Zcwh;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Zh;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Zjlx;
import com.creationstar.bjtax.qsgl.VisionLogic.form.clfgl.ClfswjghdxxlrForm;
import com.creationstar.bjtax.qsgl.model.bo.FwjhxxBo;
import com.creationstar.bjtax.qsgl.model.bo.JghdsjBo;
import com.creationstar.bjtax.qsgl.model.bo.JmsbxxBo;
import com.creationstar.bjtax.qsgl.model.bo.PldrBo;
import com.creationstar.bjtax.qsgl.model.bo.PldrBo2;
import com.creationstar.bjtax.qsgl.model.bo.SbxxBo;
import com.ttsoft.bjtax.dj.DjOuterConstant;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.dj.model.ZRRJBSJ;
import com.ttsoft.bjtax.dj.proxy.ServiceProxy;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.Debug;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.DateUtil;
import com.ttsoft.framework.util.StringUtil;


/**
 * <p>Title: ������˰��������ϵͳ������˰����</p>
 * <p>Description: ��ҵ��㣨������WEB�㣩�Ĺ��ú�����</p>
 * @author ��˰�����飭���Բ�
 * @version 1.1
 */
public class CommonUtil {
	
	/**
	 * ����֤�������ȡ֤����������
	 * @param form
	 * @param zjdm
	 * @return ֤������
	 * @author hhb
	 */
	public static String getZjmc(ClfswjghdxxlrForm form, String zjdm){
		List list=form.getZjList();
		String zjmc = "";
		for (int i = 0; i < list.size(); i++) {
			Zjlx zjlx= (Zjlx)list.get(i);
			if(zjlx.getZjlxdm().equals(zjdm)){
				zjmc = zjlx.getZjlxmc();
				return zjmc;
			}
		}
		return zjmc;
	}
    /**
     * ��ȡ���ݿ��ϵͳʱ�䡣
     *
     * @param con the Connection.
     * @return Timestamp ϵͳʱ�䡣
     * @throws BaseException �����׳����쳣��
     */
    public static Timestamp getDBtime(Connection con) throws BaseException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            String sql = "select sysdate from dual";
            pst = con.prepareStatement(sql);

            rs = pst.executeQuery();

            rs.next();

            Timestamp dbtime = rs.getTimestamp("SYSDATE");
            return dbtime;
        } catch (SQLException ex) {
            throw ExceptionUtil.getBaseException(ex);
        } finally {
            try {
                pst.close();
            } catch (Exception ex) {

            }
            try {
                rs.close();
            } catch (Exception ex) {

            }
        }
    }

    /**
     * ��ȡǰ̨������̨�Ĳ�ѯ������
     *
     * @param Object obj��ʵ��ΪBO��
     * @return String������õĲ�ѯ���������û�в�ѯ��������һ�����ַ���""��
     * @throws BaseException �����׳����쳣��
     */
    public static String getSqlQueryConditions(Object obj) {

        boolean firstCondition = true;
        StringBuffer userSQL = new StringBuffer("");

        if (obj == null) { //��������ҵ�����Ϊnull���򷵻�һ��null�������߲���
            return null;
        }
        try {
            for (int i = 0; i < obj.getClass().getFields().length; i++) {

                String name = obj.getClass().getFields()[i].getName(); //�õ�ҵ������ʵ���ֶ����ƣ�Ҳ���ǲ�ѯ����������
                Object o = obj.getClass().getFields()[i].get(obj); //�õ�ҵ������ʵ���ֶε�ֵ��Ҳ���Ǹò�ѯ������ֵ

                if (o == null) { //�����ʵ���ֶ�Ϊnull
                    continue;
                } else {
                    if (firstCondition) { //����ǵ�һ����Ϊnull�Ĳ�ѯ���������where
                        userSQL.append(" WHERE ");
                        firstCondition = false;
                    } else { //����ֱ�Ӽ�AND
                        userSQL.append(" AND ");
                    }
                }

                if (o instanceof Integer) {
                    userSQL.append(name).append(" = ").append(((Integer) o).
                            intValue());
                } else if (o instanceof Long) {
                    userSQL.append(name).append(" = ").append(((Long) o).
                            longValue());
                } else if (o instanceof Float) {
                    userSQL.append(name).append(" = ").append(((Float) o).
                            floatValue());
                } else if (o instanceof Double) {
                    userSQL.append(name).append(" = ").append(((Double) o).
                            doubleValue());
                } else if (o instanceof String) {
                    userSQL.append(name).append(" = '").append((String) o).
                            append("'");
                } else if (o instanceof Timestamp) {
                    ; //��ʱ���ṩTimestamp�Ĵ�����Ϊ��֪����ѯ��ƥ�������ǡ�= or between��
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return userSQL.toString();
    }

    /**
     * ��ȡǰ̨������̨�Ĳ�ѯ������
     *
     * @param Object obj��ʵ��ΪBO��
     * @return String������õĲ�ѯ���������û�в�ѯ��������һ�����ַ���""���˷������ڶ���ѯ��
     * @throws BaseException �����׳����쳣��
     */
    public static String getSqlQueryCondition(Object obj) {

        boolean firstCondition = true;
        StringBuffer userSQL = new StringBuffer("");

        if (obj == null) { //��������ҵ�����Ϊnull���򷵻�һ��null�������߲���
            return null;
        }
        try {
            for (int i = 0; i < obj.getClass().getFields().length; i++) {

                String name = obj.getClass().getFields()[i].getName(); //�õ�ҵ������ʵ���ֶ����ƣ�Ҳ���ǲ�ѯ����������
                Object o = obj.getClass().getFields()[i].get(obj); //�õ�ҵ������ʵ���ֶε�ֵ��Ҳ���Ǹò�ѯ������ֵ

                if (o == null) { //�����ʵ���ֶ�Ϊnull
                    continue;
                } else {
                    if (firstCondition) { //����ǵ�һ����Ϊnull�Ĳ�ѯ���������where
                        userSQL.append(" ");
                        firstCondition = false;
                    } else { //����ֱ�Ӽ�AND
                        userSQL.append(" AND ");
                    }
                }

                if (o instanceof Integer) {
                    userSQL.append(name).append(" = ").append(((Integer) o).
                            intValue());
                } else if (o instanceof Long) {
                    userSQL.append(name).append(" = ").append(((Long) o).
                            longValue());
                } else if (o instanceof Float) {
                    userSQL.append(name).append(" = ").append(((Float) o).
                            floatValue());
                } else if (o instanceof Double) {
                    userSQL.append(name).append(" = ").append(((Double) o).
                            doubleValue());
                } else if (o instanceof String) {
                    userSQL.append(name).append(" = '").append((String) o).
                            append("'");
                } else if (o instanceof Timestamp) {
                    ; //��ʱ���ṩTimestamp�Ĵ�����Ϊ��֪����ѯ��ƥ�������ǡ�= or between��
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return userSQL.toString();
    }

    /**
     * �걨��ţ����ˡ��Ǹ��ˣ���ͬʱ���������ı�ţ�
     * "1λ��ĸ" + ZHDM(8λ,����01�оֱ�ʾ) + ʱ��(040101) + ��ˮ��(4λ)
     * �걨��ŵ�1λ��ĸΪ"S"
     * 1234567890123456789
     * @param zhdm String
     * @param conn Connection
     * @param lx String
     * @return String
     * @throws ApplicationException
     */
    private static String getSBBH(String zhdm, Connection conn, String lx) throws
            ApplicationException {
        if (zhdm == null) {
            throw new ApplicationException("����Ĳ������ʻ����롱����Ϊ�գ�");
        }

        if (zhdm.length() != 10) {
            throw new ApplicationException("����Ĳ������ʻ����롱λ���쳣");
        }

        //ZHDM(8λ,����01�оֱ�ʾ)
        String SBBH = lx + zhdm.substring(2);
        SBBH = SBBH + getDatetime(new Date(), "yyMMdd");

        /** @todo �޸����ݿ���SBBH�ֶγ��� */
        Statement stmt = null;
        String sql =
                "select MAX(SBBH) maxsbbh from qsdb.qs_jl_sbzb where sbbh like '" +
                SBBH + "____'";
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                String maxSbbh = rs.getString("maxsbbh");
                if (maxSbbh == null) {
                    SBBH = SBBH + "0001";
                } else {
                    maxSbbh = maxSbbh.substring(15);
                    //Debug.out("maxSbbh in CommonUtil.getSBBH() is " + maxSbbh);
                    maxSbbh = String.valueOf(Integer.parseInt(maxSbbh) + 1);
                    //Debug.out("maxSbbh is " + maxSbbh);
                    //Debug.out("4-maxSbbh.length() is " + (4 - maxSbbh.length()));
                    String newLS = DataConvert.fillString(maxSbbh, "0", 4);
                    //Debug.out("newLS is " + newLS);
                    SBBH = SBBH + newLS;
                }
            } else {
                SBBH = SBBH + "0001";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ApplicationException("CommonUtil.getSBBH() ���������ݿ����", e);
        }
        return SBBH;
    }

    /**
     * ����걨���
     * "1λ��ĸ" + ZHDM(8λ,����01�оֱ�ʾ) + ʱ��(040101) + ��ˮ��(4λ)
     * @param zhdm String
     * @param conn Connection
     * @return String
     * @throws ApplicationException
     */
    public static String getSBBH(String zhdm, Connection conn) throws
            ApplicationException {
        return getSBBH(zhdm, conn, "S");
    }

    /**
     * ����걨��ţ������Ĳɼ���ţ�
     * "1λ��ĸ" + ZHDM(8λ,����01�оֱ�ʾ) + ʱ��(040101) + ��ˮ��(4λ)
     * @param zhdm String
     * @param conn Connection
     * @return String
     * @throws ApplicationException
     */
    public static String getBZBH(String zhdm, Connection conn) throws
            ApplicationException {
        return getSBBH(zhdm, conn, "B");
    }

    /**
     * ����걨��ţ������Ĳɼ���ţ�
     * "1λ��ĸ" + ZHDM(8λ,����01�оֱ�ʾ) + ʱ��(040101) + ��ˮ��(4λ)
     * @param zhdm String
     * @param conn Connection
     * @return String
     * @throws ApplicationException
     */
    public static String getPLBH(String zhdm, Connection conn) throws
            ApplicationException {
        return getSBBH(zhdm, conn, "P");
    }


    /**
     * �����걨��ţ����ˡ��Ǹ��ˣ�
     * "1λ��ĸ" + ZHDM(8λ,����01�оֱ�ʾ) + ʱ��(040101) + ��ˮ��(4λ)
     * @param zhdm String
     * @param conn Connection
     * @return String
     * @throws ApplicationException
     */
    public static String getJMSBBH2(String zhdm, Connection conn) throws
            ApplicationException {
        return getSBBH(zhdm, conn, "J");
    }

    /**
     * �����걨��ţ����ˡ��Ǹ��ˣ���
     * "1λ��ĸ" + ZHDM(8λ,����01�оֱ�ʾ) + ʱ��(040101) + ��ˮ��(4λ)
     * �걨��ŵ�1λ��ĸΪ"S"
     * 1234567890123456789
     * @return String
     */
    public static String getJMSBBH(String zhdm, Connection conn) throws
            ApplicationException {
        if (zhdm == null) {
            throw new ApplicationException("����Ĳ������ʻ����롱����Ϊ�գ�");
        }

        if (zhdm.length() != 10) {
            throw new ApplicationException("����Ĳ������ʻ����롱λ���쳣");
        }

        //ZHDM(8λ,����01�оֱ�ʾ)
        String JMSBBH = "J" + zhdm.substring(2);
        JMSBBH = JMSBBH + getDatetime(new Date(), "yyMMdd");

        /** @todo �޸����ݿ��м����걨���е�JMSBH�ֶγ��� */
        Statement stmt = null;
        String sql =
                "select MAX(JMSBBH) maxjmsbh from QSDB.QS_JL_JMSBB  where sbbh like '" +
                JMSBBH + "____'";
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            if (rs.next()) {
                String maxJmsbh = rs.getString("maxjmsbh");
                if (maxJmsbh == null) {
                    JMSBBH = JMSBBH + "0001";
                } else {
                    maxJmsbh = maxJmsbh.substring(15);

                    maxJmsbh = String.valueOf(Integer.parseInt(maxJmsbh) + 1);

                    String newLS = DataConvert.fillString(maxJmsbh, "0", 4);

                    JMSBBH = JMSBBH + newLS;
                }
            } else {
                JMSBBH = JMSBBH + "0001";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ApplicationException("CommonUtil.getJMSBBH() ���������ݿ����",
                                           e);
        }
        return JMSBBH;
    }

    /**
     * @return String ��������Ψһ��ʾ
     */
    public static String getTDFWID(Connection conn) throws ApplicationException {
        Statement stmt = null;
        String sql = "SELECT QSDB.SEQ_QS_JL_TDFWID.NEXTVAL TDFWID FROM DUAL";
        ResultSet rs = null;
        String maxId = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                maxId = rs.getString("TDFWID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ApplicationException("CommonUtil.getTDFWID() ���������ݿ����",
                                           e);
        }
        return maxId;
    }

    /**
     * @return String ��������Ψһ��ʾ
     */
    public static String getCqxxbh(Connection conn) throws ApplicationException {
        Statement stmt = null;
        String sql = "SELECT QSDB.SEQ_QS_JL_CQXXBH.NEXTVAL CQXXBH FROM DUAL";
        ResultSet rs = null;
        String maxId = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                maxId = rs.getString("CQXXBH");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ApplicationException("CommonUtil.getCqxxbh() ���������ݿ����",
                                           e);
        }
        return maxId;
    }

    /**
     * ���ݴ����dateFormat��������Ӧ�Ľ�ȡ��ĸ�ʽ��String�͵Ľ��
     * @param date Date
     * @param dateFormat String
     * @return String
     */
    public static String getDatetime(Date date, String dateFormat) {
        if (dateFormat == null) {
            dateFormat = "yyyy-MM-dd HH:mm:ss.SSS";
        }
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        return formatter.format(date);
    }

    /**
     * ��ȡ�˶�֪ͨ�����
     * hashmap �е�key:
     * ndzb ����ֱ�
     * wsjc ������
     * lsh ��ˮ��
     * hdtzsh �˶�֪ͨ���
     * @param ud UserData
     * @param conn Connection
     * @return HashMap
     * @throws BaseException
     */
    public static HashMap getHDTZSH(UserData ud, Connection conn) throws
            BaseException {
        HashMap resultMap = new HashMap();
        String hdtzsh = "";
        int lsh = 0;
        String tmp = "";
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Swjgzzjg swjgzzjg = getSwjgzzjg(ud, conn);
            Swjgzzjg qxfjSwjgzzjg = getSwjgzzjg(swjgzzjg.qxfjdm, conn);
            String ndzb = DateUtil.getDate().substring(0, 4); //����ֱ�
            String wsjc = qxfjSwjgzzjg.wsjc; //�����ֱ�  �������

            String sql = " select nvl(max(lsh),0) lsh from qsdb.qs_jl_hdtzs t ";
            sql = sql + " where t.ndzb='" + ndzb + "' and t.wsjc='" + wsjc +
                  "'";
            stmt = conn.createStatement();
            //Debug.out("��ȡ�˶�֪ͨ����� sql is " + sql);
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                lsh = rs.getInt("lsh");
                if (lsh == 0) {
                    tmp = "1";
                } else {
                    tmp = String.valueOf(lsh + 1);
                }
            } else {
                tmp = "1";
            }
            //��0��ȫλ��
            //String newLS = DataConvert.fillString(tmp, "0", 4);
            String newLS = DataConvert.fillString(tmp, "0", 6); //�����2014���ȡ�˶�֪ͨ����볬��4λ���ؽ�λ��������6λ tujb 2014.10.15

            /** @todo �����޸�����ַ������κ��ֶκͱ�ʾ */
            hdtzsh = "����" + wsjc + "����˰�����֣�" + ndzb + "����" + newLS + "��";
            resultMap.put("ndzb", ndzb);
            resultMap.put("wsjc", wsjc);
            resultMap.put("lsh", new BigDecimal(newLS));
            resultMap.put("hdtzsh", hdtzsh);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ApplicationException("ȡ�ú˶�֪ͨ��ŵ�ʱ�����");
        }
        //Debug.out("��ȡ�˶�֪ͨ����� is " + hdtzsh);
        return resultMap;
    }


    /**
     * ��ȡ�˶�֪ͨ�����_�����걨
     * hashmap �е�key:
     * ndzb ����ֱ�
     * wsjc ������
     * lsh ��ˮ��
     * hdtzsh �˶�֪ͨ���
     * @param ud UserData
     * @param conn Connection
     * @return HashMap
     * @throws BaseException
     */
    public static HashMap getHDTZSH_JMSB(UserData ud, Connection conn) throws
            BaseException {
        HashMap resultMap = new HashMap();
        String hdtzsh = "";
        int lsh = 0;
        String tmp = "";
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Swjgzzjg swjgzzjg = getSwjgzzjg(ud, conn);
            Swjgzzjg qxfjSwjgzzjg = getSwjgzzjg(swjgzzjg.qxfjdm, conn);
            String ndzb = DateUtil.getDate().substring(0, 4); //����ֱ�
            String wsjc = qxfjSwjgzzjg.wsjc; //�����ֱ�  �������

            String sql = " select nvl(max(lsh),0) lsh from qsdb.qs_jl_hdtzs t ";
            sql = sql + " where t.ndzb='" + ndzb + "' and t.wsjc='" + wsjc +
                  "'";
            stmt = conn.createStatement();
            //Debug.out("��ȡ�˶�֪ͨ����� sql is " + sql);
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                lsh = rs.getInt("lsh");
                if (lsh == 0) {
                    tmp = "1";
                } else {
                    tmp = String.valueOf(lsh + 1);
                }
            } else {
                tmp = "1";
            }
            //��0��ȫλ��
            //String newLS = DataConvert.fillString(tmp, "0", 4);
            String newLS = DataConvert.fillString(tmp, "0", 6); //�����2014���ȡ�˶�֪ͨ����볬��4λ���ؽ�λ��������6λ  tujb 2014.10.15
            
            /** @todo �����޸�����ַ������κ��ֶκͱ�ʾ */
            hdtzsh = wsjc + "��˰�����֣�" + ndzb + "����" + newLS + "��";
            resultMap.put("ndzb", ndzb);
            resultMap.put("wsjc", wsjc);
            resultMap.put("lsh", new BigDecimal(newLS));
            resultMap.put("hdtzsh", hdtzsh);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ApplicationException("ȡ�ú˶�֪ͨ��ŵ�ʱ�����");
        }
        //Debug.out("��ȡ�˶�֪ͨ����� is " + hdtzsh);
        return resultMap;
    }

    /**
     * ȡ�ò���UserData��QXDM�����ش��룩
     * @param ud UserData
     * @return String
     */
    public static String getQxdm(UserData ud) {
        String qxdm = "";
        //���UserData�е�������λ����Ϊ�գ��򷵻ؿյ����ش���
        if (ud != null && ud.ssdwdm != null) {
            qxdm = ud.getSsdwdm().substring(0, 2);
        }
        return qxdm;
    }

    /**
     * ���ݴ�����Grxxֵ���󣬲�ѯ��Ȼ�˵ļ��������
     * ���û����Ȼ�˵ļ�������룬������Ȼ�˵Ǽǵ����ݿ��в���һ����
     * ����ȡ���²������Ȼ�˵ļ�������룬�����м���������Grxxֵ����
     *
     * @param grxx Grxx
     * @return Grxx
     */
    public static Grxx handleZRR(Grxx grxx, UserData ud) throws BaseException {
        try {
            ZRRJBSJ zrrJbsj = null;
            try {
                //�Ȼ�ø��˻�������
                zrrJbsj = getGrJBSJ(grxx);
            } catch (Exception ex) {
            }
            //�����ѯ�����˻�������
            if (zrrJbsj != null && zrrJbsj.getJsjdm() != null) {
                if (zrrJbsj.getNsrmc().equals(grxx.nsrmc)) {
                    grxx.setJsjdm(zrrJbsj.getJsjdm());
                } else {
                    throw new ApplicationException("��˰����������Ȼ�˵Ǽǵ���˰�����Ʋ�ƥ�䣡");
                }
            }
            //�����ѯ�������˻�������
            //����Ҫ����һ�����˵Ǽ����ݣ��ӷ������ĸ�����Ϣ�������ݣ��õ����������
            else {
                zrrJbsj = insertGrJBSJ(grxx, ud);
                grxx.setJsjdm(zrrJbsj.getJsjdm());
            }
        } catch (Exception ex) {
            throw ExceptionUtil.getBaseException(ex,
                                                 "��CommonUtil.handleZRR()�����в�ѯ�Ǽǻ�����Ϣʧ��!");
        }
        return grxx;
    }

    /**
     * ���ݴ�����Grxxֵ���󣬲�ѯ��Ȼ�˵ļ��������
     * ���û����Ȼ�˵ļ�������룬������Ȼ�˵Ǽǵ����ݿ��в���һ����
     * ����ȡ���²������Ȼ�˵ļ�������룬�����м���������Grxxֵ����
     *
     * @param grxx Grxx
     * @return Grxx
     */
    public static List handleZRR(List nsrList, UserData ud) throws
            BaseException {
        List l = new ArrayList();
        List sl = new ArrayList();
        try {
            ZRRJBSJ zrrJbsj = null;
            for (int i = 0; i < nsrList.size(); i++) {
                Grxx grxx = (Grxx) nsrList.get(i);
                //�ж��ظ����������жϣ��ǼǱ���᷵�ش���
                /*for (int j=0;j<l.size();j++){
                    Grxx g=(Grxx)l.get(j);
                    if (g.getSfzjlx().equals(grxx.getSfzjlx()) && g.getSfzjhm().equals(grxx.getSfzjhm())){
                 throw new ApplicationException("��˰������("+grxx.nsrmc+")����Ȼ�˵Ǽǵ���˰�����Ʋ�ƥ�䣡");

                    }
                                 }
                 */
                zrrJbsj = null;
                try {
                    //�Ȼ�ø��˻�������
                    zrrJbsj = getGrJBSJ(grxx);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                System.out.println("��Ȼ�˻������ݣ�" + zrrJbsj);
                //�����ѯ�����˻�������
                if (zrrJbsj != null && zrrJbsj.getJsjdm() != null) {
                    if (zrrJbsj.getNsrmc().equals(grxx.nsrmc)) {
                        grxx.setJsjdm(zrrJbsj.getJsjdm());

                        l.add(grxx);
                    } else {
                        throw new ApplicationException("��˰������(" + grxx.nsrmc +
                                ")����Ȼ�˵Ǽǵ���˰�����Ʋ�ƥ�䣡");
                        //throw new ApplicationException("��˰����������Ȼ�˵Ǽǵ���˰�����Ʋ�ƥ�䣡");
                    }
                }
                //�����ѯ�������˻�������
                //����Ҫ����һ�����˵Ǽ����ݣ��ӷ������ĸ�����Ϣ�������ݣ��õ����������
                else {
                    sl.add(grxx);
                }

            }

            for (int i = 0; i < sl.size(); i++) {
                Grxx grxx = (Grxx) sl.get(i);
                zrrJbsj = insertGrJBSJ(grxx, ud);
                grxx.setJsjdm(zrrJbsj.getJsjdm());
                l.add(grxx);
            }
        } catch (Exception ex) {
            throw ExceptionUtil.getBaseException(ex,
                                                 "��CommonUtil.handleZRR()�����в�ѯ�Ǽǻ�����Ϣʧ��!");
        }

        return l;
    }


    /**
     * ȡ��ȫ���Ǽǻ�����Ϣ
     * HashMap��
     * key= JBSJ
     * value=SWDJJBSJ (һ��ֵ����)��
     * key=YHZH
     * value=List(List���Ƕ��YHZHֵ����)��
     * key=TZF
     * value=List(List���Ƕ��TZFֵ����)��
     * key=QYRY�����ˣ�
     * value=QYRY(һ��ֵ����)
     * key= BSRY����˰��Ա��
     * value=QYRY(һ��ֵ����)
     * key= CWRY��������Ա��
     * value=QYRY(һ��ֵ����)
     * �Ǽǻ�����Ϣ��һ������
     * 1.��˰������
     * 2.�Ǽ�ע�����ʹ���
     * 3.�Ǽ�ע����������
     * 4.���ұ�׼��ҵ����
     * 5.���ұ�׼��ҵ����
     * 6.������ϵ����
     * 7.������ϵ����
     * 8.����˰���������
     * 9.����˰����ش���
     * 10.��˰�˾�Ӫ�ص�ַ
     * 11.��Ӫ����ϵ�绰
     * 12.��Ӫ���ʱ�
     * 13. ˰��Ǽ�֤��
     * 14.��˰�����֤������
     * 15.��ҵ�Ǽ�����
     * 16.��˰��״̬
     * ������Ϣ����������
     * 1.������������
     * 2.�����ʺ�
     * 3.�����ʺű�ʶ
     * Ͷ�ʷ���Ϣ����������
     * 1.Ͷ�������֤��
     * 2.Ͷ�������֤������
     * 3.Ͷ��������
     * 4.��ռͶ�ʱ���
     * ���˴�����Ϣ��һ����
     * 1.���˴�������
     * 2.���˴������֤������
     * ��˰��Ա��Ϣ��һ����
     * 1.��˰��Ա����
     * 2.��˰��Ա���֤������
     * ������Ա��Ϣ��һ����
     * 1.������Ա����
     * 2.������Ա���֤������
     * �ǿյ��ֶΣ�
     * ��˰�����ơ�
     * �Ǽ�ע�����ʹ��롢
     * �Ǽ�ע���������ơ�
     * �������С�
     * �����ʺš�
     * �����ʺű�ʶ��
     * ���ұ�׼��ҵ���롢
     * ���ұ�׼��ҵ���ơ�
     * ����˰��������ơ�
     * ����˰����ش��롢
     * ˰��Ǽ�֤�š�
     * ��˰�����֤�����롢
     * ���˴���������
     * ���˴������֤�����롢
     * ��ҵ�Ǽ����ڡ�
     * ��˰��״̬��
     * Ͷ����֤�����͡�
     * Ͷ�������֤�š�
     * Ͷ����������
     * ��ռͶ�ʱ�����
     *
     * @param jsjdm ���������
     * @return HashMap �Ǽǻ�����Ϣ
     * @throws Exception ��ѯ�쳣
     */
    public static HashMap getFgrDjInfo(String jsjdm) throws
            Exception {
        ServiceProxy djS = new ServiceProxy();
        try {
            return djS.getDjInfo(jsjdm);
        } catch (BaseException ex) {
            Debug.printException(ex);
            throw ExceptionUtil.getBaseException(ex, "��ѯ�Ǽǻ�����Ϣʧ��!");
        }
    }

    /**
     * ȡ��ȫ����Ȼ�˵Ǽǻ�����Ϣ
     * ���û�и���Ȼ�˵Ǽ���Ϣ���򷵻�null��
     * ������򷵻�HashMap map map��������Ȼ�˵Ǽǵ�ֵ����(ZRRJBSJ)��
     * ��Ȼ��������Ϣ�б�(List)��ֵ����(FB_JBSJ)����Ȼ�˿ۿ������List��������λ��List��
     * map�е�keyֵ ��DjOuterConstant�е�ZRRJBSJ��ZRRYHZH��ZRRFB��ZRRKKQK��ZRRFWDW
     *
     * @param  zjlxdm  ֤�����ʹ��� ����2λ ����Ϊ��
     * @param  zjhm  ֤������ ����30λ ����Ϊ��
     * @param  gjdm  ������ ����3λ ����Ϊ��
     * @return HashMap �Ǽ���Ȼ�˻�����Ϣ
     * @throws Exception ��ѯ�쳣
     */
    public static HashMap getGrDjInfo(Grxx grxx) throws
            Exception {
        ServiceProxy djS = new ServiceProxy();
        try {
            return djS.getZrrDjInfo(grxx.sfzjlx, grxx.sfzjhm, grxx.gjdm);
        } catch (BaseException ex) {
            throw ExceptionUtil.getBaseException(ex, "��ѯ���˵Ǽǻ�����Ϣʧ��!");
        }
    }

    /**
     * ȡ�õǼǻ�����Ϣ
     * @param jsjdm ���������
     * @return SWDJJBSJ �Ǽǻ�����Ϣֵ����
     * @throws Exception ��ѯ�쳣
     */
    public static SWDJJBSJ getFgrJBSJ(String jsjdm) throws
            Exception {
        SWDJJBSJ ret = null;
        ServiceProxy djS = new ServiceProxy();
        try {
            HashMap djMap = djS.getDjInfo(jsjdm);
            ret = (SWDJJBSJ) djMap.get("JBSJ");
        } catch (BaseException ex) {
            throw ExceptionUtil.getBaseException(ex, "��ѯ�Ǽǻ�����Ϣʧ��!");
        }
        return ret;
    }

    /**
     * ȡ�õǼǻ�����Ϣ
     * @param jsjdm ���������
     * @return SWDJJBSJ �Ǽǻ�����Ϣֵ����
     * @throws Exception ��ѯ�쳣
     */
    public static ZRRJBSJ getGrJBSJ(String jsjdm) throws
            Exception {
        ZRRJBSJ ret = null;
        ServiceProxy djS = new ServiceProxy();
        try {
            HashMap djMap = djS.getZrrDjInfo(jsjdm);
            ret = (ZRRJBSJ) djMap.get("ZRRJBSJ");
        } catch (BaseException ex) {
            throw ExceptionUtil.getBaseException(ex, "��ѯ�Ǽǻ�����Ϣʧ��!");
        }
        return ret;
    }

    /**
     * ȡ����˰��������Ϣ
     * @param jsjdm ���������
     * @return List YHZHֵ�����б�
     * @throws Exception ��ѯ�쳣
     */
    public static List getYHZH(String jsjdm) throws
            Exception {
        ServiceProxy djS = new ServiceProxy();
        List ret = null;
        try {
            ret = (List) djS.getDjInfo(jsjdm).get("YHZH");
        } catch (BaseException ex) {
            throw ExceptionUtil.getBaseException(ex, "��ѯ������Ϣʧ��!");
        }
        return ret;

    }

    /**
     * ȡ��Ȼ�˻����Ǽ���Ϣ
     * @param  zjlxdm  ֤�����ʹ��� ����2λ ����Ϊ��
     * @param  zjhm  ֤������ ����30λ ����Ϊ��
     * @param  gjdm  ������ ����3λ ����Ϊ��
     * @return  ZRRJBSJֵ����
     * @throws Exception ��ѯ�쳣
     */
    public static ZRRJBSJ getGrJBSJ(Grxx grxx) throws Exception {
        ServiceProxy djS = new ServiceProxy();
        ZRRJBSJ zrrJbsj = new ZRRJBSJ();
        try {
            //Map djMap = djS.getZrrDjInfo(grxx.sfzjlx, grxx.sfzjhm, grxx.gjdm);
            Map djMap = DJHelper.getZrrDjInfo(grxx.sfzjlx, grxx.sfzjhm,
                                              grxx.gjdm);
            zrrJbsj = (ZRRJBSJ) djMap.get(DjOuterConstant.ZRRJBSJ);
            Debug.out("ͨ���ӿڻ�ȡ��Ȼ�˻�������" + zrrJbsj);
            if (zrrJbsj != null) {
                Debug.out("==>��˰�����ƣ�" + zrrJbsj.getNsrmc());
                Debug.out("==>��������룺" + zrrJbsj.getJsjdm());
            }
            if (zrrJbsj == null) {
                throw new ApplicationException("�����ڸ���Ȼ����Ϣ");
            }
        } catch (BaseException ex) {
            throw ExceptionUtil.getBaseException(ex, "��ѯ��Ȼ����Ϣʧ��!");
        }
        return zrrJbsj;
    }

    public static String checkZrr(String sfzjlx, String sfzjhm, String nsrmc,
                                  String gjdm, String cjr, String swjgzzjgdm) throws
            Exception {
        ServiceProxy djS = new ServiceProxy();
        ZRRJBSJ zrrJbsj = new ZRRJBSJ();
        try {
            Map djMap = djS.getZrrDjInfo(sfzjlx, sfzjhm, gjdm);
            zrrJbsj = (ZRRJBSJ) djMap.get(DjOuterConstant.ZRRJBSJ);
        } catch (BaseException ex) {
            throw ExceptionUtil.getBaseException(ex, "��ѯ��Ȼ����Ϣʧ��!");
        }

        //�����ѯ�����˻�������
        if (zrrJbsj != null && zrrJbsj.getJsjdm() != null) {
            return zrrJbsj.getJsjdm();
        }
        //�����ѯ�������˻�������
        //����Ҫ����һ�����˵Ǽ����ݣ��ӷ������ĸ�����Ϣ�������ݣ��õ����������
        else {

            HashMap zrrMap = new HashMap();
            zrrMap.put(DjOuterConstant.ZJLXDM, sfzjlx);
            zrrMap.put(DjOuterConstant.ZJHM, sfzjhm);
            zrrMap.put(DjOuterConstant.GJDM, gjdm);
            zrrMap.put(DjOuterConstant.NSRMC, nsrmc);
            zrrMap.put(DjOuterConstant.SWJGZZJGDM, swjgzzjgdm);
            zrrMap.put(DjOuterConstant.CZRY, cjr);
            zrrMap.put(DjOuterConstant.CZRQ,
                       new java.sql.Timestamp(System.currentTimeMillis()));

            try {
                zrrJbsj = djS.insertZrrDjInfo(zrrMap);
                if (zrrJbsj == null) {
                    throw new ApplicationException("���� " + nsrmc + " ��Ȼ����Ϣ����");
                }
            } catch (BaseException ex) {
                throw ExceptionUtil.getBaseException(ex, "������Ȼ����Ϣʧ��!");
            }

        }

        return zrrJbsj.getJsjdm();
    }

    /**
     * ���ǼǵĿ��в�����Ȼ�˻����Ǽ���Ϣ

     HashMap ��Ӧ��keyֵ������com.ttsoft.bjtax.dj. DjOuterConstant
     public static final String ZJLXDM = "zjlxdm";//String ֤�����ʹ��� ����Ϊ��
         public static final String ZJHM = "zjhm";//String ֤������ ����Ϊ��
         public static final String GJDM = "gjdm";//String �������� ����Ϊ��
         public static final String NSRMC = "nsrmc";//String ��˰������ ����Ϊ��
     public static final String SWJGZZJGDM = "swjgzzjgdm";//String ˰�������֯��������  ����Ϊ��
         public static final String CZRY = "czry";//String ������Ա  ����Ϊ��
     public static final String CZRQ = "czrq";//TimeStamp �������ڣ���Ҫ���飬�Ƿ񴫵ݸò�����
     *
     *
     * @param  Grxx  ������Ϣֵ����
     * @return  ZRRJBSJֵ����
     * @throws Exception ��ѯ�쳣
     */
    public static ZRRJBSJ insertGrJBSJ(Grxx grxx, UserData ud) throws Exception {
        ServiceProxy djS = new ServiceProxy();
        HashMap zrrMap = new HashMap();
        zrrMap.put(DjOuterConstant.ZJLXDM, grxx.getSfzjlx());
        zrrMap.put(DjOuterConstant.ZJHM, grxx.getSfzjhm());
        zrrMap.put(DjOuterConstant.GJDM, grxx.getGjdm());
        zrrMap.put(DjOuterConstant.NSRMC, grxx.getNsrmc());
        zrrMap.put(DjOuterConstant.SWJGZZJGDM, ud.getSsdwdm());
        zrrMap.put(DjOuterConstant.CZRY, grxx.getCjr());
        zrrMap.put(DjOuterConstant.CZRQ, grxx.getCjrq());
        ZRRJBSJ GrJBSJ = null;
        try {
            GrJBSJ = djS.insertZrrDjInfo(zrrMap);
            if (GrJBSJ == null) {
                throw new ApplicationException("���� " + grxx.nsrmc + " ��Ȼ����Ϣ����");
            }
        } catch (BaseException ex) {
            throw ExceptionUtil.getBaseException(ex, "������Ȼ����Ϣʧ��!");
        }
        return GrJBSJ;
    }

    /**
     * �õ��걨���
     * @return String
     */
    public static String getJksSbbh(String jsjdm) throws
            BaseException {
        String sbbh = null;
        try {
            sbbh = com.ttsoft.bjtax.shenbao.proxy.ServiceProxy.getSbbh(jsjdm);
            if (sbbh == null) {
                throw new ApplicationException("�������걨���");
            }
        } catch (BaseException ex) {
            throw ExceptionUtil.getBaseException(ex, "��ѯ�걨���ʧ��!");
        }
        return sbbh;
    }

    /**
     * �õ�������˰֤ʱ����Ҫ�ļ��������
     * @return String
     */
    public static String getWszJsjdm(UserData ud, Connection con) throws
            BaseException {
        String jsjdm = "";
        String sql =
                "select jsjdm from dmdb.gy_dm_swjgzzjg where SWJGZZJGDM = '" +
                ud.ssdwdm + "'";
        try {
            PreparedStatement pst = null;
            ResultSet rs = null;
            pst = con.prepareStatement(sql);

            rs = pst.executeQuery();

            if (rs.next()) {
                jsjdm = rs.getString(1);
            }
            //��˰�������֯���������õ����������
            //����˰�ѵĽӿڻ��
//          /** @todo �ĳ���ȷ�ķ���ȡ��˰�������֯�ṹ���� */
//          jsjdm = CodeUtils.getCodeMapValue("ZHSB_SWJGZZJG", "swjgzzjgdm", ud.ssdwdm,"jsjdm");
        } catch (Exception ex) {
//          throw ExceptionUtil.getBaseException(ex, "�����˰֤��������ļ���������ʱ�����!");
            ErrorLog.makeLog(ud, "��˰�걨���գ�WszProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "ʧ��");
//             throw ExceptionUtil.getBaseException(ex, ex.getMessage());
        }
        return jsjdm;
    }

    /**
     * ����˰�������֯���������ѯ�������
     * @param swjgzzjgdm ˰�������֯��������
     * @return swjgzzjg ˰�������֯������Ϣ
     * @throws java.lang.Exception �����쳣
     */
    public static Swjgzzjg getSwjgzzjg(UserData ud, Connection con) throws
            Exception {
        Swjgzzjg swjgzzjg = new Swjgzzjg();
        swjgzzjg.setSwjgzzjgdm(ud.ssdwdm);

        try {
            swjgzzjg = (Swjgzzjg) DAOFactory.getInstance().getSwjgzzjgDAO().get(
                    swjgzzjg, con);
        } catch (Exception ex) {
            throw ExceptionUtil.getBaseException(ex, "��ѯ����ʧ��!");
        }
        return swjgzzjg;
    }

    /**
     * ����˰�������֯���������ѯ�������
     * @param swjgzzjgdm ˰�������֯��������
     * @return swjgzzjg ˰�������֯������Ϣ
     * @throws java.lang.Exception �����쳣
     */
    public static Swjgzzjg getSwjgzzjg(String swjgzzjgdm, Connection con) throws
            Exception {
        Swjgzzjg swjgzzjg = new Swjgzzjg();
        swjgzzjg.setSwjgzzjgdm(swjgzzjgdm);

        try {
            swjgzzjg = (Swjgzzjg) DAOFactory.getInstance().getSwjgzzjgDAO().get(
                    swjgzzjg, con);
        } catch (Exception ex) {
            throw ExceptionUtil.getBaseException(ex, "��ѯ����ʧ��!");
        }
        return swjgzzjg;
    }

    /**
     * ����˰�������֯���������ѯ�������
     * @param swjgzzjgdm ˰�������֯��������
     * @return swjgzzjg ˰�������֯������Ϣ
     * @throws java.lang.Exception �����쳣
     */
    public static Yskm getYskm(String szsmdm, Connection con) throws Exception {
        Yskm yskm = new Yskm();
        SzsmYskm sy = new SzsmYskm();
        sy.setSzsmdm(szsmdm);

        try {
            sy = (SzsmYskm) DAOFactory.getInstance().getSzsmYskmDAO().get(sy,
                    con);
            yskm.setYskmdm(sy.getMryskmdm());
            System.out.println("sy.getMryskmdm()��˰����ͨ������ȡ��Ԥ���Ŀ���룺" +
                               sy.getMryskmdm());
            System.out.println("yskm.getYskmdm()��˰����ͨ������ȡ��Ԥ���Ŀ���룺" +
                               yskm.getYskmdm());
            yskm = (Yskm) DAOFactory.getInstance().getYskmDAO().get(yskm, con);
        } catch (Exception ex) {
            throw ExceptionUtil.getBaseException(ex, "��ѯ����ʧ��!");
        }
        return yskm;
    }

    /**
     * ͨ��sequence ����걨���ܵ���
     * ���ܵ��ŵĳ���Ϊ8λ
     * @param con ���ݿ�����
     * @return ���ػ��ܵ���
     * @throws Exception �����쳣
     */
    public static String getSequenceOfSbhzd(Connection con) throws
            Exception {
        String sequence = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // ������ݿ�����
            String sql = "select sbdb.seq_sb_hzdh.nextval from dual";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            rs.next();
            //���ܵ���
            long hzdhindex = rs.getLong("nextval");
            NumberFormat nmbFormat = new DecimalFormat("00000000");
            sequence = nmbFormat.format(hzdhindex);

            return sequence;
        } catch (Exception ex) {
            throw ExceptionUtil.getBaseException(ex, "�õ����ܵ��ŵ����˳���ʧ��!");
        } finally {
            try {
                pstmt.close();
            } catch (Exception ex) {

            }
            try {
                rs.close();
            } catch (Exception ex) {

            }
        }
    } //End of getSequenceOfSbhzd


    /**
     * ͨ��sequence ����˰֤���
     * ��˰֤��ŵĳ���Ϊ4λ
     * @param con ���ݿ�����
     * @return ���ػ��ܵ���
     * @throws Exception �����쳣
     */
    public String getSequenceOfWSZXH(Connection con) throws
            Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // ������ݿ�����
            String sql = "select sbdb.seq_sb_wszxh.nextval from dual";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            rs.next();
            //��˰֤���
            String wszxh = rs.getString("nextval");
            return wszxh;
        } catch (Exception ex) {
            throw ExceptionUtil.getBaseException(ex, "�õ����к�ʧ��!");
        } finally {
            try {
                pstmt.close();
            } catch (Exception ex) {

            }
            try {
                rs.close();
            } catch (Exception ex) {

            }
        }
    } //End of getSequenceOfWSZXH

    public static HashMap getJmsj(List cqL, List gfL, Spjgxx sp) {
        HashMap map = new HashMap();
        //	List cqL = bo.getCqList();
        //	List gfL = bo.getGyzfList();
        //	Spjgxx sp = bo.getVoSpjgxx();
        double cqze = 0;
        double gyzfze = 0;
        double zcspze = 0;

        if (cqL != null) {
            for (int i = 0; i < cqL.size(); i++) {
                Jsblcq cq = (Jsblcq) cqL.get(i);
                cqze = cqze + cq.getBcsybce().doubleValue();
            }
        }

        if (gfL != null) {
            for (int i = 0; i < gfL.size(); i++) {
                Jsblgyzf gf = (Jsblgyzf) gfL.get(i);
                gyzfze = gyzfze + gf.getBcdke().doubleValue();
            }
        }

        if (sp != null && sp.getJmsje() != null) {
            zcspze = sp.getJmsje().doubleValue();
        }
        Debug.out("����ס���ֿ��� �� " + gyzfze);
        Debug.out("��Ǩ����˰���" + cqze + "Ԫ");
        Debug.out("������������˰���" + zcspze + "Ԫ");
        map.put(Constants.JE_CQJMJE, new BigDecimal(cqze));
        map.put(Constants.JE_ZCSPJMJE, new BigDecimal(zcspze));
        map.put(Constants.JE_QYZFBCDKE, new BigDecimal(gyzfze));

        return map;

    }

    public static HashMap getJmsj(String sbbh, Connection conn) throws
            ApplicationException {
        HashMap map = new HashMap();
        PreparedStatement pst = null;
        ResultSet rs = null;
        StringBuffer sql = new StringBuffer();
        sql
                .append(
                        "select a.sbbh, nvl(t1.cq_total,0) cq_total, nvl(t2.gyzf_total,0) gyzf_total, nvl(t3.zcsp_total,0) zcsp_total ")
                .append(" from qsdb.qs_jl_sbzb a, ")
                .append(" (select b.sbbh, sum(b.bcsybce) cq_total ")
                .append(
                        " from qsdb.qs_jl_sbzb a, qsdb.qs_jl_sbcqgl b, qsdb.qs_jl_jsblcq c ")
                .append(
                        " where  a.sbbh = b.sbbh and b.cqxyh = c.cqxyh and a.BLJMSBS!='")
                .append(Constants.ZB_BLJMSBS_BZ)
                .append("'")
                .append(" group by b.sbbh) t1, ")
                .append(" (select b.sbbh, sum(b.bcdke) gyzf_total ")
                .append(
                        " from qsdb.qs_jl_sbzb a, qsdb.qs_jl_sbgyzf b, qsdb.qs_jl_jsblgyzf c ")
                .append(
                        " where  a.sbbh = b.sbbh and b.yggyzfqszsh = c.yggyzfqszsh  and a.BLJMSBS!='")
                .append(Constants.ZB_BLJMSBS_BZ)
                .append("'")
                .append(" group by b.sbbh) t2, ")
                .append(" (select b.sbbh, sum(b.jmsje) zcsp_total ")
                .append(" from qsdb.qs_jl_sbzb a, qsdb.qs_jl_spjgxx b ")
                .append(" where  a.sbbh = b.sbbh  and a.BLJMSBS!='")
                .append(Constants.ZB_BLJMSBS_BZ)
                .append("'")
                .append(" group by b.sbbh) t3 ")
                .append(
                        " where a.sbbh = t1.sbbh(+) and a.sbbh = t2.sbbh(+) and a.sbbh = t3.sbbh(+) and ")
                .append(" a.sbbh='").append(sbbh).append("'");

        Debug.out("����˰����ܹ�ʽ��" + sql.toString());
        try {
            //ִ�� sql ��䣻
            double cqze = 0;
            double gyzfze = 0;
            double zcspze = 0;
            pst = conn.prepareStatement(sql.toString());
            rs = pst.executeQuery();
            if (rs.next()) {
                cqze = rs.getDouble("cq_total"); //ȡ�ò�Ǩ���ܽ��
                gyzfze = rs.getDouble("gyzf_total"); //ȡ�ù���ס�����ܽ��
                zcspze = rs.getDouble("zcsp_total"); //ȡ�������������ܽ��
            }
            Debug.out("����ס���ֿ��� �� " + gyzfze);
            Debug.out("��Ǩ����˰���" + cqze + "Ԫ");
            Debug.out("������������˰���" + zcspze + "Ԫ");

            map.put(Constants.JE_CQJMJE, new BigDecimal(cqze));
            map.put(Constants.JE_ZCSPJMJE, new BigDecimal(zcspze));
            map.put(Constants.JE_QYZFBCDKE, new BigDecimal(gyzfze));

        } catch (Exception ex) {
            throw new ApplicationException("��ȡ��˰����ʱ�򣬱���");
        }
        return map;
    }

    public static JghdsjBo getJZSE(SbxxBo bo, Connection conn) throws
            ApplicationException {
        JghdsjBo hdbo = new JghdsjBo();
        try {
            //�����걨��ţ�����걨��������
            Sbzb sbzb = bo.getVoSbzb();
            String sbbh = sbzb.getSbbh();
            FwjhxxBo dfbo = bo.getDfSbxxBo();
            Tufwxx dfTfxx = new Tufwxx();
            if (dfbo != null) {
                dfTfxx = dfbo.getTufwxx();
            }

            //�����걨��Ż�ã����ط��ݵĻ�����Ϣ����
            //�Ӷ��õ��ɽ��۸񡢷������������õ��Ƿ������ͨסլ�ļ������յ�����
            Tufwxx tfxx = bo.getVoTufwxx();

            Map jmMap = new HashMap();
            if (!Constants.ZB_BLJMSBS_BZ.equals(sbzb.getBljmsbs())) {
                jmMap = getJmsj(bo.getCqList(), bo.getGyzfList(),
                                bo.getVoSpjgxx());
            }
            double sl = getSl(sbzb.getSl(), getZbz(Constants.ZCWH_SL, conn));

            //�����ͨסլ�������������
            double puzfpjjg = StringUtil.getDoubleValue(getZbz(Constants.
                    ZCWH_PTZZJMBZ, conn)); //�������Ʒ��סլƽ���ۼ�

            hdbo = getJZSE(sbzb, tfxx, dfTfxx, sl, puzfpjjg, jmMap);
        } catch (Exception ex) {
            ex.printStackTrace();
            Debug.out(ex);
            throw new ApplicationException("��ȡ��˰����ʱ�򣬱���");
        }
        return hdbo;
    }

    public static JghdsjBo getJZSE(PldrBo bo, Connection conn) throws
            ApplicationException {
        JghdsjBo hdbo = new JghdsjBo();
        try {
            //�����걨��ţ�����걨��������
            Sbzb sbzb = bo.getSbzb();
            String sbbh = sbzb.getSbbh();

            Tufwxx dfTfxx = new Tufwxx();

            //�����걨��Ż�ã����ط��ݵĻ�����Ϣ����
            //�Ӷ��õ��ɽ��۸񡢷������������õ��Ƿ������ͨסլ�ļ������յ�����
            Tufwxx tfxx = bo.getTufwxx();

            Map jmMap = new HashMap();
            if (!Constants.ZB_BLJMSBS_BZ.equals(sbzb.getBljmsbs())) {
                jmMap = getJmsj(bo.getCqxxList(), bo.getGyzfxxList(),
                                bo.getSpjgxx());
            }
            double sl = getSl(sbzb.getSl(), getZbz(Constants.ZCWH_SL, conn));

            //�����ͨסլ�������������
            double puzfpjjg = StringUtil.getDoubleValue(getZbz(Constants.
                    ZCWH_PTZZJMBZ, conn)); //�������Ʒ��סլƽ���ۼ�

            hdbo = getJZSE(sbzb, tfxx, dfTfxx, sl, puzfpjjg, jmMap);
        } catch (Exception ex) {
            ex.printStackTrace();
            Debug.out(ex);
            throw new ApplicationException("��ȡ��˰����ʱ�򣬱���");
        }
        return hdbo;
    }

    //˵�����÷��������ҳ����"��������(˰����Ա)"ģ��
    public static JghdsjBo getJZSE2(PldrBo2 bo, Connection conn) throws
            ApplicationException {
        JghdsjBo hdbo = new JghdsjBo();
        try {
            //�����걨��ţ�����걨��������
            Sbzb sbzb = bo.getSbzb();
            String sbbh = sbzb.getSbbh();

            Tufwxx dfTfxx = new Tufwxx();

            //�����걨��Ż�ã����ط��ݵĻ�����Ϣ����
            //�Ӷ��õ��ɽ��۸񡢷������������õ��Ƿ������ͨסլ�ļ������յ�����
            Tufwxx tfxx = bo.getTufwxx();

            Map jmMap = new HashMap();
            if (!Constants.ZB_BLJMSBS_BZ.equals(sbzb.getBljmsbs())) {
                jmMap = getJmsj(bo.getCqxxList(), bo.getGyzfxxList(),
                                bo.getSpjgxx());
            }
            double sl = getSl(sbzb.getSl(), getZbz(Constants.ZCWH_SL, conn));

            //�����ͨסլ�������������
            double puzfpjjg = StringUtil.getDoubleValue(getZbz(Constants.
                    ZCWH_PTZZJMBZ, conn)); //�������Ʒ��סլƽ���ۼ�

            hdbo = getJZSE(sbzb, tfxx, dfTfxx, sl, puzfpjjg, jmMap);
        } catch (Exception ex) {
            ex.printStackTrace();
            Debug.out(ex);
            throw new ApplicationException("��ȡ��˰����ʱ�򣬱���");
        }
        return hdbo;
    }

    public static JghdsjBo getJZSE(JmsbxxBo bo, Connection conn) throws
            ApplicationException {
        JghdsjBo hdbo = new JghdsjBo();
        try {
            //�����걨��ţ�����걨��������
            Sbzb sbzb = bo.getVoSbzb();
            String sbbh = sbzb.getSbbh();
            FwjhxxBo dfbo = bo.getDfSbxxBo();
            Tufwxx dfTfxx = new Tufwxx();
            if (dfbo != null) {
                dfTfxx = dfbo.getTufwxx();
            }

            //�����걨��Ż�ã����ط��ݵĻ�����Ϣ����
            //�Ӷ��õ��ɽ��۸񡢷������������õ��Ƿ������ͨסլ�ļ������յ�����
            Tufwxx tfxx = bo.getVoTufwxx();

            Map jmMap = new HashMap();
            if (!Constants.ZB_BLJMSBS_BZ.equals(sbzb.getBljmsbs())) {
                jmMap = getJmsj(bo.getCqList(), bo.getGyzfList(),
                                bo.getVoSpjgxx());
            }
            double sl = getSl(sbzb.getSl(), getZbz(Constants.ZCWH_SL, conn));

            //�����ͨסլ�������������
            double puzfpjjg = StringUtil.getDoubleValue(getZbz(Constants.
                    ZCWH_PTZZJMBZ, conn)); //�������Ʒ��סլƽ���ۼ�

            hdbo = getJZSE(sbzb, tfxx, dfTfxx, sl, puzfpjjg, jmMap);
        } catch (Exception ex) {
            ex.printStackTrace();
            Debug.out(ex);
            throw new ApplicationException("��ȡ��˰����ʱ�򣬱���");
        }
        return hdbo;
    }


    private static double getSl(BigDecimal sbsl, String zcsl) {
        double sl = 0.03;

        if (sbsl != null && sbsl.doubleValue() > 0) {
            sl = sbsl.doubleValue();
        } else {
            sl = StringUtil.getDoubleValue(zcsl);
        }
        //Debug.out("˰�ʣ� " + sl + "");
        return sl;
    }

    private static String getZbz(String zcdm, Connection conn) throws Exception {
        Zcwh zcwh = new Zcwh();
        zcwh.zbdm = zcdm;
        zcwh = (Zcwh) DAOFactory.getInstance().getZcwhDAO().get(zcwh, conn);
        return zcwh.zbz;
    }

    private static JghdsjBo getJZSE(Sbzb sbzb, Tufwxx tfxx, Tufwxx dfTfxx,
                                    double sl, double puzfpjjg, Map jmMap) throws
            Exception {
        // Map map = new HashMap();
        JghdsjBo hdbo = new JghdsjBo();
        double cjjgrmb = getCjjg(tfxx);
        //Debug.out("ȷ�����ݵĳɽ��۸� " + cjjgrmb + "Ԫ");

        //���ݽ������ֵĳɽ��۸��ȷ��
        double fwjh_cjjgrmb = getCjjg(dfTfxx);
        //Debug.out("ȷ�������ķ��ݵĳɽ��۸� " + fwjh_cjjgrmb + "Ԫ");

        double cqze = 0;
        double gyzfze = 0;
        double zcspze = 0;
        if (jmMap.get(Constants.JE_CQJMJE) != null) {
            cqze = ((BigDecimal) jmMap.get(Constants.JE_CQJMJE)).doubleValue();
        }
        if (jmMap.get(Constants.JE_QYZFBCDKE) != null) {
            gyzfze = ((BigDecimal) jmMap.get(Constants.JE_QYZFBCDKE)).
                     doubleValue();
        }
        if (jmMap.get(Constants.JE_ZCSPJMJE) != null) {
            zcspze = ((BigDecimal) jmMap.get(Constants.JE_ZCSPJMJE)).
                     doubleValue();
        }
        //��Ǩ������=����ʹ�õ��ܲ������˰��
        cqze = cqze * sl;
        cqze = cqze + 0.0001;
        cqze = DataConvert.round(cqze, 2);
        //Debug.out("��Ǩ������ܽ�� " + cqze + "Ԫ");

        //����˰��=���ɽ��۸񡪳����ѹ�����ס���ı��εֿ۶���ݽ����ĳɽ��۸񣩡�˰��
        double jzse = (cjjgrmb - gyzfze - fwjh_cjjgrmb) * sl;
        //�����������Ĳ���ֵ���������ֵ����java��BigDecimal�����Ծ�����
        jzse = jzse + 0.0001;
        jzse = DataConvert.round(jzse, 2);
        if (jzse < 0) {
            jzse = 0;
        }
        //Debug.out("����˰��3 " + jzse + "Ԫ");

        //��ͨסլ��˰��ϵͳ�Զ�������
        double puzzjmje = getPtzzJm(sbzb, tfxx, jzse, cqze, cjjgrmb, puzfpjjg);

        //�������÷�����˰��,add by Ha Zhengze 20081218
        double jjsyfjmje = getJjsyfJm(sbzb, tfxx, jzse, cqze, cjjgrmb, puzfpjjg);

        //Ӧ��˰��=����˰���Ǩ�������ͨסլ��˰��˰�������˵ļ���˰���
        //double ynse = jzse - cqze - puzzjmje - zcspze;
        //Ӧ��˰��=����˰���Ǩ�������ͨסլ��˰���-�������÷������˰�������˵ļ���˰���,add by Ha Zhengze 20081218
        double ynse = jzse - cqze - puzzjmje-jjsyfjmje- zcspze;//�������÷�����˰��,add by Ha Zhengze 20081218
        ynse = DataConvert.round(ynse, 2);
        if (ynse < 0) {
            ynse = 0;
        }
        Debug.out("Ӧ��˰�� " + ynse + "Ԫ");

        //����˰�ܽ��
        //double jmszje = cqze + zcspze + puzzjmje;
        double jmszje = cqze + zcspze + puzzjmje+jjsyfjmje;//�������÷�����˰��,add by Ha Zhengze 20081224
        jmszje = DataConvert.round(jmszje, 2);
        if (jmszje > jzse) {
            jmszje = jzse;
        }
        Debug.out("����˰�ܽ�� " + jmszje + "Ԫ");

        //��˰����=�ɽ��۸�-�۳������ѹ�����ס������ - ���ݽ����ĳɽ��۸�
        double jsyj = cjjgrmb - gyzfze - fwjh_cjjgrmb;
        jsyj = DataConvert.round(jsyj, 2);
        if (jsyj < 0) {
            jsyj = 0;
        }
        //Debug.out("��˰���� " + jsyj + "Ԫ");

        //������˰=��˰���ݡ�˰��
        double jzqs = jsyj * sl;
        jzqs = jzqs + 0.0001;
        jzqs = DataConvert.round(jzqs, 2);
        //Debug.out("������˰ " + jzqs + "Ԫ");

        //ʵ��Ӧ����˰=������˰�������׼������˰��Ӧ����Ӧ��˰�
        //�����޸ģ�Ha Zhengze �����׼������˰�����������÷�������
        hdbo.setCjjgrmb(new BigDecimal(cjjgrmb));
        hdbo.setFwjhCjjg(new BigDecimal(fwjh_cjjgrmb));
        hdbo.setCqjmje(new BigDecimal(cqze));
        hdbo.setGyzfjmje(new BigDecimal(gyzfze));
        hdbo.setJmzje(new BigDecimal(jmszje));
        hdbo.setJsyj(new BigDecimal(jsyj));
        hdbo.setJzqs(new BigDecimal(jzqs));
        hdbo.setJzse(new BigDecimal(jzse));
        hdbo.setPtzzjmje(new BigDecimal(puzzjmje));
        hdbo.setJjsyfjmje(new BigDecimal(jjsyfjmje));
        hdbo.setSl(new BigDecimal(sl));
        hdbo.setYnse(new BigDecimal(ynse));
        hdbo.setSjyz(new BigDecimal(ynse));

        return hdbo;
    }

    /**
     * �ж��Ƿ���ͨ��׼סլ
     * @param sbzb Sbzb �걨����
     * @param tfxx Tufwxx ���ط�����Ϣ
     * @param cjjgrmb double �ɽ��۸������
     * @return boolean true-��,false-��;
     * @throws Exception Ӧ���쳣
     */
    public static boolean isPtbzzz(Sbzb sbzb, Tufwxx tfxx, double cjjgrmb) throws
            Exception {
        boolean rtnFlag = false;
        Timestamp sj=null; 
        //���ַ�
        if (Constants.TUFWXX_SFESF_TRUE.equals(tfxx.sfesf)) {
        	sj = tfxx.getHtqdsj(); //��ͬǩ������
        //�·�	
        }else{
         sj=sbzb.getSbrq(); //�걨����
        }
        Timestamp NEW_JZRQ2011 = DateUtils.parseTimestamp("20111210"); //�����߻�׼�ָ�ʱ��
        
        if(!sj.before(NEW_JZRQ2011)){//��20111210֮��(��20111210)
        	rtnFlag=isPtbzzzNew(sbzb, tfxx, cjjgrmb);
        }else{
        	rtnFlag=isPtbzzzOld(sbzb, tfxx, cjjgrmb);
        }
        
        return rtnFlag;
    }
    
    /**
     * �ж��Ƿ���ͨ��׼סլ(���±�׼�ж�)
     * @param sbzb Sbzb �걨����
     * @param tfxx Tufwxx ���ط�����Ϣ
     * @param cjjgrmb double �ɽ��۸������
     * @return boolean true-��,false-��;
     * @throws Exception Ӧ���쳣
     */
    public static boolean isPtbzzzNew(Sbzb sbzb, Tufwxx tfxx, double cjjgrmb) throws
            Exception {
        boolean rtnFlag = false;
        //��������
        Timestamp htqdsj = tfxx.getHtqdsj(); //��ͬǩ������
        BigDecimal fwjzmj = tfxx.getFwjzmj(); //���ݽ������
        double dbl_fwjzmj = fwjzmj.doubleValue(); //���ݽ������doubleֵ
        double cjjg = cjjgrmb; //�ɽ��۸�
        double dwcjjg = Arith.div(cjjgrmb, dbl_fwjzmj, 2); //��λ�ɽ��۸�
        String fwszqydm = tfxx.getTdjc(); //���������������
        String rjl = tfxx.getRjl();
        System.out.println("==========�ж��Ƿ���ͨ��׼סլ==========");
        System.out.println("htqdsj=" + htqdsj.toString() + "|"
                           + "dbl_fwjzmj=" + dbl_fwjzmj + "|"
                           + "cjjg=" + cjjg + "|"
                           + "dwcjjg=" + dwcjjg + "|"
                           + "fwszqydm=" + fwszqydm + "|"
                           + "rjl=" + rjl);

        //��������
        double OLD_CJJG_PER_UNIT = 9432.00; //ԭƽ�����׼۸��׼
        //Timestamp NEW_JZRQ = DateUtils.parseTimestamp("20050601"); //���߻�׼�ָ�ʱ��
        Timestamp NEW_JZRQ2011 = DateUtils.parseTimestamp("20111210"); //�����߻�׼�ָ�ʱ��
       // String fwszqydms[] = {"01", "02", "03", "04","11","12","13","14","15","16","17"}; //���������������
       // double fwszqyjgs[] = {2150000.00, 1750000.00, 1650000.00, 1000000.00,38880.00,34560.00,32400.00,28080.00,25920.00,21600.00,17280.00}; //������������ƽ���۸�
       
        String fwszqydms[] = {"11","12","13","14","15","16","17"}; //���������������
        double fwszqyjgs[] = {38880.00,34560.00,32400.00,28080.00,25920.00,21600.00,17280.00}; //������������ƽ���۸�
 
        
        //��ʼ��׼�ж�
        double fwszqyjg=0.00;
        if (Constants.ZB_YHBS_GR.equals(sbzb.yhbs) &&
            Constants.TDFWLB.equals(tfxx.fwlxdm)) { //���Ϊ���������ط�������Ϊ����סլ������ж���ͨ��׼סլ
            if ("00".equals(fwszqydm)) {
                throw new Exception("û��ѡ������������,�޷��ж���");
            }
            
            
            Timestamp sj=null; 
            //���ַ�
            if (Constants.TUFWXX_SFESF_TRUE.equals(tfxx.sfesf)) {
            	sj = tfxx.getHtqdsj(); //��ͬǩ������
            //�·�	
            }else{
             sj=sbzb.getSbrq(); //�걨����
            }
            
           // if(!sj.before(NEW_JZRQ2011)){//�������20111210��(��20111210)
            	
            	///��ȡ����ָÿƽ�׼۸�����
                for (int i = 0; i < fwszqydms.length; i++) {
                    if (fwszqydms[i].equals(fwszqydm)) {
                        fwszqyjg = fwszqyjgs[i];
                        break;
                    }
                }
                //�ж�����ƽ���۸��Ƿ����
                if (fwszqyjg == 0.00) {
                    throw new Exception("������������������" + fwszqydm);
                }
                //
                if ("01".equals(rjl) && dbl_fwjzmj <= 140 && dwcjjg <= fwszqyjg) { //����������Ϊ��ͨ��׼סլ
                    rtnFlag = true;
                } else {
                    rtnFlag = false;
                }
            	
//            }else if (sj.after(NEW_JZRQ)&&sj.before(NEW_JZRQ2011)) { //���������20050601����20111210ǰ
//                ///��ȡ����ָ���۸��1.2��
//                for (int i = 0; i < fwszqydms.length; i++) {
//                    if (fwszqydms[i].equals(fwszqydm)) {
//                        fwszqyjg = fwszqyjgs[i] * 1.2;
//                        break;
//                    }
//                }
//                //�ж�����ƽ���۸��Ƿ����
//                if (fwszqyjg == 0.00) {
//                    throw new Exception("������������������" + fwszqydm);
//                }
//                //
//                if ("01".equals(rjl) && dbl_fwjzmj <= 140 && cjjg <= fwszqyjg) { //����������Ϊ��ͨ��׼סլ
//                    rtnFlag = true;
//                } else {
//                    rtnFlag = false;
//                }
//
//            } else { //�����ͬǩ��������20050601������ǰ
//                if (dwcjjg <= OLD_CJJG_PER_UNIT) { //�����λ�۸�С�ڵ���ƽ���۸�
//                    rtnFlag = true;
//                } else {
//                    rtnFlag = false;
//                }
//            }
            
            
            
            
        } else {
            rtnFlag = false;
        }
        System.out.println("==>��׼ס���ж����������"+rtnFlag);
        //99.����ֵ
        return rtnFlag;
    } 
    
    /**
     * �ж��Ƿ���ͨ��׼סլ(�þɱ�׼�ж�)
     * @param sbzb Sbzb �걨����
     * @param tfxx Tufwxx ���ط�����Ϣ
     * @param cjjgrmb double �ɽ��۸������
     * @return boolean true-��,false-��;
     * @throws Exception Ӧ���쳣
     */
    public static boolean isPtbzzzOld(Sbzb sbzb, Tufwxx tfxx, double cjjgrmb) throws
            Exception {
        boolean rtnFlag = false;
        //��������
        Timestamp htqdsj = tfxx.getHtqdsj(); //��ͬǩ������
        BigDecimal fwjzmj = tfxx.getFwjzmj(); //���ݽ������
        double dbl_fwjzmj = fwjzmj.doubleValue(); //���ݽ������doubleֵ
        double cjjg = cjjgrmb; //�ɽ��۸�
        double dwcjjg = Arith.div(cjjgrmb, dbl_fwjzmj, 2); //��λ�ɽ��۸�
        String fwszqydm = tfxx.getTdjc(); //���������������
        String rjl = tfxx.getRjl();
        System.out.println("==========�ж��Ƿ���ͨ��׼סլ==========");
        System.out.println("htqdsj=" + htqdsj.toString() + "|"
                           + "dbl_fwjzmj=" + dbl_fwjzmj + "|"
                           + "cjjg=" + cjjg + "|"
                           + "dwcjjg=" + dwcjjg + "|"
                           + "fwszqydm=" + fwszqydm + "|"
                           + "rjl=" + rjl);
        //��������
        double OLD_CJJG_PER_UNIT = 9432.00; //ԭƽ�����׼۸��׼
        Timestamp NEW_JZRQ = DateUtils.parseTimestamp("20050601"); //���߻�׼�ָ�ʱ��
        String fwszqydms[] = {"01", "02", "03", "04"}; //���������������
        double fwszqyjgs[] = {2150000.00, 1750000.00, 1650000.00, 1000000.00}; //������������ƽ���۸�
        //��ʼ��׼�ж�
        double fwszqyjg=0.00;
        if (Constants.ZB_YHBS_GR.equals(sbzb.yhbs) &&
            Constants.TDFWLB.equals(tfxx.fwlxdm)) { //���Ϊ���������ط�������Ϊ����סլ������ж���ͨ��׼סլ
            if ("00".equals(fwszqydm)) {
                throw new Exception("û��ѡ������������,�޷��ж���");
            }
            if (htqdsj.after(NEW_JZRQ)) { //�����ͬǩ��������20050601��
                ///��ȡ����ָ���۸��1.2��
                for (int i = 0; i < fwszqydms.length; i++) {
                    if (fwszqydms[i].equals(fwszqydm)) {
                        fwszqyjg = fwszqyjgs[i] * 1.2;
                        break;
                    }
                }
                //�ж�����ƽ���۸��Ƿ����
                if (fwszqyjg == 0.00) {
                    throw new Exception("������������������" + fwszqydm);
                }
                //
                if ("01".equals(rjl) && dbl_fwjzmj <= 140 && cjjg <= fwszqyjg) { //����������Ϊ��ͨ��׼סլ
                    rtnFlag = true;
                } else {
                    rtnFlag = false;
                }

            } else { //�����ͬǩ��������20050601������ǰ
                if (dwcjjg <= OLD_CJJG_PER_UNIT) { //�����λ�۸�С�ڵ���ƽ���۸�
                    rtnFlag = true;
                } else {
                    rtnFlag = false;
                }
            }
        } else {
            rtnFlag = false;
        }
        System.out.println("==>��׼ס���ж����������"+rtnFlag);
        //99.����ֵ
        return rtnFlag;
    }




    /**
     * ���㾭�����÷�������
     * @param sbzb Sbzb �걨�������
     * @param tfxx Tufwxx ���ط�����Ϣ
     * @param jzse double ����˰��
     * @param cqze double ��Ǩ�ܶ�
     * @param cjjgrmb double �ɽ��۸������
     * @param puzfpjjg double ��ͨס��ƽ���۸������ָ�ϵ�9432ƽ���۸��Ժ����� 20081224
     * @return double ��ͨס��������
     * @throws Exception �쳣
     */
    private static double getJjsyfJm(Sbzb sbzb, Tufwxx tfxx, double jzse,
                                     double cqze, double cjjgrmb,
                                     double puzfpjjg) throws Exception {
        double qszymj = 0;
        if (tfxx.fwjzmj != null) {
            qszymj = tfxx.fwjzmj.doubleValue();
        } else {
            throw new ApplicationException("���㷿�ݵĸ���˰���ʱ��Ȩ��ת���������Ϊ�㣡");
        }
        //Debug.out("ȷ���ķ��ݵ�Ȩ��ת����� " + qszymj + "m2");

        //ע����1����ͨסլ��˰���=������˰���Ǩ�������50%
        //��2����ͨסլ�ж��������ɽ��۸�·��ݽ���������������Ʒ��סլƽ���ۼ�
        //ֻ�и����û�������ͨסլ�ļ�������
        double jjsyfjmje = 0;
        double jbzs = 0.5;
        //
        if (Constants.ZB_YHBS_GR.equals(sbzb.yhbs)
            && Constants.TDFWLB.equals(tfxx.fwlxdm)) { //�����Ǹ�����Ϊ����סլ
            if (Constants.SETZ_JJSYF.equals(sbzb.setz)) { //�������÷�
                //��ͬ����Լ��ǩ��ʱ����2007��8��1��֮ǰ�ģ�ȫ�����գ�û�м����
                //û�����ܹ���������ס������ķ��ݿɼ���������ͨ��׼סլ�������յ��Ż����ߡ�
                if (!tfxx.getHtqdsj().before(DateUtils.parseTimestamp(
                        "20070801"))) {
                    //
                    jjsyfjmje = (jzse - cqze) * jbzs;
                    //
                    jjsyfjmje = DataConvert.round(jjsyfjmje, 2);
                    if (jjsyfjmje < 0) {
                        jjsyfjmje = 0;
                    }
                }
            }
        }
        Debug.out("�������÷���˰��� " + jjsyfjmje + "Ԫ");
        return jjsyfjmje;
    }
    /**
     * ������ͨסլ������
     * @param sbzb Sbzb �걨�������
     * @param tfxx Tufwxx ���ط�����Ϣ
     * @param jzse double ����˰��
     * @param cqze double ��Ǩ�ܶ�
     * @param cjjgrmb double �ɽ��۸������
     * @param puzfpjjg double ��ͨס��ƽ���۸������ָ�ϵ�9432ƽ���۸��Ժ����� 20081224
     * @return double ��ͨס��������
     * @throws Exception �쳣
     * �޸�˵��:[20081224 Ha Zhengze] �����˾������÷���20070801ǰ���׵�������������ͨ��׼סլ��������ͨ��׼סլ����
     * ,����ԭ����ԭ������һ��.
     *
     */
    private static double getPtzzJm(Sbzb sbzb, Tufwxx tfxx, double jzse,
                                    double cqze, double cjjgrmb,
                                    double puzfpjjg) throws Exception {
        double qszymj = 0;
        if (tfxx.fwjzmj != null) {
            qszymj = tfxx.fwjzmj.doubleValue();
        } else {
            throw new ApplicationException("���㷿�ݵĸ���˰���ʱ��Ȩ��ת���������Ϊ�㣡");
        }
        //Debug.out("ȷ���ķ��ݵ�Ȩ��ת����� " + qszymj + "m2");

        //ע����1����ͨסլ��˰���=������˰���Ǩ�������50%
        //��2����ͨסլ�ж��������ɽ��۸�·��ݽ���������������Ʒ��סլƽ���ۼ�
        //ֻ�и����û�������ͨסլ�ļ�������
        double puzzjmje = 0;
        double jbzs = 0.5;

        //����˰�걨ʱ�����ط��ݻ�����Ϣ�У����ڶ��ַ����걨��Ϣ�����ۺ�ͬǩ�������Ƕ��٣�
        //ͳһ����˰�������ѡ���������м��㡣�����·����걨��Ϣ����ͬǩ��������05��6��1��֮ǰ������ԭ�����׼��
        //6��1��֮��ĸ���˰�������ѡ���������м��㡣
        if (Constants.TUFWXX_SFESF_TRUE.equals(tfxx.sfesf)) {
            if (Constants.SETZ_JBZS.equals(sbzb.setz)) { //��������
                puzzjmje = (jzse - cqze) * jbzs;
            } else if (Constants.SETZ_JJSYF.equals(sbzb.setz)) { //�������÷�,add by Hazhengze 20081224
                if (Constants.ZB_YHBS_GR.equals(sbzb.yhbs)
                    && Constants.TDFWLB.equals(tfxx.fwlxdm)) {//�����Ǹ�����Ϊ����סլ
                    //��ͬ����Լ��ǩ��ʱ����2007��8��1��֮ǰ�ģ�ȫ�����գ�û�м����
                    //û�����ܹ���������ס������ķ��ݿɼ���������ͨ��׼סլ�������յ��Ż����ߡ�
                    if (tfxx.getHtqdsj().before(DateUtils.parseTimestamp(
                            "20070801"))) {
                        if (CommonUtil.isPtbzzz(sbzb, tfxx, cjjgrmb)) {
                            puzzjmje = (jzse - cqze) * jbzs;
                        }
                    }
                }
            }
            //�Ǹ�����
            puzzjmje = DataConvert.round(puzzjmje, 2);
            if (puzzjmje < 0) {
                puzzjmje = 0;
            }
        }
        //����걨�ķ������Ƕ��ַ�
        else {
            if (Constants.ZB_YHBS_GR.equals(sbzb.yhbs) &&
                Constants.TDFWLB.equals(tfxx.fwlxdm)) { //�����Ǹ�����Ϊ����סլ
                if (Constants.TDFWQSZY_MM.equals(tfxx.tdfwqszylx) ||
                    Constants.TDFWQSZY_JH.equals(tfxx.tdfwqszylx)) {
                    //added by zhaobo ��Ϊ˰�������ԭ���޸ı��γ��� start 20050706
                    if (Constants.SETZ_JBZS.equals(sbzb.setz) ||
                        (Constants.SETZ_ZC.equals(sbzb.setz) &&
                         CommonUtil.isPtbzzz(sbzb, tfxx, cjjgrmb))) //modify by hazhengze 20081224,������ͨ��׼סլ�ж���׼
                    //|| (Constants.SETZ_ZC.equals(sbzb.setz) && (cjjgrmb / qszymj) <= puzfpjjg))
                    {
                        puzzjmje = (jzse - cqze) * jbzs;
                    }
                    //added by fujx,hazhengze 20081224,˰��������Ӿ������÷�
                    if (Constants.SETZ_JJSYF.equals(sbzb.setz))
                    {
                        //��ͬ����Լ��ǩ��ʱ����2007��8��1��֮ǰ�ģ�ȫ�����գ�û�м����
                        //û�����ܹ���������ס������ķ��ݿɼ���������ͨ��׼סլ�������յ��Ż����ߡ�
                        if (tfxx.getHtqdsj().before(DateUtils.parseTimestamp(
                                "20070801"))) {
                            if (CommonUtil.isPtbzzz(sbzb, tfxx, cjjgrmb)) {
                                puzzjmje = (jzse - cqze) * jbzs;
                            }
                        }
                    }
                    //
                    puzzjmje = DataConvert.round(puzzjmje, 2);
                    if (puzzjmje < 0) {
                        puzzjmje = 0;
                    }
                }
            }
        }
        Debug.out("��ͨסլ��˰��� " + puzzjmje + "Ԫ");
        return puzzjmje;
    }


    /**
     * ������˼��տ�ʱ����Ҫ�õ��ļ���˰��
     * Constants �еĶ��壺
      public static final String JE_CJJG = "JE_CJJG";   //�ɽ��۸�
      public static final String JE_JSYJ = "JE_JSYJ";   //��˰����
      public static final String JE_JZQS = "JE_JZQS";   //������˰
      public static final String JE_SJYZ = "JE_SJYZ";   //ʵ��Ӧ��
      public static final String JE_JZSE = "JE_JZSE";   //����˰��
      public static final String JE_QYZFBCDKE = "JE_QYZFBCDKE";//�����ѹ�����ס���ı��εֿ۶�
      public static final String JE_FWJHJG = "JE_FWJHJG";  //���ݽ����ĶԷ��ĳɽ��۸�
      public static final String JE_CQJMJE = "JE_CQJMJE";  //��Ǩ������
      public static final String JE_PTZZJMJE = "JE_PTZZJMJE";//��ͨסլ��˰���
      public static final String JE_JMSZE = "JE_JMSZE";//����˰�ܽ��
      public static final String JE_YNSE = "JE_YNSE";    //Ӧ��˰��
     * @param String �걨���
     * @return HashMap
     */
    public static JghdsjBo getJZSE(String sbbh, Connection conn) throws
            ApplicationException {
        JghdsjBo hdbo = new JghdsjBo();
        try {
            //�����걨��ţ�����걨��������
            Sbzb sbzb = new Sbzb();
            sbzb.setSbbh(sbbh);
            sbzb = (Sbzb) DAOFactory.getInstance().getSbzbDAO().get(sbzb, conn);

            //��÷��ݽ����Է��걨���
            Tufwxx dfTfxx = new Tufwxx();
            if (sbzb.dfsbbh != null && sbzb.dfsbbh != "") {
                dfTfxx = (Tufwxx) DAOFactory.getInstance().getTufwxxDAO().
                         getBySbbh(sbzb.dfsbbh, conn);
            }

            //�����걨��Ż�ã����ط��ݵĻ�����Ϣ����
            //�Ӷ��õ��ɽ��۸񡢷������������õ��Ƿ������ͨסլ�ļ������յ�����
            Tufwxx tfxx = (Tufwxx) DAOFactory.getInstance().getTufwxxDAO().
                          getBySbbh(sbbh, conn);

            Map jmMap = new HashMap();
            if (!Constants.ZB_BLJMSBS_BZ.equals(sbzb.getBljmsbs())) {
                jmMap = getJmsj(sbbh, conn);
            }
            //�����ͨסլ�������������
            double puzfpjjg = StringUtil.getDoubleValue(getZbz(Constants.
                    ZCWH_PTZZJMBZ, conn)); //�������Ʒ��סլƽ���ۼ�

            double sl = getSl(sbzb.getSl(), getZbz(Constants.ZCWH_SL, conn));
            hdbo = getJZSE(sbzb, tfxx, dfTfxx, sl, puzfpjjg, jmMap);

        } catch (Exception ex) {
            ex.printStackTrace();
            Debug.out(ex);
            throw new ApplicationException("��ȡ��˰����ʱ�򣬱���");
        }
        return hdbo;
    }

    /**
     * ����Ȩ��ת�����Ͳ�ѯ˰��Ȩ��������õ�˰��˰Ŀ
     * @param qszydm Ȩ��ת�ƴ���
     * @param conn  ���ݿ�����
     * @return Szsm
     */
    public static Szsm getSZSMDM(String qszydm, Connection con) throws
            BaseException {
        String szsmdm = "";
        PreparedStatement pst = null;
        ResultSet rs = null;
        Szsm szsm = new Szsm();
        try {
            String sql =
                    "select szsmdm from DMDB.SF_DM_QSZYXS where QSZYXSDM = '" +
                    qszydm + "'";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();

            if (rs.next()) {
                szsmdm = rs.getString("szsmdm");
            }

            szsm.setSzsmdm(szsmdm);
            szsm = (Szsm) DAOFactory.getInstance().getSzsmDAO().get(szsm, con);
        } catch (Exception ex) {
            throw ExceptionUtil.getBaseException(ex);
        } finally {
            try {
                pst.close();
            } catch (Exception ex) {

            }
            try {
                rs.close();
            } catch (Exception ex) {

            }
        }
        return szsm;
    }

    /**
     * ����Ȩ��ת�����Ͳ�ѯ˰��Ȩ��������õ�˰��˰Ŀ
     * @param qszydm Ȩ��ת�ƴ���
     * @param conn  ���ݿ�����
     * @return Szsm
     */
    public static Szsm getSZSM(String szsmdm, Connection con) throws
            BaseException {
        Szsm szsm = new Szsm();
        try {
            szsm.setSzsmdm(szsmdm);
            szsm = (Szsm) DAOFactory.getInstance().getSzsmDAO().get(szsm, con);
        } catch (Exception ex) {
            throw ExceptionUtil.getBaseException(ex);
        }
        return szsm;
    }

    /**
     * �������ߴ��������ߵ�ֵ
     * @param zcdm String
     * @param sbrq String �걨����
     * @param conn Connection ���ݿ�����
     * @return double
     */
    public static String getZcsj(String zcdm, Connection conn) throws
            ApplicationException {
        String value = "";
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "select zbz from QSDB.QS_JL_ZCWH where ZBDM = '" + zcdm +
                     "'";

        //�����ߵ���Чʱ����Ϊ��������
//      if(sbrq != null && sbrq != "")
//      {
//          sql = sql + " and SXQSRQ <= to_date('','') and SXJZRQ>=to_date('','')";
//       }
//
        try {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            if (rs.next()) {
                value = rs.getString("zbz");
            }
        } catch (Exception ex) {
            throw new ApplicationException("ȡ�����ߵ�ֵʱ�����");
        } finally {
            try {
                pst.close();
            } catch (Exception ex) {

            }
            try {
                rs.close();
            } catch (Exception ex) {

            }
        }
        return value;
    }

    /**
     * ȷ���ɽ��۸�
     * @return double
     */
    public static double getCjjg(Tufwxx tfxx) {
        //�ɽ��۸��ȷ��
        //������ɽ��۸���������۸��������۸�Ϊ�ɽ��۸�
        //�������������Ҽ۸�������Ҽ۸���ɽ��۸�=�ɽ��۸������۸�+����ۺϵ�����Ҽ۸�
        double cjjgrmb = 0;
        double pgjgrmb = 0;
        double cjjgwb = 0;
        double zhjgrmb = 0;

        if (tfxx.cjjgrmb != null) {
            cjjgrmb = tfxx.cjjgrmb.doubleValue();
        }
        if (tfxx.pgjgrmb != null) {
            pgjgrmb = tfxx.pgjgrmb.doubleValue();
        }
        if (tfxx.cjjgwb != null) {
            cjjgwb = tfxx.cjjgwb.doubleValue();
        }
        if (tfxx.zhjgrmb != null) {
            zhjgrmb = tfxx.zhjgrmb.doubleValue();
        }
        //����ҵ���ж�ȷ���ɽ��۸�
        //����ɽ��۸�������۸��,ͬʱ��û���ۺϵ�����Ҽ۸�
        if (pgjgrmb > cjjgrmb && zhjgrmb == 0) {
            cjjgrmb = pgjgrmb;
        }

        //�����������Ҽ۸�,�����ۺ�����ҵļ۸�
        if (zhjgrmb > 0) {
            if ((cjjgrmb + zhjgrmb) > pgjgrmb) {
                cjjgrmb = cjjgrmb + zhjgrmb;
            } else { //����������۸�С
                cjjgrmb = pgjgrmb;
            }
        }
        return cjjgrmb;
    }

    /**
     * ��ȡ�����������Ϣ
     * Map :
     * zjlxdm   ֤�����ʹ���
     * zjhm   ֤������
     * gjdm   ��������
     * qsjmlbdm  ��˰����������
     * qtjmly
     * qszyxsdm  ��˰Ȩ��ת�����ʴ���
     * qsjmlxdm ��˰�������ʹ���
     * jmse  ����˰���
     * cstdfwzl
     *
     * @param spjgbh String
     * @return HashMap
     */
    public static HashMap getZcspjg(String spjgbh) {
        //Debug.out("into commonutil getZcspjg...");
        com.ttsoft.bjtax.sfgl.proxy.ServiceProxy sfS = new com.ttsoft.bjtax.
                sfgl.proxy.ServiceProxy();
        //Debug.out("aaa service sfs: " + sfS);
        HashMap map = null;
        try {
            Map map_tmp = sfS.getQsjmspjg(spjgbh);
            if (map_tmp != null && map_tmp.size() > 0) {
                map = new HashMap(map_tmp);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            //throw new ApplicationException("��ȡ˰�����й���˰���������Ϣ��ʱ�����");
        }
        return map;
    }

    /**
     * �����м���˰������걨���������
     * @param jsjdm String
     * @param szsmdm String
     * @param jsje BigDecimal
     * @param jmse BigDecimal
     * @param lrr String
     * @param jmxmdm String
     * @param cjrq Timestamp
     * @param skssjsrq String
     * @param skssksrq String
     * @return boolean
     * @throws BaseException
     */
    public static boolean insertSBJM(HashMap map) throws BaseException {
        com.ttsoft.bjtax.sfgl.proxy.ServiceProxy sfS = new com.ttsoft.bjtax.
                sfgl.proxy.ServiceProxy();
        try {
            String jsjdm = (String) map.get("jsjdm");
            String szsmdm = (String) map.get("szsmdm");
            BigDecimal jsje = (BigDecimal) map.get("jsje");
            BigDecimal jmse = (BigDecimal) map.get("jmse");
            String lrr = (String) map.get("lrr");
            String jmxmdm = (String) map.get("jmxmdm");
            String jmxzdm = (String) map.get("jmxzdm");
            String sjly = Constants.JKS_SJLY_FHZ;
           
            Timestamp cjrq = (Timestamp) map.get("cjrq");
            String skssjsrq = DataConvert.TimeStamp2String((Timestamp) map.get(
                    "skssjsrq"));
            String skssksrq = DataConvert.TimeStamp2String((Timestamp) map.get(
                    "skssksrq"));
            
            if(jmxzdm==null) jmxzdm = "";
            
            //�����ӿڴ�����������Ӽ������ʴ���
            return sfS.insertSBJM(jsjdm, szsmdm, jsje, jmse, lrr, jmxmdm, cjrq,
                                  skssjsrq, skssksrq,jmxzdm,sjly);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return true;
    }

    /**
     * ������˰�����ݱ�������ʱ����Ҫͬʱ����˰�ѹ������йش˼�����걨����
     * �Ա�֤����˰���ʵ���ȷ
     *
     * @param jsjdm String
     * @param szsmdm String
     * @param cjrq Timestamp
     * @return boolean
     * @throws BaseException
     */
    public static boolean deleteSBJM(HashMap map) throws BaseException {
        String jsjdm = (String) map.get("jsjdm");
        String szsmdm = (String) map.get("szsmdm");
        Timestamp cjrq = (Timestamp) map.get("cjrq");
        String sjly = Constants.JKS_SJLY_FHZ;
        try {
            com.ttsoft.bjtax.sfgl.proxy.ServiceProxy sfS = new com.ttsoft.bjtax.
                    sfgl.proxy.ServiceProxy();
            return sfS.deleteSBJM(jsjdm, szsmdm, cjrq,sjly);
        } catch (Exception ex) {
            throw new ApplicationException("ɾ��˰���е��걨���ݵ�ʱ�����");
        }
    }

    /**
     * ͨ��UserData�е�xtsbm1���Ʊ֤�ʻ�����������
     * @param ud UserData
     * @param conn Connection
     * @return Zh
     */
    public static Zh getPzzhVo(UserData ud, Connection conn) throws
            ApplicationException {
        Zh zh = new Zh();
        zh.setZhdm(ud.getXtsbm1());
        try {
            zh = (Zh) DAOFactory.getInstance().getZhDAO().get(zh, conn);

        } catch (Exception ex) {
            throw new ApplicationException("ȡ��Ʊ֤�ʻ���ʱ�����");
        }
        return zh;
    }

    /**
     * �ʻ������ͱ�ʶ
     * 1��˰�����
     * 2��������
     * 3�����յ�
     * 4��˰�����������Ա
     * 5�����յ�������Ա
     * ͨ��UserData�е�xtsbm1������յ��Ʊ֤�ʻ�����������
     * @param ud UserData
     * @param conn Connection
     * @return Zh
     */
    public static Zh getZsdPzzhVo(UserData ud, Connection conn) throws
            ApplicationException {
        Zh zh = new Zh();
        String zhdm = ud.getXtsbm1();

        //���ҵ����յ��������Ա��ȫ������
        //ͨ���˻������ͱ�ʶ�ж��Ƿ�Ϊ���յ��������Ա������ǣ��ͷ������յ���Ϣ
        //������Ǿͷ���null;
        zh.setZhdm(zhdm);
        try {
            zh = (Zh) DAOFactory.getInstance().getZhDAO().get(zh, conn);
            Zh zsdZh = new Zh();
            if (zh.lxbs != null && zh.lxbs.equals("5")) {
                //���ϼ����ʻ����븳�����յ��vo
                zsdZh.setZhdm(zh.getSjzhdm());
                zsdZh = (Zh) DAOFactory.getInstance().getZhDAO().get(zsdZh,
                        conn);
            }

        } catch (Exception ex) {
            throw new ApplicationException("ȡ��Ʊ֤�ʻ���ʱ�����");
        }
        return zh;
    }

    /**
     * ȡ��Timestamp �͵���˰�޽�����
     * @param htqdrq Timestamp
     * @param ts int �޽�����
     * @return Timestamp
     */
    public static Timestamp getXjrq(Timestamp htqdrq, int ts) {
        Calendar ca = Calendar.getInstance();

        ca.setTime(htqdrq);
        ca.add(Calendar.DATE, ts);
        Debug.out("�޽�����Ϊ��" + ca.toString());
        Date d = ca.getTime();
        return new Timestamp(d.getTime());
    }

    public static void main(String[] arg) {
        HashMap map = CommonUtil.getZcspjg("����˰���������[2004]040001");
        Set keys = map.keySet();
        Iterator it = keys.iterator();
        while (it.hasNext()) {
            String key = (String) it.next();
            System.out.println("key: " + key);
            Object value = (Object) map.get(key);
            if (value != null) {
                System.out.println("value type: " + value.getClass().getName());
                System.out.println("value: " + value.toString());
            } else {
                System.out.println("value is null.");
            }
        }
    }


    /**
     * �걨��ţ����ˡ��Ǹ��ˣ���ͬʱ���������ı�ţ�
     * "1λ��ĸ" + ZHDM(8λ,����01�оֱ�ʾ) + ʱ��(040101) + ��ˮ��(4λ)
     * �걨��ŵ�1λ��ĸΪ"S"
     * 1234567890123456789
     * @param zhdm String
     * @param conn Connection
     * @param lx String
     * @return String
     * @throws ApplicationException
     */
    public static String getPlh(String zhdm, Connection conn, String lx) throws
            ApplicationException {
        if (zhdm == null) {
            throw new ApplicationException("����Ĳ������ʻ����롱����Ϊ�գ�");
        }

        if (zhdm.length() != 10) {
            throw new ApplicationException("����Ĳ������ʻ����롱λ���쳣");
        }

        //ZHDM(8λ,����01�оֱ�ʾ)
        String Plh = lx + zhdm.substring(2);
        Timestamp now = null;
        Date newDate = null;
        try {
            now = getDBtime(conn);
            //newDate = new Date(now.getTime());

        } catch (Exception ex) {
            throw new ApplicationException("��ȡ���ݿ�ʱ��ʧ��");
        }

        Plh = Plh + getDatetime(now, "yyMMddhhmmss");
        return Plh;
    }


    /**
     * ����걨�������ݵĽ���� SB_JL_JM
     * @param conn
     * @param map
     * @param jsjdm String
     * @param szsmdm String
     * @param cjrq Timestamp
     * @return List
     * @throws ApplicationException
     */
    public static ArrayList getJM(Connection conn, HashMap map) throws
            ApplicationException {
        String jsjdm = (String) map.get("jsjdm");
        String szsmdm = (String) map.get("szsmdm");
        Timestamp cjrq = (Timestamp) map.get("cjrq");
        String jmlx = "1"; //д�� �����Լ���

        PreparedStatement pre_stmt = null;
        String sql =
                " SELECT JSJDM,JMLX,SZSMDM,SBRQ,LRRQ,JSJE,KSSL,JMSE,SWJGZZJGDM,LRR,FSDM,JZBZ, " +
                " JMXMDM,DJZCLXDM,GJBZHYDM,YSKMDM,YSJCDM,SKSSJSRQ,SKSSKSRQ,ND,CJRQ,QXDM " +
                " from SBDB.SB_JL_JM where jsjdm =? and JMLX=? and SZSMDM=? and CJRQ=? ";
        ResultSet rs = null;

        ArrayList l = new ArrayList();

        try {
            pre_stmt = conn.prepareStatement(sql);
            pre_stmt.setString(1, jsjdm);
            pre_stmt.setString(2, jmlx);
            pre_stmt.setString(3, szsmdm);
            pre_stmt.setTimestamp(4, cjrq);
            rs = pre_stmt.executeQuery();
            while (rs.next()) {
                Jm jm = new Jm();
                jm.setJsjdm(rs.getString("JSJDM"));
                jm.setJmlx(rs.getString("JMLX"));
                jm.setSzsmdm(rs.getString("SZSMDM"));
                jm.setSbrq(rs.getTimestamp("SBRQ"));
                jm.setLrrq(rs.getTimestamp("LRRQ"));
                jm.setJsje(rs.getBigDecimal("JSJE"));
                jm.setKssl(rs.getBigDecimal("KSSL"));
                jm.setJmse(rs.getBigDecimal("JMSE"));
                jm.setSwjgzzjgdm(rs.getString("SWJGZZJGDM"));
                jm.setLrr(rs.getString("LRR"));
                jm.setFsdm(rs.getString("FSDM"));
                jm.setJzbz(rs.getString("JZBZ"));
                jm.setJmxmdm(rs.getString("JMXMDM"));
                jm.setDjzclxdm(rs.getString("DJZCLXDM"));
                jm.setGjbzhydm(rs.getString("GJBZHYDM"));
                jm.setYskmdm(rs.getString("YSKMDM"));
                jm.setYsjcdm(rs.getString("YSJCDM"));
                jm.setSkssjsrq(rs.getTimestamp("SKSSJSRQ"));
                jm.setSkssksrq(rs.getTimestamp("SKSSKSRQ"));
                jm.setNd(rs.getString("ND"));
                jm.setCjrq(rs.getTimestamp("CJRQ"));
                jm.setQxdm(rs.getString("QXDM"));

                l.add(jm);

            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ApplicationException("CommonUtil.getJM() ���������ݿ����", e);
        }
        return l;
    }

}
