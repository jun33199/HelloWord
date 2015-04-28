/*
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.gtgsh.processor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import java.sql.Timestamp;
import java.math.BigDecimal;

import com.ttsoft.bjtax.pzgl.proxy.ServiceProxy;
import com.ttsoft.bjtax.sfgl.common.db.util.BeanUtil;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBUtils;
import com.ttsoft.bjtax.sfgl.common.util.Debug;
import com.ttsoft.bjtax.shenbao.model.domain.Gtgshwszmx;
import com.ttsoft.bjtax.shenbao.model.domain.Gtgshwszz;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.gtgsh.web.GtgshCxwszForm;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.bjtax.smsb.util.TinyTools;

/**
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Description: 个体工商户撤销完税证</p>
 * @author 开发六组－－陆峰
 * @version 1.1
 */

public class GtgshCxwszProcessor
    implements Processor
{
    public GtgshCxwszProcessor ()
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
        String ndzb = String.valueOf(nowTime).substring(0, 4);

        GtgshCxwszForm pf = new GtgshCxwszForm();
        pf = (GtgshCxwszForm) vo.getData();
        //年度字别
        pf.setTempNdzb(ndzb);

        try
        {
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
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
/*        String names[] =
            {
            "pzzldm", "wszh", "nsrjsjdm", "jsjdm", "swjgzzjgdm",
            "djzclxdm", "dz", "hjsjje", "clbjdm", "sbhzdh",
            "jbdh", "zjlxdm", "zjhm", "swjgzzjgdm2", "lrr",
            "fsdm", "gjbzhydm", "nd", "wszxh", "cjrq",
            "lrrq", "qxdm", "ndzb", "printflag", "nsrmc"};
*/
        String names[] =
        {
        "pzzldm", "wszh", "nsrjsjdm", "jsjdm", "swjgzzjgdm",
        "djzclxdm", "dz", "hjsjje", "clbjdm", "sbhzdh",
        "jbdh", "zjlxdm", "zjhm", "swjgzzjgdm2", "lrr",
        "fsdm", "gjbzhydm", "nd", "wszxh", "cjrq",
        "lrrq", "qxdm", "ndzb"};
        
        Connection conn = null;
        SfDBUtils sfDB = null;
        UserData ud = new UserData();
        GtgshCxwszForm pf = new GtgshCxwszForm();
        pf = (GtgshCxwszForm) vo.getData();

        try
        {
            conn = SfDBResource.getConnection();
            SfDBAccess da = new SfDBAccess(conn);
            //得到UserData
            ud = (UserData) vo.getUserData();

            //Modified by zhangyijun 2007-04-04 
            //设置查询条件
            String sql="SELECT WSZXH, GJBZHYDM, ZJLXDM, PZZLDM, JBDH, ZJHM, LRR, DZ, SWJGZZJGDM2, JSJDM, DJZCLXDM, CLBJDM, FSDM, HJSJJE, SBHZDH, WSZH, SWJGZZJGDM, NSRJSJDM, ND, QXDM, CJRQ, LRRQ, NDZB "
            	+"FROM sbdb.sb_jl_gtgshwszz a where 1=1 AND qxdm='"+ InterfaceDj.getQxdm(ud) +"' AND lrr='" + pf.getHeadLrr() + "' ";
            if (!pf.getHeadClbjdm().equals("*"))
            {
            	sql=sql+"AND clbjdm='" + pf.getHeadClbjdm() + "' ";
            }
            sql=sql+"AND exists (select b.wszxh from sbdb.sb_jl_gtgshwszmx b WHERE substr(b.jzbz,1,1)='"+CodeConstant.SMSB_JZBZ_EDIT+"' and b.wszxh=a.wszxh and b.nsrjsjdm=a.nsrjsjdm) " +
            		"AND (sbhzdh='' or sbhzdh is null) AND (jbdh='' or jbdh is null) AND ndzb='" + pf.getTempNdzb() + "' order by wszh asc";          
            ResultSet rs=da.querySQL(sql);
            BigDecimal hjsjje;
            while (rs.next()) {
            	HashMap map = new HashMap();
                for (int jj = 0; jj < names.length; jj++) {
                    if (rs.getString(names[jj]) != null) {
                        map.put(names[jj], rs.getString(names[jj]));  
                    	if(names[jj].equals("cjrq")){
                    		map.put("cjrq", rs.getString("cjrq").substring(0, 19));
                    	}if(names[jj].equals("lrrq")){
                    		map.put("lrrq", rs.getString("lrrq").substring(0, 19));
                    	}
                    	if(names[jj].equals("hjsjje")){
                    		hjsjje=new BigDecimal(rs.getString("hjsjje"));
                    		map.put("hjsjje", hjsjje.toString());                    		
                    	}                   		
                      
                    } else {
                        map.put(names[jj], "");
                    }
                }
                //设置显示值
                if (map.get("clbjdm").equals("1"))
                {
                    map.put("printflag", "已打印");
                }
                else
                {
                    map.put("printflag", "未打印");
                }
                dataList.add(map);            
            }
            //把值放回form对象
            pf.setDataList(dataList);
            rs.close();
        }
        catch (Exception ex)
        {
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
        return null;
    }

    /**
     * 删除（撤销）
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return 当前页面对应的Form对象
     * @throws BaseException
     */
    private Object doDelete (VOPackage vo)
        throws BaseException
    {
        List dataList = new ArrayList();
        String pzzldm = ""; //票证种类代码
        String zhdm = ""; //帐户代码
        String wszh = ""; //完税证号

        Connection conn = null;
        SfDBUtils sfDB = null;
        UserData ud = new UserData();
        //from对象
        GtgshCxwszForm pf = new GtgshCxwszForm();
        pf = (GtgshCxwszForm) vo.getData();
        dataList = (List) pf.getDataList();

        //ormapping对象
        Gtgshwszz orObjz = new Gtgshwszz();
        Gtgshwszmx orObjmx = new Gtgshwszmx();
        try
        {
            conn = SfDBResource.getConnection();
            SfDBAccess da = new SfDBAccess(conn);
            //得到UserData
            ud = (UserData) vo.getUserData();
            /**
             * 创建删除日志 20040429 Shi Yanfeng
             *
             */
            TinyTools.makeLog4Gtgsh(ud, pf.getHeadWszh(), pf.getHeadNsrjsjdm(),
                                    pf.getHeadWszxh());
            //的得到区县代码
            String qxdm = InterfaceDj.getQxdm(ud);
            String strSql = "delete from sbdb.sb_jl_gtgshwszmx " +
                            " where qxdm='" + qxdm + "'" +
                            " and substr(jzbz,1,1)='"
                            + CodeConstant.SMSB_JZBZ_EDIT + "'" +
                            " and ndzb='" + pf.getHeadNdzb() + "'" +
                            " and wszh='" + pf.getHeadWszh() + "'" +
                            " and nsrjsjdm='" + pf.getHeadNsrjsjdm() + "'" +
                            " and wszxh='" + pf.getHeadWszxh() + "'";
            //1、删除明细表数据
            try
            {
                da.updateSQL(strSql);
            }
            catch (BaseException ex1)
            {
                ex1.printStackTrace();
                throw new ApplicationException("删除明细表数据出错！");
            }

            //2、查出主表数据 //Modified by lufeng 2004-05-11
            Vector tempVector = new Vector();
            //tempVector.addElement("pzzldm='" + CodeConstant.SMSB_PZZLDM + "'");
            tempVector.addElement("pzzldm='" + pf.getHeadPzzldm() + "'");
            tempVector.addElement("qxdm='" + qxdm + "'");
            tempVector.addElement("(sbhzdh='' or sbhzdh is null)");
            tempVector.addElement("(jbdh='' or jbdh is null)");
            tempVector.addElement("ndzb='" + pf.getTempNdzb() + "'");
            tempVector.addElement("wszh='" + pf.getHeadWszh() + "'");
            tempVector.addElement("wszxh='" + pf.getHeadWszxh() + "'");
            tempVector.addElement("nsrjsjdm='" + pf.getHeadNsrjsjdm() + "'");

            tempVector.addElement("1=1 order by wszh asc ");
            //查询
            List tempList = da.query(Gtgshwszz.class, tempVector);
            if (tempList.size() <= 0)
            {
                throw new ApplicationException("没有符合撤销的完税证，数据可能已被修改，请重新查询！");
            }

            try
            {
                da.delete(tempList);
            }
            catch (BaseException ex1)
            {
                ex1.printStackTrace();
                throw new ApplicationException("删除主表数据出错！");
            }

            //3、调用票证接口，作废当前完税证号，并不再取号
            String result = null;
            try
            {
                result = ServiceProxy.setCancellation(ud,
                	pf.getHeadPzzldm(),
                    pf.getHeadNdzb() +
                    pf.getHeadWszh(),
                    0, "1", "0", "1");
            }
            catch (BaseException ex1)
            {
                throw new ApplicationException("撤销完税证号出错！");
            }

            pf.setHeadWszh("");
            pf.setHeadNdzb("");
            pf.setHeadWszxh("");
        }
        catch (BaseException ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
        finally
        {
            SfDBResource.freeConnection(conn);
        }
        return pf;
    }

    /**
     * 更新，该操作是为当前的完税证设置打印标记
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return 当前页面对应的Form对象
     * @throws BaseException
     */
    private Object doUpdate (VOPackage vo)
        throws BaseException
    {
        Connection conn = null;
        SfDBUtils sfDB = null;
        UserData ud = new UserData();
        //from对象
        GtgshCxwszForm pf = new GtgshCxwszForm();
        pf = (GtgshCxwszForm) vo.getData();
        //ormapping对象
        Gtgshwszz orObjz = new Gtgshwszz();
        try
        {
            conn = SfDBResource.getConnection();
            SfDBAccess da = new SfDBAccess(conn);
            //得到UserData
            ud = (UserData) vo.getUserData();
            //的得到区县代码
            String qxdm = InterfaceDj.getQxdm(ud);
            String strSql = "update sbdb.sb_jl_gtgshwszz " +
                            " set clbjdm='" + CodeConstant.SMSB_WSZ_PRINT + "'" +
                            " where ndzb='" + pf.getHeadNdzb() + "'" +
                            " and wszh='" + pf.getHeadWszh() + "'" +
                            " and qxdm='" + qxdm + "'" +
                            " and nsrjsjdm='" + pf.getHeadNsrjsjdm() + "'" +
                            " and wszxh='" + pf.getHeadWszxh() + "'";

            //1、更新数据
            try
            {
                da.updateSQL(strSql);
            }
            catch (BaseException ex1)
            {
                ex1.printStackTrace();
                throw new ApplicationException("更新数据出错！");
            }
        }
        catch (BaseException ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
        finally
        {
            SfDBResource.freeConnection(conn);
        }
        return pf;

    }

}
//:-)
