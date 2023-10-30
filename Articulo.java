/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.supermercado;

/**
 *
 * @author MI PC
 */
// Clase abstracta para representar un artículo
public abstract class Articulo {
    protected String nombre;
    protected int precioPorKg;

    public Articulo(String nombre, int precioPorKg) {
        this.nombre = nombre;
        this.precioPorKg = precioPorKg;
    }

    public abstract double calcularCosto(double cantidad);

    @Override
    public String toString() {
        return nombre + " ($" + precioPorKg + "/kg)";
    }
}