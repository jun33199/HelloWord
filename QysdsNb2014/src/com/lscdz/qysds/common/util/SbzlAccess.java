package com.lscdz.qysds.common.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.lscdz.hlwdsj.hxzgframe.Yh;
import com.lscdz.qysds.common.codetable.CodeTableKey;
import com.lscdz.qysds.common.codetable.vo.dj_dm_djzclx;
import com.lscdz.qysds.common.service.ServiceManager;
import com.lscdz.qysds.common.service.QysdsInfo.bo.QysdsSet;
import com.lscdz.qysds.common.service.QysdsInfo.bo.Zsfs;
import com.lscdz.qysds.common.service.djjbsj.bo.Djjbsj;
import com.lscdz.qysds.common.service.qysdsCheck.JdlxContant;
import com.lscdz.qysds.common.service.qysdsCheck.bo.CheckBean;
import com.lscdz.util.codetable.CodeTableManager;
import yangjian.frame.util.FrameException;
import yangjian.frame.util.ResourceManager;

public class SbzlAccess {
	public static final String NSRJBXXB="NSRJBXXB";//纳税人基本信息表
	public static final String YHLB_ZRR = "03";//自然人
	public static final String DJZCLXDM_NWZFLDM_GAT = "1";//内外资分类代码：港澳台
	public static final String DJZCLXDM_NWZFLDM_WZ = "2";//内外资分类代码：外资
	public static final String ZSFSDM_CYLZS = "01";//纯益率征收
	public static final String ZSFSDM_DEZS = "02";//定额征收
	public static final String DJZCLXDM_NWZFLDM_GTJJ = "3";//内外资分类代码：个体经营
	public static final String DJZCLXDM_SYDZQY = "171";//登记注册类型代码：私营独资企业
	public static final String DJZCLXDM_SYHHQY = "172";//登记注册类型代码：私营合伙企业

	
	 /** 判断当前日期是否在征期内
     *  目前只有再就业减免申报使用
     * @param zqstr
     * @param now
     * @return true当前日期符合条件申报资料可以录入，false当前日期不符合条件
     */
	public static boolean withinZq(String zqstr, Date now)
    {
        try
        {
            int fromDate = Integer.parseInt(zqstr.substring(0, 4));
            int toDate = Integer.parseInt(zqstr.substring(5));
            int nowDate = Integer.parseInt((new SimpleDateFormat("MMdd")).format(now));
            if (nowDate <= toDate && nowDate >= fromDate)
            {
                return true;
            }
            return false;
        }
        catch (Exception e)
        {
        	System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            e.printStackTrace(System.out);
            return false;
        }
    }
	 /**
     * 根据properties的名称，从配置文件中读取该properties对应的值。
     * @param propertyName properties的名称
     * @return java.lang.String
     */
    public static String getProperty(String propertyName) throws FrameException
    {
    	 String value = "";
         Connection con = null;
         Statement st = null;
  		 ResultSet rs = null;
  		 String sqlWhere="";
         try {
        	con = ResourceManager.getConnection();
 			st = con.createStatement();
 			sqlWhere = "SELECT * FROM sbdb.sb_jl_properties Where propertyname = '" + propertyName +"' AND zxbs = '0'";
 			rs = st.executeQuery(sqlWhere);
 			int i=0;
 			while(rs.next())
			{
 				if(i==0){
 					value=rs.getString("PROPERTYVALUE");
 				}
 				i++;
			}
 			 if (i == 0) {
                 return ""; // 没有可以维护的申报数据！
             }
             if (value == null) {
                 value = "";
             }
         }catch (Exception e) {
        	 System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
             throw new FrameException("获取配置文件properties值失败！");
         }finally{
 			try { if (rs != null) rs.close(); } catch(Exception exx) {}
 			try { if (st != null) st.close(); } catch(Exception exx) {}
 			try { if (con != null) con.close(); } catch(Exception exx) {}
 		}
        return value.trim();
    }
    /**
     * 判断纳税人基本信息是否可录入可编辑
     * @param yhxx
     * @return
     * @throws FrameException
     */
    public static boolean isNsrjbxxEditable(Yh yhxx) throws FrameException{
        CheckBean checkBean = new CheckBean();	
        checkBean.setJsjdm(yhxx.getYhid());
    	System.out.println("----------进入纳税人基本信息表判断---------");
    	Date now = new Date();
    	String zqstr = getProperty("WSSB_CZZSQYJBXXB_ZQRL_MONTH_"
                + (new SimpleDateFormat("MM")).format(now));

		if(!(withinZq(zqstr, now))){
			return false;
		}		
		if(!checkJd(checkBean))
		{
			return false;
		}
		//企业所得税征管范围鉴定——跨省市分支机构纳税人不能录入纳税人基本信息表
		if(JdlxContant.QYSDSZGFWJDLX_KSSFZJGNSR.equals(checkBean.getJdlx())){
        	 return false; 
        }	
		
				
        if (isForeignCorporation(yhxx))
        {
            return false;
        }
        if (!(isCzzsNb(yhxx)))
        {
            return false;
        }
        return isCorporation(yhxx);
    }
    
    /**
     * 该方法用以检查是否进入清算期，是否是应征户（时间段）
     * @Description: TODO
     * @param checkBean
     * @return
     */
    private static boolean checkJd(CheckBean checkBean)
    {
    	Connection conn=null;
    	try {    		
    		System.out.println("根据时间段查鉴定");   		
    		checkBean.setCheckJdlx(true);
    		checkBean.setCheckQsq(true);
    		checkBean.setCheckZfba(true);
    		checkBean.setJdlxCheckStyle(JdlxContant.JDLX_CHECKSTYLE_SKSSRQQZ);
    		checkBean.createZgrqByCurrenttimeY();
    		conn=ResourceManager.getConnection();
    		ServiceManager.getInstance().getQysdsCheckServer().check(conn, checkBean);
		} catch (FrameException e) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			return false;
		}finally{
        	if(conn!=null){
        		try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
        	}
        }
    	return true;
    }
    
    /**
     * 是否外企
     * @param yhxx
     * @return
     */
    @SuppressWarnings("unchecked")
	private static boolean isForeignCorporation(Yh yhxx)
    {
        if (isZrr(yhxx))
        {
            return false;
        }
        Connection conn=null;
        try
        {
        	conn=ResourceManager.getConnection();
            Djjbsj djjbsj=ServiceManager.getInstance().getDjjbsjService().query(conn, yhxx.getYhid());
            List<dj_dm_djzclx> list=CodeTableManager.getCodeTableList(CodeTableKey.DJ_DM_DJZCLX);
            dj_dm_djzclx djzclx=null;
            for(dj_dm_djzclx djzclxVo:list){
            	if(djzclxVo.getDjzclxdm().equals(djjbsj.getDjzclxdm())){
            		djzclx=djzclxVo;
            		break;
            	}
            }
            return (djzclx.getNwzfldm().equals(DJZCLXDM_NWZFLDM_GAT) ||
                    djzclx.getNwzfldm().equals(DJZCLXDM_NWZFLDM_WZ));
        }
        catch (Exception ex)
        {
        	System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            ex.printStackTrace();
            return false;
        }finally{
        	if(conn!=null){
        		try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
        	}
        }
    }
    
    /**
     * 是否自然人
     * @param yhxx
     * @return
     */
    private static boolean isZrr(Yh yhxx)
    {
        return yhxx.getYhlb().equals(YHLB_ZRR);
    }
    
    /**
     * 判断是否是查账征收方式 年报
     * @param swdjjbsj
     * @return
     */
    public static boolean isCzzsNb (Yh yhxx){

    	if (isZrr(yhxx))
        {
            return false;
        }
    	Connection conn=null;
    	try
        {
	    	//当前时间
	        Timestamp sbrq = new Timestamp(System.currentTimeMillis());
	        Map skssrq = new HashMap();

			skssrq = Skssrq.yearSkssrq(sbrq);


	        // 取得税款所属开始和结束日期
	        Timestamp skssksrq = (Timestamp) skssrq.get(Skssrq.SKSSKSRQ);
	        Timestamp skssjsrq = (Timestamp) skssrq.get(Skssrq.SKSSJSRQ);
        
	        conn=ResourceManager.getConnection();
	        // 查询税费接口
	        QysdsSet qysdsSet=ServiceManager.getInstance().getQysdsInfoServer().getQysdsInfo(conn, yhxx.getYhid(), sbrq, skssksrq, skssjsrq, "01");//年报
	        Zsfs zsfs = qysdsSet.getZsfs();
	        System.out.println("当前申报的申报日期sbrq:"+sbrq);

	        System.out.println("当前申报的税款所属开始日期skssksrq:"+skssksrq);

	        System.out.println("当前申报的税款所属结束日期skssjsrq:"+skssjsrq);


	        if (zsfs != null)
	        {
	        	System.out.println("征收方式代码:"+zsfs.getZsfsdm());
	            String zsfsdm = zsfs.getZsfsdm();
	            if (zsfsdm.equals(ZSFSDM_CYLZS)) // 纯益率征收
	            {
	               return false;
	            }
	            else if (zsfsdm.equals(ZSFSDM_DEZS)) // 定额征收
	            {
	            	return false;
	            }
	            else
	            	return true; //查账征收

	        }
	        return  true;
        }
        catch (Exception ex)
        {
        	System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            ex.printStackTrace();
            return false;
        }finally{
        	if(conn!=null){
        		try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
        	}
        }

    }   
    /**
     * 是否企业
     * @param yhxx
     * @return
     */
    private static boolean isCorporation(Yh yhxx)
    {
        return !isZrr(yhxx) && !isGtgsh(yhxx);
    }

    /**
     * 是否个体工商户
     * @param yhxx
     * @return
     */
    @SuppressWarnings("unchecked")
	private static boolean isGtgsh(Yh yhxx)
    {
        if (isZrr(yhxx))
        {
            return false;
        }
        Connection conn=null;
        try
        {
        	conn=ResourceManager.getConnection();
            Djjbsj djjbsj=ServiceManager.getInstance().getDjjbsjService().query(conn, yhxx.getYhid());
            String djzclxdm=djjbsj.getDjzclxdm();
            List<dj_dm_djzclx> list=CodeTableManager.getCodeTableList(CodeTableKey.DJ_DM_DJZCLX);
            dj_dm_djzclx djzclx=null;
            for(dj_dm_djzclx djzclxVo:list){
            	if(djzclxVo.getDjzclxdm().equals(djzclxdm)){
            		djzclx=djzclxVo;
            		break;
            	}
            }
            return (djzclx.getNwzfldm().equals(DJZCLXDM_NWZFLDM_GTJJ) ||
                    djzclxdm.equals(DJZCLXDM_SYDZQY) ||
                    djzclxdm.equals(DJZCLXDM_SYHHQY));
        }
        catch (Exception ex)
        {
        	System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            ex.printStackTrace();
            return false;
        }finally{
        	if(conn!=null){
        		try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
        	}
        }
    }
    
    public static boolean checkinZq(String propertyname) throws FrameException{
    	try
        {
	    	String canDisplay=getProperty("WSSB_SBZL_ACCESSCONTROL_"+propertyname+"_DISPLAY");
	    	if(canDisplay.equalsIgnoreCase("true")){
	    		String zqStr=getProperty("WSSB_"+propertyname+"_ZQRL_MONTH_"+ (new SimpleDateFormat("MM")).format(new Date()));
	    		if (withinZq(zqStr, new Date())){
	    			return true;
	    		}else{
	    			return false;
	    		}
	    	}else{
	    		return false;
	    	}
	    }
    	catch (Exception ex)
        {
    		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            return false;
        }
    }
}
