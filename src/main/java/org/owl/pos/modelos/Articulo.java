/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.owl.pos.modelos;

import java.util.Objects;

/**
 *
 * @author user
 */
public class Articulo implements Identificable {

    private String codigo;
    private String descripcion;
    private Double precio;
    private Double porcentajeImpuesto;
    private Long id;

    private int vexistencia;
    
    public String getCodigo(){
        return this.codigo;
    }
    public String getDescripcion(){
        return this.descripcion;
    }
    public Double getPrecio(){
        return this.precio;
    }
    public Double getProcentajeImpuesto(){
        return this.porcentajeImpuesto;
    }
    public void setcodigo(String codigo){
        this.codigo = codigo;
    }
    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }
    public void setPrecio(Double precio){
        this.precio = precio;
    }
    public void setPorcentajeImpuesto(Double porcentajeImpuesto){
        this.porcentajeImpuesto = porcentajeImpuesto;
    }

    public int getExistencia() {
        return vexistencia;
    }

    public boolean estaAgotado() {
        if (vexistencia == 0) {
            return true;
        } else {
            return false;
        }
    }
    
    public void sumarExistencia(int n){
        vexistencia = vexistencia + n;
    }
    
    public void restarExistencia(int n){
        vexistencia = vexistencia - n;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Articulo other = (Articulo) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return id + "\t" + codigo + "\t" +  descripcion + "\t" + precio + "\t" + porcentajeImpuesto; 
    }
}
