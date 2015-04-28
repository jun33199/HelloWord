package com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdsba.processor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import com.syax.creports.Constants;
import com.syax.creports.bo.qyqssds.QyqssdsReportsDeclare;
import com.syax.creports.bo.qyqssds.QyqssdsReportsTableDeclare;
import com.syax.creports.persistent.AppAccessFactory;
import com.syax.creports.persistent.access.IAppAccess;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.qysdsjmsbagl.util.QysdsUtil;
import com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.qyqssdsba.web.QyqssdsBaForm;
import com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.util.QyqssdsActionHelper;
import com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.util.QyqssdsUtil2014;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.SBStringUtils;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

public class QyqssdsBaProcessor implements Processor {

	/**
	 * 实现Processor接口
	 * 
	 * @param vo
	 *            业务参数
	 * @return Object VOPackage型数据
	 * @throws BaseException
	 *             业务异常 1 当传过来的操作类型不对时抛出 2 当调用的业务方法抛出业务异常时向上传递抛出
	 *             其他异常抛出由EJB的process方法处理。
	 */
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
		case CodeConstant.SMSB_SAVEACTION:
			result = doSave(vo);
			break;
		// 接受申请
		case 5:
			result = doAccept(vo);
			break;
		// 拒绝申请
		case 6:
			result = doRefuse(vo);
			break;
		// 删除
		case 7:
			result = doDelete(vo);
			break;
		// 撤销
		case 8:
			result = doCancle(vo);
			break;

		default:
			throw new ApplicationException("用户执行了系统不支持的方法或功能.");
		}

		return result;
	}

	/**
	 * doShow初始化对象页面信息要素
	 * 
	 * @param vo
	 *            业务参数
	 * @return 数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
	 * @throws BaseException
	 *             当其他情况都会抛出异常信息
	 */
	private Object doShow(VOPackage vo) throws BaseException {

		QyqssdsBaForm qyqssdsBaForm = (QyqssdsBaForm) vo.getData();

		UserData ud = (UserData) vo.getUserData();

		qyqssdsBaForm.setCjr(ud.yhid);
		qyqssdsBaForm.setLrr(ud.yhid);
		// qyqssdsBaForm.setBaShztMessage("请输入计算机代码进行查询！");
		return qyqssdsBaForm;
	}

	/**
	 * 根据计算机代码查询备案信息
	 * 
	 * @param vo
	 * @return
	 * @throws BaseException
	 * @throws com.syax.creports.exception.BaseException
	 */
	private Object doQuery(VOPackage vo) throws BaseException {

		QyqssdsBaForm requestForm = (QyqssdsBaForm) vo.getData();
		QyqssdsBaForm form = new QyqssdsBaForm();

		UserData ud = (UserData) vo.getUserData();

		SWDJJBSJ djsj = null;

		// 获得企业登记基本信息
		try {
			djsj = InterfaceDj.getJBSJ(requestForm.getJsjdm(), ud);
		} catch (Exception e) {
			throw ExceptionUtil.getBaseException(e);
		}
		// 进行数据权限过滤
		QyqssdsUtil2014.getAlertStrWhenAdd(requestForm.getJsjdm(), ud);
		// 计算机代码
		form.setJsjdm(requestForm.getJsjdm());
		// 纳税人名称
		form.setNsrmc(djsj.getNsrmc());
		// 纳税人识别号
		form.setNsrsbh(djsj.getSwdjzh());
		//form.setLxdh(djsj.getZcdzlxdh());
		/*-以下几项默认是选择否-*/
		form.setJyqxjm("N");
		form.setGdjyjs("N");
		form.setYfdxgb("N");
		form.setYfxgpc("N");
		form.setYfgdqs("N");
		form.setQtyy("N");
		form.setCzlx(requestForm.getCzlx());
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = SfDBResource.getConnection();
			String sql = "SELECT *FROM SBDB.SB_JL_QYQSSDSBA_NSRJBXXB WHERE NSRJSJDM='"
					+ requestForm.getJsjdm() + "'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				// 计算机代码
				form.setJsjdm(rs.getString("NSRJSJDM") == null ? "" : rs
						.getString("NSRJSJDM"));
				// 纳税人识别号
				form.setNsrsbh(rs.getString("NSRSBH") == null ? "" : rs
						.getString("NSRSBH"));
				// 纳税人名称
				form.setNsrmc(rs.getString("NSRMC") == null ? "" : rs
						.getString("NSRMC"));
				// 清算起始日期
				form.setQsbaksrq(rs.getString("QSBAKSRQ").substring(0, 10) == null ? ""
						: rs.getString("QSBAKSRQ").substring(0, 10));
				// 管理人或清算组联络人员
				form.setQsllry(rs.getString("QSLLRY") == null ? "" : rs
						.getString("QSLLRY"));
				// 联系电话
				form.setLxdh(rs.getString("LXDH") == null ? "" : rs
						.getString("LXDH"));
				// 企业章程规定的经营期限届满
				form.setJyqxjm(rs.getString("JYQXJM") == null ? "" : rs
						.getString("JYQXJM"));
				// 企业股东会、股东大会或类似机构决议解散
				form.setGdjyjs(rs.getString("GDJYJS") == null ? "" : rs
						.getString("GDJYJS"));
				// 企业依法被吊销营业执照、责令关闭或者被撤销
				form.setYfdxgb(rs.getString("YFDXGB") == null ? "" : rs
						.getString("YFDXGB"));
				// 企业被人民法院依法予以解散或宣告破产
				form.setYfxgpc(rs.getString("YFXGPC") == null ? "" : rs
						.getString("YFXGPC"));
				// 有关法律、行政法规规定清算
				form.setYfgdqs(rs.getString("YFGDQS") == null ? "" : rs
						.getString("YFGDQS"));
				// 企业因其他原因解散或进行清算
				form.setQtyy(rs.getString("QTYY") == null ? "" : rs
						.getString("QTYY"));
				// 申请类型:0,网上；1,上门
				form.setSqlx(rs.getString("SQLXDM") == null ? "" : rs
						.getString("SQLXDM"));
				String bashztbs = "";
				String shztts = rs.getString("BASHZTBS") == null ? "" : rs
						.getString("BASHZTBS");
				form.setBaShztbs(shztts);
				if (!"".equals(shztts)) {
					int key = Integer.parseInt(shztts);
					switch (key) {
					case 1:
						bashztbs = "已提交未审核";
						break;
					case 2:
						bashztbs = "审核已通过";
						break;
					case 3:
						bashztbs = "审核被驳回";
						break;
					case 4:
						bashztbs = "撤销";
						break;

					default:
						break;
					}
				}
				// 清算备案审核状态标识 1：已提交未审核，2：审核已通过，3：审核被驳回，4：撤销
				form.setBaShztMessage(bashztbs);
				// 清算申报审核状态标识 1：已提交未审核，2：审核已通过，3：审核被驳回，4：撤销
				String sbShztbs = rs.getString("SBSHZTBS") == null ? "" : rs
						.getString("SBSHZTBS");
				form.setSbShztbs(sbShztbs);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			SfDBResource.freeConnection(conn);
		}

		return form;
	}

	/**
	 * 保存备案信息
	 * 
	 * @param vo
	 * @return
	 * @throws BaseException
	 */
	private Object doSave(VOPackage vo) throws BaseException {
		QyqssdsBaForm form = (QyqssdsBaForm) vo.getData();
		UserData ud = (UserData) vo.getUserData();
		SWDJJBSJ djsj = null;
		// 获得企业登记基本信息
		try {
			djsj = InterfaceDj.getJBSJ_New(form.getJsjdm(), ud);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw ExceptionUtil.getBaseException(e);
		}
		Connection conn = null;
		int count = 0;
		try {
			conn = SfDBResource.getConnection();
			StringBuffer bf = new StringBuffer();
			/**
			 * 插入历史表
			 */
			Statement stmt = conn.createStatement();
			bf.delete(0, bf.length());
			bf.append(" INSERT INTO SBDB.SB_JL_QYQSSDSBA_NSRJBXXB_HIS ")
					.append("(xh,nsrjsjdm,nsrsbh,nsrmc,version,SSJJLX,SSJJLXMC,LXDH,"
							+ "JYDZ,SSHY,SSHYMC,SWJGZZJGDM,SWJGZZJGMC,CJR,CJSJ,LRR,LRSJ,XTJB,"
							+ "BBMSF,REMARK1,REMARK2,QSLLRY,TBRQ,QSBAKSRQ,QSBAJSRQ,BASHZTBS,BASHTGRQ,"
							+ "SBSHZTBS,SBSHTGRQ,JYQXJM,GDJYJS,YFDXGB,YFXGPC,YFGDQS,QTYY,SQLXDM,QSSBKSRQ,QSSBJSRQ) ")
					.append(" (select ").append(TinyTools.getXh(form.getJsjdm()))
					.append(" ,nsrjsjdm,nsrsbh,nsrmc,version,SSJJLX,"
							+ "SSJJLXMC,LXDH,JYDZ,SSHY,SSHYMC,SWJGZZJGDM,"
							+ "SWJGZZJGMC,CJR,CJSJ,LRR,LRSJ,XTJB,BBMSF,REMARK1,"
							+ "REMARK2,QSLLRY,TBRQ,QSBAKSRQ,QSBAJSRQ,BASHZTBS,BASHTGRQ,"
							+ "SBSHZTBS,SBSHTGRQ,JYQXJM,GDJYJS,YFDXGB,YFXGPC,YFGDQS,QTYY,"
							+ "SQLXDM,QSSBKSRQ,QSSBJSRQ ")
					.append(" from SBDB.SB_JL_QYQSSDSBA_NSRJBXXB t1 ")
					.append(" where  t1.nsrjsjdm = ")
					.append(SBStringUtils.getSQLStr(form.getJsjdm())).append(")");

			System.out.println("企业清算所得税-基本信息插入历史数据SQL");
			System.out.println(bf.toString());

			stmt.executeUpdate(bf.toString());
			if(stmt!=null){
				stmt.close();
			}
			
			/**
			 * 删除数据
			 */

			Statement st = conn.createStatement();

			bf.delete(0, bf.length());
			bf.append(" delete ")
					.append(" from SBDB.SB_JL_QYQSSDSBA_NSRJBXXB t1 ")
					.append(" where  t1.nsrjsjdm ='").append(form.getJsjdm())
					.append("'");

			System.out.println("企业所得税-基本信息删除SQL");
			System.out.println(bf.toString());

			st.execute(bf.toString());


			if (st != null) {
				st.close();
			}
			// 插入历史数据
			String sql = "insert into SBDB.SB_JL_QYQSSDSBA_NSRJBXXB(nsrjsjdm,"
					+ "nsrsbh,"
					+ "nsrmc,"
					+ "version,"
					+ "SSJJLX,"
					+ "SSJJLXMC,"
					+ "LXDH,"
					+ "JYDZ,"
					+ "SSHY,"
					+ "SSHYMC,"
					+ "SWJGZZJGDM,"
					+ "SWJGZZJGMC,"
					+ "CJR,"
					+ "CJSJ,"
					+ "LRR,"
					+ "LRSJ,"
					+ "XTJB,"
					+ "BBMSF,"
					+ "REMARK1,"
					+ "REMARK2,"
					+ "QSLLRY,"
					+ "TBRQ,"
					+ "QSBAKSRQ,"
					+ "QSBAJSRQ,"
					+ "BASHZTBS,"
					+ "BASHTGRQ,"
					+ "SBSHZTBS,"
					+ "SBSHTGRQ,"
					+ "JYQXJM,"
					+ "GDJYJS,"
					+ "YFDXGB,"
					+ "YFXGPC,"
					+ "YFGDQS,"
					+ "QTYY,"
					+ "SQLXDM,"
					+ "QSSBKSRQ,"
					+ "QSSBJSRQ"
					+ ") values(?,?,?,?,?,?,?,?,?,?,?,?,?,sysdate,?,sysdate,?,?,?,?,?,sysdate,sysdate,sysdate,?,sysdate,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			int i = 1;
			pst.setString(i++, form.getJsjdm());// 计算机代码
			pst.setString(i++, djsj.getSwdjzh());// 纳税人识别号
			pst.setString(i++, djsj.getNsrmc());// 纳税人名称
			pst.setString(i++, CodeConstant.QYQSSDS_VERSION_2014);// 版本号
			pst.setString(i++, djsj.getDjzclxdm());// 所属经济类型
			pst.setString(i++, djsj.getDjzclxmc());// 所属经济类型名称
			pst.setString(i++, form.getLxdh());// 联系电话
			pst.setString(i++, djsj.getJydz());// 经营地址
			pst.setString(i++, djsj.getGjbzhydm());// 所属行业
			pst.setString(i++, djsj.getGjbzhymc());// 所属行业名称
			pst.setString(i++, djsj.getSwjgzzjgdm());// 税务机关组织机构代码
			pst.setString(i++, djsj.getSwjgzzjgmc());// 税务机关组织机构名称
			pst.setString(i++, ud.yhid);// 创建人
			pst.setString(i++, ud.yhid);// 录入人
			pst.setString(i++, null);// 系统级别
			pst.setString(i++, "0101,0102,0103,0104");// 报表描述符
			pst.setString(i++, null);// 备注1
			pst.setString(i++, null);// 备注2
			pst.setString(i++, form.getQsllry());// 清算联络人员
			pst.setString(i++, "2");// 清算备案审核状态标识 1：已提交未审核，2：审核已通过，3：审核被驳回，4：撤销
			pst.setString(i++, null);// 清算申报审核状态标识 1：已提交未审核，2：审核已通过，3：审核被驳回，4：撤销
			pst.setString(i++, null);// 清算申报审核通过日期
			pst.setString(i++, form.getJyqxjm());// 经营期限届满 Y ：是；N：否
			pst.setString(i++, form.getGdjyjs());// 东会决议解散 Y ：是；N：否
			pst.setString(i++, form.getYfdxgb());// 依法吊销关闭 Y ：是；N：否
			pst.setString(i++, form.getYfxgpc());// 依法宣告破产 Y ：是；N：否
			pst.setString(i++, form.getYfgdqs());// 依法规定清算 Y ：是；N：否
			pst.setString(i++, form.getQtyy());// 其他原因 Y ：是；N：否
			pst.setString(i++, "1");// 申请类型代码，0：网上申请，1：上门申请
			pst.setString(i++, null);// 清算申报开始日期
			pst.setString(i++, null);// 清算申报结束日期

			count = pst.executeUpdate();
			if (pst != null) {
				pst.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		} finally {
			SfDBResource.freeConnection(conn);
		}

		return form;
	}

	/**
	 * doAccept接受申请
	 * 
	 * @param vo
	 *            业务参数
	 * @return 数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
	 * @throws BaseException
	 *             当其他情况都会抛出异常信息
	 */
	private Object doAccept(VOPackage vo) throws BaseException {

		QyqssdsBaForm qyqssdsBaForm = (QyqssdsBaForm) vo.getData();
		// 获取计算机代码
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		conn = SfDBResource.getConnection();
		String showSql="select NSRJSJDM from sbdb.sb_jl_qyqssdsba_nsrjbxxb  where nsrjsjdm=?";
		
		try {
			ps = conn.prepareStatement(showSql);
			ps.setString(1, qyqssdsBaForm.getJsjdm());
			rs = ps.executeQuery();	
		
		if(rs.next()){
			String jsjdm = qyqssdsBaForm.getJsjdm();
			String sql = "UPDATE SBDB.SB_JL_QYQSSDSBA_NSRJBXXB SET BASHZTBS='2',BASHTGRQ=SYSDATE WHERE NSRJSJDM=?";
					
			try {
				conn = SfDBResource.getConnection();
				ps = conn.prepareStatement(sql);
				ps.setString(1, qyqssdsBaForm.getJsjdm());
				rs = ps.executeQuery();
			} catch (SQLException e) {
				e.printStackTrace();
				throw ExceptionUtil.getBaseException(e);
			}finally{
				if(rs!=null){
					rs.close();
				}
				if(ps!=null){
					ps.close();
				}
				if(conn!=null){
					conn.close();
				}
			}
			
		}else{
			UserData ud = (UserData) vo.getUserData();
			SWDJJBSJ djsj = null;
			// 获得企业登记基本信息
			try {
				djsj = InterfaceDj.getJBSJ_New(qyqssdsBaForm.getJsjdm(), ud);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw ExceptionUtil.getBaseException(e);
			}

			int count = 0;
			try {
				//conn = SfDBResource.getConnection();
				StringBuffer bf = new StringBuffer();
				/**
				 * 插入历史表
				 */
				Statement stmt = conn.createStatement();
				bf.delete(0, bf.length());
				bf.append(" INSERT INTO SBDB.SB_JL_QYQSSDSBA_NSRJBXXB_HIS ")
						.append("(xh,nsrjsjdm,nsrsbh,nsrmc,version,SSJJLX,SSJJLXMC,LXDH,"
								+ "JYDZ,SSHY,SSHYMC,SWJGZZJGDM,SWJGZZJGMC,CJR,CJSJ,LRR,LRSJ,XTJB,"
								+ "BBMSF,REMARK1,REMARK2,QSLLRY,TBRQ,QSBAKSRQ,QSBAJSRQ,BASHZTBS,BASHTGRQ,"
								+ "SBSHZTBS,SBSHTGRQ,JYQXJM,GDJYJS,YFDXGB,YFXGPC,YFGDQS,QTYY,SQLXDM,QSSBKSRQ,QSSBJSRQ) ")
						.append(" (select ").append(TinyTools.getXh(qyqssdsBaForm.getJsjdm()))
						.append(" ,nsrjsjdm,nsrsbh,nsrmc,version,SSJJLX,"
								+ "SSJJLXMC,LXDH,JYDZ,SSHY,SSHYMC,SWJGZZJGDM,"
								+ "SWJGZZJGMC,CJR,CJSJ,LRR,LRSJ,XTJB,BBMSF,REMARK1,"
								+ "REMARK2,QSLLRY,TBRQ,QSBAKSRQ,QSBAJSRQ,BASHZTBS,BASHTGRQ,"
								+ "SBSHZTBS,SBSHTGRQ,JYQXJM,GDJYJS,YFDXGB,YFXGPC,YFGDQS,QTYY,"
								+ "SQLXDM,QSSBKSRQ,QSSBJSRQ ")
						.append(" from SBDB.SB_JL_QYQSSDSBA_NSRJBXXB t1 ")
						.append(" where  t1.nsrjsjdm = ")
						.append(SBStringUtils.getSQLStr(qyqssdsBaForm.getJsjdm())).append(")");

				System.out.println("企业清算所得税-基本信息插入历史数据SQL");
				System.out.println(bf.toString());

				stmt.executeUpdate(bf.toString());
				if(stmt!=null){
					stmt.close();
				}
				
				/**
				 * 删除数据
				 */

				Statement st = conn.createStatement();

				bf.delete(0, bf.length());
				bf.append(" delete ")
						.append(" from SBDB.SB_JL_QYQSSDSBA_NSRJBXXB t1 ")
						.append(" where  t1.nsrjsjdm ='").append(qyqssdsBaForm.getJsjdm())
						.append("'");

				System.out.println("企业所得税-基本信息删除SQL");
				System.out.println(bf.toString());

				st.execute(bf.toString());


				if (st != null) {
					st.close();
				}
				// 插入历史数据
				String sql = "insert into SBDB.SB_JL_QYQSSDSBA_NSRJBXXB(nsrjsjdm,"
						+ "nsrsbh,"
						+ "nsrmc,"
						+ "version,"
						+ "SSJJLX,"
						+ "SSJJLXMC,"
						+ "LXDH,"
						+ "JYDZ,"
						+ "SSHY,"
						+ "SSHYMC,"
						+ "SWJGZZJGDM,"
						+ "SWJGZZJGMC,"
						+ "CJR,"
						+ "CJSJ,"
						+ "LRR,"
						+ "LRSJ,"
						+ "XTJB,"
						+ "BBMSF,"
						+ "REMARK1,"
						+ "REMARK2,"
						+ "QSLLRY,"
						+ "TBRQ,"
						+ "QSBAKSRQ,"
						+ "QSBAJSRQ,"
						+ "BASHZTBS,"
						+ "BASHTGRQ,"
						+ "SBSHZTBS,"
						+ "SBSHTGRQ,"
						+ "JYQXJM,"
						+ "GDJYJS,"
						+ "YFDXGB,"
						+ "YFXGPC,"
						+ "YFGDQS,"
						+ "QTYY,"
						+ "SQLXDM,"
						+ "QSSBKSRQ,"
						+ "QSSBJSRQ"
						+ ") values(?,?,?,?,?,?,?,?,?,?,?,?,?,sysdate,?,sysdate,?,?,?,?,?,sysdate,sysdate,sysdate,?,sysdate,?,?,?,?,?,?,?,?,?,?,?)";
				PreparedStatement pst = conn.prepareStatement(sql);
				int i = 1;
				pst.setString(i++, qyqssdsBaForm.getJsjdm());// 计算机代码
				pst.setString(i++, djsj.getSwdjzh());// 纳税人识别号
				pst.setString(i++, djsj.getNsrmc());// 纳税人名称
				pst.setString(i++, CodeConstant.QYQSSDS_VERSION_2014);// 版本号
				pst.setString(i++, djsj.getDjzclxdm());// 所属经济类型
				pst.setString(i++, djsj.getDjzclxmc());// 所属经济类型名称
				pst.setString(i++, qyqssdsBaForm.getLxdh());// 联系电话
				pst.setString(i++, djsj.getJydz());// 经营地址
				pst.setString(i++, djsj.getGjbzhydm());// 所属行业
				pst.setString(i++, djsj.getGjbzhymc());// 所属行业名称
				pst.setString(i++, djsj.getSwjgzzjgdm());// 税务机关组织机构代码
				pst.setString(i++, djsj.getSwjgzzjgmc());// 税务机关组织机构名称
				pst.setString(i++, ud.yhid);// 创建人
				pst.setString(i++, ud.yhid);// 录入人
				pst.setString(i++, null);// 系统级别
				pst.setString(i++, "0101,0102,0103,0104");// 报表描述符
				pst.setString(i++, null);// 备注1
				pst.setString(i++, null);// 备注2
				pst.setString(i++, qyqssdsBaForm.getQsllry());// 清算联络人员
				pst.setString(i++, "2");// 清算备案审核状态标识 1：已提交未审核，2：审核已通过，3：审核被驳回，4：撤销
				pst.setString(i++, null);// 清算申报审核状态标识 1：已提交未审核，2：审核已通过，3：审核被驳回，4：撤销
				pst.setString(i++, null);// 清算申报审核通过日期
				pst.setString(i++, qyqssdsBaForm.getJyqxjm());// 经营期限届满 Y ：是；N：否
				pst.setString(i++, qyqssdsBaForm.getGdjyjs());// 东会决议解散 Y ：是；N：否
				pst.setString(i++, qyqssdsBaForm.getYfdxgb());// 依法吊销关闭 Y ：是；N：否
				pst.setString(i++, qyqssdsBaForm.getYfxgpc());// 依法宣告破产 Y ：是；N：否
				pst.setString(i++, qyqssdsBaForm.getYfgdqs());// 依法规定清算 Y ：是；N：否
				pst.setString(i++, qyqssdsBaForm.getQtyy());// 其他原因 Y ：是；N：否
				pst.setString(i++, "1");// 申请类型代码，0：网上申请，1：上门申请
				pst.setString(i++, null);// 清算申报开始日期
				pst.setString(i++, null);// 清算申报结束日期

				count = pst.executeUpdate();
				if (pst != null) {
					pst.close();
				}
		
			}catch (Exception e) {
				e.printStackTrace();
				throw ExceptionUtil.getBaseException(e);
			} finally {
				SfDBResource.freeConnection(conn);
			}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			SfDBResource.freeConnection(conn);
		}


		return qyqssdsBaForm;
	}

	/**
	 * doRefuse拒绝申请
	 * 
	 * @param vo
	 *            业务参数
	 * @return 数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
	 * @throws BaseException
	 *             当其他情况都会抛出异常信息
	 */
	private Object doRefuse(VOPackage vo) throws BaseException {

		QyqssdsBaForm qyqssdsBaForm = (QyqssdsBaForm) vo.getData();

		// 获取计算机代码
		String jsjdm = qyqssdsBaForm.getJsjdm();
		Map map=QyqssdsActionHelper.getShztbs(jsjdm);
		if(map.get(CodeConstant.SMSB_QYQSSDS2014_BASHZTBS).toString().equals("2")){
			throw new ApplicationException("清算备案审核状态，审核已通过，不能驳回！");
		}
		String sql = "UPDATE SBDB.SB_JL_QYQSSDSBA_NSRJBXXB SET BASHZTBS='3'  WHERE NSRJSJDM='"
				+ jsjdm+"'";
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = SfDBResource.getConnection();
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			SfDBResource.freeConnection(conn);
		}
		return qyqssdsBaForm;
	}

	/**
	 * doCancle撤销
	 * 
	 * @param vo
	 *            业务参数
	 * @return 数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
	 * @throws BaseException
	 *             当其他情况都会抛出异常信息
	 */
	private Object doCancle(VOPackage vo) throws BaseException {

		QyqssdsBaForm qyqssdsBaForm = (QyqssdsBaForm) vo.getData();
		// 获取计算机代码
		String jsjdm = qyqssdsBaForm.getJsjdm();
		String sql = "UPDATE SBDB.SB_JL_QYQSSDSBA_NSRJBXXB SET BASHZTBS='4' WHERE NSRJSJDM='"
				+ jsjdm+"'";
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = SfDBResource.getConnection();
			// 获取报表处理数据库接口
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYQSSDS);
			QyqssdsReportsDeclare qd = new QyqssdsReportsDeclare();
			QyqssdsUtil2014.setQyqssdsReport(qd, qyqssdsBaForm);

			// 循环删除客户端填写的所有表，外加新添加的分配表，
			for (int i = 0; i < CodeConstant.QYQSSDS_TABLE_ID_ALL.length; i++) {
				// 企业所得税报表内单表申明对象
				QyqssdsReportsTableDeclare table = new QyqssdsReportsTableDeclare();
				table.setTableId(CodeConstant.QYQSSDS_TABLE_ID_ALL[i]);
				table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_QYQSSDS);
				// set table
				qd.getTableContentList().put(table.getTableId(), table);
				// 调用delete方法进行数据删除
				iApp.deleteSingleTable(qd);
			}
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			SfDBResource.freeConnection(conn);
		}
		return qyqssdsBaForm;
	}

	/**
	 * doDelete删除
	 * 
	 * @param vo
	 *            业务参数
	 * @return 数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
	 * @throws BaseException
	 *             当其他情况都会抛出异常信息
	 */
	private Object doDelete(VOPackage vo) throws BaseException {

		QyqssdsBaForm qyqssdsBaForm = (QyqssdsBaForm) vo.getData();

		UserData ud = (UserData) vo.getUserData();

		Connection conn = null;
		Statement stmt = null;
		Statement st = null;
		conn = SfDBResource.getConnection();
		// 获取计算机代码
		String jsjdm = qyqssdsBaForm.getJsjdm();
		//by zhangj
		String sbshztbs=QyqssdsActionHelper.getShztbs(jsjdm).get(CodeConstant.SMSB_QYQSSDS2014_SBSHZTBS).toString();
		if("2".equals(sbshztbs)){//清算申报审核状态标示，审核已通过
			throw new ApplicationException("清算申报审核状态，审核已通过，请先废除清算申报！");
			//throw ExceptionUtil.getBaseException(new Exception(),"清算申报审核状态，审核已通过，请先废除清算申报！");
			//JOptionPane.showMessageDialog( null, "清算申报审核状态，审核已通过，请先废除清算申报！");
		}
			
		/**
		 * 删除数据
		 */
		StringBuffer bf = new StringBuffer();

		String sql = "DELETE FROM SBDB.SB_JL_QYQSSDSBA_NSRJBXXB  WHERE NSRJSJDM='"
				+ jsjdm+"'";
		try {

			// 获取报表处理数据库接口
			IAppAccess iApp = AppAccessFactory.getAInstance(conn,
					AppAccessFactory.ACCESS_MODEL_APP_QYQSSDS);
			QyqssdsReportsDeclare qd = new QyqssdsReportsDeclare();
			QyqssdsUtil2014.setQyqssdsReport(qd, qyqssdsBaForm);

			// 循环删除客户端填写的所有表，外加新添加的分配表，
			for (int i = 0; i < CodeConstant.QYQSSDS_TABLE_ID_ALL.length; i++) {
				// 企业所得税报表内单表申明对象
				QyqssdsReportsTableDeclare table = new QyqssdsReportsTableDeclare();
				table.setTableId(CodeConstant.QYQSSDS_TABLE_ID_ALL[i]);
				table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_QYQSSDS);
				// set table
				qd.setTableContentList(new HashMap());
				qd.getTableContentList().put(table.getTableId(), table);
				// 调用delete方法进行数据删除
				iApp.deleteSingleTable(qd);
				iApp.updateCheckStatus(qd, "");
			}
			stmt = conn.createStatement();
			bf.delete(0, bf.length());
			/*---修改历史表中录入人为当前操作人，修改备案审核状态标识为6（作废）---*/
			/*--modified by huohb 2014-06-18--*/
			// 插入历史数据
			bf.append(" INSERT INTO SBDB.SB_JL_QYQSSDSBA_NSRJBXXB_HIS ")
					.append("(xh,nsrjsjdm,nsrsbh,nsrmc,version,SSJJLX,SSJJLXMC,LXDH,"
							+ "JYDZ,SSHY,SSHYMC,SWJGZZJGDM,SWJGZZJGMC,CJR,CJSJ,LRR,LRSJ,XTJB,"
							+ "BBMSF,REMARK1,REMARK2,QSLLRY,TBRQ,QSBAKSRQ,QSBAJSRQ,BASHZTBS,BASHTGRQ,"
							+ "SBSHZTBS,SBSHTGRQ,JYQXJM,GDJYJS,YFDXGB,YFXGPC,YFGDQS,QTYY,SQLXDM,QSSBKSRQ,QSSBJSRQ) ")
					.append(" (select ")
					.append(TinyTools.getXh(jsjdm))
					.append(",")
					.append(" nsrjsjdm,nsrsbh,nsrmc,version,SSJJLX,"
							+ "SSJJLXMC,LXDH,JYDZ,SSHY,SSHYMC,SWJGZZJGDM,"
							+ "SWJGZZJGMC,CJR,CJSJ,'"+ud.getYhid()+"',LRSJ,XTJB,BBMSF,REMARK1,"
							+ "REMARK2,QSLLRY,TBRQ,QSBAKSRQ,QSBAJSRQ,'6',BASHTGRQ,"
							+ "SBSHZTBS,SBSHTGRQ,JYQXJM,GDJYJS,YFDXGB,YFXGPC,YFGDQS,QTYY,"
							+ "SQLXDM,QSSBKSRQ,QSSBJSRQ ")
					.append(" from SBDB.SB_JL_QYQSSDSBA_NSRJBXXB t1 ")
					.append(" where  t1.nsrjsjdm = ")
					.append(SBStringUtils.getSQLStr(jsjdm)).append(" ) ");

			System.out.println("企业清算所得税-基本信息插入历史数据SQL");
			System.out.println(bf.toString());
			/*--先插入历史表再删除数据--*/
			stmt.executeUpdate(bf.toString());
			// 删除数据
			st = conn.createStatement();
			st.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			SfDBResource.freeConnection(conn);
		}
		return qyqssdsBaForm;
	}
}
