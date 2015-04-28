/*
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */
package com.ttsoft.bjtax.smsb.print.processor;

import java.sql.Connection;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.pzgl.proxy.ServiceProxy;
import com.ttsoft.bjtax.sfgl.common.code.CodeUtils;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBUtils;
import com.ttsoft.bjtax.sfgl.common.util.Debug;
import com.ttsoft.bjtax.shenbao.model.domain.Gtgshwszmx;
import com.ttsoft.bjtax.shenbao.model.domain.Gtgshwszz;
import com.ttsoft.bjtax.shenbao.model.domain.Lsswszmx;
import com.ttsoft.bjtax.shenbao.model.domain.Lsswszz;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.print.web.WszPrintForm;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.Currency;
import com.ttsoft.framework.util.StringUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Description: 打印完税证</p>
 * @author 开发六组－－陆峰
 * @version 1.1
 */
public class WszPrintProcessor
    implements Processor
{
    public WszPrintProcessor ()
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
            case CodeConstant.SMSB_PRINTACTION:
                result = doSuccess(vo); //打印成功
                break;
            case CodeConstant.SMSB_REPRINTACTION:
                result = doReprint(vo);
                break;
            default:
                throw new ApplicationException(
                    "ActionType有错误，processor中找不到相应的方法.");
        }
        return result;
    }

    /**
     * 页面初始化
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return 当前页面对应的Form对象
     * @throws BaseException
     */
    private Object doShow (VOPackage vo)
        throws BaseException
    {
        WszPrintForm pf = new WszPrintForm();
        try
        {
            pf = (WszPrintForm) doQuery(vo);
        }
        catch (Exception ex)
        {
            throw new ApplicationException(ex.getMessage());
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
        Connection conn = null;
        SfDBUtils sfDB = null;
        double hjje = 0;
        String mxSz = "";
        String mxPmmc = "";
        String mxKssl = "";
        String mxJsje = "";
        String mxSl = "";
        String mxYjhkc = "";
        String mxSjse = "";
        String mxSkssrq ="";

        WszPrintForm pf = new WszPrintForm();
        pf = (WszPrintForm) vo.getData();
        //设置格式化数字
        DecimalFormat deFormat = new DecimalFormat("#0.00");

        try
        {
            conn = SfDBResource.getConnection();
            SfDBAccess da = new SfDBAccess(conn);
            //UserData对象
            UserData ud = (UserData) vo.getUserData();
            //得到区县代码
            String qxdm = InterfaceDj.getQxdm(ud);
            //从登记接口中获得信息
            SWDJJBSJ dj = (SWDJJBSJ) InterfaceDj.getJBSJ_New(pf.getJsjdm(), ud);
            if (dj == null)
            {
                throw new ApplicationException("获取登记信息出错！");
            }
            pf.setSwjgzzjgdm(dj.getSwjgzzjgdm());
            pf.setSwjgzzjgmc(dj.getSwjgzzjgmc());
            pf.setZsjg(pf.getSwjgzzjgmc()); //证收机关名称
            pf.setDfswjg(pf.getSwjgzzjgmc()); //地方税务机关

            //设置查询条件
            Vector tempVector = new Vector();
            Vector dmVector = new Vector();
            ArrayList dmList = new ArrayList();
            //个体工商户
            String fromPage = pf.getFromPage();
            if (fromPage.toLowerCase().indexOf("gtgsh") >= 0)
            {
                tempVector.clear();
                tempVector.addElement("qxdm='" + qxdm + "'");
                tempVector.addElement("ndzb='" + pf.getNdzb() + "'");
                tempVector.addElement("(sbhzdh is null or sbhzdh='')");
                tempVector.addElement("wszh='" + pf.getHeadWszh() + "'");
                tempVector.addElement("1=1 order by wszxh desc");
                ArrayList zbList = (ArrayList) da.query(Gtgshwszz.class,
                    tempVector);

                if (zbList.size() <= 0)
                {
                    throw new ApplicationException("没有找到符合条件的完税证信息！");
                }
                Gtgshwszz zb = (Gtgshwszz) zbList.get(0);
                pf.setHeadWszh(zb.getWszh());
                pf.setZclx("个体工商户"); //注册类型，暂时写死
                pf.setTfrq( (String.valueOf(zb.getCjrq())).substring(0, 10));
                pf.setNsrjsjdm(zb.getNsrjsjdm()); //纳税人计算机代码
                //从登记接口中获得信息
                SWDJJBSJ djNsr = (SWDJJBSJ) InterfaceDj.getJBSJ_New(zb.getNsrjsjdm(),
                    ud);
                pf.setNsrmc(djNsr.getNsrmc()); //纳税人名称
                pf.setDz(djNsr.getJydz()); //获得纳税人经营地址
                pf.setBz(djNsr.getNsrmc()); //备注
                pf.setHjje(deFormat.format(zb.getHjsjje())); //合计金额
                pf.setHjjedx(Currency.convert(zb.getHjsjje())); //合计金额大写

                //明细信息
                tempVector.clear();
                tempVector.addElement("qxdm='" + qxdm + "'");
                tempVector.addElement("ndzb='" + pf.getNdzb() + "'");
                tempVector.addElement("wszh='" + pf.getHeadWszh() + "'");
                tempVector.addElement("wszxh='" + zb.getWszxh() + "'");
                tempVector.addElement("1=1 order by wszxh desc");
                ArrayList mxList = (ArrayList) da.query(Gtgshwszmx.class,
                    tempVector);
                if (mxList.size() <= 0)
                {
                    throw new ApplicationException("没有找到符合条件的完税证信息！");
                }
                Gtgshwszmx mx = new Gtgshwszmx();

                for (int i = 0; i < mxList.size(); i++)
                {
                    mx = (Gtgshwszmx) mxList.get(i);
                    HashMap map = new HashMap();
                    if (mx.getKssl() == null)
                    {
                        map.put("kssl", " ");
                    }
                    else
                    {
                        map.put("kssl", String.valueOf(mx.getKssl()));

                    }
                    if (mx.getJsje() == null)
                    {
                        map.put("jsje", " ");
                    }
                    else
                    {
                        map.put("jsje",
                                deFormat.format(StringUtil.getDouble(String.
                            valueOf(mx.getJsje()), 0.00)));

                    }
                    if (mx.getSl() == null)
                    {
                        map.put("sl", " ");
                    }
                    else
                    {
                        map.put("sl", String.valueOf(mx.getSl()));

                    }
                    if (mx.getKssl() == null)
                    {
                        map.put("yjhkc", " ");
                    }
                    else
                    {
                        map.put("yjhkc",
                                deFormat.format(StringUtil.getDouble(String.
                            valueOf(mx.getYjhkc()), 0.00)));

                    }
                    if (mx.getSjse() == null)
                    {
                        map.put("sjse", " ");
                    }
                    else
                    {
                        map.put("sjse",
                                deFormat.format(StringUtil.getDouble(String.
                            valueOf(mx.getSjse()), 0.00)));

                    }
//                    pf.setSkssksrq(String.valueOf(mx.getSkssksrq()).substring(0,
//                        10));
//                    pf.setSkssjsrq(String.valueOf(mx.getSkssjsrq()).substring(0,
//                        10));

                    if (mx.getSkssksrq() == null)
                    {
                    	map.put("skssksrq", " ");
                    }
                    else
                    {
                    	map.put("skssksrq",
                        		String.valueOf(mx.getSkssksrq()).substring(0,10).replaceAll("-", ""));
                    }
                    
                    if (mx.getSkssjsrq() == null)
                    {
                    	map.put("skssjsrq", " ");
                    }
                    else
                    {
                    	map.put("skssjsrq",
                        		String.valueOf(mx.getSkssjsrq()).substring(0,10).replaceAll("-", ""));
                    }
                    
                    map.put("szmc",
                            CodeUtils.getCodeBeanLabel("DM_SZSM", mx.getSzdm()));
                    map.put("szsmmc",
                            CodeUtils.getCodeBeanLabel("DM_SZSM", mx.getSzsmdm()));

                    mxSz += map.get("szmc") + ";;";
                    mxPmmc += map.get("szsmmc") + ";;";
                    mxKssl += map.get("kssl") + ";;";
                    mxJsje += map.get("jsje") + ";;";
                    mxSl += map.get("sl") + ";;";
                    mxSkssrq += map.get("skssksrq") + "-"+map.get("skssjsrq") + ";;";
                    mxYjhkc += map.get("yjhkc") + ";;";
                    mxSjse += map.get("sjse") + ";;";
                    dataList.add(map);
                }
                pf.setMxSz(mxSz);
                pf.setMxPmmc(mxPmmc);
                pf.setMxKssl(mxKssl);
                pf.setMxJsje(mxJsje);
                pf.setMxSl(mxSl);
                pf.setMxSkssrq(mxSkssrq);
                pf.setMxYjhkc(mxYjhkc);
                pf.setMxSjse(mxSjse);

                pf.setDataList(dataList);
            }
            else
            { //零散税征收
                tempVector.clear();
                tempVector.addElement("qxdm='" + qxdm + "'");
                tempVector.addElement("(sbhzdh is null or sbhzdh='')");
                tempVector.addElement("wszh='" + pf.getHeadWszh() + "'");
                tempVector.addElement("1=1 order by wszxh desc");
                ArrayList zbList = (ArrayList) da.query(Lsswszz.class,
                    tempVector);

                if (zbList.size() <= 0)
                {
                    throw new ApplicationException("没有找到符合条件的完税证信息！");
                }

                Lsswszz zb = (Lsswszz) zbList.get(0);
                pf.setHeadWszh(zb.getWszh());
                pf.setZclx(""); //注册类型，暂时写死
                pf.setTfrq( (String.valueOf(zb.getCjrq())).substring(0, 10));
                pf.setNsrjsjdm(pf.getJsjdm()); //单位的计算机代码
                pf.setNsrmc(zb.getNsrmc());
                pf.setDz(zb.getDz()); //零散的话取自己的地址
                pf.setBz(zb.getNsrmc()); //备注
                pf.setHjje(deFormat.format(zb.getHjsjje())); //合计金额
                pf.setHjjedx(Currency.convert(zb.getHjsjje())); //合计金额大写

                //pf.setSkssksrq(zb.gets);税款所属日期根据明细信息而来
                //明细信息
                tempVector.clear();
                tempVector.addElement("qxdm='" + qxdm + "'");
                tempVector.addElement("wszh='" + pf.getHeadWszh() + "'");
                tempVector.addElement("wszxh='" + zb.getWszxh() + "'");
                tempVector.addElement("1=1 order by wszxh desc");
                ArrayList mxList = (ArrayList) da.query(Lsswszmx.class,
                    tempVector);

                if (mxList.size() <= 0)
                {
                    throw new ApplicationException("没有找到符合条件的完税证信息！");
                }

                Lsswszmx mx = new Lsswszmx();
                for (int i = 0; i < mxList.size(); i++)
                {
                    mx = (Lsswszmx) mxList.get(i);
                    HashMap map = new HashMap();
                    if (mx.getKssl() == null)
                    {
                        map.put("kssl", " ");
                    }
                    else
                    {
                        map.put("kssl", String.valueOf(mx.getKssl()));

                    }
                    if (mx.getJsje() == null)
                    {
                        map.put("jsje", " ");
                    }
                    else
                    {
                        map.put("jsje",
                                deFormat.format(StringUtil.getDouble(String.
                            valueOf(mx.getJsje()), 0.00)));

                    }
                    if (mx.getSl() == null)
                    {
                        map.put("sl", " ");
                    }
                    else
                    {
                        map.put("sl", String.valueOf(mx.getSl()));

                    }
                    if (mx.getKssl() == null)
                    {
                        map.put("yjhkc", " ");
                    }
                    else
                    {
                        map.put("yjhkc",
                                deFormat.format(StringUtil.getDouble(String.
                            valueOf(mx.getYjhkc()), 0.00)));

                    }
                    if (mx.getSjse() == null)
                    {
                        map.put("sjse", " ");
                    }
                    else
                    {
                        map.put("sjse",
                                deFormat.format(StringUtil.getDouble(String.
                            valueOf(mx.getSjse()), 0.00)));

                    }
//                    pf.setSkssksrq(String.valueOf(mx.getSkssksrq()).substring(0,
//                            10));
//                    pf.setSkssjsrq(String.valueOf(mx.getSkssjsrq()).substring(0,
//                        10));

                    if (mx.getSkssksrq() == null)
                    {
                    	map.put("skssksrq", " ");
                    }
                    else
                    {
                    	map.put("skssksrq",
                        		String.valueOf(mx.getSkssksrq()).substring(0,10).replaceAll("-", ""));
                    }
                    
                    if (mx.getSkssjsrq() == null)
                    {
                    	map.put("skssjsrq", " ");
                    }
                    else
                    {
                    	map.put("skssjsrq",
                        		String.valueOf(mx.getSkssjsrq()).substring(0,10).replaceAll("-", ""));
                    }
                    
                    map.put("szmc",
                            CodeUtils.getCodeBeanLabel("DM_SZSM", mx.getSzdm()));
                    map.put("szsmmc",
                            CodeUtils.getCodeBeanLabel("DM_SZSM", mx.getSzsmdm()));

                    mxSz += map.get("szmc") + ";;";
                    mxPmmc += map.get("szsmmc") + ";;";
                    mxKssl += map.get("kssl") + ";;";
                    mxJsje += map.get("jsje") + ";;";
                    mxSl += map.get("sl") + ";;";
                    mxSkssrq += map.get("skssksrq") + "-"+map.get("skssjsrq") + ";;";
                    mxYjhkc += map.get("yjhkc") + ";;";
                    mxSjse += map.get("sjse") + ";;";
                    dataList.add(map);
                }
                pf.setMxSz(mxSz);
                pf.setMxPmmc(mxPmmc);
                pf.setMxKssl(mxKssl);
                pf.setMxJsje(mxJsje);
                pf.setMxSl(mxSl);
                pf.setMxSkssrq(mxSkssrq);
                pf.setMxYjhkc(mxYjhkc);
                pf.setMxSjse(mxSjse);

                pf.setDataList(dataList);
            } //End of if
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
     * 删除
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return 当前页面对应的Form对象
     * @throws BaseException
     */
    private Object doDelete (VOPackage vo)
        throws BaseException
    {
        return null;
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

    /**
     * 打印成功，设置完税证的打印标记
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return 当前页面对应的Form对象
     * @throws BaseException
     */
    private Object doSuccess (VOPackage vo)
        throws BaseException
    {
        List dataList = new ArrayList();
        Connection conn = null;
        SfDBUtils sfDB = null;
        double hjje = 0;
        String wszh = "";
        String mxSz = "";
        String mxPmmc = "";
        String mxKssl = "";
        String mxJsje = "";
        String mxSl = "";
        String mxYjhkc = "";
        String mxSjse = "";
        String mxSkssrq ="";

        WszPrintForm pf = new WszPrintForm();
        pf = (WszPrintForm) vo.getData();
        //设置格式化数字
        DecimalFormat deFormat = new DecimalFormat("#0.00");
        Timestamp nowTime = new Timestamp(System.currentTimeMillis());
        try
        {
            conn = SfDBResource.getConnection();
            SfDBAccess da = new SfDBAccess(conn);
            //UserData对象
            UserData ud = (UserData) vo.getUserData();
            //得到区县代码
            String qxdm = InterfaceDj.getQxdm(ud);
            //从登记接口中获得信息
            SWDJJBSJ dj = (SWDJJBSJ) InterfaceDj.getJBSJ_New(pf.getJsjdm(), ud);
            if (dj == null)
            {
                throw new ApplicationException("获取登记信息出错！");
            }
            pf.setSwjgzzjgdm(dj.getSwjgzzjgdm());
            pf.setSwjgzzjgmc(dj.getSwjgzzjgmc());
            pf.setZsjg(pf.getSwjgzzjgmc()); //证收机关名称
            pf.setDfswjg(pf.getSwjgzzjgmc()); //地方税务机关

            //设置查询条件
            Vector tempVector = new Vector();
            Vector dmVector = new Vector();
            ArrayList dmList = new ArrayList();
            /***/
            String fromPage = pf.getFromPage();
            if (fromPage.toLowerCase().indexOf("gtgsh") >= 0)
            {

                tempVector.clear();
                tempVector.addElement("qxdm='" + qxdm + "'");
                tempVector.addElement("ndzb='" + pf.getNdzb() + "'");
                tempVector.addElement("(sbhzdh is null or sbhzdh='')");
                tempVector.addElement("wszh='" + pf.getHeadWszh() + "'");
                tempVector.addElement("1=1 order by wszh desc");
                ArrayList zbList = (ArrayList) da.query(Gtgshwszz.class,
                    tempVector);

                if (zbList.size() <= 0)
                {
                    throw new ApplicationException("没有找到符合条件的完税证信息！");
                }
                Gtgshwszz zb = (Gtgshwszz) zbList.get(0);
                pf.setHeadWszh(zb.getWszh());
                pf.setZclx("个体工商户"); //注册类型，暂时写死
                pf.setTfrq( (String.valueOf(zb.getCjrq())).substring(0, 10));
                pf.setNsrjsjdm(zb.getNsrjsjdm()); //纳税人计算机代码
                //从登记接口中获得信息
                SWDJJBSJ djNsr = (SWDJJBSJ) InterfaceDj.getJBSJ_New(zb.getNsrjsjdm(),
                    ud);
                pf.setNsrmc(djNsr.getNsrmc()); //纳税人名称
                pf.setDz(djNsr.getJydz()); //获得纳税人经营地址
                pf.setBz(djNsr.getNsrmc()); //备注
                pf.setHjje(deFormat.format(zb.getHjsjje())); //合计金额
                pf.setHjjedx(Currency.convert(zb.getHjsjje())); //合计金额大写

                //明细信息
                tempVector.clear();
                tempVector.addElement("qxdm='" + qxdm + "'");
                tempVector.addElement("ndzb='" + pf.getNdzb() + "'");
                tempVector.addElement("wszh='" + pf.getHeadWszh() + "'");
                tempVector.addElement("wszxh='" + zb.getWszxh() + "'");
                tempVector.addElement("1=1 order by wszh desc");
                ArrayList mxList = (ArrayList) da.query(Gtgshwszmx.class,
                    tempVector);
                if (mxList.size() <= 0)
                {
                    throw new ApplicationException("没有找到符合条件的完税证信息！");
                }
                Gtgshwszmx mx = new Gtgshwszmx();
                for (int i = 0; i < mxList.size(); i++)
                {
                    mx = (Gtgshwszmx) mxList.get(i);
                    HashMap map = new HashMap();
                    if (mx.getKssl() == null)
                    {
                        map.put("kssl", " ");
                    }
                    else
                    {
                        map.put("kssl", String.valueOf(mx.getKssl()));

                    }
                    if (mx.getJsje() == null)
                    {
                        map.put("jsje", " ");
                    }
                    else
                    {
                        map.put("jsje",
                                deFormat.format(StringUtil.getDouble(String.
                            valueOf(mx.getJsje()), 0.00)));

                    }
                    if (mx.getSl() == null)
                    {
                        map.put("sl", " ");
                    }
                    else
                    {
                        map.put("sl", String.valueOf(mx.getSl()));

                    }
                    if (mx.getKssl() == null)
                    {
                        map.put("yjhkc", " ");
                    }
                    else
                    {
                        map.put("yjhkc",
                                deFormat.format(StringUtil.getDouble(String.
                            valueOf(mx.getYjhkc()), 0.00)));

                    }
                    if (mx.getSjse() == null)
                    {
                        map.put("sjse", " ");
                    }
                    else
                    {
                        map.put("sjse",
                                deFormat.format(StringUtil.getDouble(String.
                            valueOf(mx.getSjse()), 0.00)));

                    }
//                    pf.setSkssksrq(String.valueOf(mx.getSkssksrq()).substring(0,
//                            10));
//                    pf.setSkssjsrq(String.valueOf(mx.getSkssjsrq()).substring(0,
//                        10));

                    if (mx.getSkssksrq() == null)
                    {
                    	map.put("skssksrq", " ");
                    }
                    else
                    {
                    	map.put("skssksrq",
                        		String.valueOf(mx.getSkssksrq()).substring(0,10).replaceAll("-", ""));
                    }
                    
                    if (mx.getSkssjsrq() == null)
                    {
                    	map.put("skssjsrq", " ");
                    }
                    else
                    {
                    	map.put("skssjsrq",
                        		String.valueOf(mx.getSkssjsrq()).substring(0,10).replaceAll("-", ""));
                    }
                    
                    map.put("szmc",
                            CodeUtils.getCodeBeanLabel("DM_SZSM", mx.getSzdm()));
                    map.put("szsmmc",
                            CodeUtils.getCodeBeanLabel("DM_SZSM", mx.getSzsmdm()));

                    mxSz += map.get("szmc") + ";;";
                    mxPmmc += map.get("szsmmc") + ";;";
                    mxKssl += map.get("kssl") + ";;";
                    mxJsje += map.get("jsje") + ";;";
                    mxSl += map.get("sl") + ";;";
                    mxSkssrq += map.get("skssksrq") + "-"+map.get("skssjsrq") + ";;";
                    mxYjhkc += map.get("yjhkc") + ";;";
                    mxSjse += map.get("sjse") + ";;";
                    dataList.add(map);
                }
                pf.setMxSz(mxSz);
                pf.setMxPmmc(mxPmmc);
                pf.setMxKssl(mxKssl);
                pf.setMxJsje(mxJsje);
                pf.setMxSl(mxSl);
                pf.setMxSkssrq(mxSkssrq);
                pf.setMxYjhkc(mxYjhkc);
                pf.setMxSjse(mxSjse);

                pf.setDataList(dataList);

                //更新完税证，打印标志
                zb = (Gtgshwszz) zbList.get(0);
                zb.setClbjdm(CodeConstant.SMSB_WSZ_PRINT); //已打印
                try
                {
                    da.update(zb);
                }
                catch (BaseException ex1)
                {
                    ex1.printStackTrace();
                    throw new ApplicationException("更新完税证打印标志出错！");
                }
                //End of reget wszh
            }
            else
            { //零散税征收
                tempVector.clear();
                tempVector.addElement("qxdm='" + qxdm + "'");
                tempVector.addElement("ndzb='" + pf.getNdzb() + "'");
                tempVector.addElement("(sbhzdh is null or sbhzdh='')");
                tempVector.addElement("wszh='" + pf.getHeadWszh() + "'");
                tempVector.addElement("1=1 order by wszh desc");
                ArrayList zbList = (ArrayList) da.query(Lsswszz.class,
                    tempVector);

                if (zbList.size() <= 0)
                {
                    throw new ApplicationException("没有找到符合条件的完税证信息！");
                }

                Lsswszz zb = (Lsswszz) zbList.get(0);
                pf.setHeadWszh(zb.getWszh());
                pf.setZclx(""); //注册类型，暂时写死
                pf.setTfrq( (String.valueOf(zb.getCjrq())).substring(0, 10));
                pf.setNsrjsjdm(pf.getJsjdm()); //单位的计算机代码
                pf.setNsrmc(zb.getNsrmc());
                pf.setDz(zb.getDz()); //零散的话取自己的地址
                pf.setBz(zb.getNsrmc()); //备注
                pf.setHjje(deFormat.format(zb.getHjsjje())); //合计金额
                pf.setHjjedx(Currency.convert(zb.getHjsjje())); //合计金额大写

                //明细信息
                tempVector.clear();
                tempVector.addElement("qxdm='" + qxdm + "'");
                tempVector.addElement("ndzb='" + pf.getNdzb() + "'");
                tempVector.addElement("wszh='" + pf.getHeadWszh() + "'");
                tempVector.addElement("wszxh='" + zb.getWszxh() + "'");
                tempVector.addElement("1=1 order by wszh desc");
                ArrayList mxList = (ArrayList) da.query(Lsswszmx.class,
                    tempVector);

                if (mxList.size() <= 0)
                {
                    throw new ApplicationException("没有找到符合条件的完税证信息！");
                }

                Lsswszmx mx = new Lsswszmx();
                for (int i = 0; i < mxList.size(); i++)
                {
                    mx = (Lsswszmx) mxList.get(i);
                    HashMap map = new HashMap();
                    if (mx.getKssl() == null)
                    {
                        map.put("kssl", " ");
                    }
                    else
                    {
                        map.put("kssl", String.valueOf(mx.getKssl()));

                    }
                    if (mx.getJsje() == null)
                    {
                        map.put("jsje", " ");
                    }
                    else
                    {
                        map.put("jsje",
                                deFormat.format(StringUtil.getDouble(String.
                            valueOf(mx.getJsje()), 0.00)));

                    }
                    if (mx.getSl() == null)
                    {
                        map.put("sl", " ");
                    }
                    else
                    {
                        map.put("sl", String.valueOf(mx.getSl()));

                    }
                    if (mx.getKssl() == null)
                    {
                        map.put("yjhkc", " ");
                    }
                    else
                    {
                        map.put("yjhkc",
                                deFormat.format(StringUtil.getDouble(String.
                            valueOf(mx.getYjhkc()), 0.00)));

                    }
                    if (mx.getSjse() == null)
                    {
                        map.put("sjse", " ");
                    }
                    else
                    {
                        map.put("sjse",
                                deFormat.format(StringUtil.getDouble(String.
                            valueOf(mx.getSjse()), 0.00)));

                    }
//                    pf.setSkssksrq(String.valueOf(mx.getSkssksrq()).substring(0,
//                            10));
//                    pf.setSkssjsrq(String.valueOf(mx.getSkssjsrq()).substring(0,
//                        10));

                    if (mx.getSkssksrq() == null)
                    {
                    	map.put("skssksrq", " ");
                    }
                    else
                    {
                    	map.put("skssksrq",
                        		String.valueOf(mx.getSkssksrq()).substring(0,10).replaceAll("-", ""));
                    }
                    
                    if (mx.getSkssjsrq() == null)
                    {
                    	map.put("skssjsrq", " ");
                    }
                    else
                    {
                    	map.put("skssjsrq",
                        		String.valueOf(mx.getSkssjsrq()).substring(0,10).replaceAll("-", ""));
                    }
                    
                    map.put("szmc",
                            CodeUtils.getCodeBeanLabel("DM_SZSM", mx.getSzdm()));
                    map.put("szsmmc",
                            CodeUtils.getCodeBeanLabel("DM_SZSM", mx.getSzsmdm()));
                    mxSz += map.get("szmc") + ";;";
                    mxPmmc += map.get("szsmmc") + ";;";
                    mxKssl += map.get("kssl") + ";;";
                    mxJsje += map.get("jsje") + ";;";
                    mxSl += map.get("sl") + ";;";
                    mxSkssrq += map.get("skssksrq") + "-"+map.get("skssjsrq") + ";;";
                    mxYjhkc += map.get("yjhkc") + ";;";
                    mxSjse += map.get("sjse") + ";;";
                    dataList.add(map);
                }
                pf.setMxSz(mxSz);
                pf.setMxPmmc(mxPmmc);
                pf.setMxKssl(mxKssl);
                pf.setMxJsje(mxJsje);
                pf.setMxSl(mxSl);
                pf.setMxSkssrq(mxSkssrq);
                pf.setMxYjhkc(mxYjhkc);
                pf.setMxSjse(mxSjse);

                pf.setDataList(dataList);

                //更新完税证，打印标志
                zb = (Lsswszz) zbList.get(0);
                zb.setClbjdm(CodeConstant.SMSB_WSZ_PRINT); //已打印
                try
                {
                    da.update(zb);
                }
                catch (BaseException ex1)
                {
                    ex1.printStackTrace();
                    throw new ApplicationException("更新完税证打印标志出错！");
                }
                //End of reget wszh
            } //End of if
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
    } //End of doSuccess

    /**
     * 取号打印，作废当前号码，取出新的完税证号码
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return 当前页面对应的Form对象
     * @throws BaseException
     */
    private Object doReprint (VOPackage vo)
        throws BaseException
    {
        List dataList = new ArrayList();
        Connection conn = null;
        SfDBUtils sfDB = null;
        double hjje = 0;
        String wszh = ""; //完税证号
        String ndzb = ""; //年度字别
        String mxSz = "";
        String mxPmmc = "";
        String mxKssl = "";
        String mxJsje = "";
        String mxSl = "";
        String mxYjhkc = "";
        String mxSjse = "";
        String mxSkssrq ="";

        WszPrintForm pf = new WszPrintForm();
        pf = (WszPrintForm) vo.getData();
        //设置格式化数字
        DecimalFormat deFormat = new DecimalFormat("#0.00");
        Timestamp nowTime = new Timestamp(System.currentTimeMillis());
        try
        {
            conn = SfDBResource.getConnection();
            SfDBAccess da = new SfDBAccess(conn);
            //UserData对象
            UserData ud = (UserData) vo.getUserData();
            //得到区县代码
            String qxdm = InterfaceDj.getQxdm(ud);
            //从登记接口中获得信息
            SWDJJBSJ dj = (SWDJJBSJ) InterfaceDj.getJBSJ_New(pf.getJsjdm(), ud);
            if (dj == null)
            {
                throw new ApplicationException("获取登记信息出错！");
            }
            pf.setSwjgzzjgdm(dj.getSwjgzzjgdm());
            pf.setSwjgzzjgmc(dj.getSwjgzzjgmc());
            pf.setZsjg(pf.getSwjgzzjgmc()); //证收机关名称
            pf.setDfswjg(pf.getSwjgzzjgmc()); //地方税务机关

            //设置查询条件
            Vector tempVector = new Vector();
            Vector dmVector = new Vector();
            ArrayList dmList = new ArrayList();
            /***/
            String fromPage = pf.getFromPage();
            if (fromPage.toLowerCase().indexOf("gtgsh") >= 0)
            {
                tempVector.clear();
                tempVector.addElement("qxdm='" + qxdm + "'");
                //tempVector.addElement("clbjdm='" + CodeConstant.SMSB_WSZ_UNPRINT + "'");
                tempVector.addElement("ndzb='" + pf.getNdzb() + "'");
                tempVector.addElement("(sbhzdh is null or sbhzdh='')");
                tempVector.addElement("wszh='" + pf.getHeadWszh() + "'");
                tempVector.addElement("1=1 order by wszh desc");
                ArrayList zbList = (ArrayList) da.query(Gtgshwszz.class,
                    tempVector);

                if (zbList.size() <= 0)
                {
                    throw new ApplicationException("没有找到符合条件的完税证信息！");
                }
                Gtgshwszz zb = (Gtgshwszz) zbList.get(0);
                pf.setHeadWszh(zb.getWszh());
                pf.setZclx("个体工商户"); //注册类型，暂时写死
                pf.setTfrq( (String.valueOf(zb.getCjrq())).substring(0, 10));
                pf.setNsrjsjdm(zb.getNsrjsjdm()); //纳税人计算机代码
                //从登记接口中获得信息
                SWDJJBSJ djNsr = (SWDJJBSJ) InterfaceDj.getJBSJ_New(zb.getNsrjsjdm(),
                    ud);
                pf.setNsrmc(djNsr.getNsrmc()); //纳税人名称
                pf.setDz(djNsr.getJydz()); //获得纳税人经营地址
                pf.setBz(djNsr.getNsrmc()); //备注
                pf.setHjje(deFormat.format(zb.getHjsjje())); //合计金额
                pf.setHjjedx(Currency.convert(zb.getHjsjje())); //合计金额大写

                //明细信息
                tempVector.clear();
                tempVector.addElement("qxdm='" + qxdm + "'");
                tempVector.addElement("ndzb='" + pf.getNdzb() + "'");
                tempVector.addElement("wszh='" + pf.getHeadWszh() + "'");
                tempVector.addElement("wszxh='" + zb.getWszxh() + "'");
                tempVector.addElement("1=1 order by wszh desc");
                ArrayList mxList = (ArrayList) da.query(Gtgshwszmx.class,
                    tempVector);
                if (mxList.size() <= 0)
                {
                    throw new ApplicationException("没有找到符合条件的完税证信息！");
                }
                Gtgshwszmx mx = new Gtgshwszmx();
                for (int i = 0; i < mxList.size(); i++)
                {
                    mx = (Gtgshwszmx) mxList.get(i);
                    HashMap map = new HashMap();
                    if (mx.getKssl() == null)
                    {
                        map.put("kssl", " ");
                    }
                    else
                    {
                        map.put("kssl", String.valueOf(mx.getKssl()));

                    }
                    if (mx.getJsje() == null)
                    {
                        map.put("jsje", " ");
                    }
                    else
                    {
                        map.put("jsje",
                                deFormat.format(StringUtil.getDouble(String.
                            valueOf(mx.getJsje()), 0.00)));

                    }
                    if (mx.getSl() == null)
                    {
                        map.put("sl", " ");
                    }
                    else
                    {
                        map.put("sl", String.valueOf(mx.getSl()));

                    }
                    if (mx.getKssl() == null)
                    {
                        map.put("yjhkc", " ");
                    }
                    else
                    {
                        map.put("yjhkc",
                                deFormat.format(StringUtil.getDouble(String.
                            valueOf(mx.getYjhkc()), 0.00)));

                    }
                    if (mx.getSjse() == null)
                    {
                        map.put("sjse", " ");
                    }
                    else
                    {
                        map.put("sjse",
                                deFormat.format(StringUtil.getDouble(String.
                            valueOf(mx.getSjse()), 0.00)));

                    }
//                    pf.setSkssksrq(String.valueOf(mx.getSkssksrq()).substring(0,
//                            10));
//                    pf.setSkssjsrq(String.valueOf(mx.getSkssjsrq()).substring(0,
//                        10));

                    if (mx.getSkssksrq() == null)
                    {
                    	map.put("skssksrq", " ");
                    }
                    else
                    {
                    	map.put("skssksrq",
                        		String.valueOf(mx.getSkssksrq()).substring(0,10).replaceAll("-", ""));
                    }
                    
                    if (mx.getSkssjsrq() == null)
                    {
                    	map.put("skssjsrq", " ");
                    }
                    else
                    {
                    	map.put("skssjsrq",
                        		String.valueOf(mx.getSkssjsrq()).substring(0,10).replaceAll("-", ""));
                    }
                    map.put("szmc",
                            CodeUtils.getCodeBeanLabel("DM_SZSM", mx.getSzdm()));
                    map.put("szsmmc",
                            CodeUtils.getCodeBeanLabel("DM_SZSM", mx.getSzsmdm()));

                    mxSz += map.get("szmc") + ";;";
                    mxPmmc += map.get("szsmmc") + ";;";
                    mxKssl += map.get("kssl") + ";;";
                    mxJsje += map.get("jsje") + ";;";
                    mxSl += map.get("sl") + ";;";
                    mxSkssrq += map.get("skssksrq") + "-"+map.get("skssjsrq") + ";;";
                    mxYjhkc += map.get("yjhkc") + ";;";
                    mxSjse += map.get("sjse") + ";;";
                    dataList.add(map);
                }
                pf.setMxSz(mxSz);
                pf.setMxPmmc(mxPmmc);
                pf.setMxKssl(mxKssl);
                pf.setMxJsje(mxJsje);
                pf.setMxSl(mxSl);
                pf.setMxSkssrq(mxSkssrq);
                pf.setMxYjhkc(mxYjhkc);
                pf.setMxSjse(mxSjse);

                pf.setDataList(dataList);

                //重新取号然后打印，1、取号,同时作废原完税证号，2、更新数据
                //1、作废原完税证号，同时重新取号
                //获得完税证号
                try
                {
                    String retResult = ServiceProxy.setCancellation(ud,
                        pf.getPzzldm(),
                        pf.getNdzb() + pf.getHeadWszh(),
                        StringUtil.getDouble(pf.getHjje(), 0.00),
                        "1", "1", "1");
                    ndzb = retResult.substring(0, 4); //年度字别
                    wszh = retResult.substring(4); //完税证号
                }
                catch (Exception ex1)
                {
                    ex1.printStackTrace();
                    throw new ApplicationException("获取完税证失败！" + ex1.getMessage());
                }
                pf.setHeadWszh(wszh);
                pf.setNdzb(ndzb);
                //2、更新数据，完税证
                //明细表
                for (int i = 0; i < mxList.size(); i++)
                {
                    mx = (Gtgshwszmx) mxList.get(i);
                    mx.setWszh(wszh);
                    mx.setLrrq(nowTime); //录入日期
                    mx.setNdzb(ndzb); //年度字别
                    da.update(mx);
                }
                //主表
                for (int i = 0; i < zbList.size(); i++)
                {
                    zb = (Gtgshwszz) zbList.get(i);
                    zb.setWszh(wszh);
                    zb.setLrrq(nowTime); //录入日期
                    zb.setNdzb(ndzb); //年度字别
                    da.update(zb);
                }
                //End of reget wszh
            }
            else
            { //零散税征收
                tempVector.clear();
                tempVector.addElement("qxdm='" + qxdm + "'");
                tempVector.addElement("ndzb='" + pf.getNdzb() + "'");
                tempVector.addElement("(sbhzdh is null or sbhzdh='')");
                tempVector.addElement("wszh='" + pf.getHeadWszh() + "'");
                tempVector.addElement("1=1 order by wszh desc");
                ArrayList zbList = (ArrayList) da.query(Lsswszz.class,
                    tempVector);

                if (zbList.size() <= 0)
                {
                    throw new ApplicationException("没有找到符合条件的完税证信息！");
                }

                Lsswszz zb = (Lsswszz) zbList.get(0);
                pf.setHeadWszh(zb.getWszh());
                pf.setZclx(""); //注册类型，暂时写死
                pf.setTfrq( (String.valueOf(zb.getCjrq())).substring(0, 10));
                pf.setNsrjsjdm(pf.getJsjdm()); //单位的计算机代码
                pf.setNsrmc(zb.getNsrmc());
                pf.setDz(zb.getDz()); //零散的话取自己的地址
                pf.setBz(zb.getNsrmc()); //备注
                pf.setHjje(deFormat.format(zb.getHjsjje())); //合计金额
                pf.setHjjedx(Currency.convert(zb.getHjsjje())); //合计金额大写

                //明细信息
                tempVector.clear();
                tempVector.addElement("qxdm='" + qxdm + "'");
                tempVector.addElement("ndzb='" + pf.getNdzb() + "'");
                tempVector.addElement("wszh='" + pf.getHeadWszh() + "'");
                tempVector.addElement("wszxh='" + zb.getWszxh() + "'");
                tempVector.addElement("1=1 order by wszh desc");
                ArrayList mxList = (ArrayList) da.query(Lsswszmx.class,
                    tempVector);

                if (mxList.size() <= 0)
                {
                    throw new ApplicationException("没有找到符合条件的完税证信息！");
                }

                Lsswszmx mx = new Lsswszmx();
                for (int i = 0; i < mxList.size(); i++)
                {
                    mx = (Lsswszmx) mxList.get(i);
                    HashMap map = new HashMap();
                    if (mx.getKssl() == null)
                    {
                        map.put("kssl", " ");
                    }
                    else
                    {
                        map.put("kssl", String.valueOf(mx.getKssl()));

                    }
                    if (mx.getJsje() == null)
                    {
                        map.put("jsje", " ");
                    }
                    else
                    {
                        map.put("jsje",
                                deFormat.format(StringUtil.getDouble(String.
                            valueOf(mx.getJsje()), 0.00)));

                    }
                    if (mx.getSl() == null)
                    {
                        map.put("sl", " ");
                    }
                    else
                    {
                        map.put("sl", String.valueOf(mx.getSl()));

                    }
                    if (mx.getKssl() == null)
                    {
                        map.put("yjhkc", " ");
                    }
                    else
                    {
                        map.put("yjhkc",
                                deFormat.format(StringUtil.getDouble(String.
                            valueOf(mx.getYjhkc()), 0.00)));

                    }
                    if (mx.getSjse() == null)
                    {
                        map.put("sjse", " ");
                    }
                    else
                    {
                        map.put("sjse",
                                deFormat.format(StringUtil.getDouble(String.
                            valueOf(mx.getSjse()), 0.00)));

                    }
//                    pf.setSkssksrq(String.valueOf(mx.getSkssksrq()).substring(0,
//                            10));
//                    pf.setSkssjsrq(String.valueOf(mx.getSkssjsrq()).substring(0,
//                        10));

                    if (mx.getSkssksrq() == null)
                    {
                    	map.put("skssksrq", " ");
                    }
                    else
                    {
                    	map.put("skssksrq",
                        		String.valueOf(mx.getSkssksrq()).substring(0,10).replaceAll("-", ""));
                    }
                    
                    if (mx.getSkssjsrq() == null)
                    {
                    	map.put("skssjsrq", " ");
                    }
                    else
                    {
                    	map.put("skssjsrq",
                        		String.valueOf(mx.getSkssjsrq()).substring(0,10).replaceAll("-", ""));
                    }
                    map.put("szmc",
                            CodeUtils.getCodeBeanLabel("DM_SZSM", mx.getSzdm()));
                    map.put("szsmmc",
                            CodeUtils.getCodeBeanLabel("DM_SZSM", mx.getSzsmdm()));
                    mxSz += map.get("szmc") + ";;";
                    mxPmmc += map.get("szsmmc") + ";;";
                    mxKssl += map.get("kssl") + ";;";
                    mxJsje += map.get("jsje") + ";;";
                    mxSl += map.get("sl") + ";;";
                    mxSkssrq += map.get("skssksrq") + "-"+map.get("skssjsrq") + ";;";
                    mxYjhkc += map.get("yjhkc") + ";;";
                    mxSjse += map.get("sjse") + ";;";
                    dataList.add(map);
                }
                pf.setMxSz(mxSz);
                pf.setMxPmmc(mxPmmc);
                pf.setMxKssl(mxKssl);
                pf.setMxJsje(mxJsje);
                pf.setMxSl(mxSl);
                pf.setMxSkssrq(mxSkssrq);
                pf.setMxYjhkc(mxYjhkc);
                pf.setMxSjse(mxSjse);

                pf.setDataList(dataList);

                //重新取号然后打印，1、取号，同时作废原完税证号，2、更新数据
                //1、作废原完税证号，同时重新取号
                //获得完税证号
                try
                {
                    String retResult = ServiceProxy.setCancellation(ud,
                        pf.getPzzldm(),
                        pf.getNdzb() + pf.getHeadWszh(),
                        StringUtil.getDouble(pf.getHjje(), 0.00),
                        "1", "1", "1");
                    ndzb = retResult.substring(0, 4); //年度字别
                    wszh = retResult.substring(4); //完税证号
                }
                catch (Exception ex1)
                {
                    ex1.printStackTrace();
                    throw new ApplicationException("获取完税证失败！" + ex1.getMessage());
                }
                pf.setHeadWszh(wszh);
                pf.setNdzb(ndzb);
                //2、更新数据，完税证
                //明细表
                for (int i = 0; i < mxList.size(); i++)
                {
                    mx = (Lsswszmx) mxList.get(i);
                    mx.setWszh(wszh);
                    mx.setLrrq(nowTime); //录入日期
                    mx.setNdzb(ndzb); //年度字别
                    da.update(mx);
                }
                //主表
                for (int i = 0; i < zbList.size(); i++)
                {
                    zb = (Lsswszz) zbList.get(i);
                    zb.setWszh(wszh);
                    zb.setLrrq(nowTime); //录入日期
                    zb.setNdzb(ndzb); //年度字别
                    da.update(zb);
                }
                //End of reget wszh
            } //End of if
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

}
//:-)
