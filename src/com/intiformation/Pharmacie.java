package com.intiformation;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.intiformation.exception.CreditNegException;
import com.intiformation.exception.DepassementCreditException;
import com.intiformation.exception.PaiementNegException;
import com.intiformation.exception.nbMedicamentNegException;
import com.intiformation.exception.notEnoughStockException;

public class Pharmacie {

	private HashMap<String, Client> listeClient = new HashMap<String, Client>();
	private HashMap<String, Medicament> listeMedicament = new HashMap<String, Medicament>();
	private static int compteurClient = 1;
	private static int compteurMedicament = 1;
	private static Scanner sc = new Scanner(System.in);
	private static DecimalFormat decf = new DecimalFormat("#.##");

	public Pharmacie() {
		super();
	}

	public void addClient(String nom, String prenom) {
		String _key = nom + "|" + prenom;
		if (listeClient.containsKey(_key)) {
			System.err.println(String.format("Le client '%s %s' existe d�j� dans la base de donn�es.", nom, prenom));
		}
		else {
			listeClient.put(_key, new Client(compteurClient, nom, prenom));
			compteurClient++;
			System.out.println("Client ajout�!");
		}
	}

	public void addClient() {
		System.out.println("Quel est le nom du client?");
		String _nom = sc.next();
		System.out.println("Quel est le pr�nom du client?");
		String _prenom = sc.next();
		addClient(_nom, _prenom);
	}

	public void addMedicament(String nom, double prix) {
		if (listeClient.containsKey(nom)) {
			System.err.println(String.format("Le m�dicament '%s' existe d�j� dans la base de donn�es.", nom));
		}
		else {
			listeMedicament.put(nom, new Medicament(compteurMedicament, nom, prix));
			compteurMedicament++;
			System.out.println("M�dicament ajout�!");
		}
	}

	public void addMedicament() {
		System.out.println("Quel est le nom du m�dicament?");
		String _nom = sc.next();
		System.out.println("Quel est le prix du m�dicament?");
		double _prix = sc.nextDouble();
		addMedicament(_nom, _prix);
	}

	public Client getClient(String nom, String prenom) {
		String _key = nom + "|" + prenom;
		if (listeClient.containsKey(_key)) {
			return listeClient.get(_key);
		}
		char _choice;
		do {
			System.out.printf("Le client '%s %s' n'existe pas. Voulez-vous l'ajouter � la base de donn�es? [o/n] : ",
					nom, prenom);
			_choice = sc.next().charAt(0);
		} while (!(_choice == 'o' || _choice == 'n'));
		if (_choice == 'o') {
			do {
				System.out.printf("Etes-vous s�r de vouloir ajouter le client '%s %s' � la base de donn�es? [o/n] : ",
						nom, prenom);
				_choice = sc.next().charAt(0);
			} while (!(_choice == 'o' || _choice == 'n'));
			if (_choice == 'o') {
				addClient(nom, prenom);
				return listeClient.get(_key);
			}
		}
		return null;
	}

	public Client getClient() {
		System.out.println("Quel est le nom du client?");
		String _nom = sc.next();
		System.out.println("Quel est le pr�nom du client?");
		String _prenom = sc.next();
		return getClient(_nom, _prenom);
	}

	public Medicament getMedicament(String nom) {
		if (listeMedicament.containsKey(nom)) {
			return listeMedicament.get(nom);
		}
		char _choice;
		do {
			System.out.printf("Le m�dicament '%s' n'existe pas. Voulez-vous l'ajouter � la base de donn�es? [o/n] : ",
					nom);
			_choice = sc.next().charAt(0);
		} while (!(_choice == 'o' || _choice == 'n'));
		if (_choice == 'o') {
			do {
				System.out.printf("Etes-vous s�r de vouloir ajouter le m�dicament '%s' � la base de donn�es? [o/n] : ",
						nom);
				_choice = sc.next().charAt(0);
			} while (!(_choice == 'o' || _choice == 'n'));
			if (_choice == 'o') {
				double _prix;
				do {
					System.out.printf("Quel est le prix du m�dicament '%s'?%n", nom);
					_prix = sc.nextDouble();
				} while (_prix < 0.);
				addMedicament(nom, _prix);
				return listeMedicament.get(nom);
			}
		}
		return null;
	}

	public Medicament getMedicament() {
		System.out.println("Quel est le nom du m�dicament?");
		String _nom = sc.next();
		return getMedicament(_nom);
	}

	public void approvisionner(String nom, int stock) {
		Medicament _medicament = getMedicament(nom);
		try {
			_medicament.approvisionner(stock);
			_medicament.addTransaction(stock, _medicament.getStock());
			System.out.println("Approvisionnement effectu�e!");
		}
		catch (nbMedicamentNegException _e) {
			System.err.println("!!! Erreur !!! Approvisionnement n�gatif! Op�ration annul�e!");
		}
		catch (Exception _e) {
			// TODO: handle exception
			System.err.printf("Le m�dicament '%s' n'existe pas dans la base de donn�es!", nom);
			// _e.printStackTrace();
		}
	}

	public void approvisionner() {
		System.out.println("Quel est le nom du m�dicament?");
		String _nom = sc.next();
		System.out.println("Quel est le stock � ajouter?");
		int _stock = sc.nextInt();
		approvisionner(_nom, _stock);
	}

	public void achat(String nomClient, String prenomClient, String nomMedicament, int quantite, double paiement) {
		Client _client = getClient(nomClient, prenomClient);
		Medicament _medicament = getMedicament(nomMedicament);

		double _prix = _medicament.getPrix() * quantite;
		char _choice;
		double _paiement;

		try {
			_client.crediter(_prix);
			_client.payer(paiement);
			_medicament.deduire(quantite);
			_client.addTransaction(_medicament, quantite, paiement, _client.getCredit());
			_medicament.addTransaction(_client, quantite, paiement, _medicament.getStock());
			System.out.println("Achat effectu�e!");
		}
		catch (CreditNegException _e) {
			// TODO Auto-generated catch block
			System.err.println("!!! Erreur !!! Cr�dit n�gatif! Op�ration annul�e!");
		}
		catch (PaiementNegException _e) {
			// TODO Auto-generated catch block
			System.err.println("!!! Erreur !!! Paiement n�gatif! Op�ration annul�e!");
		}
		catch (DepassementCreditException _e) {
			System.err.printf("Le paiement d�passe le cr�dit du client (%s).%n", decf.format(_client.getCredit()));
			System.out.println("Combien compte payer le client?");
			_paiement = sc.nextDouble();
			try {
				_client.payer(_prix);
			}
			catch (PaiementNegException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (DepassementCreditException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			achat(nomClient, prenomClient, nomMedicament, quantite, _paiement);
		}
		catch (nbMedicamentNegException _e) {
			// TODO Auto-generated catch block
			System.err.println("!!! Erreur !!! Quantit� n�gative! Op�ration annul�e!");
		}
		catch (notEnoughStockException _e) {
			// TODO Auto-generated catch block
			// System.err.println("!!! Erreur !!! Quantit� demand�e sup�rieure au stock
			// disponible! Op�ration annul�e!");
			System.err.println("La quantit� demand�e est sup�rieure au stock disponible.");
			int _quantite = _medicament.getStock();
			do {
				System.out.printf("Est-ce que le client souhaite acheter la quantit� disponible (= %d)? [o/n] : ",
						_quantite);
				_choice = sc.next().charAt(0);
			} while (!(_choice == 'o' || _choice == 'n'));
			if (_choice == 'o') {
				System.out.println("Combien compte payer le client?");
				_paiement = sc.nextDouble();
				achat(nomClient, prenomClient, nomMedicament, _quantite, _paiement);
			}
			else {
				System.err.println("!!! Erreur !!! Op�ration annul�e!");
			}
		}
	}

	public void achat() {
		System.out.println("Quel est le nom du client?");
		String _nomClient = sc.next();
		System.out.println("Quel est le pr�nom du client?");
		String _prenomClient = sc.next();
		System.out.println("Quel est le nom du m�dicament?");
		String _nomMedicament = sc.next();
		System.out.println("Quel est la quantit� souhait�e?");
		int _quantite = sc.nextInt();
		System.out.println("Combien compte payer le client?");
		double _paiement = sc.nextDouble();
		achat(_nomClient, _prenomClient, _nomMedicament, _quantite, _paiement);
	}

	public void print(String nom, String prenom) {
		String _key = nom + "|" + prenom;
		if (!listeClient.containsKey(_key)) {
			System.err.println(String.format("Le client '%s %s' n'existe pas dans la base de donn�es.", nom, prenom));
		}
		else {
			System.out.println(listeClient.get(_key));
		}
	}

	public void print(String nom) {
		if (!listeClient.containsKey(nom)) {
			System.err.println(String.format("Le m�dicament '%s' n'existe pas dans la base de donn�es.", nom));
		}
		else {
			System.out.println(listeMedicament.get(nom));
		}
	}

	public void printListe(String categorie) {
		if (categorie.equals("client")) {
			for (Map.Entry<String, Client> _c : listeClient.entrySet()) {
				System.out.println(_c.getValue());
			}
		}
		else if (categorie.equals("medicament")) {
			for (Map.Entry<String, Medicament> _c : listeMedicament.entrySet()) {
				System.out.println(_c.getValue());
			}
		}
		else {
			System.err.printf("La cat�gorie '%s' n'est pas valide! Entrer 'client' ou 'medicament'.%n", categorie);
		}
	}
	
	public void printTransaction(String nomClient, String prenomClient) {
		Client _client = getClient(nomClient, prenomClient);
		for (Map.Entry<Integer, String> _t : _client.getListeTransaction().entrySet()) {
			System.out.println(_t.getValue());
		}
	}
	
	public void printTransaction(String nomMedicament) {
		Medicament _medicament = getMedicament(nomMedicament);
		for (Map.Entry<Integer, String> _t : _medicament.getListeTransaction().entrySet()) {
			System.out.println(_t.getValue());
		}
	}

	public void quitter() {
		sc.close();
	}
}
