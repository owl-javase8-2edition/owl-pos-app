/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.owl.pos.modelos;

/**
 *
 * @author Familia
 */
public class ItemCompra implements Identificable {
    
    private Articulo articulo;
    private int cantidad;
    private Double costoItem;
    private Long id;
    
    public Double calcularSubTotalItem(){
    
        Double subTotal;
        subTotal = cantidad*costoItem;
        return subTotal;
        
    }
    
    public Articulo getArticulo (){
        
        return articulo;
        
    }
    
    public void setArticulo (Articulo articulo){
        
        this.articulo = articulo;
        
    }
    
    public int getCantidad (){
        
        return cantidad;
        
    }
    
    public void setCantidad (int cantidad){
        
        this.cantidad = cantidad;
        
    }
    
    public Double getCostoItem (){
        
        return costoItem;
        
    }
    
    public void setCostoItem (Double costoItem){
        
        this.costoItem = costoItem;
        
    }
    
    @Override
    public Long getId(){
        
        return id;
        
    }
    
    @Override
    public void setId(Long id){
        
        this.id = id;
        
    }
}
    
