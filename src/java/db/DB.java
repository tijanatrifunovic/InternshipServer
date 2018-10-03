/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tijana
 */
public class DB {
    public static String dbDriver = "com.mysql.jdbc.Driver";
    public static String dbName = "jdbc:mysql://localhost:3306/internship";
    public static String dbUsername = "root";
    public static String dbPassword = "root";
    
    static{
        try {
            Class.forName(dbDriver);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
