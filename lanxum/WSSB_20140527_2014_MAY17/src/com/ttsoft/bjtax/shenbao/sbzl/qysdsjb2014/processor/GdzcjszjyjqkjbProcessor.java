package com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2014.processor;

/**
 * �̶��ʲ������۾ɣ��۳���Ԥ�����ͳ�Ʊ�processor
 * @description : 
 * @author zhangj
 * @version 1.0
 * @time 2014-12-5 ����05:08:41
 */
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjdks.QysdsksActionConstant;
import com.ttsoft.framework.exception.SystemException;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.framework.exception.BaseException;
import java.util.Map;
import com.ttsoft.framework.exception.ExceptionUtil;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import com.ttsoft.bjtax.shenbao.util.DBResource;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2014.bo.GdzcjszjyjqkjbBO;
import java.sql.Timestamp;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjdks.QysdsksMapConstant;
import java.util.HashMap;
import com.syax.creports.bo.qysds.QysdsReportsDeclare;
import com.syax.creports.bo.qysds.QysdsReportsConstants;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2014.Constant;
import com.syax.creports.bo.qysds.QysdsReportsTableDeclare;
import com.syax.creports.persistent.access.IAppAccess;
import com.syax.creports.persistent.AppAccessFactory;
import com.syax.creports.bo.qysds.QysdsReportsItemDeclare;
import com.ttsoft.bjtax.shenbao.util.Skssrq;
import com.ttsoft.framework.exception.ApplicationException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2014.QysdsUtil2014;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.syax.bjtax.ca.util.DzyjHelper;
import com.ttsoft.common.model.UserData;
import java.sql.CallableStatement;
import com.ekernel.db.or.ORManager;
import com.syax.common.util.CAcodeConstants;
import com.syax.bjtax.ca.vo.DzyjsjVO;
import com.ttsoft.bjtax.dj.CAConstants;
import com.ttsoft.bjtax.dj.util.DjStringUtil;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2014.xmlvo.GdzcjszjyjqkjbSbsjVO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2014.xmlvo.GdzcjszjyjqkjbVO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2014.xmlvo.GdzcjszjyjqkjblistVO;
import com.syax.creports.bo.qysds.Jbxx;
import com.ttsoft.framework.util.DateTimeUtil;
import java.sql.Date;
import com.ttsoft.bjtax.shenbao.util.TinyTools;

import java.sql.*;
import java.math.BigDecimal;
import com.syax.creports.util.Arith;

public class GdzcjszjyjqkjbProcessor implements Processor
{
    //��ʼ��������
    private QysdsUtil2014 sdsUtil = new QysdsUtil2014();
    private String[] HCName = {"һ��������ҵ�̶��ʲ�", "  ��һ������ҩƷ����ҵ", "  ������ר���豸����ҵ", "  ��������·�����������պ�������������豸����ҵ", "  ���ģ��������ͨ�ź����������豸����ҵ", "  ���壩�����Ǳ�����ҵ",
    					"  ��������Ϣ���䡢�������Ϣ��������ҵ", "  ���ߣ�������ҵ", "��������һ���Կ۳��Ĺ̶��ʲ�", "  ��һ����λ��ֵ������100��Ԫ���з��������豸", "   ���У�������ҵС��΢����ҵ�з���������Ӫ���õ��������豸", 
    					"  ��������λ��ֵ������5000Ԫ�Ĺ̶��ʲ�"};
    
    private String[] LCName = {"hc", "xm", "fwjzw_yz", "fwjzw_bqzje", "fwjzw_ljzje", "jqsbhqtgdzc_yz","jqsbhqtgdzc_bqzje", "jqsbhqtgdzc_ljzje", "hj_yz", "hj_bqzje_zczje", "hj_bqzje_jszje", 
			"hj_ljzje_zczje", "hj_ljzje_jszje"};
    
	String [] HC={"1","2","3","4","5","6","7","8","9","10","11","12"};
	String ZJ_HC="13";
	private String ZJ_HCNAME="��          ��                             ";
    
    public GdzcjszjyjqkjbProcessor()
    {
    }

    /**
     * ����ҵ���������ֵ����ҵ�����
     *
     * @param vo
     *            VOPackage
     * @return java.lang.Object
     * @throws com.ttsoft.framework.exception.BaseException
     */
    public Object process(VOPackage vo) throws BaseException
    {
        // ����ҵ���������ֵ����ҵ�����
        try {
            switch(vo.getAction()) {
                // ��ѯ
                case QysdsksActionConstant.INT_ACTION_QUERY: {
                    return doQuery( (Map)vo.getData());
                }
                // ����
                case QysdsksActionConstant.INT_ACTION_SAVE: {
                    return doSave(vo);
                }
                // ɾ��
                case QysdsksActionConstant.INT_ACTION_DELETE: {
                    return doDelete(vo);
                }
                default:
                    throw new SystemException("no such method");
            }
        }
        catch(Exception e) {
            throw ExceptionUtil.getBaseException(e);
        }
    }

    /**
     * ��ѯ�̶��ʲ������۾ɣ��۳���Ԥ�����ͳ�Ʊ���Ϣ
     *
     * @param pData
     *            Map
     * @return Map
     * @throws BaseException
     */
    private Object doQuery(Map pData) throws BaseException
    {
        // ���ݿ����Ӷ���
        Connection conn = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd��");
        try {
            // ������ݿ�����
            conn = DBResource.getConnection();
            GdzcjszjyjqkjbBO gdzcbo = new GdzcjszjyjqkjbBO();
            // ���������
            String jsjdm = null;
            // ��ǰ����
            Timestamp curDate = null;
            // ˰��Ǽǻ�������ֵ����
            SWDJJBSJ djjbsj = (SWDJJBSJ)pData.get(QysdsksMapConstant.OBJECT_KEY_DJSJ);
            gdzcbo.setJsjdm(djjbsj.getJsjdm());
            gdzcbo.setNsrmc(djjbsj.getNsrmc());
            gdzcbo.setNsrsbh(djjbsj.getSwdjzh());

            // ȡ�ü��������
            jsjdm = (String)pData.get(QysdsksMapConstant.STRING_KEY_JSJDM);
            // ȡ�����ڲ���
            curDate = (Timestamp)pData.get(QysdsksMapConstant.STRING_KEY_DATE);
            gdzcbo.setSbrq(curDate);
            gdzcbo.setSbrqshow(sdf.format(curDate));
            // ȡ�����ڵļ���
            String jd = Skssrq.preQuarter(curDate);
            gdzcbo.setJd(jd);
            // �������� - ��ID
            String bblx = (String)pData.get(QysdsksMapConstant.STRING_KEY_BBLX);
            // �������� - �ں�
            String jdlx = (String)pData.get(QysdsksMapConstant.STRING_KEY_JDLX);
            gdzcbo.setJd(jdlx); //�ں�-�����ںż�Ϊ������
            System.out.println("jsjdm = " + jsjdm + "\nbblx = " + bblx + "\nqh = " + jdlx);

            // ȡ��˰����������Map
            Map skssrq = new HashMap();
            if(bblx.equals(Constant.TABLE_ID_GDZCJSZJYJQK_2014)) {
                skssrq = Skssrq.otherSkssrq(curDate);
            }
            else {
                throw new ApplicationException("���ݵı���ID����");
            }
            // ȡ��˰��������ʼ�ͽ�������
            Timestamp skssksrq = (Timestamp)skssrq.get(Skssrq.SKSSKSRQ);
            Timestamp skssjsrq = (Timestamp)skssrq.get(Skssrq.SKSSJSRQ);
            gdzcbo.setSkssksrq(skssksrq);
            gdzcbo.setSkssjsrq(skssjsrq);

            // ȡ�����
            String nd = (String)skssrq.get(Skssrq.SKSSRQ_ND);
            gdzcbo.setNd(nd);
            System.out.println("nd = " + nd);

            //����������
            gdzcbo.setBbqlx(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_QUARTOR);

            // ����QysdsReportsDeclare����
            QysdsReportsDeclare qrd = new QysdsReportsDeclare();
            // ����QysdsReportsTableDeclare�Ļ�����Ϣ
            QysdsReportsTableDeclare qrtd = new QysdsReportsTableDeclare();

            qrd.setAppid(QysdsReportsConstants.CREPORTS_APPID_QYSDS); //aid
            //qrd.setVersion(QysdsReportsConstants.CREPORTS_VERSION_QYSDS); //version
            qrd.setVersion(Constant.QYSDSJB_VERSION_2014);
            qrd.setNsrjsjdm(jsjdm); //���������
            qrd.setSknd(nd); //˰�����
            // ������ǻ�����˰��֧�����������쳣
            if(bblx.equals(Constant.TABLE_ID_GDZCJSZJYJQK_2014)) {
                //����������
                qrd.setBbqlx(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_QUARTOR);
                //�ں�
                qrd.setQh(jdlx);

                //����ID
                qrtd.setTableId(bblx);
                qrtd.setTbblx(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_QUARTOR);
            }
            else {
                throw new ApplicationException("���ݵı���ID����");
            }
            // qrtd.setTbblx(QysdsReportsConstants.CREPORTS_TABLE_REPORTTYPE_NORMAL_ONE_DIMENSIONALITY);

            // ��QysdsReportsTableDeclare�Ļ�����Ϣ����QysdsReportsDeclare
            Map tmp = new HashMap();
            tmp.put(bblx, qrtd);
            qrd.setTableContentList(tmp);

            // ��ȡ���ݿ�Ӧ�ýӿ�
            IAppAccess iApp = AppAccessFactory.getAInstance(conn, AppAccessFactory.ACCESS_MODEL_APP_QYSDS);

            // ���ò�ѯ�������в�ѯ
            qrd = (QysdsReportsDeclare)iApp.querySingleTable(qrd);

            // ��ȡ��ѯ���ľ�������
            qrtd = (QysdsReportsTableDeclare)qrd.getTableContentList().get(bblx);

            // �����ݿ��е����ݷ����ҳ���������ݸ�ʽ
            if(qrtd == null) {
                System.out.println("======no value======");
            }
            else {
                //��ȡ���������,��id, value ��ʽ����
            	GdzcjszjyjqkjblistVO list = (GdzcjszjyjqkjblistVO)this.translate2Page(qrtd);

                gdzcbo.setSbsj(list);
                
                //�����ݿ��л�ȡ������ҵ����
                this.getSshymc(gdzcbo,qrtd);
                
            }
            return gdzcbo;
        }
        catch(Exception ex) {
            throw ExceptionUtil.getBaseException(ex, "��ѯ��ҵ����˰������˰��֧������������ʧ��");
        }
        finally {
            // �ر����ݿ�����
            DBResource.destroyConnection(conn);
        }
    }



    /**
     * ������ת����ҳ��չʾ����ʽ
     * @param table QysdsReportsTableDeclare
     * @param maxIndex int
     * @return Map
     */

    private GdzcjszjyjqkjblistVO translate2Page(QysdsReportsTableDeclare qrtd)
    {
    	GdzcjszjyjqkjblistVO sbsjlist=new GdzcjszjyjqkjblistVO();
    	GdzcjszjyjqkjbSbsjVO sbsj=new GdzcjszjyjqkjbSbsjVO();
    	String [][] valueslist=new String [13][12];
    	int DB_HC;
    	valueslist[0]=HC;
    	valueslist[1]=HCName;
    	
    	for(int i=2;i<13;i++){
    		String [] values=new String [12];
    		for(int j=0;j<13;j++){
    			String yz="";
    			DB_HC=j*(13-2)+(i-1);
				if (qrtd.getCellContentList().get(DB_HC+"") != null)
                {
					QysdsReportsItemDeclare qrtid = (QysdsReportsItemDeclare) qrtd
							.getCellContentList().get(DB_HC+"");
					yz=qrtid.getItemValue();
					if (yz == null) {
						yz= "";
					} 
				}
    			if(j!=12){
        			values[j]=yz;
    			}else{
    				sbsjlist.setZj_hc(ZJ_HC);
    				sbsjlist.setZj_xm(ZJ_HCNAME);
    				switch(i){
    					case 2:
    						sbsjlist.setZj_fwjzw_yz(yz);
    						break;
    					case 3:
    						sbsjlist.setZj_fwjzw_bqzje(yz);
    						break;
    					case 4:	
    						sbsjlist.setZj_fwjzw_ljzje(yz);
    					case 5:
    						sbsjlist.setZj_jqsbhqtgdzc_yz(yz);    
    						break;
    					case 6:	
    						sbsjlist.setZj_jqsbhqtgdzc_bqzje(yz);
    					case 7:
    						sbsjlist.setZj_jqsbhqtgdzc_ljzje(yz);
    						break;
    					case 8:		
    						sbsjlist.setZj_hj_yz(yz);
    						break;
    					case 9:	
    						sbsjlist.setZj_hj_bqzje_zczje(yz);
    						break;
    					case 10:
    						sbsjlist.setZj_hj_bqzje_jszje(yz);
    						break;
    					case 11:
    						sbsjlist.setZj_hj_ljzje_zczje(yz);   
    						break;
    					case 12:
    						sbsjlist.setZj_hj_ljzje_jszje(yz); 
    						break;
    				}
    			}

    		}
    		valueslist[i]=values;
    	}
    	   	
    	sbsj.setHc(valueslist[0]);
    	sbsj.setXm(valueslist[1]);
    	sbsj.setFwjzw_yz(valueslist[2]);
    	sbsj.setFwjzw_bqzje(valueslist[3]);
    	sbsj.setFwjzw_ljzje(valueslist[4]);
    	sbsj.setJqsbhqtgdzc_yz(valueslist[5]);    	
    	sbsj.setJqsbhqtgdzc_bqzje(valueslist[6]);
    	sbsj.setJqsbhqtgdzc_ljzje(valueslist[7]);
    	sbsj.setHj_yz(valueslist[8]);
    	sbsj.setHj_bqzje_zczje(valueslist[9]);
    	sbsj.setHj_bqzje_jszje(valueslist[10]);
    	sbsj.setHj_ljzje_zczje(valueslist[11]);    
    	sbsj.setHj_ljzje_jszje(valueslist[12]); 
    	
    	sbsjlist.setSbsj(sbsj);
        return sbsjlist;
    }
	/**
	 * ����̶��ʲ������۾ɣ��۳���Ԥ�����ͳ�Ʊ�����
	 *
	 * @param data
	 *            Map
	 * @throws BaseException
	 */
	private Map doSave(VOPackage vop) throws BaseException {
		System.out.println("................zhangj..save start");
		DzyjHelper dh = new DzyjHelper();
		Map retData = new HashMap();
		Map data = (Map) vop.getData();
		UserData ud = vop.getUserData();
		CallableStatement st = null;
		String sql = "";
		ORManager orManager = null;
		Connection conn = null;
		GdzcjszjyjqkjbVO qysdsjbvo = (GdzcjszjyjqkjbVO)data.get(CAcodeConstants.CA_MAPKEY_VO_XMLVO);
        //DzyjsjVO
        DzyjsjVO dzyj = (DzyjsjVO)data.get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);
        //ZfjgqysdsjbBO
//        GdzcjszjyjqkjbBO qysdsjbbo = (GdzcjszjyjqkjbBO)data.get(QysdsksMapConstant.VO_KEY_KSSBSJ);
        // ��������
        String tableID = (String)data.get(QysdsksMapConstant.STRING_KEY_BBLX);
        // ��������
        String jdlx = (String)data.get(QysdsksMapConstant.STRING_KEY_JDLX);
        
//        System.out.println("tableID = " + tableID + "\njdlx = " + jdlx);
        // ˰��Ǽǻ�������ֵ����
        SWDJJBSJ djjbsj = (SWDJJBSJ)data.get(QysdsksMapConstant.OBJECT_KEY_DJSJ);
        try {
            // ������ݿ�����
            conn = DBResource.getConnection();
            QysdsReportsDeclare report = this.ConvertBoToReportsDeclare(qysdsjbvo, tableID, jdlx, djjbsj);
//            System.out.println("==============================");
//            System.out.println("bbqlx = " + report.getBbqlx());
            QysdsReportsTableDeclare table = (QysdsReportsTableDeclare)report.getTableContentList().get(Constant.TABLE_ID_GDZCJSZJYJQK_2014);
//            System.out.println("table id = " + table.getTableId() + "\n table name = " + table.getTableName()
//                 + "\ntbblx = " + table.getTbblx());
            // ��ȡ���ݿ�ӿڣ�����save�����������ݱ���
            IAppAccess iApp = AppAccessFactory.getAInstance(conn, AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
            iApp.saveSingleTable(report);
			DBResource.destroyConnection(conn);
			
			System.out.println("................zhangj..save end");
        }
        catch(Exception ex) {
		    DBResource.destroyConnection(conn);
            throw ExceptionUtil.getBaseException(ex);
        }
        
        if(ud.getCaflag()) {
            System.out.println("===========ǩ����ʼ==========");
            try {
                String ywid = qysdsjbvo.getNsrxx().getJsjdm() + "+"
                    + DjStringUtil.getCurrentDateStr(DjStringUtil.DATE_FORMAT5);
                System.out.println("======ywid:" + ywid);
                dzyj = dh.saveDzyjsj(dzyj, ywid, CAConstants.DADM_WHFZJG);
                System.out.println("===========ǩ������==========");
            }
            catch(Exception ex) {
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
     * @param tableID
     *            ����ID
     * @param jdlx
     *            ��������
     * @param djjbsj
     *            �Ǽ����ݶ���
     * @return
     */
    private QysdsReportsDeclare ConvertBoToReportsDeclare(GdzcjszjyjqkjbVO qysdsvo,
        String tableID, String jdlx, SWDJJBSJ djjbsj)
    {
        QysdsReportsDeclare report = new QysdsReportsDeclare();

        // ������Ϣ
        Jbxx jbxx = new Jbxx();
        
        // ������Ϣ(JBXX)-���������
        jbxx.setJsjdm(djjbsj.getJsjdm());
        // ������Ϣ(JBXX)-��˰������
        jbxx.setNsrmc(djjbsj.getNsrmc());
        
        // ������Ϣ(JBXX)-��ϵ�绰
        jbxx.setLxdh(djjbsj.getJydzlxdm());
        // ������Ϣ(JBXX)-������ҵ
        jbxx.setSshy(djjbsj.getGjbzhydm());
//        System.out.println("..............zhangj.....sshy: "+jbxx.getSshy()+djjbsj.getGjbzhymc());
        // ������Ϣ(JBXX)-���շ�ʽ
        jbxx.setZsfs("");

        // �򱨱��������˰�˻�����Ϣ
        report.setJbxx(jbxx);

        /**
         * Ӧ�ñ��
         */
        report.setAppid(QysdsReportsConstants.CREPORTS_APPID_QYSDS);
        /**
         * �汾��
         */
        report.setVersion(Constant.QYSDSJB_VERSION_2014);
        /**
         * ��˰�˼��������
         */
        report.setNsrjsjdm(djjbsj.getJsjdm());
        /**
         * ��˰������
         */
        //report.setNsrmc(qysdsbo.getNsrmc());
        report.setNsrmc(djjbsj.getNsrmc());
        /**
         * ����������
         */
        // ������걨������̶������ں�Ϊ1������Ǽ��������������������ں�Ϊ1��2��3��4
        //����
        report.setBbqlx(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_QUARTOR);
        /**�ں�*/
        report.setQh(jdlx);

        // ȡ��˰����������Map
        Timestamp curDate = new Timestamp(System.currentTimeMillis());
        Map skssrq = new HashMap();

        skssrq = Skssrq.otherSkssrq(curDate);

        String sbrq = "";
        try {
            /**˰��������*/
            //skssksrq
            String skssksrq = DateTimeUtil.timestampToString( (Timestamp)skssrq.get(Skssrq.SKSSKSRQ));
            report.setSkssksrq(new Date(TinyTools.stringToDate(skssksrq, "yyyy-MM-dd").getTime()));
            //skssjsrq
            String skssjsrq = DateTimeUtil.timestampToString( (Timestamp)skssrq.get(Skssrq.SKSSJSRQ));
            report.setSkssjsrq(new Date(TinyTools.stringToDate(skssjsrq, "yyyy-MM-dd").getTime()));
            // �걨����
            sbrq = DateTimeUtil.timestampToString(curDate);
            report.setSbrq(new Date(TinyTools.stringToDate(sbrq, "yyyy-MM-dd").getTime()));
        }
        catch(Exception e) {
            System.out.println("ת���걨��ʱ����");
        }
        // ˰�����
        report.setSknd( (String)skssrq.get(Skssrq.SKSSRQ_ND));
        /**
         * ˰�������֯��������
         */
        report.setSwjgzzjgdm(djjbsj.getSwjgzzjgdm());
        /**
         * ���ش���
         */
        report.setQxdm(djjbsj.getSwjgzzjgdm().substring(0,2));
        /**
         * ¼����
         */
        report.setLrr(djjbsj.getJsjdm());
        /**
         * ¼������
         */
        report.setLrrq(new Date(TinyTools.stringToDate(sbrq, "yyyy-MM-dd").getTime()));
        /**
         * ������
         */
        report.setCjr(djjbsj.getJsjdm());
        /**
         * ��������
         */
        report.setCjrq(new Date(TinyTools.stringToDate(sbrq, "yyyy-MM-dd").getTime()));

        // ��ҵ����˰�����ڵ�����������
        QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
        table.setTableId(tableID);
        table.setTableName(Constant.TABLE_NAME_GDZCJSZJYJQK_2014);
        /**
         * ������ͣ��ͱ���������һ��
         *
         */

        table.setTbblx(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_QUARTOR);
        GdzcjszjyjqkjblistVO  sbsjlistVO=qysdsvo.getSbsjlist();
        GdzcjszjyjqkjbSbsjVO  sbsjVO =sbsjlistVO.getSbsj();
        for(int i=0;i<sbsjVO.getHc().length+1;i++){
        	int id;
        	String value="";
        	for(int j=0;j<11;j++){
        		id=i*11+(j+1);
        		if(i!=sbsjVO.getHc().length){
            		switch(j){
        			case 0:
        				value=sbsjVO.getFwjzw_yz()[i];
        				break;
        			case 1:
        				value=sbsjVO.getFwjzw_bqzje()[i];
        				break;  
        			case 2:
        				value=sbsjVO.getFwjzw_ljzje()[i];
        				break;
        			case 3:
        				value=sbsjVO.getJqsbhqtgdzc_yz()[i];
        				break;
        			case 4:
        				value=sbsjVO.getJqsbhqtgdzc_bqzje()[i];
        				break;
        			case 5:
        				value=sbsjVO.getJqsbhqtgdzc_ljzje()[i];
        				break;  
        			case 6:
        				value=sbsjVO.getHj_yz()[i];
        				break;
        			case 7:
        				value=sbsjVO.getHj_bqzje_zczje()[i];
        				break;
        			case 8:
        				value=sbsjVO.getHj_bqzje_jszje()[i];
        				break;
        			case 9:
        				value=sbsjVO.getHj_ljzje_zczje()[i];
        				break;  
        			case 10:
        				value=sbsjVO.getHj_ljzje_jszje()[i];
        				break;
      				
            		}
        		}else{
            		switch(j){
        			case 0:
        				value=sbsjlistVO.getZj_fwjzw_yz();
        				break;
        			case 1:
        				value=sbsjlistVO.getZj_fwjzw_bqzje();
        				break;  
        			case 2:
        				value=sbsjlistVO.getZj_fwjzw_ljzje();
        				break;
        			case 3:
        				value=sbsjlistVO.getZj_jqsbhqtgdzc_yz();
        				break;
        			case 4:
        				value=sbsjlistVO.getZj_jqsbhqtgdzc_bqzje();
        				break;
        			case 5:
        				value=sbsjlistVO.getZj_jqsbhqtgdzc_ljzje();
        				break;  
        			case 6:
        				value=sbsjlistVO.getZj_hj_yz();
        				break;
        			case 7:
        				value=sbsjlistVO.getZj_hj_bqzje_zczje();
        				break;
        			case 8:
        				value=sbsjlistVO.getZj_hj_bqzje_jszje();
        				break;
        			case 9:
        				value=sbsjlistVO.getZj_hj_ljzje_zczje();
        				break;  
        			case 10:
        				value=sbsjlistVO.getZj_hj_ljzje_jszje();
        				break;
      				
            		}
        		}
        		if(value==null||"".equals(value)) continue;
    			QysdsReportsItemDeclare item_1_1 = new QysdsReportsItemDeclare();
    			item_1_1.setItemID(String.valueOf(id));
    			item_1_1.setItemValue(value);
    			System.out.println("hc:"+id+"yz:"+value);
    			item_1_1.setItemType("0");
    			table.getCellContentList().put(item_1_1.getItemID(), item_1_1);
        	}

        }
        
		QysdsReportsItemDeclare item_1_1 = new QysdsReportsItemDeclare();
		item_1_1.setItemID("999");
		item_1_1.setItemValue(qysdsvo.getNsrxx().getSshydm());
		System.out.println("hc:"+999+"yz:"+qysdsvo.getNsrxx().getSshydm());
		item_1_1.setItemType("0");
		table.getCellContentList().put(item_1_1.getItemID(), item_1_1);
		
		
        report.getTableContentList().put(table.getTableId(), table);

        return report;
    }
    
    /**
     * ɾ���걨����
     *
     * @param data
     *            Map
     * @throws BaseException
     */
    private Map doDelete(VOPackage vop) throws BaseException
    {
    	System.out.println(".............zhangj...dodelete..start");
		DzyjHelper dh = new DzyjHelper();
		Map retData = new HashMap();
		Map data = (Map) vop.getData();
		UserData ud = vop.getUserData();
		CallableStatement st = null;
		String sql = "";
		ORManager orManager = null;
		Connection conn = null;
		GdzcjszjyjqkjbVO qysdsjbvo = (GdzcjszjyjqkjbVO)data.get(CAcodeConstants.CA_MAPKEY_VO_XMLVO);
        //DzyjsjVO
        DzyjsjVO dzyj = (DzyjsjVO)data.get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);
        //ZfjgqysdsjbBO
//        GdzcjszjyjqkjbBO qysdsjbbo = (GdzcjszjyjqkjbBO)data.get(QysdsksMapConstant.VO_KEY_KSSBSJ);
        // ��������
        String tableID = (String)data.get(QysdsksMapConstant.STRING_KEY_BBLX);
        // ��������
        String jdlx = (String)data.get(QysdsksMapConstant.STRING_KEY_JDLX);
//        System.out.println("tableID = " + tableID + "\njdlx = " + jdlx);
        // ˰��Ǽǻ�������ֵ����
        SWDJJBSJ djjbsj = (SWDJJBSJ)data.get(QysdsksMapConstant.OBJECT_KEY_DJSJ);
    	
    	
        try {
            // ������ݿ�����
            conn = DBResource.getConnection();
            QysdsReportsDeclare qrd = new QysdsReportsDeclare();
            qrd.setAppid(QysdsReportsConstants.CREPORTS_APPID_QYSDS); //aid
            //qrd.setVersion(QysdsReportsConstants.CREPORTS_VERSION_QYSDS); //�汾
            qrd.setVersion(Constant.QYSDSJB_VERSION_2014);
            qrd.setNsrjsjdm(djjbsj.getJsjdm()); //���������
//            qysdsjbvo.getSbxx().getSkssjsrq();
//            String nd = (String)skssrq.get(Skssrq.SKSSRQ_ND);
            Timestamp curDate = new Timestamp(System.currentTimeMillis());
            Map skssrq = new HashMap();
            skssrq = Skssrq.otherSkssrq(curDate);
            qrd.setSknd((String)skssrq.get(Skssrq.SKSSRQ_ND)); //˰�����
            qrd.setBbqlx(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_QUARTOR); //����
            qrd.setQh(jdlx); //�ں�

            QysdsReportsTableDeclare qrtd = new QysdsReportsTableDeclare();
            qrtd.setTableId(tableID); //����ID
            qrtd.setTableName(Constant.TABLE_NAME_GDZCJSZJYJQK_2014); //��������
            // qrtd.setTbblx(QysdsReportsConstants.CREPORTS_TABLE_REPORTTYPE_NORMAL_ONE_DIMENSIONALITY);

            //������ͣ��ͱ���������һ��
//            if(bblx.equals(Constant.CZNB)) {
//                qrtd.setTbblx(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_YEAR);
//            }
//            else {
            qrtd.setTbblx(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_QUARTOR);
//            }

            Map tmp = new HashMap();
            tmp.put(tableID, qrtd);
            qrd.setTableContentList(tmp);
            System.out.println("version��"+qrd.getVersion()+"nsrjsjdm"+qrd.getNsrjsjdm()+"Sknd"+qrd.getSknd()+"Bbqlx"+qrd.getBbqlx()+"Qh"+qrd.getQh()+"tableID"+qrtd.getTableId());
            // ��ȡ���ݿ�ӿ�
            IAppAccess iApp = AppAccessFactory.getAInstance(conn, AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
            iApp.deleteSingleTable(qrd);
			DBResource.destroyConnection(conn);
			System.out.println(".............zhangj...dodelete..end");
        }
        catch(Exception ex) {
		    DBResource.destroyConnection(conn);
            throw ExceptionUtil.getBaseException(ex);
        }
        
        
        if(ud.getCaflag()) {

            System.out.println("===========ǩ����ʼ==========");
            try {
                String ywid = qysdsjbvo.getNsrxx().getJsjdm() + "+"
                    + DjStringUtil.getCurrentDateStr(DjStringUtil.DATE_FORMAT5);
                System.out.println("======ywid:" + ywid);
                dzyj = dh.saveDzyjsj(dzyj, ywid, CAConstants.DADM_WHFZJG);
                System.out.println("===========ǩ������==========");
            }
            catch(Exception ex) {
                System.out.println("===========ǩ��ʧ��==========");
                throw ExceptionUtil.getBaseException(ex);

            }
            retData.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
        }
        
        return retData;
    }
    
    /**
     * �����ݿ��л�ȡ������ҵ���룬hcΪ��999��
     * @param table
     * @return
     */
    private void getSshymc(GdzcjszjyjqkjbBO gdzcjbBo,QysdsReportsTableDeclare table) {
    	String yz="";
		if (table.getCellContentList().get("999") != null)
        {
			QysdsReportsItemDeclare qrtid = (QysdsReportsItemDeclare) table
					.getCellContentList().get("999");
			yz=qrtid.getItemValue();
			if (yz == null) {
				yz= "";
			} 
		}		
		if(yz!= ""){
			gdzcjbBo.setSshy(new QysdsUtil2014().getSshymc(yz));
			gdzcjbBo.setSshydm(yz);
		}				
	}
}
