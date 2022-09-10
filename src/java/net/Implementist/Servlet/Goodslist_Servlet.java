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
 * goodslist的Servlet
 * @author dell
 */
public class Goodslist_Servlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //定义变量
        JSONArray goodslist = new JSONArray();
        JSONArray buylist = new JSONArray();
        JSONObject params = new JSONObject();
        JSONObject goodsdata = new JSONObject();
        JSONObject buydata = new JSONObject();
        JSONObject userdata = new JSONObject();
        JSONObject uagdata = new JSONObject();
        JSONObject uabdata = new JSONObject();
        JSONArray uaglist = new JSONArray();
        JSONArray uablist = new JSONArray();
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
                case "goodstype":
                    goodslist = GoodsDao.queryType(request.getParameter("type").trim(),request.getParameter("state").trim());
                    for(int i=0;i<goodslist.size();i++){
                        goodsdata = goodslist.getJSONObject(i);
                        userdata = UserDao.queryUidReObject(String.valueOf(goodsdata.get("uid")));
                        uagdata.put("userdata", userdata);
                        uagdata.put("goodsdata", goodsdata);
                        uaglist.add(uagdata);
                    }
                    params.put("code", "1");
                    params.put("data", uaglist);
                    System.out.println("商品类型请求成功！");
                    out.write(params.toString());
                break;
                case "goodsname":
                    goodslist = GoodsDao.queryGoodsMo(request.getParameter("name").trim(),request.getParameter("state").trim());
                    for(int i=0;i<goodslist.size();i++){
                        goodsdata = goodslist.getJSONObject(i);
                        userdata = UserDao.queryUidReObject(String.valueOf(goodsdata.get("uid")));
                        uagdata.put("userdata", userdata);
                        uagdata.put("goodsdata", goodsdata);
                        uaglist.add(uagdata);
                    }
                    params.put("code", "1");
                    params.put("data", uaglist);
                    System.out.println("商品名请求成功！");
                    out.write(params.toString());
                break;
                case "freegoods":
                    goodslist = GoodsDao.queryFree(request.getParameter("state").trim());
                    for(int i=0;i<goodslist.size();i++){
                        goodsdata = goodslist.getJSONObject(i);
                        userdata = UserDao.queryUidReObject(String.valueOf(goodsdata.get("uid")));
                        uagdata.put("userdata", userdata);
                        uagdata.put("goodsdata", goodsdata);
                        uaglist.add(uagdata);
                    }
                    params.put("code", "1");
                    params.put("data", uaglist);
                    System.out.println("商品名请求成功！");
                    out.write(params.toString());
                break;
                case "jigoods":
                    goodslist = GoodsDao.queryEmergent("2",request.getParameter("state").trim());
                    for(int i=0;i<goodslist.size();i++){
                        goodsdata = goodslist.getJSONObject(i);
                        userdata = UserDao.queryUidReObject(String.valueOf(goodsdata.get("uid")));
                        uagdata.put("userdata", userdata);
                        uagdata.put("goodsdata", goodsdata);
                        uaglist.add(uagdata);
                    }
                    params.put("code", "1");
                    params.put("data", uaglist);
                    System.out.println("商品名请求成功！");
                    out.write(params.toString());
                break;
                case "myfree":
                    userdata = UserDao.queryUserReObject(request.getParameter("account").trim());
                    goodslist = GoodsDao.queryMyfree(userdata.getString("uid"),request.getParameter("state").trim());
                    for(int i=0;i<goodslist.size();i++){
                        goodsdata = goodslist.getJSONObject(i);
                        uagdata.put("userdata", userdata);
                        uagdata.put("goodsdata", goodsdata);
                        uaglist.add(uagdata);
                    }
                    params.put("code", "1");
                    params.put("data", uaglist);
                    System.out.println("商品名请求成功！");
                    out.write(params.toString());
                break;
                case "goodsbuy":
                    buylist = BuyDao.queryBuy(request.getParameter("state").trim());
                    for(int i=0;i<buylist.size();i++){
                        buydata = buylist.getJSONObject(i);
                        userdata = UserDao.queryUidReObject(String.valueOf(buydata.get("uid")));
                        uabdata.put("userdata", userdata);
                        uabdata.put("buydata", buydata);
                        uablist.add(uabdata);
                    }
                    params.put("code", "1");
                    params.put("data", uablist);
                    System.out.println("请求成功！");
                    out.write(params.toString());
                break;
                default:System.out.println("商品信息返回失败！");break;
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
