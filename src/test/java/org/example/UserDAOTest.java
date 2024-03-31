package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class UserDAOTest {

    @BeforeEach
    void setUp() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator(); // 스크립트 생성
        populator.addScript(new ClassPathResource("db_schema.sql")); // db_scgema.sql이라는 파일을 클래스 패스 리소스로 읽어와서 스크립트에 추가해줬음
        DatabasePopulatorUtils.execute(populator, ConnectionManager.getDataSource());
    }

    @Test
    void createTest() throws SQLException {

        UserDao userDao = new UserDao();
        userDao.create(new User("wizard", "password", "name", "email"));

        User user = userDao.findByUserId("wizard");
        assertThat(user).isEqualTo(new User("wizard", "password", "name", "email"));
    }
}

// 7분 30초
