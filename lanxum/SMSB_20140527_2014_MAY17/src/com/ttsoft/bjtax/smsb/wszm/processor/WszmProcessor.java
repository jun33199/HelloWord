package com.ttsoft.bjtax.smsb.wszm.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.pzgl.proxy.ServiceProxy;
import com.ttsoft.bjtax.sfgl.common.code.CodeUtils;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.model.client.Wszm;
import com.ttsoft.bjtax.smsb.util.Debug;
import com.ttsoft.bjtax.smsb.util.FenPiaoUtil;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.wszm.web.WszmForm;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.Currency;
import com.ttsoft.framework.util.StringUtil;
import com.ttsoft.framework.util.VOPackage;


/**
 * 完税证明的processor，所有与完税证明有关的后台逻辑和数据库操作，都在此Processor中进行
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2013</p>
 *
 * <p>Company: </p>
 *
 * @author tujb
 * @version 1.0
 */
public class WszmProcessor implements Processor 
{
	public WszmProcessor() 
	{
		
	}
	
	/**
	 * 通用处理调度模块
	 * @param vo  数据传递值对象
	 * @return  数据结果对象[ActionForm]
	 * @throws com.ttsoft.framework.exception.BaseException
	 */
	public Object process(VOPackage vo) throws BaseException 
	{
		Object result = null;

	    if (vo == null) 
	    {
	      throw new NullPointerException();
	    }
	    
	    switch (vo.getAction()) 
	    {
	    	case CodeConstant.SMSB_SHOWACTION:
	    		result = doShow(vo);
	    		break;
	    	case CodeConstant.SMSB_QUERYACTION:
	    		result = this.doQuery(vo);
	    		break;
	    	case CodeConstant.SMSB_READERACTION: //明细
	    		result = doDetail(vo);
	    		break;
	    	case CodeConstant.SMSB_SAVEACTION: //保存数据
	    	{
	    		WszmForm pf = (WszmForm) vo.getData();
	    		if(pf.getSspzlxdm().equals(CodeConstant.WSZM_JKS))
				{
	    		  result = doPrintJks(vo);
				}else{
   				  result = doPrint(vo);
				}
   				break;
	    	}
	    	case CodeConstant.SMSB_PRINTACTION: //打印成功
	    		result = doSuccess(vo);
	    		break;
	    	case CodeConstant.SMSB_REPRINTACTION: //取号重打
	    		WszmForm pf = (WszmForm) vo.getData();
	    		if(pf.getSspzlxdm().equals(CodeConstant.WSZM_JKS))
				{
	    		    result = doReprintJks(vo);
				}else{
					result = doReprint(vo);	
				}
	    		break;
              
	    	default:
	    		throw new ApplicationException(
	            "ActionType有错误，processor中找不到相应的方法.");
	    }
	    return result;
	}
	
	/**
	 * 页面初始化
	 * @param vo 数据集对象（包括Form和UserData对象）
	 * @return 当前页面对应的Form对象
	 * @throws BaseException
	 */
	private Object doShow(VOPackage vo) throws BaseException 
	{
		WszmForm pf = new WszmForm();
		pf = (WszmForm) vo.getData();
	    try 
	    {
	    	//UserData对象
	    	UserData ud = (UserData) vo.getUserData();
	    	
	    	pf.setTfrq(SfDateUtil.getDate());
	    	//从登记接口中获得信息
	    	SWDJJBSJ dj = new SWDJJBSJ();
	    	try 
	    	{
	    		dj = (SWDJJBSJ) InterfaceDj.getJBSJ(pf.getJsjdm(), ud);
		        pf.setSwjgzzjgdm(dj.getSwjgzzjgdm());
		        pf.setSwjgzzjgmc(dj.getSwjgzzjgmc());
	    	}
	    	catch (Exception ex5)
	    	{
	    		ex5.printStackTrace();
		        throw ExceptionUtil.getBaseException(ex5);
	    	}
		      
	    }
	    catch (Exception ex)
	    {
	    	ex.printStackTrace();
	    	throw ExceptionUtil.getBaseException(ex);
	    }
	    
	    return pf;
	}
	
	/**
	 * doQuery
	 * @param vo VOPackage
	 * @return pf WszcxForm
	 * @throws BaseException
	 */
	private Object doQuery(VOPackage vo) throws BaseException 
	{
		Connection conn = null;
		UserData ud = null;
		WszmForm pf = null;
		List dataList = new ArrayList();
		List tmpList = null;
		try 
		{
			ud = vo.getUserData();
			pf = (WszmForm) vo.getData();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		}
		try 
		{
			conn = SfDBResource.getConnection();
			SfDBAccess da = new SfDBAccess(conn);
			try
			{
				tmpList = getList(da, pf, ud);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			if (null == tmpList)
			{
				tmpList = new ArrayList();
			}
			//获取总页数数据
			pf.setTotalpage(this.getPageTotalCount(tmpList.size()));
			//整理查询结果
			dataList = this.getPageDataList(tmpList, pf);
			pf.setDataList( (ArrayList) dataList);
		}
		catch (Exception e)
		{
			throw ExceptionUtil.getBaseException(e);
		}
		finally
		{
			SfDBResource.freeConnection(conn);
		}
		
		return pf;
	}
	
	/**
	 * 缴款信息查询
	 * @param vo VOPackage
	 * @throws BaseException
	 */
	private Object doDetail(VOPackage vo) throws BaseException 
	{

		Connection conn = null;
		ArrayList resMxList = new ArrayList();
		ArrayList dataList = new ArrayList();
		
		WszmForm pf = new WszmForm();
		
		String mxYpzh = "";
		String mxYwszh = "";
		String mxSz = "";
        String mxPmmc = "";
        String mxSkssrq ="";
        String mxSjse = "";
        String mxRkrq = "";
        
        String wszh = ""; //完税证号
        String ndzb = ""; //年度字别
        
        //设置格式化数字
        DecimalFormat deFormat = new DecimalFormat("#0.00");
		
		try 
		{
			conn = SfDBResource.getConnection();
			
			//UserData对象
            UserData ud = (UserData) vo.getUserData();
            //得到区县代码
            String qxdm = InterfaceDj.getQxdm(ud);
            //从登记接口中获得信息
            SWDJJBSJ dj = (SWDJJBSJ) InterfaceDj.getJBSJ_New(InterfaceDj.getZrrJsjdm(ud), ud);
            if (dj == null)
            {
                throw new ApplicationException("获取登记信息出错！");
            }
            pf.setSwjgzzjgdm(dj.getSwjgzzjgdm());
            pf.setSwjgzzjgmc(dj.getSwjgzzjgmc()); //税务机关名称
            pf.setDfswjg(pf.getSwjgzzjgmc()); //地方税务机关
            
            pf.setZhdm(ud.getXtsbm1()); //设置帐户代码
            pf.setLrr(ud.getYhmc()); //填票人
            pf.setPzzldm(CodeConstant.SMSB_PZZLDM); //票证种类代码
            
			Map dataMap = (Map) vo.getData();
			
			String nsrsbh = (String) dataMap.get("nsrsbh");
			String nsrmc = (String) dataMap.get("nsrmc");
			String ypzh = (String) dataMap.get("ypzh");
			String ysphm = (String) dataMap.get("ysphm");
			String ywszh = (String) dataMap.get("ywszh");
			String ypzzldm = (String) dataMap.get("ypzzldm");
			String yndzb = (String) dataMap.get("yndzb");
			String sspzlxdm = (String) dataMap.get("sspzlxdm");
			String hjje = (String) dataMap.get("hjje");
			String dycs = (String) dataMap.get("dycs");
			String swjgdm = (String) dataMap.get("swjgdm");
			
			//System.out.println("nsrsbh:"+nsrsbh+" nsrmc:"+nsrmc+" dycs:"+dycs+" swjgdm:"+swjgdm);
			//System.out.println("ypzh:"+ypzh+" sspzlxdm:"+sspzlxdm);
			//System.out.println("ywszh:"+ywszh+" ypzzldm:"+ypzzldm+" yndzb:"+yndzb+" hjje:"+hjje);
			
			//设置查询条件
			
			//1.缴款书完税证明
			 if(sspzlxdm.equals(CodeConstant.WSZM_JKS))
			 {
				 resMxList = getJksWszmList(sspzlxdm,ypzh);
			 }
			
			 //2.完税证完税证明
			 if(sspzlxdm.equals(CodeConstant.WSZM_LSWSZ) 
					 || sspzlxdm.equals(CodeConstant.WSZM_GTWSZ) 
					 || sspzlxdm.equals(CodeConstant.WSZM_QSWSZ) 
					 || sspzlxdm.equals(CodeConstant.WSZM_CCWSZ))
			 {
				 resMxList = getWszWszmList(sspzlxdm,ywszh,yndzb,ypzzldm);
			 }
			
			 //3.保单完税证明
			 if(sspzlxdm.equals(CodeConstant.WSZM_BD))
			 {
				 resMxList = getBdWszmList(sspzlxdm,ywszh,yndzb,ypzzldm);
			 }
			
			 //4.收入退还书完税证明
			 if(sspzlxdm.equals(CodeConstant.WSZM_SRTHS))
			 {
				 resMxList = getSrthsWszmList(sspzlxdm,ypzh);
			 }
			
			 //5.手工完税凭证完税证明
			 if(sspzlxdm.equals(CodeConstant.WSZM_SGWSPZ))
			 {
				 resMxList = getWszmWszmList(sspzlxdm,ywszh,yndzb,ypzzldm);
			 }
			 
			 if (resMxList.size() == 0 || resMxList == null) 
			 {
				 throw new ApplicationException("无符合条件的查询结果！");
			 }
			 
			 //定义分票用HashMap
			 Wszm wszm = new Wszm();
			 
			 //如果是缴款书才进行分票操作
			 if(sspzlxdm.equals(CodeConstant.WSZM_JKS))
			 {
				 Map fpmap=new HashMap();
				 for (int i = 0; i < resMxList.size(); i++)
				 {
					 wszm = (Wszm) resMxList.get(i);
					 if(fpmap.containsKey(wszm.getYpzh())){
						 ((List)fpmap.get(wszm.getYpzh())).add(wszm);
					 }else{
						 List tempLs=new ArrayList();
						 tempLs.add(wszm);
						 fpmap.put(wszm.getYpzh(), tempLs);
					 }
				 }
				 //调用分票接口
				 ArrayList reFpList=FenPiaoUtil.fenPiao(fpmap);
				 for(int i=0;i<reFpList.size();i++){
					 List tempLs=Wszm2Map((List)reFpList.get(i));
					 reFpList.set(i, tempLs);
				 }
				// pf.setFpList(reFpList);
//				 System.out.println("11############reFpList.size()="+reFpList.size());
//				 for(int i=0;i<reFpList.size();i++){
//				 	  ArrayList eveList=(ArrayList)reFpList.get(i); 
//				 	 System.out.println(i+"############eveList.size()="+eveList.size());
//				 	 for(int j=0;j<eveList.size();j++){
//				 		HashMap map=(HashMap)eveList.get(j);
//				        	 System.out.println(j+"############map.get(ypzh)="+map.get("ypzh"));
//				 	 }  	
//				 }	  
					  
				 pf.setDyDataList(reFpList);
				 
			 }else{	 
				 for (int i = 0; i < resMxList.size(); i++)
				 {
					 wszm = (Wszm) resMxList.get(i);
					 HashMap map = new HashMap();
					 
					 if (wszm.getYpzh() == null)
					 {
						 map.put("ypzh", " ");
					 }
					 else
					 {
						 map.put("ypzh", wszm.getYpzh());
					 }
					 
					 if (wszm.getYwszh() == null)
					 {
						 map.put("ywszh", " ");
					 }
					 else
					 {
						 map.put("ywszh", wszm.getYwszh());
					 }
					 
					 if (wszm.getHjje() == null)
					 {
						 map.put("sjse", " ");
					 }
					 else
					 {
						 //收入退换书金额为负数“-￥”时，将格式调整为“（￥）”
						 if(sspzlxdm.equals(CodeConstant.WSZM_SRTHS))
						 {
							 if(String.valueOf(wszm.getHjje()) != null && String.valueOf(wszm.getHjje()).startsWith("-"))
							 {
								 map.put("sjse", "("+deFormat.format(StringUtil.getDouble(String.valueOf(wszm.getHjje()).substring(1), 0.00))+")");
							 }
							 else
							 {
								 map.put("sjse", deFormat.format(StringUtil.getDouble(String.valueOf(wszm.getHjje()), 0.00)));
							 }
						 }
						 else
						 {
							 map.put("sjse", deFormat.format(StringUtil.getDouble(String.valueOf(wszm.getHjje()), 0.00)));
						 }
							
					 }
					 
					 if (wszm.getSkssksrq() == null && wszm.getSkssjsrq() == null)
					 {
						 map.put("skssrq", " ");
					 }
					 else
					 {
						 map.put("skssrq", wszm.getSkssksrq()+"-"+wszm.getSkssjsrq());
					 }
					 
					 if (wszm.getYpzrkrq() == null)
					 {
						 map.put("ypzrkrq", " ");
					 }
					 else
					 {
						 map.put("ypzrkrq", wszm.getYpzrkrq());
					 }
					 
					 map.put("szmc", CodeUtils.getCodeBeanLabel("DM_SZSM", wszm.getSzdm()));
					 map.put("szsmmc", CodeUtils.getCodeBeanLabel("DM_SZSM", wszm.getSzsmdm()));
					 
					 mxYpzh += map.get("ypzh") + ";;";
					 mxYwszh += map.get("ywszh") + ";;";
					 mxSz += map.get("szmc") + ";;";
					 mxPmmc += map.get("szsmmc") + ";;";
					 mxSkssrq += map.get("skssrq") + ";;";
					 mxSjse += map.get("sjse") + ";;";
					 mxRkrq += map.get("ypzrkrq") + ";;";
					 
					 dataList.add(map);
				 }
				 pf.setDyDataList(dataList);
			 }
			 
			 
			 
			 
			 //获取完税证明号
			 try 
			 {
				 HashMap retResult = ServiceProxy.readNumber(ud, CodeConstant.SMSB_SWWSZM_PZZLDM);
				 ndzb = retResult.get("ndzb").toString();
				 wszh = retResult.get("result").toString();
			 }
			 catch (Exception ex) 
			 {
				 ex.printStackTrace();
				 throw new ApplicationException("获取税收完税证明号码失败！");
			 }
			 
			 pf.setMxYpzh(mxYpzh);
			 pf.setMxYwszh(mxYwszh);
			 pf.setMxSz(mxSz);
			 pf.setMxPmmc(mxPmmc);
			 pf.setMxSkssrq(mxSkssrq);
			 pf.setMxRkrq(mxRkrq);
			 pf.setMxSjse(mxSjse);
			 
			// pf.setDyDataList(dataList);
			 
			 //获取完税证明打印次数
			 HashMap dycsmap = getWszmDycs(sspzlxdm,ypzh,ypzzldm,ywszh,yndzb);
			 int tmpdycs = Integer.parseInt((String) dycsmap.get("dycs"));
			 tmpdycs = tmpdycs +1;
			 
			 //收入退换书金额为负数“-￥”时，将格式调整为“（￥）”
			 if(sspzlxdm.equals(CodeConstant.WSZM_SRTHS))
			 {
				 if(hjje != null && hjje.startsWith("-"))
				 {
					 pf.setHjje2("("+hjje.substring(1)+")"); //合计金额
					 pf.setHjjedx2(Currency.convert(Double.parseDouble(hjje.substring(1)))); //合计金额大写
				 }
				 else
				 {
					 pf.setHjje2(hjje); //合计金额
					 pf.setHjjedx2(Currency.convert(Double.parseDouble(hjje))); //合计金额大写
				 }
			 }
			 else
			 {
				 pf.setHjje2(hjje); //合计金额
				 pf.setHjjedx2(Currency.convert(Double.parseDouble(hjje))); //合计金额大写
			 }
			 
			 pf.setHjje(hjje); //合计金额
			 pf.setHjjedx(Currency.convert(Double.parseDouble(hjje))); //合计金额大写
			 pf.setHeadWszh(wszh);
			 pf.setTfrq(SfDateUtil.getDate());
			 pf.setNdzb(ndzb);
			 pf.setNsrsbh(nsrsbh); //纳税人识别号
			 pf.setNsrmc(nsrmc); //纳税人名称
			 pf.setYsphm(ysphm);
			 pf.setYpzh(ypzh);
			 pf.setYwszh(ywszh);
			 pf.setYndzb(yndzb);
			 pf.setYpzzldm(ypzzldm);
			 pf.setDycs(String.valueOf(tmpdycs)); //备注：打印次数
			 pf.setSspzlxdm(sspzlxdm);
			 pf.setSsswjgzzjgdm(getNsrmc(nsrsbh,"",""));
			 
			 //System.out.println("wszh:"+pf.getHeadWszh()+" ndzb:"+pf.getNdzb());
			 //System.out.println("resMxList size:"+resMxList.size());
			 //System.out.println("dataList size:"+dataList.size());
			 //System.out.println("DyDataList size:"+pf.getDyDataList().size());
			 //System.out.println("mxSz:"+pf.getMxSz());
			 //System.out.println("mxPmmc:"+pf.getMxPmmc());
			 //System.out.println("mxSkssrq:"+pf.getMxSkssrq());
			 //System.out.println("mxSkssrq:"+pf.getMxRkrq());
			 //System.out.println("mxSjse:"+pf.getMxSjse());
			 //System.out.println("nsrsbh:"+pf.getNsrsbh());
			 //System.out.println("nsrmc:"+pf.getNsrmc());
			 //System.out.println("hjje:"+pf.getHjje());
			 //System.out.println("hjjedx:"+pf.getHjjedx());
			 //System.out.println("ypzh:"+pf.getYpzh());
			 //System.out.println("Tfrq:"+pf.getTfrq());
		
		}
		catch (Exception ex) 
		{
			throw ExceptionUtil.getBaseException(ex);
		}
		finally 
		{
			SfDBResource.freeConnection(conn);
		}
		
		return pf;
	}
	
	/**
	 * 缴款信息查询
	 * @param vo VOPackage
	 * @throws BaseException
	 */
	private List Wszm2Map (List resMxList) throws BaseException 
	{   
		 List dataList=new ArrayList();
		 try{
		 DecimalFormat deFormat = new DecimalFormat("#0.00");
		 Wszm wszm = new Wszm();
		 for (int i = 0; i < resMxList.size(); i++)
		 {
			 wszm = (Wszm) resMxList.get(i);
			 HashMap map = new HashMap();
			 
			 if (wszm.getYpzh() == null)
			 {
				 map.put("ypzh", " ");
			 }
			 else
			 {
				 map.put("ypzh", wszm.getYpzh());
			 }
			 
			 if (wszm.getYwszh() == null)
			 {
				 map.put("ywszh", " ");
			 }
			 else
			 {
				 map.put("ywszh", wszm.getYwszh());
			 }
			 
			 if (wszm.getHjje() == null)
			 {
				 map.put("sjse", " ");
			 }
			 else
			 {
			     map.put("sjse", deFormat.format(StringUtil.getDouble(String.valueOf(wszm.getHjje()), 0.00)));
			 }
			 
			 if (wszm.getSkssksrq() == null && wszm.getSkssjsrq() == null)
			 {
				 map.put("skssrq", " ");
			 }
			 else
			 {
				 map.put("skssrq", wszm.getSkssksrq()+"-"+wszm.getSkssjsrq());
			 }
			 
			 if (wszm.getYpzrkrq() == null)
			 {
				 map.put("ypzrkrq", " ");
			 }
			 else
			 {
				 map.put("ypzrkrq", wszm.getYpzrkrq());
			 }
			 
			 map.put("szmc", CodeUtils.getCodeBeanLabel("DM_SZSM", wszm.getSzdm()));
			 map.put("szsmmc", CodeUtils.getCodeBeanLabel("DM_SZSM", wszm.getSzsmdm()));
			 dataList.add(map);
		 }
		 //resMxList=dataList;
	  }catch (Exception ex) 
	  {
			throw ExceptionUtil.getBaseException(ex);
	  }
	  return dataList;
	}
	
	/**
     * 保存打印数据
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return 当前页面对应的Form对象
     * @throws BaseException
     */
    private Object doPrintJks (VOPackage vo)
        throws BaseException
    {
    	Connection conn = null;
    	HashMap wszmap = new HashMap();
    	List swzList=new ArrayList();
    	
		//获得前台提交的数据
		WszmForm pf = (WszmForm) vo.getData();
		//UserData对象
        UserData ud = (UserData) vo.getUserData();
        
        String wszmwszh = ""; //完税证号
        String wszmndzb = ""; //年度字别
        
        String wszmwszhs = ""; //完税证号
        String wszmndzbs = ""; //年度字别
        String hjjes[]=null;
        String sjms[]=null;
		try
		{
			//System.out.println("pf.getHjje()="+pf.getHjje());
			//System.out.println("getWszmsjm()="+pf.getWszmsjm());
			hjjes=pf.getHjje().split("<#r#>");
			//System.out.println("doPrintJks##############hjjes="+hjjes);
			sjms=pf.getWszmsjm().split("<#r#>");
			//System.out.println("doPrintJks##############sjms="+sjms);
			// 获取数据连接
			conn = SfDBResource.getConnection();

			//获得完税证号
			try 
			{
				
				for(int i=0;i<hjjes.length;i++){
					//System.out.println("111########hjjes["+i+"]="+hjjes[i]);
					 wszmap = getNewPH(ud,hjjes[i],CodeConstant.SMSB_SWWSZM_PZZLDM);
					 swzList.add(wszmap);
					 wszmwszh = (String) wszmap.get("wszh");
					 wszmndzb = (String) wszmap.get("ndzb");
					 
					 //System.out.println(i+"后台1111#############wszmwszh="+wszmwszh);
					 //System.out.println(i+"后台1111#############wszmndzb="+wszmndzb);
					 
					 if(wszmwszhs.length()>0){
						 wszmwszhs=wszmwszhs+"<#r#>";
					 }
					 wszmwszhs=wszmwszhs+wszmwszh;
					 
					 if(wszmndzbs.length()>0){
						 wszmndzbs=wszmndzbs+"<#r#>";
					 }
					 wszmndzbs=wszmndzbs+wszmndzb;
					 if(i<hjjes.length-1){
					   Thread.sleep(1000);
					 }
				}
				pf.setWszmwszhs(wszmwszhs);
				pf.setWszmndzbs(wszmndzbs);
			}
			catch (Exception ex) 
			{
				ex.printStackTrace();
		        throw new ApplicationException("获取税收完税证明号码失败！");
			}
			
			//更新数据
			try 
			{
				//设置打印次数
				HashMap dycsmap = getWszmDycs(pf.getSspzlxdm(), pf.getYpzh(), pf.getYpzzldm(), pf.getYwszh(), pf.getYndzb());
				
				boolean savSucc =true;
				for(int i=0;i<swzList.size();i++){
					HashMap tempwszmap = (HashMap)swzList.get(i);
					//System.out.println("1##################="+(String)tempwszmap.get("wszh"));
					//System.out.println("1##################="+(String)tempwszmap.get("ndzb"));
					boolean tempsavSucc = save2zbJks(conn, tempwszmap, ud, pf, dycsmap,hjjes[i],sjms[i]);
					if(!tempsavSucc){
						savSucc =false;
					}
					
				}
				
				//System.out.println("savSucc:"+savSucc);
				if(savSucc == true)
				{
					pf.setBccgbz("1");
				}
				else
				{
					pf.setBccgbz("0");
				}
				
				pf.setPzzldm(CodeConstant.SMSB_SWWSZM_PZZLDM);
				pf.setHeadWszh(wszmwszh);
				pf.setNdzb(wszmndzb);
				if(pf.getSspzlxdm() != null && pf.getSspzlxdm().equals(CodeConstant.WSZM_JKS))
				{
					pf.setYsphm(pf.getYsphm());
				}
				
				String isDo = "doPrint";
				doReturnValue(ud, pf, isDo);
			}
			catch (BaseException ex1) 
			{
				ex1.printStackTrace();
		        throw new ApplicationException("新增主表信息出错！");
			}
		}
		catch (Exception ex)
		{
			//保存不成功，则作废刚刚取出的完税证号！
			try 
			{  
				if(hjjes!=null){
					for(int i=0;i<hjjes.length;i++){
					 HashMap cxwszmap=(HashMap)swzList.get(i);
					 String cxwszmwszh = (String) cxwszmap.get("wszh");
					 String cxwszmndzb = (String) cxwszmap.get("ndzb");
					 wszmwszh = ServiceProxy.setCancellation(ud, CodeConstant.SMSB_SWWSZM_PZZLDM, cxwszmwszh + cxwszmndzb, StringUtil.getDouble(hjjes[i], 0.00), "1", "0", "0");
					 if(i<hjjes.length-1){
						   Thread.sleep(1000);
					 }
					}
				}
			}
			catch (Exception ex5) 
			{
				ex5.printStackTrace();
			}
			
			ex.printStackTrace();
			if(ex instanceof BaseException)
			{
				throw new ApplicationException(ex.getMessage());
			}
			throw new ApplicationException("新增主表信息出错！");
		}
		finally
		{
			SfDBResource.freeConnection(conn);
		}
		
    	return pf;
    }
	
	
	
	/**
     * 保存打印数据
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return 当前页面对应的Form对象
     * @throws BaseException
     */
    private Object doPrint (VOPackage vo)
        throws BaseException
    {
    	Connection conn = null;
    	HashMap wszmap = new HashMap();
    	
		//获得前台提交的数据
		WszmForm pf = (WszmForm) vo.getData();
		//UserData对象
        UserData ud = (UserData) vo.getUserData();
        
        String wszmwszh = ""; //完税证号
        String wszmndzb = ""; //年度字别
		
		try
		{
			// 获取数据连接
			conn = SfDBResource.getConnection();

			//获得完税证号
			try 
			{
				wszmap = getNewPH(ud,pf.getHjje(),CodeConstant.SMSB_SWWSZM_PZZLDM);
				
				wszmwszh = (String) wszmap.get("wszh");
				wszmndzb = (String) wszmap.get("ndzb");
			}
			catch (Exception ex) 
			{
				ex.printStackTrace();
		        throw new ApplicationException("获取税收完税证明号码失败！");
			}
			
			//更新数据
			try 
			{
				//设置打印次数
				HashMap dycsmap = getWszmDycs(pf.getSspzlxdm(), pf.getYpzh(), pf.getYpzzldm(), pf.getYwszh(), pf.getYndzb());
				
				boolean savSucc = save2zb(conn, wszmap, ud, pf, dycsmap);
				//System.out.println("savSucc:"+savSucc);
				if(savSucc == true)
				{
					pf.setBccgbz("1");
				}
				else
				{
					pf.setBccgbz("0");
				}
				
				pf.setPzzldm(CodeConstant.SMSB_SWWSZM_PZZLDM);
				pf.setHeadWszh(wszmwszh);
				pf.setNdzb(wszmndzb);
				if(pf.getSspzlxdm() != null && pf.getSspzlxdm().equals(CodeConstant.WSZM_JKS))
				{
					pf.setYsphm(pf.getYsphm());
				}
				
				String isDo = "doPrint";
				doReturnValue(ud, pf, isDo);
			}
			catch (BaseException ex1) 
			{
				ex1.printStackTrace();
		        throw new ApplicationException("新增主表信息出错！");
			}
		}
		catch (Exception ex)
		{
			//保存不成功，则作废刚刚取出的完税证号！
			try 
			{
				wszmwszh = ServiceProxy.setCancellation(ud, CodeConstant.SMSB_SWWSZM_PZZLDM, wszmndzb + wszmwszh, StringUtil.getDouble(pf.getHjje(), 0.00), "1", "0", "0");
			}
			catch (Exception ex5) 
			{
				ex5.printStackTrace();
			}
			
			ex.printStackTrace();
			if(ex instanceof BaseException)
			{
				throw new ApplicationException(ex.getMessage());
			}
			throw new ApplicationException("新增主表信息出错！");
		}
		finally
		{
			SfDBResource.freeConnection(conn);
		}
		
    	return pf;
    }
    
    
    
	/**
     * 保存打印数据
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return 当前页面对应的Form对象
     * @throws BaseException
     */
    private Object doSuccess (VOPackage vo)
        throws BaseException
    {
    	Connection conn = null;
    	
		//获得前台提交的数据
		WszmForm pf = (WszmForm) vo.getData();
		//UserData对象
        UserData ud = (UserData) vo.getUserData();
		
		try
		{
			// 获取数据连接
			conn = SfDBResource.getConnection();
			
			//更新数据
			try 
			{
				//执行doSuccess  更新完税证，打印标志
				String isDo = "doSuccess";
				ArrayList resList =  doReturnValue(ud, pf, isDo);
				for(int i = 0; i < resList.size(); i++)
				{
					Wszm setWszm = (Wszm) resList.get(0);
					String wszh = setWszm.getWszh();
					String pzzldm = setWszm.getPzzldm();
					String ndzb = setWszm.getNdzb();
					String pzlydm = setWszm.getPzlydm();
					try
					 {
						boolean savSucc = false;
					   
					   //缴款书多条
					   if(pf.getSspzlxdm() != null && pf.getSspzlxdm().equals(CodeConstant.WSZM_JKS)){
						   String wszhs[]=pf.getWszmwszhs().split("<#r#>");
		            	   String ndzbs[]=pf.getWszmndzbs().split("<#r#>");
		            	   for(int k=0;k<wszhs.length;k++){
						    //设置为已打印
						     savSucc = updateDYBZ(conn, pzlydm, wszhs[k], ndzbs[k], pzzldm);
							 
						    //设置打印次数
						     savSucc = updateDYCS(conn, pzlydm, wszhs[k], ndzbs[k], pzzldm);
		            	   }
					   }else{
							 //设置为已打印
							savSucc = updateDYBZ(conn, pzlydm, wszh, ndzb, pzzldm);
							//System.out.println("savSucc 1:"+savSucc+" Ndzb:"+ndzb+" Wszh:"+wszh);
							 
							 //设置打印次数
							savSucc = updateDYCS(conn, pzlydm, wszh, ndzb, pzzldm);
							//System.out.println("savSucc 2:"+savSucc+" Ndzb:"+ndzb+" Wszh:"+wszh);
					   }
						
						if(savSucc == true)
						 {
							 pf.setBccgbz("0");
						 }
						 else
						 {
							 pf.setBccgbz("1");
						 }
					 }
					 catch (BaseException ex1)
					 {
						 ex1.printStackTrace();
						 throw new ApplicationException("更新税收完税证明打印标志出错！");
					 }
				}
				 
				if(pf.getSspzlxdm() != null && pf.getSspzlxdm().equals(CodeConstant.WSZM_JKS))
				{
					pf.setYsphm(pf.getYsphm());
				}
				doReturnValue(ud, pf, isDo);
			}
			catch (Exception ex1) 
			{
		        ex1.printStackTrace();
				if(ex1 instanceof BaseException)
				{
					throw new ApplicationException(ex1.getMessage());
				}
				throw new ApplicationException("更新税收完税证明打印标志出错！");
			}
		}
		catch (BaseException ex)
		{
			throw ExceptionUtil.getBaseException(ex);
		}
		finally
		{
			SfDBResource.freeConnection(conn);
		}
		
    	return pf;
    }
    
    
    /**
     * 重打完税证明 1、取号,同时作废原完税证号，2、更新数据
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return 当前页面对应的Form对象
     * @throws BaseException
     */
    private Object doReprintJks (VOPackage vo)
        throws BaseException
    {
    	Connection conn = null;
    	String newWszh = ""; //新完税证号
    	String newNdzb = ""; //新年度字别
    	
		//获得前台提交的数据
		WszmForm pf = (WszmForm) vo.getData();
		//UserData对象
        UserData ud = (UserData) vo.getUserData();
        //HashMap wszmap = new HashMap();
    	List newList=new ArrayList();
    	DecimalFormat deFormat = new DecimalFormat("#0.00");
		try
		{
			// 获取数据连接
			conn = SfDBResource.getConnection();
			
			//更新数据
			try 
			{
				 //执行doReprint  重新取号然后打印，1、取号,同时作废原完税证号，2、更新数据
				 //设置格式化数字
				 
				 
				 String isDo = "doReprint";
				// ArrayList resList =  getJksWspzs(ud, pf);
				 boolean flag=true;
				 String wszmwszhs = ""; //完税证号
			     String wszmndzbs = ""; //年度字别
			     String sjms = ""; //随机码
			     

			     String hjjes[] =pf.getHjje().split("<#r#>");
			     String wszhs[]=pf.getWszmwszhs().split("<#r#>");
			     String ndzbs[]=pf.getWszmndzbs().split("<#r#>");
			     
				 for(int i = 0; i < hjjes.length; i++)
				 {
					// Wszm setWszm = (Wszm) resList.get(i);
					 String wszh =wszhs[i] ;
					 String pzzldm = CodeConstant.SMSB_SWWSZM_PZZLDM;
					 String ndzb = ndzbs[i];
					String pzlydm = CodeConstant.WSZM_JKS;
					   //System.out.println(i+"doReprint####old#######pzzldm="+pzzldm);
					   //System.out.println(i+"doReprint####old#######ndzb="+ndzb);
					   //System.out.println(i+"doReprint####old#######wszh="+wszh);
					   //System.out.println(i+"doReprint####old#######deFormat.format(setWszm.getHjje())="+hjjes[i]);
					 String retResult = ServiceProxy.setCancellation(ud, pzzldm, ndzb + wszh, 
							 StringUtil.getDouble(hjjes[i], 0.00), "1", "1", "0");
					 newNdzb = retResult.substring(0, 4); //年度字别
					 newWszh = retResult.substring(4); //完税证号
					 //System.out.println(i+"doReprint####new#######newNdzb="+newNdzb);
					 //System.out.println(i+"doReprint#####new######newWszh="+newWszh);
					 Wszm newWszm=new Wszm();
					 newWszm.setWszh(newWszh);
					 newWszm.setNdzb(newNdzb);
					// newWszm.setHjje(setWszm.getHjje());
					// System.out.println(i+"doReprint#####new######Hjje="+newWszm.getHjje());
					 newList.add(newWszm);
					 
					 if(wszmwszhs.length()>0){
						 wszmwszhs=wszmwszhs+"<#r#>";
					 }
					 wszmwszhs=wszmwszhs+newWszh;
					 
					 if(wszmndzbs.length()>0){
						 wszmndzbs=wszmndzbs+"<#r#>";
					 }
					 wszmndzbs=wszmndzbs+newNdzb;
					 
					 if(wszh != null && !wszh.equals(""))
					 {
						 boolean savSucc = false;
						 
						 String tempSjm=StringUtil.randomString(10);
						 if(sjms.length()>0){
							 sjms=sjms+"<#r#>";
						 }
						 sjms=sjms+tempSjm;
						 
						 //保存新完税证明数据
						 savSucc = saveNewWszhJks(conn,ud, wszh, ndzb, pzzldm, newWszh, newNdzb,tempSjm); 
						 //System.out.println("savSucc 3:"+savSucc+" newNdzb:"+newNdzb+" newWszh:"+newWszh);
						
						 //设置原完税证明为已打印
						 //savSucc = updateDYBZ(conn, pzlydm, wszh, ndzb, pzzldm);
						 
						 //System.out.println("savSucc 1:"+savSucc+" Ndzb:"+ndzb+" Wszh:"+wszh);
						 
						 //设置原完税证明打印次数
						 savSucc = updateDYCS(conn, pzlydm, wszh, ndzb, pzzldm);
						 //System.out.println("savSucc 2:"+savSucc+" Ndzb:"+ndzb+" Wszh:"+wszh);
						
						 //更新有效标志为：无效
						 savSucc = updateYXBZ(conn, pzlydm, wszh, ndzb, pzzldm); 
						 //System.out.println("savSucc 4:"+savSucc+" wszh:"+wszh+" ndzb:"+ndzb);
						 
						 if(!savSucc){
							 flag=false;
						 }
						 
//						 if(savSucc == true)
//						 {
//							 pf.setBccgbz("1");
//							 pf.setHeadWszh(newWszh);
//							 pf.setNdzb(newNdzb);
//						 }
//						 else
//						 {
//							 pf.setBccgbz("0");
//						 }
						 
						 
					 }
					 if(i<hjjes.length-1){
						   Thread.sleep(1000);
				     }
				 }
				 
				 pf.setWszmwszhs(wszmwszhs);
				 pf.setWszmndzbs(wszmndzbs);
				 pf.setWszmsjm(sjms);
				 
				 //System.out.println("temp后台##############pf.getWszmsjm()="+pf.getWszmsjm());
				 if(flag == true)
				 {
					 pf.setBccgbz("1");
					 pf.setHeadWszh(newWszh);
					 pf.setNdzb(newNdzb);
				 }
				 else
				 {
					 pf.setBccgbz("0");
				 }
				 
				 if(pf.getSspzlxdm() != null && pf.getSspzlxdm().equals(CodeConstant.WSZM_JKS))
				 {
					 pf.setYsphm(pf.getYsphm());
				 }
				 doReturnValue(ud, pf, isDo);
				
			}
			catch (BaseException ex1) 
			{

				ex1.printStackTrace();
		        throw new ApplicationException("新增主表信息出错！");
			}
		}
		catch (Exception ex)
		{
			//保存不成功，则作废刚刚取出的完税证号！
			try 
			{
				 String hjjes[] =pf.getHjje().split("<#r#>");
				 for(int i = 0; i < hjjes.length; i++)
				 {
					 Wszm newWszm = (Wszm) newList.get(i);
				     newWszh = ServiceProxy.setCancellation(ud, CodeConstant.SMSB_SWWSZM_PZZLDM, newWszm.getNdzb() + newWszm.getWszh(),StringUtil.getDouble(hjjes[i], 0.00), "1", "0", "0");
				     if(i<hjjes.length-1){
						   Thread.sleep(1000);
				     }
				 }
			}
			catch (Exception ex5) 
			{
				ex5.printStackTrace();
			}
			
			ex.printStackTrace();
			if(ex instanceof BaseException)
			{
				throw new ApplicationException(ex.getMessage());
			}
			throw new ApplicationException("新增主表信息出错！");
		}
		finally
		{
			SfDBResource.freeConnection(conn);
		}
		
    	return pf;
    }
    
    
	/**
     * 重打完税证明 1、取号,同时作废原完税证号，2、更新数据
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return 当前页面对应的Form对象
     * @throws BaseException
     */
    private Object doReprint (VOPackage vo)
        throws BaseException
    {
    	Connection conn = null;
    	String newWszh = ""; //新完税证号
    	String newNdzb = ""; //新年度字别
    	
		//获得前台提交的数据
		WszmForm pf = (WszmForm) vo.getData();
		//UserData对象
        UserData ud = (UserData) vo.getUserData();
		
		try
		{
			// 获取数据连接
			conn = SfDBResource.getConnection();
			
			//更新数据
			try 
			{
				 //执行doReprint  重新取号然后打印，1、取号,同时作废原完税证号，2、更新数据
				 //设置格式化数字
				 DecimalFormat deFormat = new DecimalFormat("#0.00");
				 
				 String isDo = "doReprint";
				 ArrayList resList =  doReturnValue(ud, pf, isDo);
				 for(int i = 0; i < resList.size(); i++)
				 {
					 Wszm setWszm = (Wszm) resList.get(0);
					 String wszh = setWszm.getWszh();
					 String pzzldm = setWszm.getPzzldm();
					 String ndzb = setWszm.getNdzb();
					 String pzlydm = setWszm.getPzlydm();
					   //System.out.println("doReprint###########=pzzldm"+pzzldm);
					   //System.out.println("doReprint###########=ndzb"+ndzb);
					   //System.out.println("doReprint###########=wszh"+wszh);
					   //System.out.println("doReprint###########=deFormat.format(setWszm.getHjje())"+deFormat.format(setWszm.getHjje()));
					 String retResult = ServiceProxy.setCancellation(ud, pzzldm, ndzb + wszh, 
							 StringUtil.getDouble(deFormat.format(setWszm.getHjje()), 0.00), "1", "1", "0");
					 newNdzb = retResult.substring(0, 4); //年度字别
					 newWszh = retResult.substring(4); //完税证号
					 //System.out.println("doReprint###########=newNdzb"+newNdzb);
					 //System.out.println("doReprint###########=newWszh"+newWszh);
					 
					 
					 
					 if(wszh != null && !wszh.equals(""))
					 {
						 boolean savSucc = false;
						 
						 //保存新完税证明数据
						 savSucc = saveNewWszh(conn,ud, wszh, ndzb, pzzldm, newWszh, newNdzb); 
						 //System.out.println("savSucc 3:"+savSucc+" newNdzb:"+newNdzb+" newWszh:"+newWszh);
						
						 //设置原完税证明为已打印
						 //savSucc = updateDYBZ(conn, pzlydm, wszh, ndzb, pzzldm);
						 
						 //System.out.println("savSucc 1:"+savSucc+" Ndzb:"+ndzb+" Wszh:"+wszh);
						 
						 //设置原完税证明打印次数
						 savSucc = updateDYCS(conn, pzlydm, wszh, ndzb, pzzldm);
						 //System.out.println("savSucc 2:"+savSucc+" Ndzb:"+ndzb+" Wszh:"+wszh);
						
						 //更新有效标志为：无效
						 savSucc = updateYXBZ(conn, pzlydm, wszh, ndzb, pzzldm); 
						 //System.out.println("savSucc 4:"+savSucc+" wszh:"+wszh+" ndzb:"+ndzb);
						 
						 if(savSucc == true)
						 {
							 pf.setBccgbz("1");
							 pf.setHeadWszh(newWszh);
							 pf.setNdzb(newNdzb);
						 }
						 else
						 {
							 pf.setBccgbz("0");
						 }
						 
						 if(pf.getSspzlxdm() != null && pf.getSspzlxdm().equals(CodeConstant.WSZM_JKS))
						 {
							 pf.setYsphm(pf.getYsphm());
						 }
					 }
				 }
				doReturnValue(ud, pf, isDo);
				
			}
			catch (BaseException ex1) 
			{

				ex1.printStackTrace();
		        throw new ApplicationException("新增主表信息出错！");
			}
		}
		catch (Exception ex)
		{
			//保存不成功，则作废刚刚取出的完税证号！
			try 
			{
				newWszh = ServiceProxy.setCancellation(ud, CodeConstant.SMSB_SWWSZM_PZZLDM, newNdzb + newWszh, StringUtil.getDouble(pf.getHjje(), 0.00), "1", "0", "0");
			}
			catch (Exception ex5) 
			{
				ex5.printStackTrace();
			}
			
			ex.printStackTrace();
			if(ex instanceof BaseException)
			{
				throw new ApplicationException(ex.getMessage());
			}
			throw new ApplicationException("新增主表信息出错！");
		}
		finally
		{
			SfDBResource.freeConnection(conn);
		}
		
    	return pf;
    }
    
    
    /**
     * 提供页面显示值
     * 
     * @param ud
     * @param form
     * @param isDo
     * @return
     * @throws BaseException
     */
    private ArrayList doReturnValue(UserData ud,WszmForm form,String isDo) throws BaseException
    {
    	Connection conn =null;
    	
    	ArrayList resList = new ArrayList();
    	ArrayList resMxList = new ArrayList();
    	ArrayList dataList = new ArrayList();
    	
    	try
    	{
    		conn = SfDBResource.getConnection();
    		SfDBAccess da = new SfDBAccess(conn);
    		
    		StringBuffer querySql = new StringBuffer();
			querySql.append("select  * from SBDB.SB_JL_KJSSWSZM ");
			querySql.append("where pzlydm = '"+form.getSspzlxdm()+"' and wszh = '"+form.getHeadWszh()+"' and pzzldm ='"+form.getPzzldm()+"' and ndzb ='"+form.getNdzb()+"' and yxbz='"+CodeConstant.WSZM_YXBZ_0+"' ");
			
			Debug.out("WszmQuerySql=== return ==" + querySql);
			//查询
			ResultSet rs = da.querySQL(querySql.toString());
			while (rs.next()) 
			{
				Wszm wszm = new Wszm();
	            
				wszm.setWszh(rs.getString("wszh"));
				wszm.setPzzldm(rs.getString("pzzldm"));
				wszm.setNdzb(rs.getString("ndzb"));
				wszm.setHjje(rs.getBigDecimal("HJJE"));
	            wszm.setPzlydm(rs.getString("pzlydm"));
	            wszm.setNsrsbh(rs.getString("nsrsbh"));
	            wszm.setNsrmc(rs.getString("nsrmc"));
				wszm.setYpzh(rs.getString("ypzh"));
				wszm.setYwszh(rs.getString("ywszh"));
				wszm.setYpzzldm(rs.getString("ypzzldm"));
				wszm.setYndzb(rs.getString("yndzb"));
				wszm.setDybz(rs.getString("dybz"));
				wszm.setDycs(rs.getString("dycs"));
				wszm.setSjm(rs.getString("sjm"));
				 
				resList.add(wszm);
			}
			if (resList.size() == 0 || resList == null) 
			{
				throw new ApplicationException("没有符合条件的信息！");
			}
			
			
			//设置格式化数字
	        DecimalFormat deFormat = new DecimalFormat("#0.00");
	        
			for(int i = 0; i < resList.size(); i++)
			{
				Wszm tmpWszm = (Wszm) resList.get(0);
				
				//从登记接口中获得信息
	            SWDJJBSJ dj = (SWDJJBSJ) InterfaceDj.getJBSJ_New(InterfaceDj.getZrrJsjdm(ud), ud);
	            if (dj == null)
	            {
	                throw new ApplicationException("获取登记信息出错！");
	            }
	            
				
				if(form.getSspzlxdm() != null && !form.getSspzlxdm().equals(""))
				{
					//1.缴款书完税证明
//					if(form.getSspzlxdm().equals(CodeConstant.WSZM_JKS))
//					{
//						//resMxList = getJksWszmList(form.getSspzlxdm(),form.getYpzh());
//					}
					
					//2.完税证完税证明
					if(form.getSspzlxdm().equals(CodeConstant.WSZM_LSWSZ) 
							 || form.getSspzlxdm().equals(CodeConstant.WSZM_GTWSZ) 
							 || form.getSspzlxdm().equals(CodeConstant.WSZM_QSWSZ) 
							 || form.getSspzlxdm().equals(CodeConstant.WSZM_CCWSZ))
					{
						resMxList = getWszWszmList(form.getSspzlxdm(),form.getYwszh(),form.getYndzb(),form.getYpzzldm());
					}
					
					 //3.保单完税证明
					 if(form.getSspzlxdm().equals(CodeConstant.WSZM_BD))
					 {
						 resMxList = getBdWszmList(form.getSspzlxdm(),form.getYwszh(),form.getYndzb(),form.getYpzzldm());
					 }
					
					 //4.收入退还书完税证明
					 if(form.getSspzlxdm().equals(CodeConstant.WSZM_SRTHS))
					 {
						 resMxList = getSrthsWszmList(form.getSspzlxdm(),form.getYpzh());
					 }
					
					 //5.手工完税凭证完税证明
					 if(form.getSspzlxdm().equals(CodeConstant.WSZM_SGWSPZ))
					 {
						 resMxList = getWszmWszmList(form.getSspzlxdm(),form.getYwszh(),form.getYndzb(),form.getYpzzldm());
					 }
				}
				
				if(!form.getSspzlxdm().equals(CodeConstant.WSZM_JKS))
				{
					if (resMxList.size() == 0 || resMxList == null) 
					 {
						 throw new ApplicationException("无符合条件的查询结果！");
					 }
				}
				 
				 Wszm wszm = new Wszm();
				 String mxYpzh = "";
				 String mxYwszh = "";
				 String mxSz = "";
				 String mxPmmc = "";
				 String mxSkssrq ="";
				 String mxSjse = "";
				 String mxRkrq = "";
				 
				 
				//如果是缴款书才进行分票操作
				 if(form.getSspzlxdm().equals(CodeConstant.WSZM_JKS))
				 {
					 /*
					 Map fpmap=new HashMap();
					 for (int k = 0; k < resMxList.size(); k++)
					 {
						 wszm = (Wszm) resMxList.get(k);
						 if(fpmap.containsKey(wszm.getYpzh())){
							 ((List)fpmap.get(wszm.getYpzh())).add(wszm);
						 }else{
							 List tempLs=new ArrayList();
							 tempLs.add(wszm);
							 fpmap.put(wszm.getYpzh(), tempLs);
						 }
					 }
					 //调用分票接口
					 ArrayList reFpList=FenPiaoUtil.fenPiao(fpmap);
					 for(int k=0;k<reFpList.size();k++){
						 List tempLs=Wszm2Map((List)reFpList.get(k));
						 reFpList.set(k, tempLs);
					 }
					form.setDyDataList(reFpList);
					*/ 
				 }else{
				 
				 
				 
				 for (int j = 0; j < resMxList.size(); j++)
				 {
					 wszm = (Wszm) resMxList.get(j);
					 HashMap map = new HashMap();
					 
					 if (wszm.getYpzh() == null)
					 {
						 map.put("ypzh", " ");
					 }
					 else
					 {
						 map.put("ypzh", wszm.getYpzh());
					 }
					 
					 if (wszm.getYwszh() == null)
					 {
						 map.put("ywszh", " ");
					 }
					 else
					 {
						 map.put("ywszh", wszm.getYwszh());
					 }
					 
					 if (wszm.getHjje() == null)
					 {
						 map.put("sjse", " ");
					 }
					 else
					 {
						
						 //收入退换书金额为负数“-￥”时，将格式调整为“（￥）”
						 if(form.getSspzlxdm().equals(CodeConstant.WSZM_SRTHS))
						 {
							 if(String.valueOf(wszm.getHjje()) != null && String.valueOf(wszm.getHjje()).startsWith("-"))
							 {
								 map.put("sjse", "("+deFormat.format(StringUtil.getDouble(String.valueOf(wszm.getHjje()).substring(1), 0.00))+")");
							 }
							 else
							 {
								 map.put("sjse", deFormat.format(StringUtil.getDouble(String.valueOf(wszm.getHjje()), 0.00)));
							 }
						 }
						 else
						 {
							 map.put("sjse", deFormat.format(StringUtil.getDouble(String.valueOf(wszm.getHjje()), 0.00)));
						 }
					 }
					 
					 if (wszm.getSkssksrq() == null && wszm.getSkssjsrq() == null)
					 {
						 map.put("skssrq", " ");
					 }
					 else
					 {
						 map.put("skssrq", wszm.getSkssksrq()+"-"+wszm.getSkssjsrq());
					 }
					 
					 if (wszm.getYpzrkrq() == null)
					 {
						 map.put("ypzrkrq", " ");
					 }
					 else
					 {
						 map.put("ypzrkrq", wszm.getYpzrkrq());
					 }
					 
					 map.put("szmc", CodeUtils.getCodeBeanLabel("DM_SZSM", wszm.getSzdm()));
					 map.put("szsmmc", CodeUtils.getCodeBeanLabel("DM_SZSM", wszm.getSzsmdm()));
					 
					 mxYpzh += map.get("ypzh") + ";;";
					 mxYwszh += map.get("ywszh") + ";;";
					 mxSz += map.get("szmc") + ";;";
					 mxPmmc += map.get("szsmmc") + ";;";
					 mxSkssrq += map.get("skssrq") + ";;";
					 mxSjse += map.get("sjse") + ";;";
					 mxRkrq += map.get("ypzrkrq") + ";;";
					 
					 dataList.add(map);
				  }
				  form.setDyDataList(dataList);
				 }
				 
				 
				 form.setMxYpzh(mxYpzh);
				 form.setMxYwszh(mxYwszh);
				 form.setMxSz(mxSz);
				 form.setMxPmmc(mxPmmc);
				 form.setMxSkssrq(mxSkssrq);
				 form.setMxRkrq(mxRkrq);
				 form.setMxSjse(mxSjse);
				 
				 
				 
				 
				 form.setSwjgzzjgdm(dj.getSwjgzzjgdm());
				 form.setSwjgzzjgmc(dj.getSwjgzzjgmc());
				 
				 //设置打印次数
				 HashMap tmpdycsmap = getWszmDycs(tmpWszm.getPzlydm(), tmpWszm.getYpzh(), tmpWszm.getYpzzldm(), tmpWszm.getYwszh(), tmpWszm.getYndzb());
				 
				 int tmpdycs = Integer.parseInt((String) tmpdycsmap.get("dycs"));
				 if(isDo != "doSuccess")
				 {
					 tmpdycs = tmpdycs +1;
				 }
				 
				 if(!form.getSspzlxdm().equals(CodeConstant.WSZM_JKS))
				 {
				  form.setHjje(deFormat.format(tmpWszm.getHjje()));
				 }
				 form.setHjjedx(Currency.convert(tmpWszm.getHjje()));
				
				 //收入退换书金额为负数“-￥”时，将格式调整为“（￥）”
				 if(form.getSspzlxdm().equals(CodeConstant.WSZM_SRTHS))
				 {
					 if(form.getHjje() != null && form.getHjje().startsWith("-"))
					 {
						 form.setHjje2("("+form.getHjje().substring(1)+")"); //合计金额
						 form.setHjjedx2(Currency.convert(Double.parseDouble(form.getHjje().substring(1)))); //合计金额大写
					 }
					 else
					 {
						 form.setHjje2(form.getHjje()); //合计金额
						 form.setHjjedx2(Currency.convert(Double.parseDouble(form.getHjje()))); //合计金额大写
					 }
				 }
				 else
				 {
					 if(!form.getSspzlxdm().equals(CodeConstant.WSZM_JKS))
					 {
					  form.setHjje2(form.getHjje()); //合计金额
					  form.setHjjedx2(Currency.convert(Double.parseDouble(form.getHjje()))); //合计金额大写
					 }
				 }
				 
				 form.setHeadWszh(tmpWszm.getWszh());
				 form.setPzzldm(tmpWszm.getPzzldm());
				 form.setNdzb(tmpWszm.getNdzb());
				 form.setSspzlxdm(tmpWszm.getPzlydm());
				 form.setNsrsbh(tmpWszm.getNsrsbh());
				 form.setNsrmc(tmpWszm.getNsrmc());
				 form.setYpzh(tmpWszm.getYpzh());
				 form.setYwszh(tmpWszm.getYwszh());
				 form.setYpzzldm(tmpWszm.getYpzzldm());
				 form.setYndzb(tmpWszm.getYndzb());
				 form.setDycs(String.valueOf(tmpdycs));
				 if(!form.getSspzlxdm().equals(CodeConstant.WSZM_JKS))
				 {
				    form.setWszmsjm(tmpWszm.getSjm());
				 }
				 form.setLrr(ud.getYhmc());
				
			}
			
    	}
    	catch(Exception e)
    	{
			e.printStackTrace();
		}
    	finally
		{
    		SfDBResource.freeConnection(conn);
		}
    	
		return resList;
    }
	
    /**
     * 提供页面显示值
     * 
     * @param ud
     * @param form
     * @param isDo
     * @return
     * @throws BaseException
     */
    private ArrayList getJksWspzs(UserData ud,WszmForm form) throws BaseException
    {
    	Connection conn =null;
    	
    	ArrayList resList = new ArrayList();
    	
    	try
    	{
    		conn = SfDBResource.getConnection();
    		SfDBAccess da = new SfDBAccess(conn);
    		
    		
    		String wszhs[]=form.getWszmwszhs().split("<#r#>");
       	    String ndzbs[]=form.getWszmndzbs().split("<#r#>");
	       	StringBuffer buf=new StringBuffer();
	 		for(int m=0;m<wszhs.length;m++){
	 			if(buf.length()>0){
	 			  buf.append(" or ");	
	 			}
	 			buf.append("(wszh='"+wszhs[m]+"' and ndzb='"+ndzbs[m]+"')");
	 		}
    		
    		StringBuffer querySql = new StringBuffer();
			querySql.append("select  * from SBDB.SB_JL_KJSSWSZM ");
			//querySql.append("where pzlydm = '"+form.getSspzlxdm()+"' and wszh = '"+form.getHeadWszh()+"' and pzzldm ='"+form.getPzzldm()+"' and ndzb ='"+form.getNdzb()+"' and yxbz='"+CodeConstant.WSZM_YXBZ_0+"' ");
			querySql.append("where pzlydm = '"+form.getSspzlxdm()+"' and pzzldm ='"+form.getPzzldm()+ "' and yxbz='"+CodeConstant.WSZM_YXBZ_0+"' ");
			
			if(buf.length()>0){
				querySql.append(" and ("+buf.toString()+")");
			}
			
			querySql.append(" order by WSZH asc ");
			
			Debug.out("getJksWspzs##############querySql=" + querySql.toString());
			//查询
			ResultSet rs = da.querySQL(querySql.toString());
			while (rs.next()) 
			{
				Wszm wszm = new Wszm();
	            
				wszm.setWszh(rs.getString("wszh"));
				wszm.setPzzldm(rs.getString("pzzldm"));
				wszm.setNdzb(rs.getString("ndzb"));
				wszm.setHjje(rs.getBigDecimal("HJJE"));
	            wszm.setPzlydm(rs.getString("pzlydm"));
	            wszm.setNsrsbh(rs.getString("nsrsbh"));
	            wszm.setNsrmc(rs.getString("nsrmc"));
				wszm.setYpzh(rs.getString("ypzh"));
				wszm.setYwszh(rs.getString("ywszh"));
				wszm.setYpzzldm(rs.getString("ypzzldm"));
				wszm.setYndzb(rs.getString("yndzb"));
				wszm.setDybz(rs.getString("dybz"));
				wszm.setDycs(rs.getString("dycs"));
				wszm.setSjm(rs.getString("sjm"));
				 
				resList.add(wszm);
			}
			if (resList.size() == 0 || resList == null) 
			{
				throw new ApplicationException("没有符合条件的信息！");
			}
    	}
    	catch(Exception e)
    	{
			e.printStackTrace();
		}
    	finally
		{
    		SfDBResource.freeConnection(conn);
		}
    	
		return resList;
    }
    
    
	
	/**
	  * 查询完税证信息
	  * @param pf WszcxForm
	  * @param da SfDBAccess
	  * @return resList ArrayList
	  * @throws BaseException
	  * @throws SQLException
	  */
	private List getList(SfDBAccess da, WszmForm pf, UserData ud) 
		throws BaseException, SQLException 
	{
		ArrayList resList = new ArrayList();
		try
		{
			 Map dataMap = (Map) pf.getData();
			 String sspzlxdm = (String) dataMap.get("sspzlxdm");
			 String query_jsjdm = (String) dataMap.get("query_jsjdm");
			 String query_sphm = (String) dataMap.get("query_sphm");
			 String query_qsrq = (String) dataMap.get("query_qsrq");
			 String query_jzrq = (String) dataMap.get("query_jzrq");
			 String query_wszh = (String) dataMap.get("query_wszh");
			 String query_ndzb = (String) dataMap.get("query_ndzb");
			 String query_bdwsz = (String) dataMap.get("query_bdwsz");
			 String query_hphm = (String) dataMap.get("query_hphm");
			 String query_cjbh = (String) dataMap.get("query_cjbh");
			 String query_srthsh = (String) dataMap.get("query_srthsh");
			 String query_wszm = (String) dataMap.get("query_wszm");
			 String query_wszmh = (String) dataMap.get("query_wszmh");
			 String pzlxdm = (String) dataMap.get("pzlxdm");
			   
			 String ssdwdm = ud.getSsdwdm();
			 String yhjb = ud.getYhjb();
			 
			 //System.out.println("sspzlxdm:"+sspzlxdm+" pzlxdm:"+pzlxdm);
			 //System.out.println("query_jsjdm:"+query_jsjdm+" query_sphm:"+query_sphm+" query_qsrq:"+query_qsrq+" query_jzrq:"+query_jzrq);
			 //System.out.println("query_wszh:"+query_wszh+" query_ndzb:"+query_ndzb);
			 //System.out.println("query_bdwsz:"+query_bdwsz+" query_hphm:"+query_hphm+" query_cjbh:"+query_cjbh);
			 //System.out.println("query_srthsh:"+query_srthsh+" query_wszm:"+query_wszm);
			 //System.out.println("ssdwdm:"+ssdwdm+" yhjb:"+yhjb);
			 
			 if((sspzlxdm != null) && !sspzlxdm.equals(""))
			 {
				 //设置查询条件
				 //1.缴款书
				 if(sspzlxdm.equals(CodeConstant.WSZM_JKS))
				 {
					 resList = this.getJksList(da,sspzlxdm,query_jsjdm,query_sphm,query_qsrq,query_jzrq);
				 }
				
				 //2.完税证
				 if(sspzlxdm.equals(CodeConstant.WSZM_LSWSZ) 
						 || sspzlxdm.equals(CodeConstant.WSZM_GTWSZ) 
						 || sspzlxdm.equals(CodeConstant.WSZM_QSWSZ) 
						 || sspzlxdm.equals(CodeConstant.WSZM_CCWSZ))
				 {
					 resList = this.getWszList(da,sspzlxdm,query_ndzb,query_wszh,query_qsrq,query_jzrq);
				 }
				
				 //3.保单
				 if(sspzlxdm.equals(CodeConstant.WSZM_BD))
				 {
					 resList = this.getBdList(da,sspzlxdm,query_bdwsz,query_hphm,query_cjbh,query_qsrq,query_jzrq);
				 }
				
				 //4.收入退还书
				 if(sspzlxdm.equals(CodeConstant.WSZM_SRTHS))
				 {
					 resList = this.getSrthsList(da,sspzlxdm,query_srthsh,query_qsrq,query_jzrq);
				 }
				 
				 //5.手工完税凭证
				 if(sspzlxdm.equals(CodeConstant.WSZM_SGWSPZ))
				 {
					 resList = this.getWszmList(da,sspzlxdm,query_wszm,query_wszmh,pzlxdm,query_qsrq,query_jzrq);
				 }
				 
				 Debug.out("resList size:"+resList.size()); 
				 if (resList.size() == 0 || resList == null) 
				 {
					 throw new ApplicationException("没有符合条件的信息！");
				 }
				 
			 }
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
           throw new ApplicationException(ex.getMessage());
		} 
		
		return resList;
	}

	
	/**
	 * 获得完税证明开具来源--缴款书
	 * @param da
	 * @param jsjdmCon
	 * @param sphmCon
	 * @param whereCon
	 * @return
	 * @throws BaseException
	 */
	private ArrayList getJksList(SfDBAccess da, String sspzlxdm, String query_jsjdm, String query_sphm, String query_qsrq,String query_jzrq) 
			throws BaseException 
	{
		ArrayList tmpList = new ArrayList();
		
		String jsjdmCon = "";
		String jsjdm2Con = "";
		String jsjdm3Con = "";
		String sphmCon = "";
		String sphm2Con = "";
		String sphm3Con = "";
		String jsjdm = "";
		String whereCon = "";
		
		try
		{
			//计算机代码
			 if ((query_jsjdm != null) && !query_jsjdm.equals(""))
			 {
				 jsjdmCon = jsjdmCon + " and t.jsjdm = '" + query_jsjdm + "'";
				 jsjdm2Con = jsjdm2Con + " and t1.jsjdm = '" + query_jsjdm + "'";
				 jsjdm3Con = jsjdm3Con + " and a.jsjdm = '" + query_jsjdm + "'";
				 jsjdm = query_jsjdm;
			 }
			
			 //税票号码
			 if ((query_sphm != null) && !query_sphm.equals(""))
			 {
				 sphmCon = sphmCon + " and t.sphm = '" + query_sphm + "' ";
				 sphm2Con = sphm2Con + " and t1.sphm = '" + query_sphm + "' ";
				 sphm3Con = sphm3Con + " and a.sphm = '" + query_sphm + "' ";
				 
				 jsjdm = query_sphm.substring(0, 8);
				 
				 jsjdmCon = jsjdmCon + " and t.jsjdm = '" + jsjdm + "' ";
				 jsjdm2Con = jsjdm2Con + " and t1.jsjdm = '" + jsjdm + "' ";
				 jsjdm3Con = jsjdm3Con + " and a.jsjdm = '" + jsjdm + "'";
			 }
			 
			 //入库时间
			 if ((query_qsrq != null) && !query_qsrq.equals(""))
			 {
				 whereCon = whereCon + " and a.zyrq>=to_date('" + query_qsrq +
				    " 00:00:00','yyyy-mm-dd hh24:mi:ss') ";
			 }
			 if ((query_jzrq != null) && !query_jzrq.equals(""))
			 {
				 whereCon = whereCon + " and a.zyrq<=to_date('" + query_jzrq +
		            " 23:59:59','yyyy-mm-dd hh24:mi:ss') ";
			 }
			 
			StringBuffer querySql = new StringBuffer();
			querySql.append("select a.jsjdm NSRSBH, a.bz BZ,a.sjly SJLY,a.swjgzzjgdm SSSWJGZZJGDM,a.sphm YPZH, a.sphm YSPHM, sum(a.rkje) HJJE, ");
			querySql.append("(select dm1.swjgzzjgmc from dmdb.gy_dm_swjgzzjg dm1 where a.swjgzzjgdm = dm1.swjgzzjgdm(+)) SSSWJGZZJGMC ");
			querySql.append("from (select sphm, jkpzh, jsjdm, sklxdm, swjgzzjgdm, zsswjgzzjgdm,szdm, yskmdm,skssksrq, skssjsrq, xjrq, zyrq, rkje, bz, nvl(sjly, '11') sjly, fsdm ");
			querySql.append("from sbdb.sb_jl_sbjkzb t where substr(t.zwbs, 2, 1)<> 0 and substr(t.zwbs,10,1)= '0' ");
			querySql.append(jsjdmCon);
			querySql.append(sphmCon);
			querySql.append(" union all ");
			querySql.append("select t.sphm, t1.jkpzh, t1.jsjdm,t1.sklxdm, t1.swjgzzjgdm, t1.swjgzzjgdm, t2.szdm, t2.yskmdm, t2.skssksrq, t2.skssjsrq, t2.xjrq, t.zyrq, t2.rkje, t.bz, nvl(t.sjly, '11') sjly, t.fsdm ");
			querySql.append("from JKDB.KJ_JL_ZWTZJKSZB t1,JKDB.KJ_JL_ZWTZJKSMX t2, sbdb.sb_jl_sbjkzb t ");
			querySql.append("where t1.jkpzh=t2.jkpzh  and t1.zbxh=t2.zbxh and t1.jkpzh = t.jkpzh and t2.rkje>0 ");
			querySql.append(jsjdm2Con);
			querySql.append(sphmCon);
			querySql.append(" union all ");
			querySql.append("select t.sphm, t.jkpzh, t.jsjdm, t.sklxdm,t.swjgzzjgdm, t.zsswjgzzjgdm, t.szdm, t.yskmdm,t.skssksrq,t.skssjsrq,t.xjrq,a.zyrq,-t.rkje,t.bz,nvl(t.sjly, '11') sjly, t.fsdm ");
			querySql.append("from JKDB.KJ_JL_GZTZS a, sbdb.sb_jl_sbjkzb t ");
			querySql.append("where a.ysjksh=t.jkpzh and a.dzbz = '1' and a.gztzslx = '2' ");
			querySql.append(jsjdmCon);
			querySql.append(sphmCon);
			querySql.append(" ) a ");
			querySql.append("where 1=1 ");
			querySql.append(jsjdm3Con);
			querySql.append(sphm3Con);
			querySql.append(whereCon);
			querySql.append(" group by a.jsjdm,a.bz,a.sjly,a.swjgzzjgdm,a.sphm ");
			querySql.append(" having sum(a.rkje)<>0 ");
			//querySql.append("order by a.jkpzh ");
			
			Debug.out("QuerySql=== JKS ==" + querySql);
			
			//查询
			ResultSet rs = da.querySQL(querySql.toString());
			while (rs.next()) 
			{
				Wszm wszm = new Wszm();
	            
	            //获取完税证明打印次数
	            HashMap dycsmap = getWszmDycs(sspzlxdm,rs.getString("YPZH"),"","","");
	            
	            wszm.setPzlydm(sspzlxdm);
	            wszm.setNsrsbh(rs.getString("NSRSBH"));
	            wszm.setNsrmc(getNsrmc(jsjdm,rs.getString("SJLY"),rs.getString("BZ")));
				wszm.setSsswjgzzjgdm(rs.getString("SSSWJGZZJGDM"));
				wszm.setSsswjgzzjgmc(rs.getString("SSSWJGZZJGMC"));
				wszm.setHjje(rs.getBigDecimal("HJJE"));
				wszm.setYsphm(rs.getString("YSPHM"));
				wszm.setYpzh(rs.getString("YPZH"));
				wszm.setYwszh("");
				wszm.setYpzzldm("");
				wszm.setYndzb("");
				wszm.setDycs((String) dycsmap.get("dycs"));
				 
				tmpList.add(wszm);
			}
			
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
            throw new ApplicationException(ex.getMessage());
		}
		return tmpList;
	}
	
	
	/**
	 * 获得完税证明开具来源--完税证
	 * @param da
	 * @param jsjdmCon
	 * @param sphmCon
	 * @param whereCon
	 * @return
	 * @throws BaseException
	 */
	private ArrayList getWszList(SfDBAccess da, String sspzlxdm, String query_ndzb, String query_wszh, String query_qsrq,String query_jzrq) 
			throws BaseException 
	{
		ArrayList tmpList = new ArrayList();
		
		String wszhCon = "";
		String ndzbCon = "";
		String whereCon = "";
		
		try
		{
			//完税证号码
			 if ((query_wszh != null) && !query_wszh.equals(""))
			 {
				 if(sspzlxdm.equals(CodeConstant.WSZM_CCWSZ))
				 {
					 wszhCon = wszhCon + " and a.wspzh = '" + query_wszh + "'";
				 }
				 else
				 {
					 wszhCon = wszhCon + " and a.wszh = '" + query_wszh + "'";
				 }
			 }
			
			 //完税证年度字别
			 if ((query_ndzb != null) && !query_ndzb.equals(""))
			 {
				 ndzbCon = ndzbCon + " and a.ndzb = '" + query_ndzb + "'";
			 }
			 
			 //入库时间
			 if ((query_qsrq != null) && !query_qsrq.equals(""))
			 {
				 whereCon = whereCon + " and a.cjrq>=to_date('" + query_qsrq +
				    " 00:00:00','yyyy-mm-dd hh24:mi:ss')";
			 }
			 if ((query_jzrq != null) && !query_jzrq.equals(""))
			 {
				 whereCon = whereCon + " and a.cjrq<=to_date('" + query_jzrq +
		            " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
			 }
			
			//设置查询条件
			   StringBuffer querySql = new StringBuffer("SELECT * FROM ( ");
			   if((sspzlxdm != null) && !sspzlxdm.equals(""))
			   {
				   //1.个体工商户
				   if(sspzlxdm.equals(CodeConstant.WSZM_GTWSZ))
				   {
					   querySql.append("select a.wszh YWSZH, a.ndzb YNDZB, a.pzzldm YPZZLDM, a.hjsjje HJJE, a.nsrjsjdm NSRSBH, ");
					   querySql.append("(select dm1.swjgzzjgmc from dmdb.gy_dm_swjgzzjg dm1 where a.swjgzzjgdm = dm1.swjgzzjgdm(+)) SSSWJGZZJGMC, a.swjgzzjgdm SSSWJGZZJGDM ");
					   querySql.append("from sbdb.sb_jl_gtgshwszz a ");
					   querySql.append("where nvl(a.clbjdm,'0000000') <> '"+CodeConstant.WSZ_CLBJDM+"' ");
					   querySql.append(wszhCon);
					   querySql.append(ndzbCon);
					   querySql.append(whereCon);
				   }
				   //2.零散征收
				   if(sspzlxdm.equals(CodeConstant.WSZM_LSWSZ))
				   {
					   
					   querySql.append("select a.wszh YWSZH, a.ndzb YNDZB, a.pzzldm YPZZLDM, a.hjsjje HJJE, a.zjlxdm ZJLXDM, a.zjhm ZJHM, a.nsrmc NSRMC, ");
					   querySql.append("(select dm1.swjgzzjgmc from dmdb.gy_dm_swjgzzjg dm1 where a.swjgzzjgdm = dm1.swjgzzjgdm(+)) SSSWJGZZJGMC, a.swjgzzjgdm SSSWJGZZJGDM ");
					   querySql.append("from sbdb.sb_jl_lsswszz a ");
					   querySql.append("where nvl(a.clbjdm,'0000000') <> '"+CodeConstant.WSZ_CLBJDM+"' ");
					   querySql.append(wszhCon);
					   querySql.append(ndzbCon);
					   querySql.append(whereCon);
				   }
				   //3.契税征收
				   if(sspzlxdm.equals(CodeConstant.WSZM_QSWSZ))
				   {
					   querySql.append("select a.wszh YWSZH, a.ndzb YNDZB, a.pzzldm YPZZLDM, a.hjsjje HJJE, a.nsrjsjdm NSRSBH, ");
					   querySql.append("(select dm1.swjgzzjgmc from dmdb.gy_dm_swjgzzjg dm1 where a.swjgzzjgdm = dm1.swjgzzjgdm(+)) SSSWJGZZJGMC, a.swjgzzjgdm SSSWJGZZJGDM ");
					   querySql.append("from sbdb.sb_jl_qswszz a ");
					   querySql.append("where nvl(a.clbjdm,'0000000') <> '"+CodeConstant.WSZ_CLBJDM+"' ");
					   querySql.append(wszhCon);
					   querySql.append(ndzbCon);
					   querySql.append(whereCon);
				   }
				   //4.车船征收
				   if(sspzlxdm.equals(CodeConstant.WSZM_CCWSZ))
				   {
					   querySql.append("select a.wspzh YWSZH, a.ndzb YNDZB, a.pzzldm YPZZLDM, a.sjzse HJJE, " );
					   querySql.append("a.hphm HPHM,a.clbh CLBH, a.hpzldm HPZLDM, a.nsrmc NSRMC, a.lydm LYDM, ");
					   querySql.append("(select dm1.swjgzzjgmc from dmdb.gy_dm_swjgzzjg dm1 where a.swjgdm = dm1.swjgzzjgdm(+)) SSSWJGZZJGMC, a.swjgdm SSSWJGZZJGDM ");
					   querySql.append("FROM sbdb.sb_jl_ccwszz a ");
					   querySql.append("where nvl(a.clbh,'0000000') <>'7777' and nvl(a.hphm,'0000000') <>'15571279' and a.wspzlxdm <> '"+CodeConstant.CCWSZ_LYDM_04+"' ");
					   querySql.append(wszhCon);
					   querySql.append(ndzbCon);
					   querySql.append(whereCon);
				   }
			   }
		   
			   querySql.append(" ) ORDER BY YWSZH,YNDZB,YPZZLDM ");
			   
			   Debug.out("WszQuerySql=====" + querySql);
			 
			   //查询
			   ResultSet rs = da.querySQL(querySql.toString());
			   while (rs.next()) 
			   {
				   Wszm wszm = new Wszm();
				   
				 //获取契税和个体工商户纳税人计算机代码作为纳税人识别号、通过纳税人计算机代码获取纳税人名称
				   if((sspzlxdm.equals(CodeConstant.WSZM_GTWSZ)) || (sspzlxdm.equals(CodeConstant.WSZM_QSWSZ)))
				   {
					   wszm.setNsrsbh(rs.getString("NSRSBH"));
					   wszm.setNsrmc(getNsrmc(rs.getString("NSRSBH"),"",""));
					   
					   setWszmValue(sspzlxdm, rs, wszm);
					   
					   tmpList.add(wszm);
					   
					   //System.out.println("sspzlxdm:"+sspzlxdm+" Nsrsbh:"+wszm.getNsrsbh()+" Nsrmc:"+wszm.getNsrmc());
				   }
				
				   //获取零散证件类型和证件号码作为纳税人识别号、纳税人名称作为纳税人名称
				   if(sspzlxdm.equals(CodeConstant.WSZM_LSWSZ))
				   {
					   HashMap zjmap = getZjlxmc(rs.getString("ZJLXDM"));
					   //获取证件类型名称
					   String zjlxmc = (String) zjmap.get("zjlxmc");
					   wszm.setNsrsbh(zjlxmc+" "+rs.getString("ZJHM"));
					   wszm.setNsrmc(rs.getString("NSRMC"));
					   
					   setWszmValue(sspzlxdm, rs, wszm);
					   
					   tmpList.add(wszm);
					   
					   //System.out.println("sspzlxdm:"+sspzlxdm+" Nsrsbh:"+wszm.getNsrsbh()+" Nsrmc:"+wszm.getNsrmc());
				   }
				 
				   //获取车船税号牌号码、号牌种类名称和车辆编号作为纳税人识别号（如果号牌号码为空，则提供车辆编号）
				   if(sspzlxdm.equals(CodeConstant.WSZM_CCWSZ))
				   {
					   String lydm = rs.getString("LYDM");
					   String hphm = rs.getString("HPHM");
					   String clbh = rs.getString("CLBH");
					   String hpzldm = rs.getString("HPZLDM");
					   String hpzlmc ="";
					   
					   //判断是否为税务所直开(LYDM IN ('01','21'))，
					   //1.直开的完税证直接能够打印完税证明
					   if(lydm != null && !lydm.equals(""))
					   {
						   if(lydm.equals(CodeConstant.CCWSZ_LYDM_01) || lydm.equals(CodeConstant.CCWSZ_LYDM_01))
						   {
							   if(hpzldm != null && !hpzldm.equals(""))
							   {
								   HashMap hpzlmap = getHpzlmc(rs.getString("HPZLDM"));
								
								   //获取号牌号码名称
								   hpzlmc = (String) hpzlmap.get("hpzlmc");
							   }
							   
							   if((hphm == null) || hphm.equals(""))
							   {
								   wszm.setNsrsbh(clbh);
							   }
							   else
							   {
								   wszm.setNsrsbh(hphm+" "+hpzlmc);
							   }
							   
							   wszm.setNsrmc(rs.getString("NSRMC"));
							   
							   setWszmValue(sspzlxdm, rs, wszm);
							   
							   tmpList.add(wszm);
							   
							   //System.out.println("sspzlxdm:"+sspzlxdm+" clbh:"+clbh+" hphm:"+hphm);
							   //System.out.println("sspzlxdm:"+sspzlxdm+" Nsrsbh:"+wszm.getNsrsbh()+" Nsrmc:"+wszm.getNsrmc());
							   
							 //2.非直开的完税证需判断对应的缴款书信息是否已经入库
						   }
						   else
						   {
							   HashMap rkbzmap = getCcwszRkbz(rs.getString("YWSZH"),rs.getString("YNDZB"));
							   //获取入库标志
							   String zwbs = (String) rkbzmap.get("zwbs");
							   
							   //System.out.println("zwbs:"+zwbs);
							   
							   //缴款书已入库的可直接开据税收完税证明
							   if(zwbs!= null && !zwbs.equals(""))
							   {
								   if(zwbs.equals(CodeConstant.CCWSZ_RKBZ_1))
								   {
									   if(hpzldm != null && !hpzldm.equals(""))
									   {
										   HashMap hpzlmap = getHpzlmc(rs.getString("HPZLDM"));
										
										   //获取号牌号码名称
										   hpzlmc = (String) hpzlmap.get("hpzlmc");
									   }
									   
									   if((hphm == null) || hphm.equals(""))
									   {
										   wszm.setNsrsbh(clbh);
									   }
									   else
									   {
										   wszm.setNsrsbh(hphm+" "+hpzlmc);
									   }
									   
									   wszm.setNsrmc(rs.getString("NSRMC"));
									   
									   setWszmValue(sspzlxdm, rs, wszm);
									   
									   tmpList.add(wszm);
									   
									   //System.out.println("sspzlxdm:"+sspzlxdm+" clbh:"+clbh+" hphm:"+hphm);
									   //System.out.println("sspzlxdm:"+sspzlxdm+" Nsrsbh:"+wszm.getNsrsbh()+" Nsrmc:"+wszm.getNsrmc());
								   }
							   }
						   }
					   }
				   }
			   }
			   rs.close();
			   //Debug.out("resList size:"+tmpList.size());
			
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
            throw new ApplicationException(ex.getMessage());
		}
		
		return tmpList;
	}
	
	
	
	/**
	 * 获得完税证明开具来源--保单
	 * @param da
	 * @param jsjdmCon
	 * @param sphmCon
	 * @param whereCon
	 * @return
	 * @throws BaseException
	 */
	private ArrayList getBdList(SfDBAccess da, String sspzlxdm, String query_bdwsz, String query_hphm, String query_cjbh, String query_qsrq,String query_jzrq) 
			throws BaseException 
	{
		ArrayList tmpList = new ArrayList();
		
		String bdwszhCon = "";
		String hphmCon = "";
		String cjbhCon = "";
		String whereCon = "";
		
		try
		{
			//完税证号码保单
			 if ((query_bdwsz != null) && !query_bdwsz.equals(""))
			 {
				 bdwszhCon = bdwszhCon + " and a.wspzh = '" + query_bdwsz + "'";
			 }
			
			 //号牌号码
			 if ((query_hphm != null) && !query_hphm.equals(""))
			 {
				 hphmCon = hphmCon + " and a.hphm = '" + query_hphm + "'";
			 }
			
			 //车架号
			 if ((query_cjbh != null) && !query_cjbh.equals(""))
			 {
				 cjbhCon = cjbhCon + " and a.clsbdh = '" + query_cjbh + "'";
			 }
			 
			 //入库时间
			 if ((query_qsrq != null) && !query_qsrq.equals(""))
			 {
				 whereCon = whereCon + " and a.cjrq>=to_date('" + query_qsrq +
				    " 00:00:00','yyyy-mm-dd hh24:mi:ss')";
			 }
			 if ((query_jzrq != null) && !query_jzrq.equals(""))
			 {
				 whereCon = whereCon + " and a.cjrq<=to_date('" + query_jzrq +
		            " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
			 }
			 
			 StringBuffer querySql = new StringBuffer();
			 
			 if((sspzlxdm != null) && !sspzlxdm.equals(""))
			   {
				   //1.保险代征
				   if(sspzlxdm.equals(CodeConstant.WSZM_BD))
				   {
					   querySql.append("select a.wspzh YWSZH, a.ndzb YNDZB, a.pzzldm YPZZLDM, a.sjzse HJJE, " );
					   querySql.append("a.hphm HPHM,a.clbh CLBH, a.hpzldm HPZLDM, a.nsrmc NSRMC, a.lydm LYDM, ");
					   querySql.append("(select dm1.swjgzzjgmc from dmdb.gy_dm_swjgzzjg dm1 where a.swjgdm = dm1.swjgzzjgdm(+)) SSSWJGZZJGMC, a.swjgdm SSSWJGZZJGDM ");
					   querySql.append("FROM sbdb.sb_jl_ccwszz a ");
					   querySql.append("where nvl(a.clbh,'0000000') <>'7777' and nvl(a.hphm,'0000000') <>'15571279' and a.lydm ='"+CodeConstant.CCWSZ_LYDM_04+"' ");
					   querySql.append(bdwszhCon);
					   querySql.append(hphmCon);
					   querySql.append(cjbhCon);
					   querySql.append(whereCon);
				   }
			   }
			   
			   Debug.out("WszQuerySql===  bd  ==" + querySql);
			   
			 
			   //查询
			   ResultSet rs = da.querySQL(querySql.toString());
			   while (rs.next()) 
			   {
				   Wszm wszm = new Wszm();
				
				   //获取保单车船税号牌号码、号牌种类名称和车辆编号作为纳税人识别号（如果号牌号码为空，则提供车辆编号）
				   if(sspzlxdm.equals(CodeConstant.WSZM_BD))
				   {
					   String lydm = rs.getString("LYDM");
					   String hphm = rs.getString("HPHM");
					   String clbh = rs.getString("CLBH");
					   String hpzldm = rs.getString("HPZLDM");
					   String hpzlmc ="";
					   
					   //判断是否为保险代征（来源代码：04）
					   if(lydm != null && !lydm.equals(""))
					   {
						   if(lydm.equals(CodeConstant.CCWSZ_LYDM_04))
						   {
							   HashMap rkbzmap = getCcwszRkbz(rs.getString("YWSZH"),rs.getString("YNDZB"));
							   //获取入库标志
							   String zwbs = (String) rkbzmap.get("zwbs");
							   
							   //System.out.println("zwbs:"+zwbs);
							   
							   //缴款书已入库的可直接开据税收完税证明
							   if(zwbs!= null && !zwbs.equals(""))
							   {
								   if(zwbs.equals(CodeConstant.CCWSZ_RKBZ_1))
								   {
									   if(hpzldm != null && !hpzldm.equals(""))
									   {
										   HashMap hpzlmap = getHpzlmc(rs.getString("HPZLDM"));
										
										   //获取号牌号码名称
										   hpzlmc = (String) hpzlmap.get("hpzlmc");
									   }
									   
									   if((hphm == null) || hphm.equals(""))
									   {
										   wszm.setNsrsbh(clbh);
									   }
									   else
									   {
										   wszm.setNsrsbh(hphm+" "+hpzlmc);
									   }
									   
									   wszm.setNsrmc(rs.getString("NSRMC"));
									   
									   setWszmValue(sspzlxdm, rs, wszm);
									   
									   tmpList.add(wszm);
									   
									   //System.out.println("sspzlxdm:"+sspzlxdm+" clbh:"+clbh+" hphm:"+hphm);
									   //System.out.println("sspzlxdm:"+sspzlxdm+" Nsrsbh:"+wszm.getNsrsbh()+" Nsrmc:"+wszm.getNsrmc());
								   }
							   }
						   }
					   }
				   }
			   }
			   rs.close();
			
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
            throw new ApplicationException(ex.getMessage());
		}
		return tmpList;
	}
	
	
	/**
	 * 获得完税证明开具来源--收入退还书
	 * @param da
	 * @param jsjdmCon
	 * @param sphmCon
	 * @param whereCon
	 * @return
	 * @throws BaseException
	 */
	private ArrayList getSrthsList(SfDBAccess da, String sspzlxdm,String query_srthsh, String query_qsrq,String query_jzrq) 
	throws BaseException 
	{
		ArrayList tmpList = new ArrayList();
		
		String srthshCon = "";
		String whereCon = "";
		
		try
		{
			//收入退还书号
			if ((query_srthsh != null) && !query_srthsh.equals(""))
			{
				srthshCon = srthshCon + " and a.srthsh = '" + query_srthsh + "'";
			}
			
			//入库时间
			if ((query_qsrq != null) && !query_qsrq.equals(""))
			{
				whereCon = whereCon + " and a.kprq>=to_date('" + query_qsrq +
				    " 00:00:00','yyyy-mm-dd hh24:mi:ss')";
			}
			if ((query_jzrq != null) && !query_jzrq.equals(""))
			{
				whereCon = whereCon + " and a.kprq<=to_date('" + query_jzrq +
		            " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
			}
			
			StringBuffer querySql = new StringBuffer();
			querySql.append("select a.jsjdm NSRSBH,a.nasrqc NSRMC,a.srthsh YPZH, sum(a.tkje) HJJE,  ");
			querySql.append("(select dm1.swjgzzjgmc from dmdb.gy_dm_swjgzzjg dm1 where a.swjgzzjgdm = dm1.swjgzzjgdm(+)) SSSWJGZZJGMC,a.swjgzzjgdm SSSWJGZZJGDM  ");
			querySql.append("from jkdb.kj_jl_sssrths a  ");
			querySql.append("where a.dzbz='"+CodeConstant.SRTHS_DZBZ_1+"' and a.hxbz='"+CodeConstant.SRTHS_HXBZ_1+"' ");
			querySql.append(srthshCon);
			querySql.append(whereCon);
			querySql.append(" group by a.jsjdm,a.nasrqc,a.srthsh,a.swjgzzjgdm ");
			
			Debug.out("QuerySql=== Srths ==" + querySql);
			
			//查询
			ResultSet rs = da.querySQL(querySql.toString());
			while (rs.next()) 
			{
				Wszm wszm = new Wszm();
	            
	            //获取完税证明打印次数
	            HashMap dycsmap = getWszmDycs(sspzlxdm,rs.getString("YPZH"),"","","");
	            
	            wszm.setPzlydm(sspzlxdm);
	            wszm.setNsrsbh(rs.getString("NSRSBH"));
	            wszm.setNsrmc(rs.getString("NSRMC"));
				wszm.setHjje(rs.getBigDecimal("HJJE"));
				wszm.setYpzh(rs.getString("YPZH"));
				wszm.setSsswjgzzjgdm(rs.getString("SSSWJGZZJGDM"));
				wszm.setSsswjgzzjgmc(rs.getString("SSSWJGZZJGMC"));
				wszm.setYwszh("");
				wszm.setYpzzldm("");
				wszm.setYndzb("");
				wszm.setDycs((String) dycsmap.get("dycs"));
				 
				tmpList.add(wszm);
			}
			rs.close();
			 
			 
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		    throw new ApplicationException(ex.getMessage());
		}
		
		return tmpList;
	}

	
	
	/**
	 * 获得完税证明开具来源--完税证明
	 * @param da
	 * @param jsjdmCon
	 * @param sphmCon
	 * @param whereCon
	 * @return
	 * @throws BaseException
	 */
	private ArrayList getWszmList(SfDBAccess da, String sspzlxdm, String query_wszm, String query_wszmh, String pzlxdm, String query_qsrq,String query_jzrq) 
			throws BaseException 
	{
		ArrayList tmpList = new ArrayList();
		ArrayList wszmmxList = new ArrayList();
		
		String wszmCon = "";
		String whereCon = "";
		String pzlxCon = "";
		
		try
		{
			//税收完税证明号
			if ((query_wszm != null) && !query_wszm.equals(""))
			{
				wszmCon = wszmCon + " and a.wszh = '" + query_wszm + "'";
			}
			
			//税收完税凭证类型
			if ((pzlxdm != null) && !pzlxdm.equals(""))
			{
				pzlxCon = pzlxCon + " and a.pzlxdm = '" + pzlxdm + "'";
			}
			
			//入库时间
			if ((query_qsrq != null) && !query_qsrq.equals(""))
			{
				whereCon = whereCon + " and a.cjrq>=to_date('" + query_qsrq +
				    " 00:00:00','yyyy-mm-dd hh24:mi:ss')";
			}
			if ((query_jzrq != null) && !query_jzrq.equals(""))
			{
				whereCon = whereCon + " and a.cjrq<=to_date('" + query_jzrq +
		            " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
			}
			 
			
			if((query_wszmh != null) && !query_wszmh.equals(""))
			{
				try
				{
					StringBuffer sql = new StringBuffer();
					sql.append("select distinct wszh ,pzzldm, ndzb from sbdb.sb_jl_kjsswszmmx a where a.ypzh ='"+query_wszmh+"' ");
					
					Debug.out("sql=== wszmmx ==" + sql);
					
					//查询
					ResultSet rs = da.querySQL(sql.toString());
					while (rs.next()) 
					{
						Wszm tmpWszm = new Wszm();
						
						tmpWszm.setWszh(rs.getString("wszh"));
						tmpWszm.setPzzldm(rs.getString("pzzldm"));
						tmpWszm.setNdzb(rs.getString("ndzb"));
						
						wszmmxList.add(tmpWszm);
						
					}
					rs.close();
				}
				catch (Exception ex)
				{
					ex.printStackTrace();
		            throw new ApplicationException(ex.getMessage());
				}
				
				if (wszmmxList.size() == 0 || wszmmxList == null) 
				{
					 throw new ApplicationException("无符合条件的查询结果！");
				}
			}

			StringBuffer querySql = new StringBuffer();
			querySql.append("select a.wszh YWSZH, a.ndzb YNDZB, a.pzzldm YPZZLDM, a.hjje HJJE,a.nsrsbh NSRSBH, a.nsrmc NSRMC, ");
			querySql.append("(select dm1.swjgzzjgmc from dmdb.gy_dm_swjgzzjg dm1 where a.kjswjgzzjgdm = dm1.swjgzzjgdm(+)) SSSWJGZZJGMC, a.kjswjgzzjgdm SSSWJGZZJGDM ");
			querySql.append("from sbdb.sb_jl_kjsswszm a ");
			querySql.append("where a.pzlydm ='"+CodeConstant.WSZM_SGWSPZ+"' and a.yxbz='"+CodeConstant.WSZM_YXBZ_0+"' ");
			if((query_wszmh != null) && !query_wszmh.equals(""))
			{
				querySql.append("and (");
				for(int i = 0; i < wszmmxList.size(); i++)
				{
					Wszm setWszm = (Wszm) wszmmxList.get(i);
					String wszh = setWszm.getWszh();
					String pzzldm = setWszm.getPzzldm();
					String ndzb = setWszm.getNdzb();
					
					if(i == 0)
					{
						querySql.append(" (a.wszh = '"+wszh+"' and a.pzzldm = '"+pzzldm+"' and a.ndzb = '"+ndzb+"') ");
					}
					else
					{
						querySql.append("or (a.wszh = '"+wszh+"' and a.pzzldm = '"+pzzldm+"' and a.ndzb = '"+ndzb+"')");
					}
					
					if(i == wszmmxList.size() -1)
					{
						querySql.append(")");
					}
				}
				
			}
			if((query_wszm != null) && !query_wszm.equals(""))
			{
				querySql.append(wszmCon);
			}
			querySql.append(pzlxCon);
			querySql.append(whereCon);
			
			Debug.out("QuerySql=== wszm ==" + querySql);
			
			//查询
			ResultSet rs1 = da.querySQL(querySql.toString());
			while (rs1.next()) 
			{
				Wszm wszm = new Wszm();
				
				wszm.setNsrsbh(rs1.getString("NSRSBH"));
				wszm.setNsrmc(rs1.getString("NSRMC"));
				setWszmValue(sspzlxdm, rs1, wszm);
				 
				tmpList.add(wszm);
			}
			rs1.close();

		}
		catch (Exception ex)
		{
			ex.printStackTrace();
            throw new ApplicationException(ex.getMessage());
		}
		return tmpList;
	}
	
	
	
	/**
	 * 获得完税证明开具明细--缴款书
	 * @param vo
	 * @param sspzlxdm
	 * @param yjkpzh
	 * @param ypzh
	 * @return
	 * @throws BaseException
	 */
	private ArrayList getJksWszmList(String sspzlxdm, String ypzh) 
		throws BaseException 
	{
		
		ArrayList tempList = new ArrayList();
		
		Connection conn = null;
		
        try
        {
        	conn = SfDBResource.getConnection();
			SfDBAccess da = new SfDBAccess(conn);
			
            //设置查询条件
            String jkpzhCon ="";
            String jkpzh2Con ="";
            String jkpzh3Con ="";
            
            //原凭证号
			if ((ypzh != null) && !ypzh.equals(""))
			{
				jkpzhCon = jkpzhCon + " and t.sphm = '" + ypzh + "' and t.jsjdm ='"+ypzh.substring(0, 8)+"' ";
				jkpzh2Con = jkpzh2Con + " and t.sphm = '" + ypzh + "' and t1.jsjdm ='"+ypzh.substring(0, 8)+"' ";
				jkpzh3Con = jkpzh3Con + " and a.sphm = '" + ypzh + "' and a.jsjdm ='"+ypzh.substring(0, 8)+"' ";
			}
            
            StringBuffer querySql = new StringBuffer();
			querySql.append("select a.jsjdm NSRSBH, a.bz BZ,a.sjly SJLY, a.szdm SZDM, (case when substr(a.szsmdm, 1, 2) in ('02', '05') then substr(a.szsmdm, 1, 4) else a.szsmdm end) SZSMDM, sum(a.rkje) RKJE,  ");
			querySql.append("a.swjgzzjgdm SWJGZZJGDM, a.sbrq SBRQ, a.yhmc YHMC, a.zh ZH, ");
			querySql.append("to_char(a.skssksrq,'yyyymmdd') SKSSKSRQ, to_char(a.skssjsrq,'yyyymmdd') SKSSJSRQ, ");
			querySql.append("a.yskmdm YSKMDM, a.jkpzh YPZH, to_char(a.zyrq,'yyyymmdd') RKRQ, '' YWSZH, '' YPZZLDM, '' YNDZB ");
			querySql.append("from (select t.sphm, t.jkpzh, t.jsjdm, t.sklxdm, t.swjgzzjgdm, t.zsswjgzzjgdm,t.szdm, t.yskmdm,t.skssksrq, t.skssjsrq, t.xjrq, t.zyrq, t1.rkje, t.bz, nvl(t.sjly, '11') sjly, t.fsdm, t.sbrq, t.yhmc, t.zh, t1.szsmdm ");
			querySql.append("from sbdb.sb_jl_sbjkzb t,sbdb.sb_jl_Sbjkmx t1 ");
			querySql.append("where substr(t.zwbs, 2, 1)<> 0 and substr(t.zwbs,10,1)= '0' and t.jkpzh = t1.jkpzh ");
			querySql.append(jkpzhCon);
			querySql.append(" union all ");
			querySql.append("select t.sphm, t1.jkpzh, t1.jsjdm,t1.sklxdm, t1.swjgzzjgdm, t1.swjgzzjgdm, t2.szdm, t2.yskmdm, t2.skssksrq, t2.skssjsrq, t2.xjrq, t.zyrq, t2.rkje, t.bz, nvl(t.sjly, '11') sjly, t.fsdm, t.sbrq, t.yhmc, t.zh, t2.szsmdm ");
			querySql.append("from JKDB.KJ_JL_ZWTZJKSZB t1,JKDB.KJ_JL_ZWTZJKSMX t2, sbdb.sb_jl_sbjkzb t ");
			querySql.append("where t1.jkpzh=t2.jkpzh  and t1.zbxh=t2.zbxh and t1.jkpzh = t.jkpzh and t2.rkje>0 ");
			querySql.append(jkpzh2Con);
			querySql.append(" union all ");
			querySql.append("select t.sphm, t.jkpzh, t.jsjdm, t.sklxdm,t.swjgzzjgdm, t.zsswjgzzjgdm, t.szdm, t.yskmdm,t.skssksrq,t.skssjsrq,t.xjrq,a.zyrq,-t.rkje,t.bz,nvl(t.sjly, '11') sjly, t.fsdm, t.sbrq, t.yhmc, t.zh, t1.szsmdm ");
			querySql.append("from JKDB.KJ_JL_GZTZS a, sbdb.sb_jl_sbjkzb t,sbdb.sb_jl_Sbjkmx t1 ");
			querySql.append("where a.ysjksh=t.jkpzh and a.dzbz = '1' and a.gztzslx = '2' and t.jkpzh = t1.jkpzh ");
			querySql.append(jkpzhCon);
			querySql.append(" ) a ");
			querySql.append(" where 1=1 ");
			querySql.append(jkpzh3Con);
			querySql.append(" group by  a.jsjdm, a.bz,a.sjly, a.szdm, (case when substr(a.szsmdm, 1, 2) in ('02', '05') then substr(a.szsmdm, 1, 4) else a.szsmdm end),  ");
			querySql.append(" a.swjgzzjgdm, a.sbrq, a.yhmc, a.zh, to_char(a.skssksrq,'yyyymmdd'), to_char(a.skssjsrq,'yyyymmdd'), a.yskmdm, a.sphm, a.jkpzh, to_char(a.zyrq,'yyyymmdd') ");
			querySql.append(" having sum(a.rkje)<>0 ");
			querySql.append(" order by a.jkpzh");
			
			Debug.out("WszmQuerySql=== JKS ==" + querySql);

			//查询
			ResultSet rs = da.querySQL(querySql.toString());
			
			setWszmmx(rs, tempList);
			rs.close();
			if (tempList.size() <= 0)
            {
                throw new ApplicationException("没有找到符合条件的通用缴款书/付款凭证/经收缴款书信息！");
            }
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
		
		return tempList;
	}

	

	
	
	/**
	 * 获得完税证明开具明细--完税证
	 * @param vo
	 * @param sspzlxdm
	 * @param yjkpzh
	 * @param ypzh
	 * @return
	 * @throws BaseException
	 */
	private ArrayList getWszWszmList(String sspzlxdm, String ywszh, String yndzb, String ypzzldm) 
		throws BaseException 
	{
		
		ArrayList tempList = new ArrayList();
		Connection conn = null;
		
        try
        {
        	conn = SfDBResource.getConnection();
			SfDBAccess da = new SfDBAccess(conn);
			
            //设置查询条件
			String ywszhCon ="";
			String ypzzldmCon ="";
			String yndzbCon ="";
			
			//原完税证明完税证号
			if ((ywszh != null) && !ywszh.equals(""))
			 {
				 if(sspzlxdm.equals(CodeConstant.WSZM_CCWSZ))
				 {
					 ywszhCon = ywszhCon + " and a.wspzh = '" + ywszh + "'";
				 }
				 else
				 {
					 ywszhCon = ywszhCon + " and a.wszh = '" + ywszh + "'";
				 }
			 }
			
			//原票证种类代码
			if ((ypzzldm != null) && !ypzzldm.equals(""))
			{
				ypzzldmCon = ypzzldmCon + " and a.pzzldm = '" + ypzzldm + "'";
			}
			
			//原完税证年度字别
			if ((yndzb != null) && !yndzb.equals(""))
			{
				yndzbCon = yndzbCon + " and a.ndzb = '" + yndzb + "'";
			}
            
			StringBuffer querySql = new StringBuffer();
			if((sspzlxdm != null) && !sspzlxdm.equals(""))
			   {
				   //1.个体工商户
				   if(sspzlxdm.equals(CodeConstant.WSZM_GTWSZ))
				   {
					   querySql.append("select '' YPZH, a.wszh YWSZH, a.ndzb YNDZB, a.pzzldm YPZZLDM, b.sjse RKJE,to_char(a.lrrq,'yyyymmdd') RKRQ, ");
					   querySql.append("b.szsmdm SZSMDM,(select szsmdm from dmdb.sb_dm_szsm dm where SUBSTR(b.szsmdm, 1, 2)=dm.szsmdm) SZDM, a.swjgzzjgdm SWJGZZJGDM, ");
					   querySql.append("to_char(b.skssksrq,'yyyymmdd') SKSSKSRQ, to_char(b.skssjsrq,'yyyymmdd') SKSSJSRQ,b.yskmdm YSKMDM ");
					   querySql.append("from sbdb.sb_jl_gtgshwszz a,sbdb.sb_jl_gtgshwszmx b ");
					   querySql.append("where a.pzzldm = b.pzzldm and a.wszh = b.wszh and a.ndzb = b.ndzb ");
					   querySql.append("and nvl(a.clbjdm,'0000000') <> '"+CodeConstant.WSZ_CLBJDM+"' ");
					   querySql.append(ywszhCon);
					   querySql.append(yndzbCon);
					   querySql.append(ypzzldmCon);
				   }
				   //2.零散征收
				   if(sspzlxdm.equals(CodeConstant.WSZM_LSWSZ))
				   {
					   
					   querySql.append("select '' YPZH, a.wszh YWSZH, a.ndzb YNDZB, a.pzzldm YPZZLDM, b.sjse RKJE,to_char(a.lrrq,'yyyymmdd') RKRQ, ");
					   querySql.append("b.szsmdm SZSMDM,b.szdm SZDM, a.swjgzzjgdm SWJGZZJGDM,b.yskmdm YSKMDM, ");
					   querySql.append("to_char(b.skssksrq,'yyyymmdd') SKSSKSRQ, to_char(b.skssjsrq,'yyyymmdd') SKSSJSRQ ");
					   querySql.append("from sbdb.sb_jl_lsswszz a,sbdb.sb_jl_lsswszmx b ");
					   querySql.append("where a.wszxh = b.wszxh and a.wszh = b.wszh and a.ndzb = b.ndzb ");
					   querySql.append("and nvl(a.clbjdm,'0000000') <> '"+CodeConstant.WSZ_CLBJDM+"' ");
					   querySql.append(ywszhCon);
					   querySql.append(yndzbCon);
					   querySql.append(ypzzldmCon);
				   }
				   //3.契税征收
				   if(sspzlxdm.equals(CodeConstant.WSZM_QSWSZ))
				   {
					   querySql.append("select '' YPZH, a.wszh YWSZH, a.ndzb YNDZB, a.pzzldm YPZZLDM, b.sjse RKJE,to_char(a.lrrq,'yyyymmdd') RKRQ, ");
					   querySql.append("b.szsmdm SZSMDM,b.szdm SZDM, a.swjgzzjgdm SWJGZZJGDM,b.yskmdm YSKMDM, ");
					   querySql.append("to_char(b.skssksrq,'yyyymmdd') SKSSKSRQ, to_char(b.skssjsrq,'yyyymmdd') SKSSJSRQ ");
					   querySql.append("from sbdb.sb_jl_qswszz a,sbdb.sb_jl_qswszmx b ");
					   querySql.append("where a.pzzldm = b.pzzldm and a.wszh = b.wszh and a.ndzb = b.ndzb ");
					   querySql.append("and nvl(a.clbjdm,'0000000') <> '"+CodeConstant.WSZ_CLBJDM+"' ");
					   querySql.append(ywszhCon);
					   querySql.append(yndzbCon);
					   querySql.append(ypzzldmCon);
				   }
				   //4.车船征收
				   if(sspzlxdm.equals(CodeConstant.WSZM_CCWSZ))
				   {
					   querySql.append("select '' YPZH, a.wspzh YWSZH, a.ndzb YNDZB, a.pzzldm YPZZLDM, b.sjse RKJE,to_char(a.lrrq,'yyyymmdd') RKRQ, ");
					   querySql.append("b.szsmdm SZSMDM,a.szdm SZDM, a.swjgdm SWJGZZJGDM,b.yskmdm YSKMDM, ");
					   querySql.append("to_char(b.skssksrq,'yyyymmdd') SKSSKSRQ, to_char(b.skssjsrq,'yyyymmdd') SKSSJSRQ ");
					   querySql.append("from sbdb.sb_jl_ccwszz a,sbdb.sb_jl_ccwszmx b ");
					   querySql.append("where a.wspzlxdm = b.wspzlxdm and a.wspzh = b.wspzh and a.ndzb = b.ndzb ");
					   querySql.append("and nvl(a.clbh,'0000000') <>'7777' and nvl(a.hphm,'0000000') <>'15571279' ");
					   querySql.append(ywszhCon);
					   querySql.append(yndzbCon);
					   querySql.append(ypzzldmCon);
				   }
			   }
			   
			Debug.out("WszmQuerySql=== WSZ ==" + querySql);

			//查询
			ResultSet rs = da.querySQL(querySql.toString());
			
			setWszmmx(rs, tempList);
			rs.close();
			if (tempList.size() <= 0)
            {
                throw new ApplicationException("没有找到符合条件的完税证/收现缴款书信息！");
            }
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
		
		return tempList;
	}
	
	
	
	/**
	 * 获得完税证明开具明细--保单
	 * @param vo
	 * @param sspzlxdm
	 * @param yjkpzh
	 * @param ypzh
	 * @return
	 * @throws BaseException
	 */
	private ArrayList getBdWszmList(String sspzlxdm, String ywszh, String yndzb, String ypzzldm) 
		throws BaseException 
	{
		
		ArrayList tempList = new ArrayList();
		Connection conn = null;
		
        try
        {
        	conn = SfDBResource.getConnection();
			SfDBAccess da = new SfDBAccess(conn);
			
            //设置查询条件
			String ywszhCon ="";
			String ypzzldmCon ="";
			String yndzbCon ="";
			
			//原完税证明完税证号
			if ((ywszh != null) && !ywszh.equals(""))
			 {
				ywszhCon = ywszhCon + " and a.wspzh = '" + ywszh + "'";
			 }
			
			//原票证种类代码
			if ((ypzzldm != null) && !ypzzldm.equals(""))
			{
				ypzzldmCon = ypzzldmCon + " and a.pzzldm = '" + ypzzldm + "'";
			}
			
			//原完税证年度字别
			if ((yndzb != null) && !yndzb.equals(""))
			{
				yndzbCon = yndzbCon + " and a.ndzb = '" + yndzb + "'";
			}
            
			StringBuffer querySql = new StringBuffer();
			if((sspzlxdm != null) && !sspzlxdm.equals(""))
			   {
				   //保险代征
				   if(sspzlxdm.equals(CodeConstant.WSZM_BD))
				   {
					   querySql.append("select '' YPZH, a.wspzh YWSZH, a.ndzb YNDZB, a.pzzldm YPZZLDM, b.sjse RKJE,to_char(a.lrrq,'yyyymmdd') RKRQ, ");
					   querySql.append("b.szsmdm SZSMDM,a.szdm SZDM, a.swjgdm SWJGZZJGDM,b.yskmdm YSKMDM, ");
					   querySql.append("to_char(b.skssksrq,'yyyymmdd') SKSSKSRQ, to_char(b.skssjsrq,'yyyymmdd') SKSSJSRQ ");
					   querySql.append("from sbdb.sb_jl_ccwszz a,sbdb.sb_jl_ccwszmx b ");
					   querySql.append("where a.wspzlxdm = b.wspzlxdm and a.wspzh = b.wspzh and a.ndzb = b.ndzb ");
					   querySql.append("and nvl(a.clbh,'0000000') <>'7777' and nvl(a.hphm,'0000000') <>'15571279' ");
					   querySql.append(ywszhCon);
					   querySql.append(yndzbCon);
					   querySql.append(ypzzldmCon);
				   }
			   }
			   
			Debug.out("WszmQuerySql=== BD ==" + querySql);

			//查询
			ResultSet rs = da.querySQL(querySql.toString());
			
			setWszmmx(rs, tempList);
			rs.close();
			if (tempList.size() <= 0)
            {
                throw new ApplicationException("没有找到符合条件的保单信息！");
            }
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
		
		return tempList;
	}
	
	
	/**
	 * 获得完税证明开具明细--收入退还书
	 * @param vo
	 * @param sspzlxdm
	 * @param yjkpzh
	 * @param ypzh
	 * @return
	 * @throws BaseException
	 */
	private ArrayList getSrthsWszmList(String sspzlxdm, String ypzh) 
		throws BaseException 
	{
		
		ArrayList tempList = new ArrayList();
		
		Connection conn = null;
		
        try
        {
        	conn = SfDBResource.getConnection();
			SfDBAccess da = new SfDBAccess(conn);
			
            //设置查询条件
            String ypzhCon ="";
            
            //原凭证号
			if ((ypzh != null) && !ypzh.equals(""))
			{
				ypzhCon = ypzhCon + " and a.srthsh = '" + ypzh + "' ";
			}
            
            StringBuffer querySql = new StringBuffer();
            querySql.append("select a.srthsh YPZH, a.yhtpbz YHTPBZ, '' YWSZH, '' YNDZB, '' YPZZLDM, sum(a.tkje) RKJE,to_char(a.zyrq,'yyyymmdd') RKRQ,'' SKSSKSRQ,'' SKSSJSRQ, ");
            querySql.append("a.szsmdm SZSMDM,(select szsmdm from dmdb.sb_dm_szsm dm where SUBSTR(a.szsmdm, 1, 2)=dm.szsmdm) SZDM, a.swjgzzjgdm SWJGZZJGDM ");
            querySql.append("from jkdb.kj_jl_sssrths a ");
            querySql.append("where a.dzbz='"+CodeConstant.SRTHS_DZBZ_1+"' and a.hxbz='"+CodeConstant.SRTHS_HXBZ_1+"' ");
            querySql.append(ypzhCon);
            querySql.append("group by a.jsjdm,a.nasrqc,a.srthsh,a.yhtpbz,a.swjgzzjgdm,a.szsmdm,to_char(a.zyrq,'yyyymmdd') ");
			
			Debug.out("WszmQuerySql=== SRTHS ==" + querySql);

			//查询
			ResultSet rs = da.querySQL(querySql.toString());
			
			setWszmmx(rs, tempList);
			rs.close();
			if (tempList.size() <= 0)
            {
                throw new ApplicationException("没有找到符合条件的收入退还书信息！");
            }
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
		
		return tempList;
	}
	
	
	
	/**
	 * 获得完税证明开具明细--手工完税凭证
	 * @param vo
	 * @param sspzlxdm
	 * @param yjkpzh
	 * @param ypzh
	 * @return
	 * @throws BaseException
	 */
	private ArrayList getWszmWszmList(String sspzlxdm, String ywszh, String yndzb, String ypzzldm) 
		throws BaseException 
	{
		
		ArrayList tempList = new ArrayList();
		Connection conn = null;
		
        try
        {
        	conn = SfDBResource.getConnection();
			SfDBAccess da = new SfDBAccess(conn);
			
            //设置查询条件
			String ywszhCon ="";
			String ypzzldmCon ="";
			String yndzbCon ="";
			
			//原完税证明完税证号
			if ((ywszh != null) && !ywszh.equals(""))
			 {
				ywszhCon = ywszhCon + " and a.wszh = '" + ywszh + "'";
			 }
			
			//原票证种类代码
			if ((ypzzldm != null) && !ypzzldm.equals(""))
			{
				ypzzldmCon = ypzzldmCon + " and a.pzzldm = '" + ypzzldm + "'";
			}
			
			//原完税证年度字别
			if ((yndzb != null) && !yndzb.equals(""))
			{
				yndzbCon = yndzbCon + " and a.ndzb = '" + yndzb + "'";
			}
            
			StringBuffer querySql = new StringBuffer();
			if((sspzlxdm != null) && !sspzlxdm.equals(""))
			   {
				   //保险代征
				   if(sspzlxdm.equals(CodeConstant.WSZM_SGWSPZ))
				   {
					   querySql.append("select '' YPZH, a.wszh YWSZH, a.ndzb YNDZB, a.pzzldm YPZZLDM, b.sjtje RKJE,to_char(a.lrrq,'yyyymmdd') RKRQ, ");
					   querySql.append("b.szsmdm SZSMDM,b.szdm SZDM, a.kjswjgzzjgdm SWJGZZJGDM, ");
					   querySql.append("to_char(b.skssksrq,'yyyymmdd') SKSSKSRQ, to_char(b.skssjsrq,'yyyymmdd') SKSSJSRQ ");
					   querySql.append("from sbdb.sb_jl_kjsswszm a,sbdb.sb_jl_kjsswszmmx b ");
					   querySql.append("where a.pzlydm ='"+CodeConstant.WSZM_SGWSPZ+"' and a.yxbz='"+CodeConstant.WSZM_YXBZ_0+"' ");
					   querySql.append("and a.pzzldm = b.pzzldm and a.wszh = b.wszh and a.ndzb = b.ndzb ");
					   querySql.append(ywszhCon);
					   querySql.append(yndzbCon);
					   querySql.append(ypzzldmCon);
				   }
			   }
			   
			Debug.out("WszmQuerySql=== WSZM ==" + querySql);

			//查询
			ResultSet rs = da.querySQL(querySql.toString());
			
			setWszmmx(rs, tempList);
			rs.close();
			if (tempList.size() <= 0)
            {
                throw new ApplicationException("没有找到符合条件的税收完税证明信息！");
            }
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
		
		return tempList;
	}
	

	
	/**
	 * 设置完税证明查询列表值
	 * @param sspzlxdm
	 * @param rs
	 * @param wszm
	 * @throws BaseException
	 * @throws SQLException
	 */
	private void setWszmValue(String sspzlxdm, ResultSet rs, Wszm wszm)
			throws BaseException, SQLException 
	{
		//获取完税证明打印次数
		   HashMap dycsmap = getWszmDycs(sspzlxdm,"",rs.getString("YPZZLDM"),rs.getString("YWSZH"),rs.getString("YNDZB"));
		   
		   wszm.setPzlydm(sspzlxdm);
		   wszm.setSsswjgzzjgdm(rs.getString("SSSWJGZZJGDM"));
		   wszm.setSsswjgzzjgmc(rs.getString("SSSWJGZZJGMC"));
		   wszm.setHjje(rs.getBigDecimal("HJJE"));
		   wszm.setYpzh("");
		   wszm.setYwszh(rs.getString("YWSZH"));
		   wszm.setYpzzldm(rs.getString("YPZZLDM"));
		   wszm.setYndzb(rs.getString("YNDZB"));
		   wszm.setDycs((String) dycsmap.get("dycs"));
	}
	
	
	/**
	 * 获得税收完税证明开具的信息
	 * @param rs
	 * @param tmpList
	 * @throws SQLException
	 * @throws BaseException
	 */
	private void setWszmmx(ResultSet rs, ArrayList tmpList) 
			throws SQLException, BaseException
	{
		
		while (rs.next()) 
		{
			Wszm wszm = new Wszm();
			
			wszm.setYpzh(rs.getString("YPZH"));
			wszm.setYpzzldm(rs.getString("YPZZLDM"));
			wszm.setYwszh(rs.getString("YWSZH"));
			wszm.setYndzb(rs.getString("YNDZB"));
			wszm.setSzdm(rs.getString("SZDM"));
			wszm.setSzsmdm(rs.getString("SZSMDM"));
			wszm.setSkssksrq(rs.getString("SKSSKSRQ"));
			wszm.setSkssjsrq(rs.getString("SKSSJSRQ"));
			wszm.setYpzrkrq(rs.getString("RKRQ"));
			wszm.setHjje(rs.getBigDecimal("RKJE"));
			wszm.setKjswjgzzjgdm(rs.getString("SWJGZZJGDM"));
			 
			tmpList.add(wszm);
		}
	}

	
	/**
     * 查询完税证明打印次数
     * @param jsjdm String
     * @param conn Connection
     * @return String
     * @throws BaseException
     */
    private HashMap getWszmDycs(String sspzlxdm, String ypzh, String ypzzldm, String ywszh, String yndzb) throws
        BaseException {
    	
    	StringBuffer querySql = new StringBuffer();
    	if(sspzlxdm.equals(CodeConstant.WSZM_LSWSZ) || sspzlxdm.equals(CodeConstant.WSZM_GTWSZ) 
    			|| sspzlxdm.equals(CodeConstant.WSZM_QSWSZ) || sspzlxdm.equals(CodeConstant.WSZM_CCWSZ) 
    			|| sspzlxdm.equals(CodeConstant.WSZM_SGWSPZ))
    	{
    		querySql.append("SELECT max(to_number(dycs)) dycs FROM SBDB.SB_JL_KJSSWSZM WHERE pzlydm = ? and ypzzldm=? and ywszh=? and yndzb=? and yxbz='"+CodeConstant.WSZM_YXBZ_0+"' ");
    	}
    	else
    	{
    		querySql.append("SELECT max(to_number(dycs)) dycs FROM SBDB.SB_JL_KJSSWSZM WHERE pzlydm = ? and ypzh=? and yxbz='"+CodeConstant.WSZM_YXBZ_0+"' ");
    	}
    	
        String dycs = "";
        HashMap rstmap = new HashMap();
        Connection conn = null;
        try {
        	conn = SfDBResource.getConnection();
            //获取PreparedStatement
            PreparedStatement pst = conn.prepareStatement(querySql.toString());
            
            //设置查询条件
            if(sspzlxdm.equals(CodeConstant.WSZM_LSWSZ) || sspzlxdm.equals(CodeConstant.WSZM_GTWSZ) 
        			|| sspzlxdm.equals(CodeConstant.WSZM_QSWSZ) || sspzlxdm.equals(CodeConstant.WSZM_CCWSZ)
        			|| sspzlxdm.equals(CodeConstant.WSZM_SGWSPZ))
        	{
            	pst.setString(1, sspzlxdm);
            	pst.setString(2, ypzzldm);
                pst.setString(3, ywszh);
                pst.setString(4, yndzb);
        	}
        	else
        	{
        		pst.setString(1, sspzlxdm);
        		pst.setString(2, ypzh);
        	}
            //设置查询条件计算机代码
            ResultSet rs = pst.executeQuery();
            //循环取出查询结果
            while (rs.next()) 
            {
                //获取纳税人名称
            	dycs = rs.getString("dycs");
            }
            
            if(dycs == null || dycs.equals(""))
        	{
            	dycs ="0";
        	}
            rstmap.put("dycs", dycs);
            //关闭rs
            rs.close();
            //关闭pst
            pst.close();
        } catch (SQLException sqlep) {
            sqlep.printStackTrace();
            //抛出应用异常
            throw new ApplicationException("获取打印次数错误！");
        }finally
		{
			SfDBResource.freeConnection(conn);
		}
        return rstmap;
    }
    
    
    /**
     * 查询非税务所开具完税证入库标志
     * @param jsjdm String
     * @param conn Connection
     * @return String
     * @throws BaseException
     */
    private HashMap getCcwszRkbz(String wspzh, String ndzb) 
    throws BaseException 
    {
    	
    	StringBuffer querySql = new StringBuffer();
    	querySql.append("select distinct(SUBSTR(f.zwbs, 2, 1)) ZWBS ");
    	querySql.append("from sbdb.sb_jl_ccwszz a, sbdb.sb_jl_ccwszhz e, sbdb.sb_jl_sbjkzb f ");
    	querySql.append("where a.sbhzdh = e.sbhzdh and e.jsjdm = f.jsjdm and e.jkpzh = f.jkpzh ");
    	querySql.append("and f.yskmdm <> '"+CodeConstant.SZSM_FOR_ZNJFK+"' and a.wspzlxdm not in ('98', '97', '99') ");
    	querySql.append("and a.wspzh = ? and a.ndzb = ? ");
    
        
        String zwbs = "";
        HashMap rstmap = new HashMap();
        ArrayList zwbsList = new ArrayList();
        try {
            //获取PreparedStatement
            PreparedStatement pst = SfDBResource.getConnection().prepareStatement(querySql.toString());
            
            //设置查询条件
            pst.setString(1, wspzh);
            pst.setString(2, ndzb);
   
            //设置查询条件计算机代码
            ResultSet rs = pst.executeQuery();
            //循环取出查询结果
            while (rs.next()) 
            {
                //获取账务标识
            	zwbs = rs.getString("zwbs");
            	
            	zwbsList.add(zwbs);
            }
            
            //System.out.println("zwbsList:"+zwbsList.size()+" zwbs:"+zwbs);
            
            //如果zwbs列表存在多列，则说明其中有未入库的数据，那么将改完税证视为未入库状态：0
            if(zwbsList.size() > 1)
            {
            	rstmap.put("zwbs", "0");
            }
            //如果zwbs不存在，则说明其中未汇总，或数据丢失，那么将改完税证视为未入库状态：0
            else if(zwbs == null || zwbs.equals(""))
            {
            	rstmap.put("zwbs", "0");
            }
            else
            {
            	rstmap.put("zwbs", zwbs);
            }
            //关闭rs
            rs.close();
            //关闭pst
            pst.close();
        } catch (SQLException sqlep) {
            sqlep.printStackTrace();
            //抛出应用异常
            throw new ApplicationException("获取非税务所开具完税证入库标志错误！");
        }
        return rstmap;
    }
	
	
    /**
     * 通过计算机代码、数据来源、备注查询纳税人名称
     * @param jsjdm 计算机代码
     * @param sjly 数据来源备注(零散完税证) 
     * @param bz 备注(零散完税证)
     * @return nsrmc
     * @throws BaseException
     * @throws SQLException
     */
	private String getNsrmc(String jsjdm, String sjly, String bz) 
		throws BaseException,
	SQLException 
	{
		String nsrmc ="";
		//System.out.println(">>>>>>》》》》》》jsjdm:"+jsjdm);
		//System.out.println(">>>>>>》》》》》》sjly:"+sjly);
		//System.out.println(">>>>>>》》》》》》bz:"+bz);
		//获取jsjdm最后一位
		char ch = jsjdm.charAt(jsjdm.length()-1);
		//System.out.println(">>>>>>》》》》》》ch:"+ch);
		//判断是不是自然人
		if((ch>='a' && ch<='z') || (ch>='A' && ch<='Z'))
		{
			//调用登记接口获取自然人登记信息
			Map tmp = getZRRInfo(jsjdm);
			//获取纳税人名称
			nsrmc = (String) tmp.get("nsrmc");
			
			//System.out.println("自然人名称："+nsrmc);
			
		/*如果数据来源是17开头 零散纳税人*/
		}
		else if(sjly != null && !sjly.equals("") && sjly.substring(0, 2).equals("17"))
		{
			//获取纳税人名称
			nsrmc = bz.substring(0, bz.indexOf("#$#")).trim();
			
			//System.out.println("零散纳税人名称："+nsrmc);
			
		/*非零散纳税人,非自然人*/
		}
		else
		{
			HashMap djmap = getNsrmc(jsjdm);
			
			//获取纳税人名称
			nsrmc = (String) djmap.get("nsrmc");
			
			//System.out.println("纳税人名称："+nsrmc);
		}
		
		return nsrmc;
	}
	
    
	/**
	 * 自然人登记信息
	 * @param jsjdm
	 * @return
	 * @throws BaseException
	 */
    private HashMap getZRRInfo(String jsjdm) throws
    BaseException {
    	String strSql ="SELECT NSRMC,SWJGZZJGDM FROM DJDB.DJ_JL_ZRRJBSJ WHERE JSJDM=?";
        String nsrmc = "";
        String swjgdm = "";
        HashMap rstmap = new HashMap();
        try {
            //获取PreparedStatement
            PreparedStatement pst = SfDBResource.getConnection().prepareStatement(strSql);
            //设置查询条件Sphm
            pst.setString(1, jsjdm);
            //设置查询条件计算机代码
            ResultSet rs = pst.executeQuery();
            //循环取出查询结果
            if (rs.next()) 
            {
                //获取纳税人名称
                nsrmc = rs.getString("NSRMC");
                swjgdm = rs.getString("SWJGZZJGDM");
            }
            rstmap.put("nsrmc", nsrmc);
            rstmap.put("swjgdm", swjgdm);
            //关闭rs
            rs.close();
            //关闭pst
            pst.close();
        } catch (SQLException sqlep) {
            sqlep.printStackTrace();
            //抛出应用异常
            throw new ApplicationException("获取自然人名称错误！");
        }
        return rstmap;
     }
	
	/**
     * 查询纳税人名称信息
     * @param jsjdm String
     * @param conn Connection
     * @return String
     * @throws BaseException
     */
    private HashMap getNsrmc(String jsjdm) throws
        BaseException {
        String strSql =
            "SELECT NSRMC,SWDJZH,SWJGZZJGDM FROM DJDB.DJ_JL_JBSJ WHERE JSJDM=?";
        String nsrmc = "";
        String swdjzh = "";
        String swjgdm = "";
        HashMap rstmap = new HashMap();
        try {
            //获取PreparedStatement
            PreparedStatement pst = SfDBResource.getConnection().prepareStatement(strSql);
            //设置查询条件Sphm
            pst.setString(1, jsjdm);
            //设置查询条件计算机代码
            ResultSet rs = pst.executeQuery();
            //循环取出查询结果
            if (rs.next()) 
            {
                //获取纳税人名称
                nsrmc = rs.getString("NSRMC");
                swdjzh = rs.getString("SWDJZH");
                swjgdm = rs.getString("SWJGZZJGDM");
            }
            rstmap.put("nsrmc", nsrmc);
            rstmap.put("swdjzh", swdjzh);
            rstmap.put("swjgdm", swjgdm);
            //关闭rs
            rs.close();
            //关闭pst
            pst.close();
        } catch (SQLException sqlep) {
            sqlep.printStackTrace();
            //抛出应用异常
            throw new ApplicationException("获取纳税人名称错误！");
        }
        return rstmap;
    }
    
    
	/**
     * 查询证件名称型信息
     * @param jsjdm String
     * @param conn Connection
     * @return String
     * @throws BaseException
     */
    private HashMap getZjlxmc(String zjlxdm) throws
        BaseException {
        String strSql =
            "SELECT zjlxdm,zjlxmc FROM DMDB.GY_DM_ZJLX WHERE zjlxdm=?";
        String zjlxmc = "";
        
        HashMap rstmap = new HashMap();
        try {
            //获取PreparedStatement
            PreparedStatement pst = SfDBResource.getConnection().prepareStatement(strSql);
            //设置查询条件
            pst.setString(1, zjlxdm);
            
            ResultSet rs = pst.executeQuery();
            //循环取出查询结果
            if (rs.next()) 
            {
                //获取名称
            	zjlxmc = rs.getString("zjlxmc");
            }
            rstmap.put("zjlxmc", zjlxmc);
            
            //关闭rs
            rs.close();
            //关闭pst
            pst.close();
        } catch (SQLException sqlep) {
            sqlep.printStackTrace();
            //抛出应用异常
            throw new ApplicationException("获取证件类型名称错误！");
        }
        return rstmap;
    }
    
    
    /**
     * 查询号牌种类名称
     * @param jsjdm String
     * @param conn Connection
     * @return String
     * @throws BaseException
     */
    private HashMap getHpzlmc(String hpzldm) throws
        BaseException {
        String strSql =
            "select hpzldm,hpzlmc from dmdb.cc_dm_hpzl where zxbs='0' and hpzldm=?";
        String hpzlmc = "";
        
        HashMap rstmap = new HashMap();
        try {
            //获取PreparedStatement
            PreparedStatement pst = SfDBResource.getConnection().prepareStatement(strSql);
            //设置查询条件
            pst.setString(1, hpzldm);
            
            ResultSet rs = pst.executeQuery();
            //循环取出查询结果
            if (rs.next()) 
            {
                //获取名称
            	hpzlmc = rs.getString("hpzlmc");
            }
            rstmap.put("hpzlmc", hpzlmc);
            
            //关闭rs
            rs.close();
            //关闭pst
            pst.close();
        } catch (SQLException sqlep) {
            sqlep.printStackTrace();
            //抛出应用异常
            throw new ApplicationException("获取号牌种类名称错误！");
        }
        return rstmap;
    }
    

	/**
	   * 获取页数
	   * @param rsCount 查询结果集build
	   * @return 页数
	   */
	  private String getPageTotalCount(int rsCount) 
	  {
		  //1.句柄申明
		  String countTotal = "0";
		  //2.开始业务
		  int pageCount = 0;
		  if ( (rsCount % CodeConstant.SD_PG_LENGTH) == 0) 
		  {
			  pageCount = (rsCount / CodeConstant.SD_PG_LENGTH);
		  }
		  else
		  {
			  pageCount = (rsCount / CodeConstant.SD_PG_LENGTH) + 1;
		  }
		  countTotal = String.valueOf(pageCount);
		  //99.返回值
		  return countTotal;
	  }

	  
	  /**
	   * 获取当前分页数据集
	   * @param tmpList 完整数据集
	   * @param pf 页面对象
	   * @return 当前分页数据集
	   */
	  private List getPageDataList(List tmpList, WszmForm pf) 
	  {
		  //1.申明句柄
		  List dataList = new ArrayList();
		  //2.初始化参数值
		  int startIndex = this.getPageStartIndex(pf.getNextPage(), pf.getTotalpage());
		  int endIndex = this.getPageEndIndex(pf.getNextPage(), pf.getTotalpage());
		  //3.开始业务
		  for (int i = startIndex; i < endIndex; i++) 
		  {
			  if (i < tmpList.size()) 
			  {
				  dataList.add(tmpList.get(i));
		      }
		  }
		  tmpList = null;
		  //99.返回值
		  return dataList;
	  }

	  
	  /**
	   * 获取当前页结束index
	   * @param nextPage 下一页
	   * @param countTotal 总页数
	   * @return 结束index
	   */
	  private int getPageEndIndex(String nextPage, String countTotal) 
	  {
		  //1.句柄申明
		  int iNextPage = Integer.parseInt(nextPage);
		  int iCountTotal = Integer.parseInt(countTotal);
		  int end = -1;
		  //2.开始业务
		  end = iNextPage * CodeConstant.SD_PG_LENGTH;
		  //99.返回值
		  return end;
	  }

	  
	  /**
	   * 获取当前页开始index
	   * @param nextPage 下一页
	   * @param countTotal 总页数
	   * @return 开始index
	   */
	  private int getPageStartIndex(String nextPage, String countTotal) 
	  {
		  //1.句柄申明
		  int iNextPage = Integer.parseInt(nextPage);
		  int iCountTotal = Integer.parseInt(countTotal);
		  int start = -1;
		  //2.开始业务
		  start = (iNextPage - 1) * CodeConstant.SD_PG_LENGTH;
		  //99.返回值
		  return start;
	  }
	  
	  
	  /**
	     * 将字符串转换为浮点数，如果值为空则返回默认值。
	     * @param value 待转换的字符串
	     * @param defaultValue 默认值
	     * @return 浮点数
	     * @roseuid 3F3A00B7038E
	     */
	    public static double getDouble (String value, double defaultValue)
	    {
	        if (isEmpty(value) || !isFloat(value))
	        {
	            return defaultValue;
	        }
	        return Double.parseDouble(value);
	    }
	    
	    /**
	     * 判断是否为空
	     * @param str 输入的字符串
	     * @return boolean
	     * @roseuid 3F39FE470246
	     */
	    public static boolean isEmpty (String str)
	    {
	        if (str == null || str.equals("") || str.equals(""))
	        {
	            return true;
	        }
	        return false;
	    }
	    
	    
	    /**
	     * 是否为浮点数。
	     * @param str - 待检查字符串
	     * @return boolean
	     * @roseuid 3F39FE470250
	     */
	    public static boolean isFloat (String str)
	    {
	        try
	        {
	            float tmp = Float.parseFloat(str);
	            return true;
	        }
	        catch (NumberFormatException e)
	        {
	            return false;
	        } //end try - catch
	    }
	    
	    /**
		 * 保存信息到手工主表
		 * @param conn
		 * @param data
		 */
		private boolean save2zbJks(Connection conn,HashMap wszmap,UserData ud,WszmForm pf,HashMap dycsmap,String hjje,String sjm)throws
	    BaseException{
			boolean savSucc = false;
			
			try
			{
				String zbSql = "INSERT INTO SBDB.SB_JL_KJSSWSZM(pzlxdm,pzlydm,sjm,pzzldm,wszh,ndzb,nsrsbh,nsrmc,swjgzzjgdm,kjswjgzzjgdm,tfrq,hjje,cjr,cjrq,lrr,lrrq,qxdm,ypzh,ypzzldm,ywszh,yndzb,ywhm,dybz,dycs,yxbz,kjlydm,bz,zhdm)" +
			       "VALUES(null,?,?,?,?,?,?,?,?,?,sysdate,?,?,sysdate,?,sysdate,?,?,?,?,?,null,?,?,?,?,null,?)";
				
				PreparedStatement ps = conn.prepareStatement(zbSql);
				
				int index =1;
				ps.setString(index++, pf.getSspzlxdm());
				ps.setString(index++, sjm);
				ps.setString(index++, CodeConstant.SMSB_SWWSZM_PZZLDM);//票证种类代码
				ps.setString(index++, (String)wszmap.get("wszh"));//完税证明完税证号
				ps.setString(index++, (String)wszmap.get("ndzb"));//年度字别
				ps.setString(index++, pf.getNsrsbh());
				ps.setString(index++, pf.getNsrmc());
				ps.setString(index++, pf.getSwjgzzjgdm());//税务机关组织机构代码
				ps.setString(index++, ud.getSsdwdm());//开具税务机关组织机构代码
				ps.setBigDecimal(index++, string2BigDecimal(hjje));//合计金额
				ps.setString(index++, ud.getYhid());//创建人
				ps.setString(index++, ud.getYhmc());//录入人
				ps.setString(index++, ud.getSsdwdm().substring(0, 2));//区县代码
				ps.setString(index++, pf.getYpzh());//原凭证号码
				ps.setString(index++, pf.getYpzzldm());//原完税证票证种类
				ps.setString(index++, pf.getYwszh());//原完税证号
				ps.setString(index++, pf.getYndzb());//原年度字别
				ps.setString(index++, CodeConstant.SMSB_WSZ_UNPRINT);//打印标志
				ps.setInt(index++, Integer.parseInt((String) dycsmap.get("dycs")));//打印次数
				ps.setString(index++, CodeConstant.WSZM_YXBZ_0);//有效标志
				ps.setString(index++, "0");//开具来源代码
				ps.setString(index++, ud.getXtsbm1()); //票证账户代码
				
				ps.execute();
				savSucc = true;
			}
			catch(Exception e)
			{
				e.printStackTrace();
				throw new ApplicationException("保存税收完税证明信息到主表失败！");
			}
			
			return savSucc;
		}
		
	    
	    
	    
	    /**
		 * 保存信息到手工主表
		 * @param conn
		 * @param data
		 */
		private boolean save2zb(Connection conn,HashMap wszmap,UserData ud,WszmForm pf,HashMap dycsmap)throws
	    BaseException{
			boolean savSucc = false;
			
			try
			{
				String zbSql = "INSERT INTO SBDB.SB_JL_KJSSWSZM(pzlxdm,pzlydm,sjm,pzzldm,wszh,ndzb,nsrsbh,nsrmc,swjgzzjgdm,kjswjgzzjgdm,tfrq,hjje,cjr,cjrq,lrr,lrrq,qxdm,ypzh,ypzzldm,ywszh,yndzb,ywhm,dybz,dycs,yxbz,kjlydm,bz,zhdm)" +
			       "VALUES(null,?,?,?,?,?,?,?,?,?,sysdate,?,?,sysdate,?,sysdate,?,?,?,?,?,null,?,?,?,?,null,?)";
				
				PreparedStatement ps = conn.prepareStatement(zbSql);
				
				int index =1;
				ps.setString(index++, pf.getSspzlxdm());
				ps.setString(index++, pf.getWszmsjm());
				ps.setString(index++, CodeConstant.SMSB_SWWSZM_PZZLDM);//票证种类代码
				ps.setString(index++, (String)wszmap.get("wszh"));//完税证明完税证号
				ps.setString(index++, (String)wszmap.get("ndzb"));//年度字别
				ps.setString(index++, pf.getNsrsbh());
				ps.setString(index++, pf.getNsrmc());
				ps.setString(index++, pf.getSwjgzzjgdm());//税务机关组织机构代码
				ps.setString(index++, ud.getSsdwdm());//开具税务机关组织机构代码
				ps.setBigDecimal(index++, string2BigDecimal(pf.getHjje()));//合计金额
				ps.setString(index++, ud.getYhid());//创建人
				ps.setString(index++, ud.getYhmc());//录入人
				ps.setString(index++, ud.getSsdwdm().substring(0, 2));//区县代码
				ps.setString(index++, pf.getYpzh());//原凭证号码
				ps.setString(index++, pf.getYpzzldm());//原完税证票证种类
				ps.setString(index++, pf.getYwszh());//原完税证号
				ps.setString(index++, pf.getYndzb());//原年度字别
				ps.setString(index++, CodeConstant.SMSB_WSZ_UNPRINT);//打印标志
				ps.setInt(index++, Integer.parseInt((String) dycsmap.get("dycs")));//打印次数
				ps.setString(index++, CodeConstant.WSZM_YXBZ_0);//有效标志
				ps.setString(index++, "0");//开具来源代码
				ps.setString(index++, ud.getXtsbm1()); //票证账户代码
				
				ps.execute();
				savSucc = true;
			}
			catch(Exception e)
			{
				e.printStackTrace();
				throw new ApplicationException("保存税收完税证明信息到主表失败！");
			}
			
			return savSucc;
		}
		/**
		 * 保存新完税证号到主表
		 * @param conn
		 * @param data
		 */
		private boolean saveNewWszhJks(Connection conn,UserData ud, String wszh, String ndzb, String pzzldm, String newWszh,String newNdzb,String sjm)
			throws BaseException
	    {
			boolean savSucc = false;
			
			try
			{
				StringBuffer cpzbSQL = new StringBuffer();
				cpzbSQL.append("insert into SBDB.SB_JL_KJSSWSZM (PZLYDM,SJM,PZZLDM,WSZH,NDZB,NSRSBH,NSRMC,SWJGZZJGDM,KJSWJGZZJGDM,TFRQ,HJJE,CJR,CJRQ,LRR,LRRQ,QXDM,YPZH,YPZZLDM,YWSZH,YNDZB,YWHM,DYBZ,DYCS,YXBZ,KJLYDM,BZ,PZLXDM,ZHDM) ");
				cpzbSQL.append("select PZLYDM,?,PZZLDM,?,?,NSRSBH,NSRMC,SWJGZZJGDM,?,TFRQ,HJJE,?,sysdate CJRQ,?,sysdate LRRQ,QXDM,YPZH,YPZZLDM,YWSZH,YNDZB,YWHM,? DYBZ,DYCS,YXBZ,KJLYDM,BZ,PZLXDM,ZHDM ");
				cpzbSQL.append("from SBDB.SB_JL_KJSSWSZM t ");
				cpzbSQL.append("where t.pzzldm =? ");
				cpzbSQL.append("and t.wszh=? ");
				cpzbSQL.append("and t.ndzb=? ");
				cpzbSQL.append("and t.yxbz='"+CodeConstant.WSZM_YXBZ_0+"' ");
				
				PreparedStatement ps = conn.prepareStatement(cpzbSQL.toString());
				int index =1;
				ps.setString(index++, sjm);
				ps.setString(index++, newWszh);
				ps.setString(index++, newNdzb);
				ps.setString(index++, ud.getSsdwdm());
				ps.setString(index++, ud.getYhid());
				ps.setString(index++, ud.getYhmc());
				ps.setString(index++, CodeConstant.SMSB_WSZ_UNPRINT);
				//ps.setInt(index++, tmpdycs);//打印次数
				ps.setString(index++, pzzldm);
				ps.setString(index++, wszh);
				ps.setString(index++, ndzb);
				
				ps.executeUpdate();
				savSucc = true;
			}
			catch(Exception e)
			{
				e.printStackTrace();
				throw new ApplicationException("保存税收完税证明信息到主表失败！");
			}
			
			return savSucc;
		}
		
		
		/**
		 * 保存新完税证号到主表
		 * @param conn
		 * @param data
		 */
		private boolean saveNewWszh(Connection conn,UserData ud, String wszh, String ndzb, String pzzldm, String newWszh,String newNdzb)
			throws BaseException
	    {
			boolean savSucc = false;
			
			try
			{
				StringBuffer cpzbSQL = new StringBuffer();
				cpzbSQL.append("insert into SBDB.SB_JL_KJSSWSZM (PZLYDM,SJM,PZZLDM,WSZH,NDZB,NSRSBH,NSRMC,SWJGZZJGDM,KJSWJGZZJGDM,TFRQ,HJJE,CJR,CJRQ,LRR,LRRQ,QXDM,YPZH,YPZZLDM,YWSZH,YNDZB,YWHM,DYBZ,DYCS,YXBZ,KJLYDM,BZ,PZLXDM,ZHDM) ");
				cpzbSQL.append("select PZLYDM,?,PZZLDM,?,?,NSRSBH,NSRMC,SWJGZZJGDM,?,TFRQ,HJJE,?,sysdate CJRQ,?,sysdate LRRQ,QXDM,YPZH,YPZZLDM,YWSZH,YNDZB,YWHM,? DYBZ,DYCS,YXBZ,KJLYDM,BZ,PZLXDM,ZHDM ");
				cpzbSQL.append("from SBDB.SB_JL_KJSSWSZM t ");
				cpzbSQL.append("where t.pzzldm =? ");
				cpzbSQL.append("and t.wszh=? ");
				cpzbSQL.append("and t.ndzb=? ");
				cpzbSQL.append("and t.yxbz='"+CodeConstant.WSZM_YXBZ_0+"' ");
				
				PreparedStatement ps = conn.prepareStatement(cpzbSQL.toString());
				int index =1;
				ps.setString(index++, StringUtil.randomString(10));
				ps.setString(index++, newWszh);
				ps.setString(index++, newNdzb);
				ps.setString(index++, ud.getSsdwdm());
				ps.setString(index++, ud.getYhid());
				ps.setString(index++, ud.getYhmc());
				ps.setString(index++, CodeConstant.SMSB_WSZ_UNPRINT);
				//ps.setInt(index++, tmpdycs);//打印次数
				ps.setString(index++, pzzldm);
				ps.setString(index++, wszh);
				ps.setString(index++, ndzb);
				
				ps.executeUpdate();
				savSucc = true;
			}
			catch(Exception e)
			{
				e.printStackTrace();
				throw new ApplicationException("保存税收完税证明信息到主表失败！");
			}
			
			return savSucc;
		}
		
		
		/**
		 * 更新打印标志(从未打印更新到已打印)
		 * @param ndzb
		 * @param pzzldm
		 * @param wszh
		 * @return
		 */
		private boolean updateDYBZ(Connection conn,String pzlydm,String wszh,String ndzb,String pzzldm)throws BaseException{
			boolean savSucc = false;//操作是否成功
			try
			{
				if( isNotNullOrBlank(conn) && isNotNullOrBlank(ndzb) && isNotNullOrBlank(pzzldm) && isNotNullOrBlank(wszh))
				{
					String updateZbSQL = "update SBDB.SB_JL_KJSSWSZM  set dybz = ? where pzlydm = ? and ndzb = ? and pzzldm = ? and wszh = ? and dybz = ? and yxbz = '"+CodeConstant.WSZM_YXBZ_0+"' ";
					
					PreparedStatement ps = conn.prepareStatement(updateZbSQL);
					
					int index =1;
					ps.setString(index++, CodeConstant.SMSB_WSZ_PRINT);
					ps.setString(index++, pzlydm);
					ps.setString(index++, ndzb);
					ps.setString(index++, pzzldm);
					ps.setString(index++, wszh);
					ps.setString(index++, CodeConstant.SMSB_WSZ_UNPRINT);
					
					ps.execute();
					savSucc = true;
				}
				else
				{
					throw new ApplicationException("更新打印标志出错！");
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
				throw new ApplicationException("更新打印标志出错！");
			}
			return savSucc;
		}
		
		
		
		
		/**
		 * 更新已打印次数（当前打印次数+1）
		 * @param conn
		 * @param ndzb
		 * @param pzzldm
		 * @param wszh
		 * @return
		 */
		private boolean updateDYCS(Connection conn,String pzlydm,String wszh,String ndzb,String pzzldm)throws BaseException{
			boolean savSucc = false;//操作是否成功
			
			try
			{
				if(isNotNullOrBlank(conn) && isNotNullOrBlank(ndzb) && isNotNullOrBlank(pzzldm) && isNotNullOrBlank(wszh))
				{
					String updateZbSQL = "update SBDB.SB_JL_KJSSWSZM  set dycs =(dycs+1) where pzlydm = ? and ndzb = ? and pzzldm = ? and wszh = ? and dybz = ? and yxbz = '"+CodeConstant.WSZM_YXBZ_0+"' ";
					
					PreparedStatement ps = conn.prepareStatement(updateZbSQL);
					
					int index =1;
					ps.setString(index++, pzlydm);
					ps.setString(index++, ndzb);
					ps.setString(index++, pzzldm);
					ps.setString(index++, wszh);
					ps.setString(index++, CodeConstant.SMSB_WSZ_PRINT);
					
					ps.execute();
					savSucc = true;
				}
				else
				{
					throw new ApplicationException("更新打印次数出错！");
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
				throw new ApplicationException("更新打印次数出错！");
			}
			return savSucc;
		}
		
		
		
		/**
		 * 更新有效标志
		 * @param conn
		 * @param ndzb
		 * @param pzzldm
		 * @param wszh
		 * @return
		 */
		private boolean updateYXBZ(Connection conn,String pzlydm,String wszh,String ndzb,String pzzldm)throws BaseException{
			boolean savSucc = false;//操作是否成功
			
			try
			{
				if(isNotNullOrBlank(conn) && isNotNullOrBlank(ndzb) && isNotNullOrBlank(pzzldm) && isNotNullOrBlank(wszh))
				{
					String updateZbSQL = "update SBDB.SB_JL_KJSSWSZM  set yxbz=? where pzlydm = ? and ndzb = ? and pzzldm = ? and wszh = ? and yxbz = ? ";
					
					PreparedStatement ps = conn.prepareStatement(updateZbSQL);
					
					int index =1;
					ps.setString(index++, CodeConstant.WSZM_YXBZ_1);
					ps.setString(index++, pzlydm);
					ps.setString(index++, ndzb);
					ps.setString(index++, pzzldm);
					ps.setString(index++, wszh);
					ps.setString(index++, CodeConstant.WSZM_YXBZ_0);
					
					ps.execute();
					savSucc = true;
				}
				else
				{
					throw new ApplicationException("更新打印次数出错！");
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
				throw new ApplicationException("更新打印次数出错！");
			}
			return savSucc;
		}
		
		
		
		
		/**
		 * 调用票证接口，获得一张新票 
		 */
		private  HashMap getNewPH(UserData ud,String hjje,String pzzldm)throws BaseException{
			HashMap map = new HashMap();
			String ndzb ="";
			String wszh="";
	        //获得完税证号
	        try {
	            String retResult = ServiceProxy.getNumber(ud, pzzldm, StringUtil.getDouble(hjje, 0), "1", "0");
	            
	            ndzb = retResult.substring(0, 4); //年度字别
	            wszh = retResult.substring(4); //完税证号
	            
	            //System.out.println("年度字别+++++++++++++++++"+ndzb);
	            //System.out.println("完税证号+++++++++++++++++"+wszh);
	            
	            map.put("ndzb", ndzb);
	            map.put("wszh", wszh);
	            
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            throw new ApplicationException("获取税收完税证明失败！");
	        }
	        
	        return map;
		}
		
		
		public BigDecimal string2BigDecimal(String StrJe) {
			if (StrJe == null || "".equals(StrJe)) {
				StrJe = "0.00";
			}
			// DecimalFormat deFormat = new DecimalFormat("#0.00");// 合计金额格式
			BigDecimal zje = new BigDecimal(StrJe);// 获得增加前的合计金额信息
			return zje;
		}
		
		
		private  boolean isNotNullOrBlank(Object obj){
			boolean isNotNull = true;
			
			if(obj instanceof String){
				String tempStr = (String)obj;
				if(tempStr == null || "".equals(tempStr)){
					isNotNull = false;
				}
			}else{
				if(obj == null){
					//System.out.println("++++++++++++++++++++++");
					isNotNull = false;
				}
			}
			return isNotNull;
		}
		
}
