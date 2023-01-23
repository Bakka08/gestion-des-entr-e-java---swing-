package entities;

import java.util.ArrayList;

public class Sortie {
  private Commande commande;
  private int id;
   
  
    public Sortie(int id,Commande commande ) {
        this.id=id;
        this.commande=commande;
       
    }

    public Commande getCommande(){
        return this.commande;
    }
    public void setCommande(Commande commande){
        this.commande=commande;
    }
    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id=id;
    }

  

   
}
