//package com.syax.bjtax.shenbao.jmba.qygza.processor;
//
//import com.ttsoft.bjtax.shenbao.util.DBResource;
//
//import com.syax.bjtax.shenbao.jmba.jmbaz.JmbaActionConstant;
//import com.ttsoft.common.model.UserData;
//import com.ttsoft.framework.exception.BaseException;
//import com.ttsoft.framework.exception.ExceptionUtil;
//import com.ttsoft.framework.exception.SystemException;
//import com.ttsoft.framework.processor.Processor;
//import com.ttsoft.framework.util.VOPackage;
//import java.util.Map;
//import java.util.HashMap;
//import java.sql.ResultSet;
//import java.sql.Statement;
//import java.sql.Connection;
//import com.syax.bjtax.shenbao.jmba.xmlvo.Jmba14AVO;
//import com.syax.bjtax.shenbao.jmba.dao.PublicAccess;
//import com.syax.bjtax.shenbao.jmba.xmlvo.JmbaZbVO;
//import com.ttsoft.bjtax.shenbao.util.Debug;
//import com.syax.bjtax.shenbao.jmba.xmlvo.Jmba14AVO;
//import com.syax.bjtax.shenbao.jmba.xmlvo.JmbaVO;
//import com.syax.common.util.CAcodeConstants;
//import java.sql.PreparedStatement;
//import com.syax.bjtax.shenbao.util.QysdsUtil;
//import com.syax.bjtax.shenbao.jmba.xmlvo.Jmba13AVO;
//
//public class QygzaProcessor  implements Processor{
//
//    /**
//     * 总控数据
//     */
//    private UserData userData;
//    /**
//     * 处理请求
//     */
//    public Object process(VOPackage vo) throws BaseException {
//        this.userData = vo.getUserData();
//        System.out.println("vo.getAction()= "+vo.getAction()+"");
//        // 根据业务操作类型值来做业务操作
//        try {
//            switch(vo.getAction()) {
//                // 查询
//                case JmbaActionConstant.INTI_ACTION_QUERY:
//                    vo.setData(this.doQuery( (Map)vo.getData()));
//
//                case JmbaActionConstant.INTI_ACTION_SHOW:
//                    vo.setData(this.doShow( (Map)vo.getData()));
//                    return vo;
//                case JmbaActionConstant.INTI_ACTION_SAVE:
//                    vo.setData(this.doSave( (Map)vo.getData()));
//                    return vo;
//                case JmbaActionConstant.INTI_ACTION_DELETE:
//                    vo.setData(this.doDelete( (Map)vo.getData()));
//                    return vo;
//
//
//                    // 没有可调用的方法
//                default:
//                    throw new SystemException("no such mothod");
//            }
//        }
//        catch(Exception e) {
//            throw ExceptionUtil.getBaseException(e);
//        }
//    }
//
//    private Map doSave(Map data) throws BaseException {
//        System.out.println("into Processor save");
//        Map map = null;
//        Connection con = null;
//        ResultSet rs = null;
//        PreparedStatement st = null;
//        try {
//            JmbaVO jmbavo = (JmbaVO)data.get(CAcodeConstants.CA_MAPKEY_VO_XMLVO);
//            String type = (String) data.get("type");
//            String selIndex = (String) data.get("selIndex");
//
//            String sql = "insert into sfdb.sf_jl_qysdsjmsba_14a(xh,basqwsh,jsjdm,band,swjgzzjgdm,zysblxdm,zysbmc,"
//                +" gznd,sfgmfpjqd,sfsykphmxz,sfsygdsm,sfsyqksm,tze,dmynse,qtzl,cjr,cjrq,lrrq,lrr) "
//                +" values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,sysdate,sysdate,?)";
//            Debug.out("save方法执行是sql语句-----------------------------------"+sql);
//
//            con = DBResource.getConnection();
//            String xh = "";
//            System.out.println("type = "+type);
//            System.out.println("selIndex--------------------------- = "+selIndex);
//            if(type != null && selIndex != null && type.equals("Update")) {
//                java.sql.Statement delSt = con.createStatement();
//
//                String del = "delete from sfdb.sf_jl_qysdsjmsba_14a t where t.basqwsh = '" +
//                    ( (JmbaZbVO)jmbavo.getJmsbajl().get(0)).getBasqwsh() + "'" +
//                    " and t.xh = '" + selIndex + "'";
//                System.out.println("del sql =  " + del);
//                delSt.execute(del);
//                xh = selIndex;
//            }else{
//                xh = new QysdsUtil().getSequence();
//            }
//
//            st = con.prepareStatement(sql);
//            st.setString(1,xh);
//            st.setString(2,((JmbaZbVO)jmbavo.getJmsbajl().get(0)).getBasqwsh());
//            st.setString(3,jmbavo.getNsrxx().getJsjdm());
//            st.setString(4,((JmbaZbVO)jmbavo.getJmsbajl().get(0)).getBand());
//            st.setString(5,jmbavo.getNsrxx().getSwjgzzjgdm());
//            st.setString(6,((Jmba14AVO)((JmbaZbVO)jmbavo.getJmsbajl().get(0)).getQysdsjmba().get(0)).getZysblxdm());
//            st.setString(7,((Jmba14AVO)((JmbaZbVO)jmbavo.getJmsbajl().get(0)).getQysdsjmba().get(0)).getZysbmc());
//            st.setString(8,((Jmba14AVO)((JmbaZbVO)jmbavo.getJmsbajl().get(0)).getQysdsjmba().get(0)).getGznd());
//            st.setString(9,((Jmba14AVO)((JmbaZbVO)jmbavo.getJmsbajl().get(0)).getQysdsjmba().get(0)).getSfgmfpjqd());
//            st.setString(10,((Jmba14AVO)((JmbaZbVO)jmbavo.getJmsbajl().get(0)).getQysdsjmba().get(0)).getSfsykphmxz());
//            st.setString(11,((Jmba14AVO)((JmbaZbVO)jmbavo.getJmsbajl().get(0)).getQysdsjmba().get(0)).getSfsygdsm());
//            st.setString(12,((Jmba14AVO)((JmbaZbVO)jmbavo.getJmsbajl().get(0)).getQysdsjmba().get(0)).getSfsyqksm());
//            st.setString(13,((Jmba14AVO)((JmbaZbVO)jmbavo.getJmsbajl().get(0)).getQysdsjmba().get(0)).getTze());
//            st.setString(14,((Jmba14AVO)((JmbaZbVO)jmbavo.getJmsbajl().get(0)).getQysdsjmba().get(0)).getDmynse());
//            st.setString(15,((Jmba14AVO)((JmbaZbVO)jmbavo.getJmsbajl().get(0)).getQysdsjmba().get(0)).getQtzl());
//            st.setString(16,jmbavo.getNsrxx().getJsjdm());
//            st.setString(17,jmbavo.getNsrxx().getJsjdm());
//            st.executeUpdate();
//
//
//            st.clearParameters();
//            //保存操作sqzt=2,提交操作sqzt=3
//            sql="update sfdb.sf_jl_qysdsjmsbajl set sqzt=2 where BASQWSH=?";
//            st = con.prepareStatement(sql);
//            st.setString(1,((JmbaZbVO)jmbavo.getJmsbajl().get(0)).getBasqwsh());
//            st.executeUpdate();
//
//            st.close();
//           }
//        catch(Exception ex)
//        {
//            ex.printStackTrace();
//            throw ExceptionUtil.getBaseException(ex, "生成数据错误!");
//        }
//        finally
//        {
//            DBResource.destroyConnection(con);
//        }
//
//        return map;
//    }
//
//    private Map doDelete(Map data) throws BaseException {
//        System.out.println("into Processor Delete");
//
//        Connection con = null;
//        String BASQWSH = (String)data.get("BASQWSH");
//         System.out.println("BASQWSH = "+BASQWSH);
//         String type = (String)data.get("type");
//         String selIndex = (String)data.get("selIndex");
//        try {
//            con = DBResource.getConnection();
//            java.sql.Statement delSt = con.createStatement();
//
//            String del =  "delete from sfdb.sf_jl_qysdsjmsba_14a t where t.basqwsh = '"+BASQWSH+"' and t.xh = '"+selIndex+"'";
//            delSt.execute(del);
//
//
//            delSt.close();
//           }
//        catch(Exception ex)
//        {
//            ex.printStackTrace();
//            throw ExceptionUtil.getBaseException(ex, "生成数据错误!");
//        }
//        finally
//        {
//            DBResource.destroyConnection(con);
//        }
//        return null;
//    }
//
//
//
//    /**
//     */
//    private Map doQuery(Map data) throws BaseException {
//        Map map = null;
//
//        return map;
//    }
//
//    /**
//     */
//    private Map doShow(Map data) throws BaseException {
//        Map map = new HashMap();
//        String BASQWSH = (String)data.get("BASQWSH");
//        System.out.println("BASQWSH = "+BASQWSH);
//        String type = (String)data.get("type");
//        String selIndex = (String)data.get("selIndex");
//
//        Jmba14AVO vo = null;
//        Connection con = null;
//        ResultSet rs = null;
//        Statement st = null;
//
//        try {
//
//            String sql = "select * from sfdb.sf_jl_qysdsjmsba_14a t where t.basqwsh = '"+BASQWSH+"'";
//            if(type != null && type.equals("EDITOR")) {
//                sql += " and t.xh = '" + selIndex + "'";
//                type = "SHOW";
//            }
//            sql += " order by t.xh";
//            System.out.println("sql = " + sql);
////            sql = "insert into sfdb.sf_jl_qysdsjmsba_14a(xh,basqwsh,jsjdm,band,swjgzzjgdm,zysblxdm,zysbmc,"
////                          +" gznd,sfgmfpjqd,sfsykphmxz,sfsygdsm,sfsyqksm,tze,dmynse,qtzl,cjr,cjrq,lrrq,lrr) "
////                +" values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,sysdate,sysdate,?)";
//
//            System.out.println("sql = "+sql);
//            con = DBResource.getConnection();
//
//            JmbaZbVO zbvo = PublicAccess.getJmbaZbVO(con,BASQWSH);
//            System.out.println("zbvo is "+zbvo);
//            st = con.createStatement();
//            rs = st.executeQuery(sql);
//            while(rs.next() && type != null && type.equals("SHOW")) {
//                vo = new Jmba14AVO();
//                vo.setBand(rs.getString("band"));
//                vo.setBasqwsh(rs.getString("basqwsh"));
//                vo.setDmynse(rs.getString("dmynse"));
//                vo.setCjr(rs.getString("cjr"));
//                vo.setCjrq(rs.getString("cjrq"));
//                vo.setGznd(rs.getString("gznd"));
//                vo.setJsjdm(rs.getString("jsjdm"));
//                vo.setLrr(rs.getString("lrr"));
//                vo.setLrrq(rs.getString("lrrq"));
//                vo.setQtzl(rs.getString("qtzl"));
//                vo.setSfgmfpjqd(rs.getString("sfgmfpjqd"));
//                vo.setSfsygdsm(rs.getString("sfsyqksm"));
//                vo.setSfsykphmxz(rs.getString("sfsykphmxz"));
//                vo.setSfsyqksm(rs.getString("sfsygdsm"));
//                vo.setTze(rs.getString("tze"));
//                vo.setXh(rs.getString("xh"));
//                vo.setZysblxdm(rs.getString("zysblxdm"));
//                vo.setZysbmc(rs.getString("zysbmc"));
//                vo.setSwjgzzjgdm(rs.getString("swjgzzjgdm"));
//                zbvo.getQysdsjmba().add(vo);
//                System.out.println("vo to xml "+vo.toXML());
//            }
//
//            sql = " select sum(NVL(t.tze,0)) tze,sum(NVL(t.dmynse,0))dmynse from"+
//                " sfdb.sf_jl_qysdsjmsba_14a t where t.basqwsh = '"+BASQWSH+"'";
//            rs = st.executeQuery(sql);
//            while(rs.next() && type != null && type.equals("SHOW")) {
//                vo = new Jmba14AVO();
//
//                vo.setBand("/");
//                vo.setBasqwsh("/");
//                vo.setDmynse(rs.getString("dmynse"));
//                vo.setCjr("/");
//                vo.setCjrq("/");
//                vo.setGznd("/");
//                vo.setJsjdm("/");
//                vo.setLrr("/");
//                vo.setLrrq("/");
//                vo.setQtzl("/");
//                vo.setSfgmfpjqd("/");
//                vo.setSfsygdsm("/");
//                vo.setSfsykphmxz("/");
//                vo.setSfsyqksm("/");
//                vo.setTze(rs.getString("tze"));
//                vo.setXh("合计");
//                vo.setZysblxdm("/");
//                vo.setZysbmc("/");
//                vo.setSwjgzzjgdm("/");
//                zbvo.getQysdsjmba().add(vo);
//                System.out.println("vo to xml "+vo.toXML());
//            }
//
//            map.put("JmbaZbVO",zbvo);
//            //map.put("JmbaZlqdVO",PublicAccess.getJmbaZlqdVO(con,BASQWSH));
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        } finally {
//            try {
//                if (rs != null) {
//                    rs.close();
//                }
//            } catch (Exception exx) {
//            }
//            try {
//                if (st != null) {
//                    st.close();
//                }
//            } catch (Exception exx) {
//            }
//            try {
//                if (con != null) {
//                    con.close();
//                }
//            } catch (Exception exx) {
//            }
//        }
//
//        return map;
//
//    }
//
//}
//
