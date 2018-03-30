package com.yrtech.wx.capp.framework.core.net;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * IP工具类
 * 
 * @Package: com.yrtech.wx.capp.framework.core.net
 * @ClassName: IPUtil
 * @author wanghui
 * @date 2012-6-14 下午5:30:34
 * 
 *           修改日期 修改人 修改目的
 * 
 */
public class IPUtil
{
    private static boolean isInner(long userIp, long begin, long end)
    {
        boolean isInner = (userIp >= begin) && (userIp <= end);
        ;
        return isInner;
    }

    /**
     * 
     * 判断是否是内网IP
     * 
     * @author wanghui
     * @date 2012-6-14 下午5:31:14
     * @version: V1.0
     * 
     * @param ipAddress
     *            校验的IP地址
     * @return true-是内网地址， false-不是内网地址
     */
    public static boolean isInnerIP(String ipAddress)
    {
        boolean isInnerIp = false;

        long ipNum = getIpNum(ipAddress);

        /**
         * 私有IP：A类 10.0.0.0-10.255.255.255 B类 172.16.0.0-172.31.255.255 C类
         * 192.168.0.0-192.168.255.255 当然，还有127这个网段是环回地址
         **/
        long aBegin = getIpNum("10.0.0.0");
        long aEnd = getIpNum("10.255.255.255");

        long bBegin = getIpNum("172.16.0.0");
        long bEnd = getIpNum("172.31.255.255");

        long cBegin = getIpNum("192.168.0.0");
        long cEnd = getIpNum("192.168.255.255");
        isInnerIp = isInner(ipNum, aBegin, aEnd) || isInner(ipNum, bBegin, bEnd) || isInner(ipNum, cBegin, cEnd)
                || ipAddress.equals("127.0.0.1");

        return isInnerIp;
    }

    /**
     * 
     * 获取IP数
     * 
     * @author wanghui
     * @date 2012-6-14 下午5:32:36
     * @version: V1.0
     * 
     * @param ipAddress
     *            IP地址
     * @return
     */
    private static long getIpNum(String ipAddress)
    {
        String[] ip = ipAddress.split("\\.");
        long a = Integer.parseInt(ip[0]);
        long b = Integer.parseInt(ip[1]);
        long c = Integer.parseInt(ip[2]);
        long d = Integer.parseInt(ip[3]);

        long ipNum = a * 256 * 256 * 256 + b * 256 * 256 + c * 256 + d;
        return ipNum;
    }

    /**
     * 
     * 获取客户端真实IP地址
     * 
     * @author wanghui
     * @date 2012-6-14 下午5:33:46
     * @version: V1.0
     * 
     * @param request
     *            HttpServletRequest对象
     * @return IP地址
     */
    public static String getClinetIpByReq(HttpServletRequest request)
    {
        // 获取客户端ip地址
        String clientIp = request.getHeader("x-forwarded-for");

        if (clientIp == null || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp))
        {
            clientIp = request.getHeader("Proxy-Client-IP");
        }
        if (clientIp == null || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp))
        {
            clientIp = request.getHeader("WL-Proxy-Client-IP");
        }
        if (clientIp == null || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp))
        {
            clientIp = request.getRemoteAddr();
        }
        /*
         * 对于获取到多ip的情况下，找到公网ip.
         */
        String sIP = null;
        if (clientIp != null && clientIp.indexOf("unknown") == -1 && clientIp.indexOf(",") > 0)
        {
            String[] ipsz = clientIp.split(",");
            for (int i = 0; i < ipsz.length; i++)
            {
                if (!IPUtil.isInnerIP(ipsz[i].trim()))
                {
                    sIP = ipsz[i].trim();
                    break;
                }
            }
            /*
             * 如果多ip都是内网ip，则取第一个ip.
             */
            if (null == sIP)
            {
                sIP = ipsz[0].trim();
            }
            clientIp = sIP;
        }
        if (clientIp != null && clientIp.indexOf("unknown") != -1)
        {
            clientIp = clientIp.replaceAll("unknown,", "");
            clientIp = clientIp.trim();
        }
        /*
         * logger.info("x-forwarded-for = [" +
         * StringUtil.nvl(request.getHeader("x-forwarded-for")) + "]");
         * logger.info("x-real-ip = [" +
         * StringUtil.nvl(request.getHeader("x-real-ip")) + "]");
         * logger.info("clientIp = [" + StringUtil.nvl(clientIp) + "]");
         */
        /*
         * logger.debug("x-forwarded-for = [" +
         * StringUtil.nvl(request.getHeader("x-forwarded-for")) + "]");
         * logger.debug("x-real-ip = [" +
         * StringUtil.nvl(request.getHeader("x-real-ip")) + "]");
         * logger.debug("clientIp = [" + StringUtil.nvl(clientIp) + "]");
         */
        /**
         * 防止IP值取不到的情况，取一个默认的IP值 added by liuxw
         */
        if ("".equals(clientIp) || null == clientIp)
        {
            clientIp = "127.0.0.1";
        }
        return clientIp;
    }

}
