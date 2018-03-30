package com.yrtech.wx.capp.portal.action;

import java.io.ByteArrayInputStream;

import com.yrtech.wx.capp.portal.util.SecurityCode;
import com.yrtech.wx.capp.portal.util.SecurityImage;

public class SecurityCodeImageAction extends BaseAction{
    
    //图片流
    private ByteArrayInputStream imageStream;

    public ByteArrayInputStream getImageStream() {
        return imageStream;
    }

    public void setImageStream(ByteArrayInputStream imageStream) {
        this.imageStream = imageStream;
    }

    
    public String execute() throws Exception {
        //如果开启Hard模式，可以不区分大小写
//        String securityCode = SecurityCode.getSecurityCode(4,SecurityCodeLevel.Hard, false).toLowerCase();
        
        //获取默认难度和长度的验证码
        String securityCode = SecurityCode.getSecurityCode();
        imageStream = SecurityImage.getImageAsInputStream(securityCode);
        //放入session中
        session().setAttribute("SESSION_SECURITY_CODE", securityCode);
        return SUCCESS;
    }

}
