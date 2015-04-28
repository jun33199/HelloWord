/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.dsdzdk.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.dj.model.YHZH;
import com.ttsoft.bjtax.sfgl.common.db.util.BeanUtil;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.util.Debug;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.sfgl.proxy.ServiceProxy;
import com.ttsoft.bjtax.shenbao.model.domain.Dsdzdkmx;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkmx;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb;
import com.ttsoft.bjtax.shenbao.model.domain.Sdwszsbhz;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.model.client.DeclareInfor;
import com.ttsoft.bjtax.smsb.sbzl.dsdzdk.web.DsdzdkForm;
import com.ttsoft.bjtax.smsb.util.EArray;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.JksUtil;
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
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description: 三代申报</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class DsdzdkProcessor
    implements Processor
{
    public final static String SMSB_SJLY_LR = "0";

    public final static String SMSB_SJLY_DR = "1";

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

        Debug.out("DO ACTION =" + vo.getAction());

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
            case CodeConstant.SMSB_INPUTACTION: //录入
                result = doInput(vo);
                break;
            case CodeConstant.SMSB_SAVEACTION:
                result = doSave(vo);
                break;
            case CodeConstant.SMSB_DELETEACTION:
                result = doDelete(vo);
                break;
                //case CodeConstant.SMSB_UPLOADACTION:
                //  result = doUpload(vo);
                //  break;
            case CodeConstant.SMSB_HZSBJKDACTION:
                result = doHzsbjkd(vo);
                break;
            case CodeConstant.SMSB_CXHZSBJKDACTION:
                result = doCxhzsbjkd(vo);
                break;
            default:
                throw new UnsupportedOperationException(
                    "用户执行了系统不支持的操作！");
        }
        return result;
    }

    /**
     * doShow初始化对象页面信息要素
     * @param vo 业务参数
     * @return   数据对象
     * @throws BaseException 当其他情况都会抛出异常信息
     */

    public Object doShow (VOPackage vo)
        throws BaseException
    {

        //得到Action传递过来ActionForm对象
        DsdzdkForm sdForm = (DsdzdkForm) vo.getData();

        // 初始化ACTIONFORM
        initForm(sdForm);

        return sdForm;
    }

    /**
     * doQuery    用于返回页面索要查询的详尽信息
     * @param     vo 业务参数
     * @return    数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
     * @throws BaseException    当其他情况都会抛出异常信息
     *
     */

    public Object doQuery (VOPackage vo)
        throws BaseException
    {

        //得到Action传递过来ActionForm对象
        DsdzdkForm sdForm = (DsdzdkForm) vo.getData();
        sdForm.setNsrmc(""); // 纳税人名称
        sdForm.setQylxdh(""); // 注册地址联系电话

        if (sdForm.getSbrq() == null)
        {
            // 初始化ACTIONFORM
            initForm(sdForm);
        }
        UserData ud = (UserData) vo.getUserData();
        SWDJJBSJ djsj = null;
        try
        {
            // 获得企业登记基本信息
            /* start added by huxiaofeng 2005.8.16*/
            //djsj = InterfaceDj.getJBSJ(sdForm.getJsjdm(), ud);
            djsj = InterfaceDj.getJBSJ_New(sdForm.getJsjdm(), ud);
            sdForm.setNsrzt(djsj.getNsrzt());
            /* end added by huxiaofeng 2005.8.16*/

        }
        catch (Exception ex1)
        {
            throw ExceptionUtil.getBaseException(ex1);
        }

        ServiceProxy proxy = new ServiceProxy();
        boolean zg = proxy.getSdzg(sdForm.getJsjdm(),
                                   SfDateUtil.getDate(sdForm.getSbrq()));
        if (zg == false)
        {
            throw new ApplicationException("此计算机代码不具备代售代征代扣代鉴资格！");
        }
        sdForm.setQxdm(InterfaceDj.getQxdm(ud));
        sdForm.setNsrmc(djsj.getNsrmc()); // 纳税人名称
        sdForm.setQylxdh(djsj.getJydzlxdm()); // 注册地址联系电话
        sdForm.setSwjgzzjgdm(djsj.getSwjgzzjgdm()); // 税务机关组织机构代码
        //纳税人状态
        sdForm.setNsrzt(djsj.getNsrzt());
        return sdForm;
    }

    /**
     * 录入处理，取得纳税人基本信息，初始化页面数据
     * @param     vo 业务参数
     * @return    数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
     * @throws BaseException    当其他情况都会抛出异常信息
     */
    public Object doInput (VOPackage vo)
        throws BaseException
    {
        //得到Action传递过来ActionForm对象
        DsdzdkForm sdForm = (DsdzdkForm) vo.getData();
        UserData ud = (UserData) vo.getUserData();
        SWDJJBSJ djsj = null;
        try
        {
            // 获得企业登记基本信息
            /* start added by huxiaofeng 2005.8.16*/
            //djsj = InterfaceDj.getJBSJ(sdForm.getJsjdm(), ud);
            djsj = InterfaceDj.getJBSJ_New(sdForm.getJsjdm(), ud);
            sdForm.setNsrzt(djsj.getNsrzt());
            /* end added by huxiaofeng 2005.8.16*/

        }
        catch (Exception ex1)
        {
            throw ExceptionUtil.getBaseException(ex1);
        }

        sdForm.setNsrmc(djsj.getNsrmc()); // 纳税人名称
        sdForm.setQylxdh(djsj.getJydzlxdm()); // 注册地址联系电话
        sdForm.setSjly(SMSB_SJLY_LR);
        sdForm.setSwjgzzjgdm(djsj.getSwjgzzjgdm()); // 税务机关组织机构代码
        sdForm.setQxdm(InterfaceDj.getQxdm(ud));

        EArray jsArray = new EArray();
        String tempJsStr = "";
        tempJsStr += jsArray.getArrayByCode("szsmlist", "DSDZDK_SZSM");
        tempJsStr +=
            jsArray.getMsgsByCode("szsmdm", "DSDZDK_SZSM_ALL", new ArrayList());
        sdForm.setScriptStr(tempJsStr);
        sdForm.setPzzl(CodeConstant.SMSB_PZZLDM);

        return sdForm;
    }

    /**
     * doHzsbjkd    用于返回页面索要查询的详尽信息
     * @param     vo 业务参数
     * @return    数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
     * @throws BaseException    当其他情况都会抛出异常信息
     *
     */

    public Object doHzsbjkd (VOPackage vo)
        throws BaseException
    {

        DsdzdkForm sdForm = (DsdzdkForm) vo.getData();
        UserData ud = (UserData) vo.getUserData();
        SWDJJBSJ djsj = null;
        try
        {
            // 获得企业登记基本信息
            /* start added by huxiaofeng 2005.8.16*/
            //djsj = InterfaceDj.getJBSJ(sdForm.getJsjdm(), ud);
            djsj = InterfaceDj.getJBSJ_New(sdForm.getJsjdm(), ud);
            sdForm.setNsrzt(djsj.getNsrzt());
            /* end added by huxiaofeng 2005.8.16*/

        }
        catch (Exception ex1)
        {
            throw ExceptionUtil.getBaseException(ex1);
        }

        sdForm.setQxdm(InterfaceDj.getQxdm(ud));
        sdForm.setNsrmc(djsj.getNsrmc()); // 纳税人名称
        sdForm.setQylxdh(djsj.getZcdzlxdh()); // 注册地址联系电话

        // 加载可撤销申报汇总列表和缴款列表数据
        loadDataList(sdForm);

        return sdForm;
    }

    /**
     * 撤销汇总申报缴款单处理
     * @param     vo 业务参数
     * @return    数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
     * @throws BaseException    当其他情况都会抛出异常信息
     */


    public Object doCxhzsbjkd (VOPackage vo)
        throws BaseException
    {

        DsdzdkForm sdForm = (DsdzdkForm) vo.getData();
        UserData ud = (UserData) vo.getUserData();
        SWDJJBSJ djsj = null;
        try
        {
            // 获得企业登记基本信息
            /* start added by huxiaofeng 2005.8.16*/
            //djsj = InterfaceDj.getJBSJ(sdForm.getJsjdm(), ud);
            djsj = InterfaceDj.getJBSJ_New(sdForm.getJsjdm(), ud);
            sdForm.setNsrzt(djsj.getNsrzt());
            /* end added by huxiaofeng 2005.8.16*/

        }
        catch (Exception ex1)
        {
            throw ExceptionUtil.getBaseException(ex1);
        }
        sdForm.setNsrmc(djsj.getNsrmc()); // 纳税人名称
        sdForm.setQylxdh(djsj.getZcdzlxdh()); // 注册地址联系电话
        sdForm.setQxdm(InterfaceDj.getQxdm(ud));

        Connection conn = null;
        SfDBAccess dbAgent = null;

        try
        {
            conn = SfDBResource.getConnection();
            dbAgent = new SfDBAccess(conn);

            //删除申报缴款数据
            List pzList = getJkpzList(dbAgent, sdForm.getQxdm(),
                                      sdForm.getJsjdm(),
                                      sdForm.getSbhzdh());
            Iterator orItems = pzList.iterator();
            while (orItems.hasNext())
            {
                Sdwszsbhz orItem = (Sdwszsbhz) orItems.next();

                String strWhere = " QXDM='" + sdForm.getQxdm() + "'" +
                                  " AND JSJDM='" + orItem.getJsjdm() + "'" +
                                  " AND JKPZH='" + orItem.getJkpzh() + "'";
                //删除明细
                dbAgent.delete(strWhere, new Sbjkmx());
                //删除主表
                dbAgent.delete(strWhere, new Sbjkzb());
            }

            String strWhere = " QXDM='" + sdForm.getQxdm() + "'" +
                              " AND JSJDM='" + sdForm.getJsjdm() + "'" +
                              " AND SBHZDH='" + sdForm.getSbhzdh() + "'";
            //删除三代明细数据
            dbAgent.delete(strWhere, new Dsdzdkmx());

            //删除三代汇总数据
            dbAgent.delete(strWhere, new Sdwszsbhz());

            // 重新加载可撤销申报汇总列表和缴款列表数据
            sdForm.setSbhzdh("");
            loadDataList(sdForm);
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
        return sdForm;
    }

    /**
     * doDelete  用于删除页面提交的详尽处理信息
     * @param    vo 业务参数
     * @return   数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]
     * @throws BaseException 当其他情况都会抛出异常信息
     */

    public Object doDelete (VOPackage vo)
        throws BaseException
    {

        DsdzdkForm sdForm = (DsdzdkForm) vo.getData();

        Connection conn = null;
        SfDBAccess dbAgent = null;

        try
        {
            conn = SfDBResource.getConnection();
            dbAgent = new SfDBAccess(conn);

            //删除申报缴款数据
            List pzList = getJkpzList(dbAgent, sdForm.getQxdm(),
                                      sdForm.getJsjdm(),
                                      sdForm.getSbhzdh());
            Iterator orItems = pzList.iterator();
            while (orItems.hasNext())
            {
                Sdwszsbhz orItem = (Sdwszsbhz) orItems.next();

                String strWhere = " QXDM='" + sdForm.getQxdm() + "'" +
                                  " AND JSJDM='" + orItem.getJsjdm() + "'" +
                                  " AND JKPZH='" + orItem.getJkpzh() + "'";
                //删除明细
                dbAgent.delete(strWhere, new Sbjkmx());
                //删除主表
                dbAgent.delete(strWhere, new Sbjkzb());
            }

            String strWhere = " QXDM='" + sdForm.getQxdm() + "'" +
                              " AND JSJDM='" + sdForm.getJsjdm() + "'" +
                              " AND SBHZDH='" + sdForm.getSbhzdh() + "'";
            //删除三代明细数据
            dbAgent.delete(strWhere, new Dsdzdkmx());

            //删除三代汇总数据
            dbAgent.delete(strWhere, new Sdwszsbhz());

            // 清除撤销申报汇总列表和缴款列表数据
            sdForm.setSbhzdh("");
            sdForm.getHzdDataList().clear();
            sdForm.getJkpzDataList().clear();
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
        return sdForm;
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
        DsdzdkForm sdForm = (DsdzdkForm) vo.getData();
        UserData ud = (UserData) vo.getUserData();

        //======== 获得企业登记基本信息 ========================
        SWDJJBSJ djsj = null;
        YHZH yhzh = null;
        try
        {
            /* start added by huxiaofeng 2005.8.16*/
            //djsj = InterfaceDj.getJBSJ(sdForm.getJsjdm(), ud);
            djsj = InterfaceDj.getJBSJ_New(sdForm.getJsjdm(), ud);
            /* end added by huxiaofeng 2005.8.16*/

            List zhList = InterfaceDj.getYHZH(sdForm.getJsjdm(), ud);
//Update  Start  Zhou kejing 20031211
//      if(zhList != null && zhList.size() > 0){
//         yhzh = (YHZH) zhList.get(0);
//      }
            for (int i = 0; i < zhList.size(); i++)
            {
                yhzh = (YHZH) zhList.get(i);
                if (yhzh.getJbzhbs().equals(CodeConstant.SMSB_JBZHBS))
                {
                    sdForm.setYhdm(yhzh.getYhdm()); //银行代码
                    sdForm.setYhmc(yhzh.getKhyhmc()); //银行名称
                    sdForm.setZh(yhzh.getZh()); //帐户
                    break;
                }
            }
//Update  End    Zhou kejing 20031211
        }
        catch (Exception ex1)
        {
            throw ExceptionUtil.getBaseException(ex1);
        }
        sdForm.setQxdm(InterfaceDj.getQxdm(ud));
        sdForm.setNsrmc(djsj.getNsrmc()); // 纳税人名称
        sdForm.setQylxdh(djsj.getJydzlxdm()); // 注册地址联系电话
        sdForm.setDjzclxdm(djsj.getDjzclxdm()); // 登记注册类型代码
        sdForm.setSwjgzzjgdm(djsj.getSwjgzzjgdm()); //税务机关组织机构代码
        sdForm.setGjbzhydm(djsj.getGjbzhydm()); //国家标准行业代码
        sdForm.setLsgxdm(djsj.getLsgxdm()); //隶属关系代码
        sdForm.setHzrq(SfDateUtil.getDate());
        //sdForm.setSjly(SMSB_SJLY_DR);
        sdForm.setClbjdm(CodeConstant.CLBJDM_YSB);
        sdForm.setFsdm(CodeConstant.FSDM_SMSB); // 上门申报方式
        sdForm.setNd(getSbnd(sdForm.getSbrq()));
        sdForm.setLrrq(TinyTools.Date2String(new Date(), CodeConstant.DATETYPE));
        if (sdForm.getCjrq() == null || "".equals(sdForm.getCjrq()))
        {
            sdForm.setCjrq(TinyTools.Date2String(new Date(),
                                                 CodeConstant.DATETYPE));
        }

//Delete  Start  Zhou kejing 20031211
//    if (yhzh != null) {
//      sdForm.setYhdm(yhzh.getYhdm()); //银行代码
//      sdForm.setYhmc(yhzh.getKhyhmc()); //银行名称
//      sdForm.setZh(yhzh.getZh()); // 账号
//    }
//Delete  End    Zhou kejing 20031211

        //生成申报编号
        //sdForm.setSbbh(JksUtil.getSbbh(sdForm.getJsjdm()));

        Connection conn = null;
        SfDBAccess dbAgent = null;

        try
        {
            conn = SfDBResource.getConnection();
            dbAgent = new SfDBAccess(conn);
            List jkpzInfoList;

            //生成汇总单号
            JksUtil jks = new JksUtil();
            sdForm.setSbhzdh(jks.getSequenceOfSbhzd(conn));

            //======== 按税种、税目对三代明细排序 ==================
            HashMap szsmMxMap = groupMxBySzsm(sdForm);

            // 生成缴款凭证
            jkpzInfoList = (List) createSbjkpz(sdForm, szsmMxMap);

            // 取得申报编号
            DeclareInfor info = (DeclareInfor) jkpzInfoList.get(0);
            Sbjkzb zbInfo = info.getSbjkzb();
            sdForm.setSbbh(zbInfo.getSbbh());

            // 生成三代汇总数据
            createSdwszsbhz(sdForm, jkpzInfoList, dbAgent);

            // 生成三代明细数据
            createDsdzdkmx(sdForm, szsmMxMap, dbAgent);

            //装载汇总单
            sdForm.getHzdDataList().clear();
            sdForm.getHzdDataList().add(
                loadSbhz(dbAgent, sdForm.getQxdm(), sdForm.getJsjdm(),
                         sdForm.getSbhzdh()));

            //装载汇总凭证
            sdForm.getJkpzDataList().clear();
            sdForm.getJkpzDataList().addAll(
                loadJkpz(dbAgent, sdForm.getQxdm(), sdForm.getJsjdm(),
                         sdForm.getSbhzdh()));

            //清楚明细列表
            sdForm.getMxDataList().clear();
        }
        catch (Exception ex)
        {
            //抛出异常
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
        finally
        {
            //释放数据库连接
            SfDBResource.freeConnection(conn);
        }
        return sdForm;
    }

    /**
     * 保存缴款明细信息，返回缴款汇总信息
     * @param sdForm 当前ActionForm对象
     * @param sdmxMap 按税种、税目排序的三代明细数据
     * @return 生成的缴款凭证列表
     * @throws BaseException
     */
    private Object createSbjkpz (DsdzdkForm sdForm, HashMap sdmxMap)
        throws
        BaseException
    {

        // 缴款明细信息列表
        ArrayList jkmxList = new ArrayList();

        Timestamp ts_cjrq = new Timestamp(TinyTools.stringToDate(sdForm.getCjrq(),
            CodeConstant.DATETYPE).getTime());
        Timestamp ts_lrrq = new Timestamp(TinyTools.stringToDate(sdForm.getLrrq(),
            CodeConstant.DATETYPE).getTime());

        try
        {
            Iterator szEntrys = sdmxMap.entrySet().iterator();
            while (szEntrys.hasNext())
            {
                Map.Entry szEntry = (Map.Entry) szEntrys.next();
                // 税种代码
                String szdm = (String) szEntry.getKey();
                // 税种所属税目
                Iterator smEntrys = ( (HashMap) szEntry.getValue()).entrySet().
                                    iterator();
                while (smEntrys.hasNext())
                {

                    //=========== 对同一税种、税目的名细数据计算合计 =============
                    // 记税金额合计
                    BigDecimal jsjeTotal = new BigDecimal(0.00);
                    // 实缴税额合计
                    BigDecimal sjseTotal = new BigDecimal(0.00);

                    Map.Entry smEntry = (Map.Entry) smEntrys.next();
                    // 税目代码
                    String smdm = (String) smEntry.getKey();
                    // 税目所属明细信息
                    Iterator sdmxItems = ( (List) smEntry.getValue()).iterator();
                    while (sdmxItems.hasNext())
                    {
                        Dsdzdkmx item = (Dsdzdkmx) sdmxItems.next();
                        if (item.getJsje() != null)
                        {
                            jsjeTotal = jsjeTotal.add(item.getJsje());
                        }
                        if (item.getSjse() != null)
                        {
                            sjseTotal = sjseTotal.add(item.getSjse());
                        }
                    }

                    // ================= 生成缴款明细	 =======================
                    String[] jkmx_columns =
                                            {
                                            "qxdm", "nd", "jsjdm", "skssksrq",
                                            "skssjsrq", "swjgzzjgdm",
                                            "sbbh"};

                    // 把Form由colums指定的属性拷贝到缴款明细OR对象中
                    Sbjkmx orSbjkmx = new Sbjkmx();
                    BeanUtil.copyBeanToBean(jkmx_columns, sdForm, orSbjkmx);

                    orSbjkmx.setSzdm(szdm);
                    orSbjkmx.setSzsmdm(smdm);
                    orSbjkmx.setJsje(jsjeTotal);
                    orSbjkmx.setSjse(sjseTotal);
                    orSbjkmx.setCjrq(ts_cjrq);
                    orSbjkmx.setLrrq(ts_lrrq);
                    // 把缴款明细记录加到缴款列表中
                    jkmxList.add(orSbjkmx);
                }
            }

            // ================ 生成缴款主表信息 ========================
            String[] jkzb_columns =
                                    {
                                    "qxdm", "jsjdm", "djzclxdm", "fsdm",
                                    "sbbh", "gjbzhydm", "lrr", "lsgxdm", "sbrq",
                                    "nd", "swjgzzjgdm", "yhdm", "yhmc", "zh"};

            HashMap attribMap = new HashMap();
            BeanUtil.copyBeanToMap(jkzb_columns, sdForm, attribMap);
            attribMap.put("zsswjgzzjgdm", sdForm.getSwjgzzjgdm());
            attribMap.put("jydzlxdm", sdForm.getQylxdh());
            attribMap.put("sjly", CodeConstant.SMSB_SJLY_SDHZ);
            attribMap.put("sklxdm", CodeConstant.SKLXDM_SDHZ);
            Sbjkzb orSbjkzb = new Sbjkzb();
            BeanUtil.populate(orSbjkzb, attribMap);
            orSbjkzb.setCjrq(ts_cjrq);
            orSbjkzb.setLrrq(ts_lrrq);


            //调用综合申报填写缴款主表和明细
            JksUtil jks = new JksUtil();
            return jks.getJkDataSD(orSbjkzb, jkmxList, CodeConstant.PRINT_YPYS);
        }
        catch (Exception ex1)
        {
            // 系统无法生成缴款凭证
            ex1.printStackTrace();
            throw ExceptionUtil.getBaseException(ex1);
        }
    }

    /**
     * 根据三代缴款凭证生成三代汇总信息
     * @param sdForm 当前ActionForm对象
     * @param jkpzInfoList 缴款凭证列表
     * @param dbAgent 数据库存取对象
     * @throws BaseException
     */
    private void createSdwszsbhz (DsdzdkForm sdForm, List jkpzInfoList,
                                  SfDBAccess dbAgent)
        throws BaseException
    {
        Debug.out(" Creating Sdwszsbhz data................");

        Timestamp ts_cjrq = new Timestamp(TinyTools.stringToDate(sdForm.getCjrq(),
            CodeConstant.DATETYPE).getTime());
        Timestamp ts_lrrq = new Timestamp(TinyTools.stringToDate(sdForm.getLrrq(),
            CodeConstant.DATETYPE).getTime());

        try
        {
            Iterator items = jkpzInfoList.iterator();
            while (items.hasNext())
            {
                DeclareInfor item = (DeclareInfor) items.next();
                Sbjkzb orSbjkzb = item.getSbjkzb();

                HashMap attribMap = new HashMap();
                String[] hz_columns =
                                      {
                                      "qxdm", "jsjdm", "sbhzdh", "sbbh", "nd",
                                      "hzrq", "hzksrq",
                                      "hzjsrq", "clbjdm", "swjgzzjgdm", "lrr",
                                      "sjly"};
                BeanUtil.copyBeanToMap(hz_columns, sdForm, attribMap);
                attribMap.put("jkpzh", orSbjkzb.getJkpzh());
                attribMap.put("sjse", orSbjkzb.getSjje());
                // 保存汇总数据到三代汇总表
                Sdwszsbhz orSdwszsbhz = new Sdwszsbhz();
                BeanUtil.populate(orSdwszsbhz, attribMap);
                orSdwszsbhz.setCjrq(ts_cjrq);
                orSdwszsbhz.setLrrq(ts_lrrq);
                dbAgent.insert(orSdwszsbhz);
            }
        }
        catch (Exception ex1)
        {
            // 系统无法生成汇总数据
            ex1.printStackTrace();
            throw ExceptionUtil.getBaseException(ex1);
        }
    }

    /**
     * 根据三代缴款凭证生成三代汇总信息
     * @param sdForm 当前ActionForm对象
     * @param szsmMap 缴款凭证列表
     * @param dbAgent 数据库存取对象
     * @throws BaseException
     */
    private void createDsdzdkmx (DsdzdkForm sdForm, HashMap szsmMap,
                                 SfDBAccess dbAgent)
        throws BaseException
    {

        try
        {
            // 税种项目
            Iterator szItems = szsmMap.values().iterator();
            while (szItems.hasNext())
            {
                //税目项目
                Iterator smItems = ( (HashMap) szItems.next()).values().
                                   iterator();
                while (smItems.hasNext())
                {
                    //同一税种、税目下的所有明细记录
                    Iterator mxRecords = ( (List) smItems.next()).iterator();
                    while (mxRecords.hasNext())
                    {
                        Dsdzdkmx mxRecord = (Dsdzdkmx) mxRecords.next();
                        mxRecord.setSbhzdh(sdForm.getSbhzdh());
                        dbAgent.insert(mxRecord);
                    }
                }
            }
        }
        catch (Exception ex1)
        {
            // 系统无法保存明细数据，可能是你的数据不完整或是你重复导入同一数据
            ex1.printStackTrace();
            String errMsg = ex1.getMessage();
            if (errMsg.indexOf("纪录已经存在") > 0)
            {
                throw new ApplicationException("该计算机的税种税目已经汇总过了！");
            }
            else
            {
                throw ExceptionUtil.getBaseException(ex1);
            }
        }
    }

    /**
     * 根据税种、税目对导入数据分类
     * @param sdForm 三代明细OR对象列表
     * @return HashMap 按税种、税目分组的OR对象列表
     * @throws BaseException
     */
    private HashMap groupMxBySzsm (DsdzdkForm sdForm)
        throws BaseException
    {

        Timestamp ts_cjrq = new Timestamp(TinyTools.stringToDate(sdForm.getCjrq(),
            CodeConstant.DATETYPE).getTime());
        Timestamp ts_lrrq = new Timestamp(TinyTools.stringToDate(sdForm.getLrrq(),
            CodeConstant.DATETYPE).getTime());

        // 三代明细公用属性列
        String[] mx_columns =
                              {
                              "qxdm", "jsjdm", "qylxdh", "nd", "sjly",
                              "fsdm", "skssksrq", "skssjsrq", "swjgzzjgdm",
                              "lrr"};

        // 按税种存放的三代明细OR对象列表
        HashMap szMap = new HashMap();
        try
        {
            Iterator mxItems = sdForm.getMxDataList().iterator();
            while (mxItems.hasNext())
            {
                HashMap attribMap = (HashMap) mxItems.next();
                BeanUtil.copyBeanToMap(mx_columns, sdForm, attribMap);

                Dsdzdkmx mxItem = new Dsdzdkmx();
                BeanUtil.populate(mxItem, attribMap);
                mxItem.setCjrq(ts_cjrq);
                mxItem.setLrrq(ts_lrrq);

                if (szMap.containsKey(mxItem.getSzdm()))
                {
                    // 按税目存放的三代明细OR对象列表
                    HashMap smMap = (HashMap) szMap.get(mxItem.getSzdm());
                    if (smMap.containsKey(mxItem.getSzsmdm()))
                    {
                        List mxList = (List) smMap.get(mxItem.getSzsmdm());
                        mxList.add(mxItem);
                    }
                    else
                    {
                        ArrayList mxList = new ArrayList();
                        mxList.add(mxItem);
                        smMap.put(mxItem.getSzsmdm(), mxList);
                    }
                }
                else
                {
                    ArrayList mxList = new ArrayList();
                    mxList.add(mxItem);

                    HashMap smMap = new HashMap();
                    smMap.put(mxItem.getSzsmdm(), mxList);

                    szMap.put(mxItem.getSzdm(), smMap);
                }
            }
        }
        catch (Exception ex1)
        {
            // 系统无法对明细数据排序保存明细数据，可能是你的数据不完整
            ex1.printStackTrace();
            throw ExceptionUtil.getBaseException(ex1);
        }
        return szMap;
    }

    /**
     * 计算合计金额
     * @param sdForm 当前的ActionForm
     * @param mxList 已HashMap为节点的明细记录List
     * @throws BaseException
     */
    private void sum (List mxList, DsdzdkForm sdForm)
        throws BaseException
    {
        //记税金额合计
        BigDecimal jsjeTotal = new BigDecimal(0.00);
        //实缴金额合计
        BigDecimal sjseTotal = new BigDecimal(0.00);

        for (int i = 0; i < mxList.size(); i++)
        {
            HashMap record = (HashMap) mxList.get(i);

            String jsje = (String) record.get("jsje"); //记税金额
            String sjse = (String) record.get("sjse"); //实缴税额

            BigDecimal thisSjse = new BigDecimal(sjse);
            thisSjse = thisSjse.setScale(2, BigDecimal.ROUND_HALF_UP);
            sjseTotal = sjseTotal.add(thisSjse);

            BigDecimal thisJsje = new BigDecimal(jsje);
            thisJsje = thisJsje.setScale(2, BigDecimal.ROUND_HALF_UP);
            jsjeTotal = jsjeTotal.add(thisJsje);
        }

        sdForm.setJsjehj(jsjeTotal);
        sdForm.setSjsehj(sjseTotal);
    }

    /**
     * 设置申报汇总单列表、缴款凭证列表
     * @param sdForm 主表数据
     * @throws BaseException
     */
    private void loadDataList (DsdzdkForm sdForm)
        throws BaseException
    {

        Connection conn = null;
        SfDBAccess dbAgent = null;

        try
        {
            conn = SfDBResource.getConnection();
            dbAgent = new SfDBAccess(conn);

            //========================= 查找可撤销汇总单======================
            // 查找已经有缴款记录的申报编号，帐页日期或者缴款日期不为空.
            // 如果某一个申报编号下的数据，存在缴款记录，不管是否全部缴款，
            // 都不许再修改维护，过滤掉这样的数据
//      String subQuery = " SELECT DISTINCT SBBH " +
//          " FROM SBDB.SB_JL_SBJKZB " +
//          " WHERE QXDM='" + sdForm.getQxdm() + "'" +
//          " AND FSDM='" + CodeConstant.FSDM_SMSB + "'" +
//          " AND substr(zwbs,1,1)='" + CodeConstant.SMSB_ZWBS + "'" +
//          " AND substr(zwbs,20,1)='" + CodeConstant.SMSB_ZWBS20 + "'"+
//          " AND JSJDM='" + sdForm.getJsjdm() + "'";
//      String subQuery = " SELECT DISTINCT SBBH " +
//         " FROM SBDB.SB_JL_SBJKZB " +
//         " WHERE QXDM='" + sdForm.getQxdm() + "'" +
//         " AND FSDM='" + CodeConstant.FSDM_SMSB + "'" +
//         " AND zwbs like  '"+CodeConstant.SMSB_ZWBS+"%"+CodeConstant.SMSB_ZWBS+"'"+
//         " AND JSJDM='" + sdForm.getJsjdm() + "'";
            String subQuery = " SELECT DISTINCT SBBH " +
                              " FROM SBDB.SB_JL_SBJKZB " +
                              " WHERE QXDM='" + sdForm.getQxdm() + "'" +
                              " AND zwbs like  '" + CodeConstant.SMSB_ZWBS
                              + "%" + CodeConstant.SMSB_ZWBS + "'" +
                              " AND JSJDM='" + sdForm.getJsjdm() + "'";

            Vector vMx = new Vector();
            vMx.add(" QXDM='" + sdForm.getQxdm() + "'");
            vMx.add(" JSJDM='" + sdForm.getJsjdm() + "' ");
            vMx.add(" SBBH IN (" + subQuery + ")");
            vMx.add(" (CLBJDM='" + CodeConstant.CLBJDM_WCL +
                    "' OR CLBJDM='" + CodeConstant.CLBJDM_YSB + "') ");
            Debug.out("HZ SQL=" + vMx.toString());

            // 用来存放统计结果的中间表
            HashMap tmpListMap = new HashMap();

            List hzdList = dbAgent.query(Sdwszsbhz.class, vMx);
            if (hzdList.size() > 0)
            {

                Iterator hzdItems = hzdList.iterator();
                while (hzdItems.hasNext())
                {
                    Sdwszsbhz hzdItem = (Sdwszsbhz) hzdItems.next();
                    // 设置当前申报汇总编号, 默认为第一个
                    if (sdForm.getSbhzdh() == null
                        || "".equals(sdForm.getSbhzdh()))
                    {
                        sdForm.setSbhzdh(hzdItem.getSbhzdh());
                    }
                    if (!tmpListMap.containsKey(hzdItem.getSbhzdh()))
                    {
                        tmpListMap.put(hzdItem.getSbhzdh(),
                                       loadSbhz(dbAgent, sdForm.getQxdm(),
                                                sdForm.getJsjdm(),
                                                hzdItem.getSbhzdh()));
                    }
                }
                sdForm.getHzdDataList().addAll(tmpListMap.values());

                //=================== 查找当前汇总单对应的可撤销汇总凭证====================
                sdForm.getJkpzDataList().addAll(loadJkpz(dbAgent,
                    sdForm.getQxdm(),
                    sdForm.getJsjdm(),
                    sdForm.getSbhzdh()));
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
    }

    /**
     * 查找当前汇总单凭证汇总
     * @param dbAgent 数据库连接
     * @param qxdm 区县代码
     * @param jsjdm 计算机代码
     * @param sbhzdh 申报汇总单号
     * @return 汇总数据
     * @throws BaseException
     */
    private HashMap loadSbhz (SfDBAccess dbAgent, String qxdm, String jsjdm,
                              String sbhzdh)
        throws
        BaseException
    {

        HashMap hzMap = new HashMap();
        try
        {
            //汇总日期
            java.sql.Timestamp hzrq = new Timestamp(System.currentTimeMillis());
            //凭证张数
            int jkpzs = 0;
            //实缴税额合计
            BigDecimal sjseTotal = new BigDecimal(0.00);

            String[] hzd_columns =
                                   {
                                   "hzrq", "sbhzdh", "sjse", "jkpzs"};

            //================ 查找同一汇总单生成的缴款凭证 ====================
            Vector vMx = new Vector();
            vMx.add(" QXDM='" + qxdm + "'");
            vMx.add(" JSJDM='" + jsjdm + "' ");
            vMx.add(" SBHZDH='" + sbhzdh + "'");

            // 用来存放统计结果的中间表
            HashMap tmpListMap = new HashMap();

            List hzdList = dbAgent.query(Sdwszsbhz.class, vMx);
            Iterator hzdItems = hzdList.iterator();
            while (hzdItems.hasNext())
            {
                Sdwszsbhz hzdItem = (Sdwszsbhz) hzdItems.next();

                sjseTotal = sjseTotal.add(hzdItem.getSjse());
                hzrq = hzdItem.getHzrq();
                jkpzs = jkpzs + 1;
            }

            hzMap.put("sbhzdh", sbhzdh);
            hzMap.put("hzrq",
                      DateTimeUtil.timestampToString(hzrq,
                DateTimeUtil.JAVA_DATEFORMAT));
            hzMap.put("jkpzs", String.valueOf(jkpzs));
            hzMap.put("sjse", sjseTotal.toString());

        }
        catch (Exception ex)
        {
            //抛出异常
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
        return hzMap;
    }

    /**
     * 查找当前汇总单对应的可撤销汇总凭证,存储到ActionForm中
     * @param dbAgent 数据库连接
     * @param qxdm 区县代码
     * @param jsjdm 计算机代码
     * @param sbhzdh 申报汇总单号
     * @return 汇总数据
     * @throws BaseException
     */
    private List loadJkpz (SfDBAccess dbAgent, String qxdm, String jsjdm,
                           String sbhzdh)
        throws
        BaseException
    {

        List mapList = new ArrayList();
        String[] pz_columns =
                              {
                              "sbhzdh", "jkpzh", "sjse"};

        try
        {
            List orList = getJkpzList(dbAgent, qxdm, jsjdm, sbhzdh);
            Iterator orItems = orList.iterator();
            while (orItems.hasNext())
            {
                Sdwszsbhz orItem = (Sdwszsbhz) orItems.next();

                HashMap pzMap = new HashMap();
                BeanUtil.copyBeanToMap(pz_columns, orItem, pzMap);
                mapList.add(pzMap);
            }
        }
        catch (Exception ex)
        {
            //抛出异常
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
        return mapList;
    }
/**
 * 根据计算机代码等取得缴款书列表
 * @param dbAgent 数据库链接
 * @param qxdm 区县代码
 * @param jsjdm 计算机代码
 * @param sbhzdh 申报汇总单号
 * @return 缴款书列表
 * @throws BaseException
 */
    private List getJkpzList (SfDBAccess dbAgent, String qxdm, String jsjdm,
                              String sbhzdh)
        throws
        BaseException
    {

        // 返回当前申报汇总单号下的凭证放入结果集
        Vector vJk = new Vector();
        vJk.add(" QXDM='" + qxdm + "'");
        vJk.add(" JSJDM='" + jsjdm + "'");
        vJk.add(" SBHZDH='" + sbhzdh + "'");
        Debug.out("JKPZ LIST SQL=" + vJk.toString());

        List pzList = dbAgent.query(Sdwszsbhz.class, vJk);

        return pzList;
    }

    /**
     * 计算申报年度
     * @param sbrq 申报日期
     * @return 申报年度
     */
    private String getSbnd (String sbrq)
    {

        Map qj = Skssrq.yearSkssrq(SfDateUtil.getDate(sbrq));
        return (String) qj.get(Skssrq.SKSSRQ_ND);
    }

    /**
     * 设置三代导入、汇总相关的数据
     * @param sdForm 主表数据
     * @throws BaseException
     */
    private void initForm (DsdzdkForm sdForm)
        throws BaseException
    {

        sdForm.setClbjdm(CodeConstant.CLBJDM_YSB); // 已申报

        sdForm.setSbrq(SfDateUtil.getDate());
        sdForm.setHzrq(sdForm.getSbrq());
        sdForm.setHzksrq(sdForm.getHzrq());
        sdForm.setHzjsrq(sdForm.getHzrq());
        sdForm.setCjrq(TinyTools.Date2String(new Date(), CodeConstant.DATETYPE));
        sdForm.setLrrq(TinyTools.Date2String(new Date(), CodeConstant.DATETYPE));
        sdForm.setSjly(SMSB_SJLY_DR);
        sdForm.setFsdm(CodeConstant.FSDM_SMSB);

        // 申报期间
        Map qj = Skssrq.yearSkssrq(SfDateUtil.getDate(sdForm.getSbrq()));
        try
        {
            String ksrq = DateTimeUtil.timestampToString(
                (Timestamp) qj.get(Skssrq.SKSSKSRQ),
                DateTimeUtil.JAVA_DATEFORMAT);

            String jsrq = DateTimeUtil.timestampToString(
                (Timestamp) qj.get(Skssrq.SKSSJSRQ),
                DateTimeUtil.JAVA_DATEFORMAT);

            sdForm.setSkssksrq(ksrq);
            sdForm.setSkssjsrq(jsrq);
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }

    }

}
