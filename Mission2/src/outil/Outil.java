package outil;

import java.awt.RenderingHints.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import objets.Bateau;
import objets.Noeud;
import objets.Trajet;

public class Outil {
	
	public static int calculCout (Bateau b1, Bateau b2) {
		if (b1.getTemps_minute() > b2.getTemps_minute()) {
			return b1.getTemps_minute();
		} else {
			return b2.getTemps_minute();
		}
	}
	
	public static Noeud trouverMinimum(ArrayList<Noeud> noeuds) {
		int min = 1000000;
		Noeud sommet = new Noeud();
		for (Noeud n : noeuds) {
			if (n.getCout()<min) {
				min = n.getCout();
				sommet = n;
			}
		}
		
		return sommet;
	}
	
	public static void maj_distance(Map<Noeud, Integer> mapValeur, Map<Noeud, Noeud> predecesseurs,  Noeud s1, Noeud s2) {		
		if (mapValeur.get(s1).intValue() > mapValeur.get(s2).intValue() + s1.getCout()+s2.getCout()) {
			mapValeur.put(s2, s1.getCout()+s2.getCout());
			predecesseurs.put(s2, s1);
		}
	}
	
	public static ArrayList<Noeud> noeudsEtapeSuivante(Noeud n, Map<Noeud, Noeud> mapParent, Map<Noeud, Integer> mapCout) {
		int nombre_bateaux = n.getBateauxCoteA().size()+n.getBateauxCoteB().size();
		ArrayList<Noeud> returnValue = new ArrayList<>();
		ArrayList<Bateau> bateauxCoteA = (ArrayList<Bateau>) n.getBateauxCoteA().clone();
		ArrayList<Bateau> bateauxCoteB = (ArrayList<Bateau>) n.getBateauxCoteB().clone();
		Bateau b1 = new Bateau();
		Bateau b2 = new Bateau();
		if(n.isTrajetAller()) {			
			for (int i=0; i<n.getBateauxCoteA().size()-1;i++) {
				for(int j=i+1; j<n.getBateauxCoteA().size(); j++) {
					b1 = n.getBateauxCoteA().get(i);
					b2 = n.getBateauxCoteA().get(j);
					bateauxCoteB.add(bateauxCoteA.get(i));					
					bateauxCoteB.add(bateauxCoteA.get(j));
					bateauxCoteA.remove(j);
					bateauxCoteA.remove(i);
					Noeud nouveauNoeud = new Noeud();
					nouveauNoeud.setBateauxCoteA(bateauxCoteA);
					nouveauNoeud.setBateauxCoteB(bateauxCoteB);
					nouveauNoeud.setCout(Outil.calculCout(b1, b2));
					nouveauNoeud.setTrajetAller(false);
					mapParent.put(nouveauNoeud, n);
					mapCout.put(nouveauNoeud, nouveauNoeud.getCout());
					returnValue.add(nouveauNoeud);
					bateauxCoteA = (ArrayList<Bateau>) n.getBateauxCoteA().clone();
					bateauxCoteB = (ArrayList<Bateau>) n.getBateauxCoteB().clone();
				}
			}
		} else {
			for (int i = 0; i<n.getBateauxCoteB().size(); i++) {
				Bateau b = n.getBateauxCoteB().get(i);
				Noeud nouveauNoeud = new Noeud();
				bateauxCoteA.add(bateauxCoteB.get(i));
				bateauxCoteB.remove(i);
				nouveauNoeud.setBateauxCoteA(bateauxCoteA);
				nouveauNoeud.setBateauxCoteB(bateauxCoteB);
				mapParent.put(nouveauNoeud, n);
				nouveauNoeud.setCout(b.getTemps_minute());
				nouveauNoeud.setTrajetAller(true);
				mapCout.put(nouveauNoeud, nouveauNoeud.getCout());
				returnValue.add(nouveauNoeud);
				
				bateauxCoteA = (ArrayList<Bateau>) n.getBateauxCoteA().clone();
				bateauxCoteB = (ArrayList<Bateau>) n.getBateauxCoteB().clone();
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
				j = i;
			}
			
			if (j==a.size() && !val) {
				idem = false;
				
			}
		}
		return idem;
	}
	
	public static boolean deuxEtatsEgaux(Noeud s1, Noeud s2) {
		if (s1.getCout() != s2.getCout()) return false;
		if (deuxArraysEgaux(s1.getBateauxCoteA(), s2.getBateauxCoteA()) && deuxArraysEgaux(s1.getBateauxCoteB(), s2.getBateauxCoteB())) {
			return true;
		}
//		if (s1.getBateauxCoteA().containsAll(s2.getBateauxCoteA()) && s1.getBateauxCoteB().containsAll(s2.getBateauxCoteB())) return true;
		
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

		etatInitial.setCout(0);

		Noeud etatFinal = new Noeud();

		etatFinal.setBateauxCoteB(bateauxCoteA);
		etatFinal.setBateauxCoteA(bateauxCoteB);
		etatInitial.setTrajetAller(true);
		noeuds[0] = etatInitial;
		noeuds[1] = etatFinal;
		
		return noeuds;
	}
}
