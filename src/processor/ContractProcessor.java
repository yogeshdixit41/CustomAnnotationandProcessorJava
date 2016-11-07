package processor;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;

import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic.Kind;


import annotations.Contract;


@SupportedAnnotationTypes("src.annotations.Contract")
@SupportedSourceVersion(SourceVersion.RELEASE_8)


public class ContractProcessor extends AbstractProcessor {
	
	public ContractProcessor()
	{
		super();
	}

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		// TODO Auto-generated method stub
		
		processingEnv.getMessager().printMessage(Kind.ERROR, "Hello compile time message");
		
		for (Element elem : roundEnv.getElementsAnnotatedWith(Contract.class)) {
			if(elem.getKind() != ElementKind.METHOD)
			{
				processingEnv.getMessager().printMessage(Kind.NOTE, "Error");
				return true;
			}
			
			System.out.println("Hello ... Inside for ");
	        Contract contract = elem.getAnnotation(Contract.class);
	        String message = "annotation found in " + elem.getSimpleName()
	                       + " with complexity " + contract.pre_cond();
	        processingEnv.getMessager().printMessage(Kind.NOTE, message);
	    }
	    return true; // no further processing of this annotation type
	}
	
	

}
