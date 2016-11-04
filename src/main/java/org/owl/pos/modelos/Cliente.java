/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.owl.pos.modelos;
import java.util.Date;

/**
 *
 * @author Familia
 */

public class Cliente extends Persona{
   public Date fechaAlta;
   public Date fechaBaja;
   public char estado;
   
   public Date getfechaAlta (){
       return fechaAlta;
   }
   public void setfechaAlta ( Date fechaAlta){
       this.fechaAlta = fechaAlta;
   }
    
   public Date getfechaBaja (){
       return fechaBaja;
   }
   public void setfechaBaja (Date fechaBaja){
       this.fechaBaja = fechaBaja;
   }
   
   public char getEstado (){
       return estado;
   }
   public void setEstado (char Estado){
       this.estado = estado;
   }
}


