/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsnb.processor;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;
import java.util.List;
import java.util.Vector;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.Iterator;
//import com.ekernel.db.or.*;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.framework.util.DateTimeUtil;

import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.db.util.BeanUtil;

import com.ttsoft.bjtax.sfgl.proxy.ServiceProxy;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.common.model.Zsfs;

import com.ttsoft.bjtax.shenbao.model.domain.Sydwshttsrzb;
import com.ttsoft.bjtax.shenbao.model.domain.Sydwshttsrmx;
import com.ttsoft.bjtax.shenbao.model.domain.Sydwshttwh;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb.web.SydwshttsrsbForm;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.Skssrq;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.bjtax.smsb.util.EArray;
import com.ttsoft.bjtax.sfgl.common.util.Debug;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.common.model.UserData;

/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description:事业单位</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class SydwshttsrsbProcessor
    implements Processor
{

    public SydwshttsrsbProcessor ()
    {}

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

        // SMSB_SHOWACTION = 0;
        // SMSB_SAVEACTION = 1;
        // SMSB_DELETEACTION = 2;
        // SMSB_QUERYACTION = 3;
        // SMSB_UPDATEACTION = 4;

        Object result = null;
        Debug.out("action===>" + vo.getAction());
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
                    "用户执行了系统不支持的方法或功能.");
        }

        return result;
    }

    /**
     * doShow初始化对象页面信息要素
     * @param vo 业务参数
     * @return   数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
     * @throws BaseException 当其他情况都会抛出异常信息
     */

    private Object doShow (VOPackage vo)
        throws BaseException
    {

        SydwshttsrsbForm sbForm = (SydwshttsrsbForm) vo.getData();

        // 初始化FORM
        initForm(sbForm);

        return sbForm;
    }

    /**
     * doQuery    用于返回页面索要查询的详尽信息
     * @param     vo 业务参数
     * @return    数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
     * @throws BaseException    当其他情况都会抛出异常信息
     *
     */

    private Object doQuery (VOPackage vo)
        throws BaseException
    {

        SydwshttsrsbForm sbForm = (SydwshttsrsbForm) vo.getData();
        sbForm.setNsrmc(""); // 纳税人名称
        sbForm.setZcdzlxdh(""); // 注册地址联系电话

        Connection conn = null;
        SfDBAccess dbAgent = null;

        try
        {
            conn = SfDBResource.getConnection();
            dbAgent = new SfDBAccess(conn);
            UserData ud = (UserData) vo.getUserData();
            SWDJJBSJ djsj = null;
            try
            {
                // 获得企业登记基本信息
                /* start added by huxiaofeng 2005.8.16*/
                //djsj = InterfaceDj.getJBSJ(sbForm.getJsjdm(), ud);
                djsj = InterfaceDj.getJBSJ_New(sbForm.getJsjdm(), ud);
                sbForm.setNsrzt(djsj.getNsrzt());
                /* end added by huxiaofeng 2005.8.16*/

            }
            catch (Exception ex1)
            {
                throw ExceptionUtil.getBaseException(ex1);
            }

            if (! (djsj.getDjzclxdm().equals(CodeConstant.DJZCLXDM_SYDW) ||
                   djsj.getDjzclxdm().equals(CodeConstant.DJZCLXDM_SHTT)))
            {
                sbForm.setJsjdm("");
                throw new ApplicationException("此申报表只适用于事业单位、社会团体");
            }
            sbForm.setQxdm(InterfaceDj.getQxdm(ud));
            sbForm.setNsrmc(djsj.getNsrmc()); // 纳税人名称
            sbForm.setZcdzlxdh(djsj.getZcdzlxdh()); // 注册地址联系电话
            sbForm.setDjzclxdm(djsj.getDjzclxdm()); // 登记注册类型代码
            sbForm.setSwjgzzjgdm(djsj.getSwjgzzjgdm()); //税务机关组织机构代码
            sbForm.setFsdm(CodeConstant.FSDM_SMSB); // 上门申报方式
            sbForm.setNd(getSbnd(sbForm.getSbrq()));
            sbForm.setCjrq(TinyTools.Date2String(new Date(),
                                                 CodeConstant.DATETYPE));
            sbForm.setLrrq(TinyTools.Date2String(new Date(),
                                                 CodeConstant.DATETYPE));
            sbForm.setFsdm(CodeConstant.FSDM_SMSB);

            //查询主表数据
            Vector vZb = new Vector();
            vZb.add(" jsjdm='" + sbForm.getJsjdm() + "' ");
            vZb.add(" qxdm='" + sbForm.getQxdm() + "' ");
            vZb.add(" nd='" + sbForm.getNd() + "'");
            //主表数据结果
            List zbData = dbAgent.query(Sydwshttsrzb.class, vZb);
            //如果主表有数据
            if (zbData.size() > 0)
            {
                // 取得已有申报数据的申报日期
                Sydwshttsrzb srzb = (Sydwshttsrzb) zbData.get(0);
                HashMap zbMap = new HashMap();
                String[] zb_columns =
                                      {"fsdm", "sbrq", "skssksrq", "skssjsrq"};
                BeanUtil.copyBeanToMap(zb_columns, srzb, zbMap);
                sbForm.setSfyyssr(srzb.getSfyyssr());
                sbForm.setFsdm( (String) zbMap.get("fsdm"));
                sbForm.setSbrq( (String) zbMap.get("sbrq"));
                sbForm.setSkssksrq( (String) zbMap.get("skssksrq"));
                sbForm.setSkssjsrq( (String) zbMap.get("skssjsrq"));
                sbForm.setLrrq(TinyTools.Date2String(srzb.getLrrq(),
                    CodeConstant.DATETYPE));
                sbForm.setCjrq(TinyTools.Date2String(srzb.getCjrq(),
                    CodeConstant.DATETYPE));

                // 查找是否已有申报数据
                Vector vMx = new Vector();
                vMx.add(" ND='" + sbForm.getNd() + "'");
                vMx.add(" qxdm='" + sbForm.getQxdm() + "' ");
                vMx.add(" JSJDM='" + sbForm.getJsjdm() + "'" +
                        " order by  to_number(HC) ASC ");

                // 事业单位明细表部分
                String mx_columns[] =
                                      {"hc", "bqljs"};
                List mxData = dbAgent.query(Sydwshttsrmx.class, vMx);

                List mxResult = new ArrayList();
                Iterator mxit = mxData.iterator();
                while (mxit.hasNext())
                {
                    Sydwshttsrmx item = (Sydwshttsrmx) mxit.next();
                    HashMap record = new HashMap();
                    BeanUtil.copyBeanToMap(mx_columns, item, record);
                    mxResult.add(record);
                }

                // 事业单位文号表部分
                String wh_columns[] =
                                      {"hc", "wh"};
                List whData = dbAgent.query(Sydwshttwh.class, vMx);
                Iterator whit = whData.iterator();
                while (whit.hasNext())
                {
                    Sydwshttwh whitem = (Sydwshttwh) whit.next();
                    HashMap record = new HashMap();
                    BeanUtil.copyBeanToMap(wh_columns, whitem, record);

                    Object wh = record.get("wh");
                    record.remove("wh");
                    record.put("bqljs", wh);

                    mxResult.add(record);
                }

                sbForm.setDataList(mxResult);
            }
        }
        catch (Exception ex)
        {
            //抛出异常
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
        finally
        {
            SfDBResource.freeConnection(conn);
        }

        return sbForm;
    }

    /**
     * doSave   用于存储页面提交的详尽处理信息
     * @param   vo 业务参数
     * @return  数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]
     * @throws BaseException 当其他情况都会抛出异常信息
     */

    private Object doSave (VOPackage vo)
        throws BaseException
    {

        SydwshttsrsbForm sbForm = (SydwshttsrsbForm) vo.getData();

        Connection conn = null;
        SfDBAccess dbAgent = null;
        List mxResult = new ArrayList();

        try
        {
            conn = SfDBResource.getConnection();
            dbAgent = new SfDBAccess(conn);

            //============ 数据准备部分==========================

            sbForm.setFsdm(CodeConstant.FSDM_SMSB); // 申报申报方式
            sbForm.setNd(getSbnd(sbForm.getSbrq()));
            sbForm.setLrrq(TinyTools.Date2String(new Date(),
                                                 CodeConstant.DATETYPE));
            if (sbForm.getCjrq() == null || "".equals(sbForm.getCjrq()))
            {
                sbForm.setCjrq(TinyTools.Date2String(new Date(),
                    CodeConstant.DATETYPE));
            }
            Timestamp ts_cjrq = new Timestamp(TinyTools.stringToDate(sbForm.
                getCjrq(), CodeConstant.DATETYPE).getTime());
            Timestamp ts_lrrq = new Timestamp(TinyTools.stringToDate(sbForm.
                getLrrq(), CodeConstant.DATETYPE).getTime());

            String strWhere = " jsjdm='" + sbForm.getJsjdm() + "'" +
                              " and qxdm='" + sbForm.getQxdm() + "'" +
                              " and nd='" + sbForm.getNd() + "'";
            try
            {
                //删除文号明细数据
                dbAgent.delete(strWhere, new Sydwshttwh()); // 删除旧文号数据
            }
            catch (BaseException ex1)
            {
                throw new ApplicationException("数据库删除操作失败！");
            }
            try
            {
                //删除文号明细数据
                dbAgent.delete(strWhere, new Sydwshttsrmx()); // 删除旧明细
            }
            catch (BaseException ex1)
            {
                throw new ApplicationException("数据库删除操作失败！");
            }
            try
            {
                //删除主表数据
                dbAgent.delete(strWhere, new Sydwshttsrzb()); // 删除旧主记录
            }
            catch (BaseException ex1)
            {
                throw new ApplicationException("数据库删除操作失败！");
            }

            //============= 保存主表信息部分 ========================
            String zb_columns[] =
                                  {
                                  "nd", "jsjdm", "sbrq", "lrr",
                                  "skssksrq", "skssjsrq", "swjgzzjgdm", "fsdm",
                                  "qxdm", "djzclxdm", "sfyyssr"};

            Sydwshttsrzb orZb = new Sydwshttsrzb();
            HashMap zbMap = new HashMap();
            BeanUtil.copyBeanToMap(zb_columns, sbForm, zbMap);
            Debug.out(zbMap);
            BeanUtil.populate(orZb, zbMap);
            orZb.setCjrq(ts_cjrq);
            orZb.setLrrq(ts_lrrq);
            dbAgent.insert(orZb); // 插入新的申报数据

            //============ 保存明细表信息部分 ==========================
            String mx_columns[] =
                                  {"qxdm", "nd", "jsjdm", "swjgzzjgdm"};

            mxResult = sbForm.getDataList();
            for (int i = 0; i < mxResult.size(); i++)
            {
                HashMap sbData = (HashMap) mxResult.get(i);
                Integer hcIndex = new Integer( (String) sbData.get("hc"));
                if (hcIndex.intValue() <= 17)
                {
                    // 保存申报明细数据
                    BeanUtil.copyBeanToMap(mx_columns, sbForm, sbData);

                    Sydwshttsrmx orMx = new Sydwshttsrmx();
                    BeanUtil.populate(orMx, sbData);
                    orMx.setCjrq(ts_cjrq);
                    orMx.setLrrq(ts_lrrq);
                    dbAgent.insert(orMx); // 插入新的申报明细信息
                }
                else
                {
                    // 保存文号数据
                    BeanUtil.copyBeanToMap(mx_columns, sbForm, sbData);
                    // 从行次17开始所有本期累计列存储文号数据
                    // 转换文号值的字段名称
                    HashMap whRecord = new HashMap(sbData);
                    Object whValue = whRecord.get("bqljs");
                    if (whValue != null)
                    {
                        String wh = (String) whValue;
                        if (!"".equals(wh.trim()))
                        {
                            whRecord.remove("bqljs");
                            whRecord.put("wh", whValue);

                            Sydwshttwh orWh = new Sydwshttwh();
                            BeanUtil.populate(orWh, whRecord);
                            orWh.setCjrq(ts_cjrq);
                            orWh.setLrrq(ts_lrrq);
                            dbAgent.insert(orWh); // 插入新的文号数据
                        }
                    }
                }

            }
        }
        catch (Exception ex)
        {
            //抛出异常
            ex.printStackTrace();
            throw new ApplicationException("数据库插入操作失败");
        }
        finally
        {
            SfDBResource.freeConnection(conn);
        }

        return sbForm;
    }

    /**
     * doDelete  用于存储页面提交的详尽处理信息
     * @param    vo 业务参数
     * @return   数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]
     * @throws BaseException 当其他情况都会抛出异常信息
     */

    private Object doDelete (VOPackage vo)
        throws BaseException
    {

        SydwshttsrsbForm sbForm = (SydwshttsrsbForm) vo.getData();

        Connection conn = null;
        SfDBAccess dbAgent = null;

        try
        {
            conn = SfDBResource.getConnection();
            dbAgent = new SfDBAccess(conn);

            sbForm.setNd(getSbnd(sbForm.getSbrq()));

            String strWhere = " jsjdm='" + sbForm.getJsjdm() + "'" +
                              " and qxdm='" + sbForm.getQxdm() + "'" +
                              " and nd='" + sbForm.getNd() + "'";
            try
            {
                //删除文号明细数据
                dbAgent.delete(strWhere, new Sydwshttwh()); // 删除旧文号数据
            }
            catch (BaseException ex1)
            {
                throw new ApplicationException("数据库删除操作失败！");
            }
            try
            {
                //删除文号明细数据
                dbAgent.delete(strWhere, new Sydwshttsrmx()); // 删除旧明细
            }
            catch (BaseException ex1)
            {
                throw new ApplicationException("数据库删除操作失败！");
            }
            try
            {
                //删除主表数据
                dbAgent.delete(strWhere, new Sydwshttsrzb()); // 删除旧主记录
            }
            catch (BaseException ex1)
            {
                throw new ApplicationException("数据库删除操作失败！");
            }

            // 重新设置却省数据
            initForm(sbForm);
            // 清楚已加载数据
            sbForm.getDataList().clear();
        }
        catch (Exception ex)
        {
            //抛出异常
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
        finally
        {
            SfDBResource.freeConnection(conn);
        }
        return sbForm;
    }

    /**
     * doUpdate  用于存储页面提交的详尽处理信息
     * @param    vo 业务参数
     * @return   数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]
     * @throws BaseException 当其他情况都会抛出异常信息
     */

    private Object doUpdate (VOPackage vo)
        throws BaseException
    {

        SydwshttsrsbForm sbForm = (SydwshttsrsbForm) vo.getData();

        return sbForm;
    }

    /**
     * 初始化
     * @param sbForm 主表数据
     * @throws BaseException 当其他情况都会抛出异常信息
     */

    private void initForm (SydwshttsrsbForm sbForm)
        throws BaseException
    {

        sbForm.setDjzclxdm(""); // 登记注册类型代码
        sbForm.setSfyyssr(CodeConstant.YSSR_TRUE); // 应税收入

        sbForm.setSbrq(SfDateUtil.getDate());
        sbForm.setCjrq(TinyTools.Date2String(new Date(), CodeConstant.DATETYPE));
        sbForm.setLrrq(TinyTools.Date2String(new Date(), CodeConstant.DATETYPE));
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
     * 计算申报年度
     * @param   sbrq 申报日期
     * @return String 年度
     */

    private String getSbnd (String sbrq)
    {

        Map qj = Skssrq.yearSkssrq(SfDateUtil.getDate(sbrq));
        return (String) qj.get(Skssrq.SKSSRQ_ND);
    }

}
