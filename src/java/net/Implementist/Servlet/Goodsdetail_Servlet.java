/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.Implementist.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.Implementist.DB.Account;
import net.Implementist.DB.Conments;
import net.Implementist.DB.Goods;
import net.Implementist.DB.Message;
import net.Implementist.DB.User;
import net.Implementist.Dao.AccountDao;
import net.Implementist.Dao.CollectDao;
import net.Implementist.Dao.ConmentsDao;
import net.Implementist.Dao.GoodsDao;
import net.Implementist.Dao.MessageDao;
import net.Implementist.Dao.UserDao;
import net.Implementist.Utils.HXUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * GoodsdetailActivity的Servlet
 * @author dell
 */
public class Goodsdetail_Servlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //定义变量
        String uid;
        Account account = new Account();
        Message message = new Message();
        User user = new User();
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
                case "getcollect":
                    JSONObject collect = new JSONObject();
                    uid = String.valueOf(UserDao.queryUserAccount(request.getParameter("account").trim()).getId());
                    collect = CollectDao.queryCollectInfo(request.getParameter("gid").trim(), uid);
                    if (collect == null) {
                        params.put("code", "0");   //商品未被收藏
                    } else {
                        params.put("code", "1");   //商品已被收藏
                        params.put("colid", collect.get("colid").toString());
                    }
                    out.write(params.toString());
                    break;
                case "collect":
                    uid = String.valueOf(UserDao.queryUserAccount(request.getParameter("account").trim()).getId());
                    int result = CollectDao.insertCollect(request.getParameter("gid").trim(), uid,request.getParameter("uid").trim(), request.getParameter("time").trim());
                    if (result <= 0) {
                        params.put("code", "0");   //收藏失败
                    } else {
                        params.put("code", "1");   //收藏成功
                    }
                    out.write(params.toString());
                    break;
                case "uncollect":
                    int unresult = CollectDao.DeleteCollect(request.getParameter("colid").trim());
                    if (unresult <= 0) {
                        params.put("code", "0");   //取消收藏失败
                    } else {
                        params.put("code", "1");   //取消收藏成功
                    }
                    out.write(params.toString());
                    break;
                case "conments":
                    user = UserDao.queryUserAccount(request.getParameter("account").trim());
                    Conments con = new Conments();
                    con.setGid(Integer.valueOf(request.getParameter("gid").trim()));
                    con.setUid(user.getId());
                    con.setGuid(Integer.valueOf(request.getParameter("uid").trim()));
                    con.setConcontent(request.getParameter("concontent").trim());
                    con.setContime(request.getParameter("contime").trim());
                    con.setConstate(Integer.valueOf(request.getParameter("constate").trim()));
                    int conresult = ConmentsDao.insertGoodsCon(con);
                    if (conresult <= 0) {
                        params.put("code", "0");   //评论失败
                    } else {
                        params.put("code", "1");   //评论成功
                        if(user.getId() != Integer.valueOf(request.getParameter("uid").trim())){
                            message.setMtitle("【评论通知】");
                            message.setMcontent("您的商品【" + request.getParameter("gname").trim() + "】有一条新评论！");
                            message.setReceiveid(Integer.valueOf(request.getParameter("uid").trim()));
                            message.setMtime(request.getParameter("contime").trim());
                            message.setMstate(1);
                            MessageDao.insertMessage(message);
                        }
                    }
                    out.write(params.toString());
                    break;
                case "getconments":
                    conmentslist = ConmentsDao.queryGidConments(request.getParameter("gid").trim());
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
                    int delresult = ConmentsDao.RefreshConments(request.getParameter("conid").trim(), "Constate", "2");
                    if (delresult <= 0) {
                        params.put("code", "0");   //删除评论失败
                    } else {
                        params.put("code", "1");   //删除评论成功
                    }
                    out.write(params.toString());
                    break;
                case "buygoods":
                    //获取系统时间
                    final Date d = new Date();
                    final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                    String useraccount = request.getParameter("account").trim();
                    //查询买家数据
                    user = UserDao.queryUserAccount(useraccount);
                    //根据买家id+卖家id+商品id+系统时间的形式形成订单号
                    String anumber = user.getId() + request.getParameter("guid").trim() + request.getParameter("gid").trim() + sdf.format(d);
                    //设置订单数据
                    account.setAnumber(anumber);
                    account.setGid(Integer.valueOf(request.getParameter("gid").trim()));
                    account.setUid(user.getId());
                    account.setGuid(Integer.valueOf(request.getParameter("guid").trim()));
                    account.setAbill(Double.valueOf(request.getParameter("abill").trim()));
                    account.setAtime(request.getParameter("atime").trim());
                    account.setAstate(Integer.valueOf(request.getParameter("astate").trim()));
                    //存储订单数据，并获取返回结果
                    int accountrs = AccountDao.insertAccount(account);
                    //计算买家剩余金额并修改买家余额
                    double balance = user.getBalance() - Double.valueOf(request.getParameter("abill").trim());
                    int userrs = UserDao.refreshUserByAccount(user.getAccount(), "Ubalance", String.valueOf(balance));
                    //修改商品状态，将商品状态改成交易中
                    int goodsrs = GoodsDao.RefreshGoods(request.getParameter("gid").trim(), "Gstate", "2");
                    //根据返回结果判断数据是否修改成功
                    if(accountrs > 0 && userrs > 0 && goodsrs > 0){
                        params.put("code", "1");
                        params.put("balance", String.valueOf(balance));
                        message.setMtitle("【商品售出】");
                        message.setMcontent("您的商品【" + request.getParameter("gname").trim() + "】已有人拍下，请及时发货！");
                        message.setReceiveid(Integer.valueOf(request.getParameter("guid").trim()));
                        message.setMtime(request.getParameter("atime").trim());
                        message.setMstate(1);
                        MessageDao.insertMessage(message);
                        System.out.println("交易成功！");
                    }else{
                        params.put("code", "0");
                        System.out.println("交易失败！");
                    }
                    out.write(params.toString());
                    break;
                case "setscannum":
                    Goods goods = GoodsDao.queryGid(request.getParameter("gid").trim());
                    goods.setGscannum(goods.getGscannum() + 1);
                    int scanrs = GoodsDao.RefreshGoods(String.valueOf(goods.getId()),"Gscannum",String.valueOf(goods.getGscannum()));
                    if (scanrs <= 0) {
                        params.put("code", "0");   //修改失败
                    } else {
                        params.put("code", "1");   //修改成功
                        params.put("data", String.valueOf(goods.getGscannum()));
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
