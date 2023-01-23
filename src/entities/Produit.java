package entities;

public class Produit {
    private int id;
    private String designation;
    private double prixAchat;
    private Fournisseur fournisseur;

    public Produit(int id, String designation, double prixAchat , Fournisseur fournisseur) {
        this.id = id;
        this.designation = designation;
        this.prixAchat = prixAchat;
        this.fournisseur = fournisseur;
    }

    public Produit(String designation, double prixAchat, Fournisseur fournisseur) {
        this.designation = designation;
        this.prixAchat = prixAchat;
        this.fournisseur = fournisseur;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public double getPrixAchat() {
        return prixAchat;
    }

    public void setPrixAchat(double prixAchat) {
        this.prixAchat = prixAchat;
    }
    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }
}
