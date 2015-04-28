/*
 * <p>Title: ������Դռ��˰-˰Դ�Ǽǲ�ѯ</p>
 * <p>Company: ��˼������</p>
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
 * <p>Title: ������Դռ��˰-˰Դ�Ǽǲ�ѯ</p>
 * <p>Description: ��ѯ˰Դ�Ǽǡ�</p>
 * @author ������ - ��
 * @version 1.0
 */
public class GdzysShProcessor implements Processor 
{
	
	/**
	 * ���ദ����
	 */
	 public Object process(VOPackage vo) throws BaseException {
	        switch (vo.getAction()) {
	        
	        //��ѯ˰Դ�Ǽ���Ϣ�б�
	        case GdzysCodeConstant.SMSB_GDZYS_DJQuery:
	            return this.query(vo,false);
	            
	         //����ϸ��Ϣ   
//	        case GdzysCodeConstant.SMSB_GDZYS_DJQueryDetail:
//	            return this.query(vo,true);
	          //���ȷ�ϸ������ݿ���Ϣ 
//	        case GdzysCodeConstant.SMSB_GDZYS_UPDATESH:
//	        	return this.updatesh(vo);
	        default:
	            throw new ApplicationException(
	                "ActionType�д���processor���Ҳ�����Ӧ�ķ���.");
	        }
	    }
/*----------------------------------��ͬaction����--2-----------------------------------------------------------------*/	

	 
	  /**
	   * <1>��ѯ
	   */
	public Object query(VOPackage vo ,boolean detail) throws BaseException
	{
		
		//�ǿ���֤
		if(vo==null)
		{
			throw new NullPointerException("���ݴ���δ���յ�����");
		}
		
		//���ó�ʼ����Ҫ���Եñ� ��������Ը���ѯҳ�棬������ԭ����
		GdzysShqrForm gdzysShqrForm = (GdzysShqrForm)vo.getData();
		
		//��ȡ����ռ���걨��SQL
		String sql_GDSB = getsql(gdzysShqrForm ,"sbdb.sb_jl_gdzys_sbb sbb " 							
								 +"left join  dmdb.gd_dm_sylx sylx_dmb    on   sbb.sylxdm = sylx_dmb.sylxdm " , vo ); 															
		
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
		
		//��������	
		return gdzysShqrForm;
	}
	
//	/**
//	 * <2>������������������ݿ�
//	 * @param vo
//	 * @return  �ص���˲�ѯҳ��
//	 * @throws BaseException
//	 */
//	public Object updatesh(VOPackage vo ) throws BaseException
//	{
//		//�ǿ���֤
//		if(vo==null)
//		{
//			throw new NullPointerException("���ݴ���δ���յ�����");
//		}
//		
//		GdzysShqrForm gdzysShqrForm = (GdzysShqrForm)vo.getData();
//	
//		String sbxlh = gdzysShqrForm.getSbbxlh();
//		String sql = null;
//		
//		//���ݿ�����
//		Connection con = SfDBResource.getConnection();
//			
//		//�������	
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
//		//�о����
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
//		//����			
//		return gdzysShqrForm;
//	}
	
   
//	/**
//	 * <3>��ϸ��ѯ
//	 * @param vo
//	 * @return
//	 * @throws BaseException
//	 */
//	public Object queryDetail(VOPackage vo) throws BaseException 
//	{
//		//���ó�ʼ����Ҫ���Եñ� ��������Ը���ѯҳ�棬������ԭ����
//		GdzysSydjcxForm sydjcxForm = (GdzysSydjcxForm)vo.getData();
//		
//		//��ȡSQL
//		String sql_GDSBMX = "select * from SB_JL_GDZYS_SBBSBMX where SBBXLH==?";//��ȡ����ռ����ϸ��SQL
//		String sql_GDJM = "select * from SB_JL_GDZYS_SBBJMMX where SBBXLH==?";  //��ȡ����˰��SQL
//		
//		//���ݿ�����
//		Connection con = SfDBResource.getConnection();
//		
//		//��ȡmodel
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
//			throw ExceptionUtil.getBaseException(new Exception("���ݿ�洢��������")); 
//		}
//		
//		//��ѯ���ݿⲢ����
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
//		//��������	
//		return sydjcxForm;
//	}
	
	/*----------------------------------���ݿ⴦��--���ܺ���--5---------------------------------------------------------------*/
	/**
	 * <1>��ȡ4����ѯ����SQL���������
	 * @param sydjcxForm ����4��������ѯ
	 * @param ����
	 * @return SQL
	 * @throws TtsoftException 
	 * @throws Exception 
	 */
	private String getsql(GdzysShqrForm gdzysShqrForm ,String table_name ,VOPackage vo) throws BaseException
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
		
		//4���������ܶ�Ϊ��JS�ж�,���ж�һ�Σ�
		if(nsrmc == null && jsjdm == null && pzzdwh ==null && sbbxlh ==null )
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
	private void setGDSBResultSet(Connection con ,GdzysShqrForm gdzysShqrForm ,String sql_GDSB ,String sql_GDSBMX ,String sql_GDJM ,VOPackage vo ,boolean detail) 
		throws Exception
	{
		PreparedStatement ps = null;
		ps = con.prepareStatement(sql_GDSB, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		System.out.println(sql_GDSB);
		
		ResultSet rs = null ;
		rs = ps.executeQuery();
			
		//��ȡ���������
		rs.last();
		int rowCount = rs.getRow();
		//gdzysShqrForm.setNum(rowCount);            ����鵽�Ĳ�����δ��˵�
		rs.beforeFirst();
		int shnum = 0;
		
		//��Ϣ��
		List infList = new ArrayList();														//��Ϣ����
		while(rs.next())
		{
			//ֻ��ʾ��Ҫȷ�ϵ���Ϣ<1>�о�<2>����
			boolean city = ((rs.getString("SFSJSP").equals("1")) && !(rs.getString("sbzt").equals("40")));				
			boolean county = ((rs.getString("SFSJSP").equals("0")) && !(rs.getString("sbzt").equals("30")));
				
			if(city||county)
			{
					//������Ϣ
					SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");					//ʱ������
					GdzyShsbmodel gdzyShsbmodel = new GdzyShsbmodel();
					gdzyShsbmodel.setNsrmc(rs.getString("NSRMC"));								//��˰������
					gdzyShsbmodel.setJsjdm(rs.getString("JSJDM"));								//���������
					gdzyShsbmodel.setSbbxlh(rs.getString("SBBXLH"));								//�걨���к�
					gdzyShsbmodel.setNsrlx(rs.getString("NSRLX"));								//��˰������
					gdzyShsbmodel.setSfsjsp(rs.getString("SFSJSP"));							//�Ƿ��о�����
					gdzyShsbmodel.setSylx(rs.getString("SYLXMC"));								//˰Դ����	
					gdzyShsbmodel.setNsrxxdz(rs.getString("NSRXXDZ"));							//��˰����ϸ��ַ
					gdzyShsbmodel.setKhyh(rs.getString("KHYHMC"));								//��������
					gdzyShsbmodel.setYhzh(rs.getString("YHZH"));								//�����˺�
					gdzyShsbmodel.setLxrxm(rs.getString("LXRXM"));								//��ϵ������
					gdzyShsbmodel.setLxdh(rs.getString("LXDH"));								//��ϵ�绰
					gdzyShsbmodel.setSfzzlx(rs.getString("SFZZLX"));							//���֤������
					gdzyShsbmodel.setSfzzhm(rs.getString("SFZZHM"));							//���֤�պ���
					gdzyShsbmodel.setJmly(rs.getString("SBJMLY"));								//��������
					gdzyShsbmodel.setBz(rs.getString("BZMS"));									//��ע����
					gdzyShsbmodel.setSbzt(rs.getString("SBZT"));								//�걨״̬
					
					//������Ϣ
					gdzyShsbmodel.setJsmj(rs.getBigDecimal("JSMJ")==null?"0.00":String.valueOf(rs.getBigDecimal("JSMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));		//��˰���
					gdzyShsbmodel.setJzse(rs.getBigDecimal("JZSE")==null?"0.00":String.valueOf(rs.getBigDecimal("JZSE").setScale(2,BigDecimal.ROUND_HALF_UP)));    	//����˰��
					gdzyShsbmodel.setJmmj(rs.getBigDecimal("JMMJ")==null?"0.00":String.valueOf(rs.getBigDecimal("JMMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));    	//�������
					gdzyShsbmodel.setJmse(rs.getBigDecimal("JMSE")==null?"0.00":String.valueOf(rs.getBigDecimal("JMSE").setScale(2,BigDecimal.ROUND_HALF_UP)));    	//����˰��
					gdzyShsbmodel.setYsmj(rs.getBigDecimal("YSMJ")==null?"0.00":String.valueOf(rs.getBigDecimal("YSMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));   	//Ӧ˰���
					gdzyShsbmodel.setYnse(rs.getBigDecimal("YNSE")==null?"0.00":String.valueOf(rs.getBigDecimal("YNSE").setScale(2,BigDecimal.ROUND_HALF_UP)));		//Ӧ��˰��
					gdzyShsbmodel.setZdpwh (rs.getString("PZJDWH"));							    	//ռ�����ĺ�
					gdzyShsbmodel.setTdzldz(rs.getString("TDZLDZ"));							    	//���������ַ
					gdzyShsbmodel.setPzzdmj(rs.getBigDecimal("PZJDMJ")==null?"":String.valueOf(rs.getBigDecimal("PZJDMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));    //��׼ռ�����
					gdzyShsbmodel.setJsxmmc(rs.getString("JSXMMC"));							    	//������Ŀ����
					gdzyShsbmodel.setSjzdmj(rs.getBigDecimal("SJZDMJ")==null?"":String.valueOf(rs.getBigDecimal("SJZDMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));	//ʵ��ռ�����
					if(rs.getDate("ZDSJ")!=null)
					{	
						gdzyShsbmodel.setZdsj(time.format(rs.getDate("ZDSJ")));					    //ռ��ʱ��
					}
					
					//������Ϣ
					gdzyShsbmodel.setCjr(rs.getString("CJR"));														//������
					if(rs.getDate("CJRQ")!=null)
					{	
						gdzyShsbmodel.setCjsj(time.format(new Date(rs.getTimestamp("CJRQ").getTime())));										//����ʱ��
					}else
					{
						gdzyShsbmodel.setCjsj("");
					}
					
					if(rs.getString("QRZT")==null)
					{gdzyShsbmodel.setQrzt("0");}
					else
					{gdzyShsbmodel.setQrzt(rs.getString("QRZT"));}													//ȷ��״̬
					
					gdzyShsbmodel.setQrr(rs.getString("QRR"));														//ȷ����
					if(rs.getDate("QRRQ")!=null)
					{
						gdzyShsbmodel.setQrsj(time.format(new Date(rs.getTimestamp("QRRQ").getTime())));										//ȷ��ʱ��
					}else
					{
						gdzyShsbmodel.setQrsj("");
					}
					if(rs.getString("SJQRZT")==null)
					{gdzyShsbmodel.setSjqrzt("0");}
					else
					{gdzyShsbmodel.setSjqrzt(rs.getString("SJQRZT"));}												//�о�ȷ��״̬
					
					gdzyShsbmodel.setSjqrr(rs.getString("SJQRR"));													//�о�ȷ����
					if(rs.getDate("SJQRRQ")!=null)
					{
						gdzyShsbmodel.setSjqrsj(time.format(new Date(rs.getTimestamp("SJQRRQ").getTime())));									//�о�ȷ��ʱ��
					}else
					{
						gdzyShsbmodel.setSjqrsj("");
					}
					
					//ͨ���ӿ���Ϣ���
					//setInterfaceInf(gdzyShsbmodel, vo);
					
//					//ռ����Ϣ
//					if(rowCount==1 && detail)
//					{
//						
//						setGDSBMXResultSet(con,gdzyShsbmodel,sql_GDSBMX);		
//					}
//					
//					//������Ϣ				
//					if(rowCount==1 && detail)
//					{
//						setGDJMResultSet(con ,gdzyShsbmodel ,sql_GDJM);
//					}
					
					//һ���걨��Ϣ���
					infList.add(gdzyShsbmodel);
					shnum++;
			}
		}
		gdzysShqrForm.setInfList(infList);
		gdzysShqrForm.setNum(shnum);
		
		//�ر�
		if(rs!=null)
		{rs.close();}
		if(ps!=null)
		{ps.close();}
	}
/*-----------------------------------------------------------------������벻��ִ��------------------------------------------------------------------------------------------------*/	
	/**
	 * <3>������걨��ϸ��鵽�Ľ�� ,--<<����2
	 * @param con--���ݿ�����
	 * @param Gdzydjcxmodel--��Ϣ�洢
	 * @param sql_GDSBMX--��Ӧ��SQL
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
			gdzyShsbmx.setZdyt(rs.getString("ZDYTMC"));						//ռ����;
			gdzyShsbmx.setSyse(rs.getBigDecimal("SYSE")==null?"":String.valueOf(rs.getBigDecimal("SYSE").setScale(2,BigDecimal.ROUND_HALF_UP)));			//����˰��
			gdzyShsbmx.setJsmj(rs.getBigDecimal("JSMJ")==null?"0.00":String.valueOf(rs.getBigDecimal("JSMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));		//��˰���
			gdzyShsbmx.setJzse(rs.getBigDecimal("JZSE")==null?"0.00":String.valueOf(rs.getBigDecimal("JZSE").setScale(2,BigDecimal.ROUND_HALF_UP)));    	//����˰��
			gdzyShsbmx.setJmmj(rs.getBigDecimal("JMMJ")==null?"0.00":String.valueOf(rs.getBigDecimal("JMMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));    	//�������
			gdzyShsbmx.setJmse(rs.getBigDecimal("JMSE")==null?"0.00":String.valueOf(rs.getBigDecimal("JMSE").setScale(2,BigDecimal.ROUND_HALF_UP)));    	//����˰��
			gdzyShsbmx.setYsmj(rs.getBigDecimal("YSMJ")==null?"0.00":String.valueOf(rs.getBigDecimal("YSMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));   		//Ӧ˰���
			gdzyShsbmx.setYnse(rs.getBigDecimal("YNSE")==null?"0.00":String.valueOf(rs.getBigDecimal("YNSE").setScale(2,BigDecimal.ROUND_HALF_UP)));		//Ӧ��˰��
			
			gdzyShmxList.add(gdzyShsbmx);
		}
		gdzyShsbmodel.setSbmx(gdzyShmxList);
		
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
	private void setGDJMResultSet(Connection con ,GdzyShsbmodel gdzyShsbmodel ,String sql_GDJM) throws SQLException
	{
		
		PreparedStatement ps = con.prepareStatement(sql_GDJM);
		ps.setString(1, gdzyShsbmodel.getSbbxlh());
		ResultSet rs = ps.executeQuery();
		List gdzyShjmList = new ArrayList();
		while(rs.next())
		{
			GdzyShjm gdzyShjm = new GdzyShjm();
			gdzyShjm.setJmslb(rs.getString("JMSLBMC"));							//����˰������
			gdzyShjm.setJmmj(rs.getBigDecimal("JMMJ")==null?"0.00":String.valueOf(rs.getBigDecimal("JMMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));    	//�������
			gdzyShjm.setJmse(rs.getBigDecimal("JMSE")==null?"0.00":String.valueOf(rs.getBigDecimal("JMSE").setScale(2,BigDecimal.ROUND_HALF_UP)));    	//����˰��
			
			gdzyShjmList.add(gdzyShjm);
		}
		gdzyShsbmodel.setJmxx(gdzyShjmList);
		
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
	private void setInterfaceInf(GdzyShsbmodel gdzyShsbmodel ,VOPackage vo) throws ApplicationException
	{
		SWDJJBSJ dj;
		try {
			if(gdzyShsbmodel.getNsrlx().equals("0"))
			{
				UserData ud = vo.getUserData();
				dj = (SWDJJBSJ) InterfaceDj.getJBSJ(gdzyShsbmodel.getJsjdm(), ud);
			
				gdzyShsbmodel.setNsrsshy(dj.getGjbzhymc());													//��˰��������ҵ
				gdzyShsbmodel.setQydjzclx(dj.getDjzclxmc());												//��ҵ�Ǽ�ע������
//				djxx.setSwdjzh(dj.getSwdjzh());//˰��Ǽ�֤��
//				djxx.setZzjgdm(dj.getSwjgzzjgdm());//˰�������֯��������
				gdzyShsbmodel.setNsrsbh(dj.getSwdjzh()+"-"+dj.getSwjgzzjgdm());								//��˰��ʶ���			
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}

	}
	
}
