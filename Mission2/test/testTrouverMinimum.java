import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.junit.Test;

import objets.Noeud;
import outil.Outil;

public class testTrouverMinimum {

	@Test
	public void test() {
		Noeud n = Outil.sitationDepart()[0];
		n.setFils(Outil.noeudsEtapeSuivante(n, new LinkedHashMap<Noeud,Noeud>(), new LinkedHashMap<Noeud, Integer>()));
		Noeud nMin = Outil.trouverMinimum(n.getFils());
		
		System.out.println("Le minimum trouvé est : " + nMin.getCout());
	}

}
