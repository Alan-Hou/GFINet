package com.team2.controller;

import com.team2.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Controller
public class GFINetController {
    @RequestMapping(value="/")
    public ModelAndView login() {
        ModelAndView mv=new ModelAndView();
        mv.setViewName("index");
        return mv;
    }

    @RequestMapping(value="/api/login",method= RequestMethod.POST,consumes = "application/json")
    public void doLogin( @RequestBody User user) {

        System.out.println("名字" + user.getUserName() + "密码" + user.getPassword());
        String newPassword = EncoderByMd5(user.getPassword());
        System.out.println(newPassword);
        //  new ModelAndView("redirect:/toLogin？userName=" + userName + "&password=" + newPassword);
    }

    //利用MD5进行加密
    public String EncoderByMd5(String str){
        String newstr=null;
        try {
            //确定计算方法
            MessageDigest md5=MessageDigest.getInstance("MD5");
            BASE64Encoder base64en = new BASE64Encoder();
            //加密后的字符串
            newstr=base64en.encode(md5.digest(str.getBytes("utf-8")));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return newstr;
    }

}
