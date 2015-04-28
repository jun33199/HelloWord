package com.lscdz.qysds.common.service.qysds.xml;

/**
 * <p>Title: 文件内容加密</p>
 * <p>Description: 采用DES算法,通过本地用户登录密码将指定文件内容加密</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: 北京四一安信科技有限公司</p>
 * @author 涂明
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
    //定义 key加密算法
    private String keyAlgorithm = "DES";
    //定义 加密算法
    private String Algorithm = "DES/ECB/PKCS5Padding";
    //定义 Base64算法编码器
    private BASE64Encoder encoder = new BASE64Encoder();
    //定义 Base64算法解码器
    private BASE64Decoder decoder = new BASE64Decoder();


    public XMLEncrypt()
    {
        //添加新安全算法,如果用JCE就要把它添加进去
        Security.addProvider(new com.sun.crypto.provider.SunJCE());
    }

    /**
     * 将指定的字符串根据给定的密码进行加密
     * @param password 加密密码
     * @param encryptInfo 需要加密的信息
     * @return 加密后的byte[]
     */
    public byte[] Encrypt(String password, String encryptInfo)
    {
        byte[] returnByte = null;
        try
        {
            //将传入的密码串按UTF-8方式编码后转译成Base64编码
            String pwd64 = encoder.encode(password.getBytes("UTF-8"));
            //获取加密密钥
            SecretKey deskey = createKey(pwd64);
//            System.out.println("加密前的信息:" + myinfo);
            //根据加密算法初始化加密类
            Cipher c1 = Cipher.getInstance(Algorithm);
            //设置工作方式 -- 此处为加密
            c1.init(Cipher.ENCRYPT_MODE, deskey);
            //加密生成加密信息
            byte[] cipherByte = c1.doFinal(encryptInfo.getBytes("UTF-8"));
            //将加密后的信息通过Base64算法进行编码
            String str = encoder.encode(cipherByte);
//            System.out.println("加密后的信息：" + cipherByte);
            //指定返回的byte[]为UTF-8编码方式
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
     * 根据本地用户登录密码通过DES算法生成加密密钥
     * @param password
     * @return 返回生成的密钥
     */
    private SecretKey createKey(String password)
    {
        SecretKey sk = null;
        try
        {
            //将传入的密码按Base64编码规则解码
            byte[] key = decoder.decodeBuffer(password);
            //生成密钥
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