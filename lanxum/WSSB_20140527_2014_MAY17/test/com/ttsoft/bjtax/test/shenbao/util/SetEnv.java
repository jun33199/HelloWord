package com.ttsoft.bjtax.test.shenbao.util;

import java.util.Properties;

public final class SetEnv
{
    public static void setProperties()
    {
        System.getProperties().setProperty("isTest","true");
    }

}