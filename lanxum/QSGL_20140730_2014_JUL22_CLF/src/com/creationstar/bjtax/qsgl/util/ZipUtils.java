package com.creationstar.bjtax.qsgl.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * <p>Title: ��׼ZIPѹ������</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: </p>
 * @author Ha Zhengze
 * @version 1.0
 */
public class ZipUtils {

    /**
     * ѹ���ַ���Ϊ String
     * �������ʹ��new sun.misc.BASE64Encoder().encodeBuffer(byte[] b)����
     * ����Ϊ�ַ���
     *
     * @param str ѹ��ǰ���ı�
     * @return
     */
    public static final String compress(String str) {
        //System.out.println("ѹ��ǰ�ַ�����" + str);
        String rtnStr = new sun.misc.BASE64Encoder().encodeBuffer(ZipUtils.
                compressSub(str));
        //System.out.println("ѹ�����ַ�����" + rtnStr);
        return rtnStr;
    }

    /**
     * ��ѹ����� String ���ݽ�ѹ��
     *
     * @param compressed ѹ����� byte[] ����
     * @return ��ѹ����ַ���
     */
    public static final String decompress(String byteStr) throws IOException {
        //System.out.println("��ѹ��ǰ�ַ�����" + byteStr);
        String rtnStr = ZipUtils.decompressSub(new sun.misc.BASE64Decoder().
                                               decodeBuffer(byteStr));
        //System.out.println("��ѹ�����ַ�����" + rtnStr);
        return rtnStr;
    }


    /**
     * ѹ���ַ���Ϊ byte[]
     * �������ʹ��new sun.misc.BASE64Encoder().encodeBuffer(byte[] b)����
     * ����Ϊ�ַ���
     *
     * @param str ѹ��ǰ���ı�
     * @return
     */
    public static final byte[] compressSub(String str) {
        if (str == null) {
            return null;
        }

        byte[] compressed;
        ByteArrayOutputStream out = null;
        ZipOutputStream zout = null;

        try {
            out = new ByteArrayOutputStream();
            zout = new ZipOutputStream(out);
            zout.putNextEntry(new ZipEntry("0"));
            zout.write(str.getBytes());
            zout.closeEntry();
            compressed = out.toByteArray();
        } catch (IOException e) {
            compressed = null;
        } finally {
            if (zout != null) {
                try {
                    zout.close();
                } catch (IOException e) {}
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {}
            }
        }

        return compressed;
    }

    /**
     * ��ѹ����� byte[] ���ݽ�ѹ��
     *
     * @param compressed ѹ����� byte[] ����
     * @return ��ѹ����ַ���
     */
    public static final String decompressSub(byte[] compressed) {
        if (compressed == null) {
            return null;
        }

        ByteArrayOutputStream out = null;
        ByteArrayInputStream in = null;
        ZipInputStream zin = null;
        String decompressed;
        try {
            out = new ByteArrayOutputStream();
            in = new ByteArrayInputStream(compressed);
            zin = new ZipInputStream(in);
            ZipEntry entry = zin.getNextEntry();
            byte[] buffer = new byte[1024];
            int offset = -1;
            while ((offset = zin.read(buffer)) != -1) {
                out.write(buffer, 0, offset);
            }
            decompressed = out.toString();
        } catch (IOException e) {
            decompressed = null;
        } finally {
            if (zin != null) {
                try {
                    zin.close();
                } catch (IOException e) {}
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {}
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {}
            }
        }

        return decompressed;
    }


    public static void main(String[] args) {
        ZipUtils ziputils = new ZipUtils();
    }
}
