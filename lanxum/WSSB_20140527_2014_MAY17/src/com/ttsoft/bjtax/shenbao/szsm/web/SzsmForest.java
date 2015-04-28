package com.ttsoft.bjtax.shenbao.szsm.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ttsoft.bjtax.shenbao.model.domain.Szsm;
import com.ttsoft.common.util.Debug;
/**
 * 
 *
 * @author zhaob 2006-7-6
 *
 */
public class SzsmForest implements Serializable {
	/**
	 * 
	 */

	public SzsmForest()
    {
    }

    /**
     * 税种税目森林
     */
    private Map szsmForest = new HashMap();

    /**
     * 税种税目代码表
     */
    private Map szsmTable;

   // 所有根的集合
    private List roots = new ArrayList();

    /**
     * Create a forest
     * @param rawTree The raw material of a forest
     * @param filterList The elements to be filtered
     * @param disabled Do you wanna disable the element that is filtered?
     * @param szsmTable Code table:szsm
     */
    public void create(List rawTree, List hide, Map szsmTable)
    {
        //System.out.println("调用森林的create方法传近来的rawTree大小为"+rawTree.size());
        this.szsmTable = szsmTable;
        if (hide != null)
        {
        	//System.out.println("未过滤");
            for (int i=rawTree.size()-1; i>=0; i--)
            {
                String szsmdm = ((Szsm)rawTree.get(i)).getSzsmdm();
        	    for (int j=0; j<hide.size(); j++)
                {
        		    if (szsmdm.equals(((Szsm)hide.get(j)).getSzsmdm()))
        		    {
        			    rawTree.remove(i);
        			    break;
            		}
            	}
            }
        }
        createTree(rawTree);
        
    }

    /**
     * output the html code
     * @return String
     */
  


    // construct a tree
    private void createTree(List rawTree)
    {
    	 //System.out.println("调用森林的createTree方法:传近来的rawTree集合大小为"+rawTree.size());
        for(int i = 0; i < rawTree.size(); i++)
        {
            Szsm node = (Szsm)rawTree.get(i);
           // System.out.println("正在构造"+node.getSzsmdm()+node.getSzsmmc());
            // 对一个税目（叶子）构造其祖先
            constructAncestors(node);
        }
    }

    // 构造一棵树，从叶子construct到root
    private void constructAncestors(Szsm node)
    {
        while(!node.getCcbs().equals("0"))  // unless it is not the root
        {
            String father = node.getFjddm();

            if (node.getSzsmdm().equals(father))
            {
                // Its father is itself. wrong data! :(
                Debug.out(father + ": wrong data.");
                return;
            }

            List tmp = (List)szsmForest.get(father);
          
            if(tmp == null)
            {
                tmp = new ArrayList();
                szsmForest.put(father,tmp);
               
               
            }

            boolean found = false;
            for(int i = 0; i < tmp.size(); i++)
            {
                if(((Szsm)tmp.get(i)).getSzsmdm().equals(node.getSzsmdm()))
                {
                    found = true;
                }
            }

            if(!found)
            {
            	/*if(node.getFjddm().equals("02")){
            		System.out.println("发现一个"+node.getSzsmmc());
            	}*/
            	tmp.add(node);
               
            }
            
            node = (Szsm)szsmTable.get(father);

            if (node == null)
            {
            	
                return;
            }
        }
        if(!roots.contains(node))
        {
            roots.add(node);
            
        }
    }
    public List getRootsList(){
    	return this.roots;
    }
    public Map getSzsmForest(){
    	return this.szsmForest;
    }
}
