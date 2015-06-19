package com.lscdz.qysds.application.jmsba2014.base.access;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import yangjian.frame.dao.FrameCommonAccess;
import yangjian.frame.util.FrameException;
import com.lscdz.hlwdsj.hxzgframe.Yh;
import com.lscdz.qysds.application.jmsba2014.Jmsba2014Contant;
import com.lscdz.qysds.application.jmsba2014.base.vo.JmsbaBaseVo;
import com.lscdz.qysds.application.jmsba2014.base.vo.JmsbaZlqdVo;
import com.lscdz.qysds.application.jmsba2014.util.QysdsJmsbaUtil;
import com.lscdz.qysds.common.codetable.CodeTableKey;
import com.lscdz.qysds.common.util.QysdsHelperUtil;
import com.lscdz.util.codetable.CodeTableManager;

public class JmsbaBaseAccessExt {
	private  final SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * ��������ֱ�ӱ����������ݺ������嵥
	 * @param conn
	 * @param baseVo
	 * @param ud
	 * @return
	 * @throws FrameException
	 */
	public boolean doSave(Connection conn, JmsbaBaseVo baseVo, Yh ud)throws FrameException {
		boolean jmsjlSaved = false;
		// Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		QysdsJmsbaUtil qysdsJmsbaUtil = new QysdsJmsbaUtil();
		// �������������
		String basqwsh = baseVo.getBasqwsh();
		// ����������
		String basqbh = baseVo.getBasqbh();
		// ���������
		String jsjdm = baseVo.getJsjdm();
		// �������
		String band = baseVo.getBand();
		// ����������,0-�¶�,1-����,2-���
		String bbqlx = baseVo.getBbqlx();
		// ������ʽ,1-Ԥ�ɱ���,2-��ɱ���
		String bafs = baseVo.getBafs();
		// �ں� ����BBLX���ֲ�ͬ�ڵ��걨,����,BBQLX=2���ںſ�ʼΪ1
		String qh = baseVo.getQh();
		// �����Ż��ڼ���
		//Timestamp xsyhqjq = baseVo.getXsyhqjq();
		String xsyhqjq = baseVo.getXsyhqjq();
		// �����Ż��ڼ�ֹ
		//Timestamp xsyhqjz = baseVo.getXsyhqjz();
		String xsyhqjz = baseVo.getXsyhqjz();
		// ˰������ʱ����
		String sksssqq = baseVo.getSksssqq();
		// ˰������ʱ��ֹ
		String sksssqz = baseVo.getSksssqz();		
		// ����˰������		
		String jmbasxdm = baseVo.getJmbasxdm();
		// ��Ҫ���������ļ����ĺ�
		String zyzcyjwjjwh=baseVo.getZyzcyjwjjwh();
		// ��������ʸ����׼�ļ���֤�飩���ĺţ���ţ�
		String jyxgzgdpzwjjwh=baseVo.getJyxgzgdpzwjjwh();		
		//�й����˵��
		String ygqksm=baseVo.getYgqksm();
		/*//�ļ���֤�飩��Ч����ʼ����
		Timestamp wjyxqqsrq=baseVo.getWjyxqqsrq();
		//�ļ���֤�飩��Ч�ڽ�ֹ����
		Timestamp wjyxqjzrq=baseVo.getWjyxqjzrq();*/
		String wjyxqqsrq=baseVo.getWjyxqqsrq();
		String wjyxqjzrq=baseVo.getWjyxqjzrq();
		//��������˰��
		BigDecimal bajmse = baseVo.getBajmse();
		//�����������
		BigDecimal bajmbl = baseVo.getBajmbl();

		Timestamp time = new Timestamp(new Date().getTime());
		try {
			// conn = ResourceManager.getConnection();
			String sql = "";
			String sqzt="";
			//���ũ���֡�������ҵ ��������״̬
			if(baseVo.getJmbasxdm().equals(Jmsba2014Contant.QYSDS_JMSBA_BASX_0120)){
				sqzt=Jmsba2014Contant.QYSDS_JMSBA_SQZT_TSZD_CODE;
			}else{
				sqzt=Jmsba2014Contant.QYSDS_JMSBA_SQZT_SHYTG_CODE;
			}
			// ��������
			sql = " insert into SFDB.SF_JL_QYSDSJMSBAJL_2014(BASQWSH,BASQBH,JSJDM,BAND,BBQLX,BAFS,QH,XSYHQJQ,XSYHQJZ,SKSSSQQ,SKSSSQZ,JMBASXDM,SZDM,SWJGZZJGDM,ZYZCYJWJJWH,JYXGZGDPZWJJWH,"
					+"SQZT,TJR,TJSJ,SHR,SHSJ,SQLXDM,YGQKSM,WJYXQQSRQ,WJYXQJZRQ,CJR,CJRQ,LRR,LRRQ,BAJMSE,BAJMBL)values('"
					+ basqwsh+ "','"
					+ basqbh+ "','"
					+ jsjdm+ "','"
					+ band+ "','"
					+ bbqlx+ "','"
					+ bafs+ "','"
					+ qh+ "',"
					+ "?,"    
					+ "?"
					+ ",to_timestamp('"+ sksssqq+ "','yyyy-mm-dd')"
					+ ",to_timestamp('"+ sksssqz+ "','yyyy-mm-dd')"+ ",'"					
					+ jmbasxdm
					+ "','30','"
					+ ud.getSsdwdm()+"','"
					+ zyzcyjwjjwh+"','"
					+ jyxgzgdpzwjjwh
					+ "','"+sqzt+"','"
					+ ud.getYhid()
					+ "',sysdate,'"
					+ ud.getYhid()
					+ "',sysdate,'1','"
					+ ygqksm+"',"
					+ "?,"
					+ "?,'"
					+ ud.getYhid()
					+ "',sysdate,'"
					+ ud.getYhid()
					+ "',sysdate,"
					+ "?,"
					+ "?)";

			// �������

			ps = conn.prepareStatement(sql);
			if(xsyhqjq==null){
				ps.setTimestamp(1,null);
			}else{
				ps.setTimestamp(1,new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse(xsyhqjq).getTime()));
			}
			if(xsyhqjz==null){
				ps.setTimestamp(2,null);
			}else{
				ps.setTimestamp(2, new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse(xsyhqjz).getTime()));
			}
			if(wjyxqqsrq==null){
				ps.setTimestamp(3,null);
			}else{
				ps.setTimestamp(3, new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse(wjyxqqsrq).getTime()));
			}
			if(wjyxqjzrq==null){
				ps.setTimestamp(4,null);
			}else{
				ps.setTimestamp(4, new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse(wjyxqjzrq).getTime()));
			}
			
			ps.setBigDecimal(5, bajmse);
			ps.setBigDecimal(6, bajmbl);
			ps.executeQuery();

			// ���������嵥��
			if (baseVo.getZlqdList().size()!=0) {
				// ɾ���ñ�����������ŵ������嵥��
				String delzlqd = "DELETE SFDB.SF_JL_QYSDSJMSBAJLZLQD_2014 WHERE BASQWSH = '"
						+ basqwsh + "'";
				ps1 = conn.prepareStatement(delzlqd);
				ps1.execute();
				// ����ñ�����������ŵ������嵥
				String zlqdsql = "INSERT INTO SFDB.SF_JL_QYSDSJMSBAJLZLQD_2014(XH,BASQWSH,ZLQD,SWJGZZJGDM,CJR,CJRQ,LRR,LRRQ,SFSHTG,SFKYSC)"
						+ "VALUES(?,?,?,(select t.swjgzzjgdm from djdb.dj_jl_jbsj t WHERE t.jsjdm = ?),?,sysdate,?,"
						+ "to_timestamp('"
						+ time
						+ "','yyyy-mm-dd hh24:mi:ss.ff'),?,?)";
				ps2 = conn.prepareStatement(zlqdsql);
				
			
				for (int i = 0; i < baseVo.getZlqdList().size(); i++) {
					JmsbaZlqdVo zlqdVo=(JmsbaZlqdVo)baseVo.getZlqdList().get(i);
					String zlnr = zlqdVo.getZlqdmc();
					String zlsfkysc = zlqdVo.getSfkysc();
					String xh = qysdsJmsbaUtil.getSequence(conn);
					ps2.setString(1, xh);
					ps2.setString(2, basqwsh);
					ps2.setString(3, zlnr);
					ps2.setString(4, jsjdm);
					ps2.setString(5, ud.getYhid());
					ps2.setString(6, ud.getYhid());
					ps2.setString(7, zlqdVo.getSfshtg());
					ps2.setString(8, zlsfkysc);
					ps2.addBatch();
				}
				ps2.executeBatch();
			}
			jmsjlSaved = true;
		} catch (Exception ex) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			ex.printStackTrace();
			throw new FrameException("���ݿⱣ���¼ʧ�ܣ�" + baseVo.getJsjdm() + ":"
					+ ex.getMessage());
		} finally {
			QysdsHelperUtil.dbResClose(ps, null, null);
			QysdsHelperUtil.dbResClose(ps1, null, null);
			QysdsHelperUtil.dbResClose(ps2, null, null);
		}
		return jmsjlSaved;
	}
	
	/**
	 * �����������ݺ������嵥
	 * @param conn
	 * @param baseVo
	 * @param ud
	 * @return
	 * @throws FrameException
	 */
	public boolean doUpdate(Connection conn, JmsbaBaseVo baseVo, Yh ud)throws FrameException {
		boolean jmsjlUpdateed = false;
		// Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		QysdsJmsbaUtil qysdsJmsbaUtil = new QysdsJmsbaUtil();
		// �������������
		String basqwsh = baseVo.getBasqwsh();
		// ���������
		String jsjdm = baseVo.getJsjdm();
		// �����Ż��ڼ���
		String xsyhqjq = baseVo.getXsyhqjq();
		// �����Ż��ڼ�ֹ
		String xsyhqjz = baseVo.getXsyhqjz();
		// ��Ҫ���������ļ����ĺ�
		String zyzcyjwjjwh=baseVo.getZyzcyjwjjwh();
		// ��������ʸ����׼�ļ���֤�飩���ĺţ���ţ�
		String jyxgzgdpzwjjwh=baseVo.getJyxgzgdpzwjjwh();		
		//�й����˵��
		String ygqksm=baseVo.getYgqksm();
		/*//�ļ���֤�飩��Ч����ʼ����
		Timestamp wjyxqqsrq=baseVo.getWjyxqqsrq();
		//�ļ���֤�飩��Ч�ڽ�ֹ����
		Timestamp wjyxqjzrq=baseVo.getWjyxqjzrq();*/
		String wjyxqqsrq=baseVo.getWjyxqqsrq();
		String wjyxqjzrq=baseVo.getWjyxqjzrq();
		//��������˰��
		BigDecimal bajmse = baseVo.getBajmse();
		//�����������
		BigDecimal bajmbl = baseVo.getBajmbl();
		Timestamp time = new Timestamp(new Date().getTime());
		try {
			// conn = ResourceManager.getConnection();
			String sql = "";
			//������������
			sql="update SFDB.SF_JL_QYSDSJMSBAJL_2014 set XSYHQJQ=?,XSYHQJZ=?,ZYZCYJWJJWH=?,JYXGZGDPZWJJWH=?,YGQKSM=?,WJYXQQSRQ=?,WJYXQJZRQ=?,LRR=?,LRRQ=sysdate,BAJMSE=?,BAJMBL=?,sqzt=?,shr=?,shsj=? where BASQWSH=?";
			// �������

			ps = conn.prepareStatement(sql);
			if(xsyhqjq==null){
				ps.setTimestamp(1,null);
			}else{
				ps.setTimestamp(1,new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse(xsyhqjq).getTime()));
			}
			if(xsyhqjz==null){
				ps.setTimestamp(2,null);
			}else{
				ps.setTimestamp(2, new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse(xsyhqjz).getTime()));
			}
			
			ps.setString(3, zyzcyjwjjwh);
			ps.setString(4, jyxgzgdpzwjjwh);
			ps.setString(5, ygqksm);
			if(wjyxqqsrq==null){
				ps.setTimestamp(6,null);
			}else{
				ps.setTimestamp(6, new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse(wjyxqqsrq).getTime()));
			}
			if(wjyxqjzrq==null){
				ps.setTimestamp(7,null);
			}else{
				ps.setTimestamp(7, new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse(wjyxqjzrq).getTime()));
			}
			
			ps.setString(8, ud.getYhid());
			ps.setBigDecimal(9, bajmse);
			ps.setBigDecimal(10, bajmbl);
			ps.setString(11, baseVo.getSqzt());
			if(baseVo.getSqzt().equals(Jmsba2014Contant.QYSDS_JMSBA_SQZT_SHYTG_CODE)){
				ps.setString(12, ud.getYhid());//�����
				ps.setTimestamp(13, FrameCommonAccess.getDBDate());//�������
			}else{
				ps.setString(12, "");
				ps.setTimestamp(13, null);
			}
			ps.setString(14, basqwsh);
			ps.executeUpdate();

			// ���������嵥��
			if (baseVo.getZlqdList().size()!=0) {
				// ɾ���ñ�����������ŵ������嵥��
				String delzlqd = "DELETE SFDB.SF_JL_QYSDSJMSBAJLZLQD_2014 WHERE BASQWSH = '"
						+ basqwsh + "'";
				ps1 = conn.prepareStatement(delzlqd);
				ps1.execute();
				// ����ñ�����������ŵ������嵥
				String zlqdsql = "INSERT INTO SFDB.SF_JL_QYSDSJMSBAJLZLQD_2014(XH,BASQWSH,ZLQD,SWJGZZJGDM,CJR,CJRQ,LRR,LRRQ,SFSHTG,SFKYSC)"
						+ "VALUES(?,?,?,(select t.swjgzzjgdm from djdb.dj_jl_jbsj t WHERE t.jsjdm = ?),?,sysdate,?,"
						+ "to_timestamp('"
						+ time
						+ "','yyyy-mm-dd hh24:mi:ss.ff'),?,?)";
				ps2 = conn.prepareStatement(zlqdsql);
				
			
				for (int i = 0; i < baseVo.getZlqdList().size(); i++) {
					JmsbaZlqdVo zlqdVo=(JmsbaZlqdVo)baseVo.getZlqdList().get(i);
					String zlnr = zlqdVo.getZlqdmc();
					String zlsfkysc = zlqdVo.getSfkysc();
					String xh = qysdsJmsbaUtil.getSequence(conn);
					ps2.setString(1, xh);
					ps2.setString(2, basqwsh);
					ps2.setString(3, zlnr);
					ps2.setString(4, jsjdm);
					ps2.setString(5, ud.getYhid());
					ps2.setString(6, ud.getYhid());
					ps2.setString(7, zlqdVo.getSfshtg());
					ps2.setString(8, zlsfkysc);
					ps2.addBatch();
				}
				ps2.executeBatch();
			}
			jmsjlUpdateed = true;
		} catch (Exception ex) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			ex.printStackTrace();
			throw new FrameException("���ݿ���¼�¼ʧ�ܣ�" + baseVo.getJsjdm() + ":"
					+ ex.getMessage());
		} finally {
			QysdsHelperUtil.dbResClose(ps, null, null);
			QysdsHelperUtil.dbResClose(ps1, null, null);
			QysdsHelperUtil.dbResClose(ps2, null, null);
		}
		return jmsjlUpdateed;
	}
	
	
	/**
	 * ����������ʱ�����������ݺ������嵥
	 * @param conn
	 * @param baseVo
	 * @param ud
	 * @return
	 * @throws FrameException
	 */
	public boolean doSaveWs(Connection conn, JmsbaBaseVo baseVo, Yh ud)throws FrameException {
		boolean jmsjlSaved = false;
		// Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		QysdsJmsbaUtil qysdsJmsbaUtil = new QysdsJmsbaUtil();
		// �������������
		String basqwsh = baseVo.getBasqwsh();
		// ����������
		String basqbh = baseVo.getBasqbh();
		// ���������
		String jsjdm = baseVo.getJsjdm();
		// �������
		String band = baseVo.getBand();
		// ����������,0-�¶�,1-����,2-���
		String bbqlx = baseVo.getBbqlx();
		// ������ʽ,1-Ԥ�ɱ���,2-��ɱ���
		String bafs = baseVo.getBafs();
		// �ں� ����BBLX���ֲ�ͬ�ڵ��걨,����,BBQLX=2���ںſ�ʼΪ1
		String qh = baseVo.getQh();
		// �����Ż��ڼ���
		String xsyhqjq = baseVo.getXsyhqjq();
		// �����Ż��ڼ�ֹ
		String xsyhqjz = baseVo.getXsyhqjz();
		// ˰������ʱ����
		String sksssqq = baseVo.getSksssqq();
		// ˰������ʱ��ֹ
		String sksssqz = baseVo.getSksssqz();		
		// ����˰������		
		String jmbasxdm = baseVo.getJmbasxdm();
		// ��Ҫ���������ļ����ĺ�
		String zyzcyjwjjwh=baseVo.getZyzcyjwjjwh();
		// ��������ʸ����׼�ļ���֤�飩���ĺţ���ţ�
		String jyxgzgdpzwjjwh=baseVo.getJyxgzgdpzwjjwh();		
		//�й����˵��
		String ygqksm=baseVo.getYgqksm();
		/*//�ļ���֤�飩��Ч����ʼ����
		Timestamp wjyxqqsrq=baseVo.getWjyxqqsrq();
		//�ļ���֤�飩��Ч�ڽ�ֹ����
		Timestamp wjyxqjzrq=baseVo.getWjyxqjzrq();*/
		String wjyxqqsrq=baseVo.getWjyxqqsrq();
		String wjyxqjzrq=baseVo.getWjyxqjzrq();
		//��������˰��
		BigDecimal bajmse = baseVo.getBajmse();
		//�����������
		BigDecimal bajmbl = baseVo.getBajmbl();

		Timestamp time = new Timestamp(new Date().getTime());
		try {
			// conn = ResourceManager.getConnection();
			String sql = "";

			// ��������
			sql = " insert into SFDB.SF_JL_QYSDSJMSBAJL_2014(BASQWSH,BASQBH,JSJDM,BAND,BBQLX,BAFS,QH,XSYHQJQ,XSYHQJZ,SKSSSQQ,SKSSSQZ,JMBASXDM,SZDM,SWJGZZJGDM,ZYZCYJWJJWH,JYXGZGDPZWJJWH,"
					+"SQZT,TJR,TJSJ,SHR,SHSJ,SQLXDM,YGQKSM,WJYXQQSRQ,WJYXQJZRQ,CJR,CJRQ,LRR,LRRQ,BAJMSE,BAJMBL)values('"
					+ basqwsh+ "','"
					+ basqbh+ "','"
					+ jsjdm+ "','"
					+ band+ "','"
					+ bbqlx+ "','"
					+ bafs+ "','"
					+ qh+ "',"
					+ "?,"    
					+ "?"
					+ ",to_timestamp('"+ sksssqq+ "','yyyy-mm-dd')"
					+ ",to_timestamp('"+ sksssqz+ "','yyyy-mm-dd')"+ ",'"					
					+ jmbasxdm
					+ "','30','"
					+ ud.getSsdwdm()+"','"
					+ zyzcyjwjjwh+"','"
					+ jyxgzgdpzwjjwh
					+ "','"+baseVo.getSqzt()+"','"
					+ ud.getYhid()
					+ "',sysdate,'"
					+ ud.getYhid()
					+ "',sysdate,'"+baseVo.getSqlxdm()+"','"
					+ ygqksm+"',"
					+ "?,"
					+ "?,'"
					+ ud.getYhid()
					+ "',sysdate,'"
					+ ud.getYhid()
					+ "',sysdate,"
					+ "?,"
					+ "?)";

			// �������

			ps = conn.prepareStatement(sql);
			if(xsyhqjq==null){
				ps.setTimestamp(1,null);
			}else{
				ps.setTimestamp(1,new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse(xsyhqjq).getTime()));
			}
			if(xsyhqjz==null){
				ps.setTimestamp(2,null);
			}else{
				ps.setTimestamp(2, new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse(xsyhqjz).getTime()));
			}
			if(wjyxqqsrq==null){
				ps.setTimestamp(3,null);
			}else{
				ps.setTimestamp(3, new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse(wjyxqqsrq).getTime()));
			}
			if(wjyxqjzrq==null){
				ps.setTimestamp(4,null);
			}else{
				ps.setTimestamp(4, new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse(wjyxqjzrq).getTime()));
			}
			
			ps.setBigDecimal(5, bajmse);
			ps.setBigDecimal(6, bajmbl);
			ps.executeQuery();

			// ���������嵥��
			if (baseVo.getZlqdList().size()!=0) {
				// ɾ���ñ�����������ŵ������嵥��
				String delzlqd = "DELETE SFDB.SF_JL_QYSDSJMSBAJLZLQD_2014 WHERE BASQWSH = '"
						+ basqwsh + "'";
				ps1 = conn.prepareStatement(delzlqd);
				ps1.execute();
				// ����ñ�����������ŵ������嵥
				String zlqdsql = "INSERT INTO SFDB.SF_JL_QYSDSJMSBAJLZLQD_2014(XH,BASQWSH,ZLQD,SWJGZZJGDM,CJR,CJRQ,LRR,LRRQ,SFSHTG,SFKYSC)"
						+ "VALUES(?,?,?,(select t.swjgzzjgdm from djdb.dj_jl_jbsj t WHERE t.jsjdm = ?),?,sysdate,?,"
						+ "to_timestamp('"
						+ time
						+ "','yyyy-mm-dd hh24:mi:ss.ff'),?,?)";
				ps2 = conn.prepareStatement(zlqdsql);
				
			
				for (int i = 0; i < baseVo.getZlqdList().size(); i++) {
					JmsbaZlqdVo zlqdVo=(JmsbaZlqdVo)baseVo.getZlqdList().get(i);
					String zlnr = zlqdVo.getZlqdmc();
					String zlsfkysc = zlqdVo.getSfkysc();
					String xh = qysdsJmsbaUtil.getSequence(conn);
					ps2.setString(1, xh);
					ps2.setString(2, basqwsh);
					ps2.setString(3, zlnr);
					ps2.setString(4, jsjdm);
					ps2.setString(5, ud.getYhid());
					ps2.setString(6, ud.getYhid());
					ps2.setString(7, "0");
					ps2.setString(8, zlsfkysc);
					ps2.addBatch();
				}
				ps2.executeBatch();
			}
			jmsjlSaved = true;
		} catch (Exception ex) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			ex.printStackTrace();
			throw new FrameException("���ݿⱣ���¼ʧ�ܣ�" + baseVo.getJsjdm() + ":"
					+ ex.getMessage());
		} finally {
			QysdsHelperUtil.dbResClose(ps, null, null);
			QysdsHelperUtil.dbResClose(ps1, null, null);
			QysdsHelperUtil.dbResClose(ps2, null, null);
		}
		return jmsjlSaved;
	}
	/**
	 * ���������޸��ѱ���Ϊ�ύ���������ݺ������嵥
	 * �����޸��������ύδ��˵�����
	 * @param conn
	 * @param baseVo
	 * @param ud
	 * @return
	 * @throws FrameException
	 */
	public boolean doUpdateWs(Connection conn, JmsbaBaseVo baseVo, Yh ud)throws FrameException {
		boolean jmsjlUpdateed = false;
		// Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		QysdsJmsbaUtil qysdsJmsbaUtil = new QysdsJmsbaUtil();
		// �������������
		String basqwsh = baseVo.getBasqwsh();
		// ���������
		String jsjdm = baseVo.getJsjdm();
		// �����Ż��ڼ���
		String xsyhqjq = baseVo.getXsyhqjq();
		// �����Ż��ڼ�ֹ
		String xsyhqjz = baseVo.getXsyhqjz();
		// ��Ҫ���������ļ����ĺ�
		String zyzcyjwjjwh=baseVo.getZyzcyjwjjwh();
		// ��������ʸ����׼�ļ���֤�飩���ĺţ���ţ�
		String jyxgzgdpzwjjwh=baseVo.getJyxgzgdpzwjjwh();		
		//�й����˵��
		String ygqksm=baseVo.getYgqksm();
		/*//�ļ���֤�飩��Ч����ʼ����
		Timestamp wjyxqqsrq=baseVo.getWjyxqqsrq();
		//�ļ���֤�飩��Ч�ڽ�ֹ����
		Timestamp wjyxqjzrq=baseVo.getWjyxqjzrq();*/
		String wjyxqqsrq=baseVo.getWjyxqqsrq();
		String wjyxqjzrq=baseVo.getWjyxqjzrq();
		//��������˰��
		BigDecimal bajmse = baseVo.getBajmse();
		//�����������
		BigDecimal bajmbl = baseVo.getBajmbl();
		Timestamp time = new Timestamp(new Date().getTime());
		try {
			// conn = ResourceManager.getConnection();
			String sql = "";
			//������������
			sql="update SFDB.SF_JL_QYSDSJMSBAJL_2014 set XSYHQJQ=?,XSYHQJZ=?,ZYZCYJWJJWH=?,JYXGZGDPZWJJWH=?,YGQKSM=?,WJYXQQSRQ=?,WJYXQJZRQ=?,LRR=?,LRRQ=sysdate,BAJMSE=?,BAJMBL=?,sqzt=?,shr=?,shsj=?,SQLXDM=? where BASQWSH=?";
			// �������
			ps = conn.prepareStatement(sql);
			if(xsyhqjq==null){
				ps.setTimestamp(1,null);
			}else{
				ps.setTimestamp(1,new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse(xsyhqjq).getTime()));
			}
			if(xsyhqjz==null){
				ps.setTimestamp(2,null);
			}else{
				ps.setTimestamp(2, new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse(xsyhqjz).getTime()));
			}
		
			ps.setString(3, zyzcyjwjjwh);
			ps.setString(4, jyxgzgdpzwjjwh);
			ps.setString(5, ygqksm);
			if(wjyxqqsrq==null){
				ps.setTimestamp(6,null);
			}else{
				ps.setTimestamp(6, new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse(wjyxqqsrq).getTime()));
			}
			if(wjyxqjzrq==null){
				ps.setTimestamp(7,null);
			}else{
				ps.setTimestamp(7, new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse(wjyxqjzrq).getTime()));
			}
			
			ps.setString(8, ud.getYhid());
			ps.setBigDecimal(9, bajmse);
			ps.setBigDecimal(10, bajmbl);
			ps.setString(11, baseVo.getSqzt());
			
			if(baseVo.getSqzt().equals(Jmsba2014Contant.QYSDS_JMSBA_SQZT_SHYTG_CODE)){
				ps.setString(12, ud.getYhid());//�����
				ps.setTimestamp(13, FrameCommonAccess.getDBDate());//�������
			}else{
				ps.setString(12, "");
				ps.setTimestamp(13, null);
			}
			ps.setString(14, baseVo.getSqlxdm());
			ps.setString(15, basqwsh);
			ps.executeUpdate();

			// ���������嵥��
			if (baseVo.getZlqdList().size()!=0) {
				// ɾ���ñ�����������ŵ������嵥��
				String delzlqd = "DELETE SFDB.SF_JL_QYSDSJMSBAJLZLQD_2014 WHERE BASQWSH = '"
						+ basqwsh + "'";
				ps1 = conn.prepareStatement(delzlqd);
				ps1.execute();
				// ����ñ�����������ŵ������嵥
				String zlqdsql = "INSERT INTO SFDB.SF_JL_QYSDSJMSBAJLZLQD_2014(XH,BASQWSH,ZLQD,SWJGZZJGDM,CJR,CJRQ,LRR,LRRQ,SFSHTG,SFKYSC)"
						+ "VALUES(?,?,?,(select t.swjgzzjgdm from djdb.dj_jl_jbsj t WHERE t.jsjdm = ?),?,sysdate,?,"
						+ "to_timestamp('"
						+ time
						+ "','yyyy-mm-dd hh24:mi:ss.ff'),?,?)";
				ps2 = conn.prepareStatement(zlqdsql);
				
			
				for (int i = 0; i < baseVo.getZlqdList().size(); i++) {
					JmsbaZlqdVo zlqdVo=(JmsbaZlqdVo)baseVo.getZlqdList().get(i);
					String zlnr = zlqdVo.getZlqdmc();
					String zlsfkysc = zlqdVo.getSfkysc();
					String xh = qysdsJmsbaUtil.getSequence(conn);
					ps2.setString(1, xh);
					ps2.setString(2, basqwsh);
					ps2.setString(3, zlnr);
					ps2.setString(4, jsjdm);
					ps2.setString(5, ud.getYhid());
					ps2.setString(6, ud.getYhid());
					ps2.setString(7, "0");
					ps2.setString(8, zlsfkysc);
					ps2.addBatch();
				}
				ps2.executeBatch();
			}
			jmsjlUpdateed = true;
		} catch (Exception ex) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			ex.printStackTrace();
			throw new FrameException("���ݿ���¼�¼ʧ�ܣ�" + baseVo.getJsjdm() + ":"
					+ ex.getMessage());
		} finally {
			QysdsHelperUtil.dbResClose(ps, null, null);
			QysdsHelperUtil.dbResClose(ps1, null, null);
			QysdsHelperUtil.dbResClose(ps2, null, null);
		}
		return jmsjlUpdateed;
	}
	
	/**
	 * �����������״̬
	 * @param conn
	 * @param baseVo
	 * @return
	 * @throws FrameException
	 */
	public boolean doChangeSqzt(Connection conn, JmsbaBaseVo baseVo)
			throws FrameException {
		Statement st = null;
		ResultSet rs = null;
		String sql = "select BASQWSH from SFDB.SF_JL_QYSDSJMSBAJL_2014 where BASQWSH='"
				+ baseVo.getBasqwsh() + "'";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				sql = "update SFDB.SF_JL_QYSDSJMSBAJL_2014   set SQZT='"
						+ baseVo.getSqzt() + "' where BASQWSH='"
						+ baseVo.getBasqwsh() + "'";
				return st.execute(sql);
			} else {
				throw new FrameException("�ü�¼�����ڣ��޷���������״̬");
			}
		} catch (SQLException e) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			e.printStackTrace();
			throw new FrameException("��������״̬ʧ�ܣ�" + e.getMessage());
		} finally {
			QysdsHelperUtil.dbResClose(st, rs, null);
		}
	}

	/**
	 * ��������嵥����Ƿ�ͨ��״̬������Ƿ�ͨ�������ʱ���޴����ϣ�0δ��ˣ�1���ʱ�У�2���ʱ��
	 */
	public boolean doChangeSfshtg(Connection conn, JmsbaBaseVo baseVo)
			throws FrameException {
		PreparedStatement ps = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = "select Xh,BASQWSH from SFDB.SF_JL_QYSDSJMSBAJLZLQD_2014 where BASQWSH='"
				+ baseVo.getBasqwsh() + "'";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs != null) {
				ps = conn.prepareStatement("update SFDB.SF_JL_QYSDSJMSBAJL_2014   set SFSHTG=? where BASQWSH=? and XH=?");
			} else {
				throw new FrameException("�ü�¼�����ڣ��޷����������嵥����Ƿ�ͨ��״̬");
			}
			while (rs.next()) {
				ps.setString(1, "0");// ͨ��
				ps.setString(2, rs.getString("BASQWSH"));
				ps.setString(3, rs.getString("XH"));
				ps.addBatch(sql);
			}
			ps.executeBatch();
		} catch (SQLException e) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			e.printStackTrace();
			throw new FrameException("���������嵥����Ƿ�ͨ��״̬ʧ�ܣ�" + e.getMessage());
		} finally {
			QysdsHelperUtil.dbResClose(st, rs, null);
			QysdsHelperUtil.dbResClose(ps, null, null);
		}
		return true;
	}
	/**
	 * ���ݱ�����������Ų�ѯ����������嵥�е�����ȫ������
	 * @param conn
	 * @param basqwsh
	 * @return
	 * @throws FrameException 
	 */
	public JmsbaBaseVo doQueryJmsbaxx(Connection conn,String basqwsh) throws FrameException{
		JmsbaBaseVo jmsbaBaseVo=null;
		//��ѯ��������
		jmsbaBaseVo=doQueryJmsbajl(conn,basqwsh);
		if(jmsbaBaseVo!=null){
			//��ѯ�����嵥������
			jmsbaBaseVo.setZlqdList(doQueryZlqd(conn,basqwsh));
		}
		return jmsbaBaseVo;
	}
	/**
	 * ���ݱ�����������Ų�ѯ��������
	 * @param conn
	 * @param basqwsh
	 * @return
	 * @throws FrameException 
	 */
	private JmsbaBaseVo doQueryJmsbajl(Connection conn,String basqwsh) throws FrameException{
		
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="select * from SFDB.SF_JL_QYSDSJMSBAJL_2014 where basqwsh=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, basqwsh);
			rs=ps.executeQuery();
			if(rs.next()){
				JmsbaBaseVo jmsbaBaseVo=new JmsbaBaseVo();
				jmsbaBaseVo.setBafs(rs.getString("BAFS"));
				jmsbaBaseVo.setBand(rs.getString("BAND"));
				jmsbaBaseVo.setBasqbh(rs.getString("BASQBH"));
				jmsbaBaseVo.setBasqwsh(rs.getString("BASQWSH"));
				jmsbaBaseVo.setBbqlx(rs.getString("BBQLX"));
				jmsbaBaseVo.setCjr(rs.getString("CJR"));
				jmsbaBaseVo.setCjrq(rs.getTimestamp("CJRQ"));
				jmsbaBaseVo.setJmbasxdm(rs.getString("JMBASXDM"));
				jmsbaBaseVo.setJmbasxmc(CodeTableManager.getNameByCode(CodeTableKey.SF_DM_JMBASXDM_2014, rs.getString("JMBASXDM")));
				jmsbaBaseVo.setJsjdm(rs.getString("JSJDM"));
				jmsbaBaseVo.setJyxgzgdpzwjjwh(rs.getString("JYXGZGDPZWJJWH"));
				jmsbaBaseVo.setLrr(rs.getString("LRR"));
				jmsbaBaseVo.setLrrq(rs.getTimestamp("LRRQ"));
				jmsbaBaseVo.setQh(rs.getString("QH"));
				jmsbaBaseVo.setSksssqq(rs.getTimestamp("SKSSSQQ")==null?"":timeFormat.format(rs.getTimestamp("SKSSSQQ")));
				jmsbaBaseVo.setSksssqz(rs.getTimestamp("SKSSSQZ")==null?"":timeFormat.format(rs.getTimestamp("SKSSSQZ")));
				jmsbaBaseVo.setSqlxdm(rs.getString("SQLXDM"));
				jmsbaBaseVo.setSwjgzzjgdm(rs.getString("SWJGZZJGDM"));
				jmsbaBaseVo.setSzdm(rs.getString("SZDM"));
				
				//jmsbaBaseVo.setXsyhqjq(new SimpleDateFormat("yyyy-MM-dd").format(rs.getTimestamp("XSYHQJQ")));
				jmsbaBaseVo.setXsyhqjq(rs.getTimestamp("XSYHQJQ")==null?"":timeFormat.format(rs.getTimestamp("XSYHQJQ")));
				//jmsbaBaseVo.setXsyhqjz(new SimpleDateFormat("yyyy-MM-dd").format(rs.getTimestamp("XSYHQJZ")));
				jmsbaBaseVo.setXsyhqjz(rs.getTimestamp("XSYHQJZ")==null?"":timeFormat.format(rs.getTimestamp("XSYHQJZ")));
				jmsbaBaseVo.setZyzcyjwjjwh(rs.getString("ZYZCYJWJJWH"));
				jmsbaBaseVo.setBajmbl(rs.getBigDecimal("BAJMBL"));
				jmsbaBaseVo.setBajmse(rs.getBigDecimal("BAJMSE"));
				jmsbaBaseVo.setHtr(rs.getString("HTR"));
				jmsbaBaseVo.setHtrq(rs.getTimestamp("HTRQ"));
				jmsbaBaseVo.setShr(rs.getString("SHR"));
				jmsbaBaseVo.setShsj(rs.getTimestamp("SHSJ"));
				jmsbaBaseVo.setSqzt(rs.getString("SQZT"));
				jmsbaBaseVo.setTjr(rs.getString("TJR"));
				jmsbaBaseVo.setTjsj(rs.getTimestamp("TJSJ"));

				//jmsbaBaseVo.setWjyxqjzrq(new SimpleDateFormat("yyyy-MM-dd").format(rs.getTimestamp("WJYXQJZRQ")));
				//jmsbaBaseVo.setWjyxqqsrq(new SimpleDateFormat("yyyy-MM-dd").format(rs.getTimestamp("WJYXQQSRQ")));
				jmsbaBaseVo.setWjyxqjzrq(rs.getTimestamp("WJYXQJZRQ")==null?"":timeFormat.format(rs.getTimestamp("WJYXQJZRQ")));
				jmsbaBaseVo.setWjyxqqsrq(rs.getTimestamp("WJYXQQSRQ")==null?"":timeFormat.format(rs.getTimestamp("WJYXQQSRQ")));
				jmsbaBaseVo.setYgqksm(rs.getString("YGQKSM"));
				jmsbaBaseVo.setZfr(rs.getString("ZFR"));
				jmsbaBaseVo.setZfrq(rs.getTimestamp("ZFRQ"));
				jmsbaBaseVo.setZfsm(rs.getString("ZFSM"));
				return jmsbaBaseVo;
				
			}
		} catch (SQLException e) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			e.printStackTrace();
			throw new FrameException("��ѯ����˰������������ʧ�ܣ�" + e.getMessage());
		}finally{
			QysdsHelperUtil.dbResClose(ps, rs, null);
		}		
		return null;
	}
	/**
	 * ���ݱ�����������Ų�ѯ�����嵥������
	 * @param conn
	 * @param basqwsh
	 * @return
	 * @throws FrameException 
	 */
	private List<JmsbaZlqdVo> doQueryZlqd(Connection conn,String basqwsh) throws FrameException{
		
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="select * from SFDB.SF_JL_QYSDSJMSBAJLZLQD_2014 where basqwsh=?";
		List<JmsbaZlqdVo> zlqdList=new ArrayList<JmsbaZlqdVo>();
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, basqwsh);
			rs=ps.executeQuery();
			while(rs.next()){
				JmsbaZlqdVo jmsbaZlqdVo=new JmsbaZlqdVo();
				jmsbaZlqdVo.setXh(rs.getString("xh"));
				jmsbaZlqdVo.setZlqdmc(rs.getString("ZLQD"));
				jmsbaZlqdVo.setSfkysc(rs.getString("SFKYSC"));
				jmsbaZlqdVo.setSfshtg(rs.getString("SFSHTG"));
				zlqdList.add(jmsbaZlqdVo);				
			}
		} catch (SQLException e) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			e.printStackTrace();
			throw new FrameException("��ѯ����˰���������嵥����ʧ�ܣ�" + e.getMessage());
		}finally{
			QysdsHelperUtil.dbResClose(ps, rs, null);
		}		
		return zlqdList;
	}
	
	/**
	 * ���ϱ�������
	 * @param conn
	 * @param baseVo
	 * @return
	 * @throws FrameException
	 */
	public boolean doDestory(Connection conn, JmsbaBaseVo baseVo,Yh ud)throws FrameException {
		Statement st = null;
		try {
			String zfsm="";//����˵��
			st = conn.createStatement();
			st.execute("update SFDB.SF_JL_QYSDSJMSBAJL_2014 set sqzt='6',zfr='"+ud.getYhid()+"',zfrq=sysdate,zfsm='"+zfsm+"' ,htr=null,htrq=null where basqwsh='"+baseVo.getBasqwsh()+"'");
		} catch (SQLException e) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			e.printStackTrace();
			throw new FrameException("���ϼ���˰������Ŀ��"+baseVo.getJmbasxmc()+"��ʧ�ܣ�");
		} finally {
			QysdsHelperUtil.dbResClose(st, null, null);
		}
		return true;
	}
	
	/**
	 * ��״̬��Ϊ�����״̬
	 * @param conn
	 * @param baseVo
	 * @param ud
	 * @return
	 * @throws FrameException
	 */
	public boolean doModify(Connection conn, JmsbaBaseVo baseVo,Yh ud)throws FrameException {
		Statement st = null;
		try {
			st = conn.createStatement();
			st.execute("update SFDB.SF_JL_QYSDSJMSBAJL_2014 set sqzt='2',zfr=null,zfrq=null,zfsm=null,htr='"+ud.getYhid()+"',htrq=sysdate where basqwsh='"+baseVo.getBasqwsh()+"'");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FrameException("�������˰������Ŀ��"+baseVo.getJmbasxmc()+"��ʧ�ܣ�");
		} finally {
			QysdsHelperUtil.dbResClose(st, null, null);
		}
		return true;
	}
	
	/**
	 * ��״̬��Ϊ���ͨ��
	 * @param conn
	 * @param baseVo
	 * @param ud
	 * @return
	 * @throws FrameException
	 */
	public boolean doAudit(Connection conn, JmsbaBaseVo baseVo,Yh ud)throws FrameException {
		Statement st = null;
		try {
			st = conn.createStatement();
			st.execute("update SFDB.SF_JL_QYSDSJMSBAJL_2014 set sqzt='4',zfr=null,zfrq=null,zfsm=null,htr=null,htrq=null,shr='"+ud.getYhid()+"',shsj=sysdate where basqwsh='"+baseVo.getBasqwsh()+"'");
		} catch (SQLException e) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			e.printStackTrace();
			throw new FrameException("�������˰������Ŀ��"+baseVo.getJmbasxmc()+"��ʧ�ܣ�");
		} finally {
			QysdsHelperUtil.dbResClose(st, null, null);
		}
		return true;
	}
	
	/**
	 * ��״̬��Ϊ���ͨ��
	 * @param conn
	 * @param baseVo
	 * @param ud
	 * @return
	 * @throws FrameException
	 */
	public boolean doAuditReject(Connection conn, JmsbaBaseVo baseVo,Yh ud)throws FrameException {
		Statement st = null;
		try {
			st = conn.createStatement();
			st.execute("update SFDB.SF_JL_QYSDSJMSBAJL_2014 set sqzt='5',zfr=null,zfrq=null,zfsm=null,htr=null,htrq=null,shr='"+ud.getYhid()+"',shsj=sysdate where basqwsh='"+baseVo.getBasqwsh()+"'");
		} catch (SQLException e) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			e.printStackTrace();
			throw new FrameException("�������˰������Ŀ��"+baseVo.getJmbasxmc()+"��ʧ�ܣ�");
		} finally {
			QysdsHelperUtil.dbResClose(st, null, null);
		}
		return true;
	}
}
