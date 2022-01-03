package repository;

import java.io.*;
import java.util.Map;

import repository.RepositoryException;
import repository.CarsRepositoryFile;
import cars.Car;

public class CarsRepositorySerialization extends AbstractRepository<Car, Integer>{
	private String filename;

	public CarsRepositorySerialization(String filename) {
		this.filename = filename;
		readFromFile();
	}
	
	public CarsRepositorySerialization(CarsRepositoryFile current_repo, String filename) {
		this.filename = filename;
		for(Car car : current_repo.findAll())
			this.add(car);
	}
	
	private void readFromFile ()
	{
		try(ObjectInputStream in= new ObjectInputStream (new FileInputStream(filename)))
	    {	
	    	elem = (Map<Integer, Car>) in.readObject();
	  	}
	    catch(Exception err)
	    {
	    	throw new RepositoryException("Error reading from file: "+err);
	    }
	}

    @Override
    public void add(Car obj) {
        try {
            super.add(obj);
            writeToFile();
        } catch (RuntimeException e) {
            throw new RepositoryException("Object wasn’t added" + e + " "+obj);
        }
    }

    @Override
    public void delete(Car obj) {
        try {
            super.delete(obj);
            writeToFile();
        } catch (RuntimeException ex) {
            throw new RepositoryException("Object was not deleted" + ex +" "+obj);
        }
    }
    
    @Override
    public void update(Car obj, Integer id) {
        try {
            super.update(obj, id);
            writeToFile();
        } catch (RuntimeException ex) {
            throw new RepositoryException("Object was not updated" + ex + " "+obj);
        }
    }
    
    public void writeToFile()
    {
		try(ObjectOutputStream e=new ObjectOutputStream(new FileOutputStream(filename))){
        		e.writeObject(elem);
    		}
    	catch(IOException r){
    		throw new RepositoryException("message " + r);
   			}
	 }
	  
}
    	