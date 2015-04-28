package com.ttsoft.bjtax.smsb.sbzl.qysdsbbjc.processor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.model.QysdsSet;
import com.ttsoft.bjtax.sfgl.common.model.Zsfs;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.sfgl.proxy.ServiceProxy;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.sbzl.qysdsbbjc.QysdsBbjcUtil2014;
import com.ttsoft.bjtax.smsb.sbzl.qysdsbbjc.SbzllxVo;
import com.ttsoft.bjtax.smsb.sbzl.qysdsbbjc.web.QysdsBbjcForm;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2012.czzssdsnb.QysdsNb2012Util;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.QysdsNewUtil;
import com.ttsoft.bjtax.smsb.util.qysdsCheck.CheckBean;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;
/**
 * <p>
 * Title: 北京地税综合管理系统 申报征收-上门模块
 * </p>
 * <p>
 * Description:核定申报信息
 * </p>
 * 
 * @author yugw
 * @date   20140912
 */

public class QysdsBbjcProcessor implements Processor{

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
		case CodeConstant.SMSB_NEXTSTEPACTION:
				result = doNextStep(vo);
			break;
		default:
				throw new ApplicationException("用户执行了系统不支持的方法或功能.");
		}

		return result;
	}
	/**
	 * 获得登记记录的基本信息 
	 * @param vo
	 * @return qysdsbbjcForm
	 * @throws BaseException
	 */
	private Object doQuery(VOPackage vo) throws BaseException {
		QysdsBbjcForm qysdsbbjcForm = (QysdsBbjcForm) vo.getData();
		qysdsbbjcForm.setNsrmc(""); // 纳税人名称
		UserData ud = (UserData) vo.getUserData();
		
			SWDJJBSJ djsj = null;
		try {
				// 获得企业登记基本信息
				djsj = InterfaceDj.getJBSJ_New(qysdsbbjcForm.getJsjdm(), ud);
			} catch (Exception ex1) {
				ex1.printStackTrace();
				throw ExceptionUtil.getBaseException(ex1);
			}
			qysdsbbjcForm.setNsrsbh(djsj.getSwdjzh()); // 纳税人识别号
			qysdsbbjcForm.setNsrmc(djsj.getNsrmc()); // 纳税人名称
			qysdsbbjcForm.setSsjjlxdm(djsj.getDjzclxdm()); // 所属经济类型-登记注册类型代码
			qysdsbbjcForm.setSsjjlxmc(djsj.getDjzclxmc());// 所属经济类型-登记注册类型名称
			qysdsbbjcForm.setSshydm(djsj.getGjbzhydm());// 所属行业代码
			qysdsbbjcForm.setSshymc(djsj.getGjbzhymc());// 所属行业代码
			qysdsbbjcForm.setLrr(ud.getYhid());// 录入人
			qysdsbbjcForm.setCjr(ud.getYhid());// 创建人
		    System.out.println("计算机代码++++++++++++"+qysdsbbjcForm.getJsjdm());
		    return qysdsbbjcForm;
	}
	/**
	 * 初始化页面信息(加载代码表)
	 * @param vo
	 * @return qysdsbbjcForm
	 * @throws BaseException
	 */

	private Object doShow(VOPackage vo)throws BaseException {
		QysdsBbjcForm qysdsbbjcForm = (QysdsBbjcForm) vo.getData();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List list=new ArrayList();
		try {
			conn = SfDBResource.getConnection();		
			ps = conn.prepareStatement(" select T1.SBZLLXDM,T1.SBZLLXMC,T1.BBQLX,T1.ZSFS from DMDB.QYSDS_DM_SBZLLX T1");
			rs = ps.executeQuery();
			while (rs.next()) {	
				String sbzllxdm=rs.getString("SBZLLXDM");
				String sbzllxmc=rs.getString("SBZLLXMC");
				String bbqlx=rs.getString("BBQLX");
				String zsfs=rs.getString("ZSFS");
				SbzllxVo dmvo=new SbzllxVo();
				dmvo.setDm(sbzllxdm);
				dmvo.setMc(sbzllxmc);
				dmvo.setBbqlx(bbqlx);
				dmvo.setZsfs(zsfs);
				list.add(dmvo);		
			}
			System.out.println(list.size());
			qysdsbbjcForm.setQysdssbfsList(list);
	     }catch (Exception ex) {
			try {
				throw ExceptionUtil.getBaseException(ex);
			} catch (BaseException e) {
				e.printStackTrace();
			}
		} 
		finally {	
			 try {
    			 if(ps!=null){
    				 ps.close();
    			 }
    			 if(rs!=null){
    				 rs.close();
    			 }
				} catch (SQLException e) {
					e.printStackTrace();
				}
			SfDBResource.freeConnection(conn);
		}
		return qysdsbbjcForm;
	}

	/**
	 * 根据申报信息实现页面的跳转
	 * @param vo
	 * @return qysdsbbjcForm
	 * @throws BaseException
	 */
	private Object doNextStep(VOPackage vo) throws BaseException {
		QysdsBbjcForm qysdsbbjcForm = (QysdsBbjcForm) vo.getData();
		QysdsBbjcUtil2014 util=new QysdsBbjcUtil2014();
		String sbzllxdm=qysdsbbjcForm.getQysdssbfsdm();
		System.out.println("++++申报资料类型代码"+sbzllxdm);
		String jsjdm=qysdsbbjcForm.getJsjdm();
		System.out.println("++++计算机代码"+jsjdm);
		String sbnd=qysdsbbjcForm.getSbnd();
		System.out.println("++++申报年度"+sbnd);
		//获得企业所得税申报资料类型的具体信息
		util.getQysdssbzllx(sbzllxdm,qysdsbbjcForm);
		ServiceProxy proxy = new ServiceProxy();
		// 查询税费接口
		QysdsSet qysdsSet = null;
		String path=null;
		String skksrq=null;
		String skjsrq=null;		
		String zsfsdm = null; 
		String jddm=qysdsbbjcForm.getJddm();
		System.out.println("++++季度代码"+jddm);
		String bbqlx=qysdsbbjcForm.getBbqlx();
		System.out.println("++++报表期类型"+bbqlx);
		//获得税款所属日期
		skksrq=util.getKsrq(sbnd,bbqlx,jddm);
		skjsrq=util.getJsrq(sbnd, jddm, bbqlx);
		System.out.println("dqnd:"+sbnd+"-"+jddm+"-"+bbqlx);
		System.out.println("skjsrq:"+skjsrq);
		Timestamp skssksrq = QysdsNewUtil.getTimestamp(skksrq);
		Timestamp skssjsrq = QysdsNewUtil.getTimestamp(skjsrq);
		qysdsbbjcForm.setSkssksrq(skksrq);
		qysdsbbjcForm.setSkssjsrq(skjsrq);
		int nd=Integer.parseInt(sbnd);
		boolean flag=false;//判断增收代码是否匹配的标志(默认不匹配)
		//调用校验
	    CheckBean checkBean=new CheckBean();
		util.jumpCheck(nd,sbzllxdm,qysdsbbjcForm,checkBean);
		System.out.println("申报年度++++++++++++++++++++++"+nd);
		String rq=SfDateUtil.getDate();
		qysdsbbjcForm.setSbrq(SfDateUtil.getDate());
		System.out.println("申报日期++++++++++++++++++"+rq);
		qysdsbbjcForm.setSknd(sbnd);
		System.out.println("税款年度++++++++++++++++++++++"+qysdsbbjcForm.getSknd());
		// 从申报页面取得申报日期
		Timestamp sbrq = QysdsNb2012Util.getNxetTimestamp(qysdsbbjcForm.getSkssjsrq());
		//获得查询税费接口
		qysdsSet=util.getQysdsSet(bbqlx, jsjdm, skssksrq, sbrq, skssjsrq, jddm, qysdsSet, proxy);
		Zsfs zsfs = qysdsSet.getZsfs();
		//如果征收方式为空，按照查账增收来判定
		if(zsfs==null){
			zsfsdm="03";
		}else{
			zsfsdm = zsfs.getZsfsdm();
		}
		System.out.println("年度征收方式代码：" + zsfsdm);
		String [] zsfsArr=qysdsbbjcForm.getZsfs().split("\\|");
		System.out.println("++++++++++++++qysdsbbjcForm.getZsfs()"+qysdsbbjcForm.getZsfs());
		System.out.println("++++++++++++++++数组长度"+zsfsArr.length);
		    for(int i=0;i<zsfsArr.length;i++){
		       System.out.println("++++++++++++++++征收方式代码"+zsfsArr[i]);
		       if (zsfsdm.equals(zsfsArr[i])) {
		       flag=true;
		       path = util.getPath(sbzllxdm, skksrq, skjsrq);
		       path=path+"&sbrq="+rq+"&skssksrq="+skksrq+"&skssjsrq="+skjsrq+"&sknd="+sbnd+"&jsjdm="+jsjdm;
		       qysdsbbjcForm.setPath(path);
		       System.out.println("*********指向路径：" + path);
		       break;
		     }
		  }
		      //如果征收方式代码不匹配，抛出异常
		      if(flag==false){
			  throw new ApplicationException("征收方式代码不匹配！！！");
		  }
		      System.out.println("测试form:"+qysdsbbjcForm);
		      return qysdsbbjcForm;
	
  }
}