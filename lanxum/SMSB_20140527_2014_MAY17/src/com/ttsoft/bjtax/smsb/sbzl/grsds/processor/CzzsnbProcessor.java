/*
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.sbzl.grsds.processor;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.common.db.util.BeanUtil;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBUtils;
import com.ttsoft.bjtax.sfgl.common.model.Grzsfs;
import com.ttsoft.bjtax.sfgl.common.table.TableManager;
import com.ttsoft.bjtax.sfgl.common.table.TableType;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.sfgl.common.util.SfHashList;
import com.ttsoft.bjtax.sfgl.model.orobj.Tzf;
import com.ttsoft.bjtax.sfgl.proxy.ServiceProxy;
import com.ttsoft.bjtax.shenbao.model.domain.Czzsnbfpbl;
import com.ttsoft.bjtax.shenbao.model.domain.Czzsnbqy;
import com.ttsoft.bjtax.shenbao.model.domain.Czzsnbtzzmxsj;
import com.ttsoft.bjtax.shenbao.model.domain.Czzsnbtzzsj;
import com.ttsoft.bjtax.shenbao.model.domain.Czzsnbzb;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.sbzl.grsds.web.CzzsnbForm;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.Skssrq;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.DateTimeUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title:  ������˰��������ϵͳ���������걨</p>
 * <p>Description: �������ո��˶�����ҵ�ͺϻ���ҵͶ���߸�������˰����걨����</p>
 * @author �������飭�������
 * @version 1.1
 */
public class CzzsnbProcessor
    implements Processor
{

    /**
     * ʵ��Processor�ӿ�
     * @param vo ҵ�����
     * @return Object VOPackage������
     * @throws BaseException ҵ���쳣
     * 		1 ���������Ĳ������Ͳ���ʱ�׳�
     * 		2 �����õ�ҵ�񷽷��׳�ҵ���쳣ʱ���ϴ����׳�
     * 	�����쳣�׳���EJB��process��������
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
                doSave(vo);
                break;
            case CodeConstant.SMSB_DELETEACTION:
                doDelete(vo);
                break;
            default:
                throw new UnsupportedOperationException(
                    "Method process() not yet implemented.");
        }
        return result;
    }

    /**
     * doShow��ʼ������ҳ����ϢҪ��
     * @param vo ҵ�����
     * @return   ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
     * @throws BaseException ��������������׳��쳣��Ϣ
     */
    public Object doShow (VOPackage vo)
        throws BaseException
    {
        //�õ�Action���ݹ���ActionForm����
        CzzsnbForm form = (CzzsnbForm) vo.getData();
        // ��ʼ��ActionForm
        initForm(form);
        return form;
    }

    /**
     * doQuery    ���ڷ���ҳ����Ҫ��ѯ���꾡��Ϣ
     * @param     vo ҵ�����
     * @return    ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
     * @throws BaseException    ��������������׳��쳣��Ϣ
     */
    public Object doQuery (VOPackage vo)
        throws BaseException
    {
        //�õ�Action���ݹ���ActionForm����
        CzzsnbForm form = (CzzsnbForm) vo.getData();
        form.setNsrmc("");
        form.setJydzlxdm("");
        UserData ud = (UserData) vo.getUserData();
        //ȡ��ҵ�ĵǼ���Ϣ
        SWDJJBSJ djxx = null;
        try
        {
            /* start added by huxiaofeng 2005.8.16*/
            //djxx = InterfaceDj.getJBSJ(form.getJsjdm(), ud);
            djxx = InterfaceDj.getJBSJ_New(form.getJsjdm(), ud);
            form.setNsrzt(djxx.getNsrzt());
            /* end added by huxiaofeng 2005.8.16*/
        }
        catch (Exception ex1)
        {
            throw ExceptionUtil.getBaseException(ex1);
        }
        form.setQxdm(InterfaceDj.getQxdm(ud));
        form.setNsrmc(djxx.getNsrmc());
        form.setSwjgzzjgdm(djxx.getSwjgzzjgdm());
        form.setJydzlxdm(djxx.getJydzlxdm());
        form.setNd(getSbnd(form.getSbrq()));
        form.setFsdm(CodeConstant.FSDM_SMSB);
        Map lastYear = Skssrq.yearSkssrq(SfDateUtil.getDate(form.getSbrq()));
        Timestamp lastskssjsrq = (Timestamp) lastYear.get(Skssrq.SKSSJSRQ);
        java.util.Date time = SfDateUtil.getDate(lastskssjsrq.toString());
        ServiceProxy sfproxy = new ServiceProxy();
        //ȡ�ø������շ�ʽ��Ϣ
        Grzsfs grzsfs = (Grzsfs) sfproxy.getGrzsfsInfo(form.getJsjdm(), time);
        
        if (grzsfs == null)
        {
            throw new ApplicationException("������Աע�⣺�ü��������û�в������շ�ʽ��");
        }
        else
        {
            form.setZsfs(grzsfs.getZsfsdm());

        }
        if (! (grzsfs.getZsfsdm().equals(CodeConstant.ZSFSDM_CZZS_GR)))
        {
            form.setZsfs("no");
            throw new ApplicationException("������Աע�⣺�ü��������û�в������շ�ʽ��");
        }

        //ȡ�ø����걨��Ŀ��Ϣ
        SfHashList dmList = null;
        try
        {
            dmList = (SfHashList) TableManager.getTableMap("CZZSNBTZZ").
                     get(TableType.TABLE_FDDM);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        if (dmList == null || dmList.size() <= 0)
        {
            throw new ApplicationException("ϵͳ�޷�ȡ�ø����걨��Ŀ��Ϣ��");
        }
        for (int i = 0; i < dmList.size(); i++)
        {
            form.getGrsbxm().put(dmList.get(i, "fddm_sequ"),
                                 dmList.get(i, "fddm_title"));
        }

        //ȡͶ������Ϣ
        List tzfList = null;
        try
        {
            tzfList = sfproxy.getTzfInfo(form.getJsjdm(), time);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        if (tzfList == null || tzfList.size() <= 0) {
            throw new ApplicationException("����˰��û��Ͷ���ˣ�");
        }
        for (int i = 0; i < tzfList.size(); i++) {
            Tzf tzf = (Tzf) tzfList.get(i);
            //������Ϣ
            Map grInfo = new HashMap();
            grInfo.put("zjlxdm", tzf.getZjlxdm());
            grInfo.put("zjhm", tzf.getZjhm());
            
            
            grInfo.put("tzzxm", tzf.getTzfmc());   //�޸�ֵ
            
           //����:��SBDB.SB_JL_CZZSNBTZZSJ���е�tzzxm�ֶγ��ȴ���15���ַ�ʱ,�򱣴����,
           //������Ϊ��oracle���е��ֶγ�������Ϊ15,�����Ҫ���ֶγ�������Ϊ100
           //grInfo.put("tzzxm", "������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������������");
           //grInfo.put("tzzxm", "��������������������������������������������������������");
            
            
            
            grInfo.put("fpbl", tzf.getFpbl());
            grInfo.put("cwfzr", "");
            
            
            //�������� ����˰�ѽӿ�ȷ��
            boolean jmsp = sfproxy.getZRRJm(tzf.getZjlxdm(), tzf.getZjhm(),
                                            "CHN", "05", time);
            if (jmsp) {
                grInfo.put("jmsp", CodeConstant.FSDM_SMSB);
            }
            else {
                grInfo.put("jmsp", "0");
            }
            //�����걨����
            HashMap grsbsj = new HashMap();
            grInfo.put("grsbsj", grsbsj);

            form.getGrList().put(tzf.getZjlxdm() + tzf.getZjhm(), grInfo);
        }

        Connection conn = null;
        try
        {

            conn = SfDBResource.getConnection();
            SfDBAccess da = new SfDBAccess(conn);

            //��ѯ��������
            Vector vZb = new Vector();
            vZb.add(" qxdm='" + form.getQxdm() + "' ");
            vZb.add(" nd='" + form.getNd() + "'");
            vZb.add(" jsjdm='" + form.getJsjdm() + "' ");
            //���������Ϣ
            List zbData = da.query(Czzsnbzb.class, vZb);
            if (zbData.size() > 0)
            { //û�д���˰�˵��걨����

                String zbNames[] =
                                   {
                                   "jsjdm", "sbrq", "nsrmc", "swjgzzjgdm",
                                   "skssksrq",
                                   "skssjsrq", "fsdm", "nd"};
                Czzsnbzb zb = (Czzsnbzb) zbData.get(0);
                BeanUtil.copyBeanToBean(zbNames, zb, form);
                form.setCjrq(TinyTools.Date2String(zb.getCjrq(),
                    CodeConstant.DATETYPE));
                form.setLrrq(TinyTools.Date2String(zb.getLrrq(),
                    CodeConstant.DATETYPE));
                //�����ҵ�������
                String bufSql = "select hc as qyhc, bqljs as qybqljs, bl " +
                                " from SBDB.SB_JL_CZZSNBFPBL " +
                                " where qxdm='" + form.getQxdm() + "'" +
                                " and nd='" + form.getNd() + "'" +
                                " and jsjdm='" + form.getJsjdm() + "'";
                SfDBUtils sfDB = new SfDBUtils(conn);
                SfHashList blList = sfDB.getData(bufSql);
                for (int i = 0; i < blList.size(); i++)
                {
                    HashMap qyfpbl = new HashMap();
                    qyfpbl.put("qyhc", blList.get(i, "qyhc", "0"));
                    qyfpbl.put("qybqljs", blList.get(i, "qybqljs", "0"));
                    qyfpbl.put("bl", blList.get(i, "bl", "0"));
                    form.getQyfpbl().add(qyfpbl);
                }

                //�����ҵ�걨����
                bufSql = "select hc as qyhc, bqljs as qybqljs " +
                         " from SBDB.SB_JL_CZZSNBQY " +
                         " where qxdm='" + form.getQxdm() + "'" +
                         " and nd='" + form.getNd() + "'" +
                         " and jsjdm='" + form.getJsjdm() + "'";
                SfHashList qyList = sfDB.getData(bufSql);
                form.setQyList(qyList.getRecords());

                //��ø�����Ϣ
                bufSql =
                    " select a.zjlxdm, a.zjhm, a.zjhm, a.tzzxm, a.cwfzr, b.hc, b.bqljs " +
                    " from SBDB.SB_JL_CZZSNBTZZSJ a,SBDB.SB_JL_CZZSNBTZZMXSJ b " +
                    " where a.nd=b.nd" +
                    " and  a.zjhm=b.zjhm" +
                    " and a.zjlxdm=b.zjlxdm" +
                    " and a.jsjdm=b.jsjdm" +
                    " and a.nd='" + form.getNd() + "'" +
                    " and a.jsjdm='" + form.getJsjdm() + "'";

                List grDataList = sfDB.getDataList(bufSql.toString());
                for (int i = 0; i < grDataList.size(); i++)
                {
                    Map dataMap = (Map) grDataList.get(i);
                    String zjlxdm = (String) dataMap.get("zjlxdm");
                    String zjhm = (String) dataMap.get("zjhm");
                    if (form.getGrList().containsKey(zjlxdm + zjhm))
                    {
                        Map grsbsj = (Map) ( ( (Map) form.getGrList().get(
                            zjlxdm + zjhm)).
                                            get("grsbsj"));
                        grsbsj.put(dataMap.get("hc"), dataMap.get("bqljs"));

                    }
                }
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
     * doSave   ���ڴ洢ҳ���ύ���꾡������Ϣ
     * @param   vo ҵ�����
     * @return  ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]
     * @throws BaseException ��������������׳��쳣��Ϣ
     */
    public Object doSave (VOPackage vo)
        throws BaseException
    {
        //�õ�Action���ݹ���ActionForm����
        CzzsnbForm form = (CzzsnbForm) vo.getData();
        form.setFsdm(CodeConstant.FSDM_SMSB);
        form.setNd(getSbnd(form.getSbrq()));
        form.setLrrq(TinyTools.Date2String(new Date(), CodeConstant.DATETYPE));
        //���û�д���������ȡϵͳʱ��
        if (form.getCjrq() == null || "".equals(form.getCjrq()))
        {
            form.setCjrq(TinyTools.Date2String(new Date(),
                                               CodeConstant.DATETYPE));
        }

        Connection conn = null;

        try
        {
            conn = SfDBResource.getConnection();
            SfDBAccess da = new SfDBAccess(conn);

            //ɾ����ʷ����
            try
            {
                da.delete("qxdm='" + form.getQxdm() + "' and nd='" + form.getNd()
                          + "'" +
                          " and jsjdm='" + form.getJsjdm() + "'",
                          new Czzsnbfpbl());
                
                da.delete("qxdm='" + form.getQxdm() + "' and nd='" + form.getNd()
                          + "'" +
                          " and jsjdm='" + form.getJsjdm() + "'", new Czzsnbqy());
                
                da.delete("qxdm='" + form.getQxdm() + "' and nd='" + form.getNd()
                          + "'" +
                          " and jsjdm='" + form.getJsjdm() + "'",
                          new Czzsnbtzzmxsj());
                
                da.delete("qxdm='" + form.getQxdm() + "' and nd='" + form.getNd()
                          + "'" +
                          " and jsjdm='" + form.getJsjdm() + "'",
                          new Czzsnbtzzsj());
                
                da.delete("qxdm='" + form.getQxdm() + "' and nd='" + form.getNd()
                          + "'" +
                          " and jsjdm='" + form.getJsjdm() + "'", new Czzsnbzb());
                
            }
            catch (BaseException ex2)
            {
                ex2.printStackTrace();
                throw new ApplicationException("ɾ������ʧ�ܣ�");
            }

            Timestamp ts_cjrq = new Timestamp(TinyTools.stringToDate(form.
                getCjrq(), CodeConstant.DATETYPE).getTime());
            Timestamp ts_lrrq = new Timestamp(TinyTools.stringToDate(form.
                getLrrq(), CodeConstant.DATETYPE).getTime());

            //׼���걨��������
            String zbNames[] =
                               {
                               "jsjdm", "nd", "skssksrq", "skssjsrq",
                               "qxdm", "swjgzzjgdm", "lrr", "sbrq", "fsdm"};

            Czzsnbzb zbSave = new Czzsnbzb();
            BeanUtil.copyBeanToBean(zbNames, form, zbSave);
            zbSave.setCjrq(ts_cjrq);
            zbSave.setLrrq(ts_lrrq);

            List qyList = form.getQyList();//��ҵ�걨�����б�
            List qySave = new ArrayList();
            List qyblSave = new ArrayList();
            String bl[] = form.getBl();

            int blIndex = 0;
            for (int i = 0; i < qyList.size(); i++)
            {
                Map map = (Map) qyList.get(i);
                
                map.put("jsjdm", form.getJsjdm());
                map.put("nd", form.getNd());
                map.put("swjgzzjgdm", form.getSwjgzzjgdm());
                map.put("qxdm", form.getQxdm());
                map.put("hc", map.get("qyhc"));
                map.put("bqljs", map.get("qybqljs"));
                
                String hc = (String) map.get("qyhc");
                if (hc.equals("51") || hc.equals("52") ||
                    hc.equals("53") || hc.equals("54")) {
                    if (bl[blIndex] != null && !"".equals(bl[blIndex]))
                    {
                        map.put("bl", bl[blIndex]);
                        
                        Czzsnbfpbl qybl = new Czzsnbfpbl();
                        BeanUtil.populate(qybl, map);
                        qybl.setCjrq(ts_cjrq);
                        qybl.setLrrq(ts_lrrq);
                        
                        qyblSave.add(qybl);
                    }
                    blIndex++;
                }
                else
                {
                    Czzsnbqy qy = new Czzsnbqy();
                    BeanUtil.populate(qy, map);
                    qy.setCjrq(ts_cjrq);
                    qy.setLrrq(ts_lrrq);
                    
                    qySave.add(qy);//
                }
            }

            List grList = form.getGrDataList();//�����걨�����б�
            List tzzSave = new ArrayList();
            List tzzMxSave = new ArrayList();

            Iterator items = grList.iterator();
            while (items.hasNext())
            {
                Map item = (Map) items.next();

                String grId = (String) item.get("zjlxdm")
                              + (String) item.get("zjhm");
                
                if (!form.getGrList().containsKey(grId))
                {
                    form.getGrList().put(grId, "");

                    HashMap zbInfo = new HashMap();
                    
                    zbInfo.put("jsjdm", form.getJsjdm());
                    zbInfo.put("nd", form.getNd());
                    zbInfo.put("swjgzzjgdm", form.getSwjgzzjgdm());
                    zbInfo.put("zjlxdm", item.get("zjlxdm"));
                    zbInfo.put("zjhm", item.get("zjhm"));
                    zbInfo.put("tzzxm", item.get("tzzxm"));
                    zbInfo.put("cwfzr", item.get("cwfzr"));
                    zbInfo.put("qxdm", form.getQxdm());
                    
                  //  System.out.println(item.get("tzzxm"));

                    Czzsnbtzzsj zbsj = new Czzsnbtzzsj();
                    BeanUtil.populate(zbsj, zbInfo);
                    zbsj.setCjrq(ts_cjrq);
                    zbsj.setLrrq(ts_lrrq);
                    
                    tzzSave.add(zbsj);
                }

                item.put("jsjdm", form.getJsjdm());
                item.put("nd", form.getNd());
                item.put("swjgzzjgdm", form.getSwjgzzjgdm());
                item.put("hc", item.get("grhc"));
                item.put("bqljs", item.get("grbqljs"));
                item.put("qxdm", form.getQxdm());

                Czzsnbtzzmxsj mxsj = new Czzsnbtzzmxsj();
                BeanUtil.populate(mxsj, item);
                mxsj.setCjrq(ts_cjrq);
                mxsj.setLrrq(ts_lrrq);
                
                tzzMxSave.add(mxsj);
            }
            //��������
            try
            {
                da.insert(zbSave);
                da.insert(qyblSave);
                da.insert(qySave);
                da.insert(tzzSave);
                da.insert(tzzMxSave);
            }
            catch (BaseException ex3)
            {
                ex3.printStackTrace();
                throw new ApplicationException("���ݲ���ʧ�ܣ�");
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
     * doDelete   ����ɾ��ҳ���ύ���꾡������Ϣ
     * @param   vo ҵ�����
     * @return  ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]
     * @throws BaseException ��������������׳��쳣��Ϣ
     */
    public Object doDelete (VOPackage vo)
        throws BaseException
    {
        //�õ�Action���ݹ���ActionForm����
        CzzsnbForm form = (CzzsnbForm) vo.getData();
        form.setNd(getSbnd(form.getSbrq()));

        Connection conn = null;
        try
        {
            conn = SfDBResource.getConnection();
            SfDBAccess da = new SfDBAccess(conn);

            try
            {//ɾ��ҳ���ύ���꾡������Ϣ
                da.delete("qxdm='" + form.getQxdm() + "' and nd='" + form.getNd()
                          + "'" +
                          " and jsjdm='" + form.getJsjdm() + "'",
                          new Czzsnbfpbl());
                da.delete("qxdm='" + form.getQxdm() + "' and nd='" + form.getNd()
                          + "'" +
                          " and jsjdm='" + form.getJsjdm() + "'", new Czzsnbqy());
                da.delete("qxdm='" + form.getQxdm() + "' and nd='" + form.getNd()
                          + "'" +
                          " and jsjdm='" + form.getJsjdm() + "'",
                          new Czzsnbtzzmxsj());
                da.delete("qxdm='" + form.getQxdm() + "' and nd='" + form.getNd()
                          + "'" +
                          " and jsjdm='" + form.getJsjdm() + "'",
                          new Czzsnbtzzsj());
                da.delete("qxdm='" + form.getQxdm() + "' and nd='" + form.getNd()
                          + "'" +
                          " and jsjdm='" + form.getJsjdm() + "'", new Czzsnbzb());
            }
            catch (BaseException ex2)
            {
                ex2.printStackTrace();
                throw new ApplicationException("ɾ������ʧ�ܣ�");
            }
            return form;
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
    }

    /**
     * ��ʼ��
     * @param sbForm CzzsnbForm
     * @throws BaseException ��������������׳��쳣��Ϣ
     */
    private void initForm (CzzsnbForm sbForm)
        throws BaseException
    {

        //����ʱ��
        sbForm.setCjrq(TinyTools.Date2String(new Date(), CodeConstant.DATETYPE));
        sbForm.setLrrq(TinyTools.Date2String(new Date(), CodeConstant.DATETYPE));
        sbForm.setFsdm(CodeConstant.FSDM_SMSB);
        sbForm.setSbrq(SfDateUtil.getDate());
        // �걨�ڼ�
        Map qj = Skssrq.yearSkssrq(SfDateUtil.getDate(sbForm.getSbrq()));
        try
        {
            String ksrq = DateTimeUtil.timestampToString(
                (Timestamp) qj.get(Skssrq.SKSSKSRQ),
                DateTimeUtil.JAVA_DATEFORMAT);

            String jsrq = DateTimeUtil.timestampToString(
                (Timestamp) qj.get(Skssrq.SKSSJSRQ),
                DateTimeUtil.JAVA_DATEFORMAT);

            sbForm.setSkssksrq(ksrq);
            sbForm.setSkssjsrq(jsrq);
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    /**
     * �õ��걨���
     * @param sbrq �걨����
     * @return String
     */
    private String getSbnd (String sbrq)
    {
        Map qj = Skssrq.yearSkssrq(SfDateUtil.getDate(sbrq));
        return (String) qj.get(Skssrq.SKSSRQ_ND);
    }

}
