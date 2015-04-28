package com.ttsoft.bjtax.smsb.qysdsjmsbagl.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.model.QysdsSet;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.sfgl.proxy.ServiceProxy;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.qysdsjmsbagl.jmsbajl.web.QysdsJmsbajlMainForm;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.qysdsCheck.CheckBean;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;

public class QysdsUtil{
	/**
	 * 生成备案申请编号
	 * @param qxdm:区县代码；band：办案年度
	 * @return basqbh
	 * @throws BaseException 
	 */
	public static HashMap getBasqbh(String qxdm,String band,String yhid,String jmbasxdm,Connection conn) throws BaseException{
		HashMap basq = new HashMap() ;
		CallableStatement cs = null;
		PreparedStatement pst = null;
		try {
			String prcName1 = "BEGIN SFDB.SF_PKG_QYSDSJMBA.main(?,?,?,?,?,?,?); END;";
			cs = conn.prepareCall(prcName1);
			cs.setString(1, qxdm);//登陆用户的区县代码
			cs.setString(2, band);//备案年度
			cs.setString(3, yhid);//用户ID
			cs.setString(4, jmbasxdm);//减免备案事项代码
			cs.registerOutParameter(5, oracle.jdbc.OracleTypes.VARCHAR);
			cs.registerOutParameter(6, oracle.jdbc.OracleTypes.VARCHAR);
			cs.registerOutParameter(7, oracle.jdbc.OracleTypes.VARCHAR);
		    cs.execute();

		    if("0".equals(cs.getObject(5))){
		    	basq.put("basqwsh", cs.getObject(6));
		    	basq.put("basqbh", cs.getObject(7));
		    }else{
		    	throw new ApplicationException("获取备案申请文书号失败，请重新操作！");
		    }

			if (pst != null) {
				pst.close();
			}
		}catch (Exception ex) {
			throw ExceptionUtil.getBaseException(ex);
		}
		return basq;
	}
	
	/**
	 * 
	 * @param qxdm
	 * @param band
	 * @param yhid
	 * @param jmbasxdm
	 * @param conn
	 * @return
	 * @throws BaseException
	 */
	public static void getAlertStrWhenAdd(String jsjdm,String band,UserData ud) throws BaseException{
		
		
		SWDJJBSJ djsj = null;
		try {
			// 获得企业登记基本信息
			djsj = InterfaceDj.getJBSJ_New(jsjdm, ud);
		} catch (Exception ex1) {
			throw ExceptionUtil.getBaseException(ex1);
		}		
		java.util.Date time = SfDateUtil.getDate(band+ "0601");// 匹配税费接口的rq参数，无意义
		// 调用税费接口获得企业的征收方式
		ServiceProxy proxy = new ServiceProxy();	
		QysdsSet sdsInfo = proxy.getQysdsInfo(jsjdm, time,SfDateUtil.getDate(band+"0101"), SfDateUtil.getDate(band+"1231"),	CodeConstant.SFGL_QYSDS_BBFS_NB);
		String nsrSwjgzzjgdm=djsj.getSwjgzzjgdm();
		String ssdwdm=ud.getSsdwdm();		
		String yhjb=ud.getYhjb();
		
		if (sdsInfo.getZsfs() != null) {			
			if(!sdsInfo.getZsfs().getZsfsdm().equals(CodeConstant.ZSFSDM_CZZS)){				
				throw new ApplicationException("该纳税人不属于查账征收方式！不能进行减免税备案！");
			}
		}
		if(yhjb.equals("40")){
			if(!ssdwdm.substring(0,2).equals(nsrSwjgzzjgdm.substring(0,2))){
				throw new ApplicationException("该纳税人并非所属管户，不能对该纳税人进行减免税备案！");
			}
		}
		if(yhjb.equals("30")){
			if(!ssdwdm.equals(nsrSwjgzzjgdm)){
				throw new ApplicationException("该纳税人并非所属管户，不能对该纳税人进行减免税备案！");
			}
		}
		
		
	}
	
	
	
	/**
	 * 获取数据ID
	 * @param 
	 * @return basqbh
	 * @throws BaseException 
	 */
	public static String getSequence(Connection conn) throws BaseException{
		String sequence = "";
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT SFDB.SEQ_SF_QYSDSJMSBAJL.NEXTVAL FROM DUAL ";
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next()){
				sequence = rs.getString(1);
			}
			
			if (rs != null) {
				rs.close();
			}
			if (pst != null) {
				pst.close();
			}
		}catch (Exception ex) {
			throw ExceptionUtil.getBaseException(ex);
		}
		return sequence;
	}
	
	/**
	 * 更新主表申请状态
	 * @param basqwsh：备案文书号；sqzt：申请状态; czr：操作人
	 * @return basqbh
	 * @throws BaseException 
	 */
	public  static boolean updateSqzt(String basqwsh, String sqzt,String czr,Connection conn) throws BaseException{
		boolean czzt = false;
		PreparedStatement pst = null;
		try {
			//更新主表审核状态
			String updateZbSql = "UPDATE sfdb.sf_jl_qysdsjmsbajl t " +
					"SET t.sqzt = '"+sqzt+"',t.SHR = '"+czr+"',t.SHSJ = SYSDATE,t.lrr = '"+czr+"',t.lrrq = SYSDATE " +
					"WHERE t.basqwsh = '"+basqwsh+"'";
			
			pst = conn.prepareStatement(updateZbSql);
			pst.executeUpdate();
			czzt = true;
			if (pst != null) {
				pst.close();
			}
		}catch (Exception ex) {
			throw ExceptionUtil.getBaseException(ex);
		}
		return czzt;
	}
	
	/**
	 * 检查主表申请状态
	 * @param basqwsh：备案文书号
	 * @return basqbh
	 * @throws BaseException 
	 */
	public  static boolean checkSqzt(String basqwsh,Connection conn) throws BaseException{
		boolean czzt = false;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sql=" select sqzt from sfdb.sf_jl_qysdsjmsbajl where basqwsh='"+basqwsh+"'" ;
			
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			StringBuffer sb=new StringBuffer();
			while (rs.next()) {
				sb.append(rs.getString(1));
			}
			if ("3".equals(sb.toString()) || "2".equals(sb.toString())) {
				czzt = true;
			}

			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
		}catch (Exception ex) {
			throw ExceptionUtil.getBaseException(ex);
		}
		return czzt;
	}
	
	 /**
     * 字符空值验证
     * @param value String 待验证数值
     * @return boolean
     */
    public static boolean strNotNull(String value) {
        if (value == null || value.trim().length() == 0) {
            return false;
        }
        return true;
    }
	
    
    public static String getTableNameByJmbasxdm(String jmbasxdm){
    	String tableName;
    	Map map=new HashMap();
    	map.put("0010", "sfdb.sf_jl_qysdsjmsba_01");
    	map.put("0020", "sfdb.sf_jl_qysdsjmsba_02");
    	map.put("0030", "sfdb.sf_jl_qysdsjmsba_03");
    	map.put("0040", "sfdb.sf_jl_qysdsjmsba_04");
    	map.put("0050", "sfdb.sf_jl_qysdsjmsba_05");
    	map.put("0060", "sfdb.sf_jl_qysdsjmsba_06");
    	map.put("0070", "sfdb.sf_jl_qysdsjmsba_07");
    	map.put("0080", "sfdb.sf_jl_qysdsjmsba_08");
    	map.put("0090", "sfdb.sf_jl_qysdsjmsba_09");
    	map.put("0100", "sfdb.sf_jl_qysdsjmsba_10");
    	map.put("0110", "sfdb.sf_jl_qysdsjmsba_11");
    	map.put("0120", "sfdb.sf_jl_qysdsjmsba_12");
    	map.put("013A", "sfdb.sf_jl_qysdsjmsba_13A");
    	map.put("013B", "sfdb.sf_jl_qysdsjmsba_13B");
    	map.put("014A", "sfdb.sf_jl_qysdsjmsba_14A");
    	map.put("014B", "sfdb.sf_jl_qysdsjmsba_14B");
    	map.put("0150", "sfdb.sf_jl_qysdsjmsba_15");
    	map.put("0160", "sfdb.sf_jl_qysdsjmsba_16");
    	map.put("0170", "sfdb.sf_jl_qysdsjmsba_17");
    	map.put("0180", "sfdb.sf_jl_qysdsjmsba_18");
    	map.put("0190", "sfdb.sf_jl_qysdsjmsba_19");
    	map.put("0200", "sfdb.sf_jl_qysdsjmsba_20");
    	tableName=(String)map.get(jmbasxdm);
    	return tableName;
    	
    }
    /**
     * 获取资料清单代码为01的资料名称
     * @author gaoyh 
     * @date 2011-09-06 
     * @modify-type add
     * @description 根据企业所得税处要求，根据纳税人所在税务机关，
     *              资料清单中输出：北京市XXX区县地方税务局减税免税申报资料
     * @param qxmd
     * @return zlqddm01mc
     */
    public static String getZlqddm01mc(String qxdm){
    	
    	System.out.println("=====进入getZlqddm01mc方法========");
    	
    	String zlqddm01mc;
    	
    	if("01".equals(qxdm)||qxdm.equals("01")){							
    		zlqddm01mc = "北京市东城区地方税务局减税免税备案表";
		}
		else if("02".equals(qxdm)||qxdm.equals("02")){							
			zlqddm01mc = "北京市西城区地方税务局减税免税备案表";
		}
		else if("03".equals(qxdm)||qxdm.equals("03")){							
			zlqddm01mc = "北京市崇文区地方税务局减税免税备案表";
		}
		else if("04".equals(qxdm)||qxdm.equals("04")){							
			zlqddm01mc = "北京市宣武区地方税务局减税免税备案表";
		}
		else if("05".equals(qxdm)||qxdm.equals("05")){							
			zlqddm01mc = "北京市朝阳区地方税务局减税免税备案表";
		}
		else if("06".equals(qxdm)||qxdm.equals("06")){							
			zlqddm01mc = "北京市海淀区地方税务局减税免税备案表";
		}
		else if("07".equals(qxdm)||qxdm.equals("07")){							
			zlqddm01mc = "北京市丰台区地方税务局减税免税备案表";
		}
		else if("08".equals(qxdm)||qxdm.equals("08")){							
			zlqddm01mc = "北京市石景山区地方税务局减税免税备案表";
		}
		else if("09".equals(qxdm)||qxdm.equals("09")){							
			zlqddm01mc = "北京市门头沟区地方税务局减税免税备案表";
		}
		else if("10".equals(qxdm)||qxdm.equals("10")){							
			zlqddm01mc = "北京市燕山区地方税务局减税免税备案表";
		}
		else if("11".equals(qxdm)||qxdm.equals("11")){							
			zlqddm01mc = "北京市昌平区地方税务局减税免税备案表";
		}
		else if("12".equals(qxdm)||qxdm.equals("12")){							
			zlqddm01mc = "北京市通州区地方税务局减税免税备案表";
		}
		else if("13".equals(qxdm)||qxdm.equals("13")){							
			zlqddm01mc = "北京市顺义区地方税务局减税免税备案表";
		}
		else if("14".equals(qxdm)||qxdm.equals("14")){							
			zlqddm01mc = "北京市大兴区地方税务局减税免税备案表";
		}
		else if("15".equals(qxdm)||qxdm.equals("15")){							
			zlqddm01mc = "北京市房山区地方税务局减税免税备案表";
		}
		else if("16".equals(qxdm)||qxdm.equals("16")){							
			zlqddm01mc = "北京市怀柔区地方税务局减税免税备案表";
		}
		else if("17".equals(qxdm)||qxdm.equals("17")){							
			zlqddm01mc = "北京市密云县地方税务局减税免税备案表";
		}
		else if("18".equals(qxdm)||qxdm.equals("18")){							
			zlqddm01mc = "北京市平谷区地方税务局减税免税备案表";
		}
		else if("19".equals(qxdm)||qxdm.equals("19")){							
			zlqddm01mc = "北京市延庆县地方税务局减税免税备案表";
		}
		else if("20".equals(qxdm)||qxdm.equals("20")){							
			zlqddm01mc = "北京市开发区分局地方税务局减税免税备案表";
		}
		else if("21".equals(qxdm)||qxdm.equals("21")){							
			zlqddm01mc = "北京市西站分局地方税务局减税免税备案表";
		}
		else if("22".equals(qxdm)||qxdm.equals("22")){							
			zlqddm01mc = "北京市涉外分局地方税务局减税免税备案表";
		}
		else if("25".equals(qxdm)||qxdm.equals("25")){							
			zlqddm01mc = "北京市第二稽查局减税免税备案表";
		}
		else if("41".equals(qxdm)||qxdm.equals("41")){							
			zlqddm01mc = "北京市第一稽查局减税免税备案表";
		}
		else if("90".equals(qxdm)||qxdm.equals("90")){							
			zlqddm01mc = "北京市地方税务局减税免税备案表";
		}
		else {							
			zlqddm01mc = "北京市其它地方税务局减税免税备案表";
		}
 	
    	return zlqddm01mc;
    }
    
    /**
	 * @Description: TODO 设置校验参数
	 * @param CzzssdsjbForm
	 * @return
	 */
	public static CheckBean setCheckInf(QysdsJmsbajlMainForm qysdsJmsbajlMainForm)
	{
		CheckBean checkBean = new CheckBean();
		
		//校验清算期的材料
		checkBean.setJsjdm(qysdsJmsbajlMainForm.getJsjdm());
		
		//校验征管范围类型
		checkBean.setSkssrqq(qysdsJmsbajlMainForm.getQsrq().replaceAll("-", ""));
		checkBean.setSkssrqz(qysdsJmsbajlMainForm.getJzrq().replaceAll("-", ""));
		return checkBean;
	}
	
	/**
	 * @author zhangj
	 * @param qxdm
	 * @param band
	 * @param yhid
	 * @param jmbasxdm
	 * @param conn
	 * @return
	 * @throws BaseException
	 */
	public static void getAlertStrWhenAdd(String jsjdm,UserData ud) throws BaseException{
		
		
		SWDJJBSJ djsj = null;
		try {
			// 获得企业登记基本信息
			djsj = InterfaceDj.getJBSJ_New(jsjdm, ud);
		} catch (Exception ex1) {
			throw ExceptionUtil.getBaseException(ex1);
		}		

		String nsrSwjgzzjgdm=djsj.getSwjgzzjgdm();
		String ssdwdm=ud.getSsdwdm();		
		String yhjb=ud.getYhjb();

		if(yhjb.equals("40")){
			if(!ssdwdm.substring(0,2).equals(nsrSwjgzzjgdm.substring(0,2))){
				throw new ApplicationException("该纳税人并非所属管户，不能对该纳税人进行清算备案！");
			}
		}
		if(yhjb.equals("30")){
			if(!ssdwdm.equals(nsrSwjgzzjgdm)){
				throw new ApplicationException("该纳税人并非所属管户，不能对该纳税人进行清算备案！");
			}
		}
		
		
	}
}