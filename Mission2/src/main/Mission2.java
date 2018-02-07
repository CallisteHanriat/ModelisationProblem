package main;

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
		LinkedHashMap<Noeud, Integer> mapCout = new LinkedHashMap<>();
		LinkedHashMap<Noeud, Noeud> predecessor = new LinkedHashMap<>();
		Set<Noeud> noeudsNonTraites = new HashSet<Noeud>();
		Set<Noeud> noeudsTraite = new HashSet<Noeud>();		
		mapCout.put(noeudCourant, 0);
		noeudsNonTraites.add(noeudCourant);
		
		while(noeudsNonTraites.size() > 0) {
			noeudCourant = Outil.getMinimum(noeudsNonTraites, mapCout);
			noeudsTraite.add(noeudCourant);
			noeudsNonTraites.remove(noeudCourant);						
			Outil.findMinimalTime(mapCout, predecessor, noeudCourant, noeudsNonTraites);			
		}
		System.out.println("size of mapCout : " + mapCout.size());
		System.out.println("size of mapParent : " + predecessor.size()); 
		LinkedList<Noeud> chemin = new LinkedList<>();
		chemin = Outil.getChemin(noeudCourant, predecessor);
		for (Noeud n : chemin) {
			n.affichage();
		}
	}
}
