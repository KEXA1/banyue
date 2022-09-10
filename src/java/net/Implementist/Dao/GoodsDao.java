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
import net.Implementist.DB.Goods;
import net.Implementist.Utils.DBManager;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *
 * @author dell
 */
public class GoodsDao {
    /**
     * 查询给定商品名的详细信息
     *
     * @param Gname 给定的商品名
     * @param Gstate 商品状态
     * @return 查询到的封装了详细信息的Goods对象
     */
    public static JSONArray queryGoods(String Gname,String Gstate) {
        //定义变量
        JSONArray goodslist = new JSONArray();
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        switch (Gstate) {
            case "1":
                sqlStatement.append("SELECT * FROM goods WHERE Gname=? AND Gstate=1");
                break;
            case "2":
                sqlStatement.append("SELECT * FROM goods WHERE Gname=? AND Gstate=2");
                break;
            case "3":
                sqlStatement.append("SELECT * FROM goods WHERE Gname=? AND Gstate=3");
                break;
            case "4":
                sqlStatement.append("SELECT * FROM goods WHERE Gname=? AND Gstate=4");
                break;
            default:
                sqlStatement.append("SELECT * FROM goods WHERE Gname=?");
                break;
        }
        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, Gname);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                JSONObject goods = new JSONObject();
                goods.put("gid",String.valueOf(resultSet.getInt("Gid")).trim());
                goods.put("gname",resultSet.getString("Gname").trim());
                goods.put("uid",String.valueOf(resultSet.getInt("Uid")).trim());
                goods.put("gtime",resultSet.getString("Gtime").trim());
                goods.put("gtype",resultSet.getString("Gtype").trim());
                goods.put("gdetail",resultSet.getString("Gdetail").trim());
                goods.put("gprice",String.valueOf(resultSet.getDouble("Gprice")).trim());
                goods.put("goprice",String.valueOf(resultSet.getDouble("Goprice")).trim());
                goods.put("gimage",resultSet.getString("Gimage").trim());
                goods.put("gstate",String.valueOf(resultSet.getInt("Gstate")).trim());
                goods.put("gemergent",String.valueOf(resultSet.getInt("Gemergent")).trim());
                goods.put("ggetway",resultSet.getString("Ggetway").trim());
                goods.put("ghownew",resultSet.getString("Ghownew").trim());
                goods.put("gscannum",String.valueOf(resultSet.getInt("Gscannum")).trim());
                goods.put("gaddress",resultSet.getString("Gaddress").trim());
                System.out.println(goods);
                goodslist.add(goods);
            } 
        } catch (SQLException ex) {
            Logger.getLogger(GoodsDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
        return goodslist;
    }   
    /**
     * 模糊查询给定商品名的详细信息
     *
     * @param Gname 给定的商品名
     * @param Gstate 商品状态
     * @return 查询到的封装了详细信息的Goods对象
     */
    public static JSONArray queryGoodsMo(String Gname,String Gstate) {
        //定义变量
        JSONArray goodslist = new JSONArray();
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        //模糊查询字符串
        String name = "%"+Gname+"%";
        switch (Gstate) {
            case "1":
                sqlStatement.append("SELECT * FROM goods WHERE Gname like ? AND Gstate=1");
                break;
            case "2":
                sqlStatement.append("SELECT * FROM goods WHERE Gname like ? AND Gstate=2");
                break;
            case "3":
                sqlStatement.append("SELECT * FROM goods WHERE Gname like ? AND Gstate=3");
                break;
            case "4":
                sqlStatement.append("SELECT * FROM goods WHERE Gname like ? AND Gstate=4");
                break;
            default:
                sqlStatement.append("SELECT * FROM goods WHERE Gname like ?");
                break;
        }
        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                JSONObject goods = new JSONObject();
                goods.put("gid",String.valueOf(resultSet.getInt("Gid")).trim());
                goods.put("gname",resultSet.getString("Gname").trim());
                goods.put("uid",String.valueOf(resultSet.getInt("Uid")).trim());
                goods.put("gtime",resultSet.getString("Gtime").trim());
                goods.put("gtype",resultSet.getString("Gtype").trim());
                goods.put("gdetail",resultSet.getString("Gdetail").trim());
                goods.put("gprice",String.valueOf(resultSet.getDouble("Gprice")).trim());
                goods.put("goprice",String.valueOf(resultSet.getDouble("Goprice")).trim());
                goods.put("gimage",resultSet.getString("Gimage").trim());
                goods.put("gstate",String.valueOf(resultSet.getInt("Gstate")).trim());
                goods.put("gemergent",String.valueOf(resultSet.getInt("Gemergent")).trim());
                goods.put("ggetway",resultSet.getString("Ggetway").trim());
                goods.put("ghownew",resultSet.getString("Ghownew").trim());
                goods.put("gscannum",String.valueOf(resultSet.getInt("Gscannum")).trim());
                goods.put("gaddress",resultSet.getString("Gaddress").trim());
                System.out.println(goods);
                goodslist.add(goods);
            } 
        } catch (SQLException ex) {
            Logger.getLogger(GoodsDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
        return goodslist;
    }   
    /**
     * 查询给定用户ID和商品状态的商品
     *
     * @param IDname 要查询的ID类型
     * @param ID 给定的ID
     * @param Gstate 给定的商品状态
     * @return 查询到的封装了详细信息的Goods对象
     */
    public static JSONArray queryGoodsState(String IDname, String ID, String Gstate) {
        //定义变量
        JSONArray goodslist = new JSONArray();
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        switch(IDname){
            case "Uid":sqlStatement.append("SELECT * FROM goods WHERE Uid=? AND Gstate=?");break;
            case "Gid":sqlStatement.append("SELECT * FROM goods WHERE Gid=? AND Gstate=?");break;
            case "Type":sqlStatement.append("SELECT * FROM goods WHERE Gtype=? AND Gstate=?");break;
            case "Price":sqlStatement.append("SELECT * FROM goods WHERE Gprice=? AND Gstate=?");break;
            default:System.out.println("商品查询失败！");break;
        }
        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, ID);
            preparedStatement.setString(2, Gstate);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                JSONObject goods = new JSONObject();
                goods.put("gid",String.valueOf(resultSet.getInt("Gid")).trim());
                goods.put("gname",resultSet.getString("Gname").trim());
                goods.put("uid",String.valueOf(resultSet.getInt("Uid")).trim());
                goods.put("gtime",resultSet.getString("Gtime").trim());
                goods.put("gtype",resultSet.getString("Gtype").trim());
                goods.put("gdetail",resultSet.getString("Gdetail").trim());
                goods.put("gprice",String.valueOf(resultSet.getDouble("Gprice")).trim());
                goods.put("goprice",String.valueOf(resultSet.getDouble("Goprice")).trim());
                goods.put("gimage",resultSet.getString("Gimage").trim());
                goods.put("gstate",String.valueOf(resultSet.getInt("Gstate")).trim());
                goods.put("gemergent",String.valueOf(resultSet.getInt("Gemergent")).trim());
                goods.put("ggetway",resultSet.getString("Ggetway").trim());
                goods.put("ghownew",resultSet.getString("Ghownew").trim());
                goods.put("gscannum",String.valueOf(resultSet.getInt("Gscannum")).trim());
                goods.put("gaddress",resultSet.getString("Gaddress").trim());
                System.out.println(goods);
                goodslist.add(goods);
            } 
        } catch (SQLException ex) {
            Logger.getLogger(GoodsDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
        return goodslist;
    }   
    /**
     * 查询给定商品ID的商品并返回JSONObject类型的数据
     *
     * @param Gid 给定的货物ID
     * @return 查询到的封装了详细信息的Goods对象
     */
    public static JSONObject qGoodsState(String Gid) {
        JSONObject goods = new JSONObject();
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("SELECT * FROM goods WHERE Gid=?");
        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, Gid);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                goods.put("gid",String.valueOf(resultSet.getInt("Gid")).trim());
                goods.put("gname",resultSet.getString("Gname").trim());
                goods.put("uid",String.valueOf(resultSet.getInt("Uid")).trim());
                goods.put("gtime",resultSet.getString("Gtime").trim());
                goods.put("gtype",resultSet.getString("Gtype").trim());
                goods.put("gdetail",resultSet.getString("Gdetail").trim());
                goods.put("gprice",String.valueOf(resultSet.getDouble("Gprice")).trim());
                goods.put("goprice",String.valueOf(resultSet.getDouble("Goprice")).trim());
                goods.put("gimage",resultSet.getString("Gimage").trim());
                goods.put("gstate",String.valueOf(resultSet.getInt("Gstate")).trim());
                goods.put("gemergent",String.valueOf(resultSet.getInt("Gemergent")).trim());
                goods.put("ggetway",resultSet.getString("Ggetway").trim());
                goods.put("ghownew",resultSet.getString("Ghownew").trim());
                goods.put("gscannum",String.valueOf(resultSet.getInt("Gscannum")).trim());
                goods.put("gaddress",resultSet.getString("Gaddress").trim());
                System.out.println(goods);
            } 
        } catch (SQLException ex) {
            Logger.getLogger(GoodsDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
        return goods;
    }   
     /**
     * 根据商品的状态查询急售商品
     *
     * @param emergent 商品是否紧急
     * @param state 给定的货物是否状态
     * @return 查询到的封装了详细信息的JSONArray对象
     */
    public static JSONArray queryEmergent(String emergent,String state) {
        JSONArray goodslist = new JSONArray();
        JSONObject goods = new JSONObject();
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("SELECT * FROM goods WHERE Gemergent=? AND Gstate=?");
        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, emergent);
            preparedStatement.setString(2, state);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                goods.put("gid",String.valueOf(resultSet.getInt("Gid")).trim());
                goods.put("gname",resultSet.getString("Gname").trim());
                goods.put("uid",String.valueOf(resultSet.getInt("Uid")).trim());
                goods.put("gtime",resultSet.getString("Gtime").trim());
                goods.put("gtype",resultSet.getString("Gtype").trim());
                goods.put("gdetail",resultSet.getString("Gdetail").trim());
                goods.put("gprice",String.valueOf(resultSet.getDouble("Gprice")).trim());
                goods.put("goprice",String.valueOf(resultSet.getDouble("Goprice")).trim());
                goods.put("gimage",resultSet.getString("Gimage").trim());
                goods.put("gstate",String.valueOf(resultSet.getInt("Gstate")).trim());
                goods.put("gemergent",String.valueOf(resultSet.getInt("Gemergent")).trim());
                goods.put("ggetway",resultSet.getString("Ggetway").trim());
                goods.put("ghownew",resultSet.getString("Ghownew").trim());
                goods.put("gscannum",String.valueOf(resultSet.getInt("Gscannum")).trim());
                goods.put("gaddress",resultSet.getString("Gaddress").trim());
                goodslist.add(goods);
            } 
        } catch (SQLException ex) {
            Logger.getLogger(GoodsDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
        return goodslist;
    }   
    /**
     * 根据三条急售商品数据
     *
     * @param emergent 商品是否紧急
     * @param state 给定的货物是否状态
     * @return 查询到的封装了详细信息的JSONArray对象
     */
    public static JSONArray queryThreeEmergent(String emergent,String state) {
        JSONArray goodslist = new JSONArray();
        JSONObject goods = new JSONObject();
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("SELECT * FROM goods WHERE Gemergent=? AND Gstate=? ORDER BY Gtime DESC LIMIT 3");
        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, emergent);
            preparedStatement.setString(2, state);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                goods.put("gid",String.valueOf(resultSet.getInt("Gid")).trim());
                goods.put("gname",resultSet.getString("Gname").trim());
                goods.put("uid",String.valueOf(resultSet.getInt("Uid")).trim());
                goods.put("gtime",resultSet.getString("Gtime").trim());
                goods.put("gtype",resultSet.getString("Gtype").trim());
                goods.put("gdetail",resultSet.getString("Gdetail").trim());
                goods.put("gprice",String.valueOf(resultSet.getDouble("Gprice")).trim());
                goods.put("goprice",String.valueOf(resultSet.getDouble("Goprice")).trim());
                goods.put("gimage",resultSet.getString("Gimage").trim());
                goods.put("gstate",String.valueOf(resultSet.getInt("Gstate")).trim());
                goods.put("gemergent",String.valueOf(resultSet.getInt("Gemergent")).trim());
                goods.put("ggetway",resultSet.getString("Ggetway").trim());
                goods.put("ghownew",resultSet.getString("Ghownew").trim());
                goods.put("gscannum",String.valueOf(resultSet.getInt("Gscannum")).trim());
                goods.put("gaddress",resultSet.getString("Gaddress").trim());
                goodslist.add(goods);
            } 
        } catch (SQLException ex) {
            Logger.getLogger(GoodsDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
        return goodslist;
    }   
    /**
     * 根据商品的状态查询商品类型
     *
     * @param type 商品类型
     * @param state 给定的货物是否状态
     * @return 查询到的封装了详细信息的JSONArray对象
     */
    public static JSONArray queryType(String type,String state) {
        JSONArray goodslist = new JSONArray();
        JSONObject goods = new JSONObject();
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("SELECT * FROM goods WHERE Gtype=? AND Gstate=?");
        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, type);
            preparedStatement.setString(2, state);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                goods.put("gid",String.valueOf(resultSet.getInt("Gid")).trim());
                goods.put("gname",resultSet.getString("Gname").trim());
                goods.put("uid",String.valueOf(resultSet.getInt("Uid")).trim());
                goods.put("gtime",resultSet.getString("Gtime").trim());
                goods.put("gtype",resultSet.getString("Gtype").trim());
                goods.put("gdetail",resultSet.getString("Gdetail").trim());
                goods.put("gprice",String.valueOf(resultSet.getDouble("Gprice")).trim());
                goods.put("goprice",String.valueOf(resultSet.getDouble("Goprice")).trim());
                goods.put("gimage",resultSet.getString("Gimage").trim());
                goods.put("gstate",String.valueOf(resultSet.getInt("Gstate")).trim());
                goods.put("gemergent",String.valueOf(resultSet.getInt("Gemergent")).trim());
                goods.put("ggetway",resultSet.getString("Ggetway").trim());
                goods.put("ghownew",resultSet.getString("Ghownew").trim());
                goods.put("gscannum",String.valueOf(resultSet.getInt("Gscannum")).trim());
                goods.put("gaddress",resultSet.getString("Gaddress").trim());
                goodslist.add(goods);
            } 
        } catch (SQLException ex) {
            Logger.getLogger(GoodsDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
        return goodslist;
    }
    /**
     * 根据商品的状态查询免费商品
     *
     * @param state 给定的货物是否状态
     * @return 查询到的封装了详细信息的JSONArray对象
     */
    public static JSONArray queryFree(String state) {
        JSONArray goodslist = new JSONArray();
        JSONObject goods = new JSONObject();
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("SELECT * FROM goods WHERE Gprice = 0 AND Gstate=?");
        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, state);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                goods.put("gid",String.valueOf(resultSet.getInt("Gid")).trim());
                goods.put("gname",resultSet.getString("Gname").trim());
                goods.put("uid",String.valueOf(resultSet.getInt("Uid")).trim());
                goods.put("gtime",resultSet.getString("Gtime").trim());
                goods.put("gtype",resultSet.getString("Gtype").trim());
                goods.put("gdetail",resultSet.getString("Gdetail").trim());
                goods.put("gprice",String.valueOf(resultSet.getDouble("Gprice")).trim());
                goods.put("goprice",String.valueOf(resultSet.getDouble("Goprice")).trim());
                goods.put("gimage",resultSet.getString("Gimage").trim());
                goods.put("gstate",String.valueOf(resultSet.getInt("Gstate")).trim());
                goods.put("gemergent",String.valueOf(resultSet.getInt("Gemergent")).trim());
                goods.put("ggetway",resultSet.getString("Ggetway").trim());
                goods.put("ghownew",resultSet.getString("Ghownew").trim());
                goods.put("gscannum",String.valueOf(resultSet.getInt("Gscannum")).trim());
                goods.put("gaddress",resultSet.getString("Gaddress").trim());
                goodslist.add(goods);
            } 
        } catch (SQLException ex) {
            Logger.getLogger(GoodsDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
        return goodslist;
    }
    /**
     * 根据商品的状态查询免费商品
     *
     * @param uid 用户id
     * @param state 给定的货物是否状态
     * @return 查询到的封装了详细信息的JSONArray对象
     */
    public static JSONArray queryMyfree(String uid,String state) {
        JSONArray goodslist = new JSONArray();
        JSONObject goods = new JSONObject();
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("SELECT * FROM goods WHERE Gprice = 0 AND Gstate=? AND Uid=?");
        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, state);
            preparedStatement.setString(2, uid);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                goods.put("gid",String.valueOf(resultSet.getInt("Gid")).trim());
                goods.put("gname",resultSet.getString("Gname").trim());
                goods.put("uid",String.valueOf(resultSet.getInt("Uid")).trim());
                goods.put("gtime",resultSet.getString("Gtime").trim());
                goods.put("gtype",resultSet.getString("Gtype").trim());
                goods.put("gdetail",resultSet.getString("Gdetail").trim());
                goods.put("gprice",String.valueOf(resultSet.getDouble("Gprice")).trim());
                goods.put("goprice",String.valueOf(resultSet.getDouble("Goprice")).trim());
                goods.put("gimage",resultSet.getString("Gimage").trim());
                goods.put("gstate",String.valueOf(resultSet.getInt("Gstate")).trim());
                goods.put("gemergent",String.valueOf(resultSet.getInt("Gemergent")).trim());
                goods.put("ggetway",resultSet.getString("Ggetway").trim());
                goods.put("ghownew",resultSet.getString("Ghownew").trim());
                goods.put("gscannum",String.valueOf(resultSet.getInt("Gscannum")).trim());
                goods.put("gaddress",resultSet.getString("Gaddress").trim());
                goodslist.add(goods);
            } 
        } catch (SQLException ex) {
            Logger.getLogger(GoodsDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
        return goodslist;
    }
    /**
     * 根据商品的状态和发布地点查询商品
     *
     * @param location 商品类型
     * @param state 给定的货物是否状态
     * @return 查询到的封装了详细信息的JSONArray对象
     */
    public static JSONArray queryLocation(String location,String state) {
        JSONArray goodslist = new JSONArray();
        JSONObject goods = new JSONObject();
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String goodsLocation = "%" + location + "%";
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("SELECT * FROM goods WHERE Gaddress like ? AND Gstate=?");
        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, goodsLocation);
            preparedStatement.setString(2, state);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                goods.put("gid",String.valueOf(resultSet.getInt("Gid")).trim());
                goods.put("gname",resultSet.getString("Gname").trim());
                goods.put("uid",String.valueOf(resultSet.getInt("Uid")).trim());
                goods.put("gtime",resultSet.getString("Gtime").trim());
                goods.put("gtype",resultSet.getString("Gtype").trim());
                goods.put("gdetail",resultSet.getString("Gdetail").trim());
                goods.put("gprice",String.valueOf(resultSet.getDouble("Gprice")).trim());
                goods.put("goprice",String.valueOf(resultSet.getDouble("Goprice")).trim());
                goods.put("gimage",resultSet.getString("Gimage").trim());
                goods.put("gstate",String.valueOf(resultSet.getInt("Gstate")).trim());
                goods.put("gemergent",String.valueOf(resultSet.getInt("Gemergent")).trim());
                goods.put("ggetway",resultSet.getString("Ggetway").trim());
                goods.put("ghownew",resultSet.getString("Ghownew").trim());
                goods.put("gscannum",String.valueOf(resultSet.getInt("Gscannum")).trim());
                goods.put("gaddress",resultSet.getString("Gaddress").trim());
                goodslist.add(goods);
            } 
        } catch (SQLException ex) {
            Logger.getLogger(GoodsDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
        return goodslist;
    }
    /**
     * 根据商品的状态和用户id查询商品
     *
     * @param uid 用户id
     * @param state 给定的货物是否状态
     * @return 查询到的封装了详细信息的JSONArray对象
     */
    public static JSONArray queryMypublish(String uid,String state) {
        JSONArray goodslist = new JSONArray();
        JSONObject goods = new JSONObject();
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("SELECT * FROM goods WHERE Uid=? AND Gstate=?");
        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, uid);
            preparedStatement.setString(2, state);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                goods.put("gid",String.valueOf(resultSet.getInt("Gid")).trim());
                goods.put("gname",resultSet.getString("Gname").trim());
                goods.put("uid",String.valueOf(resultSet.getInt("Uid")).trim());
                goods.put("gtime",resultSet.getString("Gtime").trim());
                goods.put("gtype",resultSet.getString("Gtype").trim());
                goods.put("gdetail",resultSet.getString("Gdetail").trim());
                goods.put("gprice",String.valueOf(resultSet.getDouble("Gprice")).trim());
                goods.put("goprice",String.valueOf(resultSet.getDouble("Goprice")).trim());
                goods.put("gimage",resultSet.getString("Gimage").trim());
                goods.put("gstate",String.valueOf(resultSet.getInt("Gstate")).trim());
                goods.put("gemergent",String.valueOf(resultSet.getInt("Gemergent")).trim());
                goods.put("ggetway",resultSet.getString("Ggetway").trim());
                goods.put("ghownew",resultSet.getString("Ghownew").trim());
                goods.put("gscannum",String.valueOf(resultSet.getInt("Gscannum")).trim());
                goods.put("gaddress",resultSet.getString("Gaddress").trim());
                goodslist.add(goods);
            } 
        } catch (SQLException ex) {
            Logger.getLogger(GoodsDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
        return goodslist;
    }
     /**
     * 查询给定商品状态的商品
     *
     * @param state 给定的货物是否状态
     * @return 查询到的封装了详细信息的JSONArray对象
     */
    public static JSONArray queryState(String state) {
        JSONArray goodslist = new JSONArray();
        JSONObject goods = new JSONObject();
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("SELECT * FROM goods WHERE Gstate=?");
        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, state);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                goods.put("gid",String.valueOf(resultSet.getInt("Gid")).trim());
                goods.put("gname",resultSet.getString("Gname").trim());
                goods.put("uid",String.valueOf(resultSet.getInt("Uid")).trim());
                goods.put("gtime",resultSet.getString("Gtime").trim());
                goods.put("gtype",resultSet.getString("Gtype").trim());
                goods.put("gdetail",resultSet.getString("Gdetail").trim());
                goods.put("gprice",String.valueOf(resultSet.getDouble("Gprice")).trim());
                goods.put("goprice",String.valueOf(resultSet.getDouble("Goprice")).trim());
                goods.put("gimage",resultSet.getString("Gimage").trim());
                goods.put("gstate",String.valueOf(resultSet.getInt("Gstate")).trim());
                goods.put("gemergent",String.valueOf(resultSet.getInt("Gemergent")).trim());
                goods.put("ggetway",resultSet.getString("Ggetway").trim());
                goods.put("ghownew",resultSet.getString("Ghownew").trim());
                goods.put("gscannum",String.valueOf(resultSet.getInt("Gscannum")).trim());
                goods.put("gaddress",resultSet.getString("Gaddress").trim());
                goodslist.add(goods);
            } 
        } catch (SQLException ex) {
            Logger.getLogger(GoodsDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
        return goodslist;
    }   
    /**
     * 查询给定商品ID的详细信息
     *
     * @param gid 给定的商品名
     * @return 查询到的封装了详细信息的Goods对象
     */
    public static Goods queryGid(String gid) {
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("SELECT * FROM goods WHERE Gid=?");

        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, gid);
            
            resultSet = preparedStatement.executeQuery();
            Goods goods = new Goods();
            if (resultSet.next()) {
                goods.setId(resultSet.getInt("Gid"));
                goods.setGname(resultSet.getString("Gname"));
                goods.setUid(resultSet.getInt("Uid"));
                goods.setGtime(resultSet.getString("Gtime"));
                goods.setGtype(resultSet.getString("Gtype"));
                goods.setGdetail(resultSet.getString("Gdetail"));
                goods.setGprice(resultSet.getDouble("Gprice"));
                goods.setGoprice(resultSet.getDouble("Goprice"));
                goods.setGimage(resultSet.getString("Gimage"));
                goods.setGstate(resultSet.getInt("Gstate"));
                goods.setGemergent(resultSet.getInt("Gemergent"));
                goods.setGgetway(resultSet.getString("Ggetway"));
                goods.setGhownew(resultSet.getString("Ghownew"));
                goods.setGscannum(resultSet.getInt("Gscannum"));
                goods.setGaddress(resultSet.getString("Gaddress"));
                System.out.println(goods);
                return goods;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(GoodsDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
    }
    /**
     * 插入商品的详细信息
     *
     * @param goods 给定的商品信息
     * @return 验证结果
     */
    public static int insertGoods(Goods goods) {
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("INSERT INTO goods (Gname,Uid,Gtime,Gtype,Gdetail,Gprice,Goprice,"
                + "Gimage,Gstate,Gemergent,Ggetway,Ghownew,Gscannum,Gaddress) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        int rs = 0;
        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, goods.getGname().trim());
            preparedStatement.setString(2, String.valueOf(goods.getUid()).trim());
            preparedStatement.setString(3, goods.getGtime().trim());
            preparedStatement.setString(4, goods.getGtype().trim());
            preparedStatement.setString(5, goods.getGdetail().trim());
            preparedStatement.setString(6, String.valueOf(goods.getGprice()).trim());
            preparedStatement.setString(7, String.valueOf(goods.getGoprice()).trim());
            preparedStatement.setString(8, goods.getGimage().trim());
            preparedStatement.setString(9, String.valueOf(goods.getGstate()).trim());
            preparedStatement.setString(10, String.valueOf(goods.getGemergent()).trim());
            preparedStatement.setString(11, goods.getGgetway().trim());
            preparedStatement.setString(12, goods.getGhownew().trim());
            preparedStatement.setString(13, String.valueOf(goods.getGscannum()).trim());
            preparedStatement.setString(14, goods.getGaddress().trim());
            rs = preparedStatement.executeUpdate();
            System.out.println("商品插入成功！");
        } catch (SQLException ex) {
            Logger.getLogger(GoodsDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
        return rs;
    }
    /**
     * 更新商品的详细信息
     *
     * @param gid 给定的商品id
     * @param indexming 要修改的字段名
     * @param indexzhi 要修改的字段值
     * @return 验证结果
     */
    public static int RefreshGoods(String gid, String indexming, String indexzhi) {
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        switch(indexming){
            case "Gname":sqlStatement.append("UPDATE goods SET Gname=? WHERE Gid=?");break;
            case "Uid":sqlStatement.append("UPDATE goods SET Uid=? WHERE Gid=?");break;
            case "Gtime":sqlStatement.append("UPDATE goods SET Gtime=? WHERE Gid=?");break;
            case "Gtype":sqlStatement.append("UPDATE goods SET Gtype=? WHERE Gid=?");break;
            case "Gdetail":sqlStatement.append("UPDATE goods SET Gdetail=? WHERE Gid=?");break;
            case "Gprice":sqlStatement.append("UPDATE goods SET Gprice=? WHERE Gid=?");break;
            case "Goprice":sqlStatement.append("UPDATE goods SET Goprice=? WHERE Gid=?");break;
            case "Gimage":sqlStatement.append("UPDATE goods SET Gimage=? WHERE Gid=?");break;
            case "Gstate":sqlStatement.append("UPDATE goods SET Gstate=? WHERE Gid=?");break;
            case "Gemergent":sqlStatement.append("UPDATE goods SET Gemergent=? WHERE Gid=?");break;
            case "Ggetway":sqlStatement.append("UPDATE goods SET Ggetway=? WHERE Gid=?");break;
            case "Ghownew":sqlStatement.append("UPDATE goods SET Ghownew=? WHERE Gid=?");break;
            case "Gscannum":sqlStatement.append("UPDATE goods SET Gscannum=? WHERE Gid=?");break;
            case "Gaddress":sqlStatement.append("UPDATE goods SET Gaddress=? WHERE Gid=?");break;
            default:System.out.println("修改失败！");break;
        }
        int rs = 0;
        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, indexzhi.trim());
            preparedStatement.setString(2, gid.trim());
            System.out.println(preparedStatement);
            rs = preparedStatement.executeUpdate();
            System.out.println("修改成功！");
        } catch (SQLException ex) {
            Logger.getLogger(GoodsDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
        return rs;
    }
    /**
     * 更新商品的详细信息
     *
     * @param goods 给定的商品信息
     * @return 验证结果
     */
    public static int RefreshGoodsData(Goods goods) {
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("UPDATE goods SET Gname=?,Gtype=?,Gdetail=?,Gprice=?,Goprice=?,"
                + "Gemergent=?,Ggetway=?,Ghownew=?,Gaddress=? WHERE Gid=?");
        int rs = 0;
        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, goods.getGname().trim());
            preparedStatement.setString(2, goods.getGtype().trim());
            preparedStatement.setString(3, goods.getGdetail().trim());
            preparedStatement.setString(4, String.valueOf(goods.getGprice()).trim());
            preparedStatement.setString(5, String.valueOf(goods.getGoprice()).trim());
            preparedStatement.setString(6, String.valueOf(goods.getGemergent()).trim());
            preparedStatement.setString(7, goods.getGgetway().trim());
            preparedStatement.setString(8, goods.getGhownew().trim());
            preparedStatement.setString(9, goods.getGaddress().trim());
            preparedStatement.setString(10, String.valueOf(goods.getId()).trim());
            rs = preparedStatement.executeUpdate();
            System.out.println("商品修改成功！");
        } catch (SQLException ex) {
            Logger.getLogger(GoodsDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
        return rs;
    }
    /**
     * 删除商品
     *
     * @param gid 给定的商品id
     * @return 验证结果
     */
    public static int DeleteGoods(String gid) {
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("DELETE FROM goods WHERE Gid=?");
        int rs = 0;
        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, gid.trim());
            System.out.println(preparedStatement);
            rs = preparedStatement.executeUpdate();
            System.out.println("删除成功！");
        } catch (SQLException ex) {
            Logger.getLogger(GoodsDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
        return rs;
    }
}
