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
import net.Implementist.Servlet.Signup_Servlet;
import static net.Implementist.Utils.HXUtil.addUser;
import net.sf.json.JSONObject;

/**
 *
 * @author dell
 */
public class Signup {
    /*
    * 注册验证
    */
    public static JSONObject signupVerify(String account,String pwd,String nickName,String tel,int sex) throws ServletException{
        JSONObject vresult = new JSONObject();
        Map<String, String> params = new HashMap<>();
        try {
            //密码验证结果
            boolean verifySecret = verifySignup(account);      //验证用户名是否已经存在
            if(verifySecret)
            {
               //验证环信账号有没有注册成功
               boolean verifyHx = addUser(account, pwd, nickName);
               if(verifyHx){
                int insertResult = UserDao.insertUser(account, pwd, nickName, tel, sex);  //验证数据是否插入成功
                if (insertResult<=0) {
                    params.put("code", "0");   //注册失败，数据插入数据库失败
                } else {
                    params.put("code", "1");   //注册成功
                }
               }else{
                   params.put("code", "3");   //注册失败，注册环信账号失败
               }
            }
            else{
                params.put("code", "2");        //用户名已存在
            }
            vresult.put("params", params);
        }catch(Exception e){
            Logger.getLogger(Signup_Servlet.class.getName()).log(Level.SEVERE, null, e);
        }
        return vresult;
    }
    /**
     * 验证用户名是否存在
     *
     * @param userName
     */
    private static Boolean verifySignup(String account) {
        User user = UserDao.queryUserAccount(account);
        //账户密码验证
        return user == null;
    }
    /**
     * 验证用户ID是否存在
     *
     * @param uid
     * @return 布尔型数据
     */
    public static boolean verifyUid(String uid) {
        User user = UserDao.queryUid(uid);
        //账户id验证
        return user == null;
    }
    /**
     * 去环信服务器注册账号
     *
     * @param account    账号
     * @param password   密码
     * @return 布尔型数据
     */
    public static boolean signUpHxAccount(String account, String password) {
         
        return true;
    }
   
}
