package com.ttsoft.bjtax.shenbao.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.common.model.Grzsfs;
import com.ttsoft.bjtax.sfgl.common.model.QysdsSet;
import com.ttsoft.bjtax.sfgl.common.model.Zsfs;
import com.ttsoft.bjtax.sfgl.proxy.ServiceProxy;
import com.ttsoft.bjtax.shenbao.codetable.web.CodeTableUtil;
import com.ttsoft.bjtax.shenbao.constant.CodeConstant;
import com.ttsoft.bjtax.shenbao.constant.CodeTable;
import com.ttsoft.bjtax.shenbao.model.domain.Djzclx;
import com.ttsoft.bjtax.shenbao.util.qysdsCheck.CheckBean;
import com.ttsoft.bjtax.shenbao.util.qysdsCheck.QysdsCheckUtil;
import com.ttsoft.bjtax.shenbao.util.qysdsCheck.checkElement.CheckJdlx;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.SessionKey;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.util.StringUtil;

public class SbzlAccess
{
    public SbzlAccess()
    {
    }

    /**
     * 检查权限
     * @param type actionID
     * @param request HttpServletRequest
     * @return boolean 是否有权限
     */
    public static boolean getAuthority(int type, HttpServletRequest request)
    {
        try
        {
        	System.out.println("当前过滤的类型:"+type);



            //if (type != 9991)
            //    return true;

            if (type == ALWAYSPASS)
            {
                return true;
            }

            
            if (isZrr(request))
            {
                // 自然人
                if(FriendHelper.getZrrjbsj(request) == null)
                {
                    return false;
                }
            }
            else
            {
                SWDJJBSJ jbsj = FriendHelper.getSWDJJBSJ(request);
                // 企业
                if(jbsj == null)
                {
                    return false;
                }

                if (!(jbsj.getNsrzt().equals("10") || jbsj.getNsrzt().equals("90")))
                {
                    // 非正常户
                    return false;
                }
            }
            UserData ud = (UserData)request.getSession().getAttribute(SessionKey.USER_DATA);
            
            //用以校验该企业是否在清算期，是否是应征户
            CheckBean checkBean = new CheckBean();	
            checkBean.setJsjdm(ud.getYhid());
            
            //企业清算所得税
            if (type == QYQSSDSBA2014){
            	boolean canShenbao = canDisplay(type);
            	if(canShenbao){
	            	//需要调用企业所得税应征户接口，是应征户才可以做清算
	            	Timestamp sbrq = new Timestamp(System.currentTimeMillis());
	            	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	            	checkBean.setSksj(sdf.format(sbrq));
					if(!checkJd2(checkBean))
	    			{
	    				return false;
	    			}
	            	return true;
            	}else{
            		return false;
            	}
            }
            
            //String jsjdm = ud.yhid;
            int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
            //System.out.println("type"+type+":"+propertyNames[type]+":month"+month);
            boolean canShenbao = canDisplay(type);
//            if (! canShenbao || (canShenbao && !isMonths(type, month))) return false;
            if (! (canShenbao && isMonths(type, month))) return false;
            // added by qinw 20060922 start 增加再就业减免申报征期的判断
            if (type == ZJYJMSB) {
            	Date now = new Date();
            	String zqstr = TinyTools.getProperty("WSSB_ZJYJMSB_ZQRL_MONTH_"
                        + (new SimpleDateFormat("MM")).format(now));
            	boolean withinzq = withinZq(zqstr, now);
            	return withinzq;
            }
//          added by qinw 20060922 end

//          added by xinyy 20091213 start 安置残疾人就业企业营业税减免申报征期的判断
            if(type == CJRJYJMSB) {
            	Date now = new Date();
            	String zqstr = TinyTools.getProperty("WSSB_CJRJYJMSB_ZQRL_MONTH_"
                        + (new SimpleDateFormat("MM")).format(now));
            	System.out.println(zqstr);
            	if(!(withinZq(zqstr, now))){
					return false;
				}
            }
//          added by xinyy 20091213 end 安置残疾人就业企业营业税减免申报征期的判断


//          added by lianglw 20061101 start 新的企业所得税申报表2006版
            if (type == HDZSQYJB) {
            	Date now = new Date();
            	String zqstr = TinyTools.getProperty("WSSB_HDZSQYJB_ZQRL_MONTH_"
                        + (new SimpleDateFormat("MM")).format(now));

				if(!(withinZq(zqstr, now))){
					return false;
				}
            }
            if (type == HDZSQYNB) {
            	Date now = new Date();
            	String zqstr = TinyTools.getProperty("WSSB_HDZSQYNB_ZQRL_MONTH_"
                        + (new SimpleDateFormat("MM")).format(now));

				if(!(withinZq(zqstr, now))){
					return false;
				}
				
				checkBean.createZgrqByCurrenttimeY();
				if(!checkJd(checkBean))
    			{
    				return false;
    			}
				
            }
            if (type == CZZSQYJB) {
            	Date now = new Date();
            	String zqstr = TinyTools.getProperty("WSSB_CZZSQYJB_ZQRL_MONTH_"
                        + (new SimpleDateFormat("MM")).format(now));

				if(!(withinZq(zqstr, now))){
					return false;
				}
            }
            if (type == CZZSQYNB) {
            	Date now = new Date();
            	String zqstr = TinyTools.getProperty("WSSB_CZZSQYNB_ZQRL_MONTH_"
                        + (new SimpleDateFormat("MM")).format(now));

				if(!(withinZq(zqstr, now))){
					return false;
				}
            }
            if (type == NSRJBXXB) {
            	System.out.println("----------进入纳税人基本信息表判断---------:"+checkBean.getJdlx());
            	Date now = new Date();
            	String zqstr = TinyTools.getProperty("WSSB_CZZSQYJBXXB_ZQRL_MONTH_"
                        + (new SimpleDateFormat("MM")).format(now));

				if(!(withinZq(zqstr, now))){
					return false;
				}
				
				checkBean.createZgrqByCurrenttimeY();
				if(!checkJd(checkBean))
    			{
    				return false;
    			}
				/*-modified by huohb 2014-06-11-*/
				//System.out.println("----------测试是否能录入纳税人基本信息---------:"+checkBean.getJdlx());
				//企业所得税征管范围鉴定——跨省市分支机构纳税人不能录入纳税人基本信息表
				if(CheckJdlx.QYSDSZGFWJDLX_KSSFZJGNSR.equals(checkBean.getJdlx())){
				//	 System.out.println("----------测试是否能录入纳税人基本信息---------");
                	 return false; 
                }
				
            }
//          added by lianglw 20061101 end 新的企业所得税申报表

//          added by tum 20080306 start 企业所得税申报表2008版
            //08企业所得税核定征收季报
            if(type == HDZSQYJB2008)
            {
                Date now = new Date();
                String zqstr = TinyTools.getProperty("WSSB_HDZSQYJB2008_ZQRL_MONTH_"
                    + (new SimpleDateFormat("MM")).format(now));

                if(! (withinZq(zqstr, now))) {
                    return false;
                }
//                新版不进行税源鉴定校验   暂时屏蔽
              checkBean.createZgrqByCurrenttimeM();
              if(!checkJd(checkBean))
    			{
    				return false;
    			}
            }
            //08企业所得税查帐征收季报
            if(type == CZZSQYJB2008) {
                Date now = new Date();
                String zqstr = TinyTools.getProperty("WSSB_CZZSQYJB2008_ZQRL_MONTH_"
                    + (new SimpleDateFormat("MM")).format(now));
               
                if(! (withinZq(zqstr, now))) {
                    return false;
                }
//              if(isSybsZFD(request,CodeConstant.CODE_QYSDS_SYBS_OTHER)){
//                 	return false; 
//               }
//              新版不进行税源鉴定校验   暂时屏蔽
    			checkBean.createZgrqByCurrenttimeM();
    			if(!checkJd(checkBean))
    			{
    				return false;
    			}
            }
            //2012企业所得税查帐征收年报
            if(type == CZZSQYNB2012) {
                Date now = new Date();
                String zqstr = TinyTools.getProperty("WSSB_CZZSQYNB2012_ZQRL_MONTH_"
                    + (new SimpleDateFormat("MM")).format(now));
                if(! (withinZq(zqstr, now))) {
                    return false;
                }
             
                checkBean.createZgrqByCurrenttimeY();
    			if(!checkJd(checkBean))
    			{
    				return false;
    			}
                
            }
            //08企业所得税汇总纳税分支机构分配表
            if(type == ZFJGQYJB2008) {
                Date now = new Date();
                String zqstr = TinyTools.getProperty("WSSB_ZFJGQYJB2008_ZQRL_MONTH_"
                    + (new SimpleDateFormat("MM")).format(now));

                if(! (withinZq(zqstr, now))) {
                    return false;
                }
            }
//          added by tum 20080306 end 企业所得税申报表2008版

            
            if (! isNsr(type)) return true;
            Grzsfs grzsfs = null;


            switch (type)
            {
                case CZND:  // 查账征收年报
                    grzsfs = FriendHelper.getGrzsfsInfo(request);

                    return (isGtgsh(request) && grzsfs!= null &&
                            grzsfs.getZsfsdm().equals(CodeConstant.ZSFSDM_CZZS));

                case CZJD:     // 查账征收季报
                    return false;

                case SD:  // 三代
                    return false;
//                    return FriendHelper.getSdzg(request);

                case JM:  // 减免税
                    // 没有条件
                    return true;

                case QYKS:  // 企业所得税季报
                    if (isForeignCorporation(request))
                    {
                        return false;
                    }
                    return isCorporation(request);

                case QYND:  // 企业所得税年报
                    //由于签名项目改了财务指标，造成年报无法保存。因此将其屏蔽，
                    return false;
//                    if (isForeignCorporation(request))
//                    {
//                        return false;
//                    }
//                    return isCorporation(request);

                case WQ:  // 外企营业税
                    String djzclxdm = FriendHelper.getSWDJJBSJ(request).getDjzclxdm();
                    if (!djzclxdm.equals(CodeConstant.DJZCLXDM_GATCZDBJG) &&
                        !djzclxdm.equals(CodeConstant.DJZCLXDM_WGCZDBJG))
                    {
                        return false;
                    }
//                    Djzclx djzclx = (Djzclx)CodeTableUtil.
//                        getCodeTableMap(request, CodeTable.DJZCLX_MAP).get(djzclxdm);
//                    if (!djzclx.getNwzfldm().equals(CodeConstant.DJZCLXDM_NWZFLDM_WZ) &&
//                        !djzclx.getNwzfldm().equals(CodeConstant.DJZCLXDM_NWZFLDM_GAT))
//                    {
//                        return false;
//                    }

                    return isCorporation(request) && FriendHelper.getWqzsfsInfo(request) != null;

                case YHND:  // 印花税年度
                    return true;
                case CWZB:  // 财务指标
                    //return isCorporation(request);

                    return true;

                case ZHSBWITHSFXY:
                    return ((FriendHelper.getYhkkSfxy(request) != null) && ud.getCaflag());

                case ZHSBWITHOUTSFXY:
                    return ((FriendHelper.getYhkkSfxy(request) == null) || (! ud.getCaflag()));

                case ZRRSB:
                    return (FriendHelper.getZrrjbsj(request) != null);

                case LISTJKS:
                    return true;

                case SZSM:
                    return true;
                case WSKSB:  // 无税申报
                    // 没有条件
                    return true;

                case HDYSSDL:  // 核定征收应税所得率
                    // 不用了
                    return false;

                case HDZSL: // 核定征收征收率
                    // 不用了
                    return false;

                case GRSDS:  // 个人所得税
                    // 不用了
                    return false;

//                  added by lianglw 20061101 start 新的企业所得税申报表2006版

                case HDZSQYJB:  // 核定征收企业所得税季报
                	 //System.out.println("HDZSQYJB "+type);
                    if (isForeignCorporation(request))
                    {
                        return false;
                    }

                    if (isCzzsJb(request))
                    {
                        return false;
                    }

                    return isCorporation(request);
                case HDZSQYNB:  // 核定征收企业所得税年报
                	//System.out.println("HDZSQYNB "+type);
                    if (isForeignCorporation(request))
                    {
                        return false;
                    }

                    if (isCzzsNb(request))
                    {
                        return false;
                    }
                    return isCorporation(request);
                case CZZSQYJB:  // 查账征收企业所得税季报
                	//System.out.println("CZZSQYJB "+type);
                    if (isForeignCorporation(request))
                    {
                        return false;
                    }

                    if (!(isCzzsJb(request)))
                    {
                        return false;
                    }
                    return isCorporation(request);
                case CZZSQYNB:  // 查账征收企业所得税年报
                	//System.out.println("CZZSQYNB "+type);
                    if (isForeignCorporation(request))
                    {
                        return false;
                    }

                    if (!(isCzzsNb(request)))
                    {
                        return false;
                    }
                    return isCorporation(request);
                case NSRJBXXB:  // 查账征收企业基本信息表
                	//System.out.println("NSRJBXXB "+type);
                    if (isForeignCorporation(request))
                    {
                        return false;
                    }
                    //	System.out.println("是否是查账征收年报");
                    if (!(isCzzsNb(request)))
                    {
                        return false;
                    }
                    //添加是否是税源标识是否是总地分
                    //added by wangcy 20131206 end 查账征收企业基本信息表
//                    if(isSybsZFD(request,CodeConstant.CODE_QYSDS_SYBS_OTHER)){
//                   	 return false; 
//                    }
                    //if(isSybsZFD(request,CodeConstant.CODE_QYSDS_SYBS_F)){
                     // 	return false; 
                    //}
                   // System.out.println("基本信息表自然人和个体工商户"+isCorporation(request));
                    return isCorporation(request);

//                  added by lianglw 20061101 end 新的企业所得税申报表

//              added by tum 20080306 start 企业所得税申报表2008版
                case HDZSQYJB2008:  // 2008核定征收企业所得税季报
                //System.out.println("HDZSQYNB "+type);
                     if(isForeignCorporation(request)) {
                         return false;
                     }

                     if (isCzzsJb(request)) {
                         return false;
                     }
                     return isCorporation(request);
                 case CZZSQYJB2008:  // 2008查账征收企业所得税季报
                     //System.out.println("CZZSQYJB "+type);
                     if (isForeignCorporation(request))
                     {
                         return false;
                     }
                     if (!(isCzzsJb(request)))
                     {
                         return false;
                     }
                     return isCorporation(request);
                 case CZZSQYNB2012:  // 2008查账征收企业所得税季报
                	 
                     //System.out.println("CZZSQYJB "+type);
                     if (isForeignCorporation(request))
                     {
                         return false;
                     }
                     if (!(isCzzsJb(request)))
                     {
                         return false;
                     }
                     //添加是否是税源标识是否是分
                   //  System.out.println(checkBean.getJdlx());
                     if(!checkBean.getJdlx().equals(CheckJdlx.QYSDSZGFWJDLX_KSSFZJGNSR)){
                    	 return false; 
                     }
                     if(!isSkrq(request)){
                    	 return false;
                     }
                     return isCorporation(request);
                 case ZFJGQYJB2008:  // 2008汇总纳税分支机构分配表
                    //System.out.println("CZZSQYJB "+type);
                    if (isForeignCorporation(request))
                    {
                        return false;
                    }
                    if (!(isCzzsJb(request)))
                    {
                        return false;
                    }
                    return isCorporation(request);
//          added by tum 20080306 end 企业所得税申报表2008版
//               added by xinyy 20091213 start 安置残疾人就业企业营业税减免申报表   
                 case CJRJYJMSB:
                 	return true;
//               added by xinyy 20091213 end 安置残疾人就业企业营业税减免申报表

                 case JMBASX:  // 减免备案事项
                    if (isForeignCorporation(request))
                    {
                        return false;
                    }

                    if (!(isCzzsNb(request)))
                    {
                        return false;
                    }
                    return isCorporation(request);
                  
                 //个人所得税 added by lijn 20141106
                 
                 case GRSDSNDSBB2014: //年度申报表
                	String zsfsDM = FriendHelper.getGrzsfsInfoNew(request);
                	 String djzclx= FriendHelper.getSWDJJBSJ(request).getDjzclxdm();
                	 
                	 boolean djzclxCheck = CodeConstant.DJZCLXDM_GTGSH.equals(djzclx)||CodeConstant.DJZCLXDM_GRDZQY.equals(djzclx)||CodeConstant.DJZCLXDM_GRHHQY.equals(djzclx)||CodeConstant.DJZCLXDM_SYDZQY.equals(djzclx)||CodeConstant.DJZCLXDM_SYHHQY.equals(djzclx);
                    
                	 //条件为5种登记注册类型中的一种并且征收方式为非核定
                	 if(djzclxCheck && (zsfsDM== null ||CodeConstant.ZSFSDM_CZZS.equals(zsfsDM))){
                		 return true;
                	 }else{
                		 return false;
                	 }
                 
                default:
                    return false;
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return false;
    }

    //判断qyzy 税源标识为总分 
    static private boolean isSybsZFD(HttpServletRequest request,String bs)
    {
		try {
			SWDJJBSJ  djJbsj = (SWDJJBSJ) FriendHelper.getSWDJJBSJ(request);
	    	if(bs.equals(FriendHelper.getNsrSybs(djJbsj))){
	    		return true;
	    	}else{
	    		return false;
	    	}
		} catch (BaseException e) {
			e.printStackTrace();
		}
        return false;
    }

    
    //纳税人税务登记的开业登记日期在税款所属期内，则不允许申报则在“申报明细资料录入”页面不显示企业所得税年报资料菜单
    static private boolean isSkrq(HttpServletRequest request)
    {
		try {
			SWDJJBSJ  djJbsj = (SWDJJBSJ) FriendHelper.getSWDJJBSJ(request);
	        Timestamp sbrq = new Timestamp(System.currentTimeMillis());
	        Map skssrq = new HashMap();
			skssrq = Skssrq.yearSkssrq(sbrq);

	        // 取得税款所属开始和结束日期
	        Timestamp skssksrq = (Timestamp) skssrq.get(Skssrq.SKSSKSRQ);
	        Timestamp skssjsrq = (Timestamp) skssrq.get(Skssrq.SKSSJSRQ);
	        Timestamp kydjrq=djJbsj.getKydjrq();
	        if(skssksrq.compareTo(kydjrq)>=0){
	        	return true;
	        }else{
	        	return false;
	        }
		} catch (BaseException e) {
			e.printStackTrace();
		}
        return false;
    }
    
    // 是否自然人
    static public boolean checkCzzsQy(HttpServletRequest request)
    {
        if (isZrr(request))
            {
                return false;
        }
        else
        {
            SWDJJBSJ jbsj;
			try {
				jbsj = FriendHelper.getSWDJJBSJ(request);
			} catch (BaseException e) {
				
				e.printStackTrace();
				return false;
			}
			// 企业
            if(jbsj == null)
            {
                return false;
            }

            if (!(jbsj.getNsrzt().equals("10") || jbsj.getNsrzt().equals("90")))
            {
                // 非正常户
                return false;
            }
        }
        if (isForeignCorporation(request))
        {
            return false;
        }

        if (!(isCzzsNb(request)))
        {
            return false;
        }
        return isCorporation(request);
    }

    // 是否自然人
    static private boolean isZrr(HttpServletRequest request)
    {
        UserData userData =
            (UserData)request.getSession().getAttribute(SessionKey.USER_DATA);

        return userData.yhlb.equals(com.ttsoft.common.util.CodeConstants.YHLB_ZRR);
    }

    // 是否企业
    static private boolean isCorporation(HttpServletRequest request)
    {
        return !isZrr(request) && !isGtgsh(request);
    }

    // 是否个体工商户
    static private boolean isGtgsh(HttpServletRequest request)
    {
        if (isZrr(request))
        {
            return false;
        }

        try
        {
            String djzclxdm = FriendHelper.getSWDJJBSJ(request).getDjzclxdm();
            Djzclx djzclx = (Djzclx)CodeTableUtil.
                getCodeTableMap(request, CodeTable.DJZCLX_MAP).get(djzclxdm);

            return (djzclx.getNwzfldm().equals(CodeConstant.DJZCLXDM_NWZFLDM_GTJJ) ||
                    djzclxdm.equals(CodeConstant.DJZCLXDM_SYDZQY) ||
                    djzclxdm.equals(CodeConstant.DJZCLXDM_SYHHQY));
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
    }

    
    // 是否外企
    static private boolean isForeignCorporation(HttpServletRequest request)
    {
        if (isZrr(request))
        {
            return false;
        }

        try
        {
            String djzclxdm = FriendHelper.getSWDJJBSJ(request).getDjzclxdm();
            Djzclx djzclx = (Djzclx)CodeTableUtil.
                getCodeTableMap(request, CodeTable.DJZCLX_MAP).get(djzclxdm);

            return (djzclx.getNwzfldm().equals(CodeConstant.DJZCLXDM_NWZFLDM_GAT) ||
                    djzclx.getNwzfldm().equals(CodeConstant.DJZCLXDM_NWZFLDM_WZ));
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * 根据配置的显示属性判断是否可以进行申报录入
     * @param type 申报资料类型
     * @return true能录入申报资料但须判断月份等条件，false不能录入申报资料
     */
    static private boolean canDisplay(int type)
    {
//        System.out.println("propertyNames = " + propertyNames[type] + DISPLAY_PROPERTY);
        String display = TinyTools.getProperty(propertyNames[type] + DISPLAY_PROPERTY);
//        System.out.println("display" + display);
        return display.equalsIgnoreCase("true");
    }

    /**
     * 根据配置的纳税人属性判断是否要判断纳税人类型
     * @param type 申报资料类型
     * @return true要判断纳税人类型，false不用判断纳税人类型
     */
    static private boolean isNsr(int type)
    {
        String nsr = TinyTools.getProperty(propertyNames[type] + NSR_PROPERTY);
//      System.out.println("nsr" + nsr);
        return nsr.equalsIgnoreCase("true");
    }

    /**
     * 根据配置的月份属性判断是否可以进行申报录入
     * @param type 申报资料类型
     * @param month 当前月份
     * @return true当前月份符合条件申报资料可以录入，false当前月份不符合条件
     */
    static private boolean isMonths(int type, int month)
    {
        String months = TinyTools.getProperty(propertyNames[type] + MONTHS_PROPERTY);
        if(months == null || months.equals(""))
            return true;
     //   System.out.println(months);
        String[] monthAry = TinyTools.divideString(months,
            CodeConstant.SEPARATOR);
        return StringUtil.contains(monthAry, "" + month, false);
    }

    /** 判断当前日期是否在征期内
     *  目前只有再就业减免申报使用
     * @param zqstr
     * @param now
     * @return true当前日期符合条件申报资料可以录入，false当前日期不符合条件
     */
    static public boolean withinZq(String zqstr, Date now)
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
            e.printStackTrace(System.out);
            return false;
        }
        finally
        {

        }
    }


    /**
     * 判断是否是查账征收方式 年报
     * @param swdjjbsj
     * @return
     */
    public static boolean isCzzsNb (HttpServletRequest request){

    	if (isZrr(request))
        {
            return false;
        }
    	try
        {
	    	SWDJJBSJ swdjjbsj = FriendHelper.getSWDJJBSJ(request);

	    	//当前时间
	        Timestamp sbrq = new Timestamp(System.currentTimeMillis());
//	        Map ssrq = Skssrq.getSksssq(swdjjbsj.getJsjdm(), SzsmdmConstant.QYSDS_SM, swdjjbsj.getDjzclxdm(), CodeConstant.SKLXDM_ZCJK,
//	                CodeConstant.ZQLXDM_QUARTER, sbrq, null, null, null);


	        Map skssrq = new HashMap();

			skssrq = Skssrq.yearSkssrq(sbrq);


	        // 取得税款所属开始和结束日期
	        Timestamp skssksrq = (Timestamp) skssrq.get(Skssrq.SKSSKSRQ);
	        Timestamp skssjsrq = (Timestamp) skssrq.get(Skssrq.SKSSJSRQ);

	        ServiceProxy proxy = new ServiceProxy();

	        // 查询税费接口
	        QysdsSet qysdsSet = null;
	        try
	        {
	            qysdsSet = proxy.getQysdsInfo(swdjjbsj.getJsjdm(), sbrq, skssksrq, skssjsrq, "01");//年报
	        }
	        catch (com.ttsoft.framework.exception.BaseException e)
	        {
	            e.printStackTrace();
	        }

	        Zsfs zsfs = qysdsSet.getZsfs();

	        System.out.println("当前申报的申报日期sbrq:"+sbrq);

	        System.out.println("当前申报的税款所属开始日期skssksrq:"+skssksrq);

	        System.out.println("当前申报的税款所属结束日期skssjsrq:"+skssjsrq);


	        if (zsfs != null)
	        {
	        	System.out.println("征收方式代码:"+zsfs.getZsfsdm());
	            String zsfsdm = zsfs.getZsfsdm();
	            if (zsfsdm.equals(CodeConstant.ZSFSDM_CYLZS)) // 纯益率征收
	            {
	               return false;
	            }
	            else if (zsfsdm.equals(CodeConstant.ZSFSDM_DEZS)) // 定额征收
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
            ex.printStackTrace();
            return false;
        }

    }

    /**
     * 判断是否是查账征收方式 季报
     * @param swdjjbsj
     * @return
     */
    public static boolean isCzzsJb (HttpServletRequest request){

    	if (isZrr(request))
        {
            return false;
        }
    	try
        {
    		SWDJJBSJ swdjjbsj = FriendHelper.getSWDJJBSJ(request);

    		//当前时间
	        Timestamp sbrq = new Timestamp(System.currentTimeMillis());
//	        Map ssrq = Skssrq.getSksssq(swdjjbsj.getJsjdm(), SzsmdmConstant.QYSDS_SM, swdjjbsj.getDjzclxdm(), CodeConstant.SKLXDM_ZCJK,
//	                CodeConstant.ZQLXDM_QUARTER, sbrq, null, null, null);

	        Map skssrq = new HashMap();

	        String jd = Skssrq.preQuarter(sbrq);

	        if ("4".equals(jd)) {
				skssrq = Skssrq.yearSkssrq(sbrq);
			} else {
				skssrq = Skssrq.otherSkssrq(sbrq);
			}

	        // 取得税款所属开始和结束日期
	        Timestamp skssksrq = (Timestamp) skssrq.get(Skssrq.SKSSKSRQ);
	        Timestamp skssjsrq = (Timestamp) skssrq.get(Skssrq.SKSSJSRQ);

	        ServiceProxy proxy = new ServiceProxy();

	        // 查询税费接口
	        QysdsSet qysdsSet = null;
	        try
	        {
	        	//            qysdsSet = proxy.getQysdsInfo(swdjjbsj.getJsjdm(), sbrq, skssksrq, skssjsrq, "00");
	        	//        	取得所在的季度
//				String jd = Skssrq.preQuarter(sbrq);


				if("4".equals(jd)){
					//				如果为第4季度的时候调用年报的接口
					qysdsSet = proxy.getQysdsInfo(swdjjbsj.getJsjdm(), sbrq, skssksrq, skssjsrq,"01");//年报
				}else{
					//				如果不为第4季度的时候调用季报的接口
					qysdsSet = proxy.getQysdsInfo(swdjjbsj.getJsjdm(), sbrq, skssksrq, skssjsrq,"00");//季报
				}
	        }
	        catch (com.ttsoft.framework.exception.BaseException e)
	        {
	            e.printStackTrace();
	        }

	        Zsfs zsfs = qysdsSet.getZsfs();

	        System.out.println("当前申报的季度jd:"+jd);

	        System.out.println("当前申报的申报日期sbrq:"+sbrq);

	        System.out.println("当前申报的税款所属开始日期skssksrq:"+skssksrq);

	        System.out.println("当前申报的税款所属结束日期skssjsrq:"+skssjsrq);


	        if (zsfs != null)
	        {
	        	System.out.println("征收方式代码:"+zsfs.getZsfsdm());
	            String zsfsdm = zsfs.getZsfsdm();
	            if (zsfsdm.equals(CodeConstant.ZSFSDM_CYLZS)) // 纯益率征收
	            {
	               return false;
	            }
	            else if (zsfsdm.equals(CodeConstant.ZSFSDM_DEZS)) // 定额征收
	            {
	            	return false;
	            }else if (zsfsdm.equals(CodeConstant.ZSFSDM_DLZS)) // 定率征收
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
            ex.printStackTrace();
            return false;
        }

    }


    /**
     * 查账征收年度
     */
    public static final int CZND = 0;

    /**
     * 查账征收季度
     */
    public static final int CZJD = 1;

    /**
     * 三代
     */
    public static final int SD = 2;

    /**
     * 个人所得税
     */
    public static final int GRSDS = 3;

    /**
     * 核定应税所得率
     */
    public static final int HDYSSDL = 4;

    /**
     * 核定征收率
     */
    public static final int HDZSL = 5;

    /**
     * 减免
     */
    public static final int JM = 6;

    /**
     * 企业年报
     */
    public static final int QYND = 7;

    /**
     * 企业季报
     */
    public static final int QYKS = 8;

    /**
     * 财务指标
     */
    public static final int CWZB = 9;

    /**
     * 外企
     */
    public static final int WQ = 10;

    /**
     * 印花年度
     */
    public static final int YHND = 11;

    /**
     * 有三方协议的综合申报
     */
    public static final int ZHSBWITHSFXY = 12;

    /**
     * 没有三方协议的综合申报
     */
    public static final int ZHSBWITHOUTSFXY = 13;

    /**
     * 自然人申报
     */
    public static final int ZRRSB = 14;

    /**
     * 显示缴款书列表
     */
    public static final int LISTJKS = 15;

    /**
     * 税种税目
     */
    public static final int SZSM = 16;

    /**
     * 无税申报
     */
    public static final int WSKSB = 17;
    /**
     * 再就业减免申报
     */
    public static final int ZJYJMSB = 18;

//  added by tum 20080306 start 企业所得税申报表2008版
        /**
         * 2008查帐征收季报
         */
        public static final int CZZSQYJB2008 = 19;

        /**
         * 2008核定征收季报
         */
        public static final int HDZSQYJB2008 = 20;

        /**
         * 2008汇总纳税分支机构分配表
         */
        public static final int ZFJGQYJB2008 = 21;

//  added by tum 20080306 end 企业所得税申报表2008版

//  added by lianglw 20061101 start 新的企业所得税申报表2006版



    /**
     * 核定征收企业季报
     */
    public static final int HDZSQYJB = 22;

    /**
     * 核定征收企业年报
     */
    public static final int HDZSQYNB = 23;

    /**
     * 查账征收企业季报
     */
    public static final int CZZSQYJB = 24;

    /**
     * 查账征收企业年报
     */
    public static final int CZZSQYNB = 25;

    /**
     * 纳税人基本信息表
     */
    public static final int NSRJBXXB = 26;

//  added by lianglw 20061101 end 新的企业所得税申报表

     /**
     * 安置残疾人就业申报表
     */
    public static final int CJRJYJMSB = 27;

    /**
     * 减免备案事项
     */
    public static final int JMBASX = 28;


    /**
     * 永远可以通过检查
     */
    public static final int ALWAYSPASS = 999;
//		added by wangcy  20131202 end 企业所得税年(季)度预缴纳税申报表(A类)(2012版)
    /**
     * 
     */
    public static final int CZZSQYNB2012 = 29;
    
    //企业清算所得税备案
    public static final int QYQSSDSBA2014 = 30;
    
    /*--------个人所得税升级改造 added by lijn 20141105--------------*/
   
    //个人所得税申报表
    public static final int GRSDSNDSBB2014 = 31;
    
    
    /**
     * 申报资料配置属性名数组
     */
    public static final String[] propertyNames =
        {
        "WSSB_SBZL_ACCESSCONTROL_CZZSNB",
        "WSSB_SBZL_ACCESSCONTROL_CZZSJB",
        "WSSB_SBZL_ACCESSCONTROL_SD",
        "WSSB_SBZL_ACCESSCONTROL_GRSDS",
        "WSSB_SBZL_ACCESSCONTROL_HDYSSDL",
        "WSSB_SBZL_ACCESSCONTROL_HDZSL",
        "WSSB_SBZL_ACCESSCONTROL_JM",
        "WSSB_SBZL_ACCESSCONTROL_QYSDSNB",
        "WSSB_SBZL_ACCESSCONTROL_QYSDSJB",

        "WSSB_SBZL_ACCESSCONTROL_QYCWZB",
        "WSSB_SBZL_ACCESSCONTROL_WQYYS",
        "WSSB_SBZL_ACCESSCONTROL_YHSNB",
        "WSSB_SBZL_ACCESSCONTROL_ZHSBWITHSFXY",
        "WSSB_SBZL_ACCESSCONTROL_ZHSBWITHOUTSFXY",
        "WSSB_SBZL_ACCESSCONTROL_ZRRSB",
        "WSSB_SBZL_ACCESSCONTROL_LISTJKS",
        "WSSB_SBZL_ACCESSCONTROL_SZSM",
        "WSSB_SBZL_ACCESSCONTROL_WSKSB",
        //增加再就业减免属性配置
        "WSSB_SBZL_ACCESSCONTROL_ZJYJMSB",

        //      added by tum 20080306 start 企业所得税申报表2008版
                "WSSB_SBZL_ACCESSCONTROL_CZZSQYJB2008",
                "WSSB_SBZL_ACCESSCONTROL_HDZSQYJB2008",
                "WSSB_SBZL_ACCESSCONTROL_ZFJGQYJB2008",
//      added by tum 20080306 end 企业所得税申报表2008版


//      added by lianglw 20061101 start 新的企业所得税申报表2006版
        "WSSB_SBZL_ACCESSCONTROL_HDZSQYJB",
        "WSSB_SBZL_ACCESSCONTROL_HDZSQYNB",
        "WSSB_SBZL_ACCESSCONTROL_CZZSQYJB",
        "WSSB_SBZL_ACCESSCONTROL_CZZSQYNB",
        "WSSB_SBZL_ACCESSCONTROL_CZZSQYJBXXB",

//      added by lianglw 20061101 end 新的企业所得税申报表
            //      added by xinyy 20091213 start 安置残疾人就业企业营业税减免申报表
        "WSSB_SBZL_ACCESSCONTROL_CJRJYJMSB",
//      added by xinyy 20091213 end 安置残疾人就业企业营业税减免申报表
		//减免备案事项
		"WSSB_SBZL_ACCESSCONTROL_JMBASX",
//		added by wangcy  20131202 end 企业所得税年度预缴纳税申报表(A类)(2012版)
		"WSSB_SBZL_ACCESSCONTROL_CZZSQYNB2012",
		//企业清算所得税备案
		"WSSB_SBZL_ACCESSCONTROL_QYQSSDSBA2014",
		
		//added by lijn 20141106 个人所得税
		"WSSB_SBZL_ACCESSCONTROL_GRSDSNDSBB2014"
        };

    /**
     * 申报资料配置显示属性名数组
     */
    public static String DISPLAY_PROPERTY = "_DISPLAY";

    /**
     * 申报资料配置月份属性名数组
     */
    public static String MONTHS_PROPERTY = "_MONTHS";

    /**
     * 申报资料配置纳税人属性名数组
     */
    public static String NSR_PROPERTY = "_NSR";
    
    /**
     * 网上申报减免税申报税款所属日期判断
     */
    public static String SKSSQ_PROPERTY = "_SKSSQ";

    /**
     * 该方法用以检查是否进入清算期，是否是应征户（时间段）
     * @Description: TODO
     * @param checkBean
     * @return
     */
    private static boolean checkJd(CheckBean checkBean)
    {
    	try {
    		
    		System.out.println("根据时间段查鉴定");
			QysdsCheckUtil.getInstance().check(QysdsCheckUtil.getInstance().subject1, checkBean);
		} catch (BaseException e) {
			return false;
		}
    	return true;
    }
     
    /**
     * 该方法用以检查是否是应征户（时间点）
     * @Description: TODO
     * @param checkBean
     * @return
     */
    private static boolean checkJd2(CheckBean checkBean)
    {
    	
    	try {
    		System.out.println("根据时间点查鉴定");
			QysdsCheckUtil.getInstance().check(QysdsCheckUtil.getInstance().subject2, checkBean);
		} catch (BaseException e) {
			return false;
		}
    	return true;
    }
}
