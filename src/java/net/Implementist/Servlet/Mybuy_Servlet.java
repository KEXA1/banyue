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
import net.Implementist.DB.Buy;
import net.Implementist.DB.Goods;
import net.Implementist.Dao.AccountDao;
import net.Implementist.Dao.BuyDao;
import net.Implementist.Dao.GoodsDao;
import net.Implementist.Dao.UserDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *
 * @author dell
 */
public class Mybuy_Servlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //定义变量
        String gid;                                 //商品id
        JSONArray accountlist = new JSONArray();    //发布列表
        JSONObject params = new JSONObject();        //返回数据
        JSONObject userdata = new JSONObject();      //用户数据
        JSONObject gaudata = new JSONObject();       //用户和商品数据 
        JSONArray gaulist = new JSONArray();         //用户和商品数据列表
        JSONObject goodsdata = new JSONObject();
        
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
                case "getmybuy":               //我买到的
                    userdata = UserDao.queryUserReObject(request.getParameter("account").trim());
                    accountlist = AccountDao.queryUid(userdata.getString("uid"));
                    if(!accountlist.isEmpty()){
                        params.put("code", "1");           //我买到的不为空
                        userdata = null;                   //将userdata中的数据清空
                        for(int i=0;i<accountlist.size();i++){
                            goodsdata = GoodsDao.qGoodsState(accountlist.getJSONObject(i).getString("gid"));
                            userdata = UserDao.queryUidReObject(accountlist.getJSONObject(i).getString("guid"));
                            gaudata.put("accountdata", accountlist.getJSONObject(i));
                            gaudata.put("goodsdata", goodsdata);
                            gaudata.put("userdata", userdata);
                            gaulist.add(gaudata);
                        }
                        params.put("data", gaulist);
                    }else params.put("code", "0");       //没有发布商品
                    out.write(params.toString());
                    break;
                case "getmyout":              //我卖出的
                    userdata = UserDao.queryUserReObject(request.getParameter("account").trim());
                    accountlist = AccountDao.queryGuid(userdata.getString("uid"));
                    if(!accountlist.isEmpty()){
                        params.put("code", "1");           //我卖出的不为空
                        userdata = null;                   //将userdata中的数据清空
                        for(int i=0;i<accountlist.size();i++){
                            goodsdata = GoodsDao.qGoodsState(accountlist.getJSONObject(i).getString("gid"));
                            userdata = UserDao.queryUidReObject(accountlist.getJSONObject(i).getString("uid"));
                            gaudata.put("accountdata", accountlist.getJSONObject(i));
                            gaudata.put("goodsdata", goodsdata);
                            gaudata.put("userdata", userdata);
                            gaulist.add(gaudata);
                        }
                        params.put("data", gaulist);
                    }else params.put("code", "0");       //没有发布商品
                    out.write(params.toString());
                    break;
                case "deleteaccount":
                    int delresult = AccountDao.RefreshAccount(request.getParameter("aid").trim(), "Astate", "6");
                    if (delresult <= 0) {
                        params.put("code", "0");   //删除订单失败
                    } else {
                        params.put("code", "1");   //删除订单成功
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
