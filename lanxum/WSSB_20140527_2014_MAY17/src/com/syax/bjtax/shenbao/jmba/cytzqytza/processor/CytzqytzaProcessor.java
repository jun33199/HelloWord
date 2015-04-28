package com.syax.bjtax.shenbao.jmba.cytzqytza.processor;

import com.ttsoft.bjtax.shenbao.util.DBResource;

import com.syax.bjtax.ca.util.DzyjHelper;
import com.syax.bjtax.ca.vo.DzyjsjVO;
import com.syax.bjtax.shenbao.jmba.jmbaz.JmbaActionConstant;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.exception.SystemException;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;


import com.syax.bjtax.shenbao.jmba.xmlvo.Jmba13AVO;
import com.syax.bjtax.shenbao.jmba.dao.PublicAccess;
import com.syax.bjtax.shenbao.jmba.xmlvo.JmbaZbVO;
import com.ttsoft.bjtax.shenbao.util.Debug;
import com.syax.bjtax.shenbao.jmba.xmlvo.JmbaVO;
import com.syax.common.util.CAcodeConstants;
import com.syax.bjtax.shenbao.jmba.xmlvo.Jmba12VO;
import java.sql.PreparedStatement;
import com.syax.bjtax.shenbao.util.QysdsUtil;
import com.syax.bjtax.shenbao.jmba.jmbaz.JmbamxBo;
import com.ttsoft.bjtax.shenbao.util.DateUtilPro;
import java.util.Date;
import java.text.SimpleDateFormat;

public class CytzqytzaProcessor implements Processor{

    /**
     * 总控数据
     */
    private UserData userData;
    /**
     * 处理请求
     */
    public Object process(VOPackage vo) throws BaseException {
        this.userData = vo.getUserData();
        Object result = null;
        // 根据业务操作类型值来做业务操作
        try {
            switch(vo.getAction()) {
                // 查询
                case JmbaActionConstant.INTI_ACTION_QUERY:
                    vo.setData(this.doQuery( (Map)vo.getData()));

                case JmbaActionConstant.INTI_ACTION_SHOW:
                	Debug.out("processor111111111111");
    				result = doShow(vo);

    				return result;
                case JmbaActionConstant.INTI_ACTION_SAVE:
                	Debug.out("JMBA07 SAVE");
    				result = doSave(vo);
    				return result;
                case JmbaActionConstant.INTI_ACTION_DELETE:
                    vo.setData(this.doDelete( (Map)vo.getData()));
                    return vo;
                case JmbaActionConstant.INTI_ACTION_COMMIT:
    				vo.setData(this.doCommit((Map) vo.getData()));
    				return vo;


                    // 没有可调用的方法
                default:
                    throw new SystemException("no such mothod");
            }
        }
        catch(Exception e) {
            throw ExceptionUtil.getBaseException(e);
        }
    }
    private Map doCommit(Map data) throws BaseException {
		Connection con = null;
		PreparedStatement st = null;
		String BASQWSH = (String) data.get("BASQWSH");
		System.out.println("BASQWSH = " + BASQWSH);
		con = DBResource.getConnection();
		String sql = "update sfdb.sf_jl_qysdsjmsbajl set sqzt=3 where BASQWSH=?";// '"+((JmbaZbVO)jmbavo.getJmsbajl().get(0)).getBasqwsh()+"'";
		try {
		st = con.prepareStatement(sql);
		st.setString(1, BASQWSH);
		st.executeUpdate();
        st.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex, "生成数据错误!");
		} finally {
			DBResource.destroyConnection(con);
		}
		return null;
	}

    private Object doSave(VOPackage vo) throws BaseException {
    	List list=new ArrayList();
        DzyjHelper dh = new DzyjHelper();
		Map hm=(Map)vo.getData();
        JmbaVO bavo = (JmbaVO)hm.get(CAcodeConstants.CA_MAPKEY_VO_XMLVO);
		DzyjsjVO dzyj = (DzyjsjVO) hm.get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);
		JmbaZbVO vo1 = (JmbaZbVO)bavo.getJmsbajl().get(0);

		Jmba13AVO mxvo=(Jmba13AVO)vo1.getQysdsjmba().get(0);
		UserData ud = (UserData)vo.getUserData();
		Connection conn = null;
		PreparedStatement ps = null;
		com.syax.bjtax.shenbao.util.QysdsUtil qysdsUtil = new com.syax.bjtax.shenbao.util.QysdsUtil();
		System.out.println("into Processor save");
        try {
        	conn = DBResource.getConnection();
			JmbaZbVO vo2=PublicAccess.getJmbaZbVO(conn, vo1.getBasqwsh());
			//conn.setHoldability(1);
			//新增
			if (vo2.getBasqwsh()==null || "".equals(vo2.getBasqwsh())){
				PublicAccess.saveZb(conn,bavo,ud);
			}
			String sql = "";
			if(mxvo.getXh()!=null && !mxvo.getXh().equals("")){
				sql = "UPDATE sfdb.sf_jl_qysdsjmsba_13a SET "+
				" gxjslydm='"+mxvo.getGxjslydm()+"',ctqyzsjbh='"+mxvo.getCtqyzsjbh()+

				"',btzqymcjzsbh='"+mxvo.getBtzqymcjzsbh()+

				"',LRR='"+ud.getYhid()+"',LRRQ=sysdate where xh ='"+mxvo.getXh()+"'";
				}else{
					String xh = qysdsUtil.getSequence();
				sql = "INSERT INTO sfdb.sf_jl_qysdsjmsba_13a (xh,basqwsh,jsjdm,band,swjgzzjgdm,gxjslydm,ctqyzsjbh,"
                +" btzqymcjzsbh,cjr,cjrq,lrr,lrrq) VALUES('"+
				xh+"','" +vo1.getBasqwsh()+"','"+
				bavo.getNsrxx().getJsjdm()+"','"+
				vo1.getBand()+"','"+
				bavo.getNsrxx().getSwjgzzjgdm()+"','"+
				mxvo.getGxjslydm()+"','"+
				mxvo.getCtqyzsjbh()+"','"+
				mxvo.getBtzqymcjzsbh()+

				"','"+ud.getYhid()+"',sysdate,'"+ud.getYhid()+"',sysdate)";
			}
			System.out.println("02Processor===doSave===sql="+sql);
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			if (ps != null) {
				ps.close();
			}
        }
        catch(Exception ex)
        {
        	throw ExceptionUtil.getBaseException(ex);
        }
        finally
        {
        	DBResource.destroyConnection(conn);
        }

        return list;
    }

    private Map doDelete(Map data) throws BaseException {
        System.out.println("into Processor Delete");

        Connection con = null;
        String BASQWSH = (String)data.get("BASQWSH");
         System.out.println("BASQWSH = "+BASQWSH);
         String type = (String)data.get("type");
         String selIndex = (String)data.get("selIndex");
        try {
            con = DBResource.getConnection();
            java.sql.Statement delSt = con.createStatement();

            String del =  "delete from sfdb.sf_jl_qysdsjmsba_13a t where t.basqwsh = '"+BASQWSH+"' and t.xh = '"+selIndex+"'";
            delSt.execute(del);


            delSt.close();
           }
        catch(Exception ex)
        {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex, "生成数据错误!");
        }
        finally
        {
            DBResource.destroyConnection(con);
        }
        return null;
    }



    /**
     */
    private Map doQuery(Map data) throws BaseException {
        Map map = null;

        return map;
    }
    private Jmba13AVO getBamxvo(ResultSet rs) throws Exception {
		Jmba13AVO vo = new Jmba13AVO();
		try {
			 vo.setBand(rs.getString("Band"));
             vo.setBasqwsh(rs.getString("Basqwsh"));
             vo.setBtzqymcjzsbh(rs.getString("Btzqymcjzsbh"));
             vo.setCjr(rs.getString("Cjr"));
             vo.setCjrq(rs.getString("Cjrq"));
             vo.setCtqyzsjbh(rs.getString("Ctqyzsjbh"));
             vo.setGxjslydm(rs.getString("Gxjslydm"));
             vo.setJsjdm(rs.getString("Jsjdm"));
             vo.setLrr(rs.getString("Lrr"));
             vo.setLrrq(rs.getString("Lrrq"));
             vo.setQtzl(rs.getString("Qtzl"));
             vo.setSfrsyyezcbcx(rs.getString("Sfrsyyezcbcx"));
             vo.setSftjtzyzqk(rs.getString("Sftjtzyzqk"));
             vo.setSftjwsssm(rs.getString("Sftjwsssm"));
             vo.setSftjyzzm(rs.getString("Sftjyzzm"));
             vo.setSfynjhgtzs(rs.getString("Sfynjhgtzs"));
             vo.setXh(rs.getString("Xh"));

			return vo;
		} catch (Exception e) {
			throw e;
		}
	}
    /**
     */
    private Object doShow(VOPackage vo) throws BaseException {
    	JmbamxBo bo = (JmbamxBo) vo.getData();
		JmbaZbVO vo1 = null;
		UserData ud = vo.getUserData();
		Connection con = null;
		ResultSet rs = null;
		Statement st = null;
		PreparedStatement ps = null;
        try {
        	con = DBResource.getConnection();
			vo1 = PublicAccess.getJmbaZbVO(con, bo.getBasqwsh());
			Date date = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String tjsj = df.format(date);
			StringBuffer sb = new StringBuffer();
			List list = new ArrayList();
			if (vo1.getBasqwsh() == null || "".equals(vo1.getBasqwsh())) {
				vo1 = new JmbaZbVO();
				vo1.setBand(bo.getBand());
				vo1.setBasqbh(bo.getBasqbh());
				vo1.setBasqwsh(bo.getBasqwsh());
				vo1.setJmbasxdm(bo.getJmbasxdm());
				vo1.setLrrq(new SimpleDateFormat("yyyy-MM-dd")
						.format(new Date()));
				sb = new StringBuffer();

				sb.append("select * ");
				sb
						.append(" from sfdb.sf_jl_qysdsjmsba_13a t,sfdb.sf_jl_qysdsjmsbajl c where  t.band='"
								+ (Integer.parseInt(bo.getBand()) - 1) + "' ");
				sb
						.append(" and t.basqwsh=c.basqwsh and c.sqzt = '4'  and  t.jsjdm='"
								+ ud.getYhid() + "' and rownum=1 ");
				System.out.println("===query wn sql==" + sb.toString());
				ps = con.prepareStatement(sb.toString());
				rs = ps.executeQuery();

				while (rs.next()) {
					Jmba13AVO mxvo = getBamxvo(rs);
					mxvo.setXh("");
					list.add(mxvo);
				}

                if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }


				// }
				vo1.setQysdsjmba(list);
				return vo1;
			}
			sb=new StringBuffer();
			sb.append("select * " );//t.SFYCJRMC,t.SFYSHBX,t.SFYLDHT,t.SFYCJRZM,t.SFYGZZM,
//					sb.append(" decode(t.SFYCJRMC,'0','是','1','否','') SFYCJRMCmc ,t.QTZL, ");
//            sb.append(" decode(t.SFYSHBX,'0','是','1','否','') SFYSHBXmc , ");
//            sb.append(" decode(t.SFYLDHT,'0','是','1','否','') SFYLDHTmc , ");
//            sb.append(" decode(t.SFYCJRZM,'0','是','1','否','') SFYCJRZMmc , ");
//            sb.append(" decode(t.SFYGZZM,'0','是','1','否','') SFYGZZMmc  ");

            sb.append(" from sfdb.sf_jl_qysdsjmsba_13A t where  basqwsh='"+bo.getBasqwsh()+"'");


			if (bo.getXh()!=null && !bo.getXh().equals("")){
				sb.append(" and a.xh="+bo.getXh()+" ");
			}

			System.out.println("===query sql=="+sb.toString());
			ps = con.prepareStatement(sb.toString());
			rs = ps.executeQuery();
			int i = 0;
			while (rs.next()) {
				 Jmba13AVO mxvo=getBamxvo(rs);
	             list.add(mxvo);
			}
			vo1.setQysdsjmba(list);
			System.out.println("query mx done");
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}


        } catch (Exception ex) {
        	throw ExceptionUtil.getBaseException(ex);
        } finally {
        	DBResource.destroyConnection(con);
        }

        return vo1;

    }

}
