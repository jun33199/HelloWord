/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华紫光股份有限公司，版权所有. </p>
 * <p>Company: 清华紫光股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.wynsk.processor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.HashMap;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.common.db.util.BeanUtil;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.shenbao.model.domain.Wynsksb;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.bjtax.smsb.wynsk.web.WynsksbActionForm;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description: 实现无应纳税费款申报模块。</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class WynsksbProcessor
    implements Processor {
  public Object process(VOPackage vo) throws BaseException {
    switch (vo.getAction()) {

      case CodeConstant.SMSB_SAVEACTION:
        return doSave(vo);
      case CodeConstant.SMSB_DELETEACTION:
        return doDelete(vo);
      case CodeConstant.SMSB_QUERYACTION:
        return doQuery(vo);
      default:
        return null;
    }
  }

  /**
   * doSave     保存录入数据
   * @param     vo 业务参数
   * @return    数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
   * @throws BaseException    当其他情况都会抛出异常信息
   */

  private Object doSave(VOPackage vo) throws BaseException {
    WynsksbActionForm form = (WynsksbActionForm) vo.getData();
    //定义与form种的columns 一样的变量。其中的名称为名细的字段名
    //modified by tangchangfu 2014-04-08 无税减免税项目  新增字段 "wssbyydm","wssbyynr"
    String names[] = {
        "jsjdm", "sbrq", "skssksrq", "skssjsrq", "swjgzzjgdm","wssbyydm","wssbyynr"};
    //获得UserData
    UserData ud = vo.getUserData();
    Timestamp now = new Timestamp( (new java.util.Date()).getTime());
    Connection conn = null;
    try {
      //创建数据库链接
      conn = SfDBResource.getConnection();
      if (this.haveSb(vo, conn)) {
        //该纳税人已经存在本期无应纳税（费）款申报记录！
        throw new ApplicationException("该纳税人已经存在本期无应纳税（费）款申报记录！");
      }
      Wynsksb orObj = new Wynsksb();
      //将form中对应主表信息保存到值对象
      BeanUtil.copyBeanToBean(names, form, orObj);
      //从登记接口中获得信息
      HashMap mapDJ = new HashMap();
      try {
        /* start added by huxiaofeng 2005.8.16*/
        //mapDJ = (HashMap) InterfaceDj.getDjInfo(form.getJsjdm(), ud);
        mapDJ = (HashMap) InterfaceDj.getDjInfo_New(form.getJsjdm(), ud);
        /* end added by huxiaofeng 2005.8.16*/
      }
      catch (Exception ex5) {
        ex5.printStackTrace();
        throw ExceptionUtil.getBaseException(ex5);
      }
      //通过登记接口取得纳税人相关信息

      SWDJJBSJ jbsj = (SWDJJBSJ) mapDJ.get("JBSJ");

      orObj.setJsjdm(form.getJsjdm());
      //orObj.setSbrq();
      //orObj.setSkssksrq();
      //orObj.setSkssjsrq();
      //
      orObj.setSwjgzzjgdm(jbsj.getSwjgzzjgdm());

      //得到申报编号
      String sbbh = getSbbh(form.getJsjdm());
      orObj.setSbbh(sbbh);
      //录入日期
      orObj.setLrrq(now);
      //创建日期
      orObj.setCjrq(now);
      //录入人
      orObj.setLrr(ud.getYhid());
      orObj.setLrrmc(ud.getYhmc());//add by tangchangfu 无税减免税项目 2014-05-13
      //申报方式代码
      orObj.setFsdm(CodeConstant.FSDM_SMSB);
      //数据来源
      orObj.setSjly(CodeConstant.SMSB_SJLY_SBLR);
      orObj.setZsswjgzzjgdm(ud.getSsdwdm());
      //区县代码
      orObj.setQxdm(InterfaceDj.getQxdm(ud));
      //年度
      orObj.setNd(String.valueOf(TinyTools.getYear(orObj.getCjrq())));

      SfDBAccess da = new SfDBAccess(conn);
      //add by tangchanhgfu 2014-04-08 无税减免税项目 start
      try{
    	  //无税申报原因为其他，则无税申报内容等于备注信息
    	  if(CodeConstant.SMSB_WYNSKSB_WSSBYY_QT.equals(form.getWssbyydm())){
    		  orObj.setWssbyynr(form.getBz());
    	  }else{
        	  getWssbyyMC(form.getWssbyydm(), orObj, da);
    	  }
    	    System.out.println("##############无纳税申报原因信息：："+orObj.getWssbyydm()+"--"+orObj.getWssbyynr());
      }catch(Exception ex){
    	  ex.printStackTrace();
    	  throw ExceptionUtil.getBaseException(ex, "获得无税申报信息错误!");
      }
      
      da.insert(orObj);
      
      return form;
    }
    catch (Exception ex) {
      //throw ExceptionUtil.getBaseException(ex, "系统错误请与管理员联系!");
    	ex.printStackTrace();
      throw ExceptionUtil.getBaseException(ex);
    }
    finally {
      SfDBResource.freeConnection(conn);
    }

  }

 /**
  * 获得无税申报原因
  * @param form
  * @param orObj
  * @param da
  * @throws BaseException
  * @throws SQLException
  */
private void getWssbyyMC(String wssbyydm, Wynsksb orObj, SfDBAccess da)
		throws BaseException, SQLException {
	
	try{
		
		if(wssbyydm == null || "".equals(wssbyydm)){
			throw new ApplicationException("获得无税申报原因错误！");
			
		}
		
		//获得无税申报原因
		String queryWssbyy="select wssbyydm,wssbyymc, zchbsdm from DMDB.SB_DM_WSSBYY where wssbyydm='"+wssbyydm+"' and wssbyydm !='99'";
		ResultSet rs = da.querySQL(queryWssbyy);
		while(rs.next()){
			orObj.setWssbyynr(rs.getString("wssbyymc"));
		}
		
	}catch(Exception e){
		e.printStackTrace();
		throw new ApplicationException("获得无税申报原因错误！");
	}
}

  /**
   * doSave     删除指定数据
   * @param     vo 业务参数
   * @return    数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
   * @throws BaseException    当其他情况都会抛出异常信息
   */

  private Object doDelete(VOPackage vo) throws BaseException {
    WynsksbActionForm form = (WynsksbActionForm) vo.getData();
    Connection conn = null;
    //获得UserData
    UserData ud = vo.getUserData();
    try {
      conn = SfDBResource.getConnection();
      SfDBAccess da = new SfDBAccess(conn);
      StringBuffer sql = new StringBuffer();
      sql.append("delete from SBDB.SB_JL_WYNSKSB where ");
      sql.append("qxdm='" + InterfaceDj.getQxdm(ud)).append("'");
      sql.append(" and jsjdm='").append(form.getJsjdm()).append("'");
      sql.append(" and sbbh='").append(form.getSbbh()).append("'");
      da.updateSQL(sql.toString());
      
      return form;
    }
    catch (Exception ex) {
      throw ExceptionUtil.getBaseException(ex);
    }
    finally {
      SfDBResource.freeConnection(conn);
    }

  }

  /**
   * doSave     查询录入数据
   * @param     vo 业务参数
   * @return    数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
   * @throws BaseException    当其他情况都会抛出异常信息
   */

  private Object doQuery(VOPackage vo) throws BaseException {
    WynsksbActionForm form = (WynsksbActionForm) vo.getData();
    Connection conn = null;
    //获得UserData
    UserData ud = vo.getUserData();
    Timestamp now = new Timestamp( (new java.util.Date()).getTime());
    try {
      conn = SfDBResource.getConnection();
      SfDBAccess da = new SfDBAccess(conn);
      Vector cri = new Vector();
      cri.add("nd='" + String.valueOf(TinyTools.getYear(now)) + "'");
      cri.add("qxdm='" + InterfaceDj.getQxdm(ud) + "'");
      cri.add("jsjdm='" + form.getJsjdm() + "'");
      cri.add("to_char(sbrq,'yyyyMM')='" +
              SfDateUtil.getDate(now).substring(0, 6) + "' order by cjrq desc");
      List ret = da.query(new Wynsksb().getClass(), cri);
      form.setDataList( (ArrayList) ret);
      
      //add by tangchangfu 无税减免税项目 获得无税申报原因,除等于99--其他
      List tempList = form.getDataList();
      for(int index =0; index < tempList.size(); index ++){
    	  Wynsksb tmpOrObj = (Wynsksb)tempList.get(index);
    	  if(tmpOrObj != null ){
    		  String wssbyydm = tmpOrObj.getWssbyydm();
    		  String wssbyymc = tmpOrObj.getWssbyynr();
    		  if(wssbyydm != null && !"".equals(wssbyydm) && (wssbyymc == null || "".equals(wssbyymc)))
    		  {
    			  this.getWssbyyMC(wssbyydm, tmpOrObj, da);
    		  }
    		  //如果没有获取到申报原因
    		  wssbyymc = tmpOrObj.getWssbyynr();
    		  if(wssbyymc == null || "".equals(wssbyymc)){
    			  tmpOrObj.setWssbyynr(" ");
    			  
    		  }
    	  }
      }
      //add end
      return form;
    }
    catch (Exception ex) {
      //throw ExceptionUtil.getBaseException(ex, "系统错误请与管理员联系!");
      throw ExceptionUtil.getBaseException(ex);
    }
    finally {
      SfDBResource.freeConnection(conn);
    }

  }

  /**
   * 申报编号的生成,申报编号的生成规则为：计算机代码加上服务器的当前时间的十八位字符串
   * @param jsjdm 计算机代码
   * @return sbbh
   */
  private String getSbbh(String jsjdm) {
    //得到当前时间
    long currentTime = System.currentTimeMillis();
    //参考时间
    long targetTime = Long.parseLong("1064700000000");
    //生成申报编号
    String sbbh = jsjdm + Long.toHexString(currentTime - targetTime);
    return sbbh;
  }

  /**
   * 检查是否已有无应纳税款申报记录
   * @param vo VOPackage
   * @param conn 数据库链接
   * @return 是否已有无应纳税款申报记录
   */
  private boolean haveSb(VOPackage vo, Connection conn) {
    boolean ret = false;
    WynsksbActionForm form = (WynsksbActionForm) vo.getData();
    try {
      //获得UserData
      UserData ud = vo.getUserData();
      conn = SfDBResource.getConnection();
      SfDBAccess da = new SfDBAccess(conn);
      Vector cri = new Vector();
      cri.add("nd='" + String.valueOf(form.getSbrq().substring(0, 4)) + "'");
      cri.add("qxdm='" + InterfaceDj.getQxdm(ud) + "'");
      cri.add("jsjdm='" + form.getJsjdm() + "'");
      cri.add("to_char(skssksrq,'yyyyMMdd')='" + form.getSkssksrq() + "'");
      cri.add("to_char(skssjsrq,'yyyyMMdd')='" + form.getSkssjsrq() + "'");
      List list = da.query(new Wynsksb().getClass(), cri);
      if (list.size() > 0) {
        return true;
      }
      else {
        return false;
      }
    }
    catch (Exception ex) {
      return true;
    }
  }
}
