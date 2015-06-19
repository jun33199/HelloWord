/*
 * <p>Title:������˰�г�����֧��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪�Ϲ�Ƽ��ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: ��һ���ſƼ��ɷ����޹�˾</p>
 */
package com.ttsoft.bjtax.shenbao.print.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.dj.model.ZRRJBSJ;
import com.ttsoft.bjtax.shenbao.constant.ActionConstant;
import com.ttsoft.bjtax.shenbao.constant.CodeConstant;
import com.ttsoft.bjtax.shenbao.util.DBResource;
import com.ttsoft.bjtax.shenbao.util.FriendHelper;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.Currency;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: �ɿ����뵥��ӡ</p>
 * 
 * Ǯ���޸� 2006.2.11 
 *     1���޸���ʾ��ʽΪ�����ӽɿ�ר�ýɿ��顱
 * Ǯ���޸�  2006.2.23
 *     1����ʾ��ʽ������һ��Ԥ���Ŀһ����ʾ�����ɽ𷣿��һ��
 * 
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
            case ActionConstant.INT_ACTION_QUERY:
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

        HashMap datamap = (HashMap) vo.getData();
        //���ø�ʽ������
        DecimalFormat deFormat = new DecimalFormat("#0.00");
        ArrayList results = new ArrayList();
        
        try
        {
            UserData ud = (UserData) vo.getUserData();
            ZRRJBSJ zrrJbsj = new ZRRJBSJ();
            String qxdm = ud.getSsdwdm().substring(0,2);
            SWDJJBSJ dj = null;
            String sjly = (String)datamap.get("sjly");
            String jsjdm = (String)datamap.get("jsjdm");
            String sbbh = (String)datamap.get("sbbh");


            //�������Ȼ�ˣ��������Ȼ�˽ӿ�
            if (sjly.equals(CodeConstant.SJLY_SB_ZRR_SBLR))
            {
                //������Ϣ
                zrrJbsj = (ZRRJBSJ)FriendHelper.getZrrjbsj(jsjdm);
                if (zrrJbsj == null)
                {
                    throw new ApplicationException("��ȡ��Ȼ�˵Ǽ���Ϣ����");
                }
                datamap.put("nsrmc",zrrJbsj.getNsrmc()); //��˰��ȫ��
            } 
            else if (sjly.equals(CodeConstant.SJLY_SB_SBLR))
            {
                dj = FriendHelper.getDjJbsj(jsjdm);
                if (dj == null)
                {
                    throw new ApplicationException("��ȡ�Ǽ���Ϣ����");
                }
                datamap.put("nsrmc",dj.getNsrmc()); //��˰��ȫ��
            }
            else
            {
                throw new ApplicationException("��ȡ��Ȼ�˵Ǽ���Ϣ����δ֪��������Դ��" + sjly);
            }


            conn = DBResource.getConnection(DBResource.DB_SHENBAO);
            String sql = 
                "SELECT zb.szdm AS szdm,mx.szsmdm AS szsmdm,mx.sjse AS sjse, " +
                "zb.swjgzzjgdm AS swjgzzjgdm,zb.bz AS bz,zb.sbrq AS sbrq, " +
                "zb.yhmc AS yhmc,zb.zh AS yhzh,mx.yskmdm AS yskmdm, " +
                "zb.gkzzjgdm AS gkzzjgdm " +
                "FROM sbdb.sb_jl_sbjkmx mx,sbdb.sb_jl_sbjkzb zb " +
                "WHERE mx.sbbh=?    AND zb.sbbh=?          AND mx.jkpzh=zb.jkpzh " +
                " AND zb.sjly=?     AND zb.zwbs like '0%0' AND zb.qxdm=? " +
                " AND zb.jsjdm=? " +
                " ORDER BY mx.szsmdm,mx.yskmdm";
            
            ps = conn.prepareStatement(sql);
            ps.setString(1,sbbh);
            ps.setString(2,sbbh);
            ps.setString(3,sjly);
            ps.setString(4,qxdm);
            ps.setString(5,jsjdm);
            
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
            datamap.put("sbrq",new SimpleDateFormat("yyyyMMdd").format(tq.sbrq));
            datamap.put("yhmc",tq.yhmc);
            datamap.put("zh",tq.yhzh);

            // ���ջ������ƣ�˰���������ƣ�����Ҫ���ݴ��������
            datamap.put("swjgzzjgdm",tq.swjgzzjgdm);

            datamap.put("gkzzjgdm",tq.gkzzjgdm);

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
                    curZB.put("szsmdm",tq.szdm);
                    curZB.put("sjje",tq.sjse);
                    curZB.put("yskmdm",tq.yskmdm);
                    
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
            datamap.put("hjjexx",deFormat.format(hjje)); //�ϼƽ��
            datamap.put("datalist",dataList); //�ϼƽ��
            datamap.put("hjjedx",Currency.convert(hjje)); //�ϼƽ��
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            throw new ApplicationException(ex.getMessage());
        }
        finally
        {
            DBResource.destroyConnection(conn);
        }
        return datamap;
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
