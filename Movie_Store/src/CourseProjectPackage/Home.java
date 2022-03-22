package CourseProjectPackage;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Home extends JFrame{
	
	JPanel upPanel = new JPanel();
	JPanel downPanel = new JPanel();
	
	
	JLabel titleLabel = new JLabel("Welcome");
	JLabel searchLabel = new JLabel("Search customer: ");
	JLabel movieLabel = new JLabel("Choose for movie: ");
	JLabel bookLabel = new JLabel("Choose for book: ");
	JLabel libraryLabel = new JLabel("Choose for library: ");
	JLabel premiereLabel = new JLabel("Choose for premiere: ");
	
	JButton searchButton = new JButton("Search");
	JButton movieButton = new JButton("Movie");
	JButton bookButton = new JButton("Book");
	JButton libraryButton = new JButton("Library");
	JButton premiereButton = new JButton("Premiere");
	
	public Home() {
		this.setTitle("Shop for movie and book");
		this.setVisible(true);
		this.setSize(400, 300);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(new GridLayout(2,1));
		this.setLocation(500, 300);
		this.add(upPanel);
		this.add(downPanel);
		
		
		//upPanel
		upPanel.setLayout(new FlowLayout(FlowLayout.CENTER,10,50));
		upPanel.add(titleLabel);
		
		//downPanel
		downPanel.setLayout(new GridLayout(5, 2, 0, 5));
		downPanel.add(searchLabel);
		downPanel.add(searchButton);
		downPanel.add(movieLabel);
		downPanel.add(movieButton);
		downPanel.add(bookLabel);
		downPanel.add(bookButton);
		downPanel.add(libraryLabel);
		downPanel.add(libraryButton);
		downPanel.add(premiereLabel);
		downPanel.add(premiereButton);
		
		searchButton.addActionListener(new SearchAction());
		movieButton.addActionListener(new MovieAction());
		bookButton.addActionListener(new BookAction());
		libraryButton.addActionListener(new LibraryAction());
		premiereButton.addActionListener(new PremiereAction());
	}
	
	
	class MovieAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			BuyMovieClass movieClass = new BuyMovieClass();
			dispose();
		}
		
	}
	
	class SearchAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			SearchClass search = new SearchClass();
			dispose();
		}
		
	}
	
	class BookAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			BuyBookClass buyBook = new BuyBookClass();
			dispose();
		}
		
	}
	
	class LibraryAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			LibraryClass library = new LibraryClass();
			dispose();
		}
		
	}
	
	class PremiereAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			PremiereClass premiere = new PremiereClass();
			dispose();
		}
		
	}
	
	
	

}
