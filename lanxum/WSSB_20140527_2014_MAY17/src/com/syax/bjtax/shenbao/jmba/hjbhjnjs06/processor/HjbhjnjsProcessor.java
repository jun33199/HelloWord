/*
 * Created on 2010-1-5
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.syax.bjtax.shenbao.jmba.hjbhjnjs06.processor;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.syax.bjtax.ca.util.DzyjHelper;
import com.syax.bjtax.ca.vo.DzyjsjVO;
import com.syax.bjtax.shenbao.jmba.dao.PublicAccess;
import com.syax.bjtax.shenbao.jmba.jmbaz.JmbaActionConstant;
import com.syax.bjtax.shenbao.jmba.jmbaz.JmbamxBo;
import com.syax.bjtax.shenbao.jmba.xmlvo.Jmba01VO;
import com.syax.bjtax.shenbao.jmba.xmlvo.Jmba02VO;
import com.syax.bjtax.shenbao.jmba.xmlvo.Jmba06VO;
import com.syax.bjtax.shenbao.jmba.xmlvo.Jmba06WnVO;
import com.syax.bjtax.shenbao.jmba.xmlvo.JmbaVO;
import com.syax.bjtax.shenbao.jmba.xmlvo.JmbaZbVO;
import com.syax.bjtax.shenbao.jmba.xmlvo.VOConstants;
import com.syax.common.util.CAcodeConstants;
import com.ttsoft.bjtax.shenbao.model.domain.Wynsksb;
import com.ttsoft.bjtax.shenbao.util.CAUtils;
import com.ttsoft.bjtax.shenbao.util.DBResource;
import com.ttsoft.bjtax.shenbao.util.DateUtilPro;
import com.ttsoft.bjtax.shenbao.util.StringUtils;
import com.ttsoft.bjtax.shenbao.wsksb.xmlvo.WsksbVO;
import com.ttsoft.bjtax.shenbao.zhsb.ZhsbMapConstant;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * @author MI_Viewer
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class HjbhjnjsProcessor  implements Processor {
	/**
	 * 实现Processor接口
	 * 
	 * @param vo
	 *            业务参数
	 * @return Object VOPackage型数据
	 * @throws BaseException
	 *             业务异常 1 当传过来的操作类型不对时抛出 2 当调用的业务方法抛出业务异常时向上传递抛出
	 *             其他异常抛出由EJB的process方法处理。
	 */

	public Object process(VOPackage vo) throws BaseException {

		Object result = null;
		if (vo == null) {
			throw new NullPointerException();
		}

		switch (vo.getAction()) {
	
		
		case JmbaActionConstant.INTI_ACTION_QUERY:
			result = doQuery(vo);
			break;
		case JmbaActionConstant.INTI_ACTION_SHOW:
			result = doShow(vo);
			break;
			
		case JmbaActionConstant.INTI_ACTION_SAVE:
			result = doSave(vo);//保存为1
			break;	
		case JmbaActionConstant.INTI_ACTION_DELETE:
			result = doDelete(vo);
			break;	
		case JmbaActionConstant.INTI_ACTION_COMMIT:
			result = doCommit(vo,"3");//提交为2
			break;	
			
		default:
			throw new ApplicationException("用户执行了系统不支持的方法或功能.");
		}

		return result;
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

	private Object doCommit(VOPackage vo,String sqzt) throws BaseException {
//sqzt 保存是1，提交是2		
        DzyjHelper dh = new DzyjHelper();        
		Map hm=(Map)vo.getData();
        JmbaVO bavo = (JmbaVO)hm.get(CAcodeConstants.CA_MAPKEY_VO_XMLVO);  
		DzyjsjVO dzyj = (DzyjsjVO) hm.get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);
		UserData ud = (UserData) vo.getUserData();
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

			
		} catch (Exception ex) {
			throw ExceptionUtil.getBaseException(ex);
		} finally {		
			
		}
		return null;
	}

	/**
	 * doSave保存对象页面信息要素
	 * 
	 * @param vo
	 *            业务参数
	 * @return 数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
	 * @throws BaseException
	 *             当其他情况都会抛出异常信息
	 */

	private Object doSave(VOPackage vo) throws BaseException {
//sqzt 保存是1，提交是2		
		List list=new ArrayList();
        DzyjHelper dh = new DzyjHelper();        
		Map hm=(Map)vo.getData();
        JmbaVO bavo = (JmbaVO)hm.get(CAcodeConstants.CA_MAPKEY_VO_XMLVO);  
		DzyjsjVO dzyj = (DzyjsjVO) hm.get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);
		JmbaZbVO vo1 = (JmbaZbVO)bavo.getJmsbajl().get(0);

		Jmba06VO mxvo=(Jmba06VO)vo1.getQysdsjmba().get(0);
		UserData ud = (UserData)vo.getUserData();
		Connection conn = null;
		PreparedStatement ps = null;
		com.syax.bjtax.shenbao.util.QysdsUtil qysdsUtil = new com.syax.bjtax.shenbao.util.QysdsUtil();
		try {
//电子原件暂不实现
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
			conn = DBResource.getConnection();

			JmbaZbVO vo2=PublicAccess.getJmbaZbVO(conn, vo1.getBasqwsh());
			//新增
			if (vo2.getBasqwsh()==null || "".equals(vo2.getBasqwsh())){
				PublicAccess.saveZb(conn,bavo,ud);
			}
//,SFYHSQKSM='"+mxvo.getSfyhsqksm()+"',QTZL='"+mxvo.getQtzl()+"'
			String sql = "";
			if(mxvo.getXh()!=null && !mxvo.getXh().equals("")){
				sql = "UPDATE sfdb.sf_jl_qysdsjmsba_06 SET JNJSXMLXDM='"+mxvo.getJnjsxmlxdm()+"',BAND='"+vo1.getBand()+"',ZLMC='"+mxvo.getZlmc()+"',DYBSRND='"+mxvo.getDybsrnd()+"',MZQSND='"+mxvo.getMzqsnd()+"',MZZZND='"+mxvo.getMzzznd()+"',JZQSND='"+mxvo.getJzqsnd()+"',JZZZND='"+mxvo.getJzzznd()+"',SHBJ='0',LRR='"+ud.getYhid()+"',LRRQ=sysdate where xh ='"+mxvo.getXh()+"'";
			}else{
				//SFYHSQKSM,QTZL,'"+mxvo.getSfyhsqksm()+"','"+mxvo.getQtzl()+"',
				String xh = qysdsUtil.getSequence();
				sql = "INSERT INTO sfdb.sf_jl_qysdsjmsba_06 (XH,BASQWSH,JNJSXMLXDM,JSJDM,BAND,SWJGZZJGDM,ZLMC,DYBSRND,MZQSND,MZZZND,JZQSND,JZZZND,SHBJ,CJR,CJRQ,LRR,LRRQ) VALUES('"+xh+"','"+vo1.getBasqwsh()+"','"+mxvo.getJnjsxmlxdm()+"',(select b.JSJDM from SFDB.SF_JL_QYSDSJMSBAJL b where B.BASQWSH = '"+vo1.getBasqwsh()+"'),(select b.BAND from SFDB.SF_JL_QYSDSJMSBAJL b where B.BASQWSH = '"+vo1.getBasqwsh()+"'),(select b.SWJGZZJGDM from SFDB.SF_JL_QYSDSJMSBAJL b where B.BASQWSH = '"+vo1.getBasqwsh()+"'),'"+mxvo.getZlmc()+"','"+mxvo.getDybsrnd()+"','"+mxvo.getMzqsnd()+"','"+mxvo.getMzzznd()+"','"+mxvo.getJzqsnd()+"','"+mxvo.getJzzznd()+"','0','"+ud.getYhid()+"',sysdate,'"+ud.getYhid()+"',sysdate)";
			}
			System.out.println("06DetailProcessor===doSave===sql="+sql);
			ps = conn.prepareStatement(sql);
			ps.executeQuery();
			
			
			if (ps != null) {
				ps.close();
			}
		} catch (Exception ex) {
			throw ExceptionUtil.getBaseException(ex);
		} finally {		
			DBResource.destroyConnection(conn);
		}
		return list;
	}

	/**
	 * doDelete删除对象页面信息要素
	 * 
	 * @param vo
	 *            业务参数
	 * @return 数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
	 * @throws BaseException
	 *             当其他情况都会抛出异常信息
	 */

	private Object doDelete(VOPackage vo) throws BaseException {
		
        DzyjHelper dh = new DzyjHelper();        
		Map hm=(Map)vo.getData();
		JmbamxBo bo=(JmbamxBo)hm.get(VOConstants.KEY_JMBA_MX_BO);
        JmbaVO bavo = (JmbaVO)hm.get(CAcodeConstants.CA_MAPKEY_VO_XMLVO);  
		DzyjsjVO dzyj = (DzyjsjVO) hm.get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);
		JmbaZbVO vo1 = (JmbaZbVO)bavo.getJmsbajl().get(0);
		//Jmba01VO mxvo=(Jmba01VO)vo1.getQysdsjmba().get(0);
		UserData ud = (UserData) vo.getUserData();
		Connection conn = null;
		PreparedStatement ps = null;
		try {
//电子原件暂不实现
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
			conn = DBResource.getConnection();	
			String delSQL = "DELETE FROM sfdb.sf_jl_qysdsjmsba_06 t WHERE t.xh = '"+bo.getXh()+"'";
			System.out.println("delSQL==="+delSQL);
			ps = conn.prepareStatement(delSQL);
			ps.executeUpdate();
			if (ps != null) {
				ps.close();
			}

		
		} catch (Exception ex) {			
			throw new ApplicationException("数据库更新记录失败！" + ud.getYhid()	+ ":" + ex.getMessage());
		} finally {		
			DBResource.destroyConnection(conn);
		}
		return null;
	}
	/**
	 * doShow初始化对象页面信息要素
	 * 
	 * @param vo
	 *            业务参数
	 * @return 数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
	 * @throws BaseException
	 *             当其他情况都会抛出异常信息
	 */

	private Object doQuery(VOPackage vo) throws BaseException {
		
		JmbamxBo bo = (JmbamxBo) vo.getData();
		JmbaZbVO vo1=null;
		UserData ud = vo.getUserData();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = DBResource.getConnection();	
			StringBuffer sb=new StringBuffer();
			vo1=PublicAccess.getJmbaZbVO(conn, bo.getBasqwsh());
			//新增
			if (vo1.getBasqwsh()==null || "".equals(vo1.getBasqwsh())){
				vo1=new JmbaZbVO();
				vo1.setBand(bo.getBand());
				vo1.setBasqbh(bo.getBasqbh());
				vo1.setBasqwsh(bo.getBasqwsh());
				vo1.setJmbasxdm(bo.getJmbasxdm());
				vo1.setLrrq(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
				//vo1.setJmbasxmc("");//到action里填充
				return vo1;
			}
			
			Date date = new Date();
			SimpleDateFormat df=new SimpleDateFormat("yyyyMMdd");
			String tjsj = df.format(date);
			
			sb.append(" select ");		
            sb.append("  a.JNJSXMLXDM,(select b.JNJSXMLXMC from dmdb.SF_DM_JNJSXMLX b where a.JNJSXMLXDM=b.JNJSXMLXDM ) JNJSXMLXMC, ");
			//,a.Sfyhsqksm
            sb.append(" a.zlmc zlmc,a.Dybsrnd Dybsrnd,a.xh,");
            //sb.append(" decode(a.Sfyhsqksm,'0','是','1','否','') Sfyhsqksmmc ,a.zcba,a.qtzl qtzl, ");
            sb.append(" a.mzqsnd mzqsnd,a.mzzznd mzzznd,a.jzqsnd jzqsnd,a.jzzznd jzzznd ");
		
			sb.append(" from sfdb.sf_jl_qysdsjmsba_06 a ");
			sb.append(" where 1=1 ");
			//if ("9".equals(bo.getZtdm())){
			if (bo.getBasqwsh()!=null && !bo.getBasqwsh().equals("")){
				sb.append(" and a.basqwsh="+bo.getBasqwsh()+" ");
			}
			//}
			
			if (bo.getXh()!=null && !bo.getXh().equals("")){
				sb.append(" and a.xh="+bo.getXh()+" ");
			}
			
			System.out.println("===query sql=="+sb.toString());
			
			List list=new ArrayList();
			ps = conn.prepareStatement(sb.toString());
			rs = ps.executeQuery();
			int i = 0;
			while (rs.next()) {	
				 Jmba06VO mxvo=new Jmba06VO();
				 i++;
				 mxvo.setHc(i+"");
	             mxvo.setXh( rs.getString("xh"));
//	             mxvo.setWjmc( rs.getString("WJMC"));
//	             mxvo.setWh( rs.getString("WH"));

	             mxvo.setJnjsxmlxdm( rs.getString("JNJSXMLXDM"));
	             mxvo.setJnjsxmlxmc( rs.getString("JNJSXMLXMC"));
	             
	             mxvo.setDybsrnd(rs.getString("Dybsrnd"));//StringUtils.getStrFromDate(
	             
	             mxvo.setZlmc( rs.getString("zlmc"));
	             mxvo.setJzqsnd( rs.getString("Jzqsnd"));
	             mxvo.setJzzznd( rs.getString("Jzzznd"));
	             mxvo.setMzqsnd( rs.getString("Mzqsnd"));
	             mxvo.setMzzznd( rs.getString("Mzzznd"));
	             
//	             mxvo.setZcba( rs.getString("Zcba"));
//	             if ("2".equals(bo.getZtdm())){
//	             	mxvo.setZcba("0");
//	             }else{
//	             	mxvo.setZcba("1");
//	             }
//	             mxvo.setQtzl( rs.getString("Qtzl"));
//	             mxvo.setSfyhsqksm( rs.getString("Sfyhsqksm"));
//	             if (mxvo.getSfyhsqksm().equals("0")){
//	                   mxvo.setSfyhsqksmmc( "是");
//	             }else{
//	             	mxvo.setSfyhsqksmmc( "否");
//	             }
	            
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
			DBResource.destroyConnection(conn);
		}
		return vo1;
	}

	/**
	 * doShow初始化对象页面信息要素
	 * 
	 * @param vo
	 *            业务参数
	 * @return 数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
	 * @throws BaseException
	 *             当其他情况都会抛出异常信息
	 */

	private Object doShow(VOPackage vo) throws BaseException {
		
		JmbamxBo bo = (JmbamxBo) vo.getData();
		JmbamxBo boRes = new JmbamxBo();
		JmbaZbVO vo1=null;
		UserData ud = vo.getUserData();
		Connection conn = null;
		PreparedStatement ps = null;
		
		ResultSet rs = null;
		
		try {
			conn = DBResource.getConnection();	
			StringBuffer sb=new StringBuffer();
			vo1=PublicAccess.getJmbaZbVO(conn, bo.getBasqwsh());
			
			Date date = new Date();
			SimpleDateFormat df=new SimpleDateFormat("yyyyMMdd");
			String tjsj = df.format(date);
			
			String querysql = "BEGIN SFDB.PRO_JMBA_QYSDSJMSBA_06(?,?,?,?); END;";

			CallableStatement cs = conn.prepareCall(querysql); // 调用存储过程

			cs.setString(1, ud.getYhid());
			cs.setString(2, bo.getBasqwsh());
			//cs.setString(3, null);
			cs.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
			
			cs.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);
			cs.execute();
			//String ztdm=cs.getString(4);
			
	
			System.out.println("===query sql=="+sb.toString());
			
			List list=new ArrayList();
			int i = 0;
			//boRes.setZtdm(ztdm);
			//if (ztdm.equals("1")){ 
				rs = (ResultSet) cs.getObject(4);
			
			while (rs.next()) {	
				 Jmba06VO mxvo=new Jmba06VO();
				 i++;
				 mxvo.setHc(i+"");
//		             mxvo.setWjmc( rs.getString("WJMC"));
//		             mxvo.setWh( rs.getString("WH"));
		             //mxvo.setJNJSXMLXMC( );
		             //JNJSXMLXMC在action里设置
		             mxvo.setJnjsxmlxdm( rs.getString("JNJSXMLXDM"));
		             
		             mxvo.setDybsrnd(rs.getString("Dybsrnd"));
		             
		             mxvo.setZlmc( rs.getString("zlmc"));
		             mxvo.setJzqsnd( rs.getString("Jzqsnd"));
		             mxvo.setJzzznd( rs.getString("Jzzznd"));
		             mxvo.setMzqsnd( rs.getString("Mzqsnd"));
		             mxvo.setMzzznd( rs.getString("Mzzznd"));
		             mxvo.setZcba( rs.getString("Zcba"));
		             mxvo.setQtzl( rs.getString("Qtzl"));
		             mxvo.setSfyhsqksm( rs.getString("Sfyhsqksm"));
		             if (mxvo.getSfyhsqksm().equals("0")){
		                   mxvo.setSfyhsqksmmc( "是");
		             }else{
		             	mxvo.setSfyhsqksmmc( "否");
		             }
		             mxvo.setXh( rs.getString("xh"));
	             list.add(mxvo);
			}
			vo1.setQysdsjmba(list);
			
			list=new ArrayList();
			//}else if (ztdm.equals("2")){
				rs = (ResultSet) cs.getObject(3);
				while (rs.next()) {	
					 Jmba06WnVO mxvo=new Jmba06WnVO();
					 i++;
					 mxvo.setHc(i+"");
//			             mxvo.setWjmc( rs.getString("WJMC"));
//			             mxvo.setWh( rs.getString("WH"));
			             //mxvo.setJNJSXMLXMC( );
			             //JNJSXMLXMC在action里设置
			             mxvo.setJnjsxmlxdm( rs.getString("JNJSXMLXDM"));
			             
			             mxvo.setDybsrnd(rs.getString("Dybsrnd"));
			             
			             mxvo.setZlmc( rs.getString("zlmc"));
			             mxvo.setJzqsnd( rs.getString("Jzqsnd"));
			             mxvo.setJzzznd( rs.getString("Jzzznd"));
			             mxvo.setMzqsnd( rs.getString("Mzqsnd"));
			             mxvo.setMzzznd( rs.getString("Mzzznd"));
			             mxvo.setQtzl( rs.getString("Qtzl"));
			             mxvo.setZcba("0");
			             mxvo.setSfyhsqksm( rs.getString("Sfyhsqksm"));
			             
			             if (mxvo.getSfyhsqksm().equals("0")){
			                   mxvo.setSfyhsqksmmc( "是");
			             }else{
			             	mxvo.setSfyhsqksmmc( "否");
			             }
			             mxvo.setXh( rs.getString("xh"));
		             list.add(mxvo);
				}	
				vo1.setWnqysdsjmba(list);
				
			//}else  if (ztdm.equals("3")){//上年数据已过期
				
			//}
			
			boRes.setZbvo(vo1);
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
			DBResource.destroyConnection(conn);
		}
		return boRes;
	}

}
