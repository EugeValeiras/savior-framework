package test.clases;

import javax.swing.JButton;

import abmJavaFramework.annotations.Buscador;
import abmJavaFramework.annotations.FieldABM;
import abmJavaFramework.annotations.SoloLectura;
import abmJavaFramework.annotations.FieldABM.representationType;
import abmJavaFramework.annotations.Obligatorio;

public class Alumno {
  
  @Buscador
  @Obligatorio
  @SoloLectura
  @FieldABM(nombre="Nombre", representacion = representationType.AUTOMATICO)
  public String nombre;
  
  @Buscador
  @Obligatorio
  @SoloLectura
  @FieldABM(nombre="Apellido", representacion = representationType.TEXTFIELD)
  public String apellido;
  
  @Buscador
  @FieldABM(nombre="Edad", representacion = representationType.TEXTFIELD)  
  public Integer edad;
  
  
  @Buscador
  @FieldABM(nombre="Localidad", representacion = representationType.TEXTFIELD)
  public String localidad;

  
  @FieldABM(nombre="Fecha", representacion = representationType.AUTOMATICO)
  public Fecha fecha;
  
  
  @FieldABM(nombre="Casado")
//  @FieldABM(nombre="Casado",representacion = representationType.RADIOBUTTON)
  public Boolean casado;
  

  public Alumno(){
    
  }

  public Alumno(String nombre, String apellido, Integer edad, String localidad, Fecha fecha, Boolean casado) {
    super();
    this.nombre = nombre;
    this.apellido = apellido;
    this.edad = edad;
    this.localidad = localidad;
    this.fecha = fecha;
    this.casado = casado;
  }
  
  
  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }
  
  public String getApellido() {
    return apellido;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }
  
  public Integer getEdad() {
    return edad;
  }

  public void setEdad(Integer edad) {
    this.edad = edad;
  }
  
  public String getLocalidad() {
    return localidad;
  }
  
  public void setLocalidad(String localidad) {
    this.localidad = localidad;
  }

  public Fecha getFecha() {
    return fecha;
  }

  public void setFecha(Fecha fecha) {
    this.fecha = fecha;
  }
  
  public Boolean getCasado() {
    return casado;
  }
  
  public void setCasado(Boolean casado) {
    this.casado = casado;
  }
  
  @Override
  public String toString() {
    return nombre +" "+ apellido;
  }

}
