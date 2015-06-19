/*
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.yhsgl.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.struts.upload.FormFile;
import org.xml.sax.InputSource;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.dj.model.YHZH;
import com.ttsoft.bjtax.dj.proxy.ServiceProxy;
import com.ttsoft.bjtax.sfgl.common.db.util.BeanUtil;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.sfgl.common.util.SfTimeUtil;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkmx;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb;
import com.ttsoft.bjtax.shenbao.model.domain.Yhsgmmx;
import com.ttsoft.bjtax.shenbao.model.domain.Yhsgmz;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.model.client.DeclareInfor;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.JksUtil;
import com.ttsoft.bjtax.smsb.util.Skssrq;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.bjtax.smsb.yhsgl.web.YhsxsdrForm;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: ӡ��˰���۵�λ���۵��� ��̨����</p>
 * @author �������飭�������
 * @version 1.1
 */

public class YhsxsdrProcessor
    implements Processor
{

    /**
     * Ĭ�Ϲ��캯��
     */
    public YhsxsdrProcessor ()
    {
    }

    /**
     * ͨ�ô������ģ��
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return object
     * @throws com.ttsoft.framework.exception.BaseException
     */
    public Object process (VOPackage vo)
        throws com.ttsoft.framework.exception.
        BaseException
    {
        Object result = null;

        if (vo == null)
        {
            throw new NullPointerException();
        }

        switch (vo.getAction())
        {
            case CodeConstant.SMSB_SHOWACTION:
                doShow(vo);
                break;
            case CodeConstant.SMSB_HZJKSACTION:
                result = doHzjks(vo);
                break;
            case CodeConstant.SMSB_CXJKSACTION:
                doCxjks(vo);
            case CodeConstant.SMSB_LOADACTION:
                result = doLoad(vo);
                break;
            default:
                throw new UnsupportedOperationException(
                    "Method process() not yet implemented.");
        }
        return result;
    }

    /**
     * doShow ��ʼ������ҳ����ϢҪ��
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @throws BaseException
     */
    private void doShow (VOPackage vo)
        throws BaseException
    {
    }

    /**
     * ������۵�λ���������ļ������غϷ����ݼ�
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return Form
     * @throws BaseException
     */
    private Object doLoad (VOPackage vo)
        throws BaseException
    {
        YhsxsdrForm form = (YhsxsdrForm) vo.getData();
        FormFile formFile = form.getTheFile();
        try
        {
            //��õ�������
            InputSource theFile = new InputSource(formFile.getInputStream());
            //�������ݵõ������¼
            xml4YHS parser = new xml4YHS(theFile);
            form.setDataList(parser.yhsList); //��������
            form.setDsjsjdm(parser.dsjsjdm); //���۵�λ���������
            form.setXsList(parser.xsList); //����ƾ֤������Ӧ�ĺϼƽ��
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "��ǰ�����xml�ļ����ݴ���!");
        }
        return form;
    }

    /**
     * ���ܵ����������ɽɿ���
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return map ���ػ��ܽ����ʾҳ����Ҫ�Ļ��ܽ����Ϣ
     * @throws BaseException
     */
    private HashMap doHzjks (VOPackage vo)
        throws BaseException
    {
        try
        {
            //���浼������ݵ�ӡ��˰����������ϸ��
            doSave2GMDB(vo);

            //���ܵ����������ɽɿ������ݣ����浽�걨�ɿ�������ϸ��;
            //���������Ϣ��ӡ��˰��������
            //���ػ��ܽ����ʾҳ����Ҫ�Ļ��ܽ����Ϣ
            return (doCreateJks(vo));
        } //end try
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    /**
     * ���浼������ݵ�ӡ��˰����������ϸ��
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @throws BaseException
     */
    private void doSave2GMDB (VOPackage vo)
        throws BaseException
    {
        Connection conn = null;
        YhsxsdrForm form = (YhsxsdrForm) vo.getData();
        Yhsgmz orObj = new Yhsgmz();

        //Ϊ����Ʊ֤�ӿ�׼������
        //���������б�
        ArrayList zbList = (ArrayList) form.getXsList().clone();
        //��ϸ��Ŀ����
        ArrayList tempList = (ArrayList) form.getDataList().clone();
        List[] checkListArray = new ArrayList[zbList.size()];
        int countOfArray = 0;
        for (int i = 0; i < tempList.size(); i++)
        {
            ( (Map) tempList.get(i)).put("sequence", "" + (i + 1));
            if (i == 0)
            {
                checkListArray[countOfArray] = new ArrayList();
                checkListArray[countOfArray].add( (Map) tempList.get(i));
            }
            else if (i > 0)
            {
                //��һ������ƾ֤��
                String preXspzh = (String) ( (Map) tempList.get(i - 1)).get(
                    "xspzh");
                //��ǰ����ƾ֤��
                String curXspzh = (String) ( (Map) tempList.get(i)).get("xspzh");
                if (preXspzh.equals(curXspzh))
                //�����ͬһ����ƾ֤����һͬ����
                //��������µ��б�
                {
                    checkListArray[countOfArray].add( (Map) tempList.get(i));
                }
                else
                {
                    countOfArray = countOfArray + 1;
                    checkListArray[countOfArray] = new ArrayList();
                    checkListArray[countOfArray].add( (Map) tempList.get(i));
                }
            }
        }

        //����Ʊ֤�ӿ�ӡ��˰Ʊ���飬�����¿��
        for (int i = 0; i < zbList.size(); i++)
        {
            String zhdm = (String) ( (Map) zbList.get(i)).get("zhdm");
            List yhspList = com.ttsoft.bjtax.pzgl.proxy.ServiceProxy.
                            checkSpsl(vo.getUserData(), zhdm, checkListArray[i]);
            checkYhspsl(yhspList);
        }

        //���۵�λ��������룬�������ڣ�¼�����ڣ����۵�λ���ƣ�������Դ
        //˰�������֯�������룬��ʽ���룬¼����
        String columns[] =
                           {
                           "dsjsjdm", "cjsj", "lrrq", "dsdwmc", "sjly",
                           "swjgzzjgdm", "fsdm", "lrr"};
        try
        {
            conn = SfDBResource.getConnection();
            SfDBAccess da = new SfDBAccess(conn);

            //�������ݴ���
            BeanUtil.copyBeanToBean(columns, form, orObj);
            ArrayList xsList = (ArrayList) form.getXsList().clone();
            for (int j = 0; j < xsList.size(); j++)
            {
                String xspzh = ( (HashMap) xsList.get(j)).get("xspzh").toString();
                String hjje = ( (HashMap) xsList.get(j)).get("hjje").toString();
                orObj.setXspzh(xspzh);
                orObj.setHjje(new BigDecimal(hjje));
                orObj.setCjrq(SfTimeUtil.getNowTimestamp());
                orObj.setLrrq(SfTimeUtil.getNowTimestamp());
                orObj.setQxdm(InterfaceDj.getQxdm(vo.getUserData()));
                if (orObj.getCjrq() != null)
                {
                    orObj.setNd("" + TinyTools.getYear(orObj.getCjrq()));
                }
                orObj.setZhdm( ( (HashMap) xsList.get(j)).get("zhdm").toString());
                orObj.setJzbs("0"); //δ����
                Vector condition = new Vector();
                condition.add("qxdm='" + InterfaceDj.getQxdm(vo.getUserData())
                              + "'");
                condition.add("xspzh='" + xspzh + "'");
                condition.add("dsjsjdm='" + form.getDsjsjdm() + "'");
                if (da.query(orObj.getClass(), condition).size() > 0)
                {
                    throw new ApplicationException("���������Ѵ��ڣ�");
                }
                else
                {
                    try
                    {
                        da.insert(orObj);
                    }
                    catch (BaseException ex3)
                    {
                        throw new ApplicationException("�����������ݳ���");
                    } //end try
                } //end if
            } //end for

            //��ϸ���ݻ��
            ArrayList list = form.getDataList();
            //��ϸ���ݴ���
            for (int i = 0; i < list.size(); i++)
            {
                HashMap map = (HashMap) list.get(i);
                Yhsgmmx orMx = new Yhsgmmx();
                map.put("dsjsjdm", form.getDsjsjdm());
                map.put("swjgzzjgdm", form.getSwjgzzjgdm());
                BeanUtil.populate(orMx, map);
                orMx.setNd("" + orObj.getNd());
                orMx.setCjrq(SfTimeUtil.getNowTimestamp());
                orMx.setLrrq(SfTimeUtil.getNowTimestamp());
                orMx.setQxdm(InterfaceDj.getQxdm(vo.getUserData()));
                Vector conditionmx = new Vector();
                conditionmx.add("qxdm='" + InterfaceDj.getQxdm(vo.getUserData())
                                + "'");
                conditionmx.add("dsjsjdm='" + form.getDsjsjdm() + "'");
                conditionmx.add("xspzh='" + map.get("xspzh") + "'");
                conditionmx.add("spmzdm='" + map.get("spmzdm") + "'");
                if (da.query(orMx.getClass(), conditionmx).size() > 0)
                {
                    throw new ApplicationException("������ϸ�����Ѵ��ڣ�");
                }
                else
                {
                    try
                    {
                        da.insert(orMx);
                    }
                    catch (BaseException ex3)
                    {
                        throw new ApplicationException("����������ϸ���ݳ���");
                    } //end try
                } //end if
            } //end for
        } //end try
        catch (Exception ex)
        {
            throw new ApplicationException("���浼�����ݳ���" + ex.getMessage());
        }
        finally
        {
            SfDBResource.freeConnection(conn);
        }
    }

    /**
     * ���ܵ����������ɽɿ������ݣ����浽�걨�ɿ�������ϸ��
     * ���������Ϣ��ӡ��˰��������
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return map ���ػ�����Ϣ�����ܽ����ʾҳ���õ���
     * @throws BaseException
     */
    private HashMap doCreateJks (VOPackage vo)
        throws BaseException
    {
        Connection conn = null;
        conn = SfDBResource.getConnection();
        HashMap map = new HashMap();
        YhsxsdrForm form = (YhsxsdrForm) vo.getData();
        ArrayList xsList = (ArrayList) form.getXsList().clone();
        String jsjdm = form.getDsjsjdm();
        BigDecimal sjse = new BigDecimal(form.getHjje());
        String lrr = form.getLrr();
        String swjgzzjgdm = "";
        String jkpzh = "";
        String dsdwmc = "";

        //���ɽɿ�������������
        Sbjkzb orJkzb = new Sbjkzb();
        orJkzb.setSklxdm("200"); // 2
        orJkzb.setJsjdm(jsjdm); // 3
        orJkzb.setFsdm("1"); // 4
        orJkzb.setSjje(sjse); // 22
        orJkzb.setRkje(sjse); // 24
        //orJkzb.setZwbs("00000000000000000000"); // 25
        //��18��19 λ01��ʾ�ֽ����
        orJkzb.setZwbs("00000000000000000010"); // 25
        orJkzb.setLrr(lrr); // 28
        orJkzb.setSjly("15"); // 36

        //���Ǽǽӿ�,��ô��۵�λ��Ϣ
        try
        {
            ServiceProxy djProxy = new ServiceProxy();
            HashMap ghdwMap = djProxy.getDjInfo(jsjdm);
            SWDJJBSJ swdjjbsj = (SWDJJBSJ) ghdwMap.get("JBSJ");
            dsdwmc = swdjjbsj.getNsrmc();
            orJkzb.setLsgxdm(swdjjbsj.getLsgxdm()); // 7
            orJkzb.setDjzclxdm(swdjjbsj.getDjzclxdm()); // 9
            swjgzzjgdm = swdjjbsj.getSwjgzzjgdm();
            orJkzb.setSwjgzzjgdm(swjgzzjgdm); // 10
            orJkzb.setZsswjgzzjgdm(swjgzzjgdm); // 11
            orJkzb.setGjbzhydm(swdjjbsj.getGjbzhydm()); // 12
            orJkzb.setJydzlxdm(swdjjbsj.getJydzlxdm()); // 33
            //�õ�������Ϣ
            ArrayList dmList = (ArrayList) ghdwMap.get("YHZH");
            for (int i = 0; i < dmList.size(); i++)
            {
                YHZH yhzh = (YHZH) dmList.get(i);
                if (yhzh.getJbzhbs().equals(CodeConstant.SMSB_JBZHBS))
                {
                    orJkzb.setYhdm(yhzh.getYhdm()); //���д���
                    orJkzb.setYhmc(yhzh.getKhyhmc()); //��������
                    orJkzb.setZh(yhzh.getZh()); //�ʻ�
                    break;
                }
            }
        }
        catch (Exception e2)
        {
            throw ExceptionUtil.getBaseException(e2);
        }

        //�걨�ɿ�����δȷ����
        orJkzb.setLrrq(SfTimeUtil.getNowTimestamp()); // 17
        orJkzb.setSbrq(TinyTools.calendar2Timestamp(Calendar.getInstance()));
        orJkzb.setCjrq(SfTimeUtil.getNowTimestamp()); // 30
        orJkzb.setQxdm(InterfaceDj.getQxdm(vo.getUserData()));

        //�����걨�ɿ���ϸ������
        Sbjkmx orJkmx = new Sbjkmx();
        orJkmx.setSzsmdm("16"); // 1
        orJkmx.setSzdm("16");
        orJkmx.setJsjdm(jsjdm); // 3
        orJkmx.setSjse(sjse); // 8
        orJkmx.setRkje(sjse); // 11
        orJkmx.setSwjgzzjgdm(swjgzzjgdm); // 15
        //ȡ��˰����������
        Map dateMap = Skssrq.monthSkssrq(new Date());
        orJkmx.setSkssksrq( (Timestamp) dateMap.get(Skssrq.SKSSKSRQ));
        orJkmx.setSkssjsrq( (Timestamp) dateMap.get(Skssrq.SKSSJSRQ));

        //���������ϸ���ݲ���sbjkzb��sbjkmx
        JksUtil jks = new JksUtil();
        List mxList = new ArrayList();

        //������ϸ�����б�
        mxList.add(orJkmx);

        //����
        List retJks = null;
        try
        {
            retJks = jks.getJkDataYhs(orJkzb, mxList, CodeConstant.PRINT_YPYS);
        }
        catch (BaseException ex1)
        {
            throw ExceptionUtil.getBaseException(ex1);
        }

        //�ڷ���ֵ�еõ��ɿ�ƾ֤��
        if (retJks.size() > 0)
        {
            DeclareInfor retSbjk = (DeclareInfor) retJks.get(0);
            Sbjkzb retZb = retSbjk.getSbjkzb();
            jkpzh = retZb.getJkpzh();
        }

        String timeNow = SfTimeUtil.getNowTimestamp().toString();
        timeNow = timeNow.substring(0, timeNow.length() - 4);
        //���������Ϣ
        String hzrq = SfDateUtil.getDate();
        for (int k = 0; k < xsList.size(); k++)
        {
            String xspzh = ( (HashMap) xsList.get(k)).get("xspzh").toString();
            try
            {
                StringBuffer sqlBuffer = new StringBuffer();
                sqlBuffer.append("update sbdb.sb_jl_yhsgmz set jkpzh='")
                    .append(jkpzh)
                    .append("', hzrq=to_date('")
                    .append(hzrq)
                    .append("','yyyyMMdd'), hzr='")
                    .append(lrr)
                    .append("', lrrq=to_date('")
                    .append(timeNow)
                    .append("','yyyy-mm-dd hh24:mi:ss')")
                    //�ѻ��ܱ�ʶ��'1'
                    //���ܷ�ʽ���룺�����۵�λ����->'0'
                    .append(", yhzbs='1', hzfs='0' where ")
                    .append(" qxdm='" + InterfaceDj.getQxdm(vo.getUserData())
                            + "' ")
                    .append(" and xspzh='")
                    .append(xspzh)
                    .append("' and dsjsjdm='")
                    .append(jsjdm)
                    .append("'");
                String sqlString = sqlBuffer.toString();
                PreparedStatement sqlStatement = conn.prepareStatement(
                    sqlString);
                sqlStatement.execute();
            }
            catch (Exception ex)
            {
                throw ExceptionUtil.getBaseException(ex, "���������Ϣ����");
            } //end try
        } //end for

        //���ػ�����Ϣ
        map.put("dsdwmc", dsdwmc);
        map.put("jkpzh", jkpzh);
        map.put("sjse", sjse);
        return map;
    }

    /**
     * �����ɿ���
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @throws BaseException
     */
    private void doCxjks (VOPackage vo)
        throws BaseException
    {
    }

    /**
     * �������Ʊ֤�ӿڷ��ص�List���Ƿ���Error Message���������ڿͻ�����ʾ
     * @param yhspList ӡ��˰Ʊ�б�
     * @throws ApplicationException
     */
    private void checkYhspsl (List yhspList)
        throws ApplicationException
    {
        String errorMessage = "";
        for (int i = 0; i < yhspList.size(); i++)
        {
            Map map = (Map) yhspList.get(i);
            if (map.get("error") != null)
            {
                int intError = Integer.parseInt( (String) map.get("error"));
                if (intError == -1)
                //��ǰ����Ա��Ȩ���۴���ӡ��˰Ʊ
                {
                    errorMessage = errorMessage + "�������Ϊ" +
                                   (String) map.get("sequence") + "��ӡ��˰Ʊ��" +
                                   "��û��Ȩ�����۴���ӡ��˰Ʊ��<BR>";
                }
                else
                //��ǰ����Ա�����۵Ĵ���ӡ��˰Ʊ���������
                {
                    errorMessage = errorMessage + "�������Ϊ" +
                                   (String) map.get("sequence") + "��ӡ��˰Ʊ��" +
                                   "����ӡ��˰Ʊ�Ŀ������Ϊ" + intError + "��<BR>";
                }
            }
        }
        if (!errorMessage.equals(""))
        {
            throw new ApplicationException(errorMessage);
        }
    }
}