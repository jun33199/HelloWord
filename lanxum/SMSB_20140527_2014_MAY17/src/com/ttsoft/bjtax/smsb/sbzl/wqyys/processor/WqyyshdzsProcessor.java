/*
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.sbzl.wqyys.processor;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.common.db.util.BeanUtil;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.model.Wqzsf;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.sfgl.common.util.SfTimeUtil;
import com.ttsoft.bjtax.sfgl.proxy.ServiceProxy;
import com.ttsoft.bjtax.shenbao.model.domain.Szsm;
import com.ttsoft.bjtax.shenbao.model.domain.Wqyys;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.sbzl.wqyys.web.WqyyshdzsForm;
import com.ttsoft.bjtax.smsb.util.EArray;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.Skssrq;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.DateTimeUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: ������˰��˰�����ģ�飭�������걨</p>
 * <p>Description: �����������ҵӪҵ˰��˰�걨�����˶�����</p>
 * @author �������飭�������
 * @version 1.1
 */

public class WqyyshdzsProcessor
    implements Processor
{
    /**
     * ʵ��Processor�ӿ�
     * @param vo ҵ�����
     * @return Object VOPackage������
     * @throws BaseException ҵ���쳣
     * 		1 ���������Ĳ������Ͳ���ʱ�׳�
     * 		2 �����õ�ҵ�񷽷��׳�ҵ���쳣ʱ���ϴ����׳�
     * 	�����쳣�׳���EJB��process��������
     */

    public Object process (VOPackage vo)
        throws BaseException
    {
        //�ش�����
        Object result = null;
        //�ж�VO�Ƿ�Ϊ��
        if (vo == null)
        {
            throw new NullPointerException();
        }
        //����Action��ֵ���ò�ͬ��process����
        switch (vo.getAction())
        {
            case CodeConstant.SMSB_SHOWACTION: //ǰ̨Ĭ����ʾ
                result = doShow(vo);
                break;
            case CodeConstant.SMSB_QUERYACTION: //��ѯ
                result = doQuery(vo);
                break;
            case CodeConstant.SMSB_SAVEACTION:
                doSave(vo);
                break;
            case CodeConstant.SMSB_DELETEACTION:
                doDelete(vo);
                break;
            default:
                throw new UnsupportedOperationException(
                    "-----------Method process() not yet implemented.---------");
        }
        return result;
    }

    /**
     * doShow��ʼ������ҳ����ϢҪ��
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return Form
     * @throws BaseException
     */

    public Object doShow (VOPackage vo)
        throws BaseException
    {
        //�õ�Action���ݹ���ActionForm����
        WqyyshdzsForm form = (WqyyshdzsForm) vo.getData();
        EArray jsArray = new EArray();
        String tempJsStr = "";
        //�����걨����
        form.setSbrq(SfDateUtil.getDate());
        Date sbrq = SfDateUtil.getDate(form.getSbrq());
        Date nextSbrq = TinyTools.addMonth( -3, sbrq);
        //��ǰ���ڣ�������ȥ�����Ӧ���걨����
        Map skssrq = Skssrq.quarterSkssrq(nextSbrq);
        //ȡ��˰�������Ŀ�ʼ���ںͽ�������
        form.setSkssksrq(DateTimeUtil.timestampToString(
            (Timestamp) skssrq.get(Skssrq.SKSSKSRQ),
            DateTimeUtil.JAVA_DATEFORMAT));
        form.setSkssjsrq(DateTimeUtil.timestampToString(
            (Timestamp) skssrq.get(Skssrq.SKSSJSRQ),
            DateTimeUtil.JAVA_DATEFORMAT));
        tempJsStr += jsArray.getArrayByCode("szsmlist", "WQYYS_SZSMDM");
        tempJsStr +=
            jsArray.getMsgsByCode("szsmdm", "WQYYS_SZSMDM_ALL", new ArrayList());
        form.setScriptStr(tempJsStr);
        return form;
    }

    /**
     * doQuery    ���ڷ���ҳ����Ҫ��ѯ���꾡��Ϣ
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return Form
     * @throws BaseException
     */
    public Object doQuery (VOPackage vo)
        throws BaseException
    {
        //�õ�Action���ݹ���ActionForm����
        WqyyshdzsForm form = (WqyyshdzsForm) vo.getData();
        UserData ud = (UserData) vo.getUserData();
        SWDJJBSJ djxx = null;
        try
        {

            /* start added by huxiaofeng 2005.8.16*/
            //djxx = InterfaceDj.getJBSJ(form.getJsjdm(), ud);
            djxx = InterfaceDj.getJBSJ_New(form.getJsjdm(), ud);
            form.setNsrzt(djxx.getNsrzt());
            /* end added by huxiaofeng 2005.8.16*/
            form.setQxdm(InterfaceDj.getQxdm(ud));
        }
        catch (Exception ex1)
        {
            throw ExceptionUtil.getBaseException(ex1);
        }
        //�������ݿ�����
        Connection conn = null;
        Timestamp curTime = new Timestamp(System.currentTimeMillis());
        try
        {
            conn = SfDBResource.getConnection();
            EArray jsArray = new EArray();
            String tempJsStr = "";
            //ʹ��OrMap�������ݿ�ķ�װ��
            SfDBAccess da = new SfDBAccess(conn);
            Map dateMap = getSbrl(form.getSbrq());
            //��ѯ��������
            Vector vZb = new Vector();
            //�������ݽ��
            //����OrMappingֵ�����ÿ������
            String zbNames[] =
                {
                "jsjdm", "sbrq", "nsrmc", "skssksrq", "skssjsrq",
                "szdm", "szmc", "swjgzzjgdm", "lrr", "fsdm",
                "zsffdm", "zsffmc",
                "qxdm"};
            ServiceProxy proxy = new ServiceProxy();
            BeanUtil.copyBeanToBean(zbNames, djxx, form);
            //����Ӫҵ˰���շ�ʽ
            Wqzsf wqzsf = proxy.getWqzsfsInfo(form.getJsjdm(),
                                              SfDateUtil.getDate(form.
                getSkssjsrq()));
            if (wqzsf == null)
            {
                throw new ApplicationException("������Աע�⣺�ü�������벻�ܽ��к˶����գ�");
            }
            else
            {
                form.setZsfs(wqzsf.getWqzsfsdm());
            }
            //�ж��Ƿ����ں˶�����
            if (! (wqzsf.getWqzsfsdm().equals(CodeConstant.WQYYSZSFS_HDZS)))
            {
                form.setZsfs("no");
                throw new ApplicationException("������Աע�⣺�ü�������벻�ܽ��к˶����գ�");
            }
            vZb.add("qxdm='" + InterfaceDj.getQxdm(ud) + "'");
            vZb.add(" sbrq >= to_date('" +
                    String.valueOf(dateMap.get("ksrq")).substring(0, 10) +
                    "','yyyy-MM-dd') ");
            vZb.add(" sbrq < to_date('" +
                    String.valueOf(dateMap.get("jsrq")).substring(0, 10) +
                    "','yyyy-MM-dd') ");
            vZb.add("jsjdm='" + form.getJsjdm() + "' ");
            List zbData = da.query(Wqyys.class, vZb);
            //����������ݲ�����
            if (zbData.size() <= 0)
            {
                //���ݵǼǽӿڵõ���ǰ������������˰����Ϣ��Ȼ��ֵ��ActionForm

                BeanUtil.copyBeanToBean(zbNames, djxx, form);
                form.setCjrq(TinyTools.Date2String(curTime,
                    CodeConstant.DATETYPE));
                form.setSzdm("02");
                form.setSzmc("Ӫҵ˰");
                form.setZsffdm("03");
                form.setZsffmc("�˶�����");
                tempJsStr += jsArray.getArrayByCode("szsmlist", "WQYYS_SZSMDM");
                tempJsStr +=
                    jsArray.getMsgsByCode("szsmdm", "WQYYS_SZSMDM_ALL",
                                          new ArrayList());
                form.setScriptStr(tempJsStr);
                return form;
            } //end if
            //���������ݸ���ActionForm
            Wqyys orZb = (Wqyys) zbData.get(0);
            BeanUtil.copyBeanToBean(zbNames, orZb, form);
            form.setCjrq(String.valueOf(orZb.getCjrq()));
            //�����������ڵ���Ϣ
            //��ѯ��ϸ����
            //��ϸ��ѯ������װ
            Vector vMx = new Vector();
            vMx.add("qxdm='" + InterfaceDj.getQxdm(ud) + "'");
            vMx.add(" sbrq >= to_date('" +
                    String.valueOf(dateMap.get("ksrq")).substring(0, 10) +
                    "','yyyy-MM-dd') ");
            vMx.add(" sbrq < to_date('" +
                    String.valueOf(dateMap.get("jsrq")).substring(0, 10) +
                    "','yyyy-MM-dd') ");
            vMx.add(" jsjdm='" + form.getJsjdm() + "' ");
            //��ϸ��ѯ���ݽ��
            List mxData = da.query(Wqyys.class, vMx);
            //��ϸOrMappingֵ�����ÿ������
            String mxNames[] =
                {
                "szsmdm", "szsmmc", "sre", "htcje", "yjl",
                "hdsre", "jfzce", "hssre",
                "jsje", "sl", "ynse", "yinse", "bqybse"};
            //��OrMappingֵ����ת��Map���Ա�ActionForm�ܹ�ʹ��
            ArrayList mxMapData = new ArrayList();
            for (int i = 0; i < mxData.size(); i++)
            {
                //��ô�����ϸֵ
                Wqyys orMx = (Wqyys) mxData.get(i);
                Map map = new HashMap();
                //��ֵ�����ֵ����Map
                BeanUtil.copyBeanToMap(mxNames, orMx, map);
                String szsmdm = (String) map.get("szsmdm");
                String szmc = szsmdm.substring(0, 2);
                Vector vMxszsmmc = new Vector();
                Vector vMxszmc = new Vector();
                vMxszsmmc.add("szsmdm ='" + szsmdm + "'");
                vMxszmc.add("szsmdm ='" + szmc + "'");
                List szsmmcData = da.query(Szsm.class, vMxszsmmc);
                Szsm szsm = (Szsm) szsmmcData.get(0);
                List szmcData = da.query(Szsm.class, vMxszmc);
                Szsm szmcs = (Szsm) szmcData.get(0);
                map.put("szsmmc", szsm.getSzsmmc());
                map.put("szmc", szmcs.getSzsmmc());
                map.put("szsmdm_old", map.get("szsmdm"));
                mxMapData.add(map);
            }
            //����ϸ���ݽ�������
            form.setDataList(mxMapData);
            form.setCjrq(form.getCjrq());
            form.setSzdm("02");
            form.setSzmc("Ӫҵ˰");
            form.setZsffdm("03");
            form.setZsffmc("�˶�����");
            tempJsStr += jsArray.getArrayByCode("szsmlist", "WQYYS_SZSMDM");
            tempJsStr +=
                jsArray.getMsgsByCode("szsmdm", "WQYYS_SZSMDM_ALL",
                                      form.getDataList());
            form.setScriptStr(tempJsStr);
            return form;
        }
        catch (Exception ex)
        {
            //�׳��쳣
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
        finally
        {
            //�ͷ����ݿ�����
            SfDBResource.freeConnection(conn);
        }
    }

    /**
     * doSave   ���ڴ洢ҳ���ύ���꾡������Ϣ
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return Form
     * @throws BaseException
     */

    public Object doSave (VOPackage vo)
        throws BaseException
    {
        //�õ�Action���ݹ���ActionForm����
        WqyyshdzsForm form = (WqyyshdzsForm) vo.getData();
        SWDJJBSJ djxx = null;
        UserData ud = (UserData) vo.getUserData();
        try
        {

            /* start added by huxiaofeng 2005.8.16*/
            //djxx = InterfaceDj.getJBSJ(form.getJsjdm(), ud);
            djxx = InterfaceDj.getJBSJ_New(form.getJsjdm(), ud);
            /* end added by huxiaofeng 2005.8.16*/
        }
        catch (Exception ex1)
        {
            throw ExceptionUtil.getBaseException(ex1);
        }
        Timestamp curTime = new Timestamp(System.currentTimeMillis());
        //�������ݿ�����
        Connection conn = null;
        try
        {
            conn = SfDBResource.getConnection();
            //ʹ��OrMap�������ݿ�ķ�װ��
            SfDBAccess da = new SfDBAccess(conn);
            Map dateMap = getSbrl(form.getSbrq());
            Date lastSbrq = TinyTools.addMonth( -3,
                                               SfDateUtil.getDate(form.getSbrq()));
            Map getsbnd = Skssrq.quarterSkssrq(lastSbrq);
            String nd = (String) getsbnd.get(Skssrq.SKSSRQ_ND);
            //ɾ��ԭ�ȵļ�¼
            try
            {
                //ɾ����������
                da.delete(" qxdm='" + InterfaceDj.getQxdm(ud) +
                          "' and sbrq >= to_date('" +
                          String.valueOf(dateMap.get("ksrq")).substring(0, 10) +
                          "','yyyy-MM-dd') and sbrq < to_date('" +
                          String.valueOf(dateMap.get("jsrq")).substring(0, 10) +
                          "','yyyy-MM-dd') " + "and jsjdm='" + form.getJsjdm() +
                          "'",
                          new Wqyys());
            }
            catch (BaseException ex1)
            {
                throw new ApplicationException("���ݿ����ʧ�ܣ������ҹ���Ա��ϵ��");
            }
            //����OrMappingֵ�����ÿ������
            String zbNames[] =
                {
                "jsjdm", "sbrq", "skssksrq", "skssjsrq",
                "szsmdm"
            };
            //�õ���ϸ���ݼ�
            List mxMapData = form.getDataList();
            //������ϸ����ֵ����
            List mxData = new ArrayList();
            for (int i = 0; i < mxMapData.size(); i++)
            {
                Map map = (Map) mxMapData.get(i);
                //�������������ӽ���
                BeanUtil.copyBeanToMap(zbNames, form, map);
                Wqyys orMx = new Wqyys();
                //�����ݴ��ݸ���ϸֵ����
                BeanUtil.populate(orMx, map);
                java.util.Date cjrq = TinyTools.stringToDate(form.getCjrq(),
                    CodeConstant.DATETYPE);
                Timestamp cjrqmx = new Timestamp(cjrq.getTime());
                orMx.setCjrq(cjrqmx);
                orMx.setLrrq(curTime);
                orMx.setNd(nd);
                orMx.setFsdm(CodeConstant.FSDM_SMSB);
                orMx.setQxdm(InterfaceDj.getQxdm(ud));
                orMx.setSwjgzzjgdm(djxx.getSwjgzzjgdm());
                orMx.setLrr(ud.getYhid());
                orMx.setZsffdm(CodeConstant.WQYYSZSFS_HDZS);
                mxData.add(orMx);
            }
            try
            {
                //������ϸ������
                da.insert(mxData);
            }
            catch (BaseException ex4)
            {
                throw new ApplicationException("���ݿ����ʧ�ܣ������ҹ���Ա��ϵ��");
            }
            return null;
        }
        catch (Exception ex)
        {
            //�׳��쳣
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
        finally
        {
            //�ͷ����ݿ�����
            SfDBResource.freeConnection(conn);
        }
    }

    /**
     * doDelete  ����ɾ��ҳ���ύ���꾡������Ϣ
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return Form
     * @throws BaseException
     */
    public Object doDelete (VOPackage vo)
        throws BaseException
    {
        //�õ�Action���ݹ���ActionForm����
        WqyyshdzsForm form = (WqyyshdzsForm) vo.getData();
        UserData ud = (UserData) vo.getUserData();
        //�������ݿ�����
        Connection conn = null;
        try
        {
            conn = SfDBResource.getConnection();
            //ʹ��OrMap�������ݿ�ķ�װ��
            SfDBAccess da = new SfDBAccess(conn);
            Map dateMap = getSbrl(form.getSbrq());
            //ɾ��ԭ�ȵļ�¼
            try
            {
                //ɾ����ϸ����
                da.delete(" qxdm='" + InterfaceDj.getQxdm(ud) +
                          "' and sbrq >= to_date('" +
                          String.valueOf(dateMap.get("ksrq")).substring(0, 10) +
                          "','yyyy-MM-dd') and sbrq < to_date('" +
                          String.valueOf(dateMap.get("jsrq")).substring(0, 10) +
                          "','yyyy-MM-dd') " + "and jsjdm='" + form.getJsjdm() +
                          "'",
                          new Wqyys());
            }
            catch (BaseException ex1)
            {
                throw new ApplicationException("���ݿ����ʧ�ܣ������ҹ���Ա��ϵ��");
            }
            return null;
        }
        catch (Exception ex)
        {
            //�׳��쳣
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
        finally
        {
            //�ͷ����ݿ�����
            SfDBResource.freeConnection(conn);
        }
    }

    /**
     * �����걨����ȡ�õ�������һ��ͱ������һ��
     * @param curSbrq �걨����
     * @return Map  �걨�������ں���ʼ���ںͽ������ڣ�
     */
    private Map getSbrl (String curSbrq)
    {
        Date sbrq = SfDateUtil.getDate(curSbrq);
        Map skssrq = new HashMap();
        skssrq = Skssrq.quarterSkssrq(sbrq);
        Timestamp skssksrq = (Timestamp) skssrq.get(Skssrq.SKSSKSRQ);
        Timestamp skssjsrq = (Timestamp) skssrq.get(Skssrq.SKSSJSRQ);
        Map dateMap = new HashMap();
        dateMap.put("ksrq", skssksrq);
        dateMap.put("jsrq", skssjsrq);
        return dateMap;
    }

    /**
     * �õ�˰����������
     * @param curSbrq �걨����
     * @return Map ˰���������ڣ��ں���ʼ���ںͽ������ڣ�
     */
    private Map getSkssrq (String curSbrq)
    {
        //��ǰ���ڣ�������ȥ�����Ӧ���걨����
        Timestamp curTime = SfTimeUtil.getTimestamp(curSbrq);
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(curTime);
        int month = calendar.get(Calendar.MONTH);
        Map skssrq = null;
        skssrq = Skssrq.quarterSkssrq(curTime);

        //ȡ��˰�������Ŀ�ʼ���ںͽ�������
        Timestamp skssksrq = (Timestamp) skssrq.get(
            Skssrq.SKSSKSRQ);
        Timestamp skssjsrq = (Timestamp) skssrq.get(
            Skssrq.SKSSJSRQ);
        Map skssMap = new HashMap();
        skssMap.put("ksrq", skssksrq);
        skssMap.put("jsrq", skssjsrq);
        return skssMap;
    }

}
