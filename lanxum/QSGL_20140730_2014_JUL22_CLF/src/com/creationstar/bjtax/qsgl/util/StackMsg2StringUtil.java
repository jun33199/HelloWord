/*
 * <p>Title: 北京地税核心征管系统－－票证管理</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.creationstar.bjtax.qsgl.util;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;

import com.ttsoft.common.util.Debug;


/**
 * <p>Title: 北京地税核心征管系统－－票证管理</p>
 * <p>Description: 将异常堆栈信息连接成一字符串，并记录日志</p>
 * @author 开发五组－－李燕军
 * @version 1.1
 */
public class StackMsg2StringUtil {
    private String msg = "";
    StringWriter sw = new StringWriter();
    private PrintWriter pw = new PrintWriter(sw);

    /**
     * 构造函数。
     *
     * @param ex    原始异常。
     * @param cap   存放异常信息的数据库字段容量限制,如oracle db: VARCHAR2(600),则cap = 600。
     */
    public StackMsg2StringUtil(Exception ex, int cap) {
        this(ex, null, cap);
    }

    /**
     * 构造函数:可以合并外在的信息，以便一同记入日志表。
     *
     * @param ex        原始异常。
     * @param extra     额外信息。
     * @param cap       存放异常信息的数据库字段容量限制,如oracle database: VARCHAR2(600),则cap = 600。
     */
    public StackMsg2StringUtil(Exception ex, String extra, int cap) {
        if (extra == null) {
            extra = "";
        }

        if (!extra.equals("")) {
            extra += "\n";
        }

        ex.printStackTrace(pw);
        msg = sw.toString();
        msg = extra + msg;

        /* handle complex encoding. */
        byte[] msgBytes = msg.getBytes();

        if (msgBytes.length > cap) {
            byte[] msgBytes2 = new byte[cap];
            System.arraycopy(msgBytes, 0, msgBytes2, 0, cap);

            InputStreamReader isr = new InputStreamReader(new
                    ByteArrayInputStream(
                            msgBytes2));
            char[] target = new char[cap];

            try {
                int num = isr.read(target);
                isr.close();
            } catch (Exception exx) {
                // won't happen when we are still alive!
                exx.printStackTrace();
            }

            /* 去除char[] target中的空char */
            int target2cap = 0;

            for (int i = 0; i < target.length; i++) {
                if (target[i] == 0) {
                    target2cap = i;

                    break;
                }
            }

            /* 若 target2cap == 0, msg中无空char, 即信息中无中文等字符。 */
            if (target2cap == 0) {
                target2cap = cap;
            }

            char[] target2 = new char[target2cap];

            /* 得到最终char[] target2 */
            System.arraycopy(target, 0, target2, 0, target2cap);

            /* 最终获得的包含附加信息的堆栈信息字符串 */
            msg = new String(target2);
            Debug.out("堆栈信息字节数目【" + msg.getBytes().length + "】");
        }
    }

    /**
     * 获取异常堆栈信息字符串。
     *
     * @return msg 异常堆栈信息。
     */
    public String getStackMsg() {
        return msg;
    }

    /**
     * 获取异常堆栈信息的简短描述。
     *
     * @return msg 异常堆栈信息。
     */
    public String toString() {
        return msg;
    }
}
