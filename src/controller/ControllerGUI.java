package controller;

import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.*;
import javafx.util.Duration;

import java.util.*;
import cars.Car;
import repository.*;

import java.io.File;
import java.math.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ControllerGUI {
	String options[] = { "1 . Remove car", "2 . Show car by id", "3 . Show cars", "4 . Print cars from specific Manufacturer",
						"5 . Print cars newer than X year", "6 . Print cars older than X year", "7 . Print prices for specific car model", 
						"8 . Print years of cars from specific Manufacturer", "9 . Print sum of cars from Manufacturer", "10 . Print all Manufacturers", "11 . Print cheapest Car"};
	
	int choice = 0, ID = -1;
	CarsRepositoryFile repo;
	private boolean isRentedDate = false;
	
	@FXML
	private ComboBox<String> optionsBox, optionsBoxRented;
    @FXML
    private Button addCarButton, searchButton, rentCarButton;
    @FXML
    private TextField mainTextField;
    @FXML
    private TextArea textArea, textAreaRent;
    @FXML
    private DatePicker datePickerRent;
    @FXML
    private CheckBox rentedCarsCheckBox;
    @FXML
    private Text rentedCarsText;
    @FXML
    private Tooltip toolTipCheckBox;
    
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
    public void comboBoxSelect(ActionEvent event) {
    	try {
	    	String option = optionsBox.getValue().split(" .")[0];
	    	choice = Integer.parseInt(option);
	    	
	    	switch(choice) {
	    	case 1:
		    	mainTextField.setText("");
	        	mainTextField.setDisable(false);
	        	mainTextField.setPromptText("Write the ID of the car");
	    		break;
	    	case 2:
		    	mainTextField.setText("");
	        	mainTextField.setDisable(false);
	        	mainTextField.setPromptText("Write the ID of the car");
	    		break;
	    	case 3:
		    	mainTextField.setText("");
	        	mainTextField.setDisable(true);
	        	mainTextField.setPromptText("No need to write anything here");
	    		break;
	    	case 4:
		    	mainTextField.setText("");
	        	mainTextField.setDisable(false);
	        	mainTextField.setPromptText("Write the Manufacturer");
	    		break;
	    	case 5:
	        	mainTextField.setDisable(false);
	        	mainTextField.setPromptText("Write the Year");
	    		break;
	    	case 6:
	        	mainTextField.setDisable(false);
	        	mainTextField.setPromptText("Write the Year");
	    		break;
	    	case 7:
		    	mainTextField.setText("");
	        	mainTextField.setDisable(false);
	        	mainTextField.setPromptText("Write the model of the car");
	    		break;
	    	case 8:
		    	mainTextField.setText("");
	        	mainTextField.setDisable(false);
	        	mainTextField.setPromptText("Write the Manufacturer");
	    		break;
	    	case 9:
		    	mainTextField.setText("");
	        	mainTextField.setDisable(false);
	        	mainTextField.setPromptText("Write the Manufacturer");
	        	// call show cars
	    		break;
	    	case 10:
		    	mainTextField.setText("");
	        	mainTextField.setDisable(true);
	        	mainTextField.setPromptText("No need to write anything here");
	        	// call show cars
	    		break;
	    	case 11:
		    	mainTextField.setText("");
	        	mainTextField.setDisable(true);
	        	mainTextField.setPromptText("No need to write anything here");
	        	// call show cars
	    		break;
	    	}
    	} catch(Exception e) {
    		System.out.println(e + " Forcefully Closed");
    	}
    }
    
    @FXML
    public void comboBoxRentedSelect(ActionEvent event) {
    	String stringID = "";
    	
    	try {
	    	stringID = optionsBoxRented.getValue().split(": ")[1].split(",")[0];
	    	ID = Integer.parseInt(stringID);
    	} catch(Exception e) {
    		System.out.println(e + " Forcefully Closed");
    	}
    	
    	System.out.println("ComboBoxRented opened with id:" + stringID);
    }
    
    @FXML
    public void search(ActionEvent event){
    	try {
    		int ID = -1;
    		int year = -1;
    		String manufacturer = "";
	    	switch(choice) {
	    	case 1:
	        	//remove car
	    		ID = -1;
	    		try {
	    			ID = Integer.parseInt(mainTextField.getText());
	    			mainTextField.setPromptText("Write the ID of the car");
	    		}catch(Exception e) {
	    			mainTextField.setPromptText("Not valid ID");
	    			popUpError("Not a valid ID");
	    		}
	    		this.removeCar(ID);
	    		mainTextField.setText("");
	    		break;
	    	case 2:
	        	//show car
	    		ID = -1;
	    		try {
		    		if(repo.checkID(ID) == false)
						mainTextField.setPromptText("Not valid ID");
	    			ID = Integer.parseInt(mainTextField.getText());
	    			mainTextField.setPromptText("Write the ID of the car");
	    		}catch(Exception e) {
	    			mainTextField.setPromptText("Not valid ID");
	    			popUpError("Not a valid ID");
	    		}
	    		this.printCarByID(ID);
	    		mainTextField.setText("");
	    		break;
	    	case 3:
	        	//show cars
	    		this.printCars();
	    		break;
	    	case 4:
	        	//print cars from manufacturer
	    		manufacturer = mainTextField.getText();
	    		if(manufacturer != "")
	    			this.printCarsFromManufacturer(manufacturer);
	    		else {
	    			mainTextField.setPromptText("Write the Manufacturer please");
    				popUpError("Write the Manufacturer please");
    				}
	    		mainTextField.setText("");
	    		break;
	    	case 5:
	        	//print cars newer than
	    		year = -1;
	    		try {
	    			year = Integer.parseInt(mainTextField.getText());
	    			this.printCarsNewerOrOlderThan(year, true);
	    		}catch(Exception e) {
	    			mainTextField.setPromptText("Write a year please");
    				popUpError("Write a year please");
	    			
	    		}
	    		//mainTextField.setText("");
	    		break;
	    	case 6:
	        	//print cars older than
	    		year = -1;
	    		try {
	    			year = Integer.parseInt(mainTextField.getText());
	    			this.printCarsNewerOrOlderThan(year, false);
	    		}catch(Exception e) {
	    			mainTextField.setPromptText("Write a year please");
    				popUpError("Write a year please");
	    		}
	    		//mainTextField.setText("");
	    		break;
	    	case 7:
	        	//print prices for specific model
	    		String model = mainTextField.getText();
	    		if(model != "")
	    			this.printPricesForSpecificModels(model);
	    		else {
	    			mainTextField.setPromptText("Write the Manufacturer please");
					popUpError("Write a year please");
					}
	    		mainTextField.setText("");
	    		break;
	    	case 8:
	    		//print years of cars from specific manufacturer
	    		manufacturer = mainTextField.getText();
	    		if(manufacturer != "")
	    			this.printYearsFromManufacturer(manufacturer);
	    		else {
	    			mainTextField.setPromptText("Write the Manufacturer please");
					popUpError("Write the Manufacturer please");
					}
	    		mainTextField.setText("");
	    		break;
	    	case 9:
	    		//print sum of cars from specific manufacturer
	    		manufacturer = mainTextField.getText();
	    		if(manufacturer != "")
	    			this.printSumFromManufacturer(manufacturer);
	    		else {
	    			mainTextField.setPromptText("Write the Manufacturer please");
	    			popUpError("Write the Manufacturer please");
					}
	    		mainTextField.setText("");
	    		break;
	    	case 10:
	        	//print all manufacturers
	    		this.printManufacturers();
	    		break;
	    	case 11:
	        	//print cheapest car
	    		this.printCheapestCar();
	    		break;
	    	}
    	} catch(Exception e) {
    		System.out.println(e + " Forcefully Closed");
    	}
    }
    
    @FXML
    public void addCar(ActionEvent event){
    	try {
    	    FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/addCar.fxml"));
    	    Parent root1 = (Parent) loader.load();
    	    Stage stage = new Stage();
    	    
			ControllerGUIAddCar controllerGUIAddCar = loader.getController();
			controllerGUIAddCar.initialize(this);

	        stage.getIcons().add(new Image(new File("src/resources/logo.png").toURI().toString()));
    	    stage.setTitle("Car");
			stage.setResizable(false);
    	    stage.setScene(new Scene(root1));
    	    stage.show();
    	    
    	  } catch(Exception e) {
    	    System.out.println(e + " Can not open Add Car");
    	  }
    }
    
    @FXML
    public void rentCar(ActionEvent event) {
    	Car car;
    	try {
    		car = repo.findById(ID);
	    	LocalDate date = datePickerRent.getValue();
	    	try {
		    	if(date.isBefore(LocalDate.now())) {
		    		datePickerRent.setValue(null);
		    		popUpError("Date is in the past");
		    		return;
		    	}
	    	}
	    	catch(Exception e) {
	    		System.out.println("Wrong date chosen");
	    	}
	    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/M/yyyy");
	    	String strDate = date.format(formatter);
	    	
	    	car.setRented(true);
	    	car.setRented_date(strDate);
	    	repo.update(car, ID);
	    	
	    	initializeUnrentedCars(repo);
    	}
    	catch(Exception e) {
    		popUpError("Please choose a Car");
    	}	
    	
    	ID = -1;
    }

    public void checkBoxPressed(ActionEvent event) {
    	if(optionsBoxRented.isDisabled() == false) {
    		datePickerRent.setValue(null);
    		textAreaRent.setText("Please choose a Date above");
    		isRentedDate = true;
    		optionsBoxRented.setDisable(true);
    		rentCarButton.setDisable(true);
    		rentedCarsText.setText("Rented Cars on specific Date");
    	}
    	else {
    		isRentedDate = false;
    		optionsBoxRented.setDisable(false);
    		rentCarButton.setDisable(false);
    		initializeUnrentedCars(repo);
    		rentedCarsText.setText("Rented Cars");
    	}
    }
    
    public void datePickAction(ActionEvent event) {
    	if(isRentedDate == true) {
	    	LocalDate date = datePickerRent.getValue();
	    	try {
		    	if(date.isBefore(LocalDate.now())) {
		    		datePickerRent.setValue(null);
		    		popUpError("Date is in the past");
		    		return;
		    	}
		    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/M/yyyy");
		    	String strDate = date.format(formatter);
		    	
		    	initializeRentedCarsOnDate(strDate);
	    	}
	    	catch(Exception e) {
	    		System.out.println("Wrong date chosen");
	    	}
	    }
    }
 
    public Double roundDoubleToTwo(Double value) {
        BigDecimal bd = new BigDecimal(value).setScale(2, RoundingMode.HALF_UP);
        double result = bd.doubleValue();
        return result;
    }
    
    public void initialize() {
    	toolTipCheckBox.setShowDelay(Duration.millis(200));
    	toolTipCheckBox.setHideDelay(Duration.millis(200));
    	
    	optionsBox.getItems().addAll(options);
    	optionsBox.setValue("Please choose:");
    	mainTextField.setDisable(true);
    	
    	repo = new CarsRepositoryFile();	
    	
    	initializeUnrentedCars(repo);
	}
    
    public void initializeUnrentedCars(CarsRepositoryFile repo) {
    	optionsBoxRented.getItems().clear();
    	List<String> unrentedCars = new ArrayList<String>();
    	String rentedCarsString = "";
    	
    	for(Car car : repo.findAll()) {
    		if(car.isRented() == false) {
	    		String carText = "" + car;
	    		unrentedCars.add(carText);
    		}
    		else {
	    		rentedCarsString += car + "\n\n";
    		}
    	}
    	
    	String[] unrentedCarsString = new String[unrentedCars.size()];
    	unrentedCars.toArray(unrentedCarsString);
    	optionsBoxRented.getItems().addAll(unrentedCarsString);
    	optionsBoxRented.setValue("Please Choose Car:");
    	
    	textAreaRent.setText(rentedCarsString);
    	
    }
    
    public void initializeRentedCarsOnDate(String date) {
    	textAreaRent.setText("");
    	String rentedCarsString = "";
    	
    	for(Car car : repo.findAll()) {
    		if(car.isRented() == true) {
    			if(car.getRented_date().equals(date)) {
    				rentedCarsString += car + "\n\n";
    			}
    		}
    	}
    	
    	if(rentedCarsString.equals("")) {
    		textAreaRent.setText("No cars rented on " + date);
    	}
    	else {
    		textAreaRent.setText("Rented cars on " + date + " are:\n\n" + rentedCarsString);
    	}
    }
    
    public void addCarObj(Car car) {
    	repo.add(car);
    	textArea.setText("Added: " + car);
    }
    
    private List<Car> getCarsList(CarsRepositoryFile current_repo) {
		List<Car> carsList =  new ArrayList<Car>();
		for(Car car : current_repo.findAll())
			carsList.add(car);
		
		return carsList;
	}
    
    public void removeCar(int ID) {
    	Car car = null;
    	String s = "";
    	try {
    		car = repo.findById(ID);
        	s = "Deleted ";
        	s = s + car;
    		textArea.setText(s);
    		repo.delete(car);
    	}catch (Exception e) {
    		s = "ID not found";
    		textArea.setText(s);
    	}
    }
    
    public void printCarByID(int ID) {
    	String s = "";
    	Car car = null;
    	try {
    		car = repo.findById(ID);
        	s = s + car;
    	}catch(Exception e) {
    		s = "ID not found";
    	}
		textArea.setText(s);
    }
    
    public void printCars() {
    	String s = "";
		
		for(Car car : repo.findAll()) {
			s = s + car + "\n\n";
		}
		if(s == "")
			textArea.setText("Nothing");
		else
			textArea.setText(s);
    }
    
    public void printCarsFromManufacturer(String manufacturer) {
		List<Car> carsList = this.getCarsList(repo);

		final StringBuilder builder = new StringBuilder();
		
		carsList.stream()
			.filter(c -> c.getManufacturer().startsWith(manufacturer))
			.forEach(c ->{
				builder.append(c + "\n\n");
			});
        
        String concatenatedString = builder.toString();
        String str = manufacturer + ": ";
        try {
        	str = (concatenatedString.substring(0, concatenatedString.length() - 1));
        }catch(Exception e) {
        	str = "";
        }
		if(str == "")
			textArea.setText("Nothing");
		else
			textArea.setText(str);
    }
    
    public void printCarsNewerOrOlderThan(int Year, boolean newer) {
		List<Car> carsList = this.getCarsList(repo);

		final StringBuilder builder = new StringBuilder();
    	if(newer)
			carsList.stream()
				.filter(c -> Integer.parseInt(c.getManufacturing_year()) > Year)
				.forEach(c ->{
					builder.append(c + "\n\n");
				});
		else
			carsList.stream()
			.filter(c -> Integer.parseInt(c.getManufacturing_year()) < Year)
			.forEach(c ->{
				builder.append(c + "\n\n");
			});
    	String concatenatedString = builder.toString();
    	String str = "";
        try {
        	str = (concatenatedString.substring(0, concatenatedString.length() - 1));
        }catch(Exception e) {
        	str = "";
        }
		if(str == "")
			textArea.setText("Nothing");
		else
			textArea.setText(str);
    }
    
    public void printPricesForSpecificModels(String model) {
		List<Car> carsList = this.getCarsList(repo);
		final StringBuilder builder = new StringBuilder();
		
		carsList.stream()
			.filter(c -> c.getModel().startsWith(model))
			.map(cm -> cm.getPrice())
			.forEach(c ->{
				builder.append(c + " euros\n\n");
			});
		String concatenatedString = builder.toString();
		String str = "";
        try {
        	str = (concatenatedString.substring(0, concatenatedString.length() - 1));
        }catch(Exception e) {
        	str = "";
        }
		if(str == "")
			textArea.setText("Nothing");
		else
			str = model + " prices are: \n\n" + str;
			textArea.setText(str);
    }
    
    public void printYearsFromManufacturer(String manufacturer) {
    	List<Car> carsList = this.getCarsList(repo);
		final StringBuilder builder = new StringBuilder();
		
		carsList.stream()
			.filter(c -> c.getManufacturer().startsWith(manufacturer))
			.map(cm -> cm.getManufacturing_year())
			.forEach(c ->{
				builder.append(c + "\n\n");
			});
		String concatenatedString = builder.toString();
        String str = manufacturer + ": ";
        try {
        	str = (concatenatedString.substring(0, concatenatedString.length() - 1));
        }catch(Exception e) {
        	str = "";
        }
		if(str == "")
			textArea.setText("Nothing");
		else
			textArea.setText(str);
    }
    
    public void printSumFromManufacturer(String manufacturer) {
    	List<Car> carsList = this.getCarsList(repo);
		final StringBuilder builder = new StringBuilder();
		int sum = 0;
		sum = carsList.stream()
				.filter(c -> c.getManufacturer().startsWith(manufacturer))
				.mapToInt(cm -> (int)cm.getPrice())
				.sum();
		if(sum == 0)
			textArea.setText("Nothing");
		else {
			String str = manufacturer + " sum " + Integer.toString(sum) + " euros";
			textArea.setText(str);
		}
	}
    
    public void printManufacturers() {
    	List<Car> carsList = this.getCarsList(repo);
		final StringBuilder builder = new StringBuilder();
		
		carsList.stream()
			.map(cm -> cm.getManufacturer())
			.distinct()
			.forEach(c ->{
				builder.append(c + "\n\n");
			});
		
		String concatenatedString = builder.toString();
        String str = "";
        try {
        	str = (concatenatedString.substring(0, concatenatedString.length() - 1));
        }catch(Exception e) {
        	str = "";
        }
		if(str == "")
			textArea.setText("Nothing");
		else
			textArea.setText(str);
    }
    
    public void printCheapestCar() {
    	List<Car> carsList = this.getCarsList(repo);
		final StringBuilder builder = new StringBuilder();
		
		Car car = carsList.stream()
				.min((c1, c2) -> (int)c1.getPrice() - (int)c2.getPrice())
				.orElseThrow(NoSuchElementException::new);
		
		String str = "Cheapest car: " + car;
		if(car == null)
			textArea.setText("Nothing");
		else
			textArea.setText(str);
    }
}