/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.owl.pos.modelos.repositorios;

import java.util.List;
import org.owl.pos.modelos.Identificable;

/**
 * Esta clase representa una lista de objetos Identificables. Provee datos
 * extras para conocer el tamaño total del resultado con la finalidad de poder
 * obtener la lista completa de forma paginada.
 *
 * @author raphapy
 */
public class ListaPaginada {

    private int totalDatos;
    private List<Identificable> lista;

    public ListaPaginada(List<Identificable> lista, int totalDatos) {
        this.lista = lista;
        this.totalDatos = totalDatos;
    }

    /**
     * @return the totalDatos
     */
    public int getTotalDatos() {
        return totalDatos;
    }

    /**
     * @param totalDatos the totalDatos to set
     */
    public void setTotalDatos(int totalDatos) {
        this.totalDatos = totalDatos;
    }

    /**
     * @return the lista
     */
    public List<Identificable> getLista() {
        return lista;
    }

    /**
     * @param lista the lista to set
     */
    public void setLista(List<Identificable> lista) {
        this.lista = lista;
    }
}
