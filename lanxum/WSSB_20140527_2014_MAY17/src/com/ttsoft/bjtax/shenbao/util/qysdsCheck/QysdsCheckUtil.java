/**
 * @Title:       QysdsCheckUtil.java
 * @Description: TODO
 * @date:        2014-4-15上午09:59:30
 * @version:     V1.0
 */
package com.ttsoft.bjtax.shenbao.util.qysdsCheck;

import com.ttsoft.bjtax.shenbao.util.qysdsCheck.checkElement.CheckJdlxObserver;
import com.ttsoft.bjtax.shenbao.util.qysdsCheck.checkElement.CheckJdlxObserver2;
import com.ttsoft.bjtax.shenbao.util.qysdsCheck.checkElement.CheckZfbaObserver;
import com.ttsoft.bjtax.shenbao.util.qysdsCheck.checkElement.IsInQsqObserver;
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
		subject1.addObserver(new IsInQsqObserver());		//清算期
		subject1.addObserver(new CheckJdlxObserver());		//征管范围鉴定
		subject1.addObserver(new CheckZfbaObserver());		//总分备案
		
		subject2 = new Subject();
		subject2.addObserver(new CheckJdlxObserver2());		//征管范围鉴定
		
		subject3 = new Subject();
		subject3.addObserver(new CheckJdlxObserver());		//征管范围鉴定
	}
	
	private static QysdsCheckUtil instance;
	
	/**
	 * @Description: TODO 懒汉
	 * @return
	 */
	public static QysdsCheckUtil getInstance()
	{
		if(instance == null)
		{
			instance = new QysdsCheckUtil();
		}
		return instance;
	}

	/**
	 * 校验组1
	 * @description： 该校验组被如下功能使用：网上申报季报2012a,b， 
	 * 					年报基本信息表
	 * 					分支机构年报，
	 * 					企业所得税减免备案
	 * @description： 该组校验内容包括：清算期，征管范围鉴定类型,总分备案；
	*/
	public final Subject subject1 ;
	
	
	
	/**
	 * 校验组2
	 * @description： 该校验组被如下功能使用:企业所得税清算	
	 * @description： 该组校验内容包括：征管范围鉴定类型；
	*/
	public final Subject subject2 ;
	
	/**
	 * 校验组3
	 * @description： 该校验组被如下功能使用:企业所得税清算	
	 * @description： 该组校验内容包括：征管范围鉴定类型(时间段)；
	*/
	public final Subject subject3 ;
	
	
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
