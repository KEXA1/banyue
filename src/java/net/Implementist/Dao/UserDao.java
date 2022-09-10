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
import net.Implementist.DB.User;
import net.Implementist.Utils.DBManager;
import net.sf.json.JSONObject;

/**
 *
 * @author dell
 */
public class UserDao {
    /**
     * 查询给定用户名的用户的详细信息
     *
     * @param account 给定的用户名
     * @return 查询到的封装了详细信息的User对象
     */
    public static User queryUserAccount(String account) {
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("SELECT * FROM user WHERE Uaccount=?");
        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, account);
            
            resultSet = preparedStatement.executeQuery();
            User user = new User();
            if (resultSet.next()) {
                //将数据库中的数据赋值给user
                user.setId(resultSet.getInt("Uid"));
                user.setAccount(resultSet.getString("Uaccount"));
                user.setPassword(resultSet.getString("Upwd"));
                user.setHxId(resultSet.getString("Uhxid"));
                user.setNickName(resultSet.getString("Unickname"));
                user.setSex(resultSet.getInt("Usex"));
                user.setReputation(resultSet.getInt("Ureputation"));
                user.setHeadPhoto(resultSet.getString("Uphoto"));
                user.setBalance(resultSet.getDouble("Ubalance"));
                user.setTel(resultSet.getString("Utel"));
                user.setAddress(resultSet.getString("Uaddress"));
                user.setSchool(resultSet.getString("Uschool"));
                user.setState(resultSet.getInt("Ustate"));
                System.out.println(user);
                return user;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
    }
    /**
     * 查询给定用户名的用户的详细信息
     *
     * @param account 给定的用户名
     * @return 查询到的封装了详细信息的JSONObject对象
     */
    public static JSONObject queryUserReObject(String account) {
        JSONObject user = new JSONObject();
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("SELECT * FROM user WHERE Uaccount=?");

        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, account);
            
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user.put("uid",String.valueOf(resultSet.getInt("Uid")).trim());
                user.put("account",resultSet.getString("Uaccount").trim());
                user.put("password",resultSet.getString("Upwd").trim());
                user.put("hxid",resultSet.getString("Uhxid").trim());
                user.put("nickname",resultSet.getString("Unickname").trim());
                user.put("sex",String.valueOf(resultSet.getInt("Usex")).trim());
                user.put("reputation",String.valueOf(resultSet.getInt("Ureputation")).trim());
                user.put("headphoto",resultSet.getString("Uphoto").trim());
                user.put("balance",resultSet.getDouble("Ubalance"));
                user.put("tel",resultSet.getString("Utel").trim());
                user.put("address",resultSet.getString("Uaddress").trim());
                user.put("school",resultSet.getString("Uschool").trim());
                user.put("state",String.valueOf(resultSet.getInt("Ustate")).trim());
                System.out.println(user);
                return user;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
    }
    /**
     * 查询给定ID的用户的详细信息
     *
     * @param uid 给定的用户ID
     * @return 查询到的封装了详细信息的User对象
     */
    public static User queryUid(String uid) {
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("SELECT * FROM user WHERE Uid=?");

        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, uid);
            System.out.println(preparedStatement);
            resultSet = preparedStatement.executeQuery();
            User user = new User();
            if (resultSet.next()) {
                //将数据库中的数据赋值给user
                user.setId(resultSet.getInt("Uid"));
                user.setAccount(resultSet.getString("Uaccount"));
                user.setPassword(resultSet.getString("Upwd"));
                user.setHxId(resultSet.getString("Uhxid"));
                user.setNickName(resultSet.getString("Unickname"));
                user.setSex(resultSet.getInt("Usex"));
                user.setReputation(resultSet.getInt("Ureputation"));
                user.setHeadPhoto(resultSet.getString("Uphoto"));
                user.setBalance(resultSet.getDouble("Ubalance"));
                user.setTel(resultSet.getString("Utel"));
                user.setAddress(resultSet.getString("Uaddress"));
                user.setSchool(resultSet.getString("Uschool"));
                user.setState(resultSet.getInt("Ustate"));
                System.out.println(user);
                return user;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
    }
    /**
     * 查询给定ID的用户的详细信息并返回JSONObject对象
     *
     * @param uid 给定的用户ID
     * @return 查询到的封装了详细信息的JSONObject对象
     */
    public static JSONObject queryUidReObject(String uid) {
        JSONObject user = new JSONObject();
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("SELECT * FROM user WHERE Uid=?");

        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, uid);
            System.out.println(preparedStatement);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user.put("uid",String.valueOf(resultSet.getInt("Uid")).trim());
                user.put("account",resultSet.getString("Uaccount").trim());
                user.put("password",resultSet.getString("Upwd").trim());
                user.put("hxid",resultSet.getString("Uhxid").trim());
                user.put("nickname",resultSet.getString("Unickname").trim());
                user.put("sex",String.valueOf(resultSet.getInt("Usex")).trim());
                user.put("reputation",String.valueOf(resultSet.getInt("Ureputation")).trim());
                user.put("headphoto",resultSet.getString("Uphoto").trim());
                user.put("balance",resultSet.getDouble("Ubalance"));
                user.put("tel",resultSet.getString("Utel").trim());
                user.put("address",resultSet.getString("Uaddress").trim());
                user.put("school",resultSet.getString("Uschool").trim());
                user.put("state",String.valueOf(resultSet.getInt("Ustate")).trim());
                System.out.println(user);
                return user;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
    }
    /**
     * 插入用户的详细信息
     *
     * @param account 给定的用户名
     * @param pwd 给定的用户密码
     * @param nickName 给定的用户昵称
     * @param tel 给定的用户电话号
     * @param sex 性别，1是男，2是女
     * @return 验证结果
     */
    public static int insertUser(String account,String pwd,String nickName,String tel,int sex) {
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        //防止数据库被恶意注入数据
        sqlStatement.append("INSERT INTO user (Uaccount,Upwd,Uhxid,Unickname,Uphoto,Utel,Usex,Uaddress,Uschool,Ubalance,Ureputation,Ustate) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
        int rs = 0;
        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, account.trim());
            preparedStatement.setString(2, pwd.trim());
            preparedStatement.setString(3, account.trim());
            preparedStatement.setString(4, nickName.trim());
            preparedStatement.setString(5, "null");
            preparedStatement.setString(6, tel.trim());
            preparedStatement.setInt(7, sex);
            preparedStatement.setString(8, "null");
            preparedStatement.setString(9, "null");
            preparedStatement.setInt(10, 0);
            preparedStatement.setInt(11, 0);
            preparedStatement.setInt(12, 1);
            rs = preparedStatement.executeUpdate();
            System.out.println("注册成功！");
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
        return rs;
    }
    /**
     * 更新用户的详细信息
     *
     * @param uid 给定的用户id
     * @param indexming 要修改的字段名
     * @param indexzhi 要修改的字段值
     * @return 验证结果
     */
    public static int refreshUser(String uid, String indexming, String indexzhi) {
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        switch(indexming){
            case "Uaccount":sqlStatement.append("UPDATE user SET Uaccount=? WHERE Uid=?");break;
            case "Upwd":sqlStatement.append("UPDATE user SET Upwd=? WHERE Uid=?");break;
            case "Uhxid":sqlStatement.append("UPDATE user SET Uhxid=? WHERE Uid=?");break;
            case "Unickname":sqlStatement.append("UPDATE user SET Unickname=? WHERE Uid=?");break;
            case "Usex":sqlStatement.append("UPDATE user SET Usex=? WHERE Uid=?");break;
            case "Ureputation":sqlStatement.append("UPDATE user SET Ureputation=? WHERE Uid=?");break;
            case "Uphoto":sqlStatement.append("UPDATE user SET Uphoto=? WHERE Uid=?");break;
            case "Ubalance":sqlStatement.append("UPDATE user SET Ubalance=? WHERE Uid=?");break;
            case "Uaddress":sqlStatement.append("UPDATE user SET Uaddress=? WHERE Uid=?");break;
            case "Uschool":sqlStatement.append("UPDATE user SET Uschool=? WHERE Uid=?");break;
            case "Utel":sqlStatement.append("UPDATE user SET tel=? WHERE Uid=?");break;
            case "Ustate":sqlStatement.append("UPDATE user SET Ustate=? WHERE Uid=?");break;
            default:System.out.println("修改失败！");break;
        }
        int rs = 0;
        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, indexzhi.trim());
            preparedStatement.setString(2, uid.trim());
            System.out.println(preparedStatement);
            rs = preparedStatement.executeUpdate();
            System.out.println("修改成功！");
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
        return rs;
    }
    /**
     * 更新用户的详细信息
     *
     * @param account 给定的用户账号
     * @param indexming 要修改的字段名
     * @param indexzhi 要修改的字段值
     * @return 验证结果
     */
    public static int refreshUserByAccount(String account, String indexming, String indexzhi) {
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        switch(indexming){
            case "Uaccount":sqlStatement.append("UPDATE user SET Uaccount=? WHERE Uaccount=?");break;
            case "Upwd":sqlStatement.append("UPDATE user SET Upwd=? WHERE Uaccount=?");break;
            case "Uhxid":sqlStatement.append("UPDATE user SET Uhxid=? WHERE Uaccount=?");break;
            case "Unickname":sqlStatement.append("UPDATE user SET Unickname=? WHERE Uaccount=?");break;
            case "Usex":sqlStatement.append("UPDATE user SET Usex=? WHERE Uaccount=?");break;
            case "Ureputation":sqlStatement.append("UPDATE user SET Ureputation=? WHERE Uaccount=?");break;
            case "Uphoto":sqlStatement.append("UPDATE user SET Uphoto=? WHERE Uaccount=?");break;
            case "Ubalance":sqlStatement.append("UPDATE user SET Ubalance=? WHERE Uaccount=?");break;
            case "Uaddress":sqlStatement.append("UPDATE user SET Uaddress=? WHERE Uaccount=?");break;
            case "Uschool":sqlStatement.append("UPDATE user SET Uschool=? WHERE Uaccount=?");break;
            case "Utel":sqlStatement.append("UPDATE user SET Utel=? WHERE Uaccount=?");break;
            case "Ustate":sqlStatement.append("UPDATE user SET Ustate=? WHERE Uaccount=?");break;
            default:System.out.println("修改失败！");break;
        }
        int rs = 0;
        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, indexzhi.trim());
            preparedStatement.setString(2, account.trim());
            System.out.println(preparedStatement);
            rs = preparedStatement.executeUpdate();
            System.out.println("修改成功！");
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
        return rs;
    }
}
