package com.lscdz.qysds.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;


/**
 * @author Administrator
 * ������Ҫ�ر�ע�����:
 * 1����������ѹ�����byte[]���浽�ַ����У�����ֱ��ʹ��new String(byte)����byte.toString()��
 * ��Ϊ����ת��֮�����������ӵġ�ͬ���ĵ���������ַ����Ļ���
 * Ҳ����ֱ��ʹ��new String().getBytes()��ȡbyte[]���뵽decompress�н��н�ѹ����
����2���������ѹ����Ķ����ƣ�����ʹ��new sun.misc.BASE64Encoder().encodeBuffer(byte[] b)����ת��Ϊ�ַ�����
   ͬ����ѹ����ʱ������ʹ��new BASE64Decoder().decodeBuffer �������ַ���ת��Ϊ�ֽڣ�Ȼ���ѹ�Ϳ����ˡ�
 *
 */
public class ZipUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			String test = "ͬ����ѹ����ʱ������ʹ��new BASE64Decoder().decodeBuffer �������ַ���ת��Ϊ�ֽڣ�Ȼ���ѹ�Ϳ����ˡ�";
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
     * ѹ���ַ���Ϊ byte[] 
     * �������ʹ��new sun.misc.BASE64Encoder().encodeBuffer(byte[] b)���� 
     * ����Ϊ�ַ��� 
     * 
     * @param str ѹ��ǰ���ı� 
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
     * ��ѹ����� byte[] ���ݽ�ѹ�� 
     * 
     * @param compressed ѹ����� byte[] ���� 
     * @return ��ѹ����ַ��� 
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
     * ��ѹ����� byte[]������תΪbase64�ı� 
     * 
     * @param compressed ѹ����� byte[] ���� 
     * @return ת�����base64�ı� 
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
     * �� base64�ı� תΪ ѹ����� byte[]������
     * 
     * @param str byte[] ���� ת�����base64�ı�
     * @return  ѹ����� byte[] ����
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
