/*
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.zqwh.web;

import java.util.Calendar;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.ttsoft.framework.form.BaseForm;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: �ڼ���ά��</p>
 * @author �������飭��½��
 * @version 1.1
 */
public class JjrwhForm
    extends BaseForm
{
    /**
     * ά�����
     */
    private String whnf;

    /**
     * ��������
     */
    private String[] holyArr = new String[0];

    /**
     * ����ڼ����ڵ�����
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