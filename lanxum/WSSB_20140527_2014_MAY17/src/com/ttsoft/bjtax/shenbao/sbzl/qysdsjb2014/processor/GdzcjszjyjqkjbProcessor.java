package com.ttsoft.bjtax.shenbao.sbzl.qysdsjb2014.processor;

/**
 * 固定资产加速折旧（扣除）预缴情况统计表processor
 * @description : 
 * @author zhangj
 * @version 1.0
 * @time 2014-12-5 下午05:08:41
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
    //初始化工具类
    private QysdsUtil2014 sdsUtil = new QysdsUtil2014();
    private String[] HCName = {"一、六大行业固定资产", "  （一）生物药品制造业", "  （二）专用设备制造业", "  （三）铁路、船舶、航空航天和其他运输设备制造业", "  （四）计算机、通信和其他电子设备制造业", "  （五）仪器仪表制造业",
    					"  （六）信息传输、软件和信息技术制造业", "  （七）其他行业", "二、允许一次性扣除的固定资产", "  （一）单位价值不超过100万元的研发仪器、设备", "   其中：六大行业小型微利企业研发和生产经营共用的仪器、设备", 
    					"  （二）单位价值不超过5000元的固定资产"};
    
    private String[] LCName = {"hc", "xm", "fwjzw_yz", "fwjzw_bqzje", "fwjzw_ljzje", "jqsbhqtgdzc_yz","jqsbhqtgdzc_bqzje", "jqsbhqtgdzc_ljzje", "hj_yz", "hj_bqzje_zczje", "hj_bqzje_jszje", 
			"hj_ljzje_zczje", "hj_ljzje_jszje"};
    
	String [] HC={"1","2","3","4","5","6","7","8","9","10","11","12"};
	String ZJ_HC="13";
	private String ZJ_HCNAME="总          计                             ";
    
    public GdzcjszjyjqkjbProcessor()
    {
    }

    /**
     * 根据业务操作类型值来做业务操作
     *
     * @param vo
     *            VOPackage
     * @return java.lang.Object
     * @throws com.ttsoft.framework.exception.BaseException
     */
    public Object process(VOPackage vo) throws BaseException
    {
        // 根据业务操作类型值来做业务操作
        try {
            switch(vo.getAction()) {
                // 查询
                case QysdsksActionConstant.INT_ACTION_QUERY: {
                    return doQuery( (Map)vo.getData());
                }
                // 保存
                case QysdsksActionConstant.INT_ACTION_SAVE: {
                    return doSave(vo);
                }
                // 删除
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
     * 查询固定资产加速折旧（扣除）预缴情况统计表信息
     *
     * @param pData
     *            Map
     * @return Map
     * @throws BaseException
     */
    private Object doQuery(Map pData) throws BaseException
    {
        // 数据库连接对象
        Connection conn = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        try {
            // 获得数据库连接
            conn = DBResource.getConnection();
            GdzcjszjyjqkjbBO gdzcbo = new GdzcjszjyjqkjbBO();
            // 计算机代码
            String jsjdm = null;
            // 当前日期
            Timestamp curDate = null;
            // 税务登记基本数据值对象
            SWDJJBSJ djjbsj = (SWDJJBSJ)pData.get(QysdsksMapConstant.OBJECT_KEY_DJSJ);
            gdzcbo.setJsjdm(djjbsj.getJsjdm());
            gdzcbo.setNsrmc(djjbsj.getNsrmc());
            gdzcbo.setNsrsbh(djjbsj.getSwdjzh());

            // 取得计算机代码
            jsjdm = (String)pData.get(QysdsksMapConstant.STRING_KEY_JSJDM);
            // 取得日期参数
            curDate = (Timestamp)pData.get(QysdsksMapConstant.STRING_KEY_DATE);
            gdzcbo.setSbrq(curDate);
            gdzcbo.setSbrqshow(sdf.format(curDate));
            // 取得所在的季度
            String jd = Skssrq.preQuarter(curDate);
            gdzcbo.setJd(jd);
            // 报表类型 - 表ID
            String bblx = (String)pData.get(QysdsksMapConstant.STRING_KEY_BBLX);
            // 季度类型 - 期号
            String jdlx = (String)pData.get(QysdsksMapConstant.STRING_KEY_JDLX);
            gdzcbo.setJd(jdlx); //期号-季报期号即为季度数
            System.out.println("jsjdm = " + jsjdm + "\nbblx = " + bblx + "\nqh = " + jdlx);

            // 取得税款所属日期Map
            Map skssrq = new HashMap();
            if(bblx.equals(Constant.TABLE_ID_GDZCJSZJYJQK_2014)) {
                skssrq = Skssrq.otherSkssrq(curDate);
            }
            else {
                throw new ApplicationException("传递的报表ID错误！");
            }
            // 取得税款所属开始和结束日期
            Timestamp skssksrq = (Timestamp)skssrq.get(Skssrq.SKSSKSRQ);
            Timestamp skssjsrq = (Timestamp)skssrq.get(Skssrq.SKSSJSRQ);
            gdzcbo.setSkssksrq(skssksrq);
            gdzcbo.setSkssjsrq(skssjsrq);

            // 取得年度
            String nd = (String)skssrq.get(Skssrq.SKSSRQ_ND);
            gdzcbo.setNd(nd);
            System.out.println("nd = " + nd);

            //报表期类型
            gdzcbo.setBbqlx(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_QUARTOR);

            // 创建QysdsReportsDeclare对象
            QysdsReportsDeclare qrd = new QysdsReportsDeclare();
            // 设置QysdsReportsTableDeclare的基本信息
            QysdsReportsTableDeclare qrtd = new QysdsReportsTableDeclare();

            qrd.setAppid(QysdsReportsConstants.CREPORTS_APPID_QYSDS); //aid
            //qrd.setVersion(QysdsReportsConstants.CREPORTS_VERSION_QYSDS); //version
            qrd.setVersion(Constant.QYSDSJB_VERSION_2014);
            qrd.setNsrjsjdm(jsjdm); //计算机代码
            qrd.setSknd(nd); //税款年度
            // 如果不是汇总纳税分支机构表，则抛异常
            if(bblx.equals(Constant.TABLE_ID_GDZCJSZJYJQK_2014)) {
                //报表期类型
                qrd.setBbqlx(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_QUARTOR);
                //期号
                qrd.setQh(jdlx);

                //报表ID
                qrtd.setTableId(bblx);
                qrtd.setTbblx(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_QUARTOR);
            }
            else {
                throw new ApplicationException("传递的报表ID错误！");
            }
            // qrtd.setTbblx(QysdsReportsConstants.CREPORTS_TABLE_REPORTTYPE_NORMAL_ONE_DIMENSIONALITY);

            // 将QysdsReportsTableDeclare的基本信息置入QysdsReportsDeclare
            Map tmp = new HashMap();
            tmp.put(bblx, qrtd);
            qrd.setTableContentList(tmp);

            // 获取数据库应用接口
            IAppAccess iApp = AppAccessFactory.getAInstance(conn, AppAccessFactory.ACCESS_MODEL_APP_QYSDS);

            // 调用查询方法进行查询
            qrd = (QysdsReportsDeclare)iApp.querySingleTable(qrd);

            // 获取查询到的具体数据
            qrtd = (QysdsReportsTableDeclare)qrd.getTableContentList().get(bblx);

            // 将数据库中的数据翻译成页面所需数据格式
            if(qrtd == null) {
                System.out.println("======no value======");
            }
            else {
                //获取保存的数据,以id, value 形式保存
            	GdzcjszjyjqkjblistVO list = (GdzcjszjyjqkjblistVO)this.translate2Page(qrtd);

                gdzcbo.setSbsj(list);
                
                //从数据库中获取所属行业代码
                this.getSshymc(gdzcbo,qrtd);
                
            }
            return gdzcbo;
        }
        catch(Exception ex) {
            throw ExceptionUtil.getBaseException(ex, "查询企业所得税汇总纳税分支机构季报数据失败");
        }
        finally {
            // 关闭数据库连接
            DBResource.destroyConnection(conn);
        }
    }



    /**
     * 将数据转换成页面展示的形式
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
	 * 保存固定资产加速折旧（扣除）预缴情况统计表数据
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
        // 报表类型
        String tableID = (String)data.get(QysdsksMapConstant.STRING_KEY_BBLX);
        // 季度类型
        String jdlx = (String)data.get(QysdsksMapConstant.STRING_KEY_JDLX);
        
//        System.out.println("tableID = " + tableID + "\njdlx = " + jdlx);
        // 税务登记基本数据值对象
        SWDJJBSJ djjbsj = (SWDJJBSJ)data.get(QysdsksMapConstant.OBJECT_KEY_DJSJ);
        try {
            // 获得数据库连接
            conn = DBResource.getConnection();
            QysdsReportsDeclare report = this.ConvertBoToReportsDeclare(qysdsjbvo, tableID, jdlx, djjbsj);
//            System.out.println("==============================");
//            System.out.println("bbqlx = " + report.getBbqlx());
            QysdsReportsTableDeclare table = (QysdsReportsTableDeclare)report.getTableContentList().get(Constant.TABLE_ID_GDZCJSZJYJQK_2014);
//            System.out.println("table id = " + table.getTableId() + "\n table name = " + table.getTableName()
//                 + "\ntbblx = " + table.getTbblx());
            // 获取数据库接口，调用save方法进行数据保存
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
            System.out.println("===========签名开始==========");
            try {
                String ywid = qysdsjbvo.getNsrxx().getJsjdm() + "+"
                    + DjStringUtil.getCurrentDateStr(DjStringUtil.DATE_FORMAT5);
                System.out.println("======ywid:" + ywid);
                dzyj = dh.saveDzyjsj(dzyj, ywid, CAConstants.DADM_WHFZJG);
                System.out.println("===========签名结束==========");
            }
            catch(Exception ex) {
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
     * @param tableID
     *            报表ID
     * @param jdlx
     *            季度类型
     * @param djjbsj
     *            登记数据对象
     * @return
     */
    private QysdsReportsDeclare ConvertBoToReportsDeclare(GdzcjszjyjqkjbVO qysdsvo,
        String tableID, String jdlx, SWDJJBSJ djjbsj)
    {
        QysdsReportsDeclare report = new QysdsReportsDeclare();

        // 基本信息
        Jbxx jbxx = new Jbxx();
        
        // 基本信息(JBXX)-计算机代码
        jbxx.setJsjdm(djjbsj.getJsjdm());
        // 基本信息(JBXX)-纳税人名称
        jbxx.setNsrmc(djjbsj.getNsrmc());
        
        // 基本信息(JBXX)-联系电话
        jbxx.setLxdh(djjbsj.getJydzlxdm());
        // 基本信息(JBXX)-所属行业
        jbxx.setSshy(djjbsj.getGjbzhydm());
//        System.out.println("..............zhangj.....sshy: "+jbxx.getSshy()+djjbsj.getGjbzhymc());
        // 基本信息(JBXX)-征收方式
        jbxx.setZsfs("");

        // 向报表中添加纳税人基本信息
        report.setJbxx(jbxx);

        /**
         * 应用编号
         */
        report.setAppid(QysdsReportsConstants.CREPORTS_APPID_QYSDS);
        /**
         * 版本号
         */
        report.setVersion(Constant.QYSDSJB_VERSION_2014);
        /**
         * 纳税人计算机代码
         */
        report.setNsrjsjdm(djjbsj.getJsjdm());
        /**
         * 纳税人名称
         */
        //report.setNsrmc(qysdsbo.getNsrmc());
        report.setNsrmc(djjbsj.getNsrmc());
        /**
         * 报表期类型
         */
        // 如果是年报数据则固定设置期号为1；如果是季报数据则根据情况设置期号为1、2、3、4
        //季报
        report.setBbqlx(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_QUARTOR);
        /**期号*/
        report.setQh(jdlx);

        // 取得税款所属日期Map
        Timestamp curDate = new Timestamp(System.currentTimeMillis());
        Map skssrq = new HashMap();

        skssrq = Skssrq.otherSkssrq(curDate);

        String sbrq = "";
        try {
            /**税款所属期*/
            //skssksrq
            String skssksrq = DateTimeUtil.timestampToString( (Timestamp)skssrq.get(Skssrq.SKSSKSRQ));
            report.setSkssksrq(new Date(TinyTools.stringToDate(skssksrq, "yyyy-MM-dd").getTime()));
            //skssjsrq
            String skssjsrq = DateTimeUtil.timestampToString( (Timestamp)skssrq.get(Skssrq.SKSSJSRQ));
            report.setSkssjsrq(new Date(TinyTools.stringToDate(skssjsrq, "yyyy-MM-dd").getTime()));
            // 申报日期
            sbrq = DateTimeUtil.timestampToString(curDate);
            report.setSbrq(new Date(TinyTools.stringToDate(sbrq, "yyyy-MM-dd").getTime()));
        }
        catch(Exception e) {
            System.out.println("转换申报期时出错！");
        }
        // 税款年度
        report.setSknd( (String)skssrq.get(Skssrq.SKSSRQ_ND));
        /**
         * 税务机关组织机构代码
         */
        report.setSwjgzzjgdm(djjbsj.getSwjgzzjgdm());
        /**
         * 区县代码
         */
        report.setQxdm(djjbsj.getSwjgzzjgdm().substring(0,2));
        /**
         * 录入人
         */
        report.setLrr(djjbsj.getJsjdm());
        /**
         * 录入日期
         */
        report.setLrrq(new Date(TinyTools.stringToDate(sbrq, "yyyy-MM-dd").getTime()));
        /**
         * 创建人
         */
        report.setCjr(djjbsj.getJsjdm());
        /**
         * 创建日期
         */
        report.setCjrq(new Date(TinyTools.stringToDate(sbrq, "yyyy-MM-dd").getTime()));

        // 企业所得税报表内单表申明对象
        QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
        table.setTableId(tableID);
        table.setTableName(Constant.TABLE_NAME_GDZCJSZJYJQK_2014);
        /**
         * 填报表类型，和报表期类型一样
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
     * 删除申报数据
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
        // 报表类型
        String tableID = (String)data.get(QysdsksMapConstant.STRING_KEY_BBLX);
        // 季度类型
        String jdlx = (String)data.get(QysdsksMapConstant.STRING_KEY_JDLX);
//        System.out.println("tableID = " + tableID + "\njdlx = " + jdlx);
        // 税务登记基本数据值对象
        SWDJJBSJ djjbsj = (SWDJJBSJ)data.get(QysdsksMapConstant.OBJECT_KEY_DJSJ);
    	
    	
        try {
            // 获得数据库连接
            conn = DBResource.getConnection();
            QysdsReportsDeclare qrd = new QysdsReportsDeclare();
            qrd.setAppid(QysdsReportsConstants.CREPORTS_APPID_QYSDS); //aid
            //qrd.setVersion(QysdsReportsConstants.CREPORTS_VERSION_QYSDS); //版本
            qrd.setVersion(Constant.QYSDSJB_VERSION_2014);
            qrd.setNsrjsjdm(djjbsj.getJsjdm()); //计算机代码
//            qysdsjbvo.getSbxx().getSkssjsrq();
//            String nd = (String)skssrq.get(Skssrq.SKSSRQ_ND);
            Timestamp curDate = new Timestamp(System.currentTimeMillis());
            Map skssrq = new HashMap();
            skssrq = Skssrq.otherSkssrq(curDate);
            qrd.setSknd((String)skssrq.get(Skssrq.SKSSRQ_ND)); //税款年度
            qrd.setBbqlx(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_QUARTOR); //季报
            qrd.setQh(jdlx); //期号

            QysdsReportsTableDeclare qrtd = new QysdsReportsTableDeclare();
            qrtd.setTableId(tableID); //报表ID
            qrtd.setTableName(Constant.TABLE_NAME_GDZCJSZJYJQK_2014); //报表名称
            // qrtd.setTbblx(QysdsReportsConstants.CREPORTS_TABLE_REPORTTYPE_NORMAL_ONE_DIMENSIONALITY);

            //填报表类型，和报表期类型一样
//            if(bblx.equals(Constant.CZNB)) {
//                qrtd.setTbblx(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_YEAR);
//            }
//            else {
            qrtd.setTbblx(QysdsReportsConstants.CREPORTS_IBBQLX_TYPE_QUARTOR);
//            }

            Map tmp = new HashMap();
            tmp.put(tableID, qrtd);
            qrd.setTableContentList(tmp);
            System.out.println("version："+qrd.getVersion()+"nsrjsjdm"+qrd.getNsrjsjdm()+"Sknd"+qrd.getSknd()+"Bbqlx"+qrd.getBbqlx()+"Qh"+qrd.getQh()+"tableID"+qrtd.getTableId());
            // 获取数据库接口
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

            System.out.println("===========签名开始==========");
            try {
                String ywid = qysdsjbvo.getNsrxx().getJsjdm() + "+"
                    + DjStringUtil.getCurrentDateStr(DjStringUtil.DATE_FORMAT5);
                System.out.println("======ywid:" + ywid);
                dzyj = dh.saveDzyjsj(dzyj, ywid, CAConstants.DADM_WHFZJG);
                System.out.println("===========签名结束==========");
            }
            catch(Exception ex) {
                System.out.println("===========签名失败==========");
                throw ExceptionUtil.getBaseException(ex);

            }
            retData.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
        }
        
        return retData;
    }
    
    /**
     * 从数据库中获取所属行业代码，hc为“999”
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
