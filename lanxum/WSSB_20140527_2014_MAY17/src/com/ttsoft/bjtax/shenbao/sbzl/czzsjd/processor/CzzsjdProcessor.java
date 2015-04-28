package com.ttsoft.bjtax.shenbao.sbzl.czzsjd.processor;

import java.sql.Connection;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Date;
import java.util.Vector;

import com.ekernel.db.or.ORManager;
import com.ekernel.db.or.ORContext;

import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.exception.ApplicationException;

import com.ttsoft.common.model.UserData;
import com.ttsoft.bjtax.dj.proxy.ServiceProxy;
import com.ttsoft.bjtax.dj.model.JBSJ;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.model.orobj.Tzf;
import com.ttsoft.bjtax.shenbao.sbzl.czzsjd.CzzsjdMapConstant;
import com.ttsoft.bjtax.shenbao.sbzl.czzsjd.CzzsjdActionConstant;
import com.ttsoft.bjtax.shenbao.model.domain.Szsm;
import com.ttsoft.bjtax.shenbao.model.domain.Czzsjbtzzsj;
import com.ttsoft.bjtax.shenbao.model.domain.Czzsjbqysj;
import com.ttsoft.bjtax.shenbao.util.DBResource;
import com.ttsoft.bjtax.shenbao.util.Skssrq;
import com.ttsoft.bjtax.shenbao.util.TinyTools;
import com.ttsoft.bjtax.shenbao.constant.*;
import com.ttsoft.framework.exception.*;

/**
 *
 * ģ�����˼��--doQuery������
 * 	ǰ̨�����������ϣ���ϸ�Ĳ��ղ�ѯ�����Ĵ����������
 * 	��̨���в�ѯ��������ڱ����걨���ݣ�����ȡ�õ����ݡ�
 * 	��������ڱ����걨���ݣ����ɱ����걨���ݣ�ȱʡ���󷵻ء�
 * ģ�����˼��--doSave������
 * 	ǰ̨���뱾���걨���ݣ�
 * 	��̨�Ƚ������ݿⱾ���걨���ݵ�ɾ���������������������
 * 	�����г�����ҳ���ϵ����ݶ��Ǳ�������ݣ����������á���
 * ģ�����˼��--doDelete������
 * 	ǰ̨���뱾���걨���ݣ�
 * 	��̨�������ݿⱾ���걨���ݵ�ɾ��������
 *
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����ģ��</p>
 * <p>Description: �������ռ����걨�ĺ�̨processor</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: TTSOFT</p>
 * @author Haifeng Su
 * @version 1.0 2003-8-30
 */

public class CzzsjdProcessor implements Processor
{

    /**
     * �ܿ�����
     */
    private UserData userData;

    /**
     * �Ǽǳ���
     */
    private static final String DJ_JBSJ = "JBSJ";

    /**
     *orManage�ĳ���
     */
    private static final long SESSIONID = 0;

    /**
     * ʵ��Processor�ӿ�
     * @param voPackage ҵ�����
     * @return Object VOPackage������
     * @throws BaseException ҵ���쳣
     * 		1 ���������Ĳ������Ͳ���ʱ�׳�
     * 		2 �����õ�ҵ�񷽷��׳�ҵ���쳣ʱ���ϴ����׳�
     * 	�����쳣�׳���EJB��process��������
     */
    public Object process(VOPackage voPackage) throws BaseException
    {
        this.userData = voPackage.getUserData();
        // ����ҵ���������ֵ����ҵ�����
        try
        {
            switch(voPackage.getAction())
            {
                case CzzsjdActionConstant.INT_ACTION_QUERY:
                    voPackage.setData(doQuery((Map)voPackage.getData()));
                    return voPackage;

                case CzzsjdActionConstant.INT_ACTION_SAVE:
                    doSave((Map)voPackage.getData());
                    return null;

                case CzzsjdActionConstant.INT_ACTION_DELETE:
                    doDelete((Map)voPackage.getData());
                    return null;

                default:
                    throw new Exception();
            }
        }
        catch(Exception e)
        {
            throw ExceptionUtil.getBaseException(e);
        }
    }

    /**
     * ȡ��˰�ʱ����ݡ���ҵ�걨���ݺ�Ͷ�����걨����
     * @param data Map������
     * @return Map
     * @throws Exception ��������������׳��쳣��Ϣ
     */
    private Map doQuery(Map data) throws Exception
    {
        //���������ݽṹ
        Map map = new HashMap(2);
        //�������ݿ�����
        Connection connection = null;

        // ��ҵ�걨����
        Czzsjbqysj czzsjbqysj = null;
        // Ͷ�����걨����
        List tzzsbsjList = null;

        try
        {
            // ȡ��ҵ���������ʹ��keyΪCzzsjdMapConstant.JSJDM
            String jsjdm = (String)data.get(CzzsjdMapConstant.JSJDM);
            // ȡͶ�ʷ�����
            List tzfList = (List)data.get(CzzsjdMapConstant.LIST_TZF);

            //��ǰ���ڵ��ù����ӿڲ����������� ��Ⱥͼ���
            Date today = new Date();
            Map sksjrqMap = Skssrq.otherSkssrq(today);
            String year = (String)sksjrqMap.get(Skssrq.SKSSRQ_ND);
            String quarter = Skssrq.preQuarter(today);

            // ͨ���ǼǵĽӿ�ȡ�Ǽ�����
            ServiceProxy sp = new ServiceProxy();
            Map djMap = sp.getDjInfo(jsjdm);
            SWDJJBSJ jbsj = (SWDJJBSJ)djMap.get(DJ_JBSJ);

            Vector criteria = new Vector();
            ORContext orContext = null;

            // ȡ�����걨����
            criteria.clear();
            criteria.add("jsjdm = '" + jsjdm + "'");  // ���������
            criteria.add("nd ='" + year + "'");  // ���
            criteria.add("jd ='" + quarter + "'");  // ����
            criteria.add("fsdm ='" + CodeConstant.FSDM_WSSB + "'");  // ��ʽ����
            criteria.add("qxdm ='" + jbsj.getSwjgzzjgdm().substring(0, 2) + "'");  // ���ش���

            orContext = new ORContext(Czzsjbqysj.class.getName(), criteria);

            ORManager orManager = DBResource.getORManager();
            connection = DBResource.getConnection();

            List qysbsjList = orManager.query(this.SESSIONID, connection, orContext);
            if(qysbsjList.size() == 0)
            {
                // ���û�е��ڵ����ݣ�������Щ���ݷ��ء�
                czzsjbqysj = newInstanceCzzsjbqysj(djMap, jsjdm, year, quarter);
                tzzsbsjList = newInstanceCzzsjbtzzsjList(djMap, tzfList, jsjdm,
                                                         year, quarter);
            }
            else
            {
                criteria.clear();
                criteria.add("jsjdm = '" + jsjdm + "'");  // ���������
                criteria.add("nd ='" + year + "'");  // ���
                criteria.add("jd ='" + quarter + "'");  // ����

                czzsjbqysj = (Czzsjbqysj)qysbsjList.get(0);
                czzsjbqysj.setNsrmc(jbsj.getNsrmc());
                //ʹ��ORManager�Ա��������ռ���Ͷ�������ݡ�ȡͶ�����걨����
                //��������������롢��ȡ�����(ʹ�ú���ҵ������ͬ������)
                orContext = new ORContext(Czzsjbtzzsj.class.getName(), criteria);
                tzzsbsjList = orManager.query(this.SESSIONID, connection, orContext);
                if(tzzsbsjList.size() == 0)
                {
                    throw new ApplicationException("ȡ�����������ռ���Ͷ��������!");
                }
            }
        }
        catch(Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "��ѯ����ʧ��!");
        }
        finally
        {
            DBResource.destroyConnection(connection);
        }
        // ���÷���ֵ
        map.put(CzzsjdMapConstant.QYSBSJ, czzsjbqysj);
        map.put(CzzsjdMapConstant.LIST_TZZSBSJ, tzzsbsjList);

        return map;
    }

    /**
     * �����������ֵ����ҵ����
     * @param djMap �Ǽ�����
     * @param jsjdm ���������
     * @param year ���
     * @param quarter ����
     * @return ��ҵ�걨����
     */
    private Czzsjbqysj newInstanceCzzsjbqysj(Map djMap, String jsjdm,
                                             String year, String quarter)
    {

        Date today = new Date();
        Timestamp now = new Timestamp(today.getTime());

        // �Ǽǻ�������
        SWDJJBSJ jbsj = (SWDJJBSJ)djMap.get(DJ_JBSJ);
        // ˰����������
        Map sksjrqMap = Skssrq.otherSkssrq(today);

        Czzsjbqysj czzsjbqysj = new Czzsjbqysj();
        //���������	JSJDM
        czzsjbqysj.setJsjdm(jsjdm);
        //���	ND
        czzsjbqysj.setNd(year);
        //����	JD
        czzsjbqysj.setJd(quarter);
        //����ʱ��	CJSJ
        czzsjbqysj.setCjrq(now);
        //¼������	LRRQ
        czzsjbqysj.setLrrq(now);
        //˰��������ʼ����	SKSSKSRQ
        czzsjbqysj.setSkssksrq((Timestamp)sksjrqMap.get(Skssrq.SKSSKSRQ));
        //˰��������������	SKSSJSRQ
        czzsjbqysj.setSkssjsrq((Timestamp)sksjrqMap.get(Skssrq.SKSSJSRQ));
        //��˰������	NSRMC
        czzsjbqysj.setNsrmc(jbsj.getNsrmc());
        //�����ܶ�	LRZE
        czzsjbqysj.setLrze(new BigDecimal(0.00));
        //˰��Ǽ�֤��	SWDJZH
        czzsjbqysj.setSwdjzh(jbsj.getSwdjzh());
        //˰�������֯��������	SWJGZZJGDM
        czzsjbqysj.setSwjgzzjgdm(jbsj.getSwjgzzjgdm());
        //¼���˴���	LRR
        czzsjbqysj.setLrr(jsjdm);
        //�걨����	SBRQ
        czzsjbqysj.setSbrq(TinyTools.second2Day(now));

        return czzsjbqysj;
    }

    /**
     * �����������ֵ��Ͷ��������
     * @param djMap �Ǽ�����
     * @param tzfList Ͷ�ʷ�����
     * @param jsjdm ���������
     * @param year ���
     * @param quarter ����
     * @return Ͷ�����걨����
     */
    private List newInstanceCzzsjbtzzsjList(Map djMap,
                                            List tzfList,
                                            String jsjdm,
                                            String year,
                                            String quarter)
    {
        List list = new ArrayList();
        Date today = new Date();
        Timestamp now = new Timestamp(today.getTime());

        // �Ǽǻ�������
        SWDJJBSJ jbsj = (SWDJJBSJ)djMap.get(DJ_JBSJ);
        for(int i = 0, size = tzfList.size(); i < size; i++)
        {
            Tzf tzf = (Tzf)tzfList.get(i);
            Czzsjbtzzsj czzsjbtzzsj = new Czzsjbtzzsj();
            //���������	JSJDM
            czzsjbtzzsj.setJsjdm(jsjdm);
            //���	ND
            czzsjbtzzsj.setNd(year);
            //����	JD
            czzsjbtzzsj.setJd(quarter);
            //֤�����ʹ���	ZJLXDM
            czzsjbtzzsj.setZjlxdm(tzf.getZjlxdm());
            //֤������	ZJHM
            czzsjbtzzsj.setZjhm(tzf.getZjhm());
            //¼������	LRRQ
            czzsjbtzzsj.setLrrq(now);
            //�������	FPBL
            czzsjbtzzsj.setFpbl(tzf.getFpbl());
            //Ӧ��˰���ö�	YNSSDE
            czzsjbtzzsj.setYnssde(new BigDecimal(0.00));
            //����˰��	SYSL
            czzsjbtzzsj.setSysl(new BigDecimal(0.00));
            //����۳���	SSKCS
            czzsjbtzzsj.setSskcs(new BigDecimal(0.00));
            //Ӧ������˰��	YNSDSE
            czzsjbtzzsj.setYnsdse(new BigDecimal(0.00));
            //����˰��	JMSE
            czzsjbtzzsj.setJmse(new BigDecimal(0.00));
            //�ڳ�δ������˰��	QCWJSDSE
            czzsjbtzzsj.setQcwjsdse(new BigDecimal(0.00));
            //�ѽ�������˰��	YJNSDSE
            czzsjbtzzsj.setYjnsdse(new BigDecimal(0.00));
            //ʵ��Ӧ��˰��	SJYJSE
            czzsjbtzzsj.setSjyjse(new BigDecimal(0.00));
            //˰�������֯��������	SWJGZZJGDM
            czzsjbtzzsj.setSwjgzzjgdm(jbsj.getSwjgzzjgdm());

            list.add(czzsjbtzzsj);
        }
        return list;
    }

    /**
     * ������ҵ�걨���ݺ�Ͷ�����걨����
     * @param data Map������
     * @throws Exception ��������������׳��쳣��Ϣ
     */
    private void doSave(Map data) throws Exception
    {
        Connection connection = null;
        try
        {
            // ȡ��ҵ�걨����
            Czzsjbqysj qysbsj = (Czzsjbqysj)data.get(CzzsjdMapConstant.QYSBSJ);
            // ȡͶ�����걨����
            List tzzsbsjList = (List)data.get(CzzsjdMapConstant.LIST_TZZSBSJ);

            // �Դ��ڵı����걨��ɾ
            this.doDelete(data);

            ORManager orManager = DBResource.getORManager();
            connection = DBResource.getConnection();

            //���
            orManager.makePersistent(SESSIONID, connection, qysbsj);
            for(int i = 0, size = tzzsbsjList.size(); i < size; i++)
            {
                orManager.makePersistent(SESSIONID, connection, tzzsbsjList.get(i));
            }
        }
        catch(Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "�������ʧ��!");
        }
        finally
        {
            DBResource.destroyConnection(connection);
        }
    }

    /**
     * ɾ����ҵ�걨���ݺ�Ͷ�����걨����
     * @param data Map������
     * @throws Exception ��������������׳��쳣��Ϣ
     */
    private void doDelete(Map data) throws Exception
    {
        Connection connection = null;
        try
        {
            // ȡ��ҵ�걨����ʹ��
            Czzsjbqysj qysbsj = (Czzsjbqysj)data.get(CzzsjdMapConstant.QYSBSJ);
            // ȡͶ�����걨����
            List tzzsbsjList = (List)data.get(CzzsjdMapConstant.LIST_TZZSBSJ);

            ORManager orManager = DBResource.getORManager();
            connection = DBResource.getConnection();

            // ɾ���걨����
            for(int i = 0, size = tzzsbsjList.size(); i < size; i++)
            {
                orManager.deleteObject(SESSIONID, connection, tzzsbsjList.get(i));
            }
            orManager.deleteObject(SESSIONID, connection, qysbsj);
        }
        catch(Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "ɾ������ʧ��!");
        }
        finally
        {
            DBResource.destroyConnection(connection);
        }
    }
}