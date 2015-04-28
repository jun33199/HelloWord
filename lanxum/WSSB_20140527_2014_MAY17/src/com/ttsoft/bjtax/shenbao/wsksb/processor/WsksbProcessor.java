package com.ttsoft.bjtax.shenbao.wsksb.processor;

/**
 * <p>Title: 北京地税核心征管系统－－网上申报</p>
 * <p>Description: </p>
 * <p>Copyright: (C) 2004-2005 四一安信股份有限公司，版权所有.</p>
 * <p>Company: 四一安信股份有限公司</p>
 * @author  guzhixian
 * @version 1.1
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.ekernel.db.or.ORContext;
import com.ekernel.db.or.ORManager;
import com.syax.bjtax.ca.proxy.CAProxy;
import com.syax.bjtax.ca.util.DzyjHelper;
import com.syax.bjtax.ca.vo.DzyjsjVO;
import com.syax.common.util.CAcodeConstants;
import com.ttsoft.bjtax.shenbao.constant.CodeConstant;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb;
import com.ttsoft.bjtax.shenbao.model.domain.Wynsksb;
import com.ttsoft.bjtax.shenbao.model.domain.Wynskszsm;
import com.ttsoft.bjtax.shenbao.service.processor.InterFaceProcessor;
import com.ttsoft.bjtax.shenbao.util.CAUtils;
import com.ttsoft.bjtax.shenbao.util.DBResource;
import com.ttsoft.bjtax.shenbao.wsksb.SessionKey;
import com.ttsoft.bjtax.shenbao.wsksb.xmlvo.WsksbVO;
import com.ttsoft.bjtax.shenbao.zhsb.ZhsbMapConstant;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;


public class WsksbProcessor implements Processor
{
    public WsksbProcessor()
    {
    }

    /**
     * SessionID常量
     */
    private final long SESSION_ID = 0;

    //实现Processor接口
    public Object process(VOPackage vOPackage) throws BaseException
    {
        switch(vOPackage.getAction())
        {
            case SessionKey.INT_ACTION_SAVE:
                try{ doSave(vOPackage);
                return vOPackage;}
                catch(Exception ex) {
                    throw ExceptionUtil.getBaseException(ex, "保存无税款申报书数据失败!");
                }
            case SessionKey.INT_ACTION_QUERYJKSJL :
                try{ return doQueryJks(vOPackage, CodeConstant.SJLY_SB_SBLR); }
                catch(Exception ex) {
                    throw ExceptionUtil.getBaseException(ex, "查询缴款申报书数据失败！");
                }
           case SessionKey.INT_ACTION_QUERYWSKJL :
                    try{ return doQueryWsk(vOPackage); }
                    catch(Exception ex) {
                        throw ExceptionUtil.getBaseException(ex, "查询无税款申报书数据失败！");
                    }
           case SessionKey.INT_ACTION_QUERYGZSX :
               try{ return doQueryGzsx(vOPackage); }
               catch(Exception ex) {
                   throw ExceptionUtil.getBaseException(ex, "查询告知事项数据失败！");
               }    
                    
            case SessionKey.INT_ACTION_DELETE:
                try { doDel(vOPackage);
                    return null; }
                catch(Exception ex) {
                    throw ExceptionUtil.getBaseException(ex, "删除无税款申报书数据失败！");
                }
                default:
                    throw ExceptionUtil.getBaseException(new Exception("系统错误,请与管理员联系!"));
            }
        }


    /**
     * 删除纳税人本期无税款申报数据
     * @param voPackage object
     * @return map
     * @throws java.lang.Exception
     */
    private void doDel(VOPackage vOPackage) throws BaseException
    {
    	HashMap hm=(HashMap)vOPackage.getData();
        UserData ud = vOPackage.getUserData();
        DzyjHelper dh = new DzyjHelper();        
        Wynsksb wynsksb =(Wynsksb)hm.get(ZhsbMapConstant.SBSJ);
        WsksbVO wsksbvo = (WsksbVO)hm.get(CAcodeConstants.CA_MAPKEY_VO_XMLVO);  
		DzyjsjVO dzyj = (DzyjsjVO) hm.get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);
        Connection conn = null;
        ORManager orManager = null;
        try
        {
            orManager = DBResource.getORManager(DBResource.OR_SHENBAO);
            //获得数据库连接
            conn = DBResource.getConnection(DBResource.DB_SHENBAO);
            Vector criteria = null; //查询条件
            ORContext orCtx = null; //查询的ORContext对象
            //删除明细和主表数据
            StringBuffer sqlBuffer = new StringBuffer();
            sqlBuffer.append("(SBBH = '" + wynsksb.getSbbh() + "'");
            sqlBuffer.append(" AND FSDM = '5' AND JSJDM = '"+wynsksb.getJsjdm()+"')");
            String wheresql = sqlBuffer.toString(); //删除条件
    		// 保存签名元件
            //System.out.println("ud.getCaflag():"+ud.getCaflag());
			//dzyj.setYwczlx(wsksbvo.getYwczlx());
			//dzyj.setYwdm(wsksbvo.getYwlx());
    		/*if (ud.getCaflag()) {
    			try {
			dzyj.setYwuid(wynsksb.getSbbh());
    				dzyj = dh.saveDzyjsj(dzyj, "0", "0", "0", wynsksb.getSbbh());
    			} catch (com.syax.frame.exception.ApplicationException e) {
    				throw ExceptionUtil.getBaseException(e);
    			} catch (Exception ex) {
    				ex.printStackTrace();
    				throw ExceptionUtil.getBaseException(ex);
    			}
      			hm.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ,dzyj);
    			//System.out.println("============保存签名数据结束==============");
    		}*/
            
    		try
	        {
	       	 dzyj = CAUtils.saveDzyjsj(ud,dzyj, "0", "0", "0", wynsksb.getSbbh());
	       }catch (Exception ex)
	        {
	            ex.printStackTrace();
	            throw ExceptionUtil.getBaseException(ex);
	        }
	       hm.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);

    		String sqlZb = wheresql; //主表条件
            //删除主表
            orManager.deleteObject(SESSION_ID, conn, sqlZb, new Wynsksb());
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex, "删除申报编号对应的无应纳税款数据失败!");
        }
        finally
        {
            DBResource.destroyConnection(conn);
        }
    }

    /**
     * 保存纳税人本期无税款申报数据
     * @param voPackage object
     * @return map
     * @throws java.lang.Exception
     */
    private void doSave(VOPackage vOPackage) throws BaseException
    {
        //OR实例
        ORManager orManager = null;

        Connection conn = null;

        HashMap hm=(HashMap)vOPackage.getData();
        Wynsksb wynsksb =(Wynsksb)hm.get(ZhsbMapConstant.SBSJ);
        UserData ud = vOPackage.getUserData();
        DzyjHelper dh = new DzyjHelper();
        WsksbVO wsksbvo = (WsksbVO)hm.get(CAcodeConstants.CA_MAPKEY_VO_XMLVO);
		DzyjsjVO dzyj = (DzyjsjVO) hm.get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);
		
        //循环插入主表和明细数据
        try
        {

          String sbbh = InterFaceProcessor.getSbbh(wynsksb.getJsjdm());  //标准时间，1970年1月1日0点以来的毫秒数
          wynsksb.setSbbh(sbbh);
          hm.put(ZhsbMapConstant.SBSJ,wynsksb);
  		// 保存签名元件
			//dzyj.setYwczlx(wsksbvo.getYwczlx());
  			//dzyj.setYwdm(wsksbvo.getYwlx());
  		/*if (ud.getCaflag()) {
  			try {
  			dzyj.setYwuid(wynsksb.getSbbh());
  				dzyj = dh.saveDzyjsj(dzyj, "0", "0", "0", wynsksb.getSbbh());
  			} catch (com.syax.frame.exception.ApplicationException e) {
  				throw ExceptionUtil.getBaseException(e);
  			} catch (Exception ex) {
  				ex.printStackTrace();
  				throw ExceptionUtil.getBaseException(ex);
  			}
  			hm.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ,dzyj);
  			//System.out.println("============保存签名数据结束==============");
  		}*/
        try
        {
       	 dzyj = CAUtils.saveDzyjsj(ud,dzyj, "0", "0", "0", wynsksb.getSbbh());
       }catch (Exception ex)
        {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
       hm.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);

            // 获得 ORManager
            orManager = DBResource.getORManager(DBResource.OR_SHENBAO);
            //获得数据库连接
            conn = DBResource.getConnection(DBResource.DB_SHENBAO);

            Vector cri = new Vector();
            cri.add("nd='" + String.valueOf(new SimpleDateFormat("yyyy").format(wynsksb.getSbrq())) + "'");
            cri.add("qxdm='" + wynsksb.getQxdm() + "'");
            cri.add("jsjdm='" + wynsksb.getJsjdm() + "'");
            cri.add("to_char(skssksrq,'yyyyMMdd')='" + new SimpleDateFormat("yyyyMMdd").format(wynsksb.getSkssksrq()) + "'");
            cri.add("to_char(skssjsrq,'yyyyMMdd')='" + new SimpleDateFormat("yyyyMMdd").format(wynsksb.getSkssjsrq()) + "'");
            ORContext orCtx = new ORContext(Wynsksb.class.getName(), cri);

            ArrayList list = (ArrayList)orManager.query(SESSION_ID, conn, orCtx); //查询无税款记录

            if (list.size() > 0) {

                throw ExceptionUtil.getBaseException(new Exception("无税申报数据已经存在，不能重复保存！"));
            }


            //插入主表数据
            orManager.makePersistent(SESSION_ID, conn, wynsksb);

            /*if (vOPackage.getUserData().getCaflag()) {
              //更新签名数据表中的税票编号
              dzyj.setYwuid(wynsksb.getSbbh());
              CAProxy.getInstance().updateSignedData(dzyj);
            }*/

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex, "生成数据错误!");
        }
        finally
        {
            DBResource.destroyConnection(conn);
        }
    }

    /**
     * 申报数据维护入口进入，查询纳税人本期所有申报数据
     * @param voPackage object
     * @param sjly   数据来源
     * @return map
     * @throws java.lang.Exception
     */
    public List doQueryJks(VOPackage voPackage ,String sjly) throws Exception
    {
        HashMap dataMap = new HashMap(); //返回的map对象
        //定义数据库连接
        Connection conn = null;
        try
        {
            Map tempData = (HashMap)voPackage.getData(); //从前端获得map对象
            String swjgzzjgdm = (String)tempData.get("WSSB_SWJGZZJGDM");  //税务机关组织机构代码
            String jsjdm = (String)tempData.get(SessionKey.JSJDM); //获取用户的计算机代码
            //注意日期格式，必须为:(YYYY-MM-DD)
            String whrq = (String)tempData.get(SessionKey.WHRQ); //获取用户维护申报数据日期

            String qxdm = swjgzzjgdm.substring(0,2);  //区县代码

            //查询申报缴款主表的查询结果
            ArrayList zbResult = new ArrayList();

            //过滤出已经有缴款记录的申报编号，帐页日期或者(帐务标识的第1位<>0or第20位<>0)
            //不管同一批数据是否有缴款记录，只要没有缴款，就允许修改
            String sqlWhere = "(ZYRQ >= to_date('20050101','yyyymmdd') AND QXDM = '"
                + qxdm + "' AND JSJDM = '" + jsjdm + "'"
                + " AND substr(to_char(SBRQ,'yyyy-mm-dd'),0,7) = substr('"
                + whrq + "', 0, 7) ) "; //AND SJLY = '" + sjly
            Vector criteria = new Vector();
            criteria.add(sqlWhere);

            ORContext orCtx = new ORContext(Sbjkzb.class.getName(), criteria);

            //获得 ORManager
            ORManager orManager = DBResource.getORManager(DBResource.OR_SHENBAO);
            //获得数据库连接
            conn = DBResource.getConnection(DBResource.DB_SHENBAO);

            zbResult = (ArrayList)orManager.query(SESSION_ID, conn, orCtx); //查询申报缴款主表

            return zbResult; //返回结果数据
        }
        catch(Exception e)
        {
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e);
        }
        finally
        {
            DBResource.destroyConnection(conn);
        }
    }

    /**
     * 申报数据维护入口进入，查询纳税人本期所有申报数据
     * @param voPackage object
     * @param sjly   数据来源
     * @return map
     * @throws java.lang.Exception
     */
    public HashMap doQueryWsk(VOPackage voPackage) throws Exception
    {
        HashMap dataMap = new HashMap(); //返回的map对象
        //定义数据库连接
        Connection conn = null;
        try
        {
            Map tempData = (HashMap)voPackage.getData(); //从前端获得map对象
            String swjgzzjgdm = (String)tempData.get("WSSB_SWJGZZJGDM");  //税务机关组织机构代码
            String skssksrq = (String)tempData.get(SessionKey.SKSSKSRQ);  //
            String skssjsrq = (String)tempData.get(SessionKey.SKSSJSRQ);  //
            String jsjdm = (String)tempData.get(SessionKey.JSJDM); //获取用户的计算机代码
            //注意日期格式，必须为:(YYYY-MM-DD)
            String whrq = (String)tempData.get(SessionKey.WHRQ); //获取用户维护申报数据日期

            String qxdm = swjgzzjgdm.substring(0,2);  //区县代码

            //查询申报主表的查询结果
            Wynsksb wynsksb = null;
        //    Zqrl zqrl = null;
            //查询无应纳税款税种税目的查询结果
            ArrayList szsmResult = new ArrayList();

            StringBuffer sqlStrBuf = new StringBuffer();

            sqlStrBuf.append( "(nd = substr('" + whrq + "', 0, 4) AND QXDM = '")
                .append( qxdm + "' AND JSJDM = '" + jsjdm + "'")
                .append( " AND to_char(SKSSKSRQ,'yyyy-mm-dd') = '")
                .append( skssksrq + "'  ")
                .append( " AND to_char(SKSSJSRQ,'yyyy-mm-dd') = '")
                .append( skssjsrq + "' ) ");
            System.out.println("-----Wsksb Query Sql:::"+sqlStrBuf);

            String sqlString = sqlStrBuf.toString();
            Vector criteria = new Vector();
            criteria.add(sqlString);

            ORContext orCtx = new ORContext(Wynsksb.class.getName(), criteria);

            //获得 ORManager
            ORManager orManager = DBResource.getORManager(DBResource.OR_SHENBAO);
            //获得数据库连接
            conn = DBResource.getConnection(DBResource.DB_SHENBAO);

            ArrayList zbResult = (ArrayList)orManager.query(SESSION_ID, conn, orCtx); //查询无税款记录
            if (zbResult.size()>0){
                wynsksb = (Wynsksb)zbResult.get(0);
            }

            sqlStrBuf = new StringBuffer();
            sqlStrBuf.append("(ZXBS = '0'").append(" AND ZTBS = '0'");
            sqlStrBuf.append(")").append(" ORDER BY SZSMDM ASC");
            sqlString = sqlStrBuf.toString();
            Vector criteriaMx = new Vector();
            criteriaMx.add(sqlString);

            ORContext orCtxMx = new ORContext(Wynskszsm.class.getName(), criteriaMx);
            szsmResult = (ArrayList)orManager.query(SESSION_ID, conn, orCtxMx); //查询无税款税种税目表

            //取营业税征期
       /*     sqlStrBuf = new StringBuffer();
            sqlStrBuf.append( "(ZQLXDM >= '0' AND ZQQSRQ <= to_date('")
                .append( whrq + "','yyyy-mm-dd') AND SZSMDM = '" + SzsmdmConstant.YYS_TLYS)
                .append( "' AND DJZCLXDM = '" + djzclxdm)
                .append( "' AND ZQZZRQ >= to_date('" + whrq)
                .append( "','yyyy-mm-dd')) ORDER BY ZQQSRQ");
            sqlString = sqlStrBuf.toString();
            Vector criteriaZq = new Vector();
            criteriaZq.add(sqlString);

            ORContext orCtxZq = new ORContext(Zqrl.class.getName(), criteriaZq);
            ArrayList zqResult = (ArrayList)orManager.query(SESSION_ID, conn, orCtxZq); //查询无税款税种税目表
            if (zqResult.size()>0){
                zqrl = (Zqrl)zqResult.get(0);
            }
*/
            
            
            
            
            
            
            DBResource.destroyConnection(conn);  //关闭数据库连接
            conn = null;

            dataMap.put(SessionKey.WSKJKS, wynsksb);
            dataMap.put(SessionKey.WSKSZSM, szsmResult);
         //   dataMap.put(SessionKey.ZQRL, zqrl);

            return dataMap; //返回结果数据
        }
        catch(Exception e)
        {
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e);
        }
        finally
        {
            DBResource.destroyConnection(conn);
        }
    }
    	
    /**
     * 查询告知事项通知
     * @param voPackage
     * @return
     * @throws Exception
     */
    public String doQueryGzsx(VOPackage vo) throws Exception
    {
    	Connection conn = DBResource.getConnection(DBResource.DB_SHENBAO);
    	try{
	    	String result = "";
	    	
	    	
	    	/*--------添加告知事项  add by lijn 20140228 start------*/
	        String sql = "select gzsxnr  from SBDB.SB_JL_GZSX where jsjdm = ? and ph like 'lxwynssb%'  and GZSXKSRQ <= sysdate  and GZSXJSRQ >= sysdate";
	        
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setString(1, vo.getUserData().getYhid());
	        
	        ResultSet rs = ps.executeQuery();
	        if(rs.next()){
	        	result = rs.getString("gzsxnr");	
	        }
	        /*-----------------end--------------------------*/
	    	return result;
	    }
	    catch(Exception e)
	    {
	        e.printStackTrace();
	        throw ExceptionUtil.getBaseException(e);
	    }
	    finally
	    {
	        DBResource.destroyConnection(conn);
	    }
    }

}