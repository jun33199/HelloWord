/*
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.lszs.processor;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.dj.model.YHZH;
import com.ttsoft.bjtax.sfgl.common.code.CodeUtils;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBUtils;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.sfgl.common.util.SfTimeUtil;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.lszs.web.LszsJkslrForm;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.JksUtil;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: ��ɢ����¼��ɿ���</p>
 * @author �������飭��½��
 * @version 1.1
 */
public class LszsJkslrProcessor
    implements Processor
{
    public LszsJkslrProcessor ()
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
                result = doMoveToHis(vo);
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
        List dataList = new ArrayList();
        Connection conn = null;
        SfDBUtils sfDB = null;

        LszsJkslrForm pf = new LszsJkslrForm();
        pf = (LszsJkslrForm) vo.getData();
        try
        {
            //UserData����
            UserData ud = (UserData) vo.getUserData();
            //�����
            pf.setSbrq(SfDateUtil.getDate());
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
            pf.setSwjgzzjgdm(dj.getSwjgzzjgdm()); //˰�������֯��������
            pf.setSwjgzzjgmc(dj.getSwjgzzjgmc()); //˰�������֯��������

        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
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
        LszsJkslrForm pf = new LszsJkslrForm();
        pf = (LszsJkslrForm) vo.getData();

        try
        {
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
            pf.setNsrmc(dj.getNsrmc());
            pf.setGjbzhydm(dj.getGjbzhydm()); //���ұ�׼��ҵ����
            pf.setDjzclxdm(dj.getDjzclxdm()); //�Ǽ�ע�����ʹ���
            pf.setDjzclxmc(dj.getDjzclxmc()); //
            pf.setDz(dj.getJydz()); //��ַ����Ӫ��ַ
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
     * ����
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return ��ǰҳ���Ӧ��Form����
     * @throws BaseException
     */
    private Object doSave (VOPackage vo)
        throws BaseException
    {
        String qxdm = ""; //���ش���
        //ormapping����
        Sbjkzb orObj = new Sbjkzb();

        //start modifying by qianchao 2005.10.26
        HashMap hm = (HashMap)vo.getData();
        //��ȡform����
        LszsJkslrForm form = (LszsJkslrForm)hm.get(CodeConstant.MAP_VO_FORM);
        int ypysORypds = ((Integer)hm.get(CodeConstant.MAP_VO_YPYSorYPDS)).intValue();
        //end modifying by qianchao 2005.10.26



        //���UserData
        UserData ud = vo.getUserData();
        //������form�ֵ�columns һ���ı��������е�����Ϊ��ϸ���ֶ���
        Timestamp nowTime = new Timestamp(System.currentTimeMillis());
        try
        {
            //�õ����ش���
            qxdm = InterfaceDj.getQxdm(ud);
            //ͨ���Ǽǽӿ�ȡ����˰�������Ϣ
            //�ӵǼǽӿ��л����Ϣ
            //SWDJJBSJ jbsj = new SWDJJBSJ();
            //����������Ϣ
            HashMap mapDJ = new HashMap();
            //SWDJJBSJ jbsj = new SWDJJBSJ();
            try
            {
                mapDJ = (HashMap) InterfaceDj.getDjInfo(form.getJsjdm(), ud);
                //jbsj = InterfaceDj.getJBSJ(String.valueOf(map.get("jsjdm")), ud);
            }
            catch (Exception ex1)
            {
                ex1.printStackTrace();
                throw ExceptionUtil.getBaseException(ex1);
            }

            //Modified by lufeng 2003-12-09
            SWDJJBSJ jbsj = (SWDJJBSJ) mapDJ.get("JBSJ");
            if (jbsj == null)
            {
                throw new ApplicationException("��ȡ�Ǽ���Ϣ����");
            }

            //����������Ϣ
            //���������
            orObj.setJsjdm(form.getJsjdm());

            //������˰�����ƣ�֤�����ͣ�֤������ ���걨�ɿ�ı�ע�ֶ�
            /**20040413 Shi Yanfeng  **/
            /***����ɢ�Ľɿ����ӡ��ʱ��ѱ�ע����Ϣ��ȥ������ӡ�ˣ���Ϊ��˰�������Ѿ���ӡ�ڽɿ������˰������һ������**/
            orObj.setBz(form.getNsrmc() + " #$# " +
                        CodeUtils.getCodeBeanLabel("ZJLX", form.getZjlxdm())
                        + " " + form.getZjhm());
            //�걨����
            orObj.setSbrq(SfTimeUtil.getTimestamp(form.getSbrq()));
            //�Ǽ�ע������
            orObj.setDjzclxdm(jbsj.getDjzclxdm());
            orObj.setDjzclxmc(jbsj.getDjzclxmc());
            //���ұ�׼��ҵ����
            orObj.setGjbzhydm(jbsj.getGjbzhydm());
            orObj.setGjbzhymc(jbsj.getGjbzhymc());
            //˰�������֯����
            orObj.setSwjgzzjgdm(jbsj.getSwjgzzjgdm());
            orObj.setSwjgzzjgmc(jbsj.getSwjgzzjgmc());
            //���ջ���
            orObj.setZsswjgzzjgdm(jbsj.getSwjgzzjgdm());
            orObj.setZsswjgzzjgmc(jbsj.getSwjgzzjgmc());
            //������ϵ
            orObj.setLsgxdm(jbsj.getLsgxdm());
            orObj.setLsgxmc(jbsj.getLsgxmc());
            //��Ӫ��ַ��ϵ�绰
            orObj.setJydzlxdm(jbsj.getJydzlxdm());
            //¼����
            orObj.setLrr(form.getLrr());
            if (ud != null)
            {
                orObj.setLrr(ud.getYhid());
            }
            //�걨����
            orObj.setSbrq(nowTime);
            orObj.setCjrq(nowTime); //��������
            orObj.setLrrq(nowTime); //¼������
            orObj.setQxdm(qxdm); //���ش���

            //�õ�������Ϣ//Modified by lufeng 2003-12-09
            ArrayList dmList = (ArrayList) mapDJ.get("YHZH");
            for (int i = 0; i < dmList.size(); i++)
            {
                YHZH yhzh = (YHZH) dmList.get(i);
                if (yhzh.getJbzhbs().equals(CodeConstant.SMSB_JBZHBS))
                {
                    orObj.setYhdm(yhzh.getYhdm()); //���д���
                    orObj.setYhmc(yhzh.getKhyhmc()); //��������
                    orObj.setZh(yhzh.getZh()); //�ʻ�
                    break;
                }
            }

            //�걨��ʽ����
            orObj.setFsdm(CodeConstant.FSDM_SMSB);
            //������Դ
            orObj.setSjly(CodeConstant.SMSB_SJLY_LSZSLR);
            //˰������
            //orObj.setSklxdm(CodeConstant.SKLXDM_ZCJK);
            //orObj.setSklxmc(CodeConstant.SKLXMC_ZCJK);
          
            //˰������ tujb 2014.03.13 ����˰����������
            orObj.setSklxdm(form.getSklxdm());
            orObj.setSklxmc(this.getSklxmc(form.getSklxdm()));
            //�����ݽ��з�Ʊ����
            JksUtil ju = new JksUtil();
            //return ju.getJkData(orObj, form.getDataList());
            try
            {

              //start modifying by qianchao 2005.10.26
              System.out.println("jsjdm:" + form.getJsjdm() +  "== ��Ʊ���ͣ�" + ypysORypds);
              List tempList = (ArrayList) ju.getJkDataLS(orObj,form.getDataList(),ypysORypds);
              form.setDataList(tempList);
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
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
        return form;
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
     * ����˰�����ʹ����ѯ˰����������
     * @param sklx String ˰�����ʹ���
     * @return String ˰����������
     */
    private String getSklxmc (String sklx)
    {

        //���ݴ������˰����������
        return CodeUtils.getCodeBeanLabel("LSSB_SKLX", sklx);
    }

}
//:-)
