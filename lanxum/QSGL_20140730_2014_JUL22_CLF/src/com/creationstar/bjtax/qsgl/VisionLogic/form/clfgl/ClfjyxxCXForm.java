package com.creationstar.bjtax.qsgl.VisionLogic.form.clfgl;

import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;

import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.QueryBaseForm;
import com.creationstar.bjtax.qsgl.model.bo.clfgl.ClfjyxxCXBO;
import com.creationstar.bjtax.qsgl.util.DateUtils;

public class ClfjyxxCXForm extends QueryBaseForm {
	
	/**
	 * 查询条件
	 */
	
	private String query_qsrq;//起始日期
	
	private String query_jzrq;//截止日期
	
	private String query_htbh;//合同编号
	private String query_sellerN;//卖方姓名
	private String query_buyerN;//买方姓名
	private String query_fwqszylx;//房屋权属转移类型
	private String query_minJzmj;//最小建筑面积
	private String query_maxJzmj;//最大建筑面积
	
	
	private ArrayList resList = new ArrayList();
	
	
	public Object getData() {
		ClfjyxxCXBO bo = new ClfjyxxCXBO();
		//查询条件
		bo.setQuery_qsrq(this.query_qsrq);
		bo.setQuery_jzrq(this.query_jzrq);
		bo.setQuery_htbh(this.query_htbh);
		bo.setQuery_sellerN(this.query_sellerN);
		bo.setQuery_buyerN(this.query_buyerN);
		bo.setQuery_fwqszylx(this.query_fwqszylx);	
		bo.setQuery_minJzmj(this.query_minJzmj);
		bo.setQuery_maxJzmj(this.query_maxJzmj);
		
		//查询结果
		
		
		
		return bo;
		
	}
	
	
	public void reset(){
		
		Calendar cal = Calendar.getInstance();
		  // 不加下面2行，就是取当前时间前一个月的第一天及最后一天
/*		cal.set(Calendar.YEAR,2012);
		  cal.set(Calendar.MONTH, 6);*/
		  
		  
		  cal.set(Calendar.DAY_OF_MONTH, 1);
		  cal.add(Calendar.DAY_OF_MONTH, -1);
		  Date lastDate = cal.getTime();
		  
		  cal.set(Calendar.DAY_OF_MONTH, 1);
		  Date firstDate = cal.getTime();
		  
		this.query_htbh = "";
		this.query_jzrq=DateUtils.getDate(lastDate) ;
		this.query_qsrq=DateUtils.getDate(firstDate);
		this.query_sellerN = "";
		this.query_buyerN = "";
		this.query_fwqszylx = "";
		this.query_minJzmj = "";
		this.query_maxJzmj = "";
		this.resList = new ArrayList();
		
		
	}
	
	
	public static void main(String args[]){
		Calendar cal = Calendar.getInstance();
		  // 不加下面2行，就是取当前时间前一个月的第一天及最后一天
/*		cal.set(Calendar.YEAR,2012);
		  cal.set(Calendar.MONTH, 6);*/
		  
		  
		  cal.set(Calendar.DAY_OF_MONTH, 1);
		  cal.add(Calendar.DAY_OF_MONTH, -1);
		  Date lastDate = cal.getTime();
		  
		  
		  
		  
		  cal.set(Calendar.DAY_OF_MONTH, 1);
		  Date firstDate = cal.getTime();
		  
		  System.out.println(DateUtils.getDate(lastDate) +""+DateUtils.getDate(firstDate));
		
		
	}
	
	
	
	
	

	public String getQuery_qsrq() {
		return query_qsrq;
	}

	public void setQuery_qsrq(String query_qsrq) {
		this.query_qsrq = query_qsrq;
	}

	public String getQuery_jzrq() {
		return query_jzrq;
	}

	public void setQuery_jzrq(String query_jzrq) {
		this.query_jzrq = query_jzrq;
	}

	public String getQuery_htbh() {
		return query_htbh;
	}

	public void setQuery_htbh(String query_htbh) {
		this.query_htbh = query_htbh;
	}






	public ArrayList getResList() {
		return resList;
	}






	public void setResList(ArrayList resList) {
		this.resList = resList;
	}


	public String getQuery_sellerN() {
		return query_sellerN;
	}


	public void setQuery_sellerN(String querySellerN) {
		query_sellerN = querySellerN;
	}


	public String getQuery_buyerN() {
		return query_buyerN;
	}


	public void setQuery_buyerN(String queryBuyerN) {
		query_buyerN = queryBuyerN;
	}


	public String getQuery_fwqszylx() {
		return query_fwqszylx;
	}


	public void setQuery_fwqszylx(String queryFwqszylx) {
		query_fwqszylx = queryFwqszylx;
	}


	public String getQuery_minJzmj() {
		return query_minJzmj;
	}


	public void setQuery_minJzmj(String queryMinJzmj) {
		query_minJzmj = queryMinJzmj;
	}


	public String getQuery_maxJzmj() {
		return query_maxJzmj;
	}


	public void setQuery_maxJzmj(String queryMaxJzmj) {
		query_maxJzmj = queryMaxJzmj;
	}
	
	
	
	
	
	
	
}
