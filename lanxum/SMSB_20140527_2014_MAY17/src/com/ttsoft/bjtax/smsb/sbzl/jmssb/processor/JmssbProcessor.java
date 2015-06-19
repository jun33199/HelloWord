/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.jmssb.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector; //�Ǽǽӿڽ����

//�Ǽǽӿڽ����
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.jikuai.zwcl.inf.JKAdapter;
import com.ttsoft.bjtax.sfgl.common.code.CodeManager;
import com.ttsoft.bjtax.sfgl.common.constant.CodeConstants;
import com.ttsoft.bjtax.sfgl.common.db.util.BeanUtil;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource; //smsbbaselib�ṩ�Ĺ�����
//smsbbaselib�ṩ�Ĺ�����
import com.ttsoft.bjtax.sfgl.common.util.Debug;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.sfgl.common.util.SfStringUtils;
import com.ttsoft.bjtax.sfgl.common.util.SfTimeUtil;
import com.ttsoft.bjtax.sfgl.proxy.ServiceProxy;
//OrMappingֵ�����
import com.ttsoft.bjtax.shenbao.model.domain.Jm;
import com.ttsoft.bjtax.shenbao.model.domain.Szsm;
import com.ttsoft.bjtax.shenbao.model.domain.Zqrl;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.sbzl.jmssb.web.JmssbForm;
import com.ttsoft.bjtax.smsb.util.EArray;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.InterfaceSf4Sb;
import com.ttsoft.bjtax.smsb.util.JksUtil;
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
 * <p>Description: �����м���˰�걨</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class JmssbProcessor
    implements Processor
{

    /**
     * ҳ��ʹ�ã�����js����
     */

    private EArray jsArray = new EArray();

    /**
     * �жϱ�ʾ
     */

    private String ok = "";

    /**
     * �жϱ�ʾ
     */
    private String oks = "";

    /**
     * �к�
     */
    private int bhghc = 0;

    /**
     * ���߱������ʸ���ֵı�־��
     */
    private int time = 0;

    /**
     * ���ؽ�����б�
     */
    private List ret = new ArrayList();

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
                result = doSave(vo);
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
        JmssbForm form = (JmssbForm) vo.getData();
        String tempJsStr = jsArray.getArrayByCode("szsmlist_add",
                                                  "ZHSB_SZSMADD");
        tempJsStr += jsArray.getArrayByCode("szsmlist", "JM_SZSMDM");
        tempJsStr +=
            jsArray.getMsgsByCode("szsmdm", "JM_SZSM_ALL", new ArrayList());
        tempJsStr +=jsArray.getMsgsByCode("jmxmjdm", "JM_JMXMJDM",new ArrayList());//add by tangchangfu 2014-05-02 ��˰����˰��Ŀ����
        form.setScriptStr(tempJsStr);
        form.setSbrq(SfDateUtil.getDate());

        return form;
    }

    /**
     * doQuery    ���ڷ���ҳ����Ҫ��ѯ���꾡��Ϣ
     * @param     vo ҵ�����
     * @return    ���ݶ���
     * @throws BaseException    ��������������׳��쳣��Ϣ
     */
    public Object doQuery (VOPackage vo)
        throws BaseException
    {
        //�õ�Action���ݹ���ActionForm����
        JmssbForm form = (JmssbForm) vo.getData();
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
            throw ExceptionUtil.getBaseException(ex1);
        }
        EArray jsArray = new EArray();
        String tempJsStr = jsArray.getArrayByCode("szsmlist_add",
                                                  "ZHSB_SZSMADD");
        tempJsStr +=jsArray.getMsgsByCode("jmxmjdm", "JM_JMXMJDM",new ArrayList());//add by tangchangfu 2014-05-02 ��˰����˰��Ŀ 
        Timestamp curTime = new Timestamp(System.currentTimeMillis());
        //�������ݿ�����
        Connection conn = null;
        try
        {
            conn = SfDBResource.getConnection();
            //ʹ��OrMap�������ݿ�ķ�װ��
            SfDBAccess da = new SfDBAccess(conn);
            Map zqrlMap = this.getSksssqMap(djxx.getDjzclxdm(),
                                            SfDateUtil.getDate(form.getSbrq()),
                                            conn);
            Map ynsjeMap = InterfaceSf4Sb.getYnsje(form.getJsjdm(),
                SfDateUtil.getDate(form.getSbrq()));
            List sjList = new ArrayList();
            sjList = this.getJmDate(ynsjeMap, zqrlMap, form);
            tempJsStr += this.getMxJsArray(sjList);
            Map dateMap = getSbrl(form.getSbrq());
            //��ѯ��������
            Vector vZb = new Vector();
            //����OrMappingֵ�����ÿ������
            //modify by tangchangfu 2014-04-04 ��˰����˰��Ŀ  �����ֶ� "dqxse","dqlrze","qyrs","azrs"
            String zbNames[] =
                {
                "jsjdm", "sbrq", "swjgzzjgdm", "fsdm", "jzbz",
                "djzclxdm", "gjbzhydm", "nd", "nsrmc", "qxdm","dqxse","dqlrze","qyrs","azrs"};
            BeanUtil.copyBeanToBean(zbNames, djxx, form);
            tempJsStr += jsArray.getArrayByCode("szsmlist", "JM_SZSMDM");
            //����������ݲ�����
            vZb.add("qxdm='" + InterfaceDj.getQxdm(ud) + "'");
            vZb.add("jzbz like '" + CodeConstant.SMSB_JZBZ_EDIT + "%" + "'");
//            vZb.add("szsmdm!='" + CodeConstant.SZSM_QYSDSCODE + "'");  delete by tangchangfu 2014-04-04 ��˰����˰��Ŀ����
            vZb.add(" (FSDM='" + CodeConstant.FSDM_WSSB +
                    "' OR FSDM='" + CodeConstant.FSDM_SMSB + "') ");
            vZb.add("sbrq >= to_date('" +
                    String.valueOf(dateMap.get("ksrq")).substring(0, 10) +
                    "','yyyy-MM-dd')");
            vZb.add("sbrq < to_date('" +
                    String.valueOf(dateMap.get("jsrq")).substring(0, 10) +
                    "','yyyy-MM-dd')");
            vZb.add("jsjdm='" + form.getJsjdm() + "' ");
            //�������ݽ��
            List zbData = da.query(Jm.class, vZb);
            if (zbData.size() <= 0)
            {
                //���ݵǼǽӿڵõ���ǰ������������˰����Ϣ��Ȼ��ֵ��ActionForm

                BeanUtil.copyBeanToBean(zbNames, djxx, form);
                //��ʼ���б���ʾ���ƣ���Ϊ���û���û����Ӧ����˰��Ϣ
                tempJsStr +=
                    jsArray.getMsgsByCode("szsmdm", "JM_SZSM_ALL",
                                          new ArrayList());
                form.setScriptStr(tempJsStr);
                form.setCjrq(TinyTools.Date2String(curTime,
                    CodeConstant.DATETYPE));
                return form;
            } //end if
            form.setNsrmc(djxx.getNsrmc());
            //���������ݸ���ActionForm
            Jm orZb = (Jm) zbData.get(0);
            BeanUtil.copyBeanToBean(zbNames, orZb, form);
            form.setCjrq(String.valueOf(orZb.getCjrq()));
            //��ϸ������������һ��
            //��ϸOrMappingֵ�����ÿ������
            //modify by tangchangfu 2014-04-04 ��˰����˰��Ŀ  �����ֶ� "jmxmjdm","jmxmksrq","jmxmjsrq"
            String mxNames[] =
                {
                "szsmdm", "jsje", "kssl", "jmse", "jmxmdm",
                "yskmdm", "ysjcdm",
                "skssjsrq", "skssksrq", "jmlx","jmxmjdm","jmxmksrq","jmxmjsrq"};
            //��OrMappingֵ����ת��Map���Ա�ActionForm�ܹ�ʹ��
            ArrayList mxMapData = new ArrayList();
            ResultSet rs = null;//����ּܾ���˰�������� add by tangchangfu 2014-05-02
            for (int i = 0; i < zbData.size(); i++)
            {
                //��ô�����ϸֵ
                Jm orMx = (Jm) zbData.get(i);
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
                
                //����ּܾ���˰�������� add by tangchangfu 2014-05-02
                String jmslxmcQuerySQL = "select jmslxdm||'-'||jmslxmc descr from dmdb.sb_dm_zjqyjmslx where jmslxdm='"+(String) map.get("jmxmjdm")+"'";
                rs = da.querySQL(jmslxmcQuerySQL);
                if(rs.next()){
                	map.put("jmxmjdm", rs.getString("descr"));
                } //ADD END
                
                mxMapData.add(map);
                
               
            }
            rs.close();//����ּܾ���˰�������� add by tangchangfu 2014-05-02
            
            form.setDataList(mxMapData);
            form.setCjrq(form.getCjrq());
            form.setLrrq(SfTimeUtil.getDateFromDateTime(curTime));
            tempJsStr += jsArray.getArrayByCode("szsmlist", "JM_SZSMDM");
            tempJsStr +=
                jsArray.getMsgsByCode("szsmdm", "JM_SZSM_ALL", form.getDataList());
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
     * @param   vo ҵ�����
     * @return  ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]
     * @throws BaseException ��������������׳��쳣��Ϣ
     */

    public Object doSave (VOPackage vo)
        throws BaseException
    {
        //�õ�Action���ݹ���ActionForm����
        JmssbForm form = (JmssbForm) vo.getData();
        UserData ud = (UserData) vo.getUserData();
        SWDJJBSJ djxx = null;
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
        String waringStr = "�ü������������д�ĵ�";
        String tempJsStr = jsArray.getArrayByCode("szsmlist_add",
                                                  "ZHSB_SZSMADD");
        tempJsStr += jsArray.getArrayByCode("szsmlist", "JM_SZSMDM");
        tempJsStr +=
            jsArray.getMsgsByCode("szsmdm", "JM_SZSM_ALL", form.getDataList());
        tempJsStr +=jsArray.getMsgsByCode("jmxmjdm", "JM_JMXMJDM",new ArrayList());//add by tangchangfu 2014-05-02 ��˰����˰��Ŀ����
        form.setScriptStr(tempJsStr);
        String zbName[] =
            {
            "jsjdm", "sbrq", "swdjzh", "swjgzzjgdm",
            "djzclxdm", "gjbzhydm", "lrr", "qxdm"};

        //�������ݿ�����
        Connection conn = null;
        try
        {
            conn = SfDBResource.getConnection();
            //ʹ��OrMap�������ݿ�ķ�װ��
            SfDBAccess da = new SfDBAccess(conn);
            Map zqrlMap = this.getSksssqMap(djxx.getDjzclxdm(),
                                            SfDateUtil.getDate(form.getSbrq()),
                                            conn);
            Map ynsjeMap = InterfaceSf4Sb.getYnsje(form.getJsjdm(),
                SfDateUtil.getDate(form.getSbrq()));
            ServiceProxy proxy = new ServiceProxy();
            BeanUtil.copyBeanToBean(zbName, djxx, form);
            ArrayList mxMapDatas = form.getDataList();
            List mxDatas = new ArrayList();
            for (int i = 0; i < mxMapDatas.size(); i++)
            {
                Map map = (Map) mxMapDatas.get(i);
                String szsmdm = (String) map.get("szsmdm");
                String smdm = szsmdm.substring(0, 2);
                String jmlxs = (String) map.get("jmlx");
                String ts_skssksrq = (String) map.get("skssksrq");
                String ts_skssjsrq = (String) map.get("skssjsrq");

                Timestamp skssksrq = new Timestamp(TinyTools.stringToDate(
                    ts_skssksrq,
                    "yyyyMMdd").getTime());
                Timestamp skssjsrq = new Timestamp(TinyTools.stringToDate(
                    ts_skssjsrq,
                    "yyyyMMdd").getTime());

                if (jmlxs.equals(CodeConstant.JMLX_SP) &&
                    !smdm.equals(CodeConstant.SZSM_YYS)
                    && !this.isYysFjs(szsmdm))
                {
//modified by zhu guanglin for:ֻ�з�Ӫҵ˰��������ʸ� 2004-03-09
//modified by Shi Yanfeng : ��¼���˰ĿΪӪҵ˰����˰��ʱ��Ҳ��������Ƿ��м����ʸ� 2004-03-26
                    Date date = TinyTools.stringToDate(form.getSbrq(),
                        "yyyyMMdd");
                    String jmxmdm = proxy.getJmsbs(form.getJsjdm(), smdm,
                        skssksrq,
                        skssjsrq);
                    if (jmxmdm != null)
                    {
                        ok = "true";
                        map.put("jmxmdm", jmxmdm);
                        com.ttsoft.bjtax.smsb.model.client.Ysjc ysjc = JksUtil.
                            getYsjc(form.
                                    getJsjdm(), smdm,
                                    SfDateUtil.getDate(form.getSbrq()));
                        map.put("ysjcdm", ysjc.getYsjcdm());
                        com.ttsoft.bjtax.jikuai.zwcl.vo.page.Yskm yskm =
                            JKAdapter.
                            getInstance().getYskm(szsmdm,
                                                  djxx.getDjzclxdm(),
                                                  djxx.getGjbzhydm(),
                                                  ysjc.getYsjcdm());
                        map.put("yskmdm", yskm.getYskmdm());

                    }
                    else
                    {
                        bhghc = i + 1;
                        ok = "false";
                        if (time == 0)
                        {
                            waringStr += String.valueOf(bhghc);
                        }
                        else
                        {
                            waringStr += "��" + String.valueOf(bhghc);
                        }
                        time = time + 1;
                    }
                }
                else
                {
                    oks = "true";
                    com.ttsoft.bjtax.smsb.model.client.Ysjc ysjc = JksUtil.
                        getYsjc(form.
                                getJsjdm(), smdm,
                                SfDateUtil.getDate(form.getSbrq()));

                    map.put("ysjcdm", ysjc.getYsjcdm());
                    String jmxmdm = CodeConstant.JM_CY_JMXMDM;
                    map.put("jmxmdm", jmxmdm);
                    com.ttsoft.bjtax.jikuai.zwcl.vo.page.Yskm yskm = JKAdapter.
                        getInstance().getYskm(szsmdm,
                                              djxx.getDjzclxdm(),
                                              djxx.getGjbzhydm(),
                                              ysjc.getYsjcdm());
                    if (yskm != null)
                    {
                        map.put("yskmdm", yskm.getYskmdm());
                    }
                    else
                    {
                        throw ExceptionUtil.getBaseException(new Exception(),
                            "��ȡԤ���Ŀʧ��!");
                    }
                }
            }
            form.setDataList(mxMapDatas);
            if (bhghc > 0)
            {
                waringStr += "���ǲ��߱����������ʸ�ġ�";
                form.setWarnStr(waringStr);
                List sjList = new ArrayList();
                sjList = this.getJmDate(ynsjeMap, zqrlMap, form);
                tempJsStr += this.getMxJsArray(sjList);
                tempJsStr += jsArray.getArrayByCode("szsmlist", "JM_SZSMDM");
                tempJsStr +=jsArray.getMsgsByCode("szsmdm", "JM_SZSM_ALL",form.getDataList());
                tempJsStr +=jsArray.getMsgsByCode("jmxmjdm", "JM_JMXMJDM",new ArrayList());//add by tangchangfu 2014-05-02 ��˰����˰��Ŀ����
                form.setScriptStr(tempJsStr);
                return form;
            }
            else
            {
                if ( (oks.equals("true") || ok.equals("true") || ok.equals("")) &&
                    (bhghc == 0))
                {
                    waringStr = "";
                    form.setWarnStr(waringStr);
                }
            }
            Map dateMap = getSbrl(form.getSbrq());

            //ɾ��ԭ�ȵļ�¼
            try
            {
                //ɾ����ϸ����
                da.delete(" qxdm='" + InterfaceDj.getQxdm(ud) +
                          "' and jzbz like '" + CodeConstant.SMSB_JZBZ_EDIT
                          + "%" + "'" +
//                          " and szsmdm!='" + CodeConstant.SZSM_QYSDSCODE + "'" +  //delete by tangchangfu 2014-04-04 ��˰����˰��Ŀ����
                          "and ( FSDM='" + CodeConstant.FSDM_WSSB +
                          "' OR FSDM='" + CodeConstant.FSDM_SMSB + "') " +
                          " and sbrq >= to_date('" +
                          String.valueOf(dateMap.get("ksrq")).substring(0, 10) +
                          "','yyyy-MM-dd') and sbrq <= to_date('" +
                          String.valueOf(dateMap.get("jsrq")).substring(0, 10) +
                          "','yyyy-MM-dd') " +
                          "and jsjdm='" + form.getJsjdm() + "'"
                          ,
                          new Jm());
            }
            catch (BaseException ex1)
            {
                throw new ApplicationException("���ݿ����ʧ�ܣ������ҹ���Ա��ϵ��");
            }
            //����OrMappingֵ�����ÿ������
            String zbNames[] =
                {
                "jsjdm", "sbrq","dqxse","dqlrze","qyrs","azrs"};
            //�õ���ϸ���ݼ�
            List mxMapData = form.getDataList();
            //������ϸ����ֵ����

            List mxData = new ArrayList();
            for (int i = 0; i < mxMapData.size(); i++)
            {
                Map map = (Map) mxMapData.get(i);
                Object skssjsrq = map.get("skssjsrq");
                String nd = ( (String) skssjsrq).substring(0, 4);
                //�������������ӽ���
                BeanUtil.copyBeanToMap(zbNames, form, map);
                Jm orMx = new Jm();
                //�����ݴ��ݸ���ϸֵ����
                BeanUtil.populate(orMx, map);
                Date cjrq = TinyTools.stringToDate(form.getCjrq(),
                    CodeConstant.DATETYPE);
                Timestamp cjrqmx = new Timestamp(cjrq.getTime() + i*1000);
                orMx.setCjrq(cjrqmx);
                orMx.setLrrq(curTime);
                orMx.setNd(nd);
                orMx.setFsdm(CodeConstant.FSDM_SMSB);
                orMx.setJzbz(CodeConstant.SMSB_JZBZ);
                orMx.setDjzclxdm(djxx.getDjzclxdm());
                orMx.setGjbzhydm(djxx.getGjbzhydm());
                orMx.setQxdm(InterfaceDj.getQxdm(ud));
                orMx.setSwjgzzjgdm(djxx.getSwjgzzjgdm());
                orMx.setLrr(ud.getYhid());
                String jmxmjdm = orMx.getJmxmjdm();
                System.out.println("JMXLXDM############(1)"+jmxmjdm);
                if(jmxmjdm != null && !"".equals(jmxmjdm)){
                	int index = jmxmjdm.indexOf("-");
                	if(index>0){
                		System.out.println("JMXLXDM############(2)"+jmxmjdm);
                		orMx.setJmxmjdm(jmxmjdm.substring(0, index));
                	}
                }
                
                System.out.println("JMXLXDM############(3)"+jmxmjdm);

                //add by tangchangfu 2014-04-04 ��˰����˰��Ŀ  start
/*                orMx.setDqlrze(new BigDecimal(form.getDqlrze()));//���������ܶ�
                orMx.setDqxse(new BigDecimal(form.getDqxse()));//�������۶�
                orMx.setQyrs(new BigDecimal(form.getQyrs()));//��ҵ����
                orMx.setAzrs(new BigDecimal(form.getAzrs()));//��������
                orMx.setAzbl(new BigDecimal(form.getAzbl()));//���ñ���
*/                
                System.out.println("���������ܶ�++++++++++++++++++++"+orMx.getDqlrze());
                System.out.println("�������۶�++++++++++++++++++++"+orMx.getDqxse());
                System.out.println("��ҵ����++++++++++++++++++++"+orMx.getQyrs());
                System.out.println("��������++++++++++++++++++++"+orMx.getAzrs());
                orMx.setSjly(CodeConstant.SMSB_SJLY_SBLR);
                //add by tangchangfu 2014-04-04 ��˰����˰��Ŀ  end
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
        JmssbForm form = (JmssbForm) vo.getData();
        UserData ud = (UserData) vo.getUserData();

        //�������ݿ�����
        Connection conn = null;
        try
        {
            conn = SfDBResource.getConnection();
            //ʹ��OrMap�������ݿ�ķ�װ��
            SfDBAccess da = new SfDBAccess(conn);
            Map dateMap = getSbrl(form.getSbrq());
            String Sql = " qxdm='" + InterfaceDj.getQxdm(ud) +
                         "' and jzbz like '" + CodeConstant.SMSB_JZBZ_EDIT
                         + "%" + "'" +
//                         " and szsmdm!='" + CodeConstant.SZSM_QYSDSCODE + "'" +//delete by tangchangfu 2014-04-04 ��˰����˰��Ŀ����
                         "and ( FSDM='" + CodeConstant.FSDM_WSSB +
                         "' OR FSDM='" + CodeConstant.FSDM_SMSB + "') " +
                         " and sbrq >= to_date('" +
                         String.valueOf(dateMap.get("ksrq")).substring(0, 10) +
                         "','yyyy-MM-dd') and sbrq <= to_date('" +
                         String.valueOf(dateMap.get("jsrq")).substring(0, 10) +
                         "','yyyy-MM-dd') " +
                         "and jsjdm='" + form.getJsjdm() + "'";

            //ɾ��ԭ�ȵļ�¼
            try
            {
                //ɾ����ϸ����
                da.delete(" qxdm='" + InterfaceDj.getQxdm(ud) +
                          "' and jzbz like '" + CodeConstant.SMSB_JZBZ_EDIT
                          + "%" + "'" +
//                          " and szsmdm!='" + CodeConstant.SZSM_QYSDSCODE + "'" + //delete by tangchangfu 2014-04-04 ��˰����˰��Ŀ����
                          "and ( FSDM='" + CodeConstant.FSDM_WSSB +
                          "' OR FSDM='" + CodeConstant.FSDM_SMSB + "') " +
                          " and sbrq >= to_date('" +
                          String.valueOf(dateMap.get("ksrq")).substring(0, 10) +
                          "','yyyy-MM-dd') and sbrq <= to_date('" +
                          String.valueOf(dateMap.get("jsrq")).substring(0, 10) +
                          "','yyyy-MM-dd') " +
                          "and jsjdm='" + form.getJsjdm() + "'",
                          new Jm());
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
     * �������������õ�����˰��˰Ŀ��˰������ʱ��<br>
     * ��ǰ���ڵ��µ���������ʼ���ֹ���ڵ���
     * ���ڽ�ֹ���ڣ�1��=�޽�����<br>
     * @param  djzclxdm �Ǽ�ע������
     * @param  rq �걨ʱ��
     * @param  conn ���ݿ�����
     * @return Zqrl Map ˰��������ʼ ����ʱ�䣬�޽����ڣ�
     * @throws Exception
     */
    private Map getSksssqMap (String djzclxdm, Date rq, Connection conn)
        throws
        Exception
    {
        List ret = new ArrayList();
        try
        {
            //�õ�����
            //�õ���ǰ��
            String year = String.valueOf(TinyTools.getYear(rq));
            //�õ���ǰ��
            String month = String.valueOf(TinyTools.getMonth(rq) + 1);
            //����Ϊһʱ����
            if (month.length() == 1)
            {
                month = "0" + month;
            }

            Vector criteria = new Vector(); //��ѯ����
            criteria.add("djzclxdm='" + djzclxdm + "'");
            criteria.add("ZQQSRQ<=to_date('" + year + month + "','yyyyMM')");
            criteria.add("ZQZZRQ>=to_date('" + year + month + "','yyyyMM')");

            SfDBAccess db = new SfDBAccess(conn);
            ret = db.query(new Zqrl().getClass(), criteria);
            Map zqrlMap = new HashMap();
            for (int i = 0; i < ret.size(); i++)
            {
                Zqrl zqrl = (Zqrl) ret.get(i);
                zqrlMap.put(zqrl.getSzsmdm(), zqrl);
            }
            return zqrlMap;
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "��ѯ��������ʧ��!");
        }
        finally
        {
            //�ͷ�����
            ///SfDBResource.freeConnection(conn);
        }

    }

    /**
     * ������ϸ�����б�����js2ά����<br>
     * @param mxList ��ϸ�б�
     * @return String js2ά����
     **/
    private String getMxJsArray (List mxList)
    {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < mxList.size(); i++)
        {
            Map mxData = (Map) mxList.get(i);
            buf.append("[");
            //˰��˰Ŀ����
            buf.append("\"" + (String) mxData.get("szsmdm") + "\",");
            if (mxData.get("skssksrq") != null)
            {
                //˰����������
                buf.append("\""
                           + SfDateUtil.getDate( (Date) mxData.get("skssksrq")) +
                           "\",");
            }
            else
            {
                buf.append("\"" + (String) mxData.get("skssksrq") + "\",");
            }
            if (mxData.get("skssjsrq") != null)
            {
                buf.append("\""
                           + SfDateUtil.getDate( (Date) mxData.get("skssjsrq")) +
                           "\",");
            }
            else
            {
                buf.append("\"" + (String) mxData.get("skssjsrq") + "\",");
            }
            buf.append("],");
        }
        if (buf.length() > 0)
        {
            //��������ݣ���ɾ�������ӵĶ���
            buf.delete(buf.length() - 1, buf.length());
        }
        else
        {
            return "var szsmDate = new Array();";
        }
        buf.append("];");
        buf = SfStringUtils.replaceAll(buf, "null", "");
        return "var szsmDate = [" + buf.toString();
    }

    /**
     * ˰��˰Ŀ��������
     * @param ynsjeMap Ӧ��˰���
     * @param zqrlMap ��������
     * @param form ��������
     * @return List
     * @throws BaseException
     */
    private List getJmDate (Map ynsjeMap, Map zqrlMap, JmssbForm form)
        throws
        BaseException
    {
        List jmData = new ArrayList();
        try
        {
            List szsmData = CodeManager.getCodeList("JM_ALL_SZSMDM",
                CodeConstants.CODE_MAP_MAPLIST).
                            getRecords();
            //20040401 ��ý��ɴ������ڼ��㳵����˰����������Shi Yangfeng
            com.ttsoft.bjtax.sfgl.proxy.ServiceProxy proxy =
                new com.ttsoft.bjtax.sfgl.proxy.ServiceProxy();
            Date sbrq = SfDateUtil.getDate(form.getSbrq());
            Map cdfMap = proxy.getCDFSet(form.getJsjdm(), sbrq, sbrq, sbrq);

            for (int i = 0; i < szsmData.size(); i++)
            {
                Map orSz = (Map) szsmData.get(i);
                Map szsmMap = new HashMap();
                Zqrl zqrl = (Zqrl) zqrlMap.get( (String) orSz.get("szsmdm"));
                String szsmdm = (String) orSz.get("szsmdm");
                String zqlxdm = (String) orSz.get("zqlxdm");
                if (zqrl != null)
                {
                    szsmMap.put("szsmdm", szsmdm);
                    szsmMap.put("skssksrq", zqrl.getZqssrqq());
                    szsmMap.put("skssjsrq", zqrl.getZqssrqz());
                }
                else
                {
                    Map map = (Map) Skssrq.getSksssq(form.getJsjdm(), szsmdm,
                        CodeConstant.SKLXDM_ZCJK,
                        zqlxdm,
                        SfDateUtil.getDate(form.getSbrq()),
                        new BigDecimal(0),
                        new BigDecimal(0), new BigDecimal(0),
                        ynsjeMap);
                    szsmMap.put("szsmdm", szsmdm);
                    szsmMap.put("skssjsrq", (Timestamp) map.get("SKSSJSRQ"));
                    szsmMap.put("skssksrq", (Timestamp) map.get("SKSSKSRQ"));
                }
                //���ݽ��ɴ����޸ĳ������޽�����
                this.modifyCft(szsmMap, sbrq, (Map) cdfMap.get("JNCS"));
                jmData.add(szsmMap);
            }
        }
        catch (Exception ex)
        {
            //�׳��쳣
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex, "ϵͳ�����������Ա��ϵ!");
        }
        finally
        {
            //�ͷ����ݿ�����
        }
        return jmData;
    }

    /**
     * �жϸ�˰Ŀ�Ƿ�Ϊ����˰
     * @param  szsmdm ˰��˰Ŀ����
     * @return boolean
     */
    private boolean isYysFjs (String szsmdm)
    {
        boolean ret = false;
        List fList = CodeManager.getCodeList("ZHSB_SZSMADD",
                                             CodeConstants.CODE_MAP_MAPLIST).
                     getRecords();
        //�õ�����˰��map�б�{fjsszsmdm=100010, szsmdm=027500}
        for (int i = 0; i < fList.size(); i++)
        {
            Map temp = (Map) fList.get(i);
            if (temp.get("fjsszsmdm").equals(szsmdm) &&
                ( (String) temp.get("szsmdm")).
                substring(0, 2).equals(CodeConstant.SZSM_YYS))
            {
                //��Ӫҵ˰����˰
                return true;
            }
        }

        return ret;
    }

    /**
     * ���ݽ��ɴ�������������˰����������
     * @param  mxData ��ϸ����
     * @param  jncs ���ɴ���
     * @param sbrq �걨����
     */
    private void modifyCft (Map mxData, Date sbrq, Map jncs)
    {

        String szdm = ( (String) mxData.get("szsmdm")).substring(0, 2);
        int ijncs = 0;
        if (jncs != null && (String) jncs.get(szdm) != null)
        {
            ijncs = Integer.parseInt( (String) jncs.get(szdm));
        }
        if (Skssrq.SZDM_CFT.indexOf(szdm) > 0)
        {
            //  ���ڳ�����˰��
            Map temp = Skssrq.getCftSkssrq(sbrq, ijncs);
            mxData.put("skssksrq", (Timestamp) temp.get(Skssrq.SKSSKSRQ));
            mxData.put("skssjsrq", (Timestamp) temp.get(Skssrq.SKSSJSRQ));
        }
    }


}
