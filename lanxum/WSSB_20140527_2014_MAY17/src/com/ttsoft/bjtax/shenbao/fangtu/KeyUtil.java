package com.ttsoft.bjtax.shenbao.fangtu;


import java.util.Date;
import java.util.Random;

public class KeyUtil
{
    private static int KEY_LEN = 16;
    private static long prevTime = 0;
    private static int clockSequence = 0;

    public KeyUtil()
    {
    }

    /**
     * ���Ψһ��16λ���ȵ�keyֵ��
     *
     * @return String Ĭ�ϳ��ȵ�keyֵ
     */
    public synchronized static String getKey()
    {
        String key = "";
        long time = new Date().getTime();

        if (time == prevTime)
        {
            int cs = clockSequence + 1;

            if (cs > 65535)
            {
                cs = 0;
            }

            if (clockSequence > cs)
            {
                clockSequence = 0;

                try
                {
                    Thread.sleep(1);
                    time = new Date().getTime();
                }
                catch (InterruptedException ie)
                {
                    ie.printStackTrace();
                }
            }
            else
            {
                clockSequence = cs;
            }
        }

        key = time + Integer.toHexString(clockSequence);
        prevTime = time;

        return key;
    }
}
