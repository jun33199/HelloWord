/*
 * Created on 2009-12-21
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.syax.bjtax.shenbao.util;
import java.beans.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.struts.util.BeanUtils;
import org.apache.struts.util.ConvertUtils;
import org.apache.struts.util.PropertyUtils;

import com.syax.bjtax.shenbao.jmba.xmlvo.JmbaZbVO;
import com.syax.bjtax.shenbao.model.dm.JMBASXDM;

/**
 * @author guzx
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DmBeanUtils {

	public static void main(String[] args) {
	}
	public static PropertyDescriptor[] getProperties(String beanName){
		try {
            System.out.println("beanName = "+beanName);
				BeanInfo bf=Introspector.getBeanInfo(Class.forName(beanName));

				//
				PropertyDescriptor[] pds = bf.getPropertyDescriptors();

				return pds;
			} catch (IntrospectionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();

		}
		return null;
	}
	
	   public static JmbaZbVO completeDm(JmbaZbVO vo,Map dmMap){
	   	vo.setJmbasxmc(((JMBASXDM)dmMap.get(vo.getJmbasxdm())).getJMBASXMC());
	   	return vo;
	   }		  
	
}
