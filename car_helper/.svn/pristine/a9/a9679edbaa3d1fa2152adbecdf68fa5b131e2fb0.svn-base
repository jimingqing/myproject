package com.yrtech.wx.capp.framework.core.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.pdf.BaseFont;
import com.yrtech.wx.capp.framework.core.security.MD5;

/**
 * 
 * 文件操作工具类
 * 
 * @Package: com.yrtech.wx.capp.framework.core.util
 * @ClassName: FileUtil
 * @author wanghui
 * @date 2012-6-17 下午7:15:59
 * @version: V1.0
 * 
 *           修改日期 修改人 修改目的
 * 
 */
public class FileUtil
{
    private static Logger logger = Logger.getLogger(FileUtil.class);

    /**
     * 
     * 判断文件是否存在
     * 
     * @author wanghui
     * @date 2012-6-4 下午2:08:38
     * @version: V1.0
     * 
     * @param fileName
     *            文件名（全路径）
     * @return true-文件存在， false-文件不存在
     */
    public static boolean checkFileExists(String fileName)
    {
        if (StringUtil.empty(fileName))
        {
            return false;
        }

        File file = new File(fileName);

        if (!file.exists())
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    /**
     * 
     * 判断文件是否存在
     * 
     * @author wanghui
     * @date 2012-6-4 下午2:08:38
     * @version: V1.0
     * 
     * @param file
     *            文件对象
     * @return true-文件存在， false-文件不存在
     */
    public static boolean checkFileExists(File file)
    {
        if (file == null)
        {
            return false;
        }

        if (!file.exists())
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    /**
     * 
     * 根据目录和文件名称创建文件
     * 
     * @author wanghui
     * @date 2012-6-4 下午2:59:36
     * @version: V1.0
     * 
     * @param path
     *            文件目录
     * @param fileName
     *            文件名称
     * @return 创建完的文件对象
     */
    public static File createFile(String path, String fileName)
    {
        if (StringUtil.empty(path) || StringUtil.empty(fileName))
        {
            return null;
        }

        boolean ret = false;

        File file = null;

        try
        {
            // 判断目录是否存在，若不存在，则创建目录
            File pathFile = new File(path);
            if (!pathFile.exists())
            {
                ret = pathFile.mkdirs();
            }

            if (ret) // 目录创建成功
            {
                // 判断文件是否存在，若不存在，则创建文件
                file = new File(path + fileName);
                if (!file.exists())
                {
                    file.createNewFile();
                }
            }
        }
        catch (IOException e)
        {
            logger.error("文件创建失败!" + e.getMessage());
        }

        return file;

    }

    /**
     * 
     * 将字符串写入指定文件
     * 
     * 编码为utf-8
     * 
     * @author wanghui
     * @date 2012-6-4 下午3:01:09
     * @version: V1.0
     * 
     * @param fileName
     *            文件名称(全路径)
     * @param sb
     *            写入字符串
     */
    public static void writeStrToFile(String fileName, String sb)
    {
        try
        {
            File fileTmp = new File(fileName.substring(0, fileName.lastIndexOf("/")));
            if (!fileTmp.exists())
            {
                fileTmp.mkdirs();
            }

            File file = new File(fileName);
            if (!file.exists())
            {
                file.createNewFile();
            }

            FileOutputStream out = new FileOutputStream(file, true);
            out.write(sb.getBytes("utf-8"));
            out.close();
        }
        catch (Exception e)
        {
            logger.error("将字符串写入文件发生异常", e);
        }
    }

    /**
     * 
     * 获取随机的文件名称(MD5加密)
     * 
     * @author wanghui
     * @date 2012-6-4 下午3:05:44
     * @version: V1.0
     * 
     * @param seed
     *            随机种子
     * @return 加密后的文件名称
     */
	public static String getRandomFileName(String seed) {
		byte[] ra = new byte[100];
		new Random().nextBytes(ra);
		StringBuilder build = new StringBuilder("");
		for (int i = 0; i < ra.length; i++) {
			build.append(Byte.valueOf(ra[i]).toString());
		}
		String currentDate = Long.valueOf(new Date().getTime()).toString();
		seed = seed + currentDate + build.toString();
		try {
			return new MD5(seed).getStrDigest();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

    /**
     * 
     * 列出所有当前层的文件和目录
     * 
     * @author wanghui
     * @date 2012-6-4 下午3:08:41
     * @version: V1.0
     * 
     * @param dir
     *            目录名称
     * @return 列出的文件和目录或null
     */
    public static File[] ls(String dir)
    {
        if (StringUtil.empty(dir))
        {
            return null;
        }

        return new File(dir).listFiles();
    }

    /**
     * 
     * 
     * @param dirPath
     *            文件夹路径
     * @param del
     *            存在文件夹是否删除
     */
    /**
     * 
     * 根据需要创建文件夹
     * 
     * @author wanghui
     * @date 2012-6-4 下午3:11:04
     * @version: V1.0
     * 
     * @param dirPath
     *            目录路径
     * @param del
     *            是否删除已有目录
     */
    public static void mkdir(String dirPath, boolean del)
    {
        boolean ret = false;

        File dir = new File(dirPath);

        if (dir.exists())
        {
            if (del)
                dir.delete();
            else
                return;
        }

        ret = dir.mkdirs();

        if (!ret)
        {
            logger.error("目录创建失败!" + dirPath);
        }
    }

    /**
     * 
     * 删除文件和目录
     * 
     * @author wanghui
     * @date 2012-6-4 下午3:54:30
     * @version: V1.0
     * 
     * @param path
     *            目录或文件名称
     * @throws Exception
     */
    public static void rm(String path) throws Exception
    {
        if (logger.isDebugEnabled())
            logger.debug("需要删除的文件: " + path);

        boolean ret = false;

        File file = new File(path);
        if (!file.exists())
        {
            logger.warn("文件<" + path + ">不存在");
            return;
        }

        if (file.isDirectory())
        {
            File[] fileList = file.listFiles();
            if (fileList == null || fileList.length == 0)
            {
                file.delete();
            }
            else
            {
                for (File _file : fileList)
                {
                    rm(_file.getAbsolutePath());
                }
            }
            ret = file.delete();
        }
        else
        {
            ret = file.delete();
        }

        if (!ret)
        {
            logger.error("文件刪除失败!" + path);
        }
    }

    /**
     * 
     * 移动文件
     * 
     * @author wanghui
     * @date 2012-6-4 下午3:58:36
     * @version: V1.0
     * 
     * @param source
     *            源文件
     * @param target
     *            目标文件
     * @param cache
     *            文件缓存大小
     * @throws Exception
     */
    public static void mv(String source, String target, int cache) throws Exception
    {
        if (source.trim().equals(target.trim()))
            return;

        byte[] cached = new byte[cache];

        FileInputStream fromFile = new FileInputStream(source);
        FileOutputStream toFile = new FileOutputStream(target);

        while (fromFile.read(cached) != -1)
        {
            toFile.write(cached);
        }

        toFile.flush();
        toFile.close();
        fromFile.close();

        new File(source).deleteOnExit();
    }

    /**
     * 
     * 把属性文件转换成Map
     * 
     * @author wanghui
     * @date 2012-6-4 下午4:00:10
     * @version: V1.0
     * 
     * @param propertiesFile
     *            属性文件名称
     * @return Map对象
     * @throws Exception
     */
    public static final Map<String, String> getPropertiesMap(String propertiesFile) throws Exception
    {
        Properties properties = new Properties();
        FileInputStream inputStream = new FileInputStream(propertiesFile);
        properties.load(inputStream);
        Map<String, String> map = new HashMap<String, String>();
        Set<Object> keySet = properties.keySet();
        for (Object key : keySet)
        {
            map.put((String) key, properties.getProperty((String) key));
        }

        if (inputStream != null)
        {
            inputStream.close();
            inputStream = null;
        }

        return map;
    }

    /**
     * 
     * 把属性文件转换成Map
     * 
     * @author wanghui
     * @date 2012-6-4 下午4:18:05
     * @version: V1.0
     * 
     * @param clazz
     *            Class
     * @param fileName
     *            属性文件名
     * @return Map对象
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static final Map<String, String> getPropertiesMap(Class clazz, String fileName) throws Exception
    {
        Properties properties = new Properties();
        InputStream inputStream = clazz.getResourceAsStream(fileName);
        if (inputStream == null)
            inputStream = clazz.getClassLoader().getResourceAsStream(fileName);
        properties.load(inputStream);
        Map<String, String> map = new HashMap<String, String>();
        Set<Object> keySet = properties.keySet();
        for (Object key : keySet)
        {
            map.put((String) key, properties.getProperty((String) key));
        }

        if (inputStream != null)
        {
            inputStream.close();
            inputStream = null;
        }

        return map;
    }

    /**
     * 
     * 把属性文件转换成Map
     * 
     * @author wanghui
     * @date 2012-6-4 下午4:19:14
     * @version: V1.0
     * 
     * @param inputStream
     *            InputStream对象
     * @return Map对象
     * @throws Exception
     */
    public static final Map<String, String> getPropertiesMap(InputStream inputStream) throws Exception
    {
        Properties properties = new Properties();
        properties.load(inputStream);
        Map<String, String> map = new HashMap<String, String>();
        Set<Object> keySet = properties.keySet();
        for (Object key : keySet)
        {
            map.put((String) key, properties.getProperty((String) key));
        }
        return map;
    }

    /**
     * 
     * 把文本文件转换成String
     * 
     * @author wanghui
     * @date 2012-6-4 下午4:19:56
     * @version: V1.0
     * 
     * @param fullPath
     *            文件名称（全路径）
     * @return 文件内容串或null
     * @throws Exception
     */
    public static String readFile(String fullPath) throws Exception
    {
        BufferedReader reader = new BufferedReader(new FileReader(fullPath));
        StringBuilder builder = new StringBuilder("");
        String line = null;

        while ((line = reader.readLine()) != null)
        {
            builder.append(line + "\n");
        }

        if (reader != null)
        {
            reader.close();
            reader = null;
        }

        return builder.toString();
    }

    /**
     * 
     * 获取资源文件流
     * 
     * @author wanghui
     * @date 2012-6-4 下午4:25:25
     * @version: V1.0
     * 
     * @param clazz
     *            Class
     * @param name
     *            文件名称
     * @return InpurStream
     */
    @SuppressWarnings("unchecked")
    public static InputStream getResourceAsStream(Class clazz, String name)
    {
        try
        {
            InputStream inputStream = clazz.getResourceAsStream(name);
            if (inputStream == null)
                inputStream = clazz.getClassLoader().getResourceAsStream(name);
            return inputStream;
        }
        catch (Exception e)
        {
            logger.warn("获取资源文件失败", e);
            return null;
        }
    }

    /**
     * 
     * 把该文件拷贝到完整的路径中去
     * 
     * @author wanghui
     * @date 2012-6-4 下午4:26:46
     * @version: V1.0
     * 
     * @param file
     *            源文件对象
     * @param newPath
     *            目标目录
     * @return true-复制成功，false-复制失败
     */
    @SuppressWarnings("unused")
    private boolean copyFile(File file, String newPath)
    {
        logger.info("开始拷贝文件....");
        logger.info("源文件:" + file.getName());
        logger.info("目标文件在:" + newPath);

        boolean ret = false;

        String fileName = file.getName();

        int pos = fileName.lastIndexOf(File.separator);
        if (pos > 0)
        {
            fileName = fileName.substring(pos + 1, fileName.length());
        }

        String lastPath = newPath + File.separator + fileName;
        File objFile = new File(lastPath);

        if (objFile.exists() && !objFile.delete()) // 如果存在则删除
        {
            logger.info("删除目标文件失败");
            return false;
        }

        // 开始拷贝
        try
        {
            ret = objFile.createNewFile();

            if (!ret)
            {
                logger.error("目标文件创建失败!" + lastPath);
                return false;
            }

            FileInputStream fis = new FileInputStream(file);
            FileOutputStream fos = new FileOutputStream(objFile);
            byte[] buf = new byte[1024];
            int i = 0;
            while ((i = fis.read(buf)) != -1)
            {
                fos.write(buf, 0, i);
            }

            fis.close();
            fos.close();
            fis = null;
            fos = null;
        }
        catch (IOException t)
        {
            logger.error("文件复制失败!", t);

            return false;
        }

        return true;
    }

    /**
     * 把源文件对象复制成目标文件对象
     * @param src
     * @param dst
     */
	public static void copy(File src, File dst) {
		int buffersize = 3 * 1024;
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new BufferedInputStream(new FileInputStream(src), buffersize);
			out = new BufferedOutputStream(new FileOutputStream(dst),
					buffersize);
			byte[] buffer = new byte[buffersize];
			int len = 0;
			while ((len = in.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != out) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * html页面转换为pdf文件
	 * @param inputFile
	 * @param outputFile
	 * @return
	 * @throws Exception
	 */
	public static boolean convertHtmlToPdf(String inputFile, String outputFile)
			throws Exception {
		OutputStream os = new FileOutputStream(outputFile);
		ITextRenderer renderer = new ITextRenderer();
		File file = new File(inputFile);
		org.jsoup.nodes.Document doc = Jsoup.parse(file, "UTF-8");
		System.out.println(doc.html());
		/*org.jsoup.nodes.Element content = doc.getElementById("content");
		System.out.println(content);
		content.html("ceshi");*/
//		renderer.setDocumentFromString(doc.html());
		
        String url = new File(inputFile).toURI().toURL().toString();
        renderer.setDocument(url);
		
		// 解决中文支持问题
		ITextFontResolver fontResolver = renderer.getFontResolver();
		fontResolver.addFont("C:/Windows/Fonts/SIMSUN.TTC",
				BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
		// 解决图片的相对路径问题
		renderer.getSharedContext().setBaseURL("file:/f://ebank");
		renderer.layout();
		renderer.createPDF(os);
		os.flush();
		os.close();
		return true;
	}
	
	/**
	 * 由html生成PDF文件
	 * @param htmlName
	 * @param pdfName
	 * @throws Exception
	 */
	public static void generationPdf(String htmlName , String pdfName) throws Exception{  
        String system = System.getProperty("os.name");
        String[] commond = new String[3];
        /*if("Windows Vista".equalsIgnoreCase(system))   //xp系统  
        	commond = "c:/Program Files/wkhtmltopdf/bin/wkhtmltopdf.exe " + htmlName + " " + pdfName;  
        else  if("Linux".equalsIgnoreCase(system))  //linux 系统  
        	commond = "wkhtmltopdf-amd64 " + htmlName + " " + pdfName; */ 
//        commond = "wkhtmltopdf-amd64 '" + htmlName + "' '" + pdfName+"'";
        commond[0] = "wkhtmltopdf-amd64";
        commond[1] = "\""+htmlName+"\"";
        commond[2] = "\""+pdfName+"\"";
        Process p = Runtime.getRuntime().exec("sh "+Config.getProperty("shell_path")+"html2pdf.sh "+htmlName+" "+pdfName);
        p.waitFor();
        BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream())); //正常输入流
        BufferedReader errorin = new BufferedReader(new InputStreamReader(p.getErrorStream()));    //错误输入流
        // read the ls output                
        String line; 
        System.out.println("in.readLine() = " + in.readLine());
        System.out.println("errorin.readLine() = " + errorin.readLine());
        while ((line = errorin.readLine())!= null) 
        {      
             //print 输入流
            System.out.println(line);         
        }        
    }

	public static void main(String[] args) throws Exception{
		FileUtil.generationPdf("http://www.yrxxtech.com:9070/ysf/ebank/index.html?merName=%E6%B5%8B%E8%AF%95%E5%95%86%E6%88%B7&merBankName=%E5%B7%A5%E5%95%86%E9%93%B6%E8%A1%8C&merBankCardNo=3000912312412312&merCorpCode=90091231&userName=%E5%BC%A0%E4%B8%89&certId=309987654321234&userBankName=%E5%B7%A5%E5%95%86%E9%93%B6%E8%A1%8C&userBankCardNo=200009123141231","F:\\ebank\\index.pdf");
	}
}
