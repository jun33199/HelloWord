package com.ttsoft.bjtax.shenbao.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import com.ttsoft.bjtax.dj.DjOuterConstant;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.dj.model.ZRRJBSJ;
import com.ttsoft.bjtax.sfgl.common.model.Grzsfs;
import com.ttsoft.bjtax.sfgl.common.model.Sfxy;
import com.ttsoft.bjtax.sfgl.common.model.Wqzsf;
import com.ttsoft.bjtax.shenbao.constant.CodeConstant;
import com.ttsoft.bjtax.shenbao.model.client.Ysjc;
import com.ttsoft.bjtax.shenbao.model.domain.Zqrl;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.SessionKey;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.StringUtil;

public class FriendHelper
{

    /**
     * 生成缴款书的接口中对纳税人判断的配置属性
     */
    public static final String[] propertyNames =
        {
        "WSSB_JIEKOU_NSPG_EXCLUDES",
        "WSSB_JIEKOU_SBSJ_EXCLUDES",
        "WSSB_JIEKOU_SBSJWITHYH_EXCLUDES"};

    /**
     * 生成缴款书的接口中对纳税人判断的配置属性
     */
    public static String NSRZT_PROPERTY = "_NSRZT";

    /**
     * 生成缴款书的接口中对税务机关判断的配置属性
     */
    public static String SWJG_PROPERTY = "_SWJG";

	public FriendHelper()
    {
    }

    public static UserData getUserData(HttpServletRequest request)
    {
        return (UserData)request.getSession().getAttribute(SessionKey.USER_DATA);
    }

    /**
     * 取登记信息
     * @param request HttpServletRequest
     * @return Map
     * @throws BaseException
     */
    public static Map getDjInfo(HttpServletRequest request) throws BaseException
    {
//        HttpSession session = request.getSession();
        Map djMap = null;//(Map)session.getAttribute("DJ_KEY");
        if (djMap != null)
        {
            return djMap;
        }

        String jsjdm = (String)((UserData)request.getSession().getAttribute(SessionKey.USER_DATA)).yhid;
        com.ttsoft.bjtax.dj.proxy.ServiceProxy proxy
            = new com.ttsoft.bjtax.dj.proxy.ServiceProxy();

        try
        {
            djMap = proxy.getDjInfo(jsjdm);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            djMap = null;
        }

//        session.setAttribute("DJ_KEY", djMap);
        return djMap;
    }

    /**
     * 取登记信息
     * @param jsjdm String
     * @return Map
     * @throws BaseException
     */
    public static Map getDjInfo(String jsjdm) throws BaseException
    {
        com.ttsoft.bjtax.dj.proxy.ServiceProxy proxy
            = new com.ttsoft.bjtax.dj.proxy.ServiceProxy();

        return proxy.getDjInfo(jsjdm);
    }

    /**
     * 取登记信息
     * @param jsjdm String
     * @return Map
     * @throws BaseException
     */
    public static SWDJJBSJ getDjJbsj(String jsjdm) throws BaseException
    {
        com.ttsoft.bjtax.dj.proxy.ServiceProxy proxy
            = new com.ttsoft.bjtax.dj.proxy.ServiceProxy();
       Map map = proxy.getDjInfo(jsjdm);
       SWDJJBSJ jbsj= (SWDJJBSJ) map.get("JBSJ");
       return jbsj;
    }

    /**
     * 取登记SWDJJBSJ信息
     * @param jsjdm String
     * @return SWDJJBSJ
     * @throws BaseException
     */
    public static SWDJJBSJ getSWDJJBSJ(String jsjdm) throws BaseException
    {
        Map djMap = getDjInfo(jsjdm);
        SWDJJBSJ djjbsj = (SWDJJBSJ)djMap.get("JBSJ");
        return djjbsj;
    }

    /**
     * 取登记SWDJJBSJ信息
     * @param request HttpServletRequest
     * @return SWDJJBSJ
     * @throws BaseException
     */
    public static SWDJJBSJ getSWDJJBSJ(HttpServletRequest request) throws BaseException
    {
        Map djMap = getDjInfo(request);
        SWDJJBSJ djjbsj = (SWDJJBSJ)djMap.get("JBSJ");
        return djjbsj;
    }

    public static Sfxy getSfxy(HttpServletRequest request) throws BaseException
    {
//        HttpSession session = request.getSession();
        Sfxy sfxy = null;//(Sfxy)session.getAttribute("SFGL_SFXY");

        if (sfxy == null)
        {
            com.ttsoft.bjtax.sfgl.proxy.ServiceProxy proxy =
                new com.ttsoft.bjtax.sfgl.proxy.ServiceProxy();

            String jsjdm = ( (UserData)request.getSession().getAttribute(SessionKey.USER_DATA)).yhid;

            sfxy = proxy.getSfxyInfo(jsjdm, new Date());

//            session.setAttribute("SFGL_SFXY", sfxy);
        }

        return sfxy;
    }

    public static Sfxy getYhkkSfxy(HttpServletRequest request) throws BaseException
    {
//        HttpSession session = request.getSession();
        Sfxy sfxy = null;//(Sfxy)session.getAttribute("SFGL_SFXY");

        if (sfxy == null)
        {
            com.ttsoft.bjtax.sfgl.proxy.ServiceProxy proxy =
                new com.ttsoft.bjtax.sfgl.proxy.ServiceProxy();

            String jsjdm = ( (UserData)request.getSession().getAttribute(SessionKey.USER_DATA)).yhid;

            sfxy = proxy.getSfxySskkh(jsjdm);

//            session.setAttribute("SFGL_SFXY", sfxy);
        }

        return sfxy;
    }


    public static Grzsfs getGrzsfsInfo(HttpServletRequest request) throws BaseException
    {
//        HttpSession session = request.getSession();
        Grzsfs grzsfs = null;//(Grzsfs)session.getAttribute("SFGL_GRZSFS");

        if (grzsfs == null)
        {
            String jsjdm = ( (UserData)request.getSession().getAttribute(SessionKey.USER_DATA)).yhid;

            com.ttsoft.bjtax.sfgl.proxy.ServiceProxy proxy =
                new com.ttsoft.bjtax.sfgl.proxy.ServiceProxy();

            // 年报传上年最后一天
            // 季报传上月最后一天
            Date param = null;
            Date now = new Date();
            if (now.getMonth() < 3)
            {
                param = getLastDayOfPreviousYear(now);
            }
            else
            {
                param = getLastDayOfPreviousQuarter(now);
            }
            grzsfs = proxy.getGrzsfsInfo(jsjdm, param);

//            session.setAttribute("SFGL_GRZSFS", grzsfs);
        }

        return grzsfs;
    }

    /**
     * @param request2015版个人所得税获取征收方式
     * @return
     * @throws BaseException
     */
    public static String getGrzsfsInfoNew(HttpServletRequest request) throws BaseException
    {      
            String jsjdm = ( (UserData)request.getSession().getAttribute(SessionKey.USER_DATA)).yhid;

            String zsfsDM = null;
            String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR)-1);
          
            Connection conn = DBResource.getConnection();
            String querySQL = "select zsfsdm  from  sfdb.SF_JL_GRHHZSFS  where jsjdm=? and rdnd=?";
            try {
				PreparedStatement ps =conn.prepareStatement(querySQL);
				ps.setString(1, jsjdm);
				ps.setString(2, year);
				ResultSet rs = ps.executeQuery();
				if(rs.next()){
					zsfsDM = rs.getString("zsfsdm");
				}
			} catch (SQLException e) {
				throw ExceptionUtil.getBaseException(e);
			}
            return zsfsDM;
    }
    
    public static boolean getSdzg(HttpServletRequest request) throws BaseException
    {
//        HttpSession session = request.getSession();
//        Boolean sd = null;//(Boolean)session.getAttribute("SFGL_SDZG");

//        if (sd == null)
//        {
            com.ttsoft.bjtax.sfgl.proxy.ServiceProxy proxy =
                new com.ttsoft.bjtax.sfgl.proxy.ServiceProxy();

            String jsjdm = ( (UserData)request.getSession().getAttribute(SessionKey.USER_DATA)).yhid;

            boolean sdzg = proxy.getSdzg(jsjdm, new Date());

//            session.setAttribute("SFGL_SDZG", sd = new Boolean(sdzg));
//        }

        return sdzg;//sd.booleanValue();
    }

    public static Wqzsf getWqzsfsInfo(HttpServletRequest request) throws BaseException
    {
//        HttpSession session = request.getSession();
        Wqzsf wqzsf = null;//(Wqzsf)session.getAttribute("SFGL_WQZSF");

        if (wqzsf == null)
        {
            com.ttsoft.bjtax.sfgl.proxy.ServiceProxy proxy =
                new com.ttsoft.bjtax.sfgl.proxy.ServiceProxy();

            String jsjdm = ( (UserData)request.getSession().getAttribute(SessionKey.USER_DATA)).yhid;

            wqzsf = proxy.getWqzsfsInfo(jsjdm, getLastDayOfPreviousQuarter(new Date()));

//            session.setAttribute("SFGL_WQZSF", wqzsf);
        }

        return wqzsf;
    }

    public static ZRRJBSJ getZrrjbsj(HttpServletRequest request) throws BaseException
    {
//        HttpSession session = request.getSession();
        Map zrr = null;//(Map)session.getAttribute("DJ_ZRR");

        if (zrr == null)
        {
            String jsjdm =
                ((UserData)request.getSession().getAttribute(SessionKey.USER_DATA)).yhid;

            com.ttsoft.bjtax.dj.proxy.ServiceProxy djservice
                = new com.ttsoft.bjtax.dj.proxy.ServiceProxy();

            zrr = djservice.getZrrDjInfo(jsjdm);

//            session.setAttribute("DJ_ZRR", zrr);
        }

        return (ZRRJBSJ)zrr.get(DjOuterConstant.ZRRJBSJ);
    }



    public static ZRRJBSJ getZrrjbsj(String jsjdm) throws BaseException
    {
//        HttpSession session = request.getSession();
        Map zrr = null;//(Map)session.getAttribute("DJ_ZRR");

        if (zrr == null)
        {

            com.ttsoft.bjtax.dj.proxy.ServiceProxy djservice
                = new com.ttsoft.bjtax.dj.proxy.ServiceProxy();

            zrr = djservice.getZrrDjInfo(jsjdm);

//            session.setAttribute("DJ_ZRR", zrr);
        }

        return (ZRRJBSJ)zrr.get(DjOuterConstant.ZRRJBSJ);
    }


    public static Map getZrrDjInfo(HttpServletRequest request) throws BaseException
    {
        Map zrr = null;

        String jsjdm =
            ( (UserData)request.getSession().getAttribute(SessionKey.USER_DATA)).
            yhid;

        com.ttsoft.bjtax.dj.proxy.ServiceProxy djservice
            = new com.ttsoft.bjtax.dj.proxy.ServiceProxy();

        zrr = djservice.getZrrDjInfo(jsjdm);
        return zrr;
    }

    public static Ysjc getYsjc(String jsjdm, String szsmdm, Date rq)
    {
        Ysjc ysjc = Ysjc.getYsjc(Ysjc.YSJCDM_DF);  // 其他情况都为地方级
//        if(szsmdm.substring(0, 2).equals(SzsmdmConstant.WHSYJSF) ||
//           szsmdm.substring(0, 2).equals(SzsmdmConstant.CSJTFWSYF))
//        {
            //调用税费管理接口得到预算级次
            try
            {
                com.ttsoft.bjtax.sfgl.proxy.ServiceProxy sfServiceProxy = new
                    com.ttsoft.bjtax.sfgl.proxy.ServiceProxy();
                com.ttsoft.bjtax.sfgl.common.model.Ysjc sfysjc = sfServiceProxy.
                    getYsjcInfo(jsjdm, szsmdm, rq);
                if(sfysjc != null)
                {
                    ysjc = new Ysjc(sfysjc.getYsjcdm(), sfysjc.getYsjcmc());
                }
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
//        }

        return ysjc;
    }

    // 上个季度最后一天
    private static Date getLastDayOfPreviousQuarter(Date now)
    {
        Calendar cc = new GregorianCalendar();
        cc.setTime(now);
        int month = cc.get(Calendar.MONTH);
        if (month < Calendar.APRIL)
        {
            // 一季度
            cc.set(Calendar.YEAR, cc.get(Calendar.YEAR)-1);
            cc.set(Calendar.MONTH, Calendar.DECEMBER);
        }
        else if(month < Calendar.JULY)
        {
            // 二季度
            cc.set(Calendar.MONTH, Calendar.MARCH);
        }
        else if(month < Calendar.OCTOBER)
        {
            // 三季度
            cc.set(Calendar.MONTH, Calendar.JUNE);
        }
        else
        {
            // 四季度
            cc.set(Calendar.MONTH, Calendar.SEPTEMBER);
        }

        cc.set(Calendar.DAY_OF_MONTH, cc.getActualMaximum(Calendar.DAY_OF_MONTH));

        return cc.getTime();
    }

    // 上个月最后一天
    private static Date getLastDayOfPreviousMonth(Date now)
    {
        Calendar cc = new GregorianCalendar();
        cc.setTime(now);

        cc.add(Calendar.MONTH, -1);

        cc.set(Calendar.DAY_OF_MONTH, cc.getActualMaximum(Calendar.DAY_OF_MONTH));

        return cc.getTime();
    }

    // 上年最后一天
    private static Date getLastDayOfPreviousYear(Date now)
    {
        Calendar cc = new GregorianCalendar();
        cc.setTime(now);

        cc.add(Calendar.YEAR, -1);
        cc.set(Calendar.MONTH, Calendar.DECEMBER);
        cc.set(Calendar.DAY_OF_MONTH, 31);

        return cc.getTime();
    }

    /**
     * 税务机关组织机构代码后两位为00的不能开缴款书
     * @param type 接口类型
     * @param swdjjbsj 税务登记基本数据
     * @return true能开缴款书
     */
    public static boolean checkSwjgForJks(int type, SWDJJBSJ swdjjbsj)
    {
        String excludeSwjgzzjgdm = TinyTools.getProperty(propertyNames[type] +
            SWJG_PROPERTY);
        if (excludeSwjgzzjgdm == null || excludeSwjgzzjgdm.equals(""))
            return true;
        String swjgzzjgdm = swdjjbsj.getSwjgzzjgdm(); //税务机关
        System.out.println("WSSB--FriendHelper:swjgzzjgdm=" + swjgzzjgdm +
                           ";excludeswjg=" + excludeSwjgzzjgdm);
        if (swjgzzjgdm.substring(swjgzzjgdm.length() - 2)
            .equalsIgnoreCase(excludeSwjgzzjgdm))
        {
            return false;
        }
        return true;
    }

    /**
     * 接口中判断纳税人状态
     * @param property 配置属性名
     * @param nsrzt 纳税人状态
     * @return true能开缴款书
     */
    public static boolean checkNsrztForJks(int type, SWDJJBSJ swdjjbsj)
    {
        String nsrzts = TinyTools.getProperty(propertyNames[type] +
                                              NSRZT_PROPERTY);
        System.out.println("WSSB--FriendHelper:nsrzt=" + swdjjbsj.getNsrzt());
        System.out.println(nsrzts);
        if (nsrzts == null || nsrzts.equals(""))
            return true;
        String[] nsrztAry = TinyTools.divideString(nsrzts,
            CodeConstant.SEPARATOR);
        return!StringUtil.contains(nsrztAry, swdjjbsj.getNsrzt(), false);
    }
    
    
	/**
	 * 获取税源标识
	 *  “地” CODE_QYSDS_SYBS_D = "0"
	 *  “总” CODE_QYSDS_SYBS_Z = "1";
	 *  “分” CODE_QYSDS_SYBS_F = "2";
	 *  “国”或“不”标识填报为空其他的纳税人" CODE_QYSDS_SYBS_OTHER = "3";
	 * @param swdjjbsj
	 * @return
	 */
	public static String getNsrSybs(SWDJJBSJ swdjjbsj){
		String sybs="";
		String qyzy=swdjjbsj.getQyzy();
		if(qyzy==null || qyzy.length()<1){
			sybs=CodeConstant.CODE_QYSDS_SYBS_OTHER;
		}else{
			String sybs_temp=qyzy.substring(0,1);
			if(sybs_temp.equals("地")){
				sybs=CodeConstant.CODE_QYSDS_SYBS_D;
			}else if(sybs_temp.equals("总")){
				sybs=CodeConstant.CODE_QYSDS_SYBS_Z;
			}else if(sybs_temp.equals("分")){
				sybs=CodeConstant.CODE_QYSDS_SYBS_F;
			}else{
				sybs=CodeConstant.CODE_QYSDS_SYBS_OTHER;
			}
		}
		return sybs;
	}
	
	/**
     * 获得印花税征期日历
     * @param djsj
     * @return 
     * 减免税项目及网上申报印花税项目  Junbing Tu 201404,201407
     */
    public static List getZqrlInfor(HttpServletRequest request)
    {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        
        String jsjdm = ( (UserData)request.getSession().getAttribute(SessionKey.USER_DATA)).yhid;
        
        SimpleDateFormat simpleDataFormat = new SimpleDateFormat("yyyyMMdd");
        String month = simpleDataFormat.format(new Date()).substring(0, 6); // 当前年月:YYYYMM
        String year = simpleDataFormat.format(new Date()).substring(0, 4); // 当前年:YYYY
        
        try
        {
        	String sql =
                "select distinct b.szsmdm,b.zqqsrq,(b.zqqsrq+zqts-1) zqzzrq " +
                " from DMDB.SB_DM_SZSM a, sbdb.sb_jl_zqrl b, djdb.dj_jl_jbsj c " +
                " where a.szsmdm = b.szsmdm and a.zxbs = '0' and (a.sffjs <> '3' or a.sffjs is null) and a.ccbs = '2' " +
                " and b.szsmdm like '"+CodeConstant.WSSB_YSSB_YHS+"%' and to_char(b.zqqsrq,'yyyymm')= ? " +
                " and b.nd = ? and b.djzclxdm = c.djzclxdm and c.jsjdm = ? order by szsmdm";
        	
            conn = DBResource.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, month);
            pstmt.setString(2, year);
            pstmt.setString(3, jsjdm);

            List ret = new ArrayList();

            Zqrl temp = null;
            for(rs = pstmt.executeQuery(); rs.next(); ret.add(temp))
            {
            	temp = new Zqrl();
            	temp.setSzsmdm(rs.getString("szsmdm"));
            	temp.setZqqsrq(rs.getTimestamp("zqqsrq"));
            	temp.setZqzzrq(rs.getTimestamp("zqzzrq"));
            }

            return ret;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return new ArrayList();
        }
        finally
        {
            try  {   rs.close();  }  catch(Exception ex)    {  }
            try  {   pstmt.close();  }   catch(Exception ex)   {  }
            // 关闭数据库连接
            DBResource.destroyConnection(conn);
        }
    }
	
}
