package annotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method ;
import java.util.ArrayList;
import java.util.HashMap;

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
		Object thisInstanceCallerClass = thisJoinPoint.getThis();
		ArrayList<String> instanceVarNamesList = new ArrayList<>();
		HashMap<String, Object> instanceVarNameToValue = new HashMap<>();

		
		Annotation [] annost = method.getDeclaredAnnotationsByType(Contract.class) ;
		for(int i=0; i < annost.length; i++)
		{
			String [] pre_cond = ((Contract)annost[i]).pre_cond() ;
			String [] source_files = ((Contract)annost[i]).source_files();
			
			System.out.println("Source File : " + source_files[0]);
			for(int j=0; j< source_files.length;j++)
			{
				myJip.loadFile(source_files[j]);
			}
			
			for(int j = 0; j< pre_cond.length; j++)
			{
				instanceVarNamesList = getInstanceVariableNames(pre_cond[j]);
				instanceVarNameToValue = getInstanceValues(instanceVarNamesList, thisInstanceCallerClass);
				
				for(String instanceVarName: instanceVarNameToValue.keySet())
				{
					if(pre_cond[j].contains("@"+instanceVarName))
					{
						pre_cond[j] = pre_cond[j].replace((CharSequence)("@"+instanceVarName), (CharSequence)instanceVarNameToValue.get(instanceVarName).toString());
					}
				}
				for(int k=0; k<parameterNames.length;k++ )
				{
					//System.out.println("pre cond - " +i +"----"+ j + " --- "+ pre_cond[j]);
					if(pre_cond[j].contains(parameterNames[k]))
					{
						pre_cond[j] = pre_cond[j].replace((CharSequence)parameterNames[k], (CharSequence)arguements[k].toString());
						//System.out.println("arguements : " + parameterNames[k]+ "--" + arguements[k] + "re_cond"+ pre_cond[j]);
					}
				}
				
				myJip.checkPreCond(pre_cond[j]);
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
	
	public ArrayList<String> getInstanceVariableNames(String contractString)
	{
		String parameterString = contractString.substring(contractString.indexOf("(")+1, contractString.indexOf(")"));
		String [] allParameterArray = parameterString.split(",");
		ArrayList<String> instanceVariableNames = new ArrayList<>();
		
		for(String eachcontractParam : allParameterArray)
		{
			eachcontractParam = eachcontractParam.trim();
			if(eachcontractParam.startsWith("@") && eachcontractParam.length() > 1)
				instanceVariableNames.add(eachcontractParam.substring(1, eachcontractParam.length()));
		}
		
		return instanceVariableNames;
				
	}
	
	public HashMap<String, Object> getInstanceValues(ArrayList<String> nameList, Object thisObject) throws NoSuchFieldError, IllegalAccessError
	{
		HashMap<String, Object> instanceNameToValue = new HashMap<>();
		try
		{
			for(String nameVar : nameList)
			{
				System.out.println("Log1 : "+ nameVar);
				Field f = thisObject.getClass().getDeclaredField(nameVar);
				f.setAccessible(true);
				System.out.println("Log : "+ nameVar+ " Value : "+ f.get(thisObject));
				instanceNameToValue.put(nameVar, f.get(thisObject));
			}
			
			for(String eachKey : instanceNameToValue.keySet())
			{
				System.out.println("Log (privateVar name Value )"+ eachKey +" : " + instanceNameToValue.get(eachKey));
			}
		}catch(Exception e){}
		return instanceNameToValue;
	}
	
}