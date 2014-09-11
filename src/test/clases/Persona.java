package test.clases;

import abmJavaFramework.annotations.Buscador;
import abmJavaFramework.annotations.FieldABM;
import abmJavaFramework.annotations.Obligatorio;
import abmJavaFramework.annotations.SoloLectura;
import abmJavaFramework.annotations.FieldABM.representationType;

//Pedir por el metodo con el nombre get+nombre del field
//la clase tiene qe ser un bean
public class Persona {

  @SoloLectura
  @Obligatorio
  @Buscador
  @FieldABM(nombre = "Nombre",representacion = representationType.TEXTFIELD)
  public String nombre;
  
  @SoloLectura
  @Obligatorio
  @Buscador
  @FieldABM(nombre = "Apellido")
  public String apellido;
  
  @Buscador
  @FieldABM(nombre = "Lugar de nacimiento",representacion = representationType.TEXTFIELD)
  public String lugarDeNacimiento;
  
  @SoloLectura
  @Obligatorio
  @Buscador
  @FieldABM(nombre = "Documento",representacion = representationType.TEXTFIELD)
  public Integer documneto;
  
  @FieldABM(nombre = "edad")
  public Integer edad;
  
  @FieldABM(nombre = "Altura",representacion = representationType.TEXTFIELD)
  public Integer altura;
  
//  @Buscador
  @FieldABM(nombre = "Calzado",representacion = representationType.TEXTFIELD)
  public Integer numeroCalzado;

  @Obligatorio
  @Buscador
  @FieldABM(nombre = "Casado",representacion = representationType.RADIOBUTTON)
  public Boolean casado;
  
  @FieldABM(nombre = "Casa Propia",representacion = representationType.RADIOBUTTON)
  public Boolean casaPropia;
  
  @Obligatorio
  @FieldABM(nombre = "Secundaria Completa")
  public Boolean secundariaCompleta;
  
  @Obligatorio
  @FieldABM(nombre = "Primaria Completa",representacion = representationType.RADIOBUTTON)
  public Boolean primariaCompletaCompleta;

  
  
  public Persona(String nombre, String apellido, String lugarDeNacimiento, Integer documneto, Integer edad, Integer altura, Integer numeroCalzado, Boolean casado, Boolean casaPropia, Boolean secundariaCompleta,
      Boolean primariaCompletaCompleta) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.lugarDeNacimiento = lugarDeNacimiento;
    this.documneto = documneto;
    this.edad = edad;
    this.altura = altura;
    this.numeroCalzado = numeroCalzado;
    this.casado = casado;
    this.casaPropia = casaPropia;
    this.secundariaCompleta = secundariaCompleta;
    this.primariaCompletaCompleta = primariaCompletaCompleta;
  }

  //OBLIGATORIO
  public Persona(){
    
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
  
  public String getLugarDeNacimiento() {
    return lugarDeNacimiento;
  }
  
  public void setLugarDeNacimiento(String lugarDeNacimiento) {
    this.lugarDeNacimiento = lugarDeNacimiento;
  }
  
  public Integer getDocumneto() {
    return documneto;
  }
  
  public void setDocumneto(Integer documneto) {
    this.documneto = documneto;
  }
  
  public Integer getEdad() {
    return edad;
  }
  
  public void setEdad(Integer edad) {
    this.edad = edad;
  }
  
  public Integer getAltura() {
    return altura;
  }
  
  public void setAltura(Integer altura) {
    this.altura = altura;
  }
  
  public Integer getNumeroCalzado() {
    return numeroCalzado;
  }
  
  public void setNumeroCalzado(Integer numeroCalzado) {
    this.numeroCalzado = numeroCalzado;
  }
  
  public Boolean getEstadoCivil() {
    return casado;
  }
  
  public void setEstadoCivil(Boolean estadoCivil) {
    this.casado = estadoCivil;
  }
  
  public Boolean getCasaPropia() {
    return casaPropia;
  }
  
  public void setCasaPropia(Boolean casaPropia) {
    this.casaPropia = casaPropia;
  }

  public Boolean getPrimariaCompletaCompleta() {
    return primariaCompletaCompleta;
  }
  
  public void setPrimariaCompletaCompleta(Boolean primariaCompletaCompleta) {
    this.primariaCompletaCompleta = primariaCompletaCompleta;
  }
  
  public Boolean getSecundariaCompleta() {
    return secundariaCompleta;
  }
  
  public void setSecundariaCompleta(Boolean secundariaCompleta) {
    this.secundariaCompleta = secundariaCompleta;
  }
  
  @Override
  public String toString() {
    return nombre+" "+apellido+" de "+edad+" años, documento "+documneto+" esta casado: "+casado.toString();
  }
}

