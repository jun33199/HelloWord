/*
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.gzwh.processor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.common.db.util.BeanUtil;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBUtils;
import com.ttsoft.bjtax.sfgl.common.util.SfHashList;
import com.ttsoft.bjtax.shenbao.model.domain.Gzsx;
import com.ttsoft.bjtax.shenbao.model.domain.Gzsxls;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.gzwh.web.GzsxwhForm;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.exception.SystemException;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.bjtax.smsb.util.TinyTools;

/**
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Description: 告知事项维护</p>
 * @author 开发六组－－石岩峰
 * @version 1.1
 */
public class GzsxwhProcessor
    implements Processor
{

    /**
     * 页面元素列表数组
     */
    private String names[] =
        {
        "jsjdm", "gzsxfcrq", "nsrmc", "gzsxksrq",
        "gzsxjsrq", "gzsxnr",
        "gzsxfcdw", "gzsxsdbz", "fzcbs", "bz", "lrr",
        "lrrdm", "swjgzzjgdm",
        "swjgzzjgdm2", "djzclxdm", "gjbzhydm", "cjrq",
        "lrrq", "qxdm"};

    /**
     * 默认构造函数
     */
    public GzsxwhProcessor ()
    {
    }

    /**
     * 通用处理调度模块
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return 当前页面对应的Form对象
     * @throws BaseException
     */
    public Object process (VOPackage vo)
        throws BaseException
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
            case CodeConstant.SMSB_READERACTION:
                result = doGetNsrmc(vo);
                break;
            case CodeConstant.SMSB_DELETEALLACTION:
                result = doDeleteAll(vo);
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
    	System.out.println("GzsxwhProcessor.doShow()");
    	
        GzsxwhForm gf = (GzsxwhForm) vo.getData();
        Connection conn = null;
        try
        {
        	
            conn = SfDBResource.getConnection();
            SfDBAccess db = new SfDBAccess(conn);
            List list = this.getPh(vo, db);
            //设置导入批号列表
            gf.setPhList( (ArrayList) list);

            return gf;
        }
        catch (SystemException ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
        finally
        {
            SfDBResource.freeConnection(conn);
        }

    }

    /**
     * 得到该操作员所有的导入批号
     * @param vo 数据集对象（包括Form和UserData对象）
     * @param db 数据库访问对象
     * @return 当前操作员的所有导入批号list
     */
    private List getPh (VOPackage vo, SfDBAccess db)
    {
        UserData ud = vo.getUserData();
        List ret = new ArrayList();
        try
        {
        	System.out.println(InterfaceDj.getQxdm(ud)+"##############");
        	
           /* ResultSet rs = db.querySQL(
                "select distinct ph from sbdb.SB_JL_GZSX where qxdm='" +
                InterfaceDj.getQxdm(ud) + "' and ph is not null");*/
            
        	//在告知事项维护模块中,为'计算机代码导入方式'的选择批号添加了按照lrrq降排序功能(2014年7月14日)
        	ResultSet rs = db.querySQL(
                    "select distinct ph,lrrq from sbdb.SB_JL_GZSX where qxdm='" +
                    InterfaceDj.getQxdm(ud) + "' and ph is not null order by lrrq desc");
            
            while (rs.next()) {
                Gzsx temp = new Gzsx();
                temp.setPh(rs.getString("ph"));
                ret.add(temp);
            }
            return ret;
        }
        catch (Exception ex) {
            return ret;
        }

    }

    /**
     * 取名称后台处理
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return 纳税人名称
     * @throws BaseException
     */
	private Object doGetNsrmc (VOPackage vo)
        throws BaseException
    {

        GzsxwhForm gf = (GzsxwhForm) vo.getData();
        //调登记接口
        try
        {
            if (gf.getJsjdm() != null && (gf.getJsjdm().trim()).length() != 0)
            {
                /****得到批号***/
                gf = (GzsxwhForm)this.doShow(vo);
                com.ttsoft.bjtax.dj.proxy.ServiceProxy djProxy = new com.ttsoft.
                    bjtax.
                    dj.proxy.ServiceProxy();
                HashMap ghdwMap = djProxy.getDjInfo(gf.getJsjdm());
                SWDJJBSJ swdjjbsj = (SWDJJBSJ) ghdwMap.get("JBSJ");
                gf.setNsrmc(swdjjbsj.getNsrmc());
            }
        }
        catch (Exception e) {
            gf.setNsrmc("");
            e.printStackTrace();
            throw new ApplicationException("没有该纳税人的登记信息!");
        }
        try
        {
            if (gf.getJsjdm() != null && (gf.getJsjdm().trim()).length() != 0) {
                com.ttsoft.bjtax.dj.proxy.ServiceProxy djProxy = 
                							new com.ttsoft.bjtax.dj.proxy.ServiceProxy();
                HashMap ghdwMap = djProxy.getDjInfo(gf.getJsjdm(),
                    vo.getUserData());
            }
        }
        catch (Exception e) {
            gf.setNsrmc("");
            e.printStackTrace();
            throw new ApplicationException("您没有足够的权限处理此计算机代码！");
        }

        return gf;
    }

    /**
     * 查询后台处理
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return 当前页面对应的Form对象
     * @throws BaseException
     */
    private Object doQuery (VOPackage vo)
        throws BaseException
    {
        GzsxwhForm gf = (GzsxwhForm) vo.getData();
        Connection conn = null;
        Gzsx g = new Gzsx(); //告知事项表ORMapping值对象
        List qlist = null; //List of 告知事项表HashMap检出对象
        String sql = ""; //Case2用SQL语句
        StringBuffer sqlBuffer = new StringBuffer();
        UserData userdata = vo.getUserData();

        gf.setLength(CodeConstant.GZ_PG_LENGTH); //设置页长
        //查询条件
        Vector v = new Vector();

        //*******Case1****** 选择单发查询模式并且输入计算机代码
		if (gf.getChooseTypeRadio().equals("1") 
				&& gf.getJsjdm() != null
				&& !gf.getJsjdm().equals("")) {

            sqlBuffer.append("SELECT DISTINCT JSJDM,GZSXFCRQ,NSRMC,GZSXKSRQ,GZSXJSRQ,GZSXNR,GZSXFCDW,GZSXSDBZ,FZCBS,BZ,LRR,LRRDM,SWJGZZJGDM,cjrq "
                             + " FROM SBDB.SB_JL_GZSX WHERE ");
            //告知类型条件加入
            sqlBuffer.append(" FZCBS = '" + gf.getGzlx() + "' ");

            //计算机代码条件加入
            //如果计算机代码输入 == *
            gf.setJsjdm(gf.getJsjdm().trim());
            if (gf.getJsjdm() != null && (gf.getJsjdm()).equals("*"))
            {
                //doNothing
            }
            else
            {
                sqlBuffer.append(" AND JSJDM= '" + gf.getJsjdm() + "' ");
            }

            //*******Case2******* 选择群发查询模式
        }
        else if (gf.getChooseTypeRadio().equals("2"))
        {
            sqlBuffer.append("SELECT DISTINCT JSJDM,GZSXFCRQ,NSRMC,GZSXKSRQ,GZSXJSRQ,GZSXNR,GZSXFCDW,GZSXSDBZ,FZCBS,BZ,LRR,LRRDM,SWJGZZJGDM,cjrq  "
                             +
                             " FROM SBDB.SB_JL_GZSX WHERE JSJDM= '*' ");
            //告知类型条件加入
            sqlBuffer.append(" AND FZCBS = '" + gf.getGzlx() + "' ");

            //群发条件加入
            String multiSendString = getMultiSendString(gf);
            sqlBuffer.append(multiSendString);

        }
        else if (gf.getChooseTypeRadio().equals("3"))
        {
            sqlBuffer.append("SELECT DISTINCT JSJDM,GZSXFCRQ,NSRMC,GZSXKSRQ,GZSXJSRQ,GZSXNR,GZSXFCDW,GZSXSDBZ,FZCBS,BZ,LRR,LRRDM,SWJGZZJGDM,cjrq "
                             + " FROM SBDB.SB_JL_GZSX WHERE ");
            sqlBuffer.append("ph='" + gf.getPh() + "' ");
        }
        else
        {
            return gf;
        }
        
        //如果输入告知起始日期
        if (gf.getGzqsrq() != null && !gf.getGzqsrq().equals("")) {
            gf.setGzqsrq(gf.getGzqsrq().trim());
           
            sqlBuffer.append(" AND GZSXKSRQ >= TO_DATE('" + gf.getGzqsrq() +
                             "','yyyyMMdd') ");            
        }
        //如果输入告知截至日期
        if (gf.getGzjzrq() != null && !gf.getGzjzrq().equals(""))
        {
            gf.setGzjzrq(gf.getGzjzrq().trim());
            sqlBuffer.append(" AND GZSXJSRQ <= TO_DATE('" + gf.getGzjzrq() +
                             "','yyyyMMdd') ");
        }
        
        if (!gf.getChooseTypeRadio().equals("3"))
        {
            //如果不是文件导入型则进行权限验证
            //加入地区局所权限控制
            sqlBuffer.append(getDqjsCondition(userdata.getSsdwdm(), "SELECT"));
        }
        //sqlBuffer.append("ORDER BY GZSXKSRQ ");
        /*******************************************/
        /**              Shi Yanfeng              **/
        /*******************************************/
        sqlBuffer.append("order by cjrq desc ");

        sql = sqlBuffer.toString();
        if (!sql.equals(""))
        {
            SfHashList sfHashList = null;
            try
            {
                conn = SfDBResource.getConnection();
                
                /**20040311 Shi Yanfeng 读取批号列表**/
                SfDBAccess db = new SfDBAccess(conn);
                List list = this.getPh(vo, db);
                
                //设置导入批号列表
                gf.setPhList( (ArrayList) list);

                SfDBUtils sfDB = new SfDBUtils(conn);
                sfHashList = sfDB.getData(sql, gf.getLength(), gf.getPgNum());
                qlist = sfHashList.getRecords();
                //若删除最后一页所有记录，则退到上一页，即当前最后一页
                if (gf.getPgNum() > 1 && qlist.size() < 1)
                {
                    gf.setPgNum(gf.getPgNum() - 1);
                    sfHashList = sfDB.getData(sql, gf.getLength(), gf.getPgNum());
                    qlist = sfHashList.getRecords();
                }
                //进行一些必要的格式转换
                if (qlist.size() > 0)
                {
                    ArrayList dataList = new ArrayList();
                    for (int i = 0; i < qlist.size(); i++)
                    {
                        HashMap map = (HashMap) qlist.get(i);
                        //将时间转为标准格式
                        map.put("gzsxksrq",
                                stringToStandTimeString( (String) map.get(
                            "gzsxksrq")));
                        map.put("gzsxjsrq",
                                stringToStandTimeString( (String) map.get(
                            "gzsxjsrq")));
                        //加入主键标示
                        String modifyIndex = null;
                        if (gf.getChooseTypeRadio().equals("1"))
                        { 
                        	//单发方式
                            modifyIndex = (String) map.get("jsjdm") + "|"
                                          + (String) map.get("gzsxfcrq");
                        }
                        else if (gf.getChooseTypeRadio().equals("2"))
                        {
                        	//群发方式
                            modifyIndex = (String) map.get("jsjdm") + "|"
                                          + (String) map.get("gzsxfcrq");
                        }
                        else if (gf.getChooseTypeRadio().equals("3"))
                        { 
                        	//导入方式
                            modifyIndex = (String) map.get("jsjdm") + "|"
                                          + (String) map.get("gzsxfcrq");
                        }

                        //将代码转为名称
                        map.put("modifyIndex", modifyIndex);
                        if ( ( (String) map.get("fzcbs")).equals("0"))
                        {
                            map.put("fzcbs", "正常");
                        }
                        else if ( ( (String) map.get("fzcbs")).equals("1"))
                        {
                            map.put("fzcbs", "异常");
                        }
                        dataList.add(map);
                    }
                    gf.setDataList(dataList);

                    //确定最大页数
                    gf.setPgSum(sfDB.getMaxResultNum() % gf.getLength() == 0 ?
                                sfDB.getMaxResultNum() / gf.getLength() :
                                sfDB.getMaxResultNum() / gf.getLength() + 1);
                }
                else
                {
                    gf.setDataList(new ArrayList());
                    gf.setPgNum(0);
                    gf.setPgSum(0);
                    throw new ApplicationException("没有找到指定的记录！");
                }

            }
            catch (Exception sqlex)
            {
                sqlex.printStackTrace();
                throw ExceptionUtil.getBaseException(sqlex);
            }
            finally
            {
                SfDBResource.freeConnection(conn);
            }

        }

        return gf;
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
     * 删除文件导入的告知事项
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return 当前页面对应的Form对象
     * @throws BaseException
     */
	private Object deleteAllDr(VOPackage vo) throws BaseException {
		GzsxwhForm gf = (GzsxwhForm) vo.getData();
		Connection conn = null;
		try {
			conn = SfDBResource.getConnection();			
			SfDBAccess db = new SfDBAccess(conn);			
			db.querySQL("delete from sbdb.sb_jl_gzsx where qxdm='"
					+ InterfaceDj.getQxdm(vo.getUserData()) + "' and ph='"
					+ gf.getPh() + "'");
			
			return gf;
		} catch (SystemException ex) {
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			SfDBResource.freeConnection(conn);
		}

	}

    /**
     * 删除全部
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return 当前页面对应的Form对象
     * @throws BaseException
     */
    private Object doDeleteAll (VOPackage vo)
        throws BaseException
    {
        Connection conn = null;
        GzsxwhForm gf = (GzsxwhForm) vo.getData();
        List qlist = null; //List of 告知事项表HashMap检出对象
        String sql = ""; //Case2用SQL语句
        StringBuffer sqlBuffer = new StringBuffer();
        UserData userdata = vo.getUserData();

        try
        {

            //*******Case1****** 选择单发查询模式并且输入计算机代码
             if (gf.getChooseTypeRadio().equals("1")
                 && gf.getJsjdm() != null
                 && !gf.getJsjdm().equals(""))
             {

                 sqlBuffer.append("SELECT DISTINCT JSJDM,GZSXFCRQ,NSRMC,GZSXKSRQ,GZSXJSRQ,GZSXNR,GZSXFCDW,GZSXSDBZ,FZCBS,BZ,LRR,LRRDM,SWJGZZJGDM,cjrq "
                                  + " FROM SBDB.SB_JL_GZSX WHERE ");
                 //告知类型条件加入
                 sqlBuffer.append(" FZCBS = '" + gf.getGzlx() + "' ");

                 //计算机代码条件加入
                 //如果计算机代码输入 == *
                 gf.setJsjdm(gf.getJsjdm().trim());
                 if (gf.getJsjdm() != null && (gf.getJsjdm()).equals("*"))
                 {
                     //doNothing
                 }
                 else
                 {
                     sqlBuffer.append(" AND JSJDM= '" + gf.getJsjdm() + "' ");
                 }

                 //*******Case2******* 选择群发查询模式
             }
             else if (gf.getChooseTypeRadio().equals("2"))
             {

                 sqlBuffer.append("SELECT DISTINCT JSJDM,GZSXFCRQ,NSRMC,GZSXKSRQ,GZSXJSRQ,GZSXNR,GZSXFCDW,GZSXSDBZ,FZCBS,BZ,LRR,LRRDM,SWJGZZJGDM,cjrq  "
                                  +
                                  " FROM SBDB.SB_JL_GZSX WHERE JSJDM= '*' ");
                 //告知类型条件加入
                 sqlBuffer.append(" AND FZCBS = '" + gf.getGzlx() + "' ");

                 //群发条件加入
                 String multiSendString = getMultiSendString(gf);
                 sqlBuffer.append(multiSendString);

             }
             else if (gf.getChooseTypeRadio().equals("3"))
             {
                 //删除所有导入告知
                 this.deleteAllDr(vo);
                 return this.doShow(vo);
             }
             else
             {
                 return gf;
             }

            //如果输入告知起始日期
            if (gf.getGzqsrq() != null && !gf.getGzqsrq().equals(""))
            {
                gf.setGzqsrq(gf.getGzqsrq().trim());
                sqlBuffer.append(" AND GZSXKSRQ >= TO_DATE('" + gf.getGzqsrq() +
                                 "','yyyyMMdd') ");
            }
            //如果输入告知截至日期
            if (gf.getGzjzrq() != null && !gf.getGzjzrq().equals(""))
            {
                gf.setGzjzrq(gf.getGzjzrq().trim());
                sqlBuffer.append(" AND GZSXJSRQ <= TO_DATE('" + gf.getGzjzrq() +
                                 "','yyyyMMdd') ");
            }
            if (!gf.getChooseTypeRadio().equals("3"))
            {
                //如果不是文件导入型则进行权限验证
                //加入地区局所权限控制
                sqlBuffer.append(getDqjsCondition(userdata.getSsdwdm(),
                                                  "SELECT"));
            }
            sql = sqlBuffer.toString();
            if (!sql.equals(""))
            {
                SfHashList sfHashList = null;

                conn = SfDBResource.getConnection();
                SfDBUtils sfDB = new SfDBUtils(conn);
                sfHashList = sfDB.getData(sql);
                qlist = sfHashList.getRecords();
            }

            SfDBAccess da = new SfDBAccess(conn);
            /***得到导入批号***/
            gf.setPhList( (ArrayList)this.getPh(vo, da));
            Calendar nowTime = Calendar.getInstance();
            Calendar now = Calendar.getInstance();
            //本日时刻
            now.set(nowTime.get(Calendar.YEAR), nowTime.get(Calendar.MONTH),
                    nowTime.get(Calendar.DATE));
            for (int i = 0; i < qlist.size(); i++)
            {
                String strJsjdm = (String) ( (Map) qlist.get(i)).get("jsjdm");
                String strGzsxfcrq = (String) ( (Map) qlist.get(i)).get(
                    "gzsxfcrq");
                //去掉毫秒
                strGzsxfcrq = strGzsxfcrq.substring(0, strGzsxfcrq.length() - 2);

                Vector gzV = new Vector();
                gzV.add(" JSJDM = '" + strJsjdm + "' ");
                gzV.add(" GZSXFCRQ = to_date('" + strGzsxfcrq +
                        "','YYYY-mm-dd hh24:mi:ss') ");

                List sourceGzsxList = da.query(Gzsx.class, gzV);

                Gzsx sourceGzsx = (Gzsx) sourceGzsxList.get(0);
                Calendar gzksrq = Calendar.getInstance();
                gzksrq.set(TinyTools.getYear(sourceGzsx.getGzsxksrq()),
                           TinyTools.getMonth(sourceGzsx.getGzsxksrq()),
                           TinyTools.getDay(sourceGzsx.getGzsxksrq()), 0, 0, 0);

                //如果用户看过（告知开始日期在当前日期之前）则导入历史表，
                //否则直接删除
                if (gzksrq.after(now))
                {
                    //da.delete(sourceGzsxList);
                    deletegzsx(conn,sourceGzsxList);
                }
                else
                {
                    //导入历史表
                    List insertGzsxlsList = new ArrayList();
                    for (int j = 0; j < sourceGzsxList.size(); j++)
                    {
                        sourceGzsx = (Gzsx) sourceGzsxList.get(j);
                        Gzsxls gls = new Gzsxls();
                        BeanUtil.copyBeanToBean(names, sourceGzsx, gls);
                        insertGzsxlsList.add(gls);
                    }
                   //da.insert(insertGzsxlsList);
                   // da.delete(sourceGzsxList);
                    insertgzsx(conn,sourceGzsxList);
                    deletegzsx(conn,sourceGzsxList);
                }
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

        return gf;
    }

    /**
     * 删除处理
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return 当前页面对应的Form对象
     * @throws BaseException
     */
    private Object doDelete (VOPackage vo)
        throws BaseException
    {
        Connection conn = null;
        GzsxwhForm gf = (GzsxwhForm) vo.getData();
        String[] deleteCheckbox = gf.getDeleteCheckbox();
        try
        {
            Gzsx g = new Gzsx(); //告知事项表ORMapping值对象
            ArrayList qlist = gf.getDataList(); //List of 告知事项表ORMapping值对象
            conn = SfDBResource.getConnection();
            SfDBAccess da = new SfDBAccess(conn);
            /***得到导入批号***/
            gf.setPhList( (ArrayList)this.getPh(vo, da));
            Calendar nowTime = Calendar.getInstance();
            Calendar now = Calendar.getInstance();
            //本日时刻
            now.set(nowTime.get(Calendar.YEAR), nowTime.get(Calendar.MONTH),
                    nowTime.get(Calendar.DATE));
            for (int i = 0; i < deleteCheckbox.length; i++)
            {
                g = new Gzsx();
                String primaryKey = deleteCheckbox[i];
                String strJsjdm = primaryKey.substring(0,
                    primaryKey.indexOf("|"));
                String strGzsxfcrq = primaryKey.substring(primaryKey.indexOf(
                    "|") + 1,
                    primaryKey.length());
                //去掉毫秒
                strGzsxfcrq = strGzsxfcrq.substring(0, strGzsxfcrq.length() - 2);

                Vector gzV = new Vector();
                gzV.add(" JSJDM = '" + strJsjdm + "' ");
                gzV.add(" GZSXFCRQ = to_date('" + strGzsxfcrq +
                        "','YYYY-mm-dd hh24:mi:ss') ");

                List sourceGzsxList = da.query(Gzsx.class, gzV);

                Gzsx sourceGzsx = (Gzsx) sourceGzsxList.get(0);
                Calendar gzksrq = Calendar.getInstance();
                gzksrq.set(TinyTools.getYear(sourceGzsx.getGzsxksrq()),
                           TinyTools.getMonth(sourceGzsx.getGzsxksrq()),
                           TinyTools.getDay(sourceGzsx.getGzsxksrq()), 0, 0, 0);

//        gzksrq.set(sourceGzsx.getGzsxksrq().getYear() + 1900,
//                   sourceGzsx.getGzsxksrq().getMonth(),
//                   sourceGzsx.getGzsxksrq().getDate(), 0, 0, 0);

                //如果用户看过（告知开始日期在当前日期之前）则导入历史表，
                //否则直接删除
                if (gzksrq.after(now))
                {
                    //da.delete(sourceGzsxList);
                	deletegzsx(conn,sourceGzsxList);
                }
                else
                {
                    //导入历史表
                    List insertGzsxlsList = new ArrayList();
                    for (int j = 0; j < sourceGzsxList.size(); j++)
                    {
                        sourceGzsx = (Gzsx) sourceGzsxList.get(j);
                        Gzsxls gls = new Gzsxls();
                        BeanUtil.copyBeanToBean(names, sourceGzsx, gls);
                        insertGzsxlsList.add(gls);
                    }
                    //da.insert(insertGzsxlsList);
                    insertgzsx(conn,sourceGzsxList);
                    deletegzsx(conn,sourceGzsxList);
                }

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

        return gf;

    }
    
    
    private void insertgzsx (Connection conn, List list)
    throws BaseException{
    	Statement sttest=null;
    	try {
    		for(int i=0;i<list.size();i++){
				Gzsx g=(Gzsx)list.get(i);
				sttest = conn.createStatement();
				
				/*发出日期*/
				String fcrq=TinyTools.Date2String(new Date(g.getGzsxfcrq().getTime()),"yyyy-MM-dd HH:mm:ss");
				/*创建日期*/
				String cjrq=TinyTools.Date2String(new Date(g.getCjrq().getTime()),"yyyy-MM-dd HH:mm:ss");
				/*录入日期*/
				String lrrq=TinyTools.Date2String(new Date(g.getLrrq().getTime()),"yyyy-MM-dd HH:mm:ss");
				/*开始日期*/
				String ksrq=TinyTools.Date2String(new Date(g.getGzsxksrq().getTime()));
				/*结束日期*/
				String jsrq=TinyTools.Date2String(new Date(g.getGzsxjsrq().getTime()));
				
				String sql = "insert into sbdb.sb_jl_gzsx_ls(JSJDM,GZSXFCRQ,NSRMC,GZSXKSRQ,GZSXJSRQ,GZSXNR,GZSXFCDW,GZSXSDBZ,FZCBS,BZ,LRR,LRRDM,SWJGZZJGDM,DJZCLXDM,GJBZHYDM,SWJGZZJGDM2,CJRQ,LRRQ,QXDM,PH,GZSX_ID,GZSXNRBT) " +
				"values('"+g.getJsjdm()+"',to_date('"+fcrq+"','yyyy-MM-dd HH24:mi:ss'),'"+g.getNsrmc()+"',"+
				"to_date('"+ksrq+"','yyyy-MM-dd')"+","+
				"to_date('"+jsrq+"','yyyy-MM-dd')"+",'"+
				g.getGzsxnr()+"','"+
				g.getGzsxfcdw()+"','"+
				(g.getGzsxsdbz()==null?"":g.getGzsxsdbz())+"','"+
				g.getFzcbs()+"','"+
				g.getBz()+"','"+
				g.getLrr()+"','"+
				g.getLrrdm()+"','"+
				g.getSwjgzzjgdm()+"','"+
				g.getDjzclxdm()+"','"+
				g.getGjbzhydm()+"','"+
				g.getSwjgzzjgdm2()+"',to_date('"+cjrq+"','yyyy-MM-dd HH24:mi:ss'),to_date('"+lrrq+"','yyyy-MM-dd HH24:mi:ss')"+
				",'"+
				g.getQxdm()+"','"+
				(g.getPh()==null?"":g.getPh())+"','"+
				g.getGzsx_id()+"','"+
				g.getGzsxnrbt()+"')";
				//System.out.println("------sql"+sql);
				sttest.execute(sql);
                if(sttest!=null){
					try {
						sttest.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
			     }
			}			
    	   } catch (SQLException e1) {
			e1.printStackTrace();
			throw new ApplicationException("告知事项插入历史表失败，请联系管理员\n详细信息："+e1.getMessage());
		}finally{
			if(sttest!=null){
				try {
					sttest.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
    }
    
    
    /**
     * 删除告知事项
     * @param conn
     * @param list
     * @throws BaseException
     */
    private void deletegzsx (Connection conn, List list)
 throws BaseException {
		Statement sttest = null;
		try {
			for (int i = 0; i < list.size(); i++) {
				Gzsx g = (Gzsx) list.get(i);
				sttest = conn.createStatement();
				String sql = "delete from sbdb.sb_jl_gzsx t where t.jsjdm='"
						+ g.getJsjdm()
						+ "' and t.gzsxfcrq=to_date('"
						+ g.getGzsxfcrq().toString().substring(0,g.getGzsxfcrq().toString().length() - 2)
						+ "','YYYY-mm-dd hh24:mi:ss')";
				
				sttest.execute(sql);
				if (sttest != null) {
					try {
						sttest.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			throw new ApplicationException("告知事项删除失败，请联系管理员\n详细信息："
					+ e1.getMessage());
		} finally {
			if (sttest != null) {
				try {
					sttest.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
    
    /**
     * 修改处理
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return 当前页面对应的Form对象
     * @throws BaseException
     */
	private Object doUpdate (VOPackage vo)  throws BaseException {
		Connection conn = null;
		GzsxwhForm gf = (GzsxwhForm) vo.getData();
		try {
			conn = SfDBResource.getConnection();
			SfDBAccess da = new SfDBAccess(conn);
			/*** 得到导入批号 ***/
			gf.setPhList((ArrayList) getPh(vo, da));
			String primaryKey = gf.getModifyIndex();
			String strJsjdm = primaryKey.substring(0, primaryKey.indexOf("|"));
			String strGzsxfcrq = primaryKey.substring(primaryKey.indexOf("|") + 1, primaryKey.length());
			// 去掉毫秒
			strGzsxfcrq = strGzsxfcrq.substring(0, strGzsxfcrq.length() - 2);

			Vector gzV = new Vector();
			gzV.add(" JSJDM = '" + strJsjdm + "' ");
			gzV.add(" GZSXFCRQ = to_date('" + strGzsxfcrq
					+ "','YYYY-mm-dd hh24:mi:ss') ");
			List sourceGzsxList = da.query(Gzsx.class, gzV);
			Gzsx sourceGzsx = (Gzsx) sourceGzsxList.get(0);

			// 数据初始化（修改）
			gf.setMxJsjdm(sourceGzsx.getJsjdm());
			gf.setMxNsrmc(sourceGzsx.getNsrmc());
			gf.setMxGzlx(sourceGzsx.getFzcbs());
			gf.setMxGzqsrq(stringToStandTimeString(sourceGzsx.getGzsxksrq()
					.toString()));
			gf.setMxGzjzrq(stringToStandTimeString(sourceGzsx.getGzsxjsrq()
					.toString()));
			gf.setMxGzsxxxxx(sourceGzsx.getGzsxnr());
			if (!sourceGzsx.getJsjdm().equals("*")) {
				gf.setMxChooseTypeRadio("1");
			} else if (sourceGzsx.getJsjdm().equals("*")) {
				gf.setMxChooseTypeRadio("2");
				if (sourceGzsxList.size() == 1) {
					gf.setMxJhfs("0"); // 结合方式（与）
					gf.setMxQylx(sourceGzsx.getDjzclxdm());
					gf.setMxHylb(sourceGzsx.getGjbzhydm());
					gf.setMxDqjs(sourceGzsx.getSwjgzzjgdm2());
				} else if (sourceGzsxList.size() > 1) {
					gf.setMxJhfs("1"); // 结合方式（或）
					for (int i = 0; i < sourceGzsxList.size(); i++) {
						sourceGzsx = (Gzsx) sourceGzsxList.get(i);
						if (!sourceGzsx.getDjzclxdm().equals("0")) {
							gf.setMxQylx(sourceGzsx.getDjzclxdm());
						}
						if (!sourceGzsx.getGjbzhydm().equals("0")) {
							gf.setMxHylb(sourceGzsx.getGjbzhydm());
						}
						if (!sourceGzsx.getSwjgzzjgdm2().equals("0")) {
							gf.setMxDqjs(sourceGzsx.getSwjgzzjgdm2());
						}
					}
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			SfDBResource.freeConnection(conn);
		}

		return gf;

	}

    /**
     * 将日期字串(format:yyyyMMdd eg. 20030611)转化为Calendar型日期
     * @param strDate 日期字符串
     * @return Calendar型日期
     */
    private Calendar stringToCalendar (String strDate)
    {
        int year = Integer.parseInt(strDate.substring(0, 4));
        int month = Integer.parseInt(strDate.substring(4, 6)) - 1;
        int date = Integer.parseInt(strDate.substring(6, 8));
        Calendar c = Calendar.getInstance();
        c.set(year, month, date);
        
        return c;
    }

    /**
     * 将日期字串(format:yyyy-MM-dd hh:MI:ss.ms  eg.2003-10-20 11:11:01.0)
     * 转化为标准日期字串(format:yyyyMMdd eg. 20030611)
     * @param strDate 日期字符串
     * @return 字符型日期
     */
    private String stringToStandTimeString (String strDate)
    {
        int year = Integer.parseInt(strDate.substring(0, 4));
        int month = Integer.parseInt(strDate.substring(5, 7));
        int date = Integer.parseInt(strDate.substring(8, 10));
        String strMonth = "";
        String strDay = "";
        if (month < 10)
        {
            strMonth = "0" + month;
        }
        else
        {
            strMonth = "" + month;
        }
        if (date < 10)
        {
            strDay = "0" + date;
        }
        else
        {
            strDay = "" + date;
        }
        return "" + year + strMonth + strDay;
    }

    /**
     * 得到群发部分sql
     * @param gf 当前Form对象
     * @return String 群发部分sql
     */
    private String getMultiSendString (GzsxwhForm gf)
    {
        StringBuffer sqlBuffer = new StringBuffer();
        //群发条件加入
        if (gf.getJhfs().equals("0"))
        { //结合方式:"与"
            if (gf.getQylx().equals("0") &&
                gf.getHylb().equals("0") &&
                gf.getDqjs().equals("0"))
            {
                //无条件
            }
            else
            {
                if (!gf.getQylx().equals("0"))
                {
                    sqlBuffer.append(" AND DJZCLXDM = '" + gf.getQylx() + "' ");
                }
                if (!gf.getHylb().equals("0"))
                {
                    sqlBuffer.append(" AND GJBZHYDM = '" + gf.getHylb() + "' ");
                }
                if (!gf.getDqjs().equals("0"))
                {
                    sqlBuffer.append(" AND SWJGZZJGDM2 = '" + gf.getDqjs()
                                     + "' ");
                }
            }
        }
        else if (gf.getJhfs().equals("1"))
        { //结合方式:"或"
            if (gf.getQylx().equals("0") &&
                gf.getHylb().equals("0") &&
                gf.getDqjs().equals("0"))
            {
                //无条件
            }
            else
            {
                sqlBuffer.append(" AND ( ");
                String tempSql = new String();
                if (!gf.getQylx().equals("0"))
                {
                    tempSql = tempSql + " DJZCLXDM = '" + gf.getQylx() +
                              //"' AND GJBZHYDM = '0' AND SWJGZZJGDM2='0' OR ";
                              "'  OR ";
                }
                if (!gf.getHylb().equals("0"))
                {
                    tempSql = tempSql + " GJBZHYDM = '" + gf.getHylb() +
                              //"' AND DJZCLXDM = '0' AND SWJGZZJGDM2='0' OR ";
                              "'  OR ";
                }
                if (!gf.getDqjs().equals("0"))
                {
                    tempSql = tempSql + " SWJGZZJGDM2 = '" + gf.getDqjs() +
                              //"' AND DJZCLXDM = '0' AND GJBZHYDM='0' OR ";
                              "'  OR ";
                }
                tempSql = tempSql.substring(0, tempSql.length() - 3);
                sqlBuffer.append(tempSql + ") ");
            }
        }
        return sqlBuffer.toString();
    }

    /**
     * 得到不同级别的地区局所条件
     * @param userSsdwdm 用户所属单位代码
     * @param type 据所类别
     * @return 地区级别
     * @throws BaseException
     */
    public String getDqjsCondition (String userSsdwdm, String type)
        throws
        BaseException
    {

        String selectSql = null;
        String deleteSql = null;
        try
        {
            if (userSsdwdm.startsWith("90"))
            {
                //市局，无附加条件
                selectSql = "";
                deleteSql = "";
            }
            else if (userSsdwdm.endsWith("00"))
            {
                //分局，可管辖本分局和属于本分局所有的所
                selectSql = " AND SUBSTR(SWJGZZJGDM,1,2)='"
                            + userSsdwdm.substring(0, 2) +
                            "' ";
                deleteSql = " AND SUBSTR(A.SWJGZZJGDM,1,2)='" +
                            userSsdwdm.substring(0, 2) + "' "; ;
            }
            else
            {
                //所，只能管辖本所
                selectSql = " AND SWJGZZJGDM='" + userSsdwdm + "' ";
                deleteSql = " AND A.SWJGZZJGDM='" + userSsdwdm + "' ";
            }
            if (type.equals("SELECT"))
            {
                return selectSql;
            }
            else
            {
                return deleteSql;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e);
        }
    }

}
