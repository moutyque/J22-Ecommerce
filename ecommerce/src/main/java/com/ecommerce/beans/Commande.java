package com.ecommerce.beans;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "commande")
public class Commande {

	@ManyToOne
	@JoinColumn(name = "id_client")
	private Client client;

	@Column(name = "date")
	private LocalDate date;

	@Column(name = "montant")
	private double montant;

	@Column(name = "mode_paiement")
	private String modePaiment;

	@Column(name = "statut_paiement")
	private String statutPaiment;

	@Column(name = "mode_livraison")
	private String modeLivraison;

	@Column(name = "statut_livraison")
	private String statutLivraison;
	@Id
	private String id = "";

	public Commande() {

	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}
	public String getModePaiment() {
		return modePaiment;
	}
	public void setModePaiment(String modePaiment) {
		this.modePaiment = modePaiment;
	}
	public String getStatutPaiment() {
		return statutPaiment;
	}
	public void setStatutPaiment(String statutPaiment) {
		this.statutPaiment = statutPaiment;
	}
	public String getModeLivraison() {
		return modeLivraison;
	}
	public void setModeLivraison(String modeLivraison) {
		this.modeLivraison = modeLivraison;
	}
	public String getStatutLivraison() {
		return statutLivraison;
	}
	public void setStatutLivraison(String statutLivraison) {
		this.statutLivraison = statutLivraison;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void generateId() {

		this.setId(BeanHelper.generateId(this.getDate().toString()
				+ this.getMontant() + this.getClient().toString()));
	}
}
