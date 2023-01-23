package entities;

public class LigneCommande {
    private int quantite;
    private double prix;
    private Commande command;
    private Produit produit;

    public LigneCommande(int quantite, double prix, Commande command, Produit produit) {
        this.quantite = quantite;
        this.prix = prix;
        this.command = command;
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

    public Commande getCommand() {
        return command;
    }

    public void setCommand(Commande command) {
        this.command = command;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    
}
