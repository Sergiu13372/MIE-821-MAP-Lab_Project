package controller;

import cars.Car;

import javafx.event.*;
import javafx.fxml.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import controller.ControllerGUI;

import java.math.*;

public class ControllerGUIAddCar {
	ControllerGUI controller = new ControllerGUI();
	
    @FXML
    private Button addCarButton;
    @FXML
    private TextField fieldID, fieldManufacturer, fieldModel,  fieldSpeed, fieldPrice, fieldYear;
    
    @FXML
    public void addCarButton(ActionEvent event){
        int ID = 0;
		String manufacturer = "";
		String model = "";
		int speed = 0;
		float price = 0f;
		String year = "";
		boolean rented = false;
		String rented_date = "n/a";
		
		boolean good = true;
        
        try {
            ID = Integer.parseInt(fieldID.getText());
            if(controller.repo.checkID(ID)) {
            	good = false;
            	fieldID.setText("");
            	fieldID.setPromptText("Used ID");
            }
        }
        catch(NumberFormatException e){
        	good = false;
        	fieldID.setText("");
        	fieldID.setPromptText("Error");
        }
        manufacturer = fieldManufacturer.getText();
        model = fieldModel.getText();
        try {
        	speed = Integer.parseInt(fieldSpeed.getText());
        }
        catch(NumberFormatException e){
        	good = false;
        	fieldSpeed.setText("");
        	fieldSpeed.setPromptText("Error");
        }
        try {
        	price = Float.parseFloat(fieldPrice.getText());
        }
        catch(NumberFormatException e){
        	good = false;
        	fieldPrice.setText("");
        	fieldPrice.setPromptText("Error");
        }
        year = fieldYear.getText();
        
        if(good) {
            Car newCar = new Car(ID, manufacturer, model, speed, price, year, rented, rented_date);
            controller.addCarObj(newCar);
            
            Stage stage = (Stage) addCarButton.getScene().getWindow();
            stage.close();
        }
    }
    
    public void initialize(ControllerGUI givenController) {
    	controller = givenController;
    }
}