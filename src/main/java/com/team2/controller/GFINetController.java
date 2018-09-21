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

    @RequestMapping(value="/api/login",method= RequestMethod.POST)
    public void doLogin( @RequestParam String username ,@RequestParam String password) {

        System.out.println("名字" + username + "密码" + password);
        String newPassword = EncoderByMd5(password);
        System.out.println(newPassword);
        new ModelAndView("redirect:http://192.168.43.95:8080/user?username=" + username + "&password=" + newPassword);
    }

    @RequestMapping(value="/api/logout")
    public ModelAndView logout() {
        ModelAndView mv=new ModelAndView();
        mv.setViewName("index");
        return mv;
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
