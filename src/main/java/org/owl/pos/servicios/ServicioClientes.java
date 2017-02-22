/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.owl.pos.servicios;

import java.util.List;
import org.owl.pos.modelos.Cliente;
import org.owl.pos.modelos.repositorios.RepositorioCientes;

/**
 *
 * @author raphapy
 */
public class ServicioClientes {
        private final RepositorioCientes REPOSITORIO;
    
    public ServicioClientes() {
        REPOSITORIO = RepositorioCientes.getInstance();
    }
    
    public List<Cliente> listarTodos() {
        return REPOSITORIO.listarTodos();
    }

    public void eliminar(Cliente cliente) {
        REPOSITORIO.eliminar(cliente);
    }
    
    public void crear(Cliente cliente) {
        REPOSITORIO.crear(cliente);
    }

    public void crearOActualizar(Cliente cliente) {
        if (cliente!=null) {
            if(cliente.getId()!=null) {
                REPOSITORIO.modificar(cliente);
            } else {
                REPOSITORIO.crear(cliente);
            }
        }
    }
}
