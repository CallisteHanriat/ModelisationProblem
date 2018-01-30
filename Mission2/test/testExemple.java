import static org.junit.Assert.*;

import java.lang.reflect.Array;
import java.security.Signature;
import java.util.ArrayList;

import org.junit.Test;

import objets.Bateau;
import objets.Noeud;
import outil.Outil;

public class testExemple {

	@Test
	public void test() {
		Noeud[] noeudsDepart = new Noeud[2]; 
		noeudsDepart[0] = this.sitationDepart()[0];
		Noeud etatInitial = noeudsDepart[0];
		Noeud etatFinal = noeudsDepart[1];
		
		etatInitial.affichage();
		
		etatInitial.setFils(Outil.noeudsEtapeSuivante(etatInitial));
		for (Noeud fils : etatInitial.getFils()){
			fils.affichage();
		}
		
		ArrayList<Noeud> bateauxApresFils1 = Outil.noeudsEtapeSuivante(etatInitial.getFils().get(0));
		
		System.out.println("ANALYSE DU FILS \n\n\n nouvel etat\n");
		etatInitial.getFils().get(0).affichage();
		System.out.println("\nFils\n-----------------\n");
		for (Noeud filsFils : bateauxApresFils1) {
			filsFils.affichage();
		}
		
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
