/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.daw2.bancocutre.presentacion;

import com.fpmislata.daw2.bancocutre.negocio.CuentaBancaria;
import com.fpmislata.daw2.bancocutre.negocio.EntidadBancaria;
import com.fpmislata.daw2.bancocutre.negocio.MovimientoBancario;
import com.fpmislata.daw2.bancocutre.negocio.SucursalBancaria;
import com.fpmislata.daw2.bancocutre.negocio.TipoEntidadBancaria;
import com.fpmislata.daw2.bancocutre.negocio.TipoMovimientoBancario;
import com.fpmislata.daw2.bancocutre.datos.EntidadBancariaDAO;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author alumno
 */
public class BancoCutre {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // TODO code application logic here

        

        EntidadBancaria entidadBancaria1 = new EntidadBancaria(69, "1", "BankStafa", "1234", TipoEntidadBancaria.BANCO);


        SucursalBancaria sucursalBancaria1 = new SucursalBancaria(1, entidadBancaria1, "2", "Sucursal Mislata");
        SucursalBancaria sucursalBancaria2 = new SucursalBancaria(2, entidadBancaria1, "6", "Sucursal Valencia");

        List<SucursalBancaria> sucursalesBancarias = entidadBancaria1.getSucursalesBancarias();

        sucursalesBancarias.add(sucursalBancaria1);
        sucursalesBancarias.add(sucursalBancaria2);



        CuentaBancaria cuentaBancaria1 = new CuentaBancaria(123, sucursalBancaria1, "654321", "99", new BigDecimal("6000"), "12345678A");
        CuentaBancaria cuentaBancaria2 = new CuentaBancaria(169, sucursalBancaria1, "9498693465", "25", new BigDecimal("678000"), "87654321B");
        CuentaBancaria cuentaBancaria3 = new CuentaBancaria(65, sucursalBancaria1, "897553", "78", new BigDecimal("678000"), "87654321B");

        Map<Integer, CuentaBancaria> mapCuentaBancaria = new HashMap<>();  //Declaramos el Map de entidad bancaria.
        //mapCuentaBancaria.put(cuentaBancaria1.getIdCuentaBancaria(), cuentaBancaria1);

        List<CuentaBancaria> cuentasBancarias = sucursalBancaria1.getCuentasBancarias();

        cuentasBancarias.add(cuentaBancaria1);
        cuentasBancarias.add(cuentaBancaria2);
        cuentasBancarias.add(cuentaBancaria3);

        MovimientoBancario movimientoBancario1 = new MovimientoBancario(9876, TipoMovimientoBancario.DEBE, new BigDecimal("200"), new GregorianCalendar(2013, 4, 22).getTime(), new BigDecimal("5000"), "Pago Contribución", cuentaBancaria1);
        MovimientoBancario movimientoBancario2 = new MovimientoBancario(153, TipoMovimientoBancario.HABER, new BigDecimal("500"), new GregorianCalendar(2013, 7, 5).getTime(), new BigDecimal("2000"), "Ingreso Publicidad", cuentaBancaria1);

        List<MovimientoBancario> movimientosBancarios = cuentaBancaria1.getMovimientosBancario();

        movimientosBancarios.add(movimientoBancario1);
        movimientosBancarios.add(movimientoBancario2);

        imprimirCuentaBancaria(cuentaBancaria1);
        imprimirMovimientosCuentaBancaria(movimientosBancarios);
        
        //Prueba para buscar una entidad Bancaria mediante ID
        
        int idEntidadBancariaBuscarda = 3;
        
        EntidadBancariaDAO entidadBancariaDAO = new EntidadBancariaDAO();
        
        EntidadBancaria entidadBancariaBuscada = new EntidadBancaria();
        entidadBancariaBuscada=entidadBancariaDAO.read(idEntidadBancariaBuscarda);
        
        System.out.println(entidadBancariaBuscada.getIdEntidadBancaria());
        System.out.println(entidadBancariaBuscada.getCodigoEntidadBancaria());
        System.out.println(entidadBancariaBuscada.getNombre());
        System.out.println(entidadBancariaBuscada.getTipoEntidadBancaria());
        
        
        //Prueba de Insercion de Entidad Bancaria
        
        
        EntidadBancaria entidadBancariaInsertar = new EntidadBancaria();
        entidadBancariaInsertar.setIdEntidadBancaria(4);
        entidadBancariaInsertar.setCodigoEntidadBancaria("33");
        entidadBancariaInsertar.setNombre("Banesto");
        entidadBancariaInsertar.setCif("8888888D");
        entidadBancariaInsertar.setTipoEntidadBancaria(TipoEntidadBancaria.ESTABLECIMIENTOS_FINANCIEROS_DE_CREDITO);
        
        entidadBancariaDAO.insert(entidadBancariaInsertar);
        
        
        //Prueba de Update de Entidad Bancaria
        
        EntidadBancaria entidadBancariaUpdate = entidadBancariaInsertar;
        
        entidadBancariaUpdate.setNombre("Caja Madrid");
        
        entidadBancariaDAO.update(entidadBancariaUpdate);
        
        
        //Prueba de Delete Entidad Bancaria
        
        EntidadBancaria entidadBancariaDelete = entidadBancariaInsertar;
        
        entidadBancariaDAO.delete(entidadBancariaInsertar);
        
        
        //Prueba de ver todas la entidades Bancarias
        
        
        List<EntidadBancaria> entidadesBancarias = new ArrayList<>();
        
        entidadesBancarias = entidadBancariaDAO.findAll();
        
        for(EntidadBancaria entidadBancaria : entidadesBancarias){
        
            System.out.println(entidadBancaria.getIdEntidadBancaria()); 
            System.out.println(entidadBancaria.getCodigoEntidadBancaria());
            System.out.println(entidadBancaria.getNombre());
            System.out.println(entidadBancaria.getTipoEntidadBancaria());
            System.out.println(entidadBancaria.getCif());
        }
        
        
        
        //Prueba buscar Entidad Bancaria pasandole el Codigo
          
        List<EntidadBancaria> entidadesBancariasCodigo = new ArrayList<>();
        
        entidadesBancariasCodigo = entidadBancariaDAO.findByCodigo("11");
        
        for(EntidadBancaria entidadBancaria : entidadesBancariasCodigo){
        
            System.out.println(entidadBancaria.getIdEntidadBancaria()); 
            System.out.println(entidadBancaria.getCodigoEntidadBancaria());
            System.out.println(entidadBancaria.getNombre());
            System.out.println(entidadBancaria.getTipoEntidadBancaria());
            System.out.println(entidadBancaria.getCif());
        }
        
    }

    //Imprime el numero completo de la cuenta bancaria
    private static void imprimirCuentaBancaria(CuentaBancaria cuentaBancaria) {


        System.out.println("################# CUENTA BANCARIA #################");
        System.out.println("-------------------------------------------------");
        System.out.println("Entidad     Oficina     Control     Numero Cuenta");

        System.out.println("-------------------------------------------------");

        System.out.println(getNumeroCompletoCuentaBancaria(cuentaBancaria));

        System.out.println("-------------------------------------------------");
        System.out.println("CIF: " + cuentaBancaria.getCif());
        System.out.println("-------------------------------------------------");
        System.out.println("##################################################");

        System.out.println("");
    }

    //Recoge el numero completo de la cuenta, incluye Entidad-Oficina-NumControl-NumCuenta
    public static String getNumeroCompletoCuentaBancaria(CuentaBancaria cuentaBancaria) {

        String numeroCompletoCuenta = cuentaBancaria.getSucursalBancaria().getEntidadBancaria().getCodigoEntidadBancaria() + "           " + cuentaBancaria.getSucursalBancaria().getCodigoSucursal() + "            " + cuentaBancaria.getNumeroCuentaBancaria();

        return numeroCompletoCuenta;
    }

    //Imprimir los movimientos bancarios
    public static void imprimirMovimientosCuentaBancaria(List<MovimientoBancario> movimientosBancarios) {

        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/YYYY");
        
        System.out.println("################# CUENTA BANCARIA #################");
        System.out.println("-------------------------------------------------");
        System.out.println("Fecha     Concept     Importe     Tipo     Saldo Total");
        System.out.println("-------------------------------------------------");

        for (MovimientoBancario movimientoBancario : movimientosBancarios) {
               System.out.println(sd.format(movimientoBancario.getFecha())+"   "+movimientoBancario.getConcepto()+"    "+movimientoBancario.getImporte()+" "+movimientoBancario.getTipoMovimientoBancario()+"  "+movimientoBancario.getSaldoTotal());
        }

        // System.out.println(getMovimientoCuentaBancaria(cuentaBancaria, mapCuentaBancaria));

        System.out.println("-------------------------------------------------");
        System.out.println("##################################################");

        System.out.println("");
    }

    private static String getMovimientoCuentaBancaria(CuentaBancaria cuentaBancaria, Map<Integer, CuentaBancaria> mapCuentaBancaria) {


        CuentaBancaria cuentaBancariaBusqueda = buscarCuentaBancariaMapID(mapCuentaBancaria, cuentaBancaria.getIdCuentaBancaria());


        String movimientoCuentaBancaria = null;
        return movimientoCuentaBancaria;
    }

    private static CuentaBancaria buscarCuentaBancariaMapID(Map<Integer, CuentaBancaria> mapCuentaBancaria, Integer idCuentaBancaria) {

        CuentaBancaria cuentaBancaria = mapCuentaBancaria.get(idCuentaBancaria);
        // List<SucursalBancaria> listaSucursalesBancarias = cuentaBancaria.getSucursalBancaria();

        return cuentaBancaria;
    }
}
