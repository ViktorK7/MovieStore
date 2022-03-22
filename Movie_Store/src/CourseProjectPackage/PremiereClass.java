package CourseProjectPackage;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PremiereClass extends JFrame{
	
	Connection connection = null;
	PreparedStatement state = null;
	ResultSet rs = null;
	
	JPanel moviePanel = new JPanel();
	JPanel bookPanel = new JPanel();
	JPanel buttonPanel = new JPanel();
	
	JLabel movieLabel = new JLabel("Movie premiere");
	JLabel bookLabel = new JLabel("Book premiere");
	
	ArrayList<String> movieName = new ArrayList<String>();
	ArrayList<Date> movieDate = new ArrayList<Date>();
	ArrayList<Integer> moviePrice = new ArrayList<Integer>();
	
	ArrayList<String> bookName = new ArrayList<String>();
	ArrayList<Date> bookDate = new ArrayList<Date>();
	ArrayList<Integer> bookPrice = new ArrayList<Integer>();
	
	String[] movieNameArray;
	Date[] movieDateArray;
	Integer[] moviePriceArray;
	
	String[] bookNameArray;
	Date[] bookDateArray;
	Integer[] bookPriceArray;
	
	
	JLabel movieNameDBLabel;
	JLabel movieDataDBLabel;
	JLabel moviePriceDBLabel;
	
	JLabel bookNameDBLabel;
	JLabel bookDateDBLabel;
	JLabel bookPriceDBLabel;
	
	JButton backButton = new JButton("Back");

	int x;
	final int y = 3;
	
	public PremiereClass() {
		this.setTitle("Premiere");
		this.setVisible(true);
		this.setSize(400, 300);
		this.setLocation(500, 200);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(new GridLayout(3,1,0,10));
		
		this.add(moviePanel);
		this.add(bookPanel);
		this.add(buttonPanel);
		
		//moviePanel
		PremiereMovie();
		moviePanel.setBorder(BorderFactory.createLoweredBevelBorder());
		
		movieNameArray = new String[movieName.size()];
		movieNameArray = movieName.toArray(movieNameArray);
		
		movieDateArray = new Date[movieDate.size()];
		movieDateArray = movieDate.toArray(movieDateArray);
		
		moviePriceArray = new Integer[moviePrice.size()];
		moviePriceArray = moviePrice.toArray(moviePriceArray);
		
		moviePanel.setLayout(new GridLayout(x+1,y,10,10));
		
		movieNameDBLabel = new JLabel("Movie Name");
		movieDataDBLabel = new JLabel("Movie Date");
		moviePriceDBLabel = new JLabel("Movie Price");
		
		moviePanel.add(movieNameDBLabel);
		moviePanel.add(movieDataDBLabel);
		moviePanel.add(moviePriceDBLabel);
		
		for(int i=0; i<x ; i++) {
			movieNameDBLabel = new JLabel(movieNameArray[i]);
			movieDataDBLabel = new JLabel(movieDateArray[i].toString());
			moviePriceDBLabel = new JLabel(moviePriceArray[i].toString());
			
			moviePanel.add(movieNameDBLabel);
			moviePanel.add(movieDataDBLabel);
			moviePanel.add(moviePriceDBLabel);
		}
		
		//bookPanel
		PremiereBook();
		bookPanel.setBorder(BorderFactory.createLoweredBevelBorder());
		
		bookNameArray = new String[bookName.size()];
		bookNameArray = bookName.toArray(bookNameArray);
		
		bookDateArray = new Date[bookDate.size()];
		bookDateArray = bookDate.toArray(bookDateArray);
		
		bookPriceArray = new Integer[bookPrice.size()];
		bookPriceArray = bookPrice.toArray(bookPriceArray);
		
		bookPanel.setLayout(new GridLayout(x+1,y,10,10));
		
		bookNameDBLabel = new JLabel("Book Name");
		bookDateDBLabel = new JLabel("Book Date");
		bookPriceDBLabel = new JLabel("Book Price");
		
		bookPanel.add(bookNameDBLabel);
		bookPanel.add(bookDateDBLabel);
		bookPanel.add(bookPriceDBLabel);
		for(int i=0; i<x; i++) {
			bookNameDBLabel = new JLabel(bookNameArray[i]);
			bookDateDBLabel = new JLabel(bookDateArray[i].toString());
			bookPriceDBLabel = new JLabel(bookPriceArray[i].toString());
			
			bookPanel.add(bookNameDBLabel);
			bookPanel.add(bookDateDBLabel);
			bookPanel.add(bookPriceDBLabel);
		}
		
		//buttonPanel
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER,10,50));
		buttonPanel.add(backButton);
		
		backButton.addActionListener(new BackAction());
	}
	
	public void PremiereMovie() {
		x = 0;
		String sql = "Select * from MOVIES"
				+ " where MOVIE_DATE > '2019-12-31'"
				+ "order by Movie_Date ASC";
		connection = DBConnection.getConnection();
		
		try {
			state = connection.prepareStatement(sql);
			rs = state.executeQuery();
			while(rs.next()) {
				movieName.add(rs.getString("MOVIE_NAME"));
				movieDate.add(rs.getDate("MOVIE_DATE"));
				moviePrice.add(rs.getInt("MOVIE_PRICE"));
				x++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public void PremiereBook() {
		x=0;
		String sql = "Select * from BOOKS"
				+ " where BOOK_DATE > '2019-12-31'"
				+ " order by BOOK_DATE ASC;";
		connection = DBConnection.getConnection();
		
		
		try {
			state = connection.prepareStatement(sql);
			rs = state.executeQuery();
			while(rs.next()) {
				bookName.add(rs.getString("BOOK_NAME"));
				bookDate.add(rs.getDate("BOOK_DATE"));
				bookPrice.add(rs.getInt("BOOK_PRICE"));
				x++;
			}

			connection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	class BackAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Home home = new Home();
			dispose();
		}
		
	}
}
