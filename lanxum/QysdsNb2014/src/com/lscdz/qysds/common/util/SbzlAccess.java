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
	public static final String NSRJBXXB="NSRJBXXB";//��˰�˻�����Ϣ��
	public static final String YHLB_ZRR = "03";//��Ȼ��
	public static final String DJZCLXDM_NWZFLDM_GAT = "1";//�����ʷ�����룺�۰�̨
	public static final String DJZCLXDM_NWZFLDM_WZ = "2";//�����ʷ�����룺����
	public static final String ZSFSDM_CYLZS = "01";//����������
	public static final String ZSFSDM_DEZS = "02";//��������
	public static final String DJZCLXDM_NWZFLDM_GTJJ = "3";//�����ʷ�����룺���徭Ӫ
	public static final String DJZCLXDM_SYDZQY = "171";//�Ǽ�ע�����ʹ��룺˽Ӫ������ҵ
	public static final String DJZCLXDM_SYHHQY = "172";//�Ǽ�ע�����ʹ��룺˽Ӫ�ϻ���ҵ

	
	 /** �жϵ�ǰ�����Ƿ���������
     *  Ŀǰֻ���پ�ҵ�����걨ʹ��
     * @param zqstr
     * @param now
     * @return true��ǰ���ڷ��������걨���Ͽ���¼�룬false��ǰ���ڲ���������
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
     * ����properties�����ƣ��������ļ��ж�ȡ��properties��Ӧ��ֵ��
     * @param propertyName properties������
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
                 return ""; // û�п���ά�����걨���ݣ�
             }
             if (value == null) {
                 value = "";
             }
         }catch (Exception e) {
        	 System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
             throw new FrameException("��ȡ�����ļ�propertiesֵʧ�ܣ�");
         }finally{
 			try { if (rs != null) rs.close(); } catch(Exception exx) {}
 			try { if (st != null) st.close(); } catch(Exception exx) {}
 			try { if (con != null) con.close(); } catch(Exception exx) {}
 		}
        return value.trim();
    }
    /**
     * �ж���˰�˻�����Ϣ�Ƿ��¼��ɱ༭
     * @param yhxx
     * @return
     * @throws FrameException
     */
    public static boolean isNsrjbxxEditable(Yh yhxx) throws FrameException{
        CheckBean checkBean = new CheckBean();	
        checkBean.setJsjdm(yhxx.getYhid());
    	System.out.println("----------������˰�˻�����Ϣ���ж�---------");
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
		//��ҵ����˰���ܷ�Χ����������ʡ�з�֧������˰�˲���¼����˰�˻�����Ϣ��
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
     * �÷������Լ���Ƿ���������ڣ��Ƿ���Ӧ������ʱ��Σ�
     * @Description: TODO
     * @param checkBean
     * @return
     */
    private static boolean checkJd(CheckBean checkBean)
    {
    	Connection conn=null;
    	try {    		
    		System.out.println("����ʱ��β����");   		
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
     * �Ƿ�����
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
     * �Ƿ���Ȼ��
     * @param yhxx
     * @return
     */
    private static boolean isZrr(Yh yhxx)
    {
        return yhxx.getYhlb().equals(YHLB_ZRR);
    }
    
    /**
     * �ж��Ƿ��ǲ������շ�ʽ �걨
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
	    	//��ǰʱ��
	        Timestamp sbrq = new Timestamp(System.currentTimeMillis());
	        Map skssrq = new HashMap();

			skssrq = Skssrq.yearSkssrq(sbrq);


	        // ȡ��˰��������ʼ�ͽ�������
	        Timestamp skssksrq = (Timestamp) skssrq.get(Skssrq.SKSSKSRQ);
	        Timestamp skssjsrq = (Timestamp) skssrq.get(Skssrq.SKSSJSRQ);
        
	        conn=ResourceManager.getConnection();
	        // ��ѯ˰�ѽӿ�
	        QysdsSet qysdsSet=ServiceManager.getInstance().getQysdsInfoServer().getQysdsInfo(conn, yhxx.getYhid(), sbrq, skssksrq, skssjsrq, "01");//�걨
	        Zsfs zsfs = qysdsSet.getZsfs();
	        System.out.println("��ǰ�걨���걨����sbrq:"+sbrq);

	        System.out.println("��ǰ�걨��˰��������ʼ����skssksrq:"+skssksrq);

	        System.out.println("��ǰ�걨��˰��������������skssjsrq:"+skssjsrq);


	        if (zsfs != null)
	        {
	        	System.out.println("���շ�ʽ����:"+zsfs.getZsfsdm());
	            String zsfsdm = zsfs.getZsfsdm();
	            if (zsfsdm.equals(ZSFSDM_CYLZS)) // ����������
	            {
	               return false;
	            }
	            else if (zsfsdm.equals(ZSFSDM_DEZS)) // ��������
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
     * �Ƿ���ҵ
     * @param yhxx
     * @return
     */
    private static boolean isCorporation(Yh yhxx)
    {
        return !isZrr(yhxx) && !isGtgsh(yhxx);
    }

    /**
     * �Ƿ���幤�̻�
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
