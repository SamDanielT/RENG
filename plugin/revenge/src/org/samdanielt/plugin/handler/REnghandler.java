package org.samdanielt.plugin.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.core.resources.IResource;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IField;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;

public class REnghandler extends AbstractHandler {

	@Override
	public void addHandlerListener(IHandlerListener handlerListener) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		Shell shell = HandlerUtil.getActiveShell(event);
	    ISelection sel = HandlerUtil.getActiveMenuSelection(event);
	    IStructuredSelection selection = (IStructuredSelection) sel;

	    Object firstElement = selection.getFirstElement();
	    if (firstElement instanceof ICompilationUnit) {
	    	//Handle java File
	    	ICompilationUnit cu = (ICompilationUnit)firstElement;
	    	IResource resource = null;
			try {
				resource = cu.getUnderlyingResource();
				MessageDialog.openInformation(shell, "Info", "Java File Selected:" + resource.getFullPath().toString());
				IType[] types = cu.getTypes();
				for (IType iType : types) {
					IMethod[] methods = iType.getMethods();
					for (IMethod iMethod : methods) {
						int modifier= iMethod.getFlags();
						if(Modifier.isPublic(modifier)){
							System.out.println("Method :" + iMethod.getElementName());	
						}						
					}
					
					IField[] fields = iType.getFields();
					for (IField ifield : fields){
						System.out.println("Field :" + ifield.getElementName());
					}
				}
				
			} catch (JavaModelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    }
	    
	    MessageDialog.openInformation(shell, "Info" , firstElement.getClass().toString());
	    
	    System.out.println(firstElement.getClass().toString());
	    
		return null;
	}

}
