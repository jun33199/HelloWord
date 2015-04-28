/*
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.yhsgl.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.dj.model.YHZH;
import com.ttsoft.bjtax.dj.proxy.ServiceProxy;
import com.ttsoft.bjtax.sfgl.common.code.CodeManager;
import com.ttsoft.bjtax.sfgl.common.code.CodeUtils;
import com.ttsoft.bjtax.sfgl.common.constant.CodeConstants;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.util.LabelValueBean;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.sfgl.common.util.SfTimeUtil;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkmx;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.model.client.DeclareInfor;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.JksUtil;
import com.ttsoft.bjtax.smsb.util.Skssrq;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.bjtax.smsb.yhsgl.web.YhsgmhzForm;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: ����ӡ��˰������� ��̨����</p>
 * @author �������飭�������
 * @version 1.1
 */
public class YhsgmhzProcessor
    implements Processor
{

    /**
     * Ĭ�Ϲ��캯��
     */
    public YhsgmhzProcessor ()
    {
    }

    /**
     * ͨ�ô������ģ��
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return object
     * @throws BaseException
     */
    public Object process (VOPackage vo)
        throws
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
                result = doShow(vo);
                break;
            case CodeConstant.SMSB_HZJKSACTION:
                result = doHzjks(vo);
                break;
            default:
                throw new UnsupportedOperationException(
                    "Method process() not yet implemented.");
        }
        return result;
    }
    /**
     * ��ʼ������ҳ����ϢҪ��
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return Form
     * @throws BaseException
     */
    private Object doShow (VOPackage vo)
        throws BaseException
    {
        return new YhsgmhzForm();
    }

    /**
     * �����������ɽɿ���
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return Form
     * @throws BaseException
     */
    private Object doHzjks (VOPackage vo)
        throws BaseException
    {
        YhsgmhzForm form = (YhsgmhzForm) vo.getData();
        HashMap map = null;
        try
        {
            //���ɽɿ���
            map = doCreateJks(vo);
            form.setJkpzh(map.get("jkpzh").toString());
            form.setSjje(map.get("sjje").toString());
            form.setHzdxmc(map.get("hzdxmc").toString());
            form.setSbbh(map.get("sbbh").toString());
            return form;
        }
        catch (BaseException ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    /**
     * �����������ɽɿ������ݣ����浽�걨�ɿ�������ϸ��
     * ���������Ϣ��ӡ��˰��������
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return map ���ػ�����Ϣ�����ܽ����ʾҳ���õ���
     * @throws BaseException
     */
    private HashMap doCreateJks (VOPackage vo)
        throws BaseException
    {
        HashMap map = new HashMap();
        Connection conn = null;

        YhsgmhzForm form = (YhsgmhzForm) vo.getData();
        String hzfs = form.getHzdx();
        String jsjdm = "";
        String lrr = form.getLrr();
        String yhsxsry = form.getYhsxsry();
        String begTime = form.getHzqsrq();
        String endTime = form.getHzjsrq();
        String zsjgdm = form.getZsjgdm();
        String swjgzzjgdm = "";
        String jkpzh = "";
        String sbbh = "";
        //��������
        BigDecimal sjje = null; //ʵ�ɽ��
        String hzdxmc = ""; //���ܶ�������
        String dsdwmc = ""; //���۵�λ����
        try
        {
            conn = SfDBResource.getConnection();
            UserData userData = vo.getUserData();
            String strSsdwdm = userData.getSsdwdm(); //userData������˰�������֯��������
            String strDsJsjdm = getDsjsjdmByUserData(strSsdwdm);
            if (strDsJsjdm == null || strDsJsjdm == "")
            {
                throw new ApplicationException("δ�õ���Ӧ�Ĵ��ۼ�������룡");
            }
            else
            {
                form.setDsjsjdm(strDsJsjdm);
                jsjdm = strDsJsjdm;
            }
            SfDBAccess db = new SfDBAccess(conn);
            //����ʱ������
            if (endTime == null || endTime.equals(""))
            {
                endTime = SfDateUtil.getDate();
            }
            String timeCon = " and cjrq<=to_date('" + endTime +
                             " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
            if (begTime != null && !begTime.equals(""))
            {
                timeCon = timeCon + " and cjrq>=to_date('" + begTime +
                          " 00:00:00','yyyy-mm-dd hh24:mi:ss')";
            }

            //�õ����ܽ������
            try
            {
                StringBuffer sqlBuffer = new StringBuffer();
                sqlBuffer.append(
                    "select sum(hjje) sjje from sbdb.sb_jl_yhsgmz where");
                if (hzfs.equals("0"))
                { //����λ����
                    sqlBuffer.append(" dsjsjdm='").append(jsjdm).append("'");
                }
                else
                { //�������˻���
                    sqlBuffer.append(" zhdm='").append(yhsxsry).append("'");
                } //end if
                sqlBuffer.append(" and qxdm='" + InterfaceDj.getQxdm(userData)
                                 + "' ");
                sqlBuffer.append(" and (yhzbs<>'1' or yhzbs is null)").append(
                    timeCon);

                String sqlString = sqlBuffer.toString();
                ResultSet rs = db.querySQL(sqlString);
                if (rs.next())
                {
                    if (rs.getString("sjje") != null)
                    {
                        sjje = new BigDecimal(rs.getString("sjje")); //�õ�ʵ�ɽ��
                    }
                    else
                    {
                        throw new ApplicationException("û��δ���ܵ��������ݣ�");
                    }
                }
                else
                {
                    throw new ApplicationException("û��δ���ܵ��������ݣ�");
                }
                rs.close();
            }
            catch (Exception ex4)
            {
                ex4.printStackTrace();
                throw new ApplicationException(ex4.getMessage());
            } //end try

            //������������д
            Sbjkzb orJkzb = new Sbjkzb();
            orJkzb.setSklxdm(CodeConstant.SKLXDM_ZCJK); // 2
            orJkzb.setJsjdm(jsjdm); // 3
            orJkzb.setFsdm("1"); // 4
            orJkzb.setZsswjgzzjgdm(zsjgdm); // 11
            orJkzb.setSjje(sjje); // 22
            orJkzb.setRkje(sjje); // 24
            //orJkzb.setZwbs("00000000000000000000"); // 25
            orJkzb.setZwbs("00000000000000000010"); // 20050705
            orJkzb.setLrr(lrr); // 28
            orJkzb.setSjly("15"); // 36

            //���Ǽǽӿ�,��ô��۵�λ��Ϣ
            try
            {
                ServiceProxy djProxy = new ServiceProxy();
                HashMap ghdwMap = djProxy.getDjInfo(jsjdm);
                SWDJJBSJ swdjjbsj = (SWDJJBSJ) ghdwMap.get("JBSJ");
                dsdwmc = swdjjbsj.getNsrmc();
                orJkzb.setDjzclxdm(swdjjbsj.getDjzclxdm()); // 9
                orJkzb.setSwjgzzjgdm(swdjjbsj.getSwjgzzjgdm()); // 10
                swjgzzjgdm = swdjjbsj.getSwjgzzjgdm();
                orJkzb.setGjbzhydm(swdjjbsj.getGjbzhydm()); // 12
                orJkzb.setJydzlxdm(swdjjbsj.getJydzlxdm()); // 33
                //�������ɽɿ��鲻����������Ϣ Modifyed by wuyouzhi 2006.2.7
//                //�õ�������Ϣ
//                ArrayList dmList = (ArrayList) ghdwMap.get("YHZH");
//                for (int i = 0; i < dmList.size(); i++)
//                {
//                    YHZH yhzh = (YHZH) dmList.get(i);
//                    if (yhzh.getJbzhbs().equals(CodeConstant.SMSB_JBZHBS))
//                    {
//                        orJkzb.setYhdm(yhzh.getYhdm()); //���д���
//                        orJkzb.setYhmc(yhzh.getKhyhmc()); //��������
//                        orJkzb.setZh(yhzh.getZh()); //�ʻ�
//                        break;
//                    }
//                }
            }
            catch (Exception e2)
            {
                throw new ApplicationException("���Ǽǽӿڳ���");
            } // end try

            //����δȷ������д
            orJkzb.setLrrq(SfTimeUtil.getNowTimestamp()); // 17
            orJkzb.setSbrq(TinyTools.calendar2Timestamp(Calendar.getInstance()));
            orJkzb.setCjrq(SfTimeUtil.getNowTimestamp()); // 30
            orJkzb.setQxdm(InterfaceDj.getQxdm(userData));

            //�ӱ���
            Sbjkmx orJkmx = new Sbjkmx();
            orJkmx.setSzsmdm("161400"); // 1
            orJkmx.setSzdm("16");
            orJkmx.setJsjdm(jsjdm); // 3
            orJkmx.setSjse(sjje); // 8
            orJkmx.setRkje(sjje); // 11
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
                retJks = jks.getJkDataYhs(orJkzb, mxList,
                                          CodeConstant.PRINT_YPYS);
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
                sbbh = retZb.getSbbh();
            }

            String timeNow = SfTimeUtil.getNowTimestamp().toString();
            timeNow = timeNow.substring(0, timeNow.length() - 4);
            //���������Ϣ
            try
            {
                StringBuffer sqlBuffer = new StringBuffer();
                sqlBuffer.append("update sbdb.sb_jl_yhsgmz set jkpzh='")
                    .append(jkpzh)
                    .append("', hzrq=to_date('")
                    .append(SfDateUtil.getDate())
                    .append("','yyyyMMdd'), hzr='")
                    .append(lrr)
                    .append("', yhzbs='1', hzfs='")
                    .append(hzfs)
                    .append("', lrrq=to_date('")
                    .append(timeNow)
                    .append("','yyyy-mm-dd hh24:mi:ss')")
                    .append(" where");

                if (hzfs.equals(CodeConstant.HZFS_DW))
                { //����λ����
                    sqlBuffer.append(" dsjsjdm='").append(jsjdm).append("'");
                    hzdxmc = dsdwmc;
                }
                else
                { //�������˻���
                    sqlBuffer.append(" zhdm='").append(yhsxsry).append("'");
                    List codeList = CodeManager.getCodeList("YHS_ZHDM",
                        CodeConstants.CODE_MAP_BEANLIST).
                                    getRecords();
                    String name = "";
                    for (int k = 0; k < codeList.size(); k++)
                    {
                        if (yhsxsry.equals( ( (LabelValueBean) codeList.get(k)).
                                           getValue()))
                        {
                            name = ( (LabelValueBean) codeList.get(k)).getLabel();
                            break;
                        }
                    }
                    hzdxmc = name;
                }
                sqlBuffer.append(" and qxdm='" + InterfaceDj.getQxdm(userData)
                                 + "' ");
                sqlBuffer.append(" and (yhzbs<>'1' or yhzbs is null)").append(
                    timeCon);

                String sqlString = sqlBuffer.toString();
                PreparedStatement sqlStatement = conn.prepareStatement(
                    sqlString);
                sqlStatement.execute();
            }
            catch (Exception ex3)
            {
                ex3.printStackTrace();
                throw new ApplicationException("���������Ϣ����");
            }
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        } //end try
        finally
        {
            SfDBResource.freeConnection(conn);
        }
        map.put("sjje", sjje);
        map.put("jkpzh", jkpzh);
        map.put("hzdxmc", hzdxmc);
        map.put("sbbh",sbbh);
        return map;
    }

    /**
     * ����UserData�õ����۵�λ���������
     * @param strSsdwdm ����˰��λ����
     * @return ���۵�λ���������
     */
    private String getDsjsjdmByUserData (String strSsdwdm)
    {
        String dsJsjdm = "";
        dsJsjdm = CodeUtils.getCodeMapValue("ZHSB_SWJGZZJG",
                                            "swjgzzjgdm", strSsdwdm, "jsjdm");
        return dsJsjdm;
    }

}
