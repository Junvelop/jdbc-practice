package org.example;

import java.sql.*;

public class UserDao {

    private Connection getConnection() {

        String url = "jdbc:h2:mem://localhost/~/jdbc-practice;MODE=MySQL;DB_CLOSE_DELAY=-1";
        String id  = "sa";
        String pw = "";

        try{
            Class.forName("org.h2.Driver");
            return DriverManager.getConnection(url, id, pw);
        } catch (Exception ex){
            return  null ;
        }

    }
    public void create(User user) throws SQLException {
        Connection con = null;
        PreparedStatement pstnt = null;

        try{

            con = getConnection();
            String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
            pstnt = con.prepareStatement(sql);
            pstnt.setString(1, user.getUserId());
            pstnt.setString(2, user.getPassword());
            pstnt.setString(3, user.getName());
            pstnt.setString(4, user.getEmail());

            pstnt. executeUpdate();





        } finally {
            if(pstnt != null){
                pstnt.close();
            }

            if(con != null){
                con.close();
            }

        }


    }



    public User findByUserId(String userId) throws SQLException {

        Connection con = null;
        PreparedStatement pstnt = null;
        ResultSet rs = null;
        try {

            con = getConnection();
            String sql = "SELECT userId, password, name, email FROM USERS WHERE userid =?";
            pstnt = con.prepareStatement(sql);
            pstnt.setString(1, userId);

            rs = pstnt.executeQuery();

            User user = null;
            if(rs.next()){
                user = new User(rs.getString("userId"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("email")
                );
            }
            return user;
        }
        finally {
            if (rs != null){
                rs.close();
            }

            if (pstnt != null){
                pstnt.close();
            }

            if (con != null){
                con.close();
            }
        }

    }
}
