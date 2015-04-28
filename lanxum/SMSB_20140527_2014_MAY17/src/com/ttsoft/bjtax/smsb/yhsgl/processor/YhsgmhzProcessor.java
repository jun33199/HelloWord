/*
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */
package com.ttsoft.bjtax.smsb.yhsgl.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.dj.model.YHZH;
import com.ttsoft.bjtax.dj.proxy.ServiceProxy;
import com.ttsoft.bjtax.sfgl.common.code.CodeManager;
import com.ttsoft.bjtax.sfgl.common.code.CodeUtils;
import com.ttsoft.bjtax.sfgl.common.constant.CodeConstants;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.util.LabelValueBean;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.sfgl.common.util.SfTimeUtil;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkmx;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.model.client.DeclareInfor;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.JksUtil;
import com.ttsoft.bjtax.smsb.util.Skssrq;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.bjtax.smsb.yhsgl.web.YhsgmhzForm;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Description: 汇总印花税购买情况 后台处理</p>
 * @author 开发六组－－诸光林
 * @version 1.1
 */
public class YhsgmhzProcessor
    implements Processor
{

    /**
     * 默认构造函数
     */
    public YhsgmhzProcessor ()
    {
    }

    /**
     * 通用处理调度模块
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return object
     * @throws BaseException
     */
    public Object process (VOPackage vo)
        throws
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
                result = doShow(vo);
                break;
            case CodeConstant.SMSB_HZJKSACTION:
                result = doHzjks(vo);
                break;
            default:
                throw new UnsupportedOperationException(
                    "Method process() not yet implemented.");
        }
        return result;
    }
    /**
     * 初始化对象页面信息要素
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return Form
     * @throws BaseException
     */
    private Object doShow (VOPackage vo)
        throws BaseException
    {
        return new YhsgmhzForm();
    }

    /**
     * 汇总数据生成缴款书
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return Form
     * @throws BaseException
     */
    private Object doHzjks (VOPackage vo)
        throws BaseException
    {
        YhsgmhzForm form = (YhsgmhzForm) vo.getData();
        HashMap map = null;
        try
        {
            //生成缴款书
            map = doCreateJks(vo);
            form.setJkpzh(map.get("jkpzh").toString());
            form.setSjje(map.get("sjje").toString());
            form.setHzdxmc(map.get("hzdxmc").toString());
            form.setSbbh(map.get("sbbh").toString());
            return form;
        }
        catch (BaseException ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    /**
     * 汇总数据生成缴款书数据，保存到申报缴款主表、明细表
     * 回填汇总信息到印花税购买主表
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return map 返回汇总信息（汇总结果显示页面用到）
     * @throws BaseException
     */
    private HashMap doCreateJks (VOPackage vo)
        throws BaseException
    {
        HashMap map = new HashMap();
        Connection conn = null;

        YhsgmhzForm form = (YhsgmhzForm) vo.getData();
        String hzfs = form.getHzdx();
        String jsjdm = "";
        String lrr = form.getLrr();
        String yhsxsry = form.getYhsxsry();
        String begTime = form.getHzqsrq();
        String endTime = form.getHzjsrq();
        String zsjgdm = form.getZsjgdm();
        String swjgzzjgdm = "";
        String jkpzh = "";
        String sbbh = "";
        //返回数据
        BigDecimal sjje = null; //实缴金额
        String hzdxmc = ""; //汇总对象名称
        String dsdwmc = ""; //代售单位名称
        try
        {
            conn = SfDBResource.getConnection();
            UserData userData = vo.getUserData();
            String strSsdwdm = userData.getSsdwdm(); //userData所属的税务机关组织机构代码
            String strDsJsjdm = getDsjsjdmByUserData(strSsdwdm);
            if (strDsJsjdm == null || strDsJsjdm == "")
            {
                throw new ApplicationException("未得到相应的代售计算机代码！");
            }
            else
            {
                form.setDsjsjdm(strDsJsjdm);
                jsjdm = strDsJsjdm;
            }
            SfDBAccess db = new SfDBAccess(conn);
            //设置时间条件
            if (endTime == null || endTime.equals(""))
            {
                endTime = SfDateUtil.getDate();
            }
            String timeCon = " and cjrq<=to_date('" + endTime +
                             " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
            if (begTime != null && !begTime.equals(""))
            {
                timeCon = timeCon + " and cjrq>=to_date('" + begTime +
                          " 00:00:00','yyyy-mm-dd hh24:mi:ss')";
            }

            //得到汇总结果数据
            try
            {
                StringBuffer sqlBuffer = new StringBuffer();
                sqlBuffer.append(
                    "select sum(hjje) sjje from sbdb.sb_jl_yhsgmz where");
                if (hzfs.equals("0"))
                { //按单位汇总
                    sqlBuffer.append(" dsjsjdm='").append(jsjdm).append("'");
                }
                else
                { //按销售人汇总
                    sqlBuffer.append(" zhdm='").append(yhsxsry).append("'");
                } //end if
                sqlBuffer.append(" and qxdm='" + InterfaceDj.getQxdm(userData)
                                 + "' ");
                sqlBuffer.append(" and (yhzbs<>'1' or yhzbs is null)").append(
                    timeCon);

                String sqlString = sqlBuffer.toString();
                ResultSet rs = db.querySQL(sqlString);
                if (rs.next())
                {
                    if (rs.getString("sjje") != null)
                    {
                        sjje = new BigDecimal(rs.getString("sjje")); //得到实缴金额
                    }
                    else
                    {
                        throw new ApplicationException("没有未汇总的销售数据！");
                    }
                }
                else
                {
                    throw new ApplicationException("没有未汇总的销售数据！");
                }
                rs.close();
            }
            catch (Exception ex4)
            {
                ex4.printStackTrace();
                throw new ApplicationException(ex4.getMessage());
            } //end try

            //主表部分数据填写
            Sbjkzb orJkzb = new Sbjkzb();
            orJkzb.setSklxdm(CodeConstant.SKLXDM_ZCJK); // 2
            orJkzb.setJsjdm(jsjdm); // 3
            orJkzb.setFsdm("1"); // 4
            orJkzb.setZsswjgzzjgdm(zsjgdm); // 11
            orJkzb.setSjje(sjje); // 22
            orJkzb.setRkje(sjje); // 24
            //orJkzb.setZwbs("00000000000000000000"); // 25
            orJkzb.setZwbs("00000000000000000010"); // 20050705
            orJkzb.setLrr(lrr); // 28
            orJkzb.setSjly("15"); // 36

            //调登记接口,获得代售单位信息
            try
            {
                ServiceProxy djProxy = new ServiceProxy();
                HashMap ghdwMap = djProxy.getDjInfo(jsjdm);
                SWDJJBSJ swdjjbsj = (SWDJJBSJ) ghdwMap.get("JBSJ");
                dsdwmc = swdjjbsj.getNsrmc();
                orJkzb.setDjzclxdm(swdjjbsj.getDjzclxdm()); // 9
                orJkzb.setSwjgzzjgdm(swdjjbsj.getSwjgzzjgdm()); // 10
                swjgzzjgdm = swdjjbsj.getSwjgzzjgdm();
                orJkzb.setGjbzhydm(swdjjbsj.getGjbzhydm()); // 12
                orJkzb.setJydzlxdm(swdjjbsj.getJydzlxdm()); // 33
                //汇总生成缴款书不插入银行信息 Modifyed by wuyouzhi 2006.2.7
//                //得到银行信息
//                ArrayList dmList = (ArrayList) ghdwMap.get("YHZH");
//                for (int i = 0; i < dmList.size(); i++)
//                {
//                    YHZH yhzh = (YHZH) dmList.get(i);
//                    if (yhzh.getJbzhbs().equals(CodeConstant.SMSB_JBZHBS))
//                    {
//                        orJkzb.setYhdm(yhzh.getYhdm()); //银行代码
//                        orJkzb.setYhmc(yhzh.getKhyhmc()); //银行名称
//                        orJkzb.setZh(yhzh.getZh()); //帐户
//                        break;
//                    }
//                }
            }
            catch (Exception e2)
            {
                throw new ApplicationException("调登记接口出错！");
            } // end try

            //主表未确定项填写
            orJkzb.setLrrq(SfTimeUtil.getNowTimestamp()); // 17
            orJkzb.setSbrq(TinyTools.calendar2Timestamp(Calendar.getInstance()));
            orJkzb.setCjrq(SfTimeUtil.getNowTimestamp()); // 30
            orJkzb.setQxdm(InterfaceDj.getQxdm(userData));

            //从表处理
            Sbjkmx orJkmx = new Sbjkmx();
            orJkmx.setSzsmdm("161400"); // 1
            orJkmx.setSzdm("16");
            orJkmx.setJsjdm(jsjdm); // 3
            orJkmx.setSjse(sjje); // 8
            orJkmx.setRkje(sjje); // 11
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
                retJks = jks.getJkDataYhs(orJkzb, mxList,
                                          CodeConstant.PRINT_YPYS);
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
                sbbh = retZb.getSbbh();
            }

            String timeNow = SfTimeUtil.getNowTimestamp().toString();
            timeNow = timeNow.substring(0, timeNow.length() - 4);
            //回填汇总信息
            try
            {
                StringBuffer sqlBuffer = new StringBuffer();
                sqlBuffer.append("update sbdb.sb_jl_yhsgmz set jkpzh='")
                    .append(jkpzh)
                    .append("', hzrq=to_date('")
                    .append(SfDateUtil.getDate())
                    .append("','yyyyMMdd'), hzr='")
                    .append(lrr)
                    .append("', yhzbs='1', hzfs='")
                    .append(hzfs)
                    .append("', lrrq=to_date('")
                    .append(timeNow)
                    .append("','yyyy-mm-dd hh24:mi:ss')")
                    .append(" where");

                if (hzfs.equals(CodeConstant.HZFS_DW))
                { //按单位汇总
                    sqlBuffer.append(" dsjsjdm='").append(jsjdm).append("'");
                    hzdxmc = dsdwmc;
                }
                else
                { //按销售人汇总
                    sqlBuffer.append(" zhdm='").append(yhsxsry).append("'");
                    List codeList = CodeManager.getCodeList("YHS_ZHDM",
                        CodeConstants.CODE_MAP_BEANLIST).
                                    getRecords();
                    String name = "";
                    for (int k = 0; k < codeList.size(); k++)
                    {
                        if (yhsxsry.equals( ( (LabelValueBean) codeList.get(k)).
                                           getValue()))
                        {
                            name = ( (LabelValueBean) codeList.get(k)).getLabel();
                            break;
                        }
                    }
                    hzdxmc = name;
                }
                sqlBuffer.append(" and qxdm='" + InterfaceDj.getQxdm(userData)
                                 + "' ");
                sqlBuffer.append(" and (yhzbs<>'1' or yhzbs is null)").append(
                    timeCon);

                String sqlString = sqlBuffer.toString();
                PreparedStatement sqlStatement = conn.prepareStatement(
                    sqlString);
                sqlStatement.execute();
            }
            catch (Exception ex3)
            {
                ex3.printStackTrace();
                throw new ApplicationException("回填汇总信息出错！");
            }
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        } //end try
        finally
        {
            SfDBResource.freeConnection(conn);
        }
        map.put("sjje", sjje);
        map.put("jkpzh", jkpzh);
        map.put("hzdxmc", hzdxmc);
        map.put("sbbh",sbbh);
        return map;
    }

    /**
     * 根据UserData得到代售单位计算机代码
     * @param strSsdwdm 所属税务单位代码
     * @return 代售单位计算机代码
     */
    private String getDsjsjdmByUserData (String strSsdwdm)
    {
        String dsJsjdm = "";
        dsJsjdm = CodeUtils.getCodeMapValue("ZHSB_SWJGZZJG",
                                            "swjgzzjgdm", strSsdwdm, "jsjdm");
        return dsJsjdm;
    }

}
