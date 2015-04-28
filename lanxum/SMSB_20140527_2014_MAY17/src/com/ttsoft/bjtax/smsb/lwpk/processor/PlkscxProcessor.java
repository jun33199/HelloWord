package com.ttsoft.bjtax.smsb.lwpk.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.lwpk.model.PKJBSJModel;
import com.ttsoft.bjtax.smsb.lwpk.model.PLKKPLCXModel;
import com.ttsoft.bjtax.smsb.lwpk.web.PlkscxForm;
import com.ttsoft.bjtax.smsb.model.client.swdwmodel;
import com.ttsoft.bjtax.smsb.model.client.yhdmmodel;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.StringUtil;
import com.ttsoft.framework.util.VOPackage;

public class PlkscxProcessor implements Processor{
	public Object process(VOPackage vo) throws BaseException {
		
		 Object result = null;
		 if (vo == null) {
		      throw new NullPointerException();
		    }

		    switch (vo.getAction()) {
		    	//获取分局数据
		      case CodeConstant.SMSB_DQJSLIST:
		        result =dogetdqjslist(vo);
		        break;
		      //获取税务所数据
		      case CodeConstant.SMSB_CXDQJSLIST:
					result = dogetcxdqjslist(vo);
			   break;
			  //获取银行数据
		      case CodeConstant.SMSB_CXYHLIST:
					result = getYhlist(vo);
			   break;
			  //查询
		      case CodeConstant.SMSB_QUERYACTION:
		        result = doQuery(vo);
		        break;
		      //查询扣款详细信息
		      case CodeConstant.SMSB_QUERYA_DETATILACTION:
		        result = doQueryDetail(vo);
		        break;
		      case CodeConstant.SMSB_EXPORT:
			        result = doExport(vo);
			        break;
		      case CodeConstant.SMSB_EXPORTDETAIL:
			        result = doExportDetail(vo);
			        break;
		      default:
		        throw new UnsupportedOperationException(
		            "Method process() not yet implemented.");
		    }
		    return result;
	}
	private Object doQuery(VOPackage vo) {
		//获取form
		PlkscxForm form = (PlkscxForm) vo.getData();
		//基本数据model
		PKJBSJModel jbsjModel = new PKJBSJModel();
		//获取jsjdm
		//String jsjdm = form.getJsjdm();
		//连接数据库
		PreparedStatement ps = null;
	    Connection conn = null;
	     //获取基本数据sql
	     
	     try {
	    	 int count=0;//查询总记录条数
	    	 int currentPage = form.getCurrentPage();
		     int startNum = (currentPage-1)*(form.getPageSize())+1;
		     int endNum = currentPage*(form.getPageSize());
		    // System.out.println("################startNum="+startNum);
		    // System.out.println("################endNum="+endNum);
		     
		     //获取连接
		      conn = SfDBResource.getConnection();
		     
		       StringBuffer buffer = new StringBuffer();
			   buffer.append("select count(bb.num) from (select rownum num,aa.* from (");
			    buffer.append("select (select YHMC from dmdb.gy_dm_yh where YHDM=a.yhdm) yh,");
			    buffer.append("(select swjgzzjgmc from dmdb.gy_dm_swjgzzjg where swjgzzjgdm=a.qxdm||'00' ) fj,");
			    buffer.append("(select swjgzzjgmc from dmdb.gy_dm_swjgzzjg where swjgzzjgdm=b.swjgzzjgdm ) sws,a.kkrq,");
			    buffer.append("count(distinct decode(a.kkbz, '20', a.sphm)) cgbs,");
			    buffer.append("count(distinct decode(a.kkbz, '21', a.sphm)) sbbs,");
			    buffer.append("count(distinct a.sphm) zbs,");
			    buffer.append("count(distinct decode(a.kkbz, '20', a.sphm))/count(distinct a.sphm) bscgl,");
			    buffer.append("sum(decode(a.kkbz, '20', b.sjje)) cgje,");
			    buffer.append("sum(decode(a.kkbz, '21', b.sjje)) sbje,");
			    buffer.append("sum(b.sjje) zje,");
			    buffer.append("sum(decode(a.kkbz, '20', b.sjje))/sum(b.sjje) jecgl");
			    buffer.append(" from sbdb.sb_jl_plkkspxx_lw a,SBDB.SB_JL_PLKKSPZB_LW b");
			    buffer.append(" where a.sphm=b.sphm ");
			    if(form.getNd()!=null&&form.getNd().trim().length()>0){
			    	 buffer.append(" and a.nd = ? ");
			    }
			    if(form.getYstart()!=null&&form.getYstart().trim().length()>0){
			    	 buffer.append(" and a.yd >= ? ");
			    }
			    if(form.getYend()!=null&&form.getYend().trim().length()>0){
			    	 buffer.append(" and a.yd <= ? ");
			    }
			    
			    if(form.getDqjs()!=null&&form.getDqjs().trim().length()>0){
			    	 buffer.append(" and a.qxdm = ? ");
			    }
			    
			    if(form.getCxdqjs()!=null&&form.getCxdqjs().trim().length()>0){
			    	 buffer.append(" and b.swjgzzjgdm = ? ");
			    }
			    
			    if(form.getYhdm()!=null&&form.getYhdm().trim().length()>0){
			    	 buffer.append(" and a.yhdm = ? ");
			    }
			    buffer.append(" group by a.yhdm, a.qxdm,b.swjgzzjgdm,a.kkrq ");
			    
			    buffer.append(") aa ) bb ");
			    
			    ps = conn.prepareStatement(buffer.toString());
			    int i=1;
			    if(form.getNd()!=null&&form.getNd().trim().length()>0){
			    	ps.setString(i, form.getNd().trim());
			    	i++;
			    }
			    if(form.getYstart()!=null&&form.getYstart().trim().length()>0){
			    	ps.setString(i, form.getYstart().trim());
			    	 i++;
			    }
			    
			    if(form.getYend()!=null&&form.getYend().trim().length()>0){
			    	ps.setString(i, form.getYend().trim());
			    	 i++;
			    }
			    
			    if(form.getDqjs()!=null&&form.getDqjs().trim().length()>0){
			    	ps.setString(i, form.getDqjs().trim().substring(0,2));
			    	 i++;
			    }
			    
			    if(form.getCxdqjs()!=null&&form.getCxdqjs().trim().length()>0){
			    	ps.setString(i, form.getCxdqjs().trim());
			    	 i++;
			    }
			    
			    if(form.getYhdm()!=null&&form.getYhdm().trim().length()>0){
			    	ps.setString(i, form.getYhdm().trim());
			    	 i++;
			    }
			    ResultSet rs = ps.executeQuery();
			    if(rs.next())
				{
					count=rs.getInt(1);
				}
			   // System.out.println("###########count="+count);
			    form.setTotalRowCount(count);
			    rs.close();
			    
		    
	    	
			
		    buffer = new StringBuffer();
		    buffer.append("select bb.* from (select rownum num,aa.* from (");
		    buffer.append("select (select YHMC from dmdb.gy_dm_yh where YHDM=a.yhdm) yh,a.yhdm,");
		    buffer.append("(select swjgzzjgmc from dmdb.gy_dm_swjgzzjg where swjgzzjgdm=a.qxdm||'00' ) fj,a.qxdm fjdm,");
		    buffer.append("(select swjgzzjgmc from dmdb.gy_dm_swjgzzjg where swjgzzjgdm=b.swjgzzjgdm ) sws,b.swjgzzjgdm,a.kkrq,");
		    buffer.append("count(distinct decode(a.kkbz, '20', a.sphm)) cgbs,");
		    buffer.append("count(distinct decode(a.kkbz, '21', a.sphm)) sbbs,");
		    buffer.append("count(distinct a.sphm) zbs,");
		    buffer.append("count(distinct decode(a.kkbz, '20', a.sphm))/count(distinct a.sphm)*100 bscgl,");
		    buffer.append("sum(decode(a.kkbz, '20', b.sjje)) cgje,");
		    buffer.append("sum(decode(a.kkbz, '21', b.sjje)) sbje,");
		    buffer.append("sum(b.sjje) zje,");
		    buffer.append("sum(decode(a.kkbz, '20', b.sjje))/sum(b.sjje)*100 jecgl");
		    buffer.append(" from sbdb.sb_jl_plkkspxx_lw a,SBDB.SB_JL_PLKKSPZB_LW b");
		    buffer.append(" where a.sphm=b.sphm ");
		    if(form.getNd()!=null&&form.getNd().trim().length()>0){
		    	 buffer.append(" and a.nd = ? ");
		    }
		    if(form.getYstart()!=null&&form.getYstart().trim().length()>0){
		    	 buffer.append(" and a.yd >= ? ");
		    }
		    if(form.getYend()!=null&&form.getYend().trim().length()>0){
		    	 buffer.append(" and a.yd <= ? ");
		    }
		    
		    if(form.getDqjs()!=null&&form.getDqjs().trim().length()>0){
		    	 buffer.append(" and a.qxdm = ? ");
		    }
		    
		    if(form.getCxdqjs()!=null&&form.getCxdqjs().trim().length()>0){
		    	 buffer.append(" and b.swjgzzjgdm = ? ");
		    }
		    
		    if(form.getYhdm()!=null&&form.getYhdm().trim().length()>0){
		    	 buffer.append(" and a.yhdm = ? ");
		    }
		    buffer.append(" group by a.yhdm, a.qxdm,b.swjgzzjgdm,a.kkrq ");
		    
		    buffer.append(") aa ) bb ");
		    
		    buffer.append(" where bb.num >= ? and bb.num <= ?");
		    
		    
		    //单户查询纳税信息
		    /*
		    System.out.println("################buffer.toString(="+buffer.toString());
		    
		    System.out.println("################form.getNd()="+form.getNd());
		    System.out.println("################form.getYstart()="+form.getYstart());
		    System.out.println("################form.getYend()="+form.getYend());
		    System.out.println("################form.getDqjs()="+form.getDqjs());
		    if(form.getDqjs()!=null&&form.getDqjs().length()>0){
		    System.out.println("################form.getDqjs().substring(0,2)="+form.getDqjs().substring(0,2));
		    }
		    System.out.println("################1form.getCxdqjs()="+form.getCxdqjs());
		    System.out.println("################form.getYhdm()="+form.getYhdm());
		    */
		    ps = conn.prepareStatement(buffer.toString());
		    i=1;
		    if(form.getNd()!=null&&form.getNd().trim().length()>0){
		    	ps.setString(i, form.getNd().trim());
		    	i++;
		    }
		    if(form.getYstart()!=null&&form.getYstart().trim().length()>0){
		    	ps.setString(i, form.getYstart().trim());
		    	 i++;
		    }
		    
		    if(form.getYend()!=null&&form.getYend().trim().length()>0){
		    	ps.setString(i, form.getYend().trim());
		    	 i++;
		    }
		    
		    if(form.getDqjs()!=null&&form.getDqjs().trim().length()>0){
		    	ps.setString(i, form.getDqjs().trim().substring(0,2));
		    	 i++;
		    }
		    
		    if(form.getCxdqjs()!=null&&form.getCxdqjs().trim().length()>0){
		    	ps.setString(i, form.getCxdqjs().trim());
		    	 i++;
		    }
		    
		    if(form.getYhdm()!=null&&form.getYhdm().trim().length()>0){
		    	ps.setString(i, form.getYhdm().trim());
		    	 i++;
		    }
		     ps.setInt(i++, startNum);
	    	 ps.setInt(i++, endNum);
		    
		    ResultSet rst = ps.executeQuery();
		    
		    List list = new ArrayList();
		    //合计金额
		    double hjje = 0; 
		    //成功合计金额
		    double cgHjje = 0; 
		    //失败合计金额
		    double sbHjje = 0; 
		    
		    
		    //合计笔数
		    long hjbs = 0; 
		    //合计成功笔数
		    long hjcgbs = 0; 
		    //合计失败笔数
		    long hjsbbs = 0; 
		    while(rst.next()){
		    	PLKKPLCXModel plkkplcxmodel = new PLKKPLCXModel();
		    	String yh=rst.getString("yh");
		    	if(yh==null){
                  yh="";
		    	}
		    	plkkplcxmodel.setYh(yh);
		    	
		    	String yhdm=rst.getString("yhdm");
		    	if(yhdm==null){
		    		yhdm="";
		    	}
		    	plkkplcxmodel.setYhdm(yhdm);
		    	
		    	String fj=rst.getString("fj");
		    	if(fj==null){
		    		fj="";
		    	}
		    	plkkplcxmodel.setFj(fj);
		    	String fjdm=rst.getString("fjdm");
		    	if(fjdm==null){
		    		fjdm="";
		    	}
		    	plkkplcxmodel.setFjdm(fjdm);
		    	
		    	String sws=rst.getString("sws");
		    	if(sws==null){
		    		sws="";
		    	}
		    	plkkplcxmodel.setSws(sws);
		    	
		    	
		    	String swjgzzjgdm=rst.getString("swjgzzjgdm");
		    	if(swjgzzjgdm==null){
		    		swjgzzjgdm="";
		    	}
		    	plkkplcxmodel.setSwsdm(swjgzzjgdm);
		    	
		    	String kksj="";
		    	SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
    			try {
    				if(rst.getTimestamp("kkrq")!=null){
    					kksj= df1.format(rst.getTimestamp("kkrq"));
    				}
    			}
    			catch (Exception ex) {
    				ex.printStackTrace();
    			} 
    			plkkplcxmodel.setKksj(kksj);
    			
    			String cgbs=rst.getString("cgbs");
		    	if(cgbs==null){
		    		cgbs="0";
		    	}
		    	hjcgbs=hjcgbs+Long.parseLong(cgbs);
		    	plkkplcxmodel.setCgbs(cgbs);
		    	
		    	String sbbs=rst.getString("sbbs");
		    	if(sbbs==null){
		    		sbbs="0";
		    	}
		    	hjsbbs=hjsbbs+Long.parseLong(sbbs);
		    	plkkplcxmodel.setSbbs(sbbs);
		    	
		    	String zbs=rst.getString("zbs");
		    	if(zbs==null){
		    		zbs="0";
		    	}
		    	
		    	hjbs=hjbs+Long.parseLong(zbs);
		    	
		    	plkkplcxmodel.setZbs(zbs);
		    	
		    	String bscgl="";
		    	if(rst.getBigDecimal("bscgl")==null){
		    		bscgl="0.0%";
		    	}else{
		    		
		    		double   f_bscgl   =   rst.getBigDecimal("bscgl").setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
		    		bscgl=String
					.valueOf(f_bscgl)+"%";
		    		
		    	}
		    	plkkplcxmodel.setBscgl(bscgl);
		    	
		    	String cgje=rst.getString("cgje");
		    	if(cgje==null){
		    		cgje="0.00";
		    	}
		    	//成功合计金额
		    	cgHjje = cgHjje
                + StringUtil.getDouble(cgje,
                                       0);
		    	plkkplcxmodel.setCgje(cgje);
		    	
		    	String sbje=rst.getString("sbje");
		    	if(sbje==null){
		    		sbje="0.00";
		    	}
		    	
		    	//失败合计金额
		    	sbHjje = sbHjje
                + StringUtil.getDouble(sbje,
                                       0);		    	
		    	plkkplcxmodel.setSbje(sbje);
		    	
		    	
		    	String zje=rst.getString("zje");
		    	if(zje==null){
		    		zje="0.00";
		    	}
		    	
		    	hjje = hjje
                + StringUtil.getDouble(zje,
                                       0);
		    	
		    	plkkplcxmodel.setZje(zje);
		    	
		    	String jecgl="";
		    	if(rst.getBigDecimal("jecgl")==null){
		    		jecgl="0.0%";
		    	}else{
		    		double   f_jecgl   =   rst.getBigDecimal("jecgl").setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
		    		jecgl=String
					.valueOf(f_jecgl)+"%";
		    	}
		    	
		    	plkkplcxmodel.setJecgl(jecgl);
		    	plkkplcxmodel.setRownum(startNum);
				startNum++;
		        list.add(plkkplcxmodel);
		    }
		    rst.close();
		    
		    String tempZje=StringUtil.getCurrency(String.valueOf(hjje));
		    tempZje=tempZje.replaceAll("￥", "");
		    tempZje=tempZje.replaceAll(",", "");
		    form.setZje(tempZje);
		    
		    String tempcgZje=StringUtil.getCurrency(String.valueOf(cgHjje));
		    tempcgZje=tempcgZje.replaceAll("￥", "");
		    tempcgZje=tempcgZje.replaceAll(",", "");
		    form.setCgZje(tempcgZje);
		    
		    String tempsbZje=StringUtil.getCurrency(String.valueOf(sbHjje));
		    tempsbZje=tempsbZje.replaceAll("￥", "");
		    tempsbZje=tempsbZje.replaceAll(",", "");
		    form.setSbZje(tempsbZje);
		    
		    form.setZbs(new Long(hjbs).toString());
		    form.setZcgbs(new Long(hjcgbs).toString());
		    form.setZsbbs(new Long(hjsbbs).toString());
		    form.setDataList(list);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
   	     SfDBResource.freeConnection(conn);
	    }
		return form;
	}
	
	private Object doQueryDetail(VOPackage vo) {
		//获取form
		PlkscxForm form = (PlkscxForm) vo.getData();
		//基本数据model
		PKJBSJModel jbsjModel = new PKJBSJModel();
		//获取jsjdm
		//String jsjdm = form.getJsjdm();
		//连接数据库
		PreparedStatement ps = null;
	    Connection conn = null;
	     //获取基本数据sql
	     
	     try {
	    	 //conn = SfDBResource.getConnection();
	    	 
	    	 int count=0;//查询总记录条数
	    	 int currentPage = form.getCurrentPageXx();
		     int startNum = (currentPage-1)*(form.getPageSizeXx())+1;
		     int endNum = currentPage*(form.getPageSizeXx());
		     //System.out.println("doQueryDetail################startNum="+startNum);
		    // System.out.println("doQueryDetail################endNum="+endNum);
		     
		     //获取连接
		      conn = SfDBResource.getConnection();
		     
		       StringBuffer buffer = new StringBuffer();
		       buffer.append("select count(bb.num) from (select rownum num,aa.* from (");
			   buffer.append("select (select c.jsjdm from djdb.dj_jl_jbsj c, djdb.dj_jl_qyry d where c.jsjdm = d.jsjdm and d.zwdm = '01' and c.jsjdm=a.jsjdm ) jsjdm,");
			    buffer.append("(select c.nsrmc from djdb.dj_jl_jbsj c, djdb.dj_jl_qyry d where c.jsjdm = d.jsjdm and d.zwdm = '01' and c.jsjdm=a.jsjdm ) nsrmc,");
			    buffer.append("(select d.xm from djdb.dj_jl_jbsj c, djdb.dj_jl_qyry d where c.jsjdm = d.jsjdm and d.zwdm = '01' and c.jsjdm=a.jsjdm ) xm,");
			    buffer.append("(select d.gddh from djdb.dj_jl_jbsj c, djdb.dj_jl_qyry d where c.jsjdm = d.jsjdm and d.zwdm = '01' and c.jsjdm=a.jsjdm ) gddh,");
			    buffer.append("(select d.yddh from djdb.dj_jl_jbsj c, djdb.dj_jl_qyry d where c.jsjdm = d.jsjdm and d.zwdm = '01' and c.jsjdm=a.jsjdm ) yddh,");
			    buffer.append("(select c.zcdz from djdb.dj_jl_jbsj c, djdb.dj_jl_qyry d where c.jsjdm = d.jsjdm and d.zwdm = '01' and c.jsjdm=a.jsjdm ) zcdz,");
			    buffer.append("(select c.jydz from djdb.dj_jl_jbsj c, djdb.dj_jl_qyry d where c.jsjdm = d.jsjdm and d.zwdm = '01' and c.jsjdm=a.jsjdm ) jydz,");
	 
			    buffer.append("a.sphm,");
			    buffer.append("(select SZSMMC from DMDB.SB_DM_SZSM where SZSMDM=b.szdm) szmc,");
			    buffer.append("b.sjje,");
			    buffer.append("a.kkrq,");
			    buffer.append("(decode(a.kkbz,'00','未扣款','10','扣款中','20','扣款成功','21','扣款失败','其他')) kkjg,");
			    buffer.append("a.yhkkjgms");
			    buffer.append(" from sbdb.sb_jl_plkkspxx_lw a,SBDB.SB_JL_PLKKSPZB_LW b");
			    buffer.append(" where a.sphm=b.sphm ");
			    if(form.getNd()!=null&&form.getNd().trim().length()>0){
			    	 buffer.append(" and a.nd = ? ");
			    }
			    if(form.getYstart()!=null&&form.getYstart().trim().length()>0){
			    	 buffer.append(" and a.yd >= ? ");
			    }
			    if(form.getYend()!=null&&form.getYend().trim().length()>0){
			    	 buffer.append(" and a.yd <= ? ");
			    }
			    
			    if(form.getDqjs()!=null&&form.getDqjs().trim().length()>0){
			    	 buffer.append(" and a.qxdm = ? ");
			    }
			    
			    if(form.getCxdqjs()!=null&&form.getCxdqjs().trim().length()>0){
			    	 buffer.append(" and b.swjgzzjgdm = ? ");
			    }
			    
			    if(form.getYhdm()!=null&&form.getYhdm().trim().length()>0){
			    	 buffer.append(" and a.yhdm = ? ");
			    }
			    
			    if(form.getCgsbFlag()!=null&&form.getCgsbFlag().trim().length()>0){
			    	 buffer.append(" and a.kkbz = ? ");
			    }
			    
			    
			    if(form.getFjdmxx()!=null&&form.getFjdmxx().trim().length()>0){
			    	 buffer.append(" and a.qxdm = ? ");
			    }
			    
			    if(form.getSwsdmxx()!=null&&form.getSwsdmxx().trim().length()>0){
			    	 buffer.append(" and b.swjgzzjgdm = ? ");
			    }
			    
			    if(form.getYhdmxx()!=null&&form.getYhdmxx().trim().length()>0){
			    	 buffer.append(" and a.yhdm = ? ");
			    }
			    if(form.getKksjxx()!=null&&form.getKksjxx().trim().length()>0){
			    	 buffer.append(" and a.kkrq = to_date(?,'yyyy-mm-dd') ");
			    }
			    
			    buffer.append(") aa ) bb ");
			    
			    ps = conn.prepareStatement(buffer.toString());
			    
			    int i=1;
			    if(form.getNd()!=null&&form.getNd().trim().length()>0){
			    	ps.setString(i, form.getNd().trim());
			    	i++;
			    }
			    if(form.getYstart()!=null&&form.getYstart().trim().length()>0){
			    	ps.setString(i, form.getYstart().trim());
			    	 i++;
			    }
			    
			    if(form.getYend()!=null&&form.getYend().trim().length()>0){
			    	ps.setString(i, form.getYend().trim());
			    	 i++;
			    }
			    
			    if(form.getDqjs()!=null&&form.getDqjs().trim().length()>0){
			    	ps.setString(i, form.getDqjs().trim().substring(0,2));
			    	 i++;
			    }
			    
			    if(form.getCxdqjs()!=null&&form.getCxdqjs().trim().length()>0){
			    	ps.setString(i, form.getCxdqjs().trim());
			    	 i++;
			    }
			    
			    if(form.getYhdm()!=null&&form.getYhdm().trim().length()>0){
			    	ps.setString(i, form.getYhdm().trim());
			    	 i++;
			    }
			    
			    if(form.getCgsbFlag()!=null&&form.getCgsbFlag().trim().length()>0){
			    	ps.setString(i, form.getCgsbFlag().trim());
			    	 i++;
			    }
			    
			    if(form.getFjdmxx()!=null&&form.getFjdmxx().trim().length()>0){
			    	ps.setString(i, form.getFjdmxx().trim());
			    	 i++;
			    }
			    
			    if(form.getSwsdmxx()!=null&&form.getSwsdmxx().trim().length()>0){
			    	ps.setString(i, form.getSwsdmxx().trim());
			    	 i++;
			    }
			    
			    if(form.getYhdmxx()!=null&&form.getYhdmxx().trim().length()>0){
			    	ps.setString(i, form.getYhdmxx().trim());
			    	 i++;
			    }
			    if(form.getKksjxx()!=null&&form.getKksjxx().trim().length()>0){
			    	ps.setString(i, form.getKksjxx().trim());
			    	 i++;
			    }
			    ResultSet rs = ps.executeQuery();
			    if(rs.next())
				{
					count=rs.getInt(1);
				}
			    //System.out.println("###########count="+count);
			    form.setTotalRowCountXx(count);
			    rs.close();
			    
		    
	    	
			
	    	buffer = new StringBuffer();
		    buffer.append("select bb.* from (select rownum num,aa.* from (");
		    buffer.append("select (select c.jsjdm from djdb.dj_jl_jbsj c, djdb.dj_jl_qyry d where c.jsjdm = d.jsjdm and d.zwdm = '01' and c.jsjdm=a.jsjdm ) jsjdm,");
		    buffer.append("(select c.nsrmc from djdb.dj_jl_jbsj c, djdb.dj_jl_qyry d where c.jsjdm = d.jsjdm and d.zwdm = '01' and c.jsjdm=a.jsjdm ) nsrmc,");
		    buffer.append("(select d.xm from djdb.dj_jl_jbsj c, djdb.dj_jl_qyry d where c.jsjdm = d.jsjdm and d.zwdm = '01' and c.jsjdm=a.jsjdm ) xm,");
		    buffer.append("(select d.gddh from djdb.dj_jl_jbsj c, djdb.dj_jl_qyry d where c.jsjdm = d.jsjdm and d.zwdm = '01' and c.jsjdm=a.jsjdm ) gddh,");
		    buffer.append("(select d.yddh from djdb.dj_jl_jbsj c, djdb.dj_jl_qyry d where c.jsjdm = d.jsjdm and d.zwdm = '01' and c.jsjdm=a.jsjdm ) yddh,");
		    buffer.append("(select c.zcdz from djdb.dj_jl_jbsj c, djdb.dj_jl_qyry d where c.jsjdm = d.jsjdm and d.zwdm = '01' and c.jsjdm=a.jsjdm ) zcdz,");
		    buffer.append("(select c.jydz from djdb.dj_jl_jbsj c, djdb.dj_jl_qyry d where c.jsjdm = d.jsjdm and d.zwdm = '01' and c.jsjdm=a.jsjdm ) jydz,");
 
		    buffer.append("a.sphm,");
		    buffer.append("(select SZSMMC from DMDB.SB_DM_SZSM where SZSMDM=b.szdm) szmc,");
		    buffer.append("b.sjje,");
		    buffer.append("a.kkrq,");
		    buffer.append("(decode(a.kkbz,'00','未扣款','10','扣款中','20','扣款成功','21','扣款失败','其他')) kkjg,");
		    buffer.append("a.yhkkjgms");
		    buffer.append(" from sbdb.sb_jl_plkkspxx_lw a,SBDB.SB_JL_PLKKSPZB_LW b");
		    buffer.append(" where a.sphm=b.sphm ");
		    if(form.getNd()!=null&&form.getNd().trim().length()>0){
		    	 buffer.append(" and a.nd = ? ");
		    }
		    if(form.getYstart()!=null&&form.getYstart().trim().length()>0){
		    	 buffer.append(" and a.yd >= ? ");
		    }
		    if(form.getYend()!=null&&form.getYend().trim().length()>0){
		    	 buffer.append(" and a.yd <= ? ");
		    }
		    
		    if(form.getDqjs()!=null&&form.getDqjs().trim().length()>0){
		    	 buffer.append(" and a.qxdm = ? ");
		    }
		    
		    if(form.getCxdqjs()!=null&&form.getCxdqjs().trim().length()>0){
		    	 buffer.append(" and b.swjgzzjgdm = ? ");
		    }
		    
		    if(form.getYhdm()!=null&&form.getYhdm().trim().length()>0){
		    	 buffer.append(" and a.yhdm = ? ");
		    }
		    
		    if(form.getCgsbFlag()!=null&&form.getCgsbFlag().trim().length()>0){
		    	 buffer.append(" and a.kkbz = ? ");
		    }
		    
		    
		    if(form.getFjdmxx()!=null&&form.getFjdmxx().trim().length()>0){
		    	 buffer.append(" and a.qxdm = ? ");
		    }
		    
		    if(form.getSwsdmxx()!=null&&form.getSwsdmxx().trim().length()>0){
		    	 buffer.append(" and b.swjgzzjgdm = ? ");
		    }
		    
		    if(form.getYhdmxx()!=null&&form.getYhdmxx().trim().length()>0){
		    	 buffer.append(" and a.yhdm = ? ");
		    }
		    if(form.getKksjxx()!=null&&form.getKksjxx().trim().length()>0){
		    	 buffer.append(" and a.kkrq = to_date(?,'yyyy-mm-dd') ");
		    }
		    
		    buffer.append(") aa ) bb ");
		    buffer.append(" where bb.num >= ? and bb.num <= ?");
		    //buffer.append(" where bb.num >= ? and bb.num <= ?");
		    
		    
		    
		    //单户查询纳税信息
		    /*
		    System.out.println("doQueryDetail################buffer.toString(="+buffer.toString());
		    
		    System.out.println("doQueryDetail################form.getNd()="+form.getNd());
		    System.out.println("doQueryDetail################form.getYstart()="+form.getYstart());
		    System.out.println("doQueryDetail################form.getYend()="+form.getYend());
		    System.out.println("doQueryDetail################form.getDqjs()="+form.getDqjs());
		    if(form.getDqjs()!=null&&form.getDqjs().length()>0){
		    System.out.println("doQueryDetail################form.getDqjs().substring(0,2)="+form.getDqjs().substring(0,2));
		    }
		    System.out.println("doQueryDetail################1form.getCxdqjs()="+form.getCxdqjs());
		    System.out.println("doQueryDetail################form.getYhdm()="+form.getYhdm());
		    
		    System.out.println("doQueryDetail################form.getCgsbFlag()="+form.getCgsbFlag());
		    System.out.println("doQueryDetail################form.getFjdmxx()="+form.getFjdmxx());
		    System.out.println("doQueryDetail################form.getSwsdmxx()="+form.getSwsdmxx());
		    System.out.println("doQueryDetail################form.getYhdmxx()="+form.getYhdmxx());
		    System.out.println("doQueryDetail################form.getKksjxx()="+form.getKksjxx());
		    */
		    
		    ps = conn.prepareStatement(buffer.toString());
		    i=1;
		    if(form.getNd()!=null&&form.getNd().trim().length()>0){
		    	ps.setString(i, form.getNd().trim());
		    	i++;
		    }
		    if(form.getYstart()!=null&&form.getYstart().trim().length()>0){
		    	ps.setString(i, form.getYstart().trim());
		    	 i++;
		    }
		    
		    if(form.getYend()!=null&&form.getYend().trim().length()>0){
		    	ps.setString(i, form.getYend().trim());
		    	 i++;
		    }
		    
		    if(form.getDqjs()!=null&&form.getDqjs().trim().length()>0){
		    	ps.setString(i, form.getDqjs().trim().substring(0,2));
		    	 i++;
		    }
		    
		    if(form.getCxdqjs()!=null&&form.getCxdqjs().trim().length()>0){
		    	ps.setString(i, form.getCxdqjs().trim());
		    	 i++;
		    }
		    
		    if(form.getYhdm()!=null&&form.getYhdm().trim().length()>0){
		    	ps.setString(i, form.getYhdm().trim());
		    	 i++;
		    }
		    
		    if(form.getCgsbFlag()!=null&&form.getCgsbFlag().trim().length()>0){
		    	ps.setString(i, form.getCgsbFlag().trim());
		    	 i++;
		    }
		    
		    if(form.getFjdmxx()!=null&&form.getFjdmxx().trim().length()>0){
		    	ps.setString(i, form.getFjdmxx().trim());
		    	 i++;
		    }
		    
		    if(form.getSwsdmxx()!=null&&form.getSwsdmxx().trim().length()>0){
		    	ps.setString(i, form.getSwsdmxx().trim());
		    	 i++;
		    }
		    
		    if(form.getYhdmxx()!=null&&form.getYhdmxx().trim().length()>0){
		    	ps.setString(i, form.getYhdmxx().trim());
		    	 i++;
		    }
		    if(form.getKksjxx()!=null&&form.getKksjxx().trim().length()>0){
		    	ps.setString(i, form.getKksjxx().trim());
		    	 i++;
		    }
		    
		    // ps.setInt(i++, startNum);
	    	// ps.setInt(i++, endNum);
		    ps.setInt(i++, startNum);
	    	ps.setInt(i++, endNum);
	    	
		    ResultSet rst = ps.executeQuery();
		    
		    List list = new ArrayList();
		  
		    while(rst.next()){
		    	HashMap map = new HashMap();
                map.put("jsjdm", rst.getString("jsjdm")==null?"":rst.getString("jsjdm"));
                map.put("nsrmc", rst.getString("nsrmc")==null?"":rst.getString("nsrmc"));
                map.put("xm", rst.getString("xm")==null?"":rst.getString("xm"));
                map.put("gddh", rst.getString("gddh")==null?"":rst.getString("gddh"));
                map.put("yddh", rst.getString("yddh")==null?"":rst.getString("yddh"));
                map.put("zcdz", rst.getString("zcdz")==null?"":rst.getString("zcdz"));
                map.put("jydz", rst.getString("jydz")==null?"":rst.getString("jydz"));
                map.put("sphm", rst.getString("sphm")==null?"":rst.getString("sphm"));
                map.put("szmc", rst.getString("szmc")==null?"":rst.getString("szmc"));
                map.put("sjje", rst.getString("sjje")==null?"0.00":rst.getString("sjje"));
                
                String kksj="";
		    	SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
    			try {
    				if(rst.getTimestamp("kkrq")!=null){
    					kksj= df1.format(rst.getTimestamp("kkrq"));
    				}
    			}
    			catch (Exception ex) {
    				ex.printStackTrace();
    			} 
                
                map.put("kkrq", kksj);
                map.put("kkjg", rst.getString("kkjg")==null?"":rst.getString("kkjg"));
                map.put("yhkkjgms", rst.getString("yhkkjgms")==null?"":rst.getString("yhkkjgms"));
                map.put("startNum", Integer.toString(startNum));
                startNum++;
		        list.add(map);
		    }
		    rst.close();
		    
		    form.setDataList(list);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
   	     SfDBResource.freeConnection(conn);
	    }
		return form;
	}
	
	
	private Object doExport(VOPackage vo) {
		//获取form
		PlkscxForm form = (PlkscxForm) vo.getData();
		//基本数据model
		PKJBSJModel jbsjModel = new PKJBSJModel();
		//获取jsjdm
		//String jsjdm = form.getJsjdm();
		//连接数据库
		PreparedStatement ps = null;
	    Connection conn = null;
	     //获取基本数据sql
	     
	     try {
		     //获取连接
		      StringBuffer buffer = new StringBuffer();
		    buffer.append("select bb.* from (select rownum num,aa.* from (");
		    buffer.append("select (select YHMC from dmdb.gy_dm_yh where YHDM=a.yhdm) yh,");
		    buffer.append("(select swjgzzjgmc from dmdb.gy_dm_swjgzzjg where swjgzzjgdm=a.qxdm||'00' ) fj,");
		    buffer.append("(select swjgzzjgmc from dmdb.gy_dm_swjgzzjg where swjgzzjgdm=b.swjgzzjgdm ) sws,a.kkrq,");
		    buffer.append("count(distinct decode(a.kkbz, '20', a.sphm)) cgbs,");
		    buffer.append("count(distinct decode(a.kkbz, '21', a.sphm)) sbbs,");
		    buffer.append("count(distinct a.sphm) zbs,");
		    buffer.append("count(distinct decode(a.kkbz, '20', a.sphm))/count(distinct a.sphm)*100 bscgl,");
		    buffer.append("sum(decode(a.kkbz, '20', b.sjje)) cgje,");
		    buffer.append("sum(decode(a.kkbz, '21', b.sjje)) sbje,");
		    buffer.append("sum(b.sjje) zje,");
		    buffer.append("sum(decode(a.kkbz, '20', b.sjje))/sum(b.sjje)*100 jecgl");
		    buffer.append(" from sbdb.sb_jl_plkkspxx_lw a,SBDB.SB_JL_PLKKSPZB_LW b");
		    buffer.append(" where a.sphm=b.sphm ");
		    if(form.getNd()!=null&&form.getNd().trim().length()>0){
		    	 buffer.append(" and a.nd = ? ");
		    }
		    if(form.getYstart()!=null&&form.getYstart().trim().length()>0){
		    	 buffer.append(" and a.yd >= ? ");
		    }
		    if(form.getYend()!=null&&form.getYend().trim().length()>0){
		    	 buffer.append(" and a.yd <= ? ");
		    }
		    
		    if(form.getDqjs()!=null&&form.getDqjs().trim().length()>0){
		    	 buffer.append(" and a.qxdm = ? ");
		    }
		    
		    if(form.getCxdqjs()!=null&&form.getCxdqjs().trim().length()>0){
		    	 buffer.append(" and b.swjgzzjgdm = ? ");
		    }
		    
		    if(form.getYhdm()!=null&&form.getYhdm().trim().length()>0){
		    	 buffer.append(" and a.yhdm = ? ");
		    }
		    buffer.append(" group by a.yhdm, a.qxdm,b.swjgzzjgdm,a.kkrq ");
		    
		    buffer.append(") aa ) bb ");
		    
		    //单户查询纳税信息
		    System.out.println("doExport################buffer.toString(="+buffer.toString());
		    
		    System.out.println("################form.getNd()="+form.getNd());
		    System.out.println("################form.getYstart()="+form.getYstart());
		    System.out.println("################form.getYend()="+form.getYend());
		    System.out.println("################form.getDqjs()="+form.getDqjs());
		    if(form.getDqjs()!=null&&form.getDqjs().length()>0){
		    System.out.println("################form.getDqjs().substring(0,2)="+form.getDqjs().substring(0,2));
		    }
		    System.out.println("################1form.getCxdqjs()="+form.getCxdqjs());
		    System.out.println("################form.getYhdm()="+form.getYhdm());
		    conn = SfDBResource.getConnection();
			
		    ps = conn.prepareStatement(buffer.toString());
		    int i=1;
		    if(form.getNd()!=null&&form.getNd().trim().length()>0){
		    	ps.setString(i, form.getNd().trim());
		    	i++;
		    }
		    if(form.getYstart()!=null&&form.getYstart().trim().length()>0){
		    	ps.setString(i, form.getYstart().trim());
		    	 i++;
		    }
		    
		    if(form.getYend()!=null&&form.getYend().trim().length()>0){
		    	ps.setString(i, form.getYend().trim());
		    	 i++;
		    }
		    
		    if(form.getDqjs()!=null&&form.getDqjs().trim().length()>0){
		    	ps.setString(i, form.getDqjs().trim().substring(0,2));
		    	 i++;
		    }
		    
		    if(form.getCxdqjs()!=null&&form.getCxdqjs().trim().length()>0){
		    	ps.setString(i, form.getCxdqjs().trim());
		    	 i++;
		    }
		    
		    if(form.getYhdm()!=null&&form.getYhdm().trim().length()>0){
		    	ps.setString(i, form.getYhdm().trim());
		    	 i++;
		    }
		    
		    ResultSet rst = ps.executeQuery();
		    System.out.println("doExport################buffer.toString=test1");
		    List list = new ArrayList();
		    //合计金额
		    double hjje = 0; 
		    //合计笔数
		    long hjts = 0; 
		    int startNum=1;
		    while(rst.next()){
		    	System.out.println("doExport################startNum="+startNum);
		    	PLKKPLCXModel plkkplcxmodel = new PLKKPLCXModel();
		    	String yh=rst.getString("yh");
		    	if(yh==null){
                  yh="";
		    	}
		    	plkkplcxmodel.setYh(yh);
		    	
		    	String fj=rst.getString("fj");
		    	if(fj==null){
		    		fj="";
		    	}
		    	plkkplcxmodel.setFj(fj);
		    	
		    	String sws=rst.getString("sws");
		    	if(sws==null){
		    		sws="";
		    	}
		    	plkkplcxmodel.setSws(sws);
		    	
		    	String kksj="";
		    	SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
    			try {
    				if(rst.getTimestamp("kkrq")!=null){
    					kksj= df1.format(rst.getTimestamp("kkrq"));
    				}
    			}
    			catch (Exception ex) {
    				ex.printStackTrace();
    			} 
    			plkkplcxmodel.setKksj(kksj);
    			
    			String cgbs=rst.getString("cgbs");
		    	if(cgbs==null){
		    		cgbs="";
		    	}
		    	plkkplcxmodel.setCgbs(cgbs);
		    	
		    	String sbbs=rst.getString("sbbs");
		    	if(sbbs==null){
		    		sbbs="";
		    	}
		    	plkkplcxmodel.setSbbs(sbbs);
		    	
		    	String zbs=rst.getString("zbs");
		    	if(zbs==null){
		    		zbs="0";
		    	}
		    	
		    	hjts=hjts+Long.parseLong(zbs);
		    	
		    	plkkplcxmodel.setZbs(zbs);
		    	
		    	String bscgl="";
		    	if(rst.getBigDecimal("bscgl")==null){
		    		bscgl="0.0%";
		    	}else{
		    		
		    		double   f_bscgl   =   rst.getBigDecimal("bscgl").setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
		    		bscgl=String
					.valueOf(f_bscgl)+"%";
		    		
		    	}
		    	plkkplcxmodel.setBscgl(bscgl);
		    	
		    	String cgje=rst.getString("cgje");
		    	if(cgje==null){
		    		cgje="0.00";
		    	}
		    	plkkplcxmodel.setCgje(cgje);
		    	
		    	String sbje=rst.getString("sbje");
		    	if(sbje==null){
		    		sbje="0.00";
		    	}
		    	
		    	
		    	
		    	plkkplcxmodel.setSbje(sbje);
		    	
		    	String zje=rst.getString("zje");
		    	if(zje==null){
		    		zje="0.00";
		    	}
		    	
		    	hjje = hjje
                + StringUtil.getDouble(zje,
                                       0);
		    	
		    	plkkplcxmodel.setZje(zje);
		    	
		    	String jecgl="";
		    	if(rst.getBigDecimal("jecgl")==null){
		    		jecgl="0.0%";
		    	}else{
		    		double   f_jecgl   =   rst.getBigDecimal("jecgl").setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
		    		jecgl=String
					.valueOf(f_jecgl)+"%";
		    	}
		    	
		    	plkkplcxmodel.setJecgl(jecgl);
		    	plkkplcxmodel.setRownum(startNum);
				startNum++;
		        list.add(plkkplcxmodel);
		    }
		    rst.close();
		    
		    String tempZje=StringUtil.getCurrency(String.valueOf(hjje));
		    tempZje=tempZje.replaceAll("￥", "");
		    tempZje=tempZje.replaceAll(",", "");
		    form.setZje(tempZje);
		    form.setZbs(new Long(hjts).toString());
		    form.setDataList(list);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
   	     SfDBResource.freeConnection(conn);
	    }
		return form;
	}
	/**
	 * 获取银行信息list
	 */
	private Object getYhlist(VOPackage vo) throws BaseException {
		Connection conn = null;
		PlkscxForm theForm = (PlkscxForm) vo.getData();
		Connection con = null;
		ResultSet rs = null;
		String sql = "";
		PreparedStatement cstmt = null;
              //  UserData userdata = (UserData) vo.getUserData();
		try {

			yhdmmodel model1 = null;

			List list = new ArrayList();
			con = SfDBResource.getConnection();
			String gxswjgzzjgdm = theForm.getDqjs();
			// 根据登录用户的信息获取登陆的居所的信息
		    sql = "select b.yhdm,b.yhmc from dmdb.gy_dm_yh b where b.yhdm in ('05','16') ";
			cstmt = con.prepareStatement(sql);
			rs = cstmt.executeQuery();
			while (rs.next()) {
				model1 = new yhdmmodel();
				model1.setYhdm(rs.getString(1));
				model1.setYhmc(rs.getString(2));
				list.add(model1);
			}
			theForm.setYhlist(list);
			if (rs != null) {
				rs.close();
			}
			if (cstmt != null) {
				cstmt.close();
			}

			return theForm;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception exx) {
			}
			try {
				if (cstmt != null) {
					cstmt.close();
				}
			} catch (Exception exx) {
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (Exception exx) {
			}
		}

	}
	/**
	 * 获取分局数据list
	 */
	private Object dogetdqjslist(VOPackage vo) throws BaseException {
		Connection conn = null;
		PlkscxForm theForm = (PlkscxForm) vo.getData();
		Connection con = null;
		ResultSet rs = null;
		String sql = "";
		PreparedStatement cstmt = null;
		UserData userdata = (UserData) vo.getUserData();
		try {
			swdwmodel model = null;
			swdwmodel model1 = null;
			List list = new ArrayList();
			con = SfDBResource.getConnection();
			String gxswjgzzjgdm = userdata.getSsdwdm();
			// 根据登录用户的信息获取登陆的居所的信息
			//gxswjgzzjgdm="0604";
			if ("90".equals(gxswjgzzjgdm.substring(0, 2))) {
				// 查的是搜有分局
				sql = "select b.swjgzzjgdm,b.swjgzzjgmc from dmdb.gy_dm_swjgzzjg b where b.swjgzzjgdm like '%00' "
						+ " and b.swjgzzjgdm!='9000' order by b.swjgzzjgdm ";
				cstmt = con.prepareStatement(sql);
				rs = cstmt.executeQuery();
				while (rs.next()) {
					model = new swdwmodel();
					model.setSwdwid(rs.getString(1));
					model.setSwdwmc(rs.getString(2));
					list.add(model);
				}
				theForm.setSwdwlist(list);
				return theForm;
			} else {
				sql = "select b.swjgzzjgdm,b.swjgzzjgmc from dmdb.gy_dm_swjgzzjg b where b.swjgzzjgdm='"
						+ gxswjgzzjgdm.substring(0, 2) + "00' order by b.swjgzzjgdm ";
				cstmt = con.prepareStatement(sql);
				rs = cstmt.executeQuery();
				//System.out.println(sql + "-------------查登陆税务机关的sql");
				while (rs.next()) {
					model = new swdwmodel();
					model.setSwdwid(rs.getString(1));
					model.setSwdwmc(rs.getString(2));
					list.add(model);
				}
				theForm.setSwdwlist(list);
				return theForm;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception exx) {
			}
			try {
				if (cstmt != null) {
					cstmt.close();
				}
			} catch (Exception exx) {
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (Exception exx) {
			}
		}

	}
	
	/**
	 * 获取税务所信息list
	 */
	private Object dogetcxdqjslist(VOPackage vo) throws BaseException {
		Connection conn = null;
		PlkscxForm theForm = (PlkscxForm) vo.getData();
		Connection con = null;
		ResultSet rs = null;
		String sql = "";
		PreparedStatement cstmt = null;
                UserData userdata = (UserData) vo.getUserData();
		try {

			swdwmodel model1 = null;

			List list1 = new ArrayList();
			con = SfDBResource.getConnection();
			String gxswjgzzjgdm = theForm.getDqjs();
			// 根据登录用户的信息获取登陆的居所的信息
			if ("30".equals(userdata.yhjb)) {
				sql = "select b.swjgzzjgdm,b.swjgzzjgmc from dmdb.gy_dm_swjgzzjg b where "
						+ "  b.swjgzzjgdm='" + userdata.getSsdwdm()+ "' order by b.swjgzzjgdm ";
			} else {
				// 根据分局查税务所
				sql = "select b.swjgzzjgdm,b.swjgzzjgmc from dmdb.gy_dm_swjgzzjg b where b.swjgzzjgdm like '"
						+ gxswjgzzjgdm.substring(0, 2)
						+ "%' "
						+ " and b.swjgzzjgdm!='" + gxswjgzzjgdm + "' order by b.swjgzzjgdm ";
			}
			
			System.out.println("###########sql="+sql);
			
			cstmt = con.prepareStatement(sql);
			rs = cstmt.executeQuery();
			while (rs.next()) {
				model1 = new swdwmodel();
				model1.setSwdwid(rs.getString(1));
				model1.setSwdwmc(rs.getString(2));
				list1.add(model1);
			}
			theForm.setCxswdwlist(list1);
			if (rs != null) {
				rs.close();
			}
			if (cstmt != null) {
				cstmt.close();
			}

			return theForm;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception exx) {
			}
			try {
				if (cstmt != null) {
					cstmt.close();
				}
			} catch (Exception exx) {
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (Exception exx) {
			}
		}

	}
	private Object doExportDetail(VOPackage vo) {
		//获取form
		PlkscxForm form = (PlkscxForm) vo.getData();
		//基本数据model
		PKJBSJModel jbsjModel = new PKJBSJModel();
		//获取jsjdm
		//String jsjdm = form.getJsjdm();
		//连接数据库
		PreparedStatement ps = null;
	    Connection conn = null;
	     //获取基本数据sql
	     
	     try {
	    	 //conn = SfDBResource.getConnection();
	    	 
	    	 int count=0;//查询总记录条数
	    	 int currentPage = form.getCurrentPageXx();
		     int startNum = (currentPage-1)*(form.getPageSizeXx())+1;
		     int endNum = currentPage*(form.getPageSizeXx());
		     //System.out.println("doQueryDetail################startNum="+startNum);
		    // System.out.println("doQueryDetail################endNum="+endNum);
		     
		     //获取连接
		      conn = SfDBResource.getConnection();
		     
		       StringBuffer buffer = new StringBuffer();
		 
	    	
			
	    	buffer = new StringBuffer();
		    buffer.append("select bb.* from (select rownum num,aa.* from (");
		    buffer.append("select (select c.jsjdm from djdb.dj_jl_jbsj c, djdb.dj_jl_qyry d where c.jsjdm = d.jsjdm and d.zwdm = '01' and c.jsjdm=a.jsjdm ) jsjdm,");
		    buffer.append("(select c.nsrmc from djdb.dj_jl_jbsj c, djdb.dj_jl_qyry d where c.jsjdm = d.jsjdm and d.zwdm = '01' and c.jsjdm=a.jsjdm ) nsrmc,");
		    buffer.append("(select d.xm from djdb.dj_jl_jbsj c, djdb.dj_jl_qyry d where c.jsjdm = d.jsjdm and d.zwdm = '01' and c.jsjdm=a.jsjdm ) xm,");
		    buffer.append("(select d.gddh from djdb.dj_jl_jbsj c, djdb.dj_jl_qyry d where c.jsjdm = d.jsjdm and d.zwdm = '01' and c.jsjdm=a.jsjdm ) gddh,");
		    buffer.append("(select d.yddh from djdb.dj_jl_jbsj c, djdb.dj_jl_qyry d where c.jsjdm = d.jsjdm and d.zwdm = '01' and c.jsjdm=a.jsjdm ) yddh,");
		    buffer.append("(select c.zcdz from djdb.dj_jl_jbsj c, djdb.dj_jl_qyry d where c.jsjdm = d.jsjdm and d.zwdm = '01' and c.jsjdm=a.jsjdm ) zcdz,");
		    buffer.append("(select c.jydz from djdb.dj_jl_jbsj c, djdb.dj_jl_qyry d where c.jsjdm = d.jsjdm and d.zwdm = '01' and c.jsjdm=a.jsjdm ) jydz,");
 
		    buffer.append("a.sphm,");
		    buffer.append("(select SZSMMC from DMDB.SB_DM_SZSM where SZSMDM=b.szdm) szmc,");
		    buffer.append("b.sjje,");
		    buffer.append("a.kkrq,");
		    buffer.append("(decode(a.kkbz,'00','未扣款','10','扣款中','20','扣款成功','21','扣款失败','其他')) kkjg,");
		    buffer.append("a.yhkkjgms");
		    buffer.append(" from sbdb.sb_jl_plkkspxx_lw a,SBDB.SB_JL_PLKKSPZB_LW b");
		    buffer.append(" where a.sphm=b.sphm ");
		    if(form.getNd()!=null&&form.getNd().trim().length()>0){
		    	 buffer.append(" and a.nd = ? ");
		    }
		    if(form.getYstart()!=null&&form.getYstart().trim().length()>0){
		    	 buffer.append(" and a.yd >= ? ");
		    }
		    if(form.getYend()!=null&&form.getYend().trim().length()>0){
		    	 buffer.append(" and a.yd <= ? ");
		    }
		    
		    if(form.getDqjs()!=null&&form.getDqjs().trim().length()>0){
		    	 buffer.append(" and a.qxdm = ? ");
		    }
		    
		    if(form.getCxdqjs()!=null&&form.getCxdqjs().trim().length()>0){
		    	 buffer.append(" and b.swjgzzjgdm = ? ");
		    }
		    
		    if(form.getYhdm()!=null&&form.getYhdm().trim().length()>0){
		    	 buffer.append(" and a.yhdm = ? ");
		    }
		    
		    if(form.getCgsbFlag()!=null&&form.getCgsbFlag().trim().length()>0){
		    	 buffer.append(" and a.kkbz = ? ");
		    }
		    
		    
		    if(form.getFjdmxx()!=null&&form.getFjdmxx().trim().length()>0){
		    	 buffer.append(" and a.qxdm = ? ");
		    }
		    
		    if(form.getSwsdmxx()!=null&&form.getSwsdmxx().trim().length()>0){
		    	 buffer.append(" and b.swjgzzjgdm = ? ");
		    }
		    
		    if(form.getYhdmxx()!=null&&form.getYhdmxx().trim().length()>0){
		    	 buffer.append(" and a.yhdm = ? ");
		    }
		    if(form.getKksjxx()!=null&&form.getKksjxx().trim().length()>0){
		    	 buffer.append(" and a.kkrq = to_date(?,'yyyy-mm-dd') ");
		    }
		    
		    buffer.append(") aa ) bb ");
		    //buffer.append(" where bb.num >= ? and bb.num <= ?");
		    ps = conn.prepareStatement(buffer.toString());
		    int i=1;
		    if(form.getNd()!=null&&form.getNd().trim().length()>0){
		    	ps.setString(i, form.getNd().trim());
		    	i++;
		    }
		    if(form.getYstart()!=null&&form.getYstart().trim().length()>0){
		    	ps.setString(i, form.getYstart().trim());
		    	 i++;
		    }
		    
		    if(form.getYend()!=null&&form.getYend().trim().length()>0){
		    	ps.setString(i, form.getYend().trim());
		    	 i++;
		    }
		    
		    if(form.getDqjs()!=null&&form.getDqjs().trim().length()>0){
		    	ps.setString(i, form.getDqjs().trim().substring(0,2));
		    	 i++;
		    }
		    
		    if(form.getCxdqjs()!=null&&form.getCxdqjs().trim().length()>0){
		    	ps.setString(i, form.getCxdqjs().trim());
		    	 i++;
		    }
		    
		    if(form.getYhdm()!=null&&form.getYhdm().trim().length()>0){
		    	ps.setString(i, form.getYhdm().trim());
		    	 i++;
		    }
		    
		    if(form.getCgsbFlag()!=null&&form.getCgsbFlag().trim().length()>0){
		    	ps.setString(i, form.getCgsbFlag().trim());
		    	 i++;
		    }
		    
		    if(form.getFjdmxx()!=null&&form.getFjdmxx().trim().length()>0){
		    	ps.setString(i, form.getFjdmxx().trim());
		    	 i++;
		    }
		    
		    if(form.getSwsdmxx()!=null&&form.getSwsdmxx().trim().length()>0){
		    	ps.setString(i, form.getSwsdmxx().trim());
		    	 i++;
		    }
		    
		    if(form.getYhdmxx()!=null&&form.getYhdmxx().trim().length()>0){
		    	ps.setString(i, form.getYhdmxx().trim());
		    	 i++;
		    }
		    if(form.getKksjxx()!=null&&form.getKksjxx().trim().length()>0){
		    	ps.setString(i, form.getKksjxx().trim());
		    	 i++;
		    }
		    
	
	    	
		    ResultSet rst = ps.executeQuery();
		    
		    List list = new ArrayList();
		  
		    while(rst.next()){
		    	HashMap map = new HashMap();
                map.put("jsjdm", rst.getString("jsjdm")==null?"":rst.getString("jsjdm"));
                map.put("nsrmc", rst.getString("nsrmc")==null?"":rst.getString("nsrmc"));
                map.put("xm", rst.getString("xm")==null?"":rst.getString("xm"));
                map.put("gddh", rst.getString("gddh")==null?"":rst.getString("gddh"));
                map.put("yddh", rst.getString("yddh")==null?"":rst.getString("yddh"));
                map.put("zcdz", rst.getString("zcdz")==null?"":rst.getString("zcdz"));
                map.put("jydz", rst.getString("jydz")==null?"":rst.getString("jydz"));
                map.put("sphm", rst.getString("sphm")==null?"":rst.getString("sphm"));
                map.put("szmc", rst.getString("szmc")==null?"":rst.getString("szmc"));
                map.put("sjje", rst.getString("sjje")==null?"0.00":rst.getString("sjje"));
                
                String kksj="";
		    	SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
    			try {
    				if(rst.getTimestamp("kkrq")!=null){
    					kksj= df1.format(rst.getTimestamp("kkrq"));
    				}
    			}
    			catch (Exception ex) {
    				ex.printStackTrace();
    			} 
                
                map.put("kkrq", kksj);
                map.put("kkjg", rst.getString("kkjg")==null?"":rst.getString("kkjg"));
                map.put("yhkkjgms", rst.getString("yhkkjgms")==null?"":rst.getString("yhkkjgms"));
                map.put("startNum", Integer.toString(startNum));
                startNum++;
		        list.add(map);
		    }
		    rst.close();
		    form.setDataList(list);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
   	     SfDBResource.freeConnection(conn);
	    }
		return form;
	}
	
	
}
