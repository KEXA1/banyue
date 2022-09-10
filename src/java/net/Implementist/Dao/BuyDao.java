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
import net.Implementist.DB.Buy;
import net.Implementist.Utils.DBManager;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 求购的数据库操作类
 * @author dell
 */
public class BuyDao {
    
    /**
     * 查询给定求购商品ID的详细信息
     *
     * @param bid 给定的求购id
     * @return 查询到的封装了详细信息的Buy对象
     */
    public static Buy queryBid(String bid) {
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("SELECT * FROM buy WHERE Bid=?");

        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, bid);
            
            resultSet = preparedStatement.executeQuery();
            Buy buy = new Buy();
            if (resultSet.next()) {
                buy.setId(resultSet.getInt("Bid"));
                buy.setBname(resultSet.getString("Bname"));
                buy.setUid(resultSet.getInt("Uid"));
                buy.setBdetail(resultSet.getString("Bdetail"));
                buy.setBsprice(resultSet.getDouble("Bsprice"));
                buy.setBbprice(resultSet.getDouble("Bbprice"));
                buy.setBtype(resultSet.getString("Btype"));
                buy.setBgetway(resultSet.getString("Bgetway"));
                buy.setBhownew(resultSet.getString("Bhownew"));
                buy.setBaddress(resultSet.getString("Baddress"));
                buy.setBtime(resultSet.getString("Btime"));
                buy.setBimage(resultSet.getString("Bimage"));
                buy.setBstate(resultSet.getInt("Bstate"));
                buy.setBscannum(resultSet.getInt("Bscannum"));
                System.out.println(buy);
                return buy;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BuyDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
    }
    /**
     * 根据商品的状态和用户id查询商品
     *
     * @param uid 用户id
     * @param state 给定的求购状态
     * @return 查询到的封装了详细信息的JSONArray对象
     */
    public static JSONArray queryMypubbuy(String uid,String state) {
        JSONArray buylist = new JSONArray();
        JSONObject buy = new JSONObject();
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("SELECT * FROM buy WHERE Uid=? AND Bstate=?");
        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, uid);
            preparedStatement.setString(2, state);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                buy.put("bid",String.valueOf(resultSet.getInt("Bid")).trim());
                buy.put("bname",resultSet.getString("Bname").trim());
                buy.put("uid",String.valueOf(resultSet.getInt("Uid")).trim());
                buy.put("bdetail",resultSet.getString("Bdetail").trim());
                buy.put("bsprice",String.valueOf(resultSet.getDouble("Bsprice")).trim());
                buy.put("bbprice",String.valueOf(resultSet.getDouble("Bbprice")).trim());
                buy.put("btype",resultSet.getString("Btype").trim());
                buy.put("bgetway",resultSet.getString("Bgetway").trim());
                buy.put("bhownew",resultSet.getString("Bhownew").trim());
                buy.put("baddress",resultSet.getString("Baddress").trim());
                buy.put("btime",resultSet.getString("Btime").trim());
                buy.put("bimage",resultSet.getString("Bimage").trim());
                buy.put("bstate",String.valueOf(resultSet.getInt("Bstate")).trim());
                buy.put("bscannum",String.valueOf(resultSet.getInt("Bscannum")).trim());
                buylist.add(buy);
            } 
        } catch (SQLException ex) {
            Logger.getLogger(BuyDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
        return buylist;
    }
    /**
     * 根据商品的状态查询商品
     *
     * @param state 给定的求购状态
     * @return 查询到的封装了详细信息的JSONArray对象
     */
    public static JSONArray queryBuy(String state) {
        JSONArray buylist = new JSONArray();
        JSONObject buy = new JSONObject();
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("SELECT * FROM buy WHERE Bstate=?");
        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, state);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                buy.put("bid",String.valueOf(resultSet.getInt("Bid")).trim());
                buy.put("bname",resultSet.getString("Bname").trim());
                buy.put("uid",String.valueOf(resultSet.getInt("Uid")).trim());
                buy.put("bdetail",resultSet.getString("Bdetail").trim());
                buy.put("bsprice",String.valueOf(resultSet.getDouble("Bsprice")).trim());
                buy.put("bbprice",String.valueOf(resultSet.getDouble("Bbprice")).trim());
                buy.put("btype",resultSet.getString("Btype").trim());
                buy.put("bgetway",resultSet.getString("Bgetway").trim());
                buy.put("bhownew",resultSet.getString("Bhownew").trim());
                buy.put("baddress",resultSet.getString("Baddress").trim());
                buy.put("btime",resultSet.getString("Btime").trim());
                buy.put("bimage",resultSet.getString("Bimage").trim());
                buy.put("bstate",String.valueOf(resultSet.getInt("Bstate")).trim());
                buy.put("bscannum",String.valueOf(resultSet.getInt("Bscannum")).trim());
                buylist.add(buy);
            } 
        } catch (SQLException ex) {
            Logger.getLogger(BuyDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
        return buylist;
    }
    /**
     * 插入商品的详细信息
     *
     * @param buy 给定的商品信息
     * @return 验证结果
     */
    public static int insertBuy(Buy buy) {
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("INSERT INTO buy (Bname,Uid,Bdetail,Bsprice,Bbprice,Btype,Bhownew,Bgetway,Baddress,Btime,"
                + "Bimage,Bstate,Bscannum) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");
        int rs = 0;
        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, buy.getBname().trim());
            preparedStatement.setString(2, String.valueOf(buy.getUid()).trim());
            preparedStatement.setString(3, buy.getBdetail().trim());
            preparedStatement.setString(4, String.valueOf(buy.getBsprice()).trim());
            preparedStatement.setString(5, String.valueOf(buy.getBbprice()).trim());
            preparedStatement.setString(6, buy.getBtype().trim());
            preparedStatement.setString(7, buy.getBhownew().trim());
            preparedStatement.setString(8, buy.getBgetway().trim());
            preparedStatement.setString(9, buy.getBaddress().trim());
            preparedStatement.setString(10, buy.getBtime().trim());
            preparedStatement.setString(11, buy.getBimage().trim());
            preparedStatement.setString(12, String.valueOf(buy.getBstate()).trim());
            preparedStatement.setString(13, String.valueOf(buy.getBscannum()).trim());
            rs = preparedStatement.executeUpdate();
            System.out.println("求购插入成功！");
        } catch (SQLException ex) {
            Logger.getLogger(BuyDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
        return rs;
    }
    /**
     * 更新求购的详细信息
     *
     * @param bid 给定的求购id
     * @param indexming 要修改的字段名
     * @param indexzhi 要修改的字段值
     * @return 验证结果
     */
    public static int RefreshBuy(String bid, String indexming, String indexzhi) {
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        switch(indexming){
            case "Bname":sqlStatement.append("UPDATE buy SET Bname=? WHERE Bid=?");break;
            case "Uid":sqlStatement.append("UPDATE buy SET Uid=? WHERE Bid=?");break;
            case "Bdetail":sqlStatement.append("UPDATE buy SET Bdetail=? WHERE Bid=?");break;
            case "Bsprice":sqlStatement.append("UPDATE buy SET Bsprice=? WHERE Bid=?");break;
            case "Bbprice":sqlStatement.append("UPDATE buy SET Bbprice=? WHERE Bid=?");break;
            case "Btype":sqlStatement.append("UPDATE buy SET Btype=? WHERE Bid=?");break;
            case "Bhownew":sqlStatement.append("UPDATE buy SET Bhownew=? WHERE Bid=?");break;
            case "Bgetway":sqlStatement.append("UPDATE buy SET Bgetway=? WHERE Bid=?");break;
            case "Baddress":sqlStatement.append("UPDATE buy SET Baddress=? WHERE Bid=?");break;
            case "Btime":sqlStatement.append("UPDATE buy SET Btime=? WHERE Bid=?");break;
            case "Bimage":sqlStatement.append("UPDATE buy SET Bimage=? WHERE Bid=?");break;
            case "Bstate":sqlStatement.append("UPDATE buy SET Bstate=? WHERE Bid=?");break;
            case "Bscannum":sqlStatement.append("UPDATE buy SET Bscannum=? WHERE Bid=?");break;
            default:System.out.println("修改失败！");break;
        }
        int rs = 0;
        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, indexzhi.trim());
            preparedStatement.setString(2, bid.trim());
            System.out.println(preparedStatement);
            rs = preparedStatement.executeUpdate();
            System.out.println("修改成功！");
        } catch (SQLException ex) {
            Logger.getLogger(BuyDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
        return rs;
    }
    /**
     * 更新求购的详细信息
     *
     * @param buy 给定的求购信息
     * @return 验证结果
     */
    public static int RefreshBuyData(Buy buy) {
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("UPDATE buy SET Bname=?,Bdetail=?,Bsprice=?,Bbprice=?,Btype=?,Bhownew=?,Bgetway=?,Baddress=? WHERE Bid=?");
        int rs = 0;
        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, buy.getBname().trim());
            preparedStatement.setString(2, buy.getBdetail().trim());
            preparedStatement.setString(3, String.valueOf(buy.getBsprice()).trim());
            preparedStatement.setString(4, String.valueOf(buy.getBbprice()).trim());
            preparedStatement.setString(5, buy.getBtype().trim());
            preparedStatement.setString(6, buy.getBhownew().trim());
            preparedStatement.setString(7, buy.getBgetway().trim());
            preparedStatement.setString(8, buy.getBaddress().trim());
            preparedStatement.setString(9, String.valueOf(buy.getId()).trim());
            rs = preparedStatement.executeUpdate();
            System.out.println("求购修改成功！");
        } catch (SQLException ex) {
            Logger.getLogger(BuyDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
        return rs;
    }
    /**
     * 删除求购商品
     *
     * @param bid 给定的商品id
     * @return 验证结果
     */
    public static int DeleteBuy(String bid) {
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("DELETE FROM buy WHERE Bid=?");
        int rs = 0;
        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, bid.trim());
            System.out.println(preparedStatement);
            rs = preparedStatement.executeUpdate();
            System.out.println("删除成功！");
        } catch (SQLException ex) {
            Logger.getLogger(BuyDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
        return rs;
    }
    
}
