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

    public EntidadBancaria read(int idEntidadBancaria) throws SQLException {

        EntidadBancaria entidadBancaria;

        String selectSQL = "SELECT * FROM entidadbancaria WHERE idEntidadBancaria = ?";

        PreparedStatement preparedStatementSelect = connection.prepareStatement(selectSQL);
        preparedStatementSelect.setInt(1, idEntidadBancaria);
        preparedStatementSelect.setString(2, "11");
        ResultSet resultSet = preparedStatementSelect.executeQuery();

        if (resultSet.next() == true) {
            
            entidadBancaria = new EntidadBancaria();
            
            idEntidadBancaria = resultSet.getInt("idEntidadBancaria");
            String codigoEntidadBancaria = resultSet.getString("codigoEntidadBancaria");
            String nombre = resultSet.getString("nombre");
            String cif = resultSet.getString("cif");
            String tipoEntidadBancaria = resultSet.getString("tipoEntidadBancaria");

            entidadBancaria.setIdEntidadBancaria(idEntidadBancaria);
            entidadBancaria.setCodigoEntidadBancaria(codigoEntidadBancaria);
            entidadBancaria.setNombre(nombre);
            entidadBancaria.setCif(cif);
            entidadBancaria.setTipoEntidadBancaria(TipoEntidadBancaria.valueOf(resultSet.getString("tipoEntidadBancaria")));

            if (resultSet.next() == true) {
                throw new RuntimeException("Hay mas de una entidad Bancaria con codigo: " + codigoEntidadBancaria);
                // System.out.println("Hay mas de 1");
            }

        } else {    //Si no existe retornara un NULL

            entidadBancaria = null;

        }

        return entidadBancaria;
    }

    public void insert(EntidadBancaria entidadBancaria) throws SQLException {

        String insertSQL = "INSERT INTO EntidadBancaria (idEntidadBancaria, codigoEntidadBancaria,nombre,cif,tipoEntidadBancaria) VALUES (?,?,?,?,?)";

        PreparedStatement preparedStatementInsert = connection.prepareStatement(insertSQL);

        preparedStatementInsert.setInt(1, entidadBancaria.getIdEntidadBancaria());
        preparedStatementInsert.setString(2, entidadBancaria.getCodigoEntidadBancaria());
        preparedStatementInsert.setString(3, entidadBancaria.getNombre());
        preparedStatementInsert.setString(4, entidadBancaria.getCif());
        preparedStatementInsert.setString(5, entidadBancaria.getTipoEntidadBancaria().name());

        preparedStatementInsert.executeUpdate();
    }

    public void update(EntidadBancaria entidadBancaria) throws SQLException {

        String updateSQL = "UPDATE entidadBancaria SET codigoEntidadBancaria = ?, nombre = ?, cif = ?, tipoEntidadBancaria = ? WHERE idEntidadBancaria = ?";

        PreparedStatement preparedStatementUpdate = connection.prepareStatement(updateSQL);

        preparedStatementUpdate.setString(1, entidadBancaria.getCodigoEntidadBancaria());
        preparedStatementUpdate.setString(2, entidadBancaria.getNombre());
        preparedStatementUpdate.setString(3, entidadBancaria.getCif());
        preparedStatementUpdate.setInt(4, entidadBancaria.getIdEntidadBancaria());
        preparedStatementUpdate.setString(5, entidadBancaria.getTipoEntidadBancaria().name());

        preparedStatementUpdate.executeUpdate();
    }

    public void delete(EntidadBancaria entidadBancaria) throws SQLException {

        String deleteSQL = "DELETE FROM entidadBancaria WHERE idEntidadBancaria = ?";

        PreparedStatement preparedStatementDelete = connection.prepareStatement(deleteSQL);

        preparedStatementDelete.setInt(1, entidadBancaria.getIdEntidadBancaria());

        int numeroEntidades = preparedStatementDelete.executeUpdate();
        
        if (numeroEntidades > 1) {     //Si hay más de una entidad con el mismo ID

            throw new RuntimeException("Hay mas de una entidad Bancaria con Identificador: " + entidadBancaria.getIdEntidadBancaria());

        } 
    }

    public List<EntidadBancaria> findAll() throws SQLException {

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
            String tipoEntidadBancaria = resultSet.getString("tipoEntidadBancaria");

            entidadBancaria.setIdEntidadBancaria(idEntidadBancaria);
            entidadBancaria.setCodigoEntidadBancaria(codigoEntidadBancaria);
            entidadBancaria.setNombre(nombre);
            entidadBancaria.setCif(cif);
            entidadBancaria.setTipoEntidadBancaria(TipoEntidadBancaria.valueOf(entidadBancaria.getTipoEntidadBancaria().name()));

            entidadesBancarias.add(entidadBancaria);

        }

        return entidadesBancarias;
    }

    public List<EntidadBancaria> findByCodigo(String codigo) throws SQLException {

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
            String tipoEntidadBancaria = resultSet.getString("tipoEntidadBancaria");

            entidadBancaria.setIdEntidadBancaria(idEntidadBancaria);
            entidadBancaria.setCodigoEntidadBancaria(codigoEntidadBancaria);
            entidadBancaria.setNombre(nombre);
            entidadBancaria.setCif(cif);
            entidadBancaria.setTipoEntidadBancaria(TipoEntidadBancaria.valueOf(entidadBancaria.getTipoEntidadBancaria().name()));

            entidadesBancarias.add(entidadBancaria);

        }

        return null;
    }
}
