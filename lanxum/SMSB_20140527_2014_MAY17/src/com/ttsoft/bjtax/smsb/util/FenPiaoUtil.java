/*
 * <p>Title: 项目名称-文件中文名称</p>
 * <p>Description: 文件描述</p>
 * <p>Copyright: Copyright (c) 2007 北京市地方税务局，北京立思辰电子系统技术有限公司，版权所有. </p>
 * <p>Company: 北京立思辰电子系统技术有限公司</p>
 * @author wangxq
 * @date 2014-7-31
 * @version 1.0
 */
package com.ttsoft.bjtax.smsb.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>Title: 缴款书分票</p>
 * <p>Description: TODO</p>
 * @author wangxq
 * @date 2014-7-31
 */
public class FenPiaoUtil {
	   private static int EVE_PIAO_SIZE=7; 
	   
	   public static ArrayList fenPiao(Map map){
		   ArrayList reFpList=new ArrayList();
		   diGuifp(reFpList, new ArrayList(), map);
		   return reFpList;
	   }
	   private static void diGuifp(List ls1,List ls2,Map map){
		  if(ls2.size()==0){
			  String jkpzh=getMax(map);
			  List maxLs=(List)map.get(jkpzh);
			  if(maxLs.size()==EVE_PIAO_SIZE){
				  ls1.add(maxLs);
				  map.remove(jkpzh);
				  ls2=new ArrayList();
			  }else if(maxLs.size()<EVE_PIAO_SIZE){
				  ls2.addAll(maxLs);
				  map.remove(jkpzh);
			  }else{
				  for(int i=0;i<EVE_PIAO_SIZE;i++){
					  ls2.add(maxLs.get(0));
					  maxLs.remove(0);
				  }
				  ls1.add(ls2);
				  ls2=new ArrayList();
			  }
		  }else{
			  String jkpzh=getPretty(map,ls2.size());
			  if(jkpzh.equals("")){
				  jkpzh=getMin(map);
			  }
			  List prettyLs=(List)map.get(jkpzh);
			  if(ls2.size()+prettyLs.size()==EVE_PIAO_SIZE){
				  ls2.addAll(prettyLs);
				  ls1.add(ls2);
				  map.remove(jkpzh);
				  ls2=new ArrayList();
			  }else if(ls2.size()+prettyLs.size()<EVE_PIAO_SIZE){
				  ls2.addAll(prettyLs);
				  map.remove(jkpzh);
			  }else {
				  ls1.add(ls2);
				  ls2=new ArrayList();
			  }
		  }
		  
		  if(map.size()>0){
			  diGuifp(ls1,ls2,map);
		  }else{
			 if(ls2.size()>0){
			    ls1.add(ls2);
		     }
		  }
	  }
	   
	   private static String getMax(Map map){
		   java.util.Iterator it = map.entrySet().iterator();
		   String jkpzh="";
		   int size=0;
		   while(it.hasNext()){
		    java.util.Map.Entry entry = (java.util.Map.Entry)it.next();
		    if(size==0){
		    	jkpzh=(String)entry.getKey();
		    	size=((List)entry.getValue()).size();
		    }else{
			    if(((List)entry.getValue()).size()>size){
			    	jkpzh=(String)entry.getKey();
			    	size=((List)entry.getValue()).size();
			    }
		    }
		   }
		   return jkpzh;
	   }
	   
	   private static String getMin(Map map){
		   java.util.Iterator it = map.entrySet().iterator();
		   String jkpzh="";
		   int size=0;
		   while(it.hasNext()){
		    java.util.Map.Entry entry = (java.util.Map.Entry)it.next();
		    if(size==0){
		    	jkpzh=(String)entry.getKey();
		    	size=((List)entry.getValue()).size();
		    }else{
			    if(((List)entry.getValue()).size()<size){
			    	jkpzh=(String)entry.getKey();
			    	size=((List)entry.getValue()).size();
			    }
		    }
		   }
		   return jkpzh;
	   }
	   
	   private static String getPretty(Map map,int size){
		   java.util.Iterator it = map.entrySet().iterator();
		   String jkpzh="";
		   while(it.hasNext()){
		       java.util.Map.Entry entry = (java.util.Map.Entry)it.next();
			    if((((List)entry.getValue()).size()+size)==EVE_PIAO_SIZE){
			    	jkpzh=(String)entry.getKey();
			    	break;
			    }
		   }
		   return jkpzh;
	   }
	   public static void main(String[] args) {
		   
	   }
}
