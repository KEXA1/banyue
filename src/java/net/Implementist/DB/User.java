/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.Implementist.DB;

/**
 *
 * @author dell
 * 用户类
 */
public class User {
    //id，必须有的，数据库的检索标识
    private int id;
    //用户账户
    private String account;
    //用户密码
    private String password;
    //环信id
    private String hxId;
    //用户昵称
    private String nickName;
    //用户头像
    private String headPhoto;
    //账户余额
    private double balance;
    //用户地址
    private String address;
    //性别
    private int sex;
    //信誉值
    private int reputation;
    //电话
    private String tel;
    //学校
    private String school;

    //用户登录状态，1为未登录，2为已登录
    private int state;

    //下面分别实现相应的get和set方法
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeadPhoto() {
        return headPhoto == null ? null:headPhoto;
    }

    public void setHeadPhoto(String headPhoto) {
        this.headPhoto = headPhoto;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAddress() {
        return address == null ? null : address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getReputation() {
        return reputation;
    }

    public void setReputation(int reputation) {
        this.reputation = reputation;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
  
    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getHxId() {
        return hxId;
    }

    public void setHxId(String hxId) {
        this.hxId = hxId;
    }
    
    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }
}