package test.clases;

public class Fecha {

  private int dia;
  private int mes;
  private int anio;
 

  public Fecha(){
    
  }
  
  public Fecha(int dia, int mes, int anio) {
    this.dia = dia;
    this.mes = mes;
    this.anio = anio;
  }
  
  public Fecha(String ddmmaaaa){
    if(ddmmaaaa != ""){
      dia = Integer.valueOf(ddmmaaaa.substring(0, 2));
      mes = Integer.valueOf(ddmmaaaa.substring(2, 4));
      anio = Integer.valueOf(ddmmaaaa.substring(4, 8));  
    }
  }
  
  public int getDia() {
    return dia;
  }
  
  public void setDia(int dia) {
    this.dia = dia;
  }
  
  public int getMes() {
    return mes;
  }
  
  public void setMes(int mes) {
    this.mes = mes;
  
  }
  public int getAnio() {
    return anio;
  
  }
  public void setAnio(int anio) {
    this.anio = anio;
  }

  @Override
  public String toString() {
    String strDia = "";
    String strMes = "";
    
    if(dia < 10){
      strDia = "0"+String.valueOf(dia);
    } else {
      strDia = String.valueOf(dia);
    }
   
    if(mes < 10){
      strMes = "0"+String.valueOf(mes);
    } else {
      strMes = String.valueOf(mes);
    }
    
      return strDia+strMes+anio;
  }
  
  
  
}
