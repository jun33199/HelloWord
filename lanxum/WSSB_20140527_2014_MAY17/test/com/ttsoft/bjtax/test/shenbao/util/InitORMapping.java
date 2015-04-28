package com.ttsoft.bjtax.test.shenbao.util;

import com.ekernel.db.connection.Manager;

public final class InitORMapping
{
    private InitORMapping()
    {
    }
    public static void init_aux()
    {
        try
        {
            if(System.getProperty("isMapped") == null)
            {
                Manager.getInstance().initialize();
                System.out.println(
                    "\n============ ORMappingStartup Running OK ! ==============\n");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println(
                "=======================   ! ! !   ========================");
            System.out.println(
                "Some errors occurred in the testcase's ORMapping initial. \n\n");
            e.printStackTrace(System.out);
        }
    }

}