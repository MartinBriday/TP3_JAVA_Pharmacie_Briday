package com.intiformation;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import com.intiformation.exception.CreditNegException;
import com.intiformation.exception.DepassementCreditException;
import com.intiformation.exception.PaiementNegException;
import com.intiformation.exception.nbMedicamentNegException;
import com.intiformation.exception.notEnoughStockException;

public class Pharmacie {

	private HashMap<Integer, Client> listeClient = new HashMap<Integer, Client>();
	private HashMap<Integer, Medicament> listeMedicament = new HashMap<Integer, Medicament>();
	private static int compteurClient = 0;
	private static int compteurMedicament = 0;
	private static Scanner sc = new Scanner(System.in);
	private static DecimalFormat decf = new DecimalFormat("#.##");

	public Pharmacie() {
		super();
	}

	public void addClient(String nom, String prenom, boolean forceAdd) {
		boolean hasClient = false;
		for (Map.Entry<Integer, Client> _c : listeClient.entrySet()) {
			if (_c.getValue().getNom().equals(nom) && _c.getValue().getPrenom().equals(prenom)) {
				hasClient = true;
				break;
			}
		}
		if (!hasClient || forceAdd) {
			compteurClient++;
			listeClient.put(compteurClient, new Client(compteurClient, nom, prenom));
			System.out.println("Client ajout�!");
		}
		else {
			System.err.println(String.format("Le client '%s %s' existe d�j� dans la base de donn�es.", nom, prenom));
			char _choice;
			do {
				System.out.println("Voulez-vous en cr�er un nouveau? [o/n] : ");
				_choice = sc.next().charAt(0);
			} while (!(_choice == 'o' || _choice == 'n'));
			if (_choice == 'o') {
				compteurClient++;
				listeClient.put(compteurClient, new Client(compteurClient, nom, prenom));
				System.out.println("Client ajout�!");
			}
			else {
				System.err.println("Op�ration annul�e!");
			}
		}
	}

	public void addClient() {
		System.out.println("Quel est le nom du client?");
		String _nom = sc.next();
		System.out.println("Quel est le pr�nom du client?");
		String _prenom = sc.next();
		addClient(_nom, _prenom, false);
	}

	public void addMedicament(String nom, double prix) {
		boolean hasMedicament = false;
		for (Map.Entry<Integer, Medicament> _c : listeMedicament.entrySet()) {
			if (_c.getValue().getNom().equals(nom)) {
				hasMedicament = true;
				break;
			}
		}
		if (!hasMedicament) {
			compteurMedicament++;
			listeMedicament.put(compteurMedicament, new Medicament(compteurMedicament, nom, prix));
			System.out.println("M�dicament ajout�!");
		}
		else {
			System.err.println(String.format("Le m�dicament '%s' existe d�j� dans la base de donn�es.", nom));
			char _choice;
			do {
				System.out.println("Voulez-vous en cr�er un nouveau? [o/n] : ");
				_choice = sc.next().charAt(0);
			} while (!(_choice == 'o' || _choice == 'n'));
			if (_choice == 'o') {
				compteurMedicament++;
				listeMedicament.put(compteurMedicament, new Medicament(compteurMedicament, nom, prix));
				System.out.println("M�dicament ajout�!");
			}
			else {
				System.err.println("Op�ration annul�e!");
			}
		}
	}

	public void addMedicament() {
		System.out.println("Quel est le nom du m�dicament?");
		String _nom = sc.next();
		System.out.println("Quel est le prix du m�dicament?");
		double _prix = sc.nextDouble();
		addMedicament(_nom, _prix);
	}

	public Client getClient(int id) {
		if (listeClient.containsKey(id)) {
			return listeClient.get(id);
		}
		else {

		}
		System.err.println(String.format("Le client '%d' n'existe pas dans la base de donn�es.", id));
		return null;
	}

	public Client getClient(String nom, String prenom, boolean forceAdd) {
		TreeMap<Integer, Client> _listeCLient = new TreeMap<Integer, Client>();
		for (Map.Entry<Integer, Client> _c : listeClient.entrySet()) {
			if (_c.getValue().getNom().equals(nom) && _c.getValue().getPrenom().equals(prenom)) {
				_listeCLient.put(_c.getKey(), _c.getValue());
			}
		}
		if (_listeCLient.size() == 1 && !forceAdd) {
			return getClient(_listeCLient.firstKey());
		}
		else if (_listeCLient.size() > 1 && !forceAdd) {
			System.out.println(String.format("Il existe plusieurs '%s %s' dans la base de donn�es.", nom, prenom));
			int _id;
			do {
				System.out.println(String.format("Pr�ciser l'identifiant %s : ",
						Arrays.toString(_listeCLient.keySet().toArray())));
				_id = sc.nextInt();
			} while (!(_listeCLient.containsKey(_id)));
			return getClient(_id);
		}
		char _choice = 0;
		if (!forceAdd) {
			do {
				System.err.println(String.format("Le client '%s %s' n'existe pas.", nom, prenom));
				System.out.println(String.format("Voulez-vous ajouter le client '%s %s' � la base de donn�es? [o/n] : ",
						nom, prenom));
				_choice = sc.next().charAt(0);
			} while (!(_choice == 'o' || _choice == 'n'));
		}
		if (_choice == 'o' || forceAdd) {
			do {
				System.out.println(String.format(
						"Etes-vous s�r de vouloir ajouter le client '%s %s' � la base de donn�es? [o/n] : ", nom,
						prenom));
				_choice = sc.next().charAt(0);
			} while (!(_choice == 'o' || _choice == 'n'));
			if (_choice == 'o') {
				addClient(nom, prenom, true);
				return getClient(compteurClient);
			}
		}
		return null;
	}

	public Medicament getMedicament(int id) {
		if (listeMedicament.containsKey(id)) {
			return listeMedicament.get(id);
		}
		System.err.println(String.format("Le m�dicament '%d' n'existe pas dans la base de donn�es.", id));
		return null;
	}

	public Medicament getMedicament(String nom) {
		TreeMap<Integer, Medicament> _listeMedicament = new TreeMap<Integer, Medicament>();
		for (Map.Entry<Integer, Medicament> _m : listeMedicament.entrySet()) {
			if (_m.getValue().getNom().equals(nom)) {
				_listeMedicament.put(_m.getKey(), _m.getValue());
			}
		}
		if (_listeMedicament.size() == 1) {
			return getMedicament(_listeMedicament.firstKey());
		}
		else if (_listeMedicament.size() > 1) {
			System.out.println(String.format("Il existe plusieurs m�dicaments '%s' dans la base de donn�es.", nom));
			int _id;
			do {
				System.out.println(String.format("Pr�ciser l'identifiant %s : ",
						Arrays.toString(_listeMedicament.keySet().toArray())));
				_id = sc.nextInt();
			} while (!(_listeMedicament.containsKey(_id)));
			return getMedicament(_id);
		}
		char _choice;
		do {
			System.err.println(String.format("Le m�dicament '%s' n'existe pas.", nom));
			System.out.println("Voulez-vous l'ajouter � la base de donn�es? [o/n] : ");
			_choice = sc.next().charAt(0);
		} while (!(_choice == 'o' || _choice == 'n'));
		if (_choice == 'o') {
			do {
				System.out.println(String.format(
						"Etes-vous s�r de vouloir ajouter le m�dicament '%s' � la base de donn�es? [o/n] : ", nom));
				_choice = sc.next().charAt(0);
			} while (!(_choice == 'o' || _choice == 'n'));
			if (_choice == 'o') {
				double _prix;
				do {
					System.out.println(String.format("Quel est le prix du m�dicament '%s'?", nom));
					_prix = sc.nextDouble();
				} while (_prix < 0.);
				addMedicament(nom, _prix);
				return getMedicament(compteurMedicament);
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
			System.err.println(String.format("Le m�dicament '%s' n'existe pas dans la base de donn�es!", nom));
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

	public void achat(Client client, Medicament medicament, int quantite, double paiement) {
		double _prix = medicament.getPrix() * quantite;
		char _choice;
		double _paiement;

		try {
			client.crediter(_prix);
			client.payer(paiement);
			medicament.deduire(quantite);
			client.addTransaction(medicament, quantite, paiement, client.getCredit());
			medicament.addTransaction(client, quantite, paiement, medicament.getStock());
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
			System.err.println(
					String.format("Le paiement d�passe le cr�dit du client (%s).", decf.format(client.getCredit())));
			System.out.println("Combien compte payer le client?");
			_paiement = sc.nextDouble();
			try {
				client.payer(_prix);
			}
			catch (PaiementNegException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (DepassementCreditException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			achat(client, medicament, quantite, _paiement);
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
			int _quantite = medicament.getStock();
			do {
				System.out.println(String.format(
						"Est-ce que le client souhaite acheter la quantit� disponible (= %d)? [o/n] : ", _quantite));
				_choice = sc.next().charAt(0);
			} while (!(_choice == 'o' || _choice == 'n'));
			if (_choice == 'o') {
				System.out.println("Combien compte payer le client?");
				_paiement = sc.nextDouble();
				achat(client, medicament, _quantite, _paiement);
			}
			else {
				System.err.println("!!! Erreur !!! Op�ration annul�e!");
			}
		}
	}

	public void achat(String nomClient, String prenomClient, String nomMedicament, int quantite, double paiement,
			boolean forceAddClient) {
		Client _client = getClient(nomClient, prenomClient, forceAddClient);
		Medicament _medicament = getMedicament(nomMedicament);
		achat(_client, _medicament, quantite, paiement);
	}

	public void achat(int idClient, String nomMedicament, int quantite, double paiement) {
		Client _client = getClient(idClient);
		Medicament _medicament = getMedicament(nomMedicament);
		achat(_client, _medicament, quantite, paiement);
	}

	public void achat() {
		int _idClient = 0;
		char _choice;
		boolean _forceAddClient = false;
		System.out.println("Quel est le nom du client?");
		String _nomClient = sc.next();
		System.out.println("Quel est le pr�nom du client?");
		String _prenomClient = sc.next();
		TreeMap<Integer, Client> _listeCLient = new TreeMap<Integer, Client>();
		for (Map.Entry<Integer, Client> _c : listeClient.entrySet()) {
			if (_c.getValue().getNom().equals(_nomClient) && _c.getValue().getPrenom().equals(_prenomClient)) {
				_listeCLient.put(_c.getKey(), _c.getValue());
			}
		}
		if (_listeCLient.size() >= 1) {
			for (Map.Entry<Integer, Client> _c : _listeCLient.entrySet()) {
				System.out.println(_c.getValue());
			}
			do {
				System.out.println(String.format("Voulez-vous cr�er un nouveau client '%s %s'? [o/n] : ", _nomClient,
						_prenomClient));
				_choice = sc.next().charAt(0);
			} while (!(_choice == 'o' || _choice == 'n'));
			if (_choice == 'o') {
				_forceAddClient = true;
			}
			else {
				do {
					System.out.println(String.format("Quel est l'identifiant du client existant %s?",
							Arrays.toString(_listeCLient.keySet().toArray())));
					_idClient = sc.nextInt();
				} while (!(_listeCLient.containsKey(_idClient)));
			}
		}
		System.out.println("Quel est le nom du m�dicament?");
		String _nomMedicament = sc.next();
		System.out.println("Quel est la quantit� souhait�e?");
		int _quantite = sc.nextInt();
		System.out.println("Combien compte payer le client?");
		double _paiement = sc.nextDouble();
		if (_idClient > 0) {
			achat(_idClient, _nomMedicament, _quantite, _paiement);
		}
		else {
			achat(_nomClient, _prenomClient, _nomMedicament, _quantite, _paiement, _forceAddClient);
		}
	}

	public void print(String nom, String prenom) {
		System.out.println(getClient(nom, prenom, false));
	}

	public void print(String nom) {
		System.out.println(getMedicament(nom));
	}

	public void printListe(String categorie) {
		if (categorie.equals("client")) {
			for (Map.Entry<Integer, Client> _c : listeClient.entrySet()) {
				System.out.println(_c.getValue());
			}
		}
		else if (categorie.equals("medicament")) {
			for (Map.Entry<Integer, Medicament> _c : listeMedicament.entrySet()) {
				System.out.println(_c.getValue());
			}
		}
		else {
			System.err.println(
					String.format("La cat�gorie '%s' n'est pas valide! Entrer 'client' ou 'medicament'.", categorie));
		}
	}

	public void printTransaction(String nomClient, String prenomClient) {
		Client _client = getClient(nomClient, prenomClient, false);
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

	public HashMap<Integer, Client> getListeClient() {
		return listeClient;
	}

	public void setListeClient(HashMap<Integer, Client> listeClient) {
		this.listeClient = listeClient;
	}

	public HashMap<Integer, Medicament> getListeMedicament() {
		return listeMedicament;
	}

	public void setListeMedicament(HashMap<Integer, Medicament> listeMedicament) {
		this.listeMedicament = listeMedicament;
	}
}
