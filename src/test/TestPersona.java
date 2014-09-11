package test;

import javax.swing.DefaultListModel;

import abmJavaFramework.ABMJavaFramework;
import test.clases.Persona;

public class TestPersona {

  public static void main(String[] args) {

    DefaultListModel<Persona> objetos = new DefaultListModel<Persona>();
    objetos.addElement(new Persona("Eugenio", "Valeiras", "Junin", 36649479, 21, 178, 43, false, true, true, true));
    objetos.addElement(new Persona("Edgardo", "Valeiras", "Junin", 36364387, 22, 176, 40, false, true, true, true));
    objetos.addElement(new Persona("Santiago", "Ibañez", "Junin", 36364339, 22, 162, 36, false, true, true, true));
    objetos.addElement(new Persona("Roberto", "Ruiz", "Capital Federal", 36364387, 22, 166, 39, true, false, false, false));
    //CORRER PROGRAMA
    
    ABMJavaFramework.start(Persona.class, objetos);
    
    
  }

}
