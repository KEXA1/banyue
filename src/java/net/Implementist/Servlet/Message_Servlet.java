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
import net.Implementist.Dao.MessageDao;
import net.Implementist.Dao.UserDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *
 * @author dell
 */
public class Message_Servlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String mid;
        String state;
        JSONObject params = new JSONObject();
        JSONArray messagelist = new JSONArray();
        JSONArray unreadlist = new JSONArray();
        JSONObject userdata = new JSONObject();      //用户数据
        // 设置响应内容类型  
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        try {
            //获得请求头
            String requesttop = request.getParameter("requesttop").trim();
            switch (requesttop)
            {
                case "getmessage":
                    userdata = UserDao.queryUserReObject(request.getParameter("account").trim());
                    messagelist = MessageDao.queryMessageData(userdata.getString("uid"),1);
                    if(messagelist.size() > 0){        //查询到有消息返回1
                        params.put("code", "1");
                        params.put("data", messagelist);
                    }else if(messagelist.isEmpty()){     //查询到无消息
                        params.put("code", "2");
                    }else params.put("code", "0");          //查询出错
                    out.write(params.toString());
                break;
                case "changemessage":                        //修改消息状态
                    mid = request.getParameter("mid").trim();
                    state = request.getParameter("state").trim();
                    int rs = MessageDao.refreshMessage(mid, "Mstate", state);
                    if(rs>0){
                        params.put("code", "1");              //修改成功
                    }else params.put("code", "0");            //修改失败
                    out.write(params.toString());
                break;
                case "getunread":                        //修改消息状态
                    userdata = UserDao.queryUserReObject(request.getParameter("account").trim());
                    unreadlist = MessageDao.queryMessageData(userdata.getString("uid"),2);
                    if(!unreadlist.isEmpty()){
                        params.put("code", "1");              //有未读消息
                    }else params.put("code", "0");            //没有未读消息
                    out.write(params.toString());
                break;
                default:System.out.println("商品发布失败！");break;
            }
        }catch(Exception e){
            Logger.getLogger(Message_Servlet.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
