package entities;

import java.util.ArrayList;

public class stock {
  private Produit produit ; 
  private int quantite ;
  
    public stock(Produit produit , int quantite) {
        this.produit = produit;
        this.quantite = quantite;
        
    }

    public Produit getProduit(){
        return this.produit;
    }
    public void setProduit(Produit produit){
        this.produit=produit;
    }
      public int getQuantite(){
        return this.quantite;
    }
    public void setQuantite(int quantite){
        this.quantite=quantite;
    }

  

   
}
