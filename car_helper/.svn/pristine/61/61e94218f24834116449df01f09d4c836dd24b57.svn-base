package com.yrtech.wx.capp.framework.core.security;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import com.yrtech.wx.capp.framework.core.log.Msg;

/**
 * 
 * 提供MD5的加密方式，主要用于用户密码的保存，不可逆 加密结果是32位字符
 * 
 * @Package: com.market.portal.security
 * @ClassName: MD5
 * @author wanghui
 * @date 2012-6-15 下午2:08:20
 * @version: V1.0
 * 
 *           修改日期 修改人 修改目的
 * 
 */
@SuppressWarnings("unused")
public class MD5
{

    private static String stringify(byte buf[])
    {
        StringBuffer sb = new StringBuffer(2 * buf.length);
        for (int i = 0; i < buf.length; i++)
        {
            int h = (buf[i] & 0xf0) >> 4;
            int l = buf[i] & 0xf;
            sb.append(new Character((char) (h <= 9 ? 48 + h : (97 + h) - 10)));
            sb.append(new Character((char) (l <= 9 ? 48 + l : (97 + l) - 10)));
        }

        return sb.toString();
    }

    private final int F(int x, int y, int z)
    {
        return x & y | ~x & z;
    }

    private final int G(int x, int y, int z)
    {
        return x & z | y & ~z;
    }

    private final int H(int x, int y, int z)
    {
        return x ^ y ^ z;
    }

    private final int I(int x, int y, int z)
    {
        return y ^ (x | ~z);
    }

    private final int rotate_left(int x, int n)
    {
        return x << n | x >>> 32 - n;
    }

    private final int FF(int a, int b, int c, int d, int x, int s, int ac)
    {
        a += F(b, c, d) + x + ac;
        a = rotate_left(a, s);
        a += b;
        return a;
    }

    private final int GG(int a, int b, int c, int d, int x, int s, int ac)
    {
        a += G(b, c, d) + x + ac;
        a = rotate_left(a, s);
        a += b;
        return a;
    }

    private final int HH(int a, int b, int c, int d, int x, int s, int ac)
    {
        a += H(b, c, d) + x + ac;
        a = rotate_left(a, s);
        a += b;
        return a;
    }

    private final int II(int a, int b, int c, int d, int x, int s, int ac)
    {
        a += I(b, c, d) + x + ac;
        a = rotate_left(a, s);
        a += b;
        return a;
    }

    private final void decode(int output[], byte input[], int off, int len)
    {
        int i = 0;
        for (int j = 0; j < len; j += 4)
        {
            output[i] = input[off + j] & 0xff | (input[off + j + 1] & 0xff) << 8 | (input[off + j + 2] & 0xff) << 16
                    | (input[off + j + 3] & 0xff) << 24;
            i++;
        }

    }

    private final void transform(byte block[], int offset)
    {
        int a = state[0];
        int b = state[1];
        int c = state[2];
        int d = state[3];
        int x[] = new int[16];
        decode(x, block, offset, 64);
        a = FF(a, b, c, d, x[0], 7, 0xd76aa478);
        d = FF(d, a, b, c, x[1], 12, 0xe8c7b756);
        c = FF(c, d, a, b, x[2], 17, 0x242070db);
        b = FF(b, c, d, a, x[3], 22, 0xc1bdceee);
        a = FF(a, b, c, d, x[4], 7, 0xf57c0faf);
        d = FF(d, a, b, c, x[5], 12, 0x4787c62a);
        c = FF(c, d, a, b, x[6], 17, 0xa8304613);
        b = FF(b, c, d, a, x[7], 22, 0xfd469501);
        a = FF(a, b, c, d, x[8], 7, 0x698098d8);
        d = FF(d, a, b, c, x[9], 12, 0x8b44f7af);
        c = FF(c, d, a, b, x[10], 17, -42063);
        b = FF(b, c, d, a, x[11], 22, 0x895cd7be);
        a = FF(a, b, c, d, x[12], 7, 0x6b901122);
        d = FF(d, a, b, c, x[13], 12, 0xfd987193);
        c = FF(c, d, a, b, x[14], 17, 0xa679438e);
        b = FF(b, c, d, a, x[15], 22, 0x49b40821);
        a = GG(a, b, c, d, x[1], 5, 0xf61e2562);
        d = GG(d, a, b, c, x[6], 9, 0xc040b340);
        c = GG(c, d, a, b, x[11], 14, 0x265e5a51);
        b = GG(b, c, d, a, x[0], 20, 0xe9b6c7aa);
        a = GG(a, b, c, d, x[5], 5, 0xd62f105d);
        d = GG(d, a, b, c, x[10], 9, 0x2441453);
        c = GG(c, d, a, b, x[15], 14, 0xd8a1e681);
        b = GG(b, c, d, a, x[4], 20, 0xe7d3fbc8);
        a = GG(a, b, c, d, x[9], 5, 0x21e1cde6);
        d = GG(d, a, b, c, x[14], 9, 0xc33707d6);
        c = GG(c, d, a, b, x[3], 14, 0xf4d50d87);
        b = GG(b, c, d, a, x[8], 20, 0x455a14ed);
        a = GG(a, b, c, d, x[13], 5, 0xa9e3e905);
        d = GG(d, a, b, c, x[2], 9, 0xfcefa3f8);
        c = GG(c, d, a, b, x[7], 14, 0x676f02d9);
        b = GG(b, c, d, a, x[12], 20, 0x8d2a4c8a);
        a = HH(a, b, c, d, x[5], 4, 0xfffa3942);
        d = HH(d, a, b, c, x[8], 11, 0x8771f681);
        c = HH(c, d, a, b, x[11], 16, 0x6d9d6122);
        b = HH(b, c, d, a, x[14], 23, 0xfde5380c);
        a = HH(a, b, c, d, x[1], 4, 0xa4beea44);
        d = HH(d, a, b, c, x[4], 11, 0x4bdecfa9);
        c = HH(c, d, a, b, x[7], 16, 0xf6bb4b60);
        b = HH(b, c, d, a, x[10], 23, 0xbebfbc70);
        a = HH(a, b, c, d, x[13], 4, 0x289b7ec6);
        d = HH(d, a, b, c, x[0], 11, 0xeaa127fa);
        c = HH(c, d, a, b, x[3], 16, 0xd4ef3085);
        b = HH(b, c, d, a, x[6], 23, 0x4881d05);
        a = HH(a, b, c, d, x[9], 4, 0xd9d4d039);
        d = HH(d, a, b, c, x[12], 11, 0xe6db99e5);
        c = HH(c, d, a, b, x[15], 16, 0x1fa27cf8);
        b = HH(b, c, d, a, x[2], 23, 0xc4ac5665);
        a = II(a, b, c, d, x[0], 6, 0xf4292244);
        d = II(d, a, b, c, x[7], 10, 0x432aff97);
        c = II(c, d, a, b, x[14], 15, 0xab9423a7);
        b = II(b, c, d, a, x[5], 21, 0xfc93a039);
        a = II(a, b, c, d, x[12], 6, 0x655b59c3);
        d = II(d, a, b, c, x[3], 10, 0x8f0ccc92);
        c = II(c, d, a, b, x[10], 15, 0xffeff47d);
        b = II(b, c, d, a, x[1], 21, 0x85845dd1);
        a = II(a, b, c, d, x[8], 6, 0x6fa87e4f);
        d = II(d, a, b, c, x[15], 10, 0xfe2ce6e0);
        c = II(c, d, a, b, x[6], 15, 0xa3014314);
        b = II(b, c, d, a, x[13], 21, 0x4e0811a1);
        a = II(a, b, c, d, x[4], 6, 0xf7537e82);
        d = II(d, a, b, c, x[11], 10, 0xbd3af235);
        c = II(c, d, a, b, x[2], 15, 0x2ad7d2bb);
        b = II(b, c, d, a, x[9], 21, 0xeb86d391);
        state[0] += a;
        state[1] += b;
        state[2] += c;
        state[3] += d;
    }

    private final void update(byte input[], int len)
    {
        int index = (int) (count >> 3) & 0x3f;
        count += len << 3;
        int partLen = 64 - index;
        int i = 0;
        if (len >= partLen)
        {
            System.arraycopy(input, 0, buffer, index, partLen);
            transform(buffer, 0);
            for (i = partLen; i + 63 < len; i += 64)
                transform(input, i);

            index = 0;
        }
        else
        {
            i = 0;
        }
        System.arraycopy(input, i, buffer, index, len - i);
    }

    private byte[] end()
    {
        byte bits[] = new byte[8];
        for (int i = 0; i < 8; i++)
            bits[i] = (byte) (int) (count >>> i * 8 & 255L);

        int index = (int) (count >> 3) & 0x3f;
        int padlen = index >= 56 ? 120 - index : 56 - index;
        update(padding, padlen);
        update(bits, 8);
        return encode(state, 16);
    }

    private byte[] encode(int input[], int len)
    {
        byte output[] = new byte[len];
        int i = 0;
        for (int j = 0; j < len; j += 4)
        {
            output[j] = (byte) (input[i] & 0xff);
            output[j + 1] = (byte) (input[i] >> 8 & 0xff);
            output[j + 2] = (byte) (input[i] >> 16 & 0xff);
            output[j + 3] = (byte) (input[i] >> 24 & 0xff);
            i++;
        }

        return output;
    }

    /**
     * 
     * 获取摘要信息
     * 
     * @author wanghui
     * @date 2012-6-15 下午2:34:30
     * @version: V1.0
     * 
     * @return 摘要信息
     * @throws IOException
     */
    public byte[] getDigest() throws IOException
    {
        byte buffer[] = new byte[1024];
        int got = -1;
        if (digest != null)
            return digest;
        while ((got = in.read(buffer)) > 0)
            update(buffer, got);
        digest = end();
        return digest;
    }

    public byte[] processString()
    {
        if (!stringp)
            throw new RuntimeException(getClass().getName() + "[processString]" + " not a string.");
        try
        {
            return getDigest();
        }
        catch (IOException ex)
        {
            throw new RuntimeException(getClass().getName() + "[processString]" + ": implementation error.");
        }
    }

    /**
     * 
     * 获得加密字符串
     * 
     * @author wanghui
     * @date 2012-6-15 下午2:32:25
     * @version: V1.0
     * 
     * @return 加密后字符串
     * @throws IOException
     */
    public String getStrDigest() throws IOException
    {
        if (digest == null)
            digest = getDigest();
        return stringify(digest);
    }

    /**
     * 
     * 获取加密字符串
     * 
     * 执行该方法前，需要代码显式调用processString，获取Digest
     * 
     * @author wanghui
     * @date 2012-6-15 下午2:32:46
     * @version: V1.0
     * 
     * @return 加密后字符串
     */
    public String getStringDigest()
    {
        if (digest == null)
            throw new RuntimeException(getClass().getName() + "[getStringDigest]" + ": called before processing.");
        else
            return stringify(digest);
    }

    /**
     * 
     * 构造方法
     * 
     * @author wanghui
     * @date 2012-6-15 下午2:26:56
     * @version: V1.0
     * 
     * @param input
     *            明文字符串
     * @param enc
     *            字符编码
     */
    public MD5(String input, String enc)
    {
        in = null;
        stringp = false;
        state = null;
        count = 0L;
        buffer = null;
        digest = null;
        byte bytes[] = null;
        try
        {
            bytes = input.getBytes(enc);
        }
        catch (UnsupportedEncodingException e)
        {
            throw new RuntimeException("no " + enc + " encoding!!!");
        }
        stringp = true;
        in = new ByteArrayInputStream(bytes);
        state = new int[4];
        buffer = new byte[64];
        count = 0L;
        state[0] = 0x67452301;
        state[1] = 0xefcdab89;
        state[2] = 0x98badcfe;
        state[3] = 0x10325476;
    }

    /**
     * 
     * 构造方法
     * 
     * @author wanghui
     * @date 2012-6-15 下午2:27:43
     * @version: V1.0
     * 
     * @param input
     *            明文字符串 ，默认字符编码为UTF8
     */
    public MD5(String input)
    {
        this(input, "UTF8");
    }

    /**
     * 
     * 构造方法
     * 
     * @author wanghui
     * @date 2012-6-15 下午2:28:29
     * @version: V1.0
     * 
     * @param in
     *            输入流对象
     */
    public MD5(InputStream in)
    {
        this.in = null;
        stringp = false;
        state = null;
        count = 0L;
        buffer = null;
        digest = null;
        stringp = false;
        this.in = in;
        state = new int[4];
        buffer = new byte[64];
        count = 0L;
        state[0] = 0x67452301;
        state[1] = 0xefcdab89;
        state[2] = 0x98badcfe;
        state[3] = 0x10325476;
    }

    private static final int BUFFER_SIZE = 1024;
    private static final int S11 = 7;
    private static final int S12 = 12;
    private static final int S13 = 17;
    private static final int S14 = 22;
    private static final int S21 = 5;
    private static final int S22 = 9;
    private static final int S23 = 14;
    private static final int S24 = 20;
    private static final int S31 = 4;
    private static final int S32 = 11;
    private static final int S33 = 16;
    private static final int S34 = 23;
    private static final int S41 = 6;
    private static final int S42 = 10;
    private static final int S43 = 15;
    private static final int S44 = 21;
    private static byte padding[] = { -128, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0 };
    private InputStream in;
    private boolean stringp;
    private int state[];
    private long count;
    private byte buffer[];
    private byte digest[];

    public static void main(String[] args) throws Exception{
		MD5 md5 = new MD5("880001"+"test_mer"+"icbc"+"3000912312412312"+"90091231"+"test_zhang"+"309987654321234"+"icbc"+"200009123141231"+"hfyrkj");
//		MD5 md5 = new MD5("111111");
		Msg.print(md5.getStrDigest());
	}
    
    //file:///F:/ebank/agree.html?merCode=880001&merName=test_mer&merBankName=工商银行&merBankCardNo=3000912312412312&merCorpCode=90091231&userName=张三&certId=309987654321234&userBankName=工商银行&userBankCardNo=200009123141231&m=06c82a27f480cc69390a90399a09531a
}
