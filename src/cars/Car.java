package cars;
import java.io.Serializable;

public class Car implements Identifiable<Integer>, Serializable  {
	private int ID;
	private String manufacturer;
	private String model;
	private int max_speed;
	private float price;
	private String manufacturing_year;
	private boolean rented;
	private String rented_date;



	public Car() {
		this.ID = 0;
		this.manufacturer = "";
		this.model = "";
		this.max_speed = 0;
		this.price = 0;
		this.manufacturing_year = "";
		this.rented = false;
		this.rented_date = "";
	}

	public Car(int ID, String manufacturer, String model, int max_speed, float price, String manufacturing_year, 
			boolean rented, String rented_date) {
		this.ID = ID;
		this.manufacturer = manufacturer;
		this.model = model;
		this.max_speed = max_speed;
		this.price = price;
		this.manufacturing_year = manufacturing_year;
		this.rented = rented;
		this.rented_date = rented_date;
	}

	public String getRented_date() {
		return rented_date;
	}

	public void setRented_date(String rented_date) {
		this.rented_date = rented_date;
	}

	public boolean isRented() {
		return rented;
	}

	public void setRented(boolean rented) {
		this.rented = rented;
	}

	public void setID(int iD) {
		ID = iD;
	}

	@Override
	public Integer getID() {
		return ID;
	}

	@Override
	public void setID(Integer ID) {
		this.ID = ID;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Integer getMax_speed() {
		return max_speed;
	}

	public void setMax_speed(int max_speed) {
		this.max_speed = max_speed;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getManufacturing_year() {
		return manufacturing_year;
	}

	public void setManufacturing_year(String manufacturing_year) {
		this.manufacturing_year = manufacturing_year;
	}

	@Override
	public String toString() {
		String s = "";
		s = s + "ID: " + this.ID + ", ";
		s = s + "Made by: " + this.manufacturer + ", ";
		s = s + "Type: " + this.model + ", ";
		s = s + "With a max speed of: " + this.max_speed + ", ";
		s = s + "With a price of: " + this.price + ", ";
		s = s + "Made in: " + this.manufacturing_year + " ";
		if(this.rented == false)
			s = s + "\t Not rented";
		else {
			s = s + "\t Rented until : " + this.rented_date;
		}
		return s;
	}

}