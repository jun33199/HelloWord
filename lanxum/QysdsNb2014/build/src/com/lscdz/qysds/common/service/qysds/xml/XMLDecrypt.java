package com.lscdz.qysds.common.service.qysds.xml;

/**
 * <p>Title: �ļ����ݽ���</p>
 * <p>Description: ����DES�㷨,ͨ�������û���¼���뽫�ƶ������ļ����ݽ���</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: ������һ���ſƼ����޹�˾</p>
 * @author Ϳ��
 * @version 1.0
 */

import java.security.Security;
import javax.crypto.SecretKey;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.SecretKeyFactory;
import sun.misc.BASE64Decoder;
import javax.crypto.Cipher;
import com.lscdz.qysds.common.util.StringUtil;
import java.io.InputStream;
import sun.misc.BASE64Encoder;


public class XMLDecrypt
{
    //���� key�����㷨
    private String keyAlgorithm = "DES";
    //���� �����㷨
    private String Algorithm = "DES/ECB/PKCS5Padding";
    //���� Base64�㷨������
    private BASE64Encoder encoder = new BASE64Encoder();
    //���� Base64�㷨������
    private BASE64Decoder decoder = new BASE64Decoder();

    public XMLDecrypt()
    {
        //����°�ȫ�㷨,�����JCE��Ҫ������ӽ�ȥ
        Security.addProvider(new com.sun.crypto.provider.SunJCE());
    }

    /**
     * ��ָ���ļ��Ķ��������ݸ�����������н���
     * @param password ��������
     * @param is ��Ҫ���ܵ��ļ���
     * @return ���ؽ��ܺ���ļ��ַ���
     */
    public String Decrypte(String password, InputStream is)
    {
        String returnStr = new String();
        try
        {
            //����������봮��UTF-8��ʽ�����ת���Base64����
            String pwd64 = encoder.encode(password.getBytes("UTF-8"));
            //��ȡ������Կ
            SecretKey deskey = createKey(pwd64);
            byte[] cipherByte = decoder.decodeBuffer(StringUtil.readToBuffer(is));
            //���ݽ����㷨��ʼ��������
            Cipher c1 = Cipher.getInstance(Algorithm);
            //���ù�����ʽ -- �˴�Ϊ����
            c1.init(Cipher.DECRYPT_MODE, deskey);
            //�������ɽ�����Ϣ
            byte[] clearByte = c1.doFinal(cipherByte);
//            System.out.println("���ܺ����Ϣ:" + (new String(clearByte, "UTF-8")));
            //ָ�����ص�StingΪUTF-8���뷽ʽ
            returnStr = new String(clearByte, "UTF-8");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return returnStr;
    }

    /**
     * ���ݱ����û���¼����ͨ��DES�㷨���ɽ�����Կ
     * @param password
     * @return �������ɵ���Կ
     */
    private SecretKey createKey(String password)
    {
        SecretKey sk = null;
        try
        {
            //����������밴Base64����������
            byte[] key = decoder.decodeBuffer(password);
            //������Կ
            DESKeySpec desKS = new DESKeySpec(key);
            SecretKeyFactory skf = SecretKeyFactory.getInstance(keyAlgorithm);
            sk = skf.generateSecret(desKS);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return sk;
    }

}