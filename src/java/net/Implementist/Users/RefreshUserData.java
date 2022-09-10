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
import net.Implementist.Utils.HXUtil;
import net.sf.json.JSONObject;
/**
 *
 * @author 更新用户数据
 */
public class RefreshUserData {
    //更新用户头像并保存
    public static JSONObject RefreshHead(String account,String photoPath) throws ServletException{
        JSONObject vresult = new JSONObject();
        Map<String, String> params = new HashMap<>();
        try {
            //密码验证结果
            Boolean verifyResult = verifyRefresh(account);      //验证用户id是否已经存在
            if(verifyResult)
            {
                //删除原来的旧头像，不删除先留着
           /*     User user = UserDAO.queryUid(uid);
                String oldpath = user.getimage();
                File file = new File(oldpath);
                file.delete();*/
                //插入新头像
                int insertResult = UserDao.refreshUserByAccount(account, "Uphoto", photoPath);  
                //验证头像是否修改成功
                if (insertResult>0) {
                    params.put("code", "1");   //修改成功
                    params.put("path", photoPath);  //将头像的本地储存路径返回到客户端
                } else {
                    params.put("code", "0");   //修改失败
                }
            }
            else{
               params.put("code", "0"); //修改失败
            }
            vresult.put("params", params);
        }catch(Exception e){
            Logger.getLogger(RefreshUserData.class.getName()).log(Level.SEVERE, null, e);
        }
        return vresult;
    }
    //处理修改昵称的逻辑操作
    public static JSONObject changeName(String account,String newname) 
            throws ServletException{
        //定义变量
        JSONObject vresult = new JSONObject();       //验证结果
        Map<String, String> data = new HashMap<>();  //用户数据
        try {
            //密码验证结果
            Boolean verifyResult = verifyRefresh(account);      //验证用户id是否已经存在
            if(verifyResult)
            {
                //将新的昵称写入数据库
                int nameResult = UserDao.refreshUserByAccount(account, "Unickname", newname);  
                //验证是否修改成功
                if (nameResult > 0) {
                    vresult.put("code", "1");   //修改成功
                    data.put("name", newname);
                    vresult.put("data", data);  
                } else {
                    vresult.put("code", "0");   //修改失败
                }
            }
            else{
               vresult.put("code", "0"); //修改失败
            }
        }catch(NumberFormatException e){
            Logger.getLogger(RefreshUserData.class.getName()).log(Level.SEVERE, null, e);
        }
        return vresult;
    }
    //处理修改性别的操作逻辑
    public static JSONObject changeSex(String account,String sex) 
            throws ServletException{
        //定义变量
        JSONObject vresult = new JSONObject();       //验证结果
        Map<String, String> data = new HashMap<>();  //返回的用户数据
        try {
            //密码验证结果
            Boolean verifyResult = verifyRefresh(account);      //验证用户id是否已经存在
            if(verifyResult)
            {
                //将新的性别写入数据库
                int sexResult = UserDao.refreshUserByAccount(account, "Usex", sex);  
                //验证是否修改成功
                if (sexResult > 0) {
                    vresult.put("code", "1");   //修改成功
                    data.put("sex", sex);
                    vresult.put("data", data);  
                } else {
                    vresult.put("code", "0");   //修改失败
                }
            }
            else{
               vresult.put("code", "0"); //修改失败
            }
        }catch(NumberFormatException e){
            Logger.getLogger(RefreshUserData.class.getName()).log(Level.SEVERE, null, e);
        }
        return vresult;
    }
    //处理修改学校的操作逻辑
    public static JSONObject changeSchool(String account,String school) 
            throws ServletException{
        //定义变量
        JSONObject vresult = new JSONObject();       //验证结果
        Map<String, String> data = new HashMap<>();  //返回的用户数据
        try {
            //密码验证结果
            Boolean verifyResult = verifyRefresh(account);      //验证用户id是否已经存在
            if(verifyResult)
            {
                //将新的学校名写入数据库
                int schoolResult = UserDao.refreshUserByAccount(account, "Uschool", school);  
                //验证是否修改成功
                if (schoolResult > 0) {
                    vresult.put("code", "1");   //修改成功
                    data.put("school", school);
                    vresult.put("data", data);  
                } else {
                    vresult.put("code", "0");   //修改失败
                }
            }
            else{
               vresult.put("code", "0"); //修改失败
            }
        }catch(NumberFormatException e){
            Logger.getLogger(RefreshUserData.class.getName()).log(Level.SEVERE, null, e);
        }
        return vresult;
    }
    //处理修改联系方式的操作逻辑
    public static JSONObject changeTel(String account,String tel) 
            throws ServletException{
        //定义变量
        JSONObject vresult = new JSONObject();       //验证结果
        Map<String, String> data = new HashMap<>();  //返回的用户数据
        try {
            //密码验证结果
            Boolean verifyResult = verifyRefresh(account);      
            //验证用户名是否已经存在
            if(verifyResult)
            {
                //将新的手机号写入数据库
                int telResult = UserDao.refreshUserByAccount(account, "Utel", tel);  
                //验证是否修改成功
                if (telResult > 0) {
                    vresult.put("code", "1");   //修改成功
                    data.put("tel", tel);
                    vresult.put("data", data);  
                } else {
                    vresult.put("code", "0");   //修改失败
                }
            }
            else{
               vresult.put("code", "0"); //修改失败
            }
        }catch(NumberFormatException e){
            Logger.getLogger(RefreshUserData.class.getName()).log(Level.SEVERE, null, e);
        }
        return vresult;
    }
    //处理修改地址的操作逻辑
    public static JSONObject changeAddress(String account,String address) 
            throws ServletException{
        //定义变量
        JSONObject vresult = new JSONObject();       //验证结果
        Map<String, String> data = new HashMap<>();  //返回的用户数据
        try {
            //密码验证结果
            Boolean verifyResult = verifyRefresh(account);      //验证用户id是否已经存在
            if(verifyResult)
            {
                //将新的地址写入数据库
                int schoolResult = UserDao.refreshUserByAccount(account, "Uaddress", address);  
                //验证是否修改成功
                if (schoolResult > 0) {
                    vresult.put("code", "1");   //修改成功
                    data.put("address", address);
                    vresult.put("data", data);  
                } else {
                    vresult.put("code", "0");   //修改失败
                }
            }
            else{
               vresult.put("code", "0"); //修改失败
            }
        }catch(NumberFormatException e){
            Logger.getLogger(RefreshUserData.class.getName()).log(Level.SEVERE, null, e);
        }
        return vresult;
    }
    //处理充值按钮发送上来的数据并返回处理结果
    public static JSONObject Charge(String account,String balance) 
            throws ServletException{
        //定义变量
        JSONObject vresult = new JSONObject();
        double newbalance;
        double addbalance;
        double nowbalance;
        Map<String, String> data = new HashMap<>();
        try {
            //密码验证结果
            Boolean verifyResult = verifyRefresh(account);      //验证用户id是否已经存在
            if(verifyResult)
            {
                //从数据库中获取用户信息
                User user = UserDao.queryUserAccount(account);
                //获取用户余额
                nowbalance = user.getBalance();
                //充值的金额
                addbalance = Double.valueOf(balance);
                //计算出充值之后的金额
                newbalance = nowbalance + addbalance;
                //将修改之后的数据写入数据库
                int balanceResult = UserDao.refreshUserByAccount(account, "Ubalance", String.valueOf(newbalance));  
                //验证金额是否修改成功
                if (balanceResult > 0) {
                    //重新从数据库获取数据
                    User userbalance = UserDao.queryUserAccount(account);
                    //获取用户余额
                    nowbalance = userbalance.getBalance();
                    vresult.put("code", "1");   //修改成功
                    data.put("balance", String.valueOf(nowbalance));
                    vresult.put("data", data);  
                } else {
                    vresult.put("code", "0");   //修改失败
                }
            }
            else{
               vresult.put("code", "0"); //修改失败
            }
        }catch(NumberFormatException e){
            Logger.getLogger(RefreshUserData.class.getName()).log(Level.SEVERE, null, e);
        }
        return vresult;
    }
    //处理提现按钮发送上来的数据并返回处理结果
    public static JSONObject TiXian(String account,String balance) 
            throws ServletException{
        JSONObject vresult = new JSONObject();
        double newbalance;
        double downbalance;
        double nowbalance;
        Map<String, String> data = new HashMap<>();
        try {
            //密码验证结果
            Boolean verifyResult = verifyRefresh(account);      //验证用户id是否已经存在
            if(verifyResult)
            {
                //查询用户信息
                User user = UserDao.queryUserAccount(account);
                //获取用户当前的用户余额
                nowbalance = user.getBalance();
                //要提现的余额
                downbalance = Double.valueOf(balance);
                //计算出新的余额
                newbalance = nowbalance - downbalance;
                //将新的余额信息存入数据库并验证金额是否修改成功
                int balanceResult = UserDao.refreshUserByAccount(account, "Ubalance", String.valueOf(newbalance)); 
                //判断是否修改成功
                if (balanceResult > 0) {
                    //重新从数据库获取用户数据
                    User userbalance = UserDao.queryUserAccount(account);
                    //提取出用户现在的余额
                    nowbalance = userbalance.getBalance();
                    //修改成功
                    vresult.put("code", "1");   
                    data.put("balance", String.valueOf(nowbalance));
                    vresult.put("data", data);  
                } else {
                    vresult.put("code", "0");   //修改失败
                }
            }
            else{
               vresult.put("code", "0"); //修改失败
            }
        }catch(NumberFormatException e){
            Logger.getLogger(RefreshUserData.class.getName()).log(Level.SEVERE, null, e);
        }
        return vresult;
    }
    //修改密码并返回处理结果
    public static JSONObject ModifyPwd(String account,String password,String newpassword) 
            throws ServletException{
        //定义变量
        JSONObject vresult = new JSONObject();
        Map<String, String> data = new HashMap<>();
        try {
            //密码验证结果
            Boolean verifyResult = verifyRefresh(account);      //验证用户账号是否已经存在
            if(verifyResult)
            {
                if(HXUtil.updatePassword(account, newpassword)){
                    int modify = UserDao.refreshUserByAccount(account, "Upwd", newpassword.trim());
                    if (modify>0) {
                        vresult.put("code", "1");   //密码修改成功
                        data.put("password", newpassword.trim());
                        vresult.put("data", data);
                    } else {
                        vresult.put("code", "0");   //密码修改失败
                    }
                }else vresult.put("code", "0");   //密码修改失败 
            }else{ 
                vresult.put("code", "0"); //密码修改失败
               }
        }catch(Exception e){
            Logger.getLogger(RefreshUserData.class.getName()).log(Level.SEVERE, null, e);
        }
        return vresult;
    }
     /**
     * 验证用户名是否存在
     *
     * @param account
     */
    private static Boolean verifyRefresh(String account) {
        User user = UserDao.queryUserAccount(account);
        //账户密码验证
        return user != null;
    }
}
