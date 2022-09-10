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
import net.Implementist.Dao.BuyDao;
import net.Implementist.Dao.GoodsDao;
import net.Implementist.Dao.UserDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * MypublishActivity的Servlet
 * @author dell
 */
public class Mypublish_Servlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //定义变量
        String gid;                                 //商品id
        JSONArray publishlist = new JSONArray();    //发布列表
        JSONObject params = new JSONObject();        //返回数据
        JSONObject userdata = new JSONObject();      //用户数据
        JSONObject gaudata = new JSONObject();       //用户和商品数据 
        JSONArray gaulist = new JSONArray();         //用户和商品数据列表
        JSONObject baudata = new JSONObject();       //用户和求购数据 
        JSONArray baulist = new JSONArray();         //用户和求购数据列表
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
                case "getmypubgoods":
                    userdata = UserDao.queryUserReObject(request.getParameter("account").trim());
                    publishlist = GoodsDao.queryMypublish(userdata.getString("uid"), "1");
                    if(!publishlist.isEmpty()){
                        params.put("code", "1");           //我的发布不为空
                        for(int i=0;i<publishlist.size();i++){
                            gaudata.put("goodsdata", publishlist.getJSONObject(i));
                            gaudata.put("userdata", userdata);
                            gaulist.add(gaudata);
                        }
                        params.put("data", gaulist);
                    }else params.put("code", "0");       //没有发布商品
                    out.write(params.toString());
                    break;
                case "deletegoods":
                    int deleters = GoodsDao.RefreshGoods(request.getParameter("gid").trim(), "Gstate", "5");
                    if (deleters <= 0) {
                        params.put("code", "0");   //删除失败
                    } else {
                        params.put("code", "1");   //删除成功
                    }
                    out.write(params.toString());
                    break;
                case "getmypubbuy":
                    userdata = UserDao.queryUserReObject(request.getParameter("account").trim());
                    publishlist = BuyDao.queryMypubbuy(userdata.getString("uid"), "1");
                    if(!publishlist.isEmpty()){
                        params.put("code", "1");           //我的发布不为空
                        for(int i=0;i<publishlist.size();i++){
                            baudata.put("buydata", publishlist.getJSONObject(i));
                            baudata.put("userdata", userdata);
                            baulist.add(baudata);
                        }
                        params.put("data", baulist);
                    }else params.put("code", "0");       //没有发布
                    out.write(params.toString());
                    break;
                case "deletebuy":
                    int deletebuyrs = BuyDao.RefreshBuy(request.getParameter("bid").trim(), "Bstate", "3");
                    if (deletebuyrs <= 0) {
                        params.put("code", "0");   //删除失败
                    } else {
                        params.put("code", "1");   //删除成功
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
