/*
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */
package com.ttsoft.bjtax.smsb.zqwh.web;

import java.util.Calendar;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.ttsoft.framework.form.BaseForm;

/**
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Description: 节假日维护</p>
 * @author 开发六组－－陆峰
 * @version 1.1
 */
public class JjrwhForm
    extends BaseForm
{
    /**
     * 维护年份
     */
    private String whnf;

    /**
     * 假日数组
     */
    private String[] holyArr = new String[0];

    /**
     * 保存节假日期的数组
     */
    private String[] saveHoliday;

    public JjrwhForm ()
    {
        Calendar c = Calendar.getInstance();
        whnf = "" + c.get(Calendar.YEAR);
    }

    public void reset (ActionMapping actionMapping,
                       HttpServletRequest httpServletRequest)
    {
    }

    public String getWhnf ()
    {
        return whnf;
    }

    public void setWhnf (String whnf)
    {
        this.whnf = whnf;
    }

    public String[] getHolyArr ()
    {
        return holyArr;
    }

    public void setHolyArr (String[] holyArr)
    {
        this.holyArr = holyArr;
    }

    public String[] getSaveHoliday ()
    {
        return saveHoliday;
    }

    public void setSaveHoliday (String[] saveHoliday)
    {
        this.saveHoliday = saveHoliday;
    }
}