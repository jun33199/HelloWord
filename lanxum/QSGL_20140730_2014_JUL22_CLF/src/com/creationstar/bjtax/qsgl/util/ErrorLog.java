/*
 * <p>Title: 北京地税核心征管系统－－契税管理</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，北京创讯益达软件技术有限公司，版权所有. </p>
 * <p>Company: 北京创讯益达软件技术有限公司</p>
 */

package com.creationstar.bjtax.qsgl.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Date;

import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.DataTranslate;
import com.ttsoft.thtflog.LogBeanClient;
import com.ttsoft.thtflog.LogData;


/**
 * <p>Title: 北京地税核心征管系统－－契税管理</p>
 * <p>Description: 日志记录类</p>
 * @author 契税开发组－－赵博
 * @version 1.0
 */
public class ErrorLog {
    /** 文件名称，默认为Error.log */
    public String fileName = "Error.log";

    /**
     * 默认构造方法
     */
    public ErrorLog() {
    }

    /**
     * 以文件名为参数构造一个ErrorLog对象。
     *
     * @param fileName 文件名参数
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * 将错误信息记入日志。
     *
     * @param contant 错误信息。
     */
    public synchronized void writeLog(String contant) {
        // 文件内容
        byte[] bt = {};

        // 操作结果
        int ioFlag = 0;

        // 文件内容
        String fcontant = "";

        // 文件初始化
        File fl = new File(fileName);

        try {
            // 若文件不存在，则创建
            if (!fl.exists()) {
                fl.createNewFile();
            }
        } catch (Exception ep) {
        }

        FileInputStream fis = null;

        FileOutputStream fos = null;

        try {
            fis = new FileInputStream(fl);
            ioFlag = fis.available();
            bt = new byte[ioFlag];
            ioFlag = fis.read(bt);
            fis.close();
        } catch (Exception ep) {
        }

        try {
            fos = new FileOutputStream(fl);

            fcontant = new String(bt);
            fcontant = fcontant + "\n" + contant;
            bt = fcontant.getBytes();
            fos.write(bt);
            fos.close();
        } catch (Exception ep) {
        }
    }

    /**
     * 调用此方法表明为测试。
     */
    public void isTest() {
        this.writeLog("测试！");
    }

    /**
     *
     * @param args the main()'s args.
     */
    public static void main(String[] args) {
        ErrorLog testFile = new ErrorLog();
        testFile.isTest();
    }

    /**
     * 向数据库中记录业务对数据库操作的错误日志
     *
     * @param userData 总控数据
     * @param action 操作类型
     * @param descObj 操作对象描述
     * @param result 操作结果
     */
    public static void makeLog(UserData userData, String action,
                               Object descObj, String result) {
        makeLog(userData, action, DataTranslate.translate2String(descObj),
                result);
    }

    /**
     * 向数据库中记录业务对数据库操作的错误日志
     *
     * @param userData 总控数据
     * @param action 操作类型
     * @param desc 操作描述
     * @param result 操作结果
     */
    public static void makeLog(UserData userData, String action, String desc,
                               String result) {
        try {
            // 构造LogData
            LogData logData = new LogData(userData);
            Date date = new Date(System.currentTimeMillis());

            if ((result != null) && !(result.equals(""))) {
                // 记录结束时间
                logData.setJssj(date);
            }

            // 记录运行时间
            logData.setYxsj(date);
            logData.setRzjbdm(logData.LEVEL_4);
            logData.setCzlx(action);
            logData.setCzms(desc);
            logData.setCzjg(result);

            // 通过LogBeanClient向消息Bean发送消息，记录日志
            LogBeanClient.thtflog(logData);
        } catch (Exception ignore) {
            ignore.printStackTrace();
        }
    }
}
