/*
 * <p>Title: ������Դռ��˰-˰Դ�Ǽǲ�ѯ</p>
 * <p>Company: ��˼������</p>
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
 * <p>Title: ������Դռ��˰-˰Դ�Ǽǲ�ѯ</p>
 * <p>Description: ��ѯ˰Դ�Ǽǡ�</p>
 * @author ������ -
 * @version 1.0
 */
public class GdzysSybgProcessor  implements Processor{

	/**
	 * ���ദ����
	 */
	 public Object process(VOPackage vp) throws BaseException 
	 {
	        switch (vp.getAction()) 
	        {
	        
	        //��ѯ˰Դ�Ǽ���Ϣ
	        case GdzysCodeConstant.SMSB_GDZYS_SYBGQUERY:
	            return this.query(vp);
	            
	         //����ϸ��Ϣ   
	        case GdzysCodeConstant.SMSB_GDZYS_SYBGQUERYDETAIL:
	            return this.query(vp);
	        
	        default:
	            throw new ApplicationException(
	                "ActionType�д���processor���Ҳ�����Ӧ�ķ���.");
	        }
	    }
/*----------------------------------��ͬaction����--1��-----------------------------------------------------------------*/	
	 /**
	  * <1>��ѯ�Ǽ���Ϣ
	  */
	public Object query(VOPackage vo) throws BaseException
	{
		
		//�ǿ���֤
		if(vo==null)
		{
			throw new NullPointerException("���ݴ���δ���յ�����");
		}
		
		//���ó�ʼ����Ҫ���Եñ� ��������Ը���ѯҳ�棬������ԭ����
		GdzysSybgForm gdzysSybgForm = (GdzysSybgForm)vo.getData();
		
		//��ȡ����ռ���걨��SQL
		String sql_GDSB = getsql(gdzysSybgForm ,"sbdb.sb_jl_gdzys_sbb sbb " 							
								 +"left join  dmdb.gd_dm_sylx      sylx_dmb    on   sbb.sylxdm = sylx_dmb.sylxdm "
								 , vo); 
		System.out.println("============"+sql_GDSB);
		
		//��ȡ����ռ����ϸ��SQL
		String sql_GDSBMX = "select * " 
						   +"from 		sbdb.SB_JL_GDZYS_SBBSBMX sbmxb " 
						   +"left join  dmdb.gd_dm_zdyt          zdyt_dmb  on  sbmxb.ZDYTDM = zdyt_dmb.ZDYTDM "
						   +"where SBBXLH=?";														
		
		//��ȡ����˰��SQL
		String sql_GDJM   = "select * "
			 			   +"from       sbdb.SB_JL_GDZYS_SBBJMMX jmb "
			 			   +"left join  dmdb.gd_dm_jmslb         jmlb      on  jmb.JMSLBDM  = jmlb.JMSLBDM "
			 			   +"where SBBXLH=?";  				
		
		//���ݿ�����
		Connection con = SfDBResource.getConnection();
		
		//��ѯ���ݿⲢ����
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
		
		//��������	
		return gdzysSybgForm;
	}
	
/*----------------------------------����--���ܺ���--5---------------------------------------------------------------*/
	/**
	 * <1>��ȡ4����ѯ����SQL���������
	 * @param sydjcxForm ����4��������ѯ
	 * @param ����
	 * @return SQL
	 * @throws Exception 
	 */
	private String getsql(GdzysSybgForm gdzysSybgForm ,String table_name ,VOPackage vo) throws BaseException
	{
		//׼������ռ��˰�걨��Ҫƴ�ӵ����
		StringBuffer sql_condition = new StringBuffer();
		StringBuffer sql = new StringBuffer();
		sql.append("select * from ").append(table_name).append(" where ");
		
		//�û�Ȩ�޹���
		try{
		String datafilter = ZKAdapter.getInstance().getDataFilterString(vo.getUserData(),"SBDB", "SB_JL_GDZYS_SBB" );
		if(sql_condition.length()>0)
		{sql_condition.append(" AND ");}
			sql_condition.append(datafilter);
		}catch (Exception e) {
			throw ExceptionUtil.getBaseException(e);
		}
		
		//��ȡ��ѯ����
		String nsrmc = "";				//��˰������
		String jsjdm = "";				//���������
		String pzzdwh ="";				//ռ�����ĺ�
		String sbbxlh = "";				//�걨���к�
		
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
		
		
		//4���������ܶ�Ϊ��JS�ж�,���ж�һ�Σ�
		if(nsrmc == null && jsjdm == null && pzzdwh ==null && sbbxlh ==null)
		{
			throw ExceptionUtil.getBaseException(new Exception("400--��������һ����ѯ����"));
		}
		
		sql.append(sql_condition).append(" order by sbb.cjrq desc");
		return sql.toString();
	}
	
	/**
	 * <2>����鵽�Ľ��
	 * @param sydjcxForm--���浽��form
	 * @param ���ݿ�����
	 * @param ��ѯ��ͬ���SQL��� sql_GDSB��sql_GDSBMX��sql_GDJM��
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
			
		//��ȡ���������
		rs.last();
		int rowCount = rs.getRow();
		gdzysSybgForm.setNum(rowCount);
		rs.beforeFirst();
		
		//��Ϣ��
		List infList = new ArrayList();														//��Ϣ����
		while(rs.next())
		{
						//������Ϣ
						SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						GdzysSybgmodel gdzysSybgmodel = new GdzysSybgmodel();
						gdzysSybgmodel.setNsrmc(rs.getString("NSRMC"));								//��˰������
						gdzysSybgmodel.setJsjdm(rs.getString("JSJDM"));								//���������
						gdzysSybgmodel.setSbbxlh(rs.getString("SBBXLH"));							//�걨���к�
						gdzysSybgmodel.setNsrlx(rs.getString("NSRLX"));								//��˰������
						gdzysSybgmodel.setSfsjsp(rs.getString("SFSJSP"));							//�Ƿ��о�����
						gdzysSybgmodel.setSylx(rs.getString("SYLXMC"));								//˰Դ����	
						gdzysSybgmodel.setNsrxxdz(rs.getString("NSRXXDZ"));							//��˰����ϸ��ַ
						gdzysSybgmodel.setKhyh(rs.getString("KHYHMC"));								//��������
						gdzysSybgmodel.setYhzh(rs.getString("YHZH"));								//�����˺�
						gdzysSybgmodel.setLxrxm(rs.getString("LXRXM"));								//��ϵ������
						gdzysSybgmodel.setLxdh(rs.getString("LXDH"));								//��ϵ�绰
						gdzysSybgmodel.setSfzzlx(rs.getString("SFZZLX"));							//���֤������
						gdzysSybgmodel.setSfzzhm(rs.getString("SFZZHM"));							//���֤�պ���
						gdzysSybgmodel.setJmly(rs.getString("SBJMLY"));								//��������
						gdzysSybgmodel.setBz(rs.getString("BZMS"));									//��ע����
						gdzysSybgmodel.setSbzt(rs.getString("SBZT"));								//�걨״̬
						
						//������Ϣ
						gdzysSybgmodel.setJsmj(rs.getBigDecimal("JSMJ")==null?"0.00":String.valueOf(rs.getBigDecimal("JSMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));		//��˰���
						gdzysSybgmodel.setJzse(rs.getBigDecimal("JZSE")==null?"0.00":String.valueOf(rs.getBigDecimal("JZSE").setScale(2,BigDecimal.ROUND_HALF_UP)));    	//����˰��
						gdzysSybgmodel.setJmmj(rs.getBigDecimal("JMMJ")==null?"0.00":String.valueOf(rs.getBigDecimal("JMMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));    	//�������
						gdzysSybgmodel.setJmse(rs.getBigDecimal("JMSE")==null?"0.00":String.valueOf(rs.getBigDecimal("JMSE").setScale(2,BigDecimal.ROUND_HALF_UP)));    	//����˰��
						gdzysSybgmodel.setYsmj(rs.getBigDecimal("YSMJ")==null?"0.00":String.valueOf(rs.getBigDecimal("YSMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));   	//Ӧ˰���
						gdzysSybgmodel.setYnse(rs.getBigDecimal("YNSE")==null?"0.00":String.valueOf(rs.getBigDecimal("YNSE").setScale(2,BigDecimal.ROUND_HALF_UP)));		//Ӧ��˰��
						gdzysSybgmodel.setZdpwh (rs.getString("PZJDWH"));							    	//ռ�����ĺ�
						gdzysSybgmodel.setTdzldz(rs.getString("TDZLDZ"));							    	//���������ַ
						gdzysSybgmodel.setPzzdmj(rs.getBigDecimal("PZJDMJ")==null?"":String.valueOf(rs.getBigDecimal("PZJDMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));    //��׼ռ�����
						gdzysSybgmodel.setJsxmmc(rs.getString("JSXMMC"));							    	//������Ŀ����
						gdzysSybgmodel.setSjzdmj(rs.getBigDecimal("SJZDMJ")==null?"":String.valueOf(rs.getBigDecimal("SJZDMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));	//ʵ��ռ�����
						if(rs.getDate("ZDSJ")!=null)
						{
							gdzysSybgmodel.setZdsj(time.format(rs.getDate("ZDSJ")));						//ռ��ʱ��
						}
						
						//������Ϣ
						
						gdzysSybgmodel.setCjr(rs.getString("CJR"));														//������
						if(rs.getDate("CJRQ")!=null)
						{	
							gdzysSybgmodel.setCjsj(time.format(new Date(rs.getTimestamp("CJRQ").getTime())));										//����ʱ��
						}else
						{
							gdzysSybgmodel.setCjsj("");
						}
						if(rs.getString("QRZT")==null)
						{gdzysSybgmodel.setQrzt("0");}
						else
						{gdzysSybgmodel.setQrzt(rs.getString("QRZT"));}													//ȷ��״̬
						gdzysSybgmodel.setQrr(rs.getString("QRR"));														//ȷ����
						if(rs.getDate("QRRQ")!=null)
						{
							gdzysSybgmodel.setQrsj(time.format(new Date(rs.getTimestamp("QRRQ").getTime())));										//ȷ��ʱ��
						}else
						{
							gdzysSybgmodel.setQrsj("");
						}
						
						if(rs.getString("SJQRZT")==null)
						{gdzysSybgmodel.setSjqrzt("0");}
						else
						{gdzysSybgmodel.setSjqrzt(rs.getString("SJQRZT"));}												//�о�ȷ��״̬
						gdzysSybgmodel.setSjqrr(rs.getString("SJQRR"));													//�о�ȷ����
						if(rs.getDate("SJQRRQ")!=null)
						{
							gdzysSybgmodel.setSjqrsj(time.format(new Date(rs.getTimestamp("SJQRRQ").getTime())));								//�о�ȷ��ʱ��
						}else
						{
							gdzysSybgmodel.setSjqrsj("");
						}
			
						
						//�����Ϣ
							String sqlQueryJmxx = "select * from sbdb.sb_jl_gdzys_jmszm where sbbxlh ='"+gdzysSybgmodel.getSbbxlh()+"' AND zfbz <> '1'";
							String sqlQueryJkxx = "select * from sbdb.sb_jl_gdzys_sbbjksglb t1 left join sbdb.sb_jl_sbjkzb t2 on t1.jkpzh = t2.jkpzh where t1.sbbxlh = '"+gdzysSybgmodel.getSbbxlh()+"'";
							
							//����֤��
							PreparedStatement jmps;
							jmps = con.prepareStatement(sqlQueryJmxx);
							ResultSet rsjm = null ;
							rsjm = jmps.executeQuery();
							if(rsjm.next())
							{	
								gdzysSybgmodel.setYcjm("1");     		//�ѳ�����----��������
							}
							
							//�ɿ�֤��
							PreparedStatement jkps;
							jkps = con.prepareStatement(sqlQueryJkxx);
							ResultSet rsjk = null ;
							rsjk = jkps.executeQuery();
							while(rsjk.next())
							{
								//���ڽɿ�����Ϣ
								gdzysSybgmodel.setYcjk("1");
								
								//�����--��ʾ����˰
								if(rsjk.getString("zwbs").charAt(1)=='1')
								{
									gdzysSybgmodel.setRkbz("1");
								}
							}
			

						//ͨ���ӿ���Ϣ���
						//setInterfaceInf(gdzysSybgmodel, vo);	
						
//						//ռ����Ϣ
//						if(rowCount==1)
//						{
//							setGDSBMXResultSet(con,gdzysSybgmodel,sql_GDSBMX);		
//						}
//						
//						//������Ϣ
//						if(rowCount==1)
//						{
//							setGDJMResultSet(con ,gdzysSybgmodel ,sql_GDJM);
//						}
//						
						//һ���걨��Ϣ���
						infList.add(gdzysSybgmodel);
			
		}
		gdzysSybgForm.setInfList(infList);
		
		//�ر�
		if(rs!=null)
		{rs.close();}
		if(ps!=null)
		{ps.close();}
	}
	
	/**
	 * <3>������걨��ϸ��鵽�Ľ�� ,--<<����2
	 * @param con--���ݿ�����
	 * @param Gdzydjcxmodel--��Ϣ�洢
	 * @param sql_GDSBMX--��Ӧ��SQL
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
			gdzysSybg_sbmx.setZdyt(rs.getString("ZDYTMC"));						//ռ����;
			gdzysSybg_sbmx.setSyse(rs.getBigDecimal("SYSE")==null?"":String.valueOf(rs.getBigDecimal("SYSE").setScale(2,BigDecimal.ROUND_HALF_UP)));			//����˰��
			gdzysSybg_sbmx.setJsmj(rs.getBigDecimal("JSMJ")==null?"0.00":String.valueOf(rs.getBigDecimal("JSMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));		//��˰���
			gdzysSybg_sbmx.setJzse(rs.getBigDecimal("JZSE")==null?"0.00":String.valueOf(rs.getBigDecimal("JZSE").setScale(2,BigDecimal.ROUND_HALF_UP)));    	//����˰��
			gdzysSybg_sbmx.setJmmj(rs.getBigDecimal("JMMJ")==null?"0.00":String.valueOf(rs.getBigDecimal("JMMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));    	//�������
			gdzysSybg_sbmx.setJmse(rs.getBigDecimal("JMSE")==null?"0.00":String.valueOf(rs.getBigDecimal("JMSE").setScale(2,BigDecimal.ROUND_HALF_UP)));    	//����˰��
			gdzysSybg_sbmx.setYsmj(rs.getBigDecimal("YSMJ")==null?"0.00":String.valueOf(rs.getBigDecimal("YSMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));   		//Ӧ˰���
			gdzysSybg_sbmx.setYnse(rs.getBigDecimal("YNSE")==null?"0.00":String.valueOf(rs.getBigDecimal("YNSE").setScale(2,BigDecimal.ROUND_HALF_UP)));		//Ӧ��˰��
			
			gdzybg_sbmxList.add(gdzysSybg_sbmx);
		}
		gdzysSybgmodel.setSbmx(gdzybg_sbmxList);
		
		//�ر�
		if(rs!=null)
		{rs.close();}
		if(ps!=null)
		{ps.close();}
	}

	/**
	 * <4>������걨������ϸ��鵽�Ľ��--<<����2
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
			gdzysSybg_jm.setJmslb(rs.getString("JMSLBMC"));								//����˰������
			gdzysSybg_jm.setJmmj(rs.getBigDecimal("JMMJ")==null?"0.00":String.valueOf(rs.getBigDecimal("JMMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));    	//�������
			gdzysSybg_jm.setJmse(rs.getBigDecimal("JMSE")==null?"0.00":String.valueOf(rs.getBigDecimal("JMSE").setScale(2,BigDecimal.ROUND_HALF_UP)));    	//����˰��
			
			gdzybg_jmList.add(gdzysSybg_jm);
		}
		if(gdzybg_jmList.size()!= 0)
		{
			gdzysSybgmodel.setJmxx(gdzybg_jmList);
		};
		
		//�ر�
		if(rs!=null)
		{rs.close();}
		if(ps!=null)
		{ps.close();}
	}
	
	/**
	 * <5>��Ӵӽӿڵõ�����Ϣ
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
				gdzysSybgmodel.setNsrsshy(dj.getGjbzhymc());													//��˰��������ҵ
				gdzysSybgmodel.setQydjzclx(dj.getDjzclxmc());													//��ҵ�Ǽ�ע������
//				djxx.setSwdjzh(dj.getSwdjzh());//˰��Ǽ�֤��
//				djxx.setZzjgdm(dj.getSwjgzzjgdm());//˰�������֯��������
				gdzysSybgmodel.setNsrsbh(dj.getSwdjzh()+"-"+dj.getSwjgzzjgdm());								//��˰��ʶ���	
			}
					
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}

	}
	
}
