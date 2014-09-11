package test;

import java.util.Collections;

import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;

import javax.swing.DefaultListModel;

import abmJavaFramework.ABMJavaFramework;
import test.clases.Alumno;
import test.clases.Fecha;

public class TestAlumno {

  
  public static void main(String[] args) {
    DefaultListModel<Alumno> objetos = new DefaultListModel<Alumno>();
    objetos.addElement(new Alumno("Eugenio", "Valeiras", 21, "Junin",new Fecha(9,10,1992),true));
    objetos.addElement(new Alumno("Roberto", "Ruiz", 22, "CAP",new Fecha(9,10,1992),true));
    objetos.addElement(new Alumno("Alejandro", "Arancibia", 22, "CAP",new Fecha(9,10,1992),false));
    objetos.addElement(new Alumno("Nicolas", "Fuentes", 22, "CAP",new Fecha(9,10,1992),true));
    objetos.addElement(new Alumno("Santiago", "ibanez", 22, "Junin",new Fecha(9,10,1992),false));
    objetos.addElement(new Alumno("Lucas", "Pugliese", 22, "Junin",new Fecha("09101992"),true));
    
    ABMJavaFramework.start(Alumno.class, objetos);
  }

}
