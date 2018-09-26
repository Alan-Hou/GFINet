package com.team2.result;

import java.io.Serializable;
import java.util.Date;

public class Trade implements Serializable {


    private int tradeOrigSys;


    private String txni;


    private String sender_id;

    private Date trade_time;

    private String product_id;

    private int amount;

    private int price;

    private String receiver_id;

    private Integer status;


    public String getTxni() {
        return txni;
    }

    public void setTxni(String txni) {
        this.txni = txni;
    }

    public Date getTrade_time() {
        return trade_time;
    }

    public void setTrade_time(Date trade_time) {
        this.trade_time = trade_time;
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


    @Override
    public String toString() {
        return "Trade{" +
                "tradeOrigSys=" + tradeOrigSys +
                ", txni='" + txni + '\'' +
                ", sender_id='" + sender_id + '\'' +
                ", trade_time=" + trade_time +
                ", product_id='" + product_id + '\'' +
                ", amount=" + amount +
                ", price=" + price +
                ", receiver_id='" + receiver_id + '\'' +
                ", status=" + status +
                '}';
    }
}
