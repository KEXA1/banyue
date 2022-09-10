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
import net.Implementist.DB.Account;
import net.Implementist.DB.Buy;
import net.Implementist.DB.Buyconments;
import net.Implementist.DB.Message;
import net.Implementist.DB.User;
import net.Implementist.Dao.BuyDao;
import net.Implementist.Dao.BuyconmentsDao;
import net.Implementist.Dao.MessageDao;
import net.Implementist.Dao.UserDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *
 * @author dell
 */
public class Buydetail_Servlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //定义变量
        User user = new User();
        Message message = new Message();
        JSONArray conmentslist = new JSONArray();    //评论列表
        JSONObject params = new JSONObject();        //返回数据
        JSONObject userdata = new JSONObject();      //用户数据
        JSONObject uacdata = new JSONObject();       //用户和评论数据 
        JSONArray uaclist = new JSONArray();         //用户和评论数据列表
        JSONObject conmentsdata = new JSONObject();
        
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
                case "conments":
                    user = UserDao.queryUserAccount(request.getParameter("account").trim());
                    Buyconments con = new Buyconments();
                    con.setBid(Integer.valueOf(request.getParameter("bid").trim()));
                    con.setUid(user.getId());
                    con.setBuid(Integer.valueOf(request.getParameter("uid").trim()));
                    con.setBconcontent(request.getParameter("bconcontent").trim());
                    con.setBcontime(request.getParameter("bcontime").trim());
                    con.setBconstate(Integer.valueOf(request.getParameter("bconstate").trim()));
                    int conresult = BuyconmentsDao.insertBuyConments(con);
                    if (conresult <= 0) {
                        params.put("code", "0");   //评论失败
                    } else {
                        params.put("code", "1");   //评论成功
                        if(user.getId() != Integer.valueOf(request.getParameter("uid").trim())){
                            message.setMtitle("【评论通知】");
                            message.setMcontent("您的求购商品【" + request.getParameter("bname").trim() + "】有一条新评论！");
                            message.setReceiveid(Integer.valueOf(request.getParameter("uid").trim()));
                            message.setMtime(request.getParameter("bcontime").trim());
                            message.setMstate(1);
                            MessageDao.insertMessage(message);
                        }
                    }
                    out.write(params.toString());
                    break;
                case "getconments":
                    conmentslist = BuyconmentsDao.queryBidConments(request.getParameter("bid").trim());
                    if (conmentslist.isEmpty()) {
                        params.put("code", "0");   //没有评论
                    } else {
                        params.put("code", "1");   //有评论，并把评论列表写进返回的数据里
                        for(int i=0;i < conmentslist.size();i++){
                            conmentsdata = conmentslist.getJSONObject(i);
                            userdata = UserDao.queryUidReObject(String.valueOf(conmentsdata.get("uid")));
                            uacdata.put("userdata", userdata);
                            uacdata.put("conmentsdata", conmentsdata);
                            uaclist.add(uacdata);
                        }
                        params.put("data", uaclist);
                    }
                    out.write(params.toString());
                    break;
                case "deletecomments":
                    int delresult = BuyconmentsDao.RefreshConments(request.getParameter("bconid").trim(), "Bconstate", "2");
                    if (delresult <= 0) {
                        params.put("code", "0");   //删除评论失败
                    } else {
                        params.put("code", "1");   //删除评论成功
                    }
                    out.write(params.toString());
                    break;
                case "setscannum":
                    Buy buy = BuyDao.queryBid(request.getParameter("bid").trim());
                    buy.setBscannum(buy.getBscannum() + 1);
                    int scanrs = BuyDao.RefreshBuy(String.valueOf(buy.getId()),"Bscannum",String.valueOf(buy.getBscannum()));
                    if (scanrs <= 0) {
                        params.put("code", "0");   //修改失败
                    } else {
                        params.put("code", "1");   //修改成功
                        params.put("data", String.valueOf(buy.getBscannum()));
                    }
                    out.write(params.toString());
                    break;
                default:System.out.println("信息返回失败！");break;
            }
        }catch(NumberFormatException e){
            Logger.getLogger(F1Fragment_Servlet.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
