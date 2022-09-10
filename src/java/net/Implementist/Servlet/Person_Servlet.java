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
import static net.Implementist.Utils.ImageDeal.string2Image;
import net.Implementist.Users.RefreshUserData;
import net.sf.json.JSONObject;

/**
 *
 * @author 处理PersonActivity中的请求
 */
public class Person_Servlet extends HttpServlet {
       @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //定义变量
        String account,imagestr,imagename,imagepath,nickname,sex,school,tel,balance,address;
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
                /*case "getdata":                   //请求数据响应
                    boolean balanceResult;
                    JSONObject data = new JSONObject();
                    Map<String, String> balances = new HashMap<>();
                    UserName = request.getParameter("name").trim();
                    Password = request.getParameter("pwd").trim();
                    balanceResult = Login.verifyLogin(UserName, Password);
                    if(balanceResult){
                        data.put("code", "1");
                        user = UserDAO.queryUserReObject(UserName);
                        data.put("data", user);
                    }else{
                        data.put("code", "0");
                    }
                    out.write(data.toString());
                    data.clear();
                break;*/
                case "headPhoto":                    //上传头像响应
                    //新建变量
                    JSONObject photo = new JSONObject();
                    //获取appdata文件夹路径
                    String apppath = request.getRealPath("/")+"appdata";
                    //新建file文件
		    File appfile = new File(apppath);
		    if(!appfile.exists()){
			appfile.mkdir();
		    }
                    //在appdata文件夹下新建userdata文件夹
                    String userdata = apppath + "/" + "userdata";
                    File userfile = new File(userdata);
                    if(!userfile.exists()){
			userfile.mkdir();
		    }
                    account = request.getParameter("account").trim();
                    imagestr = request.getParameter("imagestr").trim();
                    imagename = request.getParameter("imagename").trim();
                    imagepath = userdata + "/" + imagename;
                    if(string2Image(imagestr, imagepath)){
                        photo = RefreshUserData.RefreshHead(account,imagepath);   
                    }
                    out.write(photo.toString());
                    photo.clear();
                break;
                case "changeNickname":                       //修改昵称
                    JSONObject nameResult = new JSONObject(); 
                    account = request.getParameter("account").trim();
                    nickname = request.getParameter("newname").trim();
                    nameResult = RefreshUserData.changeName(account,nickname);
                    out.write(nameResult.toString());
                    nameResult.clear();
                    break;
                case "sex":                    //修改性别
                    JSONObject sexResult = new JSONObject(); 
                    account = request.getParameter("account").trim();
                    sex = request.getParameter("sex").trim();
                    sexResult = RefreshUserData.changeSex(account,sex);
                    out.write(sexResult.toString());
                    sexResult.clear();
                    break;
                case "setschool":              //修改学校
                    JSONObject schoolResult = new JSONObject(); 
                    account = request.getParameter("account").trim();
                    school = request.getParameter("school").trim();
                    schoolResult = RefreshUserData.changeSchool(account,school);
                    out.write(schoolResult.toString());
                    schoolResult.clear();
                    break;
                case "settel":                //修改联系方式
                    JSONObject telResult = new JSONObject(); 
                    account = request.getParameter("account").trim();
                    tel = request.getParameter("tel").trim();
                    telResult = RefreshUserData.changeTel(account,tel);
                    out.write(telResult.toString());
                    telResult.clear();
                    break;
                case "charge":                          //充值按钮响应
                    JSONObject charge = new JSONObject(); 
                    account = request.getParameter("account").trim();
                    balance = request.getParameter("charge").trim();
                    charge = RefreshUserData.Charge(account,balance);
                    out.write(charge.toString());
                    charge.clear();
                break;
                case "tixian":             //提现按钮响应
                    JSONObject tixian = new JSONObject(); 
                    account = request.getParameter("account").trim();
                    balance = request.getParameter("tixian").trim();
                    tixian = RefreshUserData.TiXian(account,balance);
                    out.write(tixian.toString());
                    tixian.clear();
                break;
                case "address":              //修改地址
                    JSONObject addressResult = new JSONObject(); 
                    account = request.getParameter("account").trim();
                    address = request.getParameter("address").trim();
                    addressResult = RefreshUserData.changeAddress(account,address);
                    out.write(addressResult.toString());
                    addressResult.clear();
                    break;
               /* case "userdata":                   //请求数据响应
                    JSONObject userresult = new JSONObject();
                    JSONObject userdatas = new JSONObject();
                    Uid = request.getParameter("uid").trim();
                    userdatas = UserDAO.queryUidReObject(Uid);
                    if(userdatas != null){
                        userresult.put("code", "1");
                        userresult.put("data", userdatas);
                    }else{
                        userresult.put("code", "0");
                    }
                    out.write(userresult.toString());
                break;*/
                default:System.out.println("用户验证失败！");break;
            }
        }catch(ServletException e){
            Logger.getLogger(Login_Servlet.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    //get请求，用不到
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

}
