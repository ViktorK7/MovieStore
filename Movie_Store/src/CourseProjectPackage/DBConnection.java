package CourseProjectPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {

	private static Connection connection = null;
	
	public static Connection getConnection() {
		
		String path = "";

        for(String s : IOUtil.readFileByRow("D:\\Programming\\Java-Eclipse\\CourseProject\\CourseProject.txt")) {
            path += s;
        }
		try {
			Class.forName("org.h2.Driver");
			connection = DriverManager.getConnection("jdbc:h2:"+path+";AUTO_SERVER=TRUE","sa","sa");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return connection;
	}
	
	public static MyModel selectAll(String sql){
        //String sql = "SELECT * FROM " + nameTable;
        connection = getConnection();
        MyModel model = null;
        ResultSet result = null;

        try {
            PreparedStatement state = connection.prepareStatement(sql);
            result = state.executeQuery();
            model = new MyModel(result);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return model;

    }//end SelectAll
}
