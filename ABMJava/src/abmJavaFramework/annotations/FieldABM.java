package abmJavaFramework.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FieldABM {
  
  public enum representationType {
    TEXTFIELD, RADIOBUTTON, SELECTITEM, DATETIME, AUTOMATICO
  }

  String nombre() default "defaultNombreField";
  representationType representacion() default representationType.AUTOMATICO;
  
}
