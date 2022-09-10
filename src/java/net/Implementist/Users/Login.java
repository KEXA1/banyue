/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.Implementist.Users;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import net.Implementist.DB.User;
import net.Implementist.Dao.UserDao;
import net.sf.json.JSONObject;

/**
 * 用户登录类
 * @author dell
 */
public class Login {
    //登录验证
    public static JSONObject Loginverify(String account,String password) throws ServletException{
        JSONObject vresult = new JSONObject();
        try {
            //密码验证结果
            boolean verifyResult = verifyLogin(account, password);
            Map<String, String> code = new HashMap<>();
            Map<String, String> data = new HashMap<>();
            if (verifyResult) {
                User user = UserDao.queryUserAccount(account);
                if(user.getState() == 1){
                    //将用户的登录状态改为2
                    UserDao.refreshUser(String.valueOf(user.getId()), "Ustate", "2");
                    //拼接返回数据
                    code.put("code", "1");
                    data.put("account", user.getAccount().trim());
                    data.put("password", user.getPassword().trim());
                    data.put("hxid", user.getHxId());
                    data.put("nickname", user.getNickName());
                    data.put("headphoto", user.getHeadPhoto().trim());
                    data.put("sex", String.valueOf(user.getSex()).trim());
                    data.put("balance", String.valueOf(user.getBalance()).trim());
                    data.put("address", user.getAddress());
                    data.put("school", user.getSchool());
                    data.put("reputation", String.valueOf(user.getReputation()).trim());
                    data.put("tel", user.getTel());
                    System.out.println("登录成功！");
                }else if(user.getState() == 2){
                    //用户在其他设备已登录
                    code.put("code", "2");
                    System.out.println("用户在其他设备登录！");
                }else{
                    //登录失败
                    code.put("code", "0");
                    System.out.println("登录失败！");
                }
            } else {
                //登录失败
                code.put("code", "0");
                System.out.println("登录失败！");
            }
            vresult.put("code", code);
            vresult.put("data", data);
        }catch(Exception e){
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, e);
        }
        return vresult;
    }
    /**
     * 验证用户名密码是否正确
     *
     * @param account  账号
     * @param password  密码
     * @return 
     */
    public static Boolean verifyLogin(String account, String password) {
        User user = UserDao.queryUserAccount(account);
        //账户密码验证
        return null != user && password.equals(user.getPassword());
    }
}
