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
import net.Implementist.Utils.Token;

/**
 *
 * @author dell
 */
public class TokenDao {
    /**
     * 查询给定ID的token
     *
     * @param id 给定的tokenID
     * @return 查询到的封装了详细信息的token对象
     */
    public static Token queryId(String id) {
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("SELECT * FROM token WHERE id=?");
        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, id);
            System.out.println(preparedStatement);
            resultSet = preparedStatement.executeQuery();
            Token token = new Token();
            if (resultSet.next()) {
                //将数据库中的数据赋值给token
                token.setTokenId(resultSet.getInt("id"));
                token.setAccess_token(resultSet.getString("apptoken"));
                token.setExpires_in(resultSet.getString("expires"));
                token.setApplication(resultSet.getString("application"));
                System.out.println(token);
                return token;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TokenDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
    }
    /**
     * 插入用户的详细信息
     *
     * @param token 
     * @param expires 
     * @param application 
     * @return 验证结果
     */
    public static int insertToken(String token,String expires,String application) {
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        //防止数据库被恶意注入数据
        sqlStatement.append("INSERT INTO token (apptoken,expires,application) VALUES(?,?,?)");
        int rs = 0;
        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, token.trim());
            preparedStatement.setString(2, expires.trim());
            preparedStatement.setString(3, application.trim());
            rs = preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TokenDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
        return rs;
    }
    /**
     * 更新用户的详细信息
     *
     * @param id 给定的id
     * @param indexming 要修改的字段名
     * @param indexzhi 要修改的字段值
     * @return 验证结果
     */
    public static int updateToken(String id, String indexming, String indexzhi) {
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        switch(indexming){
            case "apptoken":sqlStatement.append("UPDATE token SET apptoken=? WHERE id=?");break;
            case "expires":sqlStatement.append("UPDATE token SET expires=? WHERE id=?");break;
            case "application":sqlStatement.append("UPDATE token SET application=? WHERE id=?");break;
        }
        int rs = 0;
        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, indexzhi.trim());
            preparedStatement.setString(2, id.trim());
            System.out.println(preparedStatement);
            rs = preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TokenDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
        return rs;
    }
}
