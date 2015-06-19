package com.ttsoft.bjtax.shenbao.sbzl.dsdzdk.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.text.NumberFormat;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Vector;
import java.util.HashMap;

import com.ekernel.db.or.ORManager;
import com.ekernel.db.or.ORContext;

import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

import com.ttsoft.bjtax.shenbao.constant.CodeConstant;
import com.ttsoft.bjtax.shenbao.model.client.DeclareInfor;
import com.ttsoft.bjtax.shenbao.model.client.SdwszsbhzCx;
import com.ttsoft.bjtax.shenbao.model.domain.Dsdzdkmx;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkmx;
import com.ttsoft.bjtax.shenbao.model.domain.Sdwszsbhz;
import com.ttsoft.bjtax.shenbao.sbzl.dsdzdk.DsdzdkActionConstant;
import com.ttsoft.bjtax.shenbao.sbzl.dsdzdk.DsdzdkConstant;
import com.ttsoft.bjtax.shenbao.service.processor.InterFaceProcessor;
import com.ttsoft.bjtax.shenbao.util.DBResource;
import com.ttsoft.bjtax.shenbao.util.FindObjInList;
import com.ttsoft.common.util.Debug;
import com.ttsoft.bjtax.shenbao.zhsb.processor.ZhsbProcessor;
import com.ttsoft.bjtax.shenbao.util.TinyTools;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.dj.model.YHZH;
import com.ttsoft.bjtax.dj.proxy.ServiceProxy;
import com.ttsoft.framework.exception.*;
import com.ttsoft.bjtax.sfgl.common.model.*;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����ģ��</p>
 * <p>Description:���۴�������processor </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: TTSOFT</p>
 * @author stone
 * @version 1.0 2003-9-9
 */

public class DsdzdkProcessor implements Processor
{
    public DsdzdkProcessor()
    {
    }

    //������ԴΪ����
    private static final String SJLY_DR = "1";

    //ORSession��ʶ
    private static final long SESSION_ID = 0;

    /**
     * ������۴���������ϸ����
     * @param vo ǰ̨������VOPackage
     * @return �д�ȷ��
     * @throws BaseException �����쳣
     */
    public Object process(VOPackage vo) throws BaseException
    {
        if(vo.getAction() == DsdzdkActionConstant.ACTION_SAVESBMX)
        {
            return doSaveSbData(vo);
        }
        if(vo.getAction() == DsdzdkActionConstant.ACTION_QUERYHZ)
        {
            return doQueryHz(vo);
        }
        if(vo.getAction() == DsdzdkActionConstant.ACTION_ERASEHZ)
        {
            return doEraseHz(vo);
        }
        if(vo.getAction() == DsdzdkActionConstant.ACTION_PRINT)  //��ӡ����
        {
            return doPrint(vo);
        }
        throw new SystemException("no such mothod");
    }

    //������������ϸ���ݲ�������д�ɿ��������ϸ���ɻ�������
    private List doSaveSbData(VOPackage vo) throws BaseException
    {
        List dsdzdkmxList = (List)vo.getData();
        if(dsdzdkmxList == null)
        {
            throw ExceptionUtil.getBaseException(new Exception("��������!"));
        }

        Connection conn = null;//���ݿ�����
        ORManager ormgr = null;//ORʵ��
        try
        {
            Timestamp cjrq = new Timestamp((new Date()).getTime());
            Dsdzdkmx dsdzdkmx = null;
            //�������ݻ�����д�ɿ��������ϸ��
            List dsdzdkmxListTmp = new ArrayList(dsdzdkmxList);
            List DeclareList = (List)createJkdata(dsdzdkmxList);
            dsdzdkmxList = dsdzdkmxListTmp;
            String sql = "select sbdb.seq_sb_hzdh.nextval from dual";

            conn = DBResource.getConnection();//�õ�����
            ormgr = DBResource.getORManager();//�õ�orʵ��
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            //���ܵ���
            long hzdhindex =  rs.getLong("nextval");
            NumberFormat nmbFormat = new DecimalFormat("00000000");
            String hzdh     =  nmbFormat.format(hzdhindex);
            //�������ܱ��������ݿ���
            List hzList = CreateHzData(DeclareList,hzdh);
            for(int i=0; i<hzList.size(); i++)
            {
                ormgr.makePersistent(SESSION_ID,conn,hzList.get(i));
            }
            Sbjkzb sbjkzb = ((DeclareInfor)DeclareList.get(0)).getSbjkzb();
            String qxdm = sbjkzb.getQxdm();  //���ش���
            for(int i=0;i<dsdzdkmxList.size(); i++)
            {
                dsdzdkmx = (Dsdzdkmx)dsdzdkmxList.get(i);
                dsdzdkmx.setSbhzdh(hzdh);
                dsdzdkmx.setCjrq(cjrq);
                dsdzdkmx.setLrrq(cjrq);
                dsdzdkmx.setQxdm(qxdm);
                dsdzdkmx.setSjly(SJLY_DR);  //������Դ
                ormgr.makePersistent(SESSION_ID,conn,dsdzdkmx);
            }
            return hzList;
        }
        catch(Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex,"������۴���������ϸ����ʧ��!");
        }
        finally
        {
            DBResource.destroyConnection(conn);
        }
    }

    //�����ɿ��������ϸ���ݲ��������ݿ�
    private Object createJkdata(List dsdzdkmxList) throws BaseException
    {
        String jsjdm = ( (Dsdzdkmx)dsdzdkmxList.get(0)).getJsjdm();
        List param = new ArrayList();
        param.add("getSzsmdm");
        //����szsmdm��ֳ���ӵ����ͬszsmdm��list��ɵ�list
        List dsdzdkmxBySzsmdmList = null;
        //��ͬszsmdm��List
        List   sameszsmdmList  = null;
        BigDecimal jsjetotal   = null;
        BigDecimal sjsetotal   = null;
        Dsdzdkmx   dsdzdkmxTmp = null;
        Sbjkmx     sbjkmx      = null;
        List       sbjkmxList  = new ArrayList();

        //�õ��˼��������ĵǼ���Ϣ����д���ӱ�ֵ����Ķ�Ӧ�ֶ�
        ServiceProxy serviceProxy = new ServiceProxy();
        Map map = serviceProxy.getDjInfo(jsjdm);
        SWDJJBSJ swdjjbsj = (SWDJJBSJ)map.get("JBSJ");

        // ����걨���
        ZhsbProcessor zhsbPro = new ZhsbProcessor();
        String sbbh;
        //started added by qianchao 2005-12-8
        try
        {
            sbbh = zhsbPro.getSbbh(jsjdm);
        }
        catch (Exception e)
        {
            throw ExceptionUtil.getBaseException(e,"ȡ�걨���ʧ��!");
        }
        //ended   added by qianchao 2005-12-8
        
        //����
        dsdzdkmxBySzsmdmList =
            FindObjInList.splitListByParam(dsdzdkmxList, Dsdzdkmx.class, param);
        //ȡ��ǰ���
        Timestamp now = new Timestamp(System.currentTimeMillis());
        String nd = new SimpleDateFormat("yyyy").format(now);

        //���ɽɿ���ϸ����
        for(int i = 0; i < dsdzdkmxBySzsmdmList.size(); i++)
        {
            sbjkmx = new Sbjkmx();
            sameszsmdmList = (List)dsdzdkmxBySzsmdmList.get(i);

            jsjetotal = new BigDecimal("0");
            sjsetotal = new BigDecimal("0");

            for(int j = 0; j < sameszsmdmList.size(); j++)
            {
                dsdzdkmxTmp = (Dsdzdkmx)sameszsmdmList.get(j);
                if(dsdzdkmxTmp.getJsje() != null)
                {
                    jsjetotal = jsjetotal.add(dsdzdkmxTmp.getJsje());
                }
                if(dsdzdkmxTmp.getSjse() != null)
                {
                    sjsetotal = sjsetotal.add(dsdzdkmxTmp.getSjse());
                }
            }
            //��д�걨��ϸ����
            sbjkmx.setJsjdm(dsdzdkmxTmp.getJsjdm());
            sbjkmx.setJsje(jsjetotal);
            sbjkmx.setSjse(sjsetotal);
            sbjkmx.setRkje(sjsetotal);  //�����
            sbjkmx.setSkssjsrq(dsdzdkmxTmp.getSkssjsrq());
            sbjkmx.setSkssksrq(dsdzdkmxTmp.getSkssksrq());
            sbjkmx.setSzdm(dsdzdkmxTmp.getSzdm());
            sbjkmx.setSzsmdm(dsdzdkmxTmp.getSzsmdm());
            sbjkmx.setZqlxdm(CodeConstant.ZQLXDM_MONTH);
            sbjkmx.setSwjgzzjgdm(swdjjbsj.getSwjgzzjgdm());  //˰�������֯����
            sbjkmx.setNd(nd);
            sbjkmx.setCjrq(now);  //��������
            sbjkmx.setLrrq(now);  //¼������
            sbjkmx.setQxdm(swdjjbsj.getSwjgzzjgdm().substring(0,2)); //���ش���
            sbjkmxList.add(sbjkmx);   //��ӵ��ɿ���ϸ������
        }

        //�ɿ���������
        Sbjkzb sbjkzb = new Sbjkzb();
        sbjkzb.setJsjdm(jsjdm);
        sbjkzb.setLrrq(now);
        sbjkzb.setCjrq(now);
        sbjkzb.setDjzclxdm(swdjjbsj.getDjzclxdm());
        sbjkzb.setFsdm(CodeConstant.FSDM_WSSB);    //���շ�ʽΪ���Ϸ�ʽ
        sbjkzb.setGjbzhydm(swdjjbsj.getGjbzhydm());
        sbjkzb.setLrr(dsdzdkmxTmp.getLrr());
        sbjkzb.setLsgxdm(swdjjbsj.getLsgxdm());
        sbjkzb.setSbrq(TinyTools.second2Day(now));
        sbjkzb.setZyrq(sbjkzb.getSbrq());
        sbjkzb.setSklxdm(CodeConstant.SKLXDM_SDJJ);
        sbjkzb.setSwjgzzjgdm(swdjjbsj.getSwjgzzjgdm());

        com.ttsoft.bjtax.sfgl.proxy.ServiceProxy sf = new com.ttsoft.bjtax.sfgl.proxy.ServiceProxy();
        Sfxy sfxy = sf.getSfxyInfo(jsjdm, new Date());
        if (sfxy != null)
        {
            sbjkzb.setYhdm(sfxy.getYhdm()); //���д���
            sbjkzb.setYhmc(sfxy.getYhmc()); //��������
            sbjkzb.setZh(sfxy.getZh()); //�����˺�

            sbjkzb.setClbjdm(CodeConstant.CLBJDM_DHK);  //������
        }
        else
        {
            List banks = (List)map.get("YHZH");
            if (banks.size() > 0)
            {
                YHZH yhzh = (YHZH)banks.get(0);
                sbjkzb.setYhdm(yhzh.getYhdm()); //���д���
                sbjkzb.setYhmc(yhzh.getKhyhmc()); //��������
                sbjkzb.setZh(yhzh.getZh()); //�����˺�
            }

            sbjkzb.setClbjdm(CodeConstant.CLBJDM_YSB);  //���걨
        }

        sbjkzb.setZsswjgzzjgdm(swdjjbsj.getSwjgzzjgdm());
        sbjkzb.setSjly(CodeConstant.SJLY_SB_SDHZ); //������Դ
        sbjkzb.setNd(nd);
        sbjkzb.setQxdm(swdjjbsj.getSwjgzzjgdm().substring(0, 2));

        DeclareInfor declareInfor = new DeclareInfor(sbjkzb, sbjkmxList);
        declareInfor.setIsReturnPaymentInfo(true);  //�������ݱ�ʶ
        declareInfor.setPrintTag(CodeConstant.PRINT_YPYS); //ƾ֤���ͣ�һƱһ˰

        //�����ۺ��걨processor���ɽɿ��������ϸ���ݣ������ɿ�ƾ֤��¼
        return zhsbPro.createJkInfor(declareInfor, sbbh);
    }

    //������������
    private List CreateHzData(List jkpzhInforList,String hzdh)
    {
        //�õ��ɿ�����
        Sbjkzb sbjkzb = null;
        Sdwszsbhz sdwszsbhz = null;
        List sdwszsbhzList = new ArrayList();
        //����ʱ��
        Timestamp hzrq = new Timestamp(System.currentTimeMillis());
        String nd = new SimpleDateFormat("yyyy").format(hzrq);

        for(int i=0; i<jkpzhInforList.size(); i++)
        {
            Debug.out(jkpzhInforList.get(i).getClass());
            sbjkzb = ((DeclareInfor)jkpzhInforList.get(i)).getSbjkzb();
            sdwszsbhz = new Sdwszsbhz();
            sdwszsbhz.setClbjdm(CodeConstant.CLBJDM_YSB);
            sdwszsbhz.setJsjdm(sbjkzb.getJsjdm());
            sdwszsbhz.setHzjsrq(hzrq);
            sdwszsbhz.setHzksrq(hzrq);
            sdwszsbhz.setHzrq(hzrq);
            sdwszsbhz.setJkpzh(sbjkzb.getJkpzh());
            sdwszsbhz.setLrr(sbjkzb.getLrr());
            sdwszsbhz.setSbhzdh(hzdh);
            sdwszsbhz.setSjly(SJLY_DR);
            sdwszsbhz.setSjse(sbjkzb.getSjje());
            sdwszsbhz.setSwjgzzjgdm(sbjkzb.getSwjgzzjgdm());
            sdwszsbhz.setSbbh(sbjkzb.getSbbh());
            sdwszsbhz.setSjly(SJLY_DR);  //������Դ
            sdwszsbhz.setNd(nd);
            sdwszsbhz.setCjrq(hzrq);
            sdwszsbhz.setLrrq(hzrq);
            sdwszsbhz.setQxdm(sbjkzb.getSwjgzzjgdm().substring(0, 2));

            sdwszsbhzList.add(sdwszsbhz);
        }
        return sdwszsbhzList;
    }

    /**
     * �������ܵ�
     * @param vo VOPackage
     * @return List
     * @throws BaseException
     */
    private List doQueryHz(VOPackage vo) throws BaseException
    {
        List hzDataList = null;
        String jsjdm = (String)vo.getData();
        Connection conn = null; //���ݿ�����
        ORManager ormgr = null; //ORʵ��
        try
        {
            //ȡ���ش���
            ServiceProxy serviceProxy = new ServiceProxy();
            Map map = serviceProxy.getDjInfo(jsjdm);
            SWDJJBSJ swdjjbsj = (SWDJJBSJ)map.get("JBSJ");
            String qxdm = (String)swdjjbsj.getSwjgzzjgdm().substring(0,2);

            //���˳��Ѿ��нɿ��¼���걨��ţ���ҳ���ڻ��߽ɿ����ڲ�Ϊ��
            SimpleDateFormat simpleDataFormat = new SimpleDateFormat("yyMM");
            String rq = simpleDataFormat.format(new Date());  //��ǰ����:YYYY-MM-DD
            String sqlW = "QXDM = '" + qxdm + "' AND JSJDM = '" + jsjdm
                + "' AND (substr(zwbs, 1, 1) <> '0' or substr(zwbs, 20, 1) <> '0')"
//                + " AND substr(to_char(SBRQ,'yyyymmdd'),3,4) = '" + rq + "'"  //������Ϊ�������ݣ���������
                + " AND SJLY = '" + CodeConstant.SJLY_SB_SDHZ
                + "' AND FSDM = '"+CodeConstant.FSDM_WSSB+"'";
            String sqlQuery = "SELECT DISTINCT SBBH FROM SBDB.SB_JL_SBJKZB WHERE " + sqlW;
            //����������ϸ�����������걨�������걨����������
            String sdmxQuery = "SELECT DISTINCT SBHZDH FROM SBDB.SB_JL_DSDZDKMX "
                + "WHERE QXDM = '" + qxdm + "' AND JSJDM='" + jsjdm
                +"' AND SJLY='" + SJLY_DR + "' AND FSDM='" +CodeConstant.FSDM_WSSB+"'";
            //���ĳһ���걨����µ����ݣ����ڽɿ��¼�������Ƿ�ȫ���ɿ���������޸�ά�������˵�����������
            String sqlWhere = "QXDM = '" + qxdm + "'"
                + " AND SBHZDH IN ("+sdmxQuery+") AND SBBH NOT IN ("+sqlQuery+")"
//                + " AND substr(to_char(HZRQ,'yyyymmdd'),3,4) = '" + rq + "'" //������Ϊ�������ݣ�������
                + " AND SJLY = '" + SJLY_DR + "' AND JSJDM = '" + jsjdm + "'";
            conn = DBResource.getConnection(); //�õ�����
            ormgr = DBResource.getORManager(); //�õ�orʵ��
            Vector criteria = new Vector();
            criteria.add(sqlWhere);
            ORContext orctx = new ORContext(Sdwszsbhz.class.getName(),criteria);
            hzDataList= ormgr.query(SESSION_ID,conn,orctx);
            if(hzDataList.size() >0)
            {
                return createErase(hzDataList);
            }
            else
            {
                return hzDataList;
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex, "�õ���������ʧ��!");
        }
        finally
        {
            DBResource.destroyConnection(conn);
        }
    }

    //������������
    private Boolean doEraseHz(VOPackage vo) throws BaseException
    {
        Connection conn = null; //���ݿ�����
        try
        {
            HashMap tempData = (HashMap)vo.getData();
            String sbbh = (String)tempData.get(DsdzdkConstant.KEY_SBBH);
            String jsjdm = sbbh.substring(0,8);
            String sbhzdh = (String)tempData.get(DsdzdkConstant.KEY_SBHZDH);
            String qxdm = (String)tempData.get(DsdzdkConstant.KEY_QXDM);
            //�õ�����
            conn = DBResource.getConnection(DBResource.DB_SHENBAO);

            //ɾ����ϸ����������
            StringBuffer sqlBuffer = new StringBuffer();
            sqlBuffer.append("AND SBBH = '").append(sbbh).append("'");
            String wheresql = sqlBuffer.toString(); //ɾ������

            StringBuffer jkbSql = new StringBuffer();
            jkbSql.append("DELETE FROM SBDB.SB_JL_SBJKMX WHERE QXDM = '")
                .append(qxdm).append("' ").append(sqlBuffer);
            //ɾ���ɿ���ϸ
            String sqlString = jkbSql.toString();
            PreparedStatement sqlStatement = conn.prepareStatement(sqlString);
            sqlStatement.execute();

            jkbSql.setLength(0);
            jkbSql.append("DELETE FROM SBDB.SB_JL_SBJKZB WHERE QXDM = '")
                .append(qxdm).append("' AND JSJDM = '").append(jsjdm)
                .append("' ").append(sqlBuffer);
            //ɾ���ɿ�����
            sqlString = jkbSql.toString();
            sqlStatement = conn.prepareStatement(sqlString);
            sqlStatement.execute();


            //ɾ��������ϸ����
            sqlBuffer.setLength(0);
            sqlBuffer.append("delete from sbdb.sb_jl_dsdzdkmx where qxdm = '")
                .append(qxdm).append("' and sbhzdh='").append(sbhzdh).append("'");
            sqlString = sqlBuffer.toString();
            sqlStatement = conn.prepareStatement(sqlString);
            sqlStatement.execute();
            //ɾ��������������
            sqlBuffer.setLength(0);
            sqlBuffer.append(
                "delete from sbdb.sb_jl_sdwszsbhz where qxdm = '")
                .append(qxdm).append("' and sbhzdh='").append(sbhzdh).append("'");
            sqlString = sqlBuffer.toString();
            sqlStatement = conn.prepareStatement(sqlString);
            sqlStatement.execute();
            return Boolean.TRUE;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex,"������������ʧ��!");
        }
        finally
        {
            DBResource.destroyConnection(conn);
        }
    }
    //������������
    private List createErase(List hzdInforList) throws BaseException
     {
         List cxhzdhList = new ArrayList();
         //���ջ��ܵ��Ž��в��
         List param = new ArrayList();
         param.add("getSbhzdh");
         List splitList = null;
         List tmphzdhList = null;
         Sdwszsbhz tmpSdwszsbhz = null;
         SdwszsbhzCx tmpsdwszsbhzCx = null;
         BigDecimal tmpsjjehz = new BigDecimal("0.00");
         try
         {
             splitList = FindObjInList.splitListByParam(hzdInforList, Sdwszsbhz.class, param);
             boolean eraseable = true;
             for(int i=0; i<splitList.size(); i++)
             {
                 tmpsjjehz = new BigDecimal("0.00");
                 eraseable = true;
                 tmphzdhList = (List)splitList.get(i);
                 for(int j=0; j<tmphzdhList.size(); j++)
                 {
                     tmpSdwszsbhz = (Sdwszsbhz)tmphzdhList.get(j);
                     if(!(tmpSdwszsbhz.getClbjdm().equals(CodeConstant.CLBJDM_WCL)
                        || tmpSdwszsbhz.getClbjdm().equals(CodeConstant.CLBJDM_YSB)))
                   {
                       eraseable = false;
                       break;
                   }
                   tmpsjjehz = tmpsjjehz.add(tmpSdwszsbhz.getSjse());
                 }
                 if(eraseable)
                 {
                     tmpsdwszsbhzCx = new SdwszsbhzCx();
                     tmpsdwszsbhzCx.setHzrq(tmpSdwszsbhz.getHzrq());
                     tmpsdwszsbhzCx.setSbhzdh(tmpSdwszsbhz.getSbhzdh());
                     tmpsdwszsbhzCx.setSjjehz(tmpsjjehz);
                     tmpsdwszsbhzCx.setSdwszsbhzList(tmphzdhList);
                     tmpsdwszsbhzCx.setJkpzhNum(tmphzdhList.size());
                     cxhzdhList.add(tmpsdwszsbhzCx);
                 }
             }
             return cxhzdhList;
         }
         catch(Exception ex)
         {
             throw ExceptionUtil.getBaseException(ex,"�������ʧ��!");
         }
     }

     /**
      * �����걨��Ŷ�Ӧ�Ľɿ�������
      * @param vo VOPackage
      * @return map
      * @throws BaseException
      */
     private HashMap doPrint(VOPackage vo) throws BaseException
     {
         HashMap dataMap = new HashMap(); //���ص�map����
         //�������ݿ�����
         Connection conn = null;
         try
         {
             String sbbh = (String)((Map)(vo.getData())).get(DsdzdkConstant.KEY_SBBH);
             String jsjdm = sbbh.substring(0,8);
             String qxdm = (String)((Map)(vo.getData())).get(DsdzdkConstant.KEY_QXDM);
             if(sbbh == null || sbbh.equals(""))
                 throw new ApplicationException("�걨��Ų���Ϊ�գ���ȷ��Ҫ��ӡ�Ľɿ����ݣ�");

             //������ݿ�����
             conn = DBResource.getConnection(DBResource.DB_SHENBAO);
             //��� ORManager
             ORManager orManager = DBResource.getORManager(DBResource.OR_SHENBAO);
             //��ѯ�걨�ɿ�����Ĳ�ѯ���
             ArrayList zbResult = new ArrayList();
             //��ѯ�걨�ɿ���ϸ��Ĳ�ѯ���
             ArrayList mxResult = new ArrayList();
             //���걨��Ŷ�Ӧ�Ľɿ��鶼û�нɿ���ԣ�����Ҫ�ж�
             //����Դ��1��ֱ���걨�õ���2��ͨ����һ����ѯ�õ����Ѿ������˴��ڽɿ��¼��sbbh��
             String sqlWhere = "(QXDM = '" + qxdm + "' AND JSJDM = '" + jsjdm
                 + "' AND SBBH = '" + sbbh
                 + "' AND SJLY = '"+CodeConstant.SJLY_SB_SDHZ+"') ORDER BY JKPZH";
             Vector criteria = new Vector();
             criteria.add(sqlWhere);
             ORContext orCtx = new ORContext(Sbjkzb.class.getName(), criteria);
             zbResult = (ArrayList)orManager.query(SESSION_ID, conn, orCtx); //��ѯ�걨�ɿ�����
             if(zbResult.size() == 0)
             {
                 return null; //û�п���ά�����걨���ݣ�
             }
             //ƴ�걨��ϸ��Ĳ�ѯwhere����
             StringBuffer sqlStrBuf = new StringBuffer();
             sqlStrBuf.append("(QXDM = '").append(qxdm);
             sqlStrBuf.append("' AND SBBH = '").append(sbbh)
                 .append("') ORDER BY SBBH DESC, JKPZH");
             String sqlString = sqlStrBuf.toString();
             Vector criteriaMx = new Vector();
             criteriaMx.add(sqlString);
             ORContext orCtxMx = new ORContext(Sbjkmx.class.getName(), criteriaMx);
             mxResult = (ArrayList)orManager.query(SESSION_ID, conn, orCtxMx); //��ѯ�걨�ɿ���ϸ��

             // ���Ŷ�ȡ�ص����ݽ��и�ʽ��װ����
             ZhsbProcessor zhsbPro = new ZhsbProcessor();
             dataMap = (HashMap)zhsbPro.convertResult(zbResult, mxResult);

             return dataMap; //���ؽ������
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
