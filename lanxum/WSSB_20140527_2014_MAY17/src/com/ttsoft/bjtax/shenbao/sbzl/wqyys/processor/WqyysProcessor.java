package com.ttsoft.bjtax.shenbao.sbzl.wqyys.processor;

import java.sql.Connection;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.ekernel.db.or.ORManager;
import com.ekernel.db.or.ORContext;

import com.syax.bjtax.ca.util.DzyjHelper;
import com.syax.bjtax.ca.vo.DzyjsjVO;
import com.syax.common.util.CAcodeConstants;
import com.ttsoft.common.model.UserData;
import com.ttsoft.bjtax.dj.proxy.ServiceProxy;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.shenbao.model.domain.Szsm;
import com.ttsoft.bjtax.shenbao.model.domain.Wqyys;
import com.ttsoft.bjtax.shenbao.util.CAUtils;
import com.ttsoft.bjtax.shenbao.util.TinyTools;
import com.ttsoft.bjtax.shenbao.util.DBResource;
import com.ttsoft.bjtax.shenbao.util.Skssrq;
import com.ttsoft.bjtax.shenbao.zhsb.ZhsbMapConstant;
import com.ttsoft.bjtax.shenbao.sbzl.wqyys.WqyysActionConstant;
import com.ttsoft.bjtax.shenbao.sbzl.wqyys.WqyysMapConstant;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.framework.exception.*;
import com.ttsoft.bjtax.shenbao.constant.*;

/**
 * 模块设计思想--doQuery方法：
 * 	前台传入必需的资料（详细的参照查询方法的传入参数），
 * 	后台进行查询，如果存在本期申报数据，返回取得的数据。
 * 	如果不存在本期申报数据，返回空的列表。
 * 	同时传回登记的数据，供前台生成值对象模板。
 * 模块设计思想--doSave方法：
 * 	前台传入本期申报数据，
 * 	后台先进行数据库本期申报数据的删除操作，再做保存操作。
 * 	（所有出现在页面上的数据都是保存的数据，所见即所得。）
 * 模块设计思想--doDelete方法：
 * 	前台传入本期申报数据，
 * 	后台进行数据库本期申报数据的删除操作。
 *
 * @author Haifeng Su
 * @version 1.0
 * 外企营业税Processor
 */
public class WqyysProcessor implements Processor
{

    /**
     * 总控用户数据
     */
    private UserData userData;

    /**
     *orManage的常量
     */
    private static final long SESSIONID = 0;

    public Object process(VOPackage voPackage) throws BaseException
    {
        this.userData = voPackage.getUserData();
        // 根据业务操作类型值来做业务操作
        try
        {
            switch(voPackage.getAction())
            {
                case WqyysActionConstant.INT_ACTION_QUERY:
                    voPackage.setData(doQuery((Map)voPackage.getData()));
                    return voPackage;

                case WqyysActionConstant.INT_ACTION_SAVE:
                    
                    return doSave((Map)voPackage.getData());

                case WqyysActionConstant.INT_ACTION_DELETE:
                    
                    return doDelete((Map)voPackage.getData());

                default:
                    throw new SystemException("no such method");
            }
        }
        catch(Exception e)
        {
            throw ExceptionUtil.getBaseException(e);
        }
    }

    /**
     * 查询申报数据。
     * 用key为YqyysMapConstant.LIST_WQYYS放本期申报数据
     * @param data 查询参数
     * @return Map 返回结果
     * @throws BaseException
     */
    private Map doQuery(Map data) throws BaseException
    {
        //保存结果数据结构
        Map map = new HashMap(2);
        //定义数据库连接
        Connection connection = null;

        // 投资者申报数据
        List tzzsbsjList = null;

        try
        {
            // 取企业计算机代码
            String jsjdm = (String)data.get(WqyysMapConstant.JSJDM);

            // 取日期
            String skssksrq = (String)data.get(WqyysMapConstant.SKSSKSRQ);
            SWDJJBSJ jbsj = (SWDJJBSJ)data.get(WqyysMapConstant.DJJBSJ);

            Vector criteria = new Vector();
            ORContext orContext = null;

            // 取当期申报数据
            criteria.clear();
            criteria.add("JSJDM = '" + jsjdm + "'");
            criteria.add("SKSSKSRQ = TO_DATE('" + skssksrq + "', 'yyyy-MM-dd')");
            criteria.add("FSDM = '" + CodeConstant.FSDM_WSSB + "'");
            criteria.add("QXDM = '" + jbsj.getSwjgzzjgdm().substring(0, 2) + "'");
            orContext = new ORContext(Wqyys.class.getName(), criteria);

            ORManager orManager = DBResource.getORManager();
            connection = DBResource.getConnection();

            // 查询数据库取当期申报数据
            List wqyysList = orManager.query(this.SESSIONID, connection, orContext);

            // 放置返回值
            map.put(WqyysMapConstant.LIST_WQYYS, wqyysList);
        }
        catch(Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "查询操作失败!");
        }
        finally
        {
            DBResource.destroyConnection(connection);
        }
        return map;

    }

    /**
     * 保存传过来的申报数据
     * @param data 保存参数
     * @return Map 返回结果
     * @throws BaseException
     */
    private Object doSave(Map data) throws BaseException
    {
        Connection connection = null;
        DzyjsjVO dzyj=(DzyjsjVO)data.get(ZhsbMapConstant.CA_QMSJ_VO);
        UserData ud=userData;
        String skssksrq=(String)data.get("dzyj_skssksrq");
        Map reMap=new HashMap();
        try
        {
            try
	        {
	       	 dzyj = CAUtils.saveDzyjsj(ud,dzyj, skssksrq, "0", skssksrq, "0");
	       }catch (Exception ex)
	        {
	            ex.printStackTrace();
	            throw ExceptionUtil.getBaseException(ex);
	        }
	       reMap.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
           /* if (ud.getCaflag())
	        {
	            try
	            {
	                dzyj = (new DzyjHelper()).saveDzyjsj(dzyj,skssksrq, "0", skssksrq, "0");
	            }
	            catch (com.syax.frame.exception.ApplicationException e)
	            {
	                //e.printStackTrace();
	                throw ExceptionUtil.getBaseException(e);
	            }
	            catch (Exception ex)
	            {
	                ex.printStackTrace();
	                throw ExceptionUtil.getBaseException(ex);
	            }
                reMap.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ,dzyj);
	            //System.out.println("============保存签名数据结束==============");
	        }*/
            // 取申报数据使用key为WqyysMapConstant.LIST_WQYYS
            List wqyysList = (List)data.get(WqyysMapConstant.LIST_WQYYS);

            // 对存在的本期申报先删后插
            this.doDelForSave(data);

            // 获得 ORManager实例
            ORManager orManager = DBResource.getORManager();
            // 获得数据库连接
            connection = DBResource.getConnection();

            // 保存每一个值对象
            for(int i = 0, size = wqyysList.size(); i < size; i++)
            {
                orManager.makePersistent(SESSIONID, connection, wqyysList.get(i));
            }
        }
        catch(Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "保存操作失败!");
        }
        finally
        {
            DBResource.destroyConnection(connection);
        }
        return reMap;
    }

    /**
     * 删除计算机代码和所属日期指定的纪录
     * @param data 删除参数
     * @return Map 返回结果
     * @throws BaseException
     */
    private Object doDelete(Map data) throws BaseException
    {
        Connection connection = null;
        DzyjsjVO dzyj=(DzyjsjVO)data.get(ZhsbMapConstant.CA_QMSJ_VO);
        UserData ud=userData;
        String skssksrq=(String)data.get("dzyj_skssksrq");
        Map reMap=new HashMap();
        try
        {
            try
	        {
	       	 dzyj = CAUtils.saveDzyjsj(ud,dzyj, skssksrq, "0", skssksrq, "0");
	       }catch (Exception ex)
	        {
	            ex.printStackTrace();
	            throw ExceptionUtil.getBaseException(ex);
	        }
	       reMap.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
           /* if (ud.getCaflag())
	        {
	            try
	            {
	                dzyj = (new DzyjHelper()).saveDzyjsj(dzyj,skssksrq, "0", skssksrq, "0");
	            }
	            catch (com.syax.frame.exception.ApplicationException e)
	            {
	                //e.printStackTrace();
	                throw ExceptionUtil.getBaseException(e);
	            }
	            catch (Exception ex)
	            {
	                ex.printStackTrace();
	                throw ExceptionUtil.getBaseException(ex);
	            }
                reMap.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ,dzyj);
	            //System.out.println("============保存签名数据结束==============");
	        }*/
            // 取申报数据使用key为WqyysMapConstant.LIST_WQYYS
            List wqyysList = (List)data.get(WqyysMapConstant.LIST_WQYYS);

            // 取到一个值对象的实例
            Wqyys wqyys = (Wqyys)wqyysList.get(0);

            // 取计算机代码
            String jsjdm = wqyys.getJsjdm();
            // 取税款所属开始日期
            String ssksrq = TinyTools.Date2String(wqyys.getSkssksrq());

            ORManager orManager = DBResource.getORManager();
            connection = DBResource.getConnection();

            // 删除数据条件
            String condition = "JSJDM = '" + jsjdm +
                "' and SKSSKSRQ = TO_DATE('" + ssksrq + "', 'yyyy-MM-dd') " +
                " and FSDM = '" + CodeConstant.FSDM_WSSB + "' " +
                " and QXDM = '" + wqyys.getQxdm() + "'";

            // 删除申报数据
            orManager.deleteObject(SESSIONID, connection, condition, wqyys);
        }
        catch(Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "删除操作失败!");
        }
        finally
        {
            DBResource.destroyConnection(connection);
        }
        return reMap;
    }
    private Object doDelForSave(Map data) throws BaseException
    {
        Connection connection = null;
        Map reMap=new HashMap();
        try
        {
            // 取申报数据使用key为WqyysMapConstant.LIST_WQYYS
            List wqyysList = (List)data.get(WqyysMapConstant.LIST_WQYYS);

            // 取到一个值对象的实例
            Wqyys wqyys = (Wqyys)wqyysList.get(0);

            // 取计算机代码
            String jsjdm = wqyys.getJsjdm();
            // 取税款所属开始日期
            String ssksrq = TinyTools.Date2String(wqyys.getSkssksrq());

            ORManager orManager = DBResource.getORManager();
            connection = DBResource.getConnection();

            // 删除数据条件
            String condition = "JSJDM = '" + jsjdm +
                "' and SKSSKSRQ = TO_DATE('" + ssksrq + "', 'yyyy-MM-dd') " +
                " and FSDM = '" + CodeConstant.FSDM_WSSB + "' " +
                " and QXDM = '" + wqyys.getQxdm() + "'";

            // 删除申报数据
            orManager.deleteObject(SESSIONID, connection, condition, wqyys);
        }
        catch(Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "删除操作失败!");
        }
        finally
        {
            DBResource.destroyConnection(connection);
        }
        return reMap;
    }
}