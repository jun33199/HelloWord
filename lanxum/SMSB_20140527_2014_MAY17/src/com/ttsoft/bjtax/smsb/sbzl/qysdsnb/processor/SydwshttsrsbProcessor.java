/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsnb.processor;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;
import java.util.List;
import java.util.Vector;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.Iterator;
//import com.ekernel.db.or.*;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.framework.util.DateTimeUtil;

import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.db.util.BeanUtil;

import com.ttsoft.bjtax.sfgl.proxy.ServiceProxy;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.common.model.Zsfs;

import com.ttsoft.bjtax.shenbao.model.domain.Sydwshttsrzb;
import com.ttsoft.bjtax.shenbao.model.domain.Sydwshttsrmx;
import com.ttsoft.bjtax.shenbao.model.domain.Sydwshttwh;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb.web.SydwshttsrsbForm;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.Skssrq;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.bjtax.smsb.util.EArray;
import com.ttsoft.bjtax.sfgl.common.util.Debug;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.common.model.UserData;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description:��ҵ��λ</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class SydwshttsrsbProcessor
    implements Processor
{

    public SydwshttsrsbProcessor ()
    {}

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

        // SMSB_SHOWACTION = 0;
        // SMSB_SAVEACTION = 1;
        // SMSB_DELETEACTION = 2;
        // SMSB_QUERYACTION = 3;
        // SMSB_UPDATEACTION = 4;

        Object result = null;
        Debug.out("action===>" + vo.getAction());
        if (vo == null)
        {
            throw new NullPointerException();
        }

        switch (vo.getAction())
        {
            case CodeConstant.SMSB_SHOWACTION:
                result = doShow(vo);
                break;
            case CodeConstant.SMSB_QUERYACTION:
                result = doQuery(vo);
                break;
            case CodeConstant.SMSB_SAVEACTION:
                result = doSave(vo);
                break;
            case CodeConstant.SMSB_DELETEACTION:
                result = doDelete(vo);
                break;
            case CodeConstant.SMSB_UPDATEACTION:
                result = doUpdate(vo);
                break;
            default:
                throw new ApplicationException(
                    "�û�ִ����ϵͳ��֧�ֵķ�������.");
        }

        return result;
    }

    /**
     * doShow��ʼ������ҳ����ϢҪ��
     * @param vo ҵ�����
     * @return   ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
     * @throws BaseException ��������������׳��쳣��Ϣ
     */

    private Object doShow (VOPackage vo)
        throws BaseException
    {

        SydwshttsrsbForm sbForm = (SydwshttsrsbForm) vo.getData();

        // ��ʼ��FORM
        initForm(sbForm);

        return sbForm;
    }

    /**
     * doQuery    ���ڷ���ҳ����Ҫ��ѯ���꾡��Ϣ
     * @param     vo ҵ�����
     * @return    ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
     * @throws BaseException    ��������������׳��쳣��Ϣ
     *
     */

    private Object doQuery (VOPackage vo)
        throws BaseException
    {

        SydwshttsrsbForm sbForm = (SydwshttsrsbForm) vo.getData();
        sbForm.setNsrmc(""); // ��˰������
        sbForm.setZcdzlxdh(""); // ע���ַ��ϵ�绰

        Connection conn = null;
        SfDBAccess dbAgent = null;

        try
        {
            conn = SfDBResource.getConnection();
            dbAgent = new SfDBAccess(conn);
            UserData ud = (UserData) vo.getUserData();
            SWDJJBSJ djsj = null;
            try
            {
                // �����ҵ�Ǽǻ�����Ϣ
                /* start added by huxiaofeng 2005.8.16*/
                //djsj = InterfaceDj.getJBSJ(sbForm.getJsjdm(), ud);
                djsj = InterfaceDj.getJBSJ_New(sbForm.getJsjdm(), ud);
                sbForm.setNsrzt(djsj.getNsrzt());
                /* end added by huxiaofeng 2005.8.16*/

            }
            catch (Exception ex1)
            {
                throw ExceptionUtil.getBaseException(ex1);
            }

            if (! (djsj.getDjzclxdm().equals(CodeConstant.DJZCLXDM_SYDW) ||
                   djsj.getDjzclxdm().equals(CodeConstant.DJZCLXDM_SHTT)))
            {
                sbForm.setJsjdm("");
                throw new ApplicationException("���걨��ֻ��������ҵ��λ���������");
            }
            sbForm.setQxdm(InterfaceDj.getQxdm(ud));
            sbForm.setNsrmc(djsj.getNsrmc()); // ��˰������
            sbForm.setZcdzlxdh(djsj.getZcdzlxdh()); // ע���ַ��ϵ�绰
            sbForm.setDjzclxdm(djsj.getDjzclxdm()); // �Ǽ�ע�����ʹ���
            sbForm.setSwjgzzjgdm(djsj.getSwjgzzjgdm()); //˰�������֯��������
            sbForm.setFsdm(CodeConstant.FSDM_SMSB); // �����걨��ʽ
            sbForm.setNd(getSbnd(sbForm.getSbrq()));
            sbForm.setCjrq(TinyTools.Date2String(new Date(),
                                                 CodeConstant.DATETYPE));
            sbForm.setLrrq(TinyTools.Date2String(new Date(),
                                                 CodeConstant.DATETYPE));
            sbForm.setFsdm(CodeConstant.FSDM_SMSB);

            //��ѯ��������
            Vector vZb = new Vector();
            vZb.add(" jsjdm='" + sbForm.getJsjdm() + "' ");
            vZb.add(" qxdm='" + sbForm.getQxdm() + "' ");
            vZb.add(" nd='" + sbForm.getNd() + "'");
            //�������ݽ��
            List zbData = dbAgent.query(Sydwshttsrzb.class, vZb);
            //�������������
            if (zbData.size() > 0)
            {
                // ȡ�������걨���ݵ��걨����
                Sydwshttsrzb srzb = (Sydwshttsrzb) zbData.get(0);
                HashMap zbMap = new HashMap();
                String[] zb_columns =
                                      {"fsdm", "sbrq", "skssksrq", "skssjsrq"};
                BeanUtil.copyBeanToMap(zb_columns, srzb, zbMap);
                sbForm.setSfyyssr(srzb.getSfyyssr());
                sbForm.setFsdm( (String) zbMap.get("fsdm"));
                sbForm.setSbrq( (String) zbMap.get("sbrq"));
                sbForm.setSkssksrq( (String) zbMap.get("skssksrq"));
                sbForm.setSkssjsrq( (String) zbMap.get("skssjsrq"));
                sbForm.setLrrq(TinyTools.Date2String(srzb.getLrrq(),
                    CodeConstant.DATETYPE));
                sbForm.setCjrq(TinyTools.Date2String(srzb.getCjrq(),
                    CodeConstant.DATETYPE));

                // �����Ƿ������걨����
                Vector vMx = new Vector();
                vMx.add(" ND='" + sbForm.getNd() + "'");
                vMx.add(" qxdm='" + sbForm.getQxdm() + "' ");
                vMx.add(" JSJDM='" + sbForm.getJsjdm() + "'" +
                        " order by  to_number(HC) ASC ");

                // ��ҵ��λ��ϸ����
                String mx_columns[] =
                                      {"hc", "bqljs"};
                List mxData = dbAgent.query(Sydwshttsrmx.class, vMx);

                List mxResult = new ArrayList();
                Iterator mxit = mxData.iterator();
                while (mxit.hasNext())
                {
                    Sydwshttsrmx item = (Sydwshttsrmx) mxit.next();
                    HashMap record = new HashMap();
                    BeanUtil.copyBeanToMap(mx_columns, item, record);
                    mxResult.add(record);
                }

                // ��ҵ��λ�ĺű���
                String wh_columns[] =
                                      {"hc", "wh"};
                List whData = dbAgent.query(Sydwshttwh.class, vMx);
                Iterator whit = whData.iterator();
                while (whit.hasNext())
                {
                    Sydwshttwh whitem = (Sydwshttwh) whit.next();
                    HashMap record = new HashMap();
                    BeanUtil.copyBeanToMap(wh_columns, whitem, record);

                    Object wh = record.get("wh");
                    record.remove("wh");
                    record.put("bqljs", wh);

                    mxResult.add(record);
                }

                sbForm.setDataList(mxResult);
            }
        }
        catch (Exception ex)
        {
            //�׳��쳣
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
        finally
        {
            SfDBResource.freeConnection(conn);
        }

        return sbForm;
    }

    /**
     * doSave   ���ڴ洢ҳ���ύ���꾡������Ϣ
     * @param   vo ҵ�����
     * @return  ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]
     * @throws BaseException ��������������׳��쳣��Ϣ
     */

    private Object doSave (VOPackage vo)
        throws BaseException
    {

        SydwshttsrsbForm sbForm = (SydwshttsrsbForm) vo.getData();

        Connection conn = null;
        SfDBAccess dbAgent = null;
        List mxResult = new ArrayList();

        try
        {
            conn = SfDBResource.getConnection();
            dbAgent = new SfDBAccess(conn);

            //============ ����׼������==========================

            sbForm.setFsdm(CodeConstant.FSDM_SMSB); // �걨�걨��ʽ
            sbForm.setNd(getSbnd(sbForm.getSbrq()));
            sbForm.setLrrq(TinyTools.Date2String(new Date(),
                                                 CodeConstant.DATETYPE));
            if (sbForm.getCjrq() == null || "".equals(sbForm.getCjrq()))
            {
                sbForm.setCjrq(TinyTools.Date2String(new Date(),
                    CodeConstant.DATETYPE));
            }
            Timestamp ts_cjrq = new Timestamp(TinyTools.stringToDate(sbForm.
                getCjrq(), CodeConstant.DATETYPE).getTime());
            Timestamp ts_lrrq = new Timestamp(TinyTools.stringToDate(sbForm.
                getLrrq(), CodeConstant.DATETYPE).getTime());

            String strWhere = " jsjdm='" + sbForm.getJsjdm() + "'" +
                              " and qxdm='" + sbForm.getQxdm() + "'" +
                              " and nd='" + sbForm.getNd() + "'";
            try
            {
                //ɾ���ĺ���ϸ����
                dbAgent.delete(strWhere, new Sydwshttwh()); // ɾ�����ĺ�����
            }
            catch (BaseException ex1)
            {
                throw new ApplicationException("���ݿ�ɾ������ʧ�ܣ�");
            }
            try
            {
                //ɾ���ĺ���ϸ����
                dbAgent.delete(strWhere, new Sydwshttsrmx()); // ɾ������ϸ
            }
            catch (BaseException ex1)
            {
                throw new ApplicationException("���ݿ�ɾ������ʧ�ܣ�");
            }
            try
            {
                //ɾ����������
                dbAgent.delete(strWhere, new Sydwshttsrzb()); // ɾ��������¼
            }
            catch (BaseException ex1)
            {
                throw new ApplicationException("���ݿ�ɾ������ʧ�ܣ�");
            }

            //============= ����������Ϣ���� ========================
            String zb_columns[] =
                                  {
                                  "nd", "jsjdm", "sbrq", "lrr",
                                  "skssksrq", "skssjsrq", "swjgzzjgdm", "fsdm",
                                  "qxdm", "djzclxdm", "sfyyssr"};

            Sydwshttsrzb orZb = new Sydwshttsrzb();
            HashMap zbMap = new HashMap();
            BeanUtil.copyBeanToMap(zb_columns, sbForm, zbMap);
            Debug.out(zbMap);
            BeanUtil.populate(orZb, zbMap);
            orZb.setCjrq(ts_cjrq);
            orZb.setLrrq(ts_lrrq);
            dbAgent.insert(orZb); // �����µ��걨����

            //============ ������ϸ����Ϣ���� ==========================
            String mx_columns[] =
                                  {"qxdm", "nd", "jsjdm", "swjgzzjgdm"};

            mxResult = sbForm.getDataList();
            for (int i = 0; i < mxResult.size(); i++)
            {
                HashMap sbData = (HashMap) mxResult.get(i);
                Integer hcIndex = new Integer( (String) sbData.get("hc"));
                if (hcIndex.intValue() <= 17)
                {
                    // �����걨��ϸ����
                    BeanUtil.copyBeanToMap(mx_columns, sbForm, sbData);

                    Sydwshttsrmx orMx = new Sydwshttsrmx();
                    BeanUtil.populate(orMx, sbData);
                    orMx.setCjrq(ts_cjrq);
                    orMx.setLrrq(ts_lrrq);
                    dbAgent.insert(orMx); // �����µ��걨��ϸ��Ϣ
                }
                else
                {
                    // �����ĺ�����
                    BeanUtil.copyBeanToMap(mx_columns, sbForm, sbData);
                    // ���д�17��ʼ���б����ۼ��д洢�ĺ�����
                    // ת���ĺ�ֵ���ֶ�����
                    HashMap whRecord = new HashMap(sbData);
                    Object whValue = whRecord.get("bqljs");
                    if (whValue != null)
                    {
                        String wh = (String) whValue;
                        if (!"".equals(wh.trim()))
                        {
                            whRecord.remove("bqljs");
                            whRecord.put("wh", whValue);

                            Sydwshttwh orWh = new Sydwshttwh();
                            BeanUtil.populate(orWh, whRecord);
                            orWh.setCjrq(ts_cjrq);
                            orWh.setLrrq(ts_lrrq);
                            dbAgent.insert(orWh); // �����µ��ĺ�����
                        }
                    }
                }

            }
        }
        catch (Exception ex)
        {
            //�׳��쳣
            ex.printStackTrace();
            throw new ApplicationException("���ݿ�������ʧ��");
        }
        finally
        {
            SfDBResource.freeConnection(conn);
        }

        return sbForm;
    }

    /**
     * doDelete  ���ڴ洢ҳ���ύ���꾡������Ϣ
     * @param    vo ҵ�����
     * @return   ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]
     * @throws BaseException ��������������׳��쳣��Ϣ
     */

    private Object doDelete (VOPackage vo)
        throws BaseException
    {

        SydwshttsrsbForm sbForm = (SydwshttsrsbForm) vo.getData();

        Connection conn = null;
        SfDBAccess dbAgent = null;

        try
        {
            conn = SfDBResource.getConnection();
            dbAgent = new SfDBAccess(conn);

            sbForm.setNd(getSbnd(sbForm.getSbrq()));

            String strWhere = " jsjdm='" + sbForm.getJsjdm() + "'" +
                              " and qxdm='" + sbForm.getQxdm() + "'" +
                              " and nd='" + sbForm.getNd() + "'";
            try
            {
                //ɾ���ĺ���ϸ����
                dbAgent.delete(strWhere, new Sydwshttwh()); // ɾ�����ĺ�����
            }
            catch (BaseException ex1)
            {
                throw new ApplicationException("���ݿ�ɾ������ʧ�ܣ�");
            }
            try
            {
                //ɾ���ĺ���ϸ����
                dbAgent.delete(strWhere, new Sydwshttsrmx()); // ɾ������ϸ
            }
            catch (BaseException ex1)
            {
                throw new ApplicationException("���ݿ�ɾ������ʧ�ܣ�");
            }
            try
            {
                //ɾ����������
                dbAgent.delete(strWhere, new Sydwshttsrzb()); // ɾ��������¼
            }
            catch (BaseException ex1)
            {
                throw new ApplicationException("���ݿ�ɾ������ʧ�ܣ�");
            }

            // ��������ȴʡ����
            initForm(sbForm);
            // ����Ѽ�������
            sbForm.getDataList().clear();
        }
        catch (Exception ex)
        {
            //�׳��쳣
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
        finally
        {
            SfDBResource.freeConnection(conn);
        }
        return sbForm;
    }

    /**
     * doUpdate  ���ڴ洢ҳ���ύ���꾡������Ϣ
     * @param    vo ҵ�����
     * @return   ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]
     * @throws BaseException ��������������׳��쳣��Ϣ
     */

    private Object doUpdate (VOPackage vo)
        throws BaseException
    {

        SydwshttsrsbForm sbForm = (SydwshttsrsbForm) vo.getData();

        return sbForm;
    }

    /**
     * ��ʼ��
     * @param sbForm ��������
     * @throws BaseException ��������������׳��쳣��Ϣ
     */

    private void initForm (SydwshttsrsbForm sbForm)
        throws BaseException
    {

        sbForm.setDjzclxdm(""); // �Ǽ�ע�����ʹ���
        sbForm.setSfyyssr(CodeConstant.YSSR_TRUE); // Ӧ˰����

        sbForm.setSbrq(SfDateUtil.getDate());
        sbForm.setCjrq(TinyTools.Date2String(new Date(), CodeConstant.DATETYPE));
        sbForm.setLrrq(TinyTools.Date2String(new Date(), CodeConstant.DATETYPE));
        // �걨�ڼ�
        Map qj = Skssrq.yearSkssrq(SfDateUtil.getDate(sbForm.getSbrq()));
        try
        {
            String ksrq = DateTimeUtil.timestampToString(
                (Timestamp) qj.get(Skssrq.SKSSKSRQ),
                DateTimeUtil.JAVA_DATEFORMAT);

            String jsrq = DateTimeUtil.timestampToString(
                (Timestamp) qj.get(Skssrq.SKSSJSRQ),
                DateTimeUtil.JAVA_DATEFORMAT);

            sbForm.setSkssksrq(ksrq);
            sbForm.setSkssjsrq(jsrq);
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    /**
     * �����걨���
     * @param   sbrq �걨����
     * @return String ���
     */

    private String getSbnd (String sbrq)
    {

        Map qj = Skssrq.yearSkssrq(SfDateUtil.getDate(sbrq));
        return (String) qj.get(Skssrq.SKSSRQ_ND);
    }

}
