package cars;

public interface Identifiable<Tid> {
	Tid getID();

	void setID(Tid id);
}