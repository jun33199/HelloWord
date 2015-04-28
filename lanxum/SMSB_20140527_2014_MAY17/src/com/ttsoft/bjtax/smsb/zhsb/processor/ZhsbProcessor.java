/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.zhsb.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import weblogic.management.console.actions.internal.ExceptionUtils;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.common.Constant;
import com.ttsoft.bjtax.sfgl.common.code.CodeManager;
import com.ttsoft.bjtax.sfgl.common.code.CodeUtils;
import com.ttsoft.bjtax.sfgl.common.constant.CodeConstants;
import com.ttsoft.bjtax.sfgl.common.db.util.BeanUtil;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.model.Cftsyhd;
import com.ttsoft.bjtax.sfgl.common.model.Grtszygsde;
import com.ttsoft.bjtax.sfgl.common.model.Grzsfs;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.sfgl.common.util.SfStringUtils;
import com.ttsoft.bjtax.sfgl.model.orobj.Dqdedlmx1;
import com.ttsoft.bjtax.sfgl.model.orobj.Tszslmx;
import com.ttsoft.bjtax.shenbao.model.domain.Gzsx;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb;
import com.ttsoft.bjtax.shenbao.model.domain.Szsm;
import com.ttsoft.bjtax.shenbao.model.domain.Zqrl;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.model.client.SbjkmxDis;
import com.ttsoft.bjtax.smsb.util.EArray;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.InterfaceSf4Sb;
import com.ttsoft.bjtax.smsb.util.JksUtil;
import com.ttsoft.bjtax.smsb.util.Skssrq;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.bjtax.smsb.zhsb.web.ZhsbActionForm;
import com.ttsoft.bjtax.smsb.zhsb.web.ZhsbGzsxActionForm;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.exception.SystemException;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.DateUtil;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.framework.exception.ApplicationException;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description: ʵ���ۺ��걨���ܣ������ɿ���¼�룬ά����</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class ZhsbProcessor
    implements Processor
{
    private Map CDF = null;

    public ZhsbProcessor ()
    {
    }

    /**
     * ʵ��Processor�ӿ�
     * @param vo ҵ�����
     * @return Object VOPackage������
     * @throws BaseException ҵ���쳣
     */

    public Object process (VOPackage vo)
        throws com.ttsoft.framework.exception.
        BaseException
    {
        switch (vo.getAction())
        {
            case CodeConstant.SMSB_QUERYACTION:
                doQuery(vo);
                return null;

            case CodeConstant.SMSB_SAVEACTION:
                return doSave(vo);
                //return null;
            case CodeConstant.SMSB_DELETEACTION:
                return doMoveToHis(vo);
            case CodeConstant.SMSB_ZHSB_INITLIST:
                return getInitList(vo);
            case CodeConstant.SMSB_ZHSB_GZSX:
                return this.getGzsxInfo(vo);
            case CodeConstant.ZHSB_JM01_ACTION:
                return doSave_his_xwqy(vo);
            case CodeConstant.ZHSB_JM01_CHECKCURRENT_ACTION:
                return doCheckCurrent(vo);
            default:
                return null;
        }

    }

    private void doQuery (VOPackage vo)
        throws BaseException
    {
        //
    }

    
    /**
     * doSave     ����¼������
     * @param     vo ҵ�����
     * @return    ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
     * @throws BaseException    ��������������׳��쳣��Ϣ
     */

    private Object doSave (VOPackage vo)
    throws BaseException
{


    //ormapping����
    Sbjkzb orObj = new Sbjkzb();
    //��ȡform����
    ZhsbActionForm form = (ZhsbActionForm) vo.getData();

    //���UserData
    UserData ud = vo.getUserData();
    //������form�ֵ�columns һ���ı��������е�����Ϊ��ϸ���ֶ���
    String names[] =
        {
        "jsjdm", "nsrmc", "yhmc", "yhdm", "zh", "sklxdm", "sklxmc", "sbrq"};
    Timestamp now = new Timestamp( (new java.util.Date()).getTime());
    try
    {

        //��form�ж�Ӧ������Ϣ���浽ֵ����
        BeanUtil.copyBeanToBean(names, form, orObj);
        //ͨ���Ǽǽӿ�ȡ����˰�������Ϣ
        /* start added by huxiaofeng 2005.8.1*/
        //SWDJJBSJ jbsj = InterfaceDj.getJBSJ(form.getJsjdm(), ud);
        SWDJJBSJ jbsj = InterfaceDj.getJBSJ_New(form.getJsjdm(), ud);
        /* end added by huxiaofeng 2005.8.1*/

        //����������Ϣ
        //�Ǽ�ע������
        orObj.setDjzclxdm(jbsj.getDjzclxdm());
        //orObj.setDjzclxmc(jbsj.getDjzclxmc());
        //���ұ�׼��ҵ����
        orObj.setGjbzhydm(jbsj.getGjbzhydm());
        //orObj.setGjbzhymc(jbsj.getGjbzhymc());
        //˰�������֯����
        orObj.setSwjgzzjgdm(jbsj.getSwjgzzjgdm());
        //orObj.setSwjgzzjgmc(jbsj.getSwjgzzjgmc());
        //���ջ���
        //orObj.setZsswjgzzjgdm(jbsj.getSwjgzzjgdm());
        orObj.setZsswjgzzjgdm(ud.getSsdwdm());
        //orObj.setZsswjgzzjgmc(jbsj.getSwjgzzjgmc());
        //������ϵ
        orObj.setLsgxdm(jbsj.getLsgxdm());
        //orObj.setLsgxmc(jbsj.getLsgxmc());
        //��Ӫ��ַ��ϵ�绰
        orObj.setJydzlxdm(jbsj.getJydzlxdm());
        //¼����
        orObj.setLrr("smsb");
        if (ud != null)
        {
            orObj.setLrr(ud.getYhid());
            //¼������
        }
        orObj.setLrrq(now);
        //��������
        //orObj.setCjsj(now);
        orObj.setCjrq(now);
        //˰������
        orObj.setSklxdm(form.getSklxdm());
        //orObj.setSklxmc(this.getSklxmc(form.getSklxdm()));
        //�걨��ʽ����
        orObj.setFsdm(CodeConstant.FSDM_SMSB);
        //������Դ
        orObj.setSjly(CodeConstant.SMSB_SJLY_SBLR);
        //���ش���
        orObj.setQxdm(InterfaceDj.getQxdm(ud));
        //�����ݽ��з�Ʊ����
        JksUtil ju = new JksUtil();
//        return ju.getJkDataZhsb(orObj, form.getDataList());
        try
        {

          //start modifying by qianchao 2005.10.26
          System.out.println("jsjdm:" + form.getJsjdm() +  "== ��Ʊ���ͣ�" + form.getJksType());
          Map retmap = (Map) ju.getJkDataZhsb(orObj,form.getDataList(),form.getJksType());
          form.setSbbh(ju.getSbbh());
          form.setDataList((List)retmap.get(CodeConstant.ZHSB_JKS_LIST));
          return form;
          //end modifying by qianchao 2005.10.26
        }
        catch (BaseException ex1)
        {
            ex1.printStackTrace();
            throw new ApplicationException("��������ʧ�ܣ�");
        }

    }
    catch (Exception ex)
    {
        //throw ExceptionUtil.getBaseException(ex, "ϵͳ�����������Ա��ϵ!");
        ex.printStackTrace();
        throw ExceptionUtil.getBaseException(ex);
    }

}

    private Boolean doMoveToHis (VOPackage vo) throws BaseException{
        //���UserData
        UserData ud = vo.getUserData();
        Map moveMap = (Map)vo.getData();
        String jsjdm = (String)moveMap.get("jsjdm");
        List jkpzhList = (List)moveMap.get("jkpzhList");
        try{  
        	JksUtil ju = new JksUtil();
        	ju.moveSbjkToHis(jsjdm, jkpzhList, ud.getYhid());
        }catch (Exception ex){
            //throw ExceptionUtil.getBaseException(ex, "ϵͳ�����������Ա��ϵ!");
            throw ExceptionUtil.getBaseException(ex);
        }
        
    	return new Boolean(true);
    }
    
    /**
     * @Description: TODO ���浽��ʷ�� ������ӡ��С΢��ҵ�Ż�ʹ�ã��Ļ���ҵ���裬Ӫҵ˰��
     * @param vo
     * @return
     * @throws BaseException
     */
    private Object doSave_his_xwqy (VOPackage vo)throws BaseException
{


    //ormapping����
    Sbjkzb orObj = new Sbjkzb();
    //��ȡform����
    ZhsbActionForm form = (ZhsbActionForm) vo.getData();

    //���UserData
    UserData ud = vo.getUserData();
    //������form�ֵ�columns һ���ı��������е�����Ϊ��ϸ���ֶ���
    String names[] =
        {
        "jsjdm", "nsrmc", "yhmc", "yhdm", "zh", "sklxdm", "sklxmc", "sbrq"};
    Timestamp now = new Timestamp( (new java.util.Date()).getTime());
    try
    {

        //��form�ж�Ӧ������Ϣ���浽ֵ����
        BeanUtil.copyBeanToBean(names, form, orObj);
        //ͨ���Ǽǽӿ�ȡ����˰�������Ϣ
        /* start added by huxiaofeng 2005.8.1*/
        //SWDJJBSJ jbsj = InterfaceDj.getJBSJ(form.getJsjdm(), ud);
        SWDJJBSJ jbsj = InterfaceDj.getJBSJ_New(form.getJsjdm(), ud);
        /* end added by huxiaofeng 2005.8.1*/

        //����������Ϣ
        //�Ǽ�ע������
        orObj.setDjzclxdm(jbsj.getDjzclxdm());
        //orObj.setDjzclxmc(jbsj.getDjzclxmc());
        //���ұ�׼��ҵ����
        orObj.setGjbzhydm(jbsj.getGjbzhydm());
        //orObj.setGjbzhymc(jbsj.getGjbzhymc());
        //˰�������֯����
        orObj.setSwjgzzjgdm(jbsj.getSwjgzzjgdm());
        //orObj.setSwjgzzjgmc(jbsj.getSwjgzzjgmc());
        //���ջ���
        //orObj.setZsswjgzzjgdm(jbsj.getSwjgzzjgdm());
        orObj.setZsswjgzzjgdm(ud.getSsdwdm());
        //orObj.setZsswjgzzjgmc(jbsj.getSwjgzzjgmc());
        //������ϵ
        orObj.setLsgxdm(jbsj.getLsgxdm());
        //orObj.setLsgxmc(jbsj.getLsgxmc());
        //��Ӫ��ַ��ϵ�绰
        orObj.setJydzlxdm(jbsj.getJydzlxdm());
        //¼����
        orObj.setLrr("smsb");
        if (ud != null)
        {
            orObj.setLrr(ud.getYhid());
            //¼������
        }
        orObj.setLrrq(now);
        //��������
        //orObj.setCjsj(now);
        orObj.setCjrq(now);
        //˰������
        orObj.setSklxdm(form.getSklxdm());
        //orObj.setSklxmc(this.getSklxmc(form.getSklxdm()));
        //�걨��ʽ����
        orObj.setFsdm(CodeConstant.FSDM_SMSB);
        //������Դ
        orObj.setSjly(CodeConstant.SMSB_SJLY_SBLR);
        //���ش���
        orObj.setQxdm(InterfaceDj.getQxdm(ud));
        //�����ݽ��з�Ʊ����
        JksUtil ju = new JksUtil();

        try
        {
          System.out.println("jsjdm:" + form.getJsjdm() +  "== ��Ʊ���ͣ�" + form.getJksType());
          Map retmap = (Map) ju.getJkDataZhsb_his(orObj,(List)form.getJmDateMap().get(CodeConstant.ZHSB_JM01),form.getJksType(),"10",ud.getYhid(),form.getSbbh());
          //.setSbbh(ju.getSbbh());
          //form.setDataList((List)retmap.get(CodeConstant.ZHSB_JKS_LIST));
          return form;
          
        }
        catch (BaseException ex1)
        {
            ex1.printStackTrace();
            throw new ApplicationException("��������ʧ�ܣ�");
        }

    }
    catch (Exception ex)
    {
        //throw ExceptionUtil.getBaseException(ex, "ϵͳ�����������Ա��ϵ!");
        ex.printStackTrace();
        throw ExceptionUtil.getBaseException(ex);
    }

}
    
    
    
    /**
     * ����˰�����ʹ����ѯ˰����������
     * @param sklx String ˰�����ʹ���
     * @return String ˰����������
     */
    /* deleted by qianchao 2005.11.2
    private String getSklxmc (String sklx)
    {

        //���ݴ������˰����������
        return CodeUtils.getCodeBeanLabel("ZHSB_SKLX", sklx);
    }
*/
    /**
     * �õ���ʼ��list����˰��˰Ŀlist,����˰list
     * @param     vo ҵ�����
     * @return List
     * @throws BaseException
     */
    private Object getInitList (VOPackage vo)
        throws BaseException
    {
        //��ȡform����
        ZhsbActionForm form = (ZhsbActionForm) vo.getData();
        //�õ��ܿ��û������ش���
        String qxdm = InterfaceDj.getQxdm(vo.getUserData());
        List ret = new ArrayList();
        String code="ORSZSM";
        Connection con = null;
        try
        {
//      System.out.println("time == "+new Date());
            con = SfDBResource.getConnection();
            //�õ�˰��˰Ŀ�����б��б�͸���˰�б�
            EArray jsArray = new EArray();
            String tempJsStr = jsArray.getArrayByCode("szsmlist_add",
                "ZHSB_SZSMADD");

            /**
             * ���˰������Ϊ�Բ鲹˰,������ע����11\88˰��,���򲻴���
             */
            if("400".equals(form.getSklxdm())){
            	tempJsStr +=jsArray.getMsgsByCode("szsmdm", "ZHSB_SZSM_ZCBS",new ArrayList());
            	code="ORSZSM_ZCBS";
            }else{
            	tempJsStr +=jsArray.getMsgsByCode("szsmdm", "ZHSB_SZSM",new ArrayList());
            	code="ORSZSM";
            }
            
            //����˰�ѽӿڴ����ڶ�����ʺ͸���˰
            List mxList = this.dealWithSfgl(form.getJsjdm(),
                                            this.getSzsmList(con,code),
                                            SfDateUtil.getDate(form.getSbrq()));
            //�����Ѿ��õ�����������mapΪ��ϸ�������˰����������
            //�����걨���ڵõ�˰����������
            Date date = SfDateUtil.getDate(form.getSbrq());
            if (date == null)
            {
                date = new Date();
            }
//      System.out.println("time == "+new Date());
            this.addSkssrqByMap(form.getJsjdm(), mxList, date, con);
//      System.out.println("time == "+new Date());
            //����˰���������ڵļ���
//      this.fixSpeSkssrq(form.getJsjdm(), date, mxList);
//
//      this.fixSpeSkssrq2(form.getJsjdm(), date, mxList);
            form.setInitMxList(mxList);
            //������ϸ���ݵ�js����
            tempJsStr += this.getMxJsArray(mxList);
            tempJsStr += "\nvar szsmlist_fields = [\"szsmdm\",\"szmc\",\"szsmmc\",\"skssksrq\",\"skssjsrq\",\"kssl\",\"jsje\",\"sjse\",\"szdm\",\"sffjs\",\"szsmdm_old\",\"asljbs\",\"sl\",\"jsjs\"];";
            tempJsStr += "\n"+this.getSzsmtskzsJs();
            form.setScriptStr(tempJsStr);
            //���ø�֪�����б�
            form.setGzsxList(this.getGzsxList(form.getJsjdm(), qxdm, new Date(),
                                              con));
            return form;

        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "ϵͳ�����������Ա��ϵ!");
        }
        finally
        {
            SfDBResource.freeConnection(con);
        }

    }

    /**
     * �õ�˰��˰Ŀ
     * @param     con ���ݿ�����
     * @return List ˰��˰Ŀlist
     * @throws BaseException
     */
    private List getSzsmList (Connection con,String code)
        throws BaseException
    {
        List ret = new ArrayList();
        ret = CodeManager.getCodeList(code, CodeConstants.CODE_MAP_ORLIST).
              getRecords();
        return ret;

    }

    /**
     * �������������õ�����˰��˰Ŀ��˰������ʱ��<br>
     * ��ǰ���ڵ��µ���������ʼ���ֹ���ڵ���
     * ���ڽ�ֹ���ڣ�1��=�޽�����<br>
     * @param  rq �걨ʱ��
     * @param  djzclxdm �Ǽ�ע������
     * @param     conn ���ݿ�����
     * @throws Exception
     * @return Map
     */
    private Map getSksssqMap (String djzclxdm, Date rq, Connection conn)
        throws
        Exception
    {
        List ret = new ArrayList();
        //Connection conn = null;
        try
        {
            //�õ�����
            //conn = SfDBResource.getConnection();

            String dateStr = TinyTools.Date2String(rq,"yyyyMM");
            Vector criteria = new Vector(); //��ѯ����

            criteria.add("djzclxdm='" + djzclxdm + "'");
            criteria.add("ZQQSRQ<=to_date('" + dateStr + "','yyyyMM')");
            criteria.add("ZQZZRQ>=to_date('" + dateStr + "','yyyyMM')");

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
     * ������ϸ�б��е�˰��˰Ŀ���걨���ڡ����˰����������
     * @param     jsjdm ���������
     * @param mxList ǰ̨��ʾ��List
     * @param rq �걨����
     * @param     conn ���ݿ�����
     * @return list
     * @throws BaseException
     *
     */
    private List addSkssrqByMap (String jsjdm, List mxList, Date rq,
                                 Connection conn)
        throws
        BaseException
    {
        List ret = new ArrayList();
        try
        {
            //ͨ���Ǽǽӿڵõ���˰�˻�����Ϣ
            /* start added by huxiaofeng 2005.8.16*/
            //SWDJJBSJ jbsj = InterfaceDj.getJBSJ(jsjdm);
            SWDJJBSJ jbsj = InterfaceDj.getJBSJ_New2(jsjdm);
            /* end added by huxiaofeng 2005.8.16*/


            //�õ��������ڵ�����˰��˰Ŀ˰����������
            Map zqrlMap = this.getSksssqMap(jbsj.getDjzclxdm(), rq, conn);
            for (int i = 0; i < mxList.size(); i++)
            {
                //Ϊÿ����ϸ���˰����������
                SbjkmxDis mxData = (SbjkmxDis) mxList.get(i);
                //����˰����������mapΪ��ϸ�������˰����������
                Zqrl zqrl = (Zqrl) zqrlMap.get(mxData.getSzsmdm());

                //Modified by lufeng 20031105
                if (zqrl != null)
                {
                    mxData.setSkssjsrq(zqrl.getZqssrqz());
                    mxData.setSkssksrq(zqrl.getZqssrqq());

                }
                else
                {
                    Map map = (Map) Skssrq.getSksssq(jsjdm, mxData.getSzsmdm(),
                        CodeConstant.SKLXDM_ZCJK,
                        mxData.getZqlxdm(),
                        rq, mxData.getSjse(),
                        mxData.getKssl(), mxData.getJsje(),
                        mxData.getJsje());
                    mxData.setSkssjsrq( (Timestamp) map.get("SKSSJSRQ")); //��ʼ����
                    mxData.setSkssksrq( (Timestamp) map.get("SKSSKSRQ")); //��������

                }
                //���ݽ��ɴ����޸ĳ������޽�����

                this.modifyCft(mxData, rq, (Map) CDF.get("JNCS"));
                ret.add(mxData);
            }
            return ret;
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "ϵͳ�����������Ա��ϵ!");
        }
    }

    /**
     * ����˰�ѽӿڵõ���������Ϣ<br>
     * ��Ϊ����˰�ѽӿ�ֻ����ͨ������������˰��˰Ŀ��һȷ��<br>
     * ����Ӧ��Ϊ����������µ�˰�ѽӿڣ�ͨ�����������õ����еĳ������˶�
     * @param     jsjdm ���������
     * @param     date �걨����
     * @param     qsrq �걨����
     * @param     jzrq �걨����
     * @return Map
     */
    private Map getCDFSet (String jsjdm, Date date, Date qsrq, Date jzrq)
    {

        Map map = new HashMap();
        try
        {

            com.ttsoft.bjtax.sfgl.proxy.ServiceProxy proxy =
                new com.ttsoft.bjtax.sfgl.proxy.ServiceProxy();
            map = proxy.getCDFSet(jsjdm, date, qsrq, jzrq);
        }
        catch (Exception ex)
        {
            return null;
        }
        return map;
    }

    /**
     * ����˰�ѽӿڵõ���������Ϣ<br>
     * ��Ϊ����˰�ѽӿ�ֻ����ͨ������������˰��˰Ŀ��һȷ��<br>
     * ����Ӧ��Ϊ����������µ�˰�ѽӿڣ�ͨ�����������õ����еĳ������˶�
     * <br>�÷���ͨ�����������õ����еĳ���������
     * @param     jsjdm ���������
     * @param     date �걨����
     * @return Map
     */
    /* deleted by qianchao 2005.11.2
    private Map getCftInfoByJsjdm (String jsjdm, Date date)
    {

        Map cftMap = new HashMap();
        try
        {

            com.ttsoft.bjtax.sfgl.proxy.ServiceProxy proxy =
                new com.ttsoft.bjtax.sfgl.proxy.ServiceProxy();
            List cftList = proxy.getCftsyhdInfo(jsjdm, date);
            for (int i = 0; i < cftList.size(); i++)
            {
                Cftsyhd temp = (Cftsyhd) cftList.get(i);
                cftMap.put(temp.getSzsmdm(), temp);
            }
        }
        catch (Exception ex)
        {
            //ex.printStackTrace();
            return null;
        }
        return cftMap;
    }
*/
    /**
     * ����˰�ѽӿڵõ�������List�õ�map<br>
     * ��Ϊ����˰�ѽӿ�ֻ����ͨ������������˰��˰Ŀ��һȷ��<br>
     * ����Ӧ��Ϊ����������µ�˰�ѽӿڣ�ͨ�����������õ����еĳ������˶�
     * <br>�÷���ͨ�����������õ����еĳ�������
     * @param cftList �϶��б�
     * @return Map
     */
    private Map getCftMap (List cftList)
    {

        Map cftMap = new HashMap();
        try
        {

            for (int i = 0; i < cftList.size(); i++)
            {
                Cftsyhd temp = (Cftsyhd) cftList.get(i);
                cftMap.put(temp.getSzsmdm(), temp);
            }
        }
        catch (Exception ex)
        {
            return null;
        }
        return cftMap;
    }

    /**
     * ���ݽ��ɴ�������������˰����������
     * @param mxData ��ϸ����
     * @param  sbrq �걨����
     * @param  jncs ���ɴ���
     */
    private void modifyCft (SbjkmxDis mxData, Date sbrq, Map jncs)
    {

        String szdm = mxData.getSzsmdm().substring(0, 2);
        int ijncs = 0;
        if (jncs != null && (String) jncs.get(szdm) != null)
        {
            ijncs = Integer.parseInt( (String) jncs.get(szdm));
            /**
             * ��������ʹ��˰2007��10��������������
             * ��2007���ϰ���δ��������˰,�°�������������ȫ��˰��
             * ���������¿���:
             * 2007���������ʹ��˰���ɴ���ͳһΪȫ������,�����ɴ���Ϊ0
             * 2007.10���ڹ���,��˰���϶����ɴ�������
             * 
             * ��־�� 2007-8-15�ձ�ע
             */
            if(szdm.equals("15")&&TinyTools.Date2String(sbrq,"yyyyMMdd").substring(0,4).equals("2007")){
            	ijncs=0;
            }
        }
        if (Skssrq.SZDM_CFT.indexOf(szdm) > 0)
        {
            //  ���ڳ�����˰��
            Map temp = Skssrq.getCftSkssrq(sbrq, ijncs);
            mxData.setSkssksrq( (Timestamp) temp.get(Skssrq.SKSSKSRQ));
            mxData.setSkssjsrq( (Timestamp) temp.get(Skssrq.SKSSJSRQ));
			//modified by Guoxh,2007-09-12��11��88ֻ������20061231ǰ��˰�
			if(szdm.equals("11") || szdm.equals("88")){
				mxData.setSkssksrq( Skssrq.getTimestampMinDay(2006,0));
				mxData.setSkssjsrq( Skssrq.getTimestampMaxDay(2006,11));
			}
        }
    }

    /**
     * ����˰�ѹ����϶����������������Ϊ���˰��˰Ŀ����<br>
     * �������ʹ��룱�����ꡢ��������ꡢ�������ȡ�12�����£�
     * ���ݶ��ڶ������ݺ�Ӫҵ˰����˰���ݴ���˰��˰Ŀlist
     * ���ش����ĸ���˰��˰Ŀ��Ӧ����ϸlist
     * @param jsjdm ���������
     * @param szsmList ˰���б�
     * @param sbrq �걨����
     * @throws Exception
     * @return Map
     */
    private List dealWithSfgl (String jsjdm, List szsmList, Date sbrq)
        throws
        Exception
    {
        //��Ӵ�������ݵ���ϸlist
        List mxList = new ArrayList();

        try
        {
            //�õ����������µ�˰����������
            Map semiY = Skssrq.monthSkssrq(sbrq);
            Date skssjsrq = (Date) semiY.get(Skssrq.SKSSJSRQ);
            Date skssksrq = (Date) semiY.get(Skssrq.SKSSKSRQ);
            /**
             * ͨ�������������ڶ���͸���˰��һ�Ľӿڵõ��������
             *
             */
            Map cdfMap = this.getCDFSet(jsjdm, sbrq, skssksrq, skssjsrq);
            CDF = cdfMap;
            //���ڶ�����Ϣ
            Map dqdeInfo = this.getDqdeMap( (List) cdfMap.get(Constant.
                SFGL_SB_DQDE));
            //Ӫҵ˰����˰��Ϣ
            Map fjsInfo = this.getFjsMap( (List) cdfMap.get(Constant.
                SFGL_SB_FJS));
            //����˰�ѽӿڵõ���������Ϣ
            Map cftInfo = this.getCftMap( (List) cdfMap.get(Constant.
                SFGL_SB_CFT));
            mxList = this.creatMxList(szsmList);
            //���ݸ�˰����ӿڵõ����и��˵Ķ���ϼ�
            List gsList = (List) cdfMap.get(Constant.SFGL_SB_GSDE);
            //������˰�����ڶ�����������
            for (int i = 0; i < mxList.size(); i++)
            {
                SbjkmxDis mxData = (SbjkmxDis) mxList.get(i);
                //  if (mxData.getZqlxdm().equals(CodeConstant.ZQLXDM_YEAR)) {
                //����������Ϊ���ʱ�����˰�ѽӿڴ�����Ӧ������

                String szsmdm = mxData.getSzsmdm();

                // ����˰�ѹ����еĶ��ڶ���
                if (dqdeInfo != null)
                {
                    Dqdedlmx1 dqde = (Dqdedlmx1) dqdeInfo.get(szsmdm);
                    if (dqde != null)
                    {
                        //
                        if (dqde.getZsfsdm().equals(CodeConstant.ZHSB_ZSFS_DE))
                        {
                            //���շ�ʽΪ����
                            //�����걨�Ľӿ��У���˰���϶�����Ӧ�걨�ġ���˰������Ӧ��˰���Ӧ�걨�ġ�ʵ�ʽ�˰���
                            mxData.setSjse(dqde.getYnsrd());
                            //mxData.setSjse(dqde.getSjrd());
                            mxData.setJsje(dqde.getSjrd());
                            mxData.setFromDqde(true);
                        }
                        else if (dqde.getZsfsdm().equals(CodeConstant.
                            ZHSB_ZSFS_DL))
                        {
                            //���շ�ʽΪ����
                            mxData.setSl(dqde.getZsl());
                        }

                    }
                }
                // ����˰�ѹ����еĸ���˰˰��
                //        if (mxData.getSffjs() != null && mxData.getSffjs().equals("2") &&
//            fjsInfo != null) {
                //�걨����ʱ��˰���ǳ���ά������˰��10%���ͽ����Ѹ��ӣ�51%��ȡ˰�����������ʣ�����˰��ȡ˰�Ѷ��ڶ���ɡ�
                String smdm = szsmdm.substring(0, 2);
                if (fjsInfo != null && (smdm.equals("10") || smdm.equals("51")))
                {
                    //˰Ŀ��Ӫҵ˰����˰��������˰��ȡ�ú˶���Ϣ
                    Tszslmx tszslmx = (Tszslmx) fjsInfo.get(smdm);
                    if (tszslmx != null)
                    {
                        //
                        mxData.setSl(tszslmx.getSl());
                    }
                }
                //����н����050130
                if (szsmdm.equals(CodeConstant.ZRR_GXSD_SZSMDM))
                {
                    //��˰����ϼ�
                    BigDecimal hj = new BigDecimal(0);
                    //�ϼ����еĸ�˰����
                    for (int ig = 0; ig < gsList.size(); ig++)
                    {
                        Grtszygsde temp = (Grtszygsde) gsList.get(ig);
                        hj = hj.add(temp.getHdske());
                    }

                    if (hj.longValue() != 0)
                    {
                        mxData.setSjse(hj);
                    }
                }

                //}
            }
            return mxList;
        }
        catch (BaseException ex)
        {
            throw ExceptionUtil.getBaseException(ex, "����˰��˰Ŀ�б�ʧ��");
        }

    }

    /**
     * ����˰�ѹ����϶��������˰��˰Ŀ����<br>
     * ���ݶ��ڶ������ݺ�Ӫҵ˰����˰���ݴ���˰��˰Ŀlist
     * ���ش����ĸ���˰��˰Ŀ��Ӧ����ϸlist
     * @param szsmList ˰���б�
     * @return Map
     */
    private List creatMxList (List szsmList)
    {
        //��Ӵ�������ݵ���ϸlist
        List mxList = new ArrayList();
        String szdm = "";
        String szmc = "";
        for (int i = 0; i < szsmList.size(); i++)
        {
            //ǰ̨��ʾ���걨�ɿ���ϸ
            SbjkmxDis temp = new SbjkmxDis();
            Szsm szsm = (Szsm) szsmList.get(i);
            //��Ϊ����˰��˰Ŀ�����������Կ�����ȡ��˰��

            if (szsm.getSzsmdm().length() == 2)
            {
                //����Ϊ2����˰�ִ���
                szdm = szsm.getSzsmdm();
                szmc = szsm.getSzsmmc();
            }
            //������ϸ��˰��˰Ŀ
            if (szsm.getSzsmdm().length() == 6)
            {
                //����Ϊ6Ϊ˰Ŀ
                temp.setSzsmdm(szsm.getSzsmdm());
                temp.setSzsmmc(szsm.getSzsmmc());
                //�����Ƿ񸽼�˰��ʾ
                temp.setSffjs(szsm.getSffjs());
                //����˰��
                temp.setSzdm(szdm);
                temp.setSzmc(szmc);
                //���ð������Ʊ�ʾ
                temp.setAsljbs(szsm.getAsljbs());
                //����˰��
                temp.setSl(szsm.getSl());
                //�����������ʹ���
                temp.setZqlxdm(szsm.getZqlxdm());
                //���ü�˰����
                temp.setJsjs(szsm.getJsjs());
                mxList.add(temp);
            }

        }
        return mxList;
    }

    /**
     * ����˰�ѽӿڵõ�����˰�˶�
     * @param jsjdm ���������
     * @throws BaseException
     * @return Map
     */
    /* deleted by qianchao 2005.11.2
    private Map getFjsInfo (String jsjdm)
        throws BaseException
    {
        com.ttsoft.bjtax.sfgl.proxy.ServiceProxy sfglProxy =
            new com.ttsoft.bjtax.sfgl.proxy.ServiceProxy();

        List fjsInfo = sfglProxy.getYyfjsslInfo(jsjdm, new Date());

        Map fjsMap = new HashMap();
        for (int i = 0; i < fjsInfo.size(); i++)
        {
            Tszslmx tszslmx = (Tszslmx) fjsInfo.get(i);
            fjsMap.put(tszslmx.getSzsmdm(), tszslmx);
        }

        return fjsMap;
    }
*/
    /**
     * ����˰�ѽӿڵõ�����˰�˶�
     * @param fjsInfo ��ϸ�����б�
     * @throws BaseException
     * @return Map
     */
    private Map getFjsMap (List fjsInfo)
        throws BaseException
    {

        Map fjsMap = new HashMap();
        for (int i = 0; i < fjsInfo.size(); i++)
        {
            Tszslmx tszslmx = (Tszslmx) fjsInfo.get(i);
            fjsMap.put(tszslmx.getSzsmdm(), tszslmx);
        }

        return fjsMap;
    }

    /**
     * ����˰�ѽӿ�ȡ�ö��ڶ���˶�
     * @param jsjdm ���������
     * @throws BaseException
     * @return Map
     */
    /* deleted by qianchao 2005.11.2
    private Map getDqdeInfo (String jsjdm)
        throws BaseException
    {
        com.ttsoft.bjtax.sfgl.proxy.ServiceProxy sfglProxy =
            new com.ttsoft.bjtax.sfgl.proxy.ServiceProxy();

        List dqdeInfo = sfglProxy.getYnsje(jsjdm, new Date(), new Date());

        Map dqdeMap = new HashMap();
        if (dqdeInfo != null)
        {
            for (int i = 0; i < dqdeInfo.size(); i++)
            {
                Dqdedlmx1 dqde = (Dqdedlmx1) dqdeInfo.get(i);
                dqdeMap.put(dqde.getSzsmdm(), dqde);
            }
        }

        return dqdeMap;
    }
*/
    /**
     * ����˰�ѽӿ�ȡ�ö��ڶ���˶�
     * ͨ�����ڶ���list�õ����ڶ���map
     * @param dqdeInfo ��ϸ�����б�
     * @throws BaseException
     * @return Map
     */
    private Map getDqdeMap (List dqdeInfo)
        throws BaseException
    {
        Map dqdeMap = new HashMap();
        if (dqdeInfo != null)
        {
            for (int i = 0; i < dqdeInfo.size(); i++)
            {
                Dqdedlmx1 dqde = (Dqdedlmx1) dqdeInfo.get(i);
                dqdeMap.put(dqde.getSzsmdm(), dqde);
            }
        }

        return dqdeMap;
    }

    /**
     * ������ϸ�����б�����js2ά����<br>
     * @param mxList ��ϸ�����б�
     * @return String js2ά����
     **/
    private String getMxJsArray (List mxList)
    {
        StringBuffer ret = new StringBuffer();
        //ret.append("[");
        for (int i = 0; i < mxList.size(); i++)
        {
            SbjkmxDis mxData = (SbjkmxDis) mxList.get(i);
            ret.append("[");
            //˰��˰Ŀ����
            ret.append("\"" + mxData.getSzsmdm() + "\",");
            //˰������
            ret.append("\"" + mxData.getSzmc() + "\",");
            //˰��˰Ŀ����
            ret.append("\"" + mxData.getSzsmmc() + "\",");
            if (mxData.getSkssksrq() != null)
            {
                //˰����������
                ret.append("\"" + SfDateUtil.getDate(mxData.getSkssksrq())
                           + "\",");
            }
            else
            {
                ret.append("\"" + mxData.getSkssksrq() + "\",");
            }
            if (mxData.getSkssjsrq() != null)
            {
                ret.append("\"" + SfDateUtil.getDate(mxData.getSkssjsrq())
                           + "\",");
            }
            else
            {
                ret.append("\"" + mxData.getSkssjsrq() + "\",");
            }
            //��˰����
            ret.append("\"" + mxData.getKssl() + "\",");
            //��˰���
            ret.append("\"" + mxData.getJsje() + "\",");
            //ʵ��˰��
            ret.append("\"" + mxData.getSjse() + "\",");
            //˰�ִ���
            ret.append("\"" + mxData.getSzdm() + "\",");
            //�Ƿ񸽼�˰
            ret.append("\"" + mxData.getSffjs() + "\",");
            //˰��˰Ŀ����
            ret.append("\"" + mxData.getSzsmdm() + "\",");
            //�������Ʊ�ʾ
            ret.append("\"" + mxData.getAsljbs() + "\",");
            //˰��
            ret.append("\"" + mxData.getSl() + "\",");
            //��˰����
            //ret.append("\"" + mxData.getJsjs() + "\"");
            ret.append("\"" + mxData.getSl() + "\"");
            ret.append("],");
        }
        if (ret.length() > 0)
        {
            //��������ݣ���ɾ�������ӵĶ���
            ret.delete(ret.length() - 1, ret.length());
        }
        else
        {
            return "var szsmlist = new Array();";
        }
        ret.append("];");
        ret = SfStringUtils.replaceAll(ret, "null", "");
        return "var szsmlist = [" + ret.toString();

    }

    /**
     * �õ�˰��˰Ŀ
     * @param jsjdm ���������
     * @param rq ����
     * @return List ˰��˰Ŀlist
     * @throws BaseException
     */
    private List getGzsxList (String jsjdm, Date rq)
        throws BaseException
    {

        List ret = new ArrayList();
        Connection con = null;
        try
        {
            con = SfDBResource.getConnection();
            SfDBAccess db = new SfDBAccess(con);
            Vector v = new Vector();
            v.add("gzsxksrq<=to_date('" + DateUtil.getDate(rq)
                  + "','yyyy-MM-dd')");
            v.add("gzsxjsrq>=to_date('" + DateUtil.getDate(rq)
                  + "','yyyy-MM-dd')");
            //ֻ�з�������֪�����ʱ���ת�Ƶ���֪����ҳ��
            //v.add("fzcbs='" + CodeConstant.ZHSB_FZCBS + "' order by GZSXKSRQ");
            v.add("fzcbs='" + CodeConstant.ZHSB_FZCBS + "' ");
            v.add("jsjdm='" + jsjdm + "'  order by cjrq desc");
            List ret1 = db.query(new Gzsx().getClass(), v);
            return ret1;
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "ϵͳ�����������Ա��ϵ!");
        }
        finally
        {
            SfDBResource.freeConnection(con);
        }

    }

    /**
     * �õ���֪�б�
     * @param jsjdm ���������
     * @param rq ����
     * @param qxdm ���ش���
     * @param con ���ݿ�����
     * @return List ��֪list
     * @throws BaseException
     */
    private List getGzsxList (String jsjdm, String qxdm, Date rq,
                              Connection con)
        throws
        BaseException
    {

        List ret = new ArrayList();
        //Connection con = null;
        try
        {
            //con = SfDBResource.getConnection();
            SfDBAccess db = new SfDBAccess(con);
            Vector v = new Vector();
            //v.add("jsjdm='" + jsjdm + "'");
            v.add("gzsxksrq<=to_date('" + DateUtil.getDate(rq)
                  + "','yyyy-MM-dd')");
            v.add("gzsxjsrq>=to_date('" + DateUtil.getDate(rq)
                  + "','yyyy-MM-dd')");
            v.add("qxdm='" + qxdm + "'");
            //ֻ�з�������֪�����ʱ���ת�Ƶ���֪����ҳ��
            //v.add("fzcbs='" + CodeConstant.ZHSB_FZCBS + "' order by GZSXKSRQ");
            v.add("fzcbs='" + CodeConstant.ZHSB_FZCBS + "' ");
            v.add("jsjdm='" + jsjdm + "'  order by cjrq desc");
            List ret1 = db.query(new Gzsx().getClass(), v);
            return ret1;
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "ϵͳ�����������Ա��ϵ!");
        }
        finally
        {
            //SfDBResource.freeConnection(con);
        }

    }

    private List getGzsxInfo (VOPackage vo)
        throws BaseException
    {
        ZhsbGzsxActionForm form = (ZhsbGzsxActionForm) vo.getData();
        return this.getGzsxList(form.getJsjdm(), new Date());
    }

    /**
     * ���ۺ��걨��ʱ��������˰�ֵ�˰���������ڼ��㷽�������⣺<br>
     1.���걨����˰���ĵǼ�ע�����Ͷ�Ӧ�������ʷ����йصģ�<br>
       ���ʵ�Ӫҵ˰(SZSMDM LIKE '02%' )��<br>
       ���Ǽ�ע�����ʹ����Ӧ�������ʷ���Ϊ1��2��ʱ�򣬼�Ϊ���ʵ�ʱ��˰��02������Ϊ��һ��İ����걨��˰������ʱ�ڣ�����ǰ�걨����Ϊһ�����е�����ʱ��˰������Ϊ�����1��1����12��31�գ���ǰ����Ϊ���������ļ��ȵ����ڵ�ʱ��˰������Ϊ��ǰ�������ڼ��ȵ��ϸ����ȵ��³����ϸ����ȵ���ĩ��<br>
       ������ҵ��Ӫҵ˰�Ľ��ڱ���˰Ŀ��SZSMDM LIKE '023%'����<br>
       ���Ǽ�ע�����ʹ���Ϊ���ʵ�ʱ�����˰ĿΪ023������˰���������ڰ�һ��İ����걨���㣬����ǰ����Ϊһ���ȵ�ʱ��˰������Ϊ�����1��1����12��31�գ���ǰ����Ϊ���������ļ��ȵ����ڵ�ʱ��˰������Ϊ��ǰ�������ڼ��ȵ��ϸ����ȵ��³����ϸ����ȵ���ĩ��<br>
     2.���걨����˰����˰�Ѻ˶������շ�ʽ�Լ��걨��˰��˰Ŀ�йصģ�<br>
         ���˶��ʺϻ���ҵ�����շ�ʽΪ�˶����յ����걨���幤�̾�Ӫ����˰Ŀ��SZSMDM LIKE '0512%'����˰���������ھ��ǰ�һ��İ����걨����˰���������ڵģ�<br>
       ��ҵ����˰���ȣ�SZSMDM LIKE '30%'���͸��˶��ʺϻ�����շ�ʽΪ�������յ����걨���幤�̾�Ӫ����˰Ŀ��SZSMDM LIKE '0512%'����˰���������ھ��ǰ��ۼƵķ�ʽ����˰������ʱ�ڵģ�<br>
       �����ۺ��걨���걨��ҵ����˰����幤�̾�Ӫ���õ�ʱ�򣬵�ǰ����Ϊһ���ȵ�ʱ��˰������Ϊ�����1��1����12��31�գ���ǰ����Ϊ���������ļ��ȵ����ڵ�ʱ��˰������Ϊ��ǰ���ڵ����1��1������ǰ���ڵ��ϸ��µ���ĩ��<br>
     ������һ�ְ������걨��˰������ʱ�����ۼƵķ�ʽ����ģ�������ǰ����Ϊһ���ȵ�ʱ��˰������Ϊ�����1��1����12��31�գ���ǰ����Ϊ���������ļ��ȵ����ڵ�ʱ��˰������Ϊ��ǰ���ڵ����1��1������ǰ���ڵ��ϸ��µ���ĩ��������ҵ����˰���Ⱥ͸��˶��ʺϻ�����յ�ʱΪ�������ռ��ȵ�˰��˰���������ǰ����ַ�������ģ�<br>
     * @param mxList ��ϸ�����б�
     * @param sbrq ����
     * @param jsjdm ���������
     */
    /* deleted by qianchao 2005.11.2
    private void fixSpeSkssrq (String jsjdm, Date sbrq, List mxList)
    {
        //List ret = new ArrayList();
        try
        {
            //ͨ���Ǽǽӿڵõ���˰�˻�����Ϣ
            SWDJJBSJ jbsj = InterfaceDj.getJBSJ(jsjdm);
            //�õ���˰�˵Ǽ�ע�����Ͷ�Ӧ�������ʷ������
            String nwzfldm = CodeUtils.getCodeMapValue("ZHSB_DJZCLX",
                "djzclxdm", jbsj.getDjzclxdm(),
                "nwzfldm");

            if (nwzfldm != null && (nwzfldm.equals(CodeConstant.NWZFLDM_WS) ||
                                    nwzfldm.equals(CodeConstant.NWZFLDM_GAT)))
            {
                //�õ������걨��˰����������
                //�õ���������Ϊ���ȵ�˰����������
                //�õ���ǰ����
                ///
                 // 20040104
                 // Modify by Shi Yanfeng
                 // ����Ӫҵ˰ȡ��һ������
                 //
                Date preMon = TinyTools.addMonth( -3, sbrq);
                Map semiY = Skssrq.quarterSkssrq(preMon);
                Date skssjsrq = (Date) semiY.get(Skssrq.SKSSJSRQ);
                Date skssksrq = (Date) semiY.get(Skssrq.SKSSKSRQ);

                //�����ʷ������Ϊ�۰�̨��������
                for (int i = 0; i < mxList.size(); i++)
                {
                    //����02%��˰����������
                    //Ϊÿ����ϸ���˰����������
                    SbjkmxDis mxData = (SbjkmxDis) mxList.get(i);
                    //�õ�˰��˰Ŀ����
                    String szsmdm = mxData.getSzsmdm().substring(0, 2);
                    if (szsmdm.equals("02"))
                    {
                        //˰��Ϊ02������˰����������
                        mxData.setSkssjsrq(new Timestamp(skssjsrq.getTime()));
                        mxData.setSkssksrq(new Timestamp(skssksrq.getTime()));
                    }
                }
            }
            else
            {
                //������ҵ��Ӫҵ˰�Ľ��ڱ���˰Ŀ��SZSMDM LIKE '023%'����
                //�õ������걨��˰����������
                //�õ���������Ϊ���ȵ�˰����������
                //�õ���ǰ����
                //
                 // 20040104
                 // Modify by Shi Yanfeng
                 // ������ҵ��Ӫҵ˰�Ľ��ڱ���˰Ŀȡ��һ������
                 //
                Date preMon = TinyTools.addMonth( -3, sbrq);
                Map semiY = Skssrq.quarterSkssrq(preMon);
                Date skssjsrq = (Date) semiY.get(Skssrq.SKSSJSRQ);
                Date skssksrq = (Date) semiY.get(Skssrq.SKSSKSRQ);

                //�����ʷ������Ϊ�۰�̨��������
                for (int i = 0; i < mxList.size(); i++)
                {
                    //����02%��˰����������
                    //Ϊÿ����ϸ���˰����������
                    SbjkmxDis mxData = (SbjkmxDis) mxList.get(i);
                    //�õ�˰��˰Ŀ����
                    String szsmdm = mxData.getSzsmdm().substring(0, 3);
                    if (szsmdm.equals("023"))
                    {
                        //˰��Ϊ02������˰����������
                        mxData.setSkssjsrq(new Timestamp(skssjsrq.getTime()));
                        mxData.setSkssksrq(new Timestamp(skssksrq.getTime()));
                    }
                }

            }
            //return ret;
        }
        catch (Exception ex)
        {
            //return mxList;
            ex.printStackTrace();
        }

    }
    */

    /**
     * ���ۺ��걨��ʱ��������˰�ֵ�˰���������ڼ��㷽�������⣺<br>
      1.���걨����˰���ĵǼ�ע�����Ͷ�Ӧ�������ʷ����йصģ�<br>
     ���ʵ�Ӫҵ˰(SZSMDM LIKE '02%' )��<br>
     ���Ǽ�ע�����ʹ����Ӧ�������ʷ���Ϊ1��2��ʱ�򣬼�Ϊ���ʵ�ʱ��˰��02������Ϊ��һ��İ����걨��˰������ʱ�ڣ�����ǰ�걨����Ϊһ�����е�����ʱ��˰������Ϊ�����1��1����12��31�գ���ǰ����Ϊ���������ļ��ȵ����ڵ�ʱ��˰������Ϊ��ǰ�������ڼ��ȵ��ϸ����ȵ��³����ϸ����ȵ���ĩ��<br>
     ������ҵ��Ӫҵ˰�Ľ��ڱ���˰Ŀ��SZSMDM LIKE '023%'����<br>
     ���Ǽ�ע�����ʹ���Ϊ���ʵ�ʱ�����˰ĿΪ023������˰���������ڰ�һ��İ����걨���㣬����ǰ����Ϊһ���ȵ�ʱ��˰������Ϊ�����1��1����12��31�գ���ǰ����Ϊ���������ļ��ȵ����ڵ�ʱ��˰������Ϊ��ǰ�������ڼ��ȵ��ϸ����ȵ��³����ϸ����ȵ���ĩ��<br>
      2.���걨����˰����˰�Ѻ˶������շ�ʽ�Լ��걨��˰��˰Ŀ�йصģ�<br>
       ���˶��ʺϻ���ҵ�����շ�ʽΪ�˶����յ����걨���幤�̾�Ӫ����˰Ŀ��SZSMDM LIKE '0512%'����˰���������ھ��ǰ�һ��İ����걨����˰���������ڵģ�<br>
     ��ҵ����˰���ȣ�SZSMDM LIKE '30%'���͸��˶��ʺϻ�����շ�ʽΪ�������յ����걨���幤�̾�Ӫ����˰Ŀ��SZSMDM LIKE '0512%'����˰���������ھ��ǰ��ۼƵķ�ʽ����˰������ʱ�ڵģ�<br>
     �����ۺ��걨���걨��ҵ����˰����幤�̾�Ӫ���õ�ʱ�򣬵�ǰ����Ϊһ���ȵ�ʱ��˰������Ϊ�����1��1����12��31�գ���ǰ����Ϊ���������ļ��ȵ����ڵ�ʱ��˰������Ϊ��ǰ���ڵ����1��1������ǰ���ڵ��ϸ��µ���ĩ��<br>
      ������һ�ְ������걨��˰������ʱ�����ۼƵķ�ʽ����ģ�������ǰ����Ϊһ���ȵ�ʱ��˰������Ϊ�����1��1����12��31�գ���ǰ����Ϊ���������ļ��ȵ����ڵ�ʱ��˰������Ϊ��ǰ���ڵ����1��1������ǰ���ڵ��ϸ��µ���ĩ��������ҵ����˰���Ⱥ͸��˶��ʺϻ�����յ�ʱΪ�������ռ��ȵ�˰��˰���������ǰ����ַ�������ģ�<br>
     * @param mxList ��ϸ�����б�
     * @param jsjdm ���������
     * @param sbrq ����
     */

    /* deleted by qianchao 2005.11.2
    private void fixSpeSkssrq2 (String jsjdm, Date sbrq, List mxList)
    {
        //List ret = new ArrayList();
        try
        {
            //ͨ��˰�ѽӿڵõ��������շ�ʽ
            Grzsfs grzsfs = InterfaceSf4Sb.getGrzsfsInfo(jsjdm, sbrq);
            //�õ������걨��˰����������
            //�õ���������Ϊ���ȵ�˰����������
            //�õ���ǰ����
            if (grzsfs != null
                && grzsfs.getZsfsdm().equals(CodeConstant.ZSFSDM_HDZS))
            {
                ///
                 // 20040104
                 // Modify by Shi Yanfeng
                 // ���˶��ʺϻ���ҵ�����շ�ʽΪ�˶����յ�ȡ��һ������
                 //
                Date preMon = TinyTools.addMonth( -3, sbrq);
                //���˶��ʺϻ���ҵ�����շ�ʽΪ�˶����յ�
                Map semiY = Skssrq.quarterSkssrq(preMon);
                Date skssjsrq = (Date) semiY.get(Skssrq.SKSSJSRQ);
                Date skssksrq = (Date) semiY.get(Skssrq.SKSSKSRQ);

                //�����ʷ������Ϊ�۰�̨��������
                for (int i = 0; i < mxList.size(); i++)
                {
                    //����02%��˰����������
                    //Ϊÿ����ϸ���˰����������
                    SbjkmxDis mxData = (SbjkmxDis) mxList.get(i);
                    //�õ�˰��˰Ŀ����
                    String szsmdm = mxData.getSzsmdm().substring(0, 4);
                    if (szsmdm.equals("0512"))
                    {
                        //�걨���幤�̾�Ӫ����˰Ŀ��
                        mxData.setSkssjsrq(new Timestamp(skssjsrq.getTime()));
                        mxData.setSkssksrq(new Timestamp(skssksrq.getTime()));
                    }
                }
            }
            if (grzsfs != null &&
                grzsfs.getZsfsdm().equals(CodeConstant.ZSFSDM_CZZS_GR))
            {
                //���˶��ʺϻ���ҵ�����շ�ʽΪ�������յ�
                String quarter = Skssrq.curQuarter(sbrq);
                Map semiY = Skssrq.yearSkssrq(sbrq);
                Date skssjsrq = new Date();
                Date skssksrq = new Date();
                if (quarter.equals("1"))
                {
                    //һ����ȡ��һ����
                    Map yy = Skssrq.yearSkssrq(sbrq);
                    skssjsrq = (Date) yy.get(Skssrq.SKSSJSRQ);
                    skssksrq = (Date) yy.get(Skssrq.SKSSKSRQ);
                }
                else
                {
                    //��һ����
                    Map m = Skssrq.monthSkssrq(sbrq);
                    //ȡ����1��1�պ�����ĩ
                    skssksrq = TinyTools.addYear(1,
                                                 (Date) semiY.get(Skssrq.
                        SKSSKSRQ));
                    skssjsrq = (Date) m.get(Skssrq.SKSSJSRQ);
                }

                //�����ʷ������Ϊ�۰�̨��������
                for (int i = 0; i < mxList.size(); i++)
                {

                    //����02%��˰����������
                    //Ϊÿ����ϸ���˰����������
                    SbjkmxDis mxData = (SbjkmxDis) mxList.get(i);
                    //�õ�˰��˰Ŀ����
                    if (mxData.getSzsmdm().substring(0, 2).equals("30") ||
                        mxData.getSzsmdm().substring(0, 4).equals("0512"))
                    {
                        //����ҵ����˰����幤�̾�Ӫ����
                        mxData.setSkssjsrq(new Timestamp(skssjsrq.getTime()));
                        mxData.setSkssksrq(new Timestamp(skssksrq.getTime()));
                    }
                }
            }

        }
        catch (Exception ex)
        {

        }

    }
    */
   
    /**
     * ���˰��˰Ŀ������ʾ�������ƺ�ֵ  ChangeBy Tujunbing 2012-08-30
     */
    private String getSzsmtskzsJs(){
    	StringBuffer returnJsStr = new StringBuffer();
    	String szsmdm_ts_name = "var szsmdm_ts_name = ['szsmdm','nowdate','tsny','tsksrq','tsjsrq','zxksrq','zxjsrq'];\n";
    	StringBuffer  szsmdm_ts_value = new StringBuffer();  	
        //�������ݿ�����
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            //������ݿ�����
            conn = SfDBResource.getConnection();
            SfDBAccess da = new SfDBAccess(conn);           
            //��ȡ--˰��˰Ŀ��ʾ�������ñ��е�����
            String querySQL ="select szsmdm,to_char(sysdate, 'yyyymmdd') nowdate,tsny,to_char(tsksrq,'yyyymmdd') tsksrq,to_char(tsjsrq,'yyyymmdd') tsjsrq,to_char(zxksrq,'yyyymmdd') zxksrq,to_char(zxjsrq,'yyyymmdd') zxjsrq from dmdb.sb_dm_szsmtskzszb where zxbs <> '1'";
            ps = conn.prepareStatement(querySQL);
            rs = ps.executeQuery();
            
            szsmdm_ts_value.append("var szsmdm_ts_value = [");
            while(rs.next()){
            	szsmdm_ts_value.append("[");
            	szsmdm_ts_value.append("'"+rs.getString("szsmdm")+"',");
            	szsmdm_ts_value.append("'"+rs.getString("tsny")+"',");
            	szsmdm_ts_value.append("'"+rs.getString("nowdate")+"',");
            	/*
              	if(rs.getString("tsny") == null || "null".equals(rs.getString("tsny")) || "".equals(rs.getString("tsny"))){
              		szsmdm_ts_value.append("'',");
              	}else{
              		szsmdm_ts_value.append("'"+rs.getString("tsny")+"',");
              	} */
            	if(rs.getString("tsksrq") == null || "null".equals(rs.getString("tsksrq")) || "".equals(rs.getString("tsksrq"))){
            		szsmdm_ts_value.append("'',");
            	}else{
            		szsmdm_ts_value.append("'"+rs.getString("tsksrq")+"',");
            	}
            	if(rs.getString("tsjsrq") == null || "null".equals(rs.getString("tsjsrq")) || "".equals(rs.getString("tsjsrq"))){
            		szsmdm_ts_value.append("'',");
            	}else{
            		szsmdm_ts_value.append("'"+rs.getString("tsjsrq")+"',");
            	}
            	if(rs.getString("zxksrq") == null || "null".equals(rs.getString("zxksrq")) || "".equals(rs.getString("zxksrq"))){
            		szsmdm_ts_value.append("'',");
            	}else{
            		szsmdm_ts_value.append("'"+rs.getString("zxksrq")+"',");
            	}
            	if(rs.getString("zxjsrq") == null || "null".equals(rs.getString("zxjsrq")) || "".equals(rs.getString("zxjsrq"))){
            		szsmdm_ts_value.append("''");
            	}else{
            		szsmdm_ts_value.append("'"+rs.getString("zxjsrq")+"'");
            	}           	
            	szsmdm_ts_value.append("],");
            	
            }
            
            szsmdm_ts_value.append(" ];");
            //System.out.println("szsmdm_ts_value.toString()------------->"+szsmdm_ts_value.toString());           
            String repalceStr_old = ", ];";
            String repalceStr_new = "];";
            
            if(szsmdm_ts_value.toString().endsWith(repalceStr_old)){
            	int index = szsmdm_ts_value.lastIndexOf(repalceStr_old);
            	szsmdm_ts_value = szsmdm_ts_value.replace(index, szsmdm_ts_value.length(), repalceStr_new);
            }
            //System.out.println("szsmdm_ts_value.toString()------------->"+szsmdm_ts_value.toString());
            returnJsStr.append(szsmdm_ts_name);
            returnJsStr.append(szsmdm_ts_value);
            //System.out.println("returnJsStr.toString()------------->"+returnJsStr.toString());
            
            //�ͷ�ռ����Դ
            rs.close();
            ps.close();
        }catch(Exception e){
        	e.printStackTrace();
        }finally{
        	SfDBResource.freeConnection(conn);
        }
    	return returnJsStr.toString();
    }
     
    /**
     * @Description: TODO ����Ƿ��ǵ���˰��
     * @param vo
     * @return
     * @throws BaseException 
     */
    private Boolean doCheckCurrent(VOPackage vo) throws BaseException
    {
    	boolean result = false;
    	 ZhsbActionForm form = (ZhsbActionForm) vo.getData();
    	 String jsjdm = form.getJsjdm();
    	 SWDJJBSJ jbsj;
		try {
			jbsj = InterfaceDj.getJBSJ_New2(jsjdm);
		
    	 String djzclxdm = jbsj.getDjzclxdm();					//�Ǽ�ע������
    	 
    	 Map jmMap = form.getJmMap();
    	 String szsmdm = (String) jmMap.get("szsmdm");			//˰��˰Ŀ
    	 
    	 String skssrqq = (String) jmMap.get("skssksrq");		//˰������������
    	 String skssrqz = (String) jmMap.get("skssjsrq");		//˰����������ֹ
    	 String zqlxdm = "07";									//�������ʹ���
    	 int i_lxdm = form.getCheck_jmLx();
    	 if(i_lxdm==1)
    	 {
    		 zqlxdm = "06";
    	 }
    	 result = checkIsCurrentMoney(szsmdm ,djzclxdm ,zqlxdm ,skssrqq ,skssrqz);
    	 
		} catch (Exception e) {
			throw ExceptionUtil.getBaseException(e);
		}
		
		Boolean rseult_B = new Boolean(result);
    	return rseult_B;
    }
    
    /**
     * @Description: TODO ����Ƿ��ǵ���˰��
     * @return
     * @throws BaseException 
     */
    private boolean checkIsCurrentMoney(String szsmdm ,String djzclxdm, String zqlxdm ,String skssrqq ,String skssrqz) throws BaseException
    {
    	boolean result = false;
    	
    	try {
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
    		String nowString =sdf.format(new Date());
    		
    		String querySQL = "select  *   from sbdb.sb_jl_zqrl where szsmdm = ?   and djzclxdm = ?   and zqqsrq <= to_date(?, 'yyyyMM')   and zqzzrq >= to_date(?, 'yyyyMM')   and zqlxdm = ?   and zqssrqq <= to_date(?,'yyyymmdd')   and zqssrqz >= to_date(?,'yyyymmdd') ";
    		
    		
			Connection conn = SfDBResource.getConnection();
			PreparedStatement ps = conn.prepareStatement(querySQL);
			
			ps.setString(1, szsmdm);
			ps.setString(2, djzclxdm);
			ps.setString(3, nowString);
			ps.setString(4, nowString);
			ps.setString(5, zqlxdm);
			ps.setString(6, skssrqq);
			ps.setString(7, skssrqz);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				result = true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw ExceptionUtil.getBaseException(e);
		}
    	
    	return result;
    	
    }
    
}
