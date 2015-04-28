
package com.lscdz.qysds.common.service.qysdsCheck.processor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;


import com.lscdz.qysds.common.service.qysdsCheck.JdlxContant;
import com.lscdz.qysds.common.service.qysdsCheck.bo.CheckBean;
import com.lscdz.qysds.common.service.qysdsCheck.bo.ZfjgInf;
import yangjian.frame.util.FrameException;

/**
 * ��ҵ����˰�걨��У����--����ʱ���
 * @description : 
 * @author zhangj
 * @version 1.0
 * @time 2014-12-19 ����11:18:33
 */

public class CheckZfbaObserver{
	
	/**
	 *�����ҵ����˰�걨���Ӧ����
	 * @throws BaseException 
	 */
	 
	public void update(Connection conn,CheckBean checkBean) throws FrameException {

		ZfjgInf zfjgInf = null;
		// ��������Ϊ ��ʡ���ܻ�����˰�� 02 ���ʡ�з�֧������˰�� 03 ʱ
		if (JdlxContant.QYSDSZGFWJDLX_KSSZJGNSR.equals(checkBean.getJdlx())
				|| JdlxContant.QYSDSZGFWJDLX_KSSFZJGNSR.equals(checkBean.getJdlx())) {
			zfjgInf = this.doQueryZjgba(conn,checkBean);
			if (zfjgInf != null) {
				// �ܻ���
				if ("0".equals(zfjgInf.getBaqylx())) {
					if (!zfjgInf.isIsftsk()) {
						checkBean.setJdlx(JdlxContant.QYSDSZGFWJDLX_DLNSR);// ���ü�������Ϊ������˰��
						System.out.println("---------CheckZfbaObserver  �ܻ���������Ϊ������˰��");
					}
				} 
                // ��֧����
				if ("1".equals(zfjgInf.getBaqylx())){
					// �Ƿ�����̯��ҵ����˰��ĿΪ���ҷ�֧��������Ϊ������֧����
					if (!zfjgInf.isCyftsk() && "02".equals(zfjgInf.getFzlx())) {
						checkBean.setJdlx(JdlxContant.QYSDSZGFWJDLX_DLNSR);// ���ü�������Ϊ������˰��
						System.out.println("---------CheckZfbaObserver  ��֧����������Ϊ������˰��");
					}
				}
			}
			checkBean.setZfjgInf(zfjgInf);
		}
	}


	/**
	 * @Description: �ⲿ���̻�ȡ�ֻܷ���������Ϣ�ӿڵ�ʵ�ַ���
	 * @param vo
	 * @return
	 */
	public ZfjgInf doQueryZjgba(Connection conn,CheckBean checkBean) throws FrameException{
//		Connection conn = null;
		PreparedStatement ps = null;
	    ResultSet rs = null;
//	    ZfjgbaModel model = null ;
//	    List zfjgList= new ArrayList(); 
	    ZfjgInf zfjgInf = null;
		try {
			//��ȡ����
//			conn = ResourceManager.getConnection();
			//��ȡ�û�����
			String jsjdm = checkBean.getJsjdm();
			//String ksrq = (String) map.get("ksrq");
			String jzrq = checkBean.getSkssrqz();
			String sql = "SELECT id,baqylx,jsjdm,baqssj,sfftsk,sndsfxwqy,fzjglxdm,BAQSSJ FROM sfdb.SF_JL_ZFJGBA_QYXX t " +
					     " WHERE jsjdm=? AND ZXBZ='0' AND BAQSSJ<to_date(?,'yyyymmdd') ORDER BY t.baqssj DESC";
		    ps = conn.prepareStatement(sql);
		    ps.setString(1, jsjdm);
		    ps.setString(2, jzrq);
		    rs = ps.executeQuery();
		    zfjgInf = new ZfjgInf();
		    if(rs.next()){
		    	
		    	//ȡ�б��е�һ������
		    	if("0".equals(rs.getString("baqylx"))){//�ܻ���
		    		//�����Ƿ��̯����˰
		    		zfjgInf.setIsftsk(("1".equals(rs.getString("sfftsk")))?false:true);
		    		//�����ܻ���С΢��ҵ����
		    		String xwqy = rs.getString("sndsfxwqy")==null?"":rs.getString("sndsfxwqy");
		    		zfjgInf.setXWQY(("1".equals(xwqy))?false:true);
		    	}else if("1".equals(rs.getString("baqylx"))){//��֧����
		    		//�����Ƿ�����̯��ҵ����˰
		    		zfjgInf.setCyftsk(("1".equals(rs.getString("sfftsk")))?false:true);
		    		//��ȡ��֧����С΢��ҵ����
		    		String isxwqy = getXwqyById(conn,rs.getString("id"));
		    		zfjgInf.setFzjg_zjginf_XWQY(("1".equals(isxwqy))?false:true);
		    		//���÷�֧��������
		    		String fzjglxdm = rs.getString("fzjglxdm")==null?"":rs.getString("fzjglxdm");
		    		zfjgInf.setFzlx(fzjglxdm);
		    	    
		    	}else{//���ֻܷ���
//		    		throw new ApplicationException("���ֻܷ�����������");
		    		System.out.println("���ֻܷ�����������");
		    	}
		    	//���ñ�����ҵ����
		    	zfjgInf.setBaqylx(rs.getString("baqylx"));
		    	//���ñ�����ʼʱ��
		    	//zfjgInf.setBaqssj(null==rs.getDate("BAQSSJ")?"":new SimpleDateFormat("yyyyMMdd").format(rs.getDate("BAQSSJ")));
		    	zfjgInf.setBaqssj(rs.getDate("BAQSSJ"));
		    	zfjgInf.setHasBasj(true);
		    	
		    }else{
		    	zfjgInf.setHasBasj(false);
		    }
		    System.out.println("----------------------zfjgInf="+zfjgInf);
		    
		  
		} catch (Exception e) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			e.printStackTrace();
			throw new FrameException("��ѯ�ܻ���������Ϣʧ�ܣ�"+e.getMessage());
		} finally{
			//�ر���Դ
			if (rs != null) {
				try {
					rs.close();
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
		return zfjgInf;
	}
	/*
	 * Ϊ��֧����ʱ��ȡС΢��ҵ����
	 */
	public String getXwqyById(Connection conn,String id) throws FrameException{
		String xwqy = "";
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = " SELECT SFXWQY FROM sfdb.SF_JL_FZJGBA_ZJGXX WHERE ID=?";
	    try{
	    	pst = conn.prepareStatement(sql);
	    	pst.setString(1, id);
	    	rs = pst.executeQuery();
	    	if(rs.next()){
	    		xwqy = rs.getString("SFXWQY");
	    	}
	    	
	    }catch(SQLException e){
	    	System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	    	e.printStackTrace();
			throw new FrameException("��ȡС΢��ҵ���ͳ���");	    	
	    }finally{
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} 
			} 
			if (pst != null) {
				try {
					pst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} 
			}
	    }
		
		return xwqy;
		
	}
	
	

}
