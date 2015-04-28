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
import com.ttsoft.bjtax.shenbao.fangtu.xmlvo.TDChuzuVO;
import com.ttsoft.bjtax.shenbao.model.domain.CZTDJBXX;
import com.ttsoft.bjtax.shenbao.model.domain.FTCDJZB;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.DBAccess;
import com.ttsoft.framework.util.DateTimeUtil;
import com.ttsoft.framework.util.VOPackage;

public class TDChuzuProcessor extends FangtuProcessor implements Processor {
	Logger logger = Logger.getLogger(TDChuzuProcessor.class);

	/**
	 * 统一接口
	 */
	public Object process(VOPackage vo) throws BaseException {

		logger.debug("execute TDChuzuProcessor.");
		fangtuVO = (FangtuVO) vo.getData();
		userData = vo.getUserData();

		List addList = new ArrayList();
		List updateList = new ArrayList();
		List deleteList = new ArrayList();

		
		List vos = fangtuVO.getTdChuzuList();
		if ( vos != null && vos.size() != 0 ) {
			for (Iterator iter = vos.iterator(); iter.hasNext();) {
				TDChuzuVO obj = (TDChuzuVO) iter.next();
				CZTDJBXX pojo = constructPojo(obj);
				
				String actionType = obj.getOpFlag() ;
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
			CZTDJBXX vo = (CZTDJBXX) deleteList.get(i);
			String pk = vo.getId();
			String cond = "ID = '" + pk + "'";
			try {
				dao.delete(cond, vo);
			} catch (Exception ep) {
				ep.printStackTrace();
				throw new ApplicationException("删除出租土地数据错误！");
			}

		}
	}

	protected void doUpdate(List updateList, DBAccess dao) throws BaseException {

		if (updateList == null || updateList.size() == 0)
			return;
		//校验是否符合应用逻辑主键
		String[] uniqueColumn = new String[] { "jsjdm","tdzl", "tdzsh" };
		String dupResult = checkDupRecord(updateList, dao, CZTDJBXX.class, uniqueColumn);
		if (dupResult != null) {
			throw new ApplicationException("重复更新出租土地[" + dupResult + "]！");
		}
		for (int i = 0; i < updateList.size(); i++) {
			CZTDJBXX vo = (CZTDJBXX) updateList.get(i);
			
			String sql="update SFDB.SF_JL_CZTDJBXX set" + 
			" TDZL='" + vo.getTdzl() + "'," +
			" TDZSH='" + vo.getTdzsh() + "'," + 
			" TDMJ=" + vo.getTdmj() + "," +
			" QZMSMJ=" + vo.getQzmsmj() + "," +
			" QZYSMJ=" + vo.getQzysmj() + "," +
			" MPFMSE=" + vo.getMpfmse() + "," +
			" NYNSE=" + vo.getNynse() + "," +  
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
				throw new ApplicationException("更新出租土地数据错误！");
			}

		}

	}

	protected void doAdd(List addList, DBAccess dao) throws BaseException {

		if (addList == null || addList.size() == 0)
			return;

//		增加一条主表记录
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
		
		//保存主表信息
		dao.insert( main );
		
		for (int i = 0; i < addList.size(); i++) {
			String pk;
			
			//下面从sequence获取主键
			pk = KeyUtil.getKey();	
			
			CZTDJBXX vo = (CZTDJBXX) addList.get(i);
			vo.setId(pk);
			vo.setDjbh( seqMain );
			vo.setCjr(userData.yhid);
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			vo.setCjrq(timestamp);
		}
		//校验是否符合应用逻辑主键
		String[] uniqueColumn = new String[] { "jsjdm","tdzl", "tdzsh" };
		String dupResult = checkDupRecord(addList, dao, CZTDJBXX.class, uniqueColumn);
		if (dupResult != null) {
			throw new ApplicationException("重复新增出租土地[" + dupResult + "]！");
		}
		// 保存
		//System.out.println("insert before. size is " + addList.size());
		dao.insert(addList);
		//System.out.println("insert after.");

	}

	private CZTDJBXX constructPojo( TDChuzuVO vo) throws BaseException {
		CZTDJBXX pojo = new CZTDJBXX();
		
		pojo.setJsjdm(fangtuVO.getJsjdm());

		pojo.setTbrq(DateTimeUtil.stringToTimestamp(fangtuVO.getInputDate(),
				ConstantFangtu.DATE_FORMAT));// 填表日期
		pojo.setDjbh( vo.getDjbh() ); // 登记编号
		pojo.setId(vo.getId()); // 唯一标识

		pojo.setTdzl(vo.getTdzl()); //土地坐落

		pojo.setTdzsh(vo.getTdzsh()); //土地证书号

		pojo.setTdmj( Utils.string2Number(vo.getTdmj())); //土地面积
		pojo.setQzmsmj( Utils.string2Number(vo.getQzmsmj())); //其中免税面积
		
		pojo.setQzysmj(Utils.string2Number(vo.getQzysmj())); //其中应税面积
		
		pojo.setMpfmse(Utils.string2Number(vo.getMpfmse())); //每平方米税额
		
		pojo.setNynse(Utils.string2Number(vo.getNynse())); //年应纳税额
		
		pojo.setBz(vo.getBz()); //备注
		

		pojo.setSwjgzzjgdm(userData.ssdwdm);
		pojo.setQxdm(userData.ssdwdm.substring(0, 2));
		pojo.setLrr(userData.yhid);
		pojo.setLrrq(new Timestamp(System.currentTimeMillis()));
		
//		复核标识, 标记为未复核
		pojo.setFhbs( "0" );
		return pojo;
	}
	


}