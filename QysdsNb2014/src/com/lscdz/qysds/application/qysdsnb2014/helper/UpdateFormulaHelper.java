package com.lscdz.qysds.application.qysdsnb2014.helper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import yangjian.frame.util.FrameException;
import yangjian.frame.util.ResourceManager;

import com.lscdz.qysds.application.qysdsnb2014.vo.request.UpdateFormulaReq;
import com.lscdz.qysds.application.qysdsnb2014.vo.response.QysdsNbResponse;
import com.lscdz.qysds.common.QysdsNbConstant;
import com.lscdz.qysds.common.service.qysds.xml.ReportsInterface;
import com.lscdz.qysds.common.service.qysds.xml.bs.WriteReports;
import com.lscdz.qysds.common.service.qysds.xml.vo.config.APPS;
import com.lscdz.qysds.common.service.qysds.xml.vo.config.CHECK;
import com.lscdz.qysds.common.service.qysds.xml.vo.config.CHECKS;
import com.lscdz.qysds.common.service.qysds.xml.vo.config.INPUTITEMS;
import com.lscdz.qysds.common.service.qysds.xml.vo.config.ITEM;
import com.lscdz.qysds.common.service.qysds.xml.vo.config.REPORT;
import com.lscdz.qysds.common.service.qysds.xml.vo.config.TABLE;
import com.lscdz.qysds.common.service.qysds.xml.vo.config.TABLES;
import com.lscdz.qysds.common.service.qysds.xml.vo.config.VIEWITEMS;
import com.lscdz.qysds.common.service.qysds.xml.vo.config.APP;
import com.lscdz.qysds.common.util.ZipUtil;


/**
 * 更新审核公式帮助类
 * 项目名称：QysdsNb2014   
 * 类名称：UpdateFormulaHelper   
 * 类描述：   更新审核公式帮助类
 * 创建人：wangcy 
 * 创建时间：2014-11-28 下午2:52:27   
 * 修改人：wangcy   
 * 修改时间：2014-11-28 下午2:52:27   
 * 修改备注：   
 * @version  1.0
 */
public class UpdateFormulaHelper {
	/**
	 * 更新审核公式
	 * @param request
	 * @param qysdsResponse
	 * @return
	 * @throws Exception
	 */
	public static QysdsNbResponse updateFormula(UpdateFormulaReq request,QysdsNbResponse qysdsResponse) throws FrameException {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String aid = request.getAID();// 应用id
			String activity = "1";
			String version = "";
			String appName = "";
			String bbqlx = "2";
			// 取得数据库连接
			conn = ResourceManager.getConnection();
			APPS conApps = new APPS();
			APP app = new APP();
			REPORT report = new REPORT();
			TABLES test_tables = new TABLES();
			TABLE test_table = new TABLE();
			INPUTITEMS test_inputitems = new INPUTITEMS();
			VIEWITEMS test_viewitems = new VIEWITEMS();
			ITEM test_item = new ITEM();
			CHECKS checks = new CHECKS();
			// get check
			StringBuffer sqlBuffer = new StringBuffer();
			// 获得应用最新的版本号
			sqlBuffer.delete(0, sqlBuffer.length());
			sqlBuffer.append("select t.version VERSION, t.APPNAME from dmdb.dm_jl_crp_appcode t ");
			sqlBuffer.append(" where t.appcode = ? and t.activity = '1' order by to_number(t.version) desc ");
			ps = conn.prepareStatement(sqlBuffer.toString());
			ps.setString(1, aid);
			rs = ps.executeQuery();
			if (rs.next()) {
				version = rs.getString("VERSION");
				appName = rs.getString("APPNAME");
				// 获取公式配置数据
				sqlBuffer.delete(0, sqlBuffer.length());
				sqlBuffer.append("select * from dmdb.dm_jl_crp_formulacode t ");
				sqlBuffer.append(" where t.version = ? and t.appcode = ?");
				sqlBuffer.append(" and t.activity = '1' order by to_NUMBER(t.formulacode)");
				ps = conn.prepareStatement(sqlBuffer.toString());
				ps.setString(1, version);
				ps.setString(2, aid);
				rs = ps.executeQuery();
				while (rs.next()) {
					CHECK check = new CHECK();
					// FID
					check.setFID(rs.getString("FORMULACODE"));
					// TYPE
					check.setTYPE(rs.getString("FORMULATYPE"));
					// LEVEL
					check.setLEVEL(rs.getString("FORMULALEVEL"));
					//AUTO_CALCULATE
					check.setAUTO_CALCULATE(rs.getString("AUTO_CALCULATE"));
					// CONTENT
					check.setCONTENT(new String(rs.getString("FORMULACONTENT").getBytes("UTF-8"), "UTF-8"));
					// REMARK1
					if (rs.getString("REMARK1") != null) {
						check.setREMARK1(rs.getString("REMARK1"));
					} else {
						check.setREMARK1("");
					}
					// REMARK2
					if (rs.getString("REMARK2") != null) {
						check.setREMARK2(rs.getString("REMARK2"));
					} else {
						check.setREMARK2("");
					}
					checks.addCHECK(check);
				}
				report.setCHECKS(checks);

				// init test
				test_table.setTID("999");
				test_table.setNAME("checks");
				test_table.setACTIVITY("0");
				test_table.setREPORTTYPE("0");
				test_table.setREMARK1("");
				test_table.setREMARK2("");

				test_item.setIID("999");
				test_item.setNAME("checks");
				test_item.setTYPE("0");
				test_item.setLENGTH("0");
				test_item.setDESCRIPTOR("");
				test_item.setACTIVITY("0");
				test_item.setREMARK1("");
				test_item.setREMARK2("");

				test_inputitems.addITEM(test_item);
				test_table.setINPUTITEMS(test_inputitems);

				test_viewitems.addITEM(test_item);
				test_table.setVIEWITEMS(test_viewitems);

				test_tables.addTABLE(test_table);
				report.setTABLES(test_tables);
				// init test

				report.setNAME(appName);
				report.setBBQLX(bbqlx);
				app.addREPORT(report);
				app.setAID(aid);
				app.setACTIVITY(activity);
				app.setVERSION(version);
				app.setNAME(appName);
				app.setREMARK1("");
				app.setREMARK2("");
				conApps.addAPP(app);
				// 将查询接口转换成字符串
				ReportsInterface rf = new WriteReports();
				String str_paraList = rf.getXMLStr(conApps);
				String compressParaList = ZipUtil.compressTobase64(ZipUtil.compress(str_paraList));
				qysdsResponse.setDataApps(compressParaList);
				
			} else {
				qysdsResponse.setErrorNo(QysdsNbConstant.ERROR_TYPE_NODATAFOUND);
				qysdsResponse.setErrorXX("服务器忙，获取应用最新版本号失败，请您稍后再试！");
			}
		} catch (Exception ex) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			ex.printStackTrace();
			qysdsResponse.setErrorNo(QysdsNbConstant.ERROR_TYPE_SYSTEMERROR);
			qysdsResponse.setErrorXX("服务器忙，更新最新的公式失败，请您稍后再试！");
			return qysdsResponse;
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
				if (ps != null) {
					ps.close();
					ps = null;
				}
				// ResourceManager.freeConnection(conn);
				conn.close();
			} catch (SQLException e) {
				System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				e.printStackTrace();
				throw new FrameException("sql异常" + e.getMessage());
			} catch (Exception e) {
				System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				e.printStackTrace();
				throw new FrameException("结果集或数据库关闭异常" + e.getMessage());
			}
		}
		return qysdsResponse;
	}
}
