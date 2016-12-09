/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.owl.pos.vistas.helpers;

/**
 *
 * @author raphapy
 */
public final class StringHelper {
    public static String getNullIfEmpty(String value) {
        return value==null || value.isEmpty() ? null : value;
    }
}
