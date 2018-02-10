package outil;

import java.awt.RenderingHints.Key;
import java.nio.MappedByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import objets.Bateau;
import objets.Noeud;

public class Outil {
	
	
	public static int getTemps(Noeud n1, Noeud n2) {
		int val= 0;
		ArrayList<Bateau> bateauxQuiOntBouge  = new ArrayList<>();
		if (!n1.isTrajetAller()) {
			for (Bateau b : n1.getBateauxCoteB()) {
				for (Bateau b2 : n2.getBateauxCoteA()) {
					if (b2.equal(b)) {
						bateauxQuiOntBouge.add(b);
						val = b.getTemps_minute();
					}
				}
			}
		} else {
			for (Bateau b : n1.getBateauxCoteA()) {
				for (Bateau b2 : n2.getBateauxCoteB()) {
					if (b2.equal(b)) {
						bateauxQuiOntBouge.add(b);
					}
				}
			}
			Bateau b1 = bateauxQuiOntBouge.get(0);
			Bateau b2 = bateauxQuiOntBouge.get(1);
			val = Outil.calculCout(b1, b2);
		}
		
		return val;
		
	}
	
	public static int calculCout (Bateau b1, Bateau b2) {
		if (b1.getTemps_minute() > b2.getTemps_minute()) {
			return b1.getTemps_minute();
		} else {
			return b2.getTemps_minute();
		}
	}
	
	public static int getMinCout(Noeud n, Map<Noeud, Integer> mapCout) {
		Integer cout = mapCout.get(n);
		if (cout == null) {
			return Integer.MAX_VALUE;
		} else {
			return cout;
		}
	}
	
	public static Noeud getMinimum(Set<Noeud> noeuds, Map<Noeud, Integer> mapVal) {
		Noeud min = null;
		for (Noeud n : noeuds) {
			if (min == null) {
				min = n;
			} else {
				int minCoutN = Outil.getMinCout(n, mapVal);
				int minCoutMin = Outil.getMinCout(min, mapVal);
				if (minCoutN < minCoutMin) {
					min = n;
				}
			}
		}
		return min;
	}
	
	public static LinkedList<Noeud> getChemin(Noeud target, Map<Noeud, Noeud> mapParent) {
		LinkedList<Noeud> chemin = new LinkedList<>();
		
		Noeud etape = target;
		chemin.add(etape);
		if (mapParent.get(etape) != null) {
			etape = mapParent.get(etape);
			chemin.add(etape);
		}
		
		while(mapParent.get(etape)!=null) {
			etape = mapParent.get(etape);
			chemin.add(etape);
		}
		Collections.reverse(chemin);
		return chemin;
	}
	
	
	public static void findMinimalTime(Map<Noeud, Integer> mapValeur, Map<Noeud, Noeud> predecesseurs,  Noeud s1, Set<Noeud> noeudsNonTraites) {		
		ArrayList<Noeud> noeudsVoisins = Outil.noeudsEtapeSuivante(s1, predecesseurs, mapValeur);	
		for (Noeud n : noeudsVoisins) {
			int tempsEntreLesDeuxNoeuds = Outil.getTemps(s1,n);
			int getMinCoutN = Outil.getMinCout(n, mapValeur);
			int getMinCoutS1 = Outil.getMinCout(s1, mapValeur) + tempsEntreLesDeuxNoeuds;
			
			if (getMinCoutN > getMinCoutS1){
				mapValeur.put(n, getMinCoutS1);
				predecesseurs.put(n, s1);
				noeudsNonTraites.add(n);
			}
		}
	}
	
	public static ArrayList<Noeud> noeudsEtapeSuivante(Noeud n, Map<Noeud, Noeud> mapParent, Map<Noeud, Integer> mapCout) {
		ArrayList<Noeud> returnValue = new ArrayList<>();		
		if(n.isTrajetAller()) {			
			for (int i=0; i<n.getBateauxCoteA().size()-1;i++) {
				for(int j=i+1; j<n.getBateauxCoteA().size(); j++) {					
					Noeud nouveauNoeud = new Noeud(n);
					nouveauNoeud.getBateauxCoteB().add(nouveauNoeud.getBateauxCoteA().get(i));
					nouveauNoeud.getBateauxCoteB().add(nouveauNoeud.getBateauxCoteA().get(j));
					nouveauNoeud.getBateauxCoteA().remove(j);					
					nouveauNoeud.getBateauxCoteA().remove(i);
					nouveauNoeud.setTrajetAller(false);
					returnValue.add(nouveauNoeud);
				}
			}
		} else {
			for (int i = 0; i<n.getBateauxCoteB().size(); i++) {
				Noeud nouveauNoeud = new Noeud(n);
				nouveauNoeud.getBateauxCoteA().add(nouveauNoeud.getBateauxCoteB().get(i));
				nouveauNoeud.getBateauxCoteB().remove(i);							
				nouveauNoeud.setTrajetAller(true);
				returnValue.add(nouveauNoeud);								
			}
		}
		
		return returnValue;
	}
	
	public static <T> boolean listEqualsIgnoreOrder(List<T> list1, List<T> list2) {
	    return new HashSet<>(list1).equals(new HashSet<>(list2));
	}
	
	
	public static boolean deuxArraysEgaux(ArrayList<Bateau> a, ArrayList<Bateau> b) {
		if (a.size() != b.size()) return false;
		
		int i = 0;
		int j = 0;
		boolean idem = true;
		
		while (i<a.size() && idem == true) {
			boolean val = a.get(i).equal(b.get(j));
			if (!val) {
				j++;
			} else {
				i++;
				j = 0;
			}
			
			if (j==a.size() && i == a.size() && !val) {
				idem = false;
				
			}
		}
		return idem;
	}
	
	public static boolean deuxEtatsEgaux(Noeud s1, Noeud s2) {
		if (deuxArraysEgaux(s1.getBateauxCoteA(), s2.getBateauxCoteA()) && deuxArraysEgaux(s1.getBateauxCoteB(), s2.getBateauxCoteB())) {
			return true;
		}
		
		return false;
	}
	
	public static void initialisation( Map<Noeud, Integer> mapCout, Map<Noeud, Noeud> mapParent)  {
		Noeud etatInitial = Outil.sitationDepart()[0];
		mapCout.put(etatInitial, 0);
		mapParent.put(etatInitial, etatInitial); //Pas de parents
	}
	
	public static Noeud[] sitationDepart() {
		Noeud[] noeuds = new Noeud[2];
		
		Noeud etatInitial = new Noeud();
		etatInitial.setTrajetAller(true);
		ArrayList<Bateau> bateauxCoteA = new ArrayList<>();
		ArrayList<Bateau> bateauxCoteB = new ArrayList<>();

		Bateau X1 = new Bateau(45, "X1");
		Bateau X2 = new Bateau(90, "X2");
		Bateau X3 = new Bateau(180, "X3");
		Bateau X4 = new Bateau(360, "X4");

		bateauxCoteA.add(X1);
		bateauxCoteA.add(X2);
		bateauxCoteA.add(X3);
		bateauxCoteA.add(X4);

		etatInitial.setBateauxCoteA(bateauxCoteA);
		etatInitial.setBateauxCoteB(bateauxCoteB);

		Noeud etatFinal = new Noeud();

		etatFinal.setBateauxCoteB(bateauxCoteA);
		etatFinal.setBateauxCoteA(bateauxCoteB);
		etatInitial.setTrajetAller(true);
		noeuds[0] = etatInitial;
		noeuds[1] = etatFinal;
		
		return noeuds;
	}
}
