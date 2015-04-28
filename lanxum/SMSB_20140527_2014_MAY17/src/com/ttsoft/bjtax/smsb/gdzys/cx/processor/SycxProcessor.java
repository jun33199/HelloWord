
package com.ttsoft.bjtax.smsb.gdzys.cx.processor;
/**
 * 
 * <p>
 * Title:������˰��������ϵͳ--����ռ��˰ҵ���ѯ
 * </p>
 * <p>
 * Description:˰Դ��ѯProcessor
 * </p>
 * 
 * @author ������-��
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
			//��ȡ�־��б�����
	        case CodeConstant.SMSB_DQJSLIST:
	        return this.getfjsj(vo);
	        
	        //��ȡ˰�����б�����
		    case CodeConstant.SMSB_CXDQJSLIST:
            return this.getswssj(vo);
            
            //�б��ѯ
		    case CodeConstant.SMSB_QUERYACTION:
		    return this.doQueryList(vo);
		    
		    //��ϸ��Ϣ��ѯ
		    case CodeConstant.SMSB_QUERYA_DETATILACTION:
		    return this.doQueryDetail(vo);
        
        default:
            throw new ApplicationException(
                "ActionType�д���processor���Ҳ�����Ӧ�ķ���.");
		}
	}

/*---------------------------------------������ȡ�־ֺ�˰�����б�--2-------------------------------------------------------------------------*/	
	/**
	 * <1>��ȡ�־��б�
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
				
				//��90��ͷΪ�оֱ�ʶ  ����90��ͷӦ��ֻ��1����������90��ͷ��˰�����޷���ѯ��
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
					
					//��ý��Ϊ���з־�
					while (rs.next()) 
					{
						model = new swdwmodel();
						model.setSwdwid(rs.getString(1));
						model.setSwdwmc(rs.getString(2));
						list.add(model);
					}
					
					//��ӷ־��б�
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
	 * <2>��ȡ˰�����б�
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
			
			//��ȡ�־�dm
			String gxswjgzzjgdm = sycxForm.getFjdm();
			
			// �������������Ƿ־� ��ѯ˰�����б�
			if ("30".equals(userdata.yhjb)) 
			{
				sql = "select	b.swjgzzjgdm,b.swjgzzjgmc "
					 +"from 	dmdb.gy_dm_swjgzzjg b "
					 +"where   	b.swjgzzjgdm='" + userdata.getSsdwdm()+ "' "
					 +"order by b.swjgzzjgdm ";
			} else {
				// ���ݷ־ֲ�˰����
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
	
/*---------------------------------------------------��ҳ��ѯ --3-----------------------------------------------------------------------*/

	/**
	 * <1>��ѯ��ҳ�б�
	 * @param vo
	 * @return
	 * @throws BaseException SQLException
	 */
	private Object doQueryList (VOPackage vo) throws BaseException
	{
		//��ȡ��ѯ����
		SycxForm sycxForm = (SycxForm)vo.getData();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			//���ݿ�����
			con = SfDBResource.getConnection();
			
			//��ȡ��ѯStatement
			ps =getPageQueryPs(sycxForm ,con);
			
			rs = ps.executeQuery();
			
			//���ý��
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
	 * <2>��ȡ��ѯʱ�õ�PS,ƴ�Ӳ�ѯSQL
	 * @param sycxForm
	 * @return
	 * @throws Exception
	 */
	private PreparedStatement getPageQueryPs(SycxForm sycxForm ,Connection con)throws Exception
	{
		//��ȡ��Ϣ������
		StringBuffer sqlnum = new StringBuffer();
		sqlnum.append("select 	count(v.num) totalnum ");
		
		//��ȡ��ѯ
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
		
		//��ȡ��ѯ������
		String swsdm = null;								//˰��������
		String jsjdm = null;								//���������
		String sylxdm = null;								//˰Դ���ʹ���
		String nsrlx = null;								//��˰������
		String starttime = null;							//��ʼ����
		String endtime = null;								//��ֹ����
		
		int maxPage ;        							//��ҳ�� 
		int pageSizeXx = 10; 								//ÿҳ��ʾ������Ĭ��Ϊ10
		int currentPageXx = 1;  							//��ǰҳ��
		
		//��ѯ����
		if((sycxForm.getSwsdm()!=null && !(sycxForm.getSwsdm().trim().equals(""))) || (sycxForm.getFjdm()!=null && !(sycxForm.getFjdm().trim().equals(""))))		//��ѯ����--˰��������
		{

			//��־�ȫ��ʱ
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
		if(sycxForm.getJsjdm()!=null && !(sycxForm.getJsjdm().trim().equals("")))		//��ѯ����--���������
		{
			if(condition.length()>0)
			{condition.append(" AND ");}
			condition.append("JSJDM=?");
			
			jsjdm = sycxForm.getJsjdm();
		}
		if(sycxForm.getSylxdm()!=null && !(sycxForm.getSylxdm().trim().equals("")))		//��ѯ����--˰Դ����
		{
			if(condition.length()>0)
			{condition.append(" AND ");}
			condition.append("SYLXDM=?");
			
			sylxdm = sycxForm.getSylxdm();
		}
		if(sycxForm.getNsrlx()!=null && !(sycxForm.getNsrlx().trim().equals("")))		//��ѯ����--��˰������
		{
			if(condition.length()>0)
			{condition.append(" AND ");}
			condition.append("NSRLX=?");
			
			nsrlx = sycxForm.getNsrlx();
		}
		if(sycxForm.getStarttime()!=null && !(sycxForm.getStarttime().trim().equals("")))		//��ѯ����--ʱ���--��ʼ����
		{
			if(condition.length()>0)
			{condition.append(" AND ");}
			condition.append("CJRQ>=to_date(?,'yyyymmdd')");
			
			starttime = sycxForm.getStarttime();
		}
		if(sycxForm.getEndtime()!=null && !(sycxForm.getEndtime().trim().equals("")))		//��ѯ����--ʱ���--��ֹ����
		{
			if(condition.length()>0)
			{condition.append(" AND ");}
			condition.append("CJRQ<=to_date(?,'yyyymmdd')");
			
			endtime = sycxForm.getEndtime();
		}
		
		//���һ����ѯ������û�� where����Ϊtrue
		if(swsdm == null && jsjdm == null && sylxdm == null && nsrlx == null && starttime == null && endtime == null)
		{
			condition.append(" 1=1 ");
		}
		
		//��ҳ
		//������ҳ��
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
			
			//���ݲ鵽�����������ҳ����ӵ�Form��
			int total = 0;
			 rsnum.next();
			 int totalinf = rsnum.getInt("totalnum");
			 total=(totalinf%sycxForm.getPageSizeXx())==0?totalinf/sycxForm.getPageSizeXx():totalinf/sycxForm.getPageSizeXx()+1;
			sycxForm.setMaxPage(total);
			rsnum.close();
		}
		
		//��������ҳ��
		condition.append(	" order by CJRQ ) v         "
				 		   +"where 					   "
				 		   +"      v.num>=? AND v.num<?"
				 		);
		
		if(sycxForm.getMaxPage()>=0)					//��ҳ��
		{
			maxPage=sycxForm.getMaxPage();
		}
		if(sycxForm.getCurrentPageXx()>=0)				//��ǰҳ��
		{
			currentPageXx=sycxForm.getCurrentPageXx();
		}
		if(sycxForm.getPageSizeXx()>=0)					//ÿҳ��ʾ����
		{
			pageSizeXx=sycxForm.getPageSizeXx();
		}
		 int startNum = (currentPageXx-1)*pageSizeXx+1;
	     int endNum = currentPageXx*pageSizeXx+1;

		PreparedStatement ps = null;
		String sqlString =sqlst.toString()+ sql.toString()+condition.toString();
		ps = con.prepareStatement(sqlString ,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		
		//��Ӳ���ֵ
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
		ps.setInt(i++,startNum );	//��ʼ����
		ps.setInt(i, endNum);		//����������
	
		return ps;
	}

	/**
	 * <3>�������ݿ��õ��б���Ϣ��ӵ�form��
	 * @param rs
	 * @param sycxForm
	 * @throws Exception
	 */
	private void setPageResultSet(ResultSet rs, SycxForm sycxForm)throws Exception
	{
		//��ȡ���������
		rs.last();
		int rowCount = rs.getRow();
		sycxForm.setTotalnum(rowCount);
		rs.beforeFirst();
		
		//��Ϣ��
		List infList = new ArrayList();
		while(rs.next())
		{
			SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			//������Ϣ
			Symodel symodel = new Symodel();
			symodel.setNsrmc(rs.getString("NSRMC"));								//��˰������
			symodel.setJsjdm(rs.getString("JSJDM"));								//���������
			symodel.setSbbxlh(rs.getString("SBBXLH"));								//�걨���к�
			symodel.setSbzt(rs.getString("SBZT"));									//�걨״̬
			symodel.setBz(rs.getString("BZMS"));									//��ע����
						
			//������Ϣ
			symodel.setJsmj(rs.getBigDecimal("JSMJ")==null?"0.00":String.valueOf(rs.getBigDecimal("JSMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));		//��˰���
			symodel.setJmmj(rs.getBigDecimal("JMMJ")==null?"0.00":String.valueOf(rs.getBigDecimal("JMMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));    	//�������
			symodel.setYnse(rs.getBigDecimal("YNSE")==null?"0.00":String.valueOf(rs.getBigDecimal("YNSE").setScale(2,BigDecimal.ROUND_HALF_UP)));		//Ӧ��˰��
			symodel.setZdpwh (rs.getString("PZJDWH"));							    																	//ռ�����ĺ�
		
			//������Ϣ
			if(rs.getDate("CJRQ")!=null)
			{	
				symodel.setCjsj(time.format(new Date(rs.getTimestamp("CJRQ").getTime())));										//����ʱ��
			}else
			{
				symodel.setCjsj("");
			}			
						
			//���һ���걨��Ϣ
			infList.add(symodel);
		}
		sycxForm.setInfList(infList);
	}
	
/*------------------------------------------��ϸ��ѯ------------------------------------------------------------------*/

	/**
	 * <1>��ѯ��ϸ��Ϣ
	 * @param vo
	 * @throws Exception
	 */
	private Object doQueryDetail(VOPackage vo) throws BaseException
	{
		//��ȡ��ѯ����
		SycxForm sycxForm = (SycxForm)vo.getData();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			//���ݿ�����
			con = SfDBResource.getConnection();
			
			//��ȡ��ѯStatement
			String sql = "select * "
						+"from 	sbdb.sb_jl_gdzys_sbb sbb ,dmdb.gd_dm_sylx sylx_dmb  "
						+"where sbb.sylxdm = sylx_dmb.sylxdm  "
						+" 	AND sbbxlh = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1,sycxForm.getSbbxlh());
			rs = ps.executeQuery();
			
			//���ý��
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
	 * <2>����˰Դ��Ϣ
	 * @param rs
	 * @param sycxForm
	 * @param vo
	 * @param con
	 * @throws Exception
	 */
	private void setDetailResultSet(ResultSet rs, SycxForm sycxForm, VOPackage vo, Connection con)throws Exception
	{
		//��Ϣ��
		List infList = new ArrayList();														//��Ϣ����
		while(rs.next())
		{	
			SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat time2 = new SimpleDateFormat("yyyy-MM-dd");
			
			//������Ϣ
			Symodel symodel = new Symodel();
			symodel.setNsrmc(rs.getString("NSRMC"));								//��˰������
			symodel.setJsjdm(rs.getString("JSJDM"));								//���������
			symodel.setSbbxlh(rs.getString("SBBXLH"));								//�걨���к�
			symodel.setNsrlx(rs.getString("NSRLX"));								//��˰������
			symodel.setSfsjsp(rs.getString("SFSJSP"));								//�Ƿ��о�����
			symodel.setSylx(rs.getString("SYLXMC"));								//˰Դ����	
			symodel.setNsrxxdz(rs.getString("NSRXXDZ"));							//��˰����ϸ��ַ
			symodel.setKhyh(rs.getString("KHYHMC"));								//��������
			symodel.setYhzh(rs.getString("YHZH"));									//�����˺�
			symodel.setLxrxm(rs.getString("LXRXM"));								//��ϵ������
			symodel.setLxdh(rs.getString("LXDH"));									//��ϵ�绰
			symodel.setSfzzlx(rs.getString("SFZZLX"));								//���֤������
			symodel.setSfzzhm(rs.getString("SFZZHM"));								//���֤�պ���
			symodel.setJmly(rs.getString("SBJMLY"));								//��������
			symodel.setBz(rs.getString("BZMS"));									//��ע����
			symodel.setSbzt(rs.getString("SBZT"));									//�걨״̬
						
			//������Ϣ
			symodel.setJsmj(rs.getBigDecimal("JSMJ")==null?"0.00":String.valueOf(rs.getBigDecimal("JSMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));		//��˰���
			symodel.setJzse(rs.getBigDecimal("JZSE")==null?"0.00":String.valueOf(rs.getBigDecimal("JZSE").setScale(2,BigDecimal.ROUND_HALF_UP)));    	//����˰��
			symodel.setJmmj(rs.getBigDecimal("JMMJ")==null?"0.00":String.valueOf(rs.getBigDecimal("JMMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));    	//�������
			symodel.setJmse(rs.getBigDecimal("JMSE")==null?"0.00":String.valueOf(rs.getBigDecimal("JMSE").setScale(2,BigDecimal.ROUND_HALF_UP)));    	//����˰��
			symodel.setYsmj(rs.getBigDecimal("YSMJ")==null?"0.00":String.valueOf(rs.getBigDecimal("YSMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));   	//Ӧ˰���
			symodel.setYnse(rs.getBigDecimal("YNSE")==null?"0.00":String.valueOf(rs.getBigDecimal("YNSE").setScale(2,BigDecimal.ROUND_HALF_UP)));		//Ӧ��˰��
			symodel.setZdpwh (rs.getString("PZJDWH"));							    																	//ռ�����ĺ�
			symodel.setTdzldz(rs.getString("TDZLDZ"));							    																	//���������ַ
			symodel.setPzzdmj(rs.getBigDecimal("PZJDMJ")==null?"":String.valueOf(rs.getBigDecimal("PZJDMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));    	//��׼ռ�����
			symodel.setJsxmmc(rs.getString("JSXMMC"));							    																	//������Ŀ����
			symodel.setSjzdmj(rs.getBigDecimal("SJZDMJ")==null?"":String.valueOf(rs.getBigDecimal("SJZDMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));		//ʵ��ռ�����
			if(rs.getDate("ZDSJ")!=null)
			{					
				symodel.setZdsj(time2.format(rs.getDate("ZDSJ")));																						//ռ��ʱ��
			}
						
			//������Ϣ	
			symodel.setCjr(rs.getString("CJR"));														//������
			if(rs.getDate("CJRQ")!=null)
			{	
				symodel.setCjsj(time.format(new Date(rs.getTimestamp("CJRQ").getTime())));				//����ʱ��
			}else
			{
				symodel.setCjsj("");
			}
			if(rs.getString("QRZT")==null)
			{symodel.setQrzt("0");}
			else
			{symodel.setQrzt(rs.getString("QRZT"));}													//ȷ��״̬
			symodel.setQrr(rs.getString("QRR"));														//ȷ����
			if(rs.getDate("QRRQ")!=null)
			{
				symodel.setQrsj(time.format(new Date(rs.getTimestamp("QRRQ").getTime())));				//ȷ��ʱ��
			}else
			{
				symodel.setQrsj("");
			}
						
			if(rs.getString("SJQRZT")==null)
			{symodel.setSjqrzt("0");}
			else
			{symodel.setSjqrzt(rs.getString("SJQRZT"));}												//�о�ȷ��״̬
			symodel.setSjqrr(rs.getString("SJQRR"));													//�о�ȷ����
			if(rs.getDate("SJQRRQ")!=null)
			{
				symodel.setSjqrsj(time.format(new Date(rs.getTimestamp("SJQRRQ").getTime())));			//�о�ȷ��ʱ��
			}else
			{
				symodel.setSjqrsj("");
			}
			
			//ͨ���ӿ���Ϣ���
			setInterfaceInf(symodel, vo);
						
			//ռ����Ϣ
			setZdinfoResultSet(con ,symodel);		
						
			//������Ϣ
			setJminfResultSet(con ,symodel );
						
			//һ���걨��Ϣ���
			infList.add(symodel);
			
		}
		sycxForm.setInfList(infList);
	}
	
	/**
	 * <3>������걨��ϸ��鵽�Ľ�� 
	 * @param con--���ݿ�����
	 * @param jmsql--��Ϣ�洢
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
			symxmodel.setZdyt(rs.getString("ZDYTMC"));									//ռ����;
			symxmodel.setSyse(rs.getBigDecimal("SYSE")==null?"":String.valueOf(rs.getBigDecimal("SYSE").setScale(2,BigDecimal.ROUND_HALF_UP)));			//����˰��
			symxmodel.setJsmj(rs.getBigDecimal("JSMJ")==null?"0.00":String.valueOf(rs.getBigDecimal("JSMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));		//��˰���
			symxmodel.setJzse(rs.getBigDecimal("JZSE")==null?"0.00":String.valueOf(rs.getBigDecimal("JZSE").setScale(2,BigDecimal.ROUND_HALF_UP)));    	//����˰��
			symxmodel.setJmmj(rs.getBigDecimal("JMMJ")==null?"0.00":String.valueOf(rs.getBigDecimal("JMMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));    	//�������
			symxmodel.setJmse(rs.getBigDecimal("JMSE")==null?"0.00":String.valueOf(rs.getBigDecimal("JMSE").setScale(2,BigDecimal.ROUND_HALF_UP)));    	//����˰��
			symxmodel.setYsmj(rs.getBigDecimal("YSMJ")==null?"0.00":String.valueOf(rs.getBigDecimal("YSMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));   		//Ӧ˰���
			symxmodel.setYnse(rs.getBigDecimal("YNSE")==null?"0.00":String.valueOf(rs.getBigDecimal("YNSE").setScale(2,BigDecimal.ROUND_HALF_UP)));		//Ӧ��˰��
			
			symxList.add(symxmodel);
		}
		symodel.setSbmx(symxList);
		
		//�ر�
		if(rs!=null)
		{rs.close();}
		if(ps!=null)
		{ps.close();}
	}
	
	/**
	 * <4>������걨������ϸ��鵽�Ľ��
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
			syjmmodel.setJmslb(rs.getString("JMSLBMC"));									//����˰�������
			syjmmodel.setJmmj(rs.getBigDecimal("JMMJ")==null?"0.00":String.valueOf(rs.getBigDecimal("JMMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));    	//�������
			syjmmodel.setJmse(rs.getBigDecimal("JMSE")==null?"0.00":String.valueOf(rs.getBigDecimal("JMSE").setScale(2,BigDecimal.ROUND_HALF_UP)));    	//����˰��
			
			syjmList.add(syjmmodel);
		}
		if(syjmList.size()!= 0)
		{
			symodel.setJmxx(syjmList);
		};
		
		//�ر�
		if(rs!=null)
		{rs.close();}
		if(ps!=null)
		{ps.close();}
	}
	
	/**
	 * <5>��Ӵӽӿڵõ�����Ϣ
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
				symodel.setNsrsshy(dj.getGjbzhymc());													//��˰��������ҵ
				symodel.setQydjzclx(dj.getDjzclxmc());													//��ҵ�Ǽ�ע������
				symodel.setNsrsbh(dj.getSwdjzh()+"-"+dj.getSwjgzzjgdm());								//��˰��ʶ���	
			}
					
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}

	}
}
