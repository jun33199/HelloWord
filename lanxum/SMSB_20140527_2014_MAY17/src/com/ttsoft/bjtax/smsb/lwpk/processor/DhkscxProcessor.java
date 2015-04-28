package com.ttsoft.bjtax.smsb.lwpk.processor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.lwpk.model.PKJBSJModel;
import com.ttsoft.bjtax.smsb.lwpk.model.PLKKDHCXModel;
import com.ttsoft.bjtax.smsb.lwpk.web.DhkscxForm;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.SystemException;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 *������ѯprocessor
 *kanght 
 * 201307
 */
public class DhkscxProcessor implements Processor{

	public Object process(VOPackage vo) throws BaseException {
		
		 Object result = null;
		 if (vo == null) {
		      throw new NullPointerException();
		    }

		    switch (vo.getAction()) {
		      case CodeConstant.SMSB_QUERYACTION:
		        result = doQuery(vo);
		        break;
		      default:
		        throw new UnsupportedOperationException(
		            "Method process() not yet implemented.");
		    }
		    return result;
	}

	private Object doQuery(VOPackage vo) throws BaseException{
		//��ȡform
		DhkscxForm form = (DhkscxForm) vo.getData();
		//��������model
		PKJBSJModel jbsjModel = new PKJBSJModel();
		//��ȡjsjdm
		String jsjdm = form.getJsjdm();
		//�������ݿ�
		 PreparedStatement ps = null;
	     Connection conn = null;
	     //����� 
	     ResultSet rst = null;
	     //��ȡ��������sql
	     String sql = "select a.jsjdm, a.nsrmc, b.xm, b.gddh, b.yddh, a.zcdz, a.zcdzlxdh, a.jydz, a.jydzlxdm " +
	     		"from djdb.dj_jl_jbsj a, djdb.dj_jl_qyry b where a.jsjdm = b.jsjdm and b.zwdm = '01' " +
	     		"and a.jsjdm=?";
	     try {
	    	 //��ȡ����
			conn = SfDBResource.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, jsjdm);
		    //��ѯ
		    rst = ps.executeQuery();
		    if(rst.next()){
		    	//�̶��绰
		    	jbsjModel.setGddh(rst.getString("gddh"));
		    	//��˰������
		    	jbsjModel.setNsrmc(rst.getString("nsrmc"));
		    	//�ƶ��绰
		    	jbsjModel.setYddh(rst.getString("yddh"));
		    	//ע���ַ
		    	jbsjModel.setZcdz(rst.getString("zcdz"));
		    	//��Ӫ��ַ
		    	jbsjModel.setJydz(rst.getString("jydz"));
		    	//����������--������
		    	jbsjModel.setZrr(rst.getString("xm"));
		    	//��������
			    form.setPkjbsjModel(jbsjModel);
		    }else{
		    	throw new ApplicationException("��˰�˻�����ϢΪ�գ�");	
		    }
		    //�رս����
		    rst.close();
		    //�ر�preparestatement
		    ps.close();
		  
		    
		    StringBuffer sql_zts = new StringBuffer();
		    sql_zts.append(" select a.sphm, b.szdm, a.kkrq, a.yhkkjgms, a.kkbz, sum(b.sjje) sjje");
		    sql_zts.append(" from sbdb.sb_jl_plkkspxx_lw a, sbdb.sb_jl_plkkspzb_lw b");
		    sql_zts.append(" where a.sphm = b.sphm");
		    sql_zts.append(" and a.jsjdm = ?");
		    sql_zts.append(" and a.nd = ?");
		    sql_zts.append(" and a.yd >= ?");
		    sql_zts.append(" and a.yd <= ?");
		    sql_zts.append(" group by a.sphm, b.szdm, a.kkrq, a.yhkkjgms,a.kkbz");
		    //������
		    int zts=0;
		    //ִ�в�ѯ
		    ps = conn.prepareStatement(sql_zts.toString());
		    //���������
		    ps.setString(1, jsjdm);
		    //���
		    ps.setString(2, form.getNd());
		    //�ۿ���ʼ�·�
		    ps.setString(3, form.getKkqsyf());
		     //�ۿ���ֹ�·�
		    ps.setString(4, form.getKkzzyf());
		    
		    rst = ps.executeQuery();
		    while(rst.next()){
		    	//������
		    	zts++;
		    }
		    //�ر�resultset
		    rst.close();
		    //�ر�preparestatement
		    ps.close();
		    //���������Ϊ0
		    if(zts<=0){
		    	throw new ApplicationException("δ�鵽���������Ŀۿ���Ϣ��");			
		    }
		    //������ҳ��
		    form.setTotalpage(String.valueOf(gettotalpage(zts)).trim());
		    //����������
		    form.setZts(zts);
		  //��ʼ���ݵ�id
			int start = (Integer.parseInt(form.getNextPage())-1)*Integer.parseInt(form.getPageSize());
			//�������ݵ�id
			int end =(Integer.parseInt(form.getNextPage()))*Integer.parseInt(form.getPageSize());
		    
			StringBuffer buffer = new StringBuffer();
			buffer.append("select * from (");
			buffer.append(" select a.sphm, b.szdm, a.kkrq, a.yhkkjgms, a.kkbz, sum(b.sjje) sjje, rownum num ");
			buffer.append(" from sbdb.sb_jl_plkkspxx_lw a, sbdb.sb_jl_plkkspzb_lw b ");
			buffer.append(" where a.sphm = b.sphm ");
			buffer.append(" and a.jsjdm = ?");
			buffer.append(" and a.nd = ?");
			buffer.append(" and a.yd >= ?");
			buffer.append(" and a.yd <= ?");
			buffer.append(" group by a.sphm, b.szdm, a.kkrq, a.yhkkjgms,a.kkbz,rownum) dh");
			buffer.append(" where dh.num>? and dh.num <=?");
		
		    //������ѯ��˰��Ϣ
		    ps = conn.prepareStatement(buffer.toString());
		    //���������
		    ps.setString(1, jsjdm);
		    //���
		    ps.setString(2, form.getNd());
		    //�ۿ���ʼ�·�
		    ps.setString(3, form.getKkqsyf());
		    //�ۿ���ֹ�·�
		    ps.setString(4, form.getKkzzyf());
		    //��ѯ��ʼ����
		    ps.setInt(5, start);
		    //��ѯ��ֹ����
		    ps.setInt(6, end);
		    
		    //��ȡ�ۿ��ʾ����map
		    Map kkbzmcMap = getkkbzmc();
		    //��ȡ˰������map
		    Map szmcMap = getszmc();
		    //ִ�в�ѯ
		    rst = ps.executeQuery();
		    List list = new ArrayList();
		    while(rst.next()){
		    	 PLKKDHCXModel plkkdhcxModel = new PLKKDHCXModel();
		    	 //�ۿ�ʧ��ԭ��
		    	 plkkdhcxModel.setKksbyy(rst.getString("yhkkjgms"));
		    	 //�ۿ�ʱ��
		    	 plkkdhcxModel.setKksj(rst.getDate("kkrq"));
		    	 //�ۿ�״̬
		    	 String kkbz = rst.getString("kkbz");
		    	 plkkdhcxModel.setKkzt((String)kkbzmcMap.get(kkbz.trim()));
		    	 //ʵ�ɽ��
		    	 plkkdhcxModel.setSjje(rst.getBigDecimal("sjje"));
		    	 //˰Ʊ����
		    	 plkkdhcxModel.setSphm(rst.getString("sphm"));
		    	 //˰������
		    	 plkkdhcxModel.setSzmc((String) szmcMap.get(rst.getString("szdm")));
		    	 //����ʾ���ݶ������list
		    	 list.add(plkkdhcxModel);
		    }
		    //�رս����
		    rst.close();
		    ps.close();
		   //���õ�form��--��ǰҳҪ��ʾ������
		    form.setPlkkdhcxlist(list);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
   	     SfDBResource.freeConnection(conn);
	       }
	 
		return form;
	}
	
	/**
	 *�ۿ��ʶ ����ת��������
	 * 
	 */
	public Map getkkbzmc(){
		Map map = new HashMap();
		map.put("00", "δ�ۿ�");
		map.put("10", "�ۿ���");
		map.put("20", "�ۿ�ɹ�");
		map.put("21", "�ۿ�ʧ��");
		return map;
	}
	
	/**
	 *˰�����������ת�� 
	 * 
	 */
	public Map getszmc(){
		Map map = new HashMap();
		 PreparedStatement ps = null;
	     Connection conn = null;
	     //��ȡ��������sql
	     String sql = "SELECT SZSMDM,SZSMMC FROM DMDB.SB_DM_SZSM";
	    	 //��ȡ����
			try {
				conn = SfDBResource.getConnection();
				ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
					map.put(rs.getString("SZSMDM"), rs.getString("SZSMMC"));
				}
				
			} catch (SystemException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		   
		    
			return map;
	}

	
	/**
	 *��ȡ��ҳ�� 
	 * 
	 * ts ���������
	 */
	public int gettotalpage(int ts ){
		//��ҳ��
		int totalpage = 0;
		//��������ܹ�������
		if(ts % CodeConstant.SMSB_PK_PG_LENGTH == 0){
			//��ҳ�� = ����/ÿҳ��ʾ������ 
			totalpage = ts / CodeConstant.SMSB_PK_PG_LENGTH ;
			//����������ܱ�����
		}else{
			//��ҳ�� = ����/ÿҳ��ʾ������ + 1
			totalpage = ts / CodeConstant.SMSB_PK_PG_LENGTH + 1;
		}
		return totalpage;
	}
	

	
	
}
