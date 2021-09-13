package kubectl.handlers;

import java.io.IOException;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

public class KubectlHandler extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        IWorkbenchWindow window = HandlerUtil
                .getActiveWorkbenchWindowChecked(event);
        if (window != null) {
            IStructuredSelection selection = (IStructuredSelection) window
                    .getSelectionService().getSelection();
            IFile firstElement = (IFile) selection.getFirstElement();
            try {
                Runtime.getRuntime().exec("kubectl.exe apply -f "
                        + firstElement.getRawLocation().toOSString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}