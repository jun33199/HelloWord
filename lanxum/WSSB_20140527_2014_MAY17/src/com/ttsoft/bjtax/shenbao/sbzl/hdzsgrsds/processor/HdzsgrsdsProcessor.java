//Source file: E:\\Generate Code\\com\\ttsoft\\bjtax\\shenbao\\sbzl\\hdzsgrsds\\processor\\HdzsgrsdsProcessor.java

package com.ttsoft.bjtax.shenbao.sbzl.hdzsgrsds.processor;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.sql.Connection;
import java.sql.Timestamp;
import java.math.BigDecimal;

import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.exception.ApplicationException;

import com.ttsoft.common.model.UserData;
import com.ttsoft.bjtax.dj.proxy.ServiceProxy;
import com.ttsoft.bjtax.dj.model.JBSJ;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.common.model.Zsfs;
import com.ttsoft.bjtax.sfgl.model.orobj.Tzf;
import com.ttsoft.bjtax.shenbao.model.domain.Szsm;
import com.ttsoft.bjtax.shenbao.model.domain.Hdzsqysb;
import com.ttsoft.bjtax.shenbao.model.domain.Hdzstzzsb;
import com.ttsoft.bjtax.shenbao.sbzl.hdzsgrsds.HdzsgrsdsActionConstant;
import com.ttsoft.bjtax.shenbao.sbzl.hdzsgrsds.HdzsgrsdsMapConstant;
import com.ttsoft.bjtax.shenbao.util.DBResource;
import com.ttsoft.bjtax.shenbao.util.Skssrq;
import com.ttsoft.bjtax.shenbao.util.TinyTools;

import com.ekernel.db.or.ORManager;
import com.ekernel.db.or.ORContext;

/**
 *
 * ģ�����˼��--doQuery������
 * 	ǰ̨�����������ϣ���ϸ�Ĳ��ղ�ѯ�����Ĵ����������
 * 	��̨���в�ѯ��������ڱ����걨���ݣ�����ȡ�õ����ݡ�
 * 	��������ڱ����걨���ݣ����ɱ����걨���ݣ�ȱʡ���󷵻ء�
 * 	�ڱ����˼����ǰ̨��Ժ�̨�Ľӿ��������Ƿ���ڱ����걨����ʱ����һ�µġ�
 * ģ�����˼��--doSave������
 * 	ǰ̨���뱾���걨���ݣ�
 * 	��̨�Ƚ������ݿⱾ���걨���ݵ�ɾ���������������������
 * 	�����г�����ҳ���ϵ����ݶ��Ǳ�������ݣ����������á���
 * ģ�����˼��--doDelete������
 * 	ǰ̨���뱾���걨���ݣ�
 * 	��̨�������ݿⱾ���걨���ݵ�ɾ��������
 *
 * �����������Դ���2�����͵ĺ˶��걨������ֻ��ֵ��������䲻ͬ���ֶΡ�
 *
 * @author Haifeng Su
 * @version 1.0
 *
 * �˶����ո��˶��ʸ��˺ϻ��������˰�걨
 */
public class HdzsgrsdsProcessor implements Processor
{

    /**
     * �ܿ��û�����
     */
    private UserData userData;
    /**
     * �Ǽǳ���
     */
    private static final String DJ_JBSJ = "JBSJ";

    /**
     * ������������ʱ���쳣��Ϣ
     */
    public static final String ILLEGALACTION = "��������!";
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
                case HdzsgrsdsActionConstant.INT_ACTION_QUERY:
                    voPackage.setData(doQuery((Map)voPackage.getData()));
                    break;
                case HdzsgrsdsActionConstant.INT_ACTION_SAVE:
                    voPackage.setData(doSave((Map)voPackage.getData()));
                    break;
                case HdzsgrsdsActionConstant.INT_ACTION_DELETE:
                    voPackage.setData(doDelete((Map)voPackage.getData()));
                    break;
                default:
                    throw new ApplicationException(ILLEGALACTION);
            }
        } catch(Exception e)
        {
            throw ExceptionUtil.getBaseException(e);
        }
        return voPackage;
    }

    /**
     * @param data Map���Ͳ���
     * @return Map
     * @throws Exception
     */
    private Map doQuery(Map data) throws Exception
    {

        //���������ݽṹ
        Map map = new HashMap(2);
        //�������ݿ�����
        Connection connection = null;

        // ��ҵ�걨����
        Hdzsqysb qysbsj = null;
        // Ͷ�����걨����
        List tzzsbsjList = null;

        try
        {
            // ȡ��ҵ���������ʹ��keyΪHdzsgrsdsMapConstant.JSJDM
            String jsjdm = (String)data.get(HdzsgrsdsMapConstant.JSJDM);
            // ȡ��ҵ�����ʻ���Ӧ˰������
            BigDecimal yssdl = new BigDecimal((String)data.get(
                HdzsgrsdsMapConstant.ZSL));
            // ȡͶ�ʷ�����
            List tzfList = (List)data.get(HdzsgrsdsMapConstant.LIST_TZF);

            //��ǰ���ڵ��ù����ӿڲ����������� ��Ⱥͼ���
            Date today = new Date();
            Map sksjrqMap = Skssrq.otherSkssrq(today);
            String year = (String)sksjrqMap.get(Skssrq.SKSSRQ_ND);
            String quarter = Skssrq.preQuarter(today);

            // ͨ���ǼǵĽӿ�ȡ�Ǽ�����
            // ���������������
            // ���ݽӿ��ĵ���û�и���˰�˵�ʱ����׳�BaseException��
            ServiceProxy sp = new ServiceProxy();
            Map djMap = sp.getDjInfo(jsjdm);

            // �Ǽǻ�������
            SWDJJBSJ jbsj = (SWDJJBSJ)djMap.get(DJ_JBSJ);
            // ȡ���ұ�׼��ҵ����
            String hydm = jbsj.getGjbzhydm();

            // ��� ORManagerʵ��
            ORManager orManager = DBResource.getORManager();
            // ������ݿ�����
            connection = DBResource.getConnection();
            // ORManager���������
            Vector criteria = new Vector();
            // ORManager������������
            ORContext orContext = null;

            // ȡ�����걨����
            //	ʹ��ORManager�Ա��˶�������ҵ�걨���ݡ�ȡ��ҵ�걨����
            //	��������������롢��ȡ�����
            criteria.clear();
            criteria.add("jsjdm = '" + jsjdm + "'");
            criteria.add("nd ='" + year + "'");
            criteria.add("jd ='" + quarter + "'");
            orContext = new ORContext(Hdzsqysb.class.getName(), criteria);
            List qysbsjList = orManager.query(this.SESSIONID, connection,
                                              orContext);
            if(qysbsjList.size() == 0)
            {
                // ���û�е��ڵ����ݣ�������Щ���ݷ��ء�
                qysbsj = newInstanceHdzsqysb(djMap, jsjdm, year, quarter, yssdl);
                tzzsbsjList = newInstanceHdzstzzsbList(djMap, tzfList, jsjdm,
                    year, quarter);
                // ע�����ϣ����Щ���������ݿ����ӵ�ռ�ã�
                // ���Կ��ǰ���β�������ŵ�try�����ȥ��
            } else
            {
                qysbsj = (Hdzsqysb)qysbsjList.get(0);
                qysbsj.setNsrmc(jbsj.getNsrmc());
                //ʹ��ORManager�Ա��˶�����Ͷ�����걨���ݡ�ȡͶ�����걨����
                //��������������롢��ȡ�����(ʹ�ú���ҵ������ͬ������)
                orContext = new ORContext(Hdzstzzsb.class.getName(), criteria);
                tzzsbsjList = orManager.query(this.SESSIONID, connection,
                                              orContext);
                if(tzzsbsjList.size() == 0)
                {
                    throw new ApplicationException("ȡ�����˶�����Ͷ�����걨����!");
                }
            }
        } catch(Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "��ѯ����ʧ��!");
        } finally
        {
            DBResource.destroyConnection(connection);
        }

        // ���÷���ֵ
        map.put(HdzsgrsdsMapConstant.QYSBSJ, qysbsj);
        map.put(HdzsgrsdsMapConstant.LIST_TZZSBSJ, tzzsbsjList);

        return map;
    }

    /**
     * @param data Map���Ͳ���
     * @return Map
     * @throws com.ttsoft.framework.exception.BaseException
     */
    private Map doSave(java.util.Map data) throws BaseException
    {

        //���������ݽṹ
        Map map = new HashMap(1);
        //�������ݿ�����
        Connection connection = null;
        try
        {
            // ȡ��ҵ�걨����ʹ��keyΪHdzsgrsdsMapConstant.QYSBSJ
            Hdzsqysb qysbsj = (Hdzsqysb)data.get(HdzsgrsdsMapConstant.QYSBSJ);
            // ȡͶ�����걨����ʹ��keyΪHdzsgrsdsMapConstant.LIST_TZZSBSJ
            List tzzsbsjList = (List)data.get(HdzsgrsdsMapConstant.LIST_TZZSBSJ);

            // �Դ��ڵı����걨��ɾ���
            this.doDelete(data);

            // ��� ORManagerʵ��
            ORManager orManager = DBResource.getORManager();
            // ������ݿ�����
            connection = DBResource.getConnection();

            orManager.makePersistent(SESSIONID, connection, qysbsj);
            for(int i = 0, size = tzzsbsjList.size(); i < size; i++)
            {
                orManager.makePersistent(SESSIONID, connection,
                                         tzzsbsjList.get(i));
            }

        } catch(Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "�������ʧ��!");
        } finally
        {
            DBResource.destroyConnection(connection);
        }
        map.put(HdzsgrsdsMapConstant.RESULT, Boolean.TRUE);
        return map;
    }

    /**
     * �����������ֵ����ҵ����
     * @param djMap �Ǽ�����
     * @param jsjdm ���������
     * @param year ���
     * @param quarter ����
     * @param yssdl Ӧ˰������
     * @return ��ҵ�걨����
     */
    private Hdzsqysb newInstanceHdzsqysb(Map djMap, String jsjdm, String year,
                                         String quarter, BigDecimal yssdl)
    {

        Date today = new Date();
        // To be changed.
        Timestamp now = new Timestamp(today.getTime());
        // �Ǽǻ�������
        SWDJJBSJ jbsj = (SWDJJBSJ)djMap.get(DJ_JBSJ);
        // ˰����������
        Map sksjrqMap = Skssrq.otherSkssrq(today);

        //�˶�������ҵ�걨����
        Hdzsqysb hdzsqysb = new Hdzsqysb();
        //���������	JSJDM���Ǽ����ݣ�
        hdzsqysb.setJsjdm(jsjdm);
        //���	ND��ϵͳ�����ӿڣ�
        hdzsqysb.setNd(year);
        //����	JD��ϵͳ�����ӿڣ�
        hdzsqysb.setJd(quarter);
        //����ʱ��	CJRQ��ϵͳ�����ӿڣ�
        hdzsqysb.setCjsj(now);
        //¼������	LRRQ��ϵͳ�����ӿڣ�
        hdzsqysb.setLrrq(now);
        //˰��������ʼ����	SKSSKSRQ��ϵͳ�����ӿڣ�
        hdzsqysb.setSkssksrq((Timestamp)sksjrqMap.get(Skssrq.SKSSKSRQ));
        //˰��������������	SKSSJSRQ��ϵͳ�����ӿڣ�
        hdzsqysb.setSkssjsrq((Timestamp)sksjrqMap.get(Skssrq.SKSSJSRQ));
        //��˰������	NSRMC���Ǽ����ݣ�
        hdzsqysb.setNsrmc(jbsj.getNsrmc());
        //˰��Ǽ�֤��	SWDJZH���Ǽ����ݣ�
        hdzsqysb.setSwdjzh(jbsj.getSwdjzh());
        //��ҵ�����ܶ�	QYSRZE�����գ�
        hdzsqysb.setQysrze(new BigDecimal(0.00));
        //Ӧ˰������	YSSDL��˰�����ݣ�
        hdzsqysb.setYssdl(yssdl);
        //�����ܶ�	LRZE�����գ�
        hdzsqysb.setLrze(new BigDecimal(0.00));
        //˰�������֯��������	SWJGZZJGDM���Ǽ����ݣ�
        hdzsqysb.setSwjgzzjgdm(jbsj.getSwjgzzjgdm());
        //¼���˴���	LRR��UserData��
        hdzsqysb.setLrr(this.userData.yhid);
        //�걨����	SBRQ
        hdzsqysb.setSbrq(TinyTools.second2Day(now));

        return hdzsqysb;
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
    private List newInstanceHdzstzzsbList(Map djMap, List tzfList, String jsjdm,
                                          String year, String quarter)
    {
        List list = new ArrayList();
        Date today = new Date();
        Timestamp now = new Timestamp(today.getTime());

        // �Ǽǻ�������
        SWDJJBSJ jbsj = (SWDJJBSJ)djMap.get(DJ_JBSJ);
        for(int i = 0, size = tzfList.size(); i < size; i++)
        {
            Tzf tzf = (Tzf)tzfList.get(i);
            //�˶�����Ͷ�����걨����
            Hdzstzzsb hdzstzzsb = new Hdzstzzsb();
            //���������	JSJDM���Ǽ����ݣ�
            hdzstzzsb.setJsjdm(jsjdm);
            //���	ND��ϵͳ�����ӿڣ�
            hdzstzzsb.setNd(year);
            //����	JD��ϵͳ�����ӿڣ�
            hdzstzzsb.setJd(quarter);
            //֤�����ʹ���	ZJLXDM���Ǽ����ݣ�
            hdzstzzsb.setZjlxdm(tzf.getZjlxdm());
            //֤������	ZJHM���Ǽ����ݣ�
            hdzstzzsb.setZjhm(tzf.getZjhm());
            //¼������	LRRQ��ϵͳ�����ӿڣ�
            hdzstzzsb.setLrrq(now);
            //����ʱ��	CJRQ��ϵͳ�����ӿڣ�
            hdzstzzsb.setCjsj(now);
            //�걨����	SBRQ��ϵͳ�����ӿڣ�
            hdzstzzsb.setSbrq(TinyTools.second2Day(now));
            //����������	LRFPBL���Ǽ����ݣ�
            hdzstzzsb.setLrfpbl(tzf.getFpbl());
            //Ӧ��˰���ö�	YNSSDE�����գ�
            hdzstzzsb.setYnssde(new BigDecimal(0.00));
            //����˰��	SYSL�����գ�
            hdzstzzsb.setSysl(new BigDecimal(0.00));
            //����۳���	SSKCS�����գ�
            hdzstzzsb.setSskcs(new BigDecimal(0.00));
            //Ӧ��˰��	YNSE�����գ�
            hdzstzzsb.setYnse(new BigDecimal(0.00));
            //����˰��	JMSE�����գ�
            hdzstzzsb.setJmse(new BigDecimal(0.00));
            //�ڳ�δ��˰��	QCWJSK�����գ�
            hdzstzzsb.setQcwjsk(new BigDecimal(0.00));
            //�ѽ���˰��	YJNSE�����գ�
            hdzstzzsb.setYjnse(new BigDecimal(0.00));
            //ʵ��Ӧ��˰��	SJYJSE�����գ�
            hdzstzzsb.setSjyjse(new BigDecimal(0.00));
            //˰�������֯��������	SWJGZZJGDM���Ǽ����ݣ�
            hdzstzzsb.setSwjgzzjgdm(jbsj.getSwjgzzjgdm());

            list.add(hdzstzzsb);
        }
        return list;
    }

    /**
     * @param data Map���Ͳ���
     * @return Map
     * @throws Exception
     */
    private Map doDelete(Map data) throws Exception
    {
        //���������ݽṹ
        Map map = new HashMap(1);
        //�������ݿ�����
        Connection connection = null;
        try
        {
            // ȡ��ҵ�걨����ʹ��keyΪCzzsjdMapConstant.QYSBSJ
            Hdzsqysb qysbsj = (Hdzsqysb)data.get(HdzsgrsdsMapConstant.QYSBSJ);
            // ȡͶ�����걨����ʹ��keyΪCzzsjdMapConstant.LIST_TZZSBSJ
            List tzzsbsjList = (List)data.get(HdzsgrsdsMapConstant.LIST_TZZSBSJ);

            // ��� ORManagerʵ��
            ORManager orManager = DBResource.getORManager();
            // ������ݿ�����
            connection = DBResource.getConnection();

            // ɾ���걨����
            for(int i = 0, size = tzzsbsjList.size(); i < size; i++)
            {
                orManager.deleteObject(SESSIONID, connection, tzzsbsjList.get(i));
            }
            orManager.deleteObject(SESSIONID, connection, qysbsj);

        } catch(Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "ɾ������ʧ��!");
        } finally
        {
            DBResource.destroyConnection(connection);
        }
        map.put(HdzsgrsdsMapConstant.RESULT, Boolean.TRUE);
        return map;
    }

}