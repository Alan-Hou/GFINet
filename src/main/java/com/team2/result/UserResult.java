package com.team2.result;

import java.io.Serializable;

public class UserResult implements Serializable {

    /*
    此字段说明用户是否验证成功，为true说明验证成功，false说明验证失败,此时type为-1,同时生成对应的描述
     */
    private boolean isSucc;

    /*
        此字段返回用户的类型
     */
    private int usertype=-1;



    /*
        错误描述
     */
    private String describe;

    public boolean isSucc() {
        return isSucc;
    }

    public void setSucc(boolean succ) {
        isSucc = succ;
    }

    public int getUsertype() {
        return usertype;
    }

    public void setUsertype(int usertype) {
        this.usertype = usertype;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }


    @Override
    public String toString() {
        return "UserResult{" +
                "isSucc=" + isSucc +
                ", usertype=" + usertype +
                ", describe='" + describe + '\'' +
                '}';
    }
}
