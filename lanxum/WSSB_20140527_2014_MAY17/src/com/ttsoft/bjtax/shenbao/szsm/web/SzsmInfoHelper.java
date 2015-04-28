package com.ttsoft.bjtax.shenbao.szsm.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.shenbao.constant.CodeConstant;
import com.ttsoft.bjtax.shenbao.constant.ProcessorNames;
import com.ttsoft.bjtax.shenbao.model.domain.Sqsbtmp;
import com.ttsoft.bjtax.shenbao.model.domain.Szsm;
import com.ttsoft.bjtax.shenbao.proxy.ShenbaoProxy;
import com.ttsoft.bjtax.shenbao.szsm.SzsmActionConstant;
import com.ttsoft.bjtax.shenbao.util.FriendHelper;
import com.ttsoft.bjtax.shenbao.util.SzsmSyjdUtil;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;
/**
 * 
 * @author zhaob
 *  这个类是用来将税种税目信息查出并放入session
 */
public class SzsmInfoHelper {

	/**
	 * 检查Session里是否存在税种税目相关信息
	 */
	 public static void putSzsmInfoIntoSession(HttpServletRequest request,UserData ud,String sklxdm) throws BaseException{
		 try{
		 Object virginSzsm = request.getSession().getAttribute("virginSzsmForest");
		 virginSzsm = null;
	    	if(virginSzsm==null){
	    		//add by zhaoss 20140704
	    		//从数据库中查询税种税目信息
		    	SWDJJBSJ djsj = FriendHelper.getSWDJJBSJ(request);
		    	HashMap dataMap = new HashMap();
		    	dataMap.put("djsj", djsj);
		    	dataMap.put("sklxdm", sklxdm);
		    	
		    	VOPackage vo = new VOPackage();
		    	vo.setAction(SzsmActionConstant.QUERY_SZSMTREE_INFO);
		    	vo.setData(dataMap);
		    	vo.setUserData(ud);
		    	vo.setProcessor(ProcessorNames.SZSM_PROCESSOR);
		    	HashMap map = (HashMap)ShenbaoProxy.getInstance().process(vo);
		    	//所有的税种税目代码表
		    	Map allSzsmMap = (Map)map.get("szsmMap"); 
		    	Map tempAllSzsmMap=new HashMap();//过滤后的税目
		    	java.util.Iterator it = allSzsmMap.entrySet().iterator();
		    	Map checkMap=new HashMap();
		    	while(it.hasNext()){
		    		java.util.Map.Entry entry = (java.util.Map.Entry)it.next();
		    		//如果返回是true，那么允许添加节点
		    		String szsmdm=(String)entry.getKey();
		    		
		    		
		    		boolean fl=SzsmSyjdUtil.isQs(szsmdm);
		    		if(fl){
		    			boolean flag=SzsmSyjdUtil.checkSzsmdmBySyjdlx(request,djsj,szsmdm);
	            		checkMap.put(szsmdm, new Boolean(flag));
	            		if(flag){
	            			tempAllSzsmMap.put(entry.getKey(), entry.getValue());
	            		}
		    		} else{
		            	
		            	tempAllSzsmMap.put(entry.getKey(), entry.getValue());
		            }
		    		
		    		
		            
		    	}
		    	allSzsmMap=tempAllSzsmMap;
		    	//获得税种税目LIST
		    	List szsmRawMaterial = (List)map.get("szsm");
		    	List tempSzsmRawMaterial=new ArrayList();
		    	for (int i = 0; i < szsmRawMaterial.size(); i++) {
		    		Szsm ss=(Szsm) szsmRawMaterial.get(i);
		    		String szsmdm=ss.getSzsmdm();
		    		boolean fl=SzsmSyjdUtil.isQs(szsmdm);
		    		if(fl){
		    			if(checkMap.get(szsmdm)==null){
		            		boolean flag=SzsmSyjdUtil.checkSzsmdmBySyjdlx(request,djsj,szsmdm);
		            		if(flag){
		            			tempSzsmRawMaterial.add(ss);
		            		}
	    			    }else{
		            	  if(((Boolean)checkMap.get(szsmdm)).booleanValue()){
		            		  tempSzsmRawMaterial.add(ss);
			    		  }
	    			    }
		    		} else{
		            	
		    			tempSzsmRawMaterial.add(ss);
		            }
		    		
		    		
		    		
		    		
		    		 
				}
		    	szsmRawMaterial=tempSzsmRawMaterial;
		    	//获得要过滤的税种税目
		    	List toBeFilterszsm = (List)map.get("toBeFilterszsm");
		    	List tempToBeFilterszsm=new ArrayList();
		    	for (int i = 0; i < toBeFilterszsm.size(); i++) {
		    		Szsm ss=(Szsm) toBeFilterszsm.get(i);
		    		String szsmdm=ss.getSzsmdm();
		    		boolean fl=SzsmSyjdUtil.isQs(szsmdm);
		    		if(fl){
		    			if(checkMap.get(szsmdm)==null){
		            		boolean flag=SzsmSyjdUtil.checkSzsmdmBySyjdlx(request,djsj,szsmdm);
		            		if(flag){
		            			tempToBeFilterszsm.add(ss);
		            		}
	    			    }else{
		            	  if(((Boolean)checkMap.get(szsmdm)).booleanValue()){
		            		  tempToBeFilterszsm.add(ss);
			    		  }
	    			    }
		    		} else{
		            	
		    			tempToBeFilterszsm.add(ss);
		            }
		    		
		    		
		    		
				}
		    	toBeFilterszsm=tempToBeFilterszsm;
		    	//获得常用税种税目
		    	List favoriteSzsm = (List)map.get("favorite");
		    	List tempFavoriteSzsm=new ArrayList();
		    	for (int i = 0; i < favoriteSzsm.size(); i++) {
		    		Sqsbtmp ss=(Sqsbtmp) favoriteSzsm.get(i);
		    		String szsmdm=ss.getSzsmdm();
		    		boolean fl=SzsmSyjdUtil.isQs(szsmdm);
		    		if(fl){
		    			if(checkMap.get(szsmdm)==null){
		            		boolean flag=SzsmSyjdUtil.checkSzsmdmBySyjdlx(request,djsj,szsmdm);
		            		if(flag){
		            			tempFavoriteSzsm.add(ss);
		            		}
	    			    }else{
		            	  if(((Boolean)checkMap.get(szsmdm)).booleanValue()){
		            		  tempFavoriteSzsm.add(ss);
			    		  }
	    			    }
		    		} else{
		    			tempFavoriteSzsm.add(ss);
		            }
		    		
		    		
		    		
		    		
		    	}
		    	favoriteSzsm=tempFavoriteSzsm;
		    	//start added by zhangyj 20120905
		    	List toBeAlertszsm = (List)map.get("toBeAlertszsm");//获得要提示的税种税目
		    	List tempToBeAlertszsm=new ArrayList();
		    	for (int i = 0; i < toBeAlertszsm.size(); i++) {
		    		String[] ss=(String[]) toBeAlertszsm.get(i);
		    		String szsmdm=ss[0];
		    		boolean fl=SzsmSyjdUtil.isQs(szsmdm);
		    		if(fl){
		    			if(checkMap.get(szsmdm)==null){
		            		boolean flag=SzsmSyjdUtil.checkSzsmdmBySyjdlx(request,djsj,szsmdm);
		            		if(flag){
		            			tempToBeAlertszsm.add(ss);
		            		}
	    			    }else{
		            	  if(((Boolean)checkMap.get(szsmdm)).booleanValue()){
		            		  tempToBeAlertszsm.add(ss);
			    		  }
	    			    }
		    		} else{
		    			tempToBeAlertszsm.add(ss);
		            }
		    		
		    		
		    		
		    	}
		    	toBeAlertszsm=tempToBeAlertszsm;
		    	//end added by zhangyj 20120905
		    	
		    	
		    	//added by zhangyj 20100517 start
		    	
		    	favoriteSzsm=favoriteSzsmOrderHandling(favoriteSzsm);
		    	szsmRawMaterial=OrderHandling(szsmRawMaterial);

		    	//added by zhangyj 20100517 end 
		    	

		    	//added by tujb 201406 start
		    	List wtdzszsm = (List)map.get("wtdzszsm");//获得委托代征税种税目
		    	//added by tujb 201406 end 
		    	
		    	HashMap favoriteSzsmMap = new HashMap();
		    	for(int i = 0;i<favoriteSzsm.size();i++){
		    		Sqsbtmp tmp = (Sqsbtmp)favoriteSzsm.get(i);
		    		favoriteSzsmMap.put(tmp.getSzsmdm(),tmp);
		    	}
		    	SzsmForest virginSzsmForest = new SzsmForest();
		    	virginSzsmForest.create(szsmRawMaterial,null,allSzsmMap);
		    	SzsmForest filteredSzsmForest = new SzsmForest();
		    	filteredSzsmForest.create(szsmRawMaterial,toBeFilterszsm,allSzsmMap);
		    	
		    	
		    	//将处理完成的数据加入session
		    	request.getSession(false).setAttribute("virginSzsmForest",virginSzsmForest);
		    	request.getSession(false).setAttribute("filteredSzsmForest",filteredSzsmForest);
		    	request.getSession(false).setAttribute("favoriteSzsmMap",favoriteSzsmMap);
		    	request.getSession(false).setAttribute("toBeFilterszsm",toBeFilterszsm);
		    	request.getSession(false).setAttribute("favoriteSzsm",favoriteSzsm);
		    	//start added by zhangyj 20120905
		    	request.getSession(false).setAttribute("toBeAlertszsm",toBeAlertszsm);
		    	//end added by zhangyj 20120905
		    	//start added by tujb 201406
		    	request.getSession(false).setAttribute("wtdzszsm",wtdzszsm);
		    	//end added by tujb 201406
	    	}
		 }catch(Exception e){
	    		e.printStackTrace();
	    		throw ExceptionUtil.getBaseException(e);
	    	}
	   }
	   /**
		 * 刷新session 
		 */
	 public static void refreshFavoriteSzsmInSession(HttpServletRequest request,UserData ud,String sklxdm) throws BaseException{
		 try{
		 	//request.getSession().setAttribute("virginSzsmForest",null);
//		 	从数据库中查询税种税目信息
	    	SWDJJBSJ djsj = FriendHelper.getSWDJJBSJ(request);
	    	HashMap dataMap = new HashMap();
	    	dataMap.put("djsj", djsj);
	    	dataMap.put("sklxdm", sklxdm);
	    	
	    	VOPackage vo = new VOPackage();
	    	vo.setAction(SzsmActionConstant.QUERY_SZSMTREE_INFO);
	    	vo.setData(dataMap);
	    	vo.setUserData(ud);
	    	vo.setProcessor(ProcessorNames.SZSM_PROCESSOR);
	    	HashMap map = (HashMap)ShenbaoProxy.getInstance().process(vo);
	    	
	    	Map allSzsmMap = (Map)map.get("szsmMap"); //所有的税种税目代码表
	    	Map tempAllSzsmMap=new HashMap();
	    	java.util.Iterator it = allSzsmMap.entrySet().iterator();
	    	Map checkMap=new HashMap();
	    	while(it.hasNext()){
	    		java.util.Map.Entry entry = (java.util.Map.Entry)it.next();
	    		//如果返回是true，那么允许添加节点
	    		String szsmdm=(String)entry.getKey();
	    		boolean fl=SzsmSyjdUtil.isQs(szsmdm);
	    		if(fl){
	    			boolean flag=SzsmSyjdUtil.checkSzsmdmBySyjdlx(request,djsj,szsmdm);
            		checkMap.put(szsmdm, new Boolean(flag));
            		if(flag){
            			tempAllSzsmMap.put(entry.getKey(), entry.getValue());
            		}
	    		} else{
	    			tempAllSzsmMap.put(entry.getKey(), entry.getValue());
	            }
	    	}
	    	allSzsmMap=tempAllSzsmMap;
	    	
	    	List szsmRawMaterial = (List)map.get("szsm");//获得税种税目LIST
	    	List tempSzsmRawMaterial=new ArrayList();
	    	for (int i = 0; i < szsmRawMaterial.size(); i++) {
	    		Szsm ss=(Szsm) szsmRawMaterial.get(i);
	    		String szsmdm=ss.getSzsmdm();
	    		boolean fl=SzsmSyjdUtil.isQs(szsmdm);
	    		if(fl){
	    			if(checkMap.get(szsmdm)==null){
	            		boolean flag=SzsmSyjdUtil.checkSzsmdmBySyjdlx(request,djsj,szsmdm);
	            		if(flag){
	            			tempSzsmRawMaterial.add(ss);
	            		}
    			    }else{
	            	  if(((Boolean)checkMap.get(szsmdm)).booleanValue()){
	            		  tempSzsmRawMaterial.add(ss);
		    		  }
    			    }
	    		} else{
	    			tempSzsmRawMaterial.add(ss);
	            }
			}
	    	szsmRawMaterial=tempSzsmRawMaterial;
	    	//System.out.println(szsmRawMaterial.size());
	    	List toBeFilterszsm = (List)map.get("toBeFilterszsm");//获得要过滤的税种税目
	    	List tempToBeFilterszsm=new ArrayList();
	    	for (int i = 0; i < toBeFilterszsm.size(); i++) {
	    		Szsm ss=(Szsm) toBeFilterszsm.get(i);
	    		String szsmdm=ss.getSzsmdm();
	    		boolean fl=SzsmSyjdUtil.isQs(szsmdm);
	    		if(fl){
	    			if(checkMap.get(szsmdm)==null){
	            		boolean flag=SzsmSyjdUtil.checkSzsmdmBySyjdlx(request,djsj,szsmdm);
	            		if(flag){
	            			tempToBeFilterszsm.add(ss);
	            		}
    			    }else{
	            	  if(((Boolean)checkMap.get(szsmdm)).booleanValue()){
	            		  tempToBeFilterszsm.add(ss);
		    		  }
    			    }
	    		} else{
	    			tempToBeFilterszsm.add(ss);
	            }
			}
	    	toBeFilterszsm=tempToBeFilterszsm;
	    	List favoriteSzsm = (List)map.get("favorite");//获得常用税种税目
	    	List tempFavoriteSzsm=new ArrayList();
	    	for (int i = 0; i < favoriteSzsm.size(); i++) {
	    		Sqsbtmp ss=(Sqsbtmp) favoriteSzsm.get(i);
	    		String szsmdm=ss.getSzsmdm();
	    		if(szsmdm.equals(CodeConstant.SZSMDM_FZJGHSQJ)||szsmdm.equals(CodeConstant.SZSMDM_ZJGHSQJ_2013YH)||
	    				szsmdm.equals(CodeConstant.SZSMDM_ZJGHSQJ_2013YQ)||szsmdm.equals(CodeConstant.SZSMDM_DLNSYS)||
	    				szsmdm.equals(CodeConstant.SZSMDM_DLNSHSQJ)||szsmdm.equals(CodeConstant.SZSMDM_ZJGYS)||
	    				szsmdm.equals(CodeConstant.SZSMDM_FZJGYS)){
	            	if(checkMap.get(szsmdm)==null){
	            		boolean flag=SzsmSyjdUtil.checkSzsmdmBySyjdlx(request,djsj,szsmdm);
	            		if(flag){
	            			tempFavoriteSzsm.add(ss);
	            		}
    			    }else{
	            	  if(((Boolean)checkMap.get(szsmdm)).booleanValue()){
	            		  tempFavoriteSzsm.add(ss);
		    		  }
    			    }
	    		}else{
	    			tempFavoriteSzsm.add(ss);
	    		}
	    	}
	    	favoriteSzsm=tempFavoriteSzsm;
	    	
	    	//added by zhangyj 20100517 start
	    	
	    	favoriteSzsm=favoriteSzsmOrderHandling(favoriteSzsm);
	    	szsmRawMaterial=OrderHandling(szsmRawMaterial);
	    	
	    	//added by zhangyj 20100517 end 
	    	
	    	HashMap favoriteSzsmMap = new HashMap();
	    	for(int i = 0;i<favoriteSzsm.size();i++){
	    		Sqsbtmp tmp = (Sqsbtmp)favoriteSzsm.get(i);
	    		favoriteSzsmMap.put(tmp.getSzsmdm(),tmp);
	    	}
	    	
	    	SzsmForest filteredSzsmForest = new SzsmForest();
	    	filteredSzsmForest.create(szsmRawMaterial,toBeFilterszsm,allSzsmMap);
	    	
	    	//将处理完成的数据加入session
	    	request.getSession(false).setAttribute("filteredSzsmForest",filteredSzsmForest);
	    	request.getSession(false).setAttribute("favoriteSzsmMap",favoriteSzsmMap);
	    	request.getSession(false).setAttribute("favoriteSzsm",favoriteSzsm);
		 }catch(Exception e){
			 e.printStackTrace();
			 throw ExceptionUtil.getBaseException(e);
		 }
	    	
	   }

	    /**
	     * 税种税目list的特殊排序处理（023203-其他保险业务（人寿、财产保险以外的业务），调整到023204-交通事故责任强制保险 后 ）
	     * @param list 按税种税目代码排序的税种税目list
	     * @return List newlist 特别需求处理后的税种税目list
	     */
	    private static List OrderHandling(List list)
	    {
		    	
	    	List newlist=new ArrayList();
	    	Szsm temp1 = new Szsm();
	    	boolean mark=false;
	    	for (int i = 0;i<list.size();i++)
	    	{
	    		Szsm tmp =(Szsm)list.get(i);
	    		
	    		if(tmp.getSzsmdm().substring(0, 4).equals("0232")){
	    			
	    			if(tmp.getSzsmdm().equals("023203")){
	    				mark=true;
	    				temp1=(Szsm)list.get(i);
	    				if(((Szsm)list.get(list.size()-1)).getSzsmdm().equals("023203"))
	    				{
	    					newlist.add(temp1);
	    					mark=false;
	    				}else{   					
	    					if(!((Szsm)list.get(i+1)).getSzsmdm().equals("023204"))
		    				{
		    					newlist.add(temp1);
		    					mark=false;
		    				}
	    				}

	    					 
	    			}
	    			else if(tmp.getSzsmdm().equals("023204")){
	    				newlist.add(tmp);
	    				if(mark){
		    				newlist.add(temp1);
		    				mark=false;
		    			}
	    			}else{
	    				newlist.add(tmp);
	    			}
	    			
	    		}
	    		else
	    		{
	    			if(mark){
	    				newlist.add(temp1);
	    				mark=false;
	    			}
	    			newlist.add(tmp);
	    		}
	    	}
	       return newlist;

	    }	 

	    /**
	     * 常用税种税目list的特殊排序处理（023203-其他保险业务（人寿、财产保险以外的业务），调整到023204-交通事故责任强制保险 后 ）
	     * @param list 按税种税目代码排序的常用税种税目list
	     * @return List newlist 特别需求处理后的常用税种税目list
	     */
	    private static List favoriteSzsmOrderHandling(List list)
	    {	

	    	List newlist=new ArrayList();
	    	Sqsbtmp tmp1 =new Sqsbtmp();
	    	boolean mark=false;
	    	for (int i = 0;i<list.size();i++)
	    	{
	    		Sqsbtmp tmp =(Sqsbtmp)list.get(i);
	    		
	    		if(tmp.getSzsmdm().substring(0, 4).equals("0232")){
	    			
	    			if(tmp.getSzsmdm().equals("023203")){
	    				mark=true;
	    				tmp1=(Sqsbtmp)list.get(i);
	    				if(((Sqsbtmp)list.get(list.size()-1)).getSzsmdm().equals("023203"))
	    				{
	    					newlist.add(tmp1);
	    					mark=false;
	    				}else{   					
	    					if(!((Sqsbtmp)list.get(i+1)).getSzsmdm().equals("023204"))
		    				{
		    					newlist.add(tmp1);
		    					mark=false;
		    				}
	    				}

	    					 
	    			}
	    			else if(tmp.getSzsmdm().equals("023204")){
	    				newlist.add(tmp);
	    				if(mark){
		    				newlist.add(tmp1);
		    				mark=false;
		    			}
	    			}else{
	    				newlist.add(tmp);
	    			}
	    			
	    		}
	    		else
	    		{
	    			if(mark){
	    				newlist.add(tmp1);
	    				mark=false;
	    			}
	    			newlist.add(tmp);
	    		}
	    	}
	       return newlist;

	    }

}
