package Family.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Family.service.Person3Service;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ParentController implements Initializable {

	 @FXML
	 private Button Page;
	 
	    @FXML
	    private Button open;

	    @FXML
	    private Pane pane;
	    
	    @FXML
	    private Button Close;
	    
	    @FXML
	    private Button Screen1;
	    
	    @FXML
	    private Pane pane2;
	    
	    @FXML
	    private Button Screen2;

	  
	private Person3Service service3 = new Person3Service();
	
	public ParentController(Person3Service service3) {
		this.service3 = service3;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		 pane.setVisible(false);
		 pane2.setVisible(false);
		 Screen2.setVisible(false);

	}	

    @FXML
    void sreenOn2(ActionEvent event) {
    	Screen2.setVisible(true);

    }

	
	 @FXML
	    void sreenOn(ActionEvent event) {
		 pane2.setVisible(true);
	    }

	
	 @FXML
	    void pages() throws IOException {
		 try {

				FXMLLoader loader = new FXMLLoader(getClass().getResource("/Family/view/Parent.fxml"));
				
				Stage stage = new Stage();

				loader.setControllerFactory(c -> new ParentController(service3));

				Parent root = loader.load();
				Scene scene = new Scene(root , 950 , 650);
				stage.setScene(scene);
				stage.setResizable(false);

				stage.show();
		}catch (IOException e) {
			e.printStackTrace();
		}
	 }
	 
	 @FXML
	    void show2(ActionEvent event) {
           pane.setVisible(false);
  		 pane2.setVisible(false);

	    }
	 
	 @FXML
	    void show(ActionEvent event) {
		 pane.setVisible(true);
		
	    }
	

}
