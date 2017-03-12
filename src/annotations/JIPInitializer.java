package annotations;



import com.ugos.jiprolog.engine.JIPEngine;
import com.ugos.jiprolog.engine.JIPQuery;
import com.ugos.jiprolog.engine.JIPSyntaxErrorException;

public class JIPInitializer {
	
	public final JIPEngine jip = new JIPEngine();
	
	
	public JIPInitializer()
	{
		jip.setDebug(false);
		jip.setTrace(false);
		jip.setEnvVariable("debug", "off");
		try
		{
			/*
			 * this should be converted to
			 * it should consider all the pl files found at build path
			 */
			jip.compileFile("C:\\My Files\\Studies\\MasterThesisProject\\EclipseWorkSPace\\TestJIPProject\\src\\com\\yd\\contractprogramming\\factorial.pl");
			jip.loadFile("C:\\My Files\\Studies\\MasterThesisProject\\EclipseWorkSPace\\TestJIPProject\\src\\com\\yd\\contractprogramming\\factorial.jip");
		}
		catch(JIPSyntaxErrorException ex)
		{
		    // there is a syntax error in the query
		    //ex.printStackTrace();
		    System.exit(0);
		}
	}
	
	/**
	 * * This method will be called from annotation processor
	 * @param string
	 * @param var
	 * @return
	 */
	 public boolean checkPreCond(String string, Object var) throws ContractFailException{
		// TODO Auto-generated method stub
		String queryString = "nonnegative(" + (int)var + ").";
		System.out.println("QS : " + queryString);
		
		
		// open a synchronous query
		JIPQuery jipQuery = jip.openSynchronousQuery(queryString);
		
		boolean queryResult = readSolution(jipQuery);
		//boolean queryResult = true;
		if(queryResult)
		{
			System.out.println("Given preconditions are satisfied");
			return true;
		}
		else
		{
			throw new ContractFailException("Contract failure : preconditions failed");
		}
		/*// Loop while there is another solution
		while (jipQuery.hasMoreChoicePoints())
		{
		    System.out.println("Solution : "+ solution);
		    solution = jipQuery.nextSolution();
		}*/
		
	}
	 
	 public boolean checkPreCond(String queryString)
	 {
		 JIPQuery jipQuery = jip.openSynchronousQuery(queryString);
			
		boolean queryResult = readSolution(jipQuery);
		if(queryResult)
		{
			System.out.println("Given preconditions are satisfied");
			return true;
		}
		else
		{
			throw new ContractFailException("Contract failure : preconditions failed");
		}
	 }

	 
		/**
		 * * This method will be called from annotation processor
		 * @param string
		 * @param var
		 * @return
		 */
		 public boolean checkPostCond(String string, Object var) throws ContractFailException {
			// TODO Auto-generated method stub
			String queryString = "nonzero(" + var + ").";
			System.out.println("QS : " + queryString);
			
			
			// open a synchronous query
			
			JIPQuery jipQuery = jip.openSynchronousQuery(queryString);
			
			boolean queryResult = readSolution(jipQuery);
			//boolean queryResult = true;
			if(queryResult)
			{
				System.out.println("Given postconditions are satisfied");
				return true;
			}
			else
			{
				throw new ContractFailException("Contract failure : postconditions failed");
			}
			
		}
		 
		 public boolean checkPostCondListOrdered(String funName, Object var) throws ContractFailException {
				// TODO Auto-generated method stub
			 String queryString = "";
			 	if(funName.contains("nonzero"))
			 	{
			 		queryString = "nonzero(" + var + ").";
					System.out.println("QS : " + queryString);
			 	}
			 	else if(funName.contains("ordered"))
			 	{
			 		String arrayString = "[";
			 		for(int eachVal : (int []) var)
			 			arrayString += eachVal +",";
			 		arrayString = arrayString.substring(0, arrayString.length()-1);
			 		arrayString += "]";
			 		System.out.println("arrayString : "+ arrayString);
			 		queryString = "ordered("+ arrayString + " ).";
			 		System.out.println("QS : " + queryString);
			 	}
				
				
				// open a synchronous query
				JIPQuery jipQuery = jip.openSynchronousQuery(queryString);
				boolean queryResult = readSolution(jipQuery);
				//boolean queryResult = true;
				if(queryResult)
				{
					System.out.println("Given postconditions are satisfied");
					return true;
				}
				else
				{
					throw new ContractFailException("Contract failure : postconditions failed");
				}
				
			}	 
	 
	 
	public boolean readSolution(JIPQuery jq)
	{
		if(jq.nextSolution() != null)
		{
			//System.out.println("Yes");
			return true;
		}
		else
		{
			//System.out.println("No");
			return false;
		}
	}
	 
}

