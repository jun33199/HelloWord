package com.ttsoft.bjtax.shenbao.fangtu.processor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.ttsoft.bjtax.shenbao.fangtu.ConstantFangtu;
import com.ttsoft.bjtax.shenbao.fangtu.KeyUtil;
import com.ttsoft.bjtax.shenbao.fangtu.Utils;
import com.ttsoft.bjtax.shenbao.fangtu.xmlvo.FWChengzuVO;
import com.ttsoft.bjtax.shenbao.fangtu.xmlvo.FangtuVO;
import com.ttsoft.bjtax.shenbao.model.domain.CZUFWJBXX;
import com.ttsoft.bjtax.shenbao.model.domain.FTCDJZB;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.DBAccess;
import com.ttsoft.framework.util.DateTimeUtil;
import com.ttsoft.framework.util.VOPackage;

public class FWChengzuProcessor extends FangtuProcessor implements Processor {
	
	Logger logger = Logger.getLogger(FWChengzuProcessor.class);

	/**
	 * 统一接口
	 */
	public Object process(VOPackage vo) throws BaseException {

		logger.debug("execute FWChuzuProcessor.");
		fangtuVO = (FangtuVO) vo.getData();
		userData = vo.getUserData();

		List addList = new ArrayList();
		List updateList = new ArrayList();
		List deleteList = new ArrayList();

		List vos = fangtuVO.getFwChengzuList();
		if ( vos != null && vos.size() != 0 ) {
			for (Iterator iter = vos.iterator(); iter.hasNext();) {
				FWChengzuVO obj = (FWChengzuVO) iter.next();
				CZUFWJBXX pojo = constructPojo(obj);
				
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

	private CZUFWJBXX constructPojo(FWChengzuVO vo) throws BaseException {

		CZUFWJBXX pojo = new CZUFWJBXX();

		pojo.setJsjdm(fangtuVO.getJsjdm());

		pojo.setTbrq(DateTimeUtil.stringToTimestamp(fangtuVO.getInputDate(),
				ConstantFangtu.DATE_FORMAT));// 填表日期

		int i = 1;

		pojo.setDjbh( vo.getDjbh() ); // 登记编号
		pojo.setId(vo.getId()); // 唯一标识
		
		
		pojo.setCzrmc(vo.getCzrmc()); // 出租人名称
		pojo.setZjlxdm(vo.getZjlxdm()); // 证件类型

		pojo.setCzrzjhm(vo.getCzrzjhm()); // 出租人证件号码
		pojo.setCzfwzl(vo.getCzfwzl()); // 承租房屋坐落
		pojo.setZlqxdm(vo.getZlqxdm()); // 坐落区县代码
		pojo.setNzj(Utils.string2Number(vo.getNzj())); //年租金
		
		pojo.setBz(vo.getBz());// 备注
		
		pojo.setSwjgzzjgdm(userData.ssdwdm);
		pojo.setQxdm(userData.ssdwdm.substring(0, 2));
		pojo.setLrr(userData.yhid);
		pojo.setLrrq(new Timestamp(System.currentTimeMillis()));

		//复核标识, 标记为未复核
		pojo.setFhbs( "0" );
		return pojo;
	}

	protected void doDelete(List deleteList, DBAccess dao) throws BaseException {

		if (deleteList == null || deleteList.size() == 0)
			return;

		for (int i = 0; i < deleteList.size(); i++) {
			CZUFWJBXX vo = (CZUFWJBXX) deleteList.get(i);
			String pk = vo.getId();
			String cond = "ID = '" + pk + "'";
			try {
				dao.delete(cond, vo);
			} catch (Exception ep) {
				ep.printStackTrace();
				throw new ApplicationException("删除承租房屋数据错误！");
			}

		}
	}

	protected void doUpdate(List updateList, DBAccess dao) throws BaseException {

		if (updateList == null || updateList.size() == 0)
			return;

		//校验是否符合应用逻辑主键
		String[] uniqueColumn = new String[] { "jsjdm","czfwzl" };
		String dupResult = checkDupRecord(updateList, dao, CZUFWJBXX.class, uniqueColumn);
		if (dupResult != null) {
			throw new ApplicationException("重复更新承租房屋[" + dupResult + "]！");
		}
		for (int i = 0; i < updateList.size(); i++) {
			CZUFWJBXX vo = (CZUFWJBXX) updateList.get(i);

			
			
			String sql="update SFDB.SF_JL_CZUFWJBXX set" + 
			" CZRMC='" + vo.getCzrmc() + "'," +
			" ZJLXDM='" + vo.getZjlxdm() + "'," + 
			" CZRZJHM='" + vo.getCzrzjhm() + "'," + 
			" CZFWZL='" + vo.getCzfwzl() + "'," + 
			" ZLQXDM='" + vo.getZlqxdm() + "'," + 
			" NZJ=" + vo.getNzj() + "," + 
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
				throw new ApplicationException("更新承租房屋数据错误！");
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
		
		//保存主表信息
		dao.insert( main );
		
		// 设置主键
		for (int i = 0; i < addList.size(); i++) {
			CZUFWJBXX vo = (CZUFWJBXX) addList.get(i);
			String seq = KeyUtil.getKey();
			//设置主键
			vo.setId(seq);
			//登记编号
			vo.setDjbh( seqMain );
			
			vo.setCjr(userData.yhid);
			vo.setCjrq(new Timestamp(System.currentTimeMillis()));

		}

		//校验是否符合应用逻辑主键
		String[] uniqueColumn = new String[] { "jsjdm","czfwzl" };
		String dupResult = checkDupRecord(addList, dao, CZUFWJBXX.class, uniqueColumn);
		if (dupResult != null) {
			throw new ApplicationException("重复增加承租房屋[" + dupResult + "]！");
		}

		// 保存
		//System.out.println("insert before. size is " + addList.size());
		dao.insert(addList);
		//System.out.println("insert after.");

	}

}