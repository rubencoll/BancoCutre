/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.daw2.bancocutre;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author alumno
 */
//  Database credentials
public class EntidadBancariaDAO {

    static final String USER = "root";
    static final String PASS = "root";

    public EntidadBancariaDAO() throws ClassNotFoundException, SQLException {



        // JDBC driver name and database URL

        Class.forName("com.mysql.jdbc.Driver");

        Connection connection = null;

        connection = DriverManager.getConnection("jdbc:mysql://localhost/banco", USER, PASS);

        System.out.println("Conexion Realizada");

    }
    
    
    
    
}
