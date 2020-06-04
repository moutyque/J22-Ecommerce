package com.ecommerce.beans;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "client")
public class Client {
	@Override
	public String toString() {
		return this.prenom + " " + this.nom;
	}

	private String nom = "";
	private String prenom = "";
	private String adresse = "";
	private String telephone = "";
	private String email = "";

	@Id
	private String id = "";

	private String image;

	public Client() {

	}
	public String getNom() {
		return nom;
	}

	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
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
	public void generateId() {
		this.setId(BeanHelper.generateId(
				this.getNom() + this.getPrenom() + this.getEmail()));
	}

}
