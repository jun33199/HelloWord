/*
 * <p>Title: ������˰��������ϵͳ������˰����</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣�������Ѷ�������������޹�˾����Ȩ����. </p>
 * <p>Company: ������Ѷ�������������޹�˾</p>
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
 * <p>Title: ������˰��������ϵͳ������˰����</p>
 * <p>Description: ��־��¼��</p>
 * @author ��˰�����飭���Բ�
 * @version 1.0
 */
public class ErrorLog {
    /** �ļ����ƣ�Ĭ��ΪError.log */
    public String fileName = "Error.log";

    /**
     * Ĭ�Ϲ��췽��
     */
    public ErrorLog() {
    }

    /**
     * ���ļ���Ϊ��������һ��ErrorLog����
     *
     * @param fileName �ļ�������
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * ��������Ϣ������־��
     *
     * @param contant ������Ϣ��
     */
    public synchronized void writeLog(String contant) {
        // �ļ�����
        byte[] bt = {};

        // �������
        int ioFlag = 0;

        // �ļ�����
        String fcontant = "";

        // �ļ���ʼ��
        File fl = new File(fileName);

        try {
            // ���ļ������ڣ��򴴽�
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
     * ���ô˷�������Ϊ���ԡ�
     */
    public void isTest() {
        this.writeLog("���ԣ�");
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
     * �����ݿ��м�¼ҵ������ݿ�����Ĵ�����־
     *
     * @param userData �ܿ�����
     * @param action ��������
     * @param descObj ������������
     * @param result �������
     */
    public static void makeLog(UserData userData, String action,
                               Object descObj, String result) {
        makeLog(userData, action, DataTranslate.translate2String(descObj),
                result);
    }

    /**
     * �����ݿ��м�¼ҵ������ݿ�����Ĵ�����־
     *
     * @param userData �ܿ�����
     * @param action ��������
     * @param desc ��������
     * @param result �������
     */
    public static void makeLog(UserData userData, String action, String desc,
                               String result) {
        try {
            // ����LogData
            LogData logData = new LogData(userData);
            Date date = new Date(System.currentTimeMillis());

            if ((result != null) && !(result.equals(""))) {
                // ��¼����ʱ��
                logData.setJssj(date);
            }

            // ��¼����ʱ��
            logData.setYxsj(date);
            logData.setRzjbdm(logData.LEVEL_4);
            logData.setCzlx(action);
            logData.setCzms(desc);
            logData.setCzjg(result);

            // ͨ��LogBeanClient����ϢBean������Ϣ����¼��־
            LogBeanClient.thtflog(logData);
        } catch (Exception ignore) {
            ignore.printStackTrace();
        }
    }
}
