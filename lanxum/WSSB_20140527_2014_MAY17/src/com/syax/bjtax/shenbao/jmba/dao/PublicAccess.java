package com.syax.bjtax.shenbao.jmba.dao;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.util.VOPackage;

import java.util.Map;
import java.util.HashMap;
import com.syax.bjtax.shenbao.jmba.xmlvo.Jmba12VO;
import com.syax.bjtax.shenbao.jmba.xmlvo.JmbaVO;
import com.ttsoft.bjtax.shenbao.util.DBResource;
import com.ttsoft.bjtax.shenbao.util.DateUtilPro;
import com.ttsoft.common.model.UserData;

import java.sql.Connection;
import com.syax.bjtax.shenbao.jmba.xmlvo.JmbaZbVO;
import java.util.List;
import java.util.ArrayList;
import com.syax.bjtax.shenbao.jmba.xmlvo.JmbaZlqdVO;
import com.syax.bjtax.shenbao.util.QysdsUtil;
import com.syax.common.util.CAcodeConstants;

public class PublicAccess {
    public PublicAccess() {
    }

 

    /**
     */
    public static JmbaZbVO getJmbaZbVO(Connection con,String BASQWSH) throws BaseException {

        System.out.println("BASQWSH = "+BASQWSH);
        JmbaZbVO vo = new JmbaZbVO();

        ResultSet rs = null;
        Statement st = null;
        try {
            //String sql = "select t.*,  decode(t.sqzt,'1','已保存未审核','2','已提交未审核','3','审核未通过','4','审核已通过','5','审核未通过') sqztmc, (select c.jmbasxmc from dmdb.sf_dm_jmbasxdm c where c.jmbasxdm=t.jmbasxdm) jmbasxmc from sfdb.sf_jl_qysdsjmsbajl t where t.basqwsh = '"+BASQWSH+"'";
			String sql = "SELECT T.basqwsh,T.BAsqbh,T.JSJDM,T.BAND,A.NSRMC,S.JMBASXMC,to_char(T.QSRQ, 'yyyy-mm-dd') QSRQ,to_char(T.JZRQ, 'yyyy-mm-dd') JZRQ,to_char(T.LRRQ, 'yyyy-mm-dd') LRRQ,T.BAJMSE,T.BAJMbl,T.LRRQ, T.sqzt,T.szDM,"+
			"t.fhwjmc,T.LRR,T.JMBASXDM, decode(t.sqzt,'1','已保存未提交','2','保存未审核','3','提交未审核','4','审核已通过','5','审核未通过') sqztmc FROM SFDB.SF_JL_QYSDSJMSBAJL T, DJDB.DJ_JL_JBSJ A, DMDB.SF_DM_JMBASXDM S " +
			"WHERE T.JSJDM = A.JSJDM AND T.JMBASXDM = S.JMBASXDM  AND T.BASQWSH = '"+BASQWSH+"'";
         
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()) {
               //vo.setXh(rs.getString("BASQWSH"));
               vo.setBasqwsh(rs.getString("BASQWSH"));
               vo.setBajmse(rs.getString("BAJMSE"));
               vo.setBajmbl(rs.getString("BAJMbl"));
               vo.setBand(rs.getString("BAND"));
               vo.setBasqbh(rs.getString("BASQBH"));
//               vo.setBsfsdm(rs.getString(""));
               vo.setLrrq(rs.getString("LRRQ"));
               vo.setFhwjmc(rs.getString("FHWJMC"));
               vo.setJmbasxdm(rs.getString("JMBASXDM"));
               vo.setJzrq(rs.getString("JZRQ"));
               vo.setSzdm(rs.getString("SZDM"));
               vo.setQsrq(rs.getString("QSRQ"));
               vo.setSzmc("企业所得税");
               vo.setJmbasxmc(rs.getString("JMBASXMC"));
               vo.setZtdm(rs.getString("SQZT"));
               vo.setZtmc(rs.getString("SQZTMC"));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception exx) {
            }
            try {
                if (st != null) {
                    st.close();
                }
            } catch (Exception exx) {
            }
        }

        return vo;

    }


    /**
     */
    public static List getJmbaZlqdVO(Connection con,String BASQWSH) throws BaseException {

        System.out.println("BASQWSH = "+BASQWSH);
        JmbaZlqdVO vo = null;
        List list = new ArrayList();
        ResultSet rs = null;
        Statement st = null;
        try {
            String sql = "select t.*, t.rowid from sfdb.SF_JL_QYSDSJMSBAJLZLQD t where t.basqwsh = '"+BASQWSH+"' ORDER BY T.XH";
     
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()) {
                vo = new JmbaZlqdVO();
                vo.setXh(rs.getString("XH"));
                vo.setZlqd(rs.getString("ZLQD"));
                vo.setSfkysc(rs.getString("sfkysc"));
                vo.setSfshtg(rs.getString("sfshtg"));
                if ("0".equals(vo.getSfshtg())){
                	vo.setSfshtgmc("未审核");
                }
                if ("1".equals(vo.getSfshtg())){
                	vo.setSfshtgmc("有");
                }
                if ("2".equals(vo.getSfshtg())){
                	vo.setSfshtgmc("无");
                }
                list.add(vo);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception exx) {
            }
            try {
                if (st != null) {
                    st.close();
                }
            } catch (Exception exx) {
            }
        }

        return list;

    }

	public static Object saveZb(Connection conn,JmbaVO form,UserData ud) throws BaseException {
		System.out.println("进入到保存processor");
		
		ResultSet rs = null;
		PreparedStatement pst;
		QysdsUtil qysdsUtil = new QysdsUtil();
		HashMap map = new HashMap();
		// 获取简称
		try {
			
			String qxdm=ud.getSsdwdm().substring(0, 2);
			
			String basqwsh = ((JmbaZbVO)form.getJmsbajl().get(0)).getBasqwsh();
			System.out.println("basqwsh===="+basqwsh);
			String basqbh = ((JmbaZbVO)form.getJmsbajl().get(0)).getBasqbh();
			boolean modify=true; //默认为修改状态，如果备案申请编号和文书号为空，设置为新增状态
			if(basqwsh==null || basqwsh.trim().length()==0){//判断如果备案申请文书号为空，则认为是新增状态
				
				return null;
			}
			
			System.out.println("====basqwsh===="+((JmbaZbVO)form.getJmsbajl().get(0)).getBasqwsh());
			String bajmsehbl="";
			String sql = "";
			
				sql="insert into SFDB.SF_JL_QYSDSJMSBAJL(BASQWSH,basqbh,JSJDM,jmbasxdm,szdm,sqzt,sqlxdm,BAND,tjr,tjsj,CJR,CJRQ,LRR,LRRQ,swjgzzjgdm) values(?,?,?,?,?,?,?,?,?,sysdate,?,sysdate,?,sysdate,?)";
			

				pst = conn.prepareStatement(sql);
				pst.setString(1,basqwsh);
				pst.setString(2,basqbh);
				pst.setString(3,form.getNsrxx().getJsjdm());			
				pst.setString(4,((JmbaZbVO)form.getJmsbajl().get(0)).getJmbasxdm());
				
				pst.setString(5,"30");
				////保存操作sqzt=1,提交操作sqzt=2
				pst.setString(6,"1");
				pst.setString(7,"0");
				pst.setString(8,((JmbaZbVO)form.getJmsbajl().get(0)).getBand());
				
				pst.setString(9,form.getNsrxx().getJsjdm());
				pst.setString(10,form.getNsrxx().getJsjdm());
				pst.setString(11,form.getNsrxx().getJsjdm());
				pst.setString(12,ud.getSsdwdm());			

		
			System.out.println("MainProcessor==doSave==sql==="+sql);

			pst.execute();

		     if(pst!=null){
		    	 pst.close(); 
		     }
		} catch (Exception ex) {
			ex.printStackTrace();	
			throw new ApplicationException("用户执行了系统不支持的方法或功能.");
		} 
		return form;
	}


}
