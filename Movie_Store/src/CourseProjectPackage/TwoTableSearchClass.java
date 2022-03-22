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

public class TwoTableSearchClass extends JFrame{
	
	Connection connection = null;
	PreparedStatement state = null;
	ResultSet rs = null;
	
	JPanel productPanel = new JPanel();
	JPanel buttonPanel = new JPanel();
	
	JLabel firstNameLabel;
	JLabel lastNameLabel;
	JLabel productNameLabel;
	JLabel productDateLabel;
	JLabel productPriceLabel;
	
	ArrayList<String> firstName = new ArrayList<String>();
	ArrayList<String> lastName = new ArrayList<String>();
	ArrayList<String> productName = new ArrayList<String>();
	ArrayList<Date> productDate = new ArrayList<Date>();
	ArrayList<Integer> productPrice = new ArrayList<Integer>();
	
	String[] firstNameArray;
	String[] lastNameArray;
	String[] productNameArray;
	Date[] productDateArray;
	Integer[] productPriceArray;
	
	JButton backButton = new JButton("Back");

	int x = 0;
	final int y = 5;
	
	public TwoTableSearchClass(String name, String movieName, String bookName) {
		this.setTitle("Search from two tables");
		this.setVisible(true);
		this.setLocation(500,300);
		this.setSize(600,300);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(new GridLayout(2,1,10,10));
		this.add(productPanel);
		this.add(buttonPanel);
		
		CheckData(name,movieName,bookName);
		//productPanel
		productPanel.setBorder(BorderFactory.createLoweredBevelBorder());
		productPanel.setLayout(new GridLayout(x+1,y,5,5));
		
		firstNameArray = new String[firstName.size()];
		firstNameArray = firstName.toArray(firstNameArray);
		
		lastNameArray = new String[lastName.size()];
		lastNameArray = lastName.toArray(lastNameArray);
		
		productNameArray = new String[productName.size()];
		productNameArray = productName.toArray(productNameArray);
		
		productDateArray = new Date[productDate.size()];
		productDateArray = productDate.toArray(productDateArray);
		
		productPriceArray = new Integer[productPrice.size()];
		productPriceArray = productPrice.toArray(productPriceArray);
		
		
		firstNameLabel = new JLabel("First Name");
		lastNameLabel = new JLabel("Last Name");
		if(movieName != null) {
			productNameLabel = new JLabel("Movie Name");
			productDateLabel = new JLabel("Movie Date");
			productPriceLabel = new JLabel("Movie Price");
		}
		else {
			productNameLabel = new JLabel("Book Name");
			productDateLabel = new JLabel("Book Date");
			productPriceLabel = new JLabel("Book Price");
		}
		
		productPanel.add(firstNameLabel);
		productPanel.add(lastNameLabel);
		productPanel.add(productNameLabel);
		productPanel.add(productDateLabel);
		productPanel.add(productPriceLabel);
		for(int i=0; i<x; i++) {
			firstNameLabel = new JLabel(firstNameArray[i]);
			lastNameLabel = new JLabel(lastNameArray[i]);
			productNameLabel = new JLabel(productNameArray[i]);
			productDateLabel = new JLabel(productDateArray[i].toString());
			productPriceLabel = new JLabel(productPriceArray[i].toString());
			
			productPanel.add(firstNameLabel);
			productPanel.add(lastNameLabel);
			productPanel.add(productNameLabel);
			productPanel.add(productDateLabel);
			productPanel.add(productPriceLabel);
		}
		
		//buttonPanel
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER,10,20));
		buttonPanel.add(backButton);
		
		backButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SearchClass search = new SearchClass();
				dispose();
			}
		});
	}
	
	public void CheckData(String name, String movieName, String bookName) {
		String sql;
		connection = DBConnection.getConnection();
		if(movieName != null) {
			sql = "Select c.FirstName, c.LastName, m.Movie_Name, m.Movie_Date, m.Movie_Price from Customers c"
					+ " Inner join Movies m ON c.Movie_Id = m.Movie_Id"
					+ " Where c.FirstName = '" + name + "' and m.Movie_Name = '" + movieName + "';";
			
			try {
				state = connection.prepareStatement(sql);
				rs = state.executeQuery();
				while(rs.next()) {
					firstName.add(rs.getString("FirstName"));
					lastName.add(rs.getString("LastName"));
					productName.add(rs.getString("Movie_Name"));
					productDate.add(rs.getDate("Movie_Date"));
					productPrice.add(rs.getInt("Movie_Price"));
					x++;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			sql = "Select c.FirstName, c.LastName, b.Book_Name, b.Book_Date, b.Book_Price from Customers c"
					+ " Inner join Books b ON c.Book_Id = b.Book_Id"
					+ " Where c.FirstName = '" + name + "' and b.Book_Name = '" + bookName + "';";
			
			try {
				state = connection.prepareStatement(sql);
				rs = state.executeQuery();
				while(rs.next()) {
					firstName.add(rs.getString("FirstName"));
					lastName.add(rs.getString("LastName"));
					productName.add(rs.getString("Book_Name"));
					productDate.add(rs.getDate("Book_Date"));
					productPrice.add(rs.getInt("Book_Price"));
					x++;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
