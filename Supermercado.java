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
        this("Bienvenido a la tiendita Mau, David y Nat");
    }

    public Supermercado(String mensajeBienvenida) {
        JOptionPane.showMessageDialog(null, mensajeBienvenida);
    }
    

    /**
    * Collects sales data including the cash register number and cashier's name.
    * Validates and enforces input requirements
    * 
    */
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


    /**
    * Displays a menu to the user for selecting items to purchase and processing the purchase.
    * The user is prompted to enter a choice, and the selected option is processed using the "procesarOpcion" method.
    * After the user finishes shopping, it calculates the total cost, applies taxes
    * It calls mostrarCArrito() and imprimirTicket()
    */
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
        
        imprimirTicket();

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

    /**
    * Processes the purchase of a selected item by the user. It prompts the user for the quantity
    * of the item they want to buy, calculates the total cost, updates the shopping cart, and displays
    * a confirmation message.
    *
    * @param articulo The selected item to purchase.
    */
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

    /**
    * Stores the purchased item's information in the shopping cart. It adds the item, quantity, and
    * total cost to the cart using an object array
    *
    * @param articulo    The purchased item.
    * @param cantidad    The quantity of the item purchased (in kilograms).
    * @param costoTotal  The total cost of the purchased item.
    */
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

    /**
    * Calculates and returns the total tax amount (10% of the total purchase amount).
    *
    * @param totalCompra The total cost of the items in the shopping cart.
    * @return The calculated tax amount.
    */
    public double calcularImpuesto(double totalCompra) {
        double impuesto = totalCompra * 0.10;
        return impuesto;
    }

    /**
    * Displays a list of items in the shopping cart. It shows the names of the items
    * currently in the cart.
    */
    public void mostrarCarrito() {
        JOptionPane.showMessageDialog(null, "Artículos en el carrito:");
        for (int i = 0; i < carrito.length; i++) {
            if (carrito[i][0] != null) {
                Articulo articulo = (Articulo) carrito[i][0];
                JOptionPane.showMessageDialog(null, articulo.nombre);
            }
        }
    }

    /**
    * Entry point of the Supermercado application. Creates a Supermercado instance, calls datosVenta
    * and initiates the shopping menu.
    * 
    */
    public static void main(String[] args) {
        Supermercado supermercado = new Supermercado("ShopPal Ver1.1");
        supermercado.datosVenta();
        supermercado.menu();
    }

   /**
    * Generates and displays a purchase receipt for the items in the shopping cart. It includes
    * the cashier's name, cash register number, purchased items, their quantities, and the total cost
    * of the purchase, along with taxes.
    */
    private void imprimirTicket() {
        StringBuilder ticketContent = new StringBuilder();

    // Nombre Supermercado
    ticketContent.append("Supermercado Tiendita de Mau, David y Nat\n\n");

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
    ticketContent.append("------------------------------------------------------------------------------------------------\n\n");


    // Articulos y costo
    ticketContent.append("Artículos en el carrito:\n");
        for (int i = 0; i < carrito.length; i++) {
            if (carrito[i][0] != null) {
                Articulo articulo = (Articulo) carrito[i][0];
                double cantidad = (double) carrito[i][1];
                double costoTotal = (double) carrito[i][2];
                String nombreArticulo = articulo.nombre.toUpperCase(); // uppercase
            ticketContent.append(nombreArticulo).append(" ($").append(articulo.precioPorKg).append("/kg)\n");
            ticketContent.append("Cantidad: ").append(cantidad).append(  "--- Total: $ ").append(costoTotal).append("kg)\n");
            //CANTIDAD
            //TOTAL POR PRODUCTO
            
        }
    }
        
    ticketContent.append("------------------------------------------------------------------------------------------------\n\n");
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
