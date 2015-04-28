/*
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */
package com.ttsoft.bjtax.smsb.gtgsh.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.ttsoft.bjtax.dj.model.QYRY;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.jikuai.zwcl.inf.JKAdapter;
import com.ttsoft.bjtax.jikuai.zwcl.vo.page.Yskm;
import com.ttsoft.bjtax.pzgl.proxy.ServiceProxy;
import com.ttsoft.bjtax.sfgl.common.Constant;
import com.ttsoft.bjtax.sfgl.common.code.CodeManager;
import com.ttsoft.bjtax.sfgl.common.constant.CodeConstants;
import com.ttsoft.bjtax.sfgl.common.db.util.BeanUtil;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBUtils;
import com.ttsoft.bjtax.sfgl.common.model.Cftsyhd;
import com.ttsoft.bjtax.sfgl.common.model.Grtszygsde;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.sfgl.common.util.SfStringUtils;
import com.ttsoft.bjtax.sfgl.common.util.Debug;
import com.ttsoft.bjtax.sfgl.model.orobj.Dqdedlmx1;
import com.ttsoft.bjtax.sfgl.model.orobj.Tszslmx;
import com.ttsoft.bjtax.shenbao.model.domain.Gtgshwszmx;
import com.ttsoft.bjtax.shenbao.model.domain.Gtgshwszz;
import com.ttsoft.bjtax.shenbao.model.domain.Szsm;
import com.ttsoft.bjtax.shenbao.model.domain.Zqrl;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.gtgsh.web.GtgshWszlrForm;
import com.ttsoft.bjtax.smsb.model.client.SbjkmxDis;
import com.ttsoft.bjtax.smsb.model.client.Ysjc;
import com.ttsoft.bjtax.smsb.util.EArray;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.InterfaceSf4Sb;
import com.ttsoft.bjtax.smsb.util.JksUtil;
import com.ttsoft.bjtax.smsb.util.Skssrq;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.bjtax.smsb.zhsb.web.ZhsbActionForm;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.StringUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Description: 个体工商户完税证录入</p>
 * @author 开发六组－－陆峰
 * @version 1.1
 */
public class GtgshWszlrProcessor
    implements Processor
{
    /**
     * 存储车房土、定期定额和附加税合一的MAP
     */
    private Map CDF = null;

    public GtgshWszlrProcessor ()
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
            case CodeConstant.SMSB_ZHSB_INITLIST:
                return getInitList(vo);
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
        List dataList = new ArrayList();

        Connection conn = null;
        SfDBUtils sfDB = null;
        Timestamp nowTime = new Timestamp(System.currentTimeMillis());
        GtgshWszlrForm pf = new GtgshWszlrForm();
        pf = (GtgshWszlrForm) vo.getData();

        try
        {
            pf.setLrrq(SfDateUtil.getDate());
            //UserData对象
            UserData ud = (UserData) vo.getUserData();
            //从登记接口中获得信息
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
            pf.setSwjgzzjgdm(dj.getSwjgzzjgdm());
            pf.setSwjgzzjgmc(dj.getSwjgzzjgmc());

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

        Connection conn = null;
        SfDBUtils sfDB = null;
        GtgshWszlrForm pf = new GtgshWszlrForm();
        pf = (GtgshWszlrForm) vo.getData();

        try
        {
            conn = SfDBResource.getConnection();
            sfDB = new SfDBUtils(conn);
            //UserData对象
            UserData ud = (UserData) vo.getUserData();
            //从登记接口中获得信息
            HashMap mapDJ = new HashMap();
            try
            {
                mapDJ = (HashMap) InterfaceDj.getDjInfo_New(pf.getNsrjsjdm(), ud);
            }
            catch (Exception ex5)
            {
                ex5.printStackTrace();
                throw ExceptionUtil.getBaseException(ex5);
            }
            //基本信息
            SWDJJBSJ dj = new SWDJJBSJ();
            try
            {
                dj = (SWDJJBSJ) mapDJ.get("JBSJ");
                pf.setNsrmc(dj.getNsrmc());
                pf.setSwjgzzjgdm2(dj.getSwjgzzjgdm());
                //Modified by lufent 20003-11-26
                pf.setGjbzhydm(CodeConstant.ZRR_JKS_GJBZHYDM); //国家标准行业代码 8190
                pf.setDjzclxdm(dj.getDjzclxdm()); //登记注册类型代码
                //pf.setDjzclxmc(dj.getDjzclxmc()); //
                pf.setDz(dj.getJydz()); //地址，经营地址
                //设置纳税人状态
                pf.setNsrzt(dj.getNsrzt());
            }
            catch (Exception ex1)
            {
                pf.setNsrmc("");
                pf.setSwjgzzjgdm2("");
                pf.setGjbzhydm(""); //国家标准行业代码
                pf.setDjzclxdm(""); //登记注册类型代码
                pf.setDz(""); //地址，经营地址
                ex1.printStackTrace();
                throw new ApplicationException("该纳税人基本信息不全！");
            }

            if ( (!dj.getDjzclxdm().equals(CodeConstant.DJZCLXDM_GTGSH)) &&
                (!dj.getDjzclxdm().equals(CodeConstant.DJZCLXDM_GRHH)))
            {
                //changed by zgl,添加了“个人合伙”（420）的情况
                throw new ApplicationException("该纳税人不是个体工商户！");
            }

            //企业人员信息
            QYRY qyry = new QYRY();
            try
            {
                qyry = (QYRY) mapDJ.get("QYRY");
                pf.setZjlxdm(qyry.getZjlxdm()); //证件类型代码
                //pf.setZjlxmc(""); //证件类型名称
                pf.setZjhm(qyry.getZjhm()); //证件号码
            }
            catch (Exception ex1)
            {
                pf.setZjlxdm(""); //证件类型代码
                pf.setZjhm(""); //证件号码
                ex1.printStackTrace();
                throw new ApplicationException("该纳税人企业信息不全！");
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
        List dataList = new ArrayList();
        Timestamp nowTime = new Timestamp(System.currentTimeMillis());
        String pzzldm = ""; //票证种类代码
        String zhdm = ""; //帐户代码
        String wszh = ""; //完税证号
        String ndzb = ""; //年度字别
        String wszxh = ""; //序号
        String nsrjsjdm = "";
        String nsrmc = "";
        String swjgzzjgdm = "";
        String qxdm = ""; // 区县代码

        String names[] =
            {
            "nsrjsjdm", "zjhm", "zjlxdm", "swjgzzjgdm",
            "wszh", "pzzldm", "hjsjje",
            "lrr", "swjgzzjgdm2", "gjbzhydm", "djzclxdm"};
        //remove 税务机关组织机构名称 ,"nsrmc","swjgzzjgmc" Modified by lufeng 20031031

        Connection conn = null;
        SfDBUtils sfDB = null;
        //from对象
        GtgshWszlrForm pf = new GtgshWszlrForm();
        pf = (GtgshWszlrForm) vo.getData();
        zhdm = pf.getZhdm();
        pzzldm = pf.getPzzldm();
        nsrjsjdm = pf.getNsrjsjdm();
        nsrmc = pf.getNsrmc();

        swjgzzjgdm = pf.getSwjgzzjgdm();

        UserData ud = (UserData) vo.getUserData(); //的到当前用户数据
        //ormapping对象
        Gtgshwszz orObjz = new Gtgshwszz();
        dataList = (List) pf.getDataList();
        try
        {
            conn = SfDBResource.getConnection();
            SfDBAccess da = new SfDBAccess(conn);

            qxdm = InterfaceDj.getQxdm(ud); //得到区县代码
            //登记注册类型代码和国家标准行业代码 不能为空！by lufeng 20031031
            if (pf.getGjbzhydm().equals(""))
            {
                //Modified by lufent 20003-11-26
                pf.setGjbzhydm(CodeConstant.ZRR_JKS_GJBZHYDM); //国家标准行业代码 8190
            }
            if (pf.getDjzclxdm().equals(""))
            {
                throw new ApplicationException("登记注册类型代码不能为空！");
            }
            //获得序号
            JksUtil ju = new JksUtil();
            try
            {
                wszxh = ju.getSequenceOfWSZXH(conn);
            }
            catch (Exception ex)
            {
                throw new ApplicationException("获取序列号出错！");
            }

            //获得完税证号
            try
            {
                String retResult = ServiceProxy.getNumber(ud, pzzldm,
                    StringUtil.getDouble(pf.
                                         getHjsjje(),
                                         StringUtil.getDouble(pf.getHjsjje(), 0)),
                    "1", "1");
                //票证接口修改，//Modified by lufeng 2003-12-01
                ndzb = retResult.substring(0, 4); //年度字别
                wszh = retResult.substring(4); //完税证号
            }
            catch (Exception ex1)
            {
                ex1.printStackTrace();
                throw new ApplicationException("获取完税证失败！请检查是否有可用的完税证号！");
            }

            pf.setWszh(wszh);
            pf.setNdzb(ndzb);
            try
            {
                BeanUtil.copyBeanToBean(names, pf, orObjz);
            }
            catch (Exception ex2)
            {
                throw new ApplicationException("格式化主表信息出错！");
            }
            //补充某些值
            orObjz.setWszxh(wszxh); //序号
            orObjz.setWszh(wszh);
            orObjz.setCjrq(nowTime); //创建日期
            orObjz.setLrrq(nowTime); //录入日期
            orObjz.setQxdm(qxdm); //区县代码
            orObjz.setJsjdm(pf.getJsjdm()); //计算机代码
            orObjz.setClbjdm(CodeConstant.SMSB_WSZ_UNPRINT); //处理标记代码，未打印 0
            orObjz.setFsdm(CodeConstant.FSDM_SMSB); //登记申报方式代码 1
            //设置年度
            orObjz.setNd(String.valueOf(SfDateUtil.getYear(SfDateUtil.getDate())));
            orObjz.setNdzb(ndzb); //年度字别
            //更新数据
            try
            {
                da.insert(orObjz);
            }
            catch (BaseException ex1)
            {
                ex1.printStackTrace();
                throw new ApplicationException("新增主表信息出错！");
            }

            //插入明细表
            for (int i = 0; i < dataList.size(); i++)
            {
                //初始化明细表
                Gtgshwszmx orObjmx = new Gtgshwszmx();
                HashMap map = (HashMap) dataList.get(i);
                try
                {
                    BeanUtil.populate(orObjmx, map);
                }
                catch (Exception ex3)
                {
                    throw new ApplicationException("格式化明细表信息出错！");
                }
                //设置其它值，表结构有问题，等修改后需修改此处的信息
                orObjmx.setWszxh(wszxh); //序号
                orObjmx.setPzzldm(pzzldm);
                orObjmx.setWszh(wszh);
                orObjmx.setNsrjsjdm(nsrjsjdm);
                orObjmx.setCjrq(nowTime); //创建日期
                orObjmx.setLrrq(nowTime); //录入日期
                orObjmx.setQxdm(qxdm); //区县代码
                //orObjmx.setNsrmc(nsrmc);
                orObjmx.setJsjdm(pf.getJsjdm()); //计算机代码
                orObjmx.setSwjgzzjgdm(swjgzzjgdm);
                orObjmx.setJzbz(CodeConstant.SMSB_JZBZ); //记账标志，默认六个0
                //设置年度
                orObjmx.setNd(String.valueOf(SfDateUtil.getYear(SfDateUtil.
                    getDate())));
                orObjmx.setNdzb(ndzb); //年度字别
                //获得预算级次 //Modified by lufeng 2003-11-26
                orObjmx.setYsjcdm(CodeConstant.YSJC_GTGSH); //给定的预算级次代码，21 地方级

                //Modified by lufeng 2004-07-05 明细表税种代码的判空
                if ( (orObjmx.getSzdm()) == null
                    || (orObjmx.getSzdm()).equals(""))
                {
                    throw new ApplicationException("税种代码为空，请检查或重新进入该页面！");
                }
                //获得预算科目
                try
                {
                    Yskm yskm = (Yskm) JKAdapter.getInstance().getYskm(orObjmx.
                        getSzsmdm(),
                        orObjz.getDjzclxdm(),
                        orObjz.getGjbzhydm(),
                        orObjmx.getYsjcdm());
                    orObjmx.setYskmdm(yskm.getYskmdm());
                }
                catch (Exception ex4)
                {
                    throw new ApplicationException("没有预算科目代码！");
                }

                if (orObjmx.getYskmdm().equals(""))
                {
                    throw new ApplicationException("预算科目代码不能为空！");
                }

                //更新数据
                try
                {
                    da.insert(orObjmx);
                }
                catch (BaseException ex6)
                {
                    ex6.printStackTrace();
                    throw new ApplicationException("新增明细表信息出错！");
                }
            } //end of loop
        }
        catch (BaseException ex)
        {
            //保存不成功，则作废刚刚取出的完税证号！
            try
            {
                wszh = ServiceProxy.setCancellation(ud,
                    pf.getPzzldm(), ndzb + wszh,
                    StringUtil.getDouble(pf.getHjsjje(),
                                         0.00),
                    "1", "0", "1");
            }
            catch (Exception ex5)
            {
                ex5.printStackTrace();
            }
            throw ExceptionUtil.getBaseException(ex);
        }
        finally
        {
            SfDBResource.freeConnection(conn);
        }
        return pf;
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
     * 得到初始化list包括税种税目list,附加税list
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return 当前页面对应的Form对象
     * @throws BaseException
     */
    private Object getInitList (VOPackage vo)
        throws BaseException
    {
        //获取form对象
        ZhsbActionForm form = (ZhsbActionForm) vo.getData();
        List ret = new ArrayList();
        Connection con = null;
        try
        {
            con = SfDBResource.getConnection();
            //得到税种税目下拉列表列表和附加税列表
            //EArray jsArray = new EArray(con);
            /**
             * 使用代码表生成税种税目下拉列表
             * Shi Yanfeng
             * 20031031
             */
            EArray jsArray = new EArray();
            String tempJsStr = jsArray.getArrayByCode("szsmlist_add",
                "ZHSB_SZSMADD");
            tempJsStr +=
                jsArray.getMsgsByCode("szsmdm", "ZHSB_SZSM",
                                      new ArrayList());
            //根据税费接口处理定期定额、定率和附加税
            List mxList = this.dealWithSfgl(form.getJsjdm(),
                                            this.getSzsmList(con),
                                            SfDateUtil.getDate(form.getSbrq()));
            //根据已经得到的征期日历map为明细数据添加税款所属日期
            Date date = new Date(); //SfDateUtil.getDate(form.getSbrq());
            if (date == null)
            {
                date = new Date();
            }
            this.addSkssrqByMap(form.getJsjdm(), mxList, date, con);
            form.setInitMxList(mxList);
            //生成明细数据的js数组
            tempJsStr += this.getMxJsArray(mxList);
            tempJsStr += "\nvar szsmlist_fields = [\"szsmdm\",\"szmc\",\"szsmmc\",\"skssksrq\",\"skssjsrq\",\"kssl\",\"jsje\",\"sjse\",\"szdm\",\"sffjs\",\"szsmdm_old\",\"asljbs\",\"sl\"];";
            tempJsStr += "\n"+this.getSzsmtskzsJs();
            form.setScriptStr(tempJsStr);
            //设置告知事项列表
            return form;

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            throw new ApplicationException("得到税种税目下拉列表列表和附加税列表信息出错！");
        }
        finally
        {
            SfDBResource.freeConnection(con);
        }

    }

    /**
     * 得到税种税目
     * @param con 数据连接对象
     * @return 税种税目的数据list
     * @throws BaseException
     */
    private List getSzsmList (Connection con)
        throws BaseException
    {
        List ret = new ArrayList();
        ret = CodeManager.getCodeList("ORSZSM", CodeConstants.CODE_MAP_ORLIST).
              getRecords();
        return ret;
    }

    /**
     * 根据税费管理认定情况处理税种税目数据<br>
     * 根据定期定额数据和营业税附加税数据处理税种税目list
     * @param jsjdm 个体工商户代码
     * @param szsmList 税种税目数据list
     * @param sbrq 申报日期
     * @return 处理后的根据税种税目对应的明细list
     * @throws java.lang.Exception
     */
    private List dealWithSfgl_old (String jsjdm, List szsmList, Date sbrq)
        throws
        Exception
    {
        //添加处理后数据的明细list
        List mxList = new ArrayList();
        //税费管理接口
        try
        {
            //得到征期类型为月的税款所属日期
            Map semiY = Skssrq.monthSkssrq(sbrq);
            Date skssjsrq = (Date) semiY.get(Skssrq.SKSSJSRQ);
            Date skssksrq = (Date) semiY.get(Skssrq.SKSSKSRQ);
            /**
             * 通过车房土、定期定额和附加税合一的接口得到相关数据
             *
             */
            Map cdfMap = this.getCDFSet(jsjdm, sbrq, skssksrq, skssjsrq);

            //定期定额信息
            Map dqdeInfo = this.getDqdeMap( (List) cdfMap.get(Constant.
                SFGL_SB_DQDE));
            //营业税附加税信息
            Map fjsInfo = this.getFjsMap( (List) cdfMap.get(Constant.
                SFGL_SB_FJS));
            //根据税费接口得到车房土信息
            Map cftInfo = this.getCftMap( (List) cdfMap.get(Constant.
                SFGL_SB_CFT));
            mxList = this.creatMxList(szsmList);

            //根据个税定额接口得到所有个人的定额合计
            List gsList = (List) cdfMap.get(Constant.SFGL_SB_GSDE);

            //处理附加税，定期定额，车房土情况
            //处理附加税，定期定额，车房土情况
            for (int i = 0; i < mxList.size(); i++)
            {
                SbjkmxDis mxData = (SbjkmxDis) mxList.get(i);
                // 处理税费管理中的附加税税率
                String szsmdm = mxData.getSzsmdm();
                if (mxData.getSffjs() != null && mxData.getSffjs().equals("2") &&
                    fjsInfo != null)
                {
                    //税目是营业税附加税，并且由税费取得核定信息
                    Tszslmx tszslmx = (Tszslmx) fjsInfo.get(szsmdm.substring(0,
                        2));
                    if (tszslmx != null)
                    {
                        mxData.setSl(tszslmx.getSl());
                    }
                }
                // 处理税费管理中的定期定额
                if (dqdeInfo != null)
                {
                    Dqdedlmx1 dqde = (Dqdedlmx1) dqdeInfo.get(szsmdm);
                    if (dqde != null)
                    {
                        if (dqde.getZsfsdm().equals("01"))
                        {
                            //征收方式为定额
                            mxData.setSjse(dqde.getYnsrd());
                            mxData.setJsje(dqde.getYnsrd());
                            mxData.setFromDqde(true);
                        }
                    }
                }
                // 处理车房土
                if (cftInfo != null)
                {
                    Cftsyhd cft = (Cftsyhd) cftInfo.get(szsmdm);
                    //实缴金额
                    if (cft != null)
                    {
                        BigDecimal money = cft.getSjje();
                        mxData.setSjse(money);
                        mxData.setJsje(money);
                        mxData.setKssl(cft.getKssl());
                    }
                }
                //处理工薪所得050110
                if (szsmdm.equals(CodeConstant.ZRR_GXSD_SZSMDM))
                {
                    //个税定额合计
                    BigDecimal hj = new BigDecimal(0);
                    //合计所有的个税定额
                    for (int ig = 0; ig < gsList.size(); ig++)
                    {
                        Grtszygsde temp = (Grtszygsde) gsList.get(ig);
                        hj = hj.add(temp.getHdske());
                    }
                    if (hj.longValue() != 0)
                    {
                        mxData.setSjse(hj);
                    }
                }

            }
            //处理半年的
            //mxList = this.dealWithSfgl4SY(jsjdm,mxList,sbrq);
            //处理季度的
            //mxList = this.dealWithSfgl4Q(jsjdm,mxList,sbrq);
            //处理月份的
            //mxList = this.dealWithSfgl4M(jsjdm,mxList,sbrq);

            return mxList;
        }
        catch (BaseException ex)
        {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex, "处理税种税目列表失败");
        }

    }

    /**
     * 根据税费管理认定情况处理征期类行为月的税种税目数据<br>
     * 征期类型代码１代表年、２代表半年、４代表季度、12代表月；
     * 根据定期定额数据和营业税附加税数据处理税种税目list，
     * 返回处理后的根据税种税目对应的明细list
     * @param jsjdm 计算机代码
     * @param szsmList 税种税目list
     * @param sbrq 申报日期
     * @return 处理后的根据税种税目对应的明细list
     * @throws java.lang.Exception
     */
    //private List dealWithSfgl4M(String jsjdm, List szsmList, Date sbrq) throws
    private List dealWithSfgl (String jsjdm, List szsmList, Date sbrq)
        throws
        Exception
    {
        //添加处理后数据的明细list
        List mxList = new ArrayList();

        try
        {
            //得到征期类型为月的税款所属日期
            Map semiY = Skssrq.monthSkssrq(sbrq);
            Date skssjsrq = (Date) semiY.get(Skssrq.SKSSJSRQ);
            Date skssksrq = (Date) semiY.get(Skssrq.SKSSKSRQ);
            /**
             * 通过车房土、定期定额和附加税合一的接口得到相关数据
             */

            Map cdfMap = this.getCDFSet(jsjdm, sbrq, skssksrq, skssjsrq);
            CDF = cdfMap;
            //定期定额信息
            Map dqdeInfo = this.getDqdeMap( (List) cdfMap.get(Constant.
                SFGL_SB_DQDE));
            //营业税附加税信息
            Map fjsInfo = this.getFjsMap( (List) cdfMap.get(Constant.
                SFGL_SB_FJS));
            //根据税费接口得到车房土信息
            Map cftInfo = this.getCftMap( (List) cdfMap.get(Constant.
                SFGL_SB_CFT));
            mxList = this.creatMxList(szsmList);
            //根据个税定额接口得到所有个人的定额合计
            List gsList = (List) cdfMap.get(Constant.SFGL_SB_GSDE);
            //处理附加税，定期定额，车房土情况
            for (int i = 0; i < mxList.size(); i++)
            {
                SbjkmxDis mxData = (SbjkmxDis) mxList.get(i);
                //当征期类型为月的时候根据税费接口处理相应的数据
                // 处理税费管理中的附加税税率
                String szsmdm = mxData.getSzsmdm();
                if (mxData.getSffjs() != null && mxData.getSffjs().equals("2") &&
                    fjsInfo != null)
                {
                    //税目是营业税附加税，并且由税费取得核定信息
                    Tszslmx tszslmx = (Tszslmx) fjsInfo.get(szsmdm.substring(0,
                        2));
                    if (tszslmx != null)
                    {
                        mxData.setSl(tszslmx.getSl());
                    }
                }
                // 处理税费管理中的定期定额
                if (dqdeInfo != null)
                {
                    Dqdedlmx1 dqde = (Dqdedlmx1) dqdeInfo.get(szsmdm);
                    if (dqde != null)
                    {
                        //
                        if (dqde.getZsfsdm().equals(CodeConstant.ZHSB_ZSFS_DE))
                        {
                            //征收方式为定额
                            //在与申报的接口中，“税基认定”对应申报的“计税金额”，“应纳税额”对应申报的“实际缴税额”。
                            //mxData.setSjse(dqde.getYnsrd());
                            mxData.setSjse(dqde.getYnsrd());
                            mxData.setJsje(dqde.getSjrd());
                            mxData.setFromDqde(true);

//                //征收方式为定额
//                mxData.setSjse(dqde.getYnsrd());
//                mxData.setJsje(dqde.getYnsrd());
//                mxData.setFromDqde(true);
                        }
                        else if (dqde.getZsfsdm().equals(CodeConstant.
                            ZHSB_ZSFS_DL))
                        {
                            //征收方式为定律
                            mxData.setSl(dqde.getZsl());
                        }

                    }
                }
                // 处理车房土
                if (cftInfo != null)
                {
                    Cftsyhd cft = (Cftsyhd) cftInfo.get(szsmdm);
                    //实缴金额
                    if (cft != null)
                    {
                        BigDecimal money = cft.getSjje();
                        //
                        mxData.setSjse(money);
                        mxData.setJsje(money);
                        mxData.setKssl(cft.getKssl());
                    }
                }
                //处理工薪所得050130
                if (szsmdm.equals(CodeConstant.ZRR_GXSD_SZSMDM))
                {
                    //个税定额合计
                    BigDecimal hj = new BigDecimal(0);
                    //合计所有的个税定额
                    for (int ig = 0; ig < gsList.size(); ig++)
                    {
                        Grtszygsde temp = (Grtszygsde) gsList.get(ig);
                        hj = hj.add(temp.getHdske());
                    }

                    if (hj.longValue() != 0)
                    {
                        mxData.setSjse(hj);
                    }
                }

            }
            return mxList;
        }
        catch (BaseException ex)
        {
            throw ExceptionUtil.getBaseException(ex, "处理税种税目列表失败");
        }

    }

    /**
     * 根据税费接口取得定期定额核定，通过定期定额list得到定期定额map
     * @param dqdeInfo 定期定额的数据list
     * @return 定期定额的map
     * @throws BaseException
     */
    private Map getDqdeMap (List dqdeInfo)
        throws BaseException
    {
        Map dqdeMap = new HashMap();
        if (dqdeInfo != null)
        {
            for (int i = 0; i < dqdeInfo.size(); i++)
            {
                Dqdedlmx1 dqde = (Dqdedlmx1) dqdeInfo.get(i);
                dqdeMap.put(dqde.getSzsmdm(), dqde);
            }
        }

        return dqdeMap;
    }

    /**
     * 根据税费接口得到附加税核定
     * @param fjsInfo 附加税数据的list
     * @return 附加税的数据map
     * @throws BaseException
     */
    private Map getFjsMap (List fjsInfo)
        throws BaseException
    {
        Map fjsMap = new HashMap();
        for (int i = 0; i < fjsInfo.size(); i++)
        {
            Tszslmx tszslmx = (Tszslmx) fjsInfo.get(i);
            fjsMap.put(tszslmx.getSzsmdm(), tszslmx);
        }

        return fjsMap;
    }

    /**
     * 根据税费接口得到车房土信息<br>
     * 因为现有税费接口只可以通过计算机代码和税种税目单一确定<br>
     * 所以应该为车房土添加新的税费接口，通过计算机代码得到所有的车房土核定
     * @param jsjdm 计算机代码
     * @param date 申报日期
     * @param qsrq 起始日期
     * @param jzrq 截止日期
     * @return 车房土数据map
     */
    private Map getCDFSet (String jsjdm, Date date, Date qsrq, Date jzrq)
    {

        Map map = new HashMap();
        try
        {

            com.ttsoft.bjtax.sfgl.proxy.ServiceProxy proxy =
                new com.ttsoft.bjtax.sfgl.proxy.ServiceProxy();
            Debug.out("qsrq=" + qsrq);
            map = proxy.getCDFSet(jsjdm, date, qsrq, jzrq);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
        return map;
    }

    /**
     * 根据税费接口得到车房土List得到map<br>
     * 因为现有税费接口只可以通过计算机代码和税种税目单一确定<br>
     * 所以应该为车房土添加新的税费接口，通过计算机代码得到所有的车房土核定
     * <br>该方法通过计算机代码得到所有的车房土数据
     * @param cftList 车房土数据的list
     * @return 返回车房土的map
     */
    private Map getCftMap (List cftList)
    {

        Map cftMap = new HashMap();
        try
        {
            for (int i = 0; i < cftList.size(); i++)
            {
                Cftsyhd temp = (Cftsyhd) cftList.get(i);
                cftMap.put(temp.getSzsmdm(), temp);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
        return cftMap;
    }

    /**
     * 根据明细列表中的税种税目、申报日期、添加税款所属日期
     * @param jsjdm 计算机代码
     * @param mxList 明细列表数据list
     * @param rq 申报日期
     * @param con 数据库连接
     * @return 明细列表的list
     * @throws BaseException
     */
    private List addSkssrqByMap (String jsjdm, List mxList, Date rq,
                                 Connection con)
        throws BaseException
    {
        List ret = new ArrayList();
        try
        {
            //通过登记接口得到纳税人基本信息
            SWDJJBSJ jbsj = InterfaceDj.getJBSJ(jsjdm);

            //得到该征期内的所有税种税目税款所属日期
            Map zqrlMap = this.getSksssqMap(jbsj.getDjzclxdm(), new Date(), con);
            //通过税费接口获得应纳税计额
            //Map ynsjeMap = InterfaceSf4Sb.getYnsje(jsjdm,rq);
            for (int i = 0; i < mxList.size(); i++)
            {

                //为每个明细添加税款所属日期
                SbjkmxDis mxData = (SbjkmxDis) mxList.get(i);
                //根据税款所属日期map为明细数据添加税款所属日期
                Zqrl zqrl = (Zqrl) zqrlMap.get(mxData.getSzsmdm());
                if (zqrl != null)
                {
                    mxData.setSkssjsrq(zqrl.getZqssrqz());
                    mxData.setSkssksrq(zqrl.getZqssrqq());
                }
                else
                {
//          Map map = (Map) Skssrq.getSksssq(jsjdm, mxData.getSzsmdm(),
//                                           CodeConstant.SKLXDM_ZCJK,
//                                           mxData.getZqlxdm(),
//                                           rq, mxData.getSjse(),
//                                           mxData.getKssl(),
//                                           mxData.getJsje(),
//                                           ynsjeMap);
                    Map map = (Map) Skssrq.getSksssq(jsjdm, mxData.getSzsmdm(),
                        CodeConstant.SKLXDM_ZCJK,
                        mxData.getZqlxdm(),
                        rq, mxData.getSjse(),
                        mxData.getKssl(), mxData.getJsje(),
                        mxData.getJsje());
//          Map map = (Map)Skssrq.getSksssq(mxData.getSzsmdm(),
//                                CodeConstant.SKLXDM_ZCJK,
//                                mxData.getZqlxdm(),
//                                new Date());
                    mxData.setSkssjsrq( (Timestamp) map.get("SKSSJSRQ")); //开始日期
                    mxData.setSkssksrq( (Timestamp) map.get("SKSSKSRQ")); //结束日期
                }
                //根据缴纳次数修改车房土限缴日期
                this.modifyCft(mxData, rq, (Map) CDF.get("JNCS"));

                ret.add(mxData);
            }
            return ret;
        }
        catch (Exception ex)
        {
            //throw ExceptionUtil.getBaseException(ex, "系统错误请与管理员联系!");
            throw new ApplicationException("获取税种税目、申报日期、添加税款所属日期出错！");
        }
    }

    /**
     * 根据明细数据列表生成js2维数组<br>
     * @param mxList 明细数据list
     * @return 明细数据的javascript二维数组
     */
    private String getMxJsArray (List mxList)
    {
        StringBuffer ret = new StringBuffer();
        for (int i = 0; i < mxList.size(); i++)
        {
            SbjkmxDis mxData = (SbjkmxDis) mxList.get(i);
            ret.append("[");
            ret.append("\"" + mxData.getSzsmdm() + "\",");
            ret.append("\"" + mxData.getSzmc() + "\",");
            ret.append("\"" + mxData.getSzsmmc() + "\",");
            if (mxData.getSkssksrq() != null)
            {
                ret.append("\"" + SfDateUtil.getDate(mxData.getSkssksrq())
                           + "\",");
            }
            else
            {
                ret.append("\"" + mxData.getSkssksrq() + "\",");
            }
            if (mxData.getSkssjsrq() != null)
            {
                ret.append("\"" + SfDateUtil.getDate(mxData.getSkssjsrq())
                           + "\",");
            }
            else
            {
                ret.append("\"" + mxData.getSkssjsrq() + "\",");
            }
            ret.append("\"" + mxData.getKssl() + "\",");
            ret.append("\"" + mxData.getJsje() + "\",");
            ret.append("\"" + mxData.getSjse() + "\",");
            ret.append("\"" + mxData.getSzdm() + "\",");
            ret.append("\"" + mxData.getSffjs() + "\",");
            ret.append("\"" + mxData.getSzsmdm() + "\",");
            ret.append("\"" + mxData.getAsljbs() + "\",");
            ret.append("\"" + mxData.getSl() + "\"");
            ret.append("],");
        }
        if (ret.length() > 0)
        {
            //如果有数据，则删除最后添加的逗号
            ret.delete(ret.length() - 1, ret.length());
        }
        else
        {
            return "var szsmlist = new Array();";
        }
        ret.append("];");
        ret = SfStringUtils.replaceAll(ret, "null", "");
//    String clean = SfStringUtil.replaceString(ret.toString(),"null","");
//    return "var szsmlist = [" + clean;
        return "var szsmlist = [" + ret.toString();

    }

    /**
     * 根据税费管理认定情况处理税种税目数据<br>
     * 根据定期定额数据和营业税附加税数据处理税种税目list
     * @param szsmList 税种税目数据list
     * @return 处理后的根据税种税目对应的明细list
     */
    private List creatMxList (List szsmList)
    {
        //添加处理后数据的明细list
        List mxList = new ArrayList();
        String szdm = "";
        String szmc = "";
        for (int i = 0; i < szsmList.size(); i++)
        {
            //前台显示用申报缴款明细
            SbjkmxDis temp = new SbjkmxDis();
            Szsm szsm = (Szsm) szsmList.get(i);
            //因为按照税种税目代码排序，所以可以先取的税种

            if (szsm.getSzsmdm().length() == 2)
            {
                //长度为2按照税种处理
                szdm = szsm.getSzsmdm();
                szmc = szsm.getSzsmmc();
            }
            //设置明细的税种税目
            if (szsm.getSzsmdm().length() == 6)
            {
                //长度为6为税目
                temp.setSzsmdm(szsm.getSzsmdm());
                temp.setSzsmmc(szsm.getSzsmmc());
                //设置是否附加税标示
                temp.setSffjs(szsm.getSffjs());
                //设置税种
                temp.setSzdm(szdm);
                temp.setSzmc(szmc);
                temp.setAsljbs(szsm.getAsljbs());
                temp.setSl(szsm.getSl());
                temp.setZqlxdm(szsm.getZqlxdm());
                mxList.add(temp);
            }

        }
        return mxList;
    }

    /**
     * 根据征期日历得到所有税种税目的税款所属时期<br>
     * 当前日期的月等于征期起始或截止日期的月
     * 征期截止日期＋1天=限缴日期<br>
     * @param djzclxdm 登记注册类型代码
     * @param rq 申报日期
     * @param conn 数据库连接
     * @return 税款所属开始 结束时间，限缴日期的Map
     * @throws java.lang.Exception
     */
    private Map getSksssqMap (String djzclxdm, Date rq, Connection conn)
        throws
        Exception
    {
        List ret = new ArrayList();
        //Connection conn = null;
        try
        {
            //得到连接
            //conn = SfDBResource.getConnection();

            String dateStr = TinyTools.Date2String(rq,"yyyyMM");
            Vector criteria = new Vector(); //查询条件
            //criteria.add("szsmdm = '" + szsmdm + "'");
            criteria.add("djzclxdm='" + djzclxdm + "'");
            criteria.add("ZQQSRQ<=to_date('" + dateStr + "','yyyyMM')");
            criteria.add("ZQZZRQ>=to_date('" + dateStr + "','yyyyMM')");

            SfDBAccess db = new SfDBAccess(conn);
            ret = db.query(new Zqrl().getClass(), criteria);
            Map zqrlMap = new HashMap();
            for (int i = 0; i < ret.size(); i++)
            {
                Zqrl zqrl = (Zqrl) ret.get(i);
                if (zqrl != null)
                {
                    zqrlMap.put(zqrl.getSzsmdm(), zqrl);
                }
                else
                {

                }
            }
            return zqrlMap;
        }
        catch (Exception ex)
        {
            //throw ExceptionUtil.getBaseException(ex, "查询征期日历失败!");
            throw new ApplicationException("查询征期日历失败！");
        }
        finally
        {
            //释放连接
            //SfDBResource.freeConnection(conn);
        }

    }

    /**
     * 根据缴纳次数，处理车房土税款所属日期
     * @param mxData 申报缴款明细表的数据对象
     * @param sbrq 申报日期
     * @param jncs 缴纳次数
     */
    private void modifyCft (SbjkmxDis mxData, Date sbrq, Map jncs)
    {

        String szdm = mxData.getSzsmdm().substring(0, 2);
        int ijncs = 0;
        if (jncs != null && (String) jncs.get(szdm) != null)
        {
            ijncs = Integer.parseInt( (String) jncs.get(szdm));
            /**
             * 城镇土地使用税2007年10月征期征收限制
             * 因2007年上半年未征收土地税,下半年征期须征收全年税款
             * 故特作如下控制:
             * 2007年城镇土地使用税缴纳次数统一为全年征收,即缴纳次数为0
             * 2007.10征期过后,按税费认定缴纳次数征收
             * 
             * 王志民 2007-8-15日备注
             */
            if(szdm.equals("15")&&TinyTools.Date2String(sbrq,"yyyyMMdd").substring(0,4).equals("2007")){
            	ijncs=0;
            }
        }
        if (Skssrq.SZDM_CFT.indexOf(szdm) > 0)
        {
            //  属于车房土税种
            Map temp = Skssrq.getCftSkssrq(sbrq, ijncs);
            mxData.setSkssksrq( (Timestamp) temp.get(Skssrq.SKSSKSRQ));
            mxData.setSkssjsrq( (Timestamp) temp.get(Skssrq.SKSSJSRQ));
        }
    }

    /**
     * 获得税种税目代码提示数组名称和值  ChangeBy Tujunbing 2012-08-30
     */
    private String getSzsmtskzsJs(){
    	StringBuffer returnJsStr = new StringBuffer();
    	String szsmdm_ts_name = "var szsmdm_ts_name = ['szsmdm','nowdate','tsny','tsksrq','tsjsrq','zxksrq','zxjsrq'];\n";
    	StringBuffer  szsmdm_ts_value = new StringBuffer();  	
        //定义数据库连接
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            //获得数据库连接
            conn = SfDBResource.getConnection();
            SfDBAccess da = new SfDBAccess(conn);           
            //获取--税种税目提示控制设置表中的数据
            String querySQL ="select szsmdm,to_char(sysdate, 'yyyymmdd') nowdate,tsny,to_char(tsksrq,'yyyymmdd') tsksrq,to_char(tsjsrq,'yyyymmdd') tsjsrq,to_char(zxksrq,'yyyymmdd') zxksrq,to_char(zxjsrq,'yyyymmdd') zxjsrq from dmdb.sb_dm_szsmtskzszb where zxbs <> '1'";
            ps = conn.prepareStatement(querySQL);
            rs = ps.executeQuery();
            
            szsmdm_ts_value.append("var szsmdm_ts_value = [");
            while(rs.next()){
            	szsmdm_ts_value.append("[");
            	szsmdm_ts_value.append("'"+rs.getString("szsmdm")+"',");            	
            	szsmdm_ts_value.append("'"+rs.getString("tsny")+"',");
            	szsmdm_ts_value.append("'"+rs.getString("nowdate")+"',");
            	/*
            	 * if(rs.getString("tsny") == null || "null".equals(rs.getString("tsny")) || "".equals(rs.getString("tsny"))){
            		szsmdm_ts_value.append("'',");
            	}else{
            		szsmdm_ts_value.append("'"+rs.getString("tsny")+"',");
            	}*/           	
            	if(rs.getString("tsksrq") == null || "null".equals(rs.getString("tsksrq")) || "".equals(rs.getString("tsksrq"))){
            		szsmdm_ts_value.append("'',");
            	}else{
            		szsmdm_ts_value.append("'"+rs.getString("tsksrq")+"',");
            	}
            	if(rs.getString("tsjsrq") == null || "null".equals(rs.getString("tsjsrq")) || "".equals(rs.getString("tsjsrq"))){
            		szsmdm_ts_value.append("'',");
            	}else{
            		szsmdm_ts_value.append("'"+rs.getString("tsjsrq")+"',");
            	}
            	if(rs.getString("zxksrq") == null || "null".equals(rs.getString("zxksrq")) || "".equals(rs.getString("zxksrq"))){
            		szsmdm_ts_value.append("'',");
            	}else{
            		szsmdm_ts_value.append("'"+rs.getString("zxksrq")+"',");
            	}
            	if(rs.getString("zxjsrq") == null || "null".equals(rs.getString("zxjsrq")) || "".equals(rs.getString("zxjsrq"))){
            		szsmdm_ts_value.append("''");
            	}else{
            		szsmdm_ts_value.append("'"+rs.getString("zxjsrq")+"'");
            	}
            	szsmdm_ts_value.append("],");
            }
            
            szsmdm_ts_value.append(" ];");       	            
            //System.out.println("szsmdm_ts_value.toString()------------->"+szsmdm_ts_value.toString());           
            String repalceStr_old = ", ];";
            String repalceStr_new = "];";
            
            if(szsmdm_ts_value.toString().endsWith(repalceStr_old)){
            	int index = szsmdm_ts_value.lastIndexOf(repalceStr_old);
            	szsmdm_ts_value = szsmdm_ts_value.replace(index, szsmdm_ts_value.length(), repalceStr_new);
            }
            //System.out.println("szsmdm_ts_value.toString()------------->"+szsmdm_ts_value.toString());             
            returnJsStr.append(szsmdm_ts_name);
            returnJsStr.append(szsmdm_ts_value);           
            //System.out.println("returnJsStr.toString()------------->"+returnJsStr.toString());
            
            //释放占用资源
            rs.close();
            ps.close();
        }catch(Exception e){
        	e.printStackTrace();
        }finally{
        	SfDBResource.freeConnection(conn);
        }
    	return returnJsStr.toString();
    }      
}
//:-)