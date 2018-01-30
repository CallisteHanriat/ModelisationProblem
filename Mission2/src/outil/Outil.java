package outil;

import java.awt.RenderingHints.Key;
import java.util.ArrayList;
import java.util.HashMap;

import objets.Bateau;
import objets.Noeud;
import objets.Trajet;

public class Outil {
	
	public static int calculCout (Bateau b1, Bateau b2) {
		if (b1.getTemps_minute() < b2.getTemps_minute()) {
			return b1.getTemps_minute();
		} else {
			return b2.getTemps_minute();
		}
	}
	
	public static ArrayList<Noeud> noeudsEtapeSuivante(Noeud n) {
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
				nouveauNoeud.setCout(n.getCout()+b.getTemps_minute());
				nouveauNoeud.setTrajetAller(true);
				returnValue.add(nouveauNoeud);
				
				bateauxCoteA = (ArrayList<Bateau>) n.getBateauxCoteA().clone();
				bateauxCoteB = (ArrayList<Bateau>) n.getBateauxCoteB().clone();
			}
		}
		
		return returnValue;
	}
	
	public static void initialisation( HashMap<Noeud, Integer> mapCout, HashMap<Noeud, Noeud> mapParent)  {
		
	}
	
	public Noeud[] sitationDepart() {
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
