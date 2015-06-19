package com.ttsoft.bjtax.smsb.sbzl.qysdsbbjc.processor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.model.QysdsSet;
import com.ttsoft.bjtax.sfgl.common.model.Zsfs;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.sfgl.proxy.ServiceProxy;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.sbzl.qysdsbbjc.QysdsBbjcUtil2014;
import com.ttsoft.bjtax.smsb.sbzl.qysdsbbjc.SbzllxVo;
import com.ttsoft.bjtax.smsb.sbzl.qysdsbbjc.web.QysdsBbjcForm;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2012.czzssdsnb.QysdsNb2012Util;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.QysdsNewUtil;
import com.ttsoft.bjtax.smsb.util.qysdsCheck.CheckBean;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;
/**
 * <p>
 * Title: ������˰�ۺϹ���ϵͳ �걨����-����ģ��
 * </p>
 * <p>
 * Description:�˶��걨��Ϣ
 * </p>
 * 
 * @author yugw
 * @date   20140912
 */

public class QysdsBbjcProcessor implements Processor{

	public Object process(VOPackage vo) throws BaseException {
		Object result = null;
		if (vo == null) {
			throw new NullPointerException();
		}
		switch (vo.getAction()) {
		case CodeConstant.SMSB_SHOWACTION:
			result = doShow(vo);
			break;
		case CodeConstant.SMSB_QUERYACTION:
			result = doQuery(vo);
			break;
		case CodeConstant.SMSB_NEXTSTEPACTION:
				result = doNextStep(vo);
			break;
		default:
				throw new ApplicationException("�û�ִ����ϵͳ��֧�ֵķ�������.");
		}

		return result;
	}
	/**
	 * ��õǼǼ�¼�Ļ�����Ϣ 
	 * @param vo
	 * @return qysdsbbjcForm
	 * @throws BaseException
	 */
	private Object doQuery(VOPackage vo) throws BaseException {
		QysdsBbjcForm qysdsbbjcForm = (QysdsBbjcForm) vo.getData();
		qysdsbbjcForm.setNsrmc(""); // ��˰������
		UserData ud = (UserData) vo.getUserData();
		
			SWDJJBSJ djsj = null;
		try {
				// �����ҵ�Ǽǻ�����Ϣ
				djsj = InterfaceDj.getJBSJ_New(qysdsbbjcForm.getJsjdm(), ud);
			} catch (Exception ex1) {
				ex1.printStackTrace();
				throw ExceptionUtil.getBaseException(ex1);
			}
			qysdsbbjcForm.setNsrsbh(djsj.getSwdjzh()); // ��˰��ʶ���
			qysdsbbjcForm.setNsrmc(djsj.getNsrmc()); // ��˰������
			qysdsbbjcForm.setSsjjlxdm(djsj.getDjzclxdm()); // ������������-�Ǽ�ע�����ʹ���
			qysdsbbjcForm.setSsjjlxmc(djsj.getDjzclxmc());// ������������-�Ǽ�ע����������
			qysdsbbjcForm.setSshydm(djsj.getGjbzhydm());// ������ҵ����
			qysdsbbjcForm.setSshymc(djsj.getGjbzhymc());// ������ҵ����
			qysdsbbjcForm.setLrr(ud.getYhid());// ¼����
			qysdsbbjcForm.setCjr(ud.getYhid());// ������
		    System.out.println("���������++++++++++++"+qysdsbbjcForm.getJsjdm());
		    return qysdsbbjcForm;
	}
	/**
	 * ��ʼ��ҳ����Ϣ(���ش����)
	 * @param vo
	 * @return qysdsbbjcForm
	 * @throws BaseException
	 */

	private Object doShow(VOPackage vo)throws BaseException {
		QysdsBbjcForm qysdsbbjcForm = (QysdsBbjcForm) vo.getData();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List list=new ArrayList();
		try {
			conn = SfDBResource.getConnection();		
			ps = conn.prepareStatement(" select T1.SBZLLXDM,T1.SBZLLXMC,T1.BBQLX,T1.ZSFS from DMDB.QYSDS_DM_SBZLLX T1");
			rs = ps.executeQuery();
			while (rs.next()) {	
				String sbzllxdm=rs.getString("SBZLLXDM");
				String sbzllxmc=rs.getString("SBZLLXMC");
				String bbqlx=rs.getString("BBQLX");
				String zsfs=rs.getString("ZSFS");
				SbzllxVo dmvo=new SbzllxVo();
				dmvo.setDm(sbzllxdm);
				dmvo.setMc(sbzllxmc);
				dmvo.setBbqlx(bbqlx);
				dmvo.setZsfs(zsfs);
				list.add(dmvo);		
			}
			System.out.println(list.size());
			qysdsbbjcForm.setQysdssbfsList(list);
	     }catch (Exception ex) {
			try {
				throw ExceptionUtil.getBaseException(ex);
			} catch (BaseException e) {
				e.printStackTrace();
			}
		} 
		finally {	
			 try {
    			 if(ps!=null){
    				 ps.close();
    			 }
    			 if(rs!=null){
    				 rs.close();
    			 }
				} catch (SQLException e) {
					e.printStackTrace();
				}
			SfDBResource.freeConnection(conn);
		}
		return qysdsbbjcForm;
	}

	/**
	 * �����걨��Ϣʵ��ҳ�����ת
	 * @param vo
	 * @return qysdsbbjcForm
	 * @throws BaseException
	 */
	private Object doNextStep(VOPackage vo) throws BaseException {
		QysdsBbjcForm qysdsbbjcForm = (QysdsBbjcForm) vo.getData();
		QysdsBbjcUtil2014 util=new QysdsBbjcUtil2014();
		String sbzllxdm=qysdsbbjcForm.getQysdssbfsdm();
		System.out.println("++++�걨�������ʹ���"+sbzllxdm);
		String jsjdm=qysdsbbjcForm.getJsjdm();
		System.out.println("++++���������"+jsjdm);
		String sbnd=qysdsbbjcForm.getSbnd();
		System.out.println("++++�걨���"+sbnd);
		//�����ҵ����˰�걨�������͵ľ�����Ϣ
		util.getQysdssbzllx(sbzllxdm,qysdsbbjcForm);
		ServiceProxy proxy = new ServiceProxy();
		// ��ѯ˰�ѽӿ�
		QysdsSet qysdsSet = null;
		String path=null;
		String skksrq=null;
		String skjsrq=null;		
		String zsfsdm = null; 
		String jddm=qysdsbbjcForm.getJddm();
		System.out.println("++++���ȴ���"+jddm);
		String bbqlx=qysdsbbjcForm.getBbqlx();
		System.out.println("++++����������"+bbqlx);
		//���˰����������
		skksrq=util.getKsrq(sbnd,bbqlx,jddm);
		skjsrq=util.getJsrq(sbnd, jddm, bbqlx);
		System.out.println("dqnd:"+sbnd+"-"+jddm+"-"+bbqlx);
		System.out.println("skjsrq:"+skjsrq);
		Timestamp skssksrq = QysdsNewUtil.getTimestamp(skksrq);
		Timestamp skssjsrq = QysdsNewUtil.getTimestamp(skjsrq);
		qysdsbbjcForm.setSkssksrq(skksrq);
		qysdsbbjcForm.setSkssjsrq(skjsrq);
		int nd=Integer.parseInt(sbnd);
		boolean flag=false;//�ж����մ����Ƿ�ƥ��ı�־(Ĭ�ϲ�ƥ��)
		//����У��
	    CheckBean checkBean=new CheckBean();
		util.jumpCheck(nd,sbzllxdm,qysdsbbjcForm,checkBean);
		System.out.println("�걨���++++++++++++++++++++++"+nd);
		String rq=SfDateUtil.getDate();
		qysdsbbjcForm.setSbrq(SfDateUtil.getDate());
		System.out.println("�걨����++++++++++++++++++"+rq);
		qysdsbbjcForm.setSknd(sbnd);
		System.out.println("˰�����++++++++++++++++++++++"+qysdsbbjcForm.getSknd());
		// ���걨ҳ��ȡ���걨����
		Timestamp sbrq = QysdsNb2012Util.getNxetTimestamp(qysdsbbjcForm.getSkssjsrq());
		//��ò�ѯ˰�ѽӿ�
		qysdsSet=util.getQysdsSet(bbqlx, jsjdm, skssksrq, sbrq, skssjsrq, jddm, qysdsSet, proxy);
		Zsfs zsfs = qysdsSet.getZsfs();
		//������շ�ʽΪ�գ����ղ����������ж�
		if(zsfs==null){
			zsfsdm="03";
		}else{
			zsfsdm = zsfs.getZsfsdm();
		}
		System.out.println("������շ�ʽ���룺" + zsfsdm);
		String [] zsfsArr=qysdsbbjcForm.getZsfs().split("\\|");
		System.out.println("++++++++++++++qysdsbbjcForm.getZsfs()"+qysdsbbjcForm.getZsfs());
		System.out.println("++++++++++++++++���鳤��"+zsfsArr.length);
		    for(int i=0;i<zsfsArr.length;i++){
		       System.out.println("++++++++++++++++���շ�ʽ����"+zsfsArr[i]);
		       if (zsfsdm.equals(zsfsArr[i])) {
		       flag=true;
		       path = util.getPath(sbzllxdm, skksrq, skjsrq);
		       path=path+"&sbrq="+rq+"&skssksrq="+skksrq+"&skssjsrq="+skjsrq+"&sknd="+sbnd+"&jsjdm="+jsjdm;
		       qysdsbbjcForm.setPath(path);
		       System.out.println("*********ָ��·����" + path);
		       break;
		     }
		  }
		      //������շ�ʽ���벻ƥ�䣬�׳��쳣
		      if(flag==false){
			  throw new ApplicationException("���շ�ʽ���벻ƥ�䣡����");
		  }
		      System.out.println("����form:"+qysdsbbjcForm);
		      return qysdsbbjcForm;
	
  }
}