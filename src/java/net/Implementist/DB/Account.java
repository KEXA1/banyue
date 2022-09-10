/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.Implementist.DB;

/**
 * 交易订单实体类
 * @author dell
 */
public class Account {
    //订单id
    private int id;
    //订单号
    private String anumber;
    //交易商品id
    private int gid;
    //买家id
    private int uid;
    //卖家id
    private int guid;
    //交易金额
    private double abill;
    //交易时间
    private String atime;
    //交易状态
    private int astate;
    
    //get和set方法
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnumber() {
        return anumber;
    }

    public void setAnumber(String anumber) {
        this.anumber = anumber;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getGuid() {
        return guid;
    }

    public void setGuid(int guid) {
        this.guid = guid;
    }

    public double getAbill() {
        return abill;
    }

    public void setAbill(double abill) {
        this.abill = abill;
    }

    public String getAtime() {
        return atime;
    }

    public void setAtime(String atime) {
        this.atime = atime;
    }

    public int getAstate() {
        return astate;
    }

    public void setAstate(int astate) {
        this.astate = astate;
    }
}
