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
import java.util.ArrayList;
import java.util.concurrent.Flow;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BuyBookClass extends JFrame{
	
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
	
	JTextField firstNameTextField = new JTextField();
	JTextField lastNameTextField = new JTextField();
	
	JLabel bookDateDBLabel = new JLabel();
	JLabel bookPriceDBLabel = new JLabel();
	
	ArrayList<Integer> bookId = new ArrayList<Integer>();
	ArrayList<String> bookName = new ArrayList<String>();
	ArrayList<Date> bookDate = new ArrayList<Date>();
	ArrayList<Integer> bookPrice = new ArrayList<Integer>();
	
	Integer[] bookIdArray;
	String[] bookNameArray;
	Date[] bookDateArray;
	Integer[] bookPriceArray;	
	
	String date;
	String price;
	
	JComboBox<String> bookComboBox;
	
	JButton buyButton = new JButton("Buy");
	JButton cancelButton = new JButton("Cancel");
	
	public BuyBookClass() {
		this.setTitle("Buy book");
		this.setVisible(true);
		this.setSize(400,300);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocation(500, 300);
		this.setLayout(new GridLayout(2,1));
		
		this.add(upPanel);
		this.add(downPanel);
		
		CheckData();
		
		bookIdArray = new Integer[bookId.size()];
		bookIdArray = bookId.toArray(bookIdArray);
		
		bookNameArray = new String[bookName.size()];
		bookNameArray = bookName.toArray(bookNameArray);
		
		bookDateArray = new Date[bookDate.size()];
		bookDateArray = bookDate.toArray(bookDateArray);
		
		bookPriceArray = new Integer[bookPrice.size()];
		bookPriceArray = bookPrice.toArray(bookPriceArray);
		
		bookComboBox = new JComboBox<String>(bookNameArray);
		
		int i = bookComboBox.getSelectedIndex();
		date = bookDateArray[i].toString();
		price = bookPriceArray[i].toString();
		
		bookDateDBLabel.setText(date);
		bookPriceDBLabel.setText(price);
		
		//upPanel
		upPanel.setLayout(new GridLayout(5,2));
		upPanel.add(firstNameLabel);
		upPanel.add(firstNameTextField);
		upPanel.add(lastNameLabel);
		upPanel.add(lastNameTextField);
		upPanel.add(bookNameLabel);
		upPanel.add(bookComboBox);
		upPanel.add(bookDateLabel);
		upPanel.add(bookDateDBLabel);
		upPanel.add(bookPriceLabel);
		upPanel.add(bookPriceDBLabel);
		
		
		//downPanel
		downPanel.setLayout(new FlowLayout(FlowLayout.CENTER,10,50));
		downPanel.add(buyButton);
		downPanel.add(cancelButton);
		
		bookComboBox.addActionListener(new ComboBoxAction());
		buyButton.addActionListener(new BuyAction());
		cancelButton.addActionListener(new CancelAction());
	}
	
	public void CheckData() {
		String sql = "SELECT * FROM BOOKS"
				+ " Order by Book_Name";
		connection = DBConnection.getConnection();
		
		try {
			state = connection.prepareStatement(sql);
			rs = state.executeQuery();
			
			while(rs.next()) {
				bookId.add(rs.getInt("BOOK_ID"));
				bookName.add(rs.getString("BOOK_NAME"));
				bookDate.add(rs.getDate("BOOK_DATE"));
				bookPrice.add(rs.getInt("BOOK_PRICE"));
			}
		connection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	class ComboBoxAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int i  = bookComboBox.getSelectedIndex();
			date = bookDateArray[i].toString();
			price = bookPriceArray[i].toString();
			
			bookDateDBLabel.setText(date);
			bookPriceDBLabel.setText(price);
			
			upPanel.add(bookDateLabel);
			upPanel.add(bookDateDBLabel);
			upPanel.add(bookPriceLabel);
			upPanel.add(bookPriceDBLabel);
			
		}
		
	}
	
	
	class BuyAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int i = bookComboBox.getSelectedIndex();
			String fName = firstNameTextField.getText();
			String lName = lastNameTextField.getText();
			String movieId = null;
			int bookId = bookIdArray[i];
			String sql = "Insert into CUSTOMERS"
					+ " VALUES(NULL,?,?,?,?)";
			connection = DBConnection.getConnection();
			
			try {
				state = connection.prepareStatement(sql);
				state.setString(1, fName);
				state.setString(2, lName);
				state.setString(3, movieId);
				state.setInt(4, bookId);
				state.execute();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			BookOrderClass bookOrder = new BookOrderClass(fName, lName);
			dispose();
		}
		
	}
	
	class CancelAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Home home = new Home();
			dispose();
		}
		
	}
}
