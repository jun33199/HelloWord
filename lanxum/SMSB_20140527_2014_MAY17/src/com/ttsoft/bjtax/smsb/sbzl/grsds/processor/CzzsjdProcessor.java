/*
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.sbzl.grsds.processor;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.common.db.util.BeanUtil;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.model.Grzsfs;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.sfgl.common.util.SfTimeUtil;
import com.ttsoft.bjtax.sfgl.model.orobj.Tzf;
import com.ttsoft.bjtax.sfgl.proxy.ServiceProxy;
import com.ttsoft.bjtax.shenbao.model.domain.Czzsjbqysj;
import com.ttsoft.bjtax.shenbao.model.domain.Czzsjbtzzsj;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.sbzl.grsds.web.CzzsjdForm;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.Skssrq;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: �������ո��˶�����ҵ�ͺϻ���ҵͶ���߸�������˰�����걨��</p>
 * @author �������飭�������
 * @version 1.1
 */
public class CzzsjdProcessor
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
            case CodeConstant.SMSB_SAVEACTION: //�洢
                doSave(vo);
                break;
            case CodeConstant.SMSB_DELETEACTION: //ɾ��
                doDelete(vo);
                break;
            default:
                throw new UnsupportedOperationException(
                    "Method process() not yet implemented.");
        }
        return result;
    }

    /**
     * doShow��ʼ������ҳ����ϢҪ��
     * @param vo ҵ�����
     * @return   ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
     * @throws BaseException ��������������׳��쳣��Ϣ
     */
    public Object doShow (VOPackage vo)
        throws BaseException
    {
        //�õ�CzzsjdAction���ݹ���CzzsjdForm����
        CzzsjdForm form = (CzzsjdForm) vo.getData();
        //��ǰ���ڣ�������ȥ�����Ӧ���걨����
        Timestamp curTime = new Timestamp(System.currentTimeMillis());
        //��ǰ���ڣ�������ȥ��ü��ȵ��걨����
        Map getsbjd = Skssrq.otherSkssrq(curTime);
        Timestamp skssksrq = (Timestamp) getsbjd.get(Skssrq.SKSSKSRQ);
        //˰��˰��������������
        Timestamp skssjsrq = (Timestamp) getsbjd.get(Skssrq.SKSSJSRQ);
        //�ѵõ���˰��������ʼ������Timestamp����ת��String���Ͳ����뵽CzzsjdForm��
        form.setSkssksrq(SfTimeUtil.getDateFromDateTime(skssksrq));
        //�ѵõ���˰����������������Timestamp����ת��String���Ͳ����뵽CzzsjdForm��
        form.setSkssjsrq(SfTimeUtil.getDateFromDateTime(skssjsrq));
        //������Ϣ����CzzsjdForm��
        form.setSbrq(SfDateUtil.getDate());
        String jd = Skssrq.preQuarter(SfDateUtil.getDate(form.getSkssksrq()));
        form.setJd(jd);
        return form;
    }

    /**
     * doQuery    ���ڷ���ҳ����Ҫ��ѯ���꾡��Ϣ
     * @param     vo ҵ�����
     * @return    ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
     * @throws BaseException    ��������������׳��쳣��Ϣ
     */
    public Object doQuery (VOPackage vo)
        throws BaseException
    {
        //�õ�CzzsjdAction���ݹ���CzzsjdForm����
        CzzsjdForm form = (CzzsjdForm) vo.getData();
        Map lastYear = Skssrq.yearSkssrq(SfDateUtil.getDate(form.getSbrq()));
        Timestamp lastskssjsrq = (Timestamp) lastYear.get(Skssrq.SKSSJSRQ);
        java.util.Date time = SfDateUtil.getDate(lastskssjsrq.toString());
        //Ͷ�ʷ�����
        List tzfinfo = new ArrayList();
        //��ǰ����
        Date date = SfDateUtil.getDate(form.getSbrq());
        //���ݿ�����
        Connection conn = null;
        try
        {
            //���ݿ�����

            ServiceProxy proxy = new ServiceProxy();
            //����OrMappingֵ�����ÿ������
            String zbNames[] = {
                               "fsdm", "jd", "jsjdm", "lrr", "lrze", "nd",
                               "nsrmc", "sbrq",
                               "qxdm",
                               "skssjsrq", "skssksrq", "swdjzh", "swjgzzjgdm"};
            //�Ǽǻ�������
            SWDJJBSJ djxx = null;
            UserData ud = (UserData) vo.getUserData();
            try {
                //�Ǽǽӿ�
                djxx = InterfaceDj.getJBSJ(form.getJsjdm(), ud);
            }
            catch (Exception ex1)
            {
                throw ExceptionUtil.getBaseException(ex1);
            }
            //��˰������
            form.setNsrmc(djxx.getNsrmc());
            //���Ƶ�form
            BeanUtil.copyBeanToBean(zbNames, djxx, form);
            //�õ�����
            int query = TinyTools.getQuarter(SfDateUtil.getDate(form.getSbrq()));
            //���ռ��Ȼ���
            //1����
            if (query == 1)
            {

                //�����������շ�ʽ�ӿ�
                Grzsfs grzsfs = (Grzsfs) proxy.getGrzsfsInfo(form.getJsjdm(),
                    time);
                if (grzsfs == null)
                {
                    throw new ApplicationException("������Աע�⣺�ü��������û�в������շ�ʽ��");
                }
                else
                {
                    form.setZsfs(grzsfs.getZsfsdm());
                }
                if (! (grzsfs.getZsfsdm().equals(CodeConstant.ZSFSDM_CZZS_GR)))
                {
                    form.setZsfs("no");
                    throw new ApplicationException("������Աע�⣺�ü��������û�в������շ�ʽ��");
                }
            }
            //2��3��4����
            else
            {
                //�����������շ�ʽ�ӿ�
                Grzsfs grzsfs = (Grzsfs) proxy.getGrzsfsInfo(form.getJsjdm(),
                    SfDateUtil.getDate(form.getSkssjsrq()));
                if (grzsfs == null)
                {
                    throw new ApplicationException("������Աע�⣺�ü��������û�в������շ�ʽ��");
                }
                else
                {
                    form.setZsfs(grzsfs.getZsfsdm());
                }
                if (! (grzsfs.getZsfsdm().equals(CodeConstant.ZSFSDM_CZZS_GR)))
                {
                    form.setZsfs("no");
                    throw new ApplicationException("������Աע�⣺�ü��������û�в������շ�ʽ��");

                }
            }
            if (query == 1)
            {
                //Ͷ���˽ӿ�
                tzfinfo = proxy.getTzfInfo(form.getJsjdm(), time);
            }
            else
            {
                //Ͷ���˽ӿ�
                tzfinfo = proxy.getTzfInfo(form.getJsjdm(),
                                           SfDateUtil.getDate(form.getSkssjsrq()));
            }
            if (tzfinfo == null || tzfinfo.size() == 0)
            {
                throw new ApplicationException("û�и���ҵͶ������Ϣ��");
            }
            Timestamp curTime = new Timestamp(System.currentTimeMillis());
            //���ò�ѯ����
            Map getsbnd = Skssrq.otherSkssrq(date);
            //����
            String jd = Skssrq.preQuarter(date);
            String nd = this.getNd(jd, getsbnd, form.getSbrq());
            conn = SfDBResource.getConnection();
            //ʹ��OrMap�������ݿ�ķ�װ��
            SfDBAccess da = new SfDBAccess(conn);
            //��ѯ��������
            Vector vZb = new Vector();
            
            //�������ش��롢��������롢��ȡ��������ò�ѯ����
            vZb.add("qxdm='" + InterfaceDj.getQxdm(ud) + "'");
            vZb.add("jd='" + jd + "'");
            vZb.add("nd='" + nd + "'");
            vZb.add("jsjdm='" + form.getJsjdm() + "'");
            
            //���Ҽ�¼
            List zbData = da.query(Czzsjbqysj.class, vZb);
            
            //����������ݲ�����
            if (zbData.size() <= 0)
            {
                //���ݵǼǽӿڵõ���ǰ������������˰����Ϣ��Ȼ��ֵ��CzzsjdForm
                //���ݼ����������˰�ѹ������ݿ��в���Ͷ���˻�����Ϣ
                if (djxx == null)
                {
                    form.setJsjdm(null);
                    form.setNsrmc(null);
                    form.setLrze(null);
                    throw new ApplicationException("�˼�������벻���ڣ�");
                }
                else
                {
                    form.setNsrmc(djxx.getNsrmc());
                }
                form.setNsrmc(djxx.getNsrmc());
                //����һЩ������Ϣ����
                form.setCjrq(TinyTools.Date2String(curTime,
                    CodeConstant.DATETYPE));
                form.setSbrq(form.getSbrq());
                //Ͷ������Ϣ
                if (tzfinfo == null || tzfinfo.size() == 0)
                {
                    throw new ApplicationException("û�и���ҵͶ������Ϣ��");
                }
                form.setCjrq(TinyTools.Date2String(curTime,
                    CodeConstant.DATETYPE));
                String tzfNames[] =
                                    {
                                    "tzfmc", "zjlxdm", "zjhm", "fpbl"};
                List mxMapDatas = new ArrayList();
                for (int i = 0; i < tzfinfo.size(); i++)
                {
                    //ͨ��˰Ŀ����Ԥ���Ŀ�Ľӿ��еĲ���Ͷ������Ϣ��ϸֵ
                    Tzf orMxs = (Tzf) tzfinfo.get(i);
                    Map maps = new HashMap();
                    //��ֵ�����ֵ����Map
                    BeanUtil.copyBeanToMap(tzfNames, orMxs, maps);
                    Object tzfRen = maps.get("tzfmc");
                    maps.remove("tzfmc");
                    maps.put("tzzxm", tzfRen);
                    //"ynssde","sysl","sskcs","ynsdse","jmse","qcwjsdse","yjnsdse","sjyse"��ʼ��
                    maps.put("ynssde", new Integer(0));
                    maps.put("sysl", new Float(0));
                    maps.put("sskcs", new Integer(0));
                    maps.put("ynsdse", new Integer(0));
                    maps.put("jmse", new Integer(0));
                    maps.put("qcwjsdse", new Integer(0));
                    maps.put("yjnsdse", new Integer(0));
                    maps.put("sjyjse", new Integer(0));
                    mxMapDatas.add(maps);
                }
                //���鵽�Ľ��ѹ�뵽DataList��
                form.setDataList(mxMapDatas);
                //��ֵ����
                return form;
            }
            //����������ݴ��ڽ��������ݸ���CzzsjdForm
            Czzsjbqysj orZb = (Czzsjbqysj) zbData.get(0);
            BeanUtil.copyBeanToBean(zbNames, orZb, form);
            form.setCjrq(String.valueOf(orZb.getCjrq()));
            //��ѯ��ϸ����
            //��ϸ��ѯ������װ
            Vector vMx = new Vector();
            vMx.add("qxdm='" + InterfaceDj.getQxdm(ud) + "'");
            vMx.add("jd='" + jd + "'");
            vMx.add("nd='" + nd + "'");
            vMx.add("jsjdm='" + form.getJsjdm() + "'");

            //��ϸ��ѯ���ݽ��
            List mxData = da.query(Czzsjbtzzsj.class, vMx);
            //��ϸOrMappingֵ�����ÿ������
            String mxNames[] =
                               {
                               "zjlxdm", "zjhm", "tzzxm", "fpbl", "ynssde",
                               "sysl", "sskcs",
                               "ynsdse", "jmse", "qcwjsdse", "yjnsdse",
                               "sjyjse"};
            List mxMapData = new ArrayList();
            for (int i = 0; i < mxData.size(); i++)
            {
                //��ô�����ϸֵ
                Czzsjbtzzsj orMx = (Czzsjbtzzsj) mxData.get(i);
                Map map = new HashMap();
                //��ֵ�����ֵ����Map
                BeanUtil.copyBeanToMap(mxNames, orMx, map);
                mxMapData.add(map);
            }
            //���鵽�Ľ��ѹ�뵽DataList��
            form.setDataList(mxMapData);
            //��ֵ����
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
     * @param   vo ҵ�����
     * @return  ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]
     * @throws BaseException ��������������׳��쳣��Ϣ
     */
    public Object doSave (VOPackage vo)
        throws BaseException
    {
        //�õ�CzzsjdAction���ݹ���CzzsjdForm����
        CzzsjdForm form = (CzzsjdForm) vo.getData();
        UserData ud = (UserData) vo.getUserData();
        //�Ǽǽӿ�
        SWDJJBSJ djxx = null;
        try
        {
            //�Ǽǽӿ���Ϣ
            djxx = InterfaceDj.getJBSJ(form.getJsjdm(), ud);
        }
        catch (Exception ex1)
        {
            throw ExceptionUtil.getBaseException(ex1);
        }
        //���ݿ�����
        Connection conn = null;
        try
        {
            //���ݿ�����
            conn = SfDBResource.getConnection();
            //ʹ��OrMap�������ݿ�ķ�װ��
            SfDBAccess da = new SfDBAccess(conn);
            //����ɾ������
            Map getsbnd = Skssrq.otherSkssrq(SfDateUtil.getDate(form.getSbrq()));
            //����
            String jd = Skssrq.preQuarter(SfDateUtil.getDate(form.getSbrq()));
            String nd = this.getNd(jd, getsbnd, form.getSbrq());
            Timestamp curTime = new Timestamp(System.currentTimeMillis());
            form.setLrrq(SfTimeUtil.getDateFromDateTime(curTime));
            String SQL = "  qxdm='" + InterfaceDj.getQxdm(ud) + "'"
                         + "and jsjdm='" +
                         form.getJsjdm() + "' and jd='" + jd + "' and nd='"
                         + nd + "'" +
                         "order by  nd ASC";
            //ɾ��ԭ�ȵļ�¼
            try
            {
                //ɾ����ϸ����
                da.delete("  qxdm='" + InterfaceDj.getQxdm(ud) + "'"
                          + "and nd='" + nd
                          + "' and jd='" + jd + "' and jsjdm='" + form.getJsjdm()
                          + "'", new Czzsjbtzzsj());
            }
            catch (BaseException ex1)
            {
                throw new ApplicationException(" ���ݿ����ʧ�ܣ������ҹ���Ա��ϵ��");
            }
            try
            {
                //ɾ����������
                da.delete("  qxdm='" + InterfaceDj.getQxdm(ud) + "'"
                          + "and nd='" + nd
                          + "' and jd='" + jd + "' and jsjdm='" + form.getJsjdm()
                          + "'", new Czzsjbqysj());
            }
            catch (BaseException ex2)
            {
                throw new ApplicationException(" ���ݿ����ʧ�ܣ������ҹ���Ա��ϵ��");
            }
            //����OrMappingֵ�����ÿ������
            String zbNames[] =
                               {
                               "jsjdm", "lrze",
                               "sbrq", "skssjsrq", "skssksrq"};
            //OrMapping����ֵ����
            Czzsjbqysj orZb = new Czzsjbqysj();
            //������ֵ����ֵ
            HashMap new_data = new HashMap();
            BeanUtil.copyBeanToMap(zbNames, form, new_data);
            BeanUtil.populate(orZb, new_data);
            Date cjrq = TinyTools.stringToDate(form.getCjrq(),
                                               CodeConstant.DATETYPE);
            Timestamp cjrqmx = new Timestamp(cjrq.getTime());
            orZb.setCjrq(cjrqmx);
            orZb.setLrrq(curTime);
            orZb.setJd(jd);
            orZb.setNd(nd);
            orZb.setFsdm(CodeConstant.FSDM_SMSB);
            orZb.setSwdjzh(djxx.getSwdjzh());
            orZb.setQxdm(InterfaceDj.getQxdm(ud));
            orZb.setSwjgzzjgdm(djxx.getSwjgzzjgdm());
            orZb.setLrr(ud.getYhid());
            try
            {
                //������������
                da.insert(orZb);
            }
            catch (BaseException ex3)
            {
                throw new ApplicationException("���ݿ����ʧ�ܣ������ҹ���Ա��ϵ��");
            }
            //�õ���ϸ���ݼ�
            List mxMapData = form.getDataList();
            //������ϸ����ֵ����
            List mxData = new ArrayList();
            for (int i = 0; i < mxMapData.size(); i++)
            {
                Map map = (Map) mxMapData.get(i);
                //���һЩ�����Ϣ
                map.put("jsjdm", form.getJsjdm());
                map.put("nd", nd);
                map.put("jd", jd);
                map.put("cjrq", cjrqmx);
                map.put("lrrq", curTime);
                map.put("swjgzzjgdm", djxx.getSwjgzzjgdm());
                map.put("qxdm", InterfaceDj.getQxdm(ud));
                BeanUtil.copyBeanToMap(zbNames, form, map);
                Czzsjbtzzsj orMx = new Czzsjbtzzsj();
                //�����ݴ��ݸ���ϸֵ����
                BeanUtil.populate(orMx, map);
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
     * @param    vo ҵ�����
     * @return   ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]
     * @throws BaseException ��������������׳��쳣��Ϣ
     */
    public Object doDelete (VOPackage vo)
        throws BaseException
    {
        //�õ�CzzsjdAction���ݹ���CzzsjdForm����
        CzzsjdForm form = (CzzsjdForm) vo.getData();
        //UserData����
        UserData ud = (UserData) vo.getUserData();
        //���ݿ�����
        Connection conn = null;
        try
        {
            conn = SfDBResource.getConnection();
            //ʹ��OrMap�������ݿ�ķ�װ��
            SfDBAccess da = new SfDBAccess(conn);
            //����ɾ������
            Map getsbnd = Skssrq.otherSkssrq(SfDateUtil.getDate(form.getSbrq()));
            //����
            String jd = Skssrq.preQuarter(SfDateUtil.getDate(form.getSbrq()));
            //���
            String nd = this.getNd(jd, getsbnd, form.getSbrq());
            //��ǰʱ��
            Timestamp curTime = new Timestamp(System.currentTimeMillis());
            form.setSbrq(SfDateUtil.getDate());
            form.setNsrmc(null);
            form.setLrze(null);
            //ɾ��ԭ�ȵļ�¼
            try
            {
                //ɾ����ϸ����
                da.delete("  qxdm='" + InterfaceDj.getQxdm(ud) + "'"
                          + "and nd='" + nd
                          + "' and jd='" + jd + "' and jsjdm='" + form.getJsjdm()
                          + "'", new Czzsjbtzzsj());
            }
            catch (BaseException ex1)
            {
                throw new ApplicationException("���ݿ����ʧ�ܣ������ҹ���Ա��ϵ��");
            }
            try
            {
                //ɾ����������
                da.delete("  qxdm='" + InterfaceDj.getQxdm(ud) + "'"
                          + "and nd='" + nd
                          + "' and jd='" + jd + "' and jsjdm='" + form.getJsjdm()
                          + "'", new Czzsjbqysj());
            }
            catch (BaseException ex2)
            {
                throw new ApplicationException("���ݿ����ʧ�ܣ������ҹ���Ա��ϵ��");
            }
            form.setJsjdm(null);
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
     * ��������������
     * @param jd ����
     * @param getsbnd �������͵�˰����������
     * @param sbrq �걨����
     * @return �������
     */
    private String getNd (String jd, Map getsbnd, String sbrq)
    {
        String nd;
        int year;
        if (jd.equals("4"))
        {
            nd = (String) getsbnd.get(Skssrq.SKSSRQ_ND);
        }
        else
        {
            nd = (String) getsbnd.get(Skssrq.SKSSRQ_ND);
        }
        return nd;
    }
}
