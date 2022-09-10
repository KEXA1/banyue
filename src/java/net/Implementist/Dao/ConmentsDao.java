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
import net.Implementist.DB.Conments;
import net.Implementist.Utils.DBManager;
import net.sf.json.JSONArray;

/**
 * 商品表的操作类
 * @author dell
 */
public class ConmentsDao {
    /**
     * 查询给定商品ID的评论信息
     *
     * @param gid 给定的商品名
     * @return 查询到的封装了评论信息的Conments对象
     */
    public static JSONArray queryGidConments(String gid) {
        //定义变量
        JSONArray goodsconlist = new JSONArray();
        Map<String, String> condata = new HashMap<>();
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("SELECT * FROM conments WHERE Gid=? AND Constate=1");

        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, gid);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                condata.put("conid",String.valueOf(resultSet.getInt("Conid")).trim());
                condata.put("gid",String.valueOf(resultSet.getInt("Gid")).trim());
                condata.put("uid",String.valueOf(resultSet.getInt("Uid")).trim());
                condata.put("contime",resultSet.getString("Contime").trim());
                condata.put("guid",String.valueOf(resultSet.getInt("Guid")).trim());
                condata.put("concontent",resultSet.getString("Concontent").trim());
                condata.put("constate",String.valueOf(resultSet.getInt("Constate")).trim());
                System.out.println(condata);
                goodsconlist.add(condata);
            } 
        } catch (SQLException ex) {
            Logger.getLogger(ConmentsDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
        return goodsconlist;
    }
    /**
     * 更新评论
     *
     * @param conid 给定的评论id
     * @param indexming 要修改的字段名
     * @param indexzhi 要修改的字段值
     * @return 验证结果
     */
    public static int RefreshConments(String conid, String indexming, String indexzhi) {
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        switch(indexming){
            case "Gid":sqlStatement.append("UPDATE conments SET Gid=? WHERE Conid=?");break;
            case "Uid":sqlStatement.append("UPDATE conments SET Uid=? WHERE Conid=?");break;
            case "Contime":sqlStatement.append("UPDATE conments SET Contime=? WHERE Conid=?");break;
            case "Guid":sqlStatement.append("UPDATE conments SET Guid=? WHERE Conid=?");break;
            case "Concontent":sqlStatement.append("UPDATE conments SET Concontent=? WHERE Conid=?");break;
            case "Constate":sqlStatement.append("UPDATE conments SET Constate=? WHERE Conid=?");break;
            default:System.out.println("修改失败！");break;
        }
        int rs = 0;
        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, indexzhi.trim());
            preparedStatement.setString(2, conid.trim());
            System.out.println(preparedStatement);
            rs = preparedStatement.executeUpdate();
            System.out.println("修改成功！");
        } catch (SQLException ex) {
            Logger.getLogger(ConmentsDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
        return rs;
    }
    /**
     * 插入商品的评论
     *
     * @param con 给定的评论信息
     * @return 验证结果
     */
    public static int insertGoodsCon(Conments con) {
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("INSERT INTO Conments (Gid,Uid,Contime,Guid,Concontent,Constate) VALUES(?,?,?,?,?,?)");
        int rs = 0;
        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, String.valueOf(con.getGid()).trim());
            preparedStatement.setString(2, String.valueOf(con.getUid()).trim());
            preparedStatement.setString(3, con.getContime().trim());
            preparedStatement.setString(4, String.valueOf(con.getGuid()).trim());
            preparedStatement.setString(5, con.getConcontent().trim());
            preparedStatement.setString(6, String.valueOf(con.getConstate()).trim());
            rs = preparedStatement.executeUpdate();
            System.out.println("评论插入成功！");
        } catch (SQLException ex) {
            Logger.getLogger(ConmentsDao.class.getName()).log(Level.SEVERE, null, ex);
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
    public static int DeleteGoodsCon(String conid) {
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("DELETE FROM conments WHERE Conid=?");
        int rs = 0;
        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, conid.trim());
            System.out.println(preparedStatement);
            rs = preparedStatement.executeUpdate();
            System.out.println("删除成功！");
        } catch (SQLException ex) {
            Logger.getLogger(ConmentsDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
        return rs;
    }
}
