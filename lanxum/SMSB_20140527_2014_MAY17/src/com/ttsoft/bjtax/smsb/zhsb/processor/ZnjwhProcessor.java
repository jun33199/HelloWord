package com.ttsoft.bjtax.smsb.zhsb.processor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBUtils;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.JksUtil;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.bjtax.smsb.zhsb.web.ZhsbjkswhActionForm;
import com.ttsoft.bjtax.smsb.zhsb.web.ZnjwhActionForm;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

public class ZnjwhProcessor implements Processor {
	public ZnjwhProcessor() {
	}

	// private static final String YPYS = "Ypys"; //一票一税的连接名
	// private static final String YPDS = "Ypds"; //一票多税的连接名
	/**
	 * 实现Processor接口
	 *
	 * @param vo
	 *            业务参数
	 * @return Object VOPackage型数据
	 * @throws BaseException
	 *             业务异常
	 */

	public Object process(VOPackage vo)
			throws com.ttsoft.framework.exception.BaseException {
		switch (vo.getAction()) {
		case CodeConstant.SMSB_QUERYACTION:
			return doQuery(vo);
		case CodeConstant.SMSB_UPDATEACTION:
			return doCx(vo);

		default:
			return null;
		}

	}

	/**
	 * 查询所有满足条件的所有未入库缴款书 modified by qianchao 2005.11.2
	 *
	 * @param vo
	 *            业务参数
	 * @return ZnjwhActionForm ZnjwhActionForm型数据
	 * @throws BaseException
	 *             业务异常
	 */
	private ZnjwhActionForm doQuery(VOPackage vo) throws BaseException {

		// 定义数据库连接
		Connection conn = null;
		SfDBUtils sbDB = null;

		try {
			ZnjwhActionForm form = (ZnjwhActionForm) vo.getData(); // 从前端获得map对象
			// 得到userdata
			UserData userData = vo.getUserData();
			// 得到区县代码
			String qxdm = InterfaceDj.getQxdm(userData);
			String jsjdm = form.getJsjdm(); // 获取用户的计算机代码
			// 获取起始日期
			String qsrq = form.getQsrq();
			// 获取截止日期
			String jzrq = form.getJzrq();
			// 获取申报编号
			String sbbh = form.getSbbh();
			// 获得数据库连接
			conn = SfDBResource.getConnection();

	        // 查询SQL
	        StringBuffer buffer = new StringBuffer();

	        buffer.append("SELECT A.*, C.SZSMMC FROM SBDB.SB_JL_SBJKZB A, SBDB.SB_JL_SBJKMX B, DMDB.SB_DM_SZSM C ");
	        buffer.append("WHERE A.JSJDM = '" + jsjdm +"' ");
	        buffer.append("AND A.SBRQ >= TO_DATE('" + qsrq +"','YYYYMMDD') ");
	        buffer.append("AND A.SBRQ <= TO_DATE('" + jzrq+" 23:59:59','YYYYMMDD hh24:mi:ss') ");
	        buffer.append("AND A.SKLXDM NOT LIKE '5%' ");
	        buffer.append("AND A.SKLXDM NOT LIKE '6%' ");
	        buffer.append("AND A.SKLXDM NOT LIKE '8%' ");
	        buffer.append("AND A.SKLXDM <> '120' ");
	        buffer.append("AND A.SJLY = '35' ");
	        buffer.append("AND A.JKPZH = B.JKPZH ");
	        buffer.append("AND B.SZSMDM LIKE '%0091' ");
	        buffer.append("AND A.SZDM = C.SZSMDM ");
	        buffer.append("AND A.QXDM = '" + qxdm + "' ");
	        buffer.append("AND A.ZWBS like '0%0'");

	        if(sbbh!=null && !sbbh.trim().equals(""))
	        {
		        buffer.append(" AND A.SBBH = '" + sbbh + "'");

	        }

	        System.out.println("SQL == " + buffer.toString());
	        //获取PreparedStatement
	        PreparedStatement pst = conn.prepareStatement(buffer.toString());
	         //执行sql
	        ResultSet rs = pst.executeQuery();

	        List datalist = getJksList(rs);

			form.setDataList(datalist);
			return form;
		} catch (Exception e) {
			throw ExceptionUtil.getBaseException(e);
		} finally {
			SfDBResource.freeConnection(conn);
		}

	}

    /**
     * 获取银行申报信息
     * @param rs ResultSet
     * @return List
     * @throws Exception
     */
    private List getJksList(ResultSet rs) throws BaseException {
        List retList = new ArrayList();
        try {
            while (rs.next()) {
                HashMap vo = new HashMap();
                // 实缴金额
                vo.put("sjje",rs.getBigDecimal("sjje"));
                // 税种代码
                vo.put("szdm",rs.getString("szdm"));
                // 税种名称
                vo.put("szmc",rs.getString("szsmmc"));
                // 缴款凭证号
                vo.put("jkpzh", rs.getString("jkpzh"));
                // 申报编号
                vo.put("sbbh",rs.getString("sbbh"));
                // 申报日期
                vo.put("sbrq", rs.getDate("sbrq"));
                retList.add(vo);
            }
            return retList;
        }
        catch (Exception ex) {
            throw ExceptionUtil.getBaseException(ex);
        }
    }

	/**
	 * 根据列表中的税种代码得到税种名称
	 *
	 * @param list
	 *            申报缴款主表列表 List 包含 HashMap型的信息
	 * @return ArrayList 添加税种名称的申报缴款主表列表
	 * @author Shi Yanfeng 20040112
	 */
	/*
	 * deleted by qianchao 2005.11.3 private ArrayList addSzmc(ArrayList list) {
	 * for (int i = 0; i < list.size(); i++) { HashMap temp = (HashMap)
	 * list.get(i); //通过税种税目代码表得到税种代码 temp.put("szmc",
	 * CodeUtils.getCodeBeanLabel("DM_SZSM", (String) temp.get("szdm"))); }
	 * return list; }
	 */

	/**
	 * 撤销指定缴款书 或申报 modified by qianchao 2005.10.27
	 *
	 * @param vo
	 *            业务参数
	 * @return ArrayList 添加税种名称的申报缴款主表列表
	 * @throws BaseException
	 *             业务异常
	 */
	private ZhsbjkswhActionForm doCx(VOPackage vo) throws BaseException {
		ZhsbjkswhActionForm form = (ZhsbjkswhActionForm) vo.getData(); // 从前端获得map对象
		// 得到userdata
		UserData userData = vo.getUserData();
		// 得到区县代码
		String qxdm = InterfaceDj.getQxdm(userData);
		/**
		 * 添加日志 20040509 ShiYanfeng
		 */
		TinyTools.makeLog4ZhsbCx(userData, form.getJkpzhStr());
		JksUtil jks = new JksUtil();
		Connection conn = null;

		try {

			// start modifying by qianchao 2005.10.27
			// ArrayList ret = (ArrayList) jks.CxJks(form.getJkpzhStr(), qxdm);
			ArrayList ret = null;
			if (form.getJksType() == CodeConstant.PRINT_YPYS) {
				jks.CxJks(form.getJkpzhStr(), qxdm);
			} else {
				conn = SfDBResource.getConnection();
				jks.CxAllJks(form.getSbbh(), qxdm, form.getJsjdm(), conn);
			}
			// form.setCoList(ret);
			// end modifying by qianchao 2005.10.27
		} catch (Exception ex) {
			throw ExceptionUtil.getBaseException(ex, "撤销缴款书失败!");
		} finally {
			SfDBResource.freeConnection(conn);
		}
		return form;
	}

}
