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
import net.Implementist.Users.Signup;
import net.sf.json.JSONObject;

/**
 *
 * @author dell
 */
public class Signup_Servlet extends HttpServlet{
    /*
    处理POST请求
    */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 设置响应内容类型  
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        try {
            JSONObject suresult = new JSONObject();
            String account = request.getParameter("account").trim();
            String pwd = request.getParameter("password").trim();
            String nickname = request.getParameter("nickname").trim();
            String tel = request.getParameter("tel").trim();
            int sex = Integer.valueOf(request.getParameter("sex").trim());
            suresult = Signup.signupVerify(account,pwd,nickname,tel,sex); 
            out.write(suresult.toString());
        }catch(ServletException e){
            Logger.getLogger(Signup_Servlet.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    /*
    处理GET请求
    */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}