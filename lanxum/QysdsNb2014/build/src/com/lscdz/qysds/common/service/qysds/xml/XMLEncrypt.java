package com.lscdz.qysds.common.service.qysds.xml;

/**
 * <p>Title: �ļ����ݼ���</p>
 * <p>Description: ����DES�㷨,ͨ�������û���¼���뽫ָ���ļ����ݼ���</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: ������һ���ſƼ����޹�˾</p>
 * @author Ϳ��
 * @version 1.0
 */

import java.security.Security;
import javax.crypto.SecretKey;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.SecretKeyFactory;
import javax.crypto.Cipher;
import sun.misc.BASE64Encoder;
import sun.misc.BASE64Decoder;

public class XMLEncrypt
{
    //���� key�����㷨
    private String keyAlgorithm = "DES";
    //���� �����㷨
    private String Algorithm = "DES/ECB/PKCS5Padding";
    //���� Base64�㷨������
    private BASE64Encoder encoder = new BASE64Encoder();
    //���� Base64�㷨������
    private BASE64Decoder decoder = new BASE64Decoder();


    public XMLEncrypt()
    {
        //����°�ȫ�㷨,�����JCE��Ҫ������ӽ�ȥ
        Security.addProvider(new com.sun.crypto.provider.SunJCE());
    }

    /**
     * ��ָ�����ַ������ݸ�����������м���
     * @param password ��������
     * @param encryptInfo ��Ҫ���ܵ���Ϣ
     * @return ���ܺ��byte[]
     */
    public byte[] Encrypt(String password, String encryptInfo)
    {
        byte[] returnByte = null;
        try
        {
            //����������봮��UTF-8��ʽ�����ת���Base64����
            String pwd64 = encoder.encode(password.getBytes("UTF-8"));
            //��ȡ������Կ
            SecretKey deskey = createKey(pwd64);
//            System.out.println("����ǰ����Ϣ:" + myinfo);
            //���ݼ����㷨��ʼ��������
            Cipher c1 = Cipher.getInstance(Algorithm);
            //���ù�����ʽ -- �˴�Ϊ����
            c1.init(Cipher.ENCRYPT_MODE, deskey);
            //�������ɼ�����Ϣ
            byte[] cipherByte = c1.doFinal(encryptInfo.getBytes("UTF-8"));
            //�����ܺ����Ϣͨ��Base64�㷨���б���
            String str = encoder.encode(cipherByte);
//            System.out.println("���ܺ����Ϣ��" + cipherByte);
            //ָ�����ص�byte[]ΪUTF-8���뷽ʽ
            returnByte = str.getBytes("UTF-8");
        }
        catch (java.security.NoSuchAlgorithmException e1)
        {
            e1.printStackTrace();
        }
        catch (javax.crypto.NoSuchPaddingException e2)
        {
            e2.printStackTrace();
        }
        catch (java.lang.Exception e3)
        {
            e3.printStackTrace();
        }
        return returnByte;
    }

    /**
     * ���ݱ����û���¼����ͨ��DES�㷨���ɼ�����Կ
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