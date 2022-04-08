package com.intiformation;

import java.text.DecimalFormat;

import com.intiformation.exception.nbMedicamentNegException;
import com.intiformation.exception.notEnoughStockException;

public class Medicament {

	private int id;
	private String nom;
	private double prix;
	private int stock;
	private static DecimalFormat decf = new DecimalFormat("#.##");

	public Medicament() {
		super();
	}

	public Medicament(int id, String nom, double prix) {
		super();
		this.id = id;
		this.nom = nom;
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

	@Override
	public String toString() {
		return "Medicament [id=" + id + " ; nom=" + nom + "; prix=" + decf.format(prix) + "€" + "; stock=" + stock + "]";
	}

}
