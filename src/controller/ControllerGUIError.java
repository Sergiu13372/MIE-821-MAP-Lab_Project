package controller;

import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class ControllerGUIError {
	ControllerGUI controller = new ControllerGUI();
	ControllerGUIAddCar controllerAddCar = new ControllerGUIAddCar();
	
    @FXML
    private Button errorOkButton;
    @FXML
    private Label textError;
    
    @FXML
    public void errorButton(ActionEvent event){
    	Stage stage = (Stage) errorOkButton.getScene().getWindow();
        stage.close();
    }
    
    public void initialize(ControllerGUI givenController, String error) {
    	controller = givenController;
    	textError.setText(error);
    }
    public void initialize(ControllerGUIAddCar givenController, String error) {
    	controllerAddCar = givenController;
    	textError.setText(error);
    }
}