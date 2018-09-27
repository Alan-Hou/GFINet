package com.team2.result;

import java.io.Serializable;
import java.text.SimpleDateFormat;

public class TradeResult implements Serializable {
    private int tradeOrigSys;
    private String txni;
    private String sender_id;
    private String trade_time;
    private String product_id;
    private int amount;
    private int price;
    private String receiver_id;
    private Integer status;
    public TradeResult(Trade trade) {
        this.tradeOrigSys = trade.getTradeOrigSys();
        this.txni = trade.getTxni();
        this.sender_id = trade.getSender_id();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.trade_time = sdf.format(trade.getTrade_time());
        this.product_id = trade.getProduct_id();
        this.amount = trade.getAmount();
        this.price = trade.getAmount();
        this.receiver_id = trade.getReceiver_id();
        this.status = trade.getStatus();
    }
    public String getTrade_time() {
        return trade_time;
    }
    public void setTrade_time(String trade_time) {
        this.trade_time = trade_time;
    }
    public String getTxni() {
        return txni;
    }
    public void setTxni(String txni) {
        this.txni = txni;
    }
    public int getTradeOrigSys() {
        return tradeOrigSys;
    }
    public void setTradeOrigSys(int tradeOrigSys) {
        this.tradeOrigSys = tradeOrigSys;
    }
    public String getSender_id() {
        return sender_id;
    }
    public void setSender_id(String sender_id) {
        this.sender_id = sender_id;
    }
    public String getProduct_id() {
        return product_id;
    }
    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public String getReceiver_id() {
        return receiver_id;
    }
    public void setReceiver_id(String receiver_id) {
        this.receiver_id = receiver_id;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
}
