package main;

import java.nio.MappedByteBuffer;
import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import objets.Noeud;
import outil.Outil;

public class Mission2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Noeud noeudCourant = Outil.sitationDepart()[0];
		Noeud noeudFinal = Outil.sitationDepart()[1];
		LinkedHashMap<Noeud, Integer> mapCout = new LinkedHashMap<>();
		LinkedHashMap<Noeud, Noeud> predecessor = new LinkedHashMap<>();
		Set<Noeud> noeudsNonTraites = new HashSet<Noeud>();
		Set<Noeud> noeudsTraite = new HashSet<Noeud>();		
		mapCout.put(noeudCourant, 0);
		noeudsNonTraites.add(noeudCourant);
		Noeud solution = new Noeud(noeudCourant);	
		mapCout.put(solution,1000000);
		while(noeudsNonTraites.size() > 0) {
			noeudCourant = Outil.getMinimum(noeudsNonTraites, mapCout);
			noeudsTraite.add(noeudCourant);
			noeudsNonTraites.remove(noeudCourant);						
			Outil.findMinimalTime(mapCout, predecessor, noeudCourant, noeudsNonTraites);
		
			if (Outil.deuxEtatsEgaux(noeudCourant, noeudFinal) && mapCout.get(noeudCourant)<mapCout.get(solution)) {
				solution = noeudCourant;
				mapCout.put(solution, mapCout.get(noeudCourant));				
			}
		}		
		LinkedList<Noeud> chemin = new LinkedList<>();
		chemin = Outil.getChemin(solution, predecessor);
		System.out.println("sol : ");
		solution.affichage();
		for (Noeud n : chemin) {
			n.affichage();
			System.out.println("Cout pour arriver jusque ici : " + mapCout.get(n));		
		}
	}
}
