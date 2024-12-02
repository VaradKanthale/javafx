package Family.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import Family.Main;
import Family.model.person;
import Family.service.Person2Service;
import Family.service.PersonService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class ExamplesController implements Initializable {

	@FXML
	private Button Add5;

	@FXML
	private Button Clear2;

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
	private Button okey;

	@FXML
	private TextField MobileNo2;

	 @FXML
	 private Button go;
	 
	   @FXML
	    private TextField Id;
	/*
	 * int index = -1; String query = null; PreparedStatement statement = null;
	 * ResultSet set = null; Connection conn = null;
	 */

	Main main = new Main();
	PersonService service = new PersonService();

	public ExamplesController(PersonService service) {
		this.service = service;
	}
	
	private ChieldController chieldController;

	public ExamplesController(ChieldController chieldController) {
		this.chieldController = chieldController;
	}

	public void setChieldController(ChieldController chieldController) {
		this.chieldController = chieldController;
	}

	private ObservableList<person> personData = FXCollections.observableArrayList();
	


	@Override
	public void initialize(URL url, ResourceBundle rb) {

		genderGroup = new ToggleGroup();
		male.setToggleGroup(genderGroup);
		female.setToggleGroup(genderGroup);

		// this.Add5.setOnAction(event -> insertData());

		// System.out.println(service);
		
		clearData();

		Name2.setOnKeyPressed(this::next);
		MobileNo2.setOnKeyPressed(this::next2);
		RollNo3.setOnKeyPressed(this::next3);
		male.setOnKeyPressed(arg0 -> {
			try {
				next4(arg0);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		female.setOnKeyPressed(arg0 -> {
			try {
				next4(arg0);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
				
	}
	

	public void setValues(person p) {

		System.out.println(p);
	}
	TableView<person>  table;
	@FXML
	void pagesData() throws IOException {
		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Family/view/Examples.fxml"));
			
			Stage stage = new Stage();

			    loader.setControllerFactory(c -> new ExamplesController(service));

				stage.setScene(new Scene((Parent) loader.load()));
				stage.setResizable(false);
			    ChieldController chieldController = loader.getController();

			     table = chieldController.setTable(table);

	   //     stage.setScene(new Scene(root));

					stage.show();


		} catch (IOException e) {
			e.printStackTrace();
		}


	}

	@FXML
	void next(KeyEvent event) {
		if (event.getCode().toString().equals("ENTER")) {
	if(Name2.getText().isEmpty()) {
				
			}else {
			MobileNo2.requestFocus();
			}
			
		
		}
	}

	@FXML
	void next2(KeyEvent event) {
		if (event.getCode().toString().equals("ENTER")) {
if(MobileNo2.getText().isEmpty()) {
				
			}else {
			RollNo3.requestFocus();
		}
	}
	}

	@FXML
	void next3(KeyEvent event) {
		if (event.getCode().toString().equals("ENTER")) {
			if(RollNo3.getText().isEmpty()) {

				
			}else {
				male.requestFocus();
		}

		}
	}


	@FXML
	void next4(KeyEvent event) throws IOException {
		if (event.getCode().toString().equals("ENTER")) {
			if(male.getText().isEmpty() || female.getText().isEmpty()) {

				
			}else {
				updateData();
		}
		}
	}

	@FXML
    void waring(MouseEvent event) {
		  if(Name2.getText().isEmpty() ) {
				JOptionPane.showMessageDialog(null, "Please Enter Name.");

		  }
	    }
	
	 @FXML
	    void waring2(MouseEvent event) {
		  if(MobileNo2.getText().isEmpty() ) {
				JOptionPane.showMessageDialog(null, "Please Enter Mobile No.");
		  }

	    }

	    @FXML
	    void waring3(MouseEvent event) {
			  if(RollNo3.getText().isEmpty() ) {
					JOptionPane.showMessageDialog(null, "Please Enter Roll No.");

			  }
	    }

	    @FXML
	    void goBack(ActionEvent event) {
	    	Stage stage = new Stage();
	    	try {
				main.start(stage);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

	    @FXML
	    void updateData() throws IOException {
	    	
	    	person selectedPerson =table.getSelectionModel().getSelectedItem();
			if (selectedPerson == null) {
				return;
			}
			
		//    Id.setText(String.valueOf(selectedPerson.getId()));
			Name2.setText(selectedPerson.getName());
			MobileNo2.setText(String.valueOf(selectedPerson.getMobileNo()));
			RollNo3.setText(String.valueOf(selectedPerson.getRollNo()));
			male.setText(String.valueOf(selectedPerson.getGender()));
			female.setText(String.valueOf(selectedPerson.getGender()));



	    	String Name = Name2.getText();
			String mobileno = MobileNo2.getText();
			String rollno = RollNo3.getText();
			String gender = male.isSelected() ? "Male" : "Female"; 

			if (Name2.getText().isEmpty() || MobileNo2.getText().isEmpty() || RollNo3.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null,"All fields must be filled out.");
				return;
			}

			if (!mobileno.matches("\\d{10}")) {
				JOptionPane.showMessageDialog(null,"Mobile number must be 10 digits.");
				return;
			}

			if (rollno.isEmpty() || !rollno.matches("\\d+")) {
				JOptionPane.showMessageDialog(null,"Invalid Roll Number Please enter a valid roll number.");

				return;
			}
		

			String Name4 = Name2.getText();
			if (!Name4.matches("[a-zA-Z ]+")) {
				JOptionPane.showMessageDialog(null,"Name must be a valid string format (letters and spaces only).");
				return;
			}

			if (genderGroup.getSelectedToggle() == null) {
				JOptionPane.showMessageDialog(null,"Please select a gender.");
			} else {
				long MobileNo = Long.parseLong(mobileno);
				int RollNo = Integer.parseInt(rollno);
				
				selectedPerson.setName(Name);
				selectedPerson.setMobileNo(MobileNo);
				selectedPerson.setRollNo(RollNo);
				selectedPerson.setGender(gender);
				
			//	person Person = new person(Name ,MobileNo , RollNo , gender , Id);

				boolean updated = service.updatePerson(selectedPerson);
				if (updated) {
					JOptionPane.showMessageDialog(null,"Sucessfully update.");
					goBack(null);					

				} else {
					JOptionPane.showMessageDialog(null,"Failed to update person.");

				}
			}
		}

	@FXML
	void clearData() {
		Name2.clear();
		MobileNo2.clear();
		RollNo3.clear();
	}

}
