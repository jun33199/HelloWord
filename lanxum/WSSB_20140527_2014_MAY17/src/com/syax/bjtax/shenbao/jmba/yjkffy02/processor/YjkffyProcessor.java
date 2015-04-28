/*
 * Created on 2010-1-5
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.syax.bjtax.shenbao.jmba.yjkffy02.processor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import com.syax.bjtax.shenbao.jmba.xmlvo.JmbaVO;
import com.syax.bjtax.shenbao.jmba.xmlvo.JmbaZbVO;
import com.syax.bjtax.shenbao.jmba.xmlvo.VOConstants;
import com.syax.common.util.CAcodeConstants;
import com.ttsoft.bjtax.shenbao.model.domain.Wynsksb;
import com.ttsoft.bjtax.shenbao.util.CAUtils;
import com.ttsoft.bjtax.shenbao.util.DBResource;
import com.ttsoft.bjtax.shenbao.util.MoneyUtils;
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
public class YjkffyProcessor  implements Processor {
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

		Jmba02VO mxvo=(Jmba02VO)vo1.getQysdsjmba().get(0);
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

			String sql = "";
			if(mxvo.getXh()!=null && !mxvo.getXh().equals("")){
				//SFYBZRY='"+mxvo.getSfybzry()+"',SFYYFFYQK='"+mxvo.getSfyyffyqk()+"',SFYJYWJ='"+mxvo.getSfyjywj()+"',SFYHTXY='"+mxvo.getSfyhtxy()+"',SFYYJCG='"+mxvo.getSfyyjcg()+"',SFYJHYS='"+mxvo.getSfyjhys()+"',,QTZL='"+mxvo.getQtzl()+"'
				sql ="UPDATE sfdb.sf_jl_qysdsjmsba_02 SET XMMC='"+mxvo.getXmmc()+"',BAND='"+vo1.getBand()+"',DQKCJE='"+mxvo.getDqkcje()+"',WXZCJE='"+mxvo.getWxzcje()+"',JJKCJE='"+mxvo.getJjkcje()+"',SHBJ='0',LRR='"+ud.getYhid()+"',LRRQ=sysdate where xh ='"+mxvo.getXh()+"'";
			}else{
				String xh = qysdsUtil.getSequence();//QTZL,SFYJHYS,SFYBZRY,SFYYFFYQK,SFYJYWJ,SFYHTXY,SFYYJCG,  mxvo.getSfyjhys()+"','"+mxvo.getSfybzry()+"','"+mxvo.getSfyyffyqk()+"','"+mxvo.getSfyjywj()+"','"+mxvo.getSfyhtxy()+"','"+mxvo.getSfyyjcg()+"','"'"+mxvo.getQtzl()+"',
				sql = "INSERT INTO sfdb.sf_jl_qysdsjmsba_02 (XH,BASQWSH,YFFYLYDM,JSJDM,BAND,SWJGZZJGDM,DQKCJE,WXZCJE,JJKCJE,SHBJ,CJR,CJRQ,LRR,LRRQ,XMMC) VALUES('"+xh+"','"+vo1.getBasqwsh()+"','"+mxvo.getYffylydm()+"',(select b.JSJDM from SFDB.SF_JL_QYSDSJMSBAJL b where B.BASQWSH = '"+vo1.getBasqwsh()+"'),(select b.BAND from SFDB.SF_JL_QYSDSJMSBAJL b where B.BASQWSH = '"+vo1.getBasqwsh()+"'),(select b.SWJGZZJGDM from SFDB.SF_JL_QYSDSJMSBAJL b where B.BASQWSH = '"+vo1.getBasqwsh()+"'),'"+mxvo.getDqkcje()+"','"+mxvo.getWxzcje()+"','"+mxvo.getJjkcje()+"','0','"+ud.getYhid()+"',sysdate,'"+ud.getYhid()+"',sysdate,'"+mxvo.getXmmc()+"')";
			}
			System.out.println("02Processor===doSave===sql="+sql);
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			
			
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
			String delSQL = "DELETE FROM sfdb.sf_jl_qysdsjmsba_02 t WHERE t.xh = '"+bo.getXh()+"'";
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
			//,a.sfyyjcg,a.sfyhtxy,a.sfyjhys,a.sfybzry,a.sfyyffyqk,a.sfyjywj,a.qtzl
			sb.append(" select ");		
			sb.append(" a.xh,a.yffylydm,a.xmmc xmmc,");
//			sb.append(" decode(a.sfyjhys,'0','是','1','否','') sfyjhysmc , ");
//            sb.append(" decode(a.sfybzry,'0','是','1','否','') sfybzrymc , ");
//            sb.append(" decode(a.sfyyffyqk,'0','是','1','否','') sfyyffyqkmc , ");
//            sb.append(" decode(a.sfyjywj,'0','是','1','否','') sfyjywjmc , ");
//            sb.append(" decode(a.sfyhtxy,'0','是','1','否','') sfyhtxymc , ");
            //sb.append(" decode(a.sfyyjcg,'0','是','1','否','') sfyyjcgmc , """ +
			sb.append(" (select b.YFFYLYMC from DMDB.SF_DM_YFFYLY b where b.YFFYLYDM=a.YFFYLYDM) YFFYLYMC ,");
			sb.append(" a.dqkcje dqkcje,a.wxzcje wxzcje,a.jjkcje jjkcje  ");
		
			sb.append(" from sfdb.sf_jl_qysdsjmsba_02 a ");
			sb.append(" where a.basqwsh='"+bo.getBasqwsh()+"' ");
			
			
			if (bo.getXh()!=null && !bo.getXh().equals("")){
				sb.append(" and a.xh="+bo.getXh()+" ");
			}
			
			System.out.println("===query sql=="+sb.toString());
			
			List list=new ArrayList();
			ps = conn.prepareStatement(sb.toString());
			rs = ps.executeQuery();
			int i = 0;
			while (rs.next()) {	
				 Jmba02VO mxvo=new Jmba02VO();
				 i++;
				 mxvo.setHc(i+"");
	             mxvo.setXh( rs.getString("xh"));
	             mxvo.setYffylydm( rs.getString("yffylydm"));
	             mxvo.setYffylymc( rs.getString("yffylymc"));
		             
		             mxvo.setDqkcje( MoneyUtils.format(rs.getString("dqkcje")));
		             mxvo.setJjkcje( MoneyUtils.format(rs.getString("jjkcje")));
		             //mxvo.setQtzl( rs.getString("qtzl"));
		             mxvo.setXmmc(rs.getString("xmmc"));
		             mxvo.setWxzcje( MoneyUtils.format(rs.getString("wxzcje")));
//		             mxvo.setSfybzry( rs.getString("sfybzry"));
//		             mxvo.setSfyhtxy( rs.getString("sfyhtxy"));
//		             mxvo.setSfyjhys( rs.getString("sfyjhys"));
//		             mxvo.setSfyjywj( rs.getString("sfyjywj"));
//		             mxvo.setSfyyffyqk( rs.getString("sfyyffyqk"));
//		             mxvo.setSfyyjcg( rs.getString("sfyyjcg"));
//		             mxvo.setSfybzrymc( rs.getString("sfybzrymc"));
//		             mxvo.setSfyhtxymc( rs.getString("sfyhtxymc"));
//		             mxvo.setSfyjhysmc( rs.getString("sfyjhysmc"));
//		             mxvo.setSfyjywjmc( rs.getString("sfyjywjmc"));
//		             mxvo.setSfyyffyqkmc( rs.getString("sfyyffyqkmc"));
//		             mxvo.setSfyyjcgmc( rs.getString("sfyyjcgmc"));
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

}
