package com.yrtech.wx.capp.framework.core.security;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * 
 * DES加密解密工具类
 * 
 * @Package: com.market.portal.security
 * @ClassName: SecretUtil
 * @author wanghui
 * @date 2012-6-15 下午4:50:53
 * @version: V1.0
 * 
 *           修改日期 修改人 修改目的
 * 
 */
public class SecretUtil
{

    /**
     * 默认密钥
     */
    private final static String strDefaultKey = "hefeiyrkj_ysfdes";

    /**
     * 
     * 获取DES密钥
     * 
     * @author wanghui
     * @date 2012-6-15 下午4:50:40
     * @version: V1.0
     * 
     * @param s
     *            密钥字符串
     * @return DES密钥对象
     * @throws Exception
     */
    private static SecretKey getKey(String s) throws Exception
    {
        char[] ss = s.toCharArray();
        StringBuffer sss = new StringBuffer() ;
        for (int i = 0; i < ss.length; i = i + 2)
        {
            sss.append(ss[i]);
        }

        SecretKeyFactory kf = SecretKeyFactory.getInstance("DES");
        DESKeySpec ks = new DESKeySpec(sss.toString().substring(0, 8).getBytes());
        SecretKey kd = kf.generateSecret(ks);
        return kd;
    }

    /**
     * 
     * 加密字符串
     * 
     * @author wanghui
     * @date 2012-6-15 下午4:59:24
     * @version: V1.0
     * 
     * @param key
     *            用于生成密钥的字符串
     * @param input
     *            要加密的字符串
     * @return 加密后的字符串
     */
    public static String getEncryptedString(String key, String input)
    {
        String base64 = "";

        try
        {
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, getKey(key));
            byte[] inputBytes = input.getBytes("UTF8");
            byte[] outputBytes = cipher.doFinal(inputBytes);
            base64 = Base64.encode(outputBytes);
        }
        catch (Exception e)
        {
            base64 = e.getMessage();
        }

        return base64;
    }

    /**
     * 
     * 解密字符串
     * 
     * @author wanghui
     * @date 2012-6-15 下午5:23:21
     * @version: V1.0
     * 
     * @param key
     *            用于生成密钥的字符串
     * @param input
     *            要解密的字符串
     * @return 解密后的字符串
     */
    public static String getDecryptedString(String key, String input)
    {
        String result = null;
        try
        {
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, getKey(key));
            byte[] raw = Base64.decode(input);
            byte[] stringBytes = cipher.doFinal(raw);
            result = new String(stringBytes, "UTF8");
        }
        catch (Exception e)
        {
            result = e.getMessage();
        }
        return result;
    }

    /**
     * 
     * 加密字符串(使用默认密钥)
     * 
     * @author wanghui
     * @date 2012-6-15 下午5:34:08
     * @version: V1.0
     * 
     * @param input
     *            要加密的字符串
     * @return 加密后的字符串
     */
    public static String getEncryptedString(String input)
    {
        return getEncryptedString(strDefaultKey, input);
    }

    /**
     * 
     * 解密字符串(使用默认密钥)
     * 
     * @author wanghui
     * @date 2012-6-15 下午5:36:08
     * @version: V1.0
     * 
     * @param input
     *            要解密的字符串
     * @return 解密后的字符串
     */
    public static String getDecryptedString(String input)
    {
        return getDecryptedString(strDefaultKey, input);
    }
    
    public static void main(String[] args) {
    	System.out.println(getEncryptedString(Base64.encode("cf_yrkj_admin".getBytes())));
//    	System.out.println(getDecryptedString("UWFWobRgE65V6yETYHgKkg=="));
	}

}
