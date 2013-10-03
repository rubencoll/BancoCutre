/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.daw2.bancocutre;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alumno
 */
//  Database credentials
public class EntidadBancariaDAO {

    static final String USER = "root";
    static final String PASS = "root";
    Connection connection = null;

    public EntidadBancariaDAO() throws ClassNotFoundException, SQLException {

        // JDBC driver name and database URL

        Class.forName("com.mysql.jdbc.Driver");

        //connection = null;

        connection = DriverManager.getConnection("jdbc:mysql://localhost/banco", USER, PASS);

        System.out.println("Conexion Realizada");

    }

    private EntidadBancaria read(int idEntidadBancaria) throws SQLException {

        EntidadBancaria entidadBancaria = new EntidadBancaria();

        String selectSQL = "SELECT * FROM entidadbancaria WHERE idEntidadBancaria = ?";

        PreparedStatement preparedStatementSelect = connection.prepareStatement(selectSQL);
        preparedStatementSelect.setInt(1, idEntidadBancaria);
        preparedStatementSelect.setString(2, "11");
        ResultSet resultSet = preparedStatementSelect.executeQuery();

        if (resultSet.next() == true) {

            idEntidadBancaria = resultSet.getInt("idEntidadBancaria");
            String codigoEntidadBancaria = resultSet.getString("codigoEntidadBancaria");
            String nombre = resultSet.getString("nombre");
            String cif = resultSet.getString("cif");

            entidadBancaria.setIdEntidadBancaria(idEntidadBancaria);
            entidadBancaria.setCodigoEntidadBancaria(codigoEntidadBancaria);
            entidadBancaria.setNombre(nombre);
            entidadBancaria.setCif(cif);

            if (resultSet.next() == true) {
                throw new RuntimeException("Hay mas de una entidad Bancaria con codigo: " + codigoEntidadBancaria);
                // System.out.println("Hay mas de 1");
            }

        } else {

            throw new RuntimeException("No existe la entidad: " + idEntidadBancaria);
        }

        return entidadBancaria;
    }

    private void insert(EntidadBancaria entidadBancaria) throws SQLException {

        String insertSQL = "INSERT INTO EntidadBancaria (idEntidadBancaria, codigoEntidadBancaria,nombre,cif) VALUES (?,?,?,?)";

        PreparedStatement preparedStatementInsert = connection.prepareStatement(insertSQL);

        preparedStatementInsert.setInt(1, entidadBancaria.getIdEntidadBancaria());
        preparedStatementInsert.setString(2, entidadBancaria.getCodigoEntidadBancaria());
        preparedStatementInsert.setString(3, entidadBancaria.getNombre());
        preparedStatementInsert.setString(4, entidadBancaria.getCif());

        preparedStatementInsert.executeUpdate();
    }

    private void update(EntidadBancaria entidadBancaria) throws SQLException {

        String updateSQL = "UPDATE entidadBancaria SET codigoEntidadBancaria = ?, nombre = ?, cif = ? WHERE idEntidadBancaria = ?";

        PreparedStatement preparedStatementUpdate = connection.prepareStatement(updateSQL);

        preparedStatementUpdate.setString(1, entidadBancaria.getCodigoEntidadBancaria());
        preparedStatementUpdate.setString(2, entidadBancaria.getNombre());
        preparedStatementUpdate.setString(3, entidadBancaria.getCif());
        preparedStatementUpdate.setInt(4, entidadBancaria.getIdEntidadBancaria());

        preparedStatementUpdate.executeUpdate();
    }

    private void delete(EntidadBancaria entidadBancaria) throws SQLException {

        String deleteSQL = "DELETE FROM entidadBancaria WHERE idEntidadBancaria = ?";

        PreparedStatement preparedStatementDelete = connection.prepareStatement(deleteSQL);

        preparedStatementDelete.setInt(1, entidadBancaria.getIdEntidadBancaria());


        if (preparedStatementDelete.executeUpdate() > 1) {     //Si hay más de una entidad con el mismo ID

            throw new RuntimeException("Hay mas de una entidad Bancaria con Identificador: " + entidadBancaria.getIdEntidadBancaria());
        
        } else if(preparedStatementDelete.executeUpdate() == 1){
            
            preparedStatementDelete.executeUpdate();
            
        } else {
        
            throw new RuntimeException("No existen Entidades Bancarias con Identificador"+entidadBancaria.getIdEntidadBancaria());
        }
    }

    private List<EntidadBancaria> findAll() throws SQLException {

        List<EntidadBancaria> entidadesBancarias = new ArrayList<>();

        String selectSQL = "SELECT * FROM entidadbancaria";

        PreparedStatement preparedStatementSelect = connection.prepareStatement(selectSQL);
        ResultSet resultSet = preparedStatementSelect.executeQuery();

        while (resultSet.next()) {

            EntidadBancaria entidadBancaria = new EntidadBancaria();

            int idEntidadBancaria = resultSet.getInt("idEntidadBancaria");
            String codigoEntidadBancaria = resultSet.getString("codigoEntidadBancaria");
            String nombre = resultSet.getString("nombre");
            String cif = resultSet.getString("cif");

            entidadBancaria.setIdEntidadBancaria(idEntidadBancaria);
            entidadBancaria.setCodigoEntidadBancaria(codigoEntidadBancaria);
            entidadBancaria.setNombre(nombre);
            entidadBancaria.setCif(cif);

            entidadesBancarias.add(entidadBancaria);

        }

        return entidadesBancarias;
    }

    private List<EntidadBancaria> findByCodigo(String codigo) throws SQLException {

        List<EntidadBancaria> entidadesBancarias = new ArrayList<>();

        String selectSQL = "SELECT * FROM entidadbancaria WHERE codigoEntidadBancaria = ?";


        PreparedStatement preparedStatementSelect = connection.prepareStatement(selectSQL);
        preparedStatementSelect.setString(1, codigo);

        ResultSet resultSet = preparedStatementSelect.executeQuery();

        while (resultSet.next()) {
            
            EntidadBancaria entidadBancaria = new EntidadBancaria();

            int idEntidadBancaria = resultSet.getInt("idEntidadBancaria");
            String codigoEntidadBancaria = resultSet.getString("codigoEntidadBancaria");
            String nombre = resultSet.getString("nombre");
            String cif = resultSet.getString("cif");

            entidadBancaria.setIdEntidadBancaria(idEntidadBancaria);
            entidadBancaria.setCodigoEntidadBancaria(codigoEntidadBancaria);
            entidadBancaria.setNombre(nombre);
            entidadBancaria.setCif(cif);

            entidadesBancarias.add(entidadBancaria);

        }

        return null;
    }
}
