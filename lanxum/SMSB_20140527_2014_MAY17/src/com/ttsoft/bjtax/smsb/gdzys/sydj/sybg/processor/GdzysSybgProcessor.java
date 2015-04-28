/*
 * <p>Title: 耕地资源占用税-税源登记查询</p>
 * <p>Company: 立思辰电子</p>
 */
package com.ttsoft.bjtax.smsb.gdzys.sydj.sybg.processor;

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
import com.ttsoft.bjtax.smsb.gdzys.sydj.sybg.model.GdzysSybg_jm;
import com.ttsoft.bjtax.smsb.gdzys.sydj.sybg.model.GdzysSybg_sbmx;
import com.ttsoft.bjtax.smsb.gdzys.sydj.sybg.model.GdzysSybgmodel;
import com.ttsoft.bjtax.smsb.gdzys.sydj.sybg.web.GdzysSybgForm;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
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
 * @author 开发部 -
 * @version 1.0
 */
public class GdzysSybgProcessor  implements Processor{

	/**
	 * 分类处理函数
	 */
	 public Object process(VOPackage vp) throws BaseException 
	 {
	        switch (vp.getAction()) 
	        {
	        
	        //查询税源登记信息
	        case GdzysCodeConstant.SMSB_GDZYS_SYBGQUERY:
	            return this.query(vp);
	            
	         //查详细信息   
	        case GdzysCodeConstant.SMSB_GDZYS_SYBGQUERYDETAIL:
	            return this.query(vp);
	        
	        default:
	            throw new ApplicationException(
	                "ActionType有错误，processor中找不到相应的方法.");
	        }
	    }
/*----------------------------------不同action处理--1个-----------------------------------------------------------------*/	
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
		GdzysSybgForm gdzysSybgForm = (GdzysSybgForm)vo.getData();
		
		//获取耕地占用申报表SQL
		String sql_GDSB = getsql(gdzysSybgForm ,"sbdb.sb_jl_gdzys_sbb sbb " 							
								 +"left join  dmdb.gd_dm_sylx      sylx_dmb    on   sbb.sylxdm = sylx_dmb.sylxdm "
								 , vo); 
		System.out.println("============"+sql_GDSB);
		
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
				setGDSBResultSet(con ,gdzysSybgForm ,sql_GDSB ,sql_GDSBMX ,sql_GDJM,vo);
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
		return gdzysSybgForm;
	}
	
/*----------------------------------处理--功能函数--5---------------------------------------------------------------*/
	/**
	 * <1>获取4个查询条件SQL操作的语句
	 * @param sydjcxForm 根据4个条件查询
	 * @param 表名
	 * @return SQL
	 * @throws Exception 
	 */
	private String getsql(GdzysSybgForm gdzysSybgForm ,String table_name ,VOPackage vo) throws BaseException
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
		
		if(gdzysSybgForm.getNsrmc()!=null && !(gdzysSybgForm.getNsrmc().equals("")))
		{
			if(sql_condition.length()>0)
			{sql_condition.append(" AND ");}
			
			nsrmc = gdzysSybgForm.getNsrmc();
			sql_condition.append("NSRMC=");
			sql_condition.append("'").append(nsrmc).append("'");
		}
		if(gdzysSybgForm.getJsjdm()!=null && !(gdzysSybgForm.getJsjdm().equals("")))
		{

			if(sql_condition.length()>0)
			{sql_condition.append(" AND ");}
			
			jsjdm = gdzysSybgForm.getJsjdm();
			sql_condition.append("JSJDM=");
			sql_condition.append("'").append(jsjdm).append("'");
		}
		if(gdzysSybgForm.getZdpwh()!=null && !(gdzysSybgForm.getZdpwh().equals("")))
		{

			if(sql_condition.length()>0)
			{sql_condition.append(" AND ");}
			
			pzzdwh = gdzysSybgForm.getZdpwh();
			sql_condition.append("PZJDWH=");
			sql_condition.append("'").append(pzzdwh).append("'");
		}
		if(gdzysSybgForm.getSbbxlh()!=null && !(gdzysSybgForm.getSbbxlh().equals("")))
		{

			if(sql_condition.length()>0)
			{sql_condition.append(" AND ");}
			
			sbbxlh = gdzysSybgForm.getSbbxlh();
			sql_condition.append("SBBXLH=");
			sql_condition.append("'").append(sbbxlh).append("'");
		}
		
		
		//4个条件不能都为空JS判断,再判断一次？
		if(nsrmc == null && jsjdm == null && pzzdwh ==null && sbbxlh ==null)
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
	private void setGDSBResultSet(Connection con ,GdzysSybgForm gdzysSybgForm ,String sql_GDSB ,String sql_GDSBMX ,String sql_GDJM ,VOPackage vo) 
		throws Exception
	{
		PreparedStatement ps = null;
		ps = con.prepareStatement(sql_GDSB, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		
		ResultSet rs = null ;
		rs = ps.executeQuery();
			
		//获取结果集条数
		rs.last();
		int rowCount = rs.getRow();
		gdzysSybgForm.setNum(rowCount);
		rs.beforeFirst();
		
		//信息列
		List infList = new ArrayList();														//信息条数
		while(rs.next())
		{
						//基本信息
						SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						GdzysSybgmodel gdzysSybgmodel = new GdzysSybgmodel();
						gdzysSybgmodel.setNsrmc(rs.getString("NSRMC"));								//纳税人名称
						gdzysSybgmodel.setJsjdm(rs.getString("JSJDM"));								//计算机代码
						gdzysSybgmodel.setSbbxlh(rs.getString("SBBXLH"));							//申报序列号
						gdzysSybgmodel.setNsrlx(rs.getString("NSRLX"));								//纳税人类型
						gdzysSybgmodel.setSfsjsp(rs.getString("SFSJSP"));							//是否市局审批
						gdzysSybgmodel.setSylx(rs.getString("SYLXMC"));								//税源类型	
						gdzysSybgmodel.setNsrxxdz(rs.getString("NSRXXDZ"));							//纳税人详细地址
						gdzysSybgmodel.setKhyh(rs.getString("KHYHMC"));								//开户银行
						gdzysSybgmodel.setYhzh(rs.getString("YHZH"));								//银行账号
						gdzysSybgmodel.setLxrxm(rs.getString("LXRXM"));								//联系人姓名
						gdzysSybgmodel.setLxdh(rs.getString("LXDH"));								//联系电话
						gdzysSybgmodel.setSfzzlx(rs.getString("SFZZLX"));							//身份证照类型
						gdzysSybgmodel.setSfzzhm(rs.getString("SFZZHM"));							//身份证照号码
						gdzysSybgmodel.setJmly(rs.getString("SBJMLY"));								//减免理由
						gdzysSybgmodel.setBz(rs.getString("BZMS"));									//备注描述
						gdzysSybgmodel.setSbzt(rs.getString("SBZT"));								//申报状态
						
						//土地信息
						gdzysSybgmodel.setJsmj(rs.getBigDecimal("JSMJ")==null?"0.00":String.valueOf(rs.getBigDecimal("JSMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));		//计税面积
						gdzysSybgmodel.setJzse(rs.getBigDecimal("JZSE")==null?"0.00":String.valueOf(rs.getBigDecimal("JZSE").setScale(2,BigDecimal.ROUND_HALF_UP)));    	//计征税额
						gdzysSybgmodel.setJmmj(rs.getBigDecimal("JMMJ")==null?"0.00":String.valueOf(rs.getBigDecimal("JMMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));    	//减免面积
						gdzysSybgmodel.setJmse(rs.getBigDecimal("JMSE")==null?"0.00":String.valueOf(rs.getBigDecimal("JMSE").setScale(2,BigDecimal.ROUND_HALF_UP)));    	//减免税额
						gdzysSybgmodel.setYsmj(rs.getBigDecimal("YSMJ")==null?"0.00":String.valueOf(rs.getBigDecimal("YSMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));   	//应税面积
						gdzysSybgmodel.setYnse(rs.getBigDecimal("YNSE")==null?"0.00":String.valueOf(rs.getBigDecimal("YNSE").setScale(2,BigDecimal.ROUND_HALF_UP)));		//应纳税额
						gdzysSybgmodel.setZdpwh (rs.getString("PZJDWH"));							    	//占地批文号
						gdzysSybgmodel.setTdzldz(rs.getString("TDZLDZ"));							    	//土地坐落地址
						gdzysSybgmodel.setPzzdmj(rs.getBigDecimal("PZJDMJ")==null?"":String.valueOf(rs.getBigDecimal("PZJDMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));    //批准占地面积
						gdzysSybgmodel.setJsxmmc(rs.getString("JSXMMC"));							    	//建设项目名称
						gdzysSybgmodel.setSjzdmj(rs.getBigDecimal("SJZDMJ")==null?"":String.valueOf(rs.getBigDecimal("SJZDMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));	//实际占地面积
						if(rs.getDate("ZDSJ")!=null)
						{
							gdzysSybgmodel.setZdsj(time.format(rs.getDate("ZDSJ")));						//占地时间
						}
						
						//创建信息
						
						gdzysSybgmodel.setCjr(rs.getString("CJR"));														//创建人
						if(rs.getDate("CJRQ")!=null)
						{	
							gdzysSybgmodel.setCjsj(time.format(new Date(rs.getTimestamp("CJRQ").getTime())));										//创建时间
						}else
						{
							gdzysSybgmodel.setCjsj("");
						}
						if(rs.getString("QRZT")==null)
						{gdzysSybgmodel.setQrzt("0");}
						else
						{gdzysSybgmodel.setQrzt(rs.getString("QRZT"));}													//确认状态
						gdzysSybgmodel.setQrr(rs.getString("QRR"));														//确认人
						if(rs.getDate("QRRQ")!=null)
						{
							gdzysSybgmodel.setQrsj(time.format(new Date(rs.getTimestamp("QRRQ").getTime())));										//确认时间
						}else
						{
							gdzysSybgmodel.setQrsj("");
						}
						
						if(rs.getString("SJQRZT")==null)
						{gdzysSybgmodel.setSjqrzt("0");}
						else
						{gdzysSybgmodel.setSjqrzt(rs.getString("SJQRZT"));}												//市局确认状态
						gdzysSybgmodel.setSjqrr(rs.getString("SJQRR"));													//市局确认人
						if(rs.getDate("SJQRRQ")!=null)
						{
							gdzysSybgmodel.setSjqrsj(time.format(new Date(rs.getTimestamp("SJQRRQ").getTime())));								//市局确认时间
						}else
						{
							gdzysSybgmodel.setSjqrsj("");
						}
			
						
						//变更信息
							String sqlQueryJmxx = "select * from sbdb.sb_jl_gdzys_jmszm where sbbxlh ='"+gdzysSybgmodel.getSbbxlh()+"' AND zfbz <> '1'";
							String sqlQueryJkxx = "select * from sbdb.sb_jl_gdzys_sbbjksglb t1 left join sbdb.sb_jl_sbjkzb t2 on t1.jkpzh = t2.jkpzh where t1.sbbxlh = '"+gdzysSybgmodel.getSbbxlh()+"'";
							
							//减免证表
							PreparedStatement jmps;
							jmps = con.prepareStatement(sqlQueryJmxx);
							ResultSet rsjm = null ;
							rsjm = jmps.executeQuery();
							if(rsjm.next())
							{	
								gdzysSybgmodel.setYcjm("1");     		//已出减免----撤销减免
							}
							
							//缴款证表
							PreparedStatement jkps;
							jkps = con.prepareStatement(sqlQueryJkxx);
							ResultSet rsjk = null ;
							rsjk = jkps.executeQuery();
							while(rsjk.next())
							{
								//存在缴款书信息
								gdzysSybgmodel.setYcjk("1");
								
								//已入库--提示走退税
								if(rsjk.getString("zwbs").charAt(1)=='1')
								{
									gdzysSybgmodel.setRkbz("1");
								}
							}
			

						//通过接口信息添加
						//setInterfaceInf(gdzysSybgmodel, vo);	
						
//						//占地信息
//						if(rowCount==1)
//						{
//							setGDSBMXResultSet(con,gdzysSybgmodel,sql_GDSBMX);		
//						}
//						
//						//减免信息
//						if(rowCount==1)
//						{
//							setGDJMResultSet(con ,gdzysSybgmodel ,sql_GDJM);
//						}
//						
						//一条申报信息完成
						infList.add(gdzysSybgmodel);
			
		}
		gdzysSybgForm.setInfList(infList);
		
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
	private void setGDSBMXResultSet(Connection con ,GdzysSybgmodel gdzysSybgmodel ,String sql_GDSBMX) throws SQLException
	{
		
		PreparedStatement ps = con.prepareStatement(sql_GDSBMX);
		ps.setString(1, gdzysSybgmodel.getSbbxlh());
		ResultSet rs = ps.executeQuery();
		List gdzybg_sbmxList = new ArrayList();
		while(rs.next())
		{
			GdzysSybg_sbmx gdzysSybg_sbmx = new GdzysSybg_sbmx ();
			gdzysSybg_sbmx.setZdyt(rs.getString("ZDYTMC"));						//占地用途
			gdzysSybg_sbmx.setSyse(rs.getBigDecimal("SYSE")==null?"":String.valueOf(rs.getBigDecimal("SYSE").setScale(2,BigDecimal.ROUND_HALF_UP)));			//适用税额
			gdzysSybg_sbmx.setJsmj(rs.getBigDecimal("JSMJ")==null?"0.00":String.valueOf(rs.getBigDecimal("JSMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));		//计税面积
			gdzysSybg_sbmx.setJzse(rs.getBigDecimal("JZSE")==null?"0.00":String.valueOf(rs.getBigDecimal("JZSE").setScale(2,BigDecimal.ROUND_HALF_UP)));    	//计征税额
			gdzysSybg_sbmx.setJmmj(rs.getBigDecimal("JMMJ")==null?"0.00":String.valueOf(rs.getBigDecimal("JMMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));    	//减免面积
			gdzysSybg_sbmx.setJmse(rs.getBigDecimal("JMSE")==null?"0.00":String.valueOf(rs.getBigDecimal("JMSE").setScale(2,BigDecimal.ROUND_HALF_UP)));    	//减免税额
			gdzysSybg_sbmx.setYsmj(rs.getBigDecimal("YSMJ")==null?"0.00":String.valueOf(rs.getBigDecimal("YSMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));   		//应税面积
			gdzysSybg_sbmx.setYnse(rs.getBigDecimal("YNSE")==null?"0.00":String.valueOf(rs.getBigDecimal("YNSE").setScale(2,BigDecimal.ROUND_HALF_UP)));		//应纳税额
			
			gdzybg_sbmxList.add(gdzysSybg_sbmx);
		}
		gdzysSybgmodel.setSbmx(gdzybg_sbmxList);
		
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
	private void setGDJMResultSet(Connection con ,GdzysSybgmodel gdzysSybgmodel ,String sql_GDJM) throws SQLException
	{
		
		PreparedStatement ps = con.prepareStatement(sql_GDJM);
		ps.setString(1, gdzysSybgmodel.getSbbxlh());
		ResultSet rs = ps.executeQuery();
		List gdzybg_jmList = new ArrayList();
		while(rs.next())
		{
			GdzysSybg_jm gdzysSybg_jm = new GdzysSybg_jm();
			gdzysSybg_jm.setJmslb(rs.getString("JMSLBMC"));								//减免税类别代码
			gdzysSybg_jm.setJmmj(rs.getBigDecimal("JMMJ")==null?"0.00":String.valueOf(rs.getBigDecimal("JMMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));    	//减免面积
			gdzysSybg_jm.setJmse(rs.getBigDecimal("JMSE")==null?"0.00":String.valueOf(rs.getBigDecimal("JMSE").setScale(2,BigDecimal.ROUND_HALF_UP)));    	//减免税额
			
			gdzybg_jmList.add(gdzysSybg_jm);
		}
		if(gdzybg_jmList.size()!= 0)
		{
			gdzysSybgmodel.setJmxx(gdzybg_jmList);
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
	private void setInterfaceInf(GdzysSybgmodel gdzysSybgmodel ,VOPackage vo) throws ApplicationException
	{
		SWDJJBSJ dj = null;
		try {
			
			UserData ud = vo.getUserData();
			if(gdzysSybgmodel.getNsrlx().equals("0"))
			{
				dj = (SWDJJBSJ) InterfaceDj.getJBSJ(gdzysSybgmodel.getJsjdm(), ud);
				gdzysSybgmodel.setNsrsshy(dj.getGjbzhymc());													//纳税人所属行业
				gdzysSybgmodel.setQydjzclx(dj.getDjzclxmc());													//企业登记注册类型
//				djxx.setSwdjzh(dj.getSwdjzh());//税务登记证号
//				djxx.setZzjgdm(dj.getSwjgzzjgdm());//税务机关组织机构代码
				gdzysSybgmodel.setNsrsbh(dj.getSwdjzh()+"-"+dj.getSwjgzzjgdm());								//纳税人识别号	
			}
					
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}

	}
	
}
