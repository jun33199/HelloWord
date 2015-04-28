package com.ttsoft.bjtax.shenbao.zrrsb.processor;

//import java.sql.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.ekernel.db.or.ORContext;
import com.ekernel.db.or.ORManager;
import com.ttsoft.bjtax.sfgl.common.model.Sfxy;
import com.ttsoft.bjtax.shenbao.constant.ActionConstant;
import com.ttsoft.bjtax.shenbao.constant.CodeConstant;
import com.ttsoft.bjtax.shenbao.constant.CodeTable;
import com.ttsoft.bjtax.shenbao.model.client.DeclareInfor;
import com.ttsoft.bjtax.shenbao.model.client.SBData;
import com.ttsoft.bjtax.shenbao.model.client.ZrrsbInfor;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkmx;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb;
import com.ttsoft.bjtax.shenbao.model.domain.Wbhs;
import com.ttsoft.bjtax.shenbao.model.domain.Wbzhrmb;
import com.ttsoft.bjtax.shenbao.model.domain.Zrrgrsdsmx;
import com.ttsoft.bjtax.shenbao.model.domain.Zrrgrsdsz;
import com.ttsoft.bjtax.shenbao.util.DBResource;
import com.ttsoft.bjtax.shenbao.util.Debug;
import com.ttsoft.bjtax.shenbao.util.TinyTools;
import com.ttsoft.bjtax.shenbao.util.TranslateHelper;
import com.ttsoft.bjtax.shenbao.zhsb.ZhsbMapConstant;
import com.ttsoft.bjtax.shenbao.zhsb.processor.ZhsbProcessor;
import com.ttsoft.bjtax.shenbao.zrrsb.ZrrsbActionConstant;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.exception.SystemException;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.DBAccess;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: 北京地税综合管理系统  申报征收模块</p>
 * <p>Description: 自然人个人所得税申报</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: TTSOFT</p>
 * @author stone
 * @version 1.0 2003-9-17
 */

public class ZrrsbProcessor implements Processor
{
    private static final int SESSION_ID = 0;
    public ZrrsbProcessor()
    {
    }

    /**
     * 处理入口
     * @param vo 前台数据
     * @return 操作结果这里为Boolean值
     * @throws BaseException 操作异常
     */
    public Object process(VOPackage vo) throws BaseException
    {
        switch(vo.getAction())
        {
            case ActionConstant.INT_ACTION_SAVE:
                return doSaveSbData(vo);

            case ActionConstant.INT_ACTION_DELETE:
                doDel(vo);
                return null;

            case ActionConstant.INT_ACTION_QUERY:
                return doQuery(vo);

            case ZrrsbActionConstant.INT_ACTION_QUERYBZ:
                return getBzList();

            default:
                throw new SystemException("no such mothod!");
        }
    }

    /**
     * 币种代码表查询
     * @return 最新币种代码表
     * @throws BaseException 操作异常
     */
    private Map getBzList() throws BaseException
    {
        // 外币换算
        HashMap ret = new HashMap();
        Connection con = null;
        Calendar time = Calendar.getInstance();
        time.add(Calendar.MONTH, -1);
        try
        {
            con = DBResource.getConnection();
            ORManager orMgr = DBResource.getORManager();
            DBAccess db = new DBAccess(con, orMgr);

            Vector criWbhs = new Vector();
            criWbhs.add("ND = '" + String.valueOf(time.get(Calendar.YEAR)) +
                        "'");
            criWbhs.add("YF = '" + String.valueOf(time.get(Calendar.MONTH) + 1) +
                        "' order by bzdm");

            List wbhsList = db.query(Wbhs.class, criWbhs);
            int size = wbhsList.size();
            Map wbhsMap = new HashMap(size);
            for(int i = 0; i < size; i++)
            {
                Wbhs wbhs = (Wbhs)wbhsList.get(i);
                wbhsMap.put(wbhs.getBzdm(), wbhs);
            }
            ret.put(CodeTable.WBHS_LIST, wbhsList);
            ret.put(CodeTable.WBHS_MAP, wbhsMap);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e, "查询币种代码数据失败！");
        }
        finally
        {
            DBResource.destroyConnection(con);
        }

        return ret;
    }

    /**保存自然人申报数据
     * modified by QianChao. 2005.10.24
     */
    private Object doSaveSbData(VOPackage vo) throws BaseException
    {
        HashMap hm = (HashMap)vo.getData();
        
//        DzyjsjVO dzyj = (DzyjsjVO)hm.get(ZhsbMapConstant.CA_QMSJ_VO);
        ZrrsbInfor zrrsbInfor = (ZrrsbInfor)hm.get(ZhsbMapConstant.SBSJ);
        Zrrgrsdsz zrrgrsdsz = null;
        List zrrgrsdsmxList = null;
        List wbzhrmbList = null;
        DeclareInfor declare = null;
        UserData ud = vo.getUserData();
        HashMap dataMap = new HashMap();
        HashMap ret = null;
        String showMsg = null;
        try
        {

            if(zrrsbInfor != null)
            {
                zrrgrsdsz = zrrsbInfor.getZrrgrsdsz();
                zrrgrsdsmxList = zrrsbInfor.getZrrsbmxList();
                wbzhrmbList = zrrsbInfor.getWbzhrmbList();
                declare = zrrsbInfor.getDeclareInfor();
            }
            else
            {
                throw ExceptionUtil.getBaseException(new Exception("找不到自然人申报信息！"));
            }

            //缴款数据
            Sbjkzb sbjkzb = declare.getSbjkzb();
            declare.setIsReturnPaymentInfo(true); //返回数据

            ZhsbProcessor zhsbProcessor = new ZhsbProcessor();
            //为该次申报的该税款类型取一个申报编号
            String sbbh;
            try
            {
                sbbh = zhsbProcessor.getSbbh(sbjkzb.getJsjdm());
            }
            catch(Exception e)
            {
                throw ExceptionUtil.getBaseException(e);
            }

            //生成申报数据
            //返回的数据是  list－－list
            Object obj = zhsbProcessor.createJkInfor(declare, sbbh);
            
            /**begin =====================================处理一张缴款书合计小于一元情况================================================**/
            
            if(declare.getPrintTag() == CodeConstant.PRINT_YPDS_KM) { //一票多税
            	boolean isReturnPaymentInfo = declare.isIsReturnPaymentInfo();
            	if(isReturnPaymentInfo) {
            		List rtnData = (List) obj;
            		for(int i=0; i<rtnData.size(); i++) {
            			
            			List list = (List) rtnData.get(i);
            			for(int j=0; j<list.size(); j++) {
            				DeclareInfor declareInfor = (DeclareInfor) list.get(j);
                			if(declareInfor.getSbjkzb().getSjje().longValue() < 1) {
                				zhsbProcessor.insertToHisBySbbh(sbbh, vo.getUserData());
                				zhsbProcessor.deleteTempJksBySbbh(sbbh);
                				showMsg = "缴款书金额不足1元，根据国家税务总局2012年25号公告的规定，不予征收!";
                			}
            			}
            		}
            	}
            } else if(declare.getPrintTag() == CodeConstant.PRINT_YPYS) { //一票多税
            	boolean isReturnPaymentInfo = declare.isIsReturnPaymentInfo();
            	if(isReturnPaymentInfo) {
            		List rtnData = (List) obj;
            		for(int i=0; i<rtnData.size(); i++) {
        				DeclareInfor declareInfor = (DeclareInfor) rtnData.get(i);
            			if(declareInfor.getSbjkzb().getSjje().longValue() < 1) {
            				zhsbProcessor.insertToHisBySbbh(sbbh, vo.getUserData());
            				zhsbProcessor.deleteTempJksBySbbh(sbbh);
            				showMsg = "缴款书金额不足1元，根据国家税务总局2012年25号公告的规定，不予征收!";
            			}
            		}
            	}
            
            	
            }
            
            /**end   =====================================处理一张缴款书合计小于一元情况================================================**/

            //填充申报编号
            zrrgrsdsz.setSbbh(sbbh);

            for(int i = 0; i < zrrgrsdsmxList.size(); i++)
            {
                ( (Zrrgrsdsmx)zrrgrsdsmxList.get(i)).setSbbh(sbbh);
            }
            for(int j = 0; j < wbzhrmbList.size(); j++)
            {
                ( (Wbzhrmb)wbzhrmbList.get(j)).setSbbh(sbbh);
            }
            //插入数据
            insertSbData(zrrgrsdsz, zrrgrsdsmxList, wbzhrmbList);

            //对返回的缴款数据进行格式化封装处理
            if(declare.getPrintTag() == CodeConstant.PRINT_YPDS_KM)
            { //一票多税转换map
                ret = TranslateHelper.translateLL2Map( (List)obj);
            }
            else
            {
                ret = new HashMap();
                List dilist = (List)obj;
                SBData sb = null;
                for(int i = 0; i < dilist.size(); i++)
                {
                    DeclareInfor di = (DeclareInfor)dilist.get(i);
                    sb = (SBData)ret.get(di.getSbjkzb().getSbbh());
                    if(sb == null)
                    {
                        sb = new SBData();
                        ret.put(di.getSbjkzb().getSbbh(), sb);
                    }
                    sb.addDeclareInfor(di);
                }
            }

            if(ret.size() != 1)
            {
                throw ExceptionUtil.getBaseException(new Exception("生成多个申报编号！"));
            }

            //如果是CA用户,调用扣款接口.
//            if (ud.getCaflag()) {
//              com.ttsoft.bjtax.sfgl.proxy.ServiceProxy proxy =
//                  new com.ttsoft.bjtax.sfgl.proxy.ServiceProxy();
//
//              String jsjdm = ud.getXtsbm1();
//
//              Sfxy sfxy = proxy.getSfxySskkh(jsjdm);
//              if (sfxy != null && sfxy.getZh() != null) {
//                dataMap.put(ZhsbMapConstant.SFXY, sfxy); //封装三方协议数据到map中
//
//                // 调用扣款接口
//                SKHAdaptor sa = new SKHAdaptor();
//
//                List jkslist = InterFaceProcessor.getYpdsJks(sbbh);
//                /*
//                                     sa.transferMoneyFromNsrZhToGk(sfxy.getYhdm()
//                                              , sfxy.getZh()
//                 , declare.getSbjkzb().getSwjgzzjgdm().substring(0, 2)
//                                              , hjzse
//                                              , jkslist
//                                              , declare.getSbjkzb().getSwjgzzjgdm()
//                                              , ud);
//                 */
//
//              }
//              //更新签名数据表中的税票编号
//              dzyj.setYwuid(sbbh);
//              CAProxy.getInstance().updateSignedData(dzyj);
//            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
//          if (vo.getUserData().getCaflag()) {
//            try {
//              //出错删除签名
//              YYAQProxy.getInstance().deleteSignedData(calsh);
//            }
//            catch (Exception ex) {
//              ex.printStackTrace();
//            }
//          }
            throw ExceptionUtil.getBaseException(e);
        }
        if(showMsg != null) {
        	ret.put("showMsg", showMsg);
        }
        return ret;
    }

    //插入自然申报数据,填写创建日期
    private void insertSbData(Zrrgrsdsz zrrgrsdsz, List zrrgrsdsmxList,
                              List wbzhrmbList) throws BaseException
    {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        zrrgrsdsz.setSbrq(TinyTools.second2Day(now));
        zrrgrsdsz.setLrrq(now);
        Zrrgrsdsmx zrrgrsdsmx = null;
        Wbzhrmb wbzhrmb = null;
        Connection conn = null;
        ORManager orMgr = null;
        try
        {
            conn = DBResource.getConnection();
            orMgr = DBResource.getORManager();

            //插入自然人主表数据
            orMgr.makePersistent(0, conn, zrrgrsdsz);

            //插入自然人明细数据
            for(int i = 0; i < zrrgrsdsmxList.size(); i++)
            {
                zrrgrsdsmx = (Zrrgrsdsmx)zrrgrsdsmxList.get(i);
                zrrgrsdsmx.setSbrq(TinyTools.second2Day(now));
                zrrgrsdsmx.setLrrq(now);
                orMgr.makePersistent(0, conn, zrrgrsdsmx);
            }
            //插入外币折合人民币数据
            for(int i = 0; i < wbzhrmbList.size(); i++)
            {
                wbzhrmb = (Wbzhrmb)wbzhrmbList.get(i);
                wbzhrmb.setLrrq(now);
                wbzhrmb.setSbrq(TinyTools.second2Day(now));
                orMgr.makePersistent(0, conn, wbzhrmb);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex, "保存申报数据失败,请与管理员联系!");
        }
        finally
        {
            DBResource.destroyConnection(conn);
        }
    }

    /**
     * 删除自然人的申报数据
     * @param voPackage 前端传入的数据
     * @throws BaseException
     */
    private void doDel(VOPackage voPackage) throws BaseException
    {
        //作废的申报编号
        String zfSbbh = (String) ( (Map)voPackage.getData()).get(
            ZrrsbActionConstant.SBBH);
        String jsjdm = (String) ( (Map)voPackage.getData()).get(
            ZrrsbActionConstant.JSJDM);
        String swjgzzjgdm = (String) ( (Map)voPackage.getData()).get(
            "WSSB_SWJGZZJGDM");

        String qxdm = swjgzzjgdm.substring(0, 2);

        String sql = null;

        sql = " WHERE QXDM = '" + qxdm
            + "' AND SBBH = '" + zfSbbh + "'";

        String sqlZb = null;
        sqlZb = " WHERE ZYRQ >= to_date('20040101','yyyymmdd') AND QXDM = '"
            + qxdm + "' AND JSJDM = '" + jsjdm
            + "' AND SBBH = '" + zfSbbh
            + "' AND ((substr(zwbs, 1, 1) = '0') AND (substr(zwbs, 20, 1) = '0' ))";
        //modified by hazhengze 2006-1-9 Start
        //String sqlJkpzh = "select count(*) from sbdb.sb_jl_sbjkzb  " +  sql;
        String sqlJkpzh = "select count(*) from sbdb.sb_jl_sbjkzb  " +  sql+" and jsjdm='"+jsjdm+"'";
        //modified by hazhengze 2006-1-9 end
        ResultSet rs = null;
        int zbCount = 0;
        int deleteCount[] = null;
        Connection conn = null;
        try
        {
            //获得数据库连接
            conn = DBResource.getConnection(DBResource.DB_SHENBAO);
            Statement st = conn.createStatement();
            StringBuffer sqlStringBuffer = new StringBuffer();

            Debug.out("sqlJkpzh=" + sqlJkpzh);

            rs = st.executeQuery(sqlJkpzh);
            rs.next();
            zbCount = rs.getInt(1);
            Debug.out("主表记录数 zbCount=" + zbCount);

            st.clearBatch();

            //定义sql语句，批处理


            //删除缴款明细数据
            //modified by hazhengze 2006-1-9 Start
            //sqlStringBuffer.append("DELETE FROM SBDB.SB_JL_SBJKMX ").append(sql);
            sqlStringBuffer.append("DELETE FROM SBDB.SB_JL_SBJKMX ").append(sql).append(" and jsjdm='"+jsjdm+"'");
             //modified by hazhengze 2006-1-9 end
            Debug.out("1==sqlStringBuffer.toString()=" + sqlStringBuffer.toString());

            st.addBatch(sqlStringBuffer.toString());

            //删除缴款主表数据
            sqlStringBuffer.setLength(0);
            sqlStringBuffer.append("DELETE FROM SBDB.SB_JL_SBJKZB ").append(
                sqlZb);

            Debug.out("2==sqlStringBuffer.toString()=" + sqlStringBuffer.toString());

            st.addBatch(sqlStringBuffer.toString());

            deleteCount = st.executeBatch();

            Debug.out("主表删除记录数 deleteCount[1]=" + deleteCount[1]);
            if (deleteCount[1] != zbCount)
            {
                throw new ApplicationException("要删除的申报已经缴款！");
            }



            sqlStringBuffer.setLength(0);
            st.clearBatch();

            //删除外币折合人民币数据
            sqlStringBuffer.append("DELETE FROM SBDB.SB_JL_WBZHRMB ").append(
                sql);

            Debug.out("3==sqlStringBuffer.toString()=" + sqlStringBuffer.toString());

            st.addBatch(sqlStringBuffer.toString());

            //删除个人所得税明细
            sqlStringBuffer.setLength(0);
            sqlStringBuffer.append("DELETE FROM SBDB.SB_JL_ZRRGRSDSMX ").append(
                sql);

            Debug.out("4==sqlStringBuffer.toString()=" + sqlStringBuffer.toString());

            st.addBatch(sqlStringBuffer.toString());

            //删除个人所得税主表
            sqlStringBuffer.setLength(0);
            sqlStringBuffer.append("DELETE FROM SBDB.SB_JL_ZRRGRSDSZ ").append(
                sql);

            Debug.out("5==sqlStringBuffer.toString()=" + sqlStringBuffer.toString());

            st.addBatch(sqlStringBuffer.toString());

            sqlStringBuffer.setLength(0);

            st.executeBatch(); //执行删除


        }
        catch(ApplicationException e)
        {
            throw e;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e, "作废缴款数据失败！");
        }
        finally
        {
            DBResource.destroyConnection(conn);
        }
    }

    /**
     * 查询自然人当期的未缴款的缴款书信息
     * modified by qianchao 2005.10.28
     * @param voPackage 前端传入的数据
     * @return HashMap 缴款书数据
     * @throws BaseException
     */
    private HashMap doQuery(VOPackage voPackage) throws BaseException
    {
        Connection conn = null;
        HashMap datamap = new HashMap();
        ZhsbProcessor zp = new ZhsbProcessor();
        try
        {
            String sjly = CodeConstant.SJLY_SB_ZRR_SBLR;

            //start add by qianchao 2005.10.25

            //HashMap datamap = zhsb.doQuery(voPackage, sjly);

            Map tempData = (HashMap)voPackage.getData(); //从前端获得map对象
            String swjgzzjgdm = (String)tempData.get("WSSB_SWJGZZJGDM"); //税务机关组织机构代码
            String jsjdm = (String)tempData.get(ZhsbMapConstant.JSJDM); //获取用户的计算机代码
            //注意日期格式，必须为:(YYYY-MM-DD)
            String whrq = (String)tempData.get(ZhsbMapConstant.WHRQ); //获取用户维护申报数据日期
            String qxdm = swjgzzjgdm.substring(0, 2); //区县代码

            //查询申报缴款主表的查询结果
            ArrayList zbResult = new ArrayList();
            //查询申报缴款明细表的查询结果
            ArrayList mxResult = new ArrayList();

            //过滤出已经有缴款记录的申报编号，帐页日期或者(帐务标识的第1位<>0or第20位<>0)
            //不管同一批数据是否有缴款记录，只要没有缴款，就允许修改

            //started modified by qianchao 2005-12-19
//            String sqlWhere =
//                "(SBBH NOT IN "
//                + "(SELECT SBBH FROM SBDB.SB_JL_SBJKZB "
//                + " WHERE ZYRQ >= to_date('20050101','yyyymmdd') AND QXDM = '"
//                + qxdm + "' AND JSJDM = '" + jsjdm + "'"
//                + " AND substr(to_char(SBRQ,'yyyy-mm-dd'),0,7) = substr('"
//                + whrq + "', 0, 7) AND SJLY = '" + sjly
//                + "' AND FSDM = '" + CodeConstant.FSDM_WSSB
//                + "' AND ((substr(zwbs, 1, 1) <> '0') OR (substr(zwbs, 20, 1) <> '0' ))"
//                + " GROUP BY sbbh))"
//                + " AND ZYRQ >= to_date('20050101','yyyymmdd') AND QXDM = '"
//                + qxdm + "' AND JSJDM = '" + jsjdm + "'"
//                + " AND substr(to_char(SBRQ,'yyyy-mm-dd'),0,7) = substr('"
//                + whrq + "', 0, 7) AND SJLY = '" + sjly
//                + "' AND FSDM = '" + CodeConstant.FSDM_WSSB
//                + "' ORDER BY SBRQ DESC, SBBH, JKPZH";

            String sqlWhere =
                "ZWBS like '0%0' "
                + " AND ND=to_char(sysdate,'yyyy') "
                + " AND ZYRQ >= to_date('20050101','yyyymmdd') AND QXDM = '"
                + qxdm + "' AND JSJDM = '" + jsjdm + "'"
                + " AND substr(to_char(SBRQ,'yyyy-mm-dd'),0,7) = substr('"
                + whrq + "', 0, 7) AND SJLY = '" + sjly
                + "' AND FSDM = '" + CodeConstant.FSDM_WSSB
                + "' ORDER BY SBRQ DESC, SBBH, JKPZH";

            //ended   modified by qianchao 2005-12-19

            Debug.out("strWhere=" + sqlWhere);
            Vector criteria = new Vector();
            criteria.add(sqlWhere);

            ORContext orCtx = new ORContext(Sbjkzb.class.getName(), criteria);

            //获得 ORManager
            ORManager orManager = DBResource.getORManager(DBResource.OR_SHENBAO);
            //获得数据库连接
            conn = DBResource.getConnection(DBResource.DB_SHENBAO);

            zbResult = (ArrayList)orManager.query(SESSION_ID, conn, orCtx); //查询申报缴款主表
            if(zbResult.size() == 0)
            {
                return null; //没有可以维护的申报数据！
            }
            //存在申报未缴款的当期数据
            Sbjkzb tempObj = (Sbjkzb)zbResult.get(0);
            String _jkpzh = tempObj.getJkpzh();
            //拼申报明细表的查询where条件
            StringBuffer sqlStrBuf = new StringBuffer();
            sqlStrBuf.append("(QXDM = '").append(qxdm)
                .append("' AND JKPZH IN ('");
            sqlStrBuf.append(_jkpzh);
            for(int i = 1; i < zbResult.size(); i++)
            {
                tempObj = (Sbjkzb)zbResult.get(i);
                _jkpzh = tempObj.getJkpzh();
                sqlStrBuf.append("','").append(_jkpzh);
            }
            sqlStrBuf.append("')")
                .append(") ORDER BY SBBH DESC, JKPZH");
            String sqlString = sqlStrBuf.toString();
            Vector criteriaMx = new Vector();
            criteriaMx.add(sqlString);

            ORContext orCtxMx = new ORContext(Sbjkmx.class.getName(), criteriaMx);
            mxResult = (ArrayList)orManager.query(SESSION_ID, conn, orCtxMx); //查询申报缴款明细表
            DBResource.destroyConnection(conn); //关闭数据库连接
            conn = null;
            // 接着对取回的数据进行格式封装处理
            datamap = (HashMap) zp.convertResult(zbResult, mxResult);


            /*
            SBData sb = null;
            for(int i = 1; i < zbResult.size(); i++)
            {
                tempObj = (Sbjkzb)zbResult.get(i);
                sb = (SBData)datamap.get(tempObj.getSbbh());
                if(sb == null)
                {
                    sb = new SBData();
                    datamap.put(tempObj.getSbbh(), sb);
                }
                sb.addSbjkzb(tempObj);
            }
            Sbjkmx mxObj = null;
            for(int i = 1; i < mxResult.size(); i++)
            {
                mxObj = (Sbjkmx)mxResult.get(i);
                sb = (SBData)datamap.get(tempObj.getSbbh());
                if(sb != null)
                {
                    sb.addSbjkmx(mxObj);
                }
            }
*/




            com.ttsoft.bjtax.sfgl.proxy.ServiceProxy proxy =
                new com.ttsoft.bjtax.sfgl.proxy.ServiceProxy();

            Debug.out("voPackage.getUserData()=" + voPackage.getUserData());
            jsjdm = voPackage.getUserData().getXtsbm1();

            Sfxy sfxy = proxy.getSfxySskkh(jsjdm);
            if(sfxy != null && sfxy.getZh() != null)
            {
                datamap.put(ZhsbMapConstant.SFXY, sfxy); //封装三方协议数据到map中
            }

            //格式化数据

            //end add by qianchao 2005.10.25
            return datamap;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex, "查询申报缴款数据失败！");
        }
        finally {
          DBResource.destroyConnection(conn);
        }
    }
}
