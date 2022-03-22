package CourseProjectPackage;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.h2.bnf.context.DbColumn;

public class ResultFromSearchClass extends JFrame{
	
	Connection connection = null;
	PreparedStatement state = null;
	ResultSet rs = null;
	
	JPanel upPanel = new JPanel();
	JPanel downPanel = new JPanel();

	JLabel firstNameLabel = new JLabel("First Name: ");
	JLabel lastNameLabel = new JLabel("Last Name: ");
	JLabel NameLabel = new JLabel("Movie Name: ");
	JLabel DateLabel= new JLabel("Movie Date: ");
	JLabel PriceLabel = new JLabel("Movie Price:");
	
	JLabel firstNameDBLabel = new JLabel();
	JLabel lastNameDBLabel = new JLabel();
	JLabel productNameDBLabel = new JLabel();
	JLabel dateDBLabel = new JLabel();
	JLabel priceDBLabel = new JLabel();
	
	String firstName;
	String lastName;
	String productName;
	Date date;
	Integer price;
	
	int customerId = 0;
	int movieId = 0;
	int bookId = 0;
	
	boolean movie;
	boolean book;
	
	JButton deleteButton = new JButton("Delete");
	JButton updateButton = new JButton("Update");
	JButton backButton = new JButton("Back");
	JButton menuButton = new JButton("Menu");
	
	public ResultFromSearchClass(String name, String surname) {
		this.setTitle("Fount customer");
		this.setVisible(true);
		this.setSize(400, 300);
		this.setLocation(500, 300);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(new GridLayout(2,1));
		this.add(upPanel);
		this.add(downPanel);
		
		CheckData(name, surname);
		
		firstNameDBLabel = new JLabel(firstName);
		lastNameDBLabel = new JLabel(lastName);
		productNameDBLabel = new JLabel(productName);
		dateDBLabel = new JLabel(date.toString());
		priceDBLabel =new JLabel(price.toString());
		
		
		//upPanel
		upPanel.setLayout(new GridLayout(6,2));
		upPanel.add(firstNameLabel);
		upPanel.add(firstNameDBLabel);
		upPanel.add(lastNameLabel);
		upPanel.add(lastNameDBLabel);
		upPanel.add(NameLabel);
		upPanel.add(productNameDBLabel);
		upPanel.add(DateLabel);
		upPanel.add(dateDBLabel);
		upPanel.add(PriceLabel);
		upPanel.add(priceDBLabel);
		
		//downPanel
		downPanel.setLayout(new FlowLayout(FlowLayout.CENTER,10,50));
		downPanel.add(deleteButton);
		downPanel.add(updateButton);
		downPanel.add(backButton);
		downPanel.add(menuButton);
		
		deleteButton.addActionListener(new DeleteAction());
		updateButton.addActionListener(new UpdateAction());
		backButton.addActionListener(new BackAction());
		menuButton.addActionListener(new MenuAction());
		
	}
	
	public void CheckData(String fName, String lName) {
		String sql = "Select * From Customers "
				+ " Where FirstName = '" + fName + "' AND LastName = '" + lName +"';";
		connection = DBConnection.getConnection();
		
		try {
			state = connection.prepareStatement(sql);
			rs = state.executeQuery();
			while(rs.next()) {
				movieId = rs.getInt("Movie_ID");
				bookId = rs.getInt("Book_ID");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(movieId != 0) {
			movie = true;
			sql = "Select c.ID, c.FirstName, c.LastName, c.Movie_ID, m.Movie_Name, m.Movie_Date, m.Movie_Price From CUSTOMERS c"
					+ " INNER Join MOVIES m ON c.Movie_ID = m.Movie_ID "
					+ " Where c.FirstName = '" + fName + "' AND c.LastName = '" + lName + "';"; 
			connection = DBConnection.getConnection();
			 try {
				state = connection.prepareStatement(sql);
				rs = state.executeQuery();
				while(rs.next()) {
					customerId = rs.getInt("ID");
					firstName = rs.getString("FirstName");
					lastName = rs.getString("LastName");
					productName = rs.getString("Movie_Name");
					date = rs.getDate("Movie_Date");
					price = rs.getInt("Movie_Price");
				}
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(bookId != 0){
			book = true;
			sql = "Select c.ID, c.FirstName, c.LastName, c.Book_ID, b.Book_Name, b.Book_Date, b.Book_Price From CUSTOMERS c"
					+ " INNER Join BOOKS b ON c.Book_ID = b.Book_ID "
					+ " Where c.FirstName = '" + fName + "' AND c.LastName = '" + lName + "';"; 
			connection = DBConnection.getConnection();
			 try {
				state = connection.prepareStatement(sql);
				rs = state.executeQuery();
				while(rs.next()) {
					customerId = rs.getInt("ID");
					firstName = rs.getString("FirstName");
					lastName = rs.getString("LastName");
					productName = rs.getString("Book_Name");
					date = rs.getDate("Book_Date");
					price = rs.getInt("Book_Price");
				}
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
		}
		else {
			System.out.println("Dont Have That Person");
		}
	}
	
	class DeleteAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String sql = "Delete from Customers"
					+ " Where ID = '" + customerId +"'";
			connection = DBConnection.getConnection();
			
			try {
				state = connection.prepareStatement(sql);
				state.execute();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			DeleteClass delete = new DeleteClass();
			dispose();
		}
		
	}
	
	class UpdateAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			UpdateCustomerClass update = new UpdateCustomerClass(book, movie, customerId);
			dispose();
		}
		
	}
	
	
	
	class BackAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			SearchClass search = new SearchClass();
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
