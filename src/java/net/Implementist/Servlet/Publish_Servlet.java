/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.Implementist.Servlet;

import java.io.File;
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
import net.Implementist.DB.Goods;
import net.Implementist.DB.User;
import net.Implementist.Dao.BuyDao;
import net.Implementist.Dao.CharityDao;
import net.Implementist.Dao.GoodsDao;
import net.Implementist.Dao.UserDao;
import static net.Implementist.Utils.ImageDeal.string2Image;
import net.sf.json.JSONObject;

/**
 * 处理发布activity的servlet
 * @author dell
 */
public class Publish_Servlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //定义变量
        String Imageurl;
        String Imagestr;
        String Imagename;
        boolean imageResult;
        User user = new User();
        Goods goods = new Goods();
        Buy buy = new Buy();
        Charity charity = new Charity();
        JSONObject params = new JSONObject();
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
                case "publish":
                    int goodsResult;
                    JSONObject emgrgentpublish = new JSONObject();
                    String apppath = request.getRealPath("/")+"appdata";
		    File appfile = new File(apppath);
		    if(!appfile.exists()){
			appfile.mkdir();
		    }
                    String goodsdata = apppath + "/" + "goodsdata";
                    File goodsfile = new File(goodsdata);
                    if(!goodsfile.exists()){
			goodsfile.mkdir();
		    }
                    user = UserDao.queryUserAccount(request.getParameter("uaccount").trim());
                    goods.setUid(user.getId()); 
                    goods.setGname(request.getParameter("gname").trim());
                    goods.setGdetail(request.getParameter("gdetail").trim());
                    goods.setGprice(Double.valueOf(request.getParameter("gprice").trim()));
                    goods.setGoprice(Double.valueOf(request.getParameter("goprice").trim()));
                    goods.setGtype(request.getParameter("gtype").trim());
                    goods.setGhownew(request.getParameter("ghownew").trim());
                    goods.setGgetway(request.getParameter("ggetway").trim());
                    goods.setGemergent(Integer.valueOf(request.getParameter("gemergent").trim()));
                    goods.setGtime(request.getParameter("gtime").trim());
                    goods.setGstate(Integer.valueOf(request.getParameter("gstate").trim()));
                    goods.setGaddress(request.getParameter("gaddress").trim());
                    goods.setGscannum(Integer.valueOf(request.getParameter("gscannum").trim()));
                    Imagestr = request.getParameter("imagestr").trim();
                    Imagename = request.getParameter("imagename").trim();
                    Imageurl = goodsdata + "/" + Imagename;
                    goods.setGimage(Imageurl.trim());
                    imageResult = string2Image(Imagestr, Imageurl);
                    if(imageResult){
                        goodsResult = GoodsDao.insertGoods(goods);
                        if(goodsResult>0){
                            emgrgentpublish.put("code","1");
                            System.out.println("发布成功！");
                        }else{
                            emgrgentpublish.put("code","0");
                            System.out.println("发布失败！");
                        } 
                    }else{
                        emgrgentpublish.put("code","0");
                        System.out.println("发布失败！");
                    }
                    out.write(emgrgentpublish.toString());
                break;
                case "republish":
                    goods.setId(Integer.valueOf(request.getParameter("gid").trim()));
                    goods.setGname(request.getParameter("gname").trim());
                    goods.setGdetail(request.getParameter("gdetail").trim());
                    goods.setGprice(Double.valueOf(request.getParameter("gprice").trim()));
                    goods.setGoprice(Double.valueOf(request.getParameter("goprice").trim()));
                    goods.setGtype(request.getParameter("gtype").trim());
                    goods.setGhownew(request.getParameter("ghownew").trim());
                    goods.setGgetway(request.getParameter("ggetway").trim());
                    goods.setGemergent(Integer.valueOf(request.getParameter("gemergent").trim()));
                    goods.setGaddress(request.getParameter("gaddress").trim());
                    int rs = GoodsDao.RefreshGoodsData(goods);
                    if(rs <= 0){
                        params.put("code","0");               //修改失败
                    }else {
                        params.put("code","1");               //修改成功 
                    }
                    out.write(params.toString());
                    break;
                case "publishbuy":
                    JSONObject publishbuy = new JSONObject();
                    String buyapppath = request.getRealPath("/")+"appdata";
		    File buyappfile = new File(buyapppath);
		    if(!buyappfile.exists()){
			buyappfile.mkdir();
		    }
                    String buydata = buyapppath + "/" + "buydata";
                    File buyfile = new File(buydata);
                    if(!buyfile.exists()){
			buyfile.mkdir();
		    }
                    user = UserDao.queryUserAccount(request.getParameter("uaccount").trim());
                    buy.setUid(user.getId()); 
                    buy.setBname(request.getParameter("bname").trim());
                    buy.setBdetail(request.getParameter("bdetail").trim());
                    buy.setBsprice(Double.valueOf(request.getParameter("bsprice").trim()));
                    buy.setBbprice(Double.valueOf(request.getParameter("bbprice").trim()));
                    buy.setBtype(request.getParameter("btype").trim());
                    buy.setBhownew(request.getParameter("bhownew").trim());
                    buy.setBgetway(request.getParameter("bgetway").trim());
                    buy.setBtime(request.getParameter("btime").trim());
                    buy.setBstate(Integer.valueOf(request.getParameter("bstate").trim()));
                    buy.setBaddress(request.getParameter("baddress").trim());
                    buy.setBscannum(Integer.valueOf(request.getParameter("bscannum").trim()));
                    Imagestr = request.getParameter("imagestr").trim();
                    Imagename = request.getParameter("imagename").trim();
                    Imageurl = buydata + "/" + Imagename;
                    buy.setBimage(Imageurl.trim());
                    imageResult = string2Image(Imagestr, Imageurl);
                    if(imageResult){
                        int buyResult = BuyDao.insertBuy(buy);
                        if(buyResult>0){
                            publishbuy.put("code","1");
                            System.out.println("发布成功！");
                        }else{
                            publishbuy.put("code","0");
                            System.out.println("发布失败！");
                        } 
                    }else{
                        publishbuy.put("code","0");
                        System.out.println("发布失败！");
                    }
                    out.write(publishbuy.toString());
                    break;
                case "republishbuy":
                    buy.setId(Integer.valueOf(request.getParameter("bid").trim()));
                    buy.setBname(request.getParameter("bname").trim());
                    buy.setBdetail(request.getParameter("bdetail").trim());
                    buy.setBsprice(Double.valueOf(request.getParameter("bsprice").trim()));
                    buy.setBbprice(Double.valueOf(request.getParameter("bbprice").trim()));
                    buy.setBtype(request.getParameter("btype").trim());
                    buy.setBhownew(request.getParameter("bhownew").trim());
                    buy.setBgetway(request.getParameter("bgetway").trim());
                    buy.setBaddress(request.getParameter("baddress").trim());
                    int buyrs = BuyDao.RefreshBuyData(buy);
                    if(buyrs <= 0){
                        params.put("code","0");               //修改失败
                    }else {
                        params.put("code","1");               //修改成功 
                    }
                    out.write(params.toString());
                    break;
                case "publishcharity":
                    JSONObject publishcharity = new JSONObject();
                    String charityapppath = request.getRealPath("/")+"appdata";
		    File charityappfile = new File(charityapppath);
		    if(!charityappfile.exists()){
			charityappfile.mkdir();
		    }
                    String charitydata = charityapppath + "/" + "charitydata";
                    File charityfile = new File(charitydata);
                    if(!charityfile.exists()){
			charityfile.mkdir();
		    }
                    user = UserDao.queryUserAccount(request.getParameter("uaccount").trim());
                    charity.setUid(user.getId());
                    charity.setCname(request.getParameter("cname").trim());
                    charity.setCdetail(request.getParameter("cdetail").trim());
                    charity.setCneed(request.getParameter("cneed").trim());
                    charity.setCnumber(Integer.valueOf(request.getParameter("cnumber").trim()));
                    charity.setCtime(request.getParameter("ctime").trim());
                    charity.setCdeadline(request.getParameter("cdate").trim());
                    charity.setCtype(request.getParameter("cstyle").trim());
                    charity.setCstate(Integer.valueOf(request.getParameter("cstate").trim()));
                    charity.setCaddress(request.getParameter("caddress").trim());
                    charity.setCscannum(Integer.valueOf(request.getParameter("cscannum").trim()));
                    charity.setCjoinnum(Integer.valueOf(request.getParameter("cscannum").trim()));
                    Imagestr = request.getParameter("imagestr").trim();
                    Imagename = request.getParameter("imagename").trim();
                    Imageurl = charitydata + "/" + Imagename;
                    charity.setCimage(Imageurl.trim());
                    imageResult = string2Image(Imagestr, Imageurl);
                    if(imageResult){
                        int charityResult = CharityDao.insertCharity(charity);
                        if(charityResult>0){
                            publishcharity.put("code","1");
                            System.out.println("发布成功！");
                        }else{
                            publishcharity.put("code","0");
                            System.out.println("发布失败！");
                        } 
                    }else{
                        publishcharity.put("code","0");
                        System.out.println("发布失败！");
                    }
                    out.write(publishcharity.toString());
                    break;
                case "republishcharity":
                    charity.setId(Integer.valueOf(request.getParameter("cid").trim()));
                    charity.setCname(request.getParameter("cname").trim());
                    charity.setCdetail(request.getParameter("cdetail").trim());
                    charity.setCneed(request.getParameter("cneed").trim());
                    charity.setCnumber(Integer.valueOf(request.getParameter("cnumber").trim()));
                    charity.setCdeadline(request.getParameter("cdate").trim());
                    charity.setCtype(request.getParameter("cstyle").trim());
                    charity.setCaddress(request.getParameter("caddress").trim());
                    int charityrs = CharityDao.RefreshCharityData(charity);
                    if(charityrs <= 0){
                        params.put("code","0");               //修改失败
                    }else {
                        params.put("code","1");               //修改成功 
                    }
                    out.write(params.toString());
                    break;
                default:System.out.println("修改发布失败！");break;
            }
        }catch(NumberFormatException e){
            Logger.getLogger(Publish_Servlet.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
