/*
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */
package com.ttsoft.bjtax.smsb.yhsgl.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.struts.upload.FormFile;
import org.xml.sax.InputSource;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.dj.model.YHZH;
import com.ttsoft.bjtax.dj.proxy.ServiceProxy;
import com.ttsoft.bjtax.sfgl.common.db.util.BeanUtil;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.sfgl.common.util.SfTimeUtil;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkmx;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb;
import com.ttsoft.bjtax.shenbao.model.domain.Yhsgmmx;
import com.ttsoft.bjtax.shenbao.model.domain.Yhsgmz;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.model.client.DeclareInfor;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.JksUtil;
import com.ttsoft.bjtax.smsb.util.Skssrq;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.bjtax.smsb.yhsgl.web.YhsxsdrForm;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Description: 印花税代售单位销售导入 后台处理</p>
 * @author 开发六组－－诸光林
 * @version 1.1
 */

public class YhsxsdrProcessor
    implements Processor
{

    /**
     * 默认构造函数
     */
    public YhsxsdrProcessor ()
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
            case CodeConstant.SMSB_HZJKSACTION:
                result = doHzjks(vo);
                break;
            case CodeConstant.SMSB_CXJKSACTION:
                doCxjks(vo);
            case CodeConstant.SMSB_LOADACTION:
                result = doLoad(vo);
                break;
            default:
                throw new UnsupportedOperationException(
                    "Method process() not yet implemented.");
        }
        return result;
    }

    /**
     * doShow 初始化对象页面信息要素
     * @param vo 数据集对象（包括Form和UserData对象）
     * @throws BaseException
     */
    private void doShow (VOPackage vo)
        throws BaseException
    {
    }

    /**
     * 导入代售单位销售数据文件，返回合法数据集
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return Form
     * @throws BaseException
     */
    private Object doLoad (VOPackage vo)
        throws BaseException
    {
        YhsxsdrForm form = (YhsxsdrForm) vo.getData();
        FormFile formFile = form.getTheFile();
        try
        {
            //获得导入数据
            InputSource theFile = new InputSource(formFile.getInputStream());
            //解析数据得到导入记录
            xml4YHS parser = new xml4YHS(theFile);
            form.setDataList(parser.yhsList); //销售数据
            form.setDsjsjdm(parser.dsjsjdm); //代售单位计算机代码
            form.setXsList(parser.xsList); //销售凭证号与相应的合计金额
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "当前导入的xml文件数据错误!");
        }
        return form;
    }

    /**
     * 汇总导入数据生成缴款书
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return map 返回汇总结果显示页面需要的汇总结果信息
     * @throws BaseException
     */
    private HashMap doHzjks (VOPackage vo)
        throws BaseException
    {
        try
        {
            //保存导入的数据到印花税购买主表、明细表
            doSave2GMDB(vo);

            //汇总导入数据生成缴款书数据，保存到申报缴款主表、明细表;
            //回填汇总信息到印花税购买主表
            //返回汇总结果显示页面需要的汇总结果信息
            return (doCreateJks(vo));
        } //end try
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    /**
     * 保存导入的数据到印花税购买主表、明细表
     * @param vo 数据集对象（包括Form和UserData对象）
     * @throws BaseException
     */
    private void doSave2GMDB (VOPackage vo)
        throws BaseException
    {
        Connection conn = null;
        YhsxsdrForm form = (YhsxsdrForm) vo.getData();
        Yhsgmz orObj = new Yhsgmz();

        //为调用票证接口准备参数
        //销售数据列表
        ArrayList zbList = (ArrayList) form.getXsList().clone();
        //明细项目集合
        ArrayList tempList = (ArrayList) form.getDataList().clone();
        List[] checkListArray = new ArrayList[zbList.size()];
        int countOfArray = 0;
        for (int i = 0; i < tempList.size(); i++)
        {
            ( (Map) tempList.get(i)).put("sequence", "" + (i + 1));
            if (i == 0)
            {
                checkListArray[countOfArray] = new ArrayList();
                checkListArray[countOfArray].add( (Map) tempList.get(i));
            }
            else if (i > 0)
            {
                //上一个销售凭证号
                String preXspzh = (String) ( (Map) tempList.get(i - 1)).get(
                    "xspzh");
                //当前销售凭证号
                String curXspzh = (String) ( (Map) tempList.get(i)).get("xspzh");
                if (preXspzh.equals(curXspzh))
                //如果属同一销售凭证号则一同处理
                //否则加入新的列表
                {
                    checkListArray[countOfArray].add( (Map) tempList.get(i));
                }
                else
                {
                    countOfArray = countOfArray + 1;
                    checkListArray[countOfArray] = new ArrayList();
                    checkListArray[countOfArray].add( (Map) tempList.get(i));
                }
            }
        }

        //调用票证接口印花税票检验，并更新库存
        for (int i = 0; i < zbList.size(); i++)
        {
            String zhdm = (String) ( (Map) zbList.get(i)).get("zhdm");
            List yhspList = com.ttsoft.bjtax.pzgl.proxy.ServiceProxy.
                            checkSpsl(vo.getUserData(), zhdm, checkListArray[i]);
            checkYhspsl(yhspList);
        }

        //代售单位计算机代码，创建日期，录入日期，代售单位名称，数据来源
        //税务机关组织机构代码，方式代码，录入人
        String columns[] =
                           {
                           "dsjsjdm", "cjsj", "lrrq", "dsdwmc", "sjly",
                           "swjgzzjgdm", "fsdm", "lrr"};
        try
        {
            conn = SfDBResource.getConnection();
            SfDBAccess da = new SfDBAccess(conn);

            //主表数据处理
            BeanUtil.copyBeanToBean(columns, form, orObj);
            ArrayList xsList = (ArrayList) form.getXsList().clone();
            for (int j = 0; j < xsList.size(); j++)
            {
                String xspzh = ( (HashMap) xsList.get(j)).get("xspzh").toString();
                String hjje = ( (HashMap) xsList.get(j)).get("hjje").toString();
                orObj.setXspzh(xspzh);
                orObj.setHjje(new BigDecimal(hjje));
                orObj.setCjrq(SfTimeUtil.getNowTimestamp());
                orObj.setLrrq(SfTimeUtil.getNowTimestamp());
                orObj.setQxdm(InterfaceDj.getQxdm(vo.getUserData()));
                if (orObj.getCjrq() != null)
                {
                    orObj.setNd("" + TinyTools.getYear(orObj.getCjrq()));
                }
                orObj.setZhdm( ( (HashMap) xsList.get(j)).get("zhdm").toString());
                orObj.setJzbs("0"); //未结帐
                Vector condition = new Vector();
                condition.add("qxdm='" + InterfaceDj.getQxdm(vo.getUserData())
                              + "'");
                condition.add("xspzh='" + xspzh + "'");
                condition.add("dsjsjdm='" + form.getDsjsjdm() + "'");
                if (da.query(orObj.getClass(), condition).size() > 0)
                {
                    throw new ApplicationException("销售数据已存在！");
                }
                else
                {
                    try
                    {
                        da.insert(orObj);
                    }
                    catch (BaseException ex3)
                    {
                        throw new ApplicationException("保存销售数据出错！");
                    } //end try
                } //end if
            } //end for

            //明细数据获得
            ArrayList list = form.getDataList();
            //名细数据处理
            for (int i = 0; i < list.size(); i++)
            {
                HashMap map = (HashMap) list.get(i);
                Yhsgmmx orMx = new Yhsgmmx();
                map.put("dsjsjdm", form.getDsjsjdm());
                map.put("swjgzzjgdm", form.getSwjgzzjgdm());
                BeanUtil.populate(orMx, map);
                orMx.setNd("" + orObj.getNd());
                orMx.setCjrq(SfTimeUtil.getNowTimestamp());
                orMx.setLrrq(SfTimeUtil.getNowTimestamp());
                orMx.setQxdm(InterfaceDj.getQxdm(vo.getUserData()));
                Vector conditionmx = new Vector();
                conditionmx.add("qxdm='" + InterfaceDj.getQxdm(vo.getUserData())
                                + "'");
                conditionmx.add("dsjsjdm='" + form.getDsjsjdm() + "'");
                conditionmx.add("xspzh='" + map.get("xspzh") + "'");
                conditionmx.add("spmzdm='" + map.get("spmzdm") + "'");
                if (da.query(orMx.getClass(), conditionmx).size() > 0)
                {
                    throw new ApplicationException("销售明细数据已存在！");
                }
                else
                {
                    try
                    {
                        da.insert(orMx);
                    }
                    catch (BaseException ex3)
                    {
                        throw new ApplicationException("保存销售明细数据出错！");
                    } //end try
                } //end if
            } //end for
        } //end try
        catch (Exception ex)
        {
            throw new ApplicationException("保存导入数据出错：" + ex.getMessage());
        }
        finally
        {
            SfDBResource.freeConnection(conn);
        }
    }

    /**
     * 汇总导入数据生成缴款书数据，保存到申报缴款主表、明细表
     * 回填汇总信息到印花税购买主表
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return map 返回汇总信息（汇总结果显示页面用到）
     * @throws BaseException
     */
    private HashMap doCreateJks (VOPackage vo)
        throws BaseException
    {
        Connection conn = null;
        conn = SfDBResource.getConnection();
        HashMap map = new HashMap();
        YhsxsdrForm form = (YhsxsdrForm) vo.getData();
        ArrayList xsList = (ArrayList) form.getXsList().clone();
        String jsjdm = form.getDsjsjdm();
        BigDecimal sjse = new BigDecimal(form.getHjje());
        String lrr = form.getLrr();
        String swjgzzjgdm = "";
        String jkpzh = "";
        String dsdwmc = "";

        //生成缴款审批主表数据
        Sbjkzb orJkzb = new Sbjkzb();
        orJkzb.setSklxdm("200"); // 2
        orJkzb.setJsjdm(jsjdm); // 3
        orJkzb.setFsdm("1"); // 4
        orJkzb.setSjje(sjse); // 22
        orJkzb.setRkje(sjse); // 24
        //orJkzb.setZwbs("00000000000000000000"); // 25
        //第18、19 位01表示现金汇总
        orJkzb.setZwbs("00000000000000000010"); // 25
        orJkzb.setLrr(lrr); // 28
        orJkzb.setSjly("15"); // 36

        //调登记接口,获得代售单位信息
        try
        {
            ServiceProxy djProxy = new ServiceProxy();
            HashMap ghdwMap = djProxy.getDjInfo(jsjdm);
            SWDJJBSJ swdjjbsj = (SWDJJBSJ) ghdwMap.get("JBSJ");
            dsdwmc = swdjjbsj.getNsrmc();
            orJkzb.setLsgxdm(swdjjbsj.getLsgxdm()); // 7
            orJkzb.setDjzclxdm(swdjjbsj.getDjzclxdm()); // 9
            swjgzzjgdm = swdjjbsj.getSwjgzzjgdm();
            orJkzb.setSwjgzzjgdm(swjgzzjgdm); // 10
            orJkzb.setZsswjgzzjgdm(swjgzzjgdm); // 11
            orJkzb.setGjbzhydm(swdjjbsj.getGjbzhydm()); // 12
            orJkzb.setJydzlxdm(swdjjbsj.getJydzlxdm()); // 33
            //得到银行信息
            ArrayList dmList = (ArrayList) ghdwMap.get("YHZH");
            for (int i = 0; i < dmList.size(); i++)
            {
                YHZH yhzh = (YHZH) dmList.get(i);
                if (yhzh.getJbzhbs().equals(CodeConstant.SMSB_JBZHBS))
                {
                    orJkzb.setYhdm(yhzh.getYhdm()); //银行代码
                    orJkzb.setYhmc(yhzh.getKhyhmc()); //银行名称
                    orJkzb.setZh(yhzh.getZh()); //帐户
                    break;
                }
            }
        }
        catch (Exception e2)
        {
            throw ExceptionUtil.getBaseException(e2);
        }

        //申报缴款主表未确定项
        orJkzb.setLrrq(SfTimeUtil.getNowTimestamp()); // 17
        orJkzb.setSbrq(TinyTools.calendar2Timestamp(Calendar.getInstance()));
        orJkzb.setCjrq(SfTimeUtil.getNowTimestamp()); // 30
        orJkzb.setQxdm(InterfaceDj.getQxdm(vo.getUserData()));

        //生成申报缴款明细表数据
        Sbjkmx orJkmx = new Sbjkmx();
        orJkmx.setSzsmdm("16"); // 1
        orJkmx.setSzdm("16");
        orJkmx.setJsjdm(jsjdm); // 3
        orJkmx.setSjse(sjse); // 8
        orJkmx.setRkje(sjse); // 11
        orJkmx.setSwjgzzjgdm(swjgzzjgdm); // 15
        //取得税款所属日期
        Map dateMap = Skssrq.monthSkssrq(new Date());
        orJkmx.setSkssksrq( (Timestamp) dateMap.get(Skssrq.SKSSKSRQ));
        orJkmx.setSkssjsrq( (Timestamp) dateMap.get(Skssrq.SKSSJSRQ));

        //将主表和明细数据插入sbjkzb，sbjkmx
        JksUtil jks = new JksUtil();
        List mxList = new ArrayList();

        //生成明细数据列表
        mxList.add(orJkmx);

        //插入
        List retJks = null;
        try
        {
            retJks = jks.getJkDataYhs(orJkzb, mxList, CodeConstant.PRINT_YPYS);
        }
        catch (BaseException ex1)
        {
            throw ExceptionUtil.getBaseException(ex1);
        }

        //在返回值中得到缴款凭证号
        if (retJks.size() > 0)
        {
            DeclareInfor retSbjk = (DeclareInfor) retJks.get(0);
            Sbjkzb retZb = retSbjk.getSbjkzb();
            jkpzh = retZb.getJkpzh();
        }

        String timeNow = SfTimeUtil.getNowTimestamp().toString();
        timeNow = timeNow.substring(0, timeNow.length() - 4);
        //回填汇总信息
        String hzrq = SfDateUtil.getDate();
        for (int k = 0; k < xsList.size(); k++)
        {
            String xspzh = ( (HashMap) xsList.get(k)).get("xspzh").toString();
            try
            {
                StringBuffer sqlBuffer = new StringBuffer();
                sqlBuffer.append("update sbdb.sb_jl_yhsgmz set jkpzh='")
                    .append(jkpzh)
                    .append("', hzrq=to_date('")
                    .append(hzrq)
                    .append("','yyyyMMdd'), hzr='")
                    .append(lrr)
                    .append("', lrrq=to_date('")
                    .append(timeNow)
                    .append("','yyyy-mm-dd hh24:mi:ss')")
                    //已汇总标识：'1'
                    //汇总方式代码：按代售单位汇总->'0'
                    .append(", yhzbs='1', hzfs='0' where ")
                    .append(" qxdm='" + InterfaceDj.getQxdm(vo.getUserData())
                            + "' ")
                    .append(" and xspzh='")
                    .append(xspzh)
                    .append("' and dsjsjdm='")
                    .append(jsjdm)
                    .append("'");
                String sqlString = sqlBuffer.toString();
                PreparedStatement sqlStatement = conn.prepareStatement(
                    sqlString);
                sqlStatement.execute();
            }
            catch (Exception ex)
            {
                throw ExceptionUtil.getBaseException(ex, "回填汇总信息出错");
            } //end try
        } //end for

        //返回汇总信息
        map.put("dsdwmc", dsdwmc);
        map.put("jkpzh", jkpzh);
        map.put("sjse", sjse);
        return map;
    }

    /**
     * 撤销缴款书
     * @param vo 数据集对象（包括Form和UserData对象）
     * @throws BaseException
     */
    private void doCxjks (VOPackage vo)
        throws BaseException
    {
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
                if (intError == -1)
                //当前操作员无权销售此种印花税票
                {
                    errorMessage = errorMessage + "请检查序号为" +
                                   (String) map.get("sequence") + "的印花税票，" +
                                   "您没有权限销售此种印花税票。<BR>";
                }
                else
                //当前操作员可销售的此种印花税票库存量不足
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