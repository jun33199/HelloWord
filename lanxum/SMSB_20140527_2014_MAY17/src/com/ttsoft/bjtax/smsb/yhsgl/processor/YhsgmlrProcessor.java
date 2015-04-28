/*
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */
package com.ttsoft.bjtax.smsb.yhsgl.processor;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.ttsoft.bjtax.sfgl.common.code.CodeUtils;
import com.ttsoft.bjtax.sfgl.common.db.util.BeanUtil;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBUtils;
import com.ttsoft.bjtax.sfgl.common.util.SfHashList;
import com.ttsoft.bjtax.sfgl.common.util.SfTimeUtil;
import com.ttsoft.bjtax.shenbao.model.domain.Yhsgmmx;
import com.ttsoft.bjtax.shenbao.model.domain.Yhsgmz;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.bjtax.smsb.yhsgl.web.YhsgmlrForm;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;
import java.sql.Timestamp;

/**
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Description: 录入印花税购买情况 后台处理</p>
 * @author 开发六组－－诸光林
 * @version 1.1
 */

public class YhsgmlrProcessor
    implements Processor
{
    /**
     * 默认构造函数
     */
    public YhsgmlrProcessor ()
    {
    }

    /**
     * 通用处理调度模块
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return object
     * @throws com.ttsoft.framework.exception.BaseException
     */
    public Object process (VOPackage vo)
        throws com.ttsoft.framework.exception.
        BaseException
    {
        Object result = null;

        if (vo == null)
        {
            throw new NullPointerException();
        }

        switch (vo.getAction())
        {
            case CodeConstant.SMSB_SHOWACTION:
                doShow(vo);
                break;
            case CodeConstant.SMSB_SAVEACTION:
                result = doSave(vo);
                break;
            case CodeConstant.SMSB_READERACTION:
                doReader(vo);
                break;
            default:
                throw new UnsupportedOperationException(
                    "Method process() not yet implemented.");
        }
        return result;
    }

    /**
     * doShow
     * @param vo 数据集对象（包括Form和UserData对象）
     * @throws BaseException
     */
    private void doShow (VOPackage vo)
        throws BaseException
    {
    }

    /**
     * 保存录入的印花税购买情况
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return object
     * @throws BaseException
     */
    private Object doSave (VOPackage vo)
        throws BaseException
    {
        Connection conn = null;
        YhsgmlrForm form = (YhsgmlrForm) vo.getData();
        //取得单位名称
        if (form.getGhrxm().equals(""))
        {
            form.setDwmc(form.getGhdwmc());
        }
        else
        {
            form.setDwmc(form.getGhrxm());
        }

        Yhsgmz orObj = new Yhsgmz();
        String columns[] =
            {
            "dsjsjdm", "cjsj", "lrrq", "dsdwmc", "sjly",
            "swjgzzjgdm", "fsdm", "lrr", "hjje"};

        try
        {
            conn = SfDBResource.getConnection();
            SfDBAccess da = new SfDBAccess(conn);
            UserData userData = vo.getUserData();
            String strSsdwdm = userData.getSsdwdm(); //userData所属的税务机关组织机构代码
            String strDsJsjdm = getDsjsjdmByUserData(strSsdwdm);//代售单位计算机代码
            if (strDsJsjdm == null || strDsJsjdm.equals(""))
            {
                throw new ApplicationException("未得到相应的代售计算机代码！");
            }
            else
            {
                form.setDsjsjdm(strDsJsjdm);
            }
            //主表数据处理
            if (form.getDwmc().equals(""))
            {
                throw new ApplicationException("购花单位(人)不能为空！");
            }
            if (Float.parseFloat(form.getHjje()) <= 0)
            {
                throw new ApplicationException("没有可保存的数据！");
            }

            BeanUtil.copyBeanToBean(columns, form, orObj);
            Vector condition = new Vector();
            String xspzh = getNextXspzh(orObj.getDsjsjdm());
            orObj.setXspzh(xspzh);
            Timestamp cjrq = TinyTools.getDBTimestamp(conn);
            orObj.setCjrq(cjrq);
            orObj.setLrrq(cjrq);
            orObj.setQxdm(InterfaceDj.getQxdm(userData));
            if (orObj.getCjrq() != null)
            {
                orObj.setNd("" + TinyTools.getYear(orObj.getCjrq()));
            }
            orObj.setZhdm(userData.getXtsbm1()); //账户代码
            orObj.setJzbs("0"); //未结帐
            condition.add("qxdm='" + InterfaceDj.getQxdm(userData) + "'");
            condition.add("xspzh='" + xspzh + "'");
            condition.add("dsjsjdm='" + form.getDsjsjdm() + "'");
            if (da.query(orObj.getClass(), condition).size() > 0)
            {
                throw new ApplicationException("录入的销售数据已存在！");
            }
            else
            {
                try
                {
                    da.insert(orObj);
                }
                catch (BaseException ex3)
                {
                    ex3.printStackTrace();
                    throw new ApplicationException("保存录入的销售数据出错！");
                } //end try
            } //end if

            //名细数据处理
            ArrayList list = form.getDataList();
            for (int i = 0; i < list.size(); i++)
            {
                HashMap map = (HashMap) list.get(i);
                //如果没有数量，则不保存
                if (map.get("gpsl").equals(""))
                {
                    continue;
                }
                Yhsgmmx orMx = new Yhsgmmx();
                map.remove("pzzldm");
                map.remove("mz");
                map.remove("pzzlmc");
                map.put("xspzh", xspzh);
                map.put("dsjsjdm", form.getDsjsjdm());
                map.put("jsjdm", form.getJsjdm());
                map.put("dwmc", form.getDwmc());
                map.put("gjdqdm", form.getGjdqdm());
                map.put("zjlxdm", form.getZjlxdm());
                map.put("zjhm", form.getZjhm());
                map.put("swjgzzjgdm", form.getSwjgzzjgdm());
                BeanUtil.populate(orMx, map);
                orMx.setNd("" + orObj.getNd());

//                orMx.setCjrq(SfTimeUtil.getNowTimestamp());
                orMx.setCjrq(cjrq);
                orMx.setLrrq(cjrq);
                orMx.setQxdm(InterfaceDj.getQxdm(userData));
                Vector conditionmx = new Vector();
                conditionmx.add("qxdm='" + InterfaceDj.getQxdm(userData) + "'");
                conditionmx.add("dsjsjdm='" + form.getDsjsjdm() + "'");
                conditionmx.add("xspzh='" + form.getXspzh() + "'");
                conditionmx.add("spmzdm='" + map.get("spmzdm") + "'");
                if (da.query(orMx.getClass(), conditionmx).size() > 0)
                {
                    throw new ApplicationException("录入的销售明细数据已存在！");
                }
                else
                {
                    try
                    {
                        da.insert(orMx);
                    }
                    catch (BaseException ex3)
                    {
                        ex3.printStackTrace();
                        throw new ApplicationException("保存录入的销售明细数据出错！");
                    } //end try
                } //end if
            } //end for

            List tempDataList = (List) form.getDataList().clone();
            List inputDataList = new ArrayList();
            for (int i = 0; i < tempDataList.size(); i++)
            {
                Map map = (Map) tempDataList.get(i);
                if (map.get("gpsl") == null
                    || ( (String) map.get("gpsl")).equals("")
                    || ( (String) map.get("gpsl")).equals("0"))
                {
                    //donothing
                }
                else
                {
                    map.put("sequence", "" + (i + 1));
                    inputDataList.add(map);
                }
            }
            for (int i = 0; i < inputDataList.size(); i++)
            {
                Map map = (Map) inputDataList.get(i);
            }
            //调用票证接口印花税票检验，并更新库存
            String zhdm = vo.getUserData().getXtsbm1();
            List yhspList = com.ttsoft.bjtax.pzgl.proxy.ServiceProxy.
                            checkSpsl(vo.getUserData(), zhdm,
                                      (List) form.getDataList().clone());
            checkYhspsl(yhspList);
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
        } //end try
    }

    /**
     * doReader
     * @param vo 数据集对象（包括Form和UserData对象）
     * @throws BaseException
     */
    private void doReader (VOPackage vo)
        throws BaseException
    {
    }

    /**
     * 获得销售凭证号
     * @param dsjsjdm 代售单位计算机代码
     * @return nextXspzh
     * @throws java.lang.Exception
     */
    private static String getNextXspzh (String dsjsjdm)
        throws
        Exception
    {
        StringBuffer sequence = new StringBuffer();
        long nextXspzh = 0;
        //数据库连接
        Connection conn = null;
        try
        {
            // 获得数据库连接
            conn = SfDBResource.getConnection();
            StringBuffer sqlbuffer = new StringBuffer();
            sqlbuffer.append("SELECT MAX(TO_NUMBER(XSPZH)) sequ ")
                .append("from SBDB.SB_JL_YHSGMZ ")
                .append("WHERE DSJSJDM = '")
                .append(dsjsjdm)
                .append("'");
            SfDBUtils sfDB = new SfDBUtils(conn);
            SfHashList sequList = sfDB.getData(sqlbuffer.toString());
            if (sequList.size() > 0)
            {
                nextXspzh = sequList.getLong(0, "sequ", 0);
            }
            nextXspzh++;
            String nextXspzhStr = (nextXspzh > 0) ? String.valueOf(nextXspzh)
                                  : "1";
            for (int i = 0; i < 8 - nextXspzhStr.length(); i++)
            {
                sequence.append("0");
            }
            sequence.append(nextXspzhStr);
            return sequence.toString();
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "获得销售凭证号失败!");
        }
        finally
        {
            SfDBResource.freeConnection(conn);
        }
    }

    /**
     * 根据UerData得到代售单位计算机代码
     * @param strSsdwdm 所属单位代码
     * @return 代售单位计算机代码
     */
    private String getDsjsjdmByUserData (String strSsdwdm)
    {
        String dsJsjdm = "";
        dsJsjdm = CodeUtils.getCodeMapValue("ZHSB_SWJGZZJG",
                                            "swjgzzjgdm", strSsdwdm, "jsjdm");
        return dsJsjdm;
    }

    /**
     * 检验调用票证接口返回的List中是否有Error Message，如有则在客户端显示
     * @param yhspList 印花税票列表
     * @throws ApplicationException
     */
    private void checkYhspsl (List yhspList)
        throws ApplicationException
    {
        String errorMessage = "";
        for (int i = 0; i < yhspList.size(); i++)
        {
            Map map = (Map) yhspList.get(i);
            if (map.get("error") != null)
            {
                int intError = Integer.parseInt( (String) map.get("error"));
                if (intError == -1)//当前操作员无权销售此种印花税票
                {
                    errorMessage = errorMessage + "请检查序号为" +
                                   (String) map.get("sequence") + "的印花税票，" +
                                   "您没有权限销售此种印花税票。<BR>";
                }
                else//当前操作员可销售的此种印花税票库存量不足
                {
                    errorMessage = errorMessage + "请检查序号为" +
                                   (String) map.get("sequence") + "的印花税票，" +
                                   "此种印花税票的库存总量为" + intError + "。<BR>";
                }
            }
        }
        if (!errorMessage.equals(""))
        {
            throw new ApplicationException(errorMessage);
        }
    }
}