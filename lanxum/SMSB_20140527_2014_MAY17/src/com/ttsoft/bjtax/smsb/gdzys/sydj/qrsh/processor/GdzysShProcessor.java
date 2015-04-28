/*
 * <p>Title: 耕地资源占用税-税源登记查询</p>
 * <p>Company: 立思辰电子</p>
 */
package com.ttsoft.bjtax.smsb.gdzys.sydj.qrsh.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.smsb.gdzys.constant.GdzysCodeConstant;
import com.ttsoft.bjtax.smsb.gdzys.sydj.qrsh.model.GdzyShjm;
import com.ttsoft.bjtax.smsb.gdzys.sydj.qrsh.model.GdzyShsbmodel;
import com.ttsoft.bjtax.smsb.gdzys.sydj.qrsh.model.GdzyShsbmx;
import com.ttsoft.bjtax.smsb.gdzys.sydj.qrsh.web.GdzysShqrForm;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.common.exceptions.TtsoftException;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.ZKAdapter;
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
public class GdzysShProcessor implements Processor 
{
	
	/**
	 * 分类处理函数
	 */
	 public Object process(VOPackage vo) throws BaseException {
	        switch (vo.getAction()) {
	        
	        //查询税源登记信息列表
	        case GdzysCodeConstant.SMSB_GDZYS_DJQuery:
	            return this.query(vo,false);
	            
	         //查详细信息   
//	        case GdzysCodeConstant.SMSB_GDZYS_DJQueryDetail:
//	            return this.query(vo,true);
	          //审核确认更新数据库信息 
//	        case GdzysCodeConstant.SMSB_GDZYS_UPDATESH:
//	        	return this.updatesh(vo);
	        default:
	            throw new ApplicationException(
	                "ActionType有错误，processor中找不到相应的方法.");
	        }
	    }
/*----------------------------------不同action处理--2-----------------------------------------------------------------*/	

	 
	  /**
	   * <1>查询
	   */
	public Object query(VOPackage vo ,boolean detail) throws BaseException
	{
		
		//非空验证
		if(vo==null)
		{
			throw new NullPointerException("数据处理未接收到内容");
		}
		
		//设置初始化的要会显得表单 ，这里回显给查询页面，所以是原表单；
		GdzysShqrForm gdzysShqrForm = (GdzysShqrForm)vo.getData();
		
		//获取耕地占用申报表SQL
		String sql_GDSB = getsql(gdzysShqrForm ,"sbdb.sb_jl_gdzys_sbb sbb " 							
								 +"left join  dmdb.gd_dm_sylx sylx_dmb    on   sbb.sylxdm = sylx_dmb.sylxdm " , vo ); 															
		
		//获取耕地占用明细表SQL
		String sql_GDSBMX = "select * " 
						   +"from 		sbdb.SB_JL_GDZYS_SBBSBMX sbmxb " 
						   +"left join  dmdb.gd_dm_zdyt          zdyt_dmb  on  sbmxb.ZDYTDM = zdyt_dmb.ZDYTDM "
						   +"where SBBXLH=?";														
		
		//获取减免税表SQL
		String sql_GDJM   = "select * "
			 			   +"from       sbdb.SB_JL_GDZYS_SBBJMMX jmb "
			 			   +"left join  dmdb.gd_dm_jmslb         jmlb      on  jmb.JMSLBDM  = jmlb.JMSLBDM "
			 			   +"where SBBXLH=?";  				
		
		//数据库连接
		Connection con = SfDBResource.getConnection();
		
		//查询数据库并保存
		try 
		{
				setGDSBResultSet(con ,gdzysShqrForm ,sql_GDSB ,sql_GDSBMX ,sql_GDJM,vo ,detail);
		}
		catch (Exception e) 
		{
				throw ExceptionUtil.getBaseException(e);
		}
		finally
		{
				SfDBResource.freeConnection(con);	
		}
		
		//结束返回	
		return gdzysShqrForm;
	}
	
//	/**
//	 * <2>根据审核条件更新数据库
//	 * @param vo
//	 * @return  回到审核查询页面
//	 * @throws BaseException
//	 */
//	public Object updatesh(VOPackage vo ) throws BaseException
//	{
//		//非空验证
//		if(vo==null)
//		{
//			throw new NullPointerException("数据处理未接收到内容");
//		}
//		
//		GdzysShqrForm gdzysShqrForm = (GdzysShqrForm)vo.getData();
//	
//		String sbxlh = gdzysShqrForm.getSbbxlh();
//		String sql = null;
//		
//		//数据库连接
//		Connection con = SfDBResource.getConnection();
//			
//		//区县审核	
//		if(gdzysShqrForm.getShzl().equals("qxsh"))
//		{
//			sql = "update sbdb.sb_jl_gdzys_sbb set sbzt = '30',qrzt = '1' where sbbxlh = ?";
//			try {
//				
//				PreparedStatement psqx = null;
//				psqx = con.prepareStatement(sql);
//				psqx.setString(1,sbxlh);
//				psqx.executeUpdate();
//				
//			} catch (SQLException e) {	
//				throw ExceptionUtil.getBaseException(e);
//			}
//		}
//		
//		//市局审核
//		if(gdzysShqrForm.getShzl().equals("sjsh"))
//		{
//			
//			String sjshsj = gdzysShqrForm.getSjshsj();
////			SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd");
////			try {
////				java.util.Date date =time.parse(sjqrsj);
////			} catch (ParseException e) {
////				throw ExceptionUtil.getBaseException(e);
////			}
//			
//			sql = "update sbdb.sb_jl_gdzys_sbb set sbzt = '40',sjqrzt = '1',sjqrrq = to_date(?,'yyyy-mm-dd') where sbbxlh = ?";
//			try {
//				PreparedStatement pssj = null;
//				pssj = con.prepareStatement(sql);
//				pssj.setString(1,sjshsj);
//				pssj.setString(2,sbxlh);
//				pssj.executeUpdate();
//			} catch (SQLException e) {	
//				throw ExceptionUtil.getBaseException(e);
//			}
//		}
//	
//		//更新			
//		return gdzysShqrForm;
//	}
	
   
//	/**
//	 * <3>详细查询
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
	 * @throws TtsoftException 
	 * @throws Exception 
	 */
	private String getsql(GdzysShqrForm gdzysShqrForm ,String table_name ,VOPackage vo) throws BaseException
	{
		//准备耕地占用税申报表要拼接的语句
		StringBuffer sql_condition = new StringBuffer();
		StringBuffer sql = new StringBuffer();
		sql.append("select * from ").append(table_name).append(" where ");
		
		//用户权限过滤
		try{
		String datafilter = ZKAdapter.getInstance().getDataFilterString(vo.getUserData(),"SBDB", "SB_JL_GDZYS_SBB" );
		if(sql_condition.length()>0)
		{sql_condition.append(" AND ");}
			sql_condition.append(datafilter);
		}catch (Exception e) {
			throw ExceptionUtil.getBaseException(e);
		}
		
		//提取查询条件
		String nsrmc = "";				//纳税人名称
		String jsjdm = "";				//计算机代码
		String pzzdwh ="";				//占地批文号
		String sbbxlh = "";				//申报序列号
		
		if(gdzysShqrForm.getNsrmc()!=null && !(gdzysShqrForm.getNsrmc().equals("")))
		{
			if(sql_condition.length()>0)
			{sql_condition.append(" AND ");}
			
			nsrmc = gdzysShqrForm.getNsrmc();
			sql_condition.append("NSRMC=");
			sql_condition.append("'").append(nsrmc).append("'");
		}
		if(gdzysShqrForm.getJsjdm()!=null && !(gdzysShqrForm.getJsjdm().equals("")))
		{

			if(sql_condition.length()>0)
			{sql_condition.append(" AND ");}
			
			jsjdm = gdzysShqrForm.getJsjdm();
			sql_condition.append("JSJDM=");
			sql_condition.append("'").append(jsjdm).append("'");
		}
		if(gdzysShqrForm.getZdpwh()!=null && !(gdzysShqrForm.getZdpwh().equals("")))
		{

			if(sql_condition.length()>0)
			{sql_condition.append(" AND ");}
			
			pzzdwh = gdzysShqrForm.getZdpwh();
			sql_condition.append("PZJDWH=");
			sql_condition.append("'").append(pzzdwh).append("'");
		}
		if(gdzysShqrForm.getSbbxlh()!=null && !(gdzysShqrForm.getSbbxlh().equals("")))
		{
			if(sql_condition.length()>0)
			{sql_condition.append(" AND ");}
			
			sbbxlh = gdzysShqrForm.getSbbxlh();
			sql_condition.append("SBBXLH=");
			sql_condition.append("'").append(sbbxlh).append("'");
		}
		
		//4个条件不能都为空JS判断,再判断一次？
		if(nsrmc == null && jsjdm == null && pzzdwh ==null && sbbxlh ==null )
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
	private void setGDSBResultSet(Connection con ,GdzysShqrForm gdzysShqrForm ,String sql_GDSB ,String sql_GDSBMX ,String sql_GDJM ,VOPackage vo ,boolean detail) 
		throws Exception
	{
		PreparedStatement ps = null;
		ps = con.prepareStatement(sql_GDSB, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		System.out.println(sql_GDSB);
		
		ResultSet rs = null ;
		rs = ps.executeQuery();
			
		//获取结果集条数
		rs.last();
		int rowCount = rs.getRow();
		//gdzysShqrForm.setNum(rowCount);            这里查到的不定是未审核的
		rs.beforeFirst();
		int shnum = 0;
		
		//信息列
		List infList = new ArrayList();														//信息条数
		while(rs.next())
		{
			//只显示需要确认的信息<1>市局<2>区县
			boolean city = ((rs.getString("SFSJSP").equals("1")) && !(rs.getString("sbzt").equals("40")));				
			boolean county = ((rs.getString("SFSJSP").equals("0")) && !(rs.getString("sbzt").equals("30")));
				
			if(city||county)
			{
					//基本信息
					SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");					//时间类型
					GdzyShsbmodel gdzyShsbmodel = new GdzyShsbmodel();
					gdzyShsbmodel.setNsrmc(rs.getString("NSRMC"));								//纳税人名称
					gdzyShsbmodel.setJsjdm(rs.getString("JSJDM"));								//计算机代码
					gdzyShsbmodel.setSbbxlh(rs.getString("SBBXLH"));								//申报序列号
					gdzyShsbmodel.setNsrlx(rs.getString("NSRLX"));								//纳税人类型
					gdzyShsbmodel.setSfsjsp(rs.getString("SFSJSP"));							//是否市局审批
					gdzyShsbmodel.setSylx(rs.getString("SYLXMC"));								//税源类型	
					gdzyShsbmodel.setNsrxxdz(rs.getString("NSRXXDZ"));							//纳税人详细地址
					gdzyShsbmodel.setKhyh(rs.getString("KHYHMC"));								//开户银行
					gdzyShsbmodel.setYhzh(rs.getString("YHZH"));								//银行账号
					gdzyShsbmodel.setLxrxm(rs.getString("LXRXM"));								//联系人姓名
					gdzyShsbmodel.setLxdh(rs.getString("LXDH"));								//联系电话
					gdzyShsbmodel.setSfzzlx(rs.getString("SFZZLX"));							//身份证照类型
					gdzyShsbmodel.setSfzzhm(rs.getString("SFZZHM"));							//身份证照号码
					gdzyShsbmodel.setJmly(rs.getString("SBJMLY"));								//减免理由
					gdzyShsbmodel.setBz(rs.getString("BZMS"));									//备注描述
					gdzyShsbmodel.setSbzt(rs.getString("SBZT"));								//申报状态
					
					//土地信息
					gdzyShsbmodel.setJsmj(rs.getBigDecimal("JSMJ")==null?"0.00":String.valueOf(rs.getBigDecimal("JSMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));		//计税面积
					gdzyShsbmodel.setJzse(rs.getBigDecimal("JZSE")==null?"0.00":String.valueOf(rs.getBigDecimal("JZSE").setScale(2,BigDecimal.ROUND_HALF_UP)));    	//计征税额
					gdzyShsbmodel.setJmmj(rs.getBigDecimal("JMMJ")==null?"0.00":String.valueOf(rs.getBigDecimal("JMMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));    	//减免面积
					gdzyShsbmodel.setJmse(rs.getBigDecimal("JMSE")==null?"0.00":String.valueOf(rs.getBigDecimal("JMSE").setScale(2,BigDecimal.ROUND_HALF_UP)));    	//减免税额
					gdzyShsbmodel.setYsmj(rs.getBigDecimal("YSMJ")==null?"0.00":String.valueOf(rs.getBigDecimal("YSMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));   	//应税面积
					gdzyShsbmodel.setYnse(rs.getBigDecimal("YNSE")==null?"0.00":String.valueOf(rs.getBigDecimal("YNSE").setScale(2,BigDecimal.ROUND_HALF_UP)));		//应纳税额
					gdzyShsbmodel.setZdpwh (rs.getString("PZJDWH"));							    	//占地批文号
					gdzyShsbmodel.setTdzldz(rs.getString("TDZLDZ"));							    	//土地坐落地址
					gdzyShsbmodel.setPzzdmj(rs.getBigDecimal("PZJDMJ")==null?"":String.valueOf(rs.getBigDecimal("PZJDMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));    //批准占地面积
					gdzyShsbmodel.setJsxmmc(rs.getString("JSXMMC"));							    	//建设项目名称
					gdzyShsbmodel.setSjzdmj(rs.getBigDecimal("SJZDMJ")==null?"":String.valueOf(rs.getBigDecimal("SJZDMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));	//实际占地面积
					if(rs.getDate("ZDSJ")!=null)
					{	
						gdzyShsbmodel.setZdsj(time.format(rs.getDate("ZDSJ")));					    //占地时间
					}
					
					//创建信息
					gdzyShsbmodel.setCjr(rs.getString("CJR"));														//创建人
					if(rs.getDate("CJRQ")!=null)
					{	
						gdzyShsbmodel.setCjsj(time.format(new Date(rs.getTimestamp("CJRQ").getTime())));										//创建时间
					}else
					{
						gdzyShsbmodel.setCjsj("");
					}
					
					if(rs.getString("QRZT")==null)
					{gdzyShsbmodel.setQrzt("0");}
					else
					{gdzyShsbmodel.setQrzt(rs.getString("QRZT"));}													//确认状态
					
					gdzyShsbmodel.setQrr(rs.getString("QRR"));														//确认人
					if(rs.getDate("QRRQ")!=null)
					{
						gdzyShsbmodel.setQrsj(time.format(new Date(rs.getTimestamp("QRRQ").getTime())));										//确认时间
					}else
					{
						gdzyShsbmodel.setQrsj("");
					}
					if(rs.getString("SJQRZT")==null)
					{gdzyShsbmodel.setSjqrzt("0");}
					else
					{gdzyShsbmodel.setSjqrzt(rs.getString("SJQRZT"));}												//市局确认状态
					
					gdzyShsbmodel.setSjqrr(rs.getString("SJQRR"));													//市局确认人
					if(rs.getDate("SJQRRQ")!=null)
					{
						gdzyShsbmodel.setSjqrsj(time.format(new Date(rs.getTimestamp("SJQRRQ").getTime())));									//市局确认时间
					}else
					{
						gdzyShsbmodel.setSjqrsj("");
					}
					
					//通过接口信息添加
					//setInterfaceInf(gdzyShsbmodel, vo);
					
//					//占地信息
//					if(rowCount==1 && detail)
//					{
//						
//						setGDSBMXResultSet(con,gdzyShsbmodel,sql_GDSBMX);		
//					}
//					
//					//减免信息				
//					if(rowCount==1 && detail)
//					{
//						setGDJMResultSet(con ,gdzyShsbmodel ,sql_GDJM);
//					}
					
					//一条申报信息完成
					infList.add(gdzyShsbmodel);
					shnum++;
			}
		}
		gdzysShqrForm.setInfList(infList);
		gdzysShqrForm.setNum(shnum);
		
		//关闭
		if(rs!=null)
		{rs.close();}
		if(ps!=null)
		{ps.close();}
	}
/*-----------------------------------------------------------------下面代码不会执行------------------------------------------------------------------------------------------------*/	
	/**
	 * <3>保存从申报详细表查到的结果 ,--<<功能2
	 * @param con--数据库连接
	 * @param Gdzydjcxmodel--信息存储
	 * @param sql_GDSBMX--对应表SQL
	 * @throws SQLException 
	 */
	private void setGDSBMXResultSet(Connection con ,GdzyShsbmodel gdzyShsbmodel ,String sql_GDSBMX) throws SQLException
	{
		
		PreparedStatement ps = con.prepareStatement(sql_GDSBMX);
		ps.setString(1, gdzyShsbmodel.getSbbxlh());
		ResultSet rs = ps.executeQuery();
		List gdzyShmxList = new ArrayList();
		while(rs.next())
		{
			GdzyShsbmx gdzyShsbmx = new GdzyShsbmx ();
			gdzyShsbmx.setZdyt(rs.getString("ZDYTMC"));						//占地用途
			gdzyShsbmx.setSyse(rs.getBigDecimal("SYSE")==null?"":String.valueOf(rs.getBigDecimal("SYSE").setScale(2,BigDecimal.ROUND_HALF_UP)));			//适用税额
			gdzyShsbmx.setJsmj(rs.getBigDecimal("JSMJ")==null?"0.00":String.valueOf(rs.getBigDecimal("JSMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));		//计税面积
			gdzyShsbmx.setJzse(rs.getBigDecimal("JZSE")==null?"0.00":String.valueOf(rs.getBigDecimal("JZSE").setScale(2,BigDecimal.ROUND_HALF_UP)));    	//计征税额
			gdzyShsbmx.setJmmj(rs.getBigDecimal("JMMJ")==null?"0.00":String.valueOf(rs.getBigDecimal("JMMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));    	//减免面积
			gdzyShsbmx.setJmse(rs.getBigDecimal("JMSE")==null?"0.00":String.valueOf(rs.getBigDecimal("JMSE").setScale(2,BigDecimal.ROUND_HALF_UP)));    	//减免税额
			gdzyShsbmx.setYsmj(rs.getBigDecimal("YSMJ")==null?"0.00":String.valueOf(rs.getBigDecimal("YSMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));   		//应税面积
			gdzyShsbmx.setYnse(rs.getBigDecimal("YNSE")==null?"0.00":String.valueOf(rs.getBigDecimal("YNSE").setScale(2,BigDecimal.ROUND_HALF_UP)));		//应纳税额
			
			gdzyShmxList.add(gdzyShsbmx);
		}
		gdzyShsbmodel.setSbmx(gdzyShmxList);
		
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
	private void setGDJMResultSet(Connection con ,GdzyShsbmodel gdzyShsbmodel ,String sql_GDJM) throws SQLException
	{
		
		PreparedStatement ps = con.prepareStatement(sql_GDJM);
		ps.setString(1, gdzyShsbmodel.getSbbxlh());
		ResultSet rs = ps.executeQuery();
		List gdzyShjmList = new ArrayList();
		while(rs.next())
		{
			GdzyShjm gdzyShjm = new GdzyShjm();
			gdzyShjm.setJmslb(rs.getString("JMSLBMC"));							//减免税类别代码
			gdzyShjm.setJmmj(rs.getBigDecimal("JMMJ")==null?"0.00":String.valueOf(rs.getBigDecimal("JMMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));    	//减免面积
			gdzyShjm.setJmse(rs.getBigDecimal("JMSE")==null?"0.00":String.valueOf(rs.getBigDecimal("JMSE").setScale(2,BigDecimal.ROUND_HALF_UP)));    	//减免税额
			
			gdzyShjmList.add(gdzyShjm);
		}
		gdzyShsbmodel.setJmxx(gdzyShjmList);
		
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
	private void setInterfaceInf(GdzyShsbmodel gdzyShsbmodel ,VOPackage vo) throws ApplicationException
	{
		SWDJJBSJ dj;
		try {
			if(gdzyShsbmodel.getNsrlx().equals("0"))
			{
				UserData ud = vo.getUserData();
				dj = (SWDJJBSJ) InterfaceDj.getJBSJ(gdzyShsbmodel.getJsjdm(), ud);
			
				gdzyShsbmodel.setNsrsshy(dj.getGjbzhymc());													//纳税人所属行业
				gdzyShsbmodel.setQydjzclx(dj.getDjzclxmc());												//企业登记注册类型
//				djxx.setSwdjzh(dj.getSwdjzh());//税务登记证号
//				djxx.setZzjgdm(dj.getSwjgzzjgdm());//税务机关组织机构代码
				gdzyShsbmodel.setNsrsbh(dj.getSwdjzh()+"-"+dj.getSwjgzzjgdm());								//纳税人识别号			
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}

	}
	
}
