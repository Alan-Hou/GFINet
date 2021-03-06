package com.team2.controller;

import com.alibaba.fastjson.JSONObject;
import com.team2.result.Trade;
import com.team2.result.TradeResult;
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

import java.util.ArrayList;
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

    @RequestMapping(value = "/api/trader", method = RequestMethod.GET, produces = "application/text")
    public @ResponseBody
    ModelAndView searchAllTrades(@RequestParam("username") String username) {
        System.out.println("/api/trader");
        ModelAndView mv = new ModelAndView();
        ModelMap map = mv.getModelMap();
        map.addAttribute("currentUser", username);
        mv.setViewName("trader");
        return mv;
    }

    @RequestMapping(value = "/api/trader/getTradeList",method=RequestMethod.POST,produces = "application/json")
    public @ResponseBody
    String tGetAllList(@ModelAttribute("currentUser") String username) {
        ResponseEntity<Trade[]> response = restTemplate.getForEntity("http://192.168.43.95:8080/get/Ttrade/getAllTrades?username=" + username, Trade[].class);
        List<Trade> result = Arrays.asList(response.getBody());
        List<TradeResult> tradeResults=new ArrayList<TradeResult>();
        for (Trade t:result
                ) {
            tradeResults.add(new TradeResult(t));
        }
        System.out.println(JSONObject.toJSONString(tradeResults));
        return JSONObject.toJSONString(tradeResults);
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

    @RequestMapping(value = "/api/seller/getTradeList",method=RequestMethod.GET,produces = "application/json")
    public @ResponseBody
    String sGetAllList(@ModelAttribute("currentUser") String username) {
        System.out.println("/api/seller/getTradeList");
        ResponseEntity<Trade[]> response = restTemplate.getForEntity("http://192.168.43.95:8080/get/Strade/getAllTrades?username=" + username, Trade[].class);
        List<Trade> result = Arrays.asList(response.getBody());
        List<TradeResult> tradeResults=new ArrayList<TradeResult>();
        for(Trade t: result) {
            tradeResults.add(new TradeResult(t));
        }
        System.out.println(JSONObject.toJSONString(tradeResults));
        return JSONObject.toJSONString(tradeResults);
    }

    @RequestMapping(value = "/api/trader/searchByStatus", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String tSearchAllTradesByStatus(@RequestParam("status") Integer status, @ModelAttribute("currentUser") String username) {
        System.out.println("/api/trader/searchByStatus");
        ResponseEntity<Trade[]> response = restTemplate.getForEntity("http://192.168.43.95:8080/get/Ttrade/getAllTradesByStatus?username=" + username + "&status=" + status, Trade[].class);
        System.out.println(response.toString());
        List<Trade> result = Arrays.asList(response.getBody());
        List<TradeResult> tradeResults=new ArrayList<TradeResult>();
        for (Trade t:result
                ) {
            tradeResults.add(new TradeResult(t));
        }
        System.out.println(JSONObject.toJSONString(tradeResults));
        return JSONObject.toJSONString(tradeResults);
    }
    @RequestMapping(value = "/api/seller/searchByStatus", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String sSearchAllTradesByStatus(@RequestParam("status") Integer status, @ModelAttribute("currentUser") String username) {
        System.out.println("/api/seller/searchByStatus");
        ResponseEntity<Trade[]> response = restTemplate.getForEntity("http://192.168.43.95:8080/get/Strade/getAllTradesByStatus?username=" + username + "&status=" + status, Trade[].class);
        System.out.println(response.toString());
        List<Trade> result = Arrays.asList(response.getBody());
        List<TradeResult> tradeResults=new ArrayList<TradeResult>();
        for (Trade t:result
                ) {
            tradeResults.add(new TradeResult(t));
        }
        System.out.println(JSONObject.toJSONString(tradeResults));
        return JSONObject.toJSONString(tradeResults);
    }
    @RequestMapping(value = "/api/trader/search", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String tSearchAllTradesById(@RequestParam("txni") String txni, @ModelAttribute("currentUser") String username) {
        System.out.println("/api/trader/search");
        ResponseEntity<Trade[]> response = restTemplate.getForEntity("http://192.168.43.95:8080/get/Ttrade/findOneTradeByTxnl?username=" + username + "&txni=" + txni, Trade[].class);
        System.out.println(response.toString());
        List<Trade> result = Arrays.asList(response.getBody());
        List<TradeResult> tradeResults=new ArrayList<TradeResult>();
        for (Trade t:result
                ) {
            tradeResults.add(new TradeResult(t));
        }
        System.out.println(JSONObject.toJSONString(tradeResults));
        return JSONObject.toJSONString(tradeResults);
    }
    @RequestMapping(value = "/api/seller/search", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String sSearchAllTradesById(@RequestParam("txni") String txni, @ModelAttribute("currentUser") String username) {
        System.out.println("/api/seller/search");
        ResponseEntity<Trade[]> response = restTemplate.getForEntity("http://192.168.43.95:8080/get/Strade/findOneTradeByTxnl?username=" + username + "&txni=" + txni, Trade[].class);
        List<Trade> result = Arrays.asList(response.getBody());
        List<TradeResult> tradeResults=new ArrayList<TradeResult>();
        for (Trade t:result
             ) {
         tradeResults.add(new TradeResult(t));
        }
        System.out.println(JSONObject.toJSONString(tradeResults));
        return JSONObject.toJSONString(tradeResults);
    }

    @RequestMapping(value = "/api/trader/addOneTrade", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    String tAddOneTrade(@ModelAttribute("currentUser") String username,
                        @RequestParam("product_id") String productId,
                        @RequestParam("amount") Integer amount,
                        @RequestParam("price") Integer price,
                        @RequestParam("receiver_id") String receiverId){
        System.out.println("/api/trader/addOneTrade");
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json;charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        JSONObject object = new JSONObject();
        object.put("tradeOrigSys", Constant.TW);
        object.put("sender_id", username);
        Date trade_time = new Date();
        object.put("trade_time", trade_time);
        object.put("product_id", productId);
        object.put("amount", amount);
        object.put("price", price);
        object.put("receiver_id", receiverId);
        object.put("status", Constant.REQUESTED);
        HttpEntity<String> formEntity = new HttpEntity<String>(object.toString(), headers);
        String response = restTemplate.postForObject("http://192.168.43.95:8080/add/Ttrade/addOneTtrade", formEntity, String.class);
        return response;
    }

    @RequestMapping(value = "/api/seller/addOneTrade", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    String sAddOneTrade(@ModelAttribute("currentUser") String username,
                        @RequestParam("product_id") String productId,
                        @RequestParam("amount") Integer amount,
                        @RequestParam("price") Integer price,
                        @RequestParam("receiver_id") String receiverId) {
        System.out.println("/api/seller/addOneTrade");
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json;charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        JSONObject object = new JSONObject();
        object.put("tradeOrigSys", Constant.TW);
        object.put("sender_id", username);
        Date trade_time = new Date();
        object.put("trade_time", trade_time);
        object.put("product_id", productId);
        object.put("amount", amount);
        object.put("price", price);
        object.put("receiver_id", receiverId);
        object.put("status", Constant.REQUESTED);
        HttpEntity<String> formEntity = new HttpEntity<String>(object.toString(), headers);
        String response = restTemplate.postForObject("http://192.168.43.95:8080/add/Strade/addOneTtrade", formEntity, JSONObject.class).toString();
        return response;
    }
}
