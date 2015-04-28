package com.ttsoft.bjtax.shenbao.fangtu.taglib;

import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;
import org.apache.struts.util.RequestUtils;
import org.apache.struts.util.ResponseUtils;

import com.ttsoft.bjtax.shenbao.fangtu.LabelValueBean;

public class FormOptionsTag extends TagSupport {
	Logger logger = Logger.getLogger(FormOptionsTag.class);
	
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

	protected String collectionName = null;

	protected String collectionProperty = null;

	// public String codeList = null;

	public String getCollectionName() {
		return collectionName;
	}

	public void setCollectionName(String collectionName) {
		this.collectionName = collectionName;
	}

	public String getCollectionProperty() {
		return collectionProperty;
	}

	public void setCollectionProperty(String collectionProperty) {
		this.collectionProperty = collectionProperty;
	}

	public int doStartTag() throws JspException {

		logger.debug("name=" + name);
		logger.debug("property=" + property);

		Object value = null;
		try {
			if (property == null) {
				value = RequestUtils.lookup(pageContext, name, scope);
				if (value == null) {
					logger.debug("值不存在");
					// return (SKIP_BODY); // Nothing to output
				}

			} else {
				// Look up the requested property value
				value = RequestUtils.lookup(pageContext, name, property, scope);
				if (value == null) {
					logger.debug("值为空");
					// return (SKIP_BODY); // Nothing to output
				}
			}
		} catch (Exception e) {
			logger.debug("在指定范围内没有找到bean");
			e.printStackTrace();
			throw new JspException(e.getMessage());
		}

		List lxList = null;
		try {
			if (collectionProperty == null) {
				lxList = (List)RequestUtils.lookup(pageContext, collectionName, scope);
				if (lxList == null) {
					logger.debug("值不存在");
					return (SKIP_BODY); // Nothing to output
				}

			} else {
				// Look up the requested property value
				lxList = (List)RequestUtils.lookup(pageContext, collectionName, collectionProperty, scope);
				if (lxList == null) {
					logger.debug("值为空");
					 return (SKIP_BODY); // Nothing to output
				}
			}
		} catch (Exception e) {
			logger.debug("在指定范围内没有找到 Collection");
			e.printStackTrace();
			throw new JspException(e.getMessage());
		}		

		logger.debug("lxList size: " + lxList.size());
		String label = "";

		try {
			for (int i = 0; i < lxList.size(); i++) {
				LabelValueBean bean = (LabelValueBean) lxList.get(i);
				if (bean.getValue().equals(value)) {
					label = bean.getLabel();
				}
			}
			logger.debug("label=" + label);
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