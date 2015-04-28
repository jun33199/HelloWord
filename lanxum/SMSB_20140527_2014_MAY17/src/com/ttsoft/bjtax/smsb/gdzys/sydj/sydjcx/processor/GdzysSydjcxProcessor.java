/*
 * <p>Title: 耕地资源占用税-税源登记查询</p>
 * <p>Company: 立思辰电子</p>
 */
package com.ttsoft.bjtax.smsb.gdzys.sydj.sydjcx.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.smsb.gdzys.constant.GdzysCodeConstant;
import com.ttsoft.bjtax.smsb.gdzys.sydj.sydjcx.model.Gdzydj_jm;
import com.ttsoft.bjtax.smsb.gdzys.sydj.sydjcx.model.Gdzydj_sbmx;
import com.ttsoft.bjtax.smsb.gdzys.sydj.sydjcx.model.Gdzydjcxmodel;
import com.ttsoft.bjtax.smsb.gdzys.sydj.sydjcx.web.GdzysSydjcxForm;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: 耕地资源占用税-税源登记查询</p>
 * <p>Description: 查询税源登记。</p>
 * @author 开发部 - 李剑楠
 * @version 1.0
 */
public class GdzysSydjcxProcessor implements Processor 
{
	
	/**
	 * 分类处理函数
	 */
	 public Object process(VOPackage vp) throws BaseException {
	        switch (vp.getAction()) {
	        
	        //查询税源登记信息
	        case GdzysCodeConstant.SMSB_GDZYS_DJQuery:
	            return this.query(vp);
	            
	         //查详细信息   
	        case GdzysCodeConstant.SMSB_GDZYS_DJQueryDetail:
	            return this.query(vp);
	        
	        default:
	            throw new ApplicationException(
	                "ActionType有错误，processor中找不到相应的方法.");
	        }
	    }
/*----------------------------------不同action处理--2-----------------------------------------------------------------*/	

	 /**
	  * <1>查询登记信息
	  */
	public Object query(VOPackage vo) throws BaseException
	{
		
		//非空验证
		if(vo==null)
		{
			throw new NullPointerException("数据处理未接收到内容");
		}
		
		//设置初始化的要会显得表单 ，这里回显给查询页面，所以是原表单；
		GdzysSydjcxForm sydjcxForm = (GdzysSydjcxForm)vo.getData();
		
		//获取耕地占用申报表SQL
		String sql_GDSB = getsql(sydjcxForm ," sbdb.sb_jl_gdzys_sbb sbb " 							
								 			+",dmdb.gd_dm_sylx sylx_dmb "
								); 															
		
		//获取耕地占用明细表SQL
		String sql_GDSBMX = "select * " 
						   +"from 		sbdb.SB_JL_GDZYS_SBBSBMX sbmxb     " 
						   +"    	   ,dmdb.gd_dm_zdyt          zdyt_dmb  "
						   +"where " 
						   +	" sbmxb.ZDYTDM = zdyt_dmb.ZDYTDM "
						   +	" AND SBBXLH=? "
						   +"order by xh";														
		
		//获取减免税表SQL
		String sql_GDJM   = "select * "
			 			   +"from       sbdb.SB_JL_GDZYS_SBBJMMX jmb       "
			 			   +"		   ,dmdb.gd_dm_jmslb         jmlb      "
			 			   +"where " 
			 			   +	" jmb.JMSLBDM  = jmlb.JMSLBDM "
			 			   +	" AND SBBXLH=? ";  				
		
		//数据库连接
		Connection con = SfDBResource.getConnection();
		
		//查询数据库并保存
		try 
		{
				setGDSBResultSet(con ,sydjcxForm ,sql_GDSB ,sql_GDSBMX ,sql_GDJM,vo);
		}
		catch (Exception e) 
		{
				e.printStackTrace();
				throw ExceptionUtil.getBaseException(e);
		}
		finally
		{
				SfDBResource.freeConnection(con);	
		}
		
		//结束返回	
		return sydjcxForm;
	}
   
//	/**
//	 * <2>详细查询
//	 * @param vo
//	 * @return
//	 * @throws BaseException
//	 */
//	public Object queryDetail(VOPackage vo) throws BaseException 
//	{
//		//设置初始化的要会显得表单 ，这里回显给查询页面，所以是原表单；
//		GdzysSydjcxForm sydjcxForm = (GdzysSydjcxForm)vo.getData();
//		
//		//获取SQL
//		String sql_GDSBMX = "select * from SB_JL_GDZYS_SBBSBMX where SBBXLH==?";//获取耕地占用明细表SQL
//		String sql_GDJM = "select * from SB_JL_GDZYS_SBBJMMX where SBBXLH==?";  //获取减免税表SQL
//		
//		//数据库连接
//		Connection con = SfDBResource.getConnection();
//		
//		//获取model
//		List lt = sydjcxForm.getInfList();
//		Gdzydjcxmodel gdzydjcxmodelTemp = null;
//		for (int i = 0; i < lt.size(); i++) 
//		{
//			if(((Gdzydjcxmodel)lt.get(i)).getSbxlh() == sydjcxForm.getSbxlh())
//			{
//				gdzydjcxmodelTemp = (Gdzydjcxmodel)lt.get(i);
//				break;
//			}
//		}
//		if(gdzydjcxmodelTemp==null)
//		{
//			throw ExceptionUtil.getBaseException(new Exception("数据库存储数据有误")); 
//		}
//		
//		//查询数据库并保存
//		try 
//		{
//				setGDSBMXResultSet(con, gdzydjcxmodelTemp, sql_GDSBMX);
//				setGDJMResultSet(con, gdzydjcxmodelTemp, sql_GDJM);
//		}
//		catch (Exception e) 
//		{
//				e.printStackTrace();
//				throw ExceptionUtil.getBaseException(e);
//		}
//		finally
//		{
//				SfDBResource.freeConnection(con);	
//		}
//		
//		//结束返回	
//		return sydjcxForm;
//	}
	
	/*----------------------------------数据库处理--功能函数--5---------------------------------------------------------------*/
	/**
	 * <1>获取4个查询条件SQL操作的语句
	 * @param sydjcxForm 根据4个条件查询
	 * @param 表名
	 * @return SQL
	 * @throws Exception 
	 */
	private String getsql(GdzysSydjcxForm sydjcxForm ,String table_name) throws BaseException
	{
		//准备耕地占用税申报表要拼接的语句
		StringBuffer sql_condition = new StringBuffer();
		StringBuffer sql = new StringBuffer();
		sql.append("select * from ").append(table_name).append(" where ");
		sql_condition.append("sbb.sylxdm = sylx_dmb.sylxdm ");
		
		//提取查询条件
		String nsrmc = "";				//纳税人名称
		String jsjdm = "";				//计算机代码
		String pzzdwh ="";				//占地批文号
		String sbxlh = "";				//申报序列号
		String zsjgdm = "";				//征收机关代码
		
		if(sydjcxForm.getNsrmc()!=null && !(sydjcxForm.getNsrmc().equals("")))
		{
			if(sql_condition.length()>0)
			{sql_condition.append(" AND ");}
			
			nsrmc = sydjcxForm.getNsrmc();
			sql_condition.append("NSRMC=");
			sql_condition.append("'").append(nsrmc).append("'");
		}
		if(sydjcxForm.getJsjdm()!=null && !(sydjcxForm.getJsjdm().equals("")))
		{

			if(sql_condition.length()>0)
			{sql_condition.append(" AND ");}
			
			jsjdm = sydjcxForm.getJsjdm();
			sql_condition.append("JSJDM=");
			sql_condition.append("'").append(jsjdm).append("'");
		}
		if(sydjcxForm.getZdpwh()!=null && !(sydjcxForm.getZdpwh().equals("")))
		{

			if(sql_condition.length()>0)
			{sql_condition.append(" AND ");}
			
			pzzdwh = sydjcxForm.getZdpwh();
			sql_condition.append("PZJDWH=");
			sql_condition.append("'").append(pzzdwh).append("'");
		}
		if(sydjcxForm.getSbxlh()!=null && !(sydjcxForm.getSbxlh().equals("")))
		{

			if(sql_condition.length()>0)
			{sql_condition.append(" AND ");}
			
			sbxlh = sydjcxForm.getSbxlh();
			sql_condition.append("SBBXLH=");
			sql_condition.append("'").append(sbxlh).append("'");
		}
		
		//权限--添加查询条件-税务所只能查本所
		if(sydjcxForm.getSwjgdm()!=null && !(sydjcxForm.getSwjgdm().equals("")))
		{

			if(sql_condition.length()>0)
			{sql_condition.append(" AND ");}
			
			zsjgdm = sydjcxForm.getSwjgdm();
			sql_condition.append("ZSJGDM=");
			sql_condition.append("'").append(zsjgdm).append("'");
		}
		
		//4个条件不能都为空JS判断,再判断一次？
		if(nsrmc == null && jsjdm == null && pzzdwh ==null && sbxlh ==null)
		{
			throw ExceptionUtil.getBaseException(new Exception("400--至少输入一个查询条件"));
		}
		
		sql.append(sql_condition).append(" order by sbb.cjrq desc");
		return sql.toString();
	}
	
	/**
	 * <2>保存查到的结果
	 * @param sydjcxForm--保存到的form
	 * @param 数据库连接
	 * @param 查询不同表的SQL语句 sql_GDSB，sql_GDSBMX，sql_GDJM；
	 * @return
	 * @throws SQLException 
	 */
	private void setGDSBResultSet(Connection con ,GdzysSydjcxForm sydjcxForm ,String sql_GDSB ,String sql_GDSBMX ,String sql_GDJM ,VOPackage vo) 
		throws Exception
	{
		PreparedStatement ps = null;
		ps = con.prepareStatement(sql_GDSB, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = null ;
		rs = ps.executeQuery();
			
		//获取结果集条数
		rs.last();
		int rowCount = rs.getRow();
		sydjcxForm.setNum(rowCount);
		rs.beforeFirst();
		
		//信息列
		List infList = new ArrayList();														//信息条数
		while(rs.next())
		{

			
						//基本信息
						SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						SimpleDateFormat time2 = new SimpleDateFormat("yyyy-MM-dd");
						Gdzydjcxmodel gdzydjcxmodel = new Gdzydjcxmodel();
						gdzydjcxmodel.setNsrmc(rs.getString("NSRMC"));								//纳税人名称
						gdzydjcxmodel.setJsjdm(rs.getString("JSJDM"));								//计算机代码
						gdzydjcxmodel.setSbxlh(rs.getString("SBBXLH"));								//申报序列号
						gdzydjcxmodel.setNsrlx(rs.getString("NSRLX"));								//纳税人类型
						gdzydjcxmodel.setSfsjsp(rs.getString("SFSJSP"));							//是否市局审批
						gdzydjcxmodel.setSylx(rs.getString("SYLXMC"));								//税源类型	
						gdzydjcxmodel.setNsrxxdz(rs.getString("NSRXXDZ"));							//纳税人详细地址
						gdzydjcxmodel.setKhyh(rs.getString("KHYHMC"));								//开户银行
						gdzydjcxmodel.setYhzh(rs.getString("YHZH"));								//银行账号
						gdzydjcxmodel.setLxrxm(rs.getString("LXRXM"));								//联系人姓名
						gdzydjcxmodel.setLxdh(rs.getString("LXDH"));								//联系电话
						gdzydjcxmodel.setSfzzlx(rs.getString("SFZZLX"));							//身份证照类型
						gdzydjcxmodel.setSfzzhm(rs.getString("SFZZHM"));							//身份证照号码
						gdzydjcxmodel.setJmly(rs.getString("SBJMLY"));								//减免理由
						gdzydjcxmodel.setBz(rs.getString("BZMS"));									//备注描述
						gdzydjcxmodel.setSbzt(rs.getString("SBZT"));								//申报状态
						
						//土地信息
						gdzydjcxmodel.setJsmj(rs.getBigDecimal("JSMJ")==null?"0.00":String.valueOf(rs.getBigDecimal("JSMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));		//计税面积
						gdzydjcxmodel.setJzse(rs.getBigDecimal("JZSE")==null?"0.00":String.valueOf(rs.getBigDecimal("JZSE").setScale(2,BigDecimal.ROUND_HALF_UP)));    	//计征税额
						gdzydjcxmodel.setJmmj(rs.getBigDecimal("JMMJ")==null?"0.00":String.valueOf(rs.getBigDecimal("JMMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));    	//减免面积
						gdzydjcxmodel.setJmse(rs.getBigDecimal("JMSE")==null?"0.00":String.valueOf(rs.getBigDecimal("JMSE").setScale(2,BigDecimal.ROUND_HALF_UP)));    	//减免税额
						gdzydjcxmodel.setYsmj(rs.getBigDecimal("YSMJ")==null?"0.00":String.valueOf(rs.getBigDecimal("YSMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));   	//应税面积
						gdzydjcxmodel.setYnse(rs.getBigDecimal("YNSE")==null?"0.00":String.valueOf(rs.getBigDecimal("YNSE").setScale(2,BigDecimal.ROUND_HALF_UP)));		//应纳税额
						gdzydjcxmodel.setZdpwh (rs.getString("PZJDWH"));							    	//占地批文号
						gdzydjcxmodel.setTdzldz(rs.getString("TDZLDZ"));							    	//土地坐落地址
						gdzydjcxmodel.setPzzdmj(rs.getBigDecimal("PZJDMJ")==null?"":String.valueOf(rs.getBigDecimal("PZJDMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));    //批准占地面积
						gdzydjcxmodel.setJsxmmc(rs.getString("JSXMMC"));							    	//建设项目名称
						gdzydjcxmodel.setSjzdmj(rs.getBigDecimal("SJZDMJ")==null?"":String.valueOf(rs.getBigDecimal("SJZDMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));	//实际占地面积
						if(rs.getDate("ZDSJ")!=null)
						{					
							gdzydjcxmodel.setZdsj(time2.format(rs.getDate("ZDSJ")));					//占地时间
						}
						
						
						//创建信息	
						gdzydjcxmodel.setCjr(rs.getString("CJR"));														//创建人
						if(rs.getDate("CJRQ")!=null)
						{	
							gdzydjcxmodel.setCjsj(time.format(new Date(rs.getTimestamp("CJRQ").getTime())));										//创建时间
						}else
						{
							gdzydjcxmodel.setCjsj("");
						}
						if(rs.getString("QRZT")==null)
						{gdzydjcxmodel.setQrzt("0");}
						else
						{gdzydjcxmodel.setQrzt(rs.getString("QRZT"));}													//确认状态
						gdzydjcxmodel.setQrr(rs.getString("QRR"));														//确认人
						if(rs.getDate("QRRQ")!=null)
						{
							gdzydjcxmodel.setQrsj(time.format(new Date(rs.getTimestamp("QRRQ").getTime())));										//确认时间
						}else
						{
							gdzydjcxmodel.setQrsj("");
						}
						
						if(rs.getString("SJQRZT")==null)
						{gdzydjcxmodel.setSjqrzt("0");}
						else
						{gdzydjcxmodel.setSjqrzt(rs.getString("SJQRZT"));}												//市局确认状态
						gdzydjcxmodel.setSjqrr(rs.getString("SJQRR"));													//市局确认人
						if(rs.getDate("SJQRRQ")!=null)
						{
							gdzydjcxmodel.setSjqrsj(time.format(new Date(rs.getTimestamp("SJQRRQ").getTime())));									//市局确认时间
						}else
						{
							gdzydjcxmodel.setSjqrsj("");
						}
			
						
						//通过接口信息添加
						setInterfaceInf(gdzydjcxmodel, vo);
						
						//占地信息
						if(rowCount==1)
						{
							
								setGDSBMXResultSet(con,gdzydjcxmodel,sql_GDSBMX);		
						}
						
						//减免信息
						if(rowCount==1)
						{
							setGDJMResultSet(con ,gdzydjcxmodel ,sql_GDJM);
						}
						
						//一条申报信息完成
						infList.add(gdzydjcxmodel);
			
		}
		sydjcxForm.setInfList(infList);
		
		//关闭
		if(rs!=null)
		{rs.close();}
		if(ps!=null)
		{ps.close();}
	}
	
	/**
	 * <3>保存从申报详细表查到的结果 ,--<<功能2
	 * @param con--数据库连接
	 * @param Gdzydjcxmodel--信息存储
	 * @param sql_GDSBMX--对应表SQL
	 * @throws SQLException 
	 */
	private void setGDSBMXResultSet(Connection con ,Gdzydjcxmodel gdzydjcxmodel ,String sql_GDSBMX) throws SQLException
	{
		
		PreparedStatement ps = con.prepareStatement(sql_GDSBMX);
		ps.setString(1, gdzydjcxmodel.getSbxlh());
		ResultSet rs = ps.executeQuery();
		List gdzydj_sbmxList = new ArrayList();
		while(rs.next())
		{
			Gdzydj_sbmx gdzydj_sbmx = new Gdzydj_sbmx ();
			gdzydj_sbmx.setZdyt(rs.getString("ZDYTMC"));									//占地用途
			gdzydj_sbmx.setSyse(rs.getBigDecimal("SYSE")==null?"":String.valueOf(rs.getBigDecimal("SYSE").setScale(2,BigDecimal.ROUND_HALF_UP)));			//适用税额
			gdzydj_sbmx.setJsmj(rs.getBigDecimal("JSMJ")==null?"0.00":String.valueOf(rs.getBigDecimal("JSMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));		//计税面积
			gdzydj_sbmx.setJzse(rs.getBigDecimal("JZSE")==null?"0.00":String.valueOf(rs.getBigDecimal("JZSE").setScale(2,BigDecimal.ROUND_HALF_UP)));    	//计征税额
			gdzydj_sbmx.setJmmj(rs.getBigDecimal("JMMJ")==null?"0.00":String.valueOf(rs.getBigDecimal("JMMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));    	//减免面积
			gdzydj_sbmx.setJmse(rs.getBigDecimal("JMSE")==null?"0.00":String.valueOf(rs.getBigDecimal("JMSE").setScale(2,BigDecimal.ROUND_HALF_UP)));    	//减免税额
			gdzydj_sbmx.setYsmj(rs.getBigDecimal("YSMJ")==null?"0.00":String.valueOf(rs.getBigDecimal("YSMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));   		//应税面积
			gdzydj_sbmx.setYnse(rs.getBigDecimal("YNSE")==null?"0.00":String.valueOf(rs.getBigDecimal("YNSE").setScale(2,BigDecimal.ROUND_HALF_UP)));		//应纳税额
			
			gdzydj_sbmxList.add(gdzydj_sbmx);
		}
		gdzydjcxmodel.setSbmx(gdzydj_sbmxList);
		
		//关闭
		if(rs!=null)
		{rs.close();}
		if(ps!=null)
		{ps.close();}
	}

	/**
	 * <4>保存从申报减免详细表查到的结果--<<功能2
	 * @param con
	 * @param gdzydjcxmodel
	 * @param String
	 * @throws SQLException 
	 */
	private void setGDJMResultSet(Connection con ,Gdzydjcxmodel gdzydjcxmodel ,String sql_GDJM) throws SQLException
	{
		
		PreparedStatement ps = con.prepareStatement(sql_GDJM);
		ps.setString(1, gdzydjcxmodel.getSbxlh());
		ResultSet rs = ps.executeQuery();
		List gdzydj_sbjmList = new ArrayList();
		while(rs.next())
		{
			Gdzydj_jm gdzydj_jm = new Gdzydj_jm();
			gdzydj_jm.setJmslb(rs.getString("JMSLBMC"));									//减免税类别代码
			gdzydj_jm.setJmmj(rs.getBigDecimal("JMMJ")==null?"0.00":String.valueOf(rs.getBigDecimal("JMMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));    	//减免面积
			gdzydj_jm.setJmse(rs.getBigDecimal("JMSE")==null?"0.00":String.valueOf(rs.getBigDecimal("JMSE").setScale(2,BigDecimal.ROUND_HALF_UP)));    	//减免税额
			
			gdzydj_sbjmList.add(gdzydj_jm);
		}
		if(gdzydj_sbjmList.size()!= 0)
		{
			gdzydjcxmodel.setJmxx(gdzydj_sbjmList);
		};
		
		//关闭
		if(rs!=null)
		{rs.close();}
		if(ps!=null)
		{ps.close();}
	}
	
	/**
	 * <5>添加从接口得到的信息
	 * @param gdzydjcxmodel
	 * @return
	 * @throws ApplicationException 
	 */
	private void setInterfaceInf(Gdzydjcxmodel gdzydjcxmodel ,VOPackage vo) throws ApplicationException
	{
		SWDJJBSJ dj = null;
		try {
			
			UserData ud = vo.getUserData();
			if(gdzydjcxmodel.getNsrlx().equals("0"))
			{
				dj = (SWDJJBSJ) InterfaceDj.getJBSJ(gdzydjcxmodel.getJsjdm(), ud);
				gdzydjcxmodel.setNsrsshy(dj.getGjbzhymc());													//纳税人所属行业
				gdzydjcxmodel.setQydjzclx(dj.getDjzclxmc());												//企业登记注册类型
//				djxx.setSwdjzh(dj.getSwdjzh());//税务登记证号
//				djxx.setZzjgdm(dj.getSwjgzzjgdm());//税务机关组织机构代码
				gdzydjcxmodel.setNsrsbh(dj.getSwdjzh()+"-"+dj.getSwjgzzjgdm());								//纳税人识别号	
			}
					
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}

	}
	
}
