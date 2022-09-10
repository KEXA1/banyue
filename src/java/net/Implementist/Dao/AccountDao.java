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
import net.Implementist.DB.Account;
import net.Implementist.Utils.DBManager;
import net.sf.json.JSONArray;

/**
 * account的操作类
 * @author dell
 */
public class AccountDao {
    /**
     * 查询给定账单ID的详细信息
     *
     * @param aid 给定的订单ID
     * @return 查询到的封装了详细信息的Account对象
     */
    public static Account queryAid(String aid) {
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("SELECT * FROM account WHERE Aid=?");

        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, aid);
            
            resultSet = preparedStatement.executeQuery();
            Account account = new Account();
            if (resultSet.next()) {
                account.setId(resultSet.getInt("Aid"));
                account.setAnumber(resultSet.getString("Anumber"));
                account.setGid(resultSet.getInt("Gid"));
                account.setUid(resultSet.getInt("Uid"));
                account.setGuid(resultSet.getInt("Guid"));
                account.setAbill(resultSet.getDouble("Abill"));
                account.setAtime(resultSet.getString("Atime"));
                account.setAstate(resultSet.getInt("Astate"));
                System.out.println(account);
                return account;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
    }
    /**
     * 查询给定货物ID的详细信息
     *
     * @param gid 给定的货物ID
     * @return 查询到的封装了详细信息的Account对象
     */
    public static Account queryGid(String gid) {
        Account account = new Account();
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("SELECT * FROM account WHERE Gid=?");

        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, gid);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                account.setId(resultSet.getInt("Aid"));
                account.setAnumber(resultSet.getString("Anumber"));
                account.setGid(resultSet.getInt("Gid"));
                account.setUid(resultSet.getInt("Uid"));
                account.setGuid(resultSet.getInt("Guid"));
                account.setAbill(resultSet.getDouble("Abill"));
                account.setAtime(resultSet.getString("Atime"));
                account.setAstate(resultSet.getInt("Astate"));
                System.out.println(account);
            } 
        } catch (SQLException ex) {
            Logger.getLogger(AccountDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
        return account;
    }
    /**
     * 查询给定买家ID的详细信息
     *
     * @param uid 给定的用户ID
     * @return 查询到的封装了详细信息的JSONArray对象
     */
    public static JSONArray queryUid(String uid) {
        JSONArray result = new JSONArray();
        Map<String, String> accdata = new HashMap<>();
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("SELECT * FROM account WHERE Uid=? AND (Astate=1 OR Astate=2 OR Astate=3 OR Astate=4 OR Astate=5)");

        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, uid);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                accdata.put("aid",resultSet.getString("Aid").trim());
                accdata.put("gid",String.valueOf(resultSet.getInt("Gid")).trim());
                accdata.put("uid",String.valueOf(resultSet.getInt("Uid")).trim());
                accdata.put("guid",String.valueOf(resultSet.getInt("Guid")).trim());
                accdata.put("anumber",resultSet.getString("Anumber").trim());
                accdata.put("abill",String.valueOf(resultSet.getDouble("Abill")).trim());
                accdata.put("atime",resultSet.getString("Atime").trim());
                accdata.put("astate",String.valueOf(resultSet.getInt("Astate")).trim());
                System.out.println(accdata);
                result.add(accdata);
            } 
        } catch (SQLException ex) {
            Logger.getLogger(AccountDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
        return result;
    }
    /**
     * 查询给定卖家ID的详细信息
     *
     * @param uid 给定的用户ID
     * @return 查询到的封装了详细信息的JSONArray对象
     */
    public static JSONArray queryGuid(String uid) {
        JSONArray result = new JSONArray();
        Map<String, String> accdata = new HashMap<>();
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("SELECT * FROM account WHERE Guid=? AND (Astate=1 OR Astate=2 OR Astate=3 OR Astate=4 OR Astate=5)");

        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, uid);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                accdata.put("aid",resultSet.getString("Aid").trim());
                accdata.put("gid",String.valueOf(resultSet.getInt("Gid")).trim());
                accdata.put("uid",String.valueOf(resultSet.getInt("Uid")).trim());
                accdata.put("guid",String.valueOf(resultSet.getInt("Guid")).trim());
                accdata.put("anumber",resultSet.getString("Anumber").trim());
                accdata.put("abill",String.valueOf(resultSet.getDouble("Abill")).trim());
                accdata.put("atime",resultSet.getString("Atime").trim());
                accdata.put("astate",String.valueOf(resultSet.getInt("Astate")).trim());
                System.out.println(accdata);
                result.add(accdata);
            } 
        } catch (SQLException ex) {
            Logger.getLogger(AccountDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
        return result;
    }
    /**
     * 查询给定买家ID的详细信息
     *
     * @param uid 给定的用户ID
     * @param state 给定的账单状态
     * @return 查询到的封装了详细信息的JSONArray对象
     */
    public static JSONArray queryUidState(String uid, String state) {
        JSONArray result = new JSONArray();
        Map<String, String> accdata = new HashMap<>();
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("SELECT * FROM account WHERE Uid=? AND Astate=?");

        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, uid);
            preparedStatement.setString(2, state);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                accdata.put("aid",resultSet.getString("Aid").trim());
                accdata.put("gid",String.valueOf(resultSet.getInt("Gid")).trim());
                accdata.put("uid",String.valueOf(resultSet.getInt("Uid")).trim());
                accdata.put("guid",String.valueOf(resultSet.getInt("Guid")).trim());
                accdata.put("anumber",resultSet.getString("Anumber").trim());
                accdata.put("abill",String.valueOf(resultSet.getDouble("Abill")).trim());
                accdata.put("atime",resultSet.getString("Atime").trim());
                accdata.put("astate",String.valueOf(resultSet.getInt("Astate")).trim());
                System.out.println(accdata);
                result.add(accdata);
            } 
        } catch (SQLException ex) {
            Logger.getLogger(AccountDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
        return result;
    }
    /**
     * 插入订单的详细信息
     *
     * @param account 给定的订单信息
     * @return 验证结果
     */
    public static int insertAccount(Account account) {
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("INSERT INTO account (Anumber,Gid,Uid,Guid,Abill,Atime,Astate) VALUES(?,?,?,?,?,?,?)");
        int rs = 0;
        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, account.getAnumber().trim());
            preparedStatement.setString(2, String.valueOf(account.getGid()).trim());
            preparedStatement.setString(3, String.valueOf(account.getUid()).trim());
            preparedStatement.setString(4, String.valueOf(account.getGuid()).trim());
            preparedStatement.setString(5, String.valueOf(account.getAbill()).trim());
            preparedStatement.setString(6, account.getAtime().trim());
            preparedStatement.setString(7, String.valueOf(account.getAstate()).trim());
            rs = preparedStatement.executeUpdate();
            System.out.println("订单信息插入成功！");
        } catch (SQLException ex) {
            Logger.getLogger(AccountDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
        return rs;
    }
    /**
     * 更新商品的详细信息
     *
     * @param aid 给定的订单id
     * @param indexming 要修改的字段名
     * @param indexzhi 要修改的字段值
     * @return 验证结果
     */
    public static int RefreshAccount(String aid, String indexming, String indexzhi) {
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        switch(indexming){
            case "Anumber":sqlStatement.append("UPDATE account SET Anumber=? WHERE Aid=?");break;
            case "Gid":sqlStatement.append("UPDATE account SET Gid=? WHERE Aid=?");break;
            case "Uid":sqlStatement.append("UPDATE account SET Uid=? WHERE Aid=?");break;
            case "Guid":sqlStatement.append("UPDATE account SET Guid=? WHERE Aid=?");break;
            case "Abill":sqlStatement.append("UPDATE account SET Abill=? WHERE Aid=?");break;
            case "Atime":sqlStatement.append("UPDATE account SET Atime=? WHERE Aid=?");break;
            case "Astate":sqlStatement.append("UPDATE account SET Astate=? WHERE Aid=?");break;
            default:System.out.println("修改失败！");break;
        }
        int rs = 0;
        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, indexzhi.trim());
            preparedStatement.setString(2, aid.trim());
            System.out.println(preparedStatement);
            rs = preparedStatement.executeUpdate();
            System.out.println("修改成功！");
        } catch (SQLException ex) {
            Logger.getLogger(AccountDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
        return rs;
    }
    /**
     * 删除订单
     *
     * @param aid 给定的订单id
     * @return 验证结果
     */
    public static int DeleteAccount(String aid) {
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("DELETE FROM account WHERE Aid=?");
        int rs = 0;
        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, aid.trim());
            System.out.println(preparedStatement);
            rs = preparedStatement.executeUpdate();
            System.out.println("删除成功！");
        } catch (SQLException ex) {
            Logger.getLogger(AccountDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
        return rs;
    }
}
