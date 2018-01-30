import static org.junit.Assert.*;

import org.junit.Test;

import objets.Noeud;
import outil.Outil;

public class testEgalite {

	@Test
	public void test() {
		Noeud n1 = Outil.sitationDepart()[0];
		Noeud n2 = Outil.sitationDepart()[0];
		
		n2.affichage();
		System.out.println("Ces deux noeuds sont-ils égaux ? " + Outil.deuxEtatsEgaux(n1, n2));
		
		n2 = Outil.sitationDepart()[1];
		System.out.println("Ces deux noeuds sont-ils égaux ? " + Outil.deuxEtatsEgaux(n1, n2));		
	}

}
