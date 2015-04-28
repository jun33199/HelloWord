/**
 * <p>Title: 北京地税综合管理系统  扣缴企业所得税生成税收缴款书</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，四一安信息科技有限公司，版权所有. </p>
 * <p>Company: 四一安信息科技有限公司</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.kjqysds.kjssjks.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.common.Constant;
import com.ttsoft.bjtax.sfgl.common.code.CodeManager;
import com.ttsoft.bjtax.sfgl.common.constant.CodeConstants;
import com.ttsoft.bjtax.sfgl.common.db.util.BeanUtil;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.model.Cftsyhd;
import com.ttsoft.bjtax.sfgl.common.model.Grtszygsde;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.sfgl.common.util.SfStringUtils;
import com.ttsoft.bjtax.sfgl.model.orobj.Dqdedlmx1;
import com.ttsoft.bjtax.sfgl.model.orobj.Tszslmx;
import com.ttsoft.bjtax.shenbao.model.domain.Gzsx;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb;
import com.ttsoft.bjtax.shenbao.model.domain.Szsm;
import com.ttsoft.bjtax.shenbao.model.domain.Zqrl;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.model.client.SbjkmxDis;
import com.ttsoft.bjtax.smsb.sbzl.kjqysds.kjssjks.web.KjssjksBO;
import com.ttsoft.bjtax.smsb.util.EArray;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.JksUtil;
import com.ttsoft.bjtax.smsb.util.Skssrq;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.bjtax.smsb.zhsb.web.ZhsbActionForm;
import com.ttsoft.bjtax.smsb.zhsb.web.ZhsbGzsxActionForm;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.DateUtil;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.framework.exception.ApplicationException;

/**
 * <p>Title: 北京地税综合管理系统   扣缴企业所得税生成税收缴款书</p>
 * <p>Description:  扣缴企业所得税生成税收缴款书</p>
 * @author wangxm
 * @version 1.1
 */

public class KjssjksProcessor
    implements Processor
{
    private Map CDF = null;

    public KjssjksProcessor ()
    {
    }

    /**
     * 实现Processor接口
     * @param vo 业务参数
     * @return Object VOPackage型数据
     * @throws BaseException 业务异常
     */

    public Object process(VOPackage vo) throws BaseException {
		System.out.println("------------kjqysdsbgbProcessor-----------------");
		Object result = null;
		if (vo == null) {
			throw new NullPointerException();
		}

		switch (vo.getAction()) {
		case CodeConstant.SMSB_QUERYACTION:
        	result = doQuery(vo);
        	break;

        case CodeConstant.SMSB_SAVEACTION:
        	result = doSave(vo);
            break;
        case CodeConstant.SMSB_ZHSB_INITLIST:
        	result = getInitList(vo);
            break;
        case CodeConstant.SMSB_ZHSB_GZSX:
        	result = this.getGzsxInfo(vo);
            break;
		default:
			throw new ApplicationException("用户执行了系统不支持的方法或功能.");
		}

		return result;
	}

    private Object doQuery (VOPackage vo)
        throws BaseException
    {
    	// 得到Action传递过来KjqysdsbgbBO对象
		KjssjksBO bo = (KjssjksBO) vo.getData();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuffer sql=new StringBuffer();
		
		
		try {
			// 创建数据库连接
			conn = SfDBResource.getConnection();
			/**
			 * 查询该报告表是否已生成缴款书
			 */
			sql.append("select t.sphm,a.sbbh from sbdb.sb_jl_kjqysds_kjbgb t,sbdb.sb_jl_sbjkzb a where t.badjxh='");
			sql.append(bo.getBadjxh()).append("' and t.bgbxh='");
			sql.append(bo.getBgbxh()).append("' and a.jkpzh=t.sphm");
			ps=conn.prepareStatement(sql.toString());
			rs=ps.executeQuery();
			String sbbh="";
			while(rs.next()){
				sbbh=rs.getString("sbbh");
				bo.setSbbh(sbbh);
			}
			sql.delete(0,sql.length());
			rs.close();
			ps.close();
			if("".equals(sbbh)){	//没有生成缴款书,则查询报告表信息
				/**
				 * 查询港澳台or外国来确认税种税目代码
				 */
				sql.append("select t.fjmgjdq from SBDB.SB_JL_KJQYSDS_FJMQYXX t where t.badjxh='");
				sql.append(bo.getBadjxh()).append("'");
				ps=conn.prepareStatement(sql.toString());
				rs=ps.executeQuery();
				rs.next();
				String fjmgjdq =rs.getString("fjmgjdq");//国家或地区
				sql.delete(0,sql.length());
				rs.close();
				ps.close();
				/**
				 * 查询税种税目代码
				 */
				if(fjmgjdq.equals(CodeConstant.PAGE_FJMQYGB_GAT)){
					bo.setSzsmdm("300021");
				}
				else{
					bo.setSzsmdm("300022");
				}
				sql.append("select a.szsmdm szsmdm,a.szsmmc szsmmc,b.szsmdm szdm,b.szsmmc szmc from dmdb.sb_dm_szsm a, dmdb.sb_dm_szsm b where a.fjddm=b.szsmdm and a.szsmdm='");
				sql.append(bo.getSzsmdm()).append("'");
				System.out.println("szsmsql----------"+sql.toString());
				ps=conn.prepareStatement(sql.toString());
				rs=ps.executeQuery();
				while(rs.next()){
					bo.setSzmc(rs.getString("szmc"));
					bo.setSzsmdm(rs.getString("szsmdm"));
					bo.setSzsmmc(rs.getString("szsmmc"));
					bo.setSzdm(rs.getString("szdm"));	
					System.out.println("szdm---"+bo.getSzdm());
				}
				rs.close();
				ps.close();
				sql.delete(0,sql.length());
				/**
				 * 查询税款所属日期
				 */
				sql.append("select to_char(t.skssksrq,'yyyymmdd') skssksrq,to_char(t.skssjsrq,'yyyymmdd') skssjsrq from  SBDB.SB_JL_KJQYSDS_KJBGB t where t.badjxh='");
				sql.append(bo.getBadjxh()).append("'");
				System.out.println("税款所属日期------------"+sql.toString());
				ps=conn.prepareStatement(sql.toString());
				rs=ps.executeQuery();
				while(rs.next()){
					bo.setSkssksrq(rs.getString("skssksrq"));
					bo.setSkssjsrq(rs.getString("skssjsrq"));			
				}
				rs.close();
				ps.close();
				sql.delete(0,sql.length());
				/**
				 * 查询合同信息
				 */
				sql.append("select t.htmc,t.htbh from sbdb.sb_jl_kjqysds_bahtmx t where t.badjxh='");
				sql.append(bo.getBadjxh()).append("'");
				System.out.println("查询合同信息-----------"+sql.toString());
				ps=conn.prepareStatement(sql.toString());
				rs=ps.executeQuery();
				
				while(rs.next()){
					bo.setHtmc(rs.getString("htmc"));
					bo.setHtbh(rs.getString("htbh"));
				}
				rs.close();
				ps.close();
				sql.delete(0,sql.length());
				/**
				 * 查询实际应缴纳得所得税额
				 */
				sql.append("select t.bgbxh,t.hc,t.yz from SBDB.SB_JL_KJQYSDS_KJBGMX t where t.badjxh='");
				sql.append(bo.getBadjxh()).append("' and t.jsjdm='");
				sql.append(bo.getJsjdm()).append("' and t.bgbxh='");
				sql.append(bo.getBgbxh()).append("' and (t.hc='12' or t.hc='8' or t.hc='11')");
				System.out.println("实际应缴纳得所得税额------------"+sql.toString());
				ps=conn.prepareStatement(sql.toString());
				rs=ps.executeQuery();
				bo.setSjse("0.00");
				bo.setJsje("0.00");
				while(rs.next()){
					if(rs.getString("hc").equals("12")){
						bo.setSjse(rs.getString("yz"));	
					}
					if(rs.getString("hc").equals("11")){
						bo.setSl(rs.getString("yz"));
					}
					if(rs.getString("hc").equals("8")){
						bo.setJsje(rs.getString("yz"));
					}
				}
				rs.close();
				ps.close();
				sql.delete(0,sql.length());
			}

		} catch (Exception e) {
			// 抛出异常
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		} finally {
			// 释放数据库连接
			SfDBResource.freeConnection(conn);
		}
		return bo;
    }

    /**
     * doSave     保存录入数据
     * @param     vo 业务参数
     * @return    数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
     * @throws BaseException    当其他情况都会抛出异常信息
     */

    private Object doSave (VOPackage vo)
        throws BaseException
    {
    	Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuffer sql=new StringBuffer();

        //ormapping对象
        Sbjkzb orObj = new Sbjkzb();
        
     // 得到Action传递过来KjqysdsbgbBO对象
		KjssjksBO bo = (KjssjksBO) vo.getData();

        //获得UserData
        UserData ud = vo.getUserData();
        //定义与form种的columns 一样的变量。其中的名称为名细的字段名
        String names[] =
            {
            "jsjdm", "nsrmc", "yhmc", "yhdm", "zh", "sklxdm", "sklxmc", "sbrq"};
        Timestamp now = new Timestamp( (new java.util.Date()).getTime());
        try
        {

            //将bo中对应主表信息保存到值对象
            BeanUtil.copyBeanToBean(names, bo, orObj);
            //通过登记接口取得纳税人相关信息
            /* start added by huxiaofeng 2005.8.1*/
            //SWDJJBSJ jbsj = InterfaceDj.getJBSJ(form.getJsjdm(), ud);
            SWDJJBSJ jbsj = InterfaceDj.getJBSJ_New(bo.getJsjdm(), ud);
            /* end added by huxiaofeng 2005.8.1*/

            //补充主表信息
            //登记注册类型
            if(bo.getSzsmdm().equals("300021")){
            	orObj.setDjzclxdm("290");
            }
            else{
            	orObj.setDjzclxdm("390");
            }
            //orObj.setDjzclxmc(jbsj.getDjzclxmc());
            //国家标准行业代码
            orObj.setGjbzhydm(jbsj.getGjbzhydm());
            //orObj.setGjbzhymc(jbsj.getGjbzhymc());
            //税务机关组织机构
            orObj.setSwjgzzjgdm(jbsj.getSwjgzzjgdm());
            //orObj.setSwjgzzjgmc(jbsj.getSwjgzzjgmc());
            //征收机关
            //orObj.setZsswjgzzjgdm(jbsj.getSwjgzzjgdm());
            orObj.setZsswjgzzjgdm(ud.getSsdwdm());
            //orObj.setZsswjgzzjgmc(jbsj.getSwjgzzjgmc());
            //隶属关系
            orObj.setLsgxdm(jbsj.getLsgxdm());
            //orObj.setLsgxmc(jbsj.getLsgxmc());
            //经营地址联系电话
            orObj.setJydzlxdm(jbsj.getJydzlxdm());
            //录入人
            orObj.setLrr("smsb");
            if (ud != null)
            {
                orObj.setLrr(ud.getYhid());
                //录入日期
            }
            orObj.setLrrq(now);
            //创建日期
            //orObj.setCjsj(now);
            orObj.setCjrq(now);
            //税款类型
            orObj.setSklxdm(bo.getSklxdm());
            //orObj.setSklxmc(this.getSklxmc(form.getSklxdm()));
            //申报方式代码
            orObj.setFsdm(CodeConstant.FSDM_SMSB);
            //数据来源
            orObj.setSjly(CodeConstant.SMSB_SJLY_YQKJ);
            //区县代码
            orObj.setQxdm(InterfaceDj.getQxdm(ud));
            //对数据进行分票保存
            JksUtil ju = new JksUtil();
//            return ju.getJkDataZhsb(orObj, form.getDataList());
            try
            {

              //start modifying by qianchao 2005.10.26
              System.out.println("jsjdm:" + bo.getJsjdm() +  "== 分票类型：" + bo.getJksType());
              Map retmap = (Map) ju.getJkDataKjqysds(orObj,bo.getDataList(),bo.getJksType());
              bo.setSbbh(ju.getSbbh());
              bo.setDataList((List)retmap.get(CodeConstant.ZHSB_JKS_LIST));
              //把sphm放入sbdb.sb_jl_kjqysds_kjbgb中
              // 创建数据库连接
  				conn = SfDBResource.getConnection();
  				sql.append("select t.sphm from sbdb.sb_jl_sbjkzb t where t.sbbh='");
  				sql.append(bo.getSbbh()).append("'");
  				ps=conn.prepareStatement(sql.toString());
  				rs=ps.executeQuery();
  				String sphm=new String();
  				while(rs.next()){
  					sphm=rs.getString("sphm");
  				}
  				rs.close();
  				ps.close();
  				sql.delete(0,sql.length());
  				
  				sql.append("update sbdb.sb_jl_kjqysds_kjbgb set sphm='");
  				sql.append(sphm).append("' where badjxh='");
  				sql.append(bo.getBadjxh()).append("' and bgbxh='");
  				sql.append(bo.getBgbxh()).append("'");
  				ps=conn.prepareStatement(sql.toString());
  				ps.execute();
  				ps.close();
  				sql.delete(0,sql.length());
  				
              return bo;
              //end modifying by qianchao 2005.10.26
            }
            catch (BaseException ex1)
            {
                ex1.printStackTrace();
                throw new ApplicationException("保存数据失败！");
            }

        }
        catch (Exception ex)
        {
            //throw ExceptionUtil.getBaseException(ex, "系统错误请与管理员联系!");
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }

    }

    /**
     * 得到初始化list包括税种税目list,附加税list
     * @param     vo 业务参数
     * @return List
     * @throws BaseException
     */
    private Object getInitList (VOPackage vo)
        throws BaseException
    {
        //获取form对象
        ZhsbActionForm form = (ZhsbActionForm) vo.getData();
        //得到总控用户的区县代码
        String qxdm = InterfaceDj.getQxdm(vo.getUserData());
        List ret = new ArrayList();
        String code="ORSZSM";
        Connection con = null;
        try
        {
//      System.out.println("time == "+new Date());
            con = SfDBResource.getConnection();
            //得到税种税目下拉列表列表和附加税列表
            EArray jsArray = new EArray();
            String tempJsStr = jsArray.getArrayByCode("szsmlist_add",
                "ZHSB_SZSMADD");

            /**
             * 如果税款类型为自查补税,带出已注销的11\88税种,否则不带出
             */
            if("400".equals(form.getSklxdm())){
            	tempJsStr +=jsArray.getMsgsByCode("szsmdm", "ZHSB_SZSM_ZCBS",new ArrayList());
            	code="ORSZSM_ZCBS";
            }else{
            	tempJsStr +=jsArray.getMsgsByCode("szsmdm", "ZHSB_SZSM",new ArrayList());
            	code="ORSZSM";
            }
            
            //根据税费接口处理定期定额、定率和附加税
            List mxList = this.dealWithSfgl(form.getJsjdm(),
                                            this.getSzsmList(con,code),
                                            SfDateUtil.getDate(form.getSbrq()));
            //根据已经得到的征期日历map为明细数据添加税款所属日期
            //根据申报日期得到税款所属日期
            Date date = SfDateUtil.getDate(form.getSbrq());
            if (date == null)
            {
                date = new Date();
            }
//      System.out.println("time == "+new Date());
            this.addSkssrqByMap(form.getJsjdm(), mxList, date, con);
//      System.out.println("time == "+new Date());
            //特殊税款所属日期的计算
//      this.fixSpeSkssrq(form.getJsjdm(), date, mxList);
//
//      this.fixSpeSkssrq2(form.getJsjdm(), date, mxList);
            form.setInitMxList(mxList);
            //生成明细数据的js数组
            tempJsStr += this.getMxJsArray(mxList);
            tempJsStr += "\nvar szsmlist_fields = [\"szsmdm\",\"szmc\",\"szsmmc\",\"skssksrq\",\"skssjsrq\",\"kssl\",\"jsje\",\"sjse\",\"szdm\",\"sffjs\",\"szsmdm_old\",\"asljbs\",\"sl\",\"jsjs\"];";
            form.setScriptStr(tempJsStr);
            //设置告知事项列表
            form.setGzsxList(this.getGzsxList(form.getJsjdm(), qxdm, new Date(),
                                              con));
            return form;

        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "系统错误请与管理员联系!");
        }
        finally
        {
            SfDBResource.freeConnection(con);
        }

    }

    /**
     * 得到税种税目
     * @param     con 数据库连接
     * @return List 税种税目list
     * @throws BaseException
     */
    private List getSzsmList (Connection con,String code)
        throws BaseException
    {
        List ret = new ArrayList();
        ret = CodeManager.getCodeList(code, CodeConstants.CODE_MAP_ORLIST).
              getRecords();
        return ret;

    }

    /**
     * 根据征期日历得到所有税种税目的税款所属时期<br>
     * 当前日期的月等于征期起始或截止日期的月
     * 征期截止日期＋1天=限缴日期<br>
     * @param  rq 申报时间
     * @param  djzclxdm 登记注册类型
     * @param     conn 数据库连接
     * @throws Exception
     * @return Map
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

            criteria.add("djzclxdm='" + djzclxdm + "'");
            criteria.add("ZQQSRQ<=to_date('" + dateStr + "','yyyyMM')");
            criteria.add("ZQZZRQ>=to_date('" + dateStr + "','yyyyMM')");

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
     * 根据明细列表中的税种税目、申报日期、添加税款所属日期
     * @param     jsjdm 计算机代码
     * @param mxList 前台显示用List
     * @param rq 申报日期
     * @param     conn 数据库连接
     * @return list
     * @throws BaseException
     *
     */
    private List addSkssrqByMap (String jsjdm, List mxList, Date rq,
                                 Connection conn)
        throws
        BaseException
    {
        List ret = new ArrayList();
        try
        {
            //通过登记接口得到纳税人基本信息
            /* start added by huxiaofeng 2005.8.16*/
            //SWDJJBSJ jbsj = InterfaceDj.getJBSJ(jsjdm);
            SWDJJBSJ jbsj = InterfaceDj.getJBSJ_New2(jsjdm);
            /* end added by huxiaofeng 2005.8.16*/


            //得到该征期内的所有税种税目税款所属日期
            Map zqrlMap = this.getSksssqMap(jbsj.getDjzclxdm(), rq, conn);
            for (int i = 0; i < mxList.size(); i++)
            {
                //为每个明细添加税款所属日期
                SbjkmxDis mxData = (SbjkmxDis) mxList.get(i);
                //根据税款所属日期map为明细数据添加税款所属日期
                Zqrl zqrl = (Zqrl) zqrlMap.get(mxData.getSzsmdm());

                //Modified by lufeng 20031105
                if (zqrl != null)
                {
                    mxData.setSkssjsrq(zqrl.getZqssrqz());
                    mxData.setSkssksrq(zqrl.getZqssrqq());

                }
                else
                {
                    Map map = (Map) Skssrq.getSksssq(jsjdm, mxData.getSzsmdm(),
                        CodeConstant.SKLXDM_ZCJK,
                        mxData.getZqlxdm(),
                        rq, mxData.getSjse(),
                        mxData.getKssl(), mxData.getJsje(),
                        mxData.getJsje());
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
            throw ExceptionUtil.getBaseException(ex, "系统错误请与管理员联系!");
        }
    }

    /**
     * 根据税费接口得到车房土信息<br>
     * 因为现有税费接口只可以通过计算机代码和税种税目单一确定<br>
     * 所以应该为车房土添加新的税费接口，通过计算机代码得到所有的车房土核定
     * @param     jsjdm 计算机代码
     * @param     date 申报日起
     * @param     qsrq 申报日起
     * @param     jzrq 申报日起
     * @return Map
     */
    private Map getCDFSet (String jsjdm, Date date, Date qsrq, Date jzrq)
    {

        Map map = new HashMap();
        try
        {

            com.ttsoft.bjtax.sfgl.proxy.ServiceProxy proxy =
                new com.ttsoft.bjtax.sfgl.proxy.ServiceProxy();
            map = proxy.getCDFSet(jsjdm, date, qsrq, jzrq);
        }
        catch (Exception ex)
        {
            return null;
        }
        return map;
    }

    /**
     * 根据税费接口得到车房土信息<br>
     * 因为现有税费接口只可以通过计算机代码和税种税目单一确定<br>
     * 所以应该为车房土添加新的税费接口，通过计算机代码得到所有的车房土核定
     * <br>该方法通过计算机代码得到所有的车房土数据
     * @param     jsjdm 计算机代码
     * @param     date 申报日期
     * @return Map
     */
    /* deleted by qianchao 2005.11.2
    private Map getCftInfoByJsjdm (String jsjdm, Date date)
    {

        Map cftMap = new HashMap();
        try
        {

            com.ttsoft.bjtax.sfgl.proxy.ServiceProxy proxy =
                new com.ttsoft.bjtax.sfgl.proxy.ServiceProxy();
            List cftList = proxy.getCftsyhdInfo(jsjdm, date);
            for (int i = 0; i < cftList.size(); i++)
            {
                Cftsyhd temp = (Cftsyhd) cftList.get(i);
                cftMap.put(temp.getSzsmdm(), temp);
            }
        }
        catch (Exception ex)
        {
            //ex.printStackTrace();
            return null;
        }
        return cftMap;
    }
*/
    /**
     * 根据税费接口得到车房土List得到map<br>
     * 因为现有税费接口只可以通过计算机代码和税种税目单一确定<br>
     * 所以应该为车房土添加新的税费接口，通过计算机代码得到所有的车房土核定
     * <br>该方法通过计算机代码得到所有的车房土数
     * @param cftList 认定列表
     * @return Map
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
            return null;
        }
        return cftMap;
    }

    /**
     * 根据缴纳次数，处理车房土税款所属日期
     * @param mxData 明细数据
     * @param  sbrq 申报日期
     * @param  jncs 交纳次数
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
			//modified by Guoxh,2007-09-12【11，88只能征收20061231前的税款】
			if(szdm.equals("11") || szdm.equals("88")){
				mxData.setSkssksrq( Skssrq.getTimestampMinDay(2006,0));
				mxData.setSkssjsrq( Skssrq.getTimestampMaxDay(2006,11));
			}
        }
    }

    /**
     * 根据税费管理认定情况处理征期类行为年的税种税目数据<br>
     * 征期类型代码１代表年、２代表半年、４代表季度、12代表月；
     * 根据定期定额数据和营业税附加税数据处理税种税目list
     * 返回处理后的根据税种税目对应的明细list
     * @param jsjdm 计算机代码
     * @param szsmList 税种列表
     * @param sbrq 申报日期
     * @throws Exception
     * @return Map
     */
    private List dealWithSfgl (String jsjdm, List szsmList, Date sbrq)
        throws
        Exception
    {
        //添加处理后数据的明细list
        List mxList = new ArrayList();

        try
        {
            //得到征期类型月的税款所属日期
            Map semiY = Skssrq.monthSkssrq(sbrq);
            Date skssjsrq = (Date) semiY.get(Skssrq.SKSSJSRQ);
            Date skssksrq = (Date) semiY.get(Skssrq.SKSSKSRQ);
            /**
             * 通过车房土、定期定额和附加税合一的接口得到相关数据
             *
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
                //  if (mxData.getZqlxdm().equals(CodeConstant.ZQLXDM_YEAR)) {
                //当征期类型为年的时候根据税费接口处理相应的数据

                String szsmdm = mxData.getSzsmdm();

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
                            mxData.setSjse(dqde.getYnsrd());
                            //mxData.setSjse(dqde.getSjrd());
                            mxData.setJsje(dqde.getSjrd());
                            mxData.setFromDqde(true);
                        }
                        else if (dqde.getZsfsdm().equals(CodeConstant.
                            ZHSB_ZSFS_DL))
                        {
                            //征收方式为定律
                            mxData.setSl(dqde.getZsl());
                        }

                    }
                }
                // 处理税费管理中的附加税税率
                //        if (mxData.getSffjs() != null && mxData.getSffjs().equals("2") &&
//            fjsInfo != null) {
                //申报征收时，税种是城市维护建设税（10%）和教育费附加（51%）取税费特殊征收率，其他税种取税费定期定额定律。
                String smdm = szsmdm.substring(0, 2);
                if (fjsInfo != null && (smdm.equals("10") || smdm.equals("51")))
                {
                    //税目是营业税附加税，并且由税费取得核定信息
                    Tszslmx tszslmx = (Tszslmx) fjsInfo.get(smdm);
                    if (tszslmx != null)
                    {
                        //
                        mxData.setSl(tszslmx.getSl());
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

                //}
            }
            return mxList;
        }
        catch (BaseException ex)
        {
            throw ExceptionUtil.getBaseException(ex, "处理税种税目列表失败");
        }

    }

    /**
     * 根据税费管理认定情况处理税种税目数据<br>
     * 根据定期定额数据和营业税附加税数据处理税种税目list
     * 返回处理后的根据税种税目对应的明细list
     * @param szsmList 税种列表
     * @return Map
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
                //设置按数量计标示
                temp.setAsljbs(szsm.getAsljbs());
                //设置税率
                temp.setSl(szsm.getSl());
                //设置征期类型代码
                temp.setZqlxdm(szsm.getZqlxdm());
                //设置计税基数
                temp.setJsjs(szsm.getJsjs());
                mxList.add(temp);
            }

        }
        return mxList;
    }

    /**
     * 根据税费接口得到附加税核定
     * @param jsjdm 计算机代码
     * @throws BaseException
     * @return Map
     */
    /* deleted by qianchao 2005.11.2
    private Map getFjsInfo (String jsjdm)
        throws BaseException
    {
        com.ttsoft.bjtax.sfgl.proxy.ServiceProxy sfglProxy =
            new com.ttsoft.bjtax.sfgl.proxy.ServiceProxy();

        List fjsInfo = sfglProxy.getYyfjsslInfo(jsjdm, new Date());

        Map fjsMap = new HashMap();
        for (int i = 0; i < fjsInfo.size(); i++)
        {
            Tszslmx tszslmx = (Tszslmx) fjsInfo.get(i);
            fjsMap.put(tszslmx.getSzsmdm(), tszslmx);
        }

        return fjsMap;
    }
*/
    /**
     * 根据税费接口得到附加税核定
     * @param fjsInfo 明细数据列表
     * @throws BaseException
     * @return Map
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
     * 根据税费接口取得定期定额核定
     * @param jsjdm 计算机代码
     * @throws BaseException
     * @return Map
     */
    /* deleted by qianchao 2005.11.2
    private Map getDqdeInfo (String jsjdm)
        throws BaseException
    {
        com.ttsoft.bjtax.sfgl.proxy.ServiceProxy sfglProxy =
            new com.ttsoft.bjtax.sfgl.proxy.ServiceProxy();

        List dqdeInfo = sfglProxy.getYnsje(jsjdm, new Date(), new Date());

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
*/
    /**
     * 根据税费接口取得定期定额核定
     * 通过定期定额list得到定期定额map
     * @param dqdeInfo 明细数据列表
     * @throws BaseException
     * @return Map
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
     * 根据明细数据列表生成js2维数组<br>
     * @param mxList 明细数据列表
     * @return String js2维数组
     **/
    private String getMxJsArray (List mxList)
    {
        StringBuffer ret = new StringBuffer();
        //ret.append("[");
        for (int i = 0; i < mxList.size(); i++)
        {
            SbjkmxDis mxData = (SbjkmxDis) mxList.get(i);
            ret.append("[");
            //税种税目代码
            ret.append("\"" + mxData.getSzsmdm() + "\",");
            //税种名称
            ret.append("\"" + mxData.getSzmc() + "\",");
            //税种税目名称
            ret.append("\"" + mxData.getSzsmmc() + "\",");
            if (mxData.getSkssksrq() != null)
            {
                //税款所属日期
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
            //课税数量
            ret.append("\"" + mxData.getKssl() + "\",");
            //计税金额
            ret.append("\"" + mxData.getJsje() + "\",");
            //实缴税额
            ret.append("\"" + mxData.getSjse() + "\",");
            //税种代码
            ret.append("\"" + mxData.getSzdm() + "\",");
            //是否附加税
            ret.append("\"" + mxData.getSffjs() + "\",");
            //税种税目代码
            ret.append("\"" + mxData.getSzsmdm() + "\",");
            //按数量计标示
            ret.append("\"" + mxData.getAsljbs() + "\",");
            //税率
            ret.append("\"" + mxData.getSl() + "\",");
            //计税基数
            //ret.append("\"" + mxData.getJsjs() + "\"");
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
        return "var szsmlist = [" + ret.toString();

    }

    /**
     * 得到税种税目
     * @param jsjdm 计算机代码
     * @param rq 日期
     * @return List 税种税目list
     * @throws BaseException
     */
    private List getGzsxList (String jsjdm, Date rq)
        throws BaseException
    {

        List ret = new ArrayList();
        Connection con = null;
        try
        {
            con = SfDBResource.getConnection();
            SfDBAccess db = new SfDBAccess(con);
            Vector v = new Vector();
            v.add("gzsxksrq<=to_date('" + DateUtil.getDate(rq)
                  + "','yyyy-MM-dd')");
            v.add("gzsxjsrq>=to_date('" + DateUtil.getDate(rq)
                  + "','yyyy-MM-dd')");
            //只有非正常告知事项的时候才转移到告知事项页面
            //v.add("fzcbs='" + CodeConstant.ZHSB_FZCBS + "' order by GZSXKSRQ");
            v.add("fzcbs='" + CodeConstant.ZHSB_FZCBS + "' ");
            v.add("jsjdm='" + jsjdm + "'  order by cjrq desc");
            List ret1 = db.query(new Gzsx().getClass(), v);
            return ret1;
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "系统错误请与管理员联系!");
        }
        finally
        {
            SfDBResource.freeConnection(con);
        }

    }

    /**
     * 得到告知列表
     * @param jsjdm 计算机代码
     * @param rq 日期
     * @param qxdm 区县代码
     * @param con 数据库连接
     * @return List 告知list
     * @throws BaseException
     */
    private List getGzsxList (String jsjdm, String qxdm, Date rq,
                              Connection con)
        throws
        BaseException
    {

        List ret = new ArrayList();
        //Connection con = null;
        try
        {
            //con = SfDBResource.getConnection();
            SfDBAccess db = new SfDBAccess(con);
            Vector v = new Vector();
            //v.add("jsjdm='" + jsjdm + "'");
            v.add("gzsxksrq<=to_date('" + DateUtil.getDate(rq)
                  + "','yyyy-MM-dd')");
            v.add("gzsxjsrq>=to_date('" + DateUtil.getDate(rq)
                  + "','yyyy-MM-dd')");
            v.add("qxdm='" + qxdm + "'");
            //只有非正常告知事项的时候才转移到告知事项页面
            //v.add("fzcbs='" + CodeConstant.ZHSB_FZCBS + "' order by GZSXKSRQ");
            v.add("fzcbs='" + CodeConstant.ZHSB_FZCBS + "' ");
            v.add("jsjdm='" + jsjdm + "'  order by cjrq desc");
            List ret1 = db.query(new Gzsx().getClass(), v);
            return ret1;
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "系统错误请与管理员联系!");
        }
        finally
        {
            //SfDBResource.freeConnection(con);
        }

    }

    private List getGzsxInfo (VOPackage vo)
        throws BaseException
    {
        ZhsbGzsxActionForm form = (ZhsbGzsxActionForm) vo.getData();
        return this.getGzsxList(form.getJsjdm(), new Date());
    }

}
