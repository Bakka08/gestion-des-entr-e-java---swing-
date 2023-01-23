package entities;

public class LigneDemande {
    private int quantite;
    private double prix;
    private Demande demande;
    private Produit produit;

    public LigneDemande(int quantite, double prix, Demande demande, Produit produit) {
        this.quantite = quantite;
        this.prix = prix;
        this.demande = demande;
        this.produit = produit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Demande getDemande() {
        return demande;
    }

    public void setDemande(Demande demande) {
        this.demande = demande;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }


    }


