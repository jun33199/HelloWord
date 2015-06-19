/*
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.zqwh.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.ttsoft.bjtax.sfgl.common.code.CodeUtils;
import com.ttsoft.bjtax.sfgl.common.db.util.BeanUtil;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBUtils;
import com.ttsoft.bjtax.sfgl.common.util.Debug;
import com.ttsoft.bjtax.sfgl.common.util.LabelValueBean;
import com.ttsoft.bjtax.sfgl.common.util.SfTimeUtil;
import com.ttsoft.bjtax.shenbao.model.domain.Czqrl;
import com.ttsoft.bjtax.shenbao.model.domain.Szzqsz;
import com.ttsoft.bjtax.shenbao.model.domain.Zqrl;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.zqwh.ZqwhHelper;
import com.ttsoft.bjtax.smsb.zqwh.web.SzzqdzForm;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: ˰�����������Ͷ��չ�ϵά��</p>
 * @author �������飭��½��
 * @version 1.1
 */
public class SzzqdzProcessor
    implements Processor
{

    /**
     * �û���Ϣ����
     */
    private UserData userData = null;

    /**
     * ͨ�ô������ģ��
     * @param vo  ���ݴ���ֵ����
     * @return  ���ݽ������[ActionForm]
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
                result = doShow(vo);
                break;
            case CodeConstant.SMSB_QUERYACTION: //��ѯ
                result = doQuery(vo);
                break;
            case CodeConstant.SMSB_SAVEACTION:
                result = doSave(vo);
                break;
            case CodeConstant.SMSB_DELETEACTION:
                result = doDelete(vo);
                break;
            case CodeConstant.SMSB_LOADACTION:
                result = doImport(vo);
                break;
            default:
                throw new UnsupportedOperationException(
                    "Method process() not yet implemented.");
        }
        return result;
    }

    /**
     * ���ǰ̨Ĭ����ʾ���Ƶ�ActionForm
     * @param vo  ���ݴ���ֵ����
     * @return  ���ݽ������[ActionForm]
     * @throws BaseException
     */
    public Object doShow (VOPackage vo)
        throws BaseException
    {
        return null;
    }

    /**
     * ��ò�ѯ�����ActionForm
     * @param vo  ���ݴ���ֵ����
     * @return  ���ݽ������[ActionForm]
     * @throws BaseException
     */
    public Object doQuery (VOPackage vo)
        throws BaseException
    {
        List slist = new ArrayList();
        //�õ�Action���ݹ���ActionForm����
        SzzqdzForm form = (SzzqdzForm) vo.getData();
        //�������ݿ�����
        Connection conn = null;
        try
        {
            conn = SfDBResource.getConnection();

            SfDBUtils sfDB = new SfDBUtils(conn);
            //��¼��ѯ����
            form.setHeadDjzclx(form.getTempDjzclx());
            form.setHeadNd(form.getTempNd());
            form.setHeadSz(form.getTempSz());
            form.setHeadZqlx(form.getTempZqlx());

            //�õ�sql���
            String sql = getQuerySql(form, "query");
            //��������
            slist = sfDB.getDataList(sql
                                     + " ORDER BY A.SZDM,A.ZQLXDM,A.DJZCLXDM");
            if (slist.size() <= 0)
            {
                throw new ApplicationException("û���ҵ�ָ���ļ�¼��");
            }

            form.setDataList(slist);
        }
        catch (Exception ex)
        {
            //�׳��쳣
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
        finally
        {
            //�ͷ����ݿ�����
            SfDBResource.freeConnection(conn);
        }
        return form;
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
        Connection conn = null;
        SzzqdzForm form = (SzzqdzForm) vo.getData();
        String strSql = "delete from sbdb.sb_jl_szzqsz where ";
        String allCon = " ";
        String strSql2 = "delete from sbdb.sb_jl_zqrl where ";
        String allCon2 = " ";
        try
        {
            conn = SfDBResource.getConnection();
            SfDBAccess da = new SfDBAccess(conn);

            String[] deleteCheckbox = form.getDeleteCheckBox();
            if (deleteCheckbox != null)
            {

                Szzqsz szzqsz = new Szzqsz();
                Zqrl zqrl = new Zqrl();
                for (int i = 0; i < deleteCheckbox.length; i++)
                {
                    String primaryKey = deleteCheckbox[i];
                    String tempStr = "";
                    //���ѡ�еļ�¼�ĸ�������ֵ
                    String szdm = primaryKey.substring(0,
                        primaryKey.indexOf("|"));
                    tempStr = primaryKey.substring(primaryKey.indexOf("|") + 1);
                    String zqlxdm = tempStr.substring(0, tempStr.indexOf("|"));
                    String djzclxdm = tempStr.substring(tempStr.lastIndexOf("|")
                        + 1);

                    //ɾ�������������
                    allCon = allCon + " (" +
                             " szdm = '" + szdm + "' " +
                             " and zqlxdm = '" + zqlxdm + "'" +
                             " and ND = '" + form.getTempNd() + "') or ";

                    //��ѯ��ɾ��
                    String wherCon = " ";
                    if (!djzclxdm.equals("*"))
                    {
                        wherCon = " and DJZCLXDM = '" + djzclxdm + "' ";
                    }

                    allCon2 = allCon2 + " (" +
                              " szsmdm like '" + szdm + "%' " +
                              " and zqlxdm = '" + zqlxdm + "'" + wherCon +
                              " and ND = '" + form.getTempNd() + "') or ";
                }
            }
            //ɾ�����ձ�
            strSql = strSql + allCon.substring(0, allCon.length() - 3);
            try
            {
                da.updateSQL(strSql);
            }
            catch (BaseException ex1)
            {
                ex1.printStackTrace();
                throw new ApplicationException("ɾ�����ձ����ݳ���");
            }

            //ɾ����������
            strSql2 = strSql2 + allCon2.substring(0, allCon2.length() - 3);
            try
            {
                da.updateSQL(strSql2);
            }
            catch (BaseException ex1)
            {
                ex1.printStackTrace();
                throw new ApplicationException("ɾ��������������");
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

        return form;
    }

    /**
     * ��������
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return ��ǰҳ���Ӧ��Form����
     * @throws BaseException
     */
    private Object doImport (VOPackage vo)
        throws BaseException
    {
        userData = vo.getUserData();
        SzzqdzForm form = (SzzqdzForm) vo.getData();
        Connection conn = null;
        try
        {
            //������ݿ�����
            conn = SfDBResource.getConnection();
            SfDBAccess da = new SfDBAccess(conn);

            //1.�������
            Vector tempVector = new Vector();
            tempVector.add(" ND = '" + form.getTempNd() + "' ");
            List tempList = da.query(Szzqsz.class, tempVector);
            if (tempList.size() > 0)
            {
                throw new ApplicationException("Ҫ����������ڶ��ձ��Ѿ����ڣ�");
            }

            //2.���������������
            tempVector.clear();
            tempList.clear();
            tempVector.add(" ND = '" + form.getTempNd() + "' ");
            tempList = da.query(Czqrl.class, tempVector);
            if (tempList == null || tempList.size() <= 0)
            {
                throw new ApplicationException("����������û�п������ݣ���������"
                                               + form.getTempNd() +
                                               "��ȵ�����������");
            }

            //3.�����������������У��������
            tempVector.clear();
            tempList.clear();
            tempVector.add(" ND = '" + form.getTempNd() + "' ");
            tempList = da.query(Zqrl.class, tempVector);
            if (tempList.size() > 0)
            {
                da.delete(tempList);
            }

            //4.���ԭ�ж��ձ�����
            tempVector.clear();
            tempList.clear();
            tempVector.add(" ND = '" + form.getImportNd() + "' ");
            tempList = da.query(Szzqsz.class, tempVector);
            if (tempList == null || tempList.size() <= 0)
            {
                throw new ApplicationException("���������" + form.getImportNd() +
                                               "��ȣ����ձ�û�п������ݣ�");
            }

            //5.���ò�����ݣ�Ȼ�������ձ�
            for (int i = 0; i < tempList.size(); i++)
            {
                Szzqsz dz = new Szzqsz();
                dz = (Szzqsz) tempList.get(i);
                dz.setNd(form.getTempNd());
            }
            //�������ݵ����ձ���Ϣ
            try
            {
                da.insert(tempList);
            }
            catch (BaseException ex2)
            {
                ex2.printStackTrace();
                throw new ApplicationException("����ʧ�ܣ�");
            }

            //6.��������ʹ�õ���������
            try
            {
                autoCreateZqrl(form, da);
            }
            catch (BaseException ex1)
            {
                throw new ApplicationException(ex1.getMessage());
            }
            form.setImportNd("");
        }
        catch (Exception ex)
        {
            //�׳��쳣
            ex.printStackTrace();
            throw new ApplicationException(ex.getMessage());
        }
        finally
        {
            //�ͷ����ݿ�����
            SfDBResource.freeConnection(conn);
        }
        return form;
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
        userData = vo.getUserData();
        SzzqdzForm form = (SzzqdzForm) vo.getData();
        Connection conn = null;
        try
        {
            //�õ����ݼ�
            List mapData = form.getDataList();
            if (mapData == null)
            {
                return form;
            }

            //��������������ֵ����
            List mxSaveData = new ArrayList();
            //������ݿ�����
            conn = SfDBResource.getConnection();
            SfDBAccess da = new SfDBAccess(conn);

            //�õ�sql���
            String sql = getQuerySql(form, "save");
            //�������ݣ���ɾ��
            SfDBUtils sfDB = new SfDBUtils(conn);
            List slist = sfDB.getDataList(sql);

            List deleteOfSaveList = new ArrayList();
            for (int i = 0; i < slist.size(); i++)
            {
                Map map = (Map) slist.get(i);
                Szzqsz s = new Szzqsz();
                //�����ݴ���ֵ����
                BeanUtil.populate(s, map);
                deleteOfSaveList.add(s);
            }
            da.delete(deleteOfSaveList);

            //ȡ��˰�����ڣ��Ǽ�ע�����ͣ����ձ����ݣ���ѯԭ�����ݣ��Ա�Ա�
            Vector szzqVector = new Vector();
            szzqVector.add(" ND = '" + form.getTempNd() + "'");
            List tempList = da.query(Szzqsz.class, szzqVector);

            //�������ֵ
            for (int i = 0; i < mapData.size(); i++)
            {
                Map map = (Map) mapData.get(i);
                Szzqsz orMx = new Szzqsz();
                //�����ݴ��ݸ���ϸֵ����
                BeanUtil.populate(orMx, map);
                orMx.setNd(form.getTempNd()); //�������
                Timestamp timeNow = SfTimeUtil.getNowTimestamp();
                orMx.setCjrq(timeNow);
                orMx.setLrrq(timeNow);
                orMx.setSwjgzzjgdm(userData.getSsdwdm());
                orMx.setZqksrq(new BigDecimal("1")); //�ֶβ���Ϊ�գ�������ʱֵ
                orMx.setZqts(new BigDecimal("10")); //�ֶβ���Ϊ�գ�������ʱֵ
                mxSaveData.add(orMx);
            }

            //���ز���
            if (tempList.size() >= 0)
            {
                for (int i = 0; i < tempList.size(); i++)
                {
                    Szzqsz tmpSzdz1 = (Szzqsz) tempList.get(i);
                    for (int j = 0; j < mxSaveData.size(); j++)
                    {
                        Szzqsz tmpSzdz2 = (Szzqsz) mxSaveData.get(j);
                        //����Ǽ�ע������
                        if (tmpSzdz1.getDjzclxdm().equals("*") ||
                            tmpSzdz2.getDjzclxdm().equals("*") ||
                            tmpSzdz1.getDjzclxdm().equals(tmpSzdz2.getDjzclxdm()))
                        {
                            if (tmpSzdz1.getSzdm().startsWith(tmpSzdz2.getSzdm()) ||
                                tmpSzdz2.getSzdm().startsWith(tmpSzdz1.getSzdm()))
                            {
                                throw new ApplicationException("����ʧ�ܣ�˰�֣�" +
                                    CodeUtils.getCodeBeanLabel("DM_SZSM",
                                    tmpSzdz1.getSzdm()) +
                                    " �ڵ�ǰ������Ѿ����ڣ�");
                            }
                        }
                    } //end of loop2
                } //end of loop1
            } //End of if

            //���в������
            try
            {
                //��������
                da.insert(mxSaveData);
            }
            catch (BaseException ex1)
            {
                ex1.printStackTrace();
                throw new ApplicationException(ex1.getMessage() + "����ʧ�ܣ�");
            }

            //Modified by lufeng 2004-02-19
            //����˰�������������ձ�
            try
            {
                createSzZqrl(form, da, mxSaveData);
            }
            catch (BaseException ex2)
            {
                throw new ApplicationException("����ʧ�ܣ�" + ex2.getMessage());
            }
            //����˰��˰Ŀ����
            //updateSzsmdm(new SfDBUtils(conn), form.getTempNd());
        }
        catch (Exception ex)
        {
            //�׳��쳣
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
        finally
        {
            //�ͷ����ݿ�����
            SfDBResource.freeConnection(conn);
        }
        return form;
    }

    /**
     * ����˰��˰Ŀ��������
     * @param pf ��ǰForm����
     * @param da ���ݷ��ʶ���
     * @param tempList ���������������List
     * @throws BaseException
     */
    private void createSzZqrl (SzzqdzForm pf, SfDBAccess da, List tempList)
        throws
        BaseException
    {
        try
        {
            //���ձ�����ݼ���������ֺ�ĵǼ�ע������
            List szdzList = new ArrayList();
            //����˰�������������ձ�
            //ɾ�����������˰����������
            //��������е���������
            Zqrl zqrl = new Zqrl();
            Vector zqrlVector = new Vector();
            //��������ɾ����Ȼ���ٲ���
            String whereCon = "";
            if (pf.getTempDjzclx() != null && !pf.getTempDjzclx().equals("*") &&
                !pf.getTempDjzclx().equals(""))
            {
                whereCon += " AND DJZCLXDM='" + pf.getTempDjzclx() + "'";
            }
            if (pf.getTempSz() != null && !pf.getTempSz().equals("*") &&
                !pf.getTempSz().equals(""))
            {
                whereCon += " AND SZSMDM LIKE '" + pf.getTempSz() + "%'";
            }
            if (pf.getTempZqlx() != null && ! (pf.getTempZqlx()).equals("*") &&
                ! (pf.getTempZqlx()).equals(""))
            {
                whereCon += " AND ZQLXDM='" + pf.getTempZqlx() + "'";
            }

            whereCon = "delete from sbdb.sb_jl_zqrl where 1=1 " + whereCon +
                       " AND ND = '" + pf.getTempNd() + "'";
            try
            {
                da.updateSQL(whereCon);
            }
            catch (BaseException ex2)
            {
                ex2.printStackTrace();
                throw new ApplicationException("ɾ��ԭ����������ʧ�ܣ�");
            }

            //ȡ��˰�����ڣ��Ǽ�ע�����ͣ����ձ�����
            Vector szzqVector = new Vector();
            whereCon = "";
            if (pf.getTempDjzclx() != null && !pf.getTempDjzclx().equals("*") &&
                !pf.getTempDjzclx().equals(""))
            {
                whereCon += " AND DJZCLXDM='" + pf.getTempDjzclx() + "'";
            }
            if (pf.getTempSz() != null && !pf.getTempSz().equals("*") &&
                !pf.getTempSz().equals(""))
            {
                whereCon += " AND SZDM LIKE '" + pf.getTempSz() + "%'";
            }
            if (pf.getTempZqlx() != null && ! (pf.getTempZqlx()).equals("*") &&
                ! (pf.getTempZqlx()).equals(""))
            {
                whereCon += " AND ZQLXDM='" + pf.getTempZqlx() + "'";
            }

            ZqwhHelper helper = new ZqwhHelper();
            ZqwhHelper helper2 = new ZqwhHelper();
            //�õ�����˰Ŀ����
            ArrayList smList = (ArrayList) helper.getSmList();
            ArrayList smTempList = new ArrayList();
            smTempList = (ArrayList) smList.clone();
            //�õ�����˰�ִ���
            List allSzList = helper2.getSzList();
            //�õ����еǼ�ע�����ʹ���
            List allDjzclxList = helper.getDjzclxList();
            //�ѵǼ�ע�������б��е�*��ȥ��
            for (int i = 0; i < allDjzclxList.size(); i++)
            {
                LabelValueBean djzclx = (LabelValueBean) allDjzclxList.get(i);
                if (djzclx.getValue().equals("*"))
                {
                    allDjzclxList.remove(i);
                    break;
                }
            }

            //�Ѷ��ձ�ĵǼ�ע������Ϊ��ȫ������*����ת��Ϊ������
            for (int i = 0; i < tempList.size(); i++)
            {
                Szzqsz dz = (Szzqsz) tempList.get(i);
                if (dz.getDjzclxdm().equals("*"))
                {
                    for (int n = 0; n < allDjzclxList.size(); n++)
                    {
                        Szzqsz dzTemp = new Szzqsz();
                        dzTemp.setCjrq(dz.getCjrq());
                        dzTemp.setLrrq(dz.getLrrq());
                        dzTemp.setNd(dz.getNd());
                        dzTemp.setSwjgzzjgdm(dz.getSwjgzzjgdm());
                        dzTemp.setSzdm(dz.getSzdm());
                        dzTemp.setZqksrq(dz.getZqksrq());
                        dzTemp.setZqlxdm(dz.getZqlxdm());
                        dzTemp.setZqts(dz.getZqts());

                        LabelValueBean djzclx = (LabelValueBean) allDjzclxList.
                                                get(n);
                        dzTemp.setDjzclxdm(djzclx.getValue());
                        //������õĶ��ձ���Ϣ����List
                        szdzList.add(dzTemp);
                    }
                }
                else
                {
                    //������õĶ��ձ���Ϣ����List
                    szdzList.add(dz);
                }
            }

            //����zqrl���������յ���������
            try
            {
                insertZqrl(da, szdzList, smTempList, allSzList, pf.getTempNd());
            }
            catch (BaseException ex1)
            {
                throw new ApplicationException("����������������ʧ�ܣ�" + ex1.getMessage());
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }

    }

    /**
     * ��˰�������������в�������
     * @param da ���ݲ�������
     * @param dzList ���ձ�����
     * @param smList ˰Ŀ����
     * @param allSzList ˰������
     * @param strNd ���
     * @throws BaseException
     */
    private void insertZqrl (SfDBAccess da, List dzList, List smList,
                             List allSzList, String strNd)
        throws BaseException
    {
        Timestamp timeNow = SfTimeUtil.getNowTimestamp();
        String[] ZQRLNames =
            {
            "szsmdm", "zqlxdm", "zqlxmc", "zqssrqq", "zqssrqz",
            "zqqsrq",
            "zqzzrq"};
        try
        {
            //1.�õ���ǰ������е������������Ա�Ա�
            Vector tempVector = new Vector();
            tempVector.add(" ND = '" + strNd + "' ");
            List zqrlList = da.query(Zqrl.class, tempVector);

            //2.�õ�����
            tempVector.clear();
            tempVector = new Vector();
            tempVector.add(" ND = '" + strNd + "' ");
            List tempList = da.query(Czqrl.class, tempVector);

            if (tempList.size() <= 0)
            {
                throw new ApplicationException("����������û�п������ݣ���������" + strNd
                                               + "��ȵ�����������");
            }

            Timestamp nowTime = new Timestamp(System.currentTimeMillis());
            //ѭ�����ձ����Ϣ��
            List insertZqrlList = new ArrayList();
            for (int i = 0; i < dzList.size(); i++)
            {
                Szzqsz szzq = (Szzqsz) dzList.get(i);
                String strSzdm = szzq.getSzdm();
                //�õ��������Ͷ�Ӧ���м���������
                List czqrlList = new ArrayList();
                for (int k = 0; k < tempList.size(); k++)
                {
                    Czqrl tempCzqrl = (Czqrl) tempList.get(k);
                    if (tempCzqrl.getZqlxdm().equals(szzq.getZqlxdm()))
                    {
                        czqrlList.add(tempCzqrl);
                    }
                } //End of czqrl

                //3.ѭ������˰Ŀ������Ҷ�ӵ�˰Ŀ����
                for (int j = 0; j < smList.size(); j++)
                {
                    LabelValueBean smBean = (LabelValueBean) smList.get(j);
                    if (smBean.getValue().startsWith(strSzdm))
                    {
                        //ѭ������
                        for (int k = 0; k < czqrlList.size(); k++)
                        {
                            Czqrl czqrl = (Czqrl) czqrlList.get(k);
                            Zqrl tmpZq = new Zqrl();
                            BeanUtil.copyBeanToBean(ZQRLNames, czqrl, tmpZq);
                            //����������������ֵ
                            tmpZq.setSzsmdm(smBean.getValue());
                            tmpZq.setCjrq(timeNow);
                            tmpZq.setLrrq(timeNow);
                            tmpZq.setSwjgzzjgdm(userData.getSsdwdm());
                            tmpZq.setNd(strNd);
                            tmpZq.setDjzclxdm(szzq.getDjzclxdm()); //�Ǽ�ע������
                            tmpZq.setZqksrq(szzq.getZqksrq()); //���ڿ�ʼ����
                            tmpZq.setZqts(szzq.getZqts()); //��������

                            //�Ѷ������List
                            insertZqrlList.add(tmpZq);
                        }
                    } //End of if
                }
            } //End of first loop

            //��������
            try
            {
                if (insertZqrlList.size() > 0)
                {
                    da.insert(insertZqrlList);
                }
            }
            catch (Exception ex2)
            {
                ex2.printStackTrace();
                throw new ApplicationException("���������������ݳ���");
            }

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    /**
     * ����˰��˰Ŀ��������
     * @param pf ��ǰForm����
     * @param da ���ݷ��ʶ���
     * @throws BaseException
     */
    private void autoCreateZqrl (SzzqdzForm pf, SfDBAccess da)
        throws
        BaseException
    {
        try
        {
            //���ձ�����ݼ���������ֺ�ĵǼ�ע������
            List szdzList = new ArrayList();
            //����˰�������������ձ�
            //��������е���������
            Zqrl zqrl = new Zqrl();
            Vector zqrlVector = new Vector();

            //ȡ��˰�����ڣ��Ǽ�ע�����ͣ����ձ�����
            Vector szzqVector = new Vector();
            szzqVector.add(" ND = '" + pf.getTempNd() + "'");
            List tempList = da.query(Szzqsz.class, szzqVector);

            ZqwhHelper helper = new ZqwhHelper();
            ZqwhHelper helper2 = new ZqwhHelper();
            //�õ�����˰Ŀ����
            ArrayList smList = (ArrayList) helper.getSmList();
            ArrayList smTempList = new ArrayList();
            smTempList = (ArrayList) smList.clone();
            //�õ�����˰�ִ���
            List allSzList = helper2.getSzList();
            //�õ����еǼ�ע�����ʹ���
            List allDjzclxList = helper.getDjzclxList();
            //�ѵǼ�ע�������б��е�*��ȥ��
            for (int i = 0; i < allDjzclxList.size(); i++)
            {
                LabelValueBean djzclx = (LabelValueBean) allDjzclxList.get(i);
                if (djzclx.getValue().equals("*"))
                {
                    allDjzclxList.remove(i);
                    break;
                }
            }

            //�Ѷ��ձ�ĵǼ�ע������Ϊ��ȫ������*����ת��Ϊ������
            for (int i = 0; i < tempList.size(); i++)
            {
                Szzqsz dz = (Szzqsz) tempList.get(i);
                if (dz.getDjzclxdm().equals("*"))
                {
                    for (int n = 0; n < allDjzclxList.size(); n++)
                    {
                        Szzqsz dzTemp = new Szzqsz();
                        dzTemp.setCjrq(dz.getCjrq());
                        dzTemp.setLrrq(dz.getLrrq());
                        dzTemp.setNd(dz.getNd());
                        dzTemp.setSwjgzzjgdm(dz.getSwjgzzjgdm());
                        dzTemp.setSzdm(dz.getSzdm());
                        dzTemp.setZqksrq(dz.getZqksrq());
                        dzTemp.setZqlxdm(dz.getZqlxdm());
                        dzTemp.setZqts(dz.getZqts());

                        LabelValueBean djzclx = (LabelValueBean) allDjzclxList.
                                                get(n);
                        dzTemp.setDjzclxdm(djzclx.getValue());
                        //������õĶ��ձ���Ϣ����List
                        szdzList.add(dzTemp);
                    }
                }
                else
                {
                    //������õĶ��ձ���Ϣ����List
                    szdzList.add(dz);
                }
            }

            //����zqrl���������յ���������
            try
            {
                insertZqrl(da, szdzList, smTempList, allSzList, pf.getTempNd());
            }
            catch (BaseException ex1)
            {
                throw new ApplicationException(ex1.getMessage());
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }

    }

    /**
     * �õ���ѯ����SQL���
     * @param sf ��ǰҳ���Ӧ��Form����
     * @param flag ��ѯ��query���򱣴棨save�����
     * @return sql�������
     */
    private String getQuerySql (SzzqdzForm sf, String flag)
    {
        String sql = "";
        String whereCon = "";
        //�õ���ѯ����
        //��ѯ����ʹ���û�����ֵ��������ʹ����ʱֵ
        if (flag.equals("query"))
        {
            if (sf.getHeadDjzclx() != null && !sf.getHeadDjzclx().equals("*") &&
                !sf.getHeadDjzclx().equals(""))
            {
                whereCon += " AND A.DJZCLXDM='" + sf.getHeadDjzclx() + "'";
            }
            if (sf.getHeadSz() != null && !sf.getHeadSz().equals("*") &&
                !sf.getHeadSz().equals(""))
            {
                whereCon += " AND A.SZDM LIKE '" + sf.getHeadSz() + "%'";
            }
            if (sf.getHeadZqlx() != null && ! (sf.getHeadZqlx()).equals("*") &&
                ! (sf.getHeadZqlx()).equals(""))
            {
                whereCon += " AND A.ZQLXDM='" + sf.getHeadZqlx() + "'";
            }
        }
        else if (flag.equals("save"))
        {
            if (sf.getTempDjzclx() != null && !sf.getTempDjzclx().equals("*") &&
                !sf.getTempDjzclx().equals(""))
            {
                whereCon += " AND A.DJZCLXDM='" + sf.getTempDjzclx() + "'";
            }
            if (sf.getTempSz() != null && !sf.getTempSz().equals("*") &&
                !sf.getTempSz().equals(""))
            {
                whereCon += " AND A.SZDM LIKE '" + sf.getTempSz() + "%'";
            }
            if (sf.getTempZqlx() != null && ! (sf.getTempZqlx()).equals("*") &&
                ! (sf.getTempZqlx()).equals(""))
            {
                whereCon += " AND A.ZQLXDM='" + sf.getTempZqlx() + "'";
            }
        }

        sql =
            "SELECT A.SZDM,A.ZQLXDM,A.ND,A.DJZCLXDM FROM SBDB.SB_JL_SZZQSZ A "
            + " WHERE A.ND = '" + sf.getTempNd() + "' "
            + whereCon;

        return sql;
    }

    /**
     * ��Timestamp��ת��Ϊһ��String������ ��eg.20030611��
     * @param gCalendar  ����
     * @return String������ ��eg.20030611��
     */
    private String calendarToString (Calendar gCalendar)
    {
        int month = gCalendar.get(Calendar.MONTH) + 1;
        int date = gCalendar.get(Calendar.DATE);
        String strMonth = "" + month;
        String strDate = "" + date;
        if (month < 10)
        {
            strMonth = "0" + strMonth;
        }
        if (date < 10)
        {
            strDate = "0" + strDate;
        }
        String strDay = "" + gCalendar.get(Calendar.YEAR) + strMonth + strDate;
        return strDay;
    }

    /**
     * ����˰��˰Ŀ�����
     * @param sfDB ���ݷ��ʶ���
     * @param Nd ���
     * @throws ApplicationException
     */
    private void updateSzsmdm (SfDBUtils sfDB, String Nd)
        throws
        ApplicationException
    {
        try
        {
            String updateSql =
                " UPDATE DMDB.SB_DM_SZSM x SET x.zqlxdm=(SELECT d.jglxdm FROM "
                + " (select distinct A.SZSMDM,B.JGLXDM "
                + " from SBDB.SB_JL_ZQRL A,DMDB.SB_DM_ZQLX B "
                + " where A.ZQLXDM = B.ZQLXDM and  B.TYRQ is Null "
                + " and Length(A.SZSMDM)=6 AND A.ND ='" + Nd + "') d "
                + " WHERE x.szsmdm=d.szsmdm) "
                + " WHERE x.szsmdm IN (select distinct A.SZSMDM "
                + " from SBDB.SB_JL_ZQRL A,DMDB.SB_DM_ZQLX B "
                + " where A.ZQLXDM = B.ZQLXDM and  B.TYRQ is Null "
                + " and Length(A.SZSMDM)=6 AND A.ND ='" + Nd + "') ";
            int result = sfDB.executeSql(updateSql);
            if (result == -1)
            {
                throw new Exception();
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new ApplicationException("����˰��˰Ŀ�����ʱ��������");
        }
    }

}
