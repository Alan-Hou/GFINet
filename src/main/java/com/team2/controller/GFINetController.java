package com.team2.controller;

import com.alibaba.fastjson.JSONObject;
import com.team2.result.Trade;
import com.team2.result.UserResult;
import com.team2.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
@SessionAttributes("currentUser")
public class GFINetController {
    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/")
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        return mv;
    }

    @RequestMapping(value = "/api/login", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    UserResult doLogin(@RequestParam("username") String username, @RequestParam("password") String password) {

        System.out.println("/api/login");
        ResponseEntity<UserResult> response = restTemplate.getForEntity("http://192.168.43.95:8080/get/user?username=" + username + "&password=" + password, UserResult.class);
        System.out.println(response.toString());
        UserResult result = response.getBody();

        return result;
    }


    @RequestMapping(value = "/api/logout", method = RequestMethod.GET)
    public ModelAndView logout(SessionStatus status) {
        status.setComplete();
        ModelAndView mv = new ModelAndView("redirect:http://192.168.43.95:8080/quit");
        mv.setViewName("index");
        return mv;
    }

    @RequestMapping(value = "/api/trader", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    ModelAndView searchAllTrades(@RequestParam("username") String username) {
        System.out.println("/api/trader");
        ModelAndView mv = new ModelAndView();
        ModelMap map = mv.getModelMap();
        map.addAttribute("currentUser", username);
        ResponseEntity<Trade[]> response = restTemplate.getForEntity("http://192.168.43.95:8080/get/getAllTrades?username=" + username, Trade[].class);
        System.out.println(response.toString());
        List<Trade> result = Arrays.asList(response.getBody());
        mv.addObject("Trade", result);
        mv.setViewName("trader");
        return mv;
    }

    @RequestMapping(value = "/api/seller", method = RequestMethod.GET, produces = "application/text")
    public @ResponseBody
    ModelAndView searchAllSellers(@RequestParam("username") String username) {
        ModelAndView mv = new ModelAndView();
        ModelMap map = mv.getModelMap();
        map.addAttribute("currentUser", username);
        mv.setViewName("seller");
        return mv;
    }
    @RequestMapping(value = "/api/getTradeList",method=RequestMethod.POST,produces = "application/json")
    public @ResponseBody
    String getAllList(@ModelAttribute("currentUser") String username)
    {
        ResponseEntity<Trade[]> response = restTemplate.getForEntity("http://192.168.43.95:8080/get/Strade/getAllTrades?username=" + username, Trade[].class);
        List<Trade> result = Arrays.asList(response.getBody());
        return JSONObject.toJSONString(result);

    }
    @RequestMapping(value = "/api/trader/searchByStatus", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    Trade searchAllTradesByStatus(@RequestParam("status") Integer status, @ModelAttribute("currentUser") String username) {
        System.out.println("/api/trader/searchByStatus");
        ResponseEntity<Trade> response = restTemplate.getForEntity("http://localhost:8080/get/Ttrade/getAllTradesByStatus?username=" + username + "&status=" + status, Trade.class);
        System.out.println(response.toString());
        Trade result = response.getBody();
        return result;
    }
    @RequestMapping(value = "/api/trader/addOneTrade", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    String tAddOneTrade(@RequestParam("product_id") String productId,
                        @RequestParam("amount") Integer amount,
                        @RequestParam("price") Integer price,
                        @RequestParam("receiver_id") String receiverId,
                        @ModelAttribute("currentUser") String username) {
        System.out.println("/api/trader/addOneTrade");
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json;charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        JSONObject object = new JSONObject();
        object.put("tradeOriSys", Constant.TW);
        object.put("sender_id", username);
        Date trader_time = new Date();
        object.put("trader_time", trader_time);
        object.put("product_id", productId);
        object.put("amount", amount);
        object.put("price", price);
        object.put("receiver_id", receiverId);
        object.put("status", Constant.REQUESTED);
        HttpEntity<String> formEntity = new HttpEntity<String>(object.toString(), headers);
        ResponseEntity<String> response = restTemplate.postForEntity("http://192.168.43.95:8080/add/Ttrade/addOneTtrade", formEntity, String.class);
        String result = response.getBody();
        return result;
    }

    @RequestMapping(value = "/api/seller/addOneTrade", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    String sAddOneTrade(@RequestParam("product_id") String productId,
                        @RequestParam("amount") Integer amount,
                        @RequestParam("price") Integer price,
                        @RequestParam("receiver_id") String receiverId,
                        @ModelAttribute("currentUser") String username) {
        System.out.println("/api/seller/addOneTrade");
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json;charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        JSONObject object = new JSONObject();
        object.put("tradeOriSys", Constant.TW);
        object.put("sender_id", username);
        Date trader_time = new Date();
        object.put("trader_time", trader_time);
        object.put("product_id", productId);
        object.put("amount", amount);
        object.put("price", price);
        object.put("receiver_id", receiverId);
        object.put("status", Constant.REQUESTED);
        HttpEntity<String> formEntity = new HttpEntity<String>(object.toString(), headers);
        ResponseEntity<String> response = restTemplate.postForEntity("http://192.168.43.95:8080/add/Strade/addOneTtrade", formEntity, String.class);
        String result = response.getBody();
        System.out.println(result);
        return result;
    }


}
