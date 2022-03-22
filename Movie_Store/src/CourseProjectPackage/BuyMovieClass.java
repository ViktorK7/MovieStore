package CourseProjectPackage;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
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
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class BuyMovieClass extends JFrame{
	
	Connection connection = null;
	PreparedStatement state = null;
	ResultSet rs = null;
	
	
	JPanel upPanel = new JPanel();
	JPanel downPanel = new JPanel();

	JLabel firstNameLabel = new JLabel("First Name:");
	JLabel lastNameLabel = new JLabel("Last Name:");
	JLabel movieNameLabel = new JLabel("Movie Name:");
	JLabel movieDateLabel = new JLabel("Movie Date:");
	JLabel moviePriceLabel = new JLabel("Movie Price:");	

	JLabel movieDateDBLabel = new JLabel();
	JLabel moviePriceDBLabel = new JLabel();
	
	JTextField firstNameTextField = new JTextField();
	JTextField lastNameTextField = new JTextField();
	
	JButton buyButton = new JButton("Buy");
	JButton cancelButton = new JButton("Cancel ");
	
	ArrayList<Integer> movieId = new ArrayList<Integer>();
	ArrayList<String> movieName = new ArrayList<String>();
	ArrayList<Date> movieDate = new ArrayList<Date>();
	ArrayList<Integer> moviePrice = new ArrayList<Integer>();
	
	Integer[] movieIdArray;
	String movieNameArray[];
	Date movieDateArray[];
	Integer moviePriceArray[];
	
	String date;
	String price;
	
	JComboBox<String> movieNameComboBox;
	
	
	public BuyMovieClass() {
		this.setTitle("Buy movie");
		this.setVisible(true);
		this.setSize(400,300);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocation(500, 300);
		this.setLayout(new GridLayout(2,1));
		this.add(upPanel);
		this.add(downPanel);
		
		CheckData();
		
		movieIdArray = new Integer[movieId.size()];
		movieIdArray = movieId.toArray(movieIdArray);
		
		movieNameArray = new String[movieName.size()];
		movieNameArray = movieName.toArray(movieNameArray);
		
		movieDateArray = new Date[movieDate.size()];
		movieDateArray = movieDate.toArray(movieDateArray);
		
		moviePriceArray = new Integer[moviePrice.size()];
		moviePriceArray = moviePrice.toArray(moviePriceArray);
		
		movieNameComboBox = new JComboBox<String>(movieNameArray);

		int i = movieNameComboBox.getSelectedIndex();
		date =  movieDateArray[i].toString();
		price = moviePriceArray[i].toString();
		
		movieDateDBLabel.setText(date);
		moviePriceDBLabel.setText(price);
		
		//upPane
		upPanel.setLayout(new GridLayout(5,2));
		upPanel.add(firstNameLabel);
		upPanel.add(firstNameTextField);
		upPanel.add(lastNameLabel);
		upPanel.add(lastNameTextField);
		upPanel.add(movieNameLabel);
		upPanel.add(movieNameComboBox);
		upPanel.add(movieDateLabel);
		upPanel.add(movieDateDBLabel);
		upPanel.add(moviePriceLabel);
		upPanel.add(moviePriceDBLabel);
		
		//downPanel
		downPanel.setLayout(new FlowLayout(FlowLayout.CENTER,10,50));
		downPanel.add(buyButton);
		downPanel.add(cancelButton);
		
		movieNameComboBox.addActionListener(new ComboBoxAction());
		buyButton.addActionListener(new BuyAction());
		cancelButton.addActionListener(new CancelAction());
	}
	
	public void CheckData() {
		String sql = "SELECT * FROM MOVIES"
				+ " Order by Movie_Name";
		connection = DBConnection.getConnection();
		
		try {
			state = connection.prepareStatement(sql);
			rs = state.executeQuery();
			
			while(rs.next()) {
				movieId.add(rs.getInt("MOVIE_ID"));
				movieName.add(rs.getString("MOVIE_NAME"));
				movieDate.add(rs.getDate("MOVIE_DATE"));
				moviePrice.add(rs.getInt("MOVIE_PRICE"));
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
			int i = movieNameComboBox.getSelectedIndex();
			date =  movieDateArray[i].toString();
			price = moviePriceArray[i].toString();
			
			movieDateDBLabel.setText(date);
			moviePriceDBLabel.setText(price);

			
			upPanel.add(movieDateLabel);
			upPanel.add(movieDateDBLabel);
			upPanel.add(moviePriceLabel);
			upPanel.add(moviePriceDBLabel);
		}
		
	}
	
	class BuyAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int i = movieNameComboBox.getSelectedIndex();
			String fName = firstNameTextField.getText();
			String lName = lastNameTextField.getText();
			int movieID = movieIdArray[i];
			String bookID = null;
			String sql = "Insert into Customers values(null,?,?,?,?)";
			connection = DBConnection.getConnection();
			
			try {
				state = connection.prepareStatement(sql);
				state.setString(1, fName);
				state.setString(2, lName);
				state.setInt(3, movieID);
				state.setString(4, bookID);
				state.execute();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			MovieOrderClass movieOrder = new MovieOrderClass(fName,lName);
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
