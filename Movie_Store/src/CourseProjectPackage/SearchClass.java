package CourseProjectPackage;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class SearchClass extends JFrame{
	
	Connection connection = null;
	PreparedStatement state = null;
	ResultSet rs = null;
	
	JTabbedPane tabbedPane = new JTabbedPane();
	JPanel searchTab = new JPanel();
	JPanel customerTab = new JPanel();
	JPanel moviePanel = new JPanel();
	JPanel bookPanel = new JPanel();
	JPanel choosePanel = new JPanel();
	JPanel searchPanel = new JPanel();
	JPanel buttonPanel = new JPanel();
	
	JLabel checkBoxLabel = new JLabel("Search method: ");
	JLabel chooseTableLabel = new JLabel("Choose Table: ");
	JLabel firstLabel = new JLabel("First Name: ");
	JLabel secondLabel = new JLabel();

	JLabel firstNameLabel;
	JLabel lastNameLabel;
	JLabel productNameLabel;
	JLabel productDateLabel;
	JLabel productPriceLabel;
		
	JTextField firstTextField = new JTextField(20);
	JTextField secondTextField = new JTextField(20);
	
	JCheckBox oneTableCheckBox = new JCheckBox("One table");
	JCheckBox twoTableCheckBox = new JCheckBox("Two table");
	JCheckBox movieCheckBox = new JCheckBox("Movies");
	JCheckBox bookCheckBox = new JCheckBox("Books");
	
	ArrayList<String> firstName;
	ArrayList<String> lastName;
	ArrayList<String> productName;
	ArrayList<Date> productDate;
	ArrayList<Integer> productPrice;
	
	String[] firstNameArray;
	String[] lastNameArray;
	String[] productNameArray;
	Date[] productDateArray;
	Integer[] productPriceArray;

	JButton searchButton = new JButton("Search");
	JButton cancelButton = new JButton("Cancel");
	
	boolean check = false;
	
	int x=0;
	final int y = 5;
	
	public SearchClass() {
		this.setTitle("Search");
		this.setVisible(true);
		this.setSize(450, 300);
		this.setLocation(500,300);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.add(tabbedPane);
		tabbedPane.add(searchTab, "Search");
		tabbedPane.add(customerTab, "Customer");
		
		tabForSearch();
		tabForCustomer();
	}
	
	public void tabForSearch() {
		searchTab.setLayout(new GridLayout(3,1,10,15));
		searchTab.add(choosePanel);
		searchTab.add(searchPanel);
		searchTab.add(buttonPanel);
		
		//choosePanel
		choosePanel.setLayout(new FlowLayout(FlowLayout.CENTER,50,10));
		choosePanel.add(checkBoxLabel);
		choosePanel.add(oneTableCheckBox);
		choosePanel.add(twoTableCheckBox);
		choosePanel.add(chooseTableLabel);
		choosePanel.add(movieCheckBox);
		choosePanel.add(bookCheckBox);
		
		chooseTableLabel.setVisible(false);
		movieCheckBox.setVisible(false);
		bookCheckBox.setVisible(false);
		
		oneTableCheckBox.setSelected(true);
		
		//searchPanel
		secondLabel = new JLabel("Last Name: ");
		searchPanel.setLayout(new FlowLayout(FlowLayout.CENTER,40,5));
		searchPanel.add(firstLabel);
		searchPanel.add(firstTextField);
		searchPanel.add(secondLabel);
		searchPanel.add(secondTextField);
		
		//buttonPanel
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));
		buttonPanel.add(searchButton);
		buttonPanel.add(cancelButton);
		
		oneTableCheckBox.addActionListener(new OneTableAction());
		twoTableCheckBox.addActionListener(new TwoTableAction());
		searchButton.addActionListener(new SearchAction());
		cancelButton.addActionListener(new CancelAction());
		
	}
	
	class OneTableAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(oneTableCheckBox.isSelected() == true) {
				twoTableCheckBox.setSelected(false);
				
				chooseTableLabel.setVisible(false);
				movieCheckBox.setVisible(false);
				bookCheckBox.setVisible(false);
				
				firstLabel.setVisible(true);
				firstTextField.setVisible(true);
				secondLabel.setVisible(true);
				secondTextField.setVisible(true);
				
				secondLabel.setText("Last Name: ");
				searchPanel.add(secondLabel);
				searchPanel.add(secondTextField);
			}
			else {
				if(twoTableCheckBox.isSelected() == false) {
					
					chooseTableLabel.setVisible(false);
					movieCheckBox.setVisible(false);
					bookCheckBox.setVisible(false);
					
					firstLabel.setVisible(false);
					firstTextField.setVisible(false);
					secondLabel.setVisible(false);
					secondTextField.setVisible(false);
				}
			}
		}
	}
	
	class TwoTableAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(twoTableCheckBox.isSelected() == true) {
				oneTableCheckBox.setSelected(false);
				
				chooseTableLabel.setVisible(true);
				movieCheckBox.setVisible(true);
				bookCheckBox.setVisible(true);
				
				firstLabel.setVisible(true);
				firstTextField.setVisible(true);
				secondLabel.setVisible(true);
				secondTextField.setVisible(true);
				
				movieCheckBox.setSelected(true);
				bookCheckBox.setSelected(false);
				
				secondLabel.setText("Movie Name:");
				searchPanel.add(secondLabel);
				searchPanel.add(secondTextField);
				
				movieCheckBox.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						if(movieCheckBox.isSelected() == true) {
							bookCheckBox.setSelected(false);
							secondLabel.setText("Movie Name:");
							searchPanel.add(secondLabel);
							searchPanel.add(secondTextField);
						}
					}
				});
				bookCheckBox.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						if(bookCheckBox.isSelected() == true) {
							movieCheckBox.setSelected(false);
							secondLabel.setText("Book Name:");
							searchPanel.add(secondLabel);
							searchPanel.add(secondTextField);
						}
					}
				});
			}
			else {
				if(oneTableCheckBox.isSelected() == false) {
					
					chooseTableLabel.setVisible(false);
					movieCheckBox.setVisible(false);
					bookCheckBox.setVisible(false);
					
					firstLabel.setVisible(false);
					firstTextField.setVisible(false);
					secondLabel.setVisible(false);
					secondTextField.setVisible(false);
				}
			}
		}
		
	}
	
	class SearchAction implements ActionListener{
		String name;
		String surname;
		String movieName;
		String bookName;
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(oneTableCheckBox.isSelected() == true) {
				name = firstTextField.getText();
				surname = secondTextField.getText();
				movieName = null;
				bookName = null;
				
				CheckForCustomer(name, surname, movieName, bookName);
				if(check) {
					ResultFromSearchClass result = new ResultFromSearchClass(name, surname);
					dispose();
				}
				else
				{
					JOptionPane.showMessageDialog(null, name + " " + surname + " isn't in database! \n"
															+ " Insert another person.");
				}
				
			}
			else {
				if(movieCheckBox.isSelected() == true) {
					name = firstTextField.getText();
					surname =  null;
					movieName = secondTextField.getText();
					bookName = null;
					
					CheckForCustomer(name, surname, movieName, bookName);
					if(check) {
						TwoTableSearchClass twoTablesearch = new TwoTableSearchClass(name, movieName, bookName);
						dispose();
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Don't have people: " + name + "\n"
																+ " which buy movie: " + movieName);
					}
				}
				else {
					name = firstTextField.getText();
					surname =  null;
					movieName = null;
					bookName = secondTextField.getText();
					
					CheckForCustomer(name, surname, movieName, bookName);
					if(check) {
						TwoTableSearchClass twoTablesearch = new TwoTableSearchClass(name, movieName, bookName);
						dispose();
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Don't have people: " + name + "\n"
																+ " which buy book: " + bookName);
					}
				}
			}
		}
		
	}
	
	public void CheckForCustomer(String name, String surname, String movieName, String bookName) {
		String sql;
		
		if(movieName != null) {
			sql = "Select c.FirstName, c.LastName, m.Movie_Name, m.Movie_Date, m.Movie_Price from Customers c"
					+ " Inner join Movies m ON c.Movie_Id = m.Movie_Id "
					+ " Where c.FirstName = '" + name +"' and m.Movie_Name = '" + movieName +"';";
		}
		else if(bookName != null) {
			sql = "Select c.FirstName, c.LastName, b.Book_Name, b.Book_Date, b.Book_Price from Customers c"
					+ " Inner join Books b ON c.Book_Id = b.Book_Id "
					+ " Where c.FirstName = '" + name +"' and b.Book_Name = '" + bookName +"';";
		}
		else {
			sql = "Select * from customers"
					+ " Where FirstName = '" + name + "' AND Lastname = '" + surname + "';";
		}
		
		
		connection = DBConnection.getConnection();
		
		try {
			state = connection.prepareStatement(sql);
			rs = state.executeQuery();
			while(rs.next()) {
				check = true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	class CancelAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub			
			Home home = new Home();
			dispose();
		}	
	}

	public void tabForCustomer() {
		customerTab.setLayout(new GridLayout(2,1));
		customerTab.add(moviePanel);
		customerTab.add(bookPanel);
		
		customerWithMovie();
		//moviePanel
		moviePanel.setLayout(new GridLayout(x+1,y,5,5));
		moviePanel.setBorder(BorderFactory.createLoweredBevelBorder());
		
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
		productNameLabel = new JLabel("Movie Name");
		productDateLabel = new JLabel("Movie Date");
		productPriceLabel = new JLabel("Movie Price");
		
		moviePanel.add(firstNameLabel);
		moviePanel.add(lastNameLabel);
		moviePanel.add(productNameLabel);
		moviePanel.add(productDateLabel);
		moviePanel.add(productPriceLabel);
		for(int i=0; i<x; i++) {
			firstNameLabel = new JLabel(firstNameArray[i]);
			lastNameLabel = new JLabel(lastNameArray[i]);
			productNameLabel = new JLabel(productNameArray[i]);
			productDateLabel = new JLabel(productDateArray[i].toString());
			productPriceLabel = new JLabel(productPriceArray[i].toString());
			
			moviePanel.add(firstNameLabel);
			moviePanel.add(lastNameLabel);
			moviePanel.add(productNameLabel);
			moviePanel.add(productDateLabel);
			moviePanel.add(productPriceLabel);
		}
		
		customerWithBook();
		//bookPane
		bookPanel.setLayout(new GridLayout(x+1,y,5,5));
		bookPanel.setBorder(BorderFactory.createLoweredBevelBorder());
	
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
		productNameLabel = new JLabel("Book Name");
		productDateLabel = new JLabel("Book Date");
		productPriceLabel = new JLabel("Book Price");
		
		bookPanel.add(firstNameLabel);
		bookPanel.add(lastNameLabel);
		bookPanel.add(productNameLabel);
		bookPanel.add(productDateLabel);
		bookPanel.add(productPriceLabel);
		for(int i=0; i<x; i++) {
			firstNameLabel = new JLabel(firstNameArray[i]);
			lastNameLabel = new JLabel(lastNameArray[i]);
			productNameLabel = new JLabel(productNameArray[i]);
			productDateLabel = new JLabel(productDateArray[i].toString());
			productPriceLabel = new JLabel(productPriceArray[i].toString());
			
			bookPanel.add(firstNameLabel);
			bookPanel.add(lastNameLabel);
			bookPanel.add(productNameLabel);
			bookPanel.add(productDateLabel);
			bookPanel.add(productPriceLabel);
		}
	}
	
	public void customerWithMovie() {
		
		firstName = new ArrayList<String>();
		lastName = new ArrayList<String>();
		productName = new ArrayList<String>();
		productDate = new ArrayList<Date>();
		productPrice = new ArrayList<Integer>();
		
		String sql = "Select c.FirstName, c.LastName, m.Movie_Name, m.Movie_Date, m.Movie_Price from Customers c"
				+ " Inner join Movies m ON c.Movie_Id = m.Movie_Id "
				+ " Order By c.FirstName";
		connection = DBConnection.getConnection();
		
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
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void customerWithBook() { 
		x = 0;

		firstName = new ArrayList<String>();
		lastName = new ArrayList<String>();
		productName = new ArrayList<String>();
		productDate = new ArrayList<Date>();
		productPrice = new ArrayList<Integer>();
		
		String sql = "Select c.FirstName, c.LastName, b.Book_Name, b.Book_Date, b.Book_Price from Customers c"
				+ " Inner join Books b ON c.Book_Id = b.Book_Id "
				+ " Order By c.FirstName";
		connection = DBConnection.getConnection();
		
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
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
