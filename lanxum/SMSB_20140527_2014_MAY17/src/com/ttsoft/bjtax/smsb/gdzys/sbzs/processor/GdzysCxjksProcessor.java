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
 * <p>Title: 北京地税核心征管系统--耕地占用税征收管理</p>
 * <p>Description: 撤销缴款书AProcessor</p>
 * @author wangxq
 * @version 1.0
 */
public class GdzysCxjksProcessor implements Processor{
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
            case GdzysCodeConstant.SMSB_GDZYS_CKJKSACTION:
                result = doCkJks(vo);
                break;
            case CodeConstant.SMSB_DELETEACTION:
            	 result = doDelJks(vo);
                 break;
            default:
                throw new ApplicationException(
                    "ActionType有错误，processor中找不到相应的方法.");
        }
        return result;
    }
	/**
     * 查询缴款书数据
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return 当前页面对应的Form对象
     * @throws BaseException
     */
    private Object doQuery (VOPackage vo)
        throws BaseException
    {
    	UserData ud = (UserData) vo.getUserData(); //的到当前用户对象
    	//获取form
    	GdzysCxjksForm pf = (GdzysCxjksForm) vo.getData();
        List dataList = new ArrayList();
         //连接数据库
        Connection conn = null;
        PreparedStatement ps = null;
        //结果集 
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
            //计算机代码
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
            	//申报表序列号
            	map.put("sbbxlh", rst.getString("sbbxlh"));
            	//缴款凭证号
            	map.put("jkpzh", rst.getString("jkpzh"));
            	//税票类型
            	map.put("splx", rst.getString("splx"));
            	//纳税人名称
            	map.put("nsrmc", rst.getString("nsrmc"));
            	//计算机代码
            	map.put("jsjdm", rst.getString("jsjdm"));
            	//课税数量
            	map.put("kssl", rst.getBigDecimal("kssl")==null?"0":GdzysMoneyUtils.format(rst.getBigDecimal("kssl").doubleValue()));
            	//实缴金额
            	map.put("sjje", rst.getBigDecimal("sjje")==null?"0":GdzysMoneyUtils.format(rst.getBigDecimal("sjje").doubleValue()));
            	//申报编号
            	map.put("sbbh", rst.getString("sbbh"));
            	dataList.add(map);
            }
            
            System.out.println("########dataList.size()="+dataList.size());
            
            //把值放回form对象
            pf.setDataList(dataList);
            //关闭resultset
		    rst.close();
		    //关闭preparestatement
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
     * 查看缴款书数据
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return 当前页面对应的Form对象
     * @throws BaseException
     */
    private Object doCkJks (VOPackage vo)
        throws BaseException
    {
    	UserData ud = (UserData) vo.getUserData(); //的到当前用户对象
    	//获取form
    	GdzysCxjksForm pf = (GdzysCxjksForm) vo.getData();
        List dataList = new ArrayList();
         //连接数据库
        Connection conn = null;
        PreparedStatement ps = null;
        //结果集 
	    ResultSet rst = null;
        //UserData ud = new UserData();
	    StringBuffer sqlBuf = new StringBuffer();
	    //ormapping对象
        Sbjkzb orObj = new Sbjkzb();
        String qxdm = ""; //区县代码
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
           //有缴款书
            if(rst.next()) {
            	System.out.println("#################1");
            	HashMap tepmmap = new HashMap();
            	//申报表序列号
            	tepmmap.put("sbbxlh", rst.getString("sbbxlh"));
            	//缴款凭证号
            	tepmmap.put("jkpzh", rst.getString("jkpzh"));
            	//计算机
            	tepmmap.put("jsjdm", rst.getString("jsjdm"));
            	dataList.add(tepmmap);
            	pf.setDataList(dataList);
            //无缴款书	
            }
            
		    rst.close();
		    //关闭preparestatement
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
     * 查看缴款书数据
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return 当前页面对应的Form对象
     * @throws BaseException
     */
    private Object doDelJks (VOPackage vo)
        throws BaseException
    {
    	UserData ud = (UserData) vo.getUserData(); //的到当前用户对象
    	//获取form
    	GdzysCxjksForm pf = (GdzysCxjksForm) vo.getData();
        List dataList = new ArrayList();
         //连接数据库
        Connection conn = null;
        PreparedStatement ps = null;
        //结果集 
	    ResultSet rst = null;
        //UserData ud = new UserData();
	    StringBuffer sqlBuf = new StringBuffer();
	    //ormapping对象
        Sbjkzb orObj = new Sbjkzb();
        String qxdm = ""; //区县代码
	    Timestamp nowTime = new Timestamp(System.currentTimeMillis());
        System.out.println("#########pf.getCxSbbxlh="+pf.getCxSbbxlh());
        System.out.println("#########pf.getCxJkpzh="+pf.getCxJkpzh());
        System.out.println("#########getCxSbbh="+pf.getCxSbbh());
        System.out.println("#########getCxJsjdm="+pf.getCxJsjdm());
        try
        {   
			conn = SfDBResource.getConnection();
			
			deleteJMSB(pf,conn);
			
			//删除缴款书明细表
			sqlBuf.append("delete from sbdb.sb_jl_sbjkmx where jsjdm=? and jkpzh=? and sbbh=? ");
			ps = conn.prepareStatement(sqlBuf.toString());
			int i = 1;
			ps.setString(i++, pf.getCxJsjdm());
			ps.setString(i++, pf.getCxJkpzh());
			ps.setString(i++, pf.getCxSbbh());
			int count = ps.executeUpdate();
			System.out.println("######缴款书明细表count="+count);
			//删除缴款书主表
			sqlBuf=new StringBuffer();
			sqlBuf.append("delete from sbdb.sb_jl_sbjkzb where jsjdm=? and jkpzh=?");
			ps = conn.prepareStatement(sqlBuf.toString());
			i = 1;
			ps.setString(i++, pf.getCxJsjdm());
			ps.setString(i++, pf.getCxJkpzh());
			count = ps.executeUpdate();
			System.out.println("#####缴款书主表count="+count);
			
			//删除耕地占用税申报表缴款书关联表
			sqlBuf=new StringBuffer();
			sqlBuf.append("delete from sbdb.SB_JL_GDZYS_SBBJKSGLB where sbbxlh=? and jkpzh=?");
			ps = conn.prepareStatement(sqlBuf.toString());
			i = 1;
			ps.setString(i++, pf.getCxSbbxlh());
			ps.setString(i++, pf.getCxJkpzh());
			count = ps.executeUpdate();
			System.out.println("#####耕地占用税关联表count="+count);
			
			//重新查询数据
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
            	//申报表序列号
            	map.put("sbbxlh", rst.getString("sbbxlh"));
            	//缴款凭证号
            	map.put("jkpzh", rst.getString("jkpzh"));
            	//税票类型
            	map.put("splx", rst.getString("splx"));
            	//纳税人名称
            	map.put("nsrmc", rst.getString("nsrmc"));
            	//计算机代码
            	map.put("jsjdm", rst.getString("jsjdm"));
            	//课税数量
            	map.put("kssl", rst.getBigDecimal("kssl")==null?"0":GdzysMoneyUtils.format(rst.getBigDecimal("kssl").doubleValue()));
            	//实缴金额
            	map.put("sjje", rst.getBigDecimal("sjje")==null?"0":GdzysMoneyUtils.format(rst.getBigDecimal("sjje").doubleValue()));
            	//申报编号
            	map.put("sbbh", rst.getString("sbbh"));
            	dataList.add(map);
            }
            
            System.out.println("########dataList.size()="+dataList.size());
            
            //把值放回form对象
            pf.setDataList(dataList);
			
			
		    rst.close();
		    //关闭preparestatement
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
     * @Description: TODO删除减免申报
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
		
		String jsjdm = queryGDJMSBRsRs.getString("JSJDM");				//计算机代码
		String szsmdm =GdzysCodeConstant.SMSB_GDZYS_SZSMDM;				//税总税目代码	
		
		//明细
		String querySql = "select * from sbdb.SB_JL_GDZYS_SBBSBMX where sbbxlh = ?";
		PreparedStatement queryPs = con.prepareStatement(querySql);
		queryPs.setString(1, sbbxlh);
		ResultSet queryRs = queryPs.executeQuery();
		
		String sjly = GdzysCodeConstant.SMSB_SJLY_GDZYS;
		
		//对于每一条明细信息查询是否符合减免条件并删除
		while(queryRs.next())
		{
			String zdyt = queryRs.getString("ZDYTDM");
			int checksyse = queryRs.getInt("SYSE");
			System.out.println("zdyt:"+zdyt+"checksyse:"+checksyse);
			if("00".equals(zdyt) && checksyse ==2 )
			{
				Timestamp cjrq = queryRs.getTimestamp("cjrq");
				
				//调用接口删除减免数据
				ServiceProxy sp = new ServiceProxy();
				sp.deleteSBJM(jsjdm, szsmdm, cjrq,sjly);
			}
			
		}
		
    }
    
}
