package commandLine;

import personnel.*;
import commandLineMenus.*;
import static commandLineMenus.rendering.examples.util.InOut.*;

public class PersonnelConsole {
	private GestionPersonnel gestionPersonnel;
	LigueConsole ligueConsole;
	EmployeConsole employeConsole;

	public PersonnelConsole(GestionPersonnel gestionPersonnel) {
		this.gestionPersonnel = gestionPersonnel;
		this.employeConsole = new EmployeConsole();
		this.ligueConsole = new LigueConsole(gestionPersonnel, employeConsole);
	}

	public void start() {
		menuPrincipal().start();
	}

	// Retour au menu principal

	private Menu menuPrincipal() {
		Menu menu = new Menu("Gestion du personnel des ligues");
		menu.add(employeConsole.editerEmploye(gestionPersonnel.getRoot()));
		menu.add(ligueConsole.menuLigues());
		menu.add(menuQuitter());
		return menu;
	}

	private Menu menuQuitter() {
		Menu menu = new Menu("Quitter", "q");
		menu.add(quitterEtEnregistrer());
		menu.add(quitterSansEnregistrer());
		menu.addBack("r");
		return menu;
	}

	// Enregistrer avant de quitter

	private Option quitterEtEnregistrer() {
		return new Option("Quitter et enregistrer", "q", () -> {
			try {
				gestionPersonnel.sauvegarder();
				Action.QUIT.optionSelected();
			} catch (SauvegardeImpossible e) {
				System.out.println("Impossible d'effectuer la sauvegarde");
			}
		});
	}

	// Quitter sans enregistrer

	private Option quitterSansEnregistrer() {
		return new Option("Quitter sans enregistrer", "a", Action.QUIT);
	}

	// Verifie le mot de passe

	private boolean verifiePassword() {
		boolean ok = gestionPersonnel.getRoot().checkPassword(getString("password : "));
		if (!ok)
			System.out.println("Password incorrect.");
		return ok;
	}

	// Ajout d'une méthode utilitaire getString si l'import statique n'est pas disponible
	private String getString(String message) {
		System.out.print(message);
		java.util.Scanner scanner = new java.util.Scanner(System.in);
		return scanner.nextLine();
	}

	public static void main(String[] args) throws SauvegardeImpossible {
		PersonnelConsole personnelConsole = new PersonnelConsole(GestionPersonnel.getGestionPersonnel());
		if (personnelConsole.verifiePassword())
			personnelConsole.start();
	}
}
