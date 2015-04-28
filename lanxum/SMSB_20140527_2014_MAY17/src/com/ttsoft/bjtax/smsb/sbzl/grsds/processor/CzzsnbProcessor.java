/*
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
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
 * <p>Title:  北京地税核心征管系统－－上门申报</p>
 * <p>Description: 查帐征收个人独资企业和合伙企业投资者个人所得税年度申报表理</p>
 * @author 开发六组－－诸光林
 * @version 1.1
 */
public class CzzsnbProcessor
    implements Processor
{

    /**
     * 实现Processor接口
     * @param vo 业务参数
     * @return Object VOPackage型数据
     * @throws BaseException 业务异常
     * 		1 当传过来的操作类型不对时抛出
     * 		2 当调用的业务方法抛出业务异常时向上传递抛出
     * 	其他异常抛出由EJB的process方法处理。
     */
    public Object process (VOPackage vo)
        throws BaseException
    {
        //回传对象
        Object result = null;
        //判断VO是否为空
        if (vo == null)
        {
            throw new NullPointerException();
        }
        //根据Action的值调用不同的process方法
        switch (vo.getAction())
        {
            case CodeConstant.SMSB_SHOWACTION: //前台默认显示
                result = doShow(vo);
                break;
            case CodeConstant.SMSB_QUERYACTION: //查询
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
     * doShow初始化对象页面信息要素
     * @param vo 业务参数
     * @return   数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
     * @throws BaseException 当其他情况都会抛出异常信息
     */
    public Object doShow (VOPackage vo)
        throws BaseException
    {
        //得到Action传递过来ActionForm对象
        CzzsnbForm form = (CzzsnbForm) vo.getData();
        // 初始化ActionForm
        initForm(form);
        return form;
    }

    /**
     * doQuery    用于返回页面索要查询的详尽信息
     * @param     vo 业务参数
     * @return    数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
     * @throws BaseException    当其他情况都会抛出异常信息
     */
    public Object doQuery (VOPackage vo)
        throws BaseException
    {
        //得到Action传递过来ActionForm对象
        CzzsnbForm form = (CzzsnbForm) vo.getData();
        form.setNsrmc("");
        form.setJydzlxdm("");
        UserData ud = (UserData) vo.getUserData();
        //取企业的登记信息
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
        //取得个人征收方式信息
        Grzsfs grzsfs = (Grzsfs) sfproxy.getGrzsfsInfo(form.getJsjdm(), time);
        
        if (grzsfs == null)
        {
            throw new ApplicationException("操作人员注意：该计算机代码没有查帐征收方式！");
        }
        else
        {
            form.setZsfs(grzsfs.getZsfsdm());

        }
        if (! (grzsfs.getZsfsdm().equals(CodeConstant.ZSFSDM_CZZS_GR)))
        {
            form.setZsfs("no");
            throw new ApplicationException("操作人员注意：该计算机代码没有查帐征收方式！");
        }

        //取得个人申报项目信息
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
            throw new ApplicationException("系统无法取得个人申报项目信息！");
        }
        for (int i = 0; i < dmList.size(); i++)
        {
            form.getGrsbxm().put(dmList.get(i, "fddm_sequ"),
                                 dmList.get(i, "fddm_title"));
        }

        //取投资人信息
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
            throw new ApplicationException("此纳税人没有投资人！");
        }
        for (int i = 0; i < tzfList.size(); i++) {
            Tzf tzf = (Tzf) tzfList.get(i);
            //个人信息
            Map grInfo = new HashMap();
            grInfo.put("zjlxdm", tzf.getZjlxdm());
            grInfo.put("zjhm", tzf.getZjhm());
            
            
            grInfo.put("tzzxm", tzf.getTzfmc());   //修改值
            
           //测试:当SBDB.SB_JL_CZZSNBTZZSJ表中的tzzxm字段长度大于15个字符时,则保存出错,
           //这是因为在oracle表中的字段长度设置为15,因此需要将字段长度增长为100
           //grInfo.put("tzzxm", "啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊");
           //grInfo.put("tzzxm", "啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊");
            
            
            
            grInfo.put("fpbl", tzf.getFpbl());
            grInfo.put("cwfzr", "");
            
            
            //减免审批 根据税费接口确定
            boolean jmsp = sfproxy.getZRRJm(tzf.getZjlxdm(), tzf.getZjhm(),
                                            "CHN", "05", time);
            if (jmsp) {
                grInfo.put("jmsp", CodeConstant.FSDM_SMSB);
            }
            else {
                grInfo.put("jmsp", "0");
            }
            //个人申报数据
            HashMap grsbsj = new HashMap();
            grInfo.put("grsbsj", grsbsj);

            form.getGrList().put(tzf.getZjlxdm() + tzf.getZjhm(), grInfo);
        }

        Connection conn = null;
        try
        {

            conn = SfDBResource.getConnection();
            SfDBAccess da = new SfDBAccess(conn);

            //查询主表数据
            Vector vZb = new Vector();
            vZb.add(" qxdm='" + form.getQxdm() + "' ");
            vZb.add(" nd='" + form.getNd() + "'");
            vZb.add(" jsjdm='" + form.getJsjdm() + "' ");
            //获得主表信息
            List zbData = da.query(Czzsnbzb.class, vZb);
            if (zbData.size() > 0)
            { //没有此纳税人的申报资料

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
                //获得企业分配比例
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

                //获得企业申报数据
                bufSql = "select hc as qyhc, bqljs as qybqljs " +
                         " from SBDB.SB_JL_CZZSNBQY " +
                         " where qxdm='" + form.getQxdm() + "'" +
                         " and nd='" + form.getNd() + "'" +
                         " and jsjdm='" + form.getJsjdm() + "'";
                SfHashList qyList = sfDB.getData(bufSql);
                form.setQyList(qyList.getRecords());

                //获得个人信息
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
     * doSave   用于存储页面提交的详尽处理信息
     * @param   vo 业务参数
     * @return  数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]
     * @throws BaseException 当其他情况都会抛出异常信息
     */
    public Object doSave (VOPackage vo)
        throws BaseException
    {
        //得到Action传递过来ActionForm对象
        CzzsnbForm form = (CzzsnbForm) vo.getData();
        form.setFsdm(CodeConstant.FSDM_SMSB);
        form.setNd(getSbnd(form.getSbrq()));
        form.setLrrq(TinyTools.Date2String(new Date(), CodeConstant.DATETYPE));
        //如果没有创建日期则取系统时间
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

            //删除历史数据
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
                throw new ApplicationException("删除数据失败！");
            }

            Timestamp ts_cjrq = new Timestamp(TinyTools.stringToDate(form.
                getCjrq(), CodeConstant.DATETYPE).getTime());
            Timestamp ts_lrrq = new Timestamp(TinyTools.stringToDate(form.
                getLrrq(), CodeConstant.DATETYPE).getTime());

            //准备申报主表数据
            String zbNames[] =
                               {
                               "jsjdm", "nd", "skssksrq", "skssjsrq",
                               "qxdm", "swjgzzjgdm", "lrr", "sbrq", "fsdm"};

            Czzsnbzb zbSave = new Czzsnbzb();
            BeanUtil.copyBeanToBean(zbNames, form, zbSave);
            zbSave.setCjrq(ts_cjrq);
            zbSave.setLrrq(ts_lrrq);

            List qyList = form.getQyList();//企业申报数据列表
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

            List grList = form.getGrDataList();//个人申报数据列表
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
            //插入数据
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
                throw new ApplicationException("数据插入失败！");
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
     * doDelete   用于删除页面提交的详尽处理信息
     * @param   vo 业务参数
     * @return  数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]
     * @throws BaseException 当其他情况都会抛出异常信息
     */
    public Object doDelete (VOPackage vo)
        throws BaseException
    {
        //得到Action传递过来ActionForm对象
        CzzsnbForm form = (CzzsnbForm) vo.getData();
        form.setNd(getSbnd(form.getSbrq()));

        Connection conn = null;
        try
        {
            conn = SfDBResource.getConnection();
            SfDBAccess da = new SfDBAccess(conn);

            try
            {//删除页面提交的详尽处理信息
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
                throw new ApplicationException("删除数据失败！");
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
     * 初始化
     * @param sbForm CzzsnbForm
     * @throws BaseException 当其他情况都会抛出异常信息
     */
    private void initForm (CzzsnbForm sbForm)
        throws BaseException
    {

        //创建时间
        sbForm.setCjrq(TinyTools.Date2String(new Date(), CodeConstant.DATETYPE));
        sbForm.setLrrq(TinyTools.Date2String(new Date(), CodeConstant.DATETYPE));
        sbForm.setFsdm(CodeConstant.FSDM_SMSB);
        sbForm.setSbrq(SfDateUtil.getDate());
        // 申报期间
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
     * 得到申报年度
     * @param sbrq 申报日期
     * @return String
     */
    private String getSbnd (String sbrq)
    {
        Map qj = Skssrq.yearSkssrq(SfDateUtil.getDate(sbrq));
        return (String) qj.get(Skssrq.SKSSRQ_ND);
    }

}
