package annotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method ;

import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;

public aspect asp
{

	JIPInitializer myJip	= new JIPInitializer();
	
	pointcut function2 () : execution (* *(..) ) ; // pointcut to catch execution context	
	pointcut function () : @annotation(Contract); // pointcut to catch annotations from the code

	// joining both the pointcuts it will catch annotations in the execution context
	before () : function() && function2() 
	{
		Signature sig = thisJoinPoint.getSignature () ;
		Method method = (( MethodSignature )sig).getMethod () ;
		String[] parameterNames = ((CodeSignature)sig).getParameterNames();
		Object[] arguements = thisJoinPoint.getArgs();

		
		Annotation [] annost = method.getDeclaredAnnotationsByType(Contract.class) ;
		for(int i=0; i < annost.length; i++)
		{
			String [] pre_cond = ((Contract)annost[i]).pre_cond() ;
			for(int j = 0; j< pre_cond.length; j++)
				for(int k=0; k<parameterNames.length;k++ )
				{
					//System.out.println("pre cond - " +i +"----"+ j + " --- "+ pre_cond[j]);
					if(pre_cond[j].contains(parameterNames[k]))
					{
						pre_cond[j] = pre_cond[j].replace((CharSequence)parameterNames[k], (CharSequence)arguements[k].toString());
						myJip.checkPreCond(pre_cond[j]);
						//System.out.println("arguements : " + parameterNames[k]+ "--" + arguements[k] + "re_cond"+ pre_cond[j]);
					}
						
				}
		}		
	}
	
	
	after () returning ( Object objret ): function ()
	{
		Signature sig = thisJoinPoint.getSignature () ;
		Method method = (( MethodSignature )sig).getMethod () ;
		
		Annotation [] annost = method.getDeclaredAnnotationsByType(Contract.class) ;
		
		if(annost.length > 0)
		{
			String [] post_cond = ((Contract)annost[0]).post_cond() ;
			//System.out.println(post_cond[0]);
			//System.out.println("----"+thisJoinPoint.getArgs()[0]);
			myJip.checkPostCondListOrdered(post_cond[0], objret);
		}
		
	}
	
}