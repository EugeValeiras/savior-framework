package test;

import javax.swing.DefaultListModel;

import abmJavaFramework.ABMJavaFramework2;
import test.clases.Persona;

public class testOverrideFunctions {

  public static void main(String[] args) {

    DefaultListModel<Persona> objetos = new DefaultListModel<Persona>();
//    objetos.addElement(new Persona("Eugenio", "Valeiras", "Junin", 36649479, 21, 178, 43, false, true, true, true));
//    objetos.addElement(new Persona("Alberto", "Pascual", "9 de Julio", 14922029, 52, 177, 43, true, true, true, true));
    //CORRER PROGRAMA
    
    ABMJavaFramework2.start(ABMJavaFramework2.class, Persona.class, objetos);
    
  }

}
