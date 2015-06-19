package com.ttsoft.bjtax.shenbao.fangtu.processor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.ttsoft.bjtax.shenbao.fangtu.ConstantFangtu;
import com.ttsoft.bjtax.shenbao.fangtu.KeyUtil;
import com.ttsoft.bjtax.shenbao.fangtu.Utils;
import com.ttsoft.bjtax.shenbao.fangtu.xmlvo.FWChuzuVO;
import com.ttsoft.bjtax.shenbao.fangtu.xmlvo.FangtuVO;
import com.ttsoft.bjtax.shenbao.model.domain.CZFWJBXX;
import com.ttsoft.bjtax.shenbao.model.domain.FTCDJZB;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.DBAccess;
import com.ttsoft.framework.util.DateTimeUtil;
import com.ttsoft.framework.util.VOPackage;

public class FWChuzuProcessor extends FangtuProcessor implements Processor {

	Logger logger = Logger.getLogger(FWChuzuProcessor.class);

	/**
	 * ͳһ�ӿ�
	 */
	public Object process(VOPackage vo) throws BaseException {

		logger.debug("execute FWChuzuProcessor.");
		fangtuVO = (FangtuVO) vo.getData();
		userData = vo.getUserData();
		
		
		List addList = new ArrayList();
		List updateList = new ArrayList();
		List deleteList = new ArrayList();


		List vos = fangtuVO.getFwChuzuList();
		if ( vos != null && vos.size() != 0 ) {
			for (Iterator iter = vos.iterator(); iter.hasNext();) {
				FWChuzuVO obj = (FWChuzuVO) iter.next();
				CZFWJBXX pojo = constructPojo(obj);
				
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

	private CZFWJBXX constructPojo( FWChuzuVO vo ) throws BaseException {

		CZFWJBXX pojo = new CZFWJBXX();

		pojo.setJsjdm(fangtuVO.getJsjdm());

		pojo.setTbrq(DateTimeUtil.stringToTimestamp(fangtuVO.getInputDate(),
				ConstantFangtu.DATE_FORMAT));// �������


		
		pojo.setDjbh( vo.getDjbh() ); // �ǼǱ��
		pojo.setId(vo.getId()); // Ψһ��ʶ
		pojo.setFwzl(vo.getFwzl()); // ��������
		pojo.setCqzsh(vo.getCqzsh()); // ��Ȩ֤���
		pojo.setCzfwyz(Utils.string2Number(vo.getCzfwyz()));//���ⷿ��ԭֵ
		pojo.setNzjsr(Utils.string2Number(vo.getNzjsr())); // ����Ӧ˰ԭֵ
		pojo.setNynse(Utils.string2Number(vo.getNynse()));// ��Ӧ��˰��
		pojo.setXgrczbs(vo.getXgrczbs());//���г��۸�����˳������ھ�ס��ס��
		pojo.setBz(vo.getBz());// ��ע
		
		pojo.setSwjgzzjgdm(userData.ssdwdm);
		pojo.setQxdm(userData.ssdwdm.substring(0, 2));
		pojo.setLrr(userData.yhid);
		pojo.setLrrq(new Timestamp(System.currentTimeMillis()));

		//���˱�ʶ, ���Ϊδ����
		pojo.setFhbs( "0" );
		return pojo;
	}

	protected void doDelete(List deleteList, DBAccess dao) throws BaseException {

		if (deleteList == null || deleteList.size() == 0)
			return;

		for (int i = 0; i < deleteList.size(); i++) {
			CZFWJBXX vo = (CZFWJBXX) deleteList.get(i);
			String pk = vo.getId();
			String cond = "ID = '" + pk + "'";
			try {
				dao.delete(cond, vo);
			} catch (Exception ep) {
				ep.printStackTrace();
				throw new ApplicationException("ɾ�����ⷿ�����ݴ���");
			}

		}
	}

	protected void doUpdate(List updateList, DBAccess dao) throws BaseException {

		if (updateList == null || updateList.size() == 0)
			return;
		//У���Ƿ����Ӧ���߼�����
		String[] uniqueColumn = new String[] { "jsjdm","fwzl", "cqzsh" };
		String dupResult = checkDupRecord(updateList, dao, CZFWJBXX.class, uniqueColumn);
		if (dupResult != null) {
			throw new ApplicationException("�ظ����³��ⷿ��[" + dupResult + "]��");
		}
		for (int i = 0; i < updateList.size(); i++) {
			CZFWJBXX vo = (CZFWJBXX) updateList.get(i);

			
			String sql="update SFDB.SF_JL_CZFWJBXX set" + 
			" FWZL='" + vo.getFwzl() + "'," +
			" CQZSH='" + vo.getCqzsh() + "'," + 
			" CZFWYZ=" + vo.getCzfwyz() + "," + 
			" NZJSR=" + vo.getNzjsr() + "," + 
			" NYNSE=" + vo.getNynse() + "," +  
			" LRR='" + userData.yhid + "'," +
			" LRRQ=sysdate" + "," +
			" XGRCZBS='" +vo.getXgrczbs()+ "'," +
			" BZ='" + vo.getBz() + "'" + 
			" where ID='" + vo.getId() + "'";
			
			try {
//				dao.update(vo);
				//System.out.println(sql);
				dao.updateSQL(sql);
				
			} catch (Exception ep) {
				ep.printStackTrace();
				throw new ApplicationException("���³��ⷿ�����ݴ���");
			}

		}

	}

	protected void doAdd(List addList, DBAccess dao) throws BaseException {

		if (addList == null || addList.size() == 0)
			return;

		// Get Sequence
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
		
		// ��������
		for (int i = 0; i < addList.size(); i++) {
			CZFWJBXX vo = (CZFWJBXX) addList.get(i);
			String seq = KeyUtil.getKey();
			//��������
			vo.setId(seq);
			//�ǼǱ��
			vo.setDjbh( seqMain );
			
			vo.setCjr(userData.yhid);
			vo.setCjrq(new Timestamp(System.currentTimeMillis()));

		}
		//У���Ƿ����Ӧ���߼�����
		String[] uniqueColumn = new String[] { "jsjdm","fwzl", "cqzsh" };
		String dupResult = checkDupRecord(addList, dao, CZFWJBXX.class, uniqueColumn);
		if (dupResult != null) {
			throw new ApplicationException("�ظ����ӳ��ⷿ��[" + dupResult + "]��");
		}

		// ����
		//System.out.println("insert before. size is " + addList.size());
		dao.insert(addList);
		//System.out.println("insert after.");

	}

}