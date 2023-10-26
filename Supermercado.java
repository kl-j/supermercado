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
    private double totalCompra = 0.0; // Variable para acumular el costo total de la compra

    // Constructor sin argumentos (mensaje de bienvenida predeterminado)
    public Supermercado() {
        this("Bienvenido a la tiendita Mau y David"); // Llama al constructor con mensaje personalizado
    }

    // Constructor con un mensaje de bienvenida personalizado
    public Supermercado(String mensajeBienvenida) {
        JOptionPane.showMessageDialog(null, mensajeBienvenida); // Muestra un mensaje de bienvenida
    }

    // Método principal que controla la interacción con el usuario
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

            // Llama al método para procesar la elección del usuario
            procesarOpcion(op);
        } while (!op.equals("6")); // Continúa mostrando el menú hasta que el usuario elija "6" para salir

        // Muestra el costo total al finalizar la compra
        JOptionPane.showMessageDialog(null, "El costo total de la compra es: $" + totalCompra);
        
        // Llama al método para calcular el impuesto y muestra el resultado
        double impuesto = calcularImpuesto(totalCompra);
        JOptionPane.showMessageDialog(null, "Impuesto aplicado: $" + impuesto);
        
        // Mensaje de despedida
        JOptionPane.showMessageDialog(null, "Gracias por tu compra. ¡Hasta luego!");
    }

    // Método para procesar la opción del usuario
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
                comprarArticulo("Papa", 23);
                break;
            case 2:
                comprarArticulo("Arroz", 35);
                break;
            case 3:
                comprarArticulo("Jitomate", 30);
                break;
            case 4:
                comprarArticulo("Huevo", 40);
                break;
            case 5:
                comprarArticulo("Frijol", 20);
                break;
            case 6:
                // El usuario seleccionó terminar la compra, no se hace nada aquí
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opción no válida.");
        }
    }

    // Método para realizar la compra del artículo
    public void comprarArticulo(String articulo, int precioPorKg) {
        String cantidadStr = JOptionPane.showInputDialog("¿Cuánto deseas comprar de " + articulo + " (en kg)?");

        try {
            double cantidad = Double.parseDouble(cantidadStr);
            double costoTotal = cantidad * precioPorKg;
            totalCompra += costoTotal; // Acumula el costo total en la variable de clase
            JOptionPane.showMessageDialog(null, "Has seleccionado comprar " + cantidad + " kg de " + articulo + ". El costo total es $" + costoTotal);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Cantidad no válida. Ingrese un número válido.");
        }
    }
    
    // Método para calcular el impuesto y devolverlo como resultado
    public double calcularImpuesto(double totalCompra) {
        // Supongamos un impuesto del 10% sobre la compra
        double impuesto = totalCompra * 0.10;
        return impuesto;
    }

    public static void main(String[] args) {
        // Creamos una instancia de Supermercado con un mensaje de bienvenida personalizado
        Supermercado supermercado = new Supermercado("ShopPal Ver1.0");
        supermercado.menu(); // Inicia la interacción con el usuario
    }
}
