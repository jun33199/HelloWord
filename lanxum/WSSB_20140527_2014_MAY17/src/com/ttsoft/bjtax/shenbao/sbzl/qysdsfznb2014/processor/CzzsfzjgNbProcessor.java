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
 * @description : 2014�˶�������ҵ����˰�걨processor��
 * @author zhangj
 * @version 2014-9-17 ����11:57:37
 */
public class CzzsfzjgNbProcessor implements Processor {

	private String[] HCItem = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13",
            "14", "15", "16", "17", "18","19","20", "30", "31", "32", "33", "34", "35","36","37"};
    private String[] HCName = {"nsfs", "zfjg", "yysr", "yycb", "lrze", "tdjsynssde", "bzsr", "mssr","jmzynssde", "mbyqndks", "sjlre", "sl", "ynsdse", "jmsdse", "xwqyjmsdse","sjyyjsdse", "tdywyjsdse", "ybtsdse", "yqnddjsdse", "bqsjybtsdse", 
        							"zjgyftsdse", "czjzfpsdse", "fzjgyftsdse", "zjgdlscjybmyftsdse", "fpbl", "fpsdse","zczbje","zcze"};

	public CzzsfzjgNbProcessor() {
	}

	/**
	 * ����ҵ���������ֵ����ҵ�����
	 *
	 * @param vo
	 *            VOPackage
	 * @return java.lang.Object
	 * @throws com.ttsoft.framework.exception.BaseException
	 */
	public Object process(VOPackage vo) throws BaseException {
		// ����ҵ���������ֵ����ҵ�����
		try {
			switch (vo.getAction()) {
			// ��ѯ
			case QysdsksActionConstant.INT_ACTION_QUERY: {
				return doQuery((Map) vo.getData());
			}
			// ����
			case QysdsksActionConstant.INT_ACTION_SAVE: {
				return doSave(vo);
			}
			// ɾ��
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
	 * ��ѯ�Ǽ���Ϣ���걨����
	 *
	 * @param pData
	 *            Map
	 * @return Map
	 * @throws BaseException
	 * 
	 */
	private Object doQuery(Map pData) throws BaseException {
		// ���ݿ����Ӷ���
		Connection conn = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd��");
		try {
			// ������ݿ�����
			conn = DBResource.getConnection();
			CzzsfzjgNbBO hdsdsbo = new CzzsfzjgNbBO();
			// ���������
			String jsjdm = null;
			// ��ǰ����
			Timestamp curDate = null;
			// ˰��Ǽǻ�������ֵ����
			SWDJJBSJ djjbsj = (SWDJJBSJ) pData.get(QysdsksMapConstant.OBJECT_KEY_DJSJ);
			hdsdsbo.setJsjdm(djjbsj.getJsjdm());
			hdsdsbo.setNsrmc(djjbsj.getNsrmc());
			hdsdsbo.setNsrsbh(djjbsj.getSwdjzh());
			// ȡ�ü��������
			jsjdm = (String) pData.get(QysdsksMapConstant.STRING_KEY_JSJDM);
			// ȡ�����ڲ���
			curDate = (Timestamp) pData.get(QysdsksMapConstant.STRING_KEY_DATE);
			hdsdsbo.setSbrq(curDate);
			hdsdsbo.setSbrqshow(sdf.format(curDate));
			// ȡ�����ڵļ���
			String jd = Skssrq.preQuarter(curDate);
			hdsdsbo.setJd(jd);
			// ��������
			String bblx = (String) pData.get(QysdsksMapConstant.STRING_KEY_BBLX);
			// �������
			String ndlx = (String) pData.get(QysdsNbConstant2014.STRING_KEY_NDLX);
			// ȡ��˰����������Map
			Map skssrq = new HashMap();
			skssrq = Skssrq.yearSkssrq(curDate);
			// ȡ��˰��������ʼ�ͽ�������
			Timestamp skssksrq = (Timestamp) skssrq.get(Skssrq.SKSSKSRQ);
			Timestamp skssjsrq = (Timestamp) skssrq.get(Skssrq.SKSSJSRQ);
			hdsdsbo.setSkssksrq(skssksrq);
			hdsdsbo.setSkssjsrq(skssjsrq);

			// ȡ�����
			String nd = (String) skssrq.get(Skssrq.SKSSRQ_ND);
			hdsdsbo.setNd(nd);

			IAppAccess iApp = AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			QysdsReportsDeclare qrd = new QysdsReportsDeclare();
			qrd.setAppid(QysdsReportsConstants.CREPORTS_APPID_QYSDS);
			//qrd.setVersion(QysdsReportsConstants.CREPORTS_VERSION_QYSDS);
			qrd.setVersion(QysdsNbConstant2014.QYSDSNB_VERSION_2014);
			qrd.setNsrjsjdm(jsjdm);

			qrd.setSknd(nd);
			// ���������������������ں�Ϊ1��2��3��4     2Ϊ�걨
			qrd.setBbqlx(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_YEAR);
			// ������걨������̶������ں�Ϊ1
			qrd.setQh(ndlx);

			QysdsReportsTableDeclare qrtd = new QysdsReportsTableDeclare();
			qrtd.setTableId(bblx);
			//������ͣ��ͱ���������һ��
			qrtd.setTbblx(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_YEAR);
			Map tmp = new HashMap();
			tmp.put(bblx, qrtd);
			qrd.setTableContentList(tmp);
			qrd = (QysdsReportsDeclare) iApp.querySingleTable(qrd);//��ѯ����
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
			throw ExceptionUtil.getBaseException(ex, "��ѯ��ҵ����˰��������ʧ��");
		} finally {
			// �ر����ݿ�����
			DBResource.destroyConnection(conn);
		}
	}

	/**
	 * ������ҵ����˰��������
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
		// ��������
		String bblx = (String) data.get(QysdsksMapConstant.STRING_KEY_BBLX);
		// �������
		String ndlx = (String) data.get(QysdsNbConstant2014.STRING_KEY_NDLX);
		// ˰��Ǽǻ�������ֵ����
		SWDJJBSJ djjbsj = (SWDJJBSJ) data.get(QysdsksMapConstant.OBJECT_KEY_DJSJ);
		try {
			// ������ݿ�����
			conn = DBResource.getConnection();
            
			QysdsReportsDeclare report = this.ConvertBoToReportsDeclare(qysdsndbo, bblx, ndlx, djjbsj);
			// ��ȡ���ݿ�ӿڣ�����save�����������ݱ���
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			iApp.saveSingleTable(report);//��������
			
		} catch (Exception ex) {
		    DBResource.destroyConnection(conn);
			throw ExceptionUtil.getBaseException(ex);
		}
		System.out.println("�����ʸ�" + qysdsndbo.getJmzg());
		insertJm(qysdsndbo, djjbsj, conn);
  
		if (ud.getCaflag()) {

			System.out.println("===========ǩ����ʼ==========");
			try {
				String ywid = qyjb.getNsrxx().getJsjdm()
						+ "+"
						+ DjStringUtil
								.getCurrentDateStr(DjStringUtil.DATE_FORMAT5);
				System.out.println("======ywid:" + ywid);
				dzyj = dh.saveDzyjsj(dzyj, ywid, CAConstants.DADM_WHFZJG);
				System.out.println("===========ǩ������==========");
			} catch (Exception ex) {
				System.out.println("===========ǩ��ʧ��==========");
				throw ExceptionUtil.getBaseException(ex);

			}

			retData.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
		}
		return retData;
	}
	/**
	 * ���ݷ���ӿ�
	 *
	 * @param qysdsbo
	 *            ��ҵ����˰BO����
	 * @param bblx
	 *            ��������
	 * @param djjbsj
	 *            �Ǽ����ݶ���
	 * @return
	 */
	private QysdsReportsDeclare ConvertBoToReportsDeclare(CzzsfzjgNbBO qysdsbo,String bblx,String ndlx, SWDJJBSJ djjbsj) {

		QysdsReportsDeclare report = new QysdsReportsDeclare();

		// ������Ϣ
		Jbxx jbxx = new Jbxx();
		// ������Ϣ(JBXX)-���������
		jbxx.setJsjdm(qysdsbo.getJsjdm());
		// ������Ϣ(JBXX)-��˰������
		jbxx.setNsrmc(qysdsbo.getNsrmc());
		// ������Ϣ(JBXX)-��ϵ�绰
		jbxx.setLxdh(djjbsj.getJydzlxdm());
		// ������Ϣ(JBXX)-������ҵ
		jbxx.setSshy(djjbsj.getGjbzhydm());
		// ������Ϣ(JBXX)-���շ�ʽ
		jbxx.setZsfs(qysdsbo.getQyzslx());
		// �򱨱��������˰�˻�����Ϣ
		report.setJbxx(jbxx);

		/**
		 * Ӧ�ñ��
		 */
		report.setAppid(QysdsReportsConstants.CREPORTS_APPID_QYSDS);
		/**
		 * �汾��
		 */
		//report.setVersion(QysdsReportsConstants.CREPORTS_VERSION_QYSDS);
		report.setVersion(QysdsNbConstant2014.QYSDSNB_VERSION_2014);
		/**
		 * ��˰�˼��������
		 */
		report.setNsrjsjdm(qysdsbo.getJsjdm());
		/**
		 * ��˰������
		 */
		report.setNsrmc(qysdsbo.getNsrmc());
		/**
		 * ����������
		 */
		// ����Ǽ��������������������ں�Ϊ1��2��3��4
		report.setBbqlx(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_YEAR);
		/**
		 * �ں�
		 */
		report.setQh(ndlx);
		// ȡ��˰����������Map
		Timestamp curDate = new Timestamp(System.currentTimeMillis());
		Map skssrq = new HashMap();

		skssrq = Skssrq.yearSkssrq(curDate);

		String sbrq = "";

		try {
			/**
			 * ˰��������ʼ����
			 */
			String skssksrq = DateTimeUtil.timestampToString((Timestamp) skssrq.get(Skssrq.SKSSKSRQ));
			report.setSkssksrq(new Date(TinyTools.stringToDate(skssksrq,"yyyy-MM-dd").getTime()));

			/**
			 * ˰��������������
			 */
			String skssjsrq = DateTimeUtil.timestampToString((Timestamp) skssrq.get(Skssrq.SKSSJSRQ));
			report.setSkssjsrq(new Date(TinyTools.stringToDate(skssjsrq,"yyyy-MM-dd").getTime()));
			/**
			 * �걨����
			 */
			sbrq = DateTimeUtil.timestampToString(curDate);
			report.setSbrq(new Date(TinyTools.stringToDate(sbrq, "yyyy-MM-dd").getTime()));
		} catch (Exception e) {
			System.out.println("ת���걨��ʱ����");
		}
		/**
		 * ˰�����
		 */
		report.setSknd((String) skssrq.get(Skssrq.SKSSRQ_ND));
		/**
		 * ˰�������֯��������
		 */
		report.setSwjgzzjgdm(qysdsbo.getSwjgzzjgdm());
		/**
		 * ¼����
		 */
		report.setLrr(qysdsbo.getJsjdm());
		/**
		 * ¼������
		 */
		report.setLrrq(new Date(TinyTools.stringToDate(sbrq, "yyyy-MM-dd").getTime()));
		/**
		 * ������
		 */
		report.setCjr(qysdsbo.getJsjdm());
		/**
		 * ��������
		 */
		report.setCjrq(new Date(TinyTools.stringToDate(sbrq, "yyyy-MM-dd").getTime()));

		/**
		 * ���ش���
		 */
		report.setQxdm(djjbsj.getSwjgzzjgdm().substring(0,2));

		// ��ҵ����˰�����ڵ�����������
		QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
		table.setTableId(bblx);
		table.setTableName(QysdsNbConstant2014.TABLE_NAME_CZZSSDSNB_2014);
		/**
		 * ������ͣ��ͱ���������һ��
		 *
		 */

		table.setTbblx(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_YEAR);
		Map sbsjMap = ConvertSbsjBoToMap(qysdsbo);// ת���걨����Ϊmap����
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
	 * ��ʽ������˰BO����
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
	 * ��������������
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

			// ��� ORManager
			orManager = DBResource.getORManager();
			Timestamp now = new Timestamp(System.currentTimeMillis());
			Jm jm = new Jm();
			jm.setJsjdm(djjbsj.getJsjdm());
			jm.setJmlx(CodeConstant.JMLX_SP); // ��������
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
			jm.setYskmdm(null); // ��processor��ֵ
			jm.setYsjcdm(null); // ��processor��ֵ
			Timestamp skssksrq = qysdsndbo.getSkssksrq();
			Timestamp skssjsrq = qysdsndbo.getSkssjsrq();
			jm.setSkssksrq(skssksrq); // ˰��������ʼʱ��
			jm.setSkssjsrq(skssjsrq); // ˰����������ʱ��
			jm.setNd(qysdsndbo.getNd());
			jm.setCjrq(now);
			jm.setQxdm(djjbsj.getSwjgzzjgdm().substring(0, 2));
			String jmxmdm = (new ServiceProxy()).getJmsbs(djjbsj.getJsjdm(),
					SzsmdmConstant.QYSDS, skssksrq, skssjsrq);

			// ������Ŀ����Ϊ�գ��ӱ���ȡ����ҵ�����������жϼ��ⷽʽ
			if (jmxmdm == null) {
				jmxmdm = CodeConstant.JMLXOTHER;
				jm.setJmlx(CodeConstant.JMLX_FD); // ��������
			}

			if (jmxmdm != null && !("".equals(jmxmdm))) {
				
				System.out.println("jmxmdm  "+jmxmdm);
				jm.setJmxmdm(jmxmdm); // ������Ŀ����
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
							"��������걨��ʧ�ܣ����ݿ����ʧ�ܣ������ҹ���Ա��ϵ��");
				}

			}

		} catch (Exception e) {
			throw ExceptionUtil.getBaseException(e);
		}finally {
			// �ر����ݿ�����
			DBResource.destroyConnection(conn);
		}

		t2 = new Timestamp(System.currentTimeMillis());

		System.out.println("��������걨�����ݺ�ʱ��" + (t2.getTime() - t1.getTime()));
	}
	
	
	/**
	 * ɾ�������걨����
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

		// ��������
		String bblx = (String) data.get(QysdsksMapConstant.STRING_KEY_BBLX);
		// �������
		String ndlx = (String) data.get(QysdsNbConstant2014.STRING_KEY_NDLX);

		// ˰��Ǽǻ�������ֵ����
		SWDJJBSJ djjbsj = (SWDJJBSJ) data.get(QysdsksMapConstant.STRING_KEY_JBSJ);

		try {
			// ������ݿ�����
			conn = DBResource.getConnection();
			QysdsReportsDeclare qrd = new QysdsReportsDeclare();
			qrd.setAppid(QysdsReportsConstants.CREPORTS_APPID_QYSDS);
			//qrd.setVersion(QysdsReportsConstants.CREPORTS_VERSION_QYSDS);
			qrd.setVersion(QysdsNbConstant2014.QYSDSNB_VERSION_2014);
			qrd.setNsrjsjdm(qynb.getNsrxx().getJsjdm());

			qrd.setSknd(qynb.getSbxx().getNd());
			// ����Ǽ��������������������ں�Ϊ1��2��3��4

			qrd.setBbqlx(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_YEAR);
			qrd.setQh(ndlx);

			QysdsReportsTableDeclare qrtd = new QysdsReportsTableDeclare();
			qrtd.setTableId(bblx);
			/**
			 * ������ͣ��ͱ���������һ��
			 *
			 */
			qrtd.setTbblx(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_YEAR);

			Map tmp = new HashMap();
			tmp.put(bblx, qrtd);
			qrd.setTableContentList(tmp);

			// ��ȡ���ݿ�ӿڣ�����save�����������ݱ���
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			iApp.deleteSingleTable(qrd);//ɾ������

			// ����һ�����Ϊ0�ļ�¼
			qysdsnbbo.setJmsdse("0");
			insertJm(qysdsnbbo, djjbsj, conn);

		} catch (Exception ex) {
		    DBResource.destroyConnection(conn);
			throw ExceptionUtil.getBaseException(ex);
		}

		if (ud.getCaflag()) {
			System.out.println("===========ǩ����ʼ==========");
			try {
				String ywid = qynb.getNsrxx().getJsjdm()+ "+"+ DjStringUtil.getCurrentDateStr(DjStringUtil.DATE_FORMAT5);
				System.out.println("======ywid:" + ywid);
				dzyj = dh.saveDzyjsj(dzyj, ywid, CAConstants.DADM_WHFZJG);
				System.out.println("===========ǩ������==========");
			} catch (Exception ex) {
				System.out.println("===========ǩ��ʧ��==========");
				throw ExceptionUtil.getBaseException(ex);
			}

			retData.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
		}
	}
}
