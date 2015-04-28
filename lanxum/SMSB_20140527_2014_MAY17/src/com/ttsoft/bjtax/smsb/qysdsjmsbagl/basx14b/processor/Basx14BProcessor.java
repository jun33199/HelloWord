package com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx14b.processor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx01.web.Basx01Form;
import com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx13b.web.Basx13BForm;
import com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx14b.web.Basx14BForm;
import com.ttsoft.bjtax.smsb.qysdsjmsbagl.util.QysdsUtil;
import com.ttsoft.bjtax.smsb.qysdsjmsbagl.vo.DmVo;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

public class Basx14BProcessor implements Processor{
	
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
		
		switch (vo.getAction()) {
		case CodeConstant.SMSB_SHOWACTION:
			result = doShow(vo);
			break;
		case CodeConstant.SMSB_SAVEACTION:
			result = doSave(vo);
			break;
		case CodeConstant.SMSB_BGACTION:
			result = doBg(vo);
			break;	
		case CodeConstant.SMSB_QUERYACTION:
			result = doQuery(vo);
			break;
		case CodeConstant.SMSB_QUERYACTION1:
			result = doBasqbhSeach(vo);
			break;
		case CodeConstant.SMSB_UPDATEACTION:
			result = doCheck(vo);
			break;
		case CodeConstant.SMSB_PRINTACTION:
			result = doPrint(vo);
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
		
		Basx14BForm basx14BForm = (Basx14BForm) vo.getData();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		PreparedStatement ps1 = null;
		ResultSet rs1 = null;
		PreparedStatement ps2 = null;
		ResultSet rs2 = null;
		List list=new ArrayList();
		try {
			//为获取纳税人基本信息准备
			//获得UserData
			UserData ud = (UserData)vo.getUserData();
			//获得纳税人基本信息
			HashMap djMap = InterfaceDj.getDjInfo(basx14BForm.getJsjdm(), ud);
			SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");
			System.out.println("=====getSwjgzzjgdm======>>>>" + jbsj.getSwjgzzjgdm());
			
			conn = SfDBResource.getConnection();	
			String sql = "SELECT zysblxdm,LPAD('  ',2*(LEVEL - 1)) || zysblxmc zysblxmc, level " +
						"FROM dmdb.sf_dm_zysblx START WITH fjddm IS NULL CONNECT BY PRIOR zysblxdm = fjddm";
			
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {	
				String dm=rs.getString("zysblxdm");
				String mc=rs.getString("zysblxmc");
				String level=rs.getString("level");
				DmVo dmvo=new DmVo();
				dmvo.setDm(dm);
				dmvo.setMc(mc);
				dmvo.setLevel(level);
				list.add(dmvo);
			}
			basx14BForm.setZysblxList(list);
			
			StringBuffer sb=new StringBuffer();
			sb.append(" select a.jsjdm,a.nsrmc nsrmc,");
			sb.append(" (select b.swjgzzjgmc from dmdb.gy_dm_swjgzzjg b where b.swjgzzjgdm=a.swjgzzjgdm) zgsws,");
			sb.append(" (select b.djzclxmc from dmdb.dj_dm_djzclx b where b.djzclxdm=a.djzclxdm) jjlx,");
			sb.append(" (select b.gjbzhymc from dmdb.gy_dm_gjbzhy b where b.gjbzhydm=a.gjbzhydm) sshy,");
			sb.append(" (select b.xm from djdb.dj_jl_qyry b where a.jsjdm=b.jsjdm and b.zwdm='05' and rownum=1)  lxr,");
			sb.append(" (select  case when b.gddh is null and b.yddh is not null then b.yddh");
			sb.append(" when b.gddh is not null and b.yddh is null then b.gddh");
			sb.append(" when b.gddh is not null and b.yddh is not null then b.gddh||'  '||b.yddh else null");
			sb.append(" end  from djdb.dj_jl_qyry b where a.jsjdm=b.jsjdm and b.zwdm='05' and rownum=1) lxdh");
			sb.append(" from djdb.dj_jl_jbsj a where a.jsjdm='"+basx14BForm.getJsjdm()+"'");
			
			
			ps1 = conn.prepareStatement(sb.toString());
			rs1 = ps1.executeQuery();
			while (rs1.next()){
				basx14BForm.setJsjdm(rs1.getString("JSJDM"));
				basx14BForm.setNsrmc(rs1.getString("NSRMC"));
				basx14BForm.setZgsws(rs1.getString("ZGSWS"));
				basx14BForm.setJjlx(rs1.getString("JJLX"));
				basx14BForm.setSshy(rs1.getString("SSHY"));
				basx14BForm.setLxr(rs1.getString("LXR"));
				basx14BForm.setLxdh(rs1.getString("LXDH"));
			}
			String zl = "";
			String zlsql = "";
			//判断是否已经保存过
			if("0".equals(basx14BForm.getClbs())){
				//zlsql = "select t.zlqdmc,t.sfkysc from dmdb.sf_dm_bazlqddm t WHERE t.jmbasxdm = '014B'  and t.zfbs = '0' ORDER BY T.zlqddm";
				zlsql = "select t.zlqdmc,t.sfkysc,t.zlqddm from dmdb.sf_dm_bazlqddm t WHERE t.jmbasxdm = '014B'  and t.zfbs = '0' ORDER BY T.zlqddm";
			}else{
				//判断是否为审核和查看
				if("3".equals(basx14BForm.getCzlx())){
					zlsql = "select t.zlqd,t.xh from SFDB.SF_JL_QYSDSJMSBAJLZLQD t WHERE t.BASQWSH = '"+basx14BForm.getBasqwsh()+"' ORDER BY T.xh";
				}else if("4".equals(basx14BForm.getCzlx())){
					zlsql = "select t.zlqd,t.sfshtg from SFDB.SF_JL_QYSDSJMSBAJLZLQD t WHERE t.BASQWSH = '"+basx14BForm.getBasqwsh()+"' ORDER BY T.sfshtg";
				}else{
					zlsql = "select t.zlqd,t.sfkysc from SFDB.SF_JL_QYSDSJMSBAJLZLQD t WHERE t.BASQWSH = '"+basx14BForm.getBasqwsh()+"' ORDER BY T.SFKYSC DESC";
				}
				
			}
			
			ps2 = conn.prepareStatement(zlsql);
			rs2 = ps2.executeQuery();
			while (rs2.next()) {
				//判断是否已经保存过
				if("0".equals(basx14BForm.getClbs())){
					
					String qxdm = jbsj.getSwjgzzjgdm().substring(0,2);
					System.out.println("============qxdm=====>>" + qxdm);
					if("01".equals(rs2.getString("ZLQDDM"))||rs2.getString("ZLQDDM").equals("01")){
						zl =zl + QysdsUtil.getZlqddm01mc(qxdm)+"|"+rs2.getString("SFKYSC")+";"; 
					}else{
						zl =zl + rs2.getString("ZLQDMC")+"|"+rs2.getString("SFKYSC")+";";	
					}
					System.out.println("=====zl=====>>>" + zl);
					//zl =zl + rs2.getString("ZLQDMC")+"|"+rs2.getString("SFKYSC")+";";
				}else{
					//判断是否为审核和查看
					if("3".equals(basx14BForm.getCzlx())){
						zl =zl + rs2.getString("ZLQD")+"|"+rs2.getString("XH")+";";
					}else if("4".equals(basx14BForm.getCzlx())){
						zl =zl + rs2.getString("ZLQD")+"|"+rs2.getString("SFSHTG")+";";
					}else{
						zl =zl + rs2.getString("ZLQD")+"|"+rs2.getString("SFKYSC")+";";
					}
					
				}
			}
			basx14BForm.setZl(zl.toString().substring(0,(zl.toString().length()-1)));
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (rs1 != null) {
				rs1.close();
			}
			if (ps1 != null) {
				ps1.close();
			}
			if (rs2 != null) {
				rs2.close();
			}
			if (ps2 != null) {
				ps2.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ApplicationException("数据库查询记录失败！" + basx14BForm.getJsjdm()	+ ":" + ex.getMessage());
		} finally {		
			SfDBResource.freeConnection(conn);
		}

		return basx14BForm;
	}
	
	/**
	 * doBasqbhSeach查询相关投资信息
	 * 
	 * @param vo
	 *            业务参数
	 * @return 数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
	 * @throws BaseException
	 *             当其他情况都会抛出异常信息
	 */

	private Object doBasqbhSeach(VOPackage vo) throws BaseException {
		Basx14BForm basx14BForm = (Basx14BForm) vo.getData();
		basx14BForm.setZysblxdm("");
		basx14BForm.setZysblx("");
		basx14BForm.setZysbmc("");
		basx14BForm.setGznd("");
		Connection conn = null;
		PreparedStatement ps0 = null;
		ResultSet rs0 = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String tze = "";
		String kdke = "";
		String sjdke = "";
		String jzdke = "";
		String bjkz = "";
		
		try {
			conn = SfDBResource.getConnection();
			String sql=" select b.tznd tznd,b.zysbmc zysbmc,b.zysblxdm zysblxdm from " +
			   " sfdb.sf_jl_qysdsjmsbajl a ,sfdb.sf_jl_qysdsjmsba_14b b where " +
			   " a.basqwsh=b.basqwsh and a.jsjdm='"+basx14BForm.getJsjdm()+"' "+ 
			   " and a.sqzt='4' and a.basqbh='"+basx14BForm.getWnbasqbh().trim()+"' " +
			   " and b.tznd<'"+basx14BForm.getBand()+"' and rownum=1 ";
			
			ps0 = conn.prepareStatement(sql);
			rs0 = ps0.executeQuery();
			int band = Integer.parseInt(basx14BForm.getBand());
			int gznd=band;	
			while(rs0.next()){
				if(rs0.getString("TZND") != null && !rs0.getString("TZND").equals("")){
					gznd=Integer.parseInt(rs0.getString("TZND"));
				}
				
			}
			StringBuffer gz = new StringBuffer();
			
			for(int n=gznd;n<=band;n++){
				gz.append("select '"+n+"' dmnd ,(SELECT BASQWSH FROM SFDB.SF_JL_QYSDSJMSBAJL WHERE BASQBH = '"+
						basx14BForm.getWnbasqbh()+"') BASQWSH from dual");
				if(n != band){
					gz.append("  union  ");
				}
			}
		
			String tzxxsql = "select b.dmnd,A.TZND,A.ZYSBMC,A.ZYSBLXDM,a.TZEZS,a.DNKDMSE,a.DMYNSE,a.JZE,nvl(a.ywcbabs,1) ywcbabs " +
				"from sfdb.sf_jl_qysdsjmsba_14b a ,("+gz.toString()+") b  " +
				" WHERE A.BASQWSH(+) =B.BASQWSH  AND A.DMND(+) = B.DMND AND A.TZND(+) >= ("+band+" - 4) ORDER BY B.DMND ";
			
			ps = conn.prepareStatement(tzxxsql);
			rs = ps.executeQuery();
			while(rs.next()){
				if(rs.getString("TZND") != null && !rs.getString("TZND").equals("")){
					basx14BForm.setGznd(rs.getString("TZND"));
				}
				if(rs.getString("ZYSBMC") != null && !rs.getString("ZYSBMC").equals("")){
					basx14BForm.setZysbmc(rs.getString("ZYSBMC"));
				}
				if(rs.getString("ZYSBLXDM") != null && !rs.getString("ZYSBLXDM").equals("")){
					basx14BForm.setZysblxdm(rs.getString("ZYSBLXDM"));
					basx14BForm.setZysblx(rs.getString("ZYSBLXDM"));
				}
				tze = tze + rs.getString("TZEZS")+";";
				kdke = kdke + rs.getString("DNKDMSE")+";";
				sjdke = sjdke + rs.getString("DMYNSE")+";";
				jzdke = jzdke + rs.getString("JZE")+";";
				bjkz = bjkz + rs.getString("YWCBABS")+";";
			}
			
			basx14BForm.setTze(tze.substring(0, tze.length()-1));
			basx14BForm.setKdke(kdke.substring(0, kdke.length()-1));
			basx14BForm.setSjdke(sjdke.substring(0, sjdke.length()-1));
			basx14BForm.setJzdke(jzdke.substring(0, jzdke.length()-1));
			basx14BForm.setBjkz(bjkz.substring(0, bjkz.length()-1));
			//判断是否查询出记录，如果有记录则设置查询结果为0，反之为1
			if(basx14BForm.getZysblxdm()!= null && !basx14BForm.getZysblxdm().equals("")){
				basx14BForm.setTzxxjg("0");
			}else{
				
				basx14BForm.setTzxxjg("1");
			}
			if(gznd==band){
				
				basx14BForm.setZysblxdm("");
				basx14BForm.setZysblx("");
				basx14BForm.setZysbmc("");
				basx14BForm.setGznd("");
				basx14BForm.setTzxxjg("1");
			}
			basx14BForm = (Basx14BForm)doShow(vo);
			if (rs0 != null) {
				rs0.close();
			}
			if (ps0 != null) {
				ps0.close();
			}
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ApplicationException("查询该税务登记证" + basx14BForm.getWnbasqbh()	+ "失败！:" + ex.getMessage());
		} finally {		
			SfDBResource.freeConnection(conn);
		}
		return basx14BForm;
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
		
		Basx14BForm basx14BForm = (Basx14BForm) vo.getData();
		UserData ud = (UserData)vo.getUserData();
		Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		PreparedStatement ps3 = null;
		PreparedStatement ps4 = null;
		QysdsUtil qysdsUtil = new QysdsUtil();
		//备案申请文书号
		String basqwsh = basx14BForm.getBasqwsh();
		//备案申请编号
		String basqbh = basx14BForm.getBasqbh();
		//计算机代码
		String jsjdm = basx14BForm.getJsjdm();
		//备案年度
		String band = basx14BForm.getBand();
		//减免税类别代码
		String jmbasxdm = "014B";
		//减免税政策执行情况
		String jmszczxqk=QysdsUtil.strNotNull(basx14BForm.getJmszczxqk())?basx14BForm.getJmszczxqk():"";
		//起始时间
		String qsrq=QysdsUtil.strNotNull(basx14BForm.getQsrq())?"to_date('"+basx14BForm.getQsrq()+"','yyyy-mm-dd')":"null";
		//截止时间
		String jzrq=QysdsUtil.strNotNull(basx14BForm.getJzrq())?"to_date('"+basx14BForm.getJzrq()+"','yyyy-mm-dd')":"null";
		//设置减免税额
		String bajmse = QysdsUtil.strNotNull(basx14BForm.getBajmse())?basx14BForm.getBajmse():"";
		String bajmbl = QysdsUtil.strNotNull(basx14BForm.getBajmbl())?basx14BForm.getBajmbl():"";
		Timestamp time = new Timestamp(new Date().getTime());
		try {
			conn = SfDBResource.getConnection();
			String sql = "";
			String zbsql = "";
			if("1".equals(basx14BForm.getClbs())){
				//更新主表
				sql = "update SFDB.SF_JL_QYSDSJMSBAJL set band = '"+band+"',bajmse='"+bajmse+"',bajmbl='"+bajmbl+"',fhwjmc=?,qsrq="+qsrq+
				",jzrq="+jzrq+",lrr='"+ud.getYhid()+"',lrrq=to_timestamp('"+time+"', 'yyyy-mm-dd hh24:mi:ss.ff') " +
				"where basqwsh='"+basx14BForm.getBasqwsh()+"'";
				//更新子表
				zbsql = "update sfdb.sf_jl_qysdsjmsba_14b set ZYSBLXDM = '"+basx14BForm.getZysblx()+"',TZND = '"+basx14BForm.getGznd()+
						"',ZYSBMC = ?,TZEZS = ?,DNKDMSE = ?,DMYNSE = ?,JZE = ?,YWCBABS = ?,SHBJ = '1'," +
						"LRR = '"+ud.getYhid()+"',LRRQ = to_timestamp('"+time+"','yyyy-mm-dd hh24:mi:ss.ff')" +
						"where basqwsh='"+basx14BForm.getBasqwsh()+"' and DMND = ?";
			}else{
				//插入主表
				sql = " insert into SFDB.SF_JL_QYSDSJMSBAJL(BASQWSH,BASQBH,JSJDM,BAND,JMBASXDM,SZDM,SWJGZZJGDM,SQZT,TJR,TJSJ,SHR,SHSJ," +
						"SQLXDM,BAJMSE,BAJMBL,FHWJMC,QSRQ,JZRQ,CJR,CJRQ,LRR,LRRQ)values('"+basqwsh+"','"+basqbh+"','"+jsjdm+"','"+band+"','"+
						jmbasxdm+"','30','"+ud.getSsdwdm()+"','4','"+ud.getYhid()+"',sysdate,'"+ud.getYhid()+"',sysdate,'1','"+bajmse+
						"','"+bajmbl+"',?,"+qsrq+","+jzrq+",'"+ud.getYhid()+"',sysdate,'"+ud.getYhid()+
						"',to_timestamp('"+time+"','yyyy-mm-dd hh24:mi:ss.ff'))";
				//保存时将 sfdb.sf_jl_qysdsjmsba_14b.ywcbabs置‘0’，
				zbsql = "INSERT INTO sfdb.sf_jl_qysdsjmsba_14b (XH,BASQWSH,JSJDM,BAND,SWJGZZJGDM,ZYSBLXDM,TZND,ZYSBMC," +
						"DMND,TZEZS,DNKDMSE,DMYNSE,JZE,YWCBABS,SHBJ,CJR,CJRQ,LRR,LRRQ)VALUES(?,'"+
						basqwsh+"','"+jsjdm+"','"+band+"',(select t.swjgzzjgdm from djdb.dj_jl_jbsj t WHERE t.jsjdm = '"+jsjdm+"'),'"+
						basx14BForm.getZysblx()+"','"+basx14BForm.getGznd()+"',?,?,?,?,?,?,?,'0','"+
						ud.getYhid()+"',sysdate,'"+ud.getYhid()+"',to_timestamp('"+time+"','yyyy-mm-dd hh24:mi:ss.ff'))";
			}
			//主表操作
			
			ps = conn.prepareStatement(sql);
			ps.setString(1,jmszczxqk);
			ps.executeQuery();
			//子表操作
			//根据被投资企业税务登记证号、计算机代码、每一个投资年度保存记录到业务表
			
			ps1 = conn.prepareStatement(zbsql);
			String[] tze = basx14BForm.getTze().split(";");
			String[] kdke = basx14BForm.getKdke().split(";");
			String[] sjdke = basx14BForm.getSjdke().split(";");
			String[] jzdke = basx14BForm.getJzdke().split(";");
			String[] bjkz = basx14BForm.getBjkz().split(";");
			int dmnd = Integer.parseInt(basx14BForm.getGznd());
			for(int i=0;i<tze.length;i++){
				
				if("1".equals(basx14BForm.getClbs())){
					
					ps1.setString(1, basx14BForm.getZysbmc());
					ps1.setString(2, tze[i]);
					ps1.setString(3, kdke[i]);
					ps1.setString(4, sjdke[i]);
					ps1.setString(5, jzdke[i]);
					ps1.setString(6, bjkz[i]);
					ps1.setString(7, String.valueOf(dmnd+i));
					
				}else{
					String xh = qysdsUtil.getSequence(conn);
					ps1.setString(1, xh);
					ps1.setString(2, basx14BForm.getZysbmc());
					ps1.setString(3, String.valueOf(dmnd+i));
					ps1.setString(4, tze[i]);
					ps1.setString(5, kdke[i]);
					ps1.setString(6, sjdke[i]);
					ps1.setString(7, jzdke[i]);
					ps1.setString(8, bjkz[i]);
				}
				
				ps1.addBatch();
			}
			ps1.executeBatch();
			//更新资料清单表
			if(!"".equals(basx14BForm.getZl())){
				//删除该备案申请文书号的资料清单表
				String delzlqd = "DELETE SFDB.SF_JL_QYSDSJMSBAJLZLQD WHERE BASQWSH = '"+basqwsh+"'";
				
				ps2=conn.prepareStatement(delzlqd);
				ps2.execute();
				//插入该备案申请文书号的资料清单
				String zlqd = "INSERT INTO SFDB.SF_JL_QYSDSJMSBAJLZLQD(XH,BASQWSH,ZLQD,SWJGZZJGDM,CJR,CJRQ,LRR,LRRQ,SFSHTG,SFKYSC)" +
						"VALUES(?,?,?,(select t.swjgzzjgdm from djdb.dj_jl_jbsj t WHERE t.jsjdm = ?),?,sysdate,?," +
						"to_timestamp('"+time+"','yyyy-mm-dd hh24:mi:ss.ff'),?,?)";
				String[] zl = basx14BForm.getZl().split(";");
				ps3=conn.prepareStatement(zlqd);			
				for(int i=0;i<zl.length;i++){
					String zlnr = zl[i].substring(0,zl[i].indexOf("|"));
					String zlsfkysc = zl[i].substring(zl[i].length()-1,zl[i].length());
//					
					String xh = qysdsUtil.getSequence(conn);
					ps3.setString(1, xh);
					ps3.setString(2, basqwsh);
					ps3.setString(3, zlnr);
					ps3.setString(4, jsjdm);
					ps3.setString(5, ud.getYhid());
					ps3.setString(6, ud.getYhid());
					ps3.setString(7, "1");
					ps3.setString(8, zlsfkysc);
					ps3.addBatch();
				}
				ps3.executeBatch();
			}
			
			if("1".equals(basx14BForm.getCzlx())){
				String updatesql = "UPDATE sfdb.sf_jl_qysdsjmsba_14b SET YWCBABS = '0' where BASQWSH = '"+basx14BForm.getBasqwsh()+"'";
				
				ps4 = conn.prepareStatement(updatesql);
				ps4.execute();
			}
			//设置处理标示为保存状态
			basx14BForm.setClbs("1");
			
			basx14BForm = (Basx14BForm)doShow(vo);
			
			if (ps != null) {
				ps.close();
			}
			if (ps1 != null) {
				ps1.close();
			}
			if (ps2 != null) {
				ps2.close();
			}
			if (ps3 != null) {
				ps3.close();
			}
			if (ps4 != null) {
				ps4.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ApplicationException("数据库保存记录失败！" + basx14BForm.getJsjdm()	+ ":" + ex.getMessage());
		} finally {		
			SfDBResource.freeConnection(conn);
		}
		return basx14BForm;
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

	private Object doBg(VOPackage vo) throws BaseException {
		
		Basx14BForm basx14BForm = (Basx14BForm) vo.getData();
		UserData ud = (UserData)vo.getUserData();
		Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		PreparedStatement ps3 = null;
		PreparedStatement ps4 = null;
		QysdsUtil qysdsUtil = new QysdsUtil();
		//备案申请文书号
		String basqwsh = basx14BForm.getBasqwsh();
		
		//计算机代码
		String jsjdm = basx14BForm.getJsjdm();
		//备案年度
		String band = basx14BForm.getBand();
		
		//减免税政策执行情况
		String jmszczxqk=QysdsUtil.strNotNull(basx14BForm.getJmszczxqk())?basx14BForm.getJmszczxqk():"";
		//起始时间
		String qsrq=QysdsUtil.strNotNull(basx14BForm.getQsrq())?"to_date('"+basx14BForm.getQsrq()+"','yyyy-mm-dd')":"null";
		//截止时间
		String jzrq=QysdsUtil.strNotNull(basx14BForm.getJzrq())?"to_date('"+basx14BForm.getJzrq()+"','yyyy-mm-dd')":"null";
		//设置减免税额
		String bajmse = QysdsUtil.strNotNull(basx14BForm.getBajmse())?basx14BForm.getBajmse():"";
		String bajmbl = QysdsUtil.strNotNull(basx14BForm.getBajmbl())?basx14BForm.getBajmbl():"";
		Timestamp time = new Timestamp(new Date().getTime());
		try {
			conn = SfDBResource.getConnection();
			String sql = "";
			String zbsql = "";

				//更新主表
				sql = "update SFDB.SF_JL_QYSDSJMSBAJL set  sqzt='4',band = '"+band+"',bajmse='"+bajmse+"',bajmbl='"+bajmbl+"',fhwjmc=?,qsrq="+qsrq+
				",jzrq="+jzrq+",lrr='"+ud.getYhid()+"',lrrq=to_timestamp('"+time+"', 'yyyy-mm-dd hh24:mi:ss.ff') " +
				"where basqwsh='"+basx14BForm.getBasqwsh()+"'";
				//更新子表
				zbsql = "update sfdb.sf_jl_qysdsjmsba_14b set ZYSBLXDM = '"+basx14BForm.getZysblx()+"',TZND = '"+basx14BForm.getGznd()+
						"',ZYSBMC = ?,TZEZS = ?,DNKDMSE = ?,DMYNSE = ?,JZE = ?,YWCBABS = ?,SHBJ = '1'," +
						"LRR = '"+ud.getYhid()+"',LRRQ = to_timestamp('"+time+"','yyyy-mm-dd hh24:mi:ss.ff')" +
						"where basqwsh='"+basx14BForm.getBasqwsh()+"' and DMND = ?";

			//主表操作
			
			ps = conn.prepareStatement(sql);
			ps.setString(1,jmszczxqk);
			ps.executeQuery();
			//子表操作
			//根据被投资企业税务登记证号、计算机代码、每一个投资年度保存记录到业务表
			
			ps1 = conn.prepareStatement(zbsql);
			String[] tze = basx14BForm.getTze().split(";");
			String[] kdke = basx14BForm.getKdke().split(";");
			String[] sjdke = basx14BForm.getSjdke().split(";");
			String[] jzdke = basx14BForm.getJzdke().split(";");
			String[] bjkz = basx14BForm.getBjkz().split(";");
			int dmnd = Integer.parseInt(basx14BForm.getGznd());
			for(int i=0;i<tze.length;i++){
				
				if("1".equals(basx14BForm.getClbs())){
					
					ps1.setString(1, basx14BForm.getZysbmc());
					ps1.setString(2, tze[i]);
					ps1.setString(3, kdke[i]);
					ps1.setString(4, sjdke[i]);
					ps1.setString(5, jzdke[i]);
					ps1.setString(6, bjkz[i]);
					ps1.setString(7, String.valueOf(dmnd+i));
					
				}else{
					String xh = qysdsUtil.getSequence(conn);
					ps1.setString(1, xh);
					ps1.setString(2, basx14BForm.getZysbmc());
					ps1.setString(3, String.valueOf(dmnd+i));
					ps1.setString(4, tze[i]);
					ps1.setString(5, kdke[i]);
					ps1.setString(6, sjdke[i]);
					ps1.setString(7, jzdke[i]);
					ps1.setString(8, bjkz[i]);
				}
				
				ps1.addBatch();
			}
			ps1.executeBatch();
			//更新资料清单表
			if(!"".equals(basx14BForm.getZl())){
				//删除该备案申请文书号的资料清单表
				String delzlqd = "DELETE SFDB.SF_JL_QYSDSJMSBAJLZLQD WHERE BASQWSH = '"+basqwsh+"'";
				
				ps2=conn.prepareStatement(delzlqd);
				ps2.execute();
				//插入该备案申请文书号的资料清单
				String zlqd = "INSERT INTO SFDB.SF_JL_QYSDSJMSBAJLZLQD(XH,BASQWSH,ZLQD,SWJGZZJGDM,CJR,CJRQ,LRR,LRRQ,SFSHTG,SFKYSC)" +
						"VALUES(?,?,?,(select t.swjgzzjgdm from djdb.dj_jl_jbsj t WHERE t.jsjdm = ?),?,sysdate,?," +
						"to_timestamp('"+time+"','yyyy-mm-dd hh24:mi:ss.ff'),?,?)";
				String[] zl = basx14BForm.getZl().split(";");
				ps3=conn.prepareStatement(zlqd);			
				for(int i=0;i<zl.length;i++){
					String zlnr = zl[i].substring(0,zl[i].indexOf("|"));
					String zlsfkysc = zl[i].substring(zl[i].length()-1,zl[i].length());
//					
					String xh = qysdsUtil.getSequence(conn);
					ps3.setString(1, xh);
					ps3.setString(2, basqwsh);
					ps3.setString(3, zlnr);
					ps3.setString(4, jsjdm);
					ps3.setString(5, ud.getYhid());
					ps3.setString(6, ud.getYhid());
					ps3.setString(7, "1");
					ps3.setString(8, zlsfkysc);
					ps3.addBatch();
				}
				ps3.executeBatch();
			}
			
			if("1".equals(basx14BForm.getCzlx())){
				String updatesql = "UPDATE sfdb.sf_jl_qysdsjmsba_14b SET YWCBABS = '0' where BASQWSH = '"+basx14BForm.getBasqwsh()+"'";
				
				ps4 = conn.prepareStatement(updatesql);
				ps4.execute();
			}
			//设置处理标示为保存状态
			basx14BForm.setClbs("1");
			
			basx14BForm = (Basx14BForm)doShow(vo);
			
			if (ps != null) {
				ps.close();
			}
			if (ps1 != null) {
				ps1.close();
			}
			if (ps2 != null) {
				ps2.close();
			}
			if (ps3 != null) {
				ps3.close();
			}
			if (ps4 != null) {
				ps4.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ApplicationException("数据库保存记录失败！" + basx14BForm.getJsjdm()	+ ":" + ex.getMessage());
		} finally {		
			SfDBResource.freeConnection(conn);
		}
		return basx14BForm;
	}
	
	/**
	 * doQuery查询对象页面信息要素
	 * 
	 * @param vo
	 *            业务参数
	 * @return 数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
	 * @throws BaseException
	 *             当其他情况都会抛出异常信息
	 */

	private Object doQuery(VOPackage vo) throws BaseException {
		
		Basx14BForm basx14BForm = (Basx14BForm) vo.getData();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		PreparedStatement ps1 = null;
		ResultSet rs1 = null;
		try {
			conn = SfDBResource.getConnection();	
			String sql = "SELECT t.jsjdm,t.TZND,t.ZYSBMC,t.ZYSBLXDM,b.ZYSBLXMC," +
					" S.QSRQ,S.JZRQ,S.BAJMBL,S.BAJMSE,S.FHWJMC,S.JSJDM,S.BASQBH,S.BAND,S.CJR,to_char(S.CJRQ,'yyyymmdd')cjrq,S.zfsm " +
					" FROM sfdb.sf_jl_qysdsjmsba_14B T,sfdb.sf_jl_qysdsjmsbajl S,dmdb.SF_DM_ZYSBLX B " +
					" WHERE T.BASQWSH = S.BASQWSH AND T.ZYSBLXDM = B.ZYSBLXDM AND T.BASQWSH = '"+basx14BForm.getBasqwsh()+"' AND ROWNUM = 1";
			
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				basx14BForm.setJsjdm(rs.getString("jsjdm"));
				basx14BForm.setGznd(rs.getString("TZND"));
				basx14BForm.setZysbmc(rs.getString("ZYSBMC"));
				basx14BForm.setZysblxdm(rs.getString("ZYSBLXDM"));
				basx14BForm.setZysblx(rs.getString("ZYSBLXDM"));
				basx14BForm.setZysblxmc(rs.getString("ZYSBLXMC"));
				basx14BForm.setJmszczxqk(rs.getString("FHWJMC"));
				basx14BForm.setJsjdm(rs.getString("JSJDM"));
				basx14BForm.setBasqbh(rs.getString("BASQBH"));
				basx14BForm.setBajmbl(rs.getString("BAJMBL"));
				basx14BForm.setBajmse(rs.getString("BAJMSE"));
				basx14BForm.setMr_band(rs.getString("BAND"));
				basx14BForm.setMr_lrr(rs.getString("CJR"));
				basx14BForm.setMr_lrrq(rs.getString("CJRQ"));
				basx14BForm.setZfsm(rs.getString("ZFSM"));
				//对日期进行处理，只取2001-01-01
				if(!"".equals(rs.getString("QSRQ"))){
					basx14BForm.setQsrq(rs.getString("QSRQ").substring(0, 10));
				}else{
					basx14BForm.setQsrq(rs.getString("QSRQ"));
				}
				if(!"".equals(rs.getString("JZRQ"))){
					basx14BForm.setJzrq(rs.getString("JZRQ").substring(0, 10));
				}else{
					basx14BForm.setJzrq(rs.getString("JZRQ"));
				}
				
			}
			String tze = "";
			String kdke = "";
			String sjdke = "";
			String jzdke = "";
			String bjkz = "";
			StringBuffer gz = new StringBuffer();
			int gznd = Integer.parseInt(basx14BForm.getGznd());
			int band = Integer.parseInt(basx14BForm.getMr_band());
			for(int n=gznd;n<=band;n++){
				gz.append("select '"+n+"' DMND from dual");
				if(n != band){
					gz.append("  union  ");
				}
			}
			String tzxxsql = "select b.DMND,a.TZEZS,a.DNKDMSE,a.DMYNSE,a.JZE,nvl(a.ywcbabs,1) ywcbabs " +
			"from sfdb.sf_jl_qysdsjmsba_14b a ,("+gz.toString()+") b  " +
			"where a.DMND(+)=b.DMND  and a.basqwsh='"+basx14BForm.getBasqwsh()+"' order by b.DMND ";
			
			ps1 = conn.prepareStatement(tzxxsql);
			rs1 = ps1.executeQuery();
			while(rs1.next()){
				tze = tze + rs1.getString("TZEZS")+";";
				kdke = kdke + rs1.getString("DNKDMSE")+";";
				sjdke = sjdke + rs1.getString("DMYNSE")+";";
				jzdke = jzdke + rs1.getString("JZE")+";";
				bjkz = bjkz + rs1.getString("YWCBABS")+";";
			}
			basx14BForm.setTze(tze.substring(0, tze.length()-1));
			basx14BForm.setKdke(kdke.substring(0, kdke.length()-1));
			basx14BForm.setSjdke(sjdke.substring(0, sjdke.length()-1));
			basx14BForm.setJzdke(jzdke.substring(0, jzdke.length()-1));
			basx14BForm.setBjkz(bjkz.substring(0, bjkz.length()-1));
			//设置处理标示为保存状态
			basx14BForm.setClbs("1");
			basx14BForm = (Basx14BForm)doShow(vo);
			
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (rs1 != null) {
				rs1.close();
			}
			if (ps1 != null) {
				ps1.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ApplicationException("数据库查询记录失败！" + basx14BForm.getJsjdm()	+ ":" + ex.getMessage());
		} finally {		
			SfDBResource.freeConnection(conn);
		}
		return basx14BForm;
	}
	
	
	/**
	 * doCheck网上-接受或拒绝申请
	 * 
	 * @param vo
	 *            业务参数
	 * @return 数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
	 * @throws BaseException
	 *             当其他情况都会抛出异常信息
	 */

	private Object doCheck(VOPackage vo) throws BaseException {
		
		Basx14BForm basx14BForm = (Basx14BForm) vo.getData();
		UserData ud = (UserData) vo.getUserData();
		Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		String SQL = "";
		String sql = "";
		String SELECT = " SELECT ";
		String XH = " XH, ";
		String SHBJ = " SFSHTG FROM DUAL ";
		String UNION = " UNION ALL";
		String s = basx14BForm.getShzl();
		String[] ss = s.split(";");
		for(int i = 0;i<ss.length;i++){
			String[] sa = ss[i].split(",");
			for(int j =0;j<sa.length;j++){
				if(j==0){
					sql = SELECT+sa[j]+XH;
				}else{
					sql = sql+sa[j]+SHBJ;
				}
				
			}
			if(i!=(ss.length-1)){
				sql = sql + UNION;
			}
			SQL = SQL+sql;
		}
		try {
			conn = SfDBResource.getConnection();
			//检查当前减免税备案申请是否为保存未审核或提交未审核
			boolean sqzt = QysdsUtil.checkSqzt(basx14BForm.getBasqwsh(), conn);
			if (!sqzt) {
				throw new ApplicationException("此减免税备案申请已被纳税人撤回！");
			}
			//更新主表状态为审核通过或审核未通过
			QysdsUtil.updateSqzt(basx14BForm.getBasqwsh(), basx14BForm.getSqzt(), ud.getYhid(),conn);
			
			//更新子表状态为审核通过或审核未通过
			StringBuffer shzbSql = new StringBuffer("UPDATE sfdb.sf_jl_qysdsjmsba_14b t SET  ");
			if("4".equals(basx14BForm.getSqzt())){
				shzbSql.append(" t.shbj ='0' ,t.YWCBABS = '0' ");
			}else{
				shzbSql.append(" t.shbj ='1' ,t.YWCBABS = '1' ");
			}
			shzbSql.append(" ,t.lrr = '"+ud.getYhid()+"',t.lrrq = sysdate WHERE t.basqwsh = '"+basx14BForm.getBasqwsh()+"'");
			
			ps = conn.prepareStatement(shzbSql.toString());
			ps.execute();
			
			//更新资料清单表中资料是否审核通过
			SQL = "UPDATE SFDB.SF_JL_QYSDSJMSBAJLZLQD A SET A.SFSHTG = (SELECT B.SFSHTG FROM (" +SQL+
					") B WHERE A.XH = B.XH)WHERE A.BASQWSH = '"+basx14BForm.getBasqwsh()+"'";
			
			ps1 = conn.prepareStatement(SQL);
			ps1.execute();
			
			if (ps != null) {
				ps.close();
			}
			if (ps1 != null) {
				ps1.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ApplicationException("数据库审核记录失败！" + basx14BForm.getJsjdm()	+ ":" + ex.getMessage());
		} finally {
			SfDBResource.freeConnection(conn);
		}
		return basx14BForm;
	}
	
	
	private Object doPrint(VOPackage vo) throws BaseException {
		Connection conn = null;
		PreparedStatement printPst = null;
		ResultSet rs = null;
		Basx14BForm basx14BForm = (Basx14BForm) vo.getData();
		String zlqd = "";
		String sql = "SELECT A.NSRMC,T.JSJDM,S.JMBASXMC,T.QSRQ,T.JZRQ,T.BAJMSEHBL,T.FHWJMC,Q.ZLQD " +
				"FROM SFDB.SF_JL_QYSDSJMSBAJL T,SFDB.SF_JL_QYSDSJMSBAJLZLQD Q,DJDB.DJ_JL_JBSJ A,DMDB.SF_DM_JMBASXDM S " +
				"WHERE T.BASQWSH = Q.BASQWSH(+) AND T.JSJDM = A.JSJDM  AND T.JMBASXDM = S.JMBASXDM AND  T.BASQWSH = '"+ basx14BForm.getBasqwsh() + "'";
		try {
			conn = SfDBResource.getConnection();
			printPst = conn.prepareStatement(sql);
			rs = printPst.executeQuery();
			while(rs.next()){
				
			}
		} catch (Exception ex) {
			throw new ApplicationException("获取打印信息错误！");
		} finally {
			// 释放数据库连接
			SfDBResource.freeConnection(conn);
		}

		return basx14BForm;
	}
}
