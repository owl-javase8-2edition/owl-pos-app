/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.owl.pos.modelos;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author fcabral
 */
public class Venta implements Identificable {
    
    private Date fechaVenta;
    private String timbradoFactura;
    private String numeroFactura;
    private Cliente clientes;
    private List<ItemVenta> items;
    private Long id;
    
    
    public Venta (){
        
    }
    
    
    public Double calcularTotalVenta() {
        Double total;
        total=0.0;
        for(ItemVenta i:items ){
            total = total+i.getCostoItem();
            
            
        }
        
        return total; 
    }
    
    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public String getTimbradoFactura() {
        return timbradoFactura;
    }

    public void setTimbradoFactura(String timbradoFactura) {
        this.timbradoFactura = timbradoFactura;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public Cliente getClientes() {
        return clientes;
    }

    public void setClientes(Cliente clientes) {
        this.clientes = clientes;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id=id;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Venta other = (Venta) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
  
    
    
    
    
    
    
    
}
