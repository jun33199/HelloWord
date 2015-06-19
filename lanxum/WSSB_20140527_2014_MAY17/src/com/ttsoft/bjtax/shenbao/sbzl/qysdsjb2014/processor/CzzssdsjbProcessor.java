package com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2014.processor;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2014.Constant;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2014.bo.CzzssdsBO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2014.bo.ZfjgqysdsjbBO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2014.xmlvo.CzzsCbJzmzxmVO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2014.xmlvo.CzzsCbMssrxmVO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2014.xmlvo.CzzsCbJmxmVO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2014.xmlvo.CzzssdsjbVO;
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
 * @version 1.0 2014�˶�������ҵ����˰����processor��
 * @author wangcy
 */
public class CzzssdsjbProcessor implements Processor {

    private String[] HCItem = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13",
        "14", "15", "16", "17", "18","19","20", "30", "31", "32", "33", "34", "35"};
    private String[] HCName = {"nsfs", "zfjg", "yysr", "yycb", "lrze", "tdjsynssde", "bzsr", "mssr","jmzynssde", "mbyqndks", "sjlre", "sl", "ynsdse", "jmsdse", "xwqyjmsdse","sjyyjsdse", "tdywyjsdse", "ybtsdse", "yqnddjsdse", "bqsjybtsdse", 
    							"zjgyftsdse", "czjzfpsdse", "fzjgyftsdse", "zjgdlscjybmyftsdse", "fpbl", "fpsdse"};

	public CzzssdsjbProcessor() {
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
	 */
	private Object doQuery(Map pData) throws BaseException {
		// ���ݿ����Ӷ���
		Connection conn = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd��");
		try {
			// ������ݿ�����
			conn = DBResource.getConnection();
			CzzssdsBO hdsdsbo = new CzzssdsBO();
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
			// ��������
			String jdlx = (String) pData.get(QysdsksMapConstant.STRING_KEY_JDLX);

			// ȡ��˰����������Map
			Map skssrq = new HashMap();

			skssrq = Skssrq.otherSkssrq(curDate);
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
			qrd.setVersion(Constant.QYSDSJB_VERSION_2014);
			qrd.setNsrjsjdm(jsjdm);

			qrd.setSknd(nd);
			// ����Ǽ��������������������ں�Ϊ1��2��3��4
			qrd.setBbqlx(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_QUARTOR);
			qrd.setQh(jdlx);

			QysdsReportsTableDeclare qrtd = new QysdsReportsTableDeclare();
			qrtd.setTableId(bblx);
			// qrtd.setTbblx(QysdsReportsConstants.CREPORTS_TABLE_REPORTTYPE_NORMAL_ONE_DIMENSIONALITY);

			/**
			 * ������ͣ��ͱ���������һ��
			 *
			 */

			qrtd.setTbblx(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_QUARTOR);

			Map tmp = new HashMap();
			tmp.put(bblx, qrtd);
			qrd.setTableContentList(tmp);

			qrd = (QysdsReportsDeclare) iApp.querySingleTable(qrd);//��ѯ����

			qrtd = (QysdsReportsTableDeclare) qrd.getTableContentList().get(
					bblx);
			if (qrtd == null) {
				System.out.println("======no value======");
			} else {
				HashMap map = new HashMap();
				for (int i = 0; i < HCItem.length; i++)
                {
//                    System.out.println("=============");
//                    System.out.println("HCItem[" + i + "] = " + HCItem[i]);
//                    System.out.println("HCName[" + i + "] = " + HCName[i]);
					if (qrtd.getCellContentList().get(HCItem[i]) != null)
                    {
						QysdsReportsItemDeclare qrtid = (QysdsReportsItemDeclare) qrtd.getCellContentList().get(HCItem[i]);
						// System.out.println("ItemID:" + qrtid.getItemID()
						// + " ItemValue:" + qrtid.getItemValue());
//                        System.out.println("value = " + qrtid.getItemValue() == null ? "0.00" : qrtid.getItemValue());
						if (qrtid.getItemValue() == null) {
							map.put(HCName[i], "0.00");
						} else {
							map.put(HCName[i], qrtid.getItemValue());
						}
					}
                    else {
//                        System.out.println("value = " + "0.00");
						map.put(HCName[i], "0.00");
					}
				}
				hdsdsbo.setSbsj(map);
				
				//��ȡ�ӱ�����
				querycbsj(hdsdsbo,qrtd);

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
	 * ��ѯ�ӱ��е�����
	 * @param hdsdsbo
	 * @param qrtd
	 */
	private void querycbsj(CzzssdsBO hdsdsbo,QysdsReportsTableDeclare qrtd){
		
		//��ȡ�ӱ�����,2015.01.09,zhangj
		HashMap cbsjMap=new HashMap();  
        HashMap cbMssrxmMap=new HashMap();
        HashMap cbJzmzxmMap=new HashMap();  
        HashMap cbJmxmMap=new HashMap();  
		List cbMssrxmList=new ArrayList();
		List cbJzmzxmList=new ArrayList();
		List cbJmxmList=new ArrayList();
        Iterator it = qrtd.getCellContentList().keySet().iterator();
        while(it.hasNext()){
        	String key=(String) it.next();
        	if(key.indexOf(".") > 0){    
        		
        		QysdsReportsItemDeclare item = (QysdsReportsItemDeclare) qrtd.getCellContentList().get(key);
        		if("777".equals(key.substring(0,3))){
        			cbMssrxmMap.put(item.getItemID(), item.getItemValue());  
        		}else if("888".equals(key.substring(0,3))){
        			cbJzmzxmMap.put(item.getItemID(), item.getItemValue());  
        		}else if("999".equals(key.substring(0,3))){
        			cbJmxmMap.put(item.getItemID(), item.getItemValue());  
        		} 
        	}		        	
        }
        
        if(cbMssrxmMap.size()==0){
        	cbMssrxmMap.put("777.1", "0101");
        	cbMssrxmMap.put("777.2", "0.00");
        }
        if(cbJzmzxmMap.size()==0){
        	cbJzmzxmMap.put("888.1", "0101");
        	cbJzmzxmMap.put("888.2", "0.00");
        }       
        if(cbJmxmMap.size()==0){
        	cbJmxmMap.put("999.1", "0101");
        	cbJmxmMap.put("999.2", "0.00");
        }
        
        for(int i=0;i<cbMssrxmMap.size();i++){
        	CzzsCbMssrxmVO cbMssrxmVO=new CzzsCbMssrxmVO();
        	cbMssrxmVO.setCbMssrxmDmhc("777."+(i+1));
        	cbMssrxmVO.setCbMssrxmDm((String) cbMssrxmMap.get("777."+(i+1)));
        	cbMssrxmVO.setCbMssrxmYzhc("777."+(i+2));
        	cbMssrxmVO.setCbMssrxmYz((String) cbMssrxmMap.get("777."+(i+2)));
        	cbMssrxmList.add(cbMssrxmVO);
        	i++;
        }
        cbsjMap.put(Constant.CZZSSDSJB_CBSJ_MSSRXM, cbMssrxmList);
        for(int i=0;i<cbJzmzxmMap.size();i++){
        	CzzsCbJzmzxmVO cbJzmzxmVO=new CzzsCbJzmzxmVO();
        	cbJzmzxmVO.setCbJzmzxmDmhc("888."+(i+1));
        	cbJzmzxmVO.setCbJzmzxmDm((String) cbJzmzxmMap.get("888."+(i+1)));
        	cbJzmzxmVO.setCbJzmzxmYzhc("888."+(i+2));
        	cbJzmzxmVO.setCbJzmzxmYz((String) cbJzmzxmMap.get("888."+(i+2)));
        	cbJzmzxmList.add(cbJzmzxmVO);
        	i++;
        }
        cbsjMap.put(Constant.CZZSSDSJB_CBSJ_JZMZXM, cbJzmzxmList);
        for(int i=0;i<cbJmxmMap.size();i++){
        	CzzsCbJmxmVO cbJmxmVO=new CzzsCbJmxmVO();
        	cbJmxmVO.setCbJmxmDmhc("999."+(i+1));
        	cbJmxmVO.setCbJmxmDm((String) cbJmxmMap.get("999."+(i+1)));
        	cbJmxmVO.setCbJmxmYzhc("999."+(i+2));
        	cbJmxmVO.setCbJmxmYz((String) cbJmxmMap.get("999."+(i+2)));
        	cbJmxmList.add(cbJmxmVO);
        	i++;
        }
        cbsjMap.put(Constant.CZZSSDSJB_CBSJ_JMXM, cbJmxmList);
        hdsdsbo.setCbSbsj(cbsjMap);
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
//		CallableStatement st = null;
//		String sql = "";
//		ORManager orManager = null;
		Connection conn = null;
		CzzssdsjbVO qyjb = (CzzssdsjbVO) data.get(CAcodeConstants.CA_MAPKEY_VO_XMLVO);
		DzyjsjVO dzyj = (DzyjsjVO) data.get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);

		CzzssdsBO qysdsjdbo = (CzzssdsBO) data.get(QysdsksMapConstant.VO_KEY_KSSBSJ);
		// ��������
		String bblx = (String) data.get(QysdsksMapConstant.STRING_KEY_BBLX);
		// ��������
		String jdlx = (String) data.get(QysdsksMapConstant.STRING_KEY_JDLX);
		// ˰��Ǽǻ�������ֵ����
		SWDJJBSJ djjbsj = (SWDJJBSJ) data.get(QysdsksMapConstant.OBJECT_KEY_DJSJ);
		try {
			// ������ݿ�����
			conn = DBResource.getConnection();
            
			QysdsReportsDeclare report = this.ConvertBoToReportsDeclare(qysdsjdbo, bblx, jdlx, djjbsj);
			// ��ȡ���ݿ�ӿڣ�����save�����������ݱ���
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			iApp.saveSingleTable(report);//��������
			
		} catch (Exception ex) {
//		    DBResource.destroyConnection(conn);
			throw ExceptionUtil.getBaseException(ex);
		}finally{
			DBResource.destroyConnection(conn);
		}
		System.out.println("�����ʸ�" + qysdsjdbo.getJmzg());
		insertJm(qysdsjdbo, djjbsj, conn);
  
		if (ud.getCaflag()) {

			System.out.println("===========ǩ����ʼ==========");
			try {
				String ywid = qyjb.getNsrxx().getJsjdm()+ "+"+ DjStringUtil.getCurrentDateStr(DjStringUtil.DATE_FORMAT5);
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
	 * ɾ�������걨����
	 *
	 * @param data
	 *            Map
	 * @throws BaseException
	 */
	private void doDelete(VOPackage vop) throws BaseException {
		DzyjHelper dh = new DzyjHelper();
		Map retData = new HashMap();
		Map data = (Map) vop.getData();
		UserData ud = vop.getUserData();
		Connection conn = null;


		CzzssdsjbVO qyjb = (CzzssdsjbVO) data.get(CAcodeConstants.CA_MAPKEY_VO_XMLVO);
		DzyjsjVO dzyj = (DzyjsjVO) data.get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);

		CzzssdsBO qysdsjbbo = (CzzssdsBO) data.get(QysdsksMapConstant.VO_KEY_KSSBSJ);

		// ��������
		String bblx = (String) data.get(QysdsksMapConstant.STRING_KEY_BBLX);
		// ��������
		String jdlx = (String) data.get(QysdsksMapConstant.STRING_KEY_JDLX);

		// ˰��Ǽǻ�������ֵ����
		SWDJJBSJ djjbsj = (SWDJJBSJ) data
				.get(QysdsksMapConstant.STRING_KEY_JBSJ);

		try {
			// ������ݿ�����
			conn = DBResource.getConnection();
			QysdsReportsDeclare qrd = new QysdsReportsDeclare();
			qrd.setAppid(QysdsReportsConstants.CREPORTS_APPID_QYSDS);
			//qrd.setVersion(QysdsReportsConstants.CREPORTS_VERSION_QYSDS);
			qrd.setVersion(Constant.QYSDSJB_VERSION_2014);
			qrd.setNsrjsjdm(qyjb.getNsrxx().getJsjdm());

			qrd.setSknd(qyjb.getSbxx().getNd());
			// ����Ǽ��������������������ں�Ϊ1��2��3��4

			qrd.setBbqlx(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_QUARTOR);
			qrd.setQh(jdlx);

			QysdsReportsTableDeclare qrtd = new QysdsReportsTableDeclare();
			qrtd.setTableId(bblx);
			// qrtd.setTbblx(QysdsReportsConstants.CREPORTS_TABLE_REPORTTYPE_NORMAL_ONE_DIMENSIONALITY);

			/**
			 * ������ͣ��ͱ���������һ��
			 *
			 */
			qrtd.setTbblx(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_QUARTOR);

			Map tmp = new HashMap();
			tmp.put(bblx, qrtd);
			qrd.setTableContentList(tmp);

			// ��ȡ���ݿ�ӿڣ�����save�����������ݱ���
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
			iApp.deleteSingleTable(qrd);//ɾ������

			// ����һ�����Ϊ0�ļ�¼
			qysdsjbbo.setJmsdse("0");
			insertJm(qysdsjbbo, djjbsj, conn);

		} catch (Exception ex) {
			throw ExceptionUtil.getBaseException(ex);
		}finally{
			DBResource.destroyConnection(conn);
		}

		if (ud.getCaflag()) {

			System.out.println("===========ǩ����ʼ==========");
			try {
				String ywid = qyjb.getNsrxx().getJsjdm()+ "+"+ DjStringUtil.getCurrentDateStr(DjStringUtil.DATE_FORMAT5);
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

	/**
	 * ���ݷ���ӿ�
	 *
	 * @param qysdsbo
	 *            ��ҵ����˰BO����
	 * @param bblx
	 *            ��������
	 * @param jdlx
	 *            ��������
	 * @param djjbsj
	 *            �Ǽ����ݶ���
	 * @return
	 */
	private QysdsReportsDeclare ConvertBoToReportsDeclare(CzzssdsBO qysdsbo,
			String bblx, String jdlx, SWDJJBSJ djjbsj) {

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

		/*
		 * //������Ϣ(JBXX)-������������ jbxx.setSsjjlx(form.getSsjjlx());
		 * //������Ϣ(JBXX)-�������� jbxx.setJmlx(form.getJmlx()); //������Ϣ(JBXX)-�ƻ��ƶ�
		 * jbxx.setCkzd(form.getCkzd()); // ������Ϣ(JBXX)-���ʹ�����ʽ //
		 * jbxx.setGzglxs(form.getGzglxs());
		 */

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
		report.setVersion(Constant.QYSDSJB_VERSION_2014);
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
		report.setBbqlx(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_QUARTOR);
		/**
		 * �ں�
		 */
		report.setQh(jdlx);

		// ȡ��˰����������Map
		Timestamp curDate = new Timestamp(System.currentTimeMillis());
		Map skssrq = new HashMap();

		skssrq = Skssrq.otherSkssrq(curDate);

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
		 * ���ش���
		 */
		report.setQxdm(djjbsj.getQxdm());
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
		report.setQxdm(qysdsbo.getSwjgzzjgdm().substring(0, 2));

		// ��ҵ����˰�����ڵ�����������
		QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
		table.setTableId(bblx);
		// table.setTbblx("0");

		/**
		 * ������ͣ��ͱ���������һ��
		 *
		 */
		table.setTbblx(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_QUARTOR);

		Map sbsjMap = ConvertSbsjBoToMap(qysdsbo);// ת���걨����Ϊmap����
		for (int i = 0; i < HCItem.length; i++) {
			System.out.println("HCItem[i]="+HCItem[i]+"|lje="+sbsjMap.get(HCItem[i]));
			if(sbsjMap.get(HCItem[i])==null)
				continue;
			QysdsReportsItemDeclare item_1_1 = new QysdsReportsItemDeclare();
			item_1_1.setItemID(HCItem[i]);
			item_1_1.setItemValue(sbsjMap.get(HCItem[i]).toString());
			item_1_1.setItemType("0");
			table.getCellContentList().put(item_1_1.getItemID(), item_1_1);
		}
		//���ӱ��е�����ת���洢��table��
		converCbsj(qysdsbo,table);
		
		report.getTableContentList().put(table.getTableId(), table);

		return report;

	}
	/**
	 * ���ӱ��е�����ת���洢��table��
	 * @param qysdsbo
	 * @param table
	 */
   private void converCbsj(CzzssdsBO qysdsbo,QysdsReportsTableDeclare table){
		//�ӱ����ݱ��棬2014.01.12��zhangj
		HashMap cbsjMap=qysdsbo.getCbSbsj();
		ArrayList cbMssrxmList=(ArrayList) cbsjMap.get(Constant.CZZSSDSJB_CBSJ_MSSRXM);
		ArrayList cbJzmzxmList=(ArrayList) cbsjMap.get(Constant.CZZSSDSJB_CBSJ_JZMZXM);
		ArrayList cbJmxmList=(ArrayList) cbsjMap.get(Constant.CZZSSDSJB_CBSJ_JMXM);
		
		for(int i=0;i<cbMssrxmList.size();i++){
			CzzsCbMssrxmVO cbsbsjvo=(CzzsCbMssrxmVO) cbMssrxmList.get(i);
			QysdsReportsItemDeclare item_1_1 = new QysdsReportsItemDeclare();
			item_1_1.setItemID(cbsbsjvo.getCbMssrxmDmhc());
			item_1_1.setItemValue(cbsbsjvo.getCbMssrxmDm());
			item_1_1.setItemType("0");
			table.getCellContentList().put(item_1_1.getItemID(), item_1_1);
			
			QysdsReportsItemDeclare item_1_2 = new QysdsReportsItemDeclare();
			item_1_2.setItemID(cbsbsjvo.getCbMssrxmYzhc());
			item_1_2.setItemValue(cbsbsjvo.getCbMssrxmYz());
			item_1_2.setItemType("0");
			table.getCellContentList().put(item_1_2.getItemID(), item_1_2);
		}
		
		for(int i=0;i<cbJzmzxmList.size();i++){
			CzzsCbJzmzxmVO cbsbsjvo=(CzzsCbJzmzxmVO) cbJzmzxmList.get(i);
			QysdsReportsItemDeclare item_1_1 = new QysdsReportsItemDeclare();
			item_1_1.setItemID(cbsbsjvo.getCbJzmzxmDmhc());
			item_1_1.setItemValue(cbsbsjvo.getCbJzmzxmDm());
			item_1_1.setItemType("0");
			table.getCellContentList().put(item_1_1.getItemID(), item_1_1);
			
			QysdsReportsItemDeclare item_1_2 = new QysdsReportsItemDeclare();
			item_1_2.setItemID(cbsbsjvo.getCbJzmzxmYzhc());
			item_1_2.setItemValue(cbsbsjvo.getCbJzmzxmYz());
			item_1_2.setItemType("0");
			table.getCellContentList().put(item_1_2.getItemID(), item_1_2);
		}
		for(int i=0;i<cbJmxmList.size();i++){
			CzzsCbJmxmVO cbsbsjvo=(CzzsCbJmxmVO) cbJmxmList.get(i);
			QysdsReportsItemDeclare item_1_1 = new QysdsReportsItemDeclare();
			item_1_1.setItemID(cbsbsjvo.getCbJmxmDmhc());
			item_1_1.setItemValue(cbsbsjvo.getCbJmxmDm());
			item_1_1.setItemType("0");
			table.getCellContentList().put(item_1_1.getItemID(), item_1_1);
			
			QysdsReportsItemDeclare item_1_2 = new QysdsReportsItemDeclare();
			item_1_2.setItemID(cbsbsjvo.getCbJmxmYzhc());
			item_1_2.setItemValue(cbsbsjvo.getCbJmxmYz());
			item_1_2.setItemType("0");
			table.getCellContentList().put(item_1_2.getItemID(), item_1_2);
		}
	   
   }
	/**
	 * ��ʽ������˰BO����
	 *
	 * @param qysdsnbbo
	 * @return
	 */
	private Map ConvertSbsjBoToMap(CzzssdsBO qysdsjbbo) {
		Map map = new HashMap();
		map.put("1", qysdsjbbo.getNsfs());
		map.put("2", qysdsjbbo.getZfjg());
		map.put("3", qysdsjbbo.getYysr());
		map.put("4", qysdsjbbo.getYycb());
		map.put("5", qysdsjbbo.getLrze());
		map.put("6", qysdsjbbo.getTdjsynssde());
		map.put("7", qysdsjbbo.getBzsr());
		map.put("8", qysdsjbbo.getMssr());
		map.put("9", qysdsjbbo.getJmzynssde());
		map.put("10", qysdsjbbo.getMbyqndks());
		map.put("11", qysdsjbbo.getSjlre());
		map.put("12", qysdsjbbo.getSl());
		map.put("13", qysdsjbbo.getYnsdse());
		map.put("14", qysdsjbbo.getJmsdse());
		map.put("15", qysdsjbbo.getXwqyjmsdse());
		map.put("16", qysdsjbbo.getSjyyjsdse());
		map.put("17", qysdsjbbo.getTdywyjsdse());
        map.put("18", qysdsjbbo.getYbtsdse());
        map.put("19", qysdsjbbo.getYqnddjsdse());
		map.put("20", qysdsjbbo.getBqsjybtsdse());
		map.put("30", qysdsjbbo.getZjgyftsdse());
		map.put("31", qysdsjbbo.getCzjzfpsdse());
		map.put("32", qysdsjbbo.getFzjgyftsdse());
		map.put("33", qysdsjbbo.getZjgdlscjybmyftsdse());
		//map.put("28", qysdsjbbo.getZjgycxfzjgyftsdse());
		map.put("34", qysdsjbbo.getFpbl());
		map.put("35", qysdsjbbo.getFpsdse());
		return map;
	}

	private void insertJm(CzzssdsBO qysdsjdbo, SWDJJBSJ djjbsj, Connection conn)
			throws BaseException {
		ORManager orManager = null;
		String sql = null;
		CallableStatement st = null;
		if ((qysdsjdbo.getJmsdse() == null)|| ("0.00".equals(qysdsjdbo.getJmsdse()))|| ("".equals(qysdsjdbo.getJmsdse()))) {
			qysdsjdbo.setJmsdse("0");
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
			jm.setJsje(new BigDecimal(qysdsjdbo.getJmsdse()));
			jm.setKssl(null);
			jm.setJmse(jm.getJsje());
			jm.setSwjgzzjgdm(djjbsj.getSwjgzzjgdm());
			jm.setLrr(djjbsj.getJsjdm());
			jm.setFsdm(CodeConstant.FSDM_WSSB);
			jm.setDjzclxdm(djjbsj.getDjzclxdm());
			jm.setGjbzhydm(djjbsj.getGjbzhydm());
			jm.setYskmdm(null); // ��processor��ֵ
			jm.setYsjcdm(null); // ��processor��ֵ
			Timestamp skssksrq = qysdsjdbo.getSkssksrq();
			Timestamp skssjsrq = qysdsjdbo.getSkssjsrq();
			jm.setSkssksrq(skssksrq); // ˰��������ʼʱ��
			jm.setSkssjsrq(skssjsrq); // ˰����������ʱ��
			jm.setNd(qysdsjdbo.getNd());
			jm.setCjrq(now);
			jm.setQxdm(djjbsj.getSwjgzzjgdm().substring(0, 2));
			String jmxmdm = (new ServiceProxy()).getJmsbs(djjbsj.getJsjdm(),
					SzsmdmConstant.QYSDS, skssksrq, skssjsrq);

			// ������Ŀ����Ϊ�գ��ӱ���ȡ����ҵ�����������жϼ��ⷽʽ
			if (jmxmdm == null) {

				System.out.println("**************   ȡ�õļ�����Ŀ����Ϊ�գ������걨�ļ�������putJm�е���ҵ��������qysdsjbbo.getQyzslx()Ϊ:"+ qysdsjdbo.getQyzslx());

				// if (qysdsjdbo.getQyzslx() != null) {

				// ��ҵ��������Ϊ���¼�����ҵ ���������Ϊ���¼�����ҵ����
				// if ("1".equals(qysdsjdbo.getQyzslx())
				// || "5".equals(qysdsjdbo.getQyzslx())) {

				// jmxmdm = CodeConstant.JMLX9010;

				// }
				// }
				jmxmdm = CodeConstant.JMLXOTHER;
				jm.setJmlx(CodeConstant.JMLX_FD); // ��������

			}

			if (jmxmdm != null && !("".equals(jmxmdm))) {

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
					// String sbrq = df.format(now);
					Timestamp sbrq = jm.getSbrq();
					String fsdm = jm.getFsdm();
					String jzbz = jm.getJzbz();
					String lrr = jm.getLrr();
					// String skssjsrq = df.format(declare.getSkssjsrq());
					// String skssksrq = df.format(declare.getSkssksrq());
					// Timestamp skssjsrq = jm.getSkssjsrq();
					// Timestamp skssksrq = jm.getSkssksrq();
					String swjgzzjgdm = jm.getSwjgzzjgdm();
					String qxdm = jm.getQxdm();
					String djzclxdm = jm.getDjzclxdm();
					String gjbzhydm = jm.getGjbzhydm();
					String nd = jm.getNd();
					// String ysjcdm = jm.getYsjcdm();
					// String yskmdm = jm.getYskmdm();
					// String jmxmdm = jm.getJmxmdm();
					BigDecimal jmse = jm.getJmse();

					sql = "BEGIN sbdb.sb_pkg_sbjmtools.insertjm_jb(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?); END;";

					st = conn.prepareCall(sql);

					System.out
							.println("-------------���ò�������걨�洢���̲���----------------");
					System.out.println("1-jsjdm--" + jsjdm);
					System.out.println("2-jmlx--" + jmlx);
					System.out.println("3-szsmdm--" + szsmdm);
					System.out.println("4-sbrq--" + sbrq);
					System.out.println("5-fsdm--" + fsdm);
					System.out.println("6-jzbz--" + jzbz);
					System.out.println("7-lrr--" + lrr);
					System.out.println("8-skssjsrq--" + skssjsrq);
					System.out.println("9-skssksrq--" + skssksrq);
					System.out.println("10-swjgzzjgdm--" + swjgzzjgdm);
					System.out.println("11-qxdm--" + qxdm);
					System.out.println("12-djzclxdm--" + djzclxdm);
					System.out.println("13-gjbzhydm--" + gjbzhydm);
					System.out.println("14-nd--" + nd);
					System.out.println("15-ysjcdm--" + ysjcdm);
					System.out.println("16-yskmdm--" + yskmdm);
					System.out.println("17-jmxmdm--" + jmxmdm);
					System.out.println("18-jmse--" + jmse);

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
				}finally{
					if(st!=null){
						st.close();
					}
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
}
