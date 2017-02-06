package annotations;

public class ContractFailException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private String preCondContractFailMessage = "Precondition failed", postCondContractFailMessage = "Postcondition failed";
	public ContractFailException( String message ) {
		// TODO Auto-generated constructor stub
		super(message);
		
	}

}
