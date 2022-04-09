package com.intiformation;

import java.text.DecimalFormat;
import java.util.TreeMap;

import com.intiformation.exception.PrixNegException;
import com.intiformation.exception.nbMedicamentNegException;
import com.intiformation.exception.notEnoughStockException;

public class Medicament {

	private int id;
	private String nom;
	private double prix;
	private int stock;
	private TreeMap<Integer, String> listeTransaction = new TreeMap<Integer, String>();
	private static DecimalFormat decf = new DecimalFormat("#.##");

	public Medicament() {
		super();
	}

	public Medicament(int id, String nom, double prix) throws PrixNegException {
		super();
		this.id = id;
		this.nom = nom;
		if (prix < 0.) {
			throw new PrixNegException();
		}
		this.prix = prix;
		this.stock = 0;
	}

	public void approvisionner(int quantite) throws nbMedicamentNegException {
		if (quantite < 0) {
			throw new nbMedicamentNegException();
		}
		this.stock += quantite;
	}

	public void deduire(int quantite) throws nbMedicamentNegException, notEnoughStockException {
		if (quantite < 0) {
			throw new nbMedicamentNegException();
		}
		if ((this.stock - quantite) < 0) {
			throw new notEnoughStockException();
		}
		this.stock -= quantite;
	}

	public void addTransaction(String transaction) {
		this.listeTransaction.put(this.listeTransaction.size() + 1, transaction);
	}

	public void addTransaction(int idClient, String nomClient, String prenomClient, int quantite, double paiement,
			int stock) {
		String _transaction = String.format("[type=achat ; idClient=%d ; nom=%s ; prenom=%s ; "
				+ "quantité=%d ; paiement=%s€ ; stock=%s]", idClient,
				nomClient, prenomClient, quantite, decf.format(paiement), stock);
		addTransaction(_transaction);
	}

	public void addTransaction(Client client, int quantite, double paiement, int stock) {
		addTransaction(client.getId(), client.getNom(), client.getPrenom(), quantite, paiement, stock);
	}

	public void addTransaction(int quantite, int stock) {
		String _transaction = String.format("[type=approvisionnement ; quantité=%d ; stock=%d]", quantite, stock);
		addTransaction(_transaction);
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

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public TreeMap<Integer, String> getListeTransaction() {
		return listeTransaction;
	}

	public void setListeTransaction(TreeMap<Integer, String> listeTransaction) {
		this.listeTransaction = listeTransaction;
	}

	@Override
	public String toString() {
		return "Medicament [id=" + id + " ; nom=" + nom + "; prix=" + decf.format(prix) + "€" + "; stock=" + stock
				+ "]";
	}

}
