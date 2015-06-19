/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.wjgr.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.struts.action.ActionForm;
import com.ttsoft.bjtax.dj.DjOuterConstant;
import com.ttsoft.bjtax.dj.model.FB_JBSJ;
import com.ttsoft.bjtax.dj.model.FB_KKQK;
import com.ttsoft.bjtax.dj.model.ZRRJBSJ;
import com.ttsoft.bjtax.dj.model.ZRRYHZH;
import com.ttsoft.bjtax.sfgl.common.code.CodeManager;
import com.ttsoft.bjtax.sfgl.common.code.CodeUtils;
import com.ttsoft.bjtax.sfgl.common.constant.CodeConstants;
import com.ttsoft.bjtax.sfgl.common.db.util.BeanUtil;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.sfgl.common.util.SfStringUtils;
import com.ttsoft.bjtax.shenbao.model.client.SBData;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkmx;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb;
import com.ttsoft.bjtax.shenbao.model.domain.Wbzhrmb;
import com.ttsoft.bjtax.shenbao.model.domain.Zrrgrsdsmx;
import com.ttsoft.bjtax.shenbao.model.domain.Zrrgrsdsz;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.util.Debug;
import com.ttsoft.bjtax.smsb.util.EArray;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.JksUtil;
import com.ttsoft.bjtax.smsb.util.Skssrq;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.bjtax.smsb.wjgr.web.WjgrActionForm;
import com.ttsoft.bjtax.smsb.wjgr.web.WjgrJkswhActionForm;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description: �⼮��������˰�·��걨��</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class WjgrProcessor
    implements Processor
{
    /**
     * ��չProcessor�ӿ�
     * @param vo  �����������ݶ�������Action
     * @return  ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]
     * @throws com.ttsoft.framework.exception.BaseException
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
                break;
            case CodeConstant.SMSB_QUERYACTION: //��ѯ
                return this.doQuery(vo);
            case CodeConstant.SMSB_SAVEACTION:
                return this.doSave(vo);
            case CodeConstant.SMSB_DELETEACTION:
            	return doMoveToHis(vo);    
            case CodeConstant.SMSB_ZHSB_INITLIST:
                return this.doJkswh(vo);
            case CodeConstant.SMSB_CXJKSACTION:
                return this.doCx(vo);
            default:
                throw new UnsupportedOperationException(
                    "Method process() not yet implemented.");
        }
        return result;
    }

    /**
     * ��ѯ�ɿ�������
     * modified by qianchao 2005.10.31
     * @param vo  ���ݴ���ֵ����
     * @return  ���ݽ������[ActionForm]
     * @throws BaseException
     */
    public Object doJkswh (VOPackage vo)
        throws BaseException
    {
        //�õ�actionForm
        WjgrJkswhActionForm form = (WjgrJkswhActionForm) vo.getData();
        //��ʼ�����ݿ�����
        Connection conn = null;
        ArrayList ar = new ArrayList();

        SBData sb = null;
        HashMap sbmaps = new HashMap();

        try
        {
            //�õ����ݿ�����
            conn = SfDBResource.getConnection();
            SfDBAccess db = new SfDBAccess(conn);
            //�õ��걨���
            String sbbh = form.getSbbh();
            Vector v = new Vector();
            //��ѯ���нɿ���
            v.add("qxdm='" + InterfaceDj.getQxdm(vo.getUserData()) + "'");
            v.add("jsjdm='" + form.getJsjdm() + "'");

            if (sbbh == null || sbbh.equals(""))
            {

                v.add("sjly='" + CodeConstant.SMSB_SJLY_ZRRLR + "'");
                v.add("zwbs like  '" + CodeConstant.SMSB_ZWBS + "%" +
                      CodeConstant.SMSB_ZWBS + "'  order by jkpzh desc ");
            }
            else
            {
                //��ѯ�����걨
                v.add("sbbh='" + sbbh + "' order by jkpzh desc");
            }

            Debug.out("v=" + v);

            List list = db.query(new Sbjkzb().getClass(), v);
            Debug.out("list.size()=" + list.size());

            //��ʽ����ѯ�������ݣ���װ��SBData�С�
            Sbjkzb zb = null;

            for (int i = 0; i < list.size(); i++)
            {
              zb = (Sbjkzb)list.get(i);

              Debug.out("zb.getJkpzh()=" + zb.getJkpzh());

              sb = (SBData)sbmaps.get(zb.getSbbh());
              if (sb == null)
              {
                sb = new SBData();
                sbmaps.put(zb.getSbbh(),sb);
                ar.add(sb);
              }
              sb.addSbjkzb(zb);
            }


            form.setDataList(ar);
            return form;
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "��ѯ�ɿ����б�ʧ��!");
        }
        finally
        {
            //�ͷ����ݿ�����
            SfDBResource.freeConnection(conn);
        }

    }

    /**
     * �����ɿ��飬ͬʱɾ����Ȼ�˱�����Ӧ����
     * modified by qianchao 2005.10.31
     * ԭ����ʵ�ֱ���ȫ������
     * �µ�ʵ���� �����걨�е���Ȼ���걨��processorһ�¡�
     *
     * @param vo  ���ݴ���ֵ����
     * @return  ���ݽ������[ActionForm]
     * @throws BaseException
     */
    public Object doCx (VOPackage vo)
        throws BaseException
    {
        //�õ�actionForm
        WjgrJkswhActionForm form = (WjgrJkswhActionForm) vo.getData();

        //���ϵ��걨���
        String zfSbbh = form.getSbbh();

        String jsjdm = form.getJsjdm();
        UserData ud = vo.getUserData();
        String qxdm = ud.getSsdwdm().substring(0,2);

        String sql = null;
        String sqlZb = null;

        sql = " WHERE QXDM = '" + qxdm
            + "' AND SBBH = '" + zfSbbh + "'";
        sqlZb = " WHERE ZYRQ >= to_date('20040101','yyyymmdd') AND QXDM = '"
            + qxdm + "' AND JSJDM = '" + jsjdm
            + "' AND SBBH = '" + zfSbbh
            + "' AND ((substr(zwbs, 1, 1) = '0') AND (substr(zwbs, 20, 1) = '0' ))";


        String sqlJkpzh = "select count(*) from sbdb.sb_jl_sbjkzb  " +  sql;
        ResultSet rs = null;
        int zbCount = 0;
        int deleteCount[] = null;
        Connection conn = null;
        try
        {
            //������ݿ�����
            conn = SfDBResource.getConnection();
            Statement st = conn.createStatement();
            StringBuffer sqlStringBuffer = new StringBuffer();

            Debug.out("sqlJkpzh=" + sqlJkpzh);

            rs = st.executeQuery(sqlJkpzh);
            rs.next();
            zbCount = rs.getInt(1);
            Debug.out("�����¼�� zbCount=" + zbCount);

            st.clearBatch();

            //����sql��䣬������


            //ɾ���ɿ���ϸ����
            sqlStringBuffer.append("DELETE FROM SBDB.SB_JL_SBJKMX ").append(sql);

            Debug.out("1==sqlStringBuffer.toString()=" + sqlStringBuffer.toString());

            st.addBatch(sqlStringBuffer.toString());

            //ɾ���ɿ���������
            sqlStringBuffer.setLength(0);
            sqlStringBuffer.append("DELETE FROM SBDB.SB_JL_SBJKZB ").append(
                sqlZb);

            Debug.out("2==sqlStringBuffer.toString()=" + sqlStringBuffer.toString());

            st.addBatch(sqlStringBuffer.toString());

            deleteCount = st.executeBatch();

            Debug.out("����ɾ����¼�� deleteCount[1]=" + deleteCount[1]);
            if (deleteCount[1] != zbCount)
            {
                throw new ApplicationException("Ҫɾ�����걨�Ѿ��ɿ");
            }

            sqlStringBuffer.setLength(0);
            st.clearBatch();

            //ɾ������ۺ����������
            sqlStringBuffer.append("DELETE FROM SBDB.SB_JL_WBZHRMB ").append(
                sql);

            Debug.out("3==sqlStringBuffer.toString()=" + sqlStringBuffer.toString());

            st.addBatch(sqlStringBuffer.toString());

            //ɾ����������˰��ϸ
            sqlStringBuffer.setLength(0);
            sqlStringBuffer.append("DELETE FROM SBDB.SB_JL_ZRRGRSDSMX ").append(
                sql);

            Debug.out("4==sqlStringBuffer.toString()=" + sqlStringBuffer.toString());

            st.addBatch(sqlStringBuffer.toString());

            //ɾ����������˰����
            sqlStringBuffer.setLength(0);
            sqlStringBuffer.append("DELETE FROM SBDB.SB_JL_ZRRGRSDSZ ").append(
                sql);

            Debug.out("5==sqlStringBuffer.toString()=" + sqlStringBuffer.toString());

            st.addBatch(sqlStringBuffer.toString());

            sqlStringBuffer.setLength(0);

            st.executeBatch(); //ִ��ɾ��

            return form;
        }
        catch(ApplicationException e)
        {
            throw e;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e, "���Ͻɿ�����ʧ�ܣ�");
        }
        finally
        {
            SfDBResource.freeConnection(conn);
        }

    }

    /**
     * ɾ����Ȼ���걨����
     * deleted by qianchao 2005.11.1
     * @param zbVo �걨��������
     * @param conn ���ݿ�����
     * @throws BaseException
     */
    /*
    private void doDelete (Zrrgrsdsz zbVo, Connection conn)
        throws
        BaseException
    {
        SfDBAccess db = new SfDBAccess(conn);
        //ɾ����ϸ������
        //Zrrgrsdsmx mx = new Zrrgrsdsmx();
        //mx.setJsjdm(zbVo.getJsjdm());
        //mx.setSbrq(zbVo.getSbrq());
        String sbrq = zbVo.getSbrq().toString();
        //db.delete(mx);
        db.querySQL("delete from sbdb.SB_JL_ZRRGRSDSMX where jsjdm='" +
                    zbVo.getJsjdm() + "' and sbbh='" + zbVo.getSbbh() + "'");
        //ɾ����������
        //db.delete(zbVo);
        db.querySQL("delete from sbdb.SB_JL_ZRRGRSDSZ where jsjdm='" +
                    zbVo.getJsjdm() + "' and sbbh='" + zbVo.getSbbh() + "'");
    }*/

    /**
     * ����¼�����ݣ�������Ȼ�˱����ɽɿ��飬�������걨��
     * @param vo  ���ݴ���ֵ����
     * @return  ���ݽ������[ActionForm]
     * @throws BaseException
     */
    public Object doSave (VOPackage vo)
        throws BaseException
    {
        //�õ�actionForm
        WjgrActionForm form = (WjgrActionForm) vo.getData();
        //�õ�userData
        UserData userData = vo.getUserData();
        //����actionform��������ֵ����
        //�⼮��������˰�걨������
        Zrrgrsdsz zbVo = new Zrrgrsdsz();
        //��Ӧform��������
        String[] names =
            {
            "jsjdm", "sbrq", "zjlxdm", "zjhm", "gjdm", "skssksrq",
            "skssjsrq",
            "dhrq", "jnzz", "zydm", "fwdw", "fwdd", "dwfdbl"};
        //�õ���ǰʱ��
        Timestamp now = new Timestamp( (new java.util.Date()).getTime());
        //��ʼ�����ݿ�����
        Connection conn = null;
        try
        {

            //�õ����ݿ�����
            conn = SfDBResource.getConnection();
            SfDBAccess db = new SfDBAccess(conn);
            //��form��Ӧ�����Ը��Ƹ�vo
            BeanUtil.copyBeanToBean(names, form, zbVo);
            //ͨ���Ǽǽӿ�ȡ����˰�������Ϣ
            ZRRJBSJ jbsj = InterfaceDj.getZRRJBSJ(zbVo.getJsjdm());
//      ZRRJBSJ jbsj = InterfaceDj.getZRRJBSJ(zbVo.getZjlxdm(), zbVo.getZjhm(),
//                                            zbVo.getGjdm());

            //����������걨ʱ��Ϊ�걨ʱ�������
            //zbVo.setSbrq(new Timestamp(TinyTools.getDayTime(zbVo.getSbrq()).getTime()));
            //����userData��Ϣ
            //������
            if (userData != null)
            {
                zbVo.setCjr(userData.getYhid());
            }
            else
            {
                zbVo.setCjr("smsb");
            }
            //����ʱ��
            //zbVo.setCjsj(now);
            zbVo.setCjrq(now);
            //¼��ʱ��
            zbVo.setLrrq(now);
            //���ش���
            zbVo.setQxdm(InterfaceDj.getQxdm(userData));
            //˰�������֯��������
            zbVo.setSwjgzzjgdm(jbsj.getSwjgzzjgdm());
            //��ϵ�绰
            zbVo.setDh(jbsj.getZzdh());
            //������������
            Debug.out(zbVo);
            //db.insert(zbVo);

            //������ϸ�������ɽɿ���
            List mxList = new ArrayList();
            //��ϸ���ݴ���
            ArrayList list = form.getDataList();
            HashMap bzMap = new HashMap();
            for (int i = 0; i < list.size(); i++)
            {
                //�õ���ϸmap
                HashMap map = (HashMap) list.get(i);


                Debug.out("map=" + map);

                String bzmx = (String) map.get("bzmx");

                //����ϸmap�������õ���ϸֵ����
                Zrrgrsdsmx orMx = new Zrrgrsdsmx();
                BeanUtil.populate(orMx, map);

                Debug.out("orMx.getJe()=" + orMx.getJe());
                Debug.out("orMx.getRmbhj()=" + orMx.getRmbhj());
                Debug.out("orMx.getYbtsk()=" + orMx.getYbtsk());
                Debug.out("orMx.getYjzgsk()=" + orMx.getYjzgsk());
                Debug.out("orMx.getYkjse()=" + orMx.getYkjse());
                Debug.out("orMx.getYnssde()=" + orMx.getYnssde());
                Debug.out("orMx.getYnse()=" + orMx.getYnse());

                //���������ϸ����
                bzMap.put(orMx.getSzsmdm(), bzmx);
                //����������Ϣ
                //����ʱ��
                orMx.setCjrq(zbVo.getCjrq());
                //¼������
                orMx.setLrrq(zbVo.getLrrq());
                //���������
                orMx.setJsjdm(zbVo.getJsjdm());
                //�걨����
                orMx.setSbrq(zbVo.getSbrq());
                //���ش���
                orMx.setQxdm(zbVo.getQxdm());
                //˰�������֯��������
                orMx.setSwjgzzjgdm(zbVo.getSwjgzzjgdm());
                //������ϸ����
                //db.insert(orMx);
                //�����ϸ�б�
                mxList.add(orMx);
            }

            //���ɽɿ���
            ArrayList retList = (ArrayList)this.creatJks(zbVo, mxList, vo);
            form.setDataList(retList);
            String sbbh = form.getSbbh();

            Debug.out("sbbh=" + sbbh);

            //������������
            //�����걨���
            zbVo.setSbbh(sbbh);
            db.insert(zbVo);
            //������ϸ����
            for (int i = 0; i < mxList.size(); i++)
            {
                Zrrgrsdsmx orMx = (Zrrgrsdsmx) mxList.get(i);
                //�����걨���
                orMx.setSbbh(sbbh);
                //�������
                this.saveBz(bzMap, orMx, db);
                //������ϸ����
                db.insert(orMx);
            }
            return form;
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "�����걨����ʧ��!");
        }
        finally
        {
            //�ͷ����ݿ�����
            SfDBResource.freeConnection(conn);
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
     * ��ѯ����
     * @param vo  ���ݴ���ֵ����
     * @return  ���ݽ������[ActionForm]
     * @throws BaseException
     */
    public Object doQuery (VOPackage vo)
        throws BaseException
    {
        WjgrActionForm form = (WjgrActionForm) vo.getData();
        EArray jsArray = new EArray();
        String tempJsStr = "";
        tempJsStr +=
            jsArray.getMsgsByCode("szsmdm", "WJGR_SZSMDM",
                                  (ArrayList) form.getDataList());
        try
        {
            //������˰�˻�����Ϣ
            this.setInitInfo(form, vo.getUserData());

            tempJsStr += this.getSzsmJsStr(form);
            //�õ�����ʹ�õ�JS����
            //�õ������б�ʹ�õ�js����
            tempJsStr += this.getWbhsJsArray(this.getWbhsList(form));
            tempJsStr +=
                jsArray.getMsgsByCode("bzdm", "BZ",
                                      (ArrayList) form.getDataList());

            form.setScriptStr(tempJsStr);
//      //������˰�˻�����Ϣ
//      this.setInitInfo(form, vo.getUserData());
        }
        catch (BaseException ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
        return form;
    }

    /**
     * ͨ���Ǽǽӿ�������˰�˻�����Ϣ
     * @param  actionForm �⼮����actionform
     * @param  userData ����Ա��Ϣ
     * @throws BaseException ����˰����Ϣ�����ڵ�ʱ���׳��쳣
     */
    private void setInitInfo (ActionForm actionForm, UserData userData)
        throws
        BaseException
    {
        WjgrActionForm form = (WjgrActionForm) actionForm;
        //ͨ���Ǽǽӿڵõ���˰�˻�����Ϣ
        try
        {
            //ZRRJBSJ jbsj = InterfaceDj.getZRRJBSJ(form.getJsjdm());
//      HashMap zrrInfo = InterfaceDj.getZrrInfo(form.getZjlxdm(), form.getZjhm(),
//                                               form.getGjdm());
            /*ʹ����Ȼ�˵ļ����������Ϊ��ѯ���� 20040303 Shi Yanfeng*/
//      HashMap zrrInfo = InterfaceDj.getZrrInfo(form.getZjlxdm(), form.getZjhm(),
//                                               form.getGjdm(), userData);
            HashMap zrrInfo = (HashMap) InterfaceDj.getZRRInfo(form.getJsjdm(),
                userData);
            //��Ȼ�˻�����Ϣ
            ZRRJBSJ jbsj = (ZRRJBSJ) zrrInfo.get(DjOuterConstant.ZRRJBSJ);
            if (jbsj == null)
            {
                throw new ApplicationException("�����ڸ���˰����Ϣ��������Ȩ�����ü�������룡");
            }
            //�ۿ����
            List kkqkList = (List) zrrInfo.get(DjOuterConstant.ZRRKKQK);
            //�����˺���Ϣ
            List bankList = (List) zrrInfo.get(DjOuterConstant.ZRRYHZH);
            bankList = this.modifyBankList(bankList);
            //���������˺���Ϣ
            form.setBankList(bankList);
            //�ⷿ�ѿ۳���
            BigDecimal zffkce = new BigDecimal(0);
            if (kkqkList != null)
            {
                for (int i = 0; i < kkqkList.size(); i++)
                {
                    //�ϼ��ⷿ�ѿ۳���
                    FB_KKQK temp = (FB_KKQK) kkqkList.get(i);
                    if (temp.getKcxmdm().equals("1"))
                    {
                        zffkce = zffkce.add(temp.getKcje());
                    }
                }
            }
            //�����ⷿ�ѿ۳���
            form.setZffkcs(zffkce.toString());
//      ZRRJBSJ jbsj = InterfaceDj.getZRRJBSJ(form.getZjlxdm(), form.getZjhm(),
//                                            form.getGjdm());

//    //��Ȼ�˸�����Ϣ
            FB_JBSJ fbJbsj = (FB_JBSJ) zrrInfo.get(DjOuterConstant.ZRRFB);
            //���������
//      form.setJsjdm(jbsj.getJsjdm());

            //֤������
            form.setZjlxdm(jbsj.getZjlxdm());
            //֤������
            form.setZjhm(jbsj.getZjhm());
            //��˰������
            form.setNsrmc(jbsj.getNsrmc());
            //���ù��Ҵ���
            form.setGjdm(jbsj.getGjdm());
            //���ù�������
            form.setGjmc(jbsj.getGjmc());
            //�ֻ�����
            if (jbsj.getDhrq() != null)
            {
                form.setDhrq(SfDateUtil.getDate(jbsj.getDhrq()));
            }
            else
            {
                form.setDhrq("");
            }
            //ְҵ
            form.setZydm(jbsj.getZydm());
            form.setZymc(CodeUtils.getCodeBeanLabel("ZYDM", jbsj.getZydm()));
            //��ַ
            form.setJnzz(jbsj.getTxdz());
            if (fbJbsj != null)
            {
                //����˰�˴��ڸ�����Ϣ
                //˰�����ʽ
                form.setFdfs(fbJbsj.getSkfdqk());
                //�������
                if (fbJbsj.getSkfdbl() != null)
                {
                    form.setDwfdbl(String.valueOf(fbJbsj.getSkfdbl().
                                                  doubleValue()));
                }
//        //��פ��ʾ
//        if (fbJbsj.getCzbs().equals("0")) {
//          form.setSfczbs("��");
//        }
//        else {
//          form.setSfczbs("��");
//        }
                form.setSfczbs(fbJbsj.getCzbs());
            }
            else
            {
                form.setFdfs("");
                form.setDwfdbl("");
                form.setSfczbs("");
            }

            //ȡ��˰����������
            Map skssrq = Skssrq.monthSkssrq(SfDateUtil.getDate(form.getSbrq()));
            form.setSkssjsrq(SfDateUtil.getDate( (Date) skssrq.get(Skssrq.
                SKSSJSRQ)));
            form.setSkssksrq(SfDateUtil.getDate( (Date) skssrq.get(Skssrq.
                SKSSKSRQ)));

        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }

    }

    /**
     * �Ǽ���ʲô���С��˺����Ǿ���ʾʲô��
     * ���������˵����и��յ����к��˺ţ��ɹ�������Աѡ�����ѡ���˿����ӡΪ�գ�
     * @param list �����˺��б�
     * @return List
     */
    private List modifyBankList (List list)
    {
        ZRRYHZH yhzh = new ZRRYHZH();
        list.add(yhzh);
        return list;
    }

    /**
     * ����ǰ̨��ʾ��js����
     * @param actionForm �⼮����actionform
     * @throws BaseException
     * @return String
     */
    private String getSzsmJsStr (ActionForm actionForm)
        throws BaseException
    {
        String jsStr = "\nvar szsmlist_fields = [\"szsmdm\",\"szsmmc\",\"szsmdm_old\",\"sl\",\"ynsqss\",\"ynszzs\",\"sskcs\",\"sdksrq\",\"sdjsrq\",\"jfye\",\"bhsdsqs\",\"bhsdszz\",\"bhsdssl\",\"bhsdskcs\"];";
        try
        {
            List szsmList = this.getSzsmList(actionForm);
            jsStr += "\n" + this.getSzsmJsArray(szsmList);
            //Debug.out(szsmList);
        }
        catch (BaseException ex)
        {
            throw ExceptionUtil.getBaseException(ex, "����ǰ̨��ʾ��js����ʧ��!");
        }
        return jsStr;
    }

    /**
     * ����˰��˰Ŀlist
     * @param actionForm �⼮����actionform
     * @throws BaseException
     * @return List
     */
    private List getSzsmList (ActionForm actionForm)
        throws BaseException
    {
        WjgrActionForm form = (WjgrActionForm) actionForm;
        List ret = new ArrayList();
        //ͨ�������õ�˰��˰Ŀ����
        try
        {
            List szsm = CodeManager.getCodeList("WJGRSDS",
                                                CodeConstants.CODE_MAP_MAPLIST).
                        getRecords();
            //����˰��˰Ŀ�б�����map�б�
            String[] name =
                {
                "szsmdm", "szsmmc", "szsmdm_old", "sl", "ynsqss",
                "ynszzs", "sskcs",
                "skssksrq", "skssjsrq"};
            for (int i = 0; i < szsm.size(); i++)
            {
                //�õ�˰��˰Ŀֵ����
                Map szsmVo = (Map) szsm.get(i);
                szsmVo.put("szsmdm_old", szsmVo.get("szsmdm"));
                //ȡ��˰����������
                Map skssrq = Skssrq.monthSkssrq(SfDateUtil.getDate(form.getSbrq()));
                szsmVo.put("sdksrq",
                           SfDateUtil.getDate( (Date) skssrq.get(Skssrq.
                    SKSSKSRQ)));
                szsmVo.put("sdjsrq",
                           SfDateUtil.getDate( (Date) skssrq.get(Skssrq.
                    SKSSJSRQ)));
                //�����ö�
                String szsmdmSub = ( (String) szsmVo.get("szsmdm"));
                if (szsmdmSub.length() > 4)
                {
                    szsmdmSub = szsmdmSub.substring(0, 4);
                }
                //if (szsmdmSub.equals("0501")) {
                if (szsmdmSub.equals(CodeConstant.ZRR_SZSMDM_GXSD))
                {
                    if (form.getGjdm().equals("CHN"))
                    {
                        //�й���ʱ�������ö�=1000
                        szsmVo.put("jfye", CodeConstant.ZRR_JFYE_CHN);
                    }
                    else
                    {
                        //���й��ˣ������ö�=4000
                        szsmVo.put("jfye", CodeConstant.ZRR_JFYE_ELSE);
                    }
                }
                else
                {
                    szsmVo.put("jfye", CodeConstant.ZRR_JFYE_NORMAL);
                }
                ret.add(szsmVo);
            }
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "����˰��˰Ŀ�б�����ʧ��!");
        }
        return ret;
    }

    /**
     * �õ���ϸ��js����
     * @param szsm ˰��˰Ŀ�б�
     * @return String
     */
    private String getSzsmJsArray (List szsm)
    {
        StringBuffer ret = new StringBuffer();
        try
        {
            for (int i = 0; i < szsm.size(); i++)
            {
                Map temp = (Map) szsm.get(i);
                ret.append("[");
                //˰��˰Ŀ����
                ret.append("\"" + temp.get("szsmdm") + "\",");
                //˰��˰Ŀ����
                ret.append("\"" + temp.get("szsmmc") + "\",");
                ret.append("\"" + temp.get("szsmdm_old") + "\",");
                //˰��
                ret.append("\"" + temp.get("sl") + "\",");
                //Ӧ��˰��ʼ��
                ret.append("\"" + temp.get("ynsqss") + "\",");
                //Ӧ��˰��ֹ��
                ret.append("\"" + temp.get("ynszzs") + "\",");
                //����۳���
                ret.append("\"" + temp.get("sskcs") + "\",");
                //ȡ��˰����������
                ret.append("\"" + temp.get("sdksrq") + "\",");
                ret.append("\"" + temp.get("sdjsrq") + "\",");
                //�����ö�
                ret.append("\"" + temp.get("jfye") + "\",");
                //����˰����
                ret.append("\"" + temp.get("bhsdsqs") + "\",");
                ret.append("\"" + temp.get("bhsdszz") + "\",");
                //����˰����˰��
                ret.append("\"" + temp.get("bhsdssl") + "\",");
                //����˰��������۳���
                ret.append("\"" + temp.get("bhsdskcs") + "\"");
                ret.append("],");
            }
            if (ret.length() > 0)
            {
                //��������ݣ���ɾ�������ӵĶ���
                ret.delete(ret.length() - 1, ret.length());
            }
            ret.append("];");
            return "var szsmlist = [" +
                SfStringUtils.replaceAll(ret.toString(), "null", "");

        }
        catch (Exception ex)
        {
            return "var szsmlist=new Array();";
        }
    }

    /**
     * �õ����µ���㻻����Ϣ�б�
     * @param actionForm �⼮����actionform
     * @return List
     * @throws BaseException
     */
    private List getWbhsList (ActionForm actionForm)
        throws BaseException
    {
        //WjgrActionForm form = (WjgrActionForm) actionForm;
        //�õ��걨����
        //��ѯ�����ڵ����µ�����Ƽ�
        Date rq = TinyTools.addMonth( -1, new Date());
        //�õ���
        int year = TinyTools.getYear(rq);
        //�õ���
        int month = TinyTools.getMonth(rq) + 1;
        List ret = new ArrayList();
        Connection conn = null;
        try
        {
            conn = SfDBResource.getConnection();
            SfDBAccess db = new SfDBAccess(conn);

//      Vector v = new Vector();
//      v.add("nd='"+year+"' and yf='"+month+"'");
//      ret = db.query(new Wbhs().getClass(), v);
            ResultSet rs = db.querySQL("select a.BZDM bzdm,a.bzmc bzmc, b.WHPJ whpj from dmdb.GY_DM_BZ a, dmdb.SB_DM_WBHS b where a.BZDM=b.BZDM(+) and nd(+)='" +
                                       year + "' and yf(+)='" + month +
                                       "' order by a.XSSX ");
            while (rs.next())
            {
                Map temp = new HashMap();
                //���ִ���
                temp.put("bzdm", rs.getString("bzdm"));
                //��������
                temp.put("bzmc", rs.getString("bzmc"));
                //����Ƽ�
                temp.put("whpj", rs.getBigDecimal("whpj"));
                ret.add(temp);
            }
            return ret;
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "�õ����µ���㻻����Ϣ�б�ʧ��!");
        }
        finally
        {
            SfDBResource.freeConnection(conn);
        }

    }

    /**
     * �õ���һ����js����
     * @param  wbhs ��һ���List
     * @return String
     */
    private String getWbhsJsArray (List wbhs)
    {
        String jsStr =
            "\nvar bzlist_fields = [\"bzdm\",\"bzmc\",\"whpj\",\"bzdm_old\"];";
        StringBuffer ret = new StringBuffer();
        //ret.append(jsStr);
        for (int i = 0; i < wbhs.size(); i++)
        {
            Map temp = (Map) wbhs.get(i);
            ret.append("[");
            ret.append("\"" + temp.get("bzdm") + "\",");
            ret.append("\"" + temp.get("bzmc") + "\",");
            ret.append("\"" + temp.get("whpj") + "\",");
            ret.append("\"" + temp.get("bzdm") + "\"");
            ret.append("],");
        }
        if (ret.length() > 0)
        {
            //��������ݣ���ɾ�������ӵĶ���
            ret.delete(ret.length() - 1, ret.length());
        }
        ret.append("];");
        String retStr = "\nvar bzlist = [" +
                        SfStringUtils.replaceAll(ret.toString(), "null", "");
        retStr = jsStr + retStr;
        //return "\nvar bzlist = [" +
        //    SfStringUtils.replaceAll(ret.toString(), "null", "");
        return retStr;

    }

    /**
     * ���ɽɿ���
     * @param zbVo ��������
     * @param mxList ��ϵ�б�
     * @param vo ���ݼ�����
     * @return List ���ɵĽɿ�����Ϣ�б�
     * @throws BaseException
     */
    private List creatJks (Zrrgrsdsz zbVo, List mxList, VOPackage vo)
        throws
        BaseException
    {
        //�õ�actionForm
        WjgrActionForm form = (WjgrActionForm) vo.getData();
        //ormapping����
        Sbjkzb orObj = new Sbjkzb();
        //���UserData
        UserData ud = vo.getUserData();
        //������form�ֵ�columns һ���ı��������е�����Ϊ��ϸ���ֶ���
        String names[] =
            {
            "jsjdm", "sbrq", "lrrq", "cjrq", "qxdm"};
        Timestamp now = new Timestamp( (new java.util.Date()).getTime());
        try
        {

            //��form�ж�Ӧ������Ϣ���浽ֵ����
            BeanUtil.copyBeanToBean(names, zbVo, orObj);
            //ͨ���Ǽǽӿ�ȡ����˰�������Ϣ
            //ZRRJBSJ jbsj = InterfaceDj.getZRRJBSJ(zbVo.getJsjdm());
            //����������Ϣ
            //���������˺�
            orObj.setYhdm(form.getYhdm());
            orObj.setYhmc(form.getYhmc());
            orObj.setZh(form.getZh());
            //��Ȼ���걨�ɿ������Ϊ�й�ʱ�Ǽ�ע������Ϊ410��������Ϊ���й�ʱ�Ǽ�ע������Ϊ390
            /*******************************************/
            /**              Shi Yanfeng              **/
            /* 20040107*/
            /*******************************************/
            if (zbVo.getGjdm().equals(CodeConstant.GJDM_CHN))
            {
                //������Ϊ�й�ʱ�Ǽ�ע������Ϊ410
                //�Ǽ�ע������
                orObj.setDjzclxdm(CodeConstant.ZRR_JKS_DJZCLXDM);
            }
            else
            {
                //������Ϊ���й�ʱ�Ǽ�ע������Ϊ390
                orObj.setDjzclxdm(CodeConstant.ZRR_JKS_DJZCLXDM_UNCN);
            }
            //���ұ�׼��ҵ����
            orObj.setGjbzhydm(CodeConstant.ZRR_JKS_GJBZHYDM);
            //˰�������֯����
            orObj.setSwjgzzjgdm(zbVo.getSwjgzzjgdm());
            //���ջ���
            orObj.setZsswjgzzjgdm(zbVo.getSwjgzzjgdm());
            //������ϵ
            //orObj.setLsgxdm(jbsj.getLsgxdm());
            //��Ӫ��ַ��ϵ�绰
            orObj.setJydzlxdm(zbVo.getDh());
            //¼����
            orObj.setLrr("smsb");
            if (ud != null)
            {
                orObj.setLrr(ud.getYhid());
            }
            //˰������
            orObj.setSklxdm(CodeConstant.SKLXDM_ZCJK);
            orObj.setSklxmc(CodeConstant.SKLXMC_ZCJK);
            //�걨��ʽ����
            orObj.setFsdm(CodeConstant.FSDM_SMSB);
            //������Դ
            orObj.setSjly(CodeConstant.SMSB_SJLY_ZRRLR);
            //�걨�ɿ���ϸ�б�
            List list = new ArrayList();
            //�����걨�ɿ���ϸ�б�
            for (int i = 0; i < mxList.size(); i++)
            {
                //�õ���Ȼ���걨��ϸ
                Zrrgrsdsmx orMx = (Zrrgrsdsmx) mxList.get(i);
                //�����걨�ɿ���ϸ
                Sbjkmx sbjkmx = new Sbjkmx();
                //���������
                sbjkmx.setJsjdm(orMx.getJsjdm());
                //˰��˰Ŀ����
                sbjkmx.setSzsmdm(orMx.getSzsmdm());
                //˰�ִ���
                sbjkmx.setSzdm(orMx.getSzsmdm().substring(0, 2));
                //��˰���
                sbjkmx.setJsje(orMx.getYnssde());
                //ʵ��˰��  modified by zhangyj 20131225 BUG�޸�
                //sbjkmx.setSjse(orMx.getYnse());
                sbjkmx.setSjse(orMx.getYbtsk());
                //˰����������
                sbjkmx.setSkssjsrq(orMx.getSdjsrq());
                sbjkmx.setSkssksrq(orMx.getSdksrq());
                //����˰��
                sbjkmx.setSl(orMx.getSl());
                //����˰�������֯��������
                sbjkmx.setSwjgzzjgdm(zbVo.getSwjgzzjgdm());
                Debug.out(sbjkmx);

                Debug.out("sbjkmx.getRkje()=" + sbjkmx.getRkje());
                Debug.out("sbjkmx.getSzsmdm()=" + sbjkmx.getSzsmdm());
                Debug.out("sbjkmx.getSjse()=" + sbjkmx.getSjse());
                //��ӽ���ϸ�б�
                list.add(sbjkmx);
            }

            //�����ݽ��з�Ʊ����
            JksUtil ju = new JksUtil();
            //start modifying by qianchao 2005.11.1
            //ͨ��jksutil��getsbbhȡ���걨��ţ��浽form�С�
            Debug.out("form.getJksType()=" + form.getJksType());
            List retlist = ju.getJkDataZRR(orObj, list, form.getJksType());
            form.setSbbh(ju.getSbbh());
            return retlist;

            //end modifying by qianchao 2005.11.1
            //return null;
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "ϵͳ�����������Ա��ϵ!");
        }

    }

    /**
     * �õ��걨���
     *
     * deleted by qianchao 2005.11.1
     * �걨���sbbh���Դ�form��ȡ���ˡ�
     *
     * @param  mxList ��ϵ�б�
     * @return String ���ɵĽɿ�����Ϣ�б�
     */
    /*
    private String getSbbh (List mxList)
    {
        String sbbh = "";
        DeclareInfor dclInfo = new DeclareInfor();
        if (mxList.size() > 0)
        {

          Debug.out("mxList.get(0).getClass().getName()=" + mxList.get(0).getClass().getName());
            dclInfo = (DeclareInfor) mxList.get(0);
            Sbjkzb temp = dclInfo.getSbjkzb();
            sbbh = temp.getSbbh();
        }
        return sbbh;
    }*/

    /**
     * ��������б�
     * @param bzMap �����б�
     * @param orMx ��ϸ
     * @param db ���ݿ�����
     * @throws BaseException
     */
    private void saveBz (HashMap bzMap, Zrrgrsdsmx orMx, SfDBAccess db)
        throws
        BaseException
    {
        //�õ�����ϸ�ı���
        String bzmx = (String) bzMap.get(orMx.getSzsmdm());
        if (bzmx == null || bzmx.length() <= 0)
        {
            //û��¼������򷵻�
            return;
        }

        try
        {
            String[] mx = SfStringUtils.split(bzmx, "#");
            for (int i = 0; i < mx.length; i++)
            {

                //�õ���ϸ����
                Map map = SfStringUtils.splitString(mx[i]);
                //������ϸ����ֵ����
                Wbzhrmb wb = new Wbzhrmb();
                BeanUtil.populate(wb, map);
                //������ϸ����
                //���������
                wb.setJsjdm(orMx.getJsjdm());
                wb.setCjrq(orMx.getCjrq());
                wb.setLrrq(orMx.getLrrq());
                wb.setSbbh(orMx.getSbbh());
                wb.setSbrq(orMx.getSbrq());
                wb.setSdjsrq(orMx.getSdjsrq());
                wb.setSdksrq(orMx.getSdksrq());
                wb.setSwjgzzjgdm(orMx.getSwjgzzjgdm());
                wb.setSzsmdm(orMx.getSzsmdm());
                wb.setQxdm(orMx.getQxdm());

                db.insert(wb);
            }
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "ϵͳ�����������Ա��ϵ!");
        }

    }

    /**
     * ɾ������б�
     * deleted by qianchao 2005.11.1
     * @param zbVo ����
     * @param conn  ���ݿ�����
     * @throws BaseException
     */
    /*
    private void deleteBz (Zrrgrsdsz zbVo, Connection conn)
        throws
        BaseException
    {
        SfDBAccess db = new SfDBAccess(conn);
        //ɾ���������
        db.querySQL("delete from sbdb.SB_JL_WBZHRMB where jsjdm='"
                    + zbVo.getJsjdm() +
                    "' and sbbh='" + zbVo.getSbbh() + "'");
    }*/

}
