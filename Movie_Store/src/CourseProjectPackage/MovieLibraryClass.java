package CourseProjectPackage;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.StreamCorruptedException;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.text.TabExpander;

public class MovieLibraryClass extends JFrame{
	
	Connection connection = null;
	PreparedStatement state = null;
	ResultSet rs = null;
	
	JTabbedPane tabbedPane = new JTabbedPane();
	
	JPanel libraryTab = new JPanel();
	JPanel addTab = new JPanel();
	JPanel updateTab = new JPanel();
	 
	JPanel upPanel = new JPanel();
	JPanel downPanel = new JPanel();
	
	JLabel movieNameDBLabel;
	JLabel movieDateDBLabel;
	JLabel moviePriceDBLabel;
	
	JLabel movieLabel;
	JLabel movieNameLabel;
	JLabel movieDateLabel;
	JLabel moviePriceLabel;
	
	JLabel yearLabel;
	JLabel monthLabel;
	JLabel dayLabel;
	
	JTextField movieNameTextField;
	JTextField moviePriceTextField;

	JButton backButton = new JButton("Back");
	JButton addButton = new JButton("Add");
	JButton updateButton = new JButton("Update");
	JButton deleteButton = new JButton("Delete");
	
	ArrayList<Integer> movieId = new ArrayList<Integer>();
	ArrayList<String> movieName = new ArrayList<String>();
	ArrayList<Date> movieDate = new ArrayList<Date>();
	ArrayList<Integer> moviePrice = new ArrayList<Integer>();
	
	String[] movieNameArray;
	Date[] movieDateArray;
	Integer[] moviePriceArray;
	Integer[] movieIdArray;
	
	int currentNumber;
	int x = 0;
	final int y = 3;
	
	Integer[] dayArray = {1,2,3,4,5,6,7,8,9,10
							,11,12,13,14,15,16,17,18,19
							,20,21,22,23,24,25,26,27,28,29,30,31};
	Integer[] monthArray = {1,2,3,4,5,6,7,8,9,10,11,12};
	Integer[] yearArray = {2020,2019,2018,2017,2016,2015,2014,2013,2012,2011
			,2010,2009,2008,2007,2006,2005,2004,2003,2002,2001
			,2000,1999,1998,1997,1996,1995,1994,1993,1992,1991
			,1990,1989,1989,1987,1986,1985,1984,1983,1982,1981,1980};
	
	JComboBox<Integer> dayComboBox;
	JComboBox<Integer> monthComboBox;
	JComboBox<Integer> yearComboBox;
	
	JComboBox<String> movieNameComboBox;
	
	JTextField nameTextField;
	JTextField dateTextField;
	JTextField priceTextField;
	
	JTable table = new JTable();
    JScrollPane scroller = new JScrollPane(table);
	
	public MovieLibraryClass() {
		this.setTitle("Movie Library");
		this.setVisible(true);
		this.setSize(360, 520);
		this.setLocation(500, 200);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.add(tabbedPane);
		tabbedPane.add(libraryTab, "Library");
		tabbedPane.add(addTab, "Add Movie");
		tabbedPane.add(updateTab, "Update and Delete");
		
		libraryTab.setLayout(new GridLayout(2,1));
		libraryTab.add(upPanel);
		libraryTab.add(downPanel);
		
		//upPanel
		MovieDB();
		movieNameArray = new String[movieName.size()];
		movieNameArray = movieName.toArray(movieNameArray);
		
		movieDateArray = new Date[movieDate.size()];
		movieDateArray = movieDate.toArray(movieDateArray);
		
		moviePriceArray = new Integer[moviePrice.size()];
		moviePriceArray = moviePrice.toArray(moviePriceArray);
		
		movieIdArray = new Integer[movieId.size()];
		movieIdArray = movieId.toArray(movieIdArray);
		
		/*upPanel.setLayout(new GridLayout(x+1,y,0,10));
		
		movieNameDBLabel = new JLabel("Movie Name");
		movieDateDBLabel = new JLabel("Movie Date");
		moviePriceDBLabel = new JLabel("Movie Price");
	
		upPanel.add(movieNameDBLabel);
		upPanel.add(movieDateDBLabel);
		upPanel.add(moviePriceDBLabel);
		
		for(int i=0; i<x; i++) {
			movieNameDBLabel = new JLabel(movieNameArray[i]);
			movieDateDBLabel = new JLabel(movieDateArray[i].toString());
			moviePriceDBLabel = new JLabel(moviePriceArray[i].toString());
		
			upPanel.add(movieNameDBLabel);
			upPanel.add(movieDateDBLabel);
			upPanel.add(moviePriceDBLabel);
		}*/
        table.setVisible(true);
        table.setModel(DBConnection.selectAll("SELECT Movie_Name, Movie_Date, Movie_Price FROM Movies")); //put name
        upPanel.setPreferredSize(new Dimension(600,500));
        upPanel.add(scroller);
        scroller.setPreferredSize(new Dimension(340,450));
		//downPanel
		downPanel.setLayout(new FlowLayout(FlowLayout.CENTER,10,50));
		downPanel.add(backButton);
		
		backButton.addActionListener(new BackAction());
		
		
		addMovie();
		updateMovie();
	
	}
	
	public void MovieDB() {
		String sql = "Select * from MOVIES"
				+ " Order By MOVIE_NAME ASC";
		connection = DBConnection.getConnection();
		
		try {
			state = connection.prepareStatement(sql);
			rs = state.executeQuery();
			while(rs.next()) {
				movieId.add(rs.getInt("MOVIE_ID"));
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
	
	class BackAction implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			LibraryClass library = new LibraryClass();
			dispose();
		}
		
	}
	
	public void addMovie() {
		addTab.setLayout(new FlowLayout(FlowLayout.CENTER,5,10));
		
		movieNameLabel = new JLabel("Movie Name: ");
		movieDateLabel = new JLabel("Movie Date: ");
		moviePriceLabel = new JLabel("Movie Price: ");
		
		yearLabel = new JLabel("year");
		monthLabel = new JLabel("month");
		dayLabel = new JLabel("day");
		
		movieNameTextField = new JTextField(20);
		moviePriceTextField = new JTextField(20);
		
		monthComboBox = new JComboBox<Integer>(monthArray);
		yearComboBox = new JComboBox<Integer>(yearArray);
		dayComboBox = new JComboBox<Integer>(dayArray);
		
		addTab.add(movieNameLabel);
		addTab.add(movieNameTextField);
		addTab.add(movieDateLabel);
		addTab.add(yearLabel);
		addTab.add(yearComboBox);
		addTab.add(monthLabel);
		addTab.add(monthComboBox);
		addTab.add(dayLabel);
		addTab.add(dayComboBox);
		addTab.add(moviePriceLabel);
		addTab.add(moviePriceTextField);
		addTab.add(addButton);
		
		addButton.addActionListener(new AddAction());
		
	}
	
	class AddAction implements ActionListener{

		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			String movieName = movieNameTextField.getText();
			
			int moviePrice = Integer.parseInt(moviePriceTextField.getText());
			
			int year = (int) yearComboBox.getSelectedItem();
			int month = (int) monthComboBox.getSelectedItem();
			int day = (int) dayComboBox.getSelectedItem();
		
			String sql = "Insert into MOVIES "
					+ " Values(null,?,'" + year + "-" + month + "-" + day + "',?);";
			
			connection = DBConnection.getConnection();
			
			try {
				state = connection.prepareStatement(sql);
				state.setString(1, movieName);
				state.setInt(2, moviePrice);
				state.execute();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			JOptionPane.showMessageDialog(null, "You successfully added " + movieName);
			MovieLibraryClass movie = new MovieLibraryClass();
			dispose();
		}
		
	}

	public void updateMovie() {
		
		updateTab.setLayout(new FlowLayout(FlowLayout.CENTER,40,15));

		movieLabel = new JLabel("Movie:");
		movieNameLabel = new JLabel("Movie Name: ");
		movieDateLabel = new JLabel("Movie Date: ");
		moviePriceLabel = new JLabel("Movie Price: ");
		
		
		movieNameComboBox = new JComboBox<String>(movieNameArray);
		int i = movieNameComboBox.getSelectedIndex();
		nameTextField = new JTextField(movieNameArray[i].toString(),15);
		dateTextField = new JTextField(movieDateArray[i].toString(),15);
		priceTextField = new JTextField(moviePriceArray[i].toString(),15);	
		
		updateTab.add(movieLabel);
		updateTab.add(movieNameComboBox);
		updateTab.add(movieNameLabel);
		updateTab.add(nameTextField);
		updateTab.add(movieDateLabel);
		updateTab.add(dateTextField);
		updateTab.add(moviePriceLabel);
		updateTab.add(priceTextField);
		updateTab.add(updateButton);
		updateTab.add(deleteButton);
		
		
		movieNameComboBox.addActionListener(new ComboBoxAction());
		
		updateButton.addActionListener(new UpdateAction());
		deleteButton.addActionListener(new DeleteAction());
	}
	
	class ComboBoxAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			// TODO Auto-generated method stub
			int i = movieNameComboBox.getSelectedIndex();
			nameTextField.setText(movieNameArray[i].toString());
			dateTextField.setText(movieDateArray[i].toString());
			priceTextField.setText(moviePriceArray[i].toString());
			
			updateTab.add(movieLabel);
			updateTab.add(movieNameComboBox);
			updateTab.add(movieNameLabel);
			updateTab.add(nameTextField);
			updateTab.add(movieDateLabel);
			updateTab.add(dateTextField);
			updateTab.add(moviePriceLabel);
			updateTab.add(priceTextField);
			updateTab.add(updateButton);
			updateTab.add(deleteButton);
		}
		
	}
	
	class UpdateAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int i = movieNameComboBox.getSelectedIndex();
			int id = movieIdArray[i];
			String name = nameTextField.getText();
			String date = dateTextField.getText();
			int price = Integer.parseInt(priceTextField.getText());
			
			String sql = "Update Movies "
					+ " Set Movie_Name = ?, Movie_Date = '" + date +"', Movie_Price = ?"
					+ " Where Movie_Id = ?;";
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
			
			JOptionPane.showMessageDialog(null, "You successfully updated " + name);
			MovieLibraryClass movie = new MovieLibraryClass();
			dispose();
		}
		
	}
	
	class DeleteAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int i = movieNameComboBox.getSelectedIndex();
			int id = movieIdArray[i];
			
			String sql;
			connection = DBConnection.getConnection();
			
			sql = "Delete from Customers "
					+ " Where MOVIE_ID = ?";
			
			try {
				state = connection.prepareStatement(sql);
				state.setInt(1, id);
				state.execute();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			sql = "Delete from Movies "
					+ " Where MOVIE_ID =?";
			
			try {
				state = connection.prepareStatement(sql);
				state.setInt(1, id);
				state.execute();
				connection.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "You successfully deleted!");
			MovieLibraryClass movie = new MovieLibraryClass();
			dispose();
		}
		
	}
}
