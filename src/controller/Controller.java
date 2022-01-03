package controller;

import java.util.*;
import cars.Car;
import repository.*;

public class Controller{
	CarsRepositoryFile fileRepo = new CarsRepositoryFile("cars.txt");
	CarsRepository repo = new CarsRepository(fileRepo);
	CarsRepositorySerialization serRepo = new CarsRepositorySerialization(fileRepo, "carsBytes.txt");
	
	private List<Car> getCarsList(CarsRepository current_repo) {
		List<Car> carsList =  new ArrayList<Car>();
		for(Car car : current_repo.findAll())
			carsList.add(car);
		
		return carsList;
	}
	
	public static Car readCar(boolean newCar, CarsRepository current_repo) {
		Scanner keyboard = new Scanner(System.in);

		int ID;
		String manufacturer;
		String model;
		int max_speed;
		float price;
		String manufacturing_year;
		boolean rented = false;
		String rented_date = "n/a";

		if(newCar == true)
			while(true) {
				System.out.println("ID: ");
				ID = keyboard.nextInt();
				if(current_repo.checkID(ID) == false)
					break;
				else System.out.println("!!! ID already in list, please write another one !!!");
			}
		else {
			while(true) {
				System.out.println("ID: ");
				ID = keyboard.nextInt();
				if(current_repo.checkID(ID) == true)
					break;
				else System.out.println("!!! ID not in list, please write another one !!!");
			}
		}
		System.out.println("Manufacturer: ");
		manufacturer = keyboard.next();
		System.out.println("Model: ");
		model = keyboard.next();
		System.out.println("Max Speed: ");
		max_speed = keyboard.nextInt();
		System.out.println("Price: ");
		price = keyboard.nextFloat();
		System.out.println("Year of Production: ");
		manufacturing_year = keyboard.next();

		return new Car(ID, manufacturer, model, max_speed, price, manufacturing_year, rented, rented_date);
	}
	
	public boolean addCar() {
		Car car = readCar(true, repo);
		try {
			repo.add(car);
			fileRepo.add(car);
			serRepo.add(car);
		}
		catch(Exception e) {
			System.out.println("Error " + e + " when adding Car to repository");
			return false;
		}
		return true;
	}
	
	public boolean updateCar() {
		Car car = readCar(false, repo);
		try {
			repo.add(car);
			fileRepo.add(car);
			serRepo.add(car);
		}
		catch(Exception e) {
			System.out.println("Error " + e + " when adding Car to repository");
			return false;
		}
		return true;
	}
	
	public boolean removeCar(int ID) {
		if(repo.checkID(ID) == true) {
			Car current_car = repo.findById(ID);
			
			repo.delete(current_car);
			fileRepo.delete(current_car);
			serRepo.delete(current_car);

			System.out.println("Removed car: " + current_car);
			return true;
		}
		else {
			System.out.println("Car not in list");
			return false;
		}
	}
	
	public Car getCar(int ID) {
		if(repo.checkID(ID) == false)
			return null;
		else return repo.findById(ID);
	}
	
	public void printCars() {
		for(Car car : repo.findAll())
			System.out.println(car);
	}
	
	public void printCarsFromManufacturers(String Manufacturer) {
		List<Car> carsList = this.getCarsList(repo);
		
		carsList.stream()
			.filter(c -> c.getManufacturer().startsWith(Manufacturer))
			.forEach(System.out::println);
	}
	
	public void printCarsNewerOrOlderThan(int Year, boolean newer) {
		List<Car> carsList = this.getCarsList(repo);
		
		if(newer)
			carsList.stream()
				.filter(c -> Integer.parseInt(c.getManufacturing_year()) > Year)
				.forEach(System.out::println);
		else
			carsList.stream()
			.filter(c -> Integer.parseInt(c.getManufacturing_year()) < Year)
			.forEach(System.out::println);
	}
	
	public void printPricesForSpecificCars(String CarsType) {
		List<Car> carsList = this.getCarsList(repo);
		
		carsList.stream()
			.filter(c -> c.getModel().startsWith(CarsType))
			.map(cm -> cm.getPrice())
			.forEach(System.out::println);
	}
	
	public void printYearsOfCarsFromManufacturer(String Manufacturer) {
		List<Car> carsList = this.getCarsList(repo);
		
		carsList.stream()
			.filter(c -> c.getManufacturer().startsWith(Manufacturer))
			.map(cm -> cm.getManufacturing_year())
			.forEach(System.out::println);
	}
	
	public void printSumOfPricesFromManufacturer(String Manufacturer) {
		List<Car> carsList = this.getCarsList(repo);
		
		int sum = carsList.stream()
			.filter(c -> c.getManufacturer().startsWith(Manufacturer))
			.mapToInt(cm -> (int)cm.getPrice())
			.sum();
		System.out.println(sum);
	}
	
	public void printAllManufacturers() {
		List<Car> carsList = this.getCarsList(repo);
		
		carsList.stream()
			.map(cm -> cm.getManufacturer())
			.distinct()
			.forEach(System.out::println);
	}
	
	public void printCheapestCar() {

		List<Car> carsList = this.getCarsList(repo);
		
		Car car = carsList.stream()
			.min((c1, c2) -> (int)c1.getPrice() - (int)c2.getPrice())
			.orElseThrow(NoSuchElementException::new);
		
		System.out.println(car);
	}
}
