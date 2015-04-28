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
	 * 回执助手类。生成回执
	 */
	public String toHz(DzyjsjVO dzyj) {
		StringBuffer hzstr = new StringBuffer();
		// 用户提交的原始数据
		hzstr.append(QysdsNbConstant.QMYJ_DZYJ_SEPERATOR);
		hzstr.append(QysdsNbConstant.QMYJ_DZYJ_ORI_BEGIN).append(QysdsNbConstant.QMYJ_DZYJ_SEPERATOR);
		hzstr.append(dzyj.getDzyj()).append(QysdsNbConstant.QMYJ_DZYJ_SEPERATOR);
		hzstr.append(QysdsNbConstant.QMYJ_DZYJ_ORI_END).append(QysdsNbConstant.QMYJ_DZYJ_SEPERATOR);

		// 用户签名使用的证书信息
		hzstr.append(QysdsNbConstant.QMYJ_DZYJ_CLIENT_CERT_BEGIN).append(QysdsNbConstant.QMYJ_DZYJ_SEPERATOR);
		hzstr.append("ZSXLH=").append(dzyj.getZsxlh()).append(QysdsNbConstant.QMYJ_DZYJ_SEPERATOR);
		hzstr.append(QysdsNbConstant.QMYJ_DZYJ_CLIENT_CERT_END).append(QysdsNbConstant.QMYJ_DZYJ_SEPERATOR);

		// 用户签名值
		hzstr.append(QysdsNbConstant.QMYJ_DZYJ_CLIENT_SIGN_BEGIN).append(QysdsNbConstant.QMYJ_DZYJ_SEPERATOR);
		hzstr.append(dzyj.getDzyjqm()).append(QysdsNbConstant.QMYJ_DZYJ_SEPERATOR);
		hzstr.append(QysdsNbConstant.QMYJ_DZYJ_CLIENT_SIGN_END).append(QysdsNbConstant.QMYJ_DZYJ_SEPERATOR);

		// 服务器回执签名时的证书信息
		hzstr.append(QysdsNbConstant.QMYJ_DZYJ_SERVER_CERT_BEGIN).append(QysdsNbConstant.QMYJ_DZYJ_SEPERATOR);
		hzstr.append("ZSXLH=").append(dzyj.getFwqzsxlh()).append(QysdsNbConstant.QMYJ_DZYJ_SEPERATOR);
		hzstr.append(QysdsNbConstant.QMYJ_DZYJ_SERVER_CERT_END).append(QysdsNbConstant.QMYJ_DZYJ_SEPERATOR);

		// 服务器回执签发时间
		hzstr.append(QysdsNbConstant.QMYJ_DZYJ_SERVER_TIME_BEGIN).append(QysdsNbConstant.QMYJ_DZYJ_SEPERATOR);
		hzstr.append("TIME=").append(new SimpleDateFormat("yyyyMMddHHmmss").format(dzyj.getJssj())).append(QysdsNbConstant.QMYJ_DZYJ_SEPERATOR);
		hzstr.append(QysdsNbConstant.QMYJ_DZYJ_SERVER_TIME_END).append(QysdsNbConstant.QMYJ_DZYJ_SEPERATOR);

		// 服务器回执签名数据
		hzstr.append(QysdsNbConstant.QMYJ_DZYJ_SERVER_SIGN_BEGIN).append(QysdsNbConstant.QMYJ_DZYJ_SEPERATOR);
		hzstr.append(dzyj.getHzqm()).append(QysdsNbConstant.QMYJ_DZYJ_SEPERATOR);
		hzstr.append(QysdsNbConstant.QMYJ_DZYJ_SERVER_SIGN_END).append(QysdsNbConstant.QMYJ_DZYJ_SEPERATOR);

		return hzstr.toString();
	}

	/**
	 * 过滤放入table数据中的空格
	 * 
	 * @param table
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public QysdsReportsDeclare cleanSpace(QysdsReportsDeclare declarein)throws FrameException {

		// 报表对象
		QysdsReportsDeclare declare = declarein;
		// 单张数据表对象
		QysdsReportsTableDeclare table = null;
		// 数据表值对象
		QysdsReportsItemDeclare item = null;
		// 数据表对象map
		Map mapTable = new HashMap();
		// 临时存储数据表id号
		String tmpTableID;
		// 临时存储数据表值对象id号
		String tmpItemID;
		// 数据表id号集合
		Iterator tableIDs = null;
		try {
			// 获得数据表id号集合
			tableIDs = declare.getTableContentList().keySet().iterator();
			while (tableIDs.hasNext()) {
				// 获的一张数据表id号
				tmpTableID = (String) tableIDs.next();
				// 获得一张数据表对象
				table = (QysdsReportsTableDeclare) declare.getTableContentList().get(tmpTableID);
				// 获得数据表值对象id号集合
				Iterator itemIDs = table.getCellContentList().keySet().iterator();
				// 数据值对象map
				Map mapItem = new HashMap();
				while (itemIDs.hasNext()) {
					// 获得一个数据表值对象id号
					tmpItemID = (String) itemIDs.next();
					// System.out.println("key--" + tmpItemID);
					// 获得一个数据表值对象
					item = (QysdsReportsItemDeclare) table.getCellContentList().get(tmpItemID);
					if (!"".equals(item.getItemValue().trim())) {
						// 一个数据表值对象添加在数据值对象map中
						mapItem.put(tmpItemID, item);
					} else {
						// 20070208全部为空表如何处理，牵扯到空申报和没申报的问题（上门申报:在录入时在主表录入一个零；网上申报：系统在处理客户端上传的数据时将主表的第一个值如果为空则置为零）
						// 如果主表的第一行为空则将第一行的值复位为零，保证即使是空表也会在数据库中存数。
						if ("1".equals(tmpTableID) && "1".equals(tmpItemID)) {
							// 主表的第一行置为零
							item.setItemValue("0");
							// 一个数据表值对象添加在数据值对象map中
							mapItem.put(tmpItemID, item);
						}
					}
				}
				// 数据表值对象集合map放入一张数据表对象中
				table.setCellContentList(mapItem);
				// 一张数据表对象添加在数据表对象map中
				mapTable.put(tmpTableID, table);
			}
			// 数据表对象map放入报表对象中
			declare.setTableContentList(mapTable);
		} catch (Exception e) {
			e.printStackTrace();
			throw new FrameException(e.getMessage());
		}
		return declare;

	}

	/**
	 * 把存放数据时过滤掉的空格复原
	 * 
	 * @param table
	 * @param a
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public QysdsReportsTableDeclare putSpace(QysdsReportsTableDeclare table,int arrs[]) throws FrameException {
		System.out.println("**显示qysdsNewUtil中的putSpace()**");
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
	 * itype=10,附表 5 itype=17,附表 5附表
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
			// 抛出异常
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
	 * 关闭数据库相关资源
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
	 * 获取税款年度
	 * @return
	 */
	public static String getYear(){
		Calendar ca = Calendar.getInstance();//得到一个Calendar的实例 
		int nowYear=ca.get(Calendar.YEAR);
		int nd=nowYear-1;
		String year=String.valueOf(nd);
		return year;
	}
	/**
	 * 获得当前日期（yyyymmdd）
	 * @return
	 */
	public static String getDate(){
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		return formatter.format(new Date());

	}
	/**
	 * 获取纳税人基本信息2014vo
	 * @return 
	 */
	public static  void getNsrjbxxVo2014(List<QysdsNsrjbxxVo2014> jbxxList2014,QysdsNsrjbxxVo2014 nsrjbxxVo2014,QysdsNsrJbxxVo nsrjbxxVo){
		if (jbxxList2014 != null && jbxxList2014.size() > 0) {
			//nsrjbxxVo=new QysdsNsrJbxxVo(); 	
			for(int i=0;i<jbxxList2014.size();i++){
				nsrjbxxVo2014=jbxxList2014.get(i);
				if(nsrjbxxVo2014!=null){
				//主管分局
				String zgswjgdm=nsrjbxxVo2014.getSwjgzzjgdm();
				String zgfjdm=Qyjbxx.getZgfjdm(zgswjgdm);
				String zgfjmc=CodeTableManager.getNameByCode(CodeTableKey.GY_DM_SWJGZZJG, zgfjdm);
				nsrjbxxVo2014.setZgfjdm(zgfjdm);
				nsrjbxxVo2014.setZgfjmc(zgfjmc);
				//税源鉴定类型
				String syjdlx=nsrjbxxVo2014.getSyjdlx();
				String syjdlxmc=CodeTableManager.getNameByCode(CodeTableKey.DJ_DM_QYSDSZGFWJDLX,syjdlx);
				nsrjbxxVo2014.setSyjdlxmc(syjdlxmc);
				//适用会计制度
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