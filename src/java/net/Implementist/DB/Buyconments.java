/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.Implementist.DB;

/**
 *
 * @author dell
 */
public class Buyconments {
    //评论id
    private int bconid;
    //求购商品id
    private int bid;
    //评论人id
    private int uid;
    //求购商品所属人id
    private int buid;
    //评论时间
    private String bcontime;
    //评论内容
    private String bconcontent;

    public int getBconid() {
        return bconid;
    }

    public void setBconid(int bconid) {
        this.bconid = bconid;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getBuid() {
        return buid;
    }

    public void setBuid(int buid) {
        this.buid = buid;
    }

    public String getBcontime() {
        return bcontime;
    }

    public void setBcontime(String bcontime) {
        this.bcontime = bcontime;
    }

    public String getBconcontent() {
        return bconcontent;
    }

    public void setBconcontent(String bconcontent) {
        this.bconcontent = bconcontent;
    }

    public int getBconstate() {
        return bconstate;
    }

    public void setBconstate(int bconstate) {
        this.bconstate = bconstate;
    }
    //评论的状态，1表示正常，2表示已删除
    private int bconstate;
}
