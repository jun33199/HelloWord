package com.ttsoft.bjtax.shenbao.sbzl.jms.processor;

import java.util.*;
import java.sql.Connection;
import java.sql.Timestamp;

import com.ekernel.db.or.ORManager;
import com.ekernel.db.or.ORContext;

import com.syax.bjtax.ca.vo.DzyjsjVO;
import com.syax.common.util.CAcodeConstants;
import com.ttsoft.common.model.UserData;

import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;

import com.ttsoft.bjtax.shenbao.util.CAUtils;
import com.ttsoft.bjtax.shenbao.util.DBResource;
import com.ttsoft.bjtax.shenbao.util.TinyTools;
import com.ttsoft.bjtax.shenbao.sbzl.jms.JmsActionConstant;
import com.ttsoft.bjtax.shenbao.sbzl.jms.JmsMapConstant;
import com.ttsoft.bjtax.shenbao.sbzl.jms.xmlvo.JmsVO;
import com.ttsoft.bjtax.shenbao.model.domain.Szsm;
import com.ttsoft.bjtax.shenbao.model.domain.Jm;
import com.ttsoft.bjtax.shenbao.model.domain.Zjqyjmslx;
import com.ttsoft.bjtax.shenbao.constant.CodeConstant;
import com.ttsoft.bjtax.shenbao.constant.CodeTable;

import com.ttsoft.bjtax.jikuai.zwcl.inf.JKAdapter;
import com.ttsoft.framework.exception.*;
import com.ttsoft.bjtax.shenbao.util.FriendHelper;

/**
 * 
 * <p>
 * Title: 北京地税综合管理系统 申报征收模块
 * </p>
 * <p>
 * Description: 减免税申报Processor
 * </p>
 * <p>
 * Copyright: Copyright (c) 2003
 * </p>
 * <p>
 * Company: TTSOFT
 * </p>
 * @version 1.0
 */
public class JmsProcessor implements Processor {

	/**
	 * 总控数据
	 */
	private UserData userData;

	/**
	 * orManage的常量
	 */
	private static final long SESSIONID = 0;

	/**
	 * 分析传递过来的VOPackage，根据action判断是何种操作
	 * 
	 * @param voPackage
	 *            业务参数
	 * @return vopackage
	 * @throws com.ttsoft.framework.exception.BaseException
	 */
	public Object process(VOPackage voPackage) throws BaseException {
		this.userData = voPackage.getUserData();
		// 根据业务操作类型值来做业务操作
		try {
			switch (voPackage.getAction()) {
			// 查询
			case JmsActionConstant.INT_ACTION_QUERY:
				voPackage.setData(doQuery((Map) voPackage.getData()));
				return voPackage;

			// 保存
			case JmsActionConstant.INT_ACTION_SAVE:
				return doSave(voPackage);

			// 删除
			case JmsActionConstant.INT_ACTION_DELETE:
				return doDelete(voPackage);

			// 没有可调用的方法
			default:
				throw new SystemException("no such mothod");
			}
		} catch (Exception e) {
			throw ExceptionUtil.getBaseException(e);
		}
	}

	/**
	 * 1.通过key = JmsMapConstant.LIST_JMSB取得申报数据，判断数据的合法性 2.通过key =
	 * JmsMapConstant.LIST_JMYSB取得本期已申报数据 3.删除已申报数据，保存申报数据 4.保存成功返回用key =
	 * JmsMapConstant.RESULT放入保存结果，否则抛出异常
	 * 
	 * @param data
	 *            业务参数
	 * @throws com.ttsoft.framework.exception.BaseException
	 */
	private Map doSave(VOPackage voPackage) throws BaseException {
		Map data = (Map) voPackage.getData();
		DzyjsjVO dzyj = saveSignData(voPackage);
		data.remove(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);
		data.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
		Connection connection = null;
		try {
			// 申报数据
			List jmsb = (List) data.get(JmsMapConstant.LIST_JMSB);

			// 本期已申报数据
			List bqysb = (List) data.get(JmsMapConstant.LIST_JMYSB);

			String[] zqlxdm = (String[]) data.get(JmsMapConstant.ZQLXDM);

			ORManager orManager = DBResource.getORManager();
			connection = DBResource.getConnection();

			// 删除已申报数据
			for (int i = 0; i < bqysb.size(); i++) {
				orManager.deleteObject(SESSIONID, connection, bqysb.get(i));
			}

			// 保存当前申报数据
			for (int i = 0; i < jmsb.size(); i++) {
				Jm jmTmp = (Jm) jmsb.get(i);
				// 预算级次代码
				jmTmp.setYsjcdm(FriendHelper.getYsjc(jmTmp.getJsjdm(),
						jmTmp.getSzsmdm(), jmTmp.getSkssjsrq()).getYsjcdm());

				// 预算科目代码
				jmTmp.setYskmdm(JKAdapter.getInstance().getYskm(
						jmTmp.getSzsmdm(), jmTmp.getDjzclxdm(),
						jmTmp.getGjbzhydm(), jmTmp.getYsjcdm()).getYskmdm());
				jmTmp.setSjly(CodeConstant.WSSB_JMSB_SJLY);
				orManager.makePersistent(SESSIONID, connection, jmTmp);
			}
		} catch (Exception ex) {
			throw ExceptionUtil.getBaseException(ex, "保存操作失败!");
		} finally {
			DBResource.destroyConnection(connection);
		}
		return data;
	}

	/**
	 * 使用key为JmsMapConstant.SBRQ取申报日期 通过计算机代码和当前日期取得本期已申报数据（当月）
	 * 用key为JmsMapConstant.LIST_JMYSB放本期已申报数据
	 * 
	 * @param data
	 *            业务参数
	 * @return Map
	 * @throws com.ttsoft.framework.exception.BaseException
	 */
	private Map doQuery(Map data) throws BaseException {
		// 保存结果数据结构
		Map map = new HashMap();

		// 定义数据库连接
		Connection connection = null;

		// 本期已申报数据
		List bqysbList = null;
		// 税种税目数据
		List szsmList = null;
		
		//减免项目及代码
		List jmxmList = new ArrayList();

		try {
			// 取企业计算机代码使用key为CzzsjdMapConstant.JSJDM
			String jsjdm = this.userData.yhid;
			// 登记注册类型代码
			String djzclxdm = (String) data.get(JmsMapConstant.DJZCLXDM);

			// 申报日期
			Timestamp sbrq = (Timestamp) data.get(JmsMapConstant.SBRQ);

			// 获得 ORManager实例
			ORManager orManager = DBResource.getORManager();
			// 获得数据库连接
			connection = DBResource.getConnection();
			// ORManager的条件语句
			Vector criteria = new Vector();
			// ORManager的条件上下文
			ORContext orContext = null;

			// 取当期申报数据
			// 使用ORManager对表“减免申报数据表”取本期已申报数据
			// 参数：计算机代码、当前年月
			criteria.add("jsjdm = '" + jsjdm + "'");

			// 根据申报日期取得当前月第一天和下月第一天
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(sbrq);
			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH);
			Timestamp ksrq = new Timestamp(
					new GregorianCalendar(year, month, 1).getTime().getTime());
			Timestamp jsrq = new Timestamp(new GregorianCalendar(year,
					month + 1, 1).getTime().getTime());

			criteria.add("sbrq >= to_date('" + ksrq.toString().substring(0, 10)
					+ "','yyyy-MM-dd')");
			criteria.add("sbrq < to_date('" + jsrq.toString().substring(0, 10)
					+ "','yyyy-MM-dd')");

			// 记账标记的第一位为０
			criteria.add("substr(jzbz, 1, 1) = '0'");
			// 取网上申报数据
			criteria.add("fsdm = '" + CodeConstant.FSDM_WSSB + "'");
			// 取减免税数据来源
			criteria.add("sjly = '" + CodeConstant.JMSSB_SJLY + "'");
			// 区县代码
			String qxdm = (String) data.get(JmsMapConstant.QXDM);
			criteria.add("qxdm = '" + qxdm + "'");
			
			orContext = new ORContext(Jm.class.getName(), criteria);
			bqysbList = orManager.query(this.SESSIONID, connection, orContext);
			map.put(JmsMapConstant.LIST_JMYSB, bqysbList);

			// 取得税种税目代码List
			criteria.removeAllElements();

			String sql = "zxbs = '0' "
					+ " and (sffjs is null or sffjs <> '3')"
					+ " and substr(szsmdm,0,2) not in (select szsmdm from sbdb.sb_jl_jjlxglszsm where djzclxdm = '"
					+ djzclxdm
					+ "')"
					+ " and substr(szsmdm,0,4) not in (select szsmdm from sbdb.sb_jl_jjlxglszsm where djzclxdm = '"
					+ djzclxdm
					+ "')"
					+ " and szsmdm not in (select szsmdm from sbdb.sb_jl_jjlxglszsm where djzclxdm = '"
					+ djzclxdm + "') order by szsmdm";

			criteria.add(sql);
			orContext = new ORContext(Szsm.class.getName(), criteria);

			szsmList = orManager.query(this.SESSIONID, connection, orContext);
			map.put(JmsMapConstant.LIST_SZSM, szsmList);
			
			//查询减免项目及代码 减免税项目  tujb 200404
            Vector criJmxm = new Vector();
            ORContext jmxmContext = null;
			criJmxm.add("zxbz = '" + CodeConstant.JMXM_ZXBS_0 + "' and yxbs = '" + CodeConstant.JMXM_YXBS_0 + "' order by jmslxdm");
			jmxmContext = new ORContext(Zjqyjmslx.class.getName(), criJmxm);
			List tmpList = orManager.query(this.SESSIONID, connection, jmxmContext);
			if(tmpList != null && tmpList.size() > 0 )
			{
				for(int i =0 ;i < tmpList.size(); i++)
				{
					Zjqyjmslx jmxm = new  Zjqyjmslx();
					Zjqyjmslx zjqyjmList = (Zjqyjmslx) tmpList.get(i);

					jmxm.setJmslxdm(zjqyjmList.getJmslxdm());
					jmxm.setJmslxmc(TinyTools.replaceBlank(zjqyjmList.getJmslxmc()));
					jmxm.setJmszcqsnd(TinyTools.replaceBlank(zjqyjmList.getJmszcqsnd()));
					jmxm.setSz(TinyTools.replaceBlank(zjqyjmList.getSz()));
					jmxm.setSzdm(TinyTools.replaceBlank(zjqyjmList.getSzdm()));
					jmxm.setWh(TinyTools.replaceBlank(zjqyjmList.getWh()));

					jmxmList.add(jmxm);
				}
				
			}
            map.put(JmsMapConstant.LIST_JMXM, jmxmList);

			return map;
		} catch (Exception ex) {
			throw ExceptionUtil.getBaseException(ex, "查询操作失败!");
		} finally {
			DBResource.destroyConnection(connection);
		}
	}

	/**
	 * 1.通过key = JmsMapConstant.LIST_JMYSB取得本期已申报数据 2.删除已申报数据 3.保存成功返回用key =
	 * JmsMapConstant.RESULT放入保存结果，否则抛出异常
	 * 
	 * @param data
	 *            业务参数
	 * @return Map
	 * @throws com.ttsoft.framework.exception.BaseException
	 */
	private Map doDelete(VOPackage voPackage) throws BaseException {
		Map data = (Map) voPackage.getData();
		DzyjsjVO dzyj = saveSignData(voPackage);
		data.remove(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);
		data.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
		// 保存结果数据结构
		Map map = new HashMap(1);
		// 定义数据库连接
		Connection connection = null;
		try {
			// 本期已申报数据
			List bqysb = (List) data.get(JmsMapConstant.LIST_JMYSB);

			ORManager orManager = DBResource.getORManager();
			connection = DBResource.getConnection();

			// 删除已申报数据
			for (int i = 0; i < bqysb.size(); i++) {
				orManager.deleteObject(SESSIONID, connection, bqysb.get(i));
			}
		} catch (Exception ex) {
			throw ExceptionUtil.getBaseException(ex, "删除操作失败!");
		} finally {
			DBResource.destroyConnection(connection);
		}
		return data;
	}

    private DzyjsjVO saveSignData(VOPackage vo) throws BaseException
    {
        Map pData = (Map) vo.getData();
        JmsVO jms = (JmsVO) pData.get(CAcodeConstants.CA_MAPKEY_VO_XMLVO);
        UserData ud = vo.getUserData();
        DzyjsjVO dzyj = null;
        dzyj = (DzyjsjVO) pData.get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);

        try
        {
            dzyj = CAUtils.saveDzyjsj(ud, dzyj,jms.getSbxx().getNd(), "", jms.getSbxx().getSkssksrq(), "");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
        return dzyj;
    }
}