package CourseProjectPackage;

import java.awt.Dimension;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class BookLibraryClass extends JFrame{
	
	Connection connection = null;
	PreparedStatement state = null;
	ResultSet rs = null;
	
	JTabbedPane tabbedPane = new JTabbedPane();

	JPanel libraryTab = new JPanel();
	JPanel addTab = new JPanel();
	JPanel updateTab = new JPanel();
	JPanel upPanel = new JPanel();
	JPanel downPanel = new JPanel();
	
	JLabel bookLabel;
	JLabel bookNameLabel;
	JLabel bookDateLabel;
	JLabel bookPriceLabel;
	JLabel yearLabel;
	JLabel monthLabel;
	JLabel dayLabel;

	JTextField bNameTextField;
	JTextField bPriceTextField;
	JTextField bookNameTextField;
	JTextField bookDateTextField;
	JTextField bookPriceTextField;

	JButton backButton = new JButton("Back");
	JButton addButton = new JButton("Add");
	JButton updateButton = new JButton("Update");
	JButton deleteButton = new JButton("Delete");
	
	ArrayList<Integer> bookId = new ArrayList<Integer>();
	ArrayList<String> bookName = new ArrayList<String>();
	ArrayList<Date> bookDate = new ArrayList<Date>();
	ArrayList<Integer> bookPrice = new ArrayList<Integer>();
	
	String[] bookNameArray;
	Date[] bookDateArray;
	Integer[] bookPriceArray;
	
	int x = 0;
	final int y = 3;
	
	Integer[] bookIdArray;
	Integer[] dayArray = {1,2,3,4,5,6,7,8,9,10
							,11,12,13,14,15,16,17,18,19,20
							,21,22,23,24,25,26,27,28,29,30,31};
	Integer[] monthArray = {1,2,3,4,5,6,7,8,9,10,11,12};
	Integer[] yearArray = {2020,2019,2018,2017,2016,2015,2014,2013,2012,2011
							,2010,2009,2008,2007,2006,2005,2004,2003,2002,2001
							,2000,1999,1998,1997,1996,1995,1994,1993,1992,1991
							,1990,1989,1989,1987,1986,1985,1984,1983,1982,1981,1980};
	
	JComboBox<Integer> dayComboBox = new JComboBox<Integer>(dayArray);
	JComboBox<Integer> monthCoboBox = new JComboBox<Integer>(monthArray);
	JComboBox<Integer> yearComboBox = new JComboBox<Integer>(yearArray);
	JComboBox<String> bookNameComboBox;

	JTable table = new JTable();
    JScrollPane scroller = new JScrollPane(table);
    
	public BookLibraryClass() {
		this.setTitle("Book Library");
		this.add(tabbedPane);
		this.setVisible(true);
		this.setSize(380, 470);
		this.setLocation(500, 200);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		tabbedPane.add(libraryTab, "Library");
		tabbedPane.add(addTab, "Add Book");
		tabbedPane.add(updateTab, "Update and Delete");
		
		libraryTab.setLayout(new GridLayout(2,1));
		
		libraryTab.add(upPanel);
		libraryTab.add(downPanel);
		
		//upPanel
		BookDB();
		
		bookIdArray = new Integer[bookId.size()];
		bookIdArray = bookId.toArray(bookIdArray);
		
		bookNameArray = new String[bookName.size()];
		bookNameArray = bookName.toArray(bookNameArray);
		
		bookDateArray = new Date[bookDate.size()];
		bookDateArray = bookDate.toArray(bookDateArray);
		
		bookPriceArray = new Integer[bookPrice.size()];
		bookPriceArray = bookPrice.toArray(bookPriceArray);
		
		/*upPanel.setLayout(new GridLayout(x+1,y));
		bookNameLabel = new JLabel("Book name");
		bookDateLabel = new JLabel("Book date");
		bookPriceLabel = new JLabel("Book price");
		
		upPanel.add(bookNameLabel);
		upPanel.add(bookDateLabel);
		upPanel.add(bookPriceLabel);
		
		for(int i=0; i<x; i++) {
			bookNameLabel = new JLabel(bookNameArray[i]);
			bookDateLabel = new JLabel(bookDateArray[i].toString());
			bookPriceLabel = new JLabel(bookPriceArray[i].toString());
			
			upPanel.add(bookNameLabel);
			upPanel.add(bookDateLabel);
			upPanel.add(bookPriceLabel);
		}*/
		
		table.setVisible(true);
        table.setModel(DBConnection.selectAll("SELECT BOOK_NAME, BOOK_DATE, BOOK_PRICE FROM BOOKS")); //put name
        upPanel.setPreferredSize(new Dimension(600,500));
        upPanel.add(scroller);
        scroller.setPreferredSize(new Dimension(340,450));
		
		//downPanel
		downPanel.setLayout(new FlowLayout(FlowLayout.CENTER,10,50));
		downPanel.add(backButton);
		
		backButton.addActionListener(new BackAction());
		
		addBook();
		updateBook();
		
	}
	
	public void BookDB() {

		String sql = "Select * from BOOKS "
				+ " Order by Book_Name ASC";
		connection = DBConnection.getConnection();
		
		try {
			state = connection.prepareStatement(sql);
			rs = state.executeQuery();
			while(rs.next()) {
				bookId.add(rs.getInt("BOOK_ID"));
				bookName.add(rs.getString("BOOK_NAME"));
				bookDate.add(rs.getDate("BOOK_DATE"));
				bookPrice.add(rs.getInt("BOOK_PRICE"));
				x++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	class BackAction implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			LibraryClass library = new LibraryClass();
			dispose();
		}
		
	}

	public void addBook() {
		 
		addTab.setLayout(new FlowLayout(FlowLayout.CENTER,11, 10));
		bookNameLabel = new JLabel("Book Name: ");
		bookDateLabel = new JLabel("Book Date: ");
		bookPriceLabel = new JLabel("Book Price: ");
		yearLabel = new JLabel("year");
		monthLabel = new JLabel("month");
		dayLabel = new JLabel("day");
		
		bNameTextField = new JTextField(20);
		bPriceTextField = new JTextField(20);
		
		addTab.add(bookNameLabel);
		addTab.add(bNameTextField);
		addTab.add(bookDateLabel);
		addTab.add(yearLabel);
		addTab.add(yearComboBox);
		addTab.add(monthLabel);
		addTab.add(monthCoboBox);
		addTab.add(dayLabel);
		addTab.add(dayComboBox);
		addTab.add(bookPriceLabel);
		addTab.add(bPriceTextField);
		addTab.add(addButton);
				
		addButton.addActionListener(new AddAction());
	}
	
	class AddAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String name = bNameTextField.getText();
			int year = (int) yearComboBox.getSelectedItem();
			int month = (int) monthCoboBox.getSelectedItem();
			int day = (int) dayComboBox.getSelectedItem();
			int bookPrice = Integer.parseInt(bPriceTextField.getText());
			
			String sql = "Insert into BOOKS "
					+ " Values(null,?,'" + year + "-" + month + "-" + day +"',?)";
			connection = DBConnection.getConnection();
			
			try {
				state = connection.prepareStatement(sql);
				state.setString(1, name);
				state.setInt(2, bookPrice);
				state.execute();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "You successfully added "+ name);
			BookLibraryClass book = new BookLibraryClass();
			dispose();
		}
		
	}

	public void updateBook() {
		
		updateTab.setLayout(new FlowLayout(FlowLayout.CENTER,40,10));
		
		bookLabel = new JLabel("Book: ");
		bookNameLabel = new JLabel("Book Name: ");
		bookDateLabel = new JLabel("Book Date: ");
		bookPriceLabel = new JLabel("Book Price: ");
		
		bookNameComboBox = new JComboBox<String>(bookNameArray);
		int i = bookNameComboBox.getSelectedIndex();
		bookNameTextField = new JTextField(bookNameArray[i],20);
		bookDateTextField = new JTextField(bookDateArray[i].toString(),20);
		bookPriceTextField = new JTextField(bookPriceArray[i].toString(),20);
		
		updateTab.add(bookLabel);
		updateTab.add(bookNameComboBox);
		updateTab.add(bookNameLabel);
		updateTab.add(bookNameTextField);
		updateTab.add(bookDateLabel);
		updateTab.add(bookDateTextField);
		updateTab.add(bookPriceLabel);
		updateTab.add(bookPriceTextField);
		updateTab.add(updateButton);
		updateTab.add(deleteButton);
		
		bookNameComboBox.addActionListener(new ComboBoxAction());
		updateButton.addActionListener(new UpdateAction());
		deleteButton.addActionListener(new DeleteAction());
		
	}
	
	class ComboBoxAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int i = bookNameComboBox.getSelectedIndex();
			bookNameTextField.setText(bookNameArray[i]);
			bookDateTextField.setText(bookDateArray[i].toString());
			bookPriceTextField.setText(bookPriceArray[i].toString());
			
			updateTab.add(bookNameLabel);
			updateTab.add(bookNameTextField);
			updateTab.add(bookDateLabel);
			updateTab.add(bookDateTextField);
			updateTab.add(bookPriceLabel);
			updateTab.add(bookPriceTextField);
			updateTab.add(updateButton);
			updateTab.add(deleteButton);
		}
		
	}
	
	class UpdateAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int i = bookNameComboBox.getSelectedIndex();
			int id = bookIdArray[i];
			String name = bookNameTextField.getText();
			String date = bookDateTextField.getText();
			int price = Integer.parseInt(bookPriceTextField.getText());
			
			String sql = "Update BOOKS "
					+ " Set Book_Name = ?, Book_Date = '" + date +"', Book_Price = ?"
					+ " Where Book_Id = ?";
			connection = DBConnection.getConnection();
			
			try {
				state = connection.prepareStatement(sql);
				state.setString(1, name);
				state.setInt(2, price);
				state.setInt(3, id);
				state.execute();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "You successfully update " + name);
			BookLibraryClass book = new BookLibraryClass();
			dispose();
		}
		
	}
	
	class DeleteAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int i = bookNameComboBox.getSelectedIndex();
			int id = bookIdArray[i];
			String name = bookNameComboBox.getSelectedItem().toString();
			
			connection = DBConnection.getConnection();
			String sql = "Delete from Customers "
					+ " Where Book_ID = ?";
			
			try {
				state = connection.prepareStatement(sql);
				state.setInt(1, id);
				state.execute();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			
			sql = "Delete from BOOKS "
					+ " Where Book_Id = ?";
			try {
				state = connection.prepareStatement(sql);
				state.setInt(1, id);
				state.execute();
				connection.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "You successfully deleted " + name);
			BookLibraryClass book = new BookLibraryClass();
			dispose();
		}
		
	}
	
}
