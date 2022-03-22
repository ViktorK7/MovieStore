package CourseProjectPackage;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DeleteClass extends JFrame{

	JLabel messenge = new JLabel("This customer is already deleted!");	
	
	JButton menuButton = new JButton("Menu");
	
	public DeleteClass() {
		this.setTitle("Delete");
		this.setVisible(true);
		this.setLocation(500,300);
		this.setSize(250, 100);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		
		this.add(messenge);
		this.add(menuButton);
		
		menuButton.addActionListener(new MenuAction());
		
	}
	
	class MenuAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Home home = new Home();
			dispose();
		}
		
	}
}
