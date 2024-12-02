package Family.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Family.model.person;
import Family.mysqlconnect.Mydb;

public class PersonService {

	public boolean updatePerson(person person) {

		String query = "UPDATE person SET Name = ?, MobileNo = ?, RollNo = ?, gender = ? WHERE Id = ?";
		try (Connection conn = Mydb.connect(); PreparedStatement statement = conn.prepareStatement(query)) {

			statement.setString(1, person.getName());
			statement.setLong(2, person.getMobileNo());
			statement.setInt(3, person.getRollNo());
			statement.setString(4, person.getGender());
			statement.setInt(5, person.getId());

			return statement.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	public boolean insertUser(String name, long mobile, int rollNo, String gender) {
		String query = "INSERT INTO person (Name, MobileNo, RollNo, gender) VALUES (?, ?, ?, ?)";

		try (Connection conn = Mydb.connect(); 
				PreparedStatement statement = conn.prepareStatement(query)) {

			statement.setString(1, name );
			statement.setLong(2, mobile);
			statement.setInt(3, rollNo);
			statement.setString(4, gender);

			int rowsInserted = statement.executeUpdate();
			
			if (rowsInserted > 0) {
				System.out.println("Insert");
				// Get the generated ID
				JOptionPane.showMessageDialog(null, "Person saved successfully!");
				return true; // Return true on success

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
