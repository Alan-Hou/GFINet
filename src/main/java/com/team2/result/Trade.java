package com.team2.result;

import java.io.Serializable;
import java.util.Date;

public class Trade implements Serializable {


    private int tradeOriSys;


    private String txnl;


    private String sender_id;

    private Date trader_time;

    private String product_id;

    private int amount;

    private int price;

    private String receiver_id;


    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTxnl() {
        return txnl;
    }

    public void setTxnl(String txnl) {
        this.txnl = txnl;
    }

    public int getTradeOriSys() {
        return tradeOriSys;
    }

    public void setTradeOriSys(int tradeOriSys) {
        this.tradeOriSys = tradeOriSys;
    }

    public String getSender_id() {
        return sender_id;
    }

    public void setSender_id(String sender_id) {
        this.sender_id = sender_id;
    }

    public Date getTrader_time() {
        return trader_time;
    }

    public void setTrader_time(Date trader_time) {
        this.trader_time = trader_time;
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
}
