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
 *  �������������˰��˰Ŀ��Ϣ���������session
 */
public class SzsmInfoHelper {

	/**
	 * ���Session���Ƿ����˰��˰Ŀ�����Ϣ
	 */
	 public static void putSzsmInfoIntoSession(HttpServletRequest request,UserData ud,String sklxdm) throws BaseException{
		 try{
		 Object virginSzsm = request.getSession().getAttribute("virginSzsmForest");
		 virginSzsm = null;
	    	if(virginSzsm==null){
	    		//add by zhaoss 20140704
	    		//�����ݿ��в�ѯ˰��˰Ŀ��Ϣ
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
		    	//���е�˰��˰Ŀ�����
		    	Map allSzsmMap = (Map)map.get("szsmMap"); 
		    	Map tempAllSzsmMap=new HashMap();//���˺��˰Ŀ
		    	java.util.Iterator it = allSzsmMap.entrySet().iterator();
		    	Map checkMap=new HashMap();
		    	while(it.hasNext()){
		    		java.util.Map.Entry entry = (java.util.Map.Entry)it.next();
		    		//���������true����ô������ӽڵ�
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
		    	//���˰��˰ĿLIST
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
		    	//���Ҫ���˵�˰��˰Ŀ
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
		    	//��ó���˰��˰Ŀ
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
		    	List toBeAlertszsm = (List)map.get("toBeAlertszsm");//���Ҫ��ʾ��˰��˰Ŀ
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
		    	List wtdzszsm = (List)map.get("wtdzszsm");//���ί�д���˰��˰Ŀ
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
		    	
		    	
		    	//��������ɵ����ݼ���session
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
		 * ˢ��session 
		 */
	 public static void refreshFavoriteSzsmInSession(HttpServletRequest request,UserData ud,String sklxdm) throws BaseException{
		 try{
		 	//request.getSession().setAttribute("virginSzsmForest",null);
//		 	�����ݿ��в�ѯ˰��˰Ŀ��Ϣ
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
	    	
	    	Map allSzsmMap = (Map)map.get("szsmMap"); //���е�˰��˰Ŀ�����
	    	Map tempAllSzsmMap=new HashMap();
	    	java.util.Iterator it = allSzsmMap.entrySet().iterator();
	    	Map checkMap=new HashMap();
	    	while(it.hasNext()){
	    		java.util.Map.Entry entry = (java.util.Map.Entry)it.next();
	    		//���������true����ô������ӽڵ�
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
	    	
	    	List szsmRawMaterial = (List)map.get("szsm");//���˰��˰ĿLIST
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
	    	List toBeFilterszsm = (List)map.get("toBeFilterszsm");//���Ҫ���˵�˰��˰Ŀ
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
	    	List favoriteSzsm = (List)map.get("favorite");//��ó���˰��˰Ŀ
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
	    	
	    	//��������ɵ����ݼ���session
	    	request.getSession(false).setAttribute("filteredSzsmForest",filteredSzsmForest);
	    	request.getSession(false).setAttribute("favoriteSzsmMap",favoriteSzsmMap);
	    	request.getSession(false).setAttribute("favoriteSzsm",favoriteSzsm);
		 }catch(Exception e){
			 e.printStackTrace();
			 throw ExceptionUtil.getBaseException(e);
		 }
	    	
	   }

	    /**
	     * ˰��˰Ŀlist������������023203-��������ҵ�����١��Ʋ����������ҵ�񣩣�������023204-��ͨ�¹�����ǿ�Ʊ��� �� ��
	     * @param list ��˰��˰Ŀ���������˰��˰Ŀlist
	     * @return List newlist �ر���������˰��˰Ŀlist
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
	     * ����˰��˰Ŀlist������������023203-��������ҵ�����١��Ʋ����������ҵ�񣩣�������023204-��ͨ�¹�����ǿ�Ʊ��� �� ��
	     * @param list ��˰��˰Ŀ��������ĳ���˰��˰Ŀlist
	     * @return List newlist �ر��������ĳ���˰��˰Ŀlist
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
