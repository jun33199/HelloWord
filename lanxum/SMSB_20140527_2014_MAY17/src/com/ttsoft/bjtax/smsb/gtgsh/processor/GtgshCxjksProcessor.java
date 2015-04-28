/*
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */
package com.ttsoft.bjtax.smsb.gtgsh.processor;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import com.ttsoft.bjtax.sfgl.common.db.util.BeanUtil;
import com.ttsoft.bjtax.sfgl.common.code.CodeUtils;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBUtils;
import com.ttsoft.bjtax.sfgl.common.util.Debug;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.gtgsh.web.GtgshCxjksForm;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Description: 个体工商户撤销缴款书</p>
 * @author 开发六组－－陆峰
 * @version 1.1
 */
public class GtgshCxjksProcessor
    implements Processor
{
    public GtgshCxjksProcessor ()
    {
    }

    /**
     * 通用处理调度模块
     * @param vo  数据传递值对象
     * @return  数据结果对象[ActionForm]
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
                    "ActionType有错误，processor中找不到相应的方法.");
        }
        return result;
    }

    /**
     * 页面初始化，调用查询方法
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return 当前页面对应的Form对象
     * @throws BaseException
     */
    private Object doShow (VOPackage vo)
        throws BaseException
    {
        GtgshCxjksForm pf = new GtgshCxjksForm();
        try
        {
            pf = (GtgshCxjksForm) doQuery(vo);
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
     * 查询
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return 当前页面对应的Form对象
     * @throws BaseException
     */
        private Object doQuery (VOPackage vo)
        throws BaseException
    {
        List dataList = new ArrayList();
        String names[] =
                         {
                         "jkpzh", "jsjdm", "szdm", "nsrmc", "swjgzzjgmc",
                         "sbrq",
                         "sjje"};

        Connection conn = null;
        SfDBUtils sfDB = null;
        UserData ud = new UserData();
        GtgshCxjksForm pf = new GtgshCxjksForm();
        pf = (GtgshCxjksForm) vo.getData();

        try
        {
            conn = SfDBResource.getConnection();
            SfDBAccess da = new SfDBAccess(conn);
            //得到UserData
            ud = (UserData) vo.getUserData();
            //设置查询条件
            Vector tempVector = new Vector();

            tempVector.addElement("lrr='" + pf.getLrrdm() + "'");
            tempVector.addElement("sjly='" + CodeConstant.SMSB_SJLY_GTGSHLR
                                  + "'");
            tempVector.addElement("zwbs like '" + CodeConstant.SMSB_ZWBS + "%"
                                  + CodeConstant.SMSB_ZWBS + "'");
            tempVector.addElement("qxdm='" + InterfaceDj.getQxdm(ud) + "'");
            tempVector.addElement("jsjdm='" + pf.getHeadJsjdm() + "'");
            tempVector.addElement("jkpzh like '" + pf.getHeadJsjdm() + "%'");
            tempVector.addElement("1=1 order by sbrq desc,jkpzh desc ");

            //查询
            List tempList = da.query(Sbjkzb.class, tempVector);
            if (tempList.size() <= 0)
            {
                throw new ApplicationException("没有符合条件的记录！");
            }
            for (int i = 0; i < tempList.size(); i++)
            {
                //格式化每条纪录
                Sbjkzb sbjkzb = (Sbjkzb) tempList.get(i);
                sbjkzb.setSzdm(CodeUtils.getCodeBeanLabel("DM_SZSM",
                    sbjkzb.getSzdm())); //税种名称
                //将值对象的值赋给Map
                HashMap map = new HashMap();

                try
                {
                    BeanUtil.copyBeanToMap(names, sbjkzb, map);
                }
                catch (Exception ex1)
                {
                    ex1.printStackTrace();
                    throw new ApplicationException("数据转换出错！");
                }
                dataList.add(map);
            }
            //把值放回form对象
            pf.setDataList(dataList);

        }
        catch (Exception ex)
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
     * 保存
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return 当前页面对应的Form对象
     * @throws BaseException
     */
    private Object doSave (VOPackage vo)
        throws BaseException
    {
        return null;
    }

    /**
     * 删除（撤销）
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return 当前页面对应的Form对象
     * @throws BaseException
     */
    private Object doDelete (VOPackage vo)
        throws BaseException
    {
        List dataList = new ArrayList();

        Connection conn = null;
        SfDBUtils sfDB = null;
        UserData ud = new UserData();
        //from对象
        GtgshCxjksForm pf = new GtgshCxjksForm();
        pf = (GtgshCxjksForm) vo.getData();
        dataList = pf.getDataList();

        //ormapping对象
        Sbjkzb orObjz = new Sbjkzb();
        try
        {
            conn = SfDBResource.getConnection();
            SfDBAccess da = new SfDBAccess(conn);
            //得到UserData
            ud = (UserData) vo.getUserData();

            String strSql = "delete from sbdb.sb_jl_sbjkmx " +
                            " where qxdm='" + InterfaceDj.getQxdm(ud) + "'" +
                            " and jsjdm='" + pf.getHeadJsjdm() + "'" +
                            " and jkpzh='" + pf.getHeadJkpzh() + "'";
            //1、删除明细表数据
            try
            {
                da.updateSQL(strSql);
            }
            catch (BaseException ex1)
            {
                ex1.printStackTrace();
                throw new ApplicationException("删除明细表数据出错！");
            }

            //2、查出主表数据
            orObjz.setQxdm(InterfaceDj.getQxdm(ud)); //区县代码
            orObjz.setJkpzh(pf.getHeadJkpzh());
            orObjz.setJsjdm(pf.getHeadJsjdm());
            try
            {
                da.delete(orObjz);
            }
            catch (BaseException ex1)
            {
                ex1.printStackTrace();
                throw new ApplicationException("删除主表数据出错！");
            }
            pf.setHeadJkpzh("");
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
     * 更新
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return 当前页面对应的Form对象
     * @throws BaseException
     */
    private Object doUpdate (VOPackage vo)
        throws BaseException
    {
        return null;
    }

}
//:-)
