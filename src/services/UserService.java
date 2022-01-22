package services;

import helpers.DatabaseConnection;

import java.sql.*;

public class UserService{
    DatabaseConnection dbConn = new DatabaseConnection();
    Connection conn = dbConn.myConnection;

    public void registerUser(String userId,String email, String password) throws SQLException {
        String sql = "INSERT INTO users(userId,email,password) VALUES(?,?,?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1,userId);
        statement.setString(2,email);
        statement.setString(3,password);
        statement.executeUpdate();
    }

    public Boolean checkEmail(String email) throws SQLException {
        String sql = "SELECT * FROM users WHERE email='"+email+"'";
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql);
        int rowCount = 0;
        while(result.next()){
            rowCount++;
        }
        if(rowCount > 0){
            return true;
        }
        return false;
    }

    public String checkCredentials(String email, String password) throws SQLException {
        String sql = "SELECT * FROM users WHERE email='"+email+"' and password='"+password+"'";
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql);
        int rowCount = 0;
        while(result.next()){
            rowCount++;
            if(rowCount == 1){
                return result.getString("userId");
            }
        }
        return "";
    }
}
