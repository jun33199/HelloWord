//Source file: F:\\Generated Code\\com\\ttsoft\\bjtax\\shenbao\\sbzl\\czzsnd\\processor\\CzzsndProcessor.java

package com.ttsoft.bjtax.shenbao.sbzl.czzsnd.processor;

import java.sql.*;
import java.util.*;
import java.util.Date;
import java.math.BigDecimal;

import com.ekernel.db.or.*;
import com.syax.bjtax.ca.util.DzyjHelper;
import com.syax.bjtax.ca.vo.DzyjsjVO;
import com.syax.common.util.CAcodeConstants;
import com.ttsoft.bjtax.dj.model.*;
import com.ttsoft.bjtax.dj.proxy.*;
import com.ttsoft.bjtax.sfgl.model.orobj.Tzf;
import com.ttsoft.bjtax.shenbao.model.domain.*;
import com.ttsoft.bjtax.shenbao.sbzl.czzsnd.*;
import com.ttsoft.bjtax.shenbao.util.*;
import com.ttsoft.bjtax.shenbao.zhsb.ZhsbMapConstant;
import com.ttsoft.common.model.*;
import com.ttsoft.framework.exception.*;
import com.ttsoft.framework.processor.*;
import com.ttsoft.framework.util.*;
import com.ttsoft.bjtax.shenbao.model.domain.Czzsnbtzzmxsj;
import com.ttsoft.bjtax.shenbao.sbzl.czzsjd.CzzsjdMapConstant;
import com.ttsoft.bjtax.shenbao.constant.*;

/**
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
 * @author Haifeng Su
 * @version 1.0
 *
 * �������ո��˶��ʺͺϻ���ҵ����걨
 */
public class CzzsndProcessor implements Processor
{
    /**
     *orManage�ĳ���
     */
    private static final long SESSIONID = 0;

    /**
     * �ܿ�����
     */
    private UserData userData;

    /**
     * ʵ��Processor�ӿ�
     * @param voPackage ҵ�����
     * @return Object VOPackage������
     * @throws BaseException ҵ���쳣
     * 		1 ���������Ĳ������Ͳ���ʱ�׳�
     * 		2 �����õ�ҵ�񷽷��׳�ҵ���쳣ʱ���ϴ����׳�
     * 	�����쳣�׳���EJB��process����������
     */
    public Object process(VOPackage voPackage) throws BaseException
    {
        this.userData = voPackage.getUserData();
        // ����ҵ���������ֵ����ҵ�����
        try
        {
            switch(voPackage.getAction())
            {
                case CzzsndActionConstant.INT_ACTION_QUERY:
                    voPackage.setData(doQuery((Map)voPackage.getData()));
                    break;
                case CzzsndActionConstant.INT_ACTION_SAVE:
                    voPackage.setData(doSave((Map)voPackage.getData()));
                    break;
                case CzzsndActionConstant.INT_ACTION_DELETE:
                    voPackage.setData(doDelete((Map)voPackage.getData()));
                    break;
                default:
                    throw new SystemException("no such method");
            }
        }
        catch(Exception e)
        {
            throw ExceptionUtil.getBaseException(e);
        }
        return voPackage;
    }

    /**
     * @param data Map���Ͳ���
     * @return Map
     * @throws com.ttsoft.framework.exception.BaseException
     */
    private Map doSave(Map data) throws BaseException
    {
        // ��������Map
        Map retMap = new HashMap(1);

        // ���ݿ����Ӷ���
        Connection conn = null;
        // ORʵ��
        ORManager orManager = null;
        
        try
        {
            // Ҫ����Ĳ��������걨����
            Czzsnbzb czzsnbzb = (Czzsnbzb)data.get(CzzsndMapConstant.CZZSNBZB);
            // Ҫ����Ĳ��������걨��ҵ����
            List czzsnbqyList = (List)data.get(CzzsndMapConstant.LIST_QYSBSJ);
            // Ҫ����Ĳ��������걨Ͷ��������
            List czzsnbtzzList = (List)data.get(CzzsndMapConstant.LIST_TZZSBSJ);
            // Ҫ����ķ����������
            List czzsnbfpblList = (List)data.get(CzzsndMapConstant.LIST_FPBLSJ);
            // ���������걨Ͷ������ϸ����
            List czzsnbtzzsjmxList = (List)data.get(CzzsndMapConstant.LIST_TZZMX);
            List dzyjList=(List)data.get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);
            List bacDzyjList=new ArrayList();
            UserData ud=userData;
            try
	        {
            	for(int i=0;i<dzyjList.size();i++)
            	{
            		DzyjsjVO dzyj=(DzyjsjVO)dzyjList.get(i);
            		dzyj = CAUtils.saveDzyjsj(ud,dzyj, czzsnbzb.getNd(), "0", "0", "0");
            		bacDzyjList.add(dzyj);
            	}
	       }catch (Exception ex)
	        {
	            ex.printStackTrace();
	            throw ExceptionUtil.getBaseException(ex);
	        }
	       retMap.remove(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);
	       retMap.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, bacDzyjList);

            // ��ɾ��
            doDelForSave(data);

            // ������ݿ�����
            conn = DBResource.getConnection();
            // ���ORManager
            orManager = DBResource.getORManager();

            // ������������걨��������
            orManager.makePersistent(SESSIONID, conn, czzsnbzb);

            // ������������걨��ҵ����
            for(int i = 0; i < czzsnbqyList.size(); i++)
            {
                orManager.makePersistent(SESSIONID, conn, czzsnbqyList.get(i));
            }

            // ������������걨Ͷ��������
            for(int i = 0; i < czzsnbtzzList.size(); i++)
            {
                orManager.makePersistent(SESSIONID, conn, czzsnbtzzList.get(i));
            }

            // ������������걨Ͷ������ϸ����
            for(int i = 0; i < czzsnbtzzsjmxList.size(); i++)
            {
                List tzzsjmxList = (List)czzsnbtzzsjmxList.get(i);
                for(int j = 0, size = tzzsjmxList.size(); j < size; j++)
                {
                    orManager.makePersistent(SESSIONID, conn, tzzsjmxList.get(j));
                }
            }

            // ������������걨�����������
            for(int i = 0; i < czzsnbfpblList.size(); i++)
            {
                orManager.makePersistent(SESSIONID, conn, czzsnbfpblList.get(i));
            }
        }
        catch(Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
        finally
        {
            DBResource.destroyConnection(conn);
        }
        retMap.put(CzzsndMapConstant.RESULT, Boolean.TRUE);
        return retMap;
    }

    /**
     * @param data Map���Ͳ���
     * @return Map
     * @throws com.ttsoft.framework.exception.BaseException
     */
    private Map doQuery(Map data) throws BaseException
    {
        Connection conn = null;
        ORManager orManager = null;

        // ��������Map
        Map retMap = new HashMap(7);

        Czzsnbzb czzsnbzb = null;  // ���������걨����VO
        List czzsnbqyList = null;  // ����������ҵ����List
        List czzsnbtzzsjList = null;  // ���������걨Ͷ��������List
        List czzsnbfpblList = null;  // ���������걨�����������
        List czzsnbtzzsjmxList = null;  // ���������걨Ͷ������ϸ����
        SWDJJBSJ swdjjbsj = null;  // �Ǽǻ�������

        try
        {
            // ȡ�ü��������
            String jsjdm = (String)data.get(CzzsndMapConstant.JSJDM);
            // ȡ�õǼ�Ͷ�ʷ�����List
            List tzfList = (List)data.get(CzzsndMapConstant.LIST_TZF);

            //���õǼǽӿڲ�ѯ�Ǽ���Ϣ
            ServiceProxy sProxy = new ServiceProxy();
            Map djsjMap = sProxy.getDjInfo(jsjdm);

            // ȡ�õǼǻ�������
            swdjjbsj = (SWDJJBSJ)djsjMap.get(CzzsndMapConstant.JBSJ);
            String swjgzzjgdm = swdjjbsj.getSwjgzzjgdm();

            // ���������걨����List
            List czzsnbzbList = null;

            conn = DBResource.getConnection();
            orManager = DBResource.getORManager();

            // ȡ�õ�ǰ�������
            Date today = new Date();
            Map sksjrqMap = Skssrq.yearSkssrq(today);
            String nd = (String)sksjrqMap.get(Skssrq.SKSSRQ_ND);

            retMap.put(CzzsndMapConstant.CZZSNBEXISTED, "0");  // �����Ƿ����
            // ��ѯ���ݿ⣬ȡ�ò���������������
            czzsnbzbList = this.queryCzzsndzb(conn, orManager, jsjdm, nd, swjgzzjgdm);
            if(czzsnbzbList == null || czzsnbzbList.size() == 0)
            {
                // ���ɲ��������걨��������
                czzsnbzb = this.newInstanceCzzsnbzb(djsjMap, jsjdm, nd);

                // ���ɲ��������걨Ͷ��������
                czzsnbtzzsjList = this.newInstanceCzzsnbtzzsbList(djsjMap, tzfList, jsjdm, nd);

                // ���ɲ��������걨��ҵ����
                czzsnbqyList = this.newInstanceCzzsnbqysbList(djsjMap, jsjdm, nd);

                // ���ɲ��������걨�����������
                czzsnbfpblList = this.newInstanceCzzsnbfpblList(djsjMap, tzfList, jsjdm, nd);

                // ���ɲ��������걨Ͷ������ϸ����
                czzsnbtzzsjmxList = this.newInstanceCzzsnbtzzsbmxList(djsjMap, tzfList, jsjdm, nd);
            }
            else
            {
                retMap.put(CzzsndMapConstant.CZZSNBEXISTED, "1");  // �����Ƿ����
                czzsnbzb = (Czzsnbzb)czzsnbzbList.get(0);
                // ��ѯ��ҵ����
                czzsnbqyList = this.queryCzzsqysj(conn, orManager, jsjdm, nd, swjgzzjgdm);
                System.out.println(czzsnbqyList.size());
                if(czzsnbqyList.size() != 40)
                {
                    throw new ApplicationException("���ݲ�������");
                }
                // ��ѯ�����������
                czzsnbfpblList = this.queryCzzsnbfpblsj(conn, orManager, jsjdm, nd, swjgzzjgdm);
                if(czzsnbfpblList.size() != 4)
                {
                    throw new ApplicationException("���ݲ�������");
                }
                // ��ѯͶ��������
                czzsnbtzzsjList = this.queryCzzsnbtzzsj(conn, orManager, jsjdm, nd, swjgzzjgdm);
                if(czzsnbtzzsjList.size() != tzfList.size())
                {
                    throw new ApplicationException("���ݲ�������");
                }
                // ���ɲ��������걨Ͷ������ϸ����
                List totalTzzmxList = this.queryCzzsnbtzzmxsj(conn, orManager, jsjdm, nd, swjgzzjgdm);
                if(totalTzzmxList.size() != tzfList.size() * 10)
                {
                    throw new ApplicationException("���ݲ�������");
                }
                int tzzSize = czzsnbtzzsjList.size();
                czzsnbtzzsjmxList = new ArrayList(tzzSize);
                int subSize = totalTzzmxList.size() / tzzSize;

                for(int i = 0, size = tzzSize; i < size; i++)
                {
                    List subList = new ArrayList(subSize);
                    for(int j = 0; j < subSize; j++)
                    {
                        subList.add(totalTzzmxList.get(subSize * i + j));
                    }
                    czzsnbtzzsjmxList.add(subList);
                }
            }
        }
        catch(Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
        finally
        {
            // �ر����ݿ�����
            DBResource.destroyConnection(conn);
        }

        retMap.put(CzzsndMapConstant.CZZSNBZB, czzsnbzb);  // ���������걨��������
        retMap.put(CzzsndMapConstant.LIST_QYSBSJ, czzsnbqyList);  // ���������걨��ҵ����
        retMap.put(CzzsndMapConstant.LIST_TZZSBSJ, czzsnbtzzsjList);  // ���������걨Ͷ��������
        retMap.put(CzzsndMapConstant.LIST_FPBLSJ, czzsnbfpblList);  // ���������걨�����������
        retMap.put(CzzsndMapConstant.LIST_TZZMX, czzsnbtzzsjmxList);  // ���������걨Ͷ������ϸ����
        retMap.put(CzzsndMapConstant.JBSJ, swdjjbsj);  // �Ǽǻ�������

        return retMap;
    }

    /**
     * @param data Map���Ͳ���
     * @return Map
     * @throws com.ttsoft.framework.exception.BaseException
     */
    private Map doDelete(Map data) throws BaseException
    {
        // ��������Map
        Map retMap = new HashMap(1);

        // ���ݿ����Ӷ���
        Connection conn = null;
        // ORʵ��
        ORManager orManager = null;
        try
        {
            // Ҫɾ���Ĳ��������걨����
            Czzsnbzb czzsnbzb = (Czzsnbzb)data.get(CzzsndMapConstant.CZZSNBZB);
            // Ҫɾ���Ĳ��������걨��ҵ����
            List czzsnbqyList = (List)data.get(CzzsndMapConstant.LIST_QYSBSJ);
            // Ҫɾ���Ĳ��������걨Ͷ��������
            List czzsnbtzzList = (List)data.get(CzzsndMapConstant.LIST_TZZSBSJ);
            // Ҫɾ���Ĳ��������걨�����������
            List czzsnbfpblList = (List)data.get(CzzsndMapConstant.LIST_FPBLSJ);
            // ���������걨Ͷ������ϸ����
            List czzsnbtzzsjmxList = (List)data.get(CzzsndMapConstant.LIST_TZZMX);
            DzyjsjVO dzyj=(DzyjsjVO)data.get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);
            UserData ud=userData;
            
            try
	        {
            	dzyj = CAUtils.saveDzyjsj(ud,dzyj, czzsnbzb.getNd(), "0", "0", "0");
	       	
	       }catch (Exception ex)
	        {
	            ex.printStackTrace();
	            throw ExceptionUtil.getBaseException(ex);
	        }
	       retMap.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);

            // ������ݿ�����
            conn = DBResource.getConnection();
            // ���ORManager
            orManager = DBResource.getORManager();

            // �Ⱥ�ɾ�����������걨�����������
            this.deleteCzzsnbfpblsj(conn, orManager, czzsnbfpblList);
            // ��ɾ�����������걨Ͷ������ϸ����
            this.deleteCzzsnbtzzmxsj(conn, orManager, czzsnbtzzsjmxList);
            // ��ɾ�����������걨Ͷ��������
            this.deleteCzzsnbtzzsj(conn, orManager, czzsnbtzzList);
            // Ȼ��ɾ�����������걨��ҵ����
            this.deleteCzzsnbqysj(conn, orManager, czzsnbqyList);
            // ���ɾ�����������걨��������
            this.deleteCzzsnbzbsj(conn, orManager, czzsnbzb);
        } catch(Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        } finally
        {
            DBResource.destroyConnection(conn);
        }
        //���췵������
        retMap.put(CzzsndMapConstant.RESULT, Boolean.TRUE);
        return retMap;
    }

    private Map doDelForSave(Map data) throws BaseException
    {
        // ��������Map
        Map retMap = new HashMap(1);

        // ���ݿ����Ӷ���
        Connection conn = null;
        // ORʵ��
        ORManager orManager = null;
        try
        {
            // Ҫɾ���Ĳ��������걨����
            Czzsnbzb czzsnbzb = (Czzsnbzb)data.get(CzzsndMapConstant.CZZSNBZB);
            // Ҫɾ���Ĳ��������걨��ҵ����
            List czzsnbqyList = (List)data.get(CzzsndMapConstant.LIST_QYSBSJ);
            // Ҫɾ���Ĳ��������걨Ͷ��������
            List czzsnbtzzList = (List)data.get(CzzsndMapConstant.LIST_TZZSBSJ);
            // Ҫɾ���Ĳ��������걨�����������
            List czzsnbfpblList = (List)data.get(CzzsndMapConstant.LIST_FPBLSJ);
            // ���������걨Ͷ������ϸ����
            List czzsnbtzzsjmxList = (List)data.get(CzzsndMapConstant.LIST_TZZMX);

            // ������ݿ�����
            conn = DBResource.getConnection();
            // ���ORManager
            orManager = DBResource.getORManager();

            // �Ⱥ�ɾ�����������걨�����������
            this.deleteCzzsnbfpblsj(conn, orManager, czzsnbfpblList);
            // ��ɾ�����������걨Ͷ������ϸ����
            this.deleteCzzsnbtzzmxsj(conn, orManager, czzsnbtzzsjmxList);
            // ��ɾ�����������걨Ͷ��������
            this.deleteCzzsnbtzzsj(conn, orManager, czzsnbtzzList);
            // Ȼ��ɾ�����������걨��ҵ����
            this.deleteCzzsnbqysj(conn, orManager, czzsnbqyList);
            // ���ɾ�����������걨��������
            this.deleteCzzsnbzbsj(conn, orManager, czzsnbzb);
        } catch(Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        } finally
        {
            DBResource.destroyConnection(conn);
        }
        //���췵������
        return retMap;
    }

    /**
     * �����������ֵ�Ĳ��������걨������
     * @param djMap �Ǽ�����
     * @param jsjdm ���������
     * @param year ���
     *
     * @return ���������걨������
     */
    private Czzsnbzb newInstanceCzzsnbzb(Map djMap, String jsjdm, String year)
    {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        SWDJJBSJ dj = (SWDJJBSJ)djMap.get(CzzsndMapConstant.JBSJ);
        Czzsnbzb czzsnbzb = new Czzsnbzb();
        czzsnbzb.setJsjdm(jsjdm);
        czzsnbzb.setCjrq(now);
        czzsnbzb.setLrrq(now);
        czzsnbzb.setLrr(userData.yhid);
        czzsnbzb.setLrr(jsjdm);
        czzsnbzb.setSwjgzzjgdm(dj.getSwjgzzjgdm());
        czzsnbzb.setNd(year);
        Map skssrqMap = Skssrq.yearSkssrq(new Date());
        czzsnbzb.setSkssjsrq((Timestamp)skssrqMap.get(Skssrq.SKSSJSRQ));
        czzsnbzb.setSkssksrq((Timestamp)skssrqMap.get(Skssrq.SKSSKSRQ));
        czzsnbzb.setSbrq(TinyTools.second2Day(now));
        czzsnbzb.setQxdm(dj.getSwjgzzjgdm().substring(0, 2));

        return czzsnbzb;
    }

    /**
     * �����������ֵ����ҵ����
     * @param djMap �Ǽ�����
     * @param jsjdm ���������
     * @param year ���
     * @return ��ҵ�걨����
     */
    private List newInstanceCzzsnbqysbList(Map djMap, String jsjdm,
                                           String year)
    {
        List czzsnbqyList = new ArrayList(40);
        SWDJJBSJ dj = (SWDJJBSJ)djMap.get(CzzsndMapConstant.JBSJ);
        for(int i = 0; i < 40; i++)
        {
            Czzsnbqy czzsqy = new Czzsnbqy();
            if(i < 38){
                czzsqy.setHc(String.valueOf(i + 1));
            }else{
                czzsqy.setHc(String.valueOf(i + 11));
            }
            czzsqy.setJsjdm(jsjdm);
            czzsqy.setNd(year);
            czzsqy.setSwjgzzjgdm(dj.getSwjgzzjgdm());
            czzsqy.setBqljs(new BigDecimal(0.00));
            czzsnbqyList.add(czzsqy);
        }
        return czzsnbqyList;
    }

    /**
     * �����������ֵ��Ͷ������ϸ����
     * @param djMap �Ǽ�����
     * @param tzzList Ͷ��������
     * @param jsjdm ���������
     * @param year ���
     * @return Ͷ�����걨����
     */
    private List newInstanceCzzsnbtzzsbmxList(Map djMap, List tzzList,
                                              String jsjdm, String year)
    {
        int size = tzzList.size();
        List czzsnbtzzmxList = new ArrayList(size);
        SWDJJBSJ dj = (SWDJJBSJ)djMap.get(CzzsndMapConstant.JBSJ);
        for(int i = 0; i < size; i++)
        {
            Tzf tzf = (Tzf)tzzList.get(i);
            List tzzsjmxList = new ArrayList();
            for(int j = 0; j < 10; j++){
                Czzsnbtzzmxsj czzsnbtzzsjmx = new Czzsnbtzzmxsj();
                if (j == 0) {
                    czzsnbtzzsjmx.setBqljs(tzf.getFpbl());
                }
                else {
                    czzsnbtzzsjmx.setBqljs(new BigDecimal(0.00));
                }
                czzsnbtzzsjmx.setHc(
                    String.valueOf(CzzsndMapConstant.INDEX_HC_TZZSJ + j));
                czzsnbtzzsjmx.setJsjdm(jsjdm);
                czzsnbtzzsjmx.setNd(year);
                czzsnbtzzsjmx.setZjhm(tzf.getZjhm());
                czzsnbtzzsjmx.setZjlxdm(tzf.getZjlxdm());
                czzsnbtzzsjmx.setSwjgzzjgdm(dj.getSwjgzzjgdm());
                tzzsjmxList.add(czzsnbtzzsjmx);
            }
            czzsnbtzzmxList.add(tzzsjmxList);
        }
        return czzsnbtzzmxList;
    }

    /**
     * �����������ֵ��Ͷ��������
     * @param djMap �Ǽ�����
     * @param tzzList Ͷ��������
     * @param jsjdm ���������
     * @param year ���
     * @return Ͷ�����걨����
     */
    private List newInstanceCzzsnbtzzsbList(Map djMap, List tzzList,
                                            String jsjdm, String year)
    {
        int size = tzzList.size();
        List czzsnbtzzList = new ArrayList(size);
        SWDJJBSJ dj = (SWDJJBSJ)djMap.get(CzzsndMapConstant.JBSJ);
        for(int i = 0; i < size; i++)
        {
            Tzf tzf = (Tzf)tzzList.get(i);
            Czzsnbtzzsj czzsnbtzzsj = new Czzsnbtzzsj();
            czzsnbtzzsj.setJsjdm(jsjdm);
            czzsnbtzzsj.setNd(year);
            czzsnbtzzsj.setTzzxm(tzf.getTzfmc());
            czzsnbtzzsj.setZjhm(tzf.getZjhm());
            czzsnbtzzsj.setZjlxdm(tzf.getZjlxdm());
            czzsnbtzzsj.setSwjgzzjgdm(dj.getSwjgzzjgdm());
            czzsnbtzzList.add(czzsnbtzzsj);
        }
        return czzsnbtzzList;
    }

    /**
     * �����������ֵ�ķ����������
     * @param djMap �Ǽ�����
     * @param tzzList Ͷ��������
     * @param jsjdm ���������
     * @param year ���
     * @return �����������
     */
    private List newInstanceCzzsnbfpblList(Map djMap, List tzzList,
                                           String jsjdm, String year)
    {
        List czzsnbfpblList = new ArrayList(4);
        SWDJJBSJ dj = (SWDJJBSJ)djMap.get(CzzsndMapConstant.JBSJ);
        for(int i = 0; i < 4; i++)
        {
            Czzsnbfpbl czzsnbfpbl = new Czzsnbfpbl();
            czzsnbfpbl.setHc(String.valueOf(CzzsndMapConstant.INDEX_HC_FPBL + i));
            czzsnbfpbl.setJsjdm(jsjdm);
            czzsnbfpbl.setNd(year);
            czzsnbfpbl.setSwjgzzjgdm(dj.getSwjgzzjgdm());
            czzsnbfpbl.setBl(new BigDecimal(0.00));
            czzsnbfpbl.setBqljs(new BigDecimal(0.00));
            czzsnbfpbl.setQxdm(dj.getSwjgzzjgdm().substring(0, 2));
            czzsnbfpblList.add(czzsnbfpbl);
        }
        return czzsnbfpblList;
    }

    /**
     * ��ѯ�������������������
     * @param conn         ���ݿ����Ӷ���
     * @param orManager    ORʵ��
     * @param jsjdm        ���������
     * @param nd           ���
     * @param swjgzzjgdm   ˰�������֯��������
     * @return             List
     * @throws BaseException
     */
    private List queryCzzsndzb(Connection conn,
                               ORManager orManager,
                               String jsjdm,
                               String nd,
                               String swjgzzjgdm)
        throws BaseException
    {
        List result = null;
        try
        {
            Vector v = new Vector();

            v.add("jsjdm = '" + jsjdm + "'");
            v.add("nd= '" + nd + "'");
            v.add("fsdm= '" + CodeConstant.FSDM_WSSB + "'");
            v.add("qxdm = '" + swjgzzjgdm.substring(0, 2) + "'");

            ORContext czzsndzbContext = new ORContext(Czzsnbzb.class.getName(), v);
            result = orManager.query(SESSIONID, conn, czzsndzbContext);
        }
        catch(Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "��ѯ�������������������ʧ��");
        }
        return result;
    }

    /**
     * ��ѯ���������걨��ҵ����
     * @param conn         ���ݿ����Ӷ���
     * @param orManager    ORʵ��
     * @param jsjdm        ���������
     * @param nd           ���
     * @return             List
     * @throws BaseException
     */
    private List queryCzzsqysj(Connection conn,
                               ORManager orManager,
                               String jsjdm,
                               String nd,
                               String swjgzzjgdm)
        throws BaseException
    {
        List result = null;
        try
        {
            Vector v = new Vector();

            v.add("jsjdm = '" + jsjdm + "'");
            v.add("qxdm = '" + swjgzzjgdm.substring(0, 2) + "'");
            v.add("nd= '" + nd + "' ORDER BY to_number(HC)");

            ORContext czzsnbqyContext = new ORContext(Czzsnbqy.class.getName(), v);
            result = orManager.query(SESSIONID, conn, czzsnbqyContext);
        }
        catch(Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "��ѯ�������������ҵ���ݴ���");
        }
        return result;
    }

    /**
     * ��ѯ���������걨Ͷ��������
     * @param conn         ���ݿ����Ӷ���
     * @param orManager    ORʵ��
     * @param jsjdm        ���������
     * @param nd           ���
     * @return             List
     * @throws BaseException
     */
    private List queryCzzsnbtzzsj(Connection conn, ORManager orManager,
                                  String jsjdm, String nd, String swjgzzjgdm)
        throws BaseException
    {
        List result = null;
        try
        {
            Vector v = new Vector();

            v.add("jsjdm = '" + jsjdm + "'");
            v.add("qxdm = '" + swjgzzjgdm.substring(0, 2) + "'");
            v.add("nd= '" + nd + "' ORDER BY ZJHM, ZJLXDM");

            ORContext czzsnbtzzsjContext = new ORContext(Czzsnbtzzsj.class.
                getName(), v);
            result = orManager.query(SESSIONID, conn, czzsnbtzzsjContext);
        }
        catch(Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "��ѯ�����������Ͷ�������ݴ���");
        }
        return result;
    }

    /**
     * ��ѯ���������걨Ͷ������ϸ����
     * @param conn         ���ݿ����Ӷ���
     * @param orManager    ORʵ��
     * @param jsjdm        ���������
     * @param nd           ���
     * @return             List
     * @throws BaseException
     */
    private List queryCzzsnbtzzmxsj(Connection conn,
                                    ORManager orManager,
                                    String jsjdm,
                                    String nd,
                                    String swjgzzjgdm)
        throws BaseException
    {
        List result = null;
        try
        {
            Vector v = new Vector();

            v.add("jsjdm = '" + jsjdm + "'");
            v.add("qxdm = '" + swjgzzjgdm.substring(0, 2) + "'");
            v.add("nd= '" + nd + "' ORDER BY ZJHM, ZJLXDM, TO_NUMBER(HC)");

            ORContext czzsnbtzzmxsjContext = new ORContext(Czzsnbtzzmxsj.class.getName(), v);
            result = orManager.query(SESSIONID, conn, czzsnbtzzmxsjContext);
        }
        catch(Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "��ѯ�����������Ͷ������ϸ���ݴ���");
        }
        return result;
    }

    /**
     * ��ѯ���������걨�����������
     * @param conn         ���ݿ����Ӷ���
     * @param orManager    ORʵ��
     * @param jsjdm        ���������
     * @param nd           ���
     * @return             List
     * @throws BaseException
     */
    private List queryCzzsnbfpblsj(Connection conn,
                                   ORManager orManager,
                                   String jsjdm,
                                   String nd,
                                   String swjgzzjgdm)
        throws BaseException
    {
        List result = null;
        try
        {
            Vector v = new Vector();

            v.add("jsjdm = '" + jsjdm + "'");
            v.add("qxdm = '" + swjgzzjgdm.substring(0, 2) + "'");
            v.add("nd= '" + nd + "' ORDER BY to_number(HC)");

            ORContext czzsnbfpblContext = new ORContext(Czzsnbfpbl.class.getName(), v);
            result = orManager.query(SESSIONID, conn, czzsnbfpblContext);
        }
        catch(Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "��ѯ����������ȷ���������ݴ���");
        }
        return result;
    }

    /**
     * ɾ�����������걨��������
     * @param conn ���ݿ�����
     * @param orManager ORManager��ʵ��
     * @param czzsnbzb �걨����
     * @throws BaseException �쳣
     */
    private void deleteCzzsnbzbsj(Connection conn, ORManager orManager, Czzsnbzb czzsnbzb)
        throws BaseException
    {
        try
        {
            orManager.deleteObject(SESSIONID, conn, czzsnbzb);
        }
        catch(Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "ɾ�����������걨�������ݴ���");
        }
    }

    /**
     * ɾ�����������걨��ҵ����
     * @param conn ���ݿ�����
     * @param orManager ORManager��ʵ��
     * @param czzsnbqyList ��ҵ����
     * @throws BaseException �쳣
     */
    private void deleteCzzsnbqysj(Connection conn,
                                  ORManager orManager,
                                  List czzsnbqyList)
        throws BaseException
    {
        try
        {
            for(int i = 0; i < czzsnbqyList.size(); i++)
            {
                orManager.deleteObject(SESSIONID, conn, czzsnbqyList.get(i));
            }
        }
        catch(Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "ɾ�����������걨��ҵ���ݴ���");
        }
    }

    /**
     * ɾ�����������걨Ͷ��������
     * @param conn ���ݿ�����
     * @param orManager ORManager��ʵ��
     * @param czzsnbtzzmxList Ͷ��������
     * @throws BaseException �쳣
     */
    private void deleteCzzsnbtzzmxsj(Connection conn,
                                     ORManager orManager,
                                     List czzsnbtzzmxList)
        throws BaseException
    {
        try
        {
            for(int i = 0, size = czzsnbtzzmxList.size(); i < size; i++)
            {
                List innerList = (List)czzsnbtzzmxList.get(i);
                for(int j = 0, jSize = innerList.size(); j < jSize; j++)
                {
                    orManager.deleteObject(SESSIONID, conn, innerList.get(j));
                }
            }
        }
        catch(Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "ɾ�����������걨Ͷ������ϸ���ݴ���");
        }
    }

    /**
     * ɾ�����������걨Ͷ��������
     * @param conn ���ݿ�����
     * @param orManager ORManager��ʵ��
     * @param czzsnbtzzList Ͷ��������
     * @throws BaseException �쳣
     */
    private void deleteCzzsnbtzzsj(Connection conn, ORManager orManager,
                                   List czzsnbtzzList) throws BaseException
    {
        try
        {
            for(int i = 0; i < czzsnbtzzList.size(); i++)
            {
                orManager.deleteObject(SESSIONID, conn, czzsnbtzzList.get(i));
            }
        }
        catch(Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "ɾ�����������걨Ͷ�������ݴ���");
        }
    }

    /**
     * ɾ�����������걨�����������
     * @param conn ���ݿ�����
     * @param orManager ORManager��ʵ��
     * @param czzsnbfpblList �����������
     * @throws BaseException �쳣
     */
    private void deleteCzzsnbfpblsj(Connection conn, ORManager orManager, List czzsnbfpblList)
        throws BaseException
    {
        try
        {
            for(int i = 0, size = czzsnbfpblList.size(); i < size; i++)
            {
                orManager.deleteObject(SESSIONID, conn,
                                       (Czzsnbfpbl)czzsnbfpblList.get(i));
            }
        }
        catch(Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "ɾ�����������걨Ͷ�������ݴ���");
        }
    }
}