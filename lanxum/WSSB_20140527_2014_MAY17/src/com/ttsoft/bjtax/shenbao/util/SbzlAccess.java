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
     * ���Ȩ��
     * @param type actionID
     * @param request HttpServletRequest
     * @return boolean �Ƿ���Ȩ��
     */
    public static boolean getAuthority(int type, HttpServletRequest request)
    {
        try
        {
        	System.out.println("��ǰ���˵�����:"+type);



            //if (type != 9991)
            //    return true;

            if (type == ALWAYSPASS)
            {
                return true;
            }

            
            if (isZrr(request))
            {
                // ��Ȼ��
                if(FriendHelper.getZrrjbsj(request) == null)
                {
                    return false;
                }
            }
            else
            {
                SWDJJBSJ jbsj = FriendHelper.getSWDJJBSJ(request);
                // ��ҵ
                if(jbsj == null)
                {
                    return false;
                }

                if (!(jbsj.getNsrzt().equals("10") || jbsj.getNsrzt().equals("90")))
                {
                    // ��������
                    return false;
                }
            }
            UserData ud = (UserData)request.getSession().getAttribute(SessionKey.USER_DATA);
            
            //����У�����ҵ�Ƿ��������ڣ��Ƿ���Ӧ����
            CheckBean checkBean = new CheckBean();	
            checkBean.setJsjdm(ud.getYhid());
            
            //��ҵ��������˰
            if (type == QYQSSDSBA2014){
            	boolean canShenbao = canDisplay(type);
            	if(canShenbao){
	            	//��Ҫ������ҵ����˰Ӧ�����ӿڣ���Ӧ�����ſ���������
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
            // added by qinw 20060922 start �����پ�ҵ�����걨���ڵ��ж�
            if (type == ZJYJMSB) {
            	Date now = new Date();
            	String zqstr = TinyTools.getProperty("WSSB_ZJYJMSB_ZQRL_MONTH_"
                        + (new SimpleDateFormat("MM")).format(now));
            	boolean withinzq = withinZq(zqstr, now);
            	return withinzq;
            }
//          added by qinw 20060922 end

//          added by xinyy 20091213 start ���òм��˾�ҵ��ҵӪҵ˰�����걨���ڵ��ж�
            if(type == CJRJYJMSB) {
            	Date now = new Date();
            	String zqstr = TinyTools.getProperty("WSSB_CJRJYJMSB_ZQRL_MONTH_"
                        + (new SimpleDateFormat("MM")).format(now));
            	System.out.println(zqstr);
            	if(!(withinZq(zqstr, now))){
					return false;
				}
            }
//          added by xinyy 20091213 end ���òм��˾�ҵ��ҵӪҵ˰�����걨���ڵ��ж�


//          added by lianglw 20061101 start �µ���ҵ����˰�걨��2006��
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
            	System.out.println("----------������˰�˻�����Ϣ���ж�---------:"+checkBean.getJdlx());
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
				//System.out.println("----------�����Ƿ���¼����˰�˻�����Ϣ---------:"+checkBean.getJdlx());
				//��ҵ����˰���ܷ�Χ����������ʡ�з�֧������˰�˲���¼����˰�˻�����Ϣ��
				if(CheckJdlx.QYSDSZGFWJDLX_KSSFZJGNSR.equals(checkBean.getJdlx())){
				//	 System.out.println("----------�����Ƿ���¼����˰�˻�����Ϣ---------");
                	 return false; 
                }
				
            }
//          added by lianglw 20061101 end �µ���ҵ����˰�걨��

//          added by tum 20080306 start ��ҵ����˰�걨��2008��
            //08��ҵ����˰�˶����ռ���
            if(type == HDZSQYJB2008)
            {
                Date now = new Date();
                String zqstr = TinyTools.getProperty("WSSB_HDZSQYJB2008_ZQRL_MONTH_"
                    + (new SimpleDateFormat("MM")).format(now));

                if(! (withinZq(zqstr, now))) {
                    return false;
                }
//                �°治����˰Դ����У��   ��ʱ����
              checkBean.createZgrqByCurrenttimeM();
              if(!checkJd(checkBean))
    			{
    				return false;
    			}
            }
            //08��ҵ����˰�������ռ���
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
//              �°治����˰Դ����У��   ��ʱ����
    			checkBean.createZgrqByCurrenttimeM();
    			if(!checkJd(checkBean))
    			{
    				return false;
    			}
            }
            //2012��ҵ����˰���������걨
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
            //08��ҵ����˰������˰��֧���������
            if(type == ZFJGQYJB2008) {
                Date now = new Date();
                String zqstr = TinyTools.getProperty("WSSB_ZFJGQYJB2008_ZQRL_MONTH_"
                    + (new SimpleDateFormat("MM")).format(now));

                if(! (withinZq(zqstr, now))) {
                    return false;
                }
            }
//          added by tum 20080306 end ��ҵ����˰�걨��2008��

            
            if (! isNsr(type)) return true;
            Grzsfs grzsfs = null;


            switch (type)
            {
                case CZND:  // ���������걨
                    grzsfs = FriendHelper.getGrzsfsInfo(request);

                    return (isGtgsh(request) && grzsfs!= null &&
                            grzsfs.getZsfsdm().equals(CodeConstant.ZSFSDM_CZZS));

                case CZJD:     // �������ռ���
                    return false;

                case SD:  // ����
                    return false;
//                    return FriendHelper.getSdzg(request);

                case JM:  // ����˰
                    // û������
                    return true;

                case QYKS:  // ��ҵ����˰����
                    if (isForeignCorporation(request))
                    {
                        return false;
                    }
                    return isCorporation(request);

                case QYND:  // ��ҵ����˰�걨
                    //����ǩ����Ŀ���˲���ָ�꣬����걨�޷����档��˽������Σ�
                    return false;
//                    if (isForeignCorporation(request))
//                    {
//                        return false;
//                    }
//                    return isCorporation(request);

                case WQ:  // ����Ӫҵ˰
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

                case YHND:  // ӡ��˰���
                    return true;
                case CWZB:  // ����ָ��
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
                case WSKSB:  // ��˰�걨
                    // û������
                    return true;

                case HDYSSDL:  // �˶�����Ӧ˰������
                    // ������
                    return false;

                case HDZSL: // �˶�����������
                    // ������
                    return false;

                case GRSDS:  // ��������˰
                    // ������
                    return false;

//                  added by lianglw 20061101 start �µ���ҵ����˰�걨��2006��

                case HDZSQYJB:  // �˶�������ҵ����˰����
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
                case HDZSQYNB:  // �˶�������ҵ����˰�걨
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
                case CZZSQYJB:  // ����������ҵ����˰����
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
                case CZZSQYNB:  // ����������ҵ����˰�걨
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
                case NSRJBXXB:  // ����������ҵ������Ϣ��
                	//System.out.println("NSRJBXXB "+type);
                    if (isForeignCorporation(request))
                    {
                        return false;
                    }
                    //	System.out.println("�Ƿ��ǲ��������걨");
                    if (!(isCzzsNb(request)))
                    {
                        return false;
                    }
                    //����Ƿ���˰Դ��ʶ�Ƿ����ܵط�
                    //added by wangcy 20131206 end ����������ҵ������Ϣ��
//                    if(isSybsZFD(request,CodeConstant.CODE_QYSDS_SYBS_OTHER)){
//                   	 return false; 
//                    }
                    //if(isSybsZFD(request,CodeConstant.CODE_QYSDS_SYBS_F)){
                     // 	return false; 
                    //}
                   // System.out.println("������Ϣ����Ȼ�˺͸��幤�̻�"+isCorporation(request));
                    return isCorporation(request);

//                  added by lianglw 20061101 end �µ���ҵ����˰�걨��

//              added by tum 20080306 start ��ҵ����˰�걨��2008��
                case HDZSQYJB2008:  // 2008�˶�������ҵ����˰����
                //System.out.println("HDZSQYNB "+type);
                     if(isForeignCorporation(request)) {
                         return false;
                     }

                     if (isCzzsJb(request)) {
                         return false;
                     }
                     return isCorporation(request);
                 case CZZSQYJB2008:  // 2008����������ҵ����˰����
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
                 case CZZSQYNB2012:  // 2008����������ҵ����˰����
                	 
                     //System.out.println("CZZSQYJB "+type);
                     if (isForeignCorporation(request))
                     {
                         return false;
                     }
                     if (!(isCzzsJb(request)))
                     {
                         return false;
                     }
                     //����Ƿ���˰Դ��ʶ�Ƿ��Ƿ�
                   //  System.out.println(checkBean.getJdlx());
                     if(!checkBean.getJdlx().equals(CheckJdlx.QYSDSZGFWJDLX_KSSFZJGNSR)){
                    	 return false; 
                     }
                     if(!isSkrq(request)){
                    	 return false;
                     }
                     return isCorporation(request);
                 case ZFJGQYJB2008:  // 2008������˰��֧���������
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
//          added by tum 20080306 end ��ҵ����˰�걨��2008��
//               added by xinyy 20091213 start ���òм��˾�ҵ��ҵӪҵ˰�����걨��   
                 case CJRJYJMSB:
                 	return true;
//               added by xinyy 20091213 end ���òм��˾�ҵ��ҵӪҵ˰�����걨��

                 case JMBASX:  // ���ⱸ������
                    if (isForeignCorporation(request))
                    {
                        return false;
                    }

                    if (!(isCzzsNb(request)))
                    {
                        return false;
                    }
                    return isCorporation(request);
                  
                 //��������˰ added by lijn 20141106
                 
                 case GRSDSNDSBB2014: //����걨��
                	String zsfsDM = FriendHelper.getGrzsfsInfoNew(request);
                	 String djzclx= FriendHelper.getSWDJJBSJ(request).getDjzclxdm();
                	 
                	 boolean djzclxCheck = CodeConstant.DJZCLXDM_GTGSH.equals(djzclx)||CodeConstant.DJZCLXDM_GRDZQY.equals(djzclx)||CodeConstant.DJZCLXDM_GRHHQY.equals(djzclx)||CodeConstant.DJZCLXDM_SYDZQY.equals(djzclx)||CodeConstant.DJZCLXDM_SYHHQY.equals(djzclx);
                    
                	 //����Ϊ5�ֵǼ�ע�������е�һ�ֲ������շ�ʽΪ�Ǻ˶�
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

    //�ж�qyzy ˰Դ��ʶΪ�ܷ� 
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

    
    //��˰��˰��ǼǵĿ�ҵ�Ǽ�������˰���������ڣ��������걨���ڡ��걨��ϸ����¼�롱ҳ�治��ʾ��ҵ����˰�걨���ϲ˵�
    static private boolean isSkrq(HttpServletRequest request)
    {
		try {
			SWDJJBSJ  djJbsj = (SWDJJBSJ) FriendHelper.getSWDJJBSJ(request);
	        Timestamp sbrq = new Timestamp(System.currentTimeMillis());
	        Map skssrq = new HashMap();
			skssrq = Skssrq.yearSkssrq(sbrq);

	        // ȡ��˰��������ʼ�ͽ�������
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
    
    // �Ƿ���Ȼ��
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
			// ��ҵ
            if(jbsj == null)
            {
                return false;
            }

            if (!(jbsj.getNsrzt().equals("10") || jbsj.getNsrzt().equals("90")))
            {
                // ��������
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

    // �Ƿ���Ȼ��
    static private boolean isZrr(HttpServletRequest request)
    {
        UserData userData =
            (UserData)request.getSession().getAttribute(SessionKey.USER_DATA);

        return userData.yhlb.equals(com.ttsoft.common.util.CodeConstants.YHLB_ZRR);
    }

    // �Ƿ���ҵ
    static private boolean isCorporation(HttpServletRequest request)
    {
        return !isZrr(request) && !isGtgsh(request);
    }

    // �Ƿ���幤�̻�
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

    
    // �Ƿ�����
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
     * �������õ���ʾ�����ж��Ƿ���Խ����걨¼��
     * @param type �걨��������
     * @return true��¼���걨���ϵ����ж��·ݵ�������false����¼���걨����
     */
    static private boolean canDisplay(int type)
    {
//        System.out.println("propertyNames = " + propertyNames[type] + DISPLAY_PROPERTY);
        String display = TinyTools.getProperty(propertyNames[type] + DISPLAY_PROPERTY);
//        System.out.println("display" + display);
        return display.equalsIgnoreCase("true");
    }

    /**
     * �������õ���˰�������ж��Ƿ�Ҫ�ж���˰������
     * @param type �걨��������
     * @return trueҪ�ж���˰�����ͣ�false�����ж���˰������
     */
    static private boolean isNsr(int type)
    {
        String nsr = TinyTools.getProperty(propertyNames[type] + NSR_PROPERTY);
//      System.out.println("nsr" + nsr);
        return nsr.equalsIgnoreCase("true");
    }

    /**
     * �������õ��·������ж��Ƿ���Խ����걨¼��
     * @param type �걨��������
     * @param month ��ǰ�·�
     * @return true��ǰ�·ݷ��������걨���Ͽ���¼�룬false��ǰ�·ݲ���������
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

    /** �жϵ�ǰ�����Ƿ���������
     *  Ŀǰֻ���پ�ҵ�����걨ʹ��
     * @param zqstr
     * @param now
     * @return true��ǰ���ڷ��������걨���Ͽ���¼�룬false��ǰ���ڲ���������
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
     * �ж��Ƿ��ǲ������շ�ʽ �걨
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

	    	//��ǰʱ��
	        Timestamp sbrq = new Timestamp(System.currentTimeMillis());
//	        Map ssrq = Skssrq.getSksssq(swdjjbsj.getJsjdm(), SzsmdmConstant.QYSDS_SM, swdjjbsj.getDjzclxdm(), CodeConstant.SKLXDM_ZCJK,
//	                CodeConstant.ZQLXDM_QUARTER, sbrq, null, null, null);


	        Map skssrq = new HashMap();

			skssrq = Skssrq.yearSkssrq(sbrq);


	        // ȡ��˰��������ʼ�ͽ�������
	        Timestamp skssksrq = (Timestamp) skssrq.get(Skssrq.SKSSKSRQ);
	        Timestamp skssjsrq = (Timestamp) skssrq.get(Skssrq.SKSSJSRQ);

	        ServiceProxy proxy = new ServiceProxy();

	        // ��ѯ˰�ѽӿ�
	        QysdsSet qysdsSet = null;
	        try
	        {
	            qysdsSet = proxy.getQysdsInfo(swdjjbsj.getJsjdm(), sbrq, skssksrq, skssjsrq, "01");//�걨
	        }
	        catch (com.ttsoft.framework.exception.BaseException e)
	        {
	            e.printStackTrace();
	        }

	        Zsfs zsfs = qysdsSet.getZsfs();

	        System.out.println("��ǰ�걨���걨����sbrq:"+sbrq);

	        System.out.println("��ǰ�걨��˰��������ʼ����skssksrq:"+skssksrq);

	        System.out.println("��ǰ�걨��˰��������������skssjsrq:"+skssjsrq);


	        if (zsfs != null)
	        {
	        	System.out.println("���շ�ʽ����:"+zsfs.getZsfsdm());
	            String zsfsdm = zsfs.getZsfsdm();
	            if (zsfsdm.equals(CodeConstant.ZSFSDM_CYLZS)) // ����������
	            {
	               return false;
	            }
	            else if (zsfsdm.equals(CodeConstant.ZSFSDM_DEZS)) // ��������
	            {
	            	return false;
	            }
	            else
	            	return true; //��������

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
     * �ж��Ƿ��ǲ������շ�ʽ ����
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

    		//��ǰʱ��
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

	        // ȡ��˰��������ʼ�ͽ�������
	        Timestamp skssksrq = (Timestamp) skssrq.get(Skssrq.SKSSKSRQ);
	        Timestamp skssjsrq = (Timestamp) skssrq.get(Skssrq.SKSSJSRQ);

	        ServiceProxy proxy = new ServiceProxy();

	        // ��ѯ˰�ѽӿ�
	        QysdsSet qysdsSet = null;
	        try
	        {
	        	//            qysdsSet = proxy.getQysdsInfo(swdjjbsj.getJsjdm(), sbrq, skssksrq, skssjsrq, "00");
	        	//        	ȡ�����ڵļ���
//				String jd = Skssrq.preQuarter(sbrq);


				if("4".equals(jd)){
					//				���Ϊ��4���ȵ�ʱ������걨�Ľӿ�
					qysdsSet = proxy.getQysdsInfo(swdjjbsj.getJsjdm(), sbrq, skssksrq, skssjsrq,"01");//�걨
				}else{
					//				�����Ϊ��4���ȵ�ʱ����ü����Ľӿ�
					qysdsSet = proxy.getQysdsInfo(swdjjbsj.getJsjdm(), sbrq, skssksrq, skssjsrq,"00");//����
				}
	        }
	        catch (com.ttsoft.framework.exception.BaseException e)
	        {
	            e.printStackTrace();
	        }

	        Zsfs zsfs = qysdsSet.getZsfs();

	        System.out.println("��ǰ�걨�ļ���jd:"+jd);

	        System.out.println("��ǰ�걨���걨����sbrq:"+sbrq);

	        System.out.println("��ǰ�걨��˰��������ʼ����skssksrq:"+skssksrq);

	        System.out.println("��ǰ�걨��˰��������������skssjsrq:"+skssjsrq);


	        if (zsfs != null)
	        {
	        	System.out.println("���շ�ʽ����:"+zsfs.getZsfsdm());
	            String zsfsdm = zsfs.getZsfsdm();
	            if (zsfsdm.equals(CodeConstant.ZSFSDM_CYLZS)) // ����������
	            {
	               return false;
	            }
	            else if (zsfsdm.equals(CodeConstant.ZSFSDM_DEZS)) // ��������
	            {
	            	return false;
	            }else if (zsfsdm.equals(CodeConstant.ZSFSDM_DLZS)) // ��������
	            {
	            	return false;
	            }
	            else
	            	return true; //��������

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
     * �����������
     */
    public static final int CZND = 0;

    /**
     * �������ռ���
     */
    public static final int CZJD = 1;

    /**
     * ����
     */
    public static final int SD = 2;

    /**
     * ��������˰
     */
    public static final int GRSDS = 3;

    /**
     * �˶�Ӧ˰������
     */
    public static final int HDYSSDL = 4;

    /**
     * �˶�������
     */
    public static final int HDZSL = 5;

    /**
     * ����
     */
    public static final int JM = 6;

    /**
     * ��ҵ�걨
     */
    public static final int QYND = 7;

    /**
     * ��ҵ����
     */
    public static final int QYKS = 8;

    /**
     * ����ָ��
     */
    public static final int CWZB = 9;

    /**
     * ����
     */
    public static final int WQ = 10;

    /**
     * ӡ�����
     */
    public static final int YHND = 11;

    /**
     * ������Э����ۺ��걨
     */
    public static final int ZHSBWITHSFXY = 12;

    /**
     * û������Э����ۺ��걨
     */
    public static final int ZHSBWITHOUTSFXY = 13;

    /**
     * ��Ȼ���걨
     */
    public static final int ZRRSB = 14;

    /**
     * ��ʾ�ɿ����б�
     */
    public static final int LISTJKS = 15;

    /**
     * ˰��˰Ŀ
     */
    public static final int SZSM = 16;

    /**
     * ��˰�걨
     */
    public static final int WSKSB = 17;
    /**
     * �پ�ҵ�����걨
     */
    public static final int ZJYJMSB = 18;

//  added by tum 20080306 start ��ҵ����˰�걨��2008��
        /**
         * 2008�������ռ���
         */
        public static final int CZZSQYJB2008 = 19;

        /**
         * 2008�˶����ռ���
         */
        public static final int HDZSQYJB2008 = 20;

        /**
         * 2008������˰��֧���������
         */
        public static final int ZFJGQYJB2008 = 21;

//  added by tum 20080306 end ��ҵ����˰�걨��2008��

//  added by lianglw 20061101 start �µ���ҵ����˰�걨��2006��



    /**
     * �˶�������ҵ����
     */
    public static final int HDZSQYJB = 22;

    /**
     * �˶�������ҵ�걨
     */
    public static final int HDZSQYNB = 23;

    /**
     * ����������ҵ����
     */
    public static final int CZZSQYJB = 24;

    /**
     * ����������ҵ�걨
     */
    public static final int CZZSQYNB = 25;

    /**
     * ��˰�˻�����Ϣ��
     */
    public static final int NSRJBXXB = 26;

//  added by lianglw 20061101 end �µ���ҵ����˰�걨��

     /**
     * ���òм��˾�ҵ�걨��
     */
    public static final int CJRJYJMSB = 27;

    /**
     * ���ⱸ������
     */
    public static final int JMBASX = 28;


    /**
     * ��Զ����ͨ�����
     */
    public static final int ALWAYSPASS = 999;
//		added by wangcy  20131202 end ��ҵ����˰��(��)��Ԥ����˰�걨��(A��)(2012��)
    /**
     * 
     */
    public static final int CZZSQYNB2012 = 29;
    
    //��ҵ��������˰����
    public static final int QYQSSDSBA2014 = 30;
    
    /*--------��������˰�������� added by lijn 20141105--------------*/
   
    //��������˰�걨��
    public static final int GRSDSNDSBB2014 = 31;
    
    
    /**
     * �걨������������������
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
        //�����پ�ҵ������������
        "WSSB_SBZL_ACCESSCONTROL_ZJYJMSB",

        //      added by tum 20080306 start ��ҵ����˰�걨��2008��
                "WSSB_SBZL_ACCESSCONTROL_CZZSQYJB2008",
                "WSSB_SBZL_ACCESSCONTROL_HDZSQYJB2008",
                "WSSB_SBZL_ACCESSCONTROL_ZFJGQYJB2008",
//      added by tum 20080306 end ��ҵ����˰�걨��2008��


//      added by lianglw 20061101 start �µ���ҵ����˰�걨��2006��
        "WSSB_SBZL_ACCESSCONTROL_HDZSQYJB",
        "WSSB_SBZL_ACCESSCONTROL_HDZSQYNB",
        "WSSB_SBZL_ACCESSCONTROL_CZZSQYJB",
        "WSSB_SBZL_ACCESSCONTROL_CZZSQYNB",
        "WSSB_SBZL_ACCESSCONTROL_CZZSQYJBXXB",

//      added by lianglw 20061101 end �µ���ҵ����˰�걨��
            //      added by xinyy 20091213 start ���òм��˾�ҵ��ҵӪҵ˰�����걨��
        "WSSB_SBZL_ACCESSCONTROL_CJRJYJMSB",
//      added by xinyy 20091213 end ���òм��˾�ҵ��ҵӪҵ˰�����걨��
		//���ⱸ������
		"WSSB_SBZL_ACCESSCONTROL_JMBASX",
//		added by wangcy  20131202 end ��ҵ����˰���Ԥ����˰�걨��(A��)(2012��)
		"WSSB_SBZL_ACCESSCONTROL_CZZSQYNB2012",
		//��ҵ��������˰����
		"WSSB_SBZL_ACCESSCONTROL_QYQSSDSBA2014",
		
		//added by lijn 20141106 ��������˰
		"WSSB_SBZL_ACCESSCONTROL_GRSDSNDSBB2014"
        };

    /**
     * �걨����������ʾ����������
     */
    public static String DISPLAY_PROPERTY = "_DISPLAY";

    /**
     * �걨���������·�����������
     */
    public static String MONTHS_PROPERTY = "_MONTHS";

    /**
     * �걨����������˰������������
     */
    public static String NSR_PROPERTY = "_NSR";
    
    /**
     * �����걨����˰�걨˰�����������ж�
     */
    public static String SKSSQ_PROPERTY = "_SKSSQ";

    /**
     * �÷������Լ���Ƿ���������ڣ��Ƿ���Ӧ������ʱ��Σ�
     * @Description: TODO
     * @param checkBean
     * @return
     */
    private static boolean checkJd(CheckBean checkBean)
    {
    	try {
    		
    		System.out.println("����ʱ��β����");
			QysdsCheckUtil.getInstance().check(QysdsCheckUtil.getInstance().subject1, checkBean);
		} catch (BaseException e) {
			return false;
		}
    	return true;
    }
     
    /**
     * �÷������Լ���Ƿ���Ӧ������ʱ��㣩
     * @Description: TODO
     * @param checkBean
     * @return
     */
    private static boolean checkJd2(CheckBean checkBean)
    {
    	
    	try {
    		System.out.println("����ʱ�������");
			QysdsCheckUtil.getInstance().check(QysdsCheckUtil.getInstance().subject2, checkBean);
		} catch (BaseException e) {
			return false;
		}
    	return true;
    }
}
