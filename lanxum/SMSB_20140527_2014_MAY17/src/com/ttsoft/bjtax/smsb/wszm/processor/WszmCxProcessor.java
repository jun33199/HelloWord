package com.ttsoft.bjtax.smsb.wszm.processor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.ttsoft.bjtax.pzgl.proxy.ServiceProxy;
import com.ttsoft.bjtax.sfgl.common.db.util.BeanUtil;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.model.client.Wszm;
import com.ttsoft.bjtax.smsb.util.Debug;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.wszm.web.WszmCxForm;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

public class WszmCxProcessor implements Processor
{
	public WszmCxProcessor ()
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

	private Object doSave(VOPackage vo) {
		// TODO Auto-generated method stub
		return null;
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
    	ArrayList dataList = new ArrayList();
    	ArrayList wszmList = new ArrayList();
        String names[] =
        {
        		"pzzldm", "wszh", "swjgzzjgdm","nsrsbh", "nsrmc", "hjje", "ndzb", "printflag", "cjrq", "lrrq", "dybz", "yxbz", "yxflag"
        };

        Connection conn = null;
        UserData ud = new UserData();
        WszmCxForm pf = (WszmCxForm) vo.getData();
        
        String wszhCon = "";
		String ndzbCon = "";
		String dybzCon = "";
		String whereCon = "";

        try
        {
        	Map dataMap = (Map) pf.getData();
        	String tempWszh = (String) dataMap.get("tempWszh");
        	String tempNdzb = (String) dataMap.get("tempNdzb");
        	String query_qsrq = (String) dataMap.get("query_qsrq");
        	String query_jzrq = (String) dataMap.get("query_jzrq");
        	String tempClbjdm = (String) dataMap.get("tempClbjdm");
        	
        	conn = SfDBResource.getConnection();
            SfDBAccess da = new SfDBAccess(conn);
            //得到UserData
            ud = (UserData) vo.getUserData();
            //得到区县代码
            String qxdm = InterfaceDj.getQxdm(ud);
            
            //完税证号
            if ((tempWszh != null) && !tempWszh.equals(""))
			 {
            	wszhCon = wszhCon + " and wszh = '" + tempWszh + "'";
			 }
			
			 //年度字别
			 if ((tempNdzb != null) && !tempNdzb.equals(""))
			 {
				 ndzbCon = ndzbCon + " and ndzb = '" + tempNdzb + "'";
			 }
			 
			 //打印标志
			 if ((tempClbjdm != null) && !tempClbjdm.equals("") && !pf.getTempClbjdm().equals("*"))
			 {
				 dybzCon = dybzCon + " and dybz = '" + tempClbjdm + "'";
			 }
			 
			 //入库时间
			 if ((query_qsrq != null) && !query_qsrq.equals(""))
			 {
				 whereCon = whereCon + " and cjrq>=to_date('" + query_qsrq +
				    " 00:00:00','yyyy-mm-dd hh24:mi:ss')";
			 }
			 if ((query_jzrq != null) && !query_jzrq.equals(""))
			 {
				 whereCon = whereCon + " and cjrq<=to_date('" + query_jzrq +
		            " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
			 }
            
            //设置查询条件
            StringBuffer querySql = new StringBuffer();
			querySql.append("select * from SBDB.SB_JL_KJSSWSZM ");
			querySql.append("where yxbz in ('"+CodeConstant.WSZM_YXBZ_0+"','"+CodeConstant.WSZM_YXBZ_1+"')");
			querySql.append(wszhCon);
			querySql.append(ndzbCon);
			querySql.append(dybzCon);
			querySql.append(whereCon);
			querySql.append(" and  zhdm = '"+pf.getHeadZhdm()+"' ");
			querySql.append("order by wszh,ndzb,pzzldm");
					
			Debug.out("WszmQuerySql=== wszm ==" + querySql);
			//查询
			ResultSet rs = da.querySQL(querySql.toString());
			while (rs.next()) 
			{
				Wszm wszm = new Wszm();
	            
				wszm.setWszh(rs.getString("wszh"));
				wszm.setPzzldm(rs.getString("pzzldm"));
				wszm.setNdzb(rs.getString("ndzb"));
				wszm.setHjje(rs.getBigDecimal("HJJE"));
	            wszm.setPzlydm(rs.getString("pzlydm"));
	            wszm.setNsrsbh(rs.getString("nsrsbh"));
	            wszm.setNsrmc(rs.getString("nsrmc"));
				wszm.setYpzh(rs.getString("ypzh"));
				wszm.setYwszh(rs.getString("ywszh"));
				wszm.setYpzzldm(rs.getString("ypzzldm"));
				wszm.setYndzb(rs.getString("yndzb"));
				wszm.setDybz(rs.getString("dybz"));
				wszm.setDycs(rs.getString("dycs"));
				wszm.setYxbz(rs.getString("yxbz"));
				wszm.setCjrq(rs.getTimestamp("cjrq"));
				wszm.setLrrq(rs.getTimestamp("lrrq"));
				 
				dataList.add(wszm);
			}
            if (dataList.size() <= 0)
            {
                throw new ApplicationException("没有符合条件的记录！");
            }
            for (int i = 0; i < dataList.size(); i++)
            {
                //格式化每条纪录
            	Wszm tmpWszm = (Wszm) dataList.get(i);
                //将值对象的值赋给Map
                HashMap map = new HashMap();
                try
                {
                    BeanUtil.copyBeanToMap(names, tmpWszm, map);
                }
                catch (Exception ex1)
                {
                    ex1.printStackTrace();
                    throw new ApplicationException("数据转换出错！");
                }

                //map.put("hjsjje",wszz.getHjsjje().toString());
                map.put("cjrq", tmpWszm.getCjrq().toString().substring(0, 19));
                map.put("lrrq", tmpWszm.getLrrq().toString().substring(0, 19));
                //设置显示值
                if (map.get("dybz").equals("1"))
                {
                    map.put("printflag", "已打印");
                }
                else
                {
                    map.put("printflag", "未打印");
                }
                
                //设置显示值
                if (map.get("yxbz").equals("1"))
                {
                    map.put("yxflag", "已作废");
                }
                else
                {
                    map.put("yxflag", "未作废");
                }
                wszmList.add(map);
            }
            //把值放回form对象
            pf.setDataList(wszmList);

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
        return pf;
    }
    
    
    /**
     * 更新有效标志
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return 当前页面对应的Form对象
     * @throws BaseException
     */
    private Object doDelete (VOPackage vo)
        throws BaseException
    {
        Connection conn = null;
        UserData ud = new UserData();
        //from对象
        WszmCxForm pf = new WszmCxForm();
        pf = (WszmCxForm) vo.getData();
        
        try
        {
            conn = SfDBResource.getConnection();
            SfDBAccess da = new SfDBAccess(conn);
            //得到UserData
            ud = (UserData) vo.getUserData();
            //的得到区县代码
            String qxdm = InterfaceDj.getQxdm(ud);
            String strSql = "update sbdb.sb_jl_kjsswszm " +
                            " set yxbz='" + CodeConstant.WSZM_YXBZ_1 + "'" +
                            " where ndzb='" + pf.getHeadNdzb() + "'" +
                            " and pzzldm='" + pf.getHeadPzzldm() + "'" +
                            " and wszh='" + pf.getHeadWszh() + "'"+
                            " and zhdm='" + pf.getHeadZhdm() + "'";

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
            
            //2、调用票证接口，作废当前完税证号，并不再取号
            try
            {
                String result = ServiceProxy.setCancellation(ud,
                	pf.getHeadPzzldm(),
                    pf.getHeadNdzb() +
                    pf.getHeadWszh(),
                    0, "1", "0", "0");
            }
            catch (Exception ex1)
            {
                throw new ApplicationException("撤销完税证号出错！");
            }
            pf.setHeadWszh("");
            pf.setHeadNdzb("");
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
     * 更新打印标志
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return 当前页面对应的Form对象
     * @throws BaseException
     */
    private Object doUpdate (VOPackage vo)
        throws BaseException
    {
        Connection conn = null;
        UserData ud = new UserData();
        //from对象
        WszmCxForm pf = new WszmCxForm();
        pf = (WszmCxForm) vo.getData();
        
        try
        {
            conn = SfDBResource.getConnection();
            SfDBAccess da = new SfDBAccess(conn);
            //得到UserData
            ud = (UserData) vo.getUserData();
            //的得到区县代码
            String qxdm = InterfaceDj.getQxdm(ud);
            String strSql = "update sbdb.sb_jl_kjsswszm " +
                            " set dybz='" + CodeConstant.SMSB_WSZ_PRINT + "'" +
                            " where ndzb='" + pf.getHeadNdzb() + "'" +
                            " and pzzldm='" + pf.getHeadPzzldm() + "'" +
                            " and wszh='" + pf.getHeadWszh() + "'";

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
