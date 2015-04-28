/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsnb.processor;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;//import com.ekernel.db.or.*;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.common.db.util.BeanUtil;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.util.Debug;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.shenbao.model.domain.Qyjbcwzb;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb.web.QyjbcwzbForm;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.Skssrq;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
//import com.ekernel.db.or.*;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.DateTimeUtil;
import com.ttsoft.framework.util.VOPackage;


/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description: 企业基本财务指标</p>
 * @author Shi Yanfeng
 * @version 1.1
 */


public class QyjbcwzbProcessor
    implements Processor
{

    public QyjbcwzbProcessor ()
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
        Debug.out("QYJBCWZB action===>" + vo.getAction());
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

        QyjbcwzbForm zbForm = (QyjbcwzbForm) vo.getData();

        // 设置收入申报期间、申报日期
        initForm(zbForm);

        return zbForm;
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

        QyjbcwzbForm zbForm = (QyjbcwzbForm) vo.getData();
        zbForm.setNsrmc(""); // 纳税人名称
        zbForm.setZcdzlxdh(""); // 注册地址联系电话

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
                //djsj = InterfaceDj.getJBSJ(zbForm.getJsjdm(), ud);
                djsj = InterfaceDj.getJBSJ_New(zbForm.getJsjdm(), ud);
                zbForm.setNsrzt(djsj.getNsrzt());
                /* end added by huxiaofeng 2005.8.16*/

            }
            catch (Exception ex1)
            {
                throw ExceptionUtil.getBaseException(ex1);
            }
            zbForm.setFsdm(CodeConstant.FSDM_SMSB);
            zbForm.setQxdm(InterfaceDj.getQxdm(ud));
            zbForm.setNsrmc(djsj.getNsrmc()); // 纳税人名称
            zbForm.setZcdzlxdh(djsj.getZcdzlxdh()); // 注册地址联系电话
            zbForm.setDjzclxdm(djsj.getDjzclxdm()); // 登记注册类型代码
            zbForm.setSwjgzzjgdm(djsj.getSwjgzzjgdm());
            zbForm.setNd(getSbnd(zbForm.getSbrq()));

            zbForm.setCjrq(TinyTools.Date2String(new Date(),
                                                 CodeConstant.DATETYPE));
            zbForm.setLrrq(TinyTools.Date2String(new Date(),
                                                 CodeConstant.DATETYPE));

            // 查找是否已有申报数据
            Vector vMx = new Vector();
            vMx.add(" ND='" + zbForm.getNd() + "'" +
                    " and QXDM='" + zbForm.getQxdm() + "'" +
                    " and JSJDM='" + zbForm.getJsjdm() + "'" +
                    " order by  to_number(HC) ASC ");

            Debug.out("SELECT SQL= ND='" + zbForm.getNd() + "'" +
                      " and QXDM='" + zbForm.getQxdm() + "'" +
                      " and JSJDM='" + zbForm.getJsjdm() + "'" +
                      " order by  to_number(HC) ASC ");

            String zb_columns[] =
                                  {"fsdm", "sbrq", "skssksrq", "skssjsrq"};
            String mx_columns[] =
                                  {"hc", "ncs", "nms"};
            List mxData = dbAgent.query(Qyjbcwzb.class, vMx);
            if (mxData.size() > 0)
            {
                Qyjbcwzb orData = (Qyjbcwzb) mxData.get(0);
                HashMap zbMap = new HashMap();
                BeanUtil.copyBeanToMap(zb_columns, orData, zbMap);
                zbForm.setFsdm( (String) zbMap.get("fsdm"));
                zbForm.setSbrq( (String) zbMap.get("sbrq"));
                zbForm.setSkssksrq( (String) zbMap.get("skssksrq"));
                zbForm.setSkssjsrq( (String) zbMap.get("skssjsrq"));
                zbForm.setLrrq(TinyTools.Date2String(orData.getLrrq(),
                    CodeConstant.DATETYPE));
                zbForm.setCjrq(TinyTools.Date2String(orData.getCjrq(),
                    CodeConstant.DATETYPE));

                List mxResult = new ArrayList();
                Iterator mxit = mxData.iterator();
                while (mxit.hasNext())
                {
                    Qyjbcwzb item = (Qyjbcwzb) mxit.next();
                    HashMap record = new HashMap();
                    BeanUtil.copyBeanToMap(mx_columns, item, record);
                    mxResult.add(record);
                }
                zbForm.setDataList(mxResult);
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

        return zbForm;
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

        QyjbcwzbForm zbForm = (QyjbcwzbForm) vo.getData();

        Connection conn = null;
        SfDBAccess dbAgent = null;
        List mxResult = new ArrayList();

        try
        {
            conn = SfDBResource.getConnection();
            dbAgent = new SfDBAccess(conn);

            //============ 数据准备部分==========================
            zbForm.setFsdm(CodeConstant.FSDM_SMSB);
            //zbForm.setLrrq(SfDateUtil.getDate());
            //zbForm.setLrr("unknown"); //TODO:需要根据登陆用户确定录入人
            zbForm.setNd(getSbnd(zbForm.getSbrq()));
            zbForm.setLrrq(TinyTools.Date2String(new Date(),
                                                 CodeConstant.DATETYPE));
            if (zbForm.getCjrq() == null || "".equals(zbForm.getCjrq()))
            {
                zbForm.setCjrq(TinyTools.Date2String(new Date(),
                    CodeConstant.DATETYPE));
            }

            String strWhere = " jsjdm='" + zbForm.getJsjdm() + "'" +
                              " and qxdm='" + zbForm.getQxdm() + "'" +
                              " and nd='" + zbForm.getNd() + "'";
            try
            {
                //删除主表数据
                dbAgent.delete(strWhere, new Qyjbcwzb()); // 删除旧主记录
            }
            catch (BaseException ex1)
            {
                throw new ApplicationException("主表删除错误");
            }

            Timestamp ts_cjrq = new Timestamp(TinyTools.stringToDate(zbForm.
                getCjrq(), CodeConstant.DATETYPE).getTime());
            Timestamp ts_lrrq = new Timestamp(TinyTools.stringToDate(zbForm.
                getLrrq(), CodeConstant.DATETYPE).getTime());

            //============= 保存企业基本财务指标数据 ========================
            String zb_columns[] =
                                  {
                                  "qxdm", "nd", "jsjdm", "sbrq", "lrr",
                                  "skssksrq", "skssjsrq", "swjgzzjgdm", "fsdm"};

            mxResult = zbForm.getDataList();
            for (int i = 0; i < mxResult.size(); i++)
            {
                HashMap zbData = (HashMap) mxResult.get(i);

                // 保存明细数据
                BeanUtil.copyBeanToMap(zb_columns, zbForm, zbData);

                Qyjbcwzb orMx = new Qyjbcwzb();
                BeanUtil.populate(orMx, zbData);
                orMx.setCjrq(ts_cjrq);
                orMx.setLrrq(ts_lrrq);
                dbAgent.insert(orMx); // 插入新的申报明细信息

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

        return zbForm;
    }

    /**
     * doDelete  用于删除页面提交的详尽处理信息
     * @param    vo 业务参数
     * @return   数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]
     * @throws BaseException 当其他情况都会抛出异常信息
     */

    private Object doDelete (VOPackage vo)
        throws BaseException
    {

        QyjbcwzbForm zbForm = (QyjbcwzbForm) vo.getData();

        Connection conn = null;
        SfDBAccess dbAgent = null;

        try
        {
            conn = SfDBResource.getConnection();
            dbAgent = new SfDBAccess(conn);

            zbForm.setNd(getSbnd(zbForm.getSbrq()));
            String strWhere = " jsjdm='" + zbForm.getJsjdm() + "'" +
                              " and qxdm='" + zbForm.getQxdm() + "'" +
                              " and nd='" + zbForm.getNd() + "'";
            try
            {
                //删除主表数据
                dbAgent.delete(strWhere, new Qyjbcwzb()); // 删除旧主记录
            }
            catch (BaseException ex1)
            {
                throw new ApplicationException("主表删除错误");
            }

            // 重新设置初始化数据
            initForm(zbForm);
            // 清楚已加载数据
            zbForm.getDataList().clear();
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
        return zbForm;
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

        QyjbcwzbForm zbForm = (QyjbcwzbForm) vo.getData();

        return zbForm;
    }

    /**
     * 初始化
     * @param zbForm 主表数据
     * @throws BaseException 当其他情况都会抛出异常信息
     */
    private void initForm (QyjbcwzbForm zbForm)
        throws BaseException
    {

        zbForm.setSbrq(SfDateUtil.getDate());
        zbForm.setLrrq(TinyTools.Date2String(new Date(), CodeConstant.DATETYPE));
        zbForm.setCjrq(TinyTools.Date2String(new Date(), CodeConstant.DATETYPE));

        // 申报期间
        Map qj = Skssrq.yearSkssrq(SfDateUtil.getDate(zbForm.getSbrq()));
        try
        {
            String ksrq = DateTimeUtil.timestampToString(
                (Timestamp) qj.get(Skssrq.SKSSKSRQ),
                DateTimeUtil.JAVA_DATEFORMAT);

            String jsrq = DateTimeUtil.timestampToString(
                (Timestamp) qj.get(Skssrq.SKSSJSRQ),
                DateTimeUtil.JAVA_DATEFORMAT);

            zbForm.setSkssksrq(ksrq);
            zbForm.setSkssjsrq(jsrq);
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
