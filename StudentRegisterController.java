package Family.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import Family.model.person;
import Family.mysqlconnect.Mydb;
import Family.service.PersonService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class StudentRegisterController implements Initializable {

    @FXML
    private AnchorPane Anchor;

    @FXML
    private TextField MobileNo2;

    @FXML
    private TextField Name2;

    @FXML
    private TextField RollNo3;
    
    @FXML
    private RadioButton female;

    @FXML
    private ToggleGroup genderGroup;

    @FXML
    private RadioButton male;

    @FXML
    private Button save;
    
    @FXML
    private Button update;
    
    @FXML
    private Button clear;
    
    // create servicde class object here
    
    PersonService service2 = new PersonService();
    
	String Name;
	String mobileno;
	String rollno;
	String gender;
	RadioButton selectedGender;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		genderGroup = new ToggleGroup();
		male.setToggleGroup(genderGroup);
		female.setToggleGroup(genderGroup); 
		
		Name = Name2.getText();
		mobileno = MobileNo2.getText();
		rollno =RollNo3.getText();
		male.getText();
		female.getText();
		
		 gender = male.isSelected() ? male.getText() : female.getText();
		
		 selectedGender = (RadioButton) genderGroup.getSelectedToggle();

		
		
		listers();
		
	}
    
    public void listers() {
    	
    	Name2.setOnKeyPressed(event -> {
             if (event.getCode() == KeyCode.ENTER  ) {
            	 if (Name2.getText().isEmpty()) {
 					showAlert( "Please Enter Name.");
                 } else {
         		MobileNo2.requestFocus();
                 }
             }
         });
    	
    	MobileNo2.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
            	if (MobileNo2.getText().isEmpty()) {
					showAlert( "Please Enter Mobile No.");
                 } else {
                 	RollNo3.requestFocus();
                 }
            }
        });
    	RollNo3.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
            	if (RollNo3.getText().isEmpty()) {
					showAlert( "Please Enter Roll No.");
                 } else {
                 	male.requestFocus();
                 }
            }
        });
    	male.setOnKeyPressed(event -> {
            // Check if the Enter key is pressed
            if (event.getCode() == KeyCode.ENTER) {
            	onSave(null);
            }
        });
    	
    }
    int Id;
    public void setValues(person person) {
    	    	
    Name2.setText(person.getName());
    MobileNo2.setText(String.valueOf(person.getMobileNo()));
    RollNo3.setText(String.valueOf(person.getRollNo()));
    
    if(person.getGender() == "male" ) {
        male.setText(person.getGender());
    }else if(person.getGender() == "female"){
    female.setText(person.getGender());
    }
    if ("Male".equals(person.getGender())) {
		male.setSelected(true);
	} else {
		female.setSelected(true);
	}
    Id= person.getId();
    
    

    }

 
    
    @FXML
    void onSave(ActionEvent event) {


		 gender = male.isSelected() ? "Male" : "Female";
		
		
		if (Name2.getText().isEmpty() || MobileNo2.getText().isEmpty() || RollNo3.getText().isEmpty()) {
			showAlert("All fields must be filled out.");
			return;
		}

		String mobileno = MobileNo2.getText();
		if (!mobileno .matches("\\d{10}")) {
			showAlert("Mobile number must be 10 digits.");
			return;
		}
		String rollno= RollNo3.getText();
		if (rollno.isEmpty() || !rollno.matches("\\d+")) {
			showAlert("Invalid Roll Number Please enter a valid roll number.");

			return;
		}

		String Name4 = Name2.getText();
		if (!Name4.matches("[a-zA-Z ]+")) {
			showAlert("Name must be a valid string format (letters and spaces only).");
			return;
		}

		if (genderGroup.getSelectedToggle() == null) {
			showAlert("Please select a gender.");
		} else {
		
			boolean insertUser = service2.insertUser( Name,  Long.parseLong(mobileno), Integer.parseInt(rollno),gender );
			
			if (insertUser) {
				showSuccessDialog("Sucessfully Insert.");
				 clearData(null);
		            Stage stage = (Stage) update.getScene().getWindow();
		    		stage.close();
	}else {
		showAlert("Not Insert opperation.");
	}
			}
		}
    

    @FXML
    void onUpdate(ActionEvent event) {

		
		 gender = male.isSelected() ? male.getText() : female.getText();

		if (Name2.getText().isEmpty() || MobileNo2.getText().isEmpty() || RollNo3.getText().isEmpty()) {
			showAlert("All fields must be filled out.");
			return;
		}
		String mobileno = MobileNo2.getText();
		if (!mobileno.matches("\\d{10}")) {
			showAlert("Mobile number must be 10 digits.");
			return;
		}
		String rollno= RollNo3.getText();
		if (rollno.isEmpty() || !rollno.matches("\\d+")) {
			showAlert("Invalid Roll Number Please enter a valid roll number.");

			return;
		}

		String Name4 = Name2.getText();
		if (!Name4.matches("[a-zA-Z ]+")) {
			showAlert("Name must be a valid string format (letters and spaces only).");
			return;
		}

		if (genderGroup.getSelectedToggle() == null) {
			showAlert("Please select a gender.");
		} else {
			Long MobileNo = Long.parseLong(mobileno);			
			Integer RollNo = Integer.parseInt(rollno);
			
                  person person = new person();
                  person.setName(Name);
                  person.setMobileNo(MobileNo);
                  person.setRollNo(RollNo);
                  person.setGender(gender);
                  person.setId(Id);
                  
              	boolean updated = service2.updatePerson(person);
				if (updated) {
					showSuccessDialog("Sucessfully update.");
					 clearData(null);
			            Stage stage = (Stage) update.getScene().getWindow();
			    		stage.close();
				} else {
					showAlert("Failed to update person.");

				}
		
		}

    }
private void showAlert(String message) {
	Alert alert = new Alert(Alert.AlertType.ERROR);
	alert.setTitle("Error");
	alert.setHeaderText(null);
	alert.setContentText(message);
	alert.showAndWait();
}

private void showSuccessDialog(String message) {
	Alert alert = new Alert(Alert.AlertType.INFORMATION);
	alert.setTitle("Success");
	alert.setHeaderText(null);
	alert.setContentText(message);
	alert.showAndWait();
}
@FXML
void clearData(ActionEvent event) {
	Name2.clear();
	MobileNo2.clear();
	RollNo3.clear();
	male.getToggleGroup().selectToggle(null);
	female.getToggleGroup().selectToggle(null);


}
	

}
