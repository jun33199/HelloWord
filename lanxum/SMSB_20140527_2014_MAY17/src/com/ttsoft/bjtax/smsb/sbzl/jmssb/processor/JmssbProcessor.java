/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.jmssb.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector; //登记接口结果类

//登记接口结果类
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.jikuai.zwcl.inf.JKAdapter;
import com.ttsoft.bjtax.sfgl.common.code.CodeManager;
import com.ttsoft.bjtax.sfgl.common.constant.CodeConstants;
import com.ttsoft.bjtax.sfgl.common.db.util.BeanUtil;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource; //smsbbaselib提供的工具类
//smsbbaselib提供的工具类
import com.ttsoft.bjtax.sfgl.common.util.Debug;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.sfgl.common.util.SfStringUtils;
import com.ttsoft.bjtax.sfgl.common.util.SfTimeUtil;
import com.ttsoft.bjtax.sfgl.proxy.ServiceProxy;
//OrMapping值对象包
import com.ttsoft.bjtax.shenbao.model.domain.Jm;
import com.ttsoft.bjtax.shenbao.model.domain.Szsm;
import com.ttsoft.bjtax.shenbao.model.domain.Zqrl;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.sbzl.jmssb.web.JmssbForm;
import com.ttsoft.bjtax.smsb.util.EArray;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.InterfaceSf4Sb;
import com.ttsoft.bjtax.smsb.util.JksUtil;
//申报内部工具类
import com.ttsoft.bjtax.smsb.util.Skssrq;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description: 北京市减免税申报</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class JmssbProcessor
    implements Processor
{

    /**
     * 页面使用，产生js数组
     */

    private EArray jsArray = new EArray();

    /**
     * 判断标示
     */

    private String ok = "";

    /**
     * 判断标示
     */
    private String oks = "";

    /**
     * 行号
     */
    private int bhghc = 0;

    /**
     * 不具备审批资格出现的标志号
     */
    private int time = 0;

    /**
     * 返回结果集列表
     */
    private List ret = new ArrayList();

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
                result = doSave(vo);
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
        JmssbForm form = (JmssbForm) vo.getData();
        String tempJsStr = jsArray.getArrayByCode("szsmlist_add",
                                                  "ZHSB_SZSMADD");
        tempJsStr += jsArray.getArrayByCode("szsmlist", "JM_SZSMDM");
        tempJsStr +=
            jsArray.getMsgsByCode("szsmdm", "JM_SZSM_ALL", new ArrayList());
        tempJsStr +=jsArray.getMsgsByCode("jmxmjdm", "JM_JMXMJDM",new ArrayList());//add by tangchangfu 2014-05-02 无税减免税项目二期
        form.setScriptStr(tempJsStr);
        form.setSbrq(SfDateUtil.getDate());

        return form;
    }

    /**
     * doQuery    用于返回页面索要查询的详尽信息
     * @param     vo 业务参数
     * @return    数据对象
     * @throws BaseException    当其他情况都会抛出异常信息
     */
    public Object doQuery (VOPackage vo)
        throws BaseException
    {
        //得到Action传递过来ActionForm对象
        JmssbForm form = (JmssbForm) vo.getData();
        UserData ud = (UserData) vo.getUserData();
        SWDJJBSJ djxx = null;
        try
        {
            /* start added by huxiaofeng 2005.8.16*/
            //djxx = InterfaceDj.getJBSJ(form.getJsjdm(), ud);
            djxx = InterfaceDj.getJBSJ_New(form.getJsjdm(), ud);
            form.setNsrzt(djxx.getNsrzt());
            /* end added by huxiaofeng 2005.8.16*/
            form.setQxdm(InterfaceDj.getQxdm(ud));
            Debug.out(InterfaceDj.getQxdm(ud));
        }
        catch (Exception ex1)
        {
            throw ExceptionUtil.getBaseException(ex1);
        }
        EArray jsArray = new EArray();
        String tempJsStr = jsArray.getArrayByCode("szsmlist_add",
                                                  "ZHSB_SZSMADD");
        tempJsStr +=jsArray.getMsgsByCode("jmxmjdm", "JM_JMXMJDM",new ArrayList());//add by tangchangfu 2014-05-02 无税减免税项目 
        Timestamp curTime = new Timestamp(System.currentTimeMillis());
        //建立数据库连接
        Connection conn = null;
        try
        {
            conn = SfDBResource.getConnection();
            //使用OrMap操作数据库的封装类
            SfDBAccess da = new SfDBAccess(conn);
            Map zqrlMap = this.getSksssqMap(djxx.getDjzclxdm(),
                                            SfDateUtil.getDate(form.getSbrq()),
                                            conn);
            Map ynsjeMap = InterfaceSf4Sb.getYnsje(form.getJsjdm(),
                SfDateUtil.getDate(form.getSbrq()));
            List sjList = new ArrayList();
            sjList = this.getJmDate(ynsjeMap, zqrlMap, form);
            tempJsStr += this.getMxJsArray(sjList);
            Map dateMap = getSbrl(form.getSbrq());
            //查询主表数据
            Vector vZb = new Vector();
            //主表OrMapping值对象的每个参数
            //modify by tangchangfu 2014-04-04 无税减免税项目  新增字段 "dqxse","dqlrze","qyrs","azrs"
            String zbNames[] =
                {
                "jsjdm", "sbrq", "swjgzzjgdm", "fsdm", "jzbz",
                "djzclxdm", "gjbzhydm", "nd", "nsrmc", "qxdm","dqxse","dqlrze","qyrs","azrs"};
            BeanUtil.copyBeanToBean(zbNames, djxx, form);
            tempJsStr += jsArray.getArrayByCode("szsmlist", "JM_SZSMDM");
            //如果主表数据不存在
            vZb.add("qxdm='" + InterfaceDj.getQxdm(ud) + "'");
            vZb.add("jzbz like '" + CodeConstant.SMSB_JZBZ_EDIT + "%" + "'");
//            vZb.add("szsmdm!='" + CodeConstant.SZSM_QYSDSCODE + "'");  delete by tangchangfu 2014-04-04 无税减免税项目二期
            vZb.add(" (FSDM='" + CodeConstant.FSDM_WSSB +
                    "' OR FSDM='" + CodeConstant.FSDM_SMSB + "') ");
            vZb.add("sbrq >= to_date('" +
                    String.valueOf(dateMap.get("ksrq")).substring(0, 10) +
                    "','yyyy-MM-dd')");
            vZb.add("sbrq < to_date('" +
                    String.valueOf(dateMap.get("jsrq")).substring(0, 10) +
                    "','yyyy-MM-dd')");
            vZb.add("jsjdm='" + form.getJsjdm() + "' ");
            //主表数据结果
            List zbData = da.query(Jm.class, vZb);
            if (zbData.size() <= 0)
            {
                //根据登记接口得到当前计算机代码的纳税人信息，然后赋值给ActionForm

                BeanUtil.copyBeanToBean(zbNames, djxx, form);
                //初始化列表显示控制，因为此用户还没有相应的纳税信息
                tempJsStr +=
                    jsArray.getMsgsByCode("szsmdm", "JM_SZSM_ALL",
                                          new ArrayList());
                form.setScriptStr(tempJsStr);
                form.setCjrq(TinyTools.Date2String(curTime,
                    CodeConstant.DATETYPE));
                return form;
            } //end if
            form.setNsrmc(djxx.getNsrmc());
            //将主表数据赋给ActionForm
            Jm orZb = (Jm) zbData.get(0);
            BeanUtil.copyBeanToBean(zbNames, orZb, form);
            form.setCjrq(String.valueOf(orZb.getCjrq()));
            //明细和主表数据在一起
            //明细OrMapping值对象的每个参数
            //modify by tangchangfu 2014-04-04 无税减免税项目  新增字段 "jmxmjdm","jmxmksrq","jmxmjsrq"
            String mxNames[] =
                {
                "szsmdm", "jsje", "kssl", "jmse", "jmxmdm",
                "yskmdm", "ysjcdm",
                "skssjsrq", "skssksrq", "jmlx","jmxmjdm","jmxmksrq","jmxmjsrq"};
            //将OrMapping值对象转成Map，以便ActionForm能够使用
            ArrayList mxMapData = new ArrayList();
            ResultSet rs = null;//获得总局减免税代码名称 add by tangchangfu 2014-05-02
            for (int i = 0; i < zbData.size(); i++)
            {
                //获得此条明细值
                Jm orMx = (Jm) zbData.get(i);
                Map map = new HashMap();
                //将值对象的值赋给Map
                BeanUtil.copyBeanToMap(mxNames, orMx, map);
                String szsmdm = (String) map.get("szsmdm");
                String szmc = szsmdm.substring(0, 2);
                Vector vMxszsmmc = new Vector();
                Vector vMxszmc = new Vector();
                vMxszsmmc.add("szsmdm ='" + szsmdm + "'");
                vMxszmc.add("szsmdm ='" + szmc + "'");
                List szsmmcData = da.query(Szsm.class, vMxszsmmc);
                Szsm szsm = (Szsm) szsmmcData.get(0);
                List szmcData = da.query(Szsm.class, vMxszmc);
                Szsm szmcs = (Szsm) szmcData.get(0);
                map.put("szsmmc", szsm.getSzsmmc());
                map.put("szmc", szmcs.getSzsmmc());
                map.put("szsmdm_old", map.get("szsmdm"));
                
                //获得总局减免税代码名称 add by tangchangfu 2014-05-02
                String jmslxmcQuerySQL = "select jmslxdm||'-'||jmslxmc descr from dmdb.sb_dm_zjqyjmslx where jmslxdm='"+(String) map.get("jmxmjdm")+"'";
                rs = da.querySQL(jmslxmcQuerySQL);
                if(rs.next()){
                	map.put("jmxmjdm", rs.getString("descr"));
                } //ADD END
                
                mxMapData.add(map);
                
               
            }
            rs.close();//获得总局减免税代码名称 add by tangchangfu 2014-05-02
            
            form.setDataList(mxMapData);
            form.setCjrq(form.getCjrq());
            form.setLrrq(SfTimeUtil.getDateFromDateTime(curTime));
            tempJsStr += jsArray.getArrayByCode("szsmlist", "JM_SZSMDM");
            tempJsStr +=
                jsArray.getMsgsByCode("szsmdm", "JM_SZSM_ALL", form.getDataList());
            form.setScriptStr(tempJsStr);

            return form;
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
        JmssbForm form = (JmssbForm) vo.getData();
        UserData ud = (UserData) vo.getUserData();
        SWDJJBSJ djxx = null;
        try
        {
           /* start added by huxiaofeng 2005.8.16*/
            //djxx = InterfaceDj.getJBSJ(form.getJsjdm(), ud);
             djxx = InterfaceDj.getJBSJ_New(form.getJsjdm(), ud);
           /* end added by huxiaofeng 2005.8.16*/
        }
        catch (Exception ex1)
        {
            throw ExceptionUtil.getBaseException(ex1);
        }
        Timestamp curTime = new Timestamp(System.currentTimeMillis());
        String waringStr = "该计算机机代码填写的第";
        String tempJsStr = jsArray.getArrayByCode("szsmlist_add",
                                                  "ZHSB_SZSMADD");
        tempJsStr += jsArray.getArrayByCode("szsmlist", "JM_SZSMDM");
        tempJsStr +=
            jsArray.getMsgsByCode("szsmdm", "JM_SZSM_ALL", form.getDataList());
        tempJsStr +=jsArray.getMsgsByCode("jmxmjdm", "JM_JMXMJDM",new ArrayList());//add by tangchangfu 2014-05-02 无税减免税项目二期
        form.setScriptStr(tempJsStr);
        String zbName[] =
            {
            "jsjdm", "sbrq", "swdjzh", "swjgzzjgdm",
            "djzclxdm", "gjbzhydm", "lrr", "qxdm"};

        //建立数据库连接
        Connection conn = null;
        try
        {
            conn = SfDBResource.getConnection();
            //使用OrMap操作数据库的封装类
            SfDBAccess da = new SfDBAccess(conn);
            Map zqrlMap = this.getSksssqMap(djxx.getDjzclxdm(),
                                            SfDateUtil.getDate(form.getSbrq()),
                                            conn);
            Map ynsjeMap = InterfaceSf4Sb.getYnsje(form.getJsjdm(),
                SfDateUtil.getDate(form.getSbrq()));
            ServiceProxy proxy = new ServiceProxy();
            BeanUtil.copyBeanToBean(zbName, djxx, form);
            ArrayList mxMapDatas = form.getDataList();
            List mxDatas = new ArrayList();
            for (int i = 0; i < mxMapDatas.size(); i++)
            {
                Map map = (Map) mxMapDatas.get(i);
                String szsmdm = (String) map.get("szsmdm");
                String smdm = szsmdm.substring(0, 2);
                String jmlxs = (String) map.get("jmlx");
                String ts_skssksrq = (String) map.get("skssksrq");
                String ts_skssjsrq = (String) map.get("skssjsrq");

                Timestamp skssksrq = new Timestamp(TinyTools.stringToDate(
                    ts_skssksrq,
                    "yyyyMMdd").getTime());
                Timestamp skssjsrq = new Timestamp(TinyTools.stringToDate(
                    ts_skssjsrq,
                    "yyyyMMdd").getTime());

                if (jmlxs.equals(CodeConstant.JMLX_SP) &&
                    !smdm.equals(CodeConstant.SZSM_YYS)
                    && !this.isYysFjs(szsmdm))
                {
//modified by zhu guanglin for:只有非营业税才需审查资格 2004-03-09
//modified by Shi Yanfeng : 当录入的税目为营业税附加税的时候也不检查其是否有减免资格 2004-03-26
                    Date date = TinyTools.stringToDate(form.getSbrq(),
                        "yyyyMMdd");
                    String jmxmdm = proxy.getJmsbs(form.getJsjdm(), smdm,
                        skssksrq,
                        skssjsrq);
                    if (jmxmdm != null)
                    {
                        ok = "true";
                        map.put("jmxmdm", jmxmdm);
                        com.ttsoft.bjtax.smsb.model.client.Ysjc ysjc = JksUtil.
                            getYsjc(form.
                                    getJsjdm(), smdm,
                                    SfDateUtil.getDate(form.getSbrq()));
                        map.put("ysjcdm", ysjc.getYsjcdm());
                        com.ttsoft.bjtax.jikuai.zwcl.vo.page.Yskm yskm =
                            JKAdapter.
                            getInstance().getYskm(szsmdm,
                                                  djxx.getDjzclxdm(),
                                                  djxx.getGjbzhydm(),
                                                  ysjc.getYsjcdm());
                        map.put("yskmdm", yskm.getYskmdm());

                    }
                    else
                    {
                        bhghc = i + 1;
                        ok = "false";
                        if (time == 0)
                        {
                            waringStr += String.valueOf(bhghc);
                        }
                        else
                        {
                            waringStr += "、" + String.valueOf(bhghc);
                        }
                        time = time + 1;
                    }
                }
                else
                {
                    oks = "true";
                    com.ttsoft.bjtax.smsb.model.client.Ysjc ysjc = JksUtil.
                        getYsjc(form.
                                getJsjdm(), smdm,
                                SfDateUtil.getDate(form.getSbrq()));

                    map.put("ysjcdm", ysjc.getYsjcdm());
                    String jmxmdm = CodeConstant.JM_CY_JMXMDM;
                    map.put("jmxmdm", jmxmdm);
                    com.ttsoft.bjtax.jikuai.zwcl.vo.page.Yskm yskm = JKAdapter.
                        getInstance().getYskm(szsmdm,
                                              djxx.getDjzclxdm(),
                                              djxx.getGjbzhydm(),
                                              ysjc.getYsjcdm());
                    if (yskm != null)
                    {
                        map.put("yskmdm", yskm.getYskmdm());
                    }
                    else
                    {
                        throw ExceptionUtil.getBaseException(new Exception(),
                            "获取预算科目失败!");
                    }
                }
            }
            form.setDataList(mxMapDatas);
            if (bhghc > 0)
            {
                waringStr += "行是不具备审批减免资格的。";
                form.setWarnStr(waringStr);
                List sjList = new ArrayList();
                sjList = this.getJmDate(ynsjeMap, zqrlMap, form);
                tempJsStr += this.getMxJsArray(sjList);
                tempJsStr += jsArray.getArrayByCode("szsmlist", "JM_SZSMDM");
                tempJsStr +=jsArray.getMsgsByCode("szsmdm", "JM_SZSM_ALL",form.getDataList());
                tempJsStr +=jsArray.getMsgsByCode("jmxmjdm", "JM_JMXMJDM",new ArrayList());//add by tangchangfu 2014-05-02 无税减免税项目二期
                form.setScriptStr(tempJsStr);
                return form;
            }
            else
            {
                if ( (oks.equals("true") || ok.equals("true") || ok.equals("")) &&
                    (bhghc == 0))
                {
                    waringStr = "";
                    form.setWarnStr(waringStr);
                }
            }
            Map dateMap = getSbrl(form.getSbrq());

            //删除原先的记录
            try
            {
                //删除明细数据
                da.delete(" qxdm='" + InterfaceDj.getQxdm(ud) +
                          "' and jzbz like '" + CodeConstant.SMSB_JZBZ_EDIT
                          + "%" + "'" +
//                          " and szsmdm!='" + CodeConstant.SZSM_QYSDSCODE + "'" +  //delete by tangchangfu 2014-04-04 无税减免税项目二期
                          "and ( FSDM='" + CodeConstant.FSDM_WSSB +
                          "' OR FSDM='" + CodeConstant.FSDM_SMSB + "') " +
                          " and sbrq >= to_date('" +
                          String.valueOf(dateMap.get("ksrq")).substring(0, 10) +
                          "','yyyy-MM-dd') and sbrq <= to_date('" +
                          String.valueOf(dateMap.get("jsrq")).substring(0, 10) +
                          "','yyyy-MM-dd') " +
                          "and jsjdm='" + form.getJsjdm() + "'"
                          ,
                          new Jm());
            }
            catch (BaseException ex1)
            {
                throw new ApplicationException("数据库操作失败，请您找管理员联系！");
            }
            //主表OrMapping值对象的每个参数
            String zbNames[] =
                {
                "jsjdm", "sbrq","dqxse","dqlrze","qyrs","azrs"};
            //得到明细数据集
            List mxMapData = form.getDataList();
            //存贮明细数据值对象

            List mxData = new ArrayList();
            for (int i = 0; i < mxMapData.size(); i++)
            {
                Map map = (Map) mxMapData.get(i);
                Object skssjsrq = map.get("skssjsrq");
                String nd = ( (String) skssjsrq).substring(0, 4);
                //将主表的数据添加进来
                BeanUtil.copyBeanToMap(zbNames, form, map);
                Jm orMx = new Jm();
                //将数据传递给明细值对象
                BeanUtil.populate(orMx, map);
                Date cjrq = TinyTools.stringToDate(form.getCjrq(),
                    CodeConstant.DATETYPE);
                Timestamp cjrqmx = new Timestamp(cjrq.getTime() + i*1000);
                orMx.setCjrq(cjrqmx);
                orMx.setLrrq(curTime);
                orMx.setNd(nd);
                orMx.setFsdm(CodeConstant.FSDM_SMSB);
                orMx.setJzbz(CodeConstant.SMSB_JZBZ);
                orMx.setDjzclxdm(djxx.getDjzclxdm());
                orMx.setGjbzhydm(djxx.getGjbzhydm());
                orMx.setQxdm(InterfaceDj.getQxdm(ud));
                orMx.setSwjgzzjgdm(djxx.getSwjgzzjgdm());
                orMx.setLrr(ud.getYhid());
                String jmxmjdm = orMx.getJmxmjdm();
                System.out.println("JMXLXDM############(1)"+jmxmjdm);
                if(jmxmjdm != null && !"".equals(jmxmjdm)){
                	int index = jmxmjdm.indexOf("-");
                	if(index>0){
                		System.out.println("JMXLXDM############(2)"+jmxmjdm);
                		orMx.setJmxmjdm(jmxmjdm.substring(0, index));
                	}
                }
                
                System.out.println("JMXLXDM############(3)"+jmxmjdm);

                //add by tangchangfu 2014-04-04 无税减免税项目  start
/*                orMx.setDqlrze(new BigDecimal(form.getDqlrze()));//当期利润总额
                orMx.setDqxse(new BigDecimal(form.getDqxse()));//当期销售额
                orMx.setQyrs(new BigDecimal(form.getQyrs()));//企业人数
                orMx.setAzrs(new BigDecimal(form.getAzrs()));//安置人数
                orMx.setAzbl(new BigDecimal(form.getAzbl()));//安置比例
*/                
                System.out.println("当期利润总额++++++++++++++++++++"+orMx.getDqlrze());
                System.out.println("当期销售额++++++++++++++++++++"+orMx.getDqxse());
                System.out.println("企业人数++++++++++++++++++++"+orMx.getQyrs());
                System.out.println("安置人数++++++++++++++++++++"+orMx.getAzrs());
                orMx.setSjly(CodeConstant.SMSB_SJLY_SBLR);
                //add by tangchangfu 2014-04-04 无税减免税项目  end
                mxData.add(orMx);
            }
            try
            {
                //插入明细表数据
                da.insert(mxData);
            }
            catch (BaseException ex4)
            {
                throw new ApplicationException("数据库操作失败，请您找管理员联系！");
            }
            return null;
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
        //得到Action传递过来ActionForm对象
        JmssbForm form = (JmssbForm) vo.getData();
        UserData ud = (UserData) vo.getUserData();

        //建立数据库连接
        Connection conn = null;
        try
        {
            conn = SfDBResource.getConnection();
            //使用OrMap操作数据库的封装类
            SfDBAccess da = new SfDBAccess(conn);
            Map dateMap = getSbrl(form.getSbrq());
            String Sql = " qxdm='" + InterfaceDj.getQxdm(ud) +
                         "' and jzbz like '" + CodeConstant.SMSB_JZBZ_EDIT
                         + "%" + "'" +
//                         " and szsmdm!='" + CodeConstant.SZSM_QYSDSCODE + "'" +//delete by tangchangfu 2014-04-04 无税减免税项目二期
                         "and ( FSDM='" + CodeConstant.FSDM_WSSB +
                         "' OR FSDM='" + CodeConstant.FSDM_SMSB + "') " +
                         " and sbrq >= to_date('" +
                         String.valueOf(dateMap.get("ksrq")).substring(0, 10) +
                         "','yyyy-MM-dd') and sbrq <= to_date('" +
                         String.valueOf(dateMap.get("jsrq")).substring(0, 10) +
                         "','yyyy-MM-dd') " +
                         "and jsjdm='" + form.getJsjdm() + "'";

            //删除原先的记录
            try
            {
                //删除明细数据
                da.delete(" qxdm='" + InterfaceDj.getQxdm(ud) +
                          "' and jzbz like '" + CodeConstant.SMSB_JZBZ_EDIT
                          + "%" + "'" +
//                          " and szsmdm!='" + CodeConstant.SZSM_QYSDSCODE + "'" + //delete by tangchangfu 2014-04-04 无税减免税项目二期
                          "and ( FSDM='" + CodeConstant.FSDM_WSSB +
                          "' OR FSDM='" + CodeConstant.FSDM_SMSB + "') " +
                          " and sbrq >= to_date('" +
                          String.valueOf(dateMap.get("ksrq")).substring(0, 10) +
                          "','yyyy-MM-dd') and sbrq <= to_date('" +
                          String.valueOf(dateMap.get("jsrq")).substring(0, 10) +
                          "','yyyy-MM-dd') " +
                          "and jsjdm='" + form.getJsjdm() + "'",
                          new Jm());
            }
            catch (BaseException ex1)
            {
                throw new ApplicationException("数据库操作失败，请您找管理员联系！");
            }
            return null;
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
    }

    /**
     * 根据申报日期取得当前月第一天和下月第一天
     * @param curSbrq 申报日期
     * @return dateMap
     */
    private Map getSbrl (String curSbrq)
    {
        Timestamp sbrq = SfTimeUtil.getTimestamp(curSbrq);
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(sbrq);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        Timestamp ksrq = new Timestamp(new GregorianCalendar(year, month, 1).
                                       getTime().getTime());
        Timestamp jsrq = new Timestamp(new GregorianCalendar(year, month + 1, 1).
                                       getTime().getTime());
        Map dateMap = new HashMap();
        dateMap.put("ksrq", ksrq);
        dateMap.put("jsrq", jsrq);
        return dateMap;
    }

    /**
     * 根据征期日历得到所有税种税目的税款所属时期<br>
     * 当前日期的月等于征期起始或截止日期的月
     * 征期截止日期＋1天=限缴日期<br>
     * @param  djzclxdm 登记注册类型
     * @param  rq 申报时间
     * @param  conn 数据库连接
     * @return Zqrl Map 税款所属开始 结束时间，限缴日期，
     * @throws Exception
     */
    private Map getSksssqMap (String djzclxdm, Date rq, Connection conn)
        throws
        Exception
    {
        List ret = new ArrayList();
        try
        {
            //得到连接
            //得到当前年
            String year = String.valueOf(TinyTools.getYear(rq));
            //得到当前月
            String month = String.valueOf(TinyTools.getMonth(rq) + 1);
            //长度为一时补零
            if (month.length() == 1)
            {
                month = "0" + month;
            }

            Vector criteria = new Vector(); //查询条件
            criteria.add("djzclxdm='" + djzclxdm + "'");
            criteria.add("ZQQSRQ<=to_date('" + year + month + "','yyyyMM')");
            criteria.add("ZQZZRQ>=to_date('" + year + month + "','yyyyMM')");

            SfDBAccess db = new SfDBAccess(conn);
            ret = db.query(new Zqrl().getClass(), criteria);
            Map zqrlMap = new HashMap();
            for (int i = 0; i < ret.size(); i++)
            {
                Zqrl zqrl = (Zqrl) ret.get(i);
                zqrlMap.put(zqrl.getSzsmdm(), zqrl);
            }
            return zqrlMap;
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "查询征期日历失败!");
        }
        finally
        {
            //释放连接
            ///SfDBResource.freeConnection(conn);
        }

    }

    /**
     * 根据明细数据列表生成js2维数组<br>
     * @param mxList 明细列表
     * @return String js2维数组
     **/
    private String getMxJsArray (List mxList)
    {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < mxList.size(); i++)
        {
            Map mxData = (Map) mxList.get(i);
            buf.append("[");
            //税种税目代码
            buf.append("\"" + (String) mxData.get("szsmdm") + "\",");
            if (mxData.get("skssksrq") != null)
            {
                //税款所属日期
                buf.append("\""
                           + SfDateUtil.getDate( (Date) mxData.get("skssksrq")) +
                           "\",");
            }
            else
            {
                buf.append("\"" + (String) mxData.get("skssksrq") + "\",");
            }
            if (mxData.get("skssjsrq") != null)
            {
                buf.append("\""
                           + SfDateUtil.getDate( (Date) mxData.get("skssjsrq")) +
                           "\",");
            }
            else
            {
                buf.append("\"" + (String) mxData.get("skssjsrq") + "\",");
            }
            buf.append("],");
        }
        if (buf.length() > 0)
        {
            //如果有数据，则删除最后添加的逗号
            buf.delete(buf.length() - 1, buf.length());
        }
        else
        {
            return "var szsmDate = new Array();";
        }
        buf.append("];");
        buf = SfStringUtils.replaceAll(buf, "null", "");
        return "var szsmDate = [" + buf.toString();
    }

    /**
     * 税种税目所属日期
     * @param ynsjeMap 应纳税金额
     * @param zqrlMap 征期日立
     * @param form 主表数据
     * @return List
     * @throws BaseException
     */
    private List getJmDate (Map ynsjeMap, Map zqrlMap, JmssbForm form)
        throws
        BaseException
    {
        List jmData = new ArrayList();
        try
        {
            List szsmData = CodeManager.getCodeList("JM_ALL_SZSMDM",
                CodeConstants.CODE_MAP_MAPLIST).
                            getRecords();
            //20040401 获得缴纳次数用于计算车访土税款所属日起，Shi Yangfeng
            com.ttsoft.bjtax.sfgl.proxy.ServiceProxy proxy =
                new com.ttsoft.bjtax.sfgl.proxy.ServiceProxy();
            Date sbrq = SfDateUtil.getDate(form.getSbrq());
            Map cdfMap = proxy.getCDFSet(form.getJsjdm(), sbrq, sbrq, sbrq);

            for (int i = 0; i < szsmData.size(); i++)
            {
                Map orSz = (Map) szsmData.get(i);
                Map szsmMap = new HashMap();
                Zqrl zqrl = (Zqrl) zqrlMap.get( (String) orSz.get("szsmdm"));
                String szsmdm = (String) orSz.get("szsmdm");
                String zqlxdm = (String) orSz.get("zqlxdm");
                if (zqrl != null)
                {
                    szsmMap.put("szsmdm", szsmdm);
                    szsmMap.put("skssksrq", zqrl.getZqssrqq());
                    szsmMap.put("skssjsrq", zqrl.getZqssrqz());
                }
                else
                {
                    Map map = (Map) Skssrq.getSksssq(form.getJsjdm(), szsmdm,
                        CodeConstant.SKLXDM_ZCJK,
                        zqlxdm,
                        SfDateUtil.getDate(form.getSbrq()),
                        new BigDecimal(0),
                        new BigDecimal(0), new BigDecimal(0),
                        ynsjeMap);
                    szsmMap.put("szsmdm", szsmdm);
                    szsmMap.put("skssjsrq", (Timestamp) map.get("SKSSJSRQ"));
                    szsmMap.put("skssksrq", (Timestamp) map.get("SKSSKSRQ"));
                }
                //根据缴纳次数修改车房土限缴日期
                this.modifyCft(szsmMap, sbrq, (Map) cdfMap.get("JNCS"));
                jmData.add(szsmMap);
            }
        }
        catch (Exception ex)
        {
            //抛出异常
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex, "系统错误请与管理员联系!");
        }
        finally
        {
            //释放数据库连接
        }
        return jmData;
    }

    /**
     * 判断该税目是否为附加税
     * @param  szsmdm 税种税目代码
     * @return boolean
     */
    private boolean isYysFjs (String szsmdm)
    {
        boolean ret = false;
        List fList = CodeManager.getCodeList("ZHSB_SZSMADD",
                                             CodeConstants.CODE_MAP_MAPLIST).
                     getRecords();
        //得到附加税的map列表{fjsszsmdm=100010, szsmdm=027500}
        for (int i = 0; i < fList.size(); i++)
        {
            Map temp = (Map) fList.get(i);
            if (temp.get("fjsszsmdm").equals(szsmdm) &&
                ( (String) temp.get("szsmdm")).
                substring(0, 2).equals(CodeConstant.SZSM_YYS))
            {
                //是营业税附加税
                return true;
            }
        }

        return ret;
    }

    /**
     * 根据缴纳次数，处理车房土税款所属日期
     * @param  mxData 明细数据
     * @param  jncs 交纳次数
     * @param sbrq 申报日期
     */
    private void modifyCft (Map mxData, Date sbrq, Map jncs)
    {

        String szdm = ( (String) mxData.get("szsmdm")).substring(0, 2);
        int ijncs = 0;
        if (jncs != null && (String) jncs.get(szdm) != null)
        {
            ijncs = Integer.parseInt( (String) jncs.get(szdm));
        }
        if (Skssrq.SZDM_CFT.indexOf(szdm) > 0)
        {
            //  属于车房土税种
            Map temp = Skssrq.getCftSkssrq(sbrq, ijncs);
            mxData.put("skssksrq", (Timestamp) temp.get(Skssrq.SKSSKSRQ));
            mxData.put("skssjsrq", (Timestamp) temp.get(Skssrq.SKSSJSRQ));
        }
    }


}
