package com.ttsoft.bjtax.shenbao.fangtu.processor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.ttsoft.bjtax.shenbao.fangtu.ConstantFangtu;
import com.ttsoft.bjtax.shenbao.fangtu.KeyUtil;
import com.ttsoft.bjtax.shenbao.fangtu.Utils;
import com.ttsoft.bjtax.shenbao.fangtu.xmlvo.FWZiyongVO;
import com.ttsoft.bjtax.shenbao.fangtu.xmlvo.FangtuVO;
import com.ttsoft.bjtax.shenbao.model.domain.FTCDJZB;
import com.ttsoft.bjtax.shenbao.model.domain.ZYFWJBXX;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.DBAccess;
import com.ttsoft.framework.util.DateTimeUtil;
import com.ttsoft.framework.util.VOPackage;

public class FWZiyongProcessor extends FangtuProcessor implements Processor {

	Logger logger = Logger.getLogger(FWZiyongProcessor.class);

	/**
	 * 统一接口
	 */
	public Object process(VOPackage vo) throws BaseException {

		logger.debug("execute FWZiyongProcessor.");
		fangtuVO = (FangtuVO) vo.getData();
		userData = vo.getUserData();

		List addList = new ArrayList();
		List updateList = new ArrayList();
		List deleteList = new ArrayList();

		List vos = fangtuVO.getFwZiyongList();
		if (vos != null && vos.size() != 0) {
			for (Iterator iter = vos.iterator(); iter.hasNext();) {
				FWZiyongVO obj = (FWZiyongVO) iter.next();
				ZYFWJBXX pojo = constructPojo(obj);

				String actionType = obj.getOpFlag();
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

	private ZYFWJBXX constructPojo(FWZiyongVO vo) throws BaseException {

		ZYFWJBXX pojo = new ZYFWJBXX();

		pojo.setJsjdm(fangtuVO.getJsjdm());

		pojo.setTbrq(DateTimeUtil.stringToTimestamp(fangtuVO.getInputDate(),
				ConstantFangtu.DATE_FORMAT));// 填表日期

		pojo.setDjbh(vo.getDjbh()); // 登记编号
		pojo.setId(vo.getId()); // 唯一标识
		pojo.setFwzl(vo.getFwzl()); // 房屋坐落
		pojo.setCqzsh(vo.getCqzsh()); // 产权证书号

		pojo.setFcyz(Utils.string2Number(vo.getFcyz())); // todo 房产原值

		pojo.setSwjggz(Utils.string2Number(vo.getSwjggz()));// 税务机关估值

		pojo.setQzmsyz(Utils.string2Number(vo.getQzmsyz()));// 其中免税原值

		pojo.setQzysyz(Utils.string2Number(vo.getQzysyz())); // 其中应税原值
		pojo.setNynse(Utils.string2Number(vo.getNynse()));// 年应纳税额

		pojo.setSfdj(vo.getSfdj());// 是否代缴
		pojo.setBz(vo.getBz());// 备注

		pojo.setSwjgzzjgdm(userData.ssdwdm);
		pojo.setQxdm(userData.ssdwdm.substring(0, 2));
		pojo.setLrr(userData.yhid);
		pojo.setLrrq(new Timestamp(System.currentTimeMillis()));

		// 复核标识, 标记为未复核
		pojo.setFhbs("0");
		return pojo;
	}

	protected void doDelete(List deleteList, DBAccess dao) throws BaseException {

		if (deleteList == null || deleteList.size() == 0)
			return;

		for (int i = 0; i < deleteList.size(); i++) {
			ZYFWJBXX vo = (ZYFWJBXX) deleteList.get(i);
			String pk = vo.getId();
			String cond = "ID = '" + pk + "'";
			logger.debug("cond=" + cond);
			try {
				dao.delete(cond, vo);
			} catch (Exception ep) {
				ep.printStackTrace();
				throw new ApplicationException("删除自用房屋数据错误！");
			}

		}
	}

	protected void doUpdate(List updateList, DBAccess dao) throws BaseException {

		if (updateList == null || updateList.size() == 0)
			return;
		
		//校验是否符合应用逻辑主键
		String[] uniqueColumn = new String[] { "jsjdm","fwzl", "cqzsh" };
		String dupResult = checkDupRecord(updateList, dao, ZYFWJBXX.class, uniqueColumn);
		if (dupResult != null) {
			throw new ApplicationException("重复更新自用房屋[" + dupResult + "]！");
		}
		
		for (int i = 0; i < updateList.size(); i++) {
			ZYFWJBXX vo = (ZYFWJBXX) updateList.get(i);

			String sql = "update SFDB.SF_JL_ZYFWJBXX set" + " FWZL='"
					+ vo.getFwzl() + "'," + " CQZSH='" + vo.getCqzsh() + "',"
					+ " FCYZ=" + vo.getFcyz() + "," + " SWJGGZ="
					+ vo.getSwjggz() + "," + " QZMSYZ=" + vo.getQzmsyz() + ","
					+ " QZYSYZ=" + vo.getQzysyz() + "," + " NYNSE="
					+ vo.getNynse() + "," + " SFDJ='" + vo.getSfdj() + "',"
					+ " LRR='" + userData.yhid + "'," + " LRRQ=sysdate" + ","
					+ " BZ='" + vo.getBz() + "'" + " where ID='" + vo.getId()
					+ "'";

			try {
				// dao.update(vo);
				//System.out.println(sql);
				dao.updateSQL(sql);

			} catch (Exception ep) {
				ep.printStackTrace();
				throw new ApplicationException("更新自用房屋数据错误！");
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
		main.setCjr(userData.yhid);
		main.setCjrq(new Timestamp(System.currentTimeMillis()));

		// 保存主表信息
		dao.insert(main);

		// 设置主键
		for (int i = 0; i < addList.size(); i++) {
			ZYFWJBXX vo = (ZYFWJBXX) addList.get(i);
			String seq = KeyUtil.getKey();
			// 设置主键
			vo.setId(seq);
			// 登记编号
			vo.setDjbh(seqMain);
			// 复核标识, 标记为未复核
			vo.setFhbs("0");

			vo.setCjr(userData.yhid);
			vo.setCjrq(new Timestamp(System.currentTimeMillis()));

		}

		//校验是否符合应用逻辑主键
		String[] uniqueColumn = new String[] { "jsjdm","fwzl", "cqzsh" };
		String dupResult = checkDupRecord(addList, dao, ZYFWJBXX.class, uniqueColumn);
		if (dupResult != null) {
			throw new ApplicationException("重复增加自用房屋[" + dupResult + "]！");
		}
		
		
		// 保存
		//System.out.println("insert before. size is " + addList.size());
		dao.insert(addList);
		//System.out.println("insert after.");

	}
}