package com.syax.bjtax.shenbao.jmba.basx021.processor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.syax.bjtax.shenbao.jmba.dao.PublicAccess;
import com.syax.bjtax.shenbao.jmba.jmbaz.JmbaActionConstant;
import com.syax.bjtax.shenbao.jmba.jmbaz.JmbamxBo;
import com.syax.bjtax.shenbao.jmba.xmlvo.Jmba21VO;
import com.syax.bjtax.shenbao.jmba.xmlvo.JmbaVO;
import com.syax.bjtax.shenbao.jmba.xmlvo.JmbaZbVO;
import com.syax.common.util.CAcodeConstants;
import com.ttsoft.bjtax.shenbao.util.DBResource;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

public class Basx021Processor implements Processor 
{
	public Object process(VOPackage vo) throws BaseException {

		Object result = null;
		if (vo == null) {
			throw new NullPointerException();
		}

		switch (vo.getAction()) {

		case JmbaActionConstant.INTI_ACTION_QUERY:
			result = doQuery(vo);
			break;

		case JmbaActionConstant.INTI_ACTION_SAVE:
			result = doSave(vo); // ����Ϊ1
			break;
//		case JmbaActionConstant.INTI_ACTION_DELETE:
//			result = doDelete(vo);
//			break;
//		case JmbaActionConstant.INTI_ACTION_COMMIT:
//			// result = doCommit(vo, "3"); // �ύΪ2
//			break;

		default:
			throw new ApplicationException("�û�ִ����ϵͳ��֧�ֵķ�������.");
		}
		return result;
	}
	
	/**
	 * �����걨ʱ����ҳ����ѯʱ���ô˷����Ի�ȡ��ʼ������
	 *
	 * @param vo    ҵ�����
	 * @return ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
	 * @throws BaseException   ��������������׳��쳣��Ϣ
	 */
	private Object doQuery(VOPackage vo) throws BaseException {

		JmbamxBo bo = (JmbamxBo) vo.getData();			//���ݽ���������
		JmbaZbVO voZb = null;							//������¼��������Ϣ
		
		UserData ud = vo.getUserData();					//�û���Ϣ
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
				/*��ʼ��*/
				//��ǰʱ��
				Date date = new Date();
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				String tjsj = df.format(date);
				
				conn = DBResource.getConnection();
				StringBuffer sb = new StringBuffer();			//Ҫ��ѯ��sql
				List list = new ArrayList();
				
				//��ȡ�����¼
				voZb = PublicAccess.getJmbaZbVO(conn, bo.getBasqwsh());
							
				/*ҵ���߼�*/
				// �������û�м�¼��Ϣ���½�
				if (voZb.getBasqwsh() == null || "".equals(voZb.getBasqwsh())) 
				{
					voZb = new JmbaZbVO();
					voZb.setBand(bo.getBand());
					voZb.setBasqbh(bo.getBasqbh());
					voZb.setBasqwsh(bo.getBasqwsh());
					voZb.setJmbasxdm(bo.getJmbasxdm());
					voZb.setLrrq(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
					
					Jmba21VO mx21vo = new Jmba21VO();										//��ϸ��Ϣ�ĳ�ʼ��
					mx21vo.setMzqsnd(voZb.getBand());	//Ĭ�ϵ���ʼ�������Ϊ�������ǰһ��
					list.add(mx21vo);
					voZb.setQysdsjmba(list);
					return voZb;
				}
				
				//����ִ�и�sql,����ϸ��¼����Ҽ�¼��Ϣ
				sb.append("select " );
				sb.append("	        t.shbj ,t.xh ,t.mzqsnd ,t.mzzznd ");
				sb.append("from     sfdb.sf_jl_qysdsjmsba_21 t      ") ;
				sb.append("where   	");
				sb.append("       t.basqwsh='"+bo.getBasqwsh()+"' ");
				if (bo.getXh()!=null && !"".equals(bo.getXh().trim()))
				{
					sb.append(" and a.xh="+bo.getXh()+" ");
				}
				
				//��Ӳ�ѯ���
				ps = conn.prepareStatement(sb.toString());
				rs = ps.executeQuery();
				while (rs.next())
				{
					Jmba21VO mx21vo = new Jmba21VO();
					mx21vo.setShbj(rs.getString("shbj"));
					mx21vo.setMzqsnd(rs.getString("mzqsnd"));
					mx21vo.setMzzznd(rs.getString("mzzznd"));
					mx21vo.setXh(rs.getString("xh"));
					list.add(mx21vo);
				}
				voZb.setQysdsjmba(list);
				
				
				if (rs != null) {
	                rs.close();
	            }
	            if (ps != null) {
	                ps.close();
	            }
			}catch (Exception ex) {
				throw ExceptionUtil.getBaseException(ex);
			} finally {		
				DBResource.destroyConnection(conn);
			}
			  return voZb;
	}
	
	/**
	 * doSave�������ҳ����ϢҪ��
	 * 
	 * @param vo       ҵ�����
	 * @return ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
	 * @throws BaseException        ��������������׳��쳣��Ϣ
	 */
	private Object doSave(VOPackage vo) throws BaseException {
		
		List list=new ArrayList();
        
        //��ȡ�������Ϣ
		Map hm=(Map)vo.getData();
        JmbaVO bavo = (JmbaVO)hm.get(CAcodeConstants.CA_MAPKEY_VO_XMLVO); 		//������Ϣ�� 
		JmbaZbVO zbvo = (JmbaZbVO)bavo.getJmsbajl().get(0);						//���ڴ洢�ڼ�¼�������Ϣ
		Jmba21VO mxvo = (Jmba21VO)zbvo.getQysdsjmba().get(0);					//���ڴ洢�ڼ�¼��ϸ���е���Ϣ
		UserData ud = (UserData)vo.getUserData();								//�û���Ϣ
	
		//��ʼ��
		if(!mxvo.getMzzznd().equals("2015"))									//��ǰҪ��������ֹ��ȱ���Ϊ2015
		{mxvo.setMzzznd("2015");}
		
		Connection conn = null;
		PreparedStatement ps = null;
		com.syax.bjtax.shenbao.util.QysdsUtil qysdsUtil = new com.syax.bjtax.shenbao.util.QysdsUtil();
		
		try {
			conn = DBResource.getConnection();
			
			//���ݴ���ı�����������Ų鿴���ڵļ�¼���ü�¼�����ж����������Ǹ���
			JmbaZbVO zbvo2=PublicAccess.getJmbaZbVO(conn, zbvo.getBasqwsh());
			
			//�����õļ�¼�����ھ��ڼ�¼����������¼
			if (zbvo2.getBasqwsh()==null || "".equals(zbvo2.getBasqwsh())){
				PublicAccess.saveZb(conn,bavo,ud);		//�ڱ�����¼��������һ����¼
			}

			//����ϸ��¼��Ĳ���
			StringBuffer sql = new StringBuffer();
			
			//��ϸ����������ڼ�¼���͸��¼�¼
			if(mxvo.getXh()!=null && !"".equals(mxvo.getXh().trim())){
				
				sql.append("UPDATE    sfdb.sf_jl_qysdsjmsba_21  ");
				sql.append("SET 	     ");
				sql.append("   MZQSND='"+mxvo.getMzqsnd()+"'");
				sql.append("  ,MZZZND='"+mxvo.getMzzznd()+"'");
				sql.append("  ,BAND= '"+zbvo.getBand()+"'");
				sql.append("  ,SHBJ= '0' ");
				sql.append("  ,LRR='"+ud.getYhid()+"'");
				sql.append("  ,LRRQ=sysdate ");
				sql.append("where  xh='"+mxvo.getXh()+"'");
				
			}else{
				
				//�����ھ�����ϸ��¼��������
				String xh = qysdsUtil.getSequence();
				sql.append("INSERT INTO     sfdb.sf_jl_qysdsjmsba_21");
				sql.append("(XH ,BASQWSH ,JSJDM ,BAND ,SWJGZZJGDM ,MZQSND ,MZZZND ,SHBJ ,CJR ,CJRQ ,LRR ,LRRQ) "); 
				sql.append("VALUES  (");
				sql.append("    '"+xh+"'");
				sql.append("   ,'"+zbvo.getBasqwsh()+"'");
				sql.append("   ,(select  b.JSJDM      from SFDB.SF_JL_QYSDSJMSBAJL b where b.BASQWSH = '"+zbvo.getBasqwsh()+"')");
				sql.append("   ,(select  b.BAND       from SFDB.SF_JL_QYSDSJMSBAJL b where b.BASQWSH = '"+zbvo.getBasqwsh()+"')");
				sql.append("   ,(select  b.SWJGZZJGDM from SFDB.SF_JL_QYSDSJMSBAJL b where b.BASQWSH = '"+zbvo.getBasqwsh()+"')");
				sql.append("   ,'"+mxvo.getMzqsnd()+"'");
				sql.append("   ,'"+mxvo.getMzzznd()+"'");
				sql.append("   ,'0'");
				sql.append("   ,'"+ud.getYhid()+"'");
				sql.append("   ,sysdate");
				sql.append("   ,'"+ud.getYhid()+"'");
				sql.append("   ,sysdate)");
				
			}
			ps = conn.prepareStatement(sql.toString());
			ps.executeQuery();
			
			
			if (ps != null) {
				ps.close();
			}
		} catch (Exception ex) {
			throw ExceptionUtil.getBaseException(ex);
		} finally {		
			DBResource.destroyConnection(conn);
		}
		return list;
	}
}
