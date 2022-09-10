/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.Implementist.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.Implementist.DB.Account;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import java.util.logging.Logger;
import net.Implementist.DB.User;
import net.Implementist.Dao.CollectDao;
import net.Implementist.Dao.GoodsDao;
import net.Implementist.Dao.UserDao;

/**
 * MycollectActivity的Servlet
 * @author dell
 */
public class Mycollect_Servlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //定义变量
        JSONArray collectlist = new JSONArray();    //收藏列表
        JSONObject params = new JSONObject();        //返回数据
        JSONObject userdata = new JSONObject();      //用户数据
        JSONObject gacdata = new JSONObject();       //商品和收藏数据 
        JSONArray gaclist = new JSONArray();         //商品和收藏数据列表
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
                case "getcollect":
                    User user = UserDao.queryUserAccount(request.getParameter("account").trim());
                    collectlist = CollectDao.queryCollectList(String.valueOf(user.getId()));
                    if(!collectlist.isEmpty()){
                        params.put("code", "1");           //收藏不为空
                        for(int i=0;i<collectlist.size();i++){
                            goodsdata = GoodsDao.qGoodsState(collectlist.getJSONObject(i).getString("gid"));
                            userdata = UserDao.queryUidReObject(collectlist.getJSONObject(i).getString("guid"));
                            gacdata.put("collectdata", collectlist.getJSONObject(i));
                            gacdata.put("goodsdata", goodsdata);
                            gacdata.put("userdata", userdata);
                            gaclist.add(gacdata);
                        }
                        params.put("data", gaclist);
                    }else params.put("code", "0");       //收藏为空
                    out.write(params.toString());
                    break;
                default:System.out.println("收藏信息返回失败！");break;
            }
        }catch(NumberFormatException e){
            Logger.getLogger(Mycollect_Servlet.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
