package abstraction.eq2Producteur2;

import abstraction.eq1Producteur1.ventesCacaoAleatoires.IVendeurCacaoAleatoire;
import abstraction.eq7Romu.produits.Feve;
import abstraction.eq7Romu.ventesContratCadre.ContratCadre;
import abstraction.eq7Romu.ventesContratCadre.IVendeurContratCadre;
import abstraction.eq7Romu.ventesContratCadre.StockEnVente;
import abstraction.fourni.IActeur;
import abstraction.fourni.Indicateur;
import abstraction.fourni.Journal;
import abstraction.fourni.Monde;

public class Producteur2 implements IActeur, IVendeurCacaoAleatoire, IVendeurContratCadre<Feve> {
	
	private Indicateur stockFeves;
	private Indicateur soldeBancaire;

	public Producteur2() {
		this.stockFeves=new Indicateur("EQ2 stock feves", this, 1000);
		this.soldeBancaire=new Indicateur("EQ2 solde bancaire", this, 50000);
		Monde.LE_MONDE.ajouterIndicateur(this.stockFeves);
		Monde.LE_MONDE.ajouterIndicateur(this.soldeBancaire);
		Journal journal=new Journal("jEq2");
		Monde.LE_MONDE.ajouterJournal(journal);
		
	}
	
	public String getNom() {
		return "EQ2";
	}

	public void initialiser() {
	}

	public void next() {
		// production
		double nouveauStock = this.stockFeves.getValeur() + Math.random()*300;
		this.stockFeves.setValeur(this, nouveauStock);
	}

	public double quantiteEnVente(double prix) {
		return this.stockFeves.getValeur();
	}
	public void notificationVente(double quantite, double prix) {
		this.stockFeves.retirer(this, quantite);
		this.soldeBancaire.ajouter(this, quantite*prix);
	}

	@Override
	public StockEnVente getStockEnVente() {
		double stockrestant = this.stockFeves.getValeur();
		for (ContratCadre<Feve> cc : this.contratsEnCours) {
			if (Monde.LE_MONDE != null) {
				stockRestant = stockRestant - cc.getQuantiteRestantALivrer();
			}
		}
		StockEnVente<Feve> res = new StockEnVente<Feve>();
		res.ajouter(this.fevesProduites, Math.max(0.0, stockRestant));
		return res;
	}

	@Override
	public double getPrix(Object produit, Double quantite) {
		if (produit==null || quantite<=0.0 || this.getStockEnVente().get(produit)<quantite) {
			return Double.NaN;
		}
		return this.prixVente;
	}

	@Override
	public void proposerEcheancierVendeur(ContratCadre cc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void proposerPrixVendeur(ContratCadre cc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifierVendeur(ContratCadre cc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double livrer(Object produit, double quantite, ContratCadre cc) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void encaisser(double montant, ContratCadre cc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getPrix(Feve produit, Double quantite) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double livrer(Feve produit, double quantite, ContratCadre<Feve> cc) {
		// TODO Auto-generated method stub
		return 0;
	}

}
