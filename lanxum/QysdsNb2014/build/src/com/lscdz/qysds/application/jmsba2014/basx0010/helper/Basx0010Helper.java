package com.lscdz.qysds.application.jmsba2014.basx0010.helper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import yangjian.frame.util.FrameException;
import yangjian.frame.util.ResourceManager;

import com.lscdz.hlwdsj.hxzgframe.Yh;


import com.lscdz.qysds.application.jmsba2014.basx0010.vo.inner.Basx0010Vo;
import com.lscdz.qysds.application.jmsba2014.util.QysdsJmsbaUtil;
import com.lscdz.qysds.common.util.QysdsHelperUtil;


public class Basx0010Helper {

	
	/**
	 * doQuery查询对象页面信息要素
	 * 
	 * @param vo
	 *            业务参数
	 * @return 数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
	 * @throws BaseException
	 *             当其他情况都会抛出异常信息
	 */

//	public Basx0010Vo doQuery(Basx0010Req reqVo) throws FrameException{
//		
//		Connection conn = null;
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		Basx0010Vo  basx01Vo=new Basx0010Vo();
//		try {
//			conn = ResourceManager.getConnection();		
//			String sql = "SELECT T.WJMC,T.WH,T.ZYZHLYZLDM,B.ZYZHLYZLMC,T.ZSBH,T.ZSYXKSRQ,T.ZSYXJZRQ,T.QDSR,T.JJSR," +
//					" S.QSRQ,S.JZRQ,S.BAJMBL,S.BAJMSE,S.FHWJMC,S.JSJDM,S.BASQBH,S.BAND,S.CJR cjr,to_char(S.CJRQ,'yyyymmdd') cjrq,s.zfsm " +
//					" FROM sfdb.sf_jl_qysdsjmsba_01 T,sfdb.sf_jl_qysdsjmsbajl S,DMDB.SF_DM_ZYZHLYZL B " +
//					" WHERE T.BASQWSH = S.BASQWSH AND T.ZYZHLYZLDM = B.ZYZHLYZLDM AND T.BASQWSH = '"+reqVo.getBasqwsh()+"'";
//			
//			ps = conn.prepareStatement(sql);
//			rs = ps.executeQuery();
//			while(rs.next()){
//				basx01Vo.setWjmc(rs.getString("WJMC"));
//				basx01Vo.setWh(rs.getString("WH"));
//				basx01Vo.setZyzhlyzldm(rs.getString("ZYZHLYZLDM"));
//				basx01Vo.setZyzhlyzlmc(rs.getString("ZYZHLYZLMC"));
//				basx01Vo.setZsbh(rs.getString("ZSBH"));
//				basx01Vo.setJmszczxqk(rs.getString("FHWJMC"));
//				basx01Vo.setJsjdm(rs.getString("JSJDM"));
//				basx01Vo.setBasqbh(rs.getString("BASQBH"));
//				basx01Vo.setBajmbl(rs.getString("BAJMBL"));
//				basx01Vo.setBajmse(rs.getString("BAJMSE"));
//				basx01Vo.setMr_band(rs.getString("BAND"));
//				basx01Vo.setMr_lrr(rs.getString("CJR"));
//				basx01Vo.setMr_lrrq(rs.getString("CJRQ"));
//				basx01Vo.setZfsm(rs.getString("ZFSM"));
//				//处理数字如果为.123则改成0.123
//				if(rs.getString("QDSR").indexOf(".")==0){
//					basx01Vo.setQdsr("0"+rs.getString("QDSR"));
//				}else{
//					basx01Vo.setQdsr(rs.getString("QDSR"));
//				}
//				if(rs.getString("JJSR").indexOf(".")==0){
//					basx01Vo.setJjsr("0"+rs.getString("JJSR"));
//				}else{
//					basx01Vo.setJjsr(rs.getString("JJSR"));
//				}
//				//对日期进行处理，只取2001-01-01
//				if(!"".equals(rs.getString("ZSYXKSRQ"))){
//					basx01Vo.setZsyxksrq(rs.getString("ZSYXKSRQ").substring(0, 10));
//				}else{
//					basx01Vo.setZsyxksrq(rs.getString("ZSYXKSRQ"));
//				}
//				if(!"".equals(rs.getString("ZSYXJZRQ"))){
//					basx01Vo.setZsyxjzrq(rs.getString("ZSYXJZRQ").substring(0, 10));
//				}else{
//					basx01Vo.setZsyxjzrq(rs.getString("ZSYXJZRQ"));
//				}
//				if(!"".equals(rs.getString("QSRQ"))){
//					basx01Vo.setQsrq(rs.getString("QSRQ").substring(0, 10));
//				}else{
//					basx01Vo.setQsrq(rs.getString("QSRQ"));
//				}
//				if(!"".equals(rs.getString("JZRQ"))){
//					basx01Vo.setJzrq(rs.getString("JZRQ").substring(0, 10));
//				}else{
//					basx01Vo.setJzrq(rs.getString("JZRQ"));
//				}
//				
//			}
//			//设置处理标示为保存状态
//			basx01Vo.setClbs("1");
////			basx01Vo = (Basx01Form)doShow(basx01Vo);
//			
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			throw new FrameException("数据库查询记录失败！" + basx01Vo.getJsjdm()	+ ":" + ex.getMessage());
//		} finally {		
//			QysdsHelper.dbResClose(ps,rs,conn);
//		}
//		return basx01Vo;
//	}
	
	
	/**
	 * doSave保存对象页面信息要素
	 * 
	 * @param vo
	 *            业务参数
	 * @return 数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
	 * @throws BaseException
	 *             当其他情况都会抛出异常信息
	 */

//	private Object doSave(Basx0010Req reqVo,Yh ud) throws FrameException {
//		Connection conn = null;
//		PreparedStatement ps = null;
//		PreparedStatement ps1 = null;
//		PreparedStatement ps2 = null;
//		PreparedStatement ps3 = null;
//		QysdsUtil qysdsUtil = new QysdsUtil();
//		//备案申请文书号
//		String basqwsh = reqVo.getBasqwsh();
//		//备案申请编号
//		String basqbh = reqVo.getBasqbh();
//		//计算机代码
//		String jsjdm = reqVo.getJsjdm();
//		//备案年度
//		String band = reqVo.getBand();
//		//减免税类别代码
//		String jmbasxdm = "0010";
//		//减免税政策执行情况
//		String jmszczxqk=QysdsUtil.strNotNull(reqVo.getJmszczxqk())?reqVo.getJmszczxqk():"";
//		//起始时间
//		String qsrq=QysdsUtil.strNotNull(reqVo.getQsrq())?"to_date('"+reqVo.getQsrq()+"','yyyy-mm-dd')":"null";
//		//截止时间
//		String jzrq=QysdsUtil.strNotNull(reqVo.getJzrq())?"to_date('"+reqVo.getJzrq()+"','yyyy-mm-dd')":"null";
//		//设置减免税额
//		String bajmse = QysdsUtil.strNotNull(reqVo.getBajmse())?reqVo.getBajmse():"";
//		String bajmbl = QysdsUtil.strNotNull(reqVo.getBajmbl())?reqVo.getBajmbl():"";
//		Timestamp time = new Timestamp(new Date().getTime());
//		try {
//			conn = ResourceManager.getConnection();	
//			String sql = "";
//			String zbsql = "";
//			if("1".equals(reqVo.getClbs())){
//				//更新主表
//				sql = "update SFDB.SF_JL_QYSDSJMSBAJL set bajmse='"+bajmse+"',bajmbl='"+bajmbl+"',fhwjmc=?,qsrq="+qsrq+
//				",jzrq="+jzrq+",lrr='"+ud.getYhid()+"',lrrq=to_timestamp('"+time+"', 'yyyy-mm-dd hh24:mi:ss.ff') " +
//				"where basqwsh='"+reqVo.getBasqwsh()+"'";
//				//更新子表
//				zbsql = "UPDATE sfdb.sf_jl_qysdsjmsba_01 SET ZYZHLYZLDM ='"+reqVo.getZyzhlyzldm()+
//				"',WJMC=?,WH=?,ZSBH=?,ZSYXKSRQ=to_date('"+reqVo.getZsyxksrq()+"', 'yyyy-mm-dd'),"+
//				"ZSYXJZRQ=to_date('"+reqVo.getZsyxjzrq()+"', 'yyyy-mm-dd'),"+
//				"SFTJSM='"+reqVo.getSftjsm()+"',QDSR='"+reqVo.getQdsr()+
//				"',JJSR='"+reqVo.getJjsr()+"',SHBJ='"+"0"+
//				"',LRR='"+ud.getYhid()+"',LRRQ=sysdate where basqwsh ='"+reqVo.getBasqwsh()+"'";
//			}else{
//				//插入主表
//				sql = " insert into SFDB.SF_JL_QYSDSJMSBAJL(BASQWSH,BASQBH,JSJDM,BAND,JMBASXDM,SZDM,SWJGZZJGDM,SQZT,TJR,TJSJ,SHR,SHSJ," +
//						"SQLXDM,BAJMSE,BAJMBL,FHWJMC,QSRQ,JZRQ,CJR,CJRQ,LRR,LRRQ)values('"+basqwsh+"','"+basqbh+"','"+jsjdm+"','"+band+"','"+
//						jmbasxdm+"','30','"+ud.getSsdwdm()+"','4','"+ud.getYhid()+"',sysdate,'"+ud.getYhid()+"',sysdate,'1','"+bajmse+
//						"','"+bajmbl+"',?,"+qsrq+","+jzrq+",'"+ud.getYhid()+"',sysdate,'"+ud.getYhid()+
//						"',to_timestamp('"+time+"','yyyy-mm-dd hh24:mi:ss.ff'))";
//				
//				//获取数据库表序号
//				reqVo.setXh(qysdsUtil.getSequence(conn));
//				//插入子表
//				zbsql = "INSERT INTO sfdb.sf_jl_qysdsjmsba_01 (XH,BASQWSH,ZYZHLYZLDM,WJMC,JSJDM,BAND," +
//				"SWJGZZJGDM,WH,ZSBH,ZSYXKSRQ,ZSYXJZRQ,SFTJSM,QDSR,JJSR,SHBJ,CJR,CJRQ,LRR,LRRQ) VALUES('"+reqVo.getXh()+"','" +
//				basqwsh+"','"+reqVo.getZyzhlyzldm()+"',?,'"+jsjdm+"','"+band+"'"+
//				",(select t.swjgzzjgdm from djdb.dj_jl_jbsj t WHERE t.jsjdm = '"+jsjdm+"'),?,?,to_date('"+reqVo.getZsyxksrq()+"', 'yyyy-MM-dd'),"+
//				"to_date('"+reqVo.getZsyxjzrq()+"', 'yyyy-MM-dd'),'"+reqVo.getSftjsm()+"','"+reqVo.getQdsr()+"','"+
//				reqVo.getJjsr()+"','0','"+ud.getYhid()+"',sysdate,'"+ud.getYhid()+"',to_timestamp('"+time+"','yyyy-mm-dd hh24:mi:ss.ff'))";
//			}
//			//主表操作
//
//			ps = conn.prepareStatement(sql);
//			ps.setString(1,jmszczxqk);
//			ps.executeQuery();
//			
//			//子表操作
//
//			ps1 = conn.prepareStatement(zbsql);
//			ps1.setString(1,reqVo.getWjmc());
//			ps1.setString(2,reqVo.getWh());
//			ps1.setString(3,reqVo.getZsbh());
//			ps1.executeQuery();
//			
//			//更新资料清单表
//			if(!"".equals(reqVo.getZl())){
//				//删除该备案申请文书号的资料清单表
//				String delzlqd = "DELETE SFDB.SF_JL_QYSDSJMSBAJLZLQD WHERE BASQWSH = '"+basqwsh+"'";
//
//				ps2=conn.prepareStatement(delzlqd);
//				ps2.execute();
//				//插入该备案申请文书号的资料清单
//				String zlqd = "INSERT INTO SFDB.SF_JL_QYSDSJMSBAJLZLQD(XH,BASQWSH,ZLQD,SWJGZZJGDM,CJR,CJRQ,LRR,LRRQ,SFSHTG,SFKYSC)" +
//						"VALUES(?,?,?,(select t.swjgzzjgdm from djdb.dj_jl_jbsj t WHERE t.jsjdm = ?),?,sysdate,?," +
//						"to_timestamp('"+time+"','yyyy-mm-dd hh24:mi:ss.ff'),?,?)";
//				String[] zl = reqVo.getZl().split(";");
//				ps3=conn.prepareStatement(zlqd);			
//				for(int i=0;i<zl.length;i++){
//					String zlnr = zl[i].substring(0,zl[i].indexOf("|"));
//					String zlsfkysc = zl[i].substring(zl[i].length()-1,zl[i].length());
//
//					String xh = qysdsUtil.getSequence(conn);
//					ps3.setString(1, xh);
//					ps3.setString(2, basqwsh);
//					ps3.setString(3, zlnr);
//					ps3.setString(4, jsjdm);
//					ps3.setString(5, ud.getYhid());
//					ps3.setString(6, ud.getYhid());
//					ps3.setString(7, "1");
//					ps3.setString(8, zlsfkysc);
//					ps3.addBatch();
//				}
//				ps3.executeBatch();
//			}
//			//设置处理标示为保存状态
//			reqVo.setClbs("1");
//			
////			reqVo = (reqVo)doShow(vo);
//			
//			if (ps != null) {
//				ps.close();
//			}
//			if (ps1 != null) {
//				ps1.close();
//			}
//			if (ps2 != null) {
//				ps2.close();
//			}
//			if (ps3 != null) {
//				ps3.close();
//			}
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			throw new FrameException("数据库保存记录失败！" + reqVo.getJsjdm()	+ ":" + ex.getMessage());
//		} finally {		
//			QysdsHelper.dbResClose(ps,null,null);
//			QysdsHelper.dbResClose(ps1,null,null);
//			QysdsHelper.dbResClose(ps2,null,null);		
//			QysdsHelper.dbResClose(ps3,null,conn);
//		}
//		return reqVo;
//	}
}
