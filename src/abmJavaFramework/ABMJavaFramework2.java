package abmJavaFramework;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class ABMJavaFramework2 extends ABMJavaFramework {

  @Override
  public void salir(JList<Object> jList) {
    JOptionPane.showMessageDialog(frame.getContentPane(), "Puedo cambiar la opcion Salir");
  }

  @Override
  public void agregarObjetoALista(JList<Object> list) {
    JOptionPane.showMessageDialog(frame.getContentPane(), "puedo cambiar la opcion de Agregar");
  }
  
  @Override
  public void removeObjectFromList(JList<Object> list) {
    JOptionPane.showMessageDialog(frame.getContentPane(), "puedo cambiar la opcion de Remover");
  }
  
  @Override
  public void iniciarBusqueda(JList<Object> list) {
    JOptionPane.showMessageDialog(frame.getContentPane(), "puedo cambiar la opcion de Iniciar Busqueda");
  }

  @Override
  public void buscarObjetoALista(JList<Object> list) {
    JOptionPane.showMessageDialog(frame.getContentPane(), "puedo cambiar la opcion de Buscar!!");
  }
  
  @Override
  public void modificarObjetoDeLista(JList<Object> list) {
    JOptionPane.showMessageDialog(frame.getContentPane(), "puedo cambiar la opcion de Modificar");
  }
  
  @Override
  public void inicializarDefaultListModel(DefaultListModel<Object> obj) {
    JOptionPane.showMessageDialog(frame.getContentPane(), "@Override public void inicializarDefaultListModel(DefaultListModel<Object> objetos2)\nPara inicializar la lista desde donde quieras");

  }
}
