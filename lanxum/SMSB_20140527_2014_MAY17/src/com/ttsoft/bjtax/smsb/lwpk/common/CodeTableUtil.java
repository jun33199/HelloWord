package com.ttsoft.bjtax.smsb.lwpk.common;

import java.util.Map;
import java.util.TreeMap;
import java.util.Iterator;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.bjtax.dj.model.dm.SWJGZZJG;

import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.CodeConstants;

/**
 * 获取税务所，分局代码表
 * 201307
 * kanght
 * @version 1.1
 */
public class CodeTableUtil
{

    /**
     * 构造方法
     */
    public CodeTableUtil()
    {
    }

    /**
     * 税务机关组织机构代码表
     */
    public final static String SWJGZZJG = "SWJGZZJG";

    //缓存中的税种税目代码对象集
    private static Map swjgzzjgMap;
  
  
    /**
     * 返回税务机关组织机构代码
     * @return Map
     * @throws BaseException
     */
    public static Map getSwjgMap() throws BaseException
    {
        Map rtnMap = new TreeMap();
        //缓存中的税种税目代码对象集为空，连接数据库重新获取
        if (swjgzzjgMap == null)
        {
            setSwjgzzjgMap();
        }
        rtnMap = swjgzzjgMap;
        return rtnMap;
    }

    public static Map getZgswjg (UserData userData)
        throws BaseException
    {
        Map swjgzzjgMap = getSwjgMap();
        Map swjgMap = new TreeMap();
        //用户级别
        if (userData == null || userData.yhjb.equals(CodeConstants.JBDM_SJ)){
        	//有市局权限
            Iterator iterator = swjgzzjgMap.keySet().iterator();
            while (iterator.hasNext())
            {
                String dm = (String) iterator.next();
                SWJGZZJG swjgzzjg = (SWJGZZJG) swjgzzjgMap.get(dm);
                if (dm.endsWith("00") == false)
                {
                    continue;
                }
                if (dm.startsWith("90") == true)
                { //过滤掉市局，因为市局不能直接作为主管税务机关
                    continue;
                }
                swjgMap.put(dm, swjgzzjg);
            }
        }
        else{
        	//非市局权限区县局权限
        	//所属单位代码
            String dm = userData.ssdwdm.substring(0, 2) + "00";
            swjgMap.put(dm, swjgzzjgMap.get(dm));
        }
        return swjgMap;
    }

    /**
     * 根据用户权限过滤税务机关--税务所代码表。
     * 市局权限：全部区县税务所代码表。
     * 区县局权限：本区县局下属全部税务所代码。
     * 税务所级全县：本所代码。
     * @param userData 用户信息
     * @return 过滤后的代码表
     * @throws BaseException 抛出应用异常
     */
    public static Map getZgsws(UserData userData) throws BaseException
    {
        Map rtnMap = new TreeMap();
        if (swjgzzjgMap == null)
        {
            setSwjgzzjgMap();
        }
        String filterdm = null;
        if (userData == null ||
            userData.yhjb.equals(CodeConstants.JBDM_SJ))
        { //有市局级权限
            filterdm = null;
        }
        else if (userData.yhjb.equals(CodeConstants.JBDM_FJ))
        { //有区县分局级权限
            filterdm = userData.ssdwdm.substring(0, 2);
        }
        else if (userData.yhjb.equals(CodeConstants.JBDM_SWSJ))
        { //税务所级权限
            filterdm = userData.ssdwdm.substring(0, 4);
            rtnMap.put(userData.ssdwdm, swjgzzjgMap.get(userData.ssdwdm));
            return rtnMap;
        }
        Iterator iterator = swjgzzjgMap.keySet().iterator();
        while (iterator.hasNext())
        {
            String dm = (String) iterator.next();
            if (dm.endsWith("00") == true)
            {
                continue;
            }
            if (filterdm != null && dm.startsWith(filterdm) == false)
            {
                continue;
            }
            rtnMap.put(dm, swjgzzjgMap.get(dm));
        }
        return rtnMap;
    }
    /**
     * 初始化税务机关组织机构代码表
     * @throws BaseException
     */
    private static void setSwjgzzjgMap() throws BaseException
    {
        //创建数据库连接
        Connection conn = null;
        ResultSet rs = null;
        Statement st;
        conn = SfDBResource.getConnection();
        swjgzzjgMap = new TreeMap();
        try
        {
            st = conn.createStatement();
            //查询sql
            String queryString = "SELECT SWJGZZJGDM,SWJGZZJGMC "
                + "FROM DMDB.GY_DM_SWJGZZJG "
                + "WHERE JGZNLX!='3'"
                + " ORDER BY SWJGZZJGDM";

            rs = st.executeQuery(queryString);
            while (rs.next())
            {
                SWJGZZJG swjg = new SWJGZZJG();
                //税务机关代码
                String swjgdm = rs.getString("SWJGZZJGDM");
                swjg.setSwjgzzjgdm(swjgdm);
                //税务机关名称
                String swjgmc = rs.getString("SWJGZZJGMC");
                swjg.setJc(swjgmc);

                swjgzzjgMap.put(rs.getString("SWJGZZJGDM"), swjg);
            }
            if (rs != null)
            {
                rs.close();
            }
        }
        catch (Exception e)
        {
            throw new ApplicationException("查询税务机关代码错误！");
        }
        finally
        {
            SfDBResource.freeConnection(conn);
        }

    }


  
}
