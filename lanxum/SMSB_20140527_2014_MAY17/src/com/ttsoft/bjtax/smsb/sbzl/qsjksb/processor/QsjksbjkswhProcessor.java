/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qsjksb.processor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.ttsoft.bjtax.sfgl.common.code.CodeUtils;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBUtils;
import com.ttsoft.bjtax.shenbao.model.client.SBData;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.sbzl.qsjksb.web.QsjksbjkswhActionForm;
import com.ttsoft.bjtax.smsb.util.Debug;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.JksUtil;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.bjtax.smsb.zhsb.web.ZhsbjkswhActionForm;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import java.util.Vector;

/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description: 实现欠税缴款申报功能：包括缴款书录入，维护。</p>
 * @author Zhang Yijun
 * @version 1.1
 */

public class QsjksbjkswhProcessor
    implements Processor
{
  public QsjksbjkswhProcessor()
  {
  }

  /**
   * 实现Processor接口
   * @param vo 业务参数
   * @return Object VOPackage型数据
   * @throws BaseException 业务异常
   */

  public Object process(VOPackage vo) throws com.ttsoft.framework.exception.
      BaseException
  {
    switch (vo.getAction())
    {
      case CodeConstant.SMSB_QUERYACTION:
        return doQuery(vo);
      case CodeConstant.SMSB_QUERYACTION1:
          return doQueryCx(vo);
      case CodeConstant.SMSB_UPDATEACTION:
        return doCx(vo);

      default:
        return null;
    }

  }

  /**
   * 显示该计算机代码对应的所有未入库缴款书
   *
   * @param     vo 业务参数
   * @return ZhsbjkswhActionForm ZhsbjkswhActionForm型数据
   * @throws BaseException 业务异常
   */
  private QsjksbjkswhActionForm doQuery(VOPackage vo) throws BaseException
  {

    //定义数据库连接
    Connection conn = null;

    try
    {
    	QsjksbjkswhActionForm form = (QsjksbjkswhActionForm) vo.getData(); //从前端获得map对象
      //得到userdata
      UserData userData = vo.getUserData();
      //得到区县代码
      String qxdm = InterfaceDj.getQxdm(userData);
      String jsjdm = form.getJsjdm(); //获取用户的计算机代码
      //数据来源
      String sjly = form.getSjly();
      if (sjly == null || sjly.equals(""))
      {
        //未指定特殊的数据来源，则则缺省设为补缴欠税形成的申报录入
        sjly = CodeConstant.SMSB_SJLY_BJQS;
        
      }
      //获得数据库连接
      conn = SfDBResource.getConnection();
      SfDBAccess da = new SfDBAccess(conn);
      //获得 ORManager


      //设置查询条件(如果非稽查一二局用户,则含区县代码查询条件)
      Vector tempSql = new Vector();
      tempSql.add("LRR='" + userData.getYhid() + "'");
      
      //增加稽查三四局权限过滤条件 modified by zhangyj 20140723     
      if(!(qxdm.endsWith("25")||qxdm.endsWith("41")||qxdm.endsWith("24")||qxdm.endsWith("45"))){
      tempSql.add("QXDM='" + qxdm + "'");
      }
      tempSql.add("JSJDM='" + jsjdm + "'");
      tempSql.add("SJLY='" + sjly + "'");
      tempSql.add("FSDM='" + CodeConstant.FSDM_SMSB + "'");
      tempSql.add("ZWBS LIKE '0%0'");
       //如果申报表不为空，则只显示该申报编号的缴款书列表
      
      if(form.getSbbhList()!=null && form.getPresbbh()!= null && form.getPresbbh().equals("1"))
      {
    	  String sbbhStr="";
    	  ArrayList list=(ArrayList) form.getSbbhList();
    	  for(int i=0;i<list.size();i++){
    		  sbbhStr=sbbhStr+"'"+list.get(i)+"',";
    	  }
    	  sbbhStr = sbbhStr.substring(0, sbbhStr.length()-1);
    	  tempSql.add("SBBH in(" + sbbhStr + ")");    	  
      }    
      tempSql.add("ZYRQ >= to_date('20050101','yyyymmdd')");
      tempSql.add("ND=to_char(sysdate,'yyyy')");
      tempSql.addElement("1=1 ORDER BY SBRQ DESC,SBBH,JKPZH ASC");
      //long t = System.currentTimeMillis();
      //Debug.out("tempSql******************"+tempSql);
      
      List tempList = da.query(Sbjkzb.class, tempSql);

      //Debug.out("doQuery time cost:" + (System.currentTimeMillis() - t));

      
      //添加税种名称后设置列表
      HashMap sbmaps = new HashMap();
      SBData sb = null;
      for (int i = 0; i < tempList.size(); i++)
      {
        //格式化每条纪录
        Sbjkzb sbjkzb = (Sbjkzb) tempList.get(i);
        sbjkzb.setSzmc(CodeUtils.getCodeBeanLabel("DM_SZSM",
                                                  sbjkzb.getSzdm())); //税种名称
        sb = (SBData)sbmaps.get(sbjkzb.getSbbh());
        if (sb == null)
        {
          sb = new SBData();
          sbmaps.put(sbjkzb.getSbbh(),sb);
        }
        sb.addSbjkzb(sbjkzb);
      }

      //将map转为list
      Iterator c = sbmaps.values().iterator();
      ArrayList datalist = new ArrayList();

      while (c.hasNext())
      {
        datalist.add(c.next());
      }
      //把值放回form对象

      form.setDataList(datalist);
      return form;
    }
    catch (Exception e)
    {
      throw ExceptionUtil.getBaseException(e);
    }
    finally
    {
      SfDBResource.freeConnection(conn);
    }

  }


  /**
   * 隐藏该计算机代码对应的所有未入库缴款书的撤销条件
   *
   * @param     vo 业务参数
   * @return ZhsbjkswhActionForm ZhsbjkswhActionForm型数据
   * @throws BaseException 业务异常
   */
  private QsjksbjkswhActionForm doQueryCx(VOPackage vo) throws BaseException
  {

    //定义数据库连接
    Connection conn = null;
    ResultSet rs=null;

    //获得数据库连接
    conn = SfDBResource.getConnection();
    SfDBAccess da = new SfDBAccess(conn);
    try
    {
    	QsjksbjkswhActionForm form = (QsjksbjkswhActionForm) vo.getData();
    	List datalist=form.getDataList();
    	List nlwdatalist=form.getNlwDataList();
    	HashMap map = null;
    	String sql = null ;
    	String firstchar=null;
    	String lastchar=null;
    	
    	if(datalist.size()!=0){
    		for(int i=0;i<datalist.size();i++){
    		map=(HashMap)datalist.get(i);
    		String sbbh=String.valueOf(map.get("sbbh"));
    		sql="select * from sbdb.sb_jl_sbjkzb where sbbh='"+sbbh+"'and zwbs not like '0%0'";
    		rs=da.querySQL(sql);
    		if(rs.next()){
    			map.put("cxbs","1");    			
    		}else{
    			map.put("cxbs","0");
    		}
    		}
    	}
    	if(nlwdatalist.size()!=0){
    		for(int j=0;j<nlwdatalist.size();j++){
    			map=(HashMap)nlwdatalist.get(j);
        		String jkpzh=String.valueOf(map.get("jkpzh"));
        		sql="select t.jkpzh,p.znjjkpzh dzjkpzh,(select zwbs from sbdb.sb_jl_sbjkzb where jkpzh=p.znjjkpzh) dzzwbs,p.jkpzh pzh,p.qs_xh qsxh,p.sbje" 
        			+" from sbdb.sb_jl_sbjkzb t,jkdb.kj_jl_sb_qs_dz p where t.jkpzh=p.jkpzh and t.jkpzh='"+jkpzh+"' union " 
        			+"select t.jkpzh,p.jkpzh dzjkpzh,(select zwbs from sbdb.sb_jl_sbjkzb where jkpzh=p.jkpzh) dzzwbs,p.jkpzh pzh,p.qs_xh qsxh,p.sbje"  
        				+" from sbdb.sb_jl_sbjkzb t,jkdb.kj_jl_sb_qs_dz p where t.jkpzh=p.znjjkpzh and t.jkpzh='"+jkpzh+"'";
        		rs=da.querySQL(sql);
        		while(rs.next()){
        			if(rs.getString("dzjkpzh")==null){
        				map.put("dzjkpzh","");
        			}else{
            			map.put("dzjkpzh",rs.getString("dzjkpzh"));       				
        			}
        			if(rs.getString("dzzwbs")==null){
        				map.put("cxbs","0");
        			}else{
        				firstchar=rs.getString("dzzwbs").toString().substring(0, 1);
        				lastchar=rs.getString("dzzwbs").toString().substring(19, 20);
        				if(firstchar.equals("0")&&lastchar.equals("0")){
        					map.put("cxbs","0");
        				}else{
        					map.put("cxbs","1");
        				}
        			}       			
        			map.put("pzh",rs.getString("pzh"));
        			map.put("qsxh",rs.getString("qsxh"));
        			map.put("sbje",rs.getString("sbje"));
        		}    			
    		}
    	}
    	form.setDataList(datalist);   	
    	form.setNlwDataList(nlwdatalist);
      return form;
    }
    catch (Exception e)
    {
      throw ExceptionUtil.getBaseException(e);
    }
    finally
    {
      SfDBResource.freeConnection(conn);
    }

  }  
  
  
  /**
   * 撤销指定缴款书 或申报
   *
   * @param     vo 业务参数
   * @return ArrayList 添加税种名称的申报缴款主表列表
   * @throws BaseException 业务异常
   */
  
  private QsjksbjkswhActionForm doCx(VOPackage vo) throws BaseException
  {
	  QsjksbjkswhActionForm form = (QsjksbjkswhActionForm) vo.getData(); //从前端获得map对象
    //得到userdata
    UserData userData = vo.getUserData();
    
    /**
     * 添加日志
     */
    
    TinyTools.makeLog4ZhsbCx(userData, form.getJkpzhStr());
    Connection conn = null;
    String sql=null;

    
    //获得数据库连接
    conn = SfDBResource.getConnection();
    SfDBAccess da = new SfDBAccess(conn);
    try
    {
      if (form.getJksType() == CodeConstant.PRINT_YPYS)
      { 
    	  
          //修改欠税明细数据
          sql="update jkdb.kj_jl_qsmx set qjje=qjje+'"+form.getSbje()+"' ,sbje=sbje-'"+form.getSbje()+"'where xh='"+form.getQsxh()+"'";
          int nret=da.updateSQL(sql);
          if (nret == 0)
          {
            //如果没有可修改数据则回滚
            throw new ApplicationException("撤销缴款书失败！无对应的欠税明细数据！");
          }
          
          //删除对照表数据
          sql="delete from jkdb.kj_jl_sb_qs_dz where jkpzh='"+form.getDzjkpzh()+"' and qs_xh='"+form.getQsxh()+"'";
          nret=da.updateSQL(sql);
          if (nret == 0)
          {
            //如果没有可删除数据则回滚
            throw new ApplicationException("撤销缴款书失败！无对应的欠税申报对照数据");
          }    	  

        //删除案件执行记录明细表数据
        sql="delete from jcdb.jc_jl_ajzxjlmx where jkpzh='"+form.getJkpzhStr()+"'";
        da.updateSQL(sql);          
        //删除明细表数据
        sql="delete from sbdb.sb_jl_sbjkmx where jkpzh='"+form.getJkpzhStr()+"'";
        da.updateSQL(sql);
        //删除主表数据
        sql="delete from sbdb.sb_jl_sbjkzb where jkpzh='"+form.getJkpzhStr()+"' and zwbs like '" +CodeConstant.SMSB_ZWBS + "%" +
                                CodeConstant.SMSB_ZWBS + "'";
        nret = da.updateSQL(sql);
        if (nret == 0)
        {
          //如果没有可删除数据则回滚
          throw new ApplicationException("撤销缴款书失败！无对应的申报缴款主表数据！");
        }

        //删除关联缴款书数据
        if(!form.getGljkpzh().equals("")){
            //删除案件执行记录明细表数据
            sql="delete from jcdb.jc_jl_ajzxjlmx where jkpzh='"+form.getGljkpzh()+"'";
            da.updateSQL(sql);         	
            //删除明细表数据
            sql="delete from sbdb.sb_jl_sbjkmx where jkpzh='"+form.getGljkpzh()+"'";
            da.updateSQL(sql);
            //删除主表数据
            sql="delete from sbdb.sb_jl_sbjkzb where jkpzh='"+form.getGljkpzh()+"' and zwbs like '" +CodeConstant.SMSB_ZWBS + "%" +
                                    CodeConstant.SMSB_ZWBS + "'";
            nret = da.updateSQL(sql);
            if (nret == 0)
            {
              //如果没有可删除数据则回滚
              throw new ApplicationException("撤销缴款书失败！无对应的申报缴款主表关联数据");
            }        	
        }

      }
      else
      {
          //修改欠税明细数据
          sql="update jkdb.kj_jl_qsmx a set a.qjje = a.qjje + (select t.sbje from jkdb.kj_jl_sb_qs_dz t where a.xh = t.qs_xh and t.sbbh = '"+form.getSbbh()+"'),"
          +"a.sbje = a.sbje - (select t.sbje from jkdb.kj_jl_sb_qs_dz t where a.xh = t.qs_xh and t.sbbh = '"+form.getSbbh()+"')"
          +"where a.xh = (select t.qs_xh from jkdb.kj_jl_sb_qs_dz t where a.xh = t.qs_xh and t.sbbh = '"+form.getSbbh()+"')";
          int nret=da.updateSQL(sql); 
          if (nret == 0)
          {
            //如果没有可修改数据则回滚
            throw new ApplicationException("撤销缴款书失败！无对应的欠税明细数据！");
          }
          
          //删除对照表数据
          sql="delete from jkdb.kj_jl_sb_qs_dz where jkpzh in (select jkpzh from sbdb.sb_jl_sbjkzb where jsjdm='"
           + form.getJsjdm() + "' and sbbh='" + form.getSbbh() +
          "'  AND zwbs like '" + CodeConstant.SMSB_ZWBS + "%" +
          CodeConstant.SMSB_ZWBS + "')";
          nret=da.updateSQL(sql);
          if (nret == 0)
          {
            //如果没有可修改数据则回滚
            throw new ApplicationException("撤销缴款书失败！无对应的欠税申报对照数据！");
          }
    	  
          //删除案件执行记录明细表数据
          sql="delete from jcdb.jc_jl_ajzxjlmx where jkpzh in (select jkpzh from sbdb.sb_jl_sbjkzb where jsjdm='"
           + form.getJsjdm() + "' and sbbh='" + form.getSbbh() +
          "'  AND zwbs like '" + CodeConstant.SMSB_ZWBS + "%" +
          CodeConstant.SMSB_ZWBS + "')";
          da.updateSQL(sql);                
          
          //删除明细表数据
    	  sql="delete from sbdb.sb_jl_sbjkmx where jkpzh in (select jkpzh from sbdb.sb_jl_sbjkzb where jsjdm='"
           + form.getJsjdm() + "' and sbbh='" + form.getSbbh() +
          "'  AND zwbs like '" + CodeConstant.SMSB_ZWBS + "%" +
          CodeConstant.SMSB_ZWBS + "')";
          nret=da.updateSQL(sql);
          if (nret == 0)
          {
            //如果没有可修改数据则回滚
            throw new ApplicationException("撤销缴款书失败！无对应的申报缴款明细表数据");
          }
          
          //删除主表数据
          sql="delete from sbdb.sb_jl_sbjkzb where jsjdm='"
           + form.getJsjdm() + "'  and sbbh='" + form.getSbbh() +
          "'  AND zwbs like '" + CodeConstant.SMSB_ZWBS + "%" +
          CodeConstant.SMSB_ZWBS + "'  ";
          nret=da.updateSQL(sql);
          if (nret == 0)
          {
            //如果没有可修改数据则回滚
            throw new ApplicationException("撤销缴款书失败！无对应的申报缴款主表数据");
          }

      }

    }
    catch (Exception ex)
    {
      throw ExceptionUtil.getBaseException(ex, "撤销缴款书失败!");
    }
    finally
    {
      SfDBResource.freeConnection(conn);
    }
    return form;
  }

}
