/*
 * <p>Title: 北京地税核心征管系统－－耕地占用税--税源登记</p>
 * <p>Copyright: (C) 2013 北京市地方税务局，立思辰电子科技有限公司，版权所有. </p>
 * <p>Company: 立思辰电子科技有限公司</p>
 */
package com.ttsoft.bjtax.smsb.gdzys.sydj.sydjxxlr.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.dj.model.YHZH;
import com.ttsoft.bjtax.dj.model.ZRRJBSJ;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.util.LabelValueBean;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.gdzys.common.GdzysGy;
import com.ttsoft.bjtax.smsb.gdzys.constant.GdzysCodeConstant;
import com.ttsoft.bjtax.smsb.gdzys.sydj.sydjxxlr.model.DJXX;
import com.ttsoft.bjtax.smsb.gdzys.sydj.sydjxxlr.model.JMXXModel;
import com.ttsoft.bjtax.smsb.gdzys.sydj.sydjxxlr.model.SBMXModel;
import com.ttsoft.bjtax.smsb.gdzys.sydj.sydjxxlr.web.GdzysSydjxxlrForm;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: 耕地占用税</p>
 * <p>Description: 新建税源登记Action。</p>
 * @author kanght
 * @version 1.0
 */
public class GdzysSydjxxlrProcessor implements Processor {

    public GdzysSydjxxlrProcessor() {
    }
    /**
     *税源登记
     * @param vp VOPackage
     * @return Object
     * @throws BaseException BaseException
     */
    public Object process(VOPackage vp) throws BaseException {
        switch (vp.getAction()) {
       
        case GdzysCodeConstant.GDZYS_SYXXLRACTION_INIT:
            return this.doInit(vp);
        case GdzysCodeConstant.GDZYS_SYXXLRACTION_QUERY: //初始化
            return this.doQuery(vp);
        case GdzysCodeConstant.GDZYS_SYXXLRACTION_CONFIRM: //确认
        	return this.doConfirm(vp);
        case GdzysCodeConstant.GDZYS_SYXXLRACTION_PRINT_SBB: //打印信息
        	return this.getPrintSBB(vp);
        case GdzysCodeConstant.GDZYS_SYXXLRACTION_REMARK://备注
        	return this.doRemark(vp);
        case GdzysCodeConstant.GDZYS_SYXXLRACTION_SAVE://保存
        	return this.doSave(vp);
        case GdzysCodeConstant.GDZYS_SYXXLRACTION_DJXX://保存
        	return this.getJBSJ(vp);
        case GdzysCodeConstant.GDZYS_SYXXLRACTION_SYSE://AJAX获取适用税额
        	return this.getSYSE(vp);
        case GdzysCodeConstant.GDZYS_SYXXLRACTION_JMSYJ:
        	return this.getJMSYJ(vp);
        case GdzysCodeConstant.GDZYS_SYXXLRACTION_ZRR:
        	return this.getZRR(vp);
        case GdzysCodeConstant.GDZYS_SYXXLRACTION_QXSH:
        	return this.doQxsh(vp);
        case GdzysCodeConstant.GDZYS_SYXXLRACTION_SJSH:
        	return this.doSjsh(vp);
        case GdzysCodeConstant.GDZYS_SYXXLRACTION_UPDATE:
        	return this.doUpdate(vp);
        case GdzysCodeConstant.GDZYS_SYXXLRACTION_BGQUERY:
        	return this.doBgcx(vp);
        default:
            throw new ApplicationException(
                "ActionType有错误，processor中找不到相应的方法.");
        }
    }
  //变更查询
    private Object doBgcx(VOPackage vp) throws BaseException  {
    
    	GdzysSydjxxlrForm form = (GdzysSydjxxlrForm) vp.getData();
    	UserData ud= vp.getUserData();
    	
	  	Connection conn = null;
		PreparedStatement ps = null;
	  	ResultSet rs = null;
	  	String sql = " select * from  sbdb.SB_JL_GDZYS_SBB where sbbxlh=?";
		 try {
			conn = SfDBResource.getConnection();
			ps =  conn.prepareStatement(sql);
			ps.setString(1, form.getSbbxlh());
			rs = ps.executeQuery();
			if(rs.next()){
				
				form.setSbbxlh(rs.getString("SBBXLH"));//申报序列号
				form.setSylxdm(rs.getString("sylxdm"));//税源类型代码
				form.setNsrlx(rs.getString("nsrlx"));//纳税人类型
				form.setNsrmc(rs.getString("nsrmc"));//纳税人名称
				form.setJsjdm(rs.getString("JSJDM"));//计算机代码
				//税务登记证号
				//组织机构代码
				form.setNsrsshy(rs.getString("nsrsshy"));//纳税人所属行业
				form.setSfzzlxdm(rs.getString("sfzzlx"));//身份证照类型
				form.setSfzzhm(rs.getString("SFZZHM"));//身份证照号码
				form.setNsrxxdz(rs.getString("nsrxxdz"));//纳税人详细地址
				form.setQydjzclx(rs.getString("qydjzclx"));//企业登记注册类型
				form.setKhyh(rs.getString("KHYHMC"));//开户银行名称
				form.setYhzh(rs.getString("YHZH"));//银行帐号
				form.setLxrxm(rs.getString("LXRXM"));//联系人姓名
				form.setLxdh(rs.getString("LXDH"));//联系电话
				form.setSfsjsp(rs.getString("sfsjsp"));//是否市局审批
				form.setTdzldz(rs.getString("TDZLDZ"));//土地坐落地址
				form.setPzjdwh(rs.getString("PZJDWH"));//批准占地文号
				if(rs.getString("PZJDMJ") == null || "".equals(rs.getString("PZJDMJ"))){
					form.setPzjdmj("");//批准占地面积
				}else{
					form.setPzjdmj(String.valueOf(rs.getBigDecimal("PZJDMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));//批准占地面积
				}
				//form.setPzjdmj(rs.getString("PZJDMJ"));//批准占地面积
				form.setJsxmmc(rs.getString("JSXMMC"));//建设项目名称
				//form.setSjzdmj(rs.getString("SJZDMJ"));//实际占地面积
				form.setSjzdmj(String.valueOf(rs.getBigDecimal("SJZDMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));//实际占地面积
				SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
				
				form.setZdsj(df.format(rs.getDate("ZDSJ")));//占地时间
				form.setJmsyj(rs.getString("sbjmly"));//申报减免理由
				form.setHj_jsmj(String.valueOf(rs.getBigDecimal("jsmj").setScale(2,BigDecimal.ROUND_HALF_UP)));//计税面积
				form.setHj_jzse(String.valueOf(rs.getBigDecimal("jzse").setScale(2,BigDecimal.ROUND_HALF_UP)));//计征税额
				form.setHj_jmmj(String.valueOf(rs.getBigDecimal("jmmj").setScale(2,BigDecimal.ROUND_HALF_UP)));//减免面积
				form.setHj_jmse(String.valueOf(rs.getBigDecimal("jmse").setScale(2,BigDecimal.ROUND_HALF_UP)));//减免税额
				form.setHj_ysmj(String.valueOf(rs.getBigDecimal("ysmj").setScale(2,BigDecimal.ROUND_HALF_UP)));//应税面积
				form.setHj_ynse(String.valueOf(rs.getBigDecimal("ynse").setScale(2,BigDecimal.ROUND_HALF_UP)));//应纳税额
				form.setBzlxdm(rs.getString("bzlxdm"));//备注类型代码
				form.setBzms(rs.getString("bzms"));//备注描述
				form.setSysend(rs.getString("sysend"));//适用税额年度
				form.setYhdm(rs.getString("khyhdm"));//开户银行代码
				
				if(form.getNsrlx().equals("0")){
					form.setNsrsbh(rs.getString("SWDJZH")+"-"+rs.getString("ZZJGDM"));//纳税人识别号
					DJXX djxx = (DJXX) getJBSJ(vp);
					form.setNsrsshymc(djxx.getNsrsshymc());
					form.setQydjzclxmc(djxx.getQydjzclxmc());
				}else{
					form.setNsrsbh("");//纳税人识别号
				}
				
			}
			if("".equals(form.getPzjdwh()) || form.getPzjdwh() == null || form.getPzjdwh() == ""){
				form.setWhnd("");
				form.setWh("");
			}else{
				String whnd = form.getPzjdwh().substring(5,9);
				String wh = form.getPzjdwh().substring(10,form.getPzjdwh().length()-1);
				System.out.println(whnd+"=nd="+wh+"=wh=");
				form.setWhnd(whnd);
				form.setWh(wh);
			}
			
			//初始化
	    	getBGXX(form,ud);
			//占地用途
			//Map map = getSBMX(form,conn);	
			//form.setSbmx_map(map);
			form.setSbmxlist(getSBMX_list(form,conn));
			//减免类别
			Map map2 = getJMMX_CX(form.getSbbxlh(),form,conn);
			form.setJmmx_map(map2);
			rs.close();
			ps.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("变更查询异常");
		}finally{
			SfDBResource.freeConnection(conn);
		}

		return form;
	}
    //获取下拉信息
    public Object getBGXX(GdzysSydjxxlrForm form,UserData ud ) throws BaseException {
		//区县代码
		String qxdm = InterfaceDj.getQxdm(ud);;
		//税源类型list
		List sylx_list = new ArrayList();
		//占地用途list
		List zdyt_list = new ArrayList();
		//适用税额list
		List syse_list = new ArrayList();
		//减免类别list
		List jmlb_list = new ArrayList();
	     Connection conn = null;
		try{
			conn = SfDBResource.getConnection();
		//那是纳税人类型
		//税源类型
			sylx_list = getSYLX(conn);
		//纳税人所属行业--企业登记注册类型 20131202
	     //getJBSJ(vp);
		//占地用途
	     zdyt_list = getZDYT(conn);
		//适用税额
	     syse_list = getSYSE_CX(conn,qxdm,form);
		//减免类别
	     jmlb_list =  getJMLB(conn);
	    
	     form.setSylx_list(sylx_list);
	     form.setZdyt_list(zdyt_list);
	     form.setSyse_list(syse_list);
	     form.setJmlb_list(jmlb_list);
		} catch (Exception e) {
			e.printStackTrace();
			 throw new ApplicationException(e.getMessage());
		}finally {
			SfDBResource.freeConnection(conn);
	       }
	   //备注
		
		
		return null;
    	
    }

    
  //修改
    private Object doUpdate(VOPackage vp) throws BaseException  {
    	System.out.println("税源登记变更");
    	GdzysSydjxxlrForm form = (GdzysSydjxxlrForm) vp.getData();
    	UserData ud= vp.getUserData();
	  	Connection conn = null;
		PreparedStatement ps = null;
		String flag="";
		try {
    
    	 //纳税人识别号
		 String swdjzh ="";
		 String zzjgdm ="";
		 String qxdm = InterfaceDj.getQxdm(ud);;
		 if(form.getNsrlx().equals("0")){
			 String nsrsbh =  form.getNsrsbh();
			 int m = nsrsbh.indexOf("-");
			 int n = m+1;
			 swdjzh = nsrsbh.substring(0,m);
			 zzjgdm = nsrsbh.substring(n);
		 }else{
			 DJXX djxx = (DJXX)getZRR(vp);
			 form.setNsrsbh("");
			/* form.setKhyh(djxx.getYhdm());
			 form.setYhzh(djxx.getYhzh());*/
			 form.setKhyh(djxx.getYhmc());
			 form.setYhzh(djxx.getYhzh());
			 form.setYhdm(djxx.getYhdm());
			// zzjgdm = djxx.getSwjgzzjgdm();
			 form.setNsrsshy("8190");//其他未列明的服务
			 form.setQydjzclx("410");//个体工商户
		 }
		 
		 //身份证照类型名称
		 Map map = getSFZZLXMC();
		 if(form.getNsrlx().equals("1")){
			 form.setSfzzlxmc(map.get(form.getSfzzlxdm().toString()).toString());	 
		 }
		 //获取占地用途名称
		 Map zdyt = getZDYTMC();
		 form.setZdytmc((List)zdyt.get("zdytmc"));
		 form.setZdytdm((List)zdyt.get("zdytdm")); 
		 //减免类别
		 Map jmlbmap = getJMLBMC();
		 form.setJmlbdm((List)jmlbmap.get("jmslbdm"));
		 form.setJmlbmc((List)jmlbmap.get("jmslbmc"));
		 //用户名
		 String yhid = ud.getYhid();
	  	
	  	String sql = " update sbdb.SB_JL_GDZYS_SBB set sbzt='10',sjqrr= '',sjqrrq=to_date('','yyyymmdd'),sjqrzt='0',qrr='',qrrq=to_date('','yyyymmdd'),qrzt='0'," +
	  			"sylxdm=?,nsrlx=?,nsrmc=?,jsjdm=?,swdjzh=?,zzjgdm=?,nsrsshy=?,sfzzlx=?,sfzzhm=?,nsrxxdz=?,qydjzclx=?," +
	  			"khyhmc=?,yhzh=?,lxrxm=?,lxdh=?,sfsjsp=?,tdzldz=?,pzjdwh=?,pzjdmj=?,jsxmmc=?,sjzdmj=?,zdsj=to_date(?,'yyyymmdd')," +
	  			"sfjmssb=?,sbjmly=?,jsmj=?,jzse=?,jmmj=?,jmse=?,ysmj=?,ynse=?,zsjgdm=?,lrr=?,lrrq=sysdate,bzms=?,sysend=?,qxdm=?,khyhdm=?" +
	  			" where sbbxlh=?";
	  
			conn = SfDBResource.getConnection();
			
			ps =  conn.prepareStatement(sql);
			ps.setString(1, form.getSylxdm());//税源类型代码
			ps.setString(2, form.getNsrlx());//纳税人类型
			ps.setString(3, form.getNsrmc());//纳税人名称
			ps.setString(4, form.getJsjdm());//计算机代码
			ps.setString(5, swdjzh);//税务登记证号
			ps.setString(6, zzjgdm);//组织机构代码
			ps.setString(7, form.getNsrsshy());//纳税人所属行业
			ps.setString(8, form.getSfzzlxdm());//身份证照类型
			ps.setString(9, form.getSfzzhm());//身份证照号码
			ps.setString(10, form.getNsrxxdz());//纳税人详细地址
			ps.setString(11, form.getQydjzclx());//企业登记注册类型
			ps.setString(12, form.getKhyh());//开户银行名称
			ps.setString(13, form.getYhzh());//银行帐号
			ps.setString(14, form.getLxrxm());//联系人姓名
			ps.setString(15, form.getLxdh());//联系电话
			ps.setString(16, form.getSfsjsp());//是否市局审批
			ps.setString(17, form.getTdzldz());//土地坐落地址
			ps.setString(18, form.getPzjdwh());//批准占地文号
			ps.setString(19, form.getPzjdmj());//批准占地面积
			ps.setString(20, form.getJsxmmc());//建设项目名称
			ps.setString(21, form.getSjzdmj());//实际占地面积
			ps.setString(22, form.getZdsj());//占地时间
			if(form.getSylxdm().equals("0")){
				/*if("".equals(form.getYhdm().trim())){
					ps.setString(23, "0");//是否减免申报
				}else{
					ps.setString(23, form.getYhdm());//是否减免申报
				}*/
				ps.setString(23, "0");//是否减免申报
			}else{
				ps.setString(23, "1");//是否贱卖呢申报
			}
			ps.setString(24, form.getJmsyj());//申报减免理由
			ps.setString(25, form.getHj_jsmj());//计税面积
			ps.setString(26, form.getHj_jzse());//计征税额
			ps.setString(27, form.getHj_jmmj());//减免面积
			ps.setString(28, form.getHj_jmse());//减免税额
			ps.setString(29, form.getHj_ysmj());//应税面积
			ps.setString(30, form.getHj_ynse());//应纳税额
			ps.setString(31, ud.getSsdwdm());//征收机关代码			
			ps.setString(32, yhid);//录入人
			ps.setString(33, form.getBzms());//录入人
			ps.setString(34, form.getSysend());//使用税额年度
			ps.setString(35, qxdm);//区县代码
			if("0".equals(form.getYhdm())){
				ps.setString(36, "");//银行代码
			}else{
				ps.setString(36, form.getYhdm());//银行代码
			}
			ps.setString(37, form.getSbbxlh());//申报表序列号
			ps.executeUpdate();
			ps.close();
			
			//删除申报明细
	    	//deleteSBMX(vp);
			String sql2 = "delete from  SBDB.SB_JL_GDZYS_SBBSBMX where sbbxlh = ?";
			ps= conn.prepareStatement(sql2);
			ps.setString(1, form.getSbbxlh());
			ps.execute();
			ps.close();
				
				
	    	//删除减免明细
//	    	deleteJMMX(vp);
			String sql3 = "delete from  SBDB.SB_JL_GDZYS_SBBJMMX where sbbxlh = ?";
			ps= conn.prepareStatement(sql3);
			ps.setString(1, form.getSbbxlh());
			ps.execute();
			ps.close();
	    	//保存申报明细
//	    	saveSBMX(vp);
			 //占地用途
			 String[] zdyt_sbmx = form.getZdyt();
			//适用税额
		    String[] syse_sbmx = form.getSyse();
		    //计税面积
		    String[] jsmj_sbmx = form.getJsmj();
		    //计征税额
		    String[] jzse_sbmx = form.getJzse();
		    //减免面积
		    String[] jmmj_sbmx = form.getJmmj();
		    //减免税额
		    String[] jmse_sbmx = form.getJmse();
		    //应税面积
		    String[] ysmj_sbmx = form.getYsmj();
		    //应纳税额
		    String[] ynse_sbmx = form.getYnse();
			 
		     //获取基本数据sql
		     String sql4 = "INSERT INTO SBDB.SB_JL_GDZYS_SBBSBMX(SBBXLH,ZDYTDM,SYSE,JSMJ,JZSE,JMMJ,JMSE,YSMJ,YNSE,CJR,CJRQ,LRR,LRRQ,xh,syselxdm) " +
		     		"VALUES(?,?,?,?,?,?,?,?,?,?,SYSDATE,?,SYSDATE,?,?)";
		    
		    	 //获取连接
				ps = conn.prepareStatement(sql4);
				for(int i = 0;i<jsmj_sbmx.length;i++){
					int j = syse_sbmx[i].indexOf("-")+1;
					ps.setString(1, form.getSbbxlh());//申报表序列号
					ps.setString(2, zdyt_sbmx[i]);//占地用途
					ps.setString(3, syse_sbmx[i].substring(j));//适用税额
					ps.setBigDecimal(4, str2Bigdecimal(jsmj_sbmx[i]));//计税面积
					ps.setBigDecimal(5, str2Bigdecimal(jzse_sbmx[i]));//计征税额
					ps.setBigDecimal(6, str2Bigdecimal(jmmj_sbmx[i]));//减免面积
					ps.setBigDecimal(7, str2Bigdecimal(jmse_sbmx[i]));//减免税额
					ps.setBigDecimal(8, str2Bigdecimal(ysmj_sbmx[i]));//应税面积
					ps.setBigDecimal(9, str2Bigdecimal(ynse_sbmx[i]));//应纳税额
					ps.setString(10, yhid);//创建人
					ps.setString(11, yhid);//录入人
					ps.setInt(12, i+1);//录入人
					ps.setString(13, syse_sbmx[i].substring(0,j-1));//录入人
			
					ps.addBatch();    
				}
			
			    //保存
				ps.executeBatch();
			   //关闭preparestatement
			    ps.close();
	    	//保存减免明细
	    	if(form.getFljmse()==null || "".equals(form.getFljmse())){
	    	}else{
	    		//减免类别代码
	    	    String[] jmlbdm_jmmx = form.getJmlbdm_jmmx();
	    	    //减免面积
	    	    String[] jmmj_jmmx = form.getFljmmj();
	    	    //减免税额
	    	    String[] jmse_jmmx = form.getFljmse();
	    	     //插入sql
	    	     String sql5 = "INSERT INTO SBDB.SB_JL_GDZYS_SBBJMMX(SBBXLH,JMSLBDM,JMMJ,JMSE,CJR,CJRQ,LRR,LRRQ) " +
	    	     		"VALUES(?,?,?,?,?,SYSDATE,?,SYSDATE)";
	    	    	 //获取连接
	    			conn = SfDBResource.getConnection();
	    			ps = conn.prepareStatement(sql5);
	    			
	    			for(int i = 0;i<jmmj_jmmx.length;i++){
	    				ps.setString(1, form.getSbbxlh());//申报表序列号
	    				if("".equals(jmlbdm_jmmx[i]) || jmlbdm_jmmx[i]==""){
	    					return null;
	    				}
	    				ps.setString(2, jmlbdm_jmmx[i]);//减免类别代码
	    				ps.setBigDecimal(3, str2Bigdecimal(jmmj_jmmx[i]));//减免面积
	    				ps.setBigDecimal(4, str2Bigdecimal(jmse_jmmx[i]));//减免税额
	    				ps.setString(5, yhid);//创建人
	    				ps.setString(6, yhid);//录入人
	    				ps.addBatch();
	    			}
	    		    ps.executeBatch();
	    		    //关闭preparestatement
	    		    ps.close();
	    	}
	    	
	    	String sql6 = "select cjr,cjrq from sbdb.SB_JL_GDZYS_SBB where sbbxlh=?";
	    	ps = conn.prepareStatement(sql6);
	    	ps.setString(1, form.getSbbxlh());
	    	ResultSet rs = ps.executeQuery();
	    	if(rs.next()){
	    		//创建人
	    		form.setCjr(rs.getString("cjr"));
	    		//创建时间
	    		if(null == rs.getString("cjrq") || "".equals(rs.getString("cjrq"))){
					form.setCjsj("");	
				}else{
					String qrrq = rs.getString("cjrq");
					String rq = qrrq.substring(0,4)+qrrq.substring(5,7)+qrrq.substring(8,10)+qrrq.substring(10,qrrq.indexOf("."));
					form.setCjsj(rq);	
				}
	    	}
	    	rs.close();
	    	ps.close();
	    	
	    	form.setShzt("10");
	    	
		} catch (Exception e) {
			
			e.printStackTrace();
			flag="error";
			form.setFlag(flag);
		}finally{
			SfDBResource.freeConnection(conn);
		}
    	
    	return form;
	}
    //删除申报明细
    public Object deleteSBMX(VOPackage vo) throws BaseException{
    	GdzysSydjxxlrForm form = (GdzysSydjxxlrForm) vo.getData();
    	Connection conn = null;
    	PreparedStatement ps = null;
    	conn = SfDBResource.getConnection();
    	String sql = "delete from  SBDB.SB_JL_GDZYS_SBBSBMX where sbbxlh = ?";
    	try {
			ps= conn.prepareStatement(sql);
			ps.setString(1, form.getSbbxlh());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}
    	return null;
    }
    //删除减免明细
  public Object deleteJMMX(VOPackage vo) throws BaseException{
	  GdzysSydjxxlrForm form = (GdzysSydjxxlrForm) vo.getData();
  	Connection conn = null;
  	PreparedStatement ps = null;
  	conn = SfDBResource.getConnection();
  	String sql = "delete from  SBDB.SB_JL_GDZYS_SBBJMMX where sbbxlh = ?";
  	try {
			ps= conn.prepareStatement(sql);
			ps.setString(1, form.getSbbxlh());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}
  	return null;
    }
    
    //市局审核
    private Object doSjsh(VOPackage vp) throws BaseException  {
    	GdzysSydjxxlrForm form = (GdzysSydjxxlrForm) vp.getData();
    	String flag = "sjsh_success";
    	UserData ud= vp.getUserData();
    	String yhid = ud.getYhid();
	  	Connection conn = null;
		PreparedStatement ps = null;
	  	
	  	String sql = " update sbdb.SB_JL_GDZYS_SBB set sbzt='40',sjqrr= ?,sjqrrq=to_date(?,'yyyymmdd HH24:MI:SS'),sjqrzt='1' where sbbxlh=?";
		 try {
			conn = SfDBResource.getConnection();
			ps =  conn.prepareStatement(sql);
			ps.setString(1, yhid);
			ps.setString(2, form.getSjqrsj()+" 00:00:00");
			ps.setString(3, form.getSbbxlh());
			ps.executeUpdate();
			ps.close();
			
			
			String sql2 = "update sbdb.SB_JL_GDZYS_JMSZM set kjrq=to_date(?,'yyyymmdd HH24:MI:SS') where sbbxlh=?";
			
			ps =  conn.prepareStatement(sql2);
			ps.setString(1, form.getSjqrsj()+" 00:00:00");
			ps.setString(2, form.getSbbxlh());
			ps.executeUpdate();
			ps.close();
			System.out.println("市局审核结束！");
			
			form.setShzt("40");
	    	form.setSjqrrq(form.getSjqrsj()+" 00:00:00");
	    	form.setSjqrr(yhid);
	    	form.setFlag(flag);
	    	form.setJms_flag("jms_flag");
		} catch (Exception e) {
			form.setShzt("30");
			flag="sjsh_false";
			form.setFlag(flag);
			e.printStackTrace();
			
		}finally{
			SfDBResource.freeConnection(conn);
		}
    	
    	return form;
	}
    
    
    //区县审核
    private Object doQxsh(VOPackage vp) throws BaseException  {
    	GdzysSydjxxlrForm form = (GdzysSydjxxlrForm) vp.getData();
    	String flag = "qxsh_success";
    	String jms_flag = "jms_flag";
    	UserData ud= vp.getUserData();
    	String yhid = ud.getYhid();
    	String ssdwdm  = ud.getSsdwdm();
    	System.out.println(ud.getSsdwmc()+"===============ssdwmc=============");
	  	Connection conn = null;
		PreparedStatement ps = null;
	  	
	  	String sql = " update sbdb.SB_JL_GDZYS_SBB set sbzt='30',qrr= ?,qrrq=sysdate,qrzt='1' where sbbxlh=?";
		 try {
			
			conn = SfDBResource.getConnection();
			ps =  conn.prepareStatement(sql);
			ps.setString(1, yhid);
			ps.setString(2, form.getSbbxlh());
			ps.executeUpdate();
			ps.close();
			System.out.println("区县审核结束！");
			//如果不是市局审核，生成减免信息
			
			
			boolean bl= true;
			if("0".equals(form.getSylxdm())){
				jms_flag = "";
				bl = false;
			}
			String[] jmlb = form.getJmlbdm_jmmx();
			if( "".equals(jmlb) || jmlb==null){
				bl=false;
			}else{
				for(int i=0;i<jmlb.length;i++){
					if("01".equals(jmlb[i]) || "02".equals(jmlb[i])){
						jms_flag="";
						bl=false;
					}
				}
			}
			//占地批文为空不能生成减免数据
			if( "".equals(form.getPzjdwh()) || form.getPzjdwh() =="" || form.getPzjdwh()==null){
				bl =false;
			}
			
			if(form.getSfsjsp().equals("0")){
				
				if(bl){
				int nd = new Date(System.currentTimeMillis()).getYear()+1900;
				String qxdm = InterfaceDj.getQxdm(ud);
				String jmszmbh = getJMSZMBH(qxdm,nd,conn);
				System.out.println("减免税编号"+jmszmbh);
				String sql2 = "insert into sbdb.SB_JL_GDZYS_JMSZM (sbbxlh,jmszmbh,qxdm,dybz,zfbz,cjr,cjrq,jzbz,KJRQ) values(?,?,?,'0','0',?,sysdate,'000000',SYSDATE)";
				ps = conn.prepareStatement(sql2);
				ps.setString(1, form.getSbbxlh());
				ps.setString(2, jmszmbh);
				ps.setString(3, ssdwdm);
				ps.setString(4, yhid);
				ps.executeUpdate();
				ps.close();
				}
				//是市局审核--不填写开局日期
			}else{
				if(bl){
				int nd = new Date(System.currentTimeMillis()).getYear()+1900;
				String qxdm = InterfaceDj.getQxdm(ud);
				String jmszmbh = getJMSZMBH_SJ(qxdm,nd,conn);
				System.out.println("减免税编号"+jmszmbh);
				String sql2 = "insert into sbdb.SB_JL_GDZYS_JMSZM (sbbxlh,jmszmbh,qxdm,dybz,zfbz,cjr,cjrq,jzbz) values(?,?,?,'0','0',?,sysdate,'000000')";
				ps = conn.prepareStatement(sql2);
				ps.setString(1, form.getSbbxlh());
				ps.setString(2, jmszmbh);
				ps.setString(3, ssdwdm);
				ps.setString(4, yhid);
				ps.executeUpdate();
				ps.close();
				}
			}
			//减免税已开局
		  	form.setJms_flag(jms_flag);
			
			String qrrq =null;
			String sql3 = " select * from  sbdb.SB_JL_GDZYS_SBB where sbbxlh=?";
				ps =  conn.prepareStatement(sql3);
				ps.setString(1, form.getSbbxlh());
				ResultSet rs = ps.executeQuery();
				if(rs.next()){
					qrrq = rs.getString("qrrq");
				}
				qrrq = qrrq.substring(0, qrrq.indexOf("."));
				String rq = qrrq.substring(0,4)+qrrq.substring(5,7)+qrrq.substring(8,10)+qrrq.substring(10);
			form.setQrrq(rq);
			form.setShzt("30");
			  
	    	form.setQrr(yhid);
	    	form.setFlag(flag);
			
		} catch (Exception e) {
			flag = "qxsh_false";
			form.setFlag(flag);
			form.setShzt("10");
			e.printStackTrace();
		
		}finally{
			SfDBResource.freeConnection(conn);
		}
		
    
    	return form;
	}
    //获取减免税证明编号
    public String getJMSZMBH(String qxdm,int nd,Connection conn) throws BaseException{
    	PreparedStatement ps= null;
    	ResultSet rs = null;
    	StringBuffer buffer = new StringBuffer();
    	String qxfjsx = "";
    	int bh = 1;
    	String sys_nd="";
    	String sql = "select qxfjsx from dmdb.GD_DM_QXFJDM where qxdm = ?";
    	String sql_nd = "select extract(year from sysdate) nd　from dual";
    	try {
    		
    		ps = conn.prepareStatement(sql_nd);
    		rs = ps.executeQuery();
    		if(rs.next()){
    			sys_nd = rs.getString("nd");
    		}
    		
    		rs.close();
    		ps.close();
    		
			ps = conn.prepareStatement(sql);
			ps.setString(1, qxdm);
			rs = ps.executeQuery();
			if(rs.next()){
				qxfjsx = rs.getString("qxfjsx");
			}
			rs.close();
			ps.close();
			String sql2 = " select bh from sbdb.SB_JL_GDZYS_JMSZMBH where qxdm =? and nd = ?";
			ps = conn.prepareStatement(sql2);
			ps.setString(1, qxdm);
			ps.setString(2, sys_nd);
			rs = ps.executeQuery();
			
				//有查询
			if(rs.next()){
				bh = rs.getInt("bh");
				//修改状态
				String sql4 = "update sbdb.SB_JL_GDZYS_JMSZMBH set bh = ?  where qxdm =? and nd= ?  and bh=?";
				try {
					ps.close();
					ps = conn.prepareStatement(sql4);
					ps.setInt(1, bh+1);
					ps.setString(2, qxdm);
					ps.setString(3, sys_nd);
					ps.setInt(4, bh);
					int num = ps.executeUpdate();
					if(num<0){
						throw new ApplicationException("获取减免税证明编号时发生异常，请重新保存！");
					}
				}catch (Exception ex) {
						ex.printStackTrace();
						throw new ApplicationException("获取减免税证明编号时发生异常，请重新保存！");
					}		
				
				//没有插入
			}else{
				ps.close();
				String sql3 = "insert into sbdb.SB_JL_GDZYS_JMSZMBH (qxdm,nd,bh,szt) values(?,?,'2','0')";
				ps = conn.prepareStatement(sql3);
				ps.setString(1, qxdm);
				ps.setString(2, sys_nd);
				ps.executeUpdate();
				ps.close();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}
    	
    	buffer.append(qxfjsx);
    	buffer.append("耕税减免（");
    	buffer.append(sys_nd);
    	buffer.append("）");
    	buffer.append(bh);
    	
    	return buffer.toString();
    }
    
  //获取减免税证明编号
    public String getJMSZMBH_SJ(String qxdm,int nd,Connection conn) throws BaseException{
    	PreparedStatement ps= null;
    	ResultSet rs = null;
    	StringBuffer buffer = new StringBuffer();
    	String qxfjsx = "";
    	int bh = 1;
    	String sys_nd="";
    	String sql = "select qxfjsx from dmdb.GD_DM_QXFJDM where qxdm = '90'";
    	String sql_nd = "select extract(year from sysdate) nd　from dual";
    	try {
    		
    		ps = conn.prepareStatement(sql_nd);
    		rs = ps.executeQuery();
    		if(rs.next()){
    			sys_nd = rs.getString("nd");
    		}
    		
    		rs.close();
    		ps.close();
    		
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			if(rs.next()){
				qxfjsx = rs.getString("qxfjsx");
			}
			rs.close();
			ps.close();
			String sql2 = " select bh from sbdb.SB_JL_GDZYS_JMSZMBH where qxdm =? and nd = ?";
			ps = conn.prepareStatement(sql2);
			//ps.setString(1, qxdm);
			ps.setString(1, "90");
			ps.setString(2, sys_nd);
			rs = ps.executeQuery();
			
				//有查询
			if(rs.next()){
				bh = rs.getInt("bh");
				//修改状态
				String sql4 = "update sbdb.SB_JL_GDZYS_JMSZMBH set bh = ?  where qxdm =? and nd= ?  and bh=?";
				try {
					ps.close();
					ps = conn.prepareStatement(sql4);
					ps.setInt(1, bh+1);
					//ps.setString(2, qxdm);
					ps.setString(2, "90");
					ps.setString(3, sys_nd);
					ps.setInt(4, bh);
					int num = ps.executeUpdate();
					if(num<0){
						throw new ApplicationException("获取减免税证明编号时发生异常，请重新保存！");
					}
				}catch (Exception ex) {
						ex.printStackTrace();
						throw new ApplicationException("获取减免税证明编号时发生异常，请重新保存！");
					}		
				
				//没有插入
			}else{
				ps.close();
				String sql3 = "insert into sbdb.SB_JL_GDZYS_JMSZMBH (qxdm,nd,bh,szt) values(?,?,'2','0')";
				ps = conn.prepareStatement(sql3);
				//ps.setString(1, qxdm);
				ps.setString(1, "90");
				ps.setString(2, sys_nd);
				ps.executeUpdate();
				ps.close();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}
    	
    	buffer.append(qxfjsx);
    	buffer.append("耕税减免（");
    	buffer.append(sys_nd);
    	buffer.append("）");
    	buffer.append(bh);
    	
    	return buffer.toString();
    }
    
    
	private Object getZRR(VOPackage vp) throws BaseException {
    	GdzysSydjxxlrForm form = (GdzysSydjxxlrForm) vp.getData();
	  	Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
	  	
	  	DJXX djxx = new DJXX();
	  	djxx.setDm("0");
	  	String sql = " select * from djdb.dj_jl_zrrjbsj WHERE zjlxdm =? and zjhm = ? ";
	  	String sql2 = "select * from djdb.dj_jl_yhzh where jsjdm=?";
		 try {
			conn = SfDBResource.getConnection();
			ps =  conn.prepareStatement(sql);
			ps.setString(1, form.getSfzzlxdm());
			ps.setString(2, form.getSfzzhm());
			rs = ps.executeQuery();
			if(rs.next()){
				djxx.setDm("1");
				djxx.setNsrmc(rs.getString("nsrmc"));
				djxx.setNsrxxdz(rs.getString("txdz"));
				djxx.setJsjdm(rs.getString("jsjdm"));
				//djxx.setSwjgzzjgdm(rs.getString("swjgzzjgdm"));
			}	
			rs.close();
			ps.close();
			
			ps=conn.prepareStatement(sql2);
			ps.setString(1, djxx.getJsjdm());
			rs = ps.executeQuery();
			while(rs.next()){
	             if (rs.getString("jbzhbs").equals(CodeConstant.SMSB_JBZHBS))
	             {
            		djxx.setYhdm(rs.getString("yhdm"));
    				djxx.setYhmc(rs.getString("khyhmc"));
    				djxx.setYhzh(rs.getString("zh"));
	                 break;
	             }
			
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}
    	return djxx;
	}

    
    
    //减免税依据
  private Object getJMSYJ(VOPackage vp) throws BaseException {
	  	GdzysSydjxxlrForm form = (GdzysSydjxxlrForm) vp.getData();
	  	Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
	  	String dm ="";
	  	StringBuffer yj =  new StringBuffer();
	  	for(int i = 0;i<form.getJmlbdm_jmmx().length;i++){
	  		if(i == form.getJmlbdm_jmmx().length-1){
	  			dm +=form.getJmlbdm_jmmx()[i];
	  		}else{
	  			dm +=form.getJmlbdm_jmmx()[i]+",";
	  		}
	  	}
	  	System.out.println("减免税类别代码"+dm);
	  	if("".equals(dm) || dm == "" || dm == null){
	  		yj.append("");
	  	}else{
		String sql = "select * from dmdb.GD_DM_JMSYJ where jmsyjdm in( select distinct jmsyjdm  from dmdb.GD_DM_JMSLB a where jmslbdm in("+dm+"))";
		 try {
			conn = SfDBResource.getConnection();
			ps =  conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				yj.append(rs.getString("jmsyjmc"));
				yj.append(" ");
			}	
			rs.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}
	  }
	  return yj;
	}
	//AJAX获取适用税额
    private Object getSYSE(VOPackage vp) throws BaseException {
    	GdzysSydjxxlrForm form = (GdzysSydjxxlrForm) vp.getData();
    	List list = new ArrayList();
    	UserData ud = vp.getUserData();
    	String qxdm = InterfaceDj.getQxdm(ud);;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT LX.SYSELXMC,LX.SYSELXDM,SE.SYSE,SE.ND FROM DMDB.GD_DM_SYSELX LX,DMDB.GD_DM_SYSE SE " +
 		"WHERE LX.SYSELXDM = SE.SYSELXDM AND LX.ZXBZ = '0' AND SE.ZXBZ = '0' " +
 		" AND SE.QXDM = ?" +
 		"AND SE.ND = ?";
		 try {
			conn = SfDBResource.getConnection();
			ps =  conn.prepareStatement(sql);
			
			ps.setString(1, qxdm);
			ps.setString(2, form.getSysend());
			rs = ps.executeQuery();
			while(rs.next()){
				LabelValueBean bean = new LabelValueBean(rs.getString("SYSE"),rs.getString("SYSELXDM")+"-"+rs.getString("SYSE"));
   				list.add(bean);
			}	
			rs.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}
		
		return list;
	}
	//保存税源登记信息
	private Object doSave(VOPackage vp)throws BaseException {
		 GdzysSydjxxlrForm form = (GdzysSydjxxlrForm) vp.getData();
		 UserData ud = vp.getUserData();
		 //纳税人识别号
		 String swdjzh ="";
		 String zzjgdm ="";
		 PreparedStatement ps = null;
	     Connection conn = null;
		 try {
		 String qxdm = InterfaceDj.getQxdm(ud);;
		 if(form.getNsrlx().equals("0")){
			 String nsrsbh =  form.getNsrsbh();
			 int m = nsrsbh.indexOf("-");
			 int n = m+1;
			 swdjzh = nsrsbh.substring(0,m);
			 zzjgdm = nsrsbh.substring(n);
			 form.setSfzzlxdm("");
			 form.setSfzzhm("");
		 }else{
			 DJXX djxx = (DJXX)getZRR(vp);
			 form.setNsrsbh("");
			/* form.setKhyh(djxx.getYhdm());
			 form.setYhzh(djxx.getYhzh());*/
			 form.setKhyh(djxx.getYhmc());
			 form.setYhzh(djxx.getYhzh());
			 form.setYhdm(djxx.getYhdm());
			 zzjgdm = djxx.getSwjgzzjgdm();
			 form.setNsrsshy("8190");//其他未列明的服务
			 form.setQydjzclx("410");//个体工商户
		 }
		 
		 //身份证照类型名称
		 Map map = getSFZZLXMC();
		 if(form.getNsrlx().equals("1")){
			 form.setSfzzlxmc(map.get(form.getSfzzlxdm().toString()).toString());	 
		 }
		 //获取占地用途名称
		 Map zdyt = getZDYTMC();
		 form.setZdytmc((List)zdyt.get("zdytmc"));
		 form.setZdytdm((List)zdyt.get("zdytdm")); 
		 //减免类别
		 Map jmlbmap = getJMLBMC();
		 form.setJmlbdm((List)jmlbmap.get("jmslbdm"));
		 form.setJmlbmc((List)jmlbmap.get("jmslbmc"));
		 //用户名
		 String yhid = ud.getYhid();
		 form.setCjr(yhid);
	     //保存sql
	     String sql = "INSERT INTO SBDB.SB_JL_GDZYS_SBB(SBBXLH,SYLXDM,NSRLX,NSRMC,JSJDM," +
	     		"SWDJZH," +
	     		"ZZJGDM," +
	     		"NSRSSHY,SFZZLX,SFZZHM,NSRXXDZ,QYDJZCLX,KHYHMC,YHZH,LXRXM," +
	     		"LXDH," +
	     		"SFSJSP," +
	     		"TDZLDZ,PZJDWH,PZJDMJ,JSXMMC," +
	     		"SJZDMJ," +
	     		"ZDSJ," +
	     		"SBJMLY,JSMJ,JZSE," +
	     		"JMMJ,JMSE,YNSE," +
	     		"CJR,CJRQ,LRR,LRRQ,QRZT,SBZT,qxdm,ZSJGDM,sysend,sfjmssb,ysmj,khyhdm) " +
	     		"VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,to_date(?,'yyyymmdd'),?,?,?,?,?,?,?,SYSDATE,?,SYSDATE,'0','10',?,?,?,?,?,?)";
	   
	    	 //获取连接
			conn = SfDBResource.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, form.getSbbxlh());//申报表序列号
			ps.setString(2, form.getSylxdm());//税源类型代码
			ps.setString(3, form.getNsrlx());//纳税人类型
			ps.setString(4, form.getNsrmc());//纳税人名称
			ps.setString(5, form.getJsjdm());//计算机代码
			ps.setString(6, swdjzh);//税务登记证号
			ps.setString(7, zzjgdm);//组织机构代码
			ps.setString(8, form.getNsrsshy());//纳税人所属行业
			ps.setString(9, form.getSfzzlxdm());//身份证照类型
			ps.setString(10, form.getSfzzhm());//身份证照号码
			ps.setString(11, form.getNsrxxdz());//纳税人详细地址
			ps.setString(12, form.getQydjzclx());//企业登记注册类型
			ps.setString(13, form.getKhyh());//开户银行名称
			ps.setString(14, form.getYhzh());//银行帐号
			ps.setString(15, form.getLxrxm());//联系人姓名
			ps.setString(16, form.getLxdh());//联系电话
			ps.setString(17, form.getSfsjsp());//是否市局审批
			ps.setString(18, form.getTdzldz());//土地坐落地址
			ps.setString(19, form.getPzjdwh());//批准占地文号
			ps.setString(20, form.getPzjdmj());//批准占地面积
			ps.setString(21, form.getJsxmmc());//建设项目名称
			ps.setString(22, form.getSjzdmj());//实际占地面积
			ps.setString(23, form.getZdsj());//占地时间
			ps.setString(24, form.getJmsyj());//申报减免理由
			ps.setString(25, form.getHj_jsmj());//计税面积
			ps.setString(26, form.getHj_jzse());//计征税额
			ps.setString(27, form.getHj_jmmj());//减免面积
			ps.setString(28, form.getHj_jmse());//减免税额
			ps.setString(29, form.getHj_ynse());//应纳税额
			ps.setString(30, yhid);//创建人
//			ps.setString(31, form.getBzlxdm());//备注类型代码
//			ps.setString(32, form.getBzms());//备注描述
			ps.setString(31, yhid);//录入人
			ps.setString(32, qxdm);//区县代码
			ps.setString(33, ud.getSsdwdm());//征收机关代码
			ps.setString(34, form.getSysend());//使用税额年度
			if(form.getSylxdm().equals("0")){
				ps.setString(35, "0");//是否减免申报
			}else{
				ps.setString(35, "1");//是否减免申报
			}
			ps.setString(36, form.getHj_ysmj());//应税面积
			if("0".equals(form.getYhdm())){
				ps.setString(37, "");//银行代码
			}else{
				ps.setString(37, form.getYhdm());//银行代码
			}
		    //保存
		     ps.execute();
		    //关闭preparestatement
		    ps.close();
			System.out.println("保存申报结束");
			
			//保存申报明细
			String[] str_zdyt = form.getZdyt();
			if(str_zdyt.length<1){
				
			}else{
				 //占地用途
				 String[] zdyt_sbmx = form.getZdyt();
				//适用税额
			    String[] syse_sbmx = form.getSyse();
			    //计税面积
			    String[] jsmj_sbmx = form.getJsmj();
			    //计征税额
			    String[] jzse_sbmx = form.getJzse();
			    //减免面积
			    String[] jmmj_sbmx = form.getJmmj();
			    //减免税额
			    String[] jmse_sbmx = form.getJmse();
			    //应税面积
			    String[] ysmj_sbmx = form.getYsmj();
			    //应纳税额
			    String[] ynse_sbmx = form.getYnse();
				  
			     String sql2 = "INSERT INTO SBDB.SB_JL_GDZYS_SBBSBMX(SBBXLH,ZDYTDM,SYSE,JSMJ,JZSE,JMMJ,JMSE,YSMJ,YNSE,CJR,CJRQ,LRR,LRRQ,xh,syselxdm) " +
			     		"VALUES(?,?,?,?,?,?,?,?,?,?,SYSDATE,?,SYSDATE,?,?)";
			    
			    	 //获取连接
					ps = conn.prepareStatement(sql2);
			
					for(int i = 0;i<jsmj_sbmx.length;i++){
						int j = syse_sbmx[i].indexOf("-")+1;
						ps.setString(1, form.getSbbxlh());//申报表序列号
						ps.setString(2, zdyt_sbmx[i]);//占地用途
						ps.setString(3, syse_sbmx[i].substring(j));//适用税额
						ps.setBigDecimal(4, str2Bigdecimal(jsmj_sbmx[i]));//计税面积
						ps.setBigDecimal(5, str2Bigdecimal(jzse_sbmx[i]));//计征税额
						ps.setBigDecimal(6, str2Bigdecimal(jmmj_sbmx[i]));//减免面积
						ps.setBigDecimal(7, str2Bigdecimal(jmse_sbmx[i]));//减免税额
						ps.setBigDecimal(8, str2Bigdecimal(ysmj_sbmx[i]));//应税面积
						ps.setBigDecimal(9, str2Bigdecimal(ynse_sbmx[i]));//应纳税额
						ps.setString(10, yhid);//创建人
						ps.setString(11, yhid);//录入人
						ps.setInt(12, i+1);//录入人
						ps.setString(13, syse_sbmx[i].substring(0,j-1));//录入人
						
						ps.addBatch();    
					}
				
				    //保存
					ps.executeBatch();
				   //关闭preparestatement
				    ps.close();
				
			}
			//保存减免明细
			String[] fljmmj = form.getFljmmj();
			if("".equals(fljmmj) || fljmmj==null || fljmmj.length<1){
			}else{
				//减免类别代码
			    String[] jmlbdm_jmmx = form.getJmlbdm_jmmx();
			    //减免面积
			    String[] jmmj_jmmx = form.getFljmmj();
			    //减免税额
			    String[] jmse_jmmx = form.getFljmse();
			     //插入sql
			     String sql3 = "INSERT INTO SBDB.SB_JL_GDZYS_SBBJMMX(SBBXLH,JMSLBDM,JMMJ,JMSE,CJR,CJRQ,LRR,LRRQ) " +
			     		"VALUES(?,?,?,?,?,SYSDATE,?,SYSDATE)";
			    	 //获取连接
					ps = conn.prepareStatement(sql3);
					for(int i = 0;i<jmmj_jmmx.length;i++){
						ps.setString(1, form.getSbbxlh());//申报表序列号
						ps.setString(2, jmlbdm_jmmx[i]);//减免类别代码
						ps.setBigDecimal(3, str2Bigdecimal(jmmj_jmmx[i]));//减免面积
						ps.setBigDecimal(4, str2Bigdecimal(jmse_jmmx[i]));//减免税额
						ps.setString(5, yhid);//创建人
						ps.setString(6, yhid);//录入人
						ps.addBatch();
					}
				    ps.executeBatch();
				    //关闭preparestatement
				    ps.close();
			}
			String sql4 = "select sysdate from dual";
			ps = conn.prepareStatement(sql4);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				if(null == rs.getString("sysdate") || "".equals(rs.getString("sysdate"))){
					form.setCjsj("");	
				}else{
					String qrrq = rs.getString("sysdate");
					String rq = qrrq.substring(0,4)+qrrq.substring(5,7)+qrrq.substring(8,10)+qrrq.substring(10,qrrq.indexOf("."));
					form.setCjsj(rq);	
				}
				
			}
			
			
		} catch (Exception e) {
			
			form.setErrors("error");
			e.printStackTrace();
			System.out.println("保存申报发生异常信息");
			
			
		}finally {
   	     	SfDBResource.freeConnection(conn);
   	     	
	       }
	
	
		form.setShzt("10");
		form.setQrrq(null);
		form.setSjqrrq(null);
		return form;
	}
	
	//保存备注信息
	private Object doRemark(VOPackage vp)throws BaseException {
		GdzysSydjxxlrForm form = (GdzysSydjxxlrForm) vp.getData();
		//申报序列号
		String sbbxlh = form.getSbbxlh();
		//备注类型代码
		String bzlxdm = form.getBzlxdm();
		//备注描述
		String bzms = form.getBzms();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuffer buffer = new StringBuffer();
		conn = SfDBResource.getConnection();
		
		String sql2 = "select bzms from SBDB.SB_JL_GDZYS_SBB where sbbxlh = ?";
		String sql = "UPDATE SBDB.SB_JL_GDZYS_SBB SET BZLXDM = ?,BZMS = ? WHERE SBBXLH = ?";
		try {
			ps = conn.prepareStatement(sql2);
			ps.setString(1, sbbxlh);
			rs = ps.executeQuery();
			if(rs.next()){
				if(rs.getString("bzms") ==null || "".equals(rs.getString("bzms")) || rs.getString("bzms") ==""){
					buffer.append("");
				}else{
					if(rs.getString("bzms").trim().length()>1){
						buffer.append(rs.getString("bzms").trim());
						buffer.append(";");
					}
					
				}
			}
			ps.close();
			//根据备注类型代码获取备注类型名称
			String bzlxmc = getBZLXMC(bzlxdm);
			buffer.append(bzlxmc);
			buffer.append(":");
			ps = conn.prepareStatement(sql);
			ps.setString(1, bzlxdm);
			ps.setString(2, buffer.append(bzms).toString());
			ps.setString(3, sbbxlh);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}finally {
			SfDBResource.freeConnection(conn);
	    }
		form.setBzms(buffer.toString());
		return form;
	}
	//获取备注类型名称
	private String getBZLXMC(String bzlxdm)throws BaseException {
		//备注描述
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String bzlxmc = "";
		conn = SfDBResource.getConnection();
		String sql = "select bzlxmc from DMDB.GD_DM_BZLX where bzlxdm = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, bzlxdm);
			rs = ps.executeQuery();
			if(rs.next()){
				bzlxmc= rs.getString("bzlxmc");
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}finally {
			SfDBResource.freeConnection(conn);
	    }
		return bzlxmc;
	}
	

	
	//获取打印信息
	private Object getPrintSBB(VOPackage vp)throws BaseException {
		
		GdzysSydjxxlrForm form = (GdzysSydjxxlrForm) vp.getData();
		//确认序列号
		String sbbxlh = form.getSbbxlh();
		//登记信息
		DJXX djxx = new DJXX();
		if(form.getNsrlx().equals("0")){
			djxx = (DJXX) getJBSJ(vp);
			form.setNsrmc(djxx.getNsrmc());
			form.setNsrxxdz(djxx.getNsrxxdz());
			form.setQydjzclxmc(djxx.getQydjzclxmc());
			form.setNsrsshymc(djxx.getNsrsshymc());
			form.setSfzzlxmc("------------------");
			form.setSfzzhm("-----------------");
		}else{
			djxx = (DJXX) getZRR(vp);
			form.setNsrmc(djxx.getNsrmc());
			form.setNsrxxdz(djxx.getNsrxxdz());
			form.setKhyh(djxx.getYhmc());
			form.setYhzh(djxx.getYhzh());
			form.setYhdm(djxx.getYhdm());
			form.setQydjzclxmc("个体工商户");
			form.setNsrsshymc("其他未列明服务");
		
		}
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		conn = SfDBResource.getConnection();
		String sql = "SELECT * FROM SBDB.SB_JL_GDZYS_SBB WHERE SBBXLH = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, sbbxlh);
			rs = ps.executeQuery();
			if(rs.next()){
				form.setSbbxlh(rs.getString("SBBXLH"));//申报序列号
				//form.setSfzzhm(rs.getString("SFZZHM"));//身份证照号码
				form.setSylxdm(rs.getString("sylxdm"));//税源类型代码
				if(form.getNsrlx().equals("0")){
					form.setJsjdm(rs.getString("JSJDM"));//计算机代码
					form.setNsrsbh(rs.getString("SWDJZH")+"-"+rs.getString("ZZJGDM"));//纳税人识别号
				}else{
					form.setNsrsbh("-");//纳税人识别号
				}
				
				form.setKhyh(rs.getString("KHYHMC"));//开户银行名称
				form.setYhzh(rs.getString("YHZH"));//银行帐号
				form.setLxrxm(rs.getString("LXRXM"));//联系人姓名
				form.setLxdh(rs.getString("LXDH"));//联系电话
				form.setPzjdwh(rs.getString("PZJDWH"));//批准占地文号
				SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
				form.setZdsj(df.format(rs.getDate("ZDSJ")));//占地时间
				if(rs.getString("PZJDMJ") == null || "".equals(rs.getString("PZJDMJ"))){
					form.setPzjdmj("");//批准占地面积
				}else{
					form.setPzjdmj(String.valueOf(rs.getBigDecimal("PZJDMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));//批准占地面积
				}
				
				form.setSjzdmj(String.valueOf(rs.getBigDecimal("SJZDMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));//实际占地面积
				
				
				
				form.setTdzldz(rs.getString("TDZLDZ"));//土地坐落地址
				form.setJsxmmc(rs.getString("JSXMMC"));//建设项目名称
				form.setHj_jsmj(String.valueOf(rs.getBigDecimal("jsmj").setScale(2,BigDecimal.ROUND_HALF_UP)));//计税面积
				form.setHj_jzse(String.valueOf(rs.getBigDecimal("jzse").setScale(2,BigDecimal.ROUND_HALF_UP)));//计征税额
				form.setHj_jmmj(String.valueOf(rs.getBigDecimal("jmmj").setScale(2,BigDecimal.ROUND_HALF_UP)));//减免面积
				form.setHj_jmse(String.valueOf(rs.getBigDecimal("jmse").setScale(2,BigDecimal.ROUND_HALF_UP)));//减免税额
				form.setHj_ynse(String.valueOf(rs.getBigDecimal("ynse").setScale(2,BigDecimal.ROUND_HALF_UP)));//应纳税额
				Date date = rs.getDate("cjrq");//创建日期
				int nian = date.getYear()+1900;
				int yue = date.getMonth()+1;
				int tian = date.getDate();
				System.out.println("date"+date+"nian"+nian+"yue"+yue+"tian"+tian);
				form.setNian(nian);
				form.setYue(yue);
				form.setTian(tian);
				
				if(form.getNsrlx().equals("1")){
					Map sfzzlxmc = getSFZZLXMC();
					form.setSfzzlxmc(sfzzlxmc.get(rs.getString("sfzzlx")).toString());//身份证照名称
					form.setSfzzhm(rs.getString("SFZZHM"));//身份证照号码
				}
				
				
			}
			//占地用途
			Map map = getSBMX(form,conn);	
			form.setSbmx_map(map);
			//减免类别
			Map map2 = getJMMX(sbbxlh,form,conn);
			form.setJmmx_map(map2);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}finally {
			SfDBResource.freeConnection(conn);
	    }
		return form;
	}
/* 
 * 开始
 * */
	public List getSBMX_list(GdzysSydjxxlrForm form,Connection conn) throws BaseException  {
		String sbbxlh = form.getSbbxlh();
		
		List list = new ArrayList();
	
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM SBDB.SB_JL_GDZYS_SBBSBMX WHERE SBBXLH = ? order by xh";	
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, sbbxlh);
			rs = ps.executeQuery();
			while(rs.next()){
				SBMXModel model = new SBMXModel();
				model.setSyse(rs.getString("syse"));
				model.setZdyt(rs.getString("zdytdm"));
				model.setJsmj(String.valueOf(rs.getBigDecimal("jsmj").setScale(2,BigDecimal.ROUND_HALF_UP)));
				model.setJzse(String.valueOf(rs.getBigDecimal("jzse").setScale(2,BigDecimal.ROUND_HALF_UP)));
				model.setJmmj(String.valueOf(rs.getBigDecimal("jmmj").setScale(2,BigDecimal.ROUND_HALF_UP)));
				model.setJmse(String.valueOf(rs.getBigDecimal("jmse").setScale(2,BigDecimal.ROUND_HALF_UP)));
				model.setYsmj(String.valueOf(rs.getBigDecimal("ysmj").setScale(2,BigDecimal.ROUND_HALF_UP)));
				model.setYnse(String.valueOf(rs.getBigDecimal("ynse").setScale(2,BigDecimal.ROUND_HALF_UP)));
				list.add(model);
				
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}
		return list;
	}
	
/*结束*/
	
	//获取打印申报明细
	
	public Map getSBMX(GdzysSydjxxlrForm form,Connection conn) throws BaseException  {
		String sbbxlh = form.getSbbxlh();
		
		Map map = new HashMap();
		//交通
		List jt = new ArrayList();
		//工业
		List gy = new ArrayList();
		//商业
		List sy = new ArrayList();
		//住宅
		List zz = new ArrayList();
		//居民住房
		List jm = new ArrayList();
		//其他
		List qt = new ArrayList();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM SBDB.SB_JL_GDZYS_SBBSBMX WHERE SBBXLH = ? order by zdytdm";	
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, sbbxlh);
			rs = ps.executeQuery();
			while(rs.next()){
				SBMXModel model = new SBMXModel();
				if("00".equals(rs.getString("zdytdm"))){
					model.setSyse(rs.getString("syse"));
					model.setJsmj(String.valueOf(rs.getBigDecimal("jsmj").setScale(2,BigDecimal.ROUND_HALF_UP)));
					model.setJzse(String.valueOf(rs.getBigDecimal("jzse").setScale(2,BigDecimal.ROUND_HALF_UP)));
					model.setJmmj(String.valueOf(rs.getBigDecimal("jmmj").setScale(2,BigDecimal.ROUND_HALF_UP)));
					model.setJmse(String.valueOf(rs.getBigDecimal("jmse").setScale(2,BigDecimal.ROUND_HALF_UP)));
					model.setYsmj(String.valueOf(rs.getBigDecimal("ysmj").setScale(2,BigDecimal.ROUND_HALF_UP)));
					model.setYnse(String.valueOf(rs.getBigDecimal("ynse").setScale(2,BigDecimal.ROUND_HALF_UP)));
					jt.add(model);
				}else if("01".equals(rs.getString("zdytdm"))){
					//
					model.setSyse(rs.getString("syse"));
					model.setJsmj(String.valueOf(rs.getBigDecimal("jsmj").setScale(2,BigDecimal.ROUND_HALF_UP)));
					model.setJzse(String.valueOf(rs.getBigDecimal("jzse").setScale(2,BigDecimal.ROUND_HALF_UP)));
					model.setJmmj(String.valueOf(rs.getBigDecimal("jmmj").setScale(2,BigDecimal.ROUND_HALF_UP)));
					model.setJmse(String.valueOf(rs.getBigDecimal("jmse").setScale(2,BigDecimal.ROUND_HALF_UP)));
					model.setYsmj(String.valueOf(rs.getBigDecimal("ysmj").setScale(2,BigDecimal.ROUND_HALF_UP)));
					model.setYnse(String.valueOf(rs.getBigDecimal("ynse").setScale(2,BigDecimal.ROUND_HALF_UP)));
					gy.add(model);
				}else if("02".equals(rs.getString("zdytdm"))){
					//
					model.setSyse(rs.getString("syse"));
					model.setJsmj(String.valueOf(rs.getBigDecimal("jsmj").setScale(2,BigDecimal.ROUND_HALF_UP)));
					model.setJzse(String.valueOf(rs.getBigDecimal("jzse").setScale(2,BigDecimal.ROUND_HALF_UP)));
					model.setJmmj(String.valueOf(rs.getBigDecimal("jmmj").setScale(2,BigDecimal.ROUND_HALF_UP)));
					model.setJmse(String.valueOf(rs.getBigDecimal("jmse").setScale(2,BigDecimal.ROUND_HALF_UP)));
					model.setYsmj(String.valueOf(rs.getBigDecimal("ysmj").setScale(2,BigDecimal.ROUND_HALF_UP)));
					model.setYnse(String.valueOf(rs.getBigDecimal("ynse").setScale(2,BigDecimal.ROUND_HALF_UP)));
					sy.add(model);
				}else if("03".equals(rs.getString("zdytdm"))){
					//学校
					model.setSyse(rs.getString("syse"));
					model.setJsmj(String.valueOf(rs.getBigDecimal("jsmj").setScale(2,BigDecimal.ROUND_HALF_UP)));
					model.setJzse(String.valueOf(rs.getBigDecimal("jzse").setScale(2,BigDecimal.ROUND_HALF_UP)));
					model.setJmmj(String.valueOf(rs.getBigDecimal("jmmj").setScale(2,BigDecimal.ROUND_HALF_UP)));
					model.setJmse(String.valueOf(rs.getBigDecimal("jmse").setScale(2,BigDecimal.ROUND_HALF_UP)));
					model.setYsmj(String.valueOf(rs.getBigDecimal("ysmj").setScale(2,BigDecimal.ROUND_HALF_UP)));
					model.setYnse(String.valueOf(rs.getBigDecimal("ynse").setScale(2,BigDecimal.ROUND_HALF_UP)));
					zz.add(model);
				}else if("04".equals(rs.getString("zdytdm"))){
					model.setSyse(rs.getString("syse"));
					model.setJsmj(String.valueOf(rs.getBigDecimal("jsmj").setScale(2,BigDecimal.ROUND_HALF_UP)));
					model.setJzse(String.valueOf(rs.getBigDecimal("jzse").setScale(2,BigDecimal.ROUND_HALF_UP)));
					model.setJmmj(String.valueOf(rs.getBigDecimal("jmmj").setScale(2,BigDecimal.ROUND_HALF_UP)));
					model.setJmse(String.valueOf(rs.getBigDecimal("jmse").setScale(2,BigDecimal.ROUND_HALF_UP)));
					model.setYsmj(String.valueOf(rs.getBigDecimal("ysmj").setScale(2,BigDecimal.ROUND_HALF_UP)));
					model.setYnse(String.valueOf(rs.getBigDecimal("ynse").setScale(2,BigDecimal.ROUND_HALF_UP)));
					jm.add(model);
				}else if("05".equals(rs.getString("zdytdm"))){
					//
					model.setSyse(rs.getString("syse"));
					model.setJsmj(String.valueOf(rs.getBigDecimal("jsmj").setScale(2,BigDecimal.ROUND_HALF_UP)));
					model.setJzse(String.valueOf(rs.getBigDecimal("jzse").setScale(2,BigDecimal.ROUND_HALF_UP)));
					model.setJmmj(String.valueOf(rs.getBigDecimal("jmmj").setScale(2,BigDecimal.ROUND_HALF_UP)));
					model.setJmse(String.valueOf(rs.getBigDecimal("jmse").setScale(2,BigDecimal.ROUND_HALF_UP)));
					model.setYsmj(String.valueOf(rs.getBigDecimal("ysmj").setScale(2,BigDecimal.ROUND_HALF_UP)));
					model.setYnse(String.valueOf(rs.getBigDecimal("ynse").setScale(2,BigDecimal.ROUND_HALF_UP)));
					qt.add(model);
				}else{
					System.out.println("减免类别发生错误！");
				}
			}
			map.put("00", jt);
			map.put("01",gy);
			map.put("02", sy);
			map.put("03", zz);
			map.put("04", jm);
			map.put("05", qt);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}
		return map;
	}
	
	

	//确认税源登记信息
	private Object doConfirm(VOPackage vp)throws BaseException {
		GdzysSydjxxlrForm form = (GdzysSydjxxlrForm) vp.getData();
		UserData ud = vp.getUserData();
		//确认人
		String yhid = ud.getYhid();
		//确认序列号
		String[] sbbxlh = form.getSbbxlh_ary();
		Connection conn = null;
		PreparedStatement ps = null;
		conn = SfDBResource.getConnection();
		String sql = "UPDATE SBDB.SB_JL_GDZYS_SBB SET QRR = ?,QRSJ = SYSDATE,QRZT = '1' WHERE SBBXLH = ?";
		try {
			ps = conn.prepareStatement(sql);
			for(int i=0;i<sbbxlh.length;i++){
				ps.setString(1, yhid);
				ps.setString(2, sbbxlh[i]);
				ps.addBatch();
			}
			ps.executeBatch();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}finally {
			SfDBResource.freeConnection(conn);
	    }
		
		return null;
	}
	//查询税源登记信息
	private Object doQuery(VOPackage vp)throws BaseException {
		 GdzysSydjxxlrForm form = (GdzysSydjxxlrForm) vp.getData();
		 String sbbxlh = form.getSbbxlh();
		 PreparedStatement ps = null;
	     Connection conn = null;
	     ResultSet rs = null;
	     String sql1 = "select * from SBDB.SB_JL_GDZYS_SBB where sbbxlh=?";
	     try {
	    	conn = SfDBResource.getConnection();
			ps = conn.prepareStatement(sql1);
			ps.setString(1, form.getSbbxlh());
			rs = ps.executeQuery();
			if(rs.next()){
				form.setSylxdm(rs.getString("sylxdm"));//税源类型代码
				form.setNsrlx(rs.getString("nsrlx"));//纳税人类型
				form.setShzt(rs.getString("sbzt"));//申报状态
				form.setNsrmc(rs.getString("nsrmc"));//纳税人名称
				form.setJsjdm(rs.getString("jsjdm"));//计算机代码
				form.setNsrsbh(rs.getString("swdjzh")+"-"+rs.getString("zzjgdm"));//纳税人识别号
				form.setNsrsshy(rs.getString("nsrsshy"));//纳税人所属行业
				form.setQydjzclx(rs.getString("qydjzclx"));
				form.setNsrxxdz(rs.getString("nsrxxdz"));//纳税人详细地址
				form.setKhyh(rs.getString("khyhmc"));//开户银行名称
				form.setYhzh(rs.getString("yhzh"));//银行帐号
				form.setLxrxm(rs.getString("lxrxm"));//联系人姓名
				form.setLxdh(rs.getString("lxdh"));//联系电话
				form.setSfsjsp(rs.getString("sfsjsp"));//是否市局审批
				form.setTdzldz(rs.getString("tdzldz"));//土地坐落地址
				form.setPzjdwh(rs.getString("pzjdwh"));//批准占地文号
			
				if(rs.getString("PZJDMJ") == null || "".equals(rs.getString("PZJDMJ"))){
					form.setPzjdmj("");//批准占地面积
				}else{
					form.setPzjdmj(String.valueOf(rs.getBigDecimal("PZJDMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));//批准占地面积
				}
				form.setJsxmmc(rs.getString("jsxmmc"));//建设项目名称
				
				form.setSjzdmj(String.valueOf(rs.getBigDecimal("sjzdmj").setScale(2,BigDecimal.ROUND_HALF_UP)));//实际占地面积
				SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
				form.setZdsj(df.format(rs.getDate("zdsj")));//占地时间
				form.setJmsyj(rs.getString("sbjmly"));//申报减免理由
				form.setHj_jsmj(String.valueOf(rs.getBigDecimal("jsmj").setScale(2,BigDecimal.ROUND_HALF_UP)));//计税面积
				form.setHj_jzse(String.valueOf(rs.getBigDecimal("jzse").setScale(2,BigDecimal.ROUND_HALF_UP)));//计征税额
				form.setHj_jmmj(String.valueOf(rs.getBigDecimal("jmmj").setScale(2,BigDecimal.ROUND_HALF_UP)));//减免面积
				form.setHj_jmse(String.valueOf(rs.getBigDecimal("jmse").setScale(2,BigDecimal.ROUND_HALF_UP)));//减免税额
				form.setHj_ysmj(String.valueOf(rs.getBigDecimal("ysmj").setScale(2,BigDecimal.ROUND_HALF_UP)));//应税面积
				form.setHj_ynse(String.valueOf(rs.getBigDecimal("ynse").setScale(2,BigDecimal.ROUND_HALF_UP)));//应纳税额
				if("".equals(rs.getString("qrrq")) || rs.getString("qrrq") ==null){
					form.setQrrq(rs.getString("qrrq"));//确认日期
				}else{
					String qrrq = rs.getString("qrrq");
					String rq = qrrq.substring(0,4)+qrrq.substring(5,7)+qrrq.substring(8,10)+qrrq.substring(10,qrrq.indexOf("."));
					form.setQrrq(rq);//确认日期
				}
				form.setQrr(rs.getString("qrr"));//确认人
				
				form.setSjqrr(rs.getString("sjqrr"));//市局确认人
				if("".equals(rs.getString("sjqrrq")) || rs.getString("sjqrrq") ==null){
					form.setSjqrrq(rs.getString("sjqrrq"));//市局确认日期
				}else{
					String qrrq = rs.getString("sjqrrq");
					String rq = qrrq.substring(0,4)+qrrq.substring(5,7)+qrrq.substring(8,10);
					form.setSjqrrq(rq+" 00:00:00");//市局确认日期
				}
				
				form.setCjr(rs.getString("cjr"));//创建人
				
				if(null == rs.getString("cjrq") || "".equals(rs.getString("cjrq"))){
					form.setCjsj("");	
				}else{
					String qrrq = rs.getString("cjrq");
					String rq = qrrq.substring(0,4)+qrrq.substring(5,7)+qrrq.substring(8,10)+qrrq.substring(10,qrrq.indexOf("."));
					form.setCjsj(rq);	
				}
				
				form.setSysend(rs.getString("sysend"));//适用税额年度
				form.setBzms(rs.getString("bzms"));//备注描述
				form.setSfzzlxdm(rs.getString("sfzzlx"));//身份证照类型
				form.setSfzzhm(rs.getString("sfzzhm"));//身份证照号码
			}
			rs.close();
			ps.close();

			//身份证照类型名称
			 Map map = getSFZZLXMC();
			 if(form.getNsrlx().equals("1")){
				 form.setSfzzlxmc(map.get(form.getSfzzlxdm().toString()).toString());	 
			 }else{
				 DJXX djxx = (DJXX) getJBSJ(vp);
				 form.setNsrsshymc(djxx.getNsrsshymc());
				 form.setQydjzclxmc(djxx.getQydjzclxmc());
			 }
			 //获取占地用途名称
			 Map zdyt = getZDYTMC();
			 form.setZdytmc((List)zdyt.get("zdytmc"));
			 form.setZdytdm((List)zdyt.get("zdytdm")); 
			 //减免类别
			 Map jmlbmap = getJMLBMC();
			 form.setJmlbdm((List)jmlbmap.get("jmslbdm"));
			 form.setJmlbmc((List)jmlbmap.get("jmslbmc"));
			//占地用途
			Map map2 = getSBMX(form,conn);	
			form.setSbmx_map(map2);
			//减免类别
			Map map3 = getJMMX_CX(sbbxlh,form,conn);
			form.setJmmx_map(map3);
			//占地用途
			getzdyt(vp);
			//减免明细
			getjmxx(vp);
			//查询是否可以进行变更
			//sql2查询减免税证明是否已生成和是否已入库
			//sql3查询缴款书是否已生成
			String sql2 = "select * from sbdb.sb_jl_gdzys_jmszm where zfbz <> '1' and sbbxlh = ?";
			String sql3 = "select * from SBDB.SB_JL_GDZYS_SBBJKSGLB where sbbxlh=?";
			ps = conn.prepareStatement(sql2);
			ps.setString(1, form.getSbbxlh());
			rs = ps.executeQuery();
			//是否生成减免税证明
			if(rs.next()){
				form.setJms_flag("jms_flag");
				//判断是否已记账
				String jzbz = rs.getString("jzbz");
				if(jzbz.charAt(1) == '1'){
					form.setZwbs_flag("zwbs_flag");
				}else{
					form.setZwbs_flag("");
				}
				
			}else{
				form.setJms_flag("");
			}
			rs.close();
			ps.close();
			//是否生成缴款书
			ps = conn.prepareStatement(sql3);
			ps.setString(1, form.getSbbxlh());
			rs = ps.executeQuery();
			if(rs.next()){
				form.setJks_flag("jks_flag");
			}else{
				form.setJks_flag("");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}

		return form;
	}
	public String[] getjmxx(VOPackage vo){
		GdzysSydjxxlrForm form = (GdzysSydjxxlrForm) vo.getData();
		//减免类别代码
	    String[] jmlbdm_jmmx = null;
	    //减免面积
	    String[] jmmj_jmmx = null;
	    //减免税额
	    String[] jmse_jmmx = null;
		int len = 0;
		 PreparedStatement ps = null;
	     Connection conn = null;
	     ResultSet rs = null;
	     //插入sql
	     String sql1 = " select count(*) num from  SBDB.SB_JL_GDZYS_SBBJMMX where sbbxlh=?";
	     String sql = " select * from  SBDB.SB_JL_GDZYS_SBBJMMX where sbbxlh=?";
	     try {
	    	 //获取连接
			conn = SfDBResource.getConnection();
			ps = conn.prepareStatement(sql1);
			ps.setString(1, form.getSbbxlh());
			rs =ps.executeQuery();
			if(rs.next()){
				len = rs.getInt("num");
			}
			rs.close();
			ps.close();
			  jmlbdm_jmmx =new String[len];
			    //减免面积
			  jmmj_jmmx = new String[len];
			    //减免税额
			  jmse_jmmx = new String[len];
			  int i=0;
			  ps = conn.prepareStatement(sql);
			  ps.setString(1, form.getSbbxlh());
			  rs =ps.executeQuery();
			  while(rs.next()){
				  jmlbdm_jmmx[i]=rs.getString("jmslbdm");
				  jmmj_jmmx[i]=String.valueOf(rs.getBigDecimal("jmmj").setScale(2,BigDecimal.ROUND_HALF_UP));//rs.getString("jmmj");
				  jmse_jmmx[i]=String.valueOf(rs.getBigDecimal("jmse").setScale(2,BigDecimal.ROUND_HALF_UP));//rs.getString("jmse");
				  i++;
			  }
			form.setJmlbdm_jmmx(jmlbdm_jmmx);
			form.setFljmmj(jmmj_jmmx);
			form.setFljmse(jmse_jmmx);
		    //关闭preparestatement
		    ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			SfDBResource.freeConnection(conn);
	       }
		return null;
	}
	
	
	
	//申报信息
	public String[] getzdyt(VOPackage vp){
		 GdzysSydjxxlrForm form = (GdzysSydjxxlrForm) vp.getData();
		 PreparedStatement ps = null;
	     Connection conn = null;
	     ResultSet rs = null;
	   //占地用途
		 String[] zdyt_sbmx = null;
		//适用税额
	    String[] syse_sbmx =null;
	    //计税面积
	    String[] jsmj_sbmx = null;
	    //计征税额
	    String[] jzse_sbmx = null;
	    //减免面积
	    String[] jmmj_sbmx = null;
	    //减免税额
	    String[] jmse_sbmx =null;
	    //应税面积
	    String[] ysmj_sbmx = null;
	    //应纳税额
	    String[] ynse_sbmx = null;
		 
	     int len =0;
	     String sql = "select count(*) num from SBDB.SB_JL_GDZYS_SBBSBMX where sbbxlh=?";
	     String sql1 = "SELECT * FROM SBDB.SB_JL_GDZYS_SBBSBMX WHERE SBBXLH = ? order by zdytdm";
	    	try {
				conn = SfDBResource.getConnection();
				ps = conn.prepareStatement(sql);
				ps.setString(1, form.getSbbxlh());
				rs = ps.executeQuery();
				if(rs.next()){
					len=rs.getInt("num");
				}
				zdyt_sbmx = new String[len] ;
				syse_sbmx = new String[len] ;
				jsmj_sbmx = new String[len] ;
				jzse_sbmx = new String[len] ;
				jmmj_sbmx = new String[len] ;
				jmse_sbmx = new String[len] ;
				ysmj_sbmx = new String[len] ;
				ynse_sbmx = new String[len] ;
				int i=0;
				rs.close();
				ps.close();
				ps = conn.prepareStatement(sql1);
				ps.setString(1, form.getSbbxlh());
				rs = ps.executeQuery();
				while(rs.next()){
					zdyt_sbmx[i] = rs.getString("zdytdm");
					syse_sbmx[i] = rs.getString("syse");//rs.getString("syse");
					jsmj_sbmx[i] = String.valueOf(rs.getBigDecimal("jsmj").setScale(2,BigDecimal.ROUND_HALF_UP));//rs.getString("jsmj");
					jzse_sbmx[i] = String.valueOf(rs.getBigDecimal("jzse").setScale(2,BigDecimal.ROUND_HALF_UP));//rs.getString("jzse");
					jmmj_sbmx[i] = String.valueOf(rs.getBigDecimal("jmmj").setScale(2,BigDecimal.ROUND_HALF_UP));//rs.getString("jmmj");
					jmse_sbmx[i] = String.valueOf(rs.getBigDecimal("jmse").setScale(2,BigDecimal.ROUND_HALF_UP));//rs.getString("jmse");
					ysmj_sbmx[i] = String.valueOf(rs.getBigDecimal("ysmj").setScale(2,BigDecimal.ROUND_HALF_UP));//rs.getString("ysmj");
					ynse_sbmx[i] = String.valueOf(rs.getBigDecimal("ynse").setScale(2,BigDecimal.ROUND_HALF_UP));//rs.getString("ynse");
					i++;
				}
				form.setZdyt(zdyt_sbmx);
				form.setSyse(syse_sbmx);
				form.setJsmj(jsmj_sbmx);
				form.setJzse(jzse_sbmx);
				form.setJmmj(jmmj_sbmx);
				form.setJmse(jmse_sbmx);
				form.setYsmj(ysmj_sbmx);
				form.setYnse(ynse_sbmx);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		return null;
	}
	
	
	
	
	
	
	//初始化税源登记页面
	private Object doInit(VOPackage vp)throws BaseException {
		GdzysSydjxxlrForm form = (GdzysSydjxxlrForm) vp.getData();
		UserData ud = vp.getUserData();
		//区县代码
		String qxdm = InterfaceDj.getQxdm(ud);;
		//税源类型list
		List sylx_list = new ArrayList();
		//占地用途list
		List zdyt_list = new ArrayList();
		//适用税额list
		List syse_list = new ArrayList();
		//减免类别list
		List jmlb_list = new ArrayList();
	     Connection conn = null;
		try{
			conn = SfDBResource.getConnection();
		//那是纳税人类型
		//税源类型
			sylx_list = getSYLX(conn);
		//纳税人所属行业--企业登记注册类型 20131202
	     //getJBSJ(vp);
		//占地用途
	     zdyt_list = getZDYT(conn);
		//适用税额
	     syse_list = getSYSE(conn,qxdm,form);
		//减免类别
	     jmlb_list =  getJMLB(conn);
	    
	     form.setSylx_list(sylx_list);
	     form.setZdyt_list(zdyt_list);
	     form.setSyse_list(syse_list);
	     form.setJmlb_list(jmlb_list);
	     String sbbxlh = GdzysGy.getSBXLH();
	     form.setSbbxlh(sbbxlh);
		} catch (Exception e) {
			e.printStackTrace();
			 throw new ApplicationException(e.getMessage());
		}finally {
			SfDBResource.freeConnection(conn);
	       }
		return form;
	}
    
	public BigDecimal str2Bigdecimal(String str){
		BigDecimal bd=new BigDecimal(str); 
		bd=bd.setScale(4, BigDecimal.ROUND_HALF_UP);
		return bd;
	}
	
	//那是纳税人类型
	//税源类型
	private List getSYLX(Connection conn) throws BaseException{
		List list = new ArrayList();
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 String sql = "SELECT * FROM DMDB.GD_DM_SYLX WHERE ZXBZ = '0'";
		 try {
			ps =  conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				LabelValueBean bean = new LabelValueBean(rs.getString("SYLXMC"),rs.getString("SYLXDM"));
				list.add(bean);
			}	
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}
		
		return list;
	}
	
	public Object getZRRJBSJ(VOPackage vo) throws BaseException{
		GdzysSydjxxlrForm form = (GdzysSydjxxlrForm) vo.getData();
		UserData ud = vo.getUserData();
		ZRRJBSJ dj = new ZRRJBSJ();
		List list = new ArrayList();
		try {
			dj =  InterfaceDj.getZRRJBSJ(form.getJsjdm(), ud);
			//list = InterfaceDj.getYHZH(form.getJsjdm());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}
		DJXX djxx = new DJXX();
		djxx.setNsrmc(dj.getNsrmc());//纳税人名称
		djxx.setNsrxxdz(dj.getTxdz());//填写地址
		/*for (int j = 0; j < list.size(); j++){
            YHZH yhzh = (YHZH) list.get(j);
            if (yhzh.getJbzhbs().equals(CodeConstant.SMSB_JBZHBS))
            {
                djxx.setYhdm(yhzh.getYhdm()); //银行代码
                djxx.setYhmc(yhzh.getKhyhmc()); //银行名称
                djxx.setYhzh(yhzh.getZh()); //帐户
                break;
            }
        }*/
			
		return djxx;
	}
	
	//纳税人所属行业-----企业登记注册类型
	public Object getJBSJ(VOPackage vo) throws BaseException{
		GdzysSydjxxlrForm form = (GdzysSydjxxlrForm) vo.getData();
		UserData ud = vo.getUserData();
		SWDJJBSJ dj = new SWDJJBSJ();
		List list = new ArrayList();
		try {
			dj = (SWDJJBSJ) InterfaceDj.getJBSJ(form.getJsjdm(), ud);
		    list = InterfaceDj.getYHZH(form.getJsjdm(), ud);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}
		DJXX djxx = new DJXX();
	
			djxx.setNsrmc(dj.getNsrmc());//纳税人名称
			djxx.setNsrsshy(dj.getGjbzhydm()); //国家标准行业代码
			djxx.setNsrsshymc(dj.getGjbzhymc());//国家标准行业名称
			djxx.setQydjzclxdm(dj.getDjzclxdm()); //登记注册类型代码
			djxx.setQydjzclxmc(dj.getDjzclxmc()); //登记注册类型名称
			djxx.setNsrxxdz(dj.getZcdz()); //注册地址
			djxx.setNsrsbh(dj.getSwdjzh()+"-"+dj.getZzjgdm());
		
			String[] yhdm_list = new String[list.size()+1];
			String[] yhmc_list = new String[list.size()+1];
			String[] yhzh_list = new String[list.size()+1];
			yhdm_list[0]="0";
			yhmc_list[0]="请录入银行名称";
			yhzh_list[0]="请录入银行账号";
			for (int j = 0; j < list.size(); j++)
	         {
	             YHZH yhzh = (YHZH) list.get(j);
	             if (yhzh.getJbzhbs().equals(CodeConstant.SMSB_JBZHBS))
	             {
	                 djxx.setYhdm(yhzh.getYhdm()); //银行代码
	                 djxx.setYhmc(yhzh.getKhyhmc()); //银行名称
	                 djxx.setYhzh(yhzh.getZh()); //帐户
	                 yhdm_list[j+1] = yhzh.getYhdm();
	                 yhmc_list[j+1] = yhzh.getKhyhmc();
	                 yhzh_list[j+1] = yhzh.getZh();
	                 
	             }else{
	            	 yhdm_list[j+1] = yhzh.getYhdm();
	                 yhmc_list[j+1] = yhzh.getKhyhmc();
	                 yhzh_list[j+1] = yhzh.getZh();
	                 
	             }
	         }
			
		djxx.setYhdm_list(yhdm_list);
		djxx.setYhmc_list(yhmc_list);
		djxx.setYhzh_list(yhzh_list);
		return djxx;
	}
	//备注
	//占地用途
	
	public List getZDYT(Connection conn) throws BaseException{
		List list = new ArrayList();
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 String sql = "SELECT * FROM DMDB.GD_DM_ZDYT WHERE ZXBZ = '0'";
		 try {
			ps =  conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				LabelValueBean bean = new LabelValueBean(rs.getString("ZDYTMC"),rs.getString("ZDYTDM"));
				list.add(bean);
			}	
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}
		
		return list;
	}
	//适用税额
	public List getSYSE(Connection conn,String qxdm, GdzysSydjxxlrForm form)throws BaseException{
		List nd_list = new ArrayList();
		List list = new ArrayList();
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 String sql2 = "select a.nd from DMDB.GD_DM_SYSE a group by a.nd order by a.nd desc";
		 try {
			ps =  conn.prepareStatement(sql2);
			rs = ps.executeQuery();
			while(rs.next()){
				nd_list.add(rs.getString("nd"));
			}
			rs.close();
			ps.close();
			//放到form中 
			form.setNd_list(nd_list);
			
			String sql = "SELECT LX.SYSELXMC,LX.SYSELXDM,SE.SYSE,SE.ND FROM DMDB.GD_DM_SYSELX LX,DMDB.GD_DM_SYSE SE " +
		 		"WHERE LX.SYSELXDM = SE.SYSELXDM AND LX.ZXBZ = '0' AND SE.ZXBZ = '0' " +
		 		" AND SE.QXDM = ?" +
		 		"AND SE.ND = ?";
		
			ps =  conn.prepareStatement(sql);
			ps.setString(1, qxdm);
			ps.setString(2, nd_list.get(0).toString());
			rs = ps.executeQuery();
			while(rs.next()){
				LabelValueBean bean = new LabelValueBean(rs.getString("SYSE"),rs.getString("SYSELXDM")+"-"+rs.getString("SYSE"));
				list.add(bean);
			}	
			rs.close();
			ps.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}
		
		return list;
	}
	
	
	//减免类别
	public List getJMLB(Connection conn) throws BaseException{
		List list = new ArrayList();
		LabelValueBean b = new LabelValueBean("","");
		list.add(b);
		LabelValueBean ncjm = null;
		LabelValueBean nckn = null;
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 String sql = "SELECT * FROM DMDB.GD_DM_JMSLB WHERE ZXBZ = '0' and jmslbdm <> '06' order by JMSLBDM";
		 try {
			ps =  conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				if("01".equals(rs.getString("JMSLBDM"))){
					ncjm = new LabelValueBean(rs.getString("JMSLBMC"),rs.getString("JMSLBDM"));;
				} else if("02".equals(rs.getString("JMSLBDM"))){
					nckn = new LabelValueBean(rs.getString("JMSLBMC"),rs.getString("JMSLBDM"));
				}else{
				LabelValueBean bean = new LabelValueBean(rs.getString("JMSLBMC"),rs.getString("JMSLBDM"));
				list.add(bean);
				}
			}
			list.add(ncjm);
			list.add(nckn);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}
		
		return list;
	}
	
	//获取减免明细信息
	private Map getJMMX_CX(String sbbxlh, GdzysSydjxxlrForm form, Connection conn) throws BaseException{
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 Map map = new HashMap();
		 //军事00
			JMXXModel js = new JMXXModel();
		 //农村01
			JMXXModel nc = new JMXXModel();
		 //困难02
			JMXXModel kn = new JMXXModel();
		 //养老院03
			JMXXModel yly = new JMXXModel();
		 //医院04
			JMXXModel yy = new JMXXModel();
		 //幼儿园05
			JMXXModel yey = new JMXXModel();
		 //学校0601--一般
			JMXXModel xx1 = new JMXXModel();
			//学校0602--技工
			JMXXModel xx2 = new JMXXModel();
		 //其他07
			JMXXModel qt = new JMXXModel();
		
		 String sql = " select * from SBDB.SB_JL_GDZYS_SBBJMMX where sbbxlh = ? ";
		 try {
			ps =  conn.prepareStatement(sql);
			ps.setString(1, sbbxlh);
			rs = ps.executeQuery();
			
			while(rs.next()){
				if("00".equals(rs.getString("jmslbdm"))){
					if("".equals(rs.getString("jmmj")) || rs.getString("jmmj") == null){
						js.setFljmmj("0");
					}else{
						js.setFljmmj(String.valueOf(rs.getBigDecimal("jmmj").setScale(2,BigDecimal.ROUND_HALF_UP)));
					}
					if("".equals(rs.getString("jmse")) || rs.getString("jmmj") == null){
						js.setFljmse("0");
					}else{
						js.setFljmse(String.valueOf(rs.getBigDecimal("jmse").setScale(2,BigDecimal.ROUND_HALF_UP)));
					}
					
				}else if("01".equals(rs.getString("jmslbdm"))){
					
					if("".equals(rs.getString("jmmj")) || rs.getString("jmmj") == null){
						nc.setFljmmj("0");
					}else{
						nc.setFljmmj(String.valueOf(rs.getBigDecimal("jmmj").setScale(2,BigDecimal.ROUND_HALF_UP)));
					}
					if("".equals(rs.getString("jmse")) || rs.getString("jmmj") == null){
						nc.setFljmse("0");
					}else{
						nc.setFljmse(String.valueOf(rs.getBigDecimal("jmse").setScale(2,BigDecimal.ROUND_HALF_UP)));
					}
					
				}else if("02".equals(rs.getString("jmslbdm"))){
					if("".equals(rs.getString("jmmj")) || rs.getString("jmmj") == null){
						kn.setFljmmj("0");
					}else{
						kn.setFljmmj(String.valueOf(rs.getBigDecimal("jmmj").setScale(2,BigDecimal.ROUND_HALF_UP)));
					}
					if("".equals(rs.getString("jmse")) || rs.getString("jmmj") == null){
						kn.setFljmse("0");
					}else{
						kn.setFljmse(String.valueOf(rs.getBigDecimal("jmse").setScale(2,BigDecimal.ROUND_HALF_UP)));
					}
					
				}else if("03".equals(rs.getString("jmslbdm"))){
					
					if("".equals(rs.getString("jmmj")) || rs.getString("jmmj") == null){
						yly.setFljmmj("0");
					}else{
						yly.setFljmmj(String.valueOf(rs.getBigDecimal("jmmj").setScale(2,BigDecimal.ROUND_HALF_UP)));
					}
					if("".equals(rs.getString("jmse")) || rs.getString("jmmj") == null){
						yly.setFljmse("0");
					}else{
						yly.setFljmse(String.valueOf(rs.getBigDecimal("jmse").setScale(2,BigDecimal.ROUND_HALF_UP)));
					}
					
					
				}else if("04".equals(rs.getString("jmslbdm"))){
					
					if("".equals(rs.getString("jmmj")) || rs.getString("jmmj") == null){
						yy.setFljmmj("0");
					}else{
						yy.setFljmmj(String.valueOf(rs.getBigDecimal("jmmj").setScale(2,BigDecimal.ROUND_HALF_UP)));
					}
					if("".equals(rs.getString("jmse")) || rs.getString("jmmj") == null){
						yy.setFljmse("0");
					}else{
						yy.setFljmse(String.valueOf(rs.getBigDecimal("jmse").setScale(2,BigDecimal.ROUND_HALF_UP)));
					}
					
				}else if("05".equals(rs.getString("jmslbdm"))){
					
					if("".equals(rs.getString("jmmj")) || rs.getString("jmmj") == null){
						yey.setFljmmj("0");
					}else{
						yey.setFljmmj(String.valueOf(rs.getBigDecimal("jmmj").setScale(2,BigDecimal.ROUND_HALF_UP)));
					}
					if("".equals(rs.getString("jmse")) || rs.getString("jmmj") == null){
						yey.setFljmse("0");
					}else{
						yey.setFljmse(String.valueOf(rs.getBigDecimal("jmse").setScale(2,BigDecimal.ROUND_HALF_UP)));
					}
					
				}else if("0601".equals(rs.getString("jmslbdm"))){
					
					if("".equals(rs.getString("jmmj")) || rs.getString("jmmj") == null){
						xx1.setFljmmj("0");
					}else{
						xx1.setFljmmj(String.valueOf(rs.getBigDecimal("jmmj").setScale(2,BigDecimal.ROUND_HALF_UP)));
					}
					if("".equals(rs.getString("jmse")) || rs.getString("jmmj") == null){
						xx1.setFljmse("0");
					}else{
						xx1.setFljmse(String.valueOf(rs.getBigDecimal("jmse").setScale(2,BigDecimal.ROUND_HALF_UP)));
					}
					
				}else if("0602".equals(rs.getString("jmslbdm"))){
						
						if("".equals(rs.getString("jmmj")) || rs.getString("jmmj") == null){
							xx2.setFljmmj("0");
						}else{
							xx2.setFljmmj(String.valueOf(rs.getBigDecimal("jmmj").setScale(2,BigDecimal.ROUND_HALF_UP)));
						}
						if("".equals(rs.getString("jmse")) || rs.getString("jmmj") == null){
							xx2.setFljmse("0");
						}else{
							xx2.setFljmse(String.valueOf(rs.getBigDecimal("jmse").setScale(2,BigDecimal.ROUND_HALF_UP)));
						}
				
				}else if("07".equals(rs.getString("jmslbdm"))){
					
					if("".equals(rs.getString("jmmj")) || rs.getString("jmmj") == null){
						qt.setFljmmj("0");
					}else{
						qt.setFljmmj(String.valueOf(rs.getBigDecimal("jmmj").setScale(2,BigDecimal.ROUND_HALF_UP)));
					}
					if("".equals(rs.getString("jmse")) || rs.getString("jmmj") == null){
						qt.setFljmse("0");
					}else{
						qt.setFljmse(String.valueOf(rs.getBigDecimal("jmse").setScale(2,BigDecimal.ROUND_HALF_UP)));
					}
					
					
				}
	
			}
			map.put("00", js);
			map.put("01", nc);
			map.put("02", kn);
			map.put("03", yly);
			map.put("04", yy);
			map.put("05", yey);
			map.put("0601", xx1);
			map.put("0602", xx2);
			map.put("07", qt);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}
		
		return map;
	}
	
	
	//获取减免明细信息
	private Map getJMMX(String sbbxlh, GdzysSydjxxlrForm form, Connection conn) throws BaseException{
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 Map map = new HashMap();
		 //军事00
			JMXXModel js = new JMXXModel();
		 //农村01
			JMXXModel nc = new JMXXModel();
		 //困难02
			JMXXModel kn = new JMXXModel();
		 //养老院03
			JMXXModel yly = new JMXXModel();
		 //医院04
			JMXXModel yy = new JMXXModel();
		 //幼儿园05
			JMXXModel yey = new JMXXModel();
		 //学校06
			JMXXModel xx = new JMXXModel();
		 //其他07
			JMXXModel qt = new JMXXModel();
		 BigDecimal jmmj = new BigDecimal("0.0000");
		 BigDecimal jmse = new BigDecimal("0.0000");
		 //str2Bigdecimal
		 String sql = " select * from SBDB.SB_JL_GDZYS_SBBJMMX where sbbxlh = ? ";
		 try {
			ps =  conn.prepareStatement(sql);
			ps.setString(1, sbbxlh);
			rs = ps.executeQuery();
			
			while(rs.next()){
				if("00".equals(rs.getString("jmslbdm"))){
					if("".equals(rs.getString("jmmj")) || rs.getString("jmmj") == null){
						js.setFljmmj("0");
					}else{
						js.setFljmmj(String.valueOf(rs.getBigDecimal("jmmj").setScale(2,BigDecimal.ROUND_HALF_UP)));
					}
					if("".equals(rs.getString("jmse")) || rs.getString("jmmj") == null){
						js.setFljmse("0");
					}else{
						js.setFljmse(String.valueOf(rs.getBigDecimal("jmse").setScale(2,BigDecimal.ROUND_HALF_UP)));
					}
					
				}else if("01".equals(rs.getString("jmslbdm"))){
					
					if("".equals(rs.getString("jmmj")) || rs.getString("jmmj") == null){
						nc.setFljmmj("0");
					}else{
						nc.setFljmmj(String.valueOf(rs.getBigDecimal("jmmj").setScale(2,BigDecimal.ROUND_HALF_UP)));
					}
					if("".equals(rs.getString("jmse")) || rs.getString("jmmj") == null){
						nc.setFljmse("0");
					}else{
						nc.setFljmse(String.valueOf(rs.getBigDecimal("jmse").setScale(2,BigDecimal.ROUND_HALF_UP)));
					}
					
				}else if("02".equals(rs.getString("jmslbdm"))){
					if("".equals(rs.getString("jmmj")) || rs.getString("jmmj") == null){
						kn.setFljmmj("0");
					}else{
						kn.setFljmmj(String.valueOf(rs.getBigDecimal("jmmj").setScale(2,BigDecimal.ROUND_HALF_UP)));
					}
					if("".equals(rs.getString("jmse")) || rs.getString("jmmj") == null){
						kn.setFljmse("0");
					}else{
						kn.setFljmse(String.valueOf(rs.getBigDecimal("jmse").setScale(2,BigDecimal.ROUND_HALF_UP)));
					}
					
				}else if("03".equals(rs.getString("jmslbdm"))){
					
					if("".equals(rs.getString("jmmj")) || rs.getString("jmmj") == null){
						yly.setFljmmj("0");
					}else{
						yly.setFljmmj(String.valueOf(rs.getBigDecimal("jmmj").setScale(2,BigDecimal.ROUND_HALF_UP)));
					}
					if("".equals(rs.getString("jmse")) || rs.getString("jmmj") == null){
						yly.setFljmse("0");
					}else{
						yly.setFljmse(String.valueOf(rs.getBigDecimal("jmse").setScale(2,BigDecimal.ROUND_HALF_UP)));
					}
					
					
				}else if("04".equals(rs.getString("jmslbdm"))){
					
					if("".equals(rs.getString("jmmj")) || rs.getString("jmmj") == null){
						yy.setFljmmj("0");
					}else{
						yy.setFljmmj(String.valueOf(rs.getBigDecimal("jmmj").setScale(2,BigDecimal.ROUND_HALF_UP)));
					}
					if("".equals(rs.getString("jmse")) || rs.getString("jmmj") == null){
						yy.setFljmse("0");
					}else{
						yy.setFljmse(String.valueOf(rs.getBigDecimal("jmse").setScale(2,BigDecimal.ROUND_HALF_UP)));
					}
					
				}else if("05".equals(rs.getString("jmslbdm"))){
					
					if("".equals(rs.getString("jmmj")) || rs.getString("jmmj") == null){
						yey.setFljmmj("0");
					}else{
						yey.setFljmmj(String.valueOf(rs.getBigDecimal("jmmj").setScale(2,BigDecimal.ROUND_HALF_UP)));
					}
					if("".equals(rs.getString("jmse")) || rs.getString("jmmj") == null){
						yey.setFljmse("0");
					}else{
						yey.setFljmse(String.valueOf(rs.getBigDecimal("jmse").setScale(2,BigDecimal.ROUND_HALF_UP)));
					}
					
				}else if(rs.getString("jmslbdm").indexOf("06")!=-1){
					
					if("".equals(rs.getString("jmmj")) || rs.getString("jmmj") == null){
						jmmj = jmmj.add(new BigDecimal("0.00"));
					}else{
						jmmj = jmmj.add(rs.getBigDecimal("jmmj").setScale(2,BigDecimal.ROUND_HALF_UP));
					}
					if("".equals(rs.getString("jmse")) || rs.getString("jmmj") == null){
						jmse = jmse.add(new BigDecimal("0.00"));
					}else{
						jmse = jmse.add(rs.getBigDecimal("jmse").setScale(2,BigDecimal.ROUND_HALF_UP));					
					}
					
					xx.setFljmmj(String.valueOf(jmmj.setScale(2,BigDecimal.ROUND_HALF_UP)));
					xx.setFljmse(String.valueOf(jmse.setScale(2,BigDecimal.ROUND_HALF_UP)));
				}else if("07".equals(rs.getString("jmslbdm"))){
					
					if("".equals(rs.getString("jmmj")) || rs.getString("jmmj") == null){
						qt.setFljmmj("0");
					}else{
						qt.setFljmmj(String.valueOf(rs.getBigDecimal("jmmj").setScale(2,BigDecimal.ROUND_HALF_UP)));
					}
					if("".equals(rs.getString("jmse")) || rs.getString("jmmj") == null){
						qt.setFljmse("0");
					}else{
						qt.setFljmse(String.valueOf(rs.getBigDecimal("jmse").setScale(2,BigDecimal.ROUND_HALF_UP)));
					}
					
					
				}
	
			}
			map.put("00", js);
			map.put("01", nc);
			map.put("02", kn);
			map.put("03", yly);
			map.put("04", yy);
			map.put("05", yey);
			map.put("06", xx);
			map.put("07", qt);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}
		
		return map;
	}
    
	public Map getSFZZLXMC(){
		
		Map map = new HashMap();
		map.put("02", "身份证");
		map.put("03", "军人证件");
		map.put("04", "护照");
		map.put("05", "港澳同胞回乡证");
		map.put("90", "其他");
		return map;
		
	}
	//占地用途名称
	private Map getZDYTMC() throws BaseException{
		Map map = new HashMap();
		List zdytmc = new ArrayList();
		List zdytdm = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from dmdb.GD_DM_ZDYT order by zdytdm";
		conn = SfDBResource.getConnection();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				zdytdm.add(rs.getString("zdytdm"));
				zdytmc.add(rs.getString("zdytmc"));
			}
			map.put("zdytmc", zdytmc);
			map.put("zdytdm", zdytdm);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}finally {
   	     	SfDBResource.freeConnection(conn);
	       }
		
		return map;
	}
	//减免类别名称
	private Map getJMLBMC() throws BaseException{
		Map map = new HashMap();
		List jmslbmc = new ArrayList();
		List jmslbdm = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from dmdb.GD_DM_JMSLB order by JMSLBDM";
		conn = SfDBResource.getConnection();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				jmslbdm.add(rs.getString("JMSLBDM"));
				jmslbmc.add(rs.getString("JMSLBMC"));
			}
			map.put("jmslbmc", jmslbmc);
			map.put("jmslbdm", jmslbdm);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}finally {
   	     	SfDBResource.freeConnection(conn);
	       }
		
		return map;
	}
	
	//适用税额
	public List getSYSE_CX(Connection conn,String qxdm, GdzysSydjxxlrForm form)throws BaseException{
		List nd_list = new ArrayList();
		List list = new ArrayList();
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 String sql2 = "select a.nd from DMDB.GD_DM_SYSE a group by a.nd order by a.nd desc";
		 try {
			ps =  conn.prepareStatement(sql2);
			rs = ps.executeQuery();
			while(rs.next()){
				nd_list.add(rs.getString("nd"));
			}
			rs.close();
			ps.close();
			//放到form中 
			form.setNd_list(nd_list);
			System.out.println("nd==========="+nd_list.get(0).toString());
			
			String sql = "SELECT LX.SYSELXMC,LX.SYSELXDM,SE.SYSE,SE.ND FROM DMDB.GD_DM_SYSELX LX,DMDB.GD_DM_SYSE SE " +
		 		"WHERE LX.SYSELXDM = SE.SYSELXDM AND LX.ZXBZ = '0' AND SE.ZXBZ = '0' " +
		 		" AND SE.QXDM = ?" +
		 		"AND SE.ND = ?";
		
			ps =  conn.prepareStatement(sql);
			ps.setString(1, qxdm);
			ps.setString(2,form.getSysend());
			rs = ps.executeQuery();
			while(rs.next()){
				LabelValueBean bean = new LabelValueBean(rs.getString("SYSE"),rs.getString("SYSELXDM")+"-"+rs.getString("SYSE"));
				list.add(bean);
			}	
			rs.close();
			ps.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}
		
		return list;
	}
	
	
	
}
