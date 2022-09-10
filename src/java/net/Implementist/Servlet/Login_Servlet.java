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
import net.Implementist.Users.Login;
import net.sf.json.JSONObject;

/**
 * 处理登录的http请求
 * @author dell
 */
public class Login_Servlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 设置响应内容类型  
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        try {
            JSONObject vresult = new JSONObject();
            //获取上传的账户密码
            String account = request.getParameter("account").trim();
            String password = request.getParameter("password").trim();
            //验证用户密码
            vresult = Login.Loginverify(account,password);
            //将验证结果返回
            out.write(vresult.toString());
        }catch(ServletException e){
            Logger.getLogger(Login_Servlet.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
