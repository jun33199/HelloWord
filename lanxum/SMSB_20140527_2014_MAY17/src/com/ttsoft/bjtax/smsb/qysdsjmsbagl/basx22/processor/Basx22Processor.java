package com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx22.processor;

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
import com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx22.web.Basx22Form;
import com.ttsoft.bjtax.smsb.qysdsjmsbagl.util.QysdsUtil;
import com.ttsoft.bjtax.smsb.qysdsjmsbagl.vo.DmVo;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

public class Basx22Processor implements Processor{
	
	/**
	 * ʵ��Processor�ӿ�
	 * 
	 * @param vo
	 *            ҵ�����
	 * @return Object VOPackage������
	 * @throws BaseException
	 *             ҵ���쳣 1 ���������Ĳ������Ͳ���ʱ�׳� 2 �����õ�ҵ�񷽷��׳�ҵ���쳣ʱ���ϴ����׳�
	 *             �����쳣�׳���EJB��process��������
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
		case CodeConstant.SMSB_UPDATEACTION:
			result = doCheck(vo);
			break;
		case CodeConstant.SMSB_PRINTACTION:
			result = doPrint(vo);
			break;
		default:
			throw new ApplicationException("�û�ִ����ϵͳ��֧�ֵķ�������.");
		}

		return result;
	}
	
	
	
	/**
	 * doShow��ʼ������ҳ����ϢҪ��
	 * 
	 * @param vo
	 *            ҵ�����
	 * @return ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
	 * @throws BaseException
	 *             ��������������׳��쳣��Ϣ
	 */

	private Object doShow(VOPackage vo) throws BaseException {
		
		Basx22Form basx22Form = (Basx22Form) vo.getData();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		PreparedStatement ps1 = null;
		ResultSet rs1 = null;
		PreparedStatement ps2 = null;
		ResultSet rs2 = null;
		List list=new ArrayList();
		try {
			//Ϊ��ȡ��˰�˻�����Ϣ׼�� 
			//���UserData
			UserData ud = (UserData)vo.getUserData();
			//�����˰�˻�����Ϣ
			HashMap djMap = InterfaceDj.getDjInfo(basx22Form.getJsjdm(), ud);
			SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");
			System.out.println("=====getSwjgzzjgdm======>>>>" + jbsj.getSwjgzzjgdm());
			
			conn = SfDBResource.getConnection();	
			String sql = "select JNJPJSGZXMDM,JNJPJSGZXMMC from DMDB.SF_DM_JNJPJSGZXM order by JNJPJSGZXMMC";
			
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {	
				String dm=rs.getString("JNJPJSGZXMDM");
				String mc=rs.getString("JNJPJSGZXMMC");
				DmVo dmvo=new DmVo();
				dmvo.setDm(dm);
				dmvo.setMc(mc);
				list.add(dmvo);		
			}
			basx22Form.setJnjpjsgzxmList(list);
			
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
			sb.append(" from djdb.dj_jl_jbsj a where a.jsjdm='"+basx22Form.getJsjdm()+"'");
			
			
			ps1 = conn.prepareStatement(sb.toString());
			rs1 = ps1.executeQuery();
			while (rs1.next()){
				basx22Form.setJsjdm(rs1.getString("JSJDM"));
				basx22Form.setNsrmc(rs1.getString("NSRMC"));
				basx22Form.setZgsws(rs1.getString("ZGSWS"));
				basx22Form.setJjlx(rs1.getString("JJLX"));
				basx22Form.setSshy(rs1.getString("SSHY"));
				basx22Form.setLxr(rs1.getString("LXR"));
				basx22Form.setLxdh(rs1.getString("LXDH"));
			}
			String zl = "";
			String zlsql = "";
			//�ж��Ƿ��Ѿ������
			if("0".equals(basx22Form.getClbs())){
				//zlsql = "select t.zlqdmc,t.sfkysc from dmdb.sf_dm_bazlqddm t WHERE t.jmbasxdm = '0220'   and t.zfbs = '0' ORDER BY T.zlqddm";
				zlsql = "select t.zlqdmc,t.sfkysc,t.zlqddm from dmdb.sf_dm_bazlqddm t WHERE t.jmbasxdm = '0220'   and t.zfbs = '0' ORDER BY T.zlqddm";
			}else{
				//�ж��Ƿ�Ϊ��˺Ͳ鿴
				if("3".equals(basx22Form.getCzlx())){
					zlsql = "select t.zlqd,t.xh from SFDB.SF_JL_QYSDSJMSBAJLZLQD t WHERE t.BASQWSH = '"+basx22Form.getBasqwsh()+"' ORDER BY T.xh";
				}else if("4".equals(basx22Form.getCzlx())){
					zlsql = "select t.zlqd,t.sfshtg from SFDB.SF_JL_QYSDSJMSBAJLZLQD t WHERE t.BASQWSH = '"+basx22Form.getBasqwsh()+"' ORDER BY T.sfshtg";
				}else{
					zlsql = "select t.zlqd,t.sfkysc from SFDB.SF_JL_QYSDSJMSBAJLZLQD t WHERE t.BASQWSH = '"+basx22Form.getBasqwsh()+"' ORDER BY T.SFKYSC DESC";
				}
				
			}
			
			ps2 = conn.prepareStatement(zlsql);
			rs2 = ps2.executeQuery();
			while (rs2.next()) {
				//�ж��Ƿ��Ѿ������
				if("0".equals(basx22Form.getClbs())){
					
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
					//�ж��Ƿ�Ϊ��˺Ͳ鿴
					if("3".equals(basx22Form.getCzlx())){
						zl =zl + rs2.getString("ZLQD")+"|"+rs2.getString("XH")+";";
					}else if("4".equals(basx22Form.getCzlx())){
						zl =zl + rs2.getString("ZLQD")+"|"+rs2.getString("SFSHTG")+";";
					}else{
						zl =zl + rs2.getString("ZLQD")+"|"+rs2.getString("SFKYSC")+";";
					}
					
				}
			}
			basx22Form.setZl(zl.toString().substring(0,(zl.toString().length()-1)));
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
			throw new ApplicationException("���ݿ��ѯ��¼ʧ�ܣ�" + basx22Form.getJsjdm()	+ ":" + ex.getMessage());
		} finally {		
			SfDBResource.freeConnection(conn);
		}

		return basx22Form;
	}
	
	/**
	 * doSave�������ҳ����ϢҪ��
	 * 
	 * @param vo
	 *            ҵ�����
	 * @return ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
	 * @throws BaseException
	 *             ��������������׳��쳣��Ϣ
	 */

	private Object doSave(VOPackage vo) throws BaseException {
		
		Basx22Form basx22Form = (Basx22Form) vo.getData();
		UserData ud = (UserData)vo.getUserData();
		Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		PreparedStatement ps3 = null;
		QysdsUtil qysdsUtil = new QysdsUtil();
		//�������������
		String basqwsh = basx22Form.getBasqwsh();
		//����������
		String basqbh = basx22Form.getBasqbh();
		//���������
		String jsjdm = basx22Form.getJsjdm();
		//�������
		String band = basx22Form.getBand();
		//����˰������
		String jmbasxdm = "0220";
		//����˰����ִ�����
		String jmszczxqk=QysdsUtil.strNotNull(basx22Form.getJmszczxqk())?basx22Form.getJmszczxqk():"";
		//��ʼʱ��
		String qsrq=QysdsUtil.strNotNull(basx22Form.getQsrq())?"to_date('"+basx22Form.getQsrq()+"','yyyy-mm-dd')":"null";
		//��ֹʱ��
		String jzrq=QysdsUtil.strNotNull(basx22Form.getJzrq())?"to_date('"+basx22Form.getJzrq()+"','yyyy-mm-dd')":"null";
		//���ü���˰��
		String bajmse = QysdsUtil.strNotNull(basx22Form.getBajmse())?basx22Form.getBajmse():"";
		String bajmbl = QysdsUtil.strNotNull(basx22Form.getBajmbl())?basx22Form.getBajmbl():"";
		Timestamp time = new Timestamp(new Date().getTime());
		try {
			conn = SfDBResource.getConnection();
			String sql = "";
			String zbsql = "";
			if("1".equals(basx22Form.getClbs())){
				//��������
				sql = "update SFDB.SF_JL_QYSDSJMSBAJL set bajmse='"+bajmse+"',bajmbl='"+bajmbl+"',fhwjmc=?,qsrq="+qsrq+
				",jzrq="+jzrq+",lrr='"+ud.getYhid()+"',lrrq=to_timestamp('"+time+"', 'yyyy-mm-dd hh24:mi:ss.ff') " +
				"where basqwsh='"+basx22Form.getBasqwsh()+"'";
				//�����ӱ�
				zbsql = "UPDATE sfdb.sf_jl_qysdsjmsba_22 SET JNJPJSGZXMDM='"+basx22Form.getJnjpjsgzxmlx()+"',BAND='"+basx22Form.getBand()+"',DYBZLMC='"+basx22Form.getDybzlmc()+"',DYBRQ='"+basx22Form.getDybrq()+"',MZQSND='"+basx22Form.getMzqsnd()+"',MZZZND='"+basx22Form.getMzzznd()+"',JBZSQSND='"+basx22Form.getJbzsqsnd()
					   +"',ZRHTXM='"+basx22Form.getZRHTXM()
					   +"',ZRHTXMYH='"+basx22Form.getZRHTXMYH()
					   +"',ZRHTXMYHWJ='"+basx22Form.getZRHTXMYHWJ()
					   +"',JBZSZZND='"+basx22Form.getJbzszznd()
					   +"',SHBJ='0',LRR='"+ud.getYhid()
					   +"',LRRQ=sysdate where basqwsh ='"+basx22Form.getBasqwsh()+"'";
			}else{
				//��������
				sql = " insert into SFDB.SF_JL_QYSDSJMSBAJL(BASQWSH,BASQBH,JSJDM,BAND,JMBASXDM,SZDM,SWJGZZJGDM,SQZT,TJR,TJSJ,SHR,SHSJ," +
						"SQLXDM,BAJMSE,BAJMBL,FHWJMC,QSRQ,JZRQ,CJR,CJRQ,LRR,LRRQ)values('"+basqwsh+"','"+basqbh+"','"+jsjdm+"','"+band+"','"+
						jmbasxdm+"','30','"+ud.getSsdwdm()+"','4','"+ud.getYhid()+"',sysdate,'"+ud.getYhid()+"',sysdate,'1','"+bajmse+
						"','"+bajmbl+"',?,"+qsrq+","+jzrq+",'"+ud.getYhid()+"',sysdate,'"+ud.getYhid()+
						"',to_timestamp('"+time+"','yyyy-mm-dd hh24:mi:ss.ff'))";
				
				//��ȡ���ݿ�����
				basx22Form.setXh(qysdsUtil.getSequence(conn));
                String xh = basx22Form.getXh();
				//�����ӱ�
				zbsql = "INSERT INTO sfdb.sf_jl_qysdsjmsba_22 (XH,BASQWSH,JNJPJSGZXMDM,JSJDM,BAND,SWJGZZJGDM,DYBZLMC,DYBRQ,MZQSND,MZZZND,JBZSQSND,JBZSZZND,ZRHTXM,ZRHTXMYH,ZRHTXMYHWJ,SHBJ,CJR,CJRQ,LRR,LRRQ) " +
						"VALUES('"+xh+"','"+basx22Form.getBasqwsh()+"','"+basx22Form.getJnjpjsgzxmlx()+"','"+jsjdm+"','"+band+"',(select t.swjgzzjgdm from djdb.dj_jl_jbsj t WHERE t.jsjdm = '"+jsjdm+"'),'"
						+basx22Form.getDybzlmc()+"','"+basx22Form.getDybrq()+"','"
						+basx22Form.getMzqsnd()+"','"+basx22Form.getMzzznd()+"','"
						+basx22Form.getJbzsqsnd()+"','"+basx22Form.getJbzszznd()+"','"
						+basx22Form.getZRHTXM()+"','"+basx22Form.getZRHTXMYH()+"','"+basx22Form.getZRHTXMYHWJ()
						+"','0','"+ud.getYhid()+"',sysdate,'"+ud.getYhid()+"',sysdate)";
			}
			//�������
			ps = conn.prepareStatement(sql);
			ps.setString(1,jmszczxqk);
			ps.executeQuery();
			
			//�ӱ����

			System.out.println("����Sql:"+zbsql);
			ps1 = conn.prepareStatement(zbsql);
			ps1.executeQuery();
			
			//���������嵥��
			if(!"".equals(basx22Form.getZl())){
				//ɾ���ñ�����������ŵ������嵥��
				String delzlqd = "DELETE SFDB.SF_JL_QYSDSJMSBAJLZLQD WHERE BASQWSH = '"+basqwsh+"'";
				
				ps2=conn.prepareStatement(delzlqd);
				ps2.execute();
				//����ñ�����������ŵ������嵥
				String zlqd = "INSERT INTO SFDB.SF_JL_QYSDSJMSBAJLZLQD(XH,BASQWSH,ZLQD,SWJGZZJGDM,CJR,CJRQ,LRR,LRRQ,SFSHTG,SFKYSC)" +
						"VALUES(?,?,?,(select t.swjgzzjgdm from djdb.dj_jl_jbsj t WHERE t.jsjdm = ?),?,sysdate,?," +
						"to_timestamp('"+time+"','yyyy-mm-dd hh24:mi:ss.ff'),?,?)";
				String[] zl = basx22Form.getZl().split(";");
				ps3=conn.prepareStatement(zlqd);			
				for(int i=0;i<zl.length;i++){
					String zlnr = zl[i].substring(0,zl[i].indexOf("|"));
					String zlsfkysc = zl[i].substring(zl[i].length()-1,zl[i].length());
					
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
			//���ô����ʾΪ����״̬
			basx22Form.setClbs("1");
			
			basx22Form = (Basx22Form)doShow(vo);
			
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
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ApplicationException("���ݿⱣ���¼ʧ�ܣ�" + basx22Form.getJsjdm()	+ ":" + ex.getMessage());
		} finally {		
			SfDBResource.freeConnection(conn);
		}
		return basx22Form;
	}
	
	/**
	 * doSave�������ҳ����ϢҪ��
	 * 
	 * @param vo
	 *            ҵ�����
	 * @return ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
	 * @throws BaseException
	 *             ��������������׳��쳣��Ϣ
	 */

	private Object doBg(VOPackage vo) throws BaseException {
		
		Basx22Form basx22Form = (Basx22Form) vo.getData();
		UserData ud = (UserData)vo.getUserData();
		Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		PreparedStatement ps3 = null;
		QysdsUtil qysdsUtil = new QysdsUtil();
		//�������������
		String basqwsh = basx22Form.getBasqwsh();
		
		//���������
		String jsjdm = basx22Form.getJsjdm();
		
		//����˰����ִ�����
		String jmszczxqk=QysdsUtil.strNotNull(basx22Form.getJmszczxqk())?basx22Form.getJmszczxqk():"";
		//��ʼʱ��
		String qsrq=QysdsUtil.strNotNull(basx22Form.getQsrq())?"to_date('"+basx22Form.getQsrq()+"','yyyy-mm-dd')":"null";
		//��ֹʱ��
		String jzrq=QysdsUtil.strNotNull(basx22Form.getJzrq())?"to_date('"+basx22Form.getJzrq()+"','yyyy-mm-dd')":"null";
		//���ü���˰��
		String bajmse = QysdsUtil.strNotNull(basx22Form.getBajmse())?basx22Form.getBajmse():"";
		String bajmbl = QysdsUtil.strNotNull(basx22Form.getBajmbl())?basx22Form.getBajmbl():"";
		Timestamp time = new Timestamp(new Date().getTime());
		try {
			conn = SfDBResource.getConnection();
			String sql = "";
			String zbsql = "";
			
				//��������
				sql = "update SFDB.SF_JL_QYSDSJMSBAJL set  sqzt='4',bajmse='"+bajmse+"',bajmbl='"+bajmbl+"',fhwjmc=?,qsrq="+qsrq+
				",jzrq="+jzrq+",lrr='"+ud.getYhid()+"',lrrq=to_timestamp('"+time+"', 'yyyy-mm-dd hh24:mi:ss.ff') " +
				"where basqwsh='"+basx22Form.getBasqwsh()+"'";
				//�����ӱ�
				zbsql = "UPDATE sfdb.sf_jl_qysdsjmsba_22 SET JNJPJSGZXMDM='"+basx22Form.getJnjpjsgzxmlx()+"',BAND='"+basx22Form.getBand()+"',DYBZLMC='"+basx22Form.getDybzlmc()+"',DYBRQ='"+basx22Form.getDybrq()+"',MZQSND='"+basx22Form.getMzqsnd()+"',MZZZND='"+basx22Form.getMzzznd()+"',JBZSQSND='"+basx22Form.getJbzsqsnd()
				       +"',JBZSZZND='"+basx22Form.getJbzszznd()
				       +"',ZRHTXM='"+basx22Form.getZRHTXM()
					   +"',ZRHTXMYH='"+basx22Form.getZRHTXMYH()
					   +"',ZRHTXMYHWJ='"+basx22Form.getZRHTXMYHWJ()
				       +"',SHBJ='0',LRR='"+ud.getYhid()+"',LRRQ=sysdate where basqwsh ='"+basx22Form.getBasqwsh()+"'";
			
			//�������

			ps = conn.prepareStatement(sql);
			ps.setString(1,jmszczxqk);
			ps.executeQuery();
			
			//�ӱ����

			System.out.println(zbsql);
			ps1 = conn.prepareStatement(zbsql);
			ps1.executeQuery();
			
			//���������嵥��
			if(!"".equals(basx22Form.getZl())){
				//ɾ���ñ�����������ŵ������嵥��
				String delzlqd = "DELETE SFDB.SF_JL_QYSDSJMSBAJLZLQD WHERE BASQWSH = '"+basqwsh+"'";
				
				ps2=conn.prepareStatement(delzlqd);
				ps2.execute();
				//����ñ�����������ŵ������嵥
				String zlqd = "INSERT INTO SFDB.SF_JL_QYSDSJMSBAJLZLQD(XH,BASQWSH,ZLQD,SWJGZZJGDM,CJR,CJRQ,LRR,LRRQ,SFSHTG,SFKYSC)" +
						"VALUES(?,?,?,(select t.swjgzzjgdm from djdb.dj_jl_jbsj t WHERE t.jsjdm = ?),?,sysdate,?," +
						"to_timestamp('"+time+"','yyyy-mm-dd hh24:mi:ss.ff'),?,?)";
				String[] zl = basx22Form.getZl().split(";");
				ps3=conn.prepareStatement(zlqd);			
				for(int i=0;i<zl.length;i++){
					String zlnr = zl[i].substring(0,zl[i].indexOf("|"));
					String zlsfkysc = zl[i].substring(zl[i].length()-1,zl[i].length());
					
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
			//���ô����ʾΪ����״̬
			basx22Form.setClbs("1");
			
			basx22Form = (Basx22Form)doShow(vo);
			
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
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ApplicationException("���ݿⱣ���¼ʧ�ܣ�" + basx22Form.getJsjdm()	+ ":" + ex.getMessage());
		} finally {		
			SfDBResource.freeConnection(conn);
		}
		return basx22Form;
	}
	
	
	/**
	 * doQuery��ѯ����ҳ����ϢҪ��
	 * 
	 * @param vo
	 *            ҵ�����
	 * @return ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
	 * @throws BaseException
	 *             ��������������׳��쳣��Ϣ
	 */

	private Object doQuery(VOPackage vo) throws BaseException {
		
		Basx22Form basx22Form = (Basx22Form) vo.getData();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = SfDBResource.getConnection();	
			String sql = "SELECT T.JNJPJSGZXMDM,B.JNJPJSGZXMMC,T.DYBZLMC,T.DYBRQ,T.MZQSND,T.MZZZND,T.JBZSQSND,T.JBZSZZND," +
					" S.QSRQ,S.JZRQ,S.BAJMBL,S.BAJMSE,S.FHWJMC,S.JSJDM,S.BASQBH,S.BAND,S.CJR,to_char(S.CJRQ,'yyyymmdd')cjrq,S.zfsm " +
					",ZRHTXM,ZRHTXMYH,ZRHTXMYHWJ"+
					" FROM sfdb.sf_jl_qysdsjmsba_22 T,sfdb.sf_jl_qysdsjmsbajl S,DMDB.SF_DM_JNJPJSGZXM B " +
					" WHERE T.BASQWSH = S.BASQWSH AND T.JNJPJSGZXMDM = B.JNJPJSGZXMDM AND T.BASQWSH = '"+basx22Form.getBasqwsh()+"'";
			
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				basx22Form.setJnjpjsgzxmdm(rs.getString("JNJPJSGZXMDM"));
				basx22Form.setJnjpjsgzxmmc(rs.getString("JNJPJSGZXMMC"));
				basx22Form.setDybzlmc(rs.getString("DYBZLMC"));
                basx22Form.setDybrq(rs.getString("DYBRQ"));
                basx22Form.setMzqsnd(rs.getString("MZQSND"));
                basx22Form.setMzzznd(rs.getString("MZZZND"));
                basx22Form.setJbzsqsnd(rs.getString("JBZSQSND"));
                basx22Form.setJbzszznd(rs.getString("JBZSZZND"));
				basx22Form.setJmszczxqk(rs.getString("FHWJMC"));
				basx22Form.setJsjdm(rs.getString("JSJDM"));
				basx22Form.setBasqbh(rs.getString("BASQBH"));
				basx22Form.setBajmbl(rs.getString("BAJMBL"));
				basx22Form.setBajmse(rs.getString("BAJMSE"));
				basx22Form.setZRHTXM(rs.getString("ZRHTXM"));
				basx22Form.setZRHTXMYH(rs.getString("ZRHTXMYH"));
				basx22Form.setZRHTXMYHWJ(rs.getString("ZRHTXMYHWJ"));
				basx22Form.setMr_band(rs.getString("BAND"));
				basx22Form.setMr_lrr(rs.getString("CJR"));
				basx22Form.setMr_lrrq(rs.getString("CJRQ"));
				basx22Form.setZfsm(rs.getString("ZFSM"));
				//�����ڽ��д���ֻȡ2001-01-01
				if(!"".equals(rs.getString("QSRQ"))){
					basx22Form.setQsrq(rs.getString("QSRQ").substring(0, 10));
				}else{
					basx22Form.setQsrq(rs.getString("QSRQ"));
				}
				if(!"".equals(rs.getString("JZRQ"))){
					basx22Form.setJzrq(rs.getString("JZRQ").substring(0, 10));
				}else{
					basx22Form.setJzrq(rs.getString("JZRQ"));
				}
			}
			//���ô����ʾΪ����״̬
			basx22Form.setClbs("1");
			basx22Form = (Basx22Form)doShow(vo);
			
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ApplicationException("���ݿ��ѯ��¼ʧ�ܣ�" + basx22Form.getJsjdm()	+ ":" + ex.getMessage());
		} finally {		
			SfDBResource.freeConnection(conn);
		}
		return basx22Form;
	}
	
	
	/**
	 * doCheck����-���ܻ�ܾ�����
	 * 
	 * @param vo
	 *            ҵ�����
	 * @return ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
	 * @throws BaseException
	 *             ��������������׳��쳣��Ϣ
	 */

	private Object doCheck(VOPackage vo) throws BaseException {
		
		Basx22Form basx22Form = (Basx22Form) vo.getData();
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
		String s = basx22Form.getShzl();
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
			//��鵱ǰ����˰���������Ƿ�Ϊ����δ��˻��ύδ���
			boolean sqzt = QysdsUtil.checkSqzt(basx22Form.getBasqwsh(), conn);
			if (!sqzt) {
				throw new ApplicationException("�˼���˰���������ѱ���˰�˳��أ�");
			}
			//��������״̬Ϊ���ͨ�������δͨ��
			QysdsUtil.updateSqzt(basx22Form.getBasqwsh(), basx22Form.getSqzt(), ud.getYhid(),conn);
			
			//�����ӱ�״̬Ϊ���ͨ�������δͨ��
			StringBuffer shzbSql = new StringBuffer("UPDATE sfdb.sf_jl_qysdsjmsba_22 t SET t.shbj = ");
			if("4".equals(basx22Form.getSqzt())){
				shzbSql.append("'0'");
			}else{
				shzbSql.append("'1'");
			}
			shzbSql.append(" ,t.lrr = '"+ud.getYhid()+"',t.lrrq = sysdate ");
			shzbSql.append(" ,ZRHTXM='"+basx22Form.getZRHTXM());
			shzbSql.append("',ZRHTXMYH='"+basx22Form.getZRHTXMYH());
			shzbSql.append("',ZRHTXMYHWJ='"+basx22Form.getZRHTXMYHWJ());
			shzbSql.append("'  WHERE t.basqwsh = '"+basx22Form.getBasqwsh()+"'");
			ps = conn.prepareStatement(shzbSql.toString());
			ps.execute();
			
			//���������嵥���������Ƿ����ͨ��
			SQL = "UPDATE SFDB.SF_JL_QYSDSJMSBAJLZLQD A SET A.SFSHTG = (SELECT B.SFSHTG FROM (" +SQL+
					") B WHERE A.XH = B.XH)WHERE A.BASQWSH = '"+basx22Form.getBasqwsh()+"'";
			
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
			throw new ApplicationException("���ݿ���˼�¼ʧ�ܣ�" + basx22Form.getJsjdm()	+ ":" + ex.getMessage());
		} finally {
			SfDBResource.freeConnection(conn);
		}
		return basx22Form;
	}
	
	
	private Object doPrint(VOPackage vo) throws BaseException {
		Connection conn = null;
		PreparedStatement printPst = null;
		ResultSet rs = null;
		Basx22Form basx22Form = (Basx22Form) vo.getData();
		String zlqd = "";
		String sql = "SELECT A.NSRMC,T.JSJDM,S.JMBASXMC,T.QSRQ,T.JZRQ,T.BAJMSEHBL,T.FHWJMC,Q.ZLQD " +
				"FROM SFDB.SF_JL_QYSDSJMSBAJL T,SFDB.SF_JL_QYSDSJMSBAJLZLQD Q,DJDB.DJ_JL_JBSJ A,DMDB.SF_DM_JMBASXDM S " +
				"WHERE T.BASQWSH = Q.BASQWSH(+) AND T.JSJDM = A.JSJDM  AND T.JMBASXDM = S.JMBASXDM AND  T.BASQWSH = '"+ basx22Form.getBasqwsh() + "'";
		try {
			conn = SfDBResource.getConnection();
			printPst = conn.prepareStatement(sql);
			rs = printPst.executeQuery();
			while(rs.next()){
				
			}
		} catch (Exception ex) {
			throw new ApplicationException("��ȡ��ӡ��Ϣ����");
		} finally {
			// �ͷ����ݿ�����
			SfDBResource.freeConnection(conn);
		}

		return basx22Form;
	}
}
