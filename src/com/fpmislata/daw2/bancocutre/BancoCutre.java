/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.daw2.bancocutre;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author alumno
 */
public class BancoCutre {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        SimpleDateFormat sd = new SimpleDateFormat("DD/MM/YYYY");

        EntidadBancaria entidadBancaria1 = new EntidadBancaria(69, "1", "BankStafa", "1234", TipoEntidadBancaria.BANCO);


        SucursalBancaria sucursalBancaria1 = new SucursalBancaria(1,entidadBancaria1, "2", "Sucursal Mislata");
        SucursalBancaria sucursalBancaria2 = new SucursalBancaria(2,entidadBancaria1, "6", "Sucursal Valencia");

        List<SucursalBancaria> sucursalesBancarias = entidadBancaria1.getSucursalesBancarias();

        sucursalesBancarias.add(sucursalBancaria1);
        sucursalesBancarias.add(sucursalBancaria2);



        CuentaBancaria cuentaBancaria1 = new CuentaBancaria(123,sucursalBancaria1, "654321", "99", new BigDecimal("6000"), "12345678A");
        CuentaBancaria cuentaBancaria2 = new CuentaBancaria(169,sucursalBancaria1, "9498693465", "25", new BigDecimal("678000"), "87654321B");
        CuentaBancaria cuentaBancaria3 = new CuentaBancaria(65,sucursalBancaria1, "897553", "78", new BigDecimal("678000"), "87654321B");


        List<CuentaBancaria> cuentasBancarias = sucursalBancaria1.getCuentasBancarias();

        cuentasBancarias.add(cuentaBancaria1);
        cuentasBancarias.add(cuentaBancaria2);
        cuentasBancarias.add(cuentaBancaria3);
        


        MovimientoBancario movimientoBancario1 = new MovimientoBancario(9876, TipoMovimientoBancario.DEBE, new BigDecimal("200"), new GregorianCalendar(2013, 4, 22).getTime(), new BigDecimal("5000"), "Pago Contribución", cuentaBancaria1);
        MovimientoBancario movimientoBancario2 = new MovimientoBancario(153, TipoMovimientoBancario.HABER, new BigDecimal("500"), new GregorianCalendar(2013, 7, 5).getTime(), new BigDecimal("2000"), "Ingreso Publicidad",cuentaBancaria1);
        

        imprimirCuentaBancaria(cuentaBancaria1);
    }

    private static void imprimirCuentaBancaria(CuentaBancaria cuentaBancaria1) {
        
        
        System.out.println("################# CUENTA BANCARIA #################");
        System.out.println("-------------------------------------------------");
        System.out.println("Entidad     Oficina     Control     Numero Cuenta");

        System.out.println("-------------------------------------------------");
        
        System.out.println(getNumeroCompletoCuentaBancaria(cuentaBancaria1));
        
        System.out.println("-------------------------------------------------");
        System.out.println("CIF: "+cuentaBancaria1.getCif());
        System.out.println("-------------------------------------------------");
        System.out.println("##################################################");

        System.out.println("");
    }
    
    
    public static String getNumeroCompletoCuentaBancaria(CuentaBancaria cuentaBancaria) {

        String numeroCompletoCuenta =cuentaBancaria.getSucursalBancaria().getEntidadBancaria().getCodigoEntidadBancaria()+"           "+cuentaBancaria.getSucursalBancaria().getCodigoSucursal()+"            "+ cuentaBancaria.getNumeroCuentaBancaria();

        return numeroCompletoCuenta;
    }
    
    
}
