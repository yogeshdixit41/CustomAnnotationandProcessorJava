package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD )
@Retention(RetentionPolicy.RUNTIME)

public @interface Contract {
		
		String [] pre_cond ();
		String [] post_cond();
		String [] source_files() default "no file to load";
 }