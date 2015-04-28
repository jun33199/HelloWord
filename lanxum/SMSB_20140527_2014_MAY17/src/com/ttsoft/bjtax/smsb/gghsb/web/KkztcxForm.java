package com.ttsoft.bjtax.smsb.gghsb.web;

import com.ttsoft.framework.form.BaseForm;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>Title: 北京地税核心征管系统--定期定额银行扣款-扣款状态查询</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c)北京市地方税务局，北京四一安信科技有限公司 版权所有 2005</p>
 * <p>Company: 北京四一安信科技有限公司</p>
 * @author not attributable
 * @version 1.0
 */

public class KkztcxForm
    extends BaseForm {
  public KkztcxForm() {
  }

  /** 年度          */
  private String nd;
  /** 征期          */
  private String zq;




  /**
   * 明细项目集合
   */
  private java.util.ArrayList dataList;

  /**
   * 获得年度
   * @return
   */
  public String getNd() {
    return nd;
  }

  /**
   * 获得征期
   * @return
   */
  public String getZq() {
    return zq;
  }

  /**
   * 设置征期
   * @param zq
   */
  public void setZq(String zq) {
    this.zq = zq;
  }

  /**
   * 设置年度
   * @param nd
   */
  public void setNd(String nd) {
    this.nd = nd;
  }
  /**
   * 获得数据集
   * @return
   */
  public java.util.ArrayList getDataList() {
      return dataList;
  }
  /**
   * 设置数据集
   * @param dataList
   */
  public void setDataList(java.util.ArrayList dataList) {
         this.dataList = dataList;
     }


}