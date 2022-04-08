package com.intiformation;

import java.text.DecimalFormat;
import java.util.ArrayList;

import com.intiformation.exception.CreditNegException;
import com.intiformation.exception.DepassementCreditException;
import com.intiformation.exception.PaiementNegException;

public class Client {

	private int id;
	private String nom;
	private String prenom;
	private double credit;
	//private ArrayList<ArrayList<String>> listeTransaction = new ArrayList<ArrayList<String>>();
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

	public void crediter(double prix) throws CreditNegException {
		if (prix < 0) {
			throw new CreditNegException();
		}
		this.credit += prix;
	}

	public void payer(double prix) throws PaiementNegException, DepassementCreditException {
		if (prix < 0) {
			throw new PaiementNegException();
		}
		if ((this.credit - prix) < 0) {
			throw new DepassementCreditException();
		}
		this.credit -= prix;
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
