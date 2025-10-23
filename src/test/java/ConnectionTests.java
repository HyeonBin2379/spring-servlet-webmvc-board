import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionTests {

    @Test
    public void testConnection() throws Exception {
        // 실행 시 최초 1회만 MySQL 드라이버 로딩 -> 싱글톤
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("MySQL 드라이버 로딩 성공");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/boardServletDB?serverTimezone=Asia/Seoul&charEncoding=UTF-8", "root", "mysql1234");

        Assertions.assertNotNull(conn);
        System.out.println(conn);
        conn.close();
    }

    @Test
    public void testHikariCP() throws Exception {
        HikariConfig config = new HikariConfig();
        // 데이터 커넥터풀 환경설정: MySQL 드라이버 로드, 사용할 db 및 사용자 로그인
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setJdbcUrl("jdbc:mysql://localhost:3306/boardServletDB?serverTimezone=Asia/Seoul&charEncoding=UTF-8");
        config.setUsername("root");
        config.setPassword("mysql1234");

        HikariDataSource ds = new HikariDataSource(config);
        Connection conn = ds.getConnection();
        System.out.println(conn);
        conn.close();
    }
}
