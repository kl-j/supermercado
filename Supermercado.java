/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.supermercado;

import javax.swing.JOptionPane;

/**
 *
 * @author MI PC
 */
public class Supermercado {

   private String op;
    private double totalCompra = 0.0;
    private Object[][] carrito = new Object[5][3];
    String cajero;
    String numeroCaja;
    private double cantidad;
    double costoTotal;

    public Supermercado() {
        this("Bienvenido a la tiendita Mau y David");
    }

    public Supermercado(String mensajeBienvenida) {
        JOptionPane.showMessageDialog(null, mensajeBienvenida);
    }
    
    //Datos del cajero en turno
    public void datosVenta() {
    boolean validNumeroCaja = false;
    boolean validCajero = false;

    while (!validNumeroCaja) {
        numeroCaja = JOptionPane.showInputDialog("Por favor, ingrese el número de la caja (un carácter):");

        if (numeroCaja != null && numeroCaja.length() == 1 && Character.isLetterOrDigit(numeroCaja.charAt(0))) {
            validNumeroCaja = true;
        } else {
            JOptionPane.showMessageDialog(null, "Número de caja no válido. Debe ser un carácter alfanumérico.");
        }
    }

    while (!validCajero) {
        cajero = JOptionPane.showInputDialog("Por favor, ingrese su nombre completo:");

        if (cajero != null && cajero.matches("^[a-zA-Z ]+$")) {
            validCajero = true;
        } else {
            JOptionPane.showMessageDialog(null, "Nombre completo no válido. Debe contener solo letras y espacios.");
        }
    }
}


    public void menu() {
        do {
            op = JOptionPane.showInputDialog("""
                                             ¿Qué deseas comprar?
                                             1. Papa ($23/kg)
                                             2. Arroz ($35/kg)
                                             3. Jitomate ($30/kg)
                                             4. Huevo ($40/kg)
                                             5. Frijol ($20/kg)
                                             6. Terminar compra""");

            procesarOpcion(op);
        } while (!op.equals("6"));

        double impuesto = calcularImpuesto(totalCompra);
        JOptionPane.showMessageDialog(null, "El costo total de la compra es: $" + totalCompra + "\nImpuesto aplicado: $" + impuesto);

        mostrarCarrito();
        
        ImprimirTicket();

        JOptionPane.showMessageDialog(null, "Gracias por tu compra. ¡Hasta luego!");
    }

    public void procesarOpcion(String opcion) {
        int eleccion;
        try {
            eleccion = Integer.parseInt(opcion);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Opción no válida. Ingrese un número válido.");
            return;
        }

        switch (eleccion) {
            case 1:
                comprarArticulo(new Papa());
                break;
            case 2:
                comprarArticulo(new Arroz());
                break;
            case 3:
                comprarArticulo(new Jitomate());
                break;
            case 4:
                comprarArticulo(new Huevo());
                break;
            case 5:
                comprarArticulo(new Frijol());
                break;
            case 6:
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opción no válida.");
        }
    }

    public void comprarArticulo(Articulo articulo) {
        String cantidadStr = JOptionPane.showInputDialog("¿Cuánto deseas comprar de " + articulo + " (en kg)?");
        try {
            cantidad = Double.parseDouble(cantidadStr);
            costoTotal = articulo.calcularCosto(cantidad);
            totalCompra += costoTotal;
            guardarEnCarrito(articulo, cantidad, costoTotal);
            JOptionPane.showMessageDialog(null, "Has seleccionado comprar " + cantidad + " kg de " + articulo.nombre + ". El costo total es $" + costoTotal);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Cantidad no válida. Ingrese un número válido.");
        }
    }

    public void guardarEnCarrito(Articulo articulo, double cantidad, double costoTotal) {
        for (int i = 0; i < carrito.length; i++) {
            if (carrito[i][0] == null) {
                carrito[i][0] = articulo;
                carrito[i][1] = cantidad;
                carrito[i][2] = costoTotal;
                
                break;
            }
        }
    }

    public double calcularImpuesto(double totalCompra) {
        double impuesto = totalCompra * 0.10;
        return impuesto;
    }

    public void mostrarCarrito() {
        JOptionPane.showMessageDialog(null, "Artículos en el carrito:");
        for (int i = 0; i < carrito.length; i++) {
            if (carrito[i][0] != null) {
                Articulo articulo = (Articulo) carrito[i][0];
                JOptionPane.showMessageDialog(null, articulo.nombre);
            }
        }
    }

    public static void main(String[] args) {
        Supermercado supermercado = new Supermercado("ShopPal Ver1.1");
        supermercado.datosVenta();
        supermercado.menu();
    }

    private void ImprimirTicket() {
        StringBuilder ticketContent = new StringBuilder();

    // Nombre Supermercado
    ticketContent.append("Supermercado Tiendita de Mau y David\n\n");

    // Cajero
     String[] nameParts = cajero.split(" ");    
    if (nameParts.length >= 2) {
            String firstName = nameParts[0];
            String lastName = nameParts[1];
            ticketContent.append("Cajero: ").append(lastName).append(", ").append(firstName.charAt(0)).append(".\n");
    // Caja
    ticketContent.append("Número de Caja: ").append(numeroCaja).append("\n");

    // RFC
    ticketContent.append("RFC: RFCXXXXX\n");

    // Dirección
    ticketContent.append("Dirección: Calle 123, Colonia, Mex\n\n");
    ticketContent.append("------------------------------------------------------\n\n");


    // Articulos y costo
    ticketContent.append("Artículos en el carrito:\n");
        for (int i = 0; i < carrito.length; i++) {
            if (carrito[i][0] != null) {
                Articulo articulo = (Articulo) carrito[i][0];
                double cantidad = (double) carrito[i][1];
                double costoTotal = (double) carrito[i][2];
            ticketContent.append(articulo.nombre).append(" ($").append(articulo.precioPorKg).append("/kg)\n");
            ticketContent.append("Cantidad: ").append(cantidad).append(  "--- Total: $ ").append(costoTotal).append("kg)\n");
            //CANTIDAD
            //TOTAL POR PRODUCTO
            
        }
    }
        
    ticketContent.append("------------------------------------------------------\n\n");
    ticketContent.append("\nCosto total de la compra: $").append(totalCompra).append("\n");

    // Impuesto
    double impuesto = calcularImpuesto(totalCompra);
    ticketContent.append("Impuesto aplicado: $").append(impuesto).append("\n");
 
    String formattedReceipt = String.format(
        "================== Ticket de Compra ==================\n%s\n======================================================",
        ticketContent.toString()
    );

    JOptionPane.showMessageDialog(null, formattedReceipt, "Ticket de Compra", JOptionPane.PLAIN_MESSAGE);
        
    }
}
}
