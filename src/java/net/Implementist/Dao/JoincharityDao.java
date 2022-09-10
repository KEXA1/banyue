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
public class JoincharityDao {
    /**
     * 验证用户是否参加过该公益
     * 表中有数据则返回true，否则返回false
     * @param uid  用户id
     * @param cid  公益id
     * @return 
     */
    public static Boolean verifyJoin(String uid, String cid) {
        boolean result = false;
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("SELECT * FROM joincharity WHERE Uid=? AND Cid=?");
        int rs = 0;
        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, uid);
            preparedStatement.setString(2, cid);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = true;
            }else result = false;
        } catch (SQLException ex) {
            Logger.getLogger(JoincharityDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
        //验证
        return result;
    }
    /**
     * 根据用户id查询该用户参加的所有公益活动
     *
     * @param uid 用户id
     * @return 查询到的封装了详细信息的JSONArray对象
     */
    public static JSONArray queryJoinList(String uid) {
        JSONArray joinlist = new JSONArray();
        JSONObject join = new JSONObject();
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("SELECT * FROM joincharity WHERE Uid=?");
        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, uid);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                join.put("cid",resultSet.getString("Cid").trim());
                join.put("uid",String.valueOf(resultSet.getInt("Uid")).trim());
                join.put("cuid",String.valueOf(resultSet.getInt("Cuid")).trim());
                join.put("jtime",resultSet.getString("Jtime").trim());
                joinlist.add(join);
            } 
        } catch (SQLException ex) {
            Logger.getLogger(CollectDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
        return joinlist;
    }
    /**
     * 更新参加公益的表
     *
     * @param jid 给定的参加公益表的id
     * @param indexming 要修改的字段名
     * @param indexzhi 要修改的字段值
     * @return 验证结果
     */
    public static int RefreshJoin(String jid, String indexming, String indexzhi) {
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        switch(indexming){
            case "Cid":sqlStatement.append("UPDATE joincharity SET Cid=? WHERE Jid=?");break;
            case "Uid":sqlStatement.append("UPDATE joincharity SET Uid=? WHERE Jid=?");break;
            case "Cuid":sqlStatement.append("UPDATE joincharity SET Cuid=? WHERE Jid=?");break;
            case "Jtime":sqlStatement.append("UPDATE joincharity SET Jtime=? WHERE Jid=?");break;
            default:System.out.println("修改失败！");break;
        }
        int rs = 0;
        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, indexzhi.trim());
            preparedStatement.setString(2, jid.trim());
            System.out.println(preparedStatement);
            rs = preparedStatement.executeUpdate();
            System.out.println("修改成功！");
        } catch (SQLException ex) {
            Logger.getLogger(JoincharityDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
        return rs;
    }
    /**
     * 插入数据
     *
     * @param cid 商品id
     * @param uid 收藏商品的用户id
     * @param cuid 商品所属的用户id
     * @param jtime 收藏时间
     * @return 验证结果
     */
    public static int insertJoin(String cid, String uid, String cuid, String jtime) {
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("INSERT INTO joincharity (Cid,Uid,Cuid,Jtime) VALUES(?,?,?,?)");
        int rs = 0;
        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, cid);
            preparedStatement.setString(2, uid);
            preparedStatement.setString(3, cuid);
            preparedStatement.setString(4, jtime);
            rs = preparedStatement.executeUpdate();
            System.out.println("数据插入成功！");
        } catch (SQLException ex) {
            Logger.getLogger(JoincharityDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
        return rs;
    }
    /**
     * 删除数据
     *
     * @param jid 给定的参加数据id
     * @return 验证结果
     */
    public static int DeleteJoin(String jid) {
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("DELETE FROM joincharity WHERE Jid=?");
        int rs = 0;
        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, jid.trim());
            System.out.println(preparedStatement);
            rs = preparedStatement.executeUpdate();
            System.out.println("删除成功！");
        } catch (SQLException ex) {
            Logger.getLogger(JoincharityDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
        return rs;
    }
}
