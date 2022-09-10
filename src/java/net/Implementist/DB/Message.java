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
public class Message {
    //消息ID
    private int mid;
    //接收人ID
    private int receiveid;
    //题目
    private String mtitle;
    //内容
    private String mcontent;
    //时间
    private String mtime;
    //状态
    private int mstate;

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public int getReceiveid() {
        return receiveid;
    }

    public void setReceiveid(int receiveid) {
        this.receiveid = receiveid;
    }

    public String getMtitle() {
        return mtitle;
    }

    public void setMtitle(String mtitle) {
        this.mtitle = mtitle;
    }

    public String getMcontent() {
        return mcontent;
    }

    public void setMcontent(String mcontent) {
        this.mcontent = mcontent;
    }

    public String getMtime() {
        return mtime;
    }

    public void setMtime(String mtime) {
        this.mtime = mtime;
    }

    public int getMstate() {
        return mstate;
    }

    public void setMstate(int mstate) {
        this.mstate = mstate;
    }
}
