package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class jdbcTemplate {

    public void executeUpdate(User user, String spl, PreparedStatementSetter pss) throws SQLException {
        Connection con = null;
        PreparedStatement pstnt = null;

        try{

            con = ConnectionManager.getConnection();
            String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
            pstnt = con.prepareStatement(sql);
            pss.setter(pstnt);

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

}
