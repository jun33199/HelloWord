package com.creationstar.bjtax.qsgl.BizLogic.processor.clfgl;

import java.sql.Connection;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.DAOFactory;
import com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl.Clfjycs;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.clfgl.ClfqxwhBo;
import com.creationstar.bjtax.qsgl.util.QSDBUtil;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.Debug;
import com.ttsoft.common.util.ZKAdapter;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

public class ClfqxwhProcessor implements Processor 
{
	public Object process(VOPackage vo) throws BaseException {
		Object result = null;

		if (vo == null) {
			throw new NullPointerException();
		}

		switch (vo.getAction()) 
		{

		case ActionType.QUERY:
			result = getQxwh(vo); // ���Ȩ��ά����Ϣ
			break;	
		case ActionType.UPDATE:
			result = updateQxwh(vo); // �޸�Ȩ��ά����Ϣ
			break;	
		case ActionType.DELETE:
			result = deleteQxwh(vo); // ɾ��Ȩ��ά����Ϣ	
			break;	
		
		default:
			throw new ApplicationException("ActionType�д���processor���Ҳ�����Ӧ�ķ���.");
		}

		return result;
	}

	/**
     * �������޸�Ȩ��ά���Ĳ�ѯ
     *
     * @param vo VOPackage
     * @return Object ҵ�����
     * @throws BaseException �����׳����쳣
     */
	private Object getQxwh(VOPackage vo) throws BaseException
	{
		Debug.out("--Debug-- Info : Here is ClfqxwhProcessor.doQuery(vo)");
		Connection conn = null;
        
        ClfqxwhBo bo = (ClfqxwhBo) vo.getData();
        UserData ud = vo.getUserData();
        
        try 
		{	
			// ��ȡ��������
			conn = QSDBUtil.getConnection();
			
			
			// ��������Ȩ�޿���
	        String datafilter = ZKAdapter.getInstance().getDataFilterString(ud,"DMDB", "QS_DM_CLFJYCSB" );
	        Debug.out("datafilter: " + datafilter);
	        
			
			//��1����ȡ��ѯ���
			String conditions = " where cslx ='02' and "+datafilter;
			
            try
			{
				
				bo.setJycsList(DAOFactory.getInstance().getClfjycsDAO().queryCsList(conn, conditions));
				System.out.println(bo.getJycsList().size());
				Debug.out("��Ʊ��Ʊ������ѯ��Ʊ��Ʊ��Ϣ�ɹ�....");
			}
			catch (Exception ex) 
			{
				Debug.out(ex);
				throw new ApplicationException("��Ʊ��Ʊ������ѯ��Ʊ��Ʊ��Ϣ����");
			}
			
		}
		catch (Exception ex) 
		{
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		}finally {
           QSDBUtil.freeConnection(conn);
       }	
		return bo;
	}
	
	
	/**
     * �������޸�Ȩ��ά����ɾ��
     *
     * @param vo VOPackage
     * @return Object ҵ�����
     * @throws BaseException �����׳����쳣
     */
	private Object deleteQxwh(VOPackage vo) throws BaseException{
		Connection conn = null;
		try{
			// ��ȡ��������
			conn = QSDBUtil.getConnection();
			
			//���ǰ̨�ύ������
			ClfqxwhBo bo = (ClfqxwhBo) vo.getData();
			
			String dyz = bo.getDyz();
			if(dyz == null || "".equals(dyz)){
				throw new ApplicationException("ɾ�����������ԣ����ߺ͹���Ա��ϵ��");
			}
			
			
		//ִ�б���
		DAOFactory.getInstance().getClfjycsDAO().delete(conn, dyz);
				
		}catch(Exception e){
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);			
		}finally {
	           QSDBUtil.freeConnection(conn);
	       }
		return this.getQxwh(vo);
	}

	
	/**
     * �������޸�Ȩ��ά�����޸�
     *
     * @param vo VOPackage
     * @return Object ҵ�����
     * @throws BaseException �����׳����쳣
     */
	private Object updateQxwh(VOPackage vo) throws BaseException{
		//���ǰ̨�ύ������
		ClfqxwhBo bo = (ClfqxwhBo) vo.getData();
		
		//����������Ϣ
		String allNewAddInfo = bo.getAllNewAddInfo();
		if(allNewAddInfo != null && !"".equals(allNewAddInfo)){
			this.SaveNewAdd(vo);
		}
		
		
		
		return null;
	}
	
	
	/**
	 * ����������Ա��Ϣ
	 * @param vo
	 * @return
	 * @throws BaseException
	 */
	private boolean SaveNewAdd(VOPackage vo) throws BaseException{
		Connection conn = null;
		UserData ud = vo.getUserData();
		try{
			String isExistStr = "";
			
			
			// ��ȡ��������
			conn = QSDBUtil.getConnection();
			
			//���ǰ̨�ύ������
			ClfqxwhBo bo = (ClfqxwhBo) vo.getData();
			
			
			String allNewAddInfo = bo.getAllNewAddInfo();
			
			if(allNewAddInfo == null || "".equals(allNewAddInfo)){
				throw new ApplicationException("����������ԱȨ����Ϣ�����޷�����ύ���ݣ������ԣ����ߺ͹���Ա��ϵ��");
			}
			
			//�����ύ��Ϣ
			ArrayList sellerList = new ArrayList();
			String regEx ="[\\^]";//���������^�ֿ�
			String[] splitAllNewAddInfo = allNewAddInfo.split(regEx);

			for (int index = 0; index < splitAllNewAddInfo.length; index++) {

				String[] oneSellerInfoArr = new String[] {};
				if (splitAllNewAddInfo[index] != null) {
					System.out.println("������Ա��Ϣ����"+splitAllNewAddInfo[index]);

					oneSellerInfoArr = splitAllNewAddInfo[index].split("~");
					if (oneSellerInfoArr.length != 0) {
						Clfjycs cs = new Clfjycs();

						cs.dyz = oneSellerInfoArr[0];
						cs.cslxms=oneSellerInfoArr[1];
						cs.zxbs = oneSellerInfoArr[2];
						cs.cjr = ud.getYhid();
						cs.lrr = ud.getYhid();
						cs.swjgzzjgdm =ud.getSsdwdm();
						
						
						//����Ƿ��Ѿ�����
						boolean isExist = DAOFactory.getInstance().getClfjycsDAO().isExist(conn, cs.dyz);
						
						if(isExist){
							isExistStr+="("+cs.dyz+":"+cs.cslxms+")";
						}else{
							sellerList.add(cs);
						}
					}
				}
			}
			
			if(isExistStr != null && !"".equals(isExistStr)){
				throw new ApplicationException("����������ԱȨ����Ϣ������Ա�Ѿ����ڣ������ظ�����,��ɾ�������ԣ�"+isExistStr);
			}
			
			
			
			
		//ִ�б���
			if(sellerList != null && sellerList.size() != 0){
				DAOFactory.getInstance().getClfjycsDAO().saveNewAdd(conn, sellerList);
			}
		}catch(Exception e){
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);			
		}finally {
	           QSDBUtil.freeConnection(conn);
	       }
		return true;
	}
	
	
	

}
