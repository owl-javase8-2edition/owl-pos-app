package org.owl.pos.modelos;
import java.util.Date;
public class Proveedor extends Persona{
	public Date fechaAlta;
	public Date fechaBaja;
	public char estado;

	 public Date getFechaAlta() {
        return fechaAlta;
    }
     public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }
     public Date getFechaBaja() {
        return fechaBaja;
    }
     public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }
      public char getEstado() {
        return estado;
    }
     public void setEstado(char estado) {
        this.estado = estado;
    }
}