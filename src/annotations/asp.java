package annotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method ;

import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;

public aspect asp
{
	JIPInitializer myJip	= new JIPInitializer();
	//pointcut function () : execution (* *(..) ) ;
	
	pointcut function () : @annotation(Contract) ;

	before () : function ()
	{
		Signature sig = thisJoinPoint.getSignature () ;
		Method method = (( MethodSignature )sig).getMethod () ;
		//System.out.println(method.getName());
		// Annotations for @Contract
		
		Annotation [] annost = method.getDeclaredAnnotationsByType(Contract.class) ;
		
		if(annost.length > 0)
		{
			String [] pre_cond = ((Contract)annost[0]).pre_cond() ;
			//System.out.println(pre_cond[0]);
			//System.out.println("----"+thisJoinPoint.getArgs()[0]);
			myJip.checkPreCond(pre_cond[0], thisJoinPoint.getArgs()[0]);
		}
		
		/*Annotation [] annost = method.getDeclaredAnnotations () ;
		
		Contract annos = ( Contract ) annost[0] ;
		
		//String [] invariant_cond = annos.invariant_cond () ;
		String [] pre_cond = annos.pre_cond () ;*/
		
	}
	
	
	after () returning ( Object objret ): function ()
	{
		Signature sig = thisJoinPoint.getSignature () ;
		Method method = (( MethodSignature )sig).getMethod () ;
		//System.out.println(method.getName());
		// Annotations for @Contract
		
		Annotation [] annost = method.getDeclaredAnnotationsByType(Contract.class) ;
		
		if(annost.length > 0)
		{
			String [] post_cond = ((Contract)annost[0]).post_cond() ;
			//System.out.println(post_cond[0]);
			//System.out.println("----"+thisJoinPoint.getArgs()[0]);
			myJip.checkPostCond(post_cond[0], (int)objret);
		}
		
	}
	
}