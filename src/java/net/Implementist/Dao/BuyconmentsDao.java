/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.Implementist.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.Implementist.DB.Buyconments;
import net.Implementist.DB.Conments;
import net.Implementist.Utils.DBManager;
import net.sf.json.JSONArray;

/**
 *
 * @author dell
 */
public class BuyconmentsDao {
    /**
     * 查询给定求购商品ID的评论信息
     *
     * @param bid 给定的求购商品名
     * @return 查询到的封装了评论信息的Buyconments对象
     */
    public static JSONArray queryBidConments(String bid) {
        //定义变量
        JSONArray buyconlist = new JSONArray();
        Map<String, String> condata = new HashMap<>();
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("SELECT * FROM buyconments WHERE Bid=? AND Bconstate=1");

        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, bid);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                condata.put("bconid",String.valueOf(resultSet.getInt("Bconid")).trim());
                condata.put("bid",String.valueOf(resultSet.getInt("Bid")).trim());
                condata.put("uid",String.valueOf(resultSet.getInt("Uid")).trim());
                condata.put("bcontime",resultSet.getString("Bcontime").trim());
                condata.put("buid",String.valueOf(resultSet.getInt("Buid")).trim());
                condata.put("bconcontent",resultSet.getString("Bconcontent").trim());
                condata.put("bconstate",String.valueOf(resultSet.getInt("Bconstate")).trim());
                System.out.println(condata);
                buyconlist.add(condata);
            } 
        } catch (SQLException ex) {
            Logger.getLogger(BuyconmentsDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
        return buyconlist;
    }
    /**
     * 更新评论
     *
     * @param bconid 给定的评论id
     * @param indexming 要修改的字段名
     * @param indexzhi 要修改的字段值
     * @return 验证结果
     */
    public static int RefreshConments(String bconid, String indexming, String indexzhi) {
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        switch(indexming){
            case "Bid":sqlStatement.append("UPDATE buyconments SET Bid=? WHERE Bconid=?");break;
            case "Uid":sqlStatement.append("UPDATE buyconments SET Uid=? WHERE Bconid=?");break;
            case "Bcontime":sqlStatement.append("UPDATE buyconments SET Bcontime=? WHERE Bconid=?");break;
            case "Buid":sqlStatement.append("UPDATE buyconments SET Buid=? WHERE Bconid=?");break;
            case "Bconcontent":sqlStatement.append("UPDATE buyconments SET Bconcontent=? WHERE Bconid=?");break;
            case "Bconstate":sqlStatement.append("UPDATE buyconments SET Bconstate=? WHERE Bconid=?");break;
            default:System.out.println("修改失败！");break;
        }
        int rs = 0;
        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, indexzhi.trim());
            preparedStatement.setString(2, bconid.trim());
            System.out.println(preparedStatement);
            rs = preparedStatement.executeUpdate();
            System.out.println("修改成功！");
        } catch (SQLException ex) {
            Logger.getLogger(BuyconmentsDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
        return rs;
    }
    /**
     * 插入评论商品的评论
     *
     * @param bconmnets 给定的评论信息
     * @return 验证结果
     */
    public static int insertBuyConments(Buyconments bconmnets) {
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("INSERT INTO buyconments (Bid,Uid,Bcontime,Buid,Bconcontent,Bconstate) VALUES(?,?,?,?,?,?)");
        int rs = 0;
        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, String.valueOf(bconmnets.getBid()).trim());
            preparedStatement.setString(2, String.valueOf(bconmnets.getUid()).trim());
            preparedStatement.setString(3, bconmnets.getBcontime().trim());
            preparedStatement.setString(4, String.valueOf(bconmnets.getBuid()).trim());
            preparedStatement.setString(5, bconmnets.getBconcontent().trim());
            preparedStatement.setString(6, String.valueOf(bconmnets.getBconstate()).trim());
            rs = preparedStatement.executeUpdate();
            System.out.println("评论插入成功！");
        } catch (SQLException ex) {
            Logger.getLogger(BuyconmentsDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
        return rs;
    }
    /**
     * 删除评论
     *
     * @param conid 给定的评论id
     * @return 验证结果
     */
    public static int DeleteBuyConments(String conid) {
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("DELETE FROM buyconments WHERE Bconid=?");
        int rs = 0;
        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, conid.trim());
            System.out.println(preparedStatement);
            rs = preparedStatement.executeUpdate();
            System.out.println("删除成功！");
        } catch (SQLException ex) {
            Logger.getLogger(BuyconmentsDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
        return rs;
    }
}
