/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.gtgshsds.processor;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.ttsoft.bjtax.dj.model.QYRY;
//�Ǽǽӿڽ����
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.dj.model.YHZH;
import com.ttsoft.bjtax.sfgl.common.db.util.BeanUtil;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
//smsbbaselib�ṩ�Ĺ�����
import com.ttsoft.bjtax.sfgl.common.util.Debug;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.sfgl.common.util.SfHashList;
import com.ttsoft.bjtax.sfgl.common.util.SfTimeUtil;
import com.ttsoft.bjtax.shenbao.model.domain.Gtgshsdsmx;
//OrMappingֵ�����
import com.ttsoft.bjtax.shenbao.model.domain.Gtgshsdsz;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.sbzl.gtgshsds.GtgshsdsHelper;
import com.ttsoft.bjtax.smsb.sbzl.gtgshsds.web.GtgshsdsForm;
import com.ttsoft.bjtax.smsb.util.InterfaceDj; //smsbbaselib�ṩ�Ĺ�����
//�걨�ڲ�������
import com.ttsoft.bjtax.smsb.util.Skssrq;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil; //�걨�ڲ�������
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description: ���幤�̻�����˰</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class GtgshsdsProcessor
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
        //�õ�Action���ݹ���ActionForm����
        GtgshsdsForm form = (GtgshsdsForm) vo.getData();
        //����ʱ��

        //�����걨����
        form.setSbrq(SfDateUtil.getDate());
        //��ǰ���ڣ�������ȥ�����Ӧ���걨����
        Map skssrq = getSkssrq(form.getSbrq());
        //ȡ��˰�������Ŀ�ʼ���ںͽ�������
        form.setSkssksrq( (String) skssrq.get("skssksrq"));
        form.setSkssjsrq( (String) skssrq.get("skssjsrq"));

        List dataList = GtgshsdsHelper.getShowList();
        form.setDataList(dataList);
        return form;
    }

    /**
     * doQuery    ���ڷ���ҳ����Ҫ��ѯ���꾡��Ϣ
     * @param     vo ҵ�����
     * @return    ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
     * @throws BaseException    ��������������׳��쳣��Ϣ
     *
     */
    public Object doQuery (VOPackage vo)
        throws BaseException
    {
        //�õ�Action���ݹ���ActionForm����
        GtgshsdsForm form = (GtgshsdsForm) vo.getData();
        //�������ݿ�����
        Timestamp curTime = new Timestamp(System.currentTimeMillis());
        Connection conn = null;
        try
        {

            //��ѯ��������
            //����OrMappingֵ�����ÿ������
            String zbNames[] =
                {
                "jsjdm", "sbrq", "skssksrq", "skssjsrq",
                "xm", "scjyrq", "yhdm", "zh", "nd", "swjgzzjgdm",
                "lrr", "fsdm", "zjhm", "zjlxdm", "nsrmc",
                "gjbzhydm", "qxdm"};
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
                Debug.out(InterfaceDj.getQxdm(ud));
            }
            catch (Exception ex1)
            {
                List dataList = GtgshsdsHelper.getShowList();
                form.setDataList(dataList);
                throw ExceptionUtil.getBaseException(ex1);
            }
            if (! (djxx.getDjzclxdm().equals(CodeConstant.GTGSH_CODE))
                && ! (djxx.getDjzclxdm().equals(CodeConstant.DJZCLXDM_GRHH)))
            {
                List dataList = GtgshsdsHelper.getShowList();
                form.setDataList(dataList);
                throw new ApplicationException("�˼�������벻���ڸ��幤�̻���");
            }
            conn = SfDBResource.getConnection();
            //ʹ��OrMap�������ݿ�ķ�װ��
            SfDBAccess da = new SfDBAccess(conn);
            Map dateMap = getSbrl(form.getSbrq());
            Vector vZb = new Vector();
            vZb.add("qxdm='" + InterfaceDj.getQxdm(ud) + "'");
            vZb.add(" sbrq>=to_date('" +
                    String.valueOf(dateMap.get("ksrq")).substring(0, 10) +
                    "','yyyy-MM-dd') ");
            vZb.add(" sbrq<to_date('" +
                    String.valueOf(dateMap.get("jsrq")).substring(0, 10) +
                    "','yyyy-MM-dd') ");
            vZb.add(" jsjdm='" + form.getJsjdm() + "' ");
            //�������ݽ��
            List zbData = da.query(Gtgshsdsz.class, vZb);
            //����������ݲ�����
            if (zbData.size() <= 0)
            {
                Debug.out("----------From DJ Interface----------------");
                //���ݵǼǽӿڵõ���ǰ������������˰����Ϣ��Ȼ��ֵ��ActionForm
                BeanUtil.copyBeanToBean(zbNames, djxx, form);
                form.setDz(djxx.getJydz());
                form.setQyyb(djxx.getZcdzyb());
                form.setLxdh(djxx.getZcdzlxdh());
                form.setNsrmc(djxx.getNsrmc());
                form.setQxdm(InterfaceDj.getQxdm(ud));
                QYRY qyry = InterfaceDj.getQYRY(form.getJsjdm());

                BeanUtil.copyBeanToBean(zbNames, qyry, form);
                //��ʼ���б���ʾ���ƣ���Ϊ���û���û����Ӧ����˰��Ϣ
                List zhList = InterfaceDj.getYHZH(form.getJsjdm());
                if (zhList.size() > 0)
                {
                    YHZH yhzh = (YHZH) zhList.get(0);
                    form.setYhdm(yhzh.getYhdm());
                    form.setZh(yhzh.getZh());
                }
                form.setCjrq(TinyTools.Date2String(curTime,
                    CodeConstant.DATETYPE));
                form.setLrrq(SfTimeUtil.getDateFromDateTime(curTime));
                form.setDataList(GtgshsdsHelper.getShowList());
                return form;
            } //end if
            //���������ݸ���ActionForm
            Gtgshsdsz orZb = (Gtgshsdsz) zbData.get(0);
            BeanUtil.copyBeanToBean(zbNames, orZb, form);
            form.setCjrq(String.valueOf(orZb.getCjrq()));
            //�����������ڵ���Ϣ
            //ҵ����Ϣ
            QYRY qyry = InterfaceDj.getQYRY(form.getJsjdm());
            BeanUtil.copyBeanToBean(zbNames, qyry, form);
            form.setDz(djxx.getJydz());
            form.setQyyb(djxx.getZcdzyb());
            form.setLxdh(djxx.getZcdzlxdh());
            form.setNsrmc(djxx.getNsrmc());
            //��ѯ��ϸ����
            //��ϸ��ѯ������װ
            Vector vMx = new Vector();
            vMx.add("qxdm='" + InterfaceDj.getQxdm(ud) + "'");
            vMx.add(" sbrq>=to_date('" +
                    String.valueOf(dateMap.get("ksrq")).substring(0, 10) +
                    "','yyyy-MM-dd') ");
            vMx.add(" sbrq<to_date('" +
                    String.valueOf(dateMap.get("jsrq")).substring(0, 10) +
                    "','yyyy-MM-dd')");
            vMx.add(" jsjdm='" + form.getJsjdm() + "' ");
            //��ϸ��ѯ���ݽ��
            List mxData = da.query(Gtgshsdsmx.class, vMx);
            //��ϸOrMappingֵ�����ÿ������
            String mxNames[] =
                {
                "hc", "xmmc", "je"};
            //��OrMappingֵ����ת��Map���Ա�ActionForm�ܹ�ʹ��
            SfHashList showList = new SfHashList(GtgshsdsHelper.getShowList());
            List mxMapData = new ArrayList();
            for (int i = 0; i < mxData.size(); i++)
            {
                //��ô�����ϸֵ
                Gtgshsdsmx orMx = (Gtgshsdsmx) mxData.get(i);
                Debug.out(orMx.getJsjdm());
                Map map = new HashMap();
                //��ֵ�����ֵ����Map
                BeanUtil.copyBeanToMap(mxNames, orMx, map);
                int location = showList.findFirst(0, "hc", orMx.getHc());
                map.put("xmmc", showList.get(i, "xmmc"));
                mxMapData.add(map);
            }
            //����ϸ���ݽ�������
            form.setDataList(mxMapData);
            form.setCjrq(form.getCjrq());
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
        //�õ�Action���ݹ���ActionForm����
        GtgshsdsForm form = (GtgshsdsForm) vo.getData();
        Timestamp curTime = new Timestamp(System.currentTimeMillis());
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
                da.delete(" qxdm='" + InterfaceDj.getQxdm(ud)
                          + "' and sbrq>=to_date('" +
                          String.valueOf(dateMap.get("ksrq")).substring(0, 10) +
                          "','yyyy-MM-dd') and sbrq<to_date('" +
                          String.valueOf(dateMap.get("jsrq")).substring(0, 10) +
                          "','yyyy-MM-dd') " + "and jsjdm='" + form.getJsjdm() +
                          "'",
                          new Gtgshsdsmx());
            }
            catch (BaseException ex1)
            {
                throw new ApplicationException("���ݿ����ʧ�ܣ������ҹ���Ա��ϵ��");
            }
            try
            {
                //ɾ����������
                da.delete(" qxdm='" + InterfaceDj.getQxdm(ud)
                          + "' and sbrq>=to_date('" +
                          String.valueOf(dateMap.get("ksrq")).substring(0, 10) +
                          "','yyyy-MM-dd') and sbrq<to_date('" +
                          String.valueOf(dateMap.get("jsrq")).substring(0, 10) +
                          "','yyyy-MM-dd') " + "and jsjdm='" + form.getJsjdm() +
                          "'",
                          new Gtgshsdsz());
            }
            catch (BaseException ex2)
            {
                throw new ApplicationException("���ݿ����ʧ�ܣ������ҹ���Ա��ϵ��");
            }
            Map getsbnd = Skssrq.otherSkssrq(SfDateUtil.getDate(form.getSbrq()));
            String nd = (String) getsbnd.get(Skssrq.SKSSRQ_ND);
            //����OrMappingֵ�����ÿ������
            String zbNames[] =
                {
                "jsjdm", "sbrq", "skssksrq", "skssjsrq",
                "xm", "scjyrq", "yhdm", "zh",
            };
            //OrMapping����ֵ����
            Gtgshsdsz orZb = new Gtgshsdsz();
            //������ֵ����ֵ
            BeanUtil.copyBeanToBean(zbNames, form, orZb);
            java.util.Date cjrq = TinyTools.stringToDate(form.getCjrq(),
                CodeConstant.DATETYPE);
            Timestamp cjrqmx = new Timestamp(cjrq.getTime());
            orZb.setCjrq(cjrqmx);
            orZb.setLrrq(curTime);
            orZb.setNd(nd);
            orZb.setFsdm(CodeConstant.FSDM_SMSB);
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
                map.put("cjrq", cjrqmx);
                map.put("sbrq", form.getSbrq());
                map.put("lrrq", curTime);
                map.put("swjgzzjgdm", djxx.getSwjgzzjgdm());
                map.put("nd", nd);
                map.put("qxdm", InterfaceDj.getQxdm(ud));
                Gtgshsdsmx orMx = new Gtgshsdsmx();
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
        //�õ�Action���ݹ���ActionForm����
        GtgshsdsForm form = (GtgshsdsForm) vo.getData();
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
                da.delete(" qxdm='" + InterfaceDj.getQxdm(ud)
                          + "' and sbrq>=to_date('" +
                          String.valueOf(dateMap.get("ksrq")).substring(0, 10) +
                          "','yyyy-MM-dd') and sbrq<to_date('" +
                          String.valueOf(dateMap.get("jsrq")).substring(0, 10) +
                          "','yyyy-MM-dd') " + "and jsjdm='" + form.getJsjdm() +
                          "'",
                          new Gtgshsdsmx());
            }
            catch (BaseException ex1)
            {
                throw new ApplicationException("���ݿ����ʧ�ܣ������ҹ���Ա��ϵ��");
            }
            try
            {
                //ɾ����������
                da.delete(" qxdm='" + InterfaceDj.getQxdm(ud)
                          + "' and sbrq>=to_date('" +
                          String.valueOf(dateMap.get("ksrq")).substring(0, 10) +
                          "','yyyy-MM-dd') and sbrq<to_date('" +
                          String.valueOf(dateMap.get("jsrq")).substring(0, 10) +
                          "','yyyy-MM-dd') " + "and jsjdm='" + form.getJsjdm() +
                          "'",
                          new Gtgshsdsz());
            }
            catch (BaseException ex2)
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
     * �����걨����ȡ�õ�ǰ�µ�һ������µ�һ��
     * @param curSbrq �걨����
     * @return dateMap ��ʼ���ںͽ�������
     */
    private Map getSbrl (String curSbrq)
    {
        Timestamp sbrq = SfTimeUtil.getTimestamp(curSbrq);
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(sbrq);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        Timestamp ksrq = new Timestamp(new GregorianCalendar(year, month, 1).
                                       getTime().getTime());
        Timestamp jsrq = new Timestamp(new GregorianCalendar(year, month + 1, 1).
                                       getTime().getTime());
        Map dateMap = new HashMap();
        dateMap.put("ksrq", ksrq);
        dateMap.put("jsrq", jsrq);
        return dateMap;
    }

    /**
     * ������ȥ�����Ӧ���걨����
     * @param curSbrq �걨����
     * @return  skssMap
     */
    private Map getSkssrq (String curSbrq)
    {
        //��ǰ���ڣ�������ȥ�����Ӧ���걨����
        Timestamp curTime = SfTimeUtil.getTimestamp(curSbrq);
        String skssksrq = "";
        String skssjsrq = "";
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(curTime);
        int month = calendar.get(Calendar.MONTH);
        Map skssrq = null;
        if (month == 1)
        {
            skssrq = Skssrq.yearSkssrq(curTime);
        }
        else
        {
            skssrq = Skssrq.monthSkssrq(curTime);
        }
        //ȡ��˰�������Ŀ�ʼ���ںͽ�������
        skssksrq = SfTimeUtil.getDateFromDateTime( (Timestamp) skssrq.get(
            Skssrq.SKSSKSRQ));
        skssjsrq = SfTimeUtil.getDateFromDateTime( (Timestamp) skssrq.get(
            Skssrq.SKSSJSRQ));
        Map skssMap = new HashMap();
        skssMap.put("skssksrq", skssksrq);
        skssMap.put("skssjsrq", skssjsrq);
        return skssMap;
    }

}
