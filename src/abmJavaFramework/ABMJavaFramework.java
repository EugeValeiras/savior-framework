package abmJavaFramework;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import abmJavaFramework.annotations.Buscador;
import abmJavaFramework.annotations.FieldABM;
import abmJavaFramework.annotations.Obligatorio;
import abmJavaFramework.annotations.SoloLectura;
import abmJavaFramework.annotations.FieldABM.representationType;

import javax.swing.JScrollPane;

/**
 * The Class need to get an empty constructor
 * 
 * The annotations are Data and Setter, both are Methods annotations Data is on
 * getters, the attribute of this is nameAtt Setter is on setters, the attribute
 * of this is nameAtt
 * 
 * All fields of the class need to get an Constructor(String str) Because the
 * Framework use an String to modified the attribute
 * */
public class ABMJavaFramework {

  public static JFrame frame;
  public static JTextField textField;
  private static JButton btnAgregar;
  private static JButton btnBorrar;
  private static JButton btnModificar;
  private static JButton btnLimpiar;
  private static JButton btnBuscar;
  private static JButton btnSalir;
  private static JButton btnIniciarBuscar;
  private static Map<String, JTextField> listaTF;
  private static Map<String, JRadioButton> listaRBSi;
  private static Map<String, JRadioButton> listaRBNo;
  private static Map<String, ButtonGroup> listaRBgroup;
  private static JPanel jPane;
  public static Class<?> clase;
  private static int posLabel, posTextF;
  static DefaultListModel<Object> objetos;
  static DefaultListModel<Object> objetosBuscados;
  static SpringLayout springLayout = new SpringLayout();
  static ABMJavaFramework window;
  
  public static void start(final Class<?> abmClass, Class<?> c, DefaultListModel<?> objs) {
    clase = c;
    objetos = (DefaultListModel<Object>) objs;
    objetosBuscados = new DefaultListModel<Object>();

    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          window = (ABMJavaFramework) abmClass.newInstance();
          window.frame.setVisible(true);
          window.inicializarDefaultListModel(objetos);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Crear Aplicacion.
   */
  public ABMJavaFramework() {
    initialize();
  }

  /**
   * Inicializad los contenidos de la ventana.
   */
  public static void initialize() {
    // Inicializamos la ventana
    frame = new JFrame();
    frame.setBounds(0, 0, 700, 700);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().setLayout(springLayout);
    listaTF = new HashMap<String, JTextField>();
    listaRBSi = new HashMap<String, JRadioButton>();
    listaRBNo = new HashMap<String, JRadioButton>();
    listaRBgroup = new HashMap<String, ButtonGroup>();
    final JList<Object> jList = new JList<Object>(objetos);
    jList.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);

    // Crear y Ubicar Lista con su respectivo Scroll
    JScrollPane scrollPane = new JScrollPane();
    springLayout.putConstraint(SpringLayout.WEST, scrollPane, 10, SpringLayout.WEST, frame.getContentPane());
    springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, -10, SpringLayout.SOUTH, frame.getContentPane());
    springLayout.putConstraint(SpringLayout.EAST, scrollPane, -10, SpringLayout.EAST, frame.getContentPane());
    springLayout.putConstraint(SpringLayout.NORTH, scrollPane, -100, SpringLayout.SOUTH, frame.getContentPane());
    frame.getContentPane().add(scrollPane);
    scrollPane.setViewportView(jList);

    // Crear y Ubicar Panel de los atributos con su respectivo Scroll
    JScrollPane scrollPaneAtt = new JScrollPane();
    jPane = new JPanel(springLayout);
    springLayout.putConstraint(SpringLayout.WEST, scrollPaneAtt, 10, SpringLayout.WEST, frame.getContentPane());
    springLayout.putConstraint(SpringLayout.SOUTH, scrollPaneAtt, -50, SpringLayout.NORTH, scrollPane);
    springLayout.putConstraint(SpringLayout.EAST, scrollPaneAtt, -10, SpringLayout.EAST, frame.getContentPane());
    springLayout.putConstraint(SpringLayout.NORTH, scrollPaneAtt, 10, SpringLayout.NORTH, frame.getContentPane());
    frame.getContentPane().add(scrollPaneAtt);

    scrollPaneAtt.setViewportView(jPane);
    scrollPaneAtt.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    scrollPaneAtt.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    // Llenar los JtextField con el dato del objeto de la lista
    jList.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseReleased(MouseEvent e) {
        if (jList.getSelectedValue() != null) {
          _setAllEditable(jList);
          _listSelectedMethod(jList);
        }
      }
    });

    // Agregamos el boton Agregar y lo ubicamos
    btnAgregar = new JButton("Agregar");
    springLayout.putConstraint(SpringLayout.NORTH, btnAgregar, 10, SpringLayout.SOUTH, scrollPaneAtt);
    springLayout.putConstraint(SpringLayout.SOUTH, btnAgregar, -10, SpringLayout.NORTH, scrollPane);
    springLayout.putConstraint(SpringLayout.WEST, btnAgregar, 10, SpringLayout.WEST, frame.getContentPane());
    springLayout.putConstraint(SpringLayout.EAST, btnAgregar, 110, SpringLayout.WEST, frame.getContentPane());
    frame.getContentPane().add(btnAgregar);

    // Agregar Objeto a la Lista
    btnAgregar.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          window.agregarObjetoALista(jList);
          jList.clearSelection();
        } catch (Exception ex) {
          System.out.println("Error al agregar objeto");
        }
      }
    });

    // Agregamos Dinamicamente los textFields para cada Atributo de la Clase
    _createAllAttributesFields(clase);
    jPane.setPreferredSize(new Dimension(450, posLabel - 20));

    // Agregamos el boton Borrar y lo ubicamos
    btnBorrar = new JButton("Borrar");
    btnBorrar.setEnabled(false);
    springLayout.putConstraint(SpringLayout.NORTH, btnBorrar, 0, SpringLayout.NORTH, btnAgregar);
    springLayout.putConstraint(SpringLayout.SOUTH, btnBorrar, 0, SpringLayout.SOUTH, btnAgregar);
    springLayout.putConstraint(SpringLayout.WEST, btnBorrar, 10, SpringLayout.EAST, btnAgregar);
    springLayout.putConstraint(SpringLayout.EAST, btnBorrar, 220, SpringLayout.WEST, frame.getContentPane());

    // Borramos objeto seleccionado de la lista
    btnBorrar.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        window.removeObjectFromList(jList);
        jList.clearSelection();
      }
    });
    frame.getContentPane().add(btnBorrar);

    // Agregamos el boton Modificar y lo ubicamos
    btnModificar = new JButton("Modificar");
    btnModificar.setEnabled(false);
    springLayout.putConstraint(SpringLayout.NORTH, btnModificar, 0, SpringLayout.NORTH, btnBorrar);
    springLayout.putConstraint(SpringLayout.SOUTH, btnModificar, 0, SpringLayout.SOUTH, btnBorrar);
    springLayout.putConstraint(SpringLayout.WEST, btnModificar, 10, SpringLayout.EAST, btnBorrar);
    springLayout.putConstraint(SpringLayout.EAST, btnModificar, 330, SpringLayout.WEST, frame.getContentPane());
    frame.getContentPane().add(btnModificar);
    btnModificar.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        window.modificarObjetoDeLista(jList);
        btnAgregar.setEnabled(true);
        btnBorrar.setEnabled(false);
        btnModificar.setEnabled(false);
        btnBuscar.setVisible(false);
        btnBuscar.setEnabled(true);
        btnIniciarBuscar.setVisible(true);
        btnIniciarBuscar.setEnabled(true);

      }
    });

    // Agregamos el boton Clear y lo ubicamos
    btnLimpiar = new JButton("Limpiar");
    btnLimpiar.setEnabled(true);
    springLayout.putConstraint(SpringLayout.NORTH, btnLimpiar, 0, SpringLayout.NORTH, btnModificar);
    springLayout.putConstraint(SpringLayout.SOUTH, btnLimpiar, 0, SpringLayout.SOUTH, btnModificar);
    springLayout.putConstraint(SpringLayout.WEST, btnLimpiar, 10, SpringLayout.EAST, btnModificar);
    springLayout.putConstraint(SpringLayout.EAST, btnLimpiar, 440, SpringLayout.WEST, frame.getContentPane());
    frame.getContentPane().add(btnLimpiar);
    btnLimpiar.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        jList.setModel(objetos);
        _limpiarLista(jList);
        _setAllEditable(jList);
        objetosBuscados.clear();
        btnAgregar.setEnabled(true);
        btnBorrar.setEnabled(false);
        btnModificar.setEnabled(false);
        btnBuscar.setVisible(false);
        btnBuscar.setEnabled(true);
        btnIniciarBuscar.setVisible(true);
        btnIniciarBuscar.setEnabled(true);
      }
    });

    // Agregamos el boton Buscar y lo ubicamos
    btnIniciarBuscar = new JButton("Buscar");
    btnIniciarBuscar.setEnabled(true);
    springLayout.putConstraint(SpringLayout.NORTH, btnIniciarBuscar, 0, SpringLayout.NORTH, btnLimpiar);
    springLayout.putConstraint(SpringLayout.SOUTH, btnIniciarBuscar, 0, SpringLayout.SOUTH, btnLimpiar);
    springLayout.putConstraint(SpringLayout.WEST, btnIniciarBuscar, 10, SpringLayout.EAST, btnLimpiar);
    springLayout.putConstraint(SpringLayout.EAST, btnIniciarBuscar, 550, SpringLayout.WEST, frame.getContentPane());
    frame.getContentPane().add(btnIniciarBuscar);
    btnIniciarBuscar.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        btnAgregar.setEnabled(false);
        btnBorrar.setEnabled(false);
        btnModificar.setEnabled(false);
        btnBuscar.setVisible(true);
        btnBuscar.setEnabled(true);
        btnIniciarBuscar.setVisible(false);
        btnIniciarBuscar.setEnabled(false);
        window.iniciarBusqueda(jList);
      }
    });

    // Agregamos el boton Buscar y lo ubicamos
    btnBuscar = new JButton("Buscar!!");
    btnBuscar.setEnabled(true);
    springLayout.putConstraint(SpringLayout.NORTH, btnBuscar, 0, SpringLayout.NORTH, btnLimpiar);
    springLayout.putConstraint(SpringLayout.SOUTH, btnBuscar, 0, SpringLayout.SOUTH, btnLimpiar);
    springLayout.putConstraint(SpringLayout.WEST, btnBuscar, 10, SpringLayout.EAST, btnLimpiar);
    springLayout.putConstraint(SpringLayout.EAST, btnBuscar, 550, SpringLayout.WEST, frame.getContentPane());
    frame.getContentPane().add(btnBuscar);
    btnBuscar.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        window.buscarObjetoALista(jList);

      }
    });

    // Agregamos el boton Salir y lo ubicamos
    btnSalir = new JButton("Salir");
    btnSalir.setEnabled(true);
    springLayout.putConstraint(SpringLayout.NORTH, btnSalir, 0, SpringLayout.NORTH, btnBuscar);
    springLayout.putConstraint(SpringLayout.SOUTH, btnSalir, 0, SpringLayout.SOUTH, btnBuscar);
    springLayout.putConstraint(SpringLayout.WEST, btnSalir, 10, SpringLayout.EAST, btnBuscar);
    springLayout.putConstraint(SpringLayout.EAST, btnSalir, 660, SpringLayout.WEST, frame.getContentPane());
    frame.getContentPane().add(btnSalir);
    btnSalir.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        window.salir(jList);
        jList.clearSelection();
      }
    });

  }

  public void inicializarDefaultListModel(DefaultListModel<Object> objetos2) {
    //VACIO PORQUE LO TRAIGO INICIALIZADO POR DEFECTO SOLO PARA QUE LO SOBREESCRIBAN
  }

  public void salir(JList<Object> jList) {
    frame.dispose();
  }

  public void agregarObjetoALista(JList<Object> list) {
    Object newObj = null;
    try {
      newObj = clase.newInstance();
      Field[] fields = Class.forName(clase.getName()).getFields();
      Boolean camposNoCompletos = false;
      camposNoCompletos = _CompletarObjetoConDatos(newObj, fields, camposNoCompletos, true);
      if (!camposNoCompletos) {
        objetos.addElement(newObj);
        _limpiarLista(list);
        _setAllEditable(list);
      }
    } catch (Exception ex) {
      _lanzarPopUp(ex, "\nAlgo esta andando mal al crear el objeto.. " + "\nMirar: " + "\nLa falta de un String Contructor de alguno de los fields del objeto "
          + "\nMal tipo de dato de alguno de los campos...");
    }
  }

  public void removeObjectFromList(JList<Object> list) {
    try {
      objetos.removeElement(list.getSelectedValue());
      objetosBuscados.removeElement(list.getSelectedValue());
      _limpiarLista(list);
    } catch (Exception e) {
      _lanzarPopUp(e, "Selecciona uno antes de borrar!");
    }
  }

  public void modificarObjetoDeLista(JList<Object> list) {
    try {
      Object objetoAModificar = (objetos.get(list.getSelectedIndex()));
      Field[] fields = Class.forName(clase.getName()).getFields();
      Boolean camposNoCompletos = false;
      camposNoCompletos = _CompletarObjetoConDatos(objetoAModificar, fields, camposNoCompletos, true);
      if (!camposNoCompletos) {
        _limpiarLista(list);
        _setAllEditable(list);
      }
    } catch (Exception e) {
      _lanzarPopUp(e, "\nError al modificar el objeto.." + "\nMirar:" + "\nLa falta de un String Contructor de alguno de los fields del objeto " + "\nMal tipo de dato de alguno de los campos...");
    }

    frame.repaint();
  }

  public void iniciarBusqueda(JList<Object> list) {
    _limpiarLista(list);
    try {
      list.setModel(objetosBuscados);
      Field[] fields = Class.forName(clase.getName()).getFields();
      for (int i = 0; i < fields.length; i++) {
        FieldABM field = fields[i].getAnnotation(FieldABM.class);
        Buscador buscador = fields[i].getAnnotation(Buscador.class);
        if (buscador == null) {
          representationType representacion = field.representacion();
          if (representacion == representationType.AUTOMATICO) {
            if (fields[i].getType() == Boolean.class) {
              // Si es un bool
              listaRBSi.get(field.nombre()).setEnabled(false);
              listaRBNo.get(field.nombre()).setEnabled(false);
            } else /* Si no lo es */{
              listaTF.get(field.nombre()).setEditable(false);
            }
          } else if (representacion == representationType.TEXTFIELD) {
            listaTF.get(field.nombre()).setEditable(false);
          } else if (representacion == representationType.RADIOBUTTON) {
            listaRBSi.get(field.nombre()).setEnabled(false);
            listaRBNo.get(field.nombre()).setEnabled(false);
          } else if (representacion == representationType.SELECTITEM) {
            // TODO
          } else if (representacion == representationType.DATETIME) {
            // TODO
          }
        }

      }

    } catch (Exception e) {
      _lanzarPopUp("MIRARME");
    } finally {
      // _BuscarObjetoALista(list);
    }
  }

  public void buscarObjetoALista(JList<Object> list) {
    Object objABuscar = null;
    objetosBuscados.clear();
    try {
      objABuscar = clase.newInstance();
      Field[] fields = Class.forName(clase.getName()).getFields();
      Boolean camposNoCompletos = false;
      camposNoCompletos = _CompletarObjetoConDatos(objABuscar, fields, camposNoCompletos, false);
      _buscar(objABuscar, list);
      // TODO BUSCAR OBJETO! -
    } catch (Exception e) {
      _lanzarPopUp(e);
    }
  }

  private static void _listSelectedMethod(JList<Object> jList) {
    btnAgregar.setEnabled(false);
    btnBorrar.setEnabled(true);
    btnModificar.setEnabled(true);
    btnBuscar.setVisible(false);
    btnIniciarBuscar.setVisible(true);
    btnBuscar.setEnabled(false);
    btnIniciarBuscar.setEnabled(false);

    _AddDataToText(jList);
  }

  private static Boolean _CompletarObjetoConDatos(Object newObj, Field[] fields, Boolean camposNoCompletos, Boolean lanzarPopUps) throws NoSuchMethodException, InstantiationException,
      IllegalAccessException, InvocationTargetException {
    for (int i = 0; i < fields.length; i++) {
      FieldABM field = fields[i].getAnnotation(FieldABM.class);
      representationType representacion = field.representacion();
      Class<?> type = fields[i].getType();
      Constructor<?> ctor = null;
      Object object = null;
      if (field != null) {
        Obligatorio obligatorio = fields[i].getAnnotation(Obligatorio.class);
        if (representacion == representationType.AUTOMATICO) {
          if (type.equals(int.class)) {
            ctor = Integer.class.getConstructor(String.class);
          } else {
            ctor = type.getConstructor(String.class);
          }
          if (fields[i].getType() == Boolean.class) {
            // Si es un bool
            object = _getDataOfRadioButton(field, ctor);
            if (object == null && obligatorio != null && lanzarPopUps) {
              _lanzarPopUp("Completar Campos Obligatorios!\n(marcados con *)");
              camposNoCompletos = true;
              break;
            }
          } else /* Si no es un bool */{
            if (!listaTF.get(field.nombre()).getText().isEmpty()) {
              object = _getDataOfTextField(field, ctor);
            } else if (obligatorio != null && lanzarPopUps) {
              _lanzarPopUp("Completar Campos Obligatorios!\n(marcados con *)");
              camposNoCompletos = true;
              break;
            }
          }
        } else if (representacion == representationType.TEXTFIELD) {
          if (!listaTF.get(field.nombre()).getText().isEmpty()) {
            ctor = crearConstructorPorTipo(type);
            object = _getDataOfTextField(field, ctor);
          } else if (obligatorio != null && lanzarPopUps) {
            _lanzarPopUp("Completar Campos Obligatorios!\n(marcados con *)");
            camposNoCompletos = true;
            break;
          }
        } else if (representacion == representationType.RADIOBUTTON) {
          ctor = crearConstructorPorTipo(type);
          object = _getDataOfRadioButton(field, ctor);
          if (object == null && obligatorio != null && lanzarPopUps) {
            _lanzarPopUp("Completar Campos Obligatorios!\n(marcados con *)");
            camposNoCompletos = true;
            break;
          }
        } else if (representacion == representationType.SELECTITEM) {
          ctor = crearConstructorPorTipo(type);
          object = _getDataOfSelectItem(field, ctor);
        } else if (representacion == representationType.DATETIME) {
          ctor = crearConstructorPorTipo(type);
          object = _getDataOfDataTime(field, ctor);
        }
      }
      fields[i].set(newObj, object);
    }
    return camposNoCompletos;
  }

  private static Constructor<?> crearConstructorPorTipo(Class<?> type) throws NoSuchMethodException {
    if (type.equals(int.class)) {
      return Integer.class.getConstructor(String.class);
    } else {
      return type.getConstructor(String.class);
    }
  }

  private static Object _getDataOfTextField(FieldABM field, Constructor<?> ctor) throws InstantiationException, IllegalAccessException, InvocationTargetException {
    if (!listaTF.get(field.nombre()).getText().isEmpty()) {
      return ctor.newInstance(new Object[] { listaTF.get(field.nombre()).getText() });
    }
    return null;
  }

  private static Object _getDataOfDataTime(FieldABM field, Constructor<?> ctor) throws InstantiationException, IllegalAccessException, InvocationTargetException {
    // TODO
    return null;
  }

  private static Object _getDataOfSelectItem(FieldABM field, Constructor<?> ctor) throws InstantiationException, IllegalAccessException, InvocationTargetException {
    // TODO
    return null;
  }

  private static Object _getDataOfRadioButton(FieldABM field, Constructor<?> ctor) throws InstantiationException, IllegalAccessException, InvocationTargetException {
    if (listaRBSi.get(field.nombre()).isSelected()) {
      return ctor.newInstance(new Object[] { "true" });
    } else if (listaRBNo.get(field.nombre()).isSelected()) {
      return ctor.newInstance(new Object[] { "false" });
    }
    // nuca va a salir por null
    return null;
  }

  private static void _AddDataToText(JList<Object> list) {
    try {
      Field[] fields = Class.forName(clase.getName()).getFields();
      for (int i = 0; i < fields.length; i++) {
        FieldABM field = fields[i].getAnnotation(FieldABM.class);
        if (field != null) {
          Boolean soloLectura = (fields[i].getAnnotation(SoloLectura.class) != null);
          representationType representacion = field.representacion();
          if (representacion == representationType.AUTOMATICO) {
            // Si es un bool
            if (fields[i].getType() == Boolean.class) {
              _addDataToRadioButon(list, fields, i, field, soloLectura);
              // Si no lo es
            } else {
              _addDataToTextField(list, fields, i, field, soloLectura);
            }
          } else if (representacion == representationType.TEXTFIELD) {
            _addDataToTextField(list, fields, i, field, soloLectura);

          } else if (representacion == representationType.RADIOBUTTON) {
            _addDataToRadioButon(list, fields, i, field, soloLectura);

          } else if (representacion == representationType.SELECTITEM) {
            _addDataToSelectItem(list, fields, i, field, soloLectura);

          } else if (representacion == representationType.DATETIME) {
            _addDataToDateTime(list, fields, i, field, soloLectura);
          }
        }
      }
    } catch (Exception e) {
      _lanzarPopUp(e, "Algo esta andando mal al cargar los atributos del objeto...");
    }
  }

  private static void _addDataToTextField(JList<Object> list, Field[] fields, int i, FieldABM field, Boolean soloLectura) throws IllegalAccessException {
    if (fields[i].get(list.getSelectedValue()) != null) {

      listaTF.get(field.nombre()).setText(fields[i].get(list.getSelectedValue()).toString());
      if (soloLectura) {
        listaTF.get(field.nombre()).setEditable(false);
      }
    }
  }

  private static void _addDataToRadioButon(JList<Object> list, Field[] fields, int i, FieldABM field, Boolean soloLectura) throws IllegalAccessException {
    if (fields[i].get(list.getSelectedValue()) != null) {
      if ((boolean) fields[i].get(list.getSelectedValue())) {
        listaRBSi.get(field.nombre()).setSelected(true);
      } else {
        listaRBNo.get(field.nombre()).setSelected(true);
      }
      if (soloLectura) {
        listaRBSi.get(field.nombre()).setEnabled(false);
        listaRBNo.get(field.nombre()).setEnabled(false);
      }
    }
  }

  private static void _addDataToSelectItem(JList<Object> list, Field[] fields, int i, FieldABM field, Boolean soloLectura) throws IllegalAccessException {
    // TODO
  }

  private static void _addDataToDateTime(JList<Object> list, Field[] fields, int i, FieldABM field, Boolean soloLectura) throws IllegalAccessException {
    // TODO
  }

  private static void _createAllAttributesFields(Object theObject) {
    try {
      Field[] fields = Class.forName(clase.getName()).getFields();
      posLabel = 10;
      posTextF = 10;
      for (int i = 0; i < fields.length; i++) {
        FieldABM field = fields[i].getAnnotation(FieldABM.class);
        if (field != null) {
          String attr = null;
          if (field.nombre().equals("defaultNombreField")) {
            attr = fields[i].getName();
          } else {
            attr = getNombreAttr(field, fields[i]);
          }
          representationType representacion = field.representacion();
          // Ponemos la etiqueta del Field o Atributo
          Obligatorio obligatorio = fields[i].getAnnotation(Obligatorio.class);
          JLabel lavelAlumno;
          if (obligatorio != null) {
            lavelAlumno = _crearLabel("*" + attr);
          } else {
            lavelAlumno = _crearLabel(attr);
          }
          if (representacion == representationType.AUTOMATICO) {

            if (fields[i].getType() == Boolean.class) {
              // Si es un Boolean le colocamos RadioButons
              _crearRadioButons(attr, lavelAlumno);
            } else {
              // Si es otro tipo de atributo colocamos un textfield
              _crearTextField(attr, lavelAlumno);
            }

          } else if (representacion == representationType.TEXTFIELD) {
            _crearTextField(attr, lavelAlumno);

          } else if (representacion == representationType.RADIOBUTTON) {
            _crearRadioButons(attr, lavelAlumno);

          } else if (representacion == representationType.SELECTITEM) {
            _crearSelectItem(attr, lavelAlumno);

          } else if (representacion == representationType.DATETIME) {
            _crearDateTime(attr, lavelAlumno);
          }
          posTextF = posTextF + 50;
        }
      }
    } catch (Exception e) {
      _lanzarPopUp(e, "Algo esta andando mal al cargar los Fields de la clase..");
    }
  }

  private static void _crearTextField(String attr, JLabel lavelAlumno) {
    textField = new JTextField();
    springLayout.putConstraint(SpringLayout.NORTH, textField, posTextF, SpringLayout.NORTH, jPane);
    springLayout.putConstraint(SpringLayout.WEST, textField, 6, SpringLayout.EAST, lavelAlumno);
    jPane.add(textField);
    textField.setColumns(10);
    listaTF.put(attr, textField);
  }

  private static void _crearRadioButons(String attr, JLabel lavelAlumno) {
    ButtonGroup grupoRadioBut = new ButtonGroup();

    JRadioButton nuevoJRadioButSi = new JRadioButton("Si");
    springLayout.putConstraint(SpringLayout.NORTH, nuevoJRadioButSi, posTextF, SpringLayout.NORTH, jPane);
    springLayout.putConstraint(SpringLayout.WEST, nuevoJRadioButSi, 6, SpringLayout.EAST, lavelAlumno);
    jPane.add(nuevoJRadioButSi);
    listaRBSi.put(attr, nuevoJRadioButSi);
    nuevoJRadioButSi.setSelected(true);
    grupoRadioBut.add(nuevoJRadioButSi);

    JRadioButton nuevoJRadioButNo = new JRadioButton("No");
    springLayout.putConstraint(SpringLayout.NORTH, nuevoJRadioButNo, posTextF, SpringLayout.NORTH, jPane);
    springLayout.putConstraint(SpringLayout.WEST, nuevoJRadioButNo, 50, SpringLayout.EAST, lavelAlumno);
    jPane.add(nuevoJRadioButNo);
    listaRBNo.put(attr, nuevoJRadioButNo);
    nuevoJRadioButNo.setSelected(false);
    grupoRadioBut.add(nuevoJRadioButNo);
    grupoRadioBut.clearSelection();

    listaRBgroup.put(attr, grupoRadioBut);
  }

  private static void _crearSelectItem(String attr, JLabel lavelAlumno) {
    // TODO
  }

  private static void _crearDateTime(String attr, JLabel lavelAlumno) {
    // UtilDateModel model = new UtilDateModel();
    // JDatePanelImpl datePanel = new JDatePanelImpl(model);
    // JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
    // JDatePicker datePicker = new JDatePicker();
    // springLayout.putConstraint(SpringLayout.NORTH, datePicker, posTextF,
    // SpringLayout.NORTH, jPane);
    // springLayout.putConstraint(SpringLayout.WEST, datePicker, 6,
    // SpringLayout.EAST, lavelAlumno);
    // jPane.add(datePicker);
    // TODO
  }

  private static JLabel _crearLabel(String attr) {
    JLabel lavelAlumno = new JLabel(attr + ":");
    springLayout.putConstraint(SpringLayout.NORTH, lavelAlumno, posLabel, SpringLayout.NORTH, jPane);
    springLayout.putConstraint(SpringLayout.WEST, lavelAlumno, 84, SpringLayout.WEST, jPane);
    springLayout.putConstraint(SpringLayout.EAST, lavelAlumno, -274, SpringLayout.EAST, jPane);
    jPane.add(lavelAlumno);
    posLabel = posLabel + 50;
    return lavelAlumno;
  }

  private static void _setAllEditable(JList<Object> jList) {
    try {
      Field[] fields = Class.forName(clase.getName()).getFields();
      posLabel = 40;
      posTextF = 35;
      for (int i = 0; i < fields.length; i++) {
        FieldABM field = fields[i].getAnnotation(FieldABM.class);
        String attr = getNombreAttr(field, fields[i]);
        if (field != null) {
          if (fields[i].getType() == Boolean.class) {
            listaRBSi.get(attr).setEnabled(true);
            listaRBNo.get(attr).setEnabled(true);
          } else {
            listaTF.get(attr).setEditable(true);
          }
        }
      }
    } catch (Exception e) {
      _lanzarPopUp(e, "ALgo esta andando mal al limpiar la Lista..");
    }
  }

  private static void _limpiarLista(JList<Object> jList) {
    jList.clearSelection();
    try {
      Field[] fields = Class.forName(clase.getName()).getFields();
      posLabel = 40;
      posTextF = 35;
      for (int i = 0; i < fields.length; i++) {
        FieldABM field = fields[i].getAnnotation(FieldABM.class);
        String attr = getNombreAttr(field, fields[i]);
        if (field != null) {
          if (fields[i].getType() == Boolean.class) {
            listaRBgroup.get(attr).clearSelection();
          } else {
            listaTF.get(attr).setText("");
          }
        }
      }
    } catch (Exception e) {
      _lanzarPopUp(e, "ALgo esta andando mal al limpiar la Lista..");
    }
  }

  private static void _lanzarPopUp(Exception ex, String str) {
    JOptionPane.showMessageDialog(frame.getContentPane(), ex + " " + str);
  }

  private static void _lanzarPopUp(Exception ex) {
    JOptionPane.showMessageDialog(frame.getContentPane(), ex);
  }

  private static void _lanzarPopUp(String str) {
    JOptionPane.showMessageDialog(frame.getContentPane(), str);
  }

  private static String getNombreAttr(FieldABM fieldAnotation, Field field) {
    if (fieldAnotation.nombre() != null) {
      return fieldAnotation.nombre();
    } else {
      return field.getName();
    }
  }

  private static void _buscar(Object objABuscar, JList<Object> list) {
    Object objAux;
    Boolean encontrado = true;
    try {
      Field[] fields = Class.forName(clase.getName()).getFields();
      for (int ii = 0; ii < objetos.size(); ii++) {
        objAux = objetos.getElementAt(ii);
        for (int i = 0; i < fields.length; i++) {
          Buscador buscador = fields[i].getAnnotation(Buscador.class);
          if (buscador != null) {
            if (fields[i].get(objABuscar) != null) {
              if (!fields[i].get(objAux).equals(fields[i].get(objABuscar))) {
                encontrado = false;
                break;
              }
            }
          }
        }
        if (encontrado) {
          objetosBuscados.addElement(objAux);
        } else {
          encontrado = true;
        }
      }
    } catch (Exception e) {
      _lanzarPopUp(e);
    }
  }
}