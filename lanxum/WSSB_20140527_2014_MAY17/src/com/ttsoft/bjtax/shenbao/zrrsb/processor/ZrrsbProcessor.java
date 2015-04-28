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
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����ģ��</p>
 * <p>Description: ��Ȼ�˸�������˰�걨</p>
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
     * �������
     * @param vo ǰ̨����
     * @return �����������ΪBooleanֵ
     * @throws BaseException �����쳣
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
     * ���ִ�����ѯ
     * @return ���±��ִ����
     * @throws BaseException �����쳣
     */
    private Map getBzList() throws BaseException
    {
        // ��һ���
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
            throw ExceptionUtil.getBaseException(e, "��ѯ���ִ�������ʧ�ܣ�");
        }
        finally
        {
            DBResource.destroyConnection(con);
        }

        return ret;
    }

    /**������Ȼ���걨����
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
                throw ExceptionUtil.getBaseException(new Exception("�Ҳ�����Ȼ���걨��Ϣ��"));
            }

            //�ɿ�����
            Sbjkzb sbjkzb = declare.getSbjkzb();
            declare.setIsReturnPaymentInfo(true); //��������

            ZhsbProcessor zhsbProcessor = new ZhsbProcessor();
            //Ϊ�ô��걨�ĸ�˰������ȡһ���걨���
            String sbbh;
            try
            {
                sbbh = zhsbProcessor.getSbbh(sbjkzb.getJsjdm());
            }
            catch(Exception e)
            {
                throw ExceptionUtil.getBaseException(e);
            }

            //�����걨����
            //���ص�������  list����list
            Object obj = zhsbProcessor.createJkInfor(declare, sbbh);
            
            /**begin =====================================����һ�Žɿ���ϼ�С��һԪ���================================================**/
            
            if(declare.getPrintTag() == CodeConstant.PRINT_YPDS_KM) { //һƱ��˰
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
                				showMsg = "�ɿ������1Ԫ�����ݹ���˰���ܾ�2012��25�Ź���Ĺ涨����������!";
                			}
            			}
            		}
            	}
            } else if(declare.getPrintTag() == CodeConstant.PRINT_YPYS) { //һƱ��˰
            	boolean isReturnPaymentInfo = declare.isIsReturnPaymentInfo();
            	if(isReturnPaymentInfo) {
            		List rtnData = (List) obj;
            		for(int i=0; i<rtnData.size(); i++) {
        				DeclareInfor declareInfor = (DeclareInfor) rtnData.get(i);
            			if(declareInfor.getSbjkzb().getSjje().longValue() < 1) {
            				zhsbProcessor.insertToHisBySbbh(sbbh, vo.getUserData());
            				zhsbProcessor.deleteTempJksBySbbh(sbbh);
            				showMsg = "�ɿ������1Ԫ�����ݹ���˰���ܾ�2012��25�Ź���Ĺ涨����������!";
            			}
            		}
            	}
            
            	
            }
            
            /**end   =====================================����һ�Žɿ���ϼ�С��һԪ���================================================**/

            //����걨���
            zrrgrsdsz.setSbbh(sbbh);

            for(int i = 0; i < zrrgrsdsmxList.size(); i++)
            {
                ( (Zrrgrsdsmx)zrrgrsdsmxList.get(i)).setSbbh(sbbh);
            }
            for(int j = 0; j < wbzhrmbList.size(); j++)
            {
                ( (Wbzhrmb)wbzhrmbList.get(j)).setSbbh(sbbh);
            }
            //��������
            insertSbData(zrrgrsdsz, zrrgrsdsmxList, wbzhrmbList);

            //�Է��صĽɿ����ݽ��и�ʽ����װ����
            if(declare.getPrintTag() == CodeConstant.PRINT_YPDS_KM)
            { //һƱ��˰ת��map
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
                throw ExceptionUtil.getBaseException(new Exception("���ɶ���걨��ţ�"));
            }

            //�����CA�û�,���ÿۿ�ӿ�.
//            if (ud.getCaflag()) {
//              com.ttsoft.bjtax.sfgl.proxy.ServiceProxy proxy =
//                  new com.ttsoft.bjtax.sfgl.proxy.ServiceProxy();
//
//              String jsjdm = ud.getXtsbm1();
//
//              Sfxy sfxy = proxy.getSfxySskkh(jsjdm);
//              if (sfxy != null && sfxy.getZh() != null) {
//                dataMap.put(ZhsbMapConstant.SFXY, sfxy); //��װ����Э�����ݵ�map��
//
//                // ���ÿۿ�ӿ�
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
//              //����ǩ�����ݱ��е�˰Ʊ���
//              dzyj.setYwuid(sbbh);
//              CAProxy.getInstance().updateSignedData(dzyj);
//            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
//          if (vo.getUserData().getCaflag()) {
//            try {
//              //����ɾ��ǩ��
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

    //������Ȼ�걨����,��д��������
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

            //������Ȼ����������
            orMgr.makePersistent(0, conn, zrrgrsdsz);

            //������Ȼ����ϸ����
            for(int i = 0; i < zrrgrsdsmxList.size(); i++)
            {
                zrrgrsdsmx = (Zrrgrsdsmx)zrrgrsdsmxList.get(i);
                zrrgrsdsmx.setSbrq(TinyTools.second2Day(now));
                zrrgrsdsmx.setLrrq(now);
                orMgr.makePersistent(0, conn, zrrgrsdsmx);
            }
            //��������ۺ����������
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
            throw ExceptionUtil.getBaseException(ex, "�����걨����ʧ��,�������Ա��ϵ!");
        }
        finally
        {
            DBResource.destroyConnection(conn);
        }
    }

    /**
     * ɾ����Ȼ�˵��걨����
     * @param voPackage ǰ�˴��������
     * @throws BaseException
     */
    private void doDel(VOPackage voPackage) throws BaseException
    {
        //���ϵ��걨���
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
            //������ݿ�����
            conn = DBResource.getConnection(DBResource.DB_SHENBAO);
            Statement st = conn.createStatement();
            StringBuffer sqlStringBuffer = new StringBuffer();

            Debug.out("sqlJkpzh=" + sqlJkpzh);

            rs = st.executeQuery(sqlJkpzh);
            rs.next();
            zbCount = rs.getInt(1);
            Debug.out("�����¼�� zbCount=" + zbCount);

            st.clearBatch();

            //����sql��䣬������


            //ɾ���ɿ���ϸ����
            //modified by hazhengze 2006-1-9 Start
            //sqlStringBuffer.append("DELETE FROM SBDB.SB_JL_SBJKMX ").append(sql);
            sqlStringBuffer.append("DELETE FROM SBDB.SB_JL_SBJKMX ").append(sql).append(" and jsjdm='"+jsjdm+"'");
             //modified by hazhengze 2006-1-9 end
            Debug.out("1==sqlStringBuffer.toString()=" + sqlStringBuffer.toString());

            st.addBatch(sqlStringBuffer.toString());

            //ɾ���ɿ���������
            sqlStringBuffer.setLength(0);
            sqlStringBuffer.append("DELETE FROM SBDB.SB_JL_SBJKZB ").append(
                sqlZb);

            Debug.out("2==sqlStringBuffer.toString()=" + sqlStringBuffer.toString());

            st.addBatch(sqlStringBuffer.toString());

            deleteCount = st.executeBatch();

            Debug.out("����ɾ����¼�� deleteCount[1]=" + deleteCount[1]);
            if (deleteCount[1] != zbCount)
            {
                throw new ApplicationException("Ҫɾ�����걨�Ѿ��ɿ");
            }



            sqlStringBuffer.setLength(0);
            st.clearBatch();

            //ɾ������ۺ����������
            sqlStringBuffer.append("DELETE FROM SBDB.SB_JL_WBZHRMB ").append(
                sql);

            Debug.out("3==sqlStringBuffer.toString()=" + sqlStringBuffer.toString());

            st.addBatch(sqlStringBuffer.toString());

            //ɾ����������˰��ϸ
            sqlStringBuffer.setLength(0);
            sqlStringBuffer.append("DELETE FROM SBDB.SB_JL_ZRRGRSDSMX ").append(
                sql);

            Debug.out("4==sqlStringBuffer.toString()=" + sqlStringBuffer.toString());

            st.addBatch(sqlStringBuffer.toString());

            //ɾ����������˰����
            sqlStringBuffer.setLength(0);
            sqlStringBuffer.append("DELETE FROM SBDB.SB_JL_ZRRGRSDSZ ").append(
                sql);

            Debug.out("5==sqlStringBuffer.toString()=" + sqlStringBuffer.toString());

            st.addBatch(sqlStringBuffer.toString());

            sqlStringBuffer.setLength(0);

            st.executeBatch(); //ִ��ɾ��


        }
        catch(ApplicationException e)
        {
            throw e;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e, "���Ͻɿ�����ʧ�ܣ�");
        }
        finally
        {
            DBResource.destroyConnection(conn);
        }
    }

    /**
     * ��ѯ��Ȼ�˵��ڵ�δ�ɿ�Ľɿ�����Ϣ
     * modified by qianchao 2005.10.28
     * @param voPackage ǰ�˴��������
     * @return HashMap �ɿ�������
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

            Map tempData = (HashMap)voPackage.getData(); //��ǰ�˻��map����
            String swjgzzjgdm = (String)tempData.get("WSSB_SWJGZZJGDM"); //˰�������֯��������
            String jsjdm = (String)tempData.get(ZhsbMapConstant.JSJDM); //��ȡ�û��ļ��������
            //ע�����ڸ�ʽ������Ϊ:(YYYY-MM-DD)
            String whrq = (String)tempData.get(ZhsbMapConstant.WHRQ); //��ȡ�û�ά���걨��������
            String qxdm = swjgzzjgdm.substring(0, 2); //���ش���

            //��ѯ�걨�ɿ�����Ĳ�ѯ���
            ArrayList zbResult = new ArrayList();
            //��ѯ�걨�ɿ���ϸ��Ĳ�ѯ���
            ArrayList mxResult = new ArrayList();

            //���˳��Ѿ��нɿ��¼���걨��ţ���ҳ���ڻ���(�����ʶ�ĵ�1λ<>0or��20λ<>0)
            //����ͬһ�������Ƿ��нɿ��¼��ֻҪû�нɿ�������޸�

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

            //��� ORManager
            ORManager orManager = DBResource.getORManager(DBResource.OR_SHENBAO);
            //������ݿ�����
            conn = DBResource.getConnection(DBResource.DB_SHENBAO);

            zbResult = (ArrayList)orManager.query(SESSION_ID, conn, orCtx); //��ѯ�걨�ɿ�����
            if(zbResult.size() == 0)
            {
                return null; //û�п���ά�����걨���ݣ�
            }
            //�����걨δ�ɿ�ĵ�������
            Sbjkzb tempObj = (Sbjkzb)zbResult.get(0);
            String _jkpzh = tempObj.getJkpzh();
            //ƴ�걨��ϸ��Ĳ�ѯwhere����
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
            mxResult = (ArrayList)orManager.query(SESSION_ID, conn, orCtxMx); //��ѯ�걨�ɿ���ϸ��
            DBResource.destroyConnection(conn); //�ر����ݿ�����
            conn = null;
            // ���Ŷ�ȡ�ص����ݽ��и�ʽ��װ����
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
                datamap.put(ZhsbMapConstant.SFXY, sfxy); //��װ����Э�����ݵ�map��
            }

            //��ʽ������

            //end add by qianchao 2005.10.25
            return datamap;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex, "��ѯ�걨�ɿ�����ʧ�ܣ�");
        }
        finally {
          DBResource.destroyConnection(conn);
        }
    }
}
