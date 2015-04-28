/**
 * @Title:       JmUtil.java
 * @Description: TODO
 * @date:        2014-10-17上午11:03:06
 * @version:     V1.0
 */
package com.ttsoft.bjtax.shenbao.zhsb.jm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.ttsoft.bjtax.shenbao.constant.ActionConstant;
import com.ttsoft.bjtax.shenbao.constant.CodeConstant;
import com.ttsoft.bjtax.shenbao.constant.ProcessorNames;
import com.ttsoft.bjtax.shenbao.model.client.DeclareInfor;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkmx;
import com.ttsoft.bjtax.shenbao.proxy.ShenbaoProxy;

import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.util.VOPackage;

/**
 * @Description: TODO
 * @author: 	 Lijn
 * @time:        2014-10-17
 */
public class JmUtil {

	/**
     * @Description: TODO 分离小微企业减免营业税和
     * @param mxInf
     * @return
	 * @throws BaseException 
     */
    public static boolean separator(DeclareInfor declareInfor ,UserData ud ,Map datamap ,StringBuffer msg) throws BaseException
    {
    	List jmXwqyList = new ArrayList();	//被分离出的list
    	
    	if(declareInfor==null)
    	{
    		return false;
    	}
    	//未分离明细数据
    	List mxList = declareInfor.getSbjkmxInfo();
    	Map checkMap = new HashMap();

    	if(mxList==null)
    	{
    		return false;
    	}
    	
	
    	/*------小微企业营业税减免:移除源数据，生成专门的list数据---------*/
    	boolean hasYysXwqy = false;					//用于移除附加税
    		
    	/*-------------------------------------月报-----------*/
    	List jmXwqyYysYdList = new ArrayList();		//月度营业税小微企业
    	double sumYdJsJeXwqyYys = 0.0;
    	Iterator iteratorYd = mxList.iterator();	//如有其它减免需移除该 明细中的数据需注意ConcurrentModifiedException	
    	while(iteratorYd.hasNext())
    	{
    		Sbjkmx sbjkmx = (Sbjkmx)iteratorYd.next();
    		
    		
    		String szsmdm_temp = sbjkmx.getSzsmdm();
    		//税种税目是 非罚款或滞纳金的营业税
    		if("02".equals(szsmdm_temp.substring(0, 2)) && !"020091".equals(szsmdm_temp) && !"020092".equals(szsmdm_temp))
    		{	
    			//是否是月报，是否是当期
    			checkMap.put("DATE",sbjkmx);
    			checkMap.put("ZQLX","07");
    			 VOPackage vo = new VOPackage();
    		     vo.setAction(ActionConstant.INT_ACTION_JMZQ);
    		     vo.setProcessor(ProcessorNames.ZHSB_PROCESSOR);
    		     vo.setUserData(ud);
    		     vo.setData(checkMap);
        		Boolean isYysXwjm_dq = (Boolean)ShenbaoProxy.getInstance().process(vo);
    			
        		if(isYysXwjm_dq.booleanValue())
        		{
        			jmXwqyYysYdList.add(sbjkmx);
        			iteratorYd.remove();
        			sumYdJsJeXwqyYys+=sbjkmx.getJsje().doubleValue();
        		}
    		}
    	}
    	
    	//是否够3万计税金额
    	if(sumYdJsJeXwqyYys>30000 || sumYdJsJeXwqyYys == 0){
    		mxList.addAll(jmXwqyYysYdList);
    	}else{
    		jmXwqyList.addAll(jmXwqyYysYdList);
    		hasYysXwqy = true;
    	}
    	
    	/*----------------------------季报-------------------*/
    	List jmXwqyYysJdList = new ArrayList();		//季度营业税小微企业
    	double sumJdJsJeXwqyYys = 0.0;
    	Iterator iteratorJd = mxList.iterator();	//如有其它减免需移除该 明细中的数据需注意ConcurrentModifiedException	
    	while(iteratorJd.hasNext())
    	{
    		Sbjkmx sbjkmx = (Sbjkmx)iteratorJd.next();
    		String szsmdm_temp = sbjkmx.getSzsmdm();
    		//税种税目是 非罚款或滞纳金的营业税
    		if("02".equals(szsmdm_temp.substring(0, 2)) && !"020091".equals(szsmdm_temp) && !"020092".equals(szsmdm_temp))
    		{		
    			//是否是季报，是否是当期
    			checkMap.put("DATE",sbjkmx);
    			checkMap.put("ZQLX","06");
    			 VOPackage vo = new VOPackage();
    		     vo.setAction(ActionConstant.INT_ACTION_JMZQ);
    		     vo.setProcessor(ProcessorNames.ZHSB_PROCESSOR);
    		     vo.setUserData(ud);
    		     vo.setData(checkMap);
        		Boolean isYysXwjm_jd = (Boolean)ShenbaoProxy.getInstance().process(vo);
        		if(isYysXwjm_jd.booleanValue())
        		{
        			jmXwqyYysJdList.add(sbjkmx);
        			iteratorJd.remove();
        			sumJdJsJeXwqyYys+=sbjkmx.getJsje().doubleValue();
        		}
    		}
    	}
    	
    	//是否够9万计税金额
    	if(sumJdJsJeXwqyYys>90000 ||  sumJdJsJeXwqyYys == 0){
    		mxList.addAll(jmXwqyYysJdList);
    	}else{
    		jmXwqyList.addAll(jmXwqyYysJdList);
    		hasYysXwqy = true;
    	}
    	
    	/*---------------------------------------------存在营业税减免则同时减免附加费----------*/
    	if(hasYysXwqy == true)
    	{
    		msg.append("对营业税纳税人中月营业额不超过3万元（季度为9万元,含本数）的纳税人，暂免征收营业税。请纳税人到减免申报模块进行减免税申报。<br/>");
    		
    		Iterator iteratorfjf = mxList.iterator();	//如有其它减免需移除该 明细中的数据需注意ConcurrentModifiedException	
        	while(iteratorfjf.hasNext())
        	{
        		Sbjkmx sbjkmx = (Sbjkmx)iteratorfjf.next();
        		String szsmdm_temp = sbjkmx.getSzsmdm();
        		if("540010".equals(szsmdm_temp) || "510010".equals(szsmdm_temp) || "100010".equals(szsmdm_temp))
        		{
        			iteratorfjf.remove();
        			jmXwqyList.add(sbjkmx);
        		}	
        	}
    	}
    	
    	
    	/*---------------------------------------------------------存在文化事业建设费--------*/
    	List jmXwqyWhsyjsfYdList = new ArrayList();		//文化事业建设费
    	double sumYdJsJeXwqyWhsyjsf = 0.0;
    	Iterator iteratorWhsyjsf = mxList.iterator();	//如有其它减免需移除该 明细中的数据需注意ConcurrentModifiedException	
    	while(iteratorWhsyjsf.hasNext())
    	{
    		Sbjkmx sbjkmx = (Sbjkmx)iteratorWhsyjsf.next();
    		
    		//20150101-20171231	
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    		
			try {
				Date now = new Date();
				Date start = sdf.parse("20150201");
				Date end = sdf.parse("20180131");
				if(now.before(start) || now.after(end)){
	    			continue;
	    		}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    		String szsmdm_temp = sbjkmx.getSzsmdm();
    		//税种税目是 非罚款或滞纳金的营业税
    		if("535610".equals(szsmdm_temp))
    		{	
    			//是否是月报，是否是当期
    			checkMap.put("DATE",sbjkmx);
    			checkMap.put("ZQLX","07");
    			 VOPackage vo = new VOPackage();
    		     vo.setAction(ActionConstant.INT_ACTION_JMZQ);
    		     vo.setProcessor(ProcessorNames.ZHSB_PROCESSOR);
    		     vo.setUserData(ud);
    		     vo.setData(checkMap);
        		Boolean isWhsyjsfXwjm_yd = (Boolean)ShenbaoProxy.getInstance().process(vo);	
        		if(isWhsyjsfXwjm_yd.booleanValue())
        		{
        			jmXwqyWhsyjsfYdList.add(sbjkmx);
        			iteratorWhsyjsf.remove();
        			sumYdJsJeXwqyWhsyjsf+=sbjkmx.getJsje().doubleValue();
        		}
    		}
    	}
    	
    	//是否够3万计税金额
    	if(sumYdJsJeXwqyWhsyjsf<=30000 && sumYdJsJeXwqyWhsyjsf > 0 && sumYdJsJeXwqyYys<=30000){

    		msg.append("对按月纳税的月营业额不超过3万元（含3万元）的缴纳义务人，免征文化事业建设费。请缴纳义务人到减免申报模块进行减免税申报。<br/>");
    		jmXwqyList.addAll(jmXwqyWhsyjsfYdList);
    		
    	}else{
    		mxList.addAll(jmXwqyWhsyjsfYdList);
    	}
    	
    	
    	
    	
    	//一元减免在后面已实现
    	/*--*/
    	
    	if(jmXwqyList.size()>0)
    	{
    		datamap.put("JMLIST",jmXwqyList);
    		return true;
    	}
    	
    	return false;
    }
}
