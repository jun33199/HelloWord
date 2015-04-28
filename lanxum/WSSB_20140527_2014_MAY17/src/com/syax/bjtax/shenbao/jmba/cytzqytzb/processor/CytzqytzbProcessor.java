package com.syax.bjtax.shenbao.jmba.cytzqytzb.processor;

import com.ttsoft.bjtax.shenbao.util.DBResource;

import com.syax.bjtax.shenbao.jmba.jmbaz.JmbaActionConstant;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.exception.SystemException;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;
import java.util.Map;
import java.util.HashMap;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import com.syax.bjtax.shenbao.jmba.xmlvo.Jmba13BVO;
import com.syax.bjtax.shenbao.jmba.dao.PublicAccess;
import com.syax.bjtax.shenbao.jmba.xmlvo.JmbaZbVO;
import com.syax.bjtax.shenbao.jmba.xmlvo.JmbaVO;
import com.syax.common.util.CAcodeConstants;
import com.syax.bjtax.ca.util.DzyjHelper;
import com.syax.bjtax.ca.vo.DzyjsjVO;
import java.util.Date;
import java.sql.CallableStatement;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import com.syax.bjtax.shenbao.jmba.xmlvo.Jmba13BViewVO;
import com.syax.bjtax.shenbao.jmba.xmlvo.Jmba13BViewWnVO;
import com.syax.bjtax.shenbao.jmba.xmlvo.JmbaWnndVO;
import com.syax.bjtax.shenbao.util.QysdsUtil;
import java.sql.PreparedStatement;
import com.syax.bjtax.shenbao.model.dm.DmVo;
import com.ttsoft.bjtax.shenbao.util.DateUtilPro;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;

public class CytzqytzbProcessor implements Processor {

    /**
     * 总控数据
     */
    private UserData userData;
    /**
     * 处理请求
     */
    public Object process(VOPackage vo) throws BaseException {
        this.userData = vo.getUserData();

        // 根据业务操作类型值来做业务操作
        try {
            switch(vo.getAction()) {
                // 查询
                case JmbaActionConstant.INTI_ACTION_QUERY:
                    vo.setData(this.doQuery( (Map)vo.getData()));
                    return vo;
                case JmbaActionConstant.INTI_ACTION_SHOW:
                    vo.setData(this.doShow(vo));
                    return vo;
                case JmbaActionConstant.INTI_ACTION_COMMIT:
                    vo.setData(this.doCommit(vo, "2")); //提交为2
                    return vo;
                case JmbaActionConstant.INTI_ACTION_QUERYDETAIL:
                    vo.setData(this.doQueryDeTali(vo)); //提交为2
                    return vo;
                case JmbaActionConstant.INTI_ACTION_SAVE:
                    vo.setData(this.doSave( (Map)vo.getData()));
                    return vo;
                case JmbaActionConstant.INTI_ACTION_UPDATE:
                    vo.setData(this.doUpdate( (Map)vo.getData()));
                    return vo;

                case JmbaActionConstant.INTI_ACTION_DELETE:
                    vo.setData(this.doDelete( (Map)vo.getData()));
                    return vo;
                case JmbaActionConstant.INTI_ACTION_QUERYZB:
                    vo.setData(this.doCheck(vo));
                    return vo;

                default:
                    throw new SystemException("no such mothod");
            }
        }
        catch(Exception e) {
            throw ExceptionUtil.getBaseException(e);
        }
    }

    /**
     */
    private Map doQuery(Map data) throws BaseException {
        Map map = null;

        return map;
    }

    /**
     * doCommit保存对象页面信息要素
     *
     * @param vo
     *            业务参数
     * @return 数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
     * @throws BaseException
     *             当其他情况都会抛出异常信息
     */

    private Object doCommit(VOPackage vo, String sqzt) throws BaseException {
//sqzt 保存是1，提交是2
        DzyjHelper dh = new DzyjHelper();
        Map hm = (Map)vo.getData();
        JmbaVO bavo = (JmbaVO)hm.get(CAcodeConstants.CA_MAPKEY_VO_XMLVO);
        DzyjsjVO dzyj = (DzyjsjVO)hm.get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);
        UserData ud = (UserData)vo.getUserData();
        JmbaZbVO vo1 = (JmbaZbVO)bavo.getJmsbajl().get(0);
        com.syax.bjtax.shenbao.util.QysdsUtil qysdsUtil = new com.syax.bjtax.shenbao.util.QysdsUtil();
        try {
//			电子原件暂不实现
            /*
                         try
                         {
             dzyj = CAUtils.saveDzyjsj(ud,dzyj, "0", "0", "0", vo1.getBasqwsh()+VOConstants.QYSDSJMBA_SXDM01);
                        }catch (Exception ex)
                         {
                ex.printStackTrace();
                throw ExceptionUtil.getBaseException(ex);
                         }
                        hm.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
             */

            //更新主表状态为审核通过或审核未通过
            qysdsUtil.updateSqzt(vo1.getBasqwsh(), sqzt, ud.getYhid());

        }
        catch(Exception ex) {
            throw ExceptionUtil.getBaseException(ex);
        }
        finally {

        }
        return null;
    }

    /**
     */
    private Map doShow(VOPackage vo) throws BaseException {
        Map map = new HashMap();
        String BASQWSH = (String) ( (Map)vo.getData()).get("BASQWSH");
        String BAND = (String) ( (Map)vo.getData()).get("BAND");

        UserData ud = vo.getUserData();

        Connection conn = null;
        ResultSet rs = null;
        CallableStatement cs = null;
        try {

            conn = DBResource.getConnection();

            JmbaZbVO zbvo = PublicAccess.getJmbaZbVO(conn, BASQWSH);

            String querysql = "{call SFDB.PRO_JMBA_QYSDSJMSBA_13B_1(?,?,?,?)}";

            cs = conn.prepareCall(querysql); // 调用存储过程

            cs.setString(1, ud.getYhid());
            cs.setString(2, BAND);
            cs.registerOutParameter(3, oracle.jdbc.OracleTypes.VARCHAR);
            cs.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);

            cs.execute();
            System.out.println("cs.getString(3) = " + cs.getString(3));
            if(cs.getString(3).equals("1")) {
                rs = (ResultSet)cs.getObject(4);
                Jmba13BViewWnVO view13WnVo = null;

                while(rs.next()) {

                    view13WnVo = new Jmba13BViewWnVO();
                    view13WnVo.setBand(rs.getString("Band"));
                    view13WnVo.setBtzqyjsjdm(rs.getString("Btzqyjsjdm"));
                    view13WnVo.setBtzqymc(rs.getString("Btzqymc"));
                    view13WnVo.setBasqwsh(rs.getString("basqwsh"));
                    view13WnVo.setBtzqyswdjzh(rs.getString("Btzqyswdjzh"));
                    view13WnVo.setGxjslydm(rs.getString("Gxjslydm"));
                    view13WnVo.setGxjslymc(rs.getString("Gxjslymc"));
                    view13WnVo.setLast_four_dke(rs.getString("Last_four_dke"));
                    view13WnVo.setLast_four_tze(rs.getString("Last_four_tze"));

                    view13WnVo.setLast_three_dke(rs.getString("Last_three_dke"));
                    view13WnVo.setLast_three_tze(rs.getString("Last_three_tze"));

                    view13WnVo.setLast_two_dke(rs.getString("Last_two_dke"));
                    view13WnVo.setLast_two_tze(rs.getString("Last_two_tze"));

                    view13WnVo.setLast_one_dke(rs.getString("Last_one_dke"));
                    view13WnVo.setLast_one_tze(rs.getString("Last_one_tze"));
                    view13WnVo.setXh(rs.getString("xh"));

                    zbvo.getWnqysdsjmba().add(view13WnVo);
                }
            }

            JmbaWnndVO wnndvo = new JmbaWnndVO("wnband");
            zbvo.getJmbadtnd().add(wnndvo);

            querysql = "{call SFDB.PRO_JMBA_QYSDSJMSBA_13B_2(?,?,?,?,?)}";

            cs = conn.prepareCall(querysql); // 调用存储过程

            cs.setString(1, ud.getYhid());
            cs.setString(2, BAND);
            cs.setString(3, BASQWSH);
            cs.registerOutParameter(4, oracle.jdbc.OracleTypes.VARCHAR);
            cs.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR); //in out

            cs.execute();

            System.out.println("cs.getString(4) = " + cs.getString(4));
            if(cs.getString(4).equals("1")) {
                rs = (ResultSet)cs.getObject(5);

                Jmba13BViewVO view13Vo = null;
                while(rs.next()) {

                    view13Vo = new Jmba13BViewVO();
                    view13Vo.setBand(rs.getString("Band"));
                    view13Vo.setBtzqyjsjdm(rs.getString("Btzqyjsjdm"));
                    view13Vo.setBtzqymc(rs.getString("Btzqymc"));
                    view13Vo.setBtzqyswdjzh(rs.getString("Btzqyswdjzh"));
                    view13Vo.setGxjslydm(rs.getString("Gxjslydm"));
                    view13Vo.setGxjslymc(rs.getString("Gxjslymc"));
                    System.out.println("~~~~~~~~~~~~~" + view13Vo.getGxjslydm());
                    System.out.println("~~~~~~~~~~~~~" + view13Vo.getGxjslymc());
                    System.out.println("~~~~~~~~~~~~~" + view13Vo.getBtzqyswdjzh());
                    view13Vo.setLast_four_dke(rs.getString("Last_four_dke"));
                    view13Vo.setLast_four_tze(rs.getString("Last_four_tze"));

                    view13Vo.setLast_three_dke(rs.getString("Last_three_dke"));
                    view13Vo.setLast_three_tze(rs.getString("Last_three_tze"));

                    view13Vo.setLast_two_dke(rs.getString("Last_two_dke"));
                    view13Vo.setLast_two_tze(rs.getString("Last_two_tze"));

                    view13Vo.setLast_one_dke(rs.getString("Last_one_dke"));
                    view13Vo.setLast_one_tze(rs.getString("Last_one_tze"));
                    view13Vo.setXh(rs.getString("xh"));
                    zbvo.getQysdsjmba().add(view13Vo);
                }

            }
            JmbaWnndVO dnndvo = new JmbaWnndVO("dnband");
            zbvo.getJmbadtnd().add(dnndvo);

            System.out.println("zbvo to xml " + zbvo.toXML());
            map.put("JmbaZbVO", zbvo);
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        finally {
            try {
                if(rs != null) {
                    rs.close();
                }
            }
            catch(Exception exx) {
            }
            try {
                if(cs != null) {
                    cs.close();
                }
            }
            catch(Exception exx) {
            }
            try {
                if(conn != null) {
                    conn.close();
                }
            }
            catch(Exception exx) {
            }
        }

        return map;

    }

    /**
     */
    private Map doQueryDeTali(VOPackage vo) throws BaseException {

        Map map = new HashMap();
        String BASQWSH = (String) ( (Map) (vo.getData())).get("BASQWSH");
        String type = (String) ( (Map) (vo.getData())).get("type");
        String gxjsly = (String) ( (Map) (vo.getData())).get("lxdm");
        String btzqyswdjzh = (String) ( (Map) (vo.getData())).get("swdjzh");

        String btzjsj = (String) ( (Map) (vo.getData())).get("btzjsj");
        String zcbabs = (String) ( (Map) (vo.getData())).get("zcbabs");

        String zcbashbj = (String) ( (Map) (vo.getData())).get("zcbashbj");
        String wnwsh = (String) ( (Map) (vo.getData())).get("wnwsh");
        int band = (Integer.parseInt(DateUtilPro.getCurYearStr4()) - 1);

        Jmba13BVO vo13 = null;
        Connection con = null;
        ResultSet rs = null;
        Statement st = null;

        try {
            String wnsql = "select b.tznd,a.tze,a.dnkdke,a.dke,a.jze,nvl(a.ywcbabs,1) ywcbabs " +
                " from sfdb.sf_jl_qysdsjmsba_13b a ,( ";

            for(int i = 2006; i <= band; i++) {
                /*需根据备案年度动态构造*/
                wnsql += "select '" + i + "' tznd from dual  union ";
            }
            wnsql += " ) b  ";
            wnsql += " where a.tznd(+)=b.tznd ";
            wnsql += " and a.ywcbabs(+)='0' ";
            wnsql += " and a.btzqyswdjzh(+)='" + null +"' ";
            wnsql += " and a.jsjdm(+)='" + null +"' ";
            wnsql += " order by b.tznd ";

            System.out.println("sql = " + wnsql);
//            con = DBResource.getConnection();

            JmbaZbVO zbvo = PublicAccess.getJmbaZbVO(con, BASQWSH);
            st = con.createStatement();

            int i = 0;

            if(type != null && type.equals("EDITOR")) {
//                rs = st.executeQuery(sql);

                while(rs.next()) {
                    i++;
                    vo13 = new Jmba13BVO();
                    vo13.setTZND(rs.getString("TZND"));
                    vo13.setXH(rs.getString("XH"));
                    vo13.setROW(i + "");
                    vo13.setBASQWSH(rs.getString("BASQWSH"));
                    vo13.setJSJDM(rs.getString("JSJDM"));
                    vo13.setBAND(rs.getString("BAND"));
                    vo13.setSWJGZZJGDM(rs.getString("SWJGZZJGDM"));
                    vo13.setGXJSLYDM(rs.getString("GXJSLYDM"));
                    vo13.setBTZQYJSJDM(rs.getString("BTZQYJSJDM"));
                    vo13.setBTZQYSWDJZH(rs.getString("BTZQYSWDJZH"));
                    vo13.setBTZQYMC(rs.getString("BTZQYMC"));
                    vo13.setBTZQYSSD(rs.getString("BTZQYSSD"));
                    vo13.setTZND(rs.getString("TZND"));
                    vo13.setTZE(rs.getString("TZE"));
                    vo13.setDKE(rs.getString("DKE"));
                    vo13.setSHBJ(rs.getString("SHBJ"));
                    vo13.setZCBA(rs.getString("ZCBA"));
                    vo13.setCJR(rs.getString("CJR"));
                    vo13.setCJRQ(rs.getString("CJRQ"));
                    vo13.setLRR(rs.getString("LRR"));
                    vo13.setLRRQ(rs.getString("LRRQ"));
                    zbvo.getQysdsjmba().add(vo13);
                    System.out.println("vo to xml " + vo13.toXML());
                }

                int curTznd = curTznd = Integer.parseInt(vo13.getTZND());
                System.out.println("当前投资年度 = " + curTznd);
                int curBand = (Integer.parseInt(DateUtilPro.getCurYearStr4()) - 1);
                System.out.println("当前备案年度 = " + curBand);
                int ndce = curBand - curTznd;
                System.out.println("年度差额 = " + ndce);
                Jmba13BVO cend13 = null;
                for(int c = 1; c <= ndce; c++) {
                    curTznd += 1;
                    i++;
                    cend13 = new Jmba13BVO();
                    cend13.setXH("");
                    cend13.setROW(i + "");
                    cend13.setBASQWSH(vo13.getBASQWSH());
                    cend13.setJSJDM(vo13.getJSJDM());
                    cend13.setBAND(curBand + "");
                    cend13.setSWJGZZJGDM(vo13.getSWJGZZJGDM());

                    cend13.setGXJSLYDM(vo13.getGXJSLYDM());
                    cend13.setBTZQYJSJDM(vo13.getBTZQYJSJDM());
                    cend13.setBTZQYSWDJZH(vo13.getBTZQYSWDJZH());
                    cend13.setBTZQYMC(vo13.getBTZQYMC());
                    cend13.setBTZQYSSD(vo13.getBTZQYSSD());
                    cend13.setTZND(curTznd + "");
                    cend13.setTZE("0");
                    cend13.setDKE("0");
                    cend13.setSHBJ("1");
                    cend13.setZCBA("1");
                    cend13.setCJR(vo13.getCJR());
                    cend13.setCJRQ(vo13.getCJRQ());
                    cend13.setLRR(vo13.getLRR());
                    cend13.setLRRQ(vo13.getLRRQ());
                    zbvo.getQysdsjmba().add(cend13);
                    System.out.println(" cend13 vo to xml " + cend13.toXML());
                }
            }
            map.put("JmbaZbVO", zbvo);
//            else{
//                sql = "SELECT zysblxdm,LPAD('  ',2*(LEVEL - 1)) || zysblxmc zysblxmc, level "+
//                    "FROM dmdb.sf_dm_zysblx START WITH fjddm IS NULL CONNECT BY PRIOR zysblxdm = fjddm";
//
//                rs = st.executeQuery(sql);
//                while(rs.next()) {
//                    String dm = rs.getString("zysblxmc");
//                    String mc = rs.getString("gxjslymc");
//                    DmVo dmvo = new DmVo();
//                    dmvo.setDm(dm);
//                    dmvo.setMc(mc);
//                    list.add(dmvo);
//                }
//            }
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        finally {
            try {
                if(rs != null) {
                    rs.close();
                }
            }
            catch(Exception exx) {
            }
            try {
                if(st != null) {
                    st.close();
                }
            }
            catch(Exception exx) {
            }

             DBResource.destroyConnection(con);


        }

        return map;

    }

    /**
     */
    private Map doCheck(VOPackage vo) throws BaseException {

        Map map = new HashMap();

        String swdjzh = (String) ( (Map) (vo.getData())).get("swdjzh");
        String basqwsh = (String) ( (Map) (vo.getData())).get("BASQWSH");
        String jsjdm = (String) ( (Map) (vo.getData())).get("jsjdm");
        String lxdm = (String) ( (Map) (vo.getData())).get("lxdm");
        Connection con = null;
        ResultSet rs = null;
        Statement st = null;

        try {
            //select * from sfdb.sf_jl_qysdsjmsba_13b t where t.basqwsh = '' and t.gxjslydm = '' and t.btzqyswdjzh-- editor
            String sql = "select * from sfdb.sf_jl_qysdsjmsba_13b t where " +
                "t.basqwsh = '" + basqwsh + "' and t.gxjslydm = '" + lxdm + "' and t.btzqyswdjzh = '" + swdjzh + "'";
            sql += " order by t.band";
            System.out.println("sql = " + sql);
            con = DBResource.getConnection();

            JmbaZbVO zbvo = PublicAccess.getJmbaZbVO(con, basqwsh);
            st = con.createStatement();
            rs = st.executeQuery(sql);
            String isCheck = "0";
            while(rs.next()) {
                isCheck = "1";
            }
            System.out.println("isCheck = " + isCheck);
            map.put("checked", isCheck);
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        finally {
            try {
                if(rs != null) {
                    rs.close();
                }
            }
            catch(Exception exx) {
            }
            try {
                if(st != null) {
                    st.close();
                }
            }
            catch(Exception exx) {
            }
            DBResource.destroyConnection(con);
        }

        return map;

    }

    private Map doSave(Map data) throws BaseException {
        System.out.println("into Processor save");
        Map map = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement st = null;
        try {
            JmbaVO jmbavo = (JmbaVO)data.get(CAcodeConstants.CA_MAPKEY_VO_XMLVO);
            JmbaZbVO zbvo = (JmbaZbVO)jmbavo.getJmsbajl().get(0);
            List mxList = zbvo.getQysdsjmba();

            String zcbashbj = (String)data.get("zcbashbj");

            System.out.println("zcbashbj = "+zcbashbj);
            String wnwsh = (String)data.get("wnwsh");
            String sql = "insert into sfdb.sf_jl_qysdsjmsba_13b(XH,BASQWSH,JSJDM,BAND,SWJGZZJGDM,GXJSLYDM,BTZQYJSJDM,"
                + " BTZQYSWDJZH,BTZQYMC,BTZQYSSD,TZND,TZE,DKE,SHBJ,ZCBA,CJR,CJRQ,LRR,LRRQ) "
                + " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,sysdate,?,sysdate)";

            con = DBResource.getConnection();
            String xh = "";
            st = con.prepareStatement(sql);
            Jmba13BVO vo13 = null;
            System.out.println("mxList.size() = " + mxList.size());
            for(int i = 0, c = mxList.size(); i < c; i++) {
                System.out.println("i = " + i);
                xh = new QysdsUtil().getSequence();
                vo13 = (Jmba13BVO)mxList.get(i);
                st.setString(1, xh);
                st.setString(2, zbvo.getBasqwsh());
                st.setString(3, jmbavo.getNsrxx().getJsjdm());
                if(zcbashbj.equals("0")) {
                    st.setString(4, (Integer.parseInt(DateUtilPro.getCurYearStr4()) - 1) + "");
                } else {
                    st.setString(4, vo13.getBAND());
                }
                st.setString(5, jmbavo.getNsrxx().getSwjgzzjgdm());
                st.setString(6, vo13.getGXJSLYDM());
                st.setString(7, vo13.getBTZQYJSJDM());
                st.setString(8, vo13.getBTZQYSWDJZH());
                st.setString(9, vo13.getBTZQYMC());
                st.setString(10, vo13.getBTZQYSSD());
                st.setString(11, vo13.getTZND());
                st.setString(12, vo13.getTZE() == "" ? "0.00" : vo13.getTZE());
                st.setString(13, vo13.getDKE() == "" ? "0.00" : vo13.getDKE());
                st.setString(14, "1");
                st.setString(15, "1");
                st.setString(16, jmbavo.getNsrxx().getJsjdm());
                st.setString(17, jmbavo.getNsrxx().getJsjdm());
                st.execute();
            }

            if(zcbashbj.equals("0")) {
                sql = "UPDATE sfdb.sf_jl_qysdsjmsba_13b  SET GXJSLYDM = ?,BTZQYJSJDM = ?" +
                    ",BTZQYSWDJZH=?,BTZQYMC=?,BTZQYSSD=?,TZE=?,DKE=?,LRRQ=sysdate,zcba = '0' " +
                    "WHERE XH = ?";
                System.out.println("sql = " + sql);
                st.clearParameters();
                st = con.prepareStatement(sql);
                System.out.println("mxList.size() = " + mxList.size());
                for(int i = 0, c = mxList.size(); i < c; i++) {

                    vo13 = (Jmba13BVO)mxList.get(i);
                    st.setString(1, vo13.getGXJSLYDM());
                    st.setString(2, vo13.getBTZQYJSJDM());
                    st.setString(3, vo13.getBTZQYSWDJZH());
                    st.setString(4, vo13.getBTZQYMC());
                    st.setString(5, vo13.getBTZQYSSD());
                    st.setString(6, vo13.getTZE() == "" ? "0.00" : vo13.getTZE());
                    st.setString(7, vo13.getDKE() == "" ? "0.00" : vo13.getDKE());
                    st.setString(8, vo13.getXH());
                    System.out.println("vo13.getXH() = " + vo13.getXH());

                    st.execute();
                }

                //wnwsh
//
//                st.clearParameters();
//                //保存操作sqzt=2,提交操作sqzt=3
//                sql = "update sfdb.sf_jl_qysdsjmsbajl set sqzt=2 where BASQWSH=?";
//                st = con.prepareStatement(sql);
//                st.setString(1, ( (JmbaZbVO)jmbavo.getJmsbajl().get(0)).getBasqwsh());
//                st.executeUpdate();


            }
//            int[] rst = st.executeBatch();
//
//            System.out.println("执行成功条数：" + rst.length);

            st.clearParameters();
            //保存操作sqzt=2,提交操作sqzt=3
            sql = "update sfdb.sf_jl_qysdsjmsbajl set sqzt=2 where BASQWSH=?";
            st = con.prepareStatement(sql);
            st.setString(1, ( (JmbaZbVO)jmbavo.getJmsbajl().get(0)).getBasqwsh());
            st.executeUpdate();

            st.close();
        }
        catch(Exception ex) {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex, "生成数据错误!");
        }
        finally {
            DBResource.destroyConnection(con);
        }

        return map;
    }

    private Map doUpdate(Map data) throws BaseException {
        System.out.println("into Processor save");
        Map map = null;
        Connection con = null;
        PreparedStatement st = null;
        try {
            JmbaVO jmbavo = (JmbaVO)data.get(CAcodeConstants.CA_MAPKEY_VO_XMLVO);
            JmbaZbVO zbvo = (JmbaZbVO)jmbavo.getJmsbajl().get(0);
            List mxList = zbvo.getQysdsjmba();

            /**
             *  sql = "insert into sfdb.sf_jl_qysdsjmsba_13b(XH,BASQWSH,JSJDM,BAND,SWJGZZJGDM,GXJSLYDM,BTZQYJSJDM,"
                +" BTZQYSWDJZH,BTZQYMC,BTZQYSSD,TZND,TZE,DKE,SHBJ,ZCBA,CJR,CJRQ,LRR,LRRQ) "
                +" values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,sysdate,?,sysdate)";
             */
            String sql = "UPDATE sfdb.sf_jl_qysdsjmsba_13b  SET GXJSLYDM = ?,BTZQYJSJDM = ?" +
                ",BTZQYSWDJZH=?,BTZQYMC=?,BTZQYSSD=?,TZE=?,DKE=?,LRRQ=sysdate " +
                "WHERE XH = ?";
            System.out.println("sql = " + sql);
            con = DBResource.getConnection();
            st = con.prepareStatement(sql);
            Jmba13BVO vo13 = null;
            System.out.println("mxList.size() = " + mxList.size());
            for(int i = 0, c = mxList.size(); i < c; i++) {

                vo13 = (Jmba13BVO)mxList.get(i);
                st.setString(1, vo13.getGXJSLYDM());
                st.setString(2, vo13.getBTZQYJSJDM());
                st.setString(3, vo13.getBTZQYSWDJZH());
                st.setString(4, vo13.getBTZQYMC());
                st.setString(5, vo13.getBTZQYSSD());
                st.setString(6, vo13.getTZE() == "" ? "0.00" : vo13.getTZE());
                st.setString(7, vo13.getDKE() == "" ? "0.00" : vo13.getDKE());
                st.setString(8, vo13.getXH());
                System.out.println("vo13.getXH() = " + vo13.getXH());

                st.execute();
            }
//            int[] rst = st.executeBatch();
//
//            System.out.println("执行成功条数：" + rst.length);

            st.clearParameters();
            //保存操作sqzt=2,提交操作sqzt=3
            sql = "update sfdb.sf_jl_qysdsjmsbajl set sqzt=2 where BASQWSH=?";
            st = con.prepareStatement(sql);
            st.setString(1, ( (JmbaZbVO)jmbavo.getJmsbajl().get(0)).getBasqwsh());
            st.executeUpdate();

            st.close();
        }
        catch(Exception ex) {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex, "生成数据错误!");
        }
        finally {
            DBResource.destroyConnection(con);
        }

        return map;
    }

    private Map doDelete(Map data) throws BaseException {
        System.out.println("into Processor Delete");

        Connection con = null;
        String BASQWSH = (String)data.get("BASQWSH");
        System.out.println("BASQWSH = " + BASQWSH);
        String type = (String)data.get("type");

        String swjdjzh = (String)data.get("swjdjzh");
        String lxdm = (String)data.get("lxdm");
        try {
            con = DBResource.getConnection();
            java.sql.Statement delSt = con.createStatement();

            String del = "delete from sfdb.sf_jl_qysdsjmsba_13b t " +
                "where t.basqwsh = '" + BASQWSH + "' and t.gxjslydm = '" + lxdm + "' and t.btzqyswdjzh = '" + swjdjzh +
                "'";
            System.out.println("del sql = " + del);
            delSt.execute(del);
            delSt.close();
        }
        catch(Exception ex) {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex, "生成数据错误!");
        }
        finally {
            DBResource.destroyConnection(con);
        }
        return null;
    }

}

