package com.creationstar.bjtax.qsgl.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * <p>Title: 标准ZIP压缩工具</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: </p>
 * @author Ha Zhengze
 * @version 1.0
 */
public class ZipUtils {

    /**
     * 压缩字符串为 String
     * 储存可以使用new sun.misc.BASE64Encoder().encodeBuffer(byte[] b)方法
     * 保存为字符串
     *
     * @param str 压缩前的文本
     * @return
     */
    public static final String compress(String str) {
        //System.out.println("压缩前字符串：" + str);
        String rtnStr = new sun.misc.BASE64Encoder().encodeBuffer(ZipUtils.
                compressSub(str));
        //System.out.println("压缩后字符串：" + rtnStr);
        return rtnStr;
    }

    /**
     * 将压缩后的 String 数据解压缩
     *
     * @param compressed 压缩后的 byte[] 数据
     * @return 解压后的字符串
     */
    public static final String decompress(String byteStr) throws IOException {
        //System.out.println("解压缩前字符串：" + byteStr);
        String rtnStr = ZipUtils.decompressSub(new sun.misc.BASE64Decoder().
                                               decodeBuffer(byteStr));
        //System.out.println("解压缩后字符串：" + rtnStr);
        return rtnStr;
    }


    /**
     * 压缩字符串为 byte[]
     * 储存可以使用new sun.misc.BASE64Encoder().encodeBuffer(byte[] b)方法
     * 保存为字符串
     *
     * @param str 压缩前的文本
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
     * 将压缩后的 byte[] 数据解压缩
     *
     * @param compressed 压缩后的 byte[] 数据
     * @return 解压后的字符串
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
