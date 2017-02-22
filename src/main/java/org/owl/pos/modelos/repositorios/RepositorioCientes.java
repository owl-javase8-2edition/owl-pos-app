/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.owl.pos.modelos.repositorios;

import org.owl.pos.modelos.Cliente;

/**
 *
 * @author raphapy
 */
public class RepositorioCientes extends Repositorio<Cliente> {

    private static RepositorioCientes instancia;
    private Long id = 0L;
    
    private RepositorioCientes() {
    }
    
    public static RepositorioCientes getInstance() {
        if(instancia==null) {
            instancia = new RepositorioCientes();
        }
        return instancia;
    }
    
    @Override
    public ListaPaginada<Cliente> buscar(String filtros, int indiceInicial, int cantidadElementos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void generarIdentificacion(Cliente entidad) {
        entidad.setId(++id);
    }
}
