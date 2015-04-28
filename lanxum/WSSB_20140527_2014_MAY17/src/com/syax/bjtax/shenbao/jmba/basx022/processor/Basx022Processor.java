/*
 * Created on 2010-1-5
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.syax.bjtax.shenbao.jmba.basx022.processor;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.syax.bjtax.ca.util.DzyjHelper;
import com.syax.bjtax.ca.vo.DzyjsjVO;
import com.syax.bjtax.shenbao.jmba.dao.PublicAccess;
import com.syax.bjtax.shenbao.jmba.jmbaz.JmbaActionConstant;
import com.syax.bjtax.shenbao.jmba.jmbaz.JmbamxBo;
import com.syax.bjtax.shenbao.jmba.xmlvo.Jmba022VO;
import com.syax.bjtax.shenbao.jmba.xmlvo.JmbaVO;
import com.syax.bjtax.shenbao.jmba.xmlvo.JmbaZbVO;
import com.syax.bjtax.shenbao.jmba.xmlvo.VOConstants;
import com.syax.common.util.CAcodeConstants;
import com.ttsoft.bjtax.shenbao.util.DBResource;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * ���ܷ���˾ʵʩ��ͬ��Դ������Ŀ�����ñ�������
 * ��Ŀ���ƣ�wssb   
 * �����ƣ�Basx022Processor   
 * ��������   
 * �����ˣ�wangcy 
 * ����ʱ�䣺2014-2-13 ����5:14:45   
 * �޸��ˣ�wangcy   
 * �޸�ʱ�䣺2014-2-13 ����5:14:45   
 * �޸ı�ע��   
 * @version  1.0
 */
public class Basx022Processor  implements Processor {
	/**
	 * ʵ��Processor�ӿ�
	 *
	 * @param vo
	 *            ҵ�����
	 * @return Object VOPackage������
	 * @throws BaseException
	 *             ҵ���쳣 1 ���������Ĳ������Ͳ���ʱ�׳� 2 �����õ�ҵ�񷽷��׳�ҵ���쳣ʱ���ϴ����׳�
	 *             �����쳣�׳���EJB��process��������
	 */

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
			result = doSave(vo);//����Ϊ1
			break;
		case JmbaActionConstant.INTI_ACTION_DELETE:
			result = doDelete(vo);
			break;
		case JmbaActionConstant.INTI_ACTION_COMMIT:
			result = doCommit(vo,"3");//�ύΪ2
			break;

		default:
			throw new ApplicationException("�û�ִ����ϵͳ��֧�ֵķ�������.");
		}

		return result;
	}

	/**
	 * doCommit�������ҳ����ϢҪ��
	 *
	 * @param vo
	 *            ҵ�����
	 * @return ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
	 * @throws BaseException
	 *             ��������������׳��쳣��Ϣ
	 */

	private Object doCommit(VOPackage vo,String sqzt) throws BaseException {
//sqzt ������1���ύ��2
        DzyjHelper dh = new DzyjHelper();
		Map hm=(Map)vo.getData();
        JmbaVO bavo = (JmbaVO)hm.get(CAcodeConstants.CA_MAPKEY_VO_XMLVO);
		DzyjsjVO dzyj = (DzyjsjVO) hm.get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);
		UserData ud = (UserData) vo.getUserData();
		JmbaZbVO vo1 = (JmbaZbVO)bavo.getJmsbajl().get(0);
		com.syax.bjtax.shenbao.util.QysdsUtil qysdsUtil = new com.syax.bjtax.shenbao.util.QysdsUtil();
		try {
//			����ԭ���ݲ�ʵ��
			/*
    		try
	        {
	       	 dzyj = CAUtils.saveDzyjsj(ud,dzyj, "0", "0", "0", vo1.getBasqwsh()+VOConstants.QYSDSJMBA_SXDM01);
	       }catch (Exception ex)
	        {
	            ex.printStackTrace();
	            throw ExceptionUtil.getBaseException(ex);
	        }
	       hm.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
*/

			//��������״̬Ϊ���ͨ�������δͨ��
			qysdsUtil.updateSqzt(vo1.getBasqwsh(), sqzt, ud.getYhid());


		} catch (Exception ex) {
			throw ExceptionUtil.getBaseException(ex);
		} finally {

		}
		return null;
	}

	/**
	 * doSave�������ҳ����ϢҪ��
	 *
	 * @param vo
	 *            ҵ�����
	 * @return ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
	 * @throws BaseException
	 *             ��������������׳��쳣��Ϣ
	 */

	private Object doSave(VOPackage vo) throws BaseException {
//sqzt ������1���ύ��2
		List list=new ArrayList();
        DzyjHelper dh = new DzyjHelper();
		Map hm=(Map)vo.getData();
        JmbaVO bavo = (JmbaVO)hm.get(CAcodeConstants.CA_MAPKEY_VO_XMLVO);
		DzyjsjVO dzyj = (DzyjsjVO) hm.get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);
		JmbaZbVO vo1 = (JmbaZbVO)bavo.getJmsbajl().get(0);

		Jmba022VO mxvo=(Jmba022VO)vo1.getQysdsjmba().get(0);
		UserData ud = (UserData)vo.getUserData();
		Connection conn = null;
		PreparedStatement ps = null;
		com.syax.bjtax.shenbao.util.QysdsUtil qysdsUtil = new com.syax.bjtax.shenbao.util.QysdsUtil();
		try {
			//����ԭ���ݲ�ʵ��
			/*
			try
	        {
	       	 dzyj = CAUtils.saveDzyjsj(ud,dzyj, "0", "0", "0", vo1.getBasqwsh()+VOConstants.QYSDSJMBA_SXDM01);
	       }catch (Exception ex)
	        {
	            ex.printStackTrace();
	            throw ExceptionUtil.getBaseException(ex);
	        }
	       hm.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
		   */
			conn = DBResource.getConnection();
			JmbaZbVO vo2=PublicAccess.getJmbaZbVO(conn, vo1.getBasqwsh());
			//����
			if (vo2.getBasqwsh()==null || "".equals(vo2.getBasqwsh())){
				PublicAccess.saveZb(conn,bavo,ud);
			}

			String sql = "";
			if(mxvo.getXh()!=null && !mxvo.getXh().equals("")){
				sql = "UPDATE sfdb.sf_jl_qysdsjmsba_22 SET JNJPJSGZXMDM='"+mxvo.getJnjpjsgzxmdm()+"',BAND='"+vo1.getBand()+"',DYBZLMC='"+mxvo.getDybzlmc()+"',DYBRQ='"+mxvo.getDybrq()+"',MZQSND='"+mxvo.getMzqsnd()+"',MZZZND='"+mxvo.getMzzznd()+"',JBZSQSND='"+mxvo.getJbzsqsnd()+"',JBZSZZND='"+mxvo.getJbzszznd()+
					  "',ZRHTXM='"+mxvo.getZRHTXM()+"', ZRHTXMYH='"+mxvo.getZRHTXMYH()+"',ZRHTXMYHWJ='"+mxvo.getZRHTXMYHWJ()+
					  "',SHBJ='0',LRR='"+ud.getYhid()+
					  "',LRRQ=sysdate where xh ='"+mxvo.getXh()+"'";
			}else{
				String xh = qysdsUtil.getSequence();
				sql = "INSERT INTO sfdb.sf_jl_qysdsjmsba_22 (XH,BASQWSH,JNJPJSGZXMDM,JSJDM,BAND,SWJGZZJGDM,DYBZLMC,DYBRQ,MZQSND,MZZZND,JBZSQSND,JBZSZZND,SHBJ,CJR,CJRQ,LRR,LRRQ,ZRHTXM,ZRHTXMYH,ZRHTXMYHWJ) " +
						"VALUES('"+xh+"','"+vo1.getBasqwsh()+"','"+mxvo.getJnjpjsgzxmdm()+"',(select b.JSJDM from SFDB.SF_JL_QYSDSJMSBAJL b where B.BASQWSH = '"+vo1.getBasqwsh()+"'),(select b.BAND from SFDB.SF_JL_QYSDSJMSBAJL b where B.BASQWSH = '"+vo1.getBasqwsh()+"'),(select b.SWJGZZJGDM from SFDB.SF_JL_QYSDSJMSBAJL b where B.BASQWSH = '"+vo1.getBasqwsh()+"'),'"+mxvo.getDybzlmc()+"','"+mxvo.getDybrq()+"','"+mxvo.getMzqsnd()+"','"+mxvo.getMzzznd()+"','"+mxvo.getJbzsqsnd()+"','"+mxvo.getJbzszznd()+"','0','"+ud.getYhid()+"',sysdate,'"+
						ud.getYhid()+"',sysdate,'"+mxvo.getZRHTXM()+"','"+mxvo.getZRHTXMYH()+"','"+mxvo.getZRHTXMYHWJ()+"')";
			}
			System.out.println("022DetailProcessor===doSave===sql="+sql);
			ps = conn.prepareStatement(sql);
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

	/**
	 * doDeleteɾ������ҳ����ϢҪ��
	 *
	 * @param vo
	 *            ҵ�����
	 * @return ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
	 * @throws BaseException
	 *             ��������������׳��쳣��Ϣ
	 */

	private Object doDelete(VOPackage vo) throws BaseException {

        DzyjHelper dh = new DzyjHelper();
		Map hm=(Map)vo.getData();
		JmbamxBo bo=(JmbamxBo)hm.get(VOConstants.KEY_JMBA_MX_BO);
        JmbaVO bavo = (JmbaVO)hm.get(CAcodeConstants.CA_MAPKEY_VO_XMLVO);
		DzyjsjVO dzyj = (DzyjsjVO) hm.get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);
		JmbaZbVO vo1 = (JmbaZbVO)bavo.getJmsbajl().get(0);
		//Jmba01VO mxvo=(Jmba01VO)vo1.getQysdsjmba().get(0);
		UserData ud = (UserData) vo.getUserData();
		Connection conn = null;
		PreparedStatement ps = null;
		try {
//����ԭ���ݲ�ʵ��
			/*
    		try
	        {
	       	 dzyj = CAUtils.saveDzyjsj(ud,dzyj, "0", "0", "0", vo1.getBasqwsh()+VOConstants.QYSDSJMBA_SXDM01);
	       }catch (Exception ex)
	        {
	            ex.printStackTrace();
	            throw ExceptionUtil.getBaseException(ex);
	        }
	       hm.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
*/
			conn = DBResource.getConnection();
			String delSQL = "DELETE FROM sfdb.sf_jl_qysdsjmsba_22 t WHERE t.xh = '"+bo.getXh()+"'";
			System.out.println("delSQL==="+delSQL);
			ps = conn.prepareStatement(delSQL);
			ps.executeUpdate();
			if (ps != null) {
				ps.close();
			}


		} catch (Exception ex) {
			throw new ApplicationException("���ݿ���¼�¼ʧ�ܣ�" + ud.getYhid()	+ ":" + ex.getMessage());
		} finally {
			DBResource.destroyConnection(conn);
		}
		return null;
	}
	/**
	 * doShow��ʼ������ҳ����ϢҪ��
	 *
	 * @param vo
	 *            ҵ�����
	 * @return ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
	 * @throws BaseException
	 *             ��������������׳��쳣��Ϣ
	 */

	private Object doQuery(VOPackage vo) throws BaseException {

		JmbamxBo bo = (JmbamxBo) vo.getData();
		JmbaZbVO vo1=null;
		UserData ud = vo.getUserData();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = DBResource.getConnection();
			StringBuffer sb=new StringBuffer();
			vo1=PublicAccess.getJmbaZbVO(conn, bo.getBasqwsh());
			//����
			if (vo1.getBasqwsh()==null || "".equals(vo1.getBasqwsh())){
				vo1=new JmbaZbVO();
				vo1.setBand(bo.getBand());
				vo1.setBasqbh(bo.getBasqbh());
				vo1.setBasqwsh(bo.getBasqwsh());
				vo1.setJmbasxdm(bo.getJmbasxdm());
				vo1.setLrrq(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
				vo1.setJmbasxmc("���ܷ���˾ʵʩ��ͬ��Դ������Ŀ�����ñ�������");
				Jmba022VO mxvo=new Jmba022VO();
				mxvo.setHc("1");
				List list=new ArrayList();
				list.add(mxvo);
				vo1.setQysdsjmba(list);
				System.out.println("*********************");
				return vo1;
			}

			Date date = new Date();
			SimpleDateFormat df=new SimpleDateFormat("yyyyMMdd");
			String tjsj = df.format(date);
			//decode(a.sfyhssm,'0','��','1','��','') sfyhssmmc ,a.qtzl qtzl,a.zcba,a.sfyhssm,
			sb.append(" select ");
            sb.append(" a.JNJPJSGZXMDM,(select b.JNJPJSGZXMMC from dmdb.SF_DM_JNJPJSGZXM b where a.JNJPJSGZXMDM=b.JNJPJSGZXMDM ) JNJPJSGZXMMC, ");
			sb.append(" a.dybzlmc dybzlmc,a.dybrq dybrq,");
            sb.append(" a.xh, ");
            sb.append(" a.mzqsnd mzqsnd,a.mzzznd mzzznd,a.jbzsqsnd jbzsqsnd,a.jbzszznd jbzszznd ");
            sb.append(" ,ZRHTXM,ZRHTXMYH,ZRHTXMYHWJ ");
			sb.append(" from sfdb.sf_jl_qysdsjmsba_22 a ");
			sb.append(" where 1=1 ");
			//if ("9".equals(bo.getZtdm())){
			if (bo.getBasqwsh()!=null && !bo.getBasqwsh().equals("")){
				sb.append(" and a.basqwsh="+bo.getBasqwsh()+" ");
			}
			//}

			if (bo.getXh()!=null && !bo.getXh().equals("")){
				sb.append(" and a.xh="+bo.getXh()+" ");
			}

			System.out.println("===query sql=="+sb.toString());

			List list=new ArrayList();
			ps = conn.prepareStatement(sb.toString());
			rs = ps.executeQuery();
			int i = 0;
			while (rs.next()) {
				 Jmba022VO mxvo=new Jmba022VO();
				 i++;
				 mxvo.setHc(i+"");
	             mxvo.setXh( rs.getString("xh"));
	             mxvo.setJnjpjsgzxmdm( rs.getString("JNJPJSGZXMDM"));
	             mxvo.setJnjpjsgzxmmc( rs.getString("JNJPJSGZXMMC"));
	             mxvo.setDybrq(rs.getString("Dybrq"));//StringUtils.getStrFromDate(
	             mxvo.setDybzlmc( rs.getString("Dybzlmc"));
	             mxvo.setJbzsqsnd( rs.getString("Jbzsqsnd"));
	             mxvo.setJbzszznd( rs.getString("Jbzszznd"));
	             mxvo.setMzqsnd( rs.getString("Mzqsnd"));
	             mxvo.setMzzznd( rs.getString("Mzzznd"));
	             mxvo.setZRHTXM(rs.getString("ZRHTXM"));
	             mxvo.setZRHTXMYH(rs.getString("ZRHTXMYH"));
	             mxvo.setZRHTXMYHWJ(rs.getString("ZRHTXMYHWJ"));
//	             mxvo.setZcba( rs.getString("Zcba"));
//	             if ("2".equals(bo.getZtdm())){
//	             	mxvo.setZcba("0");
//	             }else{
//	             	mxvo.setZcba("1");
//	             }
//	             mxvo.setQtzl( rs.getString("Qtzl"));
//	             mxvo.setSfyhssm( rs.getString("Sfyhssm"));
//	             if (mxvo.getSfyhssm().equals("0")){
//	                   mxvo.setSfyhssmmc( "��");
//	             }else{
//	             	mxvo.setSfyhssmmc( "��");
//	             }

	             list.add(mxvo);
			}
			vo1.setQysdsjmba(list);
			System.out.println("query mx done");
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
		} catch (Exception ex) {
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			DBResource.destroyConnection(conn);
		}
		return vo1;
	}
}
