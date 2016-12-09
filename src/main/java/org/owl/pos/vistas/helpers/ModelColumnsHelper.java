/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.owl.pos.vistas.helpers;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author raphapy
 */
public final class ModelColumnsHelper {

    public static void setModelColumn(TableColumn tb, String a) {
        tb.setCellValueFactory(new PropertyValueFactory(a));
    }
}
