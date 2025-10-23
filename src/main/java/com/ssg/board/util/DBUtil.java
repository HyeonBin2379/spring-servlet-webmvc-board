package com.ssg.board.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;

public enum DBUtil {

    //    Connection에 관한 객체가 싱글톤임을 보장
    INSTANCE;

    private final HikariDataSource ds;

    DBUtil() {
        HikariConfig config = new HikariConfig();
        // MySQL 드라이버 로드 및 연결할 DB 지정
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setJdbcUrl("jdbc:mysql://localhost:3306/boardServletDB?serverTimezone=Asia/Seoul&charEncoding=UTF-8");
        config.setUsername("root");
        config.setPassword("mysql1234");

        // 커넥션 설정 추가
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        this.ds = new HikariDataSource(config);
    }

    // ConnectionUtil.INSTANCE.getConnection()으로 Connection을 반환받음
    public Connection getConnection() throws Exception {
        return ds.getConnection();
    }
}
