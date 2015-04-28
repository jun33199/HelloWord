package com.creationstar.bjtax.qsgl.model.bo.clfgl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl.ClfjyxxCX;
import com.creationstar.bjtax.qsgl.VisionLogic.form.clfgl.ClfjyxxCXForm;

public class ClfjyxxCXBO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	
	
	public Object getFromData() {
		ClfjyxxCXForm form = new ClfjyxxCXForm();
		//查询条件
		form.setQuery_qsrq(this.query_qsrq);
		form.setQuery_jzrq(this.query_jzrq);
		form.setQuery_htbh(this.query_htbh);
		form.setQuery_fwqszylx(this.query_sellerN);
		form.setQuery_buyerN(this.query_buyerN);
		form.setQuery_fwqszylx(this.query_fwqszylx);	
		form.setQuery_minJzmj(this.query_minJzmj);
		form.setQuery_maxJzmj(this.query_maxJzmj);
		//查询结果
		
		
		
		return form;
		
	}
	
	public class resInfo implements Serializable{
		private static final long serialVersionUID = 1L;
		//存量房采集信息
		private ClfxxcjBo clfxxcjBo = new ClfxxcjBo();
		//征收信息 
		private  List clfjyxxList = new ArrayList();
		
		private BigDecimal all_ynse_hj;//合计应纳税额（查询显示用）
		
		private BigDecimal all_qs_ynse_hj;//契税应纳税额合计（查询显示用） add by tangchangfu 2014-06-16
		
		//存量房核定信息 added by zhangj
		private ClfswjghdxxlrBo clfswjghdxxlrBo=new ClfswjghdxxlrBo();
		private BigDecimal all_sjje_hj;//合计实缴税额
		
		public resInfo(ClfxxcjBo clfxxcjBo,ClfswjghdxxlrBo clfswjghdxxlrBo,List clfjyxxList,BigDecimal all_ynse_hj,BigDecimal all_qs_ynse_hj,BigDecimal all_sjje_hj) {
			super();
			this.clfxxcjBo = clfxxcjBo;
			this.clfjyxxList = clfjyxxList;
			this.clfswjghdxxlrBo=clfswjghdxxlrBo;
			if(all_qs_ynse_hj != null){
				this.all_ynse_hj = all_ynse_hj.add(all_qs_ynse_hj);//应纳税额 = 存量房应纳税额        +  契税应纳税额  add by tangchangfu 2014-06-16
				this.all_qs_ynse_hj = all_qs_ynse_hj;//契税应纳税额  add by tangchangfu 2014-06-16
				this.all_sjje_hj=all_sjje_hj;
			}else{
				this.all_ynse_hj = all_ynse_hj;//应纳税额 = 存量房应纳税额        +  契税应纳税额  add by tangchangfu 2014-06-16
				this.all_qs_ynse_hj = all_qs_ynse_hj;//契税应纳税额  add by tangchangfu 2014-06-16
				this.all_sjje_hj=all_sjje_hj;
			}
			
		}
		
		
		


		public ClfxxcjBo getClfxxcjBo() {
			return clfxxcjBo;
		}

		public void setClfxxcjBo(ClfxxcjBo clfxxcjBo) {
			this.clfxxcjBo = clfxxcjBo;
		}





		public List getClfjyxxList() {
			return clfjyxxList;
		}





		public void setClfjyxxList(List clfjyxxList) {
			this.clfjyxxList = clfjyxxList;
		}





		public BigDecimal getAll_ynse_hj() {
			return all_ynse_hj;
		}





		public void setAll_ynse_hj(BigDecimal all_ynse_hj) {
			this.all_ynse_hj = all_ynse_hj;
		}





		public BigDecimal getAll_qs_ynse_hj() {
			return all_qs_ynse_hj;
		}





		public void setAll_qs_ynse_hj(BigDecimal all_qs_ynse_hj) {
			this.all_qs_ynse_hj = all_qs_ynse_hj;
		}





		public ClfswjghdxxlrBo getClfswjghdxxlrBo() {
			return clfswjghdxxlrBo;
		}





		public void setClfswjghdxxlrBo(ClfswjghdxxlrBo clfswjghdxxlrBo) {
			this.clfswjghdxxlrBo = clfswjghdxxlrBo;
		}





		public BigDecimal getAll_sjje_hj() {
			return all_sjje_hj;
		}





		public void setAll_sjje_hj(BigDecimal all_sjje_hj) {
			this.all_sjje_hj = all_sjje_hj;
		}
		
		

	}
	
	
	public class clfjyxx  implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String sbbh;
		private BigDecimal sl;//税率
		private BigDecimal yys_JSJE_hj_02;//营业税计税金额
		private BigDecimal yys_YNSE_hj_02;//营业税应纳金额
		private BigDecimal grsds_JSJE_hj_05;//个人所得税计税金额
		private BigDecimal grsds_YNSE_hj_05;//个人所得税应纳金额
		private BigDecimal grsds_sl_05;//个人所得税税率
		private BigDecimal ycjd_YNSE_hj_02_10_51_54;//营城教地合计金额
		private BigDecimal yhs_YNSE_hj_16;//印花税应纳税额
		private BigDecimal all_jsje_hj;//合计计税金额
		private BigDecimal all_ynse_hj;//合计应纳税额
		
		//ADDED BY ZHANGJ
		private BigDecimal grsds_JMSE_hj_05;//个人所得税减免金额
		private BigDecimal grsds_SJJE_hj_05;//个人所得税实缴金额
		private BigDecimal yys_JMSE_hj_02;//营业税减免金额
		private BigDecimal yys_SJJE_hj_02;//营业税实缴金额
		private BigDecimal all_jmse_hj;//合计减免金额
		private BigDecimal all_sjje_hj;//合计实缴税额
		private BigDecimal tdzzs_JSJE_hj_08;//土地增值税计税金额
		private BigDecimal tdzzs_YNSE_hj_08;//土地增值税应纳金额
		private BigDecimal tdzzs_JMSE_hj_08;//土地增值税减免金额
		private BigDecimal tdzzs_SJJE_hj_08;//土地增值税实缴金额
		private BigDecimal tdzzs_sl_08;//土地增值税税率
		private BigDecimal ycjd_JMSE_hj_02_10_51_54;//营城教地合计减免金额
		private BigDecimal ycjd_SJJE_hj_02_10_51_54;//营城教地合计实缴金额
		private BigDecimal yhs_JMSE_hj_16;//印花税减免税额
		private BigDecimal yhs_SJJE_hj_16;//印花税实缴税额
		
		//契税查询信息（add by tangchangfu 2014-06-12）
		private BigDecimal qs_sl;//契税税率
		private BigDecimal qs_jsje_hj;//契税合计计税金额//add end
		
		
		
		
		public clfjyxx(ClfjyxxCX cxVo) {
			super();
			this.sbbh = cxVo.getSbbh();
			this.sl = cxVo.getSl();
			this.yys_JSJE_hj_02 = cxVo.getYys_JSJE_hj_02();
			this.yys_YNSE_hj_02 = cxVo.getYys_YNSE_hj_02();
			this.grsds_JSJE_hj_05 = cxVo.getGrsds_JSJE_hj_05();
			this.grsds_YNSE_hj_05 = cxVo.getGrsds_YNSE_hj_05();
			this.grsds_sl_05 = cxVo.getGrsds_sl_05();
			this.ycjd_YNSE_hj_02_10_51_54 = cxVo.getYcjd_YNSE_hj_02_10_51_54();
			this.yhs_YNSE_hj_16 = cxVo.getYhs_YNSE_hj_16();
			this.all_jsje_hj = cxVo.getAll_jsje_hj();
			this.all_ynse_hj = cxVo.getAll_ynse_hj();
			
			//ADDED BY ZHANGJ
			this.grsds_JMSE_hj_05=cxVo.getGrsds_JMSE_hj_05();
			this.grsds_SJJE_hj_05=cxVo.getGrsds_SJJE_hj_05();
			this.yys_JMSE_hj_02=cxVo.getYys_JMSE_hj_02();
			this.yys_SJJE_hj_02=cxVo.getYys_SJJE_hj_02();
			this.all_jmse_hj=cxVo.getAll_jmse_hj();
			this.all_sjje_hj=cxVo.getAll_sjje_hj();
			this.tdzzs_JMSE_hj_08=cxVo.getTdzzs_JMSE_hj_08();
			this.tdzzs_JSJE_hj_08=cxVo.getTdzzs_JSJE_hj_08();
			this.tdzzs_SJJE_hj_08=cxVo.getTdzzs_SJJE_hj_08();
			this.tdzzs_sl_08=cxVo.getTdzzs_sl_08();
			this.tdzzs_YNSE_hj_08=cxVo.getTdzzs_YNSE_hj_08();
			this.ycjd_JMSE_hj_02_10_51_54=cxVo.getYcjd_JMSE_hj_02_10_51_54();
			this.ycjd_SJJE_hj_02_10_51_54=cxVo.getYcjd_SJJE_hj_02_10_51_54();
			this.yhs_JMSE_hj_16=cxVo.getYhs_JMSE_hj_16();
			this.yhs_SJJE_hj_16=cxVo.getYhs_SJJE_hj_16();
			
			//契税查询信息（add by tangchangfu 2014-06-12）
			this.qs_sl = cxVo.getQs_sl();
			this.qs_jsje_hj = cxVo.getQs_jsje_hj();//add end
		}
		public String getSbbh() {
			return sbbh;
		}
		public void setSbbh(String sbbh) {
			this.sbbh = sbbh;
		}
		public BigDecimal getSl() {
			return sl;
		}
		public void setSl(BigDecimal sl) {
			this.sl = sl;
		}
		public BigDecimal getYys_JSJE_hj_02() {
			return yys_JSJE_hj_02;
		}
		public void setYys_JSJE_hj_02(BigDecimal yys_JSJE_hj_02) {
			this.yys_JSJE_hj_02 = yys_JSJE_hj_02;
		}
		public BigDecimal getYys_YNSE_hj_02() {
			return yys_YNSE_hj_02;
		}
		public void setYys_YNSE_hj_02(BigDecimal yys_YNSE_hj_02) {
			this.yys_YNSE_hj_02 = yys_YNSE_hj_02;
		}
		public BigDecimal getGrsds_JSJE_hj_05() {
			return grsds_JSJE_hj_05;
		}
		public void setGrsds_JSJE_hj_05(BigDecimal grsds_JSJE_hj_05) {
			this.grsds_JSJE_hj_05 = grsds_JSJE_hj_05;
		}
		public BigDecimal getGrsds_YNSE_hj_05() {
			return grsds_YNSE_hj_05;
		}
		public void setGrsds_YNSE_hj_05(BigDecimal grsds_YNSE_hj_05) {
			this.grsds_YNSE_hj_05 = grsds_YNSE_hj_05;
		}
		public BigDecimal getGrsds_sl_05() {
			return grsds_sl_05;
		}
		public void setGrsds_sl_05(BigDecimal grsds_sl_05) {
			this.grsds_sl_05 = grsds_sl_05;
		}
		public BigDecimal getYcjd_YNSE_hj_02_10_51_54() {
			return ycjd_YNSE_hj_02_10_51_54;
		}
		public void setYcjd_YNSE_hj_02_10_51_54(BigDecimal ycjd_YNSE_hj_02_10_51_54) {
			this.ycjd_YNSE_hj_02_10_51_54 = ycjd_YNSE_hj_02_10_51_54;
		}
		public BigDecimal getYhs_YNSE_hj_16() {
			return yhs_YNSE_hj_16;
		}
		public void setYhs_YNSE_hj_16(BigDecimal yhs_YNSE_hj_16) {
			this.yhs_YNSE_hj_16 = yhs_YNSE_hj_16;
		}
		public BigDecimal getAll_jsje_hj() {
			return all_jsje_hj;
		}
		public void setAll_jsje_hj(BigDecimal all_jsje_hj) {
			this.all_jsje_hj = all_jsje_hj;
		}
		public BigDecimal getAll_ynse_hj() {
			return all_ynse_hj;
		}
		public void setAll_ynse_hj(BigDecimal all_ynse_hj) {
			this.all_ynse_hj = all_ynse_hj;
		}
		public BigDecimal getQs_sl() {
			return qs_sl;
		}
		public void setQs_sl(BigDecimal qs_sl) {
			this.qs_sl = qs_sl;
		}
		public BigDecimal getQs_jsje_hj() {
			return qs_jsje_hj;
		}
		public void setQs_jsje_hj(BigDecimal qs_jsje_hj) {
			this.qs_jsje_hj = qs_jsje_hj;
		}
		public BigDecimal getGrsds_JMSE_hj_05() {
			return grsds_JMSE_hj_05;
		}
		public void setGrsds_JMSE_hj_05(BigDecimal grsdsJMSEHj_05) {
			grsds_JMSE_hj_05 = grsdsJMSEHj_05;
		}
		public BigDecimal getGrsds_SJJE_hj_05() {
			return grsds_SJJE_hj_05;
		}
		public void setGrsds_SJJE_hj_05(BigDecimal grsdsSJJEHj_05) {
			grsds_SJJE_hj_05 = grsdsSJJEHj_05;
		}
		public BigDecimal getYys_JMSE_hj_02() {
			return yys_JMSE_hj_02;
		}
		public void setYys_JMSE_hj_02(BigDecimal yysJMSEHj_02) {
			yys_JMSE_hj_02 = yysJMSEHj_02;
		}
		public BigDecimal getYys_SJJE_hj_02() {
			return yys_SJJE_hj_02;
		}
		public void setYys_SJJE_hj_02(BigDecimal yysSJJEHj_02) {
			yys_SJJE_hj_02 = yysSJJEHj_02;
		}
		public BigDecimal getAll_jmse_hj() {
			return all_jmse_hj;
		}
		public void setAll_jmse_hj(BigDecimal allJmseHj) {
			all_jmse_hj = allJmseHj;
		}
		public BigDecimal getAll_sjje_hj() {
			return all_sjje_hj;
		}
		public void setAll_sjje_hj(BigDecimal allSjjeHj) {
			all_sjje_hj = allSjjeHj;
		}
		public BigDecimal getTdzzs_JSJE_hj_08() {
			return tdzzs_JSJE_hj_08;
		}
		public void setTdzzs_JSJE_hj_08(BigDecimal tdzzsJSJEHj_08) {
			tdzzs_JSJE_hj_08 = tdzzsJSJEHj_08;
		}
		public BigDecimal getTdzzs_YNSE_hj_08() {
			return tdzzs_YNSE_hj_08;
		}
		public void setTdzzs_YNSE_hj_08(BigDecimal tdzzsYNSEHj_08) {
			tdzzs_YNSE_hj_08 = tdzzsYNSEHj_08;
		}
		public BigDecimal getTdzzs_JMSE_hj_08() {
			return tdzzs_JMSE_hj_08;
		}
		public void setTdzzs_JMSE_hj_08(BigDecimal tdzzsJMSEHj_08) {
			tdzzs_JMSE_hj_08 = tdzzsJMSEHj_08;
		}
		public BigDecimal getTdzzs_SJJE_hj_08() {
			return tdzzs_SJJE_hj_08;
		}
		public void setTdzzs_SJJE_hj_08(BigDecimal tdzzsSJJEHj_08) {
			tdzzs_SJJE_hj_08 = tdzzsSJJEHj_08;
		}
		public BigDecimal getTdzzs_sl_08() {
			return tdzzs_sl_08;
		}
		public void setTdzzs_sl_08(BigDecimal tdzzsSl_08) {
			tdzzs_sl_08 = tdzzsSl_08;
		}
		public BigDecimal getYcjd_JMSE_hj_02_10_51_54() {
			return ycjd_JMSE_hj_02_10_51_54;
		}
		public void setYcjd_JMSE_hj_02_10_51_54(BigDecimal ycjdJMSEHj_02_10_51_54) {
			ycjd_JMSE_hj_02_10_51_54 = ycjdJMSEHj_02_10_51_54;
		}
		public BigDecimal getYcjd_SJJE_hj_02_10_51_54() {
			return ycjd_SJJE_hj_02_10_51_54;
		}
		public void setYcjd_SJJE_hj_02_10_51_54(BigDecimal ycjdSJJEHj_02_10_51_54) {
			ycjd_SJJE_hj_02_10_51_54 = ycjdSJJEHj_02_10_51_54;
		}
		public BigDecimal getYhs_JMSE_hj_16() {
			return yhs_JMSE_hj_16;
		}
		public void setYhs_JMSE_hj_16(BigDecimal yhsJMSEHj_16) {
			yhs_JMSE_hj_16 = yhsJMSEHj_16;
		}
		public BigDecimal getYhs_SJJE_hj_16() {
			return yhs_SJJE_hj_16;
		}
		public void setYhs_SJJE_hj_16(BigDecimal yhsSJJEHj_16) {
			yhs_SJJE_hj_16 = yhsSJJEHj_16;
		}

		
		
		
		
		
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
