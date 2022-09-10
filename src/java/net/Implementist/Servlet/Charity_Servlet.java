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
import net.Implementist.DB.Charity;
import net.Implementist.Dao.BuyDao;
import net.Implementist.Dao.CharityDao;
import net.Implementist.Dao.GoodsDao;
import net.Implementist.Dao.JoincharityDao;
import net.Implementist.Dao.UserDao;
import net.Implementist.Utils.HXUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *
 * @author dell
 */
public class Charity_Servlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //定义变量
        String cid;                                 //公益id
        String uid;
        JSONArray publishlist = new JSONArray();    //发布列表
        JSONArray joinlist = new JSONArray();    //参加列表
        JSONObject params = new JSONObject();        //返回数据
        JSONObject userdata = new JSONObject();      //用户数据
        JSONObject caudata = new JSONObject();       //用户和公益数据 
        JSONArray caulist = new JSONArray();         //用户和公益数据列表
        JSONObject charitydata = new JSONObject();
        
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
                case "getmypubcharity":
                    userdata = UserDao.queryUserReObject(request.getParameter("account").trim());
                    publishlist = CharityDao.queryMypublish(userdata.getString("uid"));
                    if(!publishlist.isEmpty()){
                        params.put("code", "1");           //我的发布不为空
                        for(int i=0;i<publishlist.size();i++){
                            caudata.put("charitydata", publishlist.getJSONObject(i));
                            caudata.put("userdata", userdata);
                            caulist.add(caudata);
                        }
                        params.put("data", caulist);
                    }else params.put("code", "0");       //没有发布公益活动
                    out.write(params.toString());
                    break;
                case "getmypartcharity":
                    userdata = UserDao.queryUserReObject(request.getParameter("account").trim());
                    joinlist = JoincharityDao.queryJoinList(userdata.getString("uid"));
                    if(!joinlist.isEmpty()){
                        params.put("code", "1");           //我参加的公益不为空
                        for(int i=0;i<joinlist.size();i++){
                            charitydata = CharityDao.queryCharityByCid(joinlist.getJSONObject(i).getString("cid"));
                            caudata.put("charitydata", charitydata);
                            userdata = UserDao.queryUidReObject(joinlist.getJSONObject(i).getString("uid"));
                            caudata.put("userdata", userdata);
                            caulist.add(caudata);
                        }
                        params.put("data", caulist);
                    }else params.put("code", "0");       //没有发布公益活动
                    out.write(params.toString());
                    break;
                case "getcharity":
                    publishlist = CharityDao.queryCharity(request.getParameter("cstate").trim());
                    if(!publishlist.isEmpty()){
                        params.put("code", "1");           //公益列表不为空
                        for(int i=0;i<publishlist.size();i++){
                            charitydata = publishlist.getJSONObject(i);
                            userdata = UserDao.queryUidReObject(String.valueOf(charitydata.get("uid")));
                            caudata.put("charitydata", charitydata);
                            caudata.put("userdata", userdata);
                            caulist.add(caudata);
                        }
                        params.put("data", caulist);
                    }else params.put("code", "0");       //没有发布公益活动
                    out.write(params.toString());
                    break;
                case "joincharity":
                    userdata = UserDao.queryUserReObject(request.getParameter("account").trim());
                    if(!JoincharityDao.verifyJoin(userdata.getString("uid"), request.getParameter("cid").trim())){
                        int rs = JoincharityDao.insertJoin(request.getParameter("cid").trim(),userdata.getString("uid"),
                                request.getParameter("cuid"),request.getParameter("jtime"));
                        Charity charity = CharityDao.queryCid(request.getParameter("cid").trim());
                        int insertrs = CharityDao.RefreshCharity(String.valueOf(charity.getId()), "Cjoinnum", String.valueOf(charity.getCjoinnum()+1));
                        if(rs > 0 && insertrs > 0){
                            params.put("code", "1");           //数据插入成功
                            params.put("joinnum", charity.getCjoinnum() + 1);           //返回参加人数
                        }else params.put("code", "0");           //数据插入失败
                    }else params.put("code", "2");       //用户已加入
                    out.write(params.toString());
                    break;
                case "deletecharity":
                    int result = CharityDao.RefreshCharity(request.getParameter("cid").trim(),"Cstate","3");
                    if(result > 0){
                        params.put("code", "1");           //已删除
                    }else params.put("code", "0");       //删除失败
                    out.write(params.toString());
                    break;
                case "send":
                    String message = "#商品售出#您的商品111已被拍下，请及时发货！#";
                    boolean a = HXUtil.sendToUser("admin",request.getParameter("hxid").trim().trim(),message);
                    if(a){
                        params.put("code", "1"); 
                    }else params.put("code", "0"); 
                    out.write(params.toString());
                    break;
                case "setscannum":
                    Charity charity = CharityDao.queryCid(request.getParameter("cid").trim());
                    charity.setCscannum(charity.getCscannum() + 1);
                    int scanrs = CharityDao.RefreshCharity(String.valueOf(charity.getId()),"Cscannum",String.valueOf(charity.getCscannum()));
                    if (scanrs <= 0) {
                        params.put("code", "0");   //修改失败
                    } else {
                        params.put("code", "1");   //修改成功
                        params.put("data", String.valueOf(charity.getCscannum()));
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
