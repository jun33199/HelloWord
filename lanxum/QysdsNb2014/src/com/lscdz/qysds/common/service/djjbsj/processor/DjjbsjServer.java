package com.lscdz.qysds.common.service.djjbsj.processor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import yangjian.frame.util.FrameException;
import yangjian.frame.util.ResourceManager;

import com.lscdz.qysds.common.service.djjbsj.IDjjbsjService;
import com.lscdz.qysds.common.service.djjbsj.bo.Djjbsj;

/**
 * �Ǽǻ���������ز���������
 * @description : 
 * @author zhangj
 * @version 1.0
 * @time 2014-12-17 ����02:34:51
 */
public class DjjbsjServer implements IDjjbsjService{

	
	/**
	 * ��ѯ���������ĵǼǻ��������б����ݼ���������˰�������֯�������룩
	 * @param sqlWhere
	 * @return
	 * @throws FrameException
	 */
	@Override
	public Djjbsj query(Connection con,String jsjdm,String swjgzzjgdm) throws FrameException{
		if(jsjdm==null || swjgzzjgdm==null || jsjdm.equals("") || swjgzzjgdm.equals("")){
			throw new FrameException("����������˰�������֯�������벻��Ϊ�գ�");
		}
		String sqlWhere="jsjdm='"+jsjdm+"' and swjgzzjgdm='"+swjgzzjgdm+"'";
		List<Djjbsj> djjbsjList=this.readRecords(con,sqlWhere);	
		return djjbsjList.size()>0?djjbsjList.get(0):null;
	}
	/**
	 * ��ѯ���������ĵǼǻ��������б����ݼ�������룩
	 * @param sqlWhere
	 * @return
	 * @throws FrameException
	 */
	@Override
	public Djjbsj query(Connection con,String jsjdm) throws FrameException{
		if(jsjdm==null  || jsjdm.equals("")){
			throw new FrameException("��������벻��Ϊ�գ�");
		}
		String sqlWhere="jsjdm='"+jsjdm+"'";		
		List<Djjbsj> djjbsjList=this.readRecords(con,sqlWhere);	
		return djjbsjList.size()>0?djjbsjList.get(0):null;
	}
	
	/**
	 * ��ѯ���������ĵǼǻ��������б�
	 * @param sqlWhere
	 * @return
	 * @throws FrameException
	 */
	private  List<Djjbsj> readRecords(Connection con,String sqlWhere) throws FrameException
	{
		Statement st = null;
		ResultSet rs = null;
		Djjbsj vo = null;
		String buf = null;
		List<Djjbsj> ar = new ArrayList<Djjbsj>();

		try
		{
			st = con.createStatement();
			buf = "SELECT * FROM DJDB.DJ_JL_JBSJ where " + sqlWhere;
			rs = st.executeQuery(buf);

			while(rs.next())
			{
				vo = new Djjbsj();
				vo.setCjrq(rs.getTimestamp("CJRQ"));
				vo.setDjsllx(rs.getString("DJSLLX"));
				vo.setDjzclxdm(rs.getString("DJZCLXDM"));
				vo.setDjzzldm(rs.getString("DJZZLDM"));
				vo.setGdsgghbs(rs.getInt("GDSGGHBS"));
				vo.setGjbzhydm(rs.getString("GJBZHYDM"));
				vo.setHsxsdm(rs.getString("HSXSDM"));
				vo.setJsjdm(rs.getString("JSJDM"));
				vo.setJydz(rs.getString("JYDZ"));
				vo.setJydzyb(rs.getString("JYDZYB"));
				vo.setKjzddm(rs.getString("KJZDDM"));
				vo.setKydjrq(rs.getTimestamp("KYDJRQ"));
				vo.setLrrq(rs.getTimestamp("LRRQ"));
				vo.setLsgxdm(rs.getString("LSGXDM"));
				vo.setNsrmc(rs.getString("NSRMC"));
				vo.setNsrzt(rs.getString("NSRZT"));
				vo.setQrrq(rs.getTimestamp("QRRQ"));
				vo.setQrry(rs.getString("QRRY"));
				vo.setScjxdm(rs.getString("SCJXDM"));
				vo.setSlrq(rs.getTimestamp("SLRQ"));
				vo.setSlry(rs.getString("SLRY"));
				vo.setSwdjlx(rs.getString("SWDJLX"));
				vo.setSwdjzh(rs.getString("SWDJZH"));
				vo.setSwjgzzjgdm(rs.getString("SWJGZZJGDM"));
				vo.setZcdz(rs.getString("ZCDZ"));
				vo.setZcdzyb(rs.getString("ZCDZYB"));
				vo.setZczbbzdm(rs.getString("ZCZBBZDM"));
				vo.setZczbje(rs.getBigDecimal("ZCZBJE"));
				vo.setZgbmdm(rs.getString("ZGBMDM"));
				vo.setCjsj(rs.getTimestamp("CJSJ"));
				vo.setHzfhrq(rs.getTimestamp("HZFHRQ"));
				vo.setHzfhry(rs.getString("HZFHRY"));
				vo.setJydzlxdm(rs.getString("JYDZLXDM"));
				vo.setJyfw(rs.getString("JYFW"));
				vo.setQyzy(rs.getString("QYZY"));
				vo.setSbdm(rs.getString("SBDM"));
				vo.setScbs(rs.getString("SCBS"));
				vo.setSfzjhm(rs.getString("SFZJHM"));
				vo.setSjlylxdm(rs.getString("SJLYLXDM"));
				vo.setWzztzblhj(rs.getBigDecimal("WZZTZBLHJ"));
				vo.setXgsj(rs.getTimestamp("XGSJ"));
				vo.setYhzrq(rs.getTimestamp("YHZRQ"));
				vo.setYhzry(rs.getString("YHZRY"));
				vo.setYyzzh(rs.getString("YYZZH"));
				vo.setZcdzlxdh(rs.getString("ZCDZLXDH"));
				vo.setZhgxsj(rs.getString("ZHGXSJ"));
				vo.setZrrtzblhj(rs.getBigDecimal("ZRRTZBLHJ"));
				vo.setZzjgdm(rs.getString("ZZJGDM"));

				ar.add(vo);
			}
			return ar;
		}
		catch(Exception ex)
		{
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			ex.printStackTrace();
			throw new FrameException("�ڲ������������Ա��ϵ�����ʱ�: DJDB.DJ_JL_JBSJ");
		}
		finally
        {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} 
			} 
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} 
			}

	    } 
	}

}
