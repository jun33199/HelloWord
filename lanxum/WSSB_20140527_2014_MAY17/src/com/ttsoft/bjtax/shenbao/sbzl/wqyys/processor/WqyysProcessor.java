package com.ttsoft.bjtax.shenbao.sbzl.wqyys.processor;

import java.sql.Connection;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.ekernel.db.or.ORManager;
import com.ekernel.db.or.ORContext;

import com.syax.bjtax.ca.util.DzyjHelper;
import com.syax.bjtax.ca.vo.DzyjsjVO;
import com.syax.common.util.CAcodeConstants;
import com.ttsoft.common.model.UserData;
import com.ttsoft.bjtax.dj.proxy.ServiceProxy;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.shenbao.model.domain.Szsm;
import com.ttsoft.bjtax.shenbao.model.domain.Wqyys;
import com.ttsoft.bjtax.shenbao.util.CAUtils;
import com.ttsoft.bjtax.shenbao.util.TinyTools;
import com.ttsoft.bjtax.shenbao.util.DBResource;
import com.ttsoft.bjtax.shenbao.util.Skssrq;
import com.ttsoft.bjtax.shenbao.zhsb.ZhsbMapConstant;
import com.ttsoft.bjtax.shenbao.sbzl.wqyys.WqyysActionConstant;
import com.ttsoft.bjtax.shenbao.sbzl.wqyys.WqyysMapConstant;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.framework.exception.*;
import com.ttsoft.bjtax.shenbao.constant.*;

/**
 * ģ�����˼��--doQuery������
 * 	ǰ̨�����������ϣ���ϸ�Ĳ��ղ�ѯ�����Ĵ����������
 * 	��̨���в�ѯ��������ڱ����걨���ݣ�����ȡ�õ����ݡ�
 * 	��������ڱ����걨���ݣ����ؿյ��б�
 * 	ͬʱ���صǼǵ����ݣ���ǰ̨����ֵ����ģ�塣
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
 * ����Ӫҵ˰Processor
 */
public class WqyysProcessor implements Processor
{

    /**
     * �ܿ��û�����
     */
    private UserData userData;

    /**
     *orManage�ĳ���
     */
    private static final long SESSIONID = 0;

    public Object process(VOPackage voPackage) throws BaseException
    {
        this.userData = voPackage.getUserData();
        // ����ҵ���������ֵ����ҵ�����
        try
        {
            switch(voPackage.getAction())
            {
                case WqyysActionConstant.INT_ACTION_QUERY:
                    voPackage.setData(doQuery((Map)voPackage.getData()));
                    return voPackage;

                case WqyysActionConstant.INT_ACTION_SAVE:
                    
                    return doSave((Map)voPackage.getData());

                case WqyysActionConstant.INT_ACTION_DELETE:
                    
                    return doDelete((Map)voPackage.getData());

                default:
                    throw new SystemException("no such method");
            }
        }
        catch(Exception e)
        {
            throw ExceptionUtil.getBaseException(e);
        }
    }

    /**
     * ��ѯ�걨���ݡ�
     * ��keyΪYqyysMapConstant.LIST_WQYYS�ű����걨����
     * @param data ��ѯ����
     * @return Map ���ؽ��
     * @throws BaseException
     */
    private Map doQuery(Map data) throws BaseException
    {
        //���������ݽṹ
        Map map = new HashMap(2);
        //�������ݿ�����
        Connection connection = null;

        // Ͷ�����걨����
        List tzzsbsjList = null;

        try
        {
            // ȡ��ҵ���������
            String jsjdm = (String)data.get(WqyysMapConstant.JSJDM);

            // ȡ����
            String skssksrq = (String)data.get(WqyysMapConstant.SKSSKSRQ);
            SWDJJBSJ jbsj = (SWDJJBSJ)data.get(WqyysMapConstant.DJJBSJ);

            Vector criteria = new Vector();
            ORContext orContext = null;

            // ȡ�����걨����
            criteria.clear();
            criteria.add("JSJDM = '" + jsjdm + "'");
            criteria.add("SKSSKSRQ = TO_DATE('" + skssksrq + "', 'yyyy-MM-dd')");
            criteria.add("FSDM = '" + CodeConstant.FSDM_WSSB + "'");
            criteria.add("QXDM = '" + jbsj.getSwjgzzjgdm().substring(0, 2) + "'");
            orContext = new ORContext(Wqyys.class.getName(), criteria);

            ORManager orManager = DBResource.getORManager();
            connection = DBResource.getConnection();

            // ��ѯ���ݿ�ȡ�����걨����
            List wqyysList = orManager.query(this.SESSIONID, connection, orContext);

            // ���÷���ֵ
            map.put(WqyysMapConstant.LIST_WQYYS, wqyysList);
        }
        catch(Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "��ѯ����ʧ��!");
        }
        finally
        {
            DBResource.destroyConnection(connection);
        }
        return map;

    }

    /**
     * ���洫�������걨����
     * @param data �������
     * @return Map ���ؽ��
     * @throws BaseException
     */
    private Object doSave(Map data) throws BaseException
    {
        Connection connection = null;
        DzyjsjVO dzyj=(DzyjsjVO)data.get(ZhsbMapConstant.CA_QMSJ_VO);
        UserData ud=userData;
        String skssksrq=(String)data.get("dzyj_skssksrq");
        Map reMap=new HashMap();
        try
        {
            try
	        {
	       	 dzyj = CAUtils.saveDzyjsj(ud,dzyj, skssksrq, "0", skssksrq, "0");
	       }catch (Exception ex)
	        {
	            ex.printStackTrace();
	            throw ExceptionUtil.getBaseException(ex);
	        }
	       reMap.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
           /* if (ud.getCaflag())
	        {
	            try
	            {
	                dzyj = (new DzyjHelper()).saveDzyjsj(dzyj,skssksrq, "0", skssksrq, "0");
	            }
	            catch (com.syax.frame.exception.ApplicationException e)
	            {
	                //e.printStackTrace();
	                throw ExceptionUtil.getBaseException(e);
	            }
	            catch (Exception ex)
	            {
	                ex.printStackTrace();
	                throw ExceptionUtil.getBaseException(ex);
	            }
                reMap.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ,dzyj);
	            //System.out.println("============����ǩ�����ݽ���==============");
	        }*/
            // ȡ�걨����ʹ��keyΪWqyysMapConstant.LIST_WQYYS
            List wqyysList = (List)data.get(WqyysMapConstant.LIST_WQYYS);

            // �Դ��ڵı����걨��ɾ���
            this.doDelForSave(data);

            // ��� ORManagerʵ��
            ORManager orManager = DBResource.getORManager();
            // ������ݿ�����
            connection = DBResource.getConnection();

            // ����ÿһ��ֵ����
            for(int i = 0, size = wqyysList.size(); i < size; i++)
            {
                orManager.makePersistent(SESSIONID, connection, wqyysList.get(i));
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
        return reMap;
    }

    /**
     * ɾ��������������������ָ���ļ�¼
     * @param data ɾ������
     * @return Map ���ؽ��
     * @throws BaseException
     */
    private Object doDelete(Map data) throws BaseException
    {
        Connection connection = null;
        DzyjsjVO dzyj=(DzyjsjVO)data.get(ZhsbMapConstant.CA_QMSJ_VO);
        UserData ud=userData;
        String skssksrq=(String)data.get("dzyj_skssksrq");
        Map reMap=new HashMap();
        try
        {
            try
	        {
	       	 dzyj = CAUtils.saveDzyjsj(ud,dzyj, skssksrq, "0", skssksrq, "0");
	       }catch (Exception ex)
	        {
	            ex.printStackTrace();
	            throw ExceptionUtil.getBaseException(ex);
	        }
	       reMap.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
           /* if (ud.getCaflag())
	        {
	            try
	            {
	                dzyj = (new DzyjHelper()).saveDzyjsj(dzyj,skssksrq, "0", skssksrq, "0");
	            }
	            catch (com.syax.frame.exception.ApplicationException e)
	            {
	                //e.printStackTrace();
	                throw ExceptionUtil.getBaseException(e);
	            }
	            catch (Exception ex)
	            {
	                ex.printStackTrace();
	                throw ExceptionUtil.getBaseException(ex);
	            }
                reMap.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ,dzyj);
	            //System.out.println("============����ǩ�����ݽ���==============");
	        }*/
            // ȡ�걨����ʹ��keyΪWqyysMapConstant.LIST_WQYYS
            List wqyysList = (List)data.get(WqyysMapConstant.LIST_WQYYS);

            // ȡ��һ��ֵ�����ʵ��
            Wqyys wqyys = (Wqyys)wqyysList.get(0);

            // ȡ���������
            String jsjdm = wqyys.getJsjdm();
            // ȡ˰��������ʼ����
            String ssksrq = TinyTools.Date2String(wqyys.getSkssksrq());

            ORManager orManager = DBResource.getORManager();
            connection = DBResource.getConnection();

            // ɾ����������
            String condition = "JSJDM = '" + jsjdm +
                "' and SKSSKSRQ = TO_DATE('" + ssksrq + "', 'yyyy-MM-dd') " +
                " and FSDM = '" + CodeConstant.FSDM_WSSB + "' " +
                " and QXDM = '" + wqyys.getQxdm() + "'";

            // ɾ���걨����
            orManager.deleteObject(SESSIONID, connection, condition, wqyys);
        }
        catch(Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "ɾ������ʧ��!");
        }
        finally
        {
            DBResource.destroyConnection(connection);
        }
        return reMap;
    }
    private Object doDelForSave(Map data) throws BaseException
    {
        Connection connection = null;
        Map reMap=new HashMap();
        try
        {
            // ȡ�걨����ʹ��keyΪWqyysMapConstant.LIST_WQYYS
            List wqyysList = (List)data.get(WqyysMapConstant.LIST_WQYYS);

            // ȡ��һ��ֵ�����ʵ��
            Wqyys wqyys = (Wqyys)wqyysList.get(0);

            // ȡ���������
            String jsjdm = wqyys.getJsjdm();
            // ȡ˰��������ʼ����
            String ssksrq = TinyTools.Date2String(wqyys.getSkssksrq());

            ORManager orManager = DBResource.getORManager();
            connection = DBResource.getConnection();

            // ɾ����������
            String condition = "JSJDM = '" + jsjdm +
                "' and SKSSKSRQ = TO_DATE('" + ssksrq + "', 'yyyy-MM-dd') " +
                " and FSDM = '" + CodeConstant.FSDM_WSSB + "' " +
                " and QXDM = '" + wqyys.getQxdm() + "'";

            // ɾ���걨����
            orManager.deleteObject(SESSIONID, connection, condition, wqyys);
        }
        catch(Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "ɾ������ʧ��!");
        }
        finally
        {
            DBResource.destroyConnection(connection);
        }
        return reMap;
    }
}