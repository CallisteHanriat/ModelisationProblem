package main;

import java.util.HashMap;

import objets.Noeud;
import outil.Outil;

public class Mission2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashMap<Noeud, Integer> mapCout = new HashMap<>();
		HashMap<Noeud, Noeud> mapParent = new HashMap<>();
		Outil.initialisation(mapCout, mapParent);
		
		System.out.println(mapCout.size());
	}

}
