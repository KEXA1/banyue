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
import net.Implementist.Dao.GoodsDao;
import net.Implementist.Dao.UserDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * f1fragment的Servlet
 * @author dell
 */
public class F1Fragment_Servlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //定义变量
        JSONArray goodslist = new JSONArray();
        JSONObject params = new JSONObject();
        JSONObject goodsdata = new JSONObject();
        JSONObject userdata = new JSONObject();
        JSONObject uagdata = new JSONObject();
        JSONArray uaglist = new JSONArray();
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
                case "goodsinfo":
                    goodslist = GoodsDao.queryEmergent("1",request.getParameter("state").trim());
                    if(!goodslist.isEmpty()){
                        for(int i=0;i<goodslist.size();i++){
                            goodsdata = goodslist.getJSONObject(i);
                            userdata = UserDao.queryUidReObject(String.valueOf(goodsdata.get("uid")));
                            uagdata.put("userdata", userdata);
                            uagdata.put("goodsdata", goodsdata);
                            uaglist.add(uagdata);
                        }
                        params.put("code", "1");
                        params.put("data", uaglist);
                        System.out.println("猜你喜欢请求成功！");
                    }else{
                        params.put("code", "0");             //请求数据为空
                    }
                    out.write(params.toString());
                break;
                case "localgoods":
                    goodslist = GoodsDao.queryLocation(request.getParameter("location").trim(),"1");
                    if(!goodslist.isEmpty()){
                        for(int i=0;i<goodslist.size();i++){
                            goodsdata = goodslist.getJSONObject(i);
                            userdata = UserDao.queryUidReObject(String.valueOf(goodsdata.get("uid")));
                            uagdata.put("userdata", userdata);
                            uagdata.put("goodsdata", goodsdata);
                            uaglist.add(uagdata);
                        }
                        params.put("code", "1");
                        params.put("data", uaglist);
                        System.out.println("附近商品请求成功！");
                    }else {
                        params.put("code", "1");               //请求数据为空
                    }
                    out.write(params.toString());
                break;
                case "goodsji":
                    goodslist = GoodsDao.queryThreeEmergent("2",request.getParameter("state").trim());
                    if(!goodslist.isEmpty()){
                        for(int i=0;i<goodslist.size();i++){
                            goodsdata = goodslist.getJSONObject(i);
                            userdata = UserDao.queryUidReObject(String.valueOf(goodsdata.get("uid")));
                            uagdata.put("userdata", userdata);
                            uagdata.put("goodsdata", goodsdata);
                            uaglist.add(uagdata);
                        }
                        params.put("code", "1");
                        params.put("data", uaglist);
                        System.out.println("急售请求成功！");
                    }else {
                        params.put("code", "0");            //请求数据为空
                    }
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
