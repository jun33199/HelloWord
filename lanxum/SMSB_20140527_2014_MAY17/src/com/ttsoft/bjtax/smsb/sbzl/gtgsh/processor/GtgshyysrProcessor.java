/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.gtgsh.processor;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

//�Ǽǽӿڽ����
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.common.db.util.BeanUtil;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
//smsbbaselib�ṩ�Ĺ�����
import com.ttsoft.bjtax.sfgl.common.util.Debug;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.sfgl.common.util.SfHashList;
import com.ttsoft.bjtax.sfgl.common.util.SfTimeUtil;
import com.ttsoft.bjtax.shenbao.model.domain.Gtgshyysrmx;
//OrMappingֵ�����
import com.ttsoft.bjtax.shenbao.model.domain.Gtgshyysrz;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.sbzl.gtgsh.GtgshHelper;//�걨�ڲ�������
import com.ttsoft.bjtax.smsb.sbzl.gtgsh.web.GtgshyysrForm;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;//smsbbaselib�ṩ�Ĺ�����
//�걨�ڲ�������
import com.ttsoft.bjtax.smsb.util.Skssrq;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description: ���幤�̻�Ӫҵ����</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class GtgshyysrProcessor
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
            case CodeConstant.SMSB_CXWSZACTION: //��Ҫ���µĲ�ѯ
                result = doUpdateQuery(vo);
                break;
            default:
                throw new UnsupportedOperationException(
                    "Method process() not yet implemented.");
        }
        return result;
    }

    /**
     * doShow    ���ڷ���ҳ���ʼ�����꾡��Ϣ
     * @param vo ҵ�����
     * @return   ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
     * @throws BaseException ��������������׳��쳣��Ϣ
     */

    public Object doShow (VOPackage vo)
        throws BaseException
    {
        //�õ�Action���ݹ���ActionForm����
        GtgshyysrForm form = (GtgshyysrForm) vo.getData();
        //form.setLrrdm("1111");
        //form.setLrrq(SfTimeUtil.getDateFromDateTime(curTime));
        //form.setFsdm(CodeConstant.FSDM_SMSB);
        //�����걨����
        form.setSbrq(SfDateUtil.getDate());
        //��ǰ���ڣ�������ȥ�����Ӧ���걨����
        Map skssrq = getSkssrq(form.getSbrq());
        //ȡ��˰�������Ŀ�ʼ���ںͽ�������
        form.setSkssksrq( (String) skssrq.get("skssksrq"));
        form.setSkssjsrq( (String) skssrq.get("skssjsrq"));
        //  Map getsbjd = Skssrq.quarterSkssrq(curTime);
//    String nd = (String) getsbjd.get(Skssrq.SKSSRQ_ND);
//    form.setNd(nd);
        //��ʼ���б���ʾ���ƣ���Ϊ���ⲻ�����ݿ���ȡ[�����������]
        //form.setCjrq(TinyTools.Date2String(curTime, CodeConstant.DATETYPE));
        List dataList = GtgshHelper.getShowList();
        form.setDataList(dataList);
        form.setTempSbrq("");
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
        GtgshyysrForm form = (GtgshyysrForm) vo.getData();
        Timestamp curTime = new Timestamp(System.currentTimeMillis());
        //�������ݿ�����
        Connection conn = null;
        try
        {

            //����OrMappingֵ�����ÿ������
            String zbNames[] =
                               {
                               "jsjdm", "sbrq", "swdjzh", "qxdm", "swjgzzjgdm",
                               "skssksrq",
                               "skssjsrq", "fsdm", "nd", "nsrmc"};
            //����������ݲ�����
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
                List dataList = GtgshHelper.getShowList();
                form.setDataList(dataList);
                throw ExceptionUtil.getBaseException(ex1);
            }
            if (! (djxx.getDjzclxdm().equals(CodeConstant.GTGSH_CODE))
                && ! (djxx.getDjzclxdm().equals(CodeConstant.DJZCLXDM_GRHH)))
            {
                List dataList = GtgshHelper.getShowList();
                form.setDataList(dataList);
                throw new ApplicationException("�˼�������벻���ڸ��幤�̻���");
            }
            BeanUtil.copyBeanToBean(zbNames, djxx, form);
            form.setQxdm(InterfaceDj.getQxdm(ud));
            BeanUtil.copyBeanToBean(zbNames, djxx, form);
            form.setCjrq(TinyTools.Date2String(curTime, CodeConstant.DATETYPE));
            //form.setSwjgzzjgdm(djxx.getSwjgzzjgdm());
            //��ʼ���б���ʾ���ƣ���Ϊ���û���û����Ӧ����˰��Ϣ
            List dataList = GtgshHelper.getShowList();
            form.setDataList(dataList);
            form.setTempSbrq("");
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
        return form;
    }

    /**
     * doUpdateQuery    ���ڷ���ҳ����Ҫ��ѯ���꾡��Ϣ
     * @param     vo ҵ�����
     * @return    ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
     * @throws BaseException    ��������������׳��쳣��Ϣ
     *
     */

    public Object doUpdateQuery (VOPackage vo)
        throws BaseException
    {
        //�õ�Action���ݹ���ActionForm����
        GtgshyysrForm form = (GtgshyysrForm) vo.getData();
        Timestamp curTime = new Timestamp(System.currentTimeMillis());
        //�������ݿ�����
        Connection conn = null;
        try
        {

            //����OrMappingֵ�����ÿ������
            String zbNames[] =
                               {
                               "jsjdm", "sbrq", "swdjzh", "qxdm", "swjgzzjgdm",
                               "skssksrq",
                               "skssjsrq", "fsdm", "nd", "nsrmc"};
            //����������ݲ�����
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
                List dataList = GtgshHelper.getShowList();
                form.setDataList(dataList);
                throw ExceptionUtil.getBaseException(ex1);
            }
            if (! (djxx.getDjzclxdm().equals(CodeConstant.GTGSH_CODE)))
            {
                List dataList = GtgshHelper.getShowList();
                form.setDataList(dataList);
                throw new ApplicationException("�˼�������벻���ڸ��幤�̻���");
            }
            BeanUtil.copyBeanToBean(zbNames, djxx, form);
            //�����ݿ�
            conn = SfDBResource.getConnection();
            //ʹ��OrMap�������ݿ�ķ�װ��
            SfDBAccess da = new SfDBAccess(conn);
            //Map dateMap = getSbrl(form.getSbrq());
            //��ѯ��������
            Vector vZb = new Vector();
            //form.setQxdm(InterfaceDj.getQxdm(ud));
            vZb.add("qxdm='" + InterfaceDj.getQxdm(ud) + "'");
            vZb.add(" sbrq=to_date('" + form.getTempSbrq()
                    + "','yyyy-MM-dd hh24:mi:ss')");

            vZb.add(" jsjdm='" + form.getJsjdm() + "' ");
            //�������ݽ��
            List zbData = da.query(Gtgshyysrz.class, vZb);
            if (zbData.size() <= 0)
            {
                //���ݵǼǽӿڵõ���ǰ������������˰����Ϣ��Ȼ��ֵ��ActionForm

                if (djxx == null)
                {
                    Debug.out("�˼�������벻���ڣ�");
                    List dataList = GtgshHelper.getShowList();
                    form.setDataList(dataList);
                    throw new ApplicationException("�˼�������벻���ڣ�");
                }
                form.setQxdm(InterfaceDj.getQxdm(ud));
                BeanUtil.copyBeanToBean(zbNames, djxx, form);
                form.setCjrq(TinyTools.Date2String(curTime,
                    CodeConstant.DATETYPE));
                //form.setSwjgzzjgdm(djxx.getSwjgzzjgdm());
                //��ʼ���б���ʾ���ƣ���Ϊ���û���û����Ӧ����˰��Ϣ
                List dataList = GtgshHelper.getShowList();
                form.setDataList(dataList);
                return form;
            } //end if
            //���������ݸ���ActionForm
            Gtgshyysrz orZb = (Gtgshyysrz) zbData.get(0);
            BeanUtil.copyBeanToBean(zbNames, orZb, form);
            form.setCjrq(String.valueOf(orZb.getCjrq()));
            //form.setNsrmc(djxx.getNsrmc());
            //��ѯ��ϸ����
            //��ϸ��ѯ������װ
            Vector vMx = new Vector();
            vMx.add("qxdm='" + InterfaceDj.getQxdm(ud) + "'");
            vMx.add(" sbrq=to_date('" + form.getTempSbrq()
                    + "','yyyy-MM-dd hh24:mi:ss')");

            vMx.add(" jsjdm='" + form.getJsjdm() + "' ");
            //��ϸ��ѯ���ݽ��
            List mxData = da.query(Gtgshyysrmx.class, vMx);
            //��ϸOrMappingֵ�����ÿ������
            String mxNames[] =
                               {
                               "szsmdm", "dqkjfpje", "dqwkjfpje", "dqyysrjehj",
                               "jzyysr"};
            //��OrMappingֵ����ת��Map���Ա�ActionForm�ܹ�ʹ��
            SfHashList showList = (SfHashList) (new SfHashList(GtgshHelper.
                getShowList())).clone();
            //List mxMapData = new ArrayList();
            for (int i = 0; i < mxData.size(); i++)
            {
                //��ô�����ϸֵ
                Gtgshyysrmx orMx = (Gtgshyysrmx) mxData.get(i);
                HashMap map = new HashMap();
                //��ֵ�����ֵ����Map
                BeanUtil.copyBeanToMap(mxNames, orMx, map);
                Debug.out(orMx.getSzsmdm());
                int location = showList.findFirst(0, "szsmdm", orMx.getSzsmdm());

                //Debug.out("showList.get() = " + showList.get(i, "szsmmc"));
                map.put("szsmmc", showList.get(location, "szsmmc"));
                showList.put(location, map);
                //mxMapData.add(map);
                //Debug.out("mxMapData = " + mxMapData);
            }
            form.setSbrq(this.getFormatDate( (form.getTempSbrq().toString()).
                                            substring(0, 10)));
            form.setDataList(showList.getRecords());
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
        GtgshyysrForm form = (GtgshyysrForm) vo.getData();
        //�������ݿ�����
        Timestamp curTime = new Timestamp(System.currentTimeMillis());
        String tempSbrq = "";

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
        Connection conn = null;
        try
        {
            conn = SfDBResource.getConnection();
            //ʹ��OrMap�������ݿ�ķ�װ��
            SfDBAccess da = new SfDBAccess(conn);
            Map dateMap = getSbrl(form.getSbrq());

            Map getsbnd = Skssrq.monthSkssrq(SfDateUtil.getDate(form.getSbrq()));
            String nd = (String) getsbnd.get(Skssrq.SKSSRQ_ND);
            //����OrMappingֵ�����ÿ������
            String zbNames[] =
                               {
                               "jsjdm", "sbrq", "swjgzzjgdm", "skssksrq",
                               "skssjsrq"};
            //OrMapping����ֵ����
            Gtgshyysrz orZb = new Gtgshyysrz();
            //form.setLrrq(SfTimeUtil.getDateFromDateTime(curTime));
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
            orZb.setLrrdm(ud.getYhid());
            orZb.setSwdjzh(djxx.getSwdjzh());

            try
            {
                //�����������ݣ������ʱ���걨���ڲ�Ϊ�գ�����£��������
                if (form.getTempSbrq() != null && !form.getTempSbrq().equals(""))
                {
                    //��������ʱ���͵��걨����
                    orZb.setSbrq(Timestamp.valueOf(form.getTempSbrq()));
                    da.update(orZb);
                }
                else
                {
                    //��������ʱ���͵��걨����
                    tempSbrq = setFormatDate(form.getSbrq())
                               + (curTime.toString()).substring(10, 19);
                    orZb.setSbrq(Timestamp.valueOf(tempSbrq));
                    Debug.out("---1" + orZb.getSbrq());
                    da.insert(orZb);

                }
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

                map.put("sbrq", form.getSbrq());
                map.put("nd", nd);
                map.put("swjgzzjgdm", djxx.getSwjgzzjgdm());
                map.put("qxdm", InterfaceDj.getQxdm(ud));
                map.put("cjrq", cjrqmx);
                map.put("lrrq", curTime);
                Gtgshyysrmx orMx = new Gtgshyysrmx();
                //�����ݴ��ݸ���ϸֵ����
                BeanUtil.populate(orMx, map);
                mxData.add(orMx);
                try
                {
                    //�����������ݣ������ʱ���걨���ڲ�Ϊ�գ�����£��������
                    if (form.getTempSbrq() != null
                        && !form.getTempSbrq().equals(""))
                    {
                        //��������ʱ���͵��걨����
                        orMx.setSbrq(Timestamp.valueOf(form.getTempSbrq()));
                        da.update(orMx);
                    }
                    else
                    {
                        //��������ʱ���͵��걨����
                        tempSbrq = setFormatDate(form.getSbrq())
                                   + (curTime.toString()).substring(10, 19);
                        orMx.setSbrq(Timestamp.valueOf(tempSbrq));
                        Debug.out("---2" + orZb.getSbrq());
                        da.insert(orMx);
                    }
                }
                catch (BaseException ex4)
                {
                    throw new ApplicationException("���ݿ����ʧ�ܣ������ҹ���Ա��ϵ��");
                }

            }
            form.setTempSbrq("");
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
        return null;
    }

    /**
     * �����걨����ȡ�õ�ǰ�µ�һ������µ�һ��
     * @param curSbrq �걨����
     * @return dateMap
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
     * ȡ��˰�������Ŀ�ʼ���ںͽ�������
     * @param curSbrq �걨����
     * @return skssMap
     */
    private Map getSkssrq (String curSbrq)
    {
        //��ǰ���ڣ�������ȥ�����Ӧ���걨����
        Timestamp curTime = SfTimeUtil.getTimestamp(curSbrq);
        Map skssrq = Skssrq.monthSkssrq(curTime);
        //ȡ��˰�������Ŀ�ʼ���ںͽ�������
        String skssksrq = SfTimeUtil.getDateFromDateTime( (Timestamp) skssrq.
            get(
            Skssrq.SKSSKSRQ));
        String skssjsrq = SfTimeUtil.getDateFromDateTime( (Timestamp) skssrq.
            get(
            Skssrq.SKSSJSRQ));
        Map skssMap = new HashMap();
        skssMap.put("skssksrq", skssksrq);
        skssMap.put("skssjsrq", skssjsrq);
        return skssMap;
    }

    /**
     * ʱ��ת��������
     * �磺2008-08-08 00:00:00 ת��Ϊ20080808 00:00:00
     * ���ߣ�2008-08-08 ת��Ϊ20080808
     *
     * @param inTime ���ڻ�����ʱ���ַ���
     * @return String
     * @throws BaseException
     **/
    public static String getFormatDate (String inTime)
        throws BaseException
    {
        if (inTime == null || inTime.equals(""))
        {
            return inTime;
        }
        String result = "";
        String tempStr = inTime.substring(0, 10);
        String defStr = "";

        try
        {
            if (inTime.length() > 15)
            {
                defStr = inTime.substring(10);
            }
            java.text.SimpleDateFormat df = new java.text.SimpleDateFormat(
                "yyyyMMdd");
            result = df.format(java.sql.Date.valueOf(tempStr)) + defStr;
        }
        catch (Exception ex)
        {
            //throw ApplicationException();
        }
        return result;
    } //End of getFormatDate

    /**
     * ʱ��ת��������getFormatDate������������
     * �磺20080808 00:00:00 ת��Ϊ2008-08-08 00:00:00
     * ���ߣ�20080808 ת��Ϊ2008-08-08
     *
     * @param inTime ���ڻ�����ʱ���ַ���
     * @return String
     * @throws BaseException
     **/
    public static String setFormatDate (String inTime)
        throws BaseException
    {
        if (inTime == null || inTime.equals(""))
        {
            return inTime;
        }
        String result = "";
        try
        {
            String tempStr = inTime.substring(0, 4);
            String defStr = inTime.substring(4, 6);
            result = tempStr + "-" + defStr + "-" + inTime.substring(6);
        }
        catch (Exception ex)
        {
            //throw ApplicationException();
        }
        return result;
    } //End of setFormatDate

}
