package com.ttsoft.bjtax.smsb.lwpk.processor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.lwpk.model.PKJBSJModel;
import com.ttsoft.bjtax.smsb.lwpk.model.PLKKDHCXModel;
import com.ttsoft.bjtax.smsb.lwpk.web.DhkscxForm;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.SystemException;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 *单户查询processor
 *kanght 
 * 201307
 */
public class DhkscxProcessor implements Processor{

	public Object process(VOPackage vo) throws BaseException {
		
		 Object result = null;
		 if (vo == null) {
		      throw new NullPointerException();
		    }

		    switch (vo.getAction()) {
		      case CodeConstant.SMSB_QUERYACTION:
		        result = doQuery(vo);
		        break;
		      default:
		        throw new UnsupportedOperationException(
		            "Method process() not yet implemented.");
		    }
		    return result;
	}

	private Object doQuery(VOPackage vo) throws BaseException{
		//获取form
		DhkscxForm form = (DhkscxForm) vo.getData();
		//基本数据model
		PKJBSJModel jbsjModel = new PKJBSJModel();
		//获取jsjdm
		String jsjdm = form.getJsjdm();
		//连接数据库
		 PreparedStatement ps = null;
	     Connection conn = null;
	     //结果集 
	     ResultSet rst = null;
	     //获取基本数据sql
	     String sql = "select a.jsjdm, a.nsrmc, b.xm, b.gddh, b.yddh, a.zcdz, a.zcdzlxdh, a.jydz, a.jydzlxdm " +
	     		"from djdb.dj_jl_jbsj a, djdb.dj_jl_qyry b where a.jsjdm = b.jsjdm and b.zwdm = '01' " +
	     		"and a.jsjdm=?";
	     try {
	    	 //获取连接
			conn = SfDBResource.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, jsjdm);
		    //查询
		    rst = ps.executeQuery();
		    if(rst.next()){
		    	//固定电话
		    	jbsjModel.setGddh(rst.getString("gddh"));
		    	//纳税人名称
		    	jbsjModel.setNsrmc(rst.getString("nsrmc"));
		    	//移动电话
		    	jbsjModel.setYddh(rst.getString("yddh"));
		    	//注册地址
		    	jbsjModel.setZcdz(rst.getString("zcdz"));
		    	//经营地址
		    	jbsjModel.setJydz(rst.getString("jydz"));
		    	//法定代表人--责任人
		    	jbsjModel.setZrr(rst.getString("xm"));
		    	//基本数据
			    form.setPkjbsjModel(jbsjModel);
		    }else{
		    	throw new ApplicationException("纳税人基本信息为空！");	
		    }
		    //关闭结果集
		    rst.close();
		    //关闭preparestatement
		    ps.close();
		  
		    
		    StringBuffer sql_zts = new StringBuffer();
		    sql_zts.append(" select a.sphm, b.szdm, a.kkrq, a.yhkkjgms, a.kkbz, sum(b.sjje) sjje");
		    sql_zts.append(" from sbdb.sb_jl_plkkspxx_lw a, sbdb.sb_jl_plkkspzb_lw b");
		    sql_zts.append(" where a.sphm = b.sphm");
		    sql_zts.append(" and a.jsjdm = ?");
		    sql_zts.append(" and a.nd = ?");
		    sql_zts.append(" and a.yd >= ?");
		    sql_zts.append(" and a.yd <= ?");
		    sql_zts.append(" group by a.sphm, b.szdm, a.kkrq, a.yhkkjgms,a.kkbz");
		    //总条数
		    int zts=0;
		    //执行查询
		    ps = conn.prepareStatement(sql_zts.toString());
		    //计算机代码
		    ps.setString(1, jsjdm);
		    //年度
		    ps.setString(2, form.getNd());
		    //扣款起始月份
		    ps.setString(3, form.getKkqsyf());
		     //扣款终止月份
		    ps.setString(4, form.getKkzzyf());
		    
		    rst = ps.executeQuery();
		    while(rst.next()){
		    	//总条数
		    	zts++;
		    }
		    //关闭resultset
		    rst.close();
		    //关闭preparestatement
		    ps.close();
		    //如果总条数为0
		    if(zts<=0){
		    	throw new ApplicationException("未查到符合条件的扣款信息！");			
		    }
		    //设置总页数
		    form.setTotalpage(String.valueOf(gettotalpage(zts)).trim());
		    //试着总条数
		    form.setZts(zts);
		  //开始数据的id
			int start = (Integer.parseInt(form.getNextPage())-1)*Integer.parseInt(form.getPageSize());
			//结束数据的id
			int end =(Integer.parseInt(form.getNextPage()))*Integer.parseInt(form.getPageSize());
		    
			StringBuffer buffer = new StringBuffer();
			buffer.append("select * from (");
			buffer.append(" select a.sphm, b.szdm, a.kkrq, a.yhkkjgms, a.kkbz, sum(b.sjje) sjje, rownum num ");
			buffer.append(" from sbdb.sb_jl_plkkspxx_lw a, sbdb.sb_jl_plkkspzb_lw b ");
			buffer.append(" where a.sphm = b.sphm ");
			buffer.append(" and a.jsjdm = ?");
			buffer.append(" and a.nd = ?");
			buffer.append(" and a.yd >= ?");
			buffer.append(" and a.yd <= ?");
			buffer.append(" group by a.sphm, b.szdm, a.kkrq, a.yhkkjgms,a.kkbz,rownum) dh");
			buffer.append(" where dh.num>? and dh.num <=?");
		
		    //单户查询纳税信息
		    ps = conn.prepareStatement(buffer.toString());
		    //计算机代码
		    ps.setString(1, jsjdm);
		    //年度
		    ps.setString(2, form.getNd());
		    //扣款起始月份
		    ps.setString(3, form.getKkqsyf());
		    //扣款终止月份
		    ps.setString(4, form.getKkzzyf());
		    //查询起始条数
		    ps.setInt(5, start);
		    //查询终止条数
		    ps.setInt(6, end);
		    
		    //获取扣款标示名称map
		    Map kkbzmcMap = getkkbzmc();
		    //获取税种名称map
		    Map szmcMap = getszmc();
		    //执行查询
		    rst = ps.executeQuery();
		    List list = new ArrayList();
		    while(rst.next()){
		    	 PLKKDHCXModel plkkdhcxModel = new PLKKDHCXModel();
		    	 //扣款失败原因
		    	 plkkdhcxModel.setKksbyy(rst.getString("yhkkjgms"));
		    	 //扣款时间
		    	 plkkdhcxModel.setKksj(rst.getDate("kkrq"));
		    	 //扣款状态
		    	 String kkbz = rst.getString("kkbz");
		    	 plkkdhcxModel.setKkzt((String)kkbzmcMap.get(kkbz.trim()));
		    	 //实缴金额
		    	 plkkdhcxModel.setSjje(rst.getBigDecimal("sjje"));
		    	 //税票号码
		    	 plkkdhcxModel.setSphm(rst.getString("sphm"));
		    	 //税种名称
		    	 plkkdhcxModel.setSzmc((String) szmcMap.get(rst.getString("szdm")));
		    	 //将显示数据对象放在list
		    	 list.add(plkkdhcxModel);
		    }
		    //关闭结果集
		    rst.close();
		    ps.close();
		   //放置到form中--当前页要显示的数据
		    form.setPlkkdhcxlist(list);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
   	     SfDBResource.freeConnection(conn);
	       }
	 
		return form;
	}
	
	/**
	 *扣款标识 代码转换成名称
	 * 
	 */
	public Map getkkbzmc(){
		Map map = new HashMap();
		map.put("00", "未扣款");
		map.put("10", "扣款中");
		map.put("20", "扣款成功");
		map.put("21", "扣款失败");
		return map;
	}
	
	/**
	 *税种名称与代码转换 
	 * 
	 */
	public Map getszmc(){
		Map map = new HashMap();
		 PreparedStatement ps = null;
	     Connection conn = null;
	     //获取基本数据sql
	     String sql = "SELECT SZSMDM,SZSMMC FROM DMDB.SB_DM_SZSM";
	    	 //获取连接
			try {
				conn = SfDBResource.getConnection();
				ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
					map.put(rs.getString("SZSMDM"), rs.getString("SZSMMC"));
				}
				
			} catch (SystemException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		   
		    
			return map;
	}

	
	/**
	 *获取总页数 
	 * 
	 * ts 结果集条数
	 */
	public int gettotalpage(int ts ){
		//总页数
		int totalpage = 0;
		//如果条数能够被整除
		if(ts % CodeConstant.SMSB_PK_PG_LENGTH == 0){
			//总页数 = 条数/每页显示的条数 
			totalpage = ts / CodeConstant.SMSB_PK_PG_LENGTH ;
			//如果条数不能被整除
		}else{
			//总页数 = 条数/每页显示的条数 + 1
			totalpage = ts / CodeConstant.SMSB_PK_PG_LENGTH + 1;
		}
		return totalpage;
	}
	

	
	
}
