package schueler;

import java.io.Serializable;
import java.util.ArrayList;

public class Fach implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Double> normale = new ArrayList<>();
	private ArrayList<Double> schulaufgaben = new ArrayList<>();
	private String name;
	
	public Fach(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void addNormal(double normal) {
		normale.add(normal);
	}
	
	public void addSchulaufgabe(double schulaufgabe) {
		schulaufgaben.add(schulaufgabe);
	}
	
	public void addKurzarbeit(double kurzarbeit) {
		addNormal(kurzarbeit);
		addNormal(kurzarbeit);
	}
	
	public double normaleDurchschnitt() {
		return durchschnitt(normale);
	}
	
	public double schulaufgabenDurchschnitt() {
		return durchschnitt(schulaufgaben);
	}
	
	public double gesamtDurchschnitt() {
		if(schulaufgaben.size() == 0 && normale.size() == 0) return 0;
		if(schulaufgaben.size() == 0) return normaleDurchschnitt();
		if(normale.size() == 0) return schulaufgabenDurchschnitt();
		ArrayList<Double> gesamt = new ArrayList<>();
		double schulaufgabe = schulaufgabenDurchschnitt();
		gesamt.add(schulaufgabe);
		gesamt.add(schulaufgabe);
		gesamt.add(normaleDurchschnitt());
		return durchschnitt(gesamt);
	}
	
	private double durchschnitt(ArrayList<Double> list) {
		if(list.size() == 0) return 0;
		double gesamt = 0;
		for(double d : list) 
			gesamt += d;
		return gesamt / list.size();
	}
	
}
