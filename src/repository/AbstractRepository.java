package repository;

import cars.*;
import java.util.*;

public abstract class AbstractRepository<T extends Identifiable<ID>, ID> implements Repository<T, ID> {
	protected Map<ID, T> elem;

	public AbstractRepository() {
		elem = new HashMap<>();
	}

	public void add(T el) {
		if (elem.containsKey(el.getID()))
			throw new RuntimeException("Element already in list");
		else
			elem.put(el.getID(), el);
	}

	public void delete(T el) {
		if (elem.containsKey(el.getID()))
			elem.remove(el.getID());
	}

	public void update(T el, ID id) {
		if (elem.containsKey(id))
			elem.put(el.getID(), el);
		else
			throw new RuntimeException("Element doesn’t exist");
	}

	public T findById(ID id) {
		if (elem.containsKey(id))
			return elem.get(id);
		else
			throw new RuntimeException("Element doesn't exist");
	}

	public Iterable<T> findAll() {
		return elem.values();
	}

	public boolean checkID(ID id) {
		if (elem.containsKey(id))
			return true;
		else
			return false;
	}
}