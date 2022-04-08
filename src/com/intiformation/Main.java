package com.intiformation;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Pharmacie pharmacie = new Pharmacie();
		
		pharmacie.addClient("Dupond", "Pierre");
		pharmacie.addClient("Dupont", "Sophie");
		
		pharmacie.addMedicament("Doliprane", 1.35);
		pharmacie.addMedicament("Pansement", 3.56);
		
		pharmacie.approvisionner("Doliprane", 500);
		
		boolean runtime = true;
		int userChoice = 0;
		int userChoiceInfo = 0;
		String nomClient = "";
		String prenomClient = "";
		String nomMedicament = "";
		String categorie = "";
		Scanner sc = new Scanner(System.in);

		System.out.println("Bonjour");
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
				pharmacie.addClient();
				System.out.println("###############################################################");
				break;

			case 2:
				System.out.println("##################   Ajouter un médicament   ##################");
				pharmacie.addMedicament();
				System.out.println("###############################################################");
				break;

			case 3:
				System.out.println("#####################   Approvisionner   ######################");
				pharmacie.approvisionner();
				System.out.println("###############################################################");
				break;

			case 4:
				System.out.println("####################   Réaliser un achat   ####################");
				pharmacie.achat();
				System.out.println("###############################################################");
				break;

			case 5:
				System.out.println("Voulez-vous afficher les informations :");
				System.out.println("	[1] : d'un client?");
				System.out.println("	[2] : d'un médicament?");
				System.out.println("	[3] : d'une liste complète de l'un ou l'autre?");
				userChoiceInfo = sc.nextInt();
				System.out.println("###############################################################");
				switch (userChoiceInfo) {
				case 1:
					System.out.println("Quel est le nom du client?");
					nomClient = sc.next();
					System.out.println("Quel est le prenom du client?");
					prenomClient = sc.next();
					pharmacie.print(nomClient, prenomClient);
					break;
				
				case 2:
					System.out.println("Quel est le nom du médicament?");
					nomMedicament = sc.next();
					pharmacie.print(nomMedicament);
					break;
					
				case 3:
					System.out.println("Quelle catégorie? [client ou medicament]");
					categorie = sc.next();
					pharmacie.printListe(categorie);
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
				System.out.println("Fin de l'application! A bientôt!");
				System.out.println("###############################################################");
				break;

			default:
				System.out.println("Je n'ai pas compris!");
				break;
			}
		}
	}

}
