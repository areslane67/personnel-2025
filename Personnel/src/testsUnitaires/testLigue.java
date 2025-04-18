package testsUnitaires;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import personnel.*;

class testLigue
{
	GestionPersonnel gestionPersonnel = GestionPersonnel.getGestionPersonnel();
	
	@Test
	void addEmploye() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
		Employe employe = ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", "azerty", LocalDate.now(), null); 
		assertEquals(employe, ligue.getEmployes().first());
	}
	
	@Test
	void getNom() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
		Employe employe = ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", "azerty", LocalDate.now(), LocalDate.parse("2023-01-13")); 
		assertEquals("Bouchard", employe.getNom());
	}
	
	@Test
	void setNom() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
		Employe employe = ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", "azerty", LocalDate.now(), LocalDate.parse("2023-01-13")); 
		employe.setNom("Lucas");
		assertEquals("Lucas", employe.getNom());
	}
	
	@Test
	void getPrenom() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
		Employe employe = ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", "azerty", LocalDate.now(), LocalDate.parse("2023-01-13")); 
		assertEquals("Gérard", employe.getPrenom());
	}
	
	@Test
	void setPrenom() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
		Employe employe = ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", "azerty", LocalDate.now(), LocalDate.parse("2023-01-13")); 
		employe.setNom("Lucas");
		assertEquals("Lucas", employe.getNom());
	}
	
	@Test
	void getMail() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
		Employe employe = ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", "azerty", LocalDate.now(), LocalDate.parse("2023-01-13")); 
		assertEquals("g.bouchard@gmail.com", employe.getMail());
	}
	
	@Test
	void setMail() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
		Employe employe = ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", "azerty", LocalDate.now(), LocalDate.parse("2023-01-13")); 
		employe.setMail("Lucas.seggar@gmail.com");
		assertEquals("Lucas.seggar@gmail.com", employe.getMail());
	}
	
	@Test
	void checkpwd() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
		Employe employe = ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", "azerty", LocalDate.now(), LocalDate.parse("2023-01-13")); 
		assertTrue(employe.checkPassword("azerty"));
		assertFalse(employe.checkPassword("qwerty"));
		assertFalse(employe.checkPassword(""));
		assertFalse(employe.checkPassword("aZerty"));
		assertFalse(employe.checkPassword("azerty#"));
	}
	
	@Test
	void setpwd() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
		Employe employe = ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", "azerty", LocalDate.now(), LocalDate.parse("2023-01-13")); 
		employe.setPassword("new_pwd");
		assertTrue(employe.checkPassword("new_pwd"));
		assertFalse(employe.checkPassword("new pwd"));
		assertFalse(employe.checkPassword(""));
		assertFalse(employe.checkPassword("azerty"));
	}
	
	@Test
	void getLigue() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
		Employe employe = ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", "azerty", LocalDate.now(), LocalDate.parse("2023-01-13")); 
		assertEquals(ligue, employe.getLigue());
	}
	
	@Test
	void getArrive() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
		Employe employe = ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", "azerty", LocalDate.parse("2019-11-22"), LocalDate.parse("2023-01-13")); 
		assertEquals(LocalDate.parse("2019-11-22"), employe.getDateArrive());
	}
	
	@Test
	void getDepart() throws SauvegardeImpossible
	{
		Ligue ligue = gestionPersonnel.addLigue("Fléchettes");
		Employe employe = ligue.addEmploye("Bouchard", "Gérard", "g.bouchard@gmail.com", "azerty", LocalDate.parse("2019-11-22"), LocalDate.parse("2023-01-13")); 
		assertEquals(LocalDate.parse("2023-01-13"), employe.getDateDepart());
	}
}
