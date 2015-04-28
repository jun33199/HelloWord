package com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.his.processor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.qysdsjmsbagl.vo.DmVo;
import com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.his.web.QyqssdsHisglForm;
import com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdssbgl.web.QyqssdsSbglForm;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

public class QyqssdsHisglProcessor implements Processor {
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
		case CodeConstant.SMSB_SHOWACTION:
			result = doShow(vo);
			break;
		case CodeConstant.SMSB_QUERYACTION:
			result = doQuery(vo);
			break;
		default:
			throw new ApplicationException("用户执行了系统不支持的方法或功能.");
		}

		return result;
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

		QyqssdsHisglForm form = (QyqssdsHisglForm) vo.getData();
		UserData ud = vo.getUserData();
		Connection conn = null;

		PreparedStatement ps = null;
		ResultSet rs = null;
		List list = new ArrayList();
		try {
			conn = SfDBResource.getConnection();
			
			list = new ArrayList();
			String sql = "select swjgzzjgdm,swjgzzjgmc from dmdb.gy_dm_swjgzzjg where zxbs='0' ";
			String ssdwdm = ud.getSsdwdm();
			String yhjb = ud.getYhjb();

			if (yhjb.equals("50")) {
				sql += " and ccbs='1' ";
			}
			if (yhjb.equals("40")) {
				sql += " and ccbs='2'  and jgznlx='1'  and swjgzzjgdm like '"
						+ ssdwdm.substring(0, 2) + "%'";
			}
			if (yhjb.equals("30")) {
				sql += " and swjgzzjgdm ='" + ssdwdm + "'";
			}
			sql += " order by swjgzzjgdm";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				String swjgzzjgdm = rs.getString("SWJGZZJGDM");
				String swjgzzjgmc = rs.getString("SWJGZZJGMC");
				DmVo dmvo = new DmVo();
				dmvo.setDm(swjgzzjgdm);
				dmvo.setMc(swjgzzjgmc);
				list.add(dmvo);
			}
			form.setFilter_zgswjgList(list);
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
		} catch (Exception ex) {
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			SfDBResource.freeConnection(conn);
		}

		System.out.println(form.getFilter_zgswjgList().size()+"--------------历史表Processor-list");
		return form;
	}
	
	/**
	 * doQuery 用于返回页面索要查询的详尽信息
	 * 
	 * @param vo
	 *            业务参数
	 * @return 数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
	 * @throws BaseException
	 *             当其他情况都会抛出异常信息
	 * 
	 */

	private Object doQuery(VOPackage vo) throws BaseException {
		
		QyqssdsHisglForm qyqssdsHisglForm = (QyqssdsHisglForm) vo.getData();
		UserData ud=vo.getUserData();
		String ssdwdm=ud.getSsdwdm();		
		String yhjb=ud.getYhjb();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List list = new ArrayList();

		try {
			conn = SfDBResource.getConnection();				
			StringBuffer sb = new StringBuffer();
			sb.append("select a.nsrjsjdm,a.nsrmc,d.swjgzzjgmc,TO_CHAR(a.QSBAKSRQ, 'YYYY') qsband,  ");
			sb.append(" decode(a.sqlxdm, '0', '网上申请', '1', '上门申请') sqlx, ");
			sb.append("  decode(a.bashztbs, '5', '删除', '6', '作废') bashztbs,a.lrr ");
			sb.append("  from SBDB.SB_JL_QYQSSDSBA_NSRJBXXB_his a, ");
			sb.append("  djdb.dj_jl_jbsj  b, ");
			sb.append("  aqdb.zk_jl_wsyh  c, ");
			sb.append("  dmdb.gy_dm_swjgzzjg d ");
			sb.append("  where a.nsrjsjdm = b.jsjdm ");
			sb.append("  and a.nsrjsjdm = c.yhid ");
			sb.append("  and a.swjgzzjgdm = d.swjgzzjgdm ");
			sb.append("  and (a.bashztbs = '5' or a.bashztbs = '6') ");
			//计算机代码
			String jsjdm=qyqssdsHisglForm.getFilter_jsjdm();
			//纳税人名称
			String nsrmc=qyqssdsHisglForm.getFilter_nsrmc();
			//清算备案年度
			String band=qyqssdsHisglForm.getFilter_band();
			//申请类型
			String sqlx=qyqssdsHisglForm.getFilter_sqlx();
			//主管税务机关
			String zgswjg=qyqssdsHisglForm.getFilter_zgswjg();
			
			if(jsjdm != null && jsjdm.trim().length()>0){
	        	sb.append(" and a.nsrjsjdm='"+jsjdm+"' ");        	
	        }
			if(nsrmc != null && nsrmc.trim().length()>0){
				sb.append(" and b.nsrmc like '%"+nsrmc+"%' ");    	
	        }
			if(band != null && band.trim().length()>0){
				sb.append(" and TO_CHAR(a.QSBAKSRQ,'YYYY')='"+band+"' "); 	
	        }
			if(sqlx != null && sqlx.trim().length()>0){
				sb.append(" and a.sqlxdm='"+sqlx+"' ");
	        }
			
			if(yhjb.equals("50")){
				if(zgswjg != null && zgswjg.trim().length()>0)
				sb.append(" and a.swjgzzjgdm like '"+zgswjg.substring(0,2)+"%' ");	
	        }
			if(yhjb.equals("40")){
				
				if(zgswjg != null && zgswjg.trim().length()>0)
					sb.append(" and a.swjgzzjgdm = '"+zgswjg+"' ");	
				else
					sb.append(" and a.swjgzzjgdm like '"+ssdwdm.substring(0,2)+"%' ");	
			}
			if(yhjb.equals("30")){
				sb.append(" and a.swjgzzjgdm = '"+ssdwdm+"' ");
			}
			
			
			
			System.out.println(sb.toString());
			
			ps = conn.prepareStatement(sb.toString());
			rs = ps.executeQuery();
			 
	            while (rs.next()) {
	                Map map = new HashMap();
	                map.put("COL_1", rs.getString("NSRJSJDM"));
	                map.put("COL_2", rs.getString("NSRMC"));
	                map.put("COL_3", rs.getString("SWJGZZJGMC"));
	                map.put("COL_4", rs.getString("qsband"));
	                map.put("COL_5", rs.getString("SQLX"));
	                map.put("COL_6", rs.getString("bashztbs"));
	                map.put("COL_7", rs.getString("lrr"));
	                list.add(map);
	            }
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
		} catch (Exception ex) {
			// 抛出异常
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {		
			SfDBResource.freeConnection(conn);
		}
		return list;
	}
	
}
