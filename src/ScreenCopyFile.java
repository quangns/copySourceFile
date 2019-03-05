
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;

public class ScreenCopyFile extends JFrame implements ActionListener {
	private JTextField pathProject;
	private JTextField pathExport;
	private JTextArea pathFiles;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScreenCopyFile frame = new ScreenCopyFile();
					frame.setVisible(true);
					frame.setSize(550, 350);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ScreenCopyFile() {
		getContentPane().setLayout(null);
		
		JLabel lblProject = new JLabel("Project");
		lblProject.setBounds(36, 25, 46, 14);
		getContentPane().add(lblProject);
		
		pathProject = new JTextField();
		pathProject.setBounds(106, 22, 386, 20);
		getContentPane().add(pathProject);
		pathProject.setColumns(10);
		
		JLabel lblExport = new JLabel("Export");
		lblExport.setBounds(36, 50, 46, 14);
		getContentPane().add(lblExport);
		
		pathExport = new JTextField();
		pathExport.setBounds(106, 47, 386, 20);
		getContentPane().add(pathExport);
		pathExport.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(this);
		btnOk.setBounds(403, 249, 89, 23);
		getContentPane().add(btnOk);
		
		pathFiles = new JTextArea();
		pathFiles.setBounds(36, 85, 456, 153);
		pathFiles.setLineWrap(true);
		pathFiles.setWrapStyleWord(true);
		
		JScrollPane scrollBar = new JScrollPane(pathFiles);
		scrollBar.setBounds(36, 85, 456, 153);
		
		getContentPane().add(scrollBar);
	}
	
	public void actionPerformed(ActionEvent evt) {
		String[] text = pathFiles.getText().split("\\n");
		List<String> pathFiles = new ArrayList<>();
		for(String s: text) {
			pathFiles.add(s);
		}
		String project = pathProject.getText();
		String export = pathExport.getText();
		CopyFiles copy = new CopyFiles(project, export, pathFiles);
		copy.analysisPathFiles();
	}
}
