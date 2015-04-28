/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.gtgsh.processor;

//�걨�ڲ�������
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
import com.ttsoft.bjtax.sfgl.common.util.SfTimeUtil;
//OrMappingֵ�����
import com.ttsoft.bjtax.shenbao.model.domain.Gtgshyysrz;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.sbzl.gtgsh.web.GtgshyysrCxForm;
//�걨�ڲ�������
import com.ttsoft.bjtax.smsb.util.InterfaceDj;//smsbbaselib�ṩ�Ĺ�����
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

public class GtgshyysrCxProcessor
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
     * doShow    ���ڷ���ҳ���ʼ�����꾡��Ϣ
     * @param vo ҵ�����
     * @return   ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
     * @throws BaseException ��������������׳��쳣��Ϣ
     */

    public Object doShow (VOPackage vo)
        throws BaseException
    {
        //�õ�Action���ݹ���ActionForm����
        GtgshyysrCxForm form = (GtgshyysrCxForm) vo.getData();
        //�����걨����
        try
        {
            form.setHeadSbrq( (SfDateUtil.getDate()).substring(0, 6));
            Debug.out(form.getHeadSbrq());
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
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
        GtgshyysrCxForm form = (GtgshyysrCxForm) vo.getData();
        Timestamp curTime = new Timestamp(System.currentTimeMillis());
        List dataList = new ArrayList();

        //�������ݿ�����
        Connection conn = null;
        try
        {

            //����OrMappingֵ�����ÿ������
            String names[] =
                             {
                             "jsjdm", "sbrq", "swdjzh", "swjgzzjgdm", "lrrdm",
                             "lrrq", "fsdm", "skssksrq", "skssjsrq", "nd",
                             "cjrq", "qxdm", "nsrmc"};
            //����������ݲ�����
            UserData ud = (UserData) vo.getUserData();
            SWDJJBSJ djxx = null;
            try
            {
                djxx = InterfaceDj.getJBSJ(form.getHeadJsjdm(), ud);
            }
            catch (Exception ex1)
            {
                throw ExceptionUtil.getBaseException(ex1);
            }
            //�жϸ��幤�̻�
            if (! (djxx.getDjzclxdm().equals(CodeConstant.GTGSH_CODE)
                   && ! (djxx.getDjzclxdm().equals(CodeConstant.DJZCLXDM_GRHH))))
            {
                throw new ApplicationException("�˼�������벻���ڸ��幤�̻���");
            }

            //�����ݿ�
            conn = SfDBResource.getConnection();
            //ʹ��OrMap�������ݿ�ķ�װ��
            SfDBAccess da = new SfDBAccess(conn);
            Map dateMap = getSbrl(form.getHeadSbrq() + "15");
            //��ѯ��������
            Vector vZb = new Vector();
            //form.setQxdm(InterfaceDj.getQxdm(ud));
            vZb.add("qxdm='" + InterfaceDj.getQxdm(ud) + "'");
            vZb.add(" sbrq>=to_date('" +
                    String.valueOf(dateMap.get("ksrq")).substring(0, 10) +
                    "','yyyy-MM-dd') ");
            vZb.add(" sbrq<to_date('" +
                    String.valueOf(dateMap.get("jsrq")).substring(0, 10) +
                    "','yyyy-MM-dd') ");
            vZb.add(" jsjdm='" + form.getHeadJsjdm() + "' order by sbrq desc ");

            //�������ݽ��
            List tempList = da.query(Gtgshyysrz.class, vZb);
            //Debug.out("-----templist.size="+tempList.size());
            if (tempList.size() <= 0)
            {
                throw new ApplicationException("û�з��������ļ�¼��");
            }
            //���������ݸ���ActionForm
            for (int i = 0; i < tempList.size(); i++)
            {
                //��ʽ��ÿ����¼
                Gtgshyysrz zb = (Gtgshyysrz) tempList.get(i);
                //sbjkzb.setSzdm(CodeUtils.getCodeBeanLabel("DM_SZSM",sbjkzb.getSzdm()));//˰������
                //��ֵ�����ֵ����Map
                HashMap map = new HashMap();
                try
                {
                    BeanUtil.copyBeanToMap(names, zb, map);
                }
                catch (Exception ex1)
                {
                    ex1.printStackTrace();
                    throw new ApplicationException("����ת������");
                }
                //�걨����ʹ������ʱ���ͣ�������˰������
                map.put("sbrq", (zb.getSbrq().toString()).substring(0, 19));
                map.put("nsrmc", djxx.getNsrmc());
                dataList.add(map);
            }
            //��ֵ�Ż�form����
            form.setDataList(dataList);
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

    private Object doSave (VOPackage vo)
        throws BaseException
    {
        return null;
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
        GtgshyysrCxForm form = (GtgshyysrCxForm) vo.getData();
        UserData ud = (UserData) vo.getUserData();
        Gtgshyysrz orObjz = new Gtgshyysrz();
        //�������ݿ�����
        Connection conn = null;
        try
        {
            conn = SfDBResource.getConnection();
            //ʹ��OrMap�������ݿ�ķ�װ��
            SfDBAccess da = new SfDBAccess(conn);

            //1��ɾ����ϸ������
            String strSql = "delete from sbdb.sb_jl_gtgshyysrmx " +
                            " where qxdm='" + InterfaceDj.getQxdm(ud) + "'" +
                            " and jsjdm='" + form.getTempJsjdm() + "'" +
                            " and sbrq=to_date('" + form.getTempSbrq()
                            + "','yyyy-mm-dd hh24:mi:ss')";
            try
            {
                da.updateSQL(strSql);
            }
            catch (BaseException ex1)
            {
                ex1.printStackTrace();
                throw new ApplicationException("ɾ����ϸ�����ݳ���");
            }
            Debug.out(form.getHeadSbrq() + "<>" + form.getTempJsjdm());
            //2�������������
            /**orObjz.setQxdm(InterfaceDj.getQxdm(ud));//���ش���
                   orObjz.setSbrq(Timestamp.valueOf(form.getTempSbrq()));
                   orObjz.setJsjdm(form.getTempJsjdm());
                   try {
              da.delete(orObjz);
                   }
                   catch (BaseException ex1) {
              ex1.printStackTrace();
              throw new ApplicationException("ɾ���������ݳ���");
                   }*/
            strSql = "delete from sbdb.sb_jl_gtgshyysrz " +
                     " where qxdm='" + InterfaceDj.getQxdm(ud) + "'" +
                     " and jsjdm='" + form.getTempJsjdm() + "'" +
                     " and sbrq=to_date('" + form.getTempSbrq()
                     + "','yyyy-mm-dd hh24:mi:ss')";
            try
            {
                da.updateSQL(strSql);
            }
            catch (BaseException ex1)
            {
                ex1.printStackTrace();
                throw new ApplicationException("ɾ���������ݳ���");
            }

            //���
            form.setTempJsjdm("");
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

}
