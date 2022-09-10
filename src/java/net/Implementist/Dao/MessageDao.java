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
import net.Implementist.DB.Message;
import net.Implementist.Utils.DBManager;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *
 * @author dell
 */
public class MessageDao {
    /**
     * 查询给定消息ID的详细信息
     *
     * @param mid 给定的消息ID
     * @return 查询到的封装了详细信息的Message对象
     */
    public static Message queryMid(String mid) {
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("SELECT * FROM message WHERE Mid=?");

        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, mid);
            System.out.println(preparedStatement);
            resultSet = preparedStatement.executeQuery();
            Message message = new Message();
            if (resultSet.next()) {
                message.setMid(resultSet.getInt("Mid"));
                message.setReceiveid(resultSet.getInt("Receiveid"));
                message.setMtitle(resultSet.getString("Mtitle"));
                message.setMcontent(resultSet.getString("Mcontent"));
                message.setMtime(resultSet.getString("Mtime"));
                message.setMstate(resultSet.getInt("Mstate"));
                System.out.println(message);
                return message;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MessageDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
    }
    /**
     * 查询给定消息ID的详细信息并返回JSONObject对象
     *
     * @param mid 给定的消息ID
     * @return 查询到的封装了详细信息的JSONObject对象
     */
    public static JSONObject queryMidReObject(String mid) {
        JSONObject message = new JSONObject();
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("SELECT * FROM message WHERE Mid=?");

        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, mid);
            System.out.println(preparedStatement);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                message.put("mid",resultSet.getString("Mid").trim());
                message.put("receiveid",resultSet.getString("Receiveid").trim());
                message.put("mtitle",resultSet.getString("Mtitle").trim());
                message.put("mcontent",resultSet.getString("Mcontent"));
                message.put("mtime",resultSet.getString("Mtime").trim());
                message.put("mstate",resultSet.getString("Mstate").trim());
                System.out.println(message);
                return message;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MessageDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
    }
    /**
     * 查询给定接收人ID的详细信息并返回JSONArray对象（聊天记录)
     *
     * @param receiveid 给定的接收人ID
     * @param way 查询消息状态
     * @return 查询到的封装了详细信息的JSONArray对象
     */
    public static JSONArray queryMessageData(String receiveid, int way) {
        JSONArray message = new JSONArray();
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        switch(way){
            case 1:
                sqlStatement.append("SELECT * FROM message WHERE Receiveid=? AND (Mstate=1 OR Mstate=2) ORDER BY Mtime DESC");
                break;
            case 2:
                sqlStatement.append("SELECT * FROM message WHERE Receiveid=? AND Mstate=1");
                break;
        }
        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, receiveid);
            System.out.println(preparedStatement);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                JSONObject param = new JSONObject();
                param.put("mid",resultSet.getString("Mid").trim());
                param.put("receiveid",resultSet.getString("Receiveid").trim());
                param.put("mtitle",resultSet.getString("Mtitle").trim());
                param.put("mcontent",resultSet.getString("Mcontent"));
                param.put("mtime",resultSet.getString("Mtime").trim());
                param.put("mstate",resultSet.getString("Mstate").trim());
                message.add(param);
            } 
        } catch (SQLException ex) {
            Logger.getLogger(MessageDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
        return message;
    }
    /**
     * 插入系统消息的详细信息
     *
     * @param message 消息数据
     * @return 验证结果
     */
    public static int insertMessage(Message message) {
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("INSERT INTO Message (Receiveid,Mtitle,Mcontent,Mtime,Mstate) VALUES(?,?,?,?,?)");
        int rs = 0;
        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, String.valueOf(message.getReceiveid()).trim());
            preparedStatement.setString(2, message.getMtitle().trim());
            preparedStatement.setString(3, message.getMcontent().trim());
            preparedStatement.setString(4, message.getMtime().trim());
            preparedStatement.setString(5, String.valueOf(message.getMstate()).trim());
            rs = preparedStatement.executeUpdate();
            System.out.println("消息添加成功！");
        } catch (SQLException ex) {
            Logger.getLogger(MessageDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
        return rs;
    }
    /**
     * 更新消息的详细信息
     *
     * @param mid 给定的消息id
     * @param indexming 要修改的字段名
     * @param indexzhi 要修改的字段值
     * @return 验证结果
     */
    public static int refreshMessage(String mid, String indexming, String indexzhi) {
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        switch(indexming){
            case "Receiveid":sqlStatement.append("UPDATE message SET Receiveid=? WHERE Mid=?");break;
            case "Mtitle":sqlStatement.append("UPDATE message SET Mtitle=? WHERE Mid=?");break;
            case "Mcontent":sqlStatement.append("UPDATE message SET Mcontent=? WHERE Mid=?");break;
            case "Mtime":sqlStatement.append("UPDATE message SET Mtime=? WHERE Mid=?");break;
            case "Mstate":sqlStatement.append("UPDATE message SET Mstate=? WHERE Mid=?");break;
            default:System.out.println("修改失败！");break;
        }
        int rs = 0;
        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, indexzhi.trim());
            preparedStatement.setString(2, mid.trim());
            rs = preparedStatement.executeUpdate();
            System.out.println("消息修改成功！");
        } catch (SQLException ex) {
            Logger.getLogger(MessageDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
        return rs;
    }
}
