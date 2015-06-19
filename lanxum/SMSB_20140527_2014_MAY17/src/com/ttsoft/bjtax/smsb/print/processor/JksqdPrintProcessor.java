/*
 * <p>Title:������˰�г�����֧��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪�Ϲ�Ƽ��ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: ��һ���ſƼ��ɷ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.print.processor;

import java.math.*;
import java.sql.*;
import java.text.*;
import java.util.*;

import com.ttsoft.bjtax.sfgl.common.code.*;
import com.ttsoft.bjtax.sfgl.common.db.util.*;
import com.ttsoft.bjtax.shenbao.model.domain.*;
import com.ttsoft.bjtax.smsb.constant.*;
import com.ttsoft.bjtax.smsb.print.web.*;
import com.ttsoft.bjtax.smsb.util.*;
import com.ttsoft.common.model.*;
import com.ttsoft.framework.exception.*;
import com.ttsoft.framework.processor.*;
import com.ttsoft.framework.util.*;
import com.ttsoft.framework.util.Currency;
import com.ttsoft.bjtax.dj.DjOuterConstant;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.dj.model.ZRRJBSJ;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: �ɿ����뵥��ӡ</p>
 * 
 * Ǯ���޸� 2006.2.11 
 *     1���޸���ʾ��ʽΪ�����ӽɿ�ר�ýɿ��顱
 * 
 * Ǯ���޸�  2006.2.23
 *     1����ʾ��ʽ������һ��Ԥ���Ŀһ����ʾ�����ɽ𷣿��һ��
 * 
 * @author ����������������
 * @version 1.1
 */
public class JksqdPrintProcessor implements Processor{

    class tmpQuery{
        //��ע
        String bz;
        //������֯�ṹ����
        String gkzzjgdm;
        //�걨����
        Timestamp sbrq;
        //ʵ������
        BigDecimal sjse;
        //˰�ִ���
        String szdm;
        //˰Ŀ����
        String szsmdm;
        //�����ʺ�
        String yhzh;
        //��������        
        String yhmc;
        //Ԥ���Ŀ����
        String yskmdm;
        //˰�������֯��������
        String swjgzzjgdm;
    };
    
    
    public JksqdPrintProcessor() {
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

        if (vo == null)
        {
            throw new NullPointerException();
        }

        switch (vo.getAction())
        {
            case CodeConstant.SMSB_QUERYACTION:
                result = doQuery(vo);
                break;
            default:
                throw new ApplicationException(
                    "ActionType�д���processor���Ҳ�����Ӧ�ķ���.");
        }
        return result;
    }

    /**
     * ��ѯ
     * 
     * Ǯ��  2006.2.12  �޸�
     * Ǯ��  2006.2.23  �޸�
     *      ��������д�ˣ�����ormap�������Ϊֱ�Ӳ���ϸ��
     * 
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return ��ǰҳ���Ӧ��Form����
     * @throws BaseException
     */
    private Object doQuery (VOPackage vo)
        throws BaseException
    {
        List dataList = new ArrayList();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        BigDecimal hjje = new BigDecimal("0.00");
        BigDecimal tmpdd;

        JksqdPrintForm pf = new JksqdPrintForm();
        pf = (JksqdPrintForm) vo.getData();
        //���ø�ʽ������
        DecimalFormat deFormat = new DecimalFormat("#0.00");
        ArrayList results = new ArrayList();
        
        String tempstr;

        try
        {
            UserData ud = (UserData) vo.getUserData();
            HashMap djmap = new HashMap();
            ZRRJBSJ zrrJbsj = new ZRRJBSJ();
            String qxdm = InterfaceDj.getQxdm(ud);
            SWDJJBSJ dj = null;

            try
            {
                //�������Ȼ�ˣ��������Ȼ�˽ӿ�
            	//���Ӳ���Ƿ��˰��ģ�鴦��
                if (((!TinyTools.isCompany(pf.getJsjdm())) && pf.getHeadSjly()
						.equals(CodeConstant.SMSB_SJLY_BJQS))
						|| pf.getHeadSjly()
								.equals(CodeConstant.SMSB_SJLY_ZRRLR)) {
                	
                	System.out.println("===========���Ӳ���Ƿ��˰��ģ�鴦��===============");
					djmap = (HashMap) InterfaceDj.getZRRInfo(pf.getH_jsjdm());
				} else {
					djmap = (HashMap) InterfaceDj.getDjInfo(pf.getH_jsjdm());
				}
            }
            catch (Exception ex1)
            {
                ex1.printStackTrace();
                throw new ApplicationException("�ӵǼǽӿڻ�ȡ��Ϣ����");
            }
            //�������Ȼ�ˣ��������Ȼ�˽ӿ�
           //���Ӳ���Ƿ��˰��ģ�鴦��
            if (((!TinyTools.isCompany(pf.getJsjdm())) && pf.getHeadSjly()
					.equals(CodeConstant.SMSB_SJLY_BJQS))
					|| pf.getHeadSjly().equals(CodeConstant.SMSB_SJLY_ZRRLR))
            {
            	System.out.println("===========���Ӳ���Ƿ��˰��ģ�鴦��===============");
            	//������Ϣ
                zrrJbsj = (ZRRJBSJ)djmap.get(DjOuterConstant.ZRRJBSJ);
                if (zrrJbsj == null)
                {
                    throw new ApplicationException("��ȡ��Ȼ�˵Ǽ���Ϣ����");
                }
                pf.setNsrmc(zrrJbsj.getNsrmc()); //��˰��ȫ��
            } //��ɢ¼���ʱ��ȫ������˰�˵�����
            else if (pf.getHeadSjly().equals(CodeConstant.SMSB_SJLY_LSZSLR))
            {
            }
            else
            {
                dj = (SWDJJBSJ) djmap.get("JBSJ");
                if (dj == null)
                {
                    throw new ApplicationException("��ȡ�Ǽ���Ϣ����");
                }
                pf.setNsrmc(dj.getNsrmc()); //��˰������
            }
            
            conn = SfDBResource.getConnection();
            String sql = 
                "SELECT zb.szdm AS szdm,mx.szsmdm AS szsmdm,mx.sjse AS sjse, " +
                "zb.swjgzzjgdm AS swjgzzjgdm,zb.bz AS bz,zb.sbrq AS sbrq, " +
                "zb.yhmc AS yhmc,zb.zh AS yhzh,mx.yskmdm AS yskmdm, " +
                "zb.gkzzjgdm AS gkzzjgdm " +
                "FROM sbdb.sb_jl_sbjkmx mx,sbdb.sb_jl_sbjkzb zb " +
                "WHERE mx.sbbh=?    AND zb.sbbh=?          AND mx.jkpzh=zb.jkpzh " +
                " AND zb.sjly=?     AND zb.zwbs like '0%0' " +
                //"AND zb.qxdm=? " +
                " AND zb.jsjdm=? " +
                " ORDER BY mx.szsmdm,mx.yskmdm";
            
            ps = conn.prepareStatement(sql);
            ps.setString(1,pf.getSbbh());
            ps.setString(2,pf.getSbbh());
            ps.setString(3,pf.getHeadSjly());
            //ps.setString(4,qxdm);
            //ps.setString(5,pf.getJsjdm());
            ps.setString(4,pf.getJsjdm());
            
            Debug.out("pf.getHeadSjly()=" + pf.getHeadSjly());
            //Debug.out("qxdm=" + qxdm);
            Debug.out("pf.getSbbh()=" + pf.getSbbh());
            Debug.out("pf.getJsjdm()=" + pf.getJsjdm());

            rs = ps.executeQuery();
            tmpQuery tq = null;
            //����ѯ����ŵ������У����ڴ���
            while (rs.next())
            {
                tq = new tmpQuery();
                tq.bz = rs.getString("bz");
                tq.gkzzjgdm = rs.getString("gkzzjgdm");
                tq.sbrq = rs.getTimestamp("sbrq");
                tq.sjse = rs.getBigDecimal("sjse");
                tq.swjgzzjgdm = rs.getString("swjgzzjgdm");
                tq.szdm = rs.getString("szdm");
                tq.szsmdm = rs.getString("szsmdm");
                tq.yhmc = rs.getString("yhmc");
                tq.yhzh = rs.getString("yhzh");
                tq.yskmdm = rs.getString("yskmdm");
                results.add(tq);
            }
            rs.close();
            rs = null;
            ps.close();
            ps = null;
            
            if (results.size() == 0)
            {
                throw new ApplicationException("������걨��ţ��Ҳ����걨���ݡ�");
            }
            if (pf.getHeadSjly().equals(CodeConstant.SMSB_SJLY_LSZSLR))
            {
                pf.setNsrmc(tq.bz.substring(0, tq.bz.indexOf(" #$# "))); // ��˰��ȫ��
            }
            
            if (pf.getHeadSjly().equals(CodeConstant.SMSB_SJLY_BJQS))
            {
                pf.setBz(tq.bz); // ��ע��Ϣ
            }
            
            pf.setSbrq(new SimpleDateFormat("yyyyMMdd").format(tq.sbrq));
            pf.setYhmc(tq.yhmc);
            pf.setZh(tq.yhzh);


            // ���ջ������ƣ�˰���������ƣ�����Ҫ���ݴ��������
            pf.setSwjgzzjgmc(CodeUtils.getCodeBeanLabel("DM_SWJGZZJG",tq.swjgzzjgdm));

            // ��������
            pf.setGkzzjgdm(tq.gkzzjgdm);
            pf.setGkzzjgmc(CodeUtils.getCodeBeanLabel("DM_GKZZJG",tq.gkzzjgdm));

            
            Map preZB = null;
            Map curZB = null;
            int isz;
            for (int i = 0; i < results.size(); i++)
            {
                tq = (tmpQuery) results.get(i);
                preZB = null;
                for(isz = 0;isz < dataList.size();isz++)
                {
                    preZB = (Map)dataList.get(isz);
                    if (((String)preZB.get("szdm")).equals(tq.szdm) &&
                        ((String)preZB.get("yskmdm")).equals(tq.yskmdm))
                    {
                        break;
                    }
                }
                if ((preZB == null) || (isz >= dataList.size()))
                {
                    curZB = new HashMap();
                    curZB.put("szdm",tq.szdm);
                    curZB.put("sjje",tq.sjse);
                    curZB.put("yskmdm",tq.yskmdm);
                    tempstr = CodeUtils.getCodeBeanLabel("DM_SZSM",tq.szdm);
                    if (tq.szsmdm.endsWith("91") || tq.szsmdm.endsWith("92"))
                    {
                        tempstr += "(���ɽ𡢷���)";    
                    }
                    curZB.put("szmc",tempstr);
                    
                    dataList.add(curZB);
                }
                else
                {
                    tmpdd = (BigDecimal)preZB.get("sjje"); 
                    tmpdd = tmpdd.add(tq.sjse);
                    preZB.put("sjje",tmpdd);
                }
                hjje = hjje.add(tq.sjse);
                
            }
            for (int i = 0;i < dataList.size();i ++)
            {
                curZB = (HashMap)dataList.get(i);
                tmpdd = (BigDecimal)curZB.get("sjje"); 
                curZB.put("sjje",deFormat.format(tmpdd));
            }
            pf.setHjjexx(deFormat.format(hjje)); //�ϼƽ��
            Debug.out("�ϼƽ��Сд��" + hjje);
            pf.setSzitem(dataList);
            pf.setHjjedx(Currency.convert(hjje)); //�Ѻϼƽ��ת��Ϊ��д
            Debug.out("�ϼƽ���д��" + hjje);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            throw new ApplicationException(ex.getMessage());
        }
        finally
        {
            if (rs != null)
            {
                try
                {
                    rs.close();
                }
                catch (SQLException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                rs = null;
            }
            if (ps != null)
            {
                try
                {
                    ps.close();
                }
                catch (SQLException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                ps = null;
            }
            SfDBResource.freeConnection(conn);
        }
        return pf;
    }

    /**
     * ʱ��ת��������
     * �磺2008-08-08 00:00:00 ת��Ϊ20080808 00:00:00
     * ���ߣ�2008-08-08 ת��Ϊ20080808
     * @param inTime ���ڻ�����ʱ���ַ���
     * @return �ַ���ʱ���ʽ
     * @throws BaseException
     */
    public static String getFormatDate (String inTime)
        throws BaseException
    {
        if (inTime == null || inTime.equals(""))
        {
            return inTime;
        }
        String result = "";
        String tempStr = inTime.substring(0, 10);
        String defStr = "";

        try
        {
            if (inTime.length() > 15)
            {
                defStr = inTime.substring(10);
            }
            java.text.SimpleDateFormat df = new java.text.SimpleDateFormat(
                "yyyyMMdd");
            result = df.format(java.sql.Date.valueOf(tempStr)) + defStr;
        }
        catch (Exception ex)
        {
            //throw ApplicationException();
        }
        return result;
    } //End of getFormatDate
    
}
