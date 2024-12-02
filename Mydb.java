package Family.mysqlconnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import Family.model.person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Mydb {

	public static Connection connect() {

		try {
			// Change the URL according to your database
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/scencebuilder", "root",
					"manager");
			return conn;

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "Connection closed");
			return null;
		}

	}

}