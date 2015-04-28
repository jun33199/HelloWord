package com.ttsoft.bjtax.shenbao.fangtu.processor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.ttsoft.bjtax.shenbao.fangtu.ConstantFangtu;
import com.ttsoft.bjtax.shenbao.fangtu.KeyUtil;
import com.ttsoft.bjtax.shenbao.fangtu.Utils;
import com.ttsoft.bjtax.shenbao.fangtu.xmlvo.FangtuVO;
import com.ttsoft.bjtax.shenbao.fangtu.xmlvo.TDChengzuVO;
import com.ttsoft.bjtax.shenbao.model.domain.CZUTDJBXX;
import com.ttsoft.bjtax.shenbao.model.domain.FTCDJZB;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.DBAccess;
import com.ttsoft.framework.util.DateTimeUtil;
import com.ttsoft.framework.util.VOPackage;

public class TDChengzuProcessor extends FangtuProcessor implements Processor {
	Logger logger = Logger.getLogger(TDChengzuProcessor.class);
	/**
	 * ͳһ�ӿ�
	 */
	public Object process(VOPackage vo) throws BaseException {

		logger.debug("execute TDChengzuProcessor.");
		fangtuVO = (FangtuVO) vo.getData();
		userData = vo.getUserData();
		
		List addList = new ArrayList();
		List updateList = new ArrayList();
		List deleteList = new ArrayList();

		List vos = fangtuVO.getTdChengzuList();
		if ( vos != null && vos.size() != 0 ) {
			for (Iterator iter = vos.iterator(); iter.hasNext();) {
				TDChengzuVO obj = (TDChengzuVO) iter.next();
				CZUTDJBXX pojo = constructPojo(obj);
				
				String actionType =  obj.getOpFlag() ;
				logger.debug("actionType: [" + actionType + "]");
				
				if (ConstantFangtu.ADD_DATA.equals(actionType)) {
					addList.add(pojo);
				} else if (ConstantFangtu.UPDATE_DATA.equals(actionType)) {
					updateList.add(pojo);
				} else if (ConstantFangtu.DELETE_DATA.equals(actionType)) {
					deleteList.add(pojo);
				}
			}
			
		}

		return execute(addList, updateList, deleteList);		
	}


	protected void doDelete(List deleteList, DBAccess dao) throws BaseException {

		if (deleteList == null || deleteList.size() == 0)
			return;

		for (int i = 0; i < deleteList.size(); i++) {
			CZUTDJBXX vo = (CZUTDJBXX) deleteList.get(i);
			String pk = vo.getId();
			String cond = "ID = '" + pk + "'";
			try {
				dao.delete(cond, vo);
			} catch (Exception ep) {
				ep.printStackTrace();
				throw new ApplicationException("ɾ�������������ݴ���");
			}

		}
	}

	protected void doUpdate(List updateList, DBAccess dao) throws BaseException {

		if (updateList == null || updateList.size() == 0)
			return;

		//У���Ƿ����Ӧ���߼�����
		String[] uniqueColumn = new String[] { "jsjdm","cztdzl" };
		String dupResult = checkDupRecord(updateList, dao, CZUTDJBXX.class, uniqueColumn);
		if (dupResult != null) {
			throw new ApplicationException("�ظ����³�������[" + dupResult + "]��");
		}
		for (int i = 0; i < updateList.size(); i++) {
			CZUTDJBXX vo = (CZUTDJBXX) updateList.get(i);
			if(vo.getSfjnws()==null||vo.getSfjnws().trim().equals("")){
				/*����Ƿ��������Ͷ����ҵ����ʹ�÷� ��ʶΪ��,��Ĭ��Ϊ������*/
				vo.setSfjnws(ConstantFangtu.WZQY_JN_FLAG_NO);
			}
			String sql="update SFDB.SF_JL_CZUTDJBXX set" + 
			" CZRMC='" + vo.getCzrmc() + "'," +
			" ZJLXDM='" + vo.getZjlxdm() + "'," + 
			" CZRZJHM='" + vo.getCzrzjhm() + "'," + 
			" CZTDZL='" + vo.getCztdzl() + "'," + 
			" TDMJ=" + vo.getTdmj() + "," + 
			" SFJNWS='" + vo.getSfjnws() + "'," + 
			" LRR='" + userData.yhid + "'," +
			" LRRQ=sysdate" + "," +
			" BZ='" + vo.getBz() + "'" + 
			" where ID='" + vo.getId() + "'";
			
			try {
//				dao.update(vo);
				//System.out.println(sql);
				dao.updateSQL(sql);
			} catch (Exception ep) {
				ep.printStackTrace();
				throw new ApplicationException("���³����������ݴ���");
			}

		}

	}

	protected void doAdd(List addList, DBAccess dao) throws BaseException {

		if (addList == null || addList.size() == 0)
			return;

//		����һ��������¼
		String seqMain = KeyUtil.getKey();

		FTCDJZB main = new FTCDJZB();
		main.setJsjdm(fangtuVO.getJsjdm());
		main.setDjbh(seqMain);
		main.setFsdm("5");
		main.setNsrmc(fangtuVO.getTaxpayerName());
		main.setNsrsbh(fangtuVO.getTaxpayerId());

		main.setDjlx("0");
		main.setTbrq(DateTimeUtil.stringToTimestamp(fangtuVO.getInputDate(),
				ConstantFangtu.DATE_FORMAT));
		
		main.setSwjgzzjgdm(userData.ssdwdm);
		main.setQxdm(userData.ssdwdm.substring(0, 2));
		main.setLrr(userData.yhid);
		main.setLrrq(new Timestamp(System.currentTimeMillis()));
		main.setCjr( userData.yhid );
		main.setCjrq( new Timestamp(System.currentTimeMillis()) );
		
		//����������Ϣ
		dao.insert( main );
		
		for (int i = 0; i < addList.size(); i++) {
			String pk;
			
			//�����sequence��ȡ����
			pk = KeyUtil.getKey();	
			
			CZUTDJBXX vo = (CZUTDJBXX) addList.get(i);
			vo.setId(pk);
			vo.setDjbh( seqMain );
			vo.setCjr(userData.yhid);
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			vo.setCjrq(timestamp);
		}

		//У���Ƿ����Ӧ���߼�����
		String[] uniqueColumn = new String[] { "jsjdm","cztdzl" };
		String dupResult = checkDupRecord(addList, dao, CZUTDJBXX.class, uniqueColumn);
		if (dupResult != null) {
			throw new ApplicationException("�ظ����ӳ�������[" + dupResult + "]��");
		}
		// ����
		//System.out.println("insert before. size is " + addList.size());
		dao.insert(addList);
		//System.out.println("insert after.");
		
		

	}

	private CZUTDJBXX constructPojo(TDChengzuVO vo) throws BaseException {
		CZUTDJBXX pojo = new CZUTDJBXX();
		
		pojo.setJsjdm(fangtuVO.getJsjdm());

		pojo.setTbrq(DateTimeUtil.stringToTimestamp(fangtuVO.getInputDate(),
				ConstantFangtu.DATE_FORMAT));// �������
		int i = 1;
		pojo.setDjbh(vo.getDjbh()); // �ǼǱ��
		pojo.setId(vo.getId()); // ���ߴ���

		pojo.setCzrmc(vo.getCzrmc()); //����������

		pojo.setZjlxdm(vo.getZjlxdm()); //֤�����ʹ���
		
		pojo.setCzrzjhm(vo.getCzrzjhm()); //������֤������
		
		pojo.setCztdzl(vo.getCztdzl());  //������������
		pojo.setZlqxdm(vo.getZlqxdm());  //������������

		pojo.setTdmj(Utils.string2Number(vo.getTdmj())); //�������
		
		if(vo.getSfjnws()==null||vo.getSfjnws().trim().equals("")){
			/*����Ƿ��������Ͷ����ҵ����ʹ�÷� ��ʶΪ��,��Ĭ��Ϊ������*/
			vo.setSfjnws(ConstantFangtu.WZQY_JN_FLAG_NO);
		}
		pojo.setSfjnws(vo.getSfjnws());//�Ƿ��������Ͷ����ҵ����ʹ�÷�
		pojo.setBz(vo.getBz()); //��ע

		pojo.setSwjgzzjgdm(userData.ssdwdm);
		pojo.setQxdm(userData.ssdwdm.substring(0, 2));
		pojo.setLrr(userData.yhid);
		pojo.setLrrq(new Timestamp(System.currentTimeMillis()));

//		���˱�ʶ, ���Ϊδ����
		pojo.setFhbs( "0" );
		return pojo;
	}
	


}