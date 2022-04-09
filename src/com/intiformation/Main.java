package com.intiformation;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Pharmacie pharmacie = new Pharmacie();
		
		
		/////////////////////////////////////////
		/// Base de travail : non nécessaire! ///
		/////////////////////////////////////////
		pharmacie.addClient("Dupond", "Pierre", true);
		pharmacie.addClient("Dupont", "Sophie", true);

		pharmacie.addMedicament("Doliprane", 1.35);
		pharmacie.addMedicament("Pansement", 3.56);

		pharmacie.approvisionner("Doliprane", 500);
		/////////////////////////////////////////
		
		// Initialisation des variables utilisateurs
		boolean runtime = true;
		int userChoice = 0;
		int userChoiceInfo = 0;
		String nomClient = "";
		String prenomClient = "";
		String nomMedicament = "";
		String categorie = "";
		Scanner sc = new Scanner(System.in);
		
		
		// Lancement de l'application
		System.out.println("###############################################################");
		System.out.println("########################   Bonjour!   #########################");
		while (runtime) {
			System.out.println("###############################################################");
			System.out.println("Que voulez-vous faire? ");
			System.out.println("	[1] : Ajouter un client?");
			System.out.println("	[2] : Ajouter un médicament?");
			System.out.println("	[3] : Approvisionner un médicament?");
			System.out.println("	[4] : Réaliser un achat?");
			System.out.println("	[5] : Afficher les informations?");
			System.out.println("	[0] : Quitter?");
			userChoice = sc.nextInt();
			System.out.println("###############################################################");

			switch (userChoice) {
			case 1:
				System.out.println("####################   Ajouter un client   ####################");
				System.out.println("###############################################################");
				pharmacie.addClient();
				System.out.println("###############################################################");
				break;

			case 2:
				System.out.println("##################   Ajouter un médicament   ##################");
				System.out.println("###############################################################");
				pharmacie.addMedicament();
				System.out.println("###############################################################");
				break;

			case 3:
				System.out.println("##############   Approvisionner un médicament   ###############");
				System.out.println("###############################################################");
				pharmacie.approvisionner();
				System.out.println("###############################################################");
				break;

			case 4:
				System.out.println("####################   Réaliser un achat   ####################");
				System.out.println("###############################################################");
				pharmacie.achat();
				System.out.println("###############################################################");
				break;

			case 5:
				System.out.println("################   Afficher les informations   ################");
				System.out.println("###############################################################");
				System.out.println("Voulez-vous afficher les informations :");
				System.out.println("	[1] : d'un client?");
				System.out.println("	[2] : d'un médicament?");
				System.out.println("	[3] : d'une liste complète de l'une ou l'autre catégorie?");
				System.out.println("	[4] : des transactions d'un client?");
				System.out.println("	[5] : des transactions d'un médicament?");
				userChoiceInfo = sc.nextInt();
				System.out.println("###############################################################");
				switch (userChoiceInfo) {
				case 1:
					System.out.println("Quel est le nom du client?");
					nomClient = sc.next();
					System.out.println("Quel est le prénom du client?");
					prenomClient = sc.next();
					System.out.println("###############################################################");
					pharmacie.print(nomClient, prenomClient);
					break;

				case 2:
					System.out.println("Quel est le nom du médicament?");
					nomMedicament = sc.next();
					System.out.println("###############################################################");
					pharmacie.print(nomMedicament);
					break;

				case 3:
					System.out.println("Quelle catégorie? ['client' ou 'medicament']");
					categorie = sc.next();
					System.out.println("###############################################################");
					pharmacie.printListe(categorie);
					break;
					
				case 4:
					System.out.println("Quel est le nom du client?");
					nomClient = sc.next();
					System.out.println("Quel est le prénom du client?");
					prenomClient = sc.next();
					System.out.println("###############################################################");
					pharmacie.printTransaction(nomClient, prenomClient);
					break;

				case 5:
					System.out.println("Quel est le nom du médicament?");
					nomMedicament = sc.next();
					System.out.println("###############################################################");
					pharmacie.printTransaction(nomMedicament);
					break;

				default:
					System.out.println("Je n'ai pas compris!");
					break;
				}
				System.out.println("###############################################################");
				break;

			case 0:
				runtime = false;
				sc.close();
				System.out.println("Fin de l'application!");
				System.out.println("###############################################################");
				System.out.println("#######################   A bientôt!   ########################");
				System.out.println("###############################################################");
				break;

			default:
				System.out.println("Je n'ai pas compris!");
				break;
			}
		}
	}

}
