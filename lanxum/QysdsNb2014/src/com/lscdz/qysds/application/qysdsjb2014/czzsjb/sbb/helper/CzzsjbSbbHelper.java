package com.lscdz.qysds.application.qysdsjb2014.czzsjb.sbb.helper;

import java.sql.Connection;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Map;

import yangjian.frame.util.FrameException;
import yangjian.frame.util.ResourceManager;


import com.lscdz.qysds.application.qysdsjb2014.QysdsJb2014Contant;
import com.lscdz.qysds.application.qysdsjb2014.base.helper.QysdsjbBaseHelper;
import com.lscdz.qysds.application.qysdsjb2014.base.util.QysdsBaseUtil;
import com.lscdz.qysds.application.qysdsjb2014.base.vo.QysdsjbBaseVo;
import com.lscdz.qysds.application.qysdsjb2014.base.vo.ReportVo;
import com.lscdz.qysds.application.qysdsjb2014.czzsjb.sbb.vo.CzzssdsjbSbbVo;
import com.lscdz.qysds.common.service.ServiceManager;
import com.lscdz.qysds.common.service.QysdsInfo.bo.QysdsSet;
import com.lscdz.qysds.common.service.QysdsInfo.bo.Zsfs;
import com.lscdz.qysds.common.service.djjbsj.bo.Djjbsj;
import com.lscdz.qysds.common.service.qysds.util.DateUtils;
import com.lscdz.qysds.common.service.qysds.xml.ReportsInterface;
/**
 * 查账征收申报表dao类
 * @description : 
 * @author zhangj
 * @version 1.0
 * @time 2015-4-22 上午11:31:42
 */
public class CzzsjbSbbHelper extends QysdsjbBaseHelper{

	// 企业所得税税率
	private static final String QYSDS_SL = "0.25";
	
	@Override
	public void delete(QysdsjbBaseVo qysdsjbBaseVo)throws FrameException{
		super.delete(qysdsjbBaseVo);
	}
	@Override
	public void save(QysdsjbBaseVo qysdsjbBaseVo) throws FrameException{
		qysdsjbBaseVo.setZsfsdm("03");//查账征收
		super.save(qysdsjbBaseVo);	
	}
	@Override
	public void query(QysdsjbBaseVo qysdsjbBaseVo) throws FrameException{
		CzzssdsjbSbbVo czzssdsjbSbbVo=(CzzssdsjbSbbVo)qysdsjbBaseVo;
		Connection conn = null;
		try {		
		// 获取税款所属季度
		String jd = QysdsBaseUtil.preQuarter(DateUtils.getDateTime(czzssdsjbSbbVo.getSkssjsrq()));
		String nd = czzssdsjbSbbVo.getSkssksrq().substring(0, 4);
		// 设置季度
		czzssdsjbSbbVo.setQh(jd);
		// 设置年度
		czzssdsjbSbbVo.setSknd(nd);
		// 设置form中其它所需属性
		conn = ResourceManager.getConnection();
//		conn = ConnFactoryTest.getConnect();
		czzssdsjbSbbVo = (CzzssdsjbSbbVo) QysdsBaseUtil.queryDjxxByInterfaceDJ(conn, czzssdsjbSbbVo);
		// 获得企业登记基本信息
		Djjbsj djsj= ServiceManager.getInstance().getDjjbsjService().query(conn, czzssdsjbSbbVo.getJsjdm());
		
		Timestamp skssksrq = DateUtils.getDateTime(czzssdsjbSbbVo.getSkssksrq());
		Timestamp kydjrq=djsj.getKydjrq();
		if(skssksrq.compareTo(kydjrq)<0){
			czzssdsjbSbbVo.setIsXky("Y");
        }else{
        	czzssdsjbSbbVo.setIsXky("N");
        }
		QysdsBaseUtil qysdsUtil2014=new QysdsBaseUtil();
		//获取是否是新开户  是：Y 否：N 
		czzssdsjbSbbVo.setSfxkh(qysdsUtil2014.getSfxkh(czzssdsjbSbbVo.getSknd(), djsj, czzssdsjbSbbVo.getQh()));
		//获取税款所属期所在年度上一年度征收方式
		czzssdsjbSbbVo.setSyndZsfsdm(qysdsUtil2014.getSyndZsfsDm(czzssdsjbSbbVo.getJsjdm(), czzssdsjbSbbVo.getSknd(),czzssdsjbSbbVo.getQh()));
		//获取税款所属期所在年度上一年度的汇算清缴申报信息 主表行9、25 附表五45、46、47
		Map map=qysdsUtil2014.getSyndHsqjSbxx(czzssdsjbSbbVo.getSyndZsfsdm(),czzssdsjbSbbVo.getJsjdm(), czzssdsjbSbbVo.getSknd(),czzssdsjbSbbVo.getQh());
		czzssdsjbSbbVo.setSyndZbh6(map.get("syndZbh6").toString());
		czzssdsjbSbbVo.setSyndZbh25(map.get("syndZbh25").toString());
		czzssdsjbSbbVo.setSyndFb5jyjg(map.get("syndFb5jyjg").toString());
		this.getHdxx(czzssdsjbSbbVo);
		/* 征收方式 */
		String zsfs = czzssdsjbSbbVo.getZsfsdm();

		System.out.println(czzssdsjbSbbVo.getJsjdm() + "的征收方式代码：" + zsfs);

		if (zsfs == null || (zsfs != null && zsfs.equals(""))) {
			throw new FrameException("没有查询到该企业的征收方式认定信息，请认定后再进行申报录入！");
		}
		if (!(QysdsJb2014Contant.ZSFSDM_CZZS.equals(zsfs))) {
			throw new FrameException("该企业已认定为核定征收户，不能在此录入，请录入相应季度申报表！");
		}
		} catch (FrameException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new FrameException("查询数据出错！");
		}
		czzssdsjbSbbVo.setReportData(super.queryReportData(this.getReportVo(czzssdsjbSbbVo)));
	}
	@Override
	protected ReportVo getReportVo(QysdsjbBaseVo qysdsjbBaseVo) {
		ReportVo reportVo=new ReportVo();
		reportVo.setBbqlx(qysdsjbBaseVo.getBbqlx());
		reportVo.setJsjdm(qysdsjbBaseVo.getJsjdm());
		reportVo.setNd(qysdsjbBaseVo.getSknd());
		reportVo.setQh(qysdsjbBaseVo.getQh());
		reportVo.setSkssjsrq(qysdsjbBaseVo.getSkssjsrq());
		reportVo.setSkssksrq(qysdsjbBaseVo.getSkssksrq());
		reportVo.setTid(QysdsJb2014Contant.TABLE_NAME_CZQYSDSJB_2008);
		reportVo.setTname(QysdsJb2014Contant.TABLE_NAME_CZQYSDSJB_2008);
		reportVo.setReportData(qysdsjbBaseVo.getReportData());
		reportVo.setReportType(ReportsInterface.REPROTTYPE_REPORTDATA);
		return reportVo;
	}


	/**
	 * 取从一率和企业征税类型,用于页面校验
	 *
	 * @param form
	 * @throws BaseException
	 */
	private void getHdxx(CzzssdsjbSbbVo form) throws FrameException {
		Connection conn=null;
		String qyzssllx = "3"; // 缺省为正常申报

		// 企业征税的税率 相对于企业征税的税率类型
		String qyzssl = QYSDS_SL;

		// 应纳所得税额
		String ynsdse = "0.00";
		// 定额征收税额
		String dezsse = "0.00";

		// 当前时间
		// Timestamp sbrq = new Timestamp(System.currentTimeMillis());
		// 从申报页面取得申报日期和税款所属日期
		Timestamp sbrq = DateUtils.getDateTime(form.getSkssjsrq());

		// Map getsbjd = this.quarterSkssrq(sbrq);
		Timestamp skssksrq = DateUtils.getDateTime(form.getSkssksrq());
		Timestamp skssjsrq = DateUtils.getDateTime(form.getSkssjsrq());

		System.out.println(form.getJsjdm() + "sbrq：" + sbrq);
		System.out.println(form.getJsjdm() + "skssksrq：" + skssksrq);
		System.out.println(form.getJsjdm() + "skssjsrq：" + skssjsrq);
        System.out.println(form.getJsjdm() + "qh：" + form.getQh());

//		ServiceProxy proxy = new ServiceProxy();

		String bblx = form.getBbqlx();
		String jsjdm = form.getJsjdm();

		// 查询税费接口
		QysdsSet qysdsSet = null;

		// 数据库连接对象
	//	Connection conn = null;
	//	Statement st = null;
	//	ResultSet rs = null;
		// 减免资格标识
		boolean jm_type = false;

		try {
			conn=ResourceManager.getConnection();
			if (bblx.equals(QysdsJb2014Contant.CREPORTS_IBBQLX_TYPE_YEAR)) {
				qysdsSet = ServiceManager.getInstance().getQysdsInfoServer().getQysdsInfo(conn,jsjdm, sbrq, skssksrq, skssjsrq,
						QysdsJb2014Contant.SFGL_QYSDS_BBFS_NB);
			} else if (bblx.equals(QysdsJb2014Contant.CREPORTS_IBBQLX_TYPE_QUARTOR)) {
				/* 如果为第四季度，或取企业所得税认定信息时按年报来获取 */
				if (form.getQh() == null
						|| (form.getQh() != null && form.getQh().trim().equals(
								""))) {
					/* 期号不能为空，如果为空抛出异常 */
					throw new FrameException("系统发生异常，期号为空，请与系统管理员联系！");
				}
				if ("4".equals(form.getQh())) {
					qysdsSet = ServiceManager.getInstance().getQysdsInfoServer().getQysdsInfo(conn,jsjdm, sbrq, skssksrq,
							skssjsrq, QysdsJb2014Contant.SFGL_QYSDS_BBFS_NB);
				} else {
					qysdsSet = ServiceManager.getInstance().getQysdsInfoServer().getQysdsInfo(conn,jsjdm, sbrq, skssksrq,
							skssjsrq, QysdsJb2014Contant.SFGL_QYSDS_BBFS_JB);
                    //重载Zsfs
//                    Zsfs zsfs = util.getZsfsInfo(jsjdm, skssjsrq);
//                    qysdsSet.setZsfs(zsfs);
				}
			}

		} catch (FrameException e) {
			e.printStackTrace();
			throw new FrameException(e.getMessage());
		}

		// 1、查询企业征收方式
		Zsfs zsfs = qysdsSet.getZsfs();
		if (zsfs != null) {
			form.setZsfsdm(zsfs.getZsfsdm() == null ? QysdsJb2014Contant.ZSFSDM_CZZS
					: zsfs.getZsfsdm());
		} else {
			form.setZsfsdm(QysdsJb2014Contant.ZSFSDM_CZZS);
		}

		/* 高新技术企业认定日期 */
		Date gxqyrdrq = qysdsSet.getGxjsqy();

		// 初值
		form.setCyl("0");
		form.setXzqy("0");
		form.setDezsse("0.00");
		form.setYbjmsl("0.00");

		if (zsfs != null) {
			String zsfsdm = zsfs.getZsfsdm();
			if (zsfsdm.equals(QysdsJb2014Contant.ZSFSDM_CYLZS)) {
				if (gxqyrdrq == null) {
					// 纯益率征收
					qyzssllx = "2";
				} else {
					// 高新技术和纯益率企业
					qyzssllx = "5";
					qyzssl = "0.15";
					form.setJmzg("1"); // 有减免资格
				}
				form.setCyl(zsfs.getCyl());
			} else if (zsfsdm.equals(QysdsJb2014Contant.ZSFSDM_DEZS)) {
				// 定额征收
				qyzssllx = "4";
				// 此时本字段代表企业核定税额
				// ynsdse = zsfs.getDe();
				dezsse = zsfs.getDe();
				form.setDezsse(dezsse);
			}
		}

		// 2、查询是否是高新技术企业
		if (gxqyrdrq != null) {
			if (zsfs != null
					&& zsfs.getZsfsdm().equals(QysdsJb2014Contant.ZSFSDM_CYLZS)) {
				// 高新技术和纯益率企业
				qyzssllx = "5";
			} else {
				// 类型为高新技术企业
				qyzssllx = "1";
			}
			qyzssl = "0.15";
			form.setJmzg("1"); // 有减免资格
		} else if (form.getSsjjlx().equals(QysdsJb2014Contant.JITIQIYE_CODE)) {
			// 判断是否乡镇企业，“1”为乡镇企业，“0”为不是乡镇企业
			if (qysdsSet.isXzqy()) {
				form.setXzqy("1");
				form.setJmzg("1"); // 有减免资格
			}
		}

		if (!(form.getXzqy() != null && form.getXzqy().equals("1"))
				&& qysdsSet.getYbjmsl() != null) {
			// 非乡镇企业的减免情况
			form.setJmzg("1"); // 有减免资格
			DecimalFormat ft = new DecimalFormat("0.00");
			form.setYbjmsl(ft.format(qysdsSet.getYbjmsl()));
		}
		form.setQyzslx(qyzssllx);
		form.setSysl(qyzssl);
	}

}
