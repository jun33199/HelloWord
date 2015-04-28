package com.lscdz.qysds.common.service.qysds.xml;

/**
 * <p>Title: 文件内容解密</p>
 * <p>Description: 采用DES算法,通过本地用户登录密码将制定加密文件内容解密</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: 北京四一安信科技有限公司</p>
 * @author 涂明
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
    //定义 key解密算法
    private String keyAlgorithm = "DES";
    //定义 解密算法
    private String Algorithm = "DES/ECB/PKCS5Padding";
    //定义 Base64算法编码器
    private BASE64Encoder encoder = new BASE64Encoder();
    //定义 Base64算法解码器
    private BASE64Decoder decoder = new BASE64Decoder();

    public XMLDecrypt()
    {
        //添加新安全算法,如果用JCE就要把它添加进去
        Security.addProvider(new com.sun.crypto.provider.SunJCE());
    }

    /**
     * 将指定文件的读入流根据给定的密码进行解密
     * @param password 解密密码
     * @param is 需要解密的文件流
     * @return 返回解密后的文件字符串
     */
    public String Decrypte(String password, InputStream is)
    {
        String returnStr = new String();
        try
        {
            //将传入的密码串按UTF-8方式编码后转译成Base64编码
            String pwd64 = encoder.encode(password.getBytes("UTF-8"));
            //获取加密密钥
            SecretKey deskey = createKey(pwd64);
            byte[] cipherByte = decoder.decodeBuffer(StringUtil.readToBuffer(is));
            //根据解密算法初始化解密类
            Cipher c1 = Cipher.getInstance(Algorithm);
            //设置工作方式 -- 此处为解密
            c1.init(Cipher.DECRYPT_MODE, deskey);
            //解密生成解密信息
            byte[] clearByte = c1.doFinal(cipherByte);
//            System.out.println("解密后的信息:" + (new String(clearByte, "UTF-8")));
            //指定返回的Sting为UTF-8编码方式
            returnStr = new String(clearByte, "UTF-8");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return returnStr;
    }

    /**
     * 根据本地用户登录密码通过DES算法生成解密密钥
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