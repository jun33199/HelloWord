/*
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.lszs.processor;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import java.sql.Timestamp;

import com.ttsoft.bjtax.pzgl.proxy.ServiceProxy;
import com.ttsoft.bjtax.sfgl.common.db.util.BeanUtil;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBUtils;
import com.ttsoft.bjtax.sfgl.common.util.Debug;
import com.ttsoft.bjtax.shenbao.model.domain.Lsswszmx;
import com.ttsoft.bjtax.shenbao.model.domain.Lsswszz;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.lszs.web.LszsCxwszForm;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: ��ɢ���ճ�����˰֤</p>
 * @author �������飭��½��
 * @version 1.1
 */
public class LszsCxwszProcessor
    implements Processor
{
    public LszsCxwszProcessor ()
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
        List dataList = new ArrayList();

        Connection conn = null;
        SfDBUtils sfDB = null;
        Timestamp nowTime = new Timestamp(System.currentTimeMillis());
        String ndzb = String.valueOf(nowTime).substring(0, 4);

        LszsCxwszForm pf = new LszsCxwszForm();
        pf = (LszsCxwszForm) vo.getData();
        //����ֱ�
        pf.setTempNdzb(ndzb);

        try
        {
            //conn = SfDBResource.getConnection();
            //sfDB = new SfDBUtils(conn);

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
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
        String names[] =
                         {
                         "pzzldm", "wszh", "jsjdm", "swjgzzjgdm", "djzclxdm",
                         "nsrmc", "dz", "hjsjje", "clbjdm", "sbhzdh",
                         "jbdh", "zjlxdm", "zjhm", "swjgzzjgdm2", "lrr",
                         "fsdm", "gjbzhydm", "nd", "wszxh", "cjrq",
                         "lrrq", "qxdm", "ndzb", "printflag"};

        Connection conn = null;
        SfDBUtils sfDB = null;
        UserData ud = new UserData();
        LszsCxwszForm pf = new LszsCxwszForm();
        pf = (LszsCxwszForm) vo.getData();

        try
        {
            conn = SfDBResource.getConnection();
            SfDBAccess da = new SfDBAccess(conn);
            //�õ�UserData
            ud = (UserData) vo.getUserData();
            //�õ����ش���
            String qxdm = InterfaceDj.getQxdm(ud);
            //���ò�ѯ����
            Vector tempVector = new Vector();
            tempVector.addElement("qxdm='" + qxdm + "'");
            tempVector.addElement("lrr='" + ud.getYhid() + "'");
            if (!pf.getHeadClbjdm().equals("*"))
            {
                tempVector.addElement("clbjdm='" + pf.getHeadClbjdm() + "'");
            }
            tempVector.addElement(
                "wszxh in (select b.wszxh from sbdb.sb_jl_lsswszmx b WHERE substr(b.jzbz,1,1)='"
                + CodeConstant.SMSB_JZBZ_EDIT + "')");
            tempVector.addElement("(sbhzdh='' or sbhzdh is null)");
            tempVector.addElement("(jbdh='' or jbdh is null)"); //Modified by lufeng 2004-05-11
            tempVector.addElement("ndzb='" + pf.getTempNdzb() + "'");
            tempVector.addElement("1=1 order by wszh asc ");

            //��ѯ
            List tempList = da.query(Lsswszz.class, tempVector);
            if (tempList.size() <= 0)
            {
                throw new ApplicationException("û�з��������ļ�¼��");
            }
            for (int i = 0; i < tempList.size(); i++)
            {
                //��ʽ��ÿ����¼
                Lsswszz wszz = (Lsswszz) tempList.get(i);
                //��ֵ�����ֵ����Map
                HashMap map = new HashMap();
                try
                {
                    BeanUtil.copyBeanToMap(names, wszz, map);
                }
                catch (Exception ex1)
                {
                    ex1.printStackTrace();
                    throw new ApplicationException("����ת������");
                }

                //map.put("hjsjje",wszz.getHjsjje().toString());
                map.put("cjrq", wszz.getCjrq().toString().substring(0, 19));
                map.put("lrrq", wszz.getLrrq().toString().substring(0, 19));
                //������ʾֵ
                if (map.get("clbjdm").equals("1"))
                {
                    map.put("printflag", "�Ѵ�ӡ");
                }
                else
                {
                    map.put("printflag", "δ��ӡ");
                }
                dataList.add(map);
            }
            //��ֵ�Ż�form����
            pf.setDataList(dataList);

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
        List dataList = new ArrayList();
        String pzzldm = ""; //Ʊ֤�������
        String zhdm = ""; //�ʻ�����
        String wszh = ""; //��˰֤��

        Connection conn = null;
        SfDBUtils sfDB = null;
        UserData ud = new UserData();
        //from����
        LszsCxwszForm pf = new LszsCxwszForm();
        pf = (LszsCxwszForm) vo.getData();
        dataList = (List) pf.getDataList();
        //�õ��ʻ�����
        zhdm = pf.getHeadZhdm();

        //ormapping����
        Lsswszz orObjz = new Lsswszz();
        Lsswszmx orObjmx = new Lsswszmx();
        try
        {
            conn = SfDBResource.getConnection();
            SfDBAccess da = new SfDBAccess(conn);
            //�õ�UserData
            ud = (UserData) vo.getUserData();
            //�ĵõ����ش���
            String qxdm = InterfaceDj.getQxdm(ud);
            String strSql = "delete from sbdb.sb_jl_lsswszmx " +
                            //" where pzzldm='" + CodeConstant.SMSB_PZZLDM + "'" +
                            " where qxdm='" + qxdm + "'" +
                            " and substr(jzbz,1,1)='"
                            + CodeConstant.SMSB_JZBZ_EDIT + "'" +
                            " and ndzb='" + pf.getHeadNdzb() + "'" +
                            " and wszh='" + pf.getHeadWszh() + "'" +
                            " and jsjdm='" + pf.getHeadJsjdm() + "'" +
                            " and wszxh='" + pf.getHeadWszxh() + "'";

            //1��ɾ����ϸ������
            try
            {
                da.updateSQL(strSql);
            }
            catch (BaseException ex1)
            {
                ex1.printStackTrace();
                throw new ApplicationException("ɾ����ϸ�����ݳ���");
            }

            //2������������� //Modified by lufeng 2004-05-11
            Vector tempVector = new Vector();
            //tempVector.addElement("pzzldm='" + CodeConstant.SMSB_PZZLDM + "'");
            tempVector.addElement("pzzldm='" + pf.getHeadPzzldm() + "'");
            tempVector.addElement("qxdm='" + qxdm + "'");
            tempVector.addElement("(sbhzdh='' or sbhzdh is null)");
            tempVector.addElement("(jbdh='' or jbdh is null)");
            tempVector.addElement("ndzb='" + pf.getTempNdzb() + "'");
            tempVector.addElement("wszh='" + pf.getHeadWszh() + "'");
            tempVector.addElement("wszxh='" + pf.getHeadWszxh() + "'");
            tempVector.addElement("jsjdm='" + pf.getHeadJsjdm() + "'");

            //��ѯ
            List tempList = da.query(Lsswszz.class, tempVector);
            if (tempList.size() <= 0)
            {
                throw new ApplicationException("û�з��ϳ�������˰֤�����ݿ����ѱ��޸ģ������²�ѯ��");
            }
             else {
            	Lsswszz lsswszzsj = (Lsswszz)tempList.get(0);
            	if (lsswszzsj.getFsdm().equals(CodeConstant.SMSB_FSDM)){
                throw new ApplicationException("���˵������˰֤���ܳ�����");
            	}         
            }
            try
            {
                da.delete(tempList);
            }
            catch (BaseException ex1)
            {
                ex1.printStackTrace();
                throw new ApplicationException("ɾ���������ݳ���");
            }
            //3������Ʊ֤�ӿڣ����ϵ�ǰ��˰֤�ţ�������ȡ��
            try
            {
                String result = ServiceProxy.setCancellation(ud,
                	pf.getHeadPzzldm(),
                    pf.getHeadNdzb() +
                    pf.getHeadWszh(),
                    0, "1", "0", "1");
            }
            catch (Exception ex1)
            {
                throw new ApplicationException("������˰֤�ų���");
            }
            pf.setHeadWszh("");
            pf.setHeadWszxh("");
            pf.setHeadNdzb("");
            pf.setHeadJsjdm("");
        }
        catch (BaseException ex)
        {
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
    private Object doUpdate (VOPackage vo)
        throws BaseException
    {
        Connection conn = null;
        SfDBUtils sfDB = null;
        UserData ud = new UserData();
        //from����
        LszsCxwszForm pf = new LszsCxwszForm();
        pf = (LszsCxwszForm) vo.getData();
        //ormapping����
        Lsswszz orObjz = new Lsswszz();
        try
        {
            conn = SfDBResource.getConnection();
            SfDBAccess da = new SfDBAccess(conn);
            //�õ�UserData
            ud = (UserData) vo.getUserData();
            //�ĵõ����ش���
            String qxdm = InterfaceDj.getQxdm(ud);
            String strSql = "update sbdb.sb_jl_lsswszz " +
                            " set clbjdm='" + CodeConstant.SMSB_WSZ_PRINT + "'" +
                            " where ndzb='" + pf.getHeadNdzb() + "'" +
                            " and pzzldm='" + pf.getHeadPzzldm() + "'" +
                            " and wszh='" + pf.getHeadWszh() + "'" +
                            " and qxdm='" + qxdm + "'" +
                            " and jsjdm='" + pf.getHeadJsjdm() + "'" +
                            " and wszxh='" + pf.getHeadWszxh() + "'";

            //1����������
            try
            {
                da.updateSQL(strSql);
            }
            catch (BaseException ex1)
            {
                ex1.printStackTrace();
                throw new ApplicationException("�������ݳ���");
            }
        }
        catch (BaseException ex)
        {
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
