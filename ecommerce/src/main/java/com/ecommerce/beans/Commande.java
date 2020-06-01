package com.ecommerce.beans;

import java.time.LocalDate;

public class Commande {

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
	private Client client;
	private LocalDate date;
	private double montant;
	private String modePaiment;
	private String statutPaiment;
	private String modeLivraison;
	private String statutLivraison;
	private String id = "";
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
