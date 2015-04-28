/*
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.gtgsh.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.ttsoft.bjtax.dj.model.QYRY;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.jikuai.zwcl.inf.JKAdapter;
import com.ttsoft.bjtax.jikuai.zwcl.vo.page.Yskm;
import com.ttsoft.bjtax.pzgl.proxy.ServiceProxy;
import com.ttsoft.bjtax.sfgl.common.Constant;
import com.ttsoft.bjtax.sfgl.common.code.CodeManager;
import com.ttsoft.bjtax.sfgl.common.constant.CodeConstants;
import com.ttsoft.bjtax.sfgl.common.db.util.BeanUtil;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBUtils;
import com.ttsoft.bjtax.sfgl.common.model.Cftsyhd;
import com.ttsoft.bjtax.sfgl.common.model.Grtszygsde;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.sfgl.common.util.SfStringUtils;
import com.ttsoft.bjtax.sfgl.common.util.Debug;
import com.ttsoft.bjtax.sfgl.model.orobj.Dqdedlmx1;
import com.ttsoft.bjtax.sfgl.model.orobj.Tszslmx;
import com.ttsoft.bjtax.shenbao.model.domain.Gtgshwszmx;
import com.ttsoft.bjtax.shenbao.model.domain.Gtgshwszz;
import com.ttsoft.bjtax.shenbao.model.domain.Szsm;
import com.ttsoft.bjtax.shenbao.model.domain.Zqrl;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.gtgsh.web.GtgshWszlrForm;
import com.ttsoft.bjtax.smsb.model.client.SbjkmxDis;
import com.ttsoft.bjtax.smsb.model.client.Ysjc;
import com.ttsoft.bjtax.smsb.util.EArray;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.InterfaceSf4Sb;
import com.ttsoft.bjtax.smsb.util.JksUtil;
import com.ttsoft.bjtax.smsb.util.Skssrq;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.bjtax.smsb.zhsb.web.ZhsbActionForm;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.StringUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: ���幤�̻���˰֤¼��</p>
 * @author �������飭��½��
 * @version 1.1
 */
public class GtgshWszlrProcessor
    implements Processor
{
    /**
     * �洢�����������ڶ���͸���˰��һ��MAP
     */
    private Map CDF = null;

    public GtgshWszlrProcessor ()
    {
    }

    /**
     * ͨ�ô������ģ��
     * @param vo  ���ݴ���ֵ����
     * @return  ���ݽ������[ActionForm]
     * @throws com.ttsoft.framework.exception.BaseException
     */
    public Object process (VOPackage vo)
        throws BaseException
    {
        Object result = null;

            /**@todo Implement this com.ttsoft.framework.processor.Processor method*/
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
            case CodeConstant.SMSB_ZHSB_INITLIST:
                return getInitList(vo);
            default:
                throw new ApplicationException(
                    "ActionType�д���processor���Ҳ�����Ӧ�ķ���.");
        }
        return result;
    }

    /**
     * ҳ���ʼ��
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return ��ǰҳ���Ӧ��Form����
     * @throws BaseException
     */
    private Object doShow (VOPackage vo)
        throws BaseException
    {
        List dataList = new ArrayList();

        Connection conn = null;
        SfDBUtils sfDB = null;
        Timestamp nowTime = new Timestamp(System.currentTimeMillis());
        GtgshWszlrForm pf = new GtgshWszlrForm();
        pf = (GtgshWszlrForm) vo.getData();

        try
        {
            pf.setLrrq(SfDateUtil.getDate());
            //UserData����
            UserData ud = (UserData) vo.getUserData();
            //�ӵǼǽӿ��л����Ϣ
            SWDJJBSJ dj = new SWDJJBSJ();
            try
            {
                dj = (SWDJJBSJ) InterfaceDj.getJBSJ(pf.getJsjdm(), ud);
            }
            catch (Exception ex5)
            {
                ex5.printStackTrace();
                throw ExceptionUtil.getBaseException(ex5);
            }
            pf.setSwjgzzjgdm(dj.getSwjgzzjgdm());
            pf.setSwjgzzjgmc(dj.getSwjgzzjgmc());

        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
        finally
        {
        }
        return pf;
    }

    /**
     * ��ѯ
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return ��ǰҳ���Ӧ��Form����
     * @throws BaseException
     */
    private Object doQuery (VOPackage vo)
        throws BaseException
    {
        List dataList = new ArrayList();

        Connection conn = null;
        SfDBUtils sfDB = null;
        GtgshWszlrForm pf = new GtgshWszlrForm();
        pf = (GtgshWszlrForm) vo.getData();

        try
        {
            conn = SfDBResource.getConnection();
            sfDB = new SfDBUtils(conn);
            //UserData����
            UserData ud = (UserData) vo.getUserData();
            //�ӵǼǽӿ��л����Ϣ
            HashMap mapDJ = new HashMap();
            try
            {
                mapDJ = (HashMap) InterfaceDj.getDjInfo_New(pf.getNsrjsjdm(), ud);
            }
            catch (Exception ex5)
            {
                ex5.printStackTrace();
                throw ExceptionUtil.getBaseException(ex5);
            }
            //������Ϣ
            SWDJJBSJ dj = new SWDJJBSJ();
            try
            {
                dj = (SWDJJBSJ) mapDJ.get("JBSJ");
                pf.setNsrmc(dj.getNsrmc());
                pf.setSwjgzzjgdm2(dj.getSwjgzzjgdm());
                //Modified by lufent 20003-11-26
                pf.setGjbzhydm(CodeConstant.ZRR_JKS_GJBZHYDM); //���ұ�׼��ҵ���� 8190
                pf.setDjzclxdm(dj.getDjzclxdm()); //�Ǽ�ע�����ʹ���
                //pf.setDjzclxmc(dj.getDjzclxmc()); //
                pf.setDz(dj.getJydz()); //��ַ����Ӫ��ַ
                //������˰��״̬
                pf.setNsrzt(dj.getNsrzt());
            }
            catch (Exception ex1)
            {
                pf.setNsrmc("");
                pf.setSwjgzzjgdm2("");
                pf.setGjbzhydm(""); //���ұ�׼��ҵ����
                pf.setDjzclxdm(""); //�Ǽ�ע�����ʹ���
                pf.setDz(""); //��ַ����Ӫ��ַ
                ex1.printStackTrace();
                throw new ApplicationException("����˰�˻�����Ϣ��ȫ��");
            }

            if ( (!dj.getDjzclxdm().equals(CodeConstant.DJZCLXDM_GTGSH)) &&
                (!dj.getDjzclxdm().equals(CodeConstant.DJZCLXDM_GRHH)))
            {
                //changed by zgl,����ˡ����˺ϻ��420�������
                throw new ApplicationException("����˰�˲��Ǹ��幤�̻���");
            }

            //��ҵ��Ա��Ϣ
            QYRY qyry = new QYRY();
            try
            {
                qyry = (QYRY) mapDJ.get("QYRY");
                pf.setZjlxdm(qyry.getZjlxdm()); //֤�����ʹ���
                //pf.setZjlxmc(""); //֤����������
                pf.setZjhm(qyry.getZjhm()); //֤������
            }
            catch (Exception ex1)
            {
                pf.setZjlxdm(""); //֤�����ʹ���
                pf.setZjhm(""); //֤������
                ex1.printStackTrace();
                throw new ApplicationException("����˰����ҵ��Ϣ��ȫ��");
            }

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
        finally
        {
            SfDBResource.freeConnection(conn);
        }

        return pf;
    }

    /**
     * ����
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return ��ǰҳ���Ӧ��Form����
     * @throws BaseException
     */
    private Object doSave (VOPackage vo)
        throws BaseException
    {
        List dataList = new ArrayList();
        Timestamp nowTime = new Timestamp(System.currentTimeMillis());
        String pzzldm = ""; //Ʊ֤�������
        String zhdm = ""; //�ʻ�����
        String wszh = ""; //��˰֤��
        String ndzb = ""; //����ֱ�
        String wszxh = ""; //���
        String nsrjsjdm = "";
        String nsrmc = "";
        String swjgzzjgdm = "";
        String qxdm = ""; // ���ش���

        String names[] =
            {
            "nsrjsjdm", "zjhm", "zjlxdm", "swjgzzjgdm",
            "wszh", "pzzldm", "hjsjje",
            "lrr", "swjgzzjgdm2", "gjbzhydm", "djzclxdm"};
        //remove ˰�������֯�������� ,"nsrmc","swjgzzjgmc" Modified by lufeng 20031031

        Connection conn = null;
        SfDBUtils sfDB = null;
        //from����
        GtgshWszlrForm pf = new GtgshWszlrForm();
        pf = (GtgshWszlrForm) vo.getData();
        zhdm = pf.getZhdm();
        pzzldm = pf.getPzzldm();
        nsrjsjdm = pf.getNsrjsjdm();
        nsrmc = pf.getNsrmc();

        swjgzzjgdm = pf.getSwjgzzjgdm();

        UserData ud = (UserData) vo.getUserData(); //�ĵ���ǰ�û�����
        //ormapping����
        Gtgshwszz orObjz = new Gtgshwszz();
        dataList = (List) pf.getDataList();
        try
        {
            conn = SfDBResource.getConnection();
            SfDBAccess da = new SfDBAccess(conn);

            qxdm = InterfaceDj.getQxdm(ud); //�õ����ش���
            //�Ǽ�ע�����ʹ���͹��ұ�׼��ҵ���� ����Ϊ�գ�by lufeng 20031031
            if (pf.getGjbzhydm().equals(""))
            {
                //Modified by lufent 20003-11-26
                pf.setGjbzhydm(CodeConstant.ZRR_JKS_GJBZHYDM); //���ұ�׼��ҵ���� 8190
            }
            if (pf.getDjzclxdm().equals(""))
            {
                throw new ApplicationException("�Ǽ�ע�����ʹ��벻��Ϊ�գ�");
            }
            //������
            JksUtil ju = new JksUtil();
            try
            {
                wszxh = ju.getSequenceOfWSZXH(conn);
            }
            catch (Exception ex)
            {
                throw new ApplicationException("��ȡ���кų���");
            }

            //�����˰֤��
            try
            {
                String retResult = ServiceProxy.getNumber(ud, pzzldm,
                    StringUtil.getDouble(pf.
                                         getHjsjje(),
                                         StringUtil.getDouble(pf.getHjsjje(), 0)),
                    "1", "1");
                //Ʊ֤�ӿ��޸ģ�//Modified by lufeng 2003-12-01
                ndzb = retResult.substring(0, 4); //����ֱ�
                wszh = retResult.substring(4); //��˰֤��
            }
            catch (Exception ex1)
            {
                ex1.printStackTrace();
                throw new ApplicationException("��ȡ��˰֤ʧ�ܣ������Ƿ��п��õ���˰֤�ţ�");
            }

            pf.setWszh(wszh);
            pf.setNdzb(ndzb);
            try
            {
                BeanUtil.copyBeanToBean(names, pf, orObjz);
            }
            catch (Exception ex2)
            {
                throw new ApplicationException("��ʽ��������Ϣ����");
            }
            //����ĳЩֵ
            orObjz.setWszxh(wszxh); //���
            orObjz.setWszh(wszh);
            orObjz.setCjrq(nowTime); //��������
            orObjz.setLrrq(nowTime); //¼������
            orObjz.setQxdm(qxdm); //���ش���
            orObjz.setJsjdm(pf.getJsjdm()); //���������
            orObjz.setClbjdm(CodeConstant.SMSB_WSZ_UNPRINT); //�����Ǵ��룬δ��ӡ 0
            orObjz.setFsdm(CodeConstant.FSDM_SMSB); //�Ǽ��걨��ʽ���� 1
            //�������
            orObjz.setNd(String.valueOf(SfDateUtil.getYear(SfDateUtil.getDate())));
            orObjz.setNdzb(ndzb); //����ֱ�
            //��������
            try
            {
                da.insert(orObjz);
            }
            catch (BaseException ex1)
            {
                ex1.printStackTrace();
                throw new ApplicationException("����������Ϣ����");
            }

            //������ϸ��
            for (int i = 0; i < dataList.size(); i++)
            {
                //��ʼ����ϸ��
                Gtgshwszmx orObjmx = new Gtgshwszmx();
                HashMap map = (HashMap) dataList.get(i);
                try
                {
                    BeanUtil.populate(orObjmx, map);
                }
                catch (Exception ex3)
                {
                    throw new ApplicationException("��ʽ����ϸ����Ϣ����");
                }
                //��������ֵ����ṹ�����⣬���޸ĺ����޸Ĵ˴�����Ϣ
                orObjmx.setWszxh(wszxh); //���
                orObjmx.setPzzldm(pzzldm);
                orObjmx.setWszh(wszh);
                orObjmx.setNsrjsjdm(nsrjsjdm);
                orObjmx.setCjrq(nowTime); //��������
                orObjmx.setLrrq(nowTime); //¼������
                orObjmx.setQxdm(qxdm); //���ش���
                //orObjmx.setNsrmc(nsrmc);
                orObjmx.setJsjdm(pf.getJsjdm()); //���������
                orObjmx.setSwjgzzjgdm(swjgzzjgdm);
                orObjmx.setJzbz(CodeConstant.SMSB_JZBZ); //���˱�־��Ĭ������0
                //�������
                orObjmx.setNd(String.valueOf(SfDateUtil.getYear(SfDateUtil.
                    getDate())));
                orObjmx.setNdzb(ndzb); //����ֱ�
                //���Ԥ�㼶�� //Modified by lufeng 2003-11-26
                orObjmx.setYsjcdm(CodeConstant.YSJC_GTGSH); //������Ԥ�㼶�δ��룬21 �ط���

                //Modified by lufeng 2004-07-05 ��ϸ��˰�ִ�����п�
                if ( (orObjmx.getSzdm()) == null
                    || (orObjmx.getSzdm()).equals(""))
                {
                    throw new ApplicationException("˰�ִ���Ϊ�գ���������½����ҳ�棡");
                }
                //���Ԥ���Ŀ
                try
                {
                    Yskm yskm = (Yskm) JKAdapter.getInstance().getYskm(orObjmx.
                        getSzsmdm(),
                        orObjz.getDjzclxdm(),
                        orObjz.getGjbzhydm(),
                        orObjmx.getYsjcdm());
                    orObjmx.setYskmdm(yskm.getYskmdm());
                }
                catch (Exception ex4)
                {
                    throw new ApplicationException("û��Ԥ���Ŀ���룡");
                }

                if (orObjmx.getYskmdm().equals(""))
                {
                    throw new ApplicationException("Ԥ���Ŀ���벻��Ϊ�գ�");
                }

                //��������
                try
                {
                    da.insert(orObjmx);
                }
                catch (BaseException ex6)
                {
                    ex6.printStackTrace();
                    throw new ApplicationException("������ϸ����Ϣ����");
                }
            } //end of loop
        }
        catch (BaseException ex)
        {
            //���治�ɹ��������ϸո�ȡ������˰֤�ţ�
            try
            {
                wszh = ServiceProxy.setCancellation(ud,
                    pf.getPzzldm(), ndzb + wszh,
                    StringUtil.getDouble(pf.getHjsjje(),
                                         0.00),
                    "1", "0", "1");
            }
            catch (Exception ex5)
            {
                ex5.printStackTrace();
            }
            throw ExceptionUtil.getBaseException(ex);
        }
        finally
        {
            SfDBResource.freeConnection(conn);
        }
        return pf;
    }

    /**
     * ɾ��
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return ��ǰҳ���Ӧ��Form����
     * @throws BaseException
     */
    private Object doDelete (VOPackage vo)
        throws BaseException
    {
        return null;
    }

    /**
     * ����
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return ��ǰҳ���Ӧ��Form����
     * @throws BaseException
     */
    private Object doUpdate (VOPackage vo)
        throws BaseException
    {
        return null;
    }

    /**
     * �õ���ʼ��list����˰��˰Ŀlist,����˰list
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return ��ǰҳ���Ӧ��Form����
     * @throws BaseException
     */
    private Object getInitList (VOPackage vo)
        throws BaseException
    {
        //��ȡform����
        ZhsbActionForm form = (ZhsbActionForm) vo.getData();
        List ret = new ArrayList();
        Connection con = null;
        try
        {
            con = SfDBResource.getConnection();
            //�õ�˰��˰Ŀ�����б��б�͸���˰�б�
            //EArray jsArray = new EArray(con);
            /**
             * ʹ�ô��������˰��˰Ŀ�����б�
             * Shi Yanfeng
             * 20031031
             */
            EArray jsArray = new EArray();
            String tempJsStr = jsArray.getArrayByCode("szsmlist_add",
                "ZHSB_SZSMADD");
            tempJsStr +=
                jsArray.getMsgsByCode("szsmdm", "ZHSB_SZSM",
                                      new ArrayList());
            //����˰�ѽӿڴ����ڶ�����ʺ͸���˰
            List mxList = this.dealWithSfgl(form.getJsjdm(),
                                            this.getSzsmList(con),
                                            SfDateUtil.getDate(form.getSbrq()));
            //�����Ѿ��õ�����������mapΪ��ϸ�������˰����������
            Date date = new Date(); //SfDateUtil.getDate(form.getSbrq());
            if (date == null)
            {
                date = new Date();
            }
            this.addSkssrqByMap(form.getJsjdm(), mxList, date, con);
            form.setInitMxList(mxList);
            //������ϸ���ݵ�js����
            tempJsStr += this.getMxJsArray(mxList);
            tempJsStr += "\nvar szsmlist_fields = [\"szsmdm\",\"szmc\",\"szsmmc\",\"skssksrq\",\"skssjsrq\",\"kssl\",\"jsje\",\"sjse\",\"szdm\",\"sffjs\",\"szsmdm_old\",\"asljbs\",\"sl\"];";
            tempJsStr += "\n"+this.getSzsmtskzsJs();
            form.setScriptStr(tempJsStr);
            //���ø�֪�����б�
            return form;

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            throw new ApplicationException("�õ�˰��˰Ŀ�����б��б�͸���˰�б���Ϣ����");
        }
        finally
        {
            SfDBResource.freeConnection(con);
        }

    }

    /**
     * �õ�˰��˰Ŀ
     * @param con �������Ӷ���
     * @return ˰��˰Ŀ������list
     * @throws BaseException
     */
    private List getSzsmList (Connection con)
        throws BaseException
    {
        List ret = new ArrayList();
        ret = CodeManager.getCodeList("ORSZSM", CodeConstants.CODE_MAP_ORLIST).
              getRecords();
        return ret;
    }

    /**
     * ����˰�ѹ����϶��������˰��˰Ŀ����<br>
     * ���ݶ��ڶ������ݺ�Ӫҵ˰����˰���ݴ���˰��˰Ŀlist
     * @param jsjdm ���幤�̻�����
     * @param szsmList ˰��˰Ŀ����list
     * @param sbrq �걨����
     * @return �����ĸ���˰��˰Ŀ��Ӧ����ϸlist
     * @throws java.lang.Exception
     */
    private List dealWithSfgl_old (String jsjdm, List szsmList, Date sbrq)
        throws
        Exception
    {
        //��Ӵ�������ݵ���ϸlist
        List mxList = new ArrayList();
        //˰�ѹ���ӿ�
        try
        {
            //�õ���������Ϊ�µ�˰����������
            Map semiY = Skssrq.monthSkssrq(sbrq);
            Date skssjsrq = (Date) semiY.get(Skssrq.SKSSJSRQ);
            Date skssksrq = (Date) semiY.get(Skssrq.SKSSKSRQ);
            /**
             * ͨ�������������ڶ���͸���˰��һ�Ľӿڵõ��������
             *
             */
            Map cdfMap = this.getCDFSet(jsjdm, sbrq, skssksrq, skssjsrq);

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
            //������˰�����ڶ�����������
            for (int i = 0; i < mxList.size(); i++)
            {
                SbjkmxDis mxData = (SbjkmxDis) mxList.get(i);
                // ����˰�ѹ����еĸ���˰˰��
                String szsmdm = mxData.getSzsmdm();
                if (mxData.getSffjs() != null && mxData.getSffjs().equals("2") &&
                    fjsInfo != null)
                {
                    //˰Ŀ��Ӫҵ˰����˰��������˰��ȡ�ú˶���Ϣ
                    Tszslmx tszslmx = (Tszslmx) fjsInfo.get(szsmdm.substring(0,
                        2));
                    if (tszslmx != null)
                    {
                        mxData.setSl(tszslmx.getSl());
                    }
                }
                // ����˰�ѹ����еĶ��ڶ���
                if (dqdeInfo != null)
                {
                    Dqdedlmx1 dqde = (Dqdedlmx1) dqdeInfo.get(szsmdm);
                    if (dqde != null)
                    {
                        if (dqde.getZsfsdm().equals("01"))
                        {
                            //���շ�ʽΪ����
                            mxData.setSjse(dqde.getYnsrd());
                            mxData.setJsje(dqde.getYnsrd());
                            mxData.setFromDqde(true);
                        }
                    }
                }
                // ��������
                if (cftInfo != null)
                {
                    Cftsyhd cft = (Cftsyhd) cftInfo.get(szsmdm);
                    //ʵ�ɽ��
                    if (cft != null)
                    {
                        BigDecimal money = cft.getSjje();
                        mxData.setSjse(money);
                        mxData.setJsje(money);
                        mxData.setKssl(cft.getKssl());
                    }
                }
                //����н����050110
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

            }
            //��������
            //mxList = this.dealWithSfgl4SY(jsjdm,mxList,sbrq);
            //�����ȵ�
            //mxList = this.dealWithSfgl4Q(jsjdm,mxList,sbrq);
            //�����·ݵ�
            //mxList = this.dealWithSfgl4M(jsjdm,mxList,sbrq);

            return mxList;
        }
        catch (BaseException ex)
        {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex, "����˰��˰Ŀ�б�ʧ��");
        }

    }

    /**
     * ����˰�ѹ����϶����������������Ϊ�µ�˰��˰Ŀ����<br>
     * �������ʹ��룱�����ꡢ��������ꡢ�������ȡ�12�����£�
     * ���ݶ��ڶ������ݺ�Ӫҵ˰����˰���ݴ���˰��˰Ŀlist��
     * ���ش����ĸ���˰��˰Ŀ��Ӧ����ϸlist
     * @param jsjdm ���������
     * @param szsmList ˰��˰Ŀlist
     * @param sbrq �걨����
     * @return �����ĸ���˰��˰Ŀ��Ӧ����ϸlist
     * @throws java.lang.Exception
     */
    //private List dealWithSfgl4M(String jsjdm, List szsmList, Date sbrq) throws
    private List dealWithSfgl (String jsjdm, List szsmList, Date sbrq)
        throws
        Exception
    {
        //��Ӵ�������ݵ���ϸlist
        List mxList = new ArrayList();

        try
        {
            //�õ���������Ϊ�µ�˰����������
            Map semiY = Skssrq.monthSkssrq(sbrq);
            Date skssjsrq = (Date) semiY.get(Skssrq.SKSSJSRQ);
            Date skssksrq = (Date) semiY.get(Skssrq.SKSSKSRQ);
            /**
             * ͨ�������������ڶ���͸���˰��һ�Ľӿڵõ��������
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
                //����������Ϊ�µ�ʱ�����˰�ѽӿڴ�����Ӧ������
                // ����˰�ѹ����еĸ���˰˰��
                String szsmdm = mxData.getSzsmdm();
                if (mxData.getSffjs() != null && mxData.getSffjs().equals("2") &&
                    fjsInfo != null)
                {
                    //˰Ŀ��Ӫҵ˰����˰��������˰��ȡ�ú˶���Ϣ
                    Tszslmx tszslmx = (Tszslmx) fjsInfo.get(szsmdm.substring(0,
                        2));
                    if (tszslmx != null)
                    {
                        mxData.setSl(tszslmx.getSl());
                    }
                }
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
                            //mxData.setSjse(dqde.getYnsrd());
                            mxData.setSjse(dqde.getYnsrd());
                            mxData.setJsje(dqde.getSjrd());
                            mxData.setFromDqde(true);

//                //���շ�ʽΪ����
//                mxData.setSjse(dqde.getYnsrd());
//                mxData.setJsje(dqde.getYnsrd());
//                mxData.setFromDqde(true);
                        }
                        else if (dqde.getZsfsdm().equals(CodeConstant.
                            ZHSB_ZSFS_DL))
                        {
                            //���շ�ʽΪ����
                            mxData.setSl(dqde.getZsl());
                        }

                    }
                }
                // ��������
                if (cftInfo != null)
                {
                    Cftsyhd cft = (Cftsyhd) cftInfo.get(szsmdm);
                    //ʵ�ɽ��
                    if (cft != null)
                    {
                        BigDecimal money = cft.getSjje();
                        //
                        mxData.setSjse(money);
                        mxData.setJsje(money);
                        mxData.setKssl(cft.getKssl());
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

            }
            return mxList;
        }
        catch (BaseException ex)
        {
            throw ExceptionUtil.getBaseException(ex, "����˰��˰Ŀ�б�ʧ��");
        }

    }

    /**
     * ����˰�ѽӿ�ȡ�ö��ڶ���˶���ͨ�����ڶ���list�õ����ڶ���map
     * @param dqdeInfo ���ڶ��������list
     * @return ���ڶ����map
     * @throws BaseException
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
     * ����˰�ѽӿڵõ�����˰�˶�
     * @param fjsInfo ����˰���ݵ�list
     * @return ����˰������map
     * @throws BaseException
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
     * ����˰�ѽӿڵõ���������Ϣ<br>
     * ��Ϊ����˰�ѽӿ�ֻ����ͨ������������˰��˰Ŀ��һȷ��<br>
     * ����Ӧ��Ϊ����������µ�˰�ѽӿڣ�ͨ�����������õ����еĳ������˶�
     * @param jsjdm ���������
     * @param date �걨����
     * @param qsrq ��ʼ����
     * @param jzrq ��ֹ����
     * @return ����������map
     */
    private Map getCDFSet (String jsjdm, Date date, Date qsrq, Date jzrq)
    {

        Map map = new HashMap();
        try
        {

            com.ttsoft.bjtax.sfgl.proxy.ServiceProxy proxy =
                new com.ttsoft.bjtax.sfgl.proxy.ServiceProxy();
            Debug.out("qsrq=" + qsrq);
            map = proxy.getCDFSet(jsjdm, date, qsrq, jzrq);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
        return map;
    }

    /**
     * ����˰�ѽӿڵõ�������List�õ�map<br>
     * ��Ϊ����˰�ѽӿ�ֻ����ͨ������������˰��˰Ŀ��һȷ��<br>
     * ����Ӧ��Ϊ����������µ�˰�ѽӿڣ�ͨ�����������õ����еĳ������˶�
     * <br>�÷���ͨ�����������õ����еĳ���������
     * @param cftList ���������ݵ�list
     * @return ���س�������map
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
            ex.printStackTrace();
            return null;
        }
        return cftMap;
    }

    /**
     * ������ϸ�б��е�˰��˰Ŀ���걨���ڡ����˰����������
     * @param jsjdm ���������
     * @param mxList ��ϸ�б�����list
     * @param rq �걨����
     * @param con ���ݿ�����
     * @return ��ϸ�б��list
     * @throws BaseException
     */
    private List addSkssrqByMap (String jsjdm, List mxList, Date rq,
                                 Connection con)
        throws BaseException
    {
        List ret = new ArrayList();
        try
        {
            //ͨ���Ǽǽӿڵõ���˰�˻�����Ϣ
            SWDJJBSJ jbsj = InterfaceDj.getJBSJ(jsjdm);

            //�õ��������ڵ�����˰��˰Ŀ˰����������
            Map zqrlMap = this.getSksssqMap(jbsj.getDjzclxdm(), new Date(), con);
            //ͨ��˰�ѽӿڻ��Ӧ��˰�ƶ�
            //Map ynsjeMap = InterfaceSf4Sb.getYnsje(jsjdm,rq);
            for (int i = 0; i < mxList.size(); i++)
            {

                //Ϊÿ����ϸ���˰����������
                SbjkmxDis mxData = (SbjkmxDis) mxList.get(i);
                //����˰����������mapΪ��ϸ�������˰����������
                Zqrl zqrl = (Zqrl) zqrlMap.get(mxData.getSzsmdm());
                if (zqrl != null)
                {
                    mxData.setSkssjsrq(zqrl.getZqssrqz());
                    mxData.setSkssksrq(zqrl.getZqssrqq());
                }
                else
                {
//          Map map = (Map) Skssrq.getSksssq(jsjdm, mxData.getSzsmdm(),
//                                           CodeConstant.SKLXDM_ZCJK,
//                                           mxData.getZqlxdm(),
//                                           rq, mxData.getSjse(),
//                                           mxData.getKssl(),
//                                           mxData.getJsje(),
//                                           ynsjeMap);
                    Map map = (Map) Skssrq.getSksssq(jsjdm, mxData.getSzsmdm(),
                        CodeConstant.SKLXDM_ZCJK,
                        mxData.getZqlxdm(),
                        rq, mxData.getSjse(),
                        mxData.getKssl(), mxData.getJsje(),
                        mxData.getJsje());
//          Map map = (Map)Skssrq.getSksssq(mxData.getSzsmdm(),
//                                CodeConstant.SKLXDM_ZCJK,
//                                mxData.getZqlxdm(),
//                                new Date());
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
            //throw ExceptionUtil.getBaseException(ex, "ϵͳ�����������Ա��ϵ!");
            throw new ApplicationException("��ȡ˰��˰Ŀ���걨���ڡ����˰���������ڳ���");
        }
    }

    /**
     * ������ϸ�����б�����js2ά����<br>
     * @param mxList ��ϸ����list
     * @return ��ϸ���ݵ�javascript��ά����
     */
    private String getMxJsArray (List mxList)
    {
        StringBuffer ret = new StringBuffer();
        for (int i = 0; i < mxList.size(); i++)
        {
            SbjkmxDis mxData = (SbjkmxDis) mxList.get(i);
            ret.append("[");
            ret.append("\"" + mxData.getSzsmdm() + "\",");
            ret.append("\"" + mxData.getSzmc() + "\",");
            ret.append("\"" + mxData.getSzsmmc() + "\",");
            if (mxData.getSkssksrq() != null)
            {
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
            ret.append("\"" + mxData.getKssl() + "\",");
            ret.append("\"" + mxData.getJsje() + "\",");
            ret.append("\"" + mxData.getSjse() + "\",");
            ret.append("\"" + mxData.getSzdm() + "\",");
            ret.append("\"" + mxData.getSffjs() + "\",");
            ret.append("\"" + mxData.getSzsmdm() + "\",");
            ret.append("\"" + mxData.getAsljbs() + "\",");
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
//    String clean = SfStringUtil.replaceString(ret.toString(),"null","");
//    return "var szsmlist = [" + clean;
        return "var szsmlist = [" + ret.toString();

    }

    /**
     * ����˰�ѹ����϶��������˰��˰Ŀ����<br>
     * ���ݶ��ڶ������ݺ�Ӫҵ˰����˰���ݴ���˰��˰Ŀlist
     * @param szsmList ˰��˰Ŀ����list
     * @return �����ĸ���˰��˰Ŀ��Ӧ����ϸlist
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
                temp.setAsljbs(szsm.getAsljbs());
                temp.setSl(szsm.getSl());
                temp.setZqlxdm(szsm.getZqlxdm());
                mxList.add(temp);
            }

        }
        return mxList;
    }

    /**
     * �������������õ�����˰��˰Ŀ��˰������ʱ��<br>
     * ��ǰ���ڵ��µ���������ʼ���ֹ���ڵ���
     * ���ڽ�ֹ���ڣ�1��=�޽�����<br>
     * @param djzclxdm �Ǽ�ע�����ʹ���
     * @param rq �걨����
     * @param conn ���ݿ�����
     * @return ˰��������ʼ ����ʱ�䣬�޽����ڵ�Map
     * @throws java.lang.Exception
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
            //criteria.add("szsmdm = '" + szsmdm + "'");
            criteria.add("djzclxdm='" + djzclxdm + "'");
            criteria.add("ZQQSRQ<=to_date('" + dateStr + "','yyyyMM')");
            criteria.add("ZQZZRQ>=to_date('" + dateStr + "','yyyyMM')");

            SfDBAccess db = new SfDBAccess(conn);
            ret = db.query(new Zqrl().getClass(), criteria);
            Map zqrlMap = new HashMap();
            for (int i = 0; i < ret.size(); i++)
            {
                Zqrl zqrl = (Zqrl) ret.get(i);
                if (zqrl != null)
                {
                    zqrlMap.put(zqrl.getSzsmdm(), zqrl);
                }
                else
                {

                }
            }
            return zqrlMap;
        }
        catch (Exception ex)
        {
            //throw ExceptionUtil.getBaseException(ex, "��ѯ��������ʧ��!");
            throw new ApplicationException("��ѯ��������ʧ�ܣ�");
        }
        finally
        {
            //�ͷ�����
            //SfDBResource.freeConnection(conn);
        }

    }

    /**
     * ���ݽ��ɴ�������������˰����������
     * @param mxData �걨�ɿ���ϸ������ݶ���
     * @param sbrq �걨����
     * @param jncs ���ɴ���
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
        }
    }

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
            	 * if(rs.getString("tsny") == null || "null".equals(rs.getString("tsny")) || "".equals(rs.getString("tsny"))){
            		szsmdm_ts_value.append("'',");
            	}else{
            		szsmdm_ts_value.append("'"+rs.getString("tsny")+"',");
            	}*/           	
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
}
//:-)