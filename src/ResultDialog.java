import javax.swing.JOptionPane;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class ResultDialog {
	public void ShellInfo(String info) {
		Shell shell = new Shell();
		MessageDialog.openInformation(shell, "Info", info);
	}
}
