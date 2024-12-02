package Family.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import Family.model.person;
import Family.model.student;
import Family.service.Person2Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class StudentInfoController implements Initializable {

	@FXML
	private TableColumn<person, Integer> Idcol;

	@FXML
	private TableColumn<person, Long> MobileNocol;

	@FXML
	private TableColumn<person, String> Namecol;

	@FXML
	private TableColumn<person, Integer> RollNocol;
	
	@FXML
	private Button addNew;

	@FXML
	private Button delete;

	@FXML
	private TableColumn<person, String> gendercol;

	@FXML
	private Label label;

	@FXML
	private Button resfresh;

	@FXML
	private Button save;

	@FXML
	private TextField searchTextField;

	@FXML
	private TableView<person> studentTableView;
		
	Person2Service service = new Person2Service();


	private ObservableList<person> persons = FXCollections.observableArrayList();

	private FilteredList<person> filteredData;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		Namecol.setCellValueFactory(new PropertyValueFactory<person, String>("Name"));
		MobileNocol.setCellValueFactory(new PropertyValueFactory<person, Long>("MobileNo"));
		RollNocol.setCellValueFactory(new PropertyValueFactory<person, Integer>("RollNo"));
		gendercol.setCellValueFactory(new PropertyValueFactory<person, String>("gender"));
		Idcol.setCellValueFactory(new PropertyValueFactory<person, Integer>("Id"));

		
		onRefresh();
		
		ObservableList<person> persons2 = onRefresh();
		filteredData = new FilteredList<>(persons2, p -> true);
		studentTableView.setItems(filteredData);

		searchTextField.setOnKeyReleased(this::onSearch);
	}

	@FXML
	void handleRowClick(MouseEvent event) {
		if (event.getClickCount() == 2) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/Family/view/StudentRegister.fxml"));
				Stage stage = new Stage();
				

				stage.setScene(new Scene((Parent) loader.load()));
				stage.setResizable(false);
				StudentRegisterController obj = loader.<StudentRegisterController>getController();
				obj.setValues(studentTableView.getSelectionModel().getSelectedItem());

			//	obj.setValues(personobj);
				stage.show();
				

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@FXML
	void onAddNew(ActionEvent event) {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/Family/view/StudentRegister.fxml"));

			Stage stage = new Stage();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	@FXML
	void onDelete(ActionEvent event) {
		
		person selectedPerson = studentTableView.getSelectionModel().getSelectedItem();
		if (selectedPerson == null) {
			JOptionPane.showMessageDialog(null, "Please select a person to delete.");

			return;
		}
		
		boolean deleted = service.deletePerson(selectedPerson.getId());
		if (deleted) {
			JOptionPane.showMessageDialog(null, "Person deleted successfully.");
			onRefresh();

		} else {
			JOptionPane.showMessageDialog(null, "Failed to delete person.");
			onRefresh();

		}

	}

	@FXML
	ObservableList<person> onRefresh() {
		persons = service.getAllUsers();

		persons.forEach(user -> System.out.println(user.getName() + " - " + user.getMobileNo() + " - "
				+ user.getRollNo() + " - " + user.getGender() + " - " + user.getId() ));
		studentTableView.setItems(persons);
		return persons;

		}

	@FXML
	void onSave() {
		

	}

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

}
