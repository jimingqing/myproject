import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		String htmlName = "file:///mnt/dongguan/apache-tomcat-6.0.37/webapps/ysf/ebank/index.html?merName=%E6%B5%8B%E8%AF%95%E5%95%86%E6%88%B7&merBankName=%E5%B7%A5%E5%95%86%E9%93%B6%E8%A1%8C&merBankCardNo=3000912312412312&merCorpCode=90091231&userName=%E5%BC%A0%E4%B8%89&certId=309987654321234&userBankName=%E5%B7%A5%E5%95%86%E9%93%B6%E8%A1%8C&userBankCardNo=200009123141231";
		String pdfName = "/mnt/dongguan/apache-tomcat-6.0.37/webapps/ysf/ebank/index.pdf";
		
		String system = System.getProperty("os.name");
        String[] commond = new String[]{
        		"./test.sh",
        		 "'"+htmlName+"'", "'"+pdfName+"'"
        };
        /*if("Windows Vista".equalsIgnoreCase(system))   //xp系统  
        	commond = "c:/Program Files/wkhtmltopdf/bin/wkhtmltopdf.exe " + htmlName + " " + pdfName;  
        else  if("Linux".equalsIgnoreCase(system))  //linux 系统  
        	commond = "wkhtmltopdf-amd64 " + htmlName + " " + pdfName; */ 
//        commond = "wkhtmltopdf-amd64 " + htmlName + " " + pdfName+" >>"+"/mnt/dongguan/apache-tomcat-6.0.37/webapps/ysf/ebank/pdf.log";
//        commond[0] = "sh";
//        commond[1] = "";
//        commond[2] = "/usr/local/bin/wkhtmltopdf-amd64";
//        commond[3] = "\""+htmlName+"\"";
//        commond[4] = "\""+pdfName+"\"";
//        commond[5] = ">";
//        commond[6] = "/mnt/dongguan/apache-tomcat-6.0.37/webapps/ysf/ebank/log";
        Process p = Runtime.getRuntime().exec("sh /alidata/dongguan/apache-tomcat-6.0.37/webapps/ysf/WEB-INF/classes/test.sh "+htmlName+" "+pdfName+"");
//        p.waitFor();
        System.out.println(p.waitFor());
        
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

}
