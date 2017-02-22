/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.owl.pos.modelos.repositorios;

import java.util.List;

/**
 * Esta clase representa una lista de objetos Identificables. Provee datos
 * extras para conocer el tamaño total del resultado con la finalidad de poder
 * obtener la lista completa de forma paginada.
 *
 * @author raphapy
 */
public class ListaPaginada<T> {

    private int totalDatos;
    private List<T> lista;

    public ListaPaginada(List<T> lista, int totalDatos) {
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
    public List<T> getLista() {
        return lista;
    }

    /**
     * @param lista the lista to set
     */
    public void setLista(List<T> lista) {
        this.lista = lista;
    }
}
