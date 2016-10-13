package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.METHOD )

public @interface Contract {
		
		String [] pre_cond ();
        //String created();
 }