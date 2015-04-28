/*
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.print.processor;

import java.math.*;
import java.sql.*;
import java.text.*;
import java.util.*;

import com.ttsoft.bjtax.dj.model.*;
import com.ttsoft.bjtax.sfgl.common.code.*;
import com.ttsoft.bjtax.sfgl.common.db.util.*;
import com.ttsoft.bjtax.shenbao.model.domain.*;
import com.ttsoft.bjtax.smsb.constant.*;
import com.ttsoft.bjtax.smsb.gdzys.constant.GdzysCodeConstant;
import com.ttsoft.bjtax.smsb.print.web.*;
import com.ttsoft.bjtax.smsb.util.*;
import com.ttsoft.common.model.*;
import com.ttsoft.framework.exception.*;
import com.ttsoft.framework.processor.*;
import com.ttsoft.framework.util.*;
import com.ttsoft.framework.util.Currency;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: ��ӡ�ɿ���</p>
 * @author �������飭��½��
 * @version 1.1
 */
public class JksPrintProcessor
    implements Processor
{
    public JksPrintProcessor ()
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
        JksPrintForm pf = new JksPrintForm();
        try
        {
            pf = (JksPrintForm) doQuery(vo);
        }
        catch (Exception ex)
        {
            throw new ApplicationException(ex.getMessage());
        }
        finally
        {
            //SfDBResource.freeConnection(conn);
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
        //String swjgzzjgdm = ""; //˰�������֯��������
        BigDecimal hjje = new BigDecimal("0.00");
        String mxPmmc = ""; //��ϸƷĿ����
        String mxKssl = ""; //��ϸ��˰����
        String mxJsje = ""; //��ϸ��˰���
        String mxSl = ""; //��ϸ��˰��
        String mxSjse = ""; //��ϸʵ��˰��
        String mxFcbl = ""; //��ϸ�ֳɱ���

        JksPrintForm pf = new JksPrintForm();
        pf = (JksPrintForm) vo.getData();
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
            //���ò�ѯ����
            Vector tempVector = new Vector();
            Vector dmVector = new Vector();
            ArrayList dmList = new ArrayList();

            //tempVector.addElement("lrr='" + pf.getLrr() + "'");
            tempVector.addElement("sjly='" + pf.getHeadSjly() + "'"); //������Դ
            //tempVector.addElement("(zyrq is null or zyrq='')");
            tempVector.addElement("zwbs like '" + CodeConstant.SMSB_ZWBS + "%"
                                  + CodeConstant.SMSB_ZWBS + "'");
            //tempVector.addElement("substr(zwbs,1,1)='"+CodeConstant.SMSB_ZWBS+"'");
            //tempVector.addElement("substr(zwbs,20,1)='"+CodeConstant.SMSB_ZWBS20+"'");
            tempVector.addElement("qxdm='" + qxdm + "'");
            tempVector.addElement("jsjdm='" + pf.getHeadJsjdm() + "'");
            tempVector.addElement("jkpzh='" + pf.getHeadJkpzh() + "'");
            //tempVector.addElement("1=1 order by jkpzh asc ");

            //��ѯ
            ArrayList zbList = (ArrayList) da.query(Sbjkzb.class, tempVector);
            //��ֵ�Ż�form����
            if (zbList.size() <= 0)
            {
                throw new ApplicationException("û�з�����������Ϣ��");
            }
            Sbjkzb zb = (Sbjkzb) zbList.get(0);
            //��ֵ�Ż�form����
            //pf.setHeadJkpzh(zb.getJkpzh());
            //pf.setHeadJsjdm(zb.getJsjdm());//
            /**20040413 Shi Yanfeng  **/
            /***����ɢ�Ľɿ����ӡ��ʱ��ѱ�ע����Ϣ��ȥ������ӡ�ˣ���Ϊ��˰�������Ѿ���ӡ�ڽɿ������˰������һ������**/
//          pf.setBz(zb.getBz());//��ע
            //����ռ��˰��Ҫ��ע
            if(pf.isBzVisible()){
            	pf.setBz(zb.getBz());//��ע
            }
            

            pf.setSzdm(CodeUtils.getCodeBeanLabel("DM_SZSM", zb.getSzdm())); //˰������
            //Modified by lufeng 2003-12-13
            pf.setSklx(CodeUtils.getCodeBeanLabel("SKLX_PRINT", zb.getSklxdm())); //˰������
            pf.setHeadTfrq( (String.valueOf(zb.getSbrq())).substring(0, 10)); //�����
            //modified by zhangyj 20131114 start
            pf.setHeadTfrqn( (String.valueOf(zb.getSbrq())).substring(0, 4)); //�������
            pf.setHeadTfrqy( (String.valueOf(zb.getSbrq())).substring(5, 7)); //�������
            pf.setHeadTfrqr( (String.valueOf(zb.getSbrq())).substring(8, 10)); //�������
          //modified by zhangyj 20131114 end
//Update  Start  Zhou kejing 20031113
            //pf.setDm(zb.getLrr()); //��½�˴���
            pf.setDm(zb.getJsjdm()); //���ۻ��ؼ��������
//Update  End    Zhou kejing 20031113

            //���ݼ���������õĵǼ���Ϣ
            /**�ӵǼǽӿ��л�ȡ��Ҫ����Ϣ*/
            HashMap mapDJ = new HashMap();
            
            try
            {
                //�������Ȼ�ˣ��������Ȼ�˽ӿ�
                if (pf.getHeadSjly().equals(CodeConstant.SMSB_SJLY_ZRRLR))
                {
                    /* start added by huxiaofeng 2005.8.16*/
                    //mapDJ = (HashMap) InterfaceDj.getZRRInfo(pf.getHeadJsjdm(),ud);
                    mapDJ = (HashMap) InterfaceDj.getZRRInfo(pf.getHeadJsjdm(),ud);
                    /* end added by huxiaofeng 2005.8.16*/
                //����Ǹ���ռ��˰
                }else if(pf.getHeadSjly().equals(GdzysCodeConstant.SMSB_SJLY_GDZYS)){
                	 //���õǼ�
                	try{
                	  mapDJ = (HashMap) InterfaceDj.getDjInfo_New(pf.getHeadJsjdm(),
                             ud);
                	}catch (Exception ex10){
                		 //ex10.printStackTrace();
                    }
  //System.out.println("JksPrintProcessor##########test1");
                	 SWDJJBSJ dj = (SWDJJBSJ) mapDJ.get("JBSJ");
                	 if (dj == null)
                     {
                		 //System.out.println("JksPrintProcessor##########test11");
                		//������Ȼ��
                		 mapDJ = (HashMap) InterfaceDj.getZRRInfo(pf.getHeadJsjdm(),ud);
                     }
                }
                else
                {
                    /* start added by huxiaofeng 2005.8.16*/
                    //mapDJ = (HashMap) InterfaceDj.getDjInfo(pf.getHeadJsjdm(),ud);
                    mapDJ = (HashMap) InterfaceDj.getDjInfo_New(pf.getHeadJsjdm(),
                        ud);
                    /* end added by huxiaofeng 2005.8.16*/

                }
            }
            catch (Exception ex1)
            {
                ex1.printStackTrace();
                throw new ApplicationException("�ӵǼǽӿڻ�ȡ��Ϣ����");
            }

            //�������Ȼ�ˣ��������Ȼ�˽ӿ�
            if (pf.getHeadSjly().equals(CodeConstant.SMSB_SJLY_ZRRLR))
            {
                //������Ϣ
                ZRRJBSJ zrrJbsj = new ZRRJBSJ();
                try
                {
                    zrrJbsj = InterfaceDj.getZRRJBSJ(pf.getHeadJsjdm());
                    pf.setQc(zrrJbsj.getNsrmc()); //��˰��ȫ��
                    pf.setDh(zrrJbsj.getZzdh()); //��Ӫ��ַ��ϵ�绰
                    //pf.setHeadLsgx(""); //�õ�������ϵ����
                }
                catch (Exception ex2)
                {
                    ex2.printStackTrace();
                    throw new ApplicationException("��ȡ��Ȼ�˵Ǽ���Ϣ����");
                }
            } //��ɢ¼���ʱ��ȫ������˰�˵�����
            else if (pf.getHeadSjly().equals(CodeConstant.SMSB_SJLY_LSZSLR))
            {
                String tempStr = zb.getBz();
                pf.setQc(tempStr.substring(0, tempStr.indexOf(" #$# "))); //��˰��ȫ��
            //����Ǹ���ռ��˰
            }else if(pf.getHeadSjly().equals(GdzysCodeConstant.SMSB_SJLY_GDZYS)){
            	 SWDJJBSJ dj = (SWDJJBSJ) mapDJ.get("JBSJ");
                 if (dj == null)
                 {
                	 //System.out.println("JksPrintProcessor##########test2");
                	 //������Ϣ
                     ZRRJBSJ zrrJbsj = new ZRRJBSJ();
                     try
                     {
                         zrrJbsj = InterfaceDj.getZRRJBSJ(pf.getHeadJsjdm());
                         pf.setQc(zrrJbsj.getNsrmc()); //��˰��ȫ��
                         pf.setDh(zrrJbsj.getZzdh()); //��Ӫ��ַ��ϵ�绰
                         //pf.setHeadLsgx(""); //�õ�������ϵ����
                     }
                     catch (Exception ex2)
                     {
                         ex2.printStackTrace();
                         throw new ApplicationException("��ȡ��Ȼ�˵Ǽ���Ϣ����");
                     }
                 }else{
                	 //System.out.println("JksPrintProcessor##########test22");
                     pf.setQc(dj.getNsrmc()); //��˰������
                     pf.setDh(dj.getJydzlxdm()); //��Ӫ��ַ��ϵ�绰
                 }
            }else
            {
                SWDJJBSJ dj = (SWDJJBSJ) mapDJ.get("JBSJ");
                if (dj == null)
                {
                    throw new ApplicationException("��ȡ�Ǽ���Ϣ����");
                }
                //pf.setHeadLsgx(dj.getLsgxmc()); //�õ�������ϵ����
                pf.setQc(dj.getNsrmc()); //��˰������
                pf.setDh(dj.getJydzlxdm()); //��Ӫ��ַ��ϵ�绰
            }
            //������Ϣ
            pf.setKhyh(zb.getYhmc()); //��������
            pf.setZh(zb.getZh()); //�ʻ�

            //���ջ������ƣ�˰���������ƣ�����Ҫ���ݴ��������
            pf.setHeadZsjgmc(CodeUtils.getCodeBeanLabel("DM_SWJGZZJG",
                zb.getSwjgzzjgdm()));
            //Modified by lufeng 2004-01-03
            //ע�����ͣ��������Ȼ�ˣ���ע�����Ͳ���ʾ
//      if (pf.getHeadSjly().equals(CodeConstant.SMSB_SJLY_ZRRLR)){
//        pf.setHeadZclxmc("");
//      }
//      else {
//        pf.setHeadZclxmc(CodeUtils.getCodeBeanLabel("QSJMSP_DJZCLXDM",
//            zb.getDjzclxdm()));
//      }

            //������ϵ��ע�����Ͷ�����ӡ�ˣ�Modified by lufeng 2004-03-12
            pf.setHeadLsgx(""); //�õ�������ϵ����
            pf.setHeadZclxmc("");

            //Ԥ���Ŀ
            pf.setYskmdm(zb.getYskmdm());
            //��Ҫ���ݴ����ȡԤ���Ŀ����
            pf.setYskmmc(CodeUtils.getCodeBeanLabel("DM_YSKM", zb.getYskmdm()));
            //Ԥ�㼶��
            pf.setYskmjc(CodeUtils.getCodeBeanLabel("DM_YSJC", zb.getYsjcdm()));

            //�տ����
            dmVector.clear();
            dmVector.addElement("swjgzzjgdm='" + zb.getSwjgzzjgdm() + "'");
            dmList = (ArrayList) da.query(Swjgzzjg.class, dmVector);
            if (dmList.size() <= 0)
            {
                throw new ApplicationException("��ȡ�տ������Ϣ����");
            }
            Swjgzzjg swjgzzjg = (Swjgzzjg) dmList.get(0);
            pf.setSkgk("(" + swjgzzjg.getGkjhh() + ")" + swjgzzjg.getSkgk()); //�տ����

            pf.setSkssksrq( (String.valueOf(zb.getSkssksrq())).substring(0, 4)+(String.valueOf(zb.getSkssksrq())).substring(5, 7)+(String.valueOf(zb.getSkssksrq())).substring(8, 10));
            pf.setSkssjsrq( (String.valueOf(zb.getSkssjsrq())).substring(0, 4)+(String.valueOf(zb.getSkssjsrq())).substring(5, 7)+(String.valueOf(zb.getSkssjsrq())).substring(8, 10));
            pf.setSkxjrq( (String.valueOf(zb.getXjrq())).substring(0, 10)); //�޽�����

            //�ɱ༭�������ֶ�
            pf.setEditSkssksrq(getFormatDate( (String.valueOf(zb.getSkssksrq())).
                                             substring(0, 10)));
            pf.setEditSkssjsrq(getFormatDate( (String.valueOf(zb.getSkssjsrq())).
                                             substring(0, 10)));
            pf.setEditSkxjrq(getFormatDate( (String.valueOf(zb.getXjrq())).
                                           substring(0, 10))); //�޽�����

            //��ѯ��ϸ����Ϣ
            tempVector.clear();
            tempVector.addElement("qxdm='" + qxdm + "'");
            tempVector.addElement("jsjdm='" + pf.getHeadJsjdm() + "'");
            tempVector.addElement("jkpzh='" + pf.getHeadJkpzh() + "'");
            ArrayList mxList = (ArrayList) da.query(Sbjkmx.class, tempVector);
            for (int i = 0; i < mxList.size(); i++)
            {
                Sbjkmx mx = (Sbjkmx) mxList.get(i);
                HashMap map = new HashMap();
                map.put("szsmdm", mx.getSzsmdm());
                //����˰��˰Ŀ������˰������
                map.put("szsmmc",
                        CodeUtils.getCodeBeanLabel("DM_SZSM", mx.getSzsmdm()));
                if (mx.getKssl() == null)
                {
                    map.put("kssl", " ");
                }
                else
                {
                    map.put("kssl", String.valueOf(mx.getKssl()));
                    //˰��
                }
                if (mx.getSl() == null)
                {
                    map.put("sl", " ");
                }
                else
                {
                    map.put("sl", String.valueOf(mx.getSl()));

                }
                map.put("jsje",
                        deFormat.format(StringUtil.getDouble(String.
                    valueOf(mx.getJsje()), 0.00)));
                map.put("sjse",
                        deFormat.format(StringUtil.getDouble(String.
                    valueOf(mx.getSjse()), 0.00)));
            	
                // ���������걨�ӿڵõ�Ԥ���Ŀ�ֳɱ�������
            	String fcbl =com.ttsoft.bjtax.shenbao.proxy.ServiceProxy.getYskmFcblmc(zb.getYskmdm(), mx.getSzsmdm(), mx.getSwjgzzjgdm());
    			
                map.put("fcbl", fcbl);

                //��ϸ��Ϣ
                mxPmmc += map.get("szsmmc") + ";;"; //��ϸƷĿ����
                mxKssl += map.get("kssl") + ";;"; //��ϸ��˰����
                mxJsje += map.get("jsje") + ";;"; //��ϸ��˰���
                mxSl += map.get("sl") + ";;"; //��ϸ��˰��
                mxSjse += map.get("sjse") + ";;"; //��ϸʵ��˰��
                mxFcbl += map.get("fcbl") + ";;"; //����ȳ�����    

                //����ϼ�
//        hjje = hjje + StringUtil.getDouble(String.valueOf(mx.getSjse()),0.00);
                BigDecimal tmpBig = new BigDecimal(mx.getSjse().toString());
                tmpBig = tmpBig.setScale(2, BigDecimal.ROUND_HALF_UP);
                hjje = hjje.add(tmpBig);

                dataList.add(map);
            }

            //��ϸ��Ϣ
            pf.setMxPmmc(mxPmmc); //��ϸƷĿ����
            pf.setMxKssl(mxKssl); //��ϸ��˰����
            pf.setMxJsje(mxJsje); //��ϸ��˰���
            pf.setMxSl(mxSl); //��ϸ��˰��
            pf.setMxSjse(mxSjse); //��ϸʵ��˰��
            pf.setMxFcbl(mxFcbl); //��ϸ�ֳɱ��� 
            pf.setHjje(deFormat.format(hjje)); //�ϼƽ��
            pf.setDataList(dataList);
            pf.setHjjedx(Currency.convert(hjje)); //�Ѻϼƽ��ת��Ϊ��д
            //��ɢ¼�룬���µĽɿλ���²���ӡ
            if (pf.getHeadSjly().equals(CodeConstant.SMSB_SJLY_LSZSLR))
            {
                pf.setJkdw(""); //�ɿλ
            }
            else
            {
                pf.setJkdw(pf.getQc()); //�ɿλ
            }

            pf.setDfswjg(CodeUtils.getCodeBeanLabel("DM_SWJGZZJG",
                zb.getSwjgzzjgdm().substring(0, 2) + "00")); //�ط�˰�����

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            throw new ApplicationException(ex.getMessage());
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
        Connection conn = null;
        SfDBUtils sfDB = null;
        Timestamp nowTime = new Timestamp(System.currentTimeMillis());
        JksPrintForm pf = new JksPrintForm();
        pf = (JksPrintForm) vo.getData();

        try
        {
            conn = SfDBResource.getConnection();
            SfDBAccess da = new SfDBAccess(conn);
            //UserData����
            UserData ud = (UserData) vo.getUserData();
            //�õ����ش���
            String qxdm = InterfaceDj.getQxdm(ud);

            //��������
            //da.update(zb);
//        	2014��Ʊ��������Ŀ������˰���޽������޸Ĺ��� zhangyj 20131120               
            String strSql = "update sbdb.sb_jl_sbjkzb " +        
//                            " set skssksrq=to_date('" + pf.getEditSkssksrq()
//                            + "','yyyymmdd')," +
//                            " skssjsrq=to_date('" + pf.getEditSkssjsrq()
//                            + "','yyyymmdd')," +
//                            " xjrq=to_date('" + pf.getEditSkxjrq()
            				" set xjrq=to_date('" + pf.getEditSkxjrq()            
                            + "','yyyymmdd'), " +
                            " lrrq=to_date('"
                            + String.valueOf(nowTime).substring(0, 19)
                            + "','yyyy-mm-dd hh24:mi:ss') " +
                            " where qxdm='" + InterfaceDj.getQxdm(ud) + "'" +
                            " and jsjdm='" + pf.getHeadJsjdm() + "'" +
                            " and jkpzh='" + pf.getHeadJkpzh() + "'";
            try
            {
                da.updateSQL(strSql);
            }
            catch (BaseException ex1)
            {
                ex1.printStackTrace();
                throw new ApplicationException("�����������ݳ���");
            }

            //������ϸ��
            strSql = "update sbdb.sb_jl_sbjkmx " +
//                     " set skssksrq=to_date('" + pf.getEditSkssksrq()
//                     + "','yyyymmdd'), " +
//                     " skssjsrq=to_date('" + pf.getEditSkssjsrq()
//                     + "','yyyymmdd'), " +
                     " set lrrq=to_date('"
                     + String.valueOf(nowTime).substring(0, 19)
                     + "','yyyy-mm-dd hh24:mi:ss') " +
                     " where qxdm='" + InterfaceDj.getQxdm(ud) + "'" +
                     " and jsjdm='" + pf.getHeadJsjdm() + "'" +
                     " and jkpzh='" + pf.getHeadJkpzh() + "'";

            try
            {
                da.updateSQL(strSql);
            }
            catch (BaseException ex1)
            {
                ex1.printStackTrace();
                throw new ApplicationException("������ϸ�����ݳ���");
            }
            

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            throw new ApplicationException(ex.getMessage());
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
//:-)
