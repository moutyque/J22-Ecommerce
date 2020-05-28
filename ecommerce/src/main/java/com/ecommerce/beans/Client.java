package com.ecommerce.beans;

import java.nio.file.Path;

public class Client {
	@Override
	public String toString() {
		return this.prenom + " " + this.nom;
	}
	private String nom;
	private String prenom;
	private String adresse;
	private String telephone;
	private String email;
	private String id = "";
	private Path fichier;

	public String getNom() {
		return nom;
	}
	public Path getFichier() {
		return fichier;
	}
	public void setFichier(Path fichier) {
		this.fichier = fichier;
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
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

}
