package CourseProjectPackage;

import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.Flow;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MovieOrderClass extends JFrame {
	
	
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

	JLabel firstNameDBLabel = new JLabel();
	JLabel lastNameDBLabel = new JLabel();
	JLabel movieNameDBLabel = new JLabel();
	JLabel movieDateDBLabel = new JLabel();
	JLabel moviePriceDBLabel = new JLabel();
	
	String firstName;
	String lastName;
	String movie;
	String date;
	String price;
	
	JButton backButton = new JButton("Back");
	JButton menuButton = new JButton("Menu");

	public MovieOrderClass(String name, String surname) {
		this.setTitle("Movie order");
		this.setVisible(true);
		this.setLocation(500, 300);
		this.setSize(400, 300);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(new GridLayout(2,1));
		this.add(upPanel);
		this.add(downPanel);	
		
		CheckData(name,surname);
		
		firstNameDBLabel.setText(firstName);
		lastNameDBLabel.setText(lastName);
		movieNameDBLabel.setText(movie);
		movieDateDBLabel.setText(date);
		moviePriceDBLabel.setText(price);
		
		//upPanel
		upPanel.setLayout(new GridLayout(5,2));
		upPanel.add(firstNameLabel);
		upPanel.add(firstNameDBLabel);
		upPanel.add(lastNameLabel);
		upPanel.add(lastNameDBLabel);
		upPanel.add(movieNameLabel);
		upPanel.add(movieNameDBLabel);
		upPanel.add(movieDateLabel);
		upPanel.add(movieDateDBLabel);
		upPanel.add(moviePriceLabel);
		upPanel.add(moviePriceDBLabel);
		
		//downPanel
		downPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 50));
		downPanel.add(backButton);
		downPanel.add(menuButton);
		
		backButton.addActionListener(new BackAction());
		menuButton.addActionListener(new MenuAction());

		
	}
	
	public void CheckData(String fName, String lName) {
		String sql = "SELECT c.FIRSTNAME, c.LASTNAME, c.MOVIE_ID, m.MOVIE_NAME, m.MOVIE_DATE, m.MOVIE_PRICE FROM CUSTOMERS c"
				+ " INNER JOIN MOVIES m ON c.MOVIE_ID = m.MOVIE_ID"
				+ " WHERE c.FIRSTNAME = '" + fName + "' AND c.LASTNAME = '" + lName + "';";
		connection = DBConnection.getConnection();
		
		
		try {
			state = connection.prepareStatement(sql);
			rs = state.executeQuery();
			while(rs.next()) {
				firstName = rs.getString("FIRSTNAME");
				lastName = rs.getString("LASTNAME");
				movie = rs.getString("MOVIE_NAME");
				date  = rs.getDate("MOVIE_DATE").toString();
				price = rs.getString("MOVIE_PRICE");
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
			BuyMovieClass buyMoive = new BuyMovieClass();
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
