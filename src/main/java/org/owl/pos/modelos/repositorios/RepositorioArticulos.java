/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.owl.pos.modelos.repositorios;

import org.owl.pos.modelos.Articulo;

/**
 *
 * @author raphapy
 */
public class RepositorioArticulos extends Repositorio<Articulo> {
    private static RepositorioArticulos instancia;
    private Long id = 0L;
    
    private RepositorioArticulos() {
    }
    
    public static RepositorioArticulos getInstance() {
        if(instancia==null) {
            instancia = new RepositorioArticulos();
        }
        return instancia;
    }

    @Override
    public ListaPaginada<Articulo> buscar(String filtros, int indiceInicial, int cantidadElementos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void generarIdentificacion(Articulo entidad) {
        entidad.setId(++id);
    }    
}
