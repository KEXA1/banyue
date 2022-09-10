/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.Implementist.DB;

/**
 * 评论的实体类
 * @author dell
 */
public class Conments {
    //评论id
    private int conid;
    //商品id
    private int gid;
    //评论人id
    private int uid;
    //商品所属人id
    private int guid;
    //评论时间
    private String contime;
    //评论内容
    private String concontent;
    //评论的状态，1表示正常，2表示已删除
    private int constate;

    public int getConid() {
        return conid;
    }

    public void setConid(int conid) {
        this.conid = conid;
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

    public String getContime() {
        return contime;
    }

    public void setContime(String contime) {
        this.contime = contime;
    }

    public String getConcontent() {
        return concontent;
    }

    public void setConcontent(String concontent) {
        this.concontent = concontent;
    }

    public int getConstate() {
        return constate;
    }

    public void setConstate(int constate) {
        this.constate = constate;
    }
    
}
