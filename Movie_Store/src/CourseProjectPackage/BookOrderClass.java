package CourseProjectPackage;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BookOrderClass extends JFrame{
	
	Connection connection = null;
	PreparedStatement state = null;
	ResultSet rs = null;
	
	JPanel upPanel = new JPanel();
	JPanel downPanel = new JPanel();

	JLabel firstNameLabel = new JLabel("Fisrt Name: ");
	JLabel lastNameLabel = new JLabel("Last Name: ");
	JLabel bookNameLabel = new JLabel("Book Name: ");
	JLabel bookDateLabel = new JLabel("Book Date: ");
	JLabel bookPriceLabel = new JLabel("Book Price: ");
	
	JLabel firstNameDBLabel = new JLabel();
	JLabel lastNameDBLabel = new JLabel();
	JLabel bookNameDBLabel = new JLabel();
	JLabel bookDateDBLabel = new JLabel();
	JLabel bookPriceDBLabel = new JLabel();
	
	String firstName;
	String lastName;
	String book;
	String date;
	String price;
	
	JButton backButton = new JButton("Back");
	JButton menuButton = new JButton("Menu");
	
	public BookOrderClass(String name, String surname) {
		this.setTitle("Book order");
		this.setVisible(true);
		this.setSize(400,300);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocation(500, 300);
		this.setLayout(new GridLayout(2,1));
		this.add(upPanel);
		this.add(downPanel);
		
		CheckData(name,surname);
		
		firstNameDBLabel.setText(firstName);;
		lastNameDBLabel.setText(lastName);;
		bookNameDBLabel.setText(book);
		bookDateDBLabel.setText(date);
		bookPriceDBLabel.setText(price);
		
		//upPanel
		upPanel.setLayout(new GridLayout(5,2));
		upPanel.add(firstNameLabel);
		upPanel.add(firstNameDBLabel);
		upPanel.add(lastNameLabel);
		upPanel.add(lastNameDBLabel);
		upPanel.add(bookNameLabel);
		upPanel.add(bookNameDBLabel);
		upPanel.add(bookDateLabel);
		upPanel.add(bookDateDBLabel);
		upPanel.add(bookPriceLabel);
		upPanel.add(bookPriceDBLabel);
		
		//downPanel
		downPanel.setLayout(new FlowLayout(FlowLayout.CENTER,10,50));
		downPanel.add(backButton);
		downPanel.add(menuButton);
		
		backButton.addActionListener(new BackAction());
		menuButton.addActionListener(new MenuAction());
	}
	
	public void CheckData(String fName, String lName) {
		String sql = "SELECT c.FIRSTNAME, c.LASTNAME, c.BOOK_ID, b.BOOK_NAME, b.BOOK_DATE, b.BOOK_PRICE FROM CUSTOMERS c"
				+ " INNER JOIN BOOKS b ON c.BOOK_ID = b.BOOK_ID"
				+ " WHERE c.FIRSTNAME = '" + fName + "' AND c.LASTNAME = '" + lName + "';";
		connection = DBConnection.getConnection();
		
		
		try {
			state = connection.prepareStatement(sql);
			rs = state.executeQuery();
			while(rs.next()) {
				firstName = rs.getString("FIRSTNAME");
				lastName = rs.getString("LASTNAME");
				book = rs.getString("BOOK_NAME");
				date  = rs.getDate("BOOK_DATE").toString();
				price = rs.getString("BOOK_PRICE");
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
			BuyBookClass buyBook = new BuyBookClass();
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
