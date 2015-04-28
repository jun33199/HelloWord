//package com.syax.bjtax.shenbao.jmba.qygzb.processor;
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
//import com.syax.bjtax.shenbao.jmba.xmlvo.Jmba14BVO;
//import com.syax.bjtax.shenbao.jmba.dao.PublicAccess;
//import com.syax.bjtax.shenbao.jmba.xmlvo.JmbaZbVO;
//
//public class QygzbProcessor  implements Processor{
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
//
//        // 根据业务操作类型值来做业务操作
//        try {
//            switch(vo.getAction()) {
//                // 查询
//                case JmbaActionConstant.INTI_ACTION_QUERY:
//                    vo.setData(this.doQuery((Map)vo.getData()));
//
//                case JmbaActionConstant.INTI_ACTION_SHOW:
//                    vo.setData(this.doShow((Map)vo.getData()));
//                /**
//                 * @todo
//                 */
//
//                return vo;
//                // 没有可调用的方法
//                default:
//                    throw new SystemException("no such mothod");
//            }
//        }
//        catch(Exception e) {
//            throw ExceptionUtil.getBaseException(e);
//        }
//    }
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
//        Jmba14BVO vo = null;
//        Connection con = null;
//        ResultSet rs = null;
//        Statement st = null;
//
//        try {
//
//            String sql = "select * from sfdb.sf_jl_qysdsjmsba_14a t where t.basqwsh = '"+BASQWSH+"'";
//            System.out.println("sql = "+sql);
//            con = DBResource.getConnection();
//
//            JmbaZbVO zbvo = PublicAccess.getJmbaZbVO(con,BASQWSH);
//            System.out.println("zbvo is "+zbvo);
//            st = con.createStatement();
//            rs = st.executeQuery(sql);
//            while(rs.next()) {
//                vo = new Jmba14BVO();
//
//                vo.setBand(rs.getString("Band"));
//                vo.setBasqwsh(rs.getString("Basqwsh"));
//                vo.setCjr(rs.getString("Cjr"));
//                vo.setCjrq(rs.getString("Cjrq"));
//                vo.setDmynse(rs.getString("Dmynse"));
//                vo.setDmynse2008(rs.getString("Dmynse2008"));
//                vo.setDmynse2009(rs.getString("Dmynse2009"));
//                vo.setJsjdm(rs.getString("Jsjdm"));
//                vo.setLrr(rs.getString("Lrr"));
//                vo.setLrrq(rs.getString("Lrrq"));
//                vo.setTzezs(rs.getString("Tzezs"));
//                vo.setTzezs2008(rs.getString("Tzezs2008"));
//                vo.setTzezs2009(rs.getString("Tzezs2009"));
//                vo.setXh(rs.getString("Xh"));
//                vo.setZysblxdm(rs.getString("Zysblxdm"));
//                vo.setZysbmc(rs.getString("Zysbmc"));
//
//
//                zbvo.getQysdsjmba().add(vo);
//                System.out.println("vo to xml "+vo.toXML());
//            }
//            map.put("Jmba16VO",vo);
//            map.put("JmbaZbVO",PublicAccess.getJmbaZbVO(con,BASQWSH));
//            map.put("JmbaZlqdVO",PublicAccess.getJmbaZlqdVO(con,BASQWSH));
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
//
