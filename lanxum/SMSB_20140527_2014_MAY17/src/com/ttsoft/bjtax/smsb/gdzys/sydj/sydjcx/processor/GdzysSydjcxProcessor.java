/*
 * <p>Title: ������Դռ��˰-˰Դ�Ǽǲ�ѯ</p>
 * <p>Company: ��˼������</p>
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
 * <p>Title: ������Դռ��˰-˰Դ�Ǽǲ�ѯ</p>
 * <p>Description: ��ѯ˰Դ�Ǽǡ�</p>
 * @author ������ - ��
 * @version 1.0
 */
public class GdzysSydjcxProcessor implements Processor 
{
	
	/**
	 * ���ദ����
	 */
	 public Object process(VOPackage vp) throws BaseException {
	        switch (vp.getAction()) {
	        
	        //��ѯ˰Դ�Ǽ���Ϣ
	        case GdzysCodeConstant.SMSB_GDZYS_DJQuery:
	            return this.query(vp);
	            
	         //����ϸ��Ϣ   
	        case GdzysCodeConstant.SMSB_GDZYS_DJQueryDetail:
	            return this.query(vp);
	        
	        default:
	            throw new ApplicationException(
	                "ActionType�д���processor���Ҳ�����Ӧ�ķ���.");
	        }
	    }
/*----------------------------------��ͬaction����--2-----------------------------------------------------------------*/	

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
		GdzysSydjcxForm sydjcxForm = (GdzysSydjcxForm)vo.getData();
		
		//��ȡ����ռ���걨��SQL
		String sql_GDSB = getsql(sydjcxForm ," sbdb.sb_jl_gdzys_sbb sbb " 							
								 			+",dmdb.gd_dm_sylx sylx_dmb "
								); 															
		
		//��ȡ����ռ����ϸ��SQL
		String sql_GDSBMX = "select * " 
						   +"from 		sbdb.SB_JL_GDZYS_SBBSBMX sbmxb     " 
						   +"    	   ,dmdb.gd_dm_zdyt          zdyt_dmb  "
						   +"where " 
						   +	" sbmxb.ZDYTDM = zdyt_dmb.ZDYTDM "
						   +	" AND SBBXLH=? "
						   +"order by xh";														
		
		//��ȡ����˰��SQL
		String sql_GDJM   = "select * "
			 			   +"from       sbdb.SB_JL_GDZYS_SBBJMMX jmb       "
			 			   +"		   ,dmdb.gd_dm_jmslb         jmlb      "
			 			   +"where " 
			 			   +	" jmb.JMSLBDM  = jmlb.JMSLBDM "
			 			   +	" AND SBBXLH=? ";  				
		
		//���ݿ�����
		Connection con = SfDBResource.getConnection();
		
		//��ѯ���ݿⲢ����
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
		
		//��������	
		return sydjcxForm;
	}
   
//	/**
//	 * <2>��ϸ��ѯ
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
	 * @throws Exception 
	 */
	private String getsql(GdzysSydjcxForm sydjcxForm ,String table_name) throws BaseException
	{
		//׼������ռ��˰�걨��Ҫƴ�ӵ����
		StringBuffer sql_condition = new StringBuffer();
		StringBuffer sql = new StringBuffer();
		sql.append("select * from ").append(table_name).append(" where ");
		sql_condition.append("sbb.sylxdm = sylx_dmb.sylxdm ");
		
		//��ȡ��ѯ����
		String nsrmc = "";				//��˰������
		String jsjdm = "";				//���������
		String pzzdwh ="";				//ռ�����ĺ�
		String sbxlh = "";				//�걨���к�
		String zsjgdm = "";				//���ջ��ش���
		
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
		
		//Ȩ��--��Ӳ�ѯ����-˰����ֻ�ܲ鱾��
		if(sydjcxForm.getSwjgdm()!=null && !(sydjcxForm.getSwjgdm().equals("")))
		{

			if(sql_condition.length()>0)
			{sql_condition.append(" AND ");}
			
			zsjgdm = sydjcxForm.getSwjgdm();
			sql_condition.append("ZSJGDM=");
			sql_condition.append("'").append(zsjgdm).append("'");
		}
		
		//4���������ܶ�Ϊ��JS�ж�,���ж�һ�Σ�
		if(nsrmc == null && jsjdm == null && pzzdwh ==null && sbxlh ==null)
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
	private void setGDSBResultSet(Connection con ,GdzysSydjcxForm sydjcxForm ,String sql_GDSB ,String sql_GDSBMX ,String sql_GDJM ,VOPackage vo) 
		throws Exception
	{
		PreparedStatement ps = null;
		ps = con.prepareStatement(sql_GDSB, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = null ;
		rs = ps.executeQuery();
			
		//��ȡ���������
		rs.last();
		int rowCount = rs.getRow();
		sydjcxForm.setNum(rowCount);
		rs.beforeFirst();
		
		//��Ϣ��
		List infList = new ArrayList();														//��Ϣ����
		while(rs.next())
		{

			
						//������Ϣ
						SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						SimpleDateFormat time2 = new SimpleDateFormat("yyyy-MM-dd");
						Gdzydjcxmodel gdzydjcxmodel = new Gdzydjcxmodel();
						gdzydjcxmodel.setNsrmc(rs.getString("NSRMC"));								//��˰������
						gdzydjcxmodel.setJsjdm(rs.getString("JSJDM"));								//���������
						gdzydjcxmodel.setSbxlh(rs.getString("SBBXLH"));								//�걨���к�
						gdzydjcxmodel.setNsrlx(rs.getString("NSRLX"));								//��˰������
						gdzydjcxmodel.setSfsjsp(rs.getString("SFSJSP"));							//�Ƿ��о�����
						gdzydjcxmodel.setSylx(rs.getString("SYLXMC"));								//˰Դ����	
						gdzydjcxmodel.setNsrxxdz(rs.getString("NSRXXDZ"));							//��˰����ϸ��ַ
						gdzydjcxmodel.setKhyh(rs.getString("KHYHMC"));								//��������
						gdzydjcxmodel.setYhzh(rs.getString("YHZH"));								//�����˺�
						gdzydjcxmodel.setLxrxm(rs.getString("LXRXM"));								//��ϵ������
						gdzydjcxmodel.setLxdh(rs.getString("LXDH"));								//��ϵ�绰
						gdzydjcxmodel.setSfzzlx(rs.getString("SFZZLX"));							//���֤������
						gdzydjcxmodel.setSfzzhm(rs.getString("SFZZHM"));							//���֤�պ���
						gdzydjcxmodel.setJmly(rs.getString("SBJMLY"));								//��������
						gdzydjcxmodel.setBz(rs.getString("BZMS"));									//��ע����
						gdzydjcxmodel.setSbzt(rs.getString("SBZT"));								//�걨״̬
						
						//������Ϣ
						gdzydjcxmodel.setJsmj(rs.getBigDecimal("JSMJ")==null?"0.00":String.valueOf(rs.getBigDecimal("JSMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));		//��˰���
						gdzydjcxmodel.setJzse(rs.getBigDecimal("JZSE")==null?"0.00":String.valueOf(rs.getBigDecimal("JZSE").setScale(2,BigDecimal.ROUND_HALF_UP)));    	//����˰��
						gdzydjcxmodel.setJmmj(rs.getBigDecimal("JMMJ")==null?"0.00":String.valueOf(rs.getBigDecimal("JMMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));    	//�������
						gdzydjcxmodel.setJmse(rs.getBigDecimal("JMSE")==null?"0.00":String.valueOf(rs.getBigDecimal("JMSE").setScale(2,BigDecimal.ROUND_HALF_UP)));    	//����˰��
						gdzydjcxmodel.setYsmj(rs.getBigDecimal("YSMJ")==null?"0.00":String.valueOf(rs.getBigDecimal("YSMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));   	//Ӧ˰���
						gdzydjcxmodel.setYnse(rs.getBigDecimal("YNSE")==null?"0.00":String.valueOf(rs.getBigDecimal("YNSE").setScale(2,BigDecimal.ROUND_HALF_UP)));		//Ӧ��˰��
						gdzydjcxmodel.setZdpwh (rs.getString("PZJDWH"));							    	//ռ�����ĺ�
						gdzydjcxmodel.setTdzldz(rs.getString("TDZLDZ"));							    	//���������ַ
						gdzydjcxmodel.setPzzdmj(rs.getBigDecimal("PZJDMJ")==null?"":String.valueOf(rs.getBigDecimal("PZJDMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));    //��׼ռ�����
						gdzydjcxmodel.setJsxmmc(rs.getString("JSXMMC"));							    	//������Ŀ����
						gdzydjcxmodel.setSjzdmj(rs.getBigDecimal("SJZDMJ")==null?"":String.valueOf(rs.getBigDecimal("SJZDMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));	//ʵ��ռ�����
						if(rs.getDate("ZDSJ")!=null)
						{					
							gdzydjcxmodel.setZdsj(time2.format(rs.getDate("ZDSJ")));					//ռ��ʱ��
						}
						
						
						//������Ϣ	
						gdzydjcxmodel.setCjr(rs.getString("CJR"));														//������
						if(rs.getDate("CJRQ")!=null)
						{	
							gdzydjcxmodel.setCjsj(time.format(new Date(rs.getTimestamp("CJRQ").getTime())));										//����ʱ��
						}else
						{
							gdzydjcxmodel.setCjsj("");
						}
						if(rs.getString("QRZT")==null)
						{gdzydjcxmodel.setQrzt("0");}
						else
						{gdzydjcxmodel.setQrzt(rs.getString("QRZT"));}													//ȷ��״̬
						gdzydjcxmodel.setQrr(rs.getString("QRR"));														//ȷ����
						if(rs.getDate("QRRQ")!=null)
						{
							gdzydjcxmodel.setQrsj(time.format(new Date(rs.getTimestamp("QRRQ").getTime())));										//ȷ��ʱ��
						}else
						{
							gdzydjcxmodel.setQrsj("");
						}
						
						if(rs.getString("SJQRZT")==null)
						{gdzydjcxmodel.setSjqrzt("0");}
						else
						{gdzydjcxmodel.setSjqrzt(rs.getString("SJQRZT"));}												//�о�ȷ��״̬
						gdzydjcxmodel.setSjqrr(rs.getString("SJQRR"));													//�о�ȷ����
						if(rs.getDate("SJQRRQ")!=null)
						{
							gdzydjcxmodel.setSjqrsj(time.format(new Date(rs.getTimestamp("SJQRRQ").getTime())));									//�о�ȷ��ʱ��
						}else
						{
							gdzydjcxmodel.setSjqrsj("");
						}
			
						
						//ͨ���ӿ���Ϣ���
						setInterfaceInf(gdzydjcxmodel, vo);
						
						//ռ����Ϣ
						if(rowCount==1)
						{
							
								setGDSBMXResultSet(con,gdzydjcxmodel,sql_GDSBMX);		
						}
						
						//������Ϣ
						if(rowCount==1)
						{
							setGDJMResultSet(con ,gdzydjcxmodel ,sql_GDJM);
						}
						
						//һ���걨��Ϣ���
						infList.add(gdzydjcxmodel);
			
		}
		sydjcxForm.setInfList(infList);
		
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
	private void setGDSBMXResultSet(Connection con ,Gdzydjcxmodel gdzydjcxmodel ,String sql_GDSBMX) throws SQLException
	{
		
		PreparedStatement ps = con.prepareStatement(sql_GDSBMX);
		ps.setString(1, gdzydjcxmodel.getSbxlh());
		ResultSet rs = ps.executeQuery();
		List gdzydj_sbmxList = new ArrayList();
		while(rs.next())
		{
			Gdzydj_sbmx gdzydj_sbmx = new Gdzydj_sbmx ();
			gdzydj_sbmx.setZdyt(rs.getString("ZDYTMC"));									//ռ����;
			gdzydj_sbmx.setSyse(rs.getBigDecimal("SYSE")==null?"":String.valueOf(rs.getBigDecimal("SYSE").setScale(2,BigDecimal.ROUND_HALF_UP)));			//����˰��
			gdzydj_sbmx.setJsmj(rs.getBigDecimal("JSMJ")==null?"0.00":String.valueOf(rs.getBigDecimal("JSMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));		//��˰���
			gdzydj_sbmx.setJzse(rs.getBigDecimal("JZSE")==null?"0.00":String.valueOf(rs.getBigDecimal("JZSE").setScale(2,BigDecimal.ROUND_HALF_UP)));    	//����˰��
			gdzydj_sbmx.setJmmj(rs.getBigDecimal("JMMJ")==null?"0.00":String.valueOf(rs.getBigDecimal("JMMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));    	//�������
			gdzydj_sbmx.setJmse(rs.getBigDecimal("JMSE")==null?"0.00":String.valueOf(rs.getBigDecimal("JMSE").setScale(2,BigDecimal.ROUND_HALF_UP)));    	//����˰��
			gdzydj_sbmx.setYsmj(rs.getBigDecimal("YSMJ")==null?"0.00":String.valueOf(rs.getBigDecimal("YSMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));   		//Ӧ˰���
			gdzydj_sbmx.setYnse(rs.getBigDecimal("YNSE")==null?"0.00":String.valueOf(rs.getBigDecimal("YNSE").setScale(2,BigDecimal.ROUND_HALF_UP)));		//Ӧ��˰��
			
			gdzydj_sbmxList.add(gdzydj_sbmx);
		}
		gdzydjcxmodel.setSbmx(gdzydj_sbmxList);
		
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
	private void setGDJMResultSet(Connection con ,Gdzydjcxmodel gdzydjcxmodel ,String sql_GDJM) throws SQLException
	{
		
		PreparedStatement ps = con.prepareStatement(sql_GDJM);
		ps.setString(1, gdzydjcxmodel.getSbxlh());
		ResultSet rs = ps.executeQuery();
		List gdzydj_sbjmList = new ArrayList();
		while(rs.next())
		{
			Gdzydj_jm gdzydj_jm = new Gdzydj_jm();
			gdzydj_jm.setJmslb(rs.getString("JMSLBMC"));									//����˰������
			gdzydj_jm.setJmmj(rs.getBigDecimal("JMMJ")==null?"0.00":String.valueOf(rs.getBigDecimal("JMMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));    	//�������
			gdzydj_jm.setJmse(rs.getBigDecimal("JMSE")==null?"0.00":String.valueOf(rs.getBigDecimal("JMSE").setScale(2,BigDecimal.ROUND_HALF_UP)));    	//����˰��
			
			gdzydj_sbjmList.add(gdzydj_jm);
		}
		if(gdzydj_sbjmList.size()!= 0)
		{
			gdzydjcxmodel.setJmxx(gdzydj_sbjmList);
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
	private void setInterfaceInf(Gdzydjcxmodel gdzydjcxmodel ,VOPackage vo) throws ApplicationException
	{
		SWDJJBSJ dj = null;
		try {
			
			UserData ud = vo.getUserData();
			if(gdzydjcxmodel.getNsrlx().equals("0"))
			{
				dj = (SWDJJBSJ) InterfaceDj.getJBSJ(gdzydjcxmodel.getJsjdm(), ud);
				gdzydjcxmodel.setNsrsshy(dj.getGjbzhymc());													//��˰��������ҵ
				gdzydjcxmodel.setQydjzclx(dj.getDjzclxmc());												//��ҵ�Ǽ�ע������
//				djxx.setSwdjzh(dj.getSwdjzh());//˰��Ǽ�֤��
//				djxx.setZzjgdm(dj.getSwjgzzjgdm());//˰�������֯��������
				gdzydjcxmodel.setNsrsbh(dj.getSwdjzh()+"-"+dj.getSwjgzzjgdm());								//��˰��ʶ���	
			}
					
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}

	}
	
}
