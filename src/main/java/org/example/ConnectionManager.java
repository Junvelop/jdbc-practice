package org.example;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {


    private static final String DB_URL = "jdbc:h2:mem://localhost/~/jdbc-practice;MODE=MySQL;DB_CLOSE_DELAY=-1";
    private static final String DB_DRIVER = "org.h2.Driver";
    public static final int MAX_POOL_SIZE = 40;

    private static final DataSource ds;

    static{

        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource. setDriverClassName(DB_DRIVER); // com + option + c -> 사용한 변수 이름 한번에 바꿀 수 있음
        hikariDataSource.setJdbcUrl(DB_URL);
        hikariDataSource.setUsername("sa");
        hikariDataSource.setPassword("");
        hikariDataSource.setMaximumPoolSize(MAX_POOL_SIZE); // 커넥션 풀의 최댓값 설정 -> 많으면 메모리 소모가 높아질 수 있음
        hikariDataSource.setMinimumIdle(MAX_POOL_SIZE); // 커넥션 풀의 최솟값 설정

        ds = hikariDataSource;
    }

    public static Connection getConnection() {
        try {
            return ds.getConnection();
        }
        catch (SQLException e){
            throw new IllegalStateException(e);
        }


    }

    public static DataSource getDataSource(){
        return ds;
    }

}
