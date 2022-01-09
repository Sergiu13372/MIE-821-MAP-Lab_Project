package controller;

import cars.Car;

import javafx.event.*;
import javafx.fxml.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import controller.ControllerGUI;

import java.io.File;
import java.math.*;

public class ControllerGUIAddCar {
	ControllerGUI controller = new ControllerGUI();
	
    @FXML
    private Button addCarButton;
    @FXML
    private TextField fieldID, fieldManufacturer, fieldModel,  fieldSpeed, fieldPrice, fieldYear;
    boolean isUpdate = false;
    @FXML
    public void checkBoxFunc(ActionEvent event){
    	if(isUpdate) {
    		addCarButton.setText("Add Car");
    		isUpdate = false;
    	}
    	else {
    		addCarButton.setText("Update");
    		isUpdate = true;
    	}
    }
    
    public void popUpError(String errorMessage) {
    	try {
    	    FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/error.fxml"));
    	    Parent root2 = (Parent) loader.load();
    	    Stage stage = new Stage();
    	    
    	    ControllerGUIError controllerGUIError = loader.getController();
    	    controllerGUIError.initialize(this, errorMessage);

	        stage.getIcons().add(new Image(new File("src/resources/warning.png").toURI().toString()));
    	    stage.setTitle("Error");
			stage.setResizable(false);
    	    stage.setScene(new Scene(root2));
    	    stage.show();
    	    
    	  } catch(Exception ee) {
    	    System.out.println(ee + " Can not open " + errorMessage);
    	  }
    }
    
    @FXML
    public void addCarButton(ActionEvent event){
        int ID = -1;
		String manufacturer = "";
		String model = "";
		int speed = 0;
		float price = 0f;
		String year = "";
		boolean rented = false;
		String rented_date = "n/a";
		
		boolean good = true;
		
		String errors = "";
        
        try {
            ID = Integer.parseInt(fieldID.getText());
            if(isUpdate == false) {
	            if(controller.repo.checkID(ID)) {
	            	good = false;
	            	fieldID.setText("");
	            	fieldID.setPromptText("Used ID");
	            	errors += "Used ID\n";
	            }
            }
            else {
            	if(!controller.repo.checkID(ID)) {
	            	good = false;
	            	fieldID.setText("");
	            	fieldID.setPromptText("ID not found");
	            	errors += "ID not found\n";
	            }
            }
        }
        catch(NumberFormatException e){
        	good = false;
        	fieldID.setText("");
        	fieldID.setPromptText("Error");
        	errors += "Write integer in ID\n";
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
        	errors += "Write float in Speed\n";
        }
        try {
        	price = Float.parseFloat(fieldPrice.getText());
        }
        catch(NumberFormatException e){
        	good = false;
        	fieldPrice.setText("");
        	fieldPrice.setPromptText("Error");
        	errors += "Write float in Price\n";
        }
        year = fieldYear.getText();
        
        if(good) {
            Car newCar = new Car(ID, manufacturer, model, speed, price, year, rented, rented_date);
            if(isUpdate == false)
            	controller.addCarObj(newCar);
            else {
            	controller.repo.update(newCar, ID);
            }
            
            Stage stage = (Stage) addCarButton.getScene().getWindow();
            stage.close();
        }
        if(errors != "") {
        	popUpError(errors);
        }
    }
    
    public void initialize(ControllerGUI givenController) {
    	controller = givenController;
    }
}