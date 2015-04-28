/**
 * @Title:       QysdsCheckUtil.java
 * @Description: TODO
 * @date:        2014-4-15上午09:59:30
 * @version:     V1.0
 */
package com.ttsoft.bjtax.smsb.util.qysdsCheck;

import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;
import com.ttsoft.bjtax.smsb.util.qysdsCheck.checkElement.CheckJdlxObserver;
import com.ttsoft.bjtax.smsb.util.qysdsCheck.checkElement.CheckJdlxObserver2;
import com.ttsoft.bjtax.smsb.util.qysdsCheck.checkElement.CheckZfbaObserver;
import com.ttsoft.bjtax.smsb.util.qysdsCheck.checkElement.IsInQsqObserver;
import com.ttsoft.bjtax.smsb.util.qysdsCheck.checkElement.IsSuitableYearObserver;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;

/**
 * @Description: TODO 在后台校验信息
 * @author: 	 Lijn
 * @time:        2014-4-15
 */
public class QysdsCheckUtil {
	
	/**
	 * @Description: constructor 在该方法中添加校验类
	 */
	private QysdsCheckUtil()
	{
		//重构工厂？
		subject1 = new Subject();
		subject1.addObserver(new IsSuitableYearObserver());
		subject1.addObserver(new IsInQsqObserver());
		subject1.addObserver(new CheckJdlxObserver());
		subject1.addObserver(new CheckZfbaObserver()); 
		
		subject2 = new Subject();
		subject2.addObserver(new IsSuitableYearObserver());
		subject2.addObserver(new IsInQsqObserver());
		
		
		
		subject4 = new Subject();
		subject4.addObserver(new IsInQsqObserver());
		subject4.addObserver(new CheckJdlxObserver());
		
		subject5 = new Subject();
		subject5.addObserver(new CheckJdlxObserver());
		
		subject6 = new Subject();
		subject6.addObserver(new CheckJdlxObserver2());
		
	}
	
	private static QysdsCheckUtil instance;
	
	public static QysdsCheckUtil getInstance()
	{
		if(instance == null)
		{
			instance = new QysdsCheckUtil();
		}
		return instance;
	}

	/**
	 * @description:校验组1
	 * @description:该校验组被如下功能使用：上门申报季报（A类）--2012，2008，2009；
	 * 				        		上门申报季报（B类）--2008，2009，2012；
	 * 				        		上门申报年报查账--2013，2008，2009；
	 * 				     		            上门申报年报核定--2012，2008,2009；
	 * 				        	            上门申报 企业所得税分支机构年度纳税申报表--2013，2012
	 * 				                                                上门申报企业所得税汇总纳税分支机构分配表2008，2013,2009
	 * 								上门申报查账征收纳税人基本信息登记表--2013，2009,2008，
	 * @description:该组校验内容包括：版本控制，清算期，征管范围鉴定类型（根据时间段）；总分备案
	*/
	public final Subject subject1 ;
	
	/**
	 * @description:校验组2
	 * @description:该校验组被如下功能使用：上门申报季报（A类）--2006
	 * 				                                                上门申报季报（B类）--2006
	 * 				                                                上门申报年报查账--2006；
	 *   			                                                上门申报年报核定--2006
	 *   			                                                上门申报 查账征收纳税人基本信息登记表--2006	
	 * @description:该组校验内容包括：版本控制，清算期;
	*/
	public final Subject subject2 ;

	/**
	 * @description:校验组4
	 * @description:该校验组被如下功能使用： 企业所得税减免税备案	
	 * @description:该组校验内容包括：清算期,征管范围鉴定类型(根据时间段);
	*/
	public final Subject subject4;
	
	/**
	 * @description:校验组5
	 * @description:该校验组被如下功能使用： 企业所得税清算申报模块	
	 * @description:该组校验内容包括：征管范围鉴定类型(根据时间段);
	*/
	public final Subject subject5;
	
	/**
	 * @description:校验组6
	 * @description:该校验组被如下功能使用： 企业所得税清算管理模块	
	 * @description:该组校验内容包括：征管范围鉴定类型(根据时间点);
	 */
	public final Subject subject6;
	
	
	/**
	 * @Description: TODO 
	 * @param check:校验种类
	 * @param qysdsNewForm ：form
	 * @throws BaseException 
	 */
 	public void check(Subject check, CheckBean checkBean) throws BaseException 
	{
		//调用观察者
		check.checkElement(checkBean);
	}
}
