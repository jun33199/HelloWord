/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.gtgsh.web;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.ttsoft.framework.form.BaseForm;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description: ���幤�̻�Ӫҵ����</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class GtgshyysrCxForm
    extends BaseForm
{
    /**
     * ���������
     */
    private String headJsjdm;

    /**
     * �걨����
     */
    private String headSbrq;

    /**
     *���������ֶ�
     */
    private String[] columns =
        {
        "jsjdm", "sbrq", "swdjzh", "swjgzzjgdm", "lrrdm",
        "lrrq", "fsdm", "skssksrq", "skssjsrq", "nd",
        "cjrq", "qxdm", "nsrmc"};

    /**
     * ���ڴ洢��ϸ�о�����ֵ List
     */
    private List dataList = new ArrayList();

    /**
     * ���������
     */
    private String tempJsjdm;

    /**
     * �걨����
     */
    private String tempSbrq;

    /**
     * ҳ�����
     * @param actionMapping struts.action.ActionMapping
     * @param httpServletRequest HttpServletRequest
     */

    public void reset (ActionMapping actionMapping,
                       HttpServletRequest httpServletRequest)
    {
        this.dataList.clear();
        this.headJsjdm = "";
        this.actionType = "Show";
    }

    public String getHeadJsjdm ()
    {
        return headJsjdm;
    }

    public void setHeadJsjdm (String headJsjdm)
    {
        this.headJsjdm = headJsjdm;
    }

    public String getHeadSbrq ()
    {
        return headSbrq;
    }

    public void setHeadSbrq (String headSbrq)
    {
        this.headSbrq = headSbrq;
    }

    public String[] getColumns ()
    {
        return columns;
    }

    public void setColumns (String[] columns)
    {
        this.columns = columns;
    }

    public List getDataList ()
    {
        return dataList;
    }

    public void setDataList (List dataList)
    {
        this.dataList = dataList;
    }

    public String getTempJsjdm ()
    {
        return tempJsjdm;
    }

    public void setTempJsjdm (String tempJsjdm)
    {
        this.tempJsjdm = tempJsjdm;
    }

    public String getTempSbrq ()
    {
        return tempSbrq;
    }

    public void setTempSbrq (String tempSbrq)
    {
        this.tempSbrq = tempSbrq;
    }

}
