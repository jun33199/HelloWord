package com.ttsoft.bjtax.shenbao.fangtu.taglib;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.struts.util.ResponseUtils;

import com.ttsoft.bjtax.shenbao.codetable.web.CodeTableUtil;
import com.ttsoft.bjtax.shenbao.constant.CodeTable;
import com.ttsoft.bjtax.shenbao.fangtu.LabelValueBean;
import com.ttsoft.bjtax.shenbao.model.domain.Zjlx;

import org.apache.struts.util.RequestUtils;

public class LxTag extends TagSupport {
	  /**
	   * Name of the bean that contains the data we will be rendering.
	   */
	  private String name = null;

	  public String getName() {
	    return (this.name);
	  }

	  public void setName(String name) {
	    this.name = name;
	  }

	  /**
	   * Name of the property to be accessed on the specified bean.
	   */
	  private String property = null;

	  public String getProperty() {
	    return (this.property);
	  }

	  public void setProperty(String property) {
	    this.property = property;
	  }

	  public String scope = null;
	  

	public String codeList = null;

    public String getCodeList()
    {
        return(this.codeList);
    }

    public void setCodeList(String codeList)
    {
        this.codeList = codeList;
    }


    public int doStartTag() throws JspException {
        
        //System.out.println("enter my tag");
        //System.out.println("codeList="+codeList);
        

        
        Object value = null;
        try{
        if (property == null) {
        	value = RequestUtils.lookup(pageContext, name, scope);
            if (value == null) {
            	//System.out.println("值不存在");
                return (SKIP_BODY); // Nothing to output
            }

        }
        else {
          // Look up the requested property value
          value =
              RequestUtils.lookup(pageContext, name, property, scope);
          if (value == null) {
        	  //System.out.println("值为空");
            return (SKIP_BODY); // Nothing to output
          }
        }
        }
        catch(Exception e) {
        	//System.out.println("在指定范围内没有找到bean");
        	e.printStackTrace();
            throw new JspException(e.getMessage());
        }
        
        
        
//      取得证件类型代码表
		List lxList = CodeTableUtil.getCodeTableList((HttpServletRequest)pageContext.getRequest(),codeList );
		if ( "ZJLX_LIST".equals( codeList )) {
			List zjlx_list = new ArrayList();
			for (Iterator iter = lxList.iterator(); iter.hasNext();) {
				Zjlx element = (Zjlx) iter.next();
				zjlx_list.add( new LabelValueBean(element.getZjlxmc(), element.getZjlxdm()) );
			}
			lxList = zjlx_list;
		}
		String label = null;

        try {
			for(int i=0; i<lxList.size(); i++)
			{
				LabelValueBean bean = (LabelValueBean)lxList.get(i);
				//System.out.println("bean.value="+bean.getValue());
				if(bean.getValue().equals(value))
				{
					label = bean.getLabel();
					break;
				}
			}
			//System.out.println("label="+ label);
			ResponseUtils.write(pageContext, label);
            
            
            
        } catch (Exception e) {
        	e.printStackTrace();
            throw new JspException(e.getMessage());
        }
        // Continue processing this page
        return (EVAL_BODY_INCLUDE);

    }

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}



}