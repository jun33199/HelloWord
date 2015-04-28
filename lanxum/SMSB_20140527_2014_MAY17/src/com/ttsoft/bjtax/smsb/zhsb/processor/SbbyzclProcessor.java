package com.ttsoft.bjtax.smsb.zhsb.processor;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkmx;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkmx_His;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb_His;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.util.Arith;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.SBStringUtils;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.bjtax.smsb.zhsb.web.SbInfo;
import com.ttsoft.bjtax.smsb.zhsb.web.SbbyzclForm;
import com.ttsoft.bjtax.smsb.zhsb.web.SingleSbInfo;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.CodeConstants;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: 北京地税核心征管系统-上门申报</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2005</p>
 *
 * <p>Company: Ha Zhengze</p>
 *
 * @author Ha Zhengze
 * @version 1.0
 */
public class SbbyzclProcessor implements Processor {

    /**
     * 实现Processor接口
     * @param vo 业务参数
     * @return Object VOPackage型数据
     * @throws BaseException 业务异常
     */
    public Object process(VOPackage vo) throws com.ttsoft.framework.exception.
            BaseException {
        switch (vo.getAction()) {
        case 0:
            return doShow(vo);
        case 1:
            return doQueryDj(vo);
        case 2:
            return doQuerySb(vo);
        case 3:
            return doQueryA(vo);
        case 4:
            return doQueryB(vo);
        case 5:
            return doMoveA(vo);
        case 6:
            return doMoveB(vo);
        case 7:
            return doSelectMoveA(vo);
        case 8:
            return doSelectMoveB(vo);
        case 9:
            return doQueryMoveA(vo);
        case 10:
            return doQueryMoveB(vo);
        case 11:
            return doDetail(vo);      
        default:
            return null;
        }
    }

    /**
     * 初始化
     * @param vo 业务参数
     * @return SbbyzclForm 值对象
     * @throws BaseException 业务异常
     */
    private SbbyzclForm doShow(VOPackage vo) throws BaseException {
        //0.句柄定义
        SbbyzclForm form = null;
        Connection conn = null;
        //1.输入参数检查
        /**
         * @todo doShow().输入参数检查
         */
        //2.初始化
        ///2.0.获取页面值对象
        form = (SbbyzclForm) vo.getData(); //从前端获得map对象
        ///2.1.得到userdata
        UserData userData = vo.getUserData();
        ///2.2.得到区县代码
        String qxdm = InterfaceDj.getQxdm(userData);
        //3.业务流程
        try {
            ///3.0.获取数据库连接
            conn = SfDBResource.getConnection();
            ///3.1.处理页面初始化对象
            /**
             * @todo doShow().处理页面初始化对象
             */
        }
        ///3.98.捕捉异常并处理
        catch (Exception ex) {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex, "初始化失败!");
        }
        ///3.99.释放资源
        finally {
            SfDBResource.freeConnection(conn);
        }
        //99.返回值
        return form;
    }

    /**
     * 查询登记信息
     * @param vo 业务参数
     * @return SbbyzclForm 值对象
     * @throws BaseException 业务异常
     */
    private SbbyzclForm doQueryDj(VOPackage vo) throws BaseException {
        //0.句柄定义
        SbbyzclForm form = null;
        Connection conn = null;
        SfDBAccess da = null;
        ResultSet rs = null;
        StringBuffer sb = null;
        String sql = null;
        //1.输入参数检查
        /**
         * @todo doQueryDj().输入参数检查
         */
        //2.初始化
        ///2.0.获取页面值对象
        form = (SbbyzclForm) vo.getData(); //从前端获得map对象
        ///2.1.得到userdata
        UserData userData = vo.getUserData();
        ///2.2.得到区县代码
        String qxdm = InterfaceDj.getQxdm(userData);
        //3.业务流程
        try {
            ///3.0.获取数据库连接
            conn = SfDBResource.getConnection();
            da = new SfDBAccess(conn);
            ///3.1.查询登记数据（根据不同级别的用户查询数据范围不同）
            sb = new StringBuffer();
            sb.append("SELECT dj.swjgzzjgdm zgswjgzzjgdm,(select t.swjgzzjgmc from dmdb.gy_dm_swjgzzjg t where t.swjgzzjgdm=dj.swjgzzjgdm) zgswjgzzjgmc,dj.nsrmc,dj.nsrzt,(SELECT t.nsrztmc FROM DMDB.DJ_DM_NSRZT t WHERE t.nsrztdm=dj.nsrzt) nsrztmc FROM DJDB.DJ_JL_JBSJ dj WHERE ");
            sb.append(" dj.jsjdm=");
            sb.append(SBStringUtils.getSQLStr(form.getQueryJsjdm()));
            sb.append(" and ");
            sb.append(this.getSwjgzzjgAuthSQL(userData));
            sql = sb.toString();
            this.debug(sql);
            rs = da.querySQL(sql);
            if (rs.next()) {
                form.setNsrmc(rs.getString("nsrmc"));
                form.setNsrzt(rs.getString("nsrztmc"));
                form.setZgswjgzzjgdm(rs.getString("zgswjgzzjgdm"));
                form.setZgswjgzzjgmc(rs.getString("zgswjgzzjgmc"));
                form.setParJsjdm(form.getQueryJsjdm());
                form.setOpeFlag("1");
            } else {
                form.setNsrmc("");
                form.setNsrzt("");
                form.setOpeFlag("0");
                form.setOpeMessage("没有查询到登记基本信息！" + form.getQueryJsjdm());
            }
            rs.close();
        }
        ///3.98.捕捉异常并处理
        catch (Exception ex) {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex, "查询登记基本信息失败！");
        }
        ///3.99.释放资源
        finally {
            SfDBResource.freeConnection(conn);
        }
        //99.返回值
        return form;
    }

    /**
     * 查询申报信息
     * @param vo 业务参数
     * @return SbbyzclForm 值对象
     * @throws BaseException 业务异常
     */
    private SbbyzclForm doQuerySb(VOPackage vo) throws BaseException {
        //0.句柄定义
        SbbyzclForm form = null;
        Connection conn = null;
        SfDBAccess da = null;
        ResultSet rs = null;
        StringBuffer sb = null;
        String sql = null;
        //1.输入参数检查
        /**
         * @todo doQuerySb().输入参数检查
         */
        //2.初始化
        ///2.0.获取页面值对象
        form = (SbbyzclForm) vo.getData(); //从前端获得map对象
        ///2.1.得到userdata
        UserData userData = vo.getUserData();
        ///2.2.得到区县代码
        String qxdm = InterfaceDj.getQxdm(userData);
        //3.业务流程
        try {
            ///3.0.获取数据库连接
            conn = SfDBResource.getConnection();
            da = new SfDBAccess(conn);
            ///3.1.查询该户全部税款类型为正常且未入库数据或税款类型为正常但入库金额和申报金额不一致的申报缴款数据（当前日期大于限缴日期）
            ////3.1.0.生成查询字符串
            sb = new StringBuffer();
            sb.append("SELECT JKPZH,SKLXDM");
            sb.append(",(SELECT t.sklxmc FROM dmdb.kj_dm_sklx t where a.sklxdm=t.sklxdm) SKLXMC");
            sb.append(",JSJDM,FSDM,LSGXDM,YHDM,YHMC,ZH,DJZCLXDM,SWJGZZJGDM,ZSSWJGZZJGDM,GJBZHYDM,GKZZJGDM,YSKMDM,YSJCDM");
            sb.append(",SZDM,(SELECT t.szsmmc FROM dmdb.sb_dm_szsm t where a.szdm=t.szsmdm) SZMC");
            sb.append(",LRRQ,SBRQ,JKSJ,TO_CHAR(XJRQ,'YYYYMMDD') XJRQ,CLBJDM,SJJE,ZYRQ,RKJE,ZWBS,HXRDM,HXRMC,LRR,BZ,HXRQ,SBBH,JYDZLXDM,SKSSKSRQ,SKSSJSRQ,SJLY,ND,CJRQ,QXDM,SPHM,TO_CHAR(SYSDATE,'YYYYMMDD') XTRQ ");
            sb.append(" FROM SBDB.SB_JL_SBJKZB a");
            sb.append(" WHERE 1=1 AND to_char(sysdate,'yyyymmdd')>to_char(xjrq,'yyyymmdd')");
            sb.append(" and substr(zwbs, 1, 1) = '0'");
            sb.append(" and substr(zwbs, 20, 1) = '0'");
            sb.append(" and sjly in ('11','17','19','39')");
            sb.append(" and fsdm <> '"+CodeConstant.SMSB_FSDM_ZNJ+"' ");
            sb.append(" and jsjdm=");
            sb.append(SBStringUtils.getSQLStr(form.getQueryJsjdm()));
            sb.append(" and (sklxdm in ('100','110','400') or sklxdm like'20%')");
            if (!"".equals(SBStringUtils.killNull(form.getQueryKsrq()))) {
                sb.append(" and zyrq>=");
                sb.append(SBStringUtils.getSQLDate(form.getQueryKsrq()));
            }
            if (!"".equals(SBStringUtils.killNull(form.getQueryJsrq()))) {
                sb.append(" and zyrq<=");
                sb.append(SBStringUtils.getSQLDate(form.getQueryJsrq()));
            }
            sb.append(" and nd=");
            sb.append(SBStringUtils.getSQLStr(form.getQueryNd()));
            sb.append(" order by sklxdm,szdm");
            sql = sb.toString();
			//System.out.println("qq--------------------"+sql);
            ////3.1.1.执行查询获取该户全部查询条件内的申报数据
            this.debug(sql);
            rs=da.querySQL(sql);
            ////3.1.2.处理查询结果，此处处理原则为最小单位为单笔记录
            /////3.1.2.0.临时句柄申明
            Map sbMap=new HashMap();//临时申报数据保存容器，key=sbbh，内容为SingleSbInfo对象
            SingleSbInfo ssi=null;
            SbInfo si=null;
            List mxList=null;
            String sbbh;
            Arith arith=null;
            /////3.1.2.1.处理数据
            while(rs.next()){
               sbbh=rs.getString("sbbh") ;
               if(sbMap.get(sbbh)!=null){
                   //
                   ssi=(SingleSbInfo)sbMap.get(sbbh);
                   //
                   ssi.setSjje(arith.add(rs.getString("SJJE"),ssi.getSjje()));
                   //ssi.setRkje("0.00");
                   ssi.setCe(arith.sub(ssi.getSjje(),ssi.getRkje()));
                   //
                   si=new SbInfo();
                   si.setJkpzh(rs.getString("JKPZH"));
                   si.setSzdm(rs.getString("SZDM"));
                   si.setSzmc(rs.getString("SZMC"));
                   si.setSkssksrq(SBStringUtils.getStrFromDate(rs.getTimestamp("SKSSKSRQ")));
                   si.setSkssjsrq(SBStringUtils.getStrFromDate(rs.getTimestamp("SKSSJSRQ")));
                   si.setSjje(rs.getBigDecimal("SJJE").toString());
                   si.setRkje("0.00");
                   si.setZyrq(SBStringUtils.getStrFromDate(rs.getTimestamp("ZYRQ")));
                   si.setCe(arith.sub(si.getSjje(),si.getRkje()));
                   si.setBz(null);
                   ssi.getMxList().add(si);
               }else{
                   //
                   ssi=new SingleSbInfo();
                   ssi.setSbbh(sbbh);
                   ssi.setSklx(rs.getString("SKLXMC"));
                   ssi.setSbrq(SBStringUtils.getStrFromDate(rs.getTimestamp("SBRQ")));
                   ssi.setSjje(rs.getString("SJJE"));
                   ssi.setRkje("0.00");
                   ssi.setCe(arith.sub(ssi.getSjje(),ssi.getRkje()));
                   ssi.setBz(null);
                   ssi.setXjrq(rs.getString("XJRQ"));  
                   //申报表号、缴款凭证号、申报日期、税款类型、税种代码、税款所属时期、申报金额、帐页日期、入库金额
                   si=new SbInfo();
                   si.setJkpzh(rs.getString("JKPZH"));
                   si.setSzdm(rs.getString("SZDM"));
                   si.setSzmc(rs.getString("SZMC"));
                   si.setSkssksrq(SBStringUtils.getStrFromDate(rs.getTimestamp("SKSSKSRQ")));
                   si.setSkssjsrq(SBStringUtils.getStrFromDate(rs.getTimestamp("SKSSJSRQ")));
                   si.setSjje(rs.getString("SJJE"));
                   si.setRkje("0.00");
                   si.setZyrq(SBStringUtils.getStrFromDate(rs.getTimestamp("ZYRQ")));
                   si.setCe(arith.sub(si.getSjje(),si.getRkje()));
                   si.setBz(null);
                   ssi.getMxList().add(si);
                   //
                   sbMap.put(sbbh,ssi);
               }
            }
            rs.close();
            /////3.1.2.2.加入页面数据集
            Iterator it=sbMap.keySet().iterator();
            while(it.hasNext()){
                form.getDataList().add(sbMap.get(it.next()));
            }
        }
        ///3.98.捕捉异常并处理
        catch (Exception ex) {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex, "初始化失败!");
        }
        ///3.99.释放资源
        finally {
            SfDBResource.freeConnection(conn);
        }
        //99.返回值
        return form;
    }

    /**
     * A类查询
     * @param vo 业务参数
     * @return SbbyzclForm 值对象
     * @throws BaseException 业务异常
     */
    private SbbyzclForm doQueryA(VOPackage vo) throws BaseException {
        //0.句柄定义
        SbbyzclForm form = null;
        Connection conn = null;
        SfDBAccess da = null;
        Vector v=new Vector();
        Sbjkzb sbjkzb=null;
        Sbjkmx sbjkmx=null;
        Sbjkzb_His sbjkzb_his=null;
        Sbjkmx_His sbjkmx_his=null;
        String tailSQL=null;
        //1.输入参数检查
        /**
         * @todo doMoveB().输入参数检查
         */
        //2.初始化
        ///2.0.获取页面值对象
        form = (SbbyzclForm) vo.getData(); //从前端获得map对象
        ///2.1.得到userdata
        UserData userData = vo.getUserData();
        ///2.2.得到区县代码
        String qxdm = InterfaceDj.getQxdm(userData);
        ///2.3.获取需处理的申报编号

        //3.业务流程
        try {
            ///3.0.获取数据库连接
            conn = SfDBResource.getConnection();
            da = new SfDBAccess(conn);
            ///3.1.处理页面初始化对象
        }
        ///3.98.捕捉异常并处理
        catch (Exception ex) {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex, "重复申报处理失败!");
        }
        ///3.99.释放资源
        finally {
            SfDBResource.freeConnection(conn);
        }
        //99.返回值
        return form;

    }

    /**
     * B类查询
     * @param vo 业务参数
     * @return SbbyzclForm 值对象
     * @throws BaseException 业务异常
     */
    private SbbyzclForm doQueryB(VOPackage vo) throws BaseException {
        //0.句柄定义
        SbbyzclForm form = null;
        Connection conn = null;
        //1.输入参数检查
        /**
         * @todo doQueryB().输入参数检查
         */
        //2.初始化
        ///2.0.获取页面值对象
        form = (SbbyzclForm) vo.getData(); //从前端获得map对象
        ///2.1.得到userdata
        UserData userData = vo.getUserData();
        ///2.2.得到区县代码
        String qxdm = InterfaceDj.getQxdm(userData);
        //3.业务流程
        try {
            ///3.0.获取数据库连接
            conn = SfDBResource.getConnection();
            ///3.1.处理页面初始化对象
            /**
             * @todo doQueryB().处理页面初始化对象
             */
        }
        ///3.98.捕捉异常并处理
        catch (Exception ex) {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex, "初始化失败!");
        }
        ///3.99.释放资源
        finally {
            SfDBResource.freeConnection(conn);
        }
        //99.返回值
        return form;
    }

    /**
     * A类移动
     * @param vo 业务参数
     * @return SbbyzclForm 值对象
     * @throws BaseException 业务异常
     */
    private SbbyzclForm doMoveA(VOPackage vo) throws BaseException {
    	
        //0.句柄定义
        SbbyzclForm form = null;
        Connection conn = null;
        SfDBAccess da = null;
        Vector v=new Vector();
        Vector v1=new Vector();
        Sbjkzb sbjkzb=null;
        Sbjkmx sbjkmx=null;
        Sbjkzb_His sbjkzb_his=null;
        Sbjkmx_His sbjkmx_his=null;
        String tailSQL=null;
        //1.输入参数检查
        /**
         * @todo doMoveB().输入参数检查
         */
        //2.初始化
        ///2.0.获取页面值对象
        form = (SbbyzclForm) vo.getData(); //从前端获得map对象
        ///2.1.得到userdata
        UserData userData = vo.getUserData();
        ///2.2.得到区县代码
        String qxdm = InterfaceDj.getQxdm(userData);
        ///2.3.获取需处理的申报编号

        //3.业务流程
        try {
            ///3.0.获取数据库连接
            conn = SfDBResource.getConnection();
            da = new SfDBAccess(conn);
            ///3.1.处理页面初始化对象
            ///3.1.查询处全部申报数据
            ////3.1.0.生成主表查询条件
            v = new Vector();
            v.add("substr(zwbs, 1, 1) = '0'");
            v.add("substr(zwbs, 20, 1) = '0'");
            v.add("(sklxdm in ('100','110','400') or sklxdm like'20%')");
            v.add("jsjdm=" + SBStringUtils.getSQLStr(form.getQueryJsjdm()));
            v.add("nd=" + SBStringUtils.getSQLStr(form.getQueryNd()));
            v.add("sbbh=" + SBStringUtils.getSQLStr(form.getParSbbh()));
            v.add("zyrq>=" + SBStringUtils.getSQLDate(form.getQueryKsrq()));
            v.add("zyrq<=" + SBStringUtils.getSQLDate(form.getQueryJsrq()));
            ////3.1.1.查询主表数据
            List zbList=da.query(Sbjkzb.class,v);//zbList
          
            //add tujb 过滤 滞纳金 2014-07-01 begin
            v1 = new Vector();
            v1.add("substr(zwbs, 1, 1) = '0'");
            v1.add("substr(zwbs, 20, 1) = '0'");
            v1.add("(sklxdm in ('100','110','400') or sklxdm like'20%')");
            v1.add("fsdm <> '"+CodeConstant.SMSB_FSDM_ZNJ+"'");
            v1.add("jsjdm=" + SBStringUtils.getSQLStr(form.getQueryJsjdm()));
            v1.add("nd=" + SBStringUtils.getSQLStr(form.getQueryNd()));
            v1.add("sbbh=" + SBStringUtils.getSQLStr(form.getParSbbh()));
            v1.add("zyrq>=" + SBStringUtils.getSQLDate(form.getQueryKsrq()));
            v1.add("zyrq<=" + SBStringUtils.getSQLDate(form.getQueryJsrq()));
            ////3.1.1.查询主表数据
            List zbznjList=da.query(Sbjkzb.class,v1);//zbList
            //add tujb 过滤 滞纳金 2014-07-01 end
            
            ////3.1.2.生成子表查询条件
             v = new Vector();
             tailSQL="jkpzh in('0'";
             for(int i=0;i<zbList.size();i++){
                 sbjkzb=(Sbjkzb)zbList.get(i);
                 tailSQL=tailSQL+","+SBStringUtils.getSQLStr(sbjkzb.getJkpzh());
             }
             tailSQL=tailSQL+")";
             debug("tailSQL="+tailSQL);
             v.add(tailSQL);
            ////3.1.3.查询子表数据
            List mxList=da.query(Sbjkmx.class,v);//mxList
            
            //add tujb 过滤 滞纳金 2014-07-01 begin
            v1 = new Vector();
            tailSQL="jkpzh in('0'";
            for(int i=0;i<zbznjList.size();i++){
                sbjkzb=(Sbjkzb)zbznjList.get(i);
                tailSQL=tailSQL+","+SBStringUtils.getSQLStr(sbjkzb.getJkpzh());
            }
            tailSQL=tailSQL+")";
            debug("tailSQL="+tailSQL);
            v1.add(tailSQL);
           ////3.1.3.查询子表数据
           List mxznjList=da.query(Sbjkmx.class,v1);//mxList
           //add tujb 过滤 滞纳金 2014-07-01 end
           
            ///3.2.插入历史表
            String zrlxdm=CodeConstant.SMSB_SBJK_HIS_ZRLXDM_SBWRKZQS;
            Timestamp zrrq=new Timestamp(System.currentTimeMillis());
            String zrr=userData.yhid;
            ////3.2.0.插入主表历史数据
            List zbHisList=new ArrayList();
            for(int i=0;i<zbList.size();i++){
                sbjkzb=(Sbjkzb)zbList.get(i);
                sbjkzb_his=this.translateZbToHis(sbjkzb,zrlxdm,zrrq,zrr);
                zbHisList.add(sbjkzb_his);
            }
            da.insert(zbHisList);
            ////3.2.1.插入明细历史数据
            List mxHisList=new ArrayList();
            for(int i=0;i<mxList.size();i++){
                sbjkmx=(Sbjkmx)mxList.get(i);
                sbjkmx_his=this.translateMxToHis(sbjkmx,zrlxdm,zrrq,zrr);
                mxHisList.add(sbjkmx_his);
            }
            da.insert(mxHisList);
            ///3.3.删除原始数据
            ////3.3.0.删除明细数据
            da.delete(mxList);
            ////3.3.1.删除主表数据
            da.delete(zbList);
            ///3.4.插入欠税数据
            String sqspbbh=this.getSqspbbh(da,qxdm,String.valueOf(System.currentTimeMillis()),null);
            StringBuffer sb=null;
            String sql=null;
            String qsxclxdm="03";
            String curDate=SBStringUtils.getStrFromDate(new Timestamp(System.currentTimeMillis()));
            
            //欠税确认期由默认系统时间修改为从前台录入 added by zhangyj  
            String qsqrq=form.getQsqrq();
            
            for(int i=0;i<mxznjList.size();i++){
                sbjkmx=(Sbjkmx)mxznjList.get(i);
                for(int j=0;j<zbznjList.size();j++){
                    sbjkzb=(Sbjkzb)zbznjList.get(j);
                    if(sbjkzb.getJkpzh().equals(sbjkmx.getJkpzh())){
                        break;
                    }
                }
                sb=new StringBuffer();
                sb.append("");
                sb.append("INSERT INTO SPDB.SP_JL_JLQSXZ(");
                sb.append("SQSPBH, MXID, JSJDM,");
                sb.append("SZSMDM, SKXJRQ, SKSSKSRQ,");
                sb.append("SKSSJSRQ, QSJE, QSXCLXDM,");
                sb.append("YSJCDM, YSKMDM, DJZCLXDM,");
                sb.append("GJBZHYDM, ZGSWJGDM, SPSWJGDM,");
                sb.append("JZBZ, LRRDM, LRRQ,");
                sb.append("CJRQ, SWJGZZJGDM, QSQRQ)");
                sb.append(" VALUES(");
                sb.append(SBStringUtils.getSQLStr(sqspbbh));//SQSPBH
                sb.append(",");
                sb.append("SPDB.SEQ_JL_SPDB.NEXTVAL");//MXID
                sb.append(",");
                sb.append(SBStringUtils.getSQLStr(sbjkmx.getJsjdm()));//JSJDM
                sb.append(",");
                sb.append(SBStringUtils.getSQLStr(sbjkmx.getSzsmdm()));//SZSMDM
                sb.append(",");
                sb.append(SBStringUtils.getSQLFromTimestamp(sbjkzb.getXjrq()));//SKXJRQ
                sb.append(",");
                sb.append(SBStringUtils.getSQLFromTimestamp(sbjkmx.getSkssksrq()));//SKSSKSRQ
                sb.append(",");
                sb.append(SBStringUtils.getSQLFromTimestamp(sbjkmx.getSkssjsrq()));//SKSSJSRQ
                sb.append(",");
                sb.append(SBStringUtils.getSQLStr(sbjkmx.getSjse().toString()));//QSJE
                sb.append(",");
                sb.append(SBStringUtils.getSQLStr(qsxclxdm));//QSXCLXDM
                sb.append(",");
                sb.append(SBStringUtils.getSQLStr(sbjkmx.getYsjcdm()));//YSJCDM
                sb.append(",");
                sb.append(SBStringUtils.getSQLStr(sbjkmx.getYskmdm()));//YSKMDM
                sb.append(",");
                sb.append(SBStringUtils.getSQLStr(sbjkzb.getDjzclxdm()));//DJZCLXDM
                sb.append(",");
                sb.append(SBStringUtils.getSQLStr(sbjkzb.getGjbzhydm()));//GJBZHYDM
                sb.append(",");
                sb.append(SBStringUtils.getSQLStr(sbjkzb.getSwjgzzjgdm()));//ZGSWJGDM
                sb.append(",");
                sb.append(SBStringUtils.getSQLStr(userData.ssdwdm));//SPSWJGDM
                sb.append(",");
                sb.append(SBStringUtils.getSQLStr("000000"));//JZBZ
                sb.append(",");
                sb.append(SBStringUtils.getSQLStr(userData.yhid));//LRRDM
                sb.append(",");
                sb.append("sysdate");//LRRQ
                sb.append(",");
                sb.append("sysdate");//CJRQ
                sb.append(",");
                sb.append(SBStringUtils.getSQLStr(sbjkzb.getSwjgzzjgdm()));//SWJGZZJGDM
                sb.append(",");
                sb.append("to_date('"+qsqrq+"','yyyy-mm-dd hh24:mi:ss')");//QSQRQ
                sb.append(")");
                sql=sb.toString();
                debug(sql);
                da.updateSQL(sql);
            }

            ///3.5.记帐，调用帐务的存储过程
            ////jkdb.jk_pkg_rtacount_qs.invoke('01', '申请审批表号', '区县代码（纳税人所在的区县)', '操作员');
            CallableStatement proc = null;
            proc = conn.prepareCall("{call jkdb.jk_pkg_rtacount_qs.invoke(?,?,?,?)}");
            proc.setString(1, "01");
            proc.setString(2, sqspbbh);
            proc.setString(3, qxdm);
            proc.setString(4, userData.yhid);
            proc.execute();
            ///3.6.重新查询待处理数据
            vo.setData(form);
            form=this.doQuerySb(vo);
        }
        ///3.98.捕捉异常并处理
        catch (Exception ex) {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex, "重复申报处理失败!");
        }
        ///3.99.释放资源
        finally {
            SfDBResource.freeConnection(conn);
        }
        //99.返回值
        return form;

    }

    /**
     * B类移动
     * @param vo 业务参数
     * @return SbbyzclForm 值对象
     * @throws BaseException 业务异常
     */
    private SbbyzclForm doMoveB(VOPackage vo) throws BaseException {
        //0.句柄定义
        SbbyzclForm form = null;
        Connection conn = null;
        SfDBAccess da = null;
        Vector v=new Vector();
        Sbjkzb sbjkzb=null;
        Sbjkmx sbjkmx=null;
        Sbjkzb_His sbjkzb_his=null;
        Sbjkmx_His sbjkmx_his=null;
        String tailSQL=null;
        //1.输入参数检查
        /**
         * @todo doMoveB().输入参数检查
         */
        //2.初始化
        ///2.0.获取页面值对象
        form = (SbbyzclForm) vo.getData(); //从前端获得map对象
        ///2.1.得到userdata
        UserData userData = vo.getUserData();
        ///2.2.得到区县代码
        String qxdm = InterfaceDj.getQxdm(userData);
        ///2.3.获取需处理的申报编号

        //3.业务流程
        try {
            ///3.0.获取数据库连接
            conn = SfDBResource.getConnection();
            da = new SfDBAccess(conn);
            ///3.1.处理页面初始化对象
            ///3.1.查询处全部申报数据
            ////3.1.0.生成主表查询条件
            v = new Vector();
            v.add("substr(zwbs, 1, 1) = '0'");
            v.add("substr(zwbs, 20, 1) = '0'");
            v.add("(sklxdm in ('100','110','400') or sklxdm like'20%')");
            v.add("jsjdm=" + SBStringUtils.getSQLStr(form.getQueryJsjdm()));
            v.add("nd=" + SBStringUtils.getSQLStr(form.getQueryNd()));
            v.add("sbbh=" + SBStringUtils.getSQLStr(form.getParSbbh()));
            v.add("zyrq>=" + SBStringUtils.getSQLDate(form.getQueryKsrq()));
            v.add("zyrq<=" + SBStringUtils.getSQLDate(form.getQueryJsrq()));
            ////3.1.1.查询主表数据
            List zbList=da.query(Sbjkzb.class,v);//zbList
            ////3.1.2.生成子表查询条件
             v = new Vector();
             tailSQL="jkpzh in('0'";
             for(int i=0;i<zbList.size();i++){
                 sbjkzb=(Sbjkzb)zbList.get(i);
                 tailSQL=tailSQL+","+SBStringUtils.getSQLStr(sbjkzb.getJkpzh());
             }
             tailSQL=tailSQL+")";
             debug("tailSQL="+tailSQL);
             v.add(tailSQL);
            ////3.1.3.查询子表数据
            List mxList=da.query(Sbjkmx.class,v);//mxList
            ///3.2.插入历史表
            String zrlxdm=CodeConstant.SMSB_SBJK_HIS_ZRLXDM_SBWRKQRCFSB;
            Timestamp zrrq=new Timestamp(System.currentTimeMillis());
            String zrr=userData.yhid;
            ////3.2.0.插入主表历史数据
            List zbHisList=new ArrayList();
            for(int i=0;i<zbList.size();i++){
                sbjkzb=(Sbjkzb)zbList.get(i);
                sbjkzb_his=this.translateZbToHis(sbjkzb,zrlxdm,zrrq,zrr);
                zbHisList.add(sbjkzb_his);
            }
            da.insert(zbHisList);
            ////3.2.1.插入明细历史数据
            List mxHisList=new ArrayList();
            for(int i=0;i<mxList.size();i++){
                sbjkmx=(Sbjkmx)mxList.get(i);
                sbjkmx_his=this.translateMxToHis(sbjkmx,zrlxdm,zrrq,zrr);
                mxHisList.add(sbjkmx_his);
            }
            da.insert(mxHisList);
            ///3.3.删除原始数据
            ////3.3.0.删除明细数据
            da.delete(mxList);
            ////3.3.1.删除主表数据
            da.delete(zbList);
            ///3.4.重新查询待处理数据
            vo.setData(form);
            form=this.doQuerySb(vo);
        }
        ///3.98.捕捉异常并处理
        catch (Exception ex) {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex, "重复申报处理失败!");
        }
        ///3.99.释放资源
        finally {
            SfDBResource.freeConnection(conn);
        }
        //99.返回值
        return form;
    }

    /**
     * 选择A类移动
     * @param vo 业务参数
     * @return SbbyzclForm 值对象
     * @throws BaseException 业务异常
     */
    private SbbyzclForm doSelectMoveA(VOPackage vo) throws BaseException {
        //0.句柄定义
        SbbyzclForm form = null;
        Connection conn = null;
        //1.输入参数检查
        /**
         * @todo doSelectMoveA().输入参数检查
         */
        //2.初始化
        ///2.0.获取页面值对象
        form = (SbbyzclForm) vo.getData(); //从前端获得map对象
        ///2.1.得到userdata
        UserData userData = vo.getUserData();
        ///2.2.得到区县代码
        String qxdm = InterfaceDj.getQxdm(userData);
        //3.业务流程
        try {
            ///3.0.获取数据库连接
            conn = SfDBResource.getConnection();
            ///3.1.处理页面初始化对象
            /**
             * @todo doSelectMoveA().处理页面初始化对象
             */
        }
        ///3.98.捕捉异常并处理
        catch (Exception ex) {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex, "初始化失败!");
        }
        ///3.99.释放资源
        finally {
            SfDBResource.freeConnection(conn);
        }
        //99.返回值
        return form;
    }

    /**
     * 选择B类移动
     * @param vo 业务参数
     * @return SbbyzclForm 值对象
     * @throws BaseException 业务异常
     */
    private SbbyzclForm doSelectMoveB(VOPackage vo) throws BaseException {
        //0.句柄定义
        SbbyzclForm form = null;
        Connection conn = null;
        //1.输入参数检查
        /**
         * @todo doSelectMoveB().输入参数检查
         */
        //2.初始化
        ///2.0.获取页面值对象
        form = (SbbyzclForm) vo.getData(); //从前端获得map对象
        ///2.1.得到userdata
        UserData userData = vo.getUserData();
        ///2.2.得到区县代码
        String qxdm = InterfaceDj.getQxdm(userData);
        //3.业务流程
        try {
            ///3.0.获取数据库连接
            conn = SfDBResource.getConnection();
            ///3.1.处理页面初始化对象
            /**
             * @todo doSelectMoveB().处理页面初始化对象
             */
        }
        ///3.98.捕捉异常并处理
        catch (Exception ex) {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex, "初始化失败!");
        }
        ///3.99.释放资源
        finally {
            SfDBResource.freeConnection(conn);
        }
        //99.返回值
        return form;
    }

    /**
     * 查询A类移动
     * @param vo 业务参数
     * @return SbbyzclForm 值对象
     * @throws BaseException 业务异常
     */
    private SbbyzclForm doQueryMoveA(VOPackage vo) throws BaseException {
        //0.句柄定义
        SbbyzclForm form = null;
        Connection conn = null;
        //1.输入参数检查
        /**
         * @todo doQueryMoveA().输入参数检查
         */
        //2.初始化
        ///2.0.获取页面值对象
        form = (SbbyzclForm) vo.getData(); //从前端获得map对象
        ///2.1.得到userdata
        UserData userData = vo.getUserData();
        ///2.2.得到区县代码
        String qxdm = InterfaceDj.getQxdm(userData);
        //3.业务流程
        try {
            ///3.0.获取数据库连接
            conn = SfDBResource.getConnection();
            ///3.1.处理页面初始化对象
            /**
             * @todo doQueryMoveA().处理页面初始化对象
             */
        }
        ///3.98.捕捉异常并处理
        catch (Exception ex) {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex, "初始化失败!");
        }
        ///3.99.释放资源
        finally {
            SfDBResource.freeConnection(conn);
        }
        //99.返回值
        return form;
    }

    /**
     * 查询B类移动
     * @param vo 业务参数
     * @return SbbyzclForm 值对象
     * @throws BaseException 业务异常
     */
    private SbbyzclForm doQueryMoveB(VOPackage vo) throws BaseException {
        //0.句柄定义
        SbbyzclForm form = null;
        Connection conn = null;
        //1.输入参数检查
        /**
         * @todo doQueryMoveB().输入参数检查
         */
        //2.初始化
        ///2.0.获取页面值对象
        form = (SbbyzclForm) vo.getData(); //从前端获得map对象
        ///2.1.得到userdata
        UserData userData = vo.getUserData();
        ///2.2.得到区县代码
        String qxdm = InterfaceDj.getQxdm(userData);
        //3.业务流程
        try {
            ///3.0.获取数据库连接
            conn = SfDBResource.getConnection();
            ///3.1.处理页面初始化对象
            /**
             * @todo doQueryMoveB().处理页面初始化对象
             */
        }
        ///3.98.捕捉异常并处理
        catch (Exception ex) {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex, "初始化失败!");
        }
        ///3.99.释放资源
        finally {
            SfDBResource.freeConnection(conn);
        }
        //99.返回值
        return form;
    }

    //打印申报表 added by zhangyj 20070720
    private Object doDetail(VOPackage vo) throws BaseException {
        Connection conn = null;
        HashMap resMap = new HashMap();
        List mxList = new ArrayList();
        List zbList = new ArrayList();
        Sbjkzb zb = new Sbjkzb();
        try {
            conn = SfDBResource.getConnection();
            SfDBAccess da = new SfDBAccess(conn);
            Map dataMap = (Map) vo.getData();
            String sbbh = (String)dataMap.get("sbbh");
            String jsjdm = (String)dataMap.get("jsjdm");
            String ksrq = (String)dataMap.get("ksrq");
            String jsrq = (String)dataMap.get("jsrq");
            String nd = (String)dataMap.get("nd");
            Vector ve = new Vector();
            ve.addElement("SBBH='"+sbbh+"'");            
            ve.addElement("to_char(sysdate,'yyyymmdd')>to_char(xjrq,'yyyymmdd')");
            ve.addElement("substr(zwbs, 1, 1) = '0'");
            ve.addElement("substr(zwbs, 20, 1) = '0'");
            ve.addElement("sjly in ('11','17','19','39')");
            ve.addElement("fsdm <> '"+CodeConstant.SMSB_FSDM_ZNJ+"'");
            ve.addElement("jsjdm="+SBStringUtils.getSQLStr(jsjdm)+"");
            ve.addElement("(sklxdm in ('100','110','400') or sklxdm like'20%')");
            if (!"".equals(SBStringUtils.killNull(ksrq))) {
            	ve.addElement("zyrq>="+SBStringUtils.getSQLDate(ksrq)+"");
            }
            if (!"".equals(SBStringUtils.killNull(jsrq))) {
            	ve.addElement("zyrq<="+SBStringUtils.getSQLDate(jsrq)+"");
            }
            ve.addElement("nd="+SBStringUtils.getSQLStr(nd)+"");
            
            zbList = da.query(Sbjkzb.class,ve);
            zb = (Sbjkzb)zbList.get(0);
            for(int i=0;i<zbList.size();i++)
            {
                Sbjkzb tmpZb = (Sbjkzb)zbList.get(i);
                Vector veMx = new Vector();
                veMx.addElement("JKPZH='"+tmpZb.getJkpzh()+"'");
                mxList.addAll(da.query(Sbjkmx.class,veMx));
            }
            resMap.put("sbjkzb",zb);
            resMap.put("mxList",mxList);
        }catch (Exception ex) {
            throw ExceptionUtil.getBaseException(ex);
        } finally {
            SfDBResource.freeConnection(conn);
        }
        return resMap;
    }    
    //end
    
    /**
     *
     * @param ud UserData
     * @return String
     */
    private String getSwjgzzjgAuthSQL(UserData ud) {
        String sql = "";
        String swjgzzjgdm = ud.ssdwdm;
        if (CodeConstants.JBDM_SWSJ.equals(ud.yhjb)) { // 税务所级
            sql = " swjgzzjgdm=" + SBStringUtils.getSQLStr(swjgzzjgdm);
        } else if (CodeConstants.JBDM_FJ.equals(ud.yhjb)) { // 分局级
            sql = " swjgzzjgdm like '" + swjgzzjgdm.substring(0, 2) + "%'";
        } else if (CodeConstants.JBDM_SJ.equals(ud.yhjb)) { // 市局级
            sql = " ";
        }
        return sql;
    }

    private Sbjkzb_His translateZbToHis(Sbjkzb sbjkzb,String zrlxdm,Timestamp zrrq,String zrr){
        Sbjkzb_His sbjkzb_his=new Sbjkzb_His();
        sbjkzb_his.setJkpzh(sbjkzb.getJkpzh());
        sbjkzb_his.setJsjdm(sbjkzb.getJsjdm());
        sbjkzb_his.setZrlxdm(zrlxdm);
        sbjkzb_his.setZrrq(zrrq);
        sbjkzb_his.setZrr(zrr);
        sbjkzb_his.setSklxdm(sbjkzb.getSklxdm());
        sbjkzb_his.setFsdm(sbjkzb.getFsdm());
        sbjkzb_his.setLsgxdm(sbjkzb.getLsgxdm());
        sbjkzb_his.setYhdm(sbjkzb.getYhdm());
        sbjkzb_his.setYhmc(sbjkzb.getYhmc());
        sbjkzb_his.setZh(sbjkzb.getZh());
        sbjkzb_his.setDjzclxdm(sbjkzb.getDjzclxdm());
        sbjkzb_his.setSwjgzzjgdm(sbjkzb.getSwjgzzjgdm());
        sbjkzb_his.setZsswjgzzjgdm(sbjkzb.getZsswjgzzjgdm());
        sbjkzb_his.setGjbzhydm(sbjkzb.getGjbzhydm());
        sbjkzb_his.setGkzzjgdm(sbjkzb.getGkzzjgdm());
        sbjkzb_his.setYskmdm(sbjkzb.getYskmdm());
        sbjkzb_his.setYsjcdm(sbjkzb.getYsjcdm());
        sbjkzb_his.setSzdm(sbjkzb.getSzdm());
        sbjkzb_his.setLrrq(sbjkzb.getLrrq());
        sbjkzb_his.setSbrq(sbjkzb.getSbrq());
        sbjkzb_his.setJksj(sbjkzb.getJksj());
        sbjkzb_his.setXjrq(sbjkzb.getXjrq());
        sbjkzb_his.setClbjdm(sbjkzb.getClbjdm());
        sbjkzb_his.setSjje(sbjkzb.getSjje());
        sbjkzb_his.setZyrq(sbjkzb.getZyrq());
        sbjkzb_his.setRkje(sbjkzb.getRkje());
        sbjkzb_his.setZwbs(sbjkzb.getZwbs());
        sbjkzb_his.setHxrdm(sbjkzb.getHxrdm());
        sbjkzb_his.setHxrmc(sbjkzb.getHxrmc());
        sbjkzb_his.setLrr(sbjkzb.getLrr());
        sbjkzb_his.setBz(sbjkzb.getBz());
        sbjkzb_his.setHxrq(sbjkzb.getHxrq());
        sbjkzb_his.setSbbh(sbjkzb.getSbbh());
        sbjkzb_his.setJydzlxdm(sbjkzb.getJydzlxdm());
        sbjkzb_his.setSkssksrq(sbjkzb.getSkssksrq());
        sbjkzb_his.setSkssjsrq(sbjkzb.getSkssjsrq());
        sbjkzb_his.setSjly(sbjkzb.getSjly());
        sbjkzb_his.setNd(sbjkzb.getNd());
        sbjkzb_his.setCjrq(sbjkzb.getCjrq());
        sbjkzb_his.setQxdm(sbjkzb.getQxdm());
        sbjkzb_his.setSphm(sbjkzb.getSphm());
        return sbjkzb_his;
    }

    private Sbjkmx_His translateMxToHis(Sbjkmx sbjkmx,String zrlxdm,Timestamp zrrq,String zrr){
        Sbjkmx_His sbjkmx_his=new Sbjkmx_His();
        sbjkmx_his.setJkpzh(sbjkmx.getJkpzh());
        sbjkmx_his.setJsjdm(sbjkmx.getJsjdm());
        sbjkmx_his.setSzsmdm(sbjkmx.getSzsmdm());
        sbjkmx_his.setZrlxdm(zrlxdm);
        sbjkmx_his.setZrrq(zrrq);
        sbjkmx_his.setZrr(zrr);
        sbjkmx_his.setYskmdm(sbjkmx.getYskmdm());
        sbjkmx_his.setYsjcdm(sbjkmx.getYsjcdm());
        sbjkmx_his.setKssl(sbjkmx.getKssl());
        sbjkmx_his.setJsje(sbjkmx.getJsje());
        sbjkmx_his.setSjse(sbjkmx.getSjse());
        sbjkmx_his.setSkssksrq(sbjkmx.getSkssksrq());
        sbjkmx_his.setSkssjsrq(sbjkmx.getSkssjsrq());
        sbjkmx_his.setRkje(sbjkmx.getRkje());
        sbjkmx_his.setSbbh(sbjkmx.getSbbh());
        sbjkmx_his.setSjfc(sbjkmx.getSjfc());
        sbjkmx_his.setQjfc(sbjkmx.getQjfc());
        sbjkmx_his.setSwjgzzjgdm(sbjkmx.getSwjgzzjgdm());
        sbjkmx_his.setNd(sbjkmx.getNd());
        sbjkmx_his.setSl(sbjkmx.getSl());
        sbjkmx_his.setCjrq(sbjkmx.getCjrq());
        sbjkmx_his.setLrrq(sbjkmx.getLrrq());
        sbjkmx_his.setQxdm(sbjkmx.getQxdm());
        return sbjkmx_his;
    }

    /**
     * 获取欠税的申请审批表编号
     * @param da SfDBAccess
     * @param par1 String
     * @param par2 String
     * @param par3 String
     * @return String
     */
    private String getSqspbbh(SfDBAccess da,String par1,String par2,String par3){
        return "QSGL_CFQSQR_+"+par1+par2;
    }


    /**
     * Debug方法
     * @param str 信息
     */
    private void debug(String str) {
        if (true){
            //System.out.println("SMSB SbbyzclProcessor DEBUG:" + str);
        }
    }

    /**
     * 系统输出方法
     * @param str 信息
     */
    private void debugCore(String str) {
        System.out.println("SMSB SbbyzclProcessor CORE:" + str);
    }

}
