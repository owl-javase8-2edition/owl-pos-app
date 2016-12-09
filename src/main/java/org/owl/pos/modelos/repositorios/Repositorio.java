/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.owl.pos.modelos.repositorios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.owl.pos.modelos.Identificable;

/**
 * Clase que representa un repositorio de datos basado en {@code java.util.Map}
 * Permite realizar operaciones CRUD sobre entidades almacenadas en el
 * repositorio.
 *
 * Ver información sobre opraciones CRUD:
 * {@link https://en.wikipedia.org/wiki/Create,_read,_update_and_delete}
 * <br>
 * Ver documentación sobre el Map:
 * {@link https://docs.oracle.com/javase/8/docs/api/java/util/Map.html}
 *
 * @author raphapy
 * @param <E> el tipo identificable
 */
public abstract class Repositorio<E extends Identificable> {

    /*
        En esta estrucura de datos almacenamos todos los Ítems
     */
    private final Map<Long, E> repositorio = new HashMap<>();

    /**
     * Almacena un objeto identificable en el repositorio. No permite almacenar
     * objetos nulos ni repetidos.
     *
     * @param entidad identificable
     * @throws NullPointerException cuando la entidad a almacenar es nula.
     * @throws IllegalArgumentException cuando se intenta almacenar una entidad
     * que ya existe en el repositorio.
     */
    public void crear(E entidad) {
        if(entidad==null) {
            throw new NullPointerException("No puede almacenarce una entidad nula.");
        }
        
        if(entidad.getId()==null) {
            generarIdentificacion(entidad);
        }
        
        if(repositorio.containsKey(entidad.getId())) {
            throw new IllegalArgumentException("Violación de restricción de unicidad.");
        }
        
        repositorio.put(entidad.getId(), entidad);
    }

    /**
     * Modifica una entidad identificable si existe en el repositorio.
     *
     * @param entidad
     * @throws NullPointerException si la entidad es nula.
     * @throws IllegalArgumentException si el identificador de la identidad es
     * nulo.
     */
    public void modificar(E entidad) {
        if(entidad==null) {
            throw new NullPointerException();
        }
        
        if(entidad.getId()==null) {
            throw new IllegalArgumentException("El identificador de entidad no debe ser nulo.");
        }
        
        if(repositorio.containsKey(entidad.getId())) {
            repositorio.replace(entidad.getId(), entidad);
        }
    }

    /**
     * Elimina una entidad identificable del repositorio.
     *
     * @param entidad
     * @throws NullPointerException si la entidad es nula.
     * @throws IllegalArgumentException si el identificador de la identidad es
     * nulo.
     */
    public void eliminar(E entidad) {
        if(entidad==null) {
            throw new NullPointerException();
        }
        
        if(entidad.getId()==null) {
            throw new IllegalArgumentException("El identificador de entidad no debe ser nulo.");
        }
        
        if(repositorio.containsKey(entidad.getId())) {
            repositorio.remove(entidad.getId());
        }
    }

    /**
     * Busca una entidad en el repositrio a través de su identificador.
     *
     * @param id
     * @return entidad encontrada o {@code null} si no existe.
     * @throws NullPointerException si el identificador de la identidad es nulo.
     */
    public E obtener(Long id) {
        if(id==null) {
            throw new NullPointerException("El identificador de entidad no debe ser nulo.");
        }
        
        return repositorio.get(id);
    }

    /**
     * Realiza una búsqueda basada en un patrón de texto que coincida con el
     * resultado de la ejecución del método toString del Identificable.
     *
     * @param filtros {@code String} patrón de búsqueda.
     * @return
     * @throws NullPointerException si el patrón de búsqueda es nulo
     */
    public List<E> buscar(String filtros) {
        if(filtros==null) {
            throw new NullPointerException();
        }
        
        return  repositorio.values().parallelStream()
                          //ordenado por ID de forma ascendente
                          .filter(e-> {
                                        String valor = e.toString()
                                                        .toLowerCase()
                                                        .replaceAll(" ", "")
                                                        .replace(",", "");
                                        return valor
                                                  .contains(filtros.replaceAll(" ", "")
                                                                   .toLowerCase()
                                                            );
                                      })
                          .sorted((o1,o2)->o1.getId().compareTo(o2.getId()))
                          .collect(Collectors.toList());
    }


    /**
     * Realiza una búsqueda paginada basada en un patrón que coincida con el
     * resultado de la ejecución del método toString del Identificable y
     * delimitada por la cantidad de elementos solicitados, empezando por el
     * índice inicial especificado.
     *
     * @param filtros {@code String} patrón de búsqueda.
     * @param indiceInicial {@code int} que indica el índice del primer elemento
     * a retornar.
     * @param cantidadElementos {@code int} que indica la cantidad de elementos
     * a retornar a partir del primer elemento solicitado por el parámetro
     * {@code indiceInicial}, incluído el mismo.
     * @return
     */
    public abstract ListaPaginada<E> buscar(String filtros, int indiceInicial, int cantidadElementos);

    /**
     * Debe generar una identificación única para los objetos del repositorio.
     * Este método deberá ser utilizado por el método {@code Repositorio.crear(Identificable entidad)
     * para generar el id único para la entidad a crear.
     *
     * Este método deberá ser implementado por el repositorio concreto asociado a un modelo específico.
     *
     * @param entidad
     */
    protected abstract void generarIdentificacion(E entidad);
    
    /**
     * Obtiene todos los elementos del repositorio
     * 
     * @return 
     */
    public List<E> listarTodos() {
        return new ArrayList<>(repositorio.values());
    }
}
