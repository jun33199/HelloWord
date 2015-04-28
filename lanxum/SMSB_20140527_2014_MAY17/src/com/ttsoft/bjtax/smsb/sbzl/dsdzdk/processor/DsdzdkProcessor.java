/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.dsdzdk.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.dj.model.YHZH;
import com.ttsoft.bjtax.sfgl.common.db.util.BeanUtil;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.util.Debug;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.sfgl.proxy.ServiceProxy;
import com.ttsoft.bjtax.shenbao.model.domain.Dsdzdkmx;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkmx;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb;
import com.ttsoft.bjtax.shenbao.model.domain.Sdwszsbhz;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.model.client.DeclareInfor;
import com.ttsoft.bjtax.smsb.sbzl.dsdzdk.web.DsdzdkForm;
import com.ttsoft.bjtax.smsb.util.EArray;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.JksUtil;
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
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description: �����걨</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class DsdzdkProcessor
    implements Processor
{
    public final static String SMSB_SJLY_LR = "0";

    public final static String SMSB_SJLY_DR = "1";

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

        Debug.out("DO ACTION =" + vo.getAction());

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
            case CodeConstant.SMSB_INPUTACTION: //¼��
                result = doInput(vo);
                break;
            case CodeConstant.SMSB_SAVEACTION:
                result = doSave(vo);
                break;
            case CodeConstant.SMSB_DELETEACTION:
                result = doDelete(vo);
                break;
                //case CodeConstant.SMSB_UPLOADACTION:
                //  result = doUpload(vo);
                //  break;
            case CodeConstant.SMSB_HZSBJKDACTION:
                result = doHzsbjkd(vo);
                break;
            case CodeConstant.SMSB_CXHZSBJKDACTION:
                result = doCxhzsbjkd(vo);
                break;
            default:
                throw new UnsupportedOperationException(
                    "�û�ִ����ϵͳ��֧�ֵĲ�����");
        }
        return result;
    }

    /**
     * doShow��ʼ������ҳ����ϢҪ��
     * @param vo ҵ�����
     * @return   ���ݶ���
     * @throws BaseException ��������������׳��쳣��Ϣ
     */

    public Object doShow (VOPackage vo)
        throws BaseException
    {

        //�õ�Action���ݹ���ActionForm����
        DsdzdkForm sdForm = (DsdzdkForm) vo.getData();

        // ��ʼ��ACTIONFORM
        initForm(sdForm);

        return sdForm;
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
        DsdzdkForm sdForm = (DsdzdkForm) vo.getData();
        sdForm.setNsrmc(""); // ��˰������
        sdForm.setQylxdh(""); // ע���ַ��ϵ�绰

        if (sdForm.getSbrq() == null)
        {
            // ��ʼ��ACTIONFORM
            initForm(sdForm);
        }
        UserData ud = (UserData) vo.getUserData();
        SWDJJBSJ djsj = null;
        try
        {
            // �����ҵ�Ǽǻ�����Ϣ
            /* start added by huxiaofeng 2005.8.16*/
            //djsj = InterfaceDj.getJBSJ(sdForm.getJsjdm(), ud);
            djsj = InterfaceDj.getJBSJ_New(sdForm.getJsjdm(), ud);
            sdForm.setNsrzt(djsj.getNsrzt());
            /* end added by huxiaofeng 2005.8.16*/

        }
        catch (Exception ex1)
        {
            throw ExceptionUtil.getBaseException(ex1);
        }

        ServiceProxy proxy = new ServiceProxy();
        boolean zg = proxy.getSdzg(sdForm.getJsjdm(),
                                   SfDateUtil.getDate(sdForm.getSbrq()));
        if (zg == false)
        {
            throw new ApplicationException("�˼�������벻�߱����۴������۴����ʸ�");
        }
        sdForm.setQxdm(InterfaceDj.getQxdm(ud));
        sdForm.setNsrmc(djsj.getNsrmc()); // ��˰������
        sdForm.setQylxdh(djsj.getJydzlxdm()); // ע���ַ��ϵ�绰
        sdForm.setSwjgzzjgdm(djsj.getSwjgzzjgdm()); // ˰�������֯��������
        //��˰��״̬
        sdForm.setNsrzt(djsj.getNsrzt());
        return sdForm;
    }

    /**
     * ¼�봦��ȡ����˰�˻�����Ϣ����ʼ��ҳ������
     * @param     vo ҵ�����
     * @return    ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
     * @throws BaseException    ��������������׳��쳣��Ϣ
     */
    public Object doInput (VOPackage vo)
        throws BaseException
    {
        //�õ�Action���ݹ���ActionForm����
        DsdzdkForm sdForm = (DsdzdkForm) vo.getData();
        UserData ud = (UserData) vo.getUserData();
        SWDJJBSJ djsj = null;
        try
        {
            // �����ҵ�Ǽǻ�����Ϣ
            /* start added by huxiaofeng 2005.8.16*/
            //djsj = InterfaceDj.getJBSJ(sdForm.getJsjdm(), ud);
            djsj = InterfaceDj.getJBSJ_New(sdForm.getJsjdm(), ud);
            sdForm.setNsrzt(djsj.getNsrzt());
            /* end added by huxiaofeng 2005.8.16*/

        }
        catch (Exception ex1)
        {
            throw ExceptionUtil.getBaseException(ex1);
        }

        sdForm.setNsrmc(djsj.getNsrmc()); // ��˰������
        sdForm.setQylxdh(djsj.getJydzlxdm()); // ע���ַ��ϵ�绰
        sdForm.setSjly(SMSB_SJLY_LR);
        sdForm.setSwjgzzjgdm(djsj.getSwjgzzjgdm()); // ˰�������֯��������
        sdForm.setQxdm(InterfaceDj.getQxdm(ud));

        EArray jsArray = new EArray();
        String tempJsStr = "";
        tempJsStr += jsArray.getArrayByCode("szsmlist", "DSDZDK_SZSM");
        tempJsStr +=
            jsArray.getMsgsByCode("szsmdm", "DSDZDK_SZSM_ALL", new ArrayList());
        sdForm.setScriptStr(tempJsStr);
        sdForm.setPzzl(CodeConstant.SMSB_PZZLDM);

        return sdForm;
    }

    /**
     * doHzsbjkd    ���ڷ���ҳ����Ҫ��ѯ���꾡��Ϣ
     * @param     vo ҵ�����
     * @return    ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
     * @throws BaseException    ��������������׳��쳣��Ϣ
     *
     */

    public Object doHzsbjkd (VOPackage vo)
        throws BaseException
    {

        DsdzdkForm sdForm = (DsdzdkForm) vo.getData();
        UserData ud = (UserData) vo.getUserData();
        SWDJJBSJ djsj = null;
        try
        {
            // �����ҵ�Ǽǻ�����Ϣ
            /* start added by huxiaofeng 2005.8.16*/
            //djsj = InterfaceDj.getJBSJ(sdForm.getJsjdm(), ud);
            djsj = InterfaceDj.getJBSJ_New(sdForm.getJsjdm(), ud);
            sdForm.setNsrzt(djsj.getNsrzt());
            /* end added by huxiaofeng 2005.8.16*/

        }
        catch (Exception ex1)
        {
            throw ExceptionUtil.getBaseException(ex1);
        }

        sdForm.setQxdm(InterfaceDj.getQxdm(ud));
        sdForm.setNsrmc(djsj.getNsrmc()); // ��˰������
        sdForm.setQylxdh(djsj.getZcdzlxdh()); // ע���ַ��ϵ�绰

        // ���ؿɳ����걨�����б�ͽɿ��б�����
        loadDataList(sdForm);

        return sdForm;
    }

    /**
     * ���������걨�ɿ����
     * @param     vo ҵ�����
     * @return    ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
     * @throws BaseException    ��������������׳��쳣��Ϣ
     */


    public Object doCxhzsbjkd (VOPackage vo)
        throws BaseException
    {

        DsdzdkForm sdForm = (DsdzdkForm) vo.getData();
        UserData ud = (UserData) vo.getUserData();
        SWDJJBSJ djsj = null;
        try
        {
            // �����ҵ�Ǽǻ�����Ϣ
            /* start added by huxiaofeng 2005.8.16*/
            //djsj = InterfaceDj.getJBSJ(sdForm.getJsjdm(), ud);
            djsj = InterfaceDj.getJBSJ_New(sdForm.getJsjdm(), ud);
            sdForm.setNsrzt(djsj.getNsrzt());
            /* end added by huxiaofeng 2005.8.16*/

        }
        catch (Exception ex1)
        {
            throw ExceptionUtil.getBaseException(ex1);
        }
        sdForm.setNsrmc(djsj.getNsrmc()); // ��˰������
        sdForm.setQylxdh(djsj.getZcdzlxdh()); // ע���ַ��ϵ�绰
        sdForm.setQxdm(InterfaceDj.getQxdm(ud));

        Connection conn = null;
        SfDBAccess dbAgent = null;

        try
        {
            conn = SfDBResource.getConnection();
            dbAgent = new SfDBAccess(conn);

            //ɾ���걨�ɿ�����
            List pzList = getJkpzList(dbAgent, sdForm.getQxdm(),
                                      sdForm.getJsjdm(),
                                      sdForm.getSbhzdh());
            Iterator orItems = pzList.iterator();
            while (orItems.hasNext())
            {
                Sdwszsbhz orItem = (Sdwszsbhz) orItems.next();

                String strWhere = " QXDM='" + sdForm.getQxdm() + "'" +
                                  " AND JSJDM='" + orItem.getJsjdm() + "'" +
                                  " AND JKPZH='" + orItem.getJkpzh() + "'";
                //ɾ����ϸ
                dbAgent.delete(strWhere, new Sbjkmx());
                //ɾ������
                dbAgent.delete(strWhere, new Sbjkzb());
            }

            String strWhere = " QXDM='" + sdForm.getQxdm() + "'" +
                              " AND JSJDM='" + sdForm.getJsjdm() + "'" +
                              " AND SBHZDH='" + sdForm.getSbhzdh() + "'";
            //ɾ��������ϸ����
            dbAgent.delete(strWhere, new Dsdzdkmx());

            //ɾ��������������
            dbAgent.delete(strWhere, new Sdwszsbhz());

            // ���¼��ؿɳ����걨�����б�ͽɿ��б�����
            sdForm.setSbhzdh("");
            loadDataList(sdForm);
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
        return sdForm;
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

        DsdzdkForm sdForm = (DsdzdkForm) vo.getData();

        Connection conn = null;
        SfDBAccess dbAgent = null;

        try
        {
            conn = SfDBResource.getConnection();
            dbAgent = new SfDBAccess(conn);

            //ɾ���걨�ɿ�����
            List pzList = getJkpzList(dbAgent, sdForm.getQxdm(),
                                      sdForm.getJsjdm(),
                                      sdForm.getSbhzdh());
            Iterator orItems = pzList.iterator();
            while (orItems.hasNext())
            {
                Sdwszsbhz orItem = (Sdwszsbhz) orItems.next();

                String strWhere = " QXDM='" + sdForm.getQxdm() + "'" +
                                  " AND JSJDM='" + orItem.getJsjdm() + "'" +
                                  " AND JKPZH='" + orItem.getJkpzh() + "'";
                //ɾ����ϸ
                dbAgent.delete(strWhere, new Sbjkmx());
                //ɾ������
                dbAgent.delete(strWhere, new Sbjkzb());
            }

            String strWhere = " QXDM='" + sdForm.getQxdm() + "'" +
                              " AND JSJDM='" + sdForm.getJsjdm() + "'" +
                              " AND SBHZDH='" + sdForm.getSbhzdh() + "'";
            //ɾ��������ϸ����
            dbAgent.delete(strWhere, new Dsdzdkmx());

            //ɾ��������������
            dbAgent.delete(strWhere, new Sdwszsbhz());

            // ��������걨�����б�ͽɿ��б�����
            sdForm.setSbhzdh("");
            sdForm.getHzdDataList().clear();
            sdForm.getJkpzDataList().clear();
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
        return sdForm;
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
        DsdzdkForm sdForm = (DsdzdkForm) vo.getData();
        UserData ud = (UserData) vo.getUserData();

        //======== �����ҵ�Ǽǻ�����Ϣ ========================
        SWDJJBSJ djsj = null;
        YHZH yhzh = null;
        try
        {
            /* start added by huxiaofeng 2005.8.16*/
            //djsj = InterfaceDj.getJBSJ(sdForm.getJsjdm(), ud);
            djsj = InterfaceDj.getJBSJ_New(sdForm.getJsjdm(), ud);
            /* end added by huxiaofeng 2005.8.16*/

            List zhList = InterfaceDj.getYHZH(sdForm.getJsjdm(), ud);
//Update  Start  Zhou kejing 20031211
//      if(zhList != null && zhList.size() > 0){
//         yhzh = (YHZH) zhList.get(0);
//      }
            for (int i = 0; i < zhList.size(); i++)
            {
                yhzh = (YHZH) zhList.get(i);
                if (yhzh.getJbzhbs().equals(CodeConstant.SMSB_JBZHBS))
                {
                    sdForm.setYhdm(yhzh.getYhdm()); //���д���
                    sdForm.setYhmc(yhzh.getKhyhmc()); //��������
                    sdForm.setZh(yhzh.getZh()); //�ʻ�
                    break;
                }
            }
//Update  End    Zhou kejing 20031211
        }
        catch (Exception ex1)
        {
            throw ExceptionUtil.getBaseException(ex1);
        }
        sdForm.setQxdm(InterfaceDj.getQxdm(ud));
        sdForm.setNsrmc(djsj.getNsrmc()); // ��˰������
        sdForm.setQylxdh(djsj.getJydzlxdm()); // ע���ַ��ϵ�绰
        sdForm.setDjzclxdm(djsj.getDjzclxdm()); // �Ǽ�ע�����ʹ���
        sdForm.setSwjgzzjgdm(djsj.getSwjgzzjgdm()); //˰�������֯��������
        sdForm.setGjbzhydm(djsj.getGjbzhydm()); //���ұ�׼��ҵ����
        sdForm.setLsgxdm(djsj.getLsgxdm()); //������ϵ����
        sdForm.setHzrq(SfDateUtil.getDate());
        //sdForm.setSjly(SMSB_SJLY_DR);
        sdForm.setClbjdm(CodeConstant.CLBJDM_YSB);
        sdForm.setFsdm(CodeConstant.FSDM_SMSB); // �����걨��ʽ
        sdForm.setNd(getSbnd(sdForm.getSbrq()));
        sdForm.setLrrq(TinyTools.Date2String(new Date(), CodeConstant.DATETYPE));
        if (sdForm.getCjrq() == null || "".equals(sdForm.getCjrq()))
        {
            sdForm.setCjrq(TinyTools.Date2String(new Date(),
                                                 CodeConstant.DATETYPE));
        }

//Delete  Start  Zhou kejing 20031211
//    if (yhzh != null) {
//      sdForm.setYhdm(yhzh.getYhdm()); //���д���
//      sdForm.setYhmc(yhzh.getKhyhmc()); //��������
//      sdForm.setZh(yhzh.getZh()); // �˺�
//    }
//Delete  End    Zhou kejing 20031211

        //�����걨���
        //sdForm.setSbbh(JksUtil.getSbbh(sdForm.getJsjdm()));

        Connection conn = null;
        SfDBAccess dbAgent = null;

        try
        {
            conn = SfDBResource.getConnection();
            dbAgent = new SfDBAccess(conn);
            List jkpzInfoList;

            //���ɻ��ܵ���
            JksUtil jks = new JksUtil();
            sdForm.setSbhzdh(jks.getSequenceOfSbhzd(conn));

            //======== ��˰�֡�˰Ŀ��������ϸ���� ==================
            HashMap szsmMxMap = groupMxBySzsm(sdForm);

            // ���ɽɿ�ƾ֤
            jkpzInfoList = (List) createSbjkpz(sdForm, szsmMxMap);

            // ȡ���걨���
            DeclareInfor info = (DeclareInfor) jkpzInfoList.get(0);
            Sbjkzb zbInfo = info.getSbjkzb();
            sdForm.setSbbh(zbInfo.getSbbh());

            // ����������������
            createSdwszsbhz(sdForm, jkpzInfoList, dbAgent);

            // ����������ϸ����
            createDsdzdkmx(sdForm, szsmMxMap, dbAgent);

            //װ�ػ��ܵ�
            sdForm.getHzdDataList().clear();
            sdForm.getHzdDataList().add(
                loadSbhz(dbAgent, sdForm.getQxdm(), sdForm.getJsjdm(),
                         sdForm.getSbhzdh()));

            //װ�ػ���ƾ֤
            sdForm.getJkpzDataList().clear();
            sdForm.getJkpzDataList().addAll(
                loadJkpz(dbAgent, sdForm.getQxdm(), sdForm.getJsjdm(),
                         sdForm.getSbhzdh()));

            //�����ϸ�б�
            sdForm.getMxDataList().clear();
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
        return sdForm;
    }

    /**
     * ����ɿ���ϸ��Ϣ�����ؽɿ������Ϣ
     * @param sdForm ��ǰActionForm����
     * @param sdmxMap ��˰�֡�˰Ŀ�����������ϸ����
     * @return ���ɵĽɿ�ƾ֤�б�
     * @throws BaseException
     */
    private Object createSbjkpz (DsdzdkForm sdForm, HashMap sdmxMap)
        throws
        BaseException
    {

        // �ɿ���ϸ��Ϣ�б�
        ArrayList jkmxList = new ArrayList();

        Timestamp ts_cjrq = new Timestamp(TinyTools.stringToDate(sdForm.getCjrq(),
            CodeConstant.DATETYPE).getTime());
        Timestamp ts_lrrq = new Timestamp(TinyTools.stringToDate(sdForm.getLrrq(),
            CodeConstant.DATETYPE).getTime());

        try
        {
            Iterator szEntrys = sdmxMap.entrySet().iterator();
            while (szEntrys.hasNext())
            {
                Map.Entry szEntry = (Map.Entry) szEntrys.next();
                // ˰�ִ���
                String szdm = (String) szEntry.getKey();
                // ˰������˰Ŀ
                Iterator smEntrys = ( (HashMap) szEntry.getValue()).entrySet().
                                    iterator();
                while (smEntrys.hasNext())
                {

                    //=========== ��ͬһ˰�֡�˰Ŀ����ϸ���ݼ���ϼ� =============
                    // ��˰���ϼ�
                    BigDecimal jsjeTotal = new BigDecimal(0.00);
                    // ʵ��˰��ϼ�
                    BigDecimal sjseTotal = new BigDecimal(0.00);

                    Map.Entry smEntry = (Map.Entry) smEntrys.next();
                    // ˰Ŀ����
                    String smdm = (String) smEntry.getKey();
                    // ˰Ŀ������ϸ��Ϣ
                    Iterator sdmxItems = ( (List) smEntry.getValue()).iterator();
                    while (sdmxItems.hasNext())
                    {
                        Dsdzdkmx item = (Dsdzdkmx) sdmxItems.next();
                        if (item.getJsje() != null)
                        {
                            jsjeTotal = jsjeTotal.add(item.getJsje());
                        }
                        if (item.getSjse() != null)
                        {
                            sjseTotal = sjseTotal.add(item.getSjse());
                        }
                    }

                    // ================= ���ɽɿ���ϸ	 =======================
                    String[] jkmx_columns =
                                            {
                                            "qxdm", "nd", "jsjdm", "skssksrq",
                                            "skssjsrq", "swjgzzjgdm",
                                            "sbbh"};

                    // ��Form��columsָ�������Կ������ɿ���ϸOR������
                    Sbjkmx orSbjkmx = new Sbjkmx();
                    BeanUtil.copyBeanToBean(jkmx_columns, sdForm, orSbjkmx);

                    orSbjkmx.setSzdm(szdm);
                    orSbjkmx.setSzsmdm(smdm);
                    orSbjkmx.setJsje(jsjeTotal);
                    orSbjkmx.setSjse(sjseTotal);
                    orSbjkmx.setCjrq(ts_cjrq);
                    orSbjkmx.setLrrq(ts_lrrq);
                    // �ѽɿ���ϸ��¼�ӵ��ɿ��б���
                    jkmxList.add(orSbjkmx);
                }
            }

            // ================ ���ɽɿ�������Ϣ ========================
            String[] jkzb_columns =
                                    {
                                    "qxdm", "jsjdm", "djzclxdm", "fsdm",
                                    "sbbh", "gjbzhydm", "lrr", "lsgxdm", "sbrq",
                                    "nd", "swjgzzjgdm", "yhdm", "yhmc", "zh"};

            HashMap attribMap = new HashMap();
            BeanUtil.copyBeanToMap(jkzb_columns, sdForm, attribMap);
            attribMap.put("zsswjgzzjgdm", sdForm.getSwjgzzjgdm());
            attribMap.put("jydzlxdm", sdForm.getQylxdh());
            attribMap.put("sjly", CodeConstant.SMSB_SJLY_SDHZ);
            attribMap.put("sklxdm", CodeConstant.SKLXDM_SDHZ);
            Sbjkzb orSbjkzb = new Sbjkzb();
            BeanUtil.populate(orSbjkzb, attribMap);
            orSbjkzb.setCjrq(ts_cjrq);
            orSbjkzb.setLrrq(ts_lrrq);


            //�����ۺ��걨��д�ɿ��������ϸ
            JksUtil jks = new JksUtil();
            return jks.getJkDataSD(orSbjkzb, jkmxList, CodeConstant.PRINT_YPYS);
        }
        catch (Exception ex1)
        {
            // ϵͳ�޷����ɽɿ�ƾ֤
            ex1.printStackTrace();
            throw ExceptionUtil.getBaseException(ex1);
        }
    }

    /**
     * ���������ɿ�ƾ֤��������������Ϣ
     * @param sdForm ��ǰActionForm����
     * @param jkpzInfoList �ɿ�ƾ֤�б�
     * @param dbAgent ���ݿ��ȡ����
     * @throws BaseException
     */
    private void createSdwszsbhz (DsdzdkForm sdForm, List jkpzInfoList,
                                  SfDBAccess dbAgent)
        throws BaseException
    {
        Debug.out(" Creating Sdwszsbhz data................");

        Timestamp ts_cjrq = new Timestamp(TinyTools.stringToDate(sdForm.getCjrq(),
            CodeConstant.DATETYPE).getTime());
        Timestamp ts_lrrq = new Timestamp(TinyTools.stringToDate(sdForm.getLrrq(),
            CodeConstant.DATETYPE).getTime());

        try
        {
            Iterator items = jkpzInfoList.iterator();
            while (items.hasNext())
            {
                DeclareInfor item = (DeclareInfor) items.next();
                Sbjkzb orSbjkzb = item.getSbjkzb();

                HashMap attribMap = new HashMap();
                String[] hz_columns =
                                      {
                                      "qxdm", "jsjdm", "sbhzdh", "sbbh", "nd",
                                      "hzrq", "hzksrq",
                                      "hzjsrq", "clbjdm", "swjgzzjgdm", "lrr",
                                      "sjly"};
                BeanUtil.copyBeanToMap(hz_columns, sdForm, attribMap);
                attribMap.put("jkpzh", orSbjkzb.getJkpzh());
                attribMap.put("sjse", orSbjkzb.getSjje());
                // ����������ݵ��������ܱ�
                Sdwszsbhz orSdwszsbhz = new Sdwszsbhz();
                BeanUtil.populate(orSdwszsbhz, attribMap);
                orSdwszsbhz.setCjrq(ts_cjrq);
                orSdwszsbhz.setLrrq(ts_lrrq);
                dbAgent.insert(orSdwszsbhz);
            }
        }
        catch (Exception ex1)
        {
            // ϵͳ�޷����ɻ�������
            ex1.printStackTrace();
            throw ExceptionUtil.getBaseException(ex1);
        }
    }

    /**
     * ���������ɿ�ƾ֤��������������Ϣ
     * @param sdForm ��ǰActionForm����
     * @param szsmMap �ɿ�ƾ֤�б�
     * @param dbAgent ���ݿ��ȡ����
     * @throws BaseException
     */
    private void createDsdzdkmx (DsdzdkForm sdForm, HashMap szsmMap,
                                 SfDBAccess dbAgent)
        throws BaseException
    {

        try
        {
            // ˰����Ŀ
            Iterator szItems = szsmMap.values().iterator();
            while (szItems.hasNext())
            {
                //˰Ŀ��Ŀ
                Iterator smItems = ( (HashMap) szItems.next()).values().
                                   iterator();
                while (smItems.hasNext())
                {
                    //ͬһ˰�֡�˰Ŀ�µ�������ϸ��¼
                    Iterator mxRecords = ( (List) smItems.next()).iterator();
                    while (mxRecords.hasNext())
                    {
                        Dsdzdkmx mxRecord = (Dsdzdkmx) mxRecords.next();
                        mxRecord.setSbhzdh(sdForm.getSbhzdh());
                        dbAgent.insert(mxRecord);
                    }
                }
            }
        }
        catch (Exception ex1)
        {
            // ϵͳ�޷�������ϸ���ݣ�������������ݲ������������ظ�����ͬһ����
            ex1.printStackTrace();
            String errMsg = ex1.getMessage();
            if (errMsg.indexOf("��¼�Ѿ�����") > 0)
            {
                throw new ApplicationException("�ü������˰��˰Ŀ�Ѿ����ܹ��ˣ�");
            }
            else
            {
                throw ExceptionUtil.getBaseException(ex1);
            }
        }
    }

    /**
     * ����˰�֡�˰Ŀ�Ե������ݷ���
     * @param sdForm ������ϸOR�����б�
     * @return HashMap ��˰�֡�˰Ŀ�����OR�����б�
     * @throws BaseException
     */
    private HashMap groupMxBySzsm (DsdzdkForm sdForm)
        throws BaseException
    {

        Timestamp ts_cjrq = new Timestamp(TinyTools.stringToDate(sdForm.getCjrq(),
            CodeConstant.DATETYPE).getTime());
        Timestamp ts_lrrq = new Timestamp(TinyTools.stringToDate(sdForm.getLrrq(),
            CodeConstant.DATETYPE).getTime());

        // ������ϸ����������
        String[] mx_columns =
                              {
                              "qxdm", "jsjdm", "qylxdh", "nd", "sjly",
                              "fsdm", "skssksrq", "skssjsrq", "swjgzzjgdm",
                              "lrr"};

        // ��˰�ִ�ŵ�������ϸOR�����б�
        HashMap szMap = new HashMap();
        try
        {
            Iterator mxItems = sdForm.getMxDataList().iterator();
            while (mxItems.hasNext())
            {
                HashMap attribMap = (HashMap) mxItems.next();
                BeanUtil.copyBeanToMap(mx_columns, sdForm, attribMap);

                Dsdzdkmx mxItem = new Dsdzdkmx();
                BeanUtil.populate(mxItem, attribMap);
                mxItem.setCjrq(ts_cjrq);
                mxItem.setLrrq(ts_lrrq);

                if (szMap.containsKey(mxItem.getSzdm()))
                {
                    // ��˰Ŀ��ŵ�������ϸOR�����б�
                    HashMap smMap = (HashMap) szMap.get(mxItem.getSzdm());
                    if (smMap.containsKey(mxItem.getSzsmdm()))
                    {
                        List mxList = (List) smMap.get(mxItem.getSzsmdm());
                        mxList.add(mxItem);
                    }
                    else
                    {
                        ArrayList mxList = new ArrayList();
                        mxList.add(mxItem);
                        smMap.put(mxItem.getSzsmdm(), mxList);
                    }
                }
                else
                {
                    ArrayList mxList = new ArrayList();
                    mxList.add(mxItem);

                    HashMap smMap = new HashMap();
                    smMap.put(mxItem.getSzsmdm(), mxList);

                    szMap.put(mxItem.getSzdm(), smMap);
                }
            }
        }
        catch (Exception ex1)
        {
            // ϵͳ�޷�����ϸ�������򱣴���ϸ���ݣ�������������ݲ�����
            ex1.printStackTrace();
            throw ExceptionUtil.getBaseException(ex1);
        }
        return szMap;
    }

    /**
     * ����ϼƽ��
     * @param sdForm ��ǰ��ActionForm
     * @param mxList ��HashMapΪ�ڵ����ϸ��¼List
     * @throws BaseException
     */
    private void sum (List mxList, DsdzdkForm sdForm)
        throws BaseException
    {
        //��˰���ϼ�
        BigDecimal jsjeTotal = new BigDecimal(0.00);
        //ʵ�ɽ��ϼ�
        BigDecimal sjseTotal = new BigDecimal(0.00);

        for (int i = 0; i < mxList.size(); i++)
        {
            HashMap record = (HashMap) mxList.get(i);

            String jsje = (String) record.get("jsje"); //��˰���
            String sjse = (String) record.get("sjse"); //ʵ��˰��

            BigDecimal thisSjse = new BigDecimal(sjse);
            thisSjse = thisSjse.setScale(2, BigDecimal.ROUND_HALF_UP);
            sjseTotal = sjseTotal.add(thisSjse);

            BigDecimal thisJsje = new BigDecimal(jsje);
            thisJsje = thisJsje.setScale(2, BigDecimal.ROUND_HALF_UP);
            jsjeTotal = jsjeTotal.add(thisJsje);
        }

        sdForm.setJsjehj(jsjeTotal);
        sdForm.setSjsehj(sjseTotal);
    }

    /**
     * �����걨���ܵ��б��ɿ�ƾ֤�б�
     * @param sdForm ��������
     * @throws BaseException
     */
    private void loadDataList (DsdzdkForm sdForm)
        throws BaseException
    {

        Connection conn = null;
        SfDBAccess dbAgent = null;

        try
        {
            conn = SfDBResource.getConnection();
            dbAgent = new SfDBAccess(conn);

            //========================= ���ҿɳ������ܵ�======================
            // �����Ѿ��нɿ��¼���걨��ţ���ҳ���ڻ��߽ɿ����ڲ�Ϊ��.
            // ���ĳһ���걨����µ����ݣ����ڽɿ��¼�������Ƿ�ȫ���ɿ
            // ���������޸�ά�������˵�����������
//      String subQuery = " SELECT DISTINCT SBBH " +
//          " FROM SBDB.SB_JL_SBJKZB " +
//          " WHERE QXDM='" + sdForm.getQxdm() + "'" +
//          " AND FSDM='" + CodeConstant.FSDM_SMSB + "'" +
//          " AND substr(zwbs,1,1)='" + CodeConstant.SMSB_ZWBS + "'" +
//          " AND substr(zwbs,20,1)='" + CodeConstant.SMSB_ZWBS20 + "'"+
//          " AND JSJDM='" + sdForm.getJsjdm() + "'";
//      String subQuery = " SELECT DISTINCT SBBH " +
//         " FROM SBDB.SB_JL_SBJKZB " +
//         " WHERE QXDM='" + sdForm.getQxdm() + "'" +
//         " AND FSDM='" + CodeConstant.FSDM_SMSB + "'" +
//         " AND zwbs like  '"+CodeConstant.SMSB_ZWBS+"%"+CodeConstant.SMSB_ZWBS+"'"+
//         " AND JSJDM='" + sdForm.getJsjdm() + "'";
            String subQuery = " SELECT DISTINCT SBBH " +
                              " FROM SBDB.SB_JL_SBJKZB " +
                              " WHERE QXDM='" + sdForm.getQxdm() + "'" +
                              " AND zwbs like  '" + CodeConstant.SMSB_ZWBS
                              + "%" + CodeConstant.SMSB_ZWBS + "'" +
                              " AND JSJDM='" + sdForm.getJsjdm() + "'";

            Vector vMx = new Vector();
            vMx.add(" QXDM='" + sdForm.getQxdm() + "'");
            vMx.add(" JSJDM='" + sdForm.getJsjdm() + "' ");
            vMx.add(" SBBH IN (" + subQuery + ")");
            vMx.add(" (CLBJDM='" + CodeConstant.CLBJDM_WCL +
                    "' OR CLBJDM='" + CodeConstant.CLBJDM_YSB + "') ");
            Debug.out("HZ SQL=" + vMx.toString());

            // �������ͳ�ƽ�����м��
            HashMap tmpListMap = new HashMap();

            List hzdList = dbAgent.query(Sdwszsbhz.class, vMx);
            if (hzdList.size() > 0)
            {

                Iterator hzdItems = hzdList.iterator();
                while (hzdItems.hasNext())
                {
                    Sdwszsbhz hzdItem = (Sdwszsbhz) hzdItems.next();
                    // ���õ�ǰ�걨���ܱ��, Ĭ��Ϊ��һ��
                    if (sdForm.getSbhzdh() == null
                        || "".equals(sdForm.getSbhzdh()))
                    {
                        sdForm.setSbhzdh(hzdItem.getSbhzdh());
                    }
                    if (!tmpListMap.containsKey(hzdItem.getSbhzdh()))
                    {
                        tmpListMap.put(hzdItem.getSbhzdh(),
                                       loadSbhz(dbAgent, sdForm.getQxdm(),
                                                sdForm.getJsjdm(),
                                                hzdItem.getSbhzdh()));
                    }
                }
                sdForm.getHzdDataList().addAll(tmpListMap.values());

                //=================== ���ҵ�ǰ���ܵ���Ӧ�Ŀɳ�������ƾ֤====================
                sdForm.getJkpzDataList().addAll(loadJkpz(dbAgent,
                    sdForm.getQxdm(),
                    sdForm.getJsjdm(),
                    sdForm.getSbhzdh()));
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
    }

    /**
     * ���ҵ�ǰ���ܵ�ƾ֤����
     * @param dbAgent ���ݿ�����
     * @param qxdm ���ش���
     * @param jsjdm ���������
     * @param sbhzdh �걨���ܵ���
     * @return ��������
     * @throws BaseException
     */
    private HashMap loadSbhz (SfDBAccess dbAgent, String qxdm, String jsjdm,
                              String sbhzdh)
        throws
        BaseException
    {

        HashMap hzMap = new HashMap();
        try
        {
            //��������
            java.sql.Timestamp hzrq = new Timestamp(System.currentTimeMillis());
            //ƾ֤����
            int jkpzs = 0;
            //ʵ��˰��ϼ�
            BigDecimal sjseTotal = new BigDecimal(0.00);

            String[] hzd_columns =
                                   {
                                   "hzrq", "sbhzdh", "sjse", "jkpzs"};

            //================ ����ͬһ���ܵ����ɵĽɿ�ƾ֤ ====================
            Vector vMx = new Vector();
            vMx.add(" QXDM='" + qxdm + "'");
            vMx.add(" JSJDM='" + jsjdm + "' ");
            vMx.add(" SBHZDH='" + sbhzdh + "'");

            // �������ͳ�ƽ�����м��
            HashMap tmpListMap = new HashMap();

            List hzdList = dbAgent.query(Sdwszsbhz.class, vMx);
            Iterator hzdItems = hzdList.iterator();
            while (hzdItems.hasNext())
            {
                Sdwszsbhz hzdItem = (Sdwszsbhz) hzdItems.next();

                sjseTotal = sjseTotal.add(hzdItem.getSjse());
                hzrq = hzdItem.getHzrq();
                jkpzs = jkpzs + 1;
            }

            hzMap.put("sbhzdh", sbhzdh);
            hzMap.put("hzrq",
                      DateTimeUtil.timestampToString(hzrq,
                DateTimeUtil.JAVA_DATEFORMAT));
            hzMap.put("jkpzs", String.valueOf(jkpzs));
            hzMap.put("sjse", sjseTotal.toString());

        }
        catch (Exception ex)
        {
            //�׳��쳣
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
        return hzMap;
    }

    /**
     * ���ҵ�ǰ���ܵ���Ӧ�Ŀɳ�������ƾ֤,�洢��ActionForm��
     * @param dbAgent ���ݿ�����
     * @param qxdm ���ش���
     * @param jsjdm ���������
     * @param sbhzdh �걨���ܵ���
     * @return ��������
     * @throws BaseException
     */
    private List loadJkpz (SfDBAccess dbAgent, String qxdm, String jsjdm,
                           String sbhzdh)
        throws
        BaseException
    {

        List mapList = new ArrayList();
        String[] pz_columns =
                              {
                              "sbhzdh", "jkpzh", "sjse"};

        try
        {
            List orList = getJkpzList(dbAgent, qxdm, jsjdm, sbhzdh);
            Iterator orItems = orList.iterator();
            while (orItems.hasNext())
            {
                Sdwszsbhz orItem = (Sdwszsbhz) orItems.next();

                HashMap pzMap = new HashMap();
                BeanUtil.copyBeanToMap(pz_columns, orItem, pzMap);
                mapList.add(pzMap);
            }
        }
        catch (Exception ex)
        {
            //�׳��쳣
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
        return mapList;
    }
/**
 * ���ݼ���������ȡ�ýɿ����б�
 * @param dbAgent ���ݿ�����
 * @param qxdm ���ش���
 * @param jsjdm ���������
 * @param sbhzdh �걨���ܵ���
 * @return �ɿ����б�
 * @throws BaseException
 */
    private List getJkpzList (SfDBAccess dbAgent, String qxdm, String jsjdm,
                              String sbhzdh)
        throws
        BaseException
    {

        // ���ص�ǰ�걨���ܵ����µ�ƾ֤��������
        Vector vJk = new Vector();
        vJk.add(" QXDM='" + qxdm + "'");
        vJk.add(" JSJDM='" + jsjdm + "'");
        vJk.add(" SBHZDH='" + sbhzdh + "'");
        Debug.out("JKPZ LIST SQL=" + vJk.toString());

        List pzList = dbAgent.query(Sdwszsbhz.class, vJk);

        return pzList;
    }

    /**
     * �����걨���
     * @param sbrq �걨����
     * @return �걨���
     */
    private String getSbnd (String sbrq)
    {

        Map qj = Skssrq.yearSkssrq(SfDateUtil.getDate(sbrq));
        return (String) qj.get(Skssrq.SKSSRQ_ND);
    }

    /**
     * �����������롢������ص�����
     * @param sdForm ��������
     * @throws BaseException
     */
    private void initForm (DsdzdkForm sdForm)
        throws BaseException
    {

        sdForm.setClbjdm(CodeConstant.CLBJDM_YSB); // ���걨

        sdForm.setSbrq(SfDateUtil.getDate());
        sdForm.setHzrq(sdForm.getSbrq());
        sdForm.setHzksrq(sdForm.getHzrq());
        sdForm.setHzjsrq(sdForm.getHzrq());
        sdForm.setCjrq(TinyTools.Date2String(new Date(), CodeConstant.DATETYPE));
        sdForm.setLrrq(TinyTools.Date2String(new Date(), CodeConstant.DATETYPE));
        sdForm.setSjly(SMSB_SJLY_DR);
        sdForm.setFsdm(CodeConstant.FSDM_SMSB);

        // �걨�ڼ�
        Map qj = Skssrq.yearSkssrq(SfDateUtil.getDate(sdForm.getSbrq()));
        try
        {
            String ksrq = DateTimeUtil.timestampToString(
                (Timestamp) qj.get(Skssrq.SKSSKSRQ),
                DateTimeUtil.JAVA_DATEFORMAT);

            String jsrq = DateTimeUtil.timestampToString(
                (Timestamp) qj.get(Skssrq.SKSSJSRQ),
                DateTimeUtil.JAVA_DATEFORMAT);

            sdForm.setSkssksrq(ksrq);
            sdForm.setSkssjsrq(jsrq);
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }

    }

}
