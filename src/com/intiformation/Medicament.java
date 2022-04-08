package com.intiformation;

import java.text.DecimalFormat;

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

	public void approvisionner(int quantite) {
		if (quantite > 0) {
			this.stock += quantite;
		}
		else {
			System.err.println("!!! Erreur !!! Approvisionnement négatif! Opération annulée!");
		}
	}

	public void deduire(int quantite) {
		if (quantite > 0) {
			if ((this.stock - quantite) > 0) {
				this.stock -= quantite;
			}
			else {
				System.err
						.println("!!! Erreur !!! Quantité demandée supérieure au stock disponible! Opération annulée!");
			}
		}
		else {
			System.err.println("!!! Erreur !!! Quantité négative! Opération annulée!");
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
