/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.wjgr.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.struts.action.ActionForm;
import com.ttsoft.bjtax.dj.DjOuterConstant;
import com.ttsoft.bjtax.dj.model.FB_JBSJ;
import com.ttsoft.bjtax.dj.model.FB_KKQK;
import com.ttsoft.bjtax.dj.model.ZRRJBSJ;
import com.ttsoft.bjtax.dj.model.ZRRYHZH;
import com.ttsoft.bjtax.sfgl.common.code.CodeManager;
import com.ttsoft.bjtax.sfgl.common.code.CodeUtils;
import com.ttsoft.bjtax.sfgl.common.constant.CodeConstants;
import com.ttsoft.bjtax.sfgl.common.db.util.BeanUtil;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.sfgl.common.util.SfStringUtils;
import com.ttsoft.bjtax.shenbao.model.client.SBData;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkmx;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb;
import com.ttsoft.bjtax.shenbao.model.domain.Wbzhrmb;
import com.ttsoft.bjtax.shenbao.model.domain.Zrrgrsdsmx;
import com.ttsoft.bjtax.shenbao.model.domain.Zrrgrsdsz;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.util.Debug;
import com.ttsoft.bjtax.smsb.util.EArray;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.JksUtil;
import com.ttsoft.bjtax.smsb.util.Skssrq;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.bjtax.smsb.wjgr.web.WjgrActionForm;
import com.ttsoft.bjtax.smsb.wjgr.web.WjgrJkswhActionForm;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description: 外籍个人所得税月份申报表</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class WjgrProcessor
    implements Processor
{
    /**
     * 扩展Processor接口
     * @param vo  数据容器传递对象，来自Action
     * @return  数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]
     * @throws com.ttsoft.framework.exception.BaseException
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
                break;
            case CodeConstant.SMSB_QUERYACTION: //查询
                return this.doQuery(vo);
            case CodeConstant.SMSB_SAVEACTION:
                return this.doSave(vo);
            case CodeConstant.SMSB_DELETEACTION:
            	return doMoveToHis(vo);    
            case CodeConstant.SMSB_ZHSB_INITLIST:
                return this.doJkswh(vo);
            case CodeConstant.SMSB_CXJKSACTION:
                return this.doCx(vo);
            default:
                throw new UnsupportedOperationException(
                    "Method process() not yet implemented.");
        }
        return result;
    }

    /**
     * 查询缴款书数据
     * modified by qianchao 2005.10.31
     * @param vo  数据传递值对象
     * @return  数据结果对象[ActionForm]
     * @throws BaseException
     */
    public Object doJkswh (VOPackage vo)
        throws BaseException
    {
        //得到actionForm
        WjgrJkswhActionForm form = (WjgrJkswhActionForm) vo.getData();
        //初始化数据库连接
        Connection conn = null;
        ArrayList ar = new ArrayList();

        SBData sb = null;
        HashMap sbmaps = new HashMap();

        try
        {
            //得到数据库连接
            conn = SfDBResource.getConnection();
            SfDBAccess db = new SfDBAccess(conn);
            //得到申报编号
            String sbbh = form.getSbbh();
            Vector v = new Vector();
            //查询所有缴款书
            v.add("qxdm='" + InterfaceDj.getQxdm(vo.getUserData()) + "'");
            v.add("jsjdm='" + form.getJsjdm() + "'");

            if (sbbh == null || sbbh.equals(""))
            {

                v.add("sjly='" + CodeConstant.SMSB_SJLY_ZRRLR + "'");
                v.add("zwbs like  '" + CodeConstant.SMSB_ZWBS + "%" +
                      CodeConstant.SMSB_ZWBS + "'  order by jkpzh desc ");
            }
            else
            {
                //查询本次申报
                v.add("sbbh='" + sbbh + "' order by jkpzh desc");
            }

            Debug.out("v=" + v);

            List list = db.query(new Sbjkzb().getClass(), v);
            Debug.out("list.size()=" + list.size());

            //格式化查询到的数据，封装到SBData中。
            Sbjkzb zb = null;

            for (int i = 0; i < list.size(); i++)
            {
              zb = (Sbjkzb)list.get(i);

              Debug.out("zb.getJkpzh()=" + zb.getJkpzh());

              sb = (SBData)sbmaps.get(zb.getSbbh());
              if (sb == null)
              {
                sb = new SBData();
                sbmaps.put(zb.getSbbh(),sb);
                ar.add(sb);
              }
              sb.addSbjkzb(zb);
            }


            form.setDataList(ar);
            return form;
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "查询缴款书列表失败!");
        }
        finally
        {
            //释放数据库连接
            SfDBResource.freeConnection(conn);
        }

    }

    /**
     * 撤销缴款书，同时删除自然人表中相应数据
     * modified by qianchao 2005.10.31
     * 原来的实现被完全放弃，
     * 新的实现与 网上申报中的自然人申报的processor一致。
     *
     * @param vo  数据传递值对象
     * @return  数据结果对象[ActionForm]
     * @throws BaseException
     */
    public Object doCx (VOPackage vo)
        throws BaseException
    {
        //得到actionForm
        WjgrJkswhActionForm form = (WjgrJkswhActionForm) vo.getData();

        //作废的申报编号
        String zfSbbh = form.getSbbh();

        String jsjdm = form.getJsjdm();
        UserData ud = vo.getUserData();
        String qxdm = ud.getSsdwdm().substring(0,2);

        String sql = null;
        String sqlZb = null;

        sql = " WHERE QXDM = '" + qxdm
            + "' AND SBBH = '" + zfSbbh + "'";
        sqlZb = " WHERE ZYRQ >= to_date('20040101','yyyymmdd') AND QXDM = '"
            + qxdm + "' AND JSJDM = '" + jsjdm
            + "' AND SBBH = '" + zfSbbh
            + "' AND ((substr(zwbs, 1, 1) = '0') AND (substr(zwbs, 20, 1) = '0' ))";


        String sqlJkpzh = "select count(*) from sbdb.sb_jl_sbjkzb  " +  sql;
        ResultSet rs = null;
        int zbCount = 0;
        int deleteCount[] = null;
        Connection conn = null;
        try
        {
            //获得数据库连接
            conn = SfDBResource.getConnection();
            Statement st = conn.createStatement();
            StringBuffer sqlStringBuffer = new StringBuffer();

            Debug.out("sqlJkpzh=" + sqlJkpzh);

            rs = st.executeQuery(sqlJkpzh);
            rs.next();
            zbCount = rs.getInt(1);
            Debug.out("主表记录数 zbCount=" + zbCount);

            st.clearBatch();

            //定义sql语句，批处理


            //删除缴款明细数据
            sqlStringBuffer.append("DELETE FROM SBDB.SB_JL_SBJKMX ").append(sql);

            Debug.out("1==sqlStringBuffer.toString()=" + sqlStringBuffer.toString());

            st.addBatch(sqlStringBuffer.toString());

            //删除缴款主表数据
            sqlStringBuffer.setLength(0);
            sqlStringBuffer.append("DELETE FROM SBDB.SB_JL_SBJKZB ").append(
                sqlZb);

            Debug.out("2==sqlStringBuffer.toString()=" + sqlStringBuffer.toString());

            st.addBatch(sqlStringBuffer.toString());

            deleteCount = st.executeBatch();

            Debug.out("主表删除记录数 deleteCount[1]=" + deleteCount[1]);
            if (deleteCount[1] != zbCount)
            {
                throw new ApplicationException("要删除的申报已经缴款！");
            }

            sqlStringBuffer.setLength(0);
            st.clearBatch();

            //删除外币折合人民币数据
            sqlStringBuffer.append("DELETE FROM SBDB.SB_JL_WBZHRMB ").append(
                sql);

            Debug.out("3==sqlStringBuffer.toString()=" + sqlStringBuffer.toString());

            st.addBatch(sqlStringBuffer.toString());

            //删除个人所得税明细
            sqlStringBuffer.setLength(0);
            sqlStringBuffer.append("DELETE FROM SBDB.SB_JL_ZRRGRSDSMX ").append(
                sql);

            Debug.out("4==sqlStringBuffer.toString()=" + sqlStringBuffer.toString());

            st.addBatch(sqlStringBuffer.toString());

            //删除个人所得税主表
            sqlStringBuffer.setLength(0);
            sqlStringBuffer.append("DELETE FROM SBDB.SB_JL_ZRRGRSDSZ ").append(
                sql);

            Debug.out("5==sqlStringBuffer.toString()=" + sqlStringBuffer.toString());

            st.addBatch(sqlStringBuffer.toString());

            sqlStringBuffer.setLength(0);

            st.executeBatch(); //执行删除

            return form;
        }
        catch(ApplicationException e)
        {
            throw e;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e, "作废缴款数据失败！");
        }
        finally
        {
            SfDBResource.freeConnection(conn);
        }

    }

    /**
     * 删除自然人申报数据
     * deleted by qianchao 2005.11.1
     * @param zbVo 申报主表数据
     * @param conn 数据库连接
     * @throws BaseException
     */
    /*
    private void doDelete (Zrrgrsdsz zbVo, Connection conn)
        throws
        BaseException
    {
        SfDBAccess db = new SfDBAccess(conn);
        //删除明细表数据
        //Zrrgrsdsmx mx = new Zrrgrsdsmx();
        //mx.setJsjdm(zbVo.getJsjdm());
        //mx.setSbrq(zbVo.getSbrq());
        String sbrq = zbVo.getSbrq().toString();
        //db.delete(mx);
        db.querySQL("delete from sbdb.SB_JL_ZRRGRSDSMX where jsjdm='" +
                    zbVo.getJsjdm() + "' and sbbh='" + zbVo.getSbbh() + "'");
        //删除主表数据
        //db.delete(zbVo);
        db.querySQL("delete from sbdb.SB_JL_ZRRGRSDSZ where jsjdm='" +
                    zbVo.getJsjdm() + "' and sbbh='" + zbVo.getSbbh() + "'");
    }*/

    /**
     * 保存录入数据：保存自然人表，生成缴款书，添加外币申报表
     * @param vo  数据传递值对象
     * @return  数据结果对象[ActionForm]
     * @throws BaseException
     */
    public Object doSave (VOPackage vo)
        throws BaseException
    {
        //得到actionForm
        WjgrActionForm form = (WjgrActionForm) vo.getData();
        //得到userData
        UserData userData = vo.getUserData();
        //根据actionform生成主表值对象
        //外籍个人所得税申报主数据
        Zrrgrsdsz zbVo = new Zrrgrsdsz();
        //对应form属性名称
        String[] names =
            {
            "jsjdm", "sbrq", "zjlxdm", "zjhm", "gjdm", "skssksrq",
            "skssjsrq",
            "dhrq", "jnzz", "zydm", "fwdw", "fwdd", "dwfdbl"};
        //得到当前时间
        Timestamp now = new Timestamp( (new java.util.Date()).getTime());
        //初始化数据库连接
        Connection conn = null;
        try
        {

            //得到数据库连接
            conn = SfDBResource.getConnection();
            SfDBAccess db = new SfDBAccess(conn);
            //将form相应的属性复制给vo
            BeanUtil.copyBeanToBean(names, form, zbVo);
            //通过登记接口取得纳税人相关信息
            ZRRJBSJ jbsj = InterfaceDj.getZRRJBSJ(zbVo.getJsjdm());
//      ZRRJBSJ jbsj = InterfaceDj.getZRRJBSJ(zbVo.getZjlxdm(), zbVo.getZjhm(),
//                                            zbVo.getGjdm());

            //重设主表的申报时间为申报时间添加秒
            //zbVo.setSbrq(new Timestamp(TinyTools.getDayTime(zbVo.getSbrq()).getTime()));
            //补充userData信息
            //创建人
            if (userData != null)
            {
                zbVo.setCjr(userData.getYhid());
            }
            else
            {
                zbVo.setCjr("smsb");
            }
            //创建时间
            //zbVo.setCjsj(now);
            zbVo.setCjrq(now);
            //录入时间
            zbVo.setLrrq(now);
            //区县代码
            zbVo.setQxdm(InterfaceDj.getQxdm(userData));
            //税务机关组织机构代码
            zbVo.setSwjgzzjgdm(jbsj.getSwjgzzjgdm());
            //联系电话
            zbVo.setDh(jbsj.getZzdh());
            //生成主表数据
            Debug.out(zbVo);
            //db.insert(zbVo);

            //保存明细数据生成缴款书
            List mxList = new ArrayList();
            //名细数据处理
            ArrayList list = form.getDataList();
            HashMap bzMap = new HashMap();
            for (int i = 0; i < list.size(); i++)
            {
                //得到明细map
                HashMap map = (HashMap) list.get(i);


                Debug.out("map=" + map);

                String bzmx = (String) map.get("bzmx");

                //将明细map数据设置到明细值对象
                Zrrgrsdsmx orMx = new Zrrgrsdsmx();
                BeanUtil.populate(orMx, map);

                Debug.out("orMx.getJe()=" + orMx.getJe());
                Debug.out("orMx.getRmbhj()=" + orMx.getRmbhj());
                Debug.out("orMx.getYbtsk()=" + orMx.getYbtsk());
                Debug.out("orMx.getYjzgsk()=" + orMx.getYjzgsk());
                Debug.out("orMx.getYkjse()=" + orMx.getYkjse());
                Debug.out("orMx.getYnssde()=" + orMx.getYnssde());
                Debug.out("orMx.getYnse()=" + orMx.getYnse());

                //保存币种明细数据
                bzMap.put(orMx.getSzsmdm(), bzmx);
                //补充明晰信息
                //创建时间
                orMx.setCjrq(zbVo.getCjrq());
                //录入日期
                orMx.setLrrq(zbVo.getLrrq());
                //计算机代码
                orMx.setJsjdm(zbVo.getJsjdm());
                //申报日期
                orMx.setSbrq(zbVo.getSbrq());
                //区县代码
                orMx.setQxdm(zbVo.getQxdm());
                //税务机关组织机构代码
                orMx.setSwjgzzjgdm(zbVo.getSwjgzzjgdm());
                //生成明细数据
                //db.insert(orMx);
                //添加明细列表
                mxList.add(orMx);
            }

            //生成缴款书
            ArrayList retList = (ArrayList)this.creatJks(zbVo, mxList, vo);
            form.setDataList(retList);
            String sbbh = form.getSbbh();

            Debug.out("sbbh=" + sbbh);

            //插入主表数据
            //补充申报编号
            zbVo.setSbbh(sbbh);
            db.insert(zbVo);
            //插入明细数据
            for (int i = 0; i < mxList.size(); i++)
            {
                Zrrgrsdsmx orMx = (Zrrgrsdsmx) mxList.get(i);
                //补充申报编号
                orMx.setSbbh(sbbh);
                //保存币种
                this.saveBz(bzMap, orMx, db);
                //生成明细数据
                db.insert(orMx);
            }
            return form;
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "保存申报数据失败!");
        }
        finally
        {
            //释放数据库连接
            SfDBResource.freeConnection(conn);
        }

    }
    
    private Boolean doMoveToHis (VOPackage vo) throws BaseException{
        //获得UserData
        UserData ud = vo.getUserData();
        Map moveMap = (Map)vo.getData();
        String jsjdm = (String)moveMap.get("jsjdm");
        List jkpzhList = (List)moveMap.get("jkpzhList");
        try{  
        	JksUtil ju = new JksUtil();
        	ju.moveSbjkToHis(jsjdm, jkpzhList, ud.getYhid());
        }catch (Exception ex){
            //throw ExceptionUtil.getBaseException(ex, "系统错误请与管理员联系!");
            throw ExceptionUtil.getBaseException(ex);
        }
        
    	return new Boolean(true);
    }

    /**
     * 查询数据
     * @param vo  数据传递值对象
     * @return  数据结果对象[ActionForm]
     * @throws BaseException
     */
    public Object doQuery (VOPackage vo)
        throws BaseException
    {
        WjgrActionForm form = (WjgrActionForm) vo.getData();
        EArray jsArray = new EArray();
        String tempJsStr = "";
        tempJsStr +=
            jsArray.getMsgsByCode("szsmdm", "WJGR_SZSMDM",
                                  (ArrayList) form.getDataList());
        try
        {
            //设置纳税人基本信息
            this.setInitInfo(form, vo.getUserData());

            tempJsStr += this.getSzsmJsStr(form);
            //得到币种使用的JS数组
            //得到下拉列表使用的js数组
            tempJsStr += this.getWbhsJsArray(this.getWbhsList(form));
            tempJsStr +=
                jsArray.getMsgsByCode("bzdm", "BZ",
                                      (ArrayList) form.getDataList());

            form.setScriptStr(tempJsStr);
//      //设置纳税人基本信息
//      this.setInitInfo(form, vo.getUserData());
        }
        catch (BaseException ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
        return form;
    }

    /**
     * 通过登记接口设置纳税人基本信息
     * @param  actionForm 外籍个人actionform
     * @param  userData 操作员信息
     * @throws BaseException 当纳税人信息不存在的时候抛出异常
     */
    private void setInitInfo (ActionForm actionForm, UserData userData)
        throws
        BaseException
    {
        WjgrActionForm form = (WjgrActionForm) actionForm;
        //通过登记接口得到纳税人基本信息
        try
        {
            //ZRRJBSJ jbsj = InterfaceDj.getZRRJBSJ(form.getJsjdm());
//      HashMap zrrInfo = InterfaceDj.getZrrInfo(form.getZjlxdm(), form.getZjhm(),
//                                               form.getGjdm());
            /*使用自然人的计算机代码作为查询条件 20040303 Shi Yanfeng*/
//      HashMap zrrInfo = InterfaceDj.getZrrInfo(form.getZjlxdm(), form.getZjhm(),
//                                               form.getGjdm(), userData);
            HashMap zrrInfo = (HashMap) InterfaceDj.getZRRInfo(form.getJsjdm(),
                userData);
            //自然人基本信息
            ZRRJBSJ jbsj = (ZRRJBSJ) zrrInfo.get(DjOuterConstant.ZRRJBSJ);
            if (jbsj == null)
            {
                throw new ApplicationException("不存在该纳税人信息或者您无权操作该计算机代码！");
            }
            //扣款情况
            List kkqkList = (List) zrrInfo.get(DjOuterConstant.ZRRKKQK);
            //银行账号信息
            List bankList = (List) zrrInfo.get(DjOuterConstant.ZRRYHZH);
            bankList = this.modifyBankList(bankList);
            //设置银行账号信息
            form.setBankList(bankList);
            //租房费扣除额
            BigDecimal zffkce = new BigDecimal(0);
            if (kkqkList != null)
            {
                for (int i = 0; i < kkqkList.size(); i++)
                {
                    //合计租房费扣除额
                    FB_KKQK temp = (FB_KKQK) kkqkList.get(i);
                    if (temp.getKcxmdm().equals("1"))
                    {
                        zffkce = zffkce.add(temp.getKcje());
                    }
                }
            }
            //设置租房费扣除额
            form.setZffkcs(zffkce.toString());
//      ZRRJBSJ jbsj = InterfaceDj.getZRRJBSJ(form.getZjlxdm(), form.getZjhm(),
//                                            form.getGjdm());

//    //自然人附加信息
            FB_JBSJ fbJbsj = (FB_JBSJ) zrrInfo.get(DjOuterConstant.ZRRFB);
            //计算机代码
//      form.setJsjdm(jbsj.getJsjdm());

            //证件类型
            form.setZjlxdm(jbsj.getZjlxdm());
            //证件号码
            form.setZjhm(jbsj.getZjhm());
            //纳税人名称
            form.setNsrmc(jbsj.getNsrmc());
            //设置国家代码
            form.setGjdm(jbsj.getGjdm());
            //设置国家名称
            form.setGjmc(jbsj.getGjmc());
            //抵华日期
            if (jbsj.getDhrq() != null)
            {
                form.setDhrq(SfDateUtil.getDate(jbsj.getDhrq()));
            }
            else
            {
                form.setDhrq("");
            }
            //职业
            form.setZydm(jbsj.getZydm());
            form.setZymc(CodeUtils.getCodeBeanLabel("ZYDM", jbsj.getZydm()));
            //地址
            form.setJnzz(jbsj.getTxdz());
            if (fbJbsj != null)
            {
                //该纳税人存在附加信息
                //税款负担方式
                form.setFdfs(fbJbsj.getSkfdqk());
                //分配比率
                if (fbJbsj.getSkfdbl() != null)
                {
                    form.setDwfdbl(String.valueOf(fbJbsj.getSkfdbl().
                                                  doubleValue()));
                }
//        //常驻标示
//        if (fbJbsj.getCzbs().equals("0")) {
//          form.setSfczbs("否");
//        }
//        else {
//          form.setSfczbs("是");
//        }
                form.setSfczbs(fbJbsj.getCzbs());
            }
            else
            {
                form.setFdfs("");
                form.setDwfdbl("");
                form.setSfczbs("");
            }

            //取得税款所属日期
            Map skssrq = Skssrq.monthSkssrq(SfDateUtil.getDate(form.getSbrq()));
            form.setSkssjsrq(SfDateUtil.getDate( (Date) skssrq.get(Skssrq.
                SKSSJSRQ)));
            form.setSkssksrq(SfDateUtil.getDate( (Date) skssrq.get(Skssrq.
                SKSSKSRQ)));

        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }

    }

    /**
     * 登记有什么银行、账号我们就显示什么，
     * 但在下拉菜单中有个空的银行和账号，可供操作人员选择，如果选择了空则打印为空；
     * @param list 银行账号列表
     * @return List
     */
    private List modifyBankList (List list)
    {
        ZRRYHZH yhzh = new ZRRYHZH();
        list.add(yhzh);
        return list;
    }

    /**
     * 生成前台显示用js数组
     * @param actionForm 外籍个人actionform
     * @throws BaseException
     * @return String
     */
    private String getSzsmJsStr (ActionForm actionForm)
        throws BaseException
    {
        String jsStr = "\nvar szsmlist_fields = [\"szsmdm\",\"szsmmc\",\"szsmdm_old\",\"sl\",\"ynsqss\",\"ynszzs\",\"sskcs\",\"sdksrq\",\"sdjsrq\",\"jfye\",\"bhsdsqs\",\"bhsdszz\",\"bhsdssl\",\"bhsdskcs\"];";
        try
        {
            List szsmList = this.getSzsmList(actionForm);
            jsStr += "\n" + this.getSzsmJsArray(szsmList);
            //Debug.out(szsmList);
        }
        catch (BaseException ex)
        {
            throw ExceptionUtil.getBaseException(ex, "生成前台显示用js数组失败!");
        }
        return jsStr;
    }

    /**
     * 生成税种税目list
     * @param actionForm 外籍个人actionform
     * @throws BaseException
     * @return List
     */
    private List getSzsmList (ActionForm actionForm)
        throws BaseException
    {
        WjgrActionForm form = (WjgrActionForm) actionForm;
        List ret = new ArrayList();
        //通过代码表得到税种税目代码
        try
        {
            List szsm = CodeManager.getCodeList("WJGRSDS",
                                                CodeConstants.CODE_MAP_MAPLIST).
                        getRecords();
            //根据税种税目列表，生成map列表
            String[] name =
                {
                "szsmdm", "szsmmc", "szsmdm_old", "sl", "ynsqss",
                "ynszzs", "sskcs",
                "skssksrq", "skssjsrq"};
            for (int i = 0; i < szsm.size(); i++)
            {
                //得到税种税目值对象
                Map szsmVo = (Map) szsm.get(i);
                szsmVo.put("szsmdm_old", szsmVo.get("szsmdm"));
                //取得税款所属日期
                Map skssrq = Skssrq.monthSkssrq(SfDateUtil.getDate(form.getSbrq()));
                szsmVo.put("sdksrq",
                           SfDateUtil.getDate( (Date) skssrq.get(Skssrq.
                    SKSSKSRQ)));
                szsmVo.put("sdjsrq",
                           SfDateUtil.getDate( (Date) skssrq.get(Skssrq.
                    SKSSJSRQ)));
                //减费用额
                String szsmdmSub = ( (String) szsmVo.get("szsmdm"));
                if (szsmdmSub.length() > 4)
                {
                    szsmdmSub = szsmdmSub.substring(0, 4);
                }
                //if (szsmdmSub.equals("0501")) {
                if (szsmdmSub.equals(CodeConstant.ZRR_SZSMDM_GXSD))
                {
                    if (form.getGjdm().equals("CHN"))
                    {
                        //中国人时，减费用额=1000
                        szsmVo.put("jfye", CodeConstant.ZRR_JFYE_CHN);
                    }
                    else
                    {
                        //非中国人，减费用额=4000
                        szsmVo.put("jfye", CodeConstant.ZRR_JFYE_ELSE);
                    }
                }
                else
                {
                    szsmVo.put("jfye", CodeConstant.ZRR_JFYE_NORMAL);
                }
                ret.add(szsmVo);
            }
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "生成税种税目列表数据失败!");
        }
        return ret;
    }

    /**
     * 得到明细的js数组
     * @param szsm 税种税目列表
     * @return String
     */
    private String getSzsmJsArray (List szsm)
    {
        StringBuffer ret = new StringBuffer();
        try
        {
            for (int i = 0; i < szsm.size(); i++)
            {
                Map temp = (Map) szsm.get(i);
                ret.append("[");
                //税种税目代码
                ret.append("\"" + temp.get("szsmdm") + "\",");
                //税种税目名称
                ret.append("\"" + temp.get("szsmmc") + "\",");
                ret.append("\"" + temp.get("szsmdm_old") + "\",");
                //税率
                ret.append("\"" + temp.get("sl") + "\",");
                //应纳税起始数
                ret.append("\"" + temp.get("ynsqss") + "\",");
                //应纳税终止数
                ret.append("\"" + temp.get("ynszzs") + "\",");
                //速算扣除数
                ret.append("\"" + temp.get("sskcs") + "\",");
                //取得税款所属日期
                ret.append("\"" + temp.get("sdksrq") + "\",");
                ret.append("\"" + temp.get("sdjsrq") + "\",");
                //减费用额
                ret.append("\"" + temp.get("jfye") + "\",");
                //不含税所得
                ret.append("\"" + temp.get("bhsdsqs") + "\",");
                ret.append("\"" + temp.get("bhsdszz") + "\",");
                //不含税所得税率
                ret.append("\"" + temp.get("bhsdssl") + "\",");
                //不含税所得速算扣除数
                ret.append("\"" + temp.get("bhsdskcs") + "\"");
                ret.append("],");
            }
            if (ret.length() > 0)
            {
                //如果有数据，则删除最后添加的逗号
                ret.delete(ret.length() - 1, ret.length());
            }
            ret.append("];");
            return "var szsmlist = [" +
                SfStringUtils.replaceAll(ret.toString(), "null", "");

        }
        catch (Exception ex)
        {
            return "var szsmlist=new Array();";
        }
    }

    /**
     * 得到上月的外汇换算信息列表
     * @param actionForm 外籍个人actionform
     * @return List
     * @throws BaseException
     */
    private List getWbhsList (ActionForm actionForm)
        throws BaseException
    {
        //WjgrActionForm form = (WjgrActionForm) actionForm;
        //得到申报日期
        //查询该日期的上月的外汇牌价
        Date rq = TinyTools.addMonth( -1, new Date());
        //得到年
        int year = TinyTools.getYear(rq);
        //得到月
        int month = TinyTools.getMonth(rq) + 1;
        List ret = new ArrayList();
        Connection conn = null;
        try
        {
            conn = SfDBResource.getConnection();
            SfDBAccess db = new SfDBAccess(conn);

//      Vector v = new Vector();
//      v.add("nd='"+year+"' and yf='"+month+"'");
//      ret = db.query(new Wbhs().getClass(), v);
            ResultSet rs = db.querySQL("select a.BZDM bzdm,a.bzmc bzmc, b.WHPJ whpj from dmdb.GY_DM_BZ a, dmdb.SB_DM_WBHS b where a.BZDM=b.BZDM(+) and nd(+)='" +
                                       year + "' and yf(+)='" + month +
                                       "' order by a.XSSX ");
            while (rs.next())
            {
                Map temp = new HashMap();
                //币种代码
                temp.put("bzdm", rs.getString("bzdm"));
                //币种名称
                temp.put("bzmc", rs.getString("bzmc"));
                //外汇牌价
                temp.put("whpj", rs.getBigDecimal("whpj"));
                ret.add(temp);
            }
            return ret;
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "得到上月的外汇换算信息列表失败!");
        }
        finally
        {
            SfDBResource.freeConnection(conn);
        }

    }

    /**
     * 得到外币换算的js数组
     * @param  wbhs 外币换算List
     * @return String
     */
    private String getWbhsJsArray (List wbhs)
    {
        String jsStr =
            "\nvar bzlist_fields = [\"bzdm\",\"bzmc\",\"whpj\",\"bzdm_old\"];";
        StringBuffer ret = new StringBuffer();
        //ret.append(jsStr);
        for (int i = 0; i < wbhs.size(); i++)
        {
            Map temp = (Map) wbhs.get(i);
            ret.append("[");
            ret.append("\"" + temp.get("bzdm") + "\",");
            ret.append("\"" + temp.get("bzmc") + "\",");
            ret.append("\"" + temp.get("whpj") + "\",");
            ret.append("\"" + temp.get("bzdm") + "\"");
            ret.append("],");
        }
        if (ret.length() > 0)
        {
            //如果有数据，则删除最后添加的逗号
            ret.delete(ret.length() - 1, ret.length());
        }
        ret.append("];");
        String retStr = "\nvar bzlist = [" +
                        SfStringUtils.replaceAll(ret.toString(), "null", "");
        retStr = jsStr + retStr;
        //return "\nvar bzlist = [" +
        //    SfStringUtils.replaceAll(ret.toString(), "null", "");
        return retStr;

    }

    /**
     * 生成缴款书
     * @param zbVo 主表数据
     * @param mxList 明系列表
     * @param vo 数据集对象
     * @return List 生成的缴款书信息列表
     * @throws BaseException
     */
    private List creatJks (Zrrgrsdsz zbVo, List mxList, VOPackage vo)
        throws
        BaseException
    {
        //得到actionForm
        WjgrActionForm form = (WjgrActionForm) vo.getData();
        //ormapping对象
        Sbjkzb orObj = new Sbjkzb();
        //获得UserData
        UserData ud = vo.getUserData();
        //定义与form种的columns 一样的变量。其中的名称为名细的字段名
        String names[] =
            {
            "jsjdm", "sbrq", "lrrq", "cjrq", "qxdm"};
        Timestamp now = new Timestamp( (new java.util.Date()).getTime());
        try
        {

            //将form中对应主表信息保存到值对象
            BeanUtil.copyBeanToBean(names, zbVo, orObj);
            //通过登记接口取得纳税人相关信息
            //ZRRJBSJ jbsj = InterfaceDj.getZRRJBSJ(zbVo.getJsjdm());
            //补充主表信息
            //设置银行账号
            orObj.setYhdm(form.getYhdm());
            orObj.setYhmc(form.getYhmc());
            orObj.setZh(form.getZh());
            //自然人申报缴款，当国籍为中国时登记注册类型为410，当国籍为非中国时登记注册类型为390
            /*******************************************/
            /**              Shi Yanfeng              **/
            /* 20040107*/
            /*******************************************/
            if (zbVo.getGjdm().equals(CodeConstant.GJDM_CHN))
            {
                //当国籍为中国时登记注册类型为410
                //登记注册类型
                orObj.setDjzclxdm(CodeConstant.ZRR_JKS_DJZCLXDM);
            }
            else
            {
                //当国籍为非中国时登记注册类型为390
                orObj.setDjzclxdm(CodeConstant.ZRR_JKS_DJZCLXDM_UNCN);
            }
            //国家标准行业代码
            orObj.setGjbzhydm(CodeConstant.ZRR_JKS_GJBZHYDM);
            //税务机关组织机构
            orObj.setSwjgzzjgdm(zbVo.getSwjgzzjgdm());
            //征收机关
            orObj.setZsswjgzzjgdm(zbVo.getSwjgzzjgdm());
            //隶属关系
            //orObj.setLsgxdm(jbsj.getLsgxdm());
            //经营地址联系电话
            orObj.setJydzlxdm(zbVo.getDh());
            //录入人
            orObj.setLrr("smsb");
            if (ud != null)
            {
                orObj.setLrr(ud.getYhid());
            }
            //税款类型
            orObj.setSklxdm(CodeConstant.SKLXDM_ZCJK);
            orObj.setSklxmc(CodeConstant.SKLXMC_ZCJK);
            //申报方式代码
            orObj.setFsdm(CodeConstant.FSDM_SMSB);
            //数据来源
            orObj.setSjly(CodeConstant.SMSB_SJLY_ZRRLR);
            //申报缴款明细列表
            List list = new ArrayList();
            //生成申报缴款明细列表
            for (int i = 0; i < mxList.size(); i++)
            {
                //得到自然人申报明细
                Zrrgrsdsmx orMx = (Zrrgrsdsmx) mxList.get(i);
                //生成申报缴款明细
                Sbjkmx sbjkmx = new Sbjkmx();
                //计算机代码
                sbjkmx.setJsjdm(orMx.getJsjdm());
                //税种税目代码
                sbjkmx.setSzsmdm(orMx.getSzsmdm());
                //税种代码
                sbjkmx.setSzdm(orMx.getSzsmdm().substring(0, 2));
                //计税金额
                sbjkmx.setJsje(orMx.getYnssde());
                //实缴税额  modified by zhangyj 20131225 BUG修改
                //sbjkmx.setSjse(orMx.getYnse());
                sbjkmx.setSjse(orMx.getYbtsk());
                //税款所属日期
                sbjkmx.setSkssjsrq(orMx.getSdjsrq());
                sbjkmx.setSkssksrq(orMx.getSdksrq());
                //设置税率
                sbjkmx.setSl(orMx.getSl());
                //设置税务机关组织机构代码
                sbjkmx.setSwjgzzjgdm(zbVo.getSwjgzzjgdm());
                Debug.out(sbjkmx);

                Debug.out("sbjkmx.getRkje()=" + sbjkmx.getRkje());
                Debug.out("sbjkmx.getSzsmdm()=" + sbjkmx.getSzsmdm());
                Debug.out("sbjkmx.getSjse()=" + sbjkmx.getSjse());
                //添加进明细列表
                list.add(sbjkmx);
            }

            //对数据进行分票保存
            JksUtil ju = new JksUtil();
            //start modifying by qianchao 2005.11.1
            //通过jksutil的getsbbh取得申报表号，存到form中。
            Debug.out("form.getJksType()=" + form.getJksType());
            List retlist = ju.getJkDataZRR(orObj, list, form.getJksType());
            form.setSbbh(ju.getSbbh());
            return retlist;

            //end modifying by qianchao 2005.11.1
            //return null;
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "系统错误请与管理员联系!");
        }

    }

    /**
     * 得到申报编号
     *
     * deleted by qianchao 2005.11.1
     * 申报编号sbbh可以从form中取到了。
     *
     * @param  mxList 明系列表
     * @return String 生成的缴款书信息列表
     */
    /*
    private String getSbbh (List mxList)
    {
        String sbbh = "";
        DeclareInfor dclInfo = new DeclareInfor();
        if (mxList.size() > 0)
        {

          Debug.out("mxList.get(0).getClass().getName()=" + mxList.get(0).getClass().getName());
            dclInfo = (DeclareInfor) mxList.get(0);
            Sbjkzb temp = dclInfo.getSbjkzb();
            sbbh = temp.getSbbh();
        }
        return sbbh;
    }*/

    /**
     * 保存外币列表
     * @param bzMap 币种列表
     * @param orMx 明细
     * @param db 数据库连接
     * @throws BaseException
     */
    private void saveBz (HashMap bzMap, Zrrgrsdsmx orMx, SfDBAccess db)
        throws
        BaseException
    {
        //得到该明细的币种
        String bzmx = (String) bzMap.get(orMx.getSzsmdm());
        if (bzmx == null || bzmx.length() <= 0)
        {
            //没有录入外币则返回
            return;
        }

        try
        {
            String[] mx = SfStringUtils.split(bzmx, "#");
            for (int i = 0; i < mx.length; i++)
            {

                //得到明细数据
                Map map = SfStringUtils.splitString(mx[i]);
                //生成明细数据值对象
                Wbzhrmb wb = new Wbzhrmb();
                BeanUtil.populate(wb, map);
                //补充明细数据
                //计算机代码
                wb.setJsjdm(orMx.getJsjdm());
                wb.setCjrq(orMx.getCjrq());
                wb.setLrrq(orMx.getLrrq());
                wb.setSbbh(orMx.getSbbh());
                wb.setSbrq(orMx.getSbrq());
                wb.setSdjsrq(orMx.getSdjsrq());
                wb.setSdksrq(orMx.getSdksrq());
                wb.setSwjgzzjgdm(orMx.getSwjgzzjgdm());
                wb.setSzsmdm(orMx.getSzsmdm());
                wb.setQxdm(orMx.getQxdm());

                db.insert(wb);
            }
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "系统错误请与管理员联系!");
        }

    }

    /**
     * 删除外币列表
     * deleted by qianchao 2005.11.1
     * @param zbVo 主表
     * @param conn  数据库连接
     * @throws BaseException
     */
    /*
    private void deleteBz (Zrrgrsdsz zbVo, Connection conn)
        throws
        BaseException
    {
        SfDBAccess db = new SfDBAccess(conn);
        //删除外币数据
        db.querySQL("delete from sbdb.SB_JL_WBZHRMB where jsjdm='"
                    + zbVo.getJsjdm() +
                    "' and sbbh='" + zbVo.getSbbh() + "'");
    }*/

}
