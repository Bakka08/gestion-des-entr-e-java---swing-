package entities;

import java.util.ArrayList;

public class entree {
  private Demande demande;
  private int id;
   
  
    public entree(int id,Demande demande) {
        this.id=id;
        this.demande=demande;
       
    }

    public Demande getDemande(){
        return this.demande;
    }
    public void setDemande(Demande demande){
        this.demande=demande;
    }
    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id=id;
    }

  

   
}
