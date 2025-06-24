package testsUnitaires;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import personnel.Employe;
import personnel.GestionPersonnel;
import personnel.Ligue;
import personnel.SauvegardeImpossible;

class testLigue {
	GestionPersonnel gestionPersonnel = GestionPersonnel.getGestionPersonnel();

	@Test
	void createLigue() throws SauvegardeImpossible {
		Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
		assertEquals("Fléchettes", ligue.getNom());

		Ligue ligueF = gestionPersonnel.addLigue("Football");
		assertEquals("Football", ligueF.getNom());
	}

	@Test
	void addEmploye() throws SauvegardeImpossible {
		Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
		Employe employe = ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", "azerty", LocalDate.now(),
				null);
		assertEquals(employe, ligue.getEmployes().first());
	}

	@Test
	void getNom() throws SauvegardeImpossible {
		Ligue ligue = gestionPersonnel.addLigue("Football");
		assertEquals("Football", ligue.getNom());
	}

	@Test
	void setNom() throws SauvegardeImpossible {
		Ligue ligue = gestionPersonnel.addLigue("Football");
		ligue.setNom("Fléchettes");
		assertEquals("Fléchettes", ligue.getNom());
	}

	@Test
	void toStringLigue() throws SauvegardeImpossible {
		Ligue ligue = gestionPersonnel.addLigue("Football");
		assertEquals("Football", ligue.toString());
	}

	@Test
	void setAdmin() throws SauvegardeImpossible {
		Ligue ligue = gestionPersonnel.addLigue("Football");
		Employe employe = ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", "azerty", null, null);
		ligue.setAdministrateur(employe);
		assertEquals(employe, ligue.getAdministrateur());
	}

	@Test
	void getAdmin() throws SauvegardeImpossible {
		Ligue ligue = gestionPersonnel.addLigue("Football");
		assertEquals("root   (null/null) (super-utilisateur)", ligue.getAdministrateur().toString());
	}

	@Test
	void remove() throws SauvegardeImpossible {
		Ligue ligue = gestionPersonnel.addLigue("Football");
		int initSize = gestionPersonnel.getLigues().size();
		ligue.remove();
		assertEquals(initSize, gestionPersonnel.getLigues().size());
	}

}
