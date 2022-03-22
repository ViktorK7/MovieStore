package CourseProjectPackage;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LibraryClass extends JFrame{
	
	JPanel upPanel = new JPanel();
	JPanel middlePanel = new JPanel();
	JPanel downPanel = new JPanel();
	
	JLabel movieLabel = new JLabel("Movie library: ");
	JLabel bookLabel = new JLabel("Book library:  ");
	
	JButton movieButton = new JButton("Movie");
	JButton bookButton = new JButton("Book ");
	JButton backButton = new JButton("Back");

	public LibraryClass() {
		this.setTitle("Choose library");
		this.setVisible(true);
		this.setSize(300, 150);
		this.setLocation(500,300);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(new GridLayout(3,1));
		this.add(upPanel);
		this.add(middlePanel);
		this.add(downPanel);
		
		//upPanel
		upPanel.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		upPanel.add(movieLabel);
		upPanel.add(movieButton);
		
		//middlePanel
		middlePanel.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		middlePanel.add(bookLabel);
		middlePanel.add(bookButton);
		
		//downPanel
		downPanel.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		downPanel.add(backButton);
		
		movieButton.addActionListener(new MovieAction());
		bookButton.addActionListener(new BookAction());
		backButton.addActionListener(new BackaAction());
	}
	
	class MovieAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			MovieLibraryClass movieLibrary = new MovieLibraryClass();
			dispose();
		}
		
	}
	
	class BookAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			BookLibraryClass bookLibrary = new BookLibraryClass();
			dispose();
		}
		
	}
	
	class BackaAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Home home = new Home();
			dispose();
		}
		
	}
}
