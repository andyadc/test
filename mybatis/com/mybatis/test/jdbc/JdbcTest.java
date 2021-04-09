package com.mybatis.test.jdbc;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

/**
 * @author andy.an
 * @since 2019/4/11
 */
public class JdbcTest {

    private static Connection conn;

    @BeforeAll
    public static void getConn() {
        String username = "root";
        String password = "andyadC7,./!";
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://www.qq-server.com:3307/idea?useSSL=false&serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8";

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    public static void close() throws Exception {
        if (conn != null) {
            conn.close();
        }
    }

    @Test
    public void testQuery() throws Exception {
        String sql = "SELECT * FROM demo";
        PreparedStatement statement = conn.prepareStatement(sql);

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            System.out.println(resultSet.getString(1));
        }
        writeMetaData(resultSet);

        resultSet.close();
        statement.close();
    }

    @Test
    public void testDelete() {
        String sql = "DELETE FROM demo WHERE id = " + 1;

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            conn.setAutoCommit(false);
            int row = statement.executeUpdate(sql);

            conn.commit();
            System.out.println(row);

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Test
    public void testBatch() {
        try (Statement statement = conn.createStatement()) {

            conn.setAutoCommit(false);

            for (int i = 0; i < 100; i++) {
                statement.addBatch(
                        "INSERT INTO demo (name, type, status) VALUES "
                                + "(" +
                                "'abc+" + i + "', " // name
                                + i + ", "          // type
                                + i                 // status
                                + ")"
                );
            }

            int counts[] = statement.executeBatch();
            System.out.println(Arrays.toString(counts));

            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();

            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void writeMetaData(ResultSet resultSet) throws Exception {
        //  Now get some metadata from the database
        // Result set get the result of the SQL query
        System.out.println("The columns in the table are: ");

        System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
        for (int i = 1, count = resultSet.getMetaData().getColumnCount(); i <= count; i++) {
            System.out.println("Column " + i + " " + resultSet.getMetaData().getColumnName(i));
        }
    }
}
