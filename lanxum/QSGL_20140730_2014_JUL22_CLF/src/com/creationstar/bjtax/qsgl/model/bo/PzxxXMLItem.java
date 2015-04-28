package com.creationstar.bjtax.qsgl.model.bo;

/**
 * <p>Title:契税管理-批量受理-配置信息录入 </p>
 *
 * <p>Description:xmlItem对象类 </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: 北京四一安信科技有限公司</p>
 *
 * @author fujx
 * @version 1.0
 */
public class PzxxXMLItem {
    private String itemName = "";
    private String itemValue = "";
    public PzxxXMLItem() {
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemValue() {
        return itemValue;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemValue(String itemValue) {
        this.itemValue = itemValue;
    }
}
