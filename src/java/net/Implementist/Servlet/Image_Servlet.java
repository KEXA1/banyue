/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.Implementist.Servlet;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 处理图片请求的Servlet
 */
public class Image_Servlet extends HttpServlet {
@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    //    doPost(request, response);
        BufferedImage image_buffer = null;
        ServletOutputStream output = response.getOutputStream();
        // 设置响应内容类型  
        response.setContentType("image/jpeg");
        request.setCharacterEncoding("utf-8");
        try{
            //获得请求
            String imagepath = URLDecoder.decode(request.getQueryString().trim(),"utf-8");
            if((imagepath != null)||(new File(imagepath)).exists()){
                System.out.println("图片路径为：" + imagepath);
                try{
                    image_buffer = ImageIO.read(new File(imagepath));
                }catch(IOException e){
                }
            }else image_buffer = null;
            ImageIO.write(image_buffer, "jpg", output);
            output.flush();
            output.close();
        }catch(UnsupportedEncodingException e){
            Logger.getLogger(Image_Servlet.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
