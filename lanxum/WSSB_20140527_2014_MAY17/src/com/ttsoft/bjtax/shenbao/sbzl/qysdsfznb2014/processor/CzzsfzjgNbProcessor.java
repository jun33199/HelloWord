package com.ttsoft.bjtax.shenbao.sbzl.qysdsfznb2014.processor;


import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import com.ekernel.db.or.ORManager;
import com.syax.bjtax.ca.util.DzyjHelper;
import com.syax.bjtax.ca.vo.DzyjsjVO;
import com.syax.common.util.CAcodeConstants;
import com.syax.creports.bo.qysds.Jbxx;
import com.syax.creports.bo.qysds.QysdsReportsConstants;
import com.syax.creports.bo.qysds.QysdsReportsDeclare;
import com.syax.creports.bo.qysds.QysdsReportsItemDeclare;
import com.syax.creports.bo.qysds.QysdsReportsTableDeclare;
import com.syax.creports.persistent.AppAccessFactory;
import com.syax.creports.persistent.access.IAppAccess;
import com.syax.frame.util.DateTimeUtil;
import com.ttsoft.bjtax.dj.CAConstants;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.dj.util.DjStringUtil;
import com.ttsoft.bjtax.jikuai.zwcl.inf.JKAdapter;
import com.ttsoft.bjtax.sfgl.proxy.ServiceProxy;
import com.ttsoft.bjtax.shenbao.constant.CodeConstant;
import com.ttsoft.bjtax.shenbao.constant.SzsmdmConstant;
import com.ttsoft.bjtax.shenbao.model.domain.Jm;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsfznb2014.QysdsNbConstant2014;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsfznb2014.bo.CzzsfzjgNbBO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsfznb2014.xmlvo.CzzssdsNbVO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjdks.QysdsksActionConstant;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjdks.QysdsksMapConstant;
import com.ttsoft.bjtax.shenbao.util.DBResource;
import com.ttsoft.bjtax.shenbao.util.FriendHelper;
import com.ttsoft.bjtax.shenbao.util.Skssrq;
import com.ttsoft.bjtax.shenbao.util.TinyTools;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.exception.SystemException;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * 
 * @description : 2014核定征收企业所得税年报processor类
 * @author zhangj
 * @version 2014-9-17 上午11:57:37
 */
public class CzzsfzjgNbProcessor implements Processor {

	private String[] HCItem = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13",
            "14", "15", "16", "17", "18","19","20", "30", "31", "32", "33", "34", "35","36","37"};
    private String[] HCName = {"nsfs", "zfjg", "yysr", "yycb", "lrze", "tdjsynssde", "bzsr", "mssr","jmzynssde", "mbyqndks", "sjlre", "sl", "ynsdse", "jmsdse", "xwqyjmsdse","sjyyjsdse", "tdywyjsdse", "ybtsdse", "yqnddjsdse", "bqsjybtsdse", 
        							"zjgyftsdse", "czjzfpsdse", "fzjgyftsdse", "zjgdlscjybmyftsdse", "fpbl", "fpsdse","zczbje","zcze"};

	public CzzsfzjgNbProcessor() {
	}

	/**
	 * 根据业务操作类型值来做业务操作
	 *
	 * @param vo
	 *            VOPackage
	 * @return java.lang.Object
	 * @throws com.ttsoft.framework.exception.BaseException
	 */
	public Object process(VOPackage vo) throws BaseException {
		// 根据业务操作类型值来做业务操作
		try {
			switch (vo.getAction()) {
			// 查询
			case QysdsksActionConstant.INT_ACTION_QUERY: {
				return doQuery((Map) vo.getData());
			}
			// 保存
			case QysdsksActionConstant.INT_ACTION_SAVE: {
				return doSave(vo);
			}
			// 删除
			case QysdsksActionConstant.INT_ACTION_DELETE: {
				doDelete(vo);
				return null;
			}
			default:
				throw new SystemException("no such method");
			}
		} catch (Exception e) {
			throw ExceptionUtil.getBaseException(e);
		}
	}

	/**
	 * 查询登记信息和申报数据
	 *
	 * @param pData
	 *            Map
	 * @return Map
	 * @throws BaseException
	 * 
	 */
	private Object doQuery(Map pData) throws BaseException {
		// 数据库连接对象
		Connection conn = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		try {
			// 获得数据库连接
			conn = DBResource.getConnection();
			CzzsfzjgNbBO hdsdsbo = new CzzsfzjgNbBO();
			// 计算机代码
			String jsjdm = null;
			// 当前日期
			Timestamp curDate = null;
			// 税务登记基本数据值对象
			SWDJJBSJ djjbsj = (SWDJJBSJ) pData.get(QysdsksMapConstant.OBJECT_KEY_DJSJ);
			hdsdsbo.setJsjdm(djjbsj.getJsjdm());
			hdsdsbo.setNsrmc(djjbsj.getNsrmc());
			hdsdsbo.setNsrsbh(djjbsj.getSwdjzh());
			// 取得计算机代码
			jsjdm = (String) pData.get(QysdsksMapConstant.STRING_KEY_JSJDM);
			// 取得日期参数
			curDate = (Timestamp) pData.get(QysdsksMapConstant.STRING_KEY_DATE);
			hdsdsbo.setSbrq(curDate);
			hdsdsbo.setSbrqshow(sdf.format(curDate));
			// 取得所在的季度
			String jd = Skssrq.preQuarter(curDate);
			hdsdsbo.setJd(jd);
			// 报表类型
			String bblx = (String) pData.get(QysdsksMapConstant.STRING_KEY_BBLX);
			// 年度类型
			String ndlx = (String) pData.get(QysdsNbConstant2014.STRING_KEY_NDLX);
			// 取得税款所属日期Map
			Map skssrq = new HashMap();
			skssrq = Skssrq.yearSkssrq(curDate);
			// 取得税款所属开始和结束日期
			Timestamp skssksrq = (Timestamp) skssrq.get(Skssrq.SKSSKSRQ);
			Timestamp skssjsrq = (Timestamp) skssrq.get(Skssrq.SKSSJSRQ);
			hdsdsbo.setSkssksrq(skssksrq);
			hdsdsbo.setSkssjsrq(skssjsrq);

			// 取得年度
			String nd = (String) skssrq.get(Skssrq.SKSSRQ_ND);
			hdsdsbo.setNd(nd);

			IAppAccess iApp = AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			QysdsReportsDeclare qrd = new QysdsReportsDeclare();
			qrd.setAppid(QysdsReportsConstants.CREPORTS_APPID_QYSDS);
			//qrd.setVersion(QysdsReportsConstants.CREPORTS_VERSION_QYSDS);
			qrd.setVersion(QysdsNbConstant2014.QYSDSNB_VERSION_2014);
			qrd.setNsrjsjdm(jsjdm);

			qrd.setSknd(nd);
			// 报表数据则根据情况设置期号为1、2、3、4     2为年报
			qrd.setBbqlx(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_YEAR);
			// 如果是年报数据则固定设置期号为1
			qrd.setQh(ndlx);

			QysdsReportsTableDeclare qrtd = new QysdsReportsTableDeclare();
			qrtd.setTableId(bblx);
			//填报表类型，和报表期类型一样
			qrtd.setTbblx(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_YEAR);
			Map tmp = new HashMap();
			tmp.put(bblx, qrtd);
			qrd.setTableContentList(tmp);
			qrd = (QysdsReportsDeclare) iApp.querySingleTable(qrd);//查询数据
			qrtd = (QysdsReportsTableDeclare) qrd.getTableContentList().get(bblx);
			//System.out.println("qrtd.size: "+qrtd.getCellContentList().size());
			if (qrtd == null) {
			} else {
				if(qrtd.getCellContentList().size()==0){
					hdsdsbo.setQueryFlag("0");
				}else{
					hdsdsbo.setQueryFlag("1");
				}
				HashMap map = new HashMap();
				
				for (int i = 0; i < HCItem.length; i++)
                {
					if (qrtd.getCellContentList().get(HCItem[i]) != null)
                    {
						QysdsReportsItemDeclare qrtid = (QysdsReportsItemDeclare) qrtd.getCellContentList().get(HCItem[i]);
						if (qrtid.getItemValue() == null) {
							map.put(HCName[i], "0.00");
						} else {
							map.put(HCName[i], qrtid.getItemValue());
						}
					}
                    else {
						map.put(HCName[i], "0.00");
					}
				}
				hdsdsbo.setSbsj(map);
			}
			return hdsdsbo;
		} catch (Exception ex) {
			throw ExceptionUtil.getBaseException(ex, "查询企业所得税季报数据失败");
		} finally {
			// 关闭数据库连接
			DBResource.destroyConnection(conn);
		}
	}

	/**
	 * 保存企业所得税季报数据
	 *
	 * @param data
	 *            Map
	 * @throws BaseException
	 */
	private Map doSave(VOPackage vop) throws BaseException {
		DzyjHelper dh = new DzyjHelper();
		Map retData = new HashMap();
		Map data = (Map) vop.getData();
		UserData ud = vop.getUserData();
		Connection conn = null;
		CzzssdsNbVO qyjb = (CzzssdsNbVO) data.get(CAcodeConstants.CA_MAPKEY_VO_XMLVO);
		DzyjsjVO dzyj = (DzyjsjVO) data.get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);

		CzzsfzjgNbBO qysdsndbo = (CzzsfzjgNbBO) data.get(QysdsksMapConstant.VO_KEY_KSSBSJ);
		// 报表类型
		String bblx = (String) data.get(QysdsksMapConstant.STRING_KEY_BBLX);
		// 年度类型
		String ndlx = (String) data.get(QysdsNbConstant2014.STRING_KEY_NDLX);
		// 税务登记基本数据值对象
		SWDJJBSJ djjbsj = (SWDJJBSJ) data.get(QysdsksMapConstant.OBJECT_KEY_DJSJ);
		try {
			// 获得数据库连接
			conn = DBResource.getConnection();
            
			QysdsReportsDeclare report = this.ConvertBoToReportsDeclare(qysdsndbo, bblx, ndlx, djjbsj);
			// 获取数据库接口，调用save方法进行数据保存
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			iApp.saveSingleTable(report);//保存数据
			
		} catch (Exception ex) {
		    DBResource.destroyConnection(conn);
			throw ExceptionUtil.getBaseException(ex);
		}
		System.out.println("减免资格：" + qysdsndbo.getJmzg());
		insertJm(qysdsndbo, djjbsj, conn);
  
		if (ud.getCaflag()) {

			System.out.println("===========签名开始==========");
			try {
				String ywid = qyjb.getNsrxx().getJsjdm()
						+ "+"
						+ DjStringUtil
								.getCurrentDateStr(DjStringUtil.DATE_FORMAT5);
				System.out.println("======ywid:" + ywid);
				dzyj = dh.saveDzyjsj(dzyj, ywid, CAConstants.DADM_WHFZJG);
				System.out.println("===========签名结束==========");
			} catch (Exception ex) {
				System.out.println("===========签名失败==========");
				throw ExceptionUtil.getBaseException(ex);

			}

			retData.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
		}
		return retData;
	}
	/**
	 * 数据翻译接口
	 *
	 * @param qysdsbo
	 *            企业所得税BO对象
	 * @param bblx
	 *            报表类型
	 * @param djjbsj
	 *            登记数据对象
	 * @return
	 */
	private QysdsReportsDeclare ConvertBoToReportsDeclare(CzzsfzjgNbBO qysdsbo,String bblx,String ndlx, SWDJJBSJ djjbsj) {

		QysdsReportsDeclare report = new QysdsReportsDeclare();

		// 基本信息
		Jbxx jbxx = new Jbxx();
		// 基本信息(JBXX)-计算机代码
		jbxx.setJsjdm(qysdsbo.getJsjdm());
		// 基本信息(JBXX)-纳税人名称
		jbxx.setNsrmc(qysdsbo.getNsrmc());
		// 基本信息(JBXX)-联系电话
		jbxx.setLxdh(djjbsj.getJydzlxdm());
		// 基本信息(JBXX)-所属行业
		jbxx.setSshy(djjbsj.getGjbzhydm());
		// 基本信息(JBXX)-征收方式
		jbxx.setZsfs(qysdsbo.getQyzslx());
		// 向报表中添加纳税人基本信息
		report.setJbxx(jbxx);

		/**
		 * 应用编号
		 */
		report.setAppid(QysdsReportsConstants.CREPORTS_APPID_QYSDS);
		/**
		 * 版本号
		 */
		//report.setVersion(QysdsReportsConstants.CREPORTS_VERSION_QYSDS);
		report.setVersion(QysdsNbConstant2014.QYSDSNB_VERSION_2014);
		/**
		 * 纳税人计算机代码
		 */
		report.setNsrjsjdm(qysdsbo.getJsjdm());
		/**
		 * 纳税人名称
		 */
		report.setNsrmc(qysdsbo.getNsrmc());
		/**
		 * 报表期类型
		 */
		// 如果是季报数据则根据情况设置期号为1、2、3、4
		report.setBbqlx(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_YEAR);
		/**
		 * 期号
		 */
		report.setQh(ndlx);
		// 取得税款所属日期Map
		Timestamp curDate = new Timestamp(System.currentTimeMillis());
		Map skssrq = new HashMap();

		skssrq = Skssrq.yearSkssrq(curDate);

		String sbrq = "";

		try {
			/**
			 * 税款所属开始日期
			 */
			String skssksrq = DateTimeUtil.timestampToString((Timestamp) skssrq.get(Skssrq.SKSSKSRQ));
			report.setSkssksrq(new Date(TinyTools.stringToDate(skssksrq,"yyyy-MM-dd").getTime()));

			/**
			 * 税款所属结束日期
			 */
			String skssjsrq = DateTimeUtil.timestampToString((Timestamp) skssrq.get(Skssrq.SKSSJSRQ));
			report.setSkssjsrq(new Date(TinyTools.stringToDate(skssjsrq,"yyyy-MM-dd").getTime()));
			/**
			 * 申报日期
			 */
			sbrq = DateTimeUtil.timestampToString(curDate);
			report.setSbrq(new Date(TinyTools.stringToDate(sbrq, "yyyy-MM-dd").getTime()));
		} catch (Exception e) {
			System.out.println("转换申报期时出错！");
		}
		/**
		 * 税款年度
		 */
		report.setSknd((String) skssrq.get(Skssrq.SKSSRQ_ND));
		/**
		 * 税务机关组织机构代码
		 */
		report.setSwjgzzjgdm(qysdsbo.getSwjgzzjgdm());
		/**
		 * 录入人
		 */
		report.setLrr(qysdsbo.getJsjdm());
		/**
		 * 录入日期
		 */
		report.setLrrq(new Date(TinyTools.stringToDate(sbrq, "yyyy-MM-dd").getTime()));
		/**
		 * 创建人
		 */
		report.setCjr(qysdsbo.getJsjdm());
		/**
		 * 创建日期
		 */
		report.setCjrq(new Date(TinyTools.stringToDate(sbrq, "yyyy-MM-dd").getTime()));

		/**
		 * 区县代码
		 */
		report.setQxdm(djjbsj.getSwjgzzjgdm().substring(0,2));

		// 企业所得税报表内单表申明对象
		QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
		table.setTableId(bblx);
		table.setTableName(QysdsNbConstant2014.TABLE_NAME_CZZSSDSNB_2014);
		/**
		 * 填报表类型，和报表期类型一样
		 *
		 */

		table.setTbblx(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_YEAR);
		Map sbsjMap = ConvertSbsjBoToMap(qysdsbo);// 转换申报数据为map对象
		for (int i = 0; i < HCItem.length; i++) {
			if(sbsjMap.get(HCItem[i])==null)
				continue;
			QysdsReportsItemDeclare item_1_1 = new QysdsReportsItemDeclare();
			item_1_1.setItemID(HCItem[i]);
			System.out.println("***************"+HCItem[i]);
			item_1_1.setItemValue(sbsjMap.get(HCItem[i]).toString());
			item_1_1.setItemType("0");
			table.getCellContentList().put(item_1_1.getItemID(), item_1_1);
		}
		report.getTableContentList().put(table.getTableId(), table);
		return report;
	}
	/**
	 * 格式化所得税BO对象
	 *
	 * @param qysdsnbbo
	 * @return
	 */
	private Map ConvertSbsjBoToMap(CzzsfzjgNbBO qysdsNbbo) {
		Map map = new HashMap();		
		map.put("1", qysdsNbbo.getNsfs());
		map.put("2", qysdsNbbo.getZfjg());
		map.put("3", qysdsNbbo.getYysr());
		map.put("4", qysdsNbbo.getYycb());
		map.put("5", qysdsNbbo.getLrze());
		map.put("6", qysdsNbbo.getTdjsynssde());
		map.put("7", qysdsNbbo.getBzsr());
		map.put("8", qysdsNbbo.getMssr());
		map.put("9", qysdsNbbo.getJmzynssde());
		map.put("10", qysdsNbbo.getMbyqndks());
		map.put("11", qysdsNbbo.getSjlre());
		map.put("12", qysdsNbbo.getSl());
		map.put("13", qysdsNbbo.getYnsdse());
		map.put("14", qysdsNbbo.getJmsdse());
		map.put("15", qysdsNbbo.getXwqyjmsdse());
		map.put("16", qysdsNbbo.getSjyyjsdse());
		map.put("17", qysdsNbbo.getTdywyjsdse());
        map.put("18", qysdsNbbo.getYbtsdse());
        map.put("19", qysdsNbbo.getYqnddjsdse());
		map.put("20", qysdsNbbo.getBqsjybtsdse());
		map.put("30", qysdsNbbo.getZjgyftsdse());
		map.put("31", qysdsNbbo.getCzjzfpsdse());
		map.put("32", qysdsNbbo.getFzjgyftsdse());
		map.put("33", qysdsNbbo.getZjgdlscjybmyftsdse());
		//map.put("28", qysdsjbbo.getZjgycxfzjgyftsdse());
		map.put("34", qysdsNbbo.getFpbl());
		map.put("35", qysdsNbbo.getFpsdse());
		map.put("36", qysdsNbbo.getZczbje());
		map.put("37", qysdsNbbo.getZcze());
		
		return map;
	}
	/**
	 * 插入减免相关数据
	 * @param qysdsjdbo
	 * @param djjbsj
	 * @param conn
	 * @throws BaseException
	 */
	private void insertJm(CzzsfzjgNbBO qysdsndbo, SWDJJBSJ djjbsj, Connection conn) throws BaseException 
	{
		ORManager orManager = null;
		String sql = null;
		CallableStatement st = null;
		if ((qysdsndbo.getJmsdse() == null) || ("0.00".equals(qysdsndbo.getJmsdse()))|| ("".equals(qysdsndbo.getJmsdse()))) {
			qysdsndbo.setJmsdse("0");
		}

		Timestamp t1, t2;
		t1 = new Timestamp(System.currentTimeMillis());
		try {

			// 获得 ORManager
			orManager = DBResource.getORManager();
			Timestamp now = new Timestamp(System.currentTimeMillis());
			Jm jm = new Jm();
			jm.setJsjdm(djjbsj.getJsjdm());
			jm.setJmlx(CodeConstant.JMLX_SP); // 审批减免
			jm.setSzsmdm(SzsmdmConstant.QYSDS_SM);
			jm.setSbrq(TinyTools.second2Day(now));
			jm.setLrrq(now);
			jm.setJsje(new BigDecimal(qysdsndbo.getJmsdse()));
			jm.setKssl(null);
			jm.setJmse(jm.getJsje());
			jm.setSwjgzzjgdm(djjbsj.getSwjgzzjgdm());
			jm.setLrr(djjbsj.getJsjdm());
			jm.setFsdm(CodeConstant.FSDM_WSSB);
			jm.setDjzclxdm(djjbsj.getDjzclxdm());
			jm.setGjbzhydm(djjbsj.getGjbzhydm());
			jm.setYskmdm(null); // 在processor赋值
			jm.setYsjcdm(null); // 在processor赋值
			Timestamp skssksrq = qysdsndbo.getSkssksrq();
			Timestamp skssjsrq = qysdsndbo.getSkssjsrq();
			jm.setSkssksrq(skssksrq); // 税款所属开始时间
			jm.setSkssjsrq(skssjsrq); // 税款所属结束时间
			jm.setNd(qysdsndbo.getNd());
			jm.setCjrq(now);
			jm.setQxdm(djjbsj.getSwjgzzjgdm().substring(0, 2));
			String jmxmdm = (new ServiceProxy()).getJmsbs(djjbsj.getJsjdm(),
					SzsmdmConstant.QYSDS, skssksrq, skssjsrq);

			// 减免项目代码为空，从表单中取得企业的征收类型判断减免方式
			if (jmxmdm == null) {
				jmxmdm = CodeConstant.JMLXOTHER;
				jm.setJmlx(CodeConstant.JMLX_FD); // 审批减免
			}

			if (jmxmdm != null && !("".equals(jmxmdm))) {
				
				System.out.println("jmxmdm  "+jmxmdm);
				jm.setJmxmdm(jmxmdm); // 减免项目代码
				String ysjcdm = null;
				String yskmdm = null;							
				ysjcdm = FriendHelper.getYsjc(jm.getJsjdm(), jm.getSzsmdm(),jm.getSkssjsrq()).getYsjcdm();
				yskmdm = JKAdapter.getInstance().getYskm(jm.getSzsmdm(),jm.getDjzclxdm(), jm.getGjbzhydm(), ysjcdm).getYskmdm();
				jm.setYskmdm(yskmdm);
				jm.setYsjcdm(ysjcdm);
				try {

					String jsjdm = jm.getJsjdm();
					String jmlx = jm.getJmlx();
					String szsmdm = jm.getSzsmdm();
					Timestamp sbrq = jm.getSbrq();
					String fsdm = jm.getFsdm();
					String jzbz = jm.getJzbz();
					String lrr = jm.getLrr();
					String swjgzzjgdm = jm.getSwjgzzjgdm();
					String qxdm = jm.getQxdm();
					String djzclxdm = jm.getDjzclxdm();
					String gjbzhydm = jm.getGjbzhydm();
					String nd = jm.getNd();
					BigDecimal jmse = jm.getJmse();

					sql = "BEGIN sbdb.sb_pkg_sbjmtools.insertjm_jb(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?); END;";
					st = conn.prepareCall(sql);

					st.setString(1, jsjdm);
					st.setString(2, jmlx);
					st.setString(3, szsmdm);
					st.setTimestamp(4, sbrq);
					st.setString(5, fsdm);

					st.setString(6, jzbz);
					st.setString(7, lrr);
					st.setTimestamp(8, skssjsrq);
					st.setTimestamp(9, skssksrq);
					st.setString(10, swjgzzjgdm);

					st.setString(11, qxdm);
					st.setString(12, djzclxdm);
					st.setString(13, gjbzhydm);
					st.setString(14, nd);
					st.setString(15, ysjcdm);

					st.setString(16, yskmdm);
					st.setString(17, jmxmdm);
					st.setBigDecimal(18, jmse);

					st.execute();

				} catch (Exception ex4) {
					throw new ApplicationException(
							"插入减免申报表失败，数据库操作失败，请您找管理员联系！");
				}

			}

		} catch (Exception e) {
			throw ExceptionUtil.getBaseException(e);
		}finally {
			// 关闭数据库连接
			DBResource.destroyConnection(conn);
		}

		t2 = new Timestamp(System.currentTimeMillis());

		System.out.println("插入减免申报表数据耗时：" + (t2.getTime() - t1.getTime()));
	}
	
	
	/**
	 * 删除亏损申报数据
	 *
	 * @param data
	 *            Map
	 * @throws BaseException
	 */
	private void doDelete(VOPackage vop) throws BaseException 
	{
		DzyjHelper dh = new DzyjHelper();
		Map retData = new HashMap();
		Map data = (Map) vop.getData();
		UserData ud = vop.getUserData();
		Connection conn = null;

		CzzssdsNbVO qynb = (CzzssdsNbVO) data.get(CAcodeConstants.CA_MAPKEY_VO_XMLVO);
		DzyjsjVO dzyj = (DzyjsjVO) data.get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);

		CzzsfzjgNbBO qysdsnbbo = (CzzsfzjgNbBO) data.get(QysdsksMapConstant.VO_KEY_KSSBSJ);

		// 报表类型
		String bblx = (String) data.get(QysdsksMapConstant.STRING_KEY_BBLX);
		// 年度类型
		String ndlx = (String) data.get(QysdsNbConstant2014.STRING_KEY_NDLX);

		// 税务登记基本数据值对象
		SWDJJBSJ djjbsj = (SWDJJBSJ) data.get(QysdsksMapConstant.STRING_KEY_JBSJ);

		try {
			// 获得数据库连接
			conn = DBResource.getConnection();
			QysdsReportsDeclare qrd = new QysdsReportsDeclare();
			qrd.setAppid(QysdsReportsConstants.CREPORTS_APPID_QYSDS);
			//qrd.setVersion(QysdsReportsConstants.CREPORTS_VERSION_QYSDS);
			qrd.setVersion(QysdsNbConstant2014.QYSDSNB_VERSION_2014);
			qrd.setNsrjsjdm(qynb.getNsrxx().getJsjdm());

			qrd.setSknd(qynb.getSbxx().getNd());
			// 如果是季报数据则根据情况设置期号为1、2、3、4

			qrd.setBbqlx(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_YEAR);
			qrd.setQh(ndlx);

			QysdsReportsTableDeclare qrtd = new QysdsReportsTableDeclare();
			qrtd.setTableId(bblx);
			/**
			 * 填报表类型，和报表期类型一样
			 *
			 */
			qrtd.setTbblx(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_YEAR);

			Map tmp = new HashMap();
			tmp.put(bblx, qrtd);
			qrd.setTableContentList(tmp);

			// 获取数据库接口，调用save方法进行数据保存
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			iApp.deleteSingleTable(qrd);//删除数据

			// 插入一条金额为0的记录
			qysdsnbbo.setJmsdse("0");
			insertJm(qysdsnbbo, djjbsj, conn);

		} catch (Exception ex) {
		    DBResource.destroyConnection(conn);
			throw ExceptionUtil.getBaseException(ex);
		}

		if (ud.getCaflag()) {
			System.out.println("===========签名开始==========");
			try {
				String ywid = qynb.getNsrxx().getJsjdm()+ "+"+ DjStringUtil.getCurrentDateStr(DjStringUtil.DATE_FORMAT5);
				System.out.println("======ywid:" + ywid);
				dzyj = dh.saveDzyjsj(dzyj, ywid, CAConstants.DADM_WHFZJG);
				System.out.println("===========签名结束==========");
			} catch (Exception ex) {
				System.out.println("===========签名失败==========");
				throw ExceptionUtil.getBaseException(ex);
			}

			retData.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
		}
	}
}
