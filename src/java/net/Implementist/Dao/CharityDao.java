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
import net.Implementist.DB.Charity;
import net.Implementist.Utils.DBManager;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 公益活动的数据库操作类
 * @author dell
 */
public class CharityDao {
    /**
     * 查询给定公益ID的详细信息
     *
     * @param cid 给定的公益id
     * @return 查询到的封装了详细信息的Charity对象
     */
    public static Charity queryCid(String cid) {
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("SELECT * FROM charity WHERE Cid=?");

        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, cid);
            resultSet = preparedStatement.executeQuery();
            Charity charity = new Charity();
            if (resultSet.next()) {
                charity.setId(resultSet.getInt("Cid"));
                charity.setCname(resultSet.getString("Cname"));
                charity.setUid(resultSet.getInt("Uid"));
                charity.setCimage(resultSet.getString("Cimage"));
                charity.setCdetail(resultSet.getString("Cdetail"));
                charity.setCneed(resultSet.getString("Cneed"));
                charity.setCnumber(resultSet.getInt("Cnumber"));
                charity.setCtime(resultSet.getString("Ctime"));
                charity.setCdeadline(resultSet.getString("Cdeadline"));
                charity.setCtype(resultSet.getString("Ctype"));
                charity.setCaddress(resultSet.getString("Caddress"));
                charity.setCscannum(resultSet.getInt("Cscannum"));
                charity.setCjoinnum(resultSet.getInt("Cjoinnum"));
                charity.setCstate(resultSet.getInt("Cstate"));
                System.out.println(charity);
                return charity;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CharityDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
    }
    /**
     * 查询给定公益状态的详细信息
     *
     * @param cid 给定的公益id
     * @return 查询到的封装了详细信息的JSONArray对象
     */
    public static JSONObject queryCharityByCid(String cid) {
        JSONObject charity = new JSONObject();
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        
        sqlStatement.append("SELECT * FROM charity WHERE Cid=?");
        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, cid);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                charity.put("cid",String.valueOf(resultSet.getInt("Cid")));
                charity.put("cname",resultSet.getString("Cname"));
                charity.put("uid",String.valueOf(resultSet.getInt("Uid")));
                charity.put("cimage",resultSet.getString("Cimage"));
                charity.put("cdetail",resultSet.getString("Cdetail"));
                charity.put("cneed",resultSet.getString("Cneed"));
                charity.put("cnumber",String.valueOf(resultSet.getInt("Cnumber")));
                charity.put("ctime",resultSet.getString("Ctime"));
                charity.put("cdeadline",resultSet.getString("Cdeadline"));
                charity.put("ctype",resultSet.getString("Ctype"));
                charity.put("caddress",resultSet.getString("Caddress"));
                charity.put("cscannum",String.valueOf(resultSet.getInt("Cscannum")));
                charity.put("cjoinnum",String.valueOf(resultSet.getInt("Cjoinnum")));
                charity.put("cstate",String.valueOf(resultSet.getInt("Cstate")));
                System.out.println(charity);
            } 
        } catch (SQLException ex) {
            Logger.getLogger(CharityDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
        return charity;
    }
    /**
     * 查询给定公益状态的详细信息
     *
     * @param cstate 给定的公益状态
     * @return 查询到的封装了详细信息的JSONArray对象
     */
    public static JSONArray queryCharity(String cstate) {
        JSONArray charitylist = new JSONArray();
        JSONObject charity = new JSONObject();
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        
        sqlStatement.append("SELECT * FROM charity WHERE Cstate=?");
        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, cstate);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                charity.put("cid",String.valueOf(resultSet.getInt("Cid")));
                charity.put("cname",resultSet.getString("Cname"));
                charity.put("uid",String.valueOf(resultSet.getInt("Uid")));
                charity.put("cimage",resultSet.getString("Cimage"));
                charity.put("cdetail",resultSet.getString("Cdetail"));
                charity.put("cneed",resultSet.getString("Cneed"));
                charity.put("cnumber",String.valueOf(resultSet.getInt("Cnumber")));
                charity.put("ctime",resultSet.getString("Ctime"));
                charity.put("cdeadline",resultSet.getString("Cdeadline"));
                charity.put("ctype",resultSet.getString("Ctype"));
                charity.put("caddress",resultSet.getString("Caddress"));
                charity.put("cscannum",String.valueOf(resultSet.getInt("Cscannum")));
                charity.put("cjoinnum",String.valueOf(resultSet.getInt("Cjoinnum")));
                charity.put("cstate",String.valueOf(resultSet.getInt("Cstate")));
                charitylist.add(charity);
                System.out.println(charity);
            } 
        } catch (SQLException ex) {
            Logger.getLogger(CharityDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
        return charitylist;
    }
    /**
     * 查询给定公益ID的详细信息
     *
     * @param uid 给定的用户id
     * @return 查询到的封装了详细信息的JSONArray对象
     */
    public static JSONArray queryMypublish(String uid) {
        JSONArray charitylist = new JSONArray();
        JSONObject charity = new JSONObject();
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        
        sqlStatement.append("SELECT * FROM charity WHERE Uid=? AND (Cstate=1 OR Cstate=2)");
        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, uid);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                charity.put("cid",String.valueOf(resultSet.getInt("Cid")));
                charity.put("cname",resultSet.getString("Cname"));
                charity.put("uid",String.valueOf(resultSet.getInt("Uid")));
                charity.put("cimage",resultSet.getString("Cimage"));
                charity.put("cdetail",resultSet.getString("Cdetail"));
                charity.put("cneed",resultSet.getString("Cneed"));
                charity.put("cnumber",String.valueOf(resultSet.getInt("Cnumber")));
                charity.put("ctime",resultSet.getString("Ctime"));
                charity.put("cdeadline",resultSet.getString("Cdeadline"));
                charity.put("ctype",resultSet.getString("Ctype"));
                charity.put("caddress",resultSet.getString("Caddress"));
                charity.put("cscannum",String.valueOf(resultSet.getInt("Cscannum")));
                charity.put("cjoinnum",String.valueOf(resultSet.getInt("Cjoinnum")));
                charity.put("cstate",String.valueOf(resultSet.getInt("Cstate")));
                charitylist.add(charity);
                System.out.println(charity);
            } 
        } catch (SQLException ex) {
            Logger.getLogger(CharityDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
        return charitylist;
    }
    /**
     * 插入公益的详细信息
     *
     * @param charity 给定的公益信息
     * @return 验证结果
     */
    public static int insertCharity(Charity charity) {
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("INSERT INTO charity (Cname,Uid,Cimage,Cdetail,Cneed,Cnumber,Ctime,Cdeadline,"
                + "Ctype,Caddress,Cscannum,Cjoinnum,Cstate) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");
        int rs = 0;
        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, charity.getCname().trim());
            preparedStatement.setString(2, String.valueOf(charity.getUid()).trim());
            preparedStatement.setString(3, charity.getCimage().trim());
            preparedStatement.setString(4, charity.getCdetail().trim());
            preparedStatement.setString(5, charity.getCneed().trim());
            preparedStatement.setString(6, String.valueOf(charity.getCnumber()).trim());
            preparedStatement.setString(7, charity.getCtime().trim());
            preparedStatement.setString(8, charity.getCdeadline().trim());
            preparedStatement.setString(9, charity.getCtype().trim());
            preparedStatement.setString(10, charity.getCaddress().trim());
            preparedStatement.setString(11, String.valueOf(charity.getCscannum()).trim());
            preparedStatement.setString(12, String.valueOf(charity.getCjoinnum()).trim());
            preparedStatement.setString(13, String.valueOf(charity.getCstate()).trim());
            rs = preparedStatement.executeUpdate();
            System.out.println("公益信息插入成功！");
        } catch (SQLException ex) {
            Logger.getLogger(CharityDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
        return rs;
    }
    /**
     * 更新公益的详细信息
     *
     * @param cid 给定的公益id
     * @param indexming 要修改的字段名
     * @param indexzhi 要修改的字段值
     * @return 验证结果
     */
    public static int RefreshCharity(String cid, String indexming, String indexzhi) {
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        switch(indexming){
            case "Cname":sqlStatement.append("UPDATE charity SET Cname=? WHERE Cid=?");break;
            case "Uid":sqlStatement.append("UPDATE charity SET Uid=? WHERE Cid=?");break;
            case "Cimage":sqlStatement.append("UPDATE charity SET Cimage=? WHERE Cid=?");break;
            case "Cdetail":sqlStatement.append("UPDATE charity SET Cdetail=? WHERE Cid=?");break;
            case "Cneed":sqlStatement.append("UPDATE charity SET Cneed=? WHERE Cid=?");break;
            case "Cnumber":sqlStatement.append("UPDATE charity SET Cnumber=? WHERE Cid=?");break;
            case "Ctime":sqlStatement.append("UPDATE charity SET Ctime=? WHERE Cid=?");break;
            case "Cdeadline":sqlStatement.append("UPDATE charity SET Cdeadline=? WHERE Cid=?");break;
            case "Ctype":sqlStatement.append("UPDATE charity SET Ctype=? WHERE Cid=?");break;
            case "Caddress":sqlStatement.append("UPDATE charity SET Caddress=? WHERE Cid=?");break;
            case "Cscannum":sqlStatement.append("UPDATE charity SET Cscannum=? WHERE Cid=?");break;
            case "Cjoinnum":sqlStatement.append("UPDATE charity SET Cjoinnum=? WHERE Cid=?");break;
            case "Cstate":sqlStatement.append("UPDATE charity SET Cstate=? WHERE Cid=?");break;
            default:System.out.println("修改失败！");break;
        }
        int rs = 0;
        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, indexzhi.trim());
            preparedStatement.setString(2, cid.trim());
            System.out.println(preparedStatement);
            rs = preparedStatement.executeUpdate();
            System.out.println("修改成功！");
        } catch (SQLException ex) {
            Logger.getLogger(CharityDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
        return rs;
    }
    /**
     * 更新公益的详细信息
     *
     * @param charity 给定的求购信息
     * @return 验证结果
     */
    public static int RefreshCharityData(Charity charity) {
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("UPDATE charity SET Cname=?,Cdetail=?,Cneed=?,Cnumber=?,Cdeadline=?,Ctype=?,Caddress=? WHERE Cid=?");
        int rs = 0;
        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, charity.getCname().trim());
            preparedStatement.setString(2, charity.getCdetail().trim());
            preparedStatement.setString(3, charity.getCneed().trim());
            preparedStatement.setString(4, String.valueOf(charity.getCnumber()).trim());
            preparedStatement.setString(5, charity.getCdeadline().trim());
            preparedStatement.setString(6, charity.getCtype().trim());
            preparedStatement.setString(7, charity.getCaddress().trim());
            preparedStatement.setString(8, String.valueOf(charity.getId()).trim());
            rs = preparedStatement.executeUpdate();
            System.out.println("公益修改成功！");
        } catch (SQLException ex) {
            Logger.getLogger(CharityDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
        return rs;
    }
    /**
     * 删除公益
     *
     * @param cid 给定的商品id
     * @return 验证结果
     */
    public static int DeleteCharity(String cid) {
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("DELETE FROM charity WHERE Cid=?");
        int rs = 0;
        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, cid.trim());
            System.out.println(preparedStatement);
            rs = preparedStatement.executeUpdate();
            System.out.println("删除成功！");
        } catch (SQLException ex) {
            Logger.getLogger(CharityDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
        return rs;
    }
}
