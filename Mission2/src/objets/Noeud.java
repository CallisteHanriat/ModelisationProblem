package objets;

import java.util.ArrayList;

public class Noeud {
	private ArrayList<Bateau> bateauxCoteA, bateauxCoteB;
	
	private boolean trajetProchainAller;
	
	private int cout;
	
	private ArrayList<Noeud> fils;
	
	public Noeud(){
		
	}

	public ArrayList<Bateau> getBateauxCoteA() {
		return bateauxCoteA;
	}

	public Noeud(ArrayList<Bateau> bateauxCoteA, ArrayList<Bateau> bateauxCoteB, boolean trajetAller, int cout,
			ArrayList<Noeud> fils) {
		super();
		this.bateauxCoteA = bateauxCoteA;
		this.bateauxCoteB = bateauxCoteB;
		this.trajetProchainAller = trajetAller;
		this.cout = cout;
		this.fils = fils;
	}

	public boolean isTrajetAller() {
		return trajetProchainAller;
	}

	public void setTrajetAller(boolean trajetAller) {
		this.trajetProchainAller = trajetAller;
	}

	public void setBateauxCoteA(ArrayList<Bateau> bateauxCoteA) {
		this.bateauxCoteA = bateauxCoteA;
	}

	public ArrayList<Bateau> getBateauxCoteB() {
		return bateauxCoteB;
	}

	public void setBateauxCoteB(ArrayList<Bateau> bateauxCoteB) {
		this.bateauxCoteB = bateauxCoteB;
	}

	public int getCout() {
		return cout;
	}

	public void setCout(int cout) {
		this.cout = cout;
	}

	public ArrayList<Noeud> getFils() {
		return fils;
	}

	public void setFils(ArrayList<Noeud> fils) {
		this.fils = fils;
	}
	
	
	public void affichage() {
		System.out.println("cout le plus court pour arriver ici : " + this.getCout());
		System.out.println("CoteA");
		for (Bateau b : this.getBateauxCoteA()) {
			System.out.println("bateau " + b.getName() + " minutes trajet " + b.getTemps_minute());
		}
		System.out.println("\nCoteB");
		for (Bateau b : this.getBateauxCoteB()) {
			System.out.println("bateau " + b.getName() + " minutes trajet " + b.getTemps_minute());
		}
		System.out.println("\n----------------------------------------\n");
	}
	
}
