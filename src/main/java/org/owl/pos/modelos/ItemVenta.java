/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.owl.pos.modelos;

/**
 *
 * @author fmorel
 */
class ItemVenta {
    
    private Articulo articulo;
    private int cantidad;
    private Double costoItem;
   
    
    
    
    
    
    public Double calcularSubtotalItem() {
        return costoItem/cantidad;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Double getCostoItem() {
        return costoItem;
    }

    public void setCostoItem(Double costoItem) {
        this.costoItem = costoItem;
    } 

}
