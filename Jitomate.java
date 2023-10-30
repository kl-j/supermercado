/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.supermercado;

/**
 *
 * @author MI PC
 */
// Clase concreta que representa un artículo
public class Jitomate extends Articulo {
    public Jitomate() {
        super("Jitomate", 30);
    }

    @Override
    public double calcularCosto(double cantidad) {
        return cantidad * precioPorKg;
    }
}