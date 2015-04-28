/*
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.print.processor;

import java.sql.Connection;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.pzgl.proxy.ServiceProxy;
import com.ttsoft.bjtax.sfgl.common.code.CodeUtils;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBUtils;
import com.ttsoft.bjtax.sfgl.common.util.Debug;
import com.ttsoft.bjtax.shenbao.model.domain.Gtgshwszmx;
import com.ttsoft.bjtax.shenbao.model.domain.Gtgshwszz;
import com.ttsoft.bjtax.shenbao.model.domain.Lsswszmx;
import com.ttsoft.bjtax.shenbao.model.domain.Lsswszz;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.print.web.WszPrintForm;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.Currency;
import com.ttsoft.framework.util.StringUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: ��ӡ��˰֤</p>
 * @author �������飭��½��
 * @version 1.1
 */
public class WszPrintProcessor
    implements Processor
{
    public WszPrintProcessor ()
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
            case CodeConstant.SMSB_PRINTACTION:
                result = doSuccess(vo); //��ӡ�ɹ�
                break;
            case CodeConstant.SMSB_REPRINTACTION:
                result = doReprint(vo);
                break;
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
        WszPrintForm pf = new WszPrintForm();
        try
        {
            pf = (WszPrintForm) doQuery(vo);
        }
        catch (Exception ex)
        {
            throw new ApplicationException(ex.getMessage());
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
        double hjje = 0;
        String mxSz = "";
        String mxPmmc = "";
        String mxKssl = "";
        String mxJsje = "";
        String mxSl = "";
        String mxYjhkc = "";
        String mxSjse = "";
        String mxSkssrq ="";

        WszPrintForm pf = new WszPrintForm();
        pf = (WszPrintForm) vo.getData();
        //���ø�ʽ������
        DecimalFormat deFormat = new DecimalFormat("#0.00");

        try
        {
            conn = SfDBResource.getConnection();
            SfDBAccess da = new SfDBAccess(conn);
            //UserData����
            UserData ud = (UserData) vo.getUserData();
            //�õ����ش���
            String qxdm = InterfaceDj.getQxdm(ud);
            //�ӵǼǽӿ��л����Ϣ
            SWDJJBSJ dj = (SWDJJBSJ) InterfaceDj.getJBSJ_New(pf.getJsjdm(), ud);
            if (dj == null)
            {
                throw new ApplicationException("��ȡ�Ǽ���Ϣ����");
            }
            pf.setSwjgzzjgdm(dj.getSwjgzzjgdm());
            pf.setSwjgzzjgmc(dj.getSwjgzzjgmc());
            pf.setZsjg(pf.getSwjgzzjgmc()); //֤�ջ�������
            pf.setDfswjg(pf.getSwjgzzjgmc()); //�ط�˰�����

            //���ò�ѯ����
            Vector tempVector = new Vector();
            Vector dmVector = new Vector();
            ArrayList dmList = new ArrayList();
            //���幤�̻�
            String fromPage = pf.getFromPage();
            if (fromPage.toLowerCase().indexOf("gtgsh") >= 0)
            {
                tempVector.clear();
                tempVector.addElement("qxdm='" + qxdm + "'");
                tempVector.addElement("ndzb='" + pf.getNdzb() + "'");
                tempVector.addElement("(sbhzdh is null or sbhzdh='')");
                tempVector.addElement("wszh='" + pf.getHeadWszh() + "'");
                tempVector.addElement("1=1 order by wszxh desc");
                ArrayList zbList = (ArrayList) da.query(Gtgshwszz.class,
                    tempVector);

                if (zbList.size() <= 0)
                {
                    throw new ApplicationException("û���ҵ�������������˰֤��Ϣ��");
                }
                Gtgshwszz zb = (Gtgshwszz) zbList.get(0);
                pf.setHeadWszh(zb.getWszh());
                pf.setZclx("���幤�̻�"); //ע�����ͣ���ʱд��
                pf.setTfrq( (String.valueOf(zb.getCjrq())).substring(0, 10));
                pf.setNsrjsjdm(zb.getNsrjsjdm()); //��˰�˼��������
                //�ӵǼǽӿ��л����Ϣ
                SWDJJBSJ djNsr = (SWDJJBSJ) InterfaceDj.getJBSJ_New(zb.getNsrjsjdm(),
                    ud);
                pf.setNsrmc(djNsr.getNsrmc()); //��˰������
                pf.setDz(djNsr.getJydz()); //�����˰�˾�Ӫ��ַ
                pf.setBz(djNsr.getNsrmc()); //��ע
                pf.setHjje(deFormat.format(zb.getHjsjje())); //�ϼƽ��
                pf.setHjjedx(Currency.convert(zb.getHjsjje())); //�ϼƽ���д

                //��ϸ��Ϣ
                tempVector.clear();
                tempVector.addElement("qxdm='" + qxdm + "'");
                tempVector.addElement("ndzb='" + pf.getNdzb() + "'");
                tempVector.addElement("wszh='" + pf.getHeadWszh() + "'");
                tempVector.addElement("wszxh='" + zb.getWszxh() + "'");
                tempVector.addElement("1=1 order by wszxh desc");
                ArrayList mxList = (ArrayList) da.query(Gtgshwszmx.class,
                    tempVector);
                if (mxList.size() <= 0)
                {
                    throw new ApplicationException("û���ҵ�������������˰֤��Ϣ��");
                }
                Gtgshwszmx mx = new Gtgshwszmx();

                for (int i = 0; i < mxList.size(); i++)
                {
                    mx = (Gtgshwszmx) mxList.get(i);
                    HashMap map = new HashMap();
                    if (mx.getKssl() == null)
                    {
                        map.put("kssl", " ");
                    }
                    else
                    {
                        map.put("kssl", String.valueOf(mx.getKssl()));

                    }
                    if (mx.getJsje() == null)
                    {
                        map.put("jsje", " ");
                    }
                    else
                    {
                        map.put("jsje",
                                deFormat.format(StringUtil.getDouble(String.
                            valueOf(mx.getJsje()), 0.00)));

                    }
                    if (mx.getSl() == null)
                    {
                        map.put("sl", " ");
                    }
                    else
                    {
                        map.put("sl", String.valueOf(mx.getSl()));

                    }
                    if (mx.getKssl() == null)
                    {
                        map.put("yjhkc", " ");
                    }
                    else
                    {
                        map.put("yjhkc",
                                deFormat.format(StringUtil.getDouble(String.
                            valueOf(mx.getYjhkc()), 0.00)));

                    }
                    if (mx.getSjse() == null)
                    {
                        map.put("sjse", " ");
                    }
                    else
                    {
                        map.put("sjse",
                                deFormat.format(StringUtil.getDouble(String.
                            valueOf(mx.getSjse()), 0.00)));

                    }
//                    pf.setSkssksrq(String.valueOf(mx.getSkssksrq()).substring(0,
//                        10));
//                    pf.setSkssjsrq(String.valueOf(mx.getSkssjsrq()).substring(0,
//                        10));

                    if (mx.getSkssksrq() == null)
                    {
                    	map.put("skssksrq", " ");
                    }
                    else
                    {
                    	map.put("skssksrq",
                        		String.valueOf(mx.getSkssksrq()).substring(0,10).replaceAll("-", ""));
                    }
                    
                    if (mx.getSkssjsrq() == null)
                    {
                    	map.put("skssjsrq", " ");
                    }
                    else
                    {
                    	map.put("skssjsrq",
                        		String.valueOf(mx.getSkssjsrq()).substring(0,10).replaceAll("-", ""));
                    }
                    
                    map.put("szmc",
                            CodeUtils.getCodeBeanLabel("DM_SZSM", mx.getSzdm()));
                    map.put("szsmmc",
                            CodeUtils.getCodeBeanLabel("DM_SZSM", mx.getSzsmdm()));

                    mxSz += map.get("szmc") + ";;";
                    mxPmmc += map.get("szsmmc") + ";;";
                    mxKssl += map.get("kssl") + ";;";
                    mxJsje += map.get("jsje") + ";;";
                    mxSl += map.get("sl") + ";;";
                    mxSkssrq += map.get("skssksrq") + "-"+map.get("skssjsrq") + ";;";
                    mxYjhkc += map.get("yjhkc") + ";;";
                    mxSjse += map.get("sjse") + ";;";
                    dataList.add(map);
                }
                pf.setMxSz(mxSz);
                pf.setMxPmmc(mxPmmc);
                pf.setMxKssl(mxKssl);
                pf.setMxJsje(mxJsje);
                pf.setMxSl(mxSl);
                pf.setMxSkssrq(mxSkssrq);
                pf.setMxYjhkc(mxYjhkc);
                pf.setMxSjse(mxSjse);

                pf.setDataList(dataList);
            }
            else
            { //��ɢ˰����
                tempVector.clear();
                tempVector.addElement("qxdm='" + qxdm + "'");
                tempVector.addElement("(sbhzdh is null or sbhzdh='')");
                tempVector.addElement("wszh='" + pf.getHeadWszh() + "'");
                tempVector.addElement("1=1 order by wszxh desc");
                ArrayList zbList = (ArrayList) da.query(Lsswszz.class,
                    tempVector);

                if (zbList.size() <= 0)
                {
                    throw new ApplicationException("û���ҵ�������������˰֤��Ϣ��");
                }

                Lsswszz zb = (Lsswszz) zbList.get(0);
                pf.setHeadWszh(zb.getWszh());
                pf.setZclx(""); //ע�����ͣ���ʱд��
                pf.setTfrq( (String.valueOf(zb.getCjrq())).substring(0, 10));
                pf.setNsrjsjdm(pf.getJsjdm()); //��λ�ļ��������
                pf.setNsrmc(zb.getNsrmc());
                pf.setDz(zb.getDz()); //��ɢ�Ļ�ȡ�Լ��ĵ�ַ
                pf.setBz(zb.getNsrmc()); //��ע
                pf.setHjje(deFormat.format(zb.getHjsjje())); //�ϼƽ��
                pf.setHjjedx(Currency.convert(zb.getHjsjje())); //�ϼƽ���д

                //pf.setSkssksrq(zb.gets);˰���������ڸ�����ϸ��Ϣ����
                //��ϸ��Ϣ
                tempVector.clear();
                tempVector.addElement("qxdm='" + qxdm + "'");
                tempVector.addElement("wszh='" + pf.getHeadWszh() + "'");
                tempVector.addElement("wszxh='" + zb.getWszxh() + "'");
                tempVector.addElement("1=1 order by wszxh desc");
                ArrayList mxList = (ArrayList) da.query(Lsswszmx.class,
                    tempVector);

                if (mxList.size() <= 0)
                {
                    throw new ApplicationException("û���ҵ�������������˰֤��Ϣ��");
                }

                Lsswszmx mx = new Lsswszmx();
                for (int i = 0; i < mxList.size(); i++)
                {
                    mx = (Lsswszmx) mxList.get(i);
                    HashMap map = new HashMap();
                    if (mx.getKssl() == null)
                    {
                        map.put("kssl", " ");
                    }
                    else
                    {
                        map.put("kssl", String.valueOf(mx.getKssl()));

                    }
                    if (mx.getJsje() == null)
                    {
                        map.put("jsje", " ");
                    }
                    else
                    {
                        map.put("jsje",
                                deFormat.format(StringUtil.getDouble(String.
                            valueOf(mx.getJsje()), 0.00)));

                    }
                    if (mx.getSl() == null)
                    {
                        map.put("sl", " ");
                    }
                    else
                    {
                        map.put("sl", String.valueOf(mx.getSl()));

                    }
                    if (mx.getKssl() == null)
                    {
                        map.put("yjhkc", " ");
                    }
                    else
                    {
                        map.put("yjhkc",
                                deFormat.format(StringUtil.getDouble(String.
                            valueOf(mx.getYjhkc()), 0.00)));

                    }
                    if (mx.getSjse() == null)
                    {
                        map.put("sjse", " ");
                    }
                    else
                    {
                        map.put("sjse",
                                deFormat.format(StringUtil.getDouble(String.
                            valueOf(mx.getSjse()), 0.00)));

                    }
//                    pf.setSkssksrq(String.valueOf(mx.getSkssksrq()).substring(0,
//                            10));
//                    pf.setSkssjsrq(String.valueOf(mx.getSkssjsrq()).substring(0,
//                        10));

                    if (mx.getSkssksrq() == null)
                    {
                    	map.put("skssksrq", " ");
                    }
                    else
                    {
                    	map.put("skssksrq",
                        		String.valueOf(mx.getSkssksrq()).substring(0,10).replaceAll("-", ""));
                    }
                    
                    if (mx.getSkssjsrq() == null)
                    {
                    	map.put("skssjsrq", " ");
                    }
                    else
                    {
                    	map.put("skssjsrq",
                        		String.valueOf(mx.getSkssjsrq()).substring(0,10).replaceAll("-", ""));
                    }
                    
                    map.put("szmc",
                            CodeUtils.getCodeBeanLabel("DM_SZSM", mx.getSzdm()));
                    map.put("szsmmc",
                            CodeUtils.getCodeBeanLabel("DM_SZSM", mx.getSzsmdm()));

                    mxSz += map.get("szmc") + ";;";
                    mxPmmc += map.get("szsmmc") + ";;";
                    mxKssl += map.get("kssl") + ";;";
                    mxJsje += map.get("jsje") + ";;";
                    mxSl += map.get("sl") + ";;";
                    mxSkssrq += map.get("skssksrq") + "-"+map.get("skssjsrq") + ";;";
                    mxYjhkc += map.get("yjhkc") + ";;";
                    mxSjse += map.get("sjse") + ";;";
                    dataList.add(map);
                }
                pf.setMxSz(mxSz);
                pf.setMxPmmc(mxPmmc);
                pf.setMxKssl(mxKssl);
                pf.setMxJsje(mxJsje);
                pf.setMxSl(mxSl);
                pf.setMxSkssrq(mxSkssrq);
                pf.setMxYjhkc(mxYjhkc);
                pf.setMxSjse(mxSjse);

                pf.setDataList(dataList);
            } //End of if
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
        return null;
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
     * ��ӡ�ɹ���������˰֤�Ĵ�ӡ���
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return ��ǰҳ���Ӧ��Form����
     * @throws BaseException
     */
    private Object doSuccess (VOPackage vo)
        throws BaseException
    {
        List dataList = new ArrayList();
        Connection conn = null;
        SfDBUtils sfDB = null;
        double hjje = 0;
        String wszh = "";
        String mxSz = "";
        String mxPmmc = "";
        String mxKssl = "";
        String mxJsje = "";
        String mxSl = "";
        String mxYjhkc = "";
        String mxSjse = "";
        String mxSkssrq ="";

        WszPrintForm pf = new WszPrintForm();
        pf = (WszPrintForm) vo.getData();
        //���ø�ʽ������
        DecimalFormat deFormat = new DecimalFormat("#0.00");
        Timestamp nowTime = new Timestamp(System.currentTimeMillis());
        try
        {
            conn = SfDBResource.getConnection();
            SfDBAccess da = new SfDBAccess(conn);
            //UserData����
            UserData ud = (UserData) vo.getUserData();
            //�õ����ش���
            String qxdm = InterfaceDj.getQxdm(ud);
            //�ӵǼǽӿ��л����Ϣ
            SWDJJBSJ dj = (SWDJJBSJ) InterfaceDj.getJBSJ_New(pf.getJsjdm(), ud);
            if (dj == null)
            {
                throw new ApplicationException("��ȡ�Ǽ���Ϣ����");
            }
            pf.setSwjgzzjgdm(dj.getSwjgzzjgdm());
            pf.setSwjgzzjgmc(dj.getSwjgzzjgmc());
            pf.setZsjg(pf.getSwjgzzjgmc()); //֤�ջ�������
            pf.setDfswjg(pf.getSwjgzzjgmc()); //�ط�˰�����

            //���ò�ѯ����
            Vector tempVector = new Vector();
            Vector dmVector = new Vector();
            ArrayList dmList = new ArrayList();
            /***/
            String fromPage = pf.getFromPage();
            if (fromPage.toLowerCase().indexOf("gtgsh") >= 0)
            {

                tempVector.clear();
                tempVector.addElement("qxdm='" + qxdm + "'");
                tempVector.addElement("ndzb='" + pf.getNdzb() + "'");
                tempVector.addElement("(sbhzdh is null or sbhzdh='')");
                tempVector.addElement("wszh='" + pf.getHeadWszh() + "'");
                tempVector.addElement("1=1 order by wszh desc");
                ArrayList zbList = (ArrayList) da.query(Gtgshwszz.class,
                    tempVector);

                if (zbList.size() <= 0)
                {
                    throw new ApplicationException("û���ҵ�������������˰֤��Ϣ��");
                }
                Gtgshwszz zb = (Gtgshwszz) zbList.get(0);
                pf.setHeadWszh(zb.getWszh());
                pf.setZclx("���幤�̻�"); //ע�����ͣ���ʱд��
                pf.setTfrq( (String.valueOf(zb.getCjrq())).substring(0, 10));
                pf.setNsrjsjdm(zb.getNsrjsjdm()); //��˰�˼��������
                //�ӵǼǽӿ��л����Ϣ
                SWDJJBSJ djNsr = (SWDJJBSJ) InterfaceDj.getJBSJ_New(zb.getNsrjsjdm(),
                    ud);
                pf.setNsrmc(djNsr.getNsrmc()); //��˰������
                pf.setDz(djNsr.getJydz()); //�����˰�˾�Ӫ��ַ
                pf.setBz(djNsr.getNsrmc()); //��ע
                pf.setHjje(deFormat.format(zb.getHjsjje())); //�ϼƽ��
                pf.setHjjedx(Currency.convert(zb.getHjsjje())); //�ϼƽ���д

                //��ϸ��Ϣ
                tempVector.clear();
                tempVector.addElement("qxdm='" + qxdm + "'");
                tempVector.addElement("ndzb='" + pf.getNdzb() + "'");
                tempVector.addElement("wszh='" + pf.getHeadWszh() + "'");
                tempVector.addElement("wszxh='" + zb.getWszxh() + "'");
                tempVector.addElement("1=1 order by wszh desc");
                ArrayList mxList = (ArrayList) da.query(Gtgshwszmx.class,
                    tempVector);
                if (mxList.size() <= 0)
                {
                    throw new ApplicationException("û���ҵ�������������˰֤��Ϣ��");
                }
                Gtgshwszmx mx = new Gtgshwszmx();
                for (int i = 0; i < mxList.size(); i++)
                {
                    mx = (Gtgshwszmx) mxList.get(i);
                    HashMap map = new HashMap();
                    if (mx.getKssl() == null)
                    {
                        map.put("kssl", " ");
                    }
                    else
                    {
                        map.put("kssl", String.valueOf(mx.getKssl()));

                    }
                    if (mx.getJsje() == null)
                    {
                        map.put("jsje", " ");
                    }
                    else
                    {
                        map.put("jsje",
                                deFormat.format(StringUtil.getDouble(String.
                            valueOf(mx.getJsje()), 0.00)));

                    }
                    if (mx.getSl() == null)
                    {
                        map.put("sl", " ");
                    }
                    else
                    {
                        map.put("sl", String.valueOf(mx.getSl()));

                    }
                    if (mx.getKssl() == null)
                    {
                        map.put("yjhkc", " ");
                    }
                    else
                    {
                        map.put("yjhkc",
                                deFormat.format(StringUtil.getDouble(String.
                            valueOf(mx.getYjhkc()), 0.00)));

                    }
                    if (mx.getSjse() == null)
                    {
                        map.put("sjse", " ");
                    }
                    else
                    {
                        map.put("sjse",
                                deFormat.format(StringUtil.getDouble(String.
                            valueOf(mx.getSjse()), 0.00)));

                    }
//                    pf.setSkssksrq(String.valueOf(mx.getSkssksrq()).substring(0,
//                            10));
//                    pf.setSkssjsrq(String.valueOf(mx.getSkssjsrq()).substring(0,
//                        10));

                    if (mx.getSkssksrq() == null)
                    {
                    	map.put("skssksrq", " ");
                    }
                    else
                    {
                    	map.put("skssksrq",
                        		String.valueOf(mx.getSkssksrq()).substring(0,10).replaceAll("-", ""));
                    }
                    
                    if (mx.getSkssjsrq() == null)
                    {
                    	map.put("skssjsrq", " ");
                    }
                    else
                    {
                    	map.put("skssjsrq",
                        		String.valueOf(mx.getSkssjsrq()).substring(0,10).replaceAll("-", ""));
                    }
                    
                    map.put("szmc",
                            CodeUtils.getCodeBeanLabel("DM_SZSM", mx.getSzdm()));
                    map.put("szsmmc",
                            CodeUtils.getCodeBeanLabel("DM_SZSM", mx.getSzsmdm()));

                    mxSz += map.get("szmc") + ";;";
                    mxPmmc += map.get("szsmmc") + ";;";
                    mxKssl += map.get("kssl") + ";;";
                    mxJsje += map.get("jsje") + ";;";
                    mxSl += map.get("sl") + ";;";
                    mxSkssrq += map.get("skssksrq") + "-"+map.get("skssjsrq") + ";;";
                    mxYjhkc += map.get("yjhkc") + ";;";
                    mxSjse += map.get("sjse") + ";;";
                    dataList.add(map);
                }
                pf.setMxSz(mxSz);
                pf.setMxPmmc(mxPmmc);
                pf.setMxKssl(mxKssl);
                pf.setMxJsje(mxJsje);
                pf.setMxSl(mxSl);
                pf.setMxSkssrq(mxSkssrq);
                pf.setMxYjhkc(mxYjhkc);
                pf.setMxSjse(mxSjse);

                pf.setDataList(dataList);

                //������˰֤����ӡ��־
                zb = (Gtgshwszz) zbList.get(0);
                zb.setClbjdm(CodeConstant.SMSB_WSZ_PRINT); //�Ѵ�ӡ
                try
                {
                    da.update(zb);
                }
                catch (BaseException ex1)
                {
                    ex1.printStackTrace();
                    throw new ApplicationException("������˰֤��ӡ��־����");
                }
                //End of reget wszh
            }
            else
            { //��ɢ˰����
                tempVector.clear();
                tempVector.addElement("qxdm='" + qxdm + "'");
                tempVector.addElement("ndzb='" + pf.getNdzb() + "'");
                tempVector.addElement("(sbhzdh is null or sbhzdh='')");
                tempVector.addElement("wszh='" + pf.getHeadWszh() + "'");
                tempVector.addElement("1=1 order by wszh desc");
                ArrayList zbList = (ArrayList) da.query(Lsswszz.class,
                    tempVector);

                if (zbList.size() <= 0)
                {
                    throw new ApplicationException("û���ҵ�������������˰֤��Ϣ��");
                }

                Lsswszz zb = (Lsswszz) zbList.get(0);
                pf.setHeadWszh(zb.getWszh());
                pf.setZclx(""); //ע�����ͣ���ʱд��
                pf.setTfrq( (String.valueOf(zb.getCjrq())).substring(0, 10));
                pf.setNsrjsjdm(pf.getJsjdm()); //��λ�ļ��������
                pf.setNsrmc(zb.getNsrmc());
                pf.setDz(zb.getDz()); //��ɢ�Ļ�ȡ�Լ��ĵ�ַ
                pf.setBz(zb.getNsrmc()); //��ע
                pf.setHjje(deFormat.format(zb.getHjsjje())); //�ϼƽ��
                pf.setHjjedx(Currency.convert(zb.getHjsjje())); //�ϼƽ���д

                //��ϸ��Ϣ
                tempVector.clear();
                tempVector.addElement("qxdm='" + qxdm + "'");
                tempVector.addElement("ndzb='" + pf.getNdzb() + "'");
                tempVector.addElement("wszh='" + pf.getHeadWszh() + "'");
                tempVector.addElement("wszxh='" + zb.getWszxh() + "'");
                tempVector.addElement("1=1 order by wszh desc");
                ArrayList mxList = (ArrayList) da.query(Lsswszmx.class,
                    tempVector);

                if (mxList.size() <= 0)
                {
                    throw new ApplicationException("û���ҵ�������������˰֤��Ϣ��");
                }

                Lsswszmx mx = new Lsswszmx();
                for (int i = 0; i < mxList.size(); i++)
                {
                    mx = (Lsswszmx) mxList.get(i);
                    HashMap map = new HashMap();
                    if (mx.getKssl() == null)
                    {
                        map.put("kssl", " ");
                    }
                    else
                    {
                        map.put("kssl", String.valueOf(mx.getKssl()));

                    }
                    if (mx.getJsje() == null)
                    {
                        map.put("jsje", " ");
                    }
                    else
                    {
                        map.put("jsje",
                                deFormat.format(StringUtil.getDouble(String.
                            valueOf(mx.getJsje()), 0.00)));

                    }
                    if (mx.getSl() == null)
                    {
                        map.put("sl", " ");
                    }
                    else
                    {
                        map.put("sl", String.valueOf(mx.getSl()));

                    }
                    if (mx.getKssl() == null)
                    {
                        map.put("yjhkc", " ");
                    }
                    else
                    {
                        map.put("yjhkc",
                                deFormat.format(StringUtil.getDouble(String.
                            valueOf(mx.getYjhkc()), 0.00)));

                    }
                    if (mx.getSjse() == null)
                    {
                        map.put("sjse", " ");
                    }
                    else
                    {
                        map.put("sjse",
                                deFormat.format(StringUtil.getDouble(String.
                            valueOf(mx.getSjse()), 0.00)));

                    }
//                    pf.setSkssksrq(String.valueOf(mx.getSkssksrq()).substring(0,
//                            10));
//                    pf.setSkssjsrq(String.valueOf(mx.getSkssjsrq()).substring(0,
//                        10));

                    if (mx.getSkssksrq() == null)
                    {
                    	map.put("skssksrq", " ");
                    }
                    else
                    {
                    	map.put("skssksrq",
                        		String.valueOf(mx.getSkssksrq()).substring(0,10).replaceAll("-", ""));
                    }
                    
                    if (mx.getSkssjsrq() == null)
                    {
                    	map.put("skssjsrq", " ");
                    }
                    else
                    {
                    	map.put("skssjsrq",
                        		String.valueOf(mx.getSkssjsrq()).substring(0,10).replaceAll("-", ""));
                    }
                    
                    map.put("szmc",
                            CodeUtils.getCodeBeanLabel("DM_SZSM", mx.getSzdm()));
                    map.put("szsmmc",
                            CodeUtils.getCodeBeanLabel("DM_SZSM", mx.getSzsmdm()));
                    mxSz += map.get("szmc") + ";;";
                    mxPmmc += map.get("szsmmc") + ";;";
                    mxKssl += map.get("kssl") + ";;";
                    mxJsje += map.get("jsje") + ";;";
                    mxSl += map.get("sl") + ";;";
                    mxSkssrq += map.get("skssksrq") + "-"+map.get("skssjsrq") + ";;";
                    mxYjhkc += map.get("yjhkc") + ";;";
                    mxSjse += map.get("sjse") + ";;";
                    dataList.add(map);
                }
                pf.setMxSz(mxSz);
                pf.setMxPmmc(mxPmmc);
                pf.setMxKssl(mxKssl);
                pf.setMxJsje(mxJsje);
                pf.setMxSl(mxSl);
                pf.setMxSkssrq(mxSkssrq);
                pf.setMxYjhkc(mxYjhkc);
                pf.setMxSjse(mxSjse);

                pf.setDataList(dataList);

                //������˰֤����ӡ��־
                zb = (Lsswszz) zbList.get(0);
                zb.setClbjdm(CodeConstant.SMSB_WSZ_PRINT); //�Ѵ�ӡ
                try
                {
                    da.update(zb);
                }
                catch (BaseException ex1)
                {
                    ex1.printStackTrace();
                    throw new ApplicationException("������˰֤��ӡ��־����");
                }
                //End of reget wszh
            } //End of if
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
    } //End of doSuccess

    /**
     * ȡ�Ŵ�ӡ�����ϵ�ǰ���룬ȡ���µ���˰֤����
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return ��ǰҳ���Ӧ��Form����
     * @throws BaseException
     */
    private Object doReprint (VOPackage vo)
        throws BaseException
    {
        List dataList = new ArrayList();
        Connection conn = null;
        SfDBUtils sfDB = null;
        double hjje = 0;
        String wszh = ""; //��˰֤��
        String ndzb = ""; //����ֱ�
        String mxSz = "";
        String mxPmmc = "";
        String mxKssl = "";
        String mxJsje = "";
        String mxSl = "";
        String mxYjhkc = "";
        String mxSjse = "";
        String mxSkssrq ="";

        WszPrintForm pf = new WszPrintForm();
        pf = (WszPrintForm) vo.getData();
        //���ø�ʽ������
        DecimalFormat deFormat = new DecimalFormat("#0.00");
        Timestamp nowTime = new Timestamp(System.currentTimeMillis());
        try
        {
            conn = SfDBResource.getConnection();
            SfDBAccess da = new SfDBAccess(conn);
            //UserData����
            UserData ud = (UserData) vo.getUserData();
            //�õ����ش���
            String qxdm = InterfaceDj.getQxdm(ud);
            //�ӵǼǽӿ��л����Ϣ
            SWDJJBSJ dj = (SWDJJBSJ) InterfaceDj.getJBSJ_New(pf.getJsjdm(), ud);
            if (dj == null)
            {
                throw new ApplicationException("��ȡ�Ǽ���Ϣ����");
            }
            pf.setSwjgzzjgdm(dj.getSwjgzzjgdm());
            pf.setSwjgzzjgmc(dj.getSwjgzzjgmc());
            pf.setZsjg(pf.getSwjgzzjgmc()); //֤�ջ�������
            pf.setDfswjg(pf.getSwjgzzjgmc()); //�ط�˰�����

            //���ò�ѯ����
            Vector tempVector = new Vector();
            Vector dmVector = new Vector();
            ArrayList dmList = new ArrayList();
            /***/
            String fromPage = pf.getFromPage();
            if (fromPage.toLowerCase().indexOf("gtgsh") >= 0)
            {
                tempVector.clear();
                tempVector.addElement("qxdm='" + qxdm + "'");
                //tempVector.addElement("clbjdm='" + CodeConstant.SMSB_WSZ_UNPRINT + "'");
                tempVector.addElement("ndzb='" + pf.getNdzb() + "'");
                tempVector.addElement("(sbhzdh is null or sbhzdh='')");
                tempVector.addElement("wszh='" + pf.getHeadWszh() + "'");
                tempVector.addElement("1=1 order by wszh desc");
                ArrayList zbList = (ArrayList) da.query(Gtgshwszz.class,
                    tempVector);

                if (zbList.size() <= 0)
                {
                    throw new ApplicationException("û���ҵ�������������˰֤��Ϣ��");
                }
                Gtgshwszz zb = (Gtgshwszz) zbList.get(0);
                pf.setHeadWszh(zb.getWszh());
                pf.setZclx("���幤�̻�"); //ע�����ͣ���ʱд��
                pf.setTfrq( (String.valueOf(zb.getCjrq())).substring(0, 10));
                pf.setNsrjsjdm(zb.getNsrjsjdm()); //��˰�˼��������
                //�ӵǼǽӿ��л����Ϣ
                SWDJJBSJ djNsr = (SWDJJBSJ) InterfaceDj.getJBSJ_New(zb.getNsrjsjdm(),
                    ud);
                pf.setNsrmc(djNsr.getNsrmc()); //��˰������
                pf.setDz(djNsr.getJydz()); //�����˰�˾�Ӫ��ַ
                pf.setBz(djNsr.getNsrmc()); //��ע
                pf.setHjje(deFormat.format(zb.getHjsjje())); //�ϼƽ��
                pf.setHjjedx(Currency.convert(zb.getHjsjje())); //�ϼƽ���д

                //��ϸ��Ϣ
                tempVector.clear();
                tempVector.addElement("qxdm='" + qxdm + "'");
                tempVector.addElement("ndzb='" + pf.getNdzb() + "'");
                tempVector.addElement("wszh='" + pf.getHeadWszh() + "'");
                tempVector.addElement("wszxh='" + zb.getWszxh() + "'");
                tempVector.addElement("1=1 order by wszh desc");
                ArrayList mxList = (ArrayList) da.query(Gtgshwszmx.class,
                    tempVector);
                if (mxList.size() <= 0)
                {
                    throw new ApplicationException("û���ҵ�������������˰֤��Ϣ��");
                }
                Gtgshwszmx mx = new Gtgshwszmx();
                for (int i = 0; i < mxList.size(); i++)
                {
                    mx = (Gtgshwszmx) mxList.get(i);
                    HashMap map = new HashMap();
                    if (mx.getKssl() == null)
                    {
                        map.put("kssl", " ");
                    }
                    else
                    {
                        map.put("kssl", String.valueOf(mx.getKssl()));

                    }
                    if (mx.getJsje() == null)
                    {
                        map.put("jsje", " ");
                    }
                    else
                    {
                        map.put("jsje",
                                deFormat.format(StringUtil.getDouble(String.
                            valueOf(mx.getJsje()), 0.00)));

                    }
                    if (mx.getSl() == null)
                    {
                        map.put("sl", " ");
                    }
                    else
                    {
                        map.put("sl", String.valueOf(mx.getSl()));

                    }
                    if (mx.getKssl() == null)
                    {
                        map.put("yjhkc", " ");
                    }
                    else
                    {
                        map.put("yjhkc",
                                deFormat.format(StringUtil.getDouble(String.
                            valueOf(mx.getYjhkc()), 0.00)));

                    }
                    if (mx.getSjse() == null)
                    {
                        map.put("sjse", " ");
                    }
                    else
                    {
                        map.put("sjse",
                                deFormat.format(StringUtil.getDouble(String.
                            valueOf(mx.getSjse()), 0.00)));

                    }
//                    pf.setSkssksrq(String.valueOf(mx.getSkssksrq()).substring(0,
//                            10));
//                    pf.setSkssjsrq(String.valueOf(mx.getSkssjsrq()).substring(0,
//                        10));

                    if (mx.getSkssksrq() == null)
                    {
                    	map.put("skssksrq", " ");
                    }
                    else
                    {
                    	map.put("skssksrq",
                        		String.valueOf(mx.getSkssksrq()).substring(0,10).replaceAll("-", ""));
                    }
                    
                    if (mx.getSkssjsrq() == null)
                    {
                    	map.put("skssjsrq", " ");
                    }
                    else
                    {
                    	map.put("skssjsrq",
                        		String.valueOf(mx.getSkssjsrq()).substring(0,10).replaceAll("-", ""));
                    }
                    map.put("szmc",
                            CodeUtils.getCodeBeanLabel("DM_SZSM", mx.getSzdm()));
                    map.put("szsmmc",
                            CodeUtils.getCodeBeanLabel("DM_SZSM", mx.getSzsmdm()));

                    mxSz += map.get("szmc") + ";;";
                    mxPmmc += map.get("szsmmc") + ";;";
                    mxKssl += map.get("kssl") + ";;";
                    mxJsje += map.get("jsje") + ";;";
                    mxSl += map.get("sl") + ";;";
                    mxSkssrq += map.get("skssksrq") + "-"+map.get("skssjsrq") + ";;";
                    mxYjhkc += map.get("yjhkc") + ";;";
                    mxSjse += map.get("sjse") + ";;";
                    dataList.add(map);
                }
                pf.setMxSz(mxSz);
                pf.setMxPmmc(mxPmmc);
                pf.setMxKssl(mxKssl);
                pf.setMxJsje(mxJsje);
                pf.setMxSl(mxSl);
                pf.setMxSkssrq(mxSkssrq);
                pf.setMxYjhkc(mxYjhkc);
                pf.setMxSjse(mxSjse);

                pf.setDataList(dataList);

                //����ȡ��Ȼ���ӡ��1��ȡ��,ͬʱ����ԭ��˰֤�ţ�2����������
                //1������ԭ��˰֤�ţ�ͬʱ����ȡ��
                //�����˰֤��
                try
                {
                    String retResult = ServiceProxy.setCancellation(ud,
                        pf.getPzzldm(),
                        pf.getNdzb() + pf.getHeadWszh(),
                        StringUtil.getDouble(pf.getHjje(), 0.00),
                        "1", "1", "1");
                    ndzb = retResult.substring(0, 4); //����ֱ�
                    wszh = retResult.substring(4); //��˰֤��
                }
                catch (Exception ex1)
                {
                    ex1.printStackTrace();
                    throw new ApplicationException("��ȡ��˰֤ʧ�ܣ�" + ex1.getMessage());
                }
                pf.setHeadWszh(wszh);
                pf.setNdzb(ndzb);
                //2���������ݣ���˰֤
                //��ϸ��
                for (int i = 0; i < mxList.size(); i++)
                {
                    mx = (Gtgshwszmx) mxList.get(i);
                    mx.setWszh(wszh);
                    mx.setLrrq(nowTime); //¼������
                    mx.setNdzb(ndzb); //����ֱ�
                    da.update(mx);
                }
                //����
                for (int i = 0; i < zbList.size(); i++)
                {
                    zb = (Gtgshwszz) zbList.get(i);
                    zb.setWszh(wszh);
                    zb.setLrrq(nowTime); //¼������
                    zb.setNdzb(ndzb); //����ֱ�
                    da.update(zb);
                }
                //End of reget wszh
            }
            else
            { //��ɢ˰����
                tempVector.clear();
                tempVector.addElement("qxdm='" + qxdm + "'");
                tempVector.addElement("ndzb='" + pf.getNdzb() + "'");
                tempVector.addElement("(sbhzdh is null or sbhzdh='')");
                tempVector.addElement("wszh='" + pf.getHeadWszh() + "'");
                tempVector.addElement("1=1 order by wszh desc");
                ArrayList zbList = (ArrayList) da.query(Lsswszz.class,
                    tempVector);

                if (zbList.size() <= 0)
                {
                    throw new ApplicationException("û���ҵ�������������˰֤��Ϣ��");
                }

                Lsswszz zb = (Lsswszz) zbList.get(0);
                pf.setHeadWszh(zb.getWszh());
                pf.setZclx(""); //ע�����ͣ���ʱд��
                pf.setTfrq( (String.valueOf(zb.getCjrq())).substring(0, 10));
                pf.setNsrjsjdm(pf.getJsjdm()); //��λ�ļ��������
                pf.setNsrmc(zb.getNsrmc());
                pf.setDz(zb.getDz()); //��ɢ�Ļ�ȡ�Լ��ĵ�ַ
                pf.setBz(zb.getNsrmc()); //��ע
                pf.setHjje(deFormat.format(zb.getHjsjje())); //�ϼƽ��
                pf.setHjjedx(Currency.convert(zb.getHjsjje())); //�ϼƽ���д

                //��ϸ��Ϣ
                tempVector.clear();
                tempVector.addElement("qxdm='" + qxdm + "'");
                tempVector.addElement("ndzb='" + pf.getNdzb() + "'");
                tempVector.addElement("wszh='" + pf.getHeadWszh() + "'");
                tempVector.addElement("wszxh='" + zb.getWszxh() + "'");
                tempVector.addElement("1=1 order by wszh desc");
                ArrayList mxList = (ArrayList) da.query(Lsswszmx.class,
                    tempVector);

                if (mxList.size() <= 0)
                {
                    throw new ApplicationException("û���ҵ�������������˰֤��Ϣ��");
                }

                Lsswszmx mx = new Lsswszmx();
                for (int i = 0; i < mxList.size(); i++)
                {
                    mx = (Lsswszmx) mxList.get(i);
                    HashMap map = new HashMap();
                    if (mx.getKssl() == null)
                    {
                        map.put("kssl", " ");
                    }
                    else
                    {
                        map.put("kssl", String.valueOf(mx.getKssl()));

                    }
                    if (mx.getJsje() == null)
                    {
                        map.put("jsje", " ");
                    }
                    else
                    {
                        map.put("jsje",
                                deFormat.format(StringUtil.getDouble(String.
                            valueOf(mx.getJsje()), 0.00)));

                    }
                    if (mx.getSl() == null)
                    {
                        map.put("sl", " ");
                    }
                    else
                    {
                        map.put("sl", String.valueOf(mx.getSl()));

                    }
                    if (mx.getKssl() == null)
                    {
                        map.put("yjhkc", " ");
                    }
                    else
                    {
                        map.put("yjhkc",
                                deFormat.format(StringUtil.getDouble(String.
                            valueOf(mx.getYjhkc()), 0.00)));

                    }
                    if (mx.getSjse() == null)
                    {
                        map.put("sjse", " ");
                    }
                    else
                    {
                        map.put("sjse",
                                deFormat.format(StringUtil.getDouble(String.
                            valueOf(mx.getSjse()), 0.00)));

                    }
//                    pf.setSkssksrq(String.valueOf(mx.getSkssksrq()).substring(0,
//                            10));
//                    pf.setSkssjsrq(String.valueOf(mx.getSkssjsrq()).substring(0,
//                        10));

                    if (mx.getSkssksrq() == null)
                    {
                    	map.put("skssksrq", " ");
                    }
                    else
                    {
                    	map.put("skssksrq",
                        		String.valueOf(mx.getSkssksrq()).substring(0,10).replaceAll("-", ""));
                    }
                    
                    if (mx.getSkssjsrq() == null)
                    {
                    	map.put("skssjsrq", " ");
                    }
                    else
                    {
                    	map.put("skssjsrq",
                        		String.valueOf(mx.getSkssjsrq()).substring(0,10).replaceAll("-", ""));
                    }
                    map.put("szmc",
                            CodeUtils.getCodeBeanLabel("DM_SZSM", mx.getSzdm()));
                    map.put("szsmmc",
                            CodeUtils.getCodeBeanLabel("DM_SZSM", mx.getSzsmdm()));
                    mxSz += map.get("szmc") + ";;";
                    mxPmmc += map.get("szsmmc") + ";;";
                    mxKssl += map.get("kssl") + ";;";
                    mxJsje += map.get("jsje") + ";;";
                    mxSl += map.get("sl") + ";;";
                    mxSkssrq += map.get("skssksrq") + "-"+map.get("skssjsrq") + ";;";
                    mxYjhkc += map.get("yjhkc") + ";;";
                    mxSjse += map.get("sjse") + ";;";
                    dataList.add(map);
                }
                pf.setMxSz(mxSz);
                pf.setMxPmmc(mxPmmc);
                pf.setMxKssl(mxKssl);
                pf.setMxJsje(mxJsje);
                pf.setMxSl(mxSl);
                pf.setMxSkssrq(mxSkssrq);
                pf.setMxYjhkc(mxYjhkc);
                pf.setMxSjse(mxSjse);

                pf.setDataList(dataList);

                //����ȡ��Ȼ���ӡ��1��ȡ�ţ�ͬʱ����ԭ��˰֤�ţ�2����������
                //1������ԭ��˰֤�ţ�ͬʱ����ȡ��
                //�����˰֤��
                try
                {
                    String retResult = ServiceProxy.setCancellation(ud,
                        pf.getPzzldm(),
                        pf.getNdzb() + pf.getHeadWszh(),
                        StringUtil.getDouble(pf.getHjje(), 0.00),
                        "1", "1", "1");
                    ndzb = retResult.substring(0, 4); //����ֱ�
                    wszh = retResult.substring(4); //��˰֤��
                }
                catch (Exception ex1)
                {
                    ex1.printStackTrace();
                    throw new ApplicationException("��ȡ��˰֤ʧ�ܣ�" + ex1.getMessage());
                }
                pf.setHeadWszh(wszh);
                pf.setNdzb(ndzb);
                //2���������ݣ���˰֤
                //��ϸ��
                for (int i = 0; i < mxList.size(); i++)
                {
                    mx = (Lsswszmx) mxList.get(i);
                    mx.setWszh(wszh);
                    mx.setLrrq(nowTime); //¼������
                    mx.setNdzb(ndzb); //����ֱ�
                    da.update(mx);
                }
                //����
                for (int i = 0; i < zbList.size(); i++)
                {
                    zb = (Lsswszz) zbList.get(i);
                    zb.setWszh(wszh);
                    zb.setLrrq(nowTime); //¼������
                    zb.setNdzb(ndzb); //����ֱ�
                    da.update(zb);
                }
                //End of reget wszh
            } //End of if
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

}
//:-)
