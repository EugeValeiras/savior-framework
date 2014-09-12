package abmJavaFramework.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Validar {

	public enum ABMJavaFrameworkValidator {
		SOLO_NUMEROS, SOLO_LETRAS, NO_METHOD;
	}

	String nombreMetodo() default "NOMETHOD";
	ABMJavaFrameworkValidator abmValidator() default ABMJavaFrameworkValidator.NO_METHOD;
	
}
