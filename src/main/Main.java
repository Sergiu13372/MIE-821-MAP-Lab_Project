package main;

import java.util.Scanner;

import controller.Controller;

public class Main {

	public static void clearScreen() {
		for(int i = 0; i < 100; i++)
			System.out.println("\n");
	}
	
	public static void printChoices() {
		String s = "";
		s = s + "\n ------------------------------------ \n";
		s = s + "Please choose one of the options below: \n";
		s = s + "\t \n  1 . Add car";
		s = s + "\t \n  2 . Remove car";
		s = s + "\t \n  3 . Update car";
		s = s + "\t \n  4 . Show car by id";
		s = s + "\t \n  5 . Show cars";
		s = s + "\t \n  6 . Print cars from specific Manufacturer";
		s = s + "\t \n  7 . Print cars newer than X year";
		s = s + "\t \n  8 . Print cars older than X year";
		s = s + "\t \n  9 . Print prices for specific car type (Micra)";
		s = s + "\t \n  10. Print years of cars from specific Manufacturer";
		s = s + "\t \n  11. Print sum of cars from Manufacturer";
		s = s + "\t \n  12. Print all Manufacturers";
		s = s + "\t \n  13. Print cheapest Car";
		s = s + "\n";
		s = s + "\t \n -1. Exit";
		s = s + "\n ------------------------------------ \n";
		System.out.println(s);
	}
	
	public static void mainFunc() {
		int choice = 0;
		Scanner keyboard = new Scanner(System.in);
		Controller carController = new Controller();
		
		while(true) {
			printChoices();
			choice = 0;
			
			while(true) {
				System.out.println("Please enter your option (only Integers): ");
				choice = keyboard.nextInt();
				keyboard.nextLine();
				if(choice != 0 && choice >= -1 && choice <= 13)
					break;
				else {
					System.out.println("Please choose one of the options from above. Try again: ");
				}
			}

			clearScreen();

			if(choice == -1) {
				System.out.println("Exiting...");
				break;
			}
			else if (choice == 1) {
				carController.addCar();
				System.out.println("Car added successfully.");
			}
			else if (choice == 2) {
				System.out.println("What is the ID of the car you want to remove?: ");
				int ID = keyboard.nextInt();
				carController.removeCar(ID);
			}
			else if (choice == 3) {
				carController.updateCar();
			}
			else if (choice == 4) {
				System.out.println("What is the ID of the car you want to see?: ");
				int ID = keyboard.nextInt();
				System.out.println(carController.getCar(ID));
			}
			else if (choice == 5) {
				carController.printCars();
			}
			else if (choice == 6) {
				System.out.println("What is the Manufacturer?: ");
				String Manufacturer = keyboard.nextLine();
				carController.printCarsFromManufacturers(Manufacturer);
			}
			else if (choice == 7) {
				System.out.println("What is the year?: ");
				int year = keyboard.nextInt();
				carController.printCarsNewerOrOlderThan(year, true);
			}
			else if (choice == 8) {
				System.out.println("What is the year?: ");
				int year = keyboard.nextInt();
				carController.printCarsNewerOrOlderThan(year, false);
			}
			else if (choice == 9) {
				System.out.println("What is the car type?: ");
				String CarsType = keyboard.nextLine();
				carController.printPricesForSpecificCars(CarsType);
			}
			else if (choice == 10) {
				System.out.println("What is the Manufacturer?: ");
				String Manufacturer = keyboard.nextLine();
				carController.printYearsOfCarsFromManufacturer(Manufacturer);
			}
			else if (choice == 11) {
				System.out.println("What is the Manufacturer?: ");
				String Manufacturer = keyboard.nextLine();
				carController.printSumOfPricesFromManufacturer(Manufacturer);
			}
			else if (choice == 12) {
				carController.printAllManufacturers();
			}
			else if (choice == 13) {
				carController.printCheapestCar();
			}
		}
	}
	
	public static void main(String[] args) {
		mainFunc();
	}
}