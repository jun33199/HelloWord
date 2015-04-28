package com.lscdz.qysds.application.qysdsjb2014.czzsjb.gdzcjszj.helper;


import yangjian.frame.util.FrameException;
import com.lscdz.qysds.application.qysdsjb2014.QysdsJb2014Contant;
import com.lscdz.qysds.application.qysdsjb2014.base.helper.QysdsjbBaseHelper;
import com.lscdz.qysds.application.qysdsjb2014.base.vo.QysdsjbBaseVo;
import com.lscdz.qysds.application.qysdsjb2014.base.vo.ReportVo;
import com.lscdz.qysds.common.service.qysds.xml.ReportsInterface;
/**
 * 固定资产加速折旧dao类
 * @description : 
 * @author zhangj
 * @version 1.0
 * @time 2015-4-22 上午11:31:05
 */
public class CzzsjbGdzcjszjHelper extends QysdsjbBaseHelper{
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
		super.query(qysdsjbBaseVo);
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
		reportVo.setTid(QysdsJb2014Contant.TABLE_ID_GDZCJSZJYJQK_2014);
		reportVo.setTname(QysdsJb2014Contant.TABLE_NAME_GDZCJSZJYJQK_2014);
		reportVo.setReportData(qysdsjbBaseVo.getReportData());
		reportVo.setReportType(ReportsInterface.REPROTTYPE_REPORTDATA);
		return reportVo;
	}


}
