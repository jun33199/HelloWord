package com.ttsoft.bjtax.smsb.qysdsjmsbagl.basx16.web;



public class Basx16ActionHelper {
	 public static String nullConvertToNbsp(String value){
	    	return (value == null || value.trim().length() == 0)?"&nbsp;":value;
	    }
	    
	    public static String getForwardPath(int i,String wsh){
	    	
	    	String basqwsh = "";
	    	
	    	switch (i) {
			case 1:
				basqwsh = "/webapp/smsb/qysds/jmsbagl/Basx03Action.do?actionType=Show&basqwsh="+wsh;
				break;
			case 2:
				basqwsh = "/webapp/smsb/qysds/jmsbagl/Basx03Action.do?actionType=Check&czlx=2&xh="+wsh;
				break;
			case 3:
				basqwsh = "/webapp/smsb/qysds/jmsbagl/Basx03Action.do?actionType=Check&czlx=3&xh="+wsh;
				break;
			case 4:
				basqwsh = "/webapp/smsb/qysds/jmsbagl/Basx03Action.do?actionType=Check&czlx=4&xh="+wsh;
				break;
			}
	    	
	    	return basqwsh;
	    	
	    }
	    
	   
    
    
}
