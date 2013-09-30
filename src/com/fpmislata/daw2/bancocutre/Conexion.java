/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.daw2.bancocutre;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author alumno
 */
public class Conexion {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/banco";
    
    //  Database credentials
    static final String USER = "root";
    static final String PASS = "root";
   
    Connection connexion = null;
    
    
 

}
