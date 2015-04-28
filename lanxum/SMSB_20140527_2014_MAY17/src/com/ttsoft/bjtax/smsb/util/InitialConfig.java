package com.ttsoft.bjtax.smsb.util;

import java.util.ResourceBundle;
import java.util.MissingResourceException;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class InitialConfig
{
    public static ResourceBundle rb = ResourceBundle.getBundle("InitialConfig");

    public InitialConfig()
    {
    }

    /**
     * 获取配置信息
     *
     * @param String 配置信息
     */
    public static String getStaticContextPath()
    {
        String staticContextPath = "../";

        try
        {
            staticContextPath = rb.getString("static_contextpath");
        }
        catch (MissingResourceException mre)
        {
            staticContextPath = "../";
        }

        return staticContextPath;
    }
}
