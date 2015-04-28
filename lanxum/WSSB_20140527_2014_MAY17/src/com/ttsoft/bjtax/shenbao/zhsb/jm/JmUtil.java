/**
 * @Title:       JmUtil.java
 * @Description: TODO
 * @date:        2014-10-17����11:03:06
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
     * @Description: TODO ����С΢��ҵ����Ӫҵ˰��
     * @param mxInf
     * @return
	 * @throws BaseException 
     */
    public static boolean separator(DeclareInfor declareInfor ,UserData ud ,Map datamap ,StringBuffer msg) throws BaseException
    {
    	List jmXwqyList = new ArrayList();	//���������list
    	
    	if(declareInfor==null)
    	{
    		return false;
    	}
    	//δ������ϸ����
    	List mxList = declareInfor.getSbjkmxInfo();
    	Map checkMap = new HashMap();

    	if(mxList==null)
    	{
    		return false;
    	}
    	
	
    	/*------С΢��ҵӪҵ˰����:�Ƴ�Դ���ݣ�����ר�ŵ�list����---------*/
    	boolean hasYysXwqy = false;					//�����Ƴ�����˰
    		
    	/*-------------------------------------�±�-----------*/
    	List jmXwqyYysYdList = new ArrayList();		//�¶�Ӫҵ˰С΢��ҵ
    	double sumYdJsJeXwqyYys = 0.0;
    	Iterator iteratorYd = mxList.iterator();	//���������������Ƴ��� ��ϸ�е�������ע��ConcurrentModifiedException	
    	while(iteratorYd.hasNext())
    	{
    		Sbjkmx sbjkmx = (Sbjkmx)iteratorYd.next();
    		
    		
    		String szsmdm_temp = sbjkmx.getSzsmdm();
    		//˰��˰Ŀ�� �Ƿ�������ɽ��Ӫҵ˰
    		if("02".equals(szsmdm_temp.substring(0, 2)) && !"020091".equals(szsmdm_temp) && !"020092".equals(szsmdm_temp))
    		{	
    			//�Ƿ����±����Ƿ��ǵ���
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
    	
    	//�Ƿ�3���˰���
    	if(sumYdJsJeXwqyYys>30000 || sumYdJsJeXwqyYys == 0){
    		mxList.addAll(jmXwqyYysYdList);
    	}else{
    		jmXwqyList.addAll(jmXwqyYysYdList);
    		hasYysXwqy = true;
    	}
    	
    	/*----------------------------����-------------------*/
    	List jmXwqyYysJdList = new ArrayList();		//����Ӫҵ˰С΢��ҵ
    	double sumJdJsJeXwqyYys = 0.0;
    	Iterator iteratorJd = mxList.iterator();	//���������������Ƴ��� ��ϸ�е�������ע��ConcurrentModifiedException	
    	while(iteratorJd.hasNext())
    	{
    		Sbjkmx sbjkmx = (Sbjkmx)iteratorJd.next();
    		String szsmdm_temp = sbjkmx.getSzsmdm();
    		//˰��˰Ŀ�� �Ƿ�������ɽ��Ӫҵ˰
    		if("02".equals(szsmdm_temp.substring(0, 2)) && !"020091".equals(szsmdm_temp) && !"020092".equals(szsmdm_temp))
    		{		
    			//�Ƿ��Ǽ������Ƿ��ǵ���
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
    	
    	//�Ƿ�9���˰���
    	if(sumJdJsJeXwqyYys>90000 ||  sumJdJsJeXwqyYys == 0){
    		mxList.addAll(jmXwqyYysJdList);
    	}else{
    		jmXwqyList.addAll(jmXwqyYysJdList);
    		hasYysXwqy = true;
    	}
    	
    	/*---------------------------------------------����Ӫҵ˰������ͬʱ���⸽�ӷ�----------*/
    	if(hasYysXwqy == true)
    	{
    		msg.append("��Ӫҵ˰��˰������Ӫҵ�����3��Ԫ������Ϊ9��Ԫ,������������˰�ˣ���������Ӫҵ˰������˰�˵������걨ģ����м���˰�걨��<br/>");
    		
    		Iterator iteratorfjf = mxList.iterator();	//���������������Ƴ��� ��ϸ�е�������ע��ConcurrentModifiedException	
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
    	
    	
    	/*---------------------------------------------------------�����Ļ���ҵ�����--------*/
    	List jmXwqyWhsyjsfYdList = new ArrayList();		//�Ļ���ҵ�����
    	double sumYdJsJeXwqyWhsyjsf = 0.0;
    	Iterator iteratorWhsyjsf = mxList.iterator();	//���������������Ƴ��� ��ϸ�е�������ע��ConcurrentModifiedException	
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
    		//˰��˰Ŀ�� �Ƿ�������ɽ��Ӫҵ˰
    		if("535610".equals(szsmdm_temp))
    		{	
    			//�Ƿ����±����Ƿ��ǵ���
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
    	
    	//�Ƿ�3���˰���
    	if(sumYdJsJeXwqyWhsyjsf<=30000 && sumYdJsJeXwqyWhsyjsf > 0 && sumYdJsJeXwqyYys<=30000){

    		msg.append("�԰�����˰����Ӫҵ�����3��Ԫ����3��Ԫ���Ľ��������ˣ������Ļ���ҵ����ѡ�����������˵������걨ģ����м���˰�걨��<br/>");
    		jmXwqyList.addAll(jmXwqyWhsyjsfYdList);
    		
    	}else{
    		mxList.addAll(jmXwqyWhsyjsfYdList);
    	}
    	
    	
    	
    	
    	//һԪ�����ں�����ʵ��
    	/*--*/
    	
    	if(jmXwqyList.size()>0)
    	{
    		datamap.put("JMLIST",jmXwqyList);
    		return true;
    	}
    	
    	return false;
    }
}
