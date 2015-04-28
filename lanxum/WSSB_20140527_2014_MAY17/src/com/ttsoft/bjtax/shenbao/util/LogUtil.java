package com.ttsoft.bjtax.shenbao.util;

import java.sql.Date;
import com.ttsoft.common.util.DataTranslate;
import com.ttsoft.common.model.UserData;
import com.ttsoft.thtflog.LogBeanClient;
import com.ttsoft.thtflog.LogData;

/**
 * 调用应用安全的接口记录日志
 */
public class LogUtil
{
    static LogUtil instance;

    public LogUtil()
    {
    }

    /**
     * 得到LogUtil的实例
     * @return
     */
    public static LogUtil getInstance()
    {
        if(instance == null)
            instance = new LogUtil();
        return instance;
    }

    /**
     * 用来保存前台传人的数据。
     */

    /**
     *<p> 记录日志
     *
     *   <br>构造LogData
     *   <br>LogData logData = new LogData();
     *   <br>logData.xxx = userData.xxx;
     *   <br>if ((result != null) && (result !=""))
     *   <br>{
     *      <br>//记录结束时间
     *      <br>logData.jssj = new Date();
     *   <br>}
     *   <br>else
     *   <br>{
     *    <br>  //记录运行时间
     *      <br>logData.yxsj = new Date();
     *  <br> }
     *   <br>logData.jbdm = LogData.LEVEL4;
     *   <br>logData.czlx  = action;
     *   <br>logData.czms  = desc;
     *   <br>logData.czjg  = result;
     *<br>
     *   <br>//通过LogBeanClient向消息Bean发送消息，记录日志
     *   <br>LogBeanClient.ttftLog(logData);
     * <br>}
     *
     * @param userData 总控数据
     * @param action 操作类型
     * @param desc 操作描述
     * @param result 操作结果
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
            //构造LogData
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
                //记录结束时间
                logData.setJssj(date);
            }

            //记录运行时间
            logData.setYxsj(date);
            logData.setRzjbdm(logData.LEVEL_3);
            logData.setCzlx(action);
            logData.setCzms(desc);
            logData.setCzjg(result);

            //通过LogBeanClient向消息Bean发送消息，记录日志
            LogBeanClient.thtflog(logData);
        }
        catch (Exception ignore)
        {
        }
    }

}
