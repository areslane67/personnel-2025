package jdbc;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import commandLineMenus.examples.employees.core.Employee;

import java.sql.Date;

import personnel.*;

public class JDBC implements Passerelle 
{
	Connection connection;

	public JDBC()
	{
		try
		{
			Class.forName(Credentials.getDriverClassName());
			connection = DriverManager.getConnection(Credentials.getUrl(), Credentials.getUser(), Credentials.getPassword());
			System.out.println("CONNECTE AMEN");
		}
		catch (ClassNotFoundException e)
		{
			System.out.println("Pilote JDBC non installé.");
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}
	}
	
	@Override
	public GestionPersonnel getGestionPersonnel() 
	{
		GestionPersonnel gestionPersonnel = new GestionPersonnel();
		try 
		{
			String requete = "SELECT * FROM ligue";
			Statement instruction = connection.createStatement();
			ResultSet ligues = instruction.executeQuery(requete);
			while (ligues.next())
				gestionPersonnel.addLigue(ligues.getInt(1), ligues.getString(2));
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}
		return gestionPersonnel;
	}

	@Override
	public void sauvegarderGestionPersonnel(GestionPersonnel gestionPersonnel) throws SauvegardeImpossible 
	{
		close();
	}
	
	public void close() throws SauvegardeImpossible
	{
		try
		{
			if (connection != null)
				connection.close();
		}
		catch (SQLException e)
		{
			throw new SauvegardeImpossible(e);
		}
	}
	
	@Override
	public int insert(Ligue ligue) throws SauvegardeImpossible 
	{
		try 
		{
			PreparedStatement instruction;
			instruction = connection.prepareStatement("INSERT INTO ligue (nom_ligue) values (?)", Statement.RETURN_GENERATED_KEYS);
			instruction.setString(1, ligue.getNom());		
			instruction.executeUpdate();
			ResultSet id = instruction.getGeneratedKeys();
			id.next();
			return id.getInt(1);
		} 
		catch (SQLException exception) 
		{
			exception.printStackTrace();
			throw new SauvegardeImpossible(exception);
		}		
	}
	@Override
	public int insert(Employe employe) throws SauvegardeImpossible {
		try {
			PreparedStatement instruction;
			instruction = connection.prepareStatement( "INSERT INTO employe (nom_employe, prenom_employe, password_employe, mail_employe, date_arrive, "
					+ "date_depart,habilitation,id_employe) VALUES (?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			instruction.setString(1, employe.getNom());
			instruction.setString(2, employe.getPrenom());
			instruction.setString(3, employe.getPassword());
			instruction.setString(4, employe.getMail());
			instruction.setDate(5, employe.getDateArrive() == null ? null : Date.valueOf(employe.getDateArrive()));
			instruction.setDate(6, employe.getDateDepart() == null ? null : Date.valueOf(employe.getDateDepart()));
			instruction.setBoolean(7, employe.estAdmin());
			instruction.setInt(8, employe.getLigue().getId());
			instruction.executeUpdate();
			ResultSet id = instruction.getGeneratedKeys();
			id.next();
			return id.getInt(1);
		} 
		catch (SQLException exception) 
		{
			exception.printStackTrace();
			throw new SauvegardeImpossible(exception);
		}
	}
	@Override
	public void delete(Employe employe) throws SauvegardeImpossible {
		try {
			PreparedStatement instruction;
			instruction = connection.prepareStatement("DELETE FROM employe WHERE id_employe = (?)");
			instruction.setInt(1, employe.getId());
			instruction.executeUpdate();
		} 
		catch (SQLException exception) 
		{
			exception.printStackTrace();
			throw new SauvegardeImpossible(exception);
		}
	}
	@Override
	public void add(Ligue ligue) throws SauvegardeImpossible {
		try {
			PreparedStatement instruction;
			instruction = connection.prepareStatement("DELETE FROM ligue WHERE id_ligue = (?)");
			instruction.setInt(1, ligue.getId());
			instruction.executeUpdate();
		} 
		catch (SQLException exception) 
		{
			exception.printStackTrace();
			throw new SauvegardeImpossible(exception);
		}
	}
	@Override
	public void delete(Ligue ligue) throws SauvegardeImpossible {
		try {
			PreparedStatement instruction;
			instruction = connection.prepareStatement("DELETE FROM ligue WHERE id_ligue = (?)");
			instruction.setInt(1, ligue.getId());
			instruction.executeUpdate();
		} 
		catch (SQLException exception) 
		{
			exception.printStackTrace();
			throw new SauvegardeImpossible(exception);
		}
	}
	@Override
	public void update(Ligue ligue) throws SauvegardeImpossible {
		try {
			PreparedStatement instruction;
			instruction = connection.prepareStatement("UPDATE FROM ligue SET nom_ligue = (?) WHERE id_ligue = (?)");
			instruction.setString(1, ligue.getNom());
			instruction.setInt(2, ligue.getId());
			instruction.executeUpdate();
		} 
		catch (SQLException exception) 
		{
			exception.printStackTrace();
			throw new SauvegardeImpossible(exception);
		}
	}
	@Override
	public void update(Employe employe) throws SauvegardeImpossible {
		try {
			PreparedStatement instruction;
			instruction = connection.prepareStatement("UPDATE FROM employe SET nom_employe = (?), prenom_employe = (?), password_employe = (?), mail_employe = (?), date-arrive = (?), date-depart = (?), habilitation = (?) WHERE id_employe = (?)");
			instruction.setString(1, employe.getNom());
			instruction.setString(2, employe.getPrenom());
			instruction.setString(3, employe.getPassword());
			instruction.setString(4, employe.getMail());
			instruction.setDate(5, employe.getDateArrive() == null ? null : Date.valueOf(employe.getDateArrive()));
			instruction.setDate(6, employe.getDateDepart() == null ? null : Date.valueOf(employe.getDateDepart()));
			instruction.setBoolean(7, employe.estAdmin());
			//instruction.setInt(8, employe.getLigueId());
			instruction.executeUpdate();
		} 
		catch (SQLException exception) 
		{
			exception.printStackTrace();
			throw new SauvegardeImpossible(exception);
		}
	}

	@Override
	public void add(Employe employe) throws SauvegardeImpossible {
		try {
			PreparedStatement instruction;
			instruction = connection.prepareStatement("DELETE FROM ligue WHERE id_ligue = (?)");
			instruction.setString(1, employe.getNom());
			instruction.setString(2, employe.getPrenom());
			instruction.setString(3, employe.getPassword());
			instruction.setString(4, employe.getMail());
			instruction.setDate(5, employe.getDateArrive() == null ? null : Date.valueOf(employe.getDateArrive()));
			instruction.setDate(6, employe.getDateDepart() == null ? null : Date.valueOf(employe.getDateDepart()));
			instruction.setBoolean(7, employe.estAdmin());
			//instruction.setInt(8, employe.getLigueId());
			instruction.executeUpdate();
		} 
		catch (SQLException exception) 
		{
			exception.printStackTrace();
			throw new SauvegardeImpossible(exception);
		}
		
	}
}
