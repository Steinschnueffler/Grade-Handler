package schueler;

import java.io.Serializable;
import java.util.ArrayList;

public class Schueler implements Serializable{
	private static final long serialVersionUID = -5634223549032975671L;
	
	private String name;
	private String password;
	private ArrayList<Fach> faecher = new ArrayList<>();
	
	public Schueler(String name, String password) {
		this.name = name;
		this.password = password;
		faecher.add(new Fach("Mathe"));
		faecher.add(new Fach("Deutsch"));
		faecher.add(new Fach("Englisch"));
		faecher.add(new Fach("Latein"));
		faecher.add(new Fach("Französisch"));
		faecher.add(new Fach("Spanisch"));
		faecher.add(new Fach("Wirtschaft"));
		faecher.add(new Fach("Wirtschaftsinformatik"));
		faecher.add(new Fach("Sozialkunde"));
		faecher.add(new Fach("Physik"));
		faecher.add(new Fach("Biologie"));
		faecher.add(new Fach("Chemie"));
		faecher.add(new Fach("Religion"));
		faecher.add(new Fach("Geschichte"));
		faecher.add(new Fach("Geographie"));
		faecher.add(new Fach("Kunst"));
		faecher.add(new Fach("Musik"));
		faecher.add(new Fach("Sport"));
		faecher.add(new Fach("Informatik"));
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean passwordEquals(String anotherPassword) {
		return password.equals(anotherPassword);
	}
	
	public boolean setPassword(String newPassword) {
		if(passwordEquals(newPassword)) return false;
		this.password = newPassword;
		return true;
	}
	
	public ArrayList<Fach> getFaecher(){
		return faecher;
	}
	
	public void addFach(Fach f) {
		faecher.add(f);
	}
	
}
