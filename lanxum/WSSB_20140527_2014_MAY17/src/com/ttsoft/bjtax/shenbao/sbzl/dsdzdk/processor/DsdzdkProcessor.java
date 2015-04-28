package com.ttsoft.bjtax.shenbao.sbzl.dsdzdk.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.text.NumberFormat;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Vector;
import java.util.HashMap;

import com.ekernel.db.or.ORManager;
import com.ekernel.db.or.ORContext;

import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

import com.ttsoft.bjtax.shenbao.constant.CodeConstant;
import com.ttsoft.bjtax.shenbao.model.client.DeclareInfor;
import com.ttsoft.bjtax.shenbao.model.client.SdwszsbhzCx;
import com.ttsoft.bjtax.shenbao.model.domain.Dsdzdkmx;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkmx;
import com.ttsoft.bjtax.shenbao.model.domain.Sdwszsbhz;
import com.ttsoft.bjtax.shenbao.sbzl.dsdzdk.DsdzdkActionConstant;
import com.ttsoft.bjtax.shenbao.sbzl.dsdzdk.DsdzdkConstant;
import com.ttsoft.bjtax.shenbao.service.processor.InterFaceProcessor;
import com.ttsoft.bjtax.shenbao.util.DBResource;
import com.ttsoft.bjtax.shenbao.util.FindObjInList;
import com.ttsoft.common.util.Debug;
import com.ttsoft.bjtax.shenbao.zhsb.processor.ZhsbProcessor;
import com.ttsoft.bjtax.shenbao.util.TinyTools;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.dj.model.YHZH;
import com.ttsoft.bjtax.dj.proxy.ServiceProxy;
import com.ttsoft.framework.exception.*;
import com.ttsoft.bjtax.sfgl.common.model.*;

/**
 * <p>Title: 北京地税综合管理系统  申报征收模块</p>
 * <p>Description:代售代征代扣processor </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: TTSOFT</p>
 * @author stone
 * @version 1.0 2003-9-9
 */

public class DsdzdkProcessor implements Processor
{
    public DsdzdkProcessor()
    {
    }

    //数据来源为导入
    private static final String SJLY_DR = "1";

    //ORSession标识
    private static final long SESSION_ID = 0;

    /**
     * 保存代售代征代扣明细数据
     * @param vo 前台过来的VOPackage
     * @return 有待确认
     * @throws BaseException 操作异常
     */
    public Object process(VOPackage vo) throws BaseException
    {
        if(vo.getAction() == DsdzdkActionConstant.ACTION_SAVESBMX)
        {
            return doSaveSbData(vo);
        }
        if(vo.getAction() == DsdzdkActionConstant.ACTION_QUERYHZ)
        {
            return doQueryHz(vo);
        }
        if(vo.getAction() == DsdzdkActionConstant.ACTION_ERASEHZ)
        {
            return doEraseHz(vo);
        }
        if(vo.getAction() == DsdzdkActionConstant.ACTION_PRINT)  //打印请求
        {
            return doPrint(vo);
        }
        throw new SystemException("no such mothod");
    }

    //保存三代的明细数据并汇总填写缴款主表和明细生成汇总数据
    private List doSaveSbData(VOPackage vo) throws BaseException
    {
        List dsdzdkmxList = (List)vo.getData();
        if(dsdzdkmxList == null)
        {
            throw ExceptionUtil.getBaseException(new Exception("参数错误!"));
        }

        Connection conn = null;//数据库连接
        ORManager ormgr = null;//OR实例
        try
        {
            Timestamp cjrq = new Timestamp((new Date()).getTime());
            Dsdzdkmx dsdzdkmx = null;
            //进行数据汇总填写缴款主表和明细表
            List dsdzdkmxListTmp = new ArrayList(dsdzdkmxList);
            List DeclareList = (List)createJkdata(dsdzdkmxList);
            dsdzdkmxList = dsdzdkmxListTmp;
            String sql = "select sbdb.seq_sb_hzdh.nextval from dual";

            conn = DBResource.getConnection();//得到连接
            ormgr = DBResource.getORManager();//得到or实例
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            //汇总单号
            long hzdhindex =  rs.getLong("nextval");
            NumberFormat nmbFormat = new DecimalFormat("00000000");
            String hzdh     =  nmbFormat.format(hzdhindex);
            //创建汇总表并插入数据库中
            List hzList = CreateHzData(DeclareList,hzdh);
            for(int i=0; i<hzList.size(); i++)
            {
                ormgr.makePersistent(SESSION_ID,conn,hzList.get(i));
            }
            Sbjkzb sbjkzb = ((DeclareInfor)DeclareList.get(0)).getSbjkzb();
            String qxdm = sbjkzb.getQxdm();  //区县代码
            for(int i=0;i<dsdzdkmxList.size(); i++)
            {
                dsdzdkmx = (Dsdzdkmx)dsdzdkmxList.get(i);
                dsdzdkmx.setSbhzdh(hzdh);
                dsdzdkmx.setCjrq(cjrq);
                dsdzdkmx.setLrrq(cjrq);
                dsdzdkmx.setQxdm(qxdm);
                dsdzdkmx.setSjly(SJLY_DR);  //数据来源
                ormgr.makePersistent(SESSION_ID,conn,dsdzdkmx);
            }
            return hzList;
        }
        catch(Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex,"插入代售代征代扣明细数据失败!");
        }
        finally
        {
            DBResource.destroyConnection(conn);
        }
    }

    //创建缴款主表和明细数据并加入数据库
    private Object createJkdata(List dsdzdkmxList) throws BaseException
    {
        String jsjdm = ( (Dsdzdkmx)dsdzdkmxList.get(0)).getJsjdm();
        List param = new ArrayList();
        param.add("getSzsmdm");
        //按照szsmdm拆分成以拥有相同szsmdm的list组成的list
        List dsdzdkmxBySzsmdmList = null;
        //相同szsmdm的List
        List   sameszsmdmList  = null;
        BigDecimal jsjetotal   = null;
        BigDecimal sjsetotal   = null;
        Dsdzdkmx   dsdzdkmxTmp = null;
        Sbjkmx     sbjkmx      = null;
        List       sbjkmxList  = new ArrayList();

        //得到此计算机代码的登记信息并填写主子表值对象的对应字段
        ServiceProxy serviceProxy = new ServiceProxy();
        Map map = serviceProxy.getDjInfo(jsjdm);
        SWDJJBSJ swdjjbsj = (SWDJJBSJ)map.get("JBSJ");

        // 获得申报编号
        ZhsbProcessor zhsbPro = new ZhsbProcessor();
        String sbbh;
        //started added by qianchao 2005-12-8
        try
        {
            sbbh = zhsbPro.getSbbh(jsjdm);
        }
        catch (Exception e)
        {
            throw ExceptionUtil.getBaseException(e,"取申报表号失败!");
        }
        //ended   added by qianchao 2005-12-8
        
        //分组
        dsdzdkmxBySzsmdmList =
            FindObjInList.splitListByParam(dsdzdkmxList, Dsdzdkmx.class, param);
        //取当前年度
        Timestamp now = new Timestamp(System.currentTimeMillis());
        String nd = new SimpleDateFormat("yyyy").format(now);

        //生成缴款明细数据
        for(int i = 0; i < dsdzdkmxBySzsmdmList.size(); i++)
        {
            sbjkmx = new Sbjkmx();
            sameszsmdmList = (List)dsdzdkmxBySzsmdmList.get(i);

            jsjetotal = new BigDecimal("0");
            sjsetotal = new BigDecimal("0");

            for(int j = 0; j < sameszsmdmList.size(); j++)
            {
                dsdzdkmxTmp = (Dsdzdkmx)sameszsmdmList.get(j);
                if(dsdzdkmxTmp.getJsje() != null)
                {
                    jsjetotal = jsjetotal.add(dsdzdkmxTmp.getJsje());
                }
                if(dsdzdkmxTmp.getSjse() != null)
                {
                    sjsetotal = sjsetotal.add(dsdzdkmxTmp.getSjse());
                }
            }
            //填写申报明细数据
            sbjkmx.setJsjdm(dsdzdkmxTmp.getJsjdm());
            sbjkmx.setJsje(jsjetotal);
            sbjkmx.setSjse(sjsetotal);
            sbjkmx.setRkje(sjsetotal);  //入库金额
            sbjkmx.setSkssjsrq(dsdzdkmxTmp.getSkssjsrq());
            sbjkmx.setSkssksrq(dsdzdkmxTmp.getSkssksrq());
            sbjkmx.setSzdm(dsdzdkmxTmp.getSzdm());
            sbjkmx.setSzsmdm(dsdzdkmxTmp.getSzsmdm());
            sbjkmx.setZqlxdm(CodeConstant.ZQLXDM_MONTH);
            sbjkmx.setSwjgzzjgdm(swdjjbsj.getSwjgzzjgdm());  //税务机关组织机构
            sbjkmx.setNd(nd);
            sbjkmx.setCjrq(now);  //创建日期
            sbjkmx.setLrrq(now);  //录入日期
            sbjkmx.setQxdm(swdjjbsj.getSwjgzzjgdm().substring(0,2)); //区县代码
            sbjkmxList.add(sbjkmx);   //添加到缴款明细数组中
        }

        //缴款主表数据
        Sbjkzb sbjkzb = new Sbjkzb();
        sbjkzb.setJsjdm(jsjdm);
        sbjkzb.setLrrq(now);
        sbjkzb.setCjrq(now);
        sbjkzb.setDjzclxdm(swdjjbsj.getDjzclxdm());
        sbjkzb.setFsdm(CodeConstant.FSDM_WSSB);    //征收方式为网上方式
        sbjkzb.setGjbzhydm(swdjjbsj.getGjbzhydm());
        sbjkzb.setLrr(dsdzdkmxTmp.getLrr());
        sbjkzb.setLsgxdm(swdjjbsj.getLsgxdm());
        sbjkzb.setSbrq(TinyTools.second2Day(now));
        sbjkzb.setZyrq(sbjkzb.getSbrq());
        sbjkzb.setSklxdm(CodeConstant.SKLXDM_SDJJ);
        sbjkzb.setSwjgzzjgdm(swdjjbsj.getSwjgzzjgdm());

        com.ttsoft.bjtax.sfgl.proxy.ServiceProxy sf = new com.ttsoft.bjtax.sfgl.proxy.ServiceProxy();
        Sfxy sfxy = sf.getSfxyInfo(jsjdm, new Date());
        if (sfxy != null)
        {
            sbjkzb.setYhdm(sfxy.getYhdm()); //银行代码
            sbjkzb.setYhmc(sfxy.getYhmc()); //银行名称
            sbjkzb.setZh(sfxy.getZh()); //银行账号

            sbjkzb.setClbjdm(CodeConstant.CLBJDM_DHK);  //待划款
        }
        else
        {
            List banks = (List)map.get("YHZH");
            if (banks.size() > 0)
            {
                YHZH yhzh = (YHZH)banks.get(0);
                sbjkzb.setYhdm(yhzh.getYhdm()); //银行代码
                sbjkzb.setYhmc(yhzh.getKhyhmc()); //银行名称
                sbjkzb.setZh(yhzh.getZh()); //银行账号
            }

            sbjkzb.setClbjdm(CodeConstant.CLBJDM_YSB);  //已申报
        }

        sbjkzb.setZsswjgzzjgdm(swdjjbsj.getSwjgzzjgdm());
        sbjkzb.setSjly(CodeConstant.SJLY_SB_SDHZ); //数据来源
        sbjkzb.setNd(nd);
        sbjkzb.setQxdm(swdjjbsj.getSwjgzzjgdm().substring(0, 2));

        DeclareInfor declareInfor = new DeclareInfor(sbjkzb, sbjkmxList);
        declareInfor.setIsReturnPaymentInfo(true);  //返回数据标识
        declareInfor.setPrintTag(CodeConstant.PRINT_YPYS); //凭证类型，一票一税

        //调用综合申报processor生成缴款主表和明细数据，产生缴款凭证记录
        return zhsbPro.createJkInfor(declareInfor, sbbh);
    }

    //创建汇总数据
    private List CreateHzData(List jkpzhInforList,String hzdh)
    {
        //得到缴款数据
        Sbjkzb sbjkzb = null;
        Sdwszsbhz sdwszsbhz = null;
        List sdwszsbhzList = new ArrayList();
        //汇总时间
        Timestamp hzrq = new Timestamp(System.currentTimeMillis());
        String nd = new SimpleDateFormat("yyyy").format(hzrq);

        for(int i=0; i<jkpzhInforList.size(); i++)
        {
            Debug.out(jkpzhInforList.get(i).getClass());
            sbjkzb = ((DeclareInfor)jkpzhInforList.get(i)).getSbjkzb();
            sdwszsbhz = new Sdwszsbhz();
            sdwszsbhz.setClbjdm(CodeConstant.CLBJDM_YSB);
            sdwszsbhz.setJsjdm(sbjkzb.getJsjdm());
            sdwszsbhz.setHzjsrq(hzrq);
            sdwszsbhz.setHzksrq(hzrq);
            sdwszsbhz.setHzrq(hzrq);
            sdwszsbhz.setJkpzh(sbjkzb.getJkpzh());
            sdwszsbhz.setLrr(sbjkzb.getLrr());
            sdwszsbhz.setSbhzdh(hzdh);
            sdwszsbhz.setSjly(SJLY_DR);
            sdwszsbhz.setSjse(sbjkzb.getSjje());
            sdwszsbhz.setSwjgzzjgdm(sbjkzb.getSwjgzzjgdm());
            sdwszsbhz.setSbbh(sbjkzb.getSbbh());
            sdwszsbhz.setSjly(SJLY_DR);  //数据来源
            sdwszsbhz.setNd(nd);
            sdwszsbhz.setCjrq(hzrq);
            sdwszsbhz.setLrrq(hzrq);
            sdwszsbhz.setQxdm(sbjkzb.getSwjgzzjgdm().substring(0, 2));

            sdwszsbhzList.add(sdwszsbhz);
        }
        return sdwszsbhzList;
    }

    /**
     * 撤消汇总单
     * @param vo VOPackage
     * @return List
     * @throws BaseException
     */
    private List doQueryHz(VOPackage vo) throws BaseException
    {
        List hzDataList = null;
        String jsjdm = (String)vo.getData();
        Connection conn = null; //数据库连接
        ORManager ormgr = null; //OR实例
        try
        {
            //取区县代码
            ServiceProxy serviceProxy = new ServiceProxy();
            Map map = serviceProxy.getDjInfo(jsjdm);
            SWDJJBSJ swdjjbsj = (SWDJJBSJ)map.get("JBSJ");
            String qxdm = (String)swdjjbsj.getSwjgzzjgdm().substring(0,2);

            //过滤出已经有缴款记录的申报编号，帐页日期或者缴款日期不为空
            SimpleDateFormat simpleDataFormat = new SimpleDateFormat("yyMM");
            String rq = simpleDataFormat.format(new Date());  //当前年月:YYYY-MM-DD
            String sqlW = "QXDM = '" + qxdm + "' AND JSJDM = '" + jsjdm
                + "' AND (substr(zwbs, 1, 1) <> '0' or substr(zwbs, 20, 1) <> '0')"
//                + " AND substr(to_char(SBRQ,'yyyymmdd'),3,4) = '" + rq + "'"  //不限制为当期数据！！！！！
                + " AND SJLY = '" + CodeConstant.SJLY_SB_SDHZ
                + "' AND FSDM = '"+CodeConstant.FSDM_WSSB+"'";
            String sqlQuery = "SELECT DISTINCT SBBH FROM SBDB.SB_JL_SBJKZB WHERE " + sqlW;
            //根据三代明细，区分上门申报和网上申报的三代数据
            String sdmxQuery = "SELECT DISTINCT SBHZDH FROM SBDB.SB_JL_DSDZDKMX "
                + "WHERE QXDM = '" + qxdm + "' AND JSJDM='" + jsjdm
                +"' AND SJLY='" + SJLY_DR + "' AND FSDM='" +CodeConstant.FSDM_WSSB+"'";
            //如果某一个申报编号下的数据，存在缴款记录，不管是否全部缴款，都不许再修改维护，过滤掉这样的数据
            String sqlWhere = "QXDM = '" + qxdm + "'"
                + " AND SBHZDH IN ("+sdmxQuery+") AND SBBH NOT IN ("+sqlQuery+")"
//                + " AND substr(to_char(HZRQ,'yyyymmdd'),3,4) = '" + rq + "'" //不限制为当期数据！！！！
                + " AND SJLY = '" + SJLY_DR + "' AND JSJDM = '" + jsjdm + "'";
            conn = DBResource.getConnection(); //得到连接
            ormgr = DBResource.getORManager(); //得到or实例
            Vector criteria = new Vector();
            criteria.add(sqlWhere);
            ORContext orctx = new ORContext(Sdwszsbhz.class.getName(),criteria);
            hzDataList= ormgr.query(SESSION_ID,conn,orctx);
            if(hzDataList.size() >0)
            {
                return createErase(hzDataList);
            }
            else
            {
                return hzDataList;
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex, "得到汇总数据失败!");
        }
        finally
        {
            DBResource.destroyConnection(conn);
        }
    }

    //撤消汇总数据
    private Boolean doEraseHz(VOPackage vo) throws BaseException
    {
        Connection conn = null; //数据库连接
        try
        {
            HashMap tempData = (HashMap)vo.getData();
            String sbbh = (String)tempData.get(DsdzdkConstant.KEY_SBBH);
            String jsjdm = sbbh.substring(0,8);
            String sbhzdh = (String)tempData.get(DsdzdkConstant.KEY_SBHZDH);
            String qxdm = (String)tempData.get(DsdzdkConstant.KEY_QXDM);
            //得到连接
            conn = DBResource.getConnection(DBResource.DB_SHENBAO);

            //删除明细和主表数据
            StringBuffer sqlBuffer = new StringBuffer();
            sqlBuffer.append("AND SBBH = '").append(sbbh).append("'");
            String wheresql = sqlBuffer.toString(); //删除条件

            StringBuffer jkbSql = new StringBuffer();
            jkbSql.append("DELETE FROM SBDB.SB_JL_SBJKMX WHERE QXDM = '")
                .append(qxdm).append("' ").append(sqlBuffer);
            //删除缴款明细
            String sqlString = jkbSql.toString();
            PreparedStatement sqlStatement = conn.prepareStatement(sqlString);
            sqlStatement.execute();

            jkbSql.setLength(0);
            jkbSql.append("DELETE FROM SBDB.SB_JL_SBJKZB WHERE QXDM = '")
                .append(qxdm).append("' AND JSJDM = '").append(jsjdm)
                .append("' ").append(sqlBuffer);
            //删除缴款主表
            sqlString = jkbSql.toString();
            sqlStatement = conn.prepareStatement(sqlString);
            sqlStatement.execute();


            //删除三代明细数据
            sqlBuffer.setLength(0);
            sqlBuffer.append("delete from sbdb.sb_jl_dsdzdkmx where qxdm = '")
                .append(qxdm).append("' and sbhzdh='").append(sbhzdh).append("'");
            sqlString = sqlBuffer.toString();
            sqlStatement = conn.prepareStatement(sqlString);
            sqlStatement.execute();
            //删除三代汇总数据
            sqlBuffer.setLength(0);
            sqlBuffer.append(
                "delete from sbdb.sb_jl_sdwszsbhz where qxdm = '")
                .append(qxdm).append("' and sbhzdh='").append(sbhzdh).append("'");
            sqlString = sqlBuffer.toString();
            sqlStatement = conn.prepareStatement(sqlString);
            sqlStatement.execute();
            return Boolean.TRUE;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex,"撤消汇总数据失败!");
        }
        finally
        {
            DBResource.destroyConnection(conn);
        }
    }
    //创建撤消数据
    private List createErase(List hzdInforList) throws BaseException
     {
         List cxhzdhList = new ArrayList();
         //按照汇总单号进行拆分
         List param = new ArrayList();
         param.add("getSbhzdh");
         List splitList = null;
         List tmphzdhList = null;
         Sdwszsbhz tmpSdwszsbhz = null;
         SdwszsbhzCx tmpsdwszsbhzCx = null;
         BigDecimal tmpsjjehz = new BigDecimal("0.00");
         try
         {
             splitList = FindObjInList.splitListByParam(hzdInforList, Sdwszsbhz.class, param);
             boolean eraseable = true;
             for(int i=0; i<splitList.size(); i++)
             {
                 tmpsjjehz = new BigDecimal("0.00");
                 eraseable = true;
                 tmphzdhList = (List)splitList.get(i);
                 for(int j=0; j<tmphzdhList.size(); j++)
                 {
                     tmpSdwszsbhz = (Sdwszsbhz)tmphzdhList.get(j);
                     if(!(tmpSdwszsbhz.getClbjdm().equals(CodeConstant.CLBJDM_WCL)
                        || tmpSdwszsbhz.getClbjdm().equals(CodeConstant.CLBJDM_YSB)))
                   {
                       eraseable = false;
                       break;
                   }
                   tmpsjjehz = tmpsjjehz.add(tmpSdwszsbhz.getSjse());
                 }
                 if(eraseable)
                 {
                     tmpsdwszsbhzCx = new SdwszsbhzCx();
                     tmpsdwszsbhzCx.setHzrq(tmpSdwszsbhz.getHzrq());
                     tmpsdwszsbhzCx.setSbhzdh(tmpSdwszsbhz.getSbhzdh());
                     tmpsdwszsbhzCx.setSjjehz(tmpsjjehz);
                     tmpsdwszsbhzCx.setSdwszsbhzList(tmphzdhList);
                     tmpsdwszsbhzCx.setJkpzhNum(tmphzdhList.size());
                     cxhzdhList.add(tmpsdwszsbhzCx);
                 }
             }
             return cxhzdhList;
         }
         catch(Exception ex)
         {
             throw ExceptionUtil.getBaseException(ex,"拆分数据失败!");
         }
     }

     /**
      * 返回申报编号对应的缴款书数据
      * @param vo VOPackage
      * @return map
      * @throws BaseException
      */
     private HashMap doPrint(VOPackage vo) throws BaseException
     {
         HashMap dataMap = new HashMap(); //返回的map对象
         //定义数据库连接
         Connection conn = null;
         try
         {
             String sbbh = (String)((Map)(vo.getData())).get(DsdzdkConstant.KEY_SBBH);
             String jsjdm = sbbh.substring(0,8);
             String qxdm = (String)((Map)(vo.getData())).get(DsdzdkConstant.KEY_QXDM);
             if(sbbh == null || sbbh.equals(""))
                 throw new ApplicationException("申报编号不能为空！请确认要打印的缴款数据！");

             //获得数据库连接
             conn = DBResource.getConnection(DBResource.DB_SHENBAO);
             //获得 ORManager
             ORManager orManager = DBResource.getORManager(DBResource.OR_SHENBAO);
             //查询申报缴款主表的查询结果
             ArrayList zbResult = new ArrayList();
             //查询申报缴款明细表的查询结果
             ArrayList mxResult = new ArrayList();
             //该申报编号对应的缴款书都没有缴款，所以，不需要判断
             //（来源：1，直接申报得到；2，通过上一步查询得到，已经过滤了存在缴款记录的sbbh）
             String sqlWhere = "(QXDM = '" + qxdm + "' AND JSJDM = '" + jsjdm
                 + "' AND SBBH = '" + sbbh
                 + "' AND SJLY = '"+CodeConstant.SJLY_SB_SDHZ+"') ORDER BY JKPZH";
             Vector criteria = new Vector();
             criteria.add(sqlWhere);
             ORContext orCtx = new ORContext(Sbjkzb.class.getName(), criteria);
             zbResult = (ArrayList)orManager.query(SESSION_ID, conn, orCtx); //查询申报缴款主表
             if(zbResult.size() == 0)
             {
                 return null; //没有可以维护的申报数据！
             }
             //拼申报明细表的查询where条件
             StringBuffer sqlStrBuf = new StringBuffer();
             sqlStrBuf.append("(QXDM = '").append(qxdm);
             sqlStrBuf.append("' AND SBBH = '").append(sbbh)
                 .append("') ORDER BY SBBH DESC, JKPZH");
             String sqlString = sqlStrBuf.toString();
             Vector criteriaMx = new Vector();
             criteriaMx.add(sqlString);
             ORContext orCtxMx = new ORContext(Sbjkmx.class.getName(), criteriaMx);
             mxResult = (ArrayList)orManager.query(SESSION_ID, conn, orCtxMx); //查询申报缴款明细表

             // 接着对取回的数据进行格式封装处理
             ZhsbProcessor zhsbPro = new ZhsbProcessor();
             dataMap = (HashMap)zhsbPro.convertResult(zbResult, mxResult);

             return dataMap; //返回结果数据
         }
         catch(Exception e)
         {
             e.printStackTrace();
             throw ExceptionUtil.getBaseException(e);
         }
         finally
         {
             DBResource.destroyConnection(conn);
         }
     }
}
