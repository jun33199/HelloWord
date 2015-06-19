package com.ttsoft.bjtax.shenbao.util;

import java.sql.Date;
import com.ttsoft.common.util.DataTranslate;
import com.ttsoft.common.model.UserData;
import com.ttsoft.thtflog.LogBeanClient;
import com.ttsoft.thtflog.LogData;

/**
 * ����Ӧ�ð�ȫ�Ľӿڼ�¼��־
 */
public class LogUtil
{
    static LogUtil instance;

    public LogUtil()
    {
    }

    /**
     * �õ�LogUtil��ʵ��
     * @return
     */
    public static LogUtil getInstance()
    {
        if(instance == null)
            instance = new LogUtil();
        return instance;
    }

    /**
     * ��������ǰ̨���˵����ݡ�
     */

    /**
     *<p> ��¼��־
     *
     *   <br>����LogData
     *   <br>LogData logData = new LogData();
     *   <br>logData.xxx = userData.xxx;
     *   <br>if ((result != null) && (result !=""))
     *   <br>{
     *      <br>//��¼����ʱ��
     *      <br>logData.jssj = new Date();
     *   <br>}
     *   <br>else
     *   <br>{
     *    <br>  //��¼����ʱ��
     *      <br>logData.yxsj = new Date();
     *  <br> }
     *   <br>logData.jbdm = LogData.LEVEL4;
     *   <br>logData.czlx  = action;
     *   <br>logData.czms  = desc;
     *   <br>logData.czjg  = result;
     *<br>
     *   <br>//ͨ��LogBeanClient����ϢBean������Ϣ����¼��־
     *   <br>LogBeanClient.ttftLog(logData);
     * <br>}
     *
     * @param userData �ܿ�����
     * @param action ��������
     * @param desc ��������
     * @param result �������
     */
    public void log(UserData userData, String action, Object descObj,
                        String result)
    {
        log(userData, action, DataTranslate.translate2String(descObj), result);

    }

    public void log(UserData userData, String action, String desc,
                        String result)
    {
        try
        {
            //����LogData
            LogData logData = null;
            if (userData == null)
            {
                logData = new LogData();
            }
            else
            {
                logData = new LogData(userData);
            }
            Date date = new Date(System.currentTimeMillis());

            if ( (result != null) && ! (result.equals("")))
            {
                //��¼����ʱ��
                logData.setJssj(date);
            }

            //��¼����ʱ��
            logData.setYxsj(date);
            logData.setRzjbdm(logData.LEVEL_3);
            logData.setCzlx(action);
            logData.setCzms(desc);
            logData.setCzjg(result);

            //ͨ��LogBeanClient����ϢBean������Ϣ����¼��־
            LogBeanClient.thtflog(logData);
        }
        catch (Exception ignore)
        {
        }
    }

}
