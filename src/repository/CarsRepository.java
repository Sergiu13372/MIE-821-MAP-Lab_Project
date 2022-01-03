package repository;

import cars.*;
import repository.CarsRepositoryFile;

public class CarsRepository extends AbstractRepository<Car, Integer> {
	public CarsRepository() {
	};
	
	public CarsRepository(CarsRepositoryFile current_repo) {
		for(Car car : current_repo.findAll())
			this.add(car);
	}
	public boolean Rent(Integer ID, String Date) {
		if(this.findById(ID).isRented() == true) {
			System.out.println("!!! Can not rent because car is already rented !!!");
			return false;
		}
		else {
			this.findById(ID).setRented(true);
			this.findById(ID).setRented_date(Date);
			return true;
		}
	}

	public boolean RemoveRent(Integer ID) {
		if(this.findById(ID).isRented() == false) {
			System.out.println("!!! Can not remove rent because car is not rented !!!");
			return false;
		}
		else {
			this.findById(ID).setRented(false);
			this.findById(ID).setRented_date("");
			return true;
		}
	}
	
	public Car CheapestCar() {
		float cheap_price = 999999999;
		Car cheapest_car = null;
		for (Car car : this.findAll())
			if(cheap_price > car.getPrice()) {
				cheap_price = car.getPrice();
				cheapest_car = car;
			}			
		return cheapest_car;
		}
	
	public void RentedCars() {
		for (Car car : this.findAll())
			if(car.isRented() == true) {
				System.out.println(car);
			}
	}
};