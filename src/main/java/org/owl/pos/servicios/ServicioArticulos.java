/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.owl.pos.servicios;

import java.util.List;
import org.owl.pos.modelos.Articulo;
import org.owl.pos.modelos.repositorios.RepositorioArticulos;

/**
 *
 * @author raphapy
 */
public class ServicioArticulos {
    
    private final RepositorioArticulos REPOSITORIO;
    
    public ServicioArticulos() {
        REPOSITORIO = RepositorioArticulos.getInstance();
    }
    
    public List<Articulo> listarTodos() {
        return REPOSITORIO.listarTodos();
    }

    public void eliminar(Articulo articulo) {
        REPOSITORIO.eliminar(articulo);
    }
    
    public void crear(Articulo articulo) {
        REPOSITORIO.crear(articulo);
    }
}
