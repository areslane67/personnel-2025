package commandLine;

import static commandLineMenus.rendering.examples.util.InOut.getString;
import java.time.LocalDate;
import java.util.ArrayList;

import commandLineMenus.List;
import commandLineMenus.ListOption;
import commandLineMenus.Menu;
import commandLineMenus.Option;
import personnel.Employe;

public class EmployeConsole 
{
	private Option afficher(final Employe employe)
	{
		return new Option("Afficher l'employé", "l", () -> {System.out.println(employe);});
	}

	ListOption<Employe> editerEmploye()
	{
		return (employe) -> editerEmploye(employe);		
	}

	Option editerEmploye(Employe employe)
	{
			Menu menu = new Menu("Modifier le compte de " + employe.getNom(), "+");
			menu.add(afficher(employe));
			menu.add(changerNom(employe));
			menu.add(changerPrenom(employe));
			menu.add(changerMail(employe));
			menu.add(changerPassword(employe));
			menu.add(changerDateArrive(employe));
			menu.add(changerDateDepart(employe));
			menu.addBack("q");
			return menu;
	}
	
	ListOption<Employe> gererEmploye()
	{
		return (employe) -> gererEmploye(employe);		
	}

	private Menu gererEmploye(Employe employe)
	{
		Menu menu = new Menu("Gérer employé " + employe.getNom(), "e");
		menu.add(editerEmploye(employe));
		menu.add(supprimerEmploye(employe));
		menu.addBack("q");
		return menu;
	}
	
	private Option supprimerEmploye(final Employe employe)
	{
		return new Option("Supprimer Employé", "-", () -> {employe.remove();});
	}
	
	private Option changerNom(final Employe employe)
	{
		return new Option("Changer le nom", "n", 
				() -> {employe.setNom(getString("Nouveau nom : "));}
			);
	}
	
	private Option changerPrenom(final Employe employe)
	{
		return new Option("Changer le prénom", "p", () -> {employe.setPrenom(getString("Nouveau prénom : "));});
	}
	
	private Option changerMail(final Employe employe)
	{
		return new Option("Changer le mail", "e", () -> {employe.setMail(getString("Nouveau mail : "));});
	}
	
	private Option changerPassword(final Employe employe)
	{
		return new Option("Changer le password", "x", () -> {employe.setPassword(getString("Nouveau password : "));});
	}
	
	private Option changerDateArrive(final Employe employe)
	{
		return new Option("Changer la date d'arrivée", "a", () -> {
					String dateArr = getString("Nouvelle date d'arrivée YYYY-MM-JJ : ");
					LocalDate dateArrivee = dateArr.equals("") ? null : LocalDate.parse(dateArr);
					employe.setDateArrive(dateArrivee);
		});}
	
	private Option changerDateDepart(final Employe employe)
	{return new Option("Changer la date de départ", "j", () -> {
				String dateDep = getString("Nouvelle date de départ YYYY-MM-JJ : ");
				LocalDate dateDepart = LocalDate.parse(dateDep);
				employe.setDateDepart(dateDepart);
	});}
}
