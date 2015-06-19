package com.lscdz.qysds.common.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import com.lscdz.ca.vo.DzyjsjVO;
import com.lscdz.qysds.application.qysdsnb2014.vo.response.QysdsNsrJbxxVo;
import com.lscdz.qysds.application.qysdsnb2014.vo.response.QysdsNsrjbxxVo2014;
import com.lscdz.qysds.common.QysdsNbConstant;
import com.lscdz.qysds.common.codetable.CodeTableKey;
import com.lscdz.qysds.common.service.qysds.bo.qysds.QysdsReportsDeclare;
import com.lscdz.qysds.common.service.qysds.bo.qysds.QysdsReportsItemDeclare;
import com.lscdz.qysds.common.service.qysds.bo.qysds.QysdsReportsTableDeclare;
import com.lscdz.qysds.common.service.qysds.util.Qyjbxx;
import com.lscdz.util.codetable.CodeTableManager;

import yangjian.frame.util.FrameException;
import yangjian.frame.util.ResourceManager;

public class QysdsHelperUtil {

	public static final String[] arrFb5 = { "7", "10", "11", "16", "17", "18",
			"19", "20", "21", "22", "23", "26", "27", "29", "30", "31", "35",
			"39", "41", "42", "43", "49", "52", "53", "54", "55", "56", "57",
			"58" };

	public static final String[] arrFb5Fb = { "2", "5", "6", "7", "8", "9",
			"10", "11" };

	/**
	 * ��ִ�����ࡣ���ɻ�ִ
	 */
	public String toHz(DzyjsjVO dzyj) {
		StringBuffer hzstr = new StringBuffer();
		// �û��ύ��ԭʼ����
		hzstr.append(QysdsNbConstant.QMYJ_DZYJ_SEPERATOR);
		hzstr.append(QysdsNbConstant.QMYJ_DZYJ_ORI_BEGIN).append(QysdsNbConstant.QMYJ_DZYJ_SEPERATOR);
		hzstr.append(dzyj.getDzyj()).append(QysdsNbConstant.QMYJ_DZYJ_SEPERATOR);
		hzstr.append(QysdsNbConstant.QMYJ_DZYJ_ORI_END).append(QysdsNbConstant.QMYJ_DZYJ_SEPERATOR);

		// �û�ǩ��ʹ�õ�֤����Ϣ
		hzstr.append(QysdsNbConstant.QMYJ_DZYJ_CLIENT_CERT_BEGIN).append(QysdsNbConstant.QMYJ_DZYJ_SEPERATOR);
		hzstr.append("ZSXLH=").append(dzyj.getZsxlh()).append(QysdsNbConstant.QMYJ_DZYJ_SEPERATOR);
		hzstr.append(QysdsNbConstant.QMYJ_DZYJ_CLIENT_CERT_END).append(QysdsNbConstant.QMYJ_DZYJ_SEPERATOR);

		// �û�ǩ��ֵ
		hzstr.append(QysdsNbConstant.QMYJ_DZYJ_CLIENT_SIGN_BEGIN).append(QysdsNbConstant.QMYJ_DZYJ_SEPERATOR);
		hzstr.append(dzyj.getDzyjqm()).append(QysdsNbConstant.QMYJ_DZYJ_SEPERATOR);
		hzstr.append(QysdsNbConstant.QMYJ_DZYJ_CLIENT_SIGN_END).append(QysdsNbConstant.QMYJ_DZYJ_SEPERATOR);

		// ��������ִǩ��ʱ��֤����Ϣ
		hzstr.append(QysdsNbConstant.QMYJ_DZYJ_SERVER_CERT_BEGIN).append(QysdsNbConstant.QMYJ_DZYJ_SEPERATOR);
		hzstr.append("ZSXLH=").append(dzyj.getFwqzsxlh()).append(QysdsNbConstant.QMYJ_DZYJ_SEPERATOR);
		hzstr.append(QysdsNbConstant.QMYJ_DZYJ_SERVER_CERT_END).append(QysdsNbConstant.QMYJ_DZYJ_SEPERATOR);

		// ��������ִǩ��ʱ��
		hzstr.append(QysdsNbConstant.QMYJ_DZYJ_SERVER_TIME_BEGIN).append(QysdsNbConstant.QMYJ_DZYJ_SEPERATOR);
		hzstr.append("TIME=").append(new SimpleDateFormat("yyyyMMddHHmmss").format(dzyj.getJssj())).append(QysdsNbConstant.QMYJ_DZYJ_SEPERATOR);
		hzstr.append(QysdsNbConstant.QMYJ_DZYJ_SERVER_TIME_END).append(QysdsNbConstant.QMYJ_DZYJ_SEPERATOR);

		// ��������ִǩ������
		hzstr.append(QysdsNbConstant.QMYJ_DZYJ_SERVER_SIGN_BEGIN).append(QysdsNbConstant.QMYJ_DZYJ_SEPERATOR);
		hzstr.append(dzyj.getHzqm()).append(QysdsNbConstant.QMYJ_DZYJ_SEPERATOR);
		hzstr.append(QysdsNbConstant.QMYJ_DZYJ_SERVER_SIGN_END).append(QysdsNbConstant.QMYJ_DZYJ_SEPERATOR);

		return hzstr.toString();
	}

	/**
	 * ���˷���table�����еĿո�
	 * 
	 * @param table
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public QysdsReportsDeclare cleanSpace(QysdsReportsDeclare declarein)throws FrameException {

		// �������
		QysdsReportsDeclare declare = declarein;
		// �������ݱ����
		QysdsReportsTableDeclare table = null;
		// ���ݱ�ֵ����
		QysdsReportsItemDeclare item = null;
		// ���ݱ����map
		Map mapTable = new HashMap();
		// ��ʱ�洢���ݱ�id��
		String tmpTableID;
		// ��ʱ�洢���ݱ�ֵ����id��
		String tmpItemID;
		// ���ݱ�id�ż���
		Iterator tableIDs = null;
		try {
			// ������ݱ�id�ż���
			tableIDs = declare.getTableContentList().keySet().iterator();
			while (tableIDs.hasNext()) {
				// ���һ�����ݱ�id��
				tmpTableID = (String) tableIDs.next();
				// ���һ�����ݱ����
				table = (QysdsReportsTableDeclare) declare.getTableContentList().get(tmpTableID);
				// ������ݱ�ֵ����id�ż���
				Iterator itemIDs = table.getCellContentList().keySet().iterator();
				// ����ֵ����map
				Map mapItem = new HashMap();
				while (itemIDs.hasNext()) {
					// ���һ�����ݱ�ֵ����id��
					tmpItemID = (String) itemIDs.next();
					// System.out.println("key--" + tmpItemID);
					// ���һ�����ݱ�ֵ����
					item = (QysdsReportsItemDeclare) table.getCellContentList().get(tmpItemID);
					if (!"".equals(item.getItemValue().trim())) {
						// һ�����ݱ�ֵ�������������ֵ����map��
						mapItem.put(tmpItemID, item);
					} else {
						// 20070208ȫ��Ϊ�ձ���δ���ǣ�������걨��û�걨�����⣨�����걨:��¼��ʱ������¼��һ���㣻�����걨��ϵͳ�ڴ���ͻ����ϴ�������ʱ������ĵ�һ��ֵ���Ϊ������Ϊ�㣩
						// �������ĵ�һ��Ϊ���򽫵�һ�е�ֵ��λΪ�㣬��֤��ʹ�ǿձ�Ҳ�������ݿ��д�����
						if ("1".equals(tmpTableID) && "1".equals(tmpItemID)) {
							// ����ĵ�һ����Ϊ��
							item.setItemValue("0");
							// һ�����ݱ�ֵ�������������ֵ����map��
							mapItem.put(tmpItemID, item);
						}
					}
				}
				// ���ݱ�ֵ���󼯺�map����һ�����ݱ������
				table.setCellContentList(mapItem);
				// һ�����ݱ������������ݱ����map��
				mapTable.put(tmpTableID, table);
			}
			// ���ݱ����map���뱨�������
			declare.setTableContentList(mapTable);
		} catch (Exception e) {
			e.printStackTrace();
			throw new FrameException(e.getMessage());
		}
		return declare;

	}

	/**
	 * �Ѵ������ʱ���˵��Ŀո�ԭ
	 * 
	 * @param table
	 * @param a
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public QysdsReportsTableDeclare putSpace(QysdsReportsTableDeclare table,int arrs[]) throws FrameException {
		System.out.println("**��ʾqysdsNewUtil�е�putSpace()**");
		try {
			for (int j = 1; j <= arrs.length; j = j + 2) {
				System.out.println("j___  " + j + "***" + arrs.length);
				for (int i = arrs[j - 1]; i <= arrs[j]; i++) {
					QysdsReportsItemDeclare item = (QysdsReportsItemDeclare) table
							.getCellContentList().get(String.valueOf(i));
					if (item == null) {
						QysdsReportsItemDeclare item1 = new QysdsReportsItemDeclare();
						item1.setItemID(String.valueOf(i));
						item1.setItemValue("");
						item1.setItemType("11");
						table.getCellContentList().put(String.valueOf(i), item1);
					} else if (item != null && item.getItemValue() != null && "".equals(item.getItemValue().trim())) {
						QysdsReportsItemDeclare item1 = new QysdsReportsItemDeclare();
						item1.setItemID(String.valueOf(i));
						item1.setItemValue("");
						item1.setItemType("11");
						table.getCellContentList().put(String.valueOf(i), item1);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new FrameException(e.getMessage());
		}
		return table;
	}


	/**
	 * itype=10,���� 5 itype=17,���� 5����
	 * 
	 * @param jsjdm
	 * @param sknd
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getReadOnlyHc(String jsjdm, String sknd) throws FrameException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List l = new ArrayList();
		Connection conn = null;
		try {
			conn = ResourceManager.getConnection();
			StringBuffer bf = new StringBuffer();
			// if (itype==ConstantKey.DMFB5){
			List la = Arrays.asList(arrFb5);
			l = new ArrayList(la);
			bf.append(" select distinct   ");
			bf.append(" case   ");
			bf.append(" when a.jmbasxdm='0010' then '7,'   ");
			bf.append(" when a.jmbasxdm='0020' then '10,'   ");
			bf.append(" when a.jmbasxdm='0030' then '11,'   ");
			bf.append(" when a.jmbasxdm='0040' then '29,' 				   ");
			bf.append(" when a.jmbasxdm='0060' then '30,'   ");
			bf.append(" when a.jmbasxdm='0070' then '31,'   ");
			bf.append(" when a.jmbasxdm='0080' then '35,'   ");
			bf.append(" when a.jmbasxdm='0090' then '52,'   ");
			bf.append(" when a.jmbasxdm='0100' then '53,'   ");
			bf.append(" when a.jmbasxdm='0110' then '54,'   ");
			bf.append(" when a.jmbasxdm='0120' then '55,' 			  ");
			bf.append(" when a.jmbasxdm='013B' then '39,'   ");
			bf.append(" when a.jmbasxdm='0170' then '49,'      ");
			bf.append(" when a.jmbasxdm='0180' then '56,'                     ");
			bf.append(" when a.jmbasxdm='0190' then '58,'               ");
			bf.append(" when a.jmbasxdm='0200' then '57,'   ");
			bf.append(" else null end hc   ");
			bf.append(" from sfdb.sf_jl_qysdsjmsbajl a   ");
			bf.append(" where a.jsjdm = '" + jsjdm + "' ");
			bf.append(" and a.band='" + sknd + "' ");
			bf.append(" and a.sqzt = '4' ");
			bf.append(" union           ");
			bf.append(" select distinct   ");
			bf.append(" case   ");
			bf.append(" when b.nlmyjmxmdm='01' then '16,'   ");
			bf.append(" when b.nlmyjmxmdm='02' then '17,'   ");
			bf.append(" when b.nlmyjmxmdm='03' then '18,'   ");
			bf.append(" when b.nlmyjmxmdm='04' then '19,' 				   ");
			bf.append(" when b.nlmyjmxmdm='05' then '20,' 		  ");
			bf.append(" when b.nlmyjmxmdm='06' then '21,'   ");
			bf.append(" when b.nlmyjmxmdm='07' then '22,'   ");
			bf.append(" when b.nlmyjmxmdm='08' then '23,'   ");
			bf.append(" when b.nlmyjmxmdm='09' then '26,'   ");
			bf.append(" when b.nlmyjmxmdm='10' then '27,'   ");
			bf.append(" else null end hc  ");
			bf.append(" from sfdb.sf_jl_qysdsjmsba_05 b,sfdb.sf_jl_qysdsjmsbajl a   ");
			bf.append(" where b.basqwsh=a.basqwsh  ");
			bf.append(" and a.jsjdm = '" + jsjdm + "' ");
			bf.append(" and a.band='" + sknd + "' ");
			bf.append(" and a.sqzt = '4' ");
			bf.append(" union           ");
			bf.append(" select distinct ");
			bf.append(" case ");
			bf.append(" when c.fjddm='01' then '41,' ");
			bf.append(" when c.fjddm='07' then '42,' ");
			bf.append(" when c.fjddm='10' then '43,' ");
			bf.append(" else '9999,' end hc ");
			bf.append(" from sfdb.sf_jl_qysdsjmsba_14b b, ");
			bf.append(" sfdb.sf_jl_qysdsjmsbajl a , ");
			bf.append(" dmdb.sf_dm_zysblx c ");
			bf.append(" where b.basqwsh=a.basqwsh ");
			bf.append(" and b.zysblxdm=c.zysblxdm ");
			bf.append(" and a.jsjdm = '" + jsjdm + "' ");
			bf.append(" and a.band='" + sknd + "' ");
			bf.append(" and a.sqzt = '4' ");
			ps = conn.prepareStatement(bf.toString());
			rs = ps.executeQuery();
			while (rs.next()) {
				String ss = rs.getString("hc");
				if (ss != null && !ss.equals("")) {
					String[] arr = ss.split(",");
					if (arr != null && arr.length > 0) {
						for (int i = 0; i < arr.length; i++) {
							if (arr[i] != null && !"".equals(arr[i])) {
								l.remove(l.indexOf(arr[i]));
							}
						}
					}
				}
			}

		} catch (Exception ex) {
			// �׳��쳣
			ex.printStackTrace();
			throw new FrameException(ex.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
				if (ps != null) {
					ps.close();
					ps = null;
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new FrameException(e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				throw new FrameException(e.getMessage());
			}
		}
		return l;

	}
	/**
	 * �ر����ݿ������Դ
	 * @param st
	 * @param rs
	 * @param conn
	 */
	public static void dbResClose(Statement st,ResultSet rs,Connection conn){
		if(st!=null){
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * ��ȡ˰�����
	 * @return
	 */
	public static String getYear(){
		Calendar ca = Calendar.getInstance();//�õ�һ��Calendar��ʵ�� 
		int nowYear=ca.get(Calendar.YEAR);
		int nd=nowYear-1;
		String year=String.valueOf(nd);
		return year;
	}
	/**
	 * ��õ�ǰ���ڣ�yyyymmdd��
	 * @return
	 */
	public static String getDate(){
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		return formatter.format(new Date());

	}
	/**
	 * ��ȡ��˰�˻�����Ϣ2014vo
	 * @return 
	 */
	public static  void getNsrjbxxVo2014(List<QysdsNsrjbxxVo2014> jbxxList2014,QysdsNsrjbxxVo2014 nsrjbxxVo2014,QysdsNsrJbxxVo nsrjbxxVo){
		if (jbxxList2014 != null && jbxxList2014.size() > 0) {
			//nsrjbxxVo=new QysdsNsrJbxxVo(); 	
			for(int i=0;i<jbxxList2014.size();i++){
				nsrjbxxVo2014=jbxxList2014.get(i);
				if(nsrjbxxVo2014!=null){
				//���ܷ־�
				String zgswjgdm=nsrjbxxVo2014.getSwjgzzjgdm();
				String zgfjdm=Qyjbxx.getZgfjdm(zgswjgdm);
				String zgfjmc=CodeTableManager.getNameByCode(CodeTableKey.GY_DM_SWJGZZJG, zgfjdm);
				nsrjbxxVo2014.setZgfjdm(zgfjdm);
				nsrjbxxVo2014.setZgfjmc(zgfjmc);
				//˰Դ��������
				String syjdlx=nsrjbxxVo2014.getSyjdlx();
				String syjdlxmc=CodeTableManager.getNameByCode(CodeTableKey.DJ_DM_QYSDSZGFWJDLX,syjdlx);
				nsrjbxxVo2014.setSyjdlxmc(syjdlxmc);
				//���û���ƶ�
				String sykjzz=nsrjbxxVo2014.getSykjzzhkjzz();
				String sykjzzmc=CodeTableManager.getNameByCode(CodeTableKey.SB_DM_QYSDS_KJZZ, sykjzz);
				if(sykjzz!=null){
					if(sykjzz.equals("99")||sykjzz.equals("15")||sykjzz.equals("16")){
						String qtkjzzdm=nsrjbxxVo2014.getQtsykjzzhkjzz();
						String qtkjzzmc=CodeTableManager.getNameByCode(CodeTableKey.SB_DM_QTKJZZ, qtkjzzdm);
						nsrjbxxVo2014.setQtsykjzzhkjzz(qtkjzzdm);
						nsrjbxxVo2014.setQtkjzzmc(qtkjzzmc);
					}
				}
			nsrjbxxVo2014.setSykjzzmc(sykjzzmc);
		  }
	   }
		nsrjbxxVo.setJbxxList2014(jbxxList2014);
		//return nsrjbxxVo2014;
		
	  }
  }
}