/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsnb.processor;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;//import com.ekernel.db.or.*;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.common.db.util.BeanUtil;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.util.Debug;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.shenbao.model.domain.Qyjbcwzb;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb.web.QyjbcwzbForm;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.Skssrq;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
//import com.ekernel.db.or.*;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.DateTimeUtil;
import com.ttsoft.framework.util.VOPackage;


/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description: ��ҵ��������ָ��</p>
 * @author Shi Yanfeng
 * @version 1.1
 */


public class QyjbcwzbProcessor
    implements Processor
{

    public QyjbcwzbProcessor ()
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
        Debug.out("QYJBCWZB action===>" + vo.getAction());
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

        QyjbcwzbForm zbForm = (QyjbcwzbForm) vo.getData();

        // ���������걨�ڼ䡢�걨����
        initForm(zbForm);

        return zbForm;
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

        QyjbcwzbForm zbForm = (QyjbcwzbForm) vo.getData();
        zbForm.setNsrmc(""); // ��˰������
        zbForm.setZcdzlxdh(""); // ע���ַ��ϵ�绰

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
                //djsj = InterfaceDj.getJBSJ(zbForm.getJsjdm(), ud);
                djsj = InterfaceDj.getJBSJ_New(zbForm.getJsjdm(), ud);
                zbForm.setNsrzt(djsj.getNsrzt());
                /* end added by huxiaofeng 2005.8.16*/

            }
            catch (Exception ex1)
            {
                throw ExceptionUtil.getBaseException(ex1);
            }
            zbForm.setFsdm(CodeConstant.FSDM_SMSB);
            zbForm.setQxdm(InterfaceDj.getQxdm(ud));
            zbForm.setNsrmc(djsj.getNsrmc()); // ��˰������
            zbForm.setZcdzlxdh(djsj.getZcdzlxdh()); // ע���ַ��ϵ�绰
            zbForm.setDjzclxdm(djsj.getDjzclxdm()); // �Ǽ�ע�����ʹ���
            zbForm.setSwjgzzjgdm(djsj.getSwjgzzjgdm());
            zbForm.setNd(getSbnd(zbForm.getSbrq()));

            zbForm.setCjrq(TinyTools.Date2String(new Date(),
                                                 CodeConstant.DATETYPE));
            zbForm.setLrrq(TinyTools.Date2String(new Date(),
                                                 CodeConstant.DATETYPE));

            // �����Ƿ������걨����
            Vector vMx = new Vector();
            vMx.add(" ND='" + zbForm.getNd() + "'" +
                    " and QXDM='" + zbForm.getQxdm() + "'" +
                    " and JSJDM='" + zbForm.getJsjdm() + "'" +
                    " order by  to_number(HC) ASC ");

            Debug.out("SELECT SQL= ND='" + zbForm.getNd() + "'" +
                      " and QXDM='" + zbForm.getQxdm() + "'" +
                      " and JSJDM='" + zbForm.getJsjdm() + "'" +
                      " order by  to_number(HC) ASC ");

            String zb_columns[] =
                                  {"fsdm", "sbrq", "skssksrq", "skssjsrq"};
            String mx_columns[] =
                                  {"hc", "ncs", "nms"};
            List mxData = dbAgent.query(Qyjbcwzb.class, vMx);
            if (mxData.size() > 0)
            {
                Qyjbcwzb orData = (Qyjbcwzb) mxData.get(0);
                HashMap zbMap = new HashMap();
                BeanUtil.copyBeanToMap(zb_columns, orData, zbMap);
                zbForm.setFsdm( (String) zbMap.get("fsdm"));
                zbForm.setSbrq( (String) zbMap.get("sbrq"));
                zbForm.setSkssksrq( (String) zbMap.get("skssksrq"));
                zbForm.setSkssjsrq( (String) zbMap.get("skssjsrq"));
                zbForm.setLrrq(TinyTools.Date2String(orData.getLrrq(),
                    CodeConstant.DATETYPE));
                zbForm.setCjrq(TinyTools.Date2String(orData.getCjrq(),
                    CodeConstant.DATETYPE));

                List mxResult = new ArrayList();
                Iterator mxit = mxData.iterator();
                while (mxit.hasNext())
                {
                    Qyjbcwzb item = (Qyjbcwzb) mxit.next();
                    HashMap record = new HashMap();
                    BeanUtil.copyBeanToMap(mx_columns, item, record);
                    mxResult.add(record);
                }
                zbForm.setDataList(mxResult);
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

        return zbForm;
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

        QyjbcwzbForm zbForm = (QyjbcwzbForm) vo.getData();

        Connection conn = null;
        SfDBAccess dbAgent = null;
        List mxResult = new ArrayList();

        try
        {
            conn = SfDBResource.getConnection();
            dbAgent = new SfDBAccess(conn);

            //============ ����׼������==========================
            zbForm.setFsdm(CodeConstant.FSDM_SMSB);
            //zbForm.setLrrq(SfDateUtil.getDate());
            //zbForm.setLrr("unknown"); //TODO:��Ҫ���ݵ�½�û�ȷ��¼����
            zbForm.setNd(getSbnd(zbForm.getSbrq()));
            zbForm.setLrrq(TinyTools.Date2String(new Date(),
                                                 CodeConstant.DATETYPE));
            if (zbForm.getCjrq() == null || "".equals(zbForm.getCjrq()))
            {
                zbForm.setCjrq(TinyTools.Date2String(new Date(),
                    CodeConstant.DATETYPE));
            }

            String strWhere = " jsjdm='" + zbForm.getJsjdm() + "'" +
                              " and qxdm='" + zbForm.getQxdm() + "'" +
                              " and nd='" + zbForm.getNd() + "'";
            try
            {
                //ɾ����������
                dbAgent.delete(strWhere, new Qyjbcwzb()); // ɾ��������¼
            }
            catch (BaseException ex1)
            {
                throw new ApplicationException("����ɾ������");
            }

            Timestamp ts_cjrq = new Timestamp(TinyTools.stringToDate(zbForm.
                getCjrq(), CodeConstant.DATETYPE).getTime());
            Timestamp ts_lrrq = new Timestamp(TinyTools.stringToDate(zbForm.
                getLrrq(), CodeConstant.DATETYPE).getTime());

            //============= ������ҵ��������ָ������ ========================
            String zb_columns[] =
                                  {
                                  "qxdm", "nd", "jsjdm", "sbrq", "lrr",
                                  "skssksrq", "skssjsrq", "swjgzzjgdm", "fsdm"};

            mxResult = zbForm.getDataList();
            for (int i = 0; i < mxResult.size(); i++)
            {
                HashMap zbData = (HashMap) mxResult.get(i);

                // ������ϸ����
                BeanUtil.copyBeanToMap(zb_columns, zbForm, zbData);

                Qyjbcwzb orMx = new Qyjbcwzb();
                BeanUtil.populate(orMx, zbData);
                orMx.setCjrq(ts_cjrq);
                orMx.setLrrq(ts_lrrq);
                dbAgent.insert(orMx); // �����µ��걨��ϸ��Ϣ

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

        return zbForm;
    }

    /**
     * doDelete  ����ɾ��ҳ���ύ���꾡������Ϣ
     * @param    vo ҵ�����
     * @return   ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]
     * @throws BaseException ��������������׳��쳣��Ϣ
     */

    private Object doDelete (VOPackage vo)
        throws BaseException
    {

        QyjbcwzbForm zbForm = (QyjbcwzbForm) vo.getData();

        Connection conn = null;
        SfDBAccess dbAgent = null;

        try
        {
            conn = SfDBResource.getConnection();
            dbAgent = new SfDBAccess(conn);

            zbForm.setNd(getSbnd(zbForm.getSbrq()));
            String strWhere = " jsjdm='" + zbForm.getJsjdm() + "'" +
                              " and qxdm='" + zbForm.getQxdm() + "'" +
                              " and nd='" + zbForm.getNd() + "'";
            try
            {
                //ɾ����������
                dbAgent.delete(strWhere, new Qyjbcwzb()); // ɾ��������¼
            }
            catch (BaseException ex1)
            {
                throw new ApplicationException("����ɾ������");
            }

            // �������ó�ʼ������
            initForm(zbForm);
            // ����Ѽ�������
            zbForm.getDataList().clear();
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
        return zbForm;
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

        QyjbcwzbForm zbForm = (QyjbcwzbForm) vo.getData();

        return zbForm;
    }

    /**
     * ��ʼ��
     * @param zbForm ��������
     * @throws BaseException ��������������׳��쳣��Ϣ
     */
    private void initForm (QyjbcwzbForm zbForm)
        throws BaseException
    {

        zbForm.setSbrq(SfDateUtil.getDate());
        zbForm.setLrrq(TinyTools.Date2String(new Date(), CodeConstant.DATETYPE));
        zbForm.setCjrq(TinyTools.Date2String(new Date(), CodeConstant.DATETYPE));

        // �걨�ڼ�
        Map qj = Skssrq.yearSkssrq(SfDateUtil.getDate(zbForm.getSbrq()));
        try
        {
            String ksrq = DateTimeUtil.timestampToString(
                (Timestamp) qj.get(Skssrq.SKSSKSRQ),
                DateTimeUtil.JAVA_DATEFORMAT);

            String jsrq = DateTimeUtil.timestampToString(
                (Timestamp) qj.get(Skssrq.SKSSJSRQ),
                DateTimeUtil.JAVA_DATEFORMAT);

            zbForm.setSkssksrq(ksrq);
            zbForm.setSkssjsrq(jsrq);
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
