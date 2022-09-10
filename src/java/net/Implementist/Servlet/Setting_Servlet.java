/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.Implementist.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.Implementist.Dao.UserDao;
import net.Implementist.Users.Login;
import net.Implementist.Users.RefreshUserData;
import net.sf.json.JSONObject;

/**
 *
 * @author dell
 * 处理SettingActivity中请求的Servlet
 */
public class Setting_Servlet extends HttpServlet {
@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //定义变量
        String account,password,newpassword;
        // 设置响应内容类型  
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        //获得请求头
        String requesttop = request.getParameter("requesttop").trim();
        switch (requesttop)
        {
            case "logout":
                int rs = 0;
                JSONObject loginoutResult = new JSONObject();
                account = request.getParameter("account").trim();
                rs = UserDao.refreshUserByAccount(account, "Ustate", "1");  //把用户状态改成1
                if(rs > 0){
                    loginoutResult.put("code", "1");
                }else{
                    loginoutResult.put("code", "0");
                }
                out.write(loginoutResult.toString());
                break;
            case "modifypwd":                          //修改密码
                JSONObject pwdResult = new JSONObject(); 
                account = request.getParameter("account").trim();
                password = request.getParameter("pwd").trim();
                newpassword = request.getParameter("newpwd").trim();
                pwdResult = RefreshUserData.ModifyPwd(account,password,newpassword);
                out.write(pwdResult.toString());
                pwdResult.clear();
                break;
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
