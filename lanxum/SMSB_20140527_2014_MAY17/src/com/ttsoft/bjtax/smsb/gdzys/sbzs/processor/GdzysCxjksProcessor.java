package com.ttsoft.bjtax.smsb.gdzys.sbzs.processor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.dj.model.YHZH;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.proxy.ServiceProxy;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.gdzys.constant.GdzysCodeConstant;
import com.ttsoft.bjtax.smsb.gdzys.sbzs.web.GdzysCjjksForm;
import com.ttsoft.bjtax.smsb.gdzys.sbzs.web.GdzysCxjksForm;
import com.ttsoft.bjtax.smsb.gdzys.util.GdzysMoneyUtils;
import com.ttsoft.bjtax.smsb.model.client.DeclareInfor;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.JksUtil;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.ZKAdapter;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: ������˰��������ϵͳ--����ռ��˰���չ���</p>
 * <p>Description: �����ɿ���AProcessor</p>
 * @author wangxq
 * @version 1.0
 */
public class GdzysCxjksProcessor implements Processor{
	 /**
     * ͨ�ô������ģ��
     * @param vo  ���ݴ���ֵ����
     * @return  ���ݽ������[ActionForm]
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
            case GdzysCodeConstant.SMSB_GDZYS_CKJKSACTION:
                result = doCkJks(vo);
                break;
            case CodeConstant.SMSB_DELETEACTION:
            	 result = doDelJks(vo);
                 break;
            default:
                throw new ApplicationException(
                    "ActionType�д���processor���Ҳ�����Ӧ�ķ���.");
        }
        return result;
    }
	/**
     * ��ѯ�ɿ�������
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return ��ǰҳ���Ӧ��Form����
     * @throws BaseException
     */
    private Object doQuery (VOPackage vo)
        throws BaseException
    {
    	UserData ud = (UserData) vo.getUserData(); //�ĵ���ǰ�û�����
    	//��ȡform
    	GdzysCxjksForm pf = (GdzysCxjksForm) vo.getData();
        List dataList = new ArrayList();
         //�������ݿ�
        Connection conn = null;
        PreparedStatement ps = null;
        //����� 
	    ResultSet rst = null;
        //UserData ud = new UserData();
	    try
        {
	    String datafilter = ZKAdapter.getInstance().getDataFilterString(ud,"SBDB", "SB_JL_GDZYS_SBB" );
	 	System.out.println("##########datafilter="+datafilter);
	 	
	 	StringBuffer sqlBuf = new StringBuffer();
	    sqlBuf.append("select z.* from (");
	    sqlBuf.append("select t.*,d.kssl,c.SJJE,c.sbbh from (");
	    sqlBuf.append("select a.SBBXLH,a.jsjdm,a.NSRMC,b.SPLX,b.jkpzh from sbdb.SB_JL_GDZYS_SBB a,sbdb.SB_JL_GDZYS_SBBJKSGLB b where 1=1 and "+datafilter+" and a.sbbxlh=b.sbbxlh ");
        if(pf.getNsrmc()!=null&&!pf.getNsrmc().equals("")){
        	sqlBuf.append(" and a.NSRMC like ?");
        }
        if(pf.getJsjdm()!=null&&!pf.getJsjdm().equals("")){
        	sqlBuf.append(" and a.JSJDM like ?");
        }
        if(pf.getPzjdwh()!=null&&!pf.getPzjdwh().equals("")){
        	sqlBuf.append(" and a.PZJDWH like ?");
        }
        if(pf.getSbbxlh()!=null&&!pf.getSbbxlh().equals("")){
        	sqlBuf.append(" and a.SBBXLH like ?");
        }
        sqlBuf.append(" ) t,sbdb.sb_jl_sbjkzb c,sbdb.sb_jl_sbjkmx d where t.jsjdm=c.jsjdm and t.jkpzh=c.jkpzh and c.sjly='"+GdzysCodeConstant.SMSB_SJLY_GDZYS+"' and c.FSDM='"+CodeConstant.FSDM_SMSB+"' and c.zwbs like '"+ CodeConstant.SMSB_ZWBS + "%" + CodeConstant.SMSB_ZWBS +"' and c.jsjdm=d.jsjdm and c.jkpzh=d.jkpzh and c.sbbh=d.sbbh ");
        sqlBuf.append(") z");
    
        System.out.println("#########="+sqlBuf.toString());
      
            conn = SfDBResource.getConnection();
            ps = conn.prepareStatement(sqlBuf.toString());   
            //���������
		    int i=1;
		    if(pf.getNsrmc()!=null&&!pf.getNsrmc().equals("")){
		    	 ps.setString(i++, "%"+pf.getNsrmc()+"%");
	        }
	        if(pf.getJsjdm()!=null&&!pf.getJsjdm().equals("")){
	        	 ps.setString(i++, "%"+pf.getJsjdm()+"%");
	        }
	        if(pf.getPzjdwh()!=null&&!pf.getPzjdwh().equals("")){
	        	 ps.setString(i++, "%"+pf.getPzjdwh()+"%");
	        }
	        if(pf.getSbbxlh()!=null&&!pf.getSbbxlh().equals("")){
	        	 ps.setString(i++, "%"+pf.getSbbxlh()+"%");
	        }
            
            rst = ps.executeQuery();
            while (rst.next()) {
            	HashMap map = new HashMap();
            	//�걨�����к�
            	map.put("sbbxlh", rst.getString("sbbxlh"));
            	//�ɿ�ƾ֤��
            	map.put("jkpzh", rst.getString("jkpzh"));
            	//˰Ʊ����
            	map.put("splx", rst.getString("splx"));
            	//��˰������
            	map.put("nsrmc", rst.getString("nsrmc"));
            	//���������
            	map.put("jsjdm", rst.getString("jsjdm"));
            	//��˰����
            	map.put("kssl", rst.getBigDecimal("kssl")==null?"0":GdzysMoneyUtils.format(rst.getBigDecimal("kssl").doubleValue()));
            	//ʵ�ɽ��
            	map.put("sjje", rst.getBigDecimal("sjje")==null?"0":GdzysMoneyUtils.format(rst.getBigDecimal("sjje").doubleValue()));
            	//�걨���
            	map.put("sbbh", rst.getString("sbbh"));
            	dataList.add(map);
            }
            
            System.out.println("########dataList.size()="+dataList.size());
            
            //��ֵ�Ż�form����
            pf.setDataList(dataList);
            //�ر�resultset
		    rst.close();
		    //�ر�preparestatement
		    ps.close();

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
     * �鿴�ɿ�������
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return ��ǰҳ���Ӧ��Form����
     * @throws BaseException
     */
    private Object doCkJks (VOPackage vo)
        throws BaseException
    {
    	UserData ud = (UserData) vo.getUserData(); //�ĵ���ǰ�û�����
    	//��ȡform
    	GdzysCxjksForm pf = (GdzysCxjksForm) vo.getData();
        List dataList = new ArrayList();
         //�������ݿ�
        Connection conn = null;
        PreparedStatement ps = null;
        //����� 
	    ResultSet rst = null;
        //UserData ud = new UserData();
	    StringBuffer sqlBuf = new StringBuffer();
	    //ormapping����
        Sbjkzb orObj = new Sbjkzb();
        String qxdm = ""; //���ش���
	    Timestamp nowTime = new Timestamp(System.currentTimeMillis());
	    
	    sqlBuf.append("select t.*,(select jsjdm from sbdb.SB_JL_GDZYS_SBB where sbbxlh=t.sbbxlh) jsjdm from sbdb.SB_JL_GDZYS_SBBJKSGLB t where   t.SBBXLH=? and t.jkpzh=? ");
        System.out.println("#########="+sqlBuf.toString());
        System.out.println("#########pf.getCxSbbxlh="+pf.getCxSbbxlh());
        System.out.println("#########pf.getCxJkpzh="+pf.getCxJkpzh());
        try
        {   
            conn = SfDBResource.getConnection();
            ps = conn.prepareStatement(sqlBuf.toString());   
		    int i=1;
		    ps.setString(i++, pf.getCxSbbxlh());
		    ps.setString(i++, pf.getCxJkpzh());
            rst = ps.executeQuery();
           //�нɿ���
            if(rst.next()) {
            	System.out.println("#################1");
            	HashMap tepmmap = new HashMap();
            	//�걨�����к�
            	tepmmap.put("sbbxlh", rst.getString("sbbxlh"));
            	//�ɿ�ƾ֤��
            	tepmmap.put("jkpzh", rst.getString("jkpzh"));
            	//�����
            	tepmmap.put("jsjdm", rst.getString("jsjdm"));
            	dataList.add(tepmmap);
            	pf.setDataList(dataList);
            //�޽ɿ���	
            }
            
		    rst.close();
		    //�ر�preparestatement
		    ps.close();

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
     * �鿴�ɿ�������
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return ��ǰҳ���Ӧ��Form����
     * @throws BaseException
     */
    private Object doDelJks (VOPackage vo)
        throws BaseException
    {
    	UserData ud = (UserData) vo.getUserData(); //�ĵ���ǰ�û�����
    	//��ȡform
    	GdzysCxjksForm pf = (GdzysCxjksForm) vo.getData();
        List dataList = new ArrayList();
         //�������ݿ�
        Connection conn = null;
        PreparedStatement ps = null;
        //����� 
	    ResultSet rst = null;
        //UserData ud = new UserData();
	    StringBuffer sqlBuf = new StringBuffer();
	    //ormapping����
        Sbjkzb orObj = new Sbjkzb();
        String qxdm = ""; //���ش���
	    Timestamp nowTime = new Timestamp(System.currentTimeMillis());
        System.out.println("#########pf.getCxSbbxlh="+pf.getCxSbbxlh());
        System.out.println("#########pf.getCxJkpzh="+pf.getCxJkpzh());
        System.out.println("#########getCxSbbh="+pf.getCxSbbh());
        System.out.println("#########getCxJsjdm="+pf.getCxJsjdm());
        try
        {   
			conn = SfDBResource.getConnection();
			
			deleteJMSB(pf,conn);
			
			//ɾ���ɿ�����ϸ��
			sqlBuf.append("delete from sbdb.sb_jl_sbjkmx where jsjdm=? and jkpzh=? and sbbh=? ");
			ps = conn.prepareStatement(sqlBuf.toString());
			int i = 1;
			ps.setString(i++, pf.getCxJsjdm());
			ps.setString(i++, pf.getCxJkpzh());
			ps.setString(i++, pf.getCxSbbh());
			int count = ps.executeUpdate();
			System.out.println("######�ɿ�����ϸ��count="+count);
			//ɾ���ɿ�������
			sqlBuf=new StringBuffer();
			sqlBuf.append("delete from sbdb.sb_jl_sbjkzb where jsjdm=? and jkpzh=?");
			ps = conn.prepareStatement(sqlBuf.toString());
			i = 1;
			ps.setString(i++, pf.getCxJsjdm());
			ps.setString(i++, pf.getCxJkpzh());
			count = ps.executeUpdate();
			System.out.println("#####�ɿ�������count="+count);
			
			//ɾ������ռ��˰�걨��ɿ��������
			sqlBuf=new StringBuffer();
			sqlBuf.append("delete from sbdb.SB_JL_GDZYS_SBBJKSGLB where sbbxlh=? and jkpzh=?");
			ps = conn.prepareStatement(sqlBuf.toString());
			i = 1;
			ps.setString(i++, pf.getCxSbbxlh());
			ps.setString(i++, pf.getCxJkpzh());
			count = ps.executeUpdate();
			System.out.println("#####����ռ��˰������count="+count);
			
			//���²�ѯ����
			sqlBuf=new StringBuffer();
			sqlBuf.append("select z.* from (");
		    sqlBuf.append("select t.*,d.kssl,c.SJJE,c.sbbh from (");
		    sqlBuf.append("select a.SBBXLH,a.jsjdm,a.NSRMC,b.SPLX,b.jkpzh from sbdb.SB_JL_GDZYS_SBB a,sbdb.SB_JL_GDZYS_SBBJKSGLB b where a.CJR='"+ud.yhid+"' and a.sbbxlh=b.sbbxlh ");
	        if(pf.getNsrmc()!=null&&!pf.getNsrmc().equals("")){
	        	sqlBuf.append(" and a.NSRMC like ?");
	        }
	        if(pf.getJsjdm()!=null&&!pf.getJsjdm().equals("")){
	        	sqlBuf.append(" and a.JSJDM like ?");
	        }
	        if(pf.getPzjdwh()!=null&&!pf.getPzjdwh().equals("")){
	        	sqlBuf.append(" and a.PZJDWH like ?");
	        }
	        if(pf.getSbbxlh()!=null&&!pf.getSbbxlh().equals("")){
	        	sqlBuf.append(" and a.SBBXLH like ?");
	        }
	        sqlBuf.append(" ) t,sbdb.sb_jl_sbjkzb c,sbdb.sb_jl_sbjkmx d where t.jsjdm=c.jsjdm and t.jkpzh=c.jkpzh and c.sjly='"+GdzysCodeConstant.SMSB_SJLY_GDZYS+"' and c.FSDM='"+CodeConstant.FSDM_SMSB+"' and c.zwbs like '"+ CodeConstant.SMSB_ZWBS + "%" + CodeConstant.SMSB_ZWBS +"' and c.jsjdm=d.jsjdm and c.jkpzh=d.jkpzh and c.sbbh=d.sbbh ");
	        sqlBuf.append(") z");
	        ps = conn.prepareStatement(sqlBuf.toString());   
		    i=1;
		    if(pf.getNsrmc()!=null&&!pf.getNsrmc().equals("")){
		    	 ps.setString(i++, "%"+pf.getNsrmc()+"%");
	        }
	        if(pf.getJsjdm()!=null&&!pf.getJsjdm().equals("")){
	        	 ps.setString(i++, "%"+pf.getJsjdm()+"%");
	        }
	        if(pf.getPzjdwh()!=null&&!pf.getPzjdwh().equals("")){
	        	 ps.setString(i++, "%"+pf.getPzjdwh()+"%");
	        }
	        if(pf.getSbbxlh()!=null&&!pf.getSbbxlh().equals("")){
	        	 ps.setString(i++, "%"+pf.getSbbxlh()+"%");
	        }
            
            rst = ps.executeQuery();
            while (rst.next()) {
            	HashMap map = new HashMap();
            	//�걨�����к�
            	map.put("sbbxlh", rst.getString("sbbxlh"));
            	//�ɿ�ƾ֤��
            	map.put("jkpzh", rst.getString("jkpzh"));
            	//˰Ʊ����
            	map.put("splx", rst.getString("splx"));
            	//��˰������
            	map.put("nsrmc", rst.getString("nsrmc"));
            	//���������
            	map.put("jsjdm", rst.getString("jsjdm"));
            	//��˰����
            	map.put("kssl", rst.getBigDecimal("kssl")==null?"0":GdzysMoneyUtils.format(rst.getBigDecimal("kssl").doubleValue()));
            	//ʵ�ɽ��
            	map.put("sjje", rst.getBigDecimal("sjje")==null?"0":GdzysMoneyUtils.format(rst.getBigDecimal("sjje").doubleValue()));
            	//�걨���
            	map.put("sbbh", rst.getString("sbbh"));
            	dataList.add(map);
            }
            
            System.out.println("########dataList.size()="+dataList.size());
            
            //��ֵ�Ż�form����
            pf.setDataList(dataList);
			
			
		    rst.close();
		    //�ر�preparestatement
		    ps.close();

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
     * @Description: TODOɾ�������걨
     * @param pf
     * @throws SQLException 
     * @throws BaseException 
     */
    private void deleteJMSB(GdzysCxjksForm pcf ,Connection con) throws SQLException, BaseException
    {
   
		String sbbxlh = pcf.getCxSbbxlh();
		String queryGDJMSB="select jsjdm from sbdb.SB_JL_GDZYS_SBB where sbbxlh = ?";
		PreparedStatement queryGDJMSBPs = con.prepareStatement(queryGDJMSB);
		queryGDJMSBPs.setString(1, sbbxlh);
		ResultSet queryGDJMSBRsRs = queryGDJMSBPs.executeQuery();

		if(!queryGDJMSBRsRs.next())
		{
			return ;
		}
		
		String jsjdm = queryGDJMSBRsRs.getString("JSJDM");				//���������
		String szsmdm =GdzysCodeConstant.SMSB_GDZYS_SZSMDM;				//˰��˰Ŀ����	
		
		//��ϸ
		String querySql = "select * from sbdb.SB_JL_GDZYS_SBBSBMX where sbbxlh = ?";
		PreparedStatement queryPs = con.prepareStatement(querySql);
		queryPs.setString(1, sbbxlh);
		ResultSet queryRs = queryPs.executeQuery();
		
		String sjly = GdzysCodeConstant.SMSB_SJLY_GDZYS;
		
		//����ÿһ����ϸ��Ϣ��ѯ�Ƿ���ϼ���������ɾ��
		while(queryRs.next())
		{
			String zdyt = queryRs.getString("ZDYTDM");
			int checksyse = queryRs.getInt("SYSE");
			System.out.println("zdyt:"+zdyt+"checksyse:"+checksyse);
			if("00".equals(zdyt) && checksyse ==2 )
			{
				Timestamp cjrq = queryRs.getTimestamp("cjrq");
				
				//���ýӿ�ɾ����������
				ServiceProxy sp = new ServiceProxy();
				sp.deleteSBJM(jsjdm, szsmdm, cjrq,sjly);
			}
			
		}
		
    }
    
}
