package Family.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import Family.model.person;
import Family.service.Person2Service;
import Family.service.Person3Service;
import Family.service.PersonService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ChieldController implements Initializable {

	@FXML
	private ToggleGroup genderGroup;

	@FXML
	private TableColumn<person, Integer> Idcol;

	@FXML
	private TableColumn<person, Long> MobileNocol;

	@FXML
	private TableColumn<person, String> Namecol;

	@FXML
	private TableColumn<person, Integer> RollNocol;

	@FXML
	private TableColumn<person, String> gendercol;

	@FXML
	private Button Back;

	@FXML
	private Button Resfresh;

	@FXML
	private Button delete;

	@FXML
	private Button okey;

	@FXML
	TableView<person> table;

	@FXML
	private TextField searchTextField;

	@FXML
    private Button button1;
	
	@FXML
    private Button update;


	private Person2Service service2 = new Person2Service();

	public ChieldController(Person2Service service2) {
		this.service2 = service2;
	}
	
	private Person3Service service3 = new Person3Service();
	
	public ChieldController(Person3Service service3) {
		this.service3 = service3;
	}

	private ParentController parent = new ParentController(service3);

	public ChieldController(ParentController parent) {
		this.parent = parent;
	}

	public void setParent(ParentController parent) {
		this.parent = parent;
	}
	PersonService service = new PersonService();
	
	ExamplesController example = new ExamplesController(service);

	public ChieldController(ExamplesController example) {
		this.example = example;
	}

	public TableView<person> setTable(TableView<person> table) {
	return	this.table = table;
	}

	private ObservableList<person> persons = FXCollections.observableArrayList();

	private FilteredList<person> filteredData;

	TextField Name3 = new TextField();
	TextField MobileNo3 = new TextField();
	TextField RollNo4 = new TextField();
	RadioButton male4 = new RadioButton();
	RadioButton female4 = new RadioButton(); 

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		genderGroup = new ToggleGroup();
		male4.setToggleGroup(genderGroup);
		female4.setToggleGroup(genderGroup); 

		Namecol.setCellValueFactory(new PropertyValueFactory<person, String>("Name"));
		MobileNocol.setCellValueFactory(new PropertyValueFactory<person, Long>("MobileNo"));
		RollNocol.setCellValueFactory(new PropertyValueFactory<person, Integer>("RollNo"));
		gendercol.setCellValueFactory(new PropertyValueFactory<person, String>("gender"));
		Idcol.setCellValueFactory(new PropertyValueFactory<person, Integer>("Id"));

		System.out.println("TableView: " + table);
		if (table != null) {
		//	JOptionPane.showMessageDialog(null, "TableView initialized correctly.");

		} else {
			JOptionPane.showMessageDialog(null, "TableView is null.");

		}

		clearData();

		Name3.setOnKeyPressed(this::next);
		MobileNo3.setOnKeyPressed(this::next2);
		RollNo4.setOnKeyPressed(this::next3);
		male4.setOnKeyPressed(arg0 -> {
			try {
				next4(arg0);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		female4.setOnKeyPressed(arg0 -> {
			try {
				next4(arg0);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

		// table.setOnMouseClicked(this::loadUsers);
		refreshData(null);
		// handleRowClick();
		ObservableList<person> persons2 = service2.getAllUsers();
		filteredData = new FilteredList<>(persons2, p -> true);
		table.setItems(filteredData);

		searchTextField.setOnKeyReleased(this::onSearch);
		
		
	}
	
	void next(KeyEvent event) {
		if (event.getCode().toString().equals("ENTER")) {
	if(Name3.getText().isEmpty()) {
				
			}else {
				MobileNo3.requestFocus();
			}
			
		
		}
	}

	void next2(KeyEvent event) {
		if (event.getCode().toString().equals("ENTER")) {
if(MobileNo3.getText().isEmpty()) {
				
			}else {
				RollNo4.requestFocus();
		}
	}
	}

	void next3(KeyEvent event) {
		if (event.getCode().toString().equals("ENTER")) {
			if(RollNo4.getText().isEmpty()) {

				
			}else {
				male4.requestFocus();
		}

		}
	}


	void next4(KeyEvent event) throws IOException {
		if (event.getCode().toString().equals("ENTER")) {
			if(male4.getText().isEmpty() || female4.getText().isEmpty()) {

				
			}else {
				callling(null);
		}
		}
	}

    void waring(MouseEvent event) {
		  if(Name3.getText().isEmpty() ) {
			  showAlert( "Please Enter Name.");

		  }
	    }
	
	    void waring2(MouseEvent event) {
		  if(MobileNo3.getText().isEmpty() ) {
			  showAlert("Please Enter Mobile No.");
		  }

	    }

	    void waring3(MouseEvent event) {
			  if(RollNo4.getText().isEmpty() ) {
				  showAlert("Please Enter Roll No.");

			  }
	    }
	    
		
			

		void clearData() {
			Name3.clear();
			MobileNo3.clear();
			RollNo4.clear();
		}
	 @FXML
	    private Button First = new Button("Show Custom Stage Dialog");
	 @FXML
	    void callling(ActionEvent event) {
		 Stage primaryStage2 = new Stage();

			GridPane gridpane = new GridPane();

			gridpane.setPadding(new Insets(10));

			gridpane.setHgap(10);
			gridpane.setVgap(10);
			
			//gridpane.setMaxHeight(0);

			Label UserName = new Label("UserName : ");
			Label Mobileno = new Label("MobileNo : ");
			Label Rollno = new Label("RollNo : ");
			Label Gender2 = new Label("Gender : ");
			Label Male2 = new Label("      male ");
			Label Female2 = new Label("        Female ");

			gridpane.add(UserName, 0, 0);
			gridpane.add(Mobileno, 0, 1);
			gridpane.add(Rollno, 0, 2);
			gridpane.add(Gender2, 0, 3);
			gridpane.add(Male2, 1, 3);
			gridpane.add(Female2, 1, 4);

			gridpane.add(Name3, 1, 0);
			gridpane.add(MobileNo3, 1, 1);
			gridpane.add(RollNo4, 1, 2);
			gridpane.add(male4, 1, 3);
			gridpane.add(female4, 1, 4);
			
			clearData();
			
			Name3.setOnMousePressed(e ->{
				if(Name3.getText().isEmpty()) {
					showAlert( "Please Enter Name.");
				}
			});
			
			MobileNo3.setOnMousePressed(e ->{
				if(MobileNo3.getText().isEmpty()) {
					showAlert( "Please Enter Mobile No.");
				}
			});
			
			RollNo4.setOnMousePressed(e ->{
				if(RollNo4.getText().isEmpty()) {
					showAlert( "Please Enter Roll No.");
				}
			});


			Button submitButton2 = new Button("Submit");

			gridpane.add(submitButton2, 1, 5);
			 
       
			submitButton2.setOnAction(e -> {

				person selectedPerson = table.getSelectionModel().getSelectedItem();
				if (selectedPerson == null) {
					return;
				}

				String Name = Name3.getText();
				String mobileno = MobileNo3.getText();
				String rollno = RollNo4.getText();
				RadioButton selectedGender = (RadioButton) genderGroup.getSelectedToggle();

				String gender = male4.isSelected() ? "Male" : "Female";
				
				
				if (Name3.getText().isEmpty() || MobileNo3.getText().isEmpty() || RollNo4.getText().isEmpty()) {
					showAlert( "All fields must be filled out.");
					return;
				}

				if (!mobileno.matches("\\d{10}")) {
					showAlert( "Mobile number must be 10 digits.");
					return;
				}

				if (rollno.isEmpty() || !rollno.matches("\\d+")) {
					showAlert( "Roll number must be a valid integer.");

					return;
				}

				String Name4 = Name3.getText();
				if (!Name4.matches("[a-zA-Z ]+")) {
					showAlert( "Name must be a valid string format (letters and spaces only).");
					return;
				}

				if (genderGroup.getSelectedToggle() == null) {
					showAlert( "Please select a gender.");
				} else {
					
					Long MobileNo = Long.parseLong(MobileNo3.getText());
					int RollNo = Integer.parseInt(RollNo4.getText());

					person user = new person(Name, MobileNo, RollNo, gender);
					boolean insertUser = this.service2.insertUser(user);
					
					if (insertUser) {
						showSuccessDialog("Sucessfully Insert.");
						clearData();
						primaryStage2.close();
						refreshData(null);		
			}else {
				showAlert("Not Insert opperation.");

			}
				}
			});

			GridPane.setMargin(UserName, new Insets(5));
			GridPane.setMargin(Mobileno, new Insets(5));
			GridPane.setMargin(Rollno, new Insets(5));
			GridPane.setMargin(Gender2, new Insets(5));
			GridPane.setMargin(Male2, new Insets(5));
			GridPane.setMargin(Female2, new Insets(5));

			GridPane.setMargin(Name3, new Insets(3));
			GridPane.setMargin(MobileNo3, new Insets(3));
			GridPane.setMargin(RollNo4, new Insets(3));
			GridPane.setMargin(male4, new Insets(3));
			GridPane.setMargin(female4, new Insets(3));

			GridPane.setMargin(submitButton2, new Insets(5, 5, 3, 3));

			Scene scene = new Scene(gridpane, 700, 500);
			primaryStage2.setTitle("JavaFX Simple Dialog Example Insert Data");
			primaryStage2.setScene(scene);
			// primaryStage.show();
			primaryStage2.showAndWait();
			
	 }
	


	@FXML
	private Button update2;

	@FXML
	void openDialog() {
		
		
		
//		try {
//			example.pagesData();
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Stage stage = (Stage) Back.getScene().getWindow();
//		stage.close();
	}
	/*	Stage primaryStage = new Stage();

		GridPane gridpane = new GridPane();

		gridpane.setPadding(new Insets(10));

		gridpane.setHgap(10);
		gridpane.setVgap(10);
		
		//gridpane.setMaxHeight(0);

		Label UserName = new Label("UserName : ");
		Label Mobileno = new Label("MobileNo : ");
		Label Rollno = new Label("RollNo : ");
		Label Gender2 = new Label("Gender : ");
		Label Male2 = new Label("      male ");
		Label Female2 = new Label("        Female ");

		gridpane.add(UserName, 0, 0);
		gridpane.add(Mobileno, 0, 1);
		gridpane.add(Rollno, 0, 2);
		gridpane.add(Gender2, 0, 3);
		gridpane.add(Male2, 1, 3);
		gridpane.add(Female2, 1, 4);

		gridpane.add(Name3, 1, 0);
		gridpane.add(MobileNo3, 1, 1);
		gridpane.add(RollNo4, 1, 2);
		gridpane.add(male4, 1, 3);
		gridpane.add(female4, 1, 4);
		
		Name3.setOnMousePressed(e ->{
			if(Name3.getText().isEmpty()) {
				showAlert( "Please Enter Name.");
			}
		});
		
		MobileNo3.setOnMousePressed(e ->{
			if(MobileNo3.getText().isEmpty()) {
				showAlert( "Please Enter Mobile No.");
			}
		});
		
		RollNo4.setOnMousePressed(e ->{
			if(RollNo4.getText().isEmpty()) {
				showAlert( "Please Enter Roll No.");
			}
		});

		Button submitButton = new Button("Submit");

		gridpane.add(submitButton, 1, 5);

		submitButton.setOnAction(e -> {

			person selectedPerson = table.getSelectionModel().getSelectedItem();
			if (selectedPerson == null) {
				return;
			}

			String Name = Name3.getText();
			String mobileno = MobileNo3.getText();
			String rollno = RollNo4.getText();
			String gender = male4.isSelected() ? "Male" : "Female";

			if (Name3.getText().isEmpty() || MobileNo3.getText().isEmpty() || RollNo4.getText().isEmpty()) {
				showAlert("All fields must be filled out.");
				return;
			}

			if (!mobileno.matches("\\d{10}")) {
				showAlert("Mobile number must be 10 digits.");
				return;
			}

			if (rollno.isEmpty() || !rollno.matches("\\d+")) {
				showAlert("Invalid Roll Number Please enter a valid roll number.");

				return;
			}

			String Name4 = Name3.getText();
			if (!Name4.matches("[a-zA-Z ]+")) {
				showAlert("Name must be a valid string format (letters and spaces only).");
				return;
			}

			if (genderGroup.getSelectedToggle() == null) {
				showAlert("Please select a gender.");
			} else {
				long MobileNo = Long.parseLong(mobileno);
				Integer RollNo = Integer.parseInt(rollno);

				selectedPerson.setName(Name);
				selectedPerson.setMobileNo(MobileNo);
				selectedPerson.setRollNo(RollNo);
				selectedPerson.setGender(gender);

				boolean updated = service2.updatePerson(selectedPerson);
				if (updated) {
					showSuccessDialog("Sucessfully update.");
					primaryStage.close();
					refreshData(null);

				} else {
					showAlert("Failed to update person.");

				}
			}
		});

		GridPane.setMargin(UserName, new Insets(5));
		GridPane.setMargin(Mobileno, new Insets(5));
		GridPane.setMargin(Rollno, new Insets(5));
		GridPane.setMargin(Gender2, new Insets(5));
		GridPane.setMargin(Male2, new Insets(5));
		GridPane.setMargin(Female2, new Insets(5));

		GridPane.setMargin(Name3, new Insets(3));
		GridPane.setMargin(MobileNo3, new Insets(3));
		GridPane.setMargin(RollNo4, new Insets(3));
		GridPane.setMargin(male4, new Insets(3));
		GridPane.setMargin(female4, new Insets(3));

		GridPane.setMargin(submitButton, new Insets(5, 5, 3, 3));

		Scene scene = new Scene(gridpane, 700, 500);
		primaryStage.setTitle("JavaFX Simple Dialog Example Updata Data");
		primaryStage.setScene(scene);
		// primaryStage.show();
		primaryStage.showAndWait();
	} */

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
	void refreshData(ActionEvent event) {

		ObservableList<person> persons2 = service2.getAllUsers();
		persons.forEach(user -> System.out.println(user.getName() + " - " + user.getMobileNo() + " - "
				+ user.getRollNo() + " - " + user.getGender() + " - " + user.getId()));
		table.setItems(persons2);
	}

	@FXML
	void handleRowClick(MouseEvent event) {

		person selectedPerson = table.getSelectionModel().getSelectedItem();

		Name3.setText(selectedPerson.getName());
		MobileNo3.setText(String.valueOf(selectedPerson.getMobileNo()));
		RollNo4.setText(String.valueOf(selectedPerson.getRollNo()));

		if ("male4".equals(selectedPerson.getGender())) {
			male4.setSelected(true);
		} else {
			female4.setSelected(true);
		}
		selectedPerson.getId();

		if (event.getClickCount() == 2) {
			openDialog();
		}
	}
	/*
	 * try {
	 * 
	 * FXMLLoader loader = new
	 * FXMLLoader(getClass().getResource("/Family/view/Chield.fxml"));
	 * 
	 * Stage stage = new Stage();
	 * 
	 * loader.setControllerFactory(c -> new ChieldController(service2));
	 * 
	 * Parent root = loader.load(); Scene scene = new Scene(root);
	 * stage.setScene(scene); stage.setResizable(false);
	 * 
	 * // ExamplesController obj =loader.getController(); //
	 * obj.setValues(table.getSelectionModel().getSelectedItem());
	 * 
	 * stage.show();
	 * 
	 * } catch (IOException e) { e.printStackTrace(); } }
	 */

	@FXML
	void onSearch(KeyEvent event) {

		String searchText = searchTextField.getText().toLowerCase();
		filteredData.setPredicate(person -> {
			if (searchText.isEmpty()) {
				return true;
			}

			boolean matchesName = person.getName().toLowerCase().contains(searchText);
			boolean matchesMobile = String.valueOf(person.getMobileNo()).contains(searchText);
			boolean matchesRollNo = String.valueOf(person.getRollNo()).contains(searchText);
			boolean matchesGender = person.getGender().toLowerCase().contains(searchText);

			return matchesName || matchesMobile || matchesRollNo || matchesGender;
		});
	}

	@FXML
	void goBacks(ActionEvent event) {

		Stage stage = (Stage) Back.getScene().getWindow();
		stage.close();
		

	}

	@FXML
	void deleteText(ActionEvent event) {

		person selectedPerson = table.getSelectionModel().getSelectedItem();
		if (selectedPerson == null) {
			JOptionPane.showMessageDialog(null, "Please select a person to delete.");

			return;
		}

		boolean deleted = service2.deletePerson(selectedPerson.getId());
		if (deleted) {
			JOptionPane.showMessageDialog(null, "Person deleted successfully.");
			refreshData(null);
			// page();

		} else {
			JOptionPane.showMessageDialog(null, "Failed to delete person.");
			refreshData(null);

		}
	}

	public void populateData(person selectedPerson) {
		if (selectedPerson != null) {
			Name3.setText(selectedPerson.getName());
			MobileNo3.setText(String.valueOf(selectedPerson.getMobileNo()));
			RollNo4.setText(String.valueOf(selectedPerson.getRollNo()));

			// Set the gender based on the selected person
			if ("Male".equals(selectedPerson.getGender())) {
				male4.setSelected(true);
			} else {
				female4.setSelected(true);
			}
		}
	}

	

}
