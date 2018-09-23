package com.team2.controller;

import com.team2.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import sun.misc.BASE64Encoder;

import com.team2.result.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Controller
public class GFINetController {


    @Autowired
    RestTemplate template;

    @RequestMapping(value="/")
    public ModelAndView login() {
        ModelAndView mv=new ModelAndView();
        mv.setViewName("index");
        return mv;
    }

    @RequestMapping(value="/api/login",method= RequestMethod.POST,produces = "application/json")
    public @ResponseBody UserResult doLogin(@RequestParam("username")String username,@RequestParam("password")String password) {

        System.out.println("/api/login");
        ResponseEntity<UserResult> response=template.getForEntity("http://192.168.43.95:8080/get/user?username="+username+"&password="+password,UserResult.class);

        System.out.println(response.toString());

        UserResult result=response.getBody();

        return result;
    }
    @RequestMapping(value="/api/logout")
    public ModelAndView logout() {
        ModelAndView mv=new ModelAndView();
        mv.setViewName("index");
        return mv;
    }
    @RequestMapping(value="/api/tarder")
    public ModelAndView trader()
    {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("trader");
        return modelAndView;
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
