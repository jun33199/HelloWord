package com.creationstar.bjtax.qsgl.BizLogic.processor.clfgl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.DAOFactory;
import com.creationstar.bjtax.qsgl.BizLogic.processor.CommonProcessor;
import com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl.ClfjyxxCX;
import com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl.Fwhdxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl.MfsbxxzbSeller;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.clfgl.ClfjyxxCXBO;
import com.creationstar.bjtax.qsgl.model.bo.clfgl.ClfswjghdxxlrBo;
import com.creationstar.bjtax.qsgl.model.bo.clfgl.ClfxxcjBo;
import com.creationstar.bjtax.qsgl.util.ActionUtil;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.DateUtils;
import com.creationstar.bjtax.qsgl.util.ErrorLog;
import com.creationstar.bjtax.qsgl.util.QSDBUtil;
import com.creationstar.bjtax.qsgl.util.SecurityUtil;
import com.creationstar.bjtax.qsgl.util.StackMsg2StringUtil;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.Debug;
import com.ttsoft.common.util.ZKAdapter;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;



/**
 * 存量房交易信息查询
 * @author admin
 *
 */
public class ClfjyxxCXProcessor extends CommonProcessor  {
	public Object process(VOPackage vo) throws BaseException {
		Object result = null;

		if (vo == null) {
			throw new NullPointerException();
		}

		switch (vo.getAction()) {
		case ActionType.QUERY:
			result = doQuery(vo); // 查询存量房交易信息
			break;
		default:
			throw new ApplicationException("ActionType有错误，processor中找不到相应的方法.");
		}

		return result;
	}

	
	
	/**
	 * 存量房交易信息查询
	 * @param vo
	 * @return
	 */
	private Object doQuery(VOPackage vo)throws BaseException {
		//获得前台数据
		ClfjyxxCXBO bo = (ClfjyxxCXBO)vo.getData();
		UserData ud = vo.getUserData();
		//查询结果集
		ArrayList resList = new ArrayList();
		
		
		System.out.println("bo查询条件--起始日期-->"+bo.getQuery_qsrq());
		System.out.println("bo查询条件--截止日期-->"+bo.getQuery_jzrq());
		System.out.println("bo查询条件--合同编号-->"+bo.getQuery_htbh());
		
		
		
		
		//拼接查询条件
		String Query_qsrq = SecurityUtil.dealwithStringPara(bo.getQuery_qsrq());//查询条件 --起始日期
		String Query_jzrq =	SecurityUtil.dealwithStringPara(bo.getQuery_jzrq());	//查询条件 -- 截止日期
		String Query_htbh = SecurityUtil.dealwithStringPara(bo.getQuery_htbh());//查询条件 -- 合同编号
		String Query_sellerN = SecurityUtil.dealwithStringPara(bo.getQuery_sellerN());//卖方姓名
		String Query_buyerN = SecurityUtil.dealwithStringPara(bo.getQuery_buyerN());//买方姓名
		String Query_fwqszylx = SecurityUtil.dealwithStringPara(bo.getQuery_fwqszylx());//房屋权属转移类型
		String Query_minJzmj = SecurityUtil.dealwithStringPara(bo.getQuery_minJzmj());//房屋权属转移类型
		String Query_maxJzmj = SecurityUtil.dealwithStringPara(bo.getQuery_maxJzmj());//房屋权属转移类型
		// 增加数据权限控制
        String datafilter;
		try {
			datafilter = ZKAdapter.getInstance().getDataFilterString(ud,
					"QSDB", "QS_JL_MFSBXXZB_SELLER");
		} catch (Exception e) {
			throw new ApplicationException("过滤数据权限失败，查询出错，请与系统管理员联系.");
		}
        
		//拼接SQL
		
		StringBuffer buff = new StringBuffer();
		buff.append(" where 1=1 and a.HTBH=b.HTBH and a.HTBH=c.HTBH");
		if(Query_qsrq != null && !"".equals(Query_qsrq)){
			buff.append(" and a.cjrq >= to_date('"+Query_qsrq+"','yyyymmdd') ");
		}

		if(Query_jzrq != null && !"".equals(Query_jzrq)){
			buff.append(" and a.cjrq < to_date('"+Query_jzrq+"','yyyymmdd')+1 ");
		}
		
		if(Query_htbh != null && !"".equals(Query_htbh)){
			buff.append(" and a.htbh='"+Query_htbh+"'");
		}
		if(Query_sellerN != null && !"".equals(Query_sellerN)){
			buff.append(" and a.nsrmc like '%"+Query_sellerN+"%'");
		}
		if(Query_buyerN != null && !"".equals(Query_buyerN)){
			buff.append(" and b.nsrmc like '%"+Query_buyerN+"%'");
		}
		if(Query_fwqszylx != null && !"".equals(Query_fwqszylx)){
			buff.append(" and c.fwqszylx like '%"+Query_fwqszylx+"%'");
		}
		if(Query_minJzmj != null && !"".equals(Query_minJzmj)){
			buff.append(" and c.fwjzmj >= "+Double.parseDouble(Query_minJzmj));
		}
		if(Query_maxJzmj != null && !"".equals(Query_maxJzmj)){
			buff.append(" and c.fwjzmj <= "+Double.parseDouble(Query_maxJzmj));
		}
		buff.append(" and "+datafilter);
		
		
		Connection conn = null;

		try {
			conn = QSDBUtil.getConnection();
			
	    	//在征收表中查询出满足条件的征收信息
	    	List mfsbxxzbSellerList = new ArrayList();
	    	try{
	    	mfsbxxzbSellerList = DAOFactory.getInstance().getClfjyxxCXDAO().query(conn, buff.toString());
	    	}catch(Exception e){
	    		
	    		throw new ApplicationException("查询征收信息时出错，请与管理员联系");
	    	}
	    	
	    	if(mfsbxxzbSellerList == null || mfsbxxzbSellerList.size() == 0){
	    		//查询无结果，报错
	    		throw new ApplicationException("没有满足条件的结果");
	    		
	    	}
	    	
	    	
	    	//如果查询有结果，则开始循环szxxList里的每个sbbh和合同编号,获得每个申报编号或者合同编号下，二维码采集信息以及征收信息
	    	for(int index =0; index < mfsbxxzbSellerList.size();index ++){
	    		MfsbxxzbSeller mfsbxxzbSeller = (MfsbxxzbSeller) mfsbxxzbSellerList.get(index);
	    		//遍历出每个sbbh 和 htbh
	    		String sbbh = mfsbxxzbSeller.getSbbh();
	    		String htbh = mfsbxxzbSeller.getHtbh();
	    		//System.out.println("++++++++++sbbh::"+sbbh  +"::htbh"+htbh);
	    		
	    		//用sbbh 和  htbh 获得存量房采集信息
	    		VOPackage vo1 = vo;
	    		ClfxxcjBo data = new ClfxxcjBo();
	    		data.setSbbh(sbbh);
	    		data.setHtbh(htbh);
	    		vo1.setData(data);
	    		
	    		ClfxxcjBo resBo = null;
				try {
					ClfxxcjProcessor cjpro = new ClfxxcjProcessor();
                    resBo =  cjpro.getCjxx(vo1);
				} catch (Exception e) {
					throw new ApplicationException("查询买卖方采集信息出错，请与管理员联系");
				}
				
/*				if(resBo == null ){
					throw new ApplicationException("无买卖方采集信息信息，请检查查询条件.");
				}*/
					    		
	    		//获得税款征收信息
	    		BigDecimal ynsehj = new BigDecimal("0.00");
	    		BigDecimal ynsehj_qs = new BigDecimal("0.00");// add by tangchangfu 2014-06-16  为契税增加契税查询相关信息
	    		List resjyxxList = new ArrayList();
	    		List jyxxList = null;
	    		BigDecimal sjjehj = new BigDecimal("0.00");
				try {
					jyxxList = DAOFactory.getInstance().getClfjyxxCXDAO()
							.queryJyxxList(conn, sbbh,htbh);
				} catch (Exception e) {
					e.printStackTrace();
					throw new ApplicationException("查询征收明细信息出错，请与管理员联系");
				}
	    		
	    		if(jyxxList == null || jyxxList.size() == 0){
	    			throw new ApplicationException("无申报信息，请检查查询条件.");
	    		}
	    		
	    		
	    		
	    		for(int jyxxIndex =0; jyxxIndex < jyxxList.size(); jyxxIndex ++){
	    			
	    			ClfjyxxCX resjyxxVo = (ClfjyxxCX)jyxxList.get(jyxxIndex);
	    			
	    			ClfjyxxCXBO.clfjyxx oneItemJyxx = new ClfjyxxCXBO().new clfjyxx(resjyxxVo);
	    			resjyxxList.add(oneItemJyxx);
	    			
	    			//应纳税额合计
	    			ynsehj = oneItemJyxx.getAll_ynse_hj();
	    			sjjehj=oneItemJyxx.getAll_sjje_hj();
	    			
	    			ynsehj_qs = oneItemJyxx.getQs_jsje_hj();// add by tangchangfu 2014-06-16  为契税增加契税查询相关信息
	    			//System.out.println("循环次数+++++++++"+jyxxIndex);
	    		}
	    		
	    		
	    		
	    		
	    		//整理信息并输出
	    		//System.out.println(sbbh+"交易信息长度+++++++++"+resjyxxList.size());
	    		//System.out.println(sbbh+"htbh*************"+resBo.getHtbh());
	    		ClfjyxxCXBO.resInfo oneResItem = new ClfjyxxCXBO().new resInfo(resBo,this.getFwhdxx(sbbh),resjyxxList,ynsehj,ynsehj_qs,sjjehj);
	    		
	    		//把结果集放到返回结果里
	    		resList.add(oneResItem);
	    	}
	    	
	    	//System.out.println("返回结果长度为-----"+resList.size());
	    	
	    	
		}catch(Exception e){
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		}finally{
			QSDBUtil.freeConnection(conn);
		}
		return resList;
	}
/**
 * 获得房屋核定信息
 */

	/**
	 * 执行查询操作
	 * @param vo
	 * @return
	 * @throws BaseException
	 */
	public ClfswjghdxxlrBo getFwhdxx(String sbbh) throws BaseException {
		ClfswjghdxxlrBo resBo = null;
		Connection conn = null;
		
		
		try {
			//获得数据库连接
			conn = QSDBUtil.getConnection();
			if(conn == null){
				throw new ApplicationException("查询信息报错，无法获得数据库连接，请退出从新登陆，或者和系统管理员联系！");
			}			

			
			
			if (sbbh == null || "".equals(sbbh)) {
				throw new ApplicationException("查询不到同采集信息，请检查合同编号是否正确！");
			}

			//查询条件
			String condition = "";
			if (sbbh != null && !"".equals(sbbh)) {
				condition = " where sbbh='" + sbbh + "'";
			}
			// 获得税务人员核定信息
			ArrayList hdxxList = this.getSwryhdxx(conn,condition);
			// 如果信息只有大于一条，则报错
			if (hdxxList != null && hdxxList.size() > 1) {
				throw new ApplicationException("查询税务人员核定信息出错!，同一申报编号存在核定表中存在多条信息，申报编号为："+ resBo.getSbbh());
			}
		
			//返回结果
			if (hdxxList != null && hdxxList.size() == 1) {
				Fwhdxx hdxxVo = (Fwhdxx) hdxxList.get(0);

				//@@1 设置核定信息
				if (hdxxVo != null) {
					resBo=this.getClfswjghdxxlrBoByHdxxVo(hdxxVo);
					resBo.setIsSaved("true");// 该合合同信息已经存在于合同编号
				}

			} else {
				// 购房证明日期 初始值为 房屋所有权证填发日期
				if (resBo.getGfzmrq() == null || "".equals(resBo.getGfzmrq())) {
					resBo.setGfzmrq(resBo.getFwsyqztfrq());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			// 处理失败信息:控制台 ＋ 日志
			Debug.printException(e);
		} finally {
			QSDBUtil.freeConnection(conn);
		}
		return resBo;
	}
	/**
	 * 获得税务人员核定信息
	 * 
	 * @param condition
	 * @return
	 * @throws BaseException
	 */
	public ArrayList getSwryhdxx(Connection conn,String condition) throws BaseException {
		System.out.println("+++++++++++获得税务人员核定信息getSwryhdxx ++++++++++++");
		ArrayList hdxxList = new ArrayList();
		// 判断必填项
		if (condition != null && !"".equals(condition)) {
			try {
				hdxxList = DAOFactory.getInstance().getFwhdxxbDAO().query(conn, condition);
			} catch (Exception e) {
				e.printStackTrace();
				try {
					throw new ApplicationException("获取税务人员核定信息出错！");
				} catch (ApplicationException e1) {
					e1.printStackTrace();
					// 处理失败信息:控制台 ＋ 日志
					Debug.printException(e1);
					throw ExceptionUtil.getBaseException(e1);

				}
			}
		} else {
			throw new ApplicationException("无法获取税务人员核定信息，请输入查询条件！");
		}
		
		return hdxxList;
	}


	/**
	 * 通过 Fwhdxx 获得 ClfswjghdxxlrBo
	 * 
	 * @param hdxxVo
	 * @param bo
	 * @return
	 */
	private ClfswjghdxxlrBo getClfswjghdxxlrBoByHdxxVo(Fwhdxx hdxxVo) {
		DecimalFormat deFormat_00 = new DecimalFormat("#0.00");// 合计金额格式
		DecimalFormat deFormat_0000 = new DecimalFormat("#0.0000");// 合计金额格式
		ClfswjghdxxlrBo bo=new ClfswjghdxxlrBo();
		bo.setSbbh(hdxxVo.getSbbh());
		bo.setHtbh(hdxxVo.getHtbh());
		bo.setSqrxzdz(hdxxVo.getSqrxzdz());
		bo.setJtwyshyhbz(hdxxVo.getJtwyshyhbz());
		bo.setFwlxdm(hdxxVo.getFwlxdm());
		bo.setJcnd(hdxxVo.getJcnd());
		// bo.setZlc(hdxxVo.getZlc());
		// fwjzmj
		//System.out.println("房屋建筑面积++++++++++" + hdxxVo.getFwjzmj());
		//System.out.println("合理费用++++++++++" + hdxxVo.getHlfy());
		bo.setFwjzmj(deFormat_0000.format(hdxxVo.getFwjzmj()));
		bo.setYgffpje(deFormat_00.format(hdxxVo.getYgffpje()));

		if (hdxxVo.getGfzmrq() != null || !"".equals(hdxxVo.getGfzmrq())) {
			bo.setGfzmrq(DateUtils.getDate(hdxxVo.getGfzmrq()));
		}

		bo.setTdzzssbfs(hdxxVo.getTdzzssbfs());
		bo.setQdfcqsje(deFormat_00.format(hdxxVo.getQdfcqsje()));
		bo.setQdfcyhsje(deFormat_00.format(hdxxVo.getQdfcyhsje()));
		bo.setQdtdsyqzfje(deFormat_00.format(hdxxVo.getQdtdsyqzfje()));
		bo.setJfpgjg(deFormat_00.format(hdxxVo.getJfpgjg()));
		bo.setJgpgfy(deFormat_00.format(hdxxVo.getJgpgfy()));
		bo.setFdczjjsjdm(hdxxVo.getFdczjjsjdm());
		bo.setFdczjswdjzh(hdxxVo.getFdczjswdjzh());
		bo.setFdczjlxdh(hdxxVo.getFdczjlxdh());
		bo.setFdczjjjr(hdxxVo.getFdczjjjr());
		bo.setFdczjjjrlxdh(hdxxVo.getFdczjjjrlxdh());
		bo.setFdczjjjrzjhm(hdxxVo.getFdczjjjrzjhm());
		bo.setFdczjjjrzgzsh(hdxxVo.getFdczjjjrzgzsh());
		bo.setCqzbzjzmjfl(hdxxVo.getCqzbzjzmjfl());
		bo.setMpmjydj(deFormat_00.format(hdxxVo.getMpmjydj()));
		bo.setPtzfzgxj(deFormat_00.format(hdxxVo.getPtzfzgxj()));
		bo.setFwrjl(hdxxVo.getFwrjl());
		bo.setHfbz(hdxxVo.getHfbz());
		bo.setZfsjsjfl(hdxxVo.getZfsjsjfl());
		bo.setYyszsfs(hdxxVo.getYyszsfs());
		bo.setGrsdszsfs(hdxxVo.getGrsdszsfs());
		bo.setTdzsszsfs(hdxxVo.getTdzsszsfs());
		bo.setYhszsfs(hdxxVo.getYhszsfs());
		bo.setJssrqrfs(hdxxVo.getJssrqrfs());
		bo.setJsje(deFormat_00.format(hdxxVo.getJsje()));
		bo.setZfpgjg(deFormat_00.format(hdxxVo.getZfpgjg()));
		bo.setZfzxfy(deFormat_00.format(hdxxVo.getZfzxfy()));
		bo.setZfdklx(deFormat_00.format(hdxxVo.getZfdklx()));
		bo.setSxf(deFormat_00.format(hdxxVo.getSxf()));
		bo.setGzf(deFormat_00.format(hdxxVo.getGzf()));
		bo.setHlfy(deFormat_00.format(hdxxVo.getHlfy()));
		bo.setTdjcdm(hdxxVo.getTdjcdm());
		// bo.setTdjcmc(string2BigDecimal(hdxxVo.getTdjcmc()));
		bo.setFwcqzbzzflxdm(hdxxVo.getFwcqzbzzflxdm());
		bo.setFwcqzbzzflxdm(hdxxVo.getFwcqzbzzflxdm());
		// bo.setCqzbzjzmjflSubmit(hdxxVo.getCqzbzjzmjflSubmit());
		// bo.setHfbzSubmit(hdxxVo.getHfbzSubmit());
		// bo.setGrsdszsfsSubmit(hdxxVo.getGrsdszsfsSubmit());
		// bo.setYyszsfsSubmit(hdxxVo.getYyszsfsSubmit());
		// bo.setJsjeSubmit(hdxxVo.getJsjeSubmit());

		bo.setMpmhdjg(deFormat_00.format(hdxxVo.mpmhdjg));// 每平米核定价格
		bo.setTdcrj(deFormat_00.format(hdxxVo.tdcrj));// 土地出让金
		// bo.setJwcxfwyz(deFormat_00.format(hdxxVo.jwcxfwyz);//建委查询房屋原值
		bo.setCjssl(deFormat_00.format(hdxxVo.cjssl));// 城建税税率

		/**
		 * 发票所载日期
		 */
		if (hdxxVo.fpszrq != null && !"".equals(hdxxVo.fpszrq)) {
			bo.setFpszrq(DateUtils.getDate(hdxxVo.fpszrq));
		}
		bo.setAnjjse(deFormat_00.format(hdxxVo.anjjse));// 按年加计数额
		bo.setFwszqydm(hdxxVo.fwszqydm);// 房屋所在区域代码

		bo.setFwhdlxdm(hdxxVo.getFwhdlxdm());// 房屋核定类型代码
		bo.setQszyxsmxdm(hdxxVo.getQszyxsmxdm());//
		bo.setYqspfwjsjg(deFormat_00.format(hdxxVo.getYqspfwjsjg()));
		bo.setLrrq(DateUtils.getDate(hdxxVo.getLrrq()));
		bo.setMpmhdjg(deFormat_00.format(hdxxVo.getMpmhdjg()));		
		bo.setFwszqydm(hdxxVo.getFwszqydm());
		return bo;
	}	
	
}
