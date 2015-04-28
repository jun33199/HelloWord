package com.lscdz.qysds.application.qysdsnb2014.helper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import yangjian.frame.dao.FrameCommonAccess;
import yangjian.frame.util.FrameException;
import yangjian.frame.util.ResourceManager;

import com.lscdz.ca.CAManagerLocator;
import com.lscdz.ca.SecureManager;
import com.lscdz.hlwdsj.hxzgframe.Yh;
import com.lscdz.qysds.application.qysdsnb2014.util.QysdsNb2014Util;
import com.lscdz.qysds.application.qysdsnb2014.vo.request.UploadQysdsDataReq;
import com.lscdz.qysds.application.qysdsnb2014.vo.response.QysdsNbResponse;
import com.lscdz.qysds.common.QysdsNbConstant;
import com.lscdz.qysds.common.service.ServiceManager;
import com.lscdz.qysds.common.service.qysds.bo.CheckResult;
import com.lscdz.qysds.common.service.qysds.bo.qysds.Jbxx;
import com.lscdz.qysds.common.service.qysds.bo.qysds.QysdsReportsDeclare;
import com.lscdz.qysds.common.service.qysds.bo.qysds.QysdsReportsItemDeclare;
import com.lscdz.qysds.common.service.qysds.bo.qysds.QysdsReportsTableDeclare;
import com.lscdz.qysds.common.service.qysds.check.Checker;
import com.lscdz.qysds.common.service.qysds.check.CheckerFactory;
import com.lscdz.qysds.common.service.qysds.persistent.AppAccessFactory;
import com.lscdz.qysds.common.service.qysds.persistent.access.IAppAccess;
import com.lscdz.qysds.common.service.qysds.xml.ChangeDeclare;
import com.lscdz.qysds.common.service.qysds.xml.bs.ReadReports;
import com.lscdz.qysds.common.service.qysds.xml.vo.data.APPS;
import com.lscdz.qysds.common.util.QysdsHelperUtil;
import com.lscdz.qysds.common.util.StringUtil;
import com.lscdz.qysds.common.util.ZipUtil;

public class UploadQysdsDataHelper {
	@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
	public static QysdsNbResponse upload(UploadQysdsDataReq request,Yh userData) {

		QysdsHelperUtil qysdsHelperUtil = new QysdsHelperUtil();

		// 取得证书、登陆方式、签名值
		String reportType = request.getReportType(); // 报表类型
//		String userCert = request.getCertificate(); // 证书
		String idiographData = request.getIdiographData(); // 签名值
//		int loginType = request.getLoginType(); // 登录类型
		String reportData = request.getReportData(); // 报表数据
		String aid = request.getAID(); // AID
		String nd = request.getNd(); // 年度
		// String jsjdm = request.getJsjdm(); // 计算机代码
		String jmlx = request.getJmlx() == null ? "" : request.getJmlx(); // 减免类型
		String ckzd = request.getCkzd() == null ? "" : request.getJmlx(); // 财会制度
		String gzglxs = request.getGzglxs() == null ? "" : request.getGzglxs(); // 工资管理形式

		Connection conn = null;
		StringBuffer sql = new StringBuffer();
		Statement st = null;
		ResultSet rs = null;

		String dzyjQmhz = new String(); // 电子原件签名回执信息String

		Jbxx jbxx = new Jbxx();

		// 记录上传报表的企业所得税年报数量
		int num_sdsReports = 0;

		// 从基本信息表中获得
		String swjgzzjgdm = "";
		String qxdm = "";

		// 定义返回变量
		QysdsNbResponse qysdsResponse = new QysdsNbResponse();
		qysdsResponse.setJsjdm(request.getJsjdm());
		// 数据处理
		try {

			int nowYear = Integer.parseInt((new SimpleDateFormat("yyyy")).format(new java.util.Date()));

			// 只能上传本征期的数据（当前年度的前一年）
			if (Integer.parseInt(nd) != (nowYear - 1)) {
				qysdsResponse.setErrorNo(QysdsNbConstant.ERROR_TYPE_SYSTEMERROR);
				qysdsResponse.setErrorXX("上传失败，您只能上传本征期的申报数据！");
				return qysdsResponse;
			}

			// 对上传的报文数据进行解压缩后再处理

			String decompressReportData = ZipUtil.decompress(ZipUtil.base64Tocompress(reportData));

			// 获取数据库连接
			conn = ResourceManager.getConnection();

			/**
			 * 判断当前是否是征期
			 */
			try {
				sql.delete(0, sql.length());
				sql.append("SELECT PROPERTYVALUE from sbdb.sb_jl_properties where propertyname = 'WSSB_CZZSQYNB_ZQRL_MONTH_01'");
				st = conn.createStatement();
				rs = st.executeQuery(sql.toString());
				String zq = "";
				while (rs.next()) {
					zq = rs.getString("PROPERTYVALUE");
				}
//				if (!StringUtil.withinZq(zq, new java.util.Date())) {
//					qysdsResponse.setErrorNo(QysdsNbConstant.ERROR_TYPE_SYSTEMERROR);
//					qysdsResponse.setErrorXX("目前不是征期，不能上传数据");
//					return qysdsResponse;
//				}
				rs.close();
				st.close();
			} catch (Exception ex) {
				ex.printStackTrace();
				qysdsResponse.setErrorNo(QysdsNbConstant.ERROR_TYPE_SYSTEMERROR);
				qysdsResponse.setErrorXX("上传失败，服务器忙，获取征期信息错误，请您稍后再上传申报表数据！");
				return qysdsResponse;
			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
					rs = null;
				}
				if (st != null) {
					try {
						st.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
					st = null;
				}
			}

			/**
			 * 获得基本信息并判断上传用户减免类型、财会制度、工资管理形式与服务器信息是否一致
			 */
			try {
				// 比对错误返回信息
				StringBuffer returnMeg = new StringBuffer("您本地的");
				// 取得纳税人基本信息
				sql.delete(0, sql.length());
				sql.append("SELECT * FROM SBDB.SB_JL_QYSDS_NSRJBXXB_2014 WHERE JSJDM = '");
				sql.append(qysdsResponse.getJsjdm());
				sql.append("' AND ND = '");
				sql.append(nd);
				sql.append("'");
				// System.out.println("获取纳税人核定信息sql = " + sql.toString());
				st = conn.createStatement();
				rs = st.executeQuery(sql.toString());

				if (rs.next()) {

					// 如报表id,例如“1;3;4;5”
					jbxx.setBbmsf((rs.getString("BBMSF") == null ? "" : rs.getString("BBMSF")));
					// 财会制度
					jbxx.setCkzd(QysdsNb2014Util.getCkzd(rs.getString("SYKJZZHKJZZ"), rs.getString("QTSYKJZZHKJZZ")));
					// 工资管理形式
//					jbxx.setGzglxs((rs.getString("GZGLXS") == null ? "" : rs.getString("GZGLXS")));
//					// 减免类型
//					jbxx.setJmlx((rs.getString("JMLX") == null ? "" : rs.getString("JMLX")));
//					// 经营地址
//					jbxx.setJydz((rs.getString("JYDZ") == null ? "" : rs.getString("JYDZ")));
					// 纳税人计算机代码
					jbxx.setJsjdm((rs.getString("JSJDM") == null ? "" : rs.getString("JSJDM")));
					// 纳税人名称
					jbxx.setNsrmc((rs.getString("NSRMC") == null ? "" : rs.getString("NSRMC")));
					// 所属行业
					jbxx.setSshy((rs.getString("SSHY") == null ? "" : rs.getString("SSHY")));
					// 所属经济类型-登记注册类型
//					jbxx.setSsjjlx((rs.getString("SSJJLX") == null ? "" : rs.getString("SSJJLX")));
					// 系统级别
//					jbxx.setXtjb((rs.getString("XTJB") == null ? "" : rs.getString("XTJB")));
					// 企业所得税征收方式
//					jbxx.setZsfs((rs.getString("ZSFS") == null ? "" : rs.getString("ZSFS")));
					// 联系电话
//					jbxx.setLxdh((rs.getString("LXDH") == null ? "" : rs.getString("LXDH")));

					swjgzzjgdm = (rs.getString("SWJGZZJGDM"));
					qxdm = (rs.getString("SWJGZZJGDM").substring(0, 2));

					// // 财会制度
					// String dbckzd = rs.getString("CKZD") == null ? "" :
					// rs.getString("CKZD");
					// // 工资管理形式
					// String dbgzglxs = rs.getString("GZGLXS") == null ? ""
					// : rs.getString("GZGLXS");
					// // 减免类型
					// String dbjmlx = rs.getString("JMLX") == null ? "" :
					// rs.getString("JMLX");

					// System.out.println("获取纳税人基本核定信息jmlx:"+jmlx+";"+jbxx.getJmlx());
					// System.out.println("获取纳税人基本核定信息gzglxs:"+gzglxs+";"+jbxx.getGzglxs());
					// System.out.println("获取纳税人基本核定信息ckzd:"+ckzd+";"+jbxx.getCkzd());

//					if (!jmlx.equals(jbxx.getJmlx())) {
//						// 判断减免类型
//						returnMeg.append("减免类型,");
//					}
//					if (!gzglxs.equals(jbxx.getGzglxs())) {
//						// 工资管理形式
//						returnMeg.append("工资管理形式,");
//					}
//					if (!ckzd.equals(jbxx.getCkzd())) {
//						// 财会制度
//						returnMeg.append("财会制度,");
//					}
					// 返回信息以半角逗号为结尾则表示本地信息与服务器信息有不匹配之处,返回错误信息
//					if (returnMeg.toString().endsWith(",")) {
//						qysdsResponse.setErrorNo(QysdsNbConstant.ERROR_TYPE_CHECKERROR);
//						qysdsResponse.setErrorXX(returnMeg.substring(0,returnMeg.length() - 1)+ "与服务器的信息不一致，请您核对您的填写核定信息并将本地信息初始化为最新的填写核定信息!");
//						return qysdsResponse;
//					}
				}
//				else{
//					qysdsResponse.setErrorNo(QysdsNbConstant.ERROR_TYPE_SYSTEMERROR);
//					qysdsResponse.setErrorXX("上传失败，获取不到纳税人基本核定信息！");
//					return qysdsResponse;
//				}

			} catch (Exception ex) {
				// System.out.println("获取纳税人基本核定信息错误!");
				ex.printStackTrace();
				qysdsResponse.setErrorNo(QysdsNbConstant.ERROR_TYPE_SYSTEMERROR);
				qysdsResponse.setErrorXX("上传失败，服务器忙，获取纳税人基本核定信息错误，请您稍后再上传申报表数据！");
				return qysdsResponse;
			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
					rs = null;
				}
				if (st != null) {
					try {
						st.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
					st = null;
				}
			}

			/**
			 * 如果是证书登陆，验证数据,签名加密
			 */
			// if (loginType == QysdsNbConstant.LOGIN_TYPE_CA) {
			//
			// try {
			// try {
			// // 验证签名
			// SecurityWareService se = SecurityWareService
			// .getInstance();
			// // System.out.println("reportData:"
			// // + decompressReportData);
			// // System.out
			// // .println("idiographData:" + idiographData);
			// // System.out.println("userCert:" + userCert);
			// if (!se.SignedData_Verify(decompressReportData,
			// idiographData, userCert)) {
			// qysdsResponse
			// .setErrorNo(QysdsNbConstant.ERROR_TYPE_LOGINERROR);
			// qysdsResponse.setErrorXX("上传失败，无效的证书，请安装正确的证书");
			// return qysdsResponse;
			// }
			//
			// } catch (SecurityEngineException ex) {
			// ex.printStackTrace();
			// qysdsResponse
			// .setErrorNo(QysdsNbConstant.ERROR_TYPE_SYSTEMERROR);
			// qysdsResponse
			// .setErrorXX("上传失败，服务器忙，底层引擎错误，请您稍后再上传申报表数据！");
			// return qysdsResponse;
			// } catch (VerifyException ex) {
			// ex.printStackTrace();
			// qysdsResponse
			// .setErrorNo(QysdsNbConstant.ERROR_TYPE_SYSTEMERROR);
			// qysdsResponse.setErrorXX("上传失败，验证签名错误，请您确认您的证书的合法性！");
			// return qysdsResponse;
			// }
			// /**
			// * 保存签名信息
			// */
			// // 填写电子原件信息
			// String swjgzzggdm = qysdsResponse.getSwjgzzjgdm();
			// SecureManager sm = null;
			// sm = CAManagerLocator.getBJCASecureManager(1);
			// DzyjsjVO dzyj = new DzyjsjVO();
			// dzyj.setDzyj(decompressReportData);
			// dzyj.setDzyjqm(idiographData);
			// dzyj.setFwqzsxlh(sm.getServerCert().getZsxlh());
			// dzyj.setJsjdm(qysdsResponse.getJsjdm());
			// dzyj.setQxdm(swjgzzggdm.substring(0, 2));
			// dzyj.setSwjgzzjgdm(swjgzzggdm);
			// dzyj.setZsxlh(request.getZsxlh());
			// dzyj.setJssj(new Timestamp(System.currentTimeMillis()));
			// String hz = null;
			// String jssj = null;
			//
			// // 签发回执
			// try {
			// jssj = DateTimeUtil.timestampToString(dzyj.getJssj(),
			// "yyyyMMddHHmmss");
			// } catch (Exception e1) {
			// // TODO Auto-generated catch block
			// e1.printStackTrace();
			// qysdsResponse
			// .setErrorNo(QysdsNbConstant.ERROR_TYPE_SYSTEMERROR);
			// qysdsResponse.setErrorXX("上传失败，电字原件接收时间无法识别。");
			// return qysdsResponse;
			// }
			//
			// try {
			// hz = sm.signData(decompressReportData + jssj
			// + idiographData);
			// } catch (Exception e) {
			// e.printStackTrace();
			// qysdsResponse
			// .setErrorNo(QysdsNbConstant.ERROR_TYPE_SYSTEMERROR);
			// qysdsResponse
			// .setErrorXX("上传失败，服务器忙，服务器签发回执错误，请您稍后再上传申报表数据！");
			// return qysdsResponse;
			// }
			// //
			//
			// dzyj.setHzqm(hz);
			// // dzyj.setYwczlx(QysdsNbConstant.CA_SCHEMADM_CZZSSDSNBB);
			// // 业务操作类型利用签名原件定义的操作类型－新增
			// dzyj.setYwczlx(CAcodeConstants.YWCZLX_NEW);
			// dzyj.setYwdm(QysdsNbConstant.CA_YWLXDM_CZZSSDSNB);
			// try {
			// DzyjHelper dh = new DzyjHelper();
			// java.util.Date date = new java.util.Date();
			// String str = null;
			// SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT);
			// str = df.format(date);
			// // 生成ywid
			// String ywid = qysdsResponse.getJsjdm() + "+" + str;
			// // 保存签名信息
			// dzyj = dh.saveDzyjsj(dzyj, ywid,
			// CAcodeConstants.DADM_SB_WS_QYSDS_NB);
			//
			// dzyjQmhz = qysdsHelper.toHz(dzyj);
			//
			// // 对返回的签名回执进行压缩后再传输
			//
			// // qysdsResponse.setDzyjQmhz(dzyjQmhz);
			//
			// String compressDzyjQmhz = ZipUtil
			// .compressTobase64(ZipUtil.compress(dzyjQmhz));
			//
			// qysdsResponse.setDzyjQmhz(compressDzyjQmhz);
			//
			// } catch (Exception ex) {
			// ex.printStackTrace();
			// qysdsResponse
			// .setErrorNo(QysdsNbConstant.ERROR_TYPE_SYSTEMERROR);
			// qysdsResponse
			// .setErrorXX("上传失败，服务器忙，保存签名信息错误，请您稍后再上传申报表数据！");
			// return qysdsResponse;
			// }
			//
			// } catch (Exception ex) {
			// ex.printStackTrace();
			// qysdsResponse
			// .setErrorNo(QysdsNbConstant.ERROR_TYPE_SYSTEMERROR);
			// qysdsResponse
			// .setErrorXX("上传失败，服务器忙，验证数据及签名加密信息错误，请您稍后再上传申报表数据！");
			// return qysdsResponse;
			// }
			// }

			String deploy_environment=ResourceManager.getTokenByName("DEPLOY_ENVIRONMENT");
			//如果为外网，且为证书用户进行签名验证
			if("EXTERIOR".equals(deploy_environment) && userData.getCaflag()){
				SecureManager sm = null;
				if (sm == null)
				{
					sm = CAManagerLocator.getBJCASecureManager();
					if (sm == null)
					{
						throw new FrameException("证书初始化错误。BCU-01-0103");
					}
				}
				if(!sm.verifySignature(userData.GetCertVo().getCert(),request.getReportDataCA(),request.getIdiographDataCA())){
					 qysdsResponse.setErrorNo(QysdsNbConstant.ERROR_TYPE_LOGINERROR);
					 qysdsResponse.setErrorXX("上传失败，无效的证书，请安装正确的证书");
					 return qysdsResponse;
				}else{
					decompressReportData = ZipUtil.decompress(ZipUtil.base64Tocompress(request.getReportDataCA()));
				}
		    }
			
			
			/**
			 * 保存报表数据
			 */
			try {
				// conn = SfDBResource.getConnection();
				IAppAccess access = null;
				List declareList = null;
				/**
				 * 保存企业所得税年报报表数据
				 */
				if (aid.equals(QysdsNbConstant.APP_AID_QYSDSNB)) {
					// 保存企业所得税年报报表数据
					access = AppAccessFactory.getAInstance(conn,AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
					ReadReports rp = new ReadReports();
					// 把上传的报表数据转换成QysdsReportsDeclare的list
					declareList = ChangeDeclare.getReportDeclare((APPS) rp.readReport(reportType, decompressReportData));
					// 对所有的上传的list循环
					for (int i = 0; i < declareList.size(); i++) {
						QysdsReportsDeclare declare = (QysdsReportsDeclare) declareList.get(i);
						// 取本征期的年报数据
						if (aid.equals(declare.getAppid())&& nd.equals(declare.getSknd())&& qysdsResponse.getJsjdm().equals(declare.getNsrjsjdm())) {
							// 记录上传的年报表数量
							num_sdsReports++;

							declare.setJbxx(jbxx);

							declare.setSwjgzzjgdm(swjgzzjgdm);
							declare.setQxdm(qxdm);
							// 录入人
							declare.setLrr(userData.getYhid());
							// 申报日期
							declare.setSbrq(FrameCommonAccess.getDBDate());

							// 创建人
							declare.setCjr(userData.getYhid());

							// 录入日期
							declare.setLrrq(FrameCommonAccess.getDBDate());

							// 创建日期
							declare.setCjrq(FrameCommonAccess.getDBDate());

							// 过滤放入table数据中的空格
							declare = qysdsHelperUtil.cleanSpace(declare);

							// 调用审核接口验证数据正确

							Checker checker = CheckerFactory.getAInstance(conn,CheckerFactory.ACCESS_MODEL_APP_QYSDS);

							// 同上门方式一样从数据库中取得审核公式后审核，进行全表校验

							List listSingle = null;

							// ***********-----全表审核-----------直接读数据库的方式进行处理开始--------------------------//
							listSingle = checker.checkMain(declare,QysdsNbConstant.CREPORTS_SYSTEM_FS_SHANGMENG);

							// ***********------全表审核-----------直接读数据库的方式进行处理开始--------------------------//

							// 2009减免备案处理
							// 附表5控制项处理
							// 17为附表5附表代码,10为附表５５5代码
							// 处理附表5
							List listSingleJm = new ArrayList();
							if (declare.getVersion().equals(QysdsNbConstant.REPORT_VERSION_2009)) {

								List readList = qysdsHelperUtil.getReadOnlyHc(declare.getNsrjsjdm(),declare.getSknd());

								QysdsReportsTableDeclare fb5 = (QysdsReportsTableDeclare) declare.getTableContentList().get(QysdsNbConstant.DMFB5 + "");
								for (int j = 0; j < readList.size(); j++) {
									String fb5hc = (String) readList.get(j);
									System.out.println("fb5 hc=" + fb5hc);

									QysdsReportsItemDeclare fb5item = (QysdsReportsItemDeclare) fb5.getCellContentList().get(fb5hc);
									if (fb5item != null&& fb5item.getItemValue() != null&& !fb5item.getItemValue().equals("")) {
										// //error
										CheckResult cr = new CheckResult();
										cr.setResult(false);
										String hcms = "第" + fb5hc + "行";
										if (Integer.valueOf(fb5hc).intValue() > 47) {
											hcms = "附报资料第"
													+ (Integer.valueOf(fb5hc).intValue() - 47)
													+ "行";
										}
										cr.setResultDescription("错误行次：附表五 《税收优惠明细表》"
												+ hcms
												+ "="
												+ fb5item.getItemValue()
												+ "，目前系统中未检测到相应的备案信息，请核实。");
										cr.setCheckBy("JMBA");
										listSingleJm.add(cr);
									}

								}
							}

							// 全表审核通过
							if ((listSingle == null || listSingle.size() == 0)&& (listSingleJm == null || listSingleJm.size() == 0)) {
								// 保存报表数据
								access.save(declare);
								try {
									Timestamp t1, t2;
									t1 = FrameCommonAccess.getDBDate();

									// 插入减免表
									// this.insertJm(declare);
									// qysdsHelper.insertJmProce(declare);
//									ServiceManager.getInstance().getSfglService().insertJmProce(declare);
									t2 = FrameCommonAccess.getDBDate();
									// System.out
									// .println("插入减免申报表数据耗时："
									// + (t2.getTime() - t1
									// .getTime()));

								} catch (Exception e) {
									e.printStackTrace();
									qysdsResponse.setErrorNo(QysdsNbConstant.ERROR_TYPE_SYSTEMERROR);
									qysdsResponse.setErrorXX("服务器忙，上传的报表数据保存、插入减免申报表失败，请您稍后再上传申报表数据！！");
									return qysdsResponse;
								}

							} else {// 出错提示处理
								String resultDescriptionRe = "";

								// 审核公式处理
								if ((listSingle != null && listSingle.size() > 0)) {

									CheckResult cr;

									String resultDescription = "";
									String[] resultDescriptionSp;
									// String resultDescriptionRe = "";

									// 获得审核结果的错误信息第一条

									cr = (CheckResult) listSingle.get(0);

									resultDescription = cr.getResultDescription();
									// 换行显示
									resultDescriptionSp = StringUtil.splitBySeparator(resultDescription, "。");

									if (resultDescriptionSp != null&& resultDescriptionSp.length > 0) {

										for (int b = 0; b < resultDescriptionSp.length; b++) {
											resultDescriptionRe = resultDescriptionRe
													+ resultDescriptionSp[b]
													+ "\n";
										}
									}

									// 备案资格处理
									if ((listSingleJm != null && listSingleJm.size() > 0)) {
										resultDescriptionRe = resultDescriptionRe+ "\n下列行次填报项目数据前须到主管税务机关完成相关备案工作。\n";

										// 获得审核结果的错误信息第一条

										for (int k = 0; k < listSingleJm.size(); k++) {

											cr = (CheckResult) listSingleJm.get(k);

											resultDescription = cr.getResultDescription();
											resultDescriptionRe = resultDescriptionRe+ resultDescription + "\n";
										}

									}

								}
								qysdsResponse.setErrorNo(QysdsNbConstant.ERROR_TYPE_SYSTEMERROR);
								qysdsResponse.setErrorXX("上传的报表数据在服务器端审核未通过，请您仔细审核报表数据！\n\n注意：如果您本地数据审核通过，但仍提示服务器端审核不通过，请更新审核关系并在本地审核通过后再上传数据。\n"+ resultDescriptionRe);
								return qysdsResponse;
							}
						}
					}
					// 没有本期的年报数据
					if (num_sdsReports < 1) {
						qysdsResponse.setErrorNo(QysdsNbConstant.ERROR_TYPE_SYSTEMERROR);
						qysdsResponse.setErrorXX("上传的数据中没有包含本征期的年报数据，请您仔细审核报表数据！");
						return qysdsResponse;
					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				qysdsResponse.setErrorNo(QysdsNbConstant.ERROR_TYPE_SYSTEMERROR);
				qysdsResponse.setErrorXX("服务器忙，上传的报表数据保存失败，请您稍后再上传申报表数据！！");
				return qysdsResponse;
			}

			// System.out
			// .println("++++++++++++++++++上传的报表数据结束++++++++++++++++++++++++++");

		} catch (Exception ex) {
			// System.out.println("获取获取数据连接失败!");
			ex.printStackTrace();
			qysdsResponse.setErrorNo(QysdsNbConstant.ERROR_TYPE_SYSTEMERROR);
			qysdsResponse.setErrorXX("服务器忙，上传的报表数据保存失败，请您稍后再上传申报表数据！");
			return qysdsResponse;
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				rs = null;
			}
			if (st != null) {
				try {
					st.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				st = null;
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				conn = null;
			}
		}
		return qysdsResponse;
	}

}
