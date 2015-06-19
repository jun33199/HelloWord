package com.lscdz.qysds.common.service.qysdsCheck.processor;


import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lscdz.qysds.common.service.ServiceManager;
import com.lscdz.qysds.common.service.djjbsj.bo.Djjbsj;
import com.lscdz.qysds.common.service.qysdsCheck.JdlxContant;
import com.lscdz.qysds.common.service.qysdsCheck.bo.CheckBean;
import com.lscdz.qysds.common.service.qysdsCheck.bo.Qysdszgfwjd;

import yangjian.frame.util.FrameException;
import yangjian.frame.util.ResourceManager;

/**
 * �������ܷ�Χ��������
 * @description : 
 * @author zhangj
 * @version 1.0
 * @time 2014-12-19 ����10:54:46
 */
public class CheckJdlxObserver {
	/**
	 * �������ܷ�Χ��������
	 * @param checkBean
	 */
	public void update(Connection conn,CheckBean checkBean) throws FrameException{
		try {
			if(JdlxContant.JDLX_CHECKSTYLE_SKSJ.equals(checkBean.getJdlxCheckStyle())){
				this.updateBySksj(conn,checkBean);
			}
			if(JdlxContant.JDLX_CHECKSTYLE_SKSSRQQZ.equals(checkBean.getJdlxCheckStyle())){
				this.updateBySkssrqqz(conn,checkBean);
			}
		} catch (FrameException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * ���ݼ�������롢˰�������������˰����������ֹ�������ܷ�Χ��������
	 * @param checkBean
	 * @throws FrameException
	 */
	public void updateBySkssrqqz(Connection conn,CheckBean checkBean) throws FrameException{
		String nsrzt="";
//		Connection conn=null;
//		conn =  ResourceManager.getConnection();
		Djjbsj djjbsj=ServiceManager.getInstance().getDjjbsjService().query(conn,checkBean.getJsjdm());
		nsrzt=djjbsj.getNsrzt();
		if(!"10".equals(nsrzt)){
			throw new FrameException("����ҵ״̬�����������ܽ�����ز���");
		}
		List ls = null;	
		ls =this.getQysdszgfwjdList(conn,checkBean.getJsjdm(),checkBean.getSkssrqq(),checkBean.getSkssrqz());
		//�޲�ѯ���
		if(ls==null || ls.size()==0)
			throw new FrameException("����ҵ������ҵ����˰Ӧ���������ܽ�����ز���");
		//�в�ѯ���
		String jdjg = "";		//�������
		Qysdszgfwjd finalJd = (Qysdszgfwjd)ls.get(0);	
		for(int i=0;i<ls.size();i++)
		{
			Qysdszgfwjd temp=(Qysdszgfwjd)ls.get(i);

			if(checkYzh(temp) && (checktime(finalJd,temp) || "".equals(jdjg)) )
			{
				jdjg = temp.getJdlxdm();
				finalJd = temp;
			}	
		}

		if(jdjg==null || "".equals(jdjg))
		throw new FrameException("����ҵ������ҵ����˰Ӧ���������ܽ�����ز���");
		System.out.println("��������ǣ�"+jdjg);
		jdjg = checkfirstKfjg(jdjg ,djjbsj ,checkBean);
		checkBean.setJdlx(jdjg);
		checkBean.setQysdszgfwjd(finalJd);
		
		
	}
	/**
	 * ���ݼ�������롢˰��ʱ��������ܷ�Χ��������
	 * @param checkBean
	 * @throws FrameException
	 */
	public void updateBySksj(Connection conn,CheckBean checkBean) throws FrameException{
		String nsrzt="";
//		Connection conn=null;
//		conn =  ResourceManager.getConnection();
		Djjbsj djjbsj=ServiceManager.getInstance().getDjjbsjService().query(conn,checkBean.getJsjdm());
		nsrzt=djjbsj.getNsrzt();
		if(!"10".equals(nsrzt)){
			throw new FrameException("����ҵ״̬�����������ܽ�����ز���");
		}
		Qysdszgfwjd qysdszgfwjd = null;	
		qysdszgfwjd =this.getQysdszgfwjd(conn,checkBean.getJsjdm(),checkBean.getSksj());

		String jdjg = qysdszgfwjd.getJdlxdm();

		if(qysdszgfwjd!=null && (JdlxContant.QYSDSZGFWJDLX_DLNSR.equals(jdjg) || JdlxContant.QYSDSZGFWJDLX_KSSFZJGNSR.equals(jdjg) || JdlxContant.QYSDSZGFWJDLX_KSSZJGNSR.equals(jdjg) || JdlxContant.QYSDSZGFWJDLX_ZFJGJZBSZJGNSR.equals(jdjg)))
		{
			jdjg = checkfirstKfjg(jdjg ,djjbsj ,checkBean);
			checkBean.setJdlx(jdjg);
			checkBean.setQysdszgfwjd(qysdszgfwjd);
		}else {
			throw new FrameException("����ҵ������ҵ����˰Ӧ���������ܽ�����ز���");
		}	
	}	
	/**
	 * @Description: TODO ���ܷ�Χ����ģ��Ҫ���һ��Ǽ� ��ҵ��ʡ���ܺ��ֻܷ������ڱ��е��ܵ�������˰��
	 * @param jdjg
	 * @param sp
	 * @param checkBean
	 * @return
	 * @throws BaseException
	 */
	private String checkfirstKfjg(String jdjg ,Djjbsj sdjjbsjp ,CheckBean checkBean) throws FrameException
	{

		String kydjrq = sdjjbsjp.getKydjrq().toString();
		String djnd = kydjrq.substring(0, 4);								//�Ǽ����
		String sknd=null;
		if(JdlxContant.JDLX_CHECKSTYLE_SKSJ.equals(checkBean.getJdlxCheckStyle())){
			sknd= checkBean.getSksj().substring(0, 4);	//˰��ʱ���������
		}else{
			sknd= checkBean.getSkssrqz().substring(0, 4);				//˰���ֹ�����������			
		}

		
		boolean isInCondition1 = (djnd!=null && sknd!=null && djnd.equals(sknd));		//��һ��
		boolean isInCondition2 = (JdlxContant.QYSDSZGFWJDLX_KSSZJGNSR.equals(jdjg) || JdlxContant.QYSDSZGFWJDLX_ZFJGJZBSZJGNSR.equals(jdjg)); //��ʡ���ܻ���ڱ�����
		
		
		//System.out.println("****djnd:"+djnd+"****sknd:"+sknd+"*****isInCondition1"+isInCondition1+"***isInCondition2"+isInCondition2);
		if(isInCondition1 && isInCondition2)
		{
			jdjg = JdlxContant.QYSDSZGFWJDLX_DLNSR;
		}
		return jdjg;
	}
	/**
	 * @Description: TODO У����ҵ����˰Ӧ����
	 * @param qYSDSZGFWJD
	 * @return
	 */
	private boolean checkYzh(Qysdszgfwjd qYSDSZGFWJD)
	{
		if(JdlxContant.QYSDSZGFWJDLX_DLNSR.equals(qYSDSZGFWJD.getJdlxdm()) || JdlxContant.QYSDSZGFWJDLX_KSSFZJGNSR.equals(qYSDSZGFWJD.getJdlxdm()) || JdlxContant.QYSDSZGFWJDLX_KSSZJGNSR.equals(qYSDSZGFWJD.getJdlxdm()) || JdlxContant.QYSDSZGFWJDLX_ZFJGJZBSZJGNSR.equals(qYSDSZGFWJD.getJdlxdm()))
		{
			return true;
		}else{
			return false;
		}
	}	
	/**
	 * @Description: TODO �Ƚ�����������ʱ��
	 * @param finaljd
	 * @param currentjd
	 * @return
	 */
	private boolean checktime(Qysdszgfwjd finaljd ,Qysdszgfwjd currentjd)
	{
		if(finaljd.getJdqsrq()==null || currentjd.getJdqsrq()==null || finaljd.getJdqsrq().compareTo(currentjd.getJdqsrq())>=0)
		{
			return false;
		}else{
		return true;
		}
	}	
	
	/**
	 * ���ݼ���������ʱ����ѯ��ҵ����˰���շ�Χ����
	 * @param jsjdm ��׼�������Ż������
	 * @param date ��ѯ����
	 * @return List
	 * @throws BaseException
	 * @throws RemoteException
	 */
    public List getQysdszgfwjdList (Connection conn,String jsjdm,String startDate,String endDate)
        throws FrameException
    {
    	List dataList = new ArrayList();
    	//���ݿ�����
    	//Connection conn = null;
        PreparedStatement ps = null;
        //����� 
	    ResultSet rst = null;
        try
        {
        	  StringBuffer sqlBuf = new StringBuffer();
        	  sqlBuf.append("select k.* from (");
        	  sqlBuf.append(" select a.* from (select t.* from DJDB.DJ_JL_QYSDSZGFWJD t where t.jsjdm=? ) a where a.JDJZRQ>=to_date(?,'yyyymmdd') or (a.JDJZRQ is null) ");
        	  
        	  if(endDate!=null&&!endDate.equals("")){
    		    sqlBuf.append(" intersect ");  
    		    sqlBuf.append(" select a.* from (select t.* from DJDB.DJ_JL_QYSDSZGFWJD t where t.jsjdm=?) a where a.JDQSRQ<=to_date(?,'yyyymmdd') ");  
    	      }
        	  
        	  sqlBuf.append(" ) k order by k.jdqsrq asc ");
        	  
//        	  sqlBuf.append("select t.* from DJDB.DJ_JL_QYSDSZGFWJD t where t.jsjdm=? and t.JDJZRQ>=to_date(?,'yyyymmdd') or (t.JDJZRQ is null) ");
//        	  if(endDate!=null&&!endDate.equals("")){
//        		  sqlBuf.append(" intersect ");  
//        		  sqlBuf.append(" select t.* from DJDB.DJ_JL_QYSDSZGFWJD t where t.jsjdm=? and t.JDQSRQ<=to_date(?,'yyyymmdd') ");  
//        	  }
        	  System.out.println("#########sqlBuf="+sqlBuf.toString());
        	  
//        	  conn =  ResourceManager.getConnection();
        	  ps = conn.prepareStatement(sqlBuf.toString());   
        	  int i=1;
  		      ps.setString(i++, jsjdm);
  		      ps.setString(i++, startDate);
  		      if(endDate!=null&&!endDate.equals("")){
  		         ps.setString(i++, jsjdm);
  		    	 ps.setString(i++, endDate);
  		      }
  		      
        	  rst = ps.executeQuery();
              while (rst.next()) {
            	  Qysdszgfwjd qysdszgfwjd=new Qysdszgfwjd();
            	  qysdszgfwjd.setJsjdm(rst.getString("jsjdm"));//JSJDM
            	  qysdszgfwjd.setJdbxlh(rst.getString("jdbxlh"));//JDBXLH
            	  qysdszgfwjd.setJdlxdm(rst.getString("jdlxdm")==null?"":rst.getString("jdlxdm"));//JDLXDM
            	  qysdszgfwjd.setJdqsrq(rst.getTimestamp("jdqsrq"));//JDQSRQ
            	  qysdszgfwjd.setJdjzrq(rst.getTimestamp("jdjzrq"));//JDJZRQ
            	  qysdszgfwjd.setJdnd(rst.getString("jdnd")==null?"":rst.getString("jdnd"));//JDND
            	  qysdszgfwjd.setCjr(rst.getString("cjr")==null?"":rst.getString("cjr"));//CJR
            	  qysdszgfwjd.setCjrq(rst.getTimestamp("cjrq"));//CJRQ
            	  qysdszgfwjd.setLrr(rst.getString("lrr")==null?"":rst.getString("lrr"));//LRR
               	  qysdszgfwjd.setLrrq(rst.getTimestamp("lrrq"));//LRRQ
               	  dataList.add(qysdszgfwjd);
              }
        }catch (Exception e) {
        	System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        	 e.printStackTrace();
        	  throw new FrameException("��ȡ��ҵ����˰˰Դ��������"
                      + e.getMessage());
        }finally
        {
			if (rst != null) {
				try {
					rst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} 
			} 
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} 
			}

	    }        
        return dataList;
    }

 
/**
 * ���ݼ���������ʱ����ѯ��ҵ����˰���շ�Χ����
 * @param jsjdm ��׼�������Ż������
 * @param date ��ѯ����
 * @return QYSDSZGFWJD
 * @throws BaseException
 * @throws RemoteException
 */    
public Qysdszgfwjd getQysdszgfwjd (Connection conn,String jsjdm,String date)
    throws FrameException
{
	Qysdszgfwjd qysdszgfwjd=new Qysdszgfwjd();
	//���ݿ�����
//    Connection conn = null;
    PreparedStatement ps = null;
    //����� 
    ResultSet rst = null;
    try
    {
    	System.out.println("#########jsjdm="+jsjdm);
    	System.out.println("#########date="+date);
    	  StringBuffer sqlBuf = new StringBuffer();
    	  sqlBuf.append("select  t.* from DJDB.DJ_JL_QYSDSZGFWJD t where t.jsjdm=?  and t.JDQSRQ<=to_date(?,'yyyymmdd') and (to_date(?,'yyyymmdd') <=t.JDJZRQ or t.JDJZRQ is null)");
    
    	  System.out.println("#########sqlBuf="+sqlBuf.toString());
//    	  conn =  ResourceManager.getConnection();
    	  ps = conn.prepareStatement(sqlBuf.toString());   
    	  int i=1;
		  ps.setString(i++, jsjdm);
		  ps.setString(i++, date);
		  ps.setString(i++, date);
    	  rst = ps.executeQuery();
          if (rst.next()) {
        	  qysdszgfwjd.setJsjdm(rst.getString("jsjdm"));//JSJDM
        	  qysdszgfwjd.setJdbxlh(rst.getString("jdbxlh"));//JDBXLH
        	  qysdszgfwjd.setJdlxdm(rst.getString("jdlxdm")==null?"":rst.getString("jdlxdm"));//JDLXDM
        	  qysdszgfwjd.setJdqsrq(rst.getTimestamp("jdqsrq"));//JDQSRQ
        	  qysdszgfwjd.setJdjzrq(rst.getTimestamp("jdjzrq"));//JDJZRQ
        	  qysdszgfwjd.setJdnd(rst.getString("jdnd")==null?"":rst.getString("jdnd"));//JDND
        	  qysdszgfwjd.setCjr(rst.getString("cjr")==null?"":rst.getString("cjr"));//CJR
        	  qysdszgfwjd.setCjrq(rst.getTimestamp("cjrq"));//CJRQ
        	  qysdszgfwjd.setLrr(rst.getString("lrr")==null?"":rst.getString("lrr"));//LRR
           	  qysdszgfwjd.setLrrq(rst.getTimestamp("lrrq"));//LRRQ
          }
    }catch (Exception e) {
    	System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    	 e.printStackTrace();
    	  throw new FrameException("��ȡ��ҵ����˰˰Դ��������"
                  + e.getMessage());
    }finally
    {
		if (rst != null) {
			try {
				rst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		} 
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
    }
    return qysdszgfwjd;
}	
	

}
