package com.intiformation;

import java.text.DecimalFormat;

public class Client {

	private int id;
	private String nom;
	private String prenom;
	private double credit;
	private static DecimalFormat decf = new DecimalFormat("#.##");

	public Client() {
		super();
	}

	public Client(int id, String nom, String prenom) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.credit = 0.;
	}

	public void augmenterCredit(double prix) {
		if (prix > 0) {
			this.credit += prix;
		}
		else {
			System.err.println("!!! Erreur !!! Crédit négatif! Opération annulée!");
		}
	}

	public void réduireCredit(double prix) {
		if (prix > 0) {
			this.credit -= prix;
		}
		else {
			System.err.println("!!! Erreur !!! Paiement négatif! Opération annulée!");
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public double getCredit() {
		return credit;
	}

	public void setCredit(double credit) {
		this.credit = credit;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + " ; nom=" + nom + " ; prenom=" + prenom + " ; credit=" + decf.format(credit)
				+ "€" + "]";
	}

}
