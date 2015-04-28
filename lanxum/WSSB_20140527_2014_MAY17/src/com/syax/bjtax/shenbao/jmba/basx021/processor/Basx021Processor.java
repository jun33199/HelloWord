package com.syax.bjtax.shenbao.jmba.basx021.processor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.syax.bjtax.shenbao.jmba.dao.PublicAccess;
import com.syax.bjtax.shenbao.jmba.jmbaz.JmbaActionConstant;
import com.syax.bjtax.shenbao.jmba.jmbaz.JmbamxBo;
import com.syax.bjtax.shenbao.jmba.xmlvo.Jmba21VO;
import com.syax.bjtax.shenbao.jmba.xmlvo.JmbaVO;
import com.syax.bjtax.shenbao.jmba.xmlvo.JmbaZbVO;
import com.syax.common.util.CAcodeConstants;
import com.ttsoft.bjtax.shenbao.util.DBResource;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

public class Basx021Processor implements Processor 
{
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
			result = doSave(vo); // 保存为1
			break;
//		case JmbaActionConstant.INTI_ACTION_DELETE:
//			result = doDelete(vo);
//			break;
//		case JmbaActionConstant.INTI_ACTION_COMMIT:
//			// result = doCommit(vo, "3"); // 提交为2
//			break;

		default:
			throw new ApplicationException("用户执行了系统不支持的方法或功能.");
		}
		return result;
	}
	
	/**
	 * 网上申报时新增页面或查询时调用此方法以获取初始化参数
	 *
	 * @param vo    业务参数
	 * @return 数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
	 * @throws BaseException   当其他情况都会抛出异常信息
	 */
	private Object doQuery(VOPackage vo) throws BaseException {

		JmbamxBo bo = (JmbamxBo) vo.getData();			//传递进来的数据
		JmbaZbVO voZb = null;							//备案记录的主表信息
		
		UserData ud = vo.getUserData();					//用户信息
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
				/*初始化*/
				//当前时间
				Date date = new Date();
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				String tjsj = df.format(date);
				
				conn = DBResource.getConnection();
				StringBuffer sb = new StringBuffer();			//要查询的sql
				List list = new ArrayList();
				
				//获取主表记录
				voZb = PublicAccess.getJmbaZbVO(conn, bo.getBasqwsh());
							
				/*业务逻辑*/
				// 如果主表没有记录信息则新建
				if (voZb.getBasqwsh() == null || "".equals(voZb.getBasqwsh())) 
				{
					voZb = new JmbaZbVO();
					voZb.setBand(bo.getBand());
					voZb.setBasqbh(bo.getBasqbh());
					voZb.setBasqwsh(bo.getBasqwsh());
					voZb.setJmbasxdm(bo.getJmbasxdm());
					voZb.setLrrq(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
					
					Jmba21VO mx21vo = new Jmba21VO();										//明细信息的初始化
					mx21vo.setMzqsnd(voZb.getBand());	//默认的起始免增年度为备案年度前一年
					list.add(mx21vo);
					voZb.setQysdsjmba(list);
					return voZb;
				}
				
				//否则执行该sql,从详细记录表查找记录信息
				sb.append("select " );
				sb.append("	        t.shbj ,t.xh ,t.mzqsnd ,t.mzzznd ");
				sb.append("from     sfdb.sf_jl_qysdsjmsba_21 t      ") ;
				sb.append("where   	");
				sb.append("       t.basqwsh='"+bo.getBasqwsh()+"' ");
				if (bo.getXh()!=null && !"".equals(bo.getXh().trim()))
				{
					sb.append(" and a.xh="+bo.getXh()+" ");
				}
				
				//添加查询结果
				ps = conn.prepareStatement(sb.toString());
				rs = ps.executeQuery();
				while (rs.next())
				{
					Jmba21VO mx21vo = new Jmba21VO();
					mx21vo.setShbj(rs.getString("shbj"));
					mx21vo.setMzqsnd(rs.getString("mzqsnd"));
					mx21vo.setMzzznd(rs.getString("mzzznd"));
					mx21vo.setXh(rs.getString("xh"));
					list.add(mx21vo);
				}
				voZb.setQysdsjmba(list);
				
				
				if (rs != null) {
	                rs.close();
	            }
	            if (ps != null) {
	                ps.close();
	            }
			}catch (Exception ex) {
				throw ExceptionUtil.getBaseException(ex);
			} finally {		
				DBResource.destroyConnection(conn);
			}
			  return voZb;
	}
	
	/**
	 * doSave保存对象页面信息要素
	 * 
	 * @param vo       业务参数
	 * @return 数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
	 * @throws BaseException        当其他情况都会抛出异常信息
	 */
	private Object doSave(VOPackage vo) throws BaseException {
		
		List list=new ArrayList();
        
        //获取传入的信息
		Map hm=(Map)vo.getData();
        JmbaVO bavo = (JmbaVO)hm.get(CAcodeConstants.CA_MAPKEY_VO_XMLVO); 		//备案信息表 
		JmbaZbVO zbvo = (JmbaZbVO)bavo.getJmsbajl().get(0);						//用于存储在记录主表的信息
		Jmba21VO mxvo = (Jmba21VO)zbvo.getQysdsjmba().get(0);					//用于存储在记录明细表中的信息
		UserData ud = (UserData)vo.getUserData();								//用户信息
	
		//初始化
		if(!mxvo.getMzzznd().equals("2015"))									//当前要求免征终止年度必须为2015
		{mxvo.setMzzznd("2015");}
		
		Connection conn = null;
		PreparedStatement ps = null;
		com.syax.bjtax.shenbao.util.QysdsUtil qysdsUtil = new com.syax.bjtax.shenbao.util.QysdsUtil();
		
		try {
			conn = DBResource.getConnection();
			
			//根据传入的备案申请文书号查看存在的记录：该记录用于判断是新增还是更新
			JmbaZbVO zbvo2=PublicAccess.getJmbaZbVO(conn, zbvo.getBasqwsh());
			
			//如果查得的记录不存在就在记录主表新增记录
			if (zbvo2.getBasqwsh()==null || "".equals(zbvo2.getBasqwsh())){
				PublicAccess.saveZb(conn,bavo,ud);		//在备案记录主表新增一条记录
			}

			//对明细记录表的操作
			StringBuffer sql = new StringBuffer();
			
			//明细表中如果存在记录，就更新记录
			if(mxvo.getXh()!=null && !"".equals(mxvo.getXh().trim())){
				
				sql.append("UPDATE    sfdb.sf_jl_qysdsjmsba_21  ");
				sql.append("SET 	     ");
				sql.append("   MZQSND='"+mxvo.getMzqsnd()+"'");
				sql.append("  ,MZZZND='"+mxvo.getMzzznd()+"'");
				sql.append("  ,BAND= '"+zbvo.getBand()+"'");
				sql.append("  ,SHBJ= '0' ");
				sql.append("  ,LRR='"+ud.getYhid()+"'");
				sql.append("  ,LRRQ=sysdate ");
				sql.append("where  xh='"+mxvo.getXh()+"'");
				
			}else{
				
				//不存在就在明细记录表中新增
				String xh = qysdsUtil.getSequence();
				sql.append("INSERT INTO     sfdb.sf_jl_qysdsjmsba_21");
				sql.append("(XH ,BASQWSH ,JSJDM ,BAND ,SWJGZZJGDM ,MZQSND ,MZZZND ,SHBJ ,CJR ,CJRQ ,LRR ,LRRQ) "); 
				sql.append("VALUES  (");
				sql.append("    '"+xh+"'");
				sql.append("   ,'"+zbvo.getBasqwsh()+"'");
				sql.append("   ,(select  b.JSJDM      from SFDB.SF_JL_QYSDSJMSBAJL b where b.BASQWSH = '"+zbvo.getBasqwsh()+"')");
				sql.append("   ,(select  b.BAND       from SFDB.SF_JL_QYSDSJMSBAJL b where b.BASQWSH = '"+zbvo.getBasqwsh()+"')");
				sql.append("   ,(select  b.SWJGZZJGDM from SFDB.SF_JL_QYSDSJMSBAJL b where b.BASQWSH = '"+zbvo.getBasqwsh()+"')");
				sql.append("   ,'"+mxvo.getMzqsnd()+"'");
				sql.append("   ,'"+mxvo.getMzzznd()+"'");
				sql.append("   ,'0'");
				sql.append("   ,'"+ud.getYhid()+"'");
				sql.append("   ,sysdate");
				sql.append("   ,'"+ud.getYhid()+"'");
				sql.append("   ,sysdate)");
				
			}
			ps = conn.prepareStatement(sql.toString());
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
}
