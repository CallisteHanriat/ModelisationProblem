package objets;

import java.util.ArrayList;

public class Noeud {
	private ArrayList<Bateau> bateauxCoteA, bateauxCoteB;
	
	private boolean trajetProchainAller;
	
	public Noeud(){
		
	}

	public ArrayList<Bateau> getBateauxCoteA() {
		return bateauxCoteA;
	}

	public Noeud(Noeud n) {
		super();
		this.bateauxCoteA = new ArrayList<>(n.getBateauxCoteA());
		this.bateauxCoteB = new ArrayList<>(n.getBateauxCoteB());
		this.trajetProchainAller = n.isTrajetAller();
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
	
	
	public void affichage() {
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
