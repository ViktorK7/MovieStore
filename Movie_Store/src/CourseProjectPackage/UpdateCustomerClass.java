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

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UpdateCustomerClass extends JFrame{
	
	Connection connection = null;
	PreparedStatement state = null;
	ResultSet rs = null;

	JPanel upPanel = new JPanel();
	JPanel downPanel = new JPanel();
	
	JLabel firstNameLabel = new JLabel("First Name:");
	JLabel lastNameLabel = new JLabel("Last Name:");
	JLabel productNameLabel;
	JLabel productDateLabel;
	JLabel productPriceLabel;

	JLabel productDateDBLabel;
	JLabel productPriceDBLabel;
	
	JTextField firstNameTextField = new JTextField();
	JTextField lastNameTextField = new JTextField();
	
	JButton updateButton = new JButton("Update");
	JButton menuButton = new JButton("Menu");
	
	String firstName;
	String lastName;
	
	ArrayList<Integer> productId = new ArrayList<Integer>();
	ArrayList<String> productName = new ArrayList<String>();
	ArrayList<Date> productDate = new ArrayList<Date>();
	ArrayList<Integer> productPrice = new ArrayList<Integer>();
	
	Integer[] productIdArray;
	String[] productNameArray;
	Date[] productDateArray;
	Integer[] productPriceArray;
	
	String movieName;
	String bookName;
	
	int numberMovie = 0;
	int numberBook = 0;
	int customerId = 0;
	
	String productNameCheck;
	
	boolean movie;
	boolean book;
	JComboBox<String> productComboBox;
	
	public UpdateCustomerClass(boolean book, boolean movie, int id) {
		this.setTitle("Update customer");
		this.setVisible(true);
		this.setSize(400,300);
		this.setLocation(500,300);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(new GridLayout(2, 1));
		this.add(upPanel);
		this.add(downPanel);
		this.customerId = id;
		this.movie = movie;
		this.book = book;

		CheckData(book, movie, id);
		
		firstNameTextField.setText(firstName);
		lastNameTextField.setText(lastName);
		
		productIdArray = new Integer[productId.size()];
		productIdArray = productId.toArray(productIdArray);
		
		productNameArray = new String[productName.size()];
		productNameArray = productName.toArray(productNameArray);
		
		productDateArray = new Date[productDate.size()];
		productDateArray = productDate.toArray(productDateArray);
		
		productPriceArray = new Integer[productPrice.size()];
		productPriceArray = productPrice.toArray(productPriceArray);

		CheckMovieOrBook(book,movie);
		
		//upPanel
		upPanel.setLayout(new GridLayout(5,2));		
		upPanel.add(firstNameLabel);
		upPanel.add(firstNameTextField);
		upPanel.add(lastNameLabel);
		upPanel.add(lastNameTextField);
		upPanel.add(productNameLabel);
		upPanel.add(productComboBox);
		upPanel.add(productDateLabel);
		upPanel.add(productDateDBLabel);
		upPanel.add(productPriceLabel);
		upPanel.add(productPriceDBLabel);
		
		//downPanel
		downPanel.setLayout(new FlowLayout(FlowLayout.CENTER,10 ,50));
		downPanel.add(updateButton);
		downPanel.add(menuButton);
		
		productComboBox.addActionListener(new ComboBoxAction());
		updateButton.addActionListener(new UpdateAction());
		menuButton.addActionListener(new MenuAction());

	}
	
	public void CheckMovieOrBook(boolean book, boolean movie) {
		if(movie) {
			productNameLabel = new JLabel("Movie Name: ");
			productDateLabel = new JLabel("Movie Date");
			productPriceLabel = new JLabel("Movie Price");
			
			for(int i=0; i<productNameArray.length; i++) {
				if(movieName == productNameArray[i]) {
					productComboBox = new JComboBox<String>(productNameArray);
					productComboBox.setSelectedIndex(i);
					productDateDBLabel = new JLabel(productDateArray[i].toString());
					productPriceDBLabel = new JLabel(productPriceArray[i].toString());
				}
			}
		}
		else if(book){
			productNameLabel = new JLabel("Book Name: ");
			productDateLabel = new JLabel("Book Date");
			productPriceLabel = new JLabel("Book Price");
			
			for(int i=0; i<productNameArray.length; i++) {
				if(bookName == productNameArray[i]) {
					productComboBox = new JComboBox<String>(productNameArray);
					productComboBox.setSelectedIndex(i);
					productDateDBLabel = new JLabel(productDateArray[i].toString());
					productPriceDBLabel = new JLabel(productPriceArray[i].toString());
				}
			}
		}
	}
	
	public void CheckData(boolean book, boolean movie, int id) { 
		String sql;
		if(movie) {
			sql = "Select c.FirstName, c.LastName, m.Movie_Name from Customers c"
					+ " Inner join Movies m ON c.Movie_Id = m.Movie_Id"
					+ " Where ID ='" + id + "';";
			connection = DBConnection.getConnection();
			
			try {
				state = connection.prepareStatement(sql);
				rs = state.executeQuery();
				while(rs.next()) {
					firstName = rs.getString("FirstName");
					lastName = rs.getString("LastName");
					movieName = rs.getString("Movie_Name");
				}
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			sql = "Select * from MOVIES";
			connection = DBConnection.getConnection();
			
			try {
				state = connection.prepareStatement(sql);
				rs = state.executeQuery();
				while(rs.next()) {
					productId.add(rs.getInt("Movie_Id"));
					productName.add(rs.getString("Movie_Name"));
					productDate.add(rs.getDate("Movie_Date"));
					productPrice.add(rs.getInt("Movie_Price"));
				}
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(book) {
			sql = "Select c.FirstName, c.LastName, b.Book_Name from Customers c"
					+ " Inner join Books b ON c.Book_Id = b.Book_Id"
					+ " Where ID ='" + id + "';";
			connection = DBConnection.getConnection();
			
			try {
				state = connection.prepareStatement(sql);
				rs = state.executeQuery();
				while(rs.next()) {
					firstName = rs.getString("FirstName");
					lastName = rs.getString("LastName");
					bookName = rs.getString("Book_Name");
				}
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			sql = "Select * from BOOKS";
			connection = DBConnection.getConnection();
			
			try {
				state = connection.prepareStatement(sql);
				rs = state.executeQuery();
				while(rs.next()) {
					productId.add(rs.getInt("Book_Id"));
					productName.add(rs.getString("Book_Name"));
					productDate.add(rs.getDate("Book_Date"));
					productPrice.add(rs.getInt("Book_Price"));
				}
				connection.close();
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	
	}
	
	class ComboBoxAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int i = productComboBox.getSelectedIndex();
			productDateDBLabel.setText(productDateArray[i].toString());
			productPriceDBLabel.setText(productPriceArray[i].toString());
			
			upPanel.add(productDateLabel);
			upPanel.add(productDateDBLabel);
			upPanel.add(productPriceLabel);
			upPanel.add(productPriceDBLabel);
		}
		
	}
	
	class UpdateAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int productId = 0;
			String fName = firstNameTextField.getText();
			String lName = lastNameTextField.getText();
			String sql = null;
			if(movie) {
				String prodName = productComboBox.getSelectedItem().toString();
				for(int i=0; i<productNameArray.length; i++) {
					if(prodName == productNameArray[i]) {
						productId = productIdArray[i];
					}
				}
				
				sql = "Update Customers "
						+ " Set FirstName = '" + fName + "', LastName = '" + lName + "', Movie_ID = '" + productId + "', Book_ID = null"
						+ " Where Id = '" + customerId + "';";
			}
			else if(book) {
				String prodName = productComboBox.getSelectedItem().toString();
				for(int i=0; i<productNameArray.length; i++) {
					if(prodName == productNameArray[i]) {
						productId = productIdArray[i];
					}
				}
				
				sql = "Update Customers "
						+ " Set FirstName = '" + fName + "', LastName = '" + lName + "', Movie_ID = null, Book_ID = '" + productId + "'"
						+ " Where Id = '" + customerId + "';";
			}
			
			
			connection = DBConnection.getConnection();
			
			try {
				state = connection.prepareStatement(sql);
				state.execute();
				connection.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			ResultFromSearchClass result = new ResultFromSearchClass(fName, lName);
			dispose();
		}
		
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
