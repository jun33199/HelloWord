package com.lscdz.qysds.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;


/**
 * @author Administrator
 * 这里需要特别注意的是:
 * 1、如果你想把压缩后的byte[]保存到字符串中，不能直接使用new String(byte)或者byte.toString()，
 * 因为这样转换之后容量是增加的。同样的道理，如果是字符串的话，
 * 也不能直接使用new String().getBytes()获取byte[]传入到decompress中进行解压缩。
　　2、如果保存压缩后的二进制，可以使用new sun.misc.BASE64Encoder().encodeBuffer(byte[] b)将其转换为字符串。
   同样解压缩的时候首先使用new BASE64Decoder().decodeBuffer 方法将字符串转换为字节，然后解压就可以了。
 *
 */
public class ZipUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			String test = "同样解压缩的时候首先使用new BASE64Decoder().decodeBuffer 方法将字符串转换为字节，然后解压就可以了。";
			System.out.println(test);
			byte[] compressed = ZipUtil.compress(test);
			System.out.println(compressed);
			System.out.println(compressed.toString());
			String base64 = ZipUtil.compressTobase64(compressed);
			System.out.println(base64);
			compressed = ZipUtil.base64Tocompress(base64);
			System.out.println(compressed);
			System.out.println(compressed.toString());
			String testre = ZipUtil.decompress(compressed);
			System.out.println(testre);
		} catch (Exception e) {
			// TODO: handle exception
		}		
		

	}
	
	/** 
     * 压缩字符串为 byte[] 
     * 储存可以使用new sun.misc.BASE64Encoder().encodeBuffer(byte[] b)方法 
     * 保存为字符串 
     * 
     * @param str 压缩前的文本 
     * @return 
     */ 
    public static final byte[] compress(String str) throws Exception{ 
        if(str == null) 
            return null; 
        
        byte[] compressed = null; 
        ByteArrayOutputStream out = null; 
        ZipOutputStream zout = null; 
        
        try { 
            out = new ByteArrayOutputStream(); 
            zout = new ZipOutputStream(out); 
            zout.putNextEntry(new ZipEntry("0")); 
            zout.write(str.getBytes()); 
            zout.closeEntry(); 
            compressed = out.toByteArray(); 
        } catch(Exception e) { 
            compressed = null; 
        } finally { 
            if(zout != null) { 
                try{zout.close();} catch(IOException e){} 
            } 
            if(out != null) { 
                try{out.close();} catch(IOException e){} 
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
    @SuppressWarnings("unused")
	public static final String decompress(byte[] compressed) throws Exception{ 
        if(compressed == null) 
            return ""; 
        
        ByteArrayOutputStream out = null; 
        ByteArrayInputStream in = null; 
        ZipInputStream zin = null; 
        String decompressed = ""; 
        try { 
            out = new ByteArrayOutputStream(); 
            in = new ByteArrayInputStream(compressed); 
            zin = new ZipInputStream(in); 
            ZipEntry entry = zin.getNextEntry(); 
            byte[] buffer = new byte[1024]; 
            int offset = -1; 
            while((offset = zin.read(buffer)) != -1) { 
                out.write(buffer, 0, offset); 
            } 
            decompressed = out.toString(); 
        } catch (Exception e) { 
            decompressed = ""; 
        } finally { 
            if(zin != null) { 
                try {zin.close();} catch(IOException e) {} 
            } 
            if(in != null) { 
                try {in.close();} catch(IOException e) {} 
            } 
            if(out != null) { 
                try {out.close();} catch(IOException e) {} 
            } 
        } 
        return decompressed; 
    }
    
    /** 
     * 将压缩后的 byte[]流数据转为base64文本 
     * 
     * @param compressed 压缩后的 byte[] 数据 
     * @return 转换后的base64文本 
     */ 
    public static final String compressTobase64(byte[] compressed) throws Exception{ 
    	
    	if(compressed == null) 
            return ""; 
    	
    	String compressString = "";
    	try { 
    		compressString = new sun.misc.BASE64Encoder().encodeBuffer(compressed);
    	} catch (Exception e) { 
    		compressString = ""; 
        }
    	 return compressString; 
    }
    /** 
     * 将 base64文本 转为 压缩后的 byte[]流数据
     * 
     * @param str byte[] 数据 转换后的base64文本
     * @return  压缩后的 byte[] 数据
     */ 
    public static final byte[] base64Tocompress(String str) throws Exception{ 
    	if(str == null) 
            return null; 
    	byte[] compressed = null; 
    	try { 
    		compressed = new sun.misc.BASE64Decoder().decodeBuffer(str);
    	} catch (Exception e) { 
    		compressed = null; 
        }
    	 return compressed; 
    }


}
