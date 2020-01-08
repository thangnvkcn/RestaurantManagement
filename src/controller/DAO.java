/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Admin
 */
public class DAO {
    //protected  static Connection con;
    public static Connection con;

    public DAO() {
    }
    protected static void getInstance(){
        
        if(con==null){
            String dbUrl = "jdbc:mysql://127.0.0.1/restaurant";
           
            String dbClass = "com.mysql.jdbc.Driver";
           
            try {
                Class.forName(dbClass);
                con = DriverManager.getConnection(dbUrl,"root","");
                System.out.println("Connected");
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databasename=restaurant;"
//            +"username=sa;password=123");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
