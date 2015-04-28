package com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx13b.processor;

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
import com.ttsoft.bjtax.smsb.qysdsjmsbagl.util.QysdsUtil;
import com.ttsoft.bjtax.smsb.qysdsjmsbagl.vo.DmVo;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

public class Basx13BProcessor implements Processor{
	
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
			result = doJsjdmSeach(vo);
			break;
		case CodeConstant.SMSB_QUERYACTION2:
			result = doSwdjzhSeach(vo);
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
		
		Basx13BForm basx13BForm = (Basx13BForm) vo.getData();
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
			HashMap djMap = InterfaceDj.getDjInfo(basx13BForm.getJsjdm(), ud);
			SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");
			System.out.println("=====getSwjgzzjgdm======>>>>" + jbsj.getSwjgzzjgdm());
			
			conn = SfDBResource.getConnection();	
			String sql = "select a.gxjslydm,a.gxjslymc from dmdb.sf_dm_gxjsly a order by gxjslydm";
			
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {	
				String dm=rs.getString("gxjslydm");
				String mc=rs.getString("gxjslymc");
				DmVo dmvo=new DmVo();
				dmvo.setDm(dm);
				dmvo.setMc(mc);
				list.add(dmvo);		
			}
			basx13BForm.setGxjslyList(list);
			
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
			sb.append(" from djdb.dj_jl_jbsj a where a.jsjdm='"+basx13BForm.getJsjdm()+"'");
			
			
			ps1 = conn.prepareStatement(sb.toString());
			rs1 = ps1.executeQuery();
			while (rs1.next()){
				basx13BForm.setJsjdm(rs1.getString("JSJDM"));
				basx13BForm.setNsrmc(rs1.getString("NSRMC"));
				basx13BForm.setZgsws(rs1.getString("ZGSWS"));
				basx13BForm.setJjlx(rs1.getString("JJLX"));
				basx13BForm.setSshy(rs1.getString("SSHY"));
				basx13BForm.setLxr(rs1.getString("LXR"));
				basx13BForm.setLxdh(rs1.getString("LXDH"));
			}
			String zl = "";
			String zlsql = "";
			//判断是否已经保存过
			if("0".equals(basx13BForm.getClbs())){
				//zlsql = "select t.zlqdmc,t.sfkysc from dmdb.sf_dm_bazlqddm t WHERE t.jmbasxdm = '013B'  and t.zfbs = '0' ORDER BY T.zlqddm";
				zlsql = "select t.zlqdmc,t.sfkysc,t.zlqddm from dmdb.sf_dm_bazlqddm t WHERE t.jmbasxdm = '013B'  and t.zfbs = '0' ORDER BY T.zlqddm";
			}else{
				//判断是否为审核和查看
				if("3".equals(basx13BForm.getCzlx())){
					zlsql = "select t.zlqd,t.xh from SFDB.SF_JL_QYSDSJMSBAJLZLQD t WHERE t.BASQWSH = '"+basx13BForm.getBasqwsh()+"' ORDER BY T.xh";
				}else if("4".equals(basx13BForm.getCzlx())){
					zlsql = "select t.zlqd,t.sfshtg from SFDB.SF_JL_QYSDSJMSBAJLZLQD t WHERE t.BASQWSH = '"+basx13BForm.getBasqwsh()+"' ORDER BY T.sfshtg";
				}else{
					zlsql = "select t.zlqd,t.sfkysc from SFDB.SF_JL_QYSDSJMSBAJLZLQD t WHERE t.BASQWSH = '"+basx13BForm.getBasqwsh()+"' ORDER BY T.SFKYSC DESC";
				}
				
			}
			
			ps2 = conn.prepareStatement(zlsql);
			rs2 = ps2.executeQuery();
			while (rs2.next()) {
				//判断是否已经保存过
				if("0".equals(basx13BForm.getClbs())){
					
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
					if("3".equals(basx13BForm.getCzlx())){
						zl =zl + rs2.getString("ZLQD")+"|"+rs2.getString("XH")+";";
					}else if("4".equals(basx13BForm.getCzlx())){
						zl =zl + rs2.getString("ZLQD")+"|"+rs2.getString("SFSHTG")+";";
					}else{
						zl =zl + rs2.getString("ZLQD")+"|"+rs2.getString("SFKYSC")+";";
					}
					
				}
			}
			basx13BForm.setZl(zl.toString().substring(0,(zl.toString().length()-1)));
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
			throw new ApplicationException("数据库查询记录失败！" + basx13BForm.getJsjdm()	+ ":" + ex.getMessage());
		} finally {		
			SfDBResource.freeConnection(conn);
		}

		return basx13BForm;
	}
	
	/**
	 * doJsjdmSeach查询税务登记证号及相关投资信息
	 * 
	 * @param vo
	 *            业务参数
	 * @return 数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
	 * @throws BaseException
	 *             当其他情况都会抛出异常信息
	 */

	private Object doJsjdmSeach(VOPackage vo) throws BaseException {
		Basx13BForm basx13BForm = (Basx13BForm) vo.getData();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = SfDBResource.getConnection();	
			String jbsjsql = "SELECT nsrmc,swdjzh FROM djdb.dj_jl_jbsj WHERE jsjdm = '"+basx13BForm.getBtzqyjsjdm()+"'";
			
			ps = conn.prepareStatement(jbsjsql);
			rs = ps.executeQuery();
			while (rs.next()) {	
				basx13BForm.setBtzqymc(rs.getString("nsrmc"));	
				basx13BForm.setBtzqyswdjzh(rs.getString("swdjzh"));	
			}
			
			//设置是否查询纳税人名称
			basx13BForm.setNsrmckz("1");
			basx13BForm = (Basx13BForm)doSwdjzhSeach(vo);
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ApplicationException("查询该计算机代码" + basx13BForm.getJsjdm()	+ "失败！:" + ex.getMessage());
		} finally {		
			SfDBResource.freeConnection(conn);
		}
		return basx13BForm;
	}
	
	/**
	 * doSwdjzhSeach查询相关投资信息
	 * 
	 * @param vo
	 *            业务参数
	 * @return 数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
	 * @throws BaseException
	 *             当其他情况都会抛出异常信息
	 */

	private Object doSwdjzhSeach(VOPackage vo) throws BaseException {
		Basx13BForm basx13BForm = (Basx13BForm) vo.getData();
		basx13BForm.setGxjslydm("");
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String tze = "";
		String kdke = "";
		String sjdke = "";
		String jzdke = "";
		String bjkz = "";
		StringBuffer gz = new StringBuffer();
		int band = Integer.parseInt(basx13BForm.getBand());
		for(int n=2006;n<=band;n++){
			gz.append("select '"+n+"' tznd from dual");
			if(n != band){
				gz.append("  union  ");
			}
		}
		try {
			conn = SfDBResource.getConnection();	
			String tzxxsql = "select b.tznd,A.BTZQYMC,A.GXJSLYDM,a.tze,a.dnkdke,a.dke,a.jze,nvl(a.ywcbabs,1) ywcbabs " +
				"from sfdb.sf_jl_qysdsjmsba_13b a ,("+gz.toString()+") b  " +
				"where a.tznd(+)=b.tznd  and a.ywcbabs(+)='0' and a.btzqyswdjzh(+)='"+basx13BForm.getBtzqyswdjzh()+
				"' and a.jsjdm(+)='"+basx13BForm.getJsjdm()+"' order by b.tznd ";
			
			ps = conn.prepareStatement(tzxxsql);
			rs = ps.executeQuery();
			while(rs.next()){
				if("0".equals(basx13BForm.getNsrmckz()) && rs.getString("BTZQYMC") != null && !rs.getString("BTZQYMC").equals("")){
					basx13BForm.setBtzqymc(rs.getString("BTZQYMC"));
				}
				if(rs.getString("GXJSLYDM") != null && !rs.getString("GXJSLYDM").equals("")){
					basx13BForm.setGxjslydm(rs.getString("GXJSLYDM"));
				}
				tze = tze + rs.getString("TZE")+";";
				kdke = kdke + rs.getString("DNKDKE")+";";
				sjdke = sjdke + rs.getString("DKE")+";";
				jzdke = jzdke + rs.getString("JZE")+";";
				bjkz = bjkz + rs.getString("YWCBABS")+";";
			}
			
			basx13BForm.setTze(tze.substring(0, tze.length()-1));
			basx13BForm.setKdke(kdke.substring(0, kdke.length()-1));
			basx13BForm.setSjdke(sjdke.substring(0, sjdke.length()-1));
			basx13BForm.setJzdke(jzdke.substring(0, jzdke.length()-1));
			basx13BForm.setBjkz(bjkz.substring(0, bjkz.length()-1));
			//判断是否查询出记录，如果有记录则设置查询结果为0，反之为1
			if(basx13BForm.getGxjslydm()!= null && !basx13BForm.getGxjslydm().equals("")){
				basx13BForm.setTzxxjg("0");
			}else{
				basx13BForm.setTzxxjg("1");
			}
			basx13BForm = (Basx13BForm)doShow(vo);
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ApplicationException("查询该税务登记证" + basx13BForm.getBtzqyswdjzh()	+ "失败！:" + ex.getMessage());
		} finally {		
			SfDBResource.freeConnection(conn);
		}
		return basx13BForm;
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
		
		Basx13BForm basx13BForm = (Basx13BForm) vo.getData();
		UserData ud = (UserData)vo.getUserData();
		Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		PreparedStatement ps3 = null;
		PreparedStatement ps4 = null;
		PreparedStatement ps5 = null;
		PreparedStatement ps6 = null;
		PreparedStatement ps7 = null;
		QysdsUtil qysdsUtil = new QysdsUtil();
		//备案申请文书号
		String basqwsh = basx13BForm.getBasqwsh();
		//备案申请编号
		String basqbh = basx13BForm.getBasqbh();
		//计算机代码
		String jsjdm = basx13BForm.getJsjdm();
		//备案年度
		String band = basx13BForm.getBand();
		//减免税类别代码
		String jmbasxdm = "013B";
		//减免税政策执行情况
		String jmszczxqk=QysdsUtil.strNotNull(basx13BForm.getJmszczxqk())?basx13BForm.getJmszczxqk():"";
		//起始时间
		String qsrq=QysdsUtil.strNotNull(basx13BForm.getQsrq())?"to_date('"+basx13BForm.getQsrq()+"','yyyy-mm-dd')":"null";
		//截止时间
		String jzrq=QysdsUtil.strNotNull(basx13BForm.getJzrq())?"to_date('"+basx13BForm.getJzrq()+"','yyyy-mm-dd')":"null";
		//设置减免税额
		String bajmse = QysdsUtil.strNotNull(basx13BForm.getBajmse())?basx13BForm.getBajmse():"";
		String bajmbl = QysdsUtil.strNotNull(basx13BForm.getBajmbl())?basx13BForm.getBajmbl():"";
		Timestamp time = new Timestamp(new Date().getTime());
		try {
			conn = SfDBResource.getConnection();
			String sql = "";
			String delzbsql = "";
			String zbsql = "";
			String delhissql = "";
			String hissql = "";
			if("1".equals(basx13BForm.getClbs())){
				//更新主表
				sql = "update SFDB.SF_JL_QYSDSJMSBAJL set band = '"+band+"',bajmse='"+bajmse+"',bajmbl='"+bajmbl+"',fhwjmc=?,qsrq="+qsrq+
				",jzrq="+jzrq+",lrr='"+ud.getYhid()+"',lrrq=to_timestamp('"+time+"', 'yyyy-mm-dd hh24:mi:ss.ff') " +
				"where basqwsh='"+basx13BForm.getBasqwsh()+"'";
			}else{
				//插入主表
				sql = " insert into SFDB.SF_JL_QYSDSJMSBAJL(BASQWSH,BASQBH,JSJDM,BAND,JMBASXDM,SZDM,SWJGZZJGDM,SQZT,TJR,TJSJ,SHR,SHSJ," +
						"SQLXDM,BAJMSE,BAJMBL,FHWJMC,QSRQ,JZRQ,CJR,CJRQ,LRR,LRRQ)values('"+basqwsh+"','"+basqbh+"','"+jsjdm+"','"+band+"','"+
						jmbasxdm+"','30','"+ud.getSsdwdm()+"','4','"+ud.getYhid()+"',sysdate,'"+ud.getYhid()+"',sysdate,'1','"+bajmse+
						"','"+bajmbl+"',?,"+qsrq+","+jzrq+",'"+ud.getYhid()+"',sysdate,'"+ud.getYhid()+
						"',to_timestamp('"+time+"','yyyy-mm-dd hh24:mi:ss.ff'))";
			}
			
			//插入子表
			//根据被投资企业税务登记证号、计算机代码将业务表的数据删除
			delzbsql = "DELETE sfdb.sf_jl_qysdsjmsba_13b t WHERE t.jsjdm = '"+
					basx13BForm.getJsjdm()+"' AND t.btzqyswdjzh = '"+basx13BForm.getBtzqyswdjzh()+"'"; 
			//根据被投资企业税务登记证号、计算机代码、每一个投资年度保存记录到业务表,保存时将 sfdb.sf_jl_qysdsjmsba_13b.ywcbabs置‘0’，
			zbsql = "INSERT INTO sfdb.sf_jl_qysdsjmsba_13b (XH,BASQWSH,JSJDM,BAND,SWJGZZJGDM,GXJSLYDM,BTZQYJSJDM," +
					"BTZQYSWDJZH,BTZQYMC,BTZQYSSD,TZND,TZE,DNKDKE,DKE,JZE,YWCBABS,SHBJ,CJR,CJRQ,LRR,LRRQ)VALUES(?,'"+
					basqwsh+"','"+jsjdm+"','"+band+"',(select t.swjgzzjgdm from djdb.dj_jl_jbsj t WHERE t.jsjdm = '"+jsjdm+"'),'"+
					basx13BForm.getGxjslydm()+"','"+basx13BForm.getBtzqyjsjdm()+"','"+basx13BForm.getBtzqyswdjzh()+"','"+
					basx13BForm.getBtzqymc()+"','"+basx13BForm.getBtzqyssd()+"',?,?,?,?,?,?,'0','"+
					ud.getYhid()+"',sysdate,'"+ud.getYhid()+"',to_timestamp('"+time+"','yyyy-mm-dd hh24:mi:ss.ff'))";
			//根据备案申请文书号删除历史表数据
			delhissql = "DELETE sfdb.sf_jl_qysdsjmsba_13b_his t WHERE t.basqwsh = '"+basx13BForm.getBasqwsh()+"'";
			//将业务表的数据插入到 SF_JL_QYSDSJMSBA_13B_HIS
			hissql = "INSERT INTO sfdb.sf_jl_qysdsjmsba_13b_his (XH,BASQWSH,JSJDM,BAND,SWJGZZJGDM,GXJSLYDM,BTZQYJSJDM," +
					"BTZQYSWDJZH,BTZQYMC,BTZQYSSD,TZND,TZE,DNKDKE,DKE,JZE,YWCBABS,SHBJ,CJR,CJRQ,LRR,LRRQ)VALUES(?,'"+
					basqwsh+"','"+jsjdm+"','"+band+"',(select t.swjgzzjgdm from djdb.dj_jl_jbsj t WHERE t.jsjdm = '"+jsjdm+"'),'"+
					basx13BForm.getGxjslydm()+"','"+basx13BForm.getBtzqyjsjdm()+"','"+basx13BForm.getBtzqyswdjzh()+"','"+
					basx13BForm.getBtzqymc()+"','"+basx13BForm.getBtzqyssd()+"',?,?,?,?,?,?,'0','"+
					ud.getYhid()+"',sysdate,'"+ud.getYhid()+"',to_timestamp('"+time+"','yyyy-mm-dd hh24:mi:ss.ff'))";
			
			//主表操作
			
			ps = conn.prepareStatement(sql);
			ps.setString(1,jmszczxqk);
			ps.executeQuery();
			//子表操作
			//根据被投资企业税务登记证号、计算机代码将业务表的数据删除
			
			ps1 = conn.prepareStatement(delzbsql);
			ps1.execute();
			//根据被投资企业税务登记证号、计算机代码、每一个投资年度保存记录到业务表
			
			ps2 = conn.prepareStatement(zbsql);
			String[] tze = basx13BForm.getTze().split(";");
			String[] kdke = basx13BForm.getKdke().split(";");
			String[] sjdke = basx13BForm.getSjdke().split(";");
			String[] jzdke = basx13BForm.getJzdke().split(";");
			String[] bjkz = basx13BForm.getBjkz().split(";");
			int tznd = 2006;
			for(int i=0;i<tze.length;i++){
				String xh = qysdsUtil.getSequence(conn);
				
				ps2.setString(1, xh);
				ps2.setString(2, String.valueOf(tznd+i));
				ps2.setString(3, tze[i]);
				ps2.setString(4, kdke[i]);
				ps2.setString(5, sjdke[i]);
				ps2.setString(6, jzdke[i]);
				ps2.setString(7, bjkz[i]);
				ps2.addBatch();
			}
			ps2.executeBatch();
			//根据备案申请文书号删除历史表数据
			
			ps3 = conn.prepareStatement(delhissql);
			ps3.execute();
			//将业务表的数据插入到 SF_JL_QYSDSJMSBA_13B_HIS
			
			ps4 = conn.prepareStatement(hissql);
			for(int i=0;i<tze.length;i++){
				String xh = qysdsUtil.getSequence(conn);
				
				ps4.setString(1, xh);
				ps4.setString(2, String.valueOf(tznd+i));
				ps4.setString(3, tze[i]);
				ps4.setString(4, kdke[i]);
				ps4.setString(5, sjdke[i]);
				ps4.setString(6, jzdke[i]);
				ps4.setString(7, bjkz[i]);
				ps4.addBatch();
			}
			ps4.executeBatch();
			
			
			//更新资料清单表
			if(!"".equals(basx13BForm.getZl())){
				//删除该备案申请文书号的资料清单表
				String delzlqd = "DELETE SFDB.SF_JL_QYSDSJMSBAJLZLQD WHERE BASQWSH = '"+basqwsh+"'";
				
				ps5=conn.prepareStatement(delzlqd);
				ps5.execute();
				//插入该备案申请文书号的资料清单
				String zlqd = "INSERT INTO SFDB.SF_JL_QYSDSJMSBAJLZLQD(XH,BASQWSH,ZLQD,SWJGZZJGDM,CJR,CJRQ,LRR,LRRQ,SFSHTG,SFKYSC)" +
						"VALUES(?,?,?,(select t.swjgzzjgdm from djdb.dj_jl_jbsj t WHERE t.jsjdm = ?),?,sysdate,?," +
						"to_timestamp('"+time+"','yyyy-mm-dd hh24:mi:ss.ff'),?,?)";
				String[] zl = basx13BForm.getZl().split(";");
				ps6=conn.prepareStatement(zlqd);			
				for(int i=0;i<zl.length;i++){
					String zlnr = zl[i].substring(0,zl[i].indexOf("|"));
					String zlsfkysc = zl[i].substring(zl[i].length()-1,zl[i].length());
//					
					String xh = qysdsUtil.getSequence(conn);
					ps6.setString(1, xh);
					ps6.setString(2, basqwsh);
					ps6.setString(3, zlnr);
					ps6.setString(4, jsjdm);
					ps6.setString(5, ud.getYhid());
					ps6.setString(6, ud.getYhid());
					ps6.setString(7, "1");
					ps6.setString(8, zlsfkysc);
					ps6.addBatch();
				}
				ps6.executeBatch();
			}
			
			if("1".equals(basx13BForm.getCzlx())){
				String updatesql = "UPDATE sfdb.sf_jl_qysdsjmsba_13b SET YWCBABS = '0' where BASQWSH = '"+basx13BForm.getBasqwsh()+"'";
				
				ps7 = conn.prepareStatement(updatesql);
				ps7.execute();
			}
			//设置处理标示为保存状态
			basx13BForm.setClbs("1");
			
			basx13BForm = (Basx13BForm)doShow(vo);
			
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
			if (ps5 != null) {
				ps5.close();
			}
			if (ps6 != null) {
				ps6.close();
			}
			if (ps7 != null) {
				ps7.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ApplicationException("数据库保存记录失败！" + basx13BForm.getJsjdm()	+ ":" + ex.getMessage());
		} finally {		
			SfDBResource.freeConnection(conn);
		}
		return basx13BForm;
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
		
		Basx13BForm basx13BForm = (Basx13BForm) vo.getData();
		UserData ud = (UserData)vo.getUserData();
		Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		PreparedStatement ps3 = null;
		PreparedStatement ps4 = null;
		PreparedStatement ps5 = null;
		PreparedStatement ps6 = null;
		PreparedStatement ps7 = null;
		QysdsUtil qysdsUtil = new QysdsUtil();
		//备案申请文书号
		String basqwsh = basx13BForm.getBasqwsh();
		//备案申请编号
		String basqbh = basx13BForm.getBasqbh();
		//计算机代码
		String jsjdm = basx13BForm.getJsjdm();
		//备案年度
		String band = basx13BForm.getBand();
		//减免税类别代码
		String jmbasxdm = "013B";
		//减免税政策执行情况
		String jmszczxqk=QysdsUtil.strNotNull(basx13BForm.getJmszczxqk())?basx13BForm.getJmszczxqk():"";
		//起始时间
		String qsrq=QysdsUtil.strNotNull(basx13BForm.getQsrq())?"to_date('"+basx13BForm.getQsrq()+"','yyyy-mm-dd')":"null";
		//截止时间
		String jzrq=QysdsUtil.strNotNull(basx13BForm.getJzrq())?"to_date('"+basx13BForm.getJzrq()+"','yyyy-mm-dd')":"null";
		//设置减免税额
		String bajmse = QysdsUtil.strNotNull(basx13BForm.getBajmse())?basx13BForm.getBajmse():"";
		String bajmbl = QysdsUtil.strNotNull(basx13BForm.getBajmbl())?basx13BForm.getBajmbl():"";
		Timestamp time = new Timestamp(new Date().getTime());
		try {
			conn = SfDBResource.getConnection();
			String sql = "";
			String delzbsql = "";
			String zbsql = "";
			String delhissql = "";
			String hissql = "";

				//更新主表
				sql = "update SFDB.SF_JL_QYSDSJMSBAJL set  sqzt='4',band = '"+band+"',bajmse='"+bajmse+"',bajmbl='"+bajmbl+"',fhwjmc=?,qsrq="+qsrq+
				",jzrq="+jzrq+",lrr='"+ud.getYhid()+"',lrrq=to_timestamp('"+time+"', 'yyyy-mm-dd hh24:mi:ss.ff') " +
				"where basqwsh='"+basx13BForm.getBasqwsh()+"'";
			
			
			//插入子表
			//根据被投资企业税务登记证号、计算机代码将业务表的数据删除
			delzbsql = "DELETE sfdb.sf_jl_qysdsjmsba_13b t WHERE t.jsjdm = '"+
					basx13BForm.getJsjdm()+"' AND t.btzqyswdjzh = '"+basx13BForm.getBtzqyswdjzh()+"'"; 
			//根据被投资企业税务登记证号、计算机代码、每一个投资年度保存记录到业务表,保存时将 sfdb.sf_jl_qysdsjmsba_13b.ywcbabs置‘0’，
			zbsql = "INSERT INTO sfdb.sf_jl_qysdsjmsba_13b (XH,BASQWSH,JSJDM,BAND,SWJGZZJGDM,GXJSLYDM,BTZQYJSJDM," +
					"BTZQYSWDJZH,BTZQYMC,BTZQYSSD,TZND,TZE,DNKDKE,DKE,JZE,YWCBABS,SHBJ,CJR,CJRQ,LRR,LRRQ)VALUES(?,'"+
					basqwsh+"','"+jsjdm+"','"+band+"',(select t.swjgzzjgdm from djdb.dj_jl_jbsj t WHERE t.jsjdm = '"+jsjdm+"'),'"+
					basx13BForm.getGxjslydm()+"','"+basx13BForm.getBtzqyjsjdm()+"','"+basx13BForm.getBtzqyswdjzh()+"','"+
					basx13BForm.getBtzqymc()+"','"+basx13BForm.getBtzqyssd()+"',?,?,?,?,?,?,'0','"+
					ud.getYhid()+"',sysdate,'"+ud.getYhid()+"',to_timestamp('"+time+"','yyyy-mm-dd hh24:mi:ss.ff'))";
			//根据备案申请文书号删除历史表数据
			delhissql = "DELETE sfdb.sf_jl_qysdsjmsba_13b_his t WHERE t.basqwsh = '"+basx13BForm.getBasqwsh()+"'";
			//将业务表的数据插入到 SF_JL_QYSDSJMSBA_13B_HIS
			hissql = "INSERT INTO sfdb.sf_jl_qysdsjmsba_13b_his (XH,BASQWSH,JSJDM,BAND,SWJGZZJGDM,GXJSLYDM,BTZQYJSJDM," +
					"BTZQYSWDJZH,BTZQYMC,BTZQYSSD,TZND,TZE,DNKDKE,DKE,JZE,YWCBABS,SHBJ,CJR,CJRQ,LRR,LRRQ)VALUES(?,'"+
					basqwsh+"','"+jsjdm+"','"+band+"',(select t.swjgzzjgdm from djdb.dj_jl_jbsj t WHERE t.jsjdm = '"+jsjdm+"'),'"+
					basx13BForm.getGxjslydm()+"','"+basx13BForm.getBtzqyjsjdm()+"','"+basx13BForm.getBtzqyswdjzh()+"','"+
					basx13BForm.getBtzqymc()+"','"+basx13BForm.getBtzqyssd()+"',?,?,?,?,?,?,'0','"+
					ud.getYhid()+"',sysdate,'"+ud.getYhid()+"',to_timestamp('"+time+"','yyyy-mm-dd hh24:mi:ss.ff'))";
			
			//主表操作
			
			ps = conn.prepareStatement(sql);
			ps.setString(1,jmszczxqk);
			ps.executeQuery();
			//子表操作
			//根据被投资企业税务登记证号、计算机代码将业务表的数据删除
			
			ps1 = conn.prepareStatement(delzbsql);
			ps1.execute();
			//根据被投资企业税务登记证号、计算机代码、每一个投资年度保存记录到业务表
			
			ps2 = conn.prepareStatement(zbsql);
			String[] tze = basx13BForm.getTze().split(";");
			String[] kdke = basx13BForm.getKdke().split(";");
			String[] sjdke = basx13BForm.getSjdke().split(";");
			String[] jzdke = basx13BForm.getJzdke().split(";");
			String[] bjkz = basx13BForm.getBjkz().split(";");
			int tznd = 2006;
			for(int i=0;i<tze.length;i++){
				String xh = qysdsUtil.getSequence(conn);
				
				ps2.setString(1, xh);
				ps2.setString(2, String.valueOf(tznd+i));
				ps2.setString(3, tze[i]);
				ps2.setString(4, kdke[i]);
				ps2.setString(5, sjdke[i]);
				ps2.setString(6, jzdke[i]);
				ps2.setString(7, bjkz[i]);
				ps2.addBatch();
			}
			ps2.executeBatch();
			//根据备案申请文书号删除历史表数据
			
			ps3 = conn.prepareStatement(delhissql);
			ps3.execute();
			//将业务表的数据插入到 SF_JL_QYSDSJMSBA_13B_HIS
			
			ps4 = conn.prepareStatement(hissql);
			for(int i=0;i<tze.length;i++){
				String xh = qysdsUtil.getSequence(conn);
				
				ps4.setString(1, xh);
				ps4.setString(2, String.valueOf(tznd+i));
				ps4.setString(3, tze[i]);
				ps4.setString(4, kdke[i]);
				ps4.setString(5, sjdke[i]);
				ps4.setString(6, jzdke[i]);
				ps4.setString(7, bjkz[i]);
				ps4.addBatch();
			}
			ps4.executeBatch();
			
			
			//更新资料清单表
			if(!"".equals(basx13BForm.getZl())){
				//删除该备案申请文书号的资料清单表
				String delzlqd = "DELETE SFDB.SF_JL_QYSDSJMSBAJLZLQD WHERE BASQWSH = '"+basqwsh+"'";
				
				ps5=conn.prepareStatement(delzlqd);
				ps5.execute();
				//插入该备案申请文书号的资料清单
				String zlqd = "INSERT INTO SFDB.SF_JL_QYSDSJMSBAJLZLQD(XH,BASQWSH,ZLQD,SWJGZZJGDM,CJR,CJRQ,LRR,LRRQ,SFSHTG,SFKYSC)" +
						"VALUES(?,?,?,(select t.swjgzzjgdm from djdb.dj_jl_jbsj t WHERE t.jsjdm = ?),?,sysdate,?," +
						"to_timestamp('"+time+"','yyyy-mm-dd hh24:mi:ss.ff'),?,?)";
				String[] zl = basx13BForm.getZl().split(";");
				ps6=conn.prepareStatement(zlqd);			
				for(int i=0;i<zl.length;i++){
					String zlnr = zl[i].substring(0,zl[i].indexOf("|"));
					String zlsfkysc = zl[i].substring(zl[i].length()-1,zl[i].length());
//					
					String xh = qysdsUtil.getSequence(conn);
					ps6.setString(1, xh);
					ps6.setString(2, basqwsh);
					ps6.setString(3, zlnr);
					ps6.setString(4, jsjdm);
					ps6.setString(5, ud.getYhid());
					ps6.setString(6, ud.getYhid());
					ps6.setString(7, "1");
					ps6.setString(8, zlsfkysc);
					ps6.addBatch();
				}
				ps6.executeBatch();
			}
			
			if("1".equals(basx13BForm.getCzlx())){
				String updatesql = "UPDATE sfdb.sf_jl_qysdsjmsba_13b SET YWCBABS = '0' where BASQWSH = '"+basx13BForm.getBasqwsh()+"'";
				
				ps7 = conn.prepareStatement(updatesql);
				ps7.execute();
			}
			//设置处理标示为保存状态
			basx13BForm.setClbs("1");
			
			basx13BForm = (Basx13BForm)doShow(vo);
			
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
			if (ps5 != null) {
				ps5.close();
			}
			if (ps6 != null) {
				ps6.close();
			}
			if (ps7 != null) {
				ps7.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ApplicationException("数据库保存记录失败！" + basx13BForm.getJsjdm()	+ ":" + ex.getMessage());
		} finally {		
			SfDBResource.freeConnection(conn);
		}
		return basx13BForm;
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
		
		Basx13BForm basx13BForm = (Basx13BForm) vo.getData();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		PreparedStatement ps1 = null;
		ResultSet rs1 = null;
		try {
			conn = SfDBResource.getConnection();
			String sql = "";                            
			if("4".equals(basx13BForm.getCzlx())){
				sql = "SELECT t.jsjdm,t.btzqyswdjzh,t.btzqyjsjdm,t.btzqymc,t.gxjslydm,b.gxjslymc," +
				" S.QSRQ,S.JZRQ,S.BAJMBL,S.BAJMSE,S.FHWJMC,S.JSJDM,S.BASQBH,S.BAND,S.CJR,to_char(S.CJRQ,'yyyymmdd')cjrq,S.zfsm " +
				" FROM sfdb.sf_jl_qysdsjmsba_13b_his T,sfdb.sf_jl_qysdsjmsbajl S,dmdb.sf_dm_gxjsly B " +
				" WHERE T.BASQWSH = S.BASQWSH AND T.Gxjslydm = B.Gxjslydm AND T.BASQWSH = '"+basx13BForm.getBasqwsh()+"' AND ROWNUM = 1";
			}else{
				sql = "SELECT t.jsjdm,t.btzqyswdjzh,t.btzqyjsjdm,t.btzqymc,t.gxjslydm,b.gxjslymc," +
				" S.QSRQ,S.JZRQ,S.BAJMBL,S.BAJMSE,S.FHWJMC,S.JSJDM,S.BASQBH,S.BAND,S.CJR,to_char(S.CJRQ,'yyyymmdd')cjrq,S.zfsm " +
				" FROM sfdb.sf_jl_qysdsjmsba_13B T,sfdb.sf_jl_qysdsjmsbajl S,dmdb.sf_dm_gxjsly B " +
				" WHERE T.BASQWSH = S.BASQWSH AND T.Gxjslydm = B.Gxjslydm AND T.BASQWSH = '"+basx13BForm.getBasqwsh()+"' AND ROWNUM = 1";
			}
			
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				basx13BForm.setJsjdm(rs.getString("jsjdm"));
				basx13BForm.setBtzqyswdjzh(rs.getString("btzqyswdjzh"));
				basx13BForm.setBtzqyjsjdm(rs.getString("btzqyjsjdm"));
				basx13BForm.setBtzqymc(rs.getString("btzqymc"));
				basx13BForm.setGxjslydm(rs.getString("gxjslydm"));
				basx13BForm.setGxjslymc(rs.getString("gxjslymc"));
				basx13BForm.setJmszczxqk(rs.getString("FHWJMC"));
				basx13BForm.setJsjdm(rs.getString("JSJDM"));
				basx13BForm.setBasqbh(rs.getString("BASQBH"));
				basx13BForm.setBajmbl(rs.getString("BAJMBL"));
				basx13BForm.setBajmse(rs.getString("BAJMSE"));
				basx13BForm.setMr_band(rs.getString("BAND"));
				basx13BForm.setMr_lrr(rs.getString("CJR"));
				basx13BForm.setMr_lrrq(rs.getString("CJRQ"));
				basx13BForm.setZfsm(rs.getString("ZFSM"));
				//对日期进行处理，只取2001-01-01
				if(!"".equals(rs.getString("QSRQ"))){
					basx13BForm.setQsrq(rs.getString("QSRQ").substring(0, 10));
				}else{
					basx13BForm.setQsrq(rs.getString("QSRQ"));
				}
				if(!"".equals(rs.getString("JZRQ"))){
					basx13BForm.setJzrq(rs.getString("JZRQ").substring(0, 10));
				}else{
					basx13BForm.setJzrq(rs.getString("JZRQ"));
				}
				
			}
			String tze = "";
			String kdke = "";
			String sjdke = "";
			String jzdke = "";
			String bjkz = "";
			StringBuffer gz = new StringBuffer();
			int band = Integer.parseInt(basx13BForm.getMr_band());
			for(int n=2006;n<=band;n++){
				gz.append("select '"+n+"' tznd from dual");
				if(n != band){
					gz.append("  union  ");
				}
			}
			String tzxxsql = "";
			if("4".equals(basx13BForm.getCzlx())){
				tzxxsql = "select b.tznd,a.tze,a.dnkdke,a.dke,a.jze,nvl(a.ywcbabs,1) ywcbabs " +
				"from sfdb.sf_jl_qysdsjmsba_13b_his a ,("+gz.toString()+") b  " +
				"where a.tznd(+)=b.tznd  and a.basqwsh='"+basx13BForm.getBasqwsh()+"' order by b.tznd ";
			}else{
				tzxxsql = "select b.tznd,a.tze,a.dnkdke,a.dke,a.jze,nvl(a.ywcbabs,1) ywcbabs " +
				"from sfdb.sf_jl_qysdsjmsba_13b a ,("+gz.toString()+") b  " +
				"where a.tznd(+)=b.tznd  and a.basqwsh='"+basx13BForm.getBasqwsh()+"' order by b.tznd ";
			}
			
			ps1 = conn.prepareStatement(tzxxsql);
		
			rs1 = ps1.executeQuery();
			while(rs1.next()){
				tze = tze + rs1.getString("TZE")+";";
				kdke = kdke + rs1.getString("DNKDKE")+";";
				sjdke = sjdke + rs1.getString("DKE")+";";
				jzdke = jzdke + rs1.getString("JZE")+";";
				bjkz = bjkz + rs1.getString("YWCBABS")+";";
			}
		
			basx13BForm.setTze(tze.substring(0, tze.length()-1));
			basx13BForm.setKdke(kdke.substring(0, kdke.length()-1));
			basx13BForm.setSjdke(sjdke.substring(0, sjdke.length()-1));
			basx13BForm.setJzdke(jzdke.substring(0, jzdke.length()-1));
			basx13BForm.setBjkz(bjkz.substring(0, bjkz.length()-1));
			//设置处理标示为保存状态
			basx13BForm.setClbs("1");
			basx13BForm = (Basx13BForm)doShow(vo);
			
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
			throw new ApplicationException("数据库查询记录失败！" + basx13BForm.getJsjdm()	+ ":" + ex.getMessage());
		} finally {		
			SfDBResource.freeConnection(conn);
		}
		return basx13BForm;
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
		
		Basx13BForm basx13BForm = (Basx13BForm) vo.getData();
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
		String s = basx13BForm.getShzl();
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
			boolean sqzt = QysdsUtil.checkSqzt(basx13BForm.getBasqwsh(), conn);
			if (!sqzt) {
				throw new ApplicationException("此减免税备案申请已被纳税人撤回！");
			}
			//更新主表状态为审核通过或审核未通过
			QysdsUtil.updateSqzt(basx13BForm.getBasqwsh(), basx13BForm.getSqzt(), ud.getYhid(),conn);
			
			//更新子表状态为审核通过或审核未通过
			StringBuffer shzbSql = new StringBuffer("UPDATE sfdb.sf_jl_qysdsjmsba_13b t SET  ");
			if("4".equals(basx13BForm.getSqzt())){
				shzbSql.append(" t.shbj ='0' ,t.YWCBABS = '0' ");
			}else{
				shzbSql.append(" t.shbj ='1' ,t.YWCBABS = '1' ");
			}
			shzbSql.append(" ,t.lrr = '"+ud.getYhid()+"',t.lrrq = sysdate WHERE t.basqwsh = '"+basx13BForm.getBasqwsh()+"'");
			
			ps = conn.prepareStatement(shzbSql.toString());
			ps.execute();
			
			//更新资料清单表中资料是否审核通过
			SQL = "UPDATE SFDB.SF_JL_QYSDSJMSBAJLZLQD A SET A.SFSHTG = (SELECT B.SFSHTG FROM (" +SQL+
					") B WHERE A.XH = B.XH)WHERE A.BASQWSH = '"+basx13BForm.getBasqwsh()+"'";
			
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
			throw new ApplicationException("数据库审核记录失败！" + basx13BForm.getJsjdm()	+ ":" + ex.getMessage());
		} finally {
			SfDBResource.freeConnection(conn);
		}
		return basx13BForm;
	}
	
	
	private Object doPrint(VOPackage vo) throws BaseException {
		Connection conn = null;
		PreparedStatement printPst = null;
		ResultSet rs = null;
		Basx13BForm basx13BForm = (Basx13BForm) vo.getData();
		String zlqd = "";
		String sql = "SELECT A.NSRMC,T.JSJDM,S.JMBASXMC,T.QSRQ,T.JZRQ,T.BAJMSEHBL,T.FHWJMC,Q.ZLQD " +
				"FROM SFDB.SF_JL_QYSDSJMSBAJL T,SFDB.SF_JL_QYSDSJMSBAJLZLQD Q,DJDB.DJ_JL_JBSJ A,DMDB.SF_DM_JMBASXDM S " +
				"WHERE T.BASQWSH = Q.BASQWSH(+) AND T.JSJDM = A.JSJDM  AND T.JMBASXDM = S.JMBASXDM AND  T.BASQWSH = '"+ basx13BForm.getBasqwsh() + "'";
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

		return basx13BForm;
	}
}
