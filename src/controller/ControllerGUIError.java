package controller;

import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class ControllerGUIError {
	ControllerGUI controller = new ControllerGUI();
	
    @FXML
    private Button errorOkButton;
    @FXML
    private Text textError;
    
    @FXML
    public void errorButton(ActionEvent event){
    	Stage stage = (Stage) errorOkButton.getScene().getWindow();
        stage.close();
    }
    
    public void initialize(ControllerGUI givenController, String error) {
    	controller = givenController;
    	textError.setText(error);
    }
}