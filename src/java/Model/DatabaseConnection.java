/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.ServletException;

/**
 *
 * @author HOME
 */
public class DatabaseConnection {
    public Connection dbConn(Connection conn)throws ServletException{
     try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver Loaded");
        } catch (ClassNotFoundException cnf) {
            System.out.println("Cannot load the Driver" + cnf);
            throw new ServletException(cnf.toString());
        }
        try {
            //conn = DriverManager.getConnection("jdbc:mysql://localhost/libraryu_root?autoReconnect=true", "libraryu_ayush", "Ayushbpl10@gmail.com");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3308/Library", "root", "ayush");
            System.out.println("Connected successfully with database");
            return conn;
        } catch (SQLException sq) {
            System.out.println("Error in Database" + sq);
            throw new ServletException(sq.toString());
        }
        
    }
}
