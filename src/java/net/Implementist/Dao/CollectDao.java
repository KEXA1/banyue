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
import java.util.logging.Level;
import java.util.logging.Logger;
import net.Implementist.Utils.DBManager;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *
 * @author dell
 */
public class CollectDao {
    /**
     * 根据给定商品id和用户id查询该商品是否被该用户收藏并返回int类型的数据
     *
     * @param Gid 给定的商品id
     * @param Uid 给定的用户id
     * @return 查询到的封装了详细信息的Goods对象
     */
    public static JSONObject queryCollectInfo(String Gid, String Uid) {
        JSONObject collect = new JSONObject();
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("SELECT * FROM collect WHERE Gid=? AND Uid = ?");
        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, Gid);
            preparedStatement.setString(2, Uid);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                collect.put("colid",resultSet.getString("Colid").trim());
                collect.put("gid",String.valueOf(resultSet.getInt("Gid")).trim());
                collect.put("uid",String.valueOf(resultSet.getInt("Uid")).trim());
                collect.put("guid",String.valueOf(resultSet.getInt("Guid")).trim());
                collect.put("coltime",resultSet.getString("Coltime").trim());
                System.out.println(collect);
            } 
        } catch (SQLException ex) {
            Logger.getLogger(CollectDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
        return collect;
    }
     /**
     * 根据用户id查询所有的收藏信息
     *
     * @param uid 用户id
     * @return 查询到的封装了详细信息的JSONArray对象
     */
    public static JSONArray queryCollectList(String uid) {
        JSONArray collectlist = new JSONArray();
        JSONObject collect = new JSONObject();
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("SELECT * FROM collect WHERE Uid=?");
        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, uid);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                collect.put("colid",resultSet.getString("Colid").trim());
                collect.put("gid",String.valueOf(resultSet.getInt("Gid")).trim());
                collect.put("uid",String.valueOf(resultSet.getInt("Uid")).trim());
                collect.put("guid",String.valueOf(resultSet.getInt("Guid")).trim());
                collect.put("coltime",resultSet.getString("Coltime").trim());
                collectlist.add(collect);
            } 
        } catch (SQLException ex) {
            Logger.getLogger(CollectDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
        return collectlist;
    }
    /**
     * 插入收藏商品的数据
     *
     * @param gid 商品id
     * @param uid 收藏商品的用户id
     * @param guid 商品所属的用户id
     * @param coltime 收藏时间
     * @return 验证结果
     */
    public static int insertCollect(String gid, String uid, String guid, String coltime) {
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("INSERT INTO collect (Gid,Uid,Guid,Coltime) VALUES(?,?,?,?)");
        int rs = 0;
        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, gid);
            preparedStatement.setString(2, uid);
            preparedStatement.setString(3, guid);
            preparedStatement.setString(4, coltime);
            rs = preparedStatement.executeUpdate();
            System.out.println("收藏信息插入成功！");
        } catch (SQLException ex) {
            Logger.getLogger(CollectDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
        return rs;
    }
    /**
     * 更新收藏的详细信息
     *
     * @param colid 给定的商品id
     * @param indexming 要修改的字段名
     * @param indexzhi 要修改的字段值
     * @return 验证结果
     */
    public static int RefreshCollect(String colid, String indexming, String indexzhi) {
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        switch(indexming){
            case "Gid":sqlStatement.append("UPDATE collect SET Gid=? WHERE Colid=?");break;
            case "Uid":sqlStatement.append("UPDATE collect SET Uid=? WHERE Colid=?");break;
            case "Guid":sqlStatement.append("UPDATE collect SET Guid=? WHERE Colid=?");break;
            case "Coltime":sqlStatement.append("UPDATE collect SET Coltime=? WHERE Colid=?");break;
            default:System.out.println("修改失败！");break;
        }
        int rs = 0;
        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, indexzhi.trim());
            preparedStatement.setString(2, colid.trim());
            System.out.println(preparedStatement);
            rs = preparedStatement.executeUpdate();
            System.out.println("修改成功！");
        } catch (SQLException ex) {
            Logger.getLogger(CollectDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
        return rs;
    }
    /**
     * 删除收藏的商品信息
     *
     * @param colid 给定的商品id
     * @return 验证结果
     */
    public static int DeleteCollect(String colid) {
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("DELETE FROM collect WHERE Colid=?");
        int rs = 0;
        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, colid.trim());
            System.out.println(preparedStatement);
            rs = preparedStatement.executeUpdate();
            System.out.println("删除成功！");
        } catch (SQLException ex) {
            Logger.getLogger(CollectDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
        return rs;
    }
}
