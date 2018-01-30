package main;

import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import objets.Noeud;
import outil.Outil;

public class Mission2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Noeud etatInitial = Outil.sitationDepart()[0];
		LinkedHashMap<Noeud, Integer> mapCout = new LinkedHashMap<>();
		LinkedHashMap<Noeud, Noeud> mapParent = new LinkedHashMap<>();
		Outil.initialisation(mapCout, mapParent);
		Noeud s1 = mapParent.entrySet().iterator().next().getKey();
		s1.setFils(Outil.noeudsEtapeSuivante(s1, mapParent, mapCout));
		while(!mapParent.isEmpty() && !s1.getFils().isEmpty()) {
			mapParent.remove(s1);			
			for (Noeud s : s1.getFils()) {
				Outil.maj_distance(mapCout, mapParent, s1, s);
				System.out.println("taille du mapping parent : " + mapParent.size());
			}
			s1 = mapParent.entrySet().iterator().next().getKey();
			s1.setFils(Outil.noeudsEtapeSuivante(s1, mapParent, mapCout));
		}
		
		ArrayList<Noeud> sol = new ArrayList<>();
		Noeud etatFinal = new Noeud();
		Iterator iterator = mapParent.entrySet().iterator();
		while (iterator.hasNext()) { 
			Map.Entry pair = (Map.Entry) iterator.next();
			etatFinal = (Noeud) pair.getValue();
		}
		etatFinal.affichage();
		System.out.println("Retour a l'etat initial : \n");
		
		int a = 0;
		while (etatFinal != etatInitial) {
			sol.add(etatFinal);
			etatFinal = mapParent.get(etatFinal);
			System.out.println(a);
			a++;
		}
		etatFinal.affichage();
	}
}
