package com.intiformation;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Pharmacie {

	private HashMap<String, Client> listeClient = new HashMap<String, Client>();
	private HashMap<String, Medicament> listeMedicament = new HashMap<String, Medicament>();
	private static int compteurClient = 1;
	private static int compteurMedicament = 1;
	private static Scanner sc = new Scanner(System.in);

	public Pharmacie() {
		super();
	}

	public void addClient(String nom, String prenom) {
		String _key = nom + "|" + prenom;
		if (listeClient.containsKey(_key)) {
			System.err.println(String.format("Le client '%s %s' existe déjà dans la base de données.", nom, prenom));
		}
		else {
			listeClient.put(_key, new Client(compteurClient, nom, prenom));
			compteurClient++;
		}
	}
	
	public void addClient() {
		System.out.println("Quel est le nom du client?");
		String _nom = sc.next();
		System.out.println("Quel est le prenom du client?");
		String _prenom = sc.next();
		addClient(_nom, _prenom);
	}

	public void addMedicament(String nom, double prix) {
		if (listeClient.containsKey(nom)) {
			System.err.println(String.format("Le médicament '%s' existe déjà dans la base de données.", nom));
		}
		else {
			listeMedicament.put(nom, new Medicament(compteurMedicament, nom, prix));
			compteurMedicament++;
		}
	}
	
	public void addMedicament() {
		System.out.println("Quel est le nom du médicament?");
		String _nom = sc.next();
		System.out.println("Quel est le prix du médicament?");
		double _prix = sc.nextDouble();
		addMedicament(_nom, _prix);
	}

	public Client getClient(String nom, String prenom) {
		String _key = nom + "|" + prenom;
		if (listeClient.containsKey(_key)) {
			return listeClient.get(_key);
		}
		Character _choice;
		do {
			System.out.printf("Le client '%s %s' n'existe pas. Voulez-vous l'ajouter à la base de données? [o/n] : ",
					nom, prenom);
			_choice = sc.next().charAt(0);
		} while (!(_choice.equals('o') || _choice.equals('n')));
		if (_choice.equals('o')) {
			do {
				System.out.printf("Etes-vous sûr de vouloir ajouter le client '%s %s' à la base de données? [o/n] : ",
						nom, prenom);
				_choice = sc.next().charAt(0);
			} while (!(_choice.equals('o') || _choice.equals('n')));
			if (_choice.equals('o')) {
				addClient(nom, prenom);
				return listeClient.get(_key);
			}
		}
		return null;
	}

	public Client getClient() {
		System.out.println("Quel est le nom du client?");
		String _nom = sc.next();
		System.out.println("Quel est le prenom du client?");
		String _prenom = sc.next();
		return getClient(_nom, _prenom);
	}

	public Medicament getMedicament(String nom) {
		if (listeMedicament.containsKey(nom)) {
			return listeMedicament.get(nom);
		}
		Character _choice;
		do {
			System.out.printf("Le médicament '%s' n'existe pas. Voulez-vous l'ajouter à la base de données? [o/n] : ",
					nom);
			_choice = sc.next().charAt(0);
		} while (!(_choice.equals('o') || _choice.equals('n')));
		if (_choice.equals('o')) {
			do {
				System.out.printf("Etes-vous sûr de vouloir ajouter le médicament '%s' à la base de données? [o/n] : ",
						nom);
				_choice = sc.next().charAt(0);
			} while (!(_choice.equals('o') || _choice.equals('n')));
			if (_choice.equals('o')) {
				double _prix;
				do {
					System.out.printf("Quel est le prix du médicament '%s'? : ", nom);
					_prix = sc.nextDouble();
				} while (_prix < 0.);
				addMedicament(nom, _prix);
				return listeMedicament.get(nom);
			}
		}
		return null;
	}

	public Medicament getMedicament() {
		System.out.println("Quel est le nom du médicament?");
		String _nom = sc.next();
		return getMedicament(_nom);
	}
	
	public void approvisionner(String nom, int stock) {
		Medicament _medicament = getMedicament(nom);
		try {
			_medicament.approvisionner(stock);
		}
		catch (Exception e) {
			// TODO: handle exception
			System.err.printf("Le médicament '%s' n'existe pas dans la base de données!", nom);
		}
	}
	
	public void approvisionner() {
		System.out.println("Quel est le nom du médicament?");
		String _nom = sc.next();
		System.out.println("Quel est le stock à ajouter?");
		int _stock = sc.nextInt();
		approvisionner(_nom, _stock);
	}
	
	public void achat(String nomClient, String prenomClient, String nomMedicament, int quantite, double paiement) {
		Client _client = getClient(nomClient, prenomClient);
		Medicament _medicament = getMedicament(nomMedicament);
		
		double _prix = _medicament.getPrix() * quantite;
		
		_client.augmenterCredit(_prix);
		_client.réduireCredit(paiement);
		
		_medicament.deduire(quantite);
	}
	
	public void achat() {
		System.out.println("Quel est le nom du client?");
		String _nomClient = sc.next();
		System.out.println("Quel est le prenom du client?");
		String _prenomClient = sc.next();
		System.out.println("Quel est le nom du médicament?");
		String _nomMedicament = sc.next();
		System.out.println("Quel est la quantité souhaitée?");
		int _quantite = sc.nextInt();
		System.out.println("Combien compte payer le client?");
		double _paiement = sc.nextDouble();
		achat(_nomClient, _prenomClient, _nomMedicament, _quantite, _paiement);
	}

	public void print(String nom, String prenom) {
		String _key = nom + "|" + prenom;
		if (!listeClient.containsKey(_key)) {
			System.err.println(String.format("Le client '%s %s' n'existe pas dans la base de données.", nom, prenom));
		}
		else {
			System.out.println(listeClient.get(_key));
		}
	}

	public void print(String nom) {
		if (!listeClient.containsKey(nom)) {
			System.err.println(String.format("Le médicament '%s' n'existe pas dans la base de données.", nom));
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
			System.err.printf("La catégorie '%s' n'est pas valide! Entrer 'client' ou 'medicament'.%n", categorie);
		}
	}

	public void quitter() {
		sc.close();
	}
}
