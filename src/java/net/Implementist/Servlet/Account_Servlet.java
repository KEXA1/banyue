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
import net.Implementist.DB.Message;
import net.Implementist.DB.User;
import net.Implementist.Dao.AccountDao;
import net.Implementist.Dao.GoodsDao;
import net.Implementist.Dao.MessageDao;
import net.Implementist.Dao.UserDao;
import net.Implementist.Utils.HXUtil;
import net.sf.json.JSONObject;

/**
 * AccountActivity的Servlet
 * @author dell
 */
public class Account_Servlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //定义变量
        User user = new User();
        Message message = new Message();
        JSONObject params = new JSONObject();        //返回数据
        
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
                case "cancleaccount":
                    user = UserDao.queryUid(request.getParameter("uid").trim());
                    int canresult = AccountDao.RefreshAccount(request.getParameter("aid").trim(), "Astate", "5");
                    //计算买家剩余金额并修改买家余额
                    double balance = user.getBalance() + Double.valueOf(request.getParameter("abill").trim());
                    int userrs = UserDao.refreshUserByAccount(user.getAccount(), "Ubalance", String.valueOf(balance));
                    //修改商品状态，将商品状态改成在售状态
                    int goodsrs = GoodsDao.RefreshGoods(request.getParameter("gid").trim(), "Gstate", "1");
                    if ((canresult > 0) && (userrs > 0) && (goodsrs > 0)) {
                        params.put("code", "1");   //取消订单失败
                        message.setMtitle("【取消订单】");
                        message.setMcontent("商品【" + request.getParameter("gname").trim() + "】订单已取消，请与对方联系！");
                        message.setReceiveid(Integer.valueOf(request.getParameter("uid").trim()));
                        message.setMtime(request.getParameter("time").trim());
                        message.setMstate(1);
                        MessageDao.insertMessage(message);
                    } else {
                        params.put("code", "0");   //取消订单成功
                    }
                    out.write(params.toString());
                    break;
                case "affrimsend":
                    int sendresult = AccountDao.RefreshAccount(request.getParameter("aid").trim(), "Astate", "2");
                    if(sendresult > 0){
                        params.put("code", "1");   //发货成功
                        message.setMtitle("【发货通知】");
                        message.setMcontent("您购买的商品【" + request.getParameter("gname").trim() + "】已发货！");
                        message.setReceiveid(Integer.valueOf(request.getParameter("uid").trim()));
                        message.setMtime(request.getParameter("time").trim());
                        message.setMstate(1);
                        MessageDao.insertMessage(message);
                    } else {
                        params.put("code", "0");   //发货失败
                    }
                    out.write(params.toString());
                    break;
                case "affrimget":
                    user = UserDao.queryUid(request.getParameter("uid").trim());
                    int getresult = AccountDao.RefreshAccount(request.getParameter("aid").trim(), "Astate", "3");
                    //计算卖家剩余金额并修改卖家余额
                    double getbalance = user.getBalance() + Double.valueOf(request.getParameter("abill").trim());
                    int userresult = UserDao.refreshUserByAccount(user.getAccount(), "Ubalance", String.valueOf(getbalance));
                    //修改商品状态，将商品状态改成在售状态
                    int goodsresult = GoodsDao.RefreshGoods(request.getParameter("gid").trim(), "Gstate", "3");
                    if ((getresult > 0) && (userresult > 0) && (goodsresult > 0)) {
                        params.put("code", "1");   //确认收货成功
                        message.setMtitle("【收货通知】");
                        message.setMcontent("您售出的商品【" + request.getParameter("gname").trim() + "】买家已确认收货！");
                        message.setReceiveid(Integer.valueOf(request.getParameter("guid").trim()));
                        message.setMtime(request.getParameter("time").trim());
                        message.setMstate(1);
                        MessageDao.insertMessage(message);
                    } else {
                        params.put("code", "0");   //确认收货失败
                    }
                    out.write(params.toString());
                    break;
                default:System.out.println("信息返回失败！");break;
            }
        }catch(NumberFormatException e){
            Logger.getLogger(Mypublish_Servlet.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
