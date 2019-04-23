/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuctt.db;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Thien Phuc
 */
public class DBConnection {
    public static Connection getConnection() throws Exception {
        Connection conn = null;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1456;databaseName=Struts2ValidationDemo", "sa", "19091999+");
        return conn;
    }
}
