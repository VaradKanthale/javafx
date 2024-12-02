package Family.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import Family.model.person;
import Family.mysqlconnect.Mydb;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Person2Service {

	public ObservableList<person> getAllUsers() {

		ObservableList<person> users = FXCollections.observableArrayList();

		String query = "SELECT * FROM person";

		try (Connection conn = Mydb.connect();
				
				
				Statement statement = conn.createStatement();
				ResultSet resultSet = statement.executeQuery(query)) {

			while (resultSet.next()) {

				String Name = resultSet.getString("Name");
				Long MobileNo = resultSet.getLong("MobileNo");
				int RollNo = resultSet.getInt("RollNo");
				String gender = resultSet.getString("gender");
				int Id = resultSet.getInt("Id");

				users.add(new person(Name, MobileNo, RollNo, gender, Id));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	public boolean deletePerson(int Id) {
			
		String query = "DELETE FROM person WHERE ID = ?";

		try (Connection conn = Mydb.connect(); 
				PreparedStatement stmt = conn.prepareStatement(query)) {
			// Set the ID parameter
			stmt.setInt(1, Id);

			// Execute the DELETE statement
			int rowsDeleted = stmt.executeUpdate();

			return true; // Returns true if a row was deleted
		} catch (SQLException e) {
			e.printStackTrace();

			return false;

		}
	}

	public ObservableList<person> searchPersons(String searchText) {
		ObservableList<person> userList = FXCollections.observableArrayList();

		try (Connection conn = Mydb.connect();) {
			String query = "SELECT * FROM person WHERE Name LIKE ? ";

			try (PreparedStatement stmt = conn.prepareStatement(query)) {
				stmt.setString(1, "%" + searchText + "%");
			
				try (ResultSet resultSet = stmt.executeQuery()) {
					while (resultSet.next()) {
						String Name = resultSet.getString("Name");
						Long MobileNo = Long.parseLong(resultSet.getString("MobileNo"));
						int RollNo = Integer.parseInt(resultSet.getString("RollNo"));
						String gender = resultSet.getString("Name");
						int Id = Integer.parseInt(resultSet.getString("Id"));

						userList.add(new person(Name, MobileNo, RollNo, gender, Id));
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}
	

}
