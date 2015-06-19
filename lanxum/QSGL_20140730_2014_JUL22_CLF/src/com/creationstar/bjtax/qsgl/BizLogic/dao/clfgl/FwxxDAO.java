package com.creationstar.bjtax.qsgl.BizLogic.dao.clfgl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl.Fwxx;
import com.ttsoft.common.util.Debug;
import java.sql.Blob;

/**
 * ������ϢDAO
 * 
 * @author tutu 2013-05-07
 */
public class FwxxDAO extends BaseDAO {

	/**
	 * ����һ��������Ϣ���ݼ�¼
	 * 
	 * @param fpcz
	 *            ��Ʊ�������ֵ����
	 * @param conn
	 *            ���ݿ����Ӷ���
	 * @throws SQLException
	 */
	public void insert(Fwxx fwxx, Connection conn) throws SQLException {
		String sql = "insert into qsdb.qs_jl_fwxxb (sbbh,htbh,fwcqzh,fwsyqztfrq,fwzlqx,fwzldz,fwjzmj,jzjgdm,ghyt,fwqszylx,zcs,szlc,htwsqyrq,sfwscsssggf,htzj,bzdm,bzmc,hl,wbje,fdczjjgmc,bbbs,swjgzzjgdm,sfesf,cjr,cjrq,lrr,lrrq,bz,ewmsj,fwxzdm,xxly) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,sysdate,?,sysdate,?,rawtohex(?),?,?)";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);

			ps.setString(1, fwxx.sbbh);
			ps.setString(2, fwxx.htbh);
			ps.setString(3, fwxx.fwcqzh);
			ps.setTimestamp(4, fwxx.fwsyqztfrq);
			ps.setString(5, fwxx.fwzlqx);
			ps.setString(6, fwxx.fwzldz);
			ps.setBigDecimal(7, fwxx.fwjzmj);
			ps.setString(8, fwxx.jzjgdm);
			ps.setString(9, fwxx.ghyt);
			ps.setString(10, fwxx.fwqszylx);
			ps.setBigDecimal(11, fwxx.zcs);
			//ps.setBigDecimal(12, fwxx.szlc);
			ps.setString(12, fwxx.szlc);
			ps.setTimestamp(13, fwxx.htwsqyrq);
			ps.setString(14, fwxx.sfwscsssggf);
			ps.setBigDecimal(15, fwxx.htzj);
			ps.setString(16, fwxx.bzdm);
			ps.setString(17, fwxx.bzmc);
			ps.setBigDecimal(18, fwxx.hl);
			ps.setBigDecimal(19, fwxx.wbje);
			ps.setString(20, fwxx.fdczjjgmc);
			ps.setString(21, fwxx.bbbs);
			ps.setString(22, fwxx.swjgzzjgdm);
			ps.setString(23, fwxx.sfesf);
			ps.setString(24, fwxx.cjr);
			// ps.setTimestamp(25, fwxx.cjrq);
			ps.setString(25, fwxx.lrr);
			// ps.setTimestamp(27, fwxx.lrrq);
			ps.setString(26, fwxx.bz);
			ps.setString(27, fwxx.ewmsj);

			ps.setString(28, fwxx.fwxzdm);
			ps.setString(29, fwxx.xxly);

			System.out.println("��ά������22222222222+++++++++++++++++" + fwxx.ewmsj);
			/*
			 * if(fwxx.ewmsj != null || !"".equals(fwxx.ewmsj)){
			 * ps.setCharacterStream(27, String2Reader(fwxx.ewmsj),
			 * fwxx.ewmsj.length()); }
			 */
			// ִ��
			ps.execute();
		} catch (SQLException e) {
			throw e;
		} finally {
			ps.close();
		}
	}

	/**
	 * �ַ�ת�����ֽ�����д��blob
	 * 
	 * @methodName:String2Stream
	 * @function:
	 * 
	 * @param str
	 * @return
	 * @author:�Ʋ���
	 * @create date��2013-5-24 ����09:13:24
	 * @version 1.1
	 * 
	 * 
	 */
	public InputStreamReader String2Reader(String str) {

		if (str == null || "".equals(str)) {

			return null;
		}
		byte[] trsBype = str.getBytes();
		//
		ByteArrayInputStream bs = new ByteArrayInputStream(trsBype);

		InputStreamReader bais = new InputStreamReader(bs);

		return bais;
	}

	/**
	 * �����ݿ�blob�ֶζ�����String
	 * 
	 * @methodName:convertBlobToString
	 * @function:
	 * 
	 * @return
	 * @author:�Ʋ���
	 * @create date��2013-5-24 ����09:47:01
	 * @version 1.1
	 * 
	 * 
	 */
	// �����ݿ���blob����ת����String����
	public String convertBlobToString(Blob blob) {

		String result = "";
		try {
			InputStream inStream = (InputStream) blob.getBinaryStream();

			if (blob != null)
				System.out.println("��ֵ������������������������");
			try {
				byte[] data = new byte[200];

				int length = 0;// ÿ�ζ�ȡ��ʵ���ֽڳ���
				// is.read()��������buff�������ĵ�0��λ��ʼ��ȡ�ֽڣ�ÿ������ȡ200��
				// �����᷵��һ��ʵ�ʶ�ȡ�ĳ��ȣ���length���գ���ֵΪ-1ʱ����ʾ���е��ֽ�ȫ����ȡ���
				while ((length = inStream.read(data, 0, 200)) != -1) {
					// ���ֽ���ƽ̨��Ĭ�ϱ��뷽ʽתΪ�ַ�����buff�ĵ�0��λ��ʼת����ʵ��Ҫת���ĳ�����length
					String str = new String(data, 0, length);
					System.out.println("���ս��====  " + str + "  ====");
					result = result + str;
				}

				// �ر���
				inStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * ����ɼ���Ϣ����ʷ��
	 * 
	 * @return
	 */
	public boolean saveFwxx2his(String sbbh, String scrydm, Connection conn)
			throws SQLException {
		boolean isSuccess = false;
		PreparedStatement ps = null;

		// ɾ��ʱƴ��ɾ����Ա��ɾ��ʱ��SQL
		String scrySQL = ",'' SCCZRY,null SCRQ";
		if (scrydm != null && !"".equals(scrydm)) {
			scrySQL = ",'" + scrydm + "' SCCZRY,sysdate SCRQ";
		}

		try {
			// 1.ƴ�Ӳ��뷿����Ϣ��ʷ��SQL
			StringBuffer hisSQLBuff = new StringBuffer();
			hisSQLBuff
					.append("insert into qsdb.qs_jl_fwxxb_ls (xh,SBBH, HTBH, FWCQZH, FWSYQZTFRQ, FWZLQX, FWZLDZ, FWJZMJ, JZJGDM, GHYT, FWQSZYLX, ZCS, SZLC, HTWSQYRQ, SFWSCSSSGGF, HTZJ, BZDM, BZMC, HL, WBJE, FDCZJJGMC, BBBS, SWJGZZJGDM, SFESF, CJR, CJRQ, LRR, LRRQ, BZ, EWMSJ, FWXZDM, XXLY,SCCZRY,SCRQ) "
							+ " select QSDB.SEQ_QS_CLFJYXH.nextval, SBBH, HTBH, FWCQZH, FWSYQZTFRQ, FWZLQX, FWZLDZ, FWJZMJ, JZJGDM, GHYT, FWQSZYLX, ZCS, SZLC, HTWSQYRQ, SFWSCSSSGGF, HTZJ, BZDM, BZMC, HL, WBJE, FDCZJJGMC, BBBS, SWJGZZJGDM, SFESF, CJR, CJRQ, LRR, LRRQ, BZ, EWMSJ, FWXZDM, XXLY");
			hisSQLBuff.append(scrySQL);
			hisSQLBuff.append(" from  qsdb.qs_jl_fwxxb  where sbbh = ? ");

			System.out.println("hisSQLBuff##########" + hisSQLBuff.toString());

			ps = conn.prepareStatement(hisSQLBuff.toString());

			ps.setString(1, sbbh);

			isSuccess = ps.execute();
		} catch (SQLException e) {
			throw e;
		} finally {
			ps.close();
		}
		return isSuccess;
	}

	/**
	 * ɾ��������Ϣ
	 * 
	 * @param htbh
	 * @param sbbh
	 * @param conn
	 * @return
	 * @throws SQLException
	 */
	public boolean delFwxx(String sbbh, Connection conn) throws SQLException {
		boolean isSuccess = false;
		PreparedStatement ps = null;

		try {
			// 1.ɾ��������Ϣ
			StringBuffer delSQLBuff = new StringBuffer();
			delSQLBuff.append("delete from  qsdb.qs_jl_fwxxb  where sbbh = ? ");

			System.out.println("delSQLBuff##########" + delSQLBuff.toString());

			ps = conn.prepareStatement(delSQLBuff.toString());
			ps.setString(1, sbbh);

			isSuccess = ps.execute();
		} catch (SQLException e) {
			throw e;
		} finally {
			ps.close();
		}
		return isSuccess;
	}

	/**
	 * ���²ɼ���Ϣ
	 * 
	 * @param fpcz
	 *            ��Ʊ��������ֵ����
	 * @param conn
	 *            ���ݿ����Ӷ���
	 * @throws SQLException
	 * @throws SQLException
	 */
	public void update(Fwxx fwxx, Connection conn) throws SQLException {
		String sql = "update  qsdb.qs_jl_fwxxb  set /*sbbh=? ,*/htbh=? ,fwcqzh=? ,fwsyqztfrq=? ,fwzlqx=? ,fwzldz=? ,fwjzmj=? ,jzjgdm=? ,ghyt=? ,fwqszylx=? ,zcs=? ,szlc=? ,htwsqyrq=? ,sfwscsssggf=? ,htzj=? ,bzdm=? ,bzmc=? ,hl=? ,wbje=? ,fdczjjgmc=? ,bbbs=? ,swjgzzjgdm=? ,sfesf=? /*,cjr=? ,cjrq=?*/ ,lrr=? ,lrrq=sysdate ,bz=? /*,ewmsj=rawtohex(?) */,FWXZDM=?/*,xxly=?*/ where sbbh=?  ";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);

			int index = 1;

			// ps.setString(1, fwxx.sbbh);
			ps.setString(index++, fwxx.htbh);

			ps.setString(index++, fwxx.fwcqzh);
			ps.setTimestamp(index++, fwxx.fwsyqztfrq);
			ps.setString(index++, fwxx.fwzlqx);
			ps.setString(index++, fwxx.fwzldz);
			ps.setBigDecimal(index++, fwxx.fwjzmj);
			ps.setString(index++, fwxx.jzjgdm);
			ps.setString(index++, fwxx.ghyt);
			ps.setString(index++, fwxx.fwqszylx);
			ps.setBigDecimal(index++, fwxx.zcs);
			ps.setString(index++, fwxx.szlc);
			ps.setTimestamp(index++, fwxx.htwsqyrq);
			ps.setString(index++, fwxx.sfwscsssggf);
			ps.setBigDecimal(index++, fwxx.htzj);
			ps.setString(index++, fwxx.bzdm);
			ps.setString(index++, fwxx.bzmc);
			ps.setBigDecimal(index++, fwxx.hl);
			ps.setBigDecimal(index++, fwxx.wbje);
			ps.setString(index++, fwxx.fdczjjgmc);
			ps.setString(index++, fwxx.bbbs);
			ps.setString(index++, fwxx.swjgzzjgdm);
			ps.setString(index++, fwxx.sfesf);
			/*
			 * ps.setString(24, fwxx.cjr); ps.setTimestamp(25, fwxx.cjrq);
			 */
			ps.setString(index++, fwxx.lrr);
			/* ps.setTimestamp(index++, fwxx.lrrq); */
			ps.setString(index++, fwxx.bz);
			/* ps.setString(29, fwxx.xxly); */
			// ps.setString(29, fwxx.ewmsj);
			/*
			 * if(fwxx.ewmsj != null || !"".equals(fwxx.ewmsj)){
			 * ps.setCharacterStream(29, String2Reader(fwxx.ewmsj),
			 * fwxx.ewmsj.length()); }
			 */

			ps.setString(index++, fwxx.fwxzdm);

			ps.setString(index++, fwxx.getSbbh());

			ps.execute();
		} catch (SQLException e) {
			throw e;
		} finally {
			ps.close();
		}
	}

	/**
	 * ����conditions��ȡ��������
	 * 
	 * @param conditions
	 *            ����where �������־�
	 * @param conn
	 *            ���ݿ����Ӷ���
	 * @return ArrayList ��������ֵ����ļ���
	 * @throws SQLException
	 */
	public ArrayList queryFwList(Connection conn, String conditions)
			throws SQLException {
		ArrayList fwxxList = new ArrayList();
		String sql = "select  sbbh,htbh,fwcqzh,fwsyqztfrq,fwzlqx,fwzldz,fwjzmj,jzjgdm,ghyt,fwqszylx,zcs,szlc,htwsqyrq,sfwscsssggf,htzj,bzdm,bzmc,hl,wbje,fdczjjgmc,bbbs,swjgzzjgdm,sfesf,cjr,cjrq,lrr,lrrq,bz,ewmsj,FWXZDM,xxly from qsdb.qs_jl_fwxxb "
				+ conditions;

		//Debug.out("queryFwList:" + sql);

		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Fwxx fwxx = new Fwxx();

				fwxx.setSbbh(rs.getString("sbbh"));
				fwxx.setHtbh(rs.getString("htbh"));
				fwxx.setFwcqzh(rs.getString("fwcqzh"));
				fwxx.setFwsyqztfrq(rs.getTimestamp("fwsyqztfrq"));
				fwxx.setFwzlqx(rs.getString("fwzlqx"));
				fwxx.setFwzldz(rs.getString("fwzldz"));
				fwxx.setFwjzmj(rs.getBigDecimal("fwjzmj"));
				fwxx.setJzjgdm(rs.getString("jzjgdm"));
				fwxx.setGhyt(rs.getString("ghyt"));
				fwxx.setFwqszylx(rs.getString("fwqszylx"));
				fwxx.setZcs(rs.getBigDecimal("zcs"));
				fwxx.setSzlc(rs.getString("szlc"));
				fwxx.setHtwsqyrq(rs.getTimestamp("htwsqyrq"));
				fwxx.setSfwscsssggf(rs.getString("sfwscsssggf"));
				fwxx.setHtzj(rs.getBigDecimal("htzj"));
				fwxx.setBzdm(rs.getString("bzdm"));
				fwxx.setBzmc(rs.getString("bzmc"));
				fwxx.setHl(rs.getBigDecimal("hl"));
				fwxx.setWbje(rs.getBigDecimal("wbje"));
				fwxx.setFdczjjgmc(rs.getString("fdczjjgmc"));
				fwxx.setBbbs(rs.getString("bbbs"));
				fwxx.setSwjgzzjgdm(rs.getString("swjgzzjgdm"));
				fwxx.setSfesf(rs.getString("sfesf"));
				fwxx.setCjr(rs.getString("cjr"));
				fwxx.setCjrq(rs.getTimestamp("cjrq"));
				fwxx.setLrr(rs.getString("lrr"));
				fwxx.setLrrq(rs.getTimestamp("lrrq"));
				fwxx.setBz(rs.getString("bz"));
				if (rs.getBlob("ewmsj") != null
						&& !"".equals(rs.getBlob("ewmsj"))) {
					fwxx.setEwmsj(convertBlobToString(rs.getBlob("ewmsj")));
				}

				fwxx.setFwxzdm(rs.getString("FWXZDM"));
				fwxx.setXxly(rs.getString("xxly"));

				fwxxList.add(fwxx);
			}
			//System.out.println(" FwxxList size:" + fwxxList.size());

			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			ps.close();
		}
		return fwxxList;
	}

	// �ж��Ƿ����޸ĺ�ɾ��Ȩ�ޣ������� ��ͬ��Ϣ�ֹ�¼���˰����Ա�˶���Ϣ¼�룩
	public boolean queryAuth2UpdateAndDelete(Connection conn, String conditions)
			throws SQLException {
		boolean hasAuth = false;

		if (conditions == null || "".equals(conditions)) {
			return hasAuth;
		}

		String sql = "select * from dmdb.qs_dm_clfjycsb  where zxbs='0' and cslx='02' "+ conditions;

		Debug.out("queryAuth2UpdateAndDelete:" + sql);

		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				hasAuth = true;
				
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			ps.close();
		}
		
		System.out.println("Ȩ��ֵ++++++++++++++++++++++++"+hasAuth);
		return hasAuth;
	}
	
	
	/**
	 * ����conditions��ȡ��������
	 * 
	 * @param conditions
	 *            ����where �������־�
	 * @param conn
	 *            ���ݿ����Ӷ���
	 * @return ArrayList ��˰��ѯ��������ֵ����ļ���
	 * @throws SQLException
	 */
	public ArrayList queryFwListForQs(Connection conn, String conditions)
			throws SQLException {
		ArrayList fwxxList = new ArrayList();
		String sql = "select  sbbh,htbh,fwcqzh,fwsyqztfrq,fwzlqx,fwzldz,fwjzmj,jzjgdm,ghyt,fwqszylx,zcs,szlc,htwsqyrq,sfwscsssggf,htzj,bzdm,bzmc,hl,wbje,fdczjjgmc,bbbs,swjgzzjgdm,sfesf,cjr,cjrq,lrr,lrrq,bz,ewmsj,FWXZDM,xxly from qsdb.qs_jl_fwxxb "
				+ conditions;

		Debug.out("queryFwList:" + sql);

		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Fwxx fwxx = new Fwxx();

				fwxx.setSbbh(rs.getString("sbbh"));
				fwxx.setHtbh(rs.getString("htbh"));
				fwxx.setFwcqzh(rs.getString("fwcqzh"));
				fwxx.setFwsyqztfrq(rs.getTimestamp("fwsyqztfrq"));
				fwxx.setFwzlqx(rs.getString("fwzlqx"));
				fwxx.setFwzldz(rs.getString("fwzldz"));
				fwxx.setFwjzmj(rs.getBigDecimal("fwjzmj"));
				fwxx.setJzjgdm(rs.getString("jzjgdm"));
				fwxx.setGhyt(rs.getString("ghyt"));
				fwxx.setFwqszylx(rs.getString("fwqszylx"));
				fwxx.setZcs(rs.getBigDecimal("zcs"));
				fwxx.setSzlc(rs.getString("szlc"));
				fwxx.setHtwsqyrq(rs.getTimestamp("htwsqyrq"));
				fwxx.setSfwscsssggf(rs.getString("sfwscsssggf"));
				fwxx.setHtzj(rs.getBigDecimal("htzj"));
				fwxx.setBzdm(rs.getString("bzdm"));
				fwxx.setBzmc(rs.getString("bzmc"));
				fwxx.setHl(rs.getBigDecimal("hl"));
				fwxx.setWbje(rs.getBigDecimal("wbje"));
				fwxx.setFdczjjgmc(rs.getString("fdczjjgmc"));
				fwxx.setBbbs(rs.getString("bbbs"));
				fwxx.setSwjgzzjgdm(rs.getString("swjgzzjgdm"));
				fwxx.setSfesf(rs.getString("sfesf"));
				fwxx.setCjr(rs.getString("cjr"));
				fwxx.setCjrq(rs.getTimestamp("cjrq"));
				fwxx.setLrr(rs.getString("lrr"));
				fwxx.setLrrq(rs.getTimestamp("lrrq"));
				fwxx.setBz(rs.getString("bz"));
				if (rs.getBlob("ewmsj") != null
						&& !"".equals(rs.getBlob("ewmsj"))) {
					fwxx.setEwmsj(convertBlobToString(rs.getBlob("ewmsj")));
				}

				fwxx.setFwxzdm(rs.getString("FWXZDM"));
				fwxx.setXxly(rs.getString("xxly"));

				fwxxList.add(fwxx);
			}
			System.out.println(" FwxxList size:" + fwxxList.size());

			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			ps.close();
		}
		return fwxxList;
	}

}
