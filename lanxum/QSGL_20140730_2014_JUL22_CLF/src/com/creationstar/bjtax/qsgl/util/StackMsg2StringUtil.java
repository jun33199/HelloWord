/*
 * <p>Title: ������˰��������ϵͳ����Ʊ֤����</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.creationstar.bjtax.qsgl.util;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;

import com.ttsoft.common.util.Debug;


/**
 * <p>Title: ������˰��������ϵͳ����Ʊ֤����</p>
 * <p>Description: ���쳣��ջ��Ϣ���ӳ�һ�ַ���������¼��־</p>
 * @author �������飭�������
 * @version 1.1
 */
public class StackMsg2StringUtil {
    private String msg = "";
    StringWriter sw = new StringWriter();
    private PrintWriter pw = new PrintWriter(sw);

    /**
     * ���캯����
     *
     * @param ex    ԭʼ�쳣��
     * @param cap   ����쳣��Ϣ�����ݿ��ֶ���������,��oracle db: VARCHAR2(600),��cap = 600��
     */
    public StackMsg2StringUtil(Exception ex, int cap) {
        this(ex, null, cap);
    }

    /**
     * ���캯��:���Ժϲ����ڵ���Ϣ���Ա�һͬ������־��
     *
     * @param ex        ԭʼ�쳣��
     * @param extra     ������Ϣ��
     * @param cap       ����쳣��Ϣ�����ݿ��ֶ���������,��oracle database: VARCHAR2(600),��cap = 600��
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

            /* ȥ��char[] target�еĿ�char */
            int target2cap = 0;

            for (int i = 0; i < target.length; i++) {
                if (target[i] == 0) {
                    target2cap = i;

                    break;
                }
            }

            /* �� target2cap == 0, msg���޿�char, ����Ϣ�������ĵ��ַ��� */
            if (target2cap == 0) {
                target2cap = cap;
            }

            char[] target2 = new char[target2cap];

            /* �õ�����char[] target2 */
            System.arraycopy(target, 0, target2, 0, target2cap);

            /* ���ջ�õİ���������Ϣ�Ķ�ջ��Ϣ�ַ��� */
            msg = new String(target2);
            Debug.out("��ջ��Ϣ�ֽ���Ŀ��" + msg.getBytes().length + "��");
        }
    }

    /**
     * ��ȡ�쳣��ջ��Ϣ�ַ�����
     *
     * @return msg �쳣��ջ��Ϣ��
     */
    public String getStackMsg() {
        return msg;
    }

    /**
     * ��ȡ�쳣��ջ��Ϣ�ļ��������
     *
     * @return msg �쳣��ջ��Ϣ��
     */
    public String toString() {
        return msg;
    }
}
