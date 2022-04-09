package com.intiformation;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.TreeMap;

import com.intiformation.exception.CreditNegException;
import com.intiformation.exception.DepassementCreditException;
import com.intiformation.exception.PaiementNegException;

public class Client {

	private int id;
	private String nom;
	private String prenom;
	private double credit;
	private TreeMap<Integer, String> listeTransaction = new TreeMap<Integer, String>();
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

	public void addTransaction(String transaction) {
		this.listeTransaction.put(this.listeTransaction.size() + 1, transaction);
	}

	public void addTransaction(int idMedicament, String nomMedicament, double prixUnitaire, int quantite,
			double paiement, double credit) {
		String _transaction = String.format(
				"[idMédicament=%d ; médicament=%s ; prix unitaire=%s ; quantité=%d ; prix total=%s ; paiement=%s ; crédit=%s]",
				idMedicament, nomMedicament, decf.format(prixUnitaire), quantite, decf.format(prixUnitaire * quantite),
				decf.format(paiement), decf.format(credit));
		addTransaction(_transaction);
	}

	public void addTransaction(Medicament medicament, int quantite, double paiement, double credit) {
		addTransaction(medicament.getId(), medicament.getNom(), medicament.getPrix(), quantite, paiement, credit);
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

	public TreeMap<Integer, String> getListeTransaction() {
		return listeTransaction;
	}

	public void setListeTransaction(TreeMap<Integer, String> listeTransaction) {
		this.listeTransaction = listeTransaction;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + " ; nom=" + nom + " ; prenom=" + prenom + " ; credit=" + decf.format(credit) + "€"
				+ "]";
	}

}
