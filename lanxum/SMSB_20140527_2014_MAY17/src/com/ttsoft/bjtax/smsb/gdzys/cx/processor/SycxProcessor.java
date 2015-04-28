
package com.ttsoft.bjtax.smsb.gdzys.cx.processor;
/**
 * 
 * <p>
 * Title:北京地税核心征管系统--耕地占用税业务查询
 * </p>
 * <p>
 * Description:税源查询Processor
 * </p>
 * 
 * @author 开发部-李剑楠
 * @version 1.0
 */
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
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.gdzys.cx.model.Syjmmodel;
import com.ttsoft.bjtax.smsb.gdzys.cx.model.Symodel;
import com.ttsoft.bjtax.smsb.gdzys.cx.model.Symxmodel;
import com.ttsoft.bjtax.smsb.gdzys.cx.web.SycxForm;
import com.ttsoft.bjtax.smsb.model.client.swdwmodel;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

public class SycxProcessor implements Processor{

	public Object process(VOPackage vo) throws BaseException 
	{
		switch (vo.getAction()) 
		{
			//获取分局列表数据
	        case CodeConstant.SMSB_DQJSLIST:
	        return this.getfjsj(vo);
	        
	        //获取税务所列表数据
		    case CodeConstant.SMSB_CXDQJSLIST:
            return this.getswssj(vo);
            
            //列表查询
		    case CodeConstant.SMSB_QUERYACTION:
		    return this.doQueryList(vo);
		    
		    //详细信息查询
		    case CodeConstant.SMSB_QUERYA_DETATILACTION:
		    return this.doQueryDetail(vo);
        
        default:
            throw new ApplicationException(
                "ActionType有错误，processor中找不到相应的方法.");
		}
	}

/*---------------------------------------联动获取分局和税务所列表--2-------------------------------------------------------------------------*/	
	/**
	 * <1>获取分局列表
	 * @param vo
	 * @return
	 * @throws BaseException
	 */
	private Object getfjsj(VOPackage vo) throws BaseException 
	{
		SycxForm sycxForm = (SycxForm) vo.getData();
		Connection con = null;
		ResultSet rs = null;
		String sql = "";
		PreparedStatement cstmt = null;
		UserData userdata = (UserData) vo.getUserData();
		try {
				swdwmodel model = null;
				List list = new ArrayList();
				con = SfDBResource.getConnection();
				String gxswjgzzjgdm = userdata.getSsdwdm();
				
				//以90开头为市局标识  且以90开头应该只有1个，若出现90开头的税务所无法查询；
				if ("90".equals(gxswjgzzjgdm.substring(0, 2))) 
				{
					sql = "select   b.swjgzzjgdm  ,b.swjgzzjgmc "
						 +"from 	dmdb.gy_dm_swjgzzjg b "
						 +"where 	b.swjgzzjgdm like '%00' "
						 +" 	and b.swjgzzjgdm!='9000' "
						 +"order by b.swjgzzjgdm ";
				}else{
					sql = "select   b.swjgzzjgdm,b.swjgzzjgmc "
						 +"from 	dmdb.gy_dm_swjgzzjg b "
						 +"where 	b.swjgzzjgdm='"+ gxswjgzzjgdm.substring(0, 2) + "00' "
						 +"order by b.swjgzzjgdm ";
				}
				
					cstmt = con.prepareStatement(sql);
					rs = cstmt.executeQuery();
					
					//查得结果为所有分局
					while (rs.next()) 
					{
						model = new swdwmodel();
						model.setSwdwid(rs.getString(1));
						model.setSwdwmc(rs.getString(2));
						list.add(model);
					}
					
					//添加分局列表
					sycxForm.setFjlist(list);
					return sycxForm;

			} catch (Exception ex) {
				ex.printStackTrace();
				return null;
			} finally {
				try {
					if (rs != null) {rs.close();}
				} catch (Exception exx) {}
			
				try {
					if (cstmt != null) {cstmt.close();}
				} catch (Exception exx) {}
			
				try 
				{
					if (con != null) {con.close();}
				} catch (Exception exx) {}
			}

	}
	
	/**
	 * <2>获取税务所列表
	 * @param vo
	 * @return
	 * @throws BaseException
	 */
	private Object getswssj(VOPackage vo) throws BaseException {
		SycxForm sycxForm = (SycxForm) vo.getData();
		Connection con = null;
		ResultSet rs = null;
		String sql = "";
		PreparedStatement cstmt = null;
        UserData userdata = (UserData) vo.getUserData();
		try {
			swdwmodel model = null;
			List list = new ArrayList();
			con = SfDBResource.getConnection();
			
			//获取分局dm
			String gxswjgzzjgdm = sycxForm.getFjdm();
			
			// 根据是所级还是分局 查询税务所列表
			if ("30".equals(userdata.yhjb)) 
			{
				sql = "select	b.swjgzzjgdm,b.swjgzzjgmc "
					 +"from 	dmdb.gy_dm_swjgzzjg b "
					 +"where   	b.swjgzzjgdm='" + userdata.getSsdwdm()+ "' "
					 +"order by b.swjgzzjgdm ";
			} else {
				// 根据分局查税务所
				sql = "select	b.swjgzzjgdm,b.swjgzzjgmc "
					 +"from 	dmdb.gy_dm_swjgzzjg b "
					 +"where 	b.swjgzzjgdm like '"+ gxswjgzzjgdm.substring(0, 2)+ "%' "
					 +"     and b.swjgzzjgdm!='" + gxswjgzzjgdm + "' "
					 +"order by b.swjgzzjgdm ";
			}
			cstmt = con.prepareStatement(sql);
			rs = cstmt.executeQuery();
			while (rs.next()) {
				model = new swdwmodel();
				model.setSwdwid(rs.getString(1));
				model.setSwdwmc(rs.getString(2));
				list.add(model);
			}
			
			sycxForm.setSwslist(list);
			return sycxForm;
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			try {
				if (rs != null) {rs.close();}
			} catch (Exception exx) {}
			
			try {
				if (cstmt != null) {cstmt.close();}
			} catch (Exception exx) {
			}
			try {
				if (con != null) {con.close();}
			} catch (Exception exx) {
			}
		}

	}
	
/*---------------------------------------------------分页查询 --3-----------------------------------------------------------------------*/

	/**
	 * <1>查询分页列表
	 * @param vo
	 * @return
	 * @throws BaseException SQLException
	 */
	private Object doQueryList (VOPackage vo) throws BaseException
	{
		//获取查询条件
		SycxForm sycxForm = (SycxForm)vo.getData();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			//数据库连接
			con = SfDBResource.getConnection();
			
			//获取查询Statement
			ps =getPageQueryPs(sycxForm ,con);
			
			rs = ps.executeQuery();
			
			//设置结果
			setPageResultSet(rs,sycxForm);	
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		}
		finally
		{
			if(rs!=null)
			{
				try {rs.close();} catch (SQLException e) {throw ExceptionUtil.getBaseException(e);}
			}
			if(ps!=null)
			{
				try {ps.close();} catch (SQLException e) {throw ExceptionUtil.getBaseException(e);}
			}
				SfDBResource.freeConnection(con);	
		}
		
		return sycxForm;
	}
	

	/**
	 * <2>获取查询时用的PS,拼接查询SQL
	 * @param sycxForm
	 * @return
	 * @throws Exception
	 */
	private PreparedStatement getPageQueryPs(SycxForm sycxForm ,Connection con)throws Exception
	{
		//获取信息总条数
		StringBuffer sqlnum = new StringBuffer();
		sqlnum.append("select 	count(v.num) totalnum ");
		
		//获取查询
		StringBuffer sqlst = new StringBuffer();
		sqlst.append("select 	v.* ");
		
		StringBuffer sql = new StringBuffer();
		sql.append("from ( 		")
		   .append("		select 				")
		   .append("				rownum num, ")
		   .append("				sbbxlh, 	")
		   .append("				nsrmc,		")
		   .append("				jsjdm,		")
		   .append("				sbzt,		")
		   .append("				jsmj,		")
		   .append("				jmmj,		")
		   .append("				ynse,		")
		   .append("				pzjdwh,		")
		   .append("				cjrq,		")
		   .append("				bzms		")
		   .append("		from 		sbdb.sb_jl_gdzys_sbb sbb ")
		   .append("		where ");
		
		StringBuffer condition = new StringBuffer();
		
		//获取查询条件；
		String swsdm = null;								//税务所代码
		String jsjdm = null;								//计算机代码
		String sylxdm = null;								//税源类型代码
		String nsrlx = null;								//纳税人类型
		String starttime = null;							//起始日期
		String endtime = null;								//截止日期
		
		int maxPage ;        							//总页码 
		int pageSizeXx = 10; 								//每页显示条数，默认为10
		int currentPageXx = 1;  							//当前页码
		
		//查询条件
		if((sycxForm.getSwsdm()!=null && !(sycxForm.getSwsdm().trim().equals(""))) || (sycxForm.getFjdm()!=null && !(sycxForm.getFjdm().trim().equals(""))))		//查询条件--税务所代码
		{

			//查分局全局时
			if(sycxForm.getSwsdm()==null || sycxForm.getSwsdm().trim().equals(""))
			{
				if(condition.length()>0)
				{condition.append(" AND ");}
				condition.append("ZSJGDM like ?");
				swsdm = sycxForm.getFjdm().substring(0,2)+"%";
			}
			else{
				if(condition.length()>0)
				{condition.append(" AND ");}
				condition.append("ZSJGDM=?");
			
				swsdm = sycxForm.getSwsdm();
			}
		}
		if(sycxForm.getJsjdm()!=null && !(sycxForm.getJsjdm().trim().equals("")))		//查询条件--计算机代码
		{
			if(condition.length()>0)
			{condition.append(" AND ");}
			condition.append("JSJDM=?");
			
			jsjdm = sycxForm.getJsjdm();
		}
		if(sycxForm.getSylxdm()!=null && !(sycxForm.getSylxdm().trim().equals("")))		//查询条件--税源类型
		{
			if(condition.length()>0)
			{condition.append(" AND ");}
			condition.append("SYLXDM=?");
			
			sylxdm = sycxForm.getSylxdm();
		}
		if(sycxForm.getNsrlx()!=null && !(sycxForm.getNsrlx().trim().equals("")))		//查询条件--纳税人类型
		{
			if(condition.length()>0)
			{condition.append(" AND ");}
			condition.append("NSRLX=?");
			
			nsrlx = sycxForm.getNsrlx();
		}
		if(sycxForm.getStarttime()!=null && !(sycxForm.getStarttime().trim().equals("")))		//查询条件--时间段--起始日期
		{
			if(condition.length()>0)
			{condition.append(" AND ");}
			condition.append("CJRQ>=to_date(?,'yyyymmdd')");
			
			starttime = sycxForm.getStarttime();
		}
		if(sycxForm.getEndtime()!=null && !(sycxForm.getEndtime().trim().equals("")))		//查询条件--时间段--截止日期
		{
			if(condition.length()>0)
			{condition.append(" AND ");}
			condition.append("CJRQ<=to_date(?,'yyyymmdd')");
			
			endtime = sycxForm.getEndtime();
		}
		
		//如果一个查询条件都没有 where条件为true
		if(swsdm == null && jsjdm == null && sylxdm == null && nsrlx == null && starttime == null && endtime == null)
		{
			condition.append(" 1=1 ");
		}
		
		//分页
		//设置总页数
		if(sycxForm.getMaxPage()==-1)
		{
			PreparedStatement psnum = null;
			String sqlNumString =sqlnum.toString()+ sql.toString()+condition.toString()+ " order by CJRQ ) v    ";
			psnum = con.prepareStatement(sqlNumString ,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			int i=1;
			if(swsdm != null)
			{
				psnum.setString(i, swsdm);
				i++;
			}
			if(jsjdm != null)
			{
				psnum.setString(i, jsjdm);
				i++;
			}
			if(sylxdm != null)
			{
				psnum.setString(i, sylxdm);
				i++;
			}
			if(nsrlx != null)
			{
				psnum.setString(i, nsrlx);
				i++;
			}
			if(starttime != null)
			{
				psnum.setString(i, starttime);
				i++;
			}
			if(endtime != null)
			{
				psnum.setString(i, endtime);
				i++;
			}
			ResultSet rsnum  = psnum.executeQuery();
			
			//根据查到的总条数算出页数添加到Form中
			int total = 0;
			 rsnum.next();
			 int totalinf = rsnum.getInt("totalnum");
			 total=(totalinf%sycxForm.getPageSizeXx())==0?totalinf/sycxForm.getPageSizeXx():totalinf/sycxForm.getPageSizeXx()+1;
			sycxForm.setMaxPage(total);
			rsnum.close();
		}
		
		//已设置总页数
		condition.append(	" order by CJRQ ) v         "
				 		   +"where 					   "
				 		   +"      v.num>=? AND v.num<?"
				 		);
		
		if(sycxForm.getMaxPage()>=0)					//总页码
		{
			maxPage=sycxForm.getMaxPage();
		}
		if(sycxForm.getCurrentPageXx()>=0)				//当前页码
		{
			currentPageXx=sycxForm.getCurrentPageXx();
		}
		if(sycxForm.getPageSizeXx()>=0)					//每页显示条数
		{
			pageSizeXx=sycxForm.getPageSizeXx();
		}
		 int startNum = (currentPageXx-1)*pageSizeXx+1;
	     int endNum = currentPageXx*pageSizeXx+1;

		PreparedStatement ps = null;
		String sqlString =sqlst.toString()+ sql.toString()+condition.toString();
		ps = con.prepareStatement(sqlString ,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		
		//添加参数值
		int i=1;
		if(swsdm != null)
		{
			ps.setString(i, swsdm);
			i++;
		}
		if(jsjdm != null)
		{
			ps.setString(i, jsjdm);
			i++;
		}
		if(sylxdm != null)
		{
			ps.setString(i, sylxdm);
			i++;
		}
		if(nsrlx != null)
		{
			ps.setString(i, nsrlx);
			i++;
		}
		if(starttime != null)
		{
			ps.setString(i, starttime);
			i++;
		}
		if(endtime != null)
		{
			ps.setString(i, endtime);
			i++;
		}
		ps.setInt(i++,startNum );	//起始条数
		ps.setInt(i, endNum);		//结束条数；
	
		return ps;
	}

	/**
	 * <3>将从数据库查得的列表信息添加到form中
	 * @param rs
	 * @param sycxForm
	 * @throws Exception
	 */
	private void setPageResultSet(ResultSet rs, SycxForm sycxForm)throws Exception
	{
		//获取结果集条数
		rs.last();
		int rowCount = rs.getRow();
		sycxForm.setTotalnum(rowCount);
		rs.beforeFirst();
		
		//信息列
		List infList = new ArrayList();
		while(rs.next())
		{
			SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			//基本信息
			Symodel symodel = new Symodel();
			symodel.setNsrmc(rs.getString("NSRMC"));								//纳税人名称
			symodel.setJsjdm(rs.getString("JSJDM"));								//计算机代码
			symodel.setSbbxlh(rs.getString("SBBXLH"));								//申报序列号
			symodel.setSbzt(rs.getString("SBZT"));									//申报状态
			symodel.setBz(rs.getString("BZMS"));									//备注描述
						
			//土地信息
			symodel.setJsmj(rs.getBigDecimal("JSMJ")==null?"0.00":String.valueOf(rs.getBigDecimal("JSMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));		//计税面积
			symodel.setJmmj(rs.getBigDecimal("JMMJ")==null?"0.00":String.valueOf(rs.getBigDecimal("JMMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));    	//减免面积
			symodel.setYnse(rs.getBigDecimal("YNSE")==null?"0.00":String.valueOf(rs.getBigDecimal("YNSE").setScale(2,BigDecimal.ROUND_HALF_UP)));		//应纳税额
			symodel.setZdpwh (rs.getString("PZJDWH"));							    																	//占地批文号
		
			//创建信息
			if(rs.getDate("CJRQ")!=null)
			{	
				symodel.setCjsj(time.format(new Date(rs.getTimestamp("CJRQ").getTime())));										//创建时间
			}else
			{
				symodel.setCjsj("");
			}			
						
			//添加一条申报信息
			infList.add(symodel);
		}
		sycxForm.setInfList(infList);
	}
	
/*------------------------------------------详细查询------------------------------------------------------------------*/

	/**
	 * <1>查询详细信息
	 * @param vo
	 * @throws Exception
	 */
	private Object doQueryDetail(VOPackage vo) throws BaseException
	{
		//获取查询条件
		SycxForm sycxForm = (SycxForm)vo.getData();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			//数据库连接
			con = SfDBResource.getConnection();
			
			//获取查询Statement
			String sql = "select * "
						+"from 	sbdb.sb_jl_gdzys_sbb sbb ,dmdb.gd_dm_sylx sylx_dmb  "
						+"where sbb.sylxdm = sylx_dmb.sylxdm  "
						+" 	AND sbbxlh = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1,sycxForm.getSbbxlh());
			rs = ps.executeQuery();
			
			//设置结果
			setDetailResultSet(rs,sycxForm,vo,con);	
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		}
		finally
		{
			if(rs!=null)
			{
				try {rs.close();} catch (SQLException e) {throw ExceptionUtil.getBaseException(e);}
			}
			if(ps!=null)
			{
				try {ps.close();} catch (SQLException e) {throw ExceptionUtil.getBaseException(e);}
			}
				SfDBResource.freeConnection(con);	
		}
		
		return sycxForm;
	}
	
	/**
	 * <2>设置税源信息
	 * @param rs
	 * @param sycxForm
	 * @param vo
	 * @param con
	 * @throws Exception
	 */
	private void setDetailResultSet(ResultSet rs, SycxForm sycxForm, VOPackage vo, Connection con)throws Exception
	{
		//信息列
		List infList = new ArrayList();														//信息条数
		while(rs.next())
		{	
			SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat time2 = new SimpleDateFormat("yyyy-MM-dd");
			
			//基本信息
			Symodel symodel = new Symodel();
			symodel.setNsrmc(rs.getString("NSRMC"));								//纳税人名称
			symodel.setJsjdm(rs.getString("JSJDM"));								//计算机代码
			symodel.setSbbxlh(rs.getString("SBBXLH"));								//申报序列号
			symodel.setNsrlx(rs.getString("NSRLX"));								//纳税人类型
			symodel.setSfsjsp(rs.getString("SFSJSP"));								//是否市局审批
			symodel.setSylx(rs.getString("SYLXMC"));								//税源类型	
			symodel.setNsrxxdz(rs.getString("NSRXXDZ"));							//纳税人详细地址
			symodel.setKhyh(rs.getString("KHYHMC"));								//开户银行
			symodel.setYhzh(rs.getString("YHZH"));									//银行账号
			symodel.setLxrxm(rs.getString("LXRXM"));								//联系人姓名
			symodel.setLxdh(rs.getString("LXDH"));									//联系电话
			symodel.setSfzzlx(rs.getString("SFZZLX"));								//身份证照类型
			symodel.setSfzzhm(rs.getString("SFZZHM"));								//身份证照号码
			symodel.setJmly(rs.getString("SBJMLY"));								//减免理由
			symodel.setBz(rs.getString("BZMS"));									//备注描述
			symodel.setSbzt(rs.getString("SBZT"));									//申报状态
						
			//土地信息
			symodel.setJsmj(rs.getBigDecimal("JSMJ")==null?"0.00":String.valueOf(rs.getBigDecimal("JSMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));		//计税面积
			symodel.setJzse(rs.getBigDecimal("JZSE")==null?"0.00":String.valueOf(rs.getBigDecimal("JZSE").setScale(2,BigDecimal.ROUND_HALF_UP)));    	//计征税额
			symodel.setJmmj(rs.getBigDecimal("JMMJ")==null?"0.00":String.valueOf(rs.getBigDecimal("JMMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));    	//减免面积
			symodel.setJmse(rs.getBigDecimal("JMSE")==null?"0.00":String.valueOf(rs.getBigDecimal("JMSE").setScale(2,BigDecimal.ROUND_HALF_UP)));    	//减免税额
			symodel.setYsmj(rs.getBigDecimal("YSMJ")==null?"0.00":String.valueOf(rs.getBigDecimal("YSMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));   	//应税面积
			symodel.setYnse(rs.getBigDecimal("YNSE")==null?"0.00":String.valueOf(rs.getBigDecimal("YNSE").setScale(2,BigDecimal.ROUND_HALF_UP)));		//应纳税额
			symodel.setZdpwh (rs.getString("PZJDWH"));							    																	//占地批文号
			symodel.setTdzldz(rs.getString("TDZLDZ"));							    																	//土地坐落地址
			symodel.setPzzdmj(rs.getBigDecimal("PZJDMJ")==null?"":String.valueOf(rs.getBigDecimal("PZJDMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));    	//批准占地面积
			symodel.setJsxmmc(rs.getString("JSXMMC"));							    																	//建设项目名称
			symodel.setSjzdmj(rs.getBigDecimal("SJZDMJ")==null?"":String.valueOf(rs.getBigDecimal("SJZDMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));		//实际占地面积
			if(rs.getDate("ZDSJ")!=null)
			{					
				symodel.setZdsj(time2.format(rs.getDate("ZDSJ")));																						//占地时间
			}
						
			//创建信息	
			symodel.setCjr(rs.getString("CJR"));														//创建人
			if(rs.getDate("CJRQ")!=null)
			{	
				symodel.setCjsj(time.format(new Date(rs.getTimestamp("CJRQ").getTime())));				//创建时间
			}else
			{
				symodel.setCjsj("");
			}
			if(rs.getString("QRZT")==null)
			{symodel.setQrzt("0");}
			else
			{symodel.setQrzt(rs.getString("QRZT"));}													//确认状态
			symodel.setQrr(rs.getString("QRR"));														//确认人
			if(rs.getDate("QRRQ")!=null)
			{
				symodel.setQrsj(time.format(new Date(rs.getTimestamp("QRRQ").getTime())));				//确认时间
			}else
			{
				symodel.setQrsj("");
			}
						
			if(rs.getString("SJQRZT")==null)
			{symodel.setSjqrzt("0");}
			else
			{symodel.setSjqrzt(rs.getString("SJQRZT"));}												//市局确认状态
			symodel.setSjqrr(rs.getString("SJQRR"));													//市局确认人
			if(rs.getDate("SJQRRQ")!=null)
			{
				symodel.setSjqrsj(time.format(new Date(rs.getTimestamp("SJQRRQ").getTime())));			//市局确认时间
			}else
			{
				symodel.setSjqrsj("");
			}
			
			//通过接口信息添加
			setInterfaceInf(symodel, vo);
						
			//占地信息
			setZdinfoResultSet(con ,symodel);		
						
			//减免信息
			setJminfResultSet(con ,symodel );
						
			//一条申报信息完成
			infList.add(symodel);
			
		}
		sycxForm.setInfList(infList);
	}
	
	/**
	 * <3>保存从申报详细表查到的结果 
	 * @param con--数据库连接
	 * @param jmsql--信息存储
	 * @throws SQLException 
	 */
	private void setZdinfoResultSet(Connection con ,Symodel symodel ) throws SQLException
	{
		String xxsql = "select * " 
			   		  +"from 		sbdb.SB_JL_GDZYS_SBBSBMX sbmxb     " 
			   		  +"    	   ,dmdb.gd_dm_zdyt          zdyt_dmb  "
			   		  +"where " 
			   		  +" 	sbmxb.ZDYTDM = zdyt_dmb.ZDYTDM "
			   		  +" 	AND SBBXLH=? "
			   		  +"order by xh";	
		PreparedStatement ps = con.prepareStatement(xxsql);
		ps.setString(1, symodel.getSbbxlh());
		ResultSet rs = ps.executeQuery();
		List symxList = new ArrayList();
		while(rs.next())
		{
			Symxmodel symxmodel = new Symxmodel ();
			symxmodel.setZdyt(rs.getString("ZDYTMC"));									//占地用途
			symxmodel.setSyse(rs.getBigDecimal("SYSE")==null?"":String.valueOf(rs.getBigDecimal("SYSE").setScale(2,BigDecimal.ROUND_HALF_UP)));			//适用税额
			symxmodel.setJsmj(rs.getBigDecimal("JSMJ")==null?"0.00":String.valueOf(rs.getBigDecimal("JSMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));		//计税面积
			symxmodel.setJzse(rs.getBigDecimal("JZSE")==null?"0.00":String.valueOf(rs.getBigDecimal("JZSE").setScale(2,BigDecimal.ROUND_HALF_UP)));    	//计征税额
			symxmodel.setJmmj(rs.getBigDecimal("JMMJ")==null?"0.00":String.valueOf(rs.getBigDecimal("JMMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));    	//减免面积
			symxmodel.setJmse(rs.getBigDecimal("JMSE")==null?"0.00":String.valueOf(rs.getBigDecimal("JMSE").setScale(2,BigDecimal.ROUND_HALF_UP)));    	//减免税额
			symxmodel.setYsmj(rs.getBigDecimal("YSMJ")==null?"0.00":String.valueOf(rs.getBigDecimal("YSMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));   		//应税面积
			symxmodel.setYnse(rs.getBigDecimal("YNSE")==null?"0.00":String.valueOf(rs.getBigDecimal("YNSE").setScale(2,BigDecimal.ROUND_HALF_UP)));		//应纳税额
			
			symxList.add(symxmodel);
		}
		symodel.setSbmx(symxList);
		
		//关闭
		if(rs!=null)
		{rs.close();}
		if(ps!=null)
		{ps.close();}
	}
	
	/**
	 * <4>保存从申报减免详细表查到的结果
	 * @param con
	 * @param gdzydjcxmodel
	 * @param String
	 * @throws SQLException 
	 */
	private void setJminfResultSet(Connection con ,Symodel symodel ) throws SQLException
	{
		
		String jmsql = "select * "
			   		  +"from       sbdb.SB_JL_GDZYS_SBBJMMX jmb       "
			   		  +"		  ,dmdb.gd_dm_jmslb         jmlb      "
			   		  +"where " 
			   		  +" 	jmb.JMSLBDM  = jmlb.JMSLBDM "
			   		  +" 	AND SBBXLH=? ";  
		PreparedStatement ps = con.prepareStatement(jmsql);
		ps.setString(1, symodel.getSbbxlh());
		ResultSet rs = ps.executeQuery();
		List syjmList = new ArrayList();
		while(rs.next())
		{
			Syjmmodel syjmmodel = new Syjmmodel();
			syjmmodel.setJmslb(rs.getString("JMSLBMC"));									//减免税类别名称
			syjmmodel.setJmmj(rs.getBigDecimal("JMMJ")==null?"0.00":String.valueOf(rs.getBigDecimal("JMMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));    	//减免面积
			syjmmodel.setJmse(rs.getBigDecimal("JMSE")==null?"0.00":String.valueOf(rs.getBigDecimal("JMSE").setScale(2,BigDecimal.ROUND_HALF_UP)));    	//减免税额
			
			syjmList.add(syjmmodel);
		}
		if(syjmList.size()!= 0)
		{
			symodel.setJmxx(syjmList);
		};
		
		//关闭
		if(rs!=null)
		{rs.close();}
		if(ps!=null)
		{ps.close();}
	}
	
	/**
	 * <5>添加从接口得到的信息
	 * @param Gdzydjcxmodel
	 * @return
	 * @throws ApplicationException 
	 */
	private void setInterfaceInf(Symodel symodel ,VOPackage vo) throws ApplicationException
	{
		SWDJJBSJ dj = null;
		try {
			
			UserData ud = vo.getUserData();
			if(symodel.getNsrlx().equals("0"))
			{
				dj = (SWDJJBSJ) InterfaceDj.getJBSJ(symodel.getJsjdm(), ud);
				symodel.setNsrsshy(dj.getGjbzhymc());													//纳税人所属行业
				symodel.setQydjzclx(dj.getDjzclxmc());													//企业登记注册类型
				symodel.setNsrsbh(dj.getSwdjzh()+"-"+dj.getSwjgzzjgdm());								//纳税人识别号	
			}
					
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}

	}
}
