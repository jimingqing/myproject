package com.yrtech.wx.capp.framework.core.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CodeVerifyServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static Random random = new Random();
	
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try{
			this.getPNG(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void getPNG(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int codeLength = 4;// ��֤�볤��
		int mixTimes = 80;// ģ��̶Ȳ���
		Color bgColor = getRandColor(200, 250);// ������ɫ
		Color bfColor = new Color(0, 0, 0);// ������ɫ
		boolean ifRandomColor = true;// �����ַ��Ƿ���ɫ���
		boolean ifMixColor = true;// ģ�����Ƿ���ɫ���

		// ����ҳ�治����
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		// ���ڴ��д���ͼ��
		int width = 13 * codeLength + 6, height = 20;
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		// ��ȡͼ��������
		Graphics g = image.getGraphics();
		// �趨����ɫ
		g.setColor(bgColor);
		g.fillRect(0, 0, width, height);
		// �趨����
		g.setFont(new Font("Arail", Font.ITALIC, 18));
		// ���߿�
		g.setColor(new Color(33, 66, 99));
		// g.drawRect(0, 0, width - 1, height - 1);
		// ����������ߣ�ʹͼ���е���֤�벻�ױ��������̽�⵽
		g.setColor(getRandColor(160, 200));
		for (int i = 0; i < mixTimes * codeLength / 10; i++) {
			if (ifMixColor) {
				g.setColor(getRandColor(160, 200));
			}
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}
		// ȡ���������֤��(4λ����)
		String codeValue = "";  
        for (int i = 0; i < 4; i++) {  
            //String rand = String.valueOf(random.nextInt(10));   
            String rand = getRandomChar();  
            codeValue = codeValue.concat(rand);  
            g.setFont(getRandomFont());//�������   
            // ����֤����ʾ��ͼ����   
            if (ifRandomColor)
				g.setColor(getRandColor(20, 110, 0));
			else
				g.setColor(bfColor); 
            g.drawString(rand, 13 * i + 6, 16);  
        }  
        request.getSession().setAttribute("safeCode", codeValue);  

		g.dispose();
		// ���ͼ��ҳ��
		ImageIO.write(image, "PNG", response.getOutputStream());
	}
	
	private Color getRandColor(int fc, int bc) {
		return getRandColor(fc, bc, fc);
	}

	private Color getRandColor(int fc, int bc, int interval) {
		if (fc > 255) {
			fc = 255;
		}
		if (bc > 255) {
			bc = 255;
		}
		int r = fc + random.nextInt(bc - interval);
		int g = fc + random.nextInt(bc - interval);
		int b = fc + random.nextInt(bc - interval);
		return new Color(r, g, b);
	}
	
	/** 
     * �������ַ���д��Сд������ 
     * <b>function:</b> ���� 
     * @createDate 2010-8-23 ����10:33:55 
     * @author hoojo 
     * @return 
     */  
    public static String getRandomChar() {  
        int index = (int) Math.round(Math.random() * 2);  
        String randChar = "";  
        switch (index) {  
        case 0://��д�ַ�   
            randChar = String.valueOf((char)Math.round(Math.random() * 25 + 65));  
            break;  
        case 1://Сд�ַ�   
            randChar = String.valueOf((char)Math.round(Math.random() * 25 + 97));  
            break;  
        default://����   
            randChar = String.valueOf(Math.round(Math.random() * 9));  
            break;  
        }  
        return randChar;  
    }
    
    /** 
     * <b>function:</b> ���������塢���ִ�С 
     * @createDate 2010-8-23 ����10:44:22 
     * @author hoojo 
     * @return 
     */  
    public static Font getRandomFont() {  
        String[] fonts = {"Georgia", "Verdana", "Arial", "Tahoma", "Time News Roman", "Courier New", "Arial Black", "Quantzite"};  
        int fontIndex = (int)Math.round(Math.random() * (fonts.length - 1));  
        int fontSize = (int) Math.round(Math.random() * 4 + 16);  
        return new Font(fonts[fontIndex], Font.PLAIN, fontSize);  
    }

}