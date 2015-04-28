package com.lscdz.qysds.common.service.qysdsCheck.processor;

import java.sql.Connection;
import com.lscdz.qysds.common.service.qysdsCheck.IQysdsCheckServer;
import com.lscdz.qysds.common.service.qysdsCheck.bo.CheckBean;
import yangjian.frame.util.FrameException;

/**
 * 企业所得税鉴定服务类
 * @description : 
 * @author zhangj
 * @version 1.0
 * @time 2014-12-18 下午02:27:34
 */
public class QysdsCheckServer implements IQysdsCheckServer{
	/**
	 * 企业所得税鉴定
	 * 使用之前需要在checkbean中做数据准备： 必须赋值的项：jsjdm；可选项：setCheckQysds，setCheckJdlx，setCheckZfba，默认为false检验
	 * 如果isCheckJdlx为true则需要设置setJdlxCheckStyle(选择使用sknd还是税款所属日期起止来判断)，
	 * 如果是税款所属日期起止可以调用checkBean.createZgrqByCurrenttimeY()根据当前日期生成
	 */
	@Override
	public String check(Connection conn,CheckBean checkBean)throws FrameException{
		String jdlx="";
		//1.检验清算期，清算期结束不能申报，进入清算期仍然可以申报
		if(checkBean.isCheckQsq()){
			new IsInQsqObserver().update(conn,checkBean);
		}
		//2.征管范围鉴定
		if(checkBean.isCheckJdlx()){
			new CheckJdlxObserver().update(conn,checkBean);
		}
		//3.检验总分备案
		if(checkBean.isCheckZfba()){
			new CheckZfbaObserver().update(conn,checkBean);
		}
		jdlx=checkBean.getJdlx();
		return jdlx;
	}

}
